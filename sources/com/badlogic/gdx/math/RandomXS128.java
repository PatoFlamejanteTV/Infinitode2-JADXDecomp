package com.badlogic.gdx.math;

import java.util.Random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/RandomXS128.class */
public class RandomXS128 extends Random {
    private static final double NORM_DOUBLE = 1.1102230246251565E-16d;
    private static final double NORM_FLOAT = 5.960464477539063E-8d;
    private long seed0;
    private long seed1;

    /*  JADX ERROR: Failed to decode insn: 0x0024: MOVE_MULTI, method: com.badlogic.gdx.math.RandomXS128.nextLong():long
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[7]
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
        	at jadx.core.ProcessClass.process(ProcessClass.java:70)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
        */
    @Override // java.util.Random
    public long nextLong() {
        /*
            r7 = this;
            r0 = r7
            long r0 = r0.seed0
            r8 = r0
            r0 = r7
            long r0 = r0.seed1
            r10 = r0
            r0 = r7
            r1 = r10
            r0.seed0 = r1
            r0 = r8
            r1 = r0; r0 = r0; 
            r2 = 23
            long r1 = r1 << r2
            long r0 = r0 ^ r1
            r8 = r0
            r0 = r7
            r1 = r8
            r2 = r10
            long r1 = r1 ^ r2
            r2 = r8
            r3 = 17
            long r2 = r2 >>> r3
            long r1 = r1 ^ r2
            r2 = r10
            r3 = 26
            long r2 = r2 >>> r3
            long r1 = r1 ^ r2
            // decode failed: arraycopy: source index -1 out of bounds for object array[7]
            r0.seed1 = r1
            r0 = r10
            long r-1 = r-1 + r0
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.math.RandomXS128.nextLong():long");
    }

    public RandomXS128() {
        setSeed(new Random().nextLong());
    }

    public RandomXS128(long j) {
        setSeed(j);
    }

    public RandomXS128(long j, long j2) {
        setState(j, j2);
    }

    @Override // java.util.Random
    protected final int next(int i) {
        return (int) (nextLong() & ((1 << i) - 1));
    }

    @Override // java.util.Random
    public int nextInt() {
        return (int) nextLong();
    }

    @Override // java.util.Random
    public int nextInt(int i) {
        return (int) nextLong(i);
    }

    public long nextLong(long j) {
        long nextLong;
        long j2;
        if (j <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        do {
            nextLong = nextLong() >>> 1;
            j2 = nextLong % j;
        } while ((nextLong - j2) + (j - 1) < 0);
        return j2;
    }

    @Override // java.util.Random
    public double nextDouble() {
        return (nextLong() >>> 11) * NORM_DOUBLE;
    }

    @Override // java.util.Random
    public float nextFloat() {
        return (float) ((nextLong() >>> 40) * NORM_FLOAT);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return (nextLong() & 1) != 0;
    }

    @Override // java.util.Random
    public void nextBytes(byte[] bArr) {
        int length = bArr.length;
        while (length != 0) {
            int i = length < 8 ? length : 8;
            long nextLong = nextLong();
            while (true) {
                long j = nextLong;
                int i2 = i;
                i--;
                if (i2 != 0) {
                    length--;
                    bArr[length] = (byte) j;
                    nextLong = j >> 8;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Random
    public void setSeed(long j) {
        setState(this, murmurHash3(murmurHash3(j == 0 ? Long.MIN_VALUE : j)));
    }

    public void setState(long j, long j2) {
        this.seed0 = j;
        this.seed1 = j2;
    }

    public long getState(int i) {
        return i == 0 ? this.seed0 : this.seed1;
    }

    private static final long murmurHash3(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }
}
