package itmo.servers;

import itmo.io.ServerIO;

/**
 * class keeps all need data to all threads
 */
public class ThreadInfo {
    public ServerIO serverIO;
    public String request;

    public ThreadInfo(ServerIO serverIO, String request) {
        this.serverIO = serverIO;
        this.request = request;
    }
}
