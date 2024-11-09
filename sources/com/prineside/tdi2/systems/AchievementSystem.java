package com.prineside.tdi2.systems;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.abilities.NukeAbility;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.MdpsUpdate;
import com.prineside.tdi2.events.game.ScoreChange;
import com.prineside.tdi2.events.game.TowerBuild;
import com.prineside.tdi2.events.game.WaveComplete;
import com.prineside.tdi2.projectiles.MultishotProjectile;
import com.prineside.tdi2.towers.AirTower;
import com.prineside.tdi2.units.DisorientedUnit;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/AchievementSystem.class */
public final class AchievementSystem extends GameSystem {

    @NAGS
    private float e;

    @NAGS
    private boolean g;

    @NAGS
    private boolean h;
    private static final EnemyType[] j = {EnemyType.REGULAR, EnemyType.FAST, EnemyType.STRONG, EnemyType.HELI, EnemyType.JET, EnemyType.ARMORED, EnemyType.HEALER, EnemyType.TOXIC, EnemyType.ICY, EnemyType.FIGHTER, EnemyType.LIGHT};

    @NAGS
    private int f = 0;

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    int f2926a = 0;

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    int f2927b = 0;

    @NAGS
    private int[] i = new int[AchievementType.values.length];

    @NAGS
    IntArray c = new IntArray(false, 8);

