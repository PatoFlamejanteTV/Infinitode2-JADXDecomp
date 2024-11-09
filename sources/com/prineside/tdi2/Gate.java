package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 3, classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Gate.class */
public abstract class Gate extends Registrable {
    public static final float THICKNESS = 28.0f;

    /* renamed from: a, reason: collision with root package name */
    private GateType f1714a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1715b;
    private int c;
    private int d;

    @NAGS
    public ParticleEffectPool.PooledEffect highlightParticleA;

    @NAGS
    public ParticleEffectPool.PooledEffect highlightParticleB;

    public abstract RarityType getRarity();

    public abstract Gate cloneGate();

    public abstract double getPrestigeScore();

    public abstract int getSortingScore(ItemSortingType itemSortingType);

    public abstract Actor generateIcon(float f, boolean z);

    public abstract void drawStatic(Batch batch, float f, float f2, float f3, float f4);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.f1714a, GateType.class);
        output.writeBoolean(this.f1715b);
        output.writeVarInt(this.c, true);
        output.writeVarInt(this.d, true);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1714a = (GateType) kryo.readObjectOrNull(input, GateType.class);
        this.f1715b = input.readBoolean();
        this.c = input.readVarInt(true);
        this.d = input.readVarInt(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Gate(GateType gateType) {
        this.f1714a = gateType;
    }

    public GateType getType() {
        return this.f1714a;
    }

    public void addSellItems(Array<ItemStack> array) {
    }

    public void fillMapEditorMenu(Table table, MapEditorItemInfoMenu mapEditorItemInfoMenu) {
    }

    public Rectangle getBoundingBox() {
        if (this.f1715b) {
            return new Rectangle((this.c << 7) - 14.0f, this.d << 7, 28.0f, 128.0f);
        }
        return new Rectangle(this.c << 7, (this.d << 7) - 14.0f, 128.0f, 28.0f);
    }

    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5) {
    }

    public boolean sameAs(Gate gate) {
        return gate != null && gate.getType() == getType();
    }

    public int getX() {
        return this.c;
    }

    public int getY() {
        return this.d;
    }

    public boolean isLeftSide() {
        return this.f1715b;
    }

    public void setPosition(int i, int i2, boolean z) {
        this.c = i;
        this.d = i2;
        this.f1715b = z;
    }

    public void toJson(Json json) {
        json.writeValue("type", this.f1714a.name());
        if (this.c != 0) {
            json.writeValue("x", Integer.valueOf(this.c));
        }
        if (this.d != 0) {
            json.writeValue("y", Integer.valueOf(this.d));
        }
        if (this.f1715b) {
            json.writeValue("side", "LEFT");
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Gate$Factory.class */
    public interface Factory<T extends Gate> extends Disposable, EntityFactory {
        void setup();

        CharSequence getTitle(Gate gate);

        CharSequence getDescription(Gate gate);

        T create();

        T createRandom(float f, RandomXS128 randomXS128);

        T fromJson(JsonValue jsonValue);

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/Gate$Factory$AbstractFactory.class */
        public static abstract class AbstractFactory<T extends Gate> implements Factory<T> {

            /* renamed from: a, reason: collision with root package name */
            private final String f1716a;

            /* renamed from: b, reason: collision with root package name */
            private final String f1717b;

            public AbstractFactory(GateType gateType) {
                this.f1716a = "gate_name_" + gateType.name();
                this.f1717b = "gate_description_" + gateType.name();
            }

            @Override // com.prineside.tdi2.Gate.Factory
            public CharSequence getTitle(Gate gate) {
                return Game.i.localeManager.i18n.get(this.f1716a);
            }

            @Override // com.prineside.tdi2.Gate.Factory
            public CharSequence getDescription(Gate gate) {
                return Game.i.localeManager.i18n.get(this.f1717b);
            }

            @Override // com.prineside.tdi2.Gate.Factory
            public void setup() {
                if (Game.i.assetManager != null) {
                    setupAssets();
                }
            }

            public void setupAssets() {
            }

            @Override // com.prineside.tdi2.Gate.Factory
            public T fromJson(JsonValue jsonValue) {
                T create = create();
                int i = jsonValue.getInt("x", 0);
                int i2 = jsonValue.getInt("y", 0);
                boolean z = false;
                if (jsonValue.get("side") != null) {
                    z = "LEFT".equals(jsonValue.getString("side"));
                }
                create.setPosition(i, i2, z);
                return create;
            }

            @Override // com.badlogic.gdx.utils.Disposable
            public void dispose() {
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Gate$Pos.class */
    public static class Pos implements KryoSerializable, MapElementPos {

        /* renamed from: a, reason: collision with root package name */
        private int f1718a;

        /* renamed from: b, reason: collision with root package name */
        private int f1719b;
        private boolean c;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeVarInt(this.f1718a, true);
            output.writeVarInt(this.f1719b, true);
            output.writeBoolean(this.c);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1718a = input.readVarInt(true);
            this.f1719b = input.readVarInt(true);
            this.c = input.readBoolean();
        }

        public Pos() {
        }

        public Pos(Pos pos) {
            this(pos.getX(), pos.getY(), pos.c);
        }

        public Pos(Gate gate) {
            this(gate.getX(), gate.getY(), gate.isLeftSide());
        }

        public Pos(int i, int i2, boolean z) {
            this.f1718a = i;
            this.f1719b = i2;
            this.c = z;
        }

        public void set(Pos pos) {
            Preconditions.checkNotNull(pos);
            this.f1718a = pos.getX();
            this.f1719b = pos.getY();
            this.c = pos.isLeft();
        }

        public int getX() {
            return this.f1718a;
        }

        public void setX(int i) {
            this.f1718a = i;
        }

        public int getY() {
            return this.f1719b;
        }

        public void setY(int i) {
            this.f1719b = i;
        }

        public boolean isLeft() {
            return this.c;
        }

        public void setLeft(boolean z) {
            this.c = z;
        }

        public boolean is(int i, int i2, boolean z) {
            return i == this.f1718a && i2 == this.f1719b && this.c == z;
        }

        public String toString() {
            return super.toString() + " (" + this.f1718a + ":" + this.f1719b + ":" + this.c + ")";
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Pos)) {
                return false;
            }
            Pos pos = (Pos) obj;
            return pos.f1718a == this.f1718a && pos.f1719b == this.f1719b && pos.c == this.c;
        }

        public int hashCode() {
            return ((((961 + this.f1718a) * 31) + this.f1719b) * 31) + (this.c ? 1 : 2);
        }
    }
}
