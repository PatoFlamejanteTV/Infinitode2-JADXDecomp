package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.lang.ref.WeakReference;
import java.util.Vector;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable.class */
public class LuaTable extends LuaValue implements KryoSerializable, Metatable {

    /* renamed from: a, reason: collision with root package name */
    private static final LuaString f1487a;
    public static final DeepClassComparator<LuaTable> CLASS_COMPARATOR;

    /* renamed from: b, reason: collision with root package name */
    private LuaValue[] f1488b;
    private Slot[] c;
    private int d;
    private Metatable e;
    private static final Slot[] f;

    @REGS(arrayLevels = 1, classOnly = true)
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$Slot.class */
    public interface Slot {
        int keyindex(int i);

        StrongSlot first();

        StrongSlot find(LuaValue luaValue);

        boolean keyeq(LuaValue luaValue);

        Slot rest();

        int arraykey(int i);

        Slot set(StrongSlot strongSlot, LuaValue luaValue);

        Slot add(Slot slot);

        Slot remove(StrongSlot strongSlot);

        Slot relink(Slot slot);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$StrongSlot.class */
    public interface StrongSlot extends Slot {
        LuaValue key();

        LuaValue value();

        Varargs toVarargs();
    }

    static {
        TLog.forClass(LuaTable.class);
        f1487a = valueOf("n");
        CLASS_COMPARATOR = new DeepClassComparator<LuaTable>() { // from class: com.prineside.luaj.LuaTable.1
            @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
            public Class<LuaTable> forClass() {
                return LuaTable.class;
            }

            @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
            public void compare(LuaTable luaTable, LuaTable luaTable2, DeepClassComparisonConfig deepClassComparisonConfig) {
                if (luaTable.get("_noSyncCheck") == LuaValue.TRUE) {
                    return;
                }
                LuaValue luaValue = LuaValue.NIL;
                while (true) {
                    Varargs next = luaTable.next(luaValue);
                    LuaValue arg1 = next.arg1();
                    luaValue = arg1;
                    if (!arg1.isnil()) {
                        LuaValue arg = next.arg(2);
                        deepClassComparisonConfig.addPrefix(".", luaValue.toString());
                        if (luaValue.isuserdata() && !SyncChecker.isSyncShareableObject(luaValue.checkuserdata())) {
                            deepClassComparisonConfig.addPrefix("<!!!object may be game session specific, used as a table key!!!>");
                        }
                        SyncChecker.compareObjects(arg, luaTable2.get(luaValue), deepClassComparisonConfig);
                        if (luaValue.isuserdata() && !SyncChecker.isSyncShareableObject(luaValue.checkuserdata())) {
                            deepClassComparisonConfig.popPrefix(1);
                        }
                        deepClassComparisonConfig.popPrefix(2);
                    } else {
                        return;
                    }
                }
            }
        };
        SyncChecker.CLASS_COMPARATORS.add(CLASS_COMPARATOR);
        Slot[] slotArr = new Slot[0];
        f = slotArr;
        SyncChecker.addSyncShareableObject(slotArr);
    }

    public void write(Kryo kryo, Output output) {
        if (get("_noSerialization") == LuaValue.TRUE) {
            output.writeBoolean(false);
            return;
        }
        output.writeBoolean(true);
        LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, this.f1488b);
        kryo.writeClassAndObject(output, this.c);
        output.writeInt(this.d);
        kryo.writeClassAndObject(output, this.e);
    }

    public void read(Kryo kryo, Input input) {
        if (input.readBoolean()) {
            this.f1488b = (LuaValue[]) kryo.readClassAndObject(input);
            this.c = (Slot[]) kryo.readClassAndObject(input);
            this.d = input.readInt();
            this.e = (Metatable) kryo.readClassAndObject(input);
        }
    }

    public LuaTable() {
        this.f1488b = NOVALS;
        this.c = f;
    }

    public LuaTable(int i, int i2) {
        presize(i, i2);
    }

    public LuaTable(LuaValue[] luaValueArr, LuaValue[] luaValueArr2, Varargs varargs) {
        int length = luaValueArr != null ? luaValueArr.length : 0;
        int length2 = luaValueArr2 != null ? luaValueArr2.length : 0;
        presize(length2 + (varargs != null ? varargs.narg() : 0), length >> 1);
        for (int i = 0; i < length2; i++) {
            rawset(i + 1, luaValueArr2[i]);
        }
        if (varargs != null) {
            int narg = varargs.narg();
            for (int i2 = 1; i2 <= narg; i2++) {
                rawset(length2 + i2, varargs.arg(i2));
            }
        }
        for (int i3 = 0; i3 < length; i3 += 2) {
            if (!luaValueArr[i3 + 1].isnil()) {
                rawset(luaValueArr[i3], luaValueArr[i3 + 1]);
            }
        }
    }

    public LuaTable(Varargs varargs) {
        this(varargs, 1);
    }

    public LuaTable(Varargs varargs, int i) {
        int i2 = i - 1;
        int max = Math.max(varargs.narg() - i2, 0);
        presize(max, 1);
        set(f1487a, valueOf(max));
        for (int i3 = 1; i3 <= max; i3++) {
            set(i3, varargs.arg(i3 + i2));
        }
    }

