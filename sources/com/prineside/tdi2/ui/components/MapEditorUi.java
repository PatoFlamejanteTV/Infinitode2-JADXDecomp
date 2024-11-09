package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.events.mapEditor.HistoryUpdate;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.TableButton;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorUi.class */
public class MapEditorUi implements Disposable {

    /* renamed from: b, reason: collision with root package name */
    private Label f3345b;
    private GameSystemProvider c;
    private Table d;
    private PaddedImageButton e;
    private ToolButton f;
    private ToolButton g;
    private Table h;
    private PaddedImageButton i;
    private PaddedImageButton j;
    private LabelButton k;
    private boolean l;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3344a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 102, "MapEditorUi");
    public final Array<ToolButton> toolButtons = new Array<>(true, 1, ToolButton.class);
    private final Array<PreparedTooltip> m = new Array<>(true, 1, PreparedTooltip.class);
    private final Runnable n = this::d;

    public MapEditorUi(GameSystemProvider gameSystemProvider) {
        String str;
        this.c = gameSystemProvider;
        gameSystemProvider.events.getListeners(HistoryUpdate.class).add(historyUpdate -> {
            Game.i.uiManager.runOnStageActOnce(this.n);
        }).setDescription("MapEditorUi - updates prestige score");
        Game.i.settingsManager.getScaledViewportHeight();
        Table table = this.f3344a.getTable();
        table.setName("main_map_editor_ui");
        Group group = new Group();
        group.setTransform(false);
        group.setSize(144.0f, Game.i.settingsManager.getScaledViewportHeight());
        group.setTouchable(Touchable.childrenOnly);
        table.add((Table) group).expand().top().left().row();
        if (Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.PRESTIGE_MODE)) {
            Image image = new Image(Game.i.assetManager.getDrawable("icon-crown"));
            image.setPosition(170.0f, Game.i.settingsManager.getScaledViewportHeight() - 80.0f);
            image.setSize(32.0f, 32.0f);
            image.setColor(MaterialColor.AMBER.P500);
            group.addActor(image);
            this.f3345b = new Label("0 mP", Game.i.assetManager.getLabelStyle(24));
            this.f3345b.setPosition(214.0f, Game.i.settingsManager.getScaledViewportHeight() - 80.0f);
            this.f3345b.setColor(MaterialColor.AMBER.P500);
            group.addActor(this.f3345b);
            this.f3345b.setTouchable(Touchable.disabled);
        }
        d();
        float scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight();
        Table table2 = new Table();
        table2.setSize(144.0f, scaledViewportHeight);
        group.addActor(table2);
        Image image2 = new Image(Game.i.assetManager.getQuad("ui.components.mapEditorUi.toolPaneBackground"));
        image2.setSize(144.0f, scaledViewportHeight);
        table2.addActor(image2);
        Table table3 = new Table();
        table2.add(table3).width(144.0f).row();
        this.e = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-house"), () -> {
            gameSystemProvider._mapEditor.goToPreviousScreen();
        }, new Color(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P600).setIconPosition(24.0f, 16.0f).setIconSize(96.0f, 96.0f);
        table3.add((Table) this.e).size(144.0f, 144.0f).row();
        this.f = new ToolButton(Game.i.assetManager.getDrawable("icon-triangle-right"), () -> {
            gameSystemProvider._mapEditor.startMap();
        }, null);
        table3.add(this.f).size(144.0f, 96.0f).row();
        this.g = new ToolButton(Game.i.assetManager.getDrawable("icon-info"), this::b, null);
        table3.add(this.g).size(144.0f, 96.0f).row();
        this.h = new Table();
        table3.add(this.h).size(144.0f, 96.0f).row();
        this.i = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-left-hollow"), () -> {
            gameSystemProvider._mapEditor.historyBack();
        }, new Color(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500);
        this.i.setIconSize(48.0f, 48.0f);
        this.i.setIconPosition(18.0f, 24.0f);
        this.i.setDisabledColor(new Color(0.0f, 0.0f, 0.0f, 0.56f));
        this.h.add((Table) this.i).size(72.0f, 96.0f);
        this.j = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-right-hollow"), () -> {
            gameSystemProvider._mapEditor.historyForward();
        }, new Color(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500);
        this.j.setIconSize(48.0f, 48.0f);
        this.j.setIconPosition(6.0f, 24.0f);
        this.j.setDisabledColor(new Color(0.0f, 0.0f, 0.0f, 0.56f));
        this.h.add((Table) this.j).size(72.0f, 96.0f);
        this.k = new LabelButton("Ctrl", Game.i.assetManager.getLabelStyle(24), this::toggleCtrlButton);
        this.k.setAlignment(1);
        table3.add((Table) this.k).size(144.0f, 48.0f).row();
        toggleCtrlButton();
        toggleCtrlButton();
        this.d = new Table();
        ScrollPane scrollPane = new ScrollPane(this.d, Game.i.assetManager.getScrollPaneStyle(0.0f));
        scrollPane.setScrollingDisabled(true, false);
        table2.add((Table) scrollPane).growY().width(144.0f);
        if (gameSystemProvider._mapEditor.basicLevelEditor) {
            str = Game.i.localeManager.i18n.get("basic_level");
        } else {
            str = Game.i.localeManager.i18n.get("custom_map");
        }
        Label label = new Label(str + ": " + (gameSystemProvider._mapEditor.basicLevelEditor ? gameSystemProvider._mapEditor.basicLevel.name : gameSystemProvider._mapEditor.userMap.name), Game.i.assetManager.getLabelStyle(18));
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label.setPosition(166.0f, 20.0f);
        label.setTouchable(Touchable.disabled);
        group.addActor(label);
        a();
        gameSystemProvider.events.getListeners(HistoryUpdate.class).add(historyUpdate2 -> {
            a();
        });
    }

    public boolean isCtrlButtonEnabled() {
        return this.l;
    }

    public void toggleCtrlButton() {
        this.l = !this.l;
        if (this.l) {
            this.k.setColors(MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIGHT_GREEN.P300);
            this.k.setStyle(Game.i.assetManager.getLabelStyle(30));
        } else {
            this.k.setColors(new Color(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P300);
            this.k.setStyle(Game.i.assetManager.getLabelStyle(24));
        }
    }

    private void a() {
        this.i.setEnabled(this.c._mapEditor.hasHistoryBack());
        this.j.setEnabled(this.c._mapEditor.hasHistoryForward());
    }

    private void b() {
        TooltipsOverlay.i().hideAll(false);
        this.m.clear();
        this.m.add(new PreparedTooltip("map_editor_1", this.e, Game.i.localeManager.i18n.get("map_editor_tooltip_home_button")));
        this.m.add(new PreparedTooltip("map_editor_2", this.f, Game.i.localeManager.i18n.get("map_editor_tooltip_play_button")));
        this.m.add(new PreparedTooltip("map_editor_3", this.g, Game.i.localeManager.i18n.get("map_editor_tooltip_help_button")));
        this.m.add(new PreparedTooltip("map_editor_4", this.h, Game.i.localeManager.i18n.get("map_editor_tooltip_history_buttons")));
        Array.ArrayIterator<MapEditorSystem.Tool> it = this.c._mapEditor.getTools().iterator();
        while (it.hasNext()) {
            PreparedTooltip tooltip = it.next().getTooltip();
            if (tooltip != null) {
                this.m.add(tooltip);
            }
        }
        c();
    }

    private void c() {
        if (this.m.size != 0) {
            PreparedTooltip removeIndex = this.m.removeIndex(0);
            if (removeIndex != null) {
                TooltipsOverlay.i().showText(removeIndex.id, removeIndex.forActor, removeIndex.message, this.f3344a.mainUiLayer, this.f3344a.zIndex + 1, 16).onDispose = this::c;
            } else {
                c();
            }
        }
    }

    private void d() {
        if (this.f3345b != null) {
            double percentValueAsMultiplier = Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.PRESTIGE_DUST_DROP_RATE);
            String str = ((Object) StringFormatter.commaSeparatedNumber(StrictMath.round(this.c.map.getMap().getPrestigeScore() * percentValueAsMultiplier * 1000.0d))) + " mP";
            if (percentValueAsMultiplier > 1.0d) {
                str = str + " [#808080](x" + ((Object) StringFormatter.compactNumberWithPrecision(percentValueAsMultiplier, 2)) + ")[]";
            }
            this.f3345b.setText(str);
        }
    }

    public void addToolButton(ToolButton toolButton) {
        this.toolButtons.add(toolButton);
        updateButtonsLayout();
    }

    public void updateButtonsLayout() {
        this.d.clear();
        this.d.add().width(1.0f).growY().minHeight(24.0f).row();
        Array.ArrayIterator<ToolButton> it = this.toolButtons.iterator();
        while (it.hasNext()) {
            this.d.add(it.next()).size(144.0f, 104.0f).row();
        }
        this.d.row();
        this.d.add().width(1.0f).height(24.0f);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3344a);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorUi$ToolButton.class */
    public static class ToolButton extends TableButton {
        public static final float WIDTH = 144.0f;
        public static final float HEIGHT = 104.0f;
        public static final float ICON_SIZE = 64.0f;
        public static final float ICON_SIZE_ACTIVE = 80.0f;
        private Drawable k;

        public ToolButton(Drawable drawable, Runnable runnable, Runnable runnable2) {
            super(runnable, runnable2);
            this.k = drawable;
            setBackgroundDrawable(Game.i.assetManager.getQuad("ui.components.mapEditorUi.toolButtonActive"));
            setActive(false);
        }

        public void setActive(boolean z) {
            clearChildren();
            if (z) {
                add((ToolButton) new Image(this.k)).size(80.0f);
                Color color = MaterialColor.LIGHT_GREEN.P500;
                Color color2 = MaterialColor.LIGHT_GREEN.P500;
                setContentColors(color, color, color2, color2);
                setBackgroundDrawable(Game.i.assetManager.getQuad("ui.components.mapEditorUi.toolButtonActive"));
                return;
            }
            add((ToolButton) new Image(this.k)).size(64.0f);
            setContentColors(new Color(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500, Color.BLACK);
            setBackgroundDrawable(null);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorUi$PreparedTooltip.class */
    public static final class PreparedTooltip {
        public String id;
        public Actor forActor;
        public CharSequence message;

        public PreparedTooltip() {
        }

        public PreparedTooltip(String str, Actor actor, CharSequence charSequence) {
            this.id = str;
            this.forActor = actor;
            this.message = charSequence;
        }
    }
}
