package com.a.a.c.m;

import com.prineside.tdi2.events.EventListeners;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/a/a/c/m/ab.class */
public class ab extends DateFormat {

    /* renamed from: b, reason: collision with root package name */
    private static Pattern f704b = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d");
    private static Pattern c;
    private static String[] d;
    private static TimeZone e;
    private static Locale f;
    private static DateFormat g;

    /* renamed from: a, reason: collision with root package name */
    public static final ab f705a;
    private static Calendar h;
    private transient TimeZone i;
    private Locale j;
    private Boolean k;
    private transient Calendar l;
    private transient DateFormat m;
    private boolean n;

    static {
        try {
            c = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d(?:[:]\\d\\d)?(\\.\\d+)?(Z|[+-]\\d\\d(?:[:]?\\d\\d)?)?");
            d = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSSX", "yyyy-MM-dd'T'HH:mm:ss.SSS", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
            e = TimeZone.getTimeZone("UTC");
            f = Locale.US;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", f);
            g = simpleDateFormat;
            simpleDateFormat.setTimeZone(e);
            f705a = new ab();
            h = new GregorianCalendar(e, f);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public ab() {
        this.n = true;
        this.j = f;
    }

    private ab(TimeZone timeZone, Locale locale, Boolean bool, boolean z) {
        this.n = true;
        this.i = timeZone;
        this.j = locale;
        this.k = bool;
        this.n = z;
    }

    public final ab a(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = e;
        }
        if (timeZone == this.i || timeZone.equals(this.i)) {
            return this;
        }
        return new ab(timeZone, this.j, this.k, this.n);
    }

    public final ab a(Locale locale) {
        if (locale.equals(this.j)) {
            return this;
        }
        return new ab(this.i, locale, this.k, this.n);
    }

    public final ab a(Boolean bool) {
        if (a(bool, this.k)) {
            return this;
        }
        return new ab(this.i, this.j, bool, this.n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.text.DateFormat, java.text.Format
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ab clone() {
        return new ab(this.i, this.j, this.k, this.n);
    }

    @Override // java.text.DateFormat
    public TimeZone getTimeZone() {
        return this.i;
    }

    @Override // java.text.DateFormat
    public void setTimeZone(TimeZone timeZone) {
        if (!timeZone.equals(this.i)) {
            c();
            this.i = timeZone;
        }
    }

    @Override // java.text.DateFormat
    public void setLenient(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        if (!a(valueOf, this.k)) {
            this.k = valueOf;
            c();
        }
    }

    @Override // java.text.DateFormat
    public boolean isLenient() {
        return this.k == null || this.k.booleanValue();
    }

    @Override // java.text.DateFormat
    public Date parse(String str) {
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date a2 = a(trim, parsePosition);
        if (a2 != null) {
            return a2;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : d) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('\"');
            }
            sb.append(str2);
        }
        sb.append('\"');
        throw new ParseException(String.format("Cannot parse date \"%s\": not compatible with any of standard forms (%s)", trim, sb.toString()), parsePosition.getErrorIndex());
    }

    @Override // java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        try {
            return a(str, parsePosition);
        } catch (ParseException unused) {
            return null;
        }
    }

    private Date a(String str, ParsePosition parsePosition) {
        if (a(str)) {
            return c(str, parsePosition);
        }
        int length = str.length();
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            char charAt = str.charAt(length);
            if (charAt < '0' || charAt > '9') {
                if (length > 0 || charAt != '-') {
                    break;
                }
            }
        }
        if (length < 0 && (str.charAt(0) == '-' || com.a.a.b.c.h.a(str, false))) {
            return b(str, parsePosition);
        }
        return d(str, parsePosition);
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        TimeZone timeZone = this.i;
        TimeZone timeZone2 = timeZone;
        if (timeZone == null) {
            timeZone2 = e;
        }
        a(timeZone2, date, stringBuffer);
        return stringBuffer;
    }

    private void a(TimeZone timeZone, Date date, StringBuffer stringBuffer) {
        Calendar b2 = b(timeZone);
        b2.setTime(date);
        int i = b2.get(1);
        if (b2.get(0) == 0) {
            a(stringBuffer, i);
        } else {
            if (i > 9999) {
                stringBuffer.append('+');
            }
            d(stringBuffer, i);
        }
        stringBuffer.append('-');
        b(stringBuffer, b2.get(2) + 1);
        stringBuffer.append('-');
        b(stringBuffer, b2.get(5));
        stringBuffer.append('T');
        b(stringBuffer, b2.get(11));
        stringBuffer.append(':');
        b(stringBuffer, b2.get(12));
        stringBuffer.append(':');
        b(stringBuffer, b2.get(13));
        stringBuffer.append('.');
        c(stringBuffer, b2.get(14));
        int offset = timeZone.getOffset(b2.getTimeInMillis());
        if (offset != 0) {
            int abs = Math.abs((offset / 60000) / 60);
            int abs2 = Math.abs((offset / 60000) % 60);
            stringBuffer.append(offset < 0 ? '-' : '+');
            b(stringBuffer, abs);
            if (this.n) {
                stringBuffer.append(':');
            }
            b(stringBuffer, abs2);
            return;
        }
        if (this.n) {
            stringBuffer.append("+00:00");
        } else {
            stringBuffer.append("+0000");
        }
    }

    private static void a(StringBuffer stringBuffer, int i) {
        if (i == 1) {
            stringBuffer.append("+0000");
        } else {
            stringBuffer.append('-');
            d(stringBuffer, i - 1);
        }
    }

    private static void b(StringBuffer stringBuffer, int i) {
        int i2 = i / 10;
        if (i2 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i2 + 48));
            i -= i2 * 10;
        }
        stringBuffer.append((char) (i + 48));
    }

    private static void c(StringBuffer stringBuffer, int i) {
        int i2 = i / 100;
        if (i2 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i2 + 48));
            i -= i2 * 100;
        }
        b(stringBuffer, i);
    }

    private static void d(StringBuffer stringBuffer, int i) {
        int i2 = i / 100;
        if (i2 == 0) {
            stringBuffer.append('0').append('0');
        } else {
            if (i2 > 99) {
                stringBuffer.append(i2);
            } else {
                b(stringBuffer, i2);
            }
            i -= i2 * 100;
        }
        b(stringBuffer, i);
    }

    public String toString() {
        return String.format("DateFormat %s: (timezone: %s, locale: %s, lenient: %s)", getClass().getName(), this.i, this.j, this.k);
    }

    public final String a() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("[one of: 'yyyy-MM-dd'T'HH:mm:ss.SSSX").append("', 'EEE, dd MMM yyyy HH:mm:ss zzz").append("' (");
        sb.append(Boolean.FALSE.equals(this.k) ? "strict" : "lenient").append(")]");
        return sb.toString();
    }

    @Override // java.text.DateFormat
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // java.text.DateFormat
    public int hashCode() {
        return System.identityHashCode(this);
    }

    private static boolean a(String str) {
        if (str.length() >= 7 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == '-' && Character.isDigit(str.charAt(5))) {
            return true;
        }
        return false;
    }

    private static Date b(String str, ParsePosition parsePosition) {
        try {
            return new Date(com.a.a.b.c.h.b(str));
        } catch (NumberFormatException unused) {
            throw new ParseException(String.format("Timestamp value %s out of 64-bit value range", str), parsePosition.getErrorIndex());
        }
    }

    private Date c(String str, ParsePosition parsePosition) {
        try {
            return b(str);
        } catch (IllegalArgumentException e2) {
            throw new ParseException(String.format("Cannot parse date \"%s\", problem: %s", str, e2.getMessage()), parsePosition.getErrorIndex());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:39:0x0175. Please report as an issue. */
    private Date b(String str) {
        Object obj;
        int i;
        int i2;
        int length = str.length();
        TimeZone timeZone = e;
        if (this.i != null && 'Z' != str.charAt(length - 1)) {
            timeZone = this.i;
        }
        Calendar b2 = b(timeZone);
        b2.clear();
        if (length <= 10) {
            if (f704b.matcher(str).matches()) {
                b2.set(a(str, 0), b(str, 5) - 1, b(str, 8), 0, 0, 0);
                b2.set(14, 0);
                return b2.getTime();
            }
            obj = "yyyy-MM-dd";
        } else {
            Matcher matcher = c.matcher(str);
            if (matcher.matches()) {
                int start = matcher.start(2);
                int end = matcher.end(2);
                int i3 = end - start;
                if (i3 > 1) {
                    int b3 = b(str, start + 1) * 3600;
                    if (i3 >= 5) {
                        b3 += b(str, end - 2) * 60;
                    }
                    if (str.charAt(start) == '-') {
                        i2 = b3 * EventListeners.PRIORITY_LOW;
                    } else {
                        i2 = b3 * 1000;
                    }
                    b2.set(15, i2);
                    b2.set(16, 0);
                }
                int a2 = a(str, 0);
                int b4 = b(str, 5) - 1;
                int b5 = b(str, 8);
                int b6 = b(str, 11);
                int b7 = b(str, 14);
                if (length > 16 && str.charAt(16) == ':') {
                    i = b(str, 17);
                } else {
                    i = 0;
                }
                b2.set(a2, b4, b5, b6, b7, i);
                int start2 = matcher.start(1) + 1;
                int end2 = matcher.end(1);
                if (start2 >= end2) {
                    b2.set(14, 0);
                } else {
                    int i4 = 0;
                    int i5 = end2 - start2;
                    switch (i5) {
                        case 0:
                            b2.set(14, i4);
                            break;
                        case 1:
                            i4 += 100 * (str.charAt(start2) - '0');
                            b2.set(14, i4);
                            break;
                        case 2:
                            i4 += 10 * (str.charAt(start2 + 1) - '0');
                            i4 += 100 * (str.charAt(start2) - '0');
                            b2.set(14, i4);
                            break;
                        case 3:
                            i4 = 0 + (str.charAt(start2 + 2) - '0');
                            i4 += 10 * (str.charAt(start2 + 1) - '0');
                            i4 += 100 * (str.charAt(start2) - '0');
                            b2.set(14, i4);
                            break;
                        default:
                            if (i5 > 9) {
                                throw new ParseException(String.format("Cannot parse date \"%s\": invalid fractional seconds '%s'; can use at most 9 digits", str, matcher.group(1).substring(1)), start2);
                            }
                            i4 = 0 + (str.charAt(start2 + 2) - '0');
                            i4 += 10 * (str.charAt(start2 + 1) - '0');
                            i4 += 100 * (str.charAt(start2) - '0');
                            b2.set(14, i4);
                            break;
                    }
                }
                return b2.getTime();
            }
            obj = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
        }
        throw new ParseException(String.format("Cannot parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)", str, obj, this.k), 0);
    }

    private static int a(String str, int i) {
        return (1000 * (str.charAt(0) - '0')) + (100 * (str.charAt(1) - '0')) + (10 * (str.charAt(2) - '0')) + (str.charAt(3) - '0');
    }

    private static int b(String str, int i) {
        return (10 * (str.charAt(i) - '0')) + (str.charAt(i + 1) - '0');
    }

    private Date d(String str, ParsePosition parsePosition) {
        if (this.m == null) {
            this.m = a(g, "EEE, dd MMM yyyy HH:mm:ss zzz", this.i, this.j, this.k);
        }
        return this.m.parse(str, parsePosition);
    }

    private static final DateFormat a(DateFormat dateFormat, String str, TimeZone timeZone, Locale locale, Boolean bool) {
        DateFormat dateFormat2;
        if (!locale.equals(f)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
            dateFormat2 = simpleDateFormat;
            simpleDateFormat.setTimeZone(timeZone == null ? e : timeZone);
        } else {
            dateFormat2 = (DateFormat) dateFormat.clone();
            if (timeZone != null) {
                dateFormat2.setTimeZone(timeZone);
            }
        }
        if (bool != null) {
            dateFormat2.setLenient(bool.booleanValue());
        }
        return dateFormat2;
    }

    private void c() {
        this.m = null;
    }

    private Calendar b(TimeZone timeZone) {
        Calendar calendar = this.l;
        Calendar calendar2 = calendar;
        if (calendar == null) {
            Calendar calendar3 = (Calendar) h.clone();
            calendar2 = calendar3;
            this.l = calendar3;
        }
        if (!calendar2.getTimeZone().equals(timeZone)) {
            calendar2.setTimeZone(timeZone);
        }
        calendar2.setLenient(isLenient());
        return calendar2;
    }

    private static <T> boolean a(T t, T t2) {
        if (t == t2) {
            return true;
        }
        return t != null && t.equals(t2);
    }
}
