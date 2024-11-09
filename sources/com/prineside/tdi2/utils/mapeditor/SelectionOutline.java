package com.prineside.tdi2.utils.mapeditor;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Gate;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/SelectionOutline.class */
public final class SelectionOutline {
    public final Array<Edge> edges = new Array<>(true, 1, Edge.class);

    public final void add(int i, int i2, Edge.Side side) {
        this.edges.add(new Edge(i, i2, side));
    }

    public final void removeOverGate(Gate gate) {
        if (!gate.isLeftSide()) {
            for (int i = this.edges.size - 1; i >= 0; i--) {
                Edge edge = this.edges.items[i];
                if (edge.side == Edge.Side.BOTTOM && edge.x == gate.getX() && edge.y == gate.getY()) {
                    this.edges.removeIndex(i);
                } else if (edge.side == Edge.Side.TOP && edge.x == gate.getX() && edge.y + 1 == gate.getY()) {
                    this.edges.removeIndex(i);
                }
            }
            return;
        }
        for (int i2 = this.edges.size - 1; i2 >= 0; i2--) {
            Edge edge2 = this.edges.items[i2];
            if (edge2.side == Edge.Side.LEFT && edge2.x == gate.getX() && edge2.y == gate.getY()) {
                this.edges.removeIndex(i2);
            } else if (edge2.side == Edge.Side.RIGHT && edge2.x + 1 == gate.getX() && edge2.y == gate.getY()) {
                this.edges.removeIndex(i2);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/SelectionOutline$Edge.class */
    public static class Edge {
        public int x;
        public int y;
        public Side side;

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/mapeditor/SelectionOutline$Edge$Side.class */
        public enum Side {
            LEFT,
            RIGHT,
            TOP,
            BOTTOM
        }

        public Edge(int i, int i2, Side side) {
            this.x = i;
            this.y = i2;
            this.side = side;
        }
    }
}
