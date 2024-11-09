package org.a.c.h.c;

import com.a.a.a.am;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.a.c.b.s;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;

/* loaded from: infinitode-2.jar:org/a/c/h/c/h.class */
public final class h extends k {
    private g c = null;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.a.c.h.c.k
    public final void a(d dVar, org.a.c.b.a aVar, a aVar2) {
        byte[] digest;
        if (!(aVar2 instanceof f)) {
            throw new IOException("Provided decryption material is not compatible with the document");
        }
        a(dVar.l());
        if (dVar.d() != 0) {
            this.f4506a = dVar.d();
        }
        f fVar = (f) aVar2;
        try {
            boolean z = false;
            X509Certificate a2 = fVar.a();
            X509CertificateHolder x509CertificateHolder = null;
            if (a2 != null) {
                x509CertificateHolder = new X509CertificateHolder(a2.getEncoded());
            }
            byte[] bArr = null;
            org.a.c.b.a aVar3 = (org.a.c.b.a) dVar.f().n(org.a.c.b.j.dc);
            org.a.c.b.a aVar4 = aVar3;
            if (aVar3 == null) {
                aVar4 = (org.a.c.b.a) dVar.n().f().n(org.a.c.b.j.dc);
            }
            byte[] bArr2 = new byte[aVar4.b()];
            int i = 0;
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < aVar4.b(); i2++) {
                byte[] c = ((s) aVar4.a(i2)).c();
                int i3 = 0;
                Iterator it = new CMSEnvelopedData(c).getRecipientInfos().getRecipients().iterator();
                while (true) {
                    if (it.hasNext()) {
                        RecipientInformation recipientInformation = (RecipientInformation) it.next();
                        KeyTransRecipientId rid = recipientInformation.getRID();
                        if (!z && rid.match(x509CertificateHolder)) {
                            z = true;
                            bArr = recipientInformation.getContent(new JceKeyTransEnvelopedRecipient((PrivateKey) fVar.b()));
                            break;
                        }
                        i3++;
                        if (a2 != null) {
                            sb.append('\n');
                            sb.append(i3);
                            sb.append(": ");
                            if (rid instanceof KeyTransRecipientId) {
                                a(sb, rid, a2, x509CertificateHolder);
                            }
                        }
                    }
                }
                bArr2[i2] = c;
                i += c.length;
            }
            if (!z || bArr == null) {
                throw new IOException("The certificate matches none of " + aVar4.b() + " recipient entries" + sb.toString());
            }
            if (bArr.length != 24) {
                throw new IOException("The enveloped data does not contain 24 bytes");
            }
            byte[] bArr3 = new byte[4];
            System.arraycopy(bArr, 20, bArr3, 0, 4);
            j jVar = new j(bArr3);
            jVar.k();
            a(jVar);
            byte[] bArr4 = new byte[i + 20];
            System.arraycopy(bArr, 0, bArr4, 0, 20);
            int i4 = 20;
            for (Object[] objArr : bArr2) {
                System.arraycopy(objArr, 0, bArr4, i4, objArr.length);
                i4 += objArr.length;
            }
            if (dVar.c() == 4 || dVar.c() == 5) {
                digest = j.c().digest(bArr4);
                c n = dVar.n();
                if (n != null) {
                    org.a.c.b.j b2 = n.b();
                    b(org.a.c.b.j.d.equals(b2) || org.a.c.b.j.e.equals(b2));
                }
            } else {
                digest = j.b().digest(bArr4);
            }
            this.f4507b = new byte[this.f4506a / 8];
            System.arraycopy(digest, 0, this.f4507b, 0, this.f4506a / 8);
        } catch (CertificateEncodingException e) {
            throw new IOException(e);
        } catch (CMSException e2) {
            throw new IOException((Throwable) e2);
        } catch (KeyStoreException e3) {
            throw new IOException(e3);
        }
    }

    private static void a(StringBuilder sb, KeyTransRecipientId keyTransRecipientId, X509Certificate x509Certificate, X509CertificateHolder x509CertificateHolder) {
        BigInteger serialNumber = keyTransRecipientId.getSerialNumber();
        if (serialNumber != null) {
            String str = "unknown";
            BigInteger serialNumber2 = x509Certificate.getSerialNumber();
            if (serialNumber2 != null) {
                str = serialNumber2.toString(16);
            }
            sb.append("serial-#: rid ");
            sb.append(serialNumber.toString(16));
            sb.append(" vs. cert ");
            sb.append(str);
            sb.append(" issuer: rid '");
            sb.append(keyTransRecipientId.getIssuer());
            sb.append("' vs. cert '");
            sb.append((Object) (x509CertificateHolder == null ? "null" : x509CertificateHolder.getIssuer()));
            sb.append("' ");
        }
    }

    @Override // org.a.c.h.c.k
    public final void a(org.a.c.h.b bVar) {
        byte[] digest;
        try {
            d d = bVar.d();
            d dVar = d;
            if (d == null) {
                dVar = new d();
            }
            dVar.a("Adobe.PubSec");
            dVar.b(this.f4506a);
            int c = c();
            dVar.a(c);
            dVar.q();
            byte[] bArr = new byte[20];
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(192, new SecureRandom());
                System.arraycopy(keyGenerator.generateKey().getEncoded(), 0, bArr, 0, 20);
                byte[][] a2 = a(bArr);
                int i = 20;
                for (byte[] bArr2 : a2) {
                    i += bArr2.length;
                }
                byte[] bArr3 = new byte[i];
                System.arraycopy(bArr, 0, bArr3, 0, 20);
                int i2 = 20;
                for (byte[] bArr4 : a2) {
                    System.arraycopy(bArr4, 0, bArr3, i2, bArr4.length);
                    i2 += bArr4.length;
                }
                if (c == 4 || c == 5) {
                    dVar.b("adbe.pkcs7.s5");
                    digest = j.c().digest(bArr3);
                    a(dVar, c == 5 ? org.a.c.b.j.e : org.a.c.b.j.d, a2);
                } else {
                    dVar.b("adbe.pkcs7.s4");
                    digest = j.b().digest(bArr3);
                    dVar.a(a2);
                }
                this.f4507b = new byte[this.f4506a / 8];
                System.arraycopy(digest, 0, this.f4507b, 0, this.f4506a / 8);
                bVar.a(dVar);
                bVar.a().b(dVar.f());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } catch (GeneralSecurityException e2) {
            throw new IOException(e2);
        }
    }

    private int c() {
        switch (this.f4506a) {
            case 40:
                return 1;
            case 128:
                return 2;
            case 256:
                return 5;
            default:
                throw new IllegalArgumentException("key length must be 40, 128 or 256");
        }
    }

    private void a(d dVar, org.a.c.b.j jVar, byte[][] bArr) {
        c cVar = new c();
        cVar.a(jVar);
        cVar.a(this.f4506a);
        org.a.c.b.a aVar = new org.a.c.b.a();
        for (byte[] bArr2 : bArr) {
            aVar.a((org.a.c.b.b) new s(bArr2));
        }
        cVar.f().a(org.a.c.b.j.dc, (org.a.c.b.b) aVar);
        aVar.a(true);
        dVar.b(cVar);
        dVar.a(org.a.c.b.j.at);
        dVar.b(org.a.c.b.j.at);
        cVar.f().a(true);
        b(true);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [byte[], byte[][]] */
    private byte[][] a(byte[] bArr) {
        ?? r0 = new byte[this.c.b()];
        Iterator<am> a2 = this.c.a();
        int i = 0;
        while (a2.hasNext()) {
            am next = a2.next();
            X509Certificate a3 = next.a();
            int e = next.b().e();
            byte[] bArr2 = new byte[24];
            byte b2 = (byte) e;
            System.arraycopy(bArr, 0, bArr2, 0, 20);
            bArr2[20] = (byte) (e >>> 24);
            bArr2[21] = (byte) (e >>> 16);
            bArr2[22] = (byte) (e >>> 8);
            bArr2[23] = b2;
            ASN1Primitive a4 = a(bArr2, a3);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DEROutputStream(byteArrayOutputStream).writeObject(a4);
            r0[i] = byteArrayOutputStream.toByteArray();
            i++;
        }
        return r0;
    }

    private ASN1Primitive a(byte[] bArr, X509Certificate x509Certificate) {
        String id = PKCSObjectIdentifiers.RC2_CBC.getId();
        try {
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance(id, m.a());
            KeyGenerator keyGenerator = KeyGenerator.getInstance(id, m.a());
            Cipher cipher = Cipher.getInstance(id, m.a());
            AlgorithmParameters generateParameters = algorithmParameterGenerator.generateParameters();
            ASN1InputStream aSN1InputStream = new ASN1InputStream(generateParameters.getEncoded("ASN.1"));
            ASN1Primitive readObject = aSN1InputStream.readObject();
            aSN1InputStream.close();
            keyGenerator.init(128);
            SecretKey generateKey = keyGenerator.generateKey();
            cipher.init(1, generateKey, generateParameters);
            return new ContentInfo(PKCSObjectIdentifiers.envelopedData, new EnvelopedData((OriginatorInfo) null, new DERSet(new RecipientInfo(a(x509Certificate, generateKey.getEncoded()))), new EncryptedContentInfo(PKCSObjectIdentifiers.data, new AlgorithmIdentifier(new ASN1ObjectIdentifier(id), readObject), new DEROctetString(cipher.doFinal(bArr))), (ASN1Set) null)).toASN1Primitive();
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("Could not find a suitable javax.crypto provider for algorithm " + id + "; possible reason: using an unsigned .jar file", e);
        } catch (NoSuchPaddingException e2) {
            throw new RuntimeException("Could not find a suitable javax.crypto provider", e2);
        }
    }

    private static KeyTransRecipientInfo a(X509Certificate x509Certificate, byte[] bArr) {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(x509Certificate.getTBSCertificate());
        TBSCertificate tBSCertificate = TBSCertificate.getInstance(aSN1InputStream.readObject());
        aSN1InputStream.close();
        AlgorithmIdentifier algorithm = tBSCertificate.getSubjectPublicKeyInfo().getAlgorithm();
        IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(tBSCertificate.getIssuer(), tBSCertificate.getSerialNumber().getValue());
        try {
            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm().getId(), m.a());
            cipher.init(1, x509Certificate.getPublicKey());
            return new KeyTransRecipientInfo(new RecipientIdentifier(issuerAndSerialNumber), algorithm, new DEROctetString(cipher.doFinal(bArr)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not find a suitable javax.crypto provider", e);
        } catch (NoSuchPaddingException e2) {
            throw new RuntimeException("Could not find a suitable javax.crypto provider", e2);
        }
    }

    @Override // org.a.c.h.c.k
    public final boolean a() {
        return this.c != null;
    }
}
