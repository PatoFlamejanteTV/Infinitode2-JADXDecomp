package org.a.c.h.c;

import com.a.a.a.am;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.a.c.b.s;

/* loaded from: infinitode-2.jar:org/a/c/h/c/k.class */
public abstract class k {
    private static final org.a.a.a.a c = org.a.a.a.c.a(k.class);
    private static final byte[] d = {115, 65, 108, 84};

    /* renamed from: b, reason: collision with root package name */
    protected byte[] f4507b;
    private boolean f;
    private boolean h;
    private org.a.c.b.j j;
    private org.a.c.b.j k;

    /* renamed from: a, reason: collision with root package name */
    protected int f4506a = 40;
    private final i e = new i();
    private final Set<org.a.c.b.b> g = Collections.newSetFromMap(new IdentityHashMap());
    private j i = null;

    public abstract void a(org.a.c.h.b bVar);

    public abstract void a(d dVar, org.a.c.b.a aVar, a aVar2);

    public abstract boolean a();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(boolean z) {
        this.f = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(org.a.c.b.j jVar) {
        this.k = jVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(org.a.c.b.j jVar) {
        this.j = jVar;
    }

    private void a(long j, long j2, InputStream inputStream, OutputStream outputStream, boolean z) {
        if (this.h && this.f4507b.length == 32) {
            a(inputStream, outputStream, z);
        } else {
            byte[] a2 = a(j, j2);
            if (this.h) {
                a(a2, inputStream, outputStream, z);
            } else {
                a(a2, inputStream, outputStream);
            }
        }
        outputStream.flush();
    }

    private byte[] a(long j, long j2) {
        byte[] bArr = new byte[this.f4507b.length + 5];
        System.arraycopy(this.f4507b, 0, bArr, 0, this.f4507b.length);
        bArr[bArr.length - 5] = (byte) (j & 255);
        bArr[bArr.length - 4] = (byte) ((j >> 8) & 255);
        bArr[bArr.length - 3] = (byte) ((j >> 16) & 255);
        bArr[bArr.length - 2] = (byte) (j2 & 255);
        bArr[bArr.length - 1] = (byte) ((j2 >> 8) & 255);
        MessageDigest a2 = j.a();
        a2.update(bArr);
        if (this.h) {
            a2.update(d);
        }
        byte[] digest = a2.digest();
        int min = Math.min(bArr.length, 16);
        byte[] bArr2 = new byte[min];
        System.arraycopy(digest, 0, bArr2, 0, min);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(byte[] bArr, InputStream inputStream, OutputStream outputStream) {
        this.e.a(bArr);
        this.e.a(inputStream, outputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(byte[] bArr, byte[] bArr2, OutputStream outputStream) {
        this.e.a(bArr);
        this.e.a(bArr2, outputStream);
    }

    private void a(byte[] bArr, InputStream inputStream, OutputStream outputStream, boolean z) {
        byte[] bArr2 = new byte[16];
        if (!a(z, bArr2, inputStream, outputStream)) {
            return;
        }
        try {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(z ? 2 : 1, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
                byte[] bArr3 = new byte[256];
                while (true) {
                    int read = inputStream.read(bArr3);
                    if (read != -1) {
                        byte[] update = cipher.update(bArr3, 0, read);
                        if (update != null) {
                            outputStream.write(update);
                        }
                    } else {
                        outputStream.write(cipher.doFinal());
                        return;
                    }
                }
            } catch (InvalidAlgorithmParameterException e) {
                throw new IOException(e);
            } catch (InvalidKeyException e2) {
                throw new IOException(e2);
            } catch (BadPaddingException e3) {
                throw new IOException(e3);
            } catch (IllegalBlockSizeException e4) {
                throw new IOException(e4);
            } catch (NoSuchPaddingException e5) {
                throw new IOException(e5);
            }
        } catch (NoSuchAlgorithmException e6) {
            throw new RuntimeException(e6);
        }
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [javax.crypto.CipherInputStream, java.io.IOException, java.io.InputStream] */
    private void a(InputStream inputStream, OutputStream outputStream, boolean z) {
        byte[] bArr = new byte[16];
        if (!a(z, bArr, inputStream, outputStream)) {
            return;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(z ? 2 : 1, new SecretKeySpec(this.f4507b, "AES"), new IvParameterSpec(bArr));
            ?? cipherInputStream = new CipherInputStream(inputStream, cipher);
            try {
                am.a((InputStream) cipherInputStream, outputStream);
            } catch (IOException e) {
                if (!(cipherInputStream.getCause() instanceof GeneralSecurityException)) {
                    throw e;
                }
            } finally {
                cipherInputStream.close();
            }
        } catch (GeneralSecurityException e2) {
            throw new IOException(e2);
        }
    }

    private static boolean a(boolean z, byte[] bArr, InputStream inputStream, OutputStream outputStream) {
        if (z) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return false;
            }
            if (read != 16) {
                throw new IOException("AES initialization vector not fully read: only " + read + " bytes read instead of 16");
            }
            return true;
        }
        new SecureRandom().nextBytes(bArr);
        outputStream.write(bArr);
        return true;
    }

    public final void a(org.a.c.b.b bVar, long j, long j2) {
        if (!(bVar instanceof s) && !(bVar instanceof org.a.c.b.d) && !(bVar instanceof org.a.c.b.a)) {
            return;
        }
        if (bVar instanceof s) {
            if (this.g.contains(bVar)) {
                return;
            }
            this.g.add(bVar);
            a((s) bVar, j, j2);
            return;
        }
        if (bVar instanceof org.a.c.b.p) {
            if (this.g.contains(bVar)) {
                return;
            }
            this.g.add(bVar);
            a((org.a.c.b.p) bVar, j, j2);
            return;
        }
        if (bVar instanceof org.a.c.b.d) {
            a((org.a.c.b.d) bVar, j, j2);
        } else if (bVar instanceof org.a.c.b.a) {
            a((org.a.c.b.a) bVar, j, j2);
        }
    }

    public final void a(org.a.c.b.p pVar, long j, long j2) {
        if (org.a.c.b.j.bB.equals(this.j)) {
            return;
        }
        org.a.c.b.j b2 = pVar.b(org.a.c.b.j.dN);
        if ((!this.f && org.a.c.b.j.cj.equals(b2)) || org.a.c.b.j.ef.equals(b2)) {
            return;
        }
        if (org.a.c.b.j.cj.equals(b2)) {
            InputStream j3 = pVar.j();
            byte[] bArr = new byte[10];
            j3.read(bArr);
            j3.close();
            if (Arrays.equals(bArr, "<?xpacket ".getBytes(org.a.c.i.a.d))) {
                return;
            }
        }
        a((org.a.c.b.d) pVar, j, j2);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(am.a(pVar.j()));
        OutputStream m = pVar.m();
        try {
            a(j, j2, byteArrayInputStream, m, true);
        } finally {
            m.close();
        }
    }

    public final void a(org.a.c.b.p pVar, long j, int i) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(am.a(pVar.j()));
        OutputStream m = pVar.m();
        try {
            a(j, i, byteArrayInputStream, m, false);
        } finally {
            m.close();
        }
    }

    private void a(org.a.c.b.d dVar, long j, long j2) {
        if (dVar.n(org.a.c.b.j.Q) != null) {
            return;
        }
        org.a.c.b.b a2 = dVar.a(org.a.c.b.j.dN);
        boolean z = org.a.c.b.j.dq.equals(a2) || org.a.c.b.j.aH.equals(a2) || ((dVar.a(org.a.c.b.j.af) instanceof s) && (dVar.a(org.a.c.b.j.G) instanceof org.a.c.b.a));
        for (Map.Entry<org.a.c.b.j, org.a.c.b.b> entry : dVar.e()) {
            if (!z || !org.a.c.b.j.af.equals(entry.getKey())) {
                org.a.c.b.b value = entry.getValue();
                if ((value instanceof s) || (value instanceof org.a.c.b.a) || (value instanceof org.a.c.b.d)) {
                    a(value, j, j2);
                }
            }
        }
    }

    private void a(s sVar, long j, long j2) {
        if (org.a.c.b.j.bB.equals(this.k)) {
            return;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(sVar.c());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(j, j2, byteArrayInputStream, byteArrayOutputStream, true);
            sVar.a(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            new StringBuilder("Failed to decrypt COSString of length ").append(sVar.c().length).append(" in object ").append(j).append(": ").append(e.getMessage());
        }
    }

    public final void a(s sVar, long j, int i) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(sVar.c());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(j, i, byteArrayInputStream, byteArrayOutputStream, false);
        sVar.a(byteArrayOutputStream.toByteArray());
    }

    private void a(org.a.c.b.a aVar, long j, long j2) {
        for (int i = 0; i < aVar.b(); i++) {
            a(aVar.b(i), j, j2);
        }
    }

    public final void a(j jVar) {
        this.i = jVar;
    }

    public final j b() {
        return this.i;
    }

    public final void b(boolean z) {
        this.h = z;
    }
}
