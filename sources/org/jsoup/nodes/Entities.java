package org.jsoup.nodes;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Parser;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/Entities.class */
public class Entities {
    private static final int empty = -1;
    private static final String emptyName = "";
    static final int codepointRadix = 36;
    private static final char[] codeDelims = {',', ';'};
    private static final HashMap<String, String> multipoints = new HashMap<>();
    private static Document.OutputSettings DefaultOutput;

    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Entities$EscapeMode.class */
    public enum EscapeMode {
        xhtml(EntitiesData.xmlPoints, 4),
        base(EntitiesData.basePoints, 106),
        extended(EntitiesData.fullPoints, 2125);

        private String[] nameKeys;
        private int[] codeVals;
        private int[] codeKeys;
        private String[] nameVals;

        EscapeMode(String str, int i) {
            Entities.load(this, str, i);
        }

        final int codepointForName(String str) {
            int binarySearch = Arrays.binarySearch(this.nameKeys, str);
            if (binarySearch >= 0) {
                return this.codeVals[binarySearch];
            }
            return -1;
        }

        final String nameForCodepoint(int i) {
            int binarySearch = Arrays.binarySearch(this.codeKeys, i);
            if (binarySearch >= 0) {
                return (binarySearch >= this.nameVals.length - 1 || this.codeKeys[binarySearch + 1] != i) ? this.nameVals[binarySearch] : this.nameVals[binarySearch + 1];
            }
            return "";
        }

        private int size() {
            return this.nameKeys.length;
        }
    }

    private Entities() {
    }

    public static boolean isNamedEntity(String str) {
        return EscapeMode.extended.codepointForName(str) != -1;
    }

    public static boolean isBaseNamedEntity(String str) {
        return EscapeMode.base.codepointForName(str) != -1;
    }

