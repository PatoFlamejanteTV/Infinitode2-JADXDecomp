package com.prineside.tdi2.ui.shared.stateDebugger.listeners;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.prineside.luaj.lib.jse.LuajavaLib;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.Render;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.Window;
import com.prineside.tdi2.ui.shared.StateDebugger;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/listeners/ListenerGroupViewer.class */
public class ListenerGroupViewer extends Window {
    private final Listener<Render> n;
    private final EventListeners<?> o;
    private Table p;
    private final Array<Entry> q;
    private Integer r;

    private static Window.WindowStyle d() {
        Window.WindowStyle createDefaultWindowStyle = Game.i.assetManager.createDefaultWindowStyle();
        createDefaultWindowStyle.resizeable = true;
        createDefaultWindowStyle.inheritWidgetMinSize = true;
        return createDefaultWindowStyle;
    }

    public ListenerGroupViewer(EventListeners<?> eventListeners) {
        super(d());
        this.n = this::a;
        this.q = new Array<>(true, 1, Entry.class);
        this.o = eventListeners;
        this.minWidth = 400.0f;
        this.minHeight = 200.0f;
        setTitle("Listeners of " + eventListeners.getEventClass().getSimpleName());
        this.p = new Table();
        a(new Render(0.0f));
    }

    @Override // com.prineside.tdi2.ui.actors.Window
    public void showAtPointAligned(float f, float f2, int i) {
        super.showAtPointAligned(f, f2, i);
        Game.EVENTS.getListeners(Render.class).add(this.n).setName("ListenerGroupViewer_" + this.o.getEventClass().getSimpleName()).setDescription("Updates listener list view");
        fitToContentSimple();
    }

    private void a(Render render) {
        int i = -1;
        EventListeners.Entry<?>[] entriesBackingArray = this.o.getEntriesBackingArray();
        for (EventListeners.Entry<?> entry : entriesBackingArray) {
            if (entry != null) {
                i = (((i * 31) + entry.getListener().hashCode()) * 31) + entry.getName().hashCode();
            }
        }
        if (this.r == null || i != this.r.intValue()) {
            this.r = Integer.valueOf(i);
            this.main.clear();
            this.p.clear();
            this.main.add(this.p).grow().pad(8.0f, 12.0f, 12.0f, 12.0f);
            this.q.clear();
            Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(18);
            Label label = new Label("Listener name", labelStyle);
            label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            this.p.add((Table) label).padRight(6.0f);
            Label label2 = new Label("Priority", labelStyle);
            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            this.p.add((Table) label2).padRight(6.0f).padLeft(6.0f);
            Label label3 = new Label("Affects state", labelStyle);
            label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            this.p.add((Table) label3).padRight(6.0f).padLeft(6.0f);
            Label label4 = new Label("Scripted", labelStyle);
            label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            this.p.add((Table) label4).padRight(6.0f).padLeft(6.0f);
            Label label5 = new Label("Description", labelStyle);
            label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            this.p.add((Table) label5).growX().padLeft(6.0f);
            this.p.row();
            StateDebugger.tableRowSep(this.p, 6);
            Array array = new Array(true, this.o.size(), EventListeners.Entry.class);
            for (int i2 = 0; i2 < this.o.size(); i2++) {
                EventListeners.Entry<?> entry2 = entriesBackingArray[i2];
                if (entry2 != null) {
                    array.add(entry2);
                }
            }
            array.sort(EventListeners.Entry.COMPARATOR);
            for (int i3 = 0; i3 < array.size; i3++) {
                EventListeners.Entry entry3 = (EventListeners.Entry) array.get(i3);
                if (entry3 != null) {
                    Entry entry4 = new Entry(entry3, (byte) 0);
                    this.p.add((Table) entry4.f3787b).growX().padRight(6.0f);
                    this.p.add((Table) entry4.c).padRight(6.0f).padLeft(6.0f);
                    this.p.add((Table) entry4.d).padRight(6.0f).padLeft(6.0f);
                    this.p.add((Table) entry4.e).padRight(6.0f).padLeft(6.0f);
                    this.p.add((Table) entry4.f).growX().minWidth(200.0f).padLeft(6.0f);
                    this.q.add(entry4);
                    this.p.row();
                    StateDebugger.tableRowSep(this.p, 6);
                }
            }
            this.p.row();
            this.p.add().width(1.0f).growY();
            clampWindowPosition();
        }
        for (int i4 = 0; i4 < this.q.size; i4++) {
            this.q.get(i4).a();
        }
    }

    @Override // com.prineside.tdi2.ui.actors.Window
    public void close() {
        super.close();
        Game.EVENTS.getListeners(Render.class).remove(this.n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/listeners/ListenerGroupViewer$Entry.class */
    public static class Entry {

        /* renamed from: a, reason: collision with root package name */
        private final EventListeners.Entry<?> f3786a;

        /* renamed from: b, reason: collision with root package name */
        private final Label f3787b;
        private final Label c;
        private final Label d;
        private final Label e;
        private final LimitedWidthLabel f;

        /* synthetic */ Entry(EventListeners.Entry entry, byte b2) {
            this(entry);
        }

        private Entry(final EventListeners.Entry<?> entry) {
            this.f3786a = entry;
            Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(18);
            this.f3787b = new Label("", labelStyle);
            this.f3787b.setAlignment(8);
            this.f3787b.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.stateDebugger.listeners.ListenerGroupViewer.Entry.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    String str = entry.getListener().getClass().getName() + SequenceUtils.EOL;
                    if (entry.getListener() instanceof LuajavaLib.ProxyInvocationHandler) {
                        str = (str + "Lua script: \n") + entry + SequenceUtils.EOL;
                    }
                    TooltipsOverlay.i().showText("ListenerGroupViewer-listenerDescr", Entry.this.f3787b, str + entry.getDescription(), UiManager.MainUiLayer.OVERLAY, Game.i.uiManager.getWindowsLayer().zIndex + 1, 4);
                }
            });
            this.c = new Label("", labelStyle);
            this.d = new Label("", labelStyle);
            this.e = new Label("", labelStyle);
            this.f = new LimitedWidthLabel("", 18, 18, 400.0f);
            a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            Color color = new Color(1.0f, 1.0f, 1.0f, 0.28f);
            boolean z = this.f3786a.getListener() instanceof LuajavaLib.ProxyInvocationHandler;
            this.f3787b.setText(this.f3786a.getName());
            if (z) {
                this.f3787b.setColor(MaterialColor.LIME.P300);
            } else {
                this.f3787b.setColor(Color.WHITE);
            }
            this.c.setText(this.f3786a.getPriority() + (this.f3786a.isAutoPriority() ? " (A)" : ""));
            this.d.setText(this.f3786a.isStateAffecting() ? "+" : "-");
            if (this.f3786a.isStateAffecting()) {
                this.d.setColor(MaterialColor.LIGHT_GREEN.P500);
            } else {
                this.d.setColor(color);
            }
            if (z) {
                this.e.setText("+");
                this.e.setColor(MaterialColor.LIME.P500);
            } else {
                this.e.setText("-");
                this.e.setColor(color);
            }
            String description = this.f3786a.getDescription();
            if (description != null) {
                this.f.setText(description);
            } else {
                this.f.setText("-");
            }
        }
    }
}
