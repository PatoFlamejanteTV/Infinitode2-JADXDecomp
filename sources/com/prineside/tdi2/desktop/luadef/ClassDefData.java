package com.prineside.tdi2.desktop.luadef;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.desktop.LuaDefinitionsGenerator;
import com.prineside.tdi2.desktop.luadef.JavadocHandler;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.ObjectIntPair;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/ClassDefData.class */
public class ClassDefData {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1844a = TLog.forClass(ClassDefData.class);
    public static final int CLASS_TYPE_CLASS = 0;
    public static final int CLASS_TYPE_INTERFACE = 1;
    public static final int CLASS_TYPE_FUNCTIONAL_INTERFACE = 2;
    public static final int CLASS_TYPE_ANNOTATION = 3;
    public static final int CLASS_TYPE_ENUM = 4;
    public static final int CLASS_TYPE_ABSTRACT_CLASS = 5;

    /* renamed from: b, reason: collision with root package name */
    private static Array<Class<?>> f1845b;
    private final LuaDefinitionsGenerator c;
    public final Class<?> clazz;
    public final String filePath;
    public int classType;
    public boolean isSerializable;

    @Null
    public FunctionalInterfaceProxyData functionalInterfaceProxyData;

    @Null
    public Class<?> superClass;

    @Null
    public String javadocUrl;

    @Null
    public String classDescription;

    @Null
    public String classGenericsString;
    public Array<InnerClassData> innerClasses = new Array<>();
    public Array<FieldData> staticFields = new Array<>();
    public Array<ConstructorData> constructors = new Array<>();
    public Array<MethodData> staticMethods = new Array<>();
    public Array<Class<?>> allInterfaces = new Array<>();
    public Array<FieldData> instanceFields = new Array<>();
    public Array<MethodData> instanceMethods = new Array<>();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/ClassDefData$ParamData.class */
    public static class ParamData {
        public Parameter param;
        public String name;
        public String typeName;

        @Null
        public String description;
        public boolean canBeNull;
    }

    public ClassDefData(LuaDefinitionsGenerator luaDefinitionsGenerator, Class<?> cls) {
        this.c = luaDefinitionsGenerator;
        this.clazz = cls;
        this.filePath = cls.getName().replaceAll("\\.", "/").replaceAll("\\$", JavaConstant.Dynamic.DEFAULT_NAME);
    }

