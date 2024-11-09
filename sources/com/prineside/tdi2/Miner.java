package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.abilities.LoopAbility;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Miner.class */
public abstract class Miner extends Registrable {
    public static final int MAX_UPGRADE_LEVEL = 10;
    public int id;
    public MinerType type;
    private SourceTile c;
    public int moneySpentOn;
    public float existsTime;
    public long totalScoreGained;
    public int[] minedResources;
    public float lastMinedItemTime;
    private int d;
    public DelayedRemovalArray<Modifier> nearbyModifiers;
    private float e;
    private float f;
    public ResourceType nextMinedResourceType;
    public float miningTime;
    public float doubleSpeedTimeLeft;
    public int loopAbilityResourceBuffer;

    @Null
    public LoopAbility affectedByLoopAbility;

    @NAGS
    public ParticleEffectPool.PooledEffect doubleSpeedParticle;

    @NAGS
    private PieChart g;

    @NAGS
    private final Array<PieChart.ChartEntryConfig> h;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1739a = TLog.forClass(Miner.class);

    /* renamed from: b, reason: collision with root package name */
    private static Color f1740b = new Color(0.56f, 0.56f, 0.56f, 1.0f);
    private static StringBuilder i = new StringBuilder();

    public abstract int getBaseUpgradePrice(int i2);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.id, true);
        kryo.writeObjectOrNull(output, this.type, MinerType.class);
        kryo.writeObjectOrNull(output, this.c, SourceTile.class);
        output.writeVarInt(this.moneySpentOn, true);
        output.writeFloat(this.existsTime);
        output.writeVarLong(this.totalScoreGained, true);
        kryo.writeObjectOrNull(output, this.c, SourceTile.class);
        kryo.writeObject(output, this.minedResources);
        output.writeFloat(this.lastMinedItemTime);
        output.writeVarInt(this.d, true);
        kryo.writeObject(output, this.nearbyModifiers);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        kryo.writeObjectOrNull(output, this.nextMinedResourceType, ResourceType.class);
        output.writeFloat(this.miningTime);
        output.writeFloat(this.doubleSpeedTimeLeft);
        output.writeVarInt(this.loopAbilityResourceBuffer, true);
        kryo.writeClassAndObject(output, this.affectedByLoopAbility);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.id = input.readVarInt(true);
        this.type = (MinerType) kryo.readObjectOrNull(input, MinerType.class);
        this.c = (SourceTile) kryo.readObjectOrNull(input, SourceTile.class);
        this.moneySpentOn = input.readVarInt(true);
        this.existsTime = input.readFloat();
        this.totalScoreGained = input.readVarLong(true);
        this.c = (SourceTile) kryo.readObjectOrNull(input, SourceTile.class);
        this.minedResources = (int[]) kryo.readObject(input, int[].class);
        this.lastMinedItemTime = input.readFloat();
        this.d = input.readVarInt(true);
        this.nearbyModifiers = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.nextMinedResourceType = (ResourceType) kryo.readObjectOrNull(input, ResourceType.class);
        this.miningTime = input.readFloat();
        this.doubleSpeedTimeLeft = input.readFloat();
        this.loopAbilityResourceBuffer = input.readVarInt(true);
        this.affectedByLoopAbility = (LoopAbility) kryo.readClassAndObject(input);
    }

    private Miner() {
        this.moneySpentOn = 0;
        this.minedResources = new int[ResourceType.values.length];
        this.nearbyModifiers = new DelayedRemovalArray<>(Modifier.class);
        this.e = 0.0f;
        this.f = 0.0f;
        this.doubleSpeedTimeLeft = 0.0f;
        this.h = new Array<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Miner(MinerType minerType) {
        this.moneySpentOn = 0;
        this.minedResources = new int[ResourceType.values.length];
        this.nearbyModifiers = new DelayedRemovalArray<>(Modifier.class);
        this.e = 0.0f;
        this.f = 0.0f;
        this.doubleSpeedTimeLeft = 0.0f;
        this.h = new Array<>();
        this.type = minerType;
        for (int i2 = 0; i2 < ResourceType.values.length; i2++) {
            this.minedResources[i2] = 0;
        }
    }

    public void toJson(Json json) {
        json.writeValue("type", this.type.name());
        json.writeValue(FlexmarkHtmlConverter.UL_NODE, Integer.valueOf(this.d));
    }

    public void drawBase(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
    }

    public void updateCache() {
        this.nearbyModifiers.clear();
        this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
            if (tile instanceof PlatformTile) {
                PlatformTile platformTile = (PlatformTile) tile;
                if (platformTile.building instanceof Modifier) {
                    this.nearbyModifiers.add((Modifier) platformTile.building);
                    return true;
                }
                return true;
            }
            return true;
        });
    }

    public void setUpgradeLevel(int i2) {
        this.d = i2;
    }

    public int getUpgradeLevel() {
        return this.d;
    }

    public float getCurrentMiningSpeedFromSystem() {
        if (this.nextMinedResourceType == null || this.S == null) {
            return 0.0f;
        }
        return this.S.miner.getMiningSpeed(this, getUpgradeLevel());
    }

    public int getInstallDuration() {
        int intValueSum = this.S.gameValue.getIntValueSum(GameValueType.MINERS_INSTALL_DURATION, Game.i.minerManager.getInstallDurationGameValueType(this.type));
        int i2 = intValueSum;
        if (intValueSum <= 0) {
            i2 = 1;
        }
        return i2;
    }

    public int getSellPrice() {
        return (int) (this.moneySpentOn * 0.5f);
    }

    public boolean isPrepared() {
        return this.f <= 0.0f;
    }

    public float getPreparationProgress() {
        if (this.f <= 0.0f) {
            return 1.0f;
        }
        return 1.0f - (this.f / this.e);
    }

    public void setInstallTime(float f) {
        if (f <= 0.0f || f > 1000.0f) {
            throw new IllegalArgumentException("time is " + f);
        }
        this.e = f;
        this.f = f;
    }

    public void reduceInstallTime(float f) {
        if (f <= 0.0f || f > 1000.0f) {
            throw new IllegalArgumentException("time is " + f);
        }
        this.f -= f;
        if (this.f < 0.0f) {
            this.f = 0.0f;
        }
    }

    public float getInstallTimeLeft() {
        return this.f;
    }

    public float getVisualMiningProgress() {
        if (isPrepared()) {
            float currentMiningSpeedFromSystem = getCurrentMiningSpeedFromSystem();
            if (currentMiningSpeedFromSystem == 0.0f) {
                return 0.0f;
            }
            return MathUtils.clamp(this.miningTime / (1.0f / currentMiningSpeedFromSystem), 0.0f, 1.0f);
        }
        return 0.0f;
    }

    public SourceTile getTile() {
        return this.c;
    }

    public void setTile(SourceTile sourceTile) {
        this.c = sourceTile;
    }

    public void updatePieChart(float f, float f2, float f3) {
        PieChart.ChartEntryConfig chartEntryConfig;
        PieChart.ChartEntryConfig chartEntryConfig2;
        PieChart.ChartEntryConfig chartEntryConfig3;
        if (this.g == null) {
            return;
        }
        this.h.clear();
        SourceTile tile = getTile();
        int i2 = 0;
        int i3 = 0;
        if (tile != null) {
            for (int i4 = 0; i4 < ResourceType.values.length; i4++) {
                ResourceType resourceType = ResourceType.values[i4];
                int resourcesCount = tile.getResourcesCount(resourceType);
                if (resourcesCount > 0) {
                    i3 += resourcesCount;
                    if (this.h.size < i2 + 1) {
                        chartEntryConfig3 = new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.0f), 0.0f, 0.0f);
                        this.h.add(chartEntryConfig3);
                    } else {
                        chartEntryConfig3 = this.h.get(i2);
                    }
                    chartEntryConfig3.setValue(resourcesCount);
                    chartEntryConfig3.color = Game.i.resourceManager.getColor(resourceType);
                    i2++;
                }
            }
            if (tile.getResourceDensity() < 1.0f) {
                if (this.h.size < i2 + 1) {
                    chartEntryConfig2 = new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.0f), 0.0f, 0.0f);
                    this.h.add(chartEntryConfig2);
                } else {
                    chartEntryConfig2 = this.h.get(i2);
                }
                chartEntryConfig2.setValue((i3 / tile.getResourceDensity()) * (1.0f - tile.getResourceDensity()));
                chartEntryConfig2.color = MaterialColor.GREY.P700;
                i2++;
            }
        }
        if (i3 == 0) {
            if (this.h.size < i2 + 1) {
                chartEntryConfig = new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.0f), 0.0f, 0.0f);
                this.h.add(chartEntryConfig);
            } else {
                chartEntryConfig = this.h.get(i2);
            }
            chartEntryConfig.setValue(1.0f);
            chartEntryConfig.color = MaterialColor.GREY.P700;
            i2++;
        }
        this.h.size = i2;
        this.g.setup(f, f2, 22.0f * f3, 20, this.h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Batch batch, float f, float f2, float f3, MapRenderingSystem.DrawMode drawMode) {
        if (drawMode == MapRenderingSystem.DrawMode.DEFAULT) {
            if (this.g == null || this.g.getX() != f || this.g.getY() != f2) {
                this.g = (PieChart) Game.i.shapeManager.getFactory(ShapeType.PIE_CHART).obtain();
                updatePieChart(f, f2, f3);
            }
            this.g.draw(batch);
            return;
        }
        batch.setColor(Config.BACKGROUND_COLOR);
        batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), f - (22.0f * f3), f2 - (22.0f * f3), 44.0f * f3, 44.0f * f3);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(Batch batch, float f, float f2, float f3, MapRenderingSystem.DrawMode drawMode) {
        float f4 = f3 / 128.0f;
        if (drawMode == MapRenderingSystem.DrawMode.DETAILED || drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            batch.setColor(f1740b);
        }
        batch.draw(Game.i.minerManager.getFactory(this.type).getTexture(), f, f2, 128.0f, 128.0f);
        batch.setColor(Color.WHITE);
        if (getTile() != null && getTile().isDepleted()) {
            batch.setColor(Color.BLACK);
            batch.draw(AssetManager.TextureRegions.i().smallCircle, ((f + 64.0f) - 12.0f) * f4, ((f2 + 64.0f) - 12.0f) * f4, 24.0f * f4, 24.0f * f4);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
        if (drawMode == MapRenderingSystem.DrawMode.DETAILED || drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(36);
            i.setLength(0);
            i.append(this.d);
            font.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            try {
                font.draw(batch, i, f + (3.0f * f4), f2 + (71.0f * f4), 128.0f * f4, 1, false);
                font.setColor(Color.WHITE);
                font.draw(batch, i, f, f2 + (74.0f * f4), 128.0f * f4, 1, false);
            } catch (Exception e) {
                throw new RuntimeException("Failed to draw font, content: '" + ((Object) i) + "'", e);
            }
        }
        if (drawMode == MapRenderingSystem.DrawMode.DETAILED && this.affectedByLoopAbility != null) {
            float f5 = f + (7.0f * f4);
            float f6 = f2 + (85.0f * f4);
            batch.setColor(Config.BACKGROUND_COLOR);
            batch.draw(AssetManager.TextureRegions.i().roundedSmallRect, f5, f6, 36.0f * f4, 36.0f * f4);
            batch.setColor(MaterialColor.GREEN.P900);
            batch.draw(AssetManager.TextureRegions.i().iconLoop, f5, f6, 36.0f * f4, 36.0f * f4);
            batch.setColor(Color.WHITE);
            ResourcePack.ResourcePackBitmapFont font2 = Game.i.assetManager.getFont(18);
            StringBuilder compactNumber = StringFormatter.compactNumber(this.loopAbilityResourceBuffer, false);
            font2.setColor(Config.BACKGROUND_COLOR);
            font2.draw(batch, compactNumber, f5, f6 + (25.5f * f4), 36.0f * f4, 1, false);
            font2.draw(batch, compactNumber, f5, f6 + (22.5f * f4), 36.0f * f4, 1, false);
            font2.draw(batch, compactNumber, f5 - (1.5f * f4), f6 + (24.0f * f4), 36.0f * f4, 1, false);
            font2.draw(batch, compactNumber, f5 + (1.5f * f4), f6 + (24.0f * f4), 36.0f * f4, 1, false);
            font2.setColor(MaterialColor.GREEN.P200);
            font2.draw(batch, compactNumber, f5, f6 + (24.0f * f4), 36.0f * f4, 1, false);
            font2.setColor(Color.WHITE);
        }
    }

    public void placedOnMap() {
        updatePieChart(getTile().center.x, getTile().center.y, 1.0f);
    }

    public void removedFromMap() {
    }

    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
    }

    public void loadFromJson(JsonValue jsonValue) {
        try {
            this.d = jsonValue.getInt(FlexmarkHtmlConverter.UL_NODE, 0);
        } catch (Exception e) {
            f1739a.e("failed to load miner from json", e);
        }
    }

    public Miner cloneMiner() {
        Miner create = Game.i.minerManager.getFactory(this.type).create();
        create.d = this.d;
        return create;
    }

    public boolean sameAs(Miner miner) {
        return miner != null && miner.type == this.type && miner.d == this.d;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Miner$Factory.class */
    public static abstract class Factory<T extends Miner> implements Disposable {

        /* renamed from: a, reason: collision with root package name */
        private String f1741a;

        /* renamed from: b, reason: collision with root package name */
        private MinerType f1742b;

        public abstract T create();

        public abstract boolean canMineResource(ResourceType resourceType);

        public abstract int getBaseBuildPrice(GameValueProvider gameValueProvider);

        public abstract TextureRegion getTexture();

        public abstract float getBaseMiningSpeed(GameValueProvider gameValueProvider);

        /* JADX INFO: Access modifiers changed from: protected */
        public Factory(MinerType minerType, String str) {
            this.f1742b = minerType;
            this.f1741a = str;
        }

        public void setup() {
            if (Game.i.assetManager != null) {
                setupAssets();
            }
        }

        public String getTitle() {
            return Game.i.minerManager.getTitle(this.f1742b);
        }

        public String getDescription() {
            return Game.i.localeManager.i18n.get("digs_resources_from_sources");
        }

        public void setupAssets() {
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
        }

        public Actor createIconActor(float f) {
            Image image = new Image(Game.i.assetManager.getDrawable(this.f1741a));
            image.setSize(f, f);
            return image;
        }
    }
}
