package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.ui.Tree.Node;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.Layout;
import com.prineside.tdi2.scene2d.utils.Selection;
import com.prineside.tdi2.scene2d.utils.UIUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Tree.class */
public class Tree<N extends Node, V> extends WidgetGroup {
    private static final Vector2 o = new Vector2();
    private TreeStyle p;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private N y;
    private N z;
    N n;
    private ClickListener A;
    final Array<N> k = new Array<>();
    private float q = 4.0f;
    private float r = 2.0f;
    float m = 2.0f;
    private boolean x = true;
    final Selection<N> l = (Selection<N>) new Selection<N>() { // from class: com.prineside.tdi2.scene2d.ui.Tree.1
        @Override // com.prineside.tdi2.scene2d.utils.Selection
        protected final void a() {
            switch (size()) {
                case 0:
                    Tree.this.n = null;
                    return;
                case 1:
                    Tree.this.n = (N) first();
                    return;
                default:
                    return;
            }
        }
    };

    public Tree(TreeStyle treeStyle) {
        this.l.setActor(this);
        this.l.setMultiple(true);
        setStyle(treeStyle);
        d();
    }

    private void d() {
        ClickListener clickListener = new ClickListener() { // from class: com.prineside.tdi2.scene2d.ui.Tree.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                N n = (N) Tree.this.getNodeAt(f2);
                if (n != null && n == Tree.this.getNodeAt(getTouchDownY())) {
                    if (Tree.this.l.getMultiple() && Tree.this.l.notEmpty() && UIUtils.shift()) {
                        if (Tree.this.n == null) {
                            Tree.this.n = n;
                        }
                        N n2 = Tree.this.n;
                        if (!UIUtils.ctrl()) {
                            Tree.this.l.clear();
                        }
                        float y = n2.f2691a.getY();
                        float y2 = n.f2691a.getY();
                        if (y > y2) {
                            Tree.this.a(Tree.this.k, y2, y);
                        } else {
                            Tree.this.a(Tree.this.k, y, y2);
                            Tree.this.l.items().orderedItems().reverse();
                        }
                        Tree.this.l.fireChangeEvent();
                        Tree.this.n = n2;
                        return;
                    }
                    if (n.c.size > 0 && (!Tree.this.l.getMultiple() || !UIUtils.ctrl())) {
                        float x = n.f2691a.getX();
                        if (n.e != null) {
                            x -= Tree.this.m + n.e.getMinWidth();
                        }
                        if (f < x) {
                            n.setExpanded(!n.d);
                            return;
                        }
                    }
                    if (n.isSelectable()) {
                        Tree.this.l.choose(n);
                        if (!Tree.this.l.isEmpty()) {
                            Tree.this.n = n;
                        }
                    }
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                Tree.this.setOverNode(Tree.this.getNodeAt(f2));
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.enter(inputEvent, f, f2, i, actor);
                Tree.this.setOverNode(Tree.this.getNodeAt(f2));
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                super.exit(inputEvent, f, f2, i, actor);
                if (actor == null || !actor.isDescendantOf(Tree.this)) {
                    Tree.this.setOverNode(null);
                }
            }
        };
        this.A = clickListener;
        addListener(clickListener);
    }

    public void setStyle(TreeStyle treeStyle) {
        this.p = treeStyle;
        if (this.u == 0.0f) {
            this.u = e();
        }
    }

    public void add(N n) {
        insert(this.k.size, n);
    }

    public void insert(int i, N n) {
        int zIndex;
        if (n.f2692b != null) {
            n.f2692b.remove(n);
            n.f2692b = null;
        } else {
            int indexOf = this.k.indexOf(n, true);
            if (indexOf != -1) {
                if (indexOf == i) {
                    return;
                }
                if (indexOf < i) {
                    i--;
                }
                this.k.removeIndex(indexOf);
                int zIndex2 = n.f2691a.getZIndex();
                if (zIndex2 != -1) {
                    n.b(this, zIndex2);
                }
            }
        }
        this.k.insert(i, n);
        if (i == 0) {
            zIndex = 0;
        } else if (i < this.k.size - 1) {
            zIndex = this.k.get(i + 1).f2691a.getZIndex();
        } else {
            N n2 = this.k.get(i - 1);
            zIndex = n2.f2691a.getZIndex() + n2.a();
        }
        n.a(this, zIndex);
    }

