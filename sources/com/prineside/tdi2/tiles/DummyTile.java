package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextArea;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Iterator;
import java.util.Locale;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/DummyTile.class */
public final class DummyTile extends Tile {
    private static final TLog c = TLog.forClass(DummyTile.class);
    public static float DEFAULT_SCALE = 0.75f;

    @NAGS
    private long d;
    public ObjectMap<String, Object> data;
    public boolean selectable;
    public boolean visible;
    public float iconScale;
    public String icon;
    public String description;
    public Color color;

    /* synthetic */ DummyTile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeLong(this.d);
        output.writeBoolean(this.selectable);
        output.writeBoolean(this.visible);
        output.writeFloat(this.iconScale);
        kryo.writeObject(output, this.data);
        kryo.writeObjectOrNull(output, this.icon, String.class);
        kryo.writeObjectOrNull(output, this.description, String.class);
        kryo.writeObject(output, this.color);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readLong();
        this.selectable = input.readBoolean();
        this.visible = input.readBoolean();
        this.iconScale = input.readFloat();
        this.data = (ObjectMap) kryo.readObject(input, ObjectMap.class);
        this.icon = (String) kryo.readObjectOrNull(input, String.class);
        this.description = (String) kryo.readObjectOrNull(input, String.class);
        this.color = (Color) kryo.readObject(input, Color.class);
    }

    private DummyTile() {
        super(TileType.DUMMY);
        this.data = new ObjectMap<>();
        this.selectable = false;
        this.visible = false;
        this.iconScale = DEFAULT_SCALE;
        this.color = Color.WHITE.cpy();
        setId(PMath.generateNewId());
    }

    public final long getId() {
        return this.d;
    }

    public final void setId(long j) {
        this.d = j;
    }

    @Override // com.prineside.tdi2.Tile
    public final CharSequence getDescription() {
        return this.description == null ? "" : this.description;
    }

    public final Object getData(String str) {
        return this.data.get(str, null);
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeSelected() {
        return this.selectable;
    }

    public final void removeData(String str) {
        this.data.remove(str);
    }

    public final void setData(String str, Object obj) {
        switch (str.charAt(0)) {
            case 'S':
            case 's':
                if (obj == null) {
                    obj = "";
                }
                if (obj instanceof String) {
                    this.data.put(str, obj);
                    return;
                } else {
                    c.e("key " + str + " is for String, " + obj.getClass().getSimpleName() + " given", new Object[0]);
                    return;
                }
            case 'b':
                if (obj == null) {
                    obj = Boolean.FALSE;
                }
                if (obj instanceof Boolean) {
                    this.data.put(str, obj);
                    return;
                } else {
                    c.e("key " + str + " is for Boolean, " + obj.getClass().getSimpleName() + " given", new Object[0]);
                    return;
                }
            case 'c':
                if (obj == null) {
                    obj = Color.WHITE.cpy();
                }
                if (obj instanceof Color) {
                    this.data.put(str, obj);
                    return;
                } else {
                    c.e("key " + str + " is for Color, " + obj.getClass().getSimpleName() + " given", new Object[0]);
                    return;
                }
            case 'd':
                if (obj == null) {
                    obj = Double.valueOf(0.0d);
                }
                if (obj instanceof Double) {
                    this.data.put(str, obj);
                    return;
                } else {
                    c.e("key " + str + " is for Double, " + obj.getClass().getSimpleName() + " given", new Object[0]);
                    return;
                }
            case 'i':
                if (obj == null) {
                    obj = 0;
                }
                if (obj instanceof Integer) {
                    this.data.put(str, obj);
                    return;
                } else {
                    c.e("key " + str + " is for Integer, " + obj.getClass().getSimpleName() + " given", new Object[0]);
                    return;
                }
            default:
                c.e("key \"" + str + "\" is invalid and should start with data type (idbscS)", new Object[0]);
                return;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        Table table2 = new Table();
        table.add(table2).padBottom(4.0f).growX().row();
        table2.add().height(1.0f).growX();
        Label label = new Label(Long.toHexString(getId()), Game.i.assetManager.getLabelStyle(18));
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label.setAlignment(16);
        table2.add((Table) label);
        Label label2 = new Label(this.selectable ? "Can be selected" : "Can not be selected", Game.i.assetManager.getLabelStyle(21));
        if (this.selectable) {
            label2.setColor(MaterialColor.GREEN.P500);
        } else {
            label2.setColor(MaterialColor.YELLOW.P500);
        }
        table.add((Table) label2).growX().padBottom(8.0f).row();
        Label label3 = new Label(this.visible ? "Visible" : "Not visible", Game.i.assetManager.getLabelStyle(21));
        if (this.visible) {
            label3.setColor(MaterialColor.GREEN.P500);
        } else {
            label3.setColor(MaterialColor.YELLOW.P500);
        }
        table.add((Table) label3).growX().row();
        Label label4 = new Label(getDescription(), Game.i.assetManager.getLabelStyle(21));
        label4.setAlignment(10);
        label4.setWrap(true);
        table.add((Table) label4).padTop(8.0f).padBottom(8.0f).growX().row();
        Table table3 = new Table();
        table.add(table3).growX().row();
        int i = 0;
        ObjectMap.Entries<String, Object> it = this.data.iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            Table table4 = new Table();
            mapEditorItemInfoMenu.listRowBg(i, table4);
            table3.add(table4).growX().minHeight(32.0f).row();
            Label label5 = new Label((CharSequence) next.key, Game.i.assetManager.getSmallDebugLabelStyle());
            label5.setAlignment(8);
            label5.setColor(MaterialColor.AMBER.P500);
            table4.add((Table) label5).top().left().width(120.0f).padBottom(4.0f).padTop(4.0f);
            String valueOf = String.valueOf(next.value);
            String str = valueOf;
            if (valueOf.length() > 250) {
                str = str.substring(0, User32.VK_PLAY) + "...";
            }
            Label label6 = new Label(str, Game.i.assetManager.getSmallDebugLabelStyle());
            label6.setWrap(true);
            table4.add((Table) label6).top().left().growX().padBottom(4.0f).padTop(4.0f).row();
            i++;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f) {
        Label label;
        Label label2;
        if (this.selectable) {
            Label label3 = new Label("Can be selected", Game.i.assetManager.getLabelStyle(21));
            label = label3;
            label3.setColor(MaterialColor.GREEN.P500);
        } else {
            Label label4 = new Label("Can not be selected", Game.i.assetManager.getLabelStyle(21));
            label = label4;
            label4.setColor(MaterialColor.YELLOW.P500);
        }
        table.add((Table) label).row();
        if (this.visible) {
            Label label5 = new Label("Visible", Game.i.assetManager.getLabelStyle(21));
            label2 = label5;
            label5.setColor(MaterialColor.GREEN.P500);
        } else {
            Label label6 = new Label("Invisible", Game.i.assetManager.getLabelStyle(21));
            label2 = label6;
            label6.setColor(MaterialColor.YELLOW.P500);
        }
        table.add((Table) label2).row();
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        return 1;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_SPECIAL;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        return super.sameAs(tile) && getId() == ((DummyTile) tile).getId();
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        DummyTile dummyTile = (DummyTile) tile;
        setId(dummyTile.getId());
        this.selectable = dummyTile.selectable;
        this.visible = dummyTile.visible;
        this.iconScale = dummyTile.iconScale;
        this.description = dummyTile.description;
        this.icon = dummyTile.icon;
        this.data.clear();
        this.data.putAll(dummyTile.data);
        this.color.set(dummyTile.color);
    }

    public final TextureRegion getTexture() {
        if (this.icon == null) {
            return Game.i.assetManager.getTextureRegion("icon-question");
        }
        ResourcePack.AtlasTextureRegion textureRegionSetThrowing = Game.i.assetManager.getTextureRegionSetThrowing(this.icon, false);
        ResourcePack.AtlasTextureRegion atlasTextureRegion = textureRegionSetThrowing;
        if (textureRegionSetThrowing == null) {
            atlasTextureRegion = Game.i.assetManager.getTextureRegion("icon-question");
        }
        return atlasTextureRegion;
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = new Image(getTexture());
        image.setColor(this.color);
        image.setSize(f * this.iconScale, f * this.iconScale);
        image.setPosition(f * (1.0f - this.iconScale) * 0.5f, f * (1.0f - this.iconScale) * 0.5f);
        group.addActor(image);
        return group;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue(Attribute.ID_ATTR, Long.valueOf(getId()));
        json.writeObjectStart("d");
        ObjectMap.Entries<String, Object> it = this.data.iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            if (((String) next.key).charAt(0) == 'c') {
                json.writeValue((String) next.key, ((Color) next.value).toString());
            } else {
                json.writeValue((String) next.key, next.value);
            }
        }
        json.writeObjectEnd();
        if (this.selectable) {
            json.writeValue("s", Boolean.valueOf(this.selectable));
        }
        if (this.visible) {
            json.writeValue("v", Boolean.valueOf(this.visible));
        }
        if (this.icon != null) {
            json.writeValue(FlexmarkHtmlConverter.I_NODE, this.icon);
        }
        json.writeValue("is", Float.valueOf(this.iconScale));
        if (this.description != null) {
            json.writeValue("dsc", this.description);
        }
        if (this.color != null) {
            json.writeValue("c", this.color.toString());
        }
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        if (this.visible || drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            batch.setColor(this.color);
            batch.draw(getTexture(), f + (f3 * (1.0f - this.iconScale) * 0.5f), f2 + (f4 * (1.0f - this.iconScale) * 0.5f), f3 * this.iconScale, f4 * this.iconScale);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0223. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
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
        itemCreationOverlay.toggle("Can be selected", this.selectable, bool -> {
            this.selectable = bool.booleanValue();
        });
        itemCreationOverlay.toggle("Visible", this.visible, bool2 -> {
            this.visible = bool2.booleanValue();
        });
        itemCreationOverlay.label("Icon scale");
        itemCreationOverlay.textFieldOfWidth(new StringBuilder().append(this.iconScale).toString(), 300.0f, str2 -> {
            try {
                this.iconScale = Float.parseFloat(str2);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
            }
        });
        itemCreationOverlay.label("Description");
        itemCreationOverlay.textFieldOfWidth(this.description == null ? "" : this.description, 800.0f, str3 -> {
            this.description = str3.equals("") ? null : str3;
        });
        itemCreationOverlay.label("Icon");
        itemCreationOverlay.textField(this.icon == null ? "" : this.icon, str4 -> {
            this.icon = str4.equals("") ? null : str4;
            itemCreationOverlay.updateItemIcon();
        });
        itemCreationOverlay.label("Color");
        itemCreationOverlay.textField(this.color.toString().toUpperCase(Locale.US), str5 -> {
            try {
                this.color.set(Color.valueOf(str5));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
            }
        });
        itemCreationOverlay.label("Data");
        Table table = new Table();
        itemCreationOverlay.form.add(table).width(800.0f).top().left().row();
        ObjectMap.Entries<String, Object> it = this.data.iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            final String str6 = (String) next.key;
            Table table2 = new Table();
            table.add(table2).expandX().fillX().row();
            Table table3 = new Table();
            table2.add(table3).top().left().width(200.0f).padRight(10.0f).minHeight(48.0f);
            Label label = new Label((CharSequence) next.key, Game.i.assetManager.getLabelStyle(24));
            label.setAlignment(8);
            label.setColor(MaterialColor.AMBER.P500);
            table3.add((Table) label).top().left().width(200.0f).row();
            Label label2 = new Label("", Game.i.assetManager.getLabelStyle(21));
            label2.setAlignment(8);
            label2.setColor(MaterialColor.GREY.P500);
            table3.add((Table) label2).top().left().row();
            switch (((String) next.key).charAt(0)) {
                case 'S':
                    label2.setText("multiline string");
                    break;
                case 'b':
                    label2.setText("boolean");
                    break;
                case 'c':
                    label2.setText("color");
                    break;
                case 'd':
                    label2.setText("double");
                    break;
                case 'i':
                    label2.setText("int");
                    break;
                case 's':
                    label2.setText("string");
                    break;
            }
            Table table4 = new Table();
            table2.add(table4).top().left().width(532.0f).padBottom(10.0f).padRight(10.0f).minHeight(48.0f);
            switch (((String) next.key).charAt(0)) {
                case 'S':
                    TextField.TextFieldStyle textFieldStyleWithFontAndVariant = Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true);
                    textFieldStyleWithFontAndVariant.background.setLeftWidth(textFieldStyleWithFontAndVariant.background.getLeftWidth() + 10.0f);
                    textFieldStyleWithFontAndVariant.background.setRightWidth(textFieldStyleWithFontAndVariant.background.getRightWidth() + 10.0f);
                    textFieldStyleWithFontAndVariant.background.setBottomHeight(textFieldStyleWithFontAndVariant.background.getBottomHeight() + 5.0f);
                    textFieldStyleWithFontAndVariant.background.setTopHeight(textFieldStyleWithFontAndVariant.background.getTopHeight() + 10.0f);
                    final TextArea textArea = new TextArea((String) next.value, textFieldStyleWithFontAndVariant);
                    ScrollPane scrollPane = new ScrollPane(textArea, Game.i.assetManager.getScrollPaneStyle(12.0f));
                    UiUtils.enableMouseMoveScrollFocus(scrollPane);
                    textArea.setOnlyFontChars(false);
                    textArea.setProgrammaticChangeEvents(true);
                    textArea.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.DummyTile.5
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                DummyTile.this.setData(str6, textArea.getText());
                            } catch (Exception e) {
                                DummyTile.c.e("invalid value", e);
                            }
                        }
                    });
                    table4.add((Table) scrollPane).height(144.0f).top().left().expandX().fillX();
                    break;
                case 'c':
                    final Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(next.value.toString().toUpperCase(), itemCreationOverlay.textFieldStyle);
                    textFieldXPlatform.setSize(200.0f, 48.0f);
                    textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.DummyTile.3
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                Color valueOf = Color.valueOf(textFieldXPlatform.getText());
                                DummyTile.this.setData(str6, valueOf);
                                image.setColor(valueOf);
                            } catch (Exception e) {
                                DummyTile.c.e("invalid value", e);
                            }
                        }
                    });
                    table4.add((Table) textFieldXPlatform).size(200.0f, 48.0f).top().left();
                    image.setColor((Color) next.value);
                    table4.add((Table) image).size(48.0f).padLeft(10.0f).top().left();
                    table4.add().expandX().fillX();
                    break;
                case 'd':
                    final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(String.valueOf(next.value), itemCreationOverlay.textFieldStyle);
                    textFieldXPlatform2.setSize(200.0f, 48.0f);
                    textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.DummyTile.2
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                DummyTile.this.setData(str6, Double.valueOf(Double.parseDouble(textFieldXPlatform2.getText())));
                            } catch (Exception e) {
                                DummyTile.c.e("invalid value", e);
                            }
                        }
                    });
                    table4.add((Table) textFieldXPlatform2).size(200.0f, 48.0f).top().left();
                    table4.add().expandX().fillX();
                    break;
                case 'i':
                    final TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform(String.valueOf(next.value), itemCreationOverlay.textFieldStyle);
                    textFieldXPlatform3.setSize(200.0f, 48.0f);
                    textFieldXPlatform3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.DummyTile.1
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                DummyTile.this.setData(str6, Integer.valueOf(Integer.parseInt(textFieldXPlatform3.getText())));
                            } catch (Exception e) {
                                DummyTile.c.e("invalid value", e);
                            }
                        }
                    });
                    table4.add((Table) textFieldXPlatform3).size(200.0f, 48.0f).top().left();
                    table4.add().expandX().fillX();
                    break;
                case 's':
                    final TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform((String) next.value, itemCreationOverlay.textFieldStyle);
                    textFieldXPlatform4.setSize(590.0f, 48.0f);
                    textFieldXPlatform4.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.DummyTile.4
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                DummyTile.this.setData(str6, textFieldXPlatform4.getText());
                            } catch (Exception e) {
                                DummyTile.c.e("invalid value", e);
                            }
                        }
                    });
                    table4.add((Table) textFieldXPlatform4).height(48.0f).top().left().expandX().fillX();
                    break;
            }
            RectButton rectButton = new RectButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                removeData(str6);
                itemCreationOverlay.updateForm();
            });
            rectButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 8.0f, 8.0f, 32.0f, 32.0f);
            rectButton.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 48.0f, 48.0f);
            rectButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, MaterialColor.RED.P900);
            table2.add((Table) rectButton).top().left().size(48.0f).row();
        }
        Table table5 = new Table();
        table.add(table5).expandX().fillX().row();
        TextFieldXPlatform textFieldXPlatform5 = new TextFieldXPlatform("", itemCreationOverlay.textFieldStyle);
        textFieldXPlatform5.setMessageText("idbscS");
        table5.add((Table) textFieldXPlatform5).size(200.0f, 48.0f).padRight(10.0f);
        RectButton rectButton2 = new RectButton("", Game.i.assetManager.getLabelStyle(21), () -> {
            if (textFieldXPlatform5.getText().length() > 1) {
                setData(textFieldXPlatform5.getText(), null);
                itemCreationOverlay.updateForm();
            }
        });
        rectButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-plus"), 32.0f, 8.0f, 32.0f, 32.0f);
        rectButton2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 48.0f, 48.0f);
        rectButton2.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P900);
        table5.add((Table) rectButton2).top().left().size(96.0f, 48.0f);
        table5.add().expandX().fillX();
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 1));
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        return 0.0d;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/DummyTile$DummyTileFactory.class */
    public static class DummyTileFactory extends Tile.Factory.AbstractFactory<DummyTile> {
        public DummyTileFactory() {
            super(TileType.DUMMY);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            return 0;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public DummyTile create() {
            return new DummyTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public DummyTile fromJson(JsonValue jsonValue) {
            DummyTile dummyTile = (DummyTile) super.fromJson(jsonValue);
            JsonValue jsonValue2 = jsonValue.get("d");
            if (jsonValue2 != null) {
                dummyTile.setId(jsonValue2.getLong(Attribute.ID_ATTR, PMath.generateNewId()));
                JsonValue jsonValue3 = jsonValue2.get("d");
                if (jsonValue3 != null) {
                    Iterator<JsonValue> iterator2 = jsonValue3.iterator2();
                    while (iterator2.hasNext()) {
                        JsonValue next = iterator2.next();
                        switch (next.name.charAt(0)) {
                            case 'S':
                            case 's':
                                dummyTile.data.put(next.name, next.asString());
                                break;
                            case 'b':
                                dummyTile.data.put(next.name, Boolean.valueOf(next.asBoolean()));
                                break;
                            case 'c':
                                try {
                                    dummyTile.data.put(next.name, Color.valueOf(next.asString()));
                                    break;
                                } catch (Exception unused) {
                                    break;
                                }
                            case 'd':
                                dummyTile.data.put(next.name, Double.valueOf(next.asDouble()));
                                break;
                            case 'i':
                                dummyTile.data.put(next.name, Integer.valueOf(next.asInt()));
                                break;
                        }
                    }
                }
                dummyTile.visible = jsonValue2.getBoolean("v", false);
                dummyTile.selectable = jsonValue2.getBoolean("s", false);
                dummyTile.icon = jsonValue2.getString(FlexmarkHtmlConverter.I_NODE, null);
                dummyTile.iconScale = jsonValue2.getFloat("is", DummyTile.DEFAULT_SCALE);
                dummyTile.description = jsonValue2.getString("dsc", null);
                try {
                    dummyTile.color.set(Color.valueOf(jsonValue2.getString("c", Color.WHITE.toString())));
                } catch (Exception unused2) {
                }
            }
            return dummyTile;
        }
    }
}
