package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.components.TileResources;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.Arrays;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/SourceTile.class */
public final class SourceTile extends Tile {
    private static final TLog c = TLog.forClass(SourceTile.class);
    private static final Color d;
    private static final Color e;
    private float f;
    private int[] g;
    public Miner miner;

    @NAGS
    private boolean h;

    @NAGS
    private final Color[] i;

    @NAGS
    private final int[] j;
    private static final float[] k;
    private static final ResourceAmount[] l;
    public int[] minedResources;
    private static final StringBuilder m;

    /* synthetic */ SourceTile(byte b2) {
        this();
    }

    static /* synthetic */ boolean a(SourceTile sourceTile, boolean z) {
        sourceTile.h = true;
        return true;
    }

    static {
        Color color = new Color(858993663);
        d = color;
        e = color;
        k = new float[ResourceType.values.length];
        l = new ResourceAmount[ResourceType.values.length];
        for (int i = 0; i < l.length; i++) {
            l[i] = new ResourceAmount((byte) 0);
        }
        m = new StringBuilder();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f);
        kryo.writeObject(output, this.g);
        kryo.writeObject(output, this.minedResources);
        kryo.writeClassAndObject(output, this.miner);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = input.readFloat();
        this.g = (int[]) kryo.readObject(input, int[].class);
        this.minedResources = (int[]) kryo.readObject(input, int[].class);
        this.miner = (Miner) kryo.readClassAndObject(input);
    }

    private SourceTile() {
        super(TileType.SOURCE);
        this.f = 1.0f;
        this.g = new int[ResourceType.values.length];
        this.h = true;
        this.i = new Color[8];
        this.j = new int[8];
        this.minedResources = new int[ResourceType.values.length];
        Arrays.fill(this.i, Color.WHITE);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        this.miner = null;
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, final MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        TileResources tileResources = new TileResources(352.0f);
        tileResources.setTile(this);
        table.add((Table) tileResources).row();
        if (!mapEditorItemInfoMenu.isSelectionFromInventory() && Game.i.progressManager.isDeveloperModeEnabled()) {
            Label label = new Label("Miner type", Game.i.assetManager.getLabelStyle(18));
            label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) label).padBottom(4.0f).padTop(4.0f).growX().row();
            final SelectBox selectBox = new SelectBox(mapEditorItemInfoMenu.selectBoxStyle);
            selectBox.setName("map_editor_menu_miner_type_select");
            Array array = new Array();
            array.add("No miner");
            for (int i = 0; i < MinerType.values.length; i++) {
                array.add(MinerType.values[i].name());
            }
            selectBox.setItems(array);
            table.add((Table) selectBox).height(48.0f).padBottom(8.0f).growX().row();
            if (this.miner != null) {
                selectBox.setSelected(this.miner.type.name());
            } else {
                selectBox.setSelected("No miner");
            }
            selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.SourceTile.1
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    String str = (String) selectBox.getSelected();
                    if (str.equals("No miner")) {
                        if (SourceTile.this.miner != null) {
                            SourceTile.this.miner.setTile(null);
                            SourceTile.this.miner = null;
                        }
                    } else {
                        if (SourceTile.this.miner != null) {
                            SourceTile.this.miner.setTile(null);
                        }
                        SourceTile.this.miner = Game.i.minerManager.getFactory(MinerType.valueOf(str)).create();
                        SourceTile.this.miner.setTile(SourceTile.this);
                    }
                    mapEditorItemInfoMenu.notifySelectedElementChanged();
                    mapEditorItemInfoMenu.update();
                }
            });
            if (this.miner != null) {
                Label label2 = new Label("Upgrade level", Game.i.assetManager.getLabelStyle(18));
                label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table.add((Table) label2).padBottom(4.0f).padTop(4.0f).growX().row();
                final SelectBox selectBox2 = new SelectBox(mapEditorItemInfoMenu.selectBoxStyle);
                selectBox2.setName("map_editor_menu_source_upgrade_level_select");
                Array array2 = new Array();
                for (int i2 = 0; i2 <= 10; i2++) {
                    array2.add(String.valueOf(i2));
                }
                selectBox2.setItems(array2);
                table.add((Table) selectBox2).height(48.0f).padBottom(8.0f).growX().row();
                selectBox2.setSelected(String.valueOf(this.miner.getUpgradeLevel()));
                selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.SourceTile.2
                    @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                    public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                        SourceTile.this.miner.setUpgradeLevel(Integer.parseInt((String) selectBox2.getSelected()));
                        mapEditorItemInfoMenu.notifySelectedElementChanged();
                        mapEditorItemInfoMenu.update();
                    }
                });
            }
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f) {
        TileResources tileResources = new TileResources(f);
        tileResources.setTile(this);
        table.add((Table) tileResources).width(f);
    }

    @Override // com.prineside.tdi2.Tile
    public final int generateSeedSalt() {
        int floor = MathUtils.floor(this.f * 100.0f);
        for (ResourceType resourceType : ResourceType.values) {
            floor = (int) (floor + (StrictMath.pow(10.0d, resourceType.ordinal()) * this.g[resourceType.ordinal()]));
        }
        return floor;
    }

    public final boolean isDepleted() {
        if (this.S != null && this.S.gameState != null && this.S.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS) {
            for (int i = 0; i < ResourceType.values.length; i++) {
                int resourcesCount = getResourcesCount(ResourceType.values[i]);
                if (resourcesCount > 0 && this.minedResources[i] < resourcesCount) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.RARITY) {
            int i = 0;
            for (ResourceType resourceType : ResourceType.values) {
                i += (resourceType.ordinal() + 1) * 5 * this.g[resourceType.ordinal()];
            }
            return (getRarity().ordinal() * 1000) + ((int) (i * this.f));
        }
        for (int length = ResourceType.values.length - 1; length >= 0; length--) {
            if (this.g[length] != 0) {
                return length;
            }
        }
        return 0;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        if (this.g[ResourceType.INFIAR.ordinal()] != 0) {
            return RarityType.LEGENDARY;
        }
        if (this.g[ResourceType.TENSOR.ordinal()] != 0) {
            return RarityType.EPIC;
        }
        if (this.g[ResourceType.MATRIX.ordinal()] != 0) {
            return RarityType.VERY_RARE;
        }
        if (this.g[ResourceType.VECTOR.ordinal()] != 0) {
            return RarityType.RARE;
        }
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_SOURCES;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        SourceTile sourceTile = (SourceTile) tile;
        System.arraycopy(sourceTile.g, 0, this.g, 0, this.g.length);
        this.f = sourceTile.f;
        this.miner = sourceTile.miner == null ? null : sourceTile.miner.cloneMiner();
    }

    @Override // com.prineside.tdi2.Tile
    public final void updateCache() {
        if (this.miner != null && this.miner.isRegistered()) {
            this.miner.updateCache();
        }
    }

    public final float getResourceDensity() {
        return this.f;
    }

    public final void setResourceDensity(float f) {
        this.f = f;
        this.h = true;
    }

    public final int getResourcesCount(ResourceType resourceType) {
        return this.g[resourceType.ordinal()];
    }

    public final void setResourcesCount(ResourceType resourceType, int i) {
        this.g[resourceType.ordinal()] = i;
        this.h = true;
    }

    private void a() {
        this.h = false;
        float f = 1.0f - this.f;
        float f2 = f;
        if (f < 0.0f) {
            f2 = 0.0f;
        }
        int i = 0;
        for (ResourceType resourceType : ResourceType.values) {
            i += this.g[resourceType.ordinal()];
        }
        if (i == 0) {
            for (int i2 = 0; i2 < 8; i2++) {
                this.i[i2] = d;
            }
            return;
        }
        for (int i3 = 0; i3 < ResourceType.values.length; i3++) {
            l[i3].f3156a = ResourceType.values[i3];
            l[i3].f3157b = this.g[i3] / i;
        }
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = i4;
            this.j[i5] = i5;
        }
        for (int i6 = 0; i6 < ResourceType.values.length; i6++) {
            if (l[i6].f3157b != 0.0f && l[i6].f3157b < 0.125f) {
                float f3 = 0.125f - l[i6].f3157b;
                float f4 = 0.0f;
                for (int i7 = 0; i7 < ResourceType.values.length; i7++) {
                    if (l[i7].f3157b > 0.125f) {
                        k[i7] = l[i7].f3157b - 0.125f;
                        f4 += k[i7];
                    } else {
                        k[i7] = 0.0f;
                    }
                }
                float f5 = 1.0f - ((f4 - f3) / f4);
                for (int i8 = 0; i8 < ResourceType.values.length; i8++) {
                    if (k[i8] != 0.0f) {
                        l[i8].f3157b -= k[i8] * f5;
                    }
                }
                l[i6].f3157b = 0.125f;
            }
        }
        for (int i9 = 0; i9 < ResourceType.values.length; i9++) {
            for (int i10 = i9 + 1; i10 < ResourceType.values.length; i10++) {
                if (l[i9].f3157b < l[i10].f3157b) {
                    ResourceAmount resourceAmount = l[i9];
                    ResourceAmount[] resourceAmountArr = l;
                    resourceAmountArr[i9] = resourceAmountArr[i10];
                    l[i10] = resourceAmount;
                }
            }
        }
        float f6 = f2;
        for (int i11 = 0; i11 < ResourceType.values.length; i11++) {
            l[i11].c = f6;
            f6 += l[i11].f3157b * this.f;
        }
        for (int i12 = 0; i12 < 8; i12++) {
            int i13 = FastRandom.getInt(8);
            int i14 = this.j[i12];
            int[] iArr = this.j;
            iArr[i12] = iArr[i13];
            this.j[i13] = i14;
        }
        float f7 = 0.0f;
        for (int i15 = 0; i15 < 8; i15++) {
            int i16 = -1;
            for (int i17 = 0; i17 < ResourceType.values.length; i17++) {
                if (f7 >= l[i17].c) {
                    i16 = i17;
                }
            }
            if (i16 == -1) {
                this.i[this.j[i15]] = d;
            } else {
                this.i[this.j[i15]] = Game.i.resourceManager.getColor(l[i16].f3156a);
            }
            f7 += 0.125f;
        }
        if (this.i[0] == d) {
            ResourceType resourceType2 = null;
            int i18 = 0;
            while (true) {
                if (i18 >= ResourceType.values.length) {
                    break;
                }
                if (this.g[i18] == 0) {
                    i18++;
                } else {
                    resourceType2 = ResourceType.values[i18];
                    break;
                }
            }
            if (resourceType2 != null) {
                this.i[0] = Game.i.resourceManager.getColor(resourceType2);
            }
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        float f = 0.0f;
        for (int i = 0; i < this.g.length; i++) {
            f += this.g[i] * (0.1f + (i * 0.02f));
        }
        int ceil = MathUtils.ceil((0.25f + (this.f * 0.75f)) * f);
        int i2 = ceil;
        if (ceil <= 0) {
            i2 = 1;
        }
        array.add(new ItemStack(Item.D.GREEN_PAPER, i2));
        for (ResourceType resourceType : ResourceType.values) {
            int i3 = this.g[resourceType.ordinal()] / 5;
            if (i3 > 0) {
                array.add(new ItemStack(Item.D.F_RESOURCE.create(resourceType), i3));
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00a0, code lost:            r6 = r6 + r13;     */
    @Override // com.prineside.tdi2.Tile
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double getPrestigeScore() {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.tiles.SourceTile.getPrestigeScore():double");
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("rd", Float.valueOf(this.f));
        json.writeArrayStart("r");
        for (ResourceType resourceType : ResourceType.values) {
            if (this.g[resourceType.ordinal()] > 0) {
                json.writeObjectStart();
                json.writeValue("t", resourceType.name());
                json.writeValue(FlexmarkHtmlConverter.A_NODE, Integer.valueOf(this.g[resourceType.ordinal()]));
                json.writeObjectEnd();
            }
        }
        json.writeArrayEnd();
        json.writeObjectEnd();
        if (this.miner != null) {
            json.writeObjectStart("miner");
            this.miner.toJson(json);
            json.writeObjectEnd();
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAsWithExtras(Tile tile) {
        if (!sameAs(tile)) {
            return false;
        }
        SourceTile sourceTile = (SourceTile) tile;
        if (this.miner == null) {
            return sourceTile.miner == null;
        }
        return this.miner.sameAs(sourceTile.miner);
    }

    @Override // com.prineside.tdi2.Tile
    public final SourceTile removeExtrasForInventory() {
        this.miner = null;
        return this;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        if (!super.sameAs(tile)) {
            return false;
        }
        SourceTile sourceTile = (SourceTile) tile;
        if (getResourceDensity() != sourceTile.getResourceDensity()) {
            return false;
        }
        for (ResourceType resourceType : ResourceType.values) {
            if (getResourcesCount(resourceType) != sourceTile.getResourcesCount(resourceType)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        batch.draw(Game.i.tileManager.F.SOURCE.f3158a, f, f2, f3, f4);
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawExtras(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        if (this.h) {
            a();
        }
        for (int i = 0; i < 8; i++) {
            if (drawMode == MapRenderingSystem.DrawMode.FULL || this.miner == null) {
                batch.setColor(this.i[i]);
            } else {
                batch.setColor(e);
            }
            batch.draw(Game.i.tileManager.F.SOURCE.f3159b[i], f, f2, f3, f4);
        }
        batch.setColor(Color.WHITE);
    }

    @Override // com.prineside.tdi2.Tile
    public final void postDrawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        float f6 = f4 * 0.0078125f;
        float f7 = f5 * 0.0078125f;
        if (isDepleted()) {
            batch.setColor(MaterialColor.ORANGE.P300);
            batch.draw(AssetManager.TextureRegions.i().iconPickaxe, (((f2 + 128.0f) - 32.0f) - 6.4f) * f6, (f3 + 6.4f) * f7, 32.0f * f6, 32.0f * f7);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
        if ((drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR || drawMode == MapRenderingSystem.DrawMode.DETAILED) && getResourceDensity() > 1.0f && f6 > 0.5f) {
            m.setLength(0);
            m.append('x');
            m.append(StringFormatter.compactNumberWithPrecision(getResourceDensity(), 1));
            ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont((int) (18.0f * f6));
            font.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            font.draw(batch, m, f2 + 12.0f, f3 + 27.0f);
            font.setColor(Color.WHITE);
            font.draw(batch, m, f2 + 10.0f, f3 + 29.0f);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        if (this.h) {
            a();
        }
        float f2 = f / 128.0f;
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(new TextureRegionDrawable(Game.i.tileManager.F.SOURCE.f3158a));
        image.setSize(128.0f * f2, 128.0f * f2);
        group.addActor(image);
        for (int i = 0; i < 8; i++) {
            Image image2 = new Image(new TextureRegionDrawable(Game.i.tileManager.F.SOURCE.f3159b[i]));
            image2.setSize(128.0f * f2, 128.0f * f2);
            image2.setColor(this.i[i]);
            group.addActor(image2);
        }
        int i2 = 0;
        for (ResourceType resourceType : ResourceType.values) {
            if (this.g[resourceType.ordinal()] > 0) {
                m.setLength(0);
                m.append(StringFormatter.compactNumber(this.g[resourceType.ordinal()], false));
                Label label = new Label(m, Game.i.assetManager.getLabelStyle(MathUtils.round(24.0f * f2)));
                label.setPosition(12.0f * f2, (6.0f + (i2 * 20.0f)) * f2);
                label.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                group.addActor(label);
                Label label2 = new Label(m, Game.i.assetManager.getLabelStyle(MathUtils.round(24.0f * f2)));
                label2.setPosition(10.0f * f2, (8.0f + (i2 * 20.0f)) * f2);
                label2.setColor(Game.i.resourceManager.getColor(resourceType));
                group.addActor(label2);
                i2++;
            }
        }
        Label label3 = new Label(MathUtils.round(this.f * 100.0f) + "%", Game.i.assetManager.getLabelStyle(MathUtils.round(24.0f * f2)));
        label3.setPosition(f2 * 2.0f, 86.0f * f2);
        label3.setSize(f - (10.0f * f2), label3.getPrefHeight());
        label3.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        label3.setAlignment(16);
        group.addActor(label3);
        Label label4 = new Label(MathUtils.round(this.f * 100.0f) + "%", Game.i.assetManager.getLabelStyle(MathUtils.round(24.0f * f2)));
        label4.setPosition(0.0f, 88.0f * f2);
        label4.setSize(f - (10.0f * f2), label4.getPrefHeight());
        label4.setColor(Color.WHITE);
        label4.setAlignment(16);
        group.addActor(label4);
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Resource density");
        itemCreationOverlay.textField(String.valueOf(getResourceDensity()), str -> {
            try {
                setResourceDensity(Float.parseFloat(str));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str, new Object[0]);
            }
        });
        itemCreationOverlay.label("Resources");
        Table table = new Table();
        itemCreationOverlay.form.add(table).top().left().row();
        for (ResourceType resourceType : ResourceType.values) {
            Image image = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[resourceType.ordinal()]));
            image.setColor(Game.i.resourceManager.getColor(resourceType));
            table.add((Table) image).size(32.0f).pad(8.0f).padRight(16.0f);
            table.add((Table) itemCreationOverlay.textField(String.valueOf(getResourcesCount(resourceType)), str2 -> {
                try {
                    setResourcesCount(resourceType, Integer.parseInt(str2));
                    itemCreationOverlay.updateItemIcon();
                } catch (Exception unused) {
                    c.e("bad value: " + str2, new Object[0]);
                }
            })).size(400.0f, 48.0f).padBottom(4.0f).row();
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (density: " + getResourceDensity());
        for (ResourceType resourceType : ResourceType.values) {
            sb.append(" | ").append(resourceType.name()).append(": ").append(getResourcesCount(resourceType));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/SourceTile$ResourceAmount.class */
    public static class ResourceAmount {

        /* renamed from: a, reason: collision with root package name */
        ResourceType f3156a;

        /* renamed from: b, reason: collision with root package name */
        float f3157b;
        float c;

        private ResourceAmount() {
        }

        /* synthetic */ ResourceAmount(byte b2) {
            this();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/SourceTile$SourceTileFactory.class */
    public static class SourceTileFactory extends Tile.Factory.AbstractFactory<SourceTile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f3158a;

        /* renamed from: b, reason: collision with root package name */
        final TextureRegion[] f3159b;

        public SourceTileFactory() {
            super(TileType.SOURCE);
            this.f3159b = new TextureRegion[8];
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            if (inventoryStatistics.byTileType[TileType.SOURCE.ordinal()] >= 500) {
                return 0;
            }
            return 100;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3158a = Game.i.assetManager.getTextureRegion("tile-type-source-crack");
            for (int i = 1; i <= 8; i++) {
                this.f3159b[i - 1] = Game.i.assetManager.getTextureRegion("tile-type-source-" + i);
            }
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public SourceTile create() {
            return new SourceTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public SourceTile createRandom(float f, RandomXS128 randomXS128) {
            int nextInt;
            if (randomXS128 == null) {
                randomXS128 = FastRandom.random;
            }
            SourceTile create = create();
            float f2 = 1.0f;
            for (int i = 0; i < 5; i++) {
                if (randomXS128.nextFloat() > (f * 0.25f) + 0.75f) {
                    f2 -= 0.2f;
                }
            }
            if (f2 < 0.2f) {
                f2 = 0.2f;
            }
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            create.setResourceDensity(f2);
            RarityType rarityFromQuality = ProgressManager.getRarityFromQuality(f);
            ResourceType resourceType = rarityFromQuality.ordinal() < ResourceType.values.length ? ResourceType.values[rarityFromQuality.ordinal()] : ResourceType.INFIAR;
            int round = MathUtils.round(PMath.randomTriangular(randomXS128) * 6.0f);
            int i2 = round;
            if (round <= 0) {
                i2 = 1;
            }
            if (i2 > resourceType.ordinal() + 1) {
                i2 = resourceType.ordinal() + 1;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (i3 == 0) {
                    nextInt = resourceType.ordinal();
                } else {
                    nextInt = randomXS128.nextInt(i2);
                }
                int length = (int) ((((ResourceType.values.length - nextInt) + 1.5f) * (55.0f + (f * 15.0f))) / i2);
                int i4 = length - (length % 10);
                int i5 = i4;
                if (i4 < 0) {
                    i5 = 0;
                }
                create.setResourcesCount(ResourceType.values[nextInt], create.getResourcesCount(ResourceType.values[nextInt]) + i5 + 10);
            }
            return create;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public SourceTile fromJson(JsonValue jsonValue) {
            SourceTile sourceTile = (SourceTile) super.fromJson(jsonValue);
            if (jsonValue.has("d")) {
                JsonValue jsonValue2 = jsonValue.get("d");
                sourceTile.f = jsonValue2.getFloat("rd", 1.0f);
                if (sourceTile.f > 100.0f) {
                    sourceTile.f = 100.0f;
                }
                if (sourceTile.f < 0.0f) {
                    sourceTile.f = 0.0f;
                }
                JsonValue jsonValue3 = jsonValue2.get("r");
                if (jsonValue3 != null) {
                    Iterator<JsonValue> iterator2 = jsonValue3.iterator2();
                    while (iterator2.hasNext()) {
                        JsonValue next = iterator2.next();
                        ResourceType valueOf = ResourceType.valueOf(next.getString("t"));
                        int i = next.getInt(FlexmarkHtmlConverter.A_NODE);
                        int i2 = i;
                        if (i < 0) {
                            i2 = 0;
                        }
                        sourceTile.g[valueOf.ordinal()] = i2;
                    }
                }
                SourceTile.a(sourceTile, true);
            }
            if (jsonValue.has("miner")) {
                sourceTile.miner = Game.i.minerManager.fromJson(jsonValue.get("miner"));
            }
            return sourceTile;
        }
    }
}
