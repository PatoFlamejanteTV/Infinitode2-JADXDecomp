package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.events.game.MapSizeChange;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.WidgetGroup;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapShiftButtons.class */
public class MapShiftButtons implements Disposable {
    private WidgetGroup c;
    private boolean h;
    private CameraController i;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3349a = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 95, "MapShiftButtons", true);

    /* renamed from: b, reason: collision with root package name */
    private final DelayedRemovalArray<MapShiftButtonsListener> f3350b = new DelayedRemovalArray<>();
    private ObjectMap<Direction, Group> d = new ObjectMap<>();
    private ObjectMap<Direction, PaddedImageButton> e = new ObjectMap<>();
    private ObjectMap<Direction, PaddedImageButton> f = new ObjectMap<>();
    private ObjectMap<Direction, PaddedImageButton> g = new ObjectMap<>();
    private final _CameraControllerListener j = new _CameraControllerListener(this, 0);
    private final Runnable k = this::update;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapShiftButtons$Direction.class */
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        public static final Direction[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapShiftButtons$MapShiftButtonsListener.class */
    public interface MapShiftButtonsListener {
        void shifted(Direction direction);

        void expanded(Direction direction);

        void reduced(Direction direction);
    }

    public MapShiftButtons(GameSystemProvider gameSystemProvider) {
        this.i = gameSystemProvider._input.getCameraController();
        this.i.addListener(this.j);
        this.c = new WidgetGroup();
        this.c.setTransform(false);
        this.f3349a.getTable().add((Table) this.c).expand().fill();
        for (Direction direction : Direction.values) {
            Group group = new Group();
            group.setTransform(false);
            this.d.put(direction, group);
            group.setSize(1.0f, 1.0f);
            this.c.addActor(group);
        }
        ObjectMap objectMap = new ObjectMap();
        objectMap.put(Direction.LEFT, "icon-triangle-left-hollow");
        objectMap.put(Direction.RIGHT, "icon-triangle-right-hollow");
        objectMap.put(Direction.DOWN, "icon-triangle-bottom-hollow");
        objectMap.put(Direction.UP, "icon-triangle-top-hollow");
        for (Direction direction2 : Direction.values) {
            PaddedImageButton iconSize = new PaddedImageButton(Game.i.assetManager.getDrawable((String) objectMap.get(direction2)), () -> {
                this.f3350b.begin();
                for (int i = 0; i < this.f3350b.size; i++) {
                    this.f3350b.get(i).shifted(direction2);
                }
                this.f3350b.end();
            }, MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P900).setIconPosition(16.0f, 16.0f).setIconSize(64.0f, 64.0f);
            iconSize.setSize(96.0f, 96.0f);
            switch (direction2) {
                case LEFT:
                    iconSize.setPosition(-96.0f, -48.0f);
                    break;
                case RIGHT:
                    iconSize.setPosition(0.0f, -48.0f);
                    break;
                case UP:
                    iconSize.setPosition(-48.0f, 0.0f);
                    break;
                case DOWN:
                    iconSize.setPosition(-48.0f, -96.0f);
                    break;
            }
            this.d.get(direction2).addActor(iconSize);
            this.e.put(direction2, iconSize);
        }
        objectMap.clear();
        objectMap.put(Direction.LEFT, "icon-triangle-left");
        objectMap.put(Direction.RIGHT, "icon-triangle-right");
        objectMap.put(Direction.DOWN, "icon-triangle-bottom");
        objectMap.put(Direction.UP, "icon-triangle-top");
        for (Direction direction3 : Direction.values) {
            PaddedImageButton iconSize2 = new PaddedImageButton(Game.i.assetManager.getDrawable((String) objectMap.get(direction3)), () -> {
                this.f3350b.begin();
                for (int i = 0; i < this.f3350b.size; i++) {
                    this.f3350b.get(i).expanded(direction3);
                }
                this.f3350b.end();
            }, MaterialColor.GREEN.P800, MaterialColor.GREEN.P700, MaterialColor.GREEN.P900).setIconPosition(16.0f, 16.0f).setIconSize(64.0f, 64.0f);
            iconSize2.setSize(96.0f, 96.0f);
            switch (direction3) {
                case LEFT:
                    iconSize2.setPosition(-96.0f, 48.0f);
                    break;
                case RIGHT:
                    iconSize2.setPosition(0.0f, 48.0f);
                    break;
                case UP:
                    iconSize2.setPosition(48.0f, 0.0f);
                    break;
                case DOWN:
                    iconSize2.setPosition(48.0f, -96.0f);
                    break;
            }
            this.d.get(direction3).addActor(iconSize2);
            this.f.put(direction3, iconSize2);
        }
        objectMap.clear();
        objectMap.put(Direction.LEFT, "icon-triangle-right");
        objectMap.put(Direction.RIGHT, "icon-triangle-left");
        objectMap.put(Direction.DOWN, "icon-triangle-top");
        objectMap.put(Direction.UP, "icon-triangle-bottom");
        for (Direction direction4 : Direction.values) {
            PaddedImageButton iconSize3 = new PaddedImageButton(Game.i.assetManager.getDrawable((String) objectMap.get(direction4)), () -> {
                this.f3350b.begin();
                for (int i = 0; i < this.f3350b.size; i++) {
                    this.f3350b.get(i).reduced(direction4);
                }
                this.f3350b.end();
            }, MaterialColor.RED.P800, MaterialColor.RED.P700, MaterialColor.RED.P900).setIconPosition(16.0f, 16.0f).setIconSize(64.0f, 64.0f);
            iconSize3.setSize(96.0f, 96.0f);
            switch (direction4) {
                case LEFT:
                    iconSize3.setPosition(-96.0f, -144.0f);
                    break;
                case RIGHT:
                    iconSize3.setPosition(0.0f, -144.0f);
                    break;
                case UP:
                    iconSize3.setPosition(-144.0f, 0.0f);
                    break;
                case DOWN:
                    iconSize3.setPosition(-144.0f, -96.0f);
                    break;
            }
            this.d.get(direction4).addActor(iconSize3);
            this.g.put(direction4, iconSize3);
        }
        update();
        gameSystemProvider.events.getListeners(MapSizeChange.class).add(mapSizeChange -> {
            Game.i.uiManager.runOnStageActOnce(this.k);
        });
    }

    public void setMapSizeChangesAllowed(boolean z) {
        this.h = z;
        update();
    }

    public void addListener(MapShiftButtonsListener mapShiftButtonsListener) {
        if (mapShiftButtonsListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.f3350b.contains(mapShiftButtonsListener, true)) {
            this.f3350b.add(mapShiftButtonsListener);
        }
    }

    public void removeListener(MapShiftButtonsListener mapShiftButtonsListener) {
        if (mapShiftButtonsListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.f3350b.removeValue(mapShiftButtonsListener, true);
    }

    public void update() {
        if (this.h) {
            ObjectMap.Values<PaddedImageButton> it = this.f.values().iterator();
            while (it.hasNext()) {
                it.next().setVisible(true);
            }
            ObjectMap.Values<PaddedImageButton> it2 = this.g.values().iterator();
            while (it2.hasNext()) {
                it2.next().setVisible(true);
            }
        } else {
            ObjectMap.Values<PaddedImageButton> it3 = this.f.values().iterator();
            while (it3.hasNext()) {
                it3.next().setVisible(false);
            }
            ObjectMap.Values<PaddedImageButton> it4 = this.g.values().iterator();
            while (it4.hasNext()) {
                it4.next().setVisible(false);
            }
        }
        Vector2 vector2 = new Vector2();
        vector2.set(-16.0f, this.i.getMapHeight() * 0.5f);
        this.i.mapToStage(vector2);
        this.d.get(Direction.LEFT).setPosition(vector2.x, vector2.y);
        vector2.set(this.i.getMapWidth() + 16.0f, this.i.getMapHeight() * 0.5f);
        this.i.mapToStage(vector2);
        this.d.get(Direction.RIGHT).setPosition(vector2.x, vector2.y);
        vector2.set(this.i.getMapWidth() * 0.5f, -16.0f);
        this.i.mapToStage(vector2);
        this.d.get(Direction.DOWN).setPosition(vector2.x, vector2.y);
        vector2.set(this.i.getMapWidth() * 0.5f, this.i.getMapHeight() + 16.0f);
        this.i.mapToStage(vector2);
        this.d.get(Direction.UP).setPosition(vector2.x, vector2.y);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.i.removeListener(this.j);
        Game.i.uiManager.removeLayer(this.f3349a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapShiftButtons$_CameraControllerListener.class */
    public class _CameraControllerListener implements CameraController.CameraControllerListener {
        private _CameraControllerListener() {
        }

        /* synthetic */ _CameraControllerListener(MapShiftButtons mapShiftButtons, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerListener
        public void onViewportUpdated(CameraController cameraController) {
            MapShiftButtons.this.update();
        }
    }
}
