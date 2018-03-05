package main.java.com.github.apachelogparser.parser;

/**
 * Created by maxtar on 02.03.18.
 */
public class SplitterFileException extends Exception {
    private final static String message = "The Wrong String Was Found In File. Line #";
    private final int stringNumber;

    public int getStringNumber() {
        return stringNumber;
    }

    SplitterFileException(int stringNumber) {
        super(message);
        this.stringNumber = stringNumber;
    }
}
