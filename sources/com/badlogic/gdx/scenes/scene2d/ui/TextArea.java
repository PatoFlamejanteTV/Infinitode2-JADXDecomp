package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextArea.class */
public class TextArea extends TextField {
    IntArray linesBreak;
    private String lastText;
    int cursorLine;
    int firstLineShowing;
    private int linesShowing;
    float moveOffset;
    private float prefRows;

    public TextArea(String str, Skin skin) {
        super(str, skin);
    }

    public TextArea(String str, Skin skin, String str2) {
        super(str, skin, str2);
    }

    public TextArea(String str, TextField.TextFieldStyle textFieldStyle) {
        super(str, textFieldStyle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void initialize() {
        super.initialize();
        this.writeEnters = true;
        this.linesBreak = new IntArray();
        this.cursorLine = 0;
        this.firstLineShowing = 0;
        this.moveOffset = -1.0f;
        this.linesShowing = 0;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected int letterUnderCursor(float f) {
        if (this.linesBreak.size > 0) {
            if ((this.cursorLine << 1) >= this.linesBreak.size) {
                return this.text.length();
            }
            float[] fArr = this.glyphPositions.items;
            int i = this.linesBreak.items[this.cursorLine << 1];
            float f2 = f + fArr[i];
            int i2 = this.linesBreak.items[(this.cursorLine << 1) + 1];
            int i3 = i;
            while (i3 < i2 && fArr[i3] <= f2) {
                i3++;
            }
            return (i3 <= 0 || fArr[i3] - f2 > f2 - fArr[i3 - 1]) ? Math.max(0, i3 - 1) : i3;
        }
        return 0;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void setStyle(TextField.TextFieldStyle textFieldStyle) {
        if (textFieldStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = textFieldStyle;
        this.textHeight = textFieldStyle.font.getCapHeight() - textFieldStyle.font.getDescent();
        if (this.text != null) {
            updateDisplayText();
        }
        invalidateHierarchy();
    }

    public void setPrefRows(float f) {
        this.prefRows = f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.prefRows <= 0.0f) {
            return super.getPrefHeight();
        }
        float ceil = (float) Math.ceil(this.style.font.getLineHeight() * this.prefRows);
        if (this.style.background != null) {
            ceil = Math.max(ceil + this.style.background.getBottomHeight() + this.style.background.getTopHeight(), this.style.background.getMinHeight());
        }
        return ceil;
    }

    public int getLines() {
        return (this.linesBreak.size / 2) + (newLineAtEnd() ? 1 : 0);
    }

    public boolean newLineAtEnd() {
        if (this.text.length() != 0) {
            return this.text.charAt(this.text.length() - 1) == '\n' || this.text.charAt(this.text.length() - 1) == '\r';
        }
        return false;
    }

    public void moveCursorLine(int i) {
        if (i < 0) {
            this.cursorLine = 0;
            this.cursor = 0;
            this.moveOffset = -1.0f;
            return;
        }
        if (i >= getLines()) {
            int lines = getLines() - 1;
            this.cursor = this.text.length();
            if (i > getLines() || lines == this.cursorLine) {
                this.moveOffset = -1.0f;
            }
            this.cursorLine = lines;
            return;
        }
        if (i != this.cursorLine) {
            if (this.moveOffset < 0.0f) {
                this.moveOffset = this.linesBreak.size <= (this.cursorLine << 1) ? 0.0f : this.glyphPositions.get(this.cursor) - this.glyphPositions.get(this.linesBreak.get(this.cursorLine << 1));
            }
            this.cursorLine = i;
            TextArea textArea = this;
            int length = (textArea.cursorLine << 1) >= this.linesBreak.size ? this.text.length() : this.linesBreak.get(this.cursorLine << 1);
            while (true) {
                textArea.cursor = length;
                if (this.cursor >= this.text.length() || this.cursor > this.linesBreak.get((this.cursorLine << 1) + 1) - 1 || this.glyphPositions.get(this.cursor) - this.glyphPositions.get(this.linesBreak.get(this.cursorLine << 1)) >= this.moveOffset) {
                    break;
                }
                textArea = this;
                length = textArea.cursor + 1;
            }
            showCursor();
        }
    }

    void updateCurrentLine() {
        int calculateCurrentLineIndex = calculateCurrentLineIndex(this.cursor);
        int i = calculateCurrentLineIndex / 2;
        if ((calculateCurrentLineIndex % 2 == 0 || calculateCurrentLineIndex + 1 >= this.linesBreak.size || this.cursor != this.linesBreak.items[calculateCurrentLineIndex] || this.linesBreak.items[calculateCurrentLineIndex + 1] != this.linesBreak.items[calculateCurrentLineIndex]) && (i < this.linesBreak.size / 2 || this.text.length() == 0 || this.text.charAt(this.text.length() - 1) == '\n' || this.text.charAt(this.text.length() - 1) == '\r')) {
            this.cursorLine = i;
        }
        updateFirstLineShowing();
    }

    void showCursor() {
        updateCurrentLine();
        updateFirstLineShowing();
    }

    void updateFirstLineShowing() {
        if (this.cursorLine != this.firstLineShowing) {
            int i = this.cursorLine >= this.firstLineShowing ? 1 : -1;
            while (true) {
                if (this.firstLineShowing > this.cursorLine || (this.firstLineShowing + this.linesShowing) - 1 < this.cursorLine) {
                    this.firstLineShowing += i;
                } else {
                    return;
                }
            }
        }
    }

    private int calculateCurrentLineIndex(int i) {
        int i2 = 0;
        while (i2 < this.linesBreak.size && i > this.linesBreak.items[i2]) {
            i2++;
        }
        return i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    protected void sizeChanged() {
        this.lastText = null;
        BitmapFont bitmapFont = this.style.font;
        this.linesShowing = (int) Math.floor((getHeight() - (this.style.background == null ? 0.0f : r0.getBottomHeight() + r0.getTopHeight())) / bitmapFont.getLineHeight());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected float getTextY(BitmapFont bitmapFont, @Null Drawable drawable) {
        float height = getHeight();
        if (drawable != null) {
            height -= drawable.getTopHeight();
        }
        if (bitmapFont.usesIntegerPositions()) {
            height = (int) height;
        }
        return height;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawSelection(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        float f3 = 0.0f;
        int min = Math.min(this.cursor, this.selectionStart);
        int max = Math.max(this.cursor, this.selectionStart);
        BitmapFont.BitmapFontData data = bitmapFont.getData();
        float lineHeight = this.style.font.getLineHeight();
        for (int i = this.firstLineShowing << 1; i + 1 < this.linesBreak.size && i < ((this.firstLineShowing + this.linesShowing) << 1); i += 2) {
            int i2 = this.linesBreak.get(i);
            int i3 = this.linesBreak.get(i + 1);
            if ((min >= i2 || min >= i3 || max >= i2 || max >= i3) && (min <= i2 || min <= i3 || max <= i2 || max <= i3)) {
                int max2 = Math.max(i2, min);
                int min2 = Math.min(i3, max);
                float f4 = 0.0f;
                float f5 = 0.0f;
                BitmapFont.Glyph glyph = data.getGlyph(this.displayText.charAt(i2));
                if (glyph != null) {
                    if (max2 == i2) {
                        f5 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                    } else {
                        f4 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                    }
                }
                drawable.draw(batch, f + (this.glyphPositions.get(max2) - this.glyphPositions.get(i2)) + f4, (f2 - lineHeight) - f3, (this.glyphPositions.get(min2) - this.glyphPositions.get(max2)) + f5, bitmapFont.getLineHeight());
            }
            f3 += bitmapFont.getLineHeight();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawText(Batch batch, BitmapFont bitmapFont, float f, float f2) {
        float f3 = (-(this.style.font.getLineHeight() - this.textHeight)) / 2.0f;
        for (int i = this.firstLineShowing << 1; i < ((this.firstLineShowing + this.linesShowing) << 1) && i < this.linesBreak.size; i += 2) {
            bitmapFont.draw(batch, this.displayText, f, f2 + f3, this.linesBreak.items[i], this.linesBreak.items[i + 1], 0.0f, 8, false);
            f3 -= bitmapFont.getLineHeight();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawCursor(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        drawable.draw(batch, f + getCursorX(), f2 + getCursorY(), drawable.getMinWidth(), bitmapFont.getLineHeight());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void calculateOffsets() {
        super.calculateOffsets();
        if (!this.text.equals(this.lastText)) {
            this.lastText = this.text;
            BitmapFont bitmapFont = this.style.font;
            float width = getWidth() - (this.style.background != null ? this.style.background.getLeftWidth() + this.style.background.getRightWidth() : 0.0f);
            this.linesBreak.clear();
            int i = 0;
            int i2 = 0;
            Pool pool = Pools.get(GlyphLayout.class);
            GlyphLayout glyphLayout = (GlyphLayout) pool.obtain();
            for (int i3 = 0; i3 < this.text.length(); i3++) {
                char charAt = this.text.charAt(i3);
                if (charAt == '\r' || charAt == '\n') {
                    this.linesBreak.add(i);
                    this.linesBreak.add(i3);
                    i = i3 + 1;
                } else {
                    i2 = continueCursor(i3, 0) ? i2 : i3;
                    glyphLayout.setText(bitmapFont, this.text.subSequence(i, i3 + 1));
                    if (glyphLayout.width > width) {
                        if (i >= i2) {
                            i2 = i3 - 1;
                        }
                        this.linesBreak.add(i);
                        this.linesBreak.add(i2 + 1);
                        int i4 = i2 + 1;
                        i = i4;
                        i2 = i4;
                    }
                }
            }
            pool.free(glyphLayout);
            if (i < this.text.length()) {
                this.linesBreak.add(i);
                this.linesBreak.add(this.text.length());
            }
            showCursor();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected InputListener createInputListener() {
        return new TextAreaListener();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void setSelection(int i, int i2) {
        super.setSelection(i, i2);
        updateCurrentLine();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void moveCursor(boolean z, boolean z2) {
        int i = z ? 1 : -1;
        int i2 = (this.cursorLine << 1) + i;
        if (i2 >= 0 && i2 + 1 < this.linesBreak.size && this.linesBreak.items[i2] == this.cursor && this.linesBreak.items[i2 + 1] == this.cursor) {
            this.cursorLine += i;
            if (z2) {
                super.moveCursor(z, z2);
            }
            showCursor();
        } else {
            super.moveCursor(z, z2);
        }
        updateCurrentLine();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public boolean continueCursor(int i, int i2) {
        int calculateCurrentLineIndex = calculateCurrentLineIndex(i + i2);
        if (super.continueCursor(i, i2)) {
            return calculateCurrentLineIndex < 0 || calculateCurrentLineIndex >= this.linesBreak.size - 2 || this.linesBreak.items[calculateCurrentLineIndex + 1] != i || this.linesBreak.items[calculateCurrentLineIndex + 1] == this.linesBreak.items[calculateCurrentLineIndex + 2];
        }
        return false;
    }

    public int getCursorLine() {
        return this.cursorLine;
    }

    public int getFirstLineShowing() {
        return this.firstLineShowing;
    }

    public int getLinesShowing() {
        return this.linesShowing;
    }

    public float getCursorX() {
        float f = 0.0f;
        BitmapFont.BitmapFontData data = this.style.font.getData();
        if (this.cursor < this.glyphPositions.size && (this.cursorLine << 1) < this.linesBreak.size) {
            int i = this.linesBreak.items[this.cursorLine << 1];
            float f2 = 0.0f;
            BitmapFont.Glyph glyph = data.getGlyph(this.displayText.charAt(i));
            if (glyph != null) {
                f2 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
            }
            f = (this.glyphPositions.get(this.cursor) - this.glyphPositions.get(i)) + f2;
        }
        return f + data.cursorX;
    }

    public float getCursorY() {
        return (-((this.cursorLine - this.firstLineShowing) + 1)) * this.style.font.getLineHeight();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextArea$TextAreaListener.class */
    public class TextAreaListener extends TextField.TextFieldClickListener {
        public TextAreaListener() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        public void setCursorPosition(float f, float f2) {
            TextArea.this.moveOffset = -1.0f;
            Drawable drawable = TextArea.this.style.background;
            BitmapFont bitmapFont = TextArea.this.style.font;
            float height = TextArea.this.getHeight();
            if (drawable != null) {
                height -= drawable.getTopHeight();
                f -= drawable.getLeftWidth();
            }
            float max = Math.max(0.0f, f);
            if (drawable != null) {
                f2 -= drawable.getTopHeight();
            }
            TextArea.this.cursorLine = ((int) Math.floor((height - f2) / bitmapFont.getLineHeight())) + TextArea.this.firstLineShowing;
            TextArea.this.cursorLine = Math.max(0, Math.min(TextArea.this.cursorLine, TextArea.this.getLines() - 1));
            super.setCursorPosition(max, f2);
            TextArea.this.updateCurrentLine();
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyDown(InputEvent inputEvent, int i) {
            boolean keyDown = super.keyDown(inputEvent, i);
            if (TextArea.this.hasKeyboardFocus()) {
                boolean z = false;
                boolean z2 = Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60);
                if (i == 20) {
                    if (z2) {
                        if (!TextArea.this.hasSelection) {
                            TextArea.this.selectionStart = TextArea.this.cursor;
                            TextArea.this.hasSelection = true;
                        }
                    } else {
                        TextArea.this.clearSelection();
                    }
                    TextArea.this.moveCursorLine(TextArea.this.cursorLine + 1);
                    z = true;
                } else if (i == 19) {
                    if (z2) {
                        if (!TextArea.this.hasSelection) {
                            TextArea.this.selectionStart = TextArea.this.cursor;
                            TextArea.this.hasSelection = true;
                        }
                    } else {
                        TextArea.this.clearSelection();
                    }
                    TextArea.this.moveCursorLine(TextArea.this.cursorLine - 1);
                    z = true;
                } else {
                    TextArea.this.moveOffset = -1.0f;
                }
                if (z) {
                    scheduleKeyRepeatTask(i);
                }
                TextArea.this.showCursor();
                return true;
            }
            return keyDown;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        protected boolean checkFocusTraversal(char c) {
            return TextArea.this.focusTraversal && c == '\t';
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyTyped(InputEvent inputEvent, char c) {
            boolean keyTyped = super.keyTyped(inputEvent, c);
            TextArea.this.showCursor();
            return keyTyped;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        protected void goHome(boolean z) {
            if (z) {
                TextArea.this.cursor = 0;
            } else if ((TextArea.this.cursorLine << 1) < TextArea.this.linesBreak.size) {
                TextArea.this.cursor = TextArea.this.linesBreak.get(TextArea.this.cursorLine << 1);
            }
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        protected void goEnd(boolean z) {
            if (z || TextArea.this.cursorLine >= TextArea.this.getLines()) {
                TextArea.this.cursor = TextArea.this.text.length();
            } else if ((TextArea.this.cursorLine << 1) + 1 < TextArea.this.linesBreak.size) {
                TextArea.this.cursor = TextArea.this.linesBreak.get((TextArea.this.cursorLine << 1) + 1);
            }
        }
    }
}
