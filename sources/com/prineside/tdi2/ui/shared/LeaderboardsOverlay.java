package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.LeaderBoardManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.TextureRegionConfig;
import com.prineside.tdi2.utils.UiUtils;
import java.util.Locale;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LeaderboardsOverlay.class */
public final class LeaderboardsOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3605a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 161, "LeaderboardsOverlay main");

    /* renamed from: b, reason: collision with root package name */
    private Group f3606b;
    private Group c;
    private Image d;
    private Label e;
    private ComplexButton f;
    private ScrollPane g;
    private Table h;
    private Label i;
    private Label j;
    private Label k;
    private Label l;
    private Image m;
    private Label n;
    private BasicLevel o;
    private ReplayManager.LeaderboardsMode p;
    private static final StringBuilder q = new StringBuilder();

    public static LeaderboardsOverlay i() {
        return (LeaderboardsOverlay) Game.i.uiManager.getComponent(LeaderboardsOverlay.class);
    }

    public LeaderboardsOverlay() {
        Group group = new Group();
        group.setTransform(false);
        this.f3605a.getTable().add((Table) group).size(710.0f, 959.0f);
        this.f3606b = new Group();
        this.f3606b.setTransform(false);
        this.f3606b.setSize(710.0f, 959.0f);
        this.f3606b.setOrigin(355.0f, 479.5f);
        group.addActor(this.f3606b);
        QuadActor quadActor = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.21f), new float[]{0.0f, 0.0f, 0.0f, 920.0f, 692.0f, 950.0f, 700.0f, 27.0f});
        quadActor.setSize(700.0f, 950.0f);
        quadActor.setPosition(25.0f, -19.0f);
        this.f3606b.addActor(quadActor);
        QuadActor quadActor2 = new QuadActor(new Color(791621631), new float[]{0.0f, 0.0f, 0.0f, 958.0f, 710.0f, 946.0f, 710.0f, 21.0f});
        quadActor2.setSize(710.0f, 958.0f);
        this.f3606b.addActor(quadActor2);
        this.d = new Image(Game.i.assetManager.getDrawable("icon-crown"));
        this.d.setSize(64.0f, 64.0f);
        this.d.setPosition(40.0f, 863.0f);
        this.f3606b.addActor(this.d);
        this.n = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.n.setSize(100.0f, 40.0f);
        this.n.setPosition(128.0f, 887.0f);
        this.f3606b.addActor(this.n);
        this.e = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.e.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.e.setSize(100.0f, 24.0f);
        this.e.setPosition(128.0f, 863.0f);
        this.f3606b.addActor(this.e);
        this.f = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            show(this.o, this.p == ReplayManager.LeaderboardsMode.score ? ReplayManager.LeaderboardsMode.waves : ReplayManager.LeaderboardsMode.score);
        });
        this.f.setPosition(533.0f, 844.0f);
        this.f.setSize(194.0f, 82.0f);
        this.f.setLabel(76.0f, 21.0f, 100.0f, 48.0f, 8);
        this.f.setIconPositioned(Game.i.assetManager.getDrawable("icon-crown"), 21.0f, 20.0f, 48.0f, 48.0f);
        this.f.setBackground(Game.i.assetManager.getDrawable("ui-leaderboard-switch-button"), 0.0f, 0.0f, 194.0f, 82.0f);
        this.f3606b.addActor(this.f);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(new Color(724249599));
        image.setSize(710.0f, 662.0f);
        image.setPosition(0.0f, 173.0f);
        this.f3606b.addActor(image);
        this.h = new Table();
        this.g = new ScrollPane(this.h);
        UiUtils.enableMouseMoveScrollFocus(this.g);
        this.g.setScrollingDisabled(true, false);
        this.g.setSize(710.0f, 662.0f);
        this.g.setPosition(0.0f, 173.0f);
        this.f3606b.addActor(this.g);
        Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image2.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        image2.setSize(710.0f, 10.0f);
        image2.setPosition(0.0f, 825.0f);
        image2.setTouchable(Touchable.disabled);
        this.f3606b.addActor(image2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image3.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        image3.setSize(710.0f, 10.0f);
        image3.setPosition(0.0f, 173.0f);
        image3.setTouchable(Touchable.disabled);
        this.f3606b.addActor(image3);
        this.m = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        this.m.setSize(64.0f, 64.0f);
        this.m.setPosition(323.0f, 726.0f);
        this.m.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.m.setOrigin(1);
        this.m.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
        this.f3606b.addActor(this.m);
        this.c = new Group();
        this.c.setTransform(false);
        this.c.setSize(710.0f, 173.0f);
        this.f3606b.addActor(this.c);
        this.l = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.l.setPosition(40.0f, 108.0f);
        this.l.setSize(100.0f, 64.0f);
        this.l.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.c.addActor(this.l);
        Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image4.setPosition(0.0f, 44.0f);
        image4.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        image4.setSize(710.0f, 64.0f);
        this.c.addActor(image4);
        this.i = new Label("1", Game.i.assetManager.getLabelStyle(24));
        this.i.setPosition(0.0f, 44.0f);
        this.i.setSize(128.0f, 64.0f);
        this.i.setAlignment(1);
        this.i.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.c.addActor(this.i);
        this.j = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.j.setPosition(128.0f, 44.0f);
        this.j.setSize(100.0f, 64.0f);
        this.c.addActor(this.j);
        this.k = new Label("1,000", Game.i.assetManager.getLabelStyle(24));
        this.k.setPosition(570.0f, 44.0f);
        this.k.setAlignment(16);
        this.k.setSize(100.0f, 64.0f);
        this.c.addActor(this.k);
        this.f3605a.getTable().setVisible(false);
    }

    public final void show(BasicLevel basicLevel, ReplayManager.LeaderboardsMode leaderboardsMode) {
        if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode()) && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.ENDLESS_LEADERBOARD_HINT_SHOWN) == 0.0d) {
            Dialog.i().showAlert(Game.i.localeManager.i18n.format("endless_leaderboard_description", StringFormatter.timePassed(MathUtils.round(2700.0f), false, false)));
            Dialog.i().makeConfirmButtonDisabled(2);
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.ENDLESS_LEADERBOARD_HINT_SHOWN, 1.0d);
        }
        Game.i.settingsManager.isUiAnimationsEnabled();
        this.n.setText(Game.i.localeManager.i18n.get("leaderboard"));
        this.o = basicLevel;
        this.p = leaderboardsMode;
        DarkOverlay.i().addCallerOverlayLayer("LeaderboardsOverlay", this.f3605a.zIndex - 1, () -> {
            hide();
            return true;
        });
        UiUtils.bouncyShowOverlay(null, this.f3605a.getTable(), this.f3606b);
        DifficultyMode difficultyMode = Game.i.progressManager.getDifficultyMode();
        if (basicLevel.forcedDifficulty != null) {
            difficultyMode = basicLevel.forcedDifficulty;
        }
        if (leaderboardsMode == ReplayManager.LeaderboardsMode.score) {
            this.d.setDrawable(Game.i.assetManager.getDrawable("icon-crown"));
            this.e.setText(basicLevel.name + " - " + Game.i.localeManager.i18n.get("score") + " - " + Game.i.progressManager.getDifficultyName(difficultyMode));
            this.f.setText(Game.i.localeManager.i18n.get("score"));
            this.f.setIcon(Game.i.assetManager.getDrawable("icon-crown"));
        } else if (leaderboardsMode == ReplayManager.LeaderboardsMode.waves) {
            this.d.setDrawable(Game.i.assetManager.getDrawable("icon-wave"));
            this.e.setText(basicLevel.name + " - " + Game.i.localeManager.i18n.get("waves") + " - " + Game.i.progressManager.getDifficultyName(difficultyMode));
            this.f.setText(Game.i.localeManager.i18n.get("waves"));
            this.f.setIcon(Game.i.assetManager.getDrawable("icon-wave"));
        }
        this.l.setText(Game.i.localeManager.i18n.get("your_rank"));
        this.h.clearChildren();
        this.m.setVisible(true);
        this.c.setVisible(false);
        Game.i.leaderBoardManager.getLeaderboards(GameStateSystem.GameMode.BASIC_LEVELS, difficultyMode, basicLevel.name, leaderboardsMode, leaderboardsResult -> {
            this.m.setVisible(false);
            if (leaderboardsResult.success) {
                this.c.setVisible(true);
                Drawable tint = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f));
                for (int i = 0; i < leaderboardsResult.entries.size; i++) {
                    final LeaderBoardManager.LeaderboardsEntry leaderboardsEntry = leaderboardsResult.entries.get(i);
                    Table table = new Table();
                    Label label = new Label(String.valueOf(leaderboardsEntry.rank), Game.i.assetManager.getLabelStyle(24));
                    label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    label.setAlignment(1);
                    table.add((Table) label).size(79.0f, 64.0f).padLeft(10.0f);
                    Group group = new Group();
                    group.setTransform(false);
                    table.add((Table) group).size(64.0f);
                    Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image.setSize(64.0f, 64.0f);
                    image.setColor(0.0f, 0.0f, 0.0f, 0.14f);
                    group.addActor(image);
                    Array<TextureRegionConfig> profileLevelTextures = Game.i.authManager.getProfileLevelTextures(leaderboardsEntry.profileLevel);
                    for (int i2 = 0; i2 < profileLevelTextures.size; i2++) {
                        group.addActor(profileLevelTextures.items[i2].createImage(8.0f, 8.0f, 48.0f));
                    }
                    Image image2 = new Image();
                    if (leaderboardsEntry.hasProfilePicture) {
                        image2.setDrawable(new TextureRegionDrawable(Game.i.assetManager.loadWebTexture(Config.AVATAR_WEB_TEXTURES_URL + leaderboardsEntry.playerId.toLowerCase(Locale.US) + "-32.png")));
                    } else {
                        image2.setDrawable(Game.i.assetManager.getDrawable("icon-user"));
                        image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    }
                    table.add((Table) image2).size(40.0f).padLeft(12.0f).padRight(12.0f);
                    Group group2 = new Group();
                    group2.setTransform(false);
                    table.add((Table) group2).size(100.0f, 64.0f);
                    Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
                    image3.setColor(0.0f, 0.0f, 0.0f, 0.14f);
                    image3.setSize(128.0f, 64.0f);
                    group2.addActor(image3);
                    Table table2 = new Table();
                    table2.setSize(300.0f, 64.0f);
                    group2.addActor(table2);
                    LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(leaderboardsEntry.nickname, 30, 24, 240.0f);
                    if (leaderboardsEntry.nickname.equals(Game.i.authManager.getNickname())) {
                        limitedWidthLabel.setColor(MaterialColor.GREEN.P500);
                    } else if (leaderboardsEntry.rank == 1) {
                        limitedWidthLabel.setColor(MaterialColor.AMBER.P500);
                    } else if (leaderboardsEntry.rank == 2) {
                        limitedWidthLabel.setColor(MaterialColor.AMBER.P300);
                    } else if (leaderboardsEntry.rank == 3) {
                        limitedWidthLabel.setColor(MaterialColor.AMBER.P100);
                    }
                    limitedWidthLabel.setTouchable(Touchable.enabled);
                    limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.LeaderboardsOverlay.1
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f, float f2) {
                            WebBrowser.i().webView.loadUrl(Net.HttpMethods.GET, Config.XDX_VIEW_PLAYER_PROFILE_URL + leaderboardsEntry.playerId, null);
                            WebBrowser.i().setVisible(true);
                            LeaderboardsOverlay.i().hide();
                        }
                    });
                    table2.add((Table) limitedWidthLabel).padLeft(16.0f);
                    table2.add().height(1.0f).expandX().fillX();
                    if (leaderboardsEntry.badgeIconTexture != null) {
                        Group group3 = new Group();
                        group3.setTransform(false);
                        table2.add((Table) group3).padLeft(16.0f).size(40.0f);
                        TextureRegion textureRegionSetThrowing = Game.i.assetManager.getTextureRegionSetThrowing(leaderboardsEntry.badgeIconTexture, false);
                        TextureRegion textureRegion = textureRegionSetThrowing;
                        if (textureRegionSetThrowing == null) {
                            textureRegion = Game.i.assetManager.loadWebTexture(Config.OPTIONAL_WEB_TEXTURES_URL + leaderboardsEntry.badgeIconTexture + ".png");
                        }
                        Image image4 = new Image(new TextureRegionDrawable(textureRegion));
                        image4.setColor(leaderboardsEntry.badgeIconColor == null ? Color.WHITE : leaderboardsEntry.badgeIconColor);
                        image4.setSize(40.0f, 40.0f);
                        group3.addActor(image4);
                        if (leaderboardsEntry.badgeOverlayTexture != null) {
                            TextureRegion textureRegionSetThrowing2 = Game.i.assetManager.getTextureRegionSetThrowing(leaderboardsEntry.badgeOverlayTexture, false);
                            TextureRegion textureRegion2 = textureRegionSetThrowing2;
                            if (textureRegionSetThrowing2 == null) {
                                textureRegion2 = Game.i.assetManager.loadWebTexture(Config.OPTIONAL_WEB_TEXTURES_URL + leaderboardsEntry.badgeOverlayTexture + ".png");
                            }
                            Image image5 = new Image(new TextureRegionDrawable(textureRegion2));
                            image5.setColor(leaderboardsEntry.badgeOverlayColor == null ? Color.WHITE : leaderboardsEntry.badgeOverlayColor);
                            image5.setSize(40.0f, 40.0f);
                            group3.addActor(image5);
                        }
                    }
                    table.add().growX().height(1.0f);
                    Label label2 = new Label(StringFormatter.commaSeparatedNumber(leaderboardsEntry.score), Game.i.assetManager.getLabelStyle(24));
                    label2.setAlignment(16);
                    table.add((Table) label2).size(100.0f, 64.0f).padRight(40.0f);
                    if (i % 2 == 0) {
                        table.setBackground(tint);
                    }
                    this.h.add(table).size(710.0f, 64.0f).row();
                }
                if (leaderboardsResult.entries.size * 64.0f < this.g.getHeight()) {
                    this.h.add().size(1.0f, this.g.getHeight() - (leaderboardsResult.entries.size * 64.0f)).row();
                }
                if (Game.i.authManager.isSignedIn() && leaderboardsResult.player != null) {
                    this.j.setText(Game.i.authManager.getNickname());
                    if (leaderboardsResult.player.rank == 0) {
                        this.i.setText(TypeDescription.Generic.OfWildcardType.SYMBOL);
                        this.k.setText(Game.i.localeManager.i18n.get("not_ranked"));
                        return;
                    }
                    this.i.setText(String.valueOf(leaderboardsResult.player.rank));
                    this.k.setText(StringFormatter.commaSeparatedNumber(leaderboardsResult.player.score));
                    if (leaderboardsResult.player.rank == 1) {
                        this.l.setText(Game.i.localeManager.i18n.get("your_rank") + " - " + Game.i.localeManager.i18n.get("leader") + "!");
                        return;
                    }
                    int ceil = MathUtils.ceil((leaderboardsResult.player.rank / leaderboardsResult.player.total) * 100.0f);
                    q.setLength(0);
                    q.append(Game.i.localeManager.i18n.get("your_rank")).append(" - ").append(Game.i.localeManager.i18n.format("top_percent", Integer.valueOf(ceil)));
                    this.l.setText(q);
                    return;
                }
                this.i.setText("???");
                this.j.setText(Game.i.localeManager.i18n.get("sign_in_to_get_ranked"));
                this.k.setText(StringFormatter.commaSeparatedNumber(leaderboardsMode == ReplayManager.LeaderboardsMode.score ? ProgressPrefs.i().basicLevel.getLevelMaxScore(basicLevel.name) : ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(basicLevel.name)));
                return;
            }
            Notifications.i().add(Game.i.localeManager.i18n.get("failed_to_load_leaderboard"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
        });
        Game.i.uiManager.stage.setScrollFocus(this.g);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        Game.i.settingsManager.isUiAnimationsEnabled();
        DarkOverlay.i().removeCaller("LeaderboardsOverlay");
        UiUtils.bouncyHideOverlay(null, this.f3605a.getTable(), this.f3606b);
    }
}
