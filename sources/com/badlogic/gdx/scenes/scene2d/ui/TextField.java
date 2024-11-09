package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Timer;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField.class */
public class TextField extends Widget implements Disableable {
    protected static final char BACKSPACE = '\b';
    protected static final char CARRIAGE_RETURN = '\r';
    protected static final char NEWLINE = '\n';
    protected static final char TAB = '\t';
    protected static final char DELETE = 127;
    protected static final char BULLET = 149;
    private static final Vector2 tmp1 = new Vector2();
    private static final Vector2 tmp2 = new Vector2();
    private static final Vector2 tmp3 = new Vector2();
    public static float keyRepeatInitialTime = 0.4f;
    public static float keyRepeatTime = 0.1f;
    protected String text;
    protected int cursor;
    protected int selectionStart;
    protected boolean hasSelection;
    protected boolean writeEnters;
    protected final GlyphLayout layout;
    protected final FloatArray glyphPositions;
    TextFieldStyle style;
    private String messageText;
    protected CharSequence displayText;
    Clipboard clipboard;
    InputListener inputListener;

    @Null
    TextFieldListener listener;

    @Null
    TextFieldFilter filter;
    OnscreenKeyboard keyboard;
    boolean focusTraversal;
    boolean onlyFontChars;
    boolean disabled;
    private int textHAlign;
    private float selectionX;
    private float selectionWidth;
    String undoText;
    long lastChangeTime;
    boolean passwordMode;
    private StringBuilder passwordBuffer;
    private char passwordCharacter;
    protected float fontOffset;
    protected float textHeight;
    protected float textOffset;
    float renderOffset;
    protected int visibleTextStart;
    protected int visibleTextEnd;
    private int maxLength;
    boolean focused;
    boolean cursorOn;
    float blinkTime;
    final Timer.Task blinkTask;
    final KeyRepeatTask keyRepeatTask;
    boolean programmaticChangeEvents;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$OnscreenKeyboard.class */
    public interface OnscreenKeyboard {
        void show(boolean z);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$TextFieldListener.class */
    public interface TextFieldListener {
        void keyTyped(TextField textField, char c);
    }

    public TextField(@Null String str, Skin skin) {
        this(str, (TextFieldStyle) skin.get(TextFieldStyle.class));
    }

    public TextField(@Null String str, Skin skin, String str2) {
        this(str, (TextFieldStyle) skin.get(str2, TextFieldStyle.class));
    }

    public TextField(@Null String str, TextFieldStyle textFieldStyle) {
        this.layout = new GlyphLayout();
        this.glyphPositions = new FloatArray();
        this.keyboard = new DefaultOnscreenKeyboard();
        this.focusTraversal = true;
        this.onlyFontChars = true;
        this.textHAlign = 8;
        this.undoText = "";
        this.passwordCharacter = (char) 149;
        this.blinkTime = 0.32f;
        this.blinkTask = new Timer.Task() { // from class: com.badlogic.gdx.scenes.scene2d.ui.TextField.1
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                if (TextField.this.getStage() == null) {
                    cancel();
                    return;
                }
                TextField.this.cursorOn = !TextField.this.cursorOn;
                Gdx.graphics.requestRendering();
            }
        };
        this.keyRepeatTask = new KeyRepeatTask();
        setStyle(textFieldStyle);
        this.clipboard = Gdx.app.getClipboard();
        initialize();
        setText(str);
        setSize(getPrefWidth(), getPrefHeight());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialize() {
        InputListener createInputListener = createInputListener();
        this.inputListener = createInputListener;
        addListener(createInputListener);
    }

    protected InputListener createInputListener() {
        return new TextFieldClickListener();
    }

    protected int letterUnderCursor(float f) {
        float f2 = f - (((this.textOffset + this.fontOffset) - this.style.font.getData().cursorX) - this.glyphPositions.get(this.visibleTextStart));
        if (getBackgroundDrawable() != null) {
            f2 -= this.style.background.getLeftWidth();
        }
        int i = this.glyphPositions.size;
        float[] fArr = this.glyphPositions.items;
        for (int i2 = 1; i2 < i; i2++) {
            if (fArr[i2] > f2) {
                return fArr[i2] - f2 <= f2 - fArr[i2 - 1] ? i2 : i2 - 1;
            }
        }
        return i - 1;
    }

