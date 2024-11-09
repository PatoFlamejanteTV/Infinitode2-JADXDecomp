package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.modifiers.BountyModifier;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.shapes.MultiLine;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/LoicAbility.class */
public class LoicAbility extends Ability {
    private float d;
    private float e;
    private int f;

    @NAGS
    private float g;
    private float h;
    private Rectangle i;

    @NAGS
    private MultiLine j;

    @NAGS
    private ParticleEffectPool.PooledEffect k;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1798b = {100, 125, 170, User32.VK_PLAY, 350, 475, 600, 725, 850, 1000, 1200};
    private static final int[][] c = {new int[]{7, 15, 35, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 7, 15, 35, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 12, 25, 45, 0, 0, 0, 200}, new int[]{0, 0, 0, 0, 0, 0, 15, 45, 60, 0, 150}, new int[]{0, 0, 0, 0, 0, 0, 0, 15, 30, 80, 0}};
    private static final Color l = new Color();

    /* synthetic */ LoicAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.d);
        output.writeFloat(this.e);
        output.writeInt(this.f);
        output.writeFloat(this.h);
        kryo.writeObject(output, this.i);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readFloat();
        this.e = input.readFloat();
        this.f = input.readInt();
        this.h = input.readFloat();
        this.i = (Rectangle) kryo.readObject(input, Rectangle.class);
    }

    private LoicAbility() {
        super(AbilityType.LOIC);
        this.g = -1.0f;
        this.h = 0.0f;
        this.i = new Rectangle();
    }

    @Override // com.prineside.tdi2.Registrable
    public void setUnregistered() {
        super.setUnregistered();
        if (this.k != null) {
            this.k.allowCompletion();
            this.k = null;
        }
        this.j = null;
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_LOIC_COINS);
        this.h = 0.0f;
        this.f = i;
        this.d = (float) (this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_LOIC_DAMAGE) * d);
        this.e = this.S.gameValue.getFloatValue(GameValueType.ABILITY_LOIC_DURATION);
        this.i.set(i - 64.0f, 0.0f, 128.0f, this.S.map.getMap().getHeight() << 7);
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.d <= 0.0f) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_zero_damage"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        if (Game.i.shapeManager != null) {
            this.j = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                this.k = Game.i.abilityManager.F.LOIC.f1800b.obtain();
                this.k.setPosition(this.f, (this.S.map.getMap().getHeight() << 7) * 0.5f);
                float height = (this.S.map.getMap().getHeight() << 7) / 128.0f;
                Array<ParticleEmitter> emitters = this.k.getEmitters();
                for (int i = 0; i < emitters.size; i++) {
                    ParticleEmitter particleEmitter = emitters.get(i);
                    particleEmitter.getSpawnHeight().setHigh(128.0f * height);
                    particleEmitter.getYOffsetValue().setLow((-64.0f) * height);
                    particleEmitter.getEmission().setHigh(10.0f * height);
                }
                this.S._particle.addParticle(this.k, false);
            }
        }
        for (int i2 = 0; i2 < this.S.modifier.modifiers.size; i2++) {
            Modifier modifier = this.S.modifier.modifiers.get(i2);
            if (modifier instanceof BountyModifier) {
                ((BountyModifier) modifier).boostedByAbility = true;
            }
        }
        return true;
    }

    private void c() {
        float sin;
        if (this.h < 0.3f) {
            sin = this.h / 0.3f;
        } else if (this.e - this.h < 0.3f) {
            sin = (this.e - this.h) / 0.3f;
            if (this.k != null) {
                this.k.allowCompletion();
                this.k = null;
            }
        } else {
            float f = this.e - 0.6f;
            sin = (float) (0.9499999992549419d + (PMath.sin(((this.h - 0.3f) / (f / MathUtils.floor(f / 0.5f))) * 3.1415927f) * 0.1f * 0.5f));
        }
        float f2 = 128.0f * sin;
        if (f2 == this.g) {
            return;
        }
        this.g = f2;
        l.set(MaterialColor.CYAN.P200);
        this.j.reset();
        this.j.setTextureRegion(Game.i.abilityManager.F.LOIC.f1799a, false, false);
        int ceil = MathUtils.ceil(((this.S.map.getMap().getHeight() << 7) / Game.i.abilityManager.F.LOIC.f1799a.getRegionWidth()) + 2.0f);
        l.f889a = 0.0f;
        float height = (this.S.map.getMap().getHeight() << 7) + Game.i.abilityManager.F.LOIC.f1799a.getRegionWidth();
        this.j.appendNode(this.f, height, f2, l.toFloatBits(), false);
        for (int i = 0; i < ceil; i++) {
            l.f889a = sin;
            if (i == ceil - 1) {
                l.f889a = 0.0f;
            }
            height -= Game.i.abilityManager.F.LOIC.f1799a.getRegionWidth();
            this.j.appendNode(this.f, height, f2, l.toFloatBits(), false);
        }
        this.j.updateAllNodes();
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        this.h += f;
        float f2 = this.d * f;
        this.S.map.spawnedEnemies.begin();
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy enemy = this.S.map.spawnedEnemies.items[i].enemy;
            if (enemy != null && this.i.contains(enemy.getPosition())) {
                float abilityVulnerability = f2 * enemy.getAbilityVulnerability(AbilityType.LOIC);
                if (abilityVulnerability > 0.0f) {
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, abilityVulnerability, DamageType.LASER).setAbility(this).setEfficiency(4).setCleanForDps(false));
                }
            }
        }
        this.S.map.spawnedEnemies.end();
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.h >= this.e;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
    }

    @Override // com.prineside.tdi2.Ability
    public void drawBatchAdditive(Batch batch, float f) {
        if (this.j == null) {
            return;
        }
        c();
        this.j.draw(batch);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/LoicAbility$LoicAbilityFactory.class */
    public static class LoicAbilityFactory extends Ability.Factory<LoicAbility> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1799a;

        /* renamed from: b, reason: collision with root package name */
        private ParticleEffectPool f1800b;

        public LoicAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.f1799a = Game.i.assetManager.getTextureRegion("laser-wide");
            this.f1800b = Game.i.assetManager.getParticleEffectPool("loic.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public LoicAbility create() {
            return new LoicAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.CYAN.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_LOIC", Float.valueOf(MathUtils.round(((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_LOIC_DAMAGE)) * 1000.0f) / 10.0f), Float.valueOf(gameValueProvider.getFloatValue(GameValueType.ABILITY_LOIC_DURATION))) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_LOIC_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.CYAN.P800;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return LoicAbility.f1798b[StrictMath.min(i, LoicAbility.f1798b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return LoicAbility.c[resourceType.ordinal()][StrictMath.min(i, LoicAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-loic");
        }
    }
}