    public static String getByName(String str) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            return str2;
        }
        int codepointForName = EscapeMode.extended.codepointForName(str);
        if (codepointForName != -1) {
            return new String(new int[]{codepointForName}, 0, 1);
        }
        return "";
    }

    public static int codepointsForName(String str, int[] iArr) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            iArr[0] = str2.codePointAt(0);
            iArr[1] = str2.codePointAt(1);
            return 2;
        }
        int codepointForName = EscapeMode.extended.codepointForName(str);
        if (codepointForName != -1) {
            iArr[0] = codepointForName;
            return 1;
        }
        return 0;
    }

    public static String escape(String str, Document.OutputSettings outputSettings) {
        if (str == null) {
            return "";
        }
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        try {
            escape(borrowBuilder, str, outputSettings, false, false, false, false);
            return StringUtil.releaseBuilder(borrowBuilder);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public static String escape(String str) {
        if (DefaultOutput == null) {
            DefaultOutput = new Document.OutputSettings();
        }
        return escape(str, DefaultOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void escape(Appendable appendable, String str, Document.OutputSettings outputSettings, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5 = false;
        boolean z6 = false;
        EscapeMode escapeMode = outputSettings.escapeMode();
        CharsetEncoder encoder = outputSettings.encoder();
        CoreCharset coreCharset = outputSettings.coreCharset;
        int length = str.length();
        boolean z7 = false;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < length) {
                int codePointAt = str.codePointAt(i2);
                if (z2) {
                    if (StringUtil.isWhitespace(codePointAt)) {
                        if ((!z3 || z6) && !z5) {
                            if (z4) {
                                z7 = true;
                            } else {
                                appendable.append(' ');
                                z5 = true;
                            }
                        }
                        i = i2 + Character.charCount(codePointAt);
                    } else {
                        z5 = false;
                        z6 = true;
                        if (z7) {
                            appendable.append(' ');
                            z7 = false;
                        }
                    }
                }
                if (codePointAt < 65536) {
                    char c = (char) codePointAt;
                    switch (c) {
                        case '\t':
                        case '\n':
                        case '\r':
                            appendable.append(c);
                            break;
                        case '\"':
                            if (z) {
                                appendable.append("&quot;");
                                break;
                            } else {
                                appendable.append(c);
                                break;
                            }
                        case '&':
                            appendable.append("&amp;");
                            break;
                        case '<':
                            if (!z || escapeMode == EscapeMode.xhtml || outputSettings.syntax() == Document.OutputSettings.Syntax.xml) {
                                appendable.append("&lt;");
                                break;
                            } else {
                                appendable.append(c);
                                break;
                            }
                            break;
                        case '>':
                            if (!z) {
                                appendable.append("&gt;");
                                break;
                            } else {
                                appendable.append(c);
                                break;
                            }
                        case 160:
                            if (escapeMode != EscapeMode.xhtml) {
                                appendable.append("&nbsp;");
                                break;
                            } else {
                                appendable.append("&#xa0;");
                                break;
                            }
                        default:
                            if (c < ' ' || !canEncode(coreCharset, c, encoder)) {
                                appendEncoded(appendable, escapeMode, codePointAt);
                                break;
                            } else {
                                appendable.append(c);
                                break;
                            }
                            break;
                    }
                } else {
                    String str2 = new String(Character.toChars(codePointAt));
                    if (encoder.canEncode(str2)) {
                        appendable.append(str2);
                    } else {
                        appendEncoded(appendable, escapeMode, codePointAt);
                    }
                }
                i = i2 + Character.charCount(codePointAt);
            } else {
                return;
            }
        }
    }

    private static void appendEncoded(Appendable appendable, EscapeMode escapeMode, int i) {
        String nameForCodepoint = escapeMode.nameForCodepoint(i);
        if (!"".equals(nameForCodepoint)) {
            appendable.append('&').append(nameForCodepoint).append(';');
        } else {
            appendable.append("&#x").append(Integer.toHexString(i)).append(';');
        }
    }

    public static String unescape(String str) {
        return unescape(str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String unescape(String str, boolean z) {
        return Parser.unescapeEntities(str, z);
    }

    private static boolean canEncode(CoreCharset coreCharset, char c, CharsetEncoder charsetEncoder) {
        switch (coreCharset) {
            case ascii:
                return c < 128;
            case utf:
                return true;
            default:
                return charsetEncoder.canEncode(c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Entities$CoreCharset.class */
    public enum CoreCharset {
        ascii,
        utf,
        fallback;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static CoreCharset byName(String str) {
            if (str.equals("US-ASCII")) {
                return ascii;
            }
            if (str.startsWith("UTF-")) {
                return utf;
            }
            return fallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void load(EscapeMode escapeMode, String str, int i) {
        int i2;
        escapeMode.nameKeys = new String[i];
        escapeMode.codeVals = new int[i];
        escapeMode.codeKeys = new int[i];
        escapeMode.nameVals = new String[i];
        int i3 = 0;
        CharacterReader characterReader = new CharacterReader(str);
        while (!characterReader.isEmpty()) {
            try {
                String consumeTo = characterReader.consumeTo('=');
                characterReader.advance();
                int parseInt = Integer.parseInt(characterReader.consumeToAny(codeDelims), 36);
                char current = characterReader.current();
                characterReader.advance();
                if (current == ',') {
                    i2 = Integer.parseInt(characterReader.consumeTo(';'), 36);
                    characterReader.advance();
                } else {
                    i2 = -1;
                }
                int parseInt2 = Integer.parseInt(characterReader.consumeTo('&'), 36);
                characterReader.advance();
                escapeMode.nameKeys[i3] = consumeTo;
                escapeMode.codeVals[i3] = parseInt;
                escapeMode.codeKeys[parseInt2] = parseInt;
                escapeMode.nameVals[parseInt2] = consumeTo;
                if (i2 != -1) {
                    multipoints.put(consumeTo, new String(new int[]{parseInt, i2}, 0, 2));
                }
                i3++;
            } finally {
                characterReader.close();
            }
        }
        Validate.isTrue(i3 == i, "Unexpected count of entities loaded");
    }
}
