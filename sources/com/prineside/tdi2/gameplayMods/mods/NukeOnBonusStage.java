package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.abilities.NukeAbility;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BonusStageRequirementMet;
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
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/NukeOnBonusStage.class */
public final class NukeOnBonusStage extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private float f2164a = 1.0f;

    /* renamed from: b, reason: collision with root package name */
    private GameSystemProvider f2165b;
    private OnBonusStageRequirementMet c;

    public NukeOnBonusStage() {
        this.maxPower = 1;
        this.multipleInstances = false;
        this.c = new OnBonusStageRequirementMet(this);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2164a);
        kryo.writeObjectOrNull(output, this.f2165b, GameSystemProvider.class);
        kryo.writeObject(output, this.c);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2164a = input.readFloat();
        this.f2165b = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.c = (OnBonusStageRequirementMet) kryo.readObject(input, OnBonusStageRequirementMet.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.NukeOnBonusStage");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_nuke_on_bonus_stage", StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2164a * 100.0f, 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        NukeOnBonusStage nukeOnBonusStage = new NukeOnBonusStage();
        a(nukeOnBonusStage);
        nukeOnBonusStage.f2164a = this.f2164a;
        return nukeOnBonusStage;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(NukeOnBonusStage.class, str);
        if (activeModFromSource == null) {
            this.f2165b = gameSystemProvider;
            gameSystemProvider.events.getListeners(BonusStageRequirementMet.class).addStateAffecting(this.c).setDescription("NukeOnBonusStage - throws Nuke");
            nuke();
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    public final void nuke() {
        Enemy enemy;
        Map map = this.f2165b.map.getMap();
        NukeAbility nukeAbility = (NukeAbility) Game.i.abilityManager.getFactory(AbilityType.NUKE).create();
        int width = (int) ((map.getWidth() << 7) * 0.5f);
        int height = (int) ((map.getHeight() << 7) * 0.5f);
        if (this.f2165b.map.spawnedEnemies.size != 0 && (enemy = this.f2165b.map.spawnedEnemies.get(this.f2165b.gameState.randomInt(this.f2165b.map.spawnedEnemies.size)).enemy) != null) {
            width = (int) enemy.getPosition().x;
            height = (int) enemy.getPosition().y;
        }
        this.f2165b.ability.registerAndConfigure(nukeAbility, width, height, this.f2165b.damage.getTowersMaxDps() * this.f2164a);
        nukeAbility.setKilledEnemyNotAffectsBonusSystem(true);
        NukeAbility nukeAbility2 = (NukeAbility) this.f2165b.ability.startAbility(nukeAbility);
        if (nukeAbility2 != null) {
            nukeAbility2.startEffects();
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final NukeOnBonusStage applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2164a = jsonValue.getFloat("damageMultiplier", this.f2164a);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/NukeOnBonusStage$OnBonusStageRequirementMet.class */
    public static final class OnBonusStageRequirementMet extends SerializableListener<NukeOnBonusStage> implements Listener<BonusStageRequirementMet> {
        private OnBonusStageRequirementMet() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnBonusStageRequirementMet(NukeOnBonusStage nukeOnBonusStage) {
            this.f1759a = nukeOnBonusStage;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(BonusStageRequirementMet bonusStageRequirementMet) {
            ((NukeOnBonusStage) this.f1759a).nuke();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/NukeOnBonusStage$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2166a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2166a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2166a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("NukeOnBonusStage");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new NukeOnBonusStage().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.1f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
