package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.RenderingManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/TargetTile.class */
public final class TargetTile extends Tile {
    private Array<GameValueConfig> d;
    private boolean e;
    private boolean f;
    private int g;

    @NAGS
    private boolean h;

    @NAGS
    private Color i;

    @NAGS
    private Color j;

    @NAGS
    private TextureRegion k;

    @NAGS
    private float l;

    @NAGS
    private boolean m;
    private static final TLog c = TLog.forClass(TargetTile.class);
    private static final Color n = new Color();
    private static final Comparator<GameValueConfig> o = (gameValueConfig, gameValueConfig2) -> {
        return Integer.compare(gameValueConfig.getType().ordinal(), gameValueConfig2.getType().ordinal());
    };

    /* synthetic */ TargetTile(byte b2) {
        this();
    }

    public final Color getBaseColor() {
        return this.i;
    }

    public final Color getCoreColor() {
        return this.j;
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.d);
        output.writeBoolean(this.e);
        output.writeBoolean(this.f);
        output.writeVarInt(this.g, true);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = (Array) kryo.readObject(input, Array.class);
        this.e = input.readBoolean();
        this.f = input.readBoolean();
        this.g = input.readVarInt(true);
    }

    private TargetTile() {
        super(TileType.TARGET);
        this.d = new Array<>(GameValueConfig.class);
        this.e = false;
        this.f = false;
        this.g = 0;
        this.h = true;
        this.i = Color.WHITE;
        this.j = Color.WHITE;
    }

    public final int getOverloadLevel() {
        return this.g;
    }

    public final void setOverloadLevel(int i) {
        this.g = i;
    }

    public final void showHitEffect(Vector2 vector2) {
        if (this.S != null && this.S._particle != null) {
            c();
            ParticleEffectPool.PooledEffect obtain = Game.i.tileManager.F.TARGET.d.obtain();
            obtain.setPosition((this.f1770a << 7) + 64, (this.f1771b << 7) + 64);
            this.S._particle.addParticle(obtain, false);
            this.l += 0.4f;
            if (this.l > 1.0f) {
                this.l = 1.0f;
            }
            n.set(this.i);
            n.f889a = 0.5f;
            this.S._particle.addFlashParticleColored(Game.i.tileManager.F.TARGET.f3172b, (this.f1770a << 7) + 64, (this.f1771b << 7) + 64, 64.0f, 64.0f, 128.0f, 128.0f, 0.0f, n);
            n.set(this.j);
            n.f889a = 0.5f;
            this.S._particle.addFlashParticleColored(this.k, (this.f1770a << 7) + 64, (this.f1771b << 7) + 64, 64.0f, 64.0f, 128.0f, 128.0f, 0.0f, n);
        }
    }

    public final void startExplosionEffect() {
        this.m = true;
        if (this.S._particle != null) {
            c();
            this.S._particle.addShatterParticle(Game.i.tileManager.F.TARGET.f3172b, (this.f1770a << 7) + 64, (this.f1771b << 7) + 64, 128.0f, 0.0f, 1.0f, this.i, new ExplosionInterpolation((byte) 0), false);
            this.S._particle.addShatterParticle(this.k, (this.f1770a << 7) + 64, (this.f1771b << 7) + 64, 128.0f, 0.0f, 1.0f, this.j, new ExplosionInterpolation((byte) 0), true);
            ParticleEffectPool.PooledEffect obtain = Game.i.tileManager.F.TARGET.c.obtain();
            obtain.setPosition((this.f1770a << 7) + 64, (this.f1771b << 7) + 64);
            this.S._particle.addParticle(obtain, false);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        if (isUseStockGameValues()) {
            Label label = new Label(Game.i.localeManager.i18n.get("portal_disables_researches"), Game.i.assetManager.getLabelStyle(24));
            label.setColor(MaterialColor.RED.P500);
            label.setAlignment(8);
            table.add((Table) label).growX().height(32.0f).row();
        }
        if (isDisableAbilities()) {
            Label label2 = new Label(Game.i.localeManager.i18n.get("portal_disables_abilities"), Game.i.assetManager.getLabelStyle(24));
            label2.setColor(MaterialColor.YELLOW.P500);
            label2.setAlignment(8);
            table.add((Table) label2).growX().height(32.0f).row();
        }
        if (isWalkableTiles()) {
            Label label3 = new Label(Game.i.gameValueManager.getTitle(GameValueType.ENEMIES_WALK_ON_PLATFORMS), Game.i.assetManager.getLabelStyle(24));
            label3.setColor(MaterialColor.LIGHT_BLUE.P500);
            label3.setAlignment(8);
            table.add((Table) label3).growX().height(32.0f).row();
        }
        Array<GameValueConfig> gameValues = getGameValues();
        for (int i = 0; i < gameValues.size; i++) {
            GameValueConfig gameValueConfig = gameValues.get(i);
            Table table2 = new Table();
            mapEditorItemInfoMenu.listRowBg(i, table2);
            table.add(table2).growX().height(32.0f).row();
            table2.add((Table) new Image(Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).getIcon())).size(24.0f).padRight(8.0f);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.setLength(0);
            stringBuilder.append(Game.i.gameValueManager.getTitle(gameValueConfig.getType()));
            if (stringBuilder.length() > 34) {
                stringBuilder.setLength(34);
                stringBuilder.append("...");
            }
            table2.add((Table) new LimitedWidthLabel(stringBuilder, 21, 18, 250.0f)).growX();
            if (!gameValueConfig.isAllowBonuses()) {
                Image image = new Image(Game.i.assetManager.getDrawable("icon-check"));
                image.setColor(MaterialColor.AMBER.P500);
                table2.add((Table) image).size(24.0f).padLeft(8.0f);
            } else {
                table2.add();
            }
            stringBuilder.setLength(0);
            GameValueManager.ValueUnits valueUnits = Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).units;
            if (valueUnits != GameValueManager.ValueUnits.BOOLEAN) {
                stringBuilder.append(Game.i.gameValueManager.formatEffectValue(gameValueConfig.getValue(), valueUnits));
                if (gameValueConfig.isOverwrite()) {
                    stringBuilder.setCharAt(0, '=');
                }
            }
            Label label4 = new Label(stringBuilder, Game.i.assetManager.getLabelStyle(21));
            label4.setAlignment(16);
            table2.add((Table) label4);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f) {
        if (isUseStockGameValues()) {
            Label label = new Label(Game.i.localeManager.i18n.get("portal_disables_researches"), Game.i.assetManager.getLabelStyle(21));
            label.setColor(MaterialColor.RED.P500);
            label.setWrap(true);
            label.setAlignment(1);
            table.add((Table) label).width(f - 16.0f).row();
        }
        if (isDisableAbilities()) {
            Label label2 = new Label(Game.i.localeManager.i18n.get("portal_disables_abilities"), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(MaterialColor.YELLOW.P500);
            label2.setWrap(true);
            label2.setAlignment(1);
            table.add((Table) label2).width(f - 16.0f).row();
        }
        if (isWalkableTiles()) {
            Label label3 = new Label(Game.i.gameValueManager.getTitle(GameValueType.ENEMIES_WALK_ON_PLATFORMS), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(MaterialColor.LIGHT_BLUE.P500);
            label3.setWrap(true);
            label3.setAlignment(1);
            table.add((Table) label3).width(f - 16.0f).row();
        }
        Table table2 = new Table();
        table.add(table2).width(f).padTop(8.0f).row();
        Array<GameValueConfig> gameValues = getGameValues();
        if (gameValues.size != 0) {
            for (int i = 0; i < gameValues.size; i++) {
                final GameValueConfig gameValueConfig = gameValues.get(i);
                Group group = new Group();
                group.setTransform(false);
                if (i % 2 == 0) {
                    Image image = new Image(Game.i.assetManager.getDrawable("gradient-horizontal"));
                    image.setSize(f, 43.0f);
                    image.setPosition(0.0f, -2.0f);
                    image.setColor(0.0f, 0.0f, 0.0f, 0.21f);
                    group.addActor(image);
                }
                Image image2 = new Image(Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).createIconForBackground(new Color(623191551)));
                image2.setPosition(3.75f, 4.5f);
                image2.setSize(30.0f, 30.0f);
                group.addActor(image2);
                LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(Game.i.gameValueManager.getTitle(gameValueConfig.getType()), 21, 18, 172.5f);
                limitedWidthLabel.setSize(150.0f, 39.0f);
                limitedWidthLabel.setPosition(56.0f, 0.0f);
                limitedWidthLabel.setTouchable(Touchable.enabled);
                limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.tiles.TargetTile.1
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f2, float f3) {
                        Dialog.i().showAlert(Game.i.gameValueManager.getTitle(gameValueConfig.getType()));
                    }
                });
                group.addActor(limitedWidthLabel);
                if (!gameValueConfig.isAllowBonuses()) {
                    Image image3 = new Image(Game.i.assetManager.getDrawable("icon-check"));
                    image3.setSize(24.0f, 24.0f);
                    image3.setPosition(310.5f, 7.5f);
                    group.addActor(image3);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.setLength(0);
                GameValueManager.ValueUnits valueUnits = Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).units;
                if (valueUnits != GameValueManager.ValueUnits.BOOLEAN) {
                    stringBuilder.append(Game.i.gameValueManager.formatEffectValue(gameValueConfig.getValue(), valueUnits));
                    if (gameValueConfig.isOverwrite()) {
                        stringBuilder.setCharAt(0, '=');
                    }
                    if (stringBuilder.length == 1) {
                        stringBuilder.append('0');
                    }
                } else if (gameValueConfig.getValue() == 0.0d) {
                    stringBuilder.append(Game.i.localeManager.i18n.get("disabled").toLowerCase(Locale.ENGLISH));
                }
                Label label4 = new Label(stringBuilder, Game.i.assetManager.getLabelStyle(24));
                label4.setPosition((f - 75.0f) - 8.0f, 0.0f);
                label4.setSize(75.0f, 39.0f);
                label4.setAlignment(16);
                group.addActor(label4);
                table2.add((Table) group).size(f, 39.0f).padBottom(4.0f).row();
            }
            return;
        }
        table.add((Table) new Label(Game.i.localeManager.i18n.get("base_has_no_effects"), Game.i.assetManager.getLabelStyle(21))).row();
    }

    private void c() {
        if (this.h) {
            this.h = false;
            int i = 420;
            for (int i2 = 0; i2 < this.d.size; i2++) {
                i = (((((i * 27) + this.d.get(i2).getType().ordinal()) * 31) + (this.d.get(i2).isOverwrite() ? 1 : 0) + (this.d.get(i2).isAllowBonuses() ? 3 : 0)) * 37) + ((int) (this.d.get(i2).getValue() * 10.0d));
            }
            if (this.e) {
                i = (i * 23) + 7;
            }
            if (this.f) {
                i = (i * 31) + 5;
            }
            int abs = StrictMath.abs(i) % 1296;
            int i3 = (abs / 18) / 18;
            int i4 = (abs / 18) % 18;
            int i5 = abs % 18;
            switch (i3) {
                case 1:
                    this.k = Game.i.assetManager.getTextureRegion("tile-type-target-core-1");
                    break;
                case 2:
                    this.k = Game.i.assetManager.getTextureRegion("tile-type-target-core-2");
                    break;
                case 3:
                    this.k = Game.i.assetManager.getTextureRegion("tile-type-target-core-3");
                    break;
                default:
                    this.k = Game.i.assetManager.getTextureRegion("tile-type-target-core-4");
                    break;
            }
            this.j = MaterialColor.allColors[i5][MaterialColor.Variants.P500.ordinal()];
            this.i = MaterialColor.allColors[i4][MaterialColor.Variants.P500.ordinal()];
        }
    }

    public final void addGameValue(GameValueConfig gameValueConfig) {
        Preconditions.checkNotNull(gameValueConfig, "config can not be null");
        this.d.add(gameValueConfig);
        this.h = true;
    }

    public final void removeGameValue(GameValueConfig gameValueConfig) {
        Preconditions.checkNotNull(gameValueConfig, "config can not be null");
        this.d.removeValue(gameValueConfig, true);
        this.h = true;
    }

    public final void updateAppearance() {
        this.h = true;
    }

    public final Array<GameValueConfig> getGameValues() {
        return this.d;
    }

    public final boolean isDisableAbilities() {
        return this.f;
    }

    public final boolean isWalkableTiles() {
        for (int i = 0; i < this.d.size; i++) {
            GameValueConfig gameValueConfig = this.d.items[i];
            if (gameValueConfig.getType() == GameValueType.ENEMIES_WALK_ON_PLATFORMS && gameValueConfig.getValue() >= 1.0d) {
                return true;
            }
        }
        return false;
    }

    public final boolean isUseStockGameValues() {
        return this.e;
    }

    public final void setUseStockGameValues(boolean z) {
        this.e = z;
        this.h = true;
    }

    public final void setDisableAbilities(boolean z) {
        this.f = z;
        this.h = true;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        if (this.d.size <= 2) {
            return RarityType.COMMON;
        }
        if (this.d.size <= 4) {
            return RarityType.RARE;
        }
        if (this.d.size <= 6) {
            return RarityType.VERY_RARE;
        }
        if (this.d.size <= 8) {
            return RarityType.EPIC;
        }
        return RarityType.LEGENDARY;
    }

    @Override // com.prineside.tdi2.Tile
    public final float getWalkCost(boolean z) {
        return 1.0f;
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_BASES;
    }

    @Override // com.prineside.tdi2.Tile
    public final int generateSeedSalt() {
        int i = 0;
        for (int i2 = 0; i2 < this.d.size; i2++) {
            GameValueConfig gameValueConfig = this.d.get(i2);
            i = (((((((i * 21) + gameValueConfig.getType().ordinal()) * 23) + (gameValueConfig.isAllowBonuses() ? 0 : 1)) * 27) + (gameValueConfig.isOverwrite() ? 0 : 1)) * 31) + ((int) (gameValueConfig.getValue() * 100.0d));
        }
        if (this.e) {
            i += 1000000;
        }
        if (this.e) {
            i += 10000000;
        }
        return i;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        if (!super.sameAs(tile)) {
            return false;
        }
        TargetTile targetTile = (TargetTile) tile;
        if (targetTile.e != this.e || targetTile.f != this.f || targetTile.d.size != this.d.size) {
            return false;
        }
        for (int i = 0; i < this.d.size; i++) {
            GameValueConfig gameValueConfig = this.d.get(i);
            GameValueConfig gameValueConfig2 = targetTile.d.get(i);
            if (gameValueConfig.isOverwrite() != gameValueConfig2.isOverwrite() || gameValueConfig.getValue() != gameValueConfig2.getValue() || gameValueConfig.isAllowBonuses() != gameValueConfig2.isAllowBonuses() || gameValueConfig.getType() != gameValueConfig2.getType()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        TargetTile targetTile = (TargetTile) tile;
        this.d.clear();
        for (int i = 0; i < targetTile.d.size; i++) {
            this.d.add(targetTile.d.get(i).cpy());
        }
        this.e = targetTile.e;
        this.f = targetTile.f;
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.RARITY) {
            int ordinal = getRarity().ordinal() * 1000;
            if (this.e) {
                ordinal++;
            }
            if (this.f) {
                ordinal++;
            }
            return ordinal + this.d.size;
        }
        int i = 0;
        if (this.e) {
            i = 0 + 1;
        }
        if (this.f) {
            i++;
        }
        return i + this.d.size;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return true;
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        int i = 200;
        if (this.e) {
            i = 200 + 200;
        }
        if (this.f) {
            i += 200;
        }
        array.add(new ItemStack(Item.D.GREEN_PAPER, i + (this.d.size * 100)));
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        double d;
        double d2 = 0.05d + (this.d.size * 0.5d);
        if (this.d.size >= 8) {
            d = d2 * 1.2d;
        } else if (this.d.size >= 6) {
            d = d2 * 0.95d;
        } else if (this.d.size >= 4) {
            d = d2 * 0.65d;
        } else if (this.d.size >= 2) {
            d = d2 * 0.3d;
        } else {
            d = d2 * 0.1d;
        }
        if (this.e) {
            d += 1.5d;
        }
        if (this.f) {
            d += 0.8d;
        }
        return d;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        float f6 = f4 * 0.0078125f;
        float f7 = f5 * 0.0078125f;
        this.l -= f;
        if (this.l < 0.0f) {
            this.l = 0.0f;
        }
        c();
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        batch.draw(Game.i.tileManager.F.TARGET.f3171a, f2, f3, 128.0f * f6, 128.0f * f7);
        if (this.m) {
            return;
        }
        n.set(this.i);
        n.mul(1.0f + (this.l * 1.4f), 1.0f + (this.l * 1.9f), 1.0f + (this.l * 0.7f), 1.0f);
        batch.setColor(n);
        batch.draw(Game.i.tileManager.F.TARGET.f3172b, f2, f3, 128.0f * f6, 128.0f * f7);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.Tile
    public final void postDrawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        if (this.m) {
            return;
        }
        boolean isAdditiveBatch = RenderingManager.isAdditiveBatch(batch);
        RenderingManager.prepareBatch(batch, false);
        c();
        n.set(this.j);
        n.mul(1.0f + (this.l * 1.4f), 1.0f + (this.l * 1.9f), 1.0f + (this.l * 0.7f), 1.0f);
        batch.setColor(n);
        batch.draw(this.k, f2, f3, f4, f5);
        RenderingManager.setBatchAdditiveBlending(batch, isAdditiveBatch);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        c();
        float f2 = f / 128.0f;
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(Game.i.tileManager.getRoadTexture(null, null, null, null));
        image.setSize(f, f);
        group.addActor(image);
        Image image2 = new Image(new TextureRegionDrawable(Game.i.tileManager.F.TARGET.f3171a));
        image2.setSize(f, f);
        group.addActor(image2);
        Image image3 = new Image(new TextureRegionDrawable(Game.i.tileManager.F.TARGET.f3172b));
        image3.setSize(f, f);
        image3.setColor(this.i);
        group.addActor(image3);
        Image image4 = new Image(new TextureRegionDrawable(this.k));
        image4.setSize(f, f);
        image4.setColor(this.j);
        group.addActor(image4);
        for (int i = 0; i < this.d.size; i++) {
            Image image5 = new Image(Game.i.assetManager.getDrawable("small-circle-outline"));
            image5.setSize(10.0f * f2, 10.0f * f2);
            image5.setPosition((10.0f * f2) + (12.0f * f2 * (i / 7)), (10.0f * f2) + (12.0f * f2 * (i % 7)));
            group.addActor(image5);
        }
        if (isUseStockGameValues()) {
            Image image6 = new Image(Game.i.assetManager.getDrawable("small-circle-outline"));
            image6.setSize(16.0f * f2, 16.0f * f2);
            image6.setPosition(10.0f * f2, 102.0f * f2);
            image6.setColor(MaterialColor.RED.P500);
            group.addActor(image6);
        }
        if (this.f) {
            Image image7 = new Image(Game.i.assetManager.getDrawable("small-circle-outline"));
            image7.setSize(16.0f * f2, 16.0f * f2);
            image7.setPosition(28.0f * f2, 102.0f * f2);
            image7.setColor(MaterialColor.YELLOW.P500);
            group.addActor(image7);
        }
        if (isWalkableTiles()) {
            Image image8 = new Image(Game.i.assetManager.getDrawable("small-circle-outline"));
            image8.setSize(16.0f * f2, 16.0f * f2);
            image8.setPosition(46.0f * f2, 102.0f * f2);
            image8.setColor(MaterialColor.LIGHT_BLUE.P500);
            group.addActor(image8);
        }
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("da", Boolean.valueOf(this.f));
        json.writeValue("usgv", Boolean.valueOf(this.e));
        json.writeArrayStart("gv");
        for (int i = 0; i < this.d.size; i++) {
            json.writeObjectStart();
            this.d.get(i).toJson(json);
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        json.writeObjectEnd();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/TargetTile$TargetTileFactory.class */
    public static class TargetTileFactory extends Tile.Factory.AbstractFactory<TargetTile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f3171a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f3172b;
        ParticleEffectPool c;
        ParticleEffectPool d;

        public TargetTileFactory() {
            super(TileType.TARGET);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            int i = inventoryStatistics.byTileType[TileType.TARGET.ordinal()];
            if (i == 0) {
                return 1000;
            }
            if (i >= 500) {
                return 0;
            }
            return 30;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3171a = Game.i.assetManager.getTextureRegion("tile-type-target-hollow");
            this.f3172b = Game.i.assetManager.getTextureRegion("tile-type-target-base");
            this.c = Game.i.assetManager.getParticleEffectPool("base-explosion.prt");
            this.d = Game.i.assetManager.getParticleEffectPool("base-hit.prt");
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public TargetTile create() {
            return new TargetTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public TargetTile createRandom(float f, RandomXS128 randomXS128) {
            int nextInt;
            if (randomXS128 == null) {
                randomXS128 = FastRandom.random;
            }
            RandomXS128 randomXS1282 = randomXS128;
            TargetTile create = create();
            Array array = new Array();
            if (randomXS128.nextFloat() * f < 0.1f) {
                create.e = true;
            }
            if (randomXS128.nextFloat() * f < 0.1f) {
                create.f = true;
            }
            int i = 0;
            for (GameValueType gameValueType : GameValueType.minerCount) {
                if (Game.i.progressManager.isResourceOpened(ResourceType.values[i])) {
                    array.add(new RandomTileValue(10, () -> {
                        int round = MathUtils.round(f * 3.0f);
                        int i2 = round;
                        if (round == 0) {
                            i2 = 1;
                        }
                        create.d.add(new GameValueConfig(gameValueType, i2, false, true));
                    }));
                    i++;
                }
            }
            if (create.e && f > 0.2d) {
                array.add(new RandomTileValue(10, () -> {
                    int i2 = 1;
                    if (randomXS1282.nextFloat() * f > 0.5f) {
                        i2 = 2;
                    }
                    create.d.add(new GameValueConfig(GameValueType.TOWERS_MAX_UPGRADE_LEVEL, i2, false, true));
                }));
            }
            if (f >= 0.65f) {
                array.add(new RandomTileValue(5, () -> {
                    create.d.add(new GameValueConfig(GameValueType.ENEMIES_WALK_ON_PLATFORMS, 1.0d, false, true));
                }));
            }
            if (f >= 0.25f) {
                array.add(new RandomTileValue(10, () -> {
                    int round = MathUtils.round(((f - 0.25f) / 0.75f) * 5.0f) * 5;
                    int i2 = round;
                    if (round < 5) {
                        i2 = 5;
                    }
                    create.d.add(new GameValueConfig(GameValueType.MINERS_INSTALL_DURATION, -i2, false, true));
                }));
            }
            if (f >= 0.5f) {
                array.add(new RandomTileValue(10, () -> {
                    int round = MathUtils.round(((f - 0.5f) / 0.5f) * 10.0f) * 5;
                    int i2 = round;
                    if (round < 5) {
                        i2 = 5;
                    }
                    create.d.add(new GameValueConfig(GameValueType.TOWERS_EXPERIENCE_MULTIPLIER, i2, false, true));
                }));
            }
            if (f >= 0.25f) {
                array.add(new RandomTileValue(5, () -> {
                    double round = MathUtils.round(((f - 0.25f) / 0.75f) * 3.0f) / 10.0d;
                    double d = round;
                    if (round < 0.1d) {
                        d = 0.1d;
                    }
                    create.d.add(new GameValueConfig(GameValueType.TOWERS_EXPERIENCE_GENERATION, d, false, true));
                }));
            }
            if (f >= 0.5f) {
                array.add(new RandomTileValue(5, () -> {
                    int round = MathUtils.round(((f - 0.5f) / 0.5f) * 5.0f);
                    int i2 = round;
                    if (round <= 0) {
                        i2 = 1;
                    }
                    create.d.add(new GameValueConfig(GameValueType.TOWERS_UPGRADE_PRICE, -i2, false, true));
                }));
            }
            if (f >= 0.7f) {
                array.add(new RandomTileValue(3, () -> {
                    create.d.add(new GameValueConfig(GameValueType.TOWERS_SELL_REFUND, 99.0d, true, false));
                }));
            }
            if (f >= 0.25f) {
                array.add(new RandomTileValue(10, () -> {
                    if (randomXS1282.nextFloat() < 0.1f) {
                        create.d.add(new GameValueConfig(GameValueType.STARTING_HEALTH, 1.0d, true, false));
                        return;
                    }
                    int round = MathUtils.round(((f - 0.25f) / 0.75f) * 5.0f) << 1;
                    int i2 = round;
                    if (round < 2) {
                        i2 = 2;
                    }
                    create.d.add(new GameValueConfig(GameValueType.STARTING_HEALTH, i2, false, true));
                }));
            }
            array.add(new RandomTileValue(10, () -> {
                int round = MathUtils.round(f * 5.0f) * 10;
                int i2 = round;
                if (round < 10) {
                    i2 = 10;
                }
                create.d.add(new GameValueConfig(GameValueType.STARTING_MONEY, i2, false, true));
            }));
            for (GameValueType gameValueType2 : GameValueType.sharedTowerStats) {
                array.add(new RandomTileValue(3, () -> {
                    int round = MathUtils.round(randomXS1282.nextFloat() * f * 5.0f) * 10;
                    int i2 = round;
                    if (round < 10) {
                        i2 = 10;
                    }
                    create.d.add(new GameValueConfig(gameValueType2, i2, false, true));
                }));
            }
            int i2 = 0;
            for (int i3 = 0; i3 < array.size; i3++) {
                i2 += ((RandomTileValue) array.get(i3)).f3169a;
            }
            switch (ProgressManager.getRarityFromQuality(f)) {
                case COMMON:
                    nextInt = randomXS128.nextInt(3);
                    break;
                case RARE:
                    nextInt = 3 + randomXS128.nextInt(2);
                    break;
                case VERY_RARE:
                    nextInt = 5 + randomXS128.nextInt(2);
                    break;
                case EPIC:
                    nextInt = 7 + randomXS128.nextInt(2);
                    break;
                default:
                    nextInt = 9 + randomXS128.nextInt(2);
                    break;
            }
            for (int i4 = 0; i4 < nextInt; i4++) {
                int nextInt2 = randomXS128.nextInt(i2);
                int i5 = 0;
                int i6 = 0;
                while (true) {
                    if (i6 >= array.size) {
                        break;
                    }
                    if (i5 + ((RandomTileValue) array.get(i6)).f3169a >= nextInt2 && !((RandomTileValue) array.get(i6)).c) {
                        ((RandomTileValue) array.get(i6)).f3170b.run();
                        ((RandomTileValue) array.get(i6)).c = true;
                    } else {
                        i5 += ((RandomTileValue) array.get(i6)).f3169a;
                        i6++;
                    }
                }
            }
            Threads.sortGdxArray(create.d, (gameValueConfig, gameValueConfig2) -> {
                if (gameValueConfig.getType() == gameValueConfig2.getType()) {
                    return 0;
                }
                return gameValueConfig.getType().ordinal() > gameValueConfig2.getType().ordinal() ? -1 : 1;
            });
            return create;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public TargetTile fromJson(JsonValue jsonValue) {
            TargetTile targetTile = (TargetTile) super.fromJson(jsonValue);
            if (jsonValue.has("d")) {
                JsonValue jsonValue2 = jsonValue.get("d");
                JsonValue jsonValue3 = jsonValue2.get("gv");
                if (jsonValue3 != null) {
                    Iterator<JsonValue> iterator2 = jsonValue3.iterator2();
                    while (iterator2.hasNext()) {
                        try {
                            targetTile.d.add(GameValueConfig.fromJson(iterator2.next()));
                        } catch (Exception e) {
                            TargetTile.c.e("failed to load GV", e);
                        }
                    }
                }
                targetTile.d.sort(TargetTile.o);
                targetTile.f = jsonValue2.getBoolean("da", false);
                targetTile.e = jsonValue2.getBoolean("usgv", false);
            }
            return targetTile;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/TargetTile$RandomTileValue.class */
    public static class RandomTileValue {

        /* renamed from: a, reason: collision with root package name */
        int f3169a;

        /* renamed from: b, reason: collision with root package name */
        Runnable f3170b;
        boolean c;

        public RandomTileValue(int i, Runnable runnable) {
            this.f3169a = i;
            this.f3170b = runnable;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/TargetTile$ExplosionInterpolation.class */
    public static class ExplosionInterpolation extends Interpolation {
        private ExplosionInterpolation() {
        }

        /* synthetic */ ExplosionInterpolation(byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return (Interpolation.pow2In.apply(f) * 0.2f) + (Interpolation.pow5In.apply(f) * 0.3f);
        }
    }
}
