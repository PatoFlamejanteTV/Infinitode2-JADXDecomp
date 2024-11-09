package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.utils.MaterialColor;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/PackColor.class */
public class PackColor extends Color {
    private Color[] c;
    private int d;
    private static final Array<Color> e = new Array<>(true, 1, Color.class);
    private static final float[] f = new float[3];

    public PackColor() {
    }

    public PackColor(Color color) {
        super(color);
        if (color instanceof PackColor) {
            from((PackColor) color);
        }
    }

    public PackColor(int i) {
        super(i);
    }

    public boolean isArray() {
        return this.d != 0;
    }

    public Array<Color> getColorArray() {
        e.clear();
        if (this.d != 0) {
            for (int i = 0; i < this.d; i++) {
                e.add(this.c[i]);
            }
        }
        return e;
    }

    public Color getColorAtIndex(int i) {
        if (this.c == null) {
            return this;
        }
        if (this.c.length <= i) {
            return this.c[this.c.length - 1];
        }
        if (i < 0) {
            return this.c[0];
        }
        return this.c[i];
    }

    public PackColor from(PackColor packColor) {
        set(packColor);
        if (packColor.c != null) {
            if (this.c == null) {
                this.c = packColor.c;
            } else {
                if (packColor.c.length > this.c.length) {
                    Color[] colorArr = new Color[packColor.c.length];
                    System.arraycopy(this.c, 0, colorArr, 0, this.c.length);
                    this.c = colorArr;
                }
                int min = Math.min(packColor.c.length, this.c.length);
                for (int i = 0; i < min; i++) {
                    this.c[i].set(packColor.c[i]);
                }
            }
        }
        this.d = packColor.d;
        return this;
    }

    @Override // com.badlogic.gdx.graphics.Color
    public String toString() {
        if (this.c == null) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder(super.toString() + SequenceUtils.SPACE + this.d + " [");
        int i = 0;
        for (Color color : this.c) {
            if (i != 0) {
                sb.append(",");
            }
            StringBuilder sb2 = new StringBuilder(Integer.toHexString((((int) (255.0f * color.r)) << 24) | (((int) (255.0f * color.g)) << 16) | (((int) (255.0f * color.f888b)) << 8) | ((int) (255.0f * color.f889a))));
            while (sb2.length() < 8) {
                sb2.insert(0, "0");
            }
            sb.append((CharSequence) sb2);
            i++;
        }
        sb.append("]");
        return sb.toString();
    }

    public static PackColor parseColorConfigArray(String[] strArr, ObjectMap<String, PackColor> objectMap) {
        PackColor packColor = new PackColor();
        packColor.c = new Color[strArr.length];
        int i = 0;
        for (String str : strArr) {
            int i2 = i;
            i++;
            packColor.c[i2] = parseColorConfig(str, objectMap);
        }
        packColor.d = packColor.c.length;
        packColor.set(packColor.c[0]);
        return packColor;
    }

