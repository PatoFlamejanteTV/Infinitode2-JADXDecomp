package com.prineside.tdi2.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
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
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.Widget;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Iterator;
import java.util.Locale;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/EqualizerTile.class */
public class EqualizerTile extends Tile {
    public static final int CHANNEL_LEFT = 0;
    public static final int CHANNEL_RIGHT = 1;
    public static final int CHANNEL_BOTH = 2;
    public static final int DIRECTION_TOP = 0;
    public static final int DIRECTION_BOTTOM = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;
    public static final int INTERPOLATION_LINEAR = 0;
    public static final int INTERPOLATION_POW2 = 1;
    public static final int INTERPOLATION_POW3 = 2;
    public static final int INTERPOLATION_EXP5 = 3;
    public static final int INTERPOLATION_EXP10 = 4;

    @NAGS
    private final DelayedRemovalArray<Particle> d;

    @NAGS
    public boolean drawAlways;

    @NAGS
    public int barInterpolation;

    @NAGS
    public float spectrumDropoff;

    @NAGS
    public int channel;

    @NAGS
    public float cutout;

    @NAGS
    public int direction;

    @NAGS
    public Color colorLow;

    @NAGS
    public Color colorHigh;

    @NAGS
    public float barsWidth;

    @NAGS
    public float barsHeight;

    @NAGS
    public boolean revertBars;

    @NAGS
    public boolean particlesEnabled;

    @NAGS
    public Array<MusicManager.FrequencyRange> spectrumFrequencies;

    @NAGS
    public float maxValueEasing;

    @NAGS
    public float fixedMaxValue;

    @NAGS
    public float barSpacing;

    @NAGS
    public float shiftX;

    @NAGS
    public float shiftY;

    @NAGS
    private float[] e;

    @NAGS
    private float[] f;

    @NAGS
    private float[] g;

    @NAGS
    private float[] h;

    @NAGS
    private float[] i;

    @NAGS
    private Particle[] j;

