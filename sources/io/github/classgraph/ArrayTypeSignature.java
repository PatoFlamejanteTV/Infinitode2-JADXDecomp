package io.github.classgraph;

import io.github.classgraph.Classfile;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;

/* loaded from: infinitode-2.jar:io/github/classgraph/ArrayTypeSignature.class */
public class ArrayTypeSignature extends ReferenceTypeSignature {
    private final String typeSignatureStr;
    private String className;
    private ArrayClassInfo arrayClassInfo;
    private Class<?> elementClassRef;
    private final TypeSignature nestedType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayTypeSignature(TypeSignature typeSignature, int i, String str) {
        boolean startsWith = str.startsWith("[[");
        if (i <= 0) {
            throw new IllegalArgumentException("numDims < 1");
        }
        if ((i >= 2) != startsWith) {
            throw new IllegalArgumentException("numDims does not match type signature");
        }
        this.typeSignatureStr = str;
        this.nestedType = startsWith ? new ArrayTypeSignature(typeSignature, i - 1, str.substring(1)) : typeSignature;
    }

    public String getTypeSignatureStr() {
        return this.typeSignatureStr;
    }

    public TypeSignature getElementTypeSignature() {
        ArrayTypeSignature arrayTypeSignature = this;
        while (true) {
            ArrayTypeSignature arrayTypeSignature2 = arrayTypeSignature;
            if (arrayTypeSignature2.nestedType instanceof ArrayTypeSignature) {
                arrayTypeSignature = (ArrayTypeSignature) arrayTypeSignature2.nestedType;
            } else {
                return arrayTypeSignature2.getNestedType();
            }
        }
    }

    public int getNumDimensions() {
        int i = 1;
        ArrayTypeSignature arrayTypeSignature = this;
        while (arrayTypeSignature.nestedType instanceof ArrayTypeSignature) {
            arrayTypeSignature = (ArrayTypeSignature) arrayTypeSignature.nestedType;
            i++;
        }
        return i;
    }

    public TypeSignature getNestedType() {
        return this.nestedType;
    }

