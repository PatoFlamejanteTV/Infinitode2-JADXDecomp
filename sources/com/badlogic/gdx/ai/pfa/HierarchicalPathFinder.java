package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/HierarchicalPathFinder.class */
public class HierarchicalPathFinder<N> implements PathFinder<N> {
    private static final String TAG = "HierarchicalPathFinder";
    public static boolean DEBUG = false;
    HierarchicalGraph<N> graph;
    PathFinder<N> levelPathFinder;
    LevelPathFinderRequest<N> levelRequest = null;
    PathFinderRequestControl<N> levelRequestControl = null;

    public HierarchicalPathFinder(HierarchicalGraph<N> hierarchicalGraph, PathFinder<N> pathFinder) {
        this.graph = hierarchicalGraph;
        this.levelPathFinder = pathFinder;
    }

    @Override // com.badlogic.gdx.ai.pfa.PathFinder
    public boolean searchNodePath(N n, N n2, Heuristic<N> heuristic, GraphPath<N> graphPath) {
        N convertNodeBetweenLevels;
        if (n == n2) {
            return true;
        }
        N n3 = n2;
        int i = 0;
        int levelCount = this.graph.getLevelCount() - 1;
        while (levelCount >= 0) {
            N convertNodeBetweenLevels2 = this.graph.convertNodeBetweenLevels(0, n, levelCount);
            n3 = this.graph.convertNodeBetweenLevels(i, n3, levelCount);
            if (levelCount == 0 && (convertNodeBetweenLevels = this.graph.convertNodeBetweenLevels(0, n3, 1)) == this.graph.convertNodeBetweenLevels(0, n2, 1) && convertNodeBetweenLevels == this.graph.convertNodeBetweenLevels(0, n, 1)) {
                n3 = n2;
            }
            i = levelCount;
            levelCount--;
            if (convertNodeBetweenLevels2 != n3) {
                this.graph.setLevel(i);
                graphPath.clear();
                if (!this.levelPathFinder.searchNodePath(convertNodeBetweenLevels2, n3, heuristic, graphPath)) {
                    return false;
                }
                n3 = graphPath.get(1);
            }
        }
        return true;
    }

    @Override // com.badlogic.gdx.ai.pfa.PathFinder
    public boolean searchConnectionPath(N n, N n2, Heuristic<N> heuristic, GraphPath<Connection<N>> graphPath) {
        N convertNodeBetweenLevels;
        if (n == n2) {
            return true;
        }
        N n3 = n2;
        int i = 0;
        int levelCount = this.graph.getLevelCount() - 1;
        while (levelCount >= 0) {
            N convertNodeBetweenLevels2 = this.graph.convertNodeBetweenLevels(0, n, levelCount);
            n3 = this.graph.convertNodeBetweenLevels(i, n3, levelCount);
            if (levelCount == 0 && (convertNodeBetweenLevels = this.graph.convertNodeBetweenLevels(0, n3, 1)) == this.graph.convertNodeBetweenLevels(0, n2, 1) && convertNodeBetweenLevels == this.graph.convertNodeBetweenLevels(0, n, 1)) {
                n3 = n2;
            }
            i = levelCount;
            levelCount--;
            if (convertNodeBetweenLevels2 != n3) {
                this.graph.setLevel(i);
                graphPath.clear();
                if (!this.levelPathFinder.searchConnectionPath(convertNodeBetweenLevels2, n3, heuristic, graphPath)) {
                    return false;
                }
                n3 = graphPath.get(0).getToNode();
            }
        }
        return true;
    }

