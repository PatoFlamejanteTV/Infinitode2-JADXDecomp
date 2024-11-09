package com.prineside.tdi2.utils.mapeditor.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.ui.components.MapEditorUi;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.mapeditor.Selection;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/SelectRectangle.class */
public class SelectRectangle implements MapEditorSystem.Tool {

    /* renamed from: a, reason: collision with root package name */
    private GameSystemProvider f3952a;

    /* renamed from: b, reason: collision with root package name */
    private MapEditorUi.ToolButton f3953b;
    private boolean d;
    private Selection g;
    private Selection h;
    private int c = -1;
    private final Vector2 e = new Vector2();
    private final Vector2 f = new Vector2();

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void setup(GameSystemProvider gameSystemProvider) {
        this.f3952a = gameSystemProvider;
        this.f3953b = new MapEditorUi.ToolButton(Game.i.assetManager.getQuad("mapeditor.tools.select-rectangle"), () -> {
            gameSystemProvider._mapEditor.selectTool(this);
        }, null);
        gameSystemProvider._mapEditorUi.mainUi.addToolButton(this.f3953b);
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public MapEditorUi.PreparedTooltip getTooltip() {
        return new MapEditorUi.PreparedTooltip("map_editor_tool_select", this.f3953b, Game.i.localeManager.i18n.get("map_editor_tooltip_select_rect_tool"));
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void enabled(MapEditorSystem.Tool tool) {
        this.f3953b.setActive(true);
        this.f3952a._input.getCameraController().dragButtonIndices.clear();
        this.f3952a._input.getCameraController().dragButtonIndices.add(1);
        this.f3952a._input.setupInputMultiplexer(true, true, true).addProcessor(new InputAdapter() { // from class: com.prineside.tdi2.utils.mapeditor.tools.SelectRectangle.1
            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchDown(int i, int i2, int i3, int i4) {
                if (i4 == 0) {
                    SelectRectangle.this.e.set(i, i2);
                    SelectRectangle.this.f3952a._input.getCameraController().screenToMap(SelectRectangle.this.e);
                    SelectRectangle.this.f.set(i, i2);
                    SelectRectangle.this.f3952a._input.getCameraController().screenToMap(SelectRectangle.this.f);
                    SelectRectangle.this.c = i3;
                    SelectRectangle.this.d = Gdx.input.isKeyPressed(129) || SelectRectangle.this.f3952a._mapEditorUi.mainUi.isCtrlButtonEnabled();
                    if (!SelectRectangle.this.d) {
                        SelectRectangle.this.g = null;
                        SelectRectangle.this.a();
                        return false;
                    }
                    SelectRectangle.this.g = SelectRectangle.this.f3952a._mapEditor.selection.cpy();
                    return false;
                }
                return false;
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchUp(int i, int i2, int i3, int i4) {
                if (SelectRectangle.this.c == i3) {
                    SelectRectangle.this.c = -1;
                    return false;
                }
                return false;
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchDragged(int i, int i2, int i3) {
                if (i3 == SelectRectangle.this.c) {
                    SelectRectangle.this.f.set(i, i2);
                    SelectRectangle.this.f3952a._input.getCameraController().screenToMap(SelectRectangle.this.f);
                    SelectRectangle.this.a();
                    return false;
                }
                return false;
            }
        });
        this.f3952a._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_RENDERING_MAP_EDITOR_SELECTION + 50, false, (batch, f, f2, f3) -> {
            if (this.c != -1) {
                float min = Math.min(this.e.x, this.f.x);
                float max = Math.max(this.e.x, this.f.x);
                float min2 = Math.min(this.e.y, this.f.y);
                float max2 = Math.max(this.e.y, this.f.y);
                ResourcePack.AtlasTextureRegion atlasTextureRegion = AssetManager.TextureRegions.i().blank;
                float f = (float) (2.0d * this.f3952a._input.getCameraController().zoom);
                DrawUtils.texturedLineB(batch, atlasTextureRegion, min, this.e.y, min, this.f.y, f);
                DrawUtils.texturedLineB(batch, atlasTextureRegion, max, this.e.y, max, this.f.y, f);
                DrawUtils.texturedLineB(batch, atlasTextureRegion, this.e.x, min2, this.f.x, min2, f);
                DrawUtils.texturedLineB(batch, atlasTextureRegion, this.e.x, max2, this.f.x, max2, f);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.h == null) {
            this.h = this.f3952a._mapEditor.selection.cpy();
            this.h.setFromInventory(false);
        }
        this.h.clear();
        float min = Math.min(this.e.x, this.f.x);
        float max = Math.max(this.e.x, this.f.x);
        float min2 = Math.min(this.e.y, this.f.y);
        float max2 = Math.max(this.e.y, this.f.y);
        Rectangle rectangle = new Rectangle(min, min2, max - min, max2 - min2);
        int floor = MathUtils.floor(min * 0.0078125f) - 1;
        int floor2 = MathUtils.floor(min2 * 0.0078125f) - 1;
        int ceil = MathUtils.ceil(max * 0.0078125f) + 1;
        int ceil2 = MathUtils.ceil(max2 * 0.0078125f) + 1;
        Map map = this.f3952a.map.getMap();
        for (int i = floor; i <= ceil; i++) {
            for (int i2 = floor2; i2 <= ceil2; i2++) {
                Tile tile = map.getTile(i, i2);
                if (tile != null && tile.getBoundingBox().overlaps(rectangle)) {
                    this.h.addTile(tile);
                }
                boolean[] zArr = {false, true};
                for (int i3 = 0; i3 < 2; i3++) {
                    Gate gate = map.getGate(i, i2, zArr[i3]);
                    if (gate != null && gate.getBoundingBox().overlaps(rectangle)) {
                        this.h.addGate(gate);
                    }
                }
            }
        }
        if (this.d) {
            this.h.addAll(this.g);
        }
        if (this.f3952a._mapEditor.selection.hash() != this.h.hash()) {
            this.f3952a._mapEditor.selection.clear();
            this.f3952a._mapEditor.selection.setFromInventory(false);
            this.f3952a._mapEditor.selection.addAll(this.h);
            this.f3952a._mapEditor.notifySelectionChanged();
        }
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public void disabled() {
        this.c = -1;
        this.f3953b.setActive(false);
    }
}
