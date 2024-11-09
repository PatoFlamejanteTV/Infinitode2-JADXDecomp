package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Requirement;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.ResearchManager;
import com.prineside.tdi2.managers.TowerStatManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.screens.ResearchesScreen;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.ItemDescriptionDialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/ResearchMenu.class */
public class ResearchMenu implements Disposable {
    private final SideMenu c;
    private final ResearchesScreen d;
    private final SideMenu.SideMenuContainer e;
    private boolean f;
    private final LimitedWidthLabel i;
    private final Label j;
    private final Label k;
    private final Label l;
    private final Group m;
    private final Group n;
    private final Group o;
    private final Group p;
    private final Image q;
    private final Image r;
    private final Label s;
    private final Image t;
    private final Label u;
    private final Label v;
    private final Image w;
    private final Label x;
    private float y;
    private ComplexButton z;
    private ComplexButton A;
    private ComplexButton B;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3386a = TLog.forClass(ResearchMenu.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3387b = new Color(808464639);
    private static final StringBuilder K = new StringBuilder();
    private Color C = Color.WHITE;
    private Color D = Color.WHITE;
    private Color E = Color.WHITE;
    private boolean F = false;
    private boolean G = false;
    private final _SideMenuListener H = new _SideMenuListener(this, 0);
    private final _ResearchesScreenListener I = new _ResearchesScreenListener(this, 0);
    private final _ResearchManagerListener J = new _ResearchManagerListener(this, 0);
    private final Label.LabelStyle h = new Label.LabelStyle(Game.i.assetManager.getFont(21), new Color(1.0f, 1.0f, 1.0f, 0.28f));
    private final Label.LabelStyle g = new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE);

