package me.puyodead1.serverscanner.gui;

import com.google.gson.reflect.TypeToken;
import me.puyodead1.serverscanner.SmallHttp;
import me.puyodead1.serverscanner.ssapi.requests.ServerInfoRequest;
import me.puyodead1.serverscanner.ssapi.responses.ServersResponse;
import meteordevelopment.meteorclient.gui.GuiThemes;
import meteordevelopment.meteorclient.gui.WindowScreen;
import meteordevelopment.meteorclient.gui.widgets.containers.WTable;
import meteordevelopment.meteorclient.gui.widgets.pressable.WButton;
import meteordevelopment.meteorclient.systems.accounts.Account;
import meteordevelopment.meteorclient.systems.accounts.Accounts;
import meteordevelopment.meteorclient.systems.accounts.types.CrackedAccount;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
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
import static meteordevelopment.meteorclient.MeteorClient.mc;

public class GetInfoScreen extends WindowScreen {
    MultiplayerServerListWidget.Entry entry;

    public GetInfoScreen(MultiplayerScreen multiplayerScreen, MultiplayerServerListWidget.Entry entry) {
        super(GuiThemes.get(), "Get players");
        this.parent = multiplayerScreen;
        this.entry = entry;
    }

    @Override
    public void initWidgets() {
        if (entry == null) {
            add(theme.label("No server selected"));
            return;
        }

        // Get info about the server
        if (!(entry instanceof MultiplayerServerListWidget.ServerEntry)) {
            add(theme.label("No server selected"));
            return;
        }
        ServerInfo serverInfo = ((MultiplayerServerListWidget.ServerEntry) entry).getServer();
        String address = serverInfo.address;

        // Check if the server matches the regex for ip(:port)
        if (!address.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}(?::[0-9]{1,5})?$")) {
            add(theme.label("You can only get player info for servers with an IP address"));
            return;
        }
        String ip = address.split(":")[0];
        int port = address.split(":").length > 1 ? Integer.parseInt(address.split(":")[1]) : 25565;


        ServerInfoRequest request = new ServerInfoRequest();

        request.setIpPort(ip, port);

        String params = request.json();
        String encoded = URLEncoder.encode(params, StandardCharsets.UTF_8);
        String jsonResp = SmallHttp.get("https://api.cornbread2100.com/servers?limit=1&query=" + encoded);

        Type listType = new TypeToken<ArrayList<ServersResponse.Server>>() {
        }.getType();

        List<ServersResponse.Server> resp = gson.fromJson(jsonResp, listType);

        // Set error message if there is one
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

        List<ServersResponse.PlayerSample> players = Optional.ofNullable(server.players).map(a -> a.sample).orElseGet(ArrayList::new);

        if (players.isEmpty()) {
            clear();
            add(theme.label("No records of players found.")).expandX();
            return;
        }

        /* "players": [ // An array of when which players were seen on the server. Limited to 1000
            {
              "last_seen": 1683790506, // The last time the player was seen on the server (unix timestamp)
              "name": "DAMcraft", // The name of the player
              "uuid": "68af4d98-24a2-41b6-96bc-a9c2ef9b397b" // The uuid of the player
            }, ...
          ] */
        boolean cracked = false;
        if (server.cracked != null) {
            cracked = server.cracked;
        }

        if (!cracked) {
            add(theme.label("Attention: The server is NOT cracked!")).expandX();
            add(theme.label("")).expandX();
        }
        String playersLabel = players.size() == 1 ? " player:" : " players:";
        add(theme.label("Found " + players.size() + playersLabel));

        WTable table = add(theme.table()).widget();

        table.add(theme.label("Name "));
        table.add(theme.label("Last seen "));
        table.add(theme.label("Login (cracked)"));
        table.row();

        table.add(theme.horizontalSeparator()).expandX();
        table.row();

        for (ServersResponse.PlayerSample player : players) {
            String name = player.name;
            long lastSeen = player.lastSeen;
            String lastSeenFormatted = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(Instant.ofEpochSecond(lastSeen).atZone(ZoneId.systemDefault()).toLocalDateTime());

            table.add(theme.label(name + " "));
            table.add(theme.label(lastSeenFormatted + " "));

            if (mc.getSession().getUsername().equals(name)) {
                table.add(theme.label("Logged in")).expandCellX();
            } else {

                WButton loginButton = table.add(theme.button("Login")).widget();
                // Check if the user is currently logged in
                if (mc.getSession().getUsername().equals(name)) {
                    loginButton.visible = false;
                }

                // Log in the user
                loginButton.action = () -> {
                    loginButton.visible = false;
                    if (this.client == null) return;
                    // Check if the account already exists
                    boolean exists = false;
                    for (Account<?> account : Accounts.get()) {
                        if (account instanceof CrackedAccount && account.getUsername().equals(name)) {
                            account.login();
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        CrackedAccount account = new CrackedAccount(name);
                        account.login();
                        Accounts.get().add(account);
                    }
                    close();
                };
            }
            table.row();
        }
    }

//    @Override
//    public void tick() {
//        if (waitingForAuth) {
//            String authToken = ServerSeekerSystem.get().apiKey;
//            if (!authToken.isEmpty()) {
//                this.reload();
//                this.waitingForAuth = false;
//            }
//        }
//    }
}
