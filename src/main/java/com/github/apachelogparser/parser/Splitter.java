package main.java.com.github.apachelogparser.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by maxtar on 2/10/18.
 */
public class Splitter {

    private Splitter() {
    }

    // TODO MAKE NON STATIC
    static List<String> split(String line, List<Character> delimiters, Map<Integer, String> userParameters, int lineNumber) throws SplitterFileException {
        List<String> splitted = new ArrayList<>();
        String added = null;
        for (int i = 0; i < delimiters.size(); i++) {
            String delimiter = delimiters.get(i).toString();
            String userParameter = userParameters.get(i);

            if (added != null) {
                try {
                    line = line.substring(added.length() + 1);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new SplitterFileException("The Wrong String Was Found In File. Line #", lineNumber);
                }
            }
            if (userParameter != null && line.contains(userParameter)) {
                line = line.substring(userParameter.length());
            }

            String[] parts = line.split(delimiter);
            List<String> helper = new ArrayList<>();
            Collections.addAll(helper, parts);

            List<Integer> positions = checkOnCharsIn(helper, '[', ']');
            if (!positions.isEmpty()) {
                helper = concatAndRemove(helper, positions);
            }
            positions = checkOnCharsIn(helper, '"', '"');
            if (!positions.isEmpty()) {
                helper = concatAndRemove(helper, positions);
            }
            positions = checkOnCharsIn(helper, '\'', '\'');
            if (!positions.isEmpty()) {
                helper = concatAndRemove(helper, positions);
            }

            if (!helper.isEmpty()) {
                added = helper.get(0);
                splitted.add(added);
                if (i == delimiters.size() - 1) {
                    String lastUserParameter = userParameters.get(i + 1);
                    if (lastUserParameter != null && helper.get(1).contains(lastUserParameter)) {
                        splitted.add(helper.get(1).substring(lastUserParameter.length()));
                    } else {
                        splitted.add(helper.get(1));
                    }
                }
            }
        }
        return splitted;
    }

    static private List<Integer> checkOnCharsIn(List<String> parts, char symbolOpen, char symbolClose) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).charAt(0) == symbolOpen &&
                    parts.get(i).charAt(parts.get(i).length() - 1) != symbolClose) {
                positions.add(i);
                for (int j = i + 1; j < parts.size(); j++) {
                    if (parts.get(j).charAt(parts.get(j).length() - 1) == symbolClose) {
                        positions.add(j);
                        break;
                    } else if (j == parts.size() - 1) {
                        positions.remove(positions.size() - 1);
                    }
                }
            }
        }
        return positions;
    }

    static private List<String> concatAndRemove(List<String> splitted, List<Integer> positions) {
        List<String> joined = concat(splitted, positions);
        joined = remove(joined, positions);
        return joined;
    }

    static private List<String> concat(List<String> splitted, List<Integer> positions) {
        for (int k = 0; k < positions.size(); k += 2) {
            for (int i = positions.get(k) + 1; i <= positions.get(k + 1); i++) {
                splitted.set(positions.get(k), splitted.get(positions.get(k)) + " " + splitted.get(i));
            }
        }
        return splitted;
    }

    static private List<String> remove(List<String> splitted, List<Integer> positions) {
        for (int k = positions.size() - 1; k > 0; k -= 2) {
            for (int i = positions.get(k); i > positions.get(k - 1); i--) {
                splitted.remove(i);
            }
        }
        return splitted;
    }
}
