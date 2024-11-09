package com.badlogic.gdx.ai.pfa;

import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/pfa/PathSmoother.class */
public class PathSmoother<N, V extends Vector<V>> {
    RaycastCollisionDetector<V> raycastCollisionDetector;
    Ray<V> ray;

    public PathSmoother(RaycastCollisionDetector<V> raycastCollisionDetector) {
        this.raycastCollisionDetector = raycastCollisionDetector;
    }

    public int smoothPath(SmoothableGraphPath<N, V> smoothableGraphPath) {
        int count = smoothableGraphPath.getCount();
        if (count <= 2) {
            return 0;
        }
        if (this.ray == null) {
            V nodePosition = smoothableGraphPath.getNodePosition(0);
            this.ray = new Ray<>(nodePosition.cpy(), nodePosition.cpy());
        }
        int i = 1;
        int i2 = 2;
        while (i2 < count) {
            this.ray.start.set(smoothableGraphPath.getNodePosition(i - 1));
            this.ray.end.set(smoothableGraphPath.getNodePosition(i2));
            if (this.raycastCollisionDetector.collides(this.ray)) {
                smoothableGraphPath.swapNodes(i, i2 - 1);
                i++;
            }
            i2++;
        }
        smoothableGraphPath.swapNodes(i, i2 - 1);
        smoothableGraphPath.truncatePath(i + 1);
        return (i2 - i) - 1;
    }

    public boolean smoothPath(PathSmootherRequest<N, V> pathSmootherRequest, long j) {
        long nanoTime = TimeUtils.nanoTime();
        SmoothableGraphPath<N, V> smoothableGraphPath = pathSmootherRequest.path;
        int count = smoothableGraphPath.getCount();
        if (count <= 2) {
            return true;
        }
        if (pathSmootherRequest.isNew) {
            pathSmootherRequest.isNew = false;
            if (this.ray == null) {
                V nodePosition = pathSmootherRequest.path.getNodePosition(0);
                this.ray = new Ray<>(nodePosition.cpy(), nodePosition.cpy());
            }
            pathSmootherRequest.outputIndex = 1;
            pathSmootherRequest.inputIndex = 2;
        }
        while (pathSmootherRequest.inputIndex < count) {
            long nanoTime2 = TimeUtils.nanoTime();
            long j2 = j - (nanoTime2 - nanoTime);
            j = j2;
            if (j2 <= 100) {
                return false;
            }
            this.ray.start.set(smoothableGraphPath.getNodePosition(pathSmootherRequest.outputIndex - 1));
            this.ray.end.set(smoothableGraphPath.getNodePosition(pathSmootherRequest.inputIndex));
            if (this.raycastCollisionDetector.collides(this.ray)) {
                smoothableGraphPath.swapNodes(pathSmootherRequest.outputIndex, pathSmootherRequest.inputIndex - 1);
                pathSmootherRequest.outputIndex++;
            }
            pathSmootherRequest.inputIndex++;
            nanoTime = nanoTime2;
        }
        smoothableGraphPath.swapNodes(pathSmootherRequest.outputIndex, pathSmootherRequest.inputIndex - 1);
        smoothableGraphPath.truncatePath(pathSmootherRequest.outputIndex + 1);
        return true;
    }
}
