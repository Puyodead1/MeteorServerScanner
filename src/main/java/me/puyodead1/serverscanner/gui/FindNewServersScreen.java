package me.puyodead1.serverscanner.gui;

import com.google.common.net.HostAndPort;
import com.google.gson.reflect.TypeToken;
import me.puyodead1.serverscanner.ServerScanner;
import me.puyodead1.serverscanner.SmallHttp;
import me.puyodead1.serverscanner.country.Country;
import me.puyodead1.serverscanner.country.CountrySetting;
import me.puyodead1.serverscanner.ssapi.requests.ServersRequest;
import me.puyodead1.serverscanner.ssapi.responses.ServersResponse;
import me.puyodead1.serverscanner.utils.MultiplayerScreenUtil;
import meteordevelopment.meteorclient.gui.GuiThemes;
import meteordevelopment.meteorclient.gui.WindowScreen;
import meteordevelopment.meteorclient.gui.widgets.containers.WContainer;
import meteordevelopment.meteorclient.gui.widgets.containers.WTable;
import meteordevelopment.meteorclient.gui.widgets.pressable.WButton;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.utils.network.MeteorExecutor;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.nbt.NbtCompound;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static me.puyodead1.serverscanner.ServerScanner.gson;

public class FindNewServersScreen extends WindowScreen {
    public static NbtCompound savedSettings;
    private int timer;
    public WButton findButton;
    private boolean threadHasFinished;
    private String threadError;
    private List<ServersResponse.Server> threadServers;

    public enum Cracked {
        Any, Yes, No;

        public Boolean toBoolOrNull() {
            return switch (this) {
                case Any -> null;
                case Yes -> true;
                case No -> false;
            };
        }
    }

    public enum Version {
        Current, Any, Protocol, VersionString;

        @Override
        public String toString() {
            return switch (this) {
                case Current -> "Current";
                case Any -> "Any";
                case Protocol -> "Protocol";
                case VersionString -> "Version String";
            };
        }
    }

    public enum NumRangeType {
        Any, Equals, AtLeast, AtMost, Between;

        @Override
        public String toString() {
            return switch (this) {
                case Any -> "Any";
                case Equals -> "Equal To";
                case AtLeast -> "At Least";
                case AtMost -> "At Most";
                case Between -> "Between";
            };
        }
    }

    // Didn't have a better name
    public enum GeoSearchType {
        None, Country
    }

    private final Settings settings = new Settings();
    private final SettingGroup sg = settings.getDefaultGroup();
    WContainer settingsContainer;

    private final Setting<Cracked> crackedSetting = sg.add(new EnumSetting.Builder<Cracked>().name("cracked").description("Whether the server should be cracked or not").defaultValue(Cracked.Any).build());

    private final Setting<NumRangeType> onlinePlayersNumTypeSetting = sg.add(new EnumSetting.Builder<NumRangeType>().name("online-players-range").description("The type of number range for the online players").defaultValue(NumRangeType.Any).build());

    private final Setting<Integer> equalsOnlinePlayersSetting = sg.add(new IntSetting.Builder().name("online-players").description("The amount of online players the server should have").defaultValue(2).min(0).visible(() -> onlinePlayersNumTypeSetting.get().equals(NumRangeType.Equals)).noSlider().build());


    private final Setting<Integer> atLeastOnlinePlayersSetting = sg.add(new IntSetting.Builder().name("minimum-online-players").description("The minimum amount of online players the server should have").defaultValue(1).min(0).visible(() -> onlinePlayersNumTypeSetting.get().equals(NumRangeType.AtLeast) || onlinePlayersNumTypeSetting.get().equals(NumRangeType.Between)).noSlider().build());

    private final Setting<Integer> atMostOnlinePlayersSetting = sg.add(new IntSetting.Builder().name("maximum-online-players").description("The maximum amount of online players the server should have").defaultValue(20).min(0).visible(() -> onlinePlayersNumTypeSetting.get().equals(NumRangeType.AtMost) || onlinePlayersNumTypeSetting.get().equals(NumRangeType.Between)).noSlider().build());


