package com.badlogic.gdx.graphics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.vladsch.flexmark.parser.PegdownExtensions;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Color.class */
public class Color {
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Color LIGHT_GRAY = new Color(-1077952513);
    public static final Color GRAY = new Color(2139062271);
    public static final Color DARK_GRAY = new Color(1061109759);
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f, 1.0f);
    public static final float WHITE_FLOAT_BITS = WHITE.toFloatBits();
    public static final Color CLEAR = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    public static final Color CLEAR_WHITE = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f, 1.0f);
    public static final Color NAVY = new Color(0.0f, 0.0f, 0.5f, 1.0f);
    public static final Color ROYAL = new Color(1097458175);
    public static final Color SLATE = new Color(1887473919);
    public static final Color SKY = new Color(-2016482305);
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f, 1.0f);
    public static final Color TEAL = new Color(0.0f, 0.5f, 0.5f, 1.0f);
    public static final Color GREEN = new Color(16711935);
    public static final Color CHARTREUSE = new Color(2147418367);
    public static final Color LIME = new Color(852308735);
    public static final Color FOREST = new Color(579543807);
    public static final Color OLIVE = new Color(1804477439);
    public static final Color YELLOW = new Color(-65281);
    public static final Color GOLD = new Color(-2686721);
    public static final Color GOLDENROD = new Color(-626712321);
    public static final Color ORANGE = new Color(-5963521);
    public static final Color BROWN = new Color(-1958407169);
    public static final Color TAN = new Color(-759919361);
    public static final Color FIREBRICK = new Color(-1306385665);
    public static final Color RED = new Color(-16776961);
    public static final Color SCARLET = new Color(-13361921);
    public static final Color CORAL = new Color(-8433409);
    public static final Color SALMON = new Color(-92245249);
    public static final Color PINK = new Color(-9849601);
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f, 1.0f);
    public static final Color PURPLE = new Color(-1608453889);
    public static final Color VIOLET = new Color(-293409025);
    public static final Color MAROON = new Color(-1339006721);
    public float r;
    public float g;

    /* renamed from: b, reason: collision with root package name */
    public float f888b;

    /* renamed from: a, reason: collision with root package name */
    public float f889a;

    public Color() {
    }

    public Color(int i) {
        rgba8888ToColor(this, i);
    }

    public Color(float f, float f2, float f3, float f4) {
        this.r = f;
        this.g = f2;
        this.f888b = f3;
        this.f889a = f4;
        clamp();
    }

    public Color(Color color) {
        set(color);
    }

    public Color set(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.f888b = color.f888b;
        this.f889a = color.f889a;
        return this;
    }

    public Color set(Color color, float f) {
        this.r = color.r;
        this.g = color.g;
        this.f888b = color.f888b;
        this.f889a = MathUtils.clamp(f, 0.0f, 1.0f);
        return this;
    }

    public Color mul(Color color) {
        this.r *= color.r;
        this.g *= color.g;
        this.f888b *= color.f888b;
        this.f889a *= color.f889a;
        return clamp();
    }

    public Color mul(float f) {
        this.r *= f;
        this.g *= f;
        this.f888b *= f;
        this.f889a *= f;
        return clamp();
    }

    public Color add(Color color) {
        this.r += color.r;
        this.g += color.g;
        this.f888b += color.f888b;
        this.f889a += color.f889a;
        return clamp();
    }

    public Color sub(Color color) {
        this.r -= color.r;
        this.g -= color.g;
        this.f888b -= color.f888b;
        this.f889a -= color.f889a;
        return clamp();
    }

    public Color clamp() {
        if (this.r < 0.0f) {
            this.r = 0.0f;
        } else if (this.r > 1.0f) {
            this.r = 1.0f;
        }
        if (this.g < 0.0f) {
            this.g = 0.0f;
        } else if (this.g > 1.0f) {
            this.g = 1.0f;
        }
        if (this.f888b < 0.0f) {
            this.f888b = 0.0f;
        } else if (this.f888b > 1.0f) {
            this.f888b = 1.0f;
        }
        if (this.f889a < 0.0f) {
            this.f889a = 0.0f;
        } else if (this.f889a > 1.0f) {
            this.f889a = 1.0f;
        }
        return this;
    }

    public Color set(float f, float f2, float f3, float f4) {
        this.r = f;
        this.g = f2;
        this.f888b = f3;
        this.f889a = f4;
        return clamp();
    }

    public Color set(int i) {
        rgba8888ToColor(this, i);
        return this;
    }

    public Color add(float f, float f2, float f3, float f4) {
        this.r += f;
        this.g += f2;
        this.f888b += f3;
        this.f889a += f4;
        return clamp();
    }

    public Color sub(float f, float f2, float f3, float f4) {
        this.r -= f;
        this.g -= f2;
        this.f888b -= f3;
        this.f889a -= f4;
        return clamp();
    }

    public Color mul(float f, float f2, float f3, float f4) {
        this.r *= f;
        this.g *= f2;
        this.f888b *= f3;
        this.f889a *= f4;
        return clamp();
    }

    public Color lerp(Color color, float f) {
        this.r += f * (color.r - this.r);
        this.g += f * (color.g - this.g);
        this.f888b += f * (color.f888b - this.f888b);
        this.f889a += f * (color.f889a - this.f889a);
        return clamp();
    }

    public Color lerp(float f, float f2, float f3, float f4, float f5) {
        this.r += f5 * (f - this.r);
        this.g += f5 * (f2 - this.g);
        this.f888b += f5 * (f3 - this.f888b);
        this.f889a += f5 * (f4 - this.f889a);
        return clamp();
    }

    public Color premultiplyAlpha() {
        this.r *= this.f889a;
        this.g *= this.f889a;
        this.f888b *= this.f889a;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && toIntBits() == ((Color) obj).toIntBits();
    }

    public int hashCode() {
        return ((((((this.r != 0.0f ? NumberUtils.floatToIntBits(this.r) : 0) * 31) + (this.g != 0.0f ? NumberUtils.floatToIntBits(this.g) : 0)) * 31) + (this.f888b != 0.0f ? NumberUtils.floatToIntBits(this.f888b) : 0)) * 31) + (this.f889a != 0.0f ? NumberUtils.floatToIntBits(this.f889a) : 0);
    }

    public float toFloatBits() {
        return NumberUtils.intToFloatColor((((int) (255.0f * this.f889a)) << 24) | (((int) (255.0f * this.f888b)) << 16) | (((int) (255.0f * this.g)) << 8) | ((int) (255.0f * this.r)));
    }

    public int toIntBits() {
        return (((int) (255.0f * this.f889a)) << 24) | (((int) (255.0f * this.f888b)) << 16) | (((int) (255.0f * this.g)) << 8) | ((int) (255.0f * this.r));
    }

    public String toString() {
        String hexString = Integer.toHexString((((int) (255.0f * this.r)) << 24) | (((int) (255.0f * this.g)) << 16) | (((int) (255.0f * this.f888b)) << 8) | ((int) (255.0f * this.f889a)));
        while (true) {
            String str = hexString;
            if (str.length() < 8) {
                hexString = "0" + str;
            } else {
                return str;
            }
        }
    }

    public static Color valueOf(String str) {
        return valueOf(str, new Color());
    }

    public static Color valueOf(String str, Color color) {
        String substring = str.charAt(0) == '#' ? str.substring(1) : str;
        color.r = Integer.parseInt(substring.substring(0, 2), 16) / 255.0f;
        color.g = Integer.parseInt(substring.substring(2, 4), 16) / 255.0f;
        color.f888b = Integer.parseInt(substring.substring(4, 6), 16) / 255.0f;
        color.f889a = substring.length() != 8 ? 1.0f : Integer.parseInt(substring.substring(6, 8), 16) / 255.0f;
        return color;
    }

    public static float toFloatBits(int i, int i2, int i3, int i4) {
        return NumberUtils.intToFloatColor((i4 << 24) | (i3 << 16) | (i2 << 8) | i);
    }

    public static float toFloatBits(float f, float f2, float f3, float f4) {
        return NumberUtils.intToFloatColor((((int) (255.0f * f4)) << 24) | (((int) (255.0f * f3)) << 16) | (((int) (255.0f * f2)) << 8) | ((int) (255.0f * f)));
    }

    public static int toIntBits(int i, int i2, int i3, int i4) {
        return (i4 << 24) | (i3 << 16) | (i2 << 8) | i;
    }

    public static int alpha(float f) {
        return (int) (f * 255.0f);
    }

    public static int luminanceAlpha(float f, float f2) {
        return (((int) (f * 255.0f)) << 8) | ((int) (f2 * 255.0f));
    }

    public static int rgb565(float f, float f2, float f3) {
        return (((int) (f * 31.0f)) << 11) | (((int) (f2 * 63.0f)) << 5) | ((int) (f3 * 31.0f));
    }

    public static int rgba4444(float f, float f2, float f3, float f4) {
        return (((int) (f * 15.0f)) << 12) | (((int) (f2 * 15.0f)) << 8) | (((int) (f3 * 15.0f)) << 4) | ((int) (f4 * 15.0f));
    }

    public static int rgb888(float f, float f2, float f3) {
        return (((int) (f * 255.0f)) << 16) | (((int) (f2 * 255.0f)) << 8) | ((int) (f3 * 255.0f));
    }

    public static int rgba8888(float f, float f2, float f3, float f4) {
        return (((int) (f * 255.0f)) << 24) | (((int) (f2 * 255.0f)) << 16) | (((int) (f3 * 255.0f)) << 8) | ((int) (f4 * 255.0f));
    }

    public static int argb8888(float f, float f2, float f3, float f4) {
        return (((int) (f * 255.0f)) << 24) | (((int) (f2 * 255.0f)) << 16) | (((int) (f3 * 255.0f)) << 8) | ((int) (f4 * 255.0f));
    }

    public static int rgb565(Color color) {
        return (((int) (color.r * 31.0f)) << 11) | (((int) (color.g * 63.0f)) << 5) | ((int) (color.f888b * 31.0f));
    }

    public static int rgba4444(Color color) {
        return (((int) (color.r * 15.0f)) << 12) | (((int) (color.g * 15.0f)) << 8) | (((int) (color.f888b * 15.0f)) << 4) | ((int) (color.f889a * 15.0f));
    }

    public static int rgb888(Color color) {
        return (((int) (color.r * 255.0f)) << 16) | (((int) (color.g * 255.0f)) << 8) | ((int) (color.f888b * 255.0f));
    }

    public static int rgba8888(Color color) {
        return (((int) (color.r * 255.0f)) << 24) | (((int) (color.g * 255.0f)) << 16) | (((int) (color.f888b * 255.0f)) << 8) | ((int) (color.f889a * 255.0f));
    }

    public static int argb8888(Color color) {
        return (((int) (color.f889a * 255.0f)) << 24) | (((int) (color.r * 255.0f)) << 16) | (((int) (color.g * 255.0f)) << 8) | ((int) (color.f888b * 255.0f));
    }

    public static void rgb565ToColor(Color color, int i) {
        color.r = ((i & PegdownExtensions.UNUSED_ALL) >>> 11) / 31.0f;
        color.g = ((i & 2016) >>> 5) / 63.0f;
        color.f888b = (i & 31) / 31.0f;
    }

    public static void rgba4444ToColor(Color color, int i) {
        color.r = ((i & 61440) >>> 12) / 15.0f;
        color.g = ((i & 3840) >>> 8) / 15.0f;
        color.f888b = ((i & User32.VK_OEM_ATTN) >>> 4) / 15.0f;
        color.f889a = (i & 15) / 15.0f;
    }

    public static void rgb888ToColor(Color color, int i) {
        color.r = ((i >>> 16) & 255) / 255.0f;
        color.g = ((i >>> 8) & 255) / 255.0f;
        color.f888b = (i & 255) / 255.0f;
    }

    public static void rgba8888ToColor(Color color, int i) {
        color.r = ((i & (-16777216)) >>> 24) / 255.0f;
        color.g = ((i >>> 16) & 255) / 255.0f;
        color.f888b = ((i >>> 8) & 255) / 255.0f;
        color.f889a = (i & 255) / 255.0f;
    }

    public static void argb8888ToColor(Color color, int i) {
        color.f889a = ((i & (-16777216)) >>> 24) / 255.0f;
        color.r = ((i >>> 16) & 255) / 255.0f;
        color.g = ((i >>> 8) & 255) / 255.0f;
        color.f888b = (i & 255) / 255.0f;
    }

    public static void abgr8888ToColor(Color color, int i) {
        color.f889a = ((i & (-16777216)) >>> 24) / 255.0f;
        color.f888b = ((i >>> 16) & 255) / 255.0f;
        color.g = ((i >>> 8) & 255) / 255.0f;
        color.r = (i & 255) / 255.0f;
    }

    public static void abgr8888ToColor(Color color, float f) {
        int floatToIntColor = NumberUtils.floatToIntColor(f);
        color.f889a = ((floatToIntColor & (-16777216)) >>> 24) / 255.0f;
        color.f888b = ((floatToIntColor >>> 16) & 255) / 255.0f;
        color.g = ((floatToIntColor >>> 8) & 255) / 255.0f;
        color.r = (floatToIntColor & 255) / 255.0f;
    }

    public Color fromHsv(float f, float f2, float f3) {
        float f4 = ((f / 60.0f) + 6.0f) % 6.0f;
        int i = (int) f4;
        float f5 = f4 - i;
        float f6 = f3 * (1.0f - f2);
        float f7 = f3 * (1.0f - (f2 * f5));
        float f8 = f3 * (1.0f - (f2 * (1.0f - f5)));
        switch (i) {
            case 0:
                this.r = f3;
                this.g = f8;
                this.f888b = f6;
                break;
            case 1:
                this.r = f7;
                this.g = f3;
                this.f888b = f6;
                break;
            case 2:
                this.r = f6;
                this.g = f3;
                this.f888b = f8;
                break;
            case 3:
                this.r = f6;
                this.g = f7;
                this.f888b = f3;
                break;
            case 4:
                this.r = f8;
                this.g = f6;
                this.f888b = f3;
                break;
            default:
                this.r = f3;
                this.g = f6;
                this.f888b = f7;
                break;
        }
        return clamp();
    }

    public Color fromHsv(float[] fArr) {
        return fromHsv(fArr[0], fArr[1], fArr[2]);
    }

    public float[] toHsv(float[] fArr) {
        float max = Math.max(Math.max(this.r, this.g), this.f888b);
        float min = Math.min(Math.min(this.r, this.g), this.f888b);
        float f = max - min;
        if (f == 0.0f) {
            fArr[0] = 0.0f;
        } else if (max == this.r) {
            fArr[0] = (((60.0f * (this.g - this.f888b)) / f) + 360.0f) % 360.0f;
        } else if (max == this.g) {
            fArr[0] = ((60.0f * (this.f888b - this.r)) / f) + 120.0f;
        } else {
            fArr[0] = ((60.0f * (this.r - this.g)) / f) + 240.0f;
        }
        if (max > 0.0f) {
            fArr[1] = 1.0f - (min / max);
        } else {
            fArr[1] = 0.0f;
        }
        fArr[2] = max;
        return fArr;
    }

    public Color cpy() {
        return new Color(this);
    }
}
