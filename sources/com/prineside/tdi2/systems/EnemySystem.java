package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDespawn;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.events.game.PathfindingRebuild;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.pathfinding.MoveSide;
import com.prineside.tdi2.pathfinding.Path;
import com.prineside.tdi2.pathfinding.PathNode;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.utils.EntityUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/EnemySystem.class */
public final class EnemySystem extends GameSystem {
    public static final int RANDOM_SIDE_SHIFT = -1;
    public static final int MIDDLE_SIDE_SHIFT = 5;
    public boolean[] flyingEnemy = new boolean[EnemyType.values.length];

    @NAGS
    public Color[] enemyColor;

    @NAGS
    public TextureRegion[] enemyTexture;

    @NAGS
    public TextureRegion[] enemyHighlightTexture;

    @NAGS
    public TextureRegion[] enemyEmojiTexture;
    public boolean[][] enemyDamageVulnerability;
    public boolean[][] enemySpecialDamageVulnerability;
    public float[][] enemyBuffVulnerability;

    /* renamed from: a, reason: collision with root package name */
    private DelayedRemovalArray<Enemy.EnemyReference> f2936a;

    /* renamed from: b, reason: collision with root package name */
    private int f2937b;
    private IntArray c;

    @NAGS
    private boolean d;
    private Enemy.EnemyReference[] e;
    private static final Comparator<Enemy.EnemyReference> f;
    private OnPathfindingRebuild g;
    private OnEnemyDespawn h;

    @NAGS
    private final Array<Enemy.EnemyReference> i;

