package com.prineside.luaj;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* loaded from: infinitode-2.jar:com/prineside/luaj/Print.class */
public class Print extends Lua {
    public static PrintStream ps = System.out;
    public static final String[] OPNAMES = {"MOVE", "LOADK", "LOADKX", "LOADBOOL", "LOADNIL", "GETUPVAL", "GETTABUP", "GETTABLE", "SETTABUP", "SETUPVAL", "SETTABLE", "NEWTABLE", "SELF", "ADD", "SUB", "MUL", "DIV", "MOD", "POW", "UNM", "NOT", "LEN", "CONCAT", "JMP", "EQ", "LT", "LE", "TEST", "TESTSET", "CALL", "TAILCALL", "RETURN", "FORLOOP", "FORPREP", "TFORCALL", "TFORLOOP", "SETLIST", "CLOSURE", "VARARG", "EXTRAARG", null};

    private static void a(PrintStream printStream, LuaString luaString) {
        printStream.print('\"');
        int i = luaString.m_length;
        for (int i2 = 0; i2 < i; i2++) {
            byte b2 = luaString.m_bytes[luaString.m_offset + i2];
            if (b2 >= 32 && b2 <= 126 && b2 != 34 && b2 != 92) {
                printStream.print((char) b2);
            } else {
                switch (b2) {
                    case 7:
                        printStream.print("\\a");
                        break;
                    case 8:
                        printStream.print("\\b");
                        break;
                    case 9:
                        printStream.print("\\t");
                        break;
                    case 10:
                        printStream.print("\\n");
                        break;
                    case 11:
                        printStream.print("\\v");
                        break;
                    case 12:
                        printStream.print("\\f");
                        break;
                    case 13:
                        printStream.print("\\r");
                        break;
                    case 34:
                        printStream.print("\\\"");
                        break;
                    case 92:
                        printStream.print("\\\\");
                        break;
                    default:
                        printStream.print('\\');
                        printStream.print(Integer.toString(1255 & b2).substring(1));
                        break;
                }
            }
        }
        printStream.print('\"');
    }

    private static void a(PrintStream printStream, LuaValue luaValue) {
        if (luaValue == null) {
            printStream.print("null");
            return;
        }
        switch (luaValue.type()) {
            case 4:
                a(printStream, (LuaString) luaValue);
                return;
            default:
                printStream.print(luaValue.tojstring());
                return;
        }
    }

    private static void a(PrintStream printStream, FPrototype fPrototype, int i) {
        a(printStream, i < fPrototype.k.length ? fPrototype.k[i] : LuaValue.valueOf("UNKNOWN_CONST_" + i));
    }

    private static void a(PrintStream printStream, Upvaldesc upvaldesc) {
        printStream.print(((int) upvaldesc.idx) + SequenceUtils.SPACE);
        a(printStream, (LuaValue) upvaldesc.name);
    }

    public static void printCode(FPrototype fPrototype) {
        int length = fPrototype.code.length;
        int i = 0;
        while (i < length) {
            int printOpCode = printOpCode(fPrototype, i);
            ps.println();
            i = printOpCode + 1;
        }
    }

    public static int printOpCode(FPrototype fPrototype, int i) {
        return printOpCode(ps, fPrototype, i);
    }

