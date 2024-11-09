package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/SpawnTile.class */
public final class SpawnTile extends Tile {
    public int difficulty;
    private Array<AllowedEnemyConfig> d;
    private ObjectSet<EnemyType> e;

    @NAGS
    private float f;

    @NAGS
    private ParticleEffectPool.PooledEffect g;
    public Array<Array<EnemyGroup.SpawnEnemyGroup>> enemySpawnQueues;
    private static final TLog c = TLog.forClass(SpawnTile.class);
    private static final StringBuilder h = new StringBuilder();

    /* synthetic */ SpawnTile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.difficulty, true);
        kryo.writeObject(output, this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.enemySpawnQueues);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.difficulty = input.readVarInt(true);
        this.d = (Array) kryo.readObject(input, Array.class);
        this.e = (ObjectSet) kryo.readObject(input, ObjectSet.class);
        this.enemySpawnQueues = (Array) kryo.readObject(input, Array.class);
    }

    private SpawnTile() {
        super(TileType.SPAWN);
        this.difficulty = 100;
        this.d = new Array<>(AllowedEnemyConfig.class);
        this.e = new ObjectSet<>();
        this.enemySpawnQueues = new Array<>(Array.class);
        for (int i = 0; i <= 10; i++) {
            this.enemySpawnQueues.add(new Array<>(EnemyGroup.SpawnEnemyGroup.class));
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        table.add((Table) new Label(Game.i.localeManager.i18n.get("difficulty") + ": " + this.difficulty + "%", Game.i.assetManager.getLabelStyle(24))).growX().row();
        Table table2 = new Table();
        table.add(table2).padTop(8.0f).padBottom(4.0f).growX().row();
        Label label = new Label(Game.i.localeManager.i18n.get("enemy_type").toUpperCase(), Game.i.assetManager.getLabelStyle(18));
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label.setAlignment(8);
        table2.add((Table) label).growX();
        Label label2 = new Label(Game.i.localeManager.i18n.get("waves").toUpperCase(), Game.i.assetManager.getLabelStyle(18));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label2.setAlignment(16);
        table2.add((Table) label2);
        Table table3 = new Table();
        table.add(table3).growX().row();
        int i = 0;
        Array.ArrayIterator<AllowedEnemyConfig> it = getAllowedEnemies().iterator();
        while (it.hasNext()) {
            AllowedEnemyConfig next = it.next();
            Table table4 = new Table();
            int i2 = i;
            i++;
            mapEditorItemInfoMenu.listRowBg(i2, table4);
            table3.add(table4).growX().height(32.0f).row();
            table4.add((Table) new Image(Game.i.enemyManager.getFactory(next.enemyType).getTexture())).size(24.0f).padRight(12.0f);
            Enemy.Factory<? extends Enemy> factory = Game.i.enemyManager.getFactory(next.enemyType);
            Label label3 = new Label(factory.getTitle(), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(factory.getColor());
            table4.add((Table) label3).left().padRight(12.0f);
            table4.add().height(1.0f).growX();
            Label label4 = new Label("", Game.i.assetManager.getLabelStyle(21));
            if (next.firstWave <= 1 && next.lastWave <= 0) {
                label4.setText(Game.i.localeManager.i18n.get("spawn_tile_menu_spawns_always"));
                label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            } else {
                label4.setText(next.firstWave + " - " + (next.lastWave <= 0 ? "%" : Integer.valueOf(next.lastWave)));
            }
            table4.add((Table) label4);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f) {
        Table table2 = new Table();
        table.add(table2).width(f).row();
        table2.add();
        Image image = new Image(Game.i.assetManager.getDrawable("icon-wave"));
        image.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) image).size(32.0f).top().left().padLeft(12.0f).padBottom(4.0f).row();
        Array.ArrayIterator<AllowedEnemyConfig> it = getAllowedEnemies().iterator();
        while (it.hasNext()) {
            AllowedEnemyConfig next = it.next();
            table2.add((Table) new Image(Game.i.enemyManager.getFactory(next.enemyType).getTexture())).size(32.0f).padRight(16.0f).padLeft(16.0f).padTop(4.0f);
            Label label = new Label("", Game.i.assetManager.getLabelStyle(24));
            int max = StrictMath.max(next.firstWave, 1);
            if (next.lastWave <= 0) {
                label.setText(max + " - " + ((Object) Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-infinity>")));
            } else {
                label.setText(max + " - " + next.lastWave);
            }
            table2.add((Table) label).minWidth(100.0f).row();
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final int generateSeedSalt() {
        int i = this.difficulty;
        for (int i2 = 0; i2 < this.d.size; i2++) {
            i = (i * 29) + this.d.get(i2).enemyType.ordinal();
        }
        return i;
    }

    public final void addAllowedEnemy(EnemyType enemyType, int i, int i2) {
        boolean z = false;
        int i3 = 0;
        while (true) {
            if (i3 >= this.d.size) {
                break;
            }
            AllowedEnemyConfig allowedEnemyConfig = this.d.items[i3];
            if (allowedEnemyConfig.enemyType != enemyType) {
                i3++;
            } else {
                allowedEnemyConfig.firstWave = i;
                allowedEnemyConfig.lastWave = i2;
                z = true;
                break;
            }
        }
        if (!z) {
            AllowedEnemyConfig allowedEnemyConfig2 = new AllowedEnemyConfig();
            allowedEnemyConfig2.enemyType = enemyType;
            allowedEnemyConfig2.firstWave = i;
            allowedEnemyConfig2.lastWave = i2;
            this.d.add(allowedEnemyConfig2);
            this.e.add(enemyType);
        }
    }

    public final void removeAllowedEnemy(EnemyType enemyType) {
        if (this.e.contains(enemyType)) {
            this.e.remove(enemyType);
            for (int i = 0; i < this.d.size; i++) {
                if (this.d.items[i].enemyType == enemyType) {
                    this.d.removeIndex(i);
                    return;
                }
            }
        }
    }

    public final AllowedEnemyConfig getEnemyConfig(EnemyType enemyType) {
        for (int i = 0; i < this.d.size; i++) {
            if (this.d.items[i].enemyType == enemyType) {
                return this.d.items[i];
            }
        }
        return null;
    }

    public final void setAllowedEnemies(Array<AllowedEnemyConfig> array) {
        this.d.clear();
        this.d.addAll(array);
        this.e.clear();
        for (int i = 0; i < array.size; i++) {
            this.e.add(array.get(i).enemyType);
        }
    }

    public final Array<AllowedEnemyConfig> getAllowedEnemies() {
        return this.d;
    }

    public final ObjectSet<EnemyType> getAllowedEnemiesSet() {
        return this.e;
    }

    public final boolean isEnemyAllowedOnWave(EnemyType enemyType, int i) {
        if (this.e.contains(enemyType)) {
            for (int i2 = 0; i2 < this.d.size; i2++) {
                AllowedEnemyConfig allowedEnemyConfig = this.d.items[i2];
                if (allowedEnemyConfig.enemyType == enemyType) {
                    if (i >= allowedEnemyConfig.firstWave) {
                        return i <= allowedEnemyConfig.lastWave || allowedEnemyConfig.lastWave <= 0;
                    }
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        if (this.difficulty >= 450 || this.difficulty < 60) {
            return RarityType.LEGENDARY;
        }
        if (this.difficulty >= 350 || this.difficulty < 70) {
            return RarityType.EPIC;
        }
        if (this.difficulty >= 250 || this.difficulty < 80) {
            return RarityType.VERY_RARE;
        }
        if (this.difficulty >= 150 || this.difficulty < 90) {
            return RarityType.RARE;
        }
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Tile
    public final float getWalkCost(boolean z) {
        return 1.0f;
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_SPAWNS;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        SpawnTile spawnTile = (SpawnTile) tile;
        Array<AllowedEnemyConfig> array = new Array<>();
        for (int i = 0; i < spawnTile.d.size; i++) {
            AllowedEnemyConfig allowedEnemyConfig = spawnTile.d.items[i];
            array.add(new AllowedEnemyConfig(allowedEnemyConfig.enemyType, allowedEnemyConfig.firstWave, allowedEnemyConfig.lastWave));
        }
        setAllowedEnemies(array);
        this.difficulty = spawnTile.difficulty;
    }

    @Override // com.prineside.tdi2.Tile
    public final float getValue() {
        return this.difficulty * 0.01f;
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        float f;
        if (this.difficulty < 100) {
            f = 100.0f - ((this.difficulty - 50) * 2.0f);
        } else {
            f = (this.difficulty - 100) / 4.0f;
        }
        array.add(new ItemStack(Item.D.GREEN_PAPER, 200 + MathUtils.ceil((float) StrictMath.pow(f * 3.0f, 1.2999999523162842d))));
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        double d;
        double d2 = (this.difficulty / 500.0d) * 0.8d;
        if (this.difficulty > 400) {
            d2 += 0.2d;
        } else if (this.difficulty > 300) {
            d2 += 0.125d;
        } else if (this.difficulty > 200) {
            d2 += 0.05d;
        } else if (this.difficulty > 100) {
            d2 += 0.02d;
        }
        if (this.d.size <= 2) {
            d = 0.15d;
        } else if (this.d.size == 3) {
            d = 0.25d;
        } else if (this.d.size == 4) {
            d = 0.4d;
        } else if (this.d.size == 5) {
            d = 0.6d;
        } else {
            d = 0.85d;
        }
        for (int i = 0; i < this.d.size; i++) {
            EnemyType enemyType = this.d.items[i].enemyType;
            if (enemyType == EnemyType.ARMORED || enemyType == EnemyType.HEALER) {
                d += 0.075d;
            } else if (enemyType == EnemyType.LIGHT || enemyType == EnemyType.ICY) {
                d += 0.05d;
            }
        }
        return 0.01d + (d2 * d);
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.KIND) {
            return 5000 + this.difficulty;
        }
        if (this.difficulty < 100) {
            return (getRarity().ordinal() * 1000) + (100 - this.difficulty);
        }
        return (getRarity().ordinal() * 1000) + this.difficulty;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return true;
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        if (this.g != null) {
            this.g.allowCompletion();
            this.g = null;
        }
        this.enemySpawnQueues.clear();
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        float f6 = f4 * 0.0078125f;
        float f7 = f5 * 0.0078125f;
        boolean z = true;
        if (this.S != null) {
            z = false;
            Array<EnemyGroup.SpawnEnemyGroup> array = this.enemySpawnQueues.get(0);
            int i = 0;
            while (true) {
                if (i >= array.size) {
                    break;
                }
                if (array.get(i).count - array.get(i).getSpawnedCount() == 0) {
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (!z && this.enemySpawnQueues.get(1).size != 0) {
                z = true;
            }
        }
        if (z && this.S != null) {
            if (Game.i.settingsManager.isParticlesDrawing()) {
                if (this.g == null) {
                    this.g = Game.i.tileManager.F.SPAWN.e.obtain();
                    this.g.setPosition(this.center.x, this.center.y);
                    this.S._particle.addParticle(this.g, false);
                }
            } else if (this.g != null) {
                this.g.allowCompletion();
                this.g = null;
            }
        } else if (this.S != null && this.g != null) {
            this.g.allowCompletion();
            this.g = null;
        }
        this.f += f;
        float f8 = ((this.f % 8.0f) * 360.0f) / 8.0f;
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        if (z) {
            batch.draw(Game.i.tileManager.F.SPAWN.f3166b, f2, f3, 64.0f * f6, 64.0f * f7, 128.0f * f6, 128.0f * f7, 1.0f, 1.0f, f8);
            batch.draw(Game.i.tileManager.F.SPAWN.f3165a, f2, f3, 128.0f * f6, 128.0f * f6);
            if (this.S != null) {
                batch.setColor(1.0f, 1.0f, 1.0f, ((PMath.sin(this.f) + 1.0f) * 0.25f) + 0.25f);
                batch.draw(Game.i.tileManager.F.SPAWN.c, f2, f3, 128.0f * f6, 128.0f * f7);
            }
        } else {
            batch.draw(Game.i.tileManager.F.SPAWN.d, f2, f3, 128.0f * f6, 128.0f * f7);
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        if (drawMode == MapRenderingSystem.DrawMode.FULL || drawMode == MapRenderingSystem.DrawMode.DETAILED || drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            int i2 = (int) (21.0f * f6);
            if (i2 >= 12) {
                ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(i2);
                h.setLength(0);
                h.append(this.difficulty).append('%');
                font.draw(batch, h, f2 + (10.0f * f6), f3 + (29.0f * f7));
            }
            int i3 = this.d.size;
            int i4 = i3;
            if (i3 > 4) {
                i4 = 4;
            }
            float f9 = (128.0f - (24.0f * i4)) * 0.5f;
            float f10 = (128.0f - (((this.d.size / 4) + 1) * 24.0f)) * 0.5f;
            for (int i5 = 0; i5 < this.d.size; i5++) {
                batch.draw(Game.i.enemyManager.getFactory(this.d.items[i5].enemyType).getTexture(), f2 + ((((f9 + ((i5 % 4) * 24.0f)) - 4.0f) + 6.0f) * f6), f3 + ((((f10 + ((r0 - ((i5 / 4) + 1)) * 24.0f)) - 4.0f) + 6.0f) * f7), 20.0f * f6, 20.0f * f7);
            }
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        float f2 = f / 128.0f;
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(Game.i.tileManager.getRoadTexture(null, null, null, null));
        image.setSize(f, f);
        group.addActor(image);
        Image image2 = new Image(new TextureRegionDrawable(Game.i.tileManager.F.SPAWN.f3166b));
        image2.setSize(f, f);
        group.addActor(image2);
        Image image3 = new Image(new TextureRegionDrawable(Game.i.tileManager.F.SPAWN.f3165a));
        image3.setSize(f, f);
        group.addActor(image3);
        Image image4 = new Image(new TextureRegionDrawable(Game.i.tileManager.F.SPAWN.c));
        image4.setSize(f, f);
        image4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        group.addActor(image4);
        h.setLength(0);
        h.append(this.difficulty).append('%');
        Label label = new Label(h, Game.i.assetManager.getLabelStyle(MathUtils.round(24.0f * f2)));
        label.setPosition(10.0f, 8.0f);
        group.addActor(label);
        Table table = new Table();
        table.setSize(f, f - (24.0f * f2));
        table.setPosition(0.0f, 24.0f * f2);
        group.addActor(table);
        for (int i = 0; i < this.d.size; i++) {
            Cell pad = table.add((Table) new Image(Game.i.enemyManager.getFactory(this.d.items[i].enemyType).getTexture())).size(20.0f * f2, 20.0f * f2).pad(f2 * 2.0f);
            if (i % 4 == 3) {
                pad.row();
            }
        }
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("d", Integer.valueOf(this.difficulty));
        json.writeArrayStart("ae");
        for (int i = 0; i < this.d.size; i++) {
            AllowedEnemyConfig allowedEnemyConfig = this.d.get(i);
            json.writeObjectStart();
            json.writeValue("t", allowedEnemyConfig.enemyType.name());
            json.writeValue("f", Integer.valueOf(allowedEnemyConfig.firstWave));
            json.writeValue("l", Integer.valueOf(allowedEnemyConfig.lastWave));
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        if (!super.sameAs(tile)) {
            return false;
        }
        SpawnTile spawnTile = (SpawnTile) tile;
        if (spawnTile.difficulty != this.difficulty || spawnTile.d.size != this.d.size) {
            return false;
        }
        for (int i = 0; i < this.d.size; i++) {
            AllowedEnemyConfig allowedEnemyConfig = this.d.items[i];
            if (!spawnTile.e.contains(allowedEnemyConfig.enemyType)) {
                return false;
            }
            int i2 = 0;
            while (true) {
                if (i2 < spawnTile.d.size) {
                    AllowedEnemyConfig allowedEnemyConfig2 = spawnTile.d.items[i2];
                    if (allowedEnemyConfig2.enemyType != allowedEnemyConfig.enemyType) {
                        i2++;
                    } else if (allowedEnemyConfig2.firstWave != allowedEnemyConfig.firstWave || allowedEnemyConfig2.lastWave != allowedEnemyConfig.lastWave) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Difficulty");
        itemCreationOverlay.textField(String.valueOf(this.difficulty), str -> {
            try {
                this.difficulty = Integer.parseInt(str);
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                c.e("bad value: " + str, new Object[0]);
            }
        });
        itemCreationOverlay.label("Allowed enemies");
        Table table = new Table();
        itemCreationOverlay.form.add(table).top().left().row();
        for (final EnemyType enemyType : EnemyType.mainEnemyTypes) {
            table.add((Table) new Image(Game.i.enemyManager.getFactory(enemyType).getTexture())).size(32.0f).pad(8.0f).padRight(12.0f);
            final LabelToggleButton labelToggleButton = itemCreationOverlay.toggle(false, Game.i.enemyManager.getFactory(enemyType).getTitle(), getAllowedEnemiesSet().contains(enemyType), bool -> {
                if (bool.booleanValue()) {
                    addAllowedEnemy(enemyType, 1, 0);
                } else {
                    removeAllowedEnemy(enemyType);
                }
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                itemCreationOverlay.updateForm();
            });
            table.add(labelToggleButton).height(48.0f).width(400.0f).padLeft(16.0f).padBottom(4.0f);
            AllowedEnemyConfig enemyConfig = getEnemyConfig(enemyType);
            final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(enemyConfig == null ? "1" : String.valueOf(enemyConfig.firstWave), itemCreationOverlay.textFieldStyle);
            table.add((Table) textFieldXPlatform).size(100.0f, 48.0f).padLeft(16.0f);
            textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.SpawnTile.1
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        int parseInt = Integer.parseInt(textFieldXPlatform.getText());
                        if (parseInt > 0) {
                            AllowedEnemyConfig enemyConfig2 = SpawnTile.this.getEnemyConfig(enemyType);
                            if (enemyConfig2 == null) {
                                SpawnTile.this.addAllowedEnemy(enemyType, parseInt, 0);
                                labelToggleButton.setEnabled(true);
                            } else {
                                enemyConfig2.firstWave = parseInt;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(enemyConfig == null ? "0" : String.valueOf(enemyConfig.lastWave), itemCreationOverlay.textFieldStyle);
            table.add((Table) textFieldXPlatform2).size(100.0f, 48.0f).padLeft(16.0f).row();
            textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.SpawnTile.2
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        int parseInt = Integer.parseInt(textFieldXPlatform2.getText());
                        if (parseInt >= 0) {
                            AllowedEnemyConfig enemyConfig2 = SpawnTile.this.getEnemyConfig(enemyType);
                            if (enemyConfig2 == null) {
                                SpawnTile.this.addAllowedEnemy(enemyType, 1, parseInt);
                                labelToggleButton.setEnabled(true);
                            } else {
                                enemyConfig2.lastWave = parseInt;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/SpawnTile$SpawnTileFactory.class */
    public static class SpawnTileFactory extends Tile.Factory.AbstractFactory<SpawnTile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f3165a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f3166b;
        TextureRegion c;
        TextureRegion d;
        ParticleEffectPool e;

        public SpawnTileFactory() {
            super(TileType.SPAWN);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            int i = inventoryStatistics.byTileType[TileType.SPAWN.ordinal()];
            if (i == 0) {
                return 1000;
            }
            if (i >= 500) {
                return 0;
            }
            return 50;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3165a = Game.i.assetManager.getTextureRegion("tile-type-spawn-overlay");
            this.f3166b = Game.i.assetManager.getTextureRegion("tile-type-spawn-portal");
            this.c = Game.i.assetManager.getTextureRegion("tile-type-spawn-glow");
            this.d = Game.i.assetManager.getTextureRegion("tile-type-spawn-inactive");
            this.e = Game.i.assetManager.getParticleEffectPool("portal.prt");
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public SpawnTile create() {
            return new SpawnTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public SpawnTile createRandom(float f, RandomXS128 randomXS128) {
            int i;
            int i2;
            if (randomXS128 == null) {
                randomXS128 = FastRandom.random;
            }
            SpawnTile create = create();
            RarityType rarityFromQuality = ProgressManager.getRarityFromQuality(f);
            if (randomXS128.nextFloat() < 0.5f) {
                switch (rarityFromQuality) {
                    case LEGENDARY:
                        i = 50;
                        i2 = 59;
                        break;
                    case EPIC:
                        i = 60;
                        i2 = 69;
                        break;
                    case VERY_RARE:
                        i = 70;
                        i2 = 79;
                        break;
                    case RARE:
                        i = 80;
                        i2 = 89;
                        break;
                    default:
                        i = 90;
                        i2 = 100;
                        break;
                }
            } else {
                switch (rarityFromQuality) {
                    case LEGENDARY:
                        i = 449;
                        i2 = 500;
                        break;
                    case EPIC:
                        i = 350;
                        i2 = 449;
                        break;
                    case VERY_RARE:
                        i = 250;
                        i2 = 349;
                        break;
                    case RARE:
                        i = 150;
                        i2 = 249;
                        break;
                    default:
                        i = 100;
                        i2 = 149;
                        break;
                }
            }
            create.difficulty = i + MathUtils.round((i2 - i) * randomXS128.nextFloat());
            if (create.difficulty < 150) {
                create.difficulty -= create.difficulty % 5;
            } else if (create.difficulty > 495) {
                create.difficulty = 500;
            } else {
                create.difficulty -= create.difficulty % 10;
            }
            Array<AllowedEnemyConfig> array = new Array<>(AllowedEnemyConfig.class);
            array.add(new AllowedEnemyConfig(EnemyType.REGULAR, 1, 0));
            array.add(new AllowedEnemyConfig(EnemyType.BOSS, 1, 0));
            int round = 4 + StrictMath.round(randomXS128.nextFloat() * f * 3.0f);
            while (round > 0) {
                EnemyType enemyType = EnemyType.mainEnemyTypes[randomXS128.nextInt(EnemyType.mainEnemyTypes.length)];
                boolean z = false;
                int i3 = 0;
                while (true) {
                    if (i3 < array.size) {
                        if (array.items[i3].enemyType != enemyType) {
                            i3++;
                        } else {
                            z = true;
                        }
                    }
                }
                if (!z) {
                    round--;
                    array.add(new AllowedEnemyConfig(enemyType, 1, 0));
                }
            }
            Threads.sortGdxArray(array, (allowedEnemyConfig, allowedEnemyConfig2) -> {
                return Integer.compare(allowedEnemyConfig.enemyType.ordinal(), allowedEnemyConfig2.enemyType.ordinal());
            });
            create.setAllowedEnemies(array);
            return create;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public SpawnTile fromJson(JsonValue jsonValue) {
            SpawnTile spawnTile = (SpawnTile) super.fromJson(jsonValue);
            if (jsonValue.has("d")) {
                JsonValue jsonValue2 = jsonValue.get("d");
                JsonValue jsonValue3 = jsonValue2.get("et");
                if (jsonValue3 != null) {
                    Array<AllowedEnemyConfig> array = new Array<>();
                    Iterator<JsonValue> iterator2 = jsonValue3.iterator2();
                    while (iterator2.hasNext()) {
                        array.add(new AllowedEnemyConfig(EnemyType.valueOf(iterator2.next().asString()), 1, 0));
                    }
                    spawnTile.setAllowedEnemies(array);
                } else {
                    JsonValue jsonValue4 = jsonValue2.get("ae");
                    Array<AllowedEnemyConfig> array2 = new Array<>();
                    Iterator<JsonValue> iterator22 = jsonValue4.iterator2();
                    while (iterator22.hasNext()) {
                        JsonValue next = iterator22.next();
                        array2.add(new AllowedEnemyConfig(EnemyType.valueOf(next.getString("t")), next.getInt("f", 1), next.getInt("l", 0)));
                    }
                    if (array2.size == 0) {
                        array2.add(new AllowedEnemyConfig(EnemyType.REGULAR, 1, 0));
                    }
                    spawnTile.setAllowedEnemies(array2);
                }
                spawnTile.difficulty = jsonValue2.getInt("d", 100);
                if (spawnTile.difficulty <= 0) {
                    spawnTile.difficulty = 1;
                }
                if (spawnTile.difficulty > 65535) {
                    spawnTile.difficulty = 65535;
                }
            }
            return spawnTile;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/SpawnTile$AllowedEnemyConfig.class */
    public static class AllowedEnemyConfig implements KryoSerializable {
        public EnemyType enemyType;
        public int firstWave;
        public int lastWave;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObjectOrNull(output, this.enemyType, EnemyType.class);
            output.writeVarInt(this.firstWave, true);
            output.writeInt(this.lastWave);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.enemyType = (EnemyType) kryo.readObjectOrNull(input, EnemyType.class);
            this.firstWave = input.readVarInt(true);
            this.lastWave = input.readInt();
        }

        public AllowedEnemyConfig() {
            this.firstWave = 1;
            this.lastWave = 0;
        }

        public AllowedEnemyConfig(EnemyType enemyType, int i, int i2) {
            this.firstWave = 1;
            this.lastWave = 0;
            this.enemyType = enemyType;
            this.firstWave = i;
            this.lastWave = i2;
        }
    }
}
