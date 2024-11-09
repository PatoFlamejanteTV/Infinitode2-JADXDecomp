package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.UiUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/WavesTimelineOverlay.class */
public class WavesTimelineOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3764a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 216, "WavesTimelineOverlay tint");

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3765b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 217, "WavesTimelineOverlay main");
    private Group c;
    private Runnable d;
    private ScrollPane e;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/WavesTimelineOverlay$WavesConfiguration.class */
    public static class WavesConfiguration {
        public int startWave = 1;
        public Array<SpawnTile.AllowedEnemyConfig> enemyConfigs = new Array<>(SpawnTile.AllowedEnemyConfig.class);
        public Array<Array<EnemyGroup>> enemyGroupsByWave = new Array<>(Array.class);
    }

    public static WavesTimelineOverlay i() {
        return (WavesTimelineOverlay) Game.i.uiManager.getComponent(WavesTimelineOverlay.class);
    }

    public WavesTimelineOverlay() {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(Config.BACKGROUND_COLOR);
        image.getColor().f889a = 0.78f;
        this.f3764a.getTable().add((Table) image).expand().fill();
        this.f3764a.getTable().setTouchable(Touchable.enabled);
        this.f3764a.getTable().addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.WavesTimelineOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                WavesTimelineOverlay.this.hide();
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            }
        });
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(630.0f, 380.0f);
        group.setTouchable(Touchable.childrenOnly);
        this.f3765b.getTable().add((Table) group).size(1260.0f, 760.0f);
        this.c = new Group();
        this.c.setTransform(true);
        this.c.setOrigin(630.0f, 380.0f);
        this.c.setSize(1260.0f, 760.0f);
        group.addActor(this.c);
        this.f3764a.getTable().setVisible(false);
        this.f3765b.getTable().setVisible(false);
    }

    public void setHideListener(Runnable runnable) {
        this.d = runnable;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
        if (this.d != null) {
            this.d.run();
            this.d = null;
        }
        this.c.clearChildren();
        Game.i.uiManager.stage.setScrollFocus(null);
    }

    public void setConfiguration(WavesConfiguration wavesConfiguration) {
        String str;
        this.c.clearChildren();
        int i = 0;
        for (int i2 = 0; i2 < wavesConfiguration.enemyConfigs.size; i2++) {
            if (!EnemyType.isBoss(wavesConfiguration.enemyConfigs.items[i2].enemyType)) {
                i++;
            }
        }
        if (i == 0) {
            i = 1;
        }
        float f = (i * 50.0f) + 132.0f + 30.0f;
        this.c.setOrigin(630.0f, f * 0.5f);
        this.c.setSize(1260.0f, f);
        this.c.setPosition(0.0f, (760.0f - f) * 0.5f);
        this.c.addActor(new QuadActor(new Color(791621631), new float[]{0.0f, 0.0f, 0.0f, f, 1260.0f, f - 12.0f, 1260.0f, 21.0f}));
        Label label = new Label(Game.i.localeManager.i18n.get("enemies_by_wave_from_all_portals"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        label.setPosition(40.0f, f - 76.0f);
        label.setSize(300.0f, 28.0f);
        this.c.addActor(label);
        float f2 = (i * 50.0f) + 33.0f;
        Group group = new Group();
        group.setTransform(false);
        group.setSize(5096.0f, f2);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setSize(5096.0f, f2);
        group.addActor(group2);
        this.e = new ScrollPane(group);
        UiUtils.enableMouseMoveScrollFocus(this.e);
        this.e.setSize(1260.0f, f2);
        this.e.setPosition(0.0f, 30.0f);
        this.e.setScrollingDisabled(false, true);
        this.c.addActor(this.e);
        Group group3 = new Group();
        group3.setTransform(false);
        group3.setTouchable(Touchable.disabled);
        group3.setSize(5096.0f, 33.0f);
        group3.setPosition(0.0f, f2 - 33.0f);
        group.addActor(group3);
        Group group4 = new Group();
        group4.setTransform(false);
        group4.setSize(5096.0f, f2);
        group.addActor(group4);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setPosition(96.0f, 0.0f);
        image.setSize(5000.0f, 33.0f);
        image.setColor(new Color(909522687));
        group3.addActor(image);
        for (int i3 = 1; i3 <= 100; i3++) {
            float f3 = ((i3 - 1) * 50) + 96.0f;
            Label label2 = new Label(String.valueOf((i3 + wavesConfiguration.startWave) - 1), Game.i.assetManager.getLabelStyle(21));
            label2.setPosition(f3, 0.0f);
            label2.setAlignment(1);
            label2.setSize(48.0f, 33.0f);
            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            group3.addActor(label2);
            Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image2.setSize(2.0f, f2);
            image2.setPosition(f3 + 48.0f, (-f2) + 33.0f);
            image2.setColor(new Color(791621631));
            group3.addActor(image2);
        }
        Group group5 = new Group();
        group5.setTransform(false);
        group5.setTouchable(Touchable.disabled);
        group5.setSize(96.0f, f2);
        group5.setPosition(0.0f, 30.0f);
        this.c.addActor(group5);
        int[] iArr = new int[EnemyType.values.length];
        for (EnemyType enemyType : EnemyType.values) {
            iArr[enemyType.ordinal()] = -1;
        }
        int i4 = 0;
        Color color = new Color();
        for (int i5 = 0; i5 < wavesConfiguration.enemyConfigs.size; i5++) {
            SpawnTile.AllowedEnemyConfig allowedEnemyConfig = wavesConfiguration.enemyConfigs.items[i5];
            if (!EnemyType.isBoss(allowedEnemyConfig.enemyType)) {
                int i6 = ((i - 1) * 50) - (i4 * 50);
                iArr[allowedEnemyConfig.enemyType.ordinal()] = i6;
                color.set(Game.i.enemyManager.getFactory(allowedEnemyConfig.enemyType).getColor());
                color.lerp(new Color(673720575), 0.56f);
                Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image3.setSize(86.0f, 48.0f);
                image3.setPosition(0.0f, i6);
                image3.setColor(color);
                group5.addActor(image3);
                QuadActor quadActor = new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f, 0.0f, 0.0f}, color);
                quadActor.setPosition(86.0f, i6);
                quadActor.setSize(10.0f, 48.0f);
                group5.addActor(quadActor);
                Image image4 = new Image(Game.i.enemyManager.getFactory(allowedEnemyConfig.enemyType).getTexture());
                image4.setPosition(30.0f, i6 + 7.0f);
                image4.setSize(32.0f, 32.0f);
                group5.addActor(image4);
                Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image5.setSize(5096.0f, 48.0f);
                image5.setPosition(0.0f, i6);
                image5.setColor(new Color(673720575));
                group2.addActor(image5);
                float f4 = (allowedEnemyConfig.firstWave <= 1 ? -96.0f : (allowedEnemyConfig.firstWave - 1) * 50) - ((wavesConfiguration.startWave - 1) * 50);
                float f5 = allowedEnemyConfig.lastWave <= 0 ? 5000.0f : (allowedEnemyConfig.lastWave * 50.0f) - ((wavesConfiguration.startWave - 1) * 50);
                color.f889a = 0.56f;
                Image image6 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image6.setSize(f5 - f4, 48.0f);
                image6.setPosition(f4 + 96.0f, i6);
                image6.setColor(color);
                group2.addActor(image6);
                i4++;
            }
        }
        boolean[] zArr = new boolean[EnemyType.values.length];
        for (int i7 = 1; i7 < wavesConfiguration.enemyGroupsByWave.size; i7++) {
            Array<EnemyGroup> array = wavesConfiguration.enemyGroupsByWave.items[i7];
            for (int i8 = 0; i8 < zArr.length; i8++) {
                zArr[i8] = false;
            }
            int i9 = 0;
            while (true) {
                if (i9 < array.size) {
                    EnemyGroup enemyGroup = array.get(i9);
                    float f6 = 96.0f + ((i7 - 1) * 50.0f);
                    if (EnemyType.isBoss(enemyGroup.getEnemyType())) {
                        Image image7 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        color.set(Game.i.enemyManager.getFactory(enemyGroup.getEnemyType()).getColor());
                        color.lerp(new Color(673720575), 0.56f);
                        image7.setColor(color);
                        image7.setPosition(f6, 0.0f);
                        image7.setSize(48.0f, (i * 50.0f) - 2.0f);
                        group4.addActor(image7);
                        Image image8 = new Image(Game.i.enemyManager.getFactory(enemyGroup.getEnemyType()).getTexture());
                        image8.setPosition(f6 - 8.0f, (((i * 50.0f) - 2.0f) * 0.5f) - 32.0f);
                        image8.setSize(64.0f, 64.0f);
                        group4.addActor(image8);
                        break;
                    }
                    int i10 = iArr[enemyGroup.getEnemyType().ordinal()];
                    if (i10 >= 0 && !zArr[enemyGroup.getEnemyType().ordinal()]) {
                        if (enemyGroup.interval <= 0.25f) {
                            str = "icon-density-high";
                        } else if (enemyGroup.interval >= 1.0f) {
                            str = "icon-density-low";
                        } else {
                            str = "icon-density-medium";
                        }
                        Image image9 = new Image(Game.i.assetManager.getDrawable(str));
                        image9.setPosition(f6, i10);
                        image9.setSize(48.0f, 48.0f);
                        image9.setColor(Game.i.enemyManager.getFactory(enemyGroup.getEnemyType()).getColor());
                        group4.addActor(image9);
                        zArr[enemyGroup.getEnemyType().ordinal()] = true;
                    }
                    i9++;
                }
            }
        }
    }

    public void setVisible(boolean z) {
        if (z) {
            UiUtils.bouncyShowOverlay(this.f3764a.getTable(), this.f3765b.getTable(), this.c);
            if (this.e != null) {
                Game.i.uiManager.stage.setScrollFocus(this.e);
                return;
            }
            return;
        }
        UiUtils.bouncyHideOverlay(this.f3764a.getTable(), this.f3765b.getTable(), this.c);
    }
}
