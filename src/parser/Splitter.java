package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by maxtar on 2/10/18.
 */
class Splitter {

    private Splitter() {
    }

    // TODO DEAL WITH DELIMITERS
    // Now it works only for equal delimiters (if only one delimiter used in log format)
    static List<String> split(String oneString, List<Character> delimiters) {
        List<String> splitted = new ArrayList<>();
        if (delimiters.stream().distinct().limit(2).count() <= 1) {
            String delimiter = delimiters.get(0).toString();
            String[] parts = oneString.split(delimiter);
//        String[] parts = oneString.split(",");
            Collections.addAll(splitted, parts);
            List<Integer> positions = checkOnCharsIn(splitted, '[', ']');
            if (!positions.isEmpty()) {
                splitted = concatAndRemove(splitted, positions);
            }
            positions = checkOnCharsIn(splitted, '"', '"');
            if (!positions.isEmpty()) {
                splitted = concatAndRemove(splitted, positions);
            }
        }
        return splitted;
    }

    static private List<Integer> checkOnCharsIn(List<String> parts, char symbolOpen, char symbolClose) {
        List<Integer> posOfChars = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).charAt(0) == symbolOpen &&
                    parts.get(i).charAt(parts.get(i).length() - 1) != symbolClose) {
                posOfChars.add(i);
                for (int j = i + 1; j < parts.size(); j++) {
                    if (parts.get(j).charAt(parts.get(j).length() - 1) == symbolClose) {
                        posOfChars.add(j);
                        break;
                    }
                }
            }
        }
        return posOfChars;
    }

    static private List<String> concatAndRemove(List<String> splitted, List<Integer> positions) {
        List<String> newLine = concat(splitted, positions);
        newLine = remove(newLine, positions);
        return newLine;
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
