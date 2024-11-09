package com.prineside.tdi2.ui.shared.luaWhitelistEditor.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.Window;
import com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EClass;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EConstructor;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EField;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.EMethod;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.events.EntryStateChange;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectPair;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/windows/ListClassUsagesWindow.class */
public class ListClassUsagesWindow extends Window {
    private final Class<?> n;
    private final Table o;
    private final Array<Entry> p;
    private final Listener<EntryStateChange> q;

    private static Window.WindowStyle d() {
        Window.WindowStyle createDefaultWindowStyle = Game.i.assetManager.createDefaultWindowStyle();
        createDefaultWindowStyle.resizeable = true;
        createDefaultWindowStyle.inheritWidgetMinSize = true;
        return createDefaultWindowStyle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ListClassUsagesWindow(Class<?> cls, TreeEntry treeEntry) {
        super(d());
        this.p = new Array<>(true, 1, Entry.class);
        this.q = entryStateChange -> {
            e();
        };
        this.n = cls;
        this.minWidth = 400.0f;
        this.minHeight = 48.0f;
        setTitle("Usages of " + cls.getName());
        this.o = new Table();
        this.main.add(this.o).grow();
        Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(18);
        Array array = new Array(true, 1, ObjectPair.class);
        treeEntry.walkRecursively(treeEntry2 -> {
            if (treeEntry2 instanceof EClass) {
                EClass eClass = (EClass) treeEntry2;
                if (eClass.forClass == cls) {
                    array.add(new ObjectPair(eClass, "The class"));
                    return;
                } else {
                    if (cls.isAssignableFrom(eClass.forClass)) {
                        array.add(new ObjectPair(eClass, "Extends this class"));
                        return;
                    }
                    return;
                }
            }
            if (treeEntry2 instanceof EField) {
                EField eField = (EField) treeEntry2;
                if (eField.getField().getType() == cls) {
                    array.add(new ObjectPair(eField, "Field type"));
                    return;
                } else {
                    if (cls.isAssignableFrom(eField.getField().getType())) {
                        array.add(new ObjectPair(eField, "Field type extends this class"));
                        return;
                    }
                    return;
                }
            }
            if (treeEntry2 instanceof EConstructor) {
                EConstructor eConstructor = (EConstructor) treeEntry2;
                for (Class<?> cls2 : eConstructor.getConstructor().getParameterTypes()) {
                    if (cls2 == cls) {
                        array.add(new ObjectPair(eConstructor, "Constructor parameter"));
                        return;
                    } else {
                        if (cls.isAssignableFrom(cls2)) {
                            array.add(new ObjectPair(eConstructor, "Constructor parameter type extends this class"));
                            return;
                        }
                    }
                }
                return;
            }
            if (treeEntry2 instanceof EMethod) {
                EMethod eMethod = (EMethod) treeEntry2;
                if (eMethod.getMethod().getReturnType() == cls) {
                    array.add(new ObjectPair(eMethod, "Method return type"));
                } else if (cls.isAssignableFrom(eMethod.getMethod().getReturnType())) {
                    array.add(new ObjectPair(eMethod, "Method return type extends this class"));
                }
                for (Class<?> cls3 : eMethod.getMethod().getParameterTypes()) {
                    if (cls3 == cls) {
                        array.add(new ObjectPair(eMethod, "Method parameter"));
                        return;
                    } else {
                        if (cls.isAssignableFrom(cls3)) {
                            array.add(new ObjectPair(eMethod, "Method parameter type extends this class"));
                        }
                    }
                }
            }
        });
        if (array.size == 0) {
            this.o.add((Table) new Label("Nothing found", labelStyle)).row();
        } else {
            this.o.add((Table) new Label("Found " + array.size + " entries", labelStyle)).row();
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                TreeEntry treeEntry3 = (TreeEntry) ((ObjectPair) it.next()).first;
                Table table = new Table();
                this.o.add(table).growX().minHeight(24.0f).padTop(1.0f).row();
                Entry entry = new Entry((byte) 0);
                entry.row = table;
                entry.treeEntry = treeEntry3;
                entry.updateColor();
                this.p.add(entry);
                table.add((Table) new Image(treeEntry3.getEntryIcon())).size(16.0f).padLeft(4.0f).padRight(8.0f);
                LabelButton labelButton = new LabelButton(treeEntry3.getName(), labelStyle, () -> {
                    LuajavaWhitelistEditor.i().goToEntry(treeEntry3);
                });
                labelButton.setWrap(true);
                labelButton.setAlignment(8);
                labelButton.setColors(Color.WHITE, new Color(1.0f, 1.0f, 1.0f, 0.78f));
                table.add((Table) labelButton).minHeight(20.0f).padTop(2.0f).padBottom(2.0f).minWidth(300.0f).growX().padRight(8.0f);
                LabelButton labelButton2 = new LabelButton("W", Game.i.assetManager.getLabelStyle(21), () -> {
                    int state = treeEntry3.getState();
                    if (state == 1 || state == 4) {
                        treeEntry3.unsetWhiteListed();
                    } else {
                        treeEntry3.setWhiteListed();
                    }
                    treeEntry3.updateBackground();
                    treeEntry3.updateParentsBackground();
                });
                labelButton2.setColors(MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIGHT_GREEN.P300);
                table.add((Table) labelButton2).width(24.0f).height(24.0f);
                labelButton2.setAlignment(1);
                LabelButton labelButton3 = new LabelButton("B", Game.i.assetManager.getLabelStyle(21), () -> {
                    if (treeEntry3.getState() == 2) {
                        treeEntry3.unsetBlackListed();
                    } else {
                        treeEntry3.setBlackListed();
                    }
                    treeEntry3.updateBackground();
                    treeEntry3.updateParentsBackground();
                });
                labelButton3.setColors(MaterialColor.RED.P500, MaterialColor.RED.P300);
                labelButton3.setAlignment(1);
                table.add((Table) labelButton3).width(24.0f).height(24.0f).padRight(8.0f);
            }
        }
        this.o.row();
        this.o.add().width(1.0f).growY();
        clampWindowPosition();
    }

    @Override // com.prineside.tdi2.ui.actors.Window
    public void show() {
        super.show();
        Game.EVENTS.getListeners(EntryStateChange.class).add(this.q).setName("ListClassUsagesWindow_" + this.n.getName()).setDescription("Updates list colors");
    }

    private void e() {
        Array.ArrayIterator<Entry> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().updateColor();
        }
    }

    @Override // com.prineside.tdi2.ui.actors.Window
    public void close() {
        super.close();
        Game.EVENTS.getListeners(EntryStateChange.class).remove(this.q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/windows/ListClassUsagesWindow$Entry.class */
    public static final class Entry {
        public Table row;
        public TreeEntry treeEntry;

        private Entry() {
        }

        /* synthetic */ Entry(byte b2) {
            this();
        }

        public final void updateColor() {
            TreeEntry.updateBackgroundColorToState(this.treeEntry.getState(), this.row);
            if (this.row.getBackground() == null) {
                TreeEntry treeEntry = this.treeEntry.parent;
                while (true) {
                    TreeEntry treeEntry2 = treeEntry;
                    if (treeEntry2 != null) {
                        if (treeEntry2.getState() == 2) {
                            this.row.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.RED.P800.cpy().lerp(0.0f, 0.0f, 0.0f, 0.56f, 0.56f)));
                            return;
                        }
                        treeEntry = treeEntry2.parent;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
