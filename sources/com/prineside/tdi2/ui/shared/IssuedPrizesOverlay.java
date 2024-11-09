package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/IssuedPrizesOverlay.class */
public class IssuedPrizesOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3557a = TLog.forClass(IssuedPrizesOverlay.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3558b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 151, "IssuedPrizes main");
    private Table c;
    private ScrollPane d;
    private Label e;
    private Label f;
    private Label g;
    private Actor h;

    public static IssuedPrizesOverlay i() {
        return (IssuedPrizesOverlay) Game.i.uiManager.getComponent(IssuedPrizesOverlay.class);
    }

    public IssuedPrizesOverlay() {
        Table table = new Table();
        table.setTouchable(Touchable.childrenOnly);
        this.d = new ScrollPane(table);
        UiUtils.enableMouseMoveScrollFocus(this.d);
        this.d.setTransform(true);
        this.d.setOrigin(607.0f, Game.i.settingsManager.getScaledViewportHeight() / 2.0f);
        this.d.setTouchable(Touchable.childrenOnly);
        this.f3558b.getTable().add((Table) this.d).width(1214.0f).expandY().fillY();
        Group group = new Group();
        group.setTransform(false);
        QuadActor quadActor = new QuadActor(new Color(555819519), new float[]{0.0f, 0.0f, 0.0f, 110.0f, 1060.0f, 95.0f, 1060.0f, 0.0f});
        quadActor.setSize(1160.0f, 110.0f);
        group.addActor(quadActor);
        table.add((Table) group).height(110.0f).padTop(160.0f).padLeft(26.0f).padRight(26.0f).width(1160.0f).row();
        this.e = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.e.setSize(1000.0f, 26.0f);
        this.e.setPosition(40.0f, 26.0f);
        this.e.setAlignment(12);
        group.addActor(this.e);
        this.f = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.f.setPosition(40.0f, 26.0f);
        this.f.setSize(1080.0f, 26.0f);
        this.f.setAlignment(20);
        this.f.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        group.addActor(this.f);
        this.c = new Table();
        table.add(this.c).expandX().fillX().row();
        Group group2 = new Group();
        group2.setTransform(false);
        QuadActor quadActor2 = new QuadActor(new Color(555819519), new float[]{0.0f, 0.0f, 0.0f, 30.0f, 1060.0f, 30.0f, 1060.0f, 15.0f});
        quadActor2.setSize(1160.0f, 30.0f);
        group2.addActor(quadActor2);
        this.g = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.g.addAction(Actions.forever(Actions.sequence(Actions.color(Color.WHITE, 0.4f), Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.56f), 0.8f), Actions.delay(0.5f))));
        this.g.setTouchable(Touchable.disabled);
        this.g.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.g.setSize(1160.0f, 20.0f);
        this.g.setPosition(0.0f, -60.0f);
        this.g.setAlignment(1);
        group2.addActor(this.g);
        table.add((Table) group2).height(30.0f).padBottom(160.0f).padLeft(26.0f).padRight(26.0f).width(1160.0f).row();
        this.f3558b.getTable().setVisible(false);
    }

    public void show(Array<IssuedItems> array) {
        if (array.size == 0) {
            return;
        }
        this.e.setText(Game.i.localeManager.i18n.get("earned_items").toUpperCase());
        this.f.setText(Game.i.localeManager.i18n.get("tap_icons_for_more_info"));
        this.g.setText(Game.i.localeManager.i18n.get("tap_outside_list_to_hide"));
        this.c.clear();
        Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(24);
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        array.sort((issuedItems, issuedItems2) -> {
            return Integer.compare(issuedItems2.issueTimestamp, issuedItems.issueTimestamp);
        });
        float f2 = 0.0f;
        for (int i = 0; i < array.size; i++) {
            try {
                IssuedItems issuedItems3 = array.get(i);
                int ceil = MathUtils.ceil(issuedItems3.items.size / 8.0f);
                Group group = new Group();
                group.setTransform(false);
                float f3 = 127.0f + (ceil * 142.0f);
                this.c.add((Table) group).size(1212.0f, f3).padTop(-12.0f).row();
                Actor image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image.setColor(new Color(555819519));
                image.setSize(1160.0f, f3 - 12.0f);
                image.setPosition(26.0f, 0.0f);
                group.addActor(image);
                Group group2 = new Group();
                group2.setTransform(true);
                group2.setSize(1212.0f, f3);
                group.addActor(group2);
                if (i % 2 == 0) {
                    group2.setOrigin(0.0f, f3 / 2.0f);
                } else {
                    group2.setOrigin(1212.0f, f3 / 2.0f);
                }
                f2 += StrictMath.max(0.2f - (i * 0.02f), 0.0f);
                group2.addAction(Actions.sequence(Actions.scaleTo(0.0f, 1.0f), Actions.delay(f2 * f), Actions.scaleTo(1.0f, 1.0f, 0.3f * f, Interpolation.exp5Out), Actions.run(() -> {
                    group2.setTransform(false);
                })));
                if (i % 2 == 0) {
                    QuadActor quadActor = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{26.0f, 19.0f, 26.0f, 60.0f, 1186.0f, 85.0f, 1186.0f, 0.0f});
                    quadActor.setSize(1186.0f, 85.0f);
                    group2.addActor(quadActor);
                    QuadActor quadActor2 = new QuadActor(new Color(791621631), new float[]{6.0f, 26.0f, 0.0f, f3 - 13.0f, 1212.0f, f3, 1206.0f, 13.0f});
                    quadActor2.setSize(1212.0f, f3);
                    group2.addActor(quadActor2);
                } else {
                    QuadActor quadActor3 = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{26.0f, 0.0f, 26.0f, 60.0f, 1186.0f, 60.0f, 1186.0f, 18.0f});
                    quadActor3.setSize(1186.0f, 60.0f);
                    group2.addActor(quadActor3);
                    QuadActor quadActor4 = new QuadActor(new Color(791621631), new float[]{6.0f, 12.0f, 0.0f, f3, 1212.0f, f3 - 13.0f, 1206.0f, 25.0f});
                    quadActor4.setSize(1212.0f, f3);
                    group2.addActor(quadActor4);
                }
                Label label = new Label(issuedItems3.getReasonDescription(), labelStyle);
                label.setPosition(66.0f, f3 - 59.0f);
                label.setSize(300.0f, 18.0f);
                group2.addActor(label);
                Label label2 = new Label(new SimpleDateFormat("MMMM dd, HH:mm", Game.i.localeManager.i18n.getLocale()).format(new Date(issuedItems3.issueTimestamp * 1000)), labelStyle);
                label2.setAlignment(16);
                label2.setPosition(0.0f, f3 - 59.0f);
                label2.setSize(1146.0f, 18.0f);
                group2.addActor(label2);
                Table table = new Table();
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < issuedItems3.items.size; i4++) {
                    final ItemStack itemStack = issuedItems3.items.get(i4);
                    ItemCell itemCell = new ItemCell();
                    itemCell.setColRow(i2, i3);
                    itemCell.setItemStack(itemStack);
                    itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.IssuedPrizesOverlay.1
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f4, float f5) {
                            ItemDescriptionDialog.i().showWithCount(itemStack.getItem(), itemStack.getCount());
                            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                        }
                    });
                    Cell add = table.add((Table) itemCell);
                    i2++;
                    if (i2 == 8) {
                        i2 = 0;
                        i3++;
                        table.add().height(1.0f).fillX().expandX();
                        add.row();
                    }
                }
                if (i3 == 0) {
                    table.add().height(1.0f).fillX().expandX();
                }
                table.setPosition(66.0f, 50.0f);
                table.setSize(1146.0f, 142.0f * ceil);
                group2.addActor(table);
            } catch (Exception e) {
                f3557a.e("Can't add earnings row", e);
            }
        }
        this.f3558b.getTable().setVisible(true);
        DarkOverlay.i().addCallerOverlayLayer("IssuedPrizesOverlay", this.f3558b.zIndex - 1, () -> {
            hide();
            return true;
        });
        this.d.clearActions();
        this.d.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.1f * f), Actions.scaleBy(1.0f, 0.0f, 0.3f * f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.3f * f, Interpolation.swingOut))));
        if (Game.i.uiManager.stage.getScrollFocus() != this.d) {
            this.h = Game.i.uiManager.stage.getScrollFocus();
            Game.i.uiManager.stage.setScrollFocus(this.d);
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        DarkOverlay.i().removeCaller("IssuedPrizesOverlay");
        this.d.clearActions();
        this.d.addAction(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.07f * f), Actions.scaleBy(0.0f, -this.d.getScaleY(), 0.3f * f, Interpolation.swingIn)), Actions.scaleBy(-this.d.getScaleX(), 0.0f, 0.3f * f, Interpolation.swingIn)), Actions.run(() -> {
            this.f3558b.getTable().setVisible(false);
        })));
        if (this.h == null || this.h.getStage() != null) {
            Game.i.uiManager.stage.setScrollFocus(this.h);
            this.h = null;
        }
    }
}
