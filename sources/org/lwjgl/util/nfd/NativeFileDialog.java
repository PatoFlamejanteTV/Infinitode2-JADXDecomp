package org.lwjgl.util.nfd;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.util.nfd.NFDFilterItem;

/* loaded from: infinitode-2.jar:org/lwjgl/util/nfd/NativeFileDialog.class */
public class NativeFileDialog {
    public static final int NFD_ERROR = 0;
    public static final int NFD_OKAY = 1;
    public static final int NFD_CANCEL = 2;

    public static native void nNFD_FreePath(long j);

    @NativeType("nfdresult_t")
    public static native int NFD_Init();

    public static native void NFD_Quit();

    public static native int nNFD_OpenDialog(long j, long j2, int i, long j3);

    public static native int nNFD_OpenDialogMultiple(long j, long j2, int i, long j3);

    public static native int nNFD_SaveDialog(long j, long j2, int i, long j3, long j4);

    public static native int nNFD_PickFolder(long j, long j2);

    public static native long nNFD_GetError();

    public static native void NFD_ClearError();

    public static native int nNFD_PathSet_GetCount(long j, long j2);

    public static native int nNFD_PathSet_GetPath(long j, int i, long j2);

    public static native void nNFD_PathSet_FreePath(long j);

    public static native int nNFD_PathSet_GetEnum(long j, long j2);

    public static native void nNFD_PathSet_FreeEnum(long j);

    public static native int nNFD_PathSet_EnumNext(long j, long j2);

    public static native void nNFD_PathSet_Free(long j);

    public static native int nNFD_PathSet_GetCount(long j, int[] iArr);

    static {
        LibNFD.initialize();
    }

    protected NativeFileDialog() {
        throw new UnsupportedOperationException();
    }

