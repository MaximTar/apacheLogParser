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

    private static List<Integer> checkOnCharsIn(List<String> parts, char symbolOpen, char symbolClose) {
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

    private static List<String> concatAndRemove(List<String> splitted, List<Integer> positions) {
        List<String> joined = new ArrayList<>(splitted);
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
        return joined;
    }
}
