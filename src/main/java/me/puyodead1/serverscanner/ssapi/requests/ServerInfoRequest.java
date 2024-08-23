package me.puyodead1.serverscanner.ssapi.requests;

import static me.puyodead1.serverscanner.ServerScanner.gson;

public class ServerInfoRequest {
    private String ip;
    private Integer port;

    public void setIpPort(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String json() {
        return gson.toJson(this);
    }
}