    public static PackColor fromColors(Color... colorArr) {
        PackColor packColor = new PackColor();
        packColor.c = new Color[colorArr.length];
        System.arraycopy(colorArr, 0, packColor.c, 0, colorArr.length);
        return packColor;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x04cd, code lost:            com.prineside.tdi2.utils.PackColor.f[2] = com.badlogic.gdx.math.MathUtils.clamp(com.prineside.tdi2.utils.PackColor.f[2], 0.0f, 1.0f);        r0.fromHsv(com.prineside.tdi2.utils.PackColor.f);     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0405, code lost:            com.prineside.tdi2.utils.PackColor.f[1] = com.badlogic.gdx.math.MathUtils.clamp(com.prineside.tdi2.utils.PackColor.f[1], 0.0f, 1.0f);        r0.fromHsv(com.prineside.tdi2.utils.PackColor.f);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.prineside.tdi2.utils.PackColor parseColorConfig(java.lang.String r7, com.badlogic.gdx.utils.ObjectMap<java.lang.String, com.prineside.tdi2.utils.PackColor> r8) {
        /*
            Method dump skipped, instructions count: 1335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.utils.PackColor.parseColorConfig(java.lang.String, com.badlogic.gdx.utils.ObjectMap):com.prineside.tdi2.utils.PackColor");
    }

    public static Color parseColorValue(String str, ObjectMap<String, PackColor> objectMap) {
        float parseInt;
        float parseInt2;
        float parseInt3;
        float parseInt4;
        if (str.startsWith("$")) {
            if (objectMap == null) {
                throw new ColorConfigFormatException("No variable map provided but variable notation used: " + str);
            }
            PackColor packColor = objectMap.get(str.substring(1), null);
            if (packColor != null) {
                return new PackColor(packColor);
            }
            throw new ColorConfigFormatException("Color variable not found: " + str);
        }
        if (str.startsWith("!")) {
            String[] split = str.substring(1).split("\\.");
            if (split.length == 1) {
                String str2 = split[0];
                boolean z = -1;
                switch (str2.hashCode()) {
                    case 63281119:
                        if (str2.equals("BLACK")) {
                            z = true;
                            break;
                        }
                        break;
                    case 82564105:
                        if (str2.equals("WHITE")) {
                            z = false;
                            break;
                        }
                        break;
                    case 426766642:
                        if (str2.equals("TRANSPARENT")) {
                            z = 2;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        return Color.WHITE.cpy();
                    case true:
                        return Color.BLACK.cpy();
                    case true:
                        return new Color();
                    default:
                        try {
                            return MaterialColor.allColors[MaterialColor.Colors.valueOf(split[0]).ordinal()][MaterialColor.Variants.P500.ordinal()].cpy();
                        } catch (Exception e2) {
                            throw new ColorConfigFormatException("Palette color " + split[0] + " not found for variant P500: " + str, e2);
                        }
                }
            }
            try {
                return MaterialColor.allColors[MaterialColor.Colors.valueOf(split[0]).ordinal()][MaterialColor.Variants.valueOf(split[1]).ordinal()].cpy();
            } catch (Exception e3) {
                throw new ColorConfigFormatException("Palette color " + split[0] + " or its variant " + split[1] + " not found: " + str, e3);
            }
        }
        if (str.startsWith("#")) {
            String upperCase = str.substring(1).toUpperCase();
            if (upperCase.length() == 6) {
                parseInt = Integer.parseInt(upperCase.substring(0, 2), 16) / 255.0f;
                parseInt2 = Integer.parseInt(upperCase.substring(2, 4), 16) / 255.0f;
                parseInt3 = Integer.parseInt(upperCase.substring(4, 6), 16) / 255.0f;
                parseInt4 = 1.0f;
            } else if (upperCase.length() == 8) {
                parseInt = Integer.parseInt(upperCase.substring(0, 2), 16) / 255.0f;
                parseInt2 = Integer.parseInt(upperCase.substring(2, 4), 16) / 255.0f;
                parseInt3 = Integer.parseInt(upperCase.substring(4, 6), 16) / 255.0f;
                parseInt4 = Integer.parseInt(upperCase.substring(6, 8), 16) / 255.0f;
            } else {
                throw new ColorConfigFormatException("Incorrect #RRGGBB / #RRGGBBAA notation: " + str);
            }
            return new Color(parseInt, parseInt2, parseInt3, parseInt4);
        }
        if (str.startsWith("hsv(")) {
            String[] split2 = str.substring(4, str.length() - 1).split(",");
            if (split2.length == 3 || split2.length == 4) {
                try {
                    float parseFloat = Float.parseFloat(split2[0]);
                    float parseFloat2 = Float.parseFloat(split2[1]);
                    float parseFloat3 = Float.parseFloat(split2[2]);
                    float f2 = 1.0f;
                    if (split2.length == 4) {
                        f2 = Float.parseFloat(split2[3]);
                    }
                    Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
                    color.fromHsv(parseFloat, parseFloat2, parseFloat3);
                    color.f889a = f2;
                    return color;
                } catch (Exception e4) {
                    throw new ColorConfigFormatException("Failed to parse hsv(h,s,v) / hsv(h,s,v,a) notation: " + str, e4);
                }
            }
            throw new ColorConfigFormatException("Incorrect hsv(h,s,v) / hsv(h,s,v,a) notation (should contain 3 or 4 comma separated values): " + str);
        }
        if (str.startsWith("raw(")) {
            String[] split3 = str.substring(4, str.length() - 1).split(",");
            if (split3.length == 3 || split3.length == 4) {
                try {
                    float parseFloat4 = Float.parseFloat(split3[0]);
                    float parseFloat5 = Float.parseFloat(split3[1]);
                    float parseFloat6 = Float.parseFloat(split3[2]);
                    float f3 = 1.0f;
                    if (split3.length == 4) {
                        f3 = Float.parseFloat(split3[3]);
                    }
                    return new Color(parseFloat4, parseFloat5, parseFloat6, f3);
                } catch (Exception e5) {
                    throw new ColorConfigFormatException("Failed to parse raw(r,g,b) / raw(r,g,b,a) notation: " + str, e5);
                }
            }
            throw new ColorConfigFormatException("Incorrect raw(r,g,b) / raw(r,g,b,a) notation (should contain 3 or 4 comma separated values): " + str);
        }
        if (str.startsWith("rgb(")) {
            String[] split4 = str.substring(4, str.length() - 1).split(",");
            if (split4.length == 3 || split4.length == 4) {
                try {
                    float parseFloat7 = Float.parseFloat(split4[0]);
                    float parseFloat8 = Float.parseFloat(split4[1]);
                    float parseFloat9 = Float.parseFloat(split4[2]);
                    float f4 = 1.0f;
                    if (split4.length == 4) {
                        f4 = Float.parseFloat(split4[3]);
                    }
                    return new Color(parseFloat7 / 255.0f, parseFloat8 / 255.0f, parseFloat9 / 255.0f, f4);
                } catch (Exception e6) {
                    throw new ColorConfigFormatException("Failed to parse rgb(r,g,b) / rgb(r,g,b,a) notation: " + str, e6);
                }
            }
            throw new ColorConfigFormatException("Incorrect rgb(r,g,b) / rgb(r,g,b,a) notation (should contain 3 or 4 comma separated values): " + str);
        }
        throw new ColorConfigFormatException("Color notation not recognized: " + str);
    }

    public static boolean isLooksLikeColorValue(String str) {
        char charAt = str.charAt(0);
        if (charAt == '$' || charAt == '!' || charAt == '#') {
            return true;
        }
        if (charAt == 'h' && str.startsWith("hsv(")) {
            return true;
        }
        if (charAt == 'r') {
            return str.startsWith("raw(") || str.startsWith("rgb(");
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/PackColor$ColorConfigFormatException.class */
    public static class ColorConfigFormatException extends IllegalArgumentException {
        ColorConfigFormatException(String str) {
            super(str);
        }

        ColorConfigFormatException(String str, Throwable th) {
            super(str, th);
        }
    }
}
