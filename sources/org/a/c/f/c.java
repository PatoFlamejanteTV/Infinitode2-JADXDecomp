package org.a.c.f;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/f/c.class */
final class c extends BufferedOutputStream {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4431a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4432b;
    private int c;
    private boolean d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(OutputStream outputStream) {
        super(outputStream);
        this.f4431a = false;
        this.f4432b = false;
        this.c = 0;
        this.d = true;
    }

    @Override // java.io.BufferedOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        if (this.c == 0 && i2 > 10) {
            this.d = false;
            for (int i3 = 0; i3 < 10; i3++) {
                if (bArr[i3] < 9 || (bArr[i3] > 10 && bArr[i3] < 32 && bArr[i3] != 13)) {
                    this.d = true;
                    break;
                }
            }
        }
        if (this.d) {
            if (this.f4431a) {
                this.f4431a = false;
                if (!this.f4432b && i2 == 1 && bArr[i] == 10) {
                    return;
                } else {
                    super.write(13);
                }
            }
            if (this.f4432b) {
                super.write(10);
                this.f4432b = false;
            }
            if (i2 > 0) {
                if (bArr[(i + i2) - 1] == 13) {
                    this.f4431a = true;
                    i2--;
                } else if (bArr[(i + i2) - 1] == 10) {
                    this.f4432b = true;
                    i2--;
                    if (i2 > 0 && bArr[(i + i2) - 1] == 13) {
                        this.f4431a = true;
                        i2--;
                    }
                }
            }
        }
        super.write(bArr, i, i2);
        this.c += i2;
    }

    @Override // java.io.BufferedOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public final void flush() {
        if (this.f4431a && !this.f4432b) {
            super.write(13);
            this.c++;
        }
        this.f4431a = false;
        this.f4432b = false;
        super.flush();
    }
}
