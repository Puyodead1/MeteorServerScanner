package me.puyodead1.serverscanner.ssapi.requests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import static me.puyodead1.serverscanner.ServerScanner.gson;

public class ServersRequest {
    private final JsonObject params = new JsonObject();

    public enum Software {
        Any, Bukkit, Spigot, Paper, Vanilla
    }

    private Software software;

    public void setCountryCode(String cc) {
        this.params.addProperty("geo.country", cc);
    }

    public void setOrg(String org) {
        JsonObject filter = new JsonObject();
        filter.addProperty("$regex", org);
        filter.addProperty("$options", "i");

        this.params.add("geo.org", filter);
    }

    public void setCracked(Boolean cracked) {
        this.params.addProperty("cracked", cracked);
    }

    public void setDescription(String description) {
        if (description.isBlank() || description.isEmpty()) return;
        JsonArray filterArray = new JsonArray();

        JsonObject regexFilter = new JsonObject();
        regexFilter.addProperty("$regex", description);
        regexFilter.addProperty("$options", "i");

        JsonObject filter1 = new JsonObject();
        filter1.add("description", regexFilter);

        JsonObject filter2 = new JsonObject();
        filter2.add("description.text", regexFilter);

        JsonObject filter3 = new JsonObject();
        filter3.add("description.extra.text", regexFilter);

        filterArray.add(filter1);
        filterArray.add(filter2);
        filterArray.add(filter3);

        this.params.add("$or", filterArray);
    }

    public void setMaxPlayers(Integer exact) {
        this.params.addProperty("players.max", exact);
    }

    public void setMaxPlayers(Integer min, Integer max) {
        JsonObject filter = new JsonObject();
        filter.addProperty("$gte", min);

        if (max >= 0) {
            filter.addProperty("$lte", max);
        }

        this.params.add("players.max", filter);
    }

    public void setOnlineAfter(Integer unix_timestamp) {
        JsonObject filter = new JsonObject();
        filter.addProperty("$gte", unix_timestamp);

        this.params.add("lastSeen", filter);
    }

    public void setOnlinePlayers(Integer exact) {
        this.params.addProperty("players.online", exact);
    }

    public void setOnlinePlayers(Integer min, Integer max) {
        JsonObject filter = new JsonObject();
        filter.addProperty("$gte", min);

        if (max >= 0) {
            filter.addProperty("$lte", max);
        }

        this.params.add("players.online", filter);
    }

    public void setProtocolVersion(Integer version) {
        this.params.addProperty("version.protocol", version);
    }

    // ex: paper 1.21.1
    public void setVersionName(String versionName) {
        JsonObject filter = new JsonObject();
        filter.addProperty("$regex", versionName);
        filter.addProperty("$options", "i");

        this.params.add("version.name", filter);
    }

    // ex: paper 1.21.1
    public void setSoftware(Software software) {
        JsonObject filter = new JsonObject();
        filter.addProperty("$regex", software.name());
        filter.addProperty("$options", "i");

        this.params.add("version.name", filter);
    }

    public void setIgnoreModded(Boolean ignore) {
        JsonArray filter;

        // check for an or
        if (params.has("$or")) {
            // get the existing or
            filter = params.get("$or").getAsJsonArray();

            // remove the old field so we can re-add it later
            this.params.remove("$or");
        } else {
            // create new
            filter = new JsonArray();
        }

        JsonObject notExistFilter = new JsonObject();
        notExistFilter.addProperty("$exists", false);

        JsonObject filter1 = new JsonObject();
        filter1.addProperty("hasForgeData", false);

        JsonObject filter2 = new JsonObject();
        filter2.add("hasForgeData", notExistFilter);

        filter.add(filter1);
        filter.add(filter2);


        this.params.add("$or", filter);
    }


    public String json() {
        return gson.toJson(params);
    }
}
