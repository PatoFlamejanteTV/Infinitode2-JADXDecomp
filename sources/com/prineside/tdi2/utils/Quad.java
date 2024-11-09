package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.kryo.FixedInput;
import com.prineside.kryo.FixedOutput;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.scene2d.utils.TransformDrawable;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/Quad.class */
public final class Quad extends AbstractDrawable implements TransformDrawable {
    public static final float BINARY_FLOAT_PRECISION = 100.0f;
    public static final float BINARY_FLOAT_PRECISION_MUL = 0.01f;
    private final Array<QuadRegion> e = new Array<>(true, 1, QuadRegion.class);
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    public boolean debugging;

    /* renamed from: b, reason: collision with root package name */
    private static final TLog f3888b = TLog.forClass(Quad.class);
    private static Quad c = null;
    private static final byte[][] d = {new byte[]{0, 1, 0, 0}, new byte[]{0, 1, 1, 0}, new byte[]{1, 1, 0, 0}, new byte[]{0, 0, 0, 1}, new byte[]{0, 0, 1, 1}, new byte[]{1, 0, 0, 1}, new byte[]{0, 0, 0, 0}, new byte[]{0, 0, 1, 0}, new byte[]{1, 0, 0, 0}};
    private static final StringBuilder l = new StringBuilder();
    public static final FixedOutput outputHelper = new FixedOutput(1024, -1);
    private static final FixedInput m = new FixedInput(8);

