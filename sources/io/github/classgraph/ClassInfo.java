package io.github.classgraph;

import io.github.classgraph.Classfile;
import io.github.classgraph.FieldInfoList;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.description.method.MethodDescription;
import nonapi.io.github.classgraph.json.Id;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;
import nonapi.io.github.classgraph.types.TypeUtils;
import nonapi.io.github.classgraph.utils.Assert;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassInfo.class */
public class ClassInfo extends ScanResultObject implements HasName, Comparable<ClassInfo> {

    @Id
    protected String name;
    private int modifiers;
    private boolean isRecord;
    boolean isInherited;
    private int classfileMinorVersion;
    private int classfileMajorVersion;
    protected String typeSignatureStr;
    private transient ClassTypeSignature typeSignature;
    private transient ClassTypeSignature typeDescriptor;
    private String sourceFile;
    private String fullyQualifiedDefiningMethodName;
    protected boolean isExternalClass = true;
    protected boolean isScannedClass;
    transient ClasspathElement classpathElement;
    protected transient Resource classfileResource;
    transient ClassLoader classLoader;
    ModuleInfo moduleInfo;
    PackageInfo packageInfo;
    AnnotationInfoList annotationInfo;
    FieldInfoList fieldInfo;
    MethodInfoList methodInfo;
    AnnotationParameterValueList annotationDefaultParamValues;
    transient List<Classfile.ClassTypeAnnotationDecorator> typeAnnotationDecorators;
    private Set<String> referencedClassNames;
    private ClassInfoList referencedClasses;
    transient boolean annotationDefaultParamValuesHasBeenConvertedToPrimitive;
    private Map<RelType, Set<ClassInfo>> relatedClasses;
    private transient List<ClassInfo> overrideOrder;
    private transient List<ClassInfo> methodOverrideOrder;
    private static final int ANNOTATION_CLASS_MODIFIER = 8192;
    private static final ReachableAndDirectlyRelatedClasses NO_REACHABLE_CLASSES = new ReachableAndDirectlyRelatedClasses(Collections.emptySet(), Collections.emptySet());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassInfo$ClassType.class */
    public enum ClassType {
        ALL,
        STANDARD_CLASS,
        IMPLEMENTED_INTERFACE,
        ANNOTATION,
        INTERFACE_OR_ANNOTATION,
        ENUM,
        RECORD
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassInfo$RelType.class */
    public enum RelType {
        SUPERCLASSES,
        SUBCLASSES,
        CONTAINS_INNER_CLASS,
        CONTAINED_WITHIN_OUTER_CLASS,
        IMPLEMENTED_INTERFACES,
        CLASSES_IMPLEMENTING,
        CLASS_ANNOTATIONS,
        CLASSES_WITH_ANNOTATION,
        METHOD_ANNOTATIONS,
        CLASSES_WITH_METHOD_ANNOTATION,
        CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION,
        METHOD_PARAMETER_ANNOTATIONS,
        CLASSES_WITH_METHOD_PARAMETER_ANNOTATION,
        CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION,
        FIELD_ANNOTATIONS,
        CLASSES_WITH_FIELD_ANNOTATION,
        CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toStringWithSimpleNames() {
        return super.toStringWithSimpleNames();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClassInfo(String str, int i, Resource resource) {
        this.name = str;
        if (str.endsWith(";")) {
            throw new IllegalArgumentException("Bad class name");
        }
        setModifiers(i);
        this.classfileResource = resource;
        this.relatedClasses = new EnumMap(RelType.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addRelatedClass(RelType relType, ClassInfo classInfo) {
        Set<ClassInfo> set = this.relatedClasses.get(relType);
        Set<ClassInfo> set2 = set;
        if (set == null) {
            Map<RelType, Set<ClassInfo>> map = this.relatedClasses;
            LinkedHashSet linkedHashSet = new LinkedHashSet(4);
            set2 = linkedHashSet;
            map.put(relType, linkedHashSet);
        }
        return set2.add(classInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfo getOrCreateClassInfo(String str, Map<String, ClassInfo> map) {
        String str2;
        TypeSignature typeSignature;
        int i = 0;
        String str3 = str;
        while (true) {
            str2 = str3;
            if (!str2.endsWith("[]")) {
                break;
            }
            i++;
            str3 = str2.substring(0, str2.length() - 2);
        }
        while (str2.startsWith("[")) {
            i++;
            str2 = str2.substring(1);
        }
        if (str2.endsWith(";")) {
            String str4 = str2;
            str2 = str4.substring(str4.length() - 1);
        }
        String replace = str2.replace('/', '.');
        ClassInfo classInfo = map.get(str);
        ClassInfo classInfo2 = classInfo;
        if (classInfo == null) {
            if (i == 0) {
                classInfo2 = new ClassInfo(replace, 0, null);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i2 = 0; i2 < i; i2++) {
                    sb.append('[');
                }
                char typeChar = BaseTypeSignature.getTypeChar(replace);
                if (typeChar != 0) {
                    sb.append(typeChar);
                    typeSignature = new BaseTypeSignature(typeChar);
                } else {
                    String str5 = "L" + replace.replace('.', '/') + ";";
                    sb.append(str5);
                    try {
                        TypeSignature parse = ClassRefTypeSignature.parse(new Parser(str5), (String) null);
                        typeSignature = parse;
                        if (parse == null) {
                            throw new IllegalArgumentException("Could not form array base type signature for class " + replace);
                        }
                    } catch (ParseException unused) {
                        throw new IllegalArgumentException("Could not form array base type signature for class " + replace);
                    }
                }
                classInfo2 = new ArrayClassInfo(new ArrayTypeSignature(typeSignature, i, sb.toString()));
            }
            map.put(str, classInfo2);
        }
        return classInfo2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setClassfileVersion(int i, int i2) {
        this.classfileMinorVersion = i;
        this.classfileMajorVersion = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setModifiers(int i) {
        this.modifiers |= i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIsInterface(boolean z) {
        if (z) {
            this.modifiers |= 512;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIsAnnotation(boolean z) {
        if (z) {
            this.modifiers |= 8192;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIsRecord(boolean z) {
        if (z) {
            this.isRecord = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSourceFile(String str) {
        this.sourceFile = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTypeDecorators(List<Classfile.ClassTypeAnnotationDecorator> list) {
        if (this.typeAnnotationDecorators == null) {
            this.typeAnnotationDecorators = new ArrayList();
        }
        this.typeAnnotationDecorators.addAll(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addSuperclass(String str, Map<String, ClassInfo> map) {
        if (str != null && !str.equals("java.lang.Object")) {
            ClassInfo orCreateClassInfo = getOrCreateClassInfo(str, map);
            addRelatedClass(RelType.SUPERCLASSES, orCreateClassInfo);
            orCreateClassInfo.addRelatedClass(RelType.SUBCLASSES, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addImplementedInterface(String str, Map<String, ClassInfo> map) {
        ClassInfo orCreateClassInfo = getOrCreateClassInfo(str, map);
        orCreateClassInfo.setIsInterface(true);
        addRelatedClass(RelType.IMPLEMENTED_INTERFACES, orCreateClassInfo);
        orCreateClassInfo.addRelatedClass(RelType.CLASSES_IMPLEMENTING, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addClassContainment(List<Classfile.ClassContainment> list, Map<String, ClassInfo> map) {
        for (Classfile.ClassContainment classContainment : list) {
            ClassInfo orCreateClassInfo = getOrCreateClassInfo(classContainment.innerClassName, map);
            orCreateClassInfo.setModifiers(classContainment.innerClassModifierBits);
            ClassInfo orCreateClassInfo2 = getOrCreateClassInfo(classContainment.outerClassName, map);
            orCreateClassInfo.addRelatedClass(RelType.CONTAINED_WITHIN_OUTER_CLASS, orCreateClassInfo2);
            orCreateClassInfo2.addRelatedClass(RelType.CONTAINS_INNER_CLASS, orCreateClassInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFullyQualifiedDefiningMethodName(String str) {
        this.fullyQualifiedDefiningMethodName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addClassAnnotation(AnnotationInfo annotationInfo, Map<String, ClassInfo> map) {
        ClassInfo orCreateClassInfo = getOrCreateClassInfo(annotationInfo.getName(), map);
        orCreateClassInfo.setModifiers(8192);
        if (this.annotationInfo == null) {
            this.annotationInfo = new AnnotationInfoList(2);
        }
        this.annotationInfo.add(annotationInfo);
        addRelatedClass(RelType.CLASS_ANNOTATIONS, orCreateClassInfo);
        orCreateClassInfo.addRelatedClass(RelType.CLASSES_WITH_ANNOTATION, this);
        if (annotationInfo.getName().equals(Inherited.class.getName())) {
            this.isInherited = true;
        }
    }

    private void addFieldOrMethodAnnotationInfo(AnnotationInfoList annotationInfoList, boolean z, int i, Map<String, ClassInfo> map) {
        if (annotationInfoList != null) {
            Iterator it = annotationInfoList.iterator();
            while (it.hasNext()) {
                ClassInfo orCreateClassInfo = getOrCreateClassInfo(((AnnotationInfo) it.next()).getName(), map);
                orCreateClassInfo.setModifiers(8192);
                addRelatedClass(z ? RelType.FIELD_ANNOTATIONS : RelType.METHOD_ANNOTATIONS, orCreateClassInfo);
                orCreateClassInfo.addRelatedClass(z ? RelType.CLASSES_WITH_FIELD_ANNOTATION : RelType.CLASSES_WITH_METHOD_ANNOTATION, this);
                if (!Modifier.isPrivate(i)) {
                    orCreateClassInfo.addRelatedClass(z ? RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION : RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION, this);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFieldInfo(FieldInfoList fieldInfoList, Map<String, ClassInfo> map) {
        Iterator it = fieldInfoList.iterator();
        while (it.hasNext()) {
            FieldInfo fieldInfo = (FieldInfo) it.next();
            addFieldOrMethodAnnotationInfo(fieldInfo.annotationInfo, true, fieldInfo.getModifiers(), map);
        }
        if (this.fieldInfo == null) {
            this.fieldInfo = fieldInfoList;
        } else {
            this.fieldInfo.addAll(fieldInfoList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addMethodInfo(MethodInfoList methodInfoList, Map<String, ClassInfo> map) {
        Iterator it = methodInfoList.iterator();
        while (it.hasNext()) {
            MethodInfo methodInfo = (MethodInfo) it.next();
            addFieldOrMethodAnnotationInfo(methodInfo.annotationInfo, false, methodInfo.getModifiers(), map);
            if (methodInfo.parameterAnnotationInfo != null) {
                for (int i = 0; i < methodInfo.parameterAnnotationInfo.length; i++) {
                    AnnotationInfo[] annotationInfoArr = methodInfo.parameterAnnotationInfo[i];
                    if (annotationInfoArr != null) {
                        for (AnnotationInfo annotationInfo : annotationInfoArr) {
                            ClassInfo orCreateClassInfo = getOrCreateClassInfo(annotationInfo.getName(), map);
                            orCreateClassInfo.setModifiers(8192);
                            addRelatedClass(RelType.METHOD_PARAMETER_ANNOTATIONS, orCreateClassInfo);
                            orCreateClassInfo.addRelatedClass(RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION, this);
                            if (!Modifier.isPrivate(methodInfo.getModifiers())) {
                                orCreateClassInfo.addRelatedClass(RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION, this);
                            }
                        }
                    }
                }
            }
        }
        if (this.methodInfo == null) {
            this.methodInfo = methodInfoList;
        } else {
            this.methodInfo.addAll(methodInfoList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTypeSignature(String str) {
        this.typeSignatureStr = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAnnotationParamDefaultValues(AnnotationParameterValueList annotationParameterValueList) {
        setIsAnnotation(true);
        if (this.annotationDefaultParamValues == null) {
            this.annotationDefaultParamValues = annotationParameterValueList;
        } else {
            this.annotationDefaultParamValues.addAll(annotationParameterValueList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfo addScannedClass(String str, int i, boolean z, Map<String, ClassInfo> map, ClasspathElement classpathElement, Resource resource) {
        ClassInfo classInfo = map.get(str);
        ClassInfo classInfo2 = classInfo;
        if (classInfo == null) {
            ClassInfo classInfo3 = new ClassInfo(str, i, resource);
            classInfo2 = classInfo3;
            map.put(str, classInfo3);
        } else {
            if (classInfo2.isScannedClass) {
                throw new IllegalArgumentException("Class " + str + " should not have been encountered more than once due to classpath masking -- please report this bug at: https://github.com/classgraph/classgraph/issues");
            }
            classInfo2.classfileResource = resource;
            classInfo2.modifiers |= i;
        }
        classInfo2.isScannedClass = true;
        classInfo2.isExternalClass = z;
        classInfo2.classpathElement = classpathElement;
        classInfo2.classLoader = classpathElement.getClassLoader();
        return classInfo2;
    }

    private static Set<ClassInfo> filterClassInfo(Collection<ClassInfo> collection, ScanSpec scanSpec, boolean z, ClassType... classTypeArr) {
        if (collection == null) {
            return Collections.emptySet();
        }
        boolean z2 = classTypeArr.length == 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        for (ClassType classType : classTypeArr) {
            switch (classType) {
                case ALL:
                    z2 = true;
                    break;
                case STANDARD_CLASS:
                    z3 = true;
                    break;
                case IMPLEMENTED_INTERFACE:
                    z4 = true;
                    break;
                case ANNOTATION:
                    z5 = true;
                    break;
                case INTERFACE_OR_ANNOTATION:
                    z5 = true;
                    z4 = true;
                    break;
                case ENUM:
                    z6 = true;
                    break;
                case RECORD:
                    z7 = true;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown ClassType: " + classType);
            }
        }
        if (z3 && z4 && z5) {
            z2 = true;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection.size());
        for (ClassInfo classInfo : collection) {
            boolean z8 = z2 || (z3 && classInfo.isStandardClass()) || ((z4 && classInfo.isImplementedInterface()) || ((z5 && classInfo.isAnnotation()) || ((z6 && classInfo.isEnum()) || (z7 && classInfo.isRecord()))));
            boolean z9 = (classInfo.isExternalClass && !scanSpec.enableExternalClasses && z) ? false : true;
            if (z8 && z9 && !scanSpec.classOrPackageIsRejected(classInfo.name)) {
                linkedHashSet.add(classInfo);
            }
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassInfo$ReachableAndDirectlyRelatedClasses.class */
    public static class ReachableAndDirectlyRelatedClasses {
        final Set<ClassInfo> reachableClasses;
        final Set<ClassInfo> directlyRelatedClasses;

        private ReachableAndDirectlyRelatedClasses(Set<ClassInfo> set, Set<ClassInfo> set2) {
            this.reachableClasses = set;
            this.directlyRelatedClasses = set2;
        }
    }

    private ReachableAndDirectlyRelatedClasses filterClassInfo(RelType relType, boolean z, ClassType... classTypeArr) {
        Set<ClassInfo> set = this.relatedClasses.get(relType);
        if (set != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(set);
            LinkedHashSet<ClassInfo> linkedHashSet2 = new LinkedHashSet(linkedHashSet);
            if (relType == RelType.METHOD_ANNOTATIONS || relType == RelType.METHOD_PARAMETER_ANNOTATIONS || relType == RelType.FIELD_ANNOTATIONS) {
                Iterator it = linkedHashSet.iterator();
                while (it.hasNext()) {
                    linkedHashSet2.addAll(((ClassInfo) it.next()).filterClassInfo(RelType.CLASS_ANNOTATIONS, z, new ClassType[0]).reachableClasses);
                }
            } else if (relType == RelType.CLASSES_WITH_METHOD_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION || relType == RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION || relType == RelType.CLASSES_WITH_FIELD_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION) {
                Iterator<ClassInfo> it2 = filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, z, ClassType.ANNOTATION).reachableClasses.iterator();
                while (it2.hasNext()) {
                    Set<ClassInfo> set2 = it2.next().relatedClasses.get(relType);
                    if (set2 != null) {
                        linkedHashSet2.addAll(set2);
                    }
                }
            } else {
                LinkedList linkedList = new LinkedList(linkedHashSet);
                while (!linkedList.isEmpty()) {
                    Set<ClassInfo> set3 = ((ClassInfo) linkedList.removeFirst()).relatedClasses.get(relType);
                    if (set3 != null) {
                        for (ClassInfo classInfo : set3) {
                            if (linkedHashSet2.add(classInfo)) {
                                linkedList.add(classInfo);
                            }
                        }
                    }
                }
            }
            if (linkedHashSet2.isEmpty()) {
                return NO_REACHABLE_CLASSES;
            }
            if (relType == RelType.CLASS_ANNOTATIONS || relType == RelType.METHOD_ANNOTATIONS || relType == RelType.METHOD_PARAMETER_ANNOTATIONS || relType == RelType.FIELD_ANNOTATIONS) {
                LinkedHashSet linkedHashSet3 = null;
                for (ClassInfo classInfo2 : linkedHashSet2) {
                    if (classInfo2.getName().startsWith("java.lang.annotation.") && !linkedHashSet.contains(classInfo2)) {
                        if (linkedHashSet3 == null) {
                            linkedHashSet3 = new LinkedHashSet();
                        }
                        linkedHashSet3.add(classInfo2);
                    }
                }
                if (linkedHashSet3 != null) {
                    linkedHashSet2.removeAll(linkedHashSet3);
                }
            }
            return new ReachableAndDirectlyRelatedClasses(filterClassInfo(linkedHashSet2, this.scanResult.scanSpec, z, classTypeArr), filterClassInfo(linkedHashSet, this.scanResult.scanSpec, z, classTypeArr));
        }
        return NO_REACHABLE_CLASSES;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfoList getAllClasses(Collection<ClassInfo> collection, ScanSpec scanSpec) {
        return new ClassInfoList(filterClassInfo(collection, scanSpec, true, ClassType.ALL), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfoList getAllEnums(Collection<ClassInfo> collection, ScanSpec scanSpec) {
        return new ClassInfoList(filterClassInfo(collection, scanSpec, true, ClassType.ENUM), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfoList getAllRecords(Collection<ClassInfo> collection, ScanSpec scanSpec) {
        return new ClassInfoList(filterClassInfo(collection, scanSpec, true, ClassType.RECORD), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfoList getAllStandardClasses(Collection<ClassInfo> collection, ScanSpec scanSpec) {
        return new ClassInfoList(filterClassInfo(collection, scanSpec, true, ClassType.STANDARD_CLASS), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfoList getAllImplementedInterfaceClasses(Collection<ClassInfo> collection, ScanSpec scanSpec) {
        return new ClassInfoList(filterClassInfo(collection, scanSpec, true, ClassType.IMPLEMENTED_INTERFACE), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfoList getAllAnnotationClasses(Collection<ClassInfo> collection, ScanSpec scanSpec) {
        return new ClassInfoList(filterClassInfo(collection, scanSpec, true, ClassType.ANNOTATION), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassInfoList getAllInterfacesOrAnnotationClasses(Collection<ClassInfo> collection, ScanSpec scanSpec) {
        return new ClassInfoList(filterClassInfo(collection, scanSpec, true, ClassType.INTERFACE_OR_ANNOTATION), true);
    }

    @Override // io.github.classgraph.HasName
    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSimpleName(String str) {
        return str.substring(Math.max(str.lastIndexOf(46), str.lastIndexOf(36)) + 1);
    }

    public String getSimpleName() {
        return getSimpleName(this.name);
    }

    public ModuleInfo getModuleInfo() {
        return this.moduleInfo;
    }

    public PackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    public String getPackageName() {
        return PackageInfo.getParentPackageName(this.name);
    }

    public boolean isExternalClass() {
        return this.isExternalClass;
    }

    public int getClassfileMinorVersion() {
        return this.classfileMinorVersion;
    }

    public int getClassfileMajorVersion() {
        return this.classfileMajorVersion;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public String getModifiersStr() {
        StringBuilder sb = new StringBuilder();
        TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.CLASS, false, sb);
        return sb.toString();
    }

    public boolean isPublic() {
        return Modifier.isPublic(this.modifiers);
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(this.modifiers);
    }

    public boolean isProtected() {
        return Modifier.isProtected(this.modifiers);
    }

    public boolean isPackageVisible() {
        return (isPublic() || isPrivate() || isProtected()) ? false : true;
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this.modifiers);
    }

    public boolean isSynthetic() {
        return (this.modifiers & 4096) != 0;
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.modifiers);
    }

    public boolean isStatic() {
        return Modifier.isStatic(this.modifiers);
    }

    public boolean isAnnotation() {
        return (this.modifiers & 8192) != 0;
    }

    public boolean isInterface() {
        return isInterfaceOrAnnotation() && !isAnnotation();
    }

    public boolean isInterfaceOrAnnotation() {
        return (this.modifiers & 512) != 0;
    }

    public boolean isEnum() {
        return (this.modifiers & 16384) != 0;
    }

    public boolean isRecord() {
        return this.isRecord;
    }

    public boolean isStandardClass() {
        return (isAnnotation() || isInterface()) ? false : true;
    }

    public boolean isArrayClass() {
        return this instanceof ArrayClassInfo;
    }

    public boolean extendsSuperclass(Class<?> cls) {
        return extendsSuperclass(cls.getName());
    }

    public boolean extendsSuperclass(String str) {
        return (str.equals("java.lang.Object") && isStandardClass()) || getSuperclasses().containsName(str);
    }

    public boolean isInnerClass() {
        return !getOuterClasses().isEmpty();
    }

    public boolean isOuterClass() {
        return !getInnerClasses().isEmpty();
    }

    public boolean isAnonymousInnerClass() {
        return this.fullyQualifiedDefiningMethodName != null;
    }

    public boolean isImplementedInterface() {
        return this.relatedClasses.get(RelType.CLASSES_IMPLEMENTING) != null || isInterface();
    }

    public boolean implementsInterface(Class<?> cls) {
        Assert.isInterface(cls);
        return implementsInterface(cls.getName());
    }

    public boolean implementsInterface(String str) {
        return getInterfaces().containsName(str);
    }

    public boolean hasAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasAnnotation(cls.getName());
    }

    public boolean hasAnnotation(String str) {
        return getAnnotations().containsName(str);
    }

    public boolean hasDeclaredField(String str) {
        return getDeclaredFieldInfo().containsName(str);
    }

    public boolean hasField(String str) {
        Iterator<ClassInfo> it = getFieldOverrideOrder().iterator();
        while (it.hasNext()) {
            if (it.next().hasDeclaredField(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeclaredFieldAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasDeclaredFieldAnnotation(cls.getName());
    }

    public boolean hasDeclaredFieldAnnotation(String str) {
        Iterator it = getDeclaredFieldInfo().iterator();
        while (it.hasNext()) {
            if (((FieldInfo) it.next()).hasAnnotation(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFieldAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasFieldAnnotation(cls.getName());
    }

    public boolean hasFieldAnnotation(String str) {
        Iterator<ClassInfo> it = getFieldOverrideOrder().iterator();
        while (it.hasNext()) {
            if (it.next().hasDeclaredFieldAnnotation(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeclaredMethod(String str) {
        return getDeclaredMethodInfo().containsName(str);
    }

    public boolean hasMethod(String str) {
        Iterator<ClassInfo> it = getMethodOverrideOrder().iterator();
        while (it.hasNext()) {
            if (it.next().hasDeclaredMethod(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeclaredMethodAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasDeclaredMethodAnnotation(cls.getName());
    }

    public boolean hasDeclaredMethodAnnotation(String str) {
        Iterator it = getDeclaredMethodInfo().iterator();
        while (it.hasNext()) {
            if (((MethodInfo) it.next()).hasAnnotation(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMethodAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasMethodAnnotation(cls.getName());
    }

    public boolean hasMethodAnnotation(String str) {
        Iterator<ClassInfo> it = getMethodOverrideOrder().iterator();
        while (it.hasNext()) {
            if (it.next().hasDeclaredMethodAnnotation(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeclaredMethodParameterAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasDeclaredMethodParameterAnnotation(cls.getName());
    }

    public boolean hasDeclaredMethodParameterAnnotation(String str) {
        Iterator it = getDeclaredMethodInfo().iterator();
        while (it.hasNext()) {
            if (((MethodInfo) it.next()).hasParameterAnnotation(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMethodParameterAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasMethodParameterAnnotation(cls.getName());
    }

    public boolean hasMethodParameterAnnotation(String str) {
        Iterator<ClassInfo> it = getMethodOverrideOrder().iterator();
        while (it.hasNext()) {
            if (it.next().hasDeclaredMethodParameterAnnotation(str)) {
                return true;
            }
        }
        return false;
    }

    private List<ClassInfo> getFieldOverrideOrder(Set<ClassInfo> set, List<ClassInfo> list) {
        if (set.add(this)) {
            list.add(this);
            Iterator it = getInterfaces().iterator();
            while (it.hasNext()) {
                ((ClassInfo) it.next()).getFieldOverrideOrder(set, list);
            }
            ClassInfo superclass = getSuperclass();
            if (superclass != null) {
                superclass.getFieldOverrideOrder(set, list);
            }
        }
        return list;
    }

    private List<ClassInfo> getFieldOverrideOrder() {
        if (this.overrideOrder == null) {
            this.overrideOrder = getFieldOverrideOrder(new HashSet(), new ArrayList());
        }
        return this.overrideOrder;
    }

    private List<ClassInfo> getMethodOverrideOrder(Set<ClassInfo> set, List<ClassInfo> list) {
        if (!set.add(this)) {
            return list;
        }
        if (!isInterfaceOrAnnotation()) {
            list.add(this);
            ClassInfo superclass = getSuperclass();
            if (superclass != null) {
                superclass.getMethodOverrideOrder(set, list);
            }
            Iterator it = getInterfaces().iterator();
            while (it.hasNext()) {
                ((ClassInfo) it.next()).getMethodOverrideOrder(set, list);
            }
            return list;
        }
        ClassInfoList interfaces = getInterfaces();
        int i = Integer.MAX_VALUE;
        Iterator it2 = interfaces.iterator();
        while (it2.hasNext()) {
            ClassInfo classInfo = (ClassInfo) it2.next();
            if (set.contains(classInfo)) {
                int indexOf = list.indexOf(classInfo);
                i = (indexOf < 0 || indexOf >= i) ? i : indexOf;
            }
        }
        if (i == Integer.MAX_VALUE) {
            list.add(this);
        } else {
            list.add(i, this);
        }
        Iterator it3 = interfaces.iterator();
        while (it3.hasNext()) {
            ((ClassInfo) it3.next()).getMethodOverrideOrder(set, list);
        }
        return list;
    }

    private List<ClassInfo> getMethodOverrideOrder() {
        if (this.methodOverrideOrder == null) {
            this.methodOverrideOrder = getMethodOverrideOrder(new HashSet(), new ArrayList());
        }
        return this.methodOverrideOrder;
    }

    public ClassInfoList getSubclasses() {
        if (getName().equals("java.lang.Object")) {
            return this.scanResult.getAllStandardClasses();
        }
        return new ClassInfoList(filterClassInfo(RelType.SUBCLASSES, !this.isExternalClass, new ClassType[0]), true);
    }

    public ClassInfoList getSuperclasses() {
        return new ClassInfoList(filterClassInfo(RelType.SUPERCLASSES, false, new ClassType[0]), false);
    }

    public ClassInfo getSuperclass() {
        Set<ClassInfo> set = this.relatedClasses.get(RelType.SUPERCLASSES);
        if (set == null || set.isEmpty()) {
            return null;
        }
        if (set.size() > 2) {
            throw new IllegalArgumentException("More than one superclass: " + set);
        }
        ClassInfo next = set.iterator().next();
        if (next.getName().equals("java.lang.Object")) {
            return null;
        }
        return next;
    }

    public ClassInfoList getOuterClasses() {
        return new ClassInfoList(filterClassInfo(RelType.CONTAINED_WITHIN_OUTER_CLASS, false, new ClassType[0]), false);
    }

    public ClassInfoList getInnerClasses() {
        return new ClassInfoList(filterClassInfo(RelType.CONTAINS_INNER_CLASS, false, new ClassType[0]), true);
    }

    public String getFullyQualifiedDefiningMethodName() {
        return this.fullyQualifiedDefiningMethodName;
    }

    public ClassInfoList getInterfaces() {
        ReachableAndDirectlyRelatedClasses filterClassInfo = filterClassInfo(RelType.IMPLEMENTED_INTERFACES, false, new ClassType[0]);
        LinkedHashSet linkedHashSet = new LinkedHashSet(filterClassInfo.reachableClasses);
        Iterator<ClassInfo> it = filterClassInfo(RelType.SUPERCLASSES, false, new ClassType[0]).reachableClasses.iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(it.next().filterClassInfo(RelType.IMPLEMENTED_INTERFACES, false, new ClassType[0]).reachableClasses);
        }
        return new ClassInfoList(linkedHashSet, filterClassInfo.directlyRelatedClasses, false);
    }

    public ClassInfoList getClassesImplementing() {
        ReachableAndDirectlyRelatedClasses filterClassInfo = filterClassInfo(RelType.CLASSES_IMPLEMENTING, !this.isExternalClass, new ClassType[0]);
        LinkedHashSet linkedHashSet = new LinkedHashSet(filterClassInfo.reachableClasses);
        for (ClassInfo classInfo : filterClassInfo.reachableClasses) {
            linkedHashSet.addAll(classInfo.filterClassInfo(RelType.SUBCLASSES, !classInfo.isExternalClass, new ClassType[0]).reachableClasses);
        }
        return new ClassInfoList(linkedHashSet, filterClassInfo.directlyRelatedClasses, true);
    }

    public ClassInfoList getAnnotations() {
        if (!this.scanResult.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
        }
        ReachableAndDirectlyRelatedClasses filterClassInfo = filterClassInfo(RelType.CLASS_ANNOTATIONS, false, new ClassType[0]);
        LinkedHashSet linkedHashSet = null;
        Iterator it = getSuperclasses().iterator();
        while (it.hasNext()) {
            for (ClassInfo classInfo : ((ClassInfo) it.next()).filterClassInfo(RelType.CLASS_ANNOTATIONS, false, new ClassType[0]).reachableClasses) {
                if (classInfo != null && classInfo.isInherited) {
                    if (linkedHashSet == null) {
                        linkedHashSet = new LinkedHashSet();
                    }
                    linkedHashSet.add(classInfo);
                }
            }
        }
        if (linkedHashSet == null) {
            return new ClassInfoList(filterClassInfo, true);
        }
        linkedHashSet.addAll(filterClassInfo.reachableClasses);
        return new ClassInfoList(linkedHashSet, filterClassInfo.directlyRelatedClasses, true);
    }

    private ClassInfoList getFieldOrMethodAnnotations(RelType relType) {
        boolean z = relType == RelType.FIELD_ANNOTATIONS;
        boolean z2 = z;
        if (!z ? this.scanResult.scanSpec.enableMethodInfo : this.scanResult.scanSpec.enableFieldInfo) {
            if (this.scanResult.scanSpec.enableAnnotationInfo) {
                ReachableAndDirectlyRelatedClasses filterClassInfo = filterClassInfo(relType, false, ClassType.ANNOTATION);
                return new ClassInfoList(new LinkedHashSet(filterClassInfo.reachableClasses), filterClassInfo.directlyRelatedClasses, true);
            }
        }
        throw new IllegalArgumentException("Please call ClassGraph#enable" + (z2 ? "Field" : "Method") + "Info() and #enableAnnotationInfo() before #scan()");
    }

    private ClassInfoList getClassesWithFieldOrMethodAnnotation(RelType relType) {
        boolean z = relType == RelType.CLASSES_WITH_FIELD_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION;
        boolean z2 = z;
        if (!z ? this.scanResult.scanSpec.enableMethodInfo : this.scanResult.scanSpec.enableFieldInfo) {
            if (this.scanResult.scanSpec.enableAnnotationInfo) {
                ReachableAndDirectlyRelatedClasses filterClassInfo = filterClassInfo(relType, !this.isExternalClass, new ClassType[0]);
                ReachableAndDirectlyRelatedClasses filterClassInfo2 = filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, !this.isExternalClass, ClassType.ANNOTATION);
                if (filterClassInfo2.reachableClasses.isEmpty()) {
                    return new ClassInfoList(filterClassInfo, true);
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet(filterClassInfo.reachableClasses);
                for (ClassInfo classInfo : filterClassInfo2.reachableClasses) {
                    linkedHashSet.addAll(classInfo.filterClassInfo(relType, !classInfo.isExternalClass, new ClassType[0]).reachableClasses);
                }
                return new ClassInfoList(linkedHashSet, filterClassInfo.directlyRelatedClasses, true);
            }
        }
        throw new IllegalArgumentException("Please call ClassGraph#enable" + (z2 ? "Field" : "Method") + "Info() and #enableAnnotationInfo() before #scan()");
    }

    public AnnotationInfoList getAnnotationInfo() {
        if (!this.scanResult.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
        }
        return AnnotationInfoList.getIndirectAnnotations(this.annotationInfo, this);
    }

    public AnnotationInfo getAnnotationInfo(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getAnnotationInfo(cls.getName());
    }

    public AnnotationInfo getAnnotationInfo(String str) {
        return getAnnotationInfo().get(str);
    }

    public AnnotationInfoList getAnnotationInfoRepeatable(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getAnnotationInfoRepeatable(cls.getName());
    }

    public AnnotationInfoList getAnnotationInfoRepeatable(String str) {
        return getAnnotationInfo().getRepeatable(str);
    }

    public AnnotationParameterValueList getAnnotationDefaultParameterValues() {
        if (!this.scanResult.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
        }
        if (!isAnnotation()) {
            throw new IllegalArgumentException("Class is not an annotation: " + getName());
        }
        if (this.annotationDefaultParamValues == null) {
            return AnnotationParameterValueList.EMPTY_LIST;
        }
        if (!this.annotationDefaultParamValuesHasBeenConvertedToPrimitive) {
            this.annotationDefaultParamValues.convertWrapperArraysToPrimitiveArrays(this);
            this.annotationDefaultParamValuesHasBeenConvertedToPrimitive = true;
        }
        return this.annotationDefaultParamValues;
    }

    public ClassInfoList getClassesWithAnnotation() {
        if (!this.scanResult.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
        }
        ReachableAndDirectlyRelatedClasses filterClassInfo = filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, !this.isExternalClass, new ClassType[0]);
        if (this.isInherited) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(filterClassInfo.reachableClasses);
            Iterator<ClassInfo> it = filterClassInfo.reachableClasses.iterator();
            while (it.hasNext()) {
                linkedHashSet.addAll(it.next().getSubclasses());
            }
            return new ClassInfoList(linkedHashSet, filterClassInfo.directlyRelatedClasses, true);
        }
        return new ClassInfoList(filterClassInfo, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfoList getClassesWithAnnotationDirectOnly() {
        return new ClassInfoList(filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
    }

    private MethodInfoList getDeclaredMethodInfo(String str, boolean z, boolean z2, boolean z3) {
        if (!this.scanResult.scanSpec.enableMethodInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableMethodInfo() before #scan()");
        }
        if (this.methodInfo == null) {
            return MethodInfoList.EMPTY_LIST;
        }
        if (str == null) {
            MethodInfoList methodInfoList = new MethodInfoList();
            Iterator it = this.methodInfo.iterator();
            while (it.hasNext()) {
                MethodInfo methodInfo = (MethodInfo) it.next();
                String name = methodInfo.getName();
                boolean equals = MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(name);
                boolean equals2 = MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(name);
                if ((equals && z2) || ((equals2 && z3) || (!equals && !equals2 && z))) {
                    methodInfoList.add(methodInfo);
                }
            }
            return methodInfoList;
        }
        boolean z4 = false;
        Iterator it2 = this.methodInfo.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            if (((MethodInfo) it2.next()).getName().equals(str)) {
                z4 = true;
                break;
            }
        }
        if (!z4) {
            return MethodInfoList.EMPTY_LIST;
        }
        MethodInfoList methodInfoList2 = new MethodInfoList();
        Iterator it3 = this.methodInfo.iterator();
        while (it3.hasNext()) {
            MethodInfo methodInfo2 = (MethodInfo) it3.next();
            if (methodInfo2.getName().equals(str)) {
                methodInfoList2.add(methodInfo2);
            }
        }
        return methodInfoList2;
    }

    private MethodInfoList getMethodInfo(String str, boolean z, boolean z2, boolean z3) {
        if (!this.scanResult.scanSpec.enableMethodInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableMethodInfo() before #scan()");
        }
        MethodInfoList methodInfoList = new MethodInfoList();
        HashSet hashSet = new HashSet();
        Iterator<ClassInfo> it = getMethodOverrideOrder().iterator();
        while (it.hasNext()) {
            Iterator it2 = it.next().getDeclaredMethodInfo(str, z, z2, z3).iterator();
            while (it2.hasNext()) {
                MethodInfo methodInfo = (MethodInfo) it2.next();
                if (hashSet.add(new AbstractMap.SimpleEntry(methodInfo.getName(), methodInfo.getTypeDescriptorStr()))) {
                    methodInfoList.add(methodInfo);
                }
            }
        }
        return methodInfoList;
    }

    public MethodInfoList getDeclaredMethodInfo() {
        return getDeclaredMethodInfo(null, true, false, false);
    }

    public MethodInfoList getMethodInfo() {
        return getMethodInfo(null, true, false, false);
    }

    public MethodInfoList getDeclaredConstructorInfo() {
        return getDeclaredMethodInfo(null, false, true, false);
    }

    public MethodInfoList getConstructorInfo() {
        return getMethodInfo(null, false, true, false);
    }

    public MethodInfoList getDeclaredMethodAndConstructorInfo() {
        return getDeclaredMethodInfo(null, true, true, false);
    }

    public MethodInfoList getMethodAndConstructorInfo() {
        return getMethodInfo(null, true, true, false);
    }

    public MethodInfoList getDeclaredMethodInfo(String str) {
        return getDeclaredMethodInfo(str, false, false, false);
    }

    public MethodInfoList getMethodInfo(String str) {
        return getMethodInfo(str, false, false, false);
    }

    public ClassInfoList getMethodAnnotations() {
        return getFieldOrMethodAnnotations(RelType.METHOD_ANNOTATIONS);
    }

    public ClassInfoList getMethodParameterAnnotations() {
        return getFieldOrMethodAnnotations(RelType.METHOD_PARAMETER_ANNOTATIONS);
    }

    public ClassInfoList getClassesWithMethodAnnotation() {
        HashSet hashSet = new HashSet(getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_METHOD_ANNOTATION));
        Iterator it = getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION).iterator();
        while (it.hasNext()) {
            hashSet.addAll(((ClassInfo) it.next()).getSubclasses());
        }
        return new ClassInfoList(hashSet, new HashSet(getClassesWithMethodAnnotationDirectOnly()), true);
    }

    public ClassInfoList getClassesWithMethodParameterAnnotation() {
        HashSet hashSet = new HashSet(getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION));
        Iterator it = getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION).iterator();
        while (it.hasNext()) {
            hashSet.addAll(((ClassInfo) it.next()).getSubclasses());
        }
        return new ClassInfoList(hashSet, new HashSet(getClassesWithMethodParameterAnnotationDirectOnly()), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfoList getClassesWithMethodAnnotationDirectOnly() {
        return new ClassInfoList(filterClassInfo(RelType.CLASSES_WITH_METHOD_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
    }

    ClassInfoList getClassesWithMethodParameterAnnotationDirectOnly() {
        return new ClassInfoList(filterClassInfo(RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
    }

    public FieldInfoList getDeclaredFieldInfo() {
        if (this.scanResult.scanSpec.enableFieldInfo) {
            return this.fieldInfo == null ? FieldInfoList.EMPTY_LIST : this.fieldInfo;
        }
        throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
    }

    public FieldInfoList getFieldInfo() {
        if (!this.scanResult.scanSpec.enableFieldInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
        }
        FieldInfoList fieldInfoList = new FieldInfoList();
        HashSet hashSet = new HashSet();
        Iterator<ClassInfo> it = getFieldOverrideOrder().iterator();
        while (it.hasNext()) {
            Iterator it2 = it.next().getDeclaredFieldInfo().iterator();
            while (it2.hasNext()) {
                FieldInfo fieldInfo = (FieldInfo) it2.next();
                if (hashSet.add(fieldInfo.getName())) {
                    fieldInfoList.add(fieldInfo);
                }
            }
        }
        return fieldInfoList;
    }

    public FieldInfoList getEnumConstants() {
        if (!isEnum()) {
            throw new IllegalArgumentException("Class " + getName() + " is not an enum");
        }
        return getFieldInfo().filter(new FieldInfoList.FieldInfoFilter() { // from class: io.github.classgraph.ClassInfo.1
            @Override // io.github.classgraph.FieldInfoList.FieldInfoFilter
            public boolean accept(FieldInfo fieldInfo) {
                return fieldInfo.isEnum();
            }
        });
    }

    public List<Object> getEnumConstantObjects() {
        if (!isEnum()) {
            throw new IllegalArgumentException("Class " + getName() + " is not an enum");
        }
        Class<?> loadClass = loadClass();
        FieldInfoList enumConstants = getEnumConstants();
        ArrayList arrayList = new ArrayList(enumConstants.size());
        ReflectionUtils reflectionUtils = this.scanResult == null ? new ReflectionUtils() : this.scanResult.reflectionUtils;
        Iterator it = enumConstants.iterator();
        while (it.hasNext()) {
            Object staticFieldVal = reflectionUtils.getStaticFieldVal(true, loadClass, ((FieldInfo) it.next()).getName());
            if (staticFieldVal == null) {
                throw new IllegalArgumentException("Could not read enum constant objects");
            }
            arrayList.add(staticFieldVal);
        }
        return arrayList;
    }

    public FieldInfo getDeclaredFieldInfo(String str) {
        if (!this.scanResult.scanSpec.enableFieldInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
        }
        if (this.fieldInfo == null) {
            return null;
        }
        Iterator it = this.fieldInfo.iterator();
        while (it.hasNext()) {
            FieldInfo fieldInfo = (FieldInfo) it.next();
            if (fieldInfo.getName().equals(str)) {
                return fieldInfo;
            }
        }
        return null;
    }

    public FieldInfo getFieldInfo(String str) {
        if (!this.scanResult.scanSpec.enableFieldInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
        }
        Iterator<ClassInfo> it = getFieldOverrideOrder().iterator();
        while (it.hasNext()) {
            FieldInfo declaredFieldInfo = it.next().getDeclaredFieldInfo(str);
            if (declaredFieldInfo != null) {
                return declaredFieldInfo;
            }
        }
        return null;
    }

    public ClassInfoList getFieldAnnotations() {
        return getFieldOrMethodAnnotations(RelType.FIELD_ANNOTATIONS);
    }

    public ClassInfoList getClassesWithFieldAnnotation() {
        HashSet hashSet = new HashSet(getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_FIELD_ANNOTATION));
        Iterator it = getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION).iterator();
        while (it.hasNext()) {
            hashSet.addAll(((ClassInfo) it.next()).getSubclasses());
        }
        return new ClassInfoList(hashSet, new HashSet(getClassesWithMethodAnnotationDirectOnly()), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfoList getClassesWithFieldAnnotationDirectOnly() {
        return new ClassInfoList(filterClassInfo(RelType.CLASSES_WITH_FIELD_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
    }

    public ClassTypeSignature getTypeSignature() {
        if (this.typeSignatureStr == null) {
            return null;
        }
        if (this.typeSignature == null) {
            try {
                this.typeSignature = ClassTypeSignature.parse(this.typeSignatureStr, this);
                this.typeSignature.setScanResult(this.scanResult);
                if (this.typeAnnotationDecorators != null) {
                    Iterator<Classfile.ClassTypeAnnotationDecorator> it = this.typeAnnotationDecorators.iterator();
                    while (it.hasNext()) {
                        it.next().decorate(this.typeSignature);
                    }
                }
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid type signature for class " + getName() + " in classpath element " + getClasspathElementURI() + " : " + this.typeSignatureStr, e);
            }
        }
        return this.typeSignature;
    }

    public String getTypeSignatureStr() {
        return this.typeSignatureStr;
    }

    public ClassTypeSignature getTypeSignatureOrTypeDescriptor() {
        try {
            ClassTypeSignature typeSignature = getTypeSignature();
            if (typeSignature != null) {
                return typeSignature;
            }
        } catch (Exception unused) {
        }
        return getTypeDescriptor();
    }

    public ClassTypeSignature getTypeDescriptor() {
        if (this.typeDescriptor == null) {
            this.typeDescriptor = new ClassTypeSignature(this, getSuperclass(), getInterfaces());
            this.typeDescriptor.setScanResult(this.scanResult);
            if (this.typeAnnotationDecorators != null) {
                Iterator<Classfile.ClassTypeAnnotationDecorator> it = this.typeAnnotationDecorators.iterator();
                while (it.hasNext()) {
                    it.next().decorate(this.typeDescriptor);
                }
            }
        }
        return this.typeDescriptor;
    }

    public String getSourceFile() {
        return this.sourceFile;
    }

    public URI getClasspathElementURI() {
        return this.classfileResource.getClasspathElementURI();
    }

    public URL getClasspathElementURL() {
        try {
            return getClasspathElementURI().toURL();
        } catch (IllegalArgumentException | MalformedURLException e) {
            throw new IllegalArgumentException("Could not get classpath element URL", e);
        }
    }

    public File getClasspathElementFile() {
        if (this.classpathElement == null) {
            throw new IllegalArgumentException("Classpath element is not known for this classpath element");
        }
        return this.classpathElement.getFile();
    }

    public ModuleRef getModuleRef() {
        if (this.classpathElement == null) {
            throw new IllegalArgumentException("Classpath element is not known for this classpath element");
        }
        if (this.classpathElement instanceof ClasspathElementModule) {
            return ((ClasspathElementModule) this.classpathElement).getModuleRef();
        }
        return null;
    }

    public Resource getResource() {
        return this.classfileResource;
    }

    @Override // io.github.classgraph.ScanResultObject
    public <T> Class<T> loadClass(Class<T> cls, boolean z) {
        return super.loadClass(cls, z);
    }

    @Override // io.github.classgraph.ScanResultObject
    public <T> Class<T> loadClass(Class<T> cls) {
        return super.loadClass(cls, false);
    }

    @Override // io.github.classgraph.ScanResultObject
    public Class<?> loadClass(boolean z) {
        return super.loadClass(z);
    }

    @Override // io.github.classgraph.ScanResultObject
    public Class<?> loadClass() {
        return super.loadClass(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public ClassInfo getClassInfo() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.typeSignature != null) {
            this.typeSignature.setScanResult(scanResult);
        }
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                ((AnnotationInfo) it.next()).setScanResult(scanResult);
            }
        }
        if (this.fieldInfo != null) {
            Iterator it2 = this.fieldInfo.iterator();
            while (it2.hasNext()) {
                ((FieldInfo) it2.next()).setScanResult(scanResult);
            }
        }
        if (this.methodInfo != null) {
            Iterator it3 = this.methodInfo.iterator();
            while (it3.hasNext()) {
                ((MethodInfo) it3.next()).setScanResult(scanResult);
            }
        }
        if (this.annotationDefaultParamValues != null) {
            Iterator it4 = this.annotationDefaultParamValues.iterator();
            while (it4.hasNext()) {
                ((AnnotationParameterValue) it4.next()).setScanResult(scanResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleRepeatableAnnotations(Set<String> set) {
        if (this.annotationInfo != null) {
            this.annotationInfo.handleRepeatableAnnotations(set, this, RelType.CLASS_ANNOTATIONS, RelType.CLASSES_WITH_ANNOTATION, null);
        }
        if (this.fieldInfo != null) {
            Iterator it = this.fieldInfo.iterator();
            while (it.hasNext()) {
                ((FieldInfo) it.next()).handleRepeatableAnnotations(set);
            }
        }
        if (this.methodInfo != null) {
            Iterator it2 = this.methodInfo.iterator();
            while (it2.hasNext()) {
                ((MethodInfo) it2.next()).handleRepeatableAnnotations(set);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addReferencedClassNames(Set<String> set) {
        if (this.referencedClassNames == null) {
            this.referencedClassNames = set;
        } else {
            this.referencedClassNames.addAll(set);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        super.findReferencedClassInfo(map, set, logNode);
        if (this.referencedClassNames != null) {
            Iterator<String> it = this.referencedClassNames.iterator();
            while (it.hasNext()) {
                ClassInfo orCreateClassInfo = getOrCreateClassInfo(it.next(), map);
                orCreateClassInfo.setScanResult(this.scanResult);
                set.add(orCreateClassInfo);
            }
        }
        getMethodInfo().findReferencedClassInfo(map, set, logNode);
        getFieldInfo().findReferencedClassInfo(map, set, logNode);
        getAnnotationInfo().findReferencedClassInfo(map, set, logNode);
        if (this.annotationDefaultParamValues != null) {
            this.annotationDefaultParamValues.findReferencedClassInfo(map, set, logNode);
        }
        try {
            ClassTypeSignature typeSignature = getTypeSignature();
            if (typeSignature != null) {
                typeSignature.findReferencedClassInfo(map, set, logNode);
            }
        } catch (IllegalArgumentException unused) {
            if (logNode != null) {
                logNode.log("Illegal type signature for class " + getClassName() + ": " + getTypeSignatureStr());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setReferencedClasses(ClassInfoList classInfoList) {
        this.referencedClasses = classInfoList;
    }

    public ClassInfoList getClassDependencies() {
        if (this.scanResult.scanSpec.enableInterClassDependencies) {
            return this.referencedClasses == null ? ClassInfoList.EMPTY_LIST : this.referencedClasses;
        }
        throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
    }

    @Override // java.lang.Comparable
    public int compareTo(ClassInfo classInfo) {
        return this.name.compareTo(classInfo.name);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassInfo)) {
            return false;
        }
        return this.name.equals(((ClassInfo) obj).name);
    }

    public int hashCode() {
        if (this.name == null) {
            return 0;
        }
        return this.name.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        String str;
        boolean z2 = sb.length() == 0;
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ' && sb.charAt(sb.length() - 1) != '(') {
                    sb.append(' ');
                }
                annotationInfo.toString(z, sb);
            }
        }
        ClassTypeSignature classTypeSignature = null;
        try {
            classTypeSignature = getTypeSignature();
        } catch (Exception unused) {
        }
        if (classTypeSignature != null) {
            classTypeSignature.toStringInternal(z ? getSimpleName(this.name) : this.name, false, this.modifiers, isAnnotation(), isInterface(), this.annotationInfo, sb);
            return;
        }
        TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.CLASS, false, sb);
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ' && sb.charAt(sb.length() - 1) != '(') {
            sb.append(' ');
        }
        if (z2) {
            if (isRecord()) {
                str = "record ";
            } else if (isEnum()) {
                str = "enum ";
            } else if (isAnnotation()) {
                str = "@interface ";
            } else {
                str = isInterface() ? "interface " : "class ";
            }
            sb.append(str);
        }
        sb.append(z ? getSimpleName(this.name) : this.name);
        if (this.isRecord) {
            sb.append('(');
            boolean z3 = true;
            Iterator it2 = getFieldInfo().iterator();
            while (it2.hasNext()) {
                FieldInfo fieldInfo = (FieldInfo) it2.next();
                if (!z3) {
                    sb.append(", ");
                } else {
                    z3 = false;
                }
                fieldInfo.toString(false, false, sb);
            }
            sb.append(')');
        }
        ClassInfo superclass = getSuperclass();
        if (superclass != null && !superclass.getName().equals("java.lang.Object")) {
            sb.append(" extends ");
            superclass.toString(z, sb);
        }
        Set<ClassInfo> set = filterClassInfo(RelType.IMPLEMENTED_INTERFACES, false, new ClassType[0]).directlyRelatedClasses;
        if (!set.isEmpty()) {
            sb.append(isInterface() ? " extends " : " implements ");
            boolean z4 = true;
            for (ClassInfo classInfo : set) {
                if (z4) {
                    z4 = false;
                } else {
                    sb.append(", ");
                }
                classInfo.toString(z, sb);
            }
        }
    }
}
