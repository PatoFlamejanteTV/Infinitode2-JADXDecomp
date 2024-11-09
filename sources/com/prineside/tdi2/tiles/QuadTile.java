package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.kryo.FixedInput;
import com.prineside.kryo.FixedOutput;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.QuadRegion;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Locale;
import java.util.Objects;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/QuadTile.class */
public class QuadTile extends Tile {
    public boolean isStatic;
    public Color color;

    @NAGS
    public Quad quad;
    private static final TLog c = TLog.forClass(QuadTile.class);
    private static final FixedOutput d = new FixedOutput(128, -1);
    private static final FixedInput e = new FixedInput();

    /* synthetic */ QuadTile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.isStatic);
        kryo.writeObject(output, this.color);
        d.clear();
        if (this.quad == null) {
            output.writeVarInt(0, true);
            return;
        }
        this.quad.toBytes(d);
        output.writeVarInt(d.position(), true);
        output.writeBytes(d.getBuffer(), 0, d.position());
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.isStatic = input.readBoolean();
        this.color = (Color) kryo.readObject(input, Color.class);
        int readVarInt = input.readVarInt(true);
        if (readVarInt != 0) {
            try {
                e.setBuffer(input.readBytes(readVarInt));
                this.quad = Quad.fromBytes(e);
            } catch (Exception unused) {
            }
        }
    }

    private QuadTile() {
        super(TileType.QUAD);
        this.isStatic = true;
        this.color = Color.WHITE.cpy();
        this.quad = new Quad(1.0f, 1.0f);
    }

    @Override // com.prineside.tdi2.Tile
    public boolean canBeSelected() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        Label label = new Label(this.isStatic ? "Static" : "Dynamic", Game.i.assetManager.getLabelStyle(24));
        if (this.isStatic) {
            label.setColor(MaterialColor.GREEN.P500);
        } else {
            label.setColor(MaterialColor.YELLOW.P500);
        }
        table.add((Table) label);
    }

    @Override // com.prineside.tdi2.Tile
    public void fillInventoryWithInfo(Table table, float f) {
    }

    @Override // com.prineside.tdi2.Tile
    public int getSortingScore(ItemSortingType itemSortingType) {
        return 1;
    }

    @Override // com.prineside.tdi2.Tile
    public boolean isRoadType() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public RarityType getRarity() {
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Tile
    public ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_SPECIAL;
    }

    @Override // com.prineside.tdi2.Tile
    public boolean sameAs(Tile tile) {
        if (!super.sameAs(tile)) {
            return false;
        }
        QuadTile quadTile = (QuadTile) tile;
        if (quadTile.isStatic == this.isStatic && Objects.equals(this.color, quadTile.color)) {
            return Objects.equals(this.quad, ((QuadTile) tile).quad);
        }
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public void from(Tile tile) {
        super.from(tile);
        QuadTile quadTile = (QuadTile) tile;
        this.color.set(quadTile.color);
        this.isStatic = quadTile.isStatic;
        this.quad = new Quad(quadTile.quad, true);
    }

    @Null
    public Quad getQuad() {
        return this.quad;
    }

    @Override // com.prineside.tdi2.Tile
    public Group generateUiIcon(float f) {
        Quad quad = getQuad();
        Rectangle rectangle = new Rectangle();
        rectangle.height = quad.getHeight();
        rectangle.width = quad.getWidth();
        for (int i = 0; i < quad.getRegions().size; i++) {
            QuadRegion quadRegion = quad.getRegions().items[i];
            rectangle.x = Math.min(rectangle.x, quadRegion.getX());
            rectangle.y = Math.min(rectangle.y, quadRegion.getY());
            rectangle.width = Math.max(rectangle.width, quadRegion.getWidth() + quadRegion.getX());
            rectangle.height = Math.max(rectangle.height, quadRegion.getHeight() + quadRegion.getY());
        }
        Quad quad2 = new Quad(quad, true);
        quad2.setWidth((-rectangle.x) + rectangle.width);
        quad2.setHeight((-rectangle.y) + rectangle.height);
        for (int i2 = 0; i2 < quad2.getRegions().size; i2++) {
            quad2.getRegions().items[i2].translate(-rectangle.x, -rectangle.y);
        }
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = new Image(quad2);
        image.setColor(this.color);
        image.setSize(f, f);
        group.addActor(image);
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("q", this.quad.toBase64());
        if (this.color != null) {
            json.writeValue("c", this.color.toString());
        }
        json.writeValue("s", Boolean.valueOf(this.isStatic));
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        if (drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            batch.setColor(1.0f, 1.0f, 0.0f, 0.07f);
            batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), f, f2, f3, f4);
            batch.setColor(Color.WHITE);
        }
        if (this.isStatic) {
            batch.setColor(this.color);
            getQuad().draw(batch, f, f2, f3, f4);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        if (!this.isStatic) {
            batch.setColor(this.color);
            getQuad().draw(batch, f2, f3, f4, f5);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Quad code");
        itemCreationOverlay.textFieldOfWidth(this.quad.toBase64(), 800.0f, str -> {
            try {
                this.quad = Quad.fromString(str);
            } catch (Exception unused) {
            }
            itemCreationOverlay.updateItemIcon();
        });
        itemCreationOverlay.label("Color");
        itemCreationOverlay.textField(this.color.toString().toUpperCase(Locale.US), str2 -> {
            try {
                this.color.set(Color.valueOf(str2));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
            }
        });
        itemCreationOverlay.toggle(true, "Static", this.isStatic, bool -> {
            this.isStatic = bool.booleanValue();
            itemCreationOverlay.updateForm();
        });
    }

    @Override // com.prineside.tdi2.Tile
    public void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 1));
    }

    @Override // com.prineside.tdi2.Tile
    public double getPrestigeScore() {
        return 0.0d;
    }

    @Override // com.prineside.tdi2.Tile
    public boolean canBeUpgraded() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/QuadTile$QuadTileFactory.class */
    public static class QuadTileFactory extends Tile.Factory.AbstractFactory<QuadTile> {
        public QuadTileFactory() {
            super(TileType.QUAD);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            return 0;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public QuadTile create() {
            return new QuadTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public QuadTile fromJson(JsonValue jsonValue) {
            QuadTile quadTile = (QuadTile) super.fromJson(jsonValue);
            JsonValue jsonValue2 = jsonValue.get("d");
            if (jsonValue2 != null) {
                try {
                    quadTile.quad = Quad.fromString(jsonValue2.getString("q"));
                } catch (Exception e) {
                    QuadTile.c.e("failed to load quad from json", e);
                    quadTile.quad = new Quad(Quad.getNoQuad(), true);
                }
                try {
                    quadTile.color.set(Color.valueOf(jsonValue2.getString("c", Color.WHITE.toString())));
                } catch (Exception unused) {
                }
                quadTile.isStatic = jsonValue2.getBoolean("s", true);
            }
            return quadTile;
        }
    }
}
