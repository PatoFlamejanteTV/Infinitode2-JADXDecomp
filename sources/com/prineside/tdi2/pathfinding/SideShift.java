package com.prineside.tdi2.pathfinding;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/SideShift.class */
public final class SideShift {
    public static final int SIDE_SHIFTS_COUNT = 11;
    public static final int MIDDLE_SIDE_SHIFT = 5;
    public static final float SIDE_SHIFT_DISTANCE = 0.0625f;
    public static final int[][] SIDE_SHIFT_BY_COUNT;

    private SideShift() {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [int[], int[][]] */
    static {
        ?? r0 = new int[12];
        SIDE_SHIFT_BY_COUNT = r0;
        int[] iArr = new int[1];
        iArr[0] = 5;
        r0[1] = iArr;
        int[][] iArr2 = SIDE_SHIFT_BY_COUNT;
        int[] iArr3 = new int[2];
        iArr3[0] = 3;
        iArr3[1] = 7;
        iArr2[2] = iArr3;
        int[][] iArr4 = SIDE_SHIFT_BY_COUNT;
        int[] iArr5 = new int[3];
        iArr5[0] = 2;
        iArr5[1] = 5;
        iArr5[2] = 8;
        iArr4[3] = iArr5;
        int[][] iArr6 = SIDE_SHIFT_BY_COUNT;
        int[] iArr7 = new int[4];
        iArr7[0] = 2;
        iArr7[1] = 4;
        iArr7[2] = 6;
        iArr7[3] = 8;
        iArr6[4] = iArr7;
        int[][] iArr8 = SIDE_SHIFT_BY_COUNT;
        int[] iArr9 = new int[5];
        iArr9[0] = 1;
        iArr9[1] = 3;
        iArr9[2] = 5;
        iArr9[3] = 7;
        iArr9[4] = 9;
        iArr8[5] = iArr9;
        int[][] iArr10 = SIDE_SHIFT_BY_COUNT;
        int[] iArr11 = new int[6];
        iArr11[0] = 0;
        iArr11[1] = 2;
        iArr11[2] = 4;
        iArr11[3] = 6;
        iArr11[4] = 8;
        iArr11[5] = 10;
        iArr10[6] = iArr11;
        int[][] iArr12 = SIDE_SHIFT_BY_COUNT;
        int[] iArr13 = new int[7];
        iArr13[0] = 0;
        iArr13[1] = 2;
        iArr13[2] = 4;
        iArr13[3] = 5;
        iArr13[4] = 6;
        iArr13[5] = 8;
        iArr13[6] = 10;
        iArr12[7] = iArr13;
        int[][] iArr14 = SIDE_SHIFT_BY_COUNT;
        int[] iArr15 = new int[8];
        iArr15[0] = 0;
        iArr15[1] = 2;
        iArr15[2] = 3;
        iArr15[3] = 4;
        iArr15[4] = 6;
        iArr15[5] = 7;
        iArr15[6] = 8;
        iArr15[7] = 10;
        iArr14[8] = iArr15;
        int[][] iArr16 = SIDE_SHIFT_BY_COUNT;
        int[] iArr17 = new int[9];
        iArr17[0] = 0;
        iArr17[1] = 1;
        iArr17[2] = 2;
        iArr17[3] = 4;
        iArr17[4] = 5;
        iArr17[5] = 6;
        iArr17[6] = 8;
        iArr17[7] = 9;
        iArr17[8] = 10;
        iArr16[9] = iArr17;
        int[][] iArr18 = SIDE_SHIFT_BY_COUNT;
        int[] iArr19 = new int[10];
        iArr19[0] = 0;
        iArr19[1] = 1;
        iArr19[2] = 2;
        iArr19[3] = 3;
        iArr19[4] = 4;
        iArr19[5] = 6;
        iArr19[6] = 7;
        iArr19[7] = 8;
        iArr19[8] = 9;
        iArr19[9] = 10;
        iArr18[10] = iArr19;
        int[][] iArr20 = SIDE_SHIFT_BY_COUNT;
        int[] iArr21 = new int[11];
        iArr21[0] = 0;
        iArr21[1] = 1;
        iArr21[2] = 2;
        iArr21[3] = 3;
        iArr21[4] = 4;
        iArr21[5] = 5;
        iArr21[6] = 6;
        iArr21[7] = 7;
        iArr21[8] = 8;
        iArr21[9] = 9;
        iArr21[10] = 10;
        iArr20[11] = iArr21;
    }
}
