package com.prineside.tdi2.managers;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.gates.BarrierHealthGate;
import com.prineside.tdi2.gates.BarrierTypeGate;
import com.prineside.tdi2.gates.TeleportGate;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GateManager.class */
public class GateManager extends Manager.ManagerAdapter {
    public final Factories F = new Factories();

    /* renamed from: a, reason: collision with root package name */
    private final Gate.Factory[] f2345a = new Gate.Factory[GateType.values.length];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GateManager$Factories.class */
    public static class Factories {
        public BarrierTypeGate.BarrierTypeGateFactory BARRIER_TYPE;
        public BarrierHealthGate.BarrierHealthGateFactory BARRIER_HEALTH;
        public TeleportGate.TeleportGateFactory TELEPORT;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GateManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<GateManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public GateManager read() {
            return Game.i.gateManager;
        }
    }

    public GateManager() {
        Gate.Factory[] factoryArr = this.f2345a;
        int ordinal = GateType.BARRIER_TYPE.ordinal();
        Factories factories = this.F;
        BarrierTypeGate.BarrierTypeGateFactory barrierTypeGateFactory = new BarrierTypeGate.BarrierTypeGateFactory();
        factories.BARRIER_TYPE = barrierTypeGateFactory;
        factoryArr[ordinal] = barrierTypeGateFactory;
        Gate.Factory[] factoryArr2 = this.f2345a;
        int ordinal2 = GateType.BARRIER_HEALTH.ordinal();
        Factories factories2 = this.F;
        BarrierHealthGate.BarrierHealthGateFactory barrierHealthGateFactory = new BarrierHealthGate.BarrierHealthGateFactory();
        factories2.BARRIER_HEALTH = barrierHealthGateFactory;
        factoryArr2[ordinal2] = barrierHealthGateFactory;
        Gate.Factory[] factoryArr3 = this.f2345a;
        int ordinal3 = GateType.TELEPORT.ordinal();
        Factories factories3 = this.F;
        TeleportGate.TeleportGateFactory teleportGateFactory = new TeleportGate.TeleportGateFactory();
        factories3.TELEPORT = teleportGateFactory;
        factoryArr3[ordinal3] = teleportGateFactory;
        for (GateType gateType : GateType.values) {
            if (this.f2345a[gateType.ordinal()] == null) {
                throw new RuntimeException("Not all gate factories were created");
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        for (Gate.Factory factory : this.f2345a) {
            factory.setup();
        }
    }

    public Gate createRandomGate(GateType gateType, float f, RandomXS128 randomXS128) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        return getFactory(gateType).createRandom(f, randomXS128);
    }

    public Gate.Factory<? extends Gate> getFactory(GateType gateType) {
        return this.f2345a[gateType.ordinal()];
    }

    public Gate createGateFromJson(JsonValue jsonValue) {
        if (!jsonValue.isObject()) {
            throw new IllegalArgumentException("JsonValue must be an object");
        }
        return getFactory(GateType.valueOf(jsonValue.getString("type"))).fromJson(jsonValue);
    }
}
