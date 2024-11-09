package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 2, classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Tile.class */
public abstract class Tile extends Registrable implements KryoSerializable {
    public TileType type;

    /* renamed from: a, reason: collision with root package name */
    protected int f1770a;

    /* renamed from: b, reason: collision with root package name */
    protected int f1771b;

    @NAGS
    public ParticleEffectPool.PooledEffect highlightParticleA;

    @NAGS
    public ParticleEffectPool.PooledEffect highlightParticleB;
    public int enemyCount;
    public int towerDisablingEnemyCount;
    private ObjectMap<String, Object> c;

    @NAGS
    public Vector2 center = new Vector2();

    @NAGS
    public final IntRectangle boundingBox = new IntRectangle();

    @NAGS
    public boolean visibleOnScreen = true;

    public abstract RarityType getRarity();

    public abstract ItemSubcategoryType getInventorySubcategory();

    public abstract int getSortingScore(ItemSortingType itemSortingType);

    public abstract boolean isRoadType();

    public abstract Group generateUiIcon(float f);

    public abstract double getPrestigeScore();

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.type);
        output.writeVarInt(this.f1770a, true);
        output.writeVarInt(this.f1771b, true);
        output.writeVarInt(this.enemyCount, true);
        output.writeVarInt(this.towerDisablingEnemyCount, true);
        kryo.writeClassAndObject(output, this.c);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.type = (TileType) kryo.readObject(input, TileType.class);
        this.f1770a = input.readVarInt(true);
        this.f1771b = input.readVarInt(true);
        this.enemyCount = input.readVarInt(true);
        this.towerDisablingEnemyCount = input.readVarInt(true);
        a();
        this.c = (ObjectMap) kryo.readClassAndObject(input);
    }

    public Tile(TileType tileType) {
        this.type = tileType;
    }

    public CharSequence getTitle() {
        return Game.i.tileManager.getFactory(this.type).getTitle();
    }

    public CharSequence getDescription() {
        return Game.i.tileManager.getFactory(this.type).getDescription();
    }

    public boolean canBeSelected() {
        return true;
    }

    public boolean affectedByLuckyWheelMultiplier() {
        return true;
    }

    @Null
    public Object getUserData(String str) {
        if (this.c != null) {
            return this.c.get(str, null);
        }
        return null;
    }

    public void setUserData(String str, @Null Object obj) {
        if (obj == null) {
            if (this.c != null) {
                this.c.remove(str);
                if (this.c.size == 0) {
                    this.c = null;
                    return;
                }
                return;
            }
            return;
        }
        if (this.c == null) {
            this.c = new ObjectMap<>();
        }
        this.c.put(str, obj);
    }

    public void getData(IntArray intArray) {
    }

    public void from(Tile tile) {
        Preconditions.checkNotNull(tile, "copyFrom can not be null");
        this.f1770a = tile.f1770a;
        this.f1771b = tile.f1771b;
    }

    public void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
    }

    public void fillInventoryWithInfo(Table table, float f) {
    }

    public float getWalkCost(boolean z) {
        return 1.3421773E8f;
    }

    public void drawSelectedRange(Batch batch, RangeCircle rangeCircle) {
    }

    public void drawHoveredRange(Batch batch, RangeCircle rangeCircle) {
    }

    public void updateCache() {
    }

    public Tile cloneTile() {
        Tile create = Game.i.tileManager.getFactory(this.type).create();
        create.from(this);
        return create;
    }

    public void setPos(int i, int i2) {
        this.f1770a = i;
        this.f1771b = i2;
        a();
    }

    public int getX() {
        return this.f1770a;
    }

    public int getY() {
        return this.f1771b;
    }

    private void a() {
        int i = this.f1771b << 7;
        int i2 = i + 128;
        int i3 = this.f1770a << 7;
        this.boundingBox.set(i3, i3 + 128, i, i2);
        this.center.set(i3 + 64, i + 64);
    }

    public int generateSeedSalt() {
        return 0;
    }

    public void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        if (isRoadType()) {
            drawRoadStatic(batch, f, f2, f3, f4, map);
        }
    }

    public void drawRoadStatic(Batch batch, float f, float f2, float f3, float f4, Map map) {
        Tile tile = null;
        Tile tile2 = null;
        Tile tile3 = null;
        Tile tile4 = null;
        if (map != null) {
            tile = map.getTile(this.f1770a - 1, this.f1771b);
            tile2 = map.getTile(this.f1770a + 1, this.f1771b);
            tile3 = map.getTile(this.f1770a, this.f1771b - 1);
            tile4 = map.getTile(this.f1770a, this.f1771b + 1);
        }
        batch.draw(Game.i.tileManager.getRoadTexture(tile, tile2, tile4, tile3), f, f2, f3, f4);
    }

    public void drawExtras(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
    }

    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
    }

    public void postDrawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(this.f1770a << 7, this.f1771b << 7, 128.0f, 128.0f);
    }

    public boolean sameAs(Tile tile) {
        return tile != null && this.type == tile.type;
    }

    public boolean sameAsWithExtras(Tile tile) {
        return sameAs(tile);
    }

    public Tile removeExtrasForInventory() {
        return this;
    }

    public float getValue() {
        return 1.0f;
    }

    public void addSellItems(Array<ItemStack> array) {
    }

    public boolean canBeSold() {
        return true;
    }

    public boolean canBeUpgraded() {
        return false;
    }

    public Tile createUpgradedTile() {
        throw new IllegalStateException("Not implemented");
    }

    public int getUpgradePriceInGreenPapers() {
        return 0;
    }

    public int getUpgradePriceInAccelerators() {
        return 1;
    }

    public int getUpgradePriceInResources(ResourceType resourceType) {
        return 0;
    }

    public void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
    }

    public void toJson(Json json) {
        json.writeValue("type", this.type.name());
        if (this.f1770a != 0) {
            json.writeValue("x", Integer.valueOf(this.f1770a));
        }
        if (this.f1771b != 0) {
            json.writeValue("y", Integer.valueOf(this.f1771b));
        }
    }

    public String toString() {
        return this.type.name() + " (" + this.f1770a + ":" + this.f1771b + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tile$Factory.class */
    public interface Factory<T extends Tile> extends Disposable, EntityFactory {
        void setup();

        String getTitle();

        String getDescription();

        int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics);

        T create();

        T createRandom(float f, RandomXS128 randomXS128);

        T fromJson(JsonValue jsonValue);

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tile$Factory$AbstractFactory.class */
        public static abstract class AbstractFactory<T extends Tile> implements Factory<T> {

            /* renamed from: a, reason: collision with root package name */
            private final String f1772a;

            /* renamed from: b, reason: collision with root package name */
            private final String f1773b;

            public AbstractFactory(TileType tileType) {
                this.f1772a = "tile_name_" + tileType.name();
                this.f1773b = "tile_description_" + tileType.name();
            }

            @Override // com.prineside.tdi2.Tile.Factory
            public String getTitle() {
                return Game.i.localeManager.i18n.get(this.f1772a);
            }

            @Override // com.prineside.tdi2.Tile.Factory
            public String getDescription() {
                return Game.i.localeManager.i18n.get(this.f1773b);
            }

            @Override // com.prineside.tdi2.Tile.Factory
            public T createRandom(float f, RandomXS128 randomXS128) {
                return create();
            }

            @Override // com.prineside.tdi2.Tile.Factory
            public void setup() {
                if (Game.i.assetManager != null) {
                    setupAssets();
                }
            }

            public void setupAssets() {
            }

            @Override // com.prineside.tdi2.Tile.Factory
            public T fromJson(JsonValue jsonValue) {
                T create = create();
                create.setPos(jsonValue.getInt("x", 0), jsonValue.getInt("y", 0));
                return create;
            }

            @Override // com.badlogic.gdx.utils.Disposable
            public void dispose() {
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tile$Pos.class */
    public static class Pos implements KryoSerializable, MapElementPos {

        /* renamed from: a, reason: collision with root package name */
        private int f1774a;

        /* renamed from: b, reason: collision with root package name */
        private int f1775b;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeVarInt(this.f1774a, true);
            output.writeVarInt(this.f1775b, true);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1774a = input.readVarInt(true);
            this.f1775b = input.readVarInt(true);
        }

        public Pos() {
        }

        public Pos(Pos pos) {
            this(pos.getX(), pos.getY());
        }

        public Pos(Tile tile) {
            this(tile.getX(), tile.getY());
        }

        public Pos(int i, int i2) {
            this.f1774a = i;
            this.f1775b = i2;
        }

        public void set(Pos pos) {
            Preconditions.checkNotNull(pos);
            this.f1774a = pos.getX();
            this.f1775b = pos.getY();
        }

        public int getX() {
            return this.f1774a;
        }

        public void setX(int i) {
            this.f1774a = i;
        }

        public int getY() {
            return this.f1775b;
        }

        public void setY(int i) {
            this.f1775b = i;
        }

        public boolean is(int i, int i2) {
            return i == this.f1774a && i2 == this.f1775b;
        }

        public String toString() {
            return super.toString() + " (" + this.f1774a + ":" + this.f1775b + ")";
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Pos)) {
                return false;
            }
            Pos pos = (Pos) obj;
            return pos.f1774a == this.f1774a && pos.f1775b == this.f1775b;
        }

        public int hashCode() {
            return ((961 + this.f1774a) * 31) + this.f1775b;
        }
    }
}
