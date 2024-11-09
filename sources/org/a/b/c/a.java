package org.a.b.c;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/c/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4254a = org.a.a.a.c.a(a.class);
    private int f;

    /* renamed from: b, reason: collision with root package name */
    private String f4255b = null;
    private String c = null;
    private String d = null;
    private int e = 4;
    private final List<d> g = new ArrayList();
    private final Map<Integer, String> h = new HashMap();
    private final Map<Integer, Integer> i = new HashMap();
    private final List<c> j = new ArrayList();

    public final boolean a() {
        return (this.i.isEmpty() && this.j.isEmpty()) ? false : true;
    }

    public final boolean b() {
        return !this.h.isEmpty();
    }

    public final String a(int i) {
        return this.h.get(Integer.valueOf(i));
    }

    public final int a(InputStream inputStream) {
        byte[] bArr = new byte[this.f];
        inputStream.read(bArr, 0, this.e);
        for (int i = this.e - 1; i < this.f; i++) {
            int i2 = i + 1;
            Iterator<d> it = this.g.iterator();
            while (it.hasNext()) {
                if (it.next().a(bArr, i2)) {
                    return a(bArr, i2);
                }
            }
            if (i2 < this.f) {
                bArr[i2] = (byte) inputStream.read();
            }
        }
        String str = "";
        for (int i3 = 0; i3 < this.f; i3++) {
            str = str + String.format("0x%02X (%04o) ", Byte.valueOf(bArr[i3]), Byte.valueOf(bArr[i3]));
        }
        new StringBuilder("Invalid character code sequence ").append(str).append("in CMap ").append(this.f4255b);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) | (bArr[i3] & 255);
        }
        return i2;
    }

    public final int b(int i) {
        Integer num = this.i.get(Integer.valueOf(i));
        if (num != null) {
            return num.intValue();
        }
        Iterator<c> it = this.j.iterator();
        while (it.hasNext()) {
            int a2 = it.next().a((char) i);
            if (a2 != -1) {
                return a2;
            }
        }
        return 0;
    }

    private static int a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 << 8) | ((bArr[i4 + 0] + 256) % 256);
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(byte[] bArr, String str) {
        this.h.put(Integer.valueOf(a(bArr, 0, bArr.length)), str);
        SequenceUtils.SPACE.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, int i2) {
        this.i.put(Integer.valueOf(i2), Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(char c, char c2, int i) {
        c cVar = null;
        if (!this.j.isEmpty()) {
            cVar = this.j.get(this.j.size() - 1);
        }
        if (cVar == null || !cVar.a(c, c2, i)) {
            this.j.add(new c(c, c2, i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(d dVar) {
        this.g.add(dVar);
        this.f = Math.max(this.f, dVar.a());
        this.e = Math.min(this.e, dVar.a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(a aVar) {
        Iterator<d> it = aVar.g.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        this.h.putAll(aVar.h);
        this.i.putAll(aVar.i);
        this.j.addAll(aVar.j);
    }

    public final String c() {
        return this.f4255b;
    }

    public final void a(String str) {
        this.f4255b = str;
    }

    public final String d() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final String e() {
        return this.d;
    }

    public final void c(String str) {
        this.d = str;
    }

    public String toString() {
        return this.f4255b;
    }
}
