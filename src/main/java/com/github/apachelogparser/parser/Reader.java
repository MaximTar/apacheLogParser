package main.java.com.github.apachelogparser.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by maxtar on 2/10/18.
 */
public class Reader {

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
                    logData.add(Splitter.split(line, delimiters, userParams, lineNumber));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return logFormats;
    }
}
