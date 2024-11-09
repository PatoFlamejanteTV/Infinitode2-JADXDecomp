package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Tree.class */
public class Tree<N extends Node, V> extends WidgetGroup {
    private static final Vector2 tmp = new Vector2();
    TreeStyle style;
    final Array<N> rootNodes;
    final Selection<N> selection;
    float ySpacing;
    float iconSpacingLeft;
    float iconSpacingRight;
    float paddingLeft;
    float paddingRight;
    float indentSpacing;
    private float prefWidth;
    private float prefHeight;
    private boolean sizeInvalid;
    private N foundNode;
    private N overNode;
    N rangeStart;
    private ClickListener clickListener;

    public Tree(Skin skin) {
        this((TreeStyle) skin.get(TreeStyle.class));
    }

    public Tree(Skin skin, String str) {
        this((TreeStyle) skin.get(str, TreeStyle.class));
    }

    public Tree(TreeStyle treeStyle) {
        this.rootNodes = new Array<>();
        this.ySpacing = 4.0f;
        this.iconSpacingLeft = 2.0f;
        this.iconSpacingRight = 2.0f;
        this.sizeInvalid = true;
        this.selection = (Selection<N>) new Selection<N>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Tree.1
            @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
            protected void changed() {
                switch (size()) {
                    case 0:
                        Tree.this.rangeStart = null;
                        return;
                    case 1:
                        Tree.this.rangeStart = (N) first();
                        return;
                    default:
                        return;
                }
            }
        };
        this.selection.setActor(this);
        this.selection.setMultiple(true);
        setStyle(treeStyle);
        initialize();
    }

    private void initialize() {
        ClickListener clickListener = new ClickListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Tree.2
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                N n = (N) Tree.this.getNodeAt(f2);
                if (n != null && n == Tree.this.getNodeAt(getTouchDownY())) {
                    if (Tree.this.selection.getMultiple() && Tree.this.selection.notEmpty() && UIUtils.shift()) {
                        if (Tree.this.rangeStart == null) {
                            Tree.this.rangeStart = n;
                        }
                        N n2 = Tree.this.rangeStart;
                        if (!UIUtils.ctrl()) {
                            Tree.this.selection.clear();
                        }
                        float y = n2.actor.getY();
                        float y2 = n.actor.getY();
                        if (y > y2) {
                            Tree.this.selectNodes(Tree.this.rootNodes, y2, y);
                        } else {
                            Tree.this.selectNodes(Tree.this.rootNodes, y, y2);
                            Tree.this.selection.items().orderedItems().reverse();
                        }
                        Tree.this.selection.fireChangeEvent();
                        Tree.this.rangeStart = n2;
                        return;
                    }
                    if (n.children.size > 0 && (!Tree.this.selection.getMultiple() || !UIUtils.ctrl())) {
                        float x = n.actor.getX();
                        if (n.icon != null) {
                            x -= Tree.this.iconSpacingRight + n.icon.getMinWidth();
                        }
                        if (f < x) {
                            n.setExpanded(!n.expanded);
                            return;
                        }
                    }
                    if (n.isSelectable()) {
                        Tree.this.selection.choose(n);
                        if (!Tree.this.selection.isEmpty()) {
                            Tree.this.rangeStart = n;
                        }
                    }
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                Tree.this.setOverNode(Tree.this.getNodeAt(f2));
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.enter(inputEvent, f, f2, i, actor);
                Tree.this.setOverNode(Tree.this.getNodeAt(f2));
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                super.exit(inputEvent, f, f2, i, actor);
                if (actor == null || !actor.isDescendantOf(Tree.this)) {
                    Tree.this.setOverNode(null);
                }
            }
        };
        this.clickListener = clickListener;
        addListener(clickListener);
    }

    public void setStyle(TreeStyle treeStyle) {
        this.style = treeStyle;
        if (this.indentSpacing == 0.0f) {
            this.indentSpacing = plusMinusWidth();
        }
    }

    public void add(N n) {
        insert(this.rootNodes.size, n);
    }

    public void insert(int i, N n) {
        int zIndex;
        if (n.parent != null) {
            n.parent.remove(n);
            n.parent = null;
        } else {
            int indexOf = this.rootNodes.indexOf(n, true);
            if (indexOf != -1) {
                if (indexOf == i) {
                    return;
                }
                if (indexOf < i) {
                    i--;
                }
                this.rootNodes.removeIndex(indexOf);
                int zIndex2 = n.actor.getZIndex();
                if (zIndex2 != -1) {
                    n.removeFromTree(this, zIndex2);
                }
            }
        }
        this.rootNodes.insert(i, n);
        if (i == 0) {
            zIndex = 0;
        } else if (i < this.rootNodes.size - 1) {
            zIndex = this.rootNodes.get(i + 1).actor.getZIndex();
        } else {
            N n2 = this.rootNodes.get(i - 1);
            zIndex = n2.actor.getZIndex() + n2.countActors();
        }
        n.addToTree(this, zIndex);
    }

    public void remove(N n) {
        int zIndex;
        if (n.parent != null) {
            n.parent.remove(n);
        } else if (this.rootNodes.removeValue(n, true) && (zIndex = n.actor.getZIndex()) != -1) {
            n.removeFromTree(this, zIndex);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void clearChildren(boolean z) {
        super.clearChildren(z);
        setOverNode(null);
        this.rootNodes.clear();
        this.selection.clear();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    private float plusMinusWidth() {
        float max = Math.max(this.style.plus.getMinWidth(), this.style.minus.getMinWidth());
        if (this.style.plusOver != null) {
            max = Math.max(max, this.style.plusOver.getMinWidth());
        }
        if (this.style.minusOver != null) {
            max = Math.max(max, this.style.minusOver.getMinWidth());
        }
        return max;
    }

    private void computeSize() {
        this.sizeInvalid = false;
        this.prefWidth = plusMinusWidth();
        this.prefHeight = 0.0f;
        computeSize(this.rootNodes, 0.0f, this.prefWidth);
        this.prefWidth += this.paddingLeft + this.paddingRight;
    }

    private void computeSize(Array<N> array, float f, float f2) {
        float width;
        float f3 = this.ySpacing;
        float f4 = this.iconSpacingLeft + this.iconSpacingRight;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            float f5 = f + f2;
            A a2 = n.actor;
            if (a2 instanceof Layout) {
                Layout layout = (Layout) a2;
                width = f5 + layout.getPrefWidth();
                n.height = layout.getPrefHeight();
            } else {
                width = f5 + a2.getWidth();
                n.height = a2.getHeight();
            }
            if (n.icon != null) {
                width += f4 + n.icon.getMinWidth();
                n.height = Math.max(n.height, n.icon.getMinHeight());
            }
            this.prefWidth = Math.max(this.prefWidth, width);
            this.prefHeight += n.height + f3;
            if (n.expanded) {
                computeSize(n.children, f + this.indentSpacing, f2);
            }
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        if (this.sizeInvalid) {
            computeSize();
        }
        layout(this.rootNodes, this.paddingLeft, getHeight() - (this.ySpacing / 2.0f), plusMinusWidth());
    }

    private float layout(Array<N> array, float f, float f2, float f3) {
        float f4;
        float f5 = this.ySpacing;
        float f6 = this.iconSpacingLeft;
        float f7 = f6 + this.iconSpacingRight;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            float f8 = f + f3;
            if (n.icon != null) {
                f4 = f8 + f7 + n.icon.getMinWidth();
            } else {
                f4 = f8 + f6;
            }
            if (n.actor instanceof Layout) {
                ((Layout) n.actor).pack();
            }
            float height = f2 - n.getHeight();
            n.actor.setPosition(f4, height);
            f2 = height - f5;
            if (n.expanded) {
                f2 = layout(n.children, f + this.indentSpacing, f2, f3);
            }
        }
        return f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        drawBackground(batch, f);
        Color color = getColor();
        float f2 = color.f889a * f;
        batch.setColor(color.r, color.g, color.f888b, f2);
        drawIcons(batch, color.r, color.g, color.f888b, f2, null, this.rootNodes, this.paddingLeft, plusMinusWidth());
        super.draw(batch, f);
    }

    protected void drawBackground(Batch batch, float f) {
        if (this.style.background != null) {
            Color color = getColor();
            batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
            this.style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    protected float drawIcons(Batch batch, float f, float f2, float f3, float f4, @Null N n, Array<N> array, float f5, float f6) {
        Rectangle cullingArea = getCullingArea();
        float f7 = 0.0f;
        float f8 = 0.0f;
        if (cullingArea != null) {
            float f9 = cullingArea.y;
            f7 = f9;
            f8 = f9 + cullingArea.height;
        }
        TreeStyle treeStyle = this.style;
        float x = getX();
        float y = getY();
        float f10 = x + f5;
        float f11 = f10 + f6 + this.iconSpacingLeft;
        float f12 = 0.0f;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n2 = array.get(i2);
            A a2 = n2.actor;
            f12 = a2.getY();
            float f13 = n2.height;
            if (cullingArea == null || (f12 + f13 >= f7 && f12 <= f8)) {
                if (this.selection.contains(n2) && treeStyle.selection != null) {
                    drawSelection(n2, treeStyle.selection, batch, x, (y + f12) - (this.ySpacing / 2.0f), getWidth(), f13 + this.ySpacing);
                } else if (n2 == this.overNode && treeStyle.over != null) {
                    drawOver(n2, treeStyle.over, batch, x, (y + f12) - (this.ySpacing / 2.0f), getWidth(), f13 + this.ySpacing);
                }
                if (n2.icon != null) {
                    float round = y + f12 + Math.round((f13 - n2.icon.getMinHeight()) / 2.0f);
                    Color color = a2.getColor();
                    batch.setColor(color.r, color.g, color.f888b, color.f889a * f4);
                    drawIcon(n2, n2.icon, batch, f11, round);
                    batch.setColor(f, f2, f3, f4);
                }
                if (n2.children.size > 0) {
                    drawExpandIcon(n2, getExpandIcon(n2, f11), batch, f10, y + f12 + Math.round((f13 - r0.getMinHeight()) / 2.0f));
                }
            } else if (f12 < f7) {
                break;
            }
            if (n2.expanded && n2.children.size > 0) {
                drawIcons(batch, f, f2, f3, f4, n2, n2.children, f5 + this.indentSpacing, f6);
            }
        }
        return f12;
    }

    protected void drawSelection(N n, Drawable drawable, Batch batch, float f, float f2, float f3, float f4) {
        drawable.draw(batch, f, f2, f3, f4);
    }

    protected void drawOver(N n, Drawable drawable, Batch batch, float f, float f2, float f3, float f4) {
        drawable.draw(batch, f, f2, f3, f4);
    }

    protected void drawExpandIcon(N n, Drawable drawable, Batch batch, float f, float f2) {
        drawable.draw(batch, f, f2, drawable.getMinWidth(), drawable.getMinHeight());
    }

    protected void drawIcon(N n, Drawable drawable, Batch batch, float f, float f2) {
        drawable.draw(batch, f, f2, drawable.getMinWidth(), drawable.getMinHeight());
    }

    protected Drawable getExpandIcon(N n, float f) {
        if (n == this.overNode && Gdx.app.getType() == Application.ApplicationType.Desktop && (!this.selection.getMultiple() || (!UIUtils.ctrl() && !UIUtils.shift()))) {
            float x = screenToLocalCoordinates(tmp.set(Gdx.input.getX(), 0.0f)).x + getX();
            if (x >= 0.0f && x < f) {
                Drawable drawable = n.expanded ? this.style.minusOver : this.style.plusOver;
                Drawable drawable2 = drawable;
                if (drawable != null) {
                    return drawable2;
                }
            }
        }
        return n.expanded ? this.style.minus : this.style.plus;
    }

    @Null
    public N getNodeAt(float f) {
        this.foundNode = null;
        getNodeAt(this.rootNodes, f, getHeight());
        try {
            return this.foundNode;
        } finally {
            this.foundNode = null;
        }
    }

    private float getNodeAt(Array<N> array, float f, float f2) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            float f3 = n.height;
            float height = f2 - (n.getHeight() - f3);
            if (f >= (height - f3) - this.ySpacing && f < height) {
                this.foundNode = n;
                return -1.0f;
            }
            f2 = height - (f3 + this.ySpacing);
            if (n.expanded) {
                float nodeAt = getNodeAt(n.children, f, f2);
                f2 = nodeAt;
                if (nodeAt == -1.0f) {
                    return -1.0f;
                }
            }
        }
        return f2;
    }

    void selectNodes(Array<N> array, float f, float f2) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = array.get(i2);
            if (n.actor.getY() >= f) {
                if (n.isSelectable()) {
                    if (n.actor.getY() <= f2) {
                        this.selection.add(n);
                    }
                    if (n.expanded) {
                        selectNodes(n.children, f, f2);
                    }
                }
            } else {
                return;
            }
        }
    }

    public Selection<N> getSelection() {
        return this.selection;
    }

    @Null
    public N getSelectedNode() {
        return this.selection.first();
    }

    @Null
    public V getSelectedValue() {
        N first = this.selection.first();
        if (first == null) {
            return null;
        }
        return (V) first.getValue();
    }

    public TreeStyle getStyle() {
        return this.style;
    }

    public Array<N> getRootNodes() {
        return this.rootNodes;
    }

    @Deprecated
    public Array<N> getNodes() {
        return this.rootNodes;
    }

    public void updateRootNodes() {
        int i = this.rootNodes.size;
        for (int i2 = 0; i2 < i; i2++) {
            N n = this.rootNodes.get(i2);
            int zIndex = n.actor.getZIndex();
            if (zIndex != -1) {
                n.removeFromTree(this, zIndex);
            }
        }
        int i3 = this.rootNodes.size;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 += this.rootNodes.get(i5).addToTree(this, i4);
        }
    }

    @Null
    public N getOverNode() {
        return this.overNode;
    }

    @Null
    public V getOverValue() {
        if (this.overNode == null) {
            return null;
        }
        return (V) this.overNode.getValue();
    }

    public void setOverNode(@Null N n) {
        this.overNode = n;
    }

    public void setPadding(float f) {
        this.paddingLeft = f;
        this.paddingRight = f;
    }

    public void setPadding(float f, float f2) {
        this.paddingLeft = f;
        this.paddingRight = f2;
    }

    public void setIndentSpacing(float f) {
        this.indentSpacing = f;
    }

    public float getIndentSpacing() {
        return this.indentSpacing;
    }

    public void setYSpacing(float f) {
        this.ySpacing = f;
    }

    public float getYSpacing() {
        return this.ySpacing;
    }

    public void setIconSpacing(float f, float f2) {
        this.iconSpacingLeft = f;
        this.iconSpacingRight = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefHeight;
    }

    public void findExpandedValues(Array<V> array) {
        findExpandedValues(this.rootNodes, array);
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

    static boolean findExpandedValues(Array<? extends Node> array, Array array2) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            if (node.expanded && !findExpandedValues(node.children, array2)) {
                array2.add(node.value);
            }
        }
        return false;
    }

    @Null
    public N findNode(V v) {
        if (v == null) {
            throw new IllegalArgumentException("value cannot be null.");
        }
        return (N) findNode(this.rootNodes, v);
    }

    @Null
    static Node findNode(Array<? extends Node> array, Object obj) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            if (obj.equals(node.value)) {
                return node;
            }
        }
        int i3 = array.size;
        for (int i4 = 0; i4 < i3; i4++) {
            Node findNode = findNode(array.get(i4).children, obj);
            if (findNode != null) {
                return findNode;
            }
        }
        return null;
    }

    public void collapseAll() {
        collapseAll(this.rootNodes);
    }

    static void collapseAll(Array<? extends Node> array) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            node.setExpanded(false);
            collapseAll(node.children);
        }
    }

    public void expandAll() {
        expandAll(this.rootNodes);
    }

    static void expandAll(Array<? extends Node> array) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            array.get(i2).expandAll();
        }
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Tree$Node.class */
    public static abstract class Node<N extends Node, V, A extends Actor> {
        A actor;
        N parent;
        final Array<N> children = new Array<>(0);
        boolean selectable = true;
        boolean expanded;
        Drawable icon;
        float height;
        V value;

        public Node(A a2) {
            if (a2 == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = a2;
        }

        public Node() {
        }

        public void setExpanded(boolean z) {
            Tree<N, V> tree;
            if (z == this.expanded) {
                return;
            }
            this.expanded = z;
            if (this.children.size == 0 || (tree = getTree()) == null) {
                return;
            }
            N[] nArr = this.children.items;
            int zIndex = this.actor.getZIndex() + 1;
            if (z) {
                int i = this.children.size;
                for (int i2 = 0; i2 < i; i2++) {
                    zIndex += nArr[i2].addToTree(tree, zIndex);
                }
                return;
            }
            int i3 = this.children.size;
            for (int i4 = 0; i4 < i3; i4++) {
                nArr[i4].removeFromTree(tree, zIndex);
            }
        }

        protected int addToTree(Tree<N, V> tree, int i) {
            tree.addActorAt(i, this.actor);
            if (!this.expanded) {
                return 1;
            }
            int i2 = i + 1;
            N[] nArr = this.children.items;
            int i3 = this.children.size;
            for (int i4 = 0; i4 < i3; i4++) {
                i2 += nArr[i4].addToTree(tree, i2);
            }
            return i2 - i;
        }

        protected void removeFromTree(Tree<N, V> tree, int i) {
            tree.removeActorAt(i, true);
            if (this.expanded) {
                N[] nArr = this.children.items;
                int i2 = this.children.size;
                for (int i3 = 0; i3 < i2; i3++) {
                    nArr[i3].removeFromTree(tree, i);
                }
            }
        }

        public void add(N n) {
            insert(this.children.size, n);
        }

        public void addAll(Array<N> array) {
            int i = array.size;
            for (int i2 = 0; i2 < i; i2++) {
                insert(this.children.size, array.get(i2));
            }
        }

        public void insert(int i, N n) {
            Tree<N, V> tree;
            int zIndex;
            n.parent = this;
            this.children.insert(i, n);
            if (this.expanded && (tree = getTree()) != null) {
                if (i == 0) {
                    zIndex = this.actor.getZIndex() + 1;
                } else if (i < this.children.size - 1) {
                    zIndex = this.children.get(i + 1).actor.getZIndex();
                } else {
                    N n2 = this.children.get(i - 1);
                    zIndex = n2.actor.getZIndex() + n2.countActors();
                }
                n.addToTree(tree, zIndex);
            }
        }

        int countActors() {
            if (!this.expanded) {
                return 1;
            }
            int i = 1;
            N[] nArr = this.children.items;
            int i2 = this.children.size;
            for (int i3 = 0; i3 < i2; i3++) {
                i += nArr[i3].countActors();
            }
            return i;
        }

        public void remove() {
            Tree<N, V> tree = getTree();
            if (tree != null) {
                tree.remove(this);
            } else if (this.parent != null) {
                this.parent.remove(this);
            }
        }

        public void remove(N n) {
            Tree<N, V> tree;
            if (this.children.removeValue(n, true) && this.expanded && (tree = getTree()) != null) {
                n.removeFromTree(tree, n.actor.getZIndex());
            }
        }

        public void clearChildren() {
            Tree<N, V> tree;
            if (this.expanded && (tree = getTree()) != null) {
                int zIndex = this.actor.getZIndex() + 1;
                N[] nArr = this.children.items;
                int i = this.children.size;
                for (int i2 = 0; i2 < i; i2++) {
                    nArr[i2].removeFromTree(tree, zIndex);
                }
            }
            this.children.clear();
        }

        @Null
        public Tree<N, V> getTree() {
            Group parent = this.actor.getParent();
            if (parent instanceof Tree) {
                return (Tree) parent;
            }
            return null;
        }

        public void setActor(A a2) {
            Tree<N, V> tree;
            if (this.actor != null && (tree = getTree()) != null) {
                int zIndex = this.actor.getZIndex();
                tree.removeActorAt(zIndex, true);
                tree.addActorAt(zIndex, a2);
            }
            this.actor = a2;
        }

        public A getActor() {
            return this.actor;
        }

        public boolean isExpanded() {
            return this.expanded;
        }

        public Array<N> getChildren() {
            return this.children;
        }

        public boolean hasChildren() {
            return this.children.size > 0;
        }

        public void updateChildren() {
            Tree<N, V> tree;
            if (this.expanded && (tree = getTree()) != null) {
                N[] nArr = this.children.items;
                int i = this.children.size;
                int zIndex = this.actor.getZIndex() + 1;
                for (int i2 = 0; i2 < i; i2++) {
                    nArr[i2].removeFromTree(tree, zIndex);
                }
                for (int i3 = 0; i3 < i; i3++) {
                    zIndex += nArr[i3].addToTree(tree, zIndex);
                }
            }
        }

        @Null
        public N getParent() {
            return this.parent;
        }

        public void setIcon(@Null Drawable drawable) {
            this.icon = drawable;
        }

        @Null
        public V getValue() {
            return this.value;
        }

        public void setValue(@Null V v) {
            this.value = v;
        }

        @Null
        public Drawable getIcon() {
            return this.icon;
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
            return v.equals(this.value) ? this : (N) Tree.findNode(this.children, v);
        }

        public void collapseAll() {
            setExpanded(false);
            Tree.collapseAll(this.children);
        }

        public void expandAll() {
            setExpanded(true);
            if (this.children.size > 0) {
                Tree.expandAll(this.children);
            }
        }

        public void expandTo() {
            N n = this.parent;
            while (true) {
                N n2 = n;
                if (n2 != null) {
                    n2.setExpanded(true);
                    n = n2.parent;
                } else {
                    return;
                }
            }
        }

        public boolean isSelectable() {
            return this.selectable;
        }

        public void setSelectable(boolean z) {
            this.selectable = z;
        }

        public void findExpandedValues(Array<V> array) {
            if (!this.expanded || Tree.findExpandedValues(this.children, array)) {
                return;
            }
            array.add(this.value);
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
            return this.height;
        }

        public boolean isAscendantOf(N n) {
            if (n == null) {
                throw new IllegalArgumentException("node cannot be null.");
            }
            N n2 = n;
            while (n2 != this) {
                N n3 = n2.parent;
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
                N n3 = n2.parent;
                n2 = n3;
                if (n3 == null) {
                    return false;
                }
            }
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Tree$TreeStyle.class */
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
