package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.Render;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ScriptManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.Window;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EClass;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EConstructor;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EField;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EInterfaceConstructor;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EMethod;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EPackage;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.windows.ListClassUsagesWindow;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LuajavaWhitelistEditor.class */
public class LuajavaWhitelistEditor extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3689a = TLog.forClass(LuajavaWhitelistEditor.class);
    public static final String TYPE_COLOR = "#FFFFFF80";

    /* renamed from: b, reason: collision with root package name */
    private final Window f3690b;
    private Table c;
    private volatile EPackage d;

    @Null
    private TreeEntry e;
    private final Listener<Render> f = new Listener<Render>() { // from class: com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor.1
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(Render render) {
            Vector2 vector2 = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Game.i.uiManager.stage.screenToStageCoordinates(vector2);
            Actor hit = Game.i.uiManager.stage.hit(vector2.x, vector2.y, true);
            TreeEntry treeEntry = null;
            while (true) {
                if (hit == null) {
                    break;
                }
                if (hit.getUserObject() instanceof TreeEntry) {
                    treeEntry = (TreeEntry) hit.getUserObject();
                    break;
                }
                hit = hit.getParent();
            }
            if (LuajavaWhitelistEditor.this.e != treeEntry) {
                if (LuajavaWhitelistEditor.this.e != null) {
                    LuajavaWhitelistEditor.this.e.hovered = false;
                    LuajavaWhitelistEditor.this.e.updateBackground();
                }
                LuajavaWhitelistEditor.this.e = treeEntry;
                if (treeEntry != null) {
                    treeEntry.hovered = true;
                    LuajavaWhitelistEditor.this.e.updateBackground();
                }
            }
        }
    };

    public static LuajavaWhitelistEditor i() {
        return (LuajavaWhitelistEditor) Game.i.uiManager.getComponent(LuajavaWhitelistEditor.class);
    }

    public LuajavaWhitelistEditor() {
        Window.WindowStyle createDefaultWindowStyle = Game.i.assetManager.createDefaultWindowStyle();
        createDefaultWindowStyle.resizeable = true;
        createDefaultWindowStyle.inheritWidgetMinSize = true;
        createDefaultWindowStyle.resizeHandleSize = 12.0f;
        createDefaultWindowStyle.resizeHandleOverlap = 2.0f;
        createDefaultWindowStyle.resizeHandleSizeHeader = 18.0f;
        createDefaultWindowStyle.resizeHandleOverlapHeader = 3.0f;
        this.f3690b = new Window(createDefaultWindowStyle);
        this.f3690b.setTitle("Lua whitelist editor");
        this.f3690b.addListener(new Window.WindowListener.Adapter() { // from class: com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor.2
            @Override // com.prineside.tdi2.ui.actors.Window.WindowListener.Adapter, com.prineside.tdi2.ui.actors.Window.WindowListener
            public void closed() {
                LuajavaWhitelistEditor.this.hide();
            }
        });
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        Game.i.uiManager.stage.unfocusAll();
        Game.EVENTS.getListeners(Render.class).remove(this.f);
    }

    public void showClassUsagesWindow(Class<?> cls) {
        ListClassUsagesWindow listClassUsagesWindow = new ListClassUsagesWindow(cls, this.d);
        Game.i.uiManager.addWindow(listClassUsagesWindow);
        listClassUsagesWindow.fitToContentSimple();
        listClassUsagesWindow.showAtCursor();
    }

    public void show() {
        if (!Config.isModdingMode() && !Game.i.progressManager.isDeveloperModeEnabled()) {
            Dialog.i().showAlert("Developer mode research required");
            return;
        }
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            Dialog.i().showAlert("This thing only works on a PC version of the game");
            return;
        }
        String property = System.getProperty("java.specification.version", "not set");
        f3689a.i("Java version: " + property, new Object[0]);
        if (!"1.8".equals(property)) {
            Notifications.i().addFailure("You are running Java " + property + "! Use Java 8 for a list of classes and methods which is (kinda) compatible with all platforms. Using Java 9+ will show more methods on this list which may not be available on the other platforms.");
            Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor.3
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    Notifications.i().addFailure("If you are running the game through .exe, you are doing it wrong. Run the game through java -jar, otherwise you won't be able to see the whole class tree here");
                }
            }, 2.0f);
        }
        this.c = new Table();
        Table table = new Table();
        Table table2 = new Table();
        table.add(table2).growX().pad(10.0f).padBottom(0.0f).row();
        FancyButton fancyButton = new FancyButton("regularButton.a", () -> {
            reInitialize();
        });
        fancyButton.add((FancyButton) new Label("Rebuild the tree", Game.i.assetManager.getLabelStyle(21))).padLeft(10.0f).padRight(10.0f);
        table2.add(fancyButton).height(32.0f).padRight(8.0f);
        FancyButton fancyButton2 = new FancyButton("regularButton.b", () -> {
            TextInputOverlay.i().show(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor.4
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    try {
                        LuajavaWhitelistEditor.this.showClassUsagesWindow(Class.forName(str));
                    } catch (Exception e) {
                        LuajavaWhitelistEditor.f3689a.e("failed to find class " + str, e);
                        Notifications.i().addFailure("Class with this name not found");
                    }
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, "Class name", "", "Full class name");
        });
        fancyButton2.add((FancyButton) new Label("List class usages", Game.i.assetManager.getLabelStyle(21))).padLeft(10.0f).padRight(10.0f);
        table2.add(fancyButton2).height(32.0f).padRight(8.0f);
        FancyButton fancyButton3 = new FancyButton("regularButton.a", () -> {
            Array<String> array = new Array<>();
            Array.ArrayIterator<TreeEntry> it = this.d.getChildren().iterator();
            while (it.hasNext()) {
                it.next().gatherSaveData(array);
            }
            StringBuilder stringBuilder = new StringBuilder();
            Array.ArrayIterator<String> it2 = array.iterator();
            while (it2.hasNext()) {
                stringBuilder.append(it2.next()).append(SequenceUtils.EOL);
            }
            Gdx.files.local(ScriptManager.WHITELIST_FILE_PATH).writeString(stringBuilder.toString(), false, "UTF-8");
            Notifications.i().addSuccess("Saved!");
        });
        fancyButton3.add((FancyButton) new Label("Save", Game.i.assetManager.getLabelStyle(21))).padLeft(10.0f).padRight(10.0f);
        table2.add(fancyButton3).height(32.0f);
        table2.add().height(1.0f).growX();
        table.add(this.c).pad(10.0f).grow();
        UiUtils.enableMouseMoveScrollFocus(this.f3690b.getScrollPane());
        Game.EVENTS.getListeners(Render.class).add(this.f).setName("LuajavaWhitelistEditor").setDescription("Updates hovering");
        reInitialize();
        Game.i.uiManager.addWindow(this.f3690b);
        this.f3690b.setContents(table);
        this.f3690b.minWidth = 600.0f;
        this.f3690b.minHeight = 400.0f;
        this.f3690b.fitToContent(1, true, true, true);
        this.f3690b.clampWindowPosition();
        this.f3690b.showAtCursor();
    }

    public void goToEntry(TreeEntry treeEntry) {
        this.d.setChildrenCollapsedRecursively();
        Array array = new Array(true, 1, TreeEntry.class);
        TreeEntry treeEntry2 = treeEntry.parent;
        while (true) {
            TreeEntry treeEntry3 = treeEntry2;
            if (treeEntry3 == null) {
                break;
            }
            array.add(treeEntry3);
            treeEntry2 = treeEntry3.parent;
        }
        for (int i = array.size - 1; i >= 0; i--) {
            ((TreeEntry) array.get(i)).setCollapsed(false);
        }
        Game.i.uiManager.runOnStageActOnce(() -> {
            Vector2 vector2 = new Vector2();
            treeEntry.getListEntry().localToActorCoordinates(this.c, vector2);
            this.f3690b.getScrollPane().scrollTo(0.0f, vector2.y, 1.0f, 24.0f, false, true);
            this.f3690b.getScrollPane().updateVisualScroll();
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setFillParent(true);
            treeEntry.getListEntry().addActor(image);
            image.addAction(Actions.sequence(Actions.alpha(0.0f, 0.2f), Actions.removeActor()));
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        HashSet hashSet = new HashSet();
        hashSet.add(Object.class);
        Array.ArrayIterator<String> it = ScriptManager.getPackagesToScanFromConfig().iterator();
        while (it.hasNext()) {
            String next = it.next();
            Array<Class<?>> filterClasses = ReflectionUtils.LuaRelated.filterClasses(Game.i.actionResolver.getClasses(next), null);
            hashSet.addAll(Arrays.asList(filterClasses.toArray()));
            f3689a.i("- " + next + ": " + filterClasses.size, new Object[0]);
        }
        f3689a.i("Total: " + hashSet.size(), new Object[0]);
        Array array = new Array(true, hashSet.size(), Class.class);
        array.addAll((Class[]) hashSet.toArray(new Class[0]));
        Threads.sortGdxArray(array, (cls, cls2) -> {
            int compare = Integer.compare(cls.getName().length(), cls2.getName().length());
            if (compare != 0) {
                return compare;
            }
            return cls.getName().compareTo(cls2.getName());
        });
        HashMap hashMap = new HashMap();
        EPackage ePackage = new EPackage(null, "", "");
        Array.ArrayIterator it2 = array.iterator();
        while (it2.hasNext()) {
            Class cls3 = (Class) it2.next();
            String name = cls3.getName();
            String[] split = name.split("[.$]+");
            TreeEntry treeEntry = null;
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (true) {
                if (i < split.length - 1) {
                    String str = split[i];
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append(".");
                    }
                    stringBuilder.append(str);
                    String substring = name.substring(0, stringBuilder.toString().length());
                    TreeEntry treeEntry2 = (TreeEntry) hashMap.get(substring);
                    TreeEntry treeEntry3 = treeEntry2;
                    if (treeEntry2 == null) {
                        boolean z = false;
                        try {
                            Class.forName(substring);
                            z = true;
                        } catch (Exception unused) {
                        }
                        if (!z) {
                            treeEntry3 = new EPackage(treeEntry, str, substring);
                            if (treeEntry != null) {
                                treeEntry.addChild(treeEntry3);
                            }
                            if (i == 0) {
                                ePackage.addChild(treeEntry3);
                            }
                            hashMap.put(substring, treeEntry3);
                        }
                    }
                    treeEntry = treeEntry3;
                    i++;
                } else {
                    if (treeEntry == null) {
                        treeEntry = new EPackage(null, "", null);
                    }
                    EClass eClass = new EClass(cls3, treeEntry);
                    treeEntry.addChild(eClass);
                    hashMap.put(cls3.getName(), eClass);
                    if (cls3.isInterface()) {
                        eClass.addChild(new EInterfaceConstructor(eClass));
                    }
                    Array<Constructor<?>> gatherConstructors = ReflectionUtils.LuaRelated.gatherConstructors(cls3);
                    for (int i2 = 0; i2 < gatherConstructors.size; i2++) {
                        eClass.addChild(new EConstructor(eClass, gatherConstructors.get(i2)));
                    }
                    Array<Field> gatherFields = ReflectionUtils.LuaRelated.gatherFields(cls3);
                    for (int i3 = 0; i3 < gatherFields.size; i3++) {
                        eClass.addChild(new EField(gatherFields.get(i3), eClass));
                    }
                    Array<Method> gatherMethods = ReflectionUtils.LuaRelated.gatherMethods(cls3);
                    for (int i4 = 0; i4 < gatherMethods.size; i4++) {
                        eClass.addChild(new EMethod(gatherMethods.get(i4), eClass));
                    }
                }
            }
        }
        Comparator comparator = (treeEntry4, treeEntry5) -> {
            int sortCategory = treeEntry4.getSortCategory();
            int sortCategory2 = treeEntry5.getSortCategory();
            if (sortCategory < sortCategory2) {
                return -1;
            }
            if (sortCategory2 < sortCategory) {
                return 1;
            }
            return treeEntry4.getSortName().compareTo(treeEntry5.getSortName());
        };
        Iterator it3 = hashMap.values().iterator();
        while (it3.hasNext()) {
            Threads.sortGdxArray(((TreeEntry) it3.next()).children, comparator);
        }
        try {
            Whitelist fromFile = Whitelist.fromFile(AssetManager.localOrInternalFile(ScriptManager.WHITELIST_FILE_PATH));
            Array.ArrayIterator<TreeEntry> it4 = ePackage.getChildren().iterator();
            while (it4.hasNext()) {
                a(it4.next(), fromFile);
            }
        } catch (Exception e) {
            f3689a.e("failed to apply whitelist from file res/luaj/whitelist.txt", e);
            Notifications.i().addFailure("Failed to load / apply the whitelist from file, check console");
        }
        this.d = ePackage;
    }

    private void a(TreeEntry treeEntry, Whitelist whitelist) {
        treeEntry.applyStateFromWhitelist(whitelist);
        Array.ArrayIterator<TreeEntry> it = treeEntry.getChildren().iterator();
        while (it.hasNext()) {
            a(it.next(), whitelist);
        }
    }

    public void rebuildUi() {
        this.c.clear();
        if (this.d == null) {
            f3689a.i("rebuildUi: showing Loading...", new Object[0]);
            Image image = new Image(Game.i.assetManager.getDrawable("infinitode-2-logo"));
            image.setOrigin(16.0f, 16.0f);
            image.addAction(Actions.forever(Actions.rotateBy(-360.0f, 2.0f)));
            this.c.add((Table) image).size(32.0f);
            this.c.add((Table) new Label("Loading...", Game.i.assetManager.getLabelStyle(24))).padLeft(8.0f);
            return;
        }
        f3689a.i("rebuildUi: visualizing a tree", new Object[0]);
        Array.ArrayIterator<TreeEntry> it = this.d.getChildren().iterator();
        while (it.hasNext()) {
            this.c.add(it.next().getListEntry()).growX().row();
        }
        this.c.row();
        this.c.add().width(1.0f).growY();
        this.f3690b.fitToContent(1, true, false, true);
        Game.i.uiManager.runOnStageAct(() -> {
            if (this.f3690b.getHeight() < 900.0f) {
                this.f3690b.setHeight(900.0f);
            }
        });
    }

    public void reInitialize() {
        this.e = null;
        this.d = null;
        rebuildUi();
        Threads.i().runAsync(() -> {
            try {
                b();
            } catch (Exception e) {
                f3689a.e("failed to load class tree", e);
            }
            Threads.i().runOnMainThread(() -> {
                rebuildUi();
                this.f3690b.fitToContent(1, true, false, true);
            });
        });
    }
}
