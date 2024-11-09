package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.projectiles.BulletWallProjectile;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/BulletWallAbility.class */
public class BulletWallAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1793b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 700, User32.WM_DWMCOLORIZATIONCOLORCHANGED, 850};
    private static final int[][] c = {new int[]{5, 10, 25, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 5, 10, 30, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 10, 20, 40, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 10, 25, 50, 0, 100}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 25, 80, 150}};
    public Array<PreparedBullet> preparedBullets;

    /* synthetic */ BulletWallAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.preparedBullets);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.preparedBullets = (Array) kryo.readObject(input, Array.class);
    }

    private BulletWallAbility() {
        super(AbilityType.BULLET_WALL);
        this.preparedBullets = new Array<>(true, 1, PreparedBullet.class);
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_BULLET_WALL_COINS);
        float round = MathUtils.round(this.S.gameValue.getFloatValue(GameValueType.ABILITY_BULLET_WALL_DENSITY) * 10.0f) / 10.0f;
        float percentValueAsMultiplier = ((float) d) * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_BULLET_WALL_DAMAGE));
        if (percentValueAsMultiplier <= 0.0f) {
            return;
        }
        int i3 = 0;
        float f = 0.0f;
        while (true) {
            float f2 = f;
            if (f2 < this.S.map.getMap().getHeight()) {
                PreparedBullet preparedBullet = new PreparedBullet();
                preparedBullet.damage = percentValueAsMultiplier;
                preparedBullet.startVector.set(0.0f, f2 * 128.0f);
                preparedBullet.endVector.set((this.S.map.getMap().getWidth() + 1.0f) * 128.0f, f2 * 128.0f);
                preparedBullet.speed = 5.0f + (PMath.sin(i3 * 10) * 0.5f);
                this.preparedBullets.add(preparedBullet);
                i3++;
                f = f2 + (1.0f / round);
            } else {
                return;
            }
        }
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.preparedBullets.size == 0) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_zero_damage"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        for (int i = 0; i < this.preparedBullets.size; i++) {
            PreparedBullet preparedBullet = this.preparedBullets.items[i];
            BulletWallProjectile obtain = this.S.projectile.F.BULLET_WALL.obtain();
            this.S.projectile.register(obtain);
            obtain.setup(preparedBullet.damage, preparedBullet.startVector, preparedBullet.endVector, preparedBullet.speed);
        }
        return true;
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

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/BulletWallAbility$BulletWallAbilityFactory.class */
    public static class BulletWallAbilityFactory extends Ability.Factory<BulletWallAbility> {
        public BulletWallAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public BulletWallAbility create() {
            return new BulletWallAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.TEAL.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_BULLET_WALL", Float.valueOf(MathUtils.round(gameValueProvider.getFloatValue(GameValueType.ABILITY_BULLET_WALL_DENSITY) * 10.0f) / 10.0f), Float.valueOf(MathUtils.round(((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_BULLET_WALL_DAMAGE)) * 1000.0f) / 10.0f)) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_BULLET_WALL_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.TEAL.P800;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return BulletWallAbility.f1793b[StrictMath.min(i, BulletWallAbility.f1793b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return BulletWallAbility.c[resourceType.ordinal()][StrictMath.min(i, BulletWallAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-bullet-wall");
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/BulletWallAbility$PreparedBullet.class */
    public static final class PreparedBullet implements KryoSerializable {
        public float damage;
        public float speed;
        public Vector2 startVector = new Vector2();
        public Vector2 endVector = new Vector2();

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeFloat(this.damage);
            output.writeFloat(this.speed);
            kryo.writeObject(output, this.startVector);
            kryo.writeObject(output, this.endVector);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.damage = input.readFloat();
            this.speed = input.readFloat();
            this.startVector = (Vector2) kryo.readObject(input, Vector2.class);
            this.endVector = (Vector2) kryo.readObject(input, Vector2.class);
        }
    }
}