    public static Quad getNoQuad() {
        if (c == null) {
            c = fromString("YxQVYDYVEPj//z8/gykHR2PDN3YGWw4gALM4OAA=");
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/Quad$Prepared.class */
    public static final class Prepared {

        /* renamed from: a, reason: collision with root package name */
        private static final Array<QuadRegion> f3889a = new Array<>(true, 8, QuadRegion.class);

        /* renamed from: b, reason: collision with root package name */
        private static final FloatArray f3890b = new FloatArray(true, 32);
        private static int c;
        private static float d;
        private static float e;

        private Prepared() {
        }
    }

    private Quad() {
    }

    public Quad(float f, float f2) {
        setWidth(f);
        setHeight(f2);
        setPivot(f * 0.5f, f2 * 0.5f);
    }

    public Quad(Quad quad, boolean z) {
        set(quad, z);
    }

    public Quad(ResourcePack.AtlasTextureRegion atlasTextureRegion) {
        addRegion(new QuadRegion(atlasTextureRegion, 0.0f, 0.0f, atlasTextureRegion.getRegionWidth(), atlasTextureRegion.getRegionHeight()));
        setWidth(atlasTextureRegion.getRegionWidth());
        setHeight(atlasTextureRegion.getRegionHeight());
        setPivot(this.f * 0.5f, this.g * 0.5f);
    }

    public final boolean sameAs(Quad quad) {
        if (this == quad) {
            return true;
        }
        if (this.f != quad.f || this.g != quad.g || this.h != quad.h || this.i != quad.i || getMinWidth() != quad.getMinWidth() || getMinHeight() != quad.getMinHeight() || getLeftWidth() != quad.getLeftWidth() || getRightWidth() != quad.getRightWidth() || getTopHeight() != quad.getTopHeight() || getBottomHeight() != quad.getBottomHeight() || this.e.size != quad.e.size) {
            return false;
        }
        for (int i = 0; i < this.e.size; i++) {
            if (!this.e.items[i].sameAs(quad.e.items[i])) {
                return false;
            }
        }
        return true;
    }

    public final void set(Quad quad, boolean z) {
        this.e.clear();
        if (z) {
            for (int i = 0; i < quad.e.size; i++) {
                this.e.add(new QuadRegion(quad.e.items[i]));
            }
        } else {
            this.e.addAll(quad.e);
        }
        setWidth(quad.f);
        setHeight(quad.g);
        this.h = quad.h;
        this.i = quad.i;
        if (quad.f3812a != null) {
            a();
            System.arraycopy(quad.f3812a, 0, this.f3812a, 0, this.f3812a.length);
        }
    }

    public final void addRegion(QuadRegion quadRegion) {
        this.e.add(quadRegion);
    }

    public final void appendSet(Quad quad, Color color, float f, float f2, float f3, float f4) {
        float f5 = f3 / quad.f;
        float f6 = f4 / quad.g;
        Array.ArrayIterator<QuadRegion> it = quad.e.iterator();
        while (it.hasNext()) {
            QuadRegion quadRegion = new QuadRegion(it.next());
            quadRegion.multiplyCornerColors(color);
            float[] cornerPositions = quadRegion.getCornerPositions();
            for (int i = 0; i < 4; i++) {
                cornerPositions[i << 1] = (cornerPositions[i << 1] * f5) + f;
                cornerPositions[(i << 1) + 1] = (cornerPositions[(i << 1) + 1] * f6) + f2;
            }
            quadRegion.setCornerPositions(cornerPositions);
            this.e.add(quadRegion);
        }
    }

    public final void replaceRegionsColor(Color color, Color color2) {
        Array.ArrayIterator<QuadRegion> it = this.e.iterator();
        while (it.hasNext()) {
            QuadRegion next = it.next();
            if (next.getCornerColors().isSingleColor()) {
                next.setSameCornerColors(color2);
            }
        }
    }

    public final Array<QuadRegion> getRegions() {
        return this.e;
    }

    public final Quad multiplyRegionColors(Color color) {
        for (int i = 0; i < this.e.size; i++) {
            this.e.items[i].multiplyCornerColors(color);
        }
        return this;
    }

    public final float getWidth() {
        return this.f;
    }

    public final void setWidth(float f) {
        this.f = f;
        this.j = 1.0f / Math.max(1.0E-4f, f);
    }

    public final float getHeight() {
        return this.g;
    }

    public final void setHeight(float f) {
        this.g = f;
        this.k = 1.0f / Math.max(1.0E-4f, f);
    }

    public final void setPivot(float f, float f2) {
        this.h = f;
        this.i = f2;
    }

    public final float getPivotX() {
        return this.h;
    }

    public final float getPivotY() {
        return this.i;
    }

    public final void setSize(float f, float f2) {
        setWidth(f);
        setHeight(f2);
    }

    public static Quad fromString(String str) {
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                break;
            }
            char charAt = str.charAt(i);
            if (charAt == ' ' || charAt == '\t' || charAt == '\r' || charAt == '\n') {
                i++;
            } else if (charAt == '[') {
                try {
                    return fromJson(new JsonReader().parse(str));
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to read QuadRegionSet from string '" + str + "'", e);
                }
            }
        }
        return fromByteArray(StringFormatter.fromCompactBase64(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x01e0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01fd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0209 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0215 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0221 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x022d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0239 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00dd A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.prineside.tdi2.utils.Quad fromJson(com.a.a.c.j.a r4, com.prineside.tdi2.utils.AssetProvider<com.badlogic.gdx.graphics.g2d.TextureRegion> r5) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.utils.Quad.fromJson(com.a.a.c.j.a, com.prineside.tdi2.utils.AssetProvider):com.prineside.tdi2.utils.Quad");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x01b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01dd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01e9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01f5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0201 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x020d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.prineside.tdi2.utils.Quad fromJson(com.badlogic.gdx.utils.JsonValue r4) {
        /*
            Method dump skipped, instructions count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.utils.Quad.fromJson(com.badlogic.gdx.utils.JsonValue):com.prineside.tdi2.utils.Quad");
    }

    public final void toJson5String(StringBuilder stringBuilder) {
        toJson5StringWithIndent(stringBuilder, 0);
    }

    public final void toJson5StringWithIndent(StringBuilder stringBuilder, int i) {
        stringBuilder.append("[\n");
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append("  [");
        sbWriteIntOrFloat(stringBuilder, this.f);
        if (this.f != this.g) {
            stringBuilder.append(',');
            sbWriteIntOrFloat(stringBuilder, this.g);
        }
        stringBuilder.append("],[\n");
        for (int i3 = 0; i3 < this.e.size; i3++) {
            QuadRegion quadRegion = this.e.items[i3];
            for (int i4 = 0; i4 < i; i4++) {
                stringBuilder.append(' ');
            }
            stringBuilder.append("    ");
            quadRegion.toJson5String(stringBuilder);
            if (i3 != this.e.size - 1) {
                stringBuilder.append(',');
            }
            stringBuilder.append('\n');
        }
        for (int i5 = 0; i5 < i; i5++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append("  ]");
        if (b()) {
            stringBuilder.append(",{");
            boolean z = false;
            if (this.h != this.f * 0.5f || this.i != this.g * 0.5f) {
                stringBuilder.append("p:[");
                sbWriteIntOrFloat(stringBuilder, this.h);
                stringBuilder.append(',');
                sbWriteIntOrFloat(stringBuilder, this.i);
                stringBuilder.append(']');
                z = true;
            }
            if (getLeftWidth() != 0.0f) {
                if (z) {
                    stringBuilder.append(',');
                }
                stringBuilder.append("lw:");
                sbWriteIntOrFloat(stringBuilder, getLeftWidth());
                z = true;
            }
            if (getRightWidth() != 0.0f) {
                if (z) {
                    stringBuilder.append(',');
                }
                stringBuilder.append("rw:");
                sbWriteIntOrFloat(stringBuilder, getRightWidth());
                z = true;
            }
            if (getTopHeight() != 0.0f) {
                if (z) {
                    stringBuilder.append(',');
                }
                stringBuilder.append("th:");
                sbWriteIntOrFloat(stringBuilder, getTopHeight());
                z = true;
            }
            if (getBottomHeight() != 0.0f) {
                if (z) {
                    stringBuilder.append(',');
                }
                stringBuilder.append("bh:");
                sbWriteIntOrFloat(stringBuilder, getBottomHeight());
                z = true;
            }
            if (getMinWidth() != 0.0f) {
                if (z) {
                    stringBuilder.append(',');
                }
                stringBuilder.append("mw:");
                sbWriteIntOrFloat(stringBuilder, getMinWidth());
                z = true;
            }
            if (getMinHeight() != 0.0f) {
                if (z) {
                    stringBuilder.append(',');
                }
                stringBuilder.append("mh:");
                sbWriteIntOrFloat(stringBuilder, getMinHeight());
            }
            stringBuilder.append('}');
        }
        stringBuilder.append(SequenceUtils.EOL);
        for (int i6 = 0; i6 < i; i6++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append("]");
    }

    public static boolean floatIsInt(float f) {
        return MathUtils.round(f * 100.0f) % 100 == 0;
    }

    public static String stripNonAscii(String str) {
        boolean z = true;
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (str.charAt(i) <= 127) {
                i++;
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt <= 127) {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }

    public static void jsonWriteIntOrFloat(Json json, float f) {
        if (floatIsInt(f)) {
            json.writeValue(Integer.valueOf(MathUtils.round(f)));
        } else {
            json.writeValue(Float.valueOf(f));
        }
    }

    public static void sbWriteIntOrFloat(StringBuilder stringBuilder, float f) {
        if (floatIsInt(f)) {
            stringBuilder.append(MathUtils.round(f));
            return;
        }
        stringBuilder.append((int) f).append('.');
        int abs = Math.abs(MathUtils.round((f - ((int) f)) * 100.0f));
        if (abs < 10) {
            stringBuilder.append('0');
        }
        if (abs % 10 == 0) {
            stringBuilder.append(abs / 10);
        } else {
            stringBuilder.append(abs);
        }
    }

    public static String intOrFloatString(float f) {
        l.setLength(0);
        sbWriteIntOrFloat(l, f);
        return l.toString();
    }

    public final void toBytes(FixedOutput fixedOutput) {
        fixedOutput.writeByte((byte) 1);
        boolean z = this.f == this.g;
        boolean z2 = this.e.size == 1;
        boolean z3 = (this.h == this.f * 0.5f && this.i == this.g * 0.5f) ? false : true;
        boolean z4 = (getMinWidth() == 0.0f && getMinHeight() == 0.0f) ? false : true;
        boolean z5 = (getLeftWidth() == 0.0f && getRightWidth() == 0.0f) ? false : true;
        boolean z6 = (getBottomHeight() == 0.0f && getTopHeight() == 0.0f) ? false : true;
        boolean z7 = floatIsInt(this.f) && floatIsInt(this.g) && floatIsInt(getMinWidth()) && floatIsInt(getMinHeight()) && floatIsInt(getLeftWidth()) && floatIsInt(getRightWidth()) && floatIsInt(getBottomHeight()) && floatIsInt(getTopHeight());
        if (z3) {
            z7 = z7 && floatIsInt(this.h) && floatIsInt(this.i);
        }
        byte b2 = 0;
        if (z) {
            b2 = 1;
        }
        if (z2) {
            b2 = (byte) (b2 | 2);
        }
        if (z7) {
            b2 = (byte) (b2 | 4);
        }
        if (z3) {
            b2 = (byte) (b2 | 8);
        }
        if (z4) {
            b2 = (byte) (b2 | 16);
        }
        if (z5) {
            b2 = (byte) (b2 | 32);
        }
        if (z6) {
            b2 = (byte) (b2 | 64);
        }
        fixedOutput.writeByte(b2);
        if (z) {
            if (z7) {
                fixedOutput.writeVarInt(MathUtils.round(this.f), true);
            } else {
                fixedOutput.writeVarInt(MathUtils.round(this.f * 100.0f), true);
            }
        } else if (z7) {
            fixedOutput.writeVarInt(MathUtils.round(this.f), true);
            fixedOutput.writeVarInt(MathUtils.round(this.g), true);
        } else {
            fixedOutput.writeVarInt(MathUtils.round(this.f * 100.0f), true);
            fixedOutput.writeVarInt(MathUtils.round(this.g * 100.0f), true);
        }
        if (z2) {
            this.e.first().toBytes(fixedOutput);
        } else {
            fixedOutput.writeVarInt(this.e.size, true);
            Array.ArrayIterator<QuadRegion> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().toBytes(fixedOutput);
            }
        }
        if (z3) {
            if (z7) {
                fixedOutput.writeVarInt(MathUtils.round(this.h), true);
                fixedOutput.writeVarInt(MathUtils.round(this.i), true);
            } else {
                fixedOutput.writeVarInt(MathUtils.round(this.h * 100.0f), true);
                fixedOutput.writeVarInt(MathUtils.round(this.i * 100.0f), true);
            }
        }
        if (z4) {
            if (z7) {
                fixedOutput.writeVarInt(MathUtils.round(getMinWidth()), true);
                fixedOutput.writeVarInt(MathUtils.round(getMinHeight()), true);
            } else {
                fixedOutput.writeVarInt(MathUtils.round(getMinWidth() * 100.0f), true);
                fixedOutput.writeVarInt(MathUtils.round(getMinHeight() * 100.0f), true);
            }
        }
        if (z5) {
            if (z7) {
                fixedOutput.writeVarInt(MathUtils.round(getLeftWidth()), true);
                fixedOutput.writeVarInt(MathUtils.round(getRightWidth()), true);
            } else {
                fixedOutput.writeVarInt(MathUtils.round(getLeftWidth() * 100.0f), true);
                fixedOutput.writeVarInt(MathUtils.round(getRightWidth() * 100.0f), true);
            }
        }
        if (z6) {
            if (z7) {
                fixedOutput.writeVarInt(MathUtils.round(getBottomHeight()), true);
                fixedOutput.writeVarInt(MathUtils.round(getTopHeight()), true);
            } else {
                fixedOutput.writeVarInt(MathUtils.round(getBottomHeight() * 100.0f), true);
                fixedOutput.writeVarInt(MathUtils.round(getTopHeight() * 100.0f), true);
            }
        }
    }

    public static Quad fromByteArray(byte[] bArr) {
        m.setBuffer(bArr);
        m.setPosition(0);
        return fromBytes(m);
    }

    public static Quad fromBytes(FixedInput fixedInput) {
        float readVarInt;
        float readVarInt2;
        if (fixedInput.readByte() != 1) {
            throw new IllegalArgumentException("Version not supported");
        }
        byte readByte = fixedInput.readByte();
        boolean z = (readByte & 1) != 0;
        boolean z2 = (readByte & 2) != 0;
        boolean z3 = (readByte & 4) != 0;
        boolean z4 = (readByte & 8) != 0;
        boolean z5 = (readByte & 16) != 0;
        boolean z6 = (readByte & 32) != 0;
        boolean z7 = (readByte & 64) != 0;
        if (z) {
            if (z3) {
                float readVarInt3 = fixedInput.readVarInt(true);
                readVarInt2 = readVarInt3;
                readVarInt = readVarInt3;
            } else {
                float readVarInt4 = fixedInput.readVarInt(true) * 0.01f;
                readVarInt2 = readVarInt4;
                readVarInt = readVarInt4;
            }
        } else if (z3) {
            readVarInt = fixedInput.readVarInt(true);
            readVarInt2 = fixedInput.readVarInt(true);
        } else {
            readVarInt = fixedInput.readVarInt(true) * 0.01f;
            readVarInt2 = fixedInput.readVarInt(true) * 0.01f;
        }
        Quad quad = new Quad(readVarInt, readVarInt2);
        if (z2) {
            quad.addRegion(QuadRegion.fromBytes(fixedInput));
        } else {
            int readVarInt5 = fixedInput.readVarInt(true);
            for (int i = 0; i < readVarInt5; i++) {
                quad.addRegion(QuadRegion.fromBytes(fixedInput));
            }
        }
        if (z4) {
            if (z3) {
                quad.h = fixedInput.readVarInt(true);
                quad.i = fixedInput.readVarInt(true);
            } else {
                quad.h = fixedInput.readVarInt(true) * 0.01f;
                quad.i = fixedInput.readVarInt(true) * 0.01f;
            }
        }
        if (z5) {
            if (z3) {
                quad.setMinWidth(fixedInput.readVarInt(true));
                quad.setMinHeight(fixedInput.readVarInt(true));
            } else {
                quad.setMinWidth(fixedInput.readVarInt(true) * 0.01f);
                quad.setMinHeight(fixedInput.readVarInt(true) * 0.01f);
            }
        }
        if (z6) {
            if (z3) {
                quad.setLeftWidth(fixedInput.readVarInt(true));
                quad.setRightWidth(fixedInput.readVarInt(true));
            } else {
                quad.setLeftWidth(fixedInput.readVarInt(true) * 0.01f);
                quad.setRightWidth(fixedInput.readVarInt(true) * 0.01f);
            }
        }
        if (z7) {
            if (z3) {
                quad.setBottomHeight(fixedInput.readVarInt(true));
                quad.setTopHeight(fixedInput.readVarInt(true));
            } else {
                quad.setBottomHeight(fixedInput.readVarInt(true) * 0.01f);
                quad.setTopHeight(fixedInput.readVarInt(true) * 0.01f);
            }
        }
        return quad;
    }

    private boolean b() {
        return (getMinWidth() == 0.0f && getMinHeight() == 0.0f && getLeftWidth() == 0.0f && getRightWidth() == 0.0f && getTopHeight() == 0.0f && getBottomHeight() == 0.0f && this.h == this.f * 0.5f && this.i == this.g * 0.5f) ? false : true;
    }

    public final String toBase64() {
        outputHelper.setPosition(0);
        toBytes(outputHelper);
        return StringFormatter.toCompactBase64(outputHelper.getBuffer(), 0, outputHelper.position());
    }

    public final void toJson(Json json) {
        json.writeArrayStart();
        json.writeArrayStart();
        jsonWriteIntOrFloat(json, this.f);
        if (this.f != this.g) {
            jsonWriteIntOrFloat(json, this.g);
        }
        json.writeArrayEnd();
        json.writeArrayStart();
        Array.ArrayIterator<QuadRegion> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().toJson(json);
        }
        json.writeArrayEnd();
        if (b()) {
            json.writeObjectStart();
            if (this.h != this.f * 0.5f || this.i != this.g * 0.5f) {
                json.writeArrayStart(FlexmarkHtmlConverter.P_NODE);
                jsonWriteIntOrFloat(json, this.h);
                jsonWriteIntOrFloat(json, this.i);
                json.writeArrayEnd();
            }
            if (getMinWidth() != 0.0f) {
                json.writeValue("mw", Float.valueOf(getMinWidth()));
            }
            if (getMinHeight() != 0.0f) {
                json.writeValue("mh", Float.valueOf(getMinHeight()));
            }
            if (getLeftWidth() != 0.0f) {
                json.writeValue("lw", Float.valueOf(getLeftWidth()));
            }
            if (getRightWidth() != 0.0f) {
                json.writeValue("rw", Float.valueOf(getRightWidth()));
            }
            if (getTopHeight() != 0.0f) {
                json.writeValue(FlexmarkHtmlConverter.TH_NODE, Float.valueOf(getTopHeight()));
            }
            if (getBottomHeight() != 0.0f) {
                json.writeValue("bh", Float.valueOf(getBottomHeight()));
            }
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
    }

    private void a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (this.debugging) {
            f3888b.i("prepareDrawConfigs", new Object[0]);
        }
        float cosDeg = MathUtils.cosDeg(f7);
        float sinDeg = MathUtils.sinDeg(f7);
        float f8 = f3 * this.j;
        float f9 = f4 * this.k;
        float unused = Prepared.d = cosDeg;
        float unused2 = Prepared.e = sinDeg;
        if (Prepared.f3889a.size < this.e.size) {
            Prepared.f3889a.setSize(this.e.size);
        }
        if (Prepared.f3890b.size < (this.e.size << 2)) {
            Prepared.f3890b.setSize(this.e.size << 2);
        }
        float[] fArr = Prepared.f3890b.items;
        int i = 0;
        for (int i2 = 0; i2 < this.e.size; i2++) {
            QuadRegion quadRegion = this.e.items[i2];
            if (quadRegion.isVisible()) {
                Prepared.f3889a.set(i, quadRegion);
                if (quadRegion.getNinePathRegion() != 0) {
                    byte[] bArr = d[quadRegion.getNinePathRegion() - 1];
                    float f10 = f3 - this.f;
                    float f11 = f4 - this.g;
                    float x = (bArr[0] * f10) + quadRegion.getX();
                    float y = (bArr[1] * f11) + quadRegion.getY();
                    fArr[(i << 2) + 2] = (bArr[2] * f10) + quadRegion.getWidth();
                    fArr[(i << 2) + 3] = (bArr[3] * f11) + quadRegion.getHeight();
                    float f12 = x * f5;
                    float f13 = y * f6;
                    if (f7 != 0.0f) {
                        f12 = (cosDeg * f12) - (sinDeg * f13);
                        f13 = (sinDeg * f12) + (cosDeg * f13);
                    }
                    fArr[i << 2] = f + f12;
                    fArr[(i << 2) + 1] = f2 + f13;
                } else {
                    float x2 = quadRegion.getX() * f8 * f5;
                    float y2 = quadRegion.getY() * f9 * f6;
                    if (f7 != 0.0f) {
                        x2 = (cosDeg * x2) - (sinDeg * y2);
                        y2 = (sinDeg * x2) + (cosDeg * y2);
                        if (this.debugging) {
                            f3888b.d("apply rotation: pXo: %s, pYo: %s, pX: %s, pY: %s, cos: %s, sin: %s", Float.valueOf(x2), Float.valueOf(y2), Float.valueOf(x2), Float.valueOf(y2), Float.valueOf(cosDeg), Float.valueOf(sinDeg));
                            f3888b.d("  r.x: %s, r.y: %s, rsX: %s, rsY: %s, scaleX: %s, scaleY: %s", Float.valueOf(quadRegion.getX()), Float.valueOf(quadRegion.getY()), Float.valueOf(f8), Float.valueOf(f9), Float.valueOf(f5), Float.valueOf(f6));
                        }
                    }
                    fArr[i << 2] = f + x2;
                    fArr[(i << 2) + 1] = f2 + y2;
                    fArr[(i << 2) + 2] = quadRegion.getWidth() * f8;
                    fArr[(i << 2) + 3] = quadRegion.getHeight() * f9;
                    if (this.debugging) {
                        f3888b.d("x: %s, y: %s, w: %s, h: %s", Float.valueOf(fArr[i << 2]), Float.valueOf(fArr[(i << 2) + 1]), Float.valueOf(fArr[(i << 2) + 2]), Float.valueOf(fArr[(i << 2) + 3]));
                    }
                }
                i++;
            }
        }
        int unused3 = Prepared.c = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void drawToCache(SpriteCache spriteCache, float f, float f2, float f3, float f4) {
        a(f, f2, f3, f4, 1.0f, 1.0f, 0.0f);
        for (int i = 0; i < Prepared.c; i++) {
            float[] fArr = Prepared.f3890b.items;
            ((QuadRegion[]) Prepared.f3889a.items)[i].drawToCache(spriteCache, fArr[i << 2], fArr[(i << 2) + 1], fArr[(i << 2) + 2], fArr[(i << 2) + 3]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4) {
        if (this.debugging) {
            f3888b.i("draw 4f", new Object[0]);
        }
        a(f, f2, f3, f4, 1.0f, 1.0f, 0.0f);
        for (int i = 0; i < Prepared.c; i++) {
            float[] fArr = Prepared.f3890b.items;
            ((QuadRegion[]) Prepared.f3889a.items)[i].draw(batch, fArr[i << 2], fArr[(i << 2) + 1], fArr[(i << 2) + 2], fArr[(i << 2) + 3]);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (this.debugging) {
            f3888b.d("draw B7f", new Object[0]);
        }
        draw(batch, f, f2, this.h, this.i, f3, f4, f5, f6, f7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.scene2d.utils.TransformDrawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (this.debugging) {
            f3888b.d("draw B9f", new Object[0]);
        }
        a(f, f2, f5, f6, f7, f8, f9);
        for (int i = 0; i < Prepared.c; i++) {
            float[] fArr = Prepared.f3890b.items;
            ((QuadRegion[]) Prepared.f3889a.items)[i].a(batch, fArr[i << 2], fArr[(i << 2) + 1], f3, f4, fArr[(i << 2) + 2], fArr[(i << 2) + 3], f7, f8, Prepared.d, Prepared.e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void drawDebug(ShapeRenderer shapeRenderer, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        a(f, f2, f5, f6, f7, f8, f9);
        for (int i = 0; i < Prepared.c; i++) {
            float[] fArr = Prepared.f3890b.items;
            ((QuadRegion[]) Prepared.f3889a.items)[i].drawDebug(shapeRenderer, fArr[i << 2], fArr[(i << 2) + 1], f3, f4, fArr[(i << 2) + 2], fArr[(i << 2) + 3], f7, f8, Prepared.d, Prepared.e);
        }
    }
}
