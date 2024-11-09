package com.prineside.luaj;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.debug.CallFrame;
import com.prineside.luaj.lib.DebugLib;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import net.bytebuddy.description.type.TypeDescription;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaClosure.class */
public final class LuaClosure extends LuaFunction {

    /* renamed from: b, reason: collision with root package name */
    private static final UpValue[] f1478b;
    public final FPrototype p;

    /* renamed from: a, reason: collision with root package name */
    @Null
    final Globals f1479a;
    private final UpValue[] c;
    public static final DeepClassComparator<LuaClosure> CLASS_COMPARATOR;

    /* synthetic */ LuaClosure(FPrototype fPrototype, Globals globals, UpValue[] upValueArr, byte b2) {
        this(fPrototype, globals, upValueArr);
    }

    static {
        TLog.forClass(LuaClosure.class);
        UpValue[] upValueArr = new UpValue[0];
        f1478b = upValueArr;
        SyncChecker.addSyncShareableObject(upValueArr);
        CLASS_COMPARATOR = new DeepClassComparator<LuaClosure>() { // from class: com.prineside.luaj.LuaClosure.1
            @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
            public Class<LuaClosure> forClass() {
                return LuaClosure.class;
            }

            @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
            public void compare(LuaClosure luaClosure, LuaClosure luaClosure2, DeepClassComparisonConfig deepClassComparisonConfig) {
                deepClassComparisonConfig.addPrefix("{", luaClosure.tojstring(), "}");
                deepClassComparisonConfig.addPrefix(".p");
                SyncChecker.compareObjects(luaClosure.p, luaClosure2.p, deepClassComparisonConfig);
                deepClassComparisonConfig.popPrefix(1);
                deepClassComparisonConfig.addPrefix(".globals");
                SyncChecker.compareObjects(luaClosure.f1479a, luaClosure2.f1479a, deepClassComparisonConfig);
                deepClassComparisonConfig.popPrefix(1);
                deepClassComparisonConfig.addPrefix(".upValues");
                SyncChecker.compareObjects(luaClosure.c, luaClosure2.c, deepClassComparisonConfig);
                deepClassComparisonConfig.popPrefix(1);
                deepClassComparisonConfig.popPrefix(3);
            }
        };
        SyncChecker.CLASS_COMPARATORS.add(CLASS_COMPARATOR);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaClosure$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<LuaClosure> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, LuaClosure luaClosure) {
            kryo.writeClassAndObject(output, luaClosure.p);
            kryo.writeObjectOrNull(output, luaClosure.f1479a, Globals.class);
            kryo.writeObject(output, luaClosure.c);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public LuaClosure read2(Kryo kryo, Input input, Class<? extends LuaClosure> cls) {
            return new LuaClosure((FPrototype) kryo.readClassAndObject(input), (Globals) kryo.readObjectOrNull(input, Globals.class), (UpValue[]) kryo.readObject(input, UpValue[].class), (byte) 0);
        }
    }

    private LuaClosure(FPrototype fPrototype, Globals globals, UpValue[] upValueArr) {
        upValueArr = upValueArr == null ? f1478b : upValueArr;
        this.p = fPrototype;
        this.f1479a = globals;
        this.c = upValueArr;
    }