    public static void NFD_FreePath(@NativeType("nfdchar_t *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nNFD_FreePath(MemoryUtil.memAddress(byteBuffer));
    }

    public static void NFD_FreePath(@NativeType("nfdchar_t *") long j) {
        nNFD_FreePath(j);
    }

    @NativeType("nfdresult_t")
    public static int NFD_OpenDialog(@NativeType("nfdchar_t **") PointerBuffer pointerBuffer, @NativeType("nfdfilteritem_t const *") NFDFilterItem.Buffer buffer, @NativeType("nfdchar_t const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.checkNT1Safe(byteBuffer);
            if (buffer != null) {
                Struct.validate(buffer.address(), Checks.remainingSafe(buffer), NFDFilterItem.SIZEOF, NFDFilterItem::validate);
            }
        }
        return nNFD_OpenDialog(MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(buffer), Checks.remainingSafe(buffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("nfdresult_t")
    public static int NFD_OpenDialog(@NativeType("nfdchar_t **") PointerBuffer pointerBuffer, @NativeType("nfdfilteritem_t const *") NFDFilterItem.Buffer buffer, @NativeType("nfdchar_t const *") CharSequence charSequence) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            if (buffer != null) {
                Struct.validate(buffer.address(), Checks.remainingSafe(buffer), NFDFilterItem.SIZEOF, NFDFilterItem::validate);
            }
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return nNFD_OpenDialog(MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(buffer), Checks.remainingSafe(buffer), charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("nfdresult_t")
    public static int NFD_OpenDialogMultiple(@NativeType("nfdpathset_t const **") PointerBuffer pointerBuffer, @NativeType("nfdfilteritem_t const *") NFDFilterItem.Buffer buffer, @NativeType("nfdchar_t const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.checkNT1Safe(byteBuffer);
            if (buffer != null) {
                Struct.validate(buffer.address(), Checks.remainingSafe(buffer), NFDFilterItem.SIZEOF, NFDFilterItem::validate);
            }
        }
        return nNFD_OpenDialogMultiple(MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(buffer), Checks.remainingSafe(buffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("nfdresult_t")
    public static int NFD_OpenDialogMultiple(@NativeType("nfdpathset_t const **") PointerBuffer pointerBuffer, @NativeType("nfdfilteritem_t const *") NFDFilterItem.Buffer buffer, @NativeType("nfdchar_t const *") CharSequence charSequence) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            if (buffer != null) {
                Struct.validate(buffer.address(), Checks.remainingSafe(buffer), NFDFilterItem.SIZEOF, NFDFilterItem::validate);
            }
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return nNFD_OpenDialogMultiple(MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(buffer), Checks.remainingSafe(buffer), charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("nfdresult_t")
    public static int NFD_SaveDialog(@NativeType("nfdchar_t **") PointerBuffer pointerBuffer, @NativeType("nfdfilteritem_t const *") NFDFilterItem.Buffer buffer, @NativeType("nfdchar_t const *") ByteBuffer byteBuffer, @NativeType("nfdchar_t const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.checkNT1Safe(byteBuffer);
            Checks.checkNT1Safe(byteBuffer2);
            if (buffer != null) {
                Struct.validate(buffer.address(), Checks.remainingSafe(buffer), NFDFilterItem.SIZEOF, NFDFilterItem::validate);
            }
        }
        return nNFD_SaveDialog(MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(buffer), Checks.remainingSafe(buffer), MemoryUtil.memAddressSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer2));
    }

    @NativeType("nfdresult_t")
    public static int NFD_SaveDialog(@NativeType("nfdchar_t **") PointerBuffer pointerBuffer, @NativeType("nfdfilteritem_t const *") NFDFilterItem.Buffer buffer, @NativeType("nfdchar_t const *") CharSequence charSequence, @NativeType("nfdchar_t const *") CharSequence charSequence2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            if (buffer != null) {
                Struct.validate(buffer.address(), Checks.remainingSafe(buffer), NFDFilterItem.SIZEOF, NFDFilterItem::validate);
            }
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            long pointerAddress = charSequence == null ? 0L : stackGet.getPointerAddress();
            stackGet.nUTF8Safe(charSequence2, true);
            return nNFD_SaveDialog(MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(buffer), Checks.remainingSafe(buffer), pointerAddress, charSequence2 == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("nfdresult_t")
    public static int NFD_PickFolder(@NativeType("nfdchar_t **") PointerBuffer pointerBuffer, @NativeType("nfdchar_t const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.checkNT1Safe(byteBuffer);
        }
        return nNFD_PickFolder(MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("nfdresult_t")
    public static int NFD_PickFolder(@NativeType("nfdchar_t **") PointerBuffer pointerBuffer, @NativeType("nfdchar_t const *") CharSequence charSequence) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return nNFD_PickFolder(MemoryUtil.memAddress(pointerBuffer), charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("char const *")
    public static String NFD_GetError() {
        return MemoryUtil.memUTF8Safe(nNFD_GetError());
    }

    @NativeType("nfdresult_t")
    public static int NFD_PathSet_GetCount(@NativeType("nfdpathset_t const *") long j, @NativeType("nfdpathsetsize_t *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check((Buffer) intBuffer, 1);
        }
        return nNFD_PathSet_GetCount(j, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("nfdresult_t")
    public static int NFD_PathSet_GetPath(@NativeType("nfdpathset_t const *") long j, @NativeType("nfdpathsetsize_t") int i, @NativeType("nfdchar_t **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nNFD_PathSet_GetPath(j, i, MemoryUtil.memAddress(pointerBuffer));
    }

    public static void NFD_PathSet_FreePath(@NativeType("nfdchar_t *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nNFD_PathSet_FreePath(MemoryUtil.memAddress(byteBuffer));
    }

    public static void NFD_PathSet_FreePath(@NativeType("nfdchar_t *") long j) {
        nNFD_PathSet_FreePath(j);
    }

    @NativeType("nfdresult_t")
    public static int NFD_PathSet_GetEnum(@NativeType("nfdpathset_t const *") long j, @NativeType("nfdpathsetenum_t *") NFDPathSetEnum nFDPathSetEnum) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nNFD_PathSet_GetEnum(j, nFDPathSetEnum.address());
    }

    public static void NFD_PathSet_FreeEnum(@NativeType("nfdpathsetenum_t *") NFDPathSetEnum nFDPathSetEnum) {
        nNFD_PathSet_FreeEnum(nFDPathSetEnum.address());
    }

    @NativeType("nfdresult_t")
    public static int NFD_PathSet_EnumNext(@NativeType("nfdpathsetenum_t *") NFDPathSetEnum nFDPathSetEnum, @NativeType("nfdchar_t **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nNFD_PathSet_EnumNext(nFDPathSetEnum.address(), MemoryUtil.memAddress(pointerBuffer));
    }

    public static void NFD_PathSet_Free(@NativeType("nfdpathset_t const *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nNFD_PathSet_Free(j);
    }

    @NativeType("nfdresult_t")
    public static int NFD_PathSet_GetCount(@NativeType("nfdpathset_t const *") long j, @NativeType("nfdpathsetsize_t *") int[] iArr) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return nNFD_PathSet_GetCount(j, iArr);
    }
}