    public void remove(N n) {
        int zIndex;
        if (n.f2692b != null) {
            n.f2692b.remove(n);
        } else if (this.k.removeValue(n, true) && (zIndex = n.f2691a.getZIndex()) != -1) {
            n.b(this, zIndex);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public void clearChildren(boolean z) {
        super.clearChildren(z);
        setOverNode(null);
        this.k.clear();
        this.l.clear();
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.x = true;
    }

    private float e() {
        float max = Math.max(this.p.plus.getMinWidth(), this.p.minus.getMinWidth());
        if (this.p.plusOver != null) {
            max = Math.max(max, this.p.plusOver.getMinWidth());
        }
        if (this.p.minusOver != null) {
            max = Math.max(max, this.p.minusOver.getMinWidth());
        }
        return max;
    }

    private void f() {
        this.x = false;
        this.v = e();
        this.w = 0.0f;
        b(this.k, 0.0f, this.v);
        this.v += this.s + this.t;
    }

    private void b(Array<N> array, float f, float f2) {
        float width;
        float f3 = this.q;
        float f4 = this.r + this.m;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            float f5 = f + f2;
            A a2 = n.f2691a;
            if (a2 instanceof Layout) {
                Layout layout = (Layout) a2;
                width = f5 + layout.getPrefWidth();
                n.f = layout.getPrefHeight();
            } else {
                width = f5 + a2.getWidth();
                n.f = a2.getHeight();
            }
            if (n.e != null) {
                width += f4 + n.e.getMinWidth();
                n.f = Math.max(n.f, n.e.getMinHeight());
            }
            this.v = Math.max(this.v, width);
            this.w += n.f + f3;
            if (n.d) {
                b(n.c, f + this.u, f2);
            }
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        if (this.x) {
            f();
        }
        a(this.k, this.s, getHeight() - (this.q / 2.0f), e());
    }

    private float a(Array<N> array, float f, float f2, float f3) {
        float f4;
        float f5 = this.q;
        float f6 = this.r;
        float f7 = f6 + this.m;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            float f8 = f + f3;
            if (n.e != null) {
                f4 = f8 + f7 + n.e.getMinWidth();
            } else {
                f4 = f8 + f6;
            }
            if (n.f2691a instanceof Layout) {
                ((Layout) n.f2691a).pack();
            }
            float height = f2 - n.getHeight();
            n.f2691a.setPosition(f4, height);
            f2 = height - f5;
            if (n.d) {
                f2 = a(n.c, f + this.u, f2, f3);
            }
        }
        return f2;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        b(batch, f);
        Color color = getColor();
        float f2 = color.f889a * f;
        batch.setColor(color.r, color.g, color.f888b, f2);
        a(batch, color.r, color.g, color.f888b, f2, this.k, this.s, e());
        super.draw(batch, f);
    }

    private void b(Batch batch, float f) {
        if (this.p.background != null) {
            Color color = getColor();
            batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
            this.p.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    private float a(Batch batch, float f, float f2, float f3, float f4, Array<N> array, float f5, float f6) {
        Rectangle cullingArea = getCullingArea();
        float f7 = 0.0f;
        float f8 = 0.0f;
        if (cullingArea != null) {
            float f9 = cullingArea.y;
            f7 = f9;
            f8 = f9 + cullingArea.height;
        }
        TreeStyle treeStyle = this.p;
        float x = getX();
        float y = getY();
        float f10 = x + f5;
        float f11 = f10 + f6 + this.r;
        float f12 = 0.0f;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            A a2 = n.f2691a;
            f12 = a2.getY();
            float f13 = n.f;
            if (cullingArea == null || (f12 + f13 >= f7 && f12 <= f8)) {
                if (this.l.contains(n) && treeStyle.selection != null) {
                    a(treeStyle.selection, batch, x, (y + f12) - (this.q / 2.0f), getWidth(), f13 + this.q);
                } else if (n == this.z && treeStyle.over != null) {
                    b(treeStyle.over, batch, x, (y + f12) - (this.q / 2.0f), getWidth(), f13 + this.q);
                }
                if (n.e != null) {
                    float round = y + f12 + Math.round((f13 - n.e.getMinHeight()) / 2.0f);
                    Color color = a2.getColor();
                    batch.setColor(color.r, color.g, color.f888b, color.f889a * f4);
                    b(n.e, batch, f11, round);
                    batch.setColor(f, f2, f3, f4);
                }
                if (n.c.size > 0) {
                    a(a((Tree<N, V>) n, f11), batch, f10, y + f12 + Math.round((f13 - r0.getMinHeight()) / 2.0f));
                }
            } else if (f12 < f7) {
                break;
            }
            if (n.d && n.c.size > 0) {
                a(batch, f, f2, f3, f4, n.c, f5 + this.u, f6);
            }
        }
        return f12;
    }

    private static void a(Drawable drawable, Batch batch, float f, float f2, float f3, float f4) {
        drawable.draw(batch, f, f2, f3, f4);
    }

    private static void b(Drawable drawable, Batch batch, float f, float f2, float f3, float f4) {
        drawable.draw(batch, f, f2, f3, f4);
    }

    private static void a(Drawable drawable, Batch batch, float f, float f2) {
        drawable.draw(batch, f, f2, drawable.getMinWidth(), drawable.getMinHeight());
    }

    private static void b(Drawable drawable, Batch batch, float f, float f2) {
        drawable.draw(batch, f, f2, drawable.getMinWidth(), drawable.getMinHeight());
    }

    private Drawable a(N n, float f) {
        if (n == this.z && Gdx.app.getType() == Application.ApplicationType.Desktop && (!this.l.getMultiple() || (!UIUtils.ctrl() && !UIUtils.shift()))) {
            float x = screenToLocalCoordinates(o.set(Gdx.input.getX(), 0.0f)).x + getX();
            if (x >= 0.0f && x < f) {
                Drawable drawable = n.d ? this.p.minusOver : this.p.plusOver;
                Drawable drawable2 = drawable;
                if (drawable != null) {
                    return drawable2;
                }
            }
        }
        return n.d ? this.p.minus : this.p.plus;
    }

    @Null
    public N getNodeAt(float f) {
        this.y = null;
        c(this.k, f, getHeight());
        try {
            return this.y;
        } finally {
            this.y = null;
        }
    }

    private float c(Array<N> array, float f, float f2) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            float f3 = n.f;
            float height = f2 - (n.getHeight() - f3);
            if (f >= (height - f3) - this.q && f < height) {
                this.y = n;
                return -1.0f;
            }
            f2 = height - (f3 + this.q);
            if (n.d) {
                float c = c(n.c, f, f2);
                f2 = c;
                if (c == -1.0f) {
                    return -1.0f;
                }
            }
        }
        return f2;
    }

