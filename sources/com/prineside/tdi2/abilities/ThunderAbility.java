package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.shapes.ChainLightning;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.EntityUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/ThunderAbility.class */
public class ThunderAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1809b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 700, User32.WM_DWMCOLORIZATIONCOLORCHANGED, 850};
    private static final int[][] c = {new int[]{5, 10, 25, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 5, 15, 30, 0, 0, 0, 0, 0, User32.VK_PLAY}, new int[]{0, 0, 0, 0, 10, 25, 40, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 10, 20, 60, 0, 150}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 20, 75, 0}};
    public int chargesCount;
    public int targetChargesCount;
    public float timeSinceLastCharge;
    public float damage;

    @NAGS
    private final ChainLightning[] d;

    /* synthetic */ ThunderAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.chargesCount, true);
        output.writeVarInt(this.targetChargesCount, true);
        output.writeFloat(this.timeSinceLastCharge);
        output.writeFloat(this.damage);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.chargesCount = input.readVarInt(true);
        this.targetChargesCount = input.readVarInt(true);
        this.timeSinceLastCharge = input.readFloat();
        this.damage = input.readFloat();
    }

    private ThunderAbility() {
        super(AbilityType.THUNDER);
        this.chargesCount = 0;
        this.timeSinceLastCharge = 0.0f;
        this.d = new ChainLightning[128];
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_THUNDER_COINS);
        this.damage = (float) (this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_THUNDER_DAMAGE) * d);
        this.targetChargesCount = this.S.gameValue.getIntValue(GameValueType.ABILITY_THUNDER_CHARGES_COUNT);
        if (this.targetChargesCount >= 128) {
            this.targetChargesCount = 128;
        }
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.damage <= 0.0f) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_zero_damage"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        if (this.chargesCount < this.targetChargesCount) {
            EntityUtils.removeNullRefs(this.S.map.spawnedEnemies);
            if (this.S.map.spawnedEnemies.size != 0) {
                Enemy enemy = this.S.map.spawnedEnemies.items[this.S.gameState.randomInt(this.S.map.spawnedEnemies.size)].enemy;
                if (this.S.enemy.enemyDamageVulnerability[enemy.type.ordinal()][DamageType.ELECTRICITY.ordinal()]) {
                    float abilityVulnerability = this.damage * enemy.getAbilityVulnerability(AbilityType.THUNDER);
                    if (abilityVulnerability > 0.0f) {
                        if (Game.i.shapeManager != null && !this.S.gameState.canSkipMediaUpdate()) {
                            ChainLightning chainLightning = (ChainLightning) Game.i.shapeManager.getFactory(ShapeType.CHAIN_LIGHTNING).obtain();
                            chainLightning.setTexture(Game.i.abilityManager.F.THUNDER.lightningTexture, true, true);
                            chainLightning.setColor(MaterialColor.LIGHT_BLUE.P200);
                            this.d[this.chargesCount] = chainLightning;
                            chainLightning.setup(enemy.getPosition().x, enemy.getPosition().y + 4096.0f, enemy.getPosition().x, enemy.getPosition().y, 1.0f, 0.15f, true, 30.72f, 128.0f, 25.6f);
                        }
                        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                            ParticleEffectPool.PooledEffect obtain = Game.i.abilityManager.F.THUNDER.f1810a.obtain();
                            obtain.setPosition(enemy.getPosition().x, enemy.getPosition().y);
                            this.S._particle.addParticle(obtain, false);
                        }
                        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, abilityVulnerability, DamageType.ELECTRICITY).setAbility(this).setEfficiency(8));
                        this.chargesCount++;
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        this.timeSinceLastCharge += f;
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.chargesCount >= this.targetChargesCount && this.timeSinceLastCharge > 1.0f;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
    }

    @Override // com.prineside.tdi2.Ability
    public void drawBatchAdditive(Batch batch, float f) {
        for (int i = 0; i < this.d.length; i++) {
            if (this.d[i] != null) {
                this.d[i].update(f);
                this.d[i].draw(batch);
                if (this.d[i].isFinished()) {
                    this.d[i].free();
                    this.d[i] = null;
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/ThunderAbility$ThunderAbilityFactory.class */
    public static class ThunderAbilityFactory extends Ability.Factory<ThunderAbility> {
        public TextureRegion lightningTexture;

        /* renamed from: a, reason: collision with root package name */
        ParticleEffectPool f1810a;

        public ThunderAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.lightningTexture = Game.i.assetManager.getTextureRegion("chain-lightning-wide");
            this.f1810a = Game.i.assetManager.getParticleEffectPool("sparks.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public ThunderAbility create() {
            return new ThunderAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.INDIGO.P400;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_THUNDER", Integer.valueOf(gameValueProvider.getIntValue(GameValueType.ABILITY_THUNDER_CHARGES_COUNT)), Integer.valueOf(gameValueProvider.getIntValue(GameValueType.ABILITY_THUNDER_DAMAGE))) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_THUNDER_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.INDIGO.P600;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return ThunderAbility.f1809b[StrictMath.min(i, ThunderAbility.f1809b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return ThunderAbility.c[resourceType.ordinal()][StrictMath.min(i, ThunderAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-thunder");
        }
    }
}
