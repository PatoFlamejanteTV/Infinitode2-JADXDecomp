package com.vladsch.flexmark.util.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/Html5Entities.class */
public class Html5Entities {
    private static final Map<String, String> NAMED_CHARACTER_REFERENCES = readEntities();
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^&#[Xx]?");
    private static final String ENTITY_PATH = "/com/vladsch/flexmark/util/html/entities.properties";

    public static String entityToString(String str) {
        Matcher matcher = NUMERIC_PATTERN.matcher(str);
        if (matcher.find()) {
            try {
                int parseInt = Integer.parseInt(str.substring(matcher.end(), str.length() - 1), matcher.end() == 2 ? 10 : 16);
                if (parseInt == 0) {
                    return "�";
                }
                return new String(Character.toChars(parseInt));
            } catch (IllegalArgumentException unused) {
                return "�";
            }
        }
        String str2 = NAMED_CHARACTER_REFERENCES.get(str.substring(1, str.length() - 1));
        if (str2 != null) {
            return str2;
        }
        return str;
    }

    public static BasedSequence entityToSequence(BasedSequence basedSequence) {
        Matcher matcher = NUMERIC_PATTERN.matcher(basedSequence);
        BasedSequence subSequence = basedSequence.subSequence(0, 0);
        if (matcher.find()) {
            try {
                int parseInt = Integer.parseInt(basedSequence.subSequence(matcher.end(), basedSequence.length() - 1).toString(), matcher.end() == 2 ? 10 : 16);
                if (parseInt == 0) {
                    return PrefixedSubSequence.prefixOf("�", subSequence);
                }
                return PrefixedSubSequence.prefixOf(Arrays.toString(Character.toChars(parseInt)), subSequence);
            } catch (IllegalArgumentException unused) {
                return PrefixedSubSequence.prefixOf("�", subSequence);
            }
        }
        String str = NAMED_CHARACTER_REFERENCES.get(basedSequence.subSequence(1, basedSequence.length() - 1).toString());
        if (str != null) {
            return PrefixedSubSequence.prefixOf(str, subSequence);
        }
        return basedSequence;
    }

    private static Map<String, String> readEntities() {
        HashMap hashMap = new HashMap();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Html5Entities.class.getResourceAsStream(ENTITY_PATH), StandardCharsets.UTF_8));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    if (readLine.length() != 0) {
                        int indexOf = readLine.indexOf("=");
                        hashMap.put(readLine.substring(0, indexOf), readLine.substring(indexOf + 1));
                    }
                } else {
                    hashMap.put("NewLine", SequenceUtils.EOL);
                    return hashMap;
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed reading data for HTML named character references", e);
        }
    }
}
