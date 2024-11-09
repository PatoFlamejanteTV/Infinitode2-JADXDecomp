package com.prineside.tdi2.systems;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.actions.ReRollBonusesAction;
import com.prineside.tdi2.actions.SelectGameplayBonusAction;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BonusPointsUpdate;
import com.prineside.tdi2.events.game.BonusSelect;
import com.prineside.tdi2.events.game.BonusStageRequirementMet;
import com.prineside.tdi2.events.game.BonusStagesConfigSet;
import com.prineside.tdi2.events.game.BonusesReRoll;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.mods.ReceiveGreenPapers;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/BonusSystem.class */
public final class BonusSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2928a = TLog.forClass(BonusSystem.class);
    public static final String GAMEPLAY_MOD_SOURCE_NAME = "BonusSystem";
    public static final int MAX_TECHNICAL_BONUS_STAGES = 2048;
    public static final int FORCED_SELECTION_MAX_FRAME_DELAY = 3;
    public static final int BONUSES_TO_CHOOSE_COUNT = 3;

    /* renamed from: b, reason: collision with root package name */
    private BonusStagesConfig f2929b;
    public boolean additionalBonusToSelectFrom;

    @NAGS
    public boolean autoSelectionOnSingleBonus;
    private int c = 1;
    public IntArray selectedBonuses = new IntArray();
    public IntArray stageReRolls = new IntArray();

    @NAGS
    private final RandomXS128 d = new RandomXS128();
    private Array<BonusStage> e = new Array<>(true, 1, BonusStage.class);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.f2929b, BonusStagesConfig.class);
        output.writeVarInt(this.c, true);
        output.writeBoolean(this.additionalBonusToSelectFrom);
        kryo.writeObject(output, this.selectedBonuses);
        kryo.writeObject(output, this.stageReRolls);
        kryo.writeObject(output, this.e);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2929b = (BonusStagesConfig) kryo.readObjectOrNull(input, BonusStagesConfig.class);
        this.c = input.readVarInt(true);
        this.additionalBonusToSelectFrom = input.readBoolean();
        this.selectedBonuses = (IntArray) kryo.readObject(input, IntArray.class);
        this.stageReRolls = (IntArray) kryo.readObject(input, IntArray.class);
        this.e = (Array) kryo.readObject(input, Array.class);
    }

    public final int getBonusesToChooseCount() {
        if (this.additionalBonusToSelectFrom) {
            return 4;
        }
        return 3;
    }

    public final boolean isAutoSelectionOnSingleBonus() {
        return this.autoSelectionOnSingleBonus;
    }

    public final void setAutoSelectionOnSingleBonus(boolean z) {
        this.autoSelectionOnSingleBonus = z;
    }

    @Null
    public final BonusStage getStageToChooseBonusFor() {
        BonusStage bonusStage;
        if (!isEnabled()) {
            return null;
        }
        int maxTechnicalBonusStages = getMaxTechnicalBonusStages();
        for (int i = 1; i <= maxTechnicalBonusStages && (bonusStage = getBonusStage(i)) != null; i++) {
            if (bonusStage.canSelectBonus()) {
                return bonusStage;
            }
        }
        return null;
    }

    public final int getMaxBonusStages() {
        if (!isEnabled()) {
            return 0;
        }
        return this.f2929b.getMaxStages();
    }

    public final int getMaxTechnicalBonusStages() {
        if (!isEnabled()) {
            return 0;
        }
        int maxStages = this.f2929b.getMaxStages();
        if (maxStages <= 0) {
            return 2048;
        }
        return maxStages;
    }

    public final int getAllTimeReRollCount() {
        return this.stageReRolls.size;
    }

    public final boolean canReRollBonuses(int i) {
        BonusStage bonusStage;
        if (this.f2929b.reRollEnabled) {
            if (this.f2929b.maxReRollsPerStage > 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < this.stageReRolls.size; i3++) {
                    if (this.stageReRolls.items[i3] == i) {
                        i2++;
                        if (i2 == this.f2929b.maxReRollsPerStage) {
                            return false;
                        }
                    }
                }
            }
            if (this.f2929b.maxReRollsAllTime <= 0 || this.stageReRolls.size < this.f2929b.maxReRollsAllTime) {
                return this.e.size <= i - 1 || (bonusStage = this.e.get(i - 1)) == null || a(i, getBonusesToChooseCount(), bonusStage.getProbableBonuses()).size != 0;
            }
            return false;
        }
        return false;
    }

    public final int getActiveBonusesCount() {
        DelayedRemovalArray<GameplayModSystem.ActiveMod> activeMods = this.S.gameplayMod.getActiveMods();
        int i = 0;
        for (int i2 = 0; i2 < activeMods.size; i2++) {
            if (GAMEPLAY_MOD_SOURCE_NAME.equals(activeMods.get(i2).getSource())) {
                i++;
            }
        }
        return i;
    }

    public final boolean isEnabled() {
        return this.f2929b != null;
    }

    public final void addProgressPoints(int i) {
        BonusStage bonusStage;
        int i2 = 0;
        int maxStages = this.f2929b.getMaxStages();
        int i3 = maxStages;
        if (maxStages <= 0) {
            i3 = this.c + 100;
        }
        for (int i4 = this.c; i4 <= i3 && (bonusStage = getBonusStage(i4)) != null; i4++) {
            int i5 = bonusStage.f2931b - bonusStage.c;
            if (i5 != 0) {
                int min = Math.min(i, i5);
                BonusStage.a(bonusStage, min);
                i2 += min;
                i -= min;
                if (bonusStage.f2931b == bonusStage.c) {
                    this.c = Math.min(bonusStage.f2930a + 1, getMaxTechnicalBonusStages());
                    if (getStageToChooseBonusFor() == bonusStage) {
                        a(bonusStage);
                        b(bonusStage);
                    }
                    this.S.events.trigger(new BonusStageRequirementMet(bonusStage));
                }
                if (i == 0) {
                    break;
                }
            }
        }
        if (i2 != 0) {
            this.S.events.trigger(new BonusPointsUpdate());
        }
    }

    private void a(BonusStage bonusStage) {
        bonusStage.e = this.f2929b.getProbableBonuses(bonusStage.f2930a, this.S);
        f2928a.i("setProbableBonuses - called for stage " + bonusStage.f2930a + " (" + bonusStage.e.size + " items)", new Object[0]);
        for (int i = 0; i < bonusStage.e.size; i++) {
            ProbableBonus probableBonus = (ProbableBonus) bonusStage.e.get(i);
            f2928a.i(probableBonus.getProbability() + ": " + probableBonus.getBonus().getId(), new Object[0]);
        }
    }

    private void b(BonusStage bonusStage) {
        bonusStage.f = a(bonusStage.f2930a, getBonusesToChooseCount(), bonusStage.getProbableBonuses());
        for (int i = 0; i < bonusStage.f.size; i++) {
            GameplayMod gameplayMod = (GameplayMod) bonusStage.f.get(i);
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= bonusStage.e.size) {
                    break;
                }
                if (((ProbableBonus) bonusStage.e.get(i2)).getBonus() != gameplayMod) {
                    i2++;
                } else {
                    z = true;
                    bonusStage.e.removeIndex(i2);
                    break;
                }
            }
            if (!z) {
                f2928a.i("can't remove " + gameplayMod.getId() + " from stage.probableBonuses - exists in bonusesToChooseFrom but not in probableBonuses", new Object[0]);
            }
        }
        boolean z2 = false;
        int i3 = 0;
        while (true) {
            if (i3 >= bonusStage.f.size) {
                break;
            }
            if (((GameplayMod) bonusStage.f.get(i3)).getNotSatisfiedPreconditions(this.S) != null) {
                i3++;
            } else {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            ReceiveGreenPapers provideFallback = ReceiveGreenPapers.BonusProvider.getInstance().provideFallback(getStagesConfig(), this.S.gameplayMod.getActiveMods());
            provideFallback.configure(this.S);
            bonusStage.f.add(provideFallback);
        }
        f2928a.i("setBonusesToChooseFrom - called for stage " + bonusStage.f2930a + " (" + bonusStage.f.size + " items), probableBonuses: " + bonusStage.e.size + ", random state " + getPreparedRandom(bonusStage.f2930a).getState(0) + ":", new Object[0]);
        for (int i4 = 0; i4 < bonusStage.f.size; i4++) {
            f2928a.i("- " + ((GameplayMod) bonusStage.f.get(i4)).getId(), new Object[0]);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(this));
    }

    public final void setStagesConfig(BonusStagesConfig bonusStagesConfig) {
        f2928a.i("setStagesConfig " + bonusStagesConfig, new Object[0]);
        BonusStagesConfig cpy = bonusStagesConfig.cpy();
        if (cpy.seed == -2) {
            if (this.S.gameState.dailyQuestLevel != null) {
                cpy.seed = this.S.gameState.dailyQuestLevel.forDateTimestamp;
                f2928a.i("setting bonus stages seed to daily quest timestamp: " + this.S.gameState.dailyQuestLevel.forDateTimestamp, new Object[0]);
            } else {
                f2928a.i("stage config seed to SEED_TAKE_FROM_DAILY_QUEST but not in a daily quest", new Object[0]);
                cpy.seed = -1;
            }
        }
        if (cpy.seed == -1) {
            cpy.seed = (int) this.S.gameState.getSeed();
            f2928a.i("setting bonus stages seed from main seed: " + cpy.seed, new Object[0]);
        }
        if (cpy.seed == 0) {
            cpy.seed = this.S.gameState.randomIntIndependent(Integer.MAX_VALUE, this.S.gameState.gameStartTimestamp);
            f2928a.i("setting random bonus stages seed from game start timestamp: " + cpy.seed, new Object[0]);
        }
        this.f2929b = cpy;
        this.e.clear();
        this.S.events.trigger(new BonusStagesConfigSet());
    }

    @Null
    public final BonusStage getBonusStage(int i) {
        if (i > getMaxTechnicalBonusStages()) {
            return null;
        }
        BonusStage bonusStage = null;
        if (this.e.size > i - 1) {
            bonusStage = this.e.get(i - 1);
        } else {
            this.e.setSize(i);
        }
        if (bonusStage == null) {
            bonusStage = new BonusStage(i, this.f2929b.getStageRequirement(i));
            this.e.set(i - 1, bonusStage);
        }
        return bonusStage;
    }

    public final BonusStagesConfig getStagesConfig() {
        return this.f2929b;
    }

    public final void reRollBonusesAction() {
        BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
        if (stageToChooseBonusFor != null) {
            if (!canReRollBonuses(stageToChooseBonusFor.f2930a)) {
                f2928a.i("reRollBonusesAction skipped - disabled by config", new Object[0]);
                return;
            }
            int reRollPrice = this.f2929b.getReRollPrice(stageToChooseBonusFor.f2930a, this.S);
            if (this.S.gameState.getMoney() >= reRollPrice) {
                this.S.gameState.pushActionNextUpdate(new ReRollBonusesAction());
                return;
            } else {
                f2928a.i("reRollBonusesAction skipped - not enough coins (" + this.S.gameState.getMoney() + " / " + reRollPrice + ")", new Object[0]);
                return;
            }
        }
        f2928a.i("reRollBonusesAction skipped - bonus selection not available now", new Object[0]);
    }

    public final void selectBonusAction(int i) {
        BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
        if (stageToChooseBonusFor != null) {
            if (i >= 0 && i < stageToChooseBonusFor.getBonusesToChooseFrom().size) {
                GameplayMod gameplayMod = stageToChooseBonusFor.getBonusesToChooseFrom().get(i);
                ObjectSupplier<CharSequence> notSatisfiedPreconditions = gameplayMod.getNotSatisfiedPreconditions(this.S);
                if (notSatisfiedPreconditions != null) {
                    if (Config.isHeadless()) {
                        f2928a.i("selectBonusAction skipped - bonus preconditions not satisfied (" + gameplayMod + ")", new Object[0]);
                        return;
                    } else {
                        f2928a.i("selectBonusAction skipped - bonus preconditions not satisfied (" + gameplayMod + "): " + ((Object) notSatisfiedPreconditions.get()), new Object[0]);
                        return;
                    }
                }
                this.S.gameState.pushActionNextUpdate(new SelectGameplayBonusAction(stageToChooseBonusFor.f2930a, i));
                return;
            }
            f2928a.i("invalid bonus idx " + i, new Object[0]);
            return;
        }
        f2928a.i("selectBonusAction skipped - bonus selection not available now", new Object[0]);
    }

    public final void setSelectedBonus(int i, int i2) {
        BonusStage bonusStage = getBonusStage(i);
        if (bonusStage != null && bonusStage.canSelectBonus()) {
            GameplayMod gameplayMod = bonusStage.getBonusesToChooseFrom().get(i2);
            f2928a.i("enabling bonus " + gameplayMod.getId() + " on stage " + bonusStage, new Object[0]);
            ObjectSupplier<CharSequence> notSatisfiedPreconditions = gameplayMod.getNotSatisfiedPreconditions(this.S);
            if (notSatisfiedPreconditions != null) {
                if (!Config.isHeadless()) {
                    f2928a.e("bonus selection failed - preconditions not satisfied " + gameplayMod.getId() + ": " + ((Object) notSatisfiedPreconditions.get()), new Object[0]);
                    return;
                } else {
                    f2928a.e("bonus selection failed - preconditions not satisfied " + gameplayMod.getId(), new Object[0]);
                    return;
                }
            }
            while (this.selectedBonuses.size <= i) {
                this.selectedBonuses.add(0);
            }
            this.selectedBonuses.set(i - 1, i2);
            this.S.gameplayMod.activateMod(gameplayMod, GAMEPLAY_MOD_SOURCE_NAME);
            f2928a.i("enabled bonus " + gameplayMod.getId() + " on stage " + bonusStage, new Object[0]);
            bonusStage.d = Integer.valueOf(i2);
            bonusStage.f = null;
            bonusStage.e = null;
            this.S.events.trigger(new BonusSelect(i));
            BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
            if (stageToChooseBonusFor != null) {
                a(stageToChooseBonusFor);
                b(stageToChooseBonusFor);
                return;
            }
            return;
        }
        f2928a.e("bonus selection not available for stage " + i, new Object[0]);
    }

    public final void reRollBonuses() {
        BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
        if (stageToChooseBonusFor == null) {
            throw new IllegalStateException("No stage to re-roll for");
        }
        this.stageReRolls.add(stageToChooseBonusFor.f2930a);
        f2928a.i("reRollBonuses called", new Object[0]);
        b(stageToChooseBonusFor);
        stageToChooseBonusFor.setAvailableOnFrame(this.S.gameState.updateNumber);
        this.S.events.trigger(new BonusesReRoll());
    }

    private void a() {
        this.d.setState(this.d.nextLong(), this.d.nextLong());
    }

    public final RandomXS128 getPreparedRandom(int i) {
        this.d.setSeed(this.f2929b.seed);
        for (int i2 = 0; i2 < i; i2++) {
            this.d.nextLong();
        }
        if (this.f2929b.selectedBonusAffectsRandom) {
            for (int i3 = 0; i3 < Math.min(this.selectedBonuses.size, i); i3++) {
                a();
                int i4 = this.selectedBonuses.get(i3);
                for (int i5 = 0; i5 < i4; i5++) {
                    a();
                }
            }
        }
        a();
        if (this.f2929b.chainReRoll) {
            for (int i6 = 0; i6 < this.stageReRolls.size; i6++) {
                if (this.stageReRolls.get(i6) <= i) {
                    a();
                }
            }
        } else {
            for (int i7 = 0; i7 < this.stageReRolls.size; i7++) {
                if (this.stageReRolls.get(i7) == i) {
                    a();
                }
            }
        }
        return this.d;
    }

    public final void resetState() {
        this.selectedBonuses.clear();
        this.stageReRolls.clear();
    }

    public final int getCurrentlyProgressingStage() {
        return this.c;
    }

    public final int getCurrentVisualProgressStageNumber() {
        BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
        if (stageToChooseBonusFor == null) {
            return this.c;
        }
        return stageToChooseBonusFor.f2930a;
    }

    private Array<GameplayMod> a(int i, int i2, Array<ProbableBonus> array) {
        Array array2 = new Array(array);
        RandomXS128 preparedRandom = getPreparedRandom(i);
        DelayedRemovalArray delayedRemovalArray = new DelayedRemovalArray(true, i2, GameplayMod.class);
        long j = 0;
        for (int i3 = 0; i3 < array2.size; i3++) {
            j += ((ProbableBonus) array2.get(i3)).getProbability();
        }
        if (j == 0) {
            return delayedRemovalArray;
        }
        for (int i4 = 0; i4 < i2 && array2.size != 0; i4++) {
            long nextLong = preparedRandom.nextLong(j);
            long j2 = 0;
            int i5 = 0;
            while (true) {
                if (i5 < array2.size) {
                    ProbableBonus probableBonus = (ProbableBonus) array2.get(i5);
                    if (j2 + probableBonus.getProbability() >= nextLong) {
                        GameplayMod cpy = probableBonus.getBonus().cpy();
                        cpy.configure(this.S);
                        delayedRemovalArray.add(cpy);
                        array2.removeIndex(i5);
                        j -= probableBonus.getProbability();
                        break;
                    }
                    j2 += probableBonus.getProbability();
                    i5++;
                }
            }
        }
        if (getStagesConfig().replaceBonusesWithNotSatisfiedPreconditions) {
            IntArray intArray = new IntArray();
            for (int i6 = 0; i6 < delayedRemovalArray.size; i6++) {
                GameplayMod gameplayMod = (GameplayMod) delayedRemovalArray.get(i6);
                if (gameplayMod.getNotSatisfiedPreconditions(this.S) != null) {
                    f2928a.i("variant " + i6 + " (" + gameplayMod.getId() + ") is not satisfied, trying to replace", new Object[0]);
                    boolean z = false;
                    while (true) {
                        if (array2.size == 0) {
                            break;
                        }
                        long nextLong2 = preparedRandom.nextLong(j);
                        long j3 = 0;
                        for (int i7 = 0; i7 < array2.size; i7++) {
                            ProbableBonus probableBonus2 = (ProbableBonus) array2.get(i7);
                            if (j3 + probableBonus2.getProbability() >= nextLong2) {
                                GameplayMod bonus = probableBonus2.getBonus();
                                if (bonus.getNotSatisfiedPreconditions(this.S) != null) {
                                    array2.removeIndex(i7);
                                    j -= probableBonus2.getProbability();
                                } else {
                                    bonus.configure(this.S);
                                    delayedRemovalArray.set(i6, bonus);
                                    array2.removeIndex(i7);
                                    j -= probableBonus2.getProbability();
                                    z = true;
                                    f2928a.i("replacing variant " + gameplayMod.getId() + " with " + bonus.getId() + " due to unsatisfied preconditions", new Object[0]);
                                    bonus.setReplacesUnsatisfiedMod(gameplayMod);
                                    break;
                                }
                            } else {
                                j3 += probableBonus2.getProbability();
                            }
                        }
                    }
                    if (!z) {
                        f2928a.i("removing variant index " + i6 + " (" + gameplayMod.getId() + ") due to unsatisfied preconditions and no replacement available", new Object[0]);
                        intArray.add(i6);
                    }
                }
            }
            delayedRemovalArray.begin();
            for (int i8 = 0; i8 < intArray.size; i8++) {
                delayedRemovalArray.removeIndex(intArray.get(i8));
            }
            delayedRemovalArray.end();
        }
        return delayedRemovalArray;
    }

    public final int getCurrentVisualProgressPoints() {
        BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
        if (stageToChooseBonusFor == null) {
            BonusStage bonusStage = getBonusStage(this.c);
            if (bonusStage == null) {
                return 0;
            }
            return bonusStage.c;
        }
        return stageToChooseBonusFor.c;
    }

    public final int getNextStagePointsRequirement() {
        BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
        if (stageToChooseBonusFor == null) {
            BonusStage bonusStage = getBonusStage(this.c);
            if (bonusStage == null) {
                return 0;
            }
            return bonusStage.f2931b;
        }
        return stageToChooseBonusFor.f2931b;
    }

    public final boolean bonusSelectionAvailable() {
        return isEnabled() && getStageToChooseBonusFor() != null;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        if (isEnabled()) {
            StateSystem.ActionsArray currentUpdateActions = this.S.gameState.getCurrentUpdateActions();
            if (currentUpdateActions != null) {
                for (int i = 0; i < currentUpdateActions.size; i++) {
                    Action action = currentUpdateActions.actions[i];
                    if (action.getType() != ActionType.SGB) {
                        if (action.getType() == ActionType.RRB) {
                            BonusStage stageToChooseBonusFor = getStageToChooseBonusFor();
                            if (stageToChooseBonusFor != null) {
                                if (!canReRollBonuses(stageToChooseBonusFor.f2930a)) {
                                    f2928a.i("reRollBonusesAction ignored - disabled by config", new Object[0]);
                                } else {
                                    int reRollPrice = this.f2929b.getReRollPrice(stageToChooseBonusFor.f2930a, this.S);
                                    if (this.S.gameState.removeMoney(reRollPrice)) {
                                        reRollBonuses();
                                    } else {
                                        f2928a.i("reRollBonusesAction ignored - not enough coins (" + this.S.gameState.getMoney() + " / " + reRollPrice + ")", new Object[0]);
                                    }
                                }
                            } else {
                                f2928a.i("reRollBonusesAction ignored - bonus selection not available now", new Object[0]);
                            }
                        }
                    } else {
                        SelectGameplayBonusAction selectGameplayBonusAction = (SelectGameplayBonusAction) action;
                        BonusStage stageToChooseBonusFor2 = getStageToChooseBonusFor();
                        if (stageToChooseBonusFor2 != null) {
                            Array<GameplayMod> bonusesToChooseFrom = stageToChooseBonusFor2.getBonusesToChooseFrom();
                            if (selectGameplayBonusAction.bonusIdx >= 0 && selectGameplayBonusAction.bonusIdx < bonusesToChooseFrom.size) {
                                setSelectedBonus(stageToChooseBonusFor2.f2930a, selectGameplayBonusAction.bonusIdx);
                            }
                        }
                    }
                }
            }
            BonusStage stageToChooseBonusFor3 = getStageToChooseBonusFor();
            if (stageToChooseBonusFor3 != null) {
                if (stageToChooseBonusFor3.getAvailableOnFrame() == -1) {
                    stageToChooseBonusFor3.setAvailableOnFrame(this.S.gameState.updateNumber);
                }
                if (this.f2929b.forceImmediateSelection && this.S.gameState.updateNumber - stageToChooseBonusFor3.getAvailableOnFrame() >= 3) {
                    int fairInt = FastRandom.getFairInt(stageToChooseBonusFor3.getBonusesToChooseFrom().size);
                    setSelectedBonus(stageToChooseBonusFor3.f2930a, fairInt);
                    f2928a.e("player has not selected a bonus but frame has continued, selecting random: " + fairInt, new Object[0]);
                    f2928a.e("frame " + this.S.gameState.updateNumber + ", bonus stage " + stageToChooseBonusFor3.getNumber(), new Object[0]);
                    if (this.S._gameUi != null) {
                        this.S._gameUi.gameplayBonusesOverlay.hide();
                    }
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Bonus";
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        super.dispose();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/BonusSystem$BonusStage.class */
    public static final class BonusStage implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private int f2930a;

        /* renamed from: b, reason: collision with root package name */
        private int f2931b;
        private int c;

        @Null
        private Array<ProbableBonus> e;

        @Null
        private Array<GameplayMod> f;

        @Null
        private Integer d = null;
        private int g = -1;

        static /* synthetic */ int a(BonusStage bonusStage, int i) {
            int i2 = bonusStage.c + i;
            bonusStage.c = i2;
            return i2;
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeVarInt(this.f2930a, true);
            output.writeVarInt(this.f2931b, true);
            output.writeVarInt(this.c, true);
            kryo.writeObjectOrNull(output, this.d, Integer.class);
            kryo.writeClassAndObject(output, this.e);
            kryo.writeClassAndObject(output, this.f);
            output.writeInt(this.g);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2930a = input.readVarInt(true);
            this.f2931b = input.readVarInt(true);
            this.c = input.readVarInt(true);
            this.d = (Integer) kryo.readObjectOrNull(input, Integer.class);
            this.e = (Array) kryo.readClassAndObject(input);
            this.f = (Array) kryo.readClassAndObject(input);
            this.g = input.readInt();
        }

        private BonusStage() {
        }

        public BonusStage(int i, int i2) {
            Preconditions.checkArgument(i > 0, "number must be > 0, %s given", i);
            Preconditions.checkArgument(i2 > 0, "requirement must be > 0, %s given. Stage number: %s", i2, i);
            this.f2930a = i;
            this.f2931b = i2;
        }

        public final int getAvailableOnFrame() {
            return this.g;
        }

        public final void setAvailableOnFrame(int i) {
            this.g = i;
        }

        public final Array<GameplayMod> getBonusesToChooseFrom() {
            if (this.f == null) {
                throw new IllegalStateException("Bonuses not set yet " + this.c + " / " + this.f2931b);
            }
            return this.f;
        }

        public final Array<ProbableBonus> getProbableBonuses() {
            if (this.e == null) {
                throw new IllegalStateException("probableBonuses not set yet");
            }
            return this.e;
        }

        public final int getNumber() {
            return this.f2930a;
        }

        public final boolean canSelectBonus() {
            return this.c == this.f2931b && this.d == null;
        }

        public final boolean isBonusSelected() {
            return this.d != null;
        }

        public final int getSelectedBonusIdx() {
            if (this.d == null) {
                return -1;
            }
            return this.d.intValue();
        }

        public final int getPointsRequirement() {
            return this.f2931b;
        }

        public final int getPoints() {
            return this.c;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/BonusSystem$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<BonusSystem> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(BonusSystem bonusSystem) {
            this.f1759a = bonusSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            if (((BonusSystem) this.f1759a).isEnabled()) {
                Enemy enemy = enemyDie.getLastDamage().getEnemy();
                if (!enemy.notAffectsWaveKillCounter.isTrue() && !enemy.hasBuffsByType(BuffType.NO_BONUS_SYSTEM_POINTS)) {
                    ((BonusSystem) this.f1759a).addProgressPoints(1);
                }
            }
        }
    }
}
