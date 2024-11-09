package com.badlogic.gdx.ai.steer.utils.paths;

import com.badlogic.gdx.ai.steer.utils.Path;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/paths/LinePath.class */
public class LinePath<T extends Vector<T>> implements Path<T, LinePathParam> {
    private Array<Segment<T>> segments;
    private boolean isOpen;
    private float pathLength;
    private T nearestPointOnCurrentSegment;
    private T nearestPointOnPath;
    private T tmpB;
    private T tmpC;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.ai.steer.utils.Path
    public /* bridge */ /* synthetic */ void calculateTargetPosition(Vector vector, LinePathParam linePathParam, float f) {
        calculateTargetPosition2((LinePath<T>) vector, linePathParam, f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.ai.steer.utils.Path
    public /* bridge */ /* synthetic */ float calculateDistance(Vector vector, LinePathParam linePathParam) {
        return calculateDistance2((LinePath<T>) vector, linePathParam);
    }

    public LinePath(Array<T> array) {
        this(array, false);
    }

    public LinePath(Array<T> array, boolean z) {
        this.isOpen = z;
        createPath(array);
        this.nearestPointOnCurrentSegment = (T) array.first().cpy();
        this.nearestPointOnPath = (T) array.first().cpy();
        this.tmpB = (T) array.first().cpy();
        this.tmpC = (T) array.first().cpy();
    }

    @Override // com.badlogic.gdx.ai.steer.utils.Path
    public boolean isOpen() {
        return this.isOpen;
    }

    @Override // com.badlogic.gdx.ai.steer.utils.Path
    public float getLength() {
        return this.pathLength;
    }

    @Override // com.badlogic.gdx.ai.steer.utils.Path
    public T getStartPoint() {
        return this.segments.first().begin;
    }

    @Override // com.badlogic.gdx.ai.steer.utils.Path
    public T getEndPoint() {
        return this.segments.peek().end;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float calculatePointSegmentSquareDistance(T t, T t2, T t3, T t4) {
        t.set(t2);
        this.tmpB.set(t3);
        this.tmpC.set(t4);
        Vector sub = this.tmpB.sub(t2);
        float len2 = sub.len2();
        if (len2 != 0.0f) {
            t.mulAdd(sub, MathUtils.clamp(this.tmpC.sub(t2).dot(sub) / len2, 0.0f, 1.0f));
        }
        return t.dst2(t4);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.ai.steer.utils.Path
    public LinePathParam createParam() {
        return new LinePathParam();
    }

    /* renamed from: calculateDistance, reason: avoid collision after fix types in other method */
    public float calculateDistance2(T t, LinePathParam linePathParam) {
        float f = Float.POSITIVE_INFINITY;
        Segment<T> segment = null;
        for (int i = 0; i < this.segments.size; i++) {
            Segment<T> segment2 = this.segments.get(i);
            float calculatePointSegmentSquareDistance = calculatePointSegmentSquareDistance(this.nearestPointOnCurrentSegment, segment2.begin, segment2.end, t);
            if (calculatePointSegmentSquareDistance < f) {
                this.nearestPointOnPath.set(this.nearestPointOnCurrentSegment);
                f = calculatePointSegmentSquareDistance;
                segment = segment2;
                linePathParam.segmentIndex = i;
            }
        }
        float dst = segment.cumulativeLength - this.nearestPointOnPath.dst(segment.end);
        linePathParam.setDistance(dst);
        return dst;
    }

    /* renamed from: calculateTargetPosition, reason: avoid collision after fix types in other method */
    public void calculateTargetPosition2(T t, LinePathParam linePathParam, float f) {
        if (this.isOpen) {
            if (f < 0.0f) {
                f = 0.0f;
            } else if (f > this.pathLength) {
                f = this.pathLength;
            }
        } else if (f < 0.0f) {
            f = this.pathLength + (f % this.pathLength);
        } else if (f > this.pathLength) {
            f %= this.pathLength;
        }
        Segment<T> segment = null;
        int i = 0;
        while (true) {
            if (i >= this.segments.size) {
                break;
            }
            Segment<T> segment2 = this.segments.get(i);
            if (segment2.cumulativeLength < f) {
                i++;
            } else {
                segment = segment2;
                break;
            }
        }
        t.set(segment.begin).sub(segment.end).scl((segment.cumulativeLength - f) / segment.length).add(segment.end);
    }

    public void createPath(Array<T> array) {
        T first;
        if (array == null || array.size < 2) {
            throw new IllegalArgumentException("waypoints cannot be null and must contain at least two (2) waypoints");
        }
        this.segments = new Array<>(array.size);
        this.pathLength = 0.0f;
        T first2 = array.first();
        for (int i = 1; i <= array.size; i++) {
            T t = first2;
            if (i < array.size) {
                first = array.get(i);
            } else if (!this.isOpen) {
                first = array.first();
            } else {
                return;
            }
            first2 = first;
            Segment<T> segment = new Segment<>(t, first2);
            this.pathLength += segment.length;
            segment.cumulativeLength = this.pathLength;
            this.segments.add(segment);
        }
    }

    public Array<Segment<T>> getSegments() {
        return this.segments;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/paths/LinePath$LinePathParam.class */
    public static class LinePathParam implements Path.PathParam {
        int segmentIndex;
        float distance;

        @Override // com.badlogic.gdx.ai.steer.utils.Path.PathParam
        public float getDistance() {
            return this.distance;
        }

        @Override // com.badlogic.gdx.ai.steer.utils.Path.PathParam
        public void setDistance(float f) {
            this.distance = f;
        }

        public int getSegmentIndex() {
            return this.segmentIndex;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/paths/LinePath$Segment.class */
    public static class Segment<T extends Vector<T>> {
        T begin;
        T end;
        float length;
        float cumulativeLength;

        Segment(T t, T t2) {
            this.begin = t;
            this.end = t2;
            this.length = t.dst(t2);
        }

        public T getBegin() {
            return this.begin;
        }

        public T getEnd() {
            return this.end;
        }

        public float getLength() {
            return this.length;
        }

        public float getCumulativeLength() {
            return this.cumulativeLength;
        }
    }
}
