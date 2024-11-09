package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Interpolation;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.TrophyManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.ModelView;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/TrophyViewOverlay.class */
public class TrophyViewOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3759a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 161, "TrophyViewOverlay main");

    /* renamed from: b, reason: collision with root package name */
    private Group f3760b = new Group();
    private Label c;
    private Table d;
    private ModelView e;
    private ModelView f;
    private Image g;

    public static TrophyViewOverlay i() {
        return (TrophyViewOverlay) Game.i.uiManager.getComponent(TrophyViewOverlay.class);
    }

    public TrophyViewOverlay() {
        this.f3760b.setOrigin(458.0f, 203.0f);
        this.f3760b.setTouchable(Touchable.childrenOnly);
        this.f3759a.getTable().add((Table) this.f3760b).size(916.0f, 406.0f);
        QuadActor quadActor = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{0.0f, 17.0f, 0.0f, 93.0f, 525.0f, 94.0f, 498.0f, 0.0f});
        quadActor.setPosition(389.0f, 253.0f);
        this.f3760b.addActor(quadActor);
        QuadActor quadActor2 = new QuadActor(new Color(791621631), new float[]{0.0f, 17.0f, 0.0f, 102.0f, 510.0f, 107.0f, 489.0f, 13.0f});
        quadActor2.setPosition(389.0f, 253.0f);
        quadActor2.setTouchable(Touchable.enabled);
        this.f3760b.addActor(quadActor2);
        this.c = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.c.setPosition(427.0f, 297.0f);
        this.c.setSize(500.0f, 26.0f);
        this.f3760b.addActor(this.c);
        this.f3760b.addActor(new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{17.0f, 0.0f, 3.0f, 383.0f, 413.0f, 381.0f, 391.0f, 14.0f}));
        QuadActor quadActor3 = new QuadActor(new Color(791621631), new float[]{3.0f, 14.0f, 0.0f, 406.0f, 391.0f, 402.0f, 387.0f, 18.0f});
        quadActor3.setTouchable(Touchable.enabled);
        this.f3760b.addActor(quadActor3);
        Environment environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.3f, 0.3f, 0.3f, 1.0f));
        environment.add(new DirectionalLight().set(0.75f, 0.75f, 0.75f, -0.5f, -1.0f, -0.2f));
        this.e = new ModelView(User32.WM_MDIREFRESHMENU, User32.WM_MDIREFRESHMENU, ModelView.rotateModelAround, environment, false);
        this.e.setName("trophy-view-overlay-model-view");
        this.e.setPosition(-87.0f, -72.0f);
        this.e.setSize(564.0f, 564.0f);
        this.e.setTouchable(Touchable.disabled);
        this.e.camera.position.set(0.5f, 0.15f, 0.0f);
        this.e.camera.lookAt(0.0f, -0.03f, 0.0f);
        this.e.camera.update();
        this.f3760b.addActor(this.e);
        this.g = new Image();
        this.g.setPosition(67.0f, 82.0f);
        this.g.setSize(256.0f, 256.0f);
        this.f3760b.addActor(this.g);
        this.f = new ModelView(64, 64, ModelView.rotateModelAround, new Environment(), false);
        this.f.setName("trophy-view-overlay-model-view-locked");
        this.f.setPosition(-87.0f, -72.0f);
        this.f.setSize(564.0f, 564.0f);
        this.f3760b.addActor(this.f);
        this.d = new Table();
        this.d.setPosition(419.0f, -65.0f);
        this.d.setSize(450.0f, 301.0f);
        this.f3760b.addActor(this.d);
        this.f3759a.getTable().setVisible(false);
    }

    public void show(TrophyType trophyType) {
        this.d.clearChildren();
        this.f.setVisible(false);
        this.e.setVisible(false);
        this.g.setVisible(false);
        Model sceneModelIfLoaded = Game.i.assetManager.getSceneModelIfLoaded();
        if (Game.i.trophyManager.getConfig(trophyType).isReceived()) {
            if (Game.i.settingsManager.isThreeDeeModelsEnabled() && sceneModelIfLoaded != null) {
                this.e.setModelPart(sceneModelIfLoaded, "t-" + trophyType.name(), null, 0.3f);
                this.e.setVisible(true);
            } else {
                this.g.setDrawable(new TextureRegionDrawable(Game.i.trophyManager.getConfig(trophyType).getIconTexture()));
                this.g.setColor(Color.WHITE);
                this.g.setVisible(true);
            }
            TrophyManager.TrophyConfig config = Game.i.trophyManager.getConfig(trophyType);
            this.c.setText(config.getTitle());
            this.c.setColor(Color.WHITE);
            for (int i = 0; i < config.gameValues.size; i++) {
                GameValueManager.GameValueEffect gameValueEffect = config.gameValues.get(i);
                GameValueManager.GameValueStockConfig stockValueConfig = Game.i.gameValueManager.getStockValueConfig(gameValueEffect.type);
                Group group = new Group();
                group.setTransform(false);
                this.d.add((Table) group).size(64.0f).pad(8.0f);
                Image image = new Image(stockValueConfig.getIcon());
                image.setSize(64.0f, 64.0f);
                group.addActor(image);
                Label label = new Label(Game.i.gameValueManager.getTitle(gameValueEffect.type), Game.i.assetManager.getLabelStyle(24));
                label.setWrap(true);
                this.d.add((Table) label).padLeft(10.0f).height(80.0f).expandX().fillX();
                this.d.add((Table) new Label(Game.i.gameValueManager.formatEffectValue(gameValueEffect.delta, stockValueConfig.units), Game.i.assetManager.getLabelStyle(30))).height(80.0f).padLeft(16.0f).row();
            }
            Label label2 = new Label(Game.i.localeManager.i18n.get("trophy_view_effects_hint"), Game.i.assetManager.getLabelStyle(18));
            label2.setAlignment(1);
            label2.setColor(MaterialColor.AMBER.P300.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
            label2.setWrap(true);
            this.d.add((Table) label2).minWidth(400.0f).fillX().colspan(2).padTop(15.0f).row();
            this.d.add().expandY().fillY().width(1.0f);
        } else {
            if (Game.i.settingsManager.isThreeDeeModelsEnabled() && sceneModelIfLoaded != null) {
                this.f.setModelPart(sceneModelIfLoaded, "t-" + trophyType.name(), Game.i.assetManager.normalMaterial, 0.3f);
                this.f.setVisible(true);
            } else {
                this.g.setDrawable(new TextureRegionDrawable(Game.i.trophyManager.getConfig(trophyType).getWhiteTexture()));
                this.g.setColor(Color.BLACK);
                this.g.setVisible(true);
            }
            this.c.setText(Game.i.localeManager.i18n.get("unknown_trophy"));
            this.c.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            Label label3 = new Label(Game.i.trophyManager.getHowToObtainHint(trophyType), Game.i.assetManager.getLabelStyle(24));
            label3.setWrap(true);
            this.d.add((Table) label3).growX().row();
            this.d.add().width(1.0f).growY();
        }
        this.f3759a.getTable().setVisible(true);
        DarkOverlay.i().addCallerOverlayLayer("TrophyViewOverlay", this.f3759a.zIndex - 1, () -> {
            hide();
            return true;
        });
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        this.f3760b.clearActions();
        this.f3760b.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.1f * f), Actions.scaleBy(1.0f, 0.0f, 0.3f * f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.3f * f, Interpolation.swingOut))));
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        DarkOverlay.i().removeCaller("TrophyViewOverlay");
        this.f3760b.clearActions();
        this.f3760b.addAction(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.07f * f), Actions.scaleBy(0.0f, -this.f3760b.getScaleY(), 0.3f * f, Interpolation.swingIn)), Actions.scaleBy(-this.f3760b.getScaleX(), 0.0f, 0.3f * f, Interpolation.swingIn)), Actions.run(() -> {
            this.f3759a.getTable().setVisible(false);
        })));
    }
}
