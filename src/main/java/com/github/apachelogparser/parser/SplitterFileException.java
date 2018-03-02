package main.java.com.github.apachelogparser.parser;

/**
 * Created by maxtar on 02.03.18.
 */
public class SplitterFileException extends Exception {
    private int stringNumber;

    public int getStringNumber() {
        return stringNumber;
    }

    SplitterFileException(String message, int stringNumber) {
        super(message);
        this.stringNumber = stringNumber;
    }
}
