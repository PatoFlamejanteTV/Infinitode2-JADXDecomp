package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegraph;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/PathFinderRequest.class */
public class PathFinderRequest<N> {
    public static final int SEARCH_NEW = 0;
    public static final int SEARCH_INITIALIZED = 1;
    public static final int SEARCH_DONE = 2;
    public static final int SEARCH_FINALIZED = 3;
    public N startNode;
    public N endNode;
    public Heuristic<N> heuristic;
    public GraphPath<N> resultPath;
    public int executionFrames;
    public boolean pathFound;
    public int status;
    public boolean statusChanged;
    public Telegraph client;
    public int responseMessageCode;
    public MessageDispatcher dispatcher;

    public PathFinderRequest() {
    }

    public PathFinderRequest(N n, N n2, Heuristic<N> heuristic, GraphPath<N> graphPath) {
        this(n, n2, heuristic, graphPath, MessageManager.getInstance());
    }

    public PathFinderRequest(N n, N n2, Heuristic<N> heuristic, GraphPath<N> graphPath, MessageDispatcher messageDispatcher) {
        this.startNode = n;
        this.endNode = n2;
        this.heuristic = heuristic;
        this.resultPath = graphPath;
        this.dispatcher = messageDispatcher;
        this.executionFrames = 0;
        this.pathFound = false;
        this.status = 0;
        this.statusChanged = false;
    }

    public void changeStatus(int i) {
        this.status = i;
        this.statusChanged = true;
    }

    public boolean initializeSearch(long j) {
        return true;
    }

    public boolean search(PathFinder<N> pathFinder, long j) {
        return pathFinder.search(this, j);
    }

    public boolean finalizeSearch(long j) {
        return true;
    }
}
