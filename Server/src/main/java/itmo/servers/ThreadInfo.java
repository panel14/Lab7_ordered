package itmo.servers;

import itmo.io.ServerIO;

public class ThreadInfo {
    public ServerIO serverIO;
    public String request;

    public ThreadInfo(ServerIO serverIO, String request) {
        this.serverIO = serverIO;
        this.request = request;
    }
}
