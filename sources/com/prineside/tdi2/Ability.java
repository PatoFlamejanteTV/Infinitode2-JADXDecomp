package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Ability.class */
public abstract class Ability extends Registrable implements KryoSerializable {

    /* renamed from: b, reason: collision with root package name */
    private AbilityType f1655b;

    /* renamed from: a, reason: collision with root package name */
    protected float f1656a = 1.0f;

    public abstract void configure(int i, int i2, double d);

    public abstract void update(float f);

    public abstract boolean isDone();

    public abstract void draw(Batch batch, float f);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.f1655b, AbilityType.class);
        output.writeFloat(this.f1656a);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1655b = (AbilityType) kryo.readObjectOrNull(input, AbilityType.class);
        this.f1656a = input.readFloat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Ability(AbilityType abilityType) {
        this.f1655b = abilityType;
    }

    public AbilityType getType() {
        return this.f1655b;
    }

    public boolean start() {
        return true;
    }

    public void startEffects() {
        a(1.5f);
    }

    public void onDone() {
    }

    public void drawBatchAdditive(Batch batch, float f) {
    }

    public final float getKilledEnemiesCoinMultiplier() {
        return this.f1656a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(float f) {
        if (this.S._gameUi != null && !this.S.gameState.isFastForwarding()) {
            Color cpy = Game.i.abilityManager.getFactory(this.f1655b).getColor().cpy();
            cpy.f889a = 0.56f;
            this.S._gameUi.screenBorderGradient.flash(cpy, f);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Ability$Factory.class */
    public static abstract class Factory<T extends Ability> implements EntityFactory {
        public final AbilityType abilityType;

        /* renamed from: a, reason: collision with root package name */
        private String f1657a;

        /* renamed from: b, reason: collision with root package name */
        private String f1658b;
        private String c;

        public abstract T create();

        public abstract boolean requiresMapPointing();

        public abstract Color getColor();

        public abstract Color getDarkerColor();

        public abstract TextureRegionDrawable getIconDrawable();

        public Factory(AbilityType abilityType) {
            this.abilityType = abilityType;
            this.f1657a = "ability_name_" + abilityType.name();
            this.f1658b = "ability_name_fancy_" + abilityType.name();
            this.c = "ability_description_" + abilityType.name();
        }

        public void setup() {
            if (Game.i.assetManager != null) {
                setupAssets();
            }
        }

        public void setupAssets() {
        }

        public CharSequence getTitle() {
            return Game.i.localeManager.i18n.get(this.f1657a);
        }

        public CharSequence getFancyTitle() {
            return Game.i.localeManager.i18n.get(this.f1658b);
        }

        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.get(this.c);
        }

        public int getPriceInGreenPapers(int i) {
            return 0;
        }

        public int getPriceInResources(ResourceType resourceType, int i) {
            return 0;
        }
    }
}
