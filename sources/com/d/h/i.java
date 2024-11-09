package com.d.h;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/* loaded from: infinitode-2.jar:com/d/h/i.class */
public final class i implements com.d.d.c {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f1241a;

    /* renamed from: b, reason: collision with root package name */
    private final String f1242b;
    private float c;
    private float d;
    private final boolean e;
    private org.a.c.h.f.c.b f;

    public i(byte[] bArr, String str) {
        this.f1241a = bArr;
        this.f1242b = str;
        ImageInputStream imageInputStream = null;
        ImageReader imageReader = null;
        try {
            ImageInputStream createImageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(this.f1241a));
            Iterator imageReaders = ImageIO.getImageReaders(createImageInputStream);
            if (imageReaders.hasNext()) {
                ImageReader imageReader2 = (ImageReader) imageReaders.next();
                imageReader2.setInput(createImageInputStream);
                this.c = imageReader2.getWidth(0);
                this.d = imageReader2.getHeight(0);
                String formatName = imageReader2.getFormatName();
                this.e = formatName.equalsIgnoreCase("jpeg") || formatName.equalsIgnoreCase("jpg") || formatName.equalsIgnoreCase("jfif");
                if (createImageInputStream != null) {
                    createImageInputStream.close();
                }
                if (imageReader2 != null) {
                    imageReader2.dispose();
                    return;
                }
                return;
            }
            com.d.m.l.e(Level.WARNING, "Unrecognized image format for: " + str);
            throw new IOException("Unrecognized Image format");
        } catch (Throwable th) {
            if (0 != 0) {
                imageInputStream.close();
            }
            if (0 != 0) {
                imageReader.dispose();
            }
            throw th;
        }
    }

    public i(byte[] bArr, String str, float f, float f2, boolean z, org.a.c.h.f.c.b bVar) {
        this.f1241a = bArr;
        this.f1242b = str;
        this.c = f;
        this.d = f2;
        this.e = z;
        this.f = bVar;
    }

    @Override // com.d.d.c
    public final int a() {
        return (int) this.c;
    }

    @Override // com.d.d.c
    public final int b() {
        return (int) this.d;
    }

    @Override // com.d.d.c
    public final void a(int i, int i2) {
        float f;
        float f2;
        if (i != -1) {
            f = i;
            f2 = (i2 != -1 || this.c == 0.0f) ? i2 : (int) ((f / this.c) * this.d);
        } else if (i2 != -1) {
            f2 = i2;
            f = this.d != 0.0f ? (int) ((f2 / this.d) * this.c) : 0.0f;
        } else {
            f = this.c;
            f2 = this.d;
        }
        this.c = f;
        this.d = f2;
    }

    public final byte[] c() {
        return this.f1241a;
    }

    public final void d() {
        this.f1241a = null;
    }

    public final org.a.c.h.f.c.b e() {
        return this.f;
    }

    public final void a(org.a.c.h.f.c.b bVar) {
        this.f = bVar;
    }

    public final String f() {
        return this.f1242b;
    }

    public final boolean g() {
        return this.e;
    }
}
