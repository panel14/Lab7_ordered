package itmo.io;

public class ConsolePrint implements Printable {
    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public void close(String s) {
    }
}