    public static int printOpCode(PrintStream printStream, FPrototype fPrototype, int i) {
        int[] iArr = fPrototype.code;
        int i2 = iArr[i];
        int GET_OPCODE = GET_OPCODE(i2);
        int GETARG_A = GETARG_A(i2);
        int GETARG_B = GETARG_B(i2);
        int GETARG_C = GETARG_C(i2);
        int GETARG_Bx = GETARG_Bx(i2);
        int GETARG_sBx = GETARG_sBx(i2);
        int a2 = a(fPrototype, i);
        printStream.print("  " + (i + 1) + "  ");
        if (a2 > 0) {
            printStream.print("[" + a2 + "]  ");
        } else {
            printStream.print("[-]  ");
        }
        if (GET_OPCODE >= OPNAMES.length - 1) {
            printStream.print("UNKNOWN_OP_" + GET_OPCODE + "  ");
        } else {
            printStream.print(OPNAMES[GET_OPCODE] + "  ");
            switch (getOpMode(GET_OPCODE)) {
                case 0:
                    printStream.print(GETARG_A);
                    if (getBMode(GET_OPCODE) != 0) {
                        printStream.print(SequenceUtils.SPACE + (ISK(GETARG_B) ? (-1) - INDEXK(GETARG_B) : GETARG_B));
                    }
                    if (getCMode(GET_OPCODE) != 0) {
                        printStream.print(SequenceUtils.SPACE + (ISK(GETARG_C) ? (-1) - INDEXK(GETARG_C) : GETARG_C));
                        break;
                    }
                    break;
                case 1:
                    if (getBMode(GET_OPCODE) == 3) {
                        printStream.print(GETARG_A + SequenceUtils.SPACE + ((-1) - GETARG_Bx));
                        break;
                    } else {
                        printStream.print(GETARG_A + SequenceUtils.SPACE + GETARG_Bx);
                        break;
                    }
                case 2:
                    if (GET_OPCODE == 23) {
                        printStream.print(GETARG_sBx);
                        break;
                    } else {
                        printStream.print(GETARG_A + SequenceUtils.SPACE + GETARG_sBx);
                        break;
                    }
            }
            switch (GET_OPCODE) {
                case 1:
                    printStream.print("  ; ");
                    a(printStream, fPrototype, GETARG_Bx);
                    break;
                case 5:
                case 9:
                    printStream.print("  ; ");
                    if (GETARG_B < fPrototype.upvalues.length) {
                        a(printStream, fPrototype.upvalues[GETARG_B]);
                        break;
                    } else {
                        printStream.print("UNKNOWN_UPVALUE_" + GETARG_B);
                        break;
                    }
                case 6:
                    printStream.print("  ; ");
                    if (GETARG_B < fPrototype.upvalues.length) {
                        a(printStream, fPrototype.upvalues[GETARG_B]);
                    } else {
                        printStream.print("UNKNOWN_UPVALUE_" + GETARG_B);
                    }
                    printStream.print(SequenceUtils.SPACE);
                    if (ISK(GETARG_C)) {
                        a(printStream, fPrototype, INDEXK(GETARG_C));
                        break;
                    } else {
                        printStream.print("-");
                        break;
                    }
                case 7:
                case 12:
                    if (ISK(GETARG_C)) {
                        printStream.print("  ; ");
                        a(printStream, fPrototype, INDEXK(GETARG_C));
                        break;
                    }
                    break;
                case 8:
                    printStream.print("  ; ");
                    if (GETARG_A < fPrototype.upvalues.length) {
                        a(printStream, fPrototype.upvalues[GETARG_A]);
                    } else {
                        printStream.print("UNKNOWN_UPVALUE_" + GETARG_A);
                    }
                    printStream.print(SequenceUtils.SPACE);
                    if (ISK(GETARG_B)) {
                        a(printStream, fPrototype, INDEXK(GETARG_B));
                    } else {
                        printStream.print("-");
                    }
                    printStream.print(SequenceUtils.SPACE);
                    if (ISK(GETARG_C)) {
                        a(printStream, fPrototype, INDEXK(GETARG_C));
                        break;
                    } else {
                        printStream.print("-");
                        break;
                    }
                case 10:
                case 13:
                case 14:
                case 15:
                case 16:
                case 18:
                case 24:
                case 25:
                case 26:
                    if (ISK(GETARG_B) || ISK(GETARG_C)) {
                        printStream.print("  ; ");
                        if (ISK(GETARG_B)) {
                            a(printStream, fPrototype, INDEXK(GETARG_B));
                        } else {
                            printStream.print("-");
                        }
                        printStream.print(SequenceUtils.SPACE);
                        if (ISK(GETARG_C)) {
                            a(printStream, fPrototype, INDEXK(GETARG_C));
                            break;
                        } else {
                            printStream.print("-");
                            break;
                        }
                    }
                    break;
                case 23:
                case 32:
                case 33:
                    printStream.print("  ; to " + (GETARG_sBx + i + 2));
                    break;
                case 36:
                    if (GETARG_C == 0) {
                        i++;
                        printStream.print("  ; " + iArr[i] + " (stored in the next OP)");
                        break;
                    } else {
                        printStream.print("  ; " + GETARG_C);
                        break;
                    }
                case 37:
                    if (GETARG_Bx < fPrototype.p.length) {
                        printStream.print("  ; " + fPrototype.p[GETARG_Bx].getClass().getName());
                        break;
                    } else {
                        printStream.print("  ; UNKNOWN_PROTYPE_" + GETARG_Bx);
                        break;
                    }
                case 38:
                    printStream.print("  ; is_vararg=" + fPrototype.is_vararg);
                    break;
            }
        }
        return i;
    }

    private static int a(FPrototype fPrototype, int i) {
        if (i <= 0 || fPrototype.lineinfo == null || i >= fPrototype.lineinfo.length) {
            return -1;
        }
        return fPrototype.lineinfo[i];
    }

