package com.prineside.tdi2.events;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.events.Event;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Comparator;

@REGS(serializer = Serializer.class, arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/EventListeners.class */
public final class EventListeners<T extends Event> {
    public static final byte FLAG_AUTO_PRIORITY = 1;
    public static final byte FLAG_AFFECTS_STATE = 2;
    public static final byte FLAG_PERSISTENT = 4;
    public static final int PRIORITY_STEP = 10;
    public static final int PRIORITY_HIGHEST = 3000;
    public static final int PRIORITY_VERY_HIGH = 2000;
    public static final int PRIORITY_HIGH = 1000;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_LOW = -1000;
    public static final int PRIORITY_VERY_LOW = -2000;
    public static final int PRIORITY_LOWEST = -3000;
    public static final DeepClassComparator<EventListeners> CLASS_COMPARATOR;

    /* renamed from: a, reason: collision with root package name */
    private Entry<T>[] f1962a;

    /* renamed from: b, reason: collision with root package name */
    private int f1963b;

    @Null
    private Class<?> c;

    @NAGS
    private short d;

    @NAGS
    private int e;

    @NAGS
    private int f;
    private boolean g;

    /* synthetic */ EventListeners(byte b2) {
        this();
    }

    static {
        TLog.forClass(EventListeners.class);
        CLASS_COMPARATOR = new EventListenersDeepClassComparator();
        SyncChecker.CLASS_COMPARATORS.add(CLASS_COMPARATOR);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/events/EventListeners$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<EventListeners> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, EventListeners eventListeners) {
            output.writeString(eventListeners.c == null ? null : eventListeners.c.getName());
            int i = 0;
            for (int i2 = 0; i2 < eventListeners.size(); i2++) {
                if (eventListeners.f1962a[i2] != null && eventListeners.f1962a[i2].isStateAffecting()) {
                    i++;
                }
            }
            output.writeVarInt(i, true);
            for (int i3 = 0; i3 < eventListeners.size(); i3++) {
                Entry entry = eventListeners.f1962a[i3];
                if (entry != null && entry.isStateAffecting()) {
                    kryo.writeClassAndObject(output, entry);
                }
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [com.esotericsoftware.kryo.Kryo] */
        /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Class] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.IllegalArgumentException] */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public EventListeners read2(Kryo kryo, Input input, Class<? extends EventListeners> cls) {
            EventListeners eventListeners = new EventListeners((byte) 0);
            ?? r0 = kryo;
            r0.reference(eventListeners);
            try {
                String readString = input.readString();
                if (readString != null) {
                    r0 = eventListeners.c = ReflectionUtils.getClassByName(readString);
                }
            } catch (IllegalArgumentException e) {
                r0.printStackTrace();
            }
            int readVarInt = input.readVarInt(true);
            eventListeners.f1963b = readVarInt;
            eventListeners.a(readVarInt);
            for (int i = 0; i < readVarInt; i++) {
                eventListeners.f1962a[i] = (Entry) kryo.readClassAndObject(input);
            }
            return eventListeners;
        }
    }

    private EventListeners() {
        this.f1962a = new Entry[2];
        this.d = (short) 0;
    }

    public EventListeners(Class<?> cls) {
        this.f1962a = new Entry[2];
        this.d = (short) 0;
        this.c = cls;
    }

    public final Entry<T>[] getEntriesBackingArray() {
        return this.f1962a;
    }

    private Entry<T> a(Listener<T> listener) {
        for (int i = 0; i < this.f1963b; i++) {
            if (this.f1962a[i] != null && ((Entry) this.f1962a[i]).f1965b == listener) {
                return this.f1962a[i];
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (this.f1962a.length < i) {
            Entry<T>[] entryArr = new Entry[MathUtils.ceil(i * 1.2f)];
            System.arraycopy(this.f1962a, 0, entryArr, 0, this.f1962a.length);
            this.f1962a = entryArr;
        }
    }

    private void a() {
        if (this.g && this.d == 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.f1963b; i2++) {
                if (this.f1962a[i2] == null) {
                    i++;
                } else if (i != 0) {
                    this.f1962a[i2 - i] = this.f1962a[i2];
                    this.f1962a[i2] = null;
                }
            }
            this.f1963b -= i;
            Threads.sortArraySlice(this.f1962a, 0, this.f1963b, Entry.COMPARATOR);
            this.g = false;
        }
    }

    public final void trigger(T t) {
        a();
        if (size() == 0) {
            this.e++;
            return;
        }
        this.d = (short) (this.d + 1);
        this.e++;
        int i = 0;
        int size = size();
        while (true) {
            if (i >= size) {
                break;
            }
            Entry<T> entry = this.f1962a[i];
            if (entry != null) {
                ((Entry) entry).f1965b.handleEvent(t);
                if (t.isStopped()) {
                    this.f++;
                    break;
                }
            }
            i++;
        }
        this.d = (short) (this.d - 1);
        a();
    }

    public final Class<?> getEventClass() {
        return this.c;
    }

    public final boolean contains(Listener<T> listener) {
        return a(listener) != null;
    }

    public final int getStateHash() {
        int i = 0;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            Entry<T> entry = this.f1962a[i2];
            if (entry != null && entry.isStateAffecting()) {
                i = (i * 31) + ((Entry) this.f1962a[i2]).c;
            }
        }
        return i;
    }

    public final int getStateAffectingEntriesCount() {
        int i = 0;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            Entry<T> entry = this.f1962a[i2];
            if (entry != null && entry.isStateAffecting()) {
                i++;
            }
        }
        return i;
    }

    public final int getNonStateAffectingEntriesCount() {
        return size() - getStateAffectingEntriesCount();
    }

    public final int getLoops() {
        return this.d;
    }

    public final Entry<T> add(Listener<T> listener) {
        return addWithFlags(listener, 0);
    }

    public final Entry<T> addWithPriority(Listener<T> listener, int i) {
        return addWithFlagsAndPriority(listener, 0, i);
    }

    public final Entry<T> addStateAffectingWithPriority(Listener<T> listener, int i) {
        return addWithFlagsAndPriority(listener, 2, i);
    }

    public final Entry<T> addWithFlags(Listener<T> listener, int i) {
        boolean z = (i & 2) != 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.f1963b; i3++) {
            Entry<T> entry = this.f1962a[i3];
            if (entry != null && entry.isAutoPriority() && entry.isStateAffecting() == z && ((Entry) entry).c <= i2) {
                i2 = ((Entry) entry).c - 10;
            }
        }
        Entry<T> addWithFlagsAndPriority = addWithFlagsAndPriority(listener, i | 1, i2);
        addWithFlagsAndPriority.a();
        return addWithFlagsAndPriority;
    }

    public final Entry<T> addStateAffecting(Listener<T> listener) {
        return addWithFlags(listener, 2);
    }

    public final Entry<T> addWithFlagsAndPriority(Listener<T> listener, int i, int i2) {
        if (listener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (this.d == 0) {
            Entry<T> a2 = a(listener);
            Entry<T> entry = a2;
            if (a2 == null) {
                entry = new Entry<>();
                a(this.f1963b + 1);
                Entry<T>[] entryArr = this.f1962a;
                int i3 = this.f1963b;
                this.f1963b = i3 + 1;
                entryArr[i3] = entry;
            }
            entry.a(listener, i2, this, i);
            this.g = true;
            return entry;
        }
        Entry<T> a3 = a(listener);
        if (a3 != null) {
            a3.reset();
            a3.a(listener, i2, this, i);
            this.g = true;
            return a3;
        }
        Entry<T> entry2 = new Entry<>();
        entry2.a(listener, i2, this, i);
        a(this.f1963b + 1);
        Entry<T>[] entryArr2 = this.f1962a;
        int i4 = this.f1963b;
        this.f1963b = i4 + 1;
        entryArr2[i4] = entry2;
        this.g = true;
        return entry2;
    }

    public final boolean remove(Listener<T> listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        for (int i = 0; i < this.f1963b; i++) {
            Entry<T> entry = this.f1962a[i];
            if (entry != null && ((Entry) entry).f1965b == listener) {
                this.f1962a[i] = null;
                this.g = true;
                return true;
            }
        }
        return false;
    }

    public final int getEventsTriggered() {
        return this.e;
    }

    public final int getEventsStopped() {
        return this.f;
    }

    public final int size() {
        return this.f1963b;
    }

    public final void clear() {
        if (this.d != 0) {
            throw new IllegalStateException("some loops still running");
        }
        this.f1962a = new Entry[2];
        this.f1963b = 0;
    }

    public final StringBuilder describe() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("listeners of ").append(String.valueOf(this.c)).append(", loops: ").append(String.valueOf((int) this.d)).append(SequenceUtils.EOL);
        stringBuilder.append("entries: ").append(String.valueOf(this.f1963b)).append(SequenceUtils.EOL);
        for (int i = 0; i < this.f1963b; i++) {
            stringBuilder.append("  ").append(String.valueOf(i)).append(SequenceUtils.SPACE).append(String.valueOf(this.f1962a[i])).append(SequenceUtils.EOL);
        }
        return stringBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(byte b2) {
        return String.format("%8s", Integer.toBinaryString(b2)).replace(' ', '0');
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/events/EventListeners$Entry.class */
    public static final class Entry<T extends Event> implements Pool.Poolable, KryoSerializable {
        public static final Comparator<Entry<?>> COMPARATOR = (entry, entry2) -> {
            return Integer.compare(entry2.c, entry.c);
        };

        /* renamed from: b, reason: collision with root package name */
        private Listener<T> f1965b;
        private int c = 0;

        /* renamed from: a, reason: collision with root package name */
        private EntryMetaData<T> f1964a = new EntryMetaData<>();

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            if (!isStateAffecting()) {
                throw new IllegalStateException("Can not write non-state-affecting listener");
            }
            kryo.writeObject(output, this.f1964a);
            kryo.writeClassAndObject(output, this.f1965b);
            output.writeVarInt(this.c, true);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1964a = (EntryMetaData) kryo.readObject(input, EntryMetaData.class);
            this.f1965b = (Listener) kryo.readClassAndObject(input);
            this.c = input.readVarInt(true);
        }

        public final EntryMetaData<T> getMetaData() {
            return this.f1964a;
        }

        public final Entry<T> setName(String str) {
            ((EntryMetaData) this.f1964a).f1966a = str;
            return this;
        }

        public final String getName() {
            if (((EntryMetaData) this.f1964a).f1966a == null) {
                ((EntryMetaData) this.f1964a).f1966a = this.f1965b.getClass().getSimpleName().split("\\$")[0];
            }
            return ((EntryMetaData) this.f1964a).f1966a;
        }

        public final Entry<T> setDescription(@Null String str) {
            ((EntryMetaData) this.f1964a).f1967b = str;
            return this;
        }

        @Null
        public final String getDescription() {
            return ((EntryMetaData) this.f1964a).f1967b;
        }

        public final int getPriority() {
            return this.c;
        }

        public final Listener<T> getListener() {
            return this.f1965b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Entry<T> a(Listener<T> listener, int i, EventListeners<T> eventListeners, int i2) {
            this.f1965b = listener;
            this.c = i;
            ((EntryMetaData) this.f1964a).c = eventListeners;
            ((EntryMetaData) this.f1964a).d = (byte) i2;
            return this;
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public final void reset() {
            this.f1965b = null;
            this.c = 0;
            this.f1964a.a();
        }

        public final void remove() {
            ((EntryMetaData) this.f1964a).c.remove(this.f1965b);
        }

        public final boolean isStateAffecting() {
            return this.f1964a.flagsMatch((byte) 2);
        }

        public final boolean isAutoPriority() {
            return this.f1964a.flagsMatch((byte) 1);
        }

        public final boolean isPersistent() {
            return this.f1964a.flagsMatch((byte) 4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.f1964a.setFlag((byte) 1, true);
        }

        public final Entry<T> setPersistentToTrue() {
            return setPersistent(true);
        }

        public final Entry<T> setPersistent(boolean z) {
            this.f1964a.setFlag((byte) 4, z);
            return this;
        }

        public final String toString() {
            return super.toString() + " (listener: " + this.f1965b + ", flags: " + EventListeners.b(((EntryMetaData) this.f1964a).d) + ", affectsState: " + isStateAffecting() + ", autoPriority: " + isAutoPriority() + ", priority: " + this.c + ")";
        }

        @REGS
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/events/EventListeners$Entry$EntryMetaData.class */
        public static final class EntryMetaData<T extends Event> implements KryoSerializable {

            /* renamed from: a, reason: collision with root package name */
            private String f1966a = null;

            /* renamed from: b, reason: collision with root package name */
            private String f1967b;
            private EventListeners<T> c;
            private byte d;

            @Override // com.esotericsoftware.kryo.KryoSerializable
            public final void write(Kryo kryo, Output output) {
                output.writeString(this.f1966a);
                output.writeString(this.f1967b);
                kryo.writeClassAndObject(output, this.c);
                output.writeByte(this.d);
            }

            @Override // com.esotericsoftware.kryo.KryoSerializable
            public final void read(Kryo kryo, Input input) {
                this.f1966a = input.readString();
                this.f1967b = input.readString();
                this.c = (EventListeners) kryo.readClassAndObject(input);
                this.d = input.readByte();
            }

            public final boolean flagsMatch(byte b2) {
                return (this.d & b2) == b2;
            }

            public final void setFlag(byte b2, boolean z) {
                if (z) {
                    this.d = (byte) (this.d | b2);
                } else {
                    this.d = (byte) (this.d & (b2 ^ (-1)));
                }
            }

            public final byte getFlags() {
                return this.d;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a() {
                this.f1966a = null;
                this.f1967b = null;
                this.c = null;
                this.d = (byte) 0;
            }
        }
    }
}
