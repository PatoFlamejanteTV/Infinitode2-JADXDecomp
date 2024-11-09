package org.lwjgl.system;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.system.Pointer;
import org.lwjgl.system.Struct;

/* loaded from: infinitode-2.jar:org/lwjgl/system/Struct.class */
public abstract class Struct<SELF extends Struct<SELF>> extends Pointer.Default {
    protected static final int DEFAULT_PACK_ALIGNMENT;
    protected static final int DEFAULT_ALIGN_AS = 0;
    protected ByteBuffer container;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:org/lwjgl/system/Struct$StructValidation.class */
    public interface StructValidation {
        void validate(long j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract SELF create(long j, ByteBuffer byteBuffer);

    public abstract int sizeof();

    static {
        DEFAULT_PACK_ALIGNMENT = Platform.get() == Platform.WINDOWS ? 8 : 1073741824;
        Library.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Struct(long j, ByteBuffer byteBuffer) {
        super(j);
        this.container = byteBuffer;
    }

    public void clear() {
        MemoryUtil.memSet(address(), 0, sizeof());
    }

    public void free() {
        MemoryUtil.nmemFree(address());
    }

    public boolean isNull(int i) {
        if (Checks.DEBUG) {
            checkMemberOffset(i);
        }
        return MemoryUtil.memGetAddress(address() + ((long) i)) == 0;
    }

    private void checkMemberOffset(int i) {
        if (i < 0 || sizeof() - i < POINTER_SIZE) {
            throw new IllegalArgumentException("Invalid member offset.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ByteBuffer __checkContainer(ByteBuffer byteBuffer, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i);
        }
        return byteBuffer;
    }

    private static long getBytes(int i, int i2) {
        return (i & 4294967295L) * i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long __checkMalloc(int i, int i2) {
        long j = (i & 4294967295L) * i2;
        if (Checks.DEBUG) {
            if (i < 0) {
                throw new IllegalArgumentException("Invalid number of elements");
            }
            if (BITS32 && 4294967295L < j) {
                throw new IllegalArgumentException("The request allocation is too large");
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ByteBuffer __create(int i, int i2) {
        APIUtil.apiCheckAllocation(i, getBytes(i, i2), 2147483647L);
        return ByteBuffer.allocateDirect(i * i2).order(ByteOrder.nativeOrder());
    }

    protected static <T extends Struct<T>> ByteBuffer __getContainer(T t) {
        return t.container;
    }

    protected static ByteBuffer __getContainer(StructBuffer<?, ?> structBuffer) {
        return structBuffer.container;
    }

    public static void validate(long j, int i, int i2, StructValidation structValidation) {
        for (int i3 = 0; i3 < i; i3++) {
            structValidation.validate(j + (Integer.toUnsignedLong(i3) * i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/Struct$Member.class */
    public static class Member {
        final int size;
        final int alignment;
        final boolean forcedAlignment;
        int offset;

        Member(int i, int i2, boolean z) {
            this.size = i;
            this.alignment = i2;
            this.forcedAlignment = z;
        }

        public int getSize() {
            return this.size;
        }

        public int getAlignment() {
            return this.alignment;
        }

        public int getAlignment(int i) {
            return this.forcedAlignment ? this.alignment : Math.min(this.alignment, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/Struct$Layout.class */
    public static class Layout extends Member {
        final Member[] members;

        Layout(int i, int i2, boolean z, Member[] memberArr) {
            super(i, i2, z);
            this.members = memberArr;
        }

        public int offsetof(int i) {
            return this.members[i].offset;
        }
    }

    protected static Member __padding(int i, boolean z) {
        return __padding(i, 1, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Member __padding(int i, int i2, boolean z) {
        return __member(z ? i * i2 : 0, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Member __member(int i) {
        return __member(i, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Member __member(int i, int i2) {
        return __member(i, i2, false);
    }

    protected static Member __member(int i, int i2, boolean z) {
        return new Member(i, i2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Member __array(int i, int i2) {
        return __array(i, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Member __array(int i, int i2, int i3) {
        return new Member(i * i3, i2, false);
    }

    protected static Member __array(int i, int i2, boolean z, int i3) {
        return new Member(i * i3, i2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Layout __union(Member... memberArr) {
        return __union(DEFAULT_PACK_ALIGNMENT, 0, memberArr);
    }

    protected static Layout __union(int i, int i2, Member... memberArr) {
        ArrayList arrayList = new ArrayList(memberArr.length);
        int i3 = 0;
        int i4 = i2;
        for (Member member : memberArr) {
            i3 = Math.max(i3, member.size);
            i4 = Math.max(i4, member.getAlignment(i));
            member.offset = 0;
            arrayList.add(member);
            if (member instanceof Layout) {
                addNestedMembers(member, arrayList, 0);
            }
        }
        return new Layout(i3, i4, i2 != 0, (Member[]) arrayList.toArray(new Member[0]));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Layout __struct(Member... memberArr) {
        return __struct(DEFAULT_PACK_ALIGNMENT, 0, memberArr);
    }

    protected static Layout __struct(int i, int i2, Member... memberArr) {
        ArrayList arrayList = new ArrayList(memberArr.length);
        int i3 = 0;
        int i4 = i2;
        for (Member member : memberArr) {
            int alignment = member.getAlignment(i);
            member.offset = align(i3, alignment);
            i3 = member.offset + member.size;
            i4 = Math.max(i4, alignment);
            arrayList.add(member);
            if (member instanceof Layout) {
                addNestedMembers(member, arrayList, member.offset);
            }
        }
        return new Layout(align(i3, i4), i4, i2 != 0, (Member[]) arrayList.toArray(new Member[0]));
    }

    private static void addNestedMembers(Member member, List<Member> list, int i) {
        for (Member member2 : ((Layout) member).members) {
            member2.offset += i;
            list.add(member2);
        }
    }

    private static int align(int i, int i2) {
        return ((i - 1) | (i2 - 1)) + 1;
    }
}