    @NAGS
    public MusicManager.SpectrumConfig spectrumConfig;
    private static final TLog c = TLog.forClass(EqualizerTile.class);
    private static final Color k = new Color();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/EqualizerTile$Particle.class */
    public static final class Particle {
        public float x;
        public float y;
        public float vX;
        public float vY;
        public float t;
        public Color color = new Color();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.barInterpolation, true);
        output.writeFloat(this.spectrumDropoff);
        output.writeVarInt(this.channel, true);
        output.writeFloat(this.cutout);
        output.writeVarInt(this.direction, true);
        kryo.writeObject(output, this.colorLow);
        kryo.writeObject(output, this.colorHigh);
        output.writeFloat(this.barsWidth);
        output.writeFloat(this.barsHeight);
        output.writeBoolean(this.revertBars);
        output.writeBoolean(this.particlesEnabled);
        kryo.writeObject(output, this.spectrumFrequencies);
        output.writeFloat(this.maxValueEasing);
        output.writeFloat(this.fixedMaxValue);
        output.writeFloat(this.barSpacing);
        output.writeFloat(this.shiftX);
        output.writeFloat(this.shiftY);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.barInterpolation = input.readVarInt(true);
        this.spectrumDropoff = input.readFloat();
        this.channel = input.readVarInt(true);
        this.cutout = input.readFloat();
        this.direction = input.readVarInt(true);
        this.colorLow = (Color) kryo.readObject(input, Color.class);
        this.colorHigh = (Color) kryo.readObject(input, Color.class);
        this.barsWidth = input.readFloat();
        this.barsHeight = input.readFloat();
        this.revertBars = input.readBoolean();
        this.particlesEnabled = input.readBoolean();
        this.spectrumFrequencies = (Array) kryo.readObject(input, Array.class);
        this.maxValueEasing = input.readFloat();
        this.fixedMaxValue = input.readFloat();
        this.barSpacing = input.readFloat();
        this.shiftX = input.readFloat();
        this.shiftY = input.readFloat();
        handleConfigChanged();
    }

    public void handleConfigChanged() {
        if (Config.isHeadless()) {
            return;
        }
        int i = this.spectrumFrequencies.size;
        this.e = new float[i];
        this.f = new float[i];
        this.g = new float[i];
        this.h = new float[i];
        this.i = new float[i];
        this.j = new Particle[i];
        this.spectrumConfig = new MusicManager.SpectrumConfig(this.spectrumFrequencies);
        this.spectrumConfig.maxValueEasing = this.maxValueEasing;
        this.spectrumConfig.fixedMaxValue = this.fixedMaxValue;
        this.spectrumConfig = Game.i.musicManager.getSpectrumConfig(this.spectrumConfig);
    }

    public EqualizerTile() {
        super(TileType.EQUALIZER);
        this.d = new DelayedRemovalArray<>(true, 1, Particle.class);
        this.barInterpolation = 1;
        this.spectrumDropoff = 0.15f;
        this.channel = 2;
        this.cutout = 0.02f;
        this.direction = 0;
        this.colorLow = MaterialColor.ORANGE.P500.cpy();
        this.colorHigh = MaterialColor.LIGHT_GREEN.P500.cpy();
        this.barsWidth = 1.0f;
        this.barsHeight = 2.0f;
        this.particlesEnabled = true;
        this.spectrumFrequencies = new Array<>(true, 1, MusicManager.FrequencyRange.class);
        Array.ArrayIterator<MusicManager.FrequencyRange> it = MusicManager.SpectrumConfig.DEFAULT.frequencies.iterator();
        while (it.hasNext()) {
            MusicManager.FrequencyRange next = it.next();
            this.spectrumFrequencies.add(new MusicManager.FrequencyRange(next.min, next.max));
        }
        this.maxValueEasing = 0.998f;
        this.fixedMaxValue = 0.0f;
        this.barSpacing = 2.0f;
        handleConfigChanged();
    }

    @Override // com.prineside.tdi2.Tile
    public boolean canBeSelected() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
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
        EqualizerTile equalizerTile = (EqualizerTile) tile;
        if (this.barInterpolation != equalizerTile.barInterpolation || this.spectrumDropoff != equalizerTile.spectrumDropoff || this.channel != equalizerTile.channel || this.cutout != equalizerTile.cutout || this.direction != equalizerTile.direction || !this.colorLow.equals(equalizerTile.colorLow) || !this.colorHigh.equals(equalizerTile.colorHigh) || this.barsWidth != equalizerTile.barsWidth || this.barsHeight != equalizerTile.barsHeight || this.revertBars != equalizerTile.revertBars || this.particlesEnabled != equalizerTile.particlesEnabled || this.spectrumFrequencies.size != equalizerTile.spectrumFrequencies.size) {
            return false;
        }
        for (int i = 0; i < this.spectrumFrequencies.size; i++) {
            if (!this.spectrumFrequencies.items[i].sameAs(equalizerTile.spectrumFrequencies.items[i])) {
                return false;
            }
        }
        return this.maxValueEasing == equalizerTile.maxValueEasing && this.fixedMaxValue == equalizerTile.fixedMaxValue && this.barSpacing == equalizerTile.barSpacing && this.shiftX == equalizerTile.shiftX && this.shiftY == equalizerTile.shiftY;
    }

    @Override // com.prineside.tdi2.Tile
    public void from(Tile tile) {
        super.from(tile);
        EqualizerTile equalizerTile = (EqualizerTile) tile;
        this.barInterpolation = equalizerTile.barInterpolation;
        this.spectrumDropoff = equalizerTile.spectrumDropoff;
        this.channel = equalizerTile.channel;
        this.cutout = equalizerTile.cutout;
        this.direction = equalizerTile.direction;
        this.colorLow.set(equalizerTile.colorLow);
        this.colorHigh.set(equalizerTile.colorHigh);
        this.barsWidth = equalizerTile.barsWidth;
        this.barsHeight = equalizerTile.barsHeight;
        this.revertBars = equalizerTile.revertBars;
        this.particlesEnabled = equalizerTile.particlesEnabled;
        this.spectrumFrequencies.clear();
        this.spectrumFrequencies.addAll(equalizerTile.spectrumFrequencies);
        this.maxValueEasing = equalizerTile.maxValueEasing;
        this.fixedMaxValue = equalizerTile.fixedMaxValue;
        this.barSpacing = equalizerTile.barSpacing;
        this.shiftX = equalizerTile.shiftX;
        this.shiftY = equalizerTile.shiftY;
        handleConfigChanged();
    }

    @Override // com.prineside.tdi2.Tile
    public Group generateUiIcon(final float f) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        final EqualizerTile equalizerTile = new EqualizerTile();
        equalizerTile.from(this);
        equalizerTile.barsWidth = 1.0f;
        equalizerTile.barsHeight = 1.0f;
        equalizerTile.shiftX = 0.0f;
        equalizerTile.shiftY = 0.0f;
        equalizerTile.drawAlways = true;
        Image image = new Image(this) { // from class: com.prineside.tdi2.tiles.EqualizerTile.1
            @Override // com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
            public void draw(Batch batch, float f2) {
                validate();
                equalizerTile.drawFancy(batch, Gdx.graphics.getDeltaTime(), getX(), getY(), f);
            }
        };
        image.setSize(f, f);
        group.addActor(image);
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("bi", Integer.valueOf(this.barInterpolation));
        json.writeValue("sd", Float.valueOf(this.spectrumDropoff));
        json.writeValue("c", Integer.valueOf(this.channel));
        json.writeValue("co", Float.valueOf(this.cutout));
        json.writeValue("d", Integer.valueOf(this.direction));
        json.writeValue("cl", this.colorLow.toString());
        json.writeValue("ch", this.colorHigh.toString());
        json.writeValue("bw", Float.valueOf(this.barsWidth));
        json.writeValue("bh", Float.valueOf(this.barsHeight));
        json.writeValue("rb", Boolean.valueOf(this.revertBars));
        json.writeValue("pe", Boolean.valueOf(this.particlesEnabled));
        json.writeArrayStart("f");
        Array.ArrayIterator<MusicManager.FrequencyRange> it = this.spectrumFrequencies.iterator();
        while (it.hasNext()) {
            MusicManager.FrequencyRange next = it.next();
            json.writeArrayStart();
            json.writeValue(Float.valueOf(next.min));
            json.writeValue(Float.valueOf(next.max));
            json.writeArrayEnd();
        }
        json.writeArrayEnd();
        json.writeValue("mve", Float.valueOf(this.maxValueEasing));
        json.writeValue("fmv", Float.valueOf(this.fixedMaxValue));
        json.writeValue("bs", Float.valueOf(this.barSpacing));
        json.writeValue("sx", Float.valueOf(this.shiftX));
        json.writeValue("sy", Float.valueOf(this.shiftY));
        json.writeObjectEnd();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x022b, code lost:            r0.vX *= 0.9f + (com.prineside.tdi2.utils.FastRandom.getFloat() * 0.1f);        r0.vY *= 0.9f + (com.prineside.tdi2.utils.FastRandom.getFloat() * 0.1f);        r8.j[r15] = r0;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:28:0x0101. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drawFancy(com.badlogic.gdx.graphics.g2d.Batch r9, float r10, float r11, float r12, float r13) {
        /*
            Method dump skipped, instructions count: 1588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.tiles.EqualizerTile.drawFancy(com.badlogic.gdx.graphics.g2d.Batch, float, float, float, float):void");
    }

    @Override // com.prineside.tdi2.Tile
    public void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        if (drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            batch.setColor(1.0f, 1.0f, 1.0f, 0.14f);
            batch.draw(AssetManager.TextureRegions.i().iconOrgan, f + (f3 * 0.5f * 0.5f), f2 + (f4 * 0.5f * 0.5f), f3 * 0.5f, f4 * 0.5f);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_SPECTRUM_ENABLED) != 0.0d) {
            drawFancy(batch, Gdx.graphics.getDeltaTime(), f2, f3, f4);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public void fillItemCreationForm(final ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Bar interpolation");
        itemCreationOverlay.hintLabel("Bar height function, use linear for 1:1 representation of the spectrum", true);
        final SelectBox selectBox = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox.setItems("linear", "pow2", "pow3", "exp5", "exp10");
        switch (this.barInterpolation) {
            case 0:
                selectBox.setSelected("linear");
                break;
            case 1:
                selectBox.setSelected("pow2");
                break;
            case 2:
                selectBox.setSelected("pow3");
                break;
            case 3:
                selectBox.setSelected("exp5");
                break;
            case 4:
                selectBox.setSelected("exp10");
                break;
        }
        selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.EqualizerTile.2
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                String str = (String) selectBox.getSelected();
                boolean z = -1;
                switch (str.hashCode()) {
                    case -1102672091:
                        if (str.equals("linear")) {
                            z = false;
                            break;
                        }
                        break;
                    case 3127736:
                        if (str.equals("exp5")) {
                            z = 3;
                            break;
                        }
                        break;
                    case 3447002:
                        if (str.equals("pow2")) {
                            z = true;
                            break;
                        }
                        break;
                    case 3447003:
                        if (str.equals("pow3")) {
                            z = 2;
                            break;
                        }
                        break;
                    case 96959740:
                        if (str.equals("exp10")) {
                            z = 4;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        EqualizerTile.this.barInterpolation = 0;
                        break;
                    case true:
                        EqualizerTile.this.barInterpolation = 1;
                        break;
                    case true:
                        EqualizerTile.this.barInterpolation = 2;
                        break;
                    case true:
                        EqualizerTile.this.barInterpolation = 3;
                        break;
                    case true:
                        EqualizerTile.this.barInterpolation = 4;
                        break;
                }
                itemCreationOverlay.updateItemIcon();
            }
        });
        itemCreationOverlay.selectBox(selectBox);
        itemCreationOverlay.label("Drop-off");
        itemCreationOverlay.hintLabel("How fast will bars return to zero. Per-frame bar height reduction multiplier", true);
        itemCreationOverlay.textField(String.valueOf(this.spectrumDropoff), str -> {
            try {
                this.spectrumDropoff = Float.parseFloat(str);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str, new Object[0]);
            }
        });
        itemCreationOverlay.label("Cutout");
        itemCreationOverlay.hintLabel("Minimum value of the spectrum [0..1] to render", true);
        itemCreationOverlay.textField(String.valueOf(this.cutout), str2 -> {
            try {
                this.cutout = Float.parseFloat(str2);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str2, new Object[0]);
            }
        });
        itemCreationOverlay.label("Channel");
        final SelectBox selectBox2 = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox2.setItems("left", "right", "both");
        switch (this.channel) {
            case 0:
                selectBox2.setSelected("left");
                break;
            case 1:
                selectBox2.setSelected("right");
                break;
            case 2:
                selectBox2.setSelected("both");
                break;
        }
        selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.EqualizerTile.3
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                String str3 = (String) selectBox2.getSelected();
                boolean z = -1;
                switch (str3.hashCode()) {
                    case 3029889:
                        if (str3.equals("both")) {
                            z = 2;
                            break;
                        }
                        break;
                    case 3317767:
                        if (str3.equals("left")) {
                            z = false;
                            break;
                        }
                        break;
                    case 108511772:
                        if (str3.equals("right")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        EqualizerTile.this.channel = 0;
                        break;
                    case true:
                        EqualizerTile.this.channel = 1;
                        break;
                    case true:
                        EqualizerTile.this.channel = 2;
                        break;
                }
                itemCreationOverlay.updateItemIcon();
            }
        });
        itemCreationOverlay.selectBox(selectBox2);
        itemCreationOverlay.label("Direction");
        final SelectBox selectBox3 = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox3.setItems("left", "right", "top", "bottom");
        switch (this.direction) {
            case 0:
                selectBox3.setSelected("top");
                break;
            case 1:
                selectBox3.setSelected("bottom");
                break;
            case 2:
                selectBox3.setSelected("left");
                break;
            case 3:
                selectBox3.setSelected("right");
                break;
        }
        selectBox3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.EqualizerTile.4
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                String str3 = (String) selectBox3.getSelected();
                boolean z = -1;
                switch (str3.hashCode()) {
                    case -1383228885:
                        if (str3.equals("bottom")) {
                            z = false;
                            break;
                        }
                        break;
                    case 115029:
                        if (str3.equals("top")) {
                            z = true;
                            break;
                        }
                        break;
                    case 3317767:
                        if (str3.equals("left")) {
                            z = 2;
                            break;
                        }
                        break;
                    case 108511772:
                        if (str3.equals("right")) {
                            z = 3;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        EqualizerTile.this.direction = 1;
                        break;
                    case true:
                        EqualizerTile.this.direction = 0;
                        break;
                    case true:
                        EqualizerTile.this.direction = 2;
                        break;
                    case true:
                        EqualizerTile.this.direction = 3;
                        break;
                }
                itemCreationOverlay.updateItemIcon();
            }
        });
        itemCreationOverlay.selectBox(selectBox3);
        itemCreationOverlay.label("Bar thickness");
        itemCreationOverlay.hintLabel("How many tiles will this Equalizer take along the frequency axis", true);
        itemCreationOverlay.textField(String.valueOf(this.barsWidth), str3 -> {
            try {
                this.barsWidth = Float.parseFloat(str3);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str3, new Object[0]);
            }
        });
        itemCreationOverlay.label("Bar length");
        itemCreationOverlay.hintLabel("How many tiles will this Equalizer take along the spectrum value axis", true);
        itemCreationOverlay.textField(String.valueOf(this.barsHeight), str4 -> {
            try {
                this.barsHeight = Float.parseFloat(str4);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str4, new Object[0]);
            }
        });
        itemCreationOverlay.label("Low color");
        itemCreationOverlay.textField(this.colorLow.toString().toUpperCase(Locale.US), str5 -> {
            try {
                this.colorLow.set(Color.valueOf(str5));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
            }
        });
        itemCreationOverlay.label("High color");
        itemCreationOverlay.textField(this.colorHigh.toString().toUpperCase(Locale.US), str6 -> {
            try {
                this.colorHigh.set(Color.valueOf(str6));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
            }
        });
        itemCreationOverlay.toggle("Revert bars", this.revertBars, bool -> {
            this.revertBars = bool.booleanValue();
            itemCreationOverlay.updateItemIcon();
        });
        itemCreationOverlay.hintLabel("Swap low and high frequency sides of the spectrum", true);
        itemCreationOverlay.toggle("Particles", this.particlesEnabled, bool2 -> {
            this.particlesEnabled = bool2.booleanValue();
            itemCreationOverlay.updateItemIcon();
        });
        itemCreationOverlay.hintLabel("Show particles if available for this visualization type", true);
        itemCreationOverlay.label("Bar spacing");
        itemCreationOverlay.textField(String.valueOf(this.barSpacing), str7 -> {
            try {
                this.barSpacing = Float.parseFloat(str7);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str7, new Object[0]);
            }
        });
        itemCreationOverlay.label("Shift X");
        itemCreationOverlay.textField(String.valueOf(this.shiftX), str8 -> {
            try {
                this.shiftX = Float.parseFloat(str8);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str8, new Object[0]);
            }
        });
        itemCreationOverlay.label("Shift Y");
        itemCreationOverlay.textField(String.valueOf(this.shiftY), str9 -> {
            try {
                this.shiftY = Float.parseFloat(str9);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str9, new Object[0]);
            }
        });
        itemCreationOverlay.label("Frequencies");
        Table table = new Table();
        itemCreationOverlay.form.add(table).width(800.0f).top().left().row();
        TextureRegionDrawable drawable = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME);
        int i = 0;
        Array.ArrayIterator<MusicManager.FrequencyRange> it = this.spectrumFrequencies.iterator();
        while (it.hasNext()) {
            final MusicManager.FrequencyRange next = it.next();
            Table table2 = new Table();
            table.add(table2).expandX().fillX().row();
            Table table3 = new Table();
            table2.add(table3).top().left().padBottom(10.0f).padRight(10.0f).minHeight(32.0f);
            final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(String.valueOf(next.min), itemCreationOverlay.textFieldStyle);
            textFieldXPlatform.setSize(120.0f, 32.0f);
            textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.EqualizerTile.5
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        next.min = (float) Double.parseDouble(textFieldXPlatform.getText());
                        EqualizerTile.this.handleConfigChanged();
                        itemCreationOverlay.updateItemIcon();
                    } catch (Exception e) {
                        EqualizerTile.c.e("invalid value", e);
                    }
                }
            });
            table3.add((Table) textFieldXPlatform).size(120.0f, 32.0f).top().left();
            final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(String.valueOf(next.max), itemCreationOverlay.textFieldStyle);
            textFieldXPlatform2.setSize(120.0f, 32.0f);
            textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.EqualizerTile.6
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        next.max = (float) Double.parseDouble(textFieldXPlatform2.getText());
                        EqualizerTile.this.handleConfigChanged();
                        itemCreationOverlay.updateItemIcon();
                    } catch (Exception e) {
                        EqualizerTile.c.e("invalid value", e);
                    }
                }
            });
            table3.add((Table) textFieldXPlatform2).size(120.0f, 32.0f).padLeft(15.0f).top().left();
            final TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform(String.valueOf(next.multiplier), itemCreationOverlay.textFieldStyle);
            textFieldXPlatform3.setSize(120.0f, 32.0f);
            textFieldXPlatform3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.EqualizerTile.7
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        next.multiplier = (float) Double.parseDouble(textFieldXPlatform3.getText());
                        EqualizerTile.this.handleConfigChanged();
                        itemCreationOverlay.updateItemIcon();
                    } catch (Exception e) {
                        EqualizerTile.c.e("invalid value", e);
                    }
                }
            });
            table3.add((Table) textFieldXPlatform3).size(120.0f, 32.0f).padLeft(15.0f).top().left();
            table3.add().expandX().fillX();
            if (i != 0) {
                int i2 = i;
                RectButton rectButton = new RectButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    this.spectrumFrequencies.swap(i2, i2 - 1);
                    handleConfigChanged();
                    itemCreationOverlay.updateItemIcon();
                    itemCreationOverlay.updateForm();
                });
                rectButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-top"), 4.0f, 4.0f, 24.0f, 24.0f);
                rectButton.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 32.0f, 32.0f);
                rectButton.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.RED.P900);
                table2.add((Table) rectButton).top().left().size(32.0f);
            }
            if (i != this.spectrumFrequencies.size - 1) {
                int i3 = i;
                RectButton rectButton2 = new RectButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    this.spectrumFrequencies.swap(i3, i3 + 1);
                    handleConfigChanged();
                    itemCreationOverlay.updateItemIcon();
                    itemCreationOverlay.updateForm();
                });
                rectButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-bottom"), 4.0f, 4.0f, 24.0f, 24.0f);
                rectButton2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 32.0f, 32.0f);
                rectButton2.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.RED.P900);
                table2.add((Table) rectButton2).top().left().size(32.0f);
            }
            RectButton rectButton3 = new RectButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                this.spectrumFrequencies.removeValue(next, true);
                handleConfigChanged();
                itemCreationOverlay.updateItemIcon();
                itemCreationOverlay.updateForm();
            });
            rectButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 4.0f, 4.0f, 24.0f, 24.0f);
            rectButton3.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 32.0f, 32.0f);
            rectButton3.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, MaterialColor.RED.P900);
            table2.add((Table) rectButton3).top().left().size(32.0f);
            table2.add().height(1.0f).growX();
            final int i4 = i;
            table2.add((Table) new Widget(150.0f, i4, drawable, 32.0f) { // from class: com.prineside.tdi2.tiles.EqualizerTile.8
                private /* synthetic */ int k;
                private /* synthetic */ Drawable l;
                private /* synthetic */ float j = 150.0f;
                private /* synthetic */ float m = 32.0f;

                {
                    this.k = i4;
                    this.l = drawable;
                }

                @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
                public void draw(Batch batch, float f) {
                    validate();
                    if (EqualizerTile.this.spectrumConfig != null) {
                        MusicManager.SpectrumConfig spectrumConfig = Game.i.musicManager.getSpectrumConfig(EqualizerTile.this.spectrumConfig);
                        batch.setColor(MaterialColor.LIGHT_GREEN.P500.r, MaterialColor.LIGHT_GREEN.P500.g, MaterialColor.LIGHT_GREEN.P500.f888b, f);
                        float x = getX();
                        float y = getY();
                        float f2 = this.j * spectrumConfig.spectrumLeft[this.k];
                        this.l.draw(batch, x + (this.j - f2), y, f2, this.m);
                    }
                }
            }).top().left().size(150.0f, 32.0f).padLeft(10.0f);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME)) { // from class: com.prineside.tdi2.tiles.EqualizerTile.9
                @Override // com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
                public void draw(Batch batch, float f) {
                    if (EqualizerTile.this.spectrumConfig != null) {
                        setScaleX(Game.i.musicManager.getSpectrumConfig(EqualizerTile.this.spectrumConfig).spectrumRight[i4]);
                    }
                    super.draw(batch, f);
                }
            };
            image.setColor(MaterialColor.LIGHT_BLUE.P500);
            table2.add((Table) image).top().left().size(150.0f, 32.0f).padLeft(1.0f);
            table2.row();
            i++;
        }
        Table table4 = new Table();
        table.add(table4).expandX().fillX().row();
        TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform("", itemCreationOverlay.textFieldStyle);
        textFieldXPlatform4.setMessageText("Min");
        table4.add((Table) textFieldXPlatform4).size(200.0f, 32.0f).padRight(15.0f);
        TextFieldXPlatform textFieldXPlatform5 = new TextFieldXPlatform("", itemCreationOverlay.textFieldStyle);
        textFieldXPlatform5.setMessageText("Max");
        table4.add((Table) textFieldXPlatform5).size(200.0f, 32.0f).padRight(10.0f);
        RectButton rectButton4 = new RectButton("", Game.i.assetManager.getLabelStyle(21), () -> {
            if (textFieldXPlatform4.getText().length() > 0) {
                try {
                    this.spectrumFrequencies.add(new MusicManager.FrequencyRange(Float.parseFloat(textFieldXPlatform4.getText()), Float.parseFloat(textFieldXPlatform5.getText())));
                    handleConfigChanged();
                    itemCreationOverlay.updateForm();
                } catch (Exception e) {
                    c.e("invalid value", e);
                }
            }
        });
        rectButton4.setIconPositioned(Game.i.assetManager.getDrawable("icon-plus"), 36.0f, 4.0f, 24.0f, 24.0f);
        rectButton4.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 32.0f, 32.0f);
        rectButton4.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P900);
        table4.add((Table) rectButton4).top().left().size(96.0f, 32.0f);
        table4.add().expandX().fillX();
        itemCreationOverlay.label("Max value easing");
        itemCreationOverlay.hintLabel("By default, max value of the spectrum is determined automatically and it can become very high during some loud periods. Bar values are calculated according to the max observed value of the spectrum, and to smooth it out for a more quiet periods, max value drops over time. Set to 1 to disable (bars may become too small in this case). Too low values may render bars too high for a quiet periods.", true);
        itemCreationOverlay.textField(String.valueOf(this.maxValueEasing), str10 -> {
            try {
                this.maxValueEasing = Float.parseFloat(str10);
                handleConfigChanged();
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str10, new Object[0]);
            }
        });
        itemCreationOverlay.label("Fixed max value");
        itemCreationOverlay.hintLabel("If set to >0, spectrum analyzer won't determine max value automatically and 'Max value easing' will be ignored", true);
        itemCreationOverlay.textField(String.valueOf(this.fixedMaxValue), str11 -> {
            try {
                this.fixedMaxValue = Float.parseFloat(str11);
                handleConfigChanged();
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str11, new Object[0]);
            }
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

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/EqualizerTile$EqualizerTileFactory.class */
    public static class EqualizerTileFactory extends Tile.Factory.AbstractFactory<EqualizerTile> {
        public EqualizerTileFactory() {
            super(TileType.EQUALIZER);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            return 0;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public EqualizerTile create() {
            return new EqualizerTile();
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public EqualizerTile fromJson(JsonValue jsonValue) {
            EqualizerTile equalizerTile = (EqualizerTile) super.fromJson(jsonValue);
            JsonValue jsonValue2 = jsonValue.get("d");
            if (jsonValue2 != null) {
                try {
                    equalizerTile.barInterpolation = jsonValue2.getInt("bi", equalizerTile.barInterpolation);
                    equalizerTile.spectrumDropoff = jsonValue2.getFloat("sd", equalizerTile.spectrumDropoff);
                    equalizerTile.channel = jsonValue2.getInt("c", equalizerTile.channel);
                    equalizerTile.cutout = jsonValue2.getFloat("co", equalizerTile.cutout);
                    equalizerTile.direction = jsonValue2.getInt("d", equalizerTile.direction);
                    try {
                        equalizerTile.colorLow = Color.valueOf(jsonValue2.getString("cl"));
                    } catch (Exception unused) {
                    }
                    try {
                        equalizerTile.colorHigh = Color.valueOf(jsonValue2.getString("ch"));
                    } catch (Exception unused2) {
                    }
                    equalizerTile.barsWidth = jsonValue2.getFloat("bw", equalizerTile.barsWidth);
                    equalizerTile.barsHeight = jsonValue2.getFloat("bh", equalizerTile.barsHeight);
                    equalizerTile.revertBars = jsonValue2.getBoolean("rb", equalizerTile.revertBars);
                    equalizerTile.particlesEnabled = jsonValue2.getBoolean("pe", equalizerTile.particlesEnabled);
                    JsonValue jsonValue3 = jsonValue2.get("f");
                    if (jsonValue3 != null) {
                        equalizerTile.spectrumFrequencies.clear();
                        Iterator<JsonValue> iterator2 = jsonValue3.iterator2();
                        while (iterator2.hasNext()) {
                            JsonValue next = iterator2.next();
                            equalizerTile.spectrumFrequencies.add(new MusicManager.FrequencyRange(next.getFloat(0), next.getFloat(1)));
                        }
                    }
                    equalizerTile.maxValueEasing = jsonValue2.getFloat("mve", equalizerTile.maxValueEasing);
                    equalizerTile.fixedMaxValue = jsonValue2.getFloat("fmv", equalizerTile.fixedMaxValue);
                    equalizerTile.barSpacing = jsonValue2.getFloat("bs", equalizerTile.barSpacing);
                    equalizerTile.shiftX = jsonValue2.getFloat("sx", equalizerTile.shiftX);
                    equalizerTile.shiftY = jsonValue2.getFloat("sy", equalizerTile.shiftY);
                } catch (Exception e) {
                    EqualizerTile.c.e("failed to load from json", e);
                }
            }
            equalizerTile.handleConfigChanged();
            return equalizerTile;
        }
    }
}
