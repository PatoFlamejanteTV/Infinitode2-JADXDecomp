package com.prineside.tdi2.modifiers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ExpLine;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Comparator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/BalanceModifier.class */
public final class BalanceModifier extends Modifier {
    public static final int MAX_LEVEL = 5;
    public Mode mode;

    /* renamed from: b, reason: collision with root package name */
    private int f2589b;
    private float c;
    private float d;

    @NAGS
    private final Array<NeighbourXpConfig> f;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2588a = TLog.forClass(BalanceModifier.class);
    public static int[] XP_FOR_LEVEL = {0, 100, 300, 1000, 2500};
    public static float[] SPEED_BY_LEVEL = {3.0f, 7.0f, 15.0f, 35.0f, 100.0f};
    private static final StringBuilder e = new StringBuilder();
    private static final Comparator<NeighbourXpConfig> g = (neighbourXpConfig, neighbourXpConfig2) -> {
        return Float.compare(neighbourXpConfig2.c, neighbourXpConfig.c);
    };

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/BalanceModifier$Mode.class */
    public enum Mode {
        BALANCE,
        EVOLVE;

        public static final Mode[] values = values();
    }

    /* synthetic */ BalanceModifier(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.mode);
        output.writeVarInt(this.f2589b, true);
        output.writeFloat(this.c);
        output.writeFloat(this.d);
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.mode = (Mode) kryo.readObject(input, Mode.class);
        this.f2589b = input.readVarInt(true);
        this.c = input.readFloat();
        this.d = input.readFloat();
    }

    private BalanceModifier() {
        super(ModifierType.BALANCE);
        this.mode = Mode.BALANCE;
        this.f2589b = 1;
        this.f = new Array<>(true, 8, NeighbourXpConfig.class);
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean connectsToMiners() {
        return false;
    }

    public final int getLevel() {
        return this.f2589b;
    }

    public final void setLevel(int i) {
        if (i > 5 || i <= 0) {
            throw new IllegalArgumentException("level invalid " + i);
        }
        this.f2589b = i;
        this.S.map.markTilesDirtyNearTile(getTile(), 1);
    }

    public final int getXpEvolveRequirement() {
        return (int) (XP_FOR_LEVEL[this.f2589b] * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_BALANCE_UPGRADE_PRICE));
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean hasCustomButton() {
        return this.mode == Mode.BALANCE && this.f2589b < 5;
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean isCustomButtonNeedMapPoint() {
        return false;
    }

    @Override // com.prineside.tdi2.Modifier
    public final void fillModifierMenu(Group group, ObjectMap<String, Object> objectMap) {
        String str;
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        if (objectMap.size == 0 || !objectMap.get("state", -1).equals(1)) {
            f2588a.i("recreate custom menu", new Object[0]);
            group.clear();
            Group group2 = new Group();
            group2.setTransform(false);
            group2.setPosition(40.0f, 606.0f + scaledViewportHeight);
            group2.setSize(520.0f, 66.0f);
            group.addActor(group2);
            Label label = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
            label.setSize(40.0f, 26.0f);
            label.setPosition(480.0f, 40.0f);
            label.setAlignment(16);
            group2.addActor(label);
            Label label2 = new Label("", Game.i.assetManager.getLabelStyle(24));
            label2.setPosition(0.0f, 0.0f);
            label2.setSize(520.0f, 24.0f);
            label2.setAlignment(16);
            group2.addActor(label2);
            Label label3 = new Label("", Game.i.assetManager.getLabelStyle(30));
            label3.setPosition(0.0f, 40.0f);
            label3.setSize(520.0f, 26.0f);
            label3.setAlignment(8);
            group2.addActor(label3);
            ExpLine expLine = new ExpLine();
            expLine.setPosition(0.0f, 0.0f);
            expLine.setColor(MaterialColor.LIME.P500);
            group2.addActor(expLine);
            Actor image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            image.setPosition(0.0f, 478.0f + scaledViewportHeight);
            image.setSize(600.0f, 96.0f);
            group.addActor(image);
            Label label4 = new Label("", Game.i.assetManager.getLabelStyle(36));
            label4.setPosition(0.0f, 527.0f + scaledViewportHeight);
            label4.setSize(600.0f, 28.0f);
            label4.setAlignment(1);
            label4.setColor(MaterialColor.LIME.P500);
            group.addActor(label4);
            Label label5 = new Label("", Game.i.assetManager.getLabelStyle(24));
            label5.setPosition(0.0f, 498.0f + scaledViewportHeight);
            label5.setSize(600.0f, 17.0f);
            label5.setAlignment(1);
            label5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            group.addActor(label5);
            objectMap.put("statusLabel", label3);
            objectMap.put("levelLabel", label);
            objectMap.put("speedLabel", label4);
            objectMap.put("upgradeSpeedLabel", label5);
            objectMap.put("xpLabel", label2);
            objectMap.put("xpLine", expLine);
            objectMap.put("state", 1);
        }
        ExpLine expLine2 = (ExpLine) objectMap.get("xpLine");
        Label label6 = (Label) objectMap.get("xpLabel");
        Label label7 = (Label) objectMap.get("levelLabel");
        Label label8 = (Label) objectMap.get("statusLabel");
        Label label9 = (Label) objectMap.get("speedLabel");
        Label label10 = (Label) objectMap.get("upgradeSpeedLabel");
        label7.setText("L" + this.f2589b);
        if (this.f2589b >= 5) {
            expLine2.setCoeff(1.0f);
            label6.setText("MAX");
        } else {
            expLine2.setCoeff(this.c / getXpEvolveRequirement());
            label6.setText(((int) this.c) + " / " + getXpEvolveRequirement());
        }
        if (this.mode == Mode.EVOLVE) {
            str = Game.i.localeManager.i18n.get("state_upgrading");
            label8.setColor(MaterialColor.LIGHT_BLUE.P500);
        } else {
            str = Game.i.localeManager.i18n.get("state_balancing");
            label8.setColor(MaterialColor.LIME.P500);
        }
        label8.setText(str);
        e.setLength(0);
        e.append(StringFormatter.compactNumberWithPrecisionTrimZeros(getSpeed(), 1, true));
        e.append(" xp/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
        label9.setText(e);
        if (this.f2589b >= 5) {
            label9.setPosition(0.0f, 512.0f + scaledViewportHeight);
            label10.setVisible(false);
            return;
        }
        label9.setPosition(0.0f, 527.0f + scaledViewportHeight);
        e.setLength(0);
        e.append("L").append(this.f2589b + 1).append(": ");
        e.append(StringFormatter.compactNumberWithPrecisionTrimZeros(SPEED_BY_LEVEL[this.f2589b], 1, true));
        e.append(" xp/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
        label10.setText(e);
        label10.setVisible(true);
    }

    @Override // com.prineside.tdi2.Modifier
    public final void customButtonAction(int i, int i2) {
        if (this.f2589b < 5) {
            this.mode = Mode.EVOLVE;
        }
    }

    @Override // com.prineside.tdi2.Modifier
    public final void updateCustomButton(ComplexButton complexButton, boolean z) {
        complexButton.setText(Game.i.localeManager.i18n.get("do_upgrade"));
        complexButton.setIcon(Game.i.assetManager.getDrawable("icon-double-arrow-up"));
    }

    public final float getSpeed() {
        return SPEED_BY_LEVEL[this.f2589b - 1];
    }

    @Override // com.prineside.tdi2.Modifier
    public final void update(float f) {
        super.update(f);
        this.d += f;
        if (this.d > 0.265f) {
            float f2 = this.d;
            this.d = 0.0f;
            this.f.clear();
            this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                if (tile.type == TileType.PLATFORM) {
                    PlatformTile platformTile = (PlatformTile) tile;
                    if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                        Tower tower = (Tower) platformTile.building;
                        NeighbourXpConfig neighbourXpConfig = new NeighbourXpConfig();
                        neighbourXpConfig.f2591a = tower;
                        neighbourXpConfig.c = tower.experience;
                        neighbourXpConfig.f2592b = tower.experience - Tower.LEVEL_EXPERIENCE_MILESTONES[tower.getLevel()];
                        this.f.add(neighbourXpConfig);
                        return true;
                    }
                    return true;
                }
                return true;
            });
            if (this.f.size != 0) {
                this.S.TSH.sort.sort(this.f, g);
                if (this.mode == Mode.EVOLVE) {
                    float speed = getSpeed() * f2;
                    float xpEvolveRequirement = getXpEvolveRequirement() - this.c;
                    if (speed > xpEvolveRequirement) {
                        speed = xpEvolveRequirement;
                    }
                    if (speed > 0.0f) {
                        for (int i = 0; i < this.f.size; i++) {
                            NeighbourXpConfig neighbourXpConfig = this.f.items[i];
                            float min = StrictMath.min(speed, neighbourXpConfig.f2592b);
                            if (min > 0.0f) {
                                float removeExperienceRaw = this.S.experience.removeExperienceRaw(neighbourXpConfig.f2591a, min);
                                this.c += removeExperienceRaw;
                                speed -= removeExperienceRaw;
                                if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S.gameState.canSkipMediaUpdate()) {
                                    this.S._particle.addXpOrbParticle(removeExperienceRaw, neighbourXpConfig.f2591a.getTile().getX(), neighbourXpConfig.f2591a.getTile().getY(), getTile().getX(), getTile().getY());
                                }
                                if (speed <= 0.0f) {
                                    break;
                                }
                            }
                        }
                    }
                    if (this.f2589b >= 5) {
                        this.mode = Mode.BALANCE;
                    } else if (this.c >= getXpEvolveRequirement()) {
                        setLevel(this.f2589b + 1);
                        this.c = 0.0f;
                        this.mode = Mode.BALANCE;
                        if (this.S._sound != null) {
                            this.S._sound.playStatic(StaticSoundType.UPGRADE);
                        }
                        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                            ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.upgradeParticles.obtain();
                            obtain.setPosition(getTile().center.x, getTile().center.y);
                            this.S._particle.addParticle(obtain, true);
                        }
                        if (this.S._gameUi != null) {
                            this.S._gameUi.modifierMenu.updateCustomButton();
                        }
                    }
                } else if (this.mode == Mode.BALANCE && this.f.size > 1) {
                    float speed2 = getSpeed() * f2;
                    loop1: for (int i2 = 0; i2 < this.f.size; i2++) {
                        NeighbourXpConfig neighbourXpConfig2 = this.f.items[i2];
                        if (neighbourXpConfig2.f2592b >= 1.0f) {
                            for (int i3 = this.f.size - 1; i3 > i2; i3--) {
                                NeighbourXpConfig neighbourXpConfig3 = this.f.items[i3];
                                float f3 = (neighbourXpConfig2.c - neighbourXpConfig3.c) * 0.5f;
                                if (f3 >= 1.0f) {
                                    float min2 = StrictMath.min(speed2, StrictMath.min(neighbourXpConfig2.f2592b, f3));
                                    if (min2 != 0.0f) {
                                        float removeExperienceRaw2 = this.S.experience.removeExperienceRaw(neighbourXpConfig2.f2591a, min2);
                                        this.S.experience.addExperienceRaw(neighbourXpConfig3.f2591a, removeExperienceRaw2);
                                        speed2 -= removeExperienceRaw2;
                                        NeighbourXpConfig.a(neighbourXpConfig2, removeExperienceRaw2);
                                        NeighbourXpConfig.b(neighbourXpConfig2, removeExperienceRaw2);
                                        NeighbourXpConfig.c(neighbourXpConfig3, removeExperienceRaw2);
                                        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S.gameState.canSkipMediaUpdate()) {
                                            this.S._particle.addXpOrbParticle(removeExperienceRaw2, neighbourXpConfig2.f2591a.getTile().getX(), neighbourXpConfig2.f2591a.getTile().getY(), neighbourXpConfig3.f2591a.getTile().getX(), neighbourXpConfig3.f2591a.getTile().getY());
                                        }
                                        if (speed2 == 0.0f) {
                                            break loop1;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
                this.f.clear();
            }
        }
    }

    @Override // com.prineside.tdi2.Modifier
    public final void drawBatch(Batch batch, float f, MapRenderingSystem.DrawMode drawMode) {
        super.drawBatch(batch, f, drawMode);
        if (drawMode == MapRenderingSystem.DrawMode.DETAILED) {
            ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(36);
            e.setLength(0);
            e.append(this.f2589b);
            float x = getTile().getX() << 7;
            float y = getTile().getY() << 7;
            font.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            font.draw(batch, e, x + 3.0f, (y + 74.0f) - 3.0f, 128.0f, 1, false);
            font.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            font.draw(batch, e, x, y + 74.0f, 128.0f, 1, false);
            Mode mode = Mode.EVOLVE;
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d) {
            Game.i.assetManager.getFont(21).draw(batch, "B:" + (((int) (this.c * 10.0f)) / 10.0f) + "xp", getTile().center.x - 32.0f, (getTile().center.y - 64.0f) + 40.0f);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/BalanceModifier$BalanceModifierFactory.class */
    public static class BalanceModifierFactory extends Modifier.Factory<BalanceModifier> {
        private TextureRegion c;
        public TextureRegion orbTexture;

        public BalanceModifierFactory() {
            super(ModifierType.BALANCE, MaterialColor.LIME.P500, "icon-experience-balance");
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            return a((int) (25.0f * ((float) StrictMath.pow(1.399999976158142d, i))));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-balance");
            this.orbTexture = Game.i.assetManager.getTextureRegion("xp-orb");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public BalanceModifier create() {
            return new BalanceModifier((byte) 0);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/BalanceModifier$NeighbourXpConfig.class */
    public static class NeighbourXpConfig implements Pool.Poolable {

        /* renamed from: a, reason: collision with root package name */
        private Tower f2591a;

        /* renamed from: b, reason: collision with root package name */
        private float f2592b;
        private float c;

        static /* synthetic */ float a(NeighbourXpConfig neighbourXpConfig, float f) {
            float f2 = neighbourXpConfig.f2592b - f;
            neighbourXpConfig.f2592b = f2;
            return f2;
        }

        static /* synthetic */ float b(NeighbourXpConfig neighbourXpConfig, float f) {
            float f2 = neighbourXpConfig.c - f;
            neighbourXpConfig.c = f2;
            return f2;
        }

        static /* synthetic */ float c(NeighbourXpConfig neighbourXpConfig, float f) {
            float f2 = neighbourXpConfig.c + f;
            neighbourXpConfig.c = f2;
            return f2;
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            this.f2591a = null;
            this.f2592b = 0.0f;
            this.c = 0.0f;
        }
    }
}
