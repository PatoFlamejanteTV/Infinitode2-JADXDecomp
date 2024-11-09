package org.lwjgl.system.macosx;

import com.vladsch.flexmark.util.html.Attribute;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.Struct;
import org.lwjgl.system.libc.LibCStdlib;
import org.lwjgl.system.macosx.ObjCMethodDescription;
import org.lwjgl.system.macosx.ObjCPropertyAttribute;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/ObjCRuntime.class */
public class ObjCRuntime {
    private static final SharedLibrary OBJC = Library.loadNative(ObjCRuntime.class, "org.lwjgl", "objc");
    public static final long nil = 0;
    public static final byte YES = 1;
    public static final byte NO = 0;
    public static final char _C_ID = '@';
    public static final char _C_CLASS = '#';
    public static final char _C_SEL = ':';
    public static final char _C_CHR = 'c';
    public static final char _C_UCHR = 'C';
    public static final char _C_SHT = 's';
    public static final char _C_USHT = 'S';
    public static final char _C_INT = 'i';
    public static final char _C_UINT = 'I';
    public static final char _C_LNG = 'l';
    public static final char _C_ULNG = 'L';
    public static final char _C_LNG_LNG = 'q';
    public static final char _C_ULNG_LNG = 'Q';
    public static final char _C_FLT = 'f';
    public static final char _C_DBL = 'd';
    public static final char _C_BFLD = 'b';
    public static final char _C_BOOL = 'B';
    public static final char _C_VOID = 'v';
    public static final char _C_UNDEF = '?';
    public static final char _C_PTR = '^';
    public static final char _C_CHARPTR = '*';
    public static final char _C_ATOM = '%';
    public static final char _C_ARY_B = '[';
    public static final char _C_ARY_E = ']';
    public static final char _C_UNION_B = '(';
    public static final char _C_UNION_E = ')';
    public static final char _C_STRUCT_B = '{';
    public static final char _C_STRUCT_E = '}';
    public static final char _C_VECTOR = '!';
    public static final char _C_CONST = 'r';
    public static final int OBJC_ASSOCIATION_ASSIGN = 0;
    public static final int OBJC_ASSOCIATION_RETAIN_NONATOMIC = 1;
    public static final int OBJC_ASSOCIATION_COPY_NONATOMIC = 3;
    public static final int OBJC_ASSOCIATION_RETAIN = 1401;
    public static final int OBJC_ASSOCIATION_COPY = 1403;