    public final int getHashEntries() {
        return this.d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int type() {
        return 5;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String typename() {
        return FlexmarkHtmlConverter.TABLE_NODE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean istable() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaTable checktable() {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaTable opttable(LuaTable luaTable) {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final void presize(int i) {
        if (i > this.f1488b.length) {
            this.f1488b = a(this.f1488b, 1 << a(i));
        }
    }

    public final void presize(int i, int i2) {
        if (i2 > 0 && i2 < 2) {
            i2 = 2;
        }
        this.f1488b = i > 0 ? new LuaValue[1 << a(i)] : NOVALS;
        this.c = i2 > 0 ? new Slot[1 << a(i2)] : f;
        this.d = 0;
    }

    private static LuaValue[] a(LuaValue[] luaValueArr, int i) {
        LuaValue[] luaValueArr2 = new LuaValue[i];
        System.arraycopy(luaValueArr, 0, luaValueArr2, 0, luaValueArr.length);
        return luaValueArr2;
    }

    private int d() {
        return this.f1488b.length;
    }

    private int e() {
        return this.c.length;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue getmetatable() {
        if (this.e != null) {
            return this.e.toLuaValue();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x006f, code lost:            if (r6 != (r3.e != null && r3.e.useWeakValues())) goto L28;     */
    @Override // com.prineside.luaj.LuaValue
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.prineside.luaj.LuaValue setmetatable(com.prineside.luaj.LuaValue r4) {
        /*
            r3 = this;
            r0 = r3
            com.prineside.luaj.Metatable r0 = r0.e
            if (r0 == 0) goto L17
            r0 = r3
            com.prineside.luaj.Metatable r0 = r0.e
            boolean r0 = r0.useWeakKeys()
            if (r0 == 0) goto L17
            r0 = 1
            goto L18
        L17:
            r0 = 0
        L18:
            r5 = r0
            r0 = r3
            com.prineside.luaj.Metatable r0 = r0.e
            if (r0 == 0) goto L30
            r0 = r3
            com.prineside.luaj.Metatable r0 = r0.e
            boolean r0 = r0.useWeakValues()
            if (r0 == 0) goto L30
            r0 = 1
            goto L31
        L30:
            r0 = 0
        L31:
            r6 = r0
            r0 = r3
            r1 = r4
            com.prineside.luaj.Metatable r1 = b(r1)
            r0.e = r1
            r0 = r5
            r1 = r3
            com.prineside.luaj.Metatable r1 = r1.e
            if (r1 == 0) goto L52
            r1 = r3
            com.prineside.luaj.Metatable r1 = r1.e
            boolean r1 = r1.useWeakKeys()
            if (r1 == 0) goto L52
            r1 = 1
            goto L53
        L52:
            r1 = 0
        L53:
            if (r0 != r1) goto L72
            r0 = r6
            r1 = r3
            com.prineside.luaj.Metatable r1 = r1.e
            if (r1 == 0) goto L6e
            r1 = r3
            com.prineside.luaj.Metatable r1 = r1.e
            boolean r1 = r1.useWeakValues()
            if (r1 == 0) goto L6e
            r1 = 1
            goto L6f
        L6e:
            r1 = 0
        L6f:
            if (r0 == r1) goto L77
        L72:
            r0 = r3
            r1 = 0
            r0.b(r1)
        L77:
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.LuaTable.setmetatable(com.prineside.luaj.LuaValue):com.prineside.luaj.LuaValue");
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue get(int i) {
        LuaValue rawget = rawget(i);
        return (!rawget.isnil() || this.e == null) ? rawget : c(this, valueOf(i));
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue get(LuaValue luaValue) {
        LuaValue rawget = rawget(luaValue);
        if (rawget.isnil() && this.e != null) {
            return c(this, luaValue);
        }
        return rawget;
    }

    @Override // com.prineside.luaj.LuaValue
    protected final LuaValue a() {
        return metatag(LuaValue.CALL);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue rawget(int i) {
        if (i > 0 && i <= this.f1488b.length) {
            LuaValue arrayget = this.e == null ? this.f1488b[i - 1] : this.e.arrayget(this.f1488b, i - 1);
            return arrayget != null ? arrayget : NIL;
        }
        return c(LuaNumber.valueOf(i));
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue rawget(LuaValue luaValue) {
        int i;
        if (luaValue.isinttype() && (i = luaValue.toint()) > 0 && i <= this.f1488b.length) {
            LuaValue arrayget = this.e == null ? this.f1488b[i - 1] : this.e.arrayget(this.f1488b, i - 1);
            return arrayget != null ? arrayget : NIL;
        }
        return c(luaValue);
    }

    private LuaValue c(LuaValue luaValue) {
        if (this.d > 0) {
            Slot slot = this.c[d(luaValue)];
            while (true) {
                Slot slot2 = slot;
                if (slot2 == null) {
                    break;
                }
                StrongSlot find = slot2.find(luaValue);
                if (find == null) {
                    slot = slot2.rest();
                } else {
                    return find.value();
                }
            }
        }
        return NIL;
    }

    @Override // com.prineside.luaj.LuaValue
    public void set(int i, LuaValue luaValue) {
        if (this.e == null || !rawget(i).isnil() || !a(this, LuaNumber.valueOf(i), luaValue)) {
            rawset(i, luaValue);
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final void set(LuaValue luaValue, LuaValue luaValue2) {
        if (luaValue == null || (!luaValue.isvalidkey() && !metatag(NEWINDEX).isfunction())) {
            throw new LuaError("value ('" + luaValue + "') can not be used as a table index");
        }
        if (this.e == null || !rawget(luaValue).isnil() || !a(this, luaValue, luaValue2)) {
            rawset(luaValue, luaValue2);
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public void rawset(int i, LuaValue luaValue) {
        if (!a(i, luaValue)) {
            hashset(LuaNumber.valueOf(i), luaValue);
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public void rawset(LuaValue luaValue, LuaValue luaValue2) {
        if (!luaValue.isinttype() || !a(luaValue.toint(), luaValue2)) {
            hashset(luaValue, luaValue2);
        }
    }

    private boolean a(int i, LuaValue luaValue) {
        LuaValue wrap;
        if (i > 0 && i <= this.f1488b.length) {
            LuaValue[] luaValueArr = this.f1488b;
            int i2 = i - 1;
            if (luaValue.isnil()) {
                wrap = null;
            } else {
                wrap = this.e != null ? this.e.wrap(luaValue) : luaValue;
            }
            luaValueArr[i2] = wrap;
            return true;
        }
        return false;
    }

    public LuaValue remove(int i) {
        int length = length();
        if (i == 0) {
            i = length;
        } else if (i > length) {
            return NONE;
        }
        LuaValue luaValue = get(i);
        LuaValue luaValue2 = luaValue;
        while (!luaValue2.isnil()) {
            luaValue2 = get(i + 1);
            int i2 = i;
            i++;
            set(i2, luaValue2);
        }
        return luaValue.isnil() ? NONE : luaValue;
    }

    public final void insert(int i, LuaValue luaValue) {
        if (i == 0) {
            i = length() + 1;
        }
        while (!luaValue.isnil()) {
            LuaValue luaValue2 = get(i);
            int i2 = i;
            i++;
            set(i2, luaValue);
            luaValue = luaValue2;
        }
    }

    public final LuaValue concat(LuaString luaString, int i, int i2) {
        Buffer buffer = new Buffer();
        if (i <= i2) {
            buffer.append(get(i).checkstring());
            while (true) {
                i++;
                if (i > i2) {
                    break;
                }
                buffer.append(luaString);
                buffer.append(get(i).checkstring());
            }
        }
        return buffer.tostring();
    }

    @Override // com.prineside.luaj.LuaValue
    public final int length() {
        if (this.e != null) {
            LuaValue len = len();
            if (!len.isint()) {
                throw new LuaError("table length is not an integer: " + len);
            }
            return len.toint();
        }
        return rawlen();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue len() {
        LuaValue metatag = metatag(LEN);
        if (metatag.toboolean()) {
            return metatag.call(this);
        }
        return LuaNumber.valueOf(rawlen());
    }

    @Override // com.prineside.luaj.LuaValue
    public final int rawlen() {
        int d = d();
        int i = d + 1;
        int i2 = 0;
        while (!rawget(i).isnil()) {
            i2 = i;
            i += d + e() + 1;
        }
        while (i > i2 + 1) {
            int i3 = (i + i2) / 2;
            if (!rawget(i3).isnil()) {
                i2 = i3;
            } else {
                i = i3;
            }
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x012a, code lost:            r6 = r6 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:            if (r6 <= r4.f1488b.length) goto L67;     */
    @Override // com.prineside.luaj.LuaValue
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.prineside.luaj.Varargs next(com.prineside.luaj.LuaValue r5) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.LuaTable.next(com.prineside.luaj.LuaValue):com.prineside.luaj.Varargs");
    }

    @Override // com.prineside.luaj.LuaValue
    public final Varargs inext(LuaValue luaValue) {
        int checkint = luaValue.checkint() + 1;
        LuaValue rawget = rawget(checkint);
        if (!rawget.isnil()) {
            return varargsOf(LuaNumber.valueOf(checkint), rawget);
        }
        return NONE;
    }

    public final void hashset(LuaValue luaValue, LuaValue luaValue2) {
        Slot a2;
        if (luaValue2.isnil()) {
            e(luaValue);
            return;
        }
        int i = 0;
        if (this.c.length > 0) {
            i = d(luaValue);
            Slot slot = this.c[i];
            while (true) {
                Slot slot2 = slot;
                if (slot2 == null) {
                    break;
                }
                StrongSlot find = slot2.find(luaValue);
                if (find == null) {
                    slot = slot2.rest();
                } else {
                    Slot[] slotArr = this.c;
                    slotArr[i] = slotArr[i].set(find, luaValue2);
                    return;
                }
            }
        }
        if (f()) {
            if ((this.e == null || !this.e.useWeakValues()) && luaValue.isinttype() && luaValue.toint() > 0) {
                b(luaValue.toint());
                if (a(luaValue.toint(), luaValue2)) {
                    return;
                }
            } else {
                b(-1);
            }
            i = d(luaValue);
        }
        if (this.e != null) {
            a2 = this.e.entry(luaValue, luaValue2);
        } else {
            a2 = a(luaValue, luaValue2);
        }
        Slot slot3 = a2;
        int i2 = i;
        Slot[] slotArr2 = this.c;
        slotArr2[i2] = slotArr2[i] != null ? this.c[i].add(slot3) : slot3;
        this.d++;
    }

    public static int hashpow2(int i, int i2) {
        return i & i2;
    }

    public static int hashmod(int i, int i2) {
        return (i & Integer.MAX_VALUE) % i2;
    }

    public static int hashSlot(LuaValue luaValue, int i) {
        switch (luaValue.type()) {
            case 2:
            case 3:
            case 5:
            case 7:
            case 8:
                return hashmod(luaValue.hashCode(), i);
            case 4:
            case 6:
            default:
                return hashpow2(luaValue.hashCode(), i);
        }
    }

    private int d(LuaValue luaValue) {
        return hashSlot(luaValue, this.c.length - 1);
    }

    private void e(LuaValue luaValue) {
        if (this.c.length > 0) {
            int d = d(luaValue);
            Slot slot = this.c[d];
            while (true) {
                Slot slot2 = slot;
                if (slot2 != null) {
                    StrongSlot find = slot2.find(luaValue);
                    if (find == null) {
                        slot = slot2.rest();
                    } else {
                        Slot[] slotArr = this.c;
                        slotArr[d] = slotArr[d].remove(find);
                        this.d--;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private boolean f() {
        return this.d >= this.c.length;
    }

    private int g() {
        int i = 0;
        for (int i2 = 0; i2 < this.c.length; i2++) {
            Slot slot = this.c[i2];
            while (true) {
                Slot slot2 = slot;
                if (slot2 != null) {
                    if (slot2.first() != null) {
                        i++;
                    }
                    slot = slot2.rest();
                }
            }
        }
        return i;
    }

    private void h() {
        for (int i = 0; i < this.f1488b.length; i++) {
            this.e.arrayget(this.f1488b, i);
        }
    }

    private int a(int[] iArr) {
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < 31 && i2 <= this.f1488b.length; i3++) {
            int min = Math.min(this.f1488b.length, 1 << i3);
            int i4 = 0;
            while (i2 <= min) {
                int i5 = i2;
                i2++;
                if (this.f1488b[i5 - 1] != null) {
                    i4++;
                }
            }
            iArr[i3] = i4;
            i += i4;
        }
        for (int i6 = 0; i6 < this.c.length; i6++) {
            Slot slot = this.c[i6];
            while (true) {
                Slot slot2 = slot;
                if (slot2 != null) {
                    int arraykey = slot2.arraykey(Integer.MAX_VALUE);
                    if (arraykey > 0) {
                        int a2 = a(arraykey);
                        iArr[a2] = iArr[a2] + 1;
                        i++;
                    }
                    slot = slot2.rest();
                }
            }
        }
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x003a. Please report as an issue. */
    private static int a(int i) {
        int i2 = 0;
        int i3 = i - 1;
        if (i3 < 0) {
            return Integer.MIN_VALUE;
        }
        if ((i3 & (-65536)) != 0) {
            i2 = 16;
            i3 >>>= 16;
        }
        if ((i3 & 65280) != 0) {
            i2 += 8;
            i3 >>>= 8;
        }
        if ((i3 & User32.VK_OEM_ATTN) != 0) {
            i2 += 4;
            i3 >>>= 4;
        }
        switch (i3) {
            case 0:
                return 0;
            case 1:
                i2++;
                return i2;
            case 2:
            case 3:
                i2 += 2;
                return i2;
            case 4:
            case 5:
            case 6:
            case 7:
                i2 += 3;
                return i2;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i2 += 4;
                return i2;
            default:
                return i2;
        }
    }

    private void b(int i) {
        LuaValue[] luaValueArr;
        int i2;
        Slot[] slotArr;
        Slot a2;
        int a3;
        if (this.e != null && (this.e.useWeakKeys() || this.e.useWeakValues())) {
            this.d = g();
            if (this.e.useWeakValues()) {
                h();
            }
        }
        int[] iArr = new int[32];
        int a4 = a(iArr);
        if (i > 0) {
            a4++;
            int a5 = a(i);
            iArr[a5] = iArr[a5] + 1;
        }
        int i3 = iArr[0];
        int i4 = 0;
        for (int i5 = 1; i5 < 32; i5++) {
            i3 += iArr[i5];
            if ((a4 << 1) < (1 << i5)) {
                break;
            }
            if (i3 >= (1 << (i5 - 1))) {
                i4 = 1 << i5;
            }
        }
        LuaValue[] luaValueArr2 = this.f1488b;
        Slot[] slotArr2 = this.c;
        int i6 = 0;
        if (i > 0 && i <= i4) {
            i6 = 0 - 1;
        }
        if (i4 != luaValueArr2.length) {
            luaValueArr = new LuaValue[i4];
            if (i4 > luaValueArr2.length) {
                int a6 = a(i4) + 1;
                for (int a7 = a(luaValueArr2.length + 1); a7 < a6; a7++) {
                    i6 += iArr[a7];
                }
            } else if (luaValueArr2.length > i4) {
                int a8 = a(luaValueArr2.length) + 1;
                for (int a9 = a(i4 + 1); a9 < a8; a9++) {
                    i6 -= iArr[a9];
                }
            }
            System.arraycopy(luaValueArr2, 0, luaValueArr, 0, Math.min(luaValueArr2.length, i4));
        } else {
            luaValueArr = this.f1488b;
        }
        int i7 = (this.d - i6) + ((i < 0 || i > i4) ? 1 : 0);
        if (i7 > 0) {
            if (i7 < 2) {
                a3 = 2;
            } else {
                a3 = 1 << a(i7);
            }
            i2 = a3 - 1;
            slotArr = new Slot[a3];
        } else {
            i2 = 0;
            slotArr = f;
        }
        for (Slot slot : slotArr2) {
            while (true) {
                Slot slot2 = slot;
                if (slot2 != null) {
                    int arraykey = slot2.arraykey(i4);
                    if (arraykey > 0) {
                        StrongSlot first = slot2.first();
                        if (first != null) {
                            luaValueArr[arraykey - 1] = first.value();
                        }
                    } else if (!(slot2 instanceof DeadSlot)) {
                        int keyindex = slot2.keyindex(i2);
                        slotArr[keyindex] = slot2.relink(slotArr[keyindex]);
                    }
                    slot = slot2.rest();
                }
            }
        }
        int i8 = i4;
        while (i8 < luaValueArr2.length) {
            int i9 = i8;
            i8++;
            LuaValue luaValue = luaValueArr2[i9];
            if (luaValue != null) {
                int hashmod = hashmod(LuaNumber.hashCode(i8), i2);
                if (this.e != null) {
                    Slot entry = this.e.entry(valueOf(i8), luaValue);
                    a2 = entry;
                    if (entry == null) {
                    }
                } else {
                    a2 = a(valueOf(i8), luaValue);
                }
                slotArr[hashmod] = slotArr[hashmod] != null ? slotArr[hashmod].add(a2) : a2;
            }
        }
        this.c = slotArr;
        this.f1488b = luaValueArr;
        this.d -= i6;
    }

    @Override // com.prineside.luaj.Metatable
    public final Slot entry(LuaValue luaValue, LuaValue luaValue2) {
        return a(luaValue, luaValue2);
    }

    protected static boolean a(LuaValue luaValue) {
        switch (luaValue.type()) {
            case 1:
            case 3:
                return false;
            case 2:
            default:
                return true;
            case 4:
                return luaValue.rawlen() > 64;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Entry a(LuaValue luaValue, LuaValue luaValue2) {
        if (luaValue.isinttype()) {
            return new IntKeyEntry(luaValue.toint(), luaValue2);
        }
        if (luaValue2.type() == 3) {
            return new NumberValueEntry(luaValue, luaValue2.todouble());
        }
        return new NormalEntry(luaValue, luaValue2);
    }

    public final void sort(LuaValue luaValue) {
        if (len().tolong() >= 2147483647L) {
            throw new LuaError("array too big: " + len().tolong());
        }
        if (this.e != null && this.e.useWeakValues()) {
            h();
        }
        int length = length();
        if (length > 1) {
            b(length, luaValue.isnil() ? null : luaValue);
        }
    }

    private void b(int i, LuaValue luaValue) {
        c(i, luaValue);
        int i2 = i;
        while (i2 > 1) {
            LuaValue luaValue2 = get(i2);
            set(i2, get(1));
            set(1, luaValue2);
            i2--;
            a(1, i2, luaValue);
        }
    }

    private void c(int i, LuaValue luaValue) {
        for (int i2 = i / 2; i2 > 0; i2--) {
            a(i2, i, luaValue);
        }
    }

    private void a(int i, int i2, LuaValue luaValue) {
        int i3 = i;
        while (true) {
            int i4 = i3;
            if ((i4 << 1) <= i2) {
                int i5 = i4 << 1;
                int i6 = i5;
                if (i5 < i2 && b(i6, i6 + 1, luaValue)) {
                    i6++;
                }
                if (b(i4, i6, luaValue)) {
                    LuaValue luaValue2 = get(i4);
                    set(i4, get(i6));
                    set(i6, luaValue2);
                    i3 = i6;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private boolean b(int i, int i2, LuaValue luaValue) {
        LuaValue luaValue2 = get(i);
        LuaValue luaValue3 = get(i2);
        if (luaValue2 == null || luaValue3 == null) {
            return false;
        }
        if (luaValue != null) {
            return luaValue.call(luaValue2, luaValue3).toboolean();
        }
        return luaValue2.lt_b(luaValue3);
    }

    public final int keyCount() {
        LuaValue luaValue = LuaValue.NIL;
        int i = 0;
        while (true) {
            LuaValue arg1 = next(luaValue).arg1();
            luaValue = arg1;
            if (!arg1.isnil()) {
                i++;
            } else {
                return i;
            }
        }
    }

    public final LuaValue[] keys() {
        Vector vector = new Vector();
        LuaValue luaValue = LuaValue.NIL;
        while (true) {
            LuaValue arg1 = next(luaValue).arg1();
            luaValue = arg1;
            if (!arg1.isnil()) {
                vector.addElement(luaValue);
            } else {
                LuaValue[] luaValueArr = new LuaValue[vector.size()];
                vector.copyInto(luaValueArr);
                return luaValueArr;
            }
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue eq(LuaValue luaValue) {
        return eq_b(luaValue) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean eq_b(LuaValue luaValue) {
        LuaValue luaValue2;
        if (this == luaValue) {
            return true;
        }
        return this.e != null && luaValue.istable() && (luaValue2 = luaValue.getmetatable()) != null && LuaValue.eqmtcall(this, this.e.toLuaValue(), luaValue, luaValue2);
    }

    public final Varargs unpack() {
        return unpack(1, rawlen());
    }

    public final Varargs unpack(int i) {
        return unpack(i, rawlen());
    }

    public final Varargs unpack(int i, int i2) {
        if (i2 < i) {
            return NONE;
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new LuaError("too many results to unpack: greater 2147483647");
        }
        if (i3 >= 16777215) {
            throw new LuaError("too many results to unpack: " + i3 + " (max is 16777215)");
        }
        int i4 = (i2 + 1) - i;
        int i5 = i4;
        switch (i4) {
            case 0:
                return NONE;
            case 1:
                return get(i);
            case 2:
                return varargsOf(get(i), get(i + 1));
            default:
                if (i5 < 0) {
                    return NONE;
                }
                try {
                    LuaValue[] luaValueArr = new LuaValue[i5];
                    while (true) {
                        i5--;
                        if (i5 >= 0) {
                            luaValueArr[i5] = get(i + i5);
                        } else {
                            return varargsOf(luaValueArr);
                        }
                    }
                } catch (OutOfMemoryError unused) {
                    throw new LuaError("too many results to unpack [out of memory]: " + i5);
                }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$LinkSlot.class */
    public static final class LinkSlot implements KryoSerializable, StrongSlot {

        /* renamed from: a, reason: collision with root package name */
        private Entry f1493a;

        /* renamed from: b, reason: collision with root package name */
        private Slot f1494b;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1493a);
            kryo.writeClassAndObject(output, this.f1494b);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1493a = (Entry) kryo.readClassAndObject(input);
            this.f1494b = (Slot) kryo.readClassAndObject(input);
        }

        LinkSlot() {
        }

        LinkSlot(Entry entry, Slot slot) {
            this.f1493a = entry;
            this.f1494b = slot;
        }

        @Override // com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue key() {
            return this.f1493a.key();
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return this.f1493a.keyindex(i);
        }

        @Override // com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue value() {
            return this.f1493a.value();
        }

        @Override // com.prineside.luaj.LuaTable.StrongSlot
        public final Varargs toVarargs() {
            return this.f1493a.toVarargs();
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final StrongSlot first() {
            return this.f1493a;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final StrongSlot find(LuaValue luaValue) {
            if (this.f1493a.keyeq(luaValue)) {
                return this;
            }
            return null;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final boolean keyeq(LuaValue luaValue) {
            return this.f1493a.keyeq(luaValue);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot rest() {
            return this.f1494b;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final int arraykey(int i) {
            return this.f1493a.arraykey(i);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot set(StrongSlot strongSlot, LuaValue luaValue) {
            if (strongSlot == this) {
                this.f1493a = this.f1493a.set(luaValue);
                return this;
            }
            return a(this.f1494b.set(strongSlot, luaValue));
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot add(Slot slot) {
            return a(this.f1494b.add(slot));
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot remove(StrongSlot strongSlot) {
            if (this == strongSlot) {
                return new DeadSlot(key(), this.f1494b, (byte) 0);
            }
            this.f1494b = this.f1494b.remove(strongSlot);
            return this;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot relink(Slot slot) {
            return slot != null ? new LinkSlot(this.f1493a, slot) : this.f1493a;
        }

        private Slot a(Slot slot) {
            if (slot != null) {
                this.f1494b = slot;
                return this;
            }
            return this.f1493a;
        }

        public final String toString() {
            return this.f1493a + "; " + this.f1494b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$Entry.class */
    public static abstract class Entry extends Varargs implements StrongSlot {
        @Override // com.prineside.luaj.LuaTable.StrongSlot
        public abstract LuaValue key();

        @Override // com.prineside.luaj.LuaTable.StrongSlot
        public abstract LuaValue value();

        abstract Entry set(LuaValue luaValue);

        @Override // com.prineside.luaj.LuaTable.Slot
        public abstract boolean keyeq(LuaValue luaValue);

        @Override // com.prineside.luaj.LuaTable.Slot
        public abstract int keyindex(int i);

        Entry() {
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public int arraykey(int i) {
            return 0;
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg(int i) {
            switch (i) {
                case 1:
                    return key();
                case 2:
                    return value();
                default:
                    return LuaValue.NIL;
            }
        }

        @Override // com.prineside.luaj.Varargs
        public final int narg() {
            return 2;
        }

        @Override // com.prineside.luaj.LuaTable.StrongSlot
        public Varargs toVarargs() {
            return LuaValue.varargsOf(key(), value());
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg1() {
            return key();
        }

        @Override // com.prineside.luaj.Varargs
        public final Varargs subargs(int i) {
            switch (i) {
                case 1:
                    return this;
                case 2:
                    return value();
                default:
                    return LuaValue.NONE;
            }
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final StrongSlot first() {
            return this;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot rest() {
            return null;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final StrongSlot find(LuaValue luaValue) {
            if (keyeq(luaValue)) {
                return this;
            }
            return null;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot set(StrongSlot strongSlot, LuaValue luaValue) {
            return set(luaValue);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot add(Slot slot) {
            return new LinkSlot(this, slot);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot remove(StrongSlot strongSlot) {
            return new DeadSlot(key(), null, (byte) 0);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot relink(Slot slot) {
            return slot != null ? new LinkSlot(this, slot) : this;
        }
    }

    @REGS(serializer = Serializer.class)
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$NormalEntry.class */
    public static final class NormalEntry extends Entry {

        /* renamed from: a, reason: collision with root package name */
        private final LuaValue f1495a;

        /* renamed from: b, reason: collision with root package name */
        private LuaValue f1496b;

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final /* bridge */ /* synthetic */ int arraykey(int i) {
            return super.arraykey(i);
        }

        /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$NormalEntry$Serializer.class */
        public static class Serializer extends com.esotericsoftware.kryo.Serializer<NormalEntry> {
            @Override // com.esotericsoftware.kryo.Serializer
            public void write(Kryo kryo, Output output, NormalEntry normalEntry) {
                LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, normalEntry.f1495a);
                LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, normalEntry.f1496b);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.esotericsoftware.kryo.Serializer
            /* renamed from: read */
            public NormalEntry read2(Kryo kryo, Input input, Class<? extends NormalEntry> cls) {
                return new NormalEntry((LuaValue) kryo.readClassAndObject(input), (LuaValue) kryo.readClassAndObject(input));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public NormalEntry(LuaValue luaValue, LuaValue luaValue2) {
            this.f1495a = luaValue;
            this.f1496b = luaValue2;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue key() {
            return this.f1495a;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue value() {
            return this.f1496b;
        }

        @Override // com.prineside.luaj.LuaTable.Entry
        public final Entry set(LuaValue luaValue) {
            this.f1496b = luaValue;
            return this;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final Varargs toVarargs() {
            return this;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return LuaTable.hashSlot(this.f1495a, i);
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final boolean keyeq(LuaValue luaValue) {
            return luaValue.raweq(this.f1495a);
        }
    }

    @REGS(serializer = Serializer.class)
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$IntKeyEntry.class */
    public static final class IntKeyEntry extends Entry {

        /* renamed from: a, reason: collision with root package name */
        private final int f1491a;

        /* renamed from: b, reason: collision with root package name */
        private LuaValue f1492b;

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final /* bridge */ /* synthetic */ Varargs toVarargs() {
            return super.toVarargs();
        }

        /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$IntKeyEntry$Serializer.class */
        public static class Serializer extends com.esotericsoftware.kryo.Serializer<IntKeyEntry> {
            @Override // com.esotericsoftware.kryo.Serializer
            public void write(Kryo kryo, Output output, IntKeyEntry intKeyEntry) {
                output.writeVarInt(intKeyEntry.f1491a, false);
                LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, intKeyEntry.f1492b);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.esotericsoftware.kryo.Serializer
            /* renamed from: read */
            public IntKeyEntry read2(Kryo kryo, Input input, Class<? extends IntKeyEntry> cls) {
                return new IntKeyEntry(input.readVarInt(false), (LuaValue) kryo.readClassAndObject(input));
            }
        }

        IntKeyEntry(int i, LuaValue luaValue) {
            this.f1491a = i;
            this.f1492b = luaValue;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue key() {
            return LuaValue.valueOf(this.f1491a);
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final int arraykey(int i) {
            if (this.f1491a <= 0 || this.f1491a > i) {
                return 0;
            }
            return this.f1491a;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue value() {
            return this.f1492b;
        }

        @Override // com.prineside.luaj.LuaTable.Entry
        public final Entry set(LuaValue luaValue) {
            this.f1492b = luaValue;
            return this;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return LuaTable.hashmod(LuaNumber.hashCode(this.f1491a), i);
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final boolean keyeq(LuaValue luaValue) {
            return luaValue.raweq(this.f1491a);
        }
    }

    @REGS(serializer = Serializer.class)
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$NumberValueEntry.class */
    public static final class NumberValueEntry extends Entry {

        /* renamed from: a, reason: collision with root package name */
        private final LuaNumber f1497a = LuaValue.valueOf(123.45d);

        /* renamed from: b, reason: collision with root package name */
        private final LuaValue f1498b;

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final /* bridge */ /* synthetic */ Varargs toVarargs() {
            return super.toVarargs();
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final /* bridge */ /* synthetic */ int arraykey(int i) {
            return super.arraykey(i);
        }

        /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$NumberValueEntry$Serializer.class */
        public static class Serializer extends com.esotericsoftware.kryo.Serializer<NumberValueEntry> {
            @Override // com.esotericsoftware.kryo.Serializer
            public void write(Kryo kryo, Output output, NumberValueEntry numberValueEntry) {
                output.writeDouble(numberValueEntry.f1497a.f1483a);
                LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, numberValueEntry.f1498b);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.esotericsoftware.kryo.Serializer
            /* renamed from: read */
            public NumberValueEntry read2(Kryo kryo, Input input, Class<? extends NumberValueEntry> cls) {
                return new NumberValueEntry((LuaValue) kryo.readClassAndObject(input), input.readDouble());
            }
        }

        NumberValueEntry(LuaValue luaValue, double d) {
            this.f1498b = luaValue;
            this.f1497a.f1483a = d;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue key() {
            return this.f1498b;
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.StrongSlot
        public final LuaValue value() {
            return this.f1497a;
        }

        @Override // com.prineside.luaj.LuaTable.Entry
        public final Entry set(LuaValue luaValue) {
            if (luaValue.type() == 3) {
                LuaValue luaValue2 = luaValue.tonumber();
                if (!luaValue2.isnil()) {
                    this.f1497a.f1483a = luaValue2.todouble();
                    return this;
                }
            }
            return new NormalEntry(this.f1498b, luaValue);
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return LuaTable.hashSlot(this.f1498b, i);
        }

        @Override // com.prineside.luaj.LuaTable.Entry, com.prineside.luaj.LuaTable.Slot
        public final boolean keyeq(LuaValue luaValue) {
            return luaValue.raweq(this.f1498b);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaTable$DeadSlot.class */
    public static final class DeadSlot implements KryoSerializable, Slot {

        /* renamed from: a, reason: collision with root package name */
        private Object f1489a;

        /* renamed from: b, reason: collision with root package name */
        private Slot f1490b;

        /* synthetic */ DeadSlot(LuaValue luaValue, Slot slot, byte b2) {
            this(luaValue, slot);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1489a);
            kryo.writeClassAndObject(output, this.f1490b);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1489a = kryo.readClassAndObject(input);
            this.f1490b = (Slot) kryo.readClassAndObject(input);
        }

        private DeadSlot() {
        }

        private DeadSlot(LuaValue luaValue, Slot slot) {
            this.f1489a = LuaTable.a(luaValue) ? new WeakReference(luaValue) : luaValue;
            this.f1490b = slot;
        }

        private LuaValue a() {
            return (LuaValue) (this.f1489a instanceof WeakReference ? ((WeakReference) this.f1489a).get() : this.f1489a);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final int keyindex(int i) {
            return 0;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final StrongSlot first() {
            return null;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final StrongSlot find(LuaValue luaValue) {
            return null;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final boolean keyeq(LuaValue luaValue) {
            LuaValue a2 = a();
            return a2 != null && luaValue.raweq(a2);
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot rest() {
            return this.f1490b;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final int arraykey(int i) {
            return -1;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot set(StrongSlot strongSlot, LuaValue luaValue) {
            Slot slot = this.f1490b != null ? this.f1490b.set(strongSlot, luaValue) : null;
            if (a() != null) {
                this.f1490b = slot;
                return this;
            }
            return slot;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot add(Slot slot) {
            return this.f1490b != null ? this.f1490b.add(slot) : slot;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot remove(StrongSlot strongSlot) {
            if (a() != null) {
                this.f1490b = this.f1490b.remove(strongSlot);
                return this;
            }
            return this.f1490b;
        }

        @Override // com.prineside.luaj.LuaTable.Slot
        public final Slot relink(Slot slot) {
            return slot;
        }

        public final String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<dead");
            LuaValue a2 = a();
            if (a2 != null) {
                stringBuffer.append(": ");
                stringBuffer.append(a2.toString());
            }
            stringBuffer.append('>');
            if (this.f1490b != null) {
                stringBuffer.append("; ");
                stringBuffer.append(this.f1490b.toString());
            }
            return stringBuffer.toString();
        }
    }

    @Override // com.prineside.luaj.Metatable
    public boolean useWeakKeys() {
        return false;
    }

    @Override // com.prineside.luaj.Metatable
    public boolean useWeakValues() {
        return false;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue toLuaValue() {
        return this;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue wrap(LuaValue luaValue) {
        return luaValue;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue arrayget(LuaValue[] luaValueArr, int i) {
        return luaValueArr[i];
    }
}
