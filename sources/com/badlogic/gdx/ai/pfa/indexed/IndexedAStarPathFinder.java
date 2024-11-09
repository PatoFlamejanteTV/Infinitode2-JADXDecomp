package com.badlogic.gdx.ai.pfa.indexed;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.PathFinderRequest;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BinaryHeap;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/indexed/IndexedAStarPathFinder.class */
public class IndexedAStarPathFinder<N> implements PathFinder<N> {
    IndexedGraph<N> graph;
    NodeRecord<N>[] nodeRecords;
    BinaryHeap<NodeRecord<N>> openList;
    NodeRecord<N> current;
    public Metrics metrics;
    private int searchId;
    private static final int UNVISITED = 0;
    private static final int OPEN = 1;
    private static final int CLOSED = 2;

    public IndexedAStarPathFinder(IndexedGraph<N> indexedGraph) {
        this(indexedGraph, false);
    }

    public IndexedAStarPathFinder(IndexedGraph<N> indexedGraph, boolean z) {
        this.graph = indexedGraph;
        this.nodeRecords = new NodeRecord[indexedGraph.getNodeCount()];
        this.openList = new BinaryHeap<>();
        if (z) {
            this.metrics = new Metrics();
        }
    }

    @Override // com.badlogic.gdx.ai.pfa.PathFinder
    public boolean searchConnectionPath(N n, N n2, Heuristic<N> heuristic, GraphPath<Connection<N>> graphPath) {
        boolean search = search(n, n2, heuristic);
        if (search) {
            generateConnectionPath(n, graphPath);
        }
        return search;
    }

    @Override // com.badlogic.gdx.ai.pfa.PathFinder
    public boolean searchNodePath(N n, N n2, Heuristic<N> heuristic, GraphPath<N> graphPath) {
        boolean search = search(n, n2, heuristic);
        if (search) {
            generateNodePath(n, graphPath);
        }
        return search;
    }

    protected boolean search(N n, N n2, Heuristic<N> heuristic) {
        initSearch(n, n2, heuristic);
        do {
            this.current = this.openList.pop();
            this.current.category = 2;
            if (this.current.node == n2) {
                return true;
            }
            visitChildren(n2, heuristic);
        } while (this.openList.size > 0);
        return false;
    }

    @Override // com.badlogic.gdx.ai.pfa.PathFinder
    public boolean search(PathFinderRequest<N> pathFinderRequest, long j) {
        Heuristic<N> heuristic;
        long nanoTime = TimeUtils.nanoTime();
        if (pathFinderRequest.statusChanged) {
            N n = pathFinderRequest.startNode;
            N n2 = pathFinderRequest.endNode;
            heuristic = pathFinderRequest.heuristic;
            initSearch(n, n2, heuristic);
            pathFinderRequest.statusChanged = false;
        }
        do {
            long nanoTime2 = TimeUtils.nanoTime();
            long j2 = j - (nanoTime2 - nanoTime);
            j = heuristic;
            if (j2 <= 100) {
                return false;
            }
            this.current = this.openList.pop();
            this.current.category = 2;
            if (this.current.node == pathFinderRequest.endNode) {
                pathFinderRequest.pathFound = true;
                generateNodePath(pathFinderRequest.startNode, pathFinderRequest.resultPath);
                return true;
            }
            visitChildren(pathFinderRequest.endNode, pathFinderRequest.heuristic);
            nanoTime = nanoTime2;
        } while (this.openList.size > 0);
        pathFinderRequest.pathFound = false;
        return true;
    }

    protected void initSearch(N n, N n2, Heuristic<N> heuristic) {
        if (this.metrics != null) {
            this.metrics.reset();
        }
        int i = this.searchId + 1;
        this.searchId = i;
        if (i < 0) {
            this.searchId = 1;
        }
        this.openList.clear();
        NodeRecord<N> nodeRecord = getNodeRecord(n);
        nodeRecord.node = n;
        nodeRecord.connection = null;
        nodeRecord.costSoFar = 0.0f;
        addToOpenList(nodeRecord, heuristic.estimate(n, n2));
        this.current = null;
    }

