package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextArea;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/RatingForm.class */
public final class RatingForm extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3382a = TLog.forClass(RatingForm.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3383b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 110, "RatingForm tint");
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 111, "RatingForm main");
    private Group d;
    private Group e;
    private Group f;
    private Group g;

    public static RatingForm i() {
        return (RatingForm) Game.i.uiManager.getComponent(RatingForm.class);
    }

    public RatingForm() {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(Config.BACKGROUND_COLOR);
        image.getColor().f889a = 0.78f;
        this.f3383b.getTable().add((Table) image).expand().fill();
        this.f3383b.getTable().setTouchable(Touchable.enabled);
        this.f3383b.getTable().addListener(new InputVoid());
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(360.0f, 285.0f);
        this.c.getTable().add((Table) group).size(720.0f, 570.0f);
        this.d = new Group();
        this.d.setTransform(false);
        this.d.setOrigin(360.0f, 285.0f);
        this.d.setSize(720.0f, 570.0f);
        group.addActor(this.d);
        this.e = new Group();
        this.e.setTransform(false);
        this.e.setSize(720.0f, 300.0f);
        this.e.setPosition(0.0f, 130.0f);
        this.d.addActor(this.e);
        this.f = new Group();
        this.f.setTransform(false);
        this.f.setSize(720.0f, 300.0f);
        this.f.setPosition(0.0f, 130.0f);
        this.d.addActor(this.f);
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setSize(720.0f, 570.0f);
        this.d.addActor(this.g);
        this.f3383b.getTable().addAction(Actions.alpha(0.0f));
        this.f3383b.getTable().setVisible(false);
        this.c.getTable().setVisible(false);
    }

    public final void setVisible(boolean z) {
        if (z) {
            UiUtils.bouncyShowOverlay(this.f3383b.getTable(), this.c.getTable(), this.d);
        } else {
            UiUtils.bouncyHideOverlay(this.f3383b.getTable(), this.c.getTable(), this.d);
        }
    }

    public final void showRatePrompt() {
        this.e.setVisible(true);
        this.f.setVisible(false);
        this.g.setVisible(false);
        this.e.clearChildren();
        this.e.addActor(new QuadActor(new Color(791621631), new float[]{15.0f, 0.0f, 0.0f, 300.0f, 720.0f, 288.0f, 705.0f, 7.0f}));
        Label label = new Label(Game.i.localeManager.i18n.get("rating_form_prompt"), Game.i.assetManager.getLabelStyle(36));
        label.setAlignment(1);
        label.setSize(640.0f, 30.0f);
        label.setWrap(true);
        label.setPosition(40.0f, 175.0f);
        this.e.addActor(label);
        ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("never"), Game.i.assetManager.getLabelStyle(30), () -> {
            hide();
            Game.i.ratingManager.promptAnsweredNever();
        });
        complexButton.setSize(251.0f, 101.0f);
        complexButton.setPosition(0.0f, -11.0f);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 50.0f, 22.0f, 48.0f, 48.0f);
        complexButton.setLabel(115.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{11.0f, 0.0f, 0.0f, 101.0f, 245.0f, 93.0f, 251.0f, 0.0f})), 0.0f, 0.0f, 251.0f, 101.0f);
        complexButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.WHITE);
        this.e.addActor(complexButton);
        ComplexButton complexButton2 = new ComplexButton(Game.i.localeManager.i18n.get("later"), Game.i.assetManager.getLabelStyle(30), () -> {
            hide();
            Game.i.ratingManager.promptAnsweredLater();
        });
        complexButton2.setSize(218.0f, 96.0f);
        complexButton2.setPosition(251.0f, -11.0f);
        complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-clock"), 47.0f, 22.0f, 48.0f, 48.0f);
        complexButton2.setLabel(103.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton2.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{7.0f, 0.0f, 0.0f, 93.0f, 218.0f, 96.0f, 212.0f, 0.0f})), 0.0f, 0.0f, 218.0f, 96.0f);
        complexButton2.setBackgroundColors(MaterialColor.AMBER.P800, MaterialColor.AMBER.P900, MaterialColor.AMBER.P700, Color.WHITE);
        this.e.addActor(complexButton2);
        ComplexButton complexButton3 = new ComplexButton(Game.i.localeManager.i18n.get("rate"), Game.i.assetManager.getLabelStyle(30), () -> {
            showStarsForm();
        });
        complexButton3.setSize(251.0f, 101.0f);
        complexButton3.setPosition(469.0f, -11.0f);
        complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-star"), 50.0f, 22.0f, 48.0f, 48.0f);
        complexButton3.setLabel(115.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton3.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 7.0f, 96.0f, 251.0f, 101.0f, 240.0f, 0.0f})), 0.0f, 0.0f, 251.0f, 101.0f);
        complexButton3.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.WHITE);
        this.e.addActor(complexButton3);
        setVisible(true);
    }

    public final void showStarsForm() {
        this.e.setVisible(false);
        this.f.setVisible(true);
        this.g.setVisible(false);
        this.f.clearChildren();
        this.f.addActor(new QuadActor(new Color(791621631), new float[]{15.0f, 0.0f, 0.0f, 300.0f, 720.0f, 288.0f, 705.0f, 7.0f}));
        final int[] iArr = {5};
        ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("cancel"), Game.i.assetManager.getLabelStyle(30), () -> {
            hide();
        });
        complexButton.setSize(251.0f, 101.0f);
        complexButton.setPosition(0.0f, -11.0f);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 50.0f, 22.0f, 48.0f, 48.0f);
        complexButton.setLabel(115.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{11.0f, 0.0f, 0.0f, 101.0f, 245.0f, 93.0f, 251.0f, 0.0f})), 0.0f, 0.0f, 251.0f, 101.0f);
        complexButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.WHITE);
        this.f.addActor(complexButton);
        final ComplexButton complexButton2 = new ComplexButton("5", Game.i.assetManager.getLabelStyle(30), () -> {
            showFeedbackForm(iArr[0]);
        });
        complexButton2.setSize(251.0f, 101.0f);
        complexButton2.setPosition(469.0f, -11.0f);
        complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-star"), 82.0f, 22.0f, 48.0f, 48.0f);
        complexButton2.setLabel(140.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton2.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 7.0f, 96.0f, 251.0f, 101.0f, 240.0f, 0.0f})), 0.0f, 0.0f, 251.0f, 101.0f);
        complexButton2.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, MaterialColor.GREY.P800);
        complexButton2.setEnabled(false);
        this.f.addActor(complexButton2);
        final Image[] imageArr = new Image[5];
        for (int i = 0; i < 5; i++) {
            Image image = new Image(Game.i.assetManager.getDrawable("icon-star"));
            image.setPosition(128.0f + (i * 96.0f), 152.0f);
            image.setSize(80.0f, 80.0f);
            image.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            imageArr[i] = image;
            this.f.addActor(image);
            image.setTouchable(Touchable.enabled);
            final int i2 = i;
            image.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.RatingForm.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    Color color;
                    iArr[0] = i2 + 1;
                    for (int i3 = 0; i3 < 5; i3++) {
                        imageArr[i3].setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    }
                    if (iArr[0] < 3) {
                        color = MaterialColor.RED.P500;
                    } else if (iArr[0] < 5) {
                        color = MaterialColor.AMBER.P500;
                    } else {
                        color = MaterialColor.GREEN.P500;
                    }
                    for (int i4 = 0; i4 < iArr[0]; i4++) {
                        imageArr[i4].setColor(color);
                    }
                    complexButton2.setText(String.valueOf(iArr[0]));
                    complexButton2.setEnabled(true);
                }
            });
        }
        setVisible(true);
    }

    public final void showFeedbackForm(int i) {
        Object obj;
        if (Config.isHeadless()) {
            return;
        }
        if (i == 5) {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.REVIEW_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("playerid", Game.i.authManager.getPlayerId());
            if (Game.i.authManager.isSignedIn()) {
                hashMap.put("sessionid", Game.i.authManager.getSessionId());
            }
            hashMap.put("stars", "5");
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.ui.components.RatingForm.2
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    RatingForm.f3382a.i("response: " + httpResponse.getResultAsString(), new Object[0]);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    RatingForm.f3382a.e("failed", th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                }
            });
            if (Gdx.app.getType() == Application.ApplicationType.iOS) {
                obj = "App Store";
            } else {
                obj = "Google Play";
            }
            Dialog.i().showConfirm(Game.i.localeManager.i18n.format("rating_form_thanks_market_prompt", obj), () -> {
                if (Gdx.app.getType() == Application.ApplicationType.Android) {
                    Gdx.f881net.openURI(Config.PLAY_STORE_URI);
                } else if (Gdx.app.getType() == Application.ApplicationType.iOS) {
                    Gdx.f881net.openURI(Config.APP_STORE_URI);
                }
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("thanks_for_feedback"));
            });
            hide();
            Game.i.ratingManager.madeReview(i, null);
            return;
        }
        this.e.setVisible(false);
        this.f.setVisible(false);
        this.g.setVisible(true);
        this.g.clearChildren();
        this.g.addActor(new QuadActor(new Color(791621631), new float[]{15.0f, 0.0f, 0.0f, 570.0f, 720.0f, 558.0f, 705.0f, 7.0f}));
        Label label = new Label(Game.i.localeManager.i18n.get("thanks_for_feedback"), Game.i.assetManager.getLabelStyle(36));
        label.setPosition(55.0f, 485.0f);
        label.setSize(100.0f, 27.0f);
        label.setColor(MaterialColor.GREEN.P500);
        this.g.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("rating_form_comment_prompt"), Game.i.assetManager.getLabelStyle(30));
        label2.setPosition(55.0f, 396.0f);
        label2.setSize(600.0f, 58.0f);
        label2.setWrap(true);
        label2.setAlignment(10);
        this.g.addActor(label2);
        Label label3 = new Label("[#AAAAAA]" + Game.i.localeManager.i18n.get("if_problems_contact_us") + ": [][#03A9F4]sup.prineside@gmail.com[]", Game.i.assetManager.getLabelStyle(24));
        label3.setPosition(55.0f, 310.0f);
        label3.setSize(600.0f, 42.0f);
        label3.setWrap(true);
        label3.setAlignment(10);
        this.g.addActor(label3);
        label3.setTouchable(Touchable.enabled);
        label3.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.RatingForm.3
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Gdx.f881net.openURI("mailto:sup.prineside@gmail.com");
            }
        });
        TextArea textArea = new TextArea("", Game.i.assetManager.getTextFieldStyle(24));
        textArea.setMessageText(Game.i.localeManager.i18n.get("please_write_your_thoughts"));
        textArea.setPosition(55.0f, 148.0f);
        textArea.setSize(608.0f, 120.0f);
        this.g.addActor(textArea);
        ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("close"), Game.i.assetManager.getLabelStyle(30), () -> {
            hide();
        });
        complexButton.setSize(251.0f, 101.0f);
        complexButton.setPosition(0.0f, -11.0f);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 50.0f, 22.0f, 48.0f, 48.0f);
        complexButton.setLabel(115.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{11.0f, 0.0f, 0.0f, 101.0f, 245.0f, 93.0f, 251.0f, 0.0f})), 0.0f, 0.0f, 251.0f, 101.0f);
        complexButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.WHITE);
        this.g.addActor(complexButton);
        ComplexButton complexButton2 = new ComplexButton(Game.i.localeManager.i18n.get("market"), Game.i.assetManager.getLabelStyle(30), () -> {
            if (Gdx.app.getType() == Application.ApplicationType.Android) {
                Gdx.f881net.openURI(Config.PLAY_STORE_URI);
            } else if (Gdx.app.getType() == Application.ApplicationType.iOS) {
                Gdx.f881net.openURI(Config.APP_STORE_URI);
            }
        });
        complexButton2.setSize(218.0f, 96.0f);
        complexButton2.setPosition(251.0f, -11.0f);
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-google-play"), 47.0f, 22.0f, 48.0f, 48.0f);
        } else if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-ios-app-store"), 47.0f, 22.0f, 48.0f, 48.0f);
        }
        complexButton2.setLabel(103.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton2.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{7.0f, 0.0f, 0.0f, 93.0f, 218.0f, 96.0f, 212.0f, 0.0f})), 0.0f, 0.0f, 218.0f, 96.0f);
        complexButton2.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, Color.WHITE);
        this.g.addActor(complexButton2);
        if (i < 4) {
            complexButton2.setVisible(false);
        }
        ComplexButton complexButton3 = new ComplexButton(Game.i.localeManager.i18n.get("send"), Game.i.assetManager.getLabelStyle(30), () -> {
            Net.HttpRequest httpRequest2 = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest2.setUrl(Config.REVIEW_URL);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("playerid", Game.i.authManager.getPlayerId());
            if (Game.i.authManager.isSignedIn()) {
                hashMap2.put("sessionid", Game.i.authManager.getSessionId());
            }
            hashMap2.put("stars", String.valueOf(i));
            if (textArea.getText().length() > 0) {
                hashMap2.put("message", textArea.getText());
            }
            httpRequest2.setContent(HttpParametersUtils.convertHttpParameters(hashMap2));
            Gdx.f881net.sendHttpRequest(httpRequest2, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.ui.components.RatingForm.4
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    RatingForm.f3382a.i("response: " + httpResponse.getResultAsString(), new Object[0]);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    RatingForm.f3382a.e("failed", th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                }
            });
            hide();
            Dialog.i().showAlert(Game.i.localeManager.i18n.get("thanks_for_feedback"));
            Game.i.ratingManager.madeReview(i, textArea.getText());
        });
        complexButton3.setSize(251.0f, 101.0f);
        complexButton3.setPosition(469.0f, -11.0f);
        complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-letter"), 50.0f, 22.0f, 48.0f, 48.0f);
        complexButton3.setLabel(115.0f, 0.0f, 100.0f, 96.0f, 8);
        complexButton3.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 7.0f, 96.0f, 251.0f, 101.0f, 240.0f, 0.0f})), 0.0f, 0.0f, 251.0f, 101.0f);
        complexButton3.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.WHITE);
        this.g.addActor(complexButton3);
        setVisible(true);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false);
    }
}