    public LuaClosure(FPrototype fPrototype, LuaValue luaValue) {
        this.p = fPrototype;
        if (fPrototype.upvalues == null || fPrototype.upvalues.length == 0) {
            this.c = f1478b;
        } else {
            this.c = new UpValue[fPrototype.upvalues.length];
            this.c[0] = new UpValue(new LuaValue[]{luaValue}, 0);
        }
        this.f1479a = luaValue instanceof Globals ? (Globals) luaValue : null;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isclosure() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaClosure optclosure(LuaClosure luaClosure) {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaClosure checkclosure() {
        return this;
    }

    @Override // com.prineside.luaj.LuaFunction, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public final String tojstring() {
        return "function: " + this.p.toString();
    }

    private LuaValue[] d() {
        int i = this.p.maxstacksize;
        LuaValue[] luaValueArr = new LuaValue[i];
        System.arraycopy(NILS, 0, luaValueArr, 0, i);
        return luaValueArr;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call() {
        return a(d(), NONE).arg1();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue) {
        LuaValue[] d = d();
        if (this.p.numparams == 0) {
            return a(d, luaValue).arg1();
        }
        d[0] = luaValue;
        return a(d, NONE).arg1();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        LuaValue[] d = d();
        switch (this.p.numparams) {
            case 0:
                if (this.p.is_vararg) {
                    return a(d, LuaValue.varargsOf(luaValue, luaValue2)).arg1();
                }
                return a(d, NONE).arg1();
            case 1:
                d[0] = luaValue;
                return a(d, luaValue2).arg1();
            default:
                d[0] = luaValue;
                d[1] = luaValue2;
                return a(d, NONE).arg1();
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        LuaValue[] d = d();
        switch (this.p.numparams) {
            case 0:
                if (this.p.is_vararg) {
                    return a(d, LuaValue.varargsOf(luaValue, luaValue2, luaValue3)).arg1();
                }
                return a(d, NONE).arg1();
            case 1:
                d[0] = luaValue;
                if (this.p.is_vararg) {
                    return a(d, LuaValue.varargsOf(luaValue2, luaValue3)).arg1();
                }
                return a(d, NONE).arg1();
            case 2:
                d[0] = luaValue;
                d[1] = luaValue2;
                return a(d, luaValue3).arg1();
            default:
                d[0] = luaValue;
                d[1] = luaValue2;
                d[2] = luaValue3;
                return a(d, NONE).arg1();
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final Varargs invoke(Varargs varargs) {
        return onInvoke(varargs).eval();
    }

    @Override // com.prineside.luaj.LuaValue
    public final Varargs onInvoke(Varargs varargs) {
        LuaValue[] d = d();
        for (int i = 0; i < this.p.numparams; i++) {
            d[i] = varargs.arg(i + 1);
        }
        if (this.p.is_vararg) {
            int i2 = this.p.numparams + 1;
            if (i2 > 1) {
                return a(d, varargs.subargs(i2));
            }
            return a(d, varargs);
        }
        return a(d, NONE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:287:0x0bac, code lost:            if (r0 != false) goto L324;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v161 */
    /* JADX WARN: Type inference failed for: r0v165 */
    /* JADX WARN: Type inference failed for: r0v177 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19, types: [com.prineside.luaj.LuaError] */
    /* JADX WARN: Type inference failed for: r0v197 */
    /* JADX WARN: Type inference failed for: r0v205 */
    /* JADX WARN: Type inference failed for: r0v212, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v215, types: [int] */
    /* JADX WARN: Type inference failed for: r0v223, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v238, types: [int] */
    /* JADX WARN: Type inference failed for: r0v250 */
    /* JADX WARN: Type inference failed for: r0v271, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r0v273, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v274 */
    /* JADX WARN: Type inference failed for: r0v275 */
    /* JADX WARN: Type inference failed for: r0v276 */
    /* JADX WARN: Type inference failed for: r0v277 */
    /* JADX WARN: Type inference failed for: r0v280, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v283, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v286, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v289, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v291, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v292, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r0v294, types: [int] */
    /* JADX WARN: Type inference failed for: r0v297, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r0v299, types: [int] */
    /* JADX WARN: Type inference failed for: r0v302, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v303 */
    /* JADX WARN: Type inference failed for: r0v306, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v312, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v320, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v328, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v333 */
    /* JADX WARN: Type inference failed for: r0v336 */
    /* JADX WARN: Type inference failed for: r0v350 */
    /* JADX WARN: Type inference failed for: r0v355 */
    /* JADX WARN: Type inference failed for: r0v358 */
    /* JADX WARN: Type inference failed for: r0v359 */
    /* JADX WARN: Type inference failed for: r0v360 */
    /* JADX WARN: Type inference failed for: r0v361 */
    /* JADX WARN: Type inference failed for: r0v362 */
    /* JADX WARN: Type inference failed for: r0v363 */
    /* JADX WARN: Type inference failed for: r0v364 */
    /* JADX WARN: Type inference failed for: r0v365 */
    /* JADX WARN: Type inference failed for: r0v366 */
    /* JADX WARN: Type inference failed for: r0v368 */
    /* JADX WARN: Type inference failed for: r0v369 */
    /* JADX WARN: Type inference failed for: r0v371, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v373, types: [com.prineside.luaj.UpValue[]] */
    /* JADX WARN: Type inference failed for: r0v374, types: [com.prineside.luaj.UpValue] */
    /* JADX WARN: Type inference failed for: r0v378, types: [com.prineside.luaj.LuaValue] */
    /* JADX WARN: Type inference failed for: r0v379 */
    /* JADX WARN: Type inference failed for: r0v380 */
    /* JADX WARN: Type inference failed for: r0v381 */
    /* JADX WARN: Type inference failed for: r0v384 */
    /* JADX WARN: Type inference failed for: r0v388 */
    /* JADX WARN: Type inference failed for: r0v395 */
    /* JADX WARN: Type inference failed for: r0v396 */
    /* JADX WARN: Type inference failed for: r0v397 */
    /* JADX WARN: Type inference failed for: r0v398 */
    /* JADX WARN: Type inference failed for: r0v405 */
    /* JADX WARN: Type inference failed for: r0v406 */
    /* JADX WARN: Type inference failed for: r0v407 */
    /* JADX WARN: Type inference failed for: r0v408 */
    /* JADX WARN: Type inference failed for: r0v409 */
    /* JADX WARN: Type inference failed for: r0v60, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r1v166, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r2v270 */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r2v62, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r3v29, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r4v10, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.prineside.luaj.Varargs] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.prineside.luaj.Varargs a(com.prineside.luaj.LuaValue[] r10, com.prineside.luaj.Varargs r11) {
        /*
            Method dump skipped, instructions count: 3700
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.LuaClosure.a(com.prineside.luaj.LuaValue[], com.prineside.luaj.Varargs):com.prineside.luaj.Varargs");
    }

    private String a(String str, int i) {
        if (this.f1479a == null) {
            return str;
        }
        LuaValue errorFunc = this.f1479a.getErrorFunc();
        if (errorFunc == null) {
            DebugLib debugLib = this.f1479a.getDebugLib();
            if (debugLib != null) {
                return str + SequenceUtils.EOL + debugLib.traceback(i);
            }
            return str;
        }
        this.f1479a.setErrorFunc(null);
        try {
            return errorFunc.call(LuaValue.valueOf(str)).tojstring();
        } catch (Throwable unused) {
            return "error in error handling";
        } finally {
            this.f1479a.setErrorFunc(errorFunc);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v32, types: [int] */
    private void a(LuaError luaError, FPrototype fPrototype, int i) {
        DebugLib debugLib;
        String str = TypeDescription.Generic.OfWildcardType.SYMBOL;
        short s = -1;
        CallFrame callFrame = null;
        if (this.f1479a != null && (debugLib = this.f1479a.getDebugLib()) != null) {
            CallFrame callFrame2 = debugLib.getCallFrame(luaError.f1480a);
            callFrame = callFrame2;
            if (callFrame2 != null) {
                str = callFrame.shortsource();
                s = callFrame.currentline();
            }
        }
        if (callFrame == null) {
            str = fPrototype.source != null ? fPrototype.source.tojstring() : TypeDescription.Generic.OfWildcardType.SYMBOL;
            s = (fPrototype.lineinfo == null || i < 0 || i >= fPrototype.lineinfo.length) ? (short) -1 : fPrototype.lineinfo[i];
        }
        luaError.f1481b = str + ":" + ((int) s);
        luaError.c = a(luaError.getMessage(), luaError.f1480a);
    }

    private static UpValue a(LuaValue[] luaValueArr, short s, UpValue[] upValueArr) {
        int length = upValueArr.length;
        for (UpValue upValue : upValueArr) {
            if (upValue != null && upValue.f1509a == s) {
                return upValue;
            }
        }
        for (int i = 0; i < length; i++) {
            if (upValueArr[i] == null) {
                UpValue upValue2 = new UpValue(luaValueArr, s);
                upValueArr[i] = upValue2;
                return upValue2;
            }
        }
        error("No space for upvalue");
        return null;
    }

    @Override // com.prineside.luaj.LuaFunction
    public final String name() {
        return "<" + this.p.shortsource() + ":" + ((int) this.p.linedefined) + ">";
    }
}
