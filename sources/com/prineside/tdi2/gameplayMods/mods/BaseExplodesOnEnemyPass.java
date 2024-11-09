package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.explosions.GenericExplosion;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/BaseExplodesOnEnemyPass.class */
public final class BaseExplodesOnEnemyPass extends GenericGameplayMod implements Listener<EnemyReachTarget> {
    public float baseCooldown;
    public float cooldownPerPower;
    public float baseDamage;
    public float damagePerPower;
    public float baseRange;
    public float rangePerPower;

    /* renamed from: a, reason: collision with root package name */
    @Null
    private GameSystemProvider f2100a;

    /* renamed from: b, reason: collision with root package name */
    private float f2101b;

    public BaseExplodesOnEnemyPass() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.baseCooldown = 60.0f;
        this.cooldownPerPower = 0.0f;
        this.baseDamage = 150.0f;
        this.damagePerPower = 50.0f;
        this.baseRange = 2.5f;
        this.rangePerPower = 0.5f;
        this.f2101b = -1.0f;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.baseCooldown);
        output.writeFloat(this.cooldownPerPower);
        output.writeFloat(this.baseDamage);
        output.writeFloat(this.damagePerPower);
        output.writeFloat(this.baseRange);
        output.writeFloat(this.rangePerPower);
        kryo.writeObjectOrNull(output, this.f2100a, GameSystemProvider.class);
        output.writeFloat(this.f2101b);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseCooldown = input.readFloat();
        this.cooldownPerPower = input.readFloat();
        this.baseDamage = input.readFloat();
        this.damagePerPower = input.readFloat();
        this.baseRange = input.readFloat();
        this.rangePerPower = input.readFloat();
        this.f2100a = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.f2101b = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.DEFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getAdditionalCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    public final float getDamage() {
        return this.baseDamage + (this.damagePerPower * this.power);
    }

    public final int getCooldown() {
        return MathUtils.round(this.baseCooldown + (this.cooldownPerPower * this.power));
    }

    public final float getRange() {
        return this.baseRange + (this.rangePerPower * this.power);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.BaseExplodesOnEnemyPass");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_base_explodes_on_enemy_pass", Float.valueOf(getRange()), StringFormatter.compactNumberWithPrecisionTrimZeros(getDamage(), 1, true).toString(), StringFormatter.timePassed(getCooldown(), false, false));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final BaseExplodesOnEnemyPass cpy() {
        BaseExplodesOnEnemyPass baseExplodesOnEnemyPass = new BaseExplodesOnEnemyPass();
        a(baseExplodesOnEnemyPass);
        baseExplodesOnEnemyPass.baseCooldown = this.baseCooldown;
        baseExplodesOnEnemyPass.cooldownPerPower = this.cooldownPerPower;
        baseExplodesOnEnemyPass.baseDamage = this.baseDamage;
        baseExplodesOnEnemyPass.damagePerPower = this.damagePerPower;
        baseExplodesOnEnemyPass.baseRange = this.baseRange;
        baseExplodesOnEnemyPass.rangePerPower = this.rangePerPower;
        return baseExplodesOnEnemyPass;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(BaseExplodesOnEnemyPass.class, str);
        if (activeModFromSource == null) {
            this.f2100a = gameSystemProvider;
            gameSystemProvider.events.getListeners(EnemyReachTarget.class).addStateAffecting(this);
            return true;
        }
        activeModFromSource.getMod().setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final BaseExplodesOnEnemyPass applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.baseCooldown = jsonValue.getFloat("baseCooldown", this.baseCooldown);
        this.cooldownPerPower = jsonValue.getFloat("cooldownPerPower", this.cooldownPerPower);
        this.baseDamage = jsonValue.getFloat("baseDamage", this.baseDamage);
        this.damagePerPower = jsonValue.getFloat("damagePerPower", this.damagePerPower);
        this.baseRange = jsonValue.getFloat("baseRange", this.baseRange);
        this.rangePerPower = jsonValue.getFloat("rangePerPower", this.rangePerPower);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(EnemyReachTarget enemyReachTarget) {
        if (this.f2101b <= 0.0f || this.f2100a.statistics.getStatistic(StatisticsType.PT) - this.f2101b >= getCooldown()) {
            Tile currentTile = enemyReachTarget.getEnemy().getCurrentTile();
            if (currentTile instanceof TargetTile) {
                GenericExplosion obtain = this.f2100a.explosion.F.GENERIC.obtain();
                obtain.setup(null, currentTile.center.x, currentTile.center.y, enemyReachTarget.getEnemy().maxHealth * getDamage() * 0.01f, getRange(), 0, 0.0f, 0.0f, ((TargetTile) currentTile).getCoreColor(), null);
                this.f2100a.explosion.register(obtain);
                obtain.explode();
                this.f2101b = (float) this.f2100a.statistics.getStatistic(StatisticsType.PT);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/BaseExplodesOnEnemyPass$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2102a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2102a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2102a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("BaseExplodesOnEnemyPass");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new BaseExplodesOnEnemyPass().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}
