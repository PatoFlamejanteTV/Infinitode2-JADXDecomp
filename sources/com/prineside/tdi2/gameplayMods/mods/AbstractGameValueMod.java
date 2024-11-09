package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/AbstractGameValueMod.class */
public abstract class AbstractGameValueMod extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2085a = TLog.forClass(AbstractGameValueMod.class);

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private String f2086b;
    public GameValueType gvType;
    public GameplayModCategory mainCategory;
    public GameplayModCategory additionalCategory;
    public float baseValue;
    public float deltaPerPower;
    public boolean roundToInt;
    public boolean isFinalMultiplier;

    @Null
    private GameSystemProvider c;

    @Null
    private GameValueConfig d;

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.gvType);
        kryo.writeObject(output, this.mainCategory);
        kryo.writeObjectOrNull(output, this.additionalCategory, GameplayModCategory.class);
        output.writeFloat(this.baseValue);
        output.writeFloat(this.deltaPerPower);
        output.writeBoolean(this.roundToInt);
        output.writeBoolean(this.isFinalMultiplier);
        kryo.writeObjectOrNull(output, this.c, GameSystemProvider.class);
        kryo.writeObjectOrNull(output, this.d, GameValueConfig.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.gvType = (GameValueType) kryo.readObject(input, GameValueType.class);
        this.mainCategory = (GameplayModCategory) kryo.readObject(input, GameplayModCategory.class);
        this.additionalCategory = (GameplayModCategory) kryo.readObjectOrNull(input, GameplayModCategory.class);
        this.baseValue = input.readFloat();
        this.deltaPerPower = input.readFloat();
        this.roundToInt = input.readBoolean();
        this.isFinalMultiplier = input.readBoolean();
        this.c = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.d = (GameValueConfig) kryo.readObjectOrNull(input, GameValueConfig.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractGameValueMod() {
        this.mainCategory = GameplayModCategory.OTHER;
        this.additionalCategory = null;
    }

    public AbstractGameValueMod(GameValueType gameValueType) {
        this.mainCategory = GameplayModCategory.OTHER;
        this.additionalCategory = null;
        Preconditions.checkNotNull(gameValueType, "gvType can not be null");
        this.gvType = gameValueType;
    }

    public AbstractGameValueMod(GameValueType gameValueType, float f, float f2, boolean z, GameplayModCategory gameplayModCategory, @Null GameplayModCategory gameplayModCategory2) {
        this.mainCategory = GameplayModCategory.OTHER;
        this.additionalCategory = null;
        Preconditions.checkNotNull(gameValueType, "gvType can not be null");
        this.gvType = gameValueType;
        this.deltaPerPower = f2;
        this.baseValue = f;
        this.roundToInt = z;
        this.mainCategory = gameplayModCategory;
        this.additionalCategory = gameplayModCategory2;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public Drawable getIcon() {
        return Game.i.gameValueManager.getStockValueConfig(this.gvType).createIconForBackground(new Color(454761471));
    }

    private float a() {
        float f = this.baseValue + (this.deltaPerPower * this.power);
        if (this.roundToInt) {
            if (f < 0.0f) {
                f = -((int) (-f));
            } else {
                f = (int) f;
            }
        }
        return f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public AbstractGameValueMod applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        String string = jsonValue.getString("gvType", null);
        if (string != null) {
            try {
                this.gvType = GameValueType.valueOf(string);
            } catch (Exception e) {
                f2085a.e("failed to read gvType from config", e);
            }
        }
        this.deltaPerPower = jsonValue.getFloat("deltaPerPower", this.deltaPerPower);
        this.baseValue = jsonValue.getFloat("baseValue", this.baseValue);
        this.roundToInt = jsonValue.getBoolean("roundToInt", this.roundToInt);
        this.isFinalMultiplier = jsonValue.getBoolean("isFinalMultiplier", this.isFinalMultiplier);
        this.f2086b = null;
        return this;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public CharSequence getDescription() {
        if (this.isFinalMultiplier) {
            return ((Object) Game.i.gameValueManager.getTitle(this.gvType)) + " [#8BC34A]x" + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(a(), 2, true)) + "[]";
        }
        return Game.i.gameValueManager.formatEffectTitleValue(a(), this.gvType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final void a(GenericGameplayMod genericGameplayMod) {
        AbstractGameValueMod abstractGameValueMod = (AbstractGameValueMod) genericGameplayMod;
        super.a(abstractGameValueMod);
        abstractGameValueMod.f2086b = this.f2086b;
        abstractGameValueMod.gvType = this.gvType;
        abstractGameValueMod.deltaPerPower = this.deltaPerPower;
        abstractGameValueMod.baseValue = this.baseValue;
        abstractGameValueMod.roundToInt = this.roundToInt;
        abstractGameValueMod.isFinalMultiplier = this.isFinalMultiplier;
        abstractGameValueMod.mainCategory = this.mainCategory;
        abstractGameValueMod.additionalCategory = this.additionalCategory;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public boolean register(GameSystemProvider gameSystemProvider, String str) {
        this.c = gameSystemProvider;
        AbstractGameValueMod abstractGameValueMod = null;
        int i = 0;
        while (true) {
            if (i >= gameSystemProvider.gameplayMod.getActiveMods().size) {
                break;
            }
            GameplayModSystem.ActiveMod activeMod = gameSystemProvider.gameplayMod.getActiveMods().get(i);
            GameplayMod mod = activeMod.getMod();
            if ((mod instanceof AbstractGameValueMod) && activeMod.getSource().equals(str)) {
                AbstractGameValueMod abstractGameValueMod2 = (AbstractGameValueMod) mod;
                if (abstractGameValueMod2.d.getType() == this.gvType) {
                    abstractGameValueMod = abstractGameValueMod2;
                    break;
                }
            }
            i++;
        }
        if (abstractGameValueMod == null) {
            this.d = new GameValueConfig(this.gvType, a(), false, true);
            if (this.isFinalMultiplier) {
                this.d.setFinalGlobalMultiplier(true);
            }
            gameSystemProvider.gameValue.addCustomGameValue(this.d);
            b();
            return true;
        }
        abstractGameValueMod.setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public GameplayModCategory getCategory() {
        return this.mainCategory;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public GameplayModCategory getAdditionalCategory() {
        return this.additionalCategory;
    }

    private void b() {
        this.d.setValue(a());
        this.c.gameValue.recalculate();
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.prineside.tdi2.gameplayMods.GameplayMod
    public void setRegisteredPower(int i) {
        this.power = Math.min(i, getMaxPower());
        b();
    }
}
