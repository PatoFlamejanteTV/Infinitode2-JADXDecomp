package com.b.a.a;

import com.b.a.a.f;
import com.b.a.a.s;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/* loaded from: infinitode-2.jar:com/b/a/a/o.class */
public final class o {

    /* renamed from: a, reason: collision with root package name */
    private static final b f820a = new b(0);

    /* renamed from: b, reason: collision with root package name */
    private int f821b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private x h;
    private String i;
    private String j;
    private byte[] k;
    private int[] l;
    private y m;
    private ArrayList<com.b.a.c.i> n;

    /* loaded from: infinitode-2.jar:com/b/a/a/o$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final InputStream f822a;

        public static boolean a(char c) {
            char c2 = (char) (c - 44032);
            return c2 < 11172 && c2 % 28 == 0;
        }

        public static int a(int i, Appendable appendable) {
            try {
                int i2 = i - 44032;
                int i3 = i2 % 28;
                int i4 = i2 / 28;
                appendable.append((char) (4352 + (i4 / 21)));
                appendable.append((char) (4449 + (i4 % 21)));
                if (i3 == 0) {
                    return 2;
                }
                appendable.append((char) (i3 + 4519));
                return 3;
            } catch (IOException e) {
                throw new com.b.a.d.c(e);
            }
        }

        public a(InputStream inputStream) {
            this.f822a = inputStream;
        }

        public org.a.c.h.e.l a(boolean z) {
            return b(true);
        }

        private org.a.c.h.e.l b(boolean z) {
            String h;
            org.a.c.h.e.l lVar = new org.a.c.h.e.l();
            String h2 = h();
            if (!"StartFontMetrics".equals(h2)) {
                throw new IOException("Error: The AFM file should start with StartFontMetrics and not '" + h2 + "'");
            }
            f();
            boolean z2 = false;
            while (true) {
                h = h();
                if ("EndFontMetrics".equals(h)) {
                    break;
                }
                if ("FontName".equals(h)) {
                    lVar.c(g());
                } else if ("FullName".equals(h)) {
                    g();
                } else if ("FamilyName".equals(h)) {
                    lVar.d(g());
                } else if ("Weight".equals(h)) {
                    g();
                } else if ("FontBBox".equals(h)) {
                    org.a.b.h.a aVar = new org.a.b.h.a();
                    aVar.a(f());
                    aVar.b(f());
                    aVar.c(f());
                    aVar.d(f());
                    lVar.a(aVar);
                } else if ("Version".equals(h)) {
                    g();
                } else if ("Notice".equals(h)) {
                    g();
                } else if ("EncodingScheme".equals(h)) {
                    lVar.e(g());
                } else if ("MappingScheme".equals(h)) {
                    e();
                } else if ("EscChar".equals(h)) {
                    e();
                } else if ("CharacterSet".equals(h)) {
                    lVar.f(g());
                } else if ("Characters".equals(h)) {
                    e();
                } else if ("IsBaseFont".equals(h)) {
                    d();
                } else if ("VVector".equals(h)) {
                    float[] fArr = {f(), f()};
                } else if ("IsFixedV".equals(h)) {
                    d();
                } else if ("CapHeight".equals(h)) {
                    lVar.a(f());
                } else if ("XHeight".equals(h)) {
                    lVar.b(f());
                } else if ("Ascender".equals(h)) {
                    lVar.c(f());
                } else if ("Descender".equals(h)) {
                    lVar.d(f());
                } else if ("StdHW".equals(h)) {
                    f();
                } else if ("StdVW".equals(h)) {
                    f();
                } else if ("Comment".equals(h)) {
                    lVar.b(g());
                } else if ("UnderlinePosition".equals(h)) {
                    f();
                } else if ("UnderlineThickness".equals(h)) {
                    f();
                } else if ("ItalicAngle".equals(h)) {
                    lVar.e(f());
                } else if ("CharWidth".equals(h)) {
                    float[] fArr2 = {f(), f()};
                } else if ("IsFixedPitch".equals(h)) {
                    d();
                } else if ("StartCharMetrics".equals(h)) {
                    int e = e();
                    ArrayList arrayList = new ArrayList(e);
                    for (int i = 0; i < e; i++) {
                        arrayList.add(c());
                    }
                    String h3 = h();
                    if (!h3.equals("EndCharMetrics")) {
                        throw new IOException("Error: Expected 'EndCharMetrics' actual '" + h3 + "'");
                    }
                    z2 = true;
                    lVar.a(arrayList);
                } else if (!z && "StartComposites".equals(h)) {
                    int e2 = e();
                    for (int i2 = 0; i2 < e2; i2++) {
                        lVar.a(b());
                    }
                    String h4 = h();
                    if (!h4.equals("EndComposites")) {
                        throw new IOException("Error: Expected 'EndComposites' actual '" + h4 + "'");
                    }
                } else {
                    if (z || !"StartKernData".equals(h)) {
                        break;
                    }
                    a(lVar);
                }
            }
            if (!z || !z2) {
                throw new IOException("Unknown AFM key '" + h + "'");
            }
            return lVar;
        }

        private void a(org.a.c.h.e.l lVar) {
            while (true) {
                String h = h();
                if (!h.equals("EndKernData")) {
                    if ("StartTrackKern".equals(h)) {
                        int e = e();
                        for (int i = 0; i < e; i++) {
                            org.a.b.a.b bVar = new org.a.b.a.b();
                            e();
                            f();
                            f();
                            f();
                            f();
                            lVar.a(bVar);
                        }
                        String h2 = h();
                        if (!h2.equals("EndTrackKern")) {
                            throw new IOException("Error: Expected 'EndTrackKern' actual '" + h2 + "'");
                        }
                    } else if ("StartKernPairs".equals(h)) {
                        int e2 = e();
                        for (int i2 = 0; i2 < e2; i2++) {
                            lVar.a(a());
                        }
                        String h3 = h();
                        if (!h3.equals("EndKernPairs")) {
                            throw new IOException("Error: Expected 'EndKernPairs' actual '" + h3 + "'");
                        }
                    } else if ("StartKernPairs0".equals(h)) {
                        int e3 = e();
                        for (int i3 = 0; i3 < e3; i3++) {
                            lVar.b(a());
                        }
                        String h4 = h();
                        if (!h4.equals("EndKernPairs")) {
                            throw new IOException("Error: Expected 'EndKernPairs' actual '" + h4 + "'");
                        }
                    } else if ("StartKernPairs1".equals(h)) {
                        int e4 = e();
                        for (int i4 = 0; i4 < e4; i4++) {
                            lVar.c(a());
                        }
                        String h5 = h();
                        if (!h5.equals("EndKernPairs")) {
                            throw new IOException("Error: Expected 'EndKernPairs' actual '" + h5 + "'");
                        }
                    } else {
                        throw new IOException("Unknown kerning data type '" + h + "'");
                    }
                } else {
                    return;
                }
            }
        }

        private org.a.c.h.g.e.c a() {
            org.a.c.h.g.e.c cVar = new org.a.c.h.g.e.c();
            String h = h();
            if ("KP".equals(h)) {
                h();
                h();
                f();
                f();
            } else if ("KPH".equals(h)) {
                a(h());
                a(h());
                f();
                f();
            } else if ("KPX".equals(h)) {
                h();
                h();
                f();
            } else if ("KPY".equals(h)) {
                h();
                h();
                f();
            } else {
                throw new IOException("Error expected kern pair command actual='" + h + "'");
            }
            return cVar;
        }

        private static String a(String str) {
            if (str.length() < 2) {
                throw new IOException("Error: Expected hex string of length >= 2 not='" + str);
            }
            if (str.charAt(0) != '<' || str.charAt(str.length() - 1) != '>') {
                throw new IOException("String should be enclosed by angle brackets '" + str + "'");
            }
            String substring = str.substring(1, str.length() - 1);
            byte[] bArr = new byte[substring.length() / 2];
            for (int i = 0; i < substring.length(); i += 2) {
                try {
                    bArr[i / 2] = (byte) Integer.parseInt(Character.toString(substring.charAt(i)) + substring.charAt(i + 1), 16);
                } catch (NumberFormatException e) {
                    throw new IOException("Error parsing AFM file:" + e);
                }
            }
            return new String(bArr, org.a.b.h.b.f4351a);
        }

        private com.b.a.b.a b() {
            com.b.a.b.a aVar = new com.b.a.b.a();
            StringTokenizer stringTokenizer = new StringTokenizer(g(), " ;");
            String nextToken = stringTokenizer.nextToken();
            if (!nextToken.equals("CC")) {
                throw new IOException("Expected 'CC' actual='" + nextToken + "'");
            }
            stringTokenizer.nextToken();
            try {
                int parseInt = Integer.parseInt(stringTokenizer.nextToken());
                for (int i = 0; i < parseInt; i++) {
                    org.a.c.c.t tVar = new org.a.c.c.t();
                    String nextToken2 = stringTokenizer.nextToken();
                    if (!nextToken2.equals("PCC")) {
                        throw new IOException("Expected 'PCC' actual='" + nextToken2 + "'");
                    }
                    stringTokenizer.nextToken();
                    try {
                        Integer.parseInt(stringTokenizer.nextToken());
                        Integer.parseInt(stringTokenizer.nextToken());
                        aVar.a(tVar);
                    } catch (NumberFormatException e) {
                        throw new IOException("Error parsing AFM document:" + e);
                    }
                }
                return aVar;
            } catch (NumberFormatException e2) {
                throw new IOException("Error parsing AFM document:" + e2);
            }
        }

        private d c() {
            d dVar = new d();
            StringTokenizer stringTokenizer = new StringTokenizer(g());
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken.equals("C")) {
                        dVar.b(Integer.parseInt(stringTokenizer.nextToken()));
                        a(stringTokenizer);
                    } else if (nextToken.equals("CH")) {
                        dVar.b(Integer.parseInt(stringTokenizer.nextToken(), 16));
                        a(stringTokenizer);
                    } else if (nextToken.equals("WX")) {
                        dVar.a(Float.parseFloat(stringTokenizer.nextToken()));
                        a(stringTokenizer);
                    } else if (nextToken.equals("W0X")) {
                        Float.parseFloat(stringTokenizer.nextToken());
                        a(stringTokenizer);
                    } else if (nextToken.equals("W1X")) {
                        Float.parseFloat(stringTokenizer.nextToken());
                        a(stringTokenizer);
                    } else if (nextToken.equals("WY")) {
                        Float.parseFloat(stringTokenizer.nextToken());
                        a(stringTokenizer);
                    } else if (nextToken.equals("W0Y")) {
                        Float.parseFloat(stringTokenizer.nextToken());
                        a(stringTokenizer);
                    } else if (nextToken.equals("W1Y")) {
                        Float.parseFloat(stringTokenizer.nextToken());
                        a(stringTokenizer);
                    } else if (nextToken.equals("W")) {
                        float[] fArr = {Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())};
                        a(stringTokenizer);
                    } else if (nextToken.equals("W0")) {
                        float[] fArr2 = {Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())};
                        a(stringTokenizer);
                    } else if (nextToken.equals("W1")) {
                        float[] fArr3 = {Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())};
                        a(stringTokenizer);
                    } else if (nextToken.equals("VV")) {
                        float[] fArr4 = {Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())};
                        a(stringTokenizer);
                    } else if (nextToken.equals("N")) {
                        dVar.a(stringTokenizer.nextToken());
                        a(stringTokenizer);
                    } else if (nextToken.equals("B")) {
                        org.a.b.h.a aVar = new org.a.b.h.a();
                        aVar.a(Float.parseFloat(stringTokenizer.nextToken()));
                        aVar.b(Float.parseFloat(stringTokenizer.nextToken()));
                        aVar.c(Float.parseFloat(stringTokenizer.nextToken()));
                        aVar.d(Float.parseFloat(stringTokenizer.nextToken()));
                        a(stringTokenizer);
                    } else if (nextToken.equals("L")) {
                        org.a.b.a.a aVar2 = new org.a.b.a.a();
                        stringTokenizer.nextToken();
                        stringTokenizer.nextToken();
                        dVar.a(aVar2);
                        a(stringTokenizer);
                    } else {
                        throw new IOException("Unknown CharMetrics command '" + nextToken + "'");
                    }
                } catch (NumberFormatException e) {
                    throw new IOException("Error: Corrupt AFM document:" + e);
                }
            }
            return dVar;
        }

        private static void a(StringTokenizer stringTokenizer) {
            if (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                if (!";".equals(nextToken)) {
                    throw new IOException("Error: Expected semicolon in stream actual='" + nextToken + "'");
                }
                return;
            }
            throw new IOException("CharMetrics is missing a semicolon after a command");
        }

        private boolean d() {
            return Boolean.valueOf(h()).booleanValue();
        }

        private int e() {
            try {
                return Integer.parseInt(h());
            } catch (NumberFormatException e) {
                throw new IOException("Error parsing AFM document:" + e);
            }
        }

        private float f() {
            return Float.parseFloat(h());
        }

        private String g() {
            int i;
            StringBuilder sb = new StringBuilder(60);
            int read = this.f822a.read();
            while (true) {
                i = read;
                if (!b(i)) {
                    break;
                }
                read = this.f822a.read();
            }
            sb.append((char) i);
            int read2 = this.f822a.read();
            while (true) {
                int i2 = read2;
                if (i2 == -1 || a(i2)) {
                    break;
                }
                sb.append((char) i2);
                read2 = this.f822a.read();
            }
            return sb.toString();
        }

        private String h() {
            int i;
            StringBuilder sb = new StringBuilder(24);
            int read = this.f822a.read();
            while (true) {
                i = read;
                if (!b(i)) {
                    break;
                }
                read = this.f822a.read();
            }
            sb.append((char) i);
            int read2 = this.f822a.read();
            while (true) {
                int i2 = read2;
                if (i2 == -1 || b(i2)) {
                    break;
                }
                sb.append((char) i2);
                read2 = this.f822a.read();
            }
            return sb.toString();
        }

        private static boolean a(int i) {
            return i == 13 || i == 10;
        }

        private static boolean b(int i) {
            return i == 32 || i == 9 || i == 13 || i == 10;
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/o$c.class */
    public static final class c implements Appendable {

