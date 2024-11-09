package com.prineside.tdi2.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
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
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextArea;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TableButton;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.FullScreenTextEditor;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/ScriptTile.class */
public final class ScriptTile extends Tile {

    @NAGS
    private long d;

    @NAGS
    private String e;
    private static final TLog c = TLog.forClass(ScriptTile.class);
    private static final StringBuilder f = new StringBuilder();

    /* synthetic */ ScriptTile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeLong(this.d);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readLong();
    }

    private ScriptTile() {
        super(TileType.SCRIPT);
        setId(PMath.generateNewId());
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("ID");
        itemCreationOverlay.textFieldEndRow(String.valueOf(getId()), 300.0f, str -> {
            try {
                setId(Long.parseLong(str));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str, new Object[0]);
            }
        }, false);
        PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-restart"), () -> {
            setId(PMath.generateNewId());
            itemCreationOverlay.updateForm();
        }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
        paddedImageButton.setIconSize(40.0f, 40.0f);
        paddedImageButton.setIconPosition(12.0f, 12.0f);
        itemCreationOverlay.form.add((Table) paddedImageButton).top().left().padLeft(10.0f).size(64.0f).row();
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f2) {
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        Table table2 = new Table();
        table.add(table2).padTop(8.0f).padBottom(4.0f).growX().row();
        Label label = new Label("Source code".toUpperCase(), Game.i.assetManager.getLabelStyle(18));
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label.setAlignment(8);
        table2.add((Table) label).growX();
        Label label2 = new Label(Long.toHexString(getId()), Game.i.assetManager.getLabelStyle(18));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label2.setAlignment(16);
        table2.add((Table) label2);
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(Game.i.assetManager.getDebugFont(false), Color.WHITE, new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()), new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(MaterialColor.BLUE.P500), new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(0.0f, 0.0f, 0.0f, 0.24f)));
        textFieldStyle.cursor.setMinWidth(2.0f);
        textFieldStyle.background.setLeftWidth(textFieldStyle.background.getLeftWidth() + 24.0f);
        textFieldStyle.background.setRightWidth(textFieldStyle.background.getRightWidth() + 24.0f);
        textFieldStyle.background.setBottomHeight(textFieldStyle.background.getBottomHeight() + 10.0f);
        textFieldStyle.background.setTopHeight(textFieldStyle.background.getTopHeight() + 10.0f);
        Group group = new Group();
        group.setTransform(false);
        table.add((Table) group).size(table.getWidth(), 364.0f).row();
        TextArea textArea = new TextArea(getScript(), textFieldStyle);
        textArea.setOnlyFontChars(false);
        textArea.setProgrammaticChangeEvents(true);
        textArea.setSize(table.getWidth() + 48.0f, 364.0f);
        textArea.setPosition(-24.0f, 0.0f);
        group.addActor(textArea);
        TableButton tableButton = new TableButton(() -> {
            FullScreenTextEditor.i().show(textArea.getText(), new ObjectConsumer<String>(this) { // from class: com.prineside.tdi2.tiles.ScriptTile.1
                @Override // com.prineside.tdi2.utils.ObjectConsumer
                public void accept(String str) {
                    textArea.setText(str);
                }
            });
        });
        tableButton.add((TableButton) new Image(Game.i.assetManager.getDrawable("icon-enlarge"))).size(40.0f);
        tableButton.setSize(64.0f, 64.0f);
        tableButton.setPosition((table.getWidth() - 64.0f) + 24.0f, 300.0f);
        tableButton.setContentColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P800, Color.WHITE);
        tableButton.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.56f)));
        group.addActor(tableButton);
        Table table3 = new Table();
        table.add(table3).growX().padTop(8.0f).row();
        RectButton rectButton = new RectButton(Game.i.localeManager.i18n.get("do_save"), Game.i.assetManager.getLabelStyle(21), () -> {
            String text = textArea.getText();
            if (text.length() == 0) {
                c.i("removed code", new Object[0]);
                if (getScript() != null) {
                    setScript(null);
                    DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.TILE);
                    int i = 0;
                    while (true) {
                        if (i >= itemsByType.size) {
                            break;
                        }
                        TileItem tileItem = (TileItem) itemsByType.items[i].getItem();
                        if (tileItem.tile.type == TileType.SCRIPT) {
                            ScriptTile scriptTile = (ScriptTile) tileItem.tile;
                            if (scriptTile.getId() == getId()) {
                                scriptTile.setScript(null);
                                ProgressPrefs.i().requireSave();
                                c.i("updated real item", new Object[0]);
                                break;
                            }
                        }
                        i++;
                    }
                    mapEditorItemInfoMenu.notifySelectedElementChanged();
                    mapEditorItemInfoMenu.update();
                    Game.i.soundManager.playStatic(StaticSoundType.SUCCESS);
                    return;
                }
                return;
            }
            if (text.length() > 100000) {
                Notifications.i().add("Script is too long - max 100k characters per tile", null, null, StaticSoundType.FAIL);
                textArea.setText("");
                return;
            }
            setScript(text);
            DelayedRemovalArray<ItemStack> itemsByType2 = Game.i.progressManager.getItemsByType(ItemType.TILE);
            int i2 = 0;
            while (true) {
                if (i2 >= itemsByType2.size) {
                    break;
                }
                TileItem tileItem2 = (TileItem) itemsByType2.items[i2].getItem();
                if (tileItem2.tile.type == TileType.SCRIPT) {
                    ScriptTile scriptTile2 = (ScriptTile) tileItem2.tile;
                    if (scriptTile2.getId() == getId()) {
                        scriptTile2.setScript(text);
                        ProgressPrefs.i().requireSave();
                        break;
                    }
                }
                i2++;
            }
            mapEditorItemInfoMenu.notifySelectedElementChanged();
            mapEditorItemInfoMenu.update();
            Game.i.soundManager.playStatic(StaticSoundType.SUCCESS);
        });
        rectButton.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, MaterialColor.GREY.P800);
        table3.add((Table) rectButton).growX().height(40.0f).padRight(2.0f);
        RectButton rectButton2 = new RectButton("Copy", Game.i.assetManager.getLabelStyle(21), () -> {
            if (getScript() != null) {
                Gdx.app.getClipboard().setContents(getScript());
                Notifications.i().add(Game.i.localeManager.i18n.get("copied_to_clipboard"), Game.i.assetManager.getDrawable("icon-check"), MaterialColor.BLUE_GREY.P800, StaticSoundType.SUCCESS);
            }
        });
        rectButton2.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.GREY.P800);
        table3.add((Table) rectButton2).growX().height(40.0f).padRight(2.0f);
        RectButton rectButton3 = new RectButton("Paste", Game.i.assetManager.getLabelStyle(21), () -> {
            String contents = Gdx.app.getClipboard().getContents();
            if (contents != null && contents.length() > 0) {
                String replaceAll = contents.replaceAll("\r", "");
                setScript(replaceAll);
                DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.TILE);
                int i = 0;
                while (true) {
                    if (i >= itemsByType.size) {
                        break;
                    }
                    TileItem tileItem = (TileItem) itemsByType.items[i].getItem();
                    if (tileItem.tile.type == TileType.SCRIPT) {
                        ScriptTile scriptTile = (ScriptTile) tileItem.tile;
                        if (scriptTile.getId() == getId()) {
                            scriptTile.setScript(replaceAll);
                            ProgressPrefs.i().requireSave();
                            break;
                        }
                    }
                    i++;
                }
                mapEditorItemInfoMenu.notifySelectedElementChanged();
                mapEditorItemInfoMenu.update();
                Game.i.soundManager.playStatic(StaticSoundType.SUCCESS);
            }
        });
        rectButton3.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.GREY.P800);
        table3.add((Table) rectButton3).growX().height(40.0f);
    }

    public final long getId() {
        return this.d;
    }

    public final void setId(long j) {
        this.d = j;
    }

    public final String getScript() {
        return this.e;
    }

    public final void setScript(String str) {
        this.e = str;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        return super.sameAs(tile) && getId() == ((ScriptTile) tile).getId();
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.KIND) {
            return 30000;
        }
        return getRarity().ordinal() * 1000;
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
    public final void from(Tile tile) {
        super.from(tile);
        ScriptTile scriptTile = (ScriptTile) tile;
        setId(scriptTile.getId());
        this.e = scriptTile.e;
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f2, float f3, float f4, float f5, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f2, f3, f4, f5, map, drawMode);
        if (a(drawMode)) {
            batch.draw(Game.i.tileManager.F.SCRIPT.f3150a, f2, f3, f4, f5);
        }
    }

    private StringBuilder a() {
        f.setLength(0);
        if (this.e == null) {
            return f;
        }
        int i = 0;
        int i2 = 0;
        boolean z = true;
        int length = this.e.length();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = this.e.charAt(i3);
            if (i2 < 13 && charAt != '\n') {
                f.append(charAt);
                if (charAt != '-' && charAt != '[') {
                    z = false;
                }
                i2++;
            }
            if (charAt == '\n') {
                if (z && i2 != 0) {
                    f.setLength(0);
                } else {
                    f.append('\n');
                    i++;
                    if (i == 6) {
                        break;
                    }
                }
                i2 = 0;
            }
        }
        return f;
    }

    private static boolean a(MapRenderingSystem.DrawMode drawMode) {
        return drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR;
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawExtras(Batch batch, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        if (this.e != null && this.e.length() != 0 && a(drawMode)) {
            float f6 = f5 * 0.0078125f;
            BitmapFont smallDebugFont = Game.i.assetManager.getSmallDebugFont();
            smallDebugFont.setColor(Color.WHITE);
            float f7 = smallDebugFont.getData().scaleX;
            float f8 = smallDebugFont.getData().scaleY;
            smallDebugFont.getData().setScale(f7 * f6, f8 * f6);
            smallDebugFont.draw(batch, a(), f2 + (16.0f * f6), (f3 + f5) - (16.0f * f6), f4, 8, false);
            smallDebugFont.getData().setScale(f7, f8);
            smallDebugFont.setColor(Color.WHITE);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f2) {
        float f3 = f2 / 128.0f;
        Group group = new Group();
        if (f3 != 1.0f) {
            group.setTransform(true);
            group.setScale(f3);
        } else {
            group.setTransform(false);
        }
        Image image = new Image(Game.i.tileManager.F.SCRIPT.f3150a);
        image.setSize(128.0f, 128.0f);
        group.addActor(image);
        Label label = new Label(a(), Game.i.assetManager.getSmallDebugLabelStyle());
        label.setPosition(16.0f, 16.0f);
        label.setSize(96.0f, 96.0f);
        label.setAlignment(10);
        group.addActor(label);
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue(Attribute.ID_ATTR, Long.valueOf(getId()));
        if (this.e != null) {
            json.writeValue("script", this.e);
        }
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 1000));
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeSelected() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        return 0.1d;
    }

    @Override // com.prineside.tdi2.Tile
    public final String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (id: " + getId() + ", has script: " + (this.e == null ? "false" : "true") + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/ScriptTile$ScriptTileFactory.class */
    public static class ScriptTileFactory extends Tile.Factory.AbstractFactory<ScriptTile> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f3150a;

        public ScriptTileFactory() {
            super(TileType.SCRIPT);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            return 0;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3150a = Game.i.assetManager.getTextureRegion("tile-type-script");
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public ScriptTile create() {
            return new ScriptTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public ScriptTile fromJson(JsonValue jsonValue) {
            ScriptTile scriptTile = (ScriptTile) super.fromJson(jsonValue);
            if (jsonValue.has("d")) {
                scriptTile.setId(jsonValue.get("d").getLong(Attribute.ID_ATTR, PMath.generateNewId()));
                scriptTile.e = jsonValue.get("d").getString("script", null);
            }
            return scriptTile;
        }
    }
}
