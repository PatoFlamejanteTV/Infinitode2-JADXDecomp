package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.UiUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/OpenedPackOverlay.class */
public final class OpenedPackOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3722a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 194, "OpenedPackOverlay main");

    /* renamed from: b, reason: collision with root package name */
    private final Group f3723b;
    private final Group c;
    private final Table d;
    private final ScrollPane e;
    private long f;

    public static OpenedPackOverlay i() {
        return (OpenedPackOverlay) Game.i.uiManager.getComponent(OpenedPackOverlay.class);
    }

    public OpenedPackOverlay() {
        float scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight();
        this.f3723b = new Group();
        this.f3723b.setName("OpenedPackOverlay mainContainerWrap");
        this.f3723b.setTransform(false);
        this.f3723b.setTouchable(Touchable.childrenOnly);
        this.f3723b.setOrigin(640.0f, scaledViewportHeight * 0.5f);
        this.f3722a.getTable().add((Table) this.f3723b).size(1280.0f, scaledViewportHeight);
        this.c = new Group();
        this.c.setName("OpenedPackOverlay mainContainer");
        this.c.setTransform(false);
        this.c.setSize(1280.0f, scaledViewportHeight);
        this.c.setOrigin(640.0f, scaledViewportHeight * 0.5f);
        this.c.setTouchable(Touchable.childrenOnly);
        this.f3723b.addActor(this.c);
        this.d = new Table();
        this.d.setName("OpenedPackOverlay cells");
        this.d.setSize(1280.0f, scaledViewportHeight);
        this.d.setTouchable(Touchable.childrenOnly);
        this.e = new ScrollPane(this.d, Game.i.assetManager.getScrollPaneStyle(0.0f));
        UiUtils.enableMouseMoveScrollFocus(this.e);
        this.e.setName("OpenedPackOverlay scrollPane");
        this.e.setSize(1280.0f, scaledViewportHeight);
        this.e.setTouchable(Touchable.childrenOnly);
        this.e.setScrollingDisabled(true, false);
        this.c.addActor(this.e);
        hide();
    }

    public final void show(Array<ItemStack> array, boolean z) {
        float scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight();
        this.f3723b.setOrigin(640.0f, scaledViewportHeight * 0.5f);
        this.f3722a.getTable().clear();
        this.f3722a.getTable().add((Table) this.f3723b).size(1280.0f, scaledViewportHeight);
        this.c.setSize(1280.0f, scaledViewportHeight);
        this.c.setOrigin(640.0f, scaledViewportHeight * 0.5f);
        this.d.setSize(1280.0f, scaledViewportHeight);
        this.e.setSize(1280.0f, scaledViewportHeight);
        this.f = Game.getTimestampMillis();
        this.d.clear();
        this.d.add().width(1.0f).height(40.0f).row();
        if (z) {
            Table table = new Table();
            this.d.add(table).padBottom(16.0f).colspan(10).row();
            Image image = new Image(Game.i.assetManager.getDrawable("icon-cubes-stacked-tall"));
            image.setColor(MaterialColor.LIGHT_GREEN.P500);
            table.add((Table) image).size(48.0f);
            Label label = new Label(Game.i.localeManager.i18n.get("items_were_stacked_hint"), Game.i.assetManager.getLabelStyle(30));
            label.setColor(MaterialColor.LIGHT_GREEN.P500);
            label.setTouchable(Touchable.disabled);
            label.setAlignment(1);
            table.add((Table) label).height(48.0f).padLeft(16.0f);
        }
        float f = 0.1f;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < array.size; i4++) {
            final ItemStack itemStack = array.get(i4);
            ItemCell itemCell = new ItemCell();
            itemCell.setItemStack(itemStack);
            itemCell.setColRow(i2, i3);
            itemCell.setCovered(true);
            itemCell.addAction(Actions.sequence(Actions.delay(f), Actions.run(() -> {
                itemCell.reveal();
            }), Actions.delay(0.3f), Actions.run(() -> {
                if (itemCell.getItem().getRarity().ordinal() >= RarityType.LEGENDARY.ordinal()) {
                    itemCell.showRays(true);
                }
            })));
            itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.OpenedPackOverlay.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f2, float f3) {
                    ItemDescriptionDialog.i().showWithCount(itemStack.getItem(), itemStack.getCount());
                }
            });
            float f2 = 0.3f - (i * 0.05f);
            float f3 = f2;
            if (f2 < 0.05f) {
                f3 = 0.05f;
            }
            f += f3;
            i++;
            this.d.add((Table) itemCell);
            i2++;
            if (i2 == 10) {
                i2 = 0;
                i3++;
                this.d.row();
            }
        }
        Label label2 = new Label(Game.i.localeManager.i18n.get("tap_outside_list_to_hide"), Game.i.assetManager.getLabelStyle(24));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label2.setTouchable(Touchable.disabled);
        label2.setAlignment(1);
        this.d.row();
        this.d.add((Table) label2).width(1.0f).padTop(16.0f).colspan(10);
        label2.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.delay(0.75f), Actions.fadeIn(0.3f)));
        DarkOverlay.i().addCallerOverlayLayer("OpenedPackOverlay", this.f3722a.zIndex - 1, () -> {
            if (Game.getTimestampMillis() - this.f > 500) {
                hide();
                return true;
            }
            return false;
        });
        UiUtils.bouncyShowOverlayWithCallback(null, this.f3722a.getTable(), this.c, () -> {
            this.c.setTouchable(Touchable.childrenOnly);
        });
        this.d.row();
        this.d.add().width(1.0f).height(40.0f);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        DarkOverlay.i().removeCaller("OpenedPackOverlay");
        UiUtils.bouncyHideOverlay(null, this.f3722a.getTable(), this.c);
        this.d.clear();
    }
}