    public ResearchMenu(SideMenu sideMenu, final ResearchesScreen researchesScreen) {
        this.c = sideMenu;
        this.d = researchesScreen;
        this.e = sideMenu.createContainer("ResearchMenu");
        this.e.hide();
        this.i = new LimitedWidthLabel("", 36, 30, 440.0f);
        this.i.setSize(520.0f, 26.0f);
        this.i.setPosition(40.0f, 903.0f);
        this.e.addActor(this.i);
        this.j = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.j.setSize(440.0f, 60.0f);
        this.j.setPosition(40.0f, 835.0f);
        this.j.setWrap(true);
        this.j.setAlignment(10);
        this.e.addActor(this.j);
        this.k = new Label("L1", new Label.LabelStyle(Game.i.assetManager.getFont(36), MaterialColor.AMBER.P500));
        this.k.setSize(520.0f, 26.0f);
        this.k.setPosition(40.0f, 903.0f);
        this.k.setAlignment(16);
        this.e.addActor(this.k);
        this.l = new Label("/ 10", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.l.setSize(520.0f, 26.0f);
        this.l.setPosition(40.0f, 867.0f);
        this.l.setAlignment(16);
        this.l.setColor(MaterialColor.AMBER.P500);
        this.l.getColor().mul(1.0f, 1.0f, 1.0f, 0.56f);
        this.e.addActor(this.l);
        this.m = new Group();
        this.m.setTransform(false);
        this.m.setSize(600.0f, 825.0f);
        this.e.addActor(this.m);
        this.z = new ComplexButton(Game.i.localeManager.i18n.get("reset_branch").toUpperCase(), Game.i.localeManager.i18n.get("reset_branch").length() > 14 ? Game.i.assetManager.getLabelStyle(24) : Game.i.assetManager.getLabelStyle(30), () -> {
            Dialog.i().showConfirm(Game.i.localeManager.i18n.format("reset_research_branch_for_accelerators", Integer.valueOf(Game.i.researchManager.getResetStarResearchesAcceleratorPrice())), () -> {
                Runnable runnable = () -> {
                    Game.i.researchManager.resetStarResearches();
                    update();
                    researchesScreen.updateStarsCount();
                    if (GameStateSystem.savedGameExists()) {
                        GameStateSystem.deleteSavedGame();
                    }
                };
                if (GameStateSystem.savedGameExists()) {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), runnable);
                } else {
                    runnable.run();
                }
            });
        });
        this.A = new ComplexButton(Game.i.localeManager.i18n.get("reset").toUpperCase(), Game.i.assetManager.getLabelStyle(30), () -> {
            try {
                Research research = researchesScreen.selectedResearch;
                int resetForAcceleratorsState = research.resetForAcceleratorsState();
                if (resetForAcceleratorsState == 0) {
                    Array<ItemStack> array = new Array<>(ItemStack.class);
                    array.addAll(research.getCumulativePrice(0, research.getInstalledLevel(), true));
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.format("reset_research_confirm", Integer.valueOf(research.getResetPrice())), () -> {
                        if (GameStateSystem.savedGameExists()) {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                                GameStateSystem.deleteSavedGame();
                                Game.i.researchManager.resetResearchForAccelerators(research);
                                update();
                            });
                        } else {
                            Game.i.researchManager.resetResearchForAccelerators(research);
                            update();
                        }
                    });
                    Dialog.i().setItemsHintForVisibleDialog(array);
                    return;
                }
                String str = null;
                switch (resetForAcceleratorsState) {
                    case 1:
                        str = Game.i.localeManager.i18n.get("reset_research_unavailable_HAS_CHILD");
                        break;
                    case 4:
                        str = Game.i.localeManager.i18n.get("reset_research_unavailable_STAR_BRANCH");
                        break;
                    case 5:
                        str = Game.i.localeManager.i18n.get("reset_research_unavailable_NOT_ENOUGH_ACCELERATORS") + " (<@time-accelerator>[#FFC107]" + research.getResetPrice() + "[])";
                        break;
                }
                if (str == null) {
                    str = Game.i.localeManager.i18n.get("reset_research_unavailable_NOT_SUITABLE");
                }
                Dialog.i().showAlert(Game.i.assetManager.replaceRegionAliasesWithChars(str));
            } catch (Exception e) {
                f3386a.e("failed to reset", e);
            }
        });
        ComplexButton[] complexButtonArr = {this.z, this.A};
        for (int i = 0; i < 2; i++) {
            ComplexButton complexButton = complexButtonArr[i];
            Color color = Color.WHITE;
            complexButton.setIconLabelColors(color, color, Color.WHITE, new Color(1.0f, 1.0f, 1.0f, 0.56f));
            complexButton.setLabel(74.0f, 0.0f, 184.0f, 64.0f, 16);
            complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-restart"), 274.0f, 12.0f, 40.0f, 40.0f);
            complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 22.0f, 64.0f, 338.0f, 64.0f, 338.0f, 0.0f})), 0.0f, 0.0f, 338.0f, 64.0f);
            complexButton.setBackgroundColors(MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.ORANGE.P900.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.ORANGE.P700.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.GREY.P800);
            complexButton.setSize(338.0f, 64.0f);
            complexButton.setPosition(222.0f, 146.0f);
            this.e.addActor(complexButton);
        }
        this.n = new Group();
        this.n.setTransform(false);
        this.n.setTouchable(Touchable.enabled);
        this.n.setSize(338.0f, 80.0f);
        this.n.setPosition(40.0f, 40.0f);
        this.e.addActor(this.n);
        this.n.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.ResearchMenu.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i2, Actor actor) {
                super.enter(inputEvent, f, f2, i2, actor);
                if (i2 == -1) {
                    ResearchMenu.this.G = true;
                    ResearchMenu.this.a();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i2, Actor actor) {
                super.exit(inputEvent, f, f2, i2, actor);
                if (i2 == -1) {
                    ResearchMenu.this.G = false;
                    ResearchMenu.this.a();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i2, int i3) {
                if (i2 == -1) {
                    ResearchMenu.this.F = true;
                    ResearchMenu.this.a();
                }
                return super.touchDown(inputEvent, f, f2, i2, i3);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i2, int i3) {
                if (i2 == -1) {
                    ResearchMenu.this.F = false;
                    ResearchMenu.this.a();
                }
                super.touchUp(inputEvent, f, f2, i2, i3);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (researchesScreen.selectedResearch != null) {
                    researchesScreen.startSelectedResearch();
                }
            }
        });
        this.q = new Image(Game.i.assetManager.getDrawable("ui-upgrade-button"));
        this.q.setSize(338.0f, 80.0f);
        this.n.addActor(this.q);
        this.r = new Image(Game.i.assetManager.getDrawable("icon-research"));
        this.r.setSize(40.0f, 40.0f);
        this.r.setPosition(20.0f, 20.0f);
        this.n.addActor(this.r);
        this.s = new Label(Game.i.localeManager.i18n.get("do_research"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.s.setPosition(80.0f, 0.0f);
        this.s.setSize(100.0f, 80.0f);
        this.n.addActor(this.s);
        this.n.addActor(new HotKeyHintLabel(new int[]{66}, 80.0f, 6.0f, 8));
        this.o = new Group();
        this.o.setTransform(false);
        this.e.addActor(this.o);
        this.t = new Image(Game.i.assetManager.getDrawable("icon-clock"));
        this.t.setSize(24.0f, 24.0f);
        this.t.setPosition(120.0f, 50.0f);
        this.t.setTouchable(Touchable.disabled);
        this.o.addActor(this.t);
        this.u = new Label("30:00:00", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.u.setSize(112.0f, 24.0f);
        this.u.setPosition(152.0f, 50.0f);
        this.u.setAlignment(8);
        this.u.setTouchable(Touchable.disabled);
        this.o.addActor(this.u);
        this.B = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), null);
        this.B.setSize(180.0f, 80.0f);
        this.B.setPosition(380.0f, 40.0f);
        this.B.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 26.0f, 80.0f, 180.0f, 80.0f, 180.0f, 0.0f})), 0.0f, 0.0f, 180.0f, 80.0f);
        this.B.setBackgroundColors(MaterialColor.BLUE_GREY.P700, MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P600, MaterialColor.GREY.P900);
        this.B.setIconPositioned(Game.i.assetManager.getDrawable("research-token"), 64.0f, 8.0f, 64.0f, 64.0f);
        this.B.setIconLabelShadowEnabled(true);
        this.e.addActor(this.B);
        this.p = new Group();
        this.p.setTransform(false);
        this.e.addActor(this.p);
        Label label = new Label("for", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        label.setSize(70.0f, 80.0f);
        label.setPosition(378.0f, 40.0f);
        label.setAlignment(1);
        this.p.addActor(label);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-time-accelerator"));
        image.setSize(40.0f, 40.0f);
        image.setPosition(455.0f, 60.0f);
        image.setColor(MaterialColor.YELLOW.P500);
        this.p.addActor(image);
        this.v = new Label("100", new Label.LabelStyle(Game.i.assetManager.getFont(30), MaterialColor.YELLOW.P500));
        this.v.setSize(60.0f, 80.0f);
        this.v.setPosition(500.0f, 40.0f);
        this.v.setColor(MaterialColor.YELLOW.P500);
        this.p.addActor(this.v);
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(new Color(522133503));
        image2.setSize(520.0f, 8.0f);
        image2.setPosition(40.0f, 140.0f);
        this.p.addActor(image2);
        this.w = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.w.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.w.setSize(520.0f, 8.0f);
        this.w.setPosition(40.0f, 140.0f);
        this.p.addActor(this.w);
        Label label2 = new Label(Game.i.localeManager.i18n.get("researching..."), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        label2.setPosition(40.0f, 160.0f);
        label2.setSize(200.0f, 30.0f);
        this.p.addActor(label2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-clock"));
        image3.setSize(32.0f, 32.0f);
        image3.setPosition(420.0f, 159.0f);
        this.p.addActor(image3);
        this.x = new Label("00:30", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.x.setPosition(510.0f, 160.0f);
        this.x.setSize(50.0f, 30.0f);
        this.x.setAlignment(16);
        this.p.addActor(this.x);
        this.p.setVisible(false);
        sideMenu.addListener(this.H);
        researchesScreen.addListener(this.I);
        Game.i.researchManager.addListener(this.J);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.F) {
            this.q.setColor(this.E);
        } else if (this.G) {
            this.q.setColor(this.D);
        } else {
            this.q.setColor(this.C);
        }
    }

    private float a(Array<GameValueManager.GameValueEffect> array, Group group, float f) {
        boolean z = true;
        Array.ArrayIterator<GameValueManager.GameValueEffect> it = array.iterator();
        while (it.hasNext()) {
            GameValueManager.GameValueEffect next = it.next();
            StringBuilder title = Game.i.gameValueManager.getTitle(next.type);
            GameValueManager.ValueUnits units = Game.i.gameValueManager.getUnits(next.type);
            if (!z) {
                f -= 4.0f;
            }
            f -= 52.0f;
            if (units == GameValueManager.ValueUnits.BOOLEAN) {
                Actor image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image.setSize(600.0f, 52.0f);
                image.setPosition(0.0f, f);
                image.setColor(f3387b);
                group.addActor(image);
                Actor limitedWidthLabel = new LimitedWidthLabel(title, 24, 18, 520.0f);
                limitedWidthLabel.setSize(520.0f, 52.0f);
                limitedWidthLabel.setPosition(40.0f, f);
                limitedWidthLabel.setColor(MaterialColor.GREEN.P500);
                group.addActor(limitedWidthLabel);
            } else {
                Actor image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image2.setSize(455.0f, 52.0f);
                image2.setColor(f3387b);
                image2.setPosition(0.0f, f);
                group.addActor(image2);
                Actor image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image3.setColor(f3387b);
                image3.setSize(141.0f, 52.0f);
                image3.setPosition(459.0f, f);
                group.addActor(image3);
                Actor limitedWidthLabel2 = new LimitedWidthLabel(title, 24, 18, 410.0f);
                limitedWidthLabel2.setSize(410.0f, 52.0f);
                limitedWidthLabel2.setPosition(40.0f, f);
                limitedWidthLabel2.setColor(Color.WHITE);
                group.addActor(limitedWidthLabel2);
                Label label = new Label(Game.i.gameValueManager.formatEffectValue(next.delta, units), this.g);
                label.setSize(101.0f, 52.0f);
                label.setPosition(459.0f, f);
                label.setAlignment(16);
                label.setColor(MaterialColor.GREEN.P500);
                group.addActor(label);
            }
            z = false;
        }
        return f;
    }

    private void b() {
        if (Game.i.researchManager.getCurrentResearching() == null) {
            return;
        }
        int countAcceleratorsNeeded = Game.i.progressManager.countAcceleratorsNeeded((int) (Game.i.researchManager.getMillisToResearchingEnd() / 1000));
        K.setLength(0);
        K.append(countAcceleratorsNeeded);
        this.v.setText(K);
        int millisToResearchingEnd = (int) (Game.i.researchManager.getMillisToResearchingEnd() / 1000);
        this.w.setWidth(520.0f * (1.0f - (millisToResearchingEnd / r0.levels[r0.getInstalledLevel()].researchDuration)));
        this.x.setText(StringFormatter.digestTime(millisToResearchingEnd));
    }

    public void draw(float f) {
        if (this.d.selectedResearch != null && Game.i.researchManager.getCurrentResearching() == this.d.selectedResearch) {
            this.y += f;
            if (this.y > 1.0f) {
                b();
                this.y = 0.0f;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void update() {
        boolean isMaxNormalLevel;
        boolean isMaxNormalLevel2;
        Research research = this.d.selectedResearch;
        this.B.setVisible(false);
        if (research != null) {
            Array array = new Array(Label.class);
            this.A.setVisible(false);
            this.z.setVisible(false);
            if (research.priceInStars > 0 && Game.i.researchManager.getResetStarResearchesAcceleratorPrice() > 0) {
                this.z.setVisible(true);
            } else if (research.getInstalledLevel() > 0) {
                this.A.setVisible(true);
                if (research.resetForAcceleratorsState() == 0) {
                    this.A.setBackgroundColors(MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.ORANGE.P900.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.ORANGE.P700.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.GREY.P800);
                } else {
                    this.A.setBackgroundColors(MaterialColor.GREY.P800, MaterialColor.GREY.P900, MaterialColor.GREY.P700, Color.GRAY);
                }
            }
            this.i.setText(research.getTitle());
            this.j.setText(research.getDescription());
            int installedLevel = research.getInstalledLevel();
            int i = research.maxEndlessLevel;
            if (!DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                i = research.levels.length;
                if (installedLevel > research.levels.length) {
                    installedLevel = research.levels.length;
                }
            }
            if (i > 1 && installedLevel > 0) {
                K.setLength(0);
                this.k.setText(K.append('L').append(installedLevel));
                this.k.setVisible(true);
                K.setLength(0);
                this.l.setText(K.append("Max L").append(i));
                this.l.setVisible(true);
            } else {
                this.k.setVisible(false);
                this.l.setVisible(false);
            }
            this.m.clearChildren();
            float f = 793.0f;
            Array<GameValueManager.GameValueEffect> array2 = new Array<>();
            if (installedLevel == 0) {
                array2.addAll(research.levels[0].effects);
            } else {
                array2.addAll(research.getEffects(installedLevel));
            }
            if (array2.size != 0) {
                Label label = new Label(Game.i.localeManager.i18n.get("effects").toUpperCase(), this.h);
                label.setSize(520.0f, 52.0f);
                label.setPosition(40.0f, 741.0f);
                this.m.addActor(label);
                f = a(array2, this.m, 741.0f);
                if (installedLevel > 0 && !research.isMaxEndlessLevel()) {
                    boolean z = false;
                    array2.clear();
                    if (research.isMaxNormalLevel() && DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                        z = true;
                        if (research.endlessLevel == null) {
                            array2.addAll(research.levels[installedLevel].effects);
                        } else {
                            array2.addAll(research.endlessLevel.effects);
                        }
                    } else if (!research.isMaxNormalLevel()) {
                        z = true;
                        array2.addAll(research.levels[installedLevel].effects);
                    }
                    if (z) {
                        float f2 = f - 52.0f;
                        Label label2 = new Label(Game.i.localeManager.i18n.get("next_level").toUpperCase(), this.h);
                        label2.setSize(520.0f, 52.0f);
                        label2.setPosition(40.0f, f2);
                        this.m.addActor(label2);
                        f = a(array2, this.m, f2);
                    }
                }
            }
            if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                isMaxNormalLevel = research.isMaxEndlessLevel();
            } else {
                isMaxNormalLevel = research.isMaxNormalLevel();
            }
            if (installedLevel == 0 || !isMaxNormalLevel) {
                f -= 52.0f;
                Label label3 = new Label(Game.i.localeManager.i18n.get("research_requirements").toUpperCase(), this.h);
                label3.setSize(520.0f, 52.0f);
                label3.setPosition(40.0f, f);
                this.m.addActor(label3);
                boolean z2 = true;
                if (installedLevel == 0) {
                    int i2 = research.linksToParents.size;
                    int i3 = i2;
                    if (i2 != 0) {
                        if (research.priceInStars > 0) {
                            i3 = 1;
                        }
                        int i4 = 0;
                        f -= 52.0f;
                        z2 = false;
                        Array.ArrayIterator<Research.ResearchLink> it = research.linksToParents.iterator();
                        while (it.hasNext()) {
                            Research.ResearchLink next = it.next();
                            if (next.parent.getInstalledLevel() >= next.requiredLevels) {
                                i4++;
                            }
                        }
                        if (research.priceInStars > 0) {
                            Array.ArrayIterator<Research.ResearchLink> it2 = research.linksToChildren.iterator();
                            while (it2.hasNext()) {
                                Research.ResearchLink next2 = it2.next();
                                if (next2.child.getInstalledLevel() >= next2.requiredLevels) {
                                    i4++;
                                }
                            }
                        }
                        if (i4 > i3) {
                            i4 = i3;
                        }
                        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image.setSize(600.0f, 52.0f);
                        image.setColor(f3387b);
                        image.setPosition(0.0f, f);
                        this.m.addActor(image);
                        Label label4 = new Label(Game.i.localeManager.i18n.get("previous_researches"), this.g);
                        label4.setSize(410.0f, 52.0f);
                        label4.setPosition(40.0f, f);
                        label4.setColor(Color.WHITE);
                        this.m.addActor(label4);
                        K.setLength(0);
                        K.append(i4).append(" / ").append(i3);
                        Label label5 = new Label(K, this.g);
                        label5.setSize(101.0f, 52.0f);
                        label5.setPosition(459.0f, f);
                        label5.setAlignment(16);
                        this.m.addActor(label5);
                        if (i4 == i3) {
                            label5.setColor(MaterialColor.GREEN.P500);
                        } else {
                            label5.setColor(MaterialColor.ORANGE.P500);
                            array.add(label4);
                        }
                    }
                }
                Array array3 = new Array(ItemStack.class);
                if (research.getInstalledLevel() >= research.levels.length) {
                    array3.addAll(research.endlessLevel.getPrice(research.getInstalledLevel() + 1));
                } else {
                    Research.ResearchLevel researchLevel = research.levels[research.getInstalledLevel()];
                    array3.addAll(researchLevel.price);
                    if (researchLevel.requirements.length != 0) {
                        for (Requirement requirement : researchLevel.requirements) {
                            if (!z2) {
                                f -= 4.0f;
                            }
                            f -= 52.0f;
                            z2 = false;
                            Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                            image2.setSize(600.0f, 52.0f);
                            image2.setColor(f3387b);
                            image2.setPosition(0.0f, f);
                            this.m.addActor(image2);
                            Label label6 = new Label(requirement.getTitle(true), this.g);
                            label6.setSize(410.0f, 52.0f);
                            label6.setPosition(40.0f, f);
                            label6.setColor(Color.WHITE);
                            this.m.addActor(label6);
                            Label label7 = new Label(requirement.getFormattedValue(), this.g);
                            label7.setSize(101.0f, 52.0f);
                            label7.setPosition(459.0f, f);
                            label7.setAlignment(16);
                            this.m.addActor(label7);
                            if (requirement.isSatisfied()) {
                                label7.setColor(MaterialColor.GREEN.P500);
                            } else {
                                label7.setColor(MaterialColor.ORANGE.P500);
                                array.add(label6);
                            }
                        }
                    }
                    if (research.getInstalledLevel() == 0 && research.priceInStars > 0) {
                        if (!z2) {
                            f -= 4.0f;
                        }
                        f -= 52.0f;
                        z2 = false;
                        Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image3.setSize(600.0f, 52.0f);
                        image3.setColor(f3387b);
                        image3.setPosition(0.0f, f);
                        this.m.addActor(image3);
                        Label label8 = new Label(Game.i.localeManager.i18n.get("stars"), this.g);
                        label8.setSize(410.0f, 52.0f);
                        label8.setPosition(40.0f, f);
                        label8.setColor(Color.WHITE);
                        this.m.addActor(label8);
                        Label label9 = new Label("", this.g);
                        label9.setSize(101.0f, 52.0f);
                        label9.setPosition(459.0f, f);
                        label9.setAlignment(16);
                        this.m.addActor(label9);
                        int unusedStarsCount = Game.i.researchManager.getUnusedStarsCount();
                        if (unusedStarsCount >= research.priceInStars) {
                            label9.setColor(MaterialColor.GREEN.P500);
                            label9.setText(research.priceInStars + " / " + research.priceInStars);
                        } else {
                            label9.setColor(MaterialColor.ORANGE.P500);
                            label9.setText(unusedStarsCount + " / " + research.priceInStars);
                        }
                    }
                }
                boolean z3 = true;
                for (int i5 = 0; i5 < array3.size; i5++) {
                    final ItemStack itemStack = ((ItemStack[]) array3.items)[i5];
                    int itemsCount = Game.i.progressManager.getItemsCount(itemStack.getItem());
                    float f3 = 0.0f;
                    if (z3) {
                        f -= 52.0f;
                        if (!z2) {
                            f -= 4.0f;
                        }
                    } else {
                        f3 = 302.0f;
                    }
                    z2 = false;
                    Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image4.setSize(298.0f, 52.0f);
                    image4.setColor(f3387b);
                    image4.setPosition(f3, f);
                    image4.setTouchable(Touchable.enabled);
                    image4.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.ResearchMenu.2
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f4, float f5) {
                            ItemDescriptionDialog.i().show(itemStack.getItem());
                        }
                    });
                    this.m.addActor(image4);
                    Actor generateIcon = itemStack.getItem().generateIcon(40.0f, false);
                    generateIcon.setSize(40.0f, 40.0f);
                    if (z3) {
                        generateIcon.setPosition((f3 + 40.0f) - 4.0f, (f + 10.0f) - 4.0f);
                    } else {
                        generateIcon.setPosition((f3 + 12.0f) - 4.0f, (f + 10.0f) - 4.0f);
                    }
                    generateIcon.setTouchable(Touchable.disabled);
                    this.m.addActor(generateIcon);
                    K.setLength(0);
                    K.append(StringFormatter.compactNumber(itemsCount, false));
                    K.append(" / ");
                    K.append(StringFormatter.compactNumber(itemStack.getCount(), false));
                    Label label10 = new Label(K, this.g);
                    if (itemStack.getCount() > itemsCount) {
                        label10.setColor(MaterialColor.ORANGE.P500);
                    } else {
                        label10.setColor(Color.WHITE);
                    }
                    if (z3) {
                        label10.setSize(286.0f, 52.0f);
                    } else {
                        label10.setSize(258.0f, 52.0f);
                    }
                    label10.setPosition(f3, f);
                    label10.setAlignment(16);
                    label10.setTouchable(Touchable.disabled);
                    this.m.addActor(label10);
                    z3 = !z3;
                }
            }
            this.s.setPosition(80.0f, 0.0f);
            if (Game.i.researchManager.getCurrentResearching() == research) {
                this.o.setVisible(false);
                this.p.setVisible(true);
                this.C = MaterialColor.YELLOW.P900;
                this.E = MaterialColor.AMBER.P900;
                this.D = MaterialColor.YELLOW.P800;
                this.r.setDrawable(Game.i.assetManager.getDrawable("icon-time-accelerator"));
                this.s.setText(Game.i.localeManager.i18n.get("finish_now").toUpperCase());
                this.n.setVisible(true);
                b();
            } else {
                this.p.setVisible(false);
                if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                    isMaxNormalLevel2 = research.isMaxEndlessLevel();
                } else {
                    isMaxNormalLevel2 = research.isMaxNormalLevel();
                }
                if (!isMaxNormalLevel2) {
                    this.o.setVisible(true);
                    this.n.setVisible(true);
                    if (Game.i.researchManager.canStartResearching(research, false)) {
                        this.C = MaterialColor.LIGHT_BLUE.P800;
                        this.E = MaterialColor.LIGHT_BLUE.P900;
                        this.D = MaterialColor.LIGHT_BLUE.P700;
                    } else {
                        this.C = MaterialColor.RED.P800;
                        this.E = MaterialColor.RED.P900;
                        this.D = MaterialColor.RED.P700;
                    }
                    this.r.setDrawable(Game.i.assetManager.getDrawable("icon-research"));
                    if (research.getInstalledLevel() == 0) {
                        this.s.setText(Game.i.localeManager.i18n.get("do_research").toUpperCase());
                    } else {
                        this.s.setText(Game.i.localeManager.i18n.get("do_research").toUpperCase() + " L" + (research.getInstalledLevel() + 1));
                    }
                    if (research.levels.length > research.getInstalledLevel()) {
                        Research.ResearchLevel researchLevel2 = research.levels[research.getInstalledLevel()];
                        if (researchLevel2.researchDuration <= 0) {
                            this.u.setVisible(false);
                            this.t.setVisible(false);
                        } else {
                            this.u.setVisible(true);
                            this.t.setVisible(true);
                            this.u.setText(StringFormatter.digestTime(researchLevel2.researchDuration));
                            this.s.setPosition(80.0f, 10.0f);
                        }
                    } else {
                        this.u.setVisible(false);
                        this.t.setVisible(false);
                    }
                    if (Game.i.researchManager.canResearchForToken(research, true)) {
                        this.B.setVisible(true);
                        if (Game.i.progressManager.getItemsCount(Item.D.RESEARCH_TOKEN) > 0) {
                            if (Game.i.researchManager.canResearchForToken(research, false)) {
                                this.B.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                                this.B.setBackgroundColors(MaterialColor.BLUE_GREY.P700, MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P600, Color.WHITE);
                                this.B.setClickHandler(() -> {
                                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("research_for_token_confirm"), () -> {
                                        Game.i.researchManager.researchForToken(research);
                                        Dialog.i().hide();
                                    });
                                });
                            } else {
                                this.B.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                this.B.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.28f), Color.WHITE);
                                this.B.setClickHandler(() -> {
                                    for (int i6 = 0; i6 < array.size; i6++) {
                                        ((Label) array.get(i6)).clearActions();
                                        ((Label) array.get(i6)).addAction(Actions.sequence(Actions.alpha(0.3f, 0.25f), Actions.alpha(1.0f, 0.25f), Actions.alpha(0.3f, 0.25f), Actions.alpha(1.0f, 0.25f)));
                                    }
                                    Notifications.i().add(Game.i.localeManager.i18n.get("start_research_fail_reason_REQUIREMENT_NOT_SATISFIED"), null, null, StaticSoundType.FAIL);
                                });
                            }
                        } else {
                            this.B.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                            this.B.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.28f), Color.WHITE);
                            this.B.setClickHandler(() -> {
                                Notifications.i().add(Game.i.localeManager.i18n.get("not_enough_tokens"), null, null, StaticSoundType.FAIL);
                            });
                        }
                    }
                } else {
                    this.o.setVisible(false);
                    if (research.type == ResearchType.DEVELOPER_MODE) {
                        this.C = MaterialColor.RED.P800;
                        this.E = MaterialColor.RED.P900;
                        this.D = MaterialColor.RED.P700;
                        this.r.setDrawable(Game.i.assetManager.getDrawable("icon-times"));
                        this.s.setText(Game.i.localeManager.i18n.get("disable").toUpperCase());
                        this.n.setVisible(true);
                    } else {
                        this.n.setVisible(false);
                    }
                }
            }
            a();
            TowerType[] towerTypeArr = TowerType.values;
            int length = towerTypeArr.length;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    break;
                }
                TowerType towerType = towerTypeArr[i6];
                if (research.levels.length != 1 || research.levels[0].effects.length != 1 || research.levels[0].effects[0].type != Game.i.towerManager.getTowerGameValueType(towerType)) {
                    i6++;
                } else {
                    GameValueManager.GameValuesSnapshot createSnapshot = GameValueManager.createSnapshot(null, Game.i.progressManager.getDifficultyMode(), false, null, true, false, new ProgressManager.ProgressSnapshotForState());
                    for (TowerStatType towerStatType : Game.i.towerManager.getStatTypes(towerType)) {
                        TowerStatManager.TowerStat towerStatManager = Game.i.towerStatManager.getInstance(towerStatType);
                        float statFromConfig = Game.i.towerManager.getStatFromConfig(towerType, towerStatType, 0, 1, Game.i.gameValueManager.getSnapshot());
                        float statFromConfig2 = Game.i.towerManager.getStatFromConfig(towerType, towerStatType, 0, 1, createSnapshot);
                        float f4 = 0.0f;
                        for (TowerType towerType2 : TowerType.values) {
                            if (Game.i.towerManager.hasStat(towerType2, towerStatType)) {
                                float statFromConfig3 = Game.i.towerManager.getStatFromConfig(towerType2, towerStatType, 0, 1, Game.i.gameValueManager.getSnapshot());
                                if (statFromConfig3 > f4) {
                                    f4 = statFromConfig3;
                                }
                            }
                        }
                        f -= 52.0f;
                        Group group = new Group();
                        group.setSize(600.0f, 48.0f);
                        group.setPosition(0.0f, f);
                        this.m.addActor(group);
                        Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image5.setHeight(48.0f);
                        float f5 = (statFromConfig2 / f4) * 600.0f;
                        float f6 = f5;
                        if (f5 > 600.0f) {
                            f6 = 600.0f;
                        }
                        image5.setWidth(f6);
                        image5.setColor(towerStatManager.getColor());
                        image5.getColor().f889a = 0.42f;
                        group.addActor(image5);
                        Image image6 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image6.setHeight(48.0f);
                        float f7 = (statFromConfig / f4) * 600.0f;
                        float f8 = f7;
                        if (f7 > 600.0f) {
                            f8 = 600.0f;
                        }
                        image6.setWidth(f8 - f6);
                        image6.setPosition(f6, 0.0f);
                        image6.setColor(towerStatManager.getColor());
                        image6.getColor().f889a = 0.78f;
                        group.addActor(image6);
                        Image image7 = new Image(Game.i.assetManager.getDrawable(towerStatManager.getIconDrawableAlias()));
                        image7.setSize(32.0f, 32.0f);
                        image7.setPosition(48.0f, 8.0f);
                        group.addActor(image7);
                        Label label11 = new Label(towerStatManager.getName(), Game.i.assetManager.getLabelStyle(21));
                        label11.setPosition(104.0f, 0.0f);
                        label11.setSize(320.0f, 48.0f);
                        label11.setWrap(true);
                        group.addActor(label11);
                        Table table = new Table();
                        table.setPosition(0.0f, 0.0f);
                        table.setSize(560.0f, 48.0f);
                        group.addActor(table);
                        table.add().height(64.0f).expandX().fillX();
                        Label label12 = new Label(((Object) StringFormatter.compactNumber(statFromConfig2, true)) + " ->  ", Game.i.assetManager.getLabelStyle(21));
                        table.add((Table) label12).height(48.0f);
                        label12.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        table.add((Table) new Label(StringFormatter.compactNumber(statFromConfig, true), Game.i.assetManager.getLabelStyle(24))).height(48.0f);
                        if (Game.i.towerManager.getStatConfig(towerType, towerStatType).unique) {
                            image5.getColor().f889a = 0.28f;
                            image6.getColor().f889a = 0.42f;
                            image7.setColor(MaterialColor.AMBER.P600);
                            label11.setColor(MaterialColor.AMBER.P600);
                        }
                    }
                }
            }
            if (research.type == ResearchType.ROOT) {
                float f9 = f - 52.0f;
                K.setLength(0);
                int i7 = 0;
                int i8 = 0;
                boolean isEndless = DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode());
                Array<Research> instances = Game.i.researchManager.getInstances();
                for (int i9 = 0; i9 < instances.size; i9++) {
                    Research research2 = instances.get(i9);
                    if (research2.priceInStars == 0 && research2.type != ResearchType.DEVELOPER_MODE) {
                        if (isEndless) {
                            i7 += research2.getInstalledLevel();
                            i8 += research2.getMaxLevel();
                        } else {
                            i7 += Math.min(research2.getMaxRegularLevel(), research2.getInstalledLevel());
                            i8 += research2.getMaxRegularLevel();
                        }
                    }
                }
                Image image8 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image8.setSize(600.0f, 52.0f);
                image8.setPosition(0.0f, f9);
                this.m.addActor(image8);
                Image image9 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image9.setSize(600.0f * (i7 / i8), 52.0f);
                image9.setPosition(0.0f, f9);
                this.m.addActor(image9);
                if (isEndless) {
                    image8.setColor(MaterialColor.DEEP_ORANGE.P900.cpy().lerp(Color.BLACK, 0.1f));
                    image9.setColor(MaterialColor.DEEP_ORANGE.P800);
                } else {
                    image8.setColor(MaterialColor.BLUE.P900.cpy().lerp(Color.BLACK, 0.1f));
                    image9.setColor(MaterialColor.BLUE.P800);
                }
                K.append(StringFormatter.commaSeparatedNumber(i7)).append(" / ").append(StringFormatter.commaSeparatedNumber(i8));
                Image image10 = new Image(Game.i.assetManager.getDrawable("icon-research"));
                image10.setSize(32.0f, 32.0f);
                image10.setPosition(40.0f, f9 + 10.0f);
                this.m.addActor(image10);
                Label label13 = new Label(Game.i.progressManager.getDifficultyName(Game.i.progressManager.getDifficultyMode()), this.g);
                label13.setPosition(84.0f, f9);
                label13.setSize(100.0f, 52.0f);
                this.m.addActor(label13);
                Label label14 = new Label(K, this.g);
                label14.setSize(101.0f, 52.0f);
                label14.setPosition(459.0f, f9);
                label14.setAlignment(16);
                this.m.addActor(label14);
                this.A.setVisible(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (this.f != z) {
            this.f = z;
            if (z) {
                this.e.show();
                update();
            } else {
                this.e.hide();
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.c.removeListener(this.H);
        this.d.removeListener(this.I);
        Game.i.researchManager.removeListener(this.J);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/ResearchMenu$_SideMenuListener.class */
    public class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(ResearchMenu researchMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            ResearchMenu.this.update();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/ResearchMenu$_ResearchesScreenListener.class */
    public class _ResearchesScreenListener extends ResearchesScreen.ResearchesScreenListener.ResearchesScreenListenerAdapter {
        private _ResearchesScreenListener() {
        }

        /* synthetic */ _ResearchesScreenListener(ResearchMenu researchMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.screens.ResearchesScreen.ResearchesScreenListener.ResearchesScreenListenerAdapter, com.prineside.tdi2.screens.ResearchesScreen.ResearchesScreenListener
        public void selectedResearchChanged() {
            if (ResearchMenu.this.d.selectedResearch == null) {
                ResearchMenu.this.a(false);
            } else {
                ResearchMenu.this.a(true);
                ResearchMenu.this.update();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/ResearchMenu$_ResearchManagerListener.class */
    public class _ResearchManagerListener extends ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter {
        private _ResearchManagerListener() {
        }

        /* synthetic */ _ResearchManagerListener(ResearchMenu researchMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter, com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
        public void researchesUpdated() {
            ResearchMenu.this.update();
        }

        @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter, com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
        public void researchStarted(Research research, long j) {
            ResearchMenu.this.update();
        }

        @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter, com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
        public void researchCompleted(Research research) {
            ResearchMenu.this.update();
        }
    }
}
