package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.ParticlesCanvas;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/FirestormAbility.class */
public class FirestormAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1795b = {100, 125, 170, User32.VK_PLAY, 350, 475, 600, 725, 850, 1000, 1200};
    private static final int[][] c = {new int[]{7, 15, 35, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 7, 15, 30, 0, 0, 0, 0, 0, User32.VK_PLAY}, new int[]{0, 0, 0, 0, 10, 25, 50, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 15, 35, 60, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 15, 35, 80, 100}};
    public float progressCoeff;
    public Array<ObjectPair<Enemy, BurnBuff>> buffsToAdd;

    @NAGS
    private ParticlesCanvas.ParticleConfig d;

    /* synthetic */ FirestormAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.progressCoeff);
        kryo.writeObject(output, this.buffsToAdd);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.progressCoeff = input.readFloat();
        this.buffsToAdd = (Array) kryo.readObject(input, Array.class);
    }

    private FirestormAbility() {
        super(AbilityType.FIRESTORM);
        this.buffsToAdd = new Array<>(true, 1, ObjectPair.class);
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_FIRESTORM_COINS);
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.ABILITY_FIRESTORM_DURATION);
        float percentValueAsMultiplier = (float) (((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_FIRESTORM_DAMAGE)) * d);
        this.buffsToAdd.clear();
        if (percentValueAsMultiplier > 0.0f) {
            this.S.map.spawnedEnemies.begin();
            for (int i3 = 0; i3 < this.S.map.spawnedEnemies.size; i3++) {
                Enemy enemy = this.S.map.spawnedEnemies.items[i3].enemy;
                if (enemy != null) {
                    float abilityVulnerability = percentValueAsMultiplier * enemy.getAbilityVulnerability(AbilityType.FIRESTORM);
                    if (abilityVulnerability > 0.0f) {
                        BurnBuff burnBuff = new BurnBuff();
                        burnBuff.setup(null, floatValue, floatValue * 10.0f, abilityVulnerability, this);
                        this.buffsToAdd.add(new ObjectPair<>(enemy, burnBuff));
                    }
                }
            }
            this.S.map.spawnedEnemies.end();
        }
        this.progressCoeff = 0.0f;
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.buffsToAdd.size == 0) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_zero_damage"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        for (int i = 0; i < this.buffsToAdd.size; i++) {
            ObjectPair<Enemy, BurnBuff> objectPair = this.buffsToAdd.items[i];
            this.S.buff.P_BURN.addBuff(objectPair.first, objectPair.second);
        }
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void startEffects() {
        a(1.5f);
        if (this.S._particle == null || !Game.i.settingsManager.isParticlesDrawing() || this.S._gameUi == null || this.S.gameState.canSkipMediaUpdate()) {
            return;
        }
        this.S._gameUi.mainUi.particlesCanvas.addParticle(Game.i.abilityManager.F.FIRESTORM.f1796a.obtain(), 0.0f, 0.0f);
        this.S._gameUi.mainUi.particlesCanvas.addParticle(Game.i.abilityManager.F.FIRESTORM.f1797b.obtain(), this.S._gameUi.mainUi.particlesCanvas.getWidth(), 0.0f);
        this.d = this.S._gameUi.mainUi.particlesCanvas.addParticle(Game.i.abilityManager.F.FIRESTORM.c.obtain(), 0.0f, 0.0f);
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        this.progressCoeff += f * 0.5f;
        if (this.d != null) {
            this.d.x = Game.i.uiManager.viewport.getWorldWidth() * this.progressCoeff;
        }
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.progressCoeff >= 1.0f;
    }

    @Override // com.prineside.tdi2.Ability
    public void onDone() {
        if (this.d != null) {
            this.d.effect.allowCompletion();
            this.d = null;
        }
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/FirestormAbility$FirestormAbilityFactory.class */
    public static class FirestormAbilityFactory extends Ability.Factory<FirestormAbility> {

        /* renamed from: a, reason: collision with root package name */
        private ParticleEffectPool f1796a;

        /* renamed from: b, reason: collision with root package name */
        private ParticleEffectPool f1797b;
        private ParticleEffectPool c;

        public FirestormAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.f1796a = Game.i.assetManager.getParticleEffectPool("screen-sparks-bottom-left.prt");
            this.f1797b = Game.i.assetManager.getParticleEffectPool("screen-sparks-bottom-right.prt");
            this.c = Game.i.assetManager.getParticleEffectPool("fire-2.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public FirestormAbility create() {
            return new FirestormAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.RED.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            float floatValue = gameValueProvider.getFloatValue(GameValueType.ABILITY_FIRESTORM_DURATION);
            float percentValueAsMultiplier = (float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_FIRESTORM_DAMAGE);
            return Game.i.localeManager.i18n.format("ability_description_FIRESTORM", Float.valueOf(MathUtils.round(percentValueAsMultiplier * 1000.0f) * 0.1f), Float.valueOf(floatValue)) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_FIRESTORM_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.RED.P800;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return FirestormAbility.f1795b[StrictMath.min(i, FirestormAbility.f1795b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return FirestormAbility.c[resourceType.ordinal()][StrictMath.min(i, FirestormAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-firestorm");
        }
    }
}
