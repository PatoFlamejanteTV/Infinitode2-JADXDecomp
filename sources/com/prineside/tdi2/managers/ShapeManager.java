package com.prineside.tdi2.managers;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Shape;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.shapes.BulletSmokeMultiLine;
import com.prineside.tdi2.shapes.ChainLightning;
import com.prineside.tdi2.shapes.Circle;
import com.prineside.tdi2.shapes.MultiLine;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.shapes.StraightMultiLine;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ShapeManager.class */
public class ShapeManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Shape.Factory[] f2463a = new Shape.Factory[ShapeType.values.length];
    public final Factories F = new Factories();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ShapeManager$Factories.class */
    public static class Factories {
        public MultiLine.MultiLineFactory MULTI_LINE;
        public TrailMultiLine.TrailMultiLineFactory TRAIL_MULTI_LINE;
        public Circle.CircleFactory CIRCLE;
        public RangeCircle.RangeCircleFactory RANGE_CIRCLE;
        public BulletSmokeMultiLine.BulletSmokeMultiLineFactory BULLET_SMOKE_MULTI_LINE;
        public PieChart.PieChartFactory PIE_CHART;
        public ChainLightning.ChainLightningFactory CHAIN_LIGHTNING;
        public StraightMultiLine.StraightMultiLineFactory STRAIGHT_MULTI_LINE;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ShapeManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ShapeManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ShapeManager read() {
            return Game.i.shapeManager;
        }
    }

    public ShapeManager() {
        Shape.Factory[] factoryArr = this.f2463a;
        int ordinal = ShapeType.MULTI_LINE.ordinal();
        Factories factories = this.F;
        MultiLine.MultiLineFactory multiLineFactory = new MultiLine.MultiLineFactory();
        factories.MULTI_LINE = multiLineFactory;
        factoryArr[ordinal] = multiLineFactory;
        Shape.Factory[] factoryArr2 = this.f2463a;
        int ordinal2 = ShapeType.TRAIL_MULTI_LINE.ordinal();
        Factories factories2 = this.F;
        TrailMultiLine.TrailMultiLineFactory trailMultiLineFactory = new TrailMultiLine.TrailMultiLineFactory();
        factories2.TRAIL_MULTI_LINE = trailMultiLineFactory;
        factoryArr2[ordinal2] = trailMultiLineFactory;
        Shape.Factory[] factoryArr3 = this.f2463a;
        int ordinal3 = ShapeType.CIRCLE.ordinal();
        Factories factories3 = this.F;
        Circle.CircleFactory circleFactory = new Circle.CircleFactory();
        factories3.CIRCLE = circleFactory;
        factoryArr3[ordinal3] = circleFactory;
        Shape.Factory[] factoryArr4 = this.f2463a;
        int ordinal4 = ShapeType.RANGE_CIRCLE.ordinal();
        Factories factories4 = this.F;
        RangeCircle.RangeCircleFactory rangeCircleFactory = new RangeCircle.RangeCircleFactory();
        factories4.RANGE_CIRCLE = rangeCircleFactory;
        factoryArr4[ordinal4] = rangeCircleFactory;
        Shape.Factory[] factoryArr5 = this.f2463a;
        int ordinal5 = ShapeType.BULLET_SMOKE_MULTI_LINE.ordinal();
        Factories factories5 = this.F;
        BulletSmokeMultiLine.BulletSmokeMultiLineFactory bulletSmokeMultiLineFactory = new BulletSmokeMultiLine.BulletSmokeMultiLineFactory();
        factories5.BULLET_SMOKE_MULTI_LINE = bulletSmokeMultiLineFactory;
        factoryArr5[ordinal5] = bulletSmokeMultiLineFactory;
        Shape.Factory[] factoryArr6 = this.f2463a;
        int ordinal6 = ShapeType.PIE_CHART.ordinal();
        Factories factories6 = this.F;
        PieChart.PieChartFactory pieChartFactory = new PieChart.PieChartFactory();
        factories6.PIE_CHART = pieChartFactory;
        factoryArr6[ordinal6] = pieChartFactory;
        Shape.Factory[] factoryArr7 = this.f2463a;
        int ordinal7 = ShapeType.CHAIN_LIGHTNING.ordinal();
        Factories factories7 = this.F;
        ChainLightning.ChainLightningFactory chainLightningFactory = new ChainLightning.ChainLightningFactory();
        factories7.CHAIN_LIGHTNING = chainLightningFactory;
        factoryArr7[ordinal7] = chainLightningFactory;
        Shape.Factory[] factoryArr8 = this.f2463a;
        int ordinal8 = ShapeType.STRAIGHT_MULTI_LINE.ordinal();
        Factories factories8 = this.F;
        StraightMultiLine.StraightMultiLineFactory straightMultiLineFactory = new StraightMultiLine.StraightMultiLineFactory();
        factories8.STRAIGHT_MULTI_LINE = straightMultiLineFactory;
        factoryArr8[ordinal8] = straightMultiLineFactory;
        for (ShapeType shapeType : ShapeType.values) {
            if (this.f2463a[shapeType.ordinal()] == null) {
                throw new RuntimeException("Not all shape factories were created");
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        for (Shape.Factory factory : this.f2463a) {
            factory.setup();
        }
    }

    public Shape.Factory<? extends Shape> getFactory(ShapeType shapeType) {
        return this.f2463a[shapeType.ordinal()];
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
