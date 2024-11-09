package org.a.c.i;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.a.c.b.s;

/* loaded from: infinitode-2.jar:org/a/c/i/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f4653a = {"EEEE, dd MMM yy hh:mm:ss a", "EEEE, MMM dd, yy hh:mm:ss a", "EEEE, MMM dd, yy 'at' hh:mma", "EEEE, MMM dd, yy", "EEEE MMM dd, yy HH:mm:ss", "EEEE MMM dd HH:mm:ss z yy", "EEEE MMM dd HH:mm:ss yy"};

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f4654b = {"dd MMM yy HH:mm:ss", "dd MMM yy HH:mm", "yyyy MMM d", "yyyymmddhh:mm:ss", "H:m M/d/yy", "M/d/yy HH:mm:ss", "M/d/yy HH:mm", "M/d/yy"};

    public static String a(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return String.format(Locale.US, "D:%1$4tY%1$2tm%1$2td%1$2tH%1$2tM%1$2tS%2$s'", calendar, a(calendar.get(15) + calendar.get(16), "'"));
    }

    private static int a(long j) {
        if (j <= 50400000 && j >= -50400000) {
            return (int) j;
        }
        long j2 = (((j + 43200000) % 86400000) + 86400000) % 86400000;
        if (j2 == 0) {
            return 43200000;
        }
        return (int) ((j2 - 43200000) % 43200000);
    }

    private static String a(long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Z");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(a(j), "unknown"));
        String format = simpleDateFormat.format(new Date());
        return format.substring(0, 3) + str + format.substring(3);
    }

    private static int a(String str, ParsePosition parsePosition, int i, int i2) {
        int charAt;
        if (str == null) {
            return i2;
        }
        int i3 = 0;
        int index = parsePosition.getIndex();
        int i4 = index;
        int min = index + Math.min(i, str.length() - i4);
        while (i4 < min && (charAt = str.charAt(i4) - '0') >= 0 && charAt <= 9) {
            i3 = (i3 * 10) + charAt;
            i4++;
        }
        if (i4 == parsePosition.getIndex()) {
            return i2;
        }
        parsePosition.setIndex(i4);
        return i3;
    }

    private static char a(String str, ParsePosition parsePosition, String str2) {
        char c = ' ';
        while (str != null && parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (str2.indexOf(charAt) < 0) {
                break;
            }
            c = charAt != ' ' ? charAt : c;
            parsePosition.setIndex(parsePosition.getIndex() + 1);
        }
        return c;
    }

    private static boolean a(String str, String str2, ParsePosition parsePosition) {
        if (str.startsWith(str2, parsePosition.getIndex())) {
            parsePosition.setIndex(parsePosition.getIndex() + str2.length());
            return true;
        }
        return false;
    }

    private static GregorianCalendar a() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(Locale.ENGLISH);
        gregorianCalendar.setTimeZone(new SimpleTimeZone(0, "UTC"));
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar;
    }

    private static void a(GregorianCalendar gregorianCalendar, TimeZone timeZone) {
        gregorianCalendar.setTimeZone(timeZone);
        gregorianCalendar.add(12, -((gregorianCalendar.get(15) + gregorianCalendar.get(16)) / 60000));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.util.TimeZone] */
    private static boolean a(String str, GregorianCalendar gregorianCalendar, ParsePosition parsePosition) {
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(0, "GMT");
        char a2 = a(str, parsePosition2, "Z+- ");
        boolean z = a2 == 'Z' || a(str, "GMT", parsePosition2) || a(str, "UTC", parsePosition2);
        boolean z2 = z;
        char a3 = !z ? a2 : a(str, parsePosition2, "+- ");
        int a4 = a(str, parsePosition2, 2, -999);
        a(str, parsePosition2, "': ");
        int a5 = a(str, parsePosition2, 2, 0);
        a(str, parsePosition2, "' ");
        if (a4 != -999) {
            simpleTimeZone.setRawOffset(a((a3 == '-' ? -1 : 1) * ((a4 * 3600000) + (a5 * 60000))));
            a(simpleTimeZone);
        } else if (!z2) {
            simpleTimeZone = TimeZone.getTimeZone(str.substring(parsePosition.getIndex()).trim());
            if ("GMT".equals(simpleTimeZone.getID())) {
                return false;
            }
            parsePosition2.setIndex(str.length());
        }
        a(gregorianCalendar, simpleTimeZone);
        parsePosition.setIndex(parsePosition2.getIndex());
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void a(TimeZone timeZone) {
        int rawOffset = timeZone.getRawOffset();
        boolean z = 43;
        if (rawOffset < 0) {
            z = 45;
            rawOffset = -rawOffset;
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset % 3600000) / 60000;
        if (rawOffset == 0) {
            timeZone.setID("GMT");
            return;
        }
        if (z == 43 && i <= 12) {
            timeZone.setID(String.format(Locale.US, "GMT+%02d:%02d", Integer.valueOf(i), Integer.valueOf(i2)));
        } else if (z == 45 && i <= 14) {
            timeZone.setID(String.format(Locale.US, "GMT-%02d:%02d", Integer.valueOf(i), Integer.valueOf(i2)));
        } else {
            timeZone.setID("unknown");
        }
    }

    private static GregorianCalendar a(String str, ParsePosition parsePosition) {
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        int a2 = a(str, parsePosition2, 4, 0);
        if (parsePosition2.getIndex() != 4 + parsePosition.getIndex()) {
            return null;
        }
        a(str, parsePosition2, "/- ");
        int a3 = a(str, parsePosition2, 2, 1) - 1;
        a(str, parsePosition2, "/- ");
        int a4 = a(str, parsePosition2, 2, 1);
        a(str, parsePosition2, " T");
        int a5 = a(str, parsePosition2, 2, 0);
        a(str, parsePosition2, ": ");
        int a6 = a(str, parsePosition2, 2, 0);
        a(str, parsePosition2, ": ");
        int a7 = a(str, parsePosition2, 2, 0);
        if (a(str, parsePosition2, ".") == '.') {
            a(str, parsePosition2, 19, 0);
        }
        GregorianCalendar a8 = a();
        try {
            a8.set(a2, a3, a4, a5, a6, a7);
            a8.getTimeInMillis();
            parsePosition.setIndex(parsePosition2.getIndex());
            a(str, parsePosition, SequenceUtils.SPACE);
            return a8;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static GregorianCalendar a(String str, String[] strArr, ParsePosition parsePosition) {
        for (String str2 : strArr) {
            ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.ENGLISH);
            GregorianCalendar a2 = a();
            simpleDateFormat.setCalendar(a2);
            if (simpleDateFormat.parse(str, parsePosition2) != null) {
                parsePosition.setIndex(parsePosition2.getIndex());
                a(str, parsePosition, SequenceUtils.SPACE);
                return a2;
            }
        }
        return null;
    }

    private static Calendar b(String str, ParsePosition parsePosition) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        int i = -999999;
        GregorianCalendar gregorianCalendar = null;
        ParsePosition parsePosition2 = new ParsePosition(parsePosition.getIndex());
        a(str, parsePosition2, SequenceUtils.SPACE);
        int index = parsePosition2.getIndex();
        GregorianCalendar a2 = a(str, parsePosition2);
        if (a2 != null && (parsePosition2.getIndex() == str.length() || a(str, a2, parsePosition2))) {
            int index2 = parsePosition2.getIndex();
            if (index2 == str.length()) {
                parsePosition.setIndex(index2);
                return a2;
            }
            i = index2;
            gregorianCalendar = a2;
        }
        parsePosition2.setIndex(index);
        GregorianCalendar a3 = a(str, Character.isDigit(str.charAt(index)) ? f4654b : f4653a, parsePosition2);
        if (a3 != null && (parsePosition2.getIndex() == str.length() || a(str, a3, parsePosition2))) {
            int index3 = parsePosition2.getIndex();
            if (index3 == str.length()) {
                parsePosition.setIndex(index3);
                return a3;
            }
            if (index3 > i) {
                i = index3;
                gregorianCalendar = a3;
            }
        }
        if (gregorianCalendar != null) {
            parsePosition.setIndex(i);
            return gregorianCalendar;
        }
        return a3;
    }

    public static Calendar a(s sVar) {
        if (sVar == null) {
            return null;
        }
        return a(sVar.b());
    }

    private static Calendar a(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        a(str, parsePosition, SequenceUtils.SPACE);
        a(str, "D:", parsePosition);
        Calendar b2 = b(str, parsePosition);
        if (b2 == null || parsePosition.getIndex() != str.length()) {
            return null;
        }
        return b2;
    }
}
