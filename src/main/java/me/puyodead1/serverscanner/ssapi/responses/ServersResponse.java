package me.puyodead1.serverscanner.ssapi.responses;

import java.util.List;

public class ServersResponse {
    public record Description(String text) {
    }

    public static class PlayerSample {
        public String id;
        public String name;
        public Integer lastSeen;
    }

    public static class Players {
        public Integer max;
        public Integer online;
        public List<PlayerSample> sample;
        // dont care about history
    }

    public static class Version {
        public String name;
        public Integer protocol;
    }

    public static class Mod {
        public String modid;
        public String version;
    }

    public static class ModInfo {
        public String type; // ex: FML
        public List<Mod> modlist;
    }

    public static class Geo {
        public String country;
        public String city;
        public Float lat;
        public Float lon;
    }

    public static class Server {
        public String ip;
        public Integer port;
        public Description description;
        public Boolean enforcesSecureChat;
        public Boolean hasFavicon;
        public Boolean hasForgeData;
        public Integer lastSeen;
        public Players players;
        public Version version;
        public Boolean cracked;
        public Boolean preventsChatReports;
        public Boolean previewsChat;
        public Geo geo;
        public String org;
        public ModInfo modinfo;

        public String getServerAddress() {
            return String.format("%s:%d", this.ip, this.port);
        }
    }
}
