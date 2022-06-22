package itmo.io;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerScan implements Scannable{

    private final DatagramSocket datagramSocket;
    private int port = -1;

    public ServerScan(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public String readLine() throws Exception {
        byte[] buffer = new byte[10000];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
        datagramSocket.receive(datagramPacket);
        port = datagramPacket.getPort();

        return new String(buffer).trim();
    }

    public int getPort(){
        return port;
    }

    @Override
    public boolean hasNextLine() {
        return datagramSocket.isConnected();
    }

    @Override
    public void close() throws Exception {
        datagramSocket.close();
    }
}
