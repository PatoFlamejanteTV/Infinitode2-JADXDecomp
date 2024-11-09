package com.prineside.tdi2.desktop;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.TimeUtils;
import com.prineside.tdi2.desktop.luadef.ClassDefData;
import com.prineside.tdi2.desktop.luadef.JavadocHandler;
import com.prineside.tdi2.desktop.luadef.LuaDefUtils;
import com.prineside.tdi2.managers.ScriptManager;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.FieldInfo;
import io.github.classgraph.MethodInfo;
import io.github.classgraph.ScanResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Iterator;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/LuaDefinitionsGenerator.class */
public class LuaDefinitionsGenerator {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1837a = TLog.forClass(LuaDefinitionsGenerator.class);
    public static boolean verbose;
    public static final String DEFINITIONS_DIR = "scripts/.definitions/classes/";
    public HashSet<Class<?>> kryoRegisteredClasses;
    public Whitelist whitelist;
    public ScanResult classGraphScanResult;
    public Array<Class<?>> allClasses;
    public HashSet<Class<?>> allClassesSet;
    public HashSet<Class<?>> usedClasses;
    public JavadocHandler javadocHandler;
    public HashSet<String> handledPackageDefinitions;
    public Array<ClassDefData> filesData;

    public void loadWhitelist() {
        long millis = TimeUtils.millis();
        this.whitelist = Whitelist.fromFile(new File(ScriptManager.WHITELIST_FILE_PATH));
        f1837a.i("loadWhitelist done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    public Whitelist getWhitelist() {
        return this.whitelist;
    }

    public void prepareJavadocs() {
        if (this.usedClasses == null) {
            throw new IllegalStateException("gatherUsedClasses() has to be run first");
        }
        long millis = TimeUtils.millis();
        this.javadocHandler = new JavadocHandler();
        Array.ArrayIterator<Class<?>> it = this.allClasses.iterator();
        while (it.hasNext()) {
            Class<?> next = it.next();
            if (isClassUsed(next)) {
                this.javadocHandler.classesToFetchFor.add(next);
            }
        }
        this.javadocHandler.run();
        f1837a.i("prepareJavadocs done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    public void loadKryoRegistry() {
        long millis = TimeUtils.millis();
        this.kryoRegisteredClasses = new HashSet<>();
        FileReader fileReader = new FileReader("res/kryo-registry.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 1;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String trim = readLine.trim();
                    try {
                        this.kryoRegisteredClasses.add(Class.forName(trim));
                    } catch (Exception unused) {
                        f1837a.e("class " + trim + " not found but is listed in kryo-registry.txt at line " + i, new Object[0]);
                    }
                    i++;
                } else {
                    fileReader.close();
                    f1837a.i("loadKryoRegistry done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                fileReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public boolean isClassRegisteredInKryo(Class<?> cls) {
        return this.kryoRegisteredClasses.contains(cls);
    }

    public void gatherClasses() {
        long millis = TimeUtils.millis();
        HashSet hashSet = new HashSet();
        hashSet.add(Object.class);
        ScanResult scan = new ClassGraph().removeTemporaryFilesAfterScan().enableSystemJarsAndModules().enableClassInfo().enableFieldInfo().enableMethodInfo().enableAnnotationInfo().ignoreClassVisibility().acceptPackages(ScriptManager.getPackagesToScanFromConfig().toArray()).scan();
        Iterator it = scan.getAllClasses().iterator();
        while (it.hasNext()) {
            try {
                hashSet.add(Class.forName(((ClassInfo) it.next()).getName()));
            } catch (Throwable unused) {
            }
        }
        Array<Class<?>> filterClasses = ReflectionUtils.LuaRelated.filterClasses(hashSet, null);
        this.classGraphScanResult = scan;
        f1837a.i("found " + filterClasses.size + " classes", new Object[0]);
        this.allClasses = filterClasses;
        this.allClasses.sort((cls, cls2) -> {
            return cls.getSimpleName().compareTo(cls2.getSimpleName());
        });
        this.allClassesSet = new HashSet<>();
        Array.ArrayIterator<Class<?>> it2 = this.allClasses.iterator();
        while (it2.hasNext()) {
            this.allClassesSet.add(it2.next());
        }
        f1837a.i("gatherClasses done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    public boolean hasNullAnnotation(Field field) {
        FieldInfo declaredFieldInfo;
        ClassInfo classInfo = this.classGraphScanResult.getClassInfo(field.getDeclaringClass().getName());
        if (classInfo != null && (declaredFieldInfo = classInfo.getDeclaredFieldInfo(field.getName())) != null) {
            return declaredFieldInfo.hasAnnotation(Null.class);
        }
        return false;
    }

    public boolean hasNullAnnotation(Constructor<?> constructor, int i) {
        ClassInfo classInfo = this.classGraphScanResult.getClassInfo(constructor.getDeclaringClass().getName());
        if (classInfo != null) {
            Iterator it = classInfo.getMethodInfo(MethodDescription.CONSTRUCTOR_INTERNAL_NAME).iterator();
            while (it.hasNext()) {
                MethodInfo methodInfo = (MethodInfo) it.next();
                if (methodInfo.loadClassAndGetConstructor().equals(constructor)) {
                    return methodInfo.getParameterInfo()[i].hasAnnotation(Null.class);
                }
                continue;
            }
            return false;
        }
        return false;
    }

    public boolean hasNullAnnotation(Method method, int i) {
        ClassInfo classInfo = this.classGraphScanResult.getClassInfo(method.getDeclaringClass().getName());
        if (classInfo != null) {
            Iterator it = classInfo.getMethodInfo(method.getName()).iterator();
            while (it.hasNext()) {
                MethodInfo methodInfo = (MethodInfo) it.next();
                if (methodInfo.loadClassAndGetMethod().equals(method)) {
                    return methodInfo.getParameterInfo()[i].hasAnnotation(Null.class);
                }
                continue;
            }
            return false;
        }
        return false;
    }

    public static void deleteDefinitionFiles() {
        long millis = TimeUtils.millis();
        try {
            Files.walkFileTree(Paths.get(DEFINITIONS_DIR, new String[0]), new SimpleFileVisitor<Path>() { // from class: com.prineside.tdi2.desktop.LuaDefinitionsGenerator.1
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) {
                    Files.delete(path);
                    return FileVisitResult.CONTINUE;
                }

                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult postVisitDirectory(Path path, IOException iOException) {
                    Files.delete(path);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Throwable unused) {
        }
        f1837a.i("deleteDefinitionFiles done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    public void gatherUsedClasses() {
        boolean z;
        long millis = TimeUtils.millis();
        if (this.allClasses == null) {
            throw new IllegalStateException("allClasses is null - call gatherClasses() first");
        }
        if (getWhitelist() == null) {
            throw new IllegalStateException("whitelist is null - call loadWhitelist() first");
        }
        this.usedClasses = new HashSet<>();
        Array.ArrayIterator<Class<?>> it = this.allClasses.iterator();
        while (it.hasNext()) {
            Class<?> next = it.next();
            if (next.isInterface()) {
                if (getWhitelist().isInterfaceProxyWhiteListed(next)) {
                    a(next, "whitelisted proxy");
                }
            } else {
                Array<Constructor<?>> gatherConstructorsCached = LuaDefUtils.gatherConstructorsCached(next);
                for (int i = 0; i < gatherConstructorsCached.size; i++) {
                    Constructor<?> constructor = gatherConstructorsCached.items[i];
                    if (getWhitelist().isConstructorWhiteListed(constructor)) {
                        a(next, "has whitelisted ctor");
                        for (Parameter parameter : constructor.getParameters()) {
                            if (this.allClassesSet.contains(parameter.getType())) {
                                a(parameter.getType(), "used as a parameter to " + constructor + "'s ctor");
                            }
                        }
                    }
                }
            }
            Array<Field> gatherFieldsCached = LuaDefUtils.gatherFieldsCached(next);
            for (int i2 = 0; i2 < gatherFieldsCached.size; i2++) {
                Field field = gatherFieldsCached.items[i2];
                if (getWhitelist().isFieldWhiteListed(field)) {
                    a(next, "has whitelisted field");
                    if (this.allClassesSet.contains(field.getType())) {
                        a(field.getType(), "used as a field type in " + next);
                    }
                }
            }
            Array<Method> gatherMethodsCached = LuaDefUtils.gatherMethodsCached(next);
            for (int i3 = 0; i3 < gatherMethodsCached.size; i3++) {
                Method method = gatherMethodsCached.items[i3];
                if (getWhitelist().isMethodWhiteListedInDeclaringClass(method)) {
                    a(next, "has whitelisted method: " + method);
                    for (Parameter parameter2 : method.getParameters()) {
                        if (this.allClassesSet.contains(parameter2.getType())) {
                            a(parameter2.getType(), "used as a parameter in method " + method);
                        }
                    }
                    if (this.allClassesSet.contains(method.getReturnType())) {
                        a(method.getReturnType(), "return type of method " + method);
                    }
                }
            }
        }
        do {
            z = false;
            for (Class cls : (Class[]) this.usedClasses.toArray(new Class[0])) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass != null && this.allClassesSet.contains(superclass) && a(superclass, "super class of " + cls)) {
                    z = true;
                }
            }
        } while (z);
        f1837a.i("gatherUsedClasses done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    private boolean a(Class<?> cls, String str) {
        if (cls.isArray()) {
            return a(cls.getComponentType(), str);
        }
        if (!this.usedClasses.contains(cls)) {
            if (getWhitelist().isClassBlackListed(cls)) {
                f1837a.w("Class is blacklisted but is marked as used\n    class: " + cls + "\n    reason: " + str, new Object[0]);
                return false;
            }
            String[] split = cls.getName().split("\\.");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < split.length - 1; i++) {
                if (i != 0) {
                    stringBuilder.append('.');
                }
                stringBuilder.append(split[i]);
                if (getWhitelist().isPackageBlackListed(stringBuilder.toString())) {
                    f1837a.w("Package \"" + ((Object) stringBuilder) + "\" is blacklisted but its class is marked as used\n    class: " + cls + "\n    reason: " + str, new Object[0]);
                    return false;
                }
            }
            if (verbose) {
                f1837a.i("- marked as used: " + cls + ", reason: " + str, new Object[0]);
            }
            this.usedClasses.add(cls);
            return true;
        }
        return false;
    }

    public boolean isClassUsed(Class<?> cls) {
        return this.usedClasses.contains(cls);
    }

    public void generatePackageDefinitions() {
        long millis = TimeUtils.millis();
        if (this.allClasses == null) {
            throw new IllegalStateException("allClasses is null - call gatherClasses() first");
        }
        this.handledPackageDefinitions = new HashSet<>();
        Array.ArrayIterator<Class<?>> it = this.allClasses.iterator();
        while (it.hasNext()) {
            Class<?> next = it.next();
            if (isClassUsed(next)) {
                String[] split = next.getName().split("\\.");
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < split.length - 1; i++) {
                    if (i != 0) {
                        stringBuilder.append(".");
                    }
                    stringBuilder.append(split[i]);
                    String stringBuilder2 = stringBuilder.toString();
                    if (!this.handledPackageDefinitions.contains(stringBuilder2)) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("---@meta\n\n");
                        stringBuilder3.append(stringBuilder2).append(" = {}\n");
                        if (stringBuilder2.equals("java")) {
                            stringBuilder3.append("-- These fields are only used for an easier access to Java's primitive types\n");
                            stringBuilder3.append("-- They can not be used as a data type in docs - use Lua LS data types instead:\n");
                            stringBuilder3.append("-- - boolean\n");
                            stringBuilder3.append("-- - number\n");
                            stringBuilder3.append("-- - integer\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.int = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.byte = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.short = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.long = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.float = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.double = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.boolean = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                            stringBuilder3.append("---@type java.lang.Class\n");
                            stringBuilder3.append("java.char = nil\n");
                            stringBuilder3.append(SequenceUtils.EOL);
                        }
                        LuaDefUtils.writeFile(DEFINITIONS_DIR + stringBuilder2.replaceAll("\\.", "/") + "/package.lua", stringBuilder3.toString());
                        this.handledPackageDefinitions.add(stringBuilder2);
                    }
                }
            }
        }
        f1837a.i("generatePackageDefinitions done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    public void generateFilesData() {
        long millis = TimeUtils.millis();
        this.filesData = new Array<>();
        Array.ArrayIterator<Class<?>> it = this.allClasses.iterator();
        while (it.hasNext()) {
            Class<?> next = it.next();
            if (!this.usedClasses.contains(next)) {
                if (verbose) {
                    f1837a.i("skip " + next + " - not used", new Object[0]);
                }
            } else {
                ClassDefData classDefData = new ClassDefData(this, next);
                classDefData.prepare();
                this.filesData.add(classDefData);
            }
        }
        f1837a.i("generateFilesData done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    public void writeFilesDataToDisk() {
        long millis = TimeUtils.millis();
        Array.ArrayIterator<ClassDefData> it = this.filesData.iterator();
        while (it.hasNext()) {
            ClassDefData next = it.next();
            StringBuilder stringBuilder = new StringBuilder();
            next.printFileData(stringBuilder);
            LuaDefUtils.writeFile(DEFINITIONS_DIR + next.filePath + ".lua", stringBuilder.toString());
        }
        f1837a.i("writeFilesDataToDisk done in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c5, code lost:            throw new java.lang.IllegalArgumentException("Invalid definition at line " + r12 + " in res/luaj/forced-class-aliases.txt");     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeClassListsToDisk() {
        /*
            Method dump skipped, instructions count: 1161
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.desktop.LuaDefinitionsGenerator.writeClassListsToDisk():void");
    }

    public void runEverything() {
        loadKryoRegistry();
        loadWhitelist();
        gatherClasses();
        deleteDefinitionFiles();
        gatherUsedClasses();
        prepareJavadocs();
        generatePackageDefinitions();
        generateFilesData();
        writeFilesDataToDisk();
        writeClassListsToDisk();
        f1837a.i("all done", new Object[0]);
    }

    @Null
    public String getLuaClassName(Class<?> cls, boolean z) {
        return a(cls, 0, z);
    }

    @Null
    private String a(Class<?> cls, int i, boolean z) {
        if (cls.isArray()) {
            return a(cls.getComponentType(), i + 1, z);
        }
        StringBuilder stringBuilder = new StringBuilder();
        String str = z ? LuaDefUtils.DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.get(cls) : LuaDefUtils.DEFAULT_LUA_CLASS_NAMES.get(cls);
        String str2 = str;
        if (str == null) {
            if (this.usedClasses.contains(cls)) {
                str2 = cls.getName().replace("$", JavaConstant.Dynamic.DEFAULT_NAME);
            } else {
                return null;
            }
        }
        stringBuilder.append(str2);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("[]");
        }
        return stringBuilder.toString();
    }
}