    public EnemySystem() {
        this.flyingEnemy[EnemyType.HELI.ordinal()] = true;
        this.flyingEnemy[EnemyType.JET.ordinal()] = true;
        this.enemyColor = new Color[EnemyType.values.length];
        this.enemyTexture = new TextureRegion[EnemyType.values.length];
        this.enemyHighlightTexture = new TextureRegion[EnemyType.values.length];
        this.enemyEmojiTexture = new TextureRegion[EnemyType.values.length];
        if (Game.i.assetManager != null) {
            for (EnemyType enemyType : EnemyType.values) {
                this.enemyColor[enemyType.ordinal()] = Game.i.enemyManager.getFactory(enemyType).getColor();
                this.enemyTexture[enemyType.ordinal()] = Game.i.enemyManager.getFactory(enemyType).getTexture();
                this.enemyHighlightTexture[enemyType.ordinal()] = Game.i.enemyManager.getFactory(enemyType).getHighlightTexture();
                this.enemyEmojiTexture[enemyType.ordinal()] = Game.i.enemyManager.getFactory(enemyType).getEmojiTexture();
            }
        }
        this.enemyDamageVulnerability = new boolean[EnemyType.values.length][DamageType.values.length];
        for (boolean[] zArr : this.enemyDamageVulnerability) {
            Arrays.fill(zArr, true);
        }
        this.enemyDamageVulnerability[EnemyType.ARMORED.ordinal()][DamageType.ELECTRICITY.ordinal()] = false;
        this.enemyDamageVulnerability[EnemyType.HEALER.ordinal()][DamageType.FIRE.ordinal()] = false;
        this.enemyDamageVulnerability[EnemyType.TOXIC.ordinal()][DamageType.POISON.ordinal()] = false;
        this.enemySpecialDamageVulnerability = new boolean[EnemyType.values.length][SpecialDamageType.values.length];
        for (boolean[] zArr2 : this.enemySpecialDamageVulnerability) {
            Arrays.fill(zArr2, true);
        }
        this.enemySpecialDamageVulnerability[EnemyType.BROOT_BOSS.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.CONSTRUCTOR_BOSS.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.METAPHOR_BOSS.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.METAPHOR_BOSS_CREEP.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.MOBCHAIN_BOSS_HEAD.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.MOBCHAIN_BOSS_BODY.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.MOBCHAIN_BOSS_CREEP.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.SNAKE_BOSS_HEAD.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.SNAKE_BOSS_BODY.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemySpecialDamageVulnerability[EnemyType.SNAKE_BOSS_TAIL.ordinal()][SpecialDamageType.KILLSHOT.ordinal()] = false;
        this.enemyBuffVulnerability = new float[EnemyType.values.length][BuffType.values.length];
        for (float[] fArr : this.enemyBuffVulnerability) {
            Arrays.fill(fArr, 1.0f);
        }
        this.enemyBuffVulnerability[EnemyType.HEALER.ordinal()][BuffType.BURN.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.TOXIC.ordinal()][BuffType.POISON.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.ICY.ordinal()][BuffType.BURN.ordinal()] = 1.5f;
        this.enemyBuffVulnerability[EnemyType.BROOT_BOSS.ordinal()][BuffType.STUN.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.BROOT_BOSS.ordinal()][BuffType.FREEZING.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.BROOT_BOSS.ordinal()][BuffType.BLIZZARD.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.BROOT_BOSS.ordinal()][BuffType.SNOWBALL.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.CONSTRUCTOR_BOSS.ordinal()][BuffType.STUN.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.CONSTRUCTOR_BOSS.ordinal()][BuffType.FREEZING.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.CONSTRUCTOR_BOSS.ordinal()][BuffType.THROW_BACK.ordinal()] = 0.0f;
        this.enemyBuffVulnerability[EnemyType.CONSTRUCTOR_BOSS.ordinal()][BuffType.SNOWBALL.ordinal()] = 0.0f;
        EnemyType[] enemyTypeArr = {EnemyType.METAPHOR_BOSS, EnemyType.METAPHOR_BOSS_CREEP};
        for (int i = 0; i < 2; i++) {
            EnemyType enemyType2 = enemyTypeArr[i];
            this.enemyBuffVulnerability[enemyType2.ordinal()][BuffType.STUN.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType2.ordinal()][BuffType.FREEZING.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType2.ordinal()][BuffType.THROW_BACK.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType2.ordinal()][BuffType.BLIZZARD.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType2.ordinal()][BuffType.REGENERATION.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType2.ordinal()][BuffType.SNOWBALL.ordinal()] = 0.0f;
        }
        EnemyType[] enemyTypeArr2 = {EnemyType.MOBCHAIN_BOSS_BODY, EnemyType.MOBCHAIN_BOSS_CREEP, EnemyType.MOBCHAIN_BOSS_HEAD};
        for (int i2 = 0; i2 < 3; i2++) {
            EnemyType enemyType3 = enemyTypeArr2[i2];
            this.enemyBuffVulnerability[enemyType3.ordinal()][BuffType.THROW_BACK.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType3.ordinal()][BuffType.SNOWBALL.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType3.ordinal()][BuffType.BLIZZARD.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType3.ordinal()][BuffType.FREEZING.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType3.ordinal()][BuffType.STUN.ordinal()] = 0.0f;
        }
        EnemyType[] enemyTypeArr3 = {EnemyType.SNAKE_BOSS_BODY, EnemyType.SNAKE_BOSS_HEAD, EnemyType.SNAKE_BOSS_TAIL};
        for (int i3 = 0; i3 < 3; i3++) {
            EnemyType enemyType4 = enemyTypeArr3[i3];
            this.enemyBuffVulnerability[enemyType4.ordinal()][BuffType.STUN.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType4.ordinal()][BuffType.POISON.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType4.ordinal()][BuffType.BLIZZARD.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType4.ordinal()][BuffType.FREEZING.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType4.ordinal()][BuffType.SNOWBALL.ordinal()] = 0.0f;
            this.enemyBuffVulnerability[enemyType4.ordinal()][BuffType.THROW_BACK.ordinal()] = 0.0f;
        }
        this.f2936a = new DelayedRemovalArray<>(false, 8, Enemy.EnemyReference.class);
        this.f2937b = 1;
        this.c = new IntArray();
        this.d = false;
        this.e = new Enemy.EnemyReference[16];
        this.g = new OnPathfindingRebuild(this, (byte) 0);
        this.h = new OnEnemyDespawn(this, (byte) 0);
        this.i = new Array<>(false, 64, Enemy.EnemyReference.class);
    }

    static {
        TLog.forClass(EnemySystem.class);
        f = (enemyReference, enemyReference2) -> {
            return Float.compare(enemyReference.enemy.graphPath == null ? 0.0f : enemyReference.enemy.graphPath.getLengthInTiles() - enemyReference.enemy.passedTiles, enemyReference2.enemy.graphPath == null ? 0.0f : enemyReference2.enemy.graphPath.getLengthInTiles() - enemyReference2.enemy.passedTiles);
        };
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.flyingEnemy);
        kryo.writeObject(output, this.enemyDamageVulnerability);
        kryo.writeObject(output, this.enemyBuffVulnerability);
        kryo.writeObject(output, this.f2936a);
        output.writeVarInt(this.f2937b, true);
        kryo.writeObject(output, this.c);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.g);
        kryo.writeObject(output, this.h);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.flyingEnemy = (boolean[]) kryo.readObject(input, boolean[].class);
        this.enemyDamageVulnerability = (boolean[][]) kryo.readObject(input, boolean[][].class);
        this.enemyBuffVulnerability = (float[][]) kryo.readObject(input, float[][].class);
        this.f2936a = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.f2937b = input.readVarInt(true);
        this.c = (IntArray) kryo.readObject(input, IntArray.class);
        this.e = (Enemy.EnemyReference[]) kryo.readObject(input, Enemy.EnemyReference[].class);
        this.g = (OnPathfindingRebuild) kryo.readObject(input, OnPathfindingRebuild.class);
        this.h = (OnEnemyDespawn) kryo.readObject(input, OnEnemyDespawn.class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        if (!this.S.CFG.headless) {
            a();
        }
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.ENEMY_DRAW, false, (batch, f2, f3, f4) -> {
            draw(batch, f3, f4);
        }).setName("Enemy-draw"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.ENEMY_DRAW_HEALTH, false, (batch2, f5, f6, f7) -> {
            if (this.S._input.cameraController.zoom < 2.5d) {
                drawEnemyHealth(batch2);
            }
        }).setName("Enemy-drawHealth"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        this.S.events.getListeners(PathfindingRebuild.class).addStateAffecting(this.g).setDescription("EnemySystem");
        this.S.events.getListeners(EnemyDespawn.class).addStateAffectingWithPriority(this.h, EventListeners.PRIORITY_LOWEST).setName("EnemySystem - unregister despawned").setDescription("Unregisters despawned enemies");
        b();
    }

