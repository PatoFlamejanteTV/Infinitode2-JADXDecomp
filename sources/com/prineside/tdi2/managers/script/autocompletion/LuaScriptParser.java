package com.prineside.tdi2.managers.script.autocompletion;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.utils.logging.LogLevel;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/autocompletion/LuaScriptParser.class */
public class LuaScriptParser {

    /* renamed from: a, reason: collision with root package name */
    private String f2565a;
    public final Block root = new Block();

    static {
        TLog.forClass(LuaScriptParser.class);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0128, code lost:            continue;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public LuaScriptParser(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.script.autocompletion.LuaScriptParser.<init>(java.lang.String):void");
    }

    public static void main(String[] strArr) {
        LogLevel.setCurrent((byte) 3);
        String[] strArr2 = {"", "c", "cm", "local a = co"};
        for (int i = 0; i < 4; i++) {
            String str = strArr2[i];
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("====" + str + "====");
            LuaScriptParser luaScriptParser = new LuaScriptParser(str);
            System.out.println(luaScriptParser.root.describe(str));
            for (int i2 = -1; i2 < str.length(); i2++) {
                System.out.println("> Cursor pos: " + i2 + " | " + Arrays.toString(luaScriptParser.getACStringAt(i2)));
            }
        }
    }

    private Block a(int i) {
        Array array = new Array();
        array.add(this.root);
        Block block = this.root;
        while (array.size != 0) {
            Block block2 = (Block) array.pop();
            if (i >= block2.start && i <= block2.end) {
                block = block2;
                Array.ArrayIterator<Block> it = block2.children.iterator();
                while (it.hasNext()) {
                    array.add(it.next());
                }
            }
        }
        return block;
    }

    @Null
    public String[] getACStringAt(int i) {
        if (i < -1 || i >= this.f2565a.length()) {
            return null;
        }
        Block a2 = a(i);
        if (a2.isString()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = a2.start; i2 <= i; i2++) {
            boolean z = false;
            Array.ArrayIterator<Block> it = a2.children.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Block next = it.next();
                if (next.start <= i2 && next.end >= i2) {
                    z = true;
                    break;
                }
            }
            if (z) {
                stringBuilder.append('%');
            } else {
                stringBuilder.append(this.f2565a.charAt(i2));
            }
        }
        String replaceAll = stringBuilder.toString().replaceAll("%", "");
        String str = replaceAll;
        int max = Math.max(replaceAll.lastIndexOf(".."), str.lastIndexOf("="));
        if (max != -1) {
            str = str.substring(max + 1);
        }
        while (true) {
            char charAt = str.length() == 0 ? (char) 0 : str.charAt(0);
            char c = charAt;
            if (charAt != ' ' && c != '(' && c != '\t' && c != '\n' && c != '.') {
                break;
            }
            str = str.substring(1);
        }
        String trim = str.trim();
        if (trim.contains(")")) {
            return null;
        }
        Array array = new Array();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i3 = 0; i3 < trim.length(); i3++) {
            char charAt2 = trim.charAt(i3);
            if (charAt2 == ':' || charAt2 == '.') {
                array.add(stringBuilder2.toString());
                stringBuilder2.setLength(0);
            } else {
                stringBuilder2.append(charAt2);
            }
        }
        array.add(stringBuilder2.toString());
        return (String[]) array.toArray(String.class);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/autocompletion/LuaScriptParser$Block.class */
    public static final class Block {

        @Null
        public Block parent;
        public Array<Block> children = new Array<>();
        public char startChar;
        public int start;
        public int end;

        public final boolean isString() {
            return this.startChar == '\'' || this.startChar == '\"';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final String describe(String str) {
            Array array = new Array();
            Array array2 = new Array();
            array2.add(this);
            while (array2.size != 0) {
                Block block = (Block) array2.pop();
                array.add(block);
                for (int i = block.children.size - 1; i >= 0; i--) {
                    array2.add(block.children.get(i));
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                Block block2 = (Block) it.next();
                for (int i2 = 0; i2 < block2.start; i2++) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(block2.startChar);
                for (int i3 = 0; i3 < block2.end - block2.start; i3++) {
                    stringBuilder.append('-');
                }
                for (int i4 = 0; i4 < str.length() - block2.end; i4++) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(block2.start).append(':').append(block2.end);
                stringBuilder.append(SequenceUtils.EOL);
            }
            return str + SequenceUtils.EOL + ((Object) stringBuilder);
        }
    }
}
