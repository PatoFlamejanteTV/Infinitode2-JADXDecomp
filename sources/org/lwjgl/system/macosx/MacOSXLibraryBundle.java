package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/MacOSXLibraryBundle.class */
public class MacOSXLibraryBundle extends MacOSXLibrary {
    public MacOSXLibraryBundle(String str, long j) {
        super(str, j);
    }

    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r9v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0080: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x0080 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x007c: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE], block:B:30:0x007c */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r9v0, types: [org.lwjgl.system.MemoryStack] */
    public static MacOSXLibraryBundle getWithIdentifier(String str) {
        long j = 0;
        try {
            try {
                MemoryStack stackPush = MemoryStack.stackPush();
                Throwable th = null;
                long CString2CFString = CString2CFString(stackPush.UTF8(str), CoreFoundation.kCFStringEncodingUTF8);
                j = CString2CFString;
                long CFBundleGetBundleWithIdentifier = CoreFoundation.CFBundleGetBundleWithIdentifier(CString2CFString);
                if (CFBundleGetBundleWithIdentifier == 0) {
                    throw new UnsatisfiedLinkError("Failed to retrieve bundle with identifier: " + str);
                }
                CoreFoundation.CFRetain(CFBundleGetBundleWithIdentifier);
                MacOSXLibraryBundle macOSXLibraryBundle = new MacOSXLibraryBundle(str, CFBundleGetBundleWithIdentifier);
                if (stackPush != null) {
                    if (0 != 0) {
                        try {
                            stackPush.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        stackPush.close();
                    }
                }
                if (j != 0) {
                    CoreFoundation.CFRelease(j);
                }
                return macOSXLibraryBundle;
            } finally {
            }
        } catch (Throwable th3) {
            if (j != 0) {
                CoreFoundation.CFRelease(j);
            }
            throw th3;
        }
    }

    /* JADX WARN: Failed to calculate best type for var: r13v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r14v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 13, insn: 0x0095: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r13 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE], block:B:33:0x0095 */
    /* JADX WARN: Not initialized variable reg: 14, insn: 0x009a: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r14 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:35:0x009a */
    /* JADX WARN: Type inference failed for: r13v0, types: [org.lwjgl.system.MemoryStack] */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.Throwable] */
    public static MacOSXLibraryBundle create(String str) {
        long j = 0;
        long j2 = 0;
        try {
            try {
                MemoryStack stackPush = MemoryStack.stackPush();
                Throwable th = null;
                j = CString2CFString(stackPush.UTF8(str), CoreFoundation.kCFStringEncodingUTF8);
                j2 = Checks.check(CoreFoundation.CFURLCreateWithFileSystemPath(0L, j, 0L, true));
                long CFBundleCreate = CoreFoundation.CFBundleCreate(0L, j2);
                if (CFBundleCreate != 0) {
                    MacOSXLibraryBundle macOSXLibraryBundle = new MacOSXLibraryBundle(str, CFBundleCreate);
                    if (stackPush != null) {
                        if (0 != 0) {
                            try {
                                stackPush.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            stackPush.close();
                        }
                    }
                    if (j2 != 0) {
                        CoreFoundation.CFRelease(j2);
                    }
                    if (j != 0) {
                        CoreFoundation.CFRelease(j);
                    }
                    return macOSXLibraryBundle;
                }
                throw new UnsatisfiedLinkError("Failed to create bundle: " + str);
            } finally {
            }
        } catch (Throwable th3) {
            if (j2 != 0) {
                CoreFoundation.CFRelease(j2);
            }
            if (j != 0) {
                CoreFoundation.CFRelease(j);
            }
            throw th3;
        }
    }

    @Override // org.lwjgl.system.SharedLibrary
    public String getPath() {
        return null;
    }

    @Override // org.lwjgl.system.FunctionProvider
    public long getFunctionAddress(ByteBuffer byteBuffer) {
        long CString2CFString = CString2CFString(byteBuffer, 1536);
        try {
            return CoreFoundation.CFBundleGetFunctionPointerForName(address(), CString2CFString);
        } finally {
            CoreFoundation.CFRelease(CString2CFString);
        }
    }

    private static long CString2CFString(ByteBuffer byteBuffer, int i) {
        return Checks.check(CoreFoundation.CFStringCreateWithCStringNoCopy(0L, byteBuffer, i, CoreFoundation.kCFAllocatorNull));
    }

    @Override // org.lwjgl.system.NativeResource
    public void free() {
        CoreFoundation.CFRelease(address());
    }
}
