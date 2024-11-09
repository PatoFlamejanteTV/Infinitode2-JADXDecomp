package com.prineside.tdi2.managers;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.units.BallLightningUnit;
import com.prineside.tdi2.units.DisorientedUnit;
import com.prineside.tdi2.units.IceFieldUnit;
import com.prineside.tdi2.units.MicrogunUnit;
import com.prineside.tdi2.units.MineUnit;
import com.prineside.tdi2.units.SnowballUnit;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UnitManager.class */
public final class UnitManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Unit.Factory<? extends Unit>[] f2497a = new Unit.Factory[UnitType.values.length];
    public final Factories F = new Factories();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UnitManager$Factories.class */
    public static class Factories {
        public SnowballUnit.SnowballUnitFactory SNOWBALL;
        public BallLightningUnit.BallLightningUnitFactory BALL_LIGHTNING;
        public DisorientedUnit.DisorientedUnitFactory DISORIENTED;
        public MicrogunUnit.MicrogunUnitFactory MICROGUN;
        public MineUnit.MineUnitFactory MINE;
        public IceFieldUnit.IceFieldUnitFactory ICE_FIELD;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UnitManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<UnitManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public UnitManager read() {
            return Game.i.unitManager;
        }
    }

    public UnitManager() {
        Unit.Factory<? extends Unit>[] factoryArr = this.f2497a;
        int ordinal = UnitType.SNOWBALL.ordinal();
        Factories factories = this.F;
        SnowballUnit.SnowballUnitFactory snowballUnitFactory = new SnowballUnit.SnowballUnitFactory();
        factories.SNOWBALL = snowballUnitFactory;
        factoryArr[ordinal] = snowballUnitFactory;
        Unit.Factory<? extends Unit>[] factoryArr2 = this.f2497a;
        int ordinal2 = UnitType.BALL_LIGHTNING.ordinal();
        Factories factories2 = this.F;
        BallLightningUnit.BallLightningUnitFactory ballLightningUnitFactory = new BallLightningUnit.BallLightningUnitFactory();
        factories2.BALL_LIGHTNING = ballLightningUnitFactory;
        factoryArr2[ordinal2] = ballLightningUnitFactory;
        Unit.Factory<? extends Unit>[] factoryArr3 = this.f2497a;
        int ordinal3 = UnitType.DISORIENTED.ordinal();
        Factories factories3 = this.F;
        DisorientedUnit.DisorientedUnitFactory disorientedUnitFactory = new DisorientedUnit.DisorientedUnitFactory();
        factories3.DISORIENTED = disorientedUnitFactory;
        factoryArr3[ordinal3] = disorientedUnitFactory;
        Unit.Factory<? extends Unit>[] factoryArr4 = this.f2497a;
        int ordinal4 = UnitType.MICROGUN.ordinal();
        Factories factories4 = this.F;
        MicrogunUnit.MicrogunUnitFactory microgunUnitFactory = new MicrogunUnit.MicrogunUnitFactory();
        factories4.MICROGUN = microgunUnitFactory;
        factoryArr4[ordinal4] = microgunUnitFactory;
        Unit.Factory<? extends Unit>[] factoryArr5 = this.f2497a;
        int ordinal5 = UnitType.MINE.ordinal();
        Factories factories5 = this.F;
        MineUnit.MineUnitFactory mineUnitFactory = new MineUnit.MineUnitFactory();
        factories5.MINE = mineUnitFactory;
        factoryArr5[ordinal5] = mineUnitFactory;
        Unit.Factory<? extends Unit>[] factoryArr6 = this.f2497a;
        int ordinal6 = UnitType.ICE_FIELD.ordinal();
        Factories factories6 = this.F;
        IceFieldUnit.IceFieldUnitFactory iceFieldUnitFactory = new IceFieldUnit.IceFieldUnitFactory();
        factories6.ICE_FIELD = iceFieldUnitFactory;
        factoryArr6[ordinal6] = iceFieldUnitFactory;
        for (Unit.Factory<? extends Unit> factory : this.f2497a) {
            if (factory == null) {
                throw new RuntimeException("Not all unit factories were created");
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void setup() {
        for (Unit.Factory<? extends Unit> factory : this.f2497a) {
            factory.setup();
        }
    }

    public final Unit.Factory<? extends Unit> getFactory(UnitType unitType) {
        return this.f2497a[unitType.ordinal()];
    }
}
