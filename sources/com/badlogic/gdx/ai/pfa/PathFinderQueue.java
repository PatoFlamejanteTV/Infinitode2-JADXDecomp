package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.ai.sched.Schedulable;
import com.badlogic.gdx.ai.utils.CircularBuffer;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/PathFinderQueue.class */
public class PathFinderQueue<N> implements Telegraph, Schedulable {
    public static final long TIME_TOLERANCE = 100;
    PathFinder<N> pathFinder;
    CircularBuffer<PathFinderRequest<N>> requestQueue = new CircularBuffer<>(16);
    PathFinderRequest<N> currentRequest = null;
    PathFinderRequestControl<N> requestControl = new PathFinderRequestControl<>();

    public PathFinderQueue(PathFinder<N> pathFinder) {
        this.pathFinder = pathFinder;
    }

    @Override // com.badlogic.gdx.ai.sched.Schedulable
    public void run(long j) {
        this.requestControl.lastTime = TimeUtils.nanoTime();
        this.requestControl.timeToRun = j;
        this.requestControl.timeTolerance = 100L;
        this.requestControl.pathFinder = this.pathFinder;
        this.requestControl.server = this;
        if (this.currentRequest == null) {
            this.currentRequest = this.requestQueue.read();
        }
        while (this.currentRequest != null && this.requestControl.execute(this.currentRequest)) {
            this.currentRequest = this.requestQueue.read();
        }
    }

    @Override // com.badlogic.gdx.ai.msg.Telegraph
    public boolean handleMessage(Telegram telegram) {
        PathFinderRequest<N> pathFinderRequest = (PathFinderRequest) telegram.extraInfo;
        pathFinderRequest.client = telegram.sender;
        pathFinderRequest.status = 0;
        pathFinderRequest.statusChanged = true;
        pathFinderRequest.executionFrames = 0;
        this.requestQueue.store(pathFinderRequest);
        return true;
    }

    public int size() {
        return this.requestQueue.size();
    }
}
