package org.a.b.h.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/b/h/a/g.class */
public final class g implements a {
    private static String a(String str) {
        Process exec;
        Runtime runtime = Runtime.getRuntime();
        if (str.startsWith("Windows 9")) {
            exec = runtime.exec("command.com /c echo %windir%");
        } else {
            exec = runtime.exec("cmd.exe /c echo %windir%");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream(), org.a.b.h.b.f4351a));
        String readLine = bufferedReader.readLine();
        bufferedReader.close();
        return readLine;
    }

    @Override // org.a.b.h.a.a
    public final List<File> a() {
        ArrayList arrayList = new ArrayList();
        String str = null;
        try {
            str = System.getProperty("env.windir");
        } catch (SecurityException unused) {
        }
        String property = System.getProperty("os.name");
        if (str == null) {
            try {
                str = a(property);
            } catch (IOException unused2) {
            } catch (SecurityException unused3) {
            }
        }
        if (str != null) {
            if (str.endsWith("/")) {
                str = str.substring(0, str.length() - 1);
            }
            File file = new File(str + File.separator + "FONTS");
            if (file.exists() && file.canRead()) {
                arrayList.add(file);
            }
            File file2 = new File(str.substring(0, 2) + File.separator + "PSFONTS");
            if (file2.exists() && file2.canRead()) {
                arrayList.add(file2);
            }
        } else {
            String str2 = property.endsWith("NT") ? "WINNT" : "WINDOWS";
            char c = 'C';
            while (true) {
                char c2 = c;
                if (c2 > 'E') {
                    break;
                }
                File file3 = new File(c2 + ":" + File.separator + str2 + File.separator + "FONTS");
                try {
                    if (file3.exists() && file3.canRead()) {
                        arrayList.add(file3);
                        break;
                    }
                } catch (SecurityException unused4) {
                }
                c = (char) (c2 + 1);
            }
            char c3 = 'C';
            while (true) {
                char c4 = c3;
                if (c4 > 'E') {
                    break;
                }
                File file4 = new File(c4 + ":" + File.separator + "PSFONTS");
                try {
                    if (file4.exists() && file4.canRead()) {
                        arrayList.add(file4);
                        break;
                    }
                } catch (SecurityException unused5) {
                }
                c3 = (char) (c4 + 1);
            }
        }
        return arrayList;
    }
}
