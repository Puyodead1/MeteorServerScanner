package me.puyodead1.serverscanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import me.puyodead1.serverscanner.country.Countries;
import me.puyodead1.serverscanner.country.Country;
import me.puyodead1.serverscanner.country.CountrySetting;
import me.puyodead1.serverscanner.hud.HistoricPlayersHud;
import me.puyodead1.serverscanner.modules.BungeeSpoofModule;
import me.puyodead1.serverscanner.ssapi.responses.ServersResponse;
import me.puyodead1.serverscanner.utils.DescriptionDeserializer;
import me.puyodead1.serverscanner.utils.HistoricPlayersUpdater;
import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.gui.utils.SettingsWidgetFactory;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;
import org.slf4j.Logger;

import java.util.Map;

public class ServerScanner extends MeteorAddon {
    /*
    Feature list for anticope.pages.dev:
    (creates features matching the RegEx '(?:add\(new )([^(]+)(?:\([^)]*)\)\)', as anticope checks for that.
    add(new Find servers with many parameters, for example: Cracked, Description, Player count, much more...())
    add(new Server database with around 1.000.000 servers!())
    add(new Over 80.000.000 players tracked!())
    add(new Search for ANY server you want!())
    add(new Join misconfigured BungeeCord backends with any name you want!())
     */
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("ServerScanner", Items.SPYGLASS.getDefaultStack());
    public static final Map<String, Country> COUNTRY_MAP = new Object2ReferenceOpenHashMap<>();

    public static final Gson gson = new GsonBuilder().registerTypeAdapter(ServersResponse.Description.class, new DescriptionDeserializer()).create();

    @Override
    public void onInitialize() {
        LOG.info("Loaded the ServerScanner addon!");

        // Load countries
        Countries.init();

        Modules.get().add(new BungeeSpoofModule());
        Hud.get().register(HistoricPlayersHud.INFO);

        SettingsWidgetFactory.registerCustomFactory(CountrySetting.class, (theme) -> (table, setting) -> {
            CountrySetting.countrySettingW(table, (CountrySetting) setting, theme);
        });

        MeteorClient.EVENT_BUS.subscribe(HistoricPlayersUpdater.class);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "me.puyodead1.serverscanner";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("Puyodead1", "MeteorServerScanner");
    }

    @Override
    public String getCommit() {
        String commit = FabricLoader.getInstance().getModContainer("serverscanner").get().getMetadata().getCustomValue("github:sha").getAsString();
        return commit.isEmpty() ? null : commit.trim();
    }
}
