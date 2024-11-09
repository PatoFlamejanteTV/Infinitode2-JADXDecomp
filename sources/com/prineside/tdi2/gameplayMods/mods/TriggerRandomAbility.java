package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.GameStateTick;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/TriggerRandomAbility.class */
public final class TriggerRandomAbility extends GenericGameplayMod implements Listener<GameStateTick> {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2188a = TLog.forClass(TriggerRandomAbility.class);

    /* renamed from: b, reason: collision with root package name */
    private float f2189b = 360.0f;
    private float c = -60.0f;
    private float d = 0.15f;
    private float e = 0.15f;
    private float f = 0.05f;
    private GameSystemProvider g;
    private float h;
    private float i;
    private int j;
    private int k;

    public TriggerRandomAbility() {
        this.maxPower = 3;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2189b);
        output.writeFloat(this.c);
        output.writeFloat(this.d);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        kryo.writeObjectOrNull(output, this.g, GameSystemProvider.class);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
        output.writeVarInt(this.j, true);
        output.writeVarInt(this.k, true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2189b = input.readFloat();
        this.c = input.readFloat();
        this.d = input.readFloat();
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.h = input.readFloat();
        this.i = input.readFloat();
        this.j = input.readVarInt(true);
        this.k = input.readVarInt(true);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.TriggerRandomAbility");
    }

    public final float getMdpsMultiplier() {
        return this.e + (this.f * this.power);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_trigger_random_ability", StringFormatter.timePassed(MathUtils.round(getInterval()), false, false), StringFormatter.compactNumberWithPrecisionTrimZeros(getMdpsMultiplier() * 100.0f, 1, true).toString());
    }

    public final float getInterval() {
        return Math.max(this.f2189b + (this.c * this.power), 10.0f);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        TriggerRandomAbility triggerRandomAbility = new TriggerRandomAbility();
        a(triggerRandomAbility);
        triggerRandomAbility.f2189b = this.f2189b;
        triggerRandomAbility.c = this.c;
        triggerRandomAbility.d = this.d;
        triggerRandomAbility.e = this.e;
        triggerRandomAbility.f = this.f;
        triggerRandomAbility.k = this.k;
        return triggerRandomAbility;
    }

    private void a() {
        this.i = getInterval();
        this.i += this.i * this.g.gameState.randomTriangular() * this.d;
        this.h = 0.0f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.gameState.startingAbilitiesConfiguration == null) {
            return true;
        }
        int i = 0;
        for (int i2 = 0; i2 < gameSystemProvider.gameState.startingAbilitiesConfiguration.slots.length; i2++) {
            if (gameSystemProvider.gameState.startingAbilitiesConfiguration.slots[i2] != null) {
                i++;
            }
        }
        if (i >= this.k) {
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        int i = 0;
        if (gameSystemProvider.gameState.startingAbilitiesConfiguration != null) {
            for (int i2 = 0; i2 < gameSystemProvider.gameState.startingAbilitiesConfiguration.slots.length; i2++) {
                if (gameSystemProvider.gameState.startingAbilitiesConfiguration.slots[i2] != null) {
                    i++;
                }
            }
        }
        if (i >= this.k) {
            return null;
        }
        int i3 = this.k;
        return () -> {
            return Game.i.localeManager.i18n.format("gpmod_precondition_trigger_random_ability", Integer.valueOf(i3));
        };
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(TriggerRandomAbility.class, str);
        if (activeModFromSource == null) {
            this.g = gameSystemProvider;
            gameSystemProvider.events.getListeners(GameStateTick.class).addStateAffecting(this).setDescription("Triggers random ability if it is time to");
            a();
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final TriggerRandomAbility applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2189b = jsonValue.getFloat("baseInterval", this.f2189b);
        this.c = jsonValue.getFloat("intervalPerPower", this.c);
        this.d = jsonValue.getFloat("intervalRandomDeviation", this.d);
        this.e = jsonValue.getFloat("mdpsMultiplier", this.e);
        this.f = jsonValue.getFloat("mdpsMultiplierPerPower", this.f);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(GameStateTick gameStateTick) {
        float f;
        float f2;
        if (this.g.gameState.startingAbilitiesConfiguration == null) {
            return;
        }
        this.h += gameStateTick.getDeltaTime();
        if (this.h > this.i) {
            this.j++;
            RandomXS128 modRandom = this.g.gameplayMod.getModRandom(86862 + this.j);
            f2188a.i("triggering after " + this.h, new Object[0]);
            Array array = new Array(true, 1, AbilityType.class);
            for (int i = 0; i < this.g.gameState.startingAbilitiesConfiguration.slots.length; i++) {
                AbilityType abilityType = this.g.gameState.startingAbilitiesConfiguration.slots[i];
                if (abilityType != null) {
                    array.add(abilityType);
                }
            }
            if (array.size > 0) {
                AbilityType abilityType2 = (AbilityType) array.get(modRandom.nextInt(array.size));
                boolean z = true;
                boolean z2 = false;
                int i2 = 0;
                while (true) {
                    if (i2 >= this.g.ability.activeAbilities.size) {
                        break;
                    }
                    if (this.g.ability.activeAbilities.get(i2).getType() != AbilityType.MAGNET) {
                        i2++;
                    } else {
                        z2 = true;
                        break;
                    }
                }
                if (abilityType2 == AbilityType.MAGNET && z2) {
                    AbilityType abilityType3 = (AbilityType) array.get(modRandom.nextInt(array.size));
                    abilityType2 = abilityType3;
                    if (abilityType3 == AbilityType.MAGNET) {
                        z = false;
                    }
                }
                if (z) {
                    Array array2 = new Array(true, 1, Enemy.class);
                    for (int i3 = 0; i3 < this.g.map.spawnedEnemies.size; i3++) {
                        Enemy.EnemyReference enemyReference = this.g.map.spawnedEnemies.items[i3];
                        if (enemyReference.enemy != null) {
                            array2.add(enemyReference.enemy);
                        }
                    }
                    if (array2.size != 0) {
                        Enemy enemy = (Enemy) array2.get(modRandom.nextInt(array2.size));
                        f = enemy.getPosition().x;
                        f2 = enemy.getPosition().y;
                    } else {
                        Array tilesByType = this.g.map.getMap().getTilesByType(SpawnTile.class);
                        SpawnTile spawnTile = (SpawnTile) tilesByType.get(modRandom.nextInt(tilesByType.size));
                        f = spawnTile.center.x;
                        f2 = spawnTile.center.y;
                    }
                    Ability registerConfigureAndStartAbility = this.g.ability.registerConfigureAndStartAbility(abilityType2, (int) f, (int) f2, this.g.damage.getTowersMaxDps() * getMdpsMultiplier());
                    if (registerConfigureAndStartAbility != null) {
                        registerConfigureAndStartAbility.startEffects();
                    }
                }
            }
            a();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/TriggerRandomAbility$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2190a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2190a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2190a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("TriggerRandomAbility");
            int i2 = bonusConfig.getInt("minAbilityTypes", 5);
            int i3 = i2;
            if (i2 <= 0) {
                i3 = 1;
            }
            TriggerRandomAbility applyConfig = new TriggerRandomAbility().applyConfig(bonusConfig);
            applyConfig.k = i3;
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(applyConfig, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.7f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
