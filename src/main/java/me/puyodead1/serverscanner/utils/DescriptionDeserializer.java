package me.puyodead1.serverscanner.utils;

import com.google.gson.*;
import me.puyodead1.serverscanner.ssapi.responses.ServersResponse;

import java.lang.reflect.Type;

// fucking annoying, thanks chatgpt

public class DescriptionDeserializer implements JsonDeserializer<ServersResponse.Description> {
    @Override
    public ServersResponse.Description deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return new ServersResponse.Description(json.getAsString());
        } else if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            if (jsonObject.has("text") && !jsonObject.get("text").getAsString().isEmpty()) {
                return new ServersResponse.Description(jsonObject.get("text").getAsString());
            } else if (jsonObject.has("extra")) {
                JsonArray extraArray = jsonObject.getAsJsonArray("extra");
                if (extraArray != null && !extraArray.isEmpty()) {
                    JsonElement firstElement = extraArray.get(0);
                    if (firstElement.isJsonPrimitive() && firstElement.getAsJsonPrimitive().isString()) {
                        return new ServersResponse.Description(firstElement.getAsString());
                    }
                }
            }
        }
        return null;
    }
}
