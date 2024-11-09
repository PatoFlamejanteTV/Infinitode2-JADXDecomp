package io.github.classgraph;

import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import io.github.classgraph.Scanner;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.PackageDescription;
import nonapi.io.github.classgraph.concurrency.WorkQueue;
import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;
import nonapi.io.github.classgraph.utils.StringUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/Classfile.class */
class Classfile {
    private ClassfileReader reader;
    private final ClasspathElement classpathElement;
    private final List<ClasspathElement> classpathOrder;
    private final String relativePath;
    private final Resource classfileResource;
    private final ConcurrentHashMap<String, String> stringInternMap;
    private String className;
    private int minorVersion;
    private int majorVersion;
    private final boolean isExternalClass;
    private int classModifiers;
    private boolean isInterface;
    private boolean isRecord;
    private boolean isAnnotation;
    private String superclassName;
    private List<String> implementedInterfaces;
    private AnnotationInfoList classAnnotations;
    private String fullyQualifiedDefiningMethodName;
    private List<ClassContainment> classContainmentEntries;
    private AnnotationParameterValueList annotationParamDefaultValues;
    private Set<String> refdClassNames;
    private FieldInfoList fieldInfoList;
    private MethodInfoList methodInfoList;
    private String typeSignatureStr;
    private String sourceFile;
    private List<ClassTypeAnnotationDecorator> classTypeAnnotationDecorators;
    private final Set<String> acceptedClassNamesFound;
    private final Set<String> classNamesScheduledForExtendedScanning;
    private List<Scanner.ClassfileScanWorkUnit> additionalWorkUnits;
    private final ScanSpec scanSpec;
    private int cpCount;
    private int[] entryOffset;
    private int[] entryTag;
    private int[] indirectStringRefs;
    private static final AnnotationInfo[] NO_ANNOTATIONS = new AnnotationInfo[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Classfile$ClassTypeAnnotationDecorator.class */
    public interface ClassTypeAnnotationDecorator {
        void decorate(ClassTypeSignature classTypeSignature);
    }

    /* loaded from: infinitode-2.jar:io/github/classgraph/Classfile$MethodTypeAnnotationDecorator.class */
    interface MethodTypeAnnotationDecorator {
        void decorate(MethodTypeSignature methodTypeSignature);
    }

    /* loaded from: infinitode-2.jar:io/github/classgraph/Classfile$TypeAnnotationDecorator.class */
    interface TypeAnnotationDecorator {
        void decorate(TypeSignature typeSignature);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Classfile$ClassContainment.class */
    public static class ClassContainment {
        public final String innerClassName;
        public final int innerClassModifierBits;
        public final String outerClassName;

        public ClassContainment(String str, int i, String str2) {
            this.innerClassName = str;
            this.innerClassModifierBits = i;
            this.outerClassName = str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Classfile$ClassfileFormatException.class */
    public static class ClassfileFormatException extends IOException {
        static final long serialVersionUID = 1;

        public ClassfileFormatException(String str) {
            super(str);
        }

        public ClassfileFormatException(String str, Throwable th) {
            super(str, th);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Classfile$SkipClassException.class */
    public static class SkipClassException extends IOException {
        static final long serialVersionUID = 1;

        public SkipClassException(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    private void scheduleScanningIfExternalClass(String str, String str2, LogNode logNode) {
        if (str != null && !str.equals("java.lang.Object") && !this.acceptedClassNamesFound.contains(str) && this.classNamesScheduledForExtendedScanning.add(str)) {
            if (this.scanSpec.classAcceptReject.isRejected(str)) {
                if (logNode != null) {
                    logNode.log("Cannot extend scanning upwards to external " + str2 + SequenceUtils.SPACE + str + ", since it is rejected");
                    return;
                }
                return;
            }
            String classNameToClassfilePath = JarUtils.classNameToClassfilePath(str);
            Resource resource = this.classpathElement.getResource(classNameToClassfilePath);
            ClasspathElement classpathElement = null;
            if (resource != null) {
                classpathElement = this.classpathElement;
            } else {
                Iterator<ClasspathElement> it = this.classpathOrder.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ClasspathElement next = it.next();
                    if (next != this.classpathElement) {
                        Resource resource2 = next.getResource(classNameToClassfilePath);
                        resource = resource2;
                        if (resource2 != null) {
                            classpathElement = next;
                            break;
                        }
                    }
                }
            }
            if (resource != null) {
                if (logNode != null) {
                    resource.scanLog = logNode.log("Extending scanning to external " + str2 + (classpathElement == this.classpathElement ? " in same classpath element" : " in classpath element " + classpathElement) + ": " + str);
                }
                if (this.additionalWorkUnits == null) {
                    this.additionalWorkUnits = new ArrayList();
                }
                this.additionalWorkUnits.add(new Scanner.ClassfileScanWorkUnit(classpathElement, resource, true));
                return;
            }
            if (logNode != null) {
                logNode.log("External " + str2 + SequenceUtils.SPACE + str + " was not found in non-rejected packages -- cannot extend scanning to this class");
            }
        }
    }

    private void extendScanningUpwardsFromAnnotationParameterValues(Object obj, LogNode logNode) {
        if (obj != null) {
            if (obj instanceof AnnotationInfo) {
                AnnotationInfo annotationInfo = (AnnotationInfo) obj;
                scheduleScanningIfExternalClass(annotationInfo.getClassName(), "annotation class", logNode);
                Iterator it = annotationInfo.getParameterValues().iterator();
                while (it.hasNext()) {
                    extendScanningUpwardsFromAnnotationParameterValues(((AnnotationParameterValue) it.next()).getValue(), logNode);
                }
                return;
            }
            if (obj instanceof AnnotationEnumValue) {
                scheduleScanningIfExternalClass(((AnnotationEnumValue) obj).getClassName(), "enum class", logNode);
                return;
            }
            if (obj instanceof AnnotationClassRef) {
                scheduleScanningIfExternalClass(((AnnotationClassRef) obj).getClassName(), "class ref", logNode);
                return;
            }
            if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    extendScanningUpwardsFromAnnotationParameterValues(Array.get(obj, i), logNode);
                }
            }
        }
    }

    private void extendScanningUpwards(LogNode logNode) {
        if (this.superclassName != null) {
            scheduleScanningIfExternalClass(this.superclassName, "superclass", logNode);
        }
        if (this.implementedInterfaces != null) {
            Iterator<String> it = this.implementedInterfaces.iterator();
            while (it.hasNext()) {
                scheduleScanningIfExternalClass(it.next(), "interface", logNode);
            }
        }
        if (this.classAnnotations != null) {
            Iterator it2 = this.classAnnotations.iterator();
            while (it2.hasNext()) {
                AnnotationInfo annotationInfo = (AnnotationInfo) it2.next();
                scheduleScanningIfExternalClass(annotationInfo.getName(), "class annotation", logNode);
                extendScanningUpwardsFromAnnotationParameterValues(annotationInfo, logNode);
            }
        }
        if (this.annotationParamDefaultValues != null) {
            Iterator it3 = this.annotationParamDefaultValues.iterator();
            while (it3.hasNext()) {
                extendScanningUpwardsFromAnnotationParameterValues(((AnnotationParameterValue) it3.next()).getValue(), logNode);
            }
        }
        if (this.methodInfoList != null) {
            Iterator it4 = this.methodInfoList.iterator();
            while (it4.hasNext()) {
                MethodInfo methodInfo = (MethodInfo) it4.next();
                if (methodInfo.annotationInfo != null) {
                    Iterator it5 = methodInfo.annotationInfo.iterator();
                    while (it5.hasNext()) {
                        AnnotationInfo annotationInfo2 = (AnnotationInfo) it5.next();
                        scheduleScanningIfExternalClass(annotationInfo2.getName(), "method annotation", logNode);
                        extendScanningUpwardsFromAnnotationParameterValues(annotationInfo2, logNode);
                    }
                    if (methodInfo.parameterAnnotationInfo != null && methodInfo.parameterAnnotationInfo.length > 0) {
                        for (AnnotationInfo[] annotationInfoArr : methodInfo.parameterAnnotationInfo) {
                            if (annotationInfoArr != null && annotationInfoArr.length > 0) {
                                for (AnnotationInfo annotationInfo3 : annotationInfoArr) {
                                    scheduleScanningIfExternalClass(annotationInfo3.getName(), "method parameter annotation", logNode);
                                    extendScanningUpwardsFromAnnotationParameterValues(annotationInfo3, logNode);
                                }
                            }
                        }
                    }
                }
                if (methodInfo.getThrownExceptionNames() != null) {
                    for (String str : methodInfo.getThrownExceptionNames()) {
                        scheduleScanningIfExternalClass(str, "method throws", logNode);
                    }
                }
            }
        }
        if (this.fieldInfoList != null) {
            Iterator it6 = this.fieldInfoList.iterator();
            while (it6.hasNext()) {
                FieldInfo fieldInfo = (FieldInfo) it6.next();
                if (fieldInfo.annotationInfo != null) {
                    Iterator it7 = fieldInfo.annotationInfo.iterator();
                    while (it7.hasNext()) {
                        AnnotationInfo annotationInfo4 = (AnnotationInfo) it7.next();
                        scheduleScanningIfExternalClass(annotationInfo4.getName(), "field annotation", logNode);
                        extendScanningUpwardsFromAnnotationParameterValues(annotationInfo4, logNode);
                    }
                }
            }
        }
        if (this.classContainmentEntries != null) {
            for (ClassContainment classContainment : this.classContainmentEntries) {
                if (classContainment.innerClassName.equals(this.className)) {
                    scheduleScanningIfExternalClass(classContainment.outerClassName, "outer class", logNode);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void link(Map<String, ClassInfo> map, Map<String, PackageInfo> map2, Map<String, ModuleInfo> map3) {
        boolean z = false;
        boolean z2 = false;
        ClassInfo classInfo = null;
        if (this.className.equals("module-info")) {
            z = true;
        } else if (this.className.equals(PackageDescription.PACKAGE_CLASS_NAME) || this.className.endsWith(".package-info")) {
            z2 = true;
        } else {
            ClassInfo addScannedClass = ClassInfo.addScannedClass(this.className, this.classModifiers, this.isExternalClass, map, this.classpathElement, this.classfileResource);
            classInfo = addScannedClass;
            addScannedClass.setClassfileVersion(this.minorVersion, this.majorVersion);
            classInfo.setModifiers(this.classModifiers);
            classInfo.setIsInterface(this.isInterface);
            classInfo.setIsAnnotation(this.isAnnotation);
            classInfo.setIsRecord(this.isRecord);
            classInfo.setSourceFile(this.sourceFile);
            if (this.superclassName != null) {
                classInfo.addSuperclass(this.superclassName, map);
            }
            if (this.implementedInterfaces != null) {
                Iterator<String> it = this.implementedInterfaces.iterator();
                while (it.hasNext()) {
                    classInfo.addImplementedInterface(it.next(), map);
                }
            }
            if (this.classAnnotations != null) {
                Iterator it2 = this.classAnnotations.iterator();
                while (it2.hasNext()) {
                    classInfo.addClassAnnotation((AnnotationInfo) it2.next(), map);
                }
            }
            if (this.classContainmentEntries != null) {
                ClassInfo.addClassContainment(this.classContainmentEntries, map);
            }
            if (this.annotationParamDefaultValues != null) {
                classInfo.addAnnotationParamDefaultValues(this.annotationParamDefaultValues);
            }
            if (this.fullyQualifiedDefiningMethodName != null) {
                classInfo.addFullyQualifiedDefiningMethodName(this.fullyQualifiedDefiningMethodName);
            }
            if (this.fieldInfoList != null) {
                classInfo.addFieldInfo(this.fieldInfoList, map);
            }
            if (this.methodInfoList != null) {
                classInfo.addMethodInfo(this.methodInfoList, map);
            }
            if (this.typeSignatureStr != null) {
                classInfo.setTypeSignature(this.typeSignatureStr);
            }
            if (this.refdClassNames != null) {
                classInfo.addReferencedClassNames(this.refdClassNames);
            }
            if (this.classTypeAnnotationDecorators != null) {
                classInfo.addTypeDecorators(this.classTypeAnnotationDecorators);
            }
        }
        PackageInfo packageInfo = null;
        if (!z) {
            packageInfo = PackageInfo.getOrCreatePackage(PackageInfo.getParentPackageName(this.className), map2, this.scanSpec);
            if (z2) {
                packageInfo.addAnnotations(this.classAnnotations);
            } else if (classInfo != null) {
                packageInfo.addClassInfo(classInfo);
                classInfo.packageInfo = packageInfo;
            }
        }
        String moduleName = this.classpathElement.getModuleName();
        if (moduleName != null) {
            ModuleInfo moduleInfo = map3.get(moduleName);
            ModuleInfo moduleInfo2 = moduleInfo;
            if (moduleInfo == null) {
                ModuleInfo moduleInfo3 = new ModuleInfo(this.classfileResource.getModuleRef(), this.classpathElement);
                moduleInfo2 = moduleInfo3;
                map3.put(moduleName, moduleInfo3);
            }
            if (z) {
                moduleInfo2.addAnnotations(this.classAnnotations);
            }
            if (classInfo != null) {
                moduleInfo2.addClassInfo(classInfo);
                classInfo.moduleInfo = moduleInfo2;
            }
            if (packageInfo != null) {
                moduleInfo2.addPackageInfo(packageInfo);
            }
        }
    }

    private String intern(String str) {
        if (str == null) {
            return null;
        }
        String putIfAbsent = this.stringInternMap.putIfAbsent(str, str);
        if (putIfAbsent != null) {
            return putIfAbsent;
        }
        return str;
    }

    private int getConstantPoolStringOffset(int i, int i2) {
        int i3;
        if (i <= 0 || i >= this.cpCount) {
            throw new ClassfileFormatException("Constant pool index " + i + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
        }
        int i4 = this.entryTag[i];
        if ((i4 != 12 && i2 != 0) || (i4 == 12 && i2 != 0 && i2 != 1)) {
            throw new ClassfileFormatException("Bad subfield index " + i2 + " for tag " + i4 + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
        }
        if (i4 == 0) {
            return 0;
        }
        if (i4 == 1) {
            i3 = i;
        } else if (i4 != 7 && i4 != 8 && i4 != 19) {
            if (i4 == 12) {
                int i5 = this.indirectStringRefs[i];
                if (i5 == -1) {
                    throw new ClassfileFormatException("Bad string indirection index, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
                }
                int i6 = (i2 == 0 ? i5 >> 16 : i5) & 65535;
                if (i6 == 0) {
                    throw new ClassfileFormatException("Bad string indirection index, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
                }
                i3 = i6;
            } else {
                throw new ClassfileFormatException("Wrong tag number " + i4 + " at constant pool index " + i + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
            }
        } else {
            int i7 = this.indirectStringRefs[i];
            if (i7 == -1) {
                throw new ClassfileFormatException("Bad string indirection index, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
            }
            if (i7 == 0) {
                return 0;
            }
            i3 = i7;
        }
        if (i3 <= 0 || i3 >= this.cpCount) {
            throw new ClassfileFormatException("Constant pool index " + i + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
        }
        return this.entryOffset[i3];
    }

    private String getConstantPoolString(int i, boolean z, boolean z2) {
        int constantPoolStringOffset = getConstantPoolStringOffset(i, 0);
        if (constantPoolStringOffset != 0) {
            int readUnsignedShort = this.reader.readUnsignedShort(constantPoolStringOffset);
            if (readUnsignedShort == 0) {
                return "";
            }
            return intern(this.reader.readString(constantPoolStringOffset + 2, readUnsignedShort, z, z2));
        }
        return null;
    }

    private String getConstantPoolString(int i, int i2) {
        int constantPoolStringOffset = getConstantPoolStringOffset(i, i2);
        if (constantPoolStringOffset != 0) {
            int readUnsignedShort = this.reader.readUnsignedShort(constantPoolStringOffset);
            if (readUnsignedShort == 0) {
                return "";
            }
            return intern(this.reader.readString(constantPoolStringOffset + 2, readUnsignedShort, false, false));
        }
        return null;
    }

    private String getConstantPoolString(int i) {
        return getConstantPoolString(i, 0);
    }

    private byte getConstantPoolStringFirstByte(int i) {
        int constantPoolStringOffset = getConstantPoolStringOffset(i, 0);
        if (constantPoolStringOffset != 0 && this.reader.readUnsignedShort(constantPoolStringOffset) != 0) {
            return this.reader.readByte(constantPoolStringOffset + 2);
        }
        return (byte) 0;
    }

    private String getConstantPoolClassName(int i) {
        return getConstantPoolString(i, true, false);
    }

    private String getConstantPoolClassDescriptor(int i) {
        return getConstantPoolString(i, true, true);
    }

    private boolean constantPoolStringEquals(int i, String str) {
        int readUnsignedShort;
        int constantPoolStringOffset = getConstantPoolStringOffset(i, 0);
        if (constantPoolStringOffset == 0) {
            return str == null;
        }
        if (str == null || (readUnsignedShort = this.reader.readUnsignedShort(constantPoolStringOffset)) != str.length()) {
            return false;
        }
        int i2 = constantPoolStringOffset + 2;
        this.reader.bufferTo(i2 + readUnsignedShort);
        byte[] buf = this.reader.buf();
        for (int i3 = 0; i3 < readUnsignedShort; i3++) {
            if (((char) (buf[i2 + i3] & 255)) != str.charAt(i3)) {
                return false;
            }
        }
        return true;
    }

    private int cpReadInt(int i) {
        if (i <= 0 || i >= this.cpCount) {
            throw new ClassfileFormatException("Constant pool index " + i + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
        }
        return this.reader.readInt(this.entryOffset[i]);
    }

    private long cpReadLong(int i) {
        if (i <= 0 || i >= this.cpCount) {
            throw new ClassfileFormatException("Constant pool index " + i + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
        }
        return this.reader.readLong(this.entryOffset[i]);
    }

    private Object getFieldConstantPoolValue(int i, char c, int i2) {
        switch (i) {
            case 1:
            case 7:
            case 8:
                return getConstantPoolString(i2);
            case 2:
            default:
                throw new ClassfileFormatException("Unknown field constant pool tag " + i + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
            case 3:
                int cpReadInt = cpReadInt(i2);
                switch (c) {
                    case 'B':
                        return Byte.valueOf((byte) cpReadInt);
                    case 'C':
                        return Character.valueOf((char) cpReadInt);
                    case 'I':
                        return Integer.valueOf(cpReadInt);
                    case 'S':
                        return Short.valueOf((short) cpReadInt);
                    case 'Z':
                        return Boolean.valueOf(cpReadInt != 0);
                    default:
                        throw new ClassfileFormatException("Unknown Constant_INTEGER type " + c + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
                }
            case 4:
                return Float.valueOf(Float.intBitsToFloat(cpReadInt(i2)));
            case 5:
                return Long.valueOf(cpReadLong(i2));
            case 6:
                return Double.valueOf(Double.longBitsToDouble(cpReadLong(i2)));
        }
    }

    private AnnotationInfo readAnnotation() {
        String constantPoolClassDescriptor = getConstantPoolClassDescriptor(this.reader.readUnsignedShort());
        int readUnsignedShort = this.reader.readUnsignedShort();
        AnnotationParameterValueList annotationParameterValueList = null;
        if (readUnsignedShort > 0) {
            annotationParameterValueList = new AnnotationParameterValueList(readUnsignedShort);
            for (int i = 0; i < readUnsignedShort; i++) {
                annotationParameterValueList.add(new AnnotationParameterValue(getConstantPoolString(this.reader.readUnsignedShort()), readAnnotationElementValue()));
            }
        }
        return new AnnotationInfo(constantPoolClassDescriptor, annotationParameterValueList);
    }

    private Object readAnnotationElementValue() {
        char readUnsignedByte = (char) this.reader.readUnsignedByte();
        switch (readUnsignedByte) {
            case '@':
                return readAnnotation();
            case 'A':
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            default:
                throw new ClassfileFormatException("Class " + this.className + " has unknown annotation element type tag '" + readUnsignedByte + "': element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
            case 'B':
                return Byte.valueOf((byte) cpReadInt(this.reader.readUnsignedShort()));
            case 'C':
                return Character.valueOf((char) cpReadInt(this.reader.readUnsignedShort()));
            case 'D':
                return Double.valueOf(Double.longBitsToDouble(cpReadLong(this.reader.readUnsignedShort())));
            case 'F':
                return Float.valueOf(Float.intBitsToFloat(cpReadInt(this.reader.readUnsignedShort())));
            case 'I':
                return Integer.valueOf(cpReadInt(this.reader.readUnsignedShort()));
            case 'J':
                return Long.valueOf(cpReadLong(this.reader.readUnsignedShort()));
            case 'S':
                return Short.valueOf((short) cpReadInt(this.reader.readUnsignedShort()));
            case 'Z':
                return Boolean.valueOf(cpReadInt(this.reader.readUnsignedShort()) != 0);
            case '[':
                int readUnsignedShort = this.reader.readUnsignedShort();
                Object[] objArr = new Object[readUnsignedShort];
                for (int i = 0; i < readUnsignedShort; i++) {
                    objArr[i] = readAnnotationElementValue();
                }
                return objArr;
            case 'c':
                return new AnnotationClassRef(getConstantPoolString(this.reader.readUnsignedShort()));
            case 'e':
                return new AnnotationEnumValue(getConstantPoolClassDescriptor(this.reader.readUnsignedShort()), getConstantPoolString(this.reader.readUnsignedShort()));
            case 's':
                return getConstantPoolString(this.reader.readUnsignedShort());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Classfile$TypePathNode.class */
    public static class TypePathNode {
        short typePathKind;
        short typeArgumentIdx;

        public TypePathNode(int i, int i2) {
            this.typePathKind = (short) i;
            this.typeArgumentIdx = (short) i2;
        }

        public String toString() {
            return "(" + ((int) this.typePathKind) + "," + ((int) this.typeArgumentIdx) + ")";
        }
    }

    private List<TypePathNode> readTypePath() {
        int readUnsignedByte = this.reader.readUnsignedByte();
        if (readUnsignedByte != 0) {
            ArrayList arrayList = new ArrayList(readUnsignedByte);
            for (int i = 0; i < readUnsignedByte; i++) {
                arrayList.add(new TypePathNode(this.reader.readUnsignedByte(), this.reader.readUnsignedByte()));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    private void readConstantPoolEntries(LogNode logNode) {
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        if (this.scanSpec.enableInterClassDependencies) {
            arrayList = new ArrayList();
            arrayList2 = new ArrayList();
        }
        this.cpCount = this.reader.readUnsignedShort();
        this.entryOffset = new int[this.cpCount];
        this.entryTag = new int[this.cpCount];
        this.indirectStringRefs = new int[this.cpCount];
        Arrays.fill(this.indirectStringRefs, 0, this.cpCount, -1);
        boolean z = false;
        for (int i = 1; i < this.cpCount; i++) {
            if (z) {
                z = false;
            } else {
                this.entryTag[i] = this.reader.readUnsignedByte();
                this.entryOffset[i] = this.reader.currPos();
                switch (this.entryTag[i]) {
                    case 0:
                        throw new ClassfileFormatException("Invalid constant pool tag 0 in classfile " + this.relativePath + " (possible buffer underflow issue). Please report this at https://github.com/classgraph/classgraph/issues");
                    case 1:
                        this.reader.skip(this.reader.readUnsignedShort());
                        break;
                    case 2:
                    case 13:
                    case 14:
                    default:
                        throw new ClassfileFormatException("Unknown constant pool tag " + this.entryTag[i] + " (element size unknown, cannot continue reading class). Please report this at https://github.com/classgraph/classgraph/issues");
                    case 3:
                    case 4:
                        this.reader.skip(4);
                        break;
                    case 5:
                    case 6:
                        this.reader.skip(8);
                        z = true;
                        break;
                    case 7:
                        this.indirectStringRefs[i] = this.reader.readUnsignedShort();
                        if (arrayList != null) {
                            arrayList.add(Integer.valueOf(this.indirectStringRefs[i]));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        this.indirectStringRefs[i] = this.reader.readUnsignedShort();
                        break;
                    case 9:
                        this.reader.skip(4);
                        break;
                    case 10:
                        this.reader.skip(4);
                        break;
                    case 11:
                        this.reader.skip(4);
                        break;
                    case 12:
                        int readUnsignedShort = this.reader.readUnsignedShort();
                        int readUnsignedShort2 = this.reader.readUnsignedShort();
                        if (arrayList2 != null) {
                            arrayList2.add(Integer.valueOf(readUnsignedShort2));
                        }
                        this.indirectStringRefs[i] = (readUnsignedShort << 16) | readUnsignedShort2;
                        break;
                    case 15:
                        this.reader.skip(3);
                        break;
                    case 16:
                        this.reader.skip(2);
                        break;
                    case 17:
                        this.reader.skip(4);
                        break;
                    case 18:
                        this.reader.skip(4);
                        break;
                    case 19:
                        this.indirectStringRefs[i] = this.reader.readUnsignedShort();
                        break;
                    case 20:
                        this.reader.skip(2);
                        break;
                }
            }
        }
        if (arrayList != null) {
            this.refdClassNames = new HashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String constantPoolString = getConstantPoolString(((Integer) it.next()).intValue(), true, false);
                if (constantPoolString != null) {
                    if (!constantPoolString.startsWith("[")) {
                        this.refdClassNames.add(constantPoolString);
                    } else {
                        try {
                            TypeSignature.parse(constantPoolString.replace('.', '/'), (String) null).findReferencedClassNames(this.refdClassNames);
                        } catch (ParseException e) {
                            throw new ClassfileFormatException("Could not parse class name: " + constantPoolString, e);
                        }
                    }
                }
            }
        }
        if (arrayList2 != null) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                String constantPoolString2 = getConstantPoolString(((Integer) it2.next()).intValue());
                if (constantPoolString2 != null) {
                    try {
                        if (!constantPoolString2.startsWith("L") || !constantPoolString2.endsWith(";")) {
                            if (constantPoolString2.indexOf(40) >= 0 || MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(constantPoolString2)) {
                                MethodTypeSignature.parse(constantPoolString2, null).findReferencedClassNames(this.refdClassNames);
                            } else if (logNode != null) {
                                logNode.log("Could not extract referenced class names from constant pool string: " + constantPoolString2);
                            }
                        } else {
                            TypeSignature.parse(constantPoolString2, (String) null).findReferencedClassNames(this.refdClassNames);
                        }
                    } catch (ParseException e2) {
                        if (logNode != null) {
                            logNode.log("Could not extract referenced class names from constant pool string: " + constantPoolString2 + " : " + e2);
                        }
                    }
                }
            }
        }
    }

    private void readBasicClassInfo() {
        this.classModifiers = this.reader.readUnsignedShort();
        this.isInterface = (this.classModifiers & 512) != 0;
        this.isAnnotation = (this.classModifiers & 8192) != 0;
        String constantPoolString = getConstantPoolString(this.reader.readUnsignedShort());
        if (constantPoolString == null) {
            throw new ClassfileFormatException("Class name is null");
        }
        this.className = constantPoolString.replace('/', '.');
        if ("java.lang.Object".equals(this.className)) {
            throw new SkipClassException("No need to scan java.lang.Object");
        }
        boolean z = (this.classModifiers & 32768) != 0;
        boolean regionMatches = this.relativePath.regionMatches(this.relativePath.lastIndexOf(47) + 1, Plugin.Engine.PACKAGE_INFO, 0, 18);
        if (!this.scanSpec.ignoreClassVisibility && !Modifier.isPublic(this.classModifiers) && !z && !regionMatches) {
            throw new SkipClassException("Class is not public, and ignoreClassVisibility() was not called");
        }
        if (!this.relativePath.endsWith(".class")) {
            throw new SkipClassException("Classfile filename " + this.relativePath + " does not end in \".class\"");
        }
        int length = constantPoolString.length();
        if (this.relativePath.length() != length + 6 || !constantPoolString.regionMatches(0, this.relativePath, 0, length)) {
            throw new SkipClassException("Relative path " + this.relativePath + " does not match class name " + this.className);
        }
        int readUnsignedShort = this.reader.readUnsignedShort();
        if (readUnsignedShort > 0) {
            this.superclassName = getConstantPoolClassName(readUnsignedShort);
        }
    }

    private void readInterfaces() {
        int readUnsignedShort = this.reader.readUnsignedShort();
        for (int i = 0; i < readUnsignedShort; i++) {
            String constantPoolClassName = getConstantPoolClassName(this.reader.readUnsignedShort());
            if (this.implementedInterfaces == null) {
                this.implementedInterfaces = new ArrayList();
            }
            this.implementedInterfaces.add(constantPoolClassName);
        }
    }

    private void readFields() {
        int readUnsignedShort = this.reader.readUnsignedShort();
        for (int i = 0; i < readUnsignedShort; i++) {
            int readUnsignedShort2 = this.reader.readUnsignedShort();
            boolean z = ((readUnsignedShort2 & 1) == 1) || this.scanSpec.ignoreFieldVisibility;
            boolean z2 = this.scanSpec.enableStaticFinalFieldConstantInitializerValues && z;
            ArrayList arrayList = null;
            if (!z || (!this.scanSpec.enableFieldInfo && !z2)) {
                this.reader.readUnsignedShort();
                this.reader.readUnsignedShort();
                int readUnsignedShort3 = this.reader.readUnsignedShort();
                for (int i2 = 0; i2 < readUnsignedShort3; i2++) {
                    this.reader.readUnsignedShort();
                    this.reader.skip(this.reader.readInt());
                }
            } else {
                String constantPoolString = getConstantPoolString(this.reader.readUnsignedShort());
                int readUnsignedShort4 = this.reader.readUnsignedShort();
                char constantPoolStringFirstByte = (char) getConstantPoolStringFirstByte(readUnsignedShort4);
                String str = null;
                String constantPoolString2 = getConstantPoolString(readUnsignedShort4);
                Object obj = null;
                AnnotationInfoList annotationInfoList = null;
                int readUnsignedShort5 = this.reader.readUnsignedShort();
                for (int i3 = 0; i3 < readUnsignedShort5; i3++) {
                    int readUnsignedShort6 = this.reader.readUnsignedShort();
                    int readInt = this.reader.readInt();
                    if (z2 && constantPoolStringEquals(readUnsignedShort6, "ConstantValue")) {
                        int readUnsignedShort7 = this.reader.readUnsignedShort();
                        if (readUnsignedShort7 <= 0 || readUnsignedShort7 >= this.cpCount) {
                            throw new ClassfileFormatException("Constant pool index " + readUnsignedShort7 + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
                        }
                        obj = getFieldConstantPoolValue(this.entryTag[readUnsignedShort7], constantPoolStringFirstByte, readUnsignedShort7);
                    } else if (z && constantPoolStringEquals(readUnsignedShort6, "Signature")) {
                        str = getConstantPoolString(this.reader.readUnsignedShort());
                    } else if (this.scanSpec.enableAnnotationInfo && (constantPoolStringEquals(readUnsignedShort6, "RuntimeVisibleAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && constantPoolStringEquals(readUnsignedShort6, "RuntimeInvisibleAnnotations")))) {
                        int readUnsignedShort8 = this.reader.readUnsignedShort();
                        if (readUnsignedShort8 > 0) {
                            if (annotationInfoList == null) {
                                annotationInfoList = new AnnotationInfoList(1);
                            }
                            for (int i4 = 0; i4 < readUnsignedShort8; i4++) {
                                annotationInfoList.add(readAnnotation());
                            }
                        }
                    } else if (this.scanSpec.enableAnnotationInfo && (constantPoolStringEquals(readUnsignedShort6, "RuntimeVisibleTypeAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && constantPoolStringEquals(readUnsignedShort6, "RuntimeInvisibleTypeAnnotations")))) {
                        int readUnsignedShort9 = this.reader.readUnsignedShort();
                        if (readUnsignedShort9 > 0) {
                            arrayList = new ArrayList();
                            for (int i5 = 0; i5 < readUnsignedShort9; i5++) {
                                int readUnsignedByte = this.reader.readUnsignedByte();
                                if (readUnsignedByte != 19) {
                                    throw new ClassfileFormatException("Class " + this.className + " has unknown field type annotation target 0x" + Integer.toHexString(readUnsignedByte) + ": element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
                                }
                                final List<TypePathNode> readTypePath = readTypePath();
                                final AnnotationInfo readAnnotation = readAnnotation();
                                arrayList.add(new TypeAnnotationDecorator() { // from class: io.github.classgraph.Classfile.1
                                    @Override // io.github.classgraph.Classfile.TypeAnnotationDecorator
                                    public void decorate(TypeSignature typeSignature) {
                                        typeSignature.addTypeAnnotation(readTypePath, readAnnotation);
                                    }
                                });
                            }
                        } else {
                            continue;
                        }
                    } else {
                        this.reader.skip(readInt);
                    }
                }
                if (this.scanSpec.enableFieldInfo && z) {
                    if (this.fieldInfoList == null) {
                        this.fieldInfoList = new FieldInfoList();
                    }
                    this.fieldInfoList.add(new FieldInfo(this.className, constantPoolString, readUnsignedShort2, constantPoolString2, str, obj, annotationInfoList, arrayList));
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v268, types: [io.github.classgraph.AnnotationInfo[]] */
    private void readMethods() {
        int i;
        int i2;
        int i3;
        int readUnsignedShort;
        int readUnsignedShort2 = this.reader.readUnsignedShort();
        for (int i4 = 0; i4 < readUnsignedShort2; i4++) {
            int readUnsignedShort3 = this.reader.readUnsignedShort();
            boolean z = ((readUnsignedShort3 & 1) == 1) || this.scanSpec.ignoreMethodVisibility;
            ArrayList arrayList = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            boolean z2 = this.scanSpec.enableMethodInfo || this.isAnnotation;
            boolean z3 = z2;
            if (z2 || this.isAnnotation) {
                str = getConstantPoolString(this.reader.readUnsignedShort());
                str2 = getConstantPoolString(this.reader.readUnsignedShort());
            } else {
                this.reader.skip(4);
            }
            int readUnsignedShort4 = this.reader.readUnsignedShort();
            String[] strArr = null;
            String[] strArr2 = null;
            int[] iArr = null;
            AnnotationInfo[][] annotationInfoArr = (AnnotationInfo[][]) null;
            AnnotationInfoList annotationInfoList = null;
            boolean z4 = false;
            int i5 = 0;
            int i6 = 0;
            if (!z || (!z3 && !this.isAnnotation)) {
                for (int i7 = 0; i7 < readUnsignedShort4; i7++) {
                    this.reader.skip(2);
                    this.reader.skip(this.reader.readInt());
                }
            } else {
                for (int i8 = 0; i8 < readUnsignedShort4; i8++) {
                    int readUnsignedShort5 = this.reader.readUnsignedShort();
                    int readInt = this.reader.readInt();
                    if (this.scanSpec.enableAnnotationInfo && (constantPoolStringEquals(readUnsignedShort5, "RuntimeVisibleAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && constantPoolStringEquals(readUnsignedShort5, "RuntimeInvisibleAnnotations")))) {
                        int readUnsignedShort6 = this.reader.readUnsignedShort();
                        if (readUnsignedShort6 > 0) {
                            if (annotationInfoList == null) {
                                annotationInfoList = new AnnotationInfoList(1);
                            }
                            for (int i9 = 0; i9 < readUnsignedShort6; i9++) {
                                annotationInfoList.add(readAnnotation());
                            }
                        }
                    } else if (this.scanSpec.enableAnnotationInfo && (constantPoolStringEquals(readUnsignedShort5, "RuntimeVisibleParameterAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && constantPoolStringEquals(readUnsignedShort5, "RuntimeInvisibleParameterAnnotations")))) {
                        int readUnsignedByte = this.reader.readUnsignedByte();
                        if (annotationInfoArr == null) {
                            annotationInfoArr = new AnnotationInfo[readUnsignedByte];
                        } else if (annotationInfoArr.length != readUnsignedByte) {
                            throw new ClassfileFormatException("Mismatch in number of parameters between RuntimeVisibleParameterAnnotations and RuntimeInvisibleParameterAnnotations");
                        }
                        for (int i10 = 0; i10 < readUnsignedByte; i10++) {
                            int readUnsignedShort7 = this.reader.readUnsignedShort();
                            if (readUnsignedShort7 > 0) {
                                int i11 = 0;
                                if (annotationInfoArr[i10] == null) {
                                    annotationInfoArr[i10] = new AnnotationInfo[readUnsignedShort7];
                                } else {
                                    i11 = annotationInfoArr[i10].length;
                                    annotationInfoArr[i10] = (AnnotationInfo[]) Arrays.copyOf(annotationInfoArr[i10], i11 + readUnsignedShort7);
                                }
                                for (int i12 = 0; i12 < readUnsignedShort7; i12++) {
                                    annotationInfoArr[i10][i11 + i12] = readAnnotation();
                                }
                            } else if (annotationInfoArr[i10] == null) {
                                annotationInfoArr[i10] = NO_ANNOTATIONS;
                            }
                        }
                    } else if (this.scanSpec.enableAnnotationInfo && (constantPoolStringEquals(readUnsignedShort5, "RuntimeVisibleTypeAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && constantPoolStringEquals(readUnsignedShort5, "RuntimeInvisibleTypeAnnotations")))) {
                        int readUnsignedShort8 = this.reader.readUnsignedShort();
                        if (readUnsignedShort8 > 0) {
                            arrayList = new ArrayList(readUnsignedShort8);
                            for (int i13 = 0; i13 < readUnsignedShort8; i13++) {
                                final int readUnsignedByte2 = this.reader.readUnsignedByte();
                                if (readUnsignedByte2 == 1) {
                                    i = this.reader.readUnsignedByte();
                                    i2 = -1;
                                    i3 = -1;
                                    readUnsignedShort = -1;
                                } else if (readUnsignedByte2 == 18) {
                                    i = this.reader.readUnsignedByte();
                                    i2 = this.reader.readUnsignedByte();
                                    i3 = -1;
                                    readUnsignedShort = -1;
                                } else if (readUnsignedByte2 == 19) {
                                    i = -1;
                                    i2 = -1;
                                    i3 = -1;
                                    readUnsignedShort = -1;
                                } else if (readUnsignedByte2 == 20) {
                                    i = -1;
                                    i2 = -1;
                                    i3 = -1;
                                    readUnsignedShort = -1;
                                } else if (readUnsignedByte2 == 21) {
                                    i = -1;
                                    i2 = -1;
                                    i3 = -1;
                                    readUnsignedShort = -1;
                                } else if (readUnsignedByte2 == 22) {
                                    i = -1;
                                    i2 = -1;
                                    i3 = this.reader.readUnsignedByte();
                                    readUnsignedShort = -1;
                                } else if (readUnsignedByte2 == 23) {
                                    i = -1;
                                    i2 = -1;
                                    i3 = -1;
                                    readUnsignedShort = this.reader.readUnsignedShort();
                                } else {
                                    throw new ClassfileFormatException("Class " + this.className + " has unknown method type annotation target 0x" + Integer.toHexString(readUnsignedByte2) + ": element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
                                }
                                final int i14 = readUnsignedShort;
                                final List<TypePathNode> readTypePath = readTypePath();
                                final AnnotationInfo readAnnotation = readAnnotation();
                                final int i15 = i;
                                final int i16 = i2;
                                final int i17 = i3;
                                arrayList.add(new MethodTypeAnnotationDecorator() { // from class: io.github.classgraph.Classfile.2
                                    @Override // io.github.classgraph.Classfile.MethodTypeAnnotationDecorator
                                    public void decorate(MethodTypeSignature methodTypeSignature) {
                                        List<ClassRefOrTypeVariableSignature> throwsSignatures;
                                        if (readUnsignedByte2 == 1) {
                                            List<TypeParameter> typeParameters = methodTypeSignature.getTypeParameters();
                                            if (typeParameters != null && i15 < typeParameters.size()) {
                                                typeParameters.get(i15).addTypeAnnotation(readTypePath, readAnnotation);
                                                return;
                                            }
                                            return;
                                        }
                                        if (readUnsignedByte2 == 18) {
                                            List<TypeParameter> typeParameters2 = methodTypeSignature.getTypeParameters();
                                            if (typeParameters2 != null && i15 < typeParameters2.size()) {
                                                TypeParameter typeParameter = typeParameters2.get(i15);
                                                if (i16 == 0) {
                                                    ReferenceTypeSignature classBound = typeParameter.getClassBound();
                                                    if (classBound != null) {
                                                        classBound.addTypeAnnotation(readTypePath, readAnnotation);
                                                        return;
                                                    }
                                                    return;
                                                }
                                                List<ReferenceTypeSignature> interfaceBounds = typeParameter.getInterfaceBounds();
                                                if (interfaceBounds != null && i16 - 1 < interfaceBounds.size()) {
                                                    interfaceBounds.get(i16 - 1).addTypeAnnotation(readTypePath, readAnnotation);
                                                    return;
                                                }
                                                return;
                                            }
                                            return;
                                        }
                                        if (readUnsignedByte2 == 20) {
                                            methodTypeSignature.getResultType().addTypeAnnotation(readTypePath, readAnnotation);
                                            return;
                                        }
                                        if (readUnsignedByte2 == 21) {
                                            methodTypeSignature.addRecieverTypeAnnotation(readAnnotation);
                                            return;
                                        }
                                        if (readUnsignedByte2 == 22) {
                                            List<TypeSignature> parameterTypeSignatures = methodTypeSignature.getParameterTypeSignatures();
                                            if (i17 < parameterTypeSignatures.size()) {
                                                parameterTypeSignatures.get(i17).addTypeAnnotation(readTypePath, readAnnotation);
                                                return;
                                            }
                                            return;
                                        }
                                        if (readUnsignedByte2 == 23 && (throwsSignatures = methodTypeSignature.getThrowsSignatures()) != null && i14 < throwsSignatures.size()) {
                                            throwsSignatures.get(i14).addTypeAnnotation(readTypePath, readAnnotation);
                                        }
                                    }
                                });
                            }
                        } else {
                            continue;
                        }
                    } else if (constantPoolStringEquals(readUnsignedShort5, "MethodParameters")) {
                        int readUnsignedByte3 = this.reader.readUnsignedByte();
                        strArr = new String[readUnsignedByte3];
                        iArr = new int[readUnsignedByte3];
                        for (int i18 = 0; i18 < readUnsignedByte3; i18++) {
                            int readUnsignedShort9 = this.reader.readUnsignedShort();
                            strArr[i18] = readUnsignedShort9 == 0 ? null : getConstantPoolString(readUnsignedShort9);
                            iArr[i18] = this.reader.readUnsignedShort();
                        }
                    } else if (constantPoolStringEquals(readUnsignedShort5, "Signature")) {
                        str3 = getConstantPoolString(this.reader.readUnsignedShort());
                    } else if (constantPoolStringEquals(readUnsignedShort5, "AnnotationDefault")) {
                        if (this.annotationParamDefaultValues == null) {
                            this.annotationParamDefaultValues = new AnnotationParameterValueList();
                        }
                        this.annotationParamDefaultValues.add(new AnnotationParameterValue(str, readAnnotationElementValue()));
                    } else if (constantPoolStringEquals(readUnsignedShort5, "Exceptions")) {
                        int readUnsignedShort10 = this.reader.readUnsignedShort();
                        strArr2 = new String[readUnsignedShort10];
                        for (int i19 = 0; i19 < readUnsignedShort10; i19++) {
                            strArr2[i19] = getConstantPoolClassName(this.reader.readUnsignedShort());
                        }
                    } else if (constantPoolStringEquals(readUnsignedShort5, "Code")) {
                        z4 = true;
                        this.reader.skip(4);
                        this.reader.skip(this.reader.readInt());
                        this.reader.skip(this.reader.readUnsignedShort() * 8);
                        int readUnsignedShort11 = this.reader.readUnsignedShort();
                        for (int i20 = 0; i20 < readUnsignedShort11; i20++) {
                            int readUnsignedShort12 = this.reader.readUnsignedShort();
                            int readInt2 = this.reader.readInt();
                            if (constantPoolStringEquals(readUnsignedShort12, "LineNumberTable")) {
                                int readUnsignedShort13 = this.reader.readUnsignedShort();
                                for (int i21 = 0; i21 < readUnsignedShort13; i21++) {
                                    this.reader.skip(2);
                                    int readUnsignedShort14 = this.reader.readUnsignedShort();
                                    i5 = i5 == 0 ? readUnsignedShort14 : Math.min(i5, readUnsignedShort14);
                                    i6 = i6 == 0 ? readUnsignedShort14 : Math.max(i6, readUnsignedShort14);
                                }
                            } else {
                                this.reader.skip(readInt2);
                            }
                        }
                    } else {
                        this.reader.skip(readInt);
                    }
                }
                if (z3) {
                    if (this.methodInfoList == null) {
                        this.methodInfoList = new MethodInfoList();
                    }
                    this.methodInfoList.add(new MethodInfo(this.className, str, annotationInfoList, readUnsignedShort3, str2, str3, strArr, iArr, annotationInfoArr, z4, i5, i6, arrayList, strArr2));
                }
            }
        }
    }

    private void readClassAttributes() {
        String constantPoolString;
        int readUnsignedByte;
        int readUnsignedByte2;
        int i;
        int readUnsignedShort = this.reader.readUnsignedShort();
        for (int i2 = 0; i2 < readUnsignedShort; i2++) {
            int readUnsignedShort2 = this.reader.readUnsignedShort();
            int readInt = this.reader.readInt();
            if (this.scanSpec.enableAnnotationInfo && (constantPoolStringEquals(readUnsignedShort2, "RuntimeVisibleAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && constantPoolStringEquals(readUnsignedShort2, "RuntimeInvisibleAnnotations")))) {
                int readUnsignedShort3 = this.reader.readUnsignedShort();
                if (readUnsignedShort3 > 0) {
                    if (this.classAnnotations == null) {
                        this.classAnnotations = new AnnotationInfoList();
                    }
                    for (int i3 = 0; i3 < readUnsignedShort3; i3++) {
                        this.classAnnotations.add(readAnnotation());
                    }
                }
            } else if (this.scanSpec.enableAnnotationInfo && (constantPoolStringEquals(readUnsignedShort2, "RuntimeVisibleTypeAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && constantPoolStringEquals(readUnsignedShort2, "RuntimeInvisibleTypeAnnotations")))) {
                int readUnsignedShort4 = this.reader.readUnsignedShort();
                if (readUnsignedShort4 > 0) {
                    this.classTypeAnnotationDecorators = new ArrayList(readUnsignedShort4);
                    for (int i4 = 0; i4 < readUnsignedShort4; i4++) {
                        final int readUnsignedByte3 = this.reader.readUnsignedByte();
                        if (readUnsignedByte3 == 0) {
                            readUnsignedByte = this.reader.readUnsignedByte();
                            i = -1;
                            readUnsignedByte2 = -1;
                        } else if (readUnsignedByte3 == 16) {
                            i = this.reader.readUnsignedShort();
                            readUnsignedByte = -1;
                            readUnsignedByte2 = -1;
                        } else if (readUnsignedByte3 == 17) {
                            readUnsignedByte = this.reader.readUnsignedByte();
                            readUnsignedByte2 = this.reader.readUnsignedByte();
                            i = -1;
                        } else {
                            throw new ClassfileFormatException("Class " + this.className + " has unknown class type annotation target 0x" + Integer.toHexString(readUnsignedByte3) + ": element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
                        }
                        final List<TypePathNode> readTypePath = readTypePath();
                        final AnnotationInfo readAnnotation = readAnnotation();
                        final int i5 = readUnsignedByte;
                        final int i6 = i;
                        final int i7 = readUnsignedByte2;
                        this.classTypeAnnotationDecorators.add(new ClassTypeAnnotationDecorator() { // from class: io.github.classgraph.Classfile.3
                            @Override // io.github.classgraph.Classfile.ClassTypeAnnotationDecorator
                            public void decorate(ClassTypeSignature classTypeSignature) {
                                List<TypeParameter> typeParameters;
                                if (readUnsignedByte3 == 0) {
                                    List<TypeParameter> typeParameters2 = classTypeSignature.getTypeParameters();
                                    if (typeParameters2 != null && i5 < typeParameters2.size()) {
                                        typeParameters2.get(i5).addTypeAnnotation(readTypePath, readAnnotation);
                                        return;
                                    }
                                    return;
                                }
                                if (readUnsignedByte3 == 16) {
                                    if (i6 == 65535) {
                                        classTypeSignature.getSuperclassSignature().addTypeAnnotation(readTypePath, readAnnotation);
                                        return;
                                    } else {
                                        classTypeSignature.getSuperinterfaceSignatures().get(i6).addTypeAnnotation(readTypePath, readAnnotation);
                                        return;
                                    }
                                }
                                if (readUnsignedByte3 == 17 && (typeParameters = classTypeSignature.getTypeParameters()) != null && i5 < typeParameters.size()) {
                                    TypeParameter typeParameter = typeParameters.get(i5);
                                    if (i7 == 0) {
                                        ReferenceTypeSignature classBound = typeParameter.getClassBound();
                                        if (classBound != null) {
                                            classBound.addTypeAnnotation(readTypePath, readAnnotation);
                                            return;
                                        }
                                        return;
                                    }
                                    List<ReferenceTypeSignature> interfaceBounds = typeParameter.getInterfaceBounds();
                                    if (interfaceBounds != null && i7 - 1 < interfaceBounds.size()) {
                                        typeParameter.getInterfaceBounds().get(i7 - 1).addTypeAnnotation(readTypePath, readAnnotation);
                                    }
                                }
                            }
                        });
                    }
                } else {
                    continue;
                }
            } else {
                if (constantPoolStringEquals(readUnsignedShort2, "Record")) {
                    this.isRecord = true;
                } else if (constantPoolStringEquals(readUnsignedShort2, "InnerClasses")) {
                    int readUnsignedShort5 = this.reader.readUnsignedShort();
                    for (int i8 = 0; i8 < readUnsignedShort5; i8++) {
                        int readUnsignedShort6 = this.reader.readUnsignedShort();
                        int readUnsignedShort7 = this.reader.readUnsignedShort();
                        this.reader.skip(2);
                        int readUnsignedShort8 = this.reader.readUnsignedShort();
                        if (readUnsignedShort6 != 0 && readUnsignedShort7 != 0) {
                            String constantPoolClassName = getConstantPoolClassName(readUnsignedShort6);
                            String constantPoolClassName2 = getConstantPoolClassName(readUnsignedShort7);
                            if (constantPoolClassName == null || constantPoolClassName2 == null) {
                                throw new ClassfileFormatException("Inner and/or outer class name is null");
                            }
                            if (constantPoolClassName.equals(constantPoolClassName2)) {
                                throw new ClassfileFormatException("Inner and outer class name cannot be the same");
                            }
                            if (!"java.lang.invoke.MethodHandles$Lookup".equals(constantPoolClassName) || !"java.lang.invoke.MethodHandles".equals(constantPoolClassName2)) {
                                if (this.classContainmentEntries == null) {
                                    this.classContainmentEntries = new ArrayList();
                                }
                                this.classContainmentEntries.add(new ClassContainment(constantPoolClassName, readUnsignedShort8, constantPoolClassName2));
                            }
                        }
                    }
                } else if (constantPoolStringEquals(readUnsignedShort2, "Signature")) {
                    this.typeSignatureStr = getConstantPoolString(this.reader.readUnsignedShort());
                } else if (constantPoolStringEquals(readUnsignedShort2, "SourceFile")) {
                    this.sourceFile = getConstantPoolString(this.reader.readUnsignedShort());
                } else if (constantPoolStringEquals(readUnsignedShort2, "EnclosingMethod")) {
                    String constantPoolClassName3 = getConstantPoolClassName(this.reader.readUnsignedShort());
                    int readUnsignedShort9 = this.reader.readUnsignedShort();
                    if (readUnsignedShort9 == 0) {
                        constantPoolString = MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME;
                    } else {
                        constantPoolString = getConstantPoolString(readUnsignedShort9, 0);
                    }
                    if (this.classContainmentEntries == null) {
                        this.classContainmentEntries = new ArrayList();
                    }
                    this.classContainmentEntries.add(new ClassContainment(this.className, this.classModifiers, constantPoolClassName3));
                    this.fullyQualifiedDefiningMethodName = constantPoolClassName3 + "." + constantPoolString;
                } else if (constantPoolStringEquals(readUnsignedShort2, "Module")) {
                    this.classpathElement.moduleNameFromModuleDescriptor = getConstantPoolString(this.reader.readUnsignedShort());
                    this.reader.skip(readInt - 2);
                }
                this.reader.skip(readInt);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Classfile(ClasspathElement classpathElement, List<ClasspathElement> list, Set<String> set, Set<String> set2, String str, Resource resource, boolean z, ConcurrentHashMap<String, String> concurrentHashMap, WorkQueue<Scanner.ClassfileScanWorkUnit> workQueue, ScanSpec scanSpec, LogNode logNode) {
        LogNode log;
        this.classpathElement = classpathElement;
        this.classpathOrder = list;
        this.relativePath = str;
        this.acceptedClassNamesFound = set;
        this.classNamesScheduledForExtendedScanning = set2;
        this.classfileResource = resource;
        this.isExternalClass = z;
        this.stringInternMap = concurrentHashMap;
        this.scanSpec = scanSpec;
        ClassfileReader openClassfile = resource.openClassfile();
        Throwable th = null;
        try {
            this.reader = openClassfile;
            if (this.reader.readInt() != -889275714) {
                throw new ClassfileFormatException("Classfile does not have correct magic number");
            }
            this.minorVersion = this.reader.readUnsignedShort();
            this.majorVersion = this.reader.readUnsignedShort();
            readConstantPoolEntries(logNode);
            readBasicClassInfo();
            readInterfaces();
            readFields();
            readMethods();
            readClassAttributes();
            this.reader = null;
            if (openClassfile != null) {
                if (0 != 0) {
                    try {
                        openClassfile.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    openClassfile.close();
                }
            }
            if (logNode == null) {
                log = null;
            } else {
                log = logNode.log("Found " + (this.isAnnotation ? "annotation class" : this.isInterface ? "interface class" : Attribute.CLASS_ATTR) + SequenceUtils.SPACE + this.className);
            }
            LogNode logNode2 = log;
            if (log != null) {
                if (this.superclassName != null) {
                    logNode2.log("Super" + ((!this.isInterface || this.isAnnotation) ? Attribute.CLASS_ATTR : "interface") + ": " + this.superclassName);
                }
                if (this.implementedInterfaces != null) {
                    logNode2.log("Interfaces: " + StringUtils.join(", ", this.implementedInterfaces));
                }
                if (this.classAnnotations != null) {
                    logNode2.log("Class annotations: " + StringUtils.join(", ", this.classAnnotations));
                }
                if (this.annotationParamDefaultValues != null) {
                    Iterator it = this.annotationParamDefaultValues.iterator();
                    while (it.hasNext()) {
                        logNode2.log("Annotation default param value: " + ((AnnotationParameterValue) it.next()));
                    }
                }
                if (this.fieldInfoList != null) {
                    Iterator it2 = this.fieldInfoList.iterator();
                    while (it2.hasNext()) {
                        FieldInfo fieldInfo = (FieldInfo) it2.next();
                        String modifiersStr = fieldInfo.getModifiersStr();
                        logNode2.log("Field: " + modifiersStr + (modifiersStr.isEmpty() ? "" : SequenceUtils.SPACE) + fieldInfo.getName());
                    }
                }
                if (this.methodInfoList != null) {
                    Iterator it3 = this.methodInfoList.iterator();
                    while (it3.hasNext()) {
                        MethodInfo methodInfo = (MethodInfo) it3.next();
                        String modifiersStr2 = methodInfo.getModifiersStr();
                        logNode2.log("Method: " + modifiersStr2 + (modifiersStr2.isEmpty() ? "" : SequenceUtils.SPACE) + methodInfo.getName());
                    }
                }
                if (this.typeSignatureStr != null) {
                    logNode2.log("Class type signature: " + this.typeSignatureStr);
                }
                if (this.refdClassNames != null) {
                    ArrayList arrayList = new ArrayList(this.refdClassNames);
                    CollectionUtils.sortIfNotEmpty(arrayList);
                    logNode2.log("Additional referenced class names: " + StringUtils.join(", ", arrayList));
                }
            }
            if (scanSpec.extendScanningUpwardsToExternalClasses) {
                extendScanningUpwards(logNode2);
                if (this.additionalWorkUnits != null) {
                    workQueue.addWorkUnits(this.additionalWorkUnits);
                }
            }
        } catch (Throwable th3) {
            if (openClassfile != null) {
                if (0 != 0) {
                    try {
                        openClassfile.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    openClassfile.close();
                }
            }
            throw th3;
        }
    }
}