    protected void visitChildren(N n, Heuristic<N> heuristic) {
        float estimate;
        Array<Connection<N>> connections = this.graph.getConnections(this.current.node);
        for (int i = 0; i < connections.size; i++) {
            if (this.metrics != null) {
                this.metrics.visitedNodes++;
            }
            Connection<N> connection = connections.get(i);
            N toNode = connection.getToNode();
            float cost = this.current.costSoFar + connection.getCost();
            NodeRecord<N> nodeRecord = getNodeRecord(toNode);
            if (nodeRecord.category != 2) {
                if (nodeRecord.category != 1) {
                    estimate = heuristic.estimate(toNode, n);
                } else if (nodeRecord.costSoFar > cost) {
                    this.openList.remove(nodeRecord);
                    estimate = nodeRecord.getEstimatedTotalCost() - nodeRecord.costSoFar;
                }
                nodeRecord.costSoFar = cost;
                nodeRecord.connection = connection;
                addToOpenList(nodeRecord, cost + estimate);
            } else if (nodeRecord.costSoFar > cost) {
                estimate = nodeRecord.getEstimatedTotalCost() - nodeRecord.costSoFar;
                nodeRecord.costSoFar = cost;
                nodeRecord.connection = connection;
                addToOpenList(nodeRecord, cost + estimate);
            }
        }
    }

    protected void generateConnectionPath(N n, GraphPath<Connection<N>> graphPath) {
        while (this.current.node != n) {
            graphPath.add(this.current.connection);
            this.current = this.nodeRecords[this.graph.getIndex(this.current.connection.getFromNode())];
        }
        graphPath.reverse();
    }

    protected void generateNodePath(N n, GraphPath<N> graphPath) {
        while (this.current.connection != null) {
            graphPath.add(this.current.node);
            this.current = this.nodeRecords[this.graph.getIndex(this.current.connection.getFromNode())];
        }
        graphPath.add(n);
        graphPath.reverse();
    }

    protected void addToOpenList(NodeRecord<N> nodeRecord, float f) {
        this.openList.add(nodeRecord, f);
        nodeRecord.category = 1;
        if (this.metrics != null) {
            this.metrics.openListAdditions++;
            this.metrics.openListPeak = Math.max(this.metrics.openListPeak, this.openList.size);
        }
    }

    protected NodeRecord<N> getNodeRecord(N n) {
        int index = this.graph.getIndex(n);
        NodeRecord<N> nodeRecord = this.nodeRecords[index];
        if (nodeRecord != null) {
            if (nodeRecord.searchId != this.searchId) {
                nodeRecord.category = 0;
                nodeRecord.searchId = this.searchId;
            }
            return nodeRecord;
        }
        NodeRecord<N>[] nodeRecordArr = this.nodeRecords;
        NodeRecord<N> nodeRecord2 = new NodeRecord<>();
        nodeRecordArr[index] = nodeRecord2;
        nodeRecord2.node = n;
        nodeRecord2.searchId = this.searchId;
        return nodeRecord2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/indexed/IndexedAStarPathFinder$NodeRecord.class */
    public static class NodeRecord<N> extends BinaryHeap.Node {
        N node;
        Connection<N> connection;
        float costSoFar;
        int category;
        int searchId;

        public NodeRecord() {
            super(0.0f);
        }

        public float getEstimatedTotalCost() {
            return getValue();
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/indexed/IndexedAStarPathFinder$Metrics.class */
    public static class Metrics {
        public int visitedNodes;
        public int openListAdditions;
        public int openListPeak;

        public void reset() {
            this.visitedNodes = 0;
            this.openListAdditions = 0;
            this.openListPeak = 0;
        }
    }
}
