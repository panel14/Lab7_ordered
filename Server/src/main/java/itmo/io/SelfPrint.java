package itmo.io;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SelfPrint implements Printable{

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void println(String s) throws UnknownHostException, SocketException, IOException {
        stringBuilder.append(s).append("\n");
    }

    @Override
    public void close() {

    }

    public String getString(){
        return stringBuilder.toString();
    }
}
