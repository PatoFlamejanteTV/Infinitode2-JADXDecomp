package com.badlogic.gdx.ai.pfa;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/PathFinder.class */
public interface PathFinder<N> {
    boolean searchConnectionPath(N n, N n2, Heuristic<N> heuristic, GraphPath<Connection<N>> graphPath);

    boolean searchNodePath(N n, N n2, Heuristic<N> heuristic, GraphPath<N> graphPath);

    boolean search(PathFinderRequest<N> pathFinderRequest, long j);
}