    private static void a(FPrototype fPrototype) {
        String substring;
        String valueOf = String.valueOf(fPrototype.source);
        if (!valueOf.startsWith("@") && !valueOf.startsWith("=")) {
            if ("\u001bLua".equals(valueOf)) {
                substring = "(bstring)";
            } else {
                substring = "(string)";
            }
        } else {
            substring = valueOf.substring(1);
        }
        ps.print("\n%" + (fPrototype.linedefined == 0 ? "main" : "function") + " <" + substring + ":" + ((int) fPrototype.linedefined) + "," + ((int) fPrototype.lastlinedefined) + "> (" + fPrototype.code.length + " instructions, " + (fPrototype.code.length << 2) + " bytes at " + a() + ")\n");
        ps.print(((int) fPrototype.numparams) + " param, " + ((int) fPrototype.maxstacksize) + " slot, " + fPrototype.upvalues.length + " upvalue, ");
        ps.print(fPrototype.locvars.length + " local, " + fPrototype.k.length + " constant, " + fPrototype.p.length + " function\n");
    }

    private static void b(FPrototype fPrototype) {
        int length = fPrototype.k.length;
        ps.print("constants (" + length + ") for " + a() + ":\n");
        for (int i = 0; i < length; i++) {
            ps.print("  " + (i + 1) + "  ");
            a(ps, fPrototype.k[i]);
            ps.print(SequenceUtils.EOL);
        }
    }

    private static void c(FPrototype fPrototype) {
        int length = fPrototype.locvars.length;
        ps.print("locals (" + length + ") for " + a() + ":\n");
        for (int i = 0; i < length; i++) {
            ps.println("  " + i + "  " + fPrototype.locvars[i].varname + SequenceUtils.SPACE + (fPrototype.locvars[i].startpc + 1) + SequenceUtils.SPACE + (fPrototype.locvars[i].endpc + 1));
        }
    }

    private static void d(FPrototype fPrototype) {
        int length = fPrototype.upvalues.length;
        ps.print("upvalues (" + length + ") for " + a() + ":\n");
        for (int i = 0; i < length; i++) {
            ps.print("  " + i + "  " + fPrototype.upvalues[i] + SequenceUtils.EOL);
        }
    }

    public static void print(FPrototype fPrototype) {
        printFunction(fPrototype, true);
    }

    public static void printFunction(FPrototype fPrototype, boolean z) {
        int length = fPrototype.p.length;
        a(fPrototype);
        printCode(fPrototype);
        if (z) {
            b(fPrototype);
            c(fPrototype);
            d(fPrototype);
        }
        for (int i = 0; i < length; i++) {
            printFunction(fPrototype.p[i], z);
        }
    }

    private static void a(String str, int i) {
        int length = str.length();
        if (length > 50) {
            ps.print(str.substring(0, 50));
            return;
        }
        ps.print(str);
        int i2 = 50 - length;
        while (true) {
            i2--;
            if (i2 >= 0) {
                ps.print(' ');
            } else {
                return;
            }
        }
    }

    private static String a() {
        return "Proto";
    }

    public static void printState(LuaClosure luaClosure, int i, LuaValue[] luaValueArr, int i2, Varargs varargs) {
        PrintStream printStream = ps;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ps = new PrintStream(byteArrayOutputStream);
        printOpCode(luaClosure.p, i);
        ps.flush();
        ps.close();
        ps = printStream;
        a(byteArrayOutputStream.toString(), 50);
        printStack(luaValueArr, i2, varargs);
        ps.println();
    }

    public static void printStack(LuaValue[] luaValueArr, int i, Varargs varargs) {
        String str;
        ps.print('[');
        for (int i2 = 0; i2 < luaValueArr.length; i2++) {
            LuaValue luaValue = luaValueArr[i2];
            if (luaValue == null) {
                ps.print("null");
            } else {
                switch (luaValue.type()) {
                    case 4:
                        LuaString checkstring = luaValue.checkstring();
                        PrintStream printStream = ps;
                        if (checkstring.length() < 48) {
                            str = checkstring.tojstring();
                        } else {
                            str = checkstring.substring(0, 32).tojstring() + "...+" + (checkstring.length() - 32) + FlexmarkHtmlConverter.B_NODE;
                        }
                        printStream.print(str);
                        break;
                    case 5:
                    default:
                        ps.print(luaValue.tojstring());
                        break;
                    case 6:
                        ps.print(luaValue.tojstring());
                        break;
                    case 7:
                        Object obj = luaValue.touserdata();
                        if (obj == null) {
                            ps.print(luaValue.toString());
                            break;
                        } else {
                            String name = obj.getClass().getName();
                            ps.print(name.substring(name.lastIndexOf(46) + 1) + ": " + Integer.toHexString(obj.hashCode()));
                            break;
                        }
                }
            }
            if (i2 + 1 == i) {
                ps.print(']');
            }
            ps.print(" | ");
        }
        ps.print(varargs);
    }
}
