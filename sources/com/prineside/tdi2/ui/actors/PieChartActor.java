package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.scene2d.ui.Widget;
import com.prineside.tdi2.shapes.PieChart;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/PieChartActor.class */
public class PieChartActor extends Widget implements Disposable {
    private Array<PieChart.ChartEntryConfig> j;
    private float k;
    private float l;
    private float m = -1.0f;
    private int n = -1;
    public final PieChart chart = ((PieChart.PieChartFactory) Game.i.shapeManager.getFactory(ShapeType.PIE_CHART)).obtain();

    public void setConfigs(Array<PieChart.ChartEntryConfig> array) {
        this.j = array;
        this.m = -1.0f;
    }

    public Array<PieChart.ChartEntryConfig> getConfigs() {
        return this.j;
    }

    public void setSegmentCount(int i) {
        this.n = i;
        this.m = -1.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        super.draw(batch, f);
        if (this.j == null) {
            return;
        }
        float x = getX() + (getWidth() / 2.0f);
        float y = getY() + (getHeight() / 2.0f);
        float height = getWidth() > getHeight() ? getHeight() / 2.0f : getWidth() / 2.0f;
        if (x != this.k || y != this.l || height != this.m) {
            this.chart.setup(x, y, height, this.n == -1 ? (int) StrictMath.max(height * 2.0f, 8.0f) : this.n, this.j);
            this.k = x;
            this.l = y;
            this.m = height;
        }
        this.chart.draw(batch);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        ((PieChart.PieChartFactory) Game.i.shapeManager.getFactory(ShapeType.PIE_CHART)).free(this.chart);
    }
}
