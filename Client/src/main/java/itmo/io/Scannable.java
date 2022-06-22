package itmo.io;

/**
 * interface that helps to read from anywhere
 */
public interface Scannable {
    String readLine() throws Exception;

    boolean hasNextLine();

    void close() throws Exception;
}
