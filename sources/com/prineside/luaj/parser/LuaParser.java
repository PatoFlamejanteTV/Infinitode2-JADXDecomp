package com.prineside.luaj.parser;

import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.ast.Block;
import com.prineside.luaj.ast.Chunk;
import com.prineside.luaj.ast.Exp;
import com.prineside.luaj.ast.FuncArgs;
import com.prineside.luaj.ast.FuncBody;
import com.prineside.luaj.ast.Name;
import com.prineside.luaj.ast.ParList;
import com.prineside.luaj.ast.Stat;
import com.prineside.luaj.ast.Str;
import com.prineside.luaj.ast.SyntaxElement;
import com.prineside.luaj.ast.TableConstructor;
import com.prineside.luaj.ast.TableField;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.glfw.GLFW;

/* loaded from: infinitode-2.jar:com/prineside/luaj/parser/LuaParser.class */
public class LuaParser implements LuaParserConstants {
    public LuaParserTokenManager token_source;

    /* renamed from: a, reason: collision with root package name */
    private SimpleCharStream f1639a;
    public Token token;
    public Token jj_nt;

    /* renamed from: b, reason: collision with root package name */
    private int f1640b;
    private Token c;
    private Token d;
    private int e;
    private int f;
    private final int[] g;
    private static int[] h;
    private static int[] i;
    private static int[] j;
    private final JJCalls[] k;
    private boolean l;
    private int m;
    private final LookaheadSuccess n;
    private List o;
    private int[] p;
    private int q;
    private int[] r;
    private int s;

    static {
        LuaValue.valueOf(true);
        au();
        av();
        aw();
    }

    public static void main(String[] strArr) {
        new LuaParser(System.in).Chunk();
    }

    private static Exp.VarExp a(Exp.PrimaryExp primaryExp) {
        if (!primaryExp.isvarexp()) {
            throw new ParseException("expected variable");
        }
        return (Exp.VarExp) primaryExp;
    }

    private static Exp.FuncCall b(Exp.PrimaryExp primaryExp) {
        if (!primaryExp.isfunccall()) {
            throw new ParseException("expected function call");
        }
        return (Exp.FuncCall) primaryExp;
    }

    public SimpleCharStream getCharStream() {
        return this.f1639a;
    }

    private long a() {
        return (this.f1639a.getBeginLine() << 32) | this.f1639a.getBeginColumn();
    }

    private void a(SyntaxElement syntaxElement, long j2) {
        syntaxElement.beginLine = (int) (j2 >> 32);
        syntaxElement.beginColumn = (short) j2;
        syntaxElement.endLine = this.token.endLine;
        syntaxElement.endColumn = (short) this.token.endColumn;
    }

    private void a(SyntaxElement syntaxElement, Token token) {
        syntaxElement.beginLine = token.beginLine;
        syntaxElement.beginColumn = (short) token.beginColumn;
        syntaxElement.endLine = this.token.endLine;
        syntaxElement.endColumn = (short) this.token.endColumn;
    }

