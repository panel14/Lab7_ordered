package itmo.io;

import itmo.exceptions.ClientRuntimeException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class ClientScan implements Scannable{

    private final DatagramChannel datagramChannel;
    private Selector selector;

    public ClientScan(DatagramChannel datagramChannel) throws IOException {
        this.datagramChannel = datagramChannel;
        selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ, null); // регистрируем канал (для чтения)
    }


    @Override
    public String readLine() throws Exception { //метод сканирует сообщения от сервера

        while (true){
            if (selector.select(5000) == 0){ //проверяет сколько каналов доступно в течение n секунд
                throw new ClientRuntimeException();
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys(); // получаем ключи чтобы по ним пройтись
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator(); // получаем итератор

            while (selectionKeyIterator.hasNext()){ //перебираем ключи, то есть каналы
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove();
                if (key.isReadable()){ //проверяем, что ключ доступен для чтения
                    DatagramChannel datagramChannel = (DatagramChannel) key.channel(); //берем канал по ключу и приводим его к DatagramChannel

                    ByteBuffer byteBuffer = ByteBuffer.allocate(10000); //
                    try {
                        datagramChannel.receive(byteBuffer);
                    } catch (Exception e){
                        throw new ClientRuntimeException();
                    }

                    return new String(byteBuffer.array()).trim(); //

                }
            }
        }


//        ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
//        try {
//            datagramChannel.receive(byteBuffer);
//        } catch (SocketTimeoutException e){
//            throw new ClientRuntimeException();
//        }
//
//        return new String(byteBuffer.array()).trim();
    }

    @Override
    public boolean hasNextLine() {
        return true;
    }

    @Override
    public void close() throws Exception {
    }
}
