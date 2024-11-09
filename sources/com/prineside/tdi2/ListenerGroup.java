package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.GameListener;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/ListenerGroup.class */
public final class ListenerGroup<T extends GameListener> {

    /* renamed from: b, reason: collision with root package name */
    private final Class f1734b;
    private DelayedRemovalArray<T> c;

    @NAGS
    private final DelayedRemovalArray<T> d;
    private ObjectSet<T> e;
    private Array<T> f;

    @NAGS
    private int g;

    @NAGS
    private String h;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1733a = TLog.forClass(ListenerGroup.class);
    public static DeepClassComparator<ListenerGroup> CLASS_COMPARATOR = new DeepClassComparator<ListenerGroup>() { // from class: com.prineside.tdi2.ListenerGroup.1
        @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
        public Class<ListenerGroup> forClass() {
            return ListenerGroup.class;
        }

        @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
        public void compare(ListenerGroup listenerGroup, ListenerGroup listenerGroup2, DeepClassComparisonConfig deepClassComparisonConfig) {
            deepClassComparisonConfig.addPrefix(".stateAffectingListeners");
            SyncChecker.compareObjects(listenerGroup.c, listenerGroup2.c, deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(1);
            deepClassComparisonConfig.addPrefix(".removing");
            SyncChecker.compareObjects(listenerGroup.e, listenerGroup2.e, deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(1);
            deepClassComparisonConfig.addPrefix(".adding");
            SyncChecker.compareObjects(listenerGroup.f, listenerGroup2.f, deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(1);
        }
    };

    static {
        SyncChecker.CLASS_COMPARATORS.add(CLASS_COMPARATOR);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ListenerGroup$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<ListenerGroup> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, ListenerGroup listenerGroup) {
            kryo.writeClass(output, listenerGroup.f1734b);
            kryo.writeClassAndObject(output, listenerGroup.c);
            kryo.writeObject(output, listenerGroup.e);
            kryo.writeObject(output, listenerGroup.f);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public ListenerGroup read2(Kryo kryo, Input input, Class<? extends ListenerGroup> cls) {
            ListenerGroup listenerGroup = new ListenerGroup(kryo.readClass(input).getType());
            listenerGroup.c = (DelayedRemovalArray) kryo.readClassAndObject(input);
            listenerGroup.e = (ObjectSet) kryo.readObject(input, ObjectSet.class);
            listenerGroup.f = (Array) kryo.readObject(input, Array.class);
            return listenerGroup;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public ListenerGroup copy(Kryo kryo, ListenerGroup listenerGroup) {
            ListenerGroup listenerGroup2 = new ListenerGroup(listenerGroup.f1734b);
            listenerGroup2.c = (DelayedRemovalArray) kryo.getSerializer(DelayedRemovalArray.class).copy(kryo, listenerGroup.c);
            listenerGroup2.e = (ObjectSet) kryo.getSerializer(ObjectSet.class).copy(kryo, listenerGroup.e);
            listenerGroup2.f = (Array) kryo.getSerializer(Array.class).copy(kryo, listenerGroup.f);
            return listenerGroup2;
        }
    }

    public ListenerGroup(Class cls) {
        this.e = new ObjectSet<>();
        this.g = 0;
        this.f1734b = cls;
        this.c = new DelayedRemovalArray<>(true, 1, cls);
        this.d = new DelayedRemovalArray<>(true, 1, cls);
        this.f = new Array<>(true, 1, cls);
    }

    public ListenerGroup(Class cls, String str) {
        this(cls);
        this.h = str;
    }

    public final boolean contains(T t) {
        return (t.affectsGameState() ? this.c : this.d).contains(t, true);
    }

    public final int getStateHash() {
        int i = 0;
        for (int i2 = 0; i2 < this.c.size; i2++) {
            i = (i * 31) + this.c.items[i2].getConstantId();
        }
        return i;
    }

    public final boolean add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (this.h != null) {
            f1733a.i(this.h + " add " + t.getClass().getName() + " (" + (t.affectsGameState() ? "true" : "false") + ")", new Object[0]);
        }
        if (this.g == 0) {
            DelayedRemovalArray<T> delayedRemovalArray = t.affectsGameState() ? this.c : this.d;
            for (int i = 0; i < delayedRemovalArray.size; i++) {
                if (delayedRemovalArray.items[i] == t) {
                    return false;
                }
            }
            delayedRemovalArray.add(t);
            return true;
        }
        if (!t.affectsGameState()) {
            throw new IllegalStateException("listener not affects game state, should not be added inside other listener");
        }
        for (int i2 = 0; i2 < this.f.size; i2++) {
            if (this.f.items[i2] == t) {
                return false;
            }
        }
        boolean z = false;
        int i3 = this.c.size - 1;
        while (true) {
            if (i3 < 0) {
                break;
            }
            if (this.c.items[i3] == t) {
                z = true;
                break;
            }
            i3--;
        }
        if (z) {
            if (this.e.contains(t)) {
                this.e.remove(t);
                this.c.add(t);
                return true;
            }
            return false;
        }
        this.f.add(t);
        return true;
    }

    public final boolean remove(T t) {
        if (t == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (this.h != null) {
            f1733a.i(this.h + " remove " + t.getClass().getName() + " (" + (t.affectsGameState() ? "true" : "false") + ")", new Object[0]);
        }
        if (this.g != 0) {
            if (!t.affectsGameState()) {
                throw new IllegalStateException("listener not affects game state, should not be removed inside other listener");
            }
            if (this.e.contains(t)) {
                return false;
            }
            if (this.c.contains(t, true)) {
                this.e.add(t);
                return this.c.removeValue(t, true);
            }
            if (this.f.contains(t, true)) {
                return this.f.removeValue(t, true);
            }
            return false;
        }
        return this.c.removeValue(t, true);
    }

    public final void begin() {
        this.c.begin();
        this.d.begin();
        this.g++;
    }

    public final void end() {
        this.c.end();
        this.d.end();
        this.g--;
        if (this.g < 0) {
            throw new IllegalStateException("begin() called more times than end()");
        }
        if (this.g == 0) {
            this.c.addAll((Array<? extends T>) this.f);
            this.f.clear();
            this.e.clear();
        }
    }

    public final int size() {
        return this.c.size + this.d.size;
    }

    public final T get(int i) {
        if (this.g == 0) {
            throw new IllegalStateException("begin() must be called first");
        }
        if (i < this.c.size) {
            return this.c.items[i];
        }
        return this.d.items[i - this.c.size];
    }

    public final void clear() {
        if (this.g != 0) {
            throw new IllegalStateException("some loops still running");
        }
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f.clear();
    }

    public final void describe() {
        f1733a.i("loops: " + this.g, new Object[0]);
        f1733a.i("state affecting: " + this.c.size, new Object[0]);
        for (int i = 0; i < this.c.size; i++) {
            f1733a.i("  " + i + SequenceUtils.SPACE + String.valueOf(this.c.items[i]), new Object[0]);
        }
        f1733a.i("non intrusive: " + this.d.size, new Object[0]);
        for (int i2 = 0; i2 < this.d.size; i2++) {
            f1733a.i("  " + i2 + SequenceUtils.SPACE + String.valueOf(this.d.items[i2]), new Object[0]);
        }
        f1733a.i("removing: " + this.e.size, new Object[0]);
        ObjectSet.ObjectSetIterator<T> it = this.e.iterator();
        while (it.hasNext()) {
            f1733a.i("  " + String.valueOf(it.next()), new Object[0]);
        }
        f1733a.i("adding: " + this.f.size, new Object[0]);
        for (int i3 = 0; i3 < this.f.size; i3++) {
            f1733a.i("  " + i3 + SequenceUtils.SPACE + String.valueOf(this.f.items[i3]), new Object[0]);
        }
    }
}