    private final Setting<NumRangeType> maxPlayersNumTypeSetting = sg.add(new EnumSetting.Builder<NumRangeType>().name("max-players-range").description("The type of number range for the max players").defaultValue(NumRangeType.Any).build());

    private final Setting<Integer> equalsMaxPlayersSetting = sg.add(new IntSetting.Builder().name("max-players").description("The amount of max players the server should have").defaultValue(2).min(0).visible(() -> maxPlayersNumTypeSetting.get().equals(NumRangeType.Equals)).noSlider().build());


    private final Setting<Integer> atLeastMaxPlayersSetting = sg.add(new IntSetting.Builder().name("minimum-max-players").description("The minimum amount of max players the server should have").defaultValue(1).min(0).visible(() -> maxPlayersNumTypeSetting.get().equals(NumRangeType.AtLeast) || maxPlayersNumTypeSetting.get().equals(NumRangeType.Between)).noSlider().build());

    private final Setting<Integer> atMostMaxPlayersSetting = sg.add(new IntSetting.Builder().name("maximum-max-players").description("The maximum amount of max players the server should have").defaultValue(20).min(0).visible(() -> maxPlayersNumTypeSetting.get().equals(NumRangeType.AtMost) || maxPlayersNumTypeSetting.get().equals(NumRangeType.Between)).noSlider().build());

    private final Setting<String> descriptionSetting = sg.add(new StringSetting.Builder().name("MOTD").description("What the MOTD of the server should contain (empty for any)").defaultValue("").build());

    private final Setting<ServersRequest.Software> softwareSetting = sg.add(new EnumSetting.Builder<ServersRequest.Software>().name("software").description("The server software the servers should have").defaultValue(ServersRequest.Software.Any).build());

    private final Setting<Version> versionSetting = sg.add(new EnumSetting.Builder<Version>().name("version").description("The protocol version the servers should have").defaultValue(Version.Current).build());

    private final Setting<Integer> protocolVersionSetting = sg.add(new IntSetting.Builder().name("protocol").description("The protocol version the servers should have").defaultValue(SharedConstants.getProtocolVersion()).visible(() -> versionSetting.get() == Version.Protocol).min(0).noSlider().build());

    private final Setting<String> versionStringSetting = sg.add(new StringSetting.Builder().name("version-string").description("The version string (e.g. 1.19.3) of the protocol version the server should have, results may contain different versions that have the same protocol version. Must be at least 1.7.1").defaultValue("1.20.2").visible(() -> versionSetting.get() == Version.VersionString).build());

    private final Setting<Boolean> onlineOnlySetting = sg.add(new BoolSetting.Builder().name("online-only").description("Whether to only show servers that are online").defaultValue(true).build());

    private final Setting<Boolean> ignoreModded = sg.add(new BoolSetting.Builder().name("ignore-modded").description("Will not give you servers where mods have been detected").defaultValue(true).build());

    private final Setting<GeoSearchType> geoSearchTypeSetting = sg.add(new EnumSetting.Builder<GeoSearchType>().name("geo-search-type").description("Whether to search by ASN or country code").defaultValue(GeoSearchType.None).build());


    private final Setting<Country> countrySetting = sg.add(new CountrySetting.Builder().name("country").description("The country the server should be located in").defaultValue(ServerScanner.COUNTRY_MAP.get("UN")).visible(() -> geoSearchTypeSetting.get() == GeoSearchType.Country).build());

    private final Setting<Integer> limitSetting = sg.add(new IntSetting.Builder().name("limit").description("Number of results to return").defaultValue(100).min(0).max(100).build());


    MultiplayerScreen multiplayerScreen;


    public FindNewServersScreen(MultiplayerScreen multiplayerScreen) {
        super(GuiThemes.get(), "Find new servers");
        this.multiplayerScreen = multiplayerScreen;
    }

