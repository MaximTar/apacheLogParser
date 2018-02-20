package parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by maxtar on 2/10/18.
 */
class Splitter {

    private Splitter() {
    }

    static List<String> split(String oneString, List<Character> delimiters, Map<Integer, String> userParams) {
        List<String> splitted = new ArrayList<>();
        String addedStr = null;
        System.out.println(userParams);
        for (int i = 0; i < delimiters.size(); i++) {
            String delimiter = delimiters.get(i).toString();
            String userParam = userParams.get(i);

            if (addedStr != null) {
                oneString = oneString.substring(addedStr.length() + 1);
            }
            if (userParam != null && oneString.contains(userParam)) {
                oneString = oneString.substring(userParam.length());
            }

            String[] parts = oneString.split(delimiter);
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
            if (!helper.isEmpty()) {
                addedStr = helper.get(0);
                splitted.add(addedStr);
                if (i == delimiters.size() - 1) {
                    String lastUserParam = userParams.get(i + 1);
                    if (lastUserParam != null && helper.get(1).contains(lastUserParam)) {
                        splitted.add(helper.get(1).substring(lastUserParam.length()));
                    } else {
                        splitted.add(helper.get(1));
                    }
                }
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
                    } else {
                        posOfChars.remove(posOfChars.size() - 1);
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
