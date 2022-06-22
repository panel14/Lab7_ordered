package itmo.io;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class ClientPrint implements Printable{

    private final DatagramChannel datagramChannel;
    private final int port;

    public ClientPrint(DatagramChannel datagramChannel, int port) {
        this.datagramChannel = datagramChannel;
        this.port = port;
    }


    @Override
    public void println(String s) throws IOException { // метод который отправляет строчку на сервер
        byte[] buffer = s.getBytes(StandardCharsets.UTF_8);

        datagramChannel.send(ByteBuffer.wrap(buffer), new InetSocketAddress("localhost", 8181)); // через DatagramChannel передаем байты по такому адресу
    }

    @Override
    public void close(String s) {
    }
}
