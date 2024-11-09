package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ImageWithParentColor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Modifier.class */
public abstract class Modifier extends Building {
    public static final float PENALTY_SELL_PRICE = 0.75f;
    public static final float[][] WIRES_TEXTURES_CONFIG;
    public int id;
    public ModifierType type;
    public float timeSinceBuilt;
    public boolean[] visuallyConnectedSides;
    public int moneySpentOn;

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Modifier$ConnectionSide.class */
    public enum ConnectionSide {
        BOTTOM,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM_LEFT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT;

        public static final ConnectionSide[] values = values();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [float[], float[][]] */
    static {
        TLog.forClass(Modifier.class);
        WIRES_TEXTURES_CONFIG = new float[]{new float[]{-31.0f, -72.0f, 62.0f, 54.0f}, new float[]{-72.0f, -31.0f, 54.0f, 62.0f}, new float[]{-31.0f, 18.0f, 62.0f, 54.0f}, new float[]{18.0f, -31.0f, 54.0f, 62.0f}, new float[]{-72.0f, -72.0f, 54.0f, 54.0f}, new float[]{-72.0f, 18.0f, 54.0f, 54.0f}, new float[]{18.0f, 18.0f, 54.0f, 54.0f}, new float[]{18.0f, -72.0f, 54.0f, 54.0f}};
    }

    @Override // com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.id, true);
        kryo.writeObjectOrNull(output, this.type, ModifierType.class);
        output.writeFloat(this.timeSinceBuilt);
        kryo.writeObject(output, this.visuallyConnectedSides);
        output.writeVarInt(this.moneySpentOn, true);
    }

