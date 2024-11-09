package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.BossTileType;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.WaveBossSupplier;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Iterator;
import java.util.Locale;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/BossTile.class */
public final class BossTile extends Tile {
    private static final TLog c = TLog.forClass(BossTile.class);
    private static Array<GameValueConfig> d = new Array<>(new GameValueConfig[]{new GameValueConfig(GameValueType.SCORE, -50.0d, false, true), new GameValueConfig(GameValueType.MINERS_SPEED, -25.0d, false, true), new GameValueConfig(GameValueType.LOOT_RARITY, -10.0d, false, true)});
    private static final BossWavesConfig e = new BossWavesConfig(1, 1, 0, new Array());
    private static Array<GameValueConfig> f = new Array<>(new GameValueConfig[]{new GameValueConfig(GameValueType.SCORE, -25.0d, false, true), new GameValueConfig(GameValueType.MINERS_SPEED, -10.0d, false, true)});
    private static BossWavesConfig g = new BossWavesConfig(300, 0, 0, new Array(new BossTypeWavePair[]{new BossTypeWavePair(40, BossType.BROOT), new BossTypeWavePair(90, BossType.SNAKE), new BossTypeWavePair(150, BossType.CONSTRUCTOR), new BossTypeWavePair(User32.VK_OEM_5, BossType.MOBCHAIN), new BossTypeWavePair(300, BossType.METAPHOR)}));
    private static Array<GameValueConfig> h = new Array<>(new GameValueConfig[]{new GameValueConfig(GameValueType.SCORE, 10.0d, false, true), new GameValueConfig(GameValueType.LOOT_RARITY, 10.0d, false, true)});
    private static BossWavesConfig i = new BossWavesConfig(50, 0, 10, new Array(new BossTypeWavePair[]{new BossTypeWavePair(10, BossType.BROOT), new BossTypeWavePair(20, BossType.SNAKE), new BossTypeWavePair(30, BossType.CONSTRUCTOR), new BossTypeWavePair(40, BossType.MOBCHAIN), new BossTypeWavePair(50, BossType.METAPHOR)}));
    private static Array<GameValueConfig> j = new Array<>(new GameValueConfig[]{new GameValueConfig(GameValueType.SCORE, -25.0d, false, true), new GameValueConfig(GameValueType.MINERS_SPEED, -10.0d, false, true), new GameValueConfig(GameValueType.LOOT_RARITY, -10.0d, false, true)});
    private static BossWavesConfig[] k = new BossWavesConfig[BossType.values().length];
    private static Color[] l;
    private BossTileType m;
    public BossType oneBossType;
    public Array<GameValueConfig> customEffects;
    public BossWavesConfig customBossWaveConfig;

    /* synthetic */ BossTile(BossTileType bossTileType, byte b2) {
        this(bossTileType);
    }

