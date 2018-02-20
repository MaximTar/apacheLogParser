package parser;

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

    public static List<List<String>> readFile(String path, List<Character> delimiters, Map<Integer, String> userParams) {
        List<List<String>> logData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                logData.add(Splitter.split(line, delimiters, userParams));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logData;
    }

    public static List<String> readLogFormats(String path) {
        List<String> logFormats = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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
