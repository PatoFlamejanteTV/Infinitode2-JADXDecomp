package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.Render;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.TableButton;
import com.prineside.tdi2.ui.actors.Window;
import com.prineside.tdi2.ui.shared.stateDebugger.EnemiesView;
import com.prineside.tdi2.ui.shared.stateDebugger.GameListenersView;
import com.prineside.tdi2.ui.shared.stateDebugger.GlobalListenersView;
import com.prineside.tdi2.ui.shared.stateDebugger.UtilitiesView;
import com.prineside.tdi2.utils.MaterialColor;
import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/StateDebugger.class */
public class StateDebugger extends UiManager.UiComponent.Adapter {
    public static final int WINDOW_CONTENTS_PADDING = 12;
    public static final int WINDOW_CONTENTS_PADDING_TOP = 8;
    public Window window;
    public Table contentTable;
    private String c;

    /* renamed from: a, reason: collision with root package name */
    private final HashMap<String, View> f3746a = new HashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private final Array<String> f3747b = new Array<>();
    private final HashMap<String, TableButton> d = new HashMap<>();
    private final Listener<Render> e = render -> {
        renderWindow();
    };

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/StateDebugger$View.class */
    public interface View {
        String getId();

        String getName();

        void rebuildWindow();

        void init();

        void postInit();

        void onShow();

        void onHide();

        void onRender();
    }

    public static StateDebugger i() {
        return (StateDebugger) Game.i.uiManager.getComponent(StateDebugger.class);
    }

    public StateDebugger() {
        registerView(new GameListenersView());
        registerView(new GlobalListenersView());
        registerView(new UtilitiesView());
        registerView(new EnemiesView());
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public boolean isPersistent() {
        return true;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        if (this.window != null) {
            this.window.close();
            this.window = null;
        }
        Game.EVENTS.getListeners(Render.class).remove(this.e);
    }

    public static void tableRowSep(Table table, int i) {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        table.add((Table) image).height(1.0f).colspan(i).fillX().row();
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(1.0f, 1.0f, 1.0f, 0.14f);
        table.add((Table) image2).height(1.0f).colspan(i).fillX().row();
    }

    @Null
    public GameSystemProvider getCurrentSystemProvider() {
        if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
            return ((GameScreen) Game.i.screenManager.getCurrentScreen()).S;
        }
        return null;
    }

    public void registerView(View view) {
        this.f3746a.put(view.getId(), view);
        if (!this.f3747b.contains(view.getId(), false)) {
            this.f3747b.add(view.getId());
        }
    }

    public String getCurrentViewId() {
        return this.c == null ? this.f3747b.first() : this.c;
    }

    public View getCurrentView() {
        return getView(getCurrentViewId());
    }

    public View getView(String str) {
        return this.f3746a.get(str);
    }

    public void renderWindow() {
        if (this.window.isVisible()) {
            getCurrentView().onRender();
            this.window.clampWindowPosition();
        }
    }

    public void rebuildWindow() {
        this.contentTable.clear();
        Array.ArrayIterator<String> it = this.f3747b.iterator();
        while (it.hasNext()) {
            String next = it.next();
            this.d.get(next).setEnabled(!getCurrentViewId().equals(next));
        }
        getCurrentView().rebuildWindow();
        this.window.clampWindowPosition();
        renderWindow();
    }

    public void setTab(String str) {
        Preconditions.checkNotNull(str, "viewId is null");
        if (this.c != null) {
            getCurrentView().onHide();
        }
        this.c = str;
        rebuildWindow();
        getCurrentView().onShow();
    }

    public void show() {
        if (!Config.isModdingMode() && !Game.i.progressManager.isDeveloperModeEnabled()) {
            Dialog.i().showAlert("Developer mode research required");
            return;
        }
        hide();
        Game.EVENTS.getListeners(Render.class).add(this.e).setName("StateDebugger").setDescription("Calls renderWindow");
        Window.WindowStyle createDefaultWindowStyle = Game.i.assetManager.createDefaultWindowStyle();
        createDefaultWindowStyle.resizeable = true;
        createDefaultWindowStyle.inheritWidgetMinSize = false;
        this.window = new Window(createDefaultWindowStyle);
        this.window.setTitle("State debugger");
        this.window.minWidth = 100.0f;
        this.window.minHeight = 250.0f;
        Game.i.uiManager.addWindow(this.window);
        this.window.showAtCursor();
        this.window.addListener(new Window.WindowListener.Adapter() { // from class: com.prineside.tdi2.ui.shared.StateDebugger.1
            @Override // com.prineside.tdi2.ui.actors.Window.WindowListener.Adapter, com.prineside.tdi2.ui.actors.Window.WindowListener
            public void closed() {
                StateDebugger.this.getCurrentView().onHide();
                Game.i.uiManager.stage.setScrollFocus(null);
                StateDebugger.this.window.remove();
            }
        });
        Table table = new Table();
        Table table2 = new Table();
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        image.setFillParent(true);
        table2.addActor(image);
        table.add(table2).expandX().fillX().row();
        table2.add().height(1.0f).width(4.0f);
        Array.ArrayIterator<String> it = this.f3747b.iterator();
        while (it.hasNext()) {
            String next = it.next();
            TableButton tableButton = new TableButton(() -> {
                setTab(next);
            });
            table2.add(tableButton).height(32.0f).padLeft(1.0f).padRight(1.0f).padTop(4.0f);
            tableButton.setName("StateDebugger_tab_" + next);
            tableButton.setContentColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700, Color.WHITE);
            tableButton.setBackgroundDrawable(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            tableButton.setBackgroundColors(new Color(), new Color(), new Color(), new Color(0.1f, 0.1f, 0.1f, 1.0f));
            tableButton.add((TableButton) new Label(getView(next).getName(), Game.i.assetManager.getLabelStyle(18))).padLeft(8.0f).padRight(8.0f);
            this.d.put(next, tableButton);
        }
        table2.add().height(1.0f).growX();
        table2.add().height(1.0f).width(4.0f);
        this.contentTable = new Table();
        table.add(this.contentTable).pad(12.0f).padTop(8.0f).expand().fill();
        Array.ArrayIterator<String> it2 = this.f3747b.iterator();
        while (it2.hasNext()) {
            getView(it2.next()).init();
        }
        setTab(this.f3747b.first());
        Array.ArrayIterator<String> it3 = this.f3747b.iterator();
        while (it3.hasNext()) {
            getView(it3.next()).postInit();
        }
        this.window.setContents(table);
        this.window.fitToContentSimple();
    }
}
