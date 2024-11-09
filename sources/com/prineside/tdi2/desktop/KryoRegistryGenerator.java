package com.prineside.tdi2.desktop;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/KryoRegistryGenerator.class */
public class KryoRegistryGenerator {

    /* renamed from: a, reason: collision with root package name */
    private static final Comparator<Class<?>> f1836a = (cls, cls2) -> {
        return cls.getName().compareTo(cls2.getName());
    };

    /* JADX WARN: Multi-variable type inference failed */
    public void run() {
        ScanResult scan = new ClassGraph().enableClassInfo().enableAnnotationInfo().acceptPackages("com").scan();
        try {
            ClassInfoList classesWithAnnotation = scan.getClassesWithAnnotation(REGS.class);
            Array array = new Array(Class.class);
            Iterator it = classesWithAnnotation.iterator();
            while (it.hasNext()) {
                array.add(((ClassInfo) it.next()).loadClass());
            }
            array.sort(f1836a);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.size; i++) {
                Class cls = ((Class[]) array.items)[i];
                if (!Modifier.isPublic(cls.getModifiers())) {
                    System.err.println(cls + " is not public, can't add to kryo-registry (Proguard will change its name)");
                }
                sb.append(cls.getName()).append(SequenceUtils.EOL);
            }
            try {
                PrintStream printStream = new PrintStream(Files.newOutputStream(Paths.get("res/kryo-registry.txt", new String[0]), new OpenOption[0]), false, "UTF-8");
                try {
                    printStream.print(sb);
                    printStream.flush();
                    printStream.close();
                    System.out.println("generated kryo-registry.txt");
                    if (scan != null) {
                        scan.close();
                    }
                } catch (Throwable th) {
                    try {
                        printStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Exception e) {
                throw new IllegalStateException("Failed to write file res/kryo-registry.txt", e);
            }
        } catch (Throwable th3) {
            if (scan != null) {
                try {
                    scan.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
            }
            throw th3;
        }
    }
}
