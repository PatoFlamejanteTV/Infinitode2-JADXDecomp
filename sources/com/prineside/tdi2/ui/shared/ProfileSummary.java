package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Auth;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.screens.account.AccountScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.TextureRegionConfig;
import com.prineside.tdi2.utils.logging.TLog;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    */
/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary.class */
public class ProfileSummary extends UiManager.UiComponent.Adapter {

    /* renamed from: b */
    private int f3726b;
    private int c;
    private final Group d;
    public final Image avatar;
    private final Image e;
    private final LimitedWidthLabel f;
    private final Image g;
    private final Actor h;
    public final Label hintLabel;
    private final Label i;
    private final Group j;
    private final Image k;
    private final Group l;
    private final Image m;
    private final Group n;
    private final Image o;
    private final Label p;
    private final Image q;
    private final Label r;
    private final Table s;
    private Label t;
    private AnimationStage u;
    private boolean x;
    private boolean y;
    private int z;

    /* renamed from: a */
    private static final TLog f3725a = TLog.forClass(ProfileSummary.class);
    private static final StringBuilder w = new StringBuilder();
    public final UiManager.UiLayer uiLayer = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 100, "ProfileSummary");
    private final Array<AnimationStage> v = new Array<>(true, 1, AnimationStage.class);

    static {
    }

    public static ProfileSummary i() {
        return (ProfileSummary) Game.i.uiManager.getComponent(ProfileSummary.class);
    }

    public ProfileSummary() {
        Table table = this.uiLayer.getTable();
        Group group = new Group();
        group.setTransform(false);
        table.add((Table) group).expand().top().left().size(226.0f, 100.0f).padTop(44.0f).padLeft(40.0f);
        this.d = new Group();
        this.d.setTransform(false);
        this.d.setSize(226.0f, 100.0f);
        group.addActor(this.d);
        this.n = new Group();
        this.n.setTransform(false);
        this.n.setTouchable(Touchable.disabled);
        this.n.setSize(226.0f, 100.0f);
        group.addActor(this.n);
        this.avatar = new Image();
        this.avatar.setSize(48.0f, 48.0f);
        this.avatar.setPosition(0.0f, 52.0f);
        this.d.addActor(this.avatar);
        this.e = new Image(Game.i.assetManager.getDrawable("player-profile-avatar-frame"));
        this.e.setSize(54.0f, 54.0f);
        this.e.setPosition(-3.0f, 49.0f);
        this.e.setColor(new Color(269488383));
        this.d.addActor(this.e);
        Table table2 = new Table();
        table2.setSize(420.0f, 48.0f);
        table2.setPosition(64.0f, 52.0f);
        this.d.addActor(table2);
        this.f = new LimitedWidthLabel("", 30, 24, 380.0f);
        table2.add((Table) this.f).maxWidth(380.0f).height(48.0f).padRight(8.0f);
        this.g = new Image(Game.i.assetManager.getQuad("icons.cloudSyncWarning"));
        this.g.addAction(Actions.forever(Actions.sequence(Actions.color(MaterialColor.YELLOW.P500, 0.4f), Actions.color(MaterialColor.AMBER.P800, 0.8f))));
        this.g.setTouchable(Touchable.enabled);
        this.g.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.ProfileSummary.1
            AnonymousClass1() {
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                TooltipsOverlay.i().showText("profile_summary_cloud_sync_warning", ProfileSummary.this.g, Game.i.localeManager.i18n.get("profile_summary_cloud_sync_warning_auto_saves_off"), UiManager.MainUiLayer.SHARED_COMPONENTS, 101, 4);
            }
        });
        Game.i.cursorGraphics.setActorCustomMouseCursorConditional(this.g, () -> {
            return Cursor.SystemCursor.Hand;
        });
        this.g.setVisible(false);
        table2.add((Table) this.g).size(48.0f);
        table2.add().height(1.0f).growX();
        this.l = new Group();
        this.l.setTransform(false);
        this.l.setSize(226.0f, 48.0f);
        this.l.setTouchable(Touchable.enabled);
        this.l.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.ProfileSummary.2
            AnonymousClass2() {
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                ProfileSummary.this.toggleXpInfoDropdown();
            }
        });
        this.d.addActor(this.l);
        Game.i.cursorGraphics.setActorCustomMouseCursorConditional(this.l, () -> {
            return Cursor.SystemCursor.Hand;
        });
        this.j = new Group();
        this.j.setTransform(false);
        this.j.setSize(48.0f, 48.0f);
        this.j.setOrigin(24.0f, 24.0f);
        this.l.addActor(this.j);
        this.i = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.i.setPosition(64.0f, 24.0f);
        this.i.setSize(100.0f, 16.0f);
        this.i.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.l.addActor(this.i);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setPosition(64.0f, 10.0f);
        group2.setSize(162.0f, 8.0f);
        this.l.addActor(group2);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(162.0f, 8.0f);
        image.setColor(1.0f, 1.0f, 1.0f, 0.21f);
        group2.addActor(image);
        this.m = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        group2.addActor(this.m);
        this.o = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.o.setColor(MaterialColor.AMBER.P500);
        this.o.setSize(0.0f, 8.0f);
        group2.addActor(this.o);
        this.q = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.q.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.q.setSize(0.0f, 8.0f);
        group2.addActor(this.q);
        this.p = new Label("+0 XP", Game.i.assetManager.getLabelStyle(21));
        this.p.setSize(100.0f, 16.0f);
        this.p.setPosition(242.0f, 11.0f);
        this.p.setColor(MaterialColor.AMBER.P500);
        this.n.addActor(this.p);
        this.r = new Label("+0 bonus XP", Game.i.assetManager.getLabelStyle(21));
        this.r.setSize(100.0f, 16.0f);
        this.r.setPosition(242.0f, 11.0f);
        this.r.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.n.addActor(this.r);
        this.k = new Image();
        this.k.setPosition(207.0f, 22.0f);
        this.k.setSize(20.0f, 20.0f);
        this.l.addActor(this.k);
        this.h = new Actor();
        this.h.setPosition(-40.0f, 48.0f);
        this.h.setSize(266.0f, 92.0f);
        this.h.setTouchable(Touchable.enabled);
        this.h.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.ProfileSummary.3
            AnonymousClass3(ProfileSummary this) {
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                AccountScreen.goToScreen();
            }
        });
        this.d.addActor(this.h);
        Game.i.cursorGraphics.setActorCustomMouseCursorConditional(this.h, () -> {
            return Cursor.SystemCursor.Hand;
        });
        this.hintLabel = new Label(Game.i.localeManager.i18n.get("tap_here_to_sign_in"), Game.i.assetManager.getLabelStyle(21));
        this.hintLabel.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.hintLabel.setSize(100.0f, 16.0f);
        this.hintLabel.setPosition(64.0f, 40.0f);
        this.d.addActor(this.hintLabel);
        this.s = new Table();
        this.s.setPosition(64.0f, -140.0f);
        this.s.setSize(386.0f, 140.0f);
        this.s.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(128)));
        this.s.setTouchable(Touchable.enabled);
        this.s.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.ProfileSummary.4
            AnonymousClass4() {
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                ProfileSummary.this.toggleXpInfoDropdown();
            }
        });
        this.d.addActor(this.s);
        setVisible(false);
        Game.i.authManager.addListener(new AuthManager.AuthManagerListener.AuthManagerListenerAdapter() { // from class: com.prineside.tdi2.ui.shared.ProfileSummary.5
            AnonymousClass5() {
            }

            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void stateUpdated() {
                if (ProfileSummary.this.x && ProfileSummary.this.v.size == 0 && ProfileSummary.this.u == null) {
                    ProfileSummary.this.update();
                }
            }
        });
    }

    /* renamed from: com.prineside.tdi2.ui.shared.ProfileSummary$1 */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$1.class */
    class AnonymousClass1 extends ClickListener {
        AnonymousClass1() {
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            TooltipsOverlay.i().showText("profile_summary_cloud_sync_warning", ProfileSummary.this.g, Game.i.localeManager.i18n.get("profile_summary_cloud_sync_warning_auto_saves_off"), UiManager.MainUiLayer.SHARED_COMPONENTS, 101, 4);
        }
    }

    /* renamed from: com.prineside.tdi2.ui.shared.ProfileSummary$2 */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$2.class */
    class AnonymousClass2 extends ClickListener {
        AnonymousClass2() {
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            ProfileSummary.this.toggleXpInfoDropdown();
        }
    }

    /* renamed from: com.prineside.tdi2.ui.shared.ProfileSummary$3 */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$3.class */
    class AnonymousClass3 extends ClickListener {
        AnonymousClass3(ProfileSummary this) {
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            AccountScreen.goToScreen();
        }
    }

    /* renamed from: com.prineside.tdi2.ui.shared.ProfileSummary$4 */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$4.class */
    class AnonymousClass4 extends ClickListener {
        AnonymousClass4() {
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            ProfileSummary.this.toggleXpInfoDropdown();
        }
    }

    /* renamed from: com.prineside.tdi2.ui.shared.ProfileSummary$5 */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$5.class */
    class AnonymousClass5 extends AuthManager.AuthManagerListener.AuthManagerListenerAdapter {
        AnonymousClass5() {
        }

        @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
        public void stateUpdated() {
            if (ProfileSummary.this.x && ProfileSummary.this.v.size == 0 && ProfileSummary.this.u == null) {
                ProfileSummary.this.update();
            }
        }
    }

    public void toggleXpInfoDropdown() {
        this.s.setVisible(!this.s.isVisible());
        b();
    }

    private void b() {
        this.z = Game.getTimestampSeconds();
        if (this.t != null) {
            int timestampSeconds = Game.i.authManager.getSessionData().nextXpRefreshTimestamp - Game.getTimestampSeconds();
            int i = timestampSeconds;
            if (timestampSeconds < 0) {
                i = 0;
            }
            this.t.setText(StringFormatter.timePassed(i, true, true));
        }
    }

    public void update() {
        if (this.y) {
            f3725a.i("skipped update during animation", new Object[0]);
            return;
        }
        if (Game.i.authManager.isSignedIn() && !Game.i.authManager.isAutoSavesEnabled()) {
            this.g.setVisible(true);
        } else {
            this.g.setVisible(false);
        }
        this.f.setText(Game.i.authManager.getNickname());
        if (Game.i.authManager.isSignedIn()) {
            this.avatar.setVisible(true);
            this.f.setVisible(true);
            SP_Auth.SessionData sessionData = Game.i.authManager.getSessionData();
            this.f3726b = sessionData.profileLevel;
            this.c = sessionData.currentLevelXp;
            this.avatar.setDrawable(new TextureRegionDrawable(Game.i.authManager.getAvatar(64)));
            this.e.setVisible(true);
            this.hintLabel.setVisible(false);
            this.l.setVisible(true);
            this.j.clear();
            Array<TextureRegionConfig> profileLevelTextures = Game.i.authManager.getProfileLevelTextures(this.f3726b);
            for (int i = 0; i < profileLevelTextures.size; i++) {
                this.j.addActor(profileLevelTextures.items[i].createImage(0.0f, 0.0f, 48.0f));
            }
            this.i.setText(this.c + " / " + sessionData.nextLevelXp + " XP");
            this.m.setVisible(true);
            this.m.setColor(Color.WHITE);
            this.m.setSize((int) ((162.0f * this.c) / sessionData.nextLevelXp), 8.0f);
            if (sessionData.xpStatus == AuthManager.XpStatus.BONUS) {
                this.k.setDrawable(Game.i.assetManager.getDrawable("icon-triangle-top"));
                this.k.setColor(MaterialColor.LIGHT_GREEN.P500);
            } else if (sessionData.xpStatus == AuthManager.XpStatus.REDUCED) {
                this.k.setDrawable(Game.i.assetManager.getDrawable("icon-triangle-bottom"));
                this.k.setColor(new Color(1.0f, 1.0f, 1.0f, 0.28f));
            } else {
                this.k.setVisible(false);
            }
            this.s.clearChildren();
            Label label = new Label(Game.i.localeManager.i18n.format("profile_summary_dropdown_bonus_xp_available", Integer.valueOf(sessionData.bonusXpRemaining)), Game.i.assetManager.getLabelStyle(21));
            label.setAlignment(8);
            this.s.add((Table) label).expandX().fillX().padLeft(16.0f).padRight(16.0f).row();
            Label label2 = new Label(Game.i.localeManager.i18n.format("profile_summary_dropdown_regular_xp_available", Integer.valueOf(sessionData.regularXpRemaining)), Game.i.assetManager.getLabelStyle(21));
            label2.setAlignment(8);
            this.s.add((Table) label2).expandX().fillX().padLeft(16.0f).padRight(16.0f).row();
            if (sessionData.bonusXpRemaining == 0 && sessionData.regularXpRemaining == 0) {
                Label label3 = new Label(Game.i.localeManager.i18n.get("profile_summary_dropdown_xp_reduced"), Game.i.assetManager.getLabelStyle(21));
                label3.setAlignment(8);
                label3.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
                this.s.add((Table) label3).expandX().fillX().padLeft(16.0f).padRight(16.0f).row();
            }
            if (sessionData.tempXp > 0) {
                Label label4 = new Label("", Game.i.assetManager.getLabelStyle(21));
                label4.setText(Game.i.localeManager.i18n.format("profile_summary_dropdown_validated_xp", Integer.valueOf(sessionData.tempXp)));
                label4.setAlignment(8);
                label4.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
                this.s.add((Table) label4).expandX().fillX().padLeft(16.0f).padRight(16.0f).row();
            }
            Table table = new Table();
            Label label5 = new Label(Game.i.localeManager.i18n.get("profile_summary_dropdown_next_refresh") + ":", Game.i.assetManager.getLabelStyle(21));
            table.add((Table) label5);
            label5.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
            this.t = new Label("1d 00:00:00", Game.i.assetManager.getLabelStyle(21));
            table.add((Table) this.t).padLeft(8.0f);
            table.add().height(1.0f).expandX().fillX();
            this.s.add(table).expandX().fillX().padLeft(16.0f).padRight(16.0f);
            return;
        }
        this.avatar.setDrawable(Game.i.assetManager.getDrawable("icon-user"));
        this.e.setVisible(false);
        this.hintLabel.setVisible(true);
        this.l.setVisible(false);
    }

    public void showXpGained(int i, int i2, boolean z, boolean z2) {
        if (i <= 0) {
            f3725a.i("no regular xp, cancel", new Object[0]);
            return;
        }
        if (z) {
            this.k.setDrawable(Game.i.assetManager.getDrawable("icon-triangle-top"));
            this.k.setColor(MaterialColor.LIGHT_GREEN.P500);
        } else if (!z2) {
            this.k.setDrawable(Game.i.assetManager.getDrawable("icon-triangle-bottom"));
            this.k.setColor(new Color(1.0f, 1.0f, 1.0f, 0.28f));
        } else {
            this.k.setVisible(false);
        }
        this.p.setPosition(242.0f, 11.0f);
        this.n.setVisible(true);
        this.p.setVisible(false);
        this.r.setVisible(false);
        this.uiLayer.getTable().setVisible(true);
        this.x = true;
        this.y = true;
        this.d.clearActions();
        this.d.setTransform(true);
        this.d.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.13f), Actions.scaleBy(1.0f, 0.0f, 0.4f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.4f, Interpolation.swingOut)), Actions.run(() -> {
            this.d.setTransform(false);
            int i3 = this.f3726b;
            int i4 = Game.i.authManager.getSessionData().nextLevelXp;
            int i5 = this.c;
            int i6 = 0;
            boolean z3 = false;
            int i7 = i;
            int i8 = i2;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                if (i7 > 0 || i8 > 0) {
                    if (i7 > 0) {
                        if (i5 + i7 < i4) {
                            AnimationGiveXpStage animationGiveXpStage = new AnimationGiveXpStage();
                            animationGiveXpStage.baseXpLine = i5;
                            animationGiveXpStage.startRegularXpLine = 0;
                            animationGiveXpStage.endRegularXpLine = i7;
                            animationGiveXpStage.startBaseXpLabel = i5;
                            animationGiveXpStage.endBaseXpLabel = i5 + i7;
                            animationGiveXpStage.startRegularXpLabel = i9;
                            animationGiveXpStage.endRegularXpLabel = i9 + i7;
                            i6 = i7;
                            this.v.add(animationGiveXpStage);
                            i9 += i7;
                            i7 = 0;
                        } else {
                            int i11 = i4 - i5;
                            AnimationGiveXpStage animationGiveXpStage2 = new AnimationGiveXpStage();
                            animationGiveXpStage2.baseXpLine = i5;
                            animationGiveXpStage2.startRegularXpLine = 0;
                            animationGiveXpStage2.endRegularXpLine = i11;
                            animationGiveXpStage2.startBaseXpLabel = i5;
                            animationGiveXpStage2.endBaseXpLabel = i5 + i11;
                            animationGiveXpStage2.startRegularXpLabel = i9;
                            animationGiveXpStage2.endRegularXpLabel = i9 + i11;
                            i6 = i11;
                            i5 = 0;
                            this.v.add(animationGiveXpStage2);
                            AnimationLevelUpStage animationLevelUpStage = new AnimationLevelUpStage();
                            i3++;
                            if (i3 > Game.i.authManager.getSessionData().maxProfileLevel) {
                                i3 = Game.i.authManager.getSessionData().maxProfileLevel;
                            }
                            animationLevelUpStage.newLevel = i3;
                            this.v.add(animationLevelUpStage);
                            i9 += i11;
                            i7 -= i11;
                        }
                    } else if (i5 + i6 + i8 < i4) {
                        AnimationGiveXpStage animationGiveXpStage3 = new AnimationGiveXpStage();
                        animationGiveXpStage3.baseXpLine = i5;
                        animationGiveXpStage3.startRegularXpLine = i6;
                        animationGiveXpStage3.endRegularXpLine = i6;
                        animationGiveXpStage3.startBonusXpLine = 0;
                        animationGiveXpStage3.endBonusXpLine = i8;
                        animationGiveXpStage3.startBaseXpLabel = i5 + i6;
                        animationGiveXpStage3.endBaseXpLabel = i5 + i6 + i8;
                        animationGiveXpStage3.startRegularXpLabel = i9;
                        animationGiveXpStage3.endRegularXpLabel = i9;
                        animationGiveXpStage3.startBonusXpLabel = i10;
                        animationGiveXpStage3.endBonusXpLabel = i10 + i8;
                        if (!z3) {
                            animationGiveXpStage3.fadeInBonus = true;
                            z3 = true;
                        }
                        this.v.add(animationGiveXpStage3);
                        i10 += i8;
                        i8 = 0;
                    } else {
                        int i12 = (i4 - i5) - i6;
                        AnimationGiveXpStage animationGiveXpStage4 = new AnimationGiveXpStage();
                        animationGiveXpStage4.baseXpLine = i5;
                        animationGiveXpStage4.startRegularXpLine = i6;
                        animationGiveXpStage4.endRegularXpLine = i6;
                        animationGiveXpStage4.startBonusXpLine = 0;
                        animationGiveXpStage4.endBonusXpLine = i12;
                        animationGiveXpStage4.startBaseXpLabel = i5 + i6;
                        animationGiveXpStage4.endBaseXpLabel = i5 + i6 + i12;
                        animationGiveXpStage4.startRegularXpLabel = i9;
                        animationGiveXpStage4.endRegularXpLabel = i9;
                        animationGiveXpStage4.startBonusXpLabel = i10;
                        animationGiveXpStage4.endBonusXpLabel = i10 + i12;
                        i6 = 0;
                        i5 = 0;
                        if (!z3) {
                            animationGiveXpStage4.fadeInBonus = true;
                            z3 = true;
                        }
                        this.v.add(animationGiveXpStage4);
                        AnimationLevelUpStage animationLevelUpStage2 = new AnimationLevelUpStage();
                        i3++;
                        if (i3 > Game.i.authManager.getSessionData().maxProfileLevel) {
                            i3 = Game.i.authManager.getSessionData().maxProfileLevel;
                        }
                        animationLevelUpStage2.newLevel = i3;
                        this.v.add(animationLevelUpStage2);
                        i10 += i12;
                        i8 -= i12;
                    }
                } else {
                    return;
                }
            }
        })));
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public void postRender(float f) {
        if (this.x) {
            if (this.u == null && this.v.size != 0) {
                this.u = this.v.removeIndex(0);
                this.u.start();
            }
            if (this.u != null) {
                AnimationStage.a(this.u, (int) (f * 1000000.0f));
                if (this.u.f3733a >= this.u.duration) {
                    AnimationStage.b(this.u, this.u.duration);
                }
                this.u.draw(Interpolation.pow2Out.apply(((float) this.u.f3733a) / ((float) this.u.duration)));
                if (this.u.f3733a == this.u.duration) {
                    this.u = null;
                }
            }
            if (this.s.isVisible() && this.z != Game.getTimestampSeconds()) {
                b();
            }
        }
    }

    public ProfileSummary setVisible(boolean z) {
        return setVisibleClickEnabled(z, true);
    }

    public ProfileSummary setVisibleClickEnabled(boolean z, boolean z2) {
        this.d.clearActions();
        this.d.addAction(Actions.scaleTo(1.0f, 1.0f));
        this.uiLayer.getTable().setVisible(z);
        if (z) {
            if (Game.getTimestampSeconds() - Game.i.authManager.lastStateUpdateTimestamp > 30) {
                Game.i.authManager.loadStateFromServer(null, null);
            }
            if (!this.x) {
                update();
            }
        } else {
            this.y = false;
            this.u = null;
            this.v.clear();
            this.n.setVisible(false);
            this.o.setVisible(false);
            this.q.setVisible(false);
            this.s.setVisible(false);
        }
        this.x = z;
        this.h.setVisible(z2);
        return this;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$AnimationStage.class */
    public static abstract class AnimationStage {

        /* renamed from: a */
        private long f3733a;
        public long duration;

        /*  JADX ERROR: Failed to decode insn: 0x0007: MOVE_MULTI, method: com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage.a(com.prineside.tdi2.ui.shared.ProfileSummary$AnimationStage, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long a(com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage r6, long r7) {
            /*
                r0 = r6
                r1 = r0
                long r1 = r1.f3733a
                r2 = r7
                long r1 = r1 + r2
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.f3733a = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage.a(com.prineside.tdi2.ui.shared.ProfileSummary$AnimationStage, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage.b(com.prineside.tdi2.ui.shared.ProfileSummary$AnimationStage, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long b(com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.f3733a = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage.b(com.prineside.tdi2.ui.shared.ProfileSummary$AnimationStage, long):long");
        }

        private AnimationStage() {
        }

        /* synthetic */ AnimationStage(byte b2) {
            this();
        }

        public void start() {
        }

        public void draw(float f) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$AnimationGiveXpStage.class */
    public class AnimationGiveXpStage extends AnimationStage {
        public int startBaseXpLabel;
        public int endBaseXpLabel;
        public int baseXpLine;
        public int startRegularXpLabel;
        public int endRegularXpLabel;
        public int startRegularXpLine;
        public int endRegularXpLine;
        public int startBonusXpLabel;
        public int endBonusXpLabel;
        public int startBonusXpLine;
        public int endBonusXpLine;
        public boolean fadeInBonus;

        public AnimationGiveXpStage() {
            super((byte) 0);
            this.duration = 1000000L;
        }

        @Override // com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage
        public void start() {
            ProfileSummary.this.p.setVisible(true);
            ProfileSummary.w.setLength(0);
            ProfileSummary.w.append("+").append(this.startRegularXpLabel).append(" XP");
            ProfileSummary.this.p.setText(ProfileSummary.w);
            ProfileSummary.w.setLength(0);
            ProfileSummary.w.append("+").append(this.startBonusXpLabel).append(" bonus XP");
            ProfileSummary.this.r.setText(ProfileSummary.w);
            if (this.fadeInBonus) {
                ProfileSummary.this.r.setVisible(true);
                ProfileSummary.this.p.clearActions();
                ProfileSummary.this.p.addAction(Actions.parallel(Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.36f), 0.3f), Actions.moveTo(242.0f, 31.0f, 0.3f)));
            }
            ProfileSummary.this.m.setVisible(true);
            ProfileSummary.this.m.setColor(Color.WHITE);
            ProfileSummary.this.o.setVisible(true);
            ProfileSummary.this.o.setColor(MaterialColor.AMBER.P500);
            ProfileSummary.this.q.setVisible(true);
            ProfileSummary.this.q.setColor(MaterialColor.LIGHT_GREEN.P500);
        }

        @Override // com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage
        public void draw(float f) {
            float f2 = 162.0f / Game.i.authManager.getSessionData().nextLevelXp;
            ProfileSummary.this.m.setWidth(this.baseXpLine * f2);
            ProfileSummary.this.o.setWidth((this.startRegularXpLine + ((this.endRegularXpLine - this.startRegularXpLine) * f)) * f2);
            ProfileSummary.this.o.setPosition(ProfileSummary.this.m.getWidth(), 0.0f);
            ProfileSummary.this.q.setWidth((this.startBonusXpLine + ((this.endBonusXpLine - this.startBonusXpLine) * f)) * f2);
            ProfileSummary.this.q.setPosition(ProfileSummary.this.m.getWidth() + ProfileSummary.this.o.getWidth(), 0.0f);
            ProfileSummary.w.setLength(0);
            ProfileSummary.w.append((int) (this.startBaseXpLabel + ((this.endBaseXpLabel - this.startBaseXpLabel) * f))).append(" / ").append(Game.i.authManager.getSessionData().nextLevelXp).append(" XP");
            ProfileSummary.this.i.setText(ProfileSummary.w);
            ProfileSummary.w.setLength(0);
            ProfileSummary.w.append("+").append((int) (this.startRegularXpLabel + ((this.endRegularXpLabel - this.startRegularXpLabel) * f))).append(" XP");
            ProfileSummary.this.p.setText(ProfileSummary.w);
            ProfileSummary.w.setLength(0);
            ProfileSummary.w.append("+").append((int) (this.startBonusXpLabel + ((this.endBonusXpLabel - this.startBonusXpLabel) * f))).append(" bonus XP");
            ProfileSummary.this.r.setText(ProfileSummary.w);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ProfileSummary$AnimationLevelUpStage.class */
    public class AnimationLevelUpStage extends AnimationStage {
        public int newLevel;

        public AnimationLevelUpStage() {
            super((byte) 0);
            this.duration = 500000L;
        }

        @Override // com.prineside.tdi2.ui.shared.ProfileSummary.AnimationStage
        public void start() {
            float f = ((float) this.duration) / 1000000.0f;
            ProfileSummary.this.m.clearActions();
            ProfileSummary.this.m.addAction(Actions.sequence(Actions.delay(0.5f * f), Actions.fadeOut(0.3f * f)));
            ProfileSummary.this.q.clearActions();
            ProfileSummary.this.q.addAction(Actions.sequence(Actions.delay(0.5f * f), Actions.fadeOut(0.3f * f)));
            ProfileSummary.this.o.clearActions();
            ProfileSummary.this.o.addAction(Actions.sequence(Actions.delay(0.5f * f), Actions.fadeOut(0.3f * f)));
            ProfileSummary.this.j.clearActions();
            ProfileSummary.this.j.addAction(Actions.sequence(Actions.delay(0.5f * f), Actions.scaleTo(0.0f, 0.0f, 0.25f * f), Actions.alpha(0.0f), Actions.run(() -> {
                ProfileSummary.this.j.clearChildren();
                Array<TextureRegionConfig> profileLevelTextures = Game.i.authManager.getProfileLevelTextures(this.newLevel);
                for (int i = 0; i < profileLevelTextures.size; i++) {
                    ProfileSummary.this.j.addActor(profileLevelTextures.items[i].createImage(0.0f, 0.0f, 48.0f));
                }
            }), Actions.scaleTo(2.0f, 2.0f), Actions.parallel(Actions.scaleTo(1.0f, 1.0f, 0.25f * f, Interpolation.pow2In), Actions.alpha(1.0f, 0.25f * f, Interpolation.pow2In))));
        }
    }
}
