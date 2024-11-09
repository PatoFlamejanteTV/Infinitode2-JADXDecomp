package com.prineside.tdi2.managers.script;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/Whitelist.class */
public final class Whitelist {

    /* renamed from: b, reason: collision with root package name */
    private final EPackage f2559b = new EPackage("", null, 0);

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2558a = TLog.forClass(Whitelist.class);
    private static final StringBuilder c = new StringBuilder();
    private static final StringBuilder d = new StringBuilder();

    public static Whitelist fromFile(File file) {
        FileReader fileReader = new FileReader(file);
        try {
            Whitelist fromFile = fromFile(new BufferedReader(fileReader));
            fileReader.close();
            return fromFile;
        } catch (Throwable th) {
            try {
                fileReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static Whitelist fromFile(FileHandle fileHandle) {
        return fromFile(new BufferedReader(fileHandle.reader("UTF-8")));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x03e2  */
    /* JADX WARN: Type inference failed for: r0v15, types: [com.prineside.tdi2.managers.script.Whitelist$TreeEntry] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.prineside.tdi2.managers.script.Whitelist fromFile(java.io.BufferedReader r6) {
        /*
            Method dump skipped, instructions count: 1300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.script.Whitelist.fromFile(java.io.BufferedReader):com.prineside.tdi2.managers.script.Whitelist");
    }

    @Null
    private TreeEntry a(Class<?> cls) {
        String[] split = cls.getName().split("[.$]+");
        EPackage ePackage = this.f2559b;
        for (String str : split) {
            TreeEntry findChild = ePackage.findChild(str);
            ePackage = findChild;
            if (findChild == null) {
                return null;
            }
        }
        return ePackage;
    }

    @Null
    private EClass b(Class<?> cls) {
        TreeEntry a2 = a(cls);
        if (a2 == null || !(a2 instanceof EClass)) {
            return null;
        }
        return (EClass) a2;
    }

    public static synchronized String getMethodSignature(Method method) {
        c.setLength(0);
        c.append(method.getName()).append(':');
        int i = 0;
        for (Class<?> cls : method.getParameterTypes()) {
            if (i != 0) {
                c.append(',');
            }
            c.append(cls.getSimpleName());
            i++;
        }
        return c.toString();
    }

    public static synchronized String getConstructorSignature(Constructor<?> constructor) {
        d.setLength(0);
        int i = 0;
        for (Class<?> cls : constructor.getParameterTypes()) {
            if (i != 0) {
                d.append(',');
            }
            d.append(cls.getSimpleName());
            i++;
        }
        return d.toString();
    }

    public final boolean isFieldWhiteListed(Field field) {
        EClass b2 = b(field.getDeclaringClass());
        if (b2 == null) {
            return false;
        }
        return b2.isFieldWhitelisted(field.getName());
    }

    private boolean a(String str, Class<?> cls) {
        EClass b2 = b(cls);
        if (b2 != null && b2.isMethodWhitelisted(str)) {
            return true;
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (a(str, cls2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isMethodWhiteListed(Method method) {
        String methodSignature = getMethodSignature(method);
        EClass b2 = b(method.getDeclaringClass());
        if (b2 != null && b2.isMethodWhitelisted(methodSignature)) {
            return true;
        }
        for (Class<?> cls : method.getDeclaringClass().getInterfaces()) {
            if (a(methodSignature, cls)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isMethodWhiteListedInDeclaringClass(Method method) {
        EClass b2 = b(method.getDeclaringClass());
        if (b2 == null) {
            return false;
        }
        return b2.isMethodWhitelisted(getMethodSignature(method));
    }

    public final boolean isConstructorWhiteListed(Constructor<?> constructor) {
        EClass b2 = b(constructor.getDeclaringClass());
        if (b2 == null) {
            return false;
        }
        return b2.isConstructorWhitelisted(getConstructorSignature(constructor));
    }

    public final boolean isInterfaceProxyWhiteListed(Class<?> cls) {
        EClass b2 = b(cls);
        return b2 != null && b2.f == 1;
    }

    public final boolean isPackageBlackListed(String str) {
        String[] split = str.split("\\.");
        EPackage ePackage = this.f2559b;
        for (String str2 : split) {
            TreeEntry findChild = ePackage.findChild(str2);
            ePackage = findChild;
            if (findChild == null) {
                return false;
            }
        }
        if (!(ePackage instanceof EPackage)) {
            return false;
        }
        return ePackage.isBlacklisted();
    }

    public final boolean isClassBlackListed(Class<?> cls) {
        EClass b2 = b(cls);
        if (b2 == null) {
            return false;
        }
        return b2.isBlacklisted();
    }

    public final boolean isFieldBlackListed(Field field) {
        EClass b2 = b(field.getDeclaringClass());
        if (b2 == null) {
            return false;
        }
        return b2.isFieldBlacklisted(field.getName());
    }

    public final boolean isMethodBlackListed(Method method) {
        EClass b2 = b(method.getDeclaringClass());
        if (b2 == null) {
            return false;
        }
        return b2.isMethodBlacklisted(getMethodSignature(method));
    }

    public final boolean isConstructorBlackListed(Constructor<?> constructor) {
        EClass b2 = b(constructor.getDeclaringClass());
        if (b2 == null) {
            return false;
        }
        return b2.isConstructorBlacklisted(getConstructorSignature(constructor));
    }

    public final boolean isInterfaceProxyBlackListed(Class<?> cls) {
        EClass b2 = b(cls);
        return b2 != null && b2.f == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/Whitelist$TreeEntry.class */
    public static abstract class TreeEntry {

        /* renamed from: a, reason: collision with root package name */
        private final String f2563a;

        /* renamed from: b, reason: collision with root package name */
        @Null
        private final TreeEntry f2564b;
        private Array<TreeEntry> c;
        private boolean d;

        public TreeEntry(String str, TreeEntry treeEntry) {
            this.f2563a = str;
            this.f2564b = treeEntry;
        }

        public void addEntry(TreeEntry treeEntry) {
            if (this.c == null) {
                this.c = new Array<>(true, 1, TreeEntry.class);
            }
            this.c.add(treeEntry);
        }

        @Null
        public TreeEntry findChild(String str) {
            if (this.c == null) {
                return null;
            }
            for (int i = 0; i < this.c.size; i++) {
                if (this.c.items[i].f2563a.equals(str)) {
                    return this.c.items[i];
                }
            }
            return null;
        }

        public TreeEntry getParent() {
            return this.f2564b;
        }

        public boolean isBlacklisted() {
            return this.d;
        }

        public void setBlacklisted() {
            this.d = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/Whitelist$EPackage.class */
    public static final class EPackage extends TreeEntry {

        /* renamed from: a, reason: collision with root package name */
        private final String f2562a;

        /* synthetic */ EPackage(String str, TreeEntry treeEntry, byte b2) {
            this(str, treeEntry);
        }

        private EPackage(String str, TreeEntry treeEntry) {
            super(str, treeEntry);
            this.f2562a = str;
        }

        public final String toString() {
            return "EPackage (" + this.f2562a + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/Whitelist$EClass.class */
    public static final class EClass extends TreeEntry {
        public static final int INTERFACE_PROXY_STATE_NOT_SET = 0;
        public static final int INTERFACE_PROXY_STATE_WHITELISTED = 1;
        public static final int INTERFACE_PROXY_STATE_BLACKLISTED = 2;

        /* renamed from: a, reason: collision with root package name */
        private final String f2560a;

        /* renamed from: b, reason: collision with root package name */
        private Array<String> f2561b;
        private Array<String> c;
        private Array<String> d;
        private Array<String> e;
        private int f;

        /* synthetic */ EClass(String str, TreeEntry treeEntry, byte b2) {
            this(str, treeEntry);
        }

        private EClass(String str, TreeEntry treeEntry) {
            super(str, treeEntry);
            this.f = 0;
            this.f2560a = str;
        }

        public final int getInterfaceProxyState() {
            return this.f;
        }

        public final void setInterfaceProxyState(int i) {
            this.f = i;
        }

        public final void whitelistField(String str) {
            if (this.f2561b == null) {
                this.f2561b = new Array<>();
            }
            this.f2561b.add(str);
        }

        public final boolean isFieldWhitelisted(String str) {
            return this.f2561b != null && this.f2561b.contains(str, false);
        }

        public final boolean isFieldBlacklisted(String str) {
            return this.e != null && this.e.contains(new StringBuilder("f:").append(str).toString(), false);
        }

        public final void blacklistEntry(String str) {
            if (this.e == null) {
                this.e = new Array<>();
            }
            this.e.add(str);
        }

        public final void blacklistField(String str) {
            blacklistEntry("f:" + str);
        }

        public final void whitelistMethod(String str) {
            if (this.c == null) {
                this.c = new Array<>();
            }
            this.c.add(str);
        }

        public final boolean isMethodWhitelisted(String str) {
            return this.c != null && this.c.contains(str, false);
        }

        public final boolean isMethodBlacklisted(String str) {
            return this.e != null && this.e.contains(new StringBuilder("m:").append(str).toString(), false);
        }

        public final void blacklistMethod(String str) {
            blacklistEntry("m:" + str);
        }

        public final void whitelistConstructor(String str) {
            if (this.d == null) {
                this.d = new Array<>();
            }
            this.d.add(str);
        }

        public final boolean isConstructorWhitelisted(String str) {
            return this.d != null && this.d.contains(str, false);
        }

        public final boolean isConstructorBlacklisted(String str) {
            return this.e != null && this.e.contains(new StringBuilder("c:").append(str).toString(), false);
        }

        public final void blacklistConstructor(String str) {
            blacklistEntry("c:" + str);
        }

        public final boolean isLocalClass() {
            return getParent() instanceof EClass;
        }

        public final String toString() {
            return "EClass (" + (isLocalClass() ? "local " : "") + this.f2560a + ")";
        }
    }
}
