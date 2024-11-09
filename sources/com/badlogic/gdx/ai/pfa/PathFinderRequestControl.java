package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/PathFinderRequestControl.class */
public class PathFinderRequestControl<N> {
    private static final String TAG = "PathFinderRequestControl";
    public static final boolean DEBUG = false;
    Telegraph server;
    PathFinder<N> pathFinder;
    long lastTime;
    long timeToRun;
    long timeTolerance;

    public boolean execute(PathFinderRequest<N> pathFinderRequest) {
        pathFinderRequest.executionFrames++;
        do {
            if (pathFinderRequest.status == 0) {
                long nanoTime = TimeUtils.nanoTime();
                this.timeToRun -= nanoTime - this.lastTime;
                if (this.timeToRun <= this.timeTolerance || !pathFinderRequest.initializeSearch(this.timeToRun)) {
                    return false;
                }
                pathFinderRequest.changeStatus(1);
                this.lastTime = nanoTime;
            }
            if (pathFinderRequest.status == 1) {
                long nanoTime2 = TimeUtils.nanoTime();
                this.timeToRun -= nanoTime2 - this.lastTime;
                if (this.timeToRun <= this.timeTolerance || !pathFinderRequest.search(this.pathFinder, this.timeToRun)) {
                    return false;
                }
                pathFinderRequest.changeStatus(2);
                this.lastTime = nanoTime2;
            }
            if (pathFinderRequest.status == 2) {
                long nanoTime3 = TimeUtils.nanoTime();
                this.timeToRun -= nanoTime3 - this.lastTime;
                if (this.timeToRun <= this.timeTolerance || !pathFinderRequest.finalizeSearch(this.timeToRun)) {
                    return false;
                }
                pathFinderRequest.changeStatus(3);
                if (this.server != null) {
                    (pathFinderRequest.dispatcher != null ? pathFinderRequest.dispatcher : MessageManager.getInstance()).dispatchMessage(this.server, pathFinderRequest.client, pathFinderRequest.responseMessageCode, pathFinderRequest);
                }
                this.lastTime = nanoTime3;
                if (!pathFinderRequest.statusChanged) {
                    return true;
                }
            } else {
                return true;
            }
        } while (pathFinderRequest.status == 0);
        return true;
    }
}
