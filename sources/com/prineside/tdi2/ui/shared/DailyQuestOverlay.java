package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.LeaderBoardManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Locale;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DailyQuestOverlay.class */
public final class DailyQuestOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3504b = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SHARED_COMPONENTS, 110, "DailyQuestOverlay tint", true);
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 111, "DailyQuestOverlay main");
    private Group d;
    private Label e;
    private Table f;
    private Label g;
    private Label h;
    private Group i;
    private Group j;
    private Group k;
    private Group l;
    private Label m;
    private Label n;
    private Label o;
    private ScrollPane p;
    private Image q;
    private Label r;
    private Label s;
    private Table t;
    private Table u;
    private Group[] v;
    private Label w;
    private Label x;
    private Label y;
    private Label z;
    private Label A;
    private LabelButton B;
    private Label C;
    private ComplexButton D;
    private float E;
    private DailyQuestManager.DailyQuestLevel F;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3503a = TLog.forClass(DailyQuestOverlay.class);
    private static final StringBuilder G = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DailyQuestOverlay$RankPrizesRow.class */
    public enum RankPrizesRow {
        FIRST,
        SECOND,
        THIRD,
        TOP_3,
        TOP_10,
        TOP_30
    }

    static /* synthetic */ float a(DailyQuestOverlay dailyQuestOverlay, float f) {
        float f2 = dailyQuestOverlay.E + f;
        dailyQuestOverlay.E = f2;
        return f2;
    }

    static /* synthetic */ float b(DailyQuestOverlay dailyQuestOverlay, float f) {
        dailyQuestOverlay.E = 0.0f;
        return 0.0f;
    }

    public static DailyQuestOverlay i() {
        return (DailyQuestOverlay) Game.i.uiManager.getComponent(DailyQuestOverlay.class);
    }

    public DailyQuestOverlay() {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(Config.BACKGROUND_COLOR);
        image.getColor().f889a = 0.78f;
        this.f3504b.getTable().add((Table) image).expand().fill();
        this.f3504b.getTable().setTouchable(Touchable.enabled);
        this.f3504b.getTable().addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.DailyQuestOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                DailyQuestOverlay.this.hide();
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            }
        });
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(650.0f, 488.5f);
        this.c.getTable().add((Table) group).size(1300.0f, 977.0f);
        this.d = new Group() { // from class: com.prineside.tdi2.ui.shared.DailyQuestOverlay.2
            @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
            public void act(float f) {
                super.act(f);
                if (this.c) {
                    DailyQuestOverlay.a(DailyQuestOverlay.this, f);
                    if (DailyQuestOverlay.this.E >= 1.0f) {
                        DailyQuestOverlay.b(DailyQuestOverlay.this, 0.0f);
                        DailyQuestOverlay.this.a();
                    }
                }
            }
        };
        this.d.setTransform(true);
        this.d.setOrigin(650.0f, 488.5f);
        this.d.setSize(1300.0f, 977.0f);
        group.addActor(this.d);
        QuadActor quadActor = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{16.0f, 26.0f, 16.0f, 952.0f, 546.0f, 961.0f, 546.0f, 0.0f});
        quadActor.setSize(546.0f, 961.0f);
        this.d.addActor(quadActor);
        QuadActor quadActor2 = new QuadActor(new Color(791621631), new float[]{0.0f, 32.0f, 0.0f, 970.0f, 530.0f, 977.0f, 530.0f, 19.0f});
        quadActor2.setSize(530.0f, 977.0f);
        this.d.addActor(quadActor2);
        this.w = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.w.setPosition(40.0f, 914.0f);
        this.w.setSize(100.0f, 23.0f);
        this.d.addActor(this.w);
        this.e = new Label("2019-04-26 (" + Game.i.localeManager.i18n.get("in_progress").toLowerCase(Locale.ENGLISH) + ")", Game.i.assetManager.getLabelStyle(21));
        this.e.setPosition(40.0f, 882.0f);
        this.e.setSize(100.0f, 16.0f);
        this.e.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.d.addActor(this.e);
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setPosition(0.0f, 198.0f);
        image2.setSize(530.0f, 662.0f);
        image2.setColor(new Color(724249599));
        this.d.addActor(image2);
        this.f = new Table();
        this.p = new ScrollPane(this.f);
        UiUtils.enableMouseMoveScrollFocus(this.p);
        this.p.setPosition(0.0f, 198.0f);
        this.p.setSize(530.0f, 662.0f);
        this.d.addActor(this.p);
        Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image3.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image3.setPosition(0.0f, 850.0f);
        image3.setSize(530.0f, 10.0f);
        image3.setTouchable(Touchable.disabled);
        this.d.addActor(image3);
        Image image4 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image4.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image4.setPosition(0.0f, 198.0f);
        image4.setSize(530.0f, 10.0f);
        image4.setTouchable(Touchable.disabled);
        this.d.addActor(image4);
        this.h = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.h.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.h.setPosition(40.0f, 157.0f);
        this.h.setSize(100.0f, 16.0f);
        this.d.addActor(this.h);
        Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image5.setSize(530.0f, 64.0f);
        image5.setPosition(0.0f, 69.0f);
        image5.setColor(new Color(673720575));
        this.d.addActor(image5);
        this.j = new Group();
        this.j.setTransform(false);
        this.j.setSize(530.0f, 64.0f);
        this.j.setPosition(0.0f, 69.0f);
        this.j.setVisible(false);
        this.d.addActor(this.j);
        this.x = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.x.setPosition(40.0f, 0.0f);
        this.x.setSize(100.0f, 64.0f);
        this.j.addActor(this.x);
        this.i = new Group();
        this.i.setTransform(false);
        this.i.setSize(530.0f, 64.0f);
        this.i.setPosition(0.0f, 69.0f);
        this.i.setVisible(false);
        this.d.addActor(this.i);
        this.m = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.m.setPosition(40.0f, 0.0f);
        this.m.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.m.setSize(50.0f, 64.0f);
        this.m.setAlignment(1);
        this.i.addActor(this.m);
        this.n = new LimitedWidthLabel("", 30, 24, 280.0f);
        this.n.setPosition(122.0f, 0.0f);
        this.n.setSize(100.0f, 64.0f);
        this.i.addActor(this.n);
        this.o = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.o.setAlignment(16);
        this.o.setPosition(390.0f, 0.0f);
        this.o.setSize(100.0f, 64.0f);
        this.i.addActor(this.o);
        Table table = new Table();
        table.setPosition(312.0f, 149.0f);
        table.setSize(177.0f, 32.0f);
        this.d.addActor(table);
        table.add().height(32.0f).expandX().fillX();
        Image image6 = new Image(Game.i.assetManager.getDrawable("icon-skill-point"));
        image6.setColor(MaterialColor.CYAN.P500);
        table.add((Table) image6).size(32.0f, 32.0f).padRight(8.0f);
        this.s = new Label("-", Game.i.assetManager.getLabelStyle(24));
        this.s.setColor(MaterialColor.CYAN.P500);
        table.add((Table) this.s).height(32.0f);
        QuadActor quadActor3 = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{25.0f, 0.0f, 25.0f, 970.0f, 717.0f, 956.0f, 725.0f, 27.0f});
        quadActor3.setPosition(570.0f, 0.0f);
        quadActor3.setSize(725.0f, 970.0f);
        this.d.addActor(quadActor3);
        QuadActor quadActor4 = new QuadActor(new Color(791621631), new float[]{0.0f, 19.0f, 0.0f, 977.0f, 710.0f, 967.0f, 710.0f, 42.0f});
        quadActor4.setSize(710.0f, 977.0f);
        quadActor4.setPosition(570.0f, 0.0f);
        this.d.addActor(quadActor4);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setPosition(570.0f, 0.0f);
        group2.setSize(710.0f, 977.0f);
        this.d.addActor(group2);
        Image image7 = new Image(Game.i.assetManager.getDrawable("icon-exclamation-triangle"));
        image7.setSize(64.0f, 64.0f);
        image7.setPosition(40.0f, 882.0f);
        group2.addActor(image7);
        Table table2 = new Table();
        table2.setPosition(128.0f, 882.0f);
        table2.setSize(500.0f, 64.0f);
        group2.addActor(table2);
        this.y = new Label("", Game.i.assetManager.getLabelStyle(36));
        table2.add((Table) this.y).left().padRight(16.0f);
        this.g = new Label("00:00:00", Game.i.assetManager.getLabelStyle(21));
        this.g.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) this.g).expand().padTop(8.0f).left();
        Image image8 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image8.setPosition(0.0f, 796.0f);
        image8.setSize(710.0f, 64.0f);
        image8.setColor(new Color(623191551));
        group2.addActor(image8);
        this.q = new Image();
        this.q.setPosition(40.0f, 808.0f);
        this.q.setSize(40.0f, 40.0f);
        group2.addActor(this.q);
        this.r = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.r.setPosition(96.0f, 796.0f);
        this.r.setSize(100.0f, 64.0f);
        group2.addActor(this.r);
        this.z = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.z.setPosition(40.0f, 732.0f);
        this.z.setSize(100.0f, 64.0f);
        group2.addActor(this.z);
        this.t = new Table();
        this.t.setSize(710.0f, 140.0f);
        this.t.setPosition(0.0f, 587.0f);
        group2.addActor(this.t);
        this.l = new Group();
        this.l.setTransform(false);
        this.l.setSize(710.0f, 796.0f);
        this.l.setVisible(false);
        this.l.setTouchable(Touchable.childrenOnly);
        group2.addActor(this.l);
        this.k = new Group();
        this.k.setTransform(false);
        this.k.setSize(710.0f, 796.0f);
        this.k.setVisible(false);
        this.k.setTouchable(Touchable.childrenOnly);
        group2.addActor(this.k);
        this.A = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.A.setPosition(40.0f, 506.0f);
        this.A.setSize(100.0f, 64.0f);
        this.k.addActor(this.A);
        this.B = new LabelButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.DAILY_QUEST_RULES_WIKI_URL);
        });
        this.B.setPosition(510.0f, 506.0f);
        this.B.setSize(160.0f, 64.0f);
        this.B.setAlignment(16);
        this.k.addActor(this.B);
        this.v = new Group[RankPrizesRow.values().length];
        float f = 458.0f;
        for (RankPrizesRow rankPrizesRow : RankPrizesRow.values()) {
            Image image9 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
            image9.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image9.setPosition(0.0f, f);
            image9.setSize(710.0f, 48.0f);
            this.k.addActor(image9);
            Group group3 = new Group();
            this.v[rankPrizesRow.ordinal()] = group3;
            group3.setTransform(false);
            group3.setPosition(0.0f, f);
            group3.setSize(48.0f, 710.0f);
            this.k.addActor(group3);
            f -= 52.0f;
        }
        this.C = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.C.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.C.setPosition(40.0f, 157.0f);
        this.C.setSize(100.0f, 16.0f);
        group2.addActor(this.C);
        this.u = new Table();
        this.u.setPosition(40.0f, 68.0f);
        this.u.setSize(320.0f, 72.0f);
        group2.addActor(this.u);
        PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
            hide();
        }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P400, MaterialColor.LIGHT_BLUE.P600);
        paddedImageButton.setPosition(1228.0f, 914.0f);
        paddedImageButton.setSize(96.0f, 96.0f);
        paddedImageButton.setIconPosition(16.0f, 16.0f);
        paddedImageButton.setIconSize(64.0f, 64.0f);
        this.d.addActor(paddedImageButton);
        this.D = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), () -> {
            if (GameStateSystem.savedGameExists()) {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                    hide();
                    GameStateSystem.deleteSavedGame();
                    Game.i.dailyQuestManager.startDailyLevel();
                });
            } else {
                hide();
                Game.i.dailyQuestManager.startDailyLevel();
            }
        });
        this.D.setPosition(1063.0f, 65.0f);
        this.D.setSize(235.0f, 92.0f);
        this.D.setBackground(Game.i.assetManager.getDrawable("ui-daily-quest-play-button"), 0.0f, 0.0f, 235.0f, 92.0f);
        this.D.setIconPositioned(Game.i.assetManager.getDrawable("icon-joystick"), 29.0f, 26.0f, 48.0f, 48.0f);
        this.D.setLabel(90.0f, 11.0f, 100.0f, 82.0f, 8);
        this.d.addActor(this.D);
        this.f3504b.getTable().setVisible(false);
        this.c.getTable().setVisible(false);
    }

    private static void a(CharSequence charSequence, Group group, Array<ItemStack> array) {
        group.clear();
        Actor label = new Label(charSequence, Game.i.assetManager.getLabelStyle(24));
        label.setPosition(40.0f, 0.0f);
        label.setSize(100.0f, 48.0f);
        group.addActor(label);
        Table table = new Table();
        table.setPosition(140.0f, 0.0f);
        table.setSize(570.0f, 48.0f);
        group.addActor(table);
        for (int i = 0; i < array.size; i++) {
            table.add((Table) array.get(i).getItem().generateIcon(32.0f, false)).size(32.0f, 32.0f).padRight(6.0f);
            table.add((Table) new Label("x" + ((Object) StringFormatter.commaSeparatedNumber(array.get(i).getCount())), Game.i.assetManager.getLabelStyle(21))).padRight(20.0f);
        }
        table.add().height(48.0f).expandX().fillX();
    }

    public final void show(DailyQuestManager.DailyQuestLevel dailyQuestLevel) {
        Game.i.assertInMainThread();
        setVisible(true);
        this.w.setText(Game.i.localeManager.i18n.get("leaderboard"));
        this.x.setText(Game.i.localeManager.i18n.get("sign_in_to_get_ranked"));
        this.y.setText(Game.i.localeManager.i18n.get("daily_quest"));
        this.z.setText(Game.i.localeManager.i18n.get("complete_daily_quest_for_rewards"));
        this.A.setText(Game.i.localeManager.i18n.get("get_ranked_daily_to_win_more"));
        this.B.setText(Game.i.localeManager.i18n.get("rules"));
        this.C.setText(Game.i.localeManager.i18n.get("all_time_top_players"));
        this.D.setText(Game.i.localeManager.i18n.get("play"));
        this.F = dailyQuestLevel;
        f3503a.i("fallback: " + String.valueOf(dailyQuestLevel.isLocalFallback), new Object[0]);
        f3503a.i("name: " + String.valueOf(dailyQuestLevel.getLevelName()), new Object[0]);
        f3503a.i("date: " + String.valueOf(dailyQuestLevel.forDate), new Object[0]);
        this.q.setDrawable(Game.i.assetManager.getDrawable(dailyQuestLevel.wasCompleted() ? "checkbox-checked" : "checkbox"));
        this.r.setText(dailyQuestLevel.getQuestName());
        a();
        this.t.clearChildren();
        Array<ItemStack> questRewards = dailyQuestLevel.getQuestRewards();
        for (int i = 0; i < questRewards.size; i++) {
            final ItemStack itemStack = questRewards.get(i);
            ItemCell itemCell = new ItemCell();
            itemCell.setTouchable(Touchable.enabled);
            itemCell.setItemStack(itemStack);
            itemCell.setColRow(i, 0);
            itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.DailyQuestOverlay.3
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    ItemDescriptionDialog.i().showWithCount(itemStack.getItem(), itemStack.getCount());
                }
            });
            this.t.add((Table) itemCell).size(128.0f, 140.0f);
        }
        if (dailyQuestLevel.isLocalFallback) {
            this.l.setVisible(true);
            this.k.setVisible(false);
        } else {
            this.l.setVisible(false);
            this.k.setVisible(true);
            a(Game.i.localeManager.i18n.get("first_short"), this.v[RankPrizesRow.FIRST.ordinal()], dailyQuestLevel.prizesFirstPlace);
            a(Game.i.localeManager.i18n.get("second_short"), this.v[RankPrizesRow.SECOND.ordinal()], dailyQuestLevel.prizesSecondPlace);
            a(Game.i.localeManager.i18n.get("third_short"), this.v[RankPrizesRow.THIRD.ordinal()], dailyQuestLevel.prizesThirdPlace);
            a(Game.i.localeManager.i18n.format("top_percent", "3"), this.v[RankPrizesRow.TOP_3.ordinal()], dailyQuestLevel.prizesTop3Percent);
            a(Game.i.localeManager.i18n.format("top_percent", "10"), this.v[RankPrizesRow.TOP_10.ordinal()], dailyQuestLevel.prizesTop10Percent);
            a(Game.i.localeManager.i18n.format("top_percent", "30"), this.v[RankPrizesRow.TOP_30.ordinal()], dailyQuestLevel.prizesTop30Percent);
        }
        Game.i.leaderBoardManager.getSkillPointLeaderboards(skillPointsLeaderboardsResult -> {
            this.u.clearChildren();
            if (skillPointsLeaderboardsResult.success) {
                if (skillPointsLeaderboardsResult.player != null) {
                    this.s.setTextFromInt(skillPointsLeaderboardsResult.player.score);
                }
                for (int i2 = 0; i2 < skillPointsLeaderboardsResult.entries.size; i2++) {
                    LeaderBoardManager.LeaderboardsEntry leaderboardsEntry = skillPointsLeaderboardsResult.entries.get(i2);
                    Group group = new Group();
                    group.setTransform(false);
                    this.u.add((Table) group).size(320.0f, 24.0f).row();
                    LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(leaderboardsEntry.nickname, 21, 21, 240.0f);
                    limitedWidthLabel.setPosition(0.0f, 0.0f);
                    limitedWidthLabel.setSize(100.0f, 24.0f);
                    group.addActor(limitedWidthLabel);
                    Table table = new Table();
                    table.setPosition(160.0f, 0.0f);
                    table.setSize(160.0f, 24.0f);
                    group.addActor(table);
                    table.add().height(32.0f).expandX().fillX();
                    Image image = new Image(Game.i.assetManager.getDrawable("icon-skill-point"));
                    image.setColor(MaterialColor.CYAN.P500);
                    table.add((Table) image).size(24.0f, 24.0f).padRight(8.0f);
                    Label label = new Label(StringFormatter.commaSeparatedNumber(leaderboardsEntry.score), Game.i.assetManager.getLabelStyle(21));
                    label.setColor(MaterialColor.CYAN.P500);
                    table.add((Table) label).height(24.0f);
                }
                return;
            }
            this.s.setText("-");
            Label label2 = new Label(Game.i.localeManager.i18n.get("failed_to_load"), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(MaterialColor.ORANGE.P500);
            this.u.add((Table) label2);
        });
        setLeaderBoardDate(Game.i.dailyQuestManager.getCurrentDayDate());
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.F == null) {
            return;
        }
        int timestampSeconds = this.F.endTimestamp - Game.getTimestampSeconds();
        if (timestampSeconds >= 0) {
            this.g.setText(StringFormatter.digestTime(timestampSeconds));
            this.g.setVisible(true);
        } else {
            this.g.setText("--");
            this.g.setVisible(true);
        }
    }

    public final void setLeaderBoardDate(String str) {
        this.f.clearChildren();
        Image image = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        image.setOrigin(32.0f, 32.0f);
        image.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
        this.f.add((Table) image).size(64.0f, 64.0f);
        String str2 = str;
        if (str.equals(Game.i.dailyQuestManager.getCurrentDayDate())) {
            str2 = str2 + " (" + Game.i.localeManager.i18n.get("in_progress") + ")";
        }
        this.e.setText(str2);
        Game.i.dailyQuestManager.getLeaderboards(str, dailyQuestLeaderboards -> {
            this.f.clearChildren();
            if (dailyQuestLeaderboards.success) {
                float f = 0.0f;
                for (int i = 0; i < dailyQuestLeaderboards.entries.size; i++) {
                    final LeaderBoardManager.LeaderboardsEntry leaderboardsEntry = dailyQuestLeaderboards.entries.get(i);
                    Group group = new Group();
                    group.setTransform(false);
                    group.setSize(530.0f, 64.0f);
                    this.f.add((Table) group).row();
                    f += 64.0f;
                    if (i % 2 == 0) {
                        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image2.setColor(new Color(623191551));
                        image2.setSize(530.0f, 64.0f);
                        group.addActor(image2);
                    }
                    Label label = new Label(String.valueOf(i + 1), Game.i.assetManager.getLabelStyle(24));
                    label.setPosition(40.0f, 0.0f);
                    label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    label.setSize(40.0f, 64.0f);
                    label.setAlignment(1);
                    group.addActor(label);
                    Image image3 = new Image();
                    if (leaderboardsEntry.hasProfilePicture) {
                        image3.setDrawable(new TextureRegionDrawable(Game.i.assetManager.loadWebTexture(Config.AVATAR_WEB_TEXTURES_URL + leaderboardsEntry.playerId.toLowerCase(Locale.US) + "-32.png")));
                    } else {
                        image3.setDrawable(Game.i.assetManager.getDrawable("icon-user"));
                        image3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    }
                    image3.setPosition(92.0f, 16.0f);
                    image3.setSize(32.0f, 32.0f);
                    group.addActor(image3);
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
                    limitedWidthLabel.setPosition(140.0f, 0.0f);
                    limitedWidthLabel.setSize(100.0f, 64.0f);
                    limitedWidthLabel.setTouchable(Touchable.enabled);
                    limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.DailyQuestOverlay.4
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f2, float f3) {
                            WebBrowser.i().webView.loadUrl(Net.HttpMethods.GET, Config.XDX_VIEW_PLAYER_PROFILE_URL + leaderboardsEntry.playerId, null);
                            WebBrowser.i().setVisible(true);
                            LeaderboardsOverlay.i().hide();
                        }
                    });
                    group.addActor(limitedWidthLabel);
                    Label label2 = new Label(StringFormatter.commaSeparatedNumber(leaderboardsEntry.score), Game.i.assetManager.getLabelStyle(24));
                    label2.setAlignment(16);
                    label2.setPosition(390.0f, 0.0f);
                    label2.setSize(100.0f, 64.0f);
                    group.addActor(label2);
                }
                if (f < this.p.getHeight()) {
                    this.f.add().size(530.0f, this.p.getHeight() - f);
                }
                if (dailyQuestLeaderboards.player == null) {
                    this.i.setVisible(false);
                    this.j.setVisible(true);
                    this.h.setText(Game.i.localeManager.i18n.get("your_rank"));
                    return;
                }
                if (dailyQuestLeaderboards.player.rank == 1) {
                    this.h.setText(Game.i.localeManager.i18n.get("your_rank") + " - " + Game.i.localeManager.i18n.get("leader") + "!");
                } else {
                    int ceil = MathUtils.ceil((dailyQuestLeaderboards.player.rank / dailyQuestLeaderboards.player.total) * 100.0f);
                    int i2 = ceil;
                    if (ceil < 0) {
                        i2 = 0;
                    }
                    if (i2 > 100) {
                        i2 = 100;
                    }
                    G.setLength(0);
                    G.append(Game.i.localeManager.i18n.get("your_rank"));
                    G.append(" - ");
                    G.append(Game.i.localeManager.i18n.format("top_percent", Integer.valueOf(i2)));
                    this.h.setText(G);
                }
                this.n.setText(Game.i.authManager.getNickname());
                this.m.setText(dailyQuestLeaderboards.player.rank == 0 ? TypeDescription.Generic.OfWildcardType.SYMBOL : String.valueOf(dailyQuestLeaderboards.player.rank));
                this.o.setText(StringFormatter.commaSeparatedNumber(dailyQuestLeaderboards.player.score));
                this.i.setVisible(true);
                this.j.setVisible(false);
                return;
            }
            Label label3 = new Label(Game.i.localeManager.i18n.get("failed_to_load"), Game.i.assetManager.getLabelStyle(24));
            label3.setWrap(true);
            label3.setColor(MaterialColor.ORANGE.P500);
            label3.setAlignment(1);
            this.f.add((Table) label3).width(400.0f);
        });
    }

    public final void setVisible(boolean z) {
        if (z) {
            UiUtils.bouncyShowOverlay(this.f3504b.getTable(), this.c.getTable(), this.d);
        } else {
            UiUtils.bouncyHideOverlay(this.f3504b.getTable(), this.c.getTable(), this.d);
        }
    }
}
