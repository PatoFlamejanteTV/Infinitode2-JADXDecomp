package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.shapes.PieChart;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ButtonHoldHint.class */
public class ButtonHoldHint extends PieChartActor {
    public boolean disappearing = false;
    private float j = 0.0f;
    private float k = 0.0f;
    private final PieChart.ChartEntryConfig l;
    private final PieChart.ChartEntryConfig m;
    private final float n;

    public ButtonHoldHint(float f, float f2, float f3) {
        this.n = f3;
        Array<PieChart.ChartEntryConfig> array = new Array<>();
        this.l = new PieChart.ChartEntryConfig(Color.WHITE.cpy(), 0.0f, 0.0f);
        this.m = new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 1.0f), 1.0f, 0.0f);
        array.add(this.l);
        array.add(this.m);
        this.chart.innerRadius = Gdx.app.getType() == Application.ApplicationType.Desktop ? 12.0f : 18.0f;
        this.chart.rotationDirection = -1.0f;
        this.chart.angleShiftRad = 0.7853982f;
        float f4 = Gdx.app.getType() == Application.ApplicationType.Desktop ? 48.0f : 72.0f;
        setSize(f4, f4);
        setPosition(f - (f4 * 0.5f), (f2 - (f4 * 0.5f)) + (Gdx.app.getType() == Application.ApplicationType.Desktop ? 0.0f : 160.0f));
        setConfigs(array);
    }

    @Override // com.prineside.tdi2.ui.actors.PieChartActor, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        if (!this.disappearing) {
            this.j += Gdx.graphics.getDeltaTime();
            float f2 = 0.1f;
            if (this.n < 0.2f) {
                f2 = 0.0f;
            } else if (this.n < 0.5f) {
                f2 = 0.05f;
            }
            float f3 = (this.j - f2) / (this.n - f2);
            float f4 = f3;
            if (f3 > 1.0f) {
                f4 = 1.0f;
            }
            if (f4 < 0.0f) {
                f4 = 0.0f;
            }
            this.l.color.f889a = f4 * 0.25f;
            this.l.setValue(f4);
            this.m.setValue(1.0f - f4);
            this.m.color.f889a = 0.14f * f4;
        } else {
            if (this.k <= -1.0f) {
                return;
            }
            this.k += Gdx.graphics.getDeltaTime();
            this.l.color.f889a = 1.0f;
            this.l.setValue(1.0f);
            this.m.setValue(0.0f);
            if (this.k <= 0.2f) {
                float f5 = this.k / 0.2f;
                float f6 = Gdx.app.getType() == Application.ApplicationType.Desktop ? 12.0f : 18.0f;
                this.chart.innerRadius = f6 + (f6 * f5);
            } else {
                this.k = -2.0f;
                Threads.i().postRunnable(this::remove);
                return;
            }
        }
        this.chart.requestVerticesRebuild();
        super.draw(batch, f);
    }
}
