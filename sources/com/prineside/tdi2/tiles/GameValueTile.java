package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
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
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/GameValueTile.class */
public final class GameValueTile extends Tile {
    private static final TLog c = TLog.forClass(GameValueTile.class);
    private GameValueType d;
    private double e;
    private boolean f;
    private boolean g;

    /* synthetic */ GameValueTile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.d, GameValueType.class);
        output.writeDouble(this.e);
        output.writeBoolean(this.f);
        output.writeBoolean(this.g);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = (GameValueType) kryo.readObjectOrNull(input, GameValueType.class);
        this.e = input.readDouble();
        this.f = input.readBoolean();
        this.g = input.readBoolean();
    }

    private GameValueTile() {
        super(TileType.GAME_VALUE);
        setGameValueType(GameValueType.STARTING_MONEY);
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        Table table2 = new Table();
        mapEditorItemInfoMenu.listRowBg(0, table2);
        table.add(table2).growX().height(32.0f).row();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.setLength(0);
        stringBuilder.append(Game.i.gameValueManager.getTitle(getGameValueType()));
        if (stringBuilder.length() > 34) {
            stringBuilder.setLength(34);
            stringBuilder.append("...");
        }
        table2.add((Table) new Label(stringBuilder, Game.i.assetManager.getLabelStyle(21)));
        stringBuilder.setLength(0);
        GameValueManager.ValueUnits valueUnits = Game.i.gameValueManager.getStockValueConfig(getGameValueType()).units;
        if (valueUnits != GameValueManager.ValueUnits.BOOLEAN) {
            stringBuilder.append(Game.i.gameValueManager.formatEffectValue(getDelta(), valueUnits));
        }
        if (isFinalMultiplier()) {
            stringBuilder.setCharAt(0, 'x');
        } else if (isOverwrite()) {
            stringBuilder.setCharAt(0, '=');
        }
        Label label = new Label(stringBuilder, Game.i.assetManager.getLabelStyle(21));
        label.setAlignment(16);
        table2.add((Table) label).growX();
        if (isOverwrite()) {
            Label label2 = new Label(Game.i.localeManager.i18n.get("overwrites_other_effects"), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(MaterialColor.ORANGE.P500);
            label2.setAlignment(8);
            table.add((Table) label2).growX().height(32.0f).row();
        }
        if (isFinalMultiplier()) {
            Label label3 = new Label(Game.i.localeManager.i18n.get("gv_tile_final_multiplier"), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(MaterialColor.PURPLE.P500);
            label3.setAlignment(8);
            table.add((Table) label3).growX().height(32.0f).row();
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        return 0.02d;
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f) {
        StringBuilder stringBuilder;
        Label label = new Label(Game.i.gameValueManager.getTitle(getGameValueType()), Game.i.assetManager.getLabelStyle(21));
        label.setWrap(true);
        label.setAlignment(1);
        table.add((Table) label).width(f).row();
        if (isFinalMultiplier()) {
            if (getDelta() == 0.0d) {
                stringBuilder = new StringBuilder(Game.i.localeManager.i18n.get("gv_bonus_disabled"));
            } else {
                StringBuilder formatEffectValue = Game.i.gameValueManager.formatEffectValue(getDelta(), GameValueManager.ValueUnits.UNITS);
                stringBuilder = formatEffectValue;
                formatEffectValue.setCharAt(0, 'x');
            }
            Label label2 = new Label(stringBuilder, Game.i.assetManager.getLabelStyle(24));
            label2.setWrap(true);
            label2.setAlignment(1);
            label2.setColor(MaterialColor.LIGHT_GREEN.P500);
            table.add((Table) label2).width(f).row();
            Label label3 = new Label(Game.i.localeManager.i18n.get("gv_tile_final_multiplier"), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(MaterialColor.ORANGE.P500);
            label3.setWrap(true);
            label3.setAlignment(1);
            table.add((Table) label3).width(f).padTop(8.0f).row();
            return;
        }
        StringBuilder formatEffectValue2 = Game.i.gameValueManager.formatEffectValue(getDelta(), Game.i.gameValueManager.getUnits(getGameValueType()));
        if (isOverwrite()) {
            formatEffectValue2.setCharAt(0, '=');
        }
        Label label4 = new Label(formatEffectValue2, Game.i.assetManager.getLabelStyle(24));
        label4.setWrap(true);
        label4.setAlignment(1);
        label4.setColor(MaterialColor.LIGHT_GREEN.P500);
        table.add((Table) label4).width(f).row();
        if (isOverwrite()) {
            Label label5 = new Label(Game.i.localeManager.i18n.get("overwrites_other_effects"), Game.i.assetManager.getLabelStyle(21));
            label5.setColor(MaterialColor.ORANGE.P500);
            label5.setWrap(true);
            label5.setAlignment(1);
            table.add((Table) label5).width(f).padTop(8.0f).row();
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.RARITY) {
            return getRarity().ordinal() * 1000;
        }
        return 25000 + this.d.ordinal();
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        return RarityType.EPIC;
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
        GameValueTile gameValueTile = (GameValueTile) tile;
        return gameValueTile.d == this.d && gameValueTile.e == this.e && gameValueTile.f == this.f && gameValueTile.g == this.g;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        GameValueTile gameValueTile = (GameValueTile) tile;
        setGameValueType(gameValueTile.d);
        setDelta(gameValueTile.e);
        setOverwrite(gameValueTile.f);
        setFinalMultiplier(gameValueTile.g);
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = new Image(Game.i.tileManager.F.GAME_VALUE.f3135a);
        image.setSize(f, f);
        group.addActor(image);
        Image image2 = new Image(Game.i.gameValueManager.getStockValueConfig(this.d).getIcon());
        image2.setPosition(f * 0.15f, f * 0.15f);
        image2.setSize(f * 0.7f, f * 0.7f);
        group.addActor(image2);
        return group;
    }

    public final GameValueType getGameValueType() {
        return this.d;
    }

    public final double getDelta() {
        return this.e;
    }

    public final boolean isOverwrite() {
        return this.f;
    }

    public final boolean isFinalMultiplier() {
        return this.g;
    }

    public final void setFinalMultiplier(boolean z) {
        this.g = z;
    }

    public final void setDelta(double d) {
        this.e = d;
    }

    public final void setOverwrite(boolean z) {
        this.f = z;
    }

    public final void setGameValueType(GameValueType gameValueType) {
        if (gameValueType == null) {
            throw new IllegalArgumentException("type is null");
        }
        this.d = gameValueType;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("gv", this.d.name());
        json.writeValue("d", Double.valueOf(this.e));
        json.writeValue("o", Integer.valueOf(this.f ? 1 : 0));
        json.writeValue("f", Integer.valueOf(this.g ? 1 : 0));
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        if (this.d != GameValueType.DUMMY || drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            batch.draw(Game.i.tileManager.F.GAME_VALUE.f3135a, f, f2, f3, f4);
        }
        Game.i.gameValueManager.getStockValueConfig(this.d).getIcon().draw(batch, f + (f3 * 0.15f), f2 + (f4 * 0.15f), 0.7f * f3, 0.7f * f3);
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 150));
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/GameValueTile$GameValueTileFactory.class */
    public static class GameValueTileFactory extends Tile.Factory.AbstractFactory<GameValueTile> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f3135a;

        public GameValueTileFactory() {
            super(TileType.GAME_VALUE);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            return 0;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3135a = Game.i.assetManager.getTextureRegion("tile-type-game-value-base");
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public GameValueTile create() {
            return new GameValueTile((byte) 0);
        }

        public GameValueTile createTypeDelta(GameValueType gameValueType, double d) {
            GameValueTile gameValueTile = new GameValueTile((byte) 0);
            gameValueTile.setGameValueType(gameValueType);
            gameValueTile.setDelta(d);
            return gameValueTile;
        }

        public GameValueTile createTypeDeltaOverwrite(GameValueType gameValueType, double d, boolean z) {
            GameValueTile gameValueTile = new GameValueTile((byte) 0);
            gameValueTile.setGameValueType(gameValueType);
            gameValueTile.setDelta(d);
            gameValueTile.setOverwrite(z);
            return gameValueTile;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public GameValueTile fromJson(JsonValue jsonValue) {
            GameValueType gameValueType;
            GameValueTile gameValueTile = (GameValueTile) super.fromJson(jsonValue);
            JsonValue jsonValue2 = jsonValue.get("d");
            if (jsonValue2 != null) {
                try {
                    gameValueType = GameValueType.valueOf(jsonValue2.getString("gv"));
                } catch (Exception e) {
                    gameValueType = GameValueType.EMOJI_ENEMIES;
                    GameValueTile.c.e("failed to load GV: " + jsonValue2.getString("gv", "[gv is empty]") + ", replaced with a dummy tile", e);
                }
                gameValueTile.setGameValueType(gameValueType);
                gameValueTile.setDelta(jsonValue2.getDouble("d", 0.0d));
                gameValueTile.setOverwrite(jsonValue2.getInt("o", 0) == 1);
                gameValueTile.setFinalMultiplier(jsonValue2.getInt("f", 0) == 1);
            }
            return gameValueTile;
        }
    }
}
