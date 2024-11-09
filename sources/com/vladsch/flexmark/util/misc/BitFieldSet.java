package com.vladsch.flexmark.util.misc;

import android.database.CursorJoiner;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.lang.reflect.Field;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/BitFieldSet.class */
public class BitFieldSet<E extends Enum<E>> extends AbstractSet<E> implements Serializable, Cloneable {
    private static final long serialVersionUID = 3411599620347842686L;
    long elements = 0;
    final Class<E> elementType;
    final E[] universe;
    final long[] bitMasks;
    final int totalBits;
    static final Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/BitFieldSet$UniverseLoader.class */
    public static class UniverseLoader {
        static final ConcurrentHashMap<Class, Enum[]> enumUniverseMap;
        static final ConcurrentHashMap<Class, long[]> enumBitMasksMap;
        static final /* synthetic */ boolean $assertionsDisabled;

        UniverseLoader() {
        }

        static {
            $assertionsDisabled = !BitFieldSet.class.desiredAssertionStatus();
            enumUniverseMap = new ConcurrentHashMap<>();
            enumBitMasksMap = new ConcurrentHashMap<>();
        }

        public static Enum[] getUniverseSlow(Class cls) {
            Enum<?>[] enumArr;
            if (!$assertionsDisabled && !cls.isEnum()) {
                throw new AssertionError();
            }
            Enum[] enumArr2 = enumUniverseMap.get(cls);
            if (enumArr2 != null) {
                return enumArr2;
            }
            Field[] fields = cls.getFields();
            int i = 0;
            for (Field field : fields) {
                if (field.getType().isEnum()) {
                    i++;
                }
            }
            if (i > 0) {
                enumArr = new Enum[i];
                int i2 = 0;
                for (Field field2 : fields) {
                    if (field2.getType().isEnum()) {
                        int i3 = i2;
                        i2++;
                        enumArr[i3] = Enum.valueOf(field2.getType(), field2.getName());
                    }
                }
            } else {
                enumArr = BitFieldSet.ZERO_LENGTH_ENUM_ARRAY;
            }
            enumUniverseMap.put(cls, enumArr);
            return enumArr;
        }
    }

    public static long nextBitMask(int i, int i2) {
        return ((-1) >>> (-i2)) << i;
    }