    @NAGS
    int d = 0;

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.e);
        output.writeVarInt(this.f, true);
        output.writeBoolean(this.g);
        output.writeBoolean(this.h);
        output.writeVarInt(this.f2926a, true);
        output.writeVarInt(this.f2927b, true);
        kryo.writeObject(output, this.i);
        kryo.writeObject(output, this.c);
        output.writeInt(this.d);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readFloat();
        this.f = input.readVarInt(true);
        this.g = input.readBoolean();
        this.h = input.readBoolean();
        this.f2926a = input.readVarInt(true);
        this.f2927b = input.readVarInt(true);
        this.i = (int[]) kryo.readObject(input, int[].class);
        this.c = (IntArray) kryo.readObject(input, IntArray.class);
        this.d = input.readInt();
    }

    public final boolean isActive() {
        return this.g;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        a();
        if (isActive()) {
            this.S.events.getListeners(TowerBuild.class).addStateAffecting(new OnTowerBuild(this, (byte) 0));
            this.S.events.getListeners(WaveComplete.class).addStateAffecting(new OnWaveComplete(this, (byte) 0));
            this.S.events.getListeners(MdpsUpdate.class).addStateAffecting(new OnMdpsUpdate(this));
            this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(this));
            this.S.events.getListeners(ScoreChange.class).addStateAffecting(new OnScoreChange(this, (byte) 0));
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        a();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    private void a() {
        BasicLevel level;
        if (Config.isHeadless()) {
            this.g = false;
            return;
        }
        this.g = !this.h && this.S.gameState.difficultyMode == DifficultyMode.NORMAL && !this.S.gameState.replayMode && Game.i.isInMainThread();
        if (this.S.gameState.basicLevelName != null && (level = Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName)) != null && level.achievementsDisabled) {
            this.g = false;
        }
    }

    public final void setEnabled(boolean z) {
        this.h = !z;
        a();
    }

    public final void registerDelta(AchievementType achievementType, int i) {
        if (isActive()) {
            int[] iArr = this.i;
            int ordinal = achievementType.ordinal();
            iArr[ordinal] = iArr[ordinal] + i;
            Game.i.achievementManager.setProgress(achievementType, this.i[achievementType.ordinal()]);
        }
    }

    public final void setProgress(AchievementType achievementType, int i) {
        if (isActive()) {
            Game.i.achievementManager.setProgress(achievementType, i);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        if (isActive()) {
            this.d = 0;
            this.e += f;
            if (this.e > 0.1f) {
                this.e -= 0.1f;
                switch (this.f) {
                    case 0:
                        if (!Game.i.achievementManager.isRequirementMet(AchievementType.EVERY_ENEMY_MET)) {
                            int i = 0;
                            for (EnemyType enemyType : j) {
                                if (!Game.i.enemyManager.isEnemyTypeNewForPlayer(enemyType, false)) {
                                    i++;
                                }
                            }
                            setProgress(AchievementType.EVERY_ENEMY_MET, i);
                            break;
                        }
                        break;
                    case 1:
                        float f2 = 0.0f;
                        for (int i2 = 0; i2 < this.S.tower.towers.size; i2++) {
                            float stat = this.S.tower.towers.items[i2].getStat(TowerStatType.ATTACK_SPEED);
                            if (f2 < stat) {
                                f2 = stat;
                            }
                        }
                        setProgress(AchievementType.HUGE_TOWER_ATTACK_SPEED, (int) f2);
                        break;
                    case 2:
                        if (!Game.i.achievementManager.isRequirementMet(AchievementType.MASS_TOWERS_LEVEL_DEV) && this.S.gameState.basicLevelName != null && this.S.gameState.basicLevelName.equals("dev")) {
                            setProgress(AchievementType.MASS_TOWERS_LEVEL_DEV, this.S.tower.towers.size);
                            break;
                        }
                        break;
                    case 3:
                        setProgress(AchievementType.MASS_MINERS, this.S.miner.miners.size);
                        break;
                    case 4:
                        if (this.f2926a == 1) {
                            setProgress(AchievementType.BUILD_TOWER_FINISH_WITH_TEN, this.S.tower.towers.size);
                            break;
                        }
                        break;
                    case 5:
                        int i3 = 0;
                        for (int i4 = 0; i4 < this.S.map.spawnedUnits.size; i4++) {
                            if (this.S.map.spawnedUnits.items[i4].type == UnitType.MINE) {
                                i3++;
                            }
                        }
                        setProgress(AchievementType.PLACE_MINES_ONE_GAME, i3);
                        break;
                    case 6:
                        int i5 = 0;
                        DelayedRemovalArray<Tile> allTiles = this.S.map.getMap().getAllTiles();
                        for (int i6 = 0; i6 < allTiles.size; i6++) {
                            if (allTiles.items[i6].type == TileType.PLATFORM) {
                                i5++;
                            }
                        }
                        int i7 = this.S.tower.towers.size;
                        for (int i8 = 0; i8 < this.S.map.spawnedUnits.size; i8++) {
                            if (this.S.map.spawnedUnits.items[i8].type == UnitType.MICROGUN) {
                                i7++;
                            }
                        }
                        setProgress(AchievementType.PLACE_MICROGUNS, i7 - i5);
                        break;
                    case 7:
                        int i9 = 0;
                        for (int i10 = 0; i10 < this.S.ability.activeAbilities.size; i10++) {
                            if (this.S.ability.activeAbilities.items[i10].getType() == AbilityType.BALL_LIGHTNING) {
                                i9++;
                            }
                        }
                        setProgress(AchievementType.MASS_BALL_LIGHTNINGS, i9);
                        break;
                    case 8:
                        int i11 = 0;
                        for (int i12 = 0; i12 < this.S.projectile.projectiles.size; i12++) {
                            if (this.S.projectile.projectiles.items[i12].type == ProjectileType.MISSILE) {
                                i11++;
                            }
                        }
                        setProgress(AchievementType.MASS_MISSILES, i11);
                        break;
                    case 9:
                        int i13 = 0;
                        for (int i14 = 0; i14 < this.S.map.spawnedUnits.size; i14++) {
                            Unit unit = this.S.map.spawnedUnits.items[i14];
                            if ((unit instanceof DisorientedUnit) && ((DisorientedUnit) unit).getSpawnedByTower() != null) {
                                i13++;
                            }
                        }
                        setProgress(AchievementType.RECRUIT_ENEMIES, i13);
                        break;
                    default:
                        this.f = 0;
                        break;
                }
                this.f++;
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Achievement";
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/AchievementSystem$OnScoreChange.class */
    public static class OnScoreChange extends SerializableListener<AchievementSystem> implements Listener<ScoreChange> {
        /* synthetic */ OnScoreChange(AchievementSystem achievementSystem, byte b2) {
            this(achievementSystem);
        }

        private OnScoreChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnScoreChange(AchievementSystem achievementSystem) {
            this.f1759a = achievementSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(ScoreChange scoreChange) {
            ((AchievementSystem) this.f1759a).setProgress(AchievementType.MILLION_SCORE_ONE_GAME, ((AchievementSystem) this.f1759a).S.gameState.getScore() >= 2147483647L ? Integer.MAX_VALUE : (int) ((AchievementSystem) this.f1759a).S.gameState.getScore());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/AchievementSystem$OnTowerBuild.class */
    public static final class OnTowerBuild extends SerializableListener<AchievementSystem> implements Listener<TowerBuild> {
        /* synthetic */ OnTowerBuild(AchievementSystem achievementSystem, byte b2) {
            this(achievementSystem);
        }

        private OnTowerBuild() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTowerBuild(AchievementSystem achievementSystem) {
            this.f1759a = achievementSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TowerBuild towerBuild) {
            if (((AchievementSystem) this.f1759a).isActive()) {
                if (towerBuild.getPrice() > 0) {
                    ((AchievementSystem) this.f1759a).f2926a++;
                } else {
                    ((AchievementSystem) this.f1759a).f2927b++;
                    ((AchievementSystem) this.f1759a).setProgress(AchievementType.COPY_TOWERS_ONE_GAME, ((AchievementSystem) this.f1759a).f2927b);
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/AchievementSystem$OnWaveComplete.class */
    public static final class OnWaveComplete extends SerializableListener<AchievementSystem> implements Listener<WaveComplete> {
        /* synthetic */ OnWaveComplete(AchievementSystem achievementSystem, byte b2) {
            this(achievementSystem);
        }

        private OnWaveComplete() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnWaveComplete(AchievementSystem achievementSystem) {
            this.f1759a = achievementSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(WaveComplete waveComplete) {
            ((AchievementSystem) this.f1759a).setProgress(AchievementType.REACH_HIGH_WAVE_ONE_GAME, waveComplete.getWave().waveNumber);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/AchievementSystem$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<AchievementSystem> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(AchievementSystem achievementSystem) {
            this.f1759a = achievementSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            GameSystemProvider gameSystemProvider = ((AchievementSystem) this.f1759a).S;
            if (gameSystemProvider.achievement.isActive()) {
                DamageRecord lastDamage = enemyDie.getLastDamage();
                Enemy enemy = lastDamage.getEnemy();
                if (enemy.type == EnemyType.BROOT_BOSS || enemy.type == EnemyType.SNAKE_BOSS_HEAD || enemy.type == EnemyType.METAPHOR_BOSS || enemy.type == EnemyType.MOBCHAIN_BOSS_HEAD || enemy.type == EnemyType.CONSTRUCTOR_BOSS) {
                    if (enemy.existsTime <= 3.0f) {
                        ((AchievementSystem) this.f1759a).setProgress(AchievementType.FAST_BOSS_KILL, 1);
                    }
                    if (enemy.hasBuffsByType(BuffType.BONUS_COINS)) {
                        ((AchievementSystem) this.f1759a).setProgress(AchievementType.KILL_BOSS_BONUS_COINS, 1);
                    }
                }
                if (enemy.hasBuffsByType(BuffType.THROW_BACK)) {
                    ((AchievementSystem) this.f1759a).registerDelta(AchievementType.KILL_THROWN_BACK_ENEMIES, 1);
                }
                Projectile projectile = lastDamage.getProjectile();
                if (projectile != null && projectile.type == ProjectileType.MULTISHOT && ((MultishotProjectile) projectile).flyingBack) {
                    ((AchievementSystem) this.f1759a).registerDelta(AchievementType.KILL_ENEMY_WITH_BACK_PROJECTILE, 1);
                }
                if (lastDamage.getDamageType() == DamageType.FIRE) {
                    ((AchievementSystem) this.f1759a).c.add(gameSystemProvider.gameState.updateNumber);
                    int i = gameSystemProvider.gameState.updateNumber;
                    int i2 = 0;
                    for (int i3 = ((AchievementSystem) this.f1759a).c.size - 1; i3 >= 0; i3--) {
                        if (i - ((AchievementSystem) this.f1759a).c.items[i3] <= gameSystemProvider.gameValue.getTickRate()) {
                            i2++;
                        } else {
                            ((AchievementSystem) this.f1759a).c.removeIndex(i3);
                        }
                    }
                    ((AchievementSystem) this.f1759a).setProgress(AchievementType.MASS_BURN_ENEMIES, i2);
                }
                if (!enemy.isAir() && (lastDamage.getTower() instanceof AirTower)) {
                    ((AchievementSystem) this.f1759a).registerDelta(AchievementType.KILL_GROUND_ENEMY_WITH_AIR, 1);
                }
                if (lastDamage.getAbility() instanceof NukeAbility) {
                    ((AchievementSystem) this.f1759a).d++;
                    ((AchievementSystem) this.f1759a).setProgress(AchievementType.HUNDRED_KILLS_NUKE, ((AchievementSystem) this.f1759a).d);
                }
                if ((enemy.type == EnemyType.BROOT_BOSS || enemy.type == EnemyType.SNAKE_BOSS_HEAD || enemy.type == EnemyType.MOBCHAIN_BOSS_HEAD || enemy.type == EnemyType.METAPHOR_BOSS || enemy.type == EnemyType.CONSTRUCTOR_BOSS) && (lastDamage.getUnit() instanceof DisorientedUnit)) {
                    ((AchievementSystem) this.f1759a).registerDelta(AchievementType.KILL_BOSS_WITH_RECRUIT, 1);
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/AchievementSystem$OnMdpsUpdate.class */
    public static final class OnMdpsUpdate extends SerializableListener<AchievementSystem> implements Listener<MdpsUpdate> {
        private OnMdpsUpdate() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnMdpsUpdate(AchievementSystem achievementSystem) {
            this.f1759a = achievementSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(MdpsUpdate mdpsUpdate) {
            ((AchievementSystem) this.f1759a).S.achievement.setProgress(AchievementType.MILLION_MDPS_ONE_GAME, (int) ((AchievementSystem) this.f1759a).S.damage.getTowersMaxDps());
        }
    }
}