    private void b() {
        String format = new SimpleDateFormat("dd/MM", Locale.US).format(new Date());
        if (this.S.gameValue.getBooleanValue(GameValueType.EMOJI_ENEMIES) || format.equals("01/04")) {
            this.d = true;
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        super.postStateRestore();
        b();
        a();
    }

    private int c() {
        return this.S.gameState.randomInt(11);
    }

    public final void registerWithRandomSideShift(Enemy enemy) {
        registerWithSideShift(enemy, c());
    }

    public final Enemy.EnemyReference getReference(Enemy enemy) {
        if (enemy == null) {
            return Enemy.EnemyReference.NULL;
        }
        if (enemy.id == 0) {
            throw new IllegalArgumentException("Enemy is not registered: " + enemy);
        }
        return this.e[enemy.id];
    }

    private void a(Enemy enemy) {
        int i;
        if (enemy.id != 0) {
            throw new IllegalArgumentException("Enemy " + enemy + " is already registered with id " + enemy.id);
        }
        if (this.c.size != 0) {
            this.c.sort();
            i = this.c.removeIndex(0);
        } else {
            i = this.f2937b;
            this.f2937b++;
        }
        enemy.id = i;
        Enemy.EnemyReference enemyReference = new Enemy.EnemyReference();
        enemyReference.enemy = enemy;
        if (i >= this.e.length) {
            Enemy.EnemyReference[] enemyReferenceArr = new Enemy.EnemyReference[MathUtils.nextPowerOfTwo(i + 1)];
            System.arraycopy(this.e, 0, enemyReferenceArr, 0, this.e.length);
            this.e = enemyReferenceArr;
        }
        this.e[i] = enemyReference;
    }

    public final void registerWithSideShift(Enemy enemy, int i) {
        if (enemy.spawnTile == null) {
            throw new IllegalArgumentException("Enemy must have spawnTile set");
        }
        this.S.gameState.checkGameplayUpdateAllowed();
        a(enemy);
        enemy.setRegistered(this.S);
        if (i == -1) {
            i = c();
        }
        enemy.graphPath = this.S.pathfinding.findPathToTargetTile(enemy.spawnTile, enemy.getTypeForPathfinding());
        enemy.sideShiftIndex = i;
    }

    public final void registerWithPath(Enemy enemy, @Null Path path, int i, float f2) {
        if (enemy.spawnTile == null) {
            throw new IllegalArgumentException("Enemy must have spawnTile set");
        }
        this.S.gameState.checkGameplayUpdateAllowed();
        a(enemy);
        enemy.setRegistered(this.S);
        if (path != null && i == -1) {
            i = c();
        }
        enemy.graphPath = path;
        enemy.sideShiftIndex = i;
        enemy.passedTiles = f2;
        enemy.sumPassedTiles = f2;
        if (EnemyType.isBoss(enemy.type) && enemy.hasDrawPriority()) {
            enemy.healthBarScale = 2.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Enemy enemy) {
        this.S.gameState.checkGameplayUpdateAllowed();
        Enemy.EnemyReference reference = getReference(enemy);
        enemy.setUnregistered();
        this.f2936a.removeValue(reference, true);
        reference.enemy = null;
        this.e[enemy.id] = null;
        this.c.add(enemy.id);
        enemy.id = 0;
    }

    public final void queueAllEnemiesPathfinding() {
        int intValue = this.S.gameValue.getIntValue(GameValueType.ENEMIES_MAX_PATH_SEARCHES);
        this.f2936a.clear();
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy.EnemyReference enemyReference = this.S.map.spawnedEnemies.items[i];
            Enemy enemy = enemyReference.enemy;
            if (enemy != null && !enemy.ignorePathfinding && enemy.pathSearches < intValue && enemy.graphPath != null) {
                this.f2936a.add(enemyReference);
            }
        }
    }

    public final void addEnemyWithFirstSpawn(Enemy enemy, Tile tile, int i) {
        this.S.gameState.checkGameplayUpdateAllowed();
        enemy.spawnTile = (SpawnTile) this.S.map.getMap().getTilesByType(SpawnTile.class).first();
        enemy.wave = null;
        enemy.graphPath = this.S.pathfinding.findPathToTargetTile(tile, enemy.getTypeForPathfinding());
        if (enemy.graphPath == null) {
            throw new IllegalArgumentException("Can't find path for " + enemy.type + " from " + tile);
        }
        registerWithPath(enemy, enemy.graphPath, i, 0.0f);
        this.S.loot.fillWithLoot(enemy);
        this.S.map.spawnEnemy(enemy);
        enemy.setPositionToPath();
    }

    public final void addStaticEnemy(Enemy enemy, float f2, float f3) {
        this.S.gameState.checkGameplayUpdateAllowed();
        enemy.spawnTile = (SpawnTile) this.S.map.getMap().getTilesByType(SpawnTile.class).first();
        enemy.wave = null;
        registerWithPath(enemy, enemy.graphPath, 5, 0.0f);
        this.S.map.spawnEnemy(enemy);
        enemy.setPosition(f2, f3);
    }

    public final void addEnemy(Enemy enemy, SpawnTile spawnTile, int i, Wave wave, float f2) {
        this.S.gameState.checkGameplayUpdateAllowed();
        enemy.spawnTile = spawnTile;
        enemy.wave = wave;
        registerWithSideShift(enemy, i);
        this.S.loot.fillWithLoot(enemy);
        this.S.map.spawnEnemy(enemy);
        enemy.passedTiles = f2;
        enemy.setPositionToPath();
    }

    public final void addEnemyWithPath(Enemy enemy, @Null SpawnTile spawnTile, @Null Path path, int i, @Null Wave wave, float f2) {
        this.S.gameState.checkGameplayUpdateAllowed();
        enemy.spawnTile = spawnTile;
        enemy.wave = wave;
        registerWithPath(enemy, path, i, f2);
        this.S.loot.fillWithLoot(enemy);
        this.S.map.spawnEnemy(enemy);
        enemy.passedTiles = f2;
        enemy.setPositionToPath();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f2) {
        int i;
        byte calculateMoveSides;
        long realTickCount = Game.getRealTickCount();
        this.f2936a.begin();
        int i2 = this.f2936a.size;
        for (int i3 = 0; i3 < i2; i3++) {
            Enemy enemy = this.f2936a.items[i3].enemy;
            if (enemy == null || enemy.disabled.isTrue() || !enemy.dynamicPathfindingAllowed()) {
                this.f2936a.removeIndex(i3);
            } else {
                if (enemy.passedTiles < -0.499999f) {
                    enemy.passedTiles = -0.499999f;
                }
                int i4 = (int) (enemy.passedTiles + 0.5f);
                float passedTilesDelta = enemy.getPassedTilesDelta(f2);
                if (passedTilesDelta >= 0.0f && i4 != (i = (int) (enemy.passedTiles + 0.5f + passedTilesDelta))) {
                    PathNode pathNode = null;
                    if (enemy.graphPath.getCount() > i) {
                        pathNode = enemy.graphPath.getByIdx(i);
                    }
                    Tile currentTile = enemy.getCurrentTile();
                    Tile tile = currentTile;
                    if (currentTile == null) {
                        tile = enemy.spawnTile;
                    }
                    byte moveSideByPassedTiles = enemy.graphPath.getMoveSideByPassedTiles(enemy.passedTiles);
                    if (tile == null) {
                        this.f2936a.removeIndex(i3);
                    } else {
                        enemy.graphPath = this.S.pathfinding.findPathToTargetTile(tile, enemy.getTypeForPathfinding());
                        if (pathNode != null) {
                            if (enemy.graphPath.getByIdx(1).getX() != pathNode.getX() || enemy.graphPath.getByIdx(1).getY() != pathNode.getY()) {
                                try {
                                    calculateMoveSides = MoveSide.calculateMoveSides(enemy.graphPath.getByIdx(0), pathNode, enemy.graphPath.getByIdx(1));
                                } catch (Exception unused) {
                                    calculateMoveSides = MoveSide.calculateMoveSides(enemy.graphPath.getByIdx(0), null, enemy.graphPath.getByIdx(1));
                                }
                                enemy.graphPath = enemy.graphPath.copyWithStartingMoveSide(calculateMoveSides);
                                enemy.passedTiles = -0.4999f;
                            } else {
                                enemy.graphPath = enemy.graphPath.copyWithStartingMoveSide(moveSideByPassedTiles);
                                enemy.passedTiles -= (int) enemy.passedTiles;
                            }
                        }
                        enemy.pathSearches++;
                        this.f2936a.removeIndex(i3);
                        if (enemy.passedTiles < 0.0f) {
                            enemy.sideShiftIndex = (11 - enemy.sideShiftIndex) - 1;
                        }
                    }
                }
            }
        }
        this.f2936a.end();
        if (Game.i.debugManager != null) {
            Game.i.debugManager.registerFrameJob("Pathfinding", Game.getRealTickCount() - realTickCount);
        }
        EntityUtils.removeNullRefs(this.S.map.spawnedEnemies);
        this.S.TSH.sort.sort(this.S.map.spawnedEnemies, f);
        this.S.map.spawnedEnemies.begin();
        int i5 = this.S.map.spawnedEnemies.size;
        for (int i6 = 0; i6 < i5; i6++) {
            Enemy enemy2 = this.S.map.spawnedEnemies.items[i6].enemy;
            if (enemy2 != null) {
                if (enemy2.disabled.isTrue()) {
                    enemy2.update(f2);
                } else if (enemy2.graphPath == null) {
                    if (enemy2.velocity != null) {
                        enemy2.getPosition().add(enemy2.velocity.x * f2 * enemy2.getBuffedSpeed() * 128.0f, enemy2.velocity.y * f2 * enemy2.getBuffedSpeed() * 128.0f);
                    }
                    enemy2.update(f2);
                } else {
                    Path path = enemy2.graphPath;
                    float passedTilesDelta2 = enemy2.getPassedTilesDelta(f2);
                    enemy2.passedTiles += passedTilesDelta2;
                    enemy2.sumPassedTiles += passedTilesDelta2;
                    if (enemy2.passedTiles < -0.49999f) {
                        enemy2.sumPassedTiles += (-0.5f) - enemy2.passedTiles;
                        enemy2.passedTiles = -0.5f;
                    }
                    if (enemy2.passedTiles >= path.getLengthInTiles()) {
                        float baseDamage = enemy2.getBaseDamage();
                        if (enemy2.hasBuffsByType(BuffType.NO_DAMAGE)) {
                            baseDamage = 0.0f;
                        }
                        int i7 = 0;
                        if (baseDamage > 0.0f) {
                            if (baseDamage % 1.0f == 0.0f) {
                                i7 = MathUtils.round(baseDamage);
                            } else {
                                i7 = (int) baseDamage;
                                if (this.S.gameState.randomFloat() < baseDamage % 1.0f) {
                                    i7++;
                                }
                            }
                        }
                        EnemyReachTarget enemyReachTarget = new EnemyReachTarget(enemy2, baseDamage, i7);
                        this.S.events.getListeners(EnemyReachTarget.class).trigger(enemyReachTarget);
                        if (!enemyReachTarget.isCancelled()) {
                            this.S.map.despawnEnemy(enemy2);
                        } else {
                            enemy2.setPositionToPath();
                            enemy2.update(f2);
                        }
                    } else if (enemy2.passedTiles >= -0.5f) {
                        enemy2.setPositionToPath();
                        enemy2.update(f2);
                    } else {
                        throw new IllegalStateException(enemy2.passedTiles + " passed tiles");
                    }
                }
            }
        }
        this.S.map.spawnedEnemies.end();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Enemy";
    }

    public final boolean isEmojiEnemies() {
        return this.d;
    }

    public final Color getColor(EnemyType enemyType) {
        return this.enemyColor[enemyType.ordinal()];
    }

    public final TextureRegion getTexture(EnemyType enemyType) {
        return this.enemyTexture[enemyType.ordinal()];
    }

    public final TextureRegion getHighlightTexture(EnemyType enemyType) {
        return this.enemyHighlightTexture[enemyType.ordinal()];
    }

    public final TextureRegion getEmojiTexture(EnemyType enemyType) {
        return this.enemyEmojiTexture[enemyType.ordinal()];
    }

    public final void draw(Batch batch, float f2, float f3) {
        this.i.clear();
        this.i.addAll(this.S.map.spawnedEnemies);
        EntityUtils.removeNullRefs(this.i);
        Threads.sortGdxArray(this.i, f);
        for (int i = 0; i < this.i.size; i++) {
            this.i.items[i].enemy.applyDrawInterpolation(f3);
        }
        CameraController cameraController = this.S._input.cameraController;
        for (int i2 = this.i.size - 1; i2 >= 0; i2--) {
            Enemy enemy = this.i.items[i2].enemy;
            if (!enemy.hasDrawPriority() && !enemy.invisible.isTrue() && cameraController.isPointVisible(enemy.drawPosition, enemy.getSize())) {
                enemy.drawBatch(batch, f2);
            }
        }
        for (int i3 = this.i.size - 1; i3 >= 0; i3--) {
            Enemy enemy2 = this.i.items[i3].enemy;
            if (cameraController.isPointVisible(enemy2.drawPosition, enemy2.getSize()) && enemy2.hasDrawPriority() && !enemy2.invisible.isTrue()) {
                enemy2.drawBatch(batch, f2);
            }
        }
        if (Game.i.settingsManager.isParticlesDrawing()) {
            batch.flush();
            batch.setBlendFunction(770, 1);
            for (int i4 = this.i.size - 1; i4 >= 0; i4--) {
                Enemy enemy3 = this.i.items[i4].enemy;
                if (cameraController.isPointVisible(enemy3.drawPosition, enemy3.getSize()) && !enemy3.invisible.isTrue()) {
                    enemy3.drawBatchAdditive(batch, f2);
                }
            }
            batch.flush();
            batch.setBlendFunction(770, 771);
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_ENEMIES_INFO) != 0.0d) {
            BitmapFont smallDebugFont = Game.i.assetManager.getSmallDebugFont();
            int intValue = this.S.gameValue.getIntValue(GameValueType.ENEMIES_MAX_PATH_SEARCHES);
            smallDebugFont.setColor(MaterialColor.BLUE.P500);
            int i5 = this.i.size;
            for (int i6 = 0; i6 < i5; i6++) {
                Enemy enemy4 = this.i.items[i6].enemy;
                smallDebugFont.draw(batch, "PT:" + (StrictMath.round(enemy4.passedTiles * 100.0f) / 100.0f) + " SPD:" + (StrictMath.round(enemy4.getBuffedSpeed() * 100.0f) * 0.01f) + " PS: " + enemy4.pathSearches + "/" + intValue, enemy4.getPosition().x - 50.0f, enemy4.getPosition().y + 30.0f, 100.0f, 1, false);
                smallDebugFont.draw(batch, "EN:" + enemy4.otherEnemiesNearby, enemy4.getPosition().x - 50.0f, enemy4.getPosition().y + 45.0f, 100.0f, 1, false);
            }
            smallDebugFont.setColor(Color.WHITE);
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_UNITS_BBOX) != 0.0d) {
            batch.end();
            Game.i.renderingManager.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            Game.i.renderingManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Game.i.renderingManager.shapeRenderer.setColor(MaterialColor.ORANGE.P500.cpy());
            Game.i.renderingManager.shapeRenderer.getColor().f889a = 0.5f;
            int i7 = this.i.size;
            for (int i8 = 0; i8 < i7; i8++) {
                Enemy enemy5 = this.i.items[i8].enemy;
                Game.i.renderingManager.shapeRenderer.circle(enemy5.getPosition().x, enemy5.getPosition().y, enemy5.getSize());
            }
            Game.i.renderingManager.shapeRenderer.end();
            batch.begin();
        }
        batch.setColor(Color.WHITE);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_ENEMY_PATHS) != 0.0d) {
            batch.end();
            Game.i.renderingManager.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            Game.i.renderingManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            int i9 = this.i.size;
            for (int i10 = 0; i10 < i9; i10++) {
                Enemy enemy6 = this.i.items[i10].enemy;
                if (enemy6.graphPath != null) {
                    Vector2 vector2 = new Vector2();
                    enemy6.graphPath.getPosition(0.0f, enemy6.sideShiftIndex, vector2);
                    double d = 0.0d;
                    while (d <= enemy6.graphPath.getLengthInTiles()) {
                        float f4 = vector2.x;
                        float f5 = vector2.y;
                        d += 0.02500000037252903d;
                        enemy6.graphPath.getPosition((float) d, enemy6.sideShiftIndex, vector2);
                        if (d < enemy6.passedTiles) {
                            ShapeRenderer shapeRenderer = Game.i.renderingManager.shapeRenderer;
                            float f6 = vector2.x;
                            float f7 = vector2.y;
                            Color color = MaterialColor.CYAN.P500;
                            shapeRenderer.line(f4, f5, f6, f7, color, color);
                        } else {
                            ShapeRenderer shapeRenderer2 = Game.i.renderingManager.shapeRenderer;
                            float f8 = vector2.x;
                            float f9 = vector2.y;
                            Color color2 = MaterialColor.PURPLE.P500;
                            shapeRenderer2.line(f4, f5, f8, f9, color2, color2);
                        }
                    }
                }
            }
            Game.i.renderingManager.shapeRenderer.end();
            batch.begin();
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d && this.S._gameMapSelection.getSelectedTile() != null && this.S._gameMapSelection.getSelectedTile().type == TileType.PLATFORM) {
            PlatformTile platformTile = (PlatformTile) this.S._gameMapSelection.getSelectedTile();
            if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                Tower tower = (Tower) platformTile.building;
                BitmapFont debugFont = Game.i.assetManager.getDebugFont(false);
                for (int i11 = 0; i11 < this.i.size; i11++) {
                    Enemy enemy7 = this.i.items[i11].enemy;
                    int enemyPriority = tower.getEnemyPriority(enemy7);
                    debugFont.setColor(Color.RED);
                    debugFont.draw(batch, "P:" + enemyPriority, enemy7.getPosition().x - 8.0f, enemy7.getPosition().y, 16.0f, 1, false);
                }
                debugFont.setColor(Color.WHITE);
            }
        }
        this.i.clear();
    }

    public final void drawEnemyHealth(Batch batch) {
        this.i.clear();
        this.i.addAll(this.S.map.spawnedEnemies);
        Threads.sortGdxArray(this.i, f);
        CameraController cameraController = this.S._input.cameraController;
        int i = this.i.size;
        for (int i2 = 0; i2 < i; i2++) {
            Enemy enemy = this.i.items[i2].enemy;
            if (enemy != null && !enemy.hasDrawPriority() && cameraController.isPointVisible(enemy.drawPosition, enemy.getSize()) && enemy.healthBarScale != 0.0f && !enemy.healthBarInvisible) {
                enemy.drawHealth(batch);
            }
        }
        int i3 = this.i.size;
        for (int i4 = 0; i4 < i3; i4++) {
            Enemy enemy2 = this.i.items[i4].enemy;
            if (enemy2 != null && enemy2.hasDrawPriority() && cameraController.isPointVisible(enemy2.drawPosition, enemy2.getSize()) && enemy2.healthBarScale != 0.0f && !enemy2.healthBarInvisible) {
                enemy2.drawHealth(batch);
            }
        }
        this.i.clear();
        batch.setColor(Color.WHITE);
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.f2936a.clear();
        Game.i.debugManager.unregisterValue("Towers MDPS");
        super.dispose();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/EnemySystem$OnPathfindingRebuild.class */
    public static class OnPathfindingRebuild extends SerializableListener<EnemySystem> implements Listener<PathfindingRebuild> {
        /* synthetic */ OnPathfindingRebuild(EnemySystem enemySystem, byte b2) {
            this(enemySystem);
        }

        private OnPathfindingRebuild() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnPathfindingRebuild(EnemySystem enemySystem) {
            this.f1759a = enemySystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(PathfindingRebuild pathfindingRebuild) {
            if (pathfindingRebuild.isDefaultPathsAffected()) {
                ((EnemySystem) this.f1759a).queueAllEnemiesPathfinding();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/EnemySystem$OnEnemyDespawn.class */
    public static class OnEnemyDespawn extends SerializableListener<EnemySystem> implements Listener<EnemyDespawn> {
        /* synthetic */ OnEnemyDespawn(EnemySystem enemySystem, byte b2) {
            this(enemySystem);
        }

        private OnEnemyDespawn() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnEnemyDespawn(EnemySystem enemySystem) {
            this.f1759a = enemySystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(EnemyDespawn enemyDespawn) {
            ((EnemySystem) this.f1759a).b(enemyDespawn.getEnemy());
        }
    }
}
