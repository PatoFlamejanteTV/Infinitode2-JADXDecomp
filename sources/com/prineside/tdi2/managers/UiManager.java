package com.prineside.tdi2.managers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.events.global.GameLoad;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.events.global.VisibleDisplayFrameChange;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.ScreenManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.FocusListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.actors.HighlightActor;
import com.prineside.tdi2.ui.actors.Window;
import com.prineside.tdi2.ui.shared.DeveloperConsole;
import com.prineside.tdi2.ui.shared.InventoryOverlay;
import com.prineside.tdi2.ui.shared.TextInputOverlay;
import com.prineside.tdi2.ui.shared.WebBrowser;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Comparator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UiManager.class */
public final class UiManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2491a = TLog.forClass(UiManager.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Comparator<UiLayer> f2492b = (uiLayer, uiLayer2) -> {
        if (uiLayer.zIndex == uiLayer2.zIndex) {
            return 0;
        }
        return uiLayer.zIndex < uiLayer2.zIndex ? -1 : 1;
    };
    private final UiLayer c;
    private final UiLayer d;
    private final UiLayer h;
    private final Group i;
    private int j;
    private float k;
    public ParticleEffectPool itemCellFlashParticles;
    public final Array<UiLayer>[] layers = new Array[MainUiLayer.values.length];
    public final Group[] mainLayerGroups = new Group[MainUiLayer.values.length];
    private final ObjectMap<Class<? extends UiComponent>, UiComponent> e = new ObjectMap<>();
    private final Array<UiComponent> f = new Array<>(true, 1, UiComponent.class);
    private final Array<Runnable> g = new Array<>(true, 1, Runnable.class);
    private Drawable[][] l = new Drawable[2][RarityType.values.length];
    private Drawable[][] m = new Drawable[2][RarityType.values.length];
    public Drawable[] itemCellShapes = new Drawable[2];
    private final _ScreenManagerListener n = new _ScreenManagerListener(this, 0);
    private final boolean[] o = new boolean[256];
    public final ScreenViewport viewport = new ScreenViewport();
    public final Stage stage = new Stage(this.viewport, Game.i.renderingManager.batch) { // from class: com.prineside.tdi2.managers.UiManager.1
        private int c;
        private int d;
        private int e;
        private boolean f;

        @Override // com.prineside.tdi2.scene2d.Stage, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean keyDown(int i) {
            if (i < UiManager.this.o.length) {
                UiManager.this.o[i] = true;
            }
            return super.keyDown(i);
        }

        @Override // com.prineside.tdi2.scene2d.Stage, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean keyUp(int i) {
            if (i < UiManager.this.o.length) {
                UiManager.this.o[i] = false;
            }
            return super.keyUp(i);
        }

        @Override // com.prineside.tdi2.scene2d.Stage, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchDown(int i, int i2, int i3, int i4) {
            Vector2 vector2 = new Vector2(i, i2);
            screenToStageCoordinates(vector2);
            this.c = i;
            this.d = i2;
            this.e = 0;
            this.f = UiManager.this.stage.hit(vector2.x, vector2.y, true) instanceof TextField;
            return super.touchDown(i, i2, i3, i4);
        }

        @Override // com.prineside.tdi2.scene2d.Stage, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchDragged(int i, int i2, int i3) {
            this.e += Math.abs(i - this.c);
            this.e += Math.abs(i2 - this.d);
            return super.touchDragged(i, i2, i3);
        }

        @Override // com.prineside.tdi2.scene2d.Stage, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchUp(int i, int i2, int i3, int i4) {
            if (!this.f && this.e < 14 && (UiManager.this.stage.getKeyboardFocus() instanceof TextField)) {
                Vector2 screenToStageCoordinates = UiManager.this.stage.screenToStageCoordinates(new Vector2(i, i2));
                if (!(UiManager.this.stage.hit(screenToStageCoordinates.x, screenToStageCoordinates.y, true) instanceof TextField)) {
                    UiManager.this.stage.setKeyboardFocus(null);
                }
            }
            return super.touchUp(i, i2, i3, i4);
        }
    };

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UiManager$MainUiLayer.class */
    public enum MainUiLayer {
        SCREEN,
        SHARED_COMPONENTS,
        OVERLAY;

        public static final MainUiLayer[] values = values();
    }

    static {
        new Vector2();
        new StringBuilder();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UiManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<UiManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public UiManager read() {
            return Game.i.uiManager;
        }
    }

    public final <T extends UiComponent> T getComponent(Class<T> cls) {
        UiComponent uiComponent = this.e.get(cls);
        UiComponent uiComponent2 = uiComponent;
        if (uiComponent == null) {
            try {
                uiComponent2 = (UiComponent) ReflectionUtils.newObject(cls);
                this.e.put(cls, uiComponent2);
                this.f.add(uiComponent2);
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to initialize UI component instance for type '" + cls + "'", e);
            }
        }
        return (T) uiComponent2;
    }

    public final <T extends UiComponent> boolean isComponentInit(Class<T> cls) {
        return this.e.containsKey(cls);
    }

    public final <T extends UiComponent> void disposeComponent(Class<T> cls) {
        UiComponent uiComponent = this.e.get(cls);
        if (uiComponent != null) {
            uiComponent.dispose();
            this.e.remove(cls);
            this.f.removeValue(uiComponent, true);
        }
    }

    public UiManager() {
        this.stage.getRoot().setTransform(false);
        this.stage.addListener(new FocusListener(this) { // from class: com.prineside.tdi2.managers.UiManager.2
            @Override // com.prineside.tdi2.scene2d.utils.FocusListener
            public void keyboardFocusChanged(FocusListener.FocusEvent focusEvent, Actor actor, boolean z) {
                if (!z) {
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
            }
        });
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            Gdx.input.setKeyboardHeightObserver(new Input.KeyboardHeightObserver(this) { // from class: com.prineside.tdi2.managers.UiManager.3
                @Override // com.badlogic.gdx.Input.KeyboardHeightObserver
                public void onKeyboardHeightChanged(int i) {
                    UiManager.f2491a.d("onKeyboardHeightChanged %s %s %s", Integer.valueOf(i), Integer.valueOf(Gdx.graphics.getHeight() - i), Float.valueOf(Gdx.graphics.getDensity()));
                    Game.i.notifyVisibleDisplayFrameChanged(0, 0, 0, i);
                }
            });
        }
        for (MainUiLayer mainUiLayer : MainUiLayer.values) {
            Group group = new Group();
            group.setName("main_ui_layer_" + mainUiLayer.name());
            group.setTransform(false);
            group.setTouchable(Touchable.childrenOnly);
            this.mainLayerGroups[mainUiLayer.ordinal()] = group;
            this.stage.addActor(group);
            this.layers[mainUiLayer.ordinal()] = new Array<>(UiLayer.class);
        }
        this.j = Gdx.input.getRotation();
        this.h = addLayer(MainUiLayer.SCREEN, 110, "Highlight actors");
        this.i = new Group();
        this.i.setTransform(false);
        this.i.setTouchable(Touchable.childrenOnly);
        this.h.getTable().add((Table) this.i).expand().fill();
        this.d = addLayer(MainUiLayer.OVERLAY, 990000, "UiManager windows layer");
        this.c = addLayer(MainUiLayer.OVERLAY, 1000000, "UiManager safe area");
        this.itemCellFlashParticles = Game.i.assetManager.getParticleEffectPool("item-cell.prt");
        for (RarityType rarityType : RarityType.values) {
            this.l[0][rarityType.ordinal()] = Game.i.assetManager.getDrawable("item-cell-a-coat-" + rarityType.name());
            this.l[1][rarityType.ordinal()] = Game.i.assetManager.getDrawable("item-cell-b-coat-" + rarityType.name());
            this.m[0][rarityType.ordinal()] = Game.i.assetManager.getDrawable("item-cell-a-coat-" + rarityType.name());
            this.m[1][rarityType.ordinal()] = Game.i.assetManager.getDrawable("item-cell-b-coat-" + rarityType.name());
            if (rarityType == RarityType.VERY_RARE) {
                this.m[0][rarityType.ordinal()] = ((TextureRegionDrawable) this.m[0][rarityType.ordinal()]).tint(MaterialColor.RED.P200);
                this.m[1][rarityType.ordinal()] = ((TextureRegionDrawable) this.m[1][rarityType.ordinal()]).tint(MaterialColor.RED.P200);
            }
        }
        this.itemCellShapes[0] = Game.i.assetManager.getDrawable("item-cell-a-shape");
        this.itemCellShapes[1] = Game.i.assetManager.getDrawable("item-cell-b-shape");
        rebuildLayers();
    }

    public final boolean isStageKeyPressed(int i) {
        if (i < this.o.length) {
            return this.o[i];
        }
        return false;
    }

    public final UiLayer getWindowsLayer() {
        return this.d;
    }

    public final void addWindow(Window window) {
        Preconditions.checkNotNull(window, "window can not be null");
        getWindowsLayer().getTable().addActor(window);
    }

    public final Drawable getItemCellRarityCoat(RarityType rarityType, int i) {
        int i2 = i % 2;
        if (Game.i.settingsManager.cvdFriendlyColors()) {
            return this.m[i2][rarityType.ordinal()];
        }
        return this.l[i2][rarityType.ordinal()];
    }

    public final void getTextInput(Input.TextInputListener textInputListener, String str, String str2, String str3) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            TextInputOverlay.i().show(textInputListener, str, str2, str3);
        } else {
            Gdx.input.getTextInput(textInputListener, str, str2, str3);
        }
    }

    public final void setLogTouchDownsEnabled(boolean z) {
        if (z) {
            Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DISABLE_UI_TOUCH_LOG);
        }
    }

    public final void hideAllComponents() {
        for (int i = 0; i < this.f.size; i++) {
            if (!this.f.get(i).isPersistent()) {
                this.f.get(i).hide();
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void setup() {
        Game.EVENTS.getListeners(GameLoad.class).add(gameLoad -> {
            DeveloperConsole.i();
            Game.i.progressManager.addListener(new ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter(this) { // from class: com.prineside.tdi2.managers.UiManager.4
                @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
                public void developerConsoleEnabled() {
                    DeveloperConsole.i();
                }
            });
        });
        Game.EVENTS.getListeners(ScreenResize.class).addWithFlagsAndPriority(screenResize -> {
            rebuildLayers();
        }, 0, 1000);
        Game.EVENTS.getListeners(VisibleDisplayFrameChange.class).addWithFlagsAndPriority(visibleDisplayFrameChange -> {
            rebuildLayers();
        }, 0, 1000);
        setAsInputHandler();
        Game.i.screenManager.addListener(this.n);
        Game.i.settingsManager.addListener(new SettingsManager.SettingsManagerListener() { // from class: com.prineside.tdi2.managers.UiManager.5
            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void settingsChanged() {
            }

            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void customValueChanged(SettingsManager.CustomValueType customValueType, double d) {
                if (customValueType == SettingsManager.CustomValueType.UI_SCALE) {
                    if (UiManager.this.isComponentInit(WebBrowser.class)) {
                        WebBrowser i = WebBrowser.i();
                        i.dispose();
                        UiManager.this.e.remove(WebBrowser.class);
                        UiManager.this.f.removeValue(i, true);
                    }
                    InventoryOverlay.i().requireLayoutRebuild();
                }
            }

            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void dynamicSettingsChanged() {
            }
        });
    }

    private void a(ObjectSet<String> objectSet, Actor actor) {
        if (actor.getName() != null) {
            if (objectSet.contains(actor.getName())) {
                throw new RuntimeException("Duplicate actor name: " + actor.getName());
            }
            objectSet.add(actor.getName());
        }
        if (actor instanceof Group) {
            SnapshotArray<Actor> children = ((Group) actor).getChildren();
            int i = children.size;
            for (int i2 = 0; i2 < i; i2++) {
                a(objectSet, children.get(i2));
            }
        }
    }

    public final void findDuplicateActorNames() {
        ObjectSet<String> objectSet = new ObjectSet<>();
        for (Group group : this.mainLayerGroups) {
            a(objectSet, group);
        }
    }

    @Null
    public final Actor findActor(String str) {
        for (Group group : this.mainLayerGroups) {
            if (group.getName() != null && group.getName().equals(str)) {
                return group;
            }
            Actor findActor = group.findActor(str);
            if (findActor != null) {
                return findActor;
            }
        }
        return null;
    }

    public final void dumpActorsHierarchy(Group group, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("|");
        }
        if (group == null) {
            for (Group group2 : this.mainLayerGroups) {
                f2491a.i(((Object) stringBuilder) + SequenceUtils.SPACE + group2.getName() + " (Group)", new Object[0]);
                dumpActorsHierarchy(group2, i + 1);
            }
            return;
        }
        SnapshotArray<Actor> children = group.getChildren();
        int i3 = children.size;
        for (int i4 = 0; i4 < i3; i4++) {
            Actor actor = children.get(i4);
            f2491a.i(((Object) stringBuilder) + SequenceUtils.SPACE + actor.getName() + " (" + actor.getClass().getSimpleName() + ")", new Object[0]);
            if (actor instanceof Group) {
                dumpActorsHierarchy((Group) actor, i + 1);
            }
        }
    }

    public final void setAsInputHandler() {
        f2491a.i("stage now handles all input", new Object[0]);
        Gdx.input.setInputProcessor(this.stage);
    }

    public final UiLayer addLayer(MainUiLayer mainUiLayer, int i, String str) {
        return addLayerIgnoreSafeArea(mainUiLayer, i, str, false);
    }

    public final UiLayer addLayerIgnoreSafeArea(MainUiLayer mainUiLayer, int i, String str, boolean z) {
        UiLayer uiLayer = new UiLayer(mainUiLayer, new Table(), i, str, (byte) 0);
        uiLayer.ignoreSafeMargin = z;
        this.layers[mainUiLayer.ordinal()].add(uiLayer);
        rebuildLayers();
        return uiLayer;
    }

    public final void removeLayer(UiLayer uiLayer) {
        this.layers[uiLayer.mainUiLayer.ordinal()].removeValue(uiLayer, true);
        rebuildLayers();
    }

    public final int getScreenWidth() {
        return Math.max(10, Gdx.graphics.getWidth());
    }

    public final int getScreenHeight() {
        return Math.max(10, Gdx.graphics.getHeight());
    }

    public final int getScreenSafeMargin() {
        return Game.i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_SAFE_AREA);
    }

    public final float getRegularLayerWidth() {
        return this.viewport.getWorldWidth() - (getScreenSafeMargin() << 1);
    }

    public final void runOnStageAct(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        this.g.add(runnable);
    }

    public final void runOnStageActOnce(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.g.contains(runnable, true)) {
            return;
        }
        this.g.add(runnable);
    }

    public final void rebuildLayers() {
        if (Game.i.settingsManager == null) {
            f2491a.d("skipped rebuildLayers() - managers are not loaded yet", new Object[0]);
            return;
        }
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();
        if (screenWidth <= 0) {
            screenWidth = 1;
        }
        if (screenHeight <= 0) {
            screenHeight = 1;
        }
        this.viewport.setUnitsPerPixel(Game.i.settingsManager.getScaledViewportHeight() / screenHeight);
        this.viewport.update(screenWidth, screenHeight, true);
        int screenSafeMargin = getScreenSafeMargin();
        if (this.c != null) {
            if (screenSafeMargin == 0) {
                this.c.getTable().setVisible(false);
            } else {
                Table table = this.c.getTable();
                table.setVisible(true);
                table.clearChildren();
                Image image = new Image(Game.i.assetManager.getDrawable("transparent"));
                image.setColor(0.0f, 0.0f, 0.0f, 0.0f);
                table.add((Table) image).width(screenSafeMargin).padLeft(-screenSafeMargin).expandY().fillY();
                image.setTouchable(Touchable.enabled);
                image.addListener(new InputVoid());
                table.add().fillX().expandX();
                Image image2 = new Image(Game.i.assetManager.getDrawable("transparent"));
                image2.setColor(0.0f, 0.0f, 0.0f, 0.0f);
                table.add((Table) image2).width(screenSafeMargin).padRight(-screenSafeMargin).expandY().fillY();
                image2.setTouchable(Touchable.enabled);
                image2.addListener(new InputVoid());
            }
        }
        float worldWidth = this.viewport.getWorldWidth();
        float f = (worldWidth - screenSafeMargin) - screenSafeMargin;
        float worldHeight = this.viewport.getWorldHeight();
        for (MainUiLayer mainUiLayer : MainUiLayer.values) {
            this.mainLayerGroups[mainUiLayer.ordinal()].setSize(worldWidth, worldHeight);
            this.mainLayerGroups[mainUiLayer.ordinal()].setPosition(0.0f, 0.0f);
        }
        for (Array<UiLayer> array : this.layers) {
            array.sort(f2492b);
        }
        for (MainUiLayer mainUiLayer2 : MainUiLayer.values) {
            this.mainLayerGroups[mainUiLayer2.ordinal()].clearChildren(false);
            Array.ArrayIterator<UiLayer> it = this.layers[mainUiLayer2.ordinal()].iterator();
            while (it.hasNext()) {
                UiLayer next = it.next();
                float f2 = screenSafeMargin;
                float f3 = 0.0f;
                float f4 = f;
                float f5 = worldHeight;
                if (next.ignoreSafeMargin) {
                    f2 = 0.0f;
                    f3 = 0.0f;
                    f4 = worldWidth;
                    f5 = worldHeight;
                }
                if (next.followVisibleFrame) {
                    float f6 = worldWidth / screenWidth;
                    float f7 = worldHeight / screenHeight;
                    IntRectangle visibleDisplayFrame = Game.i.getVisibleDisplayFrame();
                    f2 = Math.max(f2, visibleDisplayFrame.minX * f6);
                    f3 = Math.max(0.0f, visibleDisplayFrame.minY * f7);
                    f4 = Math.min(f4, ((screenWidth - visibleDisplayFrame.maxX) * f6) - f2);
                    f5 = Math.min(f5, ((screenHeight - visibleDisplayFrame.maxY) * f7) - f3);
                }
                next.f2495a.setPosition(MathUtils.round(f2), MathUtils.round(f3));
                next.f2495a.setSize(MathUtils.round(f4), MathUtils.round(f5));
                next.f2495a.setOrigin(1);
                this.mainLayerGroups[mainUiLayer2.ordinal()].addActor(next.f2495a);
            }
        }
        updateLayersYAccordingToVisibleFrame();
    }

    @Null
    public final UiLayer getActorLayer(Actor actor) {
        Actor actor2 = actor;
        while (true) {
            Actor actor3 = actor2;
            if (actor3 != null) {
                if (actor3.getUserObject() instanceof UiLayer) {
                    return (UiLayer) actor3.getUserObject();
                }
                actor2 = actor3.getParent();
            } else {
                return null;
            }
        }
    }

    public final void updateLayersYAccordingToVisibleFrame() {
        UiLayer actorLayer;
        int i = 0;
        if (this.stage.getKeyboardFocus() != null && (actorLayer = getActorLayer(this.stage.getKeyboardFocus())) != null && !actorLayer.followVisibleFrame && !actorLayer.ignoreVisibleFrame) {
            int round = MathUtils.round(this.stage.screenToStageCoordinates(new Vector2(0.0f, getScreenHeight() - Game.i.getVisibleDisplayFrame().minY)).y);
            float f = this.stage.getKeyboardFocus().localToStageCoordinates(new Vector2()).y;
            int round2 = MathUtils.round((round + ((this.stage.getHeight() - round) / 2.0f)) - ((f + ((this.stage.getKeyboardFocus().localToStageCoordinates(new Vector2(0.0f, this.stage.getKeyboardFocus().getHeight())).y - f) / 2.0f)) - actorLayer.f2495a.getY()));
            i = round2;
            if (round2 < 0) {
                i = 0;
            }
            if (i > round) {
                i = round;
            }
        }
        for (MainUiLayer mainUiLayer : MainUiLayer.values) {
            Array.ArrayIterator<UiLayer> it = this.layers[mainUiLayer.ordinal()].iterator();
            while (it.hasNext()) {
                UiLayer next = it.next();
                if (!next.followVisibleFrame && !next.ignoreVisibleFrame) {
                    next.f2495a.setY(i);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void preRender(float f) {
        for (int i = 0; i < this.f.size; i++) {
            this.f.get(i).preRender(f);
        }
    }

    public final void render(float f) {
        for (int i = 0; i < this.f.size; i++) {
            this.f.get(i).postRender(f);
        }
        if (this.stage.getBatch().isDrawing()) {
            this.stage.getBatch().end();
        }
        if (Game.i.debugManager != null) {
            long realTickCount = Game.getRealTickCount();
            for (int i2 = 0; i2 < this.g.size; i2++) {
                try {
                    this.g.items[i2].run();
                } catch (Exception e) {
                    f2491a.e("failed to run stage.act runnable " + this.g.items[i2], e);
                }
            }
            this.g.clear();
            Game.i.debugManager.registerFrameJob("UI-runnables", Game.getRealTickCount() - realTickCount);
            long realTickCount2 = Game.getRealTickCount();
            this.stage.act(f);
            Game.i.debugManager.registerFrameJob("UI-act", Game.getRealTickCount() - realTickCount2);
            long realTickCount3 = Game.getRealTickCount();
            this.stage.draw();
            Game.i.debugManager.registerFrameJob("UI-draw", Game.getRealTickCount() - realTickCount3);
        } else {
            for (int i3 = 0; i3 < this.g.size; i3++) {
                try {
                    this.g.items[i3].run();
                } catch (Exception e2) {
                    f2491a.e("failed to run stage.act runnable " + this.g.items[i3], e2);
                }
            }
            this.g.clear();
            this.stage.act(f);
            this.stage.draw();
        }
        this.k += f;
        if (this.k > 1.0f) {
            this.k = 0.0f;
            int rotation = Gdx.input.getRotation();
            if (this.j != rotation) {
                this.j = rotation;
                rebuildLayers();
            }
        }
        updateLayersYAccordingToVisibleFrame();
    }

    public final HighlightActor addHighlight(Actor actor) {
        HighlightActor highlightActor = new HighlightActor(actor);
        this.i.addActor(highlightActor);
        highlightActor.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.alpha(1.0f, 0.3f)));
        return highlightActor;
    }

    public final void removeHighlight(HighlightActor highlightActor) {
        highlightActor.addAction(Actions.sequence(Actions.alpha(0.0f, 0.3f), Actions.removeActor()));
    }

    public final void removeAllHighlights() {
        SnapshotArray<Actor> children = this.i.getChildren();
        for (int i = 0; i < children.size; i++) {
            children.get(i).clearActions();
            children.get(i).addAction(Actions.sequence(Actions.alpha(0.0f, 0.3f), Actions.removeActor()));
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        for (int i = 0; i < this.f.size; i++) {
            this.f.get(i).dispose();
        }
        this.e.clear();
        this.f.clear();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UiManager$_ScreenManagerListener.class */
    private class _ScreenManagerListener implements ScreenManager.ScreenManagerListener {
        private _ScreenManagerListener() {
        }

        /* synthetic */ _ScreenManagerListener(UiManager uiManager, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ScreenManager.ScreenManagerListener
        public void screenChanged() {
            UiManager.this.removeAllHighlights();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UiManager$UiLayer.class */
    public static class UiLayer {

        /* renamed from: a, reason: collision with root package name */
        private Table f2495a;
        public final String name;
        public int zIndex;
        public final MainUiLayer mainUiLayer;
        public boolean ignoreSafeMargin;
        public boolean ignoreVisibleFrame;
        public boolean followVisibleFrame;
        public Object userdata;

        /* synthetic */ UiLayer(MainUiLayer mainUiLayer, Table table, int i, String str, byte b2) {
            this(mainUiLayer, table, i, str);
        }

        private UiLayer(MainUiLayer mainUiLayer, Table table, int i, String str) {
            this.ignoreSafeMargin = false;
            this.ignoreVisibleFrame = false;
            this.followVisibleFrame = false;
            table.setUserObject(this);
            this.mainUiLayer = mainUiLayer;
            this.f2495a = table;
            this.zIndex = i;
            this.name = str;
            table.setName(str);
        }

        public Table getTable() {
            return this.f2495a;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UiManager$UiComponent.class */
    public interface UiComponent extends Disposable {
        void hide();

        boolean isPersistent();

        void preRender(float f);

        void postRender(float f);

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UiManager$UiComponent$Adapter.class */
        public static abstract class Adapter implements UiComponent {
            @Override // com.prineside.tdi2.managers.UiManager.UiComponent
            public boolean isPersistent() {
                return false;
            }

            @Override // com.prineside.tdi2.managers.UiManager.UiComponent
            public void postRender(float f) {
            }

            @Override // com.prineside.tdi2.managers.UiManager.UiComponent
            public void preRender(float f) {
            }

            @Override // com.badlogic.gdx.utils.Disposable
            public void dispose() {
            }
        }
    }
}
