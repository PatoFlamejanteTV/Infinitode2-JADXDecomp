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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GameValueLevelItem.class */
public class GameValueLevelItem extends Item {
    public GameValueType gameValueType;
    public String levelName;
    public double delta;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2240a = TLog.forClass(GameValueLevelItem.class);

    /* renamed from: b, reason: collision with root package name */
    private static final StringBuilder f2241b = new StringBuilder();

    /* synthetic */ GameValueLevelItem(GameValueType gameValueType, String str, double d, byte b2) {
        this(gameValueType, str, d);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GameValueLevelItem$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<GameValueLevelItem> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, GameValueLevelItem gameValueLevelItem) {
            kryo.writeObject(output, gameValueLevelItem.gameValueType);
            kryo.writeObject(output, gameValueLevelItem.levelName);
            output.writeDouble(gameValueLevelItem.delta);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public GameValueLevelItem read2(Kryo kryo, Input input, Class<? extends GameValueLevelItem> cls) {
            GameValueType gameValueType = (GameValueType) kryo.readObject(input, GameValueType.class);
            String str = (String) kryo.readObject(input, String.class);
            return Item.D.F_GAME_VALUE_LEVEL.create(gameValueType, input.readDouble(), str);
        }
    }

    private GameValueLevelItem(GameValueType gameValueType, String str, double d) {
        if (gameValueType == null) {
            throw new IllegalArgumentException("GameValueType is null");
        }
        if (str == null) {
            throw new IllegalArgumentException("Level name is null");
        }
        this.gameValueType = gameValueType;
        this.levelName = str;
        this.delta = d;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        GameValueLevelItem gameValueLevelItem = (GameValueLevelItem) item;
        this.gameValueType = gameValueLevelItem.gameValueType;
        this.levelName = gameValueLevelItem.levelName;
        this.delta = gameValueLevelItem.delta;
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
    public String getAnalyticName() {
        return "gv_level";
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_GAME_VALUE_LEVEL.create(this.gameValueType, this.delta, this.levelName);
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        f2241b.setLength(0);
        f2241b.append(Game.i.gameValueManager.getTitle(this.gameValueType));
        f2241b.append(SequenceUtils.SPACE);
        f2241b.append(Game.i.gameValueManager.formatEffectValue(this.delta, Game.i.gameValueManager.getStockValueConfig(this.gameValueType).units));
        f2241b.append(" (").append(Game.i.localeManager.i18n.get("level")).append(SequenceUtils.SPACE);
        f2241b.append(this.levelName);
        f2241b.append(")");
        return f2241b;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_GAME_VALUE_LEVEL");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.VERY_RARE;
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
        json.writeValue("levelName", this.levelName);
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.GAME_VALUE_LEVEL;
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
    public void fillItemCreationForm(final ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Game value");
        final SelectBox selectBox = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox.setItems(GameValueType.values);
        selectBox.setSelected(this.gameValueType);
        selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.items.GameValueLevelItem.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                GameValueLevelItem.this.gameValueType = (GameValueType) selectBox.getSelected();
                itemCreationOverlay.updateItemIcon();
            }
        });
        itemCreationOverlay.selectBox(selectBox);
        itemCreationOverlay.label("Delta");
        itemCreationOverlay.textField(String.valueOf(this.delta), str -> {
            try {
                this.delta = Double.parseDouble(str);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                f2240a.e("bad value: " + str, new Object[0]);
            }
        });
        itemCreationOverlay.label("Level name");
        itemCreationOverlay.textField(this.levelName, str2 -> {
            try {
                this.levelName = str2;
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                f2240a.e("bad value: " + str2, new Object[0]);
            }
        });
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = new Image(Game.i.assetManager.getDrawable("item-cell-game-value-level"));
        image.setSize(f, f);
        image.setColor(MaterialColor.LIME.P300);
        group.addActor(image);
        Image image2 = new Image(Game.i.gameValueManager.getStockValueConfig(this.gameValueType).createIconForBackgroundWithColor(MaterialColor.LIME.P300, Color.BLACK));
        image2.setPosition(f * 0.25f, f * 0.08f);
        image2.setSize(f * 0.66f, f * 0.66f);
        group.addActor(image2);
        return group;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        if (!super.sameAs(item)) {
            return false;
        }
        GameValueLevelItem gameValueLevelItem = (GameValueLevelItem) item;
        return gameValueLevelItem.gameValueType == this.gameValueType && gameValueLevelItem.delta == this.delta;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GameValueLevelItem$GameValueLevelItemFactory.class */
    public static class GameValueLevelItemFactory implements Item.Factory<GameValueLevelItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public GameValueLevelItem create(GameValueType gameValueType, double d, String str) {
            return new GameValueLevelItem(gameValueType, str, d, (byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GameValueLevelItem fromJson(JsonValue jsonValue) {
            return create(GameValueType.valueOf(jsonValue.getString("gameValueType")), jsonValue.getDouble("delta"), jsonValue.getString("levelName"));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GameValueLevelItem createDefault() {
            return create(GameValueType.values[0], 1.0d, "1.1");
        }
    }
}
