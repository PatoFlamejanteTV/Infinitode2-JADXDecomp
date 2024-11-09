package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextTooltip.class */
public class TextTooltip extends Tooltip<Label> {
    public TextTooltip(@Null String str, TextTooltipStyle textTooltipStyle) {
        this(str, TooltipManager.getInstance(), textTooltipStyle);
    }

    public TextTooltip(@Null String str, TooltipManager tooltipManager, TextTooltipStyle textTooltipStyle) {
        super(null, tooltipManager);
        this.f2683a.setActor(a(str, textTooltipStyle.label));
        setStyle(textTooltipStyle);
    }

    private static Label a(String str, Label.LabelStyle labelStyle) {
        return new Label(str, labelStyle);
    }

    public void setStyle(TextTooltipStyle textTooltipStyle) {
        if (textTooltipStyle == null) {
            throw new NullPointerException("style cannot be null");
        }
        this.f2683a.setBackground(textTooltipStyle.background);
        this.f2683a.maxWidth(textTooltipStyle.wrapWidth);
        boolean z = textTooltipStyle.wrapWidth != 0.0f;
        this.f2683a.fill(z);
        Label label = (Label) this.f2683a.getActor();
        label.setStyle(textTooltipStyle.label);
        label.setWrap(z);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextTooltip$TextTooltipStyle.class */
    public static class TextTooltipStyle {
        public Label.LabelStyle label;

        @Null
        public Drawable background;
        public float wrapWidth;

        public TextTooltipStyle() {
        }

        public TextTooltipStyle(Label.LabelStyle labelStyle, @Null Drawable drawable) {
            this.label = labelStyle;
            this.background = drawable;
        }

        public TextTooltipStyle(TextTooltipStyle textTooltipStyle) {
            this.label = new Label.LabelStyle(textTooltipStyle.label);
            this.background = textTooltipStyle.background;
            this.wrapWidth = textTooltipStyle.wrapWidth;
        }
    }
}
