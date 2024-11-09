package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.screens.MainMenuScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.UiUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DifficultyModeOverlay.class */
public class DifficultyModeOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3529a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 111, "DifficultyModeOverlay main");

    /* renamed from: b, reason: collision with root package name */
    private Group f3530b;

    public static DifficultyModeOverlay i() {
        return (DifficultyModeOverlay) Game.i.uiManager.getComponent(DifficultyModeOverlay.class);
    }

    public DifficultyModeOverlay() {
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(602.0f, 300.0f);
        this.f3529a.getTable().add((Table) group).size(1204.0f, 600.0f);
        this.f3530b = new Group();
        this.f3530b.setTransform(true);
        this.f3530b.setOrigin(602.0f, 300.0f);
        this.f3530b.setSize(1204.0f, 600.0f);
        group.addActor(this.f3530b);
        a();
        this.f3529a.getTable().setVisible(false);
    }

    private void a() {
        this.f3530b.clearChildren();
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.enabled);
        group.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.DifficultyModeOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Game.i.progressManager.setDifficultyMode(DifficultyMode.EASY);
                DifficultyModeOverlay.this.setVisible(false);
                Game.i.screenManager.goToMainMenu();
            }
        });
        group.setSize(372.0f, 600.0f);
        this.f3530b.addActor(group);
        if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.EASY) {
            group.addActor(new QuadActor(new Color(-1950135553), new float[]{0.0f, -10.0f, -10.0f, 610.0f, 382.0f, 610.0f, 372.0f, -10.0f}));
            group.addActor(new QuadActor(Config.BACKGROUND_COLOR, new float[]{5.0f, -5.0f, -5.0f, 605.0f, 377.0f, 605.0f, 367.0f, -5.0f}));
        }
        group.addActor(new QuadActor(new Color(779956957), new float[]{10.0f, 0.0f, 0.0f, 600.0f, 372.0f, 600.0f, 362.0f, 0.0f}));
        Label label = new Label(Game.i.localeManager.i18n.get("difficulty_mode_EASY").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label.setPosition(0.0f, 500.0f);
        label.setSize(372.0f, 96.0f);
        label.setAlignment(1);
        label.setColor(MaterialColor.LIGHT_GREEN.P200);
        group.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("dm_no_leaderboards"), Game.i.assetManager.getLabelStyle(24));
        label2.setAlignment(1);
        label2.setWrap(true);
        label2.setPosition(30.0f, 400.0f);
        label2.setSize(312.0f, 96.0f);
        group.addActor(label2);
        Label label3 = new Label(Game.i.localeManager.i18n.get("dm_waves_on_demand"), Game.i.assetManager.getLabelStyle(24));
        label3.setAlignment(1);
        label3.setWrap(true);
        label3.setPosition(30.0f, 300.0f);
        label3.setSize(312.0f, 96.0f);
        group.addActor(label3);
        Label label4 = new Label(Game.i.localeManager.i18n.get("dm_regular_research"), Game.i.assetManager.getLabelStyle(24));
        label4.setAlignment(1);
        label4.setWrap(true);
        label4.setPosition(30.0f, 200.0f);
        label4.setSize(312.0f, 96.0f);
        group.addActor(label4);
        Label label5 = new Label(Game.i.localeManager.i18n.get("dm_easy_enemies_prizes"), Game.i.assetManager.getLabelStyle(24));
        label5.setAlignment(1);
        label5.setWrap(true);
        label5.setPosition(30.0f, 100.0f);
        label5.setSize(312.0f, 96.0f);
        group.addActor(label5);
        Label label6 = new Label(Game.i.localeManager.i18n.get("dm_no_special_loot"), Game.i.assetManager.getLabelStyle(24));
        label6.setAlignment(1);
        label6.setWrap(true);
        label6.setPosition(30.0f, 0.0f);
        label6.setSize(312.0f, 96.0f);
        label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        group.addActor(label6);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setTouchable(Touchable.enabled);
        group2.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.DifficultyModeOverlay.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Game.i.progressManager.setDifficultyMode(DifficultyMode.NORMAL);
                DifficultyModeOverlay.this.setVisible(false);
                Game.i.screenManager.goToMainMenu();
            }
        });
        group2.setSize(372.0f, 600.0f);
        group2.setPosition(416.0f, 0.0f);
        this.f3530b.addActor(group2);
        if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL) {
            group2.addActor(new QuadActor(new Color(61469951), new float[]{-10.0f, -10.0f, 0.0f, 610.0f, 372.0f, 610.0f, 382.0f, -10.0f}));
            group2.addActor(new QuadActor(Config.BACKGROUND_COLOR, new float[]{-5.0f, -5.0f, 5.0f, 605.0f, 367.0f, 605.0f, 377.0f, -5.0f}));
        }
        group2.addActor(new QuadActor(new Color(41401821), new float[]{0.0f, 0.0f, 10.0f, 600.0f, 362.0f, 600.0f, 372.0f, 0.0f}));
        Label label7 = new Label(Game.i.localeManager.i18n.get("difficulty_mode_NORMAL").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label7.setPosition(0.0f, 500.0f);
        label7.setSize(372.0f, 96.0f);
        label7.setAlignment(1);
        label7.setColor(MaterialColor.LIGHT_BLUE.P200);
        group2.addActor(label7);
        Label label8 = new Label(Game.i.localeManager.i18n.get("dm_regular_leaderboards"), Game.i.assetManager.getLabelStyle(24));
        label8.setAlignment(1);
        label8.setWrap(true);
        label8.setPosition(30.0f, 400.0f);
        label8.setSize(312.0f, 96.0f);
        group2.addActor(label8);
        Label label9 = new Label(Game.i.localeManager.i18n.get("dm_waves_by_timer"), Game.i.assetManager.getLabelStyle(24));
        label9.setAlignment(1);
        label9.setWrap(true);
        label9.setPosition(30.0f, 300.0f);
        label9.setSize(312.0f, 96.0f);
        group2.addActor(label9);
        Label label10 = new Label(Game.i.localeManager.i18n.get("dm_regular_research"), Game.i.assetManager.getLabelStyle(24));
        label10.setAlignment(1);
        label10.setWrap(true);
        label10.setPosition(30.0f, 200.0f);
        label10.setSize(312.0f, 96.0f);
        group2.addActor(label10);
        Label label11 = new Label(Game.i.localeManager.i18n.get("dm_normal_enemies_prizes"), Game.i.assetManager.getLabelStyle(24));
        label11.setAlignment(1);
        label11.setWrap(true);
        label11.setPosition(30.0f, 100.0f);
        label11.setSize(312.0f, 96.0f);
        group2.addActor(label11);
        Label label12 = new Label(Game.i.localeManager.i18n.get("dm_no_special_loot"), Game.i.assetManager.getLabelStyle(24));
        label12.setAlignment(1);
        label12.setWrap(true);
        label12.setPosition(30.0f, 0.0f);
        label12.setSize(312.0f, 96.0f);
        group2.addActor(label12);
        Group group3 = new Group();
        group3.setTransform(false);
        group3.setTouchable(Touchable.enabled);
        group3.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.DifficultyModeOverlay.3
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (Game.i.progressManager.difficultyModeAvailable(DifficultyMode.ENDLESS_I)) {
                    Game.i.progressManager.setDifficultyMode(DifficultyMode.ENDLESS_I);
                    DifficultyModeOverlay.this.setVisible(false);
                    Game.i.screenManager.goToMainMenu();
                }
            }
        });
        group3.setSize(372.0f, 600.0f);
        group3.setPosition(832.0f, 0.0f);
        this.f3530b.addActor(group3);
        if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.ENDLESS_I) {
            group3.addActor(new QuadActor(new Color(-4126721), new float[]{0.0f, -10.0f, -10.0f, 610.0f, 382.0f, 610.0f, 372.0f, -10.0f}));
            group3.addActor(new QuadActor(Config.BACKGROUND_COLOR, new float[]{5.0f, -5.0f, -5.0f, 605.0f, 377.0f, 605.0f, 367.0f, -5.0f}));
        }
        group3.addActor(new QuadActor(new Color(-176220195), new float[]{10.0f, 0.0f, 0.0f, 600.0f, 372.0f, 600.0f, 362.0f, 0.0f}));
        Label label13 = new Label(Game.i.localeManager.i18n.get("difficulty_mode_ENDLESS_I").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label13.setPosition(0.0f, 500.0f);
        label13.setSize(372.0f, 96.0f);
        label13.setAlignment(1);
        label13.setColor(MaterialColor.AMBER.P200);
        group3.addActor(label13);
        Label label14 = new Label(Game.i.localeManager.i18n.get("dm_endless_leaderboards_limited"), Game.i.assetManager.getLabelStyle(24));
        label14.setAlignment(1);
        label14.setWrap(true);
        label14.setPosition(30.0f, 400.0f);
        label14.setSize(312.0f, 96.0f);
        group3.addActor(label14);
        Label label15 = new Label(Game.i.localeManager.i18n.get("dm_waves_by_timer"), Game.i.assetManager.getLabelStyle(24));
        label15.setAlignment(1);
        label15.setWrap(true);
        label15.setPosition(30.0f, 300.0f);
        label15.setSize(312.0f, 96.0f);
        group3.addActor(label15);
        Label label16 = new Label(Game.i.localeManager.i18n.get("dm_endless_researches"), Game.i.assetManager.getLabelStyle(24));
        label16.setAlignment(1);
        label16.setWrap(true);
        label16.setPosition(30.0f, 200.0f);
        label16.setSize(312.0f, 96.0f);
        group3.addActor(label16);
        Label label17 = new Label(Game.i.localeManager.i18n.get("dm_endless_i_enemies_prizes"), Game.i.assetManager.getLabelStyle(24));
        label17.setAlignment(1);
        label17.setWrap(true);
        label17.setPosition(30.0f, 100.0f);
        label17.setSize(312.0f, 96.0f);
        group3.addActor(label17);
        Label label18 = new Label(Game.i.localeManager.i18n.get("dm_drop_special_loot"), Game.i.assetManager.getLabelStyle(24));
        label18.setAlignment(1);
        label18.setWrap(true);
        label18.setPosition(30.0f, 0.0f);
        label18.setSize(312.0f, 96.0f);
        group3.addActor(label18);
        if (!Game.i.progressManager.difficultyModeAvailable(DifficultyMode.ENDLESS_I)) {
            group3.addActor(new QuadActor(new Color(170), new float[]{10.0f, 0.0f, 0.0f, 600.0f, 372.0f, 600.0f, 362.0f, 0.0f}));
            Table table = new Table();
            table.setSize(372.0f, 600.0f);
            group3.addActor(table);
            table.add((Table) new Image(Game.i.assetManager.getDrawable("icon-lock"))).size(64.0f).padBottom(16.0f).row();
            Label label19 = new Label(Game.i.localeManager.i18n.get("complete_story_line_to_unlock"), Game.i.assetManager.getLabelStyle(30));
            table.add((Table) label19).width(312.0f);
            label19.setWrap(true);
            label19.setAlignment(1);
        }
    }

    public void setVisible(boolean z) {
        if (z) {
            a();
            UiUtils.bouncyShowOverlay(null, this.f3529a.getTable(), this.f3530b);
            DarkOverlay.i().addCaller("DifficultyModeOverlay", UiManager.MainUiLayer.SHARED_COMPONENTS, this.f3529a.zIndex - 1, () -> {
                setVisible(false);
                return true;
            });
            TooltipsOverlay.i().hideEntry(MainMenuScreen.TT_ENDLESS_DIFFICULTY_BUTTON);
            TooltipsOverlay.i().markTagShown(MainMenuScreen.TT_ENDLESS_DIFFICULTY_BUTTON);
            return;
        }
        UiUtils.bouncyHideOverlay(null, this.f3529a.getTable(), this.f3530b);
        DarkOverlay.i().removeCaller("DifficultyModeOverlay");
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
    }
}
