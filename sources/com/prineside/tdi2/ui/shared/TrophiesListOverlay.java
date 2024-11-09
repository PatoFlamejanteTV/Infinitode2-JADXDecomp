package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
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

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/TrophiesListOverlay.class */
public final class TrophiesListOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3756a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 153, "TrophiesList main");

    /* renamed from: b, reason: collision with root package name */
    private Table f3757b;
    private ScrollPane c;
    private Label d;
    private Label e;
    private Label f;

    public static TrophiesListOverlay i() {
        return (TrophiesListOverlay) Game.i.uiManager.getComponent(TrophiesListOverlay.class);
    }

    static {
        new Color(218959247);
    }

    public TrophiesListOverlay() {
        Table table = new Table();
        table.setTouchable(Touchable.childrenOnly);
        this.c = new ScrollPane(table);
        UiUtils.enableMouseMoveScrollFocus(this.c);
        this.c.setTransform(true);
        this.c.setOrigin(559.0f, Game.i.settingsManager.getScaledViewportHeight() / 2.0f);
        this.c.setTouchable(Touchable.childrenOnly);
        this.f3756a.getTable().add((Table) this.c).width(1118.0f).expandY().fillY();
        Group group = new Group();
        group.setTransform(false);
        QuadActor quadActor = new QuadActor(new Color(791621631), new float[]{0.0f, 0.0f, 0.0f, 110.0f, 1060.0f, 95.0f, 1060.0f, 0.0f});
        quadActor.setSize(1118.0f, 110.0f);
        group.addActor(quadActor);
        table.add((Table) group).height(110.0f).padTop(160.0f).width(1118.0f).row();
        this.d = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.d.setSize(1000.0f, 26.0f);
        this.d.setPosition(40.0f, 26.0f);
        this.d.setAlignment(12);
        group.addActor(this.d);
        this.e = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.e.setPosition(40.0f, 26.0f);
        this.e.setSize(1038.0f, 26.0f);
        this.e.setAlignment(20);
        this.e.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        group.addActor(this.e);
        this.f3757b = new Table();
        this.f3757b.setTouchable(Touchable.enabled);
        this.f3757b.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(791621631)));
        table.add(this.f3757b).expandX().fillX().row();
        Group group2 = new Group();
        group2.setTransform(false);
        QuadActor quadActor2 = new QuadActor(new Color(791621631), new float[]{0.0f, 0.0f, 0.0f, 30.0f, 1060.0f, 30.0f, 1060.0f, 15.0f});
        quadActor2.setSize(1118.0f, 30.0f);
        group2.addActor(quadActor2);
        this.f = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.f.addAction(Actions.forever(Actions.sequence(Actions.color(Color.WHITE, 0.4f), Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.56f), 0.8f), Actions.delay(0.5f))));
        this.f.setTouchable(Touchable.disabled);
        this.f.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.f.setSize(1118.0f, 20.0f);
        this.f.setPosition(0.0f, -60.0f);
        this.f.setAlignment(1);
        group2.addActor(this.f);
        table.add((Table) group2).height(30.0f).padBottom(160.0f).width(1118.0f).row();
        this.f3756a.getTable().setVisible(false);
    }

    public final void show() {
        Image image;
        this.f3757b.clear();
        this.d.setText(Game.i.localeManager.i18n.get("trophies").toUpperCase());
        this.e.setText(Game.i.localeManager.i18n.get("tap_icons_for_more_info"));
        this.f.setText(Game.i.localeManager.i18n.get("tap_outside_list_to_hide"));
        int i = 0;
        int i2 = 0;
        for (final TrophyType trophyType : TrophyType.values) {
            ItemCell itemCell = new ItemCell();
            itemCell.setColRow(i, i2);
            if (Game.i.trophyManager.getConfig(trophyType).isReceived()) {
                image = new Image(Game.i.trophyManager.getConfig(trophyType).getIconTexture());
            } else {
                Image image2 = new Image(Game.i.trophyManager.getConfig(trophyType).getWhiteTexture());
                image = image2;
                image2.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            }
            itemCell.setIconAndCount(image, 1.25f, 0);
            itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.TrophiesListOverlay.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    TrophyViewOverlay.i().show(trophyType);
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            });
            Cell size = this.f3757b.add((Table) itemCell).size(128.0f, 140.0f);
            i++;
            if (i == 8) {
                i = 0;
                i2++;
                size.row();
            }
        }
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        DarkOverlay.i().addCallerOverlayLayer("TrophiesListOverlay", this.f3756a.zIndex - 1, () -> {
            hide();
            return true;
        });
        this.f3756a.getTable().setVisible(true);
        this.c.clearActions();
        this.c.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.1f * f), Actions.scaleBy(1.0f, 0.0f, 0.3f * f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.3f * f, Interpolation.swingOut))));
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        DarkOverlay.i().removeCaller("TrophiesListOverlay");
        this.c.clearActions();
        this.c.addAction(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.07f * f), Actions.scaleBy(0.0f, -this.c.getScaleY(), 0.3f * f, Interpolation.swingIn)), Actions.scaleBy(-this.c.getScaleX(), 0.0f, 0.3f * f, Interpolation.swingIn)), Actions.run(() -> {
            this.f3756a.getTable().setVisible(false);
        })));
    }
}
