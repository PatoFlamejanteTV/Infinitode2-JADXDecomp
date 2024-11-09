package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/DefaultGraphPath.class */
public class DefaultGraphPath<N> implements GraphPath<N> {
    public final Array<N> nodes;

    public DefaultGraphPath() {
        this(new Array());
    }

    public DefaultGraphPath(int i) {
        this(new Array(i));
    }

    public DefaultGraphPath(Array<N> array) {
        this.nodes = array;
    }

    @Override // com.badlogic.gdx.ai.pfa.GraphPath
    public void clear() {
        this.nodes.clear();
    }

    @Override // com.badlogic.gdx.ai.pfa.GraphPath
    public int getCount() {
        return this.nodes.size;
    }

    @Override // com.badlogic.gdx.ai.pfa.GraphPath
    public void add(N n) {
        this.nodes.add(n);
    }

    @Override // com.badlogic.gdx.ai.pfa.GraphPath
    public N get(int i) {
        return this.nodes.get(i);
    }

    @Override // com.badlogic.gdx.ai.pfa.GraphPath
    public void reverse() {
        this.nodes.reverse();
    }

    @Override // java.lang.Iterable
    public Iterator<N> iterator() {
        return this.nodes.iterator();
    }
}
