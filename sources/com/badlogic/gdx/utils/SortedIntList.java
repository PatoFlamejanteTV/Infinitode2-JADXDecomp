package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/SortedIntList.class */
public class SortedIntList<E> implements Iterable<Node<E>> {
    private transient SortedIntList<E>.Iterator iterator;
    Node<E> first;
    private NodePool<E> nodePool = new NodePool<>();
    int size = 0;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/SortedIntList$Node.class */
    public static class Node<E> {
        protected Node<E> p;
        protected Node<E> n;
        public E value;
        public int index;
    }

    @Null
    public E insert(int i, E e) {
        Node<E> node;
        if (this.first != null) {
            Node<E> node2 = this.first;
            while (true) {
                node = node2;
                if (node.n == null || node.n.index > i) {
                    break;
                }
                node2 = node.n;
            }
            if (i > node.index) {
                node.n = this.nodePool.obtain(node, node.n, e, i);
                if (node.n.n != null) {
                    node.n.n.p = node.n;
                }
                this.size++;
                return null;
            }
            if (i < node.index) {
                Node<E> obtain = this.nodePool.obtain(null, this.first, e, i);
                this.first.p = obtain;
                this.first = obtain;
                this.size++;
                return null;
            }
            node.value = e;
            return null;
        }
        this.first = this.nodePool.obtain(null, null, e, i);
        this.size++;
        return null;
    }

    public E get(int i) {
        Node<E> node;
        E e = null;
        if (this.first != null) {
            Node<E> node2 = this.first;
            while (true) {
                node = node2;
                if (node.n == null || node.index >= i) {
                    break;
                }
                node2 = node.n;
            }
            if (node.index == i) {
                e = node.value;
            }
        }
        return e;
    }

    public void clear() {
        while (this.first != null) {
            this.nodePool.free(this.first);
            this.first = this.first.n;
        }
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.lang.Iterable
    public java.util.Iterator<Node<E>> iterator() {
        if (Collections.allocateIterators) {
            return new Iterator();
        }
        if (this.iterator != null) {
            return this.iterator.reset();
        }
        SortedIntList<E>.Iterator iterator = new Iterator();
        this.iterator = iterator;
        return iterator;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/SortedIntList$Iterator.class */
    public class Iterator implements java.util.Iterator<Node<E>> {
        private Node<E> position;
        private Node<E> previousPosition;

        public Iterator() {
            reset();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position != null;
        }

        @Override // java.util.Iterator
        public Node<E> next() {
            this.previousPosition = this.position;
            this.position = this.position.n;
            return this.previousPosition;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.previousPosition != null) {
                if (this.previousPosition == SortedIntList.this.first) {
                    SortedIntList.this.first = this.position;
                } else {
                    this.previousPosition.p.n = this.position;
                    if (this.position != null) {
                        this.position.p = this.previousPosition.p;
                    }
                }
                SortedIntList.this.size--;
            }
        }

        public SortedIntList<E>.Iterator reset() {
            this.position = SortedIntList.this.first;
            this.previousPosition = null;
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/SortedIntList$NodePool.class */
    static class NodePool<E> extends Pool<Node<E>> {
        NodePool() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.utils.Pool
        public Node<E> newObject() {
            return new Node<>();
        }

        public Node<E> obtain(Node<E> node, Node<E> node2, E e, int i) {
            Node<E> node3 = (Node) super.obtain();
            node3.p = node;
            node3.n = node2;
            node3.value = e;
            node3.index = i;
            return node3;
        }
    }
}