    @Override
    public void initWidgets() {
        loadSettings();
        onClosed(this::saveSettings);
        settingsContainer = add(theme.verticalList()).widget();
        settingsContainer.add(theme.settings(settings));
        findButton = add(theme.button("Find")).expandX().widget();
        findButton.action = () -> {
            ServersRequest request = new ServersRequest();

            switch (onlinePlayersNumTypeSetting.get()) {
                // [n, "inf"]
                case AtLeast -> request.setOnlinePlayers(atLeastOnlinePlayersSetting.get(), -1);

                // [0, n]
                case AtMost -> request.setOnlinePlayers(0, atMostOnlinePlayersSetting.get());

                // [min, max]
                case Between ->
                    request.setOnlinePlayers(atLeastOnlinePlayersSetting.get(), atMostOnlinePlayersSetting.get());

                // [n, n]
                case Equals -> request.setOnlinePlayers(equalsOnlinePlayersSetting.get());
            }

            switch (maxPlayersNumTypeSetting.get()) {
                // [n, "inf"]
                case AtLeast -> request.setMaxPlayers(atLeastMaxPlayersSetting.get(), -1);

                // [0, n]
                case AtMost -> request.setMaxPlayers(0, atMostMaxPlayersSetting.get());

                // [min, max]
                case Between -> request.setMaxPlayers(atLeastMaxPlayersSetting.get(), atMostMaxPlayersSetting.get());

                // [n, n]
                case Equals -> request.setMaxPlayers(equalsMaxPlayersSetting.get());
            }


            if (!countrySetting.get().name.equalsIgnoreCase("any")) {
                request.setCountryCode(countrySetting.get().code);
            }

            request.setCracked(crackedSetting.get().toBoolOrNull());
            request.setDescription(descriptionSetting.get());
            if (softwareSetting.get() != ServersRequest.Software.Any) {
                request.setSoftware(softwareSetting.get());
            }

            switch (versionSetting.get()) {
                case Protocol -> request.setProtocolVersion(protocolVersionSetting.get());
                case VersionString -> {
//                   Integer protocol = MCVersionUtil.versionToProtocol(versionStringSetting.get());
//                   if (protocol == null) {
//                       clear();
//                       add(theme.label("Unknown version string"));
//                       return;
//                   }
//                   request.setProtocolVersion(protocol);
                    String versionString = versionStringSetting.get();
                    if (versionString.equals(Version.Any.name())) return;
                    request.setVersionName(versionString);
                }
                case Current -> request.setProtocolVersion(SharedConstants.getProtocolVersion());
            }

            if (onlineOnlySetting.get()) {
                long oneHourAgo = Instant.now().minus(Duration.ofHours(1)).getEpochSecond();

                request.setOnlineAfter((int) oneHourAgo);
            }

            if (ignoreModded.get()) request.setIgnoreModded(true);
//            if (onlyBungeeSpoofable.get()) request.setOnlyBungeeSpoofable(true);


            this.locked = true;

            this.threadHasFinished = false;
            this.threadError = null;
            this.threadServers = null;


            MeteorExecutor.execute(() -> {
                String requestJson = request.json();
                String encoded = URLEncoder.encode(requestJson, StandardCharsets.UTF_8);
                String url = String.format("https://api.cornbread2100.com/servers?limit=%s&query=%s", limitSetting.get(), encoded);
                System.out.println(String.format("Requesting URL: %s", url));
                String jsonResp = SmallHttp.get(url);

                Type listType = new TypeToken<ArrayList<ServersResponse.Server>>() {
                }.getType();
                List<ServersResponse.Server> resp = gson.fromJson(jsonResp, listType);

//                // Set error message if there is one
//                if (resp.isError()) {
//                    this.threadError = resp.error;
//                    this.threadHasFinished = true;
//                    return;
//                }
                this.threadServers = resp;
                this.threadHasFinished = true;
            });
        };

        add(theme.button("Reset all")).expandX().widget().action = this::resetSettings;
    }

