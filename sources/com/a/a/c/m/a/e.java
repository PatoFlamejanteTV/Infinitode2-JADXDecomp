package com.a.a.c.m.a;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: infinitode-2.jar:com/a/a/c/m/a/e.class */
public final class e<K, V> extends AbstractMap<K, V> implements Serializable, ConcurrentMap<K, V> {
    private static int d = Runtime.getRuntime().availableProcessors();
    private static int e;
    private static int f;

    /* renamed from: a, reason: collision with root package name */
    final ConcurrentMap<K, h<K, V>> f675a;
    private int g;
    private long[] h;

    /* renamed from: b, reason: collision with root package name */
    final com.a.a.c.m.a.b<h<K, V>> f676b;
    final AtomicLong c;
    private AtomicLong i;
    private Lock j;
    private Queue<Runnable> k;
    private AtomicLongArray l;
    private AtomicLongArray m;
    private AtomicReferenceArray<h<K, V>> n;
    private AtomicReference<c> o;
    private transient Set<K> p;
    private transient Collection<V> q;
    private transient Set<Map.Entry<K, V>> r;

    /* synthetic */ e(b bVar, byte b2) {
        this(bVar);
    }

    static {
        int min = Math.min(4, a(d));
        e = min;
        f = min - 1;
    }

    private static int a(int i2) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i2 - 1));
    }

    private static int a(int i2, int i3) {
        return (i2 * 16) + i3;
    }

    private e(b<K, V> bVar) {
        this.g = bVar.f679a;
        this.i = new AtomicLong(Math.min(bVar.c, 9223372034707292160L));
        this.f675a = new ConcurrentHashMap(bVar.f680b, 0.75f, this.g);
        this.j = new ReentrantLock();
        this.c = new AtomicLong();
        this.f676b = new com.a.a.c.m.a.b<>();
        this.k = new ConcurrentLinkedQueue();
        this.o = new AtomicReference<>(c.f681a);
        this.h = new long[e];
        this.l = new AtomicLongArray(e);
        this.m = new AtomicLongArray(e);
        this.n = new AtomicReferenceArray<>(e << 4);
    }

    private static void a(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    static void b(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    private boolean b() {
        return this.c.get() > this.i.get();
    }

    final void a() {
        h<K, V> poll;
        while (b() && (poll = this.f676b.poll()) != null) {
            this.f675a.remove(poll.f691a, poll);
            b(poll);
        }
    }

    private void c(h<K, V> hVar) {
        int c2 = c();
        a(c2, a(c2, hVar));
    }

    private static int c() {
        return ((int) Thread.currentThread().getId()) & f;
    }

    private long a(int i2, h<K, V> hVar) {
        long j2 = this.l.get(i2);
        this.l.lazySet(i2, j2 + 1);
        this.n.lazySet(a(i2, (int) (j2 & 15)), hVar);
        return j2;
    }

    private void a(int i2, long j2) {
        if (this.o.get().a(j2 - this.m.get(i2) < 4)) {
            d();
        }
    }

    private void a(Runnable runnable) {
        this.k.add(runnable);
        this.o.lazySet(c.f682b);
        d();
    }

    private void d() {
        if (this.j.tryLock()) {
            try {
                this.o.lazySet(c.c);
                e();
            } finally {
                this.o.compareAndSet(c.c, c.f681a);
                this.j.unlock();
            }
        }
    }

    private void e() {
        f();
        g();
    }

    private void f() {
        int id = (int) Thread.currentThread().getId();
        int i2 = id + e;
        for (int i3 = id; i3 < i2; i3++) {
            b(i3 & f);
        }
    }

    private void b(int i2) {
        int a2;
        h<K, V> hVar;
        long j2 = this.l.get(i2);
        for (int i3 = 0; i3 < 8 && (hVar = this.n.get((a2 = a(i2, (int) (this.h[i2] & 15))))) != null; i3++) {
            this.n.lazySet(a2, null);
            a((h) hVar);
            long[] jArr = this.h;
            jArr[i2] = jArr[i2] + 1;
        }
        this.m.lazySet(i2, j2);
    }

    final void a(h<K, V> hVar) {
        if (this.f676b.a(hVar)) {
            this.f676b.b(hVar);
        }
    }

    private void g() {
        Runnable poll;
        for (int i2 = 0; i2 < 16 && (poll = this.k.poll()) != null; i2++) {
            poll.run();
        }
    }

    private static boolean a(h<K, V> hVar, m<V> mVar) {
        if (mVar.a()) {
            return hVar.compareAndSet(mVar, new m(mVar.f701b, -mVar.f700a));
        }
        return false;
    }

    private static void d(h<K, V> hVar) {
        m mVar;
        do {
            mVar = (m) hVar.get();
            if (!mVar.a()) {
                return;
            }
        } while (!hVar.compareAndSet(mVar, new m(mVar.f701b, -mVar.f700a)));
    }

    final void b(h<K, V> hVar) {
        m mVar;
        do {
            mVar = (m) hVar.get();
        } while (!hVar.compareAndSet(mVar, new m(mVar.f701b, 0)));
        this.c.lazySet(this.c.get() - Math.abs(mVar.f700a));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$a.class */
    public final class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private h<K, V> f677a;

        /* renamed from: b, reason: collision with root package name */
        private int f678b = 1;

        a(h<K, V> hVar, int i) {
            this.f677a = hVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            e.this.c.lazySet(e.this.c.get() + this.f678b);
            if (((m) this.f677a.get()).a()) {
                e.this.f676b.add(this.f677a);
                e.this.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$i.class */
    public final class i implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private h<K, V> f693a;

        i(h<K, V> hVar) {
            this.f693a = hVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            e.this.f676b.d(this.f693a);
            e.this.b(this.f693a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$j.class */
    public final class j implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f695a;

        /* renamed from: b, reason: collision with root package name */
        private h<K, V> f696b;

        j(h<K, V> hVar, int i) {
            this.f695a = i;
            this.f696b = hVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            e.this.c.lazySet(e.this.c.get() + this.f695a);
            e.this.a((h) this.f696b);
            e.this.a();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return this.f675a.isEmpty();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f675a.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.j.lock();
        while (true) {
            try {
                h<K, V> poll = this.f676b.poll();
                if (poll == null) {
                    break;
                }
                this.f675a.remove(poll.f691a, poll);
                b(poll);
            } finally {
                this.j.unlock();
            }
        }
        for (int i2 = 0; i2 < this.n.length(); i2++) {
            this.n.lazySet(i2, null);
        }
        while (true) {
            Runnable poll2 = this.k.poll();
            if (poll2 != null) {
                poll2.run();
            } else {
                return;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return this.f675a.containsKey(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        a(obj);
        Iterator<h<K, V>> it = this.f675a.values().iterator();
        while (it.hasNext()) {
            if (it.next().c().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        h<K, V> hVar = this.f675a.get(obj);
        if (hVar == null) {
            return null;
        }
        c(hVar);
        return hVar.c();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V put(K k2, V v) {
        return a(k2, v, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final V putIfAbsent(K k2, V v) {
        return a(k2, v, true);
    }

    private V a(K k2, V v, boolean z) {
        m mVar;
        a(k2);
        a(v);
        m mVar2 = new m(v, 1);
        h<K, V> hVar = new h<>(k2, mVar2);
        while (true) {
            h<K, V> putIfAbsent = this.f675a.putIfAbsent(hVar.f691a, hVar);
            if (putIfAbsent == null) {
                a((Runnable) new a(hVar, 1));
                return null;
            }
            if (z) {
                c(putIfAbsent);
                return putIfAbsent.c();
            }
            do {
                mVar = (m) putIfAbsent.get();
                if (mVar.a()) {
                }
            } while (!putIfAbsent.compareAndSet(mVar, mVar2));
            int i2 = 1 - mVar.f700a;
            if (i2 == 0) {
                c(putIfAbsent);
            } else {
                a((Runnable) new j(putIfAbsent, i2));
            }
            return mVar.f701b;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        h<K, V> remove = this.f675a.remove(obj);
        if (remove == null) {
            return null;
        }
        d(remove);
        a((Runnable) new i(remove));
        return remove.c();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final boolean remove(Object obj, Object obj2) {
        h<K, V> hVar = this.f675a.get(obj);
        if (hVar == null || obj2 == null) {
            return false;
        }
        m mVar = (m) hVar.get();
        while (mVar.a(obj2)) {
            if (a(hVar, mVar)) {
                if (this.f675a.remove(obj, hVar)) {
                    a((Runnable) new i(hVar));
                    return true;
                }
                return false;
            }
            m mVar2 = (m) hVar.get();
            mVar = mVar2;
            if (!mVar2.a()) {
                return false;
            }
        }
        return false;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final V replace(K k2, V v) {
        m mVar;
        a(k2);
        a(v);
        m mVar2 = new m(v, 1);
        h<K, V> hVar = this.f675a.get(k2);
        if (hVar == null) {
            return null;
        }
        do {
            mVar = (m) hVar.get();
            if (!mVar.a()) {
                return null;
            }
        } while (!hVar.compareAndSet(mVar, mVar2));
        int i2 = 1 - mVar.f700a;
        if (i2 == 0) {
            c(hVar);
        } else {
            a((Runnable) new j(hVar, i2));
        }
        return mVar.f701b;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final boolean replace(K k2, V v, V v2) {
        m mVar;
        a(k2);
        a(v);
        a(v2);
        m mVar2 = new m(v2, 1);
        h<K, V> hVar = this.f675a.get(k2);
        if (hVar == null) {
            return false;
        }
        do {
            mVar = (m) hVar.get();
            if (!mVar.a() || !mVar.a(v)) {
                return false;
            }
        } while (!hVar.compareAndSet(mVar, mVar2));
        int i2 = 1 - mVar.f700a;
        if (i2 == 0) {
            c(hVar);
            return true;
        }
        a((Runnable) new j(hVar, i2));
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        Set<K> set = this.p;
        if (set != null) {
            return set;
        }
        g gVar = new g();
        this.p = gVar;
        return gVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection<V> values() {
        Collection<V> collection = this.q;
        if (collection != null) {
            return collection;
        }
        l lVar = new l();
        this.q = lVar;
        return lVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.r;
        if (set != null) {
            return set;
        }
        C0012e c0012e = new C0012e();
        this.r = c0012e;
        return c0012e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$c.class */
    public static abstract class c {

        /* renamed from: a, reason: collision with root package name */
        public static final c f681a = new com.a.a.c.m.a.f("IDLE", 0);

        /* renamed from: b, reason: collision with root package name */
        public static final c f682b = new com.a.a.c.m.a.g("REQUIRED", 1);
        public static final c c = new com.a.a.c.m.a.h("PROCESSING", 2);
        private static final /* synthetic */ c[] d = {f681a, f682b, c};

        abstract boolean a(boolean z);

        public static c[] values() {
            return (c[]) d.clone();
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        private c(String str, int i) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ c(String str, int i, byte b2) {
            this(str, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$m.class */
    public static final class m<V> {

        /* renamed from: a, reason: collision with root package name */
        final int f700a;

        /* renamed from: b, reason: collision with root package name */
        final V f701b;

        m(V v, int i) {
            this.f700a = i;
            this.f701b = v;
        }

        final boolean a(Object obj) {
            return obj == this.f701b || this.f701b.equals(obj);
        }

        final boolean a() {
            return this.f700a > 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$h.class */
    public static final class h<K, V> extends AtomicReference<m<V>> implements com.a.a.c.m.a.a<h<K, V>> {

        /* renamed from: a, reason: collision with root package name */
        final K f691a;

        /* renamed from: b, reason: collision with root package name */
        private h<K, V> f692b;
        private h<K, V> c;

        h(K k, m<V> mVar) {
            super(mVar);
            this.f691a = k;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.m.a.a
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public h<K, V> a() {
            return this.f692b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.m.a.a
        public void a(h<K, V> hVar) {
            this.f692b = hVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.m.a.a
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public h<K, V> b() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.m.a.a
        public void b(h<K, V> hVar) {
            this.c = hVar;
        }

        final V c() {
            return ((m) get()).f701b;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$g.class */
    final class g extends AbstractSet<K> {

        /* renamed from: a, reason: collision with root package name */
        private e<K, V> f689a;

        g() {
            this.f689a = e.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return this.f689a.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            this.f689a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<K> iterator() {
            return new f();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            return e.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            return this.f689a.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final Object[] toArray() {
            return this.f689a.f675a.keySet().toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final <T> T[] toArray(T[] tArr) {
            return (T[]) this.f689a.f675a.keySet().toArray(tArr);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$f.class */
    final class f implements Iterator<K> {

        /* renamed from: a, reason: collision with root package name */
        private Iterator<K> f687a;

        /* renamed from: b, reason: collision with root package name */
        private K f688b;

        f() {
            this.f687a = e.this.f675a.keySet().iterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f687a.hasNext();
        }

        @Override // java.util.Iterator
        public final K next() {
            this.f688b = this.f687a.next();
            return this.f688b;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e.b(this.f688b != null);
            e.this.remove(this.f688b);
            this.f688b = null;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$l.class */
    final class l extends AbstractCollection<V> {
        l() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final int size() {
            return e.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final void clear() {
            e.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            return new k();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            return e.this.containsValue(obj);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$k.class */
    final class k implements Iterator<V> {

        /* renamed from: a, reason: collision with root package name */
        private Iterator<h<K, V>> f697a;

        /* renamed from: b, reason: collision with root package name */
        private h<K, V> f698b;

        k() {
            this.f697a = e.this.f675a.values().iterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f697a.hasNext();
        }

        @Override // java.util.Iterator
        public final V next() {
            this.f698b = this.f697a.next();
            return this.f698b.c();
        }

        @Override // java.util.Iterator
        public final void remove() {
            e.b(this.f698b != null);
            e.this.remove(this.f698b.f691a);
            this.f698b = null;
        }
    }

    /* renamed from: com.a.a.c.m.a.e$e, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$e.class */
    final class C0012e extends AbstractSet<Map.Entry<K, V>> {

        /* renamed from: a, reason: collision with root package name */
        private e<K, V> f685a;

        C0012e() {
            this.f685a = e.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* synthetic */ boolean add(Object obj) {
            return a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return this.f685a.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            this.f685a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            h<K, V> hVar = this.f685a.f675a.get(entry.getKey());
            return hVar != null && hVar.c().equals(entry.getValue());
        }

        private static boolean a() {
            throw new UnsupportedOperationException("ConcurrentLinkedHashMap does not allow add to be called on entrySet()");
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.f685a.remove(entry.getKey(), entry.getValue());
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$d.class */
    final class d implements Iterator<Map.Entry<K, V>> {

        /* renamed from: a, reason: collision with root package name */
        private Iterator<h<K, V>> f683a;

        /* renamed from: b, reason: collision with root package name */
        private h<K, V> f684b;

        d() {
            this.f683a = e.this.f675a.values().iterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f683a.hasNext();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            this.f684b = this.f683a.next();
            return new n(this.f684b);
        }

        @Override // java.util.Iterator
        public final void remove() {
            e.b(this.f684b != null);
            e.this.remove(this.f684b.f691a);
            this.f684b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$n.class */
    public final class n extends AbstractMap.SimpleEntry<K, V> {
        n(h<K, V> hVar) {
            super(hVar.f691a, hVar.c());
        }

        @Override // java.util.AbstractMap.SimpleEntry, java.util.Map.Entry
        public final V setValue(V v) {
            e.this.put(getKey(), v);
            return (V) super.setValue(v);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/e$b.class */
    public static final class b<K, V> {
        long c = -1;

        /* renamed from: b, reason: collision with root package name */
        int f680b = 16;

        /* renamed from: a, reason: collision with root package name */
        int f679a = 16;

        public final b<K, V> a(int i) {
            e.a(i >= 0);
            this.f680b = i;
            return this;
        }

        public final b<K, V> a(long j) {
            e.a(j >= 0);
            this.c = j;
            return this;
        }

        public final b<K, V> b(int i) {
            e.a(true);
            this.f679a = 4;
            return this;
        }

        public final e<K, V> a() {
            e.b(this.c >= 0);
            return new e<>(this, (byte) 0);
        }
    }
}
