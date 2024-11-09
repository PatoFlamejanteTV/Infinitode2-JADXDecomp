package com.prineside.tdi2.managers.script;

import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.luaj.Globals;
import com.prineside.luaj.LoadState;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.compiler.LuaC;
import com.prineside.luaj.lib.Bit32Lib;
import com.prineside.luaj.lib.DebugLib;
import com.prineside.luaj.lib.jse.JseBaseLib;
import com.prineside.luaj.lib.jse.JseMathLib;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/MathEnvironment.class */
public class MathEnvironment {

    /* renamed from: b, reason: collision with root package name */
    private final ObjectMap<String, PreparedMathExpression> f2554b = new ObjectMap<>();

    /* renamed from: a, reason: collision with root package name */
    private final Globals f2553a = new Globals();

    public MathEnvironment() {
        this.f2553a.load(new JseBaseLib());
        this.f2553a.load(new Bit32Lib());
        this.f2553a.load(new JseMathLib());
        this.f2553a.load(new DebugLib());
        LoadState.install(this.f2553a);
        LuaC luaC = new LuaC();
        this.f2553a.compiler = luaC;
        this.f2553a.loader = luaC;
    }

    public synchronized PreparedMathExpression prepare(String str) {
        int indexOf = str.indexOf("->");
        if (indexOf == -1) {
            throw new IllegalArgumentException("Must be \"args -> function\", got: " + str);
        }
        String trim = str.substring(0, indexOf).trim();
        String replaceAll = str.substring(indexOf + 2).trim().replaceAll(";", SequenceUtils.EOL);
        return new PreparedMathExpression((LuaFunction) this.f2553a.load("return function(" + trim + ")\n    " + (replaceAll.contains("return ") ? "" : "return ") + replaceAll + "\nend", "Prepared math expression").call(), (byte) 0);
    }

    public synchronized PreparedMathExpression cached(String str) {
        PreparedMathExpression preparedMathExpression = this.f2554b.get(str);
        PreparedMathExpression preparedMathExpression2 = preparedMathExpression;
        if (preparedMathExpression == null) {
            preparedMathExpression2 = prepare(str);
            this.f2554b.put(str, preparedMathExpression2);
        }
        return preparedMathExpression2;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/MathEnvironment$PreparedMathExpression.class */
    public static final class PreparedMathExpression {

        /* renamed from: a, reason: collision with root package name */
        private final LuaFunction f2555a;

        /* synthetic */ PreparedMathExpression(LuaFunction luaFunction, byte b2) {
            this(luaFunction);
        }

        private PreparedMathExpression(LuaFunction luaFunction) {
            this.f2555a = luaFunction;
        }

        @IgnoreMethodOverloadLuaDefWarning
        public final double eval(double d) {
            double d2;
            synchronized (this.f2555a) {
                d2 = this.f2555a.call(LuaValue.cDouble(d)).todouble();
            }
            return d2;
        }

        @IgnoreMethodOverloadLuaDefWarning
        public final double eval(double d, double d2) {
            double d3;
            synchronized (this.f2555a) {
                d3 = this.f2555a.call(LuaValue.cDouble(d), LuaValue.cDouble(d2)).todouble();
            }
            return d3;
        }

        @IgnoreMethodOverloadLuaDefWarning
        public final double eval(double d, double d2, double d3) {
            double d4;
            synchronized (this.f2555a) {
                d4 = this.f2555a.call(LuaValue.cDouble(d), LuaValue.cDouble(d2), LuaValue.cDouble(d3)).todouble();
            }
            return d4;
        }

        @IgnoreMethodOverloadLuaDefWarning
        public final double eval() {
            double d;
            synchronized (this.f2555a) {
                d = this.f2555a.call().todouble();
            }
            return d;
        }
    }
}
