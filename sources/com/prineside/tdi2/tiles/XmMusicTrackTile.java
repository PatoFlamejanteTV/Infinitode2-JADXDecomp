package com.prineside.tdi2.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.FileChooser;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.ByteArrayOutputStream;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/XmMusicTrackTile.class */
public final class XmMusicTrackTile extends Tile {
    private static final TLog c = TLog.forClass(XmMusicTrackTile.class);
    private static final Color[] d = {MaterialColor.PINK.P500, MaterialColor.PURPLE.P500, MaterialColor.DEEP_PURPLE.P500, MaterialColor.INDIGO.P500, MaterialColor.BLUE.P500, MaterialColor.CYAN.P500, MaterialColor.TEAL.P500, MaterialColor.GREEN.P500, MaterialColor.YELLOW.P500, MaterialColor.ORANGE.P500, MaterialColor.BROWN.P500};

    @NAGS
    private long e;

    @NAGS
    private String f;

    @NAGS
    private Color[] g;

    @NAGS
    private boolean h;

    @NAGS
    private String i;

    @NAGS
    private Array<Module.TrackInfoEntry> j;

    /* synthetic */ XmMusicTrackTile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeLong(this.e);
        kryo.writeObjectOrNull(output, this.i, String.class);
        kryo.writeObjectOrNull(output, this.j, Array.class);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readLong();
        this.i = (String) kryo.readObjectOrNull(input, String.class);
        this.j = (Array) kryo.readObjectOrNull(input, Array.class);
    }

    private XmMusicTrackTile() {
        super(TileType.XM_MUSIC_TRACK);
        setId(generateNewId());
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, final MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        Table table2 = new Table();
        table.add(table2).height(32.0f).growX().padLeft(-24.0f).padRight(-24.0f).row();
        Color[] idColors = getIdColors();
        for (Color color : idColors) {
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setColor(color);
            table2.add((Table) image).height(8.0f).growX();
        }
        table2.row();
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        table2.add((Table) image2).colspan(idColors.length).growX().height(4.0f);
        Label label = new Label(Game.i.localeManager.i18n.get("music_tile_editor_title_copy_from_level").toUpperCase(Locale.US), Game.i.assetManager.getLabelStyle(18));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label.setAlignment(8);
        table.add((Table) label).growX().row();
        final SelectBox selectBox = new SelectBox(mapEditorItemInfoMenu.selectBoxStyle);
        selectBox.setName("map_editor_menu_music_copy_from_select");
        Array array = new Array();
        array.add("");
        for (int i = 0; i < Game.i.basicLevelManager.levelsOrdered.size; i++) {
            BasicLevel basicLevel = Game.i.basicLevelManager.levelsOrdered.items[i];
            if (Game.i.basicLevelManager.isOpened(basicLevel) && Game.i.basicLevelManager.isLevelVisible(basicLevel) && basicLevel.getMap().getMusicTile() != null) {
                array.add(basicLevel.name);
            }
        }
        selectBox.setItems(array);
        table.add((Table) selectBox).height(40.0f).growX().padBottom(8.0f).padTop(4.0f).row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("music_tile_editor_title_load_from_url").toUpperCase(Locale.US), Game.i.assetManager.getLabelStyle(18));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label2.setAlignment(8);
        table.add((Table) label2).growX().row();
        Table table3 = new Table();
        table.add(table3).padBottom(8.0f).padTop(4.0f).growX().row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", mapEditorItemInfoMenu.textFieldStyle);
        table3.add((Table) textFieldXPlatform).growX().height(40.0f);
        RectButton rectButton = new RectButton(Game.i.localeManager.i18n.get("to_load"), Game.i.assetManager.getLabelStyle(21), null);
        table3.add((Table) rectButton).height(40.0f).width(64.0f);
        Label label3 = new Label(Game.i.localeManager.i18n.get("music_tile_editor_title_music_base64").toUpperCase(Locale.US), Game.i.assetManager.getLabelStyle(18));
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label3.setAlignment(8);
        table.add((Table) label3).growX().row();
        final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(getTrackBase64(), mapEditorItemInfoMenu.textFieldStyle);
        textFieldXPlatform2.setProgrammaticChangeEvents(true);
        table.add((Table) textFieldXPlatform2).growX().height(40.0f).padBottom(8.0f).padTop(4.0f).row();
        textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                Threads i2 = Threads.i();
                TextFieldXPlatform textFieldXPlatform3 = textFieldXPlatform2;
                MapEditorItemInfoMenu mapEditorItemInfoMenu2 = mapEditorItemInfoMenu;
                i2.runOnMainThread(() -> {
                    String text = textFieldXPlatform3.getText();
                    if (text.length() == 0) {
                        XmMusicTrackTile.c.i("removed track", new Object[0]);
                        if (XmMusicTrackTile.this.getTrackBase64() != null) {
                            TileItem tileItem = (TileItem) Game.i.progressManager.getItem(Item.D.F_TILE.create(XmMusicTrackTile.this));
                            if (tileItem != null) {
                                ((XmMusicTrackTile) tileItem.tile).setTrack(null);
                                ProgressPrefs.i().requireSave();
                                XmMusicTrackTile.c.i("updated real item", new Object[0]);
                            }
                            XmMusicTrackTile.this.setTrack(null);
                            mapEditorItemInfoMenu2.notifySelectedElementChanged();
                            mapEditorItemInfoMenu2.update();
                            return;
                        }
                        return;
                    }
                    if (text.length() > 524288) {
                        Notifications.i().add(Game.i.localeManager.i18n.format("track_string_too_long", Integer.valueOf(User32.WM_XBUTTONUP)), null, null, StaticSoundType.FAIL);
                        textFieldXPlatform3.setText("");
                        return;
                    }
                    String trackBase64 = XmMusicTrackTile.this.getTrackBase64();
                    XmMusicTrackTile.this.setTrack(text);
                    try {
                        if (XmMusicTrackTile.this.getModule() == null) {
                            throw new IllegalArgumentException();
                        }
                        TileItem tileItem2 = (TileItem) Game.i.progressManager.getItem(Item.D.F_TILE.create(XmMusicTrackTile.this));
                        if (tileItem2 != null) {
                            ((XmMusicTrackTile) tileItem2.tile).setTrack(text);
                            ProgressPrefs.i().requireSave();
                            XmMusicTrackTile.c.i("updated real item", new Object[0]);
                        }
                        mapEditorItemInfoMenu2.notifySelectedElementChanged();
                        mapEditorItemInfoMenu2.update();
                    } catch (Exception unused) {
                        XmMusicTrackTile.this.setTrack(trackBase64);
                    }
                });
            }
        });
        selectBox.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.2
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                String str = (String) selectBox.getSelected();
                try {
                    textFieldXPlatform2.setText(Game.i.basicLevelManager.getLevel(str).getMap().getMusicTile().getTrackBase64());
                    mapEditorItemInfoMenu.notifySelectedElementChanged();
                    mapEditorItemInfoMenu.update();
                } catch (Exception unused) {
                    XmMusicTrackTile.c.e("failed to copy xm from level " + str, new Object[0]);
                }
            }
        });
        rectButton.setClickHandler(() -> {
            final String text = textFieldXPlatform.getText();
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.GET);
            httpRequest.setUrl(text);
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.3
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    byte[] result = httpResponse.getResult();
                    Threads i2 = Threads.i();
                    TextFieldXPlatform textFieldXPlatform3 = textFieldXPlatform2;
                    MapEditorItemInfoMenu mapEditorItemInfoMenu2 = mapEditorItemInfoMenu;
                    String str = text;
                    i2.postRunnable(() -> {
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                            ZipEntry zipEntry = new ZipEntry("module");
                            zipEntry.setSize(result.length);
                            zipOutputStream.putNextEntry(zipEntry);
                            zipOutputStream.write(result);
                            zipOutputStream.close();
                            byteArrayOutputStream.close();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            String base64 = StringFormatter.toBase64(byteArray, 0, byteArray.length);
                            Game.i.musicManager.getModule(base64);
                            textFieldXPlatform3.setText(base64);
                            mapEditorItemInfoMenu2.notifySelectedElementChanged();
                            mapEditorItemInfoMenu2.update();
                        } catch (Exception e) {
                            Notifications.i().add("Failed to load module: " + e.getMessage(), null, MaterialColor.RED.P500, StaticSoundType.FAIL);
                            XmMusicTrackTile.c.e("failed to load music from url " + str, e);
                        }
                    });
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    Notifications.i().add("Request failed: " + th.getMessage(), null, MaterialColor.RED.P500, StaticSoundType.FAIL);
                    XmMusicTrackTile.c.e("request for music at " + text + " failed", th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    XmMusicTrackTile.c.e("request for music at " + text + " cancelled", new Object[0]);
                }
            });
        });
        if (Game.i.actionResolver.getFileChooser() != null && Game.i.actionResolver.getFileChooser().intentSupported(FileChooser.FileChooseIntent.OPEN)) {
            table.add((Table) new RectButton(Game.i.localeManager.i18n.get("to_select_file"), Game.i.assetManager.getLabelStyle(21), () -> {
                FileChooser.Configuration configuration = new FileChooser.Configuration();
                configuration.intent = FileChooser.FileChooseIntent.OPEN;
                configuration.title = "Select music file";
                Game.i.actionResolver.getFileChooser().choose(configuration, new FileChooser.Callback() { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.4
                    @Override // com.prineside.tdi2.utils.FileChooser.Callback
                    public void onFileChoose(FileHandle fileHandle) {
                        String lowerCase;
                        Module module = null;
                        String str = null;
                        try {
                            lowerCase = fileHandle.extension().toLowerCase(Locale.US);
                        } catch (Exception e) {
                            XmMusicTrackTile.c.e("failed to load music from file " + fileHandle, e);
                        }
                        if (!lowerCase.equals("mod") && !lowerCase.equals("xm") && !lowerCase.equals("s3m")) {
                            Notifications.i().add("Supported files are .mod / .xm / .s3m", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                            return;
                        }
                        if (fileHandle.length() > 1048576) {
                            Notifications.i().add("Max file size is 1Mb", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                            return;
                        }
                        try {
                            module = new Module(fileHandle.read());
                            str = Module.toZippedBase64(fileHandle.readBytes());
                        } catch (Exception unused) {
                            module = Module.fromZipInputStream(fileHandle.read());
                            byte[] readBytes = fileHandle.readBytes();
                            str = StringFormatter.toBase64(readBytes, 0, readBytes.length);
                        }
                        if (module != null) {
                            TileItem tileItem = (TileItem) Game.i.progressManager.getItem(Item.D.F_TILE.create(XmMusicTrackTile.this));
                            if (tileItem == null) {
                                XmMusicTrackTile.c.i("real item not found", new Object[0]);
                            } else {
                                ((XmMusicTrackTile) tileItem.tile).setTrack(str);
                                ProgressPrefs.i().requireSave();
                                XmMusicTrackTile.c.i("updated real item", new Object[0]);
                            }
                            textFieldXPlatform2.setText(str);
                            mapEditorItemInfoMenu.notifySelectedElementChanged();
                            mapEditorItemInfoMenu.update();
                            return;
                        }
                        Threads.i().runOnMainThread(() -> {
                            Notifications.i().add("Failed to load the track from file, check console", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                        });
                    }

                    @Override // com.prineside.tdi2.utils.FileChooser.Callback
                    public void onCancel() {
                        XmMusicTrackTile.c.i("file selection cancelled", new Object[0]);
                    }

                    @Override // com.prineside.tdi2.utils.FileChooser.Callback
                    public void onError(Exception exc) {
                        XmMusicTrackTile.c.e("error selecting a file", exc);
                    }
                });
            })).growX().height(48.0f).padBottom(15.0f).row();
        }
        Module module = null;
        try {
            module = getModule();
            if (Game.i.settingsManager.isMusicEnabled() && module != null && (Game.i.musicManager.getPlayingMusic() == null || !Game.i.musicManager.getPlayingMusic().songName.equals(module.songName))) {
                Game.i.musicManager.playMusic(module);
                Game.i.musicManager.setVolumeToStartNewTrack();
            }
        } catch (Exception unused) {
        }
        if (module != null) {
            Label label4 = new Label(module.songName, Game.i.assetManager.getLabelStyle(24));
            label4.setColor(MaterialColor.AMBER.P500);
            label4.setAlignment(8);
            table.add((Table) label4).growX().row();
            Array.ArrayIterator<Module.TrackInfoEntry> it = module.getInfoFromInstrumentNames().iterator();
            while (it.hasNext()) {
                final Module.TrackInfoEntry next = it.next();
                final LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(next.value, 21, 18, 352.0f);
                switch (next.type) {
                    case AUTHOR:
                        Label label5 = new Label(Game.i.localeManager.i18n.get("music_track_author") + ":", Game.i.assetManager.getLabelStyle(18));
                        label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        table.add((Table) label5).growX().padTop(8.0f).row();
                        break;
                    case GROUP:
                        Label label6 = new Label(Game.i.localeManager.i18n.get("music_track_group") + ":", Game.i.assetManager.getLabelStyle(18));
                        label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        table.add((Table) label6).growX().padTop(8.0f).row();
                        break;
                    case LINK:
                        limitedWidthLabel.setTouchable(Touchable.enabled);
                        limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.5
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                Gdx.f881net.openURI(next.getCompleteLink());
                            }
                        });
                        limitedWidthLabel.addListener(new InputListener(this) { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.6
                            @Override // com.prineside.tdi2.scene2d.InputListener
                            public void enter(InputEvent inputEvent, float f, float f2, int i2, @Null Actor actor) {
                                limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P300);
                            }

                            @Override // com.prineside.tdi2.scene2d.InputListener
                            public void exit(InputEvent inputEvent, float f, float f2, int i2, @Null Actor actor) {
                                limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                            }
                        });
                        limitedWidthLabel.setText(((Object) limitedWidthLabel.getText()) + Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-link-out>").toString());
                        limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                        break;
                }
                limitedWidthLabel.setAlignment(8);
                table.add((Table) limitedWidthLabel).growX().row();
            }
        }
        if (Game.i.actionResolver.getFileChooser() != null && Game.i.actionResolver.getFileChooser().intentSupported(FileChooser.FileChooseIntent.SAVE)) {
            table.add((Table) new RectButton(Game.i.localeManager.i18n.get("save_as_file"), Game.i.assetManager.getLabelStyle(21), () -> {
                FileChooser.Configuration configuration = new FileChooser.Configuration();
                configuration.intent = FileChooser.FileChooseIntent.SAVE;
                configuration.title = "Save as...";
                Game.i.actionResolver.getFileChooser().choose(configuration, new FileChooser.Callback() { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.7
                    @Override // com.prineside.tdi2.utils.FileChooser.Callback
                    public void onFileChoose(FileHandle fileHandle) {
                        try {
                            FileHandle sibling = fileHandle.sibling(fileHandle.name() + "." + XmMusicTrackTile.this.getModule().getFileExtension());
                            fileHandle = sibling;
                            sibling.writeBytes(StringFormatter.fromBase64(XmMusicTrackTile.this.getTrackBase64()), false);
                            Notifications.i().add("Saved as " + fileHandle, Game.i.assetManager.getDrawable("icon-check"), MaterialColor.GREEN.P800, StaticSoundType.SUCCESS);
                        } catch (Exception e) {
                            XmMusicTrackTile.c.e("failed to save as file " + fileHandle, e);
                        }
                    }

                    @Override // com.prineside.tdi2.utils.FileChooser.Callback
                    public void onCancel() {
                        XmMusicTrackTile.c.i("file selection cancelled", new Object[0]);
                    }

                    @Override // com.prineside.tdi2.utils.FileChooser.Callback
                    public void onError(Exception exc) {
                        XmMusicTrackTile.c.e("error selecting a file", exc);
                    }
                });
            })).growX().height(48.0f).padBottom(15.0f).padTop(15.0f).row();
        }
    }

    private void a(Module module) {
        if (this.h || this.i != null) {
            return;
        }
        Module module2 = module;
        if (module == null) {
            try {
                if (this.f == null) {
                    this.h = true;
                    return;
                }
                module2 = Game.i.musicManager.getModule(this.f);
            } catch (Exception unused) {
                this.h = true;
                return;
            }
        }
        if (module2 != null) {
            this.i = module2.songName;
            this.j = module2.getInfoFromInstrumentNames();
            this.h = true;
        }
    }

    public final String getTitleCached() {
        a(null);
        return this.i;
    }

    public final Array<Module.TrackInfoEntry> getDescriptionCached() {
        a(null);
        return this.j;
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f) {
        Module module = getModule();
        if (module != null) {
            Label label = new Label(module.songName, Game.i.assetManager.getLabelStyle(24));
            label.setColor(MaterialColor.AMBER.P500);
            label.setAlignment(8);
            label.setWrap(true);
            table.add((Table) label).growX().row();
            Array.ArrayIterator<Module.TrackInfoEntry> it = module.getInfoFromInstrumentNames().iterator();
            while (it.hasNext()) {
                final Module.TrackInfoEntry next = it.next();
                final LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(next.value, 21, 18, f);
                switch (next.type) {
                    case AUTHOR:
                        Label label2 = new Label(Game.i.localeManager.i18n.get("music_track_author") + ":", Game.i.assetManager.getLabelStyle(18));
                        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        table.add((Table) label2).growX().padTop(8.0f).row();
                        break;
                    case GROUP:
                        Label label3 = new Label(Game.i.localeManager.i18n.get("music_track_group") + ":", Game.i.assetManager.getLabelStyle(18));
                        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        table.add((Table) label3).growX().padTop(8.0f).row();
                        break;
                    case LINK:
                        limitedWidthLabel.setTouchable(Touchable.enabled);
                        limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.8
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f2, float f3) {
                                Gdx.f881net.openURI(next.getCompleteLink());
                            }
                        });
                        limitedWidthLabel.addListener(new InputListener(this) { // from class: com.prineside.tdi2.tiles.XmMusicTrackTile.9
                            @Override // com.prineside.tdi2.scene2d.InputListener
                            public void enter(InputEvent inputEvent, float f2, float f3, int i, @Null Actor actor) {
                                limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P300);
                            }

                            @Override // com.prineside.tdi2.scene2d.InputListener
                            public void exit(InputEvent inputEvent, float f2, float f3, int i, @Null Actor actor) {
                                limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                            }
                        });
                        limitedWidthLabel.setText(((Object) limitedWidthLabel.getText()) + Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-link-out>").toString());
                        limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                        break;
                }
                limitedWidthLabel.setAlignment(8);
                table.add((Table) limitedWidthLabel).growX().row();
            }
        }
    }

    public static long generateNewId() {
        return (Game.getTimestampSeconds() << 32) + FastRandom.random.nextInt();
    }

    public final long getId() {
        return this.e;
    }

    public final void setId(long j) {
        this.e = j;
        this.g = null;
    }

    public final String getTrackBase64() {
        return this.f;
    }

    public final Module getModule() {
        if (this.f == null) {
            return null;
        }
        Module module = Game.i.musicManager.getModule(this.f);
        a(module);
        return module;
    }

    public final Color[] getIdColors() {
        if (this.g == null) {
            long state = FastRandom.random.getState(0);
            long state2 = FastRandom.random.getState(1);
            FastRandom.random.setSeed(this.e);
            this.g = new Color[5];
            for (int i = 0; i < 5; i++) {
                this.g[i] = d[FastRandom.random.nextInt(d.length)];
            }
            FastRandom.random.setState(state, state2);
        }
        return this.g;
    }

    @Override // com.prineside.tdi2.Tile
    public final int generateSeedSalt() {
        if (this.f == null) {
            return 0;
        }
        return this.f.length();
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        return super.sameAs(tile) && getId() == ((XmMusicTrackTile) tile).getId();
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

    public final void setTrack(String str) {
        this.f = str;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        return RarityType.RARE;
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_SOUNDS;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        XmMusicTrackTile xmMusicTrackTile = (XmMusicTrackTile) tile;
        setId(xmMusicTrackTile.getId());
        setTrack(xmMusicTrackTile.f);
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        batch.draw(Game.i.tileManager.F.XM_MUSIC_TRACK.f3187a, f, f2, f3, f4);
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawExtras(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        Color[] idColors = getIdColors();
        for (int i = 0; i < 4; i++) {
            batch.setColor(idColors[i]);
            batch.draw(Game.i.tileManager.F.XM_MUSIC_TRACK.d[i], f, f2, f3, f4);
        }
        batch.setColor(idColors[4]);
        if (getTitleCached() == null) {
            batch.draw(Game.i.tileManager.F.XM_MUSIC_TRACK.c, f + (f3 * 0.25f), f2 + (f4 * 0.25f), f3 * 0.5f, f4 * 0.5f);
            batch.setColor(Color.WHITE);
        } else {
            batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), f + (f3 * 0.32f), f2 + (f4 * 0.32f), f3 * 0.36f, f4 * 0.36f);
            batch.setColor(Color.WHITE);
            batch.draw(Game.i.tileManager.F.XM_MUSIC_TRACK.f3188b, f, f2, f3, f4);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(Game.i.tileManager.F.XM_MUSIC_TRACK.f3187a);
        image.setSize(f, f);
        group.addActor(image);
        Color[] idColors = getIdColors();
        for (int i = 0; i < 4; i++) {
            Image image2 = new Image(Game.i.tileManager.F.XM_MUSIC_TRACK.d[i]);
            image2.setColor(idColors[i]);
            image2.setSize(f, f);
            group.addActor(image2);
        }
        if (this.f == null) {
            Image image3 = new Image(Game.i.tileManager.F.XM_MUSIC_TRACK.c);
            image3.setPosition(f * 0.25f, f * 0.25f);
            image3.setSize(f * 0.5f, f * 0.5f);
            image3.setColor(idColors[4]);
            group.addActor(image3);
        } else {
            Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image4.setSize(f * 0.36f, f * 0.36f);
            image4.setPosition(f * 0.32f, f * 0.32f);
            image4.setColor(idColors[4]);
            group.addActor(image4);
            Image image5 = new Image(Game.i.tileManager.F.XM_MUSIC_TRACK.f3188b);
            image5.setSize(f, f);
            group.addActor(image5);
        }
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue(Attribute.ID_ATTR, Long.valueOf(getId()));
        if (this.f != null) {
            json.writeValue("track", this.f);
        }
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 1000));
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        return 0.1d;
    }

    @Override // com.prineside.tdi2.Tile
    public final String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (id: " + getId() + ", has track: " + (this.f == null ? "false" : "true") + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/XmMusicTrackTile$XmMusicTrackTileFactory.class */
    public static class XmMusicTrackTileFactory extends Tile.Factory.AbstractFactory<XmMusicTrackTile> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f3187a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f3188b;
        private TextureRegion c;
        private TextureRegion[] d;

        public XmMusicTrackTileFactory() {
            super(TileType.XM_MUSIC_TRACK);
            this.d = new TextureRegion[4];
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            if (f < ProgressManager.getMinQuality(RarityType.RARE) || f > ProgressManager.getMaxQuality(RarityType.RARE) || inventoryStatistics.byTileType[TileType.XM_MUSIC_TRACK.ordinal()] >= 50) {
                return 0;
            }
            return 20;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("icon-note");
            this.f3187a = Game.i.assetManager.getTextureRegion("tile-type-xm-sound-track-base");
            this.f3188b = Game.i.assetManager.getTextureRegion("tile-type-xm-sound-track-disc");
            for (int i = 0; i < 4; i++) {
                this.d[i] = Game.i.assetManager.getTextureRegion("tile-type-xm-sound-track-corner-" + i);
            }
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public XmMusicTrackTile create() {
            return new XmMusicTrackTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public XmMusicTrackTile fromJson(JsonValue jsonValue) {
            XmMusicTrackTile xmMusicTrackTile = (XmMusicTrackTile) super.fromJson(jsonValue);
            if (jsonValue.has("d")) {
                xmMusicTrackTile.setId(jsonValue.get("d").getLong(Attribute.ID_ATTR, -1L));
                xmMusicTrackTile.setTrack(jsonValue.get("d").getString("track", null));
            }
            return xmMusicTrackTile;
        }
    }
}
