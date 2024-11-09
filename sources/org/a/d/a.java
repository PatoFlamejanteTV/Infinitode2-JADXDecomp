package org.a.d;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;

/* loaded from: infinitode-2.jar:org/a/d/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final SimpleDateFormat[] f4663a = {new SimpleDateFormat("EEEE, dd MMM yyyy hh:mm:ss a"), new SimpleDateFormat("EEEE, MMM dd, yyyy hh:mm:ss a"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S")};

    public static Calendar a(String str) {
        GregorianCalendar gregorianCalendar = null;
        if (str != null && str.trim().length() > 0) {
            int i = 1;
            int i2 = 1;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            try {
                SimpleTimeZone simpleTimeZone = null;
                if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}T.*", str)) {
                    return b(str);
                }
                if (str.startsWith("D:")) {
                    str = str.substring(2, str.length());
                }
                String replaceAll = str.replaceAll("[-:T]", "");
                if (replaceAll.length() < 4) {
                    throw new IOException("Error: Invalid date format '" + replaceAll + "'");
                }
                int parseInt = Integer.parseInt(replaceAll.substring(0, 4));
                if (replaceAll.length() >= 6) {
                    i = Integer.parseInt(replaceAll.substring(4, 6));
                }
                if (replaceAll.length() >= 8) {
                    i2 = Integer.parseInt(replaceAll.substring(6, 8));
                }
                if (replaceAll.length() >= 10) {
                    i3 = Integer.parseInt(replaceAll.substring(8, 10));
                }
                if (replaceAll.length() >= 12) {
                    i4 = Integer.parseInt(replaceAll.substring(10, 12));
                }
                int i6 = 12;
                if (replaceAll.length() - 12 > 5 || (replaceAll.length() - 12 == 3 && replaceAll.endsWith("Z"))) {
                    if (replaceAll.length() >= 14) {
                        i5 = Integer.parseInt(replaceAll.substring(12, 14));
                    }
                    i6 = 14;
                } else {
                    i5 = 0;
                }
                if (replaceAll.length() >= i6 + 1) {
                    char charAt = replaceAll.charAt(i6);
                    if (charAt == 'Z') {
                        simpleTimeZone = new SimpleTimeZone(0, "Unknown");
                    } else {
                        int i7 = 0;
                        int i8 = 0;
                        if (replaceAll.length() >= i6 + 3) {
                            if (charAt == '+') {
                                i7 = Integer.parseInt(replaceAll.substring(i6 + 1, i6 + 3));
                            } else {
                                int i9 = i6;
                                i7 = -Integer.parseInt(replaceAll.substring(i9, i9 + 2));
                            }
                        }
                        if (charAt == '+') {
                            if (replaceAll.length() >= i6 + 5) {
                                i8 = Integer.parseInt(replaceAll.substring(i6 + 3, i6 + 5));
                            }
                        } else if (replaceAll.length() >= i6 + 4) {
                            i8 = Integer.parseInt(replaceAll.substring(i6 + 2, i6 + 4));
                        }
                        simpleTimeZone = new SimpleTimeZone((i7 * 60 * 60 * 1000) + (i8 * 60 * 1000), "Unknown");
                    }
                }
                if (simpleTimeZone == null) {
                    gregorianCalendar = new GregorianCalendar();
                } else {
                    a(simpleTimeZone);
                    gregorianCalendar = new GregorianCalendar(simpleTimeZone);
                }
                gregorianCalendar.clear();
                gregorianCalendar.set(parseInt, i - 1, i2, i3, i4, i5);
            } catch (NumberFormatException e) {
                if (str.substring(str.length() - 3, str.length() - 2).equals(":") && (str.substring(str.length() - 6, str.length() - 5).equals("+") || str.substring(str.length() - 6, str.length() - 5).equals("-"))) {
                    str = str.substring(0, str.length() - 3) + str.substring(str.length() - 2);
                }
                for (int i10 = 0; gregorianCalendar == null && i10 < f4663a.length; i10++) {
                    try {
                        Date parse = f4663a[i10].parse(str);
                        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
                        gregorianCalendar = gregorianCalendar2;
                        gregorianCalendar2.setTime(parse);
                    } catch (ParseException unused) {
                    }
                }
                if (gregorianCalendar == null) {
                    throw new IOException("Error converting date:" + str, e);
                }
            }
        }
        return gregorianCalendar;
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

    private static Calendar b(String str) {
        Matcher matcher = Pattern.compile("[\\d-]*T?[\\d-\\.]([A-Z]{1,4})$|(.*\\d*)([A-Z][a-z]+\\/[A-Z][a-z]+)$").matcher(str);
        String str2 = null;
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i) != null) {
                    str2 = matcher.group(i);
                }
            }
        }
        if (str2 != null) {
            int indexOf = str.indexOf(84);
            int indexOf2 = str.indexOf(str2);
            String substring = str.substring(0, indexOf2);
            if (indexOf2 - indexOf == 6) {
                substring = str.substring(0, indexOf2) + ":00";
            }
            Calendar parseDateTime = DatatypeConverter.parseDateTime(substring);
            parseDateTime.setTimeZone(TimeZone.getTimeZone(str2));
            return parseDateTime;
        }
        int indexOf3 = str.indexOf(84);
        if (indexOf3 == -1) {
            return DatatypeConverter.parseDateTime(str);
        }
        int indexOf4 = str.indexOf(43, indexOf3 + 1);
        int indexOf5 = str.indexOf(45, indexOf3 + 1);
        if (indexOf4 == -1 && indexOf5 == -1) {
            return DatatypeConverter.parseDateTime(str);
        }
        int max = Math.max(indexOf4, indexOf5);
        if (max - indexOf3 == 6) {
            return DatatypeConverter.parseDateTime(str.substring(0, max) + ":00" + str.substring(max));
        }
        return DatatypeConverter.parseDateTime(str);
    }
}
