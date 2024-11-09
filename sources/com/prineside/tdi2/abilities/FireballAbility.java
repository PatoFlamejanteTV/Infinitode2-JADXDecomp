package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.explosions.FireballExplosion;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/FireballAbility.class */
public class FireballAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1794b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 750, 875, 1000};
    private static final int[][] c = {new int[]{4, 8, 20, 25, 0, 0, 0, 0, 0, 0, 300}, new int[]{0, 0, 4, 8, 20, 35, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 8, 20, 45, 0, 0, 0, 200}, new int[]{0, 0, 0, 0, 0, 0, 10, 20, 55, 80, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 25, 60, 0}};
    private FireballExplosion d;

    /* synthetic */ FireballAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.d);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = (FireballExplosion) kryo.readClassAndObject(input);
    }

    private FireballAbility() {
        super(AbilityType.FIREBALL);
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f1656a = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_FIREBALL_COINS);
        float percentValueAsMultiplier = ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_FIREBALL_FIRE_DAMAGE)) * 0.1f;
        this.d = this.S.explosion.F.FIREBALL.obtain();
        this.d.setup(i, i2, (float) (((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_FIREBALL_DAMAGE)) * d), (float) (percentValueAsMultiplier * d), 4.0f, this);
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.d.getDamage() <= 0.0f && this.d.getFireDamage() <= 0.0f) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_zero_damage"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        this.S.explosion.register(this.d);
        this.d.explode();
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void startEffects() {
        a(1.0f);
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

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/FireballAbility$FireballAbilityFactory.class */
    public static class FireballAbilityFactory extends Ability.Factory<FireballAbility> {
        public FireballAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public FireballAbility create() {
            return new FireballAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return true;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.DEEP_ORANGE.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_FIREBALL", Integer.valueOf(gameValueProvider.getIntValue(GameValueType.ABILITY_FIREBALL_DAMAGE)), Integer.valueOf(gameValueProvider.getIntValue(GameValueType.ABILITY_FIREBALL_FIRE_DAMAGE))) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_coins_for_killed_enemies", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_FIREBALL_COINS) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.DEEP_ORANGE.P700;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getTitle() {
            return Game.i.localeManager.i18n.get("ability_name_FIREBALL");
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return FireballAbility.f1794b[StrictMath.min(i, FireballAbility.f1794b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return FireballAbility.c[resourceType.ordinal()][StrictMath.min(i, FireballAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-fireball");
        }
    }
}