    static {
        for (BossType bossType : BossType.values) {
            k[bossType.ordinal()] = new BossWavesConfig(20, 0, 0, new Array(new BossTypeWavePair[]{new BossTypeWavePair(20, bossType)}));
        }
        Color[] colorArr = new Color[BossType.values.length];
        l = colorArr;
        colorArr[BossType.BROOT.ordinal()] = MaterialColor.ORANGE.P600;
        l[BossType.SNAKE.ordinal()] = MaterialColor.LIGHT_GREEN.P600;
        l[BossType.METAPHOR.ordinal()] = MaterialColor.RED.P600;
        l[BossType.CONSTRUCTOR.ordinal()] = MaterialColor.BLUE_GREY.P500;
        l[BossType.MOBCHAIN.ordinal()] = MaterialColor.DEEP_PURPLE.P400;
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.m);
        kryo.writeObject(output, this.oneBossType);
        kryo.writeObject(output, this.customEffects);
        kryo.writeObject(output, this.customBossWaveConfig);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.m = (BossTileType) kryo.readObject(input, BossTileType.class);
        this.oneBossType = (BossType) kryo.readObject(input, BossType.class);
        this.customEffects = (Array) kryo.readObject(input, Array.class);
        this.customBossWaveConfig = (BossWavesConfig) kryo.readObject(input, BossWavesConfig.class);
    }

    private BossTile() {
        super(TileType.BOSS);
        this.m = BossTileType.NO;
        this.oneBossType = BossType.BROOT;
        this.customEffects = new Array<>(GameValueConfig.class);
        this.customBossWaveConfig = new BossWavesConfig((byte) 0);
    }

    private BossTile(BossTileType bossTileType) {
        super(TileType.BOSS);
        this.m = BossTileType.NO;
        this.oneBossType = BossType.BROOT;
        this.customEffects = new Array<>(GameValueConfig.class);
        this.customBossWaveConfig = new BossWavesConfig((byte) 0);
        setBossTileType(bossTileType);
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f2) {
        Label label = new Label(getBossTileTypeName(), Game.i.assetManager.getLabelStyle(24));
        label.setColor(MaterialColor.GREEN.P500);
        label.setWrap(true);
        label.setAlignment(1);
        table.add((Table) label).width(f2).row();
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        Label label = new Label(getBossTileTypeName(), Game.i.assetManager.getLabelStyle(24));
        label.setColor(MaterialColor.GREEN.P500);
        table.add((Table) label).growX().row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("effects").toUpperCase(), Game.i.assetManager.getLabelStyle(18));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label2.setAlignment(8);
        table.add((Table) label2).padTop(8.0f).padBottom(4.0f).growX().row();
        Array<GameValueConfig> gameValues = getGameValues();
        if (gameValues.size != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i2 = 0; i2 < gameValues.size; i2++) {
                GameValueConfig gameValueConfig = gameValues.get(i2);
                GameValueManager.GameValueStockConfig stockValueConfig = Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType());
                Table table2 = new Table();
                mapEditorItemInfoMenu.listRowBg(i2, table2);
                table.add(table2).height(32.0f).growX().row();
                table2.add((Table) new Image(stockValueConfig.getIcon())).size(24.0f).padRight(8.0f);
                LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(Game.i.gameValueManager.getTitle(gameValueConfig.getType()), 21, 18, (table.getWidth() - 32.0f) * 0.75f);
                limitedWidthLabel.setAlignment(8);
                table2.add((Table) limitedWidthLabel).growX();
                stringBuilder.setLength(0);
                GameValueManager.ValueUnits valueUnits = Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).units;
                if (valueUnits != GameValueManager.ValueUnits.BOOLEAN) {
                    stringBuilder.append(Game.i.gameValueManager.formatEffectValue(gameValueConfig.getValue(), valueUnits));
                    if (gameValueConfig.isOverwrite()) {
                        stringBuilder.setCharAt(0, '=');
                        stringBuilder.insert(1, ' ');
                    }
                } else if (gameValueConfig.getValue() == 0.0d) {
                    stringBuilder.append(Game.i.localeManager.i18n.get("disabled").toLowerCase(Locale.ENGLISH));
                }
                table2.add((Table) new Label(stringBuilder, Game.i.assetManager.getLabelStyle(21)));
            }
        } else {
            Label label3 = new Label(Game.i.localeManager.i18n.get("tile_has_no_effects"), Game.i.assetManager.getLabelStyle(21));
            label3.setAlignment(1);
            table.add((Table) label3).height(32.0f).padBottom(4.0f).growX().row();
        }
        BossWavesConfig bossWavesConfig = getBossWavesConfig();
        if (bossWavesConfig.bossWavePairs.size > 0) {
            Table table3 = new Table();
            table.add(table3).growX().padTop(8.0f).padBottom(4.0f).growX().row();
            Label label4 = new Label(Game.i.localeManager.i18n.get("enemy_name_BOSS").toUpperCase(), Game.i.assetManager.getLabelStyle(18));
            label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label4.setAlignment(8);
            table3.add((Table) label4).growX();
            Label label5 = new Label(Game.i.localeManager.i18n.get("main_ui_wave_title").toUpperCase(), Game.i.assetManager.getLabelStyle(18));
            label5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            table3.add((Table) label5);
            for (int i3 = 0; i3 < bossWavesConfig.bossWavePairs.size; i3++) {
                BossTypeWavePair bossTypeWavePair = bossWavesConfig.bossWavePairs.items[i3];
                Table table4 = new Table();
                mapEditorItemInfoMenu.listRowBg(i3, table4);
                table.add(table4).height(32.0f).growX().row();
                Enemy.Factory<? extends Enemy> factory = Game.i.enemyManager.getFactory(Game.i.enemyManager.getBossEnemyType(bossTypeWavePair.bossType));
                table4.add((Table) new Image(factory.getTexture())).size(24.0f).padRight(8.0f);
                table4.add((Table) new Label(factory.getTitle(), Game.i.assetManager.getLabelStyle(21))).growX();
                int i4 = bossTypeWavePair.wave + bossWavesConfig.startDelay;
                String valueOf = String.valueOf(i4);
                if (bossWavesConfig.repeatCount <= 0) {
                    valueOf = valueOf + ", " + (i4 + bossWavesConfig.cycleLength) + "...";
                }
                table4.add((Table) new Label(valueOf, Game.i.assetManager.getLabelStyle(21)));
            }
            return;
        }
        Label label6 = new Label(Game.i.localeManager.i18n.get("boss_tile_name_NO"), Game.i.assetManager.getLabelStyle(21));
        label6.setAlignment(1);
        label6.setColor(MaterialColor.GREEN.P500);
        table.add((Table) label6).height(32.0f).padTop(8.0f).growX().row();
    }

    public final void setBossTileType(BossTileType bossTileType) {
        this.m = bossTileType;
    }

    public final BossTileType getBossTileType() {
        return this.m;
    }

    public final String getBossTileTypeName() {
        String bossTileTypeName = Game.i.tileManager.F.BOSS.getBossTileTypeName(this.m);
        if (this.m == BossTileType.ONE) {
            bossTileTypeName = bossTileTypeName + " (" + Game.i.enemyManager.getFactory(Game.i.enemyManager.getBossEnemyType(this.oneBossType)).getTitle() + ")";
        }
        return bossTileTypeName;
    }

    public final BossWavesConfig getBossWavesConfig() {
        if (this.m == BossTileType.CUSTOM) {
            return this.customBossWaveConfig;
        }
        if (this.m == BossTileType.ONE) {
            return k[this.oneBossType.ordinal()];
        }
        if (this.m == BossTileType.HARD) {
            return i;
        }
        if (this.m == BossTileType.RARE) {
            return g;
        }
        if (this.m == BossTileType.NO) {
            return e;
        }
        throw new IllegalArgumentException("not implemented for " + this.m);
    }

    public final WaveBossSupplier getBossWaveMap() {
        return new WaveBossSupplier.Procedural(getBossWavesConfig());
    }

    public final Array<GameValueConfig> getGameValues() {
        if (this.m == BossTileType.CUSTOM) {
            return this.customEffects;
        }
        if (this.m == BossTileType.ONE) {
            return j;
        }
        if (this.m == BossTileType.HARD) {
            return h;
        }
        if (this.m == BossTileType.RARE) {
            return f;
        }
        if (this.m == BossTileType.NO) {
            return d;
        }
        throw new IllegalArgumentException("not implemented for " + this.m);
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.RARITY) {
            return (getRarity().ordinal() * 1000) + (10 - this.m.ordinal()) + 150;
        }
        int ordinal = 2000 + (this.m.ordinal() * 100);
        if (this.m == BossTileType.ONE) {
            ordinal += this.oneBossType.ordinal() * 10;
        }
        if (this.m == BossTileType.CUSTOM) {
            ordinal = ordinal + (this.customBossWaveConfig.bossWavePairs.size * 10) + (this.customEffects.size * 100);
        }
        return ordinal;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        return RarityType.LEGENDARY;
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_SPECIAL;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        if (!super.sameAs(tile)) {
            return false;
        }
        BossTile bossTile = (BossTile) tile;
        if (bossTile.m != this.m) {
            return false;
        }
        if (this.m == BossTileType.ONE && bossTile.oneBossType != this.oneBossType) {
            return false;
        }
        if (this.m == BossTileType.CUSTOM) {
            if (bossTile.customEffects.size != this.customEffects.size) {
                return false;
            }
            for (int i2 = 0; i2 < bossTile.customEffects.size; i2++) {
                if (!bossTile.customEffects.items[i2].sameAs(this.customEffects.items[i2])) {
                    return false;
                }
            }
            return bossTile.customBossWaveConfig.sameAs(this.customBossWaveConfig);
        }
        return true;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        BossTile bossTile = (BossTile) tile;
        this.m = bossTile.m;
        this.oneBossType = bossTile.oneBossType;
        this.customEffects.clear();
        for (int i2 = 0; i2 < bossTile.customEffects.size; i2++) {
            this.customEffects.add(bossTile.customEffects.items[i2].cpy());
        }
        this.customBossWaveConfig.bossWavePairs.clear();
        for (int i3 = 0; i3 < bossTile.customBossWaveConfig.bossWavePairs.size; i3++) {
            this.customBossWaveConfig.repeatCount = bossTile.customBossWaveConfig.repeatCount;
            this.customBossWaveConfig.startDelay = bossTile.customBossWaveConfig.startDelay;
            this.customBossWaveConfig.cycleLength = bossTile.customBossWaveConfig.cycleLength;
            this.customBossWaveConfig.bossWavePairs.add(bossTile.customBossWaveConfig.bossWavePairs.items[i3].cpy());
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f2) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f2, f2);
        if (this.m == BossTileType.ONE) {
            Image image = new Image(Game.i.tileManager.F.BOSS.e);
            image.setColor(l[this.oneBossType.ordinal()]);
            image.setSize(f2, f2);
            group.addActor(image);
            Image image2 = new Image(Game.i.tileManager.F.BOSS.f);
            image2.setSize(f2, f2);
            group.addActor(image2);
        } else if (this.m == BossTileType.CUSTOM) {
            Image image3 = new Image(Game.i.tileManager.F.BOSS.f3085a);
            image3.setSize(f2, f2);
            group.addActor(image3);
        } else if (this.m == BossTileType.HARD) {
            Image image4 = new Image(Game.i.tileManager.F.BOSS.c);
            image4.setSize(f2, f2);
            group.addActor(image4);
        } else if (this.m == BossTileType.RARE) {
            Image image5 = new Image(Game.i.tileManager.F.BOSS.d);
            image5.setSize(f2, f2);
            group.addActor(image5);
        } else if (this.m == BossTileType.NO) {
            Image image6 = new Image(Game.i.tileManager.F.BOSS.f3086b);
            image6.setSize(f2, f2);
            group.addActor(image6);
        }
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("btt", this.m.name());
        if (this.m == BossTileType.ONE) {
            json.writeValue("obt", this.oneBossType.name());
        } else if (this.m == BossTileType.CUSTOM) {
            json.writeArrayStart("ce");
            for (int i2 = 0; i2 < this.customEffects.size; i2++) {
                json.writeObjectStart();
                this.customEffects.items[i2].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
            json.writeObjectStart("cbwc");
            this.customBossWaveConfig.toJson(json);
            json.writeObjectEnd();
        }
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f2, float f3, float f4, float f5, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f2, f3, f4, f5, map, drawMode);
        if (this.m == BossTileType.ONE) {
            batch.setColor(l[this.oneBossType.ordinal()]);
            batch.draw(Game.i.tileManager.F.BOSS.e, f2, f3, f4, f5);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            batch.draw(Game.i.tileManager.F.BOSS.f, f2, f3, f4, f5);
        } else if (this.m == BossTileType.CUSTOM) {
            batch.draw(Game.i.tileManager.F.BOSS.f3085a, f2, f3, f4, f5);
        } else if (this.m == BossTileType.HARD) {
            batch.draw(Game.i.tileManager.F.BOSS.c, f2, f3, f4, f5);
        } else if (this.m == BossTileType.RARE) {
            batch.draw(Game.i.tileManager.F.BOSS.d, f2, f3, f4, f5);
        } else if (this.m == BossTileType.NO) {
            batch.draw(Game.i.tileManager.F.BOSS.f3086b, f2, f3, f4, f5);
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.BLUEPRINT_SPECIAL_III, 1));
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        switch (this.m) {
            case NO:
                return 0.1d;
            case RARE:
                return 0.3d;
            case ONE:
                return 0.3d;
            case HARD:
                return 1.0d;
            case CUSTOM:
                return 0.1d;
            default:
                return 0.0d;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean affectedByLuckyWheelMultiplier() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/BossTile$BossTileFactory.class */
    public static class BossTileFactory extends Tile.Factory.AbstractFactory<BossTile> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f3085a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f3086b;
        private TextureRegion c;
        private TextureRegion d;
        private TextureRegion e;
        private TextureRegion f;
        private final String[] g;

        public BossTileFactory() {
            super(TileType.BOSS);
            this.g = new String[BossTileType.values.length];
            for (BossTileType bossTileType : BossTileType.values) {
                this.g[bossTileType.ordinal()] = "boss_tile_name_" + bossTileType.name();
            }
        }

        public String getBossTileTypeName(BossTileType bossTileType) {
            return Game.i.localeManager.i18n.get(this.g[bossTileType.ordinal()]);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            return ((double) f) < 0.9d ? 0 : 1;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3085a = Game.i.assetManager.getTextureRegion("tile-type-boss-custom");
            this.f3086b = Game.i.assetManager.getTextureRegion("tile-type-boss-no");
            this.c = Game.i.assetManager.getTextureRegion("tile-type-boss-hard");
            this.d = Game.i.assetManager.getTextureRegion("tile-type-boss-rare");
            this.e = Game.i.assetManager.getTextureRegion("tile-type-boss-one-bg");
            this.f = Game.i.assetManager.getTextureRegion("tile-type-boss-one-fg");
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public BossTile create() {
            return new BossTile(BossTileType.NO, (byte) 0);
        }

        public BossTile createWithTileType(BossTileType bossTileType) {
            return new BossTile(bossTileType, (byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public BossTile fromJson(JsonValue jsonValue) {
            BossTile bossTile = (BossTile) super.fromJson(jsonValue);
            JsonValue jsonValue2 = jsonValue.get("d");
            if (jsonValue2 != null) {
                try {
                    bossTile.setBossTileType(BossTileType.valueOf(jsonValue2.getString("btt")));
                } catch (Exception e) {
                    BossTile.c.e("failed to load boss tile type", e);
                }
                if (bossTile.m != BossTileType.ONE) {
                    if (bossTile.m == BossTileType.CUSTOM) {
                        try {
                            Iterator<JsonValue> iterator2 = jsonValue2.get("ce").iterator2();
                            while (iterator2.hasNext()) {
                                try {
                                    bossTile.customEffects.add(GameValueConfig.fromJson(iterator2.next()));
                                } catch (Exception e2) {
                                    BossTile.c.e("failed to load custom GV", e2);
                                }
                            }
                        } catch (Exception e3) {
                            BossTile.c.e("failed to load custom GVs", e3);
                        }
                        try {
                            bossTile.customBossWaveConfig = BossWavesConfig.fromJson(jsonValue2.get("cbwc"));
                        } catch (Exception e4) {
                            BossTile.c.e("failed to load custom waves config", e4);
                        }
                    }
                } else {
                    try {
                        bossTile.oneBossType = BossType.valueOf(jsonValue2.getString("obt"));
                    } catch (Exception e5) {
                        BossTile.c.e("failed to load one boss type", e5);
                    }
                }
            }
            return bossTile;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/BossTile$BossWavesConfig.class */
    public static class BossWavesConfig implements KryoSerializable {
        public int cycleLength;
        public int repeatCount;
        public int startDelay;
        public Array<BossTypeWavePair> bossWavePairs;

        /* synthetic */ BossWavesConfig(byte b2) {
            this();
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeVarInt(this.cycleLength, true);
            output.writeInt(this.repeatCount);
            output.writeVarInt(this.startDelay, true);
            kryo.writeObject(output, this.bossWavePairs);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.cycleLength = input.readVarInt(true);
            this.repeatCount = input.readInt();
            this.startDelay = input.readVarInt(true);
            this.bossWavePairs = (Array) kryo.readObject(input, Array.class);
        }

        private BossWavesConfig() {
            this.cycleLength = 20;
            this.repeatCount = 1;
            this.startDelay = 0;
            this.bossWavePairs = new Array<>(BossTypeWavePair.class);
        }

        public BossWavesConfig(int i, int i2, int i3, Array<BossTypeWavePair> array) {
            this.cycleLength = 20;
            this.repeatCount = 1;
            this.startDelay = 0;
            this.bossWavePairs = new Array<>(BossTypeWavePair.class);
            this.cycleLength = i;
            this.repeatCount = i2;
            this.startDelay = i3;
            this.bossWavePairs.addAll(array);
        }

        public boolean sameAs(BossWavesConfig bossWavesConfig) {
            if (bossWavesConfig.cycleLength != this.cycleLength || bossWavesConfig.repeatCount != this.repeatCount || bossWavesConfig.startDelay != this.startDelay || this.bossWavePairs.size != bossWavesConfig.bossWavePairs.size) {
                return false;
            }
            for (int i = 0; i < this.bossWavePairs.size; i++) {
                if (!this.bossWavePairs.items[i].sameAs(bossWavesConfig.bossWavePairs.items[i])) {
                    return false;
                }
            }
            return true;
        }

        public static BossWavesConfig fromJson(JsonValue jsonValue) {
            BossWavesConfig bossWavesConfig = new BossWavesConfig();
            bossWavesConfig.cycleLength = jsonValue.getInt("cl");
            bossWavesConfig.repeatCount = jsonValue.getInt("rc");
            bossWavesConfig.startDelay = jsonValue.getInt("sd");
            try {
                Iterator<JsonValue> iterator2 = jsonValue.get("bwp").iterator2();
                while (iterator2.hasNext()) {
                    bossWavesConfig.bossWavePairs.add(BossTypeWavePair.fromJson(iterator2.next()));
                }
            } catch (Exception e) {
                BossTile.c.e("failed to load boss wave pairs", e);
            }
            return bossWavesConfig;
        }

        public void toJson(Json json) {
            json.writeValue("cl", Integer.valueOf(this.cycleLength));
            json.writeValue("rc", Integer.valueOf(this.repeatCount));
            json.writeValue("sd", Integer.valueOf(this.startDelay));
            json.writeArrayStart("bwp");
            for (int i = 0; i < this.bossWavePairs.size; i++) {
                json.writeObjectStart();
                this.bossWavePairs.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }

        public String toString() {
            return super.toString() + " (cycleLength: " + this.cycleLength + ", repeatCount: " + this.repeatCount + ", startDelay: " + this.startDelay + ", bossWavePairs: " + this.bossWavePairs + ")";
        }

        public BossWavesConfig cpy() {
            BossWavesConfig bossWavesConfig = new BossWavesConfig();
            bossWavesConfig.repeatCount = this.repeatCount;
            bossWavesConfig.cycleLength = this.cycleLength;
            bossWavesConfig.startDelay = this.startDelay;
            for (int i = 0; i < this.bossWavePairs.size; i++) {
                bossWavesConfig.bossWavePairs.add(this.bossWavePairs.get(i).cpy());
            }
            return bossWavesConfig;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/BossTile$BossTypeWavePair.class */
    public static class BossTypeWavePair implements KryoSerializable {
        public int wave;
        public BossType bossType;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeVarInt(this.wave, true);
            kryo.writeObjectOrNull(output, this.bossType, BossType.class);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.wave = input.readVarInt(true);
            this.bossType = (BossType) kryo.readObjectOrNull(input, BossType.class);
        }

        private BossTypeWavePair() {
        }

        public BossTypeWavePair(int i, BossType bossType) {
            this.wave = i;
            this.bossType = bossType;
        }

        public BossTypeWavePair cpy() {
            return new BossTypeWavePair(this.wave, this.bossType);
        }

        public static BossTypeWavePair fromJson(JsonValue jsonValue) {
            BossTypeWavePair bossTypeWavePair = new BossTypeWavePair();
            bossTypeWavePair.wave = jsonValue.getInt("w");
            try {
                bossTypeWavePair.bossType = BossType.valueOf(jsonValue.getString("bt"));
            } catch (Exception e) {
                BossTile.c.e("failed to load boss type", e);
                bossTypeWavePair.bossType = BossType.BROOT;
            }
            return bossTypeWavePair;
        }

        public boolean sameAs(BossTypeWavePair bossTypeWavePair) {
            return bossTypeWavePair.bossType == this.bossType && bossTypeWavePair.wave == this.wave;
        }

        public void toJson(Json json) {
            json.writeValue("w", Integer.valueOf(this.wave));
            json.writeValue("bt", this.bossType.name());
        }

        public String toString() {
            return super.toString() + " (" + this.wave + ", " + this.bossType + ")";
        }
    }
}
