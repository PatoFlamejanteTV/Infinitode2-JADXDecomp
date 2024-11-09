package com.prineside.tdi2.utils.mapeditor.tools;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.items.GateItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.systems.GameMapSelectionSystem;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.components.MapEditorInventoryMenu;
import com.prineside.tdi2.ui.components.MapEditorUi;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.mapeditor.Selection;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/Move.class */
public final class Move implements MapEditorSystem.Tool {

    /* renamed from: a, reason: collision with root package name */
    private GameSystemProvider f3941a;

    /* renamed from: b, reason: collision with root package name */
    private MapEditorUi.ToolButton f3942b;
    private MapEditorSystem.HistoryImprint d;
    private Selection e;
    private Image g;
    private Selection i;
    private static final Vector2 l = new Vector2();
    private final InventoryListener c = new InventoryListener(this, 0);
    private final Vector2 f = new Vector2(-2.1474836E9f, -2.1474836E9f);
    private final Vector2 h = new Vector2();
    private final RenderSystem.Layer j = new RenderSystem.Layer(GameRenderingOrder.MAP_RENDERING_MAP_EDITOR_SELECTION + 50, false, new HintLayerRenderer(this, 0));
    private final Gate k = Game.i.gateManager.F.BARRIER_TYPE.create();

    static /* synthetic */ boolean c(Move move) {
        return false;
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public final void setup(GameSystemProvider gameSystemProvider) {
        this.f3941a = gameSystemProvider;
        this.f3942b = new MapEditorUi.ToolButton(Game.i.assetManager.getQuad("mapeditor.tools.move"), () -> {
            gameSystemProvider._mapEditor.selectTool(this);
        }, null);
        gameSystemProvider._mapEditorUi.mainUi.addToolButton(this.f3942b);
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public final MapEditorUi.PreparedTooltip getTooltip() {
        return new MapEditorUi.PreparedTooltip("map_editor_tool_move", this.f3942b, Game.i.localeManager.i18n.get("map_editor_tooltip_move_tool"));
    }

    private static float b() {
        if (c()) {
            return 0.0f;
        }
        return 150.0f;
    }

    private static boolean c() {
        return Gdx.app.getType() == Application.ApplicationType.Desktop;
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public final void enabled(MapEditorSystem.Tool tool) {
        this.f3942b.setActive(true);
        this.f3941a._mapEditorUi.inventoryMenu.setItemDraggingMode(true);
        this.f3941a._mapEditorUi.inventoryMenu.addListener(this.c);
        this.f3941a._input.getCameraController().dragButtonIndices.clear();
        this.f3941a._input.getCameraController().dragButtonIndices.add(1);
        this.f3941a._input.setupInputMultiplexer(true, true, true).addProcessor(new MapInputHandler(this, (byte) 0));
        this.f3941a._render.addLayer(this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f, float f2, float f3, float f4) {
        this.h.set(f3, f4);
        float b2 = f2 + b();
        if (this.e.count() == 1) {
            this.f3941a._mapEditorUi.draggingItemHelper.setIconShiftInstantly(f3, f4);
        } else {
            this.f3941a._mapEditorUi.draggingItemHelper.setIconShiftInstantly(0.0f, 0.0f);
        }
        if (this.f3941a._mapEditorUi.inventoryMenu.isStagePointOnInventory(f, b2)) {
            this.f3941a._mapEditorUi.draggingItemHelper.setScaleInstantly(1.0f);
        } else if (this.e.count() == 1) {
            this.f3941a._mapEditorUi.draggingItemHelper.setScaleInstantly((float) (1.0d / this.f3941a._input.getCameraController().zoom));
        } else {
            this.f3941a._mapEditorUi.draggingItemHelper.setScaleInstantly(0.0f);
        }
        Group show = this.f3941a._mapEditorUi.draggingItemHelper.show();
        this.g = new Image(Game.i.assetManager.getQuad("screen.mapEditor.draggingItems.arrowPointer"));
        this.g.setSize(64.0f, 64.0f);
        this.g.setPosition(-32.0f, 128.0f);
        this.g.setVisible(false);
        show.addActor(this.g);
        if (this.e.count() == 1) {
            Actor generateIcon = this.e.getCurrentItem().generateIcon(128.0f, true);
            generateIcon.setPosition(-64.0f, -64.0f);
            show.addActor(generateIcon);
        } else {
            Image image = new Image(Game.i.assetManager.getQuad("mapeditor.tools.select-rectangle"));
            image.setSize(128.0f, 128.0f);
            image.setPosition(-58.0f, -70.0f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            show.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getQuad("mapeditor.tools.select-rectangle"));
            image2.setSize(128.0f, 128.0f);
            image2.setPosition(-64.0f, -64.0f);
            show.addActor(image2);
            Label label = new Label(new StringBuilder().append(this.e.count()).toString(), Game.i.assetManager.getLabelStyle(60));
            label.setSize(128.0f, 128.0f);
            label.setAlignment(1);
            label.setPosition(-58.0f, -70.0f);
            label.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            show.addActor(label);
            Label label2 = new Label(new StringBuilder().append(this.e.count()).toString(), Game.i.assetManager.getLabelStyle(60));
            label2.setSize(128.0f, 128.0f);
            label2.setAlignment(1);
            label2.setPosition(-64.0f, -64.0f);
            show.addActor(label2);
        }
        a(f, b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f, float f2) {
        float b2 = f2 + b();
        this.f3941a._mapEditorUi.draggingItemHelper.setPosition(f, b2);
        if (this.g != null) {
            this.g.setVisible(false);
        }
        if (this.f3941a._mapEditorUi.inventoryMenu.isStagePointOnInventory(f, b2)) {
            this.f3941a._mapEditorUi.draggingItemHelper.setScale(1.0f);
            this.f3941a._mapEditorUi.draggingItemHelper.setIconShift(0.0f, 0.0f);
            this.f.set(-2.1474836E9f, -2.1474836E9f);
            return;
        }
        if (c()) {
            this.f3941a._mapEditorUi.draggingItemHelper.setScale(0.33f);
            this.f3941a._mapEditorUi.draggingItemHelper.setIconShift(32.0f, -42.0f);
        } else {
            this.f3941a._mapEditorUi.draggingItemHelper.setScale(0.5f);
            this.f3941a._mapEditorUi.draggingItemHelper.setIconShift(0.0f, -96.0f);
            if (this.g != null) {
                this.g.setVisible(true);
            }
        }
        this.f.set(f, b2);
        this.i = this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!this.e.isFromInventory()) {
            this.f3941a._mapEditor.selection.clear();
        }
        if (this.f.x == -2.1474836E9f || this.f.y == -2.1474836E9f) {
            Array.ArrayIterator<Tile> it = this.e.tiles.iterator();
            while (it.hasNext()) {
                this.f3941a._inventory.addTile(it.next(), 1);
            }
            Array.ArrayIterator<Gate> it2 = this.e.gates.iterator();
            while (it2.hasNext()) {
                this.f3941a._inventory.addGate(it2.next(), 1);
            }
        } else {
            this.f3941a._mapEditor.selection.setFromInventory(false);
            l.set(this.f);
            this.f3941a._input.getCameraController().stageToMap(l);
            Map map = this.f3941a.map.getMap();
            if (this.e.count() == 1 && this.e.gates.size == 1) {
                Gate first = this.e.gates.first();
                if (this.f3941a.map.getMap().fitGateToMapPos(l.x, l.y, this.k)) {
                    Gate gate = map.getGate(this.k.getX(), this.k.getY(), this.k.isLeftSide());
                    if (gate != null) {
                        if (this.e.isFromInventory() || this.e.count() != 1) {
                            this.f3941a._inventory.addGate(gate, 1);
                        } else {
                            this.f3941a.map.setGate(first.getX(), first.getY(), first.isLeftSide(), gate.cloneGate());
                        }
                    }
                    Gate cloneGate = first.cloneGate();
                    this.f3941a.map.setGate(this.k.getX(), this.k.getY(), this.k.isLeftSide(), cloneGate);
                    this.f3941a._mapEditor.selection.addGate(cloneGate);
                } else {
                    this.f3941a._inventory.addGate(first, 1);
                }
            } else if (this.e.count() == 1 && this.e.tiles.size == 1) {
                Tile first2 = this.e.tiles.first();
                int posToCell = Map.posToCell(l.x);
                int posToCell2 = Map.posToCell(l.y);
                if (posToCell >= 0 && posToCell < map.getWidth() && posToCell2 >= 0 && posToCell2 < map.getHeight()) {
                    Tile tile = map.getTile(posToCell, posToCell2);
                    if (tile != null) {
                        if (this.e.isFromInventory() || this.e.count() != 1) {
                            this.f3941a._inventory.addTile(tile, 1);
                        } else {
                            Tile first3 = this.e.tiles.first();
                            this.f3941a.map.setTile(first3.getX(), first3.getY(), tile.cloneTile());
                        }
                    }
                    Tile cloneTile = first2.cloneTile();
                    this.f3941a.map.setTile(posToCell, posToCell2, cloneTile);
                    this.f3941a._mapEditor.selection.addTile(cloneTile);
                } else {
                    this.f3941a._inventory.addTile(first2, 1);
                }
            } else {
                l.add(-this.h.x, -this.h.y);
                float f = l.x;
                float f2 = l.y;
                int floor = MathUtils.floor(f * 0.0078125f);
                int floor2 = MathUtils.floor(f2 * 0.0078125f);
                IntRectangle dimensions = this.e.dimensions();
                Array.ArrayIterator<Tile> it3 = this.e.tiles.iterator();
                while (it3.hasNext()) {
                    Tile next = it3.next();
                    int x = (next.getX() - dimensions.minX) + floor;
                    int y = (next.getY() - dimensions.minY) + floor2;
                    if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
                        this.f3941a._inventory.addTile(next, 1);
                    } else {
                        Tile tile2 = map.getTile(x, y);
                        if (tile2 != null) {
                            this.f3941a._inventory.addTile(tile2, 1);
                        }
                        Tile cloneTile2 = next.cloneTile();
                        this.f3941a.map.setTile(x, y, cloneTile2);
                        this.f3941a._mapEditor.selection.addTile(cloneTile2);
                    }
                }
                Array.ArrayIterator<Gate> it4 = this.e.gates.iterator();
                while (it4.hasNext()) {
                    Gate next2 = it4.next();
                    int x2 = (next2.getX() - dimensions.minX) + floor;
                    int y2 = (next2.getY() - dimensions.minY) + floor2;
                    if (x2 < 0 || x2 > map.getWidth() || y2 < 0 || y2 > map.getHeight() || ((y2 == map.getHeight() && next2.isLeftSide()) || (x2 == map.getWidth() && !next2.isLeftSide()))) {
                        this.f3941a._inventory.addGate(next2, 1);
                    } else {
                        Gate gate2 = map.getGate(x2, y2, next2.isLeftSide());
                        if (gate2 != null) {
                            this.f3941a._inventory.addGate(gate2, 1);
                        }
                        Gate cloneGate2 = next2.cloneGate();
                        this.f3941a.map.setGate(x2, y2, next2.isLeftSide(), cloneGate2);
                        this.f3941a._mapEditor.selection.addGate(cloneGate2);
                    }
                }
            }
        }
        this.f3941a._mapEditor.notifySelectionChanged();
        this.f3941a._mapEditorUi.draggingItemHelper.hide();
        this.f.set(-2.1474836E9f, -2.1474836E9f);
        if (this.d != null) {
            this.f3941a._mapEditor.finishActionRecord(this.d);
        }
    }

    @Override // com.prineside.tdi2.systems.MapEditorSystem.Tool
    public final void disabled() {
        this.f3941a._render.removeLayer(this.j);
        this.f3942b.setActive(false);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/Move$InventoryListener.class */
    private class InventoryListener extends MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter {

        /* renamed from: a, reason: collision with root package name */
        private final Vector2 f3944a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3945b;

        private InventoryListener() {
            this.f3944a = new Vector2();
        }

        /* synthetic */ InventoryListener(Move move, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter, com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
        public void itemSlotDragStart(MapEditorInventoryMenu.ItemSlot itemSlot, Vector2 vector2) {
            this.f3944a.set(vector2);
            Move.this.f3941a._input.getCameraController().screenToStage(this.f3944a);
            Item item = itemSlot.getItemStack().getItem();
            if (Move.this.f3941a._inventory.contains(item, 1)) {
                Move.this.d = Move.this.f3941a._mapEditor.startActionRecord();
                if (Move.this.f3941a._inventory.remove(item)) {
                    Move.this.f3941a._mapEditor.selection.clear();
                    Move.this.f3941a._mapEditor.selection.setFromInventory(true);
                    if (item instanceof TileItem) {
                        Move.this.f3941a._mapEditor.selection.addTile(((TileItem) item).tile);
                    } else if (item instanceof GateItem) {
                        Move.this.f3941a._mapEditor.selection.addGate(((GateItem) item).gate);
                    }
                    Move.this.e = Move.this.f3941a._mapEditor.selection.cpy();
                    Move.this.a(this.f3944a.x, this.f3944a.y, 0.0f, 0.0f);
                    this.f3945b = true;
                }
            }
        }

        @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter, com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
        public void itemSlotDrag(MapEditorInventoryMenu.ItemSlot itemSlot, Vector2 vector2) {
            if (this.f3945b) {
                this.f3944a.set(vector2);
                Move.this.f3941a._input.getCameraController().screenToStage(this.f3944a);
                Move.this.a(this.f3944a.x, this.f3944a.y);
            }
        }

        @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener.Adapter, com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
        public void itemSlotDragEnd(MapEditorInventoryMenu.ItemSlot itemSlot, Vector2 vector2) {
            if (this.f3945b) {
                this.f3945b = false;
                this.f3944a.set(vector2);
                Move.this.f3941a._input.getCameraController().screenToStage(this.f3944a);
                Move move = Move.this;
                float f = this.f3944a.x;
                float f2 = this.f3944a.y;
                move.d();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/Move$MapInputHandler.class */
    private class MapInputHandler extends InputAdapter {

        /* renamed from: a, reason: collision with root package name */
        private int f3946a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3947b;
        private final Vector2 c;
        private final Vector2 d;

        private MapInputHandler() {
            this.f3946a = -1;
            this.c = new Vector2();
            this.d = new Vector2();
        }

        /* synthetic */ MapInputHandler(Move move, byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchDown(int i, int i2, int i3, int i4) {
            if (i4 == 0) {
                Vector2 vector2 = new Vector2(i, i2);
                Move.this.f3941a._input.getCameraController().screenToMap(vector2);
                Move.this.f3941a._mapEditor.selection.setFromInventory(false);
                Gate gateByMapPos = Move.this.f3941a.map.getMap().getGateByMapPos(vector2.x, vector2.y);
                if (gateByMapPos == null) {
                    Tile tileByMapPos = Move.this.f3941a.map.getMap().getTileByMapPos(vector2.x, vector2.y);
                    if (tileByMapPos == null) {
                        Move.this.f3941a._mapEditor.selection.clear();
                    } else if (!Move.this.f3941a._mapEditor.selection.containsTile(tileByMapPos)) {
                        Move.this.f3941a._mapEditor.selection.clear();
                        Move.this.f3941a._mapEditor.selection.addTile(tileByMapPos);
                    }
                } else if (!Move.this.f3941a._mapEditor.selection.containsGate(gateByMapPos)) {
                    Move.this.f3941a._mapEditor.selection.clear();
                    Move.this.f3941a._mapEditor.selection.gates.add(gateByMapPos);
                }
                Move.this.f3941a._mapEditor.notifySelectionChanged();
                if (Move.this.f3941a._mapEditor.selection.count() > 0) {
                    Move.this.e = Move.this.f3941a._mapEditor.selection.cpy();
                    this.f3946a = i3;
                    this.f3947b = false;
                    this.c.set(vector2);
                    Move.this.f3941a._input.getCameraController().mapToStage(this.c);
                    this.d.setZero();
                    if (Move.this.e.count() > 1) {
                        IntRectangle dimensions = Move.this.e.dimensions();
                        this.d.x = (vector2.x - (dimensions.minX << 7)) - 64.0f;
                        this.d.y = (vector2.y - (dimensions.minY << 7)) - 64.0f;
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchDragged(int i, int i2, int i3) {
            if (i3 == this.f3946a) {
                Vector2 vector2 = new Vector2(i, i2);
                Move.this.f3941a._input.getCameraController().screenToStage(vector2);
                if (!this.f3947b && vector2.dst(this.c) > 5.0f) {
                    if (!Move.c(Move.this)) {
                        Move.this.d = Move.this.f3941a._mapEditor.startActionRecord();
                        Array.ArrayIterator<Tile> it = Move.this.e.tiles.iterator();
                        while (it.hasNext()) {
                            Tile next = it.next();
                            Move.this.f3941a.map.setTile(next.getX(), next.getY(), null);
                        }
                        Array.ArrayIterator<Gate> it2 = Move.this.e.gates.iterator();
                        while (it2.hasNext()) {
                            Gate next2 = it2.next();
                            Move.this.f3941a.map.setGate(next2.getX(), next2.getY(), next2.isLeftSide(), null);
                        }
                    }
                    this.f3947b = true;
                    Move.this.a(vector2.x, vector2.y, this.d.x, this.d.y);
                }
                if (this.f3947b) {
                    Move.this.a(vector2.x, vector2.y);
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
        public boolean touchUp(int i, int i2, int i3, int i4) {
            if (this.f3946a == i3) {
                this.f3946a = -1;
                if (this.f3947b) {
                    Vector2 vector2 = new Vector2(i, i2);
                    Move.this.f3941a._input.getCameraController().screenToStage(vector2);
                    Move move = Move.this;
                    float f = vector2.x;
                    float f2 = vector2.y;
                    move.d();
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/tools/Move$HintLayerRenderer.class */
    private final class HintLayerRenderer implements RenderSystem.LayerRenderer {
        private HintLayerRenderer() {
        }

        /* synthetic */ HintLayerRenderer(Move move, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.systems.RenderSystem.LayerRenderer
        public final void render(Batch batch, float f, float f2, float f3) {
            if (Move.this.f.x != -2.1474836E9f && Move.this.f.y != -2.1474836E9f) {
                batch.setColor(Color.WHITE);
                Move.l.set(Move.this.f);
                Move.this.f3941a._input.getCameraController().stageToMap(Move.l);
                if (Move.this.e.count() != 1 || Move.this.e.gates.size != 1) {
                    if (Move.this.e.count() > 1) {
                        Move.l.add(-Move.this.h.x, -Move.this.h.y);
                    }
                    float f4 = Move.l.x;
                    float f5 = Move.l.y;
                    int floor = MathUtils.floor(f4 * 0.0078125f);
                    int floor2 = MathUtils.floor(f5 * 0.0078125f);
                    Move.this.i.draw(batch, floor << 7, floor2 << 7, 1.0f, 1.0f, Move.this.f3941a.map.getMap());
                    Move.this.i.drawOutline(batch, floor << 7, floor2 << 7, 1.0f, 1.0f, MaterialColor.ORANGE.P500, null);
                } else {
                    Gate first = Move.this.e.gates.first();
                    if (Move.this.f3941a.map.getMap().fitGateToMapPos(Move.l.x, Move.l.y, Move.this.k)) {
                        boolean isLeftSide = first.isLeftSide();
                        first.setPosition(first.getX(), first.getY(), Move.this.k.isLeftSide());
                        first.drawStatic(batch, Move.this.k.getX() << 7, Move.this.k.getY() << 7, 128.0f, 128.0f);
                        batch.setColor(MaterialColor.ORANGE.P500);
                        GameMapSelectionSystem.drawGateSelection(batch, Move.this.k.getX(), Move.this.k.getY(), Move.this.k.isLeftSide());
                        batch.setColor(Color.WHITE);
                        first.setPosition(first.getX(), first.getY(), isLeftSide);
                    }
                }
                batch.end();
            }
        }
    }
}
