package org.a.c.h.c;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.a.c.b.s;

/* loaded from: infinitode-2.jar:org/a/c/h/c/p.class */
public final class p extends k {
    private static final org.a.a.a.a c = org.a.a.a.c.a(p.class);
    private static final byte[] d = {40, -65, 78, 94, 78, 117, -118, 65, 100, 0, 78, 86, -1, -6, 1, 8, 46, 46, 0, -74, -48, 104, 62, Byte.MIN_VALUE, 47, 12, -87, -2, 100, 83, 105, 122};
    private static final String[] e = {"SHA-256", "SHA-384", "SHA-512"};
    private o f;

    private int c() {
        if (this.f4506a == 40) {
            return 1;
        }
        if (this.f4506a == 128 && this.f.d()) {
            return 4;
        }
        if (this.f4506a == 256) {
            return 5;
        }
        return 2;
    }

    private int a(int i) {
        if (i < 2 && !this.f.a().l()) {
            return 2;
        }
        if (i == 5) {
            return 6;
        }
        if (i == 4) {
            return 4;
        }
        if (i == 2 || i == 3 || this.f.a().l()) {
            return 3;
        }
        return 4;
    }

    @Override // org.a.c.h.c.k
    public final void a(d dVar, org.a.c.b.a aVar, a aVar2) {
        c m;
        byte[] bytes;
        if (!(aVar2 instanceof n)) {
            throw new IOException("Decryption material is not compatible with the document");
        }
        if (dVar.c() >= 4) {
            b(dVar.o());
            a(dVar.o());
        }
        a(dVar.l());
        String a2 = ((n) aVar2).a();
        String str = a2;
        if (a2 == null) {
            str = "";
        }
        int k = dVar.k();
        int e2 = dVar.e();
        int d2 = dVar.c() == 1 ? 5 : dVar.d() / 8;
        byte[] a3 = a(aVar);
        boolean l = dVar.l();
        byte[] h = dVar.h();
        byte[] g = dVar.g();
        byte[] bArr = null;
        byte[] bArr2 = null;
        Charset charset = org.a.c.i.a.d;
        if (e2 == 6 || e2 == 5) {
            charset = org.a.c.i.a.f;
            bArr = dVar.j();
            bArr2 = dVar.i();
        }
        if (e2 == 6) {
            str = j.a(str);
        }
        if (a(str.getBytes(charset), h, g, k, a3, e2, d2, l)) {
            a(j.d());
            if (e2 == 6 || e2 == 5) {
                bytes = str.getBytes(charset);
            } else {
                bytes = a(str.getBytes(charset), g, e2, d2);
            }
            this.f4507b = a(bytes, g, h, bArr2, bArr, k, a3, e2, d2, l, true);
        } else if (b(str.getBytes(charset), h, g, k, a3, e2, d2, l)) {
            j jVar = new j(k);
            jVar.k();
            a(jVar);
            this.f4507b = a(str.getBytes(charset), g, h, bArr2, bArr, k, a3, e2, d2, l, false);
        } else {
            throw new b("Cannot decrypt PDF, the password is incorrect");
        }
        if (e2 == 6 || e2 == 5) {
            a(dVar, k, l);
        }
        if ((dVar.c() == 4 || dVar.c() == 5) && (m = dVar.m()) != null) {
            org.a.c.b.j b2 = m.b();
            b(org.a.c.b.j.d.equals(b2) || org.a.c.b.j.e.equals(b2));
        }
    }

    private static byte[] a(org.a.c.b.a aVar) {
        byte[] bArr;
        if (aVar != null && aVar.b() > 0) {
            bArr = ((s) aVar.a(0)).c();
        } else {
            bArr = new byte[0];
        }
        return bArr;
    }

