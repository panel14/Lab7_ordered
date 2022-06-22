package itmo.io;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ServerPrint implements Printable{

    private final DatagramSocket datagramSocket;
    private final int port;

    public ServerPrint(DatagramSocket datagramSocket, int port) {
        this.datagramSocket = datagramSocket;
        this.port = port;
    }


    @Override
    public void println(String s) throws IOException {
        byte[] buffer = s.getBytes(StandardCharsets.UTF_8);
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), port);

        datagramSocket.send(datagramPacket);
    }

    @Override
    public void close() {
    }
}
