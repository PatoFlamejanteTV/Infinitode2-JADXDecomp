package org.a.c.h.e;

import com.a.a.a.am;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.a.b.f.al;
import org.a.b.f.ao;
import org.a.b.f.ap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/e/d.class */
public final class d extends n {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4532a = org.a.a.a.c.a(d.class);

    /* renamed from: b, reason: collision with root package name */
    private final List<a> f4533b = new ArrayList();
    private final g c;

    static /* synthetic */ org.a.b.g.d a(d dVar, String str, File file) {
        return d(str, file);
    }

    static /* synthetic */ org.a.b.f.ad c(d dVar, String str, File file) {
        return c(str, file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/e/d$a.class */
    public static class a extends i {

        /* renamed from: a, reason: collision with root package name */
        private final String f4534a;

        /* renamed from: b, reason: collision with root package name */
        private final h f4535b;
        private final org.a.c.h.e.b c;
        private final int d;
        private final int e;
        private final int f;
        private final int g;
        private final int h;
        private final z i;
        private final File j;
        private transient d k;

        /* synthetic */ a(File file, h hVar, String str, org.a.c.h.e.b bVar, int i, int i2, int i3, int i4, int i5, byte[] bArr, d dVar, byte b2) {
            this(file, hVar, str, bVar, i, i2, i3, i4, i5, bArr, dVar);
        }

        private a(File file, h hVar, String str, org.a.c.h.e.b bVar, int i, int i2, int i3, int i4, int i5, byte[] bArr, d dVar) {
            this.j = file;
            this.f4535b = hVar;
            this.f4534a = str;
            this.c = bVar;
            this.d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
            this.h = i5;
            this.i = bArr != null ? new z(bArr) : null;
            this.k = dVar;
        }

        @Override // org.a.c.h.e.i
        public final String a() {
            return this.f4534a;
        }

        @Override // org.a.c.h.e.i
        public final h b() {
            return this.f4535b;
        }

        @Override // org.a.c.h.e.i
        public final org.a.c.h.e.b c() {
            return this.c;
        }

        @Override // org.a.c.h.e.i
        public final org.a.b.b d() {
            org.a.b.b c;
            org.a.b.b a2 = this.k.c.a(this);
            if (a2 != null) {
                return a2;
            }
            switch (this.f4535b) {
                case PFB:
                    c = d.a(this.k, this.f4534a, this.j);
                    break;
                case TTF:
                    c = this.k.a(this.f4534a, this.j);
                    break;
                case OTF:
                    c = d.c(this.k, this.f4534a, this.j);
                    break;
                default:
                    throw new RuntimeException("can't happen");
            }
            if (c != null) {
                this.k.c.a(this, c);
            }
            return c;
        }

        @Override // org.a.c.h.e.i
        public final int e() {
            return this.e;
        }

        @Override // org.a.c.h.e.i
        public final int f() {
            return this.d;
        }

        @Override // org.a.c.h.e.i
        public final int g() {
            return this.f;
        }

        @Override // org.a.c.h.e.i
        public final int h() {
            return this.g;
        }

        @Override // org.a.c.h.e.i
        public final int i() {
            return this.h;
        }

        @Override // org.a.c.h.e.i
        public final z j() {
            return this.i;
        }

        @Override // org.a.c.h.e.i
        public String toString() {
            return super.toString() + SequenceUtils.SPACE + this.j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/e/d$b.class */
    public static final class b extends a {
        /* synthetic */ b(File file, h hVar, String str, byte b2) {
            this(file, hVar, str);
        }

        private b(File file, h hVar, String str) {
            super(file, hVar, str, null, 0, 0, 0, 0, 0, null, null, (byte) 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(g gVar) {
        this.c = gVar;
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<URI> it = new org.a.b.h.a.b().a().iterator();
            while (it.hasNext()) {
                arrayList.add(new File(it.next()));
            }
            if (f4532a.b()) {
                new StringBuilder("Found ").append(arrayList.size()).append(" fonts on the local system");
            }
            List<a> b2 = b(arrayList);
            if (b2 != null && !b2.isEmpty()) {
                this.f4533b.addAll(b2);
                return;
            }
            a(arrayList);
            c();
            new StringBuilder("Finished building on-disk font cache, found ").append(this.f4533b.size()).append(" fonts");
        } catch (AccessControlException unused) {
        }
    }

    private void a(List<File> list) {
        for (File file : list) {
            try {
                if (file.getPath().toLowerCase().endsWith(".ttf") || file.getPath().toLowerCase().endsWith(".otf")) {
                    b(file);
                } else if (file.getPath().toLowerCase().endsWith(".ttc") || file.getPath().toLowerCase().endsWith(".otc")) {
                    a(file);
                } else if (file.getPath().toLowerCase().endsWith(".pfb")) {
                    c(file);
                }
            } catch (IOException unused) {
                new StringBuilder("Error parsing font ").append(file.getPath());
            }
        }
    }

    private static File b() {
        String property = System.getProperty("pdfbox.fontcache");
        String str = property;
        if (property == null || !new File(str).isDirectory() || !new File(str).canWrite()) {
            String property2 = System.getProperty("user.home");
            str = property2;
            if (property2 == null || !new File(str).isDirectory() || !new File(str).canWrite()) {
                str = System.getProperty("java.io.tmpdir");
            }
        }
        return new File(str, ".pdfbox.cache");
    }

    private void c() {
        BufferedWriter bufferedWriter = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(b()));
                for (a aVar : this.f4533b) {
                    bufferedWriter.write(aVar.f4534a.trim());
                    bufferedWriter.write("|");
                    bufferedWriter.write(aVar.f4535b.toString());
                    bufferedWriter.write("|");
                    if (aVar.c != null) {
                        bufferedWriter.write(aVar.c.a() + '-' + aVar.c.b() + '-' + aVar.c.c());
                    }
                    bufferedWriter.write("|");
                    if (aVar.d >= 0) {
                        bufferedWriter.write(Integer.toHexString(aVar.d));
                    }
                    bufferedWriter.write("|");
                    if (aVar.e >= 0) {
                        bufferedWriter.write(Integer.toHexString(aVar.e));
                    }
                    bufferedWriter.write("|");
                    bufferedWriter.write(Integer.toHexString(aVar.f));
                    bufferedWriter.write("|");
                    bufferedWriter.write(Integer.toHexString(aVar.g));
                    bufferedWriter.write("|");
                    if (aVar.h >= 0) {
                        bufferedWriter.write(Integer.toHexString(aVar.h));
                    }
                    bufferedWriter.write("|");
                    if (aVar.i != null) {
                        byte[] d = aVar.i.d();
                        for (int i = 0; i < 10; i++) {
                            String hexString = Integer.toHexString(d[i]);
                            if (hexString.length() == 1) {
                                bufferedWriter.write(48);
                            }
                            bufferedWriter.write(hexString);
                        }
                    }
                    bufferedWriter.write("|");
                    bufferedWriter.write(aVar.j.getAbsolutePath());
                    bufferedWriter.newLine();
                }
                am.a(bufferedWriter);
            } catch (IOException unused) {
                am.a(bufferedWriter);
            } catch (Throwable th) {
                am.a(bufferedWriter);
                throw th;
            }
        } catch (SecurityException unused2) {
            am.a(bufferedWriter);
        }
    }

    private List<a> b(List<File> list) {
        HashSet hashSet = new HashSet();
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getAbsolutePath());
        }
        ArrayList arrayList = new ArrayList();
        File file = null;
        boolean z = false;
        try {
            File b2 = b();
            file = b2;
            z = b2.exists();
        } catch (SecurityException unused) {
        }
        if (z) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String[] split = readLine.split("\\|", 10);
                    if (split.length < 10) {
                        new StringBuilder("Incorrect line '").append(readLine).append("' in font disk cache is skipped");
                    } else {
                        org.a.c.h.e.b bVar = null;
                        int i = -1;
                        int i2 = -1;
                        int i3 = -1;
                        byte[] bArr = null;
                        String str = split[0];
                        h valueOf = h.valueOf(split[1]);
                        if (split[2].length() > 0) {
                            String[] split2 = split[2].split("-");
                            bVar = new org.a.c.h.e.b(split2[0], split2[1], Integer.parseInt(split2[2]));
                        }
                        if (split[3].length() > 0) {
                            i = (int) Long.parseLong(split[3], 16);
                        }
                        if (split[4].length() > 0) {
                            i2 = (int) Long.parseLong(split[4], 16);
                        }
                        int parseLong = (int) Long.parseLong(split[5], 16);
                        int parseLong2 = (int) Long.parseLong(split[6], 16);
                        if (split[7].length() > 0) {
                            i3 = (int) Long.parseLong(split[7], 16);
                        }
                        if (split[8].length() > 0) {
                            bArr = new byte[10];
                            for (int i4 = 0; i4 < 10; i4++) {
                                bArr[i4] = (byte) Integer.parseInt(split[8].substring(i4 << 1, (i4 << 1) + 2), 16);
                            }
                        }
                        File file2 = new File(split[9]);
                        if (!file2.exists()) {
                            new StringBuilder("Font file ").append(file2.getAbsolutePath()).append(" not found, skipped");
                        } else {
                            arrayList.add(new a(file2, valueOf, str, bVar, i, i2, parseLong, parseLong2, i3, bArr, this, (byte) 0));
                        }
                        hashSet.remove(file2.getAbsolutePath());
                    }
                }
                am.a(bufferedReader);
            } catch (IOException unused2) {
                am.a(bufferedReader);
                return null;
            } catch (Throwable th) {
                am.a(bufferedReader);
                throw th;
            }
        }
        if (!hashSet.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    private void a(File file) {
        ao aoVar = null;
        try {
            try {
                ao aoVar2 = new ao(file);
                aoVar = aoVar2;
                aoVar2.a(new e(this, file));
                aoVar.close();
            } catch (IOException unused) {
                new StringBuilder("Could not load font file: ").append(file);
                if (aoVar != null) {
                    aoVar.close();
                }
            } catch (NullPointerException unused2) {
                new StringBuilder("Could not load font file: ").append(file);
                if (aoVar != null) {
                    aoVar.close();
                }
            }
        } catch (Throwable th) {
            if (aoVar != null) {
                aoVar.close();
            }
            throw th;
        }
    }

    private void b(File file) {
        try {
            if (file.getPath().endsWith(".otf")) {
                a(new org.a.b.f.ab(false, true).b(file), file);
            } else {
                a(new al(false, true).b(file), file);
            }
        } catch (IOException unused) {
            new StringBuilder("Could not load font file: ").append(file);
        } catch (NullPointerException unused2) {
            new StringBuilder("Could not load font file: ").append(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ap apVar, File file) {
        String str;
        org.a.b.f.z j;
        try {
            if (apVar.b() != null && apVar.b().contains("|")) {
                this.f4533b.add(new b(file, h.TTF, "*skippipeinname*", (byte) 0));
                new StringBuilder("Skipping font with '|' in name ").append(apVar.b()).append(" in file ").append(file);
            } else if (apVar.b() == null) {
                this.f4533b.add(new b(file, h.TTF, "*skipnoname*", (byte) 0));
                new StringBuilder("Missing 'name' entry for PostScript name in font ").append(file);
            } else {
                if (apVar.n() == null) {
                    this.f4533b.add(new b(file, h.TTF, apVar.b(), (byte) 0));
                    return;
                }
                int h = apVar.n().h();
                int i = -1;
                int i2 = -1;
                int i3 = 0;
                int i4 = 0;
                byte[] bArr = null;
                if (apVar.l() != null) {
                    i = apVar.l().e();
                    i2 = apVar.l().w();
                    i3 = (int) apVar.l().c();
                    i4 = (int) apVar.l().d();
                    bArr = apVar.l().h();
                }
                if ((apVar instanceof org.a.b.f.ad) && ((org.a.b.f.ad) apVar).f()) {
                    str = "OTF";
                    org.a.b.b.i a2 = ((org.a.b.f.ad) apVar).a().a();
                    org.a.c.h.e.b bVar = null;
                    if (a2 instanceof org.a.b.b.a) {
                        org.a.b.b.a aVar = (org.a.b.b.a) a2;
                        bVar = new org.a.c.h.e.b(aVar.a(), aVar.e(), aVar.f());
                    }
                    this.f4533b.add(new a(file, h.OTF, apVar.b(), bVar, i2, i, i3, i4, h, bArr, this, (byte) 0));
                } else {
                    org.a.c.h.e.b bVar2 = null;
                    if (apVar.i().containsKey("gcid")) {
                        byte[] b2 = apVar.b(apVar.i().get("gcid"));
                        String str2 = new String(b2, 10, 64, org.a.c.i.a.f4651a);
                        String substring = str2.substring(0, str2.indexOf(0));
                        String str3 = new String(b2, 76, 64, org.a.c.i.a.f4651a);
                        bVar2 = new org.a.c.h.e.b(substring, str3.substring(0, str3.indexOf(0)), (b2[140] << 8) & b2[141]);
                    }
                    str = "TTF";
                    this.f4533b.add(new a(file, h.TTF, apVar.b(), bVar2, i2, i, i3, i4, h, bArr, this, (byte) 0));
                }
                if (f4532a.b() && (j = apVar.j()) != null) {
                    new StringBuilder().append(str).append(": '").append(j.d()).append("' / '").append(j.b()).append("' / '").append(j.c()).append("'");
                }
            }
        } catch (IOException unused) {
            this.f4533b.add(new b(file, h.TTF, "*skipexception*", (byte) 0));
            new StringBuilder("Could not load font file: ").append(file);
        } finally {
            apVar.close();
        }
    }

    private void c(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            org.a.b.g.d a2 = org.a.b.g.d.a(fileInputStream);
            if (a2.b() != null && a2.b().contains("|")) {
                this.f4533b.add(new b(file, h.PFB, "*skippipeinname*", (byte) 0));
                new StringBuilder("Skipping font with '|' in name ").append(a2.b()).append(" in file ").append(file);
            } else {
                this.f4533b.add(new a(file, h.PFB, a2.b(), null, -1, -1, 0, 0, -1, null, this, (byte) 0));
                if (f4532a.b()) {
                    new StringBuilder("PFB: '").append(a2.b()).append("' / '").append(a2.e()).append("' / '").append(a2.f()).append("'");
                }
            }
        } catch (IOException unused) {
            new StringBuilder("Could not load font file: ").append(file);
        } finally {
            fileInputStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ap a(String str, File file) {
        try {
            ap b2 = b(str, file);
            if (f4532a.a()) {
                new StringBuilder("Loaded ").append(str).append(" from ").append(file);
            }
            return b2;
        } catch (IOException unused) {
            new StringBuilder("Could not load font file: ").append(file);
            return null;
        } catch (NullPointerException unused2) {
            new StringBuilder("Could not load font file: ").append(file);
            return null;
        }
    }

    private static ap b(String str, File file) {
        if (file.getName().toLowerCase().endsWith(".ttc")) {
            ao aoVar = new ao(file);
            ap a2 = aoVar.a(str);
            if (a2 == null) {
                aoVar.close();
                throw new IOException("Font " + str + " not found in " + file);
            }
            return a2;
        }
        return new al(false, true).b(file);
    }

    private static org.a.b.f.ad c(String str, File file) {
        try {
            org.a.b.f.ad b2 = new org.a.b.f.ab(false, true).b(file);
            if (f4532a.a()) {
                new StringBuilder("Loaded ").append(str).append(" from ").append(file);
            }
            return b2;
        } catch (IOException unused) {
            new StringBuilder("Could not load font file: ").append(file);
            return null;
        }
    }

    private static org.a.b.g.d d(String str, File file) {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                fileInputStream = fileInputStream2;
                org.a.b.g.d a2 = org.a.b.g.d.a(fileInputStream2);
                if (f4532a.a()) {
                    new StringBuilder("Loaded ").append(str).append(" from ").append(file);
                }
                am.a((Closeable) fileInputStream);
                return a2;
            } catch (IOException unused) {
                new StringBuilder("Could not load font file: ").append(file);
                am.a((Closeable) fileInputStream);
                return null;
            }
        } catch (Throwable th) {
            am.a((Closeable) fileInputStream);
            throw th;
        }
    }

    @Override // org.a.c.h.e.n
    public final List<? extends i> a() {
        return this.f4533b;
    }
}
