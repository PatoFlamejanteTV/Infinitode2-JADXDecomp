package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.actions.BuildMinerAction;
import com.prineside.tdi2.actions.GlobalUpgradeMinerAction;
import com.prineside.tdi2.actions.SellMinerAction;
import com.prineside.tdi2.actions.UpgradeMinerAction;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.MinerBuild;
import com.prineside.tdi2.events.game.MinerRemove;
import com.prineside.tdi2.events.game.MinerResourceChange;
import com.prineside.tdi2.events.game.MinerSell;
import com.prineside.tdi2.events.game.MinerUpgrade;
import com.prineside.tdi2.events.game.NextWaveForce;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MinerSystem.class */
public final class MinerSystem extends GameSystem {
    private int d = 1;
    public DelayedRemovalArray<Miner> miners = new DelayedRemovalArray<>(false, 8, Miner.class);
    private int[] e = new int[MinerType.values.length];
    public float bonusDoubleMiningSpeedTimeLeft = 0.0f;

    @NAGS
    private final float[][] f = new float[ResourceType.values.length][3];

    @NAGS
    private final Array<Sprite>[] g = new Array[ResourceType.values.length];

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3002a = TLog.forClass(MinerSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private static final GameValueType[] f3003b = {GameValueType.MINER_COUNT_SCALAR, GameValueType.MINER_COUNT_VECTOR, GameValueType.MINER_COUNT_MATRIX, GameValueType.MINER_COUNT_TENSOR, GameValueType.MINER_COUNT_INFIAR};
    private static final float[] c = {1.0f, 1.2f, 1.5f, 1.9f, 2.4f, 3.0f, 3.6f, 4.2f, 4.9f, 5.6f, 6.5f};
    private static final float[] h = new float[8];

    static {
        for (int i = 0; i < h.length; i++) {
            h[i] = (float) StrictMath.pow(r1 + 1, 0.63092975357d);
        }
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.d, true);
        kryo.writeObject(output, this.miners);
        kryo.writeObject(output, this.e);
        output.writeFloat(this.bonusDoubleMiningSpeedTimeLeft);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readVarInt(true);
        this.miners = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.e = (int[]) kryo.readObject(input, int[].class);
        this.bonusDoubleMiningSpeedTimeLeft = input.readFloat();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(NextWaveForce.class).addStateAffecting(new OnNextWaveForce(this, (byte) 0));
        this.S.events.getListeners(MinerRemove.class).addStateAffecting(new OnMinerRemove(this, (byte) 0)).setDescription("MinerSystem - unregisters the miner");
        if (!this.S.CFG.headless) {
            a();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    public final float getBonusDoubleMiningSpeedTimeLeft() {
        return this.bonusDoubleMiningSpeedTimeLeft;
    }

    public final boolean isRegistered(Tower tower) {
        return tower.isRegistered();
    }

    public final void register(Miner miner) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (miner.isRegistered()) {
            throw new IllegalStateException("Miner is already registered");
        }
        int[] iArr = this.e;
        int ordinal = miner.type.ordinal();
        iArr[ordinal] = iArr[ordinal] + 1;
        int i = this.d;
        this.d = i + 1;
        miner.id = i;
        miner.setRegistered(this.S);
        this.miners.add(miner);
    }

    public final void unregister(Miner miner) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (!miner.isRegistered()) {
            throw new IllegalArgumentException("Miner is not registered");
        }
        if (miner.doubleSpeedParticle != null) {
            miner.doubleSpeedParticle.allowCompletion();
            miner.doubleSpeedParticle = null;
        }
        int[] iArr = this.e;
        int ordinal = miner.type.ordinal();
        iArr[ordinal] = iArr[ordinal] - 1;
        miner.setUnregistered();
        this.miners.removeValue(miner, true);
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MINER_DRAW_BATCH, false, (batch, f, f2, f3) -> {
            drawBatch(batch, this.S.map.getMap(), f2, this.S._mapRendering.getDrawMode());
        }).setName("Miner-drawBatch"));
    }

    public final void addResources(Miner miner, ResourceType resourceType, int i, boolean z) {
        this.S.gameState.checkGameplayUpdateAllowed();
        MinerResourceChange minerResourceChange = new MinerResourceChange(miner, resourceType, i, z);
        this.S.events.trigger(minerResourceChange);
        if (minerResourceChange.isCancelled()) {
            return;
        }
        ResourceType resourceType2 = minerResourceChange.getResourceType();
        int delta = minerResourceChange.getDelta();
        Miner miner2 = minerResourceChange.getMiner();
        int resourcesCount = miner2.getTile().getResourcesCount(resourceType2);
        int i2 = Integer.MAX_VALUE - miner2.minedResources[resourceType2.ordinal()];
        if (this.S.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS) {
            i2 = resourcesCount - miner2.getTile().minedResources[resourceType2.ordinal()];
        }
        int[] iArr = miner2.minedResources;
        int ordinal = resourceType2.ordinal();
        iArr[ordinal] = iArr[ordinal] + delta;
        int[] iArr2 = miner2.getTile().minedResources;
        int ordinal2 = resourceType2.ordinal();
        iArr2[ordinal2] = iArr2[ordinal2] + delta;
        if (i2 > 0) {
            this.S.gameState.addResources(resourceType2, Math.min(i2, delta));
        }
    }

    public final boolean removeResources(Miner miner, ResourceType resourceType, int i) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (i == 0) {
            throw new IllegalArgumentException("Amount can not be 0");
        }
        if (miner.minedResources[resourceType.ordinal()] >= i) {
            MinerResourceChange minerResourceChange = new MinerResourceChange(miner, resourceType, -i, false);
            this.S.events.trigger(minerResourceChange);
            if (minerResourceChange.isCancelled()) {
                return false;
            }
            Miner miner2 = minerResourceChange.getMiner();
            ResourceType resourceType2 = minerResourceChange.getResourceType();
            int i2 = -minerResourceChange.getDelta();
            int resourcesCount = miner2.getTile().getResourcesCount(resourceType2);
            int i3 = 0;
            if (this.S.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS) {
                int i4 = miner2.getTile().minedResources[resourceType2.ordinal()] - resourcesCount;
                i3 = i4;
                if (i4 < 0) {
                    i3 = 0;
                }
            }
            int i5 = i2 - i3;
            if (i5 > 0) {
                this.S.gameState.removeResources(resourceType2, i5);
            }
            int[] iArr = miner2.minedResources;
            int ordinal = resourceType2.ordinal();
            iArr[ordinal] = iArr[ordinal] - i2;
            int[] iArr2 = miner2.getTile().minedResources;
            int ordinal2 = resourceType2.ordinal();
            iArr2[ordinal2] = iArr2[ordinal2] - i2;
            return true;
        }
        return false;
    }

    public final int getMaxUpgradeLevel(MinerType minerType) {
        int intValue = this.S.gameValue.getIntValue(GameValueType.MINERS_MAX_UPGRADE_LEVEL);
        switch (minerType) {
            case SCALAR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_SCALAR_MAX_UPGRADE_LEVEL);
                break;
            case VECTOR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_VECTOR_MAX_UPGRADE_LEVEL);
                break;
            case MATRIX:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_MATRIX_MAX_UPGRADE_LEVEL);
                break;
            case TENSOR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_TENSOR_MAX_UPGRADE_LEVEL);
                break;
            case INFIAR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_INFIAR_MAX_UPGRADE_LEVEL);
                break;
        }
        return Math.min(intValue, 10);
    }

    public final int getGlobalUpgradePrice(MinerType minerType) {
        int i = 0;
        for (int i2 = 0; i2 < this.miners.size; i2++) {
            Miner miner = this.miners.items[i2];
            if (miner.type == minerType && miner.getUpgradeLevel() < getMaxUpgradeLevel(miner.type)) {
                int upgradeLevel = miner.getUpgradeLevel() + 1;
                float f = 1.0f;
                for (int i3 = 0; i3 < this.miners.size; i3++) {
                    Miner miner2 = this.miners.items[i3];
                    if (miner2.type == minerType) {
                        int upgradeLevel2 = miner2.getUpgradeLevel();
                        if (i3 < i2 && upgradeLevel2 < getMaxUpgradeLevel(miner2.type)) {
                            upgradeLevel2++;
                        }
                        if (upgradeLevel2 >= upgradeLevel) {
                            f *= 1.25f;
                        }
                    }
                }
                i += (int) (miner.getBaseUpgradePrice(upgradeLevel) * f);
            }
        }
        return i;
    }

    public final int getMaxMinersCount(MinerType minerType) {
        return this.S.gameValue.getIntValue(f3003b[minerType.ordinal()]);
    }

    private int a(MinerType minerType) {
        return this.e[minerType.ordinal()];
    }

    public final int getBuildableMinersCount(MinerType minerType) {
        return Math.max(0, getMaxMinersCount(minerType) - this.e[minerType.ordinal()]);
    }

    public final int getBuildPrice(MinerType minerType) {
        return (int) (Game.i.minerManager.getFactory(minerType).getBaseBuildPrice(this.S.gameValue) * StrictMath.pow(1.600000023841858d, a(minerType)));
    }

    public final int getUpgradePrice(Miner miner) {
        int upgradeLevel = miner.getUpgradeLevel() + 1;
        if (upgradeLevel > getMaxUpgradeLevel(miner.type)) {
            return 0;
        }
        float f = 1.0f;
        for (int i = 0; i < this.miners.size; i++) {
            if (this.miners.get(i).type == miner.type && this.miners.get(i).getUpgradeLevel() >= upgradeLevel) {
                f *= 1.25f;
            }
        }
        return (int) (miner.getBaseUpgradePrice(upgradeLevel) * f);
    }

    public final void upgradeMinerAction(Miner miner) {
        upgradeMinerActionAt(miner.getTile().getX(), miner.getTile().getY());
    }

    public final void upgradeMinerActionAt(int i, int i2) {
        Tile tile = this.S.map.getMap().getTile(i, i2);
        if (tile != null && tile.type == TileType.SOURCE) {
            SourceTile sourceTile = (SourceTile) tile;
            if (sourceTile.miner != null) {
                Miner miner = sourceTile.miner;
                if (miner.getUpgradeLevel() >= getMaxUpgradeLevel(miner.type)) {
                    return;
                }
                if (this.S.gameState.getMoney() >= getUpgradePrice(miner)) {
                    this.S.gameState.pushActionNextUpdate(new UpgradeMinerAction(i, i2));
                }
            }
        }
    }

    public final void globalUpgradeMinerAction(MinerType minerType) {
        this.S.gameState.pushActionNextUpdate(new GlobalUpgradeMinerAction(minerType));
    }

    public final boolean upgradeMiner(Miner miner) {
        return upgradeMinerAt(miner.getTile().getX(), miner.getTile().getY());
    }

    public final boolean upgradeMinerAt(int i, int i2) {
        this.S.gameState.checkGameplayUpdateAllowed();
        Tile tile = this.S.map.getMap().getTile(i, i2);
        if (tile != null) {
            if (tile.type != TileType.SOURCE) {
                f3002a.e("upgradeMiner - tile is " + tile.type.name(), new Object[0]);
                return false;
            }
            SourceTile sourceTile = (SourceTile) tile;
            if (sourceTile.miner != null) {
                Miner miner = sourceTile.miner;
                if (miner.getUpgradeLevel() >= getMaxUpgradeLevel(miner.type)) {
                    f3002a.e("can't upgrade miner, it is already fully upgraded", new Object[0]);
                    return false;
                }
                int upgradePrice = getUpgradePrice(miner);
                if (this.S.gameState.removeMoney(upgradePrice)) {
                    miner.moneySpentOn += upgradePrice;
                    miner.setUpgradeLevel(miner.getUpgradeLevel() + 1);
                    if (miner.nextMinedResourceType != null) {
                        miner.miningTime *= getMiningSpeed(miner, miner.getUpgradeLevel() - 1) / getMiningSpeed(miner, miner.getUpgradeLevel());
                    }
                    this.S.events.trigger(new MinerUpgrade(miner, upgradePrice));
                    return true;
                }
                f3002a.e("not enough money to upgrade the miner", new Object[0]);
                return false;
            }
            f3002a.e("upgradeMiner - no miner on tile", new Object[0]);
            return false;
        }
        f3002a.e("upgradeMiner - tile is null", new Object[0]);
        return false;
    }

    public final void buildMinerActionForSelectedTile(MinerType minerType) {
        Tile selectedTile = this.S._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.SOURCE && ((SourceTile) selectedTile).miner == null) {
            buildMinerActionAt(minerType, selectedTile.getX(), selectedTile.getY());
        }
    }

    public final void buildMinerActionAt(MinerType minerType, int i, int i2) {
        Tile tile;
        if (this.e[minerType.ordinal()] + 1 <= getMaxMinersCount(minerType) && (tile = this.S.map.getMap().getTile(i, i2)) != null && tile.type == TileType.SOURCE && ((SourceTile) tile).miner == null) {
            if (this.S.gameState.getMoney() >= getBuildPrice(minerType)) {
                this.S.gameState.pushActionNextUpdate(new BuildMinerAction(minerType, i, i2));
            }
        }
    }

    @Null
    public final Miner buildMiner(MinerType minerType, int i, int i2, boolean z, boolean z2) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (z && this.e[minerType.ordinal()] + 1 > getMaxMinersCount(minerType)) {
            f3002a.e("No more miners of type " + minerType.name() + " can be built", new Object[0]);
            return null;
        }
        Tile tile = this.S.map.getMap().getTile(i, i2);
        if (tile != null) {
            if (tile.type != TileType.SOURCE) {
                f3002a.e("buildMiner - tile type is " + tile.type.name(), new Object[0]);
                return null;
            }
            if (((SourceTile) tile).miner == null) {
                Miner create = Game.i.minerManager.getFactory(minerType).create();
                int i3 = 0;
                if (z2) {
                    i3 = getBuildPrice(minerType);
                }
                if (this.S.gameState.removeMoney(i3)) {
                    register(create);
                    create.setInstallTime(create.getInstallDuration());
                    create.moneySpentOn = i3;
                    this.S.map.setMiner(tile.getX(), tile.getY(), create);
                    this.S.map.updateDirtyTiles();
                    this.S.events.trigger(new MinerBuild(create, i3));
                    if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                        ParticleEffectPool.PooledEffect obtain = Game.i.minerManager.highlightParticles[create.type.ordinal()].obtain();
                        obtain.setPosition(create.getTile().center.x, create.getTile().center.y);
                        this.S._particle.addParticle(obtain, true);
                    }
                    return create;
                }
                f3002a.e("not enough money to build a miner", new Object[0]);
                return null;
            }
            f3002a.e("trying to build miner on tile which already has a miner", new Object[0]);
            return null;
        }
        f3002a.e("buildMiner - tile is null", new Object[0]);
        return null;
    }

    public final void sellMinerAction(Miner miner) {
        sellMinerActionAt(miner.getTile().getX(), miner.getTile().getY());
    }

    public final void sellMinerActionAt(int i, int i2) {
        this.S.gameState.pushActionNextUpdate(new SellMinerAction(i, i2));
    }

    public final boolean sellMiner(int i, int i2) {
        this.S.gameState.checkGameplayUpdateAllowed();
        Tile tile = this.S.map.getMap().getTile(i, i2);
        if (tile != null) {
            if (tile.type != TileType.SOURCE) {
                f3002a.e("sellMiner - tile is " + tile.type.name(), new Object[0]);
                return false;
            }
            Miner miner = ((SourceTile) tile).miner;
            if (miner != null) {
                int sellPrice = miner.getSellPrice();
                this.S.gameState.addMoney(sellPrice, false);
                this.S.map.removeMiner(miner);
                this.S.events.trigger(new MinerSell(miner, sellPrice));
                return true;
            }
            f3002a.e("sellMiner - miner is not on tile", new Object[0]);
            return false;
        }
        f3002a.e("sellMiner - tile is null", new Object[0]);
        return false;
    }

    public final int getResourceMinedRawScore(ResourceType resourceType) {
        return 10 + (resourceType.ordinal() * 5);
    }

    public final float calculateScorePerMinute(Miner miner) {
        SourceTile tile = miner.getTile();
        if (tile == null) {
            return 0.0f;
        }
        Miner.Factory<? extends Miner> factory = Game.i.minerManager.getFactory(miner.type);
        int i = 0;
        for (ResourceType resourceType : ResourceType.values) {
            if (factory.canMineResource(resourceType)) {
                i += tile.getResourcesCount(resourceType);
            }
        }
        if (i == 0) {
            return 0.0f;
        }
        float miningSpeed = getMiningSpeed(miner, miner.getUpgradeLevel()) * 60.0f;
        float f = 0.0f;
        for (ResourceType resourceType2 : ResourceType.values) {
            if (factory.canMineResource(resourceType2)) {
                f += miningSpeed * ((int) this.S.gameState.calculateFinalScore(getResourceMinedRawScore(r0), StatisticsType.SG_RM)) * (tile.getResourcesCount(r0) / i);
            }
        }
        if (tile.getResourceDensity() < 1.0f) {
            f *= tile.getResourceDensity();
        }
        if (miner.doubleSpeedTimeLeft > 0.0f) {
            f *= 2.0f;
        }
        return f;
    }

    public final float getBaseMiningSpeed(Miner miner, int i) {
        return (Game.i.minerManager.getFactory(miner.type).getBaseMiningSpeed(this.S.gameValue) * c[i]) / 60.0f;
    }

    public final int getMiningSpeedModifierCount(Miner miner) {
        int i = 0;
        for (int i2 = 0; i2 < miner.nearbyModifiers.size; i2++) {
            if (miner.nearbyModifiers.get(i2).type == ModifierType.MINING_SPEED) {
                i++;
            }
        }
        return i;
    }

    public final float getMiningSpeedModifierEfficiencyPerCount(int i) {
        if (i <= h.length) {
            return h[i - 1];
        }
        return (float) StrictMath.pow(i, 0.63092975357d);
    }

    public final float getMiningSpeedModifierMultiplier(Miner miner) {
        int miningSpeedModifierCount = getMiningSpeedModifierCount(miner);
        if (miningSpeedModifierCount == 0) {
            return 1.0f;
        }
        return 1.0f + (getMiningSpeedModifierEfficiencyPerCount(miningSpeedModifierCount) * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_MINING_SPEED_VALUE)));
    }

    public final float getMiningSpeed(Miner miner, int i) {
        float baseMiningSpeed = getBaseMiningSpeed(miner, i);
        if (baseMiningSpeed <= 0.0f || baseMiningSpeed >= 2.1474836E9f) {
            throw new IllegalStateException("Base mining speed for " + miner.type.name() + " L" + i + " is " + baseMiningSpeed);
        }
        float miningSpeedModifierMultiplier = baseMiningSpeed * getMiningSpeedModifierMultiplier(miner);
        SourceTile tile = miner.getTile();
        if (tile != null) {
            miningSpeedModifierMultiplier *= tile.getResourceDensity();
        }
        if (miningSpeedModifierMultiplier < 0.0f || miningSpeedModifierMultiplier >= 2.1474836E9f) {
            throw new IllegalStateException("Base mining speed for " + miner.type.name() + " L" + i + " is " + miningSpeedModifierMultiplier);
        }
        if (this.S.gameState.difficultyMode == DifficultyMode.EASY) {
            miningSpeedModifierMultiplier *= 0.75f;
        }
        if (this.bonusDoubleMiningSpeedTimeLeft > 0.0f) {
            miningSpeedModifierMultiplier *= 2.0f;
        }
        return miningSpeedModifierMultiplier;
    }

    private void a(Miner miner) {
        this.S.gameState.checkGameplayUpdateAllowed();
        SourceTile tile = miner.getTile();
        int i = 0;
        for (ResourceType resourceType : ResourceType.values) {
            if (Game.i.minerManager.getFactory(miner.type).canMineResource(resourceType)) {
                i += tile.getResourcesCount(resourceType);
            }
        }
        if (i > 0) {
            int randomInt = this.S.gameState.randomInt(i);
            int i2 = 0;
            for (ResourceType resourceType2 : ResourceType.values) {
                if (Game.i.minerManager.getFactory(miner.type).canMineResource(resourceType2)) {
                    int resourcesCount = tile.getResourcesCount(resourceType2);
                    if (randomInt >= i2 && randomInt < i2 + resourcesCount) {
                        miner.nextMinedResourceType = resourceType2;
                        return;
                    }
                    i2 += resourcesCount;
                }
            }
            return;
        }
        miner.nextMinedResourceType = null;
    }

    private void b(Miner miner) {
        Array<Sprite> array;
        ResourceType resourceType = miner.nextMinedResourceType;
        if (resourceType != null) {
            float miningSpeed = 1.0f / getMiningSpeed(miner, miner.getUpgradeLevel());
            int i = (int) (miner.miningTime / miningSpeed);
            miner.miningTime -= i * miningSpeed;
            int min = Math.min(i, miner.loopAbilityResourceBuffer);
            addResources(miner, resourceType, i + min, true);
            miner.loopAbilityResourceBuffer -= min;
            if (miner.loopAbilityResourceBuffer <= 0) {
                miner.affectedByLoopAbility = null;
            }
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                ParticleEffectPool.PooledEffect obtain = Game.i.minerManager.minedResourceParticleEffectPool.obtain();
                if (this.g[resourceType.ordinal()] != null) {
                    array = this.g[resourceType.ordinal()];
                } else {
                    array = new Array<>(new Sprite[]{new Sprite(Game.i.assetManager.getTextureRegion(Resource.TEXTURE_REGION_NAMES[resourceType.ordinal()]))});
                    this.g[resourceType.ordinal()] = array;
                }
                obtain.getEmitters().get(1).setSprites(array);
                Array<ParticleEmitter> emitters = obtain.getEmitters();
                float f = this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED ? 0.28f : 1.0f;
                for (int i2 = 0; i2 < emitters.size; i2++) {
                    Color color = Game.i.resourceManager.getColor(resourceType);
                    float[] fArr = this.f[resourceType.ordinal()];
                    fArr[0] = color.r * f;
                    fArr[1] = color.g * f;
                    fArr[2] = color.f888b * f;
                    emitters.get(i2).getTint().setColors(fArr);
                }
                obtain.setPosition(miner.getTile().center.x, miner.getTile().center.y);
                this.S._particle.addLimitedParticle(obtain, LimitedParticleType.RESOURCE_MINED, miner.getTile().center.x, miner.getTile().center.y);
            }
        }
        a(miner);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        StateSystem.ActionsArray currentUpdateActions = this.S.gameState.getCurrentUpdateActions();
        if (currentUpdateActions != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                Action action = currentUpdateActions.actions[i];
                if (action.getType() != ActionType.BM) {
                    if (action.getType() != ActionType.UM) {
                        if (action.getType() != ActionType.GUM) {
                            if (action.getType() == ActionType.SM) {
                                SellMinerAction sellMinerAction = (SellMinerAction) action;
                                if (sellMiner(sellMinerAction.x, sellMinerAction.y)) {
                                    this.S.gameState.registerPlayerActivity();
                                }
                            }
                        } else {
                            GlobalUpgradeMinerAction globalUpgradeMinerAction = (GlobalUpgradeMinerAction) action;
                            boolean z = false;
                            for (int i2 = 0; i2 < this.miners.size; i2++) {
                                Miner miner = this.miners.items[i2];
                                if (miner.type == globalUpgradeMinerAction.minerType && upgradeMiner(miner)) {
                                    z = true;
                                }
                            }
                            if (z) {
                                this.S.gameState.registerPlayerActivity();
                            }
                        }
                    } else {
                        UpgradeMinerAction upgradeMinerAction = (UpgradeMinerAction) action;
                        if (upgradeMinerAt(upgradeMinerAction.x, upgradeMinerAction.y)) {
                            this.S.gameState.registerPlayerActivity();
                        }
                    }
                } else {
                    BuildMinerAction buildMinerAction = (BuildMinerAction) action;
                    if (buildMiner(buildMinerAction.minerType, buildMinerAction.x, buildMinerAction.y, true, true) != null) {
                        this.S.gameState.registerPlayerActivity();
                    }
                }
            }
        }
        if (this.S.gameState.isGameRealTimePasses()) {
            int i3 = this.miners.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Miner miner2 = this.miners.items[i4];
                float tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime();
                if (miner2.doubleSpeedTimeLeft != 0.0f) {
                    tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime() * 2.0f;
                    miner2.doubleSpeedTimeLeft -= this.S.gameValue.getTickRateDeltaTime();
                    if (miner2.doubleSpeedTimeLeft <= 0.0f) {
                        miner2.doubleSpeedTimeLeft = 0.0f;
                        if (miner2.doubleSpeedParticle != null) {
                            miner2.doubleSpeedParticle.allowCompletion();
                            miner2.doubleSpeedParticle = null;
                        }
                    }
                }
                if (!miner2.isPrepared()) {
                    try {
                        miner2.reduceInstallTime(tickRateDeltaTime);
                        if (miner2.isPrepared()) {
                            a(miner2);
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException("failed to reduce install time, miner idx " + i4 + " of " + i3, e);
                    }
                } else {
                    miner2.miningTime += tickRateDeltaTime;
                    if (miner2.nextMinedResourceType != null) {
                        if (miner2.miningTime >= 1.0f / getMiningSpeed(miner2, miner2.getUpgradeLevel())) {
                            b(miner2);
                        }
                    } else if (miner2.miningTime > 1.0f) {
                        miner2.miningTime = 0.0f;
                        a(miner2);
                    }
                }
                miner2.existsTime += f;
            }
            this.bonusDoubleMiningSpeedTimeLeft -= f;
            if (this.bonusDoubleMiningSpeedTimeLeft < 0.0f) {
                this.bonusDoubleMiningSpeedTimeLeft = 0.0f;
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Miner";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void drawBatch(Batch batch, Map map, float f, MapRenderingSystem.DrawMode drawMode) {
        Array.ArrayIterator it = map.getTilesByType(SourceTile.class).iterator();
        while (it.hasNext()) {
            SourceTile sourceTile = (SourceTile) it.next();
            if (sourceTile.visibleOnScreen && sourceTile.miner != null) {
                sourceTile.miner.drawBatch(batch, sourceTile.getX() << 7, sourceTile.getY() << 7, 128.0f, f, drawMode);
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.miners.clear();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MinerSystem$OnNextWaveForce.class */
    public static final class OnNextWaveForce extends SerializableListener<MinerSystem> implements Listener<NextWaveForce> {
        /* synthetic */ OnNextWaveForce(MinerSystem minerSystem, byte b2) {
            this(minerSystem);
        }

        private OnNextWaveForce() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnNextWaveForce(MinerSystem minerSystem) {
            this.f1759a = minerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(NextWaveForce nextWaveForce) {
            if (nextWaveForce.getTime() > 0.0f) {
                for (int i = 0; i < ((MinerSystem) this.f1759a).miners.size; i++) {
                    Miner miner = ((MinerSystem) this.f1759a).miners.items[i];
                    miner.doubleSpeedTimeLeft += nextWaveForce.getTime();
                    if (miner.doubleSpeedParticle == null && ((MinerSystem) this.f1759a).S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !((MinerSystem) this.f1759a).S._particle.willParticleBeSkipped()) {
                        miner.doubleSpeedParticle = Game.i.minerManager.doubleSpeedParticleEffectPool.obtain();
                        miner.doubleSpeedParticle.setPosition(miner.getTile().center.x, miner.getTile().center.y);
                        ((MinerSystem) this.f1759a).S._particle.addParticle(miner.doubleSpeedParticle, true);
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MinerSystem$OnMinerRemove.class */
    public static class OnMinerRemove extends SerializableListener<MinerSystem> implements Listener<MinerRemove> {
        /* synthetic */ OnMinerRemove(MinerSystem minerSystem, byte b2) {
            this(minerSystem);
        }

        private OnMinerRemove() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnMinerRemove(MinerSystem minerSystem) {
            this.f1759a = minerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(MinerRemove minerRemove) {
            ((MinerSystem) this.f1759a).unregister(minerRemove.getMiner());
        }
    }
}