        /* renamed from: a, reason: collision with root package name */
        private final o f823a;

        /* renamed from: b, reason: collision with root package name */
        private final Appendable f824b;
        private final StringBuilder c;
        private final boolean d;
        private int e;
        private int f;
        private int g;
        private int h;

        /* JADX WARN: Code restructure failed: missing block: B:11:0x005b, code lost:            if (f() > 1) goto L17;     */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x005e, code lost:            r4.e = r4.h;     */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0066, code lost:            return;     */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0053, code lost:            if (r4.f > 1) goto L10;     */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public c(com.b.a.a.o r5, java.lang.Appendable r6, int r7) {
            /*
                r4 = this;
                r0 = r4
                r0.<init>()
                r0 = r4
                r1 = r5
                r0.f823a = r1
                r0 = r4
                r1 = r6
                r0.f824b = r1
                r0 = r4
                java.lang.Appendable r0 = r0.f824b
                boolean r0 = r0 instanceof java.lang.StringBuilder
                if (r0 == 0) goto L67
                r0 = r4
                r1 = 1
                r0.d = r1
                r0 = r4
                r1 = r6
                java.lang.StringBuilder r1 = (java.lang.StringBuilder) r1
                r0.c = r1
                r0 = r4
                java.lang.StringBuilder r0 = r0.c
                r1 = 5
                r0.ensureCapacity(r1)
                r0 = r4
                r1 = 0
                r0.e = r1
                r0 = r4
                java.lang.StringBuilder r0 = r0.c
                int r0 = r0.length()
                if (r0 != 0) goto L42
                r0 = r4
                r1 = 0
                r0.f = r1
                return
            L42:
                r0 = r4
                r0.d()
                r0 = r4
                r1 = r0
                int r1 = r1.f()
                r0.f = r1
                r0 = r4
                int r0 = r0.f
                r1 = 1
                if (r0 <= r1) goto L5e
            L56:
                r0 = r4
                int r0 = r0.f()
                r1 = 1
                if (r0 > r1) goto L56
            L5e:
                r0 = r4
                r1 = r0
                int r1 = r1.h
                r0.e = r1
                return
            L67:
                r0 = r4
                r1 = 0
                r0.d = r1
                r0 = r4
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r2 = r1
                r2.<init>()
                r0.c = r1
                r0 = r4
                r1 = 0
                r0.e = r1
                r0 = r4
                r1 = 0
                r0.f = r1
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.o.c.<init>(com.b.a.a.o, java.lang.Appendable, int):void");
        }

        public final int a() {
            return this.c.length();
        }

        public final StringBuilder b() {
            return this.c;
        }

        public final void a(char c) {
            this.c.setCharAt(this.c.length() - 1, c);
        }

        public final void a(int i, int i2) {
            if (this.f <= i2 || i2 == 0) {
                this.c.appendCodePoint(i);
                this.f = i2;
                if (i2 <= 1) {
                    this.e = this.c.length();
                    return;
                }
                return;
            }
            b(i, i2);
        }

        public final void a(CharSequence charSequence, int i, int i2, int i3, int i4) {
            int i5;
            if (i == i2) {
                return;
            }
            if (this.f <= i3 || i3 == 0) {
                if (i4 <= 1) {
                    this.e = this.c.length() + (i2 - i);
                } else if (i3 <= 1) {
                    this.e = this.c.length() + 1;
                }
                this.c.append(charSequence, i, i2);
                this.f = i4;
                return;
            }
            int codePointAt = Character.codePointAt(charSequence, i);
            int charCount = i + Character.charCount(codePointAt);
            b(codePointAt, i3);
            while (charCount < i2) {
                int codePointAt2 = Character.codePointAt(charSequence, charCount);
                int charCount2 = charCount + Character.charCount(codePointAt2);
                charCount = charCount2;
                if (charCount2 < i2) {
                    i5 = o.f(this.f823a.a(codePointAt2));
                } else {
                    i5 = i4;
                }
                a(codePointAt2, i5);
            }
        }

        @Override // java.lang.Appendable
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public final c append(char c) {
            this.c.append(c);
            this.f = 0;
            this.e = this.c.length();
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.lang.Appendable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c append(CharSequence charSequence) {
            if (charSequence.length() != 0) {
                this.c.append(charSequence);
                this.f = 0;
                this.e = this.c.length();
            }
            return this;
        }

        @Override // java.lang.Appendable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c append(CharSequence charSequence, int i, int i2) {
            if (i != i2) {
                this.c.append(charSequence, i, i2);
                this.f = 0;
                this.e = this.c.length();
            }
            return this;
        }

        public final void c() {
            if (this.d) {
                this.e = this.c.length();
            } else {
                try {
                    this.f824b.append(this.c);
                    this.c.setLength(0);
                    this.e = 0;
                } catch (IOException e) {
                    throw new com.b.a.d.c(e);
                }
            }
            this.f = 0;
        }

        public final c b(CharSequence charSequence, int i, int i2) {
            if (this.d) {
                this.c.append(charSequence, i, i2);
                this.e = this.c.length();
            } else {
                try {
                    this.f824b.append(this.c).append(charSequence, i, i2);
                    this.c.setLength(0);
                    this.e = 0;
                } catch (IOException e) {
                    throw new com.b.a.d.c(e);
                }
            }
            this.f = 0;
            return this;
        }

        public final void a(int i) {
            int length = this.c.length();
            this.c.delete(length - i, length);
            this.f = 0;
            this.e = this.c.length();
        }

        private void b(int i, int i2) {
            d();
            e();
            do {
            } while (f() > i2);
            if (i <= 65535) {
                this.c.insert(this.h, (char) i);
                if (i2 <= 1) {
                    this.e = this.h + 1;
                    return;
                }
                return;
            }
            this.c.insert(this.h, Character.toChars(i));
            if (i2 <= 1) {
                this.e = this.h + 2;
            }
        }

        private void d() {
            this.g = this.c.length();
        }

        private void e() {
            this.h = this.g;
            this.g = this.c.offsetByCodePoints(this.g, -1);
        }

        private int f() {
            this.h = this.g;
            if (this.e >= this.g) {
                return 0;
            }
            int codePointBefore = this.c.codePointBefore(this.g);
            this.g -= Character.charCount(codePointBefore);
            if (codePointBefore < 768) {
                return 0;
            }
            return o.f(this.f823a.a(codePointBefore));
        }
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/o$d.class */
    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private int f825a;

        /* renamed from: b, reason: collision with root package name */
        private float f826b;
        private String c;
        private List<org.a.b.a.a> d = new ArrayList();

        public static boolean a(int i) {
            return (i & 1024) == 0;
        }

        public static boolean a(CharSequence charSequence, CharSequence charSequence2) {
            if (charSequence == charSequence2) {
                return true;
            }
            int length = charSequence.length();
            if (length != charSequence2.length()) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        public int a() {
            return this.f825a;
        }

        public void b(int i) {
            this.f825a = i;
        }

        public void a(org.a.b.a.a aVar) {
            this.d.add(aVar);
        }

        public String b() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public float c() {
            return this.f826b;
        }

        public void a(float f) {
            this.f826b = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/o$b.class */
    public static final class b implements f.a {
        private b() {
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        @Override // com.b.a.a.f.a
        public final boolean a(byte[] bArr) {
            return bArr[0] == 2;
        }
    }

    static {
        new p();
    }

    private o a(ByteBuffer byteBuffer) {
        try {
            f.a(byteBuffer, 1316121906, f820a);
            int i = byteBuffer.getInt() / 4;
            if (i <= 13) {
                throw new com.b.a.d.c("Normalizer2 data: not enough indexes");
            }
            int[] iArr = new int[i];
            iArr[0] = i << 2;
            for (int i2 = 1; i2 < i; i2++) {
                iArr[i2] = byteBuffer.getInt();
            }
            this.f821b = iArr[8];
            this.c = iArr[9];
            this.d = iArr[10];
            this.e = iArr[11];
            this.f = iArr[12];
            this.g = iArr[13];
            int i3 = iArr[0];
            int i4 = iArr[1];
            this.h = x.b(byteBuffer);
            int b2 = this.h.b();
            if (b2 > i4 - i3) {
                throw new com.b.a.d.c("Normalizer2 data: not enough bytes for normTrie");
            }
            f.a(byteBuffer, (i4 - i3) - b2);
            int i5 = (iArr[2] - i4) / 2;
            if (i5 != 0) {
                this.i = f.a(byteBuffer, i5, 0);
                this.j = this.i.substring(65024 - this.g);
            }
            this.k = new byte[256];
            byteBuffer.get(this.k);
            this.l = new int[384];
            int i6 = 0;
            int i7 = 0;
            while (i7 < 384) {
                if ((i7 & 255) == 0) {
                    i6 = this.k[i7 >> 8];
                }
                if ((i6 & 1) != 0) {
                    int i8 = 0;
                    while (i8 < 32) {
                        this.l[i7] = m(i7) & 255;
                        i8++;
                        i7++;
                    }
                } else {
                    i7 += 32;
                }
                i6 >>= 1;
            }
            return this;
        } catch (IOException e) {
            throw new com.b.a.d.c(e);
        }
    }

    public final o a(String str) {
        return a(f.b(str));
    }

    public final synchronized o a() {
        int i;
        if (this.m == null) {
            v vVar = new v(0, 0);
            this.n = new ArrayList<>();
            Iterator<s.a> it = this.h.iterator();
            while (it.hasNext()) {
                s.a next = it.next();
                if (next.d) {
                    break;
                }
                int i2 = next.c;
                if (i2 != 0 && (this.d > i2 || i2 >= this.e)) {
                    for (int i3 = next.f830a; i3 <= next.f831b; i3++) {
                        int a2 = vVar.a(i3);
                        int i4 = a2;
                        if (i2 >= this.g) {
                            i4 |= Integer.MIN_VALUE;
                            if (i2 < 65024) {
                                i4 |= 1073741824;
                            }
                        } else if (i2 < this.d) {
                            i4 |= 1073741824;
                        } else {
                            int i5 = i3;
                            int i6 = i2;
                            while (true) {
                                i = i6;
                                if (this.f > i || i >= this.g) {
                                    break;
                                }
                                i5 = a(i5, i);
                                i6 = a(i5);
                            }
                            if (this.d <= i && i < this.f) {
                                char charAt = this.j.charAt(i);
                                int i7 = charAt & 31;
                                if ((charAt & 128) != 0 && i3 == i5 && (this.j.charAt(i - 1) & 255) != 0) {
                                    i4 |= Integer.MIN_VALUE;
                                }
                                if (i7 != 0) {
                                    int i8 = i + 1;
                                    int i9 = i8 + i7;
                                    int codePointAt = this.j.codePointAt(i8);
                                    a(vVar, i3, codePointAt);
                                    if (i8 >= this.e) {
                                        while (true) {
                                            int charCount = i8 + Character.charCount(codePointAt);
                                            i8 = charCount;
                                            if (charCount >= i9) {
                                                break;
                                            }
                                            codePointAt = this.j.codePointAt(i8);
                                            int a3 = vVar.a(codePointAt);
                                            if ((a3 & Integer.MIN_VALUE) == 0) {
                                                vVar.d(codePointAt, a3 | Integer.MIN_VALUE);
                                            }
                                        }
                                    }
                                }
                            } else {
                                a(vVar, i3, i5);
                            }
                        }
                        if (i4 != a2) {
                            vVar.d(i3, i4);
                        }
                    }
                }
            }
            this.m = vVar.b();
        }
        return this;
    }

    public final int a(int i) {
        return this.h.a(i);
    }

    public final int b(int i) {
        if (i < this.e || 65281 <= i) {
            return 1;
        }
        if (this.g <= i) {
            return 2;
        }
        return 0;
    }

    public final boolean c(int i) {
        return this.e <= i && i < this.g;
    }

    public final boolean d(int i) {
        return i < this.d || this.g <= i;
    }

    public final int e(int i) {
        if (i >= 65024) {
            return i & 255;
        }
        if (i < this.e || this.f <= i) {
            return 0;
        }
        return v(i);
    }

    public static int f(int i) {
        if (i >= 65024) {
            return i & 255;
        }
        return 0;
    }

    public final int g(int i) {
        if (i < 0) {
            return 0;
        }
        if (i < 384) {
            return this.l[i];
        }
        if (i > 65535 || l(i)) {
            return m(i);
        }
        return 0;
    }

    private boolean l(int i) {
        byte b2 = this.k[i >> 8];
        return (b2 == 0 || ((b2 >> ((i >> 5) & 7)) & 1) == 0) ? false : true;
    }

    private int m(int i) {
        while (true) {
            int a2 = a(i);
            if (a2 <= this.d) {
                return 0;
            }
            if (a2 >= 65024) {
                int i2 = a2 & 255;
                return i2 | (i2 << 8);
            }
            if (a2 >= this.g) {
                return 0;
            }
            if (!u(a2)) {
                char charAt = this.j.charAt(a2);
                if ((charAt & 31) == 0) {
                    return 511;
                }
                int i3 = charAt >> '\b';
                if ((charAt & 128) != 0) {
                    i3 |= this.j.charAt(a2 - 1) & 65280;
                }
                return i3;
            }
            i = a(i, a2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0063, code lost:            if (r7 >= 0) goto L20;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:            return null;     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006c, code lost:            return com.a.a.a.am.d(r7);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String h(int r6) {
        /*
            r5 = this;
            r0 = -1
            r7 = r0
        L2:
            r0 = r6
            r1 = r5
            int r1 = r1.f821b
            if (r0 < r1) goto L62
            r0 = r5
            r1 = r0
            r2 = r6
            int r1 = r1.a(r2)
            r2 = r1
            r8 = r2
            boolean r0 = r0.d(r1)
            if (r0 != 0) goto L62
            r0 = r5
            r1 = r8
            boolean r0 = r0.r(r1)
            if (r0 == 0) goto L33
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r7 = r0
            r0 = r6
            r1 = r7
            int r0 = com.b.a.a.o.a.a(r0, r1)
            r0 = r7
            java.lang.String r0 = r0.toString()
            return r0
        L33:
            r0 = r5
            r1 = r8
            boolean r0 = r0.u(r1)
            if (r0 == 0) goto L47
            r0 = r5
            r1 = r6
            r2 = r8
            int r0 = r0.a(r1, r2)
            r1 = r0
            r6 = r1
            r7 = r0
            goto L2
        L47:
            r0 = r5
            java.lang.String r0 = r0.j
            r1 = r8
            int r8 = r8 + 1
            char r0 = r0.charAt(r1)
            r1 = 31
            r0 = r0 & r1
            r7 = r0
            r0 = r5
            java.lang.String r0 = r0.j
            r1 = r8
            r2 = r1
            r3 = r7
            int r2 = r2 + r3
            java.lang.String r0 = r0.substring(r1, r2)
            return r0
        L62:
            r0 = r7
            if (r0 >= 0) goto L68
            r0 = 0
            return r0
        L68:
            r0 = r7
            java.lang.String r0 = com.a.a.a.am.d(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.o.h(int):java.lang.String");
    }

    public final boolean i(int i) {
        return this.m.a(i) >= 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x01d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01c7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(java.lang.CharSequence r7, int r8, int r9, boolean r10, boolean r11, com.b.a.a.o.c r12) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.o.a(java.lang.CharSequence, int, int, boolean, boolean, com.b.a.a.o$c):boolean");
    }

    public final boolean j(int i) {
        return t(a(i));
    }

    public final boolean a(int i, boolean z, boolean z2) {
        while (true) {
            int a2 = a(i);
            if (p(a2)) {
                return true;
            }
            if (a2 <= this.d) {
                return r(a2) && !a.a((char) i);
            }
            if (a2 >= this.e) {
                return false;
            }
            if (!u(a2)) {
                char charAt = this.j.charAt(a2);
                if ((charAt & ' ') == 0) {
                    return !z || charAt <= 511;
                }
                return false;
            }
            i = a(i, a2);
        }
    }

    public final boolean k(int i) {
        return g(i) <= 1;
    }

    private boolean n(int i) {
        return this.g <= i && i <= 65280;
    }

    private boolean o(int i) {
        return i >= this.g;
    }

    private static boolean p(int i) {
        return i == 0;
    }

    private static boolean q(int i) {
        return i == 65280;
    }

    private boolean r(int i) {
        return i == this.d;
    }

    private boolean s(int i) {
        return i < this.e;
    }

    private boolean t(int i) {
        if (i < this.d || i == 65280) {
            return true;
        }
        return this.g <= i && i <= 65024;
    }

    private boolean u(int i) {
        return i >= this.f;
    }

    private int v(int i) {
        if ((this.j.charAt(i) & 128) != 0) {
            return this.j.charAt(i - 1) & 255;
        }
        return 0;
    }

    private int a(int i, int i2) {
        return (i + i2) - ((this.g - 64) - 1);
    }

    private int w(int i) {
        if (i == 0 || 65024 <= i) {
            return -1;
        }
        int i2 = i - this.g;
        int i3 = i2;
        if (i2 < 0) {
            i3 += 65024;
        }
        return i3;
    }

    private int x(int i) {
        return (65024 - this.g) + i + 1 + (this.j.charAt(i) & 31);
    }

    private void a(CharSequence charSequence, int i, int i2, c cVar) {
        while (i < i2) {
            int codePointAt = Character.codePointAt(charSequence, i);
            i += Character.charCount(codePointAt);
            a(codePointAt, a(codePointAt), cVar);
        }
    }

    private void a(int i, int i2, c cVar) {
        int i3;
        while (!d(i2)) {
            if (r(i2)) {
                a.a(i, cVar);
                return;
            }
            if (u(i2)) {
                i = a(i, i2);
                i2 = a(i);
            } else {
                char charAt = this.j.charAt(i2);
                int i4 = charAt & 31;
                int i5 = charAt >> '\b';
                if ((charAt & 128) != 0) {
                    i3 = this.j.charAt(i2 - 1) >> '\b';
                } else {
                    i3 = 0;
                }
                int i6 = i2 + 1;
                cVar.a(this.j, i6, i6 + i4, i3, i5);
                return;
            }
        }
        cVar.a(i, f(i2));
    }

    private static int a(String str, int i, int i2) {
        char charAt;
        if (i2 < 13312) {
            int i3 = i2 << 1;
            while (true) {
                charAt = str.charAt(i);
                if (i3 <= charAt) {
                    break;
                }
                i += 2 + (charAt & 1);
            }
            if (i3 == (charAt & 32766)) {
                if ((charAt & 1) != 0) {
                    return (str.charAt(i + 1) << 16) | str.charAt(i + 2);
                }
                return str.charAt(i + 1);
            }
            return -1;
        }
        int i4 = 13312 + ((i2 >> 9) & (-2));
        int i5 = (i2 << 6) & 65535;
        while (true) {
            char charAt2 = str.charAt(i);
            if (i4 > charAt2) {
                i += 2 + (charAt2 & 1);
            } else if (i4 == (charAt2 & 32766)) {
                char charAt3 = str.charAt(i + 1);
                if (i5 > charAt3) {
                    if ((charAt2 & 32768) == 0) {
                        i += 3;
                    } else {
                        return -1;
                    }
                } else {
                    if (i5 == (charAt3 & 65472)) {
                        return ((charAt3 & '?') << 16) | str.charAt(i + 2);
                    }
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }

    private void a(c cVar, int i, boolean z) {
        char charAt;
        char charAt2;
        StringBuilder b2 = cVar.b();
        int i2 = i;
        if (i == b2.length()) {
            return;
        }
        int i3 = -1;
        int i4 = -1;
        boolean z2 = false;
        int i5 = 0;
        while (true) {
            int codePointAt = b2.codePointAt(i2);
            i2 += Character.charCount(codePointAt);
            int a2 = a(codePointAt);
            int f = f(a2);
            if (n(a2) && i3 >= 0 && (i5 < f || i5 == 0)) {
                if (q(a2)) {
                    if (codePointAt < 4519 && (charAt = (char) (b2.charAt(i4) - 4352)) < 19) {
                        int i6 = i2 - 1;
                        char c2 = (char) (44032 + (((charAt * 21) + (codePointAt - 4449)) * 28));
                        if (i2 != b2.length() && (charAt2 = (char) (b2.charAt(i2) - 4519)) < 28) {
                            i2++;
                            c2 = (char) (c2 + charAt2);
                        }
                        b2.setCharAt(i4, c2);
                        b2.delete(i6, i2);
                        i2 = i6;
                    }
                    if (i2 == b2.length()) {
                        break;
                    } else {
                        i3 = -1;
                    }
                } else {
                    int a3 = a(this.i, i3, codePointAt);
                    if (a3 >= 0) {
                        int i7 = a3 >> 1;
                        int charCount = i2 - Character.charCount(codePointAt);
                        b2.delete(charCount, i2);
                        i2 = charCount;
                        if (z2) {
                            if (i7 > 65535) {
                                b2.setCharAt(i4, com.a.a.a.am.b(i7));
                                b2.setCharAt(i4 + 1, com.a.a.a.am.c(i7));
                            } else {
                                b2.setCharAt(i4, (char) codePointAt);
                                b2.deleteCharAt(i4 + 1);
                                z2 = false;
                                i2--;
                            }
                        } else if (i7 > 65535) {
                            z2 = true;
                            b2.setCharAt(i4, com.a.a.a.am.b(i7));
                            b2.insert(i4 + 1, com.a.a.a.am.c(i7));
                            i2++;
                        } else {
                            b2.setCharAt(i4, (char) i7);
                        }
                        if (i2 == b2.length()) {
                            break;
                        } else if ((a3 & 1) != 0) {
                            i3 = x(a(i7));
                        } else {
                            i3 = -1;
                        }
                    }
                }
            }
            i5 = f;
            if (i2 == b2.length()) {
                break;
            }
            if (f == 0) {
                int w = w(a2);
                i3 = w;
                if (w >= 0) {
                    if (codePointAt <= 65535) {
                        z2 = false;
                        i4 = i2 - 1;
                    } else {
                        z2 = true;
                        i4 = i2 - 2;
                    }
                }
            } else if (z) {
                i3 = -1;
            }
        }
        cVar.c();
    }

    private boolean b(int i, int i2) {
        while (!s(i2)) {
            if (o(i2)) {
                return false;
            }
            if (u(i2)) {
                i = a(i, i2);
                i2 = a(i);
            } else {
                char charAt = this.j.charAt(i2);
                if ((charAt & 31) == 0) {
                    return false;
                }
                if ((charAt & 128) != 0 && (this.j.charAt(i2 - 1) & 65280) != 0) {
                    return false;
                }
                return s(a(Character.codePointAt(this.j, i2 + 1)));
            }
        }
        return true;
    }

    private int a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int codePointAt = Character.codePointAt(charSequence, i);
            if (b(codePointAt, this.h.a(codePointAt))) {
                break;
            }
            i += Character.charCount(codePointAt);
        }
        return i;
    }

    private void a(v vVar, int i, int i2) {
        com.b.a.c.i iVar;
        int a2 = vVar.a(i2);
        if ((a2 & 4194303) == 0 && i != 0) {
            vVar.d(i2, a2 | i);
            return;
        }
        if ((a2 & 2097152) != 0) {
            iVar = this.n.get(a2 & 2097151);
        } else {
            int i3 = a2 & 2097151;
            vVar.d(i2, (a2 & (-2097152)) | 2097152 | this.n.size());
            ArrayList<com.b.a.c.i> arrayList = this.n;
            com.b.a.c.i iVar2 = new com.b.a.c.i();
            iVar = iVar2;
            arrayList.add(iVar2);
            if (i3 != 0) {
                iVar.a(i3);
            }
        }
        iVar.a(i);
    }
}
