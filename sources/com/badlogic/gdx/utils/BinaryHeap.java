package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.BinaryHeap.Node;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/BinaryHeap.class */
public class BinaryHeap<T extends Node> {
    public int size;
    private Node[] nodes;
    private final boolean isMaxHeap;

    public BinaryHeap() {
        this(16, false);
    }

    public BinaryHeap(int i, boolean z) {
        this.isMaxHeap = z;
        this.nodes = new Node[i];
    }

    public T add(T t) {
        if (this.size == this.nodes.length) {
            Node[] nodeArr = new Node[this.size << 1];
            System.arraycopy(this.nodes, 0, nodeArr, 0, this.size);
            this.nodes = nodeArr;
        }
        t.index = this.size;
        this.nodes[this.size] = t;
        int i = this.size;
        this.size = i + 1;
        up(i);
        return t;
    }

    public T add(T t, float f) {
        t.value = f;
        return add(t);
    }

    public boolean contains(T t, boolean z) {
        if (t == null) {
            throw new IllegalArgumentException("node cannot be null.");
        }
        if (z) {
            for (Node node : this.nodes) {
                if (node == t) {
                    return true;
                }
            }
            return false;
        }
        for (Node node2 : this.nodes) {
            if (node2.equals(t)) {
                return true;
            }
        }
        return false;
    }

    public T peek() {
        if (this.size == 0) {
            throw new IllegalStateException("The heap is empty.");
        }
        return (T) this.nodes[0];
    }

    public T pop() {
        T t = (T) this.nodes[0];
        int i = this.size - 1;
        this.size = i;
        if (i > 0) {
            this.nodes[0] = this.nodes[this.size];
            this.nodes[this.size] = null;
            down(0);
        } else {
            this.nodes[0] = null;
        }
        return t;
    }

    public T remove(T t) {
        int i = this.size - 1;
        this.size = i;
        if (i > 0) {
            Node node = this.nodes[this.size];
            this.nodes[this.size] = null;
            this.nodes[t.index] = node;
            if ((node.value < t.value) ^ this.isMaxHeap) {
                up(t.index);
            } else {
                down(t.index);
            }
        } else {
            this.nodes[0] = null;
        }
        return t;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        Arrays.fill(this.nodes, 0, this.size, (Object) null);
        this.size = 0;
    }

    public void setValue(T t, float f) {
        float f2 = t.value;
        t.value = f;
        if ((f < f2) ^ this.isMaxHeap) {
            up(t.index);
        } else {
            down(t.index);
        }
    }

    private void up(int i) {
        Node[] nodeArr = this.nodes;
        Node node = nodeArr[i];
        float f = node.value;
        while (i > 0) {
            int i2 = (i - 1) >> 1;
            Node node2 = nodeArr[i2];
            if (!((f < node2.value) ^ this.isMaxHeap)) {
                break;
            }
            nodeArr[i] = node2;
            node2.index = i;
            i = i2;
        }
        nodeArr[i] = node;
        node.index = i;
    }

    private void down(int i) {
        Node node;
        float f;
        Node[] nodeArr = this.nodes;
        int i2 = this.size;
        Node node2 = nodeArr[i];
        float f2 = node2.value;
        while (true) {
            int i3 = 1 + (i << 1);
            if (i3 >= i2) {
                break;
            }
            int i4 = i3 + 1;
            Node node3 = nodeArr[i3];
            float f3 = node3.value;
            if (i4 >= i2) {
                node = null;
                f = this.isMaxHeap ? -3.4028235E38f : Float.MAX_VALUE;
            } else {
                Node node4 = nodeArr[i4];
                node = node4;
                f = node4.value;
            }
            if (!((f3 < f) ^ this.isMaxHeap)) {
                if (f != f2) {
                    if ((f > f2) ^ this.isMaxHeap) {
                        break;
                    }
                    nodeArr[i] = node;
                    if (node != null) {
                        node.index = i;
                    }
                    i = i4;
                } else {
                    break;
                }
            } else if (f3 != f2) {
                if ((f3 > f2) ^ this.isMaxHeap) {
                    break;
                }
                nodeArr[i] = node3;
                node3.index = i;
                i = i3;
            } else {
                break;
            }
        }
        nodeArr[i] = node2;
        node2.index = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryHeap)) {
            return false;
        }
        BinaryHeap binaryHeap = (BinaryHeap) obj;
        if (binaryHeap.size != this.size) {
            return false;
        }
        Node[] nodeArr = this.nodes;
        Node[] nodeArr2 = binaryHeap.nodes;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (nodeArr[i2].value != nodeArr2[i2].value) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        Node[] nodeArr = this.nodes;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            i = (i * 31) + Float.floatToIntBits(nodeArr[i3].value);
        }
        return i;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        Node[] nodeArr = this.nodes;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(nodeArr[0].value);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(nodeArr[i].value);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/BinaryHeap$Node.class */
    public static class Node {
        float value;
        int index;

        public Node(float f) {
            this.value = f;
        }

        public float getValue() {
            return this.value;
        }

        public String toString() {
            return Float.toString(this.value);
        }
    }
}
