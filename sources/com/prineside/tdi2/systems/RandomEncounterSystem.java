package com.prineside.tdi2.systems;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.actions.EncounterBirdClickAction;
import com.prineside.tdi2.actions.EncounterBirdDeclineAction;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.game.InputMultiplexerConfigure;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.systems.randomEncounter.EncounterBird;
import com.prineside.tdi2.systems.randomEncounter.reward.BonusLevelPassReward;
import com.prineside.tdi2.systems.randomEncounter.reward.CoinsReward;
import com.prineside.tdi2.systems.randomEncounter.reward.GiveItemIndividuallyReward;
import com.prineside.tdi2.systems.randomEncounter.reward.GiveItemReward;
import com.prineside.tdi2.systems.randomEncounter.reward.GiveItemsReward;
import com.prineside.tdi2.systems.randomEncounter.reward.HealthReward;
import com.prineside.tdi2.systems.randomEncounter.reward.UpgradeAllTowersReward;
import com.prineside.tdi2.systems.randomEncounter.type.BirdsFlockEncounter;
import com.prineside.tdi2.systems.randomEncounter.type.RunningStarEncounter;
import com.prineside.tdi2.systems.randomEncounter.type.StarfallEncounter;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RandomEncounterSystem.class */
public class RandomEncounterSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3048a = TLog.forClass(RandomEncounterSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private Array<EncounterType> f3049b = new Array<>(true, 1, EncounterType.class);
    private Array<RewardType> c;
    private RandomXS128 d;
    private double e;
    private DelayedRemovalArray<EncounterBird> f;
    private int g;
    private boolean h;

    @REGS(classOnly = true)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RandomEncounterSystem$BirdAction.class */
    public interface BirdAction {
        void performAction(GameSystemProvider gameSystemProvider, EncounterBird encounterBird);
    }

    @REGS(classOnly = true)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RandomEncounterSystem$EncounterType.class */
    public interface EncounterType {
        int getProbability(GameSystemProvider gameSystemProvider);

        EncounterBird spawnBird(GameSystemProvider gameSystemProvider);
    }

    @REGS(classOnly = true)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RandomEncounterSystem$RewardType.class */
    public interface RewardType {
        int getProbability(GameSystemProvider gameSystemProvider);

        void giveReward(GameSystemProvider gameSystemProvider, float f, float f2);
    }

    public RandomEncounterSystem() {
        this.f3049b.add(new RunningStarEncounter());
        this.f3049b.add(new StarfallEncounter());
        this.f3049b.add(new BirdsFlockEncounter());
        this.c = new Array<>(true, 1, RewardType.class);
        this.d = new RandomXS128();
        this.f = new DelayedRemovalArray<>(true, 1, EncounterBird.class);
        this.g = -1;
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f3049b);
        kryo.writeObject(output, this.c);
        kryo.writeObject(output, this.d);
        output.writeDouble(this.e);
        kryo.writeObject(output, this.f);
        output.writeInt(this.g);
        output.writeBoolean(this.h);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f3049b = (Array) kryo.readObject(input, Array.class);
        this.c = (Array) kryo.readObject(input, Array.class);
        this.d = (RandomXS128) kryo.readObject(input, RandomXS128.class);
        this.e = input.readDouble();
        this.f = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.g = input.readInt();
        this.h = input.readBoolean();
    }

    @Override // com.prineside.tdi2.GameSystem
    public void setup() {
        if (!this.S.CFG.headless) {
            b();
        }
    }

    public boolean canGiveStateAffectingRewards() {
        if (this.S.gameState.userMapId != null) {
            return true;
        }
        if (DifficultyMode.isEndless(this.S.gameState.difficultyMode) && this.S.gameState.isMaxEndlessReplayTimeReached()) {
            return true;
        }
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public void postSetup() {
        this.d.setSeed((this.S.gameState.getRandom().getState(0) * 31) + this.S.gameState.gameStartTimestamp);
        this.c.add(new GiveItemReward(50, new ItemStack(Item.D.GREEN_PAPER, (int) (this.S.loot.inventoryStatistics.papersPerHourEstimate / 3.0f))));
        this.c.add(new GiveItemReward(30, new ItemStack(Item.D.GREEN_PAPER, (int) (this.S.loot.inventoryStatistics.papersPerHourEstimate / 2.0f))));
        this.c.add(new GiveItemIndividuallyReward(15, new ItemStack(Item.D.ACCELERATOR, 10)));
        this.c.add(new GiveItemIndividuallyReward(5, new ItemStack(Item.D.ACCELERATOR, 15)));
        this.c.add(new BonusLevelPassReward(3));
        this.c.add(new GiveItemReward(8, new ItemStack(Item.D.RESEARCH_TOKEN, 1)));
        this.c.add(new GiveItemsReward(30));
        this.c.add(new CoinsReward(30, 5000));
        this.c.add(new HealthReward(20, 100));
        this.c.add(new UpgradeAllTowersReward(15));
        try {
            if (this.S.gameState.basicLevelName != null && Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName).stageName.equals("0")) {
                this.S.gameValue.addCustomGameValue(new GameValueConfig(GameValueType.ENCOUNTER_BIRD_PROBABILITY, 0.0d, true, false));
                f3048a.d("disabling for tutorials", new Object[0]);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public void postStateRestore() {
        super.postStateRestore();
        b();
    }

    public boolean isReceivedBonusLevelPass() {
        return this.h;
    }

    public void startFireworks(float f, float f2) {
        if (this.S._particle != null) {
            ParticleEffectPool.PooledEffect obtain = Game.i.assetManager.getParticleEffectPool("fireworks.p").obtain();
            obtain.setPosition(f, f2);
            this.S._particle.addParticle(obtain, false);
        }
    }

    public void setReceivedBonusLevelPass(boolean z) {
        f3048a.d("setReceivedBonusLevelPass %s", Boolean.valueOf(z));
        this.h = z;
    }

    public Array<EncounterType> getEncounterTypes() {
        return this.f3049b;
    }

    public Array<RewardType> getRewardTypes() {
        return this.c;
    }

    public RandomXS128 getRandom() {
        return this.d;
    }

    private void b() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.RANDOM_ENCOUNTER_DRAW, false, this::a).setName("RandomEncounter-draw"));
        this.S.events.getListeners(InputMultiplexerConfigure.class).addWithPriority(inputMultiplexerConfigure -> {
            c();
        }, 1000).setDescription("Adds a layer of input handling which detects touchDown on birds");
        c();
    }

    private void c() {
        SnapshotArray<InputProcessor> processors = this.S._input.getInputMultiplexer().getProcessors();
        processors.begin();
        int i = 0;
        while (true) {
            if (i >= processors.size) {
                break;
            }
            InputProcessor inputProcessor = processors.get(i);
            if (inputProcessor instanceof BirdClickInputProcessor) {
                f3048a.d("skipping reconfiguring the multiplexer - BirdClickInputProcessor already enqueued", new Object[0]);
                break;
            } else if (inputProcessor != this.S._input.cameraController.getInputProcessor()) {
                i++;
            } else {
                this.S._input.getInputMultiplexer().addProcessorAtIndex(i, new BirdClickInputProcessor());
                processors.end();
                return;
            }
        }
        processors.end();
        f3048a.w("camera controller input processor not found in the main input multiplexer", new Object[0]);
    }

    public RewardType selectRandomReward() {
        f3048a.d("selectRandomReward", new Object[0]);
        if (this.c.size == 0) {
            throw new IllegalStateException("No rewards were defined");
        }
        RewardType rewardType = this.c.get(0);
        if (this.c.size > 1) {
            long j = 0;
            for (int i = 0; i < this.c.size; i++) {
                j += this.c.get(i).getProbability(this.S);
            }
            long nextLong = this.d.nextLong(j);
            long j2 = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.c.size) {
                    break;
                }
                RewardType rewardType2 = this.c.get(i2);
                long probability = rewardType2.getProbability(this.S);
                if (j2 + probability > nextLong) {
                    rewardType = rewardType2;
                    break;
                }
                j2 += probability;
                i2++;
            }
        }
        return rewardType;
    }

    private void a(Batch batch, float f, float f2, float f3) {
        for (int i = 0; i < this.f.size; i++) {
            this.f.get(i).updateOnDraw(f2);
            this.f.get(i).draw(batch);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public void update(float f) {
        StateSystem.ActionsArray currentUpdateActions = this.S.gameState.getCurrentUpdateActions();
        if (currentUpdateActions != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                Action action = currentUpdateActions.actions[i];
                if (action.getType() != ActionType.EBC) {
                    if (action.getType() == ActionType.EBD) {
                        EncounterBirdDeclineAction encounterBirdDeclineAction = (EncounterBirdDeclineAction) action;
                        if (encounterBirdDeclineAction.birdIdx >= 0 && encounterBirdDeclineAction.birdIdx < this.f.size) {
                            EncounterBird encounterBird = this.f.get(encounterBirdDeclineAction.birdIdx);
                            if (!encounterBird.isActive()) {
                                f3048a.w("bird %s is no longer active", Integer.valueOf(encounterBirdDeclineAction.birdIdx));
                            } else {
                                encounterBird.onDeclineAction();
                            }
                        } else {
                            f3048a.w("declineAction incorrect bird idx: %s, range 0..%s", Integer.valueOf(encounterBirdDeclineAction.birdIdx), Integer.valueOf(this.f.size - 1));
                        }
                    }
                } else {
                    EncounterBirdClickAction encounterBirdClickAction = (EncounterBirdClickAction) action;
                    if (encounterBirdClickAction.birdIdx >= 0 && encounterBirdClickAction.birdIdx < this.f.size) {
                        EncounterBird encounterBird2 = this.f.get(encounterBirdClickAction.birdIdx);
                        if (!encounterBird2.isActive()) {
                            f3048a.w("bird %s is no longer active", Integer.valueOf(encounterBirdClickAction.birdIdx));
                        } else {
                            encounterBird2.onClickAction();
                        }
                    } else {
                        f3048a.w("clickAction incorrect bird idx: %s, range 0..%s", Integer.valueOf(encounterBirdClickAction.birdIdx), Integer.valueOf(this.f.size - 1));
                    }
                }
            }
        }
        if (this.S.gameState.isGameRealTimePasses()) {
            this.e += f;
            if (this.e >= 1.0d) {
                this.e -= 1.0d;
                if (this.S.wave.wave != null && this.S.wave.wave.waveNumber >= this.S.gameValue.getIntValue(GameValueType.ENCOUNTER_BIRD_MIN_WAVE)) {
                    if (this.d.nextDouble() < this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ENCOUNTER_BIRD_PROBABILITY)) {
                        startNewEncounter();
                    } else if (this.g >= 0) {
                        this.g--;
                        if (this.g == -1) {
                            f3048a.d("double encounter", new Object[0]);
                            startNewEncounter();
                        }
                    }
                }
            }
        }
        this.f.begin();
        for (int i2 = 0; i2 < this.f.size; i2++) {
            EncounterBird encounterBird3 = this.f.get(i2);
            encounterBird3.tick();
            if (!encounterBird3.isActive()) {
                encounterBird3.onFinish();
                this.f.removeIndex(i2);
            }
        }
        this.f.end();
    }

    public void addBird(EncounterBird encounterBird) {
        this.f.add(encounterBird);
    }

    public DelayedRemovalArray<EncounterBird> getActiveBirds() {
        return this.f;
    }

    public void startNewEncounter() {
        f3048a.d("encounter", new Object[0]);
        if (this.f3049b.size == 0) {
            return;
        }
        EncounterType encounterType = this.f3049b.get(0);
        if (this.f3049b.size > 1) {
            long j = 0;
            for (int i = 0; i < this.f3049b.size; i++) {
                j += this.f3049b.get(i).getProbability(this.S);
            }
            long nextLong = this.d.nextLong(j);
            long j2 = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.f3049b.size) {
                    break;
                }
                EncounterType encounterType2 = this.f3049b.get(i2);
                long probability = encounterType2.getProbability(this.S);
                if (j2 + probability > nextLong) {
                    encounterType = encounterType2;
                    break;
                } else {
                    j2 += probability;
                    i2++;
                }
            }
            f3048a.d("- %s", encounterType.getClass().getSimpleName());
        }
        this.f.addAll(encounterType.spawnBird(this.S));
        if (this.d.nextFloat() < 0.04f) {
            this.g = 1 + this.d.nextInt(25);
        }
        if (!Config.isHeadless() && !ProgressPrefs.i().progress.isEncounterBirdEncountered() && !this.S.CFG.headless) {
            Timer.schedule(new Timer.Task() { // from class: com.prineside.tdi2.systems.RandomEncounterSystem.1
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    if (RandomEncounterSystem.this.S != null && !RandomEncounterSystem.this.S.isDisposed() && RandomEncounterSystem.this.S._gameUi != null) {
                        ProgressPrefs.i().progress.setEncounterBirdEncountered(true);
                        RandomEncounterSystem.this.S._gameUi.newEnemyOverlay.showCustom(Game.i.localeManager.i18n.get("random_encounter_hint_title"), Game.i.localeManager.i18n.get("random_encounter_hint_description"), Game.i.assetManager.getQuad("randomEncounter.hintIcon"));
                    }
                }
            }, 0.5f);
        }
    }

    public void birdAcceptAction(int i) {
        this.S.gameState.pushActionNextUpdate(new EncounterBirdClickAction(i));
    }

    public void birdDeclineAction(int i) {
        this.S.gameState.pushActionNextUpdate(new EncounterBirdDeclineAction(i));
    }

    @Override // com.prineside.tdi2.GameSystem
    public boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public String getSystemName() {
        return "RandomEncounter";
    }

    public EncounterBird prepareDefaultBird(BirdAction birdAction) {
        EncounterBird encounterBird = new EncounterBird(this.S, birdAction);
        encounterBird.setLifeTime(this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_LIFE_TIME));
        encounterBird.acceleration = this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_ACCELERATION);
        encounterBird.maxVelocity = this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_MAX_VELOCITY);
        encounterBird.requiresConfirmation = true;
        switch (FastRandom.getFairInt(4)) {
            case 0:
                encounterBird.baseColor = MaterialColor.GREEN.P600.cpy();
                encounterBird.overlayColor = MaterialColor.GREEN.P400.cpy();
                break;
            case 1:
                encounterBird.baseColor = MaterialColor.BLUE.P600.cpy();
                encounterBird.overlayColor = MaterialColor.BLUE.P400.cpy();
                break;
            case 2:
                encounterBird.baseColor = MaterialColor.RED.P600.cpy();
                encounterBird.overlayColor = MaterialColor.RED.P400.cpy();
                break;
            case 3:
                encounterBird.baseColor = MaterialColor.YELLOW.P600.cpy();
                encounterBird.overlayColor = MaterialColor.YELLOW.P400.cpy();
                break;
        }
        encounterBird.maxHp = 1;
        encounterBird.hp = 1;
        return encounterBird;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RandomEncounterSystem$BirdClickInputProcessor.class */
    public class BirdClickInputProcessor extends InputAdapter {
        public BirdClickInputProcessor() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchDown(int i, int i2, int i3, int i4) {
            if (i3 != 0) {
                return false;
            }
            Vector2 vector2 = new Vector2(i, i2);
            RandomEncounterSystem.this.S._input.cameraController.screenToMap(vector2);
            for (int i5 = 0; i5 < RandomEncounterSystem.this.f.size; i5++) {
                EncounterBird encounterBird = (EncounterBird) RandomEncounterSystem.this.f.get(i5);
                if (encounterBird.isMouseHit(vector2.x, vector2.y)) {
                    if (RandomEncounterSystem.this.S.gameState.getGameSpeed() < 0.95f) {
                        RandomEncounterSystem.f3048a.w("speed is below 1", new Object[0]);
                        Notifications.i().add(Game.i.localeManager.i18n.get("random_encounter_not_available_while_paused"), Game.i.assetManager.getDrawable("icon-speed-pause"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                    } else if (encounterBird.requiresConfirmation) {
                        float gameSpeed = RandomEncounterSystem.this.S.gameState.getGameSpeed();
                        RandomEncounterSystem.this.S.gameState.setGameSpeed(Math.min(gameSpeed, 0.0667f));
                        int i6 = i5;
                        Dialog.i().showConfirmWithCallbacks(Game.i.localeManager.i18n.get("encounter_bird_confirm_dialog"), () -> {
                            RandomEncounterSystem.this.birdAcceptAction(i6);
                            RandomEncounterSystem.this.S.gameState.setGameSpeed(gameSpeed);
                        }, () -> {
                            RandomEncounterSystem.this.birdDeclineAction(i6);
                            RandomEncounterSystem.this.S.gameState.setGameSpeed(gameSpeed);
                        });
                    } else {
                        RandomEncounterSystem.this.birdAcceptAction(i5);
                    }
                    RandomEncounterSystem.f3048a.d("consume touchDown", new Object[0]);
                    return true;
                }
            }
            return false;
        }
    }
}
