package itmo.io;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerIO implements Scannable, Printable{
    private ServerPrint serverPrint;
    private ServerScan serverScan;
    private DatagramSocket datagramSocket;

    public ServerIO(ServerPrint serverPrint, ServerScan serverScan, DatagramSocket datagramSocket) {
        this.serverPrint = serverPrint;
        this.serverScan = serverScan;
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void println(String s) throws UnknownHostException, SocketException, IOException {
        serverPrint.println(s);
    }

    @Override
    public String readLine() throws Exception {
        String s = serverScan.readLine();
        serverPrint = new ServerPrint(datagramSocket, serverScan.getPort());
        return s;
    }

    @Override
    public boolean hasNextLine() {
        return true;
    }

    @Override
    public void close() {
        datagramSocket.close();
    }
}
