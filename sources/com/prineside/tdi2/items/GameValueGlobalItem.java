package com.prineside.tdi2.items;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GameValueGlobalItem.class */
public class GameValueGlobalItem extends Item {
    public GameValueType gameValueType;
    public double delta;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2236a = TLog.forClass(GameValueGlobalItem.class);

    /* renamed from: b, reason: collision with root package name */
    private static final StringBuilder f2237b = new StringBuilder();

    /* synthetic */ GameValueGlobalItem(GameValueType gameValueType, double d, byte b2) {
        this(gameValueType, d);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GameValueGlobalItem$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<GameValueGlobalItem> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, GameValueGlobalItem gameValueGlobalItem) {
            kryo.writeObject(output, gameValueGlobalItem.gameValueType);
            output.writeDouble(gameValueGlobalItem.delta);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public GameValueGlobalItem read2(Kryo kryo, Input input, Class<? extends GameValueGlobalItem> cls) {
            return Item.D.F_GAME_VALUE_GLOBAL.create((GameValueType) kryo.readObject(input, GameValueType.class), input.readDouble());
        }
    }

    private GameValueGlobalItem(GameValueType gameValueType, double d) {
        if (gameValueType == null) {
            throw new IllegalArgumentException("GameValueType is null");
        }
        this.delta = d;
        this.gameValueType = gameValueType;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        GameValueGlobalItem gameValueGlobalItem = (GameValueGlobalItem) item;
        this.gameValueType = gameValueGlobalItem.gameValueType;
        this.delta = gameValueGlobalItem.delta;
        return this;
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.gameValueType.ordinal());
        data.add(ItemDataType.VALUE.ordinal(), MathUtils.round((float) (this.delta * 1000.0d)));
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_GAME_VALUE_GLOBAL.create(this.gameValueType, this.delta);
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        f2237b.setLength(0);
        f2237b.append(Game.i.gameValueManager.getTitle(this.gameValueType));
        f2237b.append(SequenceUtils.SPACE);
        f2237b.append(Game.i.gameValueManager.formatEffectValue(this.delta, Game.i.gameValueManager.getStockValueConfig(this.gameValueType).units));
        return f2237b;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_GAME_VALUE_GLOBAL");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.EPIC;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "gv_global";
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("gameValueType", this.gameValueType.name());
        json.writeValue("delta", Double.valueOf(this.delta));
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.GAME_VALUE_GLOBAL;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.OTHER;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.O_OTHER;
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = new Image(Game.i.assetManager.getDrawable("item-cell-game-value-global"));
        image.setSize(f, f);
        image.setColor(MaterialColor.PURPLE.P300);
        group.addActor(image);
        Image image2 = new Image(Game.i.gameValueManager.getStockValueConfig(this.gameValueType).createIconForBackgroundWithColor(MaterialColor.PURPLE.P300, Color.BLACK));
        image2.setPosition(f * 0.25f, f * 0.08f);
        image2.setSize(f * 0.66f, f * 0.66f);
        group.addActor(image2);
        return group;
    }

    @Override // com.prineside.tdi2.Item
    public void fillItemCreationForm(final ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Game value");
        final SelectBox selectBox = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox.setItems(GameValueType.values);
        selectBox.setSelected(this.gameValueType);
        selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.items.GameValueGlobalItem.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                itemCreationOverlay.currentItem = Item.D.F_GAME_VALUE_GLOBAL.create((GameValueType) selectBox.getSelected(), GameValueGlobalItem.this.delta);
                itemCreationOverlay.updateItemIcon();
            }
        });
        itemCreationOverlay.selectBox(selectBox);
        itemCreationOverlay.label("Delta");
        itemCreationOverlay.textField(String.valueOf(this.delta), str -> {
            try {
                itemCreationOverlay.currentItem = Item.D.F_GAME_VALUE_GLOBAL.create(this.gameValueType, Double.parseDouble(str));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                f2236a.e("bad value: " + str, new Object[0]);
            }
        });
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        if (!super.sameAs(item)) {
            return false;
        }
        GameValueGlobalItem gameValueGlobalItem = (GameValueGlobalItem) item;
        return gameValueGlobalItem.gameValueType == this.gameValueType && gameValueGlobalItem.delta == this.delta;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GameValueGlobalItem$GameValueGlobalItemFactory.class */
    public static class GameValueGlobalItemFactory implements Item.Factory<GameValueGlobalItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public GameValueGlobalItem create(GameValueType gameValueType, double d) {
            return new GameValueGlobalItem(gameValueType, d, (byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GameValueGlobalItem fromJson(JsonValue jsonValue) {
            return create(GameValueType.valueOf(jsonValue.getString("gameValueType")), jsonValue.getDouble("delta"));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GameValueGlobalItem createDefault() {
            return create(GameValueType.values[0], 1.0d);
        }
    }
}
