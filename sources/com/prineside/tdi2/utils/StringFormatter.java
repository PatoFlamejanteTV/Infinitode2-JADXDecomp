package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/StringFormatter.class */
public class StringFormatter {
    public static final String DISTINGUISHABLE_STRING_CHARS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    public static final IntIntMap DIST_STRING_CHAR_TO_IDX = new IntIntMap();
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX;

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f3910a;

    /* renamed from: b, reason: collision with root package name */
    private static final byte[] f3911b;
    private static final ByteArrayOutputStream c;
    private static byte[] d;
    private static final Deflater e;
    private static final Inflater f;
    private static ByteArray g;
    private static MessageDigest h;
    private static GlyphLayout i;
    private static final char[] j;
    private static final StringBuilder k;
    private static final StringBuilder l;
    private static final StringBuilder m;
    private static final StringBuilder n;
    private static final StringBuilder o;
    private static final StringBuilder p;
    private static final StringBuilder q;
    private static final StringBuilder r;
    private static final char[] s;
    private static final StringBuilder t;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/StringFormatter$MeasureUnits.class */
    public enum MeasureUnits {
        tiles,
        degrees_per_second,
        tiles_per_second,
        seconds,
        percent,
        units_per_second,
        percent_per_second,
        degrees;

        public static final MeasureUnits[] values = values();
    }

