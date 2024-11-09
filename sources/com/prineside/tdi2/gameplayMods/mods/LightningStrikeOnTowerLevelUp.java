package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.abilities.ThunderAbility;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.TowerLevelUp;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LightningStrikeOnTowerLevelUp.class */
public final class LightningStrikeOnTowerLevelUp extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2143a = TLog.forClass(LightningStrikeOnTowerLevelUp.class);

    /* renamed from: b, reason: collision with root package name */
    private float f2144b = 0.5f;
    private float c = 0.5f;
    private int d = 10;
    private float e = 2.0f;
    private float f = 1.0f;
    private GameSystemProvider g;
    private OnTowerLevelUp h;
    private IntIntMap i;

    public LightningStrikeOnTowerLevelUp() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.h = new OnTowerLevelUp(this, (byte) 0);
        this.i = new IntIntMap();
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2144b);
        output.writeFloat(this.c);
        output.writeInt(this.d);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        kryo.writeObjectOrNull(output, this.g, GameSystemProvider.class);
        kryo.writeObject(output, this.h);
        kryo.writeObject(output, this.i);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2144b = input.readFloat();
        this.c = input.readFloat();
        this.d = input.readInt();
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.h = (OnTowerLevelUp) kryo.readObject(input, OnTowerLevelUp.class);
        this.i = (IntIntMap) kryo.readObject(input, IntIntMap.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.LightningStrikeOnTowerLevelUp");
    }

    public final int getStrikeCount() {
        return MathUtils.floor(this.e + (this.f * this.power) + 0.01f);
    }

    public final float getMdpsMultiplier() {
        return this.f2144b + (this.c * this.power);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_lightning_strike_on_tower_level_up", Integer.valueOf(this.d), Integer.valueOf(getStrikeCount()), StringFormatter.compactNumberWithPrecisionTrimZeros(getMdpsMultiplier() * 100.0f, 1, true).toString());
    }

    public final void resetStrikeLevelLimits() {
        this.i.clear();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        LightningStrikeOnTowerLevelUp lightningStrikeOnTowerLevelUp = new LightningStrikeOnTowerLevelUp();
        a(lightningStrikeOnTowerLevelUp);
        lightningStrikeOnTowerLevelUp.f2144b = this.f2144b;
        lightningStrikeOnTowerLevelUp.c = this.c;
        lightningStrikeOnTowerLevelUp.d = this.d;
        lightningStrikeOnTowerLevelUp.e = this.e;
        lightningStrikeOnTowerLevelUp.f = this.f;
        return lightningStrikeOnTowerLevelUp;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(LightningStrikeOnTowerLevelUp.class, str);
        if (activeModFromSource == null) {
            this.g = gameSystemProvider;
            gameSystemProvider.events.getListeners(TowerLevelUp.class).addStateAffecting(this.h);
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    public final void strike(int i, int i2, int i3) {
        if (i3 >= this.d) {
            int i4 = (i2 * 65497) + i;
            int i5 = this.i.get(i4, 0);
            if (i3 > i5) {
                this.i.put(i4, i3);
                Map map = this.g.map.getMap();
                ThunderAbility thunderAbility = (ThunderAbility) Game.i.abilityManager.getFactory(AbilityType.THUNDER).create();
                this.g.ability.registerAndConfigure(thunderAbility, (int) ((map.getWidth() << 7) * 0.5f), (int) ((map.getHeight() << 7) * 0.5f), this.g.damage.getTowersMaxDps() * getMdpsMultiplier());
                thunderAbility.targetChargesCount = getStrikeCount();
                ThunderAbility thunderAbility2 = (ThunderAbility) this.g.ability.startAbility(thunderAbility);
                if (thunderAbility2 != null) {
                    thunderAbility2.startEffects();
                    return;
                }
                return;
            }
            f2143a.i("skipped strike - observed max tower xp level on " + i + ":" + i2 + " is >= than current (" + i5 + " >= " + i3 + ")", new Object[0]);
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final LightningStrikeOnTowerLevelUp applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2144b = jsonValue.getFloat("baseDamage", this.f2144b);
        this.c = jsonValue.getFloat("damagePerPower", this.c);
        this.d = jsonValue.getInt("minXpLevel", this.d);
        this.e = jsonValue.getFloat("enemyCount", this.e);
        this.f = jsonValue.getFloat("enemyCountPerPower", this.f);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LightningStrikeOnTowerLevelUp$OnTowerLevelUp.class */
    public static final class OnTowerLevelUp extends SerializableListener<LightningStrikeOnTowerLevelUp> implements Listener<TowerLevelUp> {
        /* synthetic */ OnTowerLevelUp(LightningStrikeOnTowerLevelUp lightningStrikeOnTowerLevelUp, byte b2) {
            this(lightningStrikeOnTowerLevelUp);
        }

        private OnTowerLevelUp() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTowerLevelUp(LightningStrikeOnTowerLevelUp lightningStrikeOnTowerLevelUp) {
            this.f1759a = lightningStrikeOnTowerLevelUp;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TowerLevelUp towerLevelUp) {
            Tower tower = towerLevelUp.getTower();
            ((LightningStrikeOnTowerLevelUp) this.f1759a).strike(tower.getTile().getX(), tower.getTile().getY(), tower.getLevel());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/LightningStrikeOnTowerLevelUp$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2145a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2145a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2145a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("LightningStrikeOnTowerLevelUp");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new LightningStrikeOnTowerLevelUp().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.7f).setPowerUpProbabilityMultiplier(0.9f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
