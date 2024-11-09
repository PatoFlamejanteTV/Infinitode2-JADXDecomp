package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.shapes.Circle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/SmokeBombAbility.class */
public class SmokeBombAbility extends Ability {
    private float d;
    private float e;
    private int f;
    private int g;
    private double h;

    @NAGS
    private ParticleEffectPool.PooledEffect i;

    @NAGS
    private Circle j;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1807b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 750, 875, 1000};
    private static final int[][] c = {new int[]{4, 8, 20, 25, 0, 0, 0, 0, 0, 0, 300}, new int[]{0, 0, 4, 8, 20, 35, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 8, 20, 45, 0, 0, 0, 200}, new int[]{0, 0, 0, 0, 0, 0, 10, 20, 55, 80, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 25, 60, 0}};
    private static final StringBuilder k = new StringBuilder();

    /* synthetic */ SmokeBombAbility(byte b2) {
        this();
    }

    private SmokeBombAbility() {
        super(AbilityType.SMOKE_BOMB);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.d);
        output.writeFloat(this.e);
        output.writeInt(this.f);
        output.writeInt(this.g);
        output.writeDouble(this.h);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readFloat();
        this.e = input.readFloat();
        this.f = input.readInt();
        this.g = input.readInt();
        this.h = input.readDouble();
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.h = d;
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_SMOKE_BOMB_COINS);
        this.f = i;
        this.g = i2;
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.ABILITY_SMOKE_BOMB_DURATION);
        this.e = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_SMOKE_BOMB_DAMAGE);
        this.d = floatValue;
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.h * this.e <= 0.0d) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_zero_damage"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            this.i = Game.i.abilityManager.F.SMOKE_BOMB.f1808a.obtain();
            this.S._particle.addParticle(this.i, false);
            this.i.setPosition(this.f, this.g);
            return true;
        }
        return true;
    }

    @Override // com.prineside.tdi2.Registrable
    public void setUnregistered() {
        if (this.i != null) {
            this.i.allowCompletion();
            this.i = null;
        }
        if (this.j != null) {
            this.j.free();
            this.j = null;
        }
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        this.d -= f;
        float f2 = ((float) this.h) * this.e * f;
        this.S.map.spawnedEnemies.begin();
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy enemy = this.S.map.spawnedEnemies.items[i].enemy;
            if (enemy != null && PMath.getSquareDistanceBetweenPoints(enemy.getPosition().x, enemy.getPosition().y, this.f, this.g) < 65536.0f && enemy.isVulnerableTo(DamageType.POISON)) {
                float abilityVulnerability = f2 * enemy.getAbilityVulnerability(AbilityType.SMOKE_BOMB);
                if (abilityVulnerability > 0.0f) {
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, abilityVulnerability, DamageType.POISON).setAbility(this).setEfficiency(4));
                }
            }
        }
        this.S.map.spawnedEnemies.end();
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.d <= 0.0f;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
            if (this.j == null && Game.i.shapeManager != null) {
                this.j = (Circle) Game.i.shapeManager.getFactory(ShapeType.CIRCLE).obtain();
                Color cpy = MaterialColor.LIGHT_GREEN.P300.cpy();
                cpy.f889a = 0.07f;
                Color cpy2 = MaterialColor.LIGHT_GREEN.P300.cpy();
                cpy2.f889a = 0.21f;
                this.j.setup(this.f, this.g, 0.0f, 256.0f, 32, cpy.toFloatBits(), cpy2.toFloatBits());
            }
            if (this.j != null) {
                this.j.draw(batch);
                batch.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                batch.draw(AssetManager.TextureRegions.i().iconSmokeBomb, (this.f - 32) + 3, (this.g - 32) - 3, 64.0f, 64.0f);
                batch.setColor(MaterialColor.LIGHT_GREEN.P300);
                batch.draw(AssetManager.TextureRegions.i().iconSmokeBomb, this.f - 32, this.g - 32, 64.0f, 64.0f);
                batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
                k.setLength(0);
                k.append((int) this.d);
                k.append('.');
                k.append((int) ((this.d % 1.0f) * 10.0f));
                ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(30);
                font.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                font.draw(batch, k, (this.f - 1) + 2.0f, (this.g + 64) - 2.0f, 2.0f, 1, false);
                font.setColor(MaterialColor.LIGHT_GREEN.P300);
                font.draw(batch, k, this.f - 1, this.g + 64, 2.0f, 1, false);
                font.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/SmokeBombAbility$SmokeBombAbilityFactory.class */
    public static class SmokeBombAbilityFactory extends Ability.Factory<SmokeBombAbility> {

        /* renamed from: a, reason: collision with root package name */
        ParticleEffectPool f1808a;

        public SmokeBombAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.f1808a = Game.i.assetManager.getParticleEffectPool("ability-poison-cloud.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public SmokeBombAbility create() {
            return new SmokeBombAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_GREEN.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_SMOKE_BOMB", Float.valueOf(MathUtils.round(((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_SMOKE_BOMB_DAMAGE)) * 1000.0f) / 10.0f), Float.valueOf(gameValueProvider.getFloatValue(GameValueType.ABILITY_SMOKE_BOMB_DURATION))) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_SMOKE_BOMB_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.LIGHT_GREEN.P800;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return SmokeBombAbility.f1807b[StrictMath.min(i, SmokeBombAbility.f1807b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return SmokeBombAbility.c[resourceType.ordinal()][StrictMath.min(i, SmokeBombAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-smoke-bomb");
        }
    }
}
