package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.BasicLevelManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/QuestPrestigeOverlay.class */
public class QuestPrestigeOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3734a = TLog.forClass(QuestPrestigeOverlay.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3735b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 175, "QuestPrestigeOverlay overlay");
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 176, "QuestPrestigeOverlay main");
    private final Group d;
    private Image e;
    private Label f;
    private Group g;
    private Table h;
    private ComplexButton i;

    public static QuestPrestigeOverlay i() {
        return (QuestPrestigeOverlay) Game.i.uiManager.getComponent(QuestPrestigeOverlay.class);
    }

    public QuestPrestigeOverlay() {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(Config.BACKGROUND_COLOR);
        image.getColor().f889a = 0.78f;
        this.f3735b.getTable().add((Table) image).expand().fill();
        this.f3735b.getTable().setTouchable(Touchable.enabled);
        this.f3735b.getTable().addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.QuestPrestigeOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                QuestPrestigeOverlay.this.setVisible(false);
            }
        });
        Group group = new Group();
        group.setOrigin(338.0f, 198.0f);
        this.c.getTable().add((Table) group).size(676.0f, 396.0f);
        this.d = new Group();
        this.d.setOrigin(338.0f, 198.0f);
        this.d.setSize(676.0f, 396.0f);
        group.addActor(this.d);
        this.d.addActor(new QuadActor(new Color(724249599), new float[]{0.0f, 0.0f, 0.0f, 396.0f, 676.0f, 385.0f, 676.0f, 9.0f}));
        Label label = new Label(Game.i.localeManager.i18n.get("gv_title_PRESTIGE_MODE"), Game.i.assetManager.getLabelStyle(36));
        label.setPosition(40.0f, 325.0f);
        label.setSize(100.0f, 28.0f);
        this.d.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("quests_prestige_description"), Game.i.assetManager.getLabelStyle(21));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label2.setSize(100.0f, 16.0f);
        label2.setPosition(40.0f, 299.0f);
        this.d.addActor(label2);
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setSize(560.0f, 16.0f);
        image2.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image2.setPosition(40.0f, 170.0f);
        this.d.addActor(image2);
        this.e = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.e.setSize(560.0f, 16.0f);
        this.e.setColor(MaterialColor.LIGHT_BLUE.P500);
        this.e.setPosition(40.0f, 170.0f);
        this.d.addActor(this.e);
        for (int i = 0; i < 3; i++) {
            Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image3.setSize(4.0f, 16.0f);
            float f = 177.0f + (141.0f * i);
            if (i == 0) {
                f = 153.0f;
            }
            image3.setPosition(f, 170.0f);
            image3.setColor(new Color(724249599));
            this.d.addActor(image3);
        }
        this.f = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.f.setPosition(40.0f, 102.0f);
        this.f.setSize(100.0f, 20.0f);
        this.d.addActor(this.f);
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setSize(495.0f, 66.0f);
        this.g.setPosition(141.0f, 190.0f);
        this.d.addActor(this.g);
        this.h = new Table();
        this.h.setPosition(486.0f, 94.0f);
        this.h.setSize(150.0f, 48.0f);
        this.d.addActor(this.h);
        ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("back"), Game.i.assetManager.getLabelStyle(30), () -> {
            setVisible(false);
        });
        complexButton.setSize(255.0f, 93.0f);
        complexButton.setBackground(Game.i.assetManager.getDrawable("ui-map-prestige-button-left"), 0.0f, 0.0f, 255.0f, 93.0f);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-left"), 18.0f, 19.0f, 48.0f, 48.0f);
        complexButton.setLabel(77.0f, 30.0f, 100.0f, 23.0f, 8);
        complexButton.setPosition(-13.0f, -9.0f);
        this.d.addActor(complexButton);
        this.i = new ComplexButton(Game.i.localeManager.i18n.get("quests_reset_button"), Game.i.assetManager.getLabelStyle(30), () -> {
            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("quests_prestige_confirm"), () -> {
                Game.i.basicLevelManager.resetQuestsForPrestige();
                setVisible(false);
            });
        });
        this.i.setSize(247.0f, 93.0f);
        this.i.setBackground(Game.i.assetManager.getDrawable("ui-map-prestige-button-right"), 0.0f, 0.0f, 247.0f, 93.0f);
        this.i.setIconPositioned(Game.i.assetManager.getDrawable("icon-dollar"), 21.0f, 19.0f, 48.0f, 48.0f);
        this.i.setLabel(80.0f, 30.0f, 100.0f, 23.0f, 8);
        this.i.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, MaterialColor.GREY.P800);
        this.i.setPosition(443.0f, -9.0f);
        this.d.addActor(this.i);
        this.f3735b.getTable().setVisible(false);
        this.c.getTable().setVisible(false);
    }

    public void show() {
        int prestigeMaxCompletedQuests = Game.i.basicLevelManager.getPrestigeMaxCompletedQuests();
        int prestigeCompletedQuests = Game.i.basicLevelManager.getPrestigeCompletedQuests();
        double d = prestigeCompletedQuests / prestigeMaxCompletedQuests;
        int i = (int) ((d * 100.0d) + 1.0E-4d);
        this.e.setSize((float) (d * 560.0d), 16.0f);
        f3734a.i("completed " + prestigeCompletedQuests + "/" + prestigeMaxCompletedQuests + " (" + i + "%)", new Object[0]);
        this.g.clear();
        int i2 = 0;
        BasicLevelManager.QuestsPrestigeMilestone[] questsPrestigeMilestones = Game.i.basicLevelManager.getQuestsPrestigeMilestones();
        for (int i3 = 0; i3 < questsPrestigeMilestones.length; i3++) {
            BasicLevelManager.QuestsPrestigeMilestone questsPrestigeMilestone = questsPrestigeMilestones[i3];
            float f = 141.0f * i3;
            if (i3 == 0) {
                f = -24.0f;
            }
            Image image = new Image(Game.i.assetManager.getDrawable("ui-quests-prestige-milestone-mark"));
            image.setPosition(f, 0.0f);
            image.setSize(74.0f, 66.0f);
            this.g.addActor(image);
            Table table = new Table();
            table.setSize(74.0f, 40.0f);
            table.setPosition(f, 14.0f);
            this.g.addActor(table);
            table.add((Table) new Image(Game.i.assetManager.getDrawable("prestige-token"))).size(24.0f);
            Label label = new Label(new StringBuilder().append(questsPrestigeMilestone.tokensCount).toString(), Game.i.assetManager.getLabelStyle(21));
            table.add((Table) label).padLeft(4.0f).row();
            table.add((Table) new Label(questsPrestigeMilestone.percentage + "%", Game.i.assetManager.getLabelStyle(21))).colspan(2);
            if (i >= questsPrestigeMilestone.percentage) {
                label.setColor(Color.WHITE);
                image.setColor(MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
                i2 = questsPrestigeMilestone.tokensCount;
            } else {
                label.setColor(MaterialColor.LIGHT_BLUE.P300);
                image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            }
        }
        this.f.setText(Game.i.localeManager.i18n.get("quests_prestige_quests_completed") + ": [#4FC3F7]" + ((Object) StringFormatter.commaSeparatedNumber(prestigeCompletedQuests)) + " / " + ((Object) StringFormatter.commaSeparatedNumber(prestigeMaxCompletedQuests)) + "[] (" + i + "%)");
        this.h.clear();
        this.h.add().height(1.0f).expandX().fillX();
        this.h.add((Table) new Image(Game.i.assetManager.getDrawable("prestige-token"))).size(48.0f);
        Label label2 = new Label(new StringBuilder().append(i2).toString(), Game.i.assetManager.getLabelStyle(36));
        label2.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.h.add((Table) label2).padLeft(8.0f);
        this.i.setEnabled(i2 > 0);
        setVisible(true);
    }

    public void setVisible(boolean z) {
        if (z) {
            UiUtils.bouncyShowOverlay(this.f3735b.getTable(), this.c.getTable(), this.d);
        } else {
            UiUtils.bouncyHideOverlay(this.f3735b.getTable(), this.c.getTable(), this.d);
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
    }
}
