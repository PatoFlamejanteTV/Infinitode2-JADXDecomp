package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Table;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/DraggingItemHelper.class */
public class DraggingItemHelper implements Disposable, Listener<Render> {

    /* renamed from: b, reason: collision with root package name */
    private final Group f3285b;
    private final Group c;
    private float g;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3284a = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 113, "DraggingItemHelper", true);
    private float d = 1.0f;
    private float e = 0.0f;
    private Vector2 f = new Vector2();

    public DraggingItemHelper(GameSystemProvider gameSystemProvider) {
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.disabled);
        this.f3284a.getTable().add((Table) group).expand().bottom().left().size(100.0f);
        this.f3285b = new Group();
        this.f3285b.setTransform(false);
        this.f3285b.setSize(1.0f, 1.0f);
        group.addActor(this.f3285b);
        this.c = new Group();
        this.c.setTransform(true);
        this.c.setSize(1.0f, 1.0f);
        this.c.setOrigin(0.5f, 0.5f);
        this.f3285b.addActor(this.c);
        this.c.setVisible(false);
        gameSystemProvider.events.getListeners(Render.class).add(this);
    }

    public Group show() {
        this.c.setVisible(true);
        this.c.clearChildren();
        this.c.setScale(this.e);
        return this.c;
    }

    public Group getIconContainer() {
        return this.c;
    }

    public void hide() {
        this.c.setVisible(false);
    }

    public void setPosition(float f, float f2) {
        this.c.setPosition(f, f2);
    }

    public void setScale(float f) {
        this.d = f;
    }

    public void setScaleInstantly(float f) {
        this.d = f;
        this.e = f;
    }

    public void setIconShift(float f, float f2) {
        this.f.set(f, f2);
    }

    public void setIconShiftInstantly(float f, float f2) {
        this.f.set(f, f2);
        this.f3285b.setPosition(f, f2);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3284a);
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(Render render) {
        this.g += render.getRealDeltaTime();
        if (this.g > 0.0166f) {
            this.g %= 0.0166f;
            if (this.f.x != this.f3285b.getX() || this.f.y != this.f3285b.getY()) {
                this.f3285b.setX(this.f3285b.getX() + ((this.f.x - this.f3285b.getX()) * 0.15f));
                this.f3285b.setY(this.f3285b.getY() + ((this.f.y - this.f3285b.getY()) * 0.15f));
                if (Math.abs(this.f.x - this.f3285b.getX()) + Math.abs(this.f.y - this.f3285b.getY()) < 0.5d) {
                    this.f3285b.setPosition(this.f.x, this.f.y);
                }
            }
            if (this.d != this.c.getScaleX()) {
                this.c.setScale(this.c.getScaleX() + ((this.d - this.c.getScaleX()) * 0.15f));
                if (Math.abs(this.c.getScaleX() - this.d) < 0.01d) {
                    this.c.setScale(this.d, this.d);
                }
            }
        }
    }
}