    private void a(d dVar, int i, boolean z) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, new SecretKeySpec(this.f4507b, "AES"));
            byte[] doFinal = cipher.doFinal(dVar.p());
            int i2 = (doFinal[0] & 255) | ((doFinal[1] & 255) << 8) | ((doFinal[2] & 255) << 16) | ((doFinal[3] & 255) << 24);
            if (i2 != i) {
                new StringBuilder("Verification of permissions failed (").append(String.format("%08X", Integer.valueOf(i2))).append(" != ").append(String.format("%08X", Integer.valueOf(i))).append(")");
            }
        } catch (GeneralSecurityException e2) {
            d();
            throw new IOException(e2);
        }
    }

    @Override // org.a.c.h.c.k
    public final void a(org.a.c.h.b bVar) {
        d d2 = bVar.d();
        d dVar = d2;
        if (d2 == null) {
            dVar = new d();
        }
        int c2 = c();
        int a2 = a(c2);
        dVar.a("Standard");
        dVar.a(c2);
        if (c2 != 4 && c2 != 5) {
            dVar.q();
        }
        dVar.c(a2);
        dVar.b(this.f4506a);
        String b2 = this.f.b();
        String c3 = this.f.c();
        if (b2 == null) {
            b2 = "";
        }
        if (c3 == null) {
            c3 = "";
        }
        if (b2.isEmpty()) {
            b2 = c3;
        }
        int f = this.f.a().f();
        dVar.d(f);
        int i = this.f4506a / 8;
        if (a2 == 6) {
            a(j.b(b2), j.b(c3), dVar, f);
        } else {
            a(b2, c3, dVar, f, bVar, a2, i);
        }
        bVar.a(dVar);
        bVar.a().b(dVar.f());
    }

    private void a(String str, String str2, d dVar, int i) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            this.f4507b = new byte[32];
            secureRandom.nextBytes(this.f4507b);
            byte[] b2 = b(str2.getBytes(org.a.c.i.a.f));
            byte[] bArr = new byte[8];
            byte[] bArr2 = new byte[8];
            secureRandom.nextBytes(bArr);
            secureRandom.nextBytes(bArr2);
            byte[] d2 = d(b(a(b2, bArr), b2, null), bArr, bArr2);
            cipher.init(1, new SecretKeySpec(b(a(b2, bArr2), b2, null), "AES"), new IvParameterSpec(new byte[16]));
            byte[] doFinal = cipher.doFinal(this.f4507b);
            byte[] b3 = b(str.getBytes(org.a.c.i.a.f));
            byte[] bArr3 = new byte[8];
            byte[] bArr4 = new byte[8];
            secureRandom.nextBytes(bArr3);
            secureRandom.nextBytes(bArr4);
            byte[] d3 = d(b(d(b3, bArr3, d2), b3, d2), bArr3, bArr4);
            cipher.init(1, new SecretKeySpec(b(d(b3, bArr4, d2), b3, d2), "AES"), new IvParameterSpec(new byte[16]));
            byte[] doFinal2 = cipher.doFinal(this.f4507b);
            dVar.b(d2);
            dVar.d(doFinal);
            dVar.a(d3);
            dVar.c(doFinal2);
            a(dVar, org.a.c.b.j.e);
            byte[] bArr5 = new byte[16];
            bArr5[0] = (byte) i;
            bArr5[1] = (byte) (i >>> 8);
            bArr5[2] = (byte) (i >>> 16);
            bArr5[3] = (byte) (i >>> 24);
            bArr5[4] = -1;
            bArr5[5] = -1;
            bArr5[6] = -1;
            bArr5[7] = -1;
            bArr5[8] = 84;
            bArr5[9] = 97;
            bArr5[10] = 100;
            bArr5[11] = 98;
            for (int i2 = 12; i2 <= 15; i2++) {
                bArr5[i2] = (byte) secureRandom.nextInt();
            }
            cipher.init(1, new SecretKeySpec(this.f4507b, "AES"), new IvParameterSpec(new byte[16]));
            dVar.e(cipher.doFinal(bArr5));
        } catch (GeneralSecurityException e2) {
            d();
            throw new IOException(e2);
        }
    }

    private void a(String str, String str2, d dVar, int i, org.a.c.h.b bVar, int i2, int i3) {
        org.a.c.b.a e2 = bVar.a().e();
        org.a.c.b.a aVar = e2;
        if (e2 == null || aVar.b() < 2) {
            MessageDigest a2 = j.a();
            a2.update(BigInteger.valueOf(System.currentTimeMillis()).toByteArray());
            a2.update(str.getBytes(org.a.c.i.a.d));
            a2.update(str2.getBytes(org.a.c.i.a.d));
            a2.update(bVar.a().toString().getBytes(org.a.c.i.a.d));
            s sVar = new s(a2.digest(toString().getBytes(org.a.c.i.a.d)));
            org.a.c.b.a aVar2 = new org.a.c.b.a();
            aVar = aVar2;
            aVar2.a((org.a.c.b.b) sVar);
            aVar.a((org.a.c.b.b) sVar);
            bVar.a().a(aVar);
        }
        s sVar2 = (s) aVar.a(0);
        byte[] b2 = b(str.getBytes(org.a.c.i.a.d), str2.getBytes(org.a.c.i.a.d), i2, i3);
        byte[] a3 = a(str2.getBytes(org.a.c.i.a.d), b2, i, sVar2.c(), i2, i3, true);
        this.f4507b = a(str2.getBytes(org.a.c.i.a.d), b2, null, null, null, i, sVar2.c(), i2, i3, true, false);
        dVar.a(b2);
        dVar.b(a3);
        if (i2 == 4) {
            a(dVar, org.a.c.b.j.d);
        }
    }

    private void a(d dVar, org.a.c.b.j jVar) {
        c cVar = new c();
        cVar.a(jVar);
        cVar.a(this.f4506a);
        dVar.a(cVar);
        dVar.a(org.a.c.b.j.du);
        dVar.b(org.a.c.b.j.du);
        b(true);
    }

    private boolean a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) {
        byte[] a2;
        if (i2 == 6 || i2 == 5) {
            byte[] b2 = b(bArr);
            byte[] bArr5 = new byte[32];
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr3, 0, bArr5, 0, 32);
            System.arraycopy(bArr3, 32, bArr6, 0, 8);
            if (i2 == 5) {
                a2 = c(b2, bArr6, bArr2);
            } else {
                a2 = a(b2, bArr6, bArr2);
            }
            return Arrays.equals(a2, bArr5);
        }
        return b(a(bArr, bArr3, i2, i3), bArr2, bArr3, i, bArr4, i2, i3, z);
    }

    private byte[] a(byte[] bArr, byte[] bArr2, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] a2 = a(bArr, i, i2);
        if (i == 2) {
            a(a2, bArr2, byteArrayOutputStream);
        } else if (i == 3 || i == 4) {
            byte[] bArr3 = new byte[a2.length];
            byte[] bArr4 = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
            for (int i3 = 19; i3 >= 0; i3--) {
                System.arraycopy(a2, 0, bArr3, 0, a2.length);
                for (int i4 = 0; i4 < bArr3.length; i4++) {
                    bArr3[i4] = (byte) (bArr3[i4] ^ ((byte) i3));
                }
                byteArrayOutputStream.reset();
                a(bArr3, bArr4, byteArrayOutputStream);
                bArr4 = byteArrayOutputStream.toByteArray();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i, byte[] bArr6, int i2, int i3, boolean z, boolean z2) {
        if (i2 == 6 || i2 == 5) {
            return a(bArr, z2, bArr2, bArr3, bArr4, bArr5, i2);
        }
        return a(bArr, bArr2, i, bArr6, z, i3, i2);
    }

    private byte[] a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, boolean z, int i2, int i3) {
        byte[] a2 = a(bArr);
        MessageDigest a3 = j.a();
        a3.update(a2);
        a3.update(bArr2);
        a3.update((byte) i);
        a3.update((byte) (i >>> 8));
        a3.update((byte) (i >>> 16));
        a3.update((byte) (i >>> 24));
        a3.update(bArr3);
        if (i3 == 4 && !z) {
            a3.update(new byte[]{-1, -1, -1, -1});
        }
        byte[] digest = a3.digest();
        if (i3 == 3 || i3 == 4) {
            for (int i4 = 0; i4 < 50; i4++) {
                a3.update(digest, 0, i2);
                digest = a3.digest();
            }
        }
        byte[] bArr4 = new byte[i2];
        System.arraycopy(digest, 0, bArr4, 0, i2);
        return bArr4;
    }

    private byte[] a(byte[] bArr, boolean z, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i) {
        byte[] a2;
        byte[] bArr6;
        if (z) {
            byte[] bArr7 = new byte[8];
            System.arraycopy(bArr2, 40, bArr7, 0, 8);
            if (i == 5) {
                a2 = c(bArr, bArr7, bArr3);
            } else {
                a2 = a(bArr, bArr7, bArr3);
            }
            bArr6 = bArr4;
        } else {
            byte[] bArr8 = new byte[8];
            System.arraycopy(bArr3, 40, bArr8, 0, 8);
            if (i == 5) {
                a2 = c(bArr, bArr8, null);
            } else {
                a2 = a(bArr, bArr8, (byte[]) null);
            }
            bArr6 = bArr5;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(2, new SecretKeySpec(a2, "AES"), new IvParameterSpec(new byte[16]));
            return cipher.doFinal(bArr6);
        } catch (GeneralSecurityException e2) {
            d();
            throw new IOException(e2);
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, int i3, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] a2 = a(bArr, bArr2, null, null, null, i, bArr3, i2, i3, z, true);
        if (i2 == 2) {
            a(a2, d, byteArrayOutputStream);
        } else if (i2 == 3 || i2 == 4) {
            MessageDigest a3 = j.a();
            a3.update(d);
            a3.update(bArr3);
            byteArrayOutputStream.write(a3.digest());
            byte[] bArr4 = new byte[a2.length];
            for (int i4 = 0; i4 < 20; i4++) {
                System.arraycopy(a2, 0, bArr4, 0, bArr4.length);
                for (int i5 = 0; i5 < bArr4.length; i5++) {
                    bArr4[i5] = (byte) (bArr4[i5] ^ i4);
                }
                InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                a(bArr4, byteArrayInputStream, byteArrayOutputStream);
            }
            byte[] bArr5 = new byte[32];
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr5, 0, 16);
            System.arraycopy(d, 0, bArr5, 16, 16);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(bArr5);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        if (i == 2 && i2 != 5) {
            throw new IOException("Expected length=5 actual=" + i2);
        }
        byte[] a2 = a(bArr, i, i2);
        byte[] a3 = a(bArr2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(a2, new ByteArrayInputStream(a3), byteArrayOutputStream);
        if (i == 3 || i == 4) {
            byte[] bArr3 = new byte[a2.length];
            for (int i3 = 1; i3 < 20; i3++) {
                System.arraycopy(a2, 0, bArr3, 0, a2.length);
                for (int i4 = 0; i4 < bArr3.length; i4++) {
                    bArr3[i4] = (byte) (bArr3[i4] ^ ((byte) i3));
                }
                InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                a(bArr3, byteArrayInputStream, byteArrayOutputStream);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] a(byte[] bArr, int i, int i2) {
        MessageDigest a2 = j.a();
        byte[] digest = a2.digest(a(bArr));
        if (i == 3 || i == 4) {
            for (int i3 = 0; i3 < 50; i3++) {
                a2.update(digest, 0, i2);
                digest = a2.digest();
            }
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(digest, 0, bArr2, 0, i2);
        return bArr2;
    }

    private static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[d.length];
        int min = Math.min(bArr.length, bArr2.length);
        System.arraycopy(bArr, 0, bArr2, 0, min);
        System.arraycopy(d, 0, bArr2, min, d.length - min);
        return bArr2;
    }

    private boolean b(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) {
        switch (i2) {
            case 2:
            case 3:
            case 4:
                return c(bArr, bArr2, bArr3, i, bArr4, i2, i3, z);
            case 5:
            case 6:
                return a(bArr, bArr2, i2);
            default:
                throw new IOException("Unknown Encryption Revision " + i2);
        }
    }

    private boolean c(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, boolean z) {
        byte[] a2 = a(bArr, bArr3, i, bArr4, i2, i3, z);
        if (i2 == 2) {
            return Arrays.equals(bArr2, a2);
        }
        return Arrays.equals(Arrays.copyOf(bArr2, 16), Arrays.copyOf(a2, 16));
    }

    private boolean a(byte[] bArr, byte[] bArr2, int i) {
        byte[] a2;
        byte[] b2 = b(bArr);
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = new byte[8];
        System.arraycopy(bArr2, 0, bArr3, 0, 32);
        System.arraycopy(bArr2, 32, bArr4, 0, 8);
        if (i == 5) {
            a2 = c(b2, bArr4, null);
        } else {
            a2 = a(b2, bArr4, (byte[]) null);
        }
        return Arrays.equals(a2, bArr3);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4;
        if (bArr3 == null) {
            bArr4 = new byte[0];
        } else {
            if (bArr3.length < 48) {
                throw new IOException("Bad U length");
            }
            if (bArr3.length > 48) {
                bArr4 = new byte[48];
                System.arraycopy(bArr3, 0, bArr4, 0, 48);
            } else {
                bArr4 = bArr3;
            }
        }
        byte[] b2 = b(bArr);
        return b(d(b2, bArr2, bArr4), b2, bArr4);
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4;
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(bArr);
            byte[] bArr5 = null;
            int i = 0;
            while (true) {
                if (i >= 64) {
                    byte[] bArr6 = bArr5;
                    if ((bArr6[bArr6.length - 1] & 255) <= i - 32) {
                        break;
                    }
                }
                if (bArr3 != null && bArr3.length >= 48) {
                    bArr4 = new byte[64 * (bArr2.length + digest.length + 48)];
                } else {
                    bArr4 = new byte[64 * (bArr2.length + digest.length)];
                }
                int i2 = 0;
                for (int i3 = 0; i3 < 64; i3++) {
                    System.arraycopy(bArr2, 0, bArr4, i2, bArr2.length);
                    int length = i2 + bArr2.length;
                    System.arraycopy(digest, 0, bArr4, length, digest.length);
                    i2 = length + digest.length;
                    if (bArr3 != null && bArr3.length >= 48) {
                        System.arraycopy(bArr3, 0, bArr4, i2, 48);
                        i2 += 48;
                    }
                }
                byte[] bArr7 = new byte[16];
                byte[] bArr8 = new byte[16];
                System.arraycopy(digest, 0, bArr7, 0, 16);
                System.arraycopy(digest, 16, bArr8, 0, 16);
                Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                cipher.init(1, new SecretKeySpec(bArr7, "AES"), new IvParameterSpec(bArr8));
                bArr5 = cipher.doFinal(bArr4);
                byte[] bArr9 = new byte[16];
                System.arraycopy(bArr5, 0, bArr9, 0, 16);
                digest = MessageDigest.getInstance(e[new BigInteger(1, bArr9).mod(new BigInteger("3")).intValue()]).digest(bArr5);
                i++;
            }
            if (digest.length > 32) {
                byte[] bArr10 = new byte[32];
                System.arraycopy(digest, 0, bArr10, 0, 32);
                return bArr10;
            }
            return digest;
        } catch (GeneralSecurityException e2) {
            d();
            throw new IOException(e2);
        }
    }

    private static byte[] c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            messageDigest.update(bArr2);
            return bArr3 == null ? messageDigest.digest() : messageDigest.digest(bArr3);
        } catch (NoSuchAlgorithmException e2) {
            throw new IOException(e2);
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + 8];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 8);
        return bArr3;
    }

    private static byte[] d(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    private static byte[] b(byte[] bArr) {
        if (bArr.length <= 127) {
            return bArr;
        }
        byte[] bArr2 = new byte[127];
        System.arraycopy(bArr, 0, bArr2, 0, 127);
        return bArr2;
    }

    private static void d() {
        try {
            Cipher.getMaxAllowedKeyLength("AES");
        } catch (NoSuchAlgorithmException unused) {
        }
    }

    @Override // org.a.c.h.c.k
    public final boolean a() {
        return this.f != null;
    }
}