    @Override // io.github.classgraph.TypeSignature, io.github.classgraph.HierarchicalTypeSignature
    protected void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo) {
        if (list.isEmpty()) {
            addTypeAnnotation(annotationInfo);
            return;
        }
        Classfile.TypePathNode typePathNode = list.get(0);
        if (typePathNode.typePathKind != 0 || typePathNode.typeArgumentIdx != 0) {
            throw new IllegalArgumentException("typePath element contains bad values: " + typePathNode);
        }
        this.nestedType.addTypeAnnotation(list.subList(1, list.size()), annotationInfo);
    }

    @Override // io.github.classgraph.TypeSignature, io.github.classgraph.HierarchicalTypeSignature
    public AnnotationInfoList getTypeAnnotationInfo() {
        return this.typeAnnotationInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        if (this.className == null) {
            this.className = toString();
        }
        return this.className;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public ClassInfo getClassInfo() {
        return getArrayClassInfo();
    }

    public ArrayClassInfo getArrayClassInfo() {
        if (this.arrayClassInfo == null) {
            if (this.scanResult != null) {
                String className = getClassName();
                this.arrayClassInfo = (ArrayClassInfo) this.scanResult.classNameToClassInfo.get(className);
                if (this.arrayClassInfo == null) {
                    Map<String, ClassInfo> map = this.scanResult.classNameToClassInfo;
                    ArrayClassInfo arrayClassInfo = new ArrayClassInfo(this);
                    this.arrayClassInfo = arrayClassInfo;
                    map.put(className, arrayClassInfo);
                    this.arrayClassInfo.setScanResult(this.scanResult);
                }
            } else {
                this.arrayClassInfo = new ArrayClassInfo(this);
            }
        }
        return this.arrayClassInfo;
    }

    @Override // io.github.classgraph.HierarchicalTypeSignature, io.github.classgraph.ScanResultObject
    void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        this.nestedType.setScanResult(scanResult);
        if (this.arrayClassInfo != null) {
            this.arrayClassInfo.setScanResult(scanResult);
        }
    }

    @Override // io.github.classgraph.TypeSignature
    protected void findReferencedClassNames(Set<String> set) {
        this.nestedType.findReferencedClassNames(set);
    }

    public Class<?> loadElementClass(boolean z) {
        IllegalArgumentException illegalArgumentException;
        if (this.elementClassRef == null) {
            TypeSignature elementTypeSignature = getElementTypeSignature();
            if (elementTypeSignature instanceof BaseTypeSignature) {
                this.elementClassRef = ((BaseTypeSignature) elementTypeSignature).getType();
            } else if (this.scanResult != null) {
                this.elementClassRef = elementTypeSignature.loadClass(z);
            } else {
                try {
                    this.elementClassRef = Class.forName(elementTypeSignature.getClassName());
                } finally {
                    if (!z) {
                    }
                }
            }
        }
        return this.elementClassRef;
    }

    public Class<?> loadElementClass() {
        return loadElementClass(false);
    }

    @Override // io.github.classgraph.ScanResultObject
    public Class<?> loadClass(boolean z) {
        Class<?> loadElementClass;
        if (this.classRef == null) {
            if (z) {
                try {
                    loadElementClass = loadElementClass();
                } catch (IllegalArgumentException unused) {
                    return null;
                }
            } else {
                loadElementClass = loadElementClass();
            }
            if (loadElementClass == null) {
                throw new IllegalArgumentException("Could not load array element class " + getElementTypeSignature());
            }
            this.classRef = Array.newInstance(loadElementClass, new int[getNumDimensions()]).getClass();
        }
        return this.classRef;
    }

    @Override // io.github.classgraph.ScanResultObject
    public Class<?> loadClass() {
        return loadClass(false);
    }

    public int hashCode() {
        return 1 + this.nestedType.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArrayTypeSignature)) {
            return false;
        }
        ArrayTypeSignature arrayTypeSignature = (ArrayTypeSignature) obj;
        return Objects.equals(this.typeAnnotationInfo, arrayTypeSignature.typeAnnotationInfo) && this.nestedType.equals(arrayTypeSignature.nestedType);
    }

    @Override // io.github.classgraph.TypeSignature
    public boolean equalsIgnoringTypeParams(TypeSignature typeSignature) {
        if (this == typeSignature) {
            return true;
        }
        if (!(typeSignature instanceof ArrayTypeSignature)) {
            return false;
        }
        return this.nestedType.equalsIgnoringTypeParams(((ArrayTypeSignature) typeSignature).nestedType);
    }

    @Override // io.github.classgraph.HierarchicalTypeSignature
    protected void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb) {
        getElementTypeSignature().toStringInternal(z, annotationInfoList, sb);
        ArrayTypeSignature arrayTypeSignature = this;
        while (true) {
            ArrayTypeSignature arrayTypeSignature2 = arrayTypeSignature;
            if (arrayTypeSignature2.typeAnnotationInfo != null && !arrayTypeSignature2.typeAnnotationInfo.isEmpty()) {
                Iterator it = arrayTypeSignature2.typeAnnotationInfo.iterator();
                while (it.hasNext()) {
                    AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
                    if (sb.length() == 0 || sb.charAt(sb.length() - 1) != ' ') {
                        sb.append(' ');
                    }
                    annotationInfo.toString(z, sb);
                }
                sb.append(' ');
            }
            sb.append("[]");
            if (arrayTypeSignature2.nestedType instanceof ArrayTypeSignature) {
                arrayTypeSignature = (ArrayTypeSignature) arrayTypeSignature2.nestedType;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayTypeSignature parse(Parser parser, String str) {
        int i = 0;
        int position = parser.getPosition();
        while (parser.peek() == '[') {
            i++;
            parser.next();
        }
        if (i > 0) {
            TypeSignature parse = TypeSignature.parse(parser, str);
            if (parse != null) {
                return new ArrayTypeSignature(parse, i, parser.getSubsequence(position, parser.getPosition()).toString());
            }
            throw new ParseException(parser, "elementTypeSignature == null");
        }
        return null;
    }
}