    static {
        for (int i2 = 0; i2 < 32; i2++) {
            DIST_STRING_CHAR_TO_IDX.put(DISTINGUISHABLE_STRING_CHARS.charAt(i2), i2);
        }
        VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2);
        String[] strArr = new String[40];
        f3910a = strArr;
        strArr[0] = "";
        for (int i3 = 1; i3 <= 3; i3++) {
            f3910a[i3] = new String(new char[i3]).replace("��", "I");
        }
        f3910a[4] = "IV";
        for (int i4 = 5; i4 <= 8; i4++) {
            f3910a[i4] = "V" + f3910a[i4 - 5];
        }
        f3910a[9] = "IX";
        f3910a[10] = "X";
        for (int i5 = 11; i5 <= 39; i5++) {
            f3910a[i5] = new String(new char[i5 / 10]).replace("��", "X") + f3910a[i5 % 10];
        }
        f3911b = new byte[1024];
        c = new ByteArrayOutputStream();
        d = new byte[32];
        e = new Deflater(1, true);
        f = new Inflater(true);
        g = new ByteArray(1024);
        j = "0123456789ABCDEF".toCharArray();
        k = new StringBuilder();
        l = new StringBuilder();
        m = new StringBuilder();
        n = new StringBuilder();
        o = new StringBuilder();
        p = new StringBuilder();
        q = new StringBuilder();
        r = new StringBuilder();
        s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        t = new StringBuilder();
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length << 1];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            cArr[i2 << 1] = j[i3 >>> 4];
            cArr[(i2 << 1) + 1] = j[i3 & 15];
        }
        return new String(cArr);
    }

    private static MessageDigest a() {
        if (h == null) {
            try {
                h = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e2) {
                throw new RuntimeException("Failed to get MD5 digest", e2);
            }
        }
        return h;
    }

    public static void main(String[] strArr) {
        long j2 = 1;
        for (int length = "pRko+ewP8b+Z5oDuA5k8+mua+go=".length() - 1; length >= 0; length--) {
            j2 = (j2 * 43117557441451L) + "pRko+ewP8b+Z5oDuA5k8+mua+go=".charAt(length);
        }
        System.out.println(j2);
    }

    public static StringBuilder toUpperCase(CharSequence charSequence) {
        k.setLength(0);
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            k.append(Character.toUpperCase(charSequence.charAt(i2)));
        }
        return k;
    }

    public static StringBuilder toLowerCase(CharSequence charSequence) {
        l.setLength(0);
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            l.append(Character.toLowerCase(charSequence.charAt(i2)));
        }
        return l;
    }

    public static StringBuilder toRGB(Color color) {
        int i2 = (int) (255.0f * color.r);
        int i3 = (int) (255.0f * color.g);
        String hexString = Integer.toHexString((i2 << 16) + (i3 << 8) + ((int) (255.0f * color.f888b)));
        m.setLength(0);
        m.append(hexString);
        for (int length = hexString.length(); length < 6; length++) {
            m.insert(0, '0');
        }
        return m;
    }

    public static StringBuilder digestTime(int i2) {
        return digestTimeWithZeroHours(i2, false);
    }

    public static StringBuilder digestTimeWithZeroHours(int i2, boolean z) {
        n.setLength(0);
        int i3 = (i2 / 60) / 60;
        int i4 = (i2 - ((i3 * 60) * 60)) / 60;
        int i5 = i2 % 60;
        if (z || i3 != 0) {
            if (i3 < 10) {
                n.append('0');
            }
            n.append(i3).append(':');
        }
        if (i4 < 10) {
            n.append('0');
        }
        n.append(i4).append(':');
        if (i5 < 10) {
            n.append('0');
        }
        n.append(i5);
        return n;
    }

    public static String timePassed(int i2, boolean z, boolean z2) {
        if (Config.isHeadless()) {
            return "";
        }
        if (i2 <= 0) {
            return "-";
        }
        int i3 = ((i2 / 60) / 60) / 24;
        if (z2) {
            i2 -= ((i3 * 60) * 60) * 24;
        }
        int i4 = (i2 / 60) / 60;
        int i5 = (i2 % 3600) / 60;
        int i6 = i2 % 60;
        String str = "";
        if (z2 && i3 != 0) {
            str = str + i3 + Game.i.localeManager.i18n.get("TIME_CHAR_DAY") + SequenceUtils.SPACE;
        }
        if (i4 != 0) {
            str = str + i4 + Game.i.localeManager.i18n.get("TIME_CHAR_HOUR") + SequenceUtils.SPACE;
        }
        if ((i4 > 0 || i5 != 0) && (i5 != 0 || z)) {
            str = str + i5 + Game.i.localeManager.i18n.get("TIME_CHAR_MINUTE") + SequenceUtils.SPACE;
        }
        if (i6 != 0 || z) {
            str = str + i6 + Game.i.localeManager.i18n.get("TIME_CHAR_SECOND");
        }
        return str.trim();
    }

    private static void a(StringBuilder stringBuilder, double d2, int i2, double d3) {
        double d4 = d3 / 10.0d;
        int i3 = (int) (d2 / d4);
        stringBuilder.append(i3);
        if (i2 == 1) {
            stringBuilder.append('.');
        }
        double d5 = d2 - (i3 * d4);
        double d6 = d4 / 10.0d;
        int i4 = (int) (d5 / d6);
        stringBuilder.append(i4);
        if (i2 == 2) {
            stringBuilder.append('.');
        }
        stringBuilder.append((int) ((d5 - (i4 * d6)) / (d6 / 10.0d)));
    }

    public static StringBuilder commaSeparatedNumber(long j2) {
        o.setLength(0);
        boolean z = true;
        long j3 = 1000000000000000000L;
        while (j3 > 0) {
            if (j2 >= j3) {
                long j4 = j2 / j3;
                j2 -= j4 * j3;
                if (z) {
                    o.append(j4);
                } else {
                    if (j4 < 10) {
                        o.append("00");
                    } else if (j4 < 100) {
                        o.append('0');
                    }
                    o.append(j4);
                }
                o.append(',');
                z = false;
            } else if (!z) {
                o.append("000");
                o.append(',');
            }
            long j5 = j3 / 1000;
            j3 = j5;
            if (j5 == 1) {
                break;
            }
        }
        if (!z) {
            if (j2 < 10) {
                o.append("00");
            } else if (j2 < 100) {
                o.append('0');
            }
        }
        o.append(j2);
        return o;
    }

    public static StringBuilder compactNumberWithPrecision(double d2, int i2) {
        return compactNumberWithPrecisionTrimZeros(d2, i2, false);
    }

    public static synchronized StringBuilder compactNumberWithPrecisionTrimZeros(double d2, int i2, boolean z) {
        p.setLength(0);
        if (Double.isInfinite(d2)) {
            p.append("Inf");
            return p;
        }
        if (Double.isNaN(d2)) {
            p.append("NaN");
            return p;
        }
        boolean z2 = d2 < 0.0d;
        boolean z3 = z2;
        if (z2) {
            d2 = -d2;
        }
        char c2 = ' ';
        if (d2 < 10.0d && i2 != 0) {
            p.append(StrictMath.round(d2 * 10000.0d) / 10000.0d);
            int i3 = i2 + 2;
            if (p.length > i3) {
                p.setLength(i3);
            }
        } else if (d2 < 100.0d && i2 != 0) {
            p.append(StrictMath.round(d2 * 1000.0d) / 1000.0d);
            int i4 = i2 + 3;
            if (p.length > i4) {
                p.setLength(i4);
            }
        } else if (d2 < 1000.0d) {
            p.append(StrictMath.round(d2));
        } else if (d2 < 10000.0d) {
            a(p, d2, 1, 10000.0d);
            c2 = 'K';
        } else if (d2 < 100000.0d) {
            a(p, d2, 2, 100000.0d);
            c2 = 'K';
        } else if (d2 < 1000000.0d) {
            a(p, d2, 0, 1000000.0d);
            c2 = 'K';
        } else if (d2 < 1.0E7d) {
            a(p, d2, 1, 1.0E7d);
            c2 = 'M';
        } else if (d2 < 1.0E8d) {
            a(p, d2, 2, 1.0E8d);
            c2 = 'M';
        } else if (d2 < 1.0E9d) {
            a(p, d2, 0, 1.0E9d);
            c2 = 'M';
        } else if (d2 < 1.0E10d) {
            a(p, d2, 1, 1.0E10d);
            c2 = 'B';
        } else if (d2 < 1.0E11d) {
            a(p, d2, 2, 1.0E11d);
            c2 = 'B';
        } else if (d2 < 1.0E12d) {
            a(p, d2, 0, 1.0E12d);
            c2 = 'B';
        } else if (d2 < 1.0E13d) {
            a(p, d2, 1, 1.0E13d);
            c2 = 'T';
        } else if (d2 < 1.0E14d) {
            a(p, d2, 2, 1.0E14d);
            c2 = 'T';
        } else if (d2 < 1.0E15d) {
            a(p, d2, 0, 1.0E15d);
            c2 = 'T';
        } else {
            double d3 = d2;
            int i5 = 0;
            while (d3 >= 1.0E9d) {
                d3 /= 1.0E9d;
                i5 += 9;
            }
            while (d3 >= 1000000.0d) {
                d3 /= 1000000.0d;
                i5 += 6;
            }
            while (d3 >= 1000.0d) {
                d3 /= 10.0d;
                i5++;
            }
            p.append((int) d3).append('e').append(i5);
            if (z3) {
                p.insert(0, '-');
            }
            return p;
        }
        if (z) {
            boolean z4 = false;
            int i6 = 0;
            while (true) {
                if (i6 >= p.length) {
                    break;
                }
                if (p.charAt(i6) != '.') {
                    i6++;
                } else {
                    z4 = true;
                    break;
                }
            }
            if (z4) {
                int i7 = p.length - 1;
                while (true) {
                    if (i7 < 0) {
                        break;
                    }
                    char charAt = p.charAt(i7);
                    if (charAt == '.') {
                        p.setLength(i7);
                        break;
                    }
                    if (charAt == '0') {
                        i7--;
                    } else {
                        p.setLength(i7 + 1);
                        break;
                    }
                }
            }
        }
        if (z3) {
            p.insert(0, '-');
        }
        if (c2 != ' ') {
            p.append(c2);
        }
        return p;
    }

    public static synchronized StringBuilder compactNumber(double d2, boolean z) {
        q.setLength(0);
        boolean z2 = d2 < 0.0d;
        boolean z3 = z2;
        if (z2) {
            d2 = -d2;
        }
        if (d2 < 1.0d && z) {
            q.append(StrictMath.round(d2 * 1000.0d) / 1000.0d);
            if (q.length > 5) {
                q.setLength(5);
            }
        } else if (d2 < 100.0d && z) {
            q.append(StrictMath.round(d2 * 1000.0d) / 1000.0d);
            if (q.length > 4) {
                q.setLength(4);
            }
        } else if (d2 < 1000.0d) {
            q.append(StrictMath.round(d2));
        } else if (d2 < 10000.0d) {
            a(q, d2, 1, 10000.0d);
            q.append('K');
        } else if (d2 < 100000.0d) {
            a(q, d2, 2, 100000.0d);
            q.append('K');
        } else if (d2 < 1000000.0d) {
            a(q, d2, 0, 1000000.0d);
            q.append('K');
        } else if (d2 < 1.0E7d) {
            a(q, d2, 1, 1.0E7d);
            q.append('M');
        } else if (d2 < 1.0E8d) {
            a(q, d2, 2, 1.0E8d);
            q.append('M');
        } else if (d2 < 1.0E9d) {
            a(q, d2, 0, 1.0E9d);
            q.append('M');
        } else if (d2 < 1.0E10d) {
            a(q, d2, 1, 1.0E10d);
            q.append('B');
        } else if (d2 < 1.0E11d) {
            a(q, d2, 2, 1.0E11d);
            q.append('B');
        } else if (d2 < 1.0E12d) {
            a(q, d2, 0, 1.0E12d);
            q.append('B');
        } else if (d2 < 1.0E13d) {
            a(q, d2, 1, 1.0E13d);
            q.append('T');
        } else if (d2 < 1.0E14d) {
            a(q, d2, 2, 1.0E14d);
            q.append('T');
        } else if (d2 < 1.0E15d) {
            a(q, d2, 0, 1.0E15d);
            q.append('T');
        } else {
            double d3 = d2;
            int i2 = 0;
            while (d3 >= 1000.0d) {
                d3 /= 10.0d;
                i2++;
            }
            q.append((int) d3).append('e').append(i2);
        }
        if (z3) {
            q.insert(0, '-');
        }
        return q;
    }

    public static String romanNumber(int i2) {
        if (i2 < 0 || i2 >= f3910a.length) {
            return "";
        }
        return f3910a[i2];
    }

    public static String distinguishableString(int i2) {
        char[] cArr = new char[33];
        boolean z = i2 < 0;
        int i3 = 32;
        if (!z) {
            i2 = -i2;
        }
        while (i2 <= (-32)) {
            int i4 = i3;
            i3--;
            cArr[i4] = DISTINGUISHABLE_STRING_CHARS.charAt(-(i2 % 32));
            i2 /= 32;
        }
        cArr[i3] = DISTINGUISHABLE_STRING_CHARS.charAt(-i2);
        if (z) {
            i3--;
            cArr[i3] = '-';
        }
        return new String(cArr, i3, 33 - i3);
    }

    public static synchronized String toCompactBase64(byte[] bArr, int i2, int i3) {
        if (d.length < bArr.length) {
            d = new byte[MathUtils.nextPowerOfTwo(bArr.length)];
        }
        e.setInput(bArr, i2, i3);
        e.finish();
        int deflate = e.deflate(d);
        e.reset();
        return new String(Base64Coder.encode(d, 0, deflate, Base64Coder.regularMap));
    }

    public static synchronized String toBase64(byte[] bArr, int i2, int i3) {
        return new String(Base64Coder.encode(bArr, i2, i3, Base64Coder.regularMap));
    }

    public static synchronized ByteArray compactBytes(byte[] bArr, int i2, int i3) {
        if (d.length < bArr.length) {
            d = new byte[MathUtils.nextPowerOfTwo(bArr.length)];
        }
        e.setInput(bArr, i2, i3);
        e.finish();
        int deflate = e.deflate(d);
        e.reset();
        g.clear();
        g.addAll(d, 0, deflate);
        return g;
    }

    public static synchronized ByteArrayOutputStream fromCompactBytes(byte[] bArr, int i2, int i3) {
        int inflate;
        try {
            c.reset();
            f.setInput(bArr, i2, i3);
            while (!f.finished() && (inflate = f.inflate(f3911b)) != 0) {
                c.write(f3911b, 0, inflate);
            }
            f.reset();
            return c;
        } catch (DataFormatException e2) {
            throw new IllegalArgumentException("Failed to read compact base64", e2);
        }
    }

    public static synchronized byte[] fromCompactBase64(String str) {
        int inflate;
        try {
            c.reset();
            byte[] decode = Base64Coder.decode(str);
            f.reset();
            f.setInput(decode, 0, decode.length);
            while (!f.finished() && (inflate = f.inflate(f3911b)) != 0) {
                c.write(f3911b, 0, inflate);
            }
            f.reset();
            return c.toByteArray();
        } catch (DataFormatException e2) {
            throw new IllegalArgumentException("Failed to read compact base64", e2);
        }
    }

    public static synchronized byte[] fromBase64(String str) {
        try {
            return Base64Coder.decode(str);
        } catch (Exception e2) {
            throw new IllegalArgumentException("Failed to read base64", e2);
        }
    }

    public static synchronized String stringToCompactBase64(String str) {
        byte[] bytes = str.getBytes();
        if (d.length < bytes.length) {
            d = new byte[MathUtils.nextPowerOfTwo(bytes.length)];
        }
        e.setInput(bytes, 0, bytes.length);
        e.finish();
        int deflate = e.deflate(d);
        e.reset();
        return new String(Base64Coder.encode(d, 0, deflate, Base64Coder.regularMap));
    }

    public static synchronized String stringFromCompactBase64(String str) {
        int inflate;
        try {
            c.reset();
            byte[] decode = Base64Coder.decode(str);
            f.setInput(decode, 0, decode.length);
            while (!f.finished() && (inflate = f.inflate(f3911b)) != 0) {
                c.write(f3911b, 0, inflate);
            }
            f.reset();
            return c.toString();
        } catch (DataFormatException e2) {
            throw new IllegalArgumentException("Failed to read compact base64", e2);
        }
    }

    public static synchronized String md5Hash(String str) {
        MessageDigest a2 = a();
        a2.reset();
        a2.update(str.getBytes());
        StringBuilder sb = new StringBuilder(new BigInteger(1, a2.digest()).toString(16));
        while (sb.length() < 32) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public static synchronized String bytesMd5Hash(byte[] bArr) {
        MessageDigest a2 = a();
        a2.reset();
        a2.update(bArr);
        StringBuilder sb = new StringBuilder(new BigInteger(1, a2.digest()).toString(16));
        while (sb.length() < 32) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public static byte[] md5HashToBytes(String str) {
        MessageDigest a2 = a();
        a2.reset();
        a2.update(str.getBytes());
        return a2.digest();
    }

    public static float calculateWidth(CharSequence charSequence, BitmapFont bitmapFont) {
        if (i == null) {
            i = new GlyphLayout(bitmapFont, "");
        }
        GlyphLayout glyphLayout = i;
        glyphLayout.setText(bitmapFont, charSequence);
        return glyphLayout.width;
    }

    public static CharSequence fitToWidth(CharSequence charSequence, float f2, BitmapFont bitmapFont, CharSequence charSequence2) {
        if (i == null) {
            i = new GlyphLayout(bitmapFont, "");
        }
        GlyphLayout glyphLayout = i;
        glyphLayout.setText(bitmapFont, charSequence);
        if (glyphLayout.width <= f2) {
            return charSequence;
        }
        r.setLength(0);
        r.append(charSequence2);
        glyphLayout.setText(bitmapFont, r);
        float f3 = glyphLayout.width;
        r.setLength(0);
        int i2 = 0;
        while (i2 < charSequence.length()) {
            char charAt = charSequence.charAt(i2);
            char charAt2 = charSequence.length() > i2 + 1 ? charSequence.charAt(i2 + 1) : '~';
            if (charAt != '[' || charAt2 != '#') {
                if (charAt == '[' && charAt2 == ']') {
                    r.append("[]");
                    i2 += 2;
                } else {
                    r.append(charAt);
                    glyphLayout.setText(bitmapFont, r);
                    if (glyphLayout.width + f3 >= f2) {
                        break;
                    }
                }
                i2++;
            }
            while (i2 < charSequence.length()) {
                char charAt3 = charSequence.charAt(i2);
                r.append(charSequence.charAt(i2));
                i2++;
                if (charAt3 == ']') {
                    break;
                }
            }
            i2++;
        }
        r.append(charSequence2);
        return r;
    }

    public static StringBuilder intToString(int i2) {
        t.setLength(0);
        int length = s.length;
        while (i2 >= length) {
            t.append(s[i2 % length]);
            i2 /= length;
        }
        t.append(s[i2 % length]);
        return t;
    }

    public static String shortenFirstWord(String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            switch (charAt) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                    z = true;
                    break;
                default:
                    if (z && (Character.isUpperCase(charAt) || charAt == '.' || charAt == '_' || charAt == '-' || charAt == ' ')) {
                        sb.append(str.subSequence(i2, str.length()));
                    } else {
                        sb.append(charAt);
                        break;
                    }
                    break;
            }
        }
        return sb.toString();
    }

    public static CharSequence stripTerminalColors(String str) {
        if (!str.contains("\u001b")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == 27) {
                z = true;
            } else if (!z) {
                sb.append(charAt);
            } else if (charAt == 'm') {
                z = false;
            }
        }
        return sb;
    }
}
