package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/XmlReader.class */
public class XmlReader {
    private Element root;
    private Element current;
    private String entitiesText;
    private static final byte[] _xml_actions = init__xml_actions_0();
    private static final byte[] _xml_key_offsets = init__xml_key_offsets_0();
    private static final char[] _xml_trans_keys = init__xml_trans_keys_0();
    private static final byte[] _xml_single_lengths = init__xml_single_lengths_0();
    private static final byte[] _xml_range_lengths = init__xml_range_lengths_0();
    private static final short[] _xml_index_offsets = init__xml_index_offsets_0();
    private static final byte[] _xml_indicies = init__xml_indicies_0();
    private static final byte[] _xml_trans_targs = init__xml_trans_targs_0();
    private static final byte[] _xml_trans_actions = init__xml_trans_actions_0();
    static final int xml_start = 1;
    static final int xml_first_final = 34;
    static final int xml_error = 0;
    static final int xml_en_elementBody = 15;
    static final int xml_en_main = 1;
    private final Array<Element> elements = new Array<>(8);
    private final StringBuilder textBuffer = new StringBuilder(64);

    public Element parse(String str) {
        char[] charArray = str.toCharArray();
        return parse(charArray, 0, charArray.length);
    }

    public Element parse(Reader reader) {
        try {
            try {
                char[] cArr = new char[1024];
                int i = 0;
                while (true) {
                    int read = reader.read(cArr, i, cArr.length - i);
                    if (read != -1) {
                        if (read != 0) {
                            i += read;
                        } else {
                            char[] cArr2 = new char[cArr.length << 1];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            cArr = cArr2;
                        }
                    } else {
                        Element parse = parse(cArr, 0, i);
                        StreamUtils.closeQuietly(reader);
                        return parse;
                    }
                }
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(reader);
            throw th;
        }
    }

    public Element parse(InputStream inputStream) {
        try {
            try {
                Element parse = parse(new InputStreamReader(inputStream, "UTF-8"));
                StreamUtils.closeQuietly(inputStream);
                return parse;
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(inputStream);
            throw th;
        }
    }

    public Element parse(FileHandle fileHandle) {
        try {
            return parse(fileHandle.reader("UTF-8"));
        } catch (Exception e) {
            throw new SerializationException("Error parsing file: " + fileHandle, e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02a7, code lost:            if (r12[r15] == '>') goto L213;     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02aa, code lost:            r15 = r15 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01c6, code lost:            if (r12[r16 + 1] != '[') goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01d1, code lost:            if (r12[r16 + 2] != 'C') goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:            if (r12[r16 + 3] != 'D') goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01e7, code lost:            if (r12[r16 + 4] != 'A') goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01f2, code lost:            if (r12[r16 + 5] != 'T') goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01fe, code lost:            if (r12[r16 + 6] != 'A') goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x020a, code lost:            if (r12[r16 + 7] != '[') goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x020d, code lost:            r16 = r16 + 8;        r15 = r16 + 2;     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x021e, code lost:            if (r12[r15 - 2] != ']') goto L206;     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0229, code lost:            if (r12[r15 - 1] != ']') goto L207;     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0232, code lost:            if (r12[r15] == '>') goto L205;     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x023b, code lost:            text(new java.lang.String(r12, r16, (r15 - r16) - 2));     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02b0, code lost:            r13 = 15;        r0 = 2;     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0235, code lost:            r15 = r15 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0257, code lost:            if (r0 != '!') goto L214;     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0262, code lost:            if (r12[r16 + 1] != '-') goto L214;     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x026d, code lost:            if (r12[r16 + 2] != '-') goto L214;     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0270, code lost:            r15 = r16 + 3;     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x027c, code lost:            if (r12[r15] != '-') goto L210;     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0287, code lost:            if (r12[r15 + 1] != '-') goto L211;     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0292, code lost:            if (r12[r15 + 2] == '>') goto L209;     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x029b, code lost:            r15 = r15 + 2;     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0295, code lost:            r15 = r15 + 1;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x0172. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0013. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0438 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0432 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x042e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x014e  */
    /* JADX WARN: Type inference failed for: r0v170, types: [int] */
    /* JADX WARN: Type inference failed for: r0v173, types: [int] */
    /* JADX WARN: Type inference failed for: r0v175, types: [int] */
    /* JADX WARN: Type inference failed for: r0v181, types: [int] */
    /* JADX WARN: Type inference failed for: r0v185, types: [int] */
    /* JADX WARN: Type inference failed for: r0v187, types: [int] */
    /* JADX WARN: Type inference failed for: r0v46, types: [int] */
    /* JADX WARN: Type inference failed for: r0v49, types: [int] */
    /* JADX WARN: Type inference failed for: r0v55, types: [int] */
    /* JADX WARN: Type inference failed for: r0v59, types: [int] */
    /* JADX WARN: Type inference failed for: r0v61, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.badlogic.gdx.utils.XmlReader.Element parse(char[] r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 1260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.XmlReader.parse(char[], int, int):com.badlogic.gdx.utils.XmlReader$Element");
    }

    private static byte[] init__xml_actions_0() {
        return new byte[]{0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 2, 1, 4, 2, 2, 4, 2, 6, 7, 2, 6, 8, 3, 0, 6, 7};
    }

    private static byte[] init__xml_key_offsets_0() {
        return new byte[]{0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95, 99, 103, 104, 108, 109, 110, 111, 112, 115};
    }

    private static char[] init__xml_trans_keys_0() {
        return new char[]{' ', '<', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '=', '\t', '\r', ' ', '\"', '\'', '\t', '\r', '\"', '\"', ' ', '/', '>', '\t', '\r', ' ', '>', '\t', '\r', ' ', '>', '\t', '\r', '\'', '\'', ' ', '<', '\t', '\r', '<', ' ', '/', '>', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '=', '\t', '\r', ' ', '\"', '\'', '\t', '\r', '\"', '\"', ' ', '/', '>', '\t', '\r', ' ', '>', '\t', '\r', ' ', '>', '\t', '\r', '<', ' ', '/', '\t', '\r', '>', '>', '\'', '\'', ' ', '\t', '\r', 0};
    }

    private static byte[] init__xml_single_lengths_0() {
        return new byte[]{0, 2, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 1, 2, 1, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 2, 1, 1, 1, 1, 1, 0};
    }

    private static byte[] init__xml_range_lengths_0() {
        return new byte[]{0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0};
    }

    private static short[] init__xml_index_offsets_0() {
        return new short[]{0, 0, 4, 9, 14, 20, 26, 30, 35, 37, 39, 44, 48, 52, 54, 56, 60, 62, 67, 72, 78, 84, 88, 93, 95, 97, 102, 106, 110, 112, 116, 118, 120, 122, 124, 127};
    }

    private static byte[] init__xml_indicies_0() {
        return new byte[]{0, 2, 0, 1, 2, 1, 1, 2, 3, 5, 6, 7, 5, 4, 9, 10, 1, 11, 9, 8, 13, 1, 14, 1, 13, 12, 15, 16, 15, 1, 16, 17, 18, 16, 1, 20, 19, 22, 21, 9, 10, 11, 9, 1, 23, 24, 23, 1, 25, 11, 25, 1, 20, 26, 22, 27, 29, 30, 29, 28, 32, 31, 30, 34, 1, 30, 33, 36, 37, 38, 36, 35, 40, 41, 1, 42, 40, 39, 44, 1, 45, 1, 44, 43, 46, 47, 46, 1, 47, 48, 49, 47, 1, 51, 50, 53, 52, 40, 41, 42, 40, 1, 54, 55, 54, 1, 56, 42, 56, 1, 57, 1, 57, 34, 57, 1, 1, 58, 59, 58, 51, 60, 53, 61, 62, 62, 1, 1, 0};
    }

    private static byte[] init__xml_trans_targs_0() {
        return new byte[]{1, 0, 2, 3, 3, 4, 11, 34, 5, 4, 11, 34, 5, 6, 7, 6, 7, 8, 13, 9, 10, 9, 10, 12, 34, 12, 14, 14, 16, 15, 17, 16, 17, 18, 30, 18, 19, 26, 28, 20, 19, 26, 28, 20, 21, 22, 21, 22, 23, 32, 24, 25, 24, 25, 27, 28, 27, 29, 31, 35, 33, 33, 34};
    }

    private static byte[] init__xml_trans_actions_0() {
        return new byte[]{0, 0, 0, 1, 0, 3, 3, 13, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 25, 0, 19, 5, 16, 0, 1, 0, 1, 0, 0, 0, 22, 1, 0, 0, 3, 3, 13, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 25, 0, 19, 5, 16, 0, 0, 0, 7, 1, 0, 0};
    }

    protected void open(String str) {
        Element element = new Element(str, this.current);
        Element element2 = this.current;
        if (element2 != null) {
            element2.addChild(element);
        }
        this.elements.add(element);
        this.current = element;
    }

    protected void attribute(String str, String str2) {
        this.current.setAttribute(str, str2);
    }

    @Null
    protected String entity(String str) {
        if (str.equals("lt")) {
            return "<";
        }
        if (str.equals("gt")) {
            return ">";
        }
        if (str.equals("amp")) {
            return "&";
        }
        if (str.equals("apos")) {
            return "'";
        }
        if (str.equals("quot")) {
            return "\"";
        }
        if (str.startsWith("#x")) {
            return Character.toString((char) Integer.parseInt(str.substring(2), 16));
        }
        return null;
    }

    protected void text(String str) {
        String text = this.current.getText();
        this.current.setText(text != null ? text + str : str);
    }

    protected void close() {
        this.root = this.elements.pop();
        this.current = this.elements.size > 0 ? this.elements.peek() : null;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/XmlReader$Element.class */
    public static class Element {
        private final String name;
        private ObjectMap<String, String> attributes;
        private Array<Element> children;
        private String text;
        private Element parent;

        public Element(String str, Element element) {
            this.name = str;
            this.parent = element;
        }

        public String getName() {
            return this.name;
        }

        public ObjectMap<String, String> getAttributes() {
            return this.attributes;
        }

        public String getAttribute(String str) {
            if (this.attributes == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn't have attribute: " + str);
            }
            String str2 = this.attributes.get(str);
            if (str2 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn't have attribute: " + str);
            }
            return str2;
        }

        public String getAttribute(String str, String str2) {
            String str3;
            if (this.attributes != null && (str3 = this.attributes.get(str)) != null) {
                return str3;
            }
            return str2;
        }

        public boolean hasAttribute(String str) {
            if (this.attributes == null) {
                return false;
            }
            return this.attributes.containsKey(str);
        }

        public void setAttribute(String str, String str2) {
            if (this.attributes == null) {
                this.attributes = new ObjectMap<>(8);
            }
            this.attributes.put(str, str2);
        }

        public int getChildCount() {
            if (this.children == null) {
                return 0;
            }
            return this.children.size;
        }

        public Element getChild(int i) {
            if (this.children == null) {
                throw new GdxRuntimeException("Element has no children: " + this.name);
            }
            return this.children.get(i);
        }

        public void addChild(Element element) {
            if (this.children == null) {
                this.children = new Array<>(8);
            }
            this.children.add(element);
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public void removeChild(int i) {
            if (this.children != null) {
                this.children.removeIndex(i);
            }
        }

        public void removeChild(Element element) {
            if (this.children != null) {
                this.children.removeValue(element, true);
            }
        }

        public void remove() {
            this.parent.removeChild(this);
        }

        public Element getParent() {
            return this.parent;
        }

        public String toString() {
            return toString("");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public String toString(String str) {
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append(str);
            stringBuilder.append('<');
            stringBuilder.append(this.name);
            if (this.attributes != null) {
                ObjectMap.Entries<String, String> it = this.attributes.entries().iterator();
                while (it.hasNext()) {
                    ObjectMap.Entry next = it.next();
                    stringBuilder.append(' ');
                    stringBuilder.append((String) next.key);
                    stringBuilder.append("=\"");
                    stringBuilder.append((String) next.value);
                    stringBuilder.append('\"');
                }
            }
            if (this.children == null && (this.text == null || this.text.length() == 0)) {
                stringBuilder.append("/>");
            } else {
                stringBuilder.append(">\n");
                String str2 = str + '\t';
                if (this.text != null && this.text.length() > 0) {
                    stringBuilder.append(str2);
                    stringBuilder.append(this.text);
                    stringBuilder.append('\n');
                }
                if (this.children != null) {
                    Array.ArrayIterator<Element> it2 = this.children.iterator();
                    while (it2.hasNext()) {
                        stringBuilder.append(it2.next().toString(str2));
                        stringBuilder.append('\n');
                    }
                }
                stringBuilder.append(str);
                stringBuilder.append("</");
                stringBuilder.append(this.name);
                stringBuilder.append('>');
            }
            return stringBuilder.toString();
        }

        @Null
        public Element getChildByName(String str) {
            if (this.children == null) {
                return null;
            }
            for (int i = 0; i < this.children.size; i++) {
                Element element = this.children.get(i);
                if (element.name.equals(str)) {
                    return element;
                }
            }
            return null;
        }

        public boolean hasChild(String str) {
            return (this.children == null || getChildByName(str) == null) ? false : true;
        }

        @Null
        public Element getChildByNameRecursive(String str) {
            if (this.children == null) {
                return null;
            }
            for (int i = 0; i < this.children.size; i++) {
                Element element = this.children.get(i);
                if (element.name.equals(str)) {
                    return element;
                }
                Element childByNameRecursive = element.getChildByNameRecursive(str);
                if (childByNameRecursive != null) {
                    return childByNameRecursive;
                }
            }
            return null;
        }

        public boolean hasChildRecursive(String str) {
            return (this.children == null || getChildByNameRecursive(str) == null) ? false : true;
        }

        public Array<Element> getChildrenByName(String str) {
            Array<Element> array = new Array<>();
            if (this.children == null) {
                return array;
            }
            for (int i = 0; i < this.children.size; i++) {
                Element element = this.children.get(i);
                if (element.name.equals(str)) {
                    array.add(element);
                }
            }
            return array;
        }

        public Array<Element> getChildrenByNameRecursively(String str) {
            Array<Element> array = new Array<>();
            getChildrenByNameRecursively(str, array);
            return array;
        }

        private void getChildrenByNameRecursively(String str, Array<Element> array) {
            if (this.children == null) {
                return;
            }
            for (int i = 0; i < this.children.size; i++) {
                Element element = this.children.get(i);
                if (element.name.equals(str)) {
                    array.add(element);
                }
                element.getChildrenByNameRecursively(str, array);
            }
        }

        public float getFloatAttribute(String str) {
            return Float.parseFloat(getAttribute(str));
        }

        public float getFloatAttribute(String str, float f) {
            String attribute = getAttribute(str, null);
            return attribute == null ? f : Float.parseFloat(attribute);
        }

        public int getIntAttribute(String str) {
            return Integer.parseInt(getAttribute(str));
        }

        public int getIntAttribute(String str, int i) {
            String attribute = getAttribute(str, null);
            return attribute == null ? i : Integer.parseInt(attribute);
        }

        public boolean getBooleanAttribute(String str) {
            return Boolean.parseBoolean(getAttribute(str));
        }

        public boolean getBooleanAttribute(String str, boolean z) {
            String attribute = getAttribute(str, null);
            return attribute == null ? z : Boolean.parseBoolean(attribute);
        }

        public String get(String str) {
            String str2 = get(str, null);
            if (str2 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn't have attribute or child: " + str);
            }
            return str2;
        }

        public String get(String str, String str2) {
            String text;
            String str3;
            if (this.attributes != null && (str3 = this.attributes.get(str)) != null) {
                return str3;
            }
            Element childByName = getChildByName(str);
            if (childByName != null && (text = childByName.getText()) != null) {
                return text;
            }
            return str2;
        }

        public int getInt(String str) {
            String str2 = get(str, null);
            if (str2 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn't have attribute or child: " + str);
            }
            return Integer.parseInt(str2);
        }

        public int getInt(String str, int i) {
            String str2 = get(str, null);
            return str2 == null ? i : Integer.parseInt(str2);
        }

        public float getFloat(String str) {
            String str2 = get(str, null);
            if (str2 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn't have attribute or child: " + str);
            }
            return Float.parseFloat(str2);
        }

        public float getFloat(String str, float f) {
            String str2 = get(str, null);
            return str2 == null ? f : Float.parseFloat(str2);
        }

        public boolean getBoolean(String str) {
            String str2 = get(str, null);
            if (str2 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn't have attribute or child: " + str);
            }
            return Boolean.parseBoolean(str2);
        }

        public boolean getBoolean(String str, boolean z) {
            String str2 = get(str, null);
            return str2 == null ? z : Boolean.parseBoolean(str2);
        }
    }
}