    private static Array<Class<?>> a() {
        if (f1845b == null) {
            f1845b = new Array<>();
            try {
                FileReader fileReader = new FileReader("res/luaj/interfaces-priority.txt");
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    int i = 1;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            String trim = readLine.trim();
                            if (trim.length() != 0 && trim.charAt(0) != '#') {
                                try {
                                    Class<?> cls = Class.forName(trim);
                                    if (cls.isInterface()) {
                                        f1845b.add(cls);
                                    } else {
                                        throw new IllegalArgumentException(cls + " is not an interface");
                                        break;
                                    }
                                } catch (Exception e) {
                                    f1844a.e("Failed to get class for '%s' defined on line %s in interfaces-priority.txt", trim, Integer.valueOf(i), e);
                                }
                            }
                            i++;
                        } else {
                            fileReader.close();
                            break;
                        }
                    }
                } finally {
                }
            } catch (Exception unused) {
                f1844a.e("Failed to load interfaces-priority.txt", new Object[0]);
            }
        }
        return f1845b;
    }

    private static String a(Class<?> cls) {
        if (cls == CharSequence.class) {
            return "|string";
        }
        if (cls == CharSequence[].class) {
            return "|string[]";
        }
        if (cls == CharSequence[][].class) {
            return "|string[][]";
        }
        if (cls == Object.class) {
            return "|any";
        }
        if (cls == Object[].class) {
            return "|any[]";
        }
        if (cls == Object[][].class) {
            return "|any[][]";
        }
        return "";
    }

    private ParamData a(int i, Parameter parameter, @Null JavadocHandler.ParamJD paramJD, Object obj) {
        String str = ParameterDescription.NAME_PREFIX + i;
        Class<?> type = parameter.getType();
        if (parameter.isVarArgs()) {
            str = "...";
            if (type.isArray()) {
                type = type.getComponentType();
            } else {
                f1844a.w("varargs param type " + parameter + " of " + obj + " is not an array", new Object[0]);
            }
        } else {
            if (paramJD != null) {
                str = paramJD.name;
            }
            if (LuaDefUtils.LUA_KEYWORDS.contains(str)) {
                str = "p_" + str;
            }
        }
        String luaClassName = this.c.getLuaClassName(type, true);
        String str2 = luaClassName;
        if (luaClassName == null) {
            f1844a.e("not accessible parameter type for '" + type + "' in " + obj, new Object[0]);
            str2 = "any";
        }
        String str3 = str2 + a(type);
        ParamData paramData = new ParamData();
        paramData.param = parameter;
        paramData.name = str;
        paramData.typeName = str3;
        if (obj instanceof Constructor) {
            paramData.canBeNull = this.c.hasNullAnnotation((Constructor<?>) obj, i);
        } else if (obj instanceof Method) {
            paramData.canBeNull = this.c.hasNullAnnotation((Method) obj, i);
        }
        if (paramJD != null) {
            paramData.description = paramJD.description;
        }
        return paramData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void prepare() {
        Class<? super Object> cls;
        JavadocHandler.ClassJD forClass = this.c.javadocHandler.getForClass(this.clazz);
        this.javadocUrl = forClass.javadocUrl;
        this.classDescription = forClass.description;
        this.classGenericsString = forClass.genericsString;
        this.classType = 0;
        this.isSerializable = false;
        this.innerClasses.clear();
        this.staticFields.clear();
        this.functionalInterfaceProxyData = null;
        this.constructors.clear();
        this.staticMethods.clear();
        this.superClass = null;
        this.allInterfaces.clear();
        this.instanceFields.clear();
        this.instanceMethods.clear();
        Whitelist whitelist = this.c.getWhitelist();
        Array<Field> gatherFieldsCached = LuaDefUtils.gatherFieldsCached(this.clazz);
        Array<Method> gatherMethodsCached = LuaDefUtils.gatherMethodsCached(this.clazz);
        if (this.clazz.isInterface()) {
            if (this.clazz.isAnnotation()) {
                this.classType = 3;
            } else if (whitelist.isInterfaceProxyWhiteListed(this.clazz) && this.clazz.getDeclaredMethods().length == 1) {
                this.classType = 2;
            } else {
                this.classType = 1;
            }
        } else if (this.clazz.isEnum()) {
            this.classType = 4;
        } else if (Modifier.isAbstract(this.clazz.getModifiers())) {
            this.classType = 5;
        } else {
            this.classType = 0;
        }
        this.isSerializable = this.c.isClassRegisteredInKryo(this.clazz);
        for (int i = 0; i < gatherFieldsCached.size; i++) {
            Field field = gatherFieldsCached.items[i];
            if (whitelist.isFieldWhiteListed(field) && Modifier.isStatic(field.getModifiers())) {
                String luaClassName = this.c.getLuaClassName(field.getType(), true);
                String str = luaClassName;
                if (luaClassName == null) {
                    if (LuaDefinitionsGenerator.verbose) {
                        f1844a.i("using \"any\" as a type for " + field + " on " + this.clazz + " as it is not marked as used and will have no definitions", new Object[0]);
                    }
                    str = "any";
                }
                String name = field.getName();
                if (LuaDefUtils.LUA_KEYWORDS.contains(name)) {
                    name = "_F_" + name;
                }
                if (this.c.hasNullAnnotation(field)) {
                    name = name + TypeDescription.Generic.OfWildcardType.SYMBOL;
                }
                JavadocHandler.FieldJD fieldJD = forClass.fields.get(field);
                FieldData fieldData = new FieldData(field);
                fieldData.name = name;
                fieldData.typeName = str;
                if (fieldJD != null) {
                    fieldData.description = fieldJD.description;
                    fieldData.generics = fieldJD.generics;
                }
                this.staticFields.add(fieldData);
            }
        }
        for (Class<?> cls2 : this.clazz.getDeclaredClasses()) {
            if (this.c.isClassUsed(cls2)) {
                InnerClassData innerClassData = new InnerClassData(cls2);
                innerClassData.name = cls2.getSimpleName();
                innerClassData.typeName = this.c.getLuaClassName(cls2, false) + ".class";
                this.innerClasses.add(innerClassData);
            }
        }
        if (this.clazz.isInterface() && !this.clazz.isAnnotation() && whitelist.isInterfaceProxyWhiteListed(this.clazz) && this.clazz.getDeclaredMethods().length == 1) {
            Method method = this.clazz.getDeclaredMethods()[0];
            JavadocHandler.MethodJD methodJD = forClass.methods.get(method);
            String luaClassName2 = this.c.getLuaClassName(method.getReturnType(), true);
            String str2 = luaClassName2;
            if (luaClassName2 == null) {
                str2 = "any";
            }
            String str3 = str2 + a(method.getReturnType());
            this.functionalInterfaceProxyData = new FunctionalInterfaceProxyData(method);
            this.functionalInterfaceProxyData.returnTypeName = str3;
            int i2 = 0;
            for (Parameter parameter : method.getParameters()) {
                this.functionalInterfaceProxyData.params.add(a(i2, parameter, methodJD == null ? null : methodJD.params.get(i2), method));
                i2++;
            }
        }
        if (!this.clazz.isInterface()) {
            Array<Constructor<?>> gatherConstructorsCached = LuaDefUtils.gatherConstructorsCached(this.clazz);
            Array array = new Array();
            for (int i3 = 0; i3 < gatherConstructorsCached.size; i3++) {
                Constructor<?> constructor = gatherConstructorsCached.items[i3];
                if (whitelist.isConstructorWhiteListed(constructor)) {
                    array.add(constructor);
                }
            }
            Array<String> array2 = null;
            if (array.size >= 2) {
                array2 = ReflectionUtils.LuaRelated.generateOverloadSuffixForConstructors(array);
            }
            int i4 = 0;
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                Constructor constructor2 = (Constructor) it.next();
                ConstructorData constructorData = new ConstructorData(constructor2);
                this.constructors.add(constructorData);
                JavadocHandler.ConstructorJD constructorJD = forClass.constructors.get(constructor2);
                if (constructorJD != null) {
                    constructorData.description = constructorJD.description;
                }
                int i5 = 0;
                for (Parameter parameter2 : constructor2.getParameters()) {
                    constructorData.params.add(a(i5, parameter2, constructorJD != null ? constructorJD.params.get(i5) : null, constructor2));
                    i5++;
                }
                String str4 = "new";
                if (array2 != null) {
                    str4 = str4 + array2.get(i4);
                }
                constructorData.name = str4;
                i4++;
            }
        }
        HashMap hashMap = new HashMap();
        for (int i6 = 0; i6 < gatherMethodsCached.size; i6++) {
            Method method2 = gatherMethodsCached.items[i6];
            if (whitelist.isMethodWhiteListedInDeclaringClass(method2) && Modifier.isStatic(method2.getModifiers())) {
                if (((Array) hashMap.get(method2.getName())) == null) {
                    hashMap.put(method2.getName(), new Array());
                }
                ((Array) hashMap.get(method2.getName())).add(method2);
            }
        }
        HashMap hashMap2 = new HashMap();
        Iterator it2 = hashMap.entrySet().iterator();
        while (it2.hasNext()) {
            Array array3 = (Array) ((Map.Entry) it2.next()).getValue();
            if (array3.size >= 2) {
                Array<String> generateOverloadSuffixForMethods = ReflectionUtils.LuaRelated.generateOverloadSuffixForMethods(array3);
                for (int i7 = 0; i7 < array3.size; i7++) {
                    hashMap2.put((Method) array3.get(i7), generateOverloadSuffixForMethods.get(i7));
                }
            }
        }
        for (int i8 = 0; i8 < gatherMethodsCached.size; i8++) {
            Method method3 = gatherMethodsCached.items[i8];
            if (whitelist.isMethodWhiteListedInDeclaringClass(method3) && Modifier.isStatic(method3.getModifiers())) {
                MethodData methodData = new MethodData(method3);
                this.staticMethods.add(methodData);
                String name2 = method3.getName();
                String str5 = (String) hashMap2.get(method3);
                if (str5 != null) {
                    if (method3.getDeclaringClass().getName().startsWith("com.prineside") && !method3.isAnnotationPresent(IgnoreMethodOverloadLuaDefWarning.class)) {
                        f1844a.w("overload method \"" + method3 + "\" - suffix auto-generated from parameters", new Object[0]);
                    }
                    name2 = name2 + str5;
                }
                if (LuaDefUtils.LUA_KEYWORDS.contains(name2)) {
                    name2 = "_M_" + name2;
                } else {
                    int i9 = 0;
                    while (true) {
                        if (i9 >= gatherFieldsCached.size) {
                            break;
                        }
                        Field field2 = gatherFieldsCached.items[i9];
                        if (!whitelist.isFieldWhiteListed(field2) || !Modifier.isStatic(field2.getModifiers()) || !field2.getName().equals(method3.getName())) {
                            i9++;
                        } else {
                            name2 = "_M_" + name2;
                            break;
                        }
                    }
                }
                methodData.name = name2;
                JavadocHandler.MethodJD methodJD2 = forClass.methods.get(method3);
                if (methodJD2 != null) {
                    methodData.description = methodJD2.description;
                    methodData.returnDescription = methodJD2.returnDescription;
                }
                String luaClassName3 = this.c.getLuaClassName(method3.getReturnType(), true);
                String str6 = luaClassName3;
                if (luaClassName3 == null) {
                    f1844a.e("not accessible return type for '" + method3.getReturnType() + "' in method " + method3, new Object[0]);
                    str6 = "any";
                }
                methodData.returnTypeName = str6;
                int i10 = 0;
                for (Parameter parameter3 : method3.getParameters()) {
                    methodData.params.add(a(i10, parameter3, methodJD2 == null ? null : methodJD2.params.get(i10), method3));
                    i10++;
                }
            }
        }
        Class<? super Object> superclass = this.clazz.getSuperclass();
        while (true) {
            cls = superclass;
            if (cls == null || this.c.isClassUsed(cls)) {
                break;
            } else {
                superclass = cls.getSuperclass();
            }
        }
        if (cls != null) {
            if (cls == Object.class) {
                Class<?>[] interfaces = this.clazz.getInterfaces();
                if (interfaces.length != 0) {
                    Array array4 = new Array();
                    for (Class<?> cls3 : interfaces) {
                        if (this.c.isClassUsed(cls3)) {
                            array4.add(cls3);
                        }
                    }
                    if (array4.size != 0) {
                        if (array4.size > 1) {
                            Array array5 = new Array();
                            for (int i11 = 0; i11 < array4.size; i11++) {
                                Class<?> cls4 = (Class) array4.get(i11);
                                int i12 = -1;
                                Array<Class<?>> a2 = a();
                                int i13 = 0;
                                while (true) {
                                    if (i13 >= a2.size) {
                                        break;
                                    }
                                    if (a2.get(i13) != cls4) {
                                        i13++;
                                    } else {
                                        i12 = i13;
                                        break;
                                    }
                                }
                                array5.add(new ObjectIntPair(cls4, i12));
                            }
                            array5.sort((objectIntPair, objectIntPair2) -> {
                                return Integer.compare(objectIntPair.intValue, objectIntPair2.intValue);
                            });
                            ObjectIntPair objectIntPair3 = (ObjectIntPair) array5.first();
                            cls = (Class) objectIntPair3.object;
                            if (objectIntPair3.intValue == -1) {
                                f1844a.w("Multiple interfaces in " + this.clazz + ": " + array4.toString(", ") + ". Using " + cls + " (not defined in interfaces-priority.txt) as a super class", new Object[0]);
                            }
                        } else {
                            cls = (Class) array4.first();
                        }
                    }
                }
            }
        } else if (this.clazz != Object.class) {
            cls = Object.class;
        }
        this.superClass = cls;
        Class<?> cls5 = this.clazz;
        while (true) {
            Class<?> cls6 = cls5;
            if (cls6 == null) {
                break;
            }
            for (Class<?> cls7 : this.clazz.getInterfaces()) {
                if (!this.allInterfaces.contains(cls7, true)) {
                    this.allInterfaces.add(cls7);
                }
            }
            cls5 = cls6.getSuperclass();
        }
        for (int i14 = 0; i14 < gatherFieldsCached.size; i14++) {
            Field field3 = gatherFieldsCached.items[i14];
            if (whitelist.isFieldWhiteListed(field3) && !Modifier.isStatic(field3.getModifiers())) {
                String luaClassName4 = this.c.getLuaClassName(field3.getType(), true);
                String str7 = luaClassName4;
                if (luaClassName4 == null) {
                    str7 = "any";
                }
                String name3 = field3.getName();
                if (LuaDefUtils.LUA_KEYWORDS.contains(name3)) {
                    name3 = "_F_" + name3;
                }
                JavadocHandler.FieldJD fieldJD2 = forClass.fields.get(field3);
                FieldData fieldData2 = new FieldData(field3);
                fieldData2.name = name3;
                fieldData2.typeName = str7;
                fieldData2.canBeNull = this.c.hasNullAnnotation(field3);
                if (fieldJD2 != null) {
                    fieldData2.description = fieldJD2.description;
                    fieldData2.generics = fieldJD2.generics;
                }
                this.instanceFields.add(fieldData2);
            }
        }
        for (int i15 = 0; i15 < gatherMethodsCached.size; i15++) {
            Method method4 = gatherMethodsCached.items[i15];
            if (whitelist.isMethodWhiteListedInDeclaringClass(method4) && !Modifier.isStatic(method4.getModifiers())) {
                String name4 = method4.getName();
                if (LuaDefUtils.LUA_KEYWORDS.contains(name4)) {
                    name4 = "_M_" + name4;
                } else {
                    int i16 = 0;
                    while (true) {
                        if (i16 >= gatherFieldsCached.size) {
                            break;
                        }
                        Field field4 = gatherFieldsCached.items[i16];
                        if (!whitelist.isFieldWhiteListed(field4) || Modifier.isStatic(field4.getModifiers()) || !field4.getName().equals(method4.getName())) {
                            i16++;
                        } else {
                            name4 = "_M_" + name4;
                            break;
                        }
                    }
                }
                if (this.clazz == Class.class) {
                    name4 = JavaConstant.Dynamic.DEFAULT_NAME + name4;
                }
                MethodData methodData2 = new MethodData(method4);
                methodData2.name = name4;
                this.instanceMethods.add(methodData2);
                JavadocHandler.MethodJD methodJD3 = forClass.methods.get(method4);
                if (methodJD3 != null) {
                    methodData2.description = methodJD3.description;
                    methodData2.returnDescription = methodJD3.returnDescription;
                    methodData2.specifiedByInterface = methodJD3.specifiedByInterface;
                    methodData2.overridesSuperMethod = methodJD3.overridesSuperMethod;
                }
                String luaClassName5 = this.c.getLuaClassName(method4.getReturnType(), true);
                String str8 = luaClassName5;
                if (luaClassName5 == null) {
                    f1844a.e("not accessible return type for '" + method4.getReturnType() + "' in method " + method4, new Object[0]);
                    str8 = "any";
                }
                methodData2.returnTypeName = str8;
                int i17 = 0;
                for (Parameter parameter4 : method4.getParameters()) {
                    methodData2.params.add(a(i17, parameter4, methodJD3 == null ? null : methodJD3.params.get(i17), method4));
                    i17++;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void printFileData(StringBuilder stringBuilder) {
        String luaClassName = this.c.getLuaClassName(this.clazz, false);
        stringBuilder.append("--- @meta\n");
        stringBuilder.append("--- @diagnostic disable: duplicate-index\n\n");
        String[] split = this.filePath.split("/");
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            if (i != 0) {
                stringBuilder2.append(".");
            }
            stringBuilder2.append(split[i]);
        }
        stringBuilder.append("--- @see ").append(stringBuilder2).append(SequenceUtils.EOL);
        if (this.javadocUrl != null) {
            stringBuilder.append("--- �� ").append(this.javadocUrl).append(SequenceUtils.EOL);
        }
        stringBuilder.append(SequenceUtils.EOL);
        stringBuilder.append("--- Scroll to object definition:\n");
        stringBuilder.append("--- @see ").append(luaClassName).append(SequenceUtils.EOL);
        stringBuilder.append(SequenceUtils.EOL);
        stringBuilder.append(SequenceUtils.EOL);
        stringBuilder.append("-- ─────────────────────────────────────────────────────────────────────────────\n");
        stringBuilder.append("--                           --- Class definition ---\n");
        Array array = new Array();
        if (this.isSerializable) {
            array.add("�� KryoSerializable");
        }
        switch (this.classType) {
            case 1:
                array.add("⭐ Interface");
                break;
            case 2:
                array.add("⭐ Interface");
                array.add("✨ Functional interface");
                break;
            case 3:
                array.add("✍ Annotation");
                break;
            case 4:
                array.add("�� Enum");
                break;
            case 5:
                array.add("�� Abstract class");
                break;
        }
        if (array.size != 0) {
            int i2 = (array.size - 1) * 3;
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                i2 += ((String) it.next()).length();
            }
            stringBuilder.append("--");
            int i3 = (77 - i2) / 2;
            for (int i4 = 0; i4 < i3; i4++) {
                stringBuilder.append(' ');
            }
            Array.ArrayIterator it2 = array.iterator();
            while (it2.hasNext()) {
                stringBuilder.append((String) it2.next()).append("   ");
            }
            stringBuilder.append(SequenceUtils.EOL);
        }
        stringBuilder.append("-- ─────────────────────────────────────────────────────────────────────────────\n");
        stringBuilder.append(SequenceUtils.EOL);
        stringBuilder.append("--- @class ").append(luaClassName).append(".class: ").append(this.c.getLuaClassName(Class.class, false)).append(SequenceUtils.EOL);
        if (this.staticFields.size != 0) {
            stringBuilder.append("---\n");
            Array.ArrayIterator<FieldData> it3 = this.staticFields.iterator();
            while (it3.hasNext()) {
                FieldData next = it3.next();
                stringBuilder.append("--- @field ").append(next.name).append(SequenceUtils.SPACE).append(next.typeName).append(LuaDefUtils.getLuaPrimitiveType(next.field.getType()));
                if (next.generics != null) {
                    stringBuilder.append(" (").append(next.generics).append(")");
                }
                if (next.description != null) {
                    stringBuilder.append(' ').append(next.description);
                }
                stringBuilder.append(SequenceUtils.EOL);
            }
        }
        if (this.innerClasses.size != 0) {
            stringBuilder.append("---\n");
            Array.ArrayIterator<InnerClassData> it4 = this.innerClasses.iterator();
            while (it4.hasNext()) {
                InnerClassData next2 = it4.next();
                stringBuilder.append("--- @field ").append(next2.name).append(SequenceUtils.SPACE).append(this.c.getLuaClassName(next2.clazz, false)).append(".class\n");
            }
        }
        if (this.classType == 1 || this.classType == 2) {
            stringBuilder.append("---\n");
            if (this.functionalInterfaceProxyData != null) {
                int i5 = 0;
                stringBuilder.append("--- @overload fun(method: fun(");
                Array.ArrayIterator<ParamData> it5 = this.functionalInterfaceProxyData.params.iterator();
                while (it5.hasNext()) {
                    ParamData next3 = it5.next();
                    if (i5 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(next3.name).append(next3.canBeNull ? TypeDescription.Generic.OfWildcardType.SYMBOL : "").append(": ").append(next3.typeName);
                    i5++;
                }
                stringBuilder.append("): ").append(this.functionalInterfaceProxyData.returnTypeName).append("): ").append(luaClassName).append(SequenceUtils.EOL);
            }
            stringBuilder.append("--- @overload fun(methods: { ");
            int i6 = 0;
            Array.ArrayIterator<MethodData> it6 = this.instanceMethods.iterator();
            while (it6.hasNext()) {
                MethodData next4 = it6.next();
                if (i6 != 0) {
                    stringBuilder.append("; ");
                }
                stringBuilder.append(next4.name).append(": fun(self");
                Array.ArrayIterator<ParamData> it7 = next4.params.iterator();
                while (it7.hasNext()) {
                    ParamData next5 = it7.next();
                    stringBuilder.append(", ").append(next5.name).append(next5.canBeNull ? TypeDescription.Generic.OfWildcardType.SYMBOL : "").append(": ").append(next5.typeName);
                }
                stringBuilder.append("): ").append(next4.returnTypeName).append(a(next4.method.getReturnType()));
                i6++;
            }
            stringBuilder.append("}): ").append(luaClassName).append(SequenceUtils.EOL);
        }
        stringBuilder.append(luaClassName).append(".class = {\n");
        if (this.constructors.size != 0) {
            stringBuilder.append(SequenceUtils.EOL);
            stringBuilder.append("    -- ─────────────────────────── Constructors ────────────────────────────────\n");
            Array.ArrayIterator<ConstructorData> it8 = this.constructors.iterator();
            while (it8.hasNext()) {
                ConstructorData next6 = it8.next();
                stringBuilder.append(SequenceUtils.EOL);
                stringBuilder.append("    --- ✨ Constructor\n");
                if (next6.description != null) {
                    stringBuilder.append("    ---\n");
                    stringBuilder.append(next6.description);
                }
                if (next6.ctor.isVarArgs()) {
                    stringBuilder.append("    --- �� Varargs\n");
                }
                Array.ArrayIterator<ParamData> it9 = next6.params.iterator();
                while (it9.hasNext()) {
                    ParamData next7 = it9.next();
                    stringBuilder.append("    --- @param ").append(next7.name).append(next7.canBeNull ? TypeDescription.Generic.OfWildcardType.SYMBOL : "").append(SequenceUtils.SPACE).append(next7.typeName).append(LuaDefUtils.getLuaPrimitiveType(next7.param.getType()));
                    if (next7.description != null) {
                        stringBuilder.append(' ').append(next7.description);
                    }
                    stringBuilder.append(SequenceUtils.EOL);
                }
                stringBuilder.append("    --- @return ").append(luaClassName).append(SequenceUtils.EOL);
                stringBuilder.append("    ").append(next6.name).append(" = function(");
                int i7 = 0;
                Array.ArrayIterator<ParamData> it10 = next6.params.iterator();
                while (it10.hasNext()) {
                    ParamData next8 = it10.next();
                    if (i7 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(next8.name);
                    i7++;
                }
                stringBuilder.append(") end,\n");
            }
        }
        if (this.staticMethods.size != 0) {
            stringBuilder.append(SequenceUtils.EOL);
            stringBuilder.append("    -- ────────────────────────── Static methods ───────────────────────────────\n");
        }
        Array.ArrayIterator<MethodData> it11 = this.staticMethods.iterator();
        while (it11.hasNext()) {
            MethodData next9 = it11.next();
            stringBuilder.append(SequenceUtils.EOL);
            if (next9.description != null) {
                stringBuilder.append(next9.description);
            }
            if (next9.method.isVarArgs()) {
                stringBuilder.append("    --- �� Varargs\n");
            }
            Array.ArrayIterator<ParamData> it12 = next9.params.iterator();
            while (it12.hasNext()) {
                ParamData next10 = it12.next();
                stringBuilder.append("    --- @param ").append(next10.name).append(next10.canBeNull ? TypeDescription.Generic.OfWildcardType.SYMBOL : "").append(SequenceUtils.SPACE).append(next10.typeName).append(LuaDefUtils.getLuaPrimitiveType(next10.param.getType()));
                if (next10.description != null) {
                    stringBuilder.append(SequenceUtils.SPACE).append(next10.description);
                }
                stringBuilder.append(SequenceUtils.EOL);
            }
            stringBuilder.append("    --- @return ").append(next9.returnTypeName).append(LuaDefUtils.getLuaPrimitiveType(next9.method.getReturnType()));
            if (next9.returnDescription != null) {
                stringBuilder.append(" - ").append(next9.returnDescription);
            }
            stringBuilder.append(SequenceUtils.EOL);
            stringBuilder.append("    ").append(next9.name).append(" = function(self");
            Array.ArrayIterator<ParamData> it13 = next9.params.iterator();
            while (it13.hasNext()) {
                stringBuilder.append(", ").append(it13.next().name);
            }
            stringBuilder.append(") end,\n");
        }
        stringBuilder.append("}\n");
        stringBuilder.append(SequenceUtils.EOL);
        stringBuilder.append(SequenceUtils.EOL);
        stringBuilder.append("-- ─────────────────────────────────────────────────────────────────────────────\n");
        stringBuilder.append("--                           --- Object definition ---\n");
        stringBuilder.append("-- ─────────────────────────────────────────────────────────────────────────────\n");
        stringBuilder.append(SequenceUtils.EOL);
        stringBuilder.append("--- @class ").append(luaClassName);
        if (this.superClass != null) {
            stringBuilder.append(": ").append(this.c.getLuaClassName(this.superClass, false));
        }
        stringBuilder.append(SequenceUtils.EOL);
        if (this.isSerializable) {
            stringBuilder.append("--- �� KryoSerializable\n");
        }
        switch (this.classType) {
            case 1:
                stringBuilder.append("--- ⭐ Interface\n");
                break;
            case 2:
                stringBuilder.append("--- ⭐ Interface\n--- ✨ Functional interface\n");
                break;
            case 3:
                stringBuilder.append("--- ✍ Annotation\n");
                break;
            case 4:
                stringBuilder.append("--- �� Enum\n");
                break;
            case 5:
                stringBuilder.append("--- �� Abstract class\n");
                break;
        }
        if (this.classGenericsString != null) {
            stringBuilder.append("---\n");
            stringBuilder.append("--- Generic: ").append(this.classGenericsString).append(SequenceUtils.EOL);
        }
        if (this.classDescription != null) {
            stringBuilder.append("---\n");
            stringBuilder.append(this.classDescription);
        }
        if (this.allInterfaces.size != 0) {
            stringBuilder.append("---\n");
            stringBuilder.append("--- Implements interfaces:\n");
            Array.ArrayIterator<Class<?>> it14 = this.allInterfaces.iterator();
            while (it14.hasNext()) {
                Class<?> next11 = it14.next();
                if (this.c.isClassUsed(next11)) {
                    stringBuilder.append("--- @see ").append(this.c.getLuaClassName(next11, false)).append(" ⭐\n");
                } else {
                    stringBuilder.append("---    - ").append(next11.getName()).append(" (not accessible)\n");
                }
            }
        }
        if (this.javadocUrl != null) {
            stringBuilder.append("---\n");
            stringBuilder.append("--- �� [").append(this.clazz.getSimpleName()).append(" Javadoc](").append(this.javadocUrl).append(")\n");
        }
        if (this.instanceFields.size != 0) {
            stringBuilder.append("---\n");
            Array.ArrayIterator<FieldData> it15 = this.instanceFields.iterator();
            while (it15.hasNext()) {
                FieldData next12 = it15.next();
                stringBuilder.append("--- @field ").append(next12.name).append(SequenceUtils.SPACE).append(next12.typeName).append(LuaDefUtils.getLuaPrimitiveType(next12.field.getType()));
                if (next12.generics != null) {
                    stringBuilder.append(" (").append(next12.generics).append(")");
                }
                if (next12.description != null) {
                    stringBuilder.append(' ').append(next12.description);
                }
                stringBuilder.append(SequenceUtils.EOL);
            }
        }
        stringBuilder.append(luaClassName).append(" = {\n");
        if (this.clazz == Class.class) {
            stringBuilder.append(SequenceUtils.EOL);
            stringBuilder.append("    --- Get a method handle by exact method parameter list\n");
            stringBuilder.append("    --- Makes it easier to specify which method you want to call if there are multiple methods with the same name\n");
            stringBuilder.append("    --- \n");
            stringBuilder.append("    --- Usage example:\n");
            stringBuilder.append("    ---     local handle = java.util.ArrayList.class:_findMethod(\"add\", java.int, java.lang.Object.class)\n");
            stringBuilder.append("    ---     handle(list, \"5\", someObject) -- where list is ArrayList or anything extending it\n");
            stringBuilder.append("    --- \n");
            stringBuilder.append("    --- @param name string method name\n");
            stringBuilder.append("    --- @param ... java.lang.Class parameter types of the method\n");
            stringBuilder.append("    --- @return fun(obj:java.lang.Object, ...):any|nil �� KryoSerializable\n");
            stringBuilder.append("    _findMethod = function(self, name, ...) end,\n");
        }
        Array.ArrayIterator<MethodData> it16 = this.instanceMethods.iterator();
        while (it16.hasNext()) {
            MethodData next13 = it16.next();
            stringBuilder.append(SequenceUtils.EOL);
            if (next13.description != null) {
                stringBuilder.append(next13.description);
            }
            if (next13.overridesSuperMethod) {
                stringBuilder.append("    ---- �� Override\n");
            }
            if (next13.specifiedByInterface) {
                stringBuilder.append("    ---- �� Specified by interface\n");
            }
            if (next13.method.isVarArgs()) {
                stringBuilder.append("    --- �� Varargs\n");
            }
            Array.ArrayIterator<ParamData> it17 = next13.params.iterator();
            while (it17.hasNext()) {
                ParamData next14 = it17.next();
                stringBuilder.append("    --- @param ").append(next14.name).append(next14.canBeNull ? TypeDescription.Generic.OfWildcardType.SYMBOL : "").append(SequenceUtils.SPACE).append(next14.typeName).append(LuaDefUtils.getLuaPrimitiveType(next14.param.getType()));
                if (next14.description != null) {
                    stringBuilder.append(' ').append(next14.description);
                }
                stringBuilder.append(SequenceUtils.EOL);
            }
            stringBuilder.append("    --- @return ").append(next13.returnTypeName).append(LuaDefUtils.getLuaPrimitiveType(next13.method.getReturnType()));
            if (next13.returnDescription != null) {
                stringBuilder.append(" - ").append(next13.returnDescription);
            }
            stringBuilder.append(SequenceUtils.EOL);
            stringBuilder.append("    ").append(next13.name).append(" = function(self");
            Array.ArrayIterator<ParamData> it18 = next13.params.iterator();
            while (it18.hasNext()) {
                stringBuilder.append(", ").append(it18.next().name);
            }
            stringBuilder.append(") end,\n");
        }
        stringBuilder.append("}\n");
        stringBuilder.append(SequenceUtils.EOL);
        LuaDefUtils.appendArt(StringFormatter.md5Hash(this.clazz.getName()), stringBuilder);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/ClassDefData$FieldData.class */
    public static class FieldData {
        public final Field field;
        public String name;
        public String typeName;
        public boolean canBeNull;

        @Null
        public String description;

        @Null
        public String generics;

        public FieldData(Field field) {
            this.field = field;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/ClassDefData$InnerClassData.class */
    public static class InnerClassData {
        public final Class<?> clazz;
        public String name;
        public String typeName;

        public InnerClassData(Class<?> cls) {
            this.clazz = cls;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/ClassDefData$FunctionalInterfaceProxyData.class */
    public static class FunctionalInterfaceProxyData {
        public final Method method;
        public String returnTypeName;
        public Array<ParamData> params = new Array<>();

        public FunctionalInterfaceProxyData(Method method) {
            this.method = method;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/ClassDefData$ConstructorData.class */
    public static class ConstructorData {
        public final Constructor<?> ctor;
        public String name;
        public Array<ParamData> params = new Array<>();

        @Null
        public String description;

        public ConstructorData(Constructor<?> constructor) {
            this.ctor = constructor;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/ClassDefData$MethodData.class */
    public static class MethodData {
        public final Method method;
        public String name;
        public String returnTypeName;
        public Array<ParamData> params = new Array<>();

        @Null
        public String description;

        @Null
        public String returnDescription;
        public boolean specifiedByInterface;
        public boolean overridesSuperMethod;

        public MethodData(Method method) {
            this.method = method;
        }
    }
}
