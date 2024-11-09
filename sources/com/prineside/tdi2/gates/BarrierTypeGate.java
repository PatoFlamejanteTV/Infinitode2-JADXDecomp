package com.prineside.tdi2.gates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.GateBarrier;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.utils.REGS;
import java.util.Arrays;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gates/BarrierTypeGate.class */
public class BarrierTypeGate extends GateBarrier {

    /* renamed from: a, reason: collision with root package name */
    private boolean[] f2193a;

    /* renamed from: b, reason: collision with root package name */
    private static final Color f2194b = new Color();

    /* synthetic */ BarrierTypeGate(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Gate, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2193a);
    }

    @Override // com.prineside.tdi2.Gate, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2193a = (boolean[]) kryo.readObject(input, boolean[].class);
    }

    private BarrierTypeGate() {
        super(GateType.BARRIER_TYPE);
        this.f2193a = new boolean[EnemyType.values.length];
    }

    @Override // com.prineside.tdi2.Gate
    public RarityType getRarity() {
        return RarityType.RARE;
    }

    @Override // com.prineside.tdi2.Gate
    public void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        Label label = new Label(Game.i.localeManager.i18n.get("blocked_enemies").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table.add((Table) label).growX().padBottom(4.0f).row();
        Table table2 = new Table();
        table.add(table2).growX().row();
        int i = 0;
        for (EnemyType enemyType : EnemyType.values) {
            if (isEnemyBlocked(enemyType)) {
                Table table3 = new Table();
                int i2 = i;
                i++;
                mapEditorItemInfoMenu.listRowBg(i2, table3);
                table2.add(table3).growX().height(32.0f).row();
                table3.add((Table) new Image(Game.i.enemyManager.getFactory(enemyType).getTexture())).size(24.0f).padRight(12.0f);
                Enemy.Factory<? extends Enemy> factory = Game.i.enemyManager.getFactory(enemyType);
                Label label2 = new Label(factory.getTitle(), Game.i.assetManager.getLabelStyle(21));
                label2.setColor(factory.getColor());
                table3.add((Table) label2).growX();
            }
        }
    }

    @Override // com.prineside.tdi2.Gate
    public Gate cloneGate() {
        BarrierTypeGate create = Game.i.gateManager.F.BARRIER_TYPE.create();
        create.setPosition(getX(), getY(), isLeftSide());
        System.arraycopy(this.f2193a, 0, create.f2193a, 0, this.f2193a.length);
        return create;
    }

    public int getBlockedEnemyTypeCount() {
        int i = 0;
        for (int i2 = 0; i2 < EnemyType.values.length; i2++) {
            if (this.f2193a[i2]) {
                i++;
            }
        }
        return i;
    }

    @Override // com.prineside.tdi2.Gate
    public void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 100 + (getBlockedEnemyTypeCount() * 100)));
    }

    @Override // com.prineside.tdi2.Gate
    public double getPrestigeScore() {
        return 0.05d + (getBlockedEnemyTypeCount() * 0.02d);
    }

    @Override // com.prineside.tdi2.Gate
    public int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.KIND) {
            return 2000;
        }
        return (getRarity().ordinal() * 1000) + getBlockedEnemyTypeCount();
    }

    @Override // com.prineside.tdi2.GateBarrier
    public boolean canEnemyPass(EnemyType enemyType) {
        return !isEnemyBlocked(enemyType);
    }

    public boolean isEnemyBlocked(EnemyType enemyType) {
        return this.f2193a[enemyType.ordinal()];
    }

    public void setEnemyBlocked(EnemyType enemyType, boolean z) {
        this.f2193a[enemyType.ordinal()] = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Gate
    public Actor generateIcon(float f, boolean z) {
        float f2 = f / 128.0f;
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = new Image(Game.i.assetManager.getDrawable("item-gate-barrier-type-icon"));
        image.setSize(f, f);
        group.addActor(image);
        float f3 = 0.0f;
        float f4 = 0.0f;
        int i = 0;
        Array array = new Array(true, getBlockedEnemyTypeCount(), EnemyType.class);
        for (EnemyType enemyType : EnemyType.values) {
            if (isEnemyBlocked(enemyType)) {
                array.add(enemyType);
            }
        }
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            Image image2 = new Image(Game.i.enemyManager.getFactory((EnemyType) it.next()).getTexture());
            image2.setSize(26.0f * f2, 26.0f * f2);
            image2.setPosition(f3, f4);
            group.addActor(image2);
            f3 += 32.0f * f2;
            i++;
            if (i % 3 == 0) {
                f4 += 32.0f * f2;
                f3 = 0.0f;
            }
        }
        return group;
    }

    @Override // com.prineside.tdi2.Gate
    public void drawStatic(Batch batch, float f, float f2, float f3, float f4) {
        float f5 = f3 * 0.0078125f;
        float f6 = f4 * 0.0078125f;
        ResourcePack.AtlasTextureRegion atlasTextureRegion = AssetManager.TextureRegions.i().blank;
        if (isLeftSide()) {
            float blockedEnemyTypeCount = (94.0f / getBlockedEnemyTypeCount()) * f6;
            float f7 = 17.0f * f6;
            for (EnemyType enemyType : EnemyType.values) {
                if (isEnemyBlocked(enemyType)) {
                    f2194b.set(Game.i.enemyManager.getFactory(enemyType).getColor());
                    f2194b.f889a = 0.4f;
                    batch.setColor(f2194b);
                    batch.draw(atlasTextureRegion, f - (4.0f * f5), f2 + f7, 8.0f * f5, blockedEnemyTypeCount);
                    f7 += blockedEnemyTypeCount;
                }
            }
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            batch.draw(Game.i.gateManager.F.BARRIER_TYPE.f2195a, f - (14.0f * f5), f2, 28.0f * f5, 128.0f * f6);
            return;
        }
        float blockedEnemyTypeCount2 = (94.0f / getBlockedEnemyTypeCount()) * f5;
        float f8 = 17.0f * f5;
        for (EnemyType enemyType2 : EnemyType.values) {
            if (isEnemyBlocked(enemyType2)) {
                f2194b.set(Game.i.enemyManager.getFactory(enemyType2).getColor());
                f2194b.f889a = 0.4f;
                batch.setColor(f2194b);
                batch.draw(atlasTextureRegion, f + f8, f2 - (4.0f * f6), blockedEnemyTypeCount2, 8.0f * f6);
                f8 += blockedEnemyTypeCount2;
            }
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        batch.draw(Game.i.gateManager.F.BARRIER_TYPE.f2196b, f, f2 - (14.0f * f6), 128.0f * f5, 28.0f * f6);
    }

    @Override // com.prineside.tdi2.Gate
    public boolean sameAs(Gate gate) {
        if (!super.sameAs(gate)) {
            return false;
        }
        BarrierTypeGate barrierTypeGate = (BarrierTypeGate) gate;
        for (EnemyType enemyType : EnemyType.values) {
            if (barrierTypeGate.f2193a[enemyType.ordinal()] != this.f2193a[enemyType.ordinal()]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return super.toString() + " {blockedEnemies: " + Arrays.toString(this.f2193a) + "}";
    }

    @Override // com.prineside.tdi2.Gate
    public void toJson(Json json) {
        super.toJson(json);
        json.writeArrayStart("blockedEnemies");
        for (EnemyType enemyType : EnemyType.values) {
            if (isEnemyBlocked(enemyType)) {
                json.writeValue(enemyType.name());
            }
        }
        json.writeArrayEnd();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gates/BarrierTypeGate$BarrierTypeGateFactory.class */
    public static class BarrierTypeGateFactory extends Gate.Factory.AbstractFactory<BarrierTypeGate> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2195a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f2196b;

        public BarrierTypeGateFactory() {
            super(GateType.BARRIER_TYPE);
        }

        @Override // com.prineside.tdi2.Gate.Factory.AbstractFactory
        public void setupAssets() {
            this.f2195a = Game.i.assetManager.getTextureRegion("gate-barrier-type-vertical");
            this.f2196b = Game.i.assetManager.getTextureRegion("gate-barrier-type-horizontal");
        }

        @Override // com.prineside.tdi2.Gate.Factory
        public BarrierTypeGate create() {
            return new BarrierTypeGate((byte) 0);
        }

        @Override // com.prineside.tdi2.Gate.Factory
        public BarrierTypeGate createRandom(float f, RandomXS128 randomXS128) {
            BarrierTypeGate create = create();
            if (f <= 0.0f) {
                f = 0.001f;
            }
            while (f > 0.0f) {
                create.f2193a[EnemyType.mainEnemyTypes[randomXS128.nextInt(EnemyType.mainEnemyTypes.length)].ordinal()] = true;
                f -= 0.25f;
            }
            return create;
        }

        @Override // com.prineside.tdi2.Gate.Factory.AbstractFactory, com.prineside.tdi2.Gate.Factory
        public BarrierTypeGate fromJson(JsonValue jsonValue) {
            BarrierTypeGate barrierTypeGate = (BarrierTypeGate) super.fromJson(jsonValue);
            if (jsonValue.has("blockedEnemies")) {
                Iterator<JsonValue> iterator2 = jsonValue.get("blockedEnemies").iterator2();
                while (iterator2.hasNext()) {
                    barrierTypeGate.f2193a[EnemyType.valueOf(iterator2.next().asString()).ordinal()] = true;
                }
            }
            return barrierTypeGate;
        }
    }
}