    @Override // com.badlogic.gdx.ai.pfa.PathFinder
    public boolean search(PathFinderRequest<N> pathFinderRequest, long j) {
        if (DEBUG) {
            GdxAI.getLogger().debug(TAG, "Enter interruptible HPF; request.status = " + pathFinderRequest.status);
        }
        if (this.levelRequest == null) {
            this.levelRequest = new LevelPathFinderRequest<>();
            this.levelRequestControl = new PathFinderRequestControl<>();
        }
        if (pathFinderRequest.statusChanged) {
            if (DEBUG) {
                GdxAI.getLogger().debug(TAG, "-- statusChanged");
            }
            if (pathFinderRequest.startNode == pathFinderRequest.endNode) {
                return true;
            }
            this.levelRequestControl.lastTime = TimeUtils.nanoTime();
            this.levelRequestControl.timeToRun = j;
            this.levelRequestControl.timeTolerance = 100L;
            this.levelRequestControl.server = null;
            this.levelRequestControl.pathFinder = this.levelPathFinder;
            this.levelRequest.hpf = this;
            this.levelRequest.hpfRequest = pathFinderRequest;
            this.levelRequest.status = 0;
            this.levelRequest.statusChanged = true;
            this.levelRequest.heuristic = pathFinderRequest.heuristic;
            this.levelRequest.resultPath = pathFinderRequest.resultPath;
            this.levelRequest.startNode = pathFinderRequest.startNode;
            this.levelRequest.endNode = pathFinderRequest.endNode;
            this.levelRequest.levelOfNodes = 0;
            this.levelRequest.currentLevel = this.graph.getLevelCount() - 1;
        }
        while (this.levelRequest.currentLevel >= 0) {
            if (!this.levelRequestControl.execute(this.levelRequest)) {
                return false;
            }
            this.levelRequest.executionFrames = 0;
            this.levelRequest.status = 0;
            this.levelRequest.statusChanged = true;
            if (!this.levelRequest.pathFound) {
                return true;
            }
        }
        if (DEBUG) {
            GdxAI.getLogger().debug(TAG, "-- before exit");
            return true;
        }
        return true;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/HierarchicalPathFinder$LevelPathFinderRequest.class */
    static class LevelPathFinderRequest<N> extends PathFinderRequest<N> {
        HierarchicalPathFinder<N> hpf;
        PathFinderRequest<N> hpfRequest;
        int levelOfNodes;
        int currentLevel;

        LevelPathFinderRequest() {
        }

        @Override // com.badlogic.gdx.ai.pfa.PathFinderRequest
        public boolean initializeSearch(long j) {
            N convertNodeBetweenLevels;
            this.executionFrames = 0;
            this.pathFound = false;
            this.status = 0;
            this.statusChanged = false;
            do {
                this.startNode = this.hpf.graph.convertNodeBetweenLevels(0, this.hpfRequest.startNode, this.currentLevel);
                this.endNode = this.hpf.graph.convertNodeBetweenLevels(this.levelOfNodes, this.endNode, this.currentLevel);
                if (this.currentLevel == 0 && (convertNodeBetweenLevels = this.hpf.graph.convertNodeBetweenLevels(0, this.endNode, 1)) == this.hpf.graph.convertNodeBetweenLevels(0, this.hpfRequest.endNode, 1) && convertNodeBetweenLevels == this.hpf.graph.convertNodeBetweenLevels(0, this.hpfRequest.startNode, 1)) {
                    this.endNode = this.hpfRequest.endNode;
                }
                if (HierarchicalPathFinder.DEBUG) {
                    GdxAI.getLogger().debug(HierarchicalPathFinder.TAG, "LevelPathFinder initializeSearch");
                }
                this.levelOfNodes = this.currentLevel;
                this.currentLevel--;
                if (this.startNode != this.endNode) {
                    break;
                }
            } while (this.currentLevel >= 0);
            this.hpf.graph.setLevel(this.levelOfNodes);
            this.resultPath.clear();
            return true;
        }

        @Override // com.badlogic.gdx.ai.pfa.PathFinderRequest
        public boolean search(PathFinder<N> pathFinder, long j) {
            if (HierarchicalPathFinder.DEBUG) {
                GdxAI.getLogger().debug(HierarchicalPathFinder.TAG, "LevelPathFinder search; status: " + this.status);
            }
            return super.search(pathFinder, j);
        }

        @Override // com.badlogic.gdx.ai.pfa.PathFinderRequest
        public boolean finalizeSearch(long j) {
            this.hpfRequest.pathFound = this.pathFound;
            if (this.pathFound) {
                this.endNode = this.resultPath.get(1);
            }
            if (HierarchicalPathFinder.DEBUG) {
                GdxAI.getLogger().debug(HierarchicalPathFinder.TAG, "LevelPathFinder finalizeSearch; status: " + this.status);
                return true;
            }
            return true;
        }
    }
}