    public static native void nprotocol_getMethodDescription(long j, long j2, boolean z, boolean z2, long j3, long j4);

    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/ObjCRuntime$Functions.class */
    public static final class Functions {
        public static final long object_copy = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_copy");
        public static final long object_dispose = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_dispose");
        public static final long object_getClass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_getClass");
        public static final long object_setClass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_setClass");
        public static final long object_getClassName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_getClassName");
        public static final long object_getIndexedIvars = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_getIndexedIvars");
        public static final long object_getIvar = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_getIvar");
        public static final long object_setIvar = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_setIvar");
        public static final long object_setInstanceVariable = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_setInstanceVariable");
        public static final long object_getInstanceVariable = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "object_getInstanceVariable");
        public static final long objc_getClass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_getClass");
        public static final long objc_getMetaClass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_getMetaClass");
        public static final long objc_lookUpClass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_lookUpClass");
        public static final long objc_getRequiredClass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_getRequiredClass");
        public static final long objc_getClassList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_getClassList");
        public static final long objc_copyClassList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_copyClassList");
        public static final long class_getName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getName");
        public static final long class_isMetaClass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_isMetaClass");
        public static final long class_getSuperclass = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getSuperclass");
        public static final long class_getVersion = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getVersion");
        public static final long class_setVersion = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_setVersion");
        public static final long class_getInstanceSize = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getInstanceSize");
        public static final long class_getInstanceVariable = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getInstanceVariable");
        public static final long class_getClassVariable = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getClassVariable");
        public static final long class_copyIvarList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_copyIvarList");
        public static final long class_getInstanceMethod = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getInstanceMethod");
        public static final long class_getClassMethod = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getClassMethod");
        public static final long class_getMethodImplementation = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getMethodImplementation");
        public static final long class_respondsToSelector = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_respondsToSelector");
        public static final long class_copyMethodList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_copyMethodList");
        public static final long class_conformsToProtocol = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_conformsToProtocol");
        public static final long class_copyProtocolList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_copyProtocolList");
        public static final long class_getProperty = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getProperty");
        public static final long class_copyPropertyList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_copyPropertyList");
        public static final long class_getIvarLayout = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getIvarLayout");
        public static final long class_getWeakIvarLayout = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getWeakIvarLayout");
        public static final long class_addMethod = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_addMethod");
        public static final long class_replaceMethod = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_replaceMethod");
        public static final long class_addIvar = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_addIvar");
        public static final long class_addProtocol = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_addProtocol");
        public static final long class_addProperty = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_addProperty");
        public static final long class_replaceProperty = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_replaceProperty");
        public static final long class_setIvarLayout = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_setIvarLayout");
        public static final long class_setWeakIvarLayout = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_setWeakIvarLayout");
        public static final long class_createInstance = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_createInstance");
        public static final long objc_constructInstance = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_constructInstance");
        public static final long objc_destructInstance = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_destructInstance");
        public static final long objc_allocateClassPair = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_allocateClassPair");
        public static final long objc_registerClassPair = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_registerClassPair");
        public static final long objc_disposeClassPair = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_disposeClassPair");
        public static final long method_getName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_getName");
        public static final long method_getImplementation = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_getImplementation");
        public static final long method_getTypeEncoding = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_getTypeEncoding");
        public static final long method_getNumberOfArguments = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_getNumberOfArguments");
        public static final long method_copyReturnType = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_copyReturnType");
        public static final long method_copyArgumentType = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_copyArgumentType");
        public static final long method_getReturnType = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_getReturnType");
        public static final long method_getArgumentType = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_getArgumentType");
        public static final long method_setImplementation = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_setImplementation");
        public static final long method_exchangeImplementations = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "method_exchangeImplementations");
        public static final long ivar_getName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "ivar_getName");
        public static final long ivar_getTypeEncoding = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "ivar_getTypeEncoding");
        public static final long ivar_getOffset = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "ivar_getOffset");
        public static final long property_getName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "property_getName");
        public static final long property_getAttributes = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "property_getAttributes");
        public static final long property_copyAttributeList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "property_copyAttributeList");
        public static final long property_copyAttributeValue = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "property_copyAttributeValue");
        public static final long objc_getProtocol = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_getProtocol");
        public static final long objc_copyProtocolList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_copyProtocolList");
        public static final long protocol_conformsToProtocol = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_conformsToProtocol");
        public static final long protocol_isEqual = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_isEqual");
        public static final long protocol_getName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_getName");
        public static final long protocol_getMethodDescription = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_getMethodDescription");
        public static final long protocol_copyMethodDescriptionList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_copyMethodDescriptionList");
        public static final long protocol_getProperty = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_getProperty");
        public static final long protocol_copyPropertyList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_copyPropertyList");
        public static final long protocol_copyProtocolList = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_copyProtocolList");
        public static final long objc_allocateProtocol = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_allocateProtocol");
        public static final long objc_registerProtocol = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_registerProtocol");
        public static final long protocol_addMethodDescription = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_addMethodDescription");
        public static final long protocol_addProtocol = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_addProtocol");
        public static final long protocol_addProperty = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "protocol_addProperty");
        public static final long objc_copyImageNames = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_copyImageNames");
        public static final long class_getImageName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "class_getImageName");
        public static final long objc_copyClassNamesForImage = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_copyClassNamesForImage");
        public static final long sel_getName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "sel_getName");
        public static final long sel_getUid = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "sel_getUid");
        public static final long sel_registerName = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "sel_registerName");
        public static final long sel_isEqual = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "sel_isEqual");
        public static final long objc_enumerationMutation = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_enumerationMutation");
        public static final long objc_setEnumerationMutationHandler = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_setEnumerationMutationHandler");
        public static final long imp_implementationWithBlock = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "imp_implementationWithBlock");
        public static final long imp_getBlock = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "imp_getBlock");
        public static final long imp_removeBlock = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "imp_removeBlock");
        public static final long objc_loadWeak = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_loadWeak");
        public static final long objc_storeWeak = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_storeWeak");
        public static final long objc_setAssociatedObject = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_setAssociatedObject");
        public static final long objc_getAssociatedObject = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_getAssociatedObject");
        public static final long objc_removeAssociatedObjects = APIUtil.apiGetFunctionAddress(ObjCRuntime.OBJC, "objc_removeAssociatedObjects");

        private Functions() {
        }
    }

    public static SharedLibrary getLibrary() {
        return OBJC;
    }

    protected ObjCRuntime() {
        throw new UnsupportedOperationException();
    }

    @NativeType(Attribute.ID_ATTR)
    public static long object_copy(@NativeType("id") long j, @NativeType("size_t") long j2) {
        long j3 = Functions.object_copy;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long object_dispose(@NativeType("id") long j) {
        long j2 = Functions.object_dispose;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("Class")
    public static long object_getClass(@NativeType("id") long j) {
        return JNI.invokePP(j, Functions.object_getClass);
    }

    @NativeType("Class")
    public static long object_setClass(@NativeType("id") long j, @NativeType("Class") long j2) {
        long j3 = Functions.object_setClass;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    public static long nobject_getClassName(long j) {
        return JNI.invokePP(j, Functions.object_getClassName);
    }

    @NativeType("char const *")
    public static String object_getClassName(@NativeType("id") long j) {
        return MemoryUtil.memUTF8Safe(nobject_getClassName(j));
    }

    @NativeType("void *")
    public static long object_getIndexedIvars(@NativeType("id") long j) {
        long j2 = Functions.object_getIndexedIvars;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long object_getIvar(@NativeType("id") long j, @NativeType("Ivar") long j2) {
        long j3 = Functions.object_getIvar;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    public static void object_setIvar(@NativeType("id") long j, @NativeType("Ivar") long j2, @NativeType("id") long j3) {
        long j4 = Functions.object_setIvar;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static long nobject_setInstanceVariable(long j, long j2, long j3) {
        long j4 = Functions.object_setInstanceVariable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPPP(j, j2, j3, j4);
    }

    @NativeType("Ivar")
    public static long object_setInstanceVariable(@NativeType("id") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("void *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobject_setInstanceVariable(j, MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2));
    }

    @NativeType("Ivar")
    public static long object_setInstanceVariable(@NativeType("id") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("void *") ByteBuffer byteBuffer) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobject_setInstanceVariable(j, stackGet.getPointerAddress(), MemoryUtil.memAddress(byteBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobject_getInstanceVariable(long j, long j2, long j3) {
        long j4 = Functions.object_getInstanceVariable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPPP(j, j2, j3, j4);
    }

    @NativeType("Ivar")
    public static long object_getInstanceVariable(@NativeType("id") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nobject_getInstanceVariable(j, MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("Ivar")
    public static long object_getInstanceVariable(@NativeType("id") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobject_getInstanceVariable(j, stackGet.getPointerAddress(), MemoryUtil.memAddress(pointerBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobjc_getClass(long j) {
        return JNI.invokePP(j, Functions.objc_getClass);
    }

    @NativeType("Class")
    public static long objc_getClass(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobjc_getClass(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Class")
    public static long objc_getClass(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobjc_getClass(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobjc_getMetaClass(long j) {
        return JNI.invokePP(j, Functions.objc_getMetaClass);
    }

    @NativeType("Class")
    public static long objc_getMetaClass(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobjc_getMetaClass(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Class")
    public static long objc_getMetaClass(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobjc_getMetaClass(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobjc_lookUpClass(long j) {
        return JNI.invokePP(j, Functions.objc_lookUpClass);
    }

    @NativeType("Class")
    public static long objc_lookUpClass(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobjc_lookUpClass(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Class")
    public static long objc_lookUpClass(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobjc_lookUpClass(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobjc_getRequiredClass(long j) {
        return JNI.invokePP(j, Functions.objc_getRequiredClass);
    }

    @NativeType("Class")
    public static long objc_getRequiredClass(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobjc_getRequiredClass(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Class")
    public static long objc_getRequiredClass(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobjc_getRequiredClass(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nobjc_getClassList(long j, int i) {
        return JNI.invokePI(j, i, Functions.objc_getClassList);
    }

    public static int objc_getClassList(@NativeType("Class *") PointerBuffer pointerBuffer) {
        return nobjc_getClassList(MemoryUtil.memAddressSafe(pointerBuffer), Checks.remainingSafe(pointerBuffer));
    }

    public static long nobjc_copyClassList(long j) {
        return JNI.invokePP(j, Functions.objc_copyClassList);
    }

    @NativeType("Class *")
    public static PointerBuffer objc_copyClassList() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nobjc_copyClassList(MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_getName(long j) {
        return JNI.invokePP(j, Functions.class_getName);
    }

    @NativeType("char const *")
    public static String class_getName(@NativeType("Class") long j) {
        return MemoryUtil.memUTF8Safe(nclass_getName(j));
    }

    @NativeType("BOOL")
    public static boolean class_isMetaClass(@NativeType("Class") long j) {
        return JNI.invokePZ(j, Functions.class_isMetaClass);
    }

    @NativeType("Class")
    public static long class_getSuperclass(@NativeType("Class") long j) {
        return JNI.invokePP(j, Functions.class_getSuperclass);
    }

    public static int class_getVersion(@NativeType("Class") long j) {
        long j2 = Functions.class_getVersion;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, j2);
    }

    public static void class_setVersion(@NativeType("Class") long j, int i) {
        long j2 = Functions.class_setVersion;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, j2);
    }

    @NativeType("size_t")
    public static long class_getInstanceSize(@NativeType("Class") long j) {
        return JNI.invokePP(j, Functions.class_getInstanceSize);
    }

    public static long nclass_getInstanceVariable(long j, long j2) {
        long j3 = Functions.class_getInstanceVariable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("Ivar")
    public static long class_getInstanceVariable(@NativeType("Class") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nclass_getInstanceVariable(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Ivar")
    public static long class_getInstanceVariable(@NativeType("Class") long j, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nclass_getInstanceVariable(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_getClassVariable(long j, long j2) {
        long j3 = Functions.class_getClassVariable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("Ivar")
    public static long class_getClassVariable(@NativeType("Class") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nclass_getClassVariable(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Ivar")
    public static long class_getClassVariable(@NativeType("Class") long j, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nclass_getClassVariable(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_copyIvarList(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.class_copyIvarList);
    }

    @NativeType("Ivar *")
    public static PointerBuffer class_copyIvarList(@NativeType("Class") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nclass_copyIvarList(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("Method")
    public static long class_getInstanceMethod(@NativeType("Class") long j, @NativeType("SEL") long j2) {
        long j3 = Functions.class_getInstanceMethod;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("Method")
    public static long class_getClassMethod(@NativeType("Class") long j, @NativeType("SEL") long j2) {
        long j3 = Functions.class_getClassMethod;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("IMP")
    public static long class_getMethodImplementation(@NativeType("Class") long j, @NativeType("SEL") long j2) {
        long j3 = Functions.class_getMethodImplementation;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean class_respondsToSelector(@NativeType("Class") long j, @NativeType("SEL") long j2) {
        long j3 = Functions.class_respondsToSelector;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPZ(j, j2, j3);
    }

    public static long nclass_copyMethodList(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.class_copyMethodList);
    }

    @NativeType("Method *")
    public static PointerBuffer class_copyMethodList(@NativeType("Class") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nclass_copyMethodList(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("BOOL")
    public static boolean class_conformsToProtocol(@NativeType("Class") long j, @NativeType("Protocol *") long j2) {
        long j3 = Functions.class_conformsToProtocol;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPZ(j, j2, j3);
    }

    public static long nclass_copyProtocolList(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.class_copyProtocolList);
    }

    @NativeType("Protocol **")
    public static PointerBuffer class_copyProtocolList(@NativeType("Class") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nclass_copyProtocolList(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_getProperty(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.class_getProperty);
    }

    @NativeType("objc_property_t")
    public static long class_getProperty(@NativeType("Class") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nclass_getProperty(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("objc_property_t")
    public static long class_getProperty(@NativeType("Class") long j, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nclass_getProperty(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_copyPropertyList(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.class_copyPropertyList);
    }

    @NativeType("objc_property_t *")
    public static PointerBuffer class_copyPropertyList(@NativeType("Class") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nclass_copyPropertyList(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_getIvarLayout(long j) {
        long j2 = Functions.class_getIvarLayout;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("uint8_t const *")
    public static String class_getIvarLayout(@NativeType("Class") long j) {
        return MemoryUtil.memASCIISafe(nclass_getIvarLayout(j));
    }

    public static long nclass_getWeakIvarLayout(long j) {
        long j2 = Functions.class_getWeakIvarLayout;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("uint8_t const *")
    public static String class_getWeakIvarLayout(@NativeType("Class") long j) {
        return MemoryUtil.memASCIISafe(nclass_getWeakIvarLayout(j));
    }

    public static boolean nclass_addMethod(long j, long j2, long j3, long j4) {
        long j5 = Functions.class_addMethod;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        return JNI.invokePPPPZ(j, j2, j3, j4, j5);
    }

    @NativeType("BOOL")
    public static boolean class_addMethod(@NativeType("Class") long j, @NativeType("SEL") long j2, @NativeType("IMP") long j3, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nclass_addMethod(j, j2, j3, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("BOOL")
    public static boolean class_addMethod(@NativeType("Class") long j, @NativeType("SEL") long j2, @NativeType("IMP") long j3, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nclass_addMethod(j, j2, j3, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_replaceMethod(long j, long j2, long j3, long j4) {
        long j5 = Functions.class_replaceMethod;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        return JNI.invokePPPPP(j, j2, j3, j4, j5);
    }

    @NativeType("IMP")
    public static long class_replaceMethod(@NativeType("Class") long j, @NativeType("SEL") long j2, @NativeType("IMP") long j3, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nclass_replaceMethod(j, j2, j3, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("IMP")
    public static long class_replaceMethod(@NativeType("Class") long j, @NativeType("SEL") long j2, @NativeType("IMP") long j3, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nclass_replaceMethod(j, j2, j3, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static boolean nclass_addIvar(long j, long j2, long j3, byte b2, long j4) {
        long j5 = Functions.class_addIvar;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPPUPZ(j, j2, j3, b2, j4, j5);
    }

    @NativeType("BOOL")
    public static boolean class_addIvar(@NativeType("Class") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("size_t") long j2, @NativeType("uint8_t") byte b2, @NativeType("char const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
        }
        return nclass_addIvar(j, MemoryUtil.memAddress(byteBuffer), j2, b2, MemoryUtil.memAddress(byteBuffer2));
    }

    @NativeType("BOOL")
    public static boolean class_addIvar(@NativeType("Class") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("size_t") long j2, @NativeType("uint8_t") byte b2, @NativeType("char const *") CharSequence charSequence2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            return nclass_addIvar(j, pointerAddress, j2, b2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("BOOL")
    public static boolean class_addProtocol(@NativeType("Class") long j, @NativeType("Protocol *") long j2) {
        long j3 = Functions.class_addProtocol;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPZ(j, j2, j3);
    }

    public static boolean nclass_addProperty(long j, long j2, long j3, int i) {
        long j4 = Functions.class_addProperty;
        if (Checks.CHECKS) {
            Checks.check(j);
            Struct.validate(j3, i, ObjCPropertyAttribute.SIZEOF, ObjCPropertyAttribute::validate);
        }
        return JNI.invokePPPZ(j, j2, j3, i, j4);
    }

    @NativeType("BOOL")
    public static boolean class_addProperty(@NativeType("Class") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("objc_property_attribute_t const *") ObjCPropertyAttribute.Buffer buffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nclass_addProperty(j, MemoryUtil.memAddress(byteBuffer), buffer.address(), buffer.remaining());
    }

    @NativeType("BOOL")
    public static boolean class_addProperty(@NativeType("Class") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("objc_property_attribute_t const *") ObjCPropertyAttribute.Buffer buffer) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nclass_addProperty(j, stackGet.getPointerAddress(), buffer.address(), buffer.remaining());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nclass_replaceProperty(long j, long j2, long j3, int i) {
        long j4 = Functions.class_replaceProperty;
        if (Checks.CHECKS) {
            Checks.check(j);
            Struct.validate(j3, i, ObjCPropertyAttribute.SIZEOF, ObjCPropertyAttribute::validate);
        }
        JNI.invokePPPV(j, j2, j3, i, j4);
    }

    public static void class_replaceProperty(@NativeType("Class") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("objc_property_attribute_t const *") ObjCPropertyAttribute.Buffer buffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nclass_replaceProperty(j, MemoryUtil.memAddress(byteBuffer), buffer.address(), buffer.remaining());
    }

    public static void class_replaceProperty(@NativeType("Class") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("objc_property_attribute_t const *") ObjCPropertyAttribute.Buffer buffer) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nclass_replaceProperty(j, stackGet.getPointerAddress(), buffer.address(), buffer.remaining());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nclass_setIvarLayout(long j, long j2) {
        long j3 = Functions.class_setIvarLayout;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static void class_setIvarLayout(@NativeType("Class") long j, @NativeType("uint8_t const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nclass_setIvarLayout(j, MemoryUtil.memAddress(byteBuffer));
    }

    public static void class_setIvarLayout(@NativeType("Class") long j, @NativeType("uint8_t const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nclass_setIvarLayout(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nclass_setWeakIvarLayout(long j, long j2) {
        long j3 = Functions.class_setWeakIvarLayout;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static void class_setWeakIvarLayout(@NativeType("Class") long j, @NativeType("uint8_t const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nclass_setWeakIvarLayout(j, MemoryUtil.memAddress(byteBuffer));
    }

    public static void class_setWeakIvarLayout(@NativeType("Class") long j, @NativeType("uint8_t const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nclass_setWeakIvarLayout(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType(Attribute.ID_ATTR)
    public static long class_createInstance(@NativeType("Class") long j, @NativeType("size_t") long j2) {
        long j3 = Functions.class_createInstance;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    public static long nobjc_constructInstance(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.objc_constructInstance);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long objc_constructInstance(@NativeType("Class") long j, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.checkSafe(byteBuffer, class_getInstanceSize(j));
        }
        return nobjc_constructInstance(j, MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("void *")
    public static long objc_destructInstance(@NativeType("id") long j) {
        long j2 = Functions.objc_destructInstance;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static long nobjc_allocateClassPair(long j, long j2, long j3) {
        return JNI.invokePPPP(j, j2, j3, Functions.objc_allocateClassPair);
    }

    @NativeType("Class")
    public static long objc_allocateClassPair(@NativeType("Class") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("size_t") long j2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobjc_allocateClassPair(j, MemoryUtil.memAddress(byteBuffer), j2);
    }

    @NativeType("Class")
    public static long objc_allocateClassPair(@NativeType("Class") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("size_t") long j2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobjc_allocateClassPair(j, stackGet.getPointerAddress(), j2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void objc_registerClassPair(@NativeType("Class") long j) {
        long j2 = Functions.objc_registerClassPair;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void objc_disposeClassPair(@NativeType("Class") long j) {
        long j2 = Functions.objc_disposeClassPair;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    @NativeType("SEL")
    public static long method_getName(@NativeType("Method") long j) {
        long j2 = Functions.method_getName;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("IMP")
    public static long method_getImplementation(@NativeType("Method") long j) {
        long j2 = Functions.method_getImplementation;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static long nmethod_getTypeEncoding(long j) {
        long j2 = Functions.method_getTypeEncoding;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String method_getTypeEncoding(@NativeType("Method") long j) {
        return MemoryUtil.memUTF8Safe(nmethod_getTypeEncoding(j));
    }

    @NativeType("unsigned int")
    public static int method_getNumberOfArguments(@NativeType("Method") long j) {
        long j2 = Functions.method_getNumberOfArguments;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, j2);
    }

    public static long nmethod_copyReturnType(long j) {
        long j2 = Functions.method_copyReturnType;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char *")
    public static String method_copyReturnType(@NativeType("Method") long j) {
        return MemoryUtil.memUTF8Safe(nmethod_copyReturnType(j));
    }

    public static long nmethod_copyArgumentType(long j, int i) {
        long j2 = Functions.method_copyArgumentType;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, i, j2);
    }

    @NativeType("char *")
    public static String method_copyArgumentType(@NativeType("Method") long j, @NativeType("unsigned int") int i) {
        long j2 = 0;
        try {
            long nmethod_copyArgumentType = nmethod_copyArgumentType(j, i);
            j2 = nmethod_copyArgumentType;
            String memUTF8Safe = MemoryUtil.memUTF8Safe(nmethod_copyArgumentType);
            if (j2 != 0) {
                LibCStdlib.nfree(j2);
            }
            return memUTF8Safe;
        } catch (Throwable th) {
            if (j2 != 0) {
                LibCStdlib.nfree(j2);
            }
            throw th;
        }
    }

    public static void nmethod_getReturnType(long j, long j2, long j3) {
        long j4 = Functions.method_getReturnType;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void method_getReturnType(@NativeType("Method") long j, @NativeType("char *") ByteBuffer byteBuffer) {
        nmethod_getReturnType(j, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
    }

    @NativeType("void")
    public static String method_getReturnType(@NativeType("Method") long j, @NativeType("size_t") long j2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            ByteBuffer malloc = stackGet.malloc((int) j2);
            nmethod_getReturnType(j, MemoryUtil.memAddress(malloc), j2);
            return MemoryUtil.memUTF8(MemoryUtil.memByteBufferNT1(MemoryUtil.memAddress(malloc), (int) j2));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nmethod_getArgumentType(long j, int i, long j2, long j3) {
        long j4 = Functions.method_getArgumentType;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, i, j2, j3, j4);
    }

    public static void method_getArgumentType(@NativeType("Method") long j, @NativeType("unsigned int") int i, @NativeType("char *") ByteBuffer byteBuffer) {
        nmethod_getArgumentType(j, i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
    }

    @NativeType("void")
    public static String method_getArgumentType(@NativeType("Method") long j, @NativeType("unsigned int") int i, @NativeType("size_t") long j2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            ByteBuffer malloc = stackGet.malloc((int) j2);
            nmethod_getArgumentType(j, i, MemoryUtil.memAddress(malloc), j2);
            return MemoryUtil.memUTF8(MemoryUtil.memByteBufferNT1(MemoryUtil.memAddress(malloc), (int) j2));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("IMP")
    public static long method_setImplementation(@NativeType("Method") long j, @NativeType("IMP") long j2) {
        long j3 = Functions.method_setImplementation;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    public static void method_exchangeImplementations(@NativeType("Method") long j, @NativeType("Method") long j2) {
        long j3 = Functions.method_exchangeImplementations;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static long nivar_getName(long j) {
        long j2 = Functions.ivar_getName;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String ivar_getName(@NativeType("Ivar") long j) {
        return MemoryUtil.memUTF8Safe(nivar_getName(j));
    }

    public static long nivar_getTypeEncoding(long j) {
        long j2 = Functions.ivar_getTypeEncoding;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String ivar_getTypeEncoding(@NativeType("Ivar") long j) {
        return MemoryUtil.memUTF8Safe(nivar_getTypeEncoding(j));
    }

    @NativeType("ptrdiff_t")
    public static long ivar_getOffset(@NativeType("Ivar") long j) {
        long j2 = Functions.ivar_getOffset;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static long nproperty_getName(long j) {
        long j2 = Functions.property_getName;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String property_getName(@NativeType("objc_property_t") long j) {
        return MemoryUtil.memUTF8Safe(nproperty_getName(j));
    }

    public static long nproperty_getAttributes(long j) {
        long j2 = Functions.property_getAttributes;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String property_getAttributes(@NativeType("objc_property_t") long j) {
        return MemoryUtil.memUTF8Safe(nproperty_getAttributes(j));
    }

    public static long nproperty_copyAttributeList(long j, long j2) {
        long j3 = Functions.property_copyAttributeList;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("objc_property_attribute_t *")
    public static ObjCPropertyAttribute.Buffer property_copyAttributeList(@NativeType("objc_property_t") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return ObjCPropertyAttribute.createSafe(nproperty_copyAttributeList(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nproperty_copyAttributeValue(long j, long j2) {
        long j3 = Functions.property_copyAttributeValue;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("char *")
    public static String property_copyAttributeValue(@NativeType("objc_property_t") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        long j2 = 0;
        try {
            long nproperty_copyAttributeValue = nproperty_copyAttributeValue(j, MemoryUtil.memAddress(byteBuffer));
            j2 = nproperty_copyAttributeValue;
            String memUTF8Safe = MemoryUtil.memUTF8Safe(nproperty_copyAttributeValue);
            if (j2 != 0) {
                LibCStdlib.nfree(j2);
            }
            return memUTF8Safe;
        } catch (Throwable th) {
            if (j2 != 0) {
                LibCStdlib.nfree(j2);
            }
            throw th;
        }
    }

    @NativeType("char *")
    public static String property_copyAttributeValue(@NativeType("objc_property_t") long j, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        long j2 = 0;
        try {
            stackGet.nUTF8(charSequence, true);
            long nproperty_copyAttributeValue = nproperty_copyAttributeValue(j, stackGet.getPointerAddress());
            j2 = nproperty_copyAttributeValue;
            String memUTF8Safe = MemoryUtil.memUTF8Safe(nproperty_copyAttributeValue);
            if (j2 != 0) {
                LibCStdlib.nfree(j2);
            }
            stackGet.setPointer(pointer);
            return memUTF8Safe;
        } catch (Throwable th) {
            if (j2 != 0) {
                LibCStdlib.nfree(j2);
            }
            stackGet.setPointer(pointer);
            throw th;
        }
    }

    public static long nobjc_getProtocol(long j) {
        return JNI.invokePP(j, Functions.objc_getProtocol);
    }

    @NativeType("Protocol *")
    public static long objc_getProtocol(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobjc_getProtocol(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Protocol *")
    public static long objc_getProtocol(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobjc_getProtocol(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobjc_copyProtocolList(long j) {
        return JNI.invokePP(j, Functions.objc_copyProtocolList);
    }

    @NativeType("Protocol **")
    public static PointerBuffer objc_copyProtocolList() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nobjc_copyProtocolList(MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("BOOL")
    public static boolean protocol_conformsToProtocol(@NativeType("Protocol *") long j, @NativeType("Protocol *") long j2) {
        long j3 = Functions.protocol_conformsToProtocol;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPZ(j, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean protocol_isEqual(@NativeType("Protocol *") long j, @NativeType("Protocol *") long j2) {
        long j3 = Functions.protocol_isEqual;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPZ(j, j2, j3);
    }

    public static long nprotocol_getName(long j) {
        long j2 = Functions.protocol_getName;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String protocol_getName(@NativeType("Protocol *") long j) {
        return MemoryUtil.memUTF8Safe(nprotocol_getName(j));
    }

    public static void nprotocol_getMethodDescription(long j, long j2, boolean z, boolean z2, long j3) {
        long j4 = Functions.protocol_getMethodDescription;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        nprotocol_getMethodDescription(j, j2, z, z2, j4, j3);
    }

    @NativeType("struct objc_method_description")
    public static ObjCMethodDescription protocol_getMethodDescription(@NativeType("Protocol *") long j, @NativeType("SEL") long j2, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2, @NativeType("struct objc_method_description") ObjCMethodDescription objCMethodDescription) {
        nprotocol_getMethodDescription(j, j2, z, z2, objCMethodDescription.address());
        return objCMethodDescription;
    }

    public static long nprotocol_copyMethodDescriptionList(long j, boolean z, boolean z2, long j2) {
        long j3 = Functions.protocol_copyMethodDescriptionList;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, z, z2, j2, j3);
    }

    @NativeType("struct objc_method_description *")
    public static ObjCMethodDescription.Buffer protocol_copyMethodDescriptionList(@NativeType("Protocol *") long j, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return ObjCMethodDescription.createSafe(nprotocol_copyMethodDescriptionList(j, z, z2, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nprotocol_getProperty(long j, long j2, boolean z, boolean z2) {
        long j3 = Functions.protocol_getProperty;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, z, z2, j3);
    }

    @NativeType("objc_property_t")
    public static long protocol_getProperty(@NativeType("Protocol *") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nprotocol_getProperty(j, MemoryUtil.memAddress(byteBuffer), z, z2);
    }

    @NativeType("objc_property_t")
    public static long protocol_getProperty(@NativeType("Protocol *") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nprotocol_getProperty(j, stackGet.getPointerAddress(), z, z2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nprotocol_copyPropertyList(long j, long j2) {
        long j3 = Functions.protocol_copyPropertyList;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("objc_property_t *")
    public static PointerBuffer protocol_copyPropertyList(@NativeType("Protocol *") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nprotocol_copyPropertyList(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nprotocol_copyProtocolList(long j, long j2) {
        long j3 = Functions.protocol_copyProtocolList;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("Protocol **")
    public static PointerBuffer protocol_copyProtocolList(@NativeType("Protocol *") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nprotocol_copyProtocolList(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobjc_allocateProtocol(long j) {
        return JNI.invokePP(j, Functions.objc_allocateProtocol);
    }

    @NativeType("Protocol *")
    public static long objc_allocateProtocol(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nobjc_allocateProtocol(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Protocol *")
    public static long objc_allocateProtocol(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nobjc_allocateProtocol(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void objc_registerProtocol(@NativeType("Protocol *") long j) {
        long j2 = Functions.objc_registerProtocol;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void nprotocol_addMethodDescription(long j, long j2, long j3, boolean z, boolean z2) {
        long j4 = Functions.protocol_addMethodDescription;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.invokePPPV(j, j2, j3, z, z2, j4);
    }

    public static void protocol_addMethodDescription(@NativeType("Protocol *") long j, @NativeType("SEL") long j2, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nprotocol_addMethodDescription(j, j2, MemoryUtil.memAddress(byteBuffer), z, z2);
    }

    public static void protocol_addMethodDescription(@NativeType("Protocol *") long j, @NativeType("SEL") long j2, @NativeType("char const *") CharSequence charSequence, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nprotocol_addMethodDescription(j, j2, stackGet.getPointerAddress(), z, z2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void protocol_addProtocol(@NativeType("Protocol *") long j, @NativeType("Protocol *") long j2) {
        long j3 = Functions.protocol_addProtocol;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static void nprotocol_addProperty(long j, long j2, long j3, int i, boolean z, boolean z2) {
        long j4 = Functions.protocol_addProperty;
        if (Checks.CHECKS) {
            Checks.check(j);
            Struct.validate(j3, i, ObjCPropertyAttribute.SIZEOF, ObjCPropertyAttribute::validate);
        }
        JNI.invokePPPV(j, j2, j3, i, z, z2, j4);
    }

    public static void protocol_addProperty(@NativeType("Protocol *") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("objc_property_attribute_t const *") ObjCPropertyAttribute.Buffer buffer, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nprotocol_addProperty(j, MemoryUtil.memAddress(byteBuffer), buffer.address(), buffer.remaining(), z, z2);
    }

    public static void protocol_addProperty(@NativeType("Protocol *") long j, @NativeType("char const *") CharSequence charSequence, @NativeType("objc_property_attribute_t const *") ObjCPropertyAttribute.Buffer buffer, @NativeType("BOOL") boolean z, @NativeType("BOOL") boolean z2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nprotocol_addProperty(j, stackGet.getPointerAddress(), buffer.address(), buffer.remaining(), z, z2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nobjc_copyImageNames(long j) {
        return JNI.invokePP(j, Functions.objc_copyImageNames);
    }

    @NativeType("char const **")
    public static PointerBuffer objc_copyImageNames() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nobjc_copyImageNames(MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nclass_getImageName(long j) {
        long j2 = Functions.class_getImageName;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String class_getImageName(@NativeType("Class") long j) {
        return MemoryUtil.memUTF8Safe(nclass_getImageName(j));
    }

    public static long nobjc_copyClassNamesForImage(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.objc_copyClassNamesForImage);
    }

    @NativeType("char const **")
    public static PointerBuffer objc_copyClassNamesForImage(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nobjc_copyClassNamesForImage(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("char const **")
    public static PointerBuffer objc_copyClassNamesForImage(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            stackGet.nUTF8(charSequence, true);
            return MemoryUtil.memPointerBufferSafe(nobjc_copyClassNamesForImage(stackGet.getPointerAddress(), MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nsel_getName(long j) {
        long j2 = Functions.sel_getName;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String sel_getName(@NativeType("SEL") long j) {
        return MemoryUtil.memUTF8Safe(nsel_getName(j));
    }

    public static long nsel_getUid(long j) {
        return JNI.invokePP(j, Functions.sel_getUid);
    }

    @NativeType("SEL")
    public static long sel_getUid(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nsel_getUid(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("SEL")
    public static long sel_getUid(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nsel_getUid(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nsel_registerName(long j) {
        return JNI.invokePP(j, Functions.sel_registerName);
    }

    @NativeType("SEL")
    public static long sel_registerName(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nsel_registerName(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("SEL")
    public static long sel_registerName(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nsel_registerName(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("BOOL")
    public static boolean sel_isEqual(@NativeType("SEL") long j, @NativeType("SEL") long j2) {
        long j3 = Functions.sel_isEqual;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPZ(j, j2, j3);
    }

    public static void objc_enumerationMutation(@NativeType("id") long j) {
        long j2 = Functions.objc_enumerationMutation;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void nobjc_setEnumerationMutationHandler(long j) {
        JNI.invokePV(j, Functions.objc_setEnumerationMutationHandler);
    }

    public static void objc_setEnumerationMutationHandler(@NativeType("EnumerationMutationHandler") EnumerationMutationHandlerI enumerationMutationHandlerI) {
        nobjc_setEnumerationMutationHandler(enumerationMutationHandlerI.address());
    }

    @NativeType("IMP")
    public static long imp_implementationWithBlock(@NativeType("id") long j) {
        long j2 = Functions.imp_implementationWithBlock;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long imp_getBlock(@NativeType("IMP") long j) {
        long j2 = Functions.imp_getBlock;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("BOOL")
    public static boolean imp_removeBlock(@NativeType("IMP") long j) {
        long j2 = Functions.imp_removeBlock;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePZ(j, j2);
    }

    public static long nobjc_loadWeak(long j) {
        return JNI.invokePP(j, Functions.objc_loadWeak);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long objc_loadWeak(@NativeType("id *") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nobjc_loadWeak(MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static long nobjc_storeWeak(long j, long j2) {
        long j3 = Functions.objc_storeWeak;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long objc_storeWeak(@NativeType("id *") PointerBuffer pointerBuffer, @NativeType("id") long j) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nobjc_storeWeak(MemoryUtil.memAddress(pointerBuffer), j);
    }

    public static void objc_setAssociatedObject(@NativeType("id") long j, @NativeType("void const *") long j2, @NativeType("id") long j3, @NativeType("objc_AssociationPolicy") long j4) {
        long j5 = Functions.objc_setAssociatedObject;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        JNI.invokePPPPV(j, j2, j3, j4, j5);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long objc_getAssociatedObject(@NativeType("id") long j, @NativeType("void const *") long j2) {
        long j3 = Functions.objc_getAssociatedObject;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    public static void objc_removeAssociatedObjects(@NativeType("id") long j) {
        long j2 = Functions.objc_removeAssociatedObjects;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }
}