    protected boolean isWordCharacter(char c) {
        return Character.isLetterOrDigit(c);
    }

    protected int[] wordUnderCursor(int i) {
        String str = this.text;
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
                if (isWordCharacter(str.charAt(i3))) {
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
                if (isWordCharacter(str.charAt(i4))) {
                    i4--;
                } else {
                    i2 = i4 + 1;
                    break;
                }
            }
        }
        return new int[]{i2, length};
    }

    int[] wordUnderCursor(float f) {
        return wordUnderCursor(letterUnderCursor(f));
    }

    boolean withinMaxLength(int i) {
        return this.maxLength <= 0 || i < this.maxLength;
    }

    public void setMaxLength(int i) {
        this.maxLength = i;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public void setOnlyFontChars(boolean z) {
        this.onlyFontChars = z;
    }

    public void setStyle(TextFieldStyle textFieldStyle) {
        if (textFieldStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = textFieldStyle;
        this.textHeight = textFieldStyle.font.getCapHeight() - (textFieldStyle.font.getDescent() * 2.0f);
        if (this.text != null) {
            updateDisplayText();
        }
        invalidateHierarchy();
    }

    public TextFieldStyle getStyle() {
        return this.style;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void calculateOffsets() {
        float width = getWidth();
        Drawable backgroundDrawable = getBackgroundDrawable();
        if (backgroundDrawable != null) {
            width -= backgroundDrawable.getLeftWidth() + backgroundDrawable.getRightWidth();
        }
        int i = this.glyphPositions.size;
        float[] fArr = this.glyphPositions.items;
        this.cursor = MathUtils.clamp(this.cursor, 0, i - 1);
        float f = fArr[Math.max(0, this.cursor - 1)] + this.renderOffset;
        if (f <= 0.0f) {
            this.renderOffset -= f;
        } else {
            float f2 = fArr[Math.min(i - 1, this.cursor + 1)] - width;
            if ((-this.renderOffset) < f2) {
                this.renderOffset = -f2;
            }
        }
        float f3 = 0.0f;
        float f4 = fArr[i - 1];
        for (int i2 = i - 2; i2 >= 0; i2--) {
            float f5 = fArr[i2];
            if (f4 - f5 > width) {
                break;
            }
            f3 = f5;
        }
        if ((-this.renderOffset) > f3) {
            this.renderOffset = -f3;
        }
        this.visibleTextStart = 0;
        float f6 = 0.0f;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                break;
            }
            if (fArr[i3] < (-this.renderOffset)) {
                i3++;
            } else {
                this.visibleTextStart = i3;
                f6 = fArr[i3];
                break;
            }
        }
        int i4 = this.visibleTextStart + 1;
        float f7 = width - this.renderOffset;
        int min = Math.min(this.displayText.length(), i);
        while (i4 <= min && fArr[i4] <= f7) {
            i4++;
        }
        this.visibleTextEnd = Math.max(0, i4 - 1);
        if ((this.textHAlign & 8) == 0) {
            this.textOffset = ((width - fArr[this.visibleTextEnd]) - this.fontOffset) + f6;
            if ((this.textHAlign & 1) != 0) {
                this.textOffset = Math.round(this.textOffset * 0.5f);
            }
        } else {
            this.textOffset = f6 + this.renderOffset;
        }
        if (this.hasSelection) {
            int min2 = Math.min(this.cursor, this.selectionStart);
            int max = Math.max(this.cursor, this.selectionStart);
            float max2 = Math.max(fArr[min2] - fArr[this.visibleTextStart], -this.textOffset);
            float min3 = Math.min(fArr[max] - fArr[this.visibleTextStart], width - this.textOffset);
            this.selectionX = max2;
            this.selectionWidth = (min3 - max2) - this.style.font.getData().cursorX;
        }
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        return (!this.disabled || this.style.disabledBackground == null) ? (this.style.focusedBackground == null || !hasKeyboardFocus()) ? this.style.background : this.style.focusedBackground : this.style.disabledBackground;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        Color color;
        boolean hasKeyboardFocus = hasKeyboardFocus();
        if (hasKeyboardFocus != this.focused || (hasKeyboardFocus && !this.blinkTask.isScheduled())) {
            this.focused = hasKeyboardFocus;
            this.blinkTask.cancel();
            this.cursorOn = hasKeyboardFocus;
            if (hasKeyboardFocus) {
                Timer.schedule(this.blinkTask, this.blinkTime, this.blinkTime);
            } else {
                this.keyRepeatTask.cancel();
            }
        } else if (!hasKeyboardFocus) {
            this.cursorOn = false;
        }
        BitmapFont bitmapFont = this.style.font;
        if (!this.disabled || this.style.disabledFontColor == null) {
            color = (!hasKeyboardFocus || this.style.focusedFontColor == null) ? this.style.fontColor : this.style.focusedFontColor;
        } else {
            color = this.style.disabledFontColor;
        }
        Color color2 = color;
        Drawable drawable = this.style.selection;
        Drawable drawable2 = this.style.cursor;
        Drawable backgroundDrawable = getBackgroundDrawable();
        Color color3 = getColor();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        batch.setColor(color3.r, color3.g, color3.f888b, color3.f889a * f);
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (backgroundDrawable != null) {
            backgroundDrawable.draw(batch, x, y, width, height);
            f2 = backgroundDrawable.getLeftWidth();
            f3 = backgroundDrawable.getRightWidth();
        }
        float textY = getTextY(bitmapFont, backgroundDrawable);
        calculateOffsets();
        if (hasKeyboardFocus && this.hasSelection && drawable != null) {
            drawSelection(drawable, batch, bitmapFont, x + f2, y + textY);
        }
        float f4 = bitmapFont.isFlipped() ? -this.textHeight : 0.0f;
        if (this.displayText.length() == 0) {
            if ((!hasKeyboardFocus || this.disabled) && this.messageText != null) {
                BitmapFont bitmapFont2 = this.style.messageFont != null ? this.style.messageFont : bitmapFont;
                if (this.style.messageFontColor != null) {
                    bitmapFont2.setColor(this.style.messageFontColor.r, this.style.messageFontColor.g, this.style.messageFontColor.f888b, this.style.messageFontColor.f889a * color3.f889a * f);
                } else {
                    bitmapFont2.setColor(0.7f, 0.7f, 0.7f, color3.f889a * f);
                }
                drawMessageText(batch, bitmapFont2, x + f2, y + textY + f4, (width - f2) - f3);
            }
        } else {
            BitmapFont.BitmapFontData data = bitmapFont.getData();
            boolean z = data.markupEnabled;
            data.markupEnabled = false;
            bitmapFont.setColor(color2.r, color2.g, color2.f888b, color2.f889a * color3.f889a * f);
            drawText(batch, bitmapFont, x + f2, y + textY + f4);
            data.markupEnabled = z;
        }
        if (!this.disabled && this.cursorOn && drawable2 != null) {
            drawCursor(drawable2, batch, bitmapFont, x + f2, y + textY);
        }
    }

    protected float getTextY(BitmapFont bitmapFont, @Null Drawable drawable) {
        float f;
        float height = getHeight();
        float descent = (this.textHeight / 2.0f) + bitmapFont.getDescent();
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

    protected void drawSelection(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        drawable.draw(batch, f + this.textOffset + this.selectionX + this.fontOffset, (f2 - this.textHeight) - bitmapFont.getDescent(), this.selectionWidth, this.textHeight);
    }

    protected void drawText(Batch batch, BitmapFont bitmapFont, float f, float f2) {
        bitmapFont.draw(batch, this.displayText, f + this.textOffset, f2, this.visibleTextStart, this.visibleTextEnd, 0.0f, 8, false);
    }

    protected void drawMessageText(Batch batch, BitmapFont bitmapFont, float f, float f2, float f3) {
        bitmapFont.draw(batch, this.messageText, f, f2, 0, this.messageText.length(), f3, this.textHAlign, false, "...");
    }

    protected void drawCursor(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        drawable.draw(batch, (((f + this.textOffset) + this.glyphPositions.get(this.cursor)) - this.glyphPositions.get(this.visibleTextStart)) + this.fontOffset + bitmapFont.getData().cursorX, (f2 - this.textHeight) - bitmapFont.getDescent(), drawable.getMinWidth(), this.textHeight);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateDisplayText() {
        BitmapFont bitmapFont = this.style.font;
        BitmapFont.BitmapFontData data = bitmapFont.getData();
        String str = this.text;
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            sb.append(data.hasGlyph(charAt) ? charAt : ' ');
        }
        String sb2 = sb.toString();
        if (this.passwordMode && data.hasGlyph(this.passwordCharacter)) {
            if (this.passwordBuffer == null) {
                this.passwordBuffer = new StringBuilder(sb2.length());
            }
            if (this.passwordBuffer.length() > length) {
                this.passwordBuffer.setLength(length);
            } else {
                for (int length2 = this.passwordBuffer.length(); length2 < length; length2++) {
                    this.passwordBuffer.append(this.passwordCharacter);
                }
            }
            this.displayText = this.passwordBuffer;
        } else {
            this.displayText = sb2;
        }
        boolean z = data.markupEnabled;
        data.markupEnabled = false;
        this.layout.setText(bitmapFont, this.displayText.toString().replace('\r', ' ').replace('\n', ' '));
        data.markupEnabled = z;
        this.glyphPositions.clear();
        float f = 0.0f;
        if (this.layout.runs.size > 0) {
            FloatArray floatArray = this.layout.runs.first().xAdvances;
            this.fontOffset = floatArray.first();
            int i2 = floatArray.size;
            for (int i3 = 1; i3 < i2; i3++) {
                this.glyphPositions.add(f);
                f += floatArray.get(i3);
            }
        } else {
            this.fontOffset = 0.0f;
        }
        this.glyphPositions.add(f);
        this.visibleTextStart = Math.min(this.visibleTextStart, this.glyphPositions.size - 1);
        this.visibleTextEnd = MathUtils.clamp(this.visibleTextEnd, this.visibleTextStart, this.glyphPositions.size - 1);
        if (this.selectionStart > sb2.length()) {
            this.selectionStart = length;
        }
    }

    public void copy() {
        if (this.hasSelection && !this.passwordMode) {
            this.clipboard.setContents(this.text.substring(Math.min(this.cursor, this.selectionStart), Math.max(this.cursor, this.selectionStart)));
        }
    }

    public void cut() {
        cut(this.programmaticChangeEvents);
    }

    void cut(boolean z) {
        if (this.hasSelection && !this.passwordMode) {
            copy();
            this.cursor = delete(z);
            updateDisplayText();
        }
    }

    void paste(@Null String str, boolean z) {
        if (str == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int length = this.text.length();
        if (this.hasSelection) {
            length -= Math.abs(this.cursor - this.selectionStart);
        }
        BitmapFont.BitmapFontData data = this.style.font.getData();
        int length2 = str.length();
        for (int i = 0; i < length2 && withinMaxLength(length + sb.length()); i++) {
            char charAt = str.charAt(i);
            if ((this.writeEnters && (charAt == '\n' || charAt == '\r')) || (charAt != '\r' && charAt != '\n' && ((!this.onlyFontChars || data.hasGlyph(charAt)) && (this.filter == null || this.filter.acceptChar(this, charAt))))) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        if (this.hasSelection) {
            this.cursor = delete(z);
        }
        if (z) {
            changeText(this.text, insert(this.cursor, sb2, this.text));
        } else {
            this.text = insert(this.cursor, sb2, this.text);
        }
        updateDisplayText();
        this.cursor += sb2.length();
    }

    String insert(int i, CharSequence charSequence, String str) {
        return str.length() == 0 ? charSequence.toString() : str.substring(0, i) + ((Object) charSequence) + str.substring(i, str.length());
    }

    int delete(boolean z) {
        String str;
        int i = this.selectionStart;
        int i2 = this.cursor;
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        StringBuilder append = new StringBuilder().append(min > 0 ? this.text.substring(0, min) : "");
        if (max < this.text.length()) {
            String str2 = this.text;
            str = str2.substring(max, str2.length());
        } else {
            str = "";
        }
        String sb = append.append(str).toString();
        if (z) {
            changeText(this.text, sb);
        } else {
            this.text = sb;
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
        Vector2 localToStageCoordinates = getParent().localToStageCoordinates(tmp2.set(textField.getX(), textField.getY()));
        Vector2 vector2 = tmp1;
        while (true) {
            TextField findNextTextField = textField.findNextTextField(stage.getActors(), null, vector2, localToStageCoordinates, z);
            TextField textField2 = findNextTextField;
            if (findNextTextField == null) {
                if (z) {
                    localToStageCoordinates.set(-3.4028235E38f, -3.4028235E38f);
                } else {
                    localToStageCoordinates.set(Float.MAX_VALUE, Float.MAX_VALUE);
                }
                textField2 = textField.findNextTextField(stage.getActors(), null, vector2, localToStageCoordinates, z);
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
    private com.badlogic.gdx.scenes.scene2d.ui.TextField findNextTextField(com.badlogic.gdx.utils.Array<com.badlogic.gdx.scenes.scene2d.Actor> r8, @com.badlogic.gdx.utils.Null com.badlogic.gdx.scenes.scene2d.ui.TextField r9, com.badlogic.gdx.math.Vector2 r10, com.badlogic.gdx.math.Vector2 r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.scenes.scene2d.ui.TextField.findNextTextField(com.badlogic.gdx.utils.Array, com.badlogic.gdx.scenes.scene2d.ui.TextField, com.badlogic.gdx.math.Vector2, com.badlogic.gdx.math.Vector2, boolean):com.badlogic.gdx.scenes.scene2d.ui.TextField");
    }

    public InputListener getDefaultInputListener() {
        return this.inputListener;
    }

    public void setTextFieldListener(@Null TextFieldListener textFieldListener) {
        this.listener = textFieldListener;
    }

    public void setTextFieldFilter(@Null TextFieldFilter textFieldFilter) {
        this.filter = textFieldFilter;
    }

    @Null
    public TextFieldFilter getTextFieldFilter() {
        return this.filter;
    }

    public void setFocusTraversal(boolean z) {
        this.focusTraversal = z;
    }

    public boolean getFocusTraversal() {
        return this.focusTraversal;
    }

    @Null
    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(@Null String str) {
        this.messageText = str;
    }

    public void appendText(@Null String str) {
        if (str == null) {
            str = "";
        }
        clearSelection();
        this.cursor = this.text.length();
        paste(str, this.programmaticChangeEvents);
    }

    public void setText(@Null String str) {
        if (str == null) {
            str = "";
        }
        if (str.equals(this.text)) {
            return;
        }
        clearSelection();
        String str2 = this.text;
        this.text = "";
        paste(str, false);
        if (this.programmaticChangeEvents) {
            changeText(str2, this.text);
        }
        this.cursor = 0;
    }

    public String getText() {
        return this.text;
    }

    boolean changeText(String str, String str2) {
        if (str2.equals(str)) {
            return false;
        }
        this.text = str2;
        ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
        boolean fire = fire(changeEvent);
        if (fire) {
            this.text = str;
        }
        Pools.free(changeEvent);
        return !fire;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
    }

    public boolean getProgrammaticChangeEvents() {
        return this.programmaticChangeEvents;
    }

    public int getSelectionStart() {
        return this.selectionStart;
    }

    public String getSelection() {
        return this.hasSelection ? this.text.substring(Math.min(this.selectionStart, this.cursor), Math.max(this.selectionStart, this.cursor)) : "";
    }

    public void setSelection(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("selectionStart must be >= 0");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("selectionEnd must be >= 0");
        }
        int min = Math.min(this.text.length(), i);
        int min2 = Math.min(this.text.length(), i2);
        int i3 = min2;
        if (min2 == min) {
            clearSelection();
            return;
        }
        if (i3 < min) {
            i3 = min;
            min = i3;
        }
        this.hasSelection = true;
        this.selectionStart = min;
        this.cursor = i3;
    }

    public void selectAll() {
        setSelection(0, this.text.length());
    }

    public void clearSelection() {
        this.hasSelection = false;
    }

    public void setCursorPosition(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("cursorPosition must be >= 0");
        }
        clearSelection();
        this.cursor = Math.min(i, this.text.length());
    }

    public int getCursorPosition() {
        return this.cursor;
    }

    public OnscreenKeyboard getOnscreenKeyboard() {
        return this.keyboard;
    }

    public void setOnscreenKeyboard(OnscreenKeyboard onscreenKeyboard) {
        this.keyboard = onscreenKeyboard;
    }

    public void setClipboard(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        return 150.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        float f = 0.0f;
        float f2 = 0.0f;
        if (this.style.background != null) {
            f = Math.max(0.0f, this.style.background.getBottomHeight() + this.style.background.getTopHeight());
            f2 = Math.max(0.0f, this.style.background.getMinHeight());
        }
        if (this.style.focusedBackground != null) {
            f = Math.max(f, this.style.focusedBackground.getBottomHeight() + this.style.focusedBackground.getTopHeight());
            f2 = Math.max(f2, this.style.focusedBackground.getMinHeight());
        }
        if (this.style.disabledBackground != null) {
            f = Math.max(f, this.style.disabledBackground.getBottomHeight() + this.style.disabledBackground.getTopHeight());
            f2 = Math.max(f2, this.style.disabledBackground.getMinHeight());
        }
        return Math.max(f + this.textHeight, f2);
    }

    public void setAlignment(int i) {
        this.textHAlign = i;
    }

    public int getAlignment() {
        return this.textHAlign;
    }

    public void setPasswordMode(boolean z) {
        this.passwordMode = z;
        updateDisplayText();
    }

    public boolean isPasswordMode() {
        return this.passwordMode;
    }

    public void setPasswordCharacter(char c) {
        this.passwordCharacter = c;
        if (this.passwordMode) {
            updateDisplayText();
        }
    }

    public void setBlinkTime(float f) {
        this.blinkTime = f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.disabled = z;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void moveCursor(boolean z, boolean z2) {
        int length = z ? this.text.length() : 0;
        int i = z ? 0 : -1;
        do {
            if (z) {
                int i2 = this.cursor + 1;
                this.cursor = i2;
                if (i2 >= length) {
                    return;
                }
            } else {
                int i3 = this.cursor - 1;
                this.cursor = i3;
                if (i3 <= length) {
                    return;
                }
            }
            if (!z2) {
                return;
            }
        } while (continueCursor(this.cursor, i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean continueCursor(int i, int i2) {
        return isWordCharacter(this.text.charAt(i + i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$KeyRepeatTask.class */
    public class KeyRepeatTask extends Timer.Task {
        int keycode;

        KeyRepeatTask() {
        }

        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
        public void run() {
            if (TextField.this.getStage() == null) {
                cancel();
            } else {
                TextField.this.inputListener.keyDown(null, this.keycode);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$TextFieldFilter.class */
    public interface TextFieldFilter {
        boolean acceptChar(TextField textField, char c);

        /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$TextFieldFilter$DigitsOnlyFilter.class */
        public static class DigitsOnlyFilter implements TextFieldFilter {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter
            public boolean acceptChar(TextField textField, char c) {
                return Character.isDigit(c);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$DefaultOnscreenKeyboard.class */
    public static class DefaultOnscreenKeyboard implements OnscreenKeyboard {
        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.OnscreenKeyboard
        public void show(boolean z) {
            Gdx.input.setOnscreenKeyboardVisible(z);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$TextFieldClickListener.class */
    public class TextFieldClickListener extends ClickListener {
        public TextFieldClickListener() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            int tapCount = getTapCount() % 4;
            if (tapCount == 0) {
                TextField.this.clearSelection();
            }
            if (tapCount == 2) {
                int[] wordUnderCursor = TextField.this.wordUnderCursor(f);
                TextField.this.setSelection(wordUnderCursor[0], wordUnderCursor[1]);
            }
            if (tapCount == 3) {
                TextField.this.selectAll();
            }
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
            if (!super.touchDown(inputEvent, f, f2, i, i2)) {
                return false;
            }
            if (i == 0 && i2 != 0) {
                return false;
            }
            if (TextField.this.disabled) {
                return true;
            }
            setCursorPosition(f, f2);
            TextField.this.selectionStart = TextField.this.cursor;
            Stage stage = TextField.this.getStage();
            if (stage != null) {
                stage.setKeyboardFocus(TextField.this);
            }
            TextField.this.keyboard.show(true);
            TextField.this.hasSelection = true;
            return true;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
            super.touchDragged(inputEvent, f, f2, i);
            setCursorPosition(f, f2);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
            if (TextField.this.selectionStart == TextField.this.cursor) {
                TextField.this.hasSelection = false;
            }
            super.touchUp(inputEvent, f, f2, i, i2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setCursorPosition(float f, float f2) {
            TextField.this.cursor = TextField.this.letterUnderCursor(f);
            TextField.this.cursorOn = TextField.this.focused;
            TextField.this.blinkTask.cancel();
            if (TextField.this.focused) {
                Timer.schedule(TextField.this.blinkTask, TextField.this.blinkTime, TextField.this.blinkTime);
            }
        }

        protected void goHome(boolean z) {
            TextField.this.cursor = 0;
        }

        protected void goEnd(boolean z) {
            TextField.this.cursor = TextField.this.text.length();
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyDown(InputEvent inputEvent, int i) {
            if (TextField.this.disabled) {
                return false;
            }
            TextField.this.cursorOn = TextField.this.focused;
            TextField.this.blinkTask.cancel();
            if (TextField.this.focused) {
                Timer.schedule(TextField.this.blinkTask, TextField.this.blinkTime, TextField.this.blinkTime);
            }
            if (!TextField.this.hasKeyboardFocus()) {
                return false;
            }
            boolean z = false;
            boolean ctrl = UIUtils.ctrl();
            boolean z2 = ctrl && !TextField.this.passwordMode;
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
                        TextField.this.paste(TextField.this.clipboard.getContents(), true);
                        z = true;
                        break;
                    case 52:
                        TextField.this.cut(true);
                        return true;
                    case 54:
                        String str = TextField.this.text;
                        TextField.this.setText(TextField.this.undoText);
                        TextField.this.undoText = str;
                        TextField.this.updateDisplayText();
                        return true;
                    default:
                        z3 = false;
                        break;
                }
            }
            if (UIUtils.shift()) {
                switch (i) {
                    case 112:
                        TextField.this.cut(true);
                        break;
                    case 124:
                        TextField.this.paste(TextField.this.clipboard.getContents(), true);
                        break;
                }
                int i2 = TextField.this.cursor;
                switch (i) {
                    case 3:
                        goHome(z2);
                        z3 = true;
                        break;
                    case 21:
                        TextField.this.moveCursor(false, z2);
                        z = true;
                        z3 = true;
                        break;
                    case 22:
                        TextField.this.moveCursor(true, z2);
                        z = true;
                        z3 = true;
                        break;
                    case 123:
                        goEnd(z2);
                        z3 = true;
                        break;
                }
                if (!TextField.this.hasSelection) {
                    TextField.this.selectionStart = i2;
                    TextField.this.hasSelection = true;
                }
            } else {
                switch (i) {
                    case 3:
                        goHome(z2);
                        TextField.this.clearSelection();
                        z3 = true;
                        break;
                    case 21:
                        TextField.this.moveCursor(false, z2);
                        TextField.this.clearSelection();
                        z = true;
                        z3 = true;
                        break;
                    case 22:
                        TextField.this.moveCursor(true, z2);
                        TextField.this.clearSelection();
                        z = true;
                        z3 = true;
                        break;
                    case 123:
                        goEnd(z2);
                        TextField.this.clearSelection();
                        z3 = true;
                        break;
                }
            }
            TextField.this.cursor = MathUtils.clamp(TextField.this.cursor, 0, TextField.this.text.length());
            if (z) {
                scheduleKeyRepeatTask(i);
            }
            return z3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void scheduleKeyRepeatTask(int i) {
            if (!TextField.this.keyRepeatTask.isScheduled() || TextField.this.keyRepeatTask.keycode != i) {
                TextField.this.keyRepeatTask.keycode = i;
                TextField.this.keyRepeatTask.cancel();
                Timer.schedule(TextField.this.keyRepeatTask, TextField.keyRepeatInitialTime, TextField.keyRepeatTime);
            }
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyUp(InputEvent inputEvent, int i) {
            if (TextField.this.disabled) {
                return false;
            }
            TextField.this.keyRepeatTask.cancel();
            return true;
        }

        protected boolean checkFocusTraversal(char c) {
            if (!TextField.this.focusTraversal) {
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

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v11 */
        /* JADX WARN: Type inference failed for: r4v19 */
        /* JADX WARN: Type inference failed for: r4v20 */
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyTyped(InputEvent inputEvent, char c) {
            ?? r4;
            TextField textField;
            if (TextField.this.disabled) {
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
            if (checkFocusTraversal(c)) {
                TextField.this.next(UIUtils.shift());
            } else {
                boolean z = c == '\r' || c == '\n';
                boolean z2 = c == 127;
                boolean z3 = c == '\b';
                boolean z4 = z ? TextField.this.writeEnters : !TextField.this.onlyFontChars || TextField.this.style.font.getData().hasGlyph(c);
                boolean z5 = z3 || z2;
                if (z4 || z5) {
                    String str = TextField.this.text;
                    int i = TextField.this.cursor;
                    if (z5) {
                        if (TextField.this.hasSelection) {
                            TextField.this.cursor = TextField.this.delete(false);
                        } else {
                            if (z3 && TextField.this.cursor > 0) {
                                TextField textField2 = TextField.this;
                                StringBuilder append = new StringBuilder().append(TextField.this.text.substring(0, TextField.this.cursor - 1));
                                String str2 = TextField.this.text;
                                TextField textField3 = TextField.this;
                                int i2 = textField3.cursor;
                                textField = textField3;
                                textField.cursor = i2 - 1;
                                textField2.text = append.append(str2.substring(i2)).toString();
                                TextField.this.renderOffset = 0.0f;
                            }
                            r4 = textField;
                            if (z2) {
                                r4 = textField;
                                if (TextField.this.cursor < TextField.this.text.length()) {
                                    r4 = 1;
                                    TextField.this.text = TextField.this.text.substring(0, TextField.this.cursor) + TextField.this.text.substring(TextField.this.cursor + 1);
                                }
                            }
                        }
                    }
                    String str3 = r4;
                    if (z4) {
                        str3 = r4;
                        if (!z5) {
                            if (!z && TextField.this.filter != null && !TextField.this.filter.acceptChar(TextField.this, c)) {
                                return true;
                            }
                            if (!TextField.this.withinMaxLength(TextField.this.text.length() - (TextField.this.hasSelection ? Math.abs(TextField.this.cursor - TextField.this.selectionStart) : 0))) {
                                return true;
                            }
                            if (TextField.this.hasSelection) {
                                TextField.this.cursor = TextField.this.delete(false);
                            }
                            String valueOf = z ? SequenceUtils.EOL : String.valueOf(c);
                            TextField textField4 = TextField.this;
                            TextField textField5 = TextField.this;
                            TextField textField6 = TextField.this;
                            int i3 = textField6.cursor;
                            textField6.cursor = i3 + 1;
                            String str4 = TextField.this.text;
                            textField4.text = textField5.insert(i3, valueOf, str4);
                            str3 = str4;
                        }
                    }
                    if (TextField.this.changeText(str, TextField.this.text)) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - 750 > TextField.this.lastChangeTime) {
                            TextField.this.undoText = str;
                        }
                        TextField.this.lastChangeTime = currentTimeMillis;
                        TextField.this.updateDisplayText();
                    } else if (!TextField.this.text.equals(str)) {
                        TextField.this.cursor = i;
                    }
                }
            }
            if (TextField.this.listener != null) {
                TextField.this.listener.keyTyped(TextField.this, c);
                return true;
            }
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextField$TextFieldStyle.class */
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
}
