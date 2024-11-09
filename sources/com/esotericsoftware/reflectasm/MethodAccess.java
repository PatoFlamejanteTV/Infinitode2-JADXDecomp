package com.esotericsoftware.reflectasm;

import com.esotericsoftware.asm.ClassWriter;
import com.esotericsoftware.asm.Label;
import com.esotericsoftware.asm.MethodVisitor;
import com.esotericsoftware.asm.Type;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import net.bytebuddy.description.method.MethodDescription;

/* loaded from: infinitode-2.jar:com/esotericsoftware/reflectasm/MethodAccess.class */
public abstract class MethodAccess {
    private String[] methodNames;
    private Class[][] parameterTypes;
    private Class[] returnTypes;

    public abstract Object invoke(Object obj, int i, Object... objArr);

    public Object invoke(Object obj, String str, Class[] clsArr, Object... objArr) {
        return invoke(obj, getIndex(str, clsArr), objArr);
    }

    public Object invoke(Object obj, String str, Object... objArr) {
        return invoke(obj, getIndex(str, objArr == null ? 0 : objArr.length), objArr);
    }

    public int getIndex(String str) {
        int length = this.methodNames.length;
        for (int i = 0; i < length; i++) {
            if (this.methodNames[i].equals(str)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Unable to find non-private method: " + str);
    }

    public int getIndex(String str, Class... clsArr) {
        int length = this.methodNames.length;
        for (int i = 0; i < length; i++) {
            if (this.methodNames[i].equals(str) && Arrays.equals(clsArr, this.parameterTypes[i])) {
                return i;
            }
        }
        throw new IllegalArgumentException("Unable to find non-private method: " + str + SequenceUtils.SPACE + Arrays.toString(clsArr));
    }

    public int getIndex(String str, int i) {
        int length = this.methodNames.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.methodNames[i2].equals(str) && this.parameterTypes[i2].length == i) {
                return i2;
            }
        }
        throw new IllegalArgumentException("Unable to find non-private method: " + str + " with " + i + " params.");
    }

    public String[] getMethodNames() {
        return this.methodNames;
    }

    public Class[][] getParameterTypes() {
        return this.parameterTypes;
    }

