package me.puyodead1.serverscanner.gui;

import me.puyodead1.serverscanner.utils.MultiplayerScreenUtil;
import meteordevelopment.meteorclient.gui.GuiThemes;
import meteordevelopment.meteorclient.gui.WindowScreen;
import meteordevelopment.meteorclient.gui.widgets.containers.WHorizontalList;
import meteordevelopment.meteorclient.gui.widgets.pressable.WButton;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;


public class ServerScannerScreen extends WindowScreen {
    private final MultiplayerScreen multiplayerScreen;

    public ServerScannerScreen(MultiplayerScreen multiplayerScreen) {
        super(GuiThemes.get(), "ServerScanner");
        this.multiplayerScreen = multiplayerScreen;
    }
    @Override
    public void initWidgets() {
        WHorizontalList widgetList = add(theme.horizontalList()).expandX().widget();
        WButton newServersButton = widgetList.add(this.theme.button("Find new servers")).expandX().widget();
//        WButton findPlayersButton = widgetList.add(this.theme.button("Search players")).expandX().widget();
        WButton cleanUpServersButton = widgetList.add(this.theme.button("Clean up")).expandX().widget();
        newServersButton.action = () -> {
            if (this.client == null) return;
            this.client.setScreen(new FindNewServersScreen(this.multiplayerScreen));
        };
//        findPlayersButton.action = () -> {
//            if (this.client == null) return;
//            this.client.setScreen(new FindPlayerScreen(this.multiplayerScreen));
//        };
        cleanUpServersButton.action = () -> {
            if (this.client == null) return;
            clear();
            add(theme.label("Are you sure you want to clean up your server list?"));
            add(theme.label("This will remove all servers that start with \"ServerScanner\""));
            WHorizontalList buttonList = add(theme.horizontalList()).expandX().widget();
            WButton backButton = buttonList.add(theme.button("Back")).expandX().widget();
            backButton.action = this::reload;
            WButton confirmButton = buttonList.add(theme.button("Confirm")).expandX().widget();
            confirmButton.action = this::cleanUpServers;
        };
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

    public void cleanUpServers() {
        if (this.client == null) return;

        for (int i = 0; i < this.multiplayerScreen.getServerList().size(); i++) {
            if (this.multiplayerScreen.getServerList().get(i).name.startsWith("ServerScanner")) {
                this.multiplayerScreen.getServerList().remove(this.multiplayerScreen.getServerList().get(i));
                i--;
            }
        }

        MultiplayerScreenUtil.saveList(multiplayerScreen);
        MultiplayerScreenUtil.reloadServerList(multiplayerScreen);

        client.setScreen(this.multiplayerScreen);
    }
}
