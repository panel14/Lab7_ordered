package itmo.io;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public interface Printable {
    void println(String s) throws UnknownHostException, SocketException, IOException;
    void close(String s);
}
