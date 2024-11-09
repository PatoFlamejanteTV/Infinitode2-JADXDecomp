package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/PooledLinkedList.class */
public class PooledLinkedList<T> {
    private Item<T> head;
    private Item<T> tail;
    private Item<T> iter;
    private Item<T> curr;
    private int size = 0;
    private final Pool<Item<T>> pool;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/PooledLinkedList$Item.class */
    public static final class Item<T> {
        public T payload;
        public Item<T> next;
        public Item<T> prev;

        Item() {
        }
    }

    public PooledLinkedList(int i) {
        this.pool = new Pool<Item<T>>(16, i) { // from class: com.badlogic.gdx.utils.PooledLinkedList.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.badlogic.gdx.utils.Pool
            public Item<T> newObject() {
                return new Item<>();
            }
        };
    }

    public void add(T t) {
        Item<T> obtain = this.pool.obtain();
        obtain.payload = t;
        obtain.next = null;
        obtain.prev = null;
        if (this.head == null) {
            this.head = obtain;
            this.tail = obtain;
            this.size++;
        } else {
            obtain.prev = this.tail;
            this.tail.next = obtain;
            this.tail = obtain;
            this.size++;
        }
    }

    public void addFirst(T t) {
        Item<T> obtain = this.pool.obtain();
        obtain.payload = t;
        obtain.next = this.head;
        obtain.prev = null;
        if (this.head != null) {
            this.head.prev = obtain;
        } else {
            this.tail = obtain;
        }
        this.head = obtain;
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public void iter() {
        this.iter = this.head;
    }

    public void iterReverse() {
        this.iter = this.tail;
    }

    @Null
    public T next() {
        if (this.iter == null) {
            return null;
        }
        T t = this.iter.payload;
        this.curr = this.iter;
        this.iter = this.iter.next;
        return t;
    }

    @Null
    public T previous() {
        if (this.iter == null) {
            return null;
        }
        T t = this.iter.payload;
        this.curr = this.iter;
        this.iter = this.iter.prev;
        return t;
    }

    public void remove() {
        if (this.curr == null) {
            return;
        }
        this.size--;
        Item<T> item = this.curr;
        Item<T> item2 = this.curr.next;
        Item<T> item3 = this.curr.prev;
        this.pool.free(this.curr);
        this.curr = null;
        if (this.size == 0) {
            this.head = null;
            this.tail = null;
        } else if (item == this.head) {
            item2.prev = null;
            this.head = item2;
        } else if (item == this.tail) {
            item3.next = null;
            this.tail = item3;
        } else {
            item3.next = item2;
            item2.prev = item3;
        }
    }

    @Null
    public T removeLast() {
        if (this.tail == null) {
            return null;
        }
        T t = this.tail.payload;
        this.size--;
        Item<T> item = this.tail.prev;
        this.pool.free(this.tail);
        if (this.size == 0) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = item;
            this.tail.next = null;
        }
        return t;
    }

    public void clear() {
        iter();
        while (next() != null) {
            remove();
        }
    }
}
