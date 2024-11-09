package com.prineside.luaj.ast;

import com.prineside.luaj.LuaValue;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp.class */
public abstract class Exp extends SyntaxElement {
    public abstract void accept(Visitor visitor);

    public static Exp constant(LuaValue luaValue) {
        return new Constant(luaValue);
    }

    public static Exp numberconstant(String str) {
        return new Constant(LuaValue.valueOf(str).tonumber());
    }

    public static Exp varargs() {
        return new VarargsExp();
    }

    public static Exp tableconstructor(TableConstructor tableConstructor) {
        return tableConstructor;
    }

    public static Exp unaryexp(int i, Exp exp) {
        if (exp instanceof BinopExp) {
            BinopExp binopExp = (BinopExp) exp;
            if (b(i) > b(binopExp.op)) {
                return binaryexp(unaryexp(i, binopExp.lhs), binopExp.op, binopExp.rhs);
            }
        }
        return new UnopExp(i, exp);
    }

    public static Exp binaryexp(Exp exp, int i, Exp exp2) {
        if (exp instanceof UnopExp) {
            UnopExp unopExp = (UnopExp) exp;
            if (b(i) > b(unopExp.op)) {
                return unaryexp(unopExp.op, binaryexp(unopExp.rhs, i, exp2));
            }
        }
        if (exp instanceof BinopExp) {
            BinopExp binopExp = (BinopExp) exp;
            if (b(i) > b(binopExp.op) || (b(i) == b(binopExp.op) && a(i))) {
                return binaryexp(binopExp.lhs, binopExp.op, binaryexp(binopExp.rhs, i, exp2));
            }
        }
        if (exp2 instanceof BinopExp) {
            BinopExp binopExp2 = (BinopExp) exp2;
            if (b(i) > b(binopExp2.op) || (b(i) == b(binopExp2.op) && !a(i))) {
                return binaryexp(binaryexp(exp, i, binopExp2.lhs), binopExp2.op, binopExp2.rhs);
            }
        }
        return new BinopExp(exp, i, exp2);
    }

    private static boolean a(int i) {
        switch (i) {
            case 18:
            case 22:
                return true;
            default:
                return false;
        }
    }

    private static int b(int i) {
        switch (i) {
            case 13:
            case 14:
                return 4;
            case 15:
            case 16:
            case 17:
                return 5;
            case 18:
                return 7;
            case 19:
            case 20:
            case 21:
                return 6;
            case 22:
                return 3;
            case 23:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            default:
                throw new IllegalStateException("precedence of bad op " + i);
            case 24:
            case 25:
            case 26:
            case 61:
            case 62:
            case 63:
                return 2;
            case 59:
                return 0;
            case 60:
                return 1;
        }
    }

    public static Exp anonymousfunction(FuncBody funcBody) {
        return new AnonFuncDef(funcBody);
    }

    public static NameExp nameprefix(String str) {
        return new NameExp(str);
    }

    public static ParensExp parensprefix(Exp exp) {
        return new ParensExp(exp);
    }

    public static IndexExp indexop(PrimaryExp primaryExp, Exp exp) {
        return new IndexExp(primaryExp, exp);
    }

    public static FieldExp fieldop(PrimaryExp primaryExp, String str) {
        return new FieldExp(primaryExp, str);
    }

    public static FuncCall functionop(PrimaryExp primaryExp, FuncArgs funcArgs) {
        return new FuncCall(primaryExp, funcArgs);
    }

    public static MethodCall methodop(PrimaryExp primaryExp, String str, FuncArgs funcArgs) {
        return new MethodCall(primaryExp, str, funcArgs);
    }

    public boolean isvarexp() {
        return false;
    }

    public boolean isfunccall() {
        return false;
    }

    public boolean isvarargexp() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$PrimaryExp.class */
    public static abstract class PrimaryExp extends Exp {
        @Override // com.prineside.luaj.ast.Exp
        public boolean isvarexp() {
            return false;
        }

        @Override // com.prineside.luaj.ast.Exp
        public boolean isfunccall() {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$VarExp.class */
    public static abstract class VarExp extends PrimaryExp {
        @Override // com.prineside.luaj.ast.Exp.PrimaryExp, com.prineside.luaj.ast.Exp
        public boolean isvarexp() {
            return true;
        }

        public void markHasAssignment() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$NameExp.class */
    public static class NameExp extends VarExp {
        public final Name name;

        public NameExp(String str) {
            this.name = new Name(str);
        }

        @Override // com.prineside.luaj.ast.Exp.VarExp
        public void markHasAssignment() {
            this.name.variable.hasassignments = true;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$ParensExp.class */
    public static class ParensExp extends PrimaryExp {
        public final Exp exp;

        public ParensExp(Exp exp) {
            this.exp = exp;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$FieldExp.class */
    public static class FieldExp extends VarExp {
        public final PrimaryExp lhs;
        public final Name name;

        public FieldExp(PrimaryExp primaryExp, String str) {
            this.lhs = primaryExp;
            this.name = new Name(str);
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$IndexExp.class */
    public static class IndexExp extends VarExp {
        public final PrimaryExp lhs;
        public final Exp exp;

        public IndexExp(PrimaryExp primaryExp, Exp exp) {
            this.lhs = primaryExp;
            this.exp = exp;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$FuncCall.class */
    public static class FuncCall extends PrimaryExp {
        public final PrimaryExp lhs;
        public final FuncArgs args;

        public FuncCall(PrimaryExp primaryExp, FuncArgs funcArgs) {
            this.lhs = primaryExp;
            this.args = funcArgs;
        }

        @Override // com.prineside.luaj.ast.Exp.PrimaryExp, com.prineside.luaj.ast.Exp
        public boolean isfunccall() {
            return true;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        @Override // com.prineside.luaj.ast.Exp
        public boolean isvarargexp() {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$MethodCall.class */
    public static class MethodCall extends FuncCall {
        public final String name;

        public MethodCall(PrimaryExp primaryExp, String str, FuncArgs funcArgs) {
            super(primaryExp, funcArgs);
            this.name = new String(str);
        }

        @Override // com.prineside.luaj.ast.Exp.FuncCall, com.prineside.luaj.ast.Exp.PrimaryExp, com.prineside.luaj.ast.Exp
        public boolean isfunccall() {
            return true;
        }

        @Override // com.prineside.luaj.ast.Exp.FuncCall, com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$Constant.class */
    public static class Constant extends Exp {
        public final LuaValue value;

        public Constant(LuaValue luaValue) {
            this.value = luaValue;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$VarargsExp.class */
    public static class VarargsExp extends Exp {
        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        @Override // com.prineside.luaj.ast.Exp
        public boolean isvarargexp() {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$UnopExp.class */
    public static class UnopExp extends Exp {
        public final int op;
        public final Exp rhs;

        public UnopExp(int i, Exp exp) {
            this.op = i;
            this.rhs = exp;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$BinopExp.class */
    public static class BinopExp extends Exp {
        public final Exp lhs;
        public final Exp rhs;
        public final int op;

        public BinopExp(Exp exp, int i, Exp exp2) {
            this.lhs = exp;
            this.op = i;
            this.rhs = exp2;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Exp$AnonFuncDef.class */
    public static class AnonFuncDef extends Exp {
        public final FuncBody body;

        public AnonFuncDef(FuncBody funcBody) {
            this.body = funcBody;
        }

        @Override // com.prineside.luaj.ast.Exp
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
}
