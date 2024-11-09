package com.prineside.tdi2.gates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gates/TeleportGate.class */
public class TeleportGate extends Gate {
    public static final Color[] INDEX_COLORS = {MaterialColor.RED.P500, MaterialColor.PINK.P500, MaterialColor.PURPLE.P500, MaterialColor.DEEP_PURPLE.P500, MaterialColor.INDIGO.P500, MaterialColor.BLUE.P500, MaterialColor.LIGHT_BLUE.P500, MaterialColor.CYAN.P500, MaterialColor.TEAL.P500, MaterialColor.GREEN.P500, MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIME.P500, MaterialColor.YELLOW.P500, MaterialColor.AMBER.P500, MaterialColor.ORANGE.P500, MaterialColor.DEEP_ORANGE.P500, MaterialColor.BROWN.P500, MaterialColor.GREY.P500, MaterialColor.BLUE_GREY.P500, MaterialColor.RED.P800, MaterialColor.PINK.P800, MaterialColor.PURPLE.P800, MaterialColor.DEEP_PURPLE.P800, MaterialColor.INDIGO.P800, MaterialColor.BLUE.P800, MaterialColor.LIGHT_BLUE.P800, MaterialColor.CYAN.P800, MaterialColor.TEAL.P800, MaterialColor.GREEN.P800, MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIME.P800, MaterialColor.YELLOW.P800, MaterialColor.AMBER.P800, MaterialColor.ORANGE.P800, MaterialColor.DEEP_ORANGE.P800, MaterialColor.BROWN.P800, MaterialColor.GREY.P800, MaterialColor.BLUE_GREY.P800, MaterialColor.RED.P300, MaterialColor.PINK.P300, MaterialColor.PURPLE.P300, MaterialColor.DEEP_PURPLE.P300, MaterialColor.INDIGO.P300, MaterialColor.BLUE.P300, MaterialColor.LIGHT_BLUE.P300, MaterialColor.CYAN.P300, MaterialColor.TEAL.P300, MaterialColor.GREEN.P300, MaterialColor.LIGHT_GREEN.P300, MaterialColor.LIME.P300, MaterialColor.YELLOW.P300, MaterialColor.AMBER.P300, MaterialColor.ORANGE.P300, MaterialColor.DEEP_ORANGE.P300, MaterialColor.BROWN.P300, MaterialColor.GREY.P300, MaterialColor.BLUE_GREY.P300};
    public static final String[] INDEX_NAMES = {"RED", "PINK", "PURPLE", "DEEP_PURPLE", "INDIGO", "BLUE", "LIGHT_BLUE", "CYAN", "TEAL", "GREEN", "LIGHT_GREEN", "LIME", "YELLOW", "AMBER", "ORANGE", "DEEP_ORANGE", "BROWN", "GRAY", "BLUE_GRAY", "Dark RED", "Dark PINK", "Dark PURPLE", "Dark DEEP_PURPLE", "Dark INDIGO", "Dark BLUE", "Dark LIGHT_BLUE", "Dark CYAN", "Dark TEAL", "Dark GREEN", "Dark LIGHT_GREEN", "Dark LIME", "Dark YELLOW", "Dark AMBER", "Dark ORANGE", "Dark DEEP_ORANGE", "Dark BROWN", "Dark GRAY", "Dark BLUE_GRAY", "Bright RED", "Bright PINK", "Bright PURPLE", "Bright DEEP_PURPLE", "Bright INDIGO", "Bright BLUE", "Bright LIGHT_BLUE", "Bright CYAN", "Bright TEAL", "Bright GREEN", "Bright LIGHT_GREEN", "Bright LIME", "Bright YELLOW", "Bright AMBER", "Bright ORANGE", "Bright DEEP_ORANGE", "Bright BROWN", "Bright GRAY", "Bright BLUE_GRAY"};
    public static final int MAX_INDEX = INDEX_COLORS.length - 1;
    public static final int MAX_INDEX_FOR_LOOT = 16;
    public int index;

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private ParticleEffectPool.PooledEffect f2197a;

