package com.d.l;

import com.d.m.f;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/* loaded from: infinitode-2.jar:com/d/l/a.class */
public abstract class a implements com.d.d.c {

    /* renamed from: a, reason: collision with root package name */
    private static final com.d.d.c f1406a = new b(0);

    public static com.d.d.c a(Image image) {
        BufferedImage a2;
        if (image == null) {
            return f1406a;
        }
        if (image instanceof BufferedImage) {
            a2 = f.a((BufferedImage) image);
        } else {
            a2 = f.a(image, 2);
        }
        return new C0028a(a2);
    }

    protected a() {
    }

    /* renamed from: com.d.l.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/l/a$a.class */
    static class C0028a extends a {

        /* renamed from: a, reason: collision with root package name */
        private BufferedImage f1407a;

        public C0028a(BufferedImage bufferedImage) {
            this.f1407a = bufferedImage;
        }

        @Override // com.d.d.c
        public final int a() {
            return this.f1407a.getWidth((ImageObserver) null);
        }

        @Override // com.d.d.c
        public final int b() {
            return this.f1407a.getHeight((ImageObserver) null);
        }

        @Override // com.d.d.c
        public final void a(int i, int i2) {
            if (i > 0 || i2 > 0) {
                int a2 = a();
                int b2 = b();
                int i3 = i;
                int i4 = i2;
                if (i3 == -1) {
                    i3 = (int) (a2 * (i4 / b2));
                }
                if (i4 == -1) {
                    i4 = (int) (b2 * (i3 / a2));
                }
                if (a2 != i3 || b2 != i4) {
                    this.f1407a = f.a(this.f1407a, i3, i4);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/d/l/a$b.class */
    static class b extends a {
        private b() {
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        static {
            f.a(1, 1);
        }

        @Override // com.d.d.c
        public final int a() {
            return 0;
        }

        @Override // com.d.d.c
        public final int b() {
            return 0;
        }

        @Override // com.d.d.c
        public final void a(int i, int i2) {
        }
    }
}
