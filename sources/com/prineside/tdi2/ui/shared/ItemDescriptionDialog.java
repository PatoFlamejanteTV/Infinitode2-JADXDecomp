package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ItemDescriptionDialog.class */
public class ItemDescriptionDialog extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3603a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 203, "ItemDescriptionDialog main");

    /* renamed from: b, reason: collision with root package name */
    private Group f3604b = new Group();
    private ItemCell c;
    private Table d;

    public static ItemDescriptionDialog i() {
        return (ItemDescriptionDialog) Game.i.uiManager.getComponent(ItemDescriptionDialog.class);
    }

    public ItemDescriptionDialog() {
        this.f3604b.setOrigin(487.5f, 118.5f);
        this.f3603a.getTable().add((Table) this.f3604b).size(975.0f, 237.0f);
        this.f3604b.addActor(new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{6.0f, 19.0f, 6.0f, 220.0f, 966.0f, 231.0f, 975.0f, 0.0f}));
        this.f3604b.addActor(new QuadActor(MaterialColor.BLUE_GREY.P800, new float[]{0.0f, 25.0f, 0.0f, 226.0f, 960.0f, 237.0f, 960.0f, 15.0f}));
        this.c = new ItemCell();
        this.c.setPosition(40.0f, 56.0f);
        this.f3604b.addActor(this.c);
        this.d = new Table();
        this.d.setPosition(210.0f, 56.0f);
        this.d.setSize(650.0f, 140.0f);
        this.f3604b.addActor(this.d);
        this.f3603a.getTable().setVisible(false);
    }

    public void show(Item item) {
        showWithCount(item, 0);
    }

    public void showWithCount(Item item, int i) {
        this.c.setItem(item, i);
        this.d.clear();
        this.d.add((Table) new Label(item.getTitle(), new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE))).top().left().width(650.0f).row();
        Label label = new Label(item.getDescription(), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        label.setColor(1.0f, 1.0f, 1.0f, 0.78f);
        label.setWrap(true);
        this.d.add((Table) label).top().left().width(650.0f).row();
        this.f3603a.getTable().setVisible(true);
        DarkOverlay.i().addCallerOverlayLayer("ItemDescriptionDialog", this.f3603a.zIndex - 1, () -> {
            hide();
            return true;
        });
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        this.f3604b.clearActions();
        this.f3604b.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.1f * f), Actions.scaleBy(1.0f, 0.0f, 0.3f * f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.3f * f, Interpolation.swingOut))));
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        DarkOverlay.i().removeCaller("ItemDescriptionDialog");
        this.f3604b.clearActions();
        this.f3604b.addAction(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.07f * f), Actions.scaleBy(0.0f, -this.f3604b.getScaleY(), 0.3f * f, Interpolation.swingIn)), Actions.scaleBy(-this.f3604b.getScaleX(), 0.0f, 0.3f * f, Interpolation.swingIn)), Actions.run(() -> {
            this.f3603a.getTable().setVisible(false);
        })));
    }
}
