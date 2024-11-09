package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Interpolation;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.buffs.NoBonusSystemPointsBuff;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/NukeAbility.class */
public class NukeAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1804b = {100, 125, 170, User32.VK_PLAY, 350, 475, 600, 725, 850, 1000, 1200};
    private static final int[][] c = {new int[]{10, 20, 45, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 15, 25, 60, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 15, 35, 70, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 15, 35, 60, 0, 150}, new int[]{0, 0, 0, 0, 0, 0, 0, 15, 35, 80, 100}};

    @NAGS
    private float d;

    @NAGS
    private float e;
    public float damage;
    private boolean f;

    /* synthetic */ NukeAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.damage);
        output.writeBoolean(this.f);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.damage = input.readFloat();
        this.f = input.readBoolean();
    }

    private NukeAbility() {
        super(AbilityType.NUKE);
    }

    public boolean isKilledEnemyNotAffectsBonusSystem() {
        return this.f;
    }

    public void setKilledEnemyNotAffectsBonusSystem(boolean z) {
        this.f = z;
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_NUKE_COINS);
        this.d = i;
        this.e = i2;
        this.damage = (float) (d * this.S.gameValue.getIntValue(GameValueType.ABILITY_NUKE_DAMAGE) * 0.009999999776482582d);
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
        this.S.map.spawnedEnemies.begin();
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy enemy = this.S.map.spawnedEnemies.items[i].enemy;
            if (enemy != null) {
                if (this.f) {
                    NoBonusSystemPointsBuff noBonusSystemPointsBuff = new NoBonusSystemPointsBuff();
                    noBonusSystemPointsBuff.setup(this.S.gameValue.getTickRateDeltaTime() + 0.001f, this.S.gameValue.getTickRateDeltaTime() + 0.001f);
                    this.S.buff.P_NO_BONUS_SYSTEM_POINTS.addBuff(enemy, noBonusSystemPointsBuff);
                }
                float abilityVulnerability = this.damage * enemy.getAbilityVulnerability(AbilityType.NUKE);
                if (abilityVulnerability > 0.0f) {
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, abilityVulnerability, DamageType.EXPLOSION).setAbility(this).setCleanForDps(false).setEfficiency(8));
                }
            }
        }
        this.S.map.spawnedEnemies.end();
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void startEffects() {
        a(1.0f);
        if (this.S._particle != null) {
            ParticleEffectPool.PooledEffect obtain = Game.i.abilityManager.F.NUKE.f1805a.obtain();
            obtain.setPosition(this.d, this.e);
            this.S._particle.addParticle(obtain, false);
        }
        if (this.S._input != null && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.CAMERA_SHAKE_ENABLED) == 1.0d) {
            this.S._input.cameraController.animate(new CameraController.ShakeAnimation(35.0f, 2.5f));
            this.S._gameUi.screenBorderGradient.fullscreenFlash(Color.WHITE.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), 2.0f, Interpolation.pow2In);
            this.S.gameState.animateSpeed(0.35f, 4.0f);
        }
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/NukeAbility$NukeAbilityFactory.class */
    public static class NukeAbilityFactory extends Ability.Factory<NukeAbility> {

        /* renamed from: a, reason: collision with root package name */
        private ParticleEffectPool f1805a;

        public NukeAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public NukeAbility create() {
            return new NukeAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.f1805a = Game.i.assetManager.getParticleEffectPool("nuke-explosion.prt");
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.PURPLE.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_NUKE", Integer.valueOf(gameValueProvider.getIntValue(GameValueType.ABILITY_NUKE_DAMAGE))) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_NUKE_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.PURPLE.P700;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getTitle() {
            return Game.i.localeManager.i18n.get("ability_name_NUKE");
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return NukeAbility.f1804b[StrictMath.min(i, NukeAbility.f1804b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return NukeAbility.c[resourceType.ordinal()][StrictMath.min(i, NukeAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-nuke");
        }
    }
}
