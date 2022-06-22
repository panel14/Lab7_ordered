package itmo.io;

import java.io.FileReader;

/**
 * class helps to read from file
 */
public class FileScan implements Scannable {

    private FileReader fileReader;
    private boolean eof = false;

    public FileScan(String filename) throws Exception {
        fileReader = new FileReader(filename);

        if (fileReader.read() == -1)
            eof = true;

        fileReader = new FileReader(filename);
    }

    @Override
    public String readLine() throws Exception {
        if (!hasNextLine())
            return null;

        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            int intChar = fileReader.read();

            if (intChar == -1) {
                eof = true;
                break;
            }

            char ch = (char) intChar;

            if (ch == '\n' || ch == '\r') {
                break;
            }

            stringBuilder.append(ch);
        }

        return stringBuilder.toString();
    }

    /**
     * this method checks if it is possible to read next line
     *
     * @return boolean
     */
    @Override
    public boolean hasNextLine() {
        return !eof;
    }

    @Override
    public void close() throws Exception {
        fileReader.close();
    }
}
