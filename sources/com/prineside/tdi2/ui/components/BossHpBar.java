package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.GroupClipping;
import com.prineside.tdi2.ui.actors.LabelWithShadow;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/BossHpBar.class */
public final class BossHpBar extends Group {
    public static final float WIDTH = 304.0f;
    public static final float HEIGHT = 52.0f;
    public static final float MAIN_BAR_WIDTH = 300.0f;
    public Image backgroundImage;
    public LabelWithShadow nameLabel;
    public Image iconImage;
    public Image iconImageShadow;
    public Table effectIconsTable;
    public LabelWithShadow hpLabelCurrent;
    public LabelWithShadow hpLabelMax;
    public Group largeBarContainer;
    public Image barBackgroundLarge;
    public Image barLarge;
    public Group smallBarOneContainer;
    public Image barBackgroundSmallOne;
    public Image barSmallOne;
    public Group smallBarTwoContainer;
    public Image barBackgroundSmallTwo;
    public Image barSmallTwo;
    public Group marksGroup;
    private final Array<Drawable> k;
    private String l;

    public BossHpBar() {
        Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 106, "Tooltip");
        this.k = new Array<>(true, 1, Drawable.class);
        this.l = "Dummy";
        setTransform(false);
        setSize(304.0f, 52.0f);
        this.backgroundImage = new Image();
        this.backgroundImage.setSize(304.0f, 28.0f);
        addActor(this.backgroundImage);
        Table table = new Table();
        table.setPosition(26.0f, 30.0f);
        table.setSize(269.0f, 22.0f);
        addActor(table);
        this.nameLabel = new LabelWithShadow("Dummy", Game.i.assetManager.getLabelStyle(21)).setShadowColor(new Color(0.0f, 0.0f, 0.0f, 1.0f)).setShadowShift(0.0f, -2.0f);
        this.nameLabel.setPosition(26.0f, 34.0f);
        table.add((Table) this.nameLabel);
        this.effectIconsTable = new Table();
        table.add(this.effectIconsTable).padLeft(4.0f);
        table.add().height(1.0f).growX();
        this.hpLabelCurrent = new LabelWithShadow("", Game.i.assetManager.getLabelStyle(21)).setShadowColor(new Color(0.0f, 0.0f, 0.0f, 1.0f)).setShadowShift(0.0f, -2.0f);
        table.add((Table) this.hpLabelCurrent);
        this.hpLabelMax = new LabelWithShadow("", Game.i.assetManager.getLabelStyle(18)).setShadowColor(new Color(0.0f, 0.0f, 0.0f, 1.0f)).setShadowShift(0.0f, -2.0f);
        table.add((Table) this.hpLabelMax).padLeft(2.0f);
        this.barBackgroundLarge = new Image(Game.i.assetManager.getQuad("ui.bossHpBar.lineLargeBg"));
        this.barBackgroundLarge.setPosition(2.0f, 14.0f);
        this.barBackgroundLarge.setSize(300.0f, 12.0f);
        addActor(this.barBackgroundLarge);
        this.largeBarContainer = new GroupClipping();
        this.largeBarContainer.setTransform(false);
        this.largeBarContainer.setPosition(2.0f, 14.0f);
        this.largeBarContainer.setSize(300.0f, 12.0f);
        addActor(this.largeBarContainer);
        this.barLarge = new Image(Game.i.assetManager.getQuad("ui.bossHpBar.lineLarge"));
        this.barLarge.setSize(300.0f, 12.0f);
        this.largeBarContainer.addActor(this.barLarge);
        this.iconImageShadow = new Image();
        this.iconImageShadow.setPosition(-13.0f, 15.0f);
        this.iconImageShadow.setSize(32.0f, 32.0f);
        this.iconImageShadow.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        addActor(this.iconImageShadow);
        this.iconImage = new Image();
        this.iconImage.setPosition(-15.0f, 17.0f);
        this.iconImage.setSize(32.0f, 32.0f);
        addActor(this.iconImage);
        this.barBackgroundSmallOne = new Image(Game.i.assetManager.getQuad("ui.bossHpBar.lineSmallBg"));
        this.barBackgroundSmallOne.setPosition(6.0f, 8.0f);
        this.barBackgroundSmallOne.setSize(292.0f, 4.0f);
        addActor(this.barBackgroundSmallOne);
        this.smallBarOneContainer = new GroupClipping();
        this.smallBarOneContainer.setTransform(false);
        this.smallBarOneContainer.setPosition(6.0f, 8.0f);
        this.smallBarOneContainer.setSize(292.0f, 4.0f);
        addActor(this.smallBarOneContainer);
        this.barSmallOne = new Image(Game.i.assetManager.getQuad("ui.bossHpBar.lineSmall"));
        this.barSmallOne.setSize(292.0f, 4.0f);
        this.smallBarOneContainer.addActor(this.barSmallOne);
        this.barBackgroundSmallTwo = new Image(Game.i.assetManager.getQuad("ui.bossHpBar.lineSmallBg"));
        this.barBackgroundSmallTwo.setPosition(8.0f, 2.0f);
        this.barBackgroundSmallTwo.setSize(288.0f, 4.0f);
        addActor(this.barBackgroundSmallTwo);
        this.smallBarTwoContainer = new GroupClipping();
        this.smallBarTwoContainer.setTransform(false);
        this.smallBarTwoContainer.setPosition(8.0f, 2.0f);
        this.smallBarTwoContainer.setSize(288.0f, 4.0f);
        addActor(this.smallBarTwoContainer);
        this.barSmallTwo = new Image(Game.i.assetManager.getQuad("ui.bossHpBar.lineSmall"));
        this.barSmallTwo.setSize(288.0f, 4.0f);
        this.smallBarTwoContainer.addActor(this.barSmallTwo);
        this.marksGroup = new Group();
        this.marksGroup.setTransform(false);
        this.marksGroup.setPosition(2.0f, 12.0f);
        addActor(this.marksGroup);
        setSmallBarsCount(0);
        setLabelsColor(Color.WHITE);
        setMainBarColor(Color.DARK_GRAY, Color.GRAY);
    }

    public final BossHpBar setLabelsColor(Color color) {
        this.nameLabel.setColor(color);
        this.hpLabelCurrent.setColor(color);
        this.hpLabelMax.setColor(color);
        return this;
    }

    public final BossHpBar setMainBarColor(Color color, Color color2) {
        this.barBackgroundLarge.setColor(color);
        this.barLarge.setColor(color2);
        return this;
    }

    public final BossHpBar setFirstSmallBarColor(Color color, Color color2) {
        this.barBackgroundSmallOne.setColor(color);
        this.barSmallOne.setColor(color2);
        return this;
    }

    public final BossHpBar setSecondSmallBarColor(Color color, Color color2) {
        this.barBackgroundSmallTwo.setColor(color);
        this.barSmallTwo.setColor(color2);
        return this;
    }

    public final BossHpBar setSmallBarsCount(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 2) {
            i = 2;
        }
        this.barBackgroundSmallOne.setVisible(false);
        this.barSmallOne.setVisible(false);
        this.barBackgroundSmallTwo.setVisible(false);
        this.barSmallTwo.setVisible(false);
        switch (i) {
            case 0:
                this.backgroundImage.setDrawable(Game.i.assetManager.getQuad("ui.bossHpBar.bgOneLine"));
                break;
            case 1:
                this.barBackgroundSmallOne.setVisible(true);
                this.barSmallOne.setVisible(true);
                this.backgroundImage.setDrawable(Game.i.assetManager.getQuad("ui.bossHpBar.bgTwoLines"));
                break;
            case 2:
                this.barBackgroundSmallOne.setVisible(true);
                this.barSmallOne.setVisible(true);
                this.barBackgroundSmallTwo.setVisible(true);
                this.barSmallTwo.setVisible(true);
                this.backgroundImage.setDrawable(Game.i.assetManager.getQuad("ui.bossHpBar.bgThreeLines"));
                break;
        }
        return this;
    }

    public final BossHpBar setBossName(String str) {
        Preconditions.checkNotNull(str, "Name is null");
        if (!this.l.equals(str)) {
            this.l = str;
            this.nameLabel.setText(str);
        }
        return this;
    }

    public final String getBossName() {
        return this.l;
    }

    public final BossHpBar setIcon(Drawable drawable) {
        this.iconImage.setDrawable(drawable);
        this.iconImageShadow.setDrawable(drawable);
        return this;
    }

    public final BossHpBar setMainHP(double d, double d2) {
        String stringBuilder = StringFormatter.compactNumber(d, false).toString();
        String str = "/ " + StringFormatter.compactNumber(d2, false).toString() + " HP";
        this.hpLabelCurrent.setText(stringBuilder);
        this.hpLabelMax.setText(str);
        this.largeBarContainer.setWidth(MathUtils.clamp((float) (d / d2), 0.0f, 1.0f) * 300.0f);
        return this;
    }

    public final BossHpBar setSmallBarOneProgress(double d) {
        this.smallBarOneContainer.setWidth(((float) d) * 292.0f);
        return this;
    }

    public final BossHpBar setSmallBarTwoProgress(double d) {
        this.smallBarTwoContainer.setWidth(((float) d) * 288.0f);
        return this;
    }

    public final BossHpBar clearMarks() {
        this.marksGroup.clear();
        return this;
    }

    public final BossHpBar addMark(float f) {
        Image image = new Image(Game.i.assetManager.getQuad("ui.bossHpBar.mark"));
        image.setSize(10.0f, 7.0f);
        image.setPosition((f * 300.0f) - 5.0f, 0.0f);
        this.marksGroup.addActor(image);
        return this;
    }

    public final BossHpBar addEffectIcon(Drawable drawable) {
        this.effectIconsTable.add((Table) new Image(drawable)).size(22.0f);
        this.k.add(drawable);
        return this;
    }

    public final Array<Drawable> getEffectIcons() {
        return this.k;
    }

    public final boolean isEffectIconExists(Drawable drawable) {
        return this.k.contains(drawable, true);
    }

    public final BossHpBar clearEffectIcons() {
        this.effectIconsTable.clear();
        this.k.clear();
        return this;
    }
}
