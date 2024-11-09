package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.projectiles.ChainLightningProjectile;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.shapes.ChainLightning;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.FloatSorter;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/BallLightningAbility.class */
public final class BallLightningAbility extends Ability {
    public static final float DEFAULT_ATTACK_INTERVAL = 0.2f;
    private Vector2 d;
    private Vector2 e;

    @NAGS
    private final Vector2 f;
    private Enemy.EnemyReference g;
    public float duration;
    public float damage;

    @Null
    public Tower launchedByTower;
    public float attackInterval;
    private float h;
    private float i;
    private DelayedRemovalArray<Enemy.EnemyReference> j;
    private int k;

    @NAGS
    private final DelayedRemovalArray<ChainLightning> l;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1788b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 750, 875, 1000};
    private static final int[][] c = {new int[]{5, 10, 25, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 5, 15, 30, 0, 0, 0, 0, 0, User32.VK_PLAY}, new int[]{0, 0, 0, 0, 10, 25, 40, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 10, 20, 60, 0, 150}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 20, 75, 0}};
    private static final Color m = new Color();

    /* synthetic */ BallLightningAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.g);
        output.writeFloat(this.duration);
        output.writeFloat(this.damage);
        kryo.writeClassAndObject(output, this.launchedByTower);
        output.writeFloat(this.attackInterval);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
        kryo.writeObject(output, this.j);
        output.writeVarInt(this.k, true);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = (Vector2) kryo.readObject(input, Vector2.class);
        this.e = (Vector2) kryo.readObject(input, Vector2.class);
        this.g = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.duration = input.readFloat();
        this.damage = input.readFloat();
        this.launchedByTower = (Tower) kryo.readClassAndObject(input);
        this.attackInterval = input.readFloat();
        this.h = input.readFloat();
        this.i = input.readFloat();
        this.j = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.k = input.readVarInt(true);
    }

    @Override // com.prineside.tdi2.Ability
    public final void configure(int i, int i2, double d) {
        this.duration = this.S.gameValue.getFloatValue(GameValueType.ABILITY_BALL_LIGHTNING_DURATION);
        this.i = 0.0f;
        this.h = 0.0f;
        this.launchedByTower = null;
        this.damage = (float) (this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_BALL_LIGHTNING_DAMAGE) * d);
        this.d.set(i, i2);
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_BALL_LIGHTNING_COINS);
    }

    private BallLightningAbility() {
        super(AbilityType.BALL_LIGHTNING);
        this.d = new Vector2();
        this.e = new Vector2();
        this.f = new Vector2();
        this.g = Enemy.EnemyReference.NULL;
        this.attackInterval = 0.2f;
        this.i = 0.0f;
        this.j = new DelayedRemovalArray<>(false, 4, Enemy.EnemyReference.class);
        this.k = 0;
        this.l = new DelayedRemovalArray<>(false, 8, ChainLightning.class);
    }

    @Override // com.prineside.tdi2.Ability
    public final boolean start() {
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
    public final void update(float f) {
        this.h += f;
        if (this.launchedByTower != null && !this.launchedByTower.isRegistered()) {
            this.launchedByTower = null;
            this.h = this.duration;
            return;
        }
        if (this.g.enemy == null) {
            float f2 = Float.MAX_VALUE;
            Enemy enemy = null;
            for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
                Enemy enemy2 = this.S.map.spawnedEnemies.items[i].enemy;
                if (enemy2 != null && enemy2.isVulnerableTo(DamageType.ELECTRICITY) && (this.launchedByTower == null || this.launchedByTower.canAttackEnemy(enemy2))) {
                    if (enemy != null) {
                        float dst2 = this.d.dst2(enemy2.getPosition());
                        if (dst2 < f2) {
                            enemy = enemy2;
                            f2 = dst2;
                        }
                    } else {
                        enemy = enemy2;
                    }
                }
            }
            if (enemy != null) {
                this.g = this.S.enemy.getReference(enemy);
            }
        }
        if (this.g.enemy != null) {
            this.f.set(this.g.enemy.getPosition());
            this.f.sub(this.d);
            this.f.nor();
            this.f.scl(10.0f * f);
            this.e.add(this.f);
            this.e.limit2(100.0f);
        } else {
            this.e.scl(0.9f);
        }
        this.d.add(this.e);
        this.i += f;
        if (this.i >= this.attackInterval) {
            this.i -= this.attackInterval;
            this.j.begin();
            for (int i2 = 0; i2 < this.j.size; i2++) {
                if (this.j.items[i2].enemy == null) {
                    this.j.removeIndex(i2);
                }
            }
            this.j.end();
            if (this.j.size < 3 && this.j.size < this.S.map.spawnedEnemies.size) {
                FloatSorter floatSorter = this.S.TSH.floatSorter;
                floatSorter.begin();
                for (int i3 = 0; i3 < this.S.map.spawnedEnemies.size; i3++) {
                    Enemy.EnemyReference enemyReference = this.S.map.spawnedEnemies.items[i3];
                    Enemy enemy3 = enemyReference.enemy;
                    if (enemy3 != null) {
                        floatSorter.add(enemyReference, this.d.dst2(enemy3.getPosition()));
                    }
                }
                Array<FloatSorter.Entity> sort = floatSorter.sort();
                for (int i4 = 0; i4 < sort.size; i4++) {
                    Enemy.EnemyReference enemyReference2 = (Enemy.EnemyReference) sort.items[i4].object;
                    Enemy enemy4 = enemyReference2.enemy;
                    if (enemy4.getAbilityVulnerability(AbilityType.BALL_LIGHTNING) != 0.0f && enemy4.isVulnerableTo(DamageType.ELECTRICITY) && (this.launchedByTower == null || this.launchedByTower.canAttackEnemy(enemy4))) {
                        boolean z = false;
                        int i5 = 0;
                        while (true) {
                            if (i5 >= this.j.size) {
                                break;
                            }
                            if (this.j.items[i5].enemy != enemy4) {
                                i5++;
                            } else {
                                z = true;
                                break;
                            }
                        }
                        if (!z) {
                            this.j.add(enemyReference2);
                            if (this.j.size == 3) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                floatSorter.end();
            }
            if (this.j.size != 0) {
                if (this.k >= this.j.size) {
                    this.k = 0;
                }
                Enemy enemy5 = this.j.items[this.k].enemy;
                ChainLightningProjectile obtain = this.S.projectile.F.CHAIN_LIGHTNING.obtain();
                this.S.projectile.register(obtain);
                float abilityVulnerability = this.damage * enemy5.getAbilityVulnerability(AbilityType.BALL_LIGHTNING);
                if (abilityVulnerability > 0.0f) {
                    obtain.setup(this.launchedByTower, enemy5, abilityVulnerability, abilityVulnerability * 0.1f, 0.0f, 0.0f, this.d);
                }
                this.k++;
            }
        }
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && this.S._gameUi != null) {
            if (this.h < this.duration) {
                int i6 = FastRandom.getInt(3) + 1;
                for (int i7 = 0; i7 < i6; i7++) {
                    ChainLightning obtain2 = Game.i.shapeManager.F.CHAIN_LIGHTNING.obtain();
                    obtain2.setTexture(Game.i.abilityManager.F.BALL_LIGHTNING.c, true, true);
                    m.set(MaterialColor.LIGHT_BLUE.P200);
                    m.f889a = 0.56f;
                    obtain2.setFadingToEnd(true);
                    obtain2.setColor(m);
                    obtain2.setup(this.d.x, this.d.y, this.d.x + ((FastRandom.getFloat() - 0.5f) * 2.0f * 100.0f), this.d.y + ((FastRandom.getFloat() - 0.5f) * 2.0f * 100.0f), 2.0f, 0.1f, false, 6.7200003f, 33.6f, 8.0f);
                    this.l.add(obtain2);
                }
            }
            this.l.begin();
            for (int i8 = 0; i8 < this.l.size; i8++) {
                ChainLightning chainLightning = this.l.items[i8];
                chainLightning.update(f);
                if (chainLightning.isFinished()) {
                    this.l.removeIndex(i8);
                    chainLightning.free();
                }
            }
            this.l.end();
        }
    }

    @Override // com.prineside.tdi2.Ability
    public final boolean isDone() {
        return this.h >= this.duration + 0.5f;
    }

    @Override // com.prineside.tdi2.Ability
    public final void draw(Batch batch, float f) {
    }

    @Override // com.prineside.tdi2.Ability
    public final void drawBatchAdditive(Batch batch, float f) {
        float f2 = 1.0f;
        if (this.h >= this.duration) {
            float f3 = 1.0f - ((this.h - this.duration) / 0.5f);
            f2 = f3;
            if (f3 < 0.0f) {
                f2 = 0.0f;
            }
        }
        m.set(MaterialColor.LIGHT_BLUE.P200);
        m.f889a = (0.16f + (PMath.sin(this.h * 5.0f) * 0.04f)) * f2;
        batch.setColor(m);
        batch.draw(Game.i.abilityManager.F.BALL_LIGHTNING.f1790b, this.d.x - 192.0f, this.d.y - 192.0f, 384.0f, 384.0f);
        m.set(1.0f, 1.0f, 1.0f, f2);
        batch.setColor(m);
        batch.draw(Game.i.abilityManager.F.BALL_LIGHTNING.f1789a, this.d.x - 32.0f, this.d.y - 32.0f, 64.0f, 64.0f);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        for (int i = 0; i < this.l.size; i++) {
            this.l.items[i].draw(batch);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/BallLightningAbility$BallLightningAbilityFactory.class */
    public static class BallLightningAbilityFactory extends Ability.Factory<BallLightningAbility> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1789a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1790b;
        private TextureRegion c;

        public BallLightningAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.f1789a = Game.i.assetManager.getTextureRegion("ball-lightning-orb");
            this.f1790b = Game.i.assetManager.getTextureRegion("particle-default");
            this.c = Game.i.assetManager.getTextureRegion("chain-lightning");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public BallLightningAbility create() {
            return new BallLightningAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return true;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.YELLOW.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_BALL_LIGHTNING", 3, Float.valueOf(0.6f), Float.valueOf(MathUtils.round(((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_BALL_LIGHTNING_DAMAGE)) * 1000.0f) / 10.0f), Float.valueOf(gameValueProvider.getFloatValue(GameValueType.ABILITY_BALL_LIGHTNING_DURATION))) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_BALL_LIGHTNING_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.YELLOW.P800;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return BallLightningAbility.f1788b[StrictMath.min(i, BallLightningAbility.f1788b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return BallLightningAbility.c[resourceType.ordinal()][StrictMath.min(i, BallLightningAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-ball-lightning");
        }
    }
}
