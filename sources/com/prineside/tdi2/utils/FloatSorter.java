package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FloatSorter.class */
public final class FloatSorter {
    public static final Comparator<Entity> COMPARATOR;

    /* renamed from: a, reason: collision with root package name */
    private final Sort f3830a;

    /* renamed from: b, reason: collision with root package name */
    private final Array<Entity> f3831b;
    private int c;
    private boolean d;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FloatSorter$Entity.class */
    public static final class Entity {
        public Object object;
        public float value;
    }

    static {
        TLog.forClass(FloatSorter.class);
        COMPARATOR = (entity, entity2) -> {
            return Float.compare(entity.value, entity2.value);
        };
    }

    public FloatSorter() {
        this.c = 0;
        this.f3831b = new Array<>(true, 1, Entity.class);
        this.f3830a = new Sort();
    }

    public FloatSorter(Sort sort) {
        this.c = 0;
        this.f3831b = new Array<>(true, 1, Entity.class);
        this.f3830a = sort;
    }

    private void a() {
        if (!this.d) {
            throw new IllegalStateException("begin() has not been called yet");
        }
    }

    public final void begin() {
        if (this.d) {
            throw new IllegalStateException("Previous sorting not finished, call end() before starting another one");
        }
        this.f3831b.clear();
        this.d = true;
    }

    public final void add(Object obj, float f) {
        Entity entity;
        a();
        if (this.c > this.f3831b.size) {
            entity = this.f3831b.items[this.f3831b.size];
            this.f3831b.size++;
        } else {
            entity = new Entity();
            this.f3831b.add(entity);
            this.c++;
        }
        entity.object = obj;
        entity.value = f;
    }

    public final int getCount() {
        return this.f3831b.size;
    }

    public final Array<Entity> sort() {
        a();
        this.f3830a.sort(this.f3831b.items, COMPARATOR, 0, this.f3831b.size);
        return this.f3831b;
    }

    public final void end() {
        a();
        int i = this.f3831b.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.f3831b.items[i2].object = null;
        }
        this.f3831b.size = 0;
        this.d = false;
    }
}