    @Override // com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.id = input.readVarInt(true);
        this.type = (ModifierType) kryo.readObjectOrNull(input, ModifierType.class);
        this.timeSinceBuilt = input.readFloat();
        this.visuallyConnectedSides = (boolean[]) kryo.readObject(input, boolean[].class);
        this.moneySpentOn = input.readVarInt(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Modifier(ModifierType modifierType) {
        super(BuildingType.MODIFIER);
        this.visuallyConnectedSides = new boolean[ConnectionSide.values.length];
        this.moneySpentOn = 0;
        this.type = modifierType;
    }

    @Override // com.prineside.tdi2.Building
    public float getWalkCost() {
        return 512.0f;
    }

    public void setSideConnected(ConnectionSide connectionSide, boolean z) {
        this.visuallyConnectedSides[connectionSide.ordinal()] = z;
    }

    public boolean connectsToTowers() {
        return true;
    }

    public boolean connectsToMiners() {
        return true;
    }

    public float getSellDelay() {
        return 300.0f;
    }

    public float getTimeTillSellAvailable() {
        return StrictMath.max(0.0f, getSellDelay() - this.timeSinceBuilt);
    }

    public boolean isSellAvailable() {
        return getTimeTillSellAvailable() == 0.0f;
    }

    public int getSellPrice() {
        return this.moneySpentOn;
    }

    @Override // com.prineside.tdi2.Building
    public void updateCache() {
        Arrays.fill(this.visuallyConnectedSides, false);
        this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
            if ((tile instanceof PlatformTile) && connectsToTowers()) {
                if (((PlatformTile) tile).building instanceof Tower) {
                    this.visuallyConnectedSides[a(tile.getX(), tile.getY()).ordinal()] = true;
                    return true;
                }
                return true;
            }
            if ((tile instanceof SourceTile) && connectsToMiners() && ((SourceTile) tile).miner != null) {
                this.visuallyConnectedSides[a(tile.getX(), tile.getY()).ordinal()] = true;
                return true;
            }
            return true;
        });
    }

    public void fillModifierMenu(Group group, ObjectMap<String, Object> objectMap) {
    }

    public boolean hasCustomButton() {
        return false;
    }

    public boolean isCustomButtonNeedMapPoint() {
        return false;
    }

    public void customButtonAction(int i, int i2) {
    }

    public void updateCustomButton(ComplexButton complexButton, boolean z) {
    }

    @Override // com.prineside.tdi2.Building
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("type", this.type.name());
    }

    private void a(Batch batch) {
        for (ConnectionSide connectionSide : ConnectionSide.values) {
            if (this.visuallyConnectedSides[connectionSide.ordinal()]) {
                float[] fArr = WIRES_TEXTURES_CONFIG[connectionSide.ordinal()];
                batch.draw(Game.i.modifierManager.getFactory(this.type).wires[connectionSide.ordinal()], getTile().center.x + fArr[0], getTile().center.y + fArr[1], fArr[2], fArr[3]);
            }
        }
    }

    public void drawBatch(Batch batch, float f, MapRenderingSystem.DrawMode drawMode) {
        if (getTile().visibleOnScreen) {
            Factory<? extends Modifier> factory = Game.i.modifierManager.getFactory(this.type);
            batch.setColor(factory.color);
            a(batch);
            if (drawMode == MapRenderingSystem.DrawMode.DETAILED) {
                batch.setColor(0.8f, 0.8f, 0.8f, 1.0f);
            } else {
                batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            }
            batch.draw(factory.getBaseTexture(), getTile().center.x - 64.0f, getTile().center.y - 64.0f, 128.0f, 128.0f);
            if (drawMode == MapRenderingSystem.DrawMode.DETAILED) {
                batch.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                batch.draw(factory.f1744a, (getTile().getX() << 7) + 42.24f, (getTile().getY() << 7) + 42.24f, 42.24f, 42.24f);
            } else {
                batch.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                batch.draw(factory.f1744a, (getTile().getX() << 7) + 39.68f, (getTile().getY() << 7) + 44.8f, 42.24f, 42.24f);
                batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
                batch.draw(factory.f1744a, (getTile().getX() << 7) + 42.24f, (getTile().getY() << 7) + 42.24f, 42.24f, 42.24f);
            }
            if (!isSellAvailable()) {
                batch.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                batch.draw(factory.f1745b, (getTile().center.x - 64.0f) + 10.0f + 2.0f, ((getTile().center.y - 64.0f) + 10.0f) - 2.0f, 18.285715f, 18.285715f);
                batch.setColor(MaterialColor.ORANGE.P500);
                batch.draw(factory.f1745b, (getTile().center.x - 64.0f) + 10.0f, (getTile().center.y - 64.0f) + 10.0f, 18.285715f, 18.285715f);
                batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            }
        }
    }

    public void drawBatchAdditive(Batch batch, float f, MapRenderingSystem.DrawMode drawMode) {
    }

    @Override // com.prineside.tdi2.Building
    public boolean sameAs(Building building) {
        return super.sameAs(building) && ((Modifier) building).type == this.type;
    }

    @Override // com.prineside.tdi2.Building
    public Modifier cloneBuilding() {
        return Game.i.modifierManager.getFactory(this.type).create();
    }

    public void update(float f) {
        if (this.S.gameState.isGameRealTimePasses()) {
            this.timeSinceBuilt += f;
        }
    }

    public void dispose() {
    }

    private ConnectionSide a(int i, int i2) {
        int x = getTile().getX();
        int y = getTile().getY();
        if (i == x) {
            if (i2 + 1 == y) {
                return ConnectionSide.BOTTOM;
            }
            if (i2 - 1 == y) {
                return ConnectionSide.TOP;
            }
            return null;
        }
        if (i2 == y) {
            if (i + 1 == x) {
                return ConnectionSide.LEFT;
            }
            if (i - 1 == x) {
                return ConnectionSide.RIGHT;
            }
            return null;
        }
        if (i + 1 == x) {
            if (i2 + 1 == y) {
                return ConnectionSide.BOTTOM_LEFT;
            }
            if (i2 - 1 == y) {
                return ConnectionSide.TOP_LEFT;
            }
            return null;
        }
        if (i2 + 1 == y) {
            return ConnectionSide.BOTTOM_RIGHT;
        }
        if (i2 - 1 == y) {
            return ConnectionSide.TOP_RIGHT;
        }
        return null;
    }

    public void loadFromJson(JsonValue jsonValue) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Modifier$Factory.class */
    public static abstract class Factory<T extends Modifier> implements EntityFactory {
        public final Color color;
        public final String iconName;
        public final ModifierType modifierType;
        public TextureRegion[] wires = new TextureRegion[ConnectionSide.values.length];

        /* renamed from: a, reason: collision with root package name */
        protected TextureRegion f1744a;

        /* renamed from: b, reason: collision with root package name */
        protected TextureRegion f1745b;

        public abstract T create();

        public abstract TextureRegion getBaseTexture();

        public abstract int getBuildPrice(GameSystemProvider gameSystemProvider, int i);

        /* JADX INFO: Access modifiers changed from: protected */
        public Factory(ModifierType modifierType, Color color, String str) {
            this.color = color;
            this.iconName = str;
            this.modifierType = modifierType;
            if (Game.i.assetManager != null) {
                this.f1744a = Game.i.assetManager.getTextureRegion(str);
                this.f1745b = Game.i.assetManager.getTextureRegion("icon-dollar");
                this.wires[ConnectionSide.LEFT.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-left");
                this.wires[ConnectionSide.RIGHT.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-right");
                this.wires[ConnectionSide.TOP.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-top");
                this.wires[ConnectionSide.BOTTOM.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-bottom");
                this.wires[ConnectionSide.TOP_LEFT.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-top-left");
                this.wires[ConnectionSide.TOP_RIGHT.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-top-right");
                this.wires[ConnectionSide.BOTTOM_LEFT.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-bottom-left");
                this.wires[ConnectionSide.BOTTOM_RIGHT.ordinal()] = Game.i.assetManager.getTextureRegion("modifier-wires-bottom-right");
            }
        }

        public void setup() {
            if (Game.i.assetManager != null) {
                setupAssets();
            }
        }

        public ModifierProcessor createProcessor() {
            return null;
        }

        public boolean canBePlacedNear(ModifierType modifierType, GameValueProvider gameValueProvider) {
            return true;
        }

        public void setupAssets() {
        }

        public CharSequence getTitle() {
            return Game.i.localeManager.i18n.get(Game.i.modifierManager.getTitleAlias(this.modifierType));
        }

        public CharSequence getFancyTitle() {
            return Game.i.localeManager.i18n.get(Game.i.modifierManager.getTitleFancyAlias(this.modifierType));
        }

        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.get(Game.i.modifierManager.getDescriptionAlias(this.modifierType));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static int a(int i) {
            if (i < 500) {
                return MathUtils.round(i / 5.0f) * 5;
            }
            if (i < 5000) {
                return MathUtils.round(i / 10.0f) * 10;
            }
            return MathUtils.round(i / 50.0f) * 50;
        }

        public boolean isAvailable(GameValueProvider gameValueProvider) {
            return gameValueProvider.getIntValue(Game.i.modifierManager.getCountGameValue(this.modifierType)) != 0;
        }

        public Actor createIconActor(float f) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            ImageWithParentColor imageWithParentColor = new ImageWithParentColor(Game.i.assetManager.getDrawable("modifier-icon-wires"));
            imageWithParentColor.setColor(this.color);
            imageWithParentColor.setSize(f, f);
            group.addActor(imageWithParentColor);
            ImageWithParentColor imageWithParentColor2 = new ImageWithParentColor(getBaseTexture());
            imageWithParentColor2.setSize(f, f);
            group.addActor(imageWithParentColor2);
            Image image = new Image(Game.i.assetManager.getDrawable(this.iconName));
            image.setSize(f * 0.33f, f * 0.33f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(0.31f * f, 0.35f * f);
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable(this.iconName));
            image2.setSize(f * 0.33f, f * 0.33f);
            image2.setPosition(0.33f * f, 0.33f * f);
            group.addActor(image2);
            return group;
        }
    }
}
