package itmo;

import itmo.io.*;
import itmo.servers.ReadingThread;
import itmo.servers.ThreadInfo;

import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final ExecutorService readingThread = Executors.newFixedThreadPool(8);
    private static final ExecutorService writingThread = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws Exception {

        DatagramSocket datagramSocket = new DatagramSocket(8181);
        ServerScan serverScan = new ServerScan(datagramSocket);
        ServerPrint serverPrint = new ServerPrint(datagramSocket, 0);

        ServerIO serverIO = new ServerIO(serverPrint, serverScan, datagramSocket);

        while (true) {
            String request = serverIO.readLine();

            ThreadInfo info = new ThreadInfo(serverIO, request);
            readingThread.execute(new ReadingThread(info, writingThread));
        }
    }
}