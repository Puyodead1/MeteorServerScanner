package me.puyodead1.serverscanner.ssapi.requests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import static me.puyodead1.serverscanner.ServerScanner.gson;

public class ServersRequest {
    private final JsonObject params = new JsonObject();

    public enum Software {
        Any, Bukkit, Spigot, Paper, Vanilla, Forge, Fabric
    }

    private Software software;

    private void addOrFilter(JsonObject object) {
        JsonArray filters;

        // check for an or
        if (params.has("$or")) {
            // get the existing or
            filters = params.get("$or").getAsJsonArray();
            System.out.println("Found existing $or");
        } else {
            // create new
            filters = new JsonArray();
            System.out.println("Creating new $or");
        }

        filters.add(object);

        this.params.add("$or", filters);
    }

    private void addAndFilter(JsonObject object) {
        JsonArray filters;

        // check for an or
        if (params.has("$and")) {
            // get the existing and
            filters = params.get("$and").getAsJsonArray();
            System.out.println("Found existing $and");
        } else {
            // create new
            filters = new JsonArray();
            System.out.println("Creating new and");
        }

        filters.add(object);

        this.params.add("$and", filters);
    }

    public void setCountryCode(String cc) {
        this.params.addProperty("geo.country", cc.toUpperCase());
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

        JsonObject regexFilter = new JsonObject();
        regexFilter.addProperty("$regex", description);
        regexFilter.addProperty("$options", "i");

        JsonObject filter1 = new JsonObject();
        filter1.add("description", regexFilter);

        JsonObject filter2 = new JsonObject();
        filter2.add("description.text", regexFilter);

        JsonObject filter3 = new JsonObject();
        filter3.add("description.extra.text", regexFilter);

        addOrFilter(filter1);
        addOrFilter(filter2);
        addOrFilter(filter3);
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
        // {"version.name": regexFilter}
        JsonObject filter = new JsonObject();

        JsonObject regexFilter = new JsonObject();
        regexFilter.addProperty("$regex", versionName);
        regexFilter.addProperty("$options", "i");

        // add the regex filter object to the filter object
        filter.add("version.name", regexFilter);

        // append the filter
        addAndFilter(filter);
    }

    // ex: paper 1.21.1
    public void setSoftware(Software software) {
        // {"version.name": regexFilter}
        JsonObject filter = new JsonObject();

        JsonObject regexFilter = new JsonObject();
        // not sure if this is the best idea or not, but most if not all but vanilla should work here

        regexFilter.addProperty("$regex", String.format("^%s", software.name()));
        regexFilter.addProperty("$options", "i");

        // add the regex filter object to the filter object
        filter.add("version.name", regexFilter);

        // append the filter
        addAndFilter(filter);
    }

    public void setIgnoreModded(Boolean ignore) {
        // ignore forgedata
        JsonObject notExistFilter = new JsonObject();
        notExistFilter.addProperty("$exists", false);

        JsonObject filter1 = new JsonObject();
        filter1.addProperty("hasForgeData", false);

        JsonObject filter2 = new JsonObject();
        filter2.add("hasForgeData", notExistFilter);

        // do it again for modinfo
        JsonObject notExistFilter2 = new JsonObject();
        notExistFilter2.addProperty("$exists", false);

        JsonObject filter3 = new JsonObject();
        filter3.add("modinfo", notExistFilter);

        addOrFilter(filter1);
        addOrFilter(filter2);
        addOrFilter(filter3);
    }

    public void setWhitelist(Boolean whitelist) {
        this.params.addProperty("whitelist", whitelist);
    }


    public String json() {
        return gson.toJson(params);
    }
}
