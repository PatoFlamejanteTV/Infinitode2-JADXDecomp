package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.WebView;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/WebBrowser.class */
public final class WebBrowser implements Disposable, UiManager.UiComponent {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3767a = TLog.forClass(WebBrowser.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3768b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 149, "WebBrowser main");
    private Group c;
    private ScrollPane d;
    private Table e;
    private Image f;
    private Group g;
    private Group h;
    private WebView i;
    public WebView webView;

    public static WebBrowser i() {
        return (WebBrowser) Game.i.uiManager.getComponent(WebBrowser.class);
    }

    public WebBrowser() {
        final int scaledViewportHeight = (Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT) + 960;
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(520.0f, scaledViewportHeight * 0.5f);
        this.f3768b.getTable().add((Table) group).size(1040.0f, scaledViewportHeight);
        this.c = new Group();
        this.c.setTransform(false);
        this.c.setOrigin(520.0f, scaledViewportHeight * 0.5f);
        this.c.setSize(1040.0f, scaledViewportHeight);
        group.addActor(this.c);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(new Color(555819519));
        image.setSize(1040.0f, scaledViewportHeight);
        this.c.addActor(image);
        QuadActor quadActor = new QuadActor(new Color(555819519), new float[]{0.0f, 0.0f, 0.0f, 12.0f, 1040.0f, 12.0f, 1040.0f, 12.0f});
        quadActor.setPosition(0.0f, -12.0f);
        this.c.addActor(quadActor);
        this.e = new Table();
        this.e.setTouchable(Touchable.childrenOnly);
        this.d = new ScrollPane(this.e);
        UiUtils.enableMouseMoveScrollFocus(this.d);
        this.d.setTransform(true);
        this.d.setSize(1040.0f, scaledViewportHeight);
        this.d.setTouchable(Touchable.enabled);
        this.c.addActor(this.d);
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setSize(1040.0f, scaledViewportHeight);
        this.g.setVisible(false);
        this.c.addActor(this.g);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setSize(1040.0f, scaledViewportHeight);
        group2.setTouchable(Touchable.enabled);
        group2.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.WebBrowser.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                WebBrowser.this.b();
            }
        });
        this.g.addActor(group2);
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(new Color(64));
        image2.setSize(1040.0f, scaledViewportHeight);
        group2.addActor(image2);
        QuadActor quadActor2 = new QuadActor(new Color(64), new float[]{0.0f, 0.0f, 0.0f, 12.0f, 1040.0f, 12.0f, 1040.0f, 12.0f});
        quadActor2.setPosition(0.0f, -12.0f);
        group2.addActor(quadActor2);
        this.h = new Group();
        this.h.setTransform(false);
        this.h.setSize(1040.0f, scaledViewportHeight);
        this.h.setTouchable(Touchable.childrenOnly);
        this.g.addActor(this.h);
        this.i = new WebView();
        this.i.addListener(new WebView.WebViewListener() { // from class: com.prineside.tdi2.ui.shared.WebBrowser.2
            @Override // com.prineside.tdi2.ui.actors.WebView.WebViewListener
            public void urlLoadStart(Net.HttpRequest httpRequest) {
                WebBrowser.f3767a.i("modal urlLoadStart", new Object[0]);
                WebBrowser.this.g.clearActions();
                WebBrowser.this.g.addAction(Actions.sequence(Actions.show(), Actions.fadeIn(0.2f)));
                WebBrowser.this.h.clear();
                Image image3 = new Image(Game.i.assetManager.getDrawable("loading-icon"));
                image3.setColor(1.0f, 1.0f, 1.0f, 0.0f);
                image3.setTouchable(Touchable.disabled);
                image3.setOrigin(24.0f, 24.0f);
                image3.setPosition(496.0f, (scaledViewportHeight * 0.5f) - 24.0f);
                image3.setSize(48.0f, 48.0f);
                WebBrowser.this.h.addActor(image3);
            }

            @Override // com.prineside.tdi2.ui.actors.WebView.WebViewListener
            public void urlLoadFinish(boolean z, String str, boolean z2) {
                WebBrowser.f3767a.i("modal urlLoadFinish " + z + SequenceUtils.SPACE + str, new Object[0]);
                WebBrowser.this.h.clear();
                if (z) {
                    if (WebBrowser.this.i.pageWidth <= 0 || WebBrowser.this.i.pageHeight <= 0) {
                        WebBrowser.f3767a.e("modal size is unknown: " + WebBrowser.this.i.pageWidth + SequenceUtils.SPACE + WebBrowser.this.i.pageHeight, new Object[0]);
                        WebBrowser.this.b();
                        return;
                    }
                    Group group3 = new Group();
                    group3.setTransform(false);
                    group3.setSize(WebBrowser.this.i.pageWidth + 80.0f, WebBrowser.this.i.pageHeight + 80.0f);
                    group3.setPosition(520.0f - ((WebBrowser.this.i.pageWidth + 80.0f) * 0.5f), (scaledViewportHeight * 0.5f) - ((WebBrowser.this.i.pageHeight + 80.0f) * 0.5f));
                    WebBrowser.this.h.addActor(group3);
                    Group group4 = new Group();
                    group4.setTransform(false);
                    group4.setOrigin((WebBrowser.this.i.pageWidth + 80.0f) * 0.5f, (WebBrowser.this.i.pageHeight + 80.0f) * 0.5f);
                    group4.setSize(WebBrowser.this.i.pageWidth + 80.0f, WebBrowser.this.i.pageHeight + 80.0f);
                    group3.addActor(group4);
                    UiUtils.bouncyShowOverlay(null, null, group4);
                    QuadActor quadActor3 = new QuadActor(new Color(48), new float[]{0.0f, 0.0f, 6.0f, WebBrowser.this.i.pageHeight + 80.0f, WebBrowser.this.i.pageWidth + 80.0f, (WebBrowser.this.i.pageHeight + 80.0f) - 6.0f, (WebBrowser.this.i.pageWidth + 80.0f) - 6.0f, 6.0f});
                    quadActor3.setPosition(12.0f, -8.0f);
                    group4.addActor(quadActor3);
                    group4.addActor(new QuadActor(new Color(606348543), new float[]{0.0f, 0.0f, 6.0f, WebBrowser.this.i.pageHeight + 80.0f, WebBrowser.this.i.pageWidth + 80.0f, (WebBrowser.this.i.pageHeight + 80.0f) - 6.0f, (WebBrowser.this.i.pageWidth + 80.0f) - 6.0f, 6.0f}));
                    WebBrowser.this.i.setSize(WebBrowser.this.i.pageWidth, WebBrowser.this.i.pageHeight);
                    WebBrowser.this.i.setPosition(40.0f, 40.0f);
                    group4.addActor(WebBrowser.this.i);
                    PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
                        WebBrowser.this.b();
                    }, MaterialColor.ORANGE.P500, MaterialColor.ORANGE.P400, MaterialColor.ORANGE.P600);
                    paddedImageButton.setIconSize(48.0f, 48.0f);
                    paddedImageButton.setIconPosition(16.0f, 16.0f);
                    paddedImageButton.setSize(64.0f, 64.0f);
                    paddedImageButton.setPosition(WebBrowser.this.i.pageWidth + 40, WebBrowser.this.i.pageHeight + 40);
                    group4.addActor(paddedImageButton);
                    return;
                }
                WebBrowser.f3767a.e("modal loading is not successful", new Object[0]);
                WebBrowser.this.b();
            }

            @Override // com.prineside.tdi2.ui.actors.WebView.WebViewListener
            public void modalLoadRequested(String str) {
                WebBrowser.this.i.loadUrl(Net.HttpMethods.GET, str, null);
            }
        });
        this.webView = new WebView();
        this.e.add().width(1.0f).height(32.0f).row();
        this.e.add().width(40.0f);
        this.e.add(this.webView).width(960.0f).expandY().fillY();
        this.e.add().width(40.0f).row();
        this.e.add().width(1.0f).height(32.0f).row();
        this.f = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        this.f.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        this.f.setTouchable(Touchable.disabled);
        this.f.setOrigin(24.0f, 24.0f);
        this.f.setPosition(496.0f, (scaledViewportHeight * 0.5f) - 24.0f);
        this.f.setSize(48.0f, 48.0f);
        this.c.addActor(this.f);
        this.webView.addListener(new WebView.WebViewListener() { // from class: com.prineside.tdi2.ui.shared.WebBrowser.3
            @Override // com.prineside.tdi2.ui.actors.WebView.WebViewListener
            public void urlLoadStart(Net.HttpRequest httpRequest) {
                WebBrowser.this.d.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                WebBrowser.this.d.setTouchable(Touchable.disabled);
                WebBrowser.this.f.setDrawable(Game.i.assetManager.getDrawable("loading-icon"));
                WebBrowser.this.f.clearActions();
                WebBrowser.this.f.addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(0.2f), Actions.forever(Actions.rotateBy(90.0f, 0.5f)))));
            }

            @Override // com.prineside.tdi2.ui.actors.WebView.WebViewListener
            public void urlLoadFinish(boolean z, String str, boolean z2) {
                WebBrowser.this.d.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                WebBrowser.this.d.setTouchable(Touchable.enabled);
                if (z) {
                    WebBrowser.this.f.clearActions();
                    WebBrowser.this.f.addAction(Actions.fadeOut(0.2f));
                } else {
                    WebBrowser.this.f.setDrawable(Game.i.assetManager.getDrawable("icon-times"));
                    WebBrowser.this.f.clearActions();
                    WebBrowser.this.f.addAction(Actions.sequence(Actions.rotateTo(0.0f), Actions.fadeIn(0.2f), Actions.delay(0.5f), Actions.fadeOut(0.2f)));
                }
            }

            @Override // com.prineside.tdi2.ui.actors.WebView.WebViewListener
            public void modalLoadRequested(String str) {
                WebBrowser.this.i.loadUrl(Net.HttpMethods.GET, str, null);
            }
        });
        PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
            setVisible(false);
        }, MaterialColor.ORANGE.P500, MaterialColor.ORANGE.P400, MaterialColor.ORANGE.P600);
        paddedImageButton.setIconSize(48.0f, 48.0f);
        paddedImageButton.setIconPosition(16.0f, 16.0f);
        paddedImageButton.setSize(64.0f, 64.0f);
        paddedImageButton.setPosition(1008.0f, scaledViewportHeight - 32.0f);
        paddedImageButton.setName("web_browser_close_button");
        this.c.addActor(paddedImageButton);
        this.f3768b.getTable().setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        f3767a.i("hideModal", new Object[0]);
        this.g.clearActions();
        this.g.addAction(Actions.sequence(Actions.fadeOut(0.2f), Actions.hide()));
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false);
        b();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final boolean isPersistent() {
        return false;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void preRender(float f) {
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f) {
    }

    public final void setVisible(boolean z) {
        if (z) {
            DarkOverlay.i().addCallerOverlayLayer("WebBrowser", this.f3768b.zIndex - 1, () -> {
                hide();
                return true;
            });
            UiUtils.bouncyShowOverlay(null, this.f3768b.getTable(), this.c);
        } else {
            DarkOverlay.i().removeCaller("WebBrowser");
            UiUtils.bouncyHideOverlay(null, this.f3768b.getTable(), this.c);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        Game.i.uiManager.removeLayer(this.f3768b);
    }
}
