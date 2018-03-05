package main.java.com.github.apachelogparser.parser;

import javafx.scene.control.Alert;
import main.java.com.github.apachelogparser.controller.AlertHandler;
import main.java.com.github.apachelogparser.controller.FirstViewController;
import main.java.com.github.apachelogparser.controller.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by maxtar on 2/10/18.
 */
public final class Reader {

    private final static Logger LOGGER = Logger.getLogger(FirstViewController.class.getName());
    static {
        LOGGER.setUseParentHandlers(false);
        FileHandler fh = Main.getFileHandler();
        LOGGER.addHandler(fh);
    }

    private Reader() {
    }

    public static List<List<String>> readFile(String path, List<Character> delimiters, Map<Integer, String> userParams) throws SplitterFileException {
        List<List<String>> logData = new ArrayList<>();
        try (FileReader fr = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.length() > 0) {
                    logData.add(new Splitter(line).split(delimiters, userParams, lineNumber));
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "IOException while reading log file. StackTrace: "
                    + Arrays.toString(e.getStackTrace()));
            new AlertHandler(Alert.AlertType.ERROR, "Program could not read log file.\n" +
                    "Grant the rights to the program so that it can access the filesystem.");
        }
        return logData;
    }

    public static List<String> readLogFormats(String path) {
        List<String> logFormats = new ArrayList<>();
        try (FileReader fr = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                logFormats.add(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "IOException while reading log formats file. StackTrace: "
                    + Arrays.toString(e.getStackTrace()));
            new AlertHandler(Alert.AlertType.ERROR,
                    "Program could not read file that contains saved log formats.\n" +
                    "Grant the rights to the program so that it can access the filesystem.");
        }

        return logFormats;
    }
}
