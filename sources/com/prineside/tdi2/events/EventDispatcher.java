package com.prineside.tdi2.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/EventDispatcher.class */
public class EventDispatcher implements KryoSerializable {

    /* renamed from: b, reason: collision with root package name */
    private static final TLog f1960b = TLog.forClass(EventDispatcher.class);
    public static DeepClassComparator<EventDispatcher> CLASS_COMPARATOR = new DeepClassComparator<EventDispatcher>() { // from class: com.prineside.tdi2.events.EventDispatcher.1
        @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
        public Class<EventDispatcher> forClass() {
            return EventDispatcher.class;
        }

        @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
        public void compare(EventDispatcher eventDispatcher, EventDispatcher eventDispatcher2, DeepClassComparisonConfig deepClassComparisonConfig) {
            deepClassComparisonConfig.addPrefix(".listenerGroups");
            for (int i = 0; i < eventDispatcher.f1961a.size; i++) {
                deepClassComparisonConfig.addPrefix("[" + i + "]");
                for (int i2 = 0; i2 < eventDispatcher.f1961a.size; i2++) {
                    EventListeners<?> eventListeners = eventDispatcher.f1961a.get(i2);
                    int i3 = 0;
                    while (true) {
                        if (i3 < eventDispatcher2.f1961a.size) {
                            EventListeners<?> eventListeners2 = eventDispatcher2.f1961a.get(i3);
                            if (eventListeners2.getEventClass() != eventListeners.getEventClass()) {
                                i3++;
                            } else {
                                SyncChecker.compareObjects(eventListeners, eventListeners2, deepClassComparisonConfig);
                                break;
                            }
                        }
                    }
                }
                deepClassComparisonConfig.popPrefix(1);
            }
            deepClassComparisonConfig.popPrefix(1);
        }
    };

    @NAGS
    private ObjectIntMap<Class<? extends Event>> c = new ObjectIntMap<>();

    /* renamed from: a, reason: collision with root package name */
    protected Array<EventListeners<?>> f1961a = new Array<>(true, 1, EventListeners.class);
    private Array<Event> d = new Array<>();

    static {
        SyncChecker.CLASS_COMPARATORS.add(CLASS_COMPARATOR);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.c);
        kryo.writeObject(output, this.f1961a);
        kryo.writeObject(output, this.d);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.c = (ObjectIntMap) kryo.readObject(input, ObjectIntMap.class);
        this.f1961a = (Array) kryo.readObject(input, Array.class);
        this.d = (Array) kryo.readObject(input, Array.class);
    }

    private int a(Class<? extends Event> cls) {
        Preconditions.checkNotNull(cls, "Event class can not be null");
        if (this.c.get(cls, -1) != -1) {
            throw new IllegalArgumentException("Event class " + cls + " is already registered");
        }
        int i = this.f1961a.size;
        this.f1961a.add(new EventListeners<>(cls));
        this.c.put(cls, i);
        return i;
    }

    public <T extends Event> EventListeners<T> getListeners(Class<T> cls) {
        int i = this.c.get(cls, -1);
        int i2 = i;
        if (i == -1) {
            i2 = a(cls);
        }
        return this.f1961a.items[i2];
    }

    public <T extends Event> void queue(T t) {
        this.d.add(t);
    }

    public boolean hasQueuedEvents() {
        return this.d.size != 0;
    }

    public <T extends Event> T trigger(T t) {
        getListeners(t.getClass()).trigger(t);
        return t;
    }

    public ObjectIntMap<Class<? extends Event>> getClassToId() {
        return this.c;
    }

    public Array<EventListeners<?>> getListenerGroups() {
        return this.f1961a;
    }

    public Array<Event> getQueuedEvents() {
        return this.d;
    }

    public void reset(boolean z) {
        if (z) {
            this.c.clear();
            this.f1961a.clear();
            return;
        }
        for (int i = 0; i < this.f1961a.size; i++) {
            EventListeners<?> eventListeners = this.f1961a.items[i];
            EventListeners.Entry<?>[] entriesBackingArray = eventListeners.getEntriesBackingArray();
            for (int i2 = 0; i2 < eventListeners.size(); i2++) {
                EventListeners.Entry<?> entry = entriesBackingArray[i2];
                if (entry != null && !entry.isPersistent()) {
                    f1960b.i("Removing non-persistent listener " + entry, new Object[0]);
                    entry.remove();
                }
            }
        }
    }

    public StringBuilder describe() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("event types: ").append(String.valueOf(this.f1961a.size));
        for (int i = 0; i < this.f1961a.size; i++) {
            stringBuilder.append(this.f1961a.items[i].describe());
        }
        return stringBuilder;
    }
}
