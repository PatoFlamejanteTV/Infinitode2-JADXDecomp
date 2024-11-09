package com.prineside.tdi2.systems;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.actions.UseAbilityAction;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.events.game.AbilitiesConfigChange;
import com.prineside.tdi2.events.game.AbilityApply;
import com.prineside.tdi2.events.game.AbilityStart;
import com.prineside.tdi2.events.game.AbilityUseStart;
import com.prineside.tdi2.events.game.AbilityUseStop;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/AbilitySystem.class */
public final class AbilitySystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2923a = TLog.forClass(AbilitySystem.class);
    public AbilitySelectionOverlay.SelectedAbilitiesConfiguration abilitiesConfiguration;
    private float c;

    @NAGS
    private AbilityType e;
    public DelayedRemovalArray<Ability> activeAbilities = new DelayedRemovalArray<>(false, 1, Ability.class);

    /* renamed from: b, reason: collision with root package name */
    private int f2924b = 0;
    public int[] abilitiesUsed = {0, 0, 0, 0, 0, 0};

    @NAGS
    private final InputProcessor d = new InputAdapter() { // from class: com.prineside.tdi2.systems.AbilitySystem.1
        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchUp(int i, int i2, int i3, int i4) {
            if (AbilitySystem.this.S._input == null) {
                return false;
            }
            Vector2 vector2 = new Vector2(i, i2);
            AbilitySystem.this.S._input.getCameraController().screenToMap(vector2);
            if (AbilitySystem.this.e != null) {
                AbilitySystem.this.a(AbilitySystem.this.e, (int) vector2.x, (int) vector2.y);
            }
            AbilitySystem.this.cancelUsingAbility();
            return false;
        }
    };

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.activeAbilities);
        kryo.writeObjectOrNull(output, this.abilitiesConfiguration, AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
        output.writeVarInt(this.f2924b, true);
        output.writeFloat(this.c);
        kryo.writeObject(output, this.abilitiesUsed);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.activeAbilities = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.abilitiesConfiguration = (AbilitySelectionOverlay.SelectedAbilitiesConfiguration) kryo.readObjectOrNull(input, AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
        this.f2924b = input.readVarInt(true);
        this.c = input.readFloat();
        this.abilitiesUsed = (int[]) kryo.readObject(input, int[].class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.abilitiesConfiguration = new AbilitySelectionOverlay.SelectedAbilitiesConfiguration(this.S.gameState.startingAbilitiesConfiguration);
        if (this.S.map.getMap().getTargetTileOrThrow().isDisableAbilities()) {
            for (int i = 0; i < getAbilitySlotCount(); i++) {
                AbilityType abilityType = this.abilitiesConfiguration.slots[i];
                this.abilitiesConfiguration.counts[i] = 0;
                if (abilityType != null) {
                    this.abilitiesConfiguration.counts[i] = this.S.gameValue.getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType));
                }
            }
        }
        if (!this.S.CFG.headless) {
            a();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.ABILITY_DRAW_BATCH_ADDITIVE, true, (batch, f, f2, f3) -> {
            drawBatchAdditive(batch, f2);
        }).setName("Ability-drawBatchAdditive"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.ABILITY_DRAW_BATCH, false, (batch2, f4, f5, f6) -> {
            draw(batch2, f5);
        }).setName("Ability-drawBatch"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        int intValue;
        super.postSetup();
        for (int i = 0; i < this.abilitiesConfiguration.slots.length; i++) {
            AbilityType abilityType = this.abilitiesConfiguration.slots[i];
            if (abilityType != null && this.abilitiesConfiguration.counts[i] > (intValue = this.S.gameValue.getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType)))) {
                this.abilitiesConfiguration.counts[i] = intValue;
            }
        }
        b();
    }

    public final int getAbilitySlotCount() {
        return 6;
    }

    private void b() {
        this.S.events.trigger(new AbilitiesConfigChange());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AbilityType abilityType, int i, int i2) {
        this.S.gameState.pushActionNextUpdate(new UseAbilityAction(abilityType, i, i2));
    }

    public final AbilityType getUiCurrentlyUsingAbility() {
        return this.e;
    }

    public final void startUsingAbility(AbilityType abilityType) {
        if (this.e != null) {
            cancelUsingAbility();
        }
        int slot = this.abilitiesConfiguration.getSlot(abilityType);
        if (slot == -1 || getAvailableAbilities(slot) <= 0) {
            return;
        }
        this.S._input.setupInputMultiplexer(true, true, false).addProcessor(this.d);
        this.e = abilityType;
        this.S.events.trigger(new AbilityUseStart());
    }

    public final void cancelUsingAbility() {
        if (this.e != null) {
            this.e = null;
            this.S.events.trigger(new AbilityUseStop());
        }
        this.S._input.enableAllInput();
    }

    public final int getEnergy() {
        return this.f2924b;
    }

    public final float getNextEnergyGenerationTime() {
        return this.c;
    }

    public final int getAvailableAbilitiesByType(AbilityType abilityType) {
        for (int i = 0; i < this.abilitiesConfiguration.slots.length; i++) {
            if (this.abilitiesConfiguration.slots[i] == abilityType) {
                return getAvailableAbilities(i);
            }
        }
        return 0;
    }

    public final int getAvailableAbilities(int i) {
        return this.abilitiesConfiguration.counts[i];
    }

    public final void addAbilityCharges(int i, int i2) {
        int[] iArr = this.abilitiesConfiguration.counts;
        iArr[i] = iArr[i] + i2;
        b();
    }

    public final Ability registerConfigureAndStartAbility(AbilityType abilityType, int i, int i2, double d) {
        Ability create = Game.i.abilityManager.getFactory(abilityType).create();
        registerAndConfigure(create, i, i2, d);
        return startAbility(create);
    }

    public final Ability startAbility(Ability ability) {
        if (!ability.isRegistered()) {
            throw new IllegalArgumentException("Ability must be registered and configured first");
        }
        this.S.events.trigger(new AbilityStart(ability));
        if (ability.start()) {
            this.activeAbilities.add(ability);
            return ability;
        }
        return null;
    }

    public final void registerAndConfigure(Ability ability, int i, int i2, double d) {
        ability.setRegistered(this.S);
        ability.configure(i, i2, d);
    }

    public final int getEnergyCost(AbilityType abilityType) {
        return this.S.gameValue.getIntValue(Game.i.abilityManager.getEnergyCostGameValueType(abilityType));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        StateSystem.ActionsArray currentUpdateActions = this.S.gameState.getCurrentUpdateActions();
        if (currentUpdateActions != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                Action action = currentUpdateActions.actions[i];
                if (action.getType() == ActionType.UA) {
                    UseAbilityAction useAbilityAction = (UseAbilityAction) action;
                    int slot = this.abilitiesConfiguration.getSlot(useAbilityAction.abilityType);
                    if (slot != -1) {
                        if (getAvailableAbilities(slot) > 0) {
                            int energyCost = getEnergyCost(useAbilityAction.abilityType);
                            if (this.f2924b >= energyCost) {
                                Ability registerConfigureAndStartAbility = registerConfigureAndStartAbility(useAbilityAction.abilityType, useAbilityAction.x, useAbilityAction.y, this.S.damage.getTowersMaxDps());
                                if (registerConfigureAndStartAbility != null) {
                                    registerConfigureAndStartAbility.startEffects();
                                    this.f2924b -= energyCost;
                                    int[] iArr = this.abilitiesConfiguration.counts;
                                    iArr[slot] = iArr[slot] - 1;
                                    int[] iArr2 = this.abilitiesUsed;
                                    iArr2[slot] = iArr2[slot] + 1;
                                    b();
                                    this.S.events.trigger(new AbilityApply(registerConfigureAndStartAbility, useAbilityAction.x, useAbilityAction.y));
                                    this.S.gameState.registerPlayerActivity();
                                } else {
                                    f2923a.e("useAbility - ability can not be started", new Object[0]);
                                }
                            } else {
                                f2923a.e("useAbility - ability requires " + energyCost + " energy though only " + this.f2924b + " available", new Object[0]);
                            }
                        } else {
                            f2923a.e("useAbility - no abilities of type " + useAbilityAction.abilityType.name() + " left", new Object[0]);
                        }
                    } else {
                        f2923a.e("useAbility - ability type " + useAbilityAction.abilityType.name() + " not exists in configuration", new Object[0]);
                    }
                }
            }
        }
        if (this.S.gameState.isGameRealTimePasses() && this.f2924b < getMaxEnergy()) {
            float energyRegenerationTime = getEnergyRegenerationTime();
            this.c += f;
            if (this.c >= energyRegenerationTime) {
                this.c -= energyRegenerationTime;
                this.f2924b++;
                if (this.f2924b == getMaxEnergy()) {
                    this.c = 0.0f;
                }
            }
        }
        this.activeAbilities.begin();
        for (int i2 = 0; i2 < this.activeAbilities.size; i2++) {
            Ability ability = this.activeAbilities.items[i2];
            ability.update(f);
            if (ability.isDone()) {
                ability.onDone();
                ability.setUnregistered();
                this.activeAbilities.removeIndex(i2);
            }
        }
        this.activeAbilities.end();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Ability";
    }

    public final int getMaxEnergy() {
        return this.S.gameValue.getIntValue(GameValueType.ABILITIES_MAX_ENERGY);
    }

    public final float getEnergyRegenerationTime() {
        return this.S.gameValue.getFloatValue(GameValueType.ABILITIES_ENERGY_GENERATION_INTERVAL);
    }

    public final void draw(Batch batch, float f) {
        this.activeAbilities.begin();
        for (int i = 0; i < this.activeAbilities.size; i++) {
            this.activeAbilities.items[i].draw(batch, f);
        }
        this.activeAbilities.end();
    }

    public final void drawBatchAdditive(Batch batch, float f) {
        this.activeAbilities.begin();
        for (int i = 0; i < this.activeAbilities.size; i++) {
            this.activeAbilities.items[i].drawBatchAdditive(batch, f);
        }
        this.activeAbilities.end();
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        for (int i = 0; i < this.activeAbilities.size; i++) {
            this.activeAbilities.items[i].setUnregistered();
        }
        this.activeAbilities.clear();
        super.dispose();
    }
}
