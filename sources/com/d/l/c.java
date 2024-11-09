package com.d.l;

import com.d.d.d;
import com.d.d.e;
import com.d.d.i;
import com.d.d.s;
import com.d.j.f;
import com.d.j.g;
import com.d.m.l;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.imageio.ImageIO;

/* loaded from: infinitode-2.jar:com/d/l/c.class */
public class c implements s {
    private String d;

    /* renamed from: a, reason: collision with root package name */
    protected final LinkedHashMap<String, f> f1410a = new LinkedHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private i f1411b = new C0029c();
    private i c = this.f1411b;
    private Map<String, e> e = new HashMap(2);

    /* loaded from: infinitode-2.jar:com/d/l/c$a.class */
    public static class a implements d {

        /* renamed from: a, reason: collision with root package name */
        private InputStream f1412a;

        public a(InputStream inputStream) {
            this.f1412a = inputStream;
        }

        @Override // com.d.d.d
        public final InputStream a() {
            return this.f1412a;
        }

        @Override // com.d.d.d
        public final Reader b() {
            if (this.f1412a != null) {
                try {
                    return new InputStreamReader(this.f1412a, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    l.a("Exception when creating stream reader", e);
                    return null;
                }
            }
            return null;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/l/c$b.class */
    public static class b implements e {
        @Override // com.d.d.e
        public final d a(String str) {
            InputStream inputStream = null;
            try {
                inputStream = new URL(str).openStream();
            } catch (FileNotFoundException unused) {
                l.c("item at URI " + str + " not found");
            } catch (MalformedURLException e) {
                l.a("bad URL given: " + str, e);
            } catch (IOException e2) {
                l.a("IO problem for " + str, e2);
            }
            return new a(inputStream);
        }
    }

    public c() {
        b bVar = new b();
        this.e.put("http", bVar);
        this.e.put("https", bVar);
    }

    public final void a(Map<String, e> map) {
        this.e = map;
    }

    public final void a(i iVar) {
        this.c = iVar;
    }

    private e h(String str) {
        return this.e.get(str);
    }

    private boolean i(String str) {
        return this.e.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final InputStream g(String str) {
        String scheme;
        InputStream inputStream = null;
        try {
            scheme = new URI(str).getScheme();
        } catch (URISyntaxException e) {
            l.a("bad URL given: " + str, e);
        }
        if (i(scheme)) {
            return h(scheme).a(str).a();
        }
        try {
            inputStream = new URL(str).openStream();
        } catch (FileNotFoundException e2) {
            l.a("item at URI " + str + " not found", e2);
        } catch (MalformedURLException e3) {
            l.a("bad URL given: " + str, e3);
        } catch (IOException e4) {
            l.a("IO problem for " + str, e4);
        }
        return inputStream;
    }

    private Reader j(String str) {
        String scheme;
        InputStream inputStream = null;
        try {
            scheme = new URI(str).getScheme();
        } catch (URISyntaxException e) {
            l.a("bad URL given: " + str, e);
        }
        if (i(scheme)) {
            return h(scheme).a(str).b();
        }
        try {
            try {
                inputStream = new URL(str).openStream();
            } catch (FileNotFoundException unused) {
                l.c("item at URI " + str + " not found");
            } catch (MalformedURLException e2) {
                l.a("bad URL given: " + str, e2);
            }
        } catch (IOException e3) {
            l.a("IO problem for " + str, e3);
        }
        if (inputStream == null) {
            return null;
        }
        try {
            return new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e4) {
            l.a("Failed to create stream reader", e4);
            return null;
        }
    }

    @Override // com.d.d.s
    public final com.d.j.b a(String str) {
        String a2 = this.c.a(this.d, str);
        if (a2 == null) {
            l.e(Level.INFO, "URI resolver rejected loading CSS resource at (" + str + ")");
            return null;
        }
        return new com.d.j.b(j(a2));
    }

    @Override // com.d.d.s
    public f b(String str) {
        if (com.d.m.f.a(str)) {
            return new f(null, com.d.l.a.a(com.d.m.f.e(str)));
        }
        String a2 = this.c.a(this.d, str);
        if (a2 == null) {
            l.e(Level.INFO, "URI resolver rejected loading image resource at (" + str + ")");
            return null;
        }
        f fVar = this.f1410a.get(a2);
        if (fVar != null) {
            return fVar;
        }
        InputStream g = g(a2);
        try {
            if (g != null) {
                try {
                    BufferedImage read = ImageIO.read(g);
                    if (read == null) {
                        throw new IOException("ImageIO.read() returned null");
                    }
                    f fVar2 = new f(a2, (com.d.l.a) com.d.l.a.a(read));
                    this.f1410a.put(a2, fVar2);
                    return fVar2;
                } catch (FileNotFoundException unused) {
                    l.c("Can't read image file; image at URI '" + a2 + "' not found");
                    try {
                        g.close();
                    } catch (IOException unused2) {
                    }
                } catch (IOException e) {
                    l.a("Can't read image file; unexpected problem for URI '" + a2 + "'", e);
                    try {
                        g.close();
                    } catch (IOException unused3) {
                    }
                }
            }
            return new f(a2, null);
        } finally {
            try {
                g.close();
            } catch (IOException unused4) {
            }
        }
    }

    @Override // com.d.d.s
    public final g c(String str) {
        g a2;
        String a3 = this.c.a(this.d, str);
        if (a3 == null) {
            l.e(Level.INFO, "URI resolver rejected loading XML resource at (" + str + ")");
            return null;
        }
        try {
            Reader j = j(a3);
            if (j == null) {
                a2 = null;
            } else {
                try {
                    a2 = g.a(j);
                } catch (Throwable th) {
                    if (j != null) {
                        try {
                            j.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            g gVar = a2;
            if (j != null) {
                j.close();
            }
            return gVar;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // com.d.d.s
    public final byte[] d(String str) {
        if (com.d.m.f.b(str)) {
            return com.d.m.f.d(str);
        }
        String a2 = this.c.a(this.d, str);
        if (a2 == null) {
            l.e(Level.INFO, "URI resolver rejected loading binary resource at (" + str + ")");
            return null;
        }
        InputStream g = g(a2);
        InputStream inputStream = g;
        if (g == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[10240];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            inputStream.close();
            inputStream = null;
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            return byteArray;
        } catch (IOException unused2) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    @Override // com.d.d.s
    public final void e(String str) {
        this.d = str;
    }

    /* renamed from: com.d.l.c$c, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/l/c$c.class */
    public static class C0029c implements i {
        @Override // com.d.d.i
        public final String a(String str, String str2) {
            if (str2 == null || str2.isEmpty()) {
                return null;
            }
            try {
                URI uri = new URI(str2);
                if (uri.isAbsolute()) {
                    return uri.toString();
                }
                if (str == null) {
                    l.e(Level.WARNING, "Couldn't resolve relative URI(" + str2 + ") because no base URI was provided.");
                    return null;
                }
                if (str.startsWith("jar")) {
                    return new URL(new URL(str), str2).toString();
                }
                return new URI(str).resolve(str2).toString();
            } catch (MalformedURLException e) {
                l.a("When trying to load uri(" + str2 + ") with base jar scheme URI(" + str + "), one or both were invalid URIs.", e);
                return null;
            } catch (URISyntaxException e2) {
                l.a("When trying to load uri(" + str2 + ") with base URI(" + str + "), one or both were invalid URIs.", e2);
                return null;
            }
        }
    }

    @Override // com.d.d.s
    public final String a() {
        return this.d;
    }

    @Override // com.d.d.s
    public final String f(String str) {
        return this.c.a(a(), str);
    }

    @Override // com.d.d.s
    public final String a(String str, String str2) {
        return this.c.a(str, str2);
    }
}
