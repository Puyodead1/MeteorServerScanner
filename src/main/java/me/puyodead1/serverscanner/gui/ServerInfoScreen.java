package me.puyodead1.serverscanner.gui;

import com.google.common.net.HostAndPort;
import com.google.gson.reflect.TypeToken;
import me.puyodead1.serverscanner.SmallHttp;
import me.puyodead1.serverscanner.ssapi.requests.ServerInfoRequest;
import me.puyodead1.serverscanner.ssapi.responses.ServersResponse;
import meteordevelopment.meteorclient.gui.GuiThemes;
import meteordevelopment.meteorclient.gui.WindowScreen;
import meteordevelopment.meteorclient.gui.widgets.containers.WTable;
import meteordevelopment.meteorclient.gui.widgets.pressable.WButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static me.puyodead1.serverscanner.ServerScanner.gson;

public class ServerInfoScreen extends WindowScreen {
    private final String serverIp;

    public ServerInfoScreen(String serverIp) {
        super(GuiThemes.get(), "Server Info: " + serverIp);
        this.serverIp = serverIp;
    }

    @Override
    public void initWidgets() {
        add(theme.label("Fetching server info..."));
        ServerInfoRequest request = new ServerInfoRequest();
        HostAndPort hap = HostAndPort.fromString(serverIp);
        request.setIpPort(hap.getHost(), hap.getPort());

        String params = request.json();
        String encoded = URLEncoder.encode(params, StandardCharsets.UTF_8);
        String jsonResp = SmallHttp.get("https://api.cornbread2100.com/servers?limit=1&query=" + encoded);

        Type listType = new TypeToken<ArrayList<ServersResponse.Server>>() {
        }.getType();

        List<ServersResponse.Server> resp = gson.fromJson(jsonResp, listType);
//        if (resp.isError()) {
//            clear();
//            add(theme.label(resp.error)).expandX();
//            return;
//        }

        if (resp == null) {
            clear();
            add(theme.label("No response")).expandX();
            return;
        }

        if (resp.isEmpty()) {
            clear();
            add(theme.label("No server records were returned.")).expandX();
            return;
        }

        clear();
        ServersResponse.Server server = resp.getFirst();

        Boolean cracked = server.cracked;
        String description = server.description.text();
        int onlinePlayers = Optional.ofNullable(server.players).map(players -> players.online).orElse(0);
        int maxPlayers = Optional.ofNullable(server.players).map(players -> players.max).orElse(0);
        int protocol = server.version.protocol;
        int lastSeen = server.lastSeen;
        String version = server.version.name;
        List<ServersResponse.PlayerSample> players = Optional.ofNullable(server.players).map(a -> a.sample).orElseGet(ArrayList::new);

        WTable dataTable = add(theme.table()).widget();
        WTable playersTable = add(theme.table()).expandX().widget();

        dataTable.add(theme.label("Cracked: "));
        dataTable.add(theme.label(cracked == null ? "Unknown" : cracked.toString()));
        dataTable.row();

        dataTable.add(theme.label("Description: "));
        if (description.length() > 100) description = description.substring(0, 100) + "...";
        description = description.replace("\n", "\\n");
        description = description.replace("§r", "");
        dataTable.add(theme.label(description));
        dataTable.row();

        dataTable.add(theme.label("Online Players (last scan): "));
        dataTable.add(theme.label(String.valueOf(onlinePlayers)));
        dataTable.row();

        dataTable.add(theme.label("Max Players: "));
        dataTable.add(theme.label(String.valueOf(maxPlayers)));
        dataTable.row();

        dataTable.add(theme.label("Last Seen: "));
        String lastSeenDate = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(Instant.ofEpochSecond(lastSeen).atZone(ZoneId.systemDefault()).toLocalDateTime());
        dataTable.add(theme.label(lastSeenDate));
        dataTable.row();

        dataTable.add(theme.label("Version: "));
        dataTable.add(theme.label(version + " (" + protocol + ")"));

        playersTable.add(theme.label(""));
        playersTable.row();
        playersTable.add(theme.label("Players:"));
        playersTable.row();


        playersTable.add(theme.label("Name ")).expandX();
        playersTable.add(theme.label("Last seen ")).expandX();
        playersTable.row();


        playersTable.add(theme.horizontalSeparator()).expandX();
        playersTable.row();

        for (ServersResponse.PlayerSample player : players) {
            String name = player.name;
            long playerLastSeen = player.lastSeen;
            String lastSeenFormatted = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(Instant.ofEpochSecond(playerLastSeen).atZone(ZoneId.systemDefault()).toLocalDateTime());

            playersTable.add(theme.label(name + " ")).expandX();
            playersTable.add(theme.label(lastSeenFormatted + " ")).expandX();
            playersTable.row();
        }
        WButton joinServerButton = add(theme.button("Join this Server")).expandX().widget();
        joinServerButton.action = () -> ConnectScreen.connect(new TitleScreen(), MinecraftClient.getInstance(), new ServerAddress(hap.getHost(), hap.getPort()), new ServerInfo("a", hap.toString(), ServerInfo.ServerType.OTHER), false, null);
    }
}
