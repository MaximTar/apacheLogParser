package tableCreator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 14.02.18.
 */
public class MaskParser {

    public static Map<Integer, Character> getMaskParameters(String mask) {
        String[] parts = mask.split("%");
        Map<Integer, Character> maskParameters = new HashMap<>();
        System.out.println(Arrays.toString(parts));
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            int j = 0;
            while (j < part.length() && !Character.isLetter(part.charAt(j))) {
                j++;
            }
            if (j < part.length() && Character.isLetter(part.charAt(j))) {
                maskParameters.put(i, part.charAt(j));
            }
        }
        return maskParameters;
    }

    public static void getMaskParameters() {
        String defaultMask = "%h %l %u %t \"%r\" %>s %b";
        getMaskParameters(defaultMask);
    }

    public static void parseMask() {
    }
}
