package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Disableable;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.FocusListener;
import com.prineside.tdi2.scene2d.utils.UIUtils;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.TableButton;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField.class */
public class TextField extends WidgetGroup implements Disableable {
    private static final TLog k = TLog.forClass(TextField.class);
    private static final Vector2 l = new Vector2();
    private static final Vector2 m = new Vector2();
    private static final Vector2 n = new Vector2();
    public static float keyRepeatInitialTime = 0.3f;
    public static float keyRepeatTime = 0.05f;
    protected String o;
    protected int p;
    protected int q;
    protected boolean r;
    public boolean writeEnters;
    TextFieldStyle t;
    private String R;
    protected CharSequence u;
    Clipboard v;
    InputListener w;

    @Null
    TextFieldListener x;

    @Null
    TextFieldFilter y;
    boolean C;
    private float T;
    private float U;
    int E;
    long F;
    boolean G;
    private StringBuilder V;
    private float X;
    protected float H;
    private float Y;
    float I;
    private int Z;
    private int aa;
    private int ab;
    boolean J;
    boolean K;
    private ContextMenu ac;
    private boolean ad;
    private int O = -1;
    private boolean P = false;
    private GlyphLayout Q = new GlyphLayout();
    protected final FloatArray s = new FloatArray();
    OnscreenKeyboard z = new DefaultOnscreenKeyboard();
    boolean A = true;
    boolean B = true;
    private int S = 8;
    String D = "";
    private char W = 149;
    public boolean replaceTabsWithSpaces = true;
    float L = 0.32f;
    final Timer.Task M = new Timer.Task() { // from class: com.prineside.tdi2.scene2d.ui.TextField.1
        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
        public void run() {
            if (TextField.this.getStage() == null) {
                cancel();
                return;
            }
            TextField.this.K = !TextField.this.K;
            Gdx.graphics.requestRendering();
        }
    };
    final KeyRepeatTask N = new KeyRepeatTask();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$OnscreenKeyboard.class */
    public interface OnscreenKeyboard {
        void show(boolean z);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$TextFieldListener.class */
    public interface TextFieldListener {
        void keyTyped(TextField textField, char c);
    }

    public TextField(@Null String str, TextFieldStyle textFieldStyle) {
        setStyle(textFieldStyle);
        this.v = Gdx.app.getClipboard();
        d();
        setText(str);
        setSize(getPrefWidth(), getPrefHeight());
        Game.i.cursorGraphics.setActorCustomMouseCursor(this, Cursor.SystemCursor.Ibeam);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        InputListener h = h();
        this.w = h;
        addListener(h);
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            addListener(new FocusListener() { // from class: com.prineside.tdi2.scene2d.ui.TextField.2
                @Override // com.prineside.tdi2.scene2d.utils.FocusListener
                public void keyboardFocusChanged(FocusListener.FocusEvent focusEvent, Actor actor, boolean z) {
                    Game.i.actionResolver.handleTextFieldFocus(focusEvent, TextField.this, z);
                }
            });
        }
    }

    protected InputListener h() {
        return new TextFieldClickListener();
    }

    public int letterUnderCursor(float f) {
        float f2 = f - (((this.Y + this.X) - this.t.font.getData().cursorX) - this.s.get(this.Z));
        if (i() != null) {
            f2 -= this.t.background.getLeftWidth();
        }
        int i = this.s.size;
        float[] fArr = this.s.items;
        for (int i2 = 1; i2 < i; i2++) {
            if (fArr[i2] > f2) {
                return fArr[i2] - f2 <= f2 - fArr[i2 - 1] ? i2 : i2 - 1;
            }
        }
        return i - 1;
    }

    private static boolean a(char c) {
        return Character.isLetterOrDigit(c);
    }

