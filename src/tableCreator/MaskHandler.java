package tableCreator;

import java.util.*;

/**
 * Created by maxtar on 14.02.18.
 */
public class MaskHandler {

    public static class Parameters {
        private Map<Integer, Character> parameters;
        private Map<Integer, String> additionalParameters;
        private Map<Integer, String> userParameters;
        private List<Character> delimiters;

        Parameters(Map<Integer, Character> params, Map<Integer, String> additionalParams,
                   Map<Integer, String> userParams, List<Character> delimiters) {
            this.parameters = params;
            this.additionalParameters = additionalParams;
            this.userParameters = userParams;
            this.delimiters = delimiters;
        }

        public Map<Integer, Character> getParameters() {
            return parameters;
        }

        public Map<Integer, String> getAdditionalParameters() {
            return additionalParameters;
        }

        public Map<Integer, String> getUserParameters() {
            return userParameters;
        }

        public List<Character> getDelimiters() {
            return delimiters;
        }
    }

    public static boolean verifyMask(String mask) {
        String[] parts = mask.split("%");
        if (parts.length == 1) {
            return false;
        }
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            int leftIndex = part.indexOf('{');
            if (leftIndex >= 0) {
                int rightIndex = part.indexOf('}');
                if (rightIndex >= 0 && rightIndex > leftIndex) {
                    if (!checkChar(part, rightIndex)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (!checkChar(part, 0)) {
                return false;
            }
            // TODO check modifiers
            int modifierLess = part.indexOf('<');
            int modifierMore = part.indexOf('>');
            if (modifierLess >= 0 && modifierMore >= 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkChar(String part, int startChar) {
        for (int k = startChar; k < part.length(); k++) {
            if (Character.isLetter(part.charAt(k))) {
                if (part.length() > k + 1 && Character.isLetter(part.charAt(k + 1))) {
                    return false;
                }
                String allValues = "aAbBCDefhHilmnopPqrstTuUvVXIO";
                return allValues.indexOf(part.charAt(k)) >= 0;
            } else if ((part.charAt(k) == '!' || part.charAt(k) == ',') &&
                    (part.length() <= k + 1 || !Character.isDigit(part.charAt(k + 1)))) {
                return false;
            } else {
                String valuesBetween = "!0123456789,<>";
                if (valuesBetween.indexOf(part.charAt(k)) < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Parameters getMaskParameters(String mask) {

        if ((mask.startsWith("«") && mask.endsWith("»")) || (mask.startsWith("\"") && mask.endsWith("\""))) {
            mask = mask.substring(1, mask.length() - 1);
        }

        String[] parts = mask.split("%");
        Map<Integer, Character> maskParameters = new HashMap<>();
        Map<Integer, String> maskAdditionalParameters = new HashMap<>();
        Map<Integer, String> maskUserParameters = new HashMap<>();
        List<Character> delimiters = new ArrayList<>();
        maskUserParameters.put(0, parts[0]);
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            int j = 0;
            while (j < part.length() && !Character.isLetter(part.charAt(j))) {
                j++;
            }
            if (j < part.length() && Character.isLetter(part.charAt(j))) {
                maskParameters.put(i, part.charAt(j));
                if (i < parts.length - 1) {
                    maskUserParameters.put(i, part.substring(j + 2));
                }
                if (j + 1 < part.length() && i != parts.length - 1) {
                    if (parts[i - 1].length() > 0 && part.charAt(j + 1) != ' ' &&
                            (part.charAt(j + 1) == parts[i - 1].charAt(parts[i - 1].length() - 1) ||
                                    (part.charAt(j + 1) == ']' && parts[i - 1].charAt(parts[i - 1].length() - 1) == '[') ||
                                    (part.charAt(j + 1) == '}' && parts[i - 1].charAt(parts[i - 1].length() - 1) == '{') ||
                                    (part.charAt(j + 1) == ')' && parts[i - 1].charAt(parts[i - 1].length() - 1) == '(')) &&
                            j + 2 < part.length()) {
                        delimiters.add(part.charAt(j + 2));
                    } else {
                        delimiters.add(part.charAt(j + 1));
                    }
                }
                if (j > 0) {
                    maskAdditionalParameters.put(i, part.substring(0, j));
                } else {
                    maskAdditionalParameters.put(i, "");
                }
            }
        }
        return new Parameters(maskParameters, maskAdditionalParameters, maskUserParameters, delimiters);
    }

    static int getKeyByValue(Map<Integer, Character> map, Character value) {
        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return 0;
    }

    static LogString buildLogString(Map<Integer, Character> maskParams, List<String> element) {
        LogString.Builder builder = LogString.newBuilder();
        if (maskParams.containsValue('a')) {
            builder.setRemoteIp(element.get(MaskHandler.getKeyByValue(maskParams, 'a') - 1));
        }
        if (maskParams.containsValue('A')) {
            builder.setLocalIp(element.get(MaskHandler.getKeyByValue(maskParams, 'A') - 1));
        }
        if (maskParams.containsValue('B')) {
            builder.setSizeOfResponseInBytes(element.get(MaskHandler.getKeyByValue(maskParams, 'B') - 1));
        }
        if (maskParams.containsValue('b')) {
            builder.setSizeOfResponseInBytesInClf(element.get(MaskHandler.getKeyByValue(maskParams, 'b') - 1));
        }
        if (maskParams.containsValue('C')) {
            builder.setContentsOfCookie(element.get(MaskHandler.getKeyByValue(maskParams, 'C') - 1));
        }
        if (maskParams.containsValue('D')) {
            builder.setTimeToServeRequest(element.get(MaskHandler.getKeyByValue(maskParams, 'D') - 1));
        }
        if (maskParams.containsValue('e')) {
            builder.setContentsOfEnvironmentVar(element.get(MaskHandler.getKeyByValue(maskParams, 'e') - 1));
        }
        if (maskParams.containsValue('f')) {
            builder.setFilename(element.get(MaskHandler.getKeyByValue(maskParams, 'f') - 1));
        }
        if (maskParams.containsValue('h')) {
            builder.setRemoteHost(element.get(MaskHandler.getKeyByValue(maskParams, 'h') - 1));
        }
        if (maskParams.containsValue('H')) {
            builder.setRequestProtocol(element.get(MaskHandler.getKeyByValue(maskParams, 'H') - 1));
        }
        if (maskParams.containsValue('i')) {
            builder.setContentsOfHeaderInRequest(element.get(MaskHandler.getKeyByValue(maskParams, 'i') - 1));
        }
        if (maskParams.containsValue('l')) {
            builder.setRemoteLogname(element.get(MaskHandler.getKeyByValue(maskParams, 'l') - 1));
        }
        if (maskParams.containsValue('m')) {
            builder.setRequestMethod(element.get(MaskHandler.getKeyByValue(maskParams, 'm') - 1));
        }
        if (maskParams.containsValue('n')) {
            builder.setContentsOfNote(element.get(MaskHandler.getKeyByValue(maskParams, 'n') - 1));
        }
        if (maskParams.containsValue('o')) {
            builder.setContentsOfHeaderInReply(element.get(MaskHandler.getKeyByValue(maskParams, 'o') - 1));
        }
        if (maskParams.containsValue('p')) {
            builder.setCanonicalPort(element.get(MaskHandler.getKeyByValue(maskParams, 'p') - 1));
        }
        if (maskParams.containsValue('P')) {
            builder.setProcessIdOfChild(element.get(MaskHandler.getKeyByValue(maskParams, 'e') - 1));
        }
//            if (maskParams.containsValue('P')) { // w Format
//                builder.setProcessIdOfThreadOfChild(element.get(MaskHandler.getKeyByValue(maskParams, 'e') - 1));
//            }
        if (maskParams.containsValue('q')) {
            builder.setQueryString(element.get(MaskHandler.getKeyByValue(maskParams, 'q') - 1));
        }
        if (maskParams.containsValue('r')) {
            builder.setFirstLineOfRequest(element.get(MaskHandler.getKeyByValue(maskParams, 'r') - 1));
        }
        if (maskParams.containsValue('s')) {
            builder.setStatus(element.get(MaskHandler.getKeyByValue(maskParams, 's') - 1));
        }
        if (maskParams.containsValue('t')) {
            builder.setTimeInStandartFormat(element.get(MaskHandler.getKeyByValue(maskParams, 't') - 1));
        }
//        if (maskParams.containsValue('t')){ // w Format
//            builder.setTimeInGivenFormat(element.get(MaskHandler.getKeyByValue(maskParams, 'e') - 1));
//        }
        if (maskParams.containsValue('T')) {
            builder.setTimeTakenToServe(element.get(MaskHandler.getKeyByValue(maskParams, 'T') - 1));
        }
        if (maskParams.containsValue('u')) {
            builder.setRemoteUser(element.get(MaskHandler.getKeyByValue(maskParams, 'u') - 1));
        }
        if (maskParams.containsValue('U')) {
            builder.setUrlPathRequested(element.get(MaskHandler.getKeyByValue(maskParams, 'U') - 1));
        }
        if (maskParams.containsValue('v')) {
            builder.setCanonicalServerName(element.get(MaskHandler.getKeyByValue(maskParams, 'v') - 1));
        }
        if (maskParams.containsValue('V')) {
            builder.setServerName(element.get(MaskHandler.getKeyByValue(maskParams, 'V') - 1));
        }
        if (maskParams.containsValue('X')) {
            builder.setConnectionStatus(element.get(MaskHandler.getKeyByValue(maskParams, 'X') - 1));
        }
        if (maskParams.containsValue('I')) {
            builder.setBytesReceived(element.get(MaskHandler.getKeyByValue(maskParams, 'I') - 1));
        }
        if (maskParams.containsValue('O')) {
            builder.setBytesSent(element.get(MaskHandler.getKeyByValue(maskParams, 'O') - 1));
        }
        return builder.build();
    }
}
