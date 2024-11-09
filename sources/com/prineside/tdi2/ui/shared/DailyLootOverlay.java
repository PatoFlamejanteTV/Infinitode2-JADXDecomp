package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DailyLootOverlay.class */
public class DailyLootOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3499a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 150, "DailyLootOverlay main");

    /* renamed from: b, reason: collision with root package name */
    private final Label f3500b;
    private final ScrollPane c;
    private final Group d;
    private final Table e;
    private final Table f;
    private final Label g;
    private final Label h;
    private final Label i;
    private final Image j;
    private final Label k;
    private final Label l;
    private final ComplexButton m;
    private float n;
    private boolean o;
    private static final StringBuilder p = new StringBuilder();

    public static DailyLootOverlay i() {
        return (DailyLootOverlay) Game.i.uiManager.getComponent(DailyLootOverlay.class);
    }

    public DailyLootOverlay() {
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(488.0f, 435.5f);
        this.f3499a.getTable().add((Table) group).size(976.0f, 871.0f);
        this.d = new Group();
        this.d.setOrigin(488.0f, 435.5f);
        group.addActor(this.d);
        this.d.addActor(new QuadActor(new Color(724249599), new float[]{0.0f, 0.0f, 0.0f, 871.0f, 976.0f, 858.0f, 976.0f, 13.0f}));
        this.e = new Table();
        this.c = new ScrollPane(this.e, Game.i.assetManager.getScrollPaneStyle(16.0f));
        UiUtils.enableMouseMoveScrollFocus(this.c);
        this.c.setPosition(0.0f, 175.0f);
        this.c.setSize(976.0f, 654.0f);
        this.d.addActor(this.c);
        Image image = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image.setColor(new Color(724249599));
        image.setSize(976.0f, 64.0f);
        image.setPosition(0.0f, 766.0f);
        image.setTouchable(Touchable.disabled);
        this.d.addActor(image);
        this.f3500b = new Label("", Game.i.assetManager.getLabelStyle(36));
        this.f3500b.setSize(100.0f, 29.0f);
        this.f3500b.setPosition(40.0f, 800.0f);
        this.d.addActor(this.f3500b);
        this.f = new Table();
        this.f.setSize(294.0f, 64.0f);
        this.f.setPosition(642.0f, 765.0f);
        this.d.addActor(this.f);
        Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image2.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image2.setSize(976.0f, 12.0f);
        image2.setPosition(0.0f, 175.0f);
        image2.setTouchable(Touchable.disabled);
        this.d.addActor(image2);
        this.d.addActor(new QuadActor(new Color(858993663), new float[]{0.0f, 0.0f, 0.0f, 175.0f, 976.0f, 175.0f, 976.0f, 13.0f}));
        this.g = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.g.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.g.setSize(100.0f, 22.0f);
        this.g.setPosition(40.0f, 127.0f);
        this.d.addActor(this.g);
        this.h = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.h.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.h.setSize(100.0f, 22.0f);
        this.h.setPosition(40.0f, 99.0f);
        this.d.addActor(this.h);
        this.i = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.i.setSize(100.0f, 22.0f);
        this.i.setPosition(836.0f, 127.0f);
        this.i.setAlignment(16);
        this.d.addActor(this.i);
        Image image3 = new Image(Game.i.assetManager.getDrawable("checkbox"));
        image3.setSize(48.0f, 48.0f);
        image3.setPosition(34.0f, 35.0f);
        this.d.addActor(image3);
        this.j = new Image(Game.i.assetManager.getDrawable("icon-check"));
        this.j.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.j.setSize(32.0f, 32.0f);
        this.j.setPosition(44.0f, 49.0f);
        this.d.addActor(this.j);
        this.k = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.k.setSize(100.0f, 18.0f);
        this.k.setPosition(95.0f, 63.0f);
        this.d.addActor(this.k);
        this.l = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.l.setSize(100.0f, 18.0f);
        this.l.setPosition(95.0f, 36.0f);
        this.l.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.d.addActor(this.l);
        this.m = new ComplexButton(Game.i.localeManager.i18n.get("play"), Game.i.assetManager.getLabelStyle(30), () -> {
            String dailyLootCurrentQuest = Game.i.dailyQuestManager.getDailyLootCurrentQuest();
            if (!dailyLootCurrentQuest.equals(DailyQuestManager.RESET_QUESTS_QUEST_ID)) {
                Game.i.screenManager.goToLevelSelectScreenShowLevel(Game.i.basicLevelManager.getRegularQuestById(dailyLootCurrentQuest).level);
            } else {
                QuestPrestigeOverlay.i().show();
                setVisible(false);
            }
        });
        this.m.setPosition(743.0f, 21.0f);
        this.m.setSize(247.0f, 93.0f);
        this.m.setBackground(Game.i.assetManager.getDrawable("ui-map-prestige-button-right"), 0.0f, 0.0f, 247.0f, 93.0f);
        this.m.setIconPositioned(Game.i.assetManager.getDrawable("icon-joystick"), 21.0f, 19.0f, 48.0f, 48.0f);
        this.m.setLabel(80.0f, 30.0f, 100.0f, 23.0f, 8);
        this.d.addActor(this.m);
        this.f3499a.getTable().setVisible(false);
    }

    public void show() {
        this.f3500b.setText(Game.i.localeManager.i18n.get("daily_loot"));
        this.h.setText(Game.i.localeManager.i18n.get("daily_loot_hint"));
        this.f.clear();
        int dailyLootCurrentMonthIndex = Game.i.dailyQuestManager.getDailyLootCurrentMonthIndex();
        int dailyLootCurrentDayIndex = Game.i.dailyQuestManager.getDailyLootCurrentDayIndex();
        Label label = new Label(Game.i.localeManager.i18n.get("month") + ": " + (dailyLootCurrentMonthIndex + 1), Game.i.assetManager.getLabelStyle(24));
        label.setAlignment(16);
        this.f.add((Table) label).top().right().expandX().fillX().row();
        int i = Game.i.dailyQuestManager.dailyLootMinBonusPerMonth * dailyLootCurrentMonthIndex;
        int i2 = Game.i.dailyQuestManager.dailyLootMaxBonusPerMonth * dailyLootCurrentMonthIndex;
        if (i2 > 0) {
            Label label2 = new Label("[#888888]" + Game.i.localeManager.i18n.get("bonus") + ": [][#8BC34A]+" + i + " - " + i2 + "%[]", Game.i.assetManager.getLabelStyle(21));
            label2.setAlignment(16);
            this.f.add((Table) label2).top().right().row();
        }
        if (Game.i.progressManager.isDoubleGainEnabled()) {
            Label label3 = new Label("[#FFC107]" + Game.i.localeManager.i18n.get("double_gain_title") + ": x2[]", Game.i.assetManager.getLabelStyle(21));
            label3.setAlignment(16);
            this.f.add((Table) label3).top().right().row();
        }
        this.e.clear();
        this.e.add().width(1.0f).height(32.0f).row();
        for (int i3 = dailyLootCurrentMonthIndex; i3 <= dailyLootCurrentMonthIndex + 1; i3++) {
            Table table = new Table();
            this.e.add(table).expandX().fillX().padTop(16.0f).padBottom(8.0f).row();
            Label label4 = new Label(Game.i.localeManager.i18n.get("month") + SequenceUtils.SPACE + (i3 + 1), Game.i.assetManager.getLabelStyle(24));
            label4.setColor(MaterialColor.LIGHT_GREEN.P500);
            table.add((Table) label4).padLeft(40.0f);
            if (i3 != 0) {
                Label label5 = new Label(Game.i.localeManager.i18n.get("bonus") + ": +" + (Game.i.dailyQuestManager.dailyLootMinBonusPerMonth * i3) + "-" + (Game.i.dailyQuestManager.dailyLootMaxBonusPerMonth * i3) + "%", Game.i.assetManager.getLabelStyle(24));
                label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table.add((Table) label5).padLeft(16.0f);
            }
            table.add().height(1.0f).expandX().fillX();
            Table table2 = new Table();
            for (int i4 = 0; i4 < Game.i.dailyQuestManager.dailyLootDayConfigs.size; i4++) {
                final DailyQuestManager.DailyLootDayConfig dailyLootDayConfig = Game.i.dailyQuestManager.dailyLootDayConfigs.items[i4];
                if (i4 % 7 == 0) {
                    table2.row();
                }
                Group group = new Group();
                group.setTransform(false);
                table2.add((Table) group).size(128.0f, 140.0f);
                ItemCell itemCell = new ItemCell();
                final int count = dailyLootDayConfig.getCount(i3);
                itemCell.setItem(dailyLootDayConfig.item, count);
                itemCell.setColRow(i4 % 7, i4 / 7);
                int i5 = (i3 * Game.i.dailyQuestManager.dailyLootDayConfigs.size) + i4;
                itemCell.setCornerText(new StringBuilder().append(i5 + 1).toString());
                boolean z = false;
                if (i5 < dailyLootCurrentDayIndex) {
                    z = true;
                }
                itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.DailyLootOverlay.1
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        ItemDescriptionDialog.i().showWithCount(dailyLootDayConfig.item, count);
                    }
                });
                if (dailyLootCurrentDayIndex == i5) {
                    if (Game.i.dailyQuestManager.isTodayDailyLootQuestCompleted()) {
                        z = true;
                    }
                    itemCell.setSelected(true);
                }
                group.addActor(itemCell);
                if (z) {
                    Image image = new Image();
                    if (((i4 % 7) + (i4 / 7)) % 2 == 0) {
                        image.setDrawable(Game.i.assetManager.getDrawable("item-cell-a"));
                    } else {
                        image.setDrawable(Game.i.assetManager.getDrawable("item-cell-b"));
                    }
                    image.setSize(128.0f, 140.0f);
                    image.setColor(0.05f, 0.05f, 0.05f, 0.56f);
                    group.addActor(image);
                    Image image2 = new Image(Game.i.assetManager.getDrawable("icon-check"));
                    image2.setColor(MaterialColor.LIGHT_GREEN.P500);
                    image2.setSize(64.0f, 64.0f);
                    image2.setPosition(32.0f, 38.0f);
                    group.addActor(image2);
                }
            }
            this.e.add(table2).row();
        }
        this.e.add().width(1.0f).height(104.0f).row();
        this.g.setText(Game.i.localeManager.i18n.format("daily_loot_current_day_title", Integer.valueOf(dailyLootCurrentDayIndex + 1)));
        String dailyLootCurrentQuest = Game.i.dailyQuestManager.getDailyLootCurrentQuest();
        if (!dailyLootCurrentQuest.equals(DailyQuestManager.RESET_QUESTS_QUEST_ID)) {
            BasicLevelQuestConfig regularQuestById = Game.i.basicLevelManager.getRegularQuestById(dailyLootCurrentQuest);
            this.l.setText(Game.i.localeManager.i18n.get("level") + ": " + regularQuestById.level.name);
            p.setLength(0);
            p.append(regularQuestById.getTitle(true, true));
            if (regularQuestById.getRequiredValue(Game.i.gameValueManager.getSnapshot()) > 1) {
                p.append(": [#8BC34A]").append(regularQuestById.formatValueForUi(regularQuestById.getRequiredValue(Game.i.gameValueManager.getSnapshot()))).append("[]");
            }
            this.k.setText(p);
        } else {
            this.l.setText("");
            this.k.setText(Game.i.localeManager.i18n.get("daily_loot_quest_reset_quests"));
        }
        this.j.setVisible(Game.i.dailyQuestManager.isTodayDailyLootQuestCompleted());
        if (Game.i.dailyQuestManager.isTodayDailyLootQuestCompleted()) {
            this.m.setVisible(false);
        } else {
            this.m.setVisible(true);
            if (dailyLootCurrentQuest.equals(DailyQuestManager.RESET_QUESTS_QUEST_ID)) {
                this.m.setText(Game.i.localeManager.i18n.get("quests_reset_button"));
                this.m.setIcon(Game.i.assetManager.getDrawable("icon-crown"));
            } else {
                this.m.setText(Game.i.localeManager.i18n.get("play"));
                this.m.setIcon(Game.i.assetManager.getDrawable("icon-joystick"));
            }
        }
        setVisible(true);
    }

    public void setVisible(boolean z) {
        if (z) {
            DarkOverlay.i().addCallerOverlayLayer("DailyLootOverlay", this.f3499a.zIndex - 1, () -> {
                setVisible(false);
                return true;
            });
            UiUtils.bouncyShowOverlay(null, this.f3499a.getTable(), this.d);
            Game.i.uiManager.stage.setScrollFocus(this.c);
        } else {
            DarkOverlay.i().removeCaller("DailyLootOverlay");
            UiUtils.bouncyHideOverlay(null, this.f3499a.getTable(), this.d);
        }
        this.o = z;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public void postRender(float f) {
        if (this.o) {
            this.n += f;
            if (this.n >= 1.0f) {
                this.n -= 1.0f;
                p.setLength(0);
                p.append("[#888888]").append(Game.i.localeManager.i18n.get("daily_loot_next_in")).append("[] ");
                p.append(StringFormatter.digestTime(Game.i.dailyQuestManager.getSecondsTillNextDailyLoot()));
                this.i.setText(p);
            }
        }
    }
}