    public final Chunk Chunk() {
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 69:
                h(69);
                this.token_source.SwitchTo(1);
                break;
            default:
                this.g[0] = this.f;
                break;
        }
        Block Block = Block();
        h(0);
        Chunk chunk = new Chunk(Block);
        a(chunk, a2);
        return chunk;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final com.prineside.luaj.ast.Block Block() {
        /*
            r5 = this;
            com.prineside.luaj.ast.Block r0 = new com.prineside.luaj.ast.Block
            r1 = r0
            r1.<init>()
            r6 = r0
            r0 = r5
            long r0 = r0.a()
            r8 = r0
        Ld:
            r0 = r5
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto L1c
            r0 = r5
            int r0 = r0.ax()
            goto L20
        L1c:
            r0 = r5
            int r0 = r0.f1640b
        L20:
            switch(r0) {
                case 30: goto Le8;
                case 31: goto Le8;
                case 32: goto Leb;
                case 33: goto Leb;
                case 34: goto Leb;
                case 35: goto Leb;
                case 36: goto Le8;
                case 37: goto Le8;
                case 38: goto Le8;
                case 39: goto Le8;
                case 40: goto Leb;
                case 41: goto Le8;
                case 42: goto Leb;
                case 43: goto Leb;
                case 44: goto Leb;
                case 45: goto Leb;
                case 46: goto Le8;
                case 47: goto Leb;
                case 48: goto Leb;
                case 49: goto Leb;
                case 50: goto Le8;
                case 51: goto Le8;
                case 52: goto Leb;
                case 53: goto Leb;
                case 54: goto Leb;
                case 55: goto Leb;
                case 56: goto Leb;
                case 57: goto Leb;
                case 58: goto Leb;
                case 59: goto Leb;
                case 60: goto Leb;
                case 61: goto Leb;
                case 62: goto Leb;
                case 63: goto Leb;
                case 64: goto Leb;
                case 65: goto Le8;
                case 66: goto Leb;
                case 67: goto Leb;
                case 68: goto Leb;
                case 69: goto Leb;
                case 70: goto Le8;
                case 71: goto Leb;
                case 72: goto Leb;
                case 73: goto Leb;
                case 74: goto Leb;
                case 75: goto Le8;
                default: goto Leb;
            }
        Le8:
            goto Lf8
        Leb:
            r0 = r5
            int[] r0 = r0.g
            r1 = 1
            r2 = r5
            int r2 = r2.f
            r0[r1] = r2
            goto L105
        Lf8:
            r0 = r5
            com.prineside.luaj.ast.Stat r0 = r0.Stat()
            r7 = r0
            r0 = r6
            r1 = r7
            r0.add(r1)
            goto Ld
        L105:
            r0 = r5
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto L114
            r0 = r5
            int r0 = r0.ax()
            goto L118
        L114:
            r0 = r5
            int r0 = r0.f1640b
        L118:
            switch(r0) {
                case 45: goto L12c;
                default: goto L139;
            }
        L12c:
            r0 = r5
            com.prineside.luaj.ast.Stat r0 = r0.ReturnStat()
            r7 = r0
            r0 = r6
            r1 = r7
            r0.add(r1)
            goto L143
        L139:
            r0 = r5
            int[] r0 = r0.g
            r1 = 2
            r2 = r5
            int r2 = r2.f
            r0[r1] = r2
        L143:
            r0 = r5
            r1 = r6
            r2 = r8
            r0.a(r1, r2)
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParser.Block():com.prineside.luaj.ast.Block");
    }

    public final Stat Stat() {
        Exp exp = null;
        List<Exp> list = null;
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 30:
                h(30);
                Stat breakstat = Stat.breakstat();
                a(breakstat, a2);
                return breakstat;
            case 31:
                h(31);
                Block Block = Block();
                h(34);
                Stat block = Stat.block(Block);
                a(block, a2);
                return block;
            case 38:
                h(38);
                Stat gotostat = Stat.gotostat(h(51).image);
                a(gotostat, a2);
                return gotostat;
            case 39:
                Stat IfThenElse = IfThenElse();
                a(IfThenElse, a2);
                return IfThenElse;
            case 46:
                h(46);
                Block Block2 = Block();
                h(49);
                Stat repeatuntil = Stat.repeatuntil(Block2, Exp());
                a(repeatuntil, a2);
                return repeatuntil;
            case 50:
                h(50);
                Exp Exp = Exp();
                h(31);
                Block Block3 = Block();
                h(34);
                Stat whiledo = Stat.whiledo(Exp, Block3);
                a(whiledo, a2);
                return whiledo;
            case 65:
                Stat Label = Label();
                a(Label, a2);
                return Label;
            case 70:
                h(70);
                return null;
            default:
                this.g[5] = this.f;
                if (a(3)) {
                    h(36);
                    Token h2 = h(51);
                    h(71);
                    Exp Exp2 = Exp();
                    h(72);
                    Exp Exp3 = Exp();
                    switch (this.f1640b == -1 ? ax() : this.f1640b) {
                        case 72:
                            h(72);
                            exp = Exp();
                            break;
                        default:
                            this.g[3] = this.f;
                            break;
                    }
                    h(31);
                    Block Block4 = Block();
                    h(34);
                    Stat fornumeric = Stat.fornumeric(h2.image, Exp2, Exp3, exp, Block4);
                    a(fornumeric, a2);
                    return fornumeric;
                }
                switch (this.f1640b == -1 ? ax() : this.f1640b) {
                    case 36:
                        h(36);
                        List<Name> NameList = NameList();
                        h(40);
                        List<Exp> ExpList = ExpList();
                        h(31);
                        Block Block5 = Block();
                        h(34);
                        Stat forgeneric = Stat.forgeneric(NameList, ExpList, Block5);
                        a(forgeneric, a2);
                        return forgeneric;
                    case 37:
                        h(37);
                        Stat functiondef = Stat.functiondef(FuncName(), FuncBody());
                        a(functiondef, a2);
                        return functiondef;
                    default:
                        this.g[6] = this.f;
                        if (b(2)) {
                            h(41);
                            h(37);
                            Stat localfunctiondef = Stat.localfunctiondef(h(51).image, FuncBody());
                            a(localfunctiondef, a2);
                            return localfunctiondef;
                        }
                        switch (this.f1640b == -1 ? ax() : this.f1640b) {
                            case 41:
                                h(41);
                                List<Name> NameList2 = NameList();
                                switch (this.f1640b == -1 ? ax() : this.f1640b) {
                                    case 71:
                                        h(71);
                                        list = ExpList();
                                        break;
                                    default:
                                        this.g[4] = this.f;
                                        break;
                                }
                                Stat localassignment = Stat.localassignment(NameList2, list);
                                a(localassignment, a2);
                                return localassignment;
                            case 51:
                            case 75:
                                Stat ExprStat = ExprStat();
                                a(ExprStat, a2);
                                return ExprStat;
                            default:
                                this.g[7] = this.f;
                                h(-1);
                                throw new ParseException();
                        }
                }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final com.prineside.luaj.ast.Stat IfThenElse() {
        /*
            r6 = this;
            r0 = 0
            r9 = r0
            r0 = 0
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r6
            r1 = 39
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r6
            com.prineside.luaj.ast.Exp r0 = r0.Exp()
            r10 = r0
            r0 = r6
            r1 = 47
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r6
            com.prineside.luaj.ast.Block r0 = r0.Block()
            r7 = r0
        L21:
            r0 = r6
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto L30
            r0 = r6
            int r0 = r0.ax()
            goto L34
        L30:
            r0 = r6
            int r0 = r0.f1640b
        L34:
            switch(r0) {
                case 33: goto L48;
                default: goto L4b;
            }
        L48:
            goto L59
        L4b:
            r0 = r6
            int[] r0 = r0.g
            r1 = 8
            r2 = r6
            int r2 = r2.f
            r0[r1] = r2
            goto La4
        L59:
            r0 = r6
            r1 = 33
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r6
            com.prineside.luaj.ast.Exp r0 = r0.Exp()
            r11 = r0
            r0 = r6
            r1 = 47
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r6
            com.prineside.luaj.ast.Block r0 = r0.Block()
            r8 = r0
            r0 = r12
            if (r0 != 0) goto L80
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            r12 = r0
        L80:
            r0 = r13
            if (r0 != 0) goto L8e
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            r13 = r0
        L8e:
            r0 = r12
            r1 = r11
            boolean r0 = r0.add(r1)
            r0 = r13
            r1 = r8
            boolean r0 = r0.add(r1)
            goto L21
        La4:
            r0 = r6
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto Lb3
            r0 = r6
            int r0 = r0.ax()
            goto Lb7
        Lb3:
            r0 = r6
            int r0 = r0.f1640b
        Lb7:
            switch(r0) {
                case 32: goto Lc8;
                default: goto Ld7;
            }
        Lc8:
            r0 = r6
            r1 = 32
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r6
            com.prineside.luaj.ast.Block r0 = r0.Block()
            r9 = r0
            goto Le2
        Ld7:
            r0 = r6
            int[] r0 = r0.g
            r1 = 9
            r2 = r6
            int r2 = r2.f
            r0[r1] = r2
        Le2:
            r0 = r6
            r1 = 34
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r10
            r1 = r7
            r2 = r12
            r3 = r13
            r4 = r9
            com.prineside.luaj.ast.Stat r0 = com.prineside.luaj.ast.Stat.ifthenelse(r0, r1, r2, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParser.IfThenElse():com.prineside.luaj.ast.Stat");
    }

    public final Stat ReturnStat() {
        List<Exp> list = null;
        long a2 = a();
        h(45);
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 35:
            case 37:
            case 42:
            case 43:
            case 48:
            case 51:
            case 52:
            case 61:
            case 62:
            case 69:
            case 75:
            case 79:
            case 80:
            case 83:
                list = ExpList();
                break;
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 46:
            case 47:
            case 49:
            case 50:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 76:
            case 77:
            case 78:
            case 81:
            case 82:
            default:
                this.g[10] = this.f;
                break;
        }
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 70:
                h(70);
                break;
            default:
                this.g[11] = this.f;
                break;
        }
        Stat returnstat = Stat.returnstat(list);
        a(returnstat, a2);
        return returnstat;
    }

    public final Stat Label() {
        h(65);
        Token h2 = h(51);
        h(65);
        return Stat.labelstat(h2.image);
    }

    public final Stat ExprStat() {
        Stat stat = null;
        long a2 = a();
        Exp.PrimaryExp PrimaryExp = PrimaryExp();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 71:
            case 72:
                stat = Assign(a(PrimaryExp));
                break;
            default:
                this.g[12] = this.f;
                break;
        }
        if (stat == null) {
            stat = Stat.functioncall(b(PrimaryExp));
        }
        a(stat, a2);
        return stat;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final com.prineside.luaj.ast.Stat Assign(com.prineside.luaj.ast.Exp.VarExp r6) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            r1 = r0
            r7 = r1
            r1 = r6
            boolean r0 = r0.add(r1)
            r0 = r5
            long r0 = r0.a()
            r11 = r0
        L16:
            r0 = r5
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto L25
            r0 = r5
            int r0 = r0.ax()
            goto L29
        L25:
            r0 = r5
            int r0 = r0.f1640b
        L29:
            switch(r0) {
                case 72: goto L3c;
                default: goto L3f;
            }
        L3c:
            goto L4d
        L3f:
            r0 = r5
            int[] r0 = r0.g
            r1 = 13
            r2 = r5
            int r2 = r2.f
            r0[r1] = r2
            goto L64
        L4d:
            r0 = r5
            r1 = 72
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r5
            com.prineside.luaj.ast.Exp$VarExp r0 = r0.VarExp()
            r6 = r0
            r0 = r7
            r1 = r6
            boolean r0 = r0.add(r1)
            goto L16
        L64:
            r0 = r5
            r1 = 71
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r5
            java.util.List r0 = r0.ExpList()
            r6 = r0
            r0 = r7
            r1 = r6
            com.prineside.luaj.ast.Stat r0 = com.prineside.luaj.ast.Stat.assignment(r0, r1)
            r6 = r0
            r0 = r5
            r1 = r6
            r2 = r11
            r0.a(r1, r2)
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParser.Assign(com.prineside.luaj.ast.Exp$VarExp):com.prineside.luaj.ast.Stat");
    }

    public final Exp.VarExp VarExp() {
        return a(PrimaryExp());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final com.prineside.luaj.ast.FuncName FuncName() {
        /*
            r4 = this;
            r0 = r4
            r1 = 51
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r5 = r0
            com.prineside.luaj.ast.FuncName r0 = new com.prineside.luaj.ast.FuncName
            r1 = r0
            r2 = r5
            java.lang.String r2 = r2.image
            r1.<init>(r2)
            r6 = r0
        L13:
            r0 = r4
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto L22
            r0 = r4
            int r0 = r0.ax()
            goto L26
        L22:
            r0 = r4
            int r0 = r0.f1640b
        L26:
            switch(r0) {
                case 73: goto L38;
                default: goto L3b;
            }
        L38:
            goto L49
        L3b:
            r0 = r4
            int[] r0 = r0.g
            r1 = 14
            r2 = r4
            int r2 = r2.f
            r0[r1] = r2
            goto L62
        L49:
            r0 = r4
            r1 = 73
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r4
            r1 = 51
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r5 = r0
            r0 = r6
            r1 = r5
            java.lang.String r1 = r1.image
            r0.adddot(r1)
            goto L13
        L62:
            r0 = r4
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto L71
            r0 = r4
            int r0 = r0.ax()
            goto L75
        L71:
            r0 = r4
            int r0 = r0.f1640b
        L75:
            switch(r0) {
                case 74: goto L88;
                default: goto La1;
            }
        L88:
            r0 = r4
            r1 = 74
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r4
            r1 = 51
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r5 = r0
            r0 = r6
            r1 = r5
            java.lang.String r1 = r1.image
            r0.method = r1
            goto Lac
        La1:
            r0 = r4
            int[] r0 = r0.g
            r1 = 15
            r2 = r4
            int r2 = r2.f
            r0[r1] = r2
        Lac:
            r0 = r4
            r1 = r6
            r2 = r5
            r0.a(r1, r2)
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParser.FuncName():com.prineside.luaj.ast.FuncName");
    }

    public final Exp.PrimaryExp PrefixExp() {
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 51:
                Exp.NameExp nameprefix = Exp.nameprefix(h(51).image);
                a(nameprefix, a2);
                return nameprefix;
            case 75:
                h(75);
                Exp Exp = Exp();
                h(76);
                Exp.ParensExp parensprefix = Exp.parensprefix(Exp);
                a(parensprefix, a2);
                return parensprefix;
            default:
                this.g[16] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    public final Exp.PrimaryExp PrimaryExp() {
        long a2 = a();
        Exp.PrimaryExp PrefixExp = PrefixExp();
        while (true) {
            Exp.PrimaryExp primaryExp = PrefixExp;
            if (c(2)) {
                PrefixExp = PostfixOp(primaryExp);
            } else {
                a(primaryExp, a2);
                return primaryExp;
            }
        }
    }

    public final Exp.PrimaryExp PostfixOp(Exp.PrimaryExp primaryExp) {
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 61:
            case 62:
            case 75:
            case 80:
                Exp.FuncCall functionop = Exp.functionop(primaryExp, FuncArgs());
                a(functionop, a2);
                return functionop;
            case 73:
                h(73);
                Exp.FieldExp fieldop = Exp.fieldop(primaryExp, h(51).image);
                a(fieldop, a2);
                return fieldop;
            case 74:
                h(74);
                Exp.MethodCall methodop = Exp.methodop(primaryExp, h(51).image, FuncArgs());
                a(methodop, a2);
                return methodop;
            case 77:
                h(77);
                Exp Exp = Exp();
                h(78);
                Exp.IndexExp indexop = Exp.indexop(primaryExp, Exp);
                a(indexop, a2);
                return indexop;
            default:
                this.g[17] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    public final FuncArgs FuncArgs() {
        List<Exp> list = null;
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 61:
            case 62:
                FuncArgs string = FuncArgs.string(Str());
                a(string, a2);
                return string;
            case 75:
                h(75);
                switch (this.f1640b == -1 ? ax() : this.f1640b) {
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 35:
                    case 37:
                    case 42:
                    case 43:
                    case 48:
                    case 51:
                    case 52:
                    case 61:
                    case 62:
                    case 69:
                    case 75:
                    case 79:
                    case 80:
                    case 83:
                        list = ExpList();
                        break;
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 36:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 49:
                    case 50:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 70:
                    case 71:
                    case 72:
                    case 73:
                    case 74:
                    case 76:
                    case 77:
                    case 78:
                    case 81:
                    case 82:
                    default:
                        this.g[18] = this.f;
                        break;
                }
                h(76);
                FuncArgs explist = FuncArgs.explist(list);
                a(explist, a2);
                return explist;
            case 80:
                FuncArgs tableconstructor = FuncArgs.tableconstructor(TableConstructor());
                a(tableconstructor, a2);
                return tableconstructor;
            default:
                this.g[19] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    public final List<Name> NameList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Name(h(51).image));
        while (d(2)) {
            h(72);
            arrayList.add(new Name(h(51).image));
        }
        return arrayList;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final java.util.List<com.prineside.luaj.ast.Exp> ExpList() {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            r5 = r0
            r0 = r4
            com.prineside.luaj.ast.Exp r0 = r0.Exp()
            r6 = r0
            r0 = r5
            r1 = r6
            boolean r0 = r0.add(r1)
        L15:
            r0 = r4
            int r0 = r0.f1640b
            r1 = -1
            if (r0 != r1) goto L24
            r0 = r4
            int r0 = r0.ax()
            goto L28
        L24:
            r0 = r4
            int r0 = r0.f1640b
        L28:
            switch(r0) {
                case 72: goto L3c;
                default: goto L3f;
            }
        L3c:
            goto L4d
        L3f:
            r0 = r4
            int[] r0 = r0.g
            r1 = 20
            r2 = r4
            int r2 = r2.f
            r0[r1] = r2
            goto L64
        L4d:
            r0 = r4
            r1 = 72
            com.prineside.luaj.parser.Token r0 = r0.h(r1)
            r0 = r4
            com.prineside.luaj.ast.Exp r0 = r0.Exp()
            r6 = r0
            r0 = r5
            r1 = r6
            boolean r0 = r0.add(r1)
            goto L15
        L64:
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParser.ExpList():java.util.List");
    }

    public final Exp SimpleExp() {
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 61:
            case 62:
                Exp constant = Exp.constant(Str());
                a(constant, a2);
                return constant;
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 39:
            case 40:
            case 41:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 49:
            case 50:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 76:
            case 77:
            case 78:
            default:
                this.g[21] = this.f;
                h(-1);
                throw new ParseException();
            case 35:
                h(35);
                Exp constant2 = Exp.constant(LuaValue.FALSE);
                a(constant2, a2);
                return constant2;
            case 37:
                Exp anonymousfunction = Exp.anonymousfunction(FunctionCall());
                a(anonymousfunction, a2);
                return anonymousfunction;
            case 42:
                h(42);
                Exp constant3 = Exp.constant(LuaValue.NIL);
                a(constant3, a2);
                return constant3;
            case 48:
                h(48);
                Exp constant4 = Exp.constant(LuaValue.TRUE);
                a(constant4, a2);
                return constant4;
            case 51:
            case 75:
                return PrimaryExp();
            case 52:
                Exp numberconstant = Exp.numberconstant(h(52).image);
                a(numberconstant, a2);
                return numberconstant;
            case 79:
                h(79);
                Exp varargs = Exp.varargs();
                a(varargs, a2);
                return varargs;
            case 80:
                Exp tableconstructor = Exp.tableconstructor(TableConstructor());
                a(tableconstructor, a2);
                return tableconstructor;
        }
    }

    public final LuaString Str() {
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 23:
                h(23);
                return Str.longString(this.token.image);
            case 24:
                h(24);
                return Str.longString(this.token.image);
            case 25:
                h(25);
                return Str.longString(this.token.image);
            case 26:
                h(26);
                return Str.longString(this.token.image);
            case 27:
                h(27);
                return Str.longString(this.token.image);
            case 61:
                h(61);
                return Str.quoteString(this.token.image);
            case 62:
                h(62);
                return Str.charString(this.token.image);
            default:
                this.g[22] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    public final Exp Exp() {
        Exp unaryexp;
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 35:
            case 37:
            case 42:
            case 48:
            case 51:
            case 52:
            case 61:
            case 62:
            case 75:
            case 79:
            case 80:
                unaryexp = SimpleExp();
                break;
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 46:
            case 47:
            case 49:
            case 50:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 76:
            case 77:
            case 78:
            case 81:
            case 82:
            default:
                this.g[23] = this.f;
                h(-1);
                throw new ParseException();
            case 43:
            case 69:
            case 83:
                unaryexp = Exp.unaryexp(Unop(), Exp());
                break;
        }
        while (true) {
            Exp exp = unaryexp;
            if (e(2)) {
                unaryexp = Exp.binaryexp(exp, Binop(), Exp());
            } else {
                a(exp, a2);
                return exp;
            }
        }
    }

    public final FuncBody FunctionCall() {
        long a2 = a();
        h(37);
        FuncBody FuncBody = FuncBody();
        a(FuncBody, a2);
        return FuncBody;
    }

    public final FuncBody FuncBody() {
        ParList parList = null;
        long a2 = a();
        h(75);
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 51:
            case 79:
                parList = ParList();
                break;
            default:
                this.g[24] = this.f;
                break;
        }
        h(76);
        Block Block = Block();
        h(34);
        FuncBody funcBody = new FuncBody(parList, Block);
        a(funcBody, a2);
        return funcBody;
    }

    public final ParList ParList() {
        boolean z = false;
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 51:
                List<Name> NameList = NameList();
                switch (this.f1640b == -1 ? ax() : this.f1640b) {
                    case 72:
                        h(72);
                        h(79);
                        z = true;
                        break;
                    default:
                        this.g[25] = this.f;
                        break;
                }
                ParList parList = new ParList(NameList, z);
                a(parList, a2);
                return parList;
            case 79:
                h(79);
                ParList parList2 = new ParList(null, true);
                a(parList2, a2);
                return parList2;
            default:
                this.g[26] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    public final TableConstructor TableConstructor() {
        TableConstructor tableConstructor = new TableConstructor();
        long a2 = a();
        h(80);
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 35:
            case 37:
            case 42:
            case 43:
            case 48:
            case 51:
            case 52:
            case 61:
            case 62:
            case 69:
            case 75:
            case 77:
            case 79:
            case 80:
            case 83:
                tableConstructor.fields = FieldList();
                break;
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 46:
            case 47:
            case 49:
            case 50:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 76:
            case 78:
            case 81:
            case 82:
            default:
                this.g[27] = this.f;
                break;
        }
        h(81);
        a(tableConstructor, a2);
        return tableConstructor;
    }

    public final List<TableField> FieldList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Field());
        while (f(2)) {
            FieldSep();
            arrayList.add(Field());
        }
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 70:
            case 72:
                FieldSep();
                break;
            default:
                this.g[28] = this.f;
                break;
        }
        return arrayList;
    }

    public final TableField Field() {
        long a2 = a();
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 77:
                h(77);
                Exp Exp = Exp();
                h(78);
                h(71);
                TableField keyedField = TableField.keyedField(Exp, Exp());
                a(keyedField, a2);
                return keyedField;
            default:
                this.g[29] = this.f;
                if (g(2)) {
                    Token h2 = h(51);
                    h(71);
                    TableField namedField = TableField.namedField(h2.image, Exp());
                    a(namedField, a2);
                    return namedField;
                }
                switch (this.f1640b == -1 ? ax() : this.f1640b) {
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 35:
                    case 37:
                    case 42:
                    case 43:
                    case 48:
                    case 51:
                    case 52:
                    case 61:
                    case 62:
                    case 69:
                    case 75:
                    case 79:
                    case 80:
                    case 83:
                        TableField listField = TableField.listField(Exp());
                        a(listField, a2);
                        return listField;
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 36:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 49:
                    case 50:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 70:
                    case 71:
                    case 72:
                    case 73:
                    case 74:
                    case 76:
                    case 77:
                    case 78:
                    case 81:
                    case 82:
                    default:
                        this.g[30] = this.f;
                        h(-1);
                        throw new ParseException();
                }
        }
    }

    public final void FieldSep() {
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 70:
                h(70);
                return;
            case 72:
                h(72);
                return;
            default:
                this.g[31] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    public final int Binop() {
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 29:
                h(29);
                return 60;
            case 44:
                h(44);
                return 59;
            case 82:
                h(82);
                return 13;
            case 83:
                h(83);
                return 14;
            case 84:
                h(84);
                return 15;
            case 85:
                h(85);
                return 16;
            case 86:
                h(86);
                return 18;
            case 87:
                h(87);
                return 17;
            case 88:
                h(88);
                return 22;
            case 89:
                h(89);
                return 25;
            case 90:
                h(90);
                return 26;
            case 91:
                h(91);
                return 63;
            case 92:
                h(92);
                return 62;
            case 93:
                h(93);
                return 24;
            case 94:
                h(94);
                return 61;
            default:
                this.g[32] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    public final int Unop() {
        switch (this.f1640b == -1 ? ax() : this.f1640b) {
            case 43:
                h(43);
                return 20;
            case 69:
                h(69);
                return 21;
            case 83:
                h(83);
                return 19;
            default:
                this.g[33] = this.f;
                h(-1);
                throw new ParseException();
        }
    }

    /* JADX WARN: Finally extract failed */
    private boolean a(int i2) {
        this.e = 3;
        Token token = this.token;
        this.c = token;
        this.d = token;
        try {
            boolean z = !E();
            b(0, 3);
            return z;
        } catch (LookaheadSuccess unused) {
            b(0, 3);
            return true;
        } catch (Throwable th) {
            b(0, 3);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private boolean b(int i2) {
        this.e = 2;
        Token token = this.token;
        this.c = token;
        this.d = token;
        try {
            boolean z = !z();
            b(1, 2);
            return z;
        } catch (LookaheadSuccess unused) {
            b(1, 2);
            return true;
        } catch (Throwable th) {
            b(1, 2);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private boolean c(int i2) {
        this.e = 2;
        Token token = this.token;
        this.c = token;
        this.d = token;
        try {
            boolean z = !f();
            b(2, 2);
            return z;
        } catch (LookaheadSuccess unused) {
            b(2, 2);
            return true;
        } catch (Throwable th) {
            b(2, 2);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private boolean d(int i2) {
        this.e = 2;
        Token token = this.token;
        this.c = token;
        this.d = token;
        try {
            boolean z = !C();
            b(3, 2);
            return z;
        } catch (LookaheadSuccess unused) {
            b(3, 2);
            return true;
        } catch (Throwable th) {
            b(3, 2);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private boolean e(int i2) {
        this.e = 2;
        Token token = this.token;
        this.c = token;
        this.d = token;
        try {
            boolean z = !n();
            b(4, 2);
            return z;
        } catch (LookaheadSuccess unused) {
            b(4, 2);
            return true;
        } catch (Throwable th) {
            b(4, 2);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private boolean f(int i2) {
        this.e = 2;
        Token token = this.token;
        this.c = token;
        this.d = token;
        try {
            boolean z = !Z();
            b(5, 2);
            return z;
        } catch (LookaheadSuccess unused) {
            b(5, 2);
            return true;
        } catch (Throwable th) {
            b(5, 2);
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private boolean g(int i2) {
        this.e = 2;
        Token token = this.token;
        this.c = token;
        this.d = token;
        try {
            boolean z = !am();
            b(6, 2);
            return z;
        } catch (LookaheadSuccess unused) {
            b(6, 2);
            return true;
        } catch (Throwable th) {
            b(6, 2);
            throw th;
        }
    }

    private boolean b() {
        return U();
    }

    private boolean c() {
        return as();
    }

    private boolean d() {
        if (i(75)) {
            return true;
        }
        Token token = this.c;
        if (at()) {
            this.c = token;
        }
        return i(76);
    }

    private boolean e() {
        Token token = this.c;
        if (d()) {
            this.c = token;
            if (c()) {
                this.c = token;
                return b();
            }
            return false;
        }
        return false;
    }

    private boolean f() {
        return l();
    }

    private boolean g() {
        return e();
    }

    private boolean h() {
        return i(74) || i(51);
    }

    private boolean i() {
        return i(77) || u();
    }

    private boolean j() {
        return s();
    }

    private boolean k() {
        return i(73) || i(51);
    }

    private boolean l() {
        Token token = this.c;
        if (k()) {
            this.c = token;
            if (i()) {
                this.c = token;
                if (h()) {
                    this.c = token;
                    return g();
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean m() {
        return i(37);
    }

    private boolean n() {
        return Y() || u();
    }

    private boolean o() {
        return y();
    }

    private boolean p() {
        return i(69);
    }

    private boolean q() {
        return i(43);
    }

    private boolean r() {
        return i(83);
    }

    private boolean s() {
        Token token = this.c;
        if (r()) {
            this.c = token;
            if (q()) {
                this.c = token;
                return p();
            }
            return false;
        }
        return false;
    }

    private boolean t() {
        return ak();
    }

    private boolean u() {
        Token token = this.c;
        if (t()) {
            this.c = token;
            return j();
        }
        return false;
    }

    private boolean v() {
        return i(75);
    }

    private boolean w() {
        return i(44);
    }

    private boolean x() {
        return i(51);
    }

    private boolean y() {
        Token token = this.c;
        if (x()) {
            this.c = token;
            return v();
        }
        return false;
    }

    private boolean z() {
        return i(41) || i(37);
    }

    private boolean A() {
        return i(29);
    }

    private boolean B() {
        return i(94);
    }

    private boolean C() {
        return i(72) || i(51);
    }

    private boolean D() {
        return i(93);
    }

    private boolean E() {
        return i(36) || i(51) || i(71);
    }

    private boolean F() {
        return i(92);
    }

    private boolean G() {
        return i(91);
    }

    private boolean H() {
        return i(27);
    }

    private boolean I() {
        return i(90);
    }

    private boolean J() {
        return i(26);
    }

    private boolean K() {
        return i(89);
    }

    private boolean L() {
        return i(25);
    }

    private boolean M() {
        return i(88);
    }

    private boolean N() {
        return i(24);
    }

    private boolean O() {
        return i(87);
    }

    private boolean P() {
        return i(23);
    }

    private boolean Q() {
        return i(86);
    }

    private boolean R() {
        return i(62);
    }

    private boolean S() {
        return i(85);
    }

    private boolean T() {
        return i(61);
    }

    private boolean U() {
        Token token = this.c;
        if (T()) {
            this.c = token;
            if (R()) {
                this.c = token;
                if (P()) {
                    this.c = token;
                    if (N()) {
                        this.c = token;
                        if (L()) {
                            this.c = token;
                            if (J()) {
                                this.c = token;
                                return H();
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean V() {
        return i(84);
    }

    private boolean W() {
        return i(83);
    }

    private boolean X() {
        return i(82);
    }

    private boolean Y() {
        Token token = this.c;
        if (X()) {
            this.c = token;
            if (W()) {
                this.c = token;
                if (V()) {
                    this.c = token;
                    if (S()) {
                        this.c = token;
                        if (Q()) {
                            this.c = token;
                            if (O()) {
                                this.c = token;
                                if (M()) {
                                    this.c = token;
                                    if (K()) {
                                        this.c = token;
                                        if (I()) {
                                            this.c = token;
                                            if (G()) {
                                                this.c = token;
                                                if (F()) {
                                                    this.c = token;
                                                    if (D()) {
                                                        this.c = token;
                                                        if (B()) {
                                                            this.c = token;
                                                            if (A()) {
                                                                this.c = token;
                                                                return w();
                                                            }
                                                            return false;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean Z() {
        return ad() || an();
    }

    private boolean aa() {
        return o();
    }

    private boolean ab() {
        return m();
    }

    private boolean ac() {
        return as();
    }

    private boolean ad() {
        Token token = this.c;
        if (i(72)) {
            this.c = token;
            return i(70);
        }
        return false;
    }

    private boolean ae() {
        return i(79);
    }

    private boolean af() {
        return U();
    }

    private boolean ag() {
        return i(52);
    }

    private boolean ah() {
        return i(35);
    }

    private boolean ai() {
        return i(48);
    }

    private boolean aj() {
        return i(42);
    }

    private boolean ak() {
        Token token = this.c;
        if (aj()) {
            this.c = token;
            if (ai()) {
                this.c = token;
                if (ah()) {
                    this.c = token;
                    if (ag()) {
                        this.c = token;
                        if (af()) {
                            this.c = token;
                            if (ae()) {
                                this.c = token;
                                if (ac()) {
                                    this.c = token;
                                    if (ab()) {
                                        this.c = token;
                                        return aa();
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean al() {
        return u();
    }

    private boolean am() {
        return i(51) || i(71);
    }

    private boolean an() {
        Token token = this.c;
        if (ao()) {
            this.c = token;
            if (am()) {
                this.c = token;
                return al();
            }
            return false;
        }
        return false;
    }

    private boolean ao() {
        return i(77);
    }

    private boolean ap() {
        return an();
    }

    private boolean aq() {
        return u();
    }

    private boolean ar() {
        return ap();
    }

    private boolean as() {
        if (i(80)) {
            return true;
        }
        Token token = this.c;
        if (ar()) {
            this.c = token;
        }
        return i(81);
    }

    private boolean at() {
        return aq();
    }

    private static void au() {
        h = new int[]{0, -1073741824, 0, 0, 0, -1073741824, 0, 0, 0, 0, 260046848, 0, 0, 0, 0, 0, 0, 260046848, 260046848, 260046848, 0, 260046848, 260046848, 260046848, 0, 0, 0, 260046848, 0, 0, 260046848, 0, 536870912, 0};
    }

    private static void av() {
        i = new int[]{0, 803568, 8192, 0, 0, 278720, 48, 524800, 2, 1, 1612254248, 0, 0, 0, 0, 0, 524288, 1610612736, 1612254248, 1610612736, 0, 1612252200, 1610612736, 1612254248, 524288, 0, 524288, 1612254248, 0, 0, 1612254248, 0, 4096, 2048};
    }

    private static void aw() {
        j = new int[]{32, 2114, 0, 256, 128, 66, 0, 2048, 0, 0, 624672, 64, 384, 256, 512, 1024, 2048, 77312, 624672, 67584, 256, 100352, 0, 624672, 32768, 256, 32768, 632864, GLFW.GLFW_KEY_KP_0, 8192, 624672, GLFW.GLFW_KEY_KP_0, 2147221504, 524320};
    }

    public LuaParser(InputStream inputStream) {
        this(inputStream, null);
    }

    public LuaParser(InputStream inputStream, String str) {
        this.g = new int[34];
        this.k = new JJCalls[7];
        this.l = false;
        this.m = 0;
        this.n = new LookaheadSuccess((byte) 0);
        this.o = new ArrayList();
        this.q = -1;
        this.r = new int[100];
        try {
            this.f1639a = new SimpleCharStream(inputStream, str, 1, 1);
            this.token_source = new LuaParserTokenManager(this.f1639a);
            this.token = new Token();
            this.f1640b = -1;
            this.f = 0;
            for (int i2 = 0; i2 < 34; i2++) {
                this.g[i2] = -1;
            }
            for (int i3 = 0; i3 < this.k.length; i3++) {
                this.k[i3] = new JJCalls();
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void ReInit(InputStream inputStream) {
        ReInit(inputStream, null);
    }

    public void ReInit(InputStream inputStream, String str) {
        try {
            this.f1639a.ReInit(inputStream, str, 1, 1);
            this.token_source.ReInit(this.f1639a);
            this.token = new Token();
            this.f1640b = -1;
            this.f = 0;
            for (int i2 = 0; i2 < 34; i2++) {
                this.g[i2] = -1;
            }
            for (int i3 = 0; i3 < this.k.length; i3++) {
                this.k[i3] = new JJCalls();
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public LuaParser(Reader reader) {
        this.g = new int[34];
        this.k = new JJCalls[7];
        this.l = false;
        this.m = 0;
        this.n = new LookaheadSuccess((byte) 0);
        this.o = new ArrayList();
        this.q = -1;
        this.r = new int[100];
        this.f1639a = new SimpleCharStream(reader, 1, 1);
        this.token_source = new LuaParserTokenManager(this.f1639a);
        this.token = new Token();
        this.f1640b = -1;
        this.f = 0;
        for (int i2 = 0; i2 < 34; i2++) {
            this.g[i2] = -1;
        }
        for (int i3 = 0; i3 < this.k.length; i3++) {
            this.k[i3] = new JJCalls();
        }
    }

    public void ReInit(Reader reader) {
        this.f1639a.ReInit(reader, 1, 1);
        this.token_source.ReInit(this.f1639a);
        this.token = new Token();
        this.f1640b = -1;
        this.f = 0;
        for (int i2 = 0; i2 < 34; i2++) {
            this.g[i2] = -1;
        }
        for (int i3 = 0; i3 < this.k.length; i3++) {
            this.k[i3] = new JJCalls();
        }
    }

    public LuaParser(LuaParserTokenManager luaParserTokenManager) {
        this.g = new int[34];
        this.k = new JJCalls[7];
        this.l = false;
        this.m = 0;
        this.n = new LookaheadSuccess((byte) 0);
        this.o = new ArrayList();
        this.q = -1;
        this.r = new int[100];
        this.token_source = luaParserTokenManager;
        this.token = new Token();
        this.f1640b = -1;
        this.f = 0;
        for (int i2 = 0; i2 < 34; i2++) {
            this.g[i2] = -1;
        }
        for (int i3 = 0; i3 < this.k.length; i3++) {
            this.k[i3] = new JJCalls();
        }
    }

    public void ReInit(LuaParserTokenManager luaParserTokenManager) {
        this.token_source = luaParserTokenManager;
        this.token = new Token();
        this.f1640b = -1;
        this.f = 0;
        for (int i2 = 0; i2 < 34; i2++) {
            this.g[i2] = -1;
        }
        for (int i3 = 0; i3 < this.k.length; i3++) {
            this.k[i3] = new JJCalls();
        }
    }

    private Token h(int i2) {
        Token token = this.token;
        if (token.next != null) {
            this.token = this.token.next;
        } else {
            Token token2 = this.token;
            Token nextToken = this.token_source.getNextToken();
            token2.next = nextToken;
            this.token = nextToken;
        }
        this.f1640b = -1;
        if (this.token.kind == i2) {
            this.f++;
            int i3 = this.m + 1;
            this.m = i3;
            if (i3 > 100) {
                this.m = 0;
                for (int i4 = 0; i4 < this.k.length; i4++) {
                    JJCalls jJCalls = this.k[i4];
                    while (true) {
                        JJCalls jJCalls2 = jJCalls;
                        if (jJCalls2 != null) {
                            if (jJCalls2.f1641a < this.f) {
                                jJCalls2.f1642b = null;
                            }
                            jJCalls = jJCalls2.d;
                        }
                    }
                }
            }
            return this.token;
        }
        this.token = token;
        this.q = i2;
        throw generateParseException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/parser/LuaParser$LookaheadSuccess.class */
    public static final class LookaheadSuccess extends Error {
        private LookaheadSuccess() {
        }

        /* synthetic */ LookaheadSuccess(byte b2) {
            this();
        }
    }

    private boolean i(int i2) {
        Token token;
        if (this.c == this.d) {
            this.e--;
            if (this.c.next == null) {
                Token token2 = this.c;
                Token nextToken = this.token_source.getNextToken();
                token2.next = nextToken;
                this.c = nextToken;
                this.d = nextToken;
            } else {
                Token token3 = this.c.next;
                this.c = token3;
                this.d = token3;
            }
        } else {
            this.c = this.c.next;
        }
        if (this.l) {
            int i3 = 0;
            Token token4 = this.token;
            while (true) {
                token = token4;
                if (token == null || token == this.c) {
                    break;
                }
                i3++;
                token4 = token.next;
            }
            if (token != null) {
                a(i2, i3);
            }
        }
        if (this.c.kind != i2) {
            return true;
        }
        if (this.e == 0 && this.c == this.d) {
            throw this.n;
        }
        return false;
    }

    public final Token getNextToken() {
        if (this.token.next != null) {
            this.token = this.token.next;
        } else {
            Token token = this.token;
            Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.f1640b = -1;
        this.f++;
        return this.token;
    }

    public final Token getToken(int i2) {
        Token token;
        Token token2 = this.token;
        for (int i3 = 0; i3 < i2; i3++) {
            if (token2.next != null) {
                token = token2.next;
            } else {
                Token nextToken = this.token_source.getNextToken();
                token = nextToken;
                token2.next = nextToken;
            }
            token2 = token;
        }
        return token2;
    }

    private int ax() {
        Token token = this.token.next;
        this.jj_nt = token;
        if (token == null) {
            Token token2 = this.token;
            Token nextToken = this.token_source.getNextToken();
            token2.next = nextToken;
            int i2 = nextToken.kind;
            this.f1640b = i2;
            return i2;
        }
        int i3 = this.jj_nt.kind;
        this.f1640b = i3;
        return i3;
    }

    private void a(int i2, int i3) {
        int i4;
        if (i3 >= 100) {
            return;
        }
        if (i3 == this.s + 1) {
            int[] iArr = this.r;
            int i5 = this.s;
            this.s = i5 + 1;
            iArr[i5] = i2;
            return;
        }
        if (this.s != 0) {
            this.p = new int[this.s];
            for (int i6 = 0; i6 < this.s; i6++) {
                this.p[i6] = this.r[i6];
            }
            Iterator it = this.o.iterator();
            loop1: while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int[] iArr2 = (int[]) it.next();
                if (iArr2.length == this.p.length) {
                    for (0; i4 < this.p.length; i4 + 1) {
                        i4 = iArr2[i4] == this.p[i4] ? i4 + 1 : 0;
                    }
                    this.o.add(this.p);
                    break loop1;
                }
            }
            if (i3 != 0) {
                int[] iArr3 = this.r;
                this.s = i3;
                iArr3[i3 - 1] = i2;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v16, types: [int[], int[][]] */
    public ParseException generateParseException() {
        this.o.clear();
        boolean[] zArr = new boolean[95];
        if (this.q >= 0) {
            zArr[this.q] = true;
            this.q = -1;
        }
        for (int i2 = 0; i2 < 34; i2++) {
            if (this.g[i2] == this.f) {
                for (int i3 = 0; i3 < 32; i3++) {
                    if ((h[i2] & (1 << i3)) != 0) {
                        zArr[i3] = true;
                    }
                    if ((i[i2] & (1 << i3)) != 0) {
                        zArr[i3 + 32] = true;
                    }
                    if ((j[i2] & (1 << i3)) != 0) {
                        zArr[i3 + 64] = true;
                    }
                }
            }
        }
        for (int i4 = 0; i4 < 95; i4++) {
            if (zArr[i4]) {
                this.p = new int[1];
                this.p[0] = i4;
                this.o.add(this.p);
            }
        }
        this.s = 0;
        ay();
        a(0, 0);
        ?? r0 = new int[this.o.size()];
        for (int i5 = 0; i5 < this.o.size(); i5++) {
            r0[i5] = (int[]) this.o.get(i5);
        }
        return new ParseException(this.token, r0, tokenImage);
    }

    public final void enable_tracing() {
    }

    public final void disable_tracing() {
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0035. Please report as an issue. */
    private void ay() {
        JJCalls jJCalls;
        this.l = true;
        for (int i2 = 0; i2 < 7; i2++) {
            try {
                JJCalls jJCalls2 = this.k[i2];
                do {
                    if (jJCalls2.f1641a > this.f) {
                        this.e = jJCalls2.c;
                        Token token = jJCalls2.f1642b;
                        this.c = token;
                        this.d = token;
                        switch (i2) {
                            case 0:
                                E();
                                break;
                            case 1:
                                z();
                                break;
                            case 2:
                                f();
                                break;
                            case 3:
                                C();
                                break;
                            case 4:
                                n();
                                break;
                            case 5:
                                Z();
                                break;
                            case 6:
                                am();
                                break;
                        }
                    }
                    jJCalls = jJCalls2.d;
                    jJCalls2 = jJCalls;
                } while (jJCalls != null);
            } catch (LookaheadSuccess unused) {
            }
        }
        this.l = false;
    }

    private void b(int i2, int i3) {
        JJCalls jJCalls;
        JJCalls jJCalls2 = this.k[i2];
        while (true) {
            jJCalls = jJCalls2;
            if (jJCalls.f1641a <= this.f) {
                break;
            }
            if (jJCalls.d == null) {
                JJCalls jJCalls3 = new JJCalls();
                jJCalls.d = jJCalls3;
                jJCalls = jJCalls3;
                break;
            }
            jJCalls2 = jJCalls.d;
        }
        jJCalls.f1641a = (this.f + i3) - this.e;
        jJCalls.f1642b = this.token;
        jJCalls.c = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/parser/LuaParser$JJCalls.class */
    public static final class JJCalls {

        /* renamed from: a, reason: collision with root package name */
        int f1641a;

        /* renamed from: b, reason: collision with root package name */
        Token f1642b;
        int c;
        JJCalls d;

        JJCalls() {
        }
    }
}
