package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.BasicLevelStage;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.CheckBox;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LevelStagesEditor.class */
public class LevelStagesEditor extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3682a = TLog.forClass(LevelStagesEditor.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3683b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 142, "LevelStagesEditor main");
    private CheckBox.CheckBoxStyle c = new CheckBox.CheckBoxStyle(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.RED.P500), Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.GREEN.P500), Game.i.assetManager.getFont(24), Color.WHITE);
    private SelectBox.SelectBoxStyle d;
    private TextField.TextFieldStyle e;
    private BasicLevelStage f;
    private Table g;
    private Table h;

    public static LevelStagesEditor i() {
        return (LevelStagesEditor) Game.i.uiManager.getComponent(LevelStagesEditor.class);
    }

    public LevelStagesEditor() {
        this.c.checkboxOff.setLeftWidth(24.0f);
        this.c.checkboxOff.setBottomHeight(24.0f);
        this.d = Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getFont(24), true);
        this.e = Game.i.assetManager.getTextFieldStyle(24);
        Group group = new Group();
        group.setTransform(false);
        this.f3683b.getTable().add((Table) group).size(1200.0f, 1000.0f);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(1200.0f, 1000.0f);
        image.setColor(new Color(858993663));
        group.addActor(image);
        this.g = new Table();
        ScrollPane scrollPane = new ScrollPane(this.g, Game.i.assetManager.getScrollPaneStyle(8.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(240.0f, 1000.0f);
        scrollPane.setScrollingDisabled(true, false);
        group.addActor(scrollPane);
        a();
        this.h = new Table();
        this.h.setSize(900.0f, 906.0f);
        ScrollPane scrollPane2 = new ScrollPane(this.h, Game.i.assetManager.getScrollPaneStyle(16.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane2);
        scrollPane2.setSize(940.0f, 906.0f);
        scrollPane2.setPosition(260.0f, 84.0f);
        group.addActor(scrollPane2);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setPosition(250.0f, 10.0f);
        group.addActor(group2);
        RectButton rectButton = new RectButton("Save", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.basicLevelManager.saveStagesConfigOnDisk();
            Label label = new Label("Done!", Game.i.assetManager.getLabelStyle(24));
            label.setColor(MaterialColor.GREEN.P500);
            label.setPosition(740.0f, 0.0f);
            label.setSize(200.0f, 64.0f);
            label.setAlignment(1);
            label.setTouchable(Touchable.disabled);
            group2.addActor(label);
            label.addAction(Actions.sequence(Actions.moveBy(0.0f, 64.0f, 0.3f, Interpolation.exp5Out), Actions.fadeOut(0.2f), Actions.removeActor()));
            b();
        });
        rectButton.setSize(200.0f, 64.0f);
        rectButton.setPosition(740.0f, 0.0f);
        group2.addActor(rectButton);
        b();
        hide();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public boolean isPersistent() {
        return true;
    }

    public void show() {
        if (!Config.isModdingMode() && !Game.i.progressManager.isDeveloperModeEnabled()) {
            Dialog.i().showAlert("Developer mode research required");
            return;
        }
        DarkOverlay.i().addCallerOverlayLayer("LevelStagesEditor", this.f3683b.zIndex - 1, () -> {
            hide();
            return true;
        });
        b();
        this.f3683b.getTable().setVisible(true);
    }

    private void a() {
        this.g.clear();
        Array.ArrayIterator<BasicLevelStage> it = Game.i.basicLevelManager.stagesOrdered.iterator();
        while (it.hasNext()) {
            BasicLevelStage next = it.next();
            RectButton rectButton = new RectButton(next.name + " - " + next.getTitle(), Game.i.assetManager.getLabelStyle(24), () -> {
                this.f = next;
                b();
            });
            if (this.f == next) {
                Color color = Color.WHITE;
                rectButton.setBackgroundColors(color, color, Color.WHITE, MaterialColor.AMBER.P900);
                rectButton.setEnabled(false);
            }
            Color color2 = Color.WHITE;
            Color color3 = Color.WHITE;
            rectButton.setIconLabelColors(color2, color2, color3, color3);
            this.g.add((Table) rectButton).size(240.0f, 48.0f).padBottom(2.0f).row();
        }
        this.g.add().width(1.0f).growY().row();
        RectButton rectButton2 = new RectButton("Add stage", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.LevelStagesEditor.1
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    Threads.i().runOnMainThread(() -> {
                        if (str.length() < 2 || str.contains("/") || str.contains("\\")) {
                            Dialog.i().showAlert("0-9, a-Z and dot, minimum 2 characters");
                            return;
                        }
                        if (Game.i.basicLevelManager.getStage(str) != null) {
                            Dialog.i().showAlert("Stage with this name already exists");
                            return;
                        }
                        BasicLevelStage basicLevelStage = new BasicLevelStage(str, Color.WHITE, "New stage");
                        Game.i.basicLevelManager.addStage(basicLevelStage);
                        LevelStagesEditor.this.f = basicLevelStage;
                        LevelStagesEditor.this.b();
                    });
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, "Stage ID", "", "0-9, a-Z and dot, minimum 2 characters. ID can't be changed, choose wisely");
        });
        Color color4 = Color.WHITE;
        Color color5 = Color.WHITE;
        rectButton2.setIconLabelColors(color4, color4, color5, color5);
        rectButton2.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.WHITE);
        this.g.add((Table) rectButton2).size(240.0f, 48.0f).padBottom(2.0f).padTop(8.0f).row();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.h.clearChildren();
        if (this.f == null) {
            this.f = Game.i.basicLevelManager.stagesOrdered.first();
        }
        a();
        Group group = new Group();
        group.setTransform(false);
        this.h.add((Table) group).expandX().fillX().padTop(20.0f).height(40.0f).row();
        Table table = new Table();
        table.setSize(700.0f, 40.0f);
        table.setPosition(200.0f, 0.0f);
        group.addActor(table);
        Table table2 = new Table();
        table2.add().height(1.0f).growX();
        table.add(table2).growX().row();
        RectButton rectButton = new RectButton("Up", Game.i.assetManager.getLabelStyle(21), () -> {
            int indexOf = Game.i.basicLevelManager.stagesOrdered.indexOf(this.f, true);
            if (indexOf > 0) {
                Game.i.basicLevelManager.stagesOrdered.swap(indexOf, indexOf - 1);
                b();
            }
        });
        table2.add((Table) rectButton).size(150.0f, 32.0f).padLeft(10.0f);
        if (Game.i.basicLevelManager.stagesOrdered.indexOf(this.f, true) == 0) {
            rectButton.setEnabled(false);
        }
        RectButton rectButton2 = new RectButton("Down", Game.i.assetManager.getLabelStyle(21), () -> {
            int indexOf = Game.i.basicLevelManager.stagesOrdered.indexOf(this.f, true);
            if (indexOf < Game.i.basicLevelManager.stagesOrdered.size - 1) {
                Game.i.basicLevelManager.stagesOrdered.swap(indexOf, indexOf + 1);
                b();
            }
        });
        table2.add((Table) rectButton2).size(150.0f, 32.0f).padLeft(10.0f);
        if (Game.i.basicLevelManager.stagesOrdered.indexOf(this.f, true) == Game.i.basicLevelManager.stagesOrdered.size - 1) {
            rectButton2.setEnabled(false);
        }
        table2.add((Table) new RectButton("Delete", Game.i.assetManager.getLabelStyle(21), () -> {
            if (this.f.levels.size != 0) {
                Notifications.i().addFailure(this.f.levels.size + " levels are assigned to this stage, can't delete it");
            } else {
                Dialog.i().showConfirm("Delete this stage?", () -> {
                    try {
                        Game.i.basicLevelManager.removeStage(this.f);
                        this.f = Game.i.basicLevelManager.stagesOrdered.first();
                        b();
                    } catch (Exception e) {
                        f3682a.e("failed to delete stage " + this.f.name, e);
                    }
                });
            }
        })).size(150.0f, 32.0f).padLeft(10.0f);
        a("Stage ID");
        hint("Levels will refer to this stage by its ID");
        textField(this.f.name, str -> {
            this.f.name = str;
            a();
        });
        c();
        a("Stage title");
        hint("String alias (from i18n) or a regular string (which won't be translated)");
        textField(this.f.titleAlias, str2 -> {
            this.f.titleAlias = str2;
            a();
        });
        c();
        a("Color");
        textField(this.f.color.toString().substring(0, 6), str3 -> {
            try {
                Color.rgb888ToColor(this.f.color, Integer.parseInt(str3, 16));
                a();
            } catch (Exception e) {
                f3682a.e("invalid value: " + str3, e);
            }
        });
        c();
        a("Is regular", this.f.regular, bool -> {
            this.f.regular = bool.booleanValue();
            a();
        });
        hint("If stage is not regular, it won't be affected by quest difficulty research, its quests will not count towards quests prestige, its quests can not be skipped with accelerators, its quests won't be used for daily quests");
        a("Visibility requirements");
        LevelConfigurationEditor.requirements(this.f.showRequirements, this.h, this.d, this.e, this::b);
        this.h.row();
        this.h.add().width(1.0f).growY().row();
    }

    public Label hint(String str) {
        Label label = new Label(str, Game.i.assetManager.getLabelStyle(21));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label.setWrap(true);
        label.setAlignment(8);
        this.h.add((Table) label).top().left().pad(-6.0f, 0.0f, 10.0f, 0.0f).top().left().width(900.0f).row();
        return label;
    }

    private LabelToggleButton a(String str, boolean z, ObjectConsumer<Boolean> objectConsumer) {
        return a(true, str, z, objectConsumer);
    }

    private LabelToggleButton a(boolean z, String str, boolean z2, ObjectConsumer<Boolean> objectConsumer) {
        LabelToggleButton labelToggleButton = new LabelToggleButton(str, z2, 24, 32.0f, objectConsumer);
        this.h.add(labelToggleButton).height(48.0f).top().left().row();
        return labelToggleButton;
    }

    private Label a(String str) {
        Label label = new Label(str, Game.i.assetManager.getLabelStyle(24));
        this.h.add((Table) label).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).top().left().row();
        return label;
    }

    private void c() {
        this.h.add().width(1.0f).height(10.0f).row();
    }

    public TextFieldXPlatform textField(String str, final ObjectConsumer<String> objectConsumer) {
        final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(str, this.e);
        textFieldXPlatform.setSize(400.0f, 64.0f);
        this.h.add((Table) textFieldXPlatform).top().left().size(300.0f, 48.0f).row();
        textFieldXPlatform.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelStagesEditor.2
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                objectConsumer.accept(textFieldXPlatform.getText());
            }
        });
        return textFieldXPlatform;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        DarkOverlay.i().removeCaller("LevelStagesEditor");
        this.f3683b.getTable().setVisible(false);
        Game.i.uiManager.stage.unfocusAll();
    }
}
