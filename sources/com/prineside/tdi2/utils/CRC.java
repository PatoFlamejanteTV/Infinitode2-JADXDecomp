package com.prineside.tdi2.utils;

import org.lwjgl.system.windows.User32;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1017)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:239)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:154)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:64)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:57)
    */
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/CRC.class */
public final class CRC {

    /* renamed from: a, reason: collision with root package name */
    private Parameters f3813a;

    /* renamed from: b, reason: collision with root package name */
    private long f3814b;
    private long[] c;
    private long d;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/CRC$Parameters.class */
    public static final class Parameters {

        /* renamed from: a, reason: collision with root package name */
        private int f3815a;

        /* renamed from: b, reason: collision with root package name */
        private long f3816b;
        private boolean c;
        private boolean d;
        private long e;
        private long f;
        public static final Parameters CCITT = new Parameters(16, 4129, User32.HWND_BROADCAST, false, false, 0);
        public static final Parameters CRC16 = new Parameters(16, 32773, 0, true, true, 0);
        public static final Parameters XMODEM = new Parameters(16, 4129, 0, false, false, 0);
        public static final Parameters XMODEM2 = new Parameters(16, 33800, 0, true, true, 0);
        public static final Parameters CRC32;
        public static final Parameters IEEE;
        public static final Parameters Castagnoli;
        public static final Parameters CRC32C;
        public static final Parameters Koopman;
        public static final Parameters CRC64ISO;
        public static final Parameters CRC64ECMA;

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.utils.CRC.Parameters.a(com.prineside.tdi2.utils.CRC$Parameters, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long a(com.prineside.tdi2.utils.CRC.Parameters r6, long r7) {
            /*
                r0 = r6
                r1 = 0
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.e = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.utils.CRC.Parameters.a(com.prineside.tdi2.utils.CRC$Parameters, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.utils.CRC.Parameters.b(com.prineside.tdi2.utils.CRC$Parameters, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long b(com.prineside.tdi2.utils.CRC.Parameters r6, long r7) {
            /*
                r0 = r6
                r1 = 0
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.f = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.utils.CRC.Parameters.b(com.prineside.tdi2.utils.CRC$Parameters, long):long");
        }

        static /* synthetic */ boolean a(Parameters parameters, boolean z) {
            parameters.d = z;
            return z;
        }

        public Parameters(int i, long j, long j2, boolean z, boolean z2, long j3) {
            this.f3815a = i;
            this.f3816b = j;
            this.c = z;
            this.d = z2;
            this.e = j2;
            this.f = j3;
        }

        public Parameters(Parameters parameters) {
            this.f3815a = parameters.f3815a;
            this.f3816b = parameters.f3816b;
            this.c = parameters.c;
            this.d = parameters.d;
            this.e = parameters.e;
            this.f = parameters.f;
        }

        public final int getWidth() {
            return this.f3815a;
        }

        public final long getPolynomial() {
            return this.f3816b;
        }

        public final boolean isReflectIn() {
            return this.c;
        }

        public final boolean isReflectOut() {
            return this.d;
        }

        public final long getInit() {
            return this.e;
        }

        public final long getFinalXor() {
            return this.f;
        }

        static {
            Parameters parameters = new Parameters(32, 79764919L, 4294967295L, true, true, 4294967295L);
            CRC32 = parameters;
            IEEE = parameters;
            Parameters parameters2 = new Parameters(32, 517762881L, 4294967295L, true, true, 4294967295L);
            Castagnoli = parameters2;
            CRC32C = parameters2;
            Koopman = new Parameters(32, 1947962583L, 4294967295L, true, true, 4294967295L);
            CRC64ISO = new Parameters(64, 27L, -1L, true, true, -1L);
            CRC64ECMA = new Parameters(64, 4823603603198064275L, -1L, true, true, -1L);
        }
    }

    private static long a(long j, int i) {
        long j2;
        long j3 = j;
        for (int i2 = 0; i2 < i; i2++) {
            long j4 = 1 << ((i - i2) - 1);
            if ((j & (1 << i2)) != 0) {
                j2 = j3 | j4;
            } else {
                j2 = j3 & (j4 ^ (-1));
            }
            j3 = j2;
        }
        return j3;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static long calculateCRC(Parameters parameters, byte[] bArr) {
        return calculateCRC(parameters, bArr, 0, bArr.length);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static long calculateCRC(Parameters parameters, byte[] bArr, int i, int i2) {
        long j = parameters.e;
        long j2 = 1 << (parameters.f3815a - 1);
        long j3 = (j2 << 1) - 1;
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            long j4 = bArr[i4] & 255;
            if (parameters.c) {
                j4 = a(j4, 8);
            }
            int i5 = 128;
            while (true) {
                int i6 = i5;
                if (i6 != 0) {
                    long j5 = j & j2;
                    j <<= 1;
                    if ((j4 & i6) != 0) {
                        j5 ^= j2;
                    }
                    if (j5 != 0) {
                        j ^= parameters.f3816b;
                    }
                    i5 = i6 >> 1;
                }
            }
        }
        if (parameters.d) {
            j = a(j, parameters.f3815a);
        }
        return (j ^ parameters.f) & j3;
    }

    public final long init() {
        return this.f3814b;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final long update(long j, byte[] bArr, int i, int i2) {
        if (!this.f3813a.c) {
            if (this.f3813a.f3815a < 8) {
                for (int i3 = 0; i3 < i2; i3++) {
                    j = this.c[(((byte) (j << (8 - this.f3813a.f3815a))) ^ bArr[i + i3]) & 255] ^ (j << 8);
                }
            } else {
                for (int i4 = 0; i4 < i2; i4++) {
                    j = this.c[(((byte) (j >>> (this.f3813a.f3815a - 8))) ^ bArr[i + i4]) & 255] ^ (j << 8);
                }
            }
        } else {
            for (int i5 = 0; i5 < i2; i5++) {
                j = this.c[(((byte) j) ^ bArr[i + i5]) & 255] ^ (j >>> 8);
            }
        }
        return j;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final long update(long j, byte[] bArr) {
        return update(j, bArr, 0, bArr.length);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final long finalCRC(long j) {
        long j2 = j;
        if (this.f3813a.d != this.f3813a.c) {
            j2 = a(j, this.f3813a.f3815a);
        }
        return (j2 ^ this.f3813a.f) & this.d;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final long calculateCRC(byte[] bArr) {
        return calculateCRC(bArr, 0, bArr.length);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final long calculateCRC(byte[] bArr, int i, int i2) {
        return finalCRC(update(init(), bArr, i, i2));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.utils.CRC.Parameters.a(com.prineside.tdi2.utils.CRC$Parameters, long):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Class not yet loaded at codegen stage: com.prineside.tdi2.utils.CRC
        	at jadx.core.dex.nodes.ClassNode.reloadAtCodegenStage(ClassNode.java:883)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:66)
        	... 1 more
        */
    public CRC(com.prineside.tdi2.utils.CRC.Parameters r7) {
        /*
            r6 = this;
            r0 = r6
            r0.<init>()
            r0 = r6
            com.prineside.tdi2.utils.CRC$Parameters r1 = new com.prineside.tdi2.utils.CRC$Parameters
            r2 = r1
            r3 = r7
            r2.<init>(r3)
            r0.f3813a = r1
            r0 = r6
            r1 = r7
            boolean r1 = com.prineside.tdi2.utils.CRC.Parameters.c(r1)
            if (r1 == 0) goto L26
            r1 = r7
            long r1 = com.prineside.tdi2.utils.CRC.Parameters.a(r1)
            r2 = r7
            int r2 = com.prineside.tdi2.utils.CRC.Parameters.b(r2)
            long r1 = a(r1, r2)
            goto L2a
        L26:
            r1 = r7
            long r1 = com.prineside.tdi2.utils.CRC.Parameters.a(r1)
        L2a:
            r0.f3814b = r1
            r0 = r6
            r1 = r7
            int r1 = com.prineside.tdi2.utils.CRC.Parameters.b(r1)
            r2 = 64
            if (r1 < r2) goto L3b
            r1 = 0
            goto L41
        L3b:
            r1 = 1
            r2 = r7
            int r2 = com.prineside.tdi2.utils.CRC.Parameters.b(r2)
            long r1 = r1 << r2
        L41:
            r2 = 1
            long r1 = r1 - r2
            r0.d = r1
            r0 = r6
            r1 = 256(0x100, float:3.59E-43)
            long[] r1 = new long[r1]
            r0.c = r1
            r0 = 1
            byte[] r0 = new byte[r0]
            r8 = r0
            com.prineside.tdi2.utils.CRC$Parameters r0 = new com.prineside.tdi2.utils.CRC$Parameters
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            r1 = r0
            r7 = r1
            r1 = 0
            long r0 = com.prineside.tdi2.utils.CRC.Parameters.a(r0, r1)
            r0 = r7
            r1 = r0
            boolean r1 = com.prineside.tdi2.utils.CRC.Parameters.c(r1)
            boolean r0 = com.prineside.tdi2.utils.CRC.Parameters.a(r0, r1)
            r0 = r7
            r1 = 0
            long r0 = com.prineside.tdi2.utils.CRC.Parameters.b(r0, r1)
            r0 = 0
            r9 = r0
        L73:
            r0 = r9
            r1 = 256(0x100, float:3.59E-43)
            if (r0 >= r1) goto L90
            r0 = r8
            r1 = 0
            r2 = r9
            byte r2 = (byte) r2
            r0[r1] = r2
            r0 = r6
            long[] r0 = r0.c
            r1 = r9
            r2 = r7
            r3 = r8
            long r2 = calculateCRC(r2, r3)
            r0[r1] = r2
            int r9 = r9 + 1
            goto L73
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.utils.CRC.<init>(com.prineside.tdi2.utils.CRC$Parameters):void");
    }

    public final byte finalCRC8(long j) {
        if (this.f3813a.f3815a != 8) {
            throw new RuntimeException("CRC width mismatch");
        }
        return (byte) finalCRC(j);
    }

    public final short finalCRC16(long j) {
        if (this.f3813a.f3815a != 16) {
            throw new RuntimeException("CRC width mismatch");
        }
        return (short) finalCRC(j);
    }

    public final int finalCRC32(long j) {
        if (this.f3813a.f3815a != 32) {
            throw new RuntimeException("CRC width mismatch");
        }
        return (int) finalCRC(j);
    }
}
