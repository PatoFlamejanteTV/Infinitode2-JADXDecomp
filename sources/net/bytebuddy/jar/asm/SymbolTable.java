package net.bytebuddy.jar.asm;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/SymbolTable.class */
public final class SymbolTable {

    /* renamed from: a, reason: collision with root package name */
    final ClassWriter f4148a;
    private final ClassReader sourceClassReader;
    private int majorVersion;
    private String className;
    private int entryCount;
    private Entry[] entries;
    private int constantPoolCount;
    private ByteVector constantPool;
    private int bootstrapMethodCount;
    private ByteVector bootstrapMethods;
    private int typeCount;
    private Entry[] typeTable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymbolTable(ClassWriter classWriter) {
        this.f4148a = classWriter;
        this.sourceClassReader = null;
        this.entries = new Entry[256];
        this.constantPoolCount = 1;
        this.constantPool = new ByteVector();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymbolTable(ClassWriter classWriter, ClassReader classReader) {
        this.f4148a = classWriter;
        this.sourceClassReader = classReader;
        byte[] bArr = classReader.f4136a;
        int item = classReader.getItem(1) - 1;
        int i = classReader.header - item;
        this.constantPoolCount = classReader.getItemCount();
        this.constantPool = new ByteVector(i);
        this.constantPool.putByteArray(bArr, item, i);
        this.entries = new Entry[this.constantPoolCount << 1];
        char[] cArr = new char[classReader.getMaxStringLength()];
        boolean z = false;
        int i2 = 1;
        while (true) {
            int i3 = i2;
            if (i3 < this.constantPoolCount) {
                int item2 = classReader.getItem(i3);
                byte b2 = bArr[item2 - 1];
                switch (b2) {
                    case 1:
                        addConstantUtf8(i3, classReader.a(i3, cArr));
                        break;
                    case 2:
                    case 13:
                    case 14:
                    default:
                        throw new IllegalArgumentException();
                    case 3:
                    case 4:
                        addConstantIntegerOrFloat(i3, b2, classReader.readInt(item2));
                        break;
                    case 5:
                    case 6:
                        addConstantLongOrDouble(i3, b2, classReader.readLong(item2));
                        break;
                    case 7:
                    case 8:
                    case 16:
                    case 19:
                    case 20:
                        addConstantUtf8Reference(i3, b2, classReader.readUTF8(item2, cArr));
                        break;
                    case 9:
                    case 10:
                    case 11:
                        int item3 = classReader.getItem(classReader.readUnsignedShort(item2 + 2));
                        addConstantMemberReference(i3, b2, classReader.readClass(item2, cArr), classReader.readUTF8(item3, cArr), classReader.readUTF8(item3 + 2, cArr));
                        break;
                    case 12:
                        addConstantNameAndType(i3, classReader.readUTF8(item2, cArr), classReader.readUTF8(item2 + 2, cArr));
                        break;
                    case 15:
                        int item4 = classReader.getItem(classReader.readUnsignedShort(item2 + 1));
                        int item5 = classReader.getItem(classReader.readUnsignedShort(item4 + 2));
                        addConstantMethodHandle(i3, classReader.readByte(item2), classReader.readClass(item4, cArr), classReader.readUTF8(item5, cArr), classReader.readUTF8(item5 + 2, cArr));
                        break;
                    case 17:
                    case 18:
                        z = true;
                        int item6 = classReader.getItem(classReader.readUnsignedShort(item2 + 2));
                        addConstantDynamicOrInvokeDynamicReference(b2, i3, classReader.readUTF8(item6, cArr), classReader.readUTF8(item6 + 2, cArr), classReader.readUnsignedShort(item2));
                        break;
                }
                i2 = i3 + ((b2 == 5 || b2 == 6) ? 2 : 1);
            } else {
                if (z) {
                    copyBootstrapMethods(classReader, cArr);
                    return;
                }
                return;
            }
        }
    }

    private void copyBootstrapMethods(ClassReader classReader, char[] cArr) {
        int i;
        byte[] bArr = classReader.f4136a;
        int a2 = classReader.a();
        int readUnsignedShort = classReader.readUnsignedShort(a2 - 2);
        while (true) {
            if (readUnsignedShort <= 0) {
                break;
            }
            if ("BootstrapMethods".equals(classReader.readUTF8(a2, cArr))) {
                this.bootstrapMethodCount = classReader.readUnsignedShort(a2 + 6);
                break;
            } else {
                a2 += 6 + classReader.readInt(a2 + 2);
                readUnsignedShort--;
            }
        }
        if (this.bootstrapMethodCount > 0) {
            int i2 = a2 + 8;
            int readInt = classReader.readInt(a2 + 2) - 2;
            this.bootstrapMethods = new ByteVector(readInt);
            this.bootstrapMethods.putByteArray(bArr, i2, readInt);
            int i3 = i2;
            for (int i4 = 0; i4 < this.bootstrapMethodCount; i4++) {
                int i5 = i3 - i2;
                int readUnsignedShort2 = classReader.readUnsignedShort(i3);
                int i6 = i3 + 2;
                int readUnsignedShort3 = classReader.readUnsignedShort(i6);
                i3 = i6 + 2;
                int hashCode = classReader.readConst(readUnsignedShort2, cArr).hashCode();
                while (true) {
                    i = hashCode;
                    int i7 = readUnsignedShort3;
                    readUnsignedShort3--;
                    if (i7 > 0) {
                        int readUnsignedShort4 = classReader.readUnsignedShort(i3);
                        i3 += 2;
                        hashCode = i ^ classReader.readConst(readUnsignedShort4, cArr).hashCode();
                    }
                }
                add(new Entry(i4, 64, i5, i & Integer.MAX_VALUE));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ClassReader a() {
        return this.sourceClassReader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        return this.majorVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String c() {
        return this.className;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(int i, String str) {
        this.majorVersion = i;
        this.className = str;
        return a(str).f4146a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int d() {
        return this.constantPoolCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int e() {
        return this.constantPool.f4134b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        byteVector.putShort(this.constantPoolCount).putByteArray(this.constantPool.f4133a, 0, this.constantPool.f4134b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int f() {
        if (this.bootstrapMethods != null) {
            b("BootstrapMethods");
            return 8 + this.bootstrapMethods.f4134b;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(ByteVector byteVector) {
        if (this.bootstrapMethods != null) {
            byteVector.putShort(b("BootstrapMethods")).putInt(this.bootstrapMethods.f4134b + 2).putShort(this.bootstrapMethodCount).putByteArray(this.bootstrapMethods.f4133a, 0, this.bootstrapMethods.f4134b);
        }
    }

    private Entry get(int i) {
        Entry[] entryArr = this.entries;
        return entryArr[i % entryArr.length];
    }

    private Entry put(Entry entry) {
        if (this.entryCount > (this.entries.length * 3) / 4) {
            int length = this.entries.length;
            int i = (length << 1) + 1;
            Entry[] entryArr = new Entry[i];
            for (int i2 = length - 1; i2 >= 0; i2--) {
                Entry entry2 = this.entries[i2];
                while (true) {
                    Entry entry3 = entry2;
                    if (entry3 != null) {
                        int i3 = entry3.h % i;
                        Entry entry4 = entry3.i;
                        entry3.i = entryArr[i3];
                        entryArr[i3] = entry3;
                        entry2 = entry4;
                    }
                }
            }
            this.entries = entryArr;
        }
        this.entryCount++;
        int length2 = entry.h % this.entries.length;
        entry.i = this.entries[length2];
        this.entries[length2] = entry;
        return entry;
    }

    private void add(Entry entry) {
        this.entryCount++;
        int length = entry.h % this.entries.length;
        entry.i = this.entries[length];
        this.entries[length] = entry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(Object obj) {
        if (obj instanceof Integer) {
            return a(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return a(((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return a((int) ((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return a(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return a(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return a(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return a(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return a(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return g((String) obj);
        }
        if (obj instanceof Type) {
            Type type = (Type) obj;
            int sort = type.getSort();
            if (sort == 10) {
                return a(type.getInternalName());
            }
            if (sort == 11) {
                return c(type.getDescriptor());
            }
            return a(type.getDescriptor());
        }
        if (obj instanceof Handle) {
            Handle handle = (Handle) obj;
            return a(handle.getTag(), handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface());
        }
        if (obj instanceof ConstantDynamic) {
            ConstantDynamic constantDynamic = (ConstantDynamic) obj;
            return a(constantDynamic.getName(), constantDynamic.getDescriptor(), constantDynamic.getBootstrapMethod(), constantDynamic.a());
        }
        throw new IllegalArgumentException("value " + obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(String str) {
        return addConstantUtf8Reference(7, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(String str, String str2, String str3) {
        return addConstantMemberReference(9, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(String str, String str2, String str3, boolean z) {
        return addConstantMemberReference(z ? 11 : 10, str, str2, str3);
    }

    private Entry addConstantMemberReference(int i, String str, String str2, String str3) {
        int hash = hash(i, str, str2, str3);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == i && entry2.h == hash && entry2.c.equals(str) && entry2.d.equals(str2) && entry2.e.equals(str3)) {
                    return entry2;
                }
                entry = entry2.i;
            } else {
                this.constantPool.b(i, a(str).f4146a, a(str2, str3));
                int i2 = this.constantPoolCount;
                this.constantPoolCount = i2 + 1;
                return put(new Entry(i2, i, str, str2, str3, 0L, hash));
            }
        }
    }

    private void addConstantMemberReference(int i, int i2, String str, String str2, String str3) {
        add(new Entry(i, i2, str, str2, str3, 0L, hash(i2, str, str2, str3)));
    }

    private Symbol g(String str) {
        return addConstantUtf8Reference(8, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(int i) {
        return addConstantIntegerOrFloat(3, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(float f) {
        return addConstantIntegerOrFloat(4, Float.floatToRawIntBits(f));
    }

    private Symbol addConstantIntegerOrFloat(int i, int i2) {
        int hash = hash(i, i2);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == i && entry2.h == hash && entry2.f == i2) {
                    return entry2;
                }
                entry = entry2.i;
            } else {
                this.constantPool.putByte(i).putInt(i2);
                int i3 = this.constantPoolCount;
                this.constantPoolCount = i3 + 1;
                return put(new Entry(i3, i, i2, hash));
            }
        }
    }

    private void addConstantIntegerOrFloat(int i, int i2, int i3) {
        add(new Entry(i, i2, i3, hash(i2, i3)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(long j) {
        return addConstantLongOrDouble(5, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(double d) {
        return addConstantLongOrDouble(6, Double.doubleToRawLongBits(d));
    }

    private Symbol addConstantLongOrDouble(int i, long j) {
        int hash = hash(i, j);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == i && entry2.h == hash && entry2.f == j) {
                    return entry2;
                }
                entry = entry2.i;
            } else {
                int i2 = this.constantPoolCount;
                this.constantPool.putByte(i).putLong(j);
                this.constantPoolCount += 2;
                return put(new Entry(i2, i, j, hash));
            }
        }
    }

    private void addConstantLongOrDouble(int i, int i2, long j) {
        add(new Entry(i, i2, j, hash(i2, j)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, String str2) {
        int hash = hash(12, str, str2);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == 12 && entry2.h == hash && entry2.d.equals(str) && entry2.e.equals(str2)) {
                    return entry2.f4146a;
                }
                entry = entry2.i;
            } else {
                this.constantPool.b(12, b(str), b(str2));
                int i = this.constantPoolCount;
                this.constantPoolCount = i + 1;
                return put(new Entry(i, 12, str, str2, hash)).f4146a;
            }
        }
    }

    private void addConstantNameAndType(int i, String str, String str2) {
        add(new Entry(i, 12, str, str2, hash(12, str, str2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b(String str) {
        int hash = hash(1, str);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == 1 && entry2.h == hash && entry2.e.equals(str)) {
                    return entry2.f4146a;
                }
                entry = entry2.i;
            } else {
                this.constantPool.putByte(1).putUTF8(str);
                int i = this.constantPoolCount;
                this.constantPoolCount = i + 1;
                return put(new Entry(i, 1, str, hash)).f4146a;
            }
        }
    }

    private void addConstantUtf8(int i, String str) {
        add(new Entry(i, 1, str, hash(1, str)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(int i, String str, String str2, String str3, boolean z) {
        int hash = hash(15, str, str2, str3, i);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == 15 && entry2.h == hash && entry2.f == i && entry2.c.equals(str) && entry2.d.equals(str2) && entry2.e.equals(str3)) {
                    return entry2;
                }
                entry = entry2.i;
            } else {
                if (i <= 4) {
                    this.constantPool.a(15, i, a(str, str2, str3).f4146a);
                } else {
                    this.constantPool.a(15, i, a(str, str2, str3, z).f4146a);
                }
                int i2 = this.constantPoolCount;
                this.constantPoolCount = i2 + 1;
                return put(new Entry(i2, 15, str, str2, str3, i, hash));
            }
        }
    }

    private void addConstantMethodHandle(int i, int i2, String str, String str2, String str3) {
        add(new Entry(i, 15, str, str2, str3, i2, hash(15, str, str2, str3, i2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol c(String str) {
        return addConstantUtf8Reference(16, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol a(String str, String str2, Handle handle, Object... objArr) {
        return addConstantDynamicOrInvokeDynamicReference(17, str, str2, a(handle, objArr).f4146a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol b(String str, String str2, Handle handle, Object... objArr) {
        return addConstantDynamicOrInvokeDynamicReference(18, str, str2, a(handle, objArr).f4146a);
    }

    private Symbol addConstantDynamicOrInvokeDynamicReference(int i, String str, String str2, int i2) {
        int hash = hash(i, str, str2, i2);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == i && entry2.h == hash && entry2.f == i2 && entry2.d.equals(str) && entry2.e.equals(str2)) {
                    return entry2;
                }
                entry = entry2.i;
            } else {
                this.constantPool.b(i, i2, a(str, str2));
                int i3 = this.constantPoolCount;
                this.constantPoolCount = i3 + 1;
                return put(new Entry(i3, i, null, str, str2, i2, hash));
            }
        }
    }

    private void addConstantDynamicOrInvokeDynamicReference(int i, int i2, String str, String str2, int i3) {
        add(new Entry(i2, i, null, str, str2, i3, hash(i, str, str2, i3)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol d(String str) {
        return addConstantUtf8Reference(19, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol e(String str) {
        return addConstantUtf8Reference(20, str);
    }

    private Symbol addConstantUtf8Reference(int i, String str) {
        int hash = hash(i, str);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == i && entry2.h == hash && entry2.e.equals(str)) {
                    return entry2;
                }
                entry = entry2.i;
            } else {
                this.constantPool.b(i, b(str));
                int i2 = this.constantPoolCount;
                this.constantPoolCount = i2 + 1;
                return put(new Entry(i2, i, str, hash));
            }
        }
    }

    private void addConstantUtf8Reference(int i, int i2, String str) {
        add(new Entry(i, i2, str, hash(i2, str)));
    }

    private Symbol a(Handle handle, Object... objArr) {
        ByteVector byteVector = this.bootstrapMethods;
        ByteVector byteVector2 = byteVector;
        if (byteVector == null) {
            ByteVector byteVector3 = new ByteVector();
            this.bootstrapMethods = byteVector3;
            byteVector2 = byteVector3;
        }
        int length = objArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = a(objArr[i]).f4146a;
        }
        int i2 = byteVector2.f4134b;
        byteVector2.putShort(a(handle.getTag(), handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface()).f4146a);
        byteVector2.putShort(length);
        for (int i3 = 0; i3 < length; i3++) {
            byteVector2.putShort(iArr[i3]);
        }
        int i4 = byteVector2.f4134b - i2;
        int hashCode = handle.hashCode();
        for (Object obj : objArr) {
            hashCode ^= obj.hashCode();
        }
        return addBootstrapMethod(i2, i4, hashCode & Integer.MAX_VALUE);
    }

    private Symbol addBootstrapMethod(int i, int i2, int i3) {
        byte[] bArr = this.bootstrapMethods.f4133a;
        Entry entry = get(i3);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == 64 && entry2.h == i3) {
                    int i4 = (int) entry2.f;
                    boolean z = true;
                    int i5 = 0;
                    while (true) {
                        if (i5 >= i2) {
                            break;
                        }
                        if (bArr[i + i5] == bArr[i4 + i5]) {
                            i5++;
                        } else {
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        this.bootstrapMethods.f4134b = i;
                        return entry2;
                    }
                }
                entry = entry2.i;
            } else {
                int i6 = this.bootstrapMethodCount;
                this.bootstrapMethodCount = i6 + 1;
                return put(new Entry(i6, 64, i, i3));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Symbol b(int i) {
        return this.typeTable[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int f(String str) {
        int hash = hash(128, str);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == 128 && entry2.h == hash && entry2.e.equals(str)) {
                    return entry2.f4146a;
                }
                entry = entry2.i;
            } else {
                return addTypeInternal(new Entry(this.typeCount, 128, str, hash));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, int i) {
        int hash = hash(129, str, i);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == 129 && entry2.h == hash && entry2.f == i && entry2.e.equals(str)) {
                    return entry2.f4146a;
                }
                entry = entry2.i;
            } else {
                return addTypeInternal(new Entry(this.typeCount, 129, str, i, hash));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(int i, int i2) {
        long j;
        if (i < i2) {
            j = i | (i2 << 32);
        } else {
            j = i2 | (i << 32);
        }
        long j2 = j;
        int hash = hash(130, i + i2);
        Entry entry = get(hash);
        while (true) {
            Entry entry2 = entry;
            if (entry2 != null) {
                if (entry2.f4147b == 130 && entry2.h == hash && entry2.f == j2) {
                    return entry2.g;
                }
                entry = entry2.i;
            } else {
                int f = f(this.f4148a.getCommonSuperClass(this.typeTable[i].e, this.typeTable[i2].e));
                put(new Entry(this.typeCount, 130, j2, hash)).g = f;
                return f;
            }
        }
    }

    private int addTypeInternal(Entry entry) {
        if (this.typeTable == null) {
            this.typeTable = new Entry[16];
        }
        if (this.typeCount == this.typeTable.length) {
            Entry[] entryArr = new Entry[2 * this.typeTable.length];
            System.arraycopy(this.typeTable, 0, entryArr, 0, this.typeTable.length);
            this.typeTable = entryArr;
        }
        Entry[] entryArr2 = this.typeTable;
        int i = this.typeCount;
        this.typeCount = i + 1;
        entryArr2[i] = entry;
        return put(entry).f4146a;
    }

    private static int hash(int i, int i2) {
        return Integer.MAX_VALUE & (i + i2);
    }

    private static int hash(int i, long j) {
        return Integer.MAX_VALUE & (i + ((int) j) + ((int) (j >>> 32)));
    }

    private static int hash(int i, String str) {
        return Integer.MAX_VALUE & (i + str.hashCode());
    }

    private static int hash(int i, String str, int i2) {
        return Integer.MAX_VALUE & (i + str.hashCode() + i2);
    }

    private static int hash(int i, String str, String str2) {
        return Integer.MAX_VALUE & (i + (str.hashCode() * str2.hashCode()));
    }

    private static int hash(int i, String str, String str2, int i2) {
        return Integer.MAX_VALUE & (i + (str.hashCode() * str2.hashCode() * (i2 + 1)));
    }

    private static int hash(int i, String str, String str2, String str3) {
        return Integer.MAX_VALUE & (i + (str.hashCode() * str2.hashCode() * str3.hashCode()));
    }

    private static int hash(int i, String str, String str2, String str3, int i2) {
        return Integer.MAX_VALUE & (i + (str.hashCode() * str2.hashCode() * str3.hashCode() * i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/SymbolTable$Entry.class */
    public static class Entry extends Symbol {
        final int h;
        Entry i;

        Entry(int i, int i2, String str, String str2, String str3, long j, int i3) {
            super(i, i2, str, str2, str3, j);
            this.h = i3;
        }

        Entry(int i, int i2, String str, int i3) {
            super(i, i2, null, null, str, 0L);
            this.h = i3;
        }

        Entry(int i, int i2, String str, long j, int i3) {
            super(i, 129, null, null, str, j);
            this.h = i3;
        }

        Entry(int i, int i2, String str, String str2, int i3) {
            super(i, 12, null, str, str2, 0L);
            this.h = i3;
        }

        Entry(int i, int i2, long j, int i3) {
            super(i, i2, null, null, null, j);
            this.h = i3;
        }
    }
}
