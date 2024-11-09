package com.prineside.tdi2.ui.shared.stateDebugger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.events.EventDispatcher;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.shared.StateDebugger;
import com.prineside.tdi2.ui.shared.stateDebugger.listeners.ListenerGroupViewer;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/ListenersView.class */
public abstract class ListenersView implements StateDebugger.View {

    /* renamed from: a, reason: collision with root package name */
    private Table f3782a;
    public EventDispatcher dispatcher;

    /* renamed from: b, reason: collision with root package name */
    private final Array<Entry> f3783b = new Array<>(true, 1, Entry.class);
    private Integer c;

    public void setDispatcher(EventDispatcher eventDispatcher) {
        this.dispatcher = eventDispatcher;
        rebuildWindow();
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void rebuildWindow() {
        this.f3782a = new Table();
        Table table = StateDebugger.i().contentTable;
        table.clear();
        table.add(this.f3782a).grow();
        onRender();
        StateDebugger.i().window.fitToContent(12, true, false, true);
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void init() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void postInit() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onShow() {
        StateDebugger.i().renderWindow();
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onHide() {
        setDispatcher(null);
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onRender() {
        int i;
        if (this.dispatcher == null) {
            i = -1;
        } else {
            Array<EventListeners<?>> listenerGroups = this.dispatcher.getListenerGroups();
            i = 31 + listenerGroups.size;
            for (int i2 = 0; i2 < listenerGroups.size; i2++) {
                i = (i * 31) + listenerGroups.get(i2).getEventClass().getName().hashCode();
            }
        }
        if (this.c == null || i != this.c.intValue()) {
            this.c = Integer.valueOf(i);
            this.f3782a.clear();
            this.f3783b.clear();
            Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(18);
            if (this.dispatcher == null) {
                this.f3782a.add((Table) new Label("Dispatcher not found", labelStyle));
            } else {
                Label label = new Label("Event class", labelStyle);
                label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3782a.add((Table) label).growX().padRight(6.0f);
                Label label2 = new Label("Triggers", labelStyle);
                label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3782a.add((Table) label2).padLeft(6.0f).padRight(6.0f);
                Label label3 = new Label("Stops", labelStyle);
                label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3782a.add((Table) label3).padLeft(6.0f).padRight(6.0f);
                Label label4 = new Label("No state", labelStyle);
                label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3782a.add((Table) label4).padLeft(6.0f).padRight(6.0f);
                Label label5 = new Label("State", labelStyle);
                label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3782a.add((Table) label5).padLeft(6.0f).padRight(6.0f);
                Label label6 = new Label("Total", labelStyle);
                label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3782a.add((Table) label6).padLeft(6.0f);
                this.f3782a.row();
                StateDebugger.tableRowSep(this.f3782a, 6);
                Array array = new Array(this.dispatcher.getListenerGroups());
                array.sort((eventListeners, eventListeners2) -> {
                    return eventListeners.getEventClass().getSimpleName().compareTo(eventListeners2.getEventClass().getSimpleName());
                });
                for (int i3 = 0; i3 < array.size; i3++) {
                    Entry entry = new Entry((EventListeners) array.get(i3), (byte) 0);
                    this.f3782a.add((Table) entry.f3785b).growX().padRight(6.0f);
                    this.f3782a.add((Table) entry.c).padLeft(6.0f).padRight(6.0f);
                    this.f3782a.add((Table) entry.d).padLeft(6.0f).padRight(6.0f);
                    this.f3782a.add((Table) entry.e).padLeft(6.0f).padRight(6.0f);
                    this.f3782a.add((Table) entry.f).padLeft(6.0f).padRight(6.0f);
                    this.f3782a.add((Table) entry.g).padLeft(6.0f);
                    this.f3783b.add(entry);
                    this.f3782a.row();
                    StateDebugger.tableRowSep(this.f3782a, 6);
                }
            }
            this.f3782a.row();
            this.f3782a.add().width(1.0f).growY();
        }
        for (int i4 = 0; i4 < this.f3783b.size; i4++) {
            this.f3783b.get(i4).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/ListenersView$Entry.class */
    public static class Entry {

        /* renamed from: a, reason: collision with root package name */
        private final EventListeners<?> f3784a;

        /* renamed from: b, reason: collision with root package name */
        private final LabelButton f3785b;
        private final Label c;
        private final Label d;
        private final Label e;
        private final Label f;
        private final Label g;
        private long h;
        private long i;

        /* synthetic */ Entry(EventListeners eventListeners, byte b2) {
            this(eventListeners);
        }

        private Entry(EventListeners<?> eventListeners) {
            this.f3784a = eventListeners;
            Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(18);
            this.f3785b = new LabelButton(eventListeners.getEventClass().getSimpleName(), labelStyle, null);
            this.f3785b.setClickHandler(() -> {
                ListenerGroupViewer listenerGroupViewer = new ListenerGroupViewer(eventListeners);
                Game.i.uiManager.addWindow(listenerGroupViewer);
                listenerGroupViewer.fitToContentSimple();
                Vector2 localToStageCoordinates = this.f3785b.localToStageCoordinates(new Vector2());
                listenerGroupViewer.showAtPointAligned(localToStageCoordinates.x, localToStageCoordinates.y, 1);
            });
            this.f3785b.setAlignment(8);
            this.c = new Label("", labelStyle);
            this.d = new Label("", labelStyle);
            this.e = new Label("", labelStyle);
            this.f = new Label("", labelStyle);
            this.g = new Label("", labelStyle);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            Color color = new Color(1.0f, 1.0f, 1.0f, 0.28f);
            this.c.setText(StringFormatter.compactNumber(this.f3784a.getEventsTriggered(), false));
            if (this.f3784a.getEventsTriggered() == 0) {
                this.c.setColor(color);
            } else if (this.h != this.f3784a.getEventsTriggered()) {
                this.h = this.f3784a.getEventsTriggered();
                this.c.setColor(MaterialColor.LIGHT_GREEN.P500);
                this.c.clearActions();
                this.c.addAction(Actions.sequence(Actions.color(MaterialColor.LIGHT_GREEN.P500), Actions.color(Color.WHITE, 0.25f)));
            }
            this.d.setText(StringFormatter.compactNumber(this.f3784a.getEventsStopped(), false));
            if (this.f3784a.getEventsStopped() == 0) {
                this.d.setColor(color);
            } else if (this.i != this.f3784a.getEventsStopped()) {
                this.i = this.f3784a.getEventsStopped();
                this.d.setColor(MaterialColor.LIGHT_GREEN.P500);
                this.d.clearActions();
                this.d.addAction(Actions.sequence(Actions.color(MaterialColor.LIGHT_GREEN.P500), Actions.color(Color.WHITE, 0.25f)));
            }
            this.e.setTextFromInt(this.f3784a.getNonStateAffectingEntriesCount());
            if (this.f3784a.getNonStateAffectingEntriesCount() == 0) {
                this.e.setColor(color);
            } else {
                this.e.setColor(MaterialColor.CYAN.P300);
            }
            this.f.setTextFromInt(this.f3784a.getStateAffectingEntriesCount());
            if (this.f3784a.getStateAffectingEntriesCount() == 0) {
                this.f.setColor(color);
            } else {
                this.f.setColor(MaterialColor.PURPLE.P300);
            }
            this.g.setTextFromInt(this.f3784a.size());
            if (this.f3784a.size() == 0) {
                this.g.setColor(color);
            } else {
                this.g.setColor(Color.WHITE);
            }
        }
    }
}
