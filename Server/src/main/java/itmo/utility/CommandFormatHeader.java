package itmo.utility;

import itmo.commands.Command;

/**
 * helps to generate header of commands
 */
public class CommandFormatHeader {

    private final int numberOfChars;
    private final Command command;

    /**
     * @param numberOfChars the length of header
     * @param command       command that needs a header
     */
    public CommandFormatHeader(int numberOfChars, Command command) {
        this.numberOfChars = numberOfChars;
        this.command = command;
    }

    /**
     * @return delimiter of elements
     */
    public String getElementDelimiter() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfChars; ++i)
            stringBuilder.append('-');

        return stringBuilder.toString();
    }

    /**
     * @return header
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        String title = command.getClass().getSimpleName();

        for (int i = 0; i < (numberOfChars - title.length()) / 2; ++i)
            stringBuilder.append('=');
        String beforeTitle = stringBuilder.toString();

        stringBuilder = new StringBuilder();

        for (int i = 0; i < (numberOfChars - title.length()) / 2 + (numberOfChars - title.length()) % 2; ++i)
            stringBuilder.append('=');
        String afterTitle = stringBuilder.toString();

        return beforeTitle + title + afterTitle;
    }
}