    private int[] b(int i) {
        String str = this.o;
        int length = str.length();
        int i2 = 0;
        int i3 = i;
        if (i >= str.length()) {
            i2 = str.length();
            length = 0;
        } else {
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (a(str.charAt(i3))) {
                    i3++;
                } else {
                    length = i3;
                    break;
                }
            }
            int i4 = i - 1;
            while (true) {
                if (i4 < 0) {
                    break;
                }
                if (a(str.charAt(i4))) {
                    i4--;
                } else {
                    i2 = i4 + 1;
                    break;
                }
            }
        }
        return new int[]{i2, length};
    }

    final int[] a(float f) {
        return b(letterUnderCursor(f));
    }

    final boolean a(int i) {
        return this.ab <= 0 || i < this.ab;
    }

    public void setMaxLength(int i) {
        this.ab = i;
    }

    public int getMaxLength() {
        return this.ab;
    }

    public void setOnlyFontChars(boolean z) {
        this.B = z;
    }

    public void setStyle(TextFieldStyle textFieldStyle) {
        if (textFieldStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.t = textFieldStyle;
        this.H = textFieldStyle.font.getCapHeight() - (textFieldStyle.font.getDescent() * 2.0f);
        if (this.o != null) {
            j();
        }
        invalidateHierarchy();
    }

    public TextFieldStyle getStyle() {
        return this.t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g() {
        float width = getWidth();
        Drawable i = i();
        if (i != null) {
            width -= i.getLeftWidth() + i.getRightWidth();
        }
        int i2 = this.s.size;
        float[] fArr = this.s.items;
        int i3 = this.p;
        this.p = MathUtils.clamp(this.p, 0, i2 - 1);
        float f = fArr[Math.max(0, this.p - 1)] + this.I;
        if (f <= 0.0f) {
            this.I -= f;
        } else {
            float f2 = fArr[Math.min(i2 - 1, this.p + 1)] - width;
            if ((-this.I) < f2) {
                this.I = -f2;
            }
        }
        float f3 = 0.0f;
        float f4 = fArr[i2 - 1];
        for (int i4 = i2 - 2; i4 >= 0; i4--) {
            float f5 = fArr[i4];
            if (f4 - f5 > width) {
                break;
            }
            f3 = f5;
        }
        if ((-this.I) > f3) {
            this.I = -f3;
        }
        this.Z = 0;
        float f6 = 0.0f;
        int i5 = 0;
        while (true) {
            if (i5 >= i2) {
                break;
            }
            if (fArr[i5] < (-this.I)) {
                i5++;
            } else {
                this.Z = i5;
                f6 = fArr[i5];
                break;
            }
        }
        int i6 = this.Z + 1;
        float f7 = width - this.I;
        int min = Math.min(this.u.length(), i2);
        while (i6 <= min && fArr[i6] <= f7) {
            i6++;
        }
        this.aa = Math.max(0, i6 - 1);
        if ((this.S & 8) == 0) {
            this.Y = ((width - fArr[this.aa]) - this.X) + f6;
            if ((this.S & 1) != 0) {
                this.Y = Math.round(this.Y * 0.5f);
            }
        } else {
            this.Y = f6 + this.I;
        }
        if (this.r) {
            int min2 = Math.min(this.p, this.q);
            int max = Math.max(this.p, this.q);
            float max2 = Math.max(fArr[min2] - fArr[this.Z], -this.Y);
            float min3 = Math.min(fArr[max] - fArr[this.Z], width - this.Y);
            this.T = max2;
            this.U = (min3 - max2) - this.t.font.getData().cursorX;
        }
        if (i3 != this.p) {
            updateContextMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Null
    public final Drawable i() {
        return (!this.C || this.t.disabledBackground == null) ? (this.t.focusedBackground == null || !hasKeyboardFocus()) ? this.t.background : this.t.focusedBackground : this.t.disabledBackground;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        Color color;
        boolean hasKeyboardFocus = hasKeyboardFocus();
        if (hasKeyboardFocus != this.J || (hasKeyboardFocus && !this.M.isScheduled())) {
            this.J = hasKeyboardFocus;
            e();
            this.M.cancel();
            this.K = hasKeyboardFocus;
            if (hasKeyboardFocus) {
                Timer.schedule(this.M, this.L, this.L);
            } else {
                this.N.cancel();
            }
        } else if (!hasKeyboardFocus) {
            this.K = false;
        }
        BitmapFont bitmapFont = this.t.font;
        if (!this.C || this.t.disabledFontColor == null) {
            color = (!hasKeyboardFocus || this.t.focusedFontColor == null) ? this.t.fontColor : this.t.focusedFontColor;
        } else {
            color = this.t.disabledFontColor;
        }
        Color color2 = color;
        Drawable drawable = this.t.selection;
        Drawable drawable2 = this.t.cursor;
        Drawable i = i();
        Color color3 = getColor();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        batch.setColor(color3.r, color3.g, color3.f888b, color3.f889a * f);
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (i != null) {
            i.draw(batch, x, y, width, height);
            f2 = i.getLeftWidth();
            f3 = i.getRightWidth();
        }
        float a2 = a(bitmapFont, i);
        g();
        if (hasKeyboardFocus && this.r && drawable != null) {
            a(drawable, batch, bitmapFont, x + f2, y + a2);
        }
        float f4 = bitmapFont.isFlipped() ? -this.H : 0.0f;
        if (this.u.length() == 0) {
            if ((!hasKeyboardFocus || this.C) && this.R != null) {
                BitmapFont bitmapFont2 = this.t.messageFont != null ? this.t.messageFont : bitmapFont;
                if (this.t.messageFontColor != null) {
                    bitmapFont2.setColor(this.t.messageFontColor.r, this.t.messageFontColor.g, this.t.messageFontColor.f888b, this.t.messageFontColor.f889a * color3.f889a * f);
                } else {
                    bitmapFont2.setColor(0.7f, 0.7f, 0.7f, color3.f889a * f);
                }
                a(batch, bitmapFont2, x + f2, y + a2 + f4, (width - f2) - f3);
            }
        } else {
            BitmapFont.BitmapFontData data = bitmapFont.getData();
            boolean z = data.markupEnabled;
            data.markupEnabled = false;
            bitmapFont.setColor(color2.r, color2.g, color2.f888b, color2.f889a * color3.f889a * f);
            a(batch, bitmapFont, x + f2, y + a2 + f4);
            data.markupEnabled = z;
        }
        if (!this.C && this.K && drawable2 != null) {
            b(drawable2, batch, bitmapFont, x + f2, y + a2);
        }
        super.draw(batch, f);
    }

    protected float a(BitmapFont bitmapFont, @Null Drawable drawable) {
        float f;
        float height = getHeight();
        float descent = (this.H / 2.0f) + bitmapFont.getDescent();
        if (drawable != null) {
            float bottomHeight = drawable.getBottomHeight();
            f = descent + (((height - drawable.getTopHeight()) - bottomHeight) / 2.0f) + bottomHeight;
        } else {
            f = descent + (height / 2.0f);
        }
        if (bitmapFont.usesIntegerPositions()) {
            f = (int) f;
        }
        return f;
    }

    protected void a(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        drawable.draw(batch, f + this.Y + this.T + this.X, (f2 - this.H) - bitmapFont.getDescent(), this.U, this.H);
    }

    public void getSelectionBoundingBox(Rectangle rectangle) {
        g();
        float f = 0.0f;
        Drawable i = i();
        if (i != null) {
            f = i.getLeftWidth();
        }
        if (this.J && this.r) {
            rectangle.x = f + this.Y + this.T + this.X;
            rectangle.y = (a(this.t.font, i) - this.H) - this.t.font.getDescent();
            rectangle.width = this.U;
            rectangle.height = this.H;
            return;
        }
        rectangle.x = (((f + this.Y) + this.s.get(this.p)) - this.s.get(this.Z)) + this.X + this.t.font.getData().cursorX;
        rectangle.y = (a(this.t.font, i) - this.H) - this.t.font.getDescent();
        rectangle.width = 0.0f;
        rectangle.height = this.H;
    }

    protected void a(Batch batch, BitmapFont bitmapFont, float f, float f2) {
        bitmapFont.draw(batch, this.u, f + this.Y, f2, this.Z, this.aa, 0.0f, 8, false);
    }

    private void a(Batch batch, BitmapFont bitmapFont, float f, float f2, float f3) {
        bitmapFont.draw(batch, this.R, f, f2, 0, this.R.length(), f3, this.S, false, "...");
    }

    public float getCursorX() {
        return ((this.Y + this.s.get(this.p)) - this.s.get(this.Z)) + this.X + this.t.font.getData().cursorX;
    }

    public float getCursorY() {
        return this.H - this.t.font.getDescent();
    }

    protected void b(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        drawable.draw(batch, (((f + this.Y) + this.s.get(this.p)) - this.s.get(this.Z)) + this.X + bitmapFont.getData().cursorX, (f2 - this.H) - bitmapFont.getDescent(), drawable.getMinWidth(), this.H);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void j() {
        BitmapFont bitmapFont = this.t.font;
        BitmapFont.BitmapFontData data = bitmapFont.getData();
        String str = this.o;
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            sb.append(data.hasGlyph(charAt) ? charAt : ' ');
        }
        String sb2 = sb.toString();
        if (this.G && data.hasGlyph(this.W)) {
            if (this.V == null) {
                this.V = new StringBuilder(sb2.length());
            }
            if (this.V.length() > length) {
                this.V.setLength(length);
            } else {
                for (int length2 = this.V.length(); length2 < length; length2++) {
                    this.V.append(this.W);
                }
            }
            this.u = this.V;
        } else {
            this.u = sb2;
        }
        boolean z = data.markupEnabled;
        data.markupEnabled = false;
        this.Q.setText(bitmapFont, this.u.toString().replace('\r', ' ').replace('\n', ' '));
        data.markupEnabled = z;
        this.s.clear();
        float f = 0.0f;
        if (this.Q.runs.size > 0) {
            FloatArray floatArray = this.Q.runs.first().xAdvances;
            this.X = floatArray.first();
            int i2 = floatArray.size;
            for (int i3 = 1; i3 < i2; i3++) {
                this.s.add(f);
                f += floatArray.get(i3);
            }
        } else {
            this.X = 0.0f;
        }
        this.s.add(f);
        this.Z = Math.min(this.Z, this.s.size - 1);
        this.aa = MathUtils.clamp(this.aa, this.Z, this.s.size - 1);
        if (this.q > sb2.length()) {
            this.q = length;
        }
    }

    public void copy() {
        if (this.r && !this.G) {
            this.v.setContents(this.o.substring(Math.min(this.p, this.q), Math.max(this.p, this.q)));
        }
    }

    public void cut() {
        a(this.ad);
    }

    final void a(boolean z) {
        if (this.r && !this.G) {
            copy();
            this.p = b(z);
            j();
            updateContextMenu();
        }
    }

    final void a(@Null String str, boolean z) {
        if (str == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int length = this.o.length();
        if (this.r) {
            length -= Math.abs(this.p - this.q);
        }
        BitmapFont.BitmapFontData data = this.t.font.getData();
        int length2 = str.length();
        for (int i = 0; i < length2 && a(length + sb.length()); i++) {
            char charAt = str.charAt(i);
            if ((this.writeEnters && (charAt == '\n' || charAt == '\r')) || (charAt != '\r' && charAt != '\n' && ((!this.B || data.hasGlyph(charAt)) && (this.y == null || this.y.acceptChar(this, charAt))))) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        if (this.r) {
            this.p = b(z);
        }
        if (z) {
            a(this.o, a(this.p, sb2, this.o));
        } else {
            this.o = a(this.p, sb2, this.o);
        }
        j();
        this.p += sb2.length();
        updateContextMenu();
    }

    static String a(int i, CharSequence charSequence, String str) {
        return str.length() == 0 ? charSequence.toString() : str.substring(0, i) + ((Object) charSequence) + str.substring(i, str.length());
    }

    final int b(boolean z) {
        String str;
        int i = this.q;
        int i2 = this.p;
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        StringBuilder append = new StringBuilder().append(min > 0 ? this.o.substring(0, min) : "");
        if (max < this.o.length()) {
            String str2 = this.o;
            str = str2.substring(max, str2.length());
        } else {
            str = "";
        }
        String sb = append.append(str).toString();
        if (z) {
            a(this.o, sb);
        } else {
            this.o = sb;
        }
        clearSelection();
        return min;
    }

    public void next(boolean z) {
        Stage stage = getStage();
        if (stage == null) {
            return;
        }
        TextField textField = this;
        Vector2 localToStageCoordinates = getParent().localToStageCoordinates(m.set(textField.getX(), textField.getY()));
        Vector2 vector2 = l;
        while (true) {
            TextField a2 = textField.a(stage.getActors(), (TextField) null, vector2, localToStageCoordinates, z);
            TextField textField2 = a2;
            if (a2 == null) {
                if (z) {
                    localToStageCoordinates.set(-3.4028235E38f, -3.4028235E38f);
                } else {
                    localToStageCoordinates.set(Float.MAX_VALUE, Float.MAX_VALUE);
                }
                textField2 = textField.a(stage.getActors(), (TextField) null, vector2, localToStageCoordinates, z);
            }
            if (textField2 == null) {
                Gdx.input.setOnscreenKeyboardVisible(false);
                return;
            } else if (stage.setKeyboardFocus(textField2)) {
                textField2.selectAll();
                return;
            } else {
                textField = textField2;
                localToStageCoordinates.set(vector2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00eb, code lost:            if (((r0.y > r10.y) ^ r12) != false) goto L49;     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0155 A[SYNTHETIC] */
    @com.badlogic.gdx.utils.Null
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.prineside.tdi2.scene2d.ui.TextField a(com.badlogic.gdx.utils.Array<com.prineside.tdi2.scene2d.Actor> r8, @com.badlogic.gdx.utils.Null com.prineside.tdi2.scene2d.ui.TextField r9, com.badlogic.gdx.math.Vector2 r10, com.badlogic.gdx.math.Vector2 r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.scene2d.ui.TextField.a(com.badlogic.gdx.utils.Array, com.prineside.tdi2.scene2d.ui.TextField, com.badlogic.gdx.math.Vector2, com.badlogic.gdx.math.Vector2, boolean):com.prineside.tdi2.scene2d.ui.TextField");
    }

    public InputListener getDefaultInputListener() {
        return this.w;
    }

    public void setTextFieldListener(@Null TextFieldListener textFieldListener) {
        this.x = textFieldListener;
    }

    public void setTextFieldFilter(@Null TextFieldFilter textFieldFilter) {
        this.y = textFieldFilter;
    }

    @Null
    public TextFieldFilter getTextFieldFilter() {
        return this.y;
    }

    public void setFocusTraversal(boolean z) {
        this.A = z;
    }

    public boolean getFocusTraversal() {
        return this.A;
    }

    @Null
    public String getMessageText() {
        return this.R;
    }

    public void setMessageText(@Null String str) {
        this.R = str;
    }

    public void appendText(@Null String str) {
        if (str == null) {
            str = "";
        }
        clearSelection();
        this.p = this.o.length();
        a(str, this.ad);
        updateContextMenu();
    }

    public void setText(@Null String str) {
        if (str == null) {
            str = "";
        }
        if (str.equals(this.o)) {
            return;
        }
        clearSelection();
        String str2 = this.o;
        this.o = "";
        a(str, false);
        if (this.ad) {
            a(str2, this.o);
        }
        this.p = 0;
        updateContextMenu();
    }

    public String getText() {
        return this.o;
    }

    final boolean a(String str, String str2) {
        if (str2.equals(str)) {
            return false;
        }
        this.o = str2;
        ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
        boolean fire = fire(changeEvent);
        if (fire) {
            this.o = str;
        }
        Pools.free(changeEvent);
        return !fire;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.ad = z;
    }

    public boolean getProgrammaticChangeEvents() {
        return this.ad;
    }

    public int getSelectionStart() {
        return this.q;
    }

    public String getSelection() {
        if (this.r) {
            int min = Math.min(this.q, this.p);
            int max = Math.max(this.q, this.p);
            int clamp = MathUtils.clamp(min, 0, this.o.length());
            int clamp2 = MathUtils.clamp(max, 0, this.o.length());
            if (clamp == -1 || clamp2 == -1) {
                return "";
            }
            return this.o.substring(clamp, clamp2);
        }
        return "";
    }

    public void setSelection(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("selectionStart must be >= 0");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("selectionEnd must be >= 0");
        }
        int min = Math.min(this.o.length(), i);
        int min2 = Math.min(this.o.length(), i2);
        int i3 = min2;
        if (min2 == min) {
            clearSelection();
            return;
        }
        if (i3 < min) {
            i3 = min;
            min = i3;
        }
        this.r = true;
        this.q = min;
        this.p = i3;
        updateContextMenu();
    }

    public void selectAll() {
        setSelection(0, this.o.length());
    }

    public void clearSelection() {
        this.r = false;
        updateContextMenu();
    }

    private void e() {
        this.O = -1;
        updateContextMenu();
    }

    public void updateContextMenu() {
        if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS) {
            boolean z = false;
            if (this.J) {
                boolean z2 = getSelection().length() != 0 || this.P;
                z = z2;
                if (z2) {
                    if (this.ac == null) {
                        this.ac = new ContextMenu();
                        addActor(this.ac);
                    }
                    this.ac.update();
                }
            }
            if (!z && this.ac != null) {
                this.ac.remove();
                this.ac = null;
            }
        }
    }

    public void setCursorPosition(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("cursorPosition must be >= 0");
        }
        clearSelection();
        this.p = Math.min(i, this.o.length());
        updateContextMenu();
    }

    public int getCursorPosition() {
        return this.p;
    }

    public OnscreenKeyboard getOnscreenKeyboard() {
        return this.z;
    }

    public void setOnscreenKeyboard(OnscreenKeyboard onscreenKeyboard) {
        this.z = onscreenKeyboard;
    }

    public void setClipboard(Clipboard clipboard) {
        this.v = clipboard;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        return 150.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        float f = 0.0f;
        float f2 = 0.0f;
        if (this.t.background != null) {
            f = Math.max(0.0f, this.t.background.getBottomHeight() + this.t.background.getTopHeight());
            f2 = Math.max(0.0f, this.t.background.getMinHeight());
        }
        if (this.t.focusedBackground != null) {
            f = Math.max(f, this.t.focusedBackground.getBottomHeight() + this.t.focusedBackground.getTopHeight());
            f2 = Math.max(f2, this.t.focusedBackground.getMinHeight());
        }
        if (this.t.disabledBackground != null) {
            f = Math.max(f, this.t.disabledBackground.getBottomHeight() + this.t.disabledBackground.getTopHeight());
            f2 = Math.max(f2, this.t.disabledBackground.getMinHeight());
        }
        return Math.max(f + this.H, f2);
    }

    public void setAlignment(int i) {
        this.S = i;
    }

    public int getAlignment() {
        return this.S;
    }

    public void setPasswordMode(boolean z) {
        this.G = z;
        j();
    }

    public boolean isPasswordMode() {
        return this.G;
    }

    public void setPasswordCharacter(char c) {
        this.W = c;
        if (this.G) {
            j();
        }
    }

    public void setBlinkTime(float f) {
        this.L = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.C = z;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.C;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0051 A[EDGE_INSN: B:21:0x0051->B:17:0x0051 BREAK  A[LOOP:0: B:8:0x001b->B:20:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r5, boolean r6) {
        /*
            r4 = this;
            r0 = r5
            if (r0 == 0) goto Le
            r0 = r4
            java.lang.String r0 = r0.o
            int r0 = r0.length()
            goto Lf
        Le:
            r0 = 0
        Lf:
            r7 = r0
            r0 = r5
            if (r0 == 0) goto L18
            r0 = 0
            goto L19
        L18:
            r0 = -1
        L19:
            r8 = r0
        L1b:
            r0 = r5
            if (r0 == 0) goto L31
            r0 = r4
            r1 = r0
            int r1 = r1.p
            r2 = 1
            int r1 = r1 + r2
            r2 = r1; r1 = r0; r0 = r2; 
            r1.p = r2
            r1 = r7
            if (r0 >= r1) goto L51
            goto L40
        L31:
            r0 = r4
            r1 = r0
            int r1 = r1.p
            r2 = 1
            int r1 = r1 - r2
            r2 = r1; r1 = r0; r0 = r2; 
            r1.p = r2
            r1 = r7
            if (r0 <= r1) goto L51
        L40:
            r0 = r6
            if (r0 == 0) goto L51
            r0 = r4
            r1 = r0
            int r1 = r1.p
            r2 = r8
            boolean r0 = r0.a(r1, r2)
            if (r0 != 0) goto L1b
        L51:
            r0 = r4
            r0.updateContextMenu()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.scene2d.ui.TextField.a(boolean, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(int i, int i2) {
        return a(this.o.charAt(i + i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$KeyRepeatTask.class */
    public class KeyRepeatTask extends Timer.Task {

        /* renamed from: a, reason: collision with root package name */
        int f2680a;

        KeyRepeatTask() {
        }

        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
        public void run() {
            if (TextField.this.getStage() == null) {
                cancel();
            } else {
                TextField.this.w.keyDown(null, this.f2680a);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$TextFieldFilter.class */
    public interface TextFieldFilter {
        boolean acceptChar(TextField textField, char c);

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$TextFieldFilter$DigitsOnlyFilter.class */
        public static class DigitsOnlyFilter implements TextFieldFilter {
            @Override // com.prineside.tdi2.scene2d.ui.TextField.TextFieldFilter
            public boolean acceptChar(TextField textField, char c) {
                return Character.isDigit(c);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$DefaultOnscreenKeyboard.class */
    public static class DefaultOnscreenKeyboard implements OnscreenKeyboard {
        @Override // com.prineside.tdi2.scene2d.ui.TextField.OnscreenKeyboard
        public void show(boolean z) {
            Gdx.input.setOnscreenKeyboardVisible(z);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$TextFieldClickListener.class */
    public class TextFieldClickListener extends ClickListener {
        public TextFieldClickListener() {
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            int tapCount = getTapCount() % 4;
            if (tapCount == 0) {
                TextField.this.clearSelection();
            }
            if (tapCount == 2) {
                int[] a2 = TextField.this.a(f);
                TextField.this.setSelection(a2[0], a2[1]);
            }
            if (tapCount == 3) {
                TextField.this.selectAll();
            }
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
        public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
            if (!super.touchDown(inputEvent, f, f2, i, i2)) {
                return false;
            }
            if (i == 0 && i2 != 0) {
                return false;
            }
            if (TextField.this.C) {
                return true;
            }
            a(f, f2);
            if (TextField.this.O == TextField.this.p) {
                TextField.this.P = true;
            } else {
                TextField.this.P = false;
                TextField.this.O = TextField.this.p;
            }
            TextField.this.q = TextField.this.p;
            Stage stage = TextField.this.getStage();
            if (stage != null) {
                stage.setKeyboardFocus(TextField.this);
            }
            TextField.this.z.show(true);
            TextField.this.r = true;
            TextField.this.updateContextMenu();
            return true;
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
        public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
            super.touchDragged(inputEvent, f, f2, i);
            a(f, f2);
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
        public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
            if (TextField.this.q == TextField.this.p) {
                TextField.this.r = false;
            }
            TextField.this.updateContextMenu();
            super.touchUp(inputEvent, f, f2, i, i2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void a(float f, float f2) {
            TextField.this.p = TextField.this.letterUnderCursor(f);
            TextField.this.K = TextField.this.J;
            TextField.this.M.cancel();
            if (TextField.this.J) {
                Timer.schedule(TextField.this.M, TextField.this.L, TextField.this.L);
            }
            TextField.this.updateContextMenu();
        }

        protected void a(boolean z) {
            TextField.this.p = 0;
            TextField.this.updateContextMenu();
        }

        protected void b(boolean z) {
            TextField.this.p = TextField.this.o.length();
            TextField.this.updateContextMenu();
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public boolean keyDown(InputEvent inputEvent, int i) {
            if (TextField.this.C) {
                return false;
            }
            TextField.this.P = false;
            TextField.this.K = TextField.this.J;
            TextField.this.M.cancel();
            if (TextField.this.J) {
                Timer.schedule(TextField.this.M, TextField.this.L, TextField.this.L);
            }
            if (!TextField.this.hasKeyboardFocus()) {
                return false;
            }
            boolean z = false;
            boolean ctrl = UIUtils.ctrl();
            boolean z2 = ctrl && !TextField.this.G;
            boolean z3 = true;
            if (ctrl) {
                switch (i) {
                    case 29:
                        TextField.this.selectAll();
                        return true;
                    case 31:
                    case 124:
                        TextField.this.copy();
                        return true;
                    case 50:
                        TextField.this.a(TextField.this.v.getContents(), true);
                        z = true;
                        break;
                    case 52:
                        TextField.this.a(true);
                        return true;
                    case 54:
                        String str = TextField.this.o;
                        int i2 = TextField.this.p;
                        TextField.this.setText(TextField.this.D);
                        TextField.this.setCursorPosition(TextField.this.E);
                        TextField.this.D = str;
                        TextField.this.E = i2;
                        TextField.this.j();
                        return true;
                    default:
                        z3 = false;
                        break;
                }
            }
            if (UIUtils.shift()) {
                switch (i) {
                    case 112:
                        TextField.this.a(true);
                        break;
                    case 124:
                        TextField.this.a(TextField.this.v.getContents(), true);
                        break;
                }
                int i3 = TextField.this.p;
                switch (i) {
                    case 3:
                        a(z2);
                        z3 = true;
                        break;
                    case 21:
                        TextField.this.a(false, z2);
                        z = true;
                        z3 = true;
                        break;
                    case 22:
                        TextField.this.a(true, z2);
                        z = true;
                        z3 = true;
                        break;
                    case 123:
                        b(z2);
                        z3 = true;
                        break;
                }
                if (!TextField.this.r) {
                    TextField.this.q = i3;
                    TextField.this.r = true;
                    TextField.this.updateContextMenu();
                }
            } else {
                switch (i) {
                    case 3:
                        a(z2);
                        TextField.this.clearSelection();
                        z3 = true;
                        break;
                    case 21:
                        if (TextField.this.r) {
                            TextField.this.setCursorPosition(Math.min(TextField.this.p, TextField.this.q));
                        } else {
                            TextField.this.a(false, z2);
                        }
                        TextField.this.clearSelection();
                        z = true;
                        z3 = true;
                        break;
                    case 22:
                        if (TextField.this.r) {
                            TextField.this.setCursorPosition(Math.max(TextField.this.p, TextField.this.q));
                        } else {
                            TextField.this.a(true, z2);
                        }
                        TextField.this.clearSelection();
                        z = true;
                        z3 = true;
                        break;
                    case 123:
                        b(z2);
                        TextField.this.clearSelection();
                        z3 = true;
                        break;
                }
            }
            TextField.this.p = MathUtils.clamp(TextField.this.p, 0, TextField.this.o.length());
            TextField.this.updateContextMenu();
            if (z) {
                a(i);
            }
            return z3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void a(int i) {
            if (!TextField.this.N.isScheduled() || TextField.this.N.f2680a != i) {
                TextField.this.N.f2680a = i;
                TextField.this.N.cancel();
                Timer.schedule(TextField.this.N, TextField.keyRepeatInitialTime, TextField.keyRepeatTime);
            }
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public boolean keyUp(InputEvent inputEvent, int i) {
            if (TextField.this.C) {
                return false;
            }
            TextField.this.N.cancel();
            return true;
        }

        protected boolean a(char c) {
            if (!TextField.this.A) {
                return false;
            }
            if (c == '\t') {
                return true;
            }
            if (c == '\r' || c == '\n') {
                return UIUtils.isAndroid || UIUtils.isIos;
            }
            return false;
        }

        private int b(int i) {
            for (int min = Math.min(i, TextField.this.o.length()) - 1; min >= 0; min--) {
                if (TextField.this.o.charAt(min) == '\n') {
                    return min;
                }
            }
            return -1;
        }

        private IntPair c(int i) {
            int b2 = b(i);
            int i2 = 0;
            for (int i3 = b2 + 1; i3 < TextField.this.o.length() && (TextField.this.o.charAt(i3) == ' ' || TextField.this.o.charAt(i3) == '\t'); i3++) {
                i2++;
            }
            if (i2 > 0) {
                int i4 = i2 % 4;
                int i5 = i4;
                if (i4 == 0) {
                    i5 = 4;
                }
                int i6 = b2 + 1;
                int i7 = b2 + 1 + i5;
                TextField.this.o = TextField.this.o.substring(0, i6) + TextField.this.o.substring(i7);
                return new IntPair(i6, i7);
            }
            return new IntPair(0, 0);
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public boolean keyTyped(InputEvent inputEvent, char c) {
            if (TextField.this.C) {
                return false;
            }
            switch (c) {
                case '\b':
                case '\t':
                case '\n':
                case '\r':
                    break;
                case 11:
                case '\f':
                default:
                    if (c < ' ') {
                        return false;
                    }
                    break;
            }
            if (!TextField.this.hasKeyboardFocus()) {
                return false;
            }
            if (UIUtils.isMac && Gdx.input.isKeyPressed(63)) {
                return true;
            }
            String str = TextField.this.o;
            int i = TextField.this.p;
            if (a(c)) {
                TextField.this.next(UIUtils.shift());
            } else if (TextField.this.replaceTabsWithSpaces && c == '\t') {
                if (UIUtils.shift()) {
                    if (TextField.this.r && TextField.this.q != TextField.this.p) {
                        boolean z = TextField.this.p > TextField.this.q;
                        int min = Math.min(TextField.this.q, TextField.this.p);
                        int max = Math.max(TextField.this.q, TextField.this.p);
                        int i2 = max;
                        while (true) {
                            int i3 = max;
                            if (i3 >= 0) {
                                int b2 = b(i3);
                                IntPair c2 = c(i3);
                                for (int i4 = c2.f3850a; i4 < c2.f3851b; i4++) {
                                    if (i4 < min) {
                                        min--;
                                        i2--;
                                    } else if (i4 < i2) {
                                        i2--;
                                    }
                                }
                                if (b2 >= Math.min(TextField.this.q, TextField.this.p)) {
                                    max = b2 - 1;
                                }
                            }
                        }
                        if (z) {
                            TextField.this.q = min;
                            TextField.this.p = i2;
                        } else {
                            TextField.this.q = i2;
                            TextField.this.p = min;
                        }
                    } else {
                        int b3 = b(TextField.this.p);
                        IntPair c3 = c(TextField.this.p);
                        TextField.this.p = Math.max(TextField.this.p - (c3.f3851b - c3.f3850a), b3 + 1);
                    }
                } else if (TextField.this.r && TextField.this.q != TextField.this.p) {
                    boolean z2 = TextField.this.p > TextField.this.q;
                    int min2 = Math.min(TextField.this.q, TextField.this.p);
                    int max2 = Math.max(TextField.this.q, TextField.this.p);
                    int i5 = max2;
                    while (true) {
                        int i6 = max2;
                        if (i6 >= 0) {
                            int b4 = b(i6);
                            int i7 = b4 + 1;
                            int i8 = 0;
                            for (int i9 = b4 + 1; i9 < TextField.this.o.length() && (TextField.this.o.charAt(i9) == ' ' || TextField.this.o.charAt(i9) == '\t'); i9++) {
                                i8++;
                            }
                            int i10 = 4 - (i8 % 4);
                            StringBuilder sb = new StringBuilder();
                            for (int i11 = 0; i11 < i10; i11++) {
                                sb.append(' ');
                            }
                            TextField.this.o = TextField.a(i7, sb, TextField.this.o);
                            for (int i12 = i7; i12 < i7 + i10; i12++) {
                                if (i12 < min2) {
                                    min2++;
                                    i5++;
                                } else if (i12 < i5) {
                                    i5++;
                                }
                            }
                            if (b4 >= Math.min(TextField.this.q, TextField.this.p)) {
                                max2 = b4 - 1;
                            }
                        }
                    }
                    if (z2) {
                        TextField.this.q = min2;
                        TextField.this.p = i5;
                    } else {
                        TextField.this.q = i5;
                        TextField.this.p = min2;
                    }
                } else {
                    int b5 = 4 - ((TextField.this.p - (b(TextField.this.p) + 1)) % 4);
                    StringBuilder sb2 = new StringBuilder();
                    for (int i13 = 0; i13 < b5; i13++) {
                        sb2.append(' ');
                    }
                    TextField.this.o = TextField.a(TextField.this.p, sb2, TextField.this.o);
                    TextField.this.p += b5;
                }
            } else {
                boolean z3 = c == '\r' || c == '\n';
                boolean z4 = c == 127;
                boolean z5 = c == '\b';
                boolean z6 = z3 ? TextField.this.writeEnters : !TextField.this.B || TextField.this.t.font.getData().hasGlyph(c);
                boolean z7 = z5 || z4;
                if (z6 || z7) {
                    if (z7) {
                        if (TextField.this.r) {
                            TextField.this.p = TextField.this.b(false);
                        } else {
                            if (z5 && TextField.this.p > 0) {
                                TextField textField = TextField.this;
                                StringBuilder append = new StringBuilder().append(TextField.this.o.substring(0, TextField.this.p - 1));
                                String str2 = TextField.this.o;
                                TextField textField2 = TextField.this;
                                int i14 = textField2.p;
                                textField2.p = i14 - 1;
                                textField.o = append.append(str2.substring(i14)).toString();
                                TextField.this.I = 0.0f;
                            }
                            if (z4 && TextField.this.p < TextField.this.o.length()) {
                                TextField.this.o = TextField.this.o.substring(0, TextField.this.p) + TextField.this.o.substring(TextField.this.p + 1);
                            }
                        }
                    }
                    if (z6 && !z7) {
                        if (!z3 && TextField.this.y != null && !TextField.this.y.acceptChar(TextField.this, c)) {
                            return true;
                        }
                        if (!TextField.this.a(TextField.this.o.length() - (TextField.this.r ? Math.abs(TextField.this.p - TextField.this.q) : 0))) {
                            return true;
                        }
                        if (TextField.this.r) {
                            TextField.this.p = TextField.this.b(false);
                        }
                        String valueOf = z3 ? SequenceUtils.EOL : String.valueOf(c);
                        TextField textField3 = TextField.this;
                        TextField textField4 = TextField.this;
                        int i15 = textField4.p;
                        textField4.p = i15 + 1;
                        textField3.o = TextField.a(i15, valueOf, TextField.this.o);
                    }
                }
            }
            if (TextField.this.a(str, TextField.this.o)) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - 500 > TextField.this.F) {
                    TextField.this.D = str;
                    TextField.this.E = i;
                }
                TextField.this.F = currentTimeMillis;
                TextField.this.j();
            } else if (!TextField.this.o.equals(str)) {
                TextField.this.p = i;
            }
            TextField.this.updateContextMenu();
            if (TextField.this.x != null) {
                TextField.this.x.keyTyped(TextField.this, c);
                return true;
            }
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$TextFieldStyle.class */
    public static class TextFieldStyle {
        public BitmapFont font;
        public Color fontColor;

        @Null
        public Color focusedFontColor;

        @Null
        public Color disabledFontColor;

        @Null
        public Drawable background;

        @Null
        public Drawable focusedBackground;

        @Null
        public Drawable disabledBackground;

        @Null
        public Drawable cursor;

        @Null
        public Drawable selection;

        @Null
        public BitmapFont messageFont;

        @Null
        public Color messageFontColor;

        public TextFieldStyle() {
        }

        public TextFieldStyle(BitmapFont bitmapFont, Color color, @Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3) {
            this.font = bitmapFont;
            this.fontColor = color;
            this.cursor = drawable;
            this.selection = drawable2;
            this.background = drawable3;
        }

        public TextFieldStyle(TextFieldStyle textFieldStyle) {
            this.font = textFieldStyle.font;
            if (textFieldStyle.fontColor != null) {
                this.fontColor = new Color(textFieldStyle.fontColor);
            }
            if (textFieldStyle.focusedFontColor != null) {
                this.focusedFontColor = new Color(textFieldStyle.focusedFontColor);
            }
            if (textFieldStyle.disabledFontColor != null) {
                this.disabledFontColor = new Color(textFieldStyle.disabledFontColor);
            }
            this.background = textFieldStyle.background;
            this.focusedBackground = textFieldStyle.focusedBackground;
            this.disabledBackground = textFieldStyle.disabledBackground;
            this.cursor = textFieldStyle.cursor;
            this.selection = textFieldStyle.selection;
            this.messageFont = textFieldStyle.messageFont;
            if (textFieldStyle.messageFontColor != null) {
                this.messageFontColor = new Color(textFieldStyle.messageFontColor);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextField$ContextMenu.class */
    public class ContextMenu extends Table {
        private final Rectangle k = new Rectangle();
        private Image l;
        private TableButton n;
        private TableButton o;
        private TableButton p;

        public ContextMenu() {
            background(Game.i.assetManager.getQuad("ui.textField.contextMenu.bg"));
            this.l = new Image(Game.i.assetManager.getQuad("ui.textField.contextMenu.bgArrow"));
            addActor(this.l);
            this.l.setTouchable(Touchable.disabled);
            setTouchable(Touchable.enabled);
            this.n = new TableButton(TextField.this::copy);
            this.n.add((TableButton) new Label(Game.i.localeManager.i18n.get("text_field_context_copy"), Game.i.assetManager.getLabelStyle(21))).pad(10.0f).padLeft(20.0f).padRight(20.0f);
            this.n.setContentColors(Color.WHITE, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500, MaterialColor.BLUE_GREY.P500);
            this.o = new TableButton(() -> {
                TextField.this.a(true);
            });
            this.o.add((TableButton) new Label(Game.i.localeManager.i18n.get("text_field_context_cut"), Game.i.assetManager.getLabelStyle(21))).pad(10.0f).padLeft(20.0f).padRight(20.0f);
            this.o.setContentColors(Color.WHITE, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500, MaterialColor.BLUE_GREY.P500);
            this.p = new TableButton(() -> {
                TextField.this.a(Gdx.app.getClipboard().getContents(), true);
            });
            this.p.add((TableButton) new Label(Game.i.localeManager.i18n.get("text_field_context_paste"), Game.i.assetManager.getLabelStyle(21))).pad(10.0f).padLeft(20.0f).padRight(20.0f);
            this.p.setContentColors(Color.WHITE, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500, MaterialColor.BLUE_GREY.P500);
        }

        public void update() {
            try {
                if (TextField.this.getSelection().length() != 0 || TextField.this.P) {
                    clear();
                    if (TextField.this.getSelection().length() != 0) {
                        add((ContextMenu) this.n);
                        add((ContextMenu) new Image(Game.i.assetManager.getQuad("ui.textField.contextMenu.delimiter"))).width(2.0f).fillY();
                        add((ContextMenu) this.o);
                        add((ContextMenu) new Image(Game.i.assetManager.getQuad("ui.textField.contextMenu.delimiter"))).width(2.0f).fillY();
                    }
                    add((ContextMenu) this.p);
                    addActor(this.l);
                    layout();
                    pack();
                    TextField.this.getSelectionBoundingBox(this.k);
                    float width = ((this.k.width * 0.5f) - (getWidth() * 0.5f)) + this.k.x;
                    float f = this.k.y + this.k.height + 20.0f;
                    setPosition(width, f);
                    this.l.setX((getWidth() * 0.5f) - (this.l.getWidth() * 0.5f));
                    this.l.setY(-this.l.getHeight());
                    float f2 = localToStageCoordinates(new Vector2()).x;
                    float f3 = f2;
                    float width2 = f2 + getWidth();
                    float height = (this.f2638a.getHeight() - localToStageCoordinates(new Vector2()).y) + getHeight();
                    Group group = TextField.this.f2639b;
                    while (true) {
                        if (group == null) {
                            break;
                        }
                        if (group instanceof ScrollPane) {
                            float f4 = localToActorCoordinates(group, new Vector2()).x;
                            f3 = f4;
                            width2 = f4 + getWidth();
                            height = (group.getHeight() - localToActorCoordinates(group, new Vector2()).y) + getHeight();
                            break;
                        }
                        group = group.getParent();
                    }
                    float f5 = 0.0f;
                    if (f3 < 0.0f) {
                        f5 = f3;
                    } else if (width2 > this.f2638a.getWidth()) {
                        f5 = width2 - this.f2638a.getWidth();
                    }
                    if (height < 100.0f) {
                        setPosition(width - f5, (this.k.y - getHeight()) - 20.0f);
                        this.l.setY(getHeight());
                        this.l.setDrawable(Game.i.assetManager.getQuad("ui.textField.contextMenu.bgArrowTop"));
                    } else {
                        setPosition(width - f5, f);
                        this.l.setDrawable(Game.i.assetManager.getQuad("ui.textField.contextMenu.bgArrow"));
                    }
                    this.l.setX(this.l.getX() + f5);
                    setVisible(true);
                    return;
                }
                setVisible(false);
            } catch (Exception e) {
                TextField.k.w("failed to update text field context menu", e);
            }
        }
    }
}
