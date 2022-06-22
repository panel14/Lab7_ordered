package itmo.io;

import java.util.Scanner;

public class StringScan implements Scannable{

    private String s;
    private Scanner scanner;

    public StringScan(String s) {
        this.s = s;
        this.scanner = new Scanner(s);
    }

    @Override
    public String readLine() throws Exception {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