    @Override
    public void tick() {
        super.tick();
        settings.tick(settingsContainer, theme);

        if (threadHasFinished) handleThreadFinish();

        if (locked) {
            if (timer > 2) {
                findButton.set(getNext(findButton));
                timer = 0;
            } else {
                timer++;
            }
        } else if (!findButton.getText().equals("Find")) {
            findButton.set("Find");
        }
    }

    @Override
    protected void onClosed() {
        ServerScanner.COUNTRY_MAP.values().forEach(Country::dispose);
    }

    private String getNext(WButton add) {
        return switch (add.getText()) {
            case "Find", "oo0" -> "ooo";
            case "ooo" -> "0oo";
            case "0oo" -> "o0o";
            case "o0o" -> "oo0";
            default -> "Find";
        };
    }

    private void handleThreadFinish() {
        this.threadHasFinished = false;
        this.locked = false;
        if (this.threadError != null) {
            clear();
            add(theme.label(this.threadError)).expandX();
            WButton backButton = add(theme.button("Back")).expandX().widget();
            backButton.action = this::reload;
            this.locked = false;
            return;
        }
        clear();
        List<ServersResponse.Server> servers = this.threadServers;

        if (servers.isEmpty()) {
            add(theme.label("No servers found")).expandX();
            WButton backButton = add(theme.button("Back")).expandX().widget();
            backButton.action = this::reload;
            this.locked = false;
            return;
        }
        add(theme.label("Found " + servers.size() + " servers")).expandX();
        WButton addAllButton = add(theme.button("Add all")).expandX().widget();
        addAllButton.action = () -> {
            for (ServersResponse.Server server : servers) {
                String ip = server.getServerAddress();

                // Add server to list
                MultiplayerScreenUtil.addNameIpToServerList(multiplayerScreen, "ServerScanner " + ip, ip, false);
            }
            MultiplayerScreenUtil.saveList(multiplayerScreen);

            // Reload widget
            MultiplayerScreenUtil.reloadServerList(multiplayerScreen);

            // Close screen
            if (this.client == null) return;
            client.setScreen(this.multiplayerScreen);
        };

        WTable table = add(theme.table()).widget();

        table.add(theme.label("Server IP"));
        table.add(theme.label("Version"));


        table.row();

        table.add(theme.horizontalSeparator()).expandX();
        table.row();


        for (ServersResponse.Server server : servers) {
            final String serverIP = server.getServerAddress();
            String serverVersion = server.version.name;

            table.add(theme.label(serverIP));
            table.add(theme.label(serverVersion));

            WButton addServerButton = theme.button("Add Server");
            addServerButton.action = () -> {
                System.out.println(multiplayerScreen.getServerList() == null);
                ServerInfo info = new ServerInfo("ServerScanner " + serverIP, serverIP, ServerInfo.ServerType.OTHER);
                MultiplayerScreenUtil.addInfoToServerList(multiplayerScreen, info);
                addServerButton.visible = false;
            };

            WButton joinServerButton = theme.button("Join Server");
            HostAndPort hap = HostAndPort.fromString(serverIP);

            joinServerButton.action = () -> ConnectScreen.connect(new TitleScreen(), MinecraftClient.getInstance(), new ServerAddress(hap.getHost(), hap.getPort()), new ServerInfo("a", hap.toString(), ServerInfo.ServerType.OTHER), false, null);

            WButton serverInfoButton = theme.button("Server Info");
            serverInfoButton.action = () -> this.client.setScreen(new ServerInfoScreen(serverIP));

            table.add(addServerButton);
            table.add(joinServerButton);
            table.add(serverInfoButton);

            table.row();
        }

        this.locked = false;
    }

    public void saveSettings() {
        savedSettings = sg.toTag();
    }

    public void loadSettings() {
        if (savedSettings == null) return;
        sg.fromTag(savedSettings);
    }

    public void resetSettings() {
        for (Setting<?> setting : sg) {
            setting.reset();
        }
        saveSettings();
        reload();
    }
}
