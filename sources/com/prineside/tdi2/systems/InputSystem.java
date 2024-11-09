package com.prineside.tdi2.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.GamePaused;
import com.prineside.tdi2.events.game.GameResumed;
import com.prineside.tdi2.events.game.InputMultiplexerConfigure;
import com.prineside.tdi2.events.game.MouseClick;
import com.prineside.tdi2.events.game.MouseMove;
import com.prineside.tdi2.events.global.Render;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.utils.InputMultiplexerExtended;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.logging.TLog;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/InputSystem.class */
public final class InputSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2963a = TLog.forClass(InputSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private InputMultiplexerExtended f2964b;
    public CameraController cameraController;
    private InputProcessor d;
    private final Vector2 c = new Vector2();
    private boolean e = false;
    private final Listener<ScreenResize> f = new Listener<ScreenResize>() { // from class: com.prineside.tdi2.systems.InputSystem.1
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(ScreenResize screenResize) {
            if (InputSystem.this.e) {
                return;
            }
            InputSystem.this.cameraController.setScreenSize(screenResize.getWidth(), screenResize.getHeight());
        }
    };
    private final Listener<Render> g = new Listener<Render>() { // from class: com.prineside.tdi2.systems.InputSystem.2
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(Render render) {
            InputSystem.this.cameraController.realUpdate(render.getDeltaTime());
        }
    };

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Input";
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        Game.EVENTS.getListeners(Render.class).add(this.g).setDescription("InputSystem - updates cameraController");
        this.S.events.getListeners(GamePaused.class).add(gamePaused -> {
            this.d = Gdx.input.getInputProcessor();
            enableOnlyStage();
        }).setDescription("InputSystem - saves last InputProcessor and replaces it with stage-only processor");
        this.S.events.getListeners(GameResumed.class).add(gameResumed -> {
            f2963a.i("game resumed, input processor: " + this.d, new Object[0]);
            Gdx.input.setInputProcessor(this.d);
        }).setDescription("InputSystem - restores stored InputProcessor from the GamePaused event");
        this.cameraController = new CameraController(this.S._render.getCamera(), this.S.map.getMap().getWidth() << 7, this.S.map.getMap().getHeight() << 7);
        this.cameraController.setScreenSize(Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight());
        this.cameraController.lookAt((this.S.map.getMap().getWidth() << 7) / 2.0f, (this.S.map.getMap().getHeight() << 7) / 2.0f);
        this.cameraController.fitMapToScreen(60.0f);
        Gdx.input.setCatchKey(4, true);
        this.f2964b = new InputMultiplexerExtended();
        disableInput();
        Game.EVENTS.getListeners(ScreenResize.class).add(this.f);
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.INPUT_DRAW, false, (batch, f, f2, f3) -> {
            draw(batch, f2, f);
        }).setName("Input-draw"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        enableAllInput();
        this.cameraController.setMap(this.S.map.getMap());
    }

    public final InputMultiplexerExtended getInputMultiplexer() {
        return this.f2964b;
    }

    public final void disableInput() {
        Gdx.input.setInputProcessor(null);
    }

    public final void enableOnlyStage() {
        Game.i.uiManager.setAsInputHandler();
    }

    public final void enableAllInput() {
        setupInputMultiplexer(true, true, true);
        Game.i.uiManager.stage.setScrollFocus(null);
    }

    public final InputMultiplexerExtended setupInputMultiplexer(boolean z, boolean z2, boolean z3) {
        this.f2964b.clear();
        if (z) {
            this.f2964b.addProcessor(Game.i.uiManager.stage);
        }
        if (z2) {
            this.f2964b.addProcessor(this.cameraController.getInputProcessor());
        }
        if (z3) {
            this.f2964b.addProcessor(new MouseEventsInputProcessor());
        }
        this.S.events.trigger(new InputMultiplexerConfigure(this.f2964b));
        Gdx.input.setInputProcessor(this.f2964b);
        return this.f2964b;
    }

    public final InputProcessor getInputProcessor() {
        return Gdx.input.getInputProcessor();
    }

    public final CameraController getCameraController() {
        return this.cameraController;
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        Game.EVENTS.getListeners(Render.class).remove(this.g);
        Game.EVENTS.getListeners(ScreenResize.class).remove(this.f);
        this.f2964b = null;
        this.cameraController = null;
        this.d = null;
        this.e = true;
        super.dispose();
    }

    public final void draw(Batch batch, float f, float f2) {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_CURSOR_POS) != 0.0d) {
            Game.i.assetManager.getDebugFont(false).draw(batch, ((int) this.c.x) + ":" + ((int) this.c.y), this.c.x + 10.0f, this.c.y - 10.0f);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/InputSystem$MouseEventsInputProcessor.class */
    public class MouseEventsInputProcessor extends InputAdapter {

        /* renamed from: a, reason: collision with root package name */
        private final Vector2 f2967a = new Vector2();

        public MouseEventsInputProcessor() {
        }

        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean keyDown(int i) {
            return false;
        }

        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchDragged(int i, int i2, int i3) {
            a(i, i2, i3);
            return false;
        }

        private void a(int i, int i2, int i3) {
            if (InputSystem.this.e || InputSystem.this.cameraController == null) {
                return;
            }
            this.f2967a.set(i, i2);
            InputSystem.this.cameraController.screenToMap(this.f2967a);
            InputSystem.this.S.events.trigger(new MouseMove(this.f2967a.x, this.f2967a.y, i3));
            InputSystem.this.c.set(this.f2967a.x, this.f2967a.y);
        }

        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean mouseMoved(int i, int i2) {
            a(i, i2, -1);
            return false;
        }

        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchUp(int i, int i2, int i3, int i4) {
            if (InputSystem.this.e || InputSystem.this.cameraController == null) {
                return false;
            }
            this.f2967a.set(i, i2);
            InputSystem.this.cameraController.screenToMap(this.f2967a);
            InputSystem.this.S.events.trigger(new MouseClick(this.f2967a.x, this.f2967a.y, i3, i4));
            return false;
        }
    }
}