    public Class[] getReturnTypes() {
        return this.returnTypes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Class[], java.lang.Class[][]] */
    public static MethodAccess get(Class cls) {
        Class cls2;
        int i;
        boolean isInterface = cls.isInterface();
        if (!isInterface && cls.getSuperclass() == null && cls != Object.class) {
            throw new IllegalArgumentException("The type must not be an interface, a primitive type, or void.");
        }
        ArrayList arrayList = new ArrayList();
        if (!isInterface) {
            Class cls3 = cls;
            while (true) {
                Class cls4 = cls3;
                if (cls4 == Object.class) {
                    break;
                }
                addDeclaredMethodsToList(cls4, arrayList);
                cls3 = cls4.getSuperclass();
            }
        } else {
            recursiveAddInterfaceMethodsToList(cls, arrayList);
        }
        int size = arrayList.size();
        String[] strArr = new String[size];
        ?? r0 = new Class[size];
        Class[] clsArr = new Class[size];
        for (int i2 = 0; i2 < size; i2++) {
            Method method = (Method) arrayList.get(i2);
            strArr[i2] = method.getName();
            r0[i2] = method.getParameterTypes();
            clsArr[i2] = method.getReturnType();
        }
        String name = cls.getName();
        String str = name + "MethodAccess";
        String str2 = str;
        if (str.startsWith("java.")) {
            str2 = "reflectasm." + str2;
        }
        AccessClassLoader accessClassLoader = AccessClassLoader.get(cls);
        synchronized (accessClassLoader) {
            Class loadAccessClass = accessClassLoader.loadAccessClass(str2);
            cls2 = loadAccessClass;
            if (loadAccessClass == null) {
                String replace = str2.replace('.', '/');
                String replace2 = name.replace('.', '/');
                ClassWriter classWriter = new ClassWriter(1);
                classWriter.visit(196653, 33, replace, null, "com/esotericsoftware/reflectasm/MethodAccess", null);
                MethodVisitor visitMethod = classWriter.visitMethod(1, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V", null, null);
                visitMethod.visitCode();
                visitMethod.visitVarInsn(25, 0);
                visitMethod.visitMethodInsn(183, "com/esotericsoftware/reflectasm/MethodAccess", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
                visitMethod.visitInsn(177);
                visitMethod.visitMaxs(0, 0);
                visitMethod.visitEnd();
                MethodVisitor visitMethod2 = classWriter.visitMethod(129, "invoke", "(Ljava/lang/Object;I[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
                visitMethod2.visitCode();
                if (!arrayList.isEmpty()) {
                    visitMethod2.visitVarInsn(25, 1);
                    visitMethod2.visitTypeInsn(192, replace2);
                    visitMethod2.visitVarInsn(58, 4);
                    visitMethod2.visitVarInsn(21, 2);
                    Label[] labelArr = new Label[size];
                    for (int i3 = 0; i3 < size; i3++) {
                        labelArr[i3] = new Label();
                    }
                    Label label = new Label();
                    visitMethod2.visitTableSwitchInsn(0, size - 1, label, labelArr);
                    StringBuilder sb = new StringBuilder(128);
                    for (int i4 = 0; i4 < size; i4++) {
                        visitMethod2.visitLabel(labelArr[i4]);
                        if (i4 == 0) {
                            visitMethod2.visitFrame(1, 1, new Object[]{replace2}, 0, null);
                        } else {
                            visitMethod2.visitFrame(3, 0, null, 0, null);
                        }
                        visitMethod2.visitVarInsn(25, 4);
                        sb.setLength(0);
                        sb.append('(');
                        Object[] objArr = r0[i4];
                        Class cls5 = clsArr[i4];
                        for (int i5 = 0; i5 < objArr.length; i5++) {
                            visitMethod2.visitVarInsn(25, 3);
                            visitMethod2.visitIntInsn(16, i5);
                            visitMethod2.visitInsn(50);
                            Type type = Type.getType(objArr[i5]);
                            switch (type.getSort()) {
                                case 1:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Boolean");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Boolean", "booleanValue", "()Z");
                                    break;
                                case 2:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Character");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Character", "charValue", "()C");
                                    break;
                                case 3:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Byte");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Byte", "byteValue", "()B");
                                    break;
                                case 4:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Short");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Short", "shortValue", "()S");
                                    break;
                                case 5:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Integer");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I");
                                    break;
                                case 6:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Float");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Float", "floatValue", "()F");
                                    break;
                                case 7:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Long");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Long", "longValue", "()J");
                                    break;
                                case 8:
                                    visitMethod2.visitTypeInsn(192, "java/lang/Double");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Double", "doubleValue", "()D");
                                    break;
                                case 9:
                                    visitMethod2.visitTypeInsn(192, type.getDescriptor());
                                    break;
                                case 10:
                                    visitMethod2.visitTypeInsn(192, type.getInternalName());
                                    break;
                            }
                            sb.append(type.getDescriptor());
                        }
                        sb.append(')');
                        sb.append(Type.getDescriptor(cls5));
                        if (isInterface) {
                            i = 185;
                        } else if (Modifier.isStatic(((Method) arrayList.get(i4)).getModifiers())) {
                            i = 184;
                        } else {
                            i = 182;
                        }
                        visitMethod2.visitMethodInsn(i, replace2, strArr[i4], sb.toString());
                        switch (Type.getType(cls5).getSort()) {
                            case 0:
                                visitMethod2.visitInsn(1);
                                break;
                            case 1:
                                visitMethod2.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
                                break;
                            case 2:
                                visitMethod2.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
                                break;
                            case 3:
                                visitMethod2.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                                break;
                            case 4:
                                visitMethod2.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                                break;
                            case 5:
                                visitMethod2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                break;
                            case 6:
                                visitMethod2.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                                break;
                            case 7:
                                visitMethod2.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                                break;
                            case 8:
                                visitMethod2.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                                break;
                        }
                        visitMethod2.visitInsn(176);
                    }
                    visitMethod2.visitLabel(label);
                    visitMethod2.visitFrame(3, 0, null, 0, null);
                }
                visitMethod2.visitTypeInsn(187, "java/lang/IllegalArgumentException");
                visitMethod2.visitInsn(89);
                visitMethod2.visitTypeInsn(187, "java/lang/StringBuilder");
                visitMethod2.visitInsn(89);
                visitMethod2.visitLdcInsn("Method not found: ");
                visitMethod2.visitMethodInsn(183, "java/lang/StringBuilder", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/String;)V");
                visitMethod2.visitVarInsn(21, 2);
                visitMethod2.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
                visitMethod2.visitMethodInsn(182, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
                visitMethod2.visitMethodInsn(183, "java/lang/IllegalArgumentException", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/String;)V");
                visitMethod2.visitInsn(191);
                visitMethod2.visitMaxs(0, 0);
                visitMethod2.visitEnd();
                classWriter.visitEnd();
                cls2 = accessClassLoader.defineAccessClass(str2, classWriter.toByteArray());
            }
        }
        try {
            MethodAccess methodAccess = (MethodAccess) cls2.newInstance();
            methodAccess.methodNames = strArr;
            methodAccess.parameterTypes = r0;
            methodAccess.returnTypes = clsArr;
            return methodAccess;
        } catch (Throwable th) {
            throw new RuntimeException("Error constructing method access class: " + str2, th);
        }
    }

    private static void addDeclaredMethodsToList(Class cls, ArrayList<Method> arrayList) {
        for (Method method : cls.getDeclaredMethods()) {
            if (!Modifier.isPrivate(method.getModifiers())) {
                arrayList.add(method);
            }
        }
    }

    private static void recursiveAddInterfaceMethodsToList(Class cls, ArrayList<Method> arrayList) {
        addDeclaredMethodsToList(cls, arrayList);
        for (Class<?> cls2 : cls.getInterfaces()) {
            recursiveAddInterfaceMethodsToList(cls2, arrayList);
        }
    }
}
