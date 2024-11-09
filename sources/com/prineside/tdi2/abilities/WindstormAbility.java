package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.buffs.ThrowBackBuff;
import com.prineside.tdi2.buffs.processors.ThrowBackBuffProcessor;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/WindstormAbility.class */
public class WindstormAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1811b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 750, 875, 1000};
    private static final int[][] c = {new int[]{5, 10, 25, 0, 0, 0, 0, 0, 0, 0, 300}, new int[]{0, 0, 5, 10, 30, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 10, 20, 50, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 10, 25, 45, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 25, 75, 100}};
    private int d;
    private int e;
    private float f;
    private float g;
    private float h;
    private float i;

    @NAGS
    private ParticleEffectPool.PooledEffect j;
    private Array<Enemy.EnemyReference> k;

    /* synthetic */ WindstormAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.d, true);
        output.writeInt(this.e, true);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
        kryo.writeObject(output, this.k);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readInt(true);
        this.e = input.readInt(true);
        this.f = input.readFloat();
        this.g = input.readFloat();
        this.h = input.readFloat();
        this.i = input.readFloat();
        this.k = (Array) kryo.readObject(input, Array.class);
    }

    private WindstormAbility() {
        super(AbilityType.WINDSTORM);
        this.i = 0.0f;
        this.k = new Array<>(Enemy.EnemyReference.class);
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.d = i;
        this.e = i2;
        this.f = this.S.gameValue.getFloatValue(GameValueType.ABILITY_WINDSTORM_RANGE);
        this.g = this.S.gameValue.getFloatValue(GameValueType.ABILITY_WINDSTORM_DURATION);
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S.gameState.canSkipMediaUpdate()) {
            this.j = Game.i.abilityManager.F.WINDSTORM.f1812a.obtain();
            this.j.setPosition(this.d, this.e);
            float f = (this.f * 128.0f) / 128.0f;
            this.j.getEmitters().first().getVelocity().setHigh(140.0f * f, 200.0f * f);
            this.S._particle.addParticle(this.j, false);
        }
        this.S.wave.freezeTimeToNextWave(this.g * 2.0f);
        this.S.wave.setForceWaveDoubleBonus(true);
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        DelayedRemovalArray buffsByTypeOrNull;
        this.i -= f;
        this.h += f;
        if (this.h >= this.g) {
            ThrowBackBuffProcessor throwBackBuffProcessor = this.S.buff.P_THROW_BACK;
            for (int i = 0; i < this.k.size; i++) {
                Enemy enemy = this.k.items[i].enemy;
                if (enemy != null) {
                    DelayedRemovalArray buffsByTypeOrNull2 = enemy.getBuffsByTypeOrNull(BuffType.THROW_BACK);
                    for (int i2 = buffsByTypeOrNull2.size - 1; i2 >= 0; i2--) {
                        if (((ThrowBackBuff) buffsByTypeOrNull2.items[i2]).ownerId == -1) {
                            throwBackBuffProcessor.removeBuffAtIndex(enemy, BuffType.THROW_BACK, i2);
                        }
                    }
                }
            }
            this.k.clear();
            if (this.j != null) {
                this.j.allowCompletion();
                return;
            }
            return;
        }
        if (this.i <= 0.0f) {
            ThrowBackBuffProcessor throwBackBuffProcessor2 = this.S.buff.P_THROW_BACK;
            float f2 = this.f * 128.0f * this.f * 128.0f;
            this.S.map.spawnedEnemies.begin();
            for (int i3 = 0; i3 < this.S.map.spawnedEnemies.size; i3++) {
                Enemy enemy2 = this.S.map.spawnedEnemies.items[i3].enemy;
                if (enemy2 != null && (((buffsByTypeOrNull = enemy2.getBuffsByTypeOrNull(BuffType.THROW_BACK)) == null || buffsByTypeOrNull.size == 0) && PMath.getSquareDistanceBetweenPoints(this.d, this.e, enemy2.getPosition().x, enemy2.getPosition().y) < f2)) {
                    ThrowBackBuff throwBackBuff = new ThrowBackBuff();
                    throwBackBuff.setup(-1, 2.5f, this.g + 0.01f, this.g * 10.0f);
                    if (throwBackBuffProcessor2.addBuff(enemy2, throwBackBuff)) {
                        this.k.add(this.S.enemy.getReference(enemy2));
                    }
                }
            }
            this.S.map.spawnedEnemies.end();
            this.i = 0.191f;
        }
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.h >= this.g;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
        float f2 = this.h / this.g;
        float f3 = f2;
        if (f2 > 1.0f) {
            f3 = 1.0f;
        }
        ResourcePack.AtlasTextureRegion textureRegion = Game.i.assetManager.getTextureRegion("particle-shockwave-twirled-fat");
        float f4 = 1.0f;
        if (f3 <= 0.05f) {
            f4 = f3 / 0.05f;
        } else if (f3 >= 0.95f) {
            f4 = 1.0f - ((f3 - 0.95f) / 0.05f);
        }
        batch.setColor(1.0f, 1.0f, 1.0f, f4 * 0.19f);
        batch.draw(textureRegion, this.d - (this.f * 128.0f), this.e - (this.f * 128.0f), this.f * 128.0f, this.f * 128.0f, this.f * 2.0f * 128.0f, this.f * 2.0f * 128.0f, 1.0f, 1.0f, (-this.h) * 90.0f);
        batch.draw(textureRegion, this.d - (this.f * 128.0f), this.e - (this.f * 128.0f), this.f * 128.0f, this.f * 128.0f, this.f * 2.0f * 128.0f, this.f * 2.0f * 128.0f, 0.74f, 0.74f, (-this.h) * 120.0f);
        batch.draw(textureRegion, this.d - (this.f * 128.0f), this.e - (this.f * 128.0f), this.f * 128.0f, this.f * 128.0f, this.f * 2.0f * 128.0f, this.f * 2.0f * 128.0f, 0.54760003f, 0.54760003f, (-this.h) * 150.0f);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/WindstormAbility$WindstormAbilityFactory.class */
    public static class WindstormAbilityFactory extends Ability.Factory<WindstormAbility> {

        /* renamed from: a, reason: collision with root package name */
        private ParticleEffectPool f1812a;

        public WindstormAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.f1812a = Game.i.assetManager.getParticleEffectPool("windstorm.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public WindstormAbility create() {
            return new WindstormAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.BLUE.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_WINDSTORM", Float.valueOf(gameValueProvider.getFloatValue(GameValueType.ABILITY_WINDSTORM_RANGE)), Float.valueOf(gameValueProvider.getFloatValue(GameValueType.ABILITY_WINDSTORM_DURATION))) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_delays_next_wave", StringFormatter.compactNumberWithPrecision(r0 * 2.0f, 1));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.BLUE.P700;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return WindstormAbility.f1811b[StrictMath.min(i, WindstormAbility.f1811b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return WindstormAbility.c[resourceType.ordinal()][StrictMath.min(i, WindstormAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-windstorm");
        }
    }
}