    /* synthetic */ TeleportGate(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Gate, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.index, true);
    }

    @Override // com.prineside.tdi2.Gate, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.index = input.readVarInt(true);
    }

    private TeleportGate() {
        super(GateType.TELEPORT);
    }

    @Override // com.prineside.tdi2.Gate
    public RarityType getRarity() {
        return RarityType.VERY_RARE;
    }

    @Override // com.prineside.tdi2.Gate
    public Gate cloneGate() {
        TeleportGate create = Game.i.gateManager.F.TELEPORT.create();
        create.setPosition(getX(), getY(), isLeftSide());
        create.index = this.index;
        return create;
    }

    @Override // com.prineside.tdi2.Gate
    public void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 100));
    }

    @Override // com.prineside.tdi2.Gate
    public void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        Label label = new Label(INDEX_NAMES[this.index] + " (" + this.index + ")", Game.i.assetManager.getLabelStyle(30));
        label.setWrap(true);
        label.setAlignment(1);
        table.add((Table) label).padBottom(4.0f).growX();
    }

    @Override // com.prineside.tdi2.Gate
    public double getPrestigeScore() {
        return 0.3d;
    }

    @Override // com.prineside.tdi2.Gate
    public int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.KIND) {
            return EventListeners.PRIORITY_HIGHEST + this.index;
        }
        return (getRarity().ordinal() * 1000) + this.index;
    }

    @Override // com.prineside.tdi2.Registrable
    public void setUnregistered() {
        if (this.f2197a != null) {
            this.f2197a.allowCompletion();
            this.f2197a = null;
        }
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Gate
    public Actor generateIcon(float f, boolean z) {
        Image image = new Image(Game.i.assetManager.getDrawable("item-gate-teleport-icon"));
        image.setSize(f, f);
        image.setColor(INDEX_COLORS[this.index]);
        return image;
    }

    @Override // com.prineside.tdi2.Gate
    public void drawStatic(Batch batch, float f, float f2, float f3, float f4) {
        float f5 = f3 * 0.0078125f;
        float f6 = f4 * 0.0078125f;
        batch.setColor(INDEX_COLORS[this.index]);
        if (isLeftSide()) {
            batch.draw(Game.i.gateManager.F.TELEPORT.f2198a, f - (14.0f * f5), f2, 28.0f * f5, 128.0f * f6);
        } else {
            batch.draw(Game.i.gateManager.F.TELEPORT.f2199b, f, f2 - (14.0f * f6), 128.0f * f5, 28.0f * f6);
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.Gate
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5) {
        float f6 = f4 * 0.0078125f;
        float f7 = f5 * 0.0078125f;
        if (this.S != null && Game.i.settingsManager.isParticlesDrawing()) {
            if (this.f2197a == null) {
                if (!isLeftSide()) {
                    this.f2197a = Game.i.gateManager.F.TELEPORT.c.obtain();
                    this.f2197a.setPosition(f2 + (64.0f * f6), f3);
                } else {
                    this.f2197a = Game.i.gateManager.F.TELEPORT.d.obtain();
                    this.f2197a.setPosition(f2, f3 + (64.0f * f7));
                }
                Array.ArrayIterator<ParticleEmitter> it = this.f2197a.getEmitters().iterator();
                while (it.hasNext()) {
                    it.next().getTint().setColors(new float[]{INDEX_COLORS[this.index].r, INDEX_COLORS[this.index].g, INDEX_COLORS[this.index].f888b});
                }
                this.S._particle.addParticle(this.f2197a, false);
                return;
            }
            return;
        }
        if (this.f2197a != null) {
            this.f2197a.allowCompletion();
            this.f2197a = null;
        }
    }

    @Override // com.prineside.tdi2.Gate
    public boolean sameAs(Gate gate) {
        return super.sameAs(gate) && ((TeleportGate) gate).index == this.index;
    }

    @Override // com.prineside.tdi2.Gate
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("index", Integer.valueOf(this.index));
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (" + this.index + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gates/TeleportGate$TeleportGateFactory.class */
    public static class TeleportGateFactory extends Gate.Factory.AbstractFactory<TeleportGate> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2198a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f2199b;
        ParticleEffectPool c;
        ParticleEffectPool d;

        public TeleportGateFactory() {
            super(GateType.TELEPORT);
        }

        @Override // com.prineside.tdi2.Gate.Factory.AbstractFactory
        public void setupAssets() {
            this.f2198a = Game.i.assetManager.getTextureRegion("gate-teleport-vertical");
            this.f2199b = Game.i.assetManager.getTextureRegion("gate-teleport-horizontal");
            this.c = Game.i.assetManager.getParticleEffectPool("teleport-horizontal.prt");
            this.d = Game.i.assetManager.getParticleEffectPool("teleport-vertical.prt");
        }

        @Override // com.prineside.tdi2.Gate.Factory
        public TeleportGate create() {
            return new TeleportGate((byte) 0);
        }

        @Override // com.prineside.tdi2.Gate.Factory
        public TeleportGate createRandom(float f, RandomXS128 randomXS128) {
            TeleportGate create = create();
            create.index = randomXS128.nextInt(17);
            return create;
        }

        @Override // com.prineside.tdi2.Gate.Factory.AbstractFactory, com.prineside.tdi2.Gate.Factory
        public TeleportGate fromJson(JsonValue jsonValue) {
            TeleportGate teleportGate = (TeleportGate) super.fromJson(jsonValue);
            teleportGate.index = jsonValue.getInt("index");
            return teleportGate;
        }
    }
}
