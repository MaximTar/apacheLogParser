package main.java.com.github.apachelogparser.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by maxtar on 2/10/18.
 */
public final class Splitter {
    private String line;

    Splitter(String line) {
        this.line = line;
    }

    public List<String> split(List<Character> delimiters, Map<Integer, String> userParameters, int lineNumber) throws SplitterFileException {
        List<String> splitted = new ArrayList<>();
        String added = null;
        for (int i = 0; i < delimiters.size(); i++) {
            Character delimiter = delimiters.get(i);
            String userParameter = userParameters.get(i);

            if (added != null) {
                try {
                    line = line.substring(added.length() + 1);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new SplitterFileException(lineNumber);
                }
            }
            if (userParameter != null && line.contains(userParameter)) {
                line = line.substring(userParameter.length());
            }

            String[] parts = line.split(String.valueOf(delimiter));

            List<Integer> positions = checkOnCharsIn(parts, '[', ']');
            if (!positions.isEmpty()) {
                parts = concatAndRemove(parts, positions);
            }
            positions = checkOnCharsIn(parts, '"', '"');
            if (!positions.isEmpty()) {
                parts = concatAndRemove(parts, positions);
            }
            positions = checkOnCharsIn(parts, '\'', '\'');
            if (!positions.isEmpty()) {
                parts = concatAndRemove(parts, positions);
            }

            if (parts.length > 0) {
                added = parts[0];
                splitted.add(added);
                if (i == delimiters.size() - 1) {
                    String lastUserParameter = userParameters.get(i + 1);
                    if (lastUserParameter != null && parts[1].contains(lastUserParameter)) {
                        splitted.add(parts[1].substring(lastUserParameter.length()));
                    } else {
                        splitted.add(parts[1]);
                    }
                }
            }
        }
        return splitted;
    }

    private static List<Integer> checkOnCharsIn(String[] parts, char symbolOpen, char symbolClose) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].charAt(0) == symbolOpen &&
                    parts[i].charAt(parts[i].length() - 1) != symbolClose) {
                positions.add(i);
                for (int j = i + 1; j < parts.length; j++) {
                    if (parts[j].charAt(parts[j].length() - 1) == symbolClose) {
                        positions.add(j);
                        break;
                    } else if (j == parts.length - 1) {
                        positions.remove(positions.size() - 1);
                    }
                }
            }
        }
        return positions;
    }

    private static String[] concatAndRemove(String[] parts, List<Integer> positions) {
        List<String> joined = new ArrayList<>();
        Collections.addAll(joined, parts);
        for (int k = 0; k < positions.size(); k += 2) {
            for (int i = positions.get(k) + 1; i <= positions.get(k + 1); i++) {
                joined.set(positions.get(k), joined.get(positions.get(k)) + " " + joined.get(i));
            }
        }
        for (int k = positions.size() - 1; k > 0; k -= 2) {
            for (int i = positions.get(k); i > positions.get(k - 1); i--) {
                joined.remove(i);
            }
        }
        return joined.toArray(new String[0]);
    }
}