    public static <E extends Enum<E>> E[] getUniverse(Class<E> cls) {
        return (E[]) UniverseLoader.getUniverseSlow(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E extends Enum<E>> long[] getBitMasks(Class<E> cls) {
        long[] jArr;
        long[] jArr2 = UniverseLoader.enumBitMasksMap.get(cls);
        if (jArr2 != null) {
            return jArr2;
        }
        Enum[] universeSlow = UniverseLoader.getUniverseSlow(cls);
        if (BitField.class.isAssignableFrom(cls)) {
            int i = 0;
            jArr = new long[universeSlow.length];
            for (CursorJoiner.Result result : universeSlow) {
                int bits = ((BitField) result).getBits();
                if (bits > 0) {
                    if (i + bits > 64) {
                        throw new IllegalArgumentException(String.format("Enum bit field %s.%s bits exceed available 64 bits by %d", cls.getSimpleName(), result.name(), Integer.valueOf((i + bits) - 64)));
                    }
                    jArr[result.ordinal()] = nextBitMask(i, bits);
                    i += bits;
                } else {
                    throw new IllegalArgumentException(String.format("Enum bit field %s.%s bits must be >= 1, got: %d", cls.getSimpleName(), result.name(), Integer.valueOf(bits)));
                }
            }
        } else if (universeSlow.length <= 64) {
            jArr = new long[universeSlow.length];
            for (CursorJoiner.Result result2 : universeSlow) {
                jArr[result2.ordinal()] = 1 << result2.ordinal();
            }
        } else {
            throw new IllegalArgumentException("Enums with more than 64 values are not supported");
        }
        UniverseLoader.enumBitMasksMap.put(cls, jArr);
        return jArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    BitFieldSet(Class<E> cls, Enum<?>[] enumArr, long[] jArr) {
        this.elementType = cls;
        this.universe = (E[]) enumArr;
        this.bitMasks = jArr;
        this.totalBits = getTotalBits(jArr);
    }

    public static int getTotalBits(long[] jArr) {
        if (jArr.length == 0) {
            return 0;
        }
        return 64 - Long.numberOfLeadingZeros(jArr[jArr.length - 1]);
    }

    void addRange(E e, E e2) {
        this.elements = ((-1) >>> ((e.ordinal() - e2.ordinal()) - 1)) << e.ordinal();
    }

    void addAll() {
        if (this.totalBits != 0) {
            this.elements = (-1) >>> (-this.totalBits);
        }
    }

    public void complement() {
        if (this.totalBits != 0) {
            this.elements ^= -1;
            this.elements &= (-1) >>> (-this.totalBits);
        }
    }

    public long toLong() {
        return this.elements;
    }

    public int toInt() {
        if (this.totalBits > 32) {
            throw new IllegalArgumentException(String.format("Enum fields use %d bits, which is more than 32 bits available in an int", Integer.valueOf(this.totalBits)));
        }
        return (int) this.elements;
    }

    public short toShort() {
        if (this.totalBits > 16) {
            throw new IllegalArgumentException(String.format("Enum fields use %d bits, which is more than 16 bits available in a short", Integer.valueOf(this.totalBits)));
        }
        return (short) this.elements;
    }

    public byte toByte() {
        if (this.totalBits > 8) {
            throw new IllegalArgumentException(String.format("Enum fields use %d bits, which is more than 8 bits available in a byte", Integer.valueOf(this.totalBits)));
        }
        return (byte) this.elements;
    }

    public long allBitsMask() {
        return (-1) >>> (-this.totalBits);
    }

    public boolean orMask(long j) {
        long j2 = (-1) >>> (-this.totalBits);
        if ((j & (j2 ^ (-1))) != 0) {
            throw new IllegalArgumentException(String.format("bitMask %d value contains elements outside the universe %s", Long.valueOf(j), Long.toBinaryString(j & (j2 ^ (-1)))));
        }
        long j3 = this.elements;
        this.elements |= j;
        return j3 != this.elements;
    }

    @Deprecated
    public boolean replaceAll(long j) {
        return setAll(j);
    }

    public boolean setAll(long j) {
        long j2 = (-1) >>> (-this.totalBits);
        if ((j & (j2 ^ (-1))) != 0) {
            throw new IllegalArgumentException(String.format("mask %d(0b%s) value contains elements outside the universe 0b%s", Long.valueOf(j), Long.toBinaryString(j), Long.toBinaryString(j & (j2 ^ (-1)))));
        }
        long j3 = this.elements;
        this.elements = j;
        return j3 != this.elements;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        if (this.elements == 0) {
            return this.elementType.getSimpleName() + ": { }";
        }
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(", ");
        delimitedBuilder.append(this.elementType.getSimpleName()).append(": { ");
        for (E e : this.universe) {
            if (any(mask((BitFieldSet<E>) e))) {
                delimitedBuilder.append(e.name());
                if ((e instanceof BitField) && ((BitField) e).getBits() > 1) {
                    delimitedBuilder.append("(").append(getLong(e)).append(")");
                }
                delimitedBuilder.mark();
            }
        }
        delimitedBuilder.unmark().append(" }");
        return delimitedBuilder.toString();
    }

    public boolean andNotMask(long j) {
        long j2 = this.elements;
        this.elements &= j ^ (-1);
        return j2 != this.elements;
    }

    public boolean any(long j) {
        return (this.elements & j) != 0;
    }

    public boolean none(long j) {
        return (this.elements & j) == 0;
    }

    public boolean all(long j) {
        long j2 = (-1) >>> (-this.totalBits);
        if ((j & (j2 ^ (-1))) != 0) {
            throw new IllegalArgumentException(String.format("mask %d(0b%s) value contains elements outside the universe 0b%s", Long.valueOf(j), Long.toBinaryString(j), Long.toBinaryString(j & (j2 ^ (-1)))));
        }
        return (this.elements & j) == j;
    }

    public static <E extends Enum<E>> long longMask(E e) {
        return getBitMasks(e.getDeclaringClass())[e.ordinal()];
    }

    public static <E extends Enum<E>> int intMask(E e) {
        long[] bitMasks = getBitMasks(e.getDeclaringClass());
        int totalBits = getTotalBits(bitMasks);
        if (totalBits > 32) {
            throw new IllegalArgumentException(String.format("Enum fields use %d, which is more than 32 available in int", Integer.valueOf(totalBits)));
        }
        return (int) bitMasks[e.ordinal()];
    }

    public long get(E e) {
        long j = this.bitMasks[e.ordinal()];
        return (this.elements & j) >>> Long.numberOfTrailingZeros(j);
    }

    public boolean setUnsigned(E e, long j) {
        long j2 = this.elements;
        this.elements = setUnsigned(this.elementType, this.bitMasks, this.elements, e, j);
        return j2 != this.elements;
    }

    public boolean setSigned(E e, long j) {
        long j2 = this.elements;
        this.elements = setSigned(this.elementType, this.bitMasks, this.elements, e, j);
        return j2 != this.elements;
    }

    public void setBitField(E e, long j) {
        setSigned(e, j);
    }

    public void setBitField(E e, int i) {
        setSigned(e, i);
    }

    public void setBitField(E e, short s) {
        setSigned(e, s);
    }

    public void setBitField(E e, byte b2) {
        setSigned(e, b2);
    }

    public void setUnsignedField(E e, long j) {
        setUnsigned(e, j);
    }

    public void setUnsignedField(E e, int i) {
        setUnsigned(e, i);
    }

    public void setUnsignedField(E e, short s) {
        setUnsigned(e, s);
    }

    public void setUnsignedField(E e, byte b2) {
        setUnsigned(e, b2);
    }

    public long getUnsigned(E e, int i, String str) {
        return getUnsignedBitField(this.elements, e, i, str);
    }

    public long getSigned(E e, int i, String str) {
        return getSignedBitField(this.elements, e, i, str);
    }

    public long getLong(E e) {
        return getSigned(e, 64, "long");
    }

    public int getInt(E e) {
        return (int) getSigned(e, 32, "int");
    }

    public short getShort(E e) {
        return (short) getSigned(e, 16, "short");
    }

    public byte getByte(E e) {
        return (byte) getSigned(e, 8, "byte");
    }

    public int getUInt(E e) {
        return (int) getSigned(e, 32, "int");
    }

    public short getUShort(E e) {
        return (short) getSigned(e, 16, "short");
    }

    public byte getUByte(E e) {
        return (byte) getSigned(e, 8, "byte");
    }

    public static long orMask(long j, long j2) {
        return j | j2;
    }

    public static long andNotMask(long j, long j2) {
        return j & (j2 ^ (-1));
    }

    public static boolean any(long j, long j2) {
        return (j & j2) != 0;
    }

    public static boolean all(long j, long j2) {
        return (j & j2) == j2;
    }

    public static boolean none(long j, long j2) {
        return (j & j2) == 0;
    }

    public long mask(E e) {
        return this.bitMasks[e.ordinal()];
    }

    public long mask(E e, E e2) {
        return this.bitMasks[e.ordinal()] | this.bitMasks[e2.ordinal()];
    }

    public long mask(E e, E e2, E e3) {
        return this.bitMasks[e.ordinal()] | this.bitMasks[e2.ordinal()] | this.bitMasks[e3.ordinal()];
    }

    public long mask(E e, E e2, E e3, E e4) {
        return this.bitMasks[e.ordinal()] | this.bitMasks[e2.ordinal()] | this.bitMasks[e3.ordinal()] | this.bitMasks[e4.ordinal()];
    }

    public long mask(E e, E e2, E e3, E e4, E e5) {
        return this.bitMasks[e.ordinal()] | this.bitMasks[e2.ordinal()] | this.bitMasks[e3.ordinal()] | this.bitMasks[e4.ordinal()] | this.bitMasks[e5.ordinal()];
    }

    @SafeVarargs
    public final long mask(E... eArr) {
        long j = 0;
        for (E e : eArr) {
            j |= this.bitMasks[e.ordinal()];
        }
        return j;
    }

    public boolean add(E e, E e2) {
        return orMask(mask(e, e2));
    }

    public boolean add(E e, E e2, E e3) {
        return orMask(mask(e, e2, e3));
    }

    public boolean add(E e, E e2, E e3, E e4) {
        return orMask(mask(e, e2, e3, e4));
    }

    public boolean add(E e, E e2, E e3, E e4, E e5) {
        return orMask(mask(e, e2, e3, e4, e5));
    }

    @SafeVarargs
    public final boolean add(E... eArr) {
        return orMask(mask(eArr));
    }

    public boolean remove(E e, E e2) {
        return andNotMask(mask(e, e2));
    }

    public boolean remove(E e, E e2, E e3) {
        return andNotMask(mask(e, e2, e3));
    }

    public boolean remove(E e, E e2, E e3, E e4) {
        return andNotMask(mask(e, e2, e3, e4));
    }

    public boolean remove(E e, E e2, E e3, E e4, E e5) {
        return andNotMask(mask(e, e2, e3, e4, e5));
    }

    @SafeVarargs
    public final boolean remove(E... eArr) {
        return andNotMask(mask(eArr));
    }

    public boolean any(E e) {
        return any(mask((BitFieldSet<E>) e));
    }

    public boolean any(E e, E e2) {
        return any(mask(e, e2));
    }

    public boolean any(E e, E e2, E e3) {
        return any(mask(e, e2, e3));
    }

    public boolean any(E e, E e2, E e3, E e4) {
        return any(mask(e, e2, e3, e4));
    }

    public boolean any(E e, E e2, E e3, E e4, E e5) {
        return any(mask(e, e2, e3, e4, e5));
    }

    @SafeVarargs
    public final boolean any(E... eArr) {
        return any(mask(eArr));
    }

    public boolean all(E e) {
        return all(mask((BitFieldSet<E>) e));
    }

    public boolean all(E e, E e2) {
        return all(mask(e, e2));
    }

    public boolean all(E e, E e2, E e3) {
        return all(mask(e, e2, e3));
    }

    public boolean all(E e, E e2, E e3, E e4) {
        return all(mask(e, e2, e3, e4));
    }

    public boolean all(E e, E e2, E e3, E e4, E e5) {
        return all(mask(e, e2, e3, e4, e5));
    }

    @SafeVarargs
    public final boolean all(E... eArr) {
        return all(mask(eArr));
    }

    public boolean none(E e) {
        return none(mask((BitFieldSet<E>) e));
    }

    public boolean none(E e, E e2) {
        return none(mask(e, e2));
    }

    public boolean none(E e, E e2, E e3) {
        return none(mask(e, e2, e3));
    }

    public boolean none(E e, E e2, E e3, E e4) {
        return none(mask(e, e2, e3, e4));
    }

    public boolean none(E e, E e2, E e3, E e4, E e5) {
        return none(mask(e, e2, e3, e4, e5));
    }

    @SafeVarargs
    public final boolean none(E... eArr) {
        return none(mask(eArr));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return this.bitMasks.length == this.totalBits ? new EnumBitSetIterator() : new EnumBitFieldIterator();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/BitFieldSet$EnumBitSetIterator.class */
    private class EnumBitSetIterator<E extends Enum<E>> implements Iterator<E> {
        long unseen;
        long lastReturned = 0;

        EnumBitSetIterator() {
            this.unseen = BitFieldSet.this.elements;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.unseen != 0;
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.unseen == 0) {
                throw new NoSuchElementException();
            }
            this.lastReturned = this.unseen & (-this.unseen);
            this.unseen -= this.lastReturned;
            return BitFieldSet.this.universe[Long.numberOfTrailingZeros(this.lastReturned)];
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastReturned == 0) {
                throw new IllegalStateException();
            }
            BitFieldSet.this.elements &= this.lastReturned ^ (-1);
            this.lastReturned = 0L;
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/BitFieldSet$EnumBitFieldIterator.class */
    private class EnumBitFieldIterator<E extends Enum<E>> implements Iterator<E> {
        int lastReturnedIndex = -1;
        int nextIndex = -1;

        EnumBitFieldIterator() {
            findNext();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < BitFieldSet.this.universe.length;
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.nextIndex >= BitFieldSet.this.universe.length) {
                throw new NoSuchElementException();
            }
            this.lastReturnedIndex = this.nextIndex;
            findNext();
            return BitFieldSet.this.universe[this.lastReturnedIndex];
        }

        void findNext() {
            do {
                this.nextIndex++;
                if (this.nextIndex >= BitFieldSet.this.universe.length) {
                    return;
                }
            } while ((BitFieldSet.this.elements & BitFieldSet.this.bitMasks[this.nextIndex]) == 0);
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }
            BitFieldSet.this.elements &= BitFieldSet.this.bitMasks[this.lastReturnedIndex] ^ (-1);
            this.lastReturnedIndex = -1;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.totalBits;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.elements == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        Class<?> cls = obj.getClass();
        return (cls == this.elementType || cls.getSuperclass() == this.elementType) && (this.elements & this.bitMasks[((Enum) obj).ordinal()]) != 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e) {
        typeCheck(e);
        long j = this.elements;
        this.elements |= this.bitMasks[e.ordinal()];
        return this.elements != j;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        Class<?> cls = obj.getClass();
        if (cls != this.elementType && cls.getSuperclass() != this.elementType) {
            return false;
        }
        long j = this.elements;
        this.elements &= this.bitMasks[((Enum) obj).ordinal()] ^ (-1);
        return this.elements != j;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        if (!(collection instanceof BitFieldSet)) {
            return super.containsAll(collection);
        }
        BitFieldSet bitFieldSet = (BitFieldSet) collection;
        if (bitFieldSet.elementType != this.elementType) {
            return bitFieldSet.isEmpty();
        }
        return (bitFieldSet.elements & (this.elements ^ (-1))) == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        if (!(collection instanceof BitFieldSet)) {
            return super.addAll(collection);
        }
        BitFieldSet bitFieldSet = (BitFieldSet) collection;
        if (bitFieldSet.elementType != this.elementType) {
            if (bitFieldSet.isEmpty()) {
                return false;
            }
            throw new ClassCastException(bitFieldSet.elementType + " != " + this.elementType);
        }
        long j = this.elements;
        this.elements |= bitFieldSet.elements;
        return this.elements != j;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        if (!(collection instanceof BitFieldSet)) {
            return super.removeAll(collection);
        }
        BitFieldSet bitFieldSet = (BitFieldSet) collection;
        if (bitFieldSet.elementType != this.elementType) {
            return false;
        }
        long j = this.elements;
        this.elements &= bitFieldSet.elements ^ (-1);
        return this.elements != j;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        if (!(collection instanceof BitFieldSet)) {
            return super.retainAll(collection);
        }
        BitFieldSet bitFieldSet = (BitFieldSet) collection;
        if (bitFieldSet.elementType != this.elementType) {
            boolean z = this.elements != 0;
            this.elements = 0L;
            return z;
        }
        long j = this.elements;
        this.elements &= bitFieldSet.elements;
        return this.elements != j;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.elements = 0L;
    }

    public static <T extends Enum<T>> BitFieldSet<T> of(Class<T> cls, long j) {
        BitFieldSet<T> noneOf = noneOf(cls);
        noneOf.orMask(j);
        return noneOf;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitFieldSet<E> m1707clone() {
        try {
            return (BitFieldSet) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    final void typeCheck(E e) {
        Class<?> cls = e.getClass();
        if (cls != this.elementType && cls.getSuperclass() != this.elementType) {
            throw new ClassCastException(cls + " != " + this.elementType);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/BitFieldSet$SerializationProxy.class */
    private static class SerializationProxy<E extends Enum<E>> implements Serializable {
        private final Class<E> elementType;
        private final long bits;
        private static final long serialVersionUID = 362491234563181265L;

        SerializationProxy(BitFieldSet<E> bitFieldSet) {
            this.elementType = bitFieldSet.elementType;
            this.bits = bitFieldSet.elements;
        }

        private Object readResolve() {
            BitFieldSet noneOf = BitFieldSet.noneOf(this.elementType);
            noneOf.orMask(this.bits);
            return noneOf;
        }
    }

    Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Proxy required");
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (!(obj instanceof BitFieldSet)) {
            return super.equals(obj);
        }
        BitFieldSet bitFieldSet = (BitFieldSet) obj;
        return bitFieldSet.elementType != this.elementType ? this.elements == 0 && bitFieldSet.elements == 0 : bitFieldSet.elements == this.elements;
    }

    public static <E extends Enum<E>> BitFieldSet<E> noneOf(Class<E> cls) {
        if (!cls.isEnum()) {
            throw new ClassCastException(cls + " not an enum");
        }
        return new BitFieldSet<>(cls, getUniverse(cls), getBitMasks(cls));
    }

    static <E extends Enum<E>> long setSigned(long j, E e, long j2) {
        Class<E> declaringClass = e.getDeclaringClass();
        return setSigned(declaringClass, getBitMasks(declaringClass), j, e, j2);
    }

    static <E extends Enum<E>> long setSigned(Class<E> cls, long[] jArr, long j, E e, long j2) {
        long j3 = jArr[e.ordinal()];
        int bitCount = Long.bitCount(j3);
        long j4 = 1 << (bitCount - 1);
        if (bitCount < 64 && (j2 < (-j4) || j2 > j4 - 1)) {
            Object[] objArr = new Object[7];
            objArr[0] = cls.getSimpleName();
            objArr[1] = e.name();
            objArr[2] = Integer.valueOf(bitCount);
            objArr[3] = bitCount > 1 ? "s" : "";
            objArr[4] = Long.valueOf(-j4);
            objArr[5] = Long.valueOf(j4 - 1);
            objArr[6] = Long.valueOf(j2);
            throw new IllegalArgumentException(String.format("Enum field %s.%s is %d bit%s, value range is [%d, %d], cannot be set to %d", objArr));
        }
        return j ^ ((j ^ (j2 << Long.numberOfTrailingZeros(j3))) & j3);
    }

    static <E extends Enum<E>> long setUnsigned(long j, E e, long j2) {
        Class<E> declaringClass = e.getDeclaringClass();
        return setUnsigned(declaringClass, getBitMasks(declaringClass), j, e, j2);
    }

    static <E extends Enum<E>> long setUnsigned(Class<E> cls, long[] jArr, long j, E e, long j2) {
        long j3 = jArr[e.ordinal()];
        int bitCount = Long.bitCount(j3);
        long j4 = 1 << bitCount;
        if (bitCount < 64 && (j2 < 0 || j2 >= j4)) {
            Object[] objArr = new Object[6];
            objArr[0] = cls.getSimpleName();
            objArr[1] = e.name();
            objArr[2] = Integer.valueOf(bitCount);
            objArr[3] = bitCount > 1 ? "s" : "";
            objArr[4] = Long.valueOf(j4 - 1);
            objArr[5] = Long.valueOf(j2);
            throw new IllegalArgumentException(String.format("Enum field %s.%s is %d bit%s, value range is [0, %d), cannot be set to %d", objArr));
        }
        return j ^ ((j ^ (j2 << Long.numberOfTrailingZeros(j3))) & j3);
    }

    public static <E extends Enum<E>> long setBitField(long j, E e, int i) {
        return setUnsigned(j, e, i);
    }

    public static <E extends Enum<E>> int setBitField(int i, E e, int i2) {
        return (int) setUnsigned(i, e, i2);
    }

    public static <E extends Enum<E>> short setBitField(short s, E e, short s2) {
        return (short) setUnsigned(s, e, s2);
    }

    public static <E extends Enum<E>> byte setBitField(byte b2, E e, byte b3) {
        return (byte) setUnsigned(b2, e, b3);
    }

    public static <E extends Enum<E>> long getUnsignedBitField(long j, E e, int i, String str) {
        Class<E> declaringClass = e.getDeclaringClass();
        long j2 = getBitMasks(declaringClass)[e.ordinal()];
        int bitCount = Long.bitCount(j2);
        if (bitCount <= i) {
            return (j & j2) >>> Long.numberOfTrailingZeros(j2);
        }
        throw new IllegalArgumentException(String.format("Enum field %s.%s uses %d, which is more than %d available in %s", declaringClass.getSimpleName(), e.name(), Integer.valueOf(bitCount), Integer.valueOf(i), str));
    }

    static <E extends Enum<E>> long getSignedBitField(long j, E e, int i, String str) {
        Class<E> declaringClass = e.getDeclaringClass();
        long j2 = getBitMasks(declaringClass)[e.ordinal()];
        int bitCount = Long.bitCount(j2);
        if (bitCount <= i) {
            return (j << Long.numberOfLeadingZeros(j2)) >> (64 - bitCount);
        }
        throw new IllegalArgumentException(String.format("Enum field %s.%s uses %d, which is more than %d available in %s", declaringClass.getSimpleName(), e.name(), Integer.valueOf(bitCount), Integer.valueOf(i), str));
    }

    public static <E extends Enum<E>> long getBitField(long j, E e) {
        return getUnsignedBitField(j, e, 64, "long");
    }

    public static <E extends Enum<E>> int getBitField(int i, E e) {
        return (int) getUnsignedBitField(i, e, 32, "int");
    }

    public static <E extends Enum<E>> short getBitField(short s, E e) {
        return (short) getUnsignedBitField(s, e, 16, "short");
    }

    public static <E extends Enum<E>> byte getBitField(byte b2, E e) {
        return (byte) getUnsignedBitField(b2, e, 8, "byte");
    }

    public static <E extends Enum<E>> BitFieldSet<E> allOf(Class<E> cls) {
        BitFieldSet<E> noneOf = noneOf(cls);
        noneOf.addAll();
        return noneOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> copyOf(BitFieldSet<E> bitFieldSet) {
        return bitFieldSet.m1707clone();
    }

    public static <E extends Enum<E>> BitFieldSet<E> copyOf(Collection<E> collection) {
        if (collection instanceof BitFieldSet) {
            return ((BitFieldSet) collection).m1707clone();
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        }
        Iterator<E> it = collection.iterator();
        BitFieldSet<E> of = of((Enum) it.next());
        while (it.hasNext()) {
            of.add((BitFieldSet<E>) it.next());
        }
        return of;
    }

    public static <E extends Enum<E>> BitFieldSet<E> complementOf(BitFieldSet<E> bitFieldSet) {
        BitFieldSet<E> copyOf = copyOf((BitFieldSet) bitFieldSet);
        copyOf.complement();
        return copyOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> of(E e) {
        BitFieldSet<E> noneOf = noneOf(e.getDeclaringClass());
        noneOf.add((BitFieldSet<E>) e);
        return noneOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> of(E e, E e2) {
        BitFieldSet<E> noneOf = noneOf(e.getDeclaringClass());
        noneOf.add((BitFieldSet<E>) e);
        noneOf.add((BitFieldSet<E>) e2);
        return noneOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> of(E e, E e2, E e3) {
        BitFieldSet<E> noneOf = noneOf(e.getDeclaringClass());
        noneOf.add((BitFieldSet<E>) e);
        noneOf.add((BitFieldSet<E>) e2);
        noneOf.add((BitFieldSet<E>) e3);
        return noneOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> of(E e, E e2, E e3, E e4) {
        BitFieldSet<E> noneOf = noneOf(e.getDeclaringClass());
        noneOf.add((BitFieldSet<E>) e);
        noneOf.add((BitFieldSet<E>) e2);
        noneOf.add((BitFieldSet<E>) e3);
        noneOf.add((BitFieldSet<E>) e4);
        return noneOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> of(E e, E e2, E e3, E e4, E e5) {
        BitFieldSet<E> noneOf = noneOf(e.getDeclaringClass());
        noneOf.add((BitFieldSet<E>) e);
        noneOf.add((BitFieldSet<E>) e2);
        noneOf.add((BitFieldSet<E>) e3);
        noneOf.add((BitFieldSet<E>) e4);
        noneOf.add((BitFieldSet<E>) e5);
        return noneOf;
    }

    @SafeVarargs
    public static <E extends Enum<E>> BitFieldSet<E> of(E e, E... eArr) {
        BitFieldSet<E> noneOf = noneOf(e.getDeclaringClass());
        noneOf.add((BitFieldSet<E>) e);
        for (E e2 : eArr) {
            noneOf.add((BitFieldSet<E>) e2);
        }
        return noneOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> of(Class<E> cls, E[] eArr) {
        BitFieldSet<E> noneOf = noneOf(cls);
        for (E e : eArr) {
            noneOf.add((BitFieldSet<E>) e);
        }
        return noneOf;
    }

    public static <E extends Enum<E>> BitFieldSet<E> range(E e, E e2) {
        if (e.compareTo(e2) > 0) {
            throw new IllegalArgumentException(e + " > " + e2);
        }
        BitFieldSet<E> noneOf = noneOf(e.getDeclaringClass());
        noneOf.addRange(e, e2);
        return noneOf;
    }
}
