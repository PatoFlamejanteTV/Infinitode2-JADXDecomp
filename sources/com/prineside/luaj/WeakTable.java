package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaTable;
import com.prineside.tdi2.utils.REGS;
import java.lang.ref.WeakReference;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/WeakTable.class */
public class WeakTable implements KryoSerializable, Metatable {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1518a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1519b;
    private LuaValue c;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeBoolean(this.f1518a);
        output.writeBoolean(this.f1519b);
        kryo.writeClassAndObject(output, this.c);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f1518a = input.readBoolean();
        this.f1519b = input.readBoolean();
        this.c = (LuaValue) kryo.readClassAndObject(input);
    }

    public static LuaTable make(boolean z, boolean z2) {
        LuaString valueOf;
        if (z && z2) {
            valueOf = LuaString.valueOf("kv");
        } else if (z) {
            valueOf = LuaString.valueOf("k");
        } else if (z2) {
            valueOf = LuaString.valueOf("v");
        } else {
            return LuaTable.tableOf();
        }
        LuaTable tableOf = LuaTable.tableOf();
        tableOf.setmetatable(LuaTable.tableOf(new LuaValue[]{LuaValue.MODE, valueOf}));
        return tableOf;
    }

    private WeakTable() {
    }

    public WeakTable(boolean z, boolean z2, LuaValue luaValue) {
        this.f1518a = z;
        this.f1519b = z2;
        this.c = luaValue;
    }

    @Override // com.prineside.luaj.Metatable
    public boolean useWeakKeys() {
        return this.f1518a;
    }

    @Override // com.prineside.luaj.Metatable
    public boolean useWeakValues() {
        return this.f1519b;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue toLuaValue() {
        return this.c;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaTable.Slot entry(LuaValue luaValue, LuaValue luaValue2) {
        LuaValue strongvalue = luaValue2.strongvalue();
        if (strongvalue == null) {
            return null;
        }
        if (this.f1518a && !luaValue.isnumber() && !luaValue.isstring() && !luaValue.isboolean()) {
            return (!this.f1519b || strongvalue.isnumber() || strongvalue.isstring() || strongvalue.isboolean()) ? new WeakKeySlot(luaValue, strongvalue, null) : new WeakKeyAndValueSlot(luaValue, strongvalue, null);
        }
        if (this.f1519b && !strongvalue.isnumber() && !strongvalue.isstring() && !strongvalue.isboolean()) {
            return new WeakValueSlot(luaValue, strongvalue, null);
        }
        return LuaTable.a(luaValue, strongvalue);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/WeakTable$WeakSlot.class */
    public static abstract class WeakSlot implements KryoSerializable, LuaTable.Slot {

        /* renamed from: a, reason: collision with root package name */
        protected Object f1520a;

        /* renamed from: b, reason: collision with root package name */
        protected Object f1521b;
        private LuaTable.Slot c;

        public abstract int keyindex(int i);

        public abstract LuaTable.Slot set(LuaValue luaValue);

        protected abstract WeakSlot a(LuaTable.Slot slot);

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1520a);
            kryo.writeClassAndObject(output, this.f1521b);
            kryo.writeClassAndObject(output, this.c);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1520a = kryo.readClassAndObject(input);
            this.f1521b = kryo.readClassAndObject(input);
            this.c = (LuaTable.Slot) kryo.readClassAndObject(input);
        }

        protected WeakSlot() {
        }

        protected WeakSlot(Object obj, Object obj2, LuaTable.Slot slot) {
            this.f1520a = obj;
            this.f1521b = obj2;
            this.c = slot;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public LuaTable.StrongSlot first() {
            LuaValue strongkey = strongkey();
            LuaValue strongvalue = strongvalue();
            if (strongkey != null && strongvalue != null) {
                return new LuaTable.NormalEntry(strongkey, strongvalue);
            }
            this.f1520a = null;
            this.f1521b = null;
            return null;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public LuaTable.StrongSlot find(LuaValue luaValue) {
            LuaTable.StrongSlot first = first();
            if (first != null) {
                return first.find(luaValue);
            }
            return null;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public boolean keyeq(LuaValue luaValue) {
            LuaTable.StrongSlot first = first();
            return first != null && first.keyeq(luaValue);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public LuaTable.Slot rest() {
            return this.c;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public int arraykey(int i) {
            return 0;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public LuaTable.Slot set(LuaTable.StrongSlot strongSlot, LuaValue luaValue) {
            LuaValue strongkey = strongkey();
            if (strongkey != null && strongSlot.find(strongkey) != null) {
                return set(luaValue);
            }
            if (strongkey != null) {
                this.c = this.c.set(strongSlot, luaValue);
                return this;
            }
            return this.c.set(strongSlot, luaValue);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public LuaTable.Slot add(LuaTable.Slot slot) {
            this.c = this.c != null ? this.c.add(slot) : slot;
            if (strongkey() != null && strongvalue() != null) {
                return this;
            }
            return this.c;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public LuaTable.Slot remove(LuaTable.StrongSlot strongSlot) {
            LuaValue strongkey = strongkey();
            if (strongkey == null) {
                return this.c.remove(strongSlot);
            }
            if (strongSlot.keyeq(strongkey)) {
                this.f1521b = null;
                return this;
            }
            this.c = this.c.remove(strongSlot);
            return this;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public LuaTable.Slot relink(LuaTable.Slot slot) {
            if (strongkey() != null && strongvalue() != null) {
                if (slot == null && this.c == null) {
                    return this;
                }
                return a(slot);
            }
            return slot;
        }

        public LuaValue strongkey() {
            return (LuaValue) this.f1520a;
        }

        public LuaValue strongvalue() {
            return (LuaValue) this.f1521b;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/WeakTable$WeakKeySlot.class */
    public static final class WeakKeySlot extends WeakSlot {
        private int c;

        @Override // com.prineside.luaj.WeakTable.WeakSlot, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            super.write(kryo, output);
            output.writeInt(this.c);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            super.read(kryo, input);
            this.c = input.readInt();
        }

        private WeakKeySlot() {
        }

        protected WeakKeySlot(LuaValue luaValue, LuaValue luaValue2, LuaTable.Slot slot) {
            super(WeakTable.a(luaValue), luaValue2, slot);
            this.c = luaValue.hashCode();
        }

        private WeakKeySlot(WeakKeySlot weakKeySlot, LuaTable.Slot slot) {
            super(weakKeySlot.f1520a, weakKeySlot.f1521b, slot);
            this.c = weakKeySlot.c;
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot, com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return LuaTable.hashmod(this.c, i);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        public final LuaTable.Slot set(LuaValue luaValue) {
            this.f1521b = luaValue;
            return this;
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        public final LuaValue strongkey() {
            return WeakTable.a(this.f1520a);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        protected final WeakSlot a(LuaTable.Slot slot) {
            return new WeakKeySlot(this, slot);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/WeakTable$WeakValueSlot.class */
    public static final class WeakValueSlot extends WeakSlot {
        private WeakValueSlot() {
        }

        protected WeakValueSlot(LuaValue luaValue, LuaValue luaValue2, LuaTable.Slot slot) {
            super(luaValue, WeakTable.a(luaValue2), slot);
        }

        private WeakValueSlot(WeakValueSlot weakValueSlot, LuaTable.Slot slot) {
            super(weakValueSlot.f1520a, weakValueSlot.f1521b, slot);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot, com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return LuaTable.hashSlot(strongkey(), i);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        public final LuaTable.Slot set(LuaValue luaValue) {
            this.f1521b = WeakTable.a(luaValue);
            return this;
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        public final LuaValue strongvalue() {
            return WeakTable.a(this.f1521b);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        protected final WeakSlot a(LuaTable.Slot slot) {
            return new WeakValueSlot(this, slot);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/WeakTable$WeakKeyAndValueSlot.class */
    public static final class WeakKeyAndValueSlot extends WeakSlot {
        private int c;

        @Override // com.prineside.luaj.WeakTable.WeakSlot, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            super.write(kryo, output);
            output.writeInt(this.c);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            super.read(kryo, input);
            this.c = input.readInt();
        }

        private WeakKeyAndValueSlot() {
        }

        protected WeakKeyAndValueSlot(LuaValue luaValue, LuaValue luaValue2, LuaTable.Slot slot) {
            super(WeakTable.a(luaValue), WeakTable.a(luaValue2), slot);
            this.c = luaValue.hashCode();
        }

        private WeakKeyAndValueSlot(WeakKeyAndValueSlot weakKeyAndValueSlot, LuaTable.Slot slot) {
            super(weakKeyAndValueSlot.f1520a, weakKeyAndValueSlot.f1521b, slot);
            this.c = weakKeyAndValueSlot.c;
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot, com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return LuaTable.hashmod(this.c, i);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        public final LuaTable.Slot set(LuaValue luaValue) {
            this.f1521b = WeakTable.a(luaValue);
            return this;
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        public final LuaValue strongkey() {
            return WeakTable.a(this.f1520a);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        public final LuaValue strongvalue() {
            return WeakTable.a(this.f1521b);
        }

        @Override // com.prineside.luaj.WeakTable.WeakSlot
        protected final WeakSlot a(LuaTable.Slot slot) {
            return new WeakKeyAndValueSlot(this, slot);
        }
    }

    protected static LuaValue a(LuaValue luaValue) {
        switch (luaValue.type()) {
            case 5:
            case 6:
            case 8:
                return new WeakValue(luaValue);
            case 7:
                return new WeakUserdata(luaValue, (byte) 0);
            default:
                return luaValue;
        }
    }

    protected static LuaValue a(Object obj) {
        if (obj instanceof WeakReference) {
            obj = ((WeakReference) obj).get();
        }
        if (obj instanceof WeakValue) {
            return ((WeakValue) obj).strongvalue();
        }
        return (LuaValue) obj;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/WeakTable$WeakValue.class */
    public static class WeakValue extends LuaValue implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        WeakReference f1523a;

        /* synthetic */ WeakValue(byte b2) {
            this();
        }

        public void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1523a.get());
        }

        public void read(Kryo kryo, Input input) {
            this.f1523a = new WeakReference(kryo.readClassAndObject(input));
        }

        private WeakValue() {
        }

        protected WeakValue(LuaValue luaValue) {
            this.f1523a = new WeakReference(luaValue);
        }

        @Override // com.prineside.luaj.LuaValue
        public final int type() {
            a("type", "weak value");
            return 0;
        }

        @Override // com.prineside.luaj.LuaValue
        public final String typename() {
            a("typename", "weak value");
            return null;
        }

        @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public String toString() {
            return "weak<" + this.f1523a.get() + ">";
        }

        @Override // com.prineside.luaj.LuaValue
        public LuaValue strongvalue() {
            return (LuaValue) this.f1523a.get();
        }

        @Override // com.prineside.luaj.LuaValue
        public boolean raweq(LuaValue luaValue) {
            Object obj = this.f1523a.get();
            return obj != null && luaValue.raweq((LuaValue) obj);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/WeakTable$WeakUserdata.class */
    public static final class WeakUserdata extends WeakValue {

        /* renamed from: b, reason: collision with root package name */
        private WeakReference f1522b;
        private LuaValue c;

        /* synthetic */ WeakUserdata(LuaValue luaValue, byte b2) {
            this(luaValue);
        }

        @Override // com.prineside.luaj.WeakTable.WeakValue, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            super.write(kryo, output);
            kryo.writeClassAndObject(output, this.f1522b.get());
            kryo.writeClassAndObject(output, this.c);
        }

        @Override // com.prineside.luaj.WeakTable.WeakValue, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            super.read(kryo, input);
            this.f1522b = new WeakReference(kryo.readClassAndObject(input));
            this.c = (LuaValue) kryo.readClassAndObject(input);
        }

        private WeakUserdata() {
            super((byte) 0);
        }

        private WeakUserdata(LuaValue luaValue) {
            super(luaValue);
            this.f1522b = new WeakReference(luaValue.touserdata());
            this.c = luaValue.getmetatable();
        }

        @Override // com.prineside.luaj.WeakTable.WeakValue, com.prineside.luaj.LuaValue
        public final LuaValue strongvalue() {
            Object obj = this.f1523a.get();
            if (obj != null) {
                return (LuaValue) obj;
            }
            Object obj2 = this.f1522b.get();
            if (obj2 != null) {
                LuaUserdata userdataOf = LuaValue.userdataOf(obj2, this.c);
                this.f1523a = new WeakReference(userdataOf);
                return userdataOf;
            }
            return null;
        }
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue wrap(LuaValue luaValue) {
        return this.f1519b ? a(luaValue) : luaValue;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue arrayget(LuaValue[] luaValueArr, int i) {
        LuaValue luaValue = luaValueArr[i];
        LuaValue luaValue2 = luaValue;
        if (luaValue != null) {
            LuaValue a2 = a((Object) luaValue2);
            luaValue2 = a2;
            if (a2 == null) {
                luaValueArr[i] = null;
            }
        }
        return luaValue2;
    }
}
