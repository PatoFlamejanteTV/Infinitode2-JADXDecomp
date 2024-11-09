package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/BoostExistingEnemiesWithLoot.class */
public final class BoostExistingEnemiesWithLoot extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private float f2103a = 3.0f;

    /* renamed from: b, reason: collision with root package name */
    private float f2104b = 0.5f;
    private int c = 20;
    private int d;

    public BoostExistingEnemiesWithLoot() {
        this.maxPower = 3;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2103a);
        output.writeFloat(this.f2104b);
        output.writeInt(this.c);
        output.writeVarInt(this.d, true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2103a = input.readFloat();
        this.f2104b = input.readFloat();
        this.c = input.readInt();
        this.d = input.readVarInt(true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getAdditionalCategory() {
        return GameplayModCategory.ECONOMICS;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.BoostExistingEnemiesWithLoot");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_boost_existing_enemies_with_loot", Integer.valueOf(this.c), StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2103a, 1, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2104b * 100.0f, 1, true).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final BoostExistingEnemiesWithLoot cpy() {
        BoostExistingEnemiesWithLoot boostExistingEnemiesWithLoot = new BoostExistingEnemiesWithLoot();
        a(boostExistingEnemiesWithLoot);
        boostExistingEnemiesWithLoot.f2103a = this.f2103a;
        boostExistingEnemiesWithLoot.f2104b = this.f2104b;
        boostExistingEnemiesWithLoot.d = this.d;
        return boostExistingEnemiesWithLoot;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.spawnedEnemies.size >= this.d) {
            return null;
        }
        int i = this.d;
        return () -> {
            return Game.i.localeManager.i18n.format("gpmod_precondition_boost_existing_enemies", Integer.valueOf(i));
        };
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        int i = 0;
        for (int i2 = 0; i2 < gameSystemProvider.map.spawnedEnemies.size; i2++) {
            Enemy enemy = gameSystemProvider.map.spawnedEnemies.get(i2).enemy;
            if (enemy != null) {
                enemy.bounty *= this.f2103a;
                if (this.f2104b > 0.0f) {
                    enemy.setHealth(Math.min(enemy.getHealth() + (enemy.maxHealth * this.f2104b), enemy.maxHealth));
                }
                gameSystemProvider.loot.forceFillWithLoot(enemy);
                i++;
                if (i == this.c) {
                    break;
                }
            }
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(BoostExistingEnemiesWithLoot.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final BoostExistingEnemiesWithLoot applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2103a = jsonValue.getFloat("coinMultiplier", this.f2103a);
        this.f2104b = jsonValue.getFloat("hpRestore", this.f2104b);
        this.c = jsonValue.getInt("enemyCount", this.c);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/BoostExistingEnemiesWithLoot$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2105a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2105a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2105a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("BoostExistingEnemiesWithLoot");
            BoostExistingEnemiesWithLoot applyConfig = new BoostExistingEnemiesWithLoot().applyConfig(bonusConfig);
            applyConfig.d = bonusConfig.getInt("minEnemyCount", 5);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(applyConfig, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.5f).setPowerUpProbabilityMultiplier(0.9f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