    final void a(Array<N> array, float f, float f2) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            if (n.f2691a.getY() >= f) {
                if (n.isSelectable()) {
                    if (n.f2691a.getY() <= f2) {
                        this.l.add(n);
                    }
                    if (n.d) {
                        a(n.c, f, f2);
                    }
                }
            } else {
                return;
            }
        }
    }

    public Selection<N> getSelection() {
        return this.l;
    }

    @Null
    public N getSelectedNode() {
        return this.l.first();
    }

    @Null
    public V getSelectedValue() {
        N first = this.l.first();
        if (first == null) {
            return null;
        }
        return (V) first.getValue();
    }

    public TreeStyle getStyle() {
        return this.p;
    }

    public Array<N> getRootNodes() {
        return this.k;
    }

    @Deprecated
    public Array<N> getNodes() {
        return this.k;
    }

    public void updateRootNodes() {
        int i = this.k.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = this.k.get(i2);
            int zIndex = n.f2691a.getZIndex();
            if (zIndex != -1) {
                n.b(this, zIndex);
            }
        }
        int i3 = this.k.size;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 += this.k.get(i5).a(this, i4);
        }
    }

    @Null
    public N getOverNode() {
        return this.z;
    }

    @Null
    public V getOverValue() {
        if (this.z == null) {
            return null;
        }
        return (V) this.z.getValue();
    }

    public void setOverNode(@Null N n) {
        this.z = n;
    }

    public void setPadding(float f) {
        this.s = f;
        this.t = f;
    }

    public void setPadding(float f, float f2) {
        this.s = f;
        this.t = f2;
    }

    public void setIndentSpacing(float f) {
        this.u = f;
    }

    public float getIndentSpacing() {
        return this.u;
    }

    public void setYSpacing(float f) {
        this.q = f;
    }

    public float getYSpacing() {
        return this.q;
    }

    public void setIconSpacing(float f, float f2) {
        this.r = f;
        this.m = f2;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.x) {
            f();
        }
        return this.v;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.x) {
            f();
        }
        return this.w;
    }

    public void findExpandedValues(Array<V> array) {
        a((Array<? extends Node>) this.k, (Array) array);
    }

    public void restoreExpandedValues(Array<V> array) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N findNode = findNode(array.get(i2));
            if (findNode != null) {
                findNode.setExpanded(true);
                findNode.expandTo();
            }
        }
    }

    static boolean a(Array<? extends Node> array, Array array2) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            if (node.d) {
                a((Array<? extends Node>) node.c, array2);
                array2.add(node.g);
            }
        }
        return false;
    }

    @Null
    public N findNode(V v) {
        if (v == null) {
            throw new IllegalArgumentException("value cannot be null.");
        }
        return (N) a((Array<? extends Node>) this.k, (Object) v);
    }

    @Null
    static Node a(Array<? extends Node> array, Object obj) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            if (obj.equals(node.g)) {
                return node;
            }
        }
        int i3 = array.size;
        for (int i4 = 0; i4 < i3; i4++) {
            Node a2 = a((Array<? extends Node>) array.get(i4).c, obj);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    public void collapseAll() {
        a((Array<? extends Node>) this.k);
    }

    static void a(Array<? extends Node> array) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            node.setExpanded(false);
            a((Array<? extends Node>) node.c);
        }
    }

    public void expandAll() {
        b((Array<? extends Node>) this.k);
    }

    static void b(Array<? extends Node> array) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            array.get(i2).expandAll();
        }
    }

    public ClickListener getClickListener() {
        return this.A;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Tree$Node.class */
    public static abstract class Node<N extends Node, V, A extends Actor> {

        /* renamed from: a, reason: collision with root package name */
        A f2691a;

        /* renamed from: b, reason: collision with root package name */
        N f2692b;
        final Array<N> c = new Array<>(0);
        private boolean h = true;
        boolean d;
        Drawable e;
        float f;
        V g;

        public Node(A a2) {
            if (a2 == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.f2691a = a2;
        }

        public Node() {
        }

        public void setExpanded(boolean z) {
            Tree<N, V> tree;
            if (z == this.d) {
                return;
            }
            this.d = z;
            if (this.c.size == 0 || (tree = getTree()) == null) {
                return;
            }
            N[] nArr = this.c.items;
            int zIndex = this.f2691a.getZIndex() + 1;
            if (z) {
                int i = this.c.size;
                for (int i2 = 0; i2 < i; i2++) {
                    zIndex += nArr[i2].a(tree, zIndex);
                }
                return;
            }
            int i3 = this.c.size;
            for (int i4 = 0; i4 < i3; i4++) {
                nArr[i4].b(tree, zIndex);
            }
        }

        protected final int a(Tree<N, V> tree, int i) {
            tree.addActorAt(i, this.f2691a);
            if (!this.d) {
                return 1;
            }
            int i2 = i + 1;
            N[] nArr = this.c.items;
            int i3 = this.c.size;
            for (int i4 = 0; i4 < i3; i4++) {
                i2 += nArr[i4].a(tree, i2);
            }
            return i2 - i;
        }

        protected final void b(Tree<N, V> tree, int i) {
            tree.removeActorAt(i, true);
            if (this.d) {
                N[] nArr = this.c.items;
                int i2 = this.c.size;
                for (int i3 = 0; i3 < i2; i3++) {
                    nArr[i3].b(tree, i);
                }
            }
        }

        public void add(N n) {
            insert(this.c.size, n);
        }

        public void addAll(Array<N> array) {
            int i = array.size;
            for (int i2 = 0; i2 < i; i2++) {
                insert(this.c.size, array.get(i2));
            }
        }

        public void insert(int i, N n) {
            Tree<N, V> tree;
            int zIndex;
            n.f2692b = this;
            this.c.insert(i, n);
            if (this.d && (tree = getTree()) != null) {
                if (i == 0) {
                    zIndex = this.f2691a.getZIndex() + 1;
                } else if (i < this.c.size - 1) {
                    zIndex = this.c.get(i + 1).f2691a.getZIndex();
                } else {
                    N n2 = this.c.get(i - 1);
                    zIndex = n2.f2691a.getZIndex() + n2.a();
                }
                n.a(tree, zIndex);
            }
        }

        final int a() {
            if (!this.d) {
                return 1;
            }
            int i = 1;
            N[] nArr = this.c.items;
            int i2 = this.c.size;
            for (int i3 = 0; i3 < i2; i3++) {
                i += nArr[i3].a();
            }
            return i;
        }

        public void remove() {
            Tree<N, V> tree = getTree();
            if (tree != null) {
                tree.remove(this);
            } else if (this.f2692b != null) {
                this.f2692b.remove(this);
            }
        }

        public void remove(N n) {
            Tree<N, V> tree;
            if (this.c.removeValue(n, true) && this.d && (tree = getTree()) != null) {
                n.b(tree, n.f2691a.getZIndex());
            }
        }

        public void clearChildren() {
            Tree<N, V> tree;
            if (this.d && (tree = getTree()) != null) {
                int zIndex = this.f2691a.getZIndex() + 1;
                N[] nArr = this.c.items;
                int i = this.c.size;
                for (int i2 = 0; i2 < i; i2++) {
                    nArr[i2].b(tree, zIndex);
                }
            }
            this.c.clear();
        }

        @Null
        public Tree<N, V> getTree() {
            Group parent = this.f2691a.getParent();
            if (parent instanceof Tree) {
                return (Tree) parent;
            }
            return null;
        }

        public void setActor(A a2) {
            Tree<N, V> tree;
            if (this.f2691a != null && (tree = getTree()) != null) {
                int zIndex = this.f2691a.getZIndex();
                tree.removeActorAt(zIndex, true);
                tree.addActorAt(zIndex, a2);
            }
            this.f2691a = a2;
        }

        public A getActor() {
            return this.f2691a;
        }

        public boolean isExpanded() {
            return this.d;
        }

        public Array<N> getChildren() {
            return this.c;
        }

        public boolean hasChildren() {
            return this.c.size > 0;
        }

        public void updateChildren() {
            Tree<N, V> tree;
            if (this.d && (tree = getTree()) != null) {
                N[] nArr = this.c.items;
                int i = this.c.size;
                int zIndex = this.f2691a.getZIndex() + 1;
                for (int i2 = 0; i2 < i; i2++) {
                    nArr[i2].b(tree, zIndex);
                }
                for (int i3 = 0; i3 < i; i3++) {
                    zIndex += nArr[i3].a(tree, zIndex);
                }
            }
        }

        @Null
        public N getParent() {
            return this.f2692b;
        }

        public void setIcon(@Null Drawable drawable) {
            this.e = drawable;
        }

        @Null
        public V getValue() {
            return this.g;
        }

        public void setValue(@Null V v) {
            this.g = v;
        }

        @Null
        public Drawable getIcon() {
            return this.e;
        }

        public int getLevel() {
            Node<N, V, A> parent;
            int i = 0;
            Node<N, V, A> node = this;
            do {
                i++;
                parent = node.getParent();
                node = parent;
            } while (parent != null);
            return i;
        }

        @Null
        public N findNode(V v) {
            if (v == null) {
                throw new IllegalArgumentException("value cannot be null.");
            }
            return v.equals(this.g) ? this : (N) Tree.a((Array<? extends Node>) this.c, (Object) v);
        }

        public void collapseAll() {
            setExpanded(false);
            Tree.a((Array<? extends Node>) this.c);
        }

        public void expandAll() {
            setExpanded(true);
            if (this.c.size > 0) {
                Tree.b((Array<? extends Node>) this.c);
            }
        }

        public void expandTo() {
            N n = this.f2692b;
            while (true) {
                N n2 = n;
                if (n2 != null) {
                    n2.setExpanded(true);
                    n = n2.f2692b;
                } else {
                    return;
                }
            }
        }

        public boolean isSelectable() {
            return this.h;
        }

        public void setSelectable(boolean z) {
            this.h = z;
        }

        public void findExpandedValues(Array<V> array) {
            if (this.d) {
                Tree.a((Array<? extends Node>) this.c, (Array) array);
                array.add(this.g);
            }
        }

        public void restoreExpandedValues(Array<V> array) {
            int i = array.size;
            for (int i2 = 0; i2 < i; i2++) {
                N findNode = findNode(array.get(i2));
                if (findNode != null) {
                    findNode.setExpanded(true);
                    findNode.expandTo();
                }
            }
        }

        public float getHeight() {
            return this.f;
        }

        public boolean isAscendantOf(N n) {
            if (n == null) {
                throw new IllegalArgumentException("node cannot be null.");
            }
            N n2 = n;
            while (n2 != this) {
                N n3 = n2.f2692b;
                n2 = n3;
                if (n3 == null) {
                    return false;
                }
            }
            return true;
        }

        public boolean isDescendantOf(N n) {
            if (n == null) {
                throw new IllegalArgumentException("node cannot be null.");
            }
            N n2 = this;
            while (n2 != n) {
                N n3 = n2.f2692b;
                n2 = n3;
                if (n3 == null) {
                    return false;
                }
            }
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Tree$TreeStyle.class */
    public static class TreeStyle {
        public Drawable plus;
        public Drawable minus;

        @Null
        public Drawable plusOver;

        @Null
        public Drawable minusOver;

        @Null
        public Drawable over;

        @Null
        public Drawable selection;

        @Null
        public Drawable background;

        public TreeStyle() {
        }

        public TreeStyle(Drawable drawable, Drawable drawable2, @Null Drawable drawable3) {
            this.plus = drawable;
            this.minus = drawable2;
            this.selection = drawable3;
        }

        public TreeStyle(TreeStyle treeStyle) {
            this.plus = treeStyle.plus;
            this.minus = treeStyle.minus;
            this.plusOver = treeStyle.plusOver;
            this.minusOver = treeStyle.minusOver;
            this.over = treeStyle.over;
            this.selection = treeStyle.selection;
            this.background = treeStyle.background;
        }
    }
}
