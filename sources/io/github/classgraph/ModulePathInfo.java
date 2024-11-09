package io.github.classgraph;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.StringUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/ModulePathInfo.class */
public class ModulePathInfo {
    private static final List<String> argSwitches = Arrays.asList("--module-path=", "--add-modules=", "--patch-module=", "--add-exports=", "--add-opens=", "--add-reads=");
    private static final List<Character> argPartSeparatorChars = Arrays.asList(Character.valueOf(File.pathSeparatorChar), ',', (char) 0, (char) 0, (char) 0, (char) 0);
    public final Set<String> modulePath = new LinkedHashSet();
    public final Set<String> addModules = new LinkedHashSet();
    public final Set<String> patchModules = new LinkedHashSet();
    public final Set<String> addExports = new LinkedHashSet();
    public final Set<String> addOpens = new LinkedHashSet();
    public final Set<String> addReads = new LinkedHashSet();
    private final List<Set<String>> fields = Arrays.asList(this.modulePath, this.addModules, this.patchModules, this.addExports, this.addOpens, this.addReads);
    private final AtomicBoolean gotRuntimeInfo = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getRuntimeInfo(ReflectionUtils reflectionUtils) {
        if (!this.gotRuntimeInfo.getAndSet(true)) {
            Class<?> classForNameOrNull = reflectionUtils.classForNameOrNull("java.lang.management.ManagementFactory");
            Object invokeStaticMethod = classForNameOrNull == null ? null : reflectionUtils.invokeStaticMethod(false, classForNameOrNull, "getRuntimeMXBean");
            List list = invokeStaticMethod == null ? null : (List) reflectionUtils.invokeMethod(false, invokeStaticMethod, "getInputArguments");
            List<String> list2 = list;
            if (list != null) {
                for (String str : list2) {
                    for (int i = 0; i < this.fields.size(); i++) {
                        String str2 = argSwitches.get(i);
                        if (str.startsWith(str2)) {
                            String substring = str.substring(str2.length());
                            Set<String> set = this.fields.get(i);
                            char charValue = argPartSeparatorChars.get(i).charValue();
                            if (charValue == 0) {
                                set.add(substring);
                            } else {
                                set.addAll(Arrays.asList(JarUtils.smartPathSplit(substring, charValue, null)));
                            }
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        if (!this.modulePath.isEmpty()) {
            sb.append("--module-path=");
            sb.append(StringUtils.join(File.pathSeparator, this.modulePath));
        }
        if (!this.addModules.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append("--add-modules=");
            sb.append(StringUtils.join(",", this.addModules));
        }
        for (String str : this.patchModules) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append("--patch-module=");
            sb.append(str);
        }
        for (String str2 : this.addExports) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append("--add-exports=");
            sb.append(str2);
        }
        for (String str3 : this.addOpens) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append("--add-opens=");
            sb.append(str3);
        }
        for (String str4 : this.addReads) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append("--add-reads=");
            sb.append(str4);
        }
        return sb.toString();
    }
}
