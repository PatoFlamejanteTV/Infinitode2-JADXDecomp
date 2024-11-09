package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.IntSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.CollidingProjectile;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.ProjectileDespawn;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/MagnetAbility.class */
public class MagnetAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1802b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 750, 875, 1000};
    private static final int[][] c = {new int[]{5, 10, 25, 0, 0, 0, 0, 0, 0, 0, 300}, new int[]{0, 0, 5, 10, 30, 0, 0, 0, 0, 0, User32.VK_PLAY}, new int[]{0, 0, 0, 0, 10, 20, 50, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 10, 25, 45, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 25, 75, 0}};
    private float d;
    private Enemy.EnemyReference e;
    private IntSet f;
    private float g;
    private OnProjectileDespawn h;

    @NAGS
    private ParticleEffectPool.PooledEffect i;

    @NAGS
    private boolean j;

    @NAGS
    private boolean k;

    /* synthetic */ MagnetAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.f);
        output.writeFloat(this.g);
        kryo.writeObject(output, this.h);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readFloat();
        this.e = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.f = (IntSet) kryo.readObject(input, IntSet.class);
        this.g = input.readFloat();
        this.h = (OnProjectileDespawn) kryo.readObject(input, OnProjectileDespawn.class);
    }

    private MagnetAbility() {
        super(AbilityType.MAGNET);
        this.e = Enemy.EnemyReference.NULL;
        this.f = new IntSet();
        this.h = new OnProjectileDespawn(this, (byte) 0);
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_MAGNET_COINS);
        this.g = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_MAGNET_DAMAGE_MULTIPLIER);
        this.d = 0.0f;
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        for (int i = 0; i < this.S.ability.activeAbilities.size; i++) {
            if (this.S.ability.activeAbilities.get(i).getType() == AbilityType.MAGNET) {
                if (this.S._gameUi != null) {
                    Notifications.i().add(Game.i.localeManager.i18n.get("multiple_abilities_can_not_be_active"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                    return false;
                }
                return false;
            }
        }
        this.S.events.getListeners(ProjectileDespawn.class).addStateAffecting(this.h);
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            this.i = Game.i.abilityManager.F.MAGNET.f1803a.obtain();
        }
        if (this.e.enemy == null) {
            c();
            return true;
        }
        return true;
    }

    private void c() {
        this.e = Enemy.EnemyReference.NULL;
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy.EnemyReference enemyReference = this.S.map.spawnedEnemies.items[i];
            if (enemyReference.enemy != null && (this.e.enemy == null || enemyReference.enemy.passedTiles > this.e.enemy.passedTiles)) {
                this.e = enemyReference;
            }
        }
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        this.d += f;
        if (this.d <= 8.0f) {
            if (this.e.enemy == null) {
                c();
            }
            if (this.e.enemy != null) {
                for (int i = 0; i < this.S.projectile.projectiles.size; i++) {
                    Projectile projectile = this.S.projectile.projectiles.items[i];
                    if (!this.f.contains(projectile.id)) {
                        this.f.add(projectile.id);
                        projectile.multiplyDamage(this.g);
                        if (projectile instanceof CollidingProjectile) {
                            ((CollidingProjectile) projectile).totalFlyTime *= 2.0f;
                        }
                    }
                    projectile.flyOnEnemy(this.e.enemy);
                }
            }
        } else if (this.i != null && !this.k) {
            this.i.allowCompletion();
            this.k = true;
        }
        if (this.e.enemy != null && this.i != null) {
            this.i.setPosition(this.e.enemy.getPosition().x, this.e.enemy.getPosition().y);
            if (!this.j) {
                this.j = true;
                this.S._particle.addParticle(this.i, false);
            }
        }
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.d > 9.7f;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
    }

    @Override // com.prineside.tdi2.Registrable
    public void setUnregistered() {
        this.e = Enemy.EnemyReference.NULL;
        this.S.events.getListeners(ProjectileDespawn.class).remove(this.h);
        this.f.clear();
        if (this.i != null) {
            this.i.allowCompletion();
            this.i = null;
        }
        super.setUnregistered();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/MagnetAbility$MagnetAbilityFactory.class */
    public static class MagnetAbilityFactory extends Ability.Factory<MagnetAbility> {

        /* renamed from: a, reason: collision with root package name */
        private ParticleEffectPool f1803a;

        public MagnetAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            this.f1803a = Game.i.assetManager.getParticleEffectPool("ability-magnet.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public MagnetAbility create() {
            return new MagnetAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.PINK.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_MAGNET", Float.valueOf(MathUtils.round(((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_MAGNET_DAMAGE_MULTIPLIER)) * 1000.0f) / 10.0f)) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_MAGNET_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.PINK.P800;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return MagnetAbility.f1802b[StrictMath.min(i, MagnetAbility.f1802b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return MagnetAbility.c[resourceType.ordinal()][StrictMath.min(i, MagnetAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-magnet");
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/MagnetAbility$OnProjectileDespawn.class */
    public static final class OnProjectileDespawn extends SerializableListener<MagnetAbility> implements Listener<ProjectileDespawn> {
        /* synthetic */ OnProjectileDespawn(MagnetAbility magnetAbility, byte b2) {
            this(magnetAbility);
        }

        private OnProjectileDespawn() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnProjectileDespawn(MagnetAbility magnetAbility) {
            this.f1759a = magnetAbility;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(ProjectileDespawn projectileDespawn) {
            ((MagnetAbility) this.f1759a).f.remove(projectileDespawn.getProjectile().id);
        }
    }
}
