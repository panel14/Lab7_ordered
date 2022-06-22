package itmo.io;

import java.util.Scanner;

/**
 * class that helps to read from console
 */
public class ConsoleScan implements Scannable {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        if (!this.hasNextLine())
            System.exit(1);
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * method that closes scannable
     */
    @Override
    public void close() {
        scanner.close();
    }
}
