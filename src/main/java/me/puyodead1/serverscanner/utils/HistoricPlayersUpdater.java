package me.puyodead1.serverscanner.utils;

import com.google.gson.reflect.TypeToken;
import me.puyodead1.serverscanner.SmallHttp;
import me.puyodead1.serverscanner.hud.HistoricPlayersHud;
import me.puyodead1.serverscanner.ssapi.requests.ServerInfoRequest;
import me.puyodead1.serverscanner.ssapi.responses.ServersResponse;
import meteordevelopment.meteorclient.events.game.GameJoinedEvent;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.network.ClientPlayNetworkHandler;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static me.puyodead1.serverscanner.ServerScanner.gson;
import static meteordevelopment.meteorclient.MeteorClient.mc;

public class HistoricPlayersUpdater {
    @EventHandler
    private static void onGameJoinEvent(GameJoinedEvent ignoredEvent) {
        // Run in a new thread
        new Thread(HistoricPlayersUpdater::update).start();
    }

    public static void update() {
        // If the Hud contains the HistoricPlayersHud, update the players
        List<HistoricPlayersHud> huds = new ArrayList<>();
        for (HudElement hudElement : Hud.get()) {
            if (hudElement instanceof HistoricPlayersHud && hudElement.isActive()) {
                huds.add((HistoricPlayersHud) hudElement);
            }
        }
        if (huds.isEmpty()) return;

        ClientPlayNetworkHandler networkHandler = mc.getNetworkHandler();
        if (networkHandler == null) return;

        String address = networkHandler.getConnection().getAddress().toString();
        // Split it at "/" and take the second part
        String[] addressParts = address.split("/");
        if (addressParts.length < 2) return;

        String ip = addressParts[1].split(":")[0];
        Integer port = Integer.valueOf(addressParts[1].split(":")[1]);

        ServerInfoRequest request = new ServerInfoRequest();
        request.setIpPort(ip, port);

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
            return;
        }

        if (resp.isEmpty()) {
            return;
        }

        ServersResponse.Server server = resp.getFirst();

        List<ServersResponse.PlayerSample> players = Optional.ofNullable(server.players).map(a -> a.sample).orElseGet(ArrayList::new);

        for (HistoricPlayersHud hud : huds) {
            hud.players = players;
            hud.isCracked = server.cracked != null && server.cracked;
        }
    }
}
