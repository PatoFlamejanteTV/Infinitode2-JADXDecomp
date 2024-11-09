package com.badlogic.gdx.math;

import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.OrientedBoundingBox;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Frustum.class */
public class Frustum {
    protected static final Vector3[] clipSpacePlanePoints = {new Vector3(-1.0f, -1.0f, -1.0f), new Vector3(1.0f, -1.0f, -1.0f), new Vector3(1.0f, 1.0f, -1.0f), new Vector3(-1.0f, 1.0f, -1.0f), new Vector3(-1.0f, -1.0f, 1.0f), new Vector3(1.0f, -1.0f, 1.0f), new Vector3(1.0f, 1.0f, 1.0f), new Vector3(-1.0f, 1.0f, 1.0f)};
    protected static final float[] clipSpacePlanePointsArray = new float[24];
    private static final Vector3 tmpV;
    public final Plane[] planes = new Plane[6];
    public final Vector3[] planePoints = {new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3()};
    protected final float[] planePointsArray = new float[24];

    static {
        int i = 0;
        for (Vector3 vector3 : clipSpacePlanePoints) {
            int i2 = i;
            int i3 = i + 1;
            clipSpacePlanePointsArray[i2] = vector3.x;
            int i4 = i3 + 1;
            clipSpacePlanePointsArray[i3] = vector3.y;
            i = i4 + 1;
            clipSpacePlanePointsArray[i4] = vector3.z;
        }
        tmpV = new Vector3();
    }

    public Frustum() {
        for (int i = 0; i < 6; i++) {
            this.planes[i] = new Plane(new Vector3(), 0.0f);
        }
    }

    public void update(Matrix4 matrix4) {
        System.arraycopy(clipSpacePlanePointsArray, 0, this.planePointsArray, 0, clipSpacePlanePointsArray.length);
        Matrix4.prj(matrix4.val, this.planePointsArray, 0, 8, 3);
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            Vector3 vector3 = this.planePoints[i2];
            int i3 = i;
            int i4 = i + 1;
            vector3.x = this.planePointsArray[i3];
            int i5 = i4 + 1;
            vector3.y = this.planePointsArray[i4];
            i = i5 + 1;
            vector3.z = this.planePointsArray[i5];
        }
        this.planes[0].set(this.planePoints[1], this.planePoints[0], this.planePoints[2]);
        this.planes[1].set(this.planePoints[4], this.planePoints[5], this.planePoints[7]);
        this.planes[2].set(this.planePoints[0], this.planePoints[4], this.planePoints[3]);
        this.planes[3].set(this.planePoints[5], this.planePoints[1], this.planePoints[6]);
        this.planes[4].set(this.planePoints[2], this.planePoints[3], this.planePoints[6]);
        this.planes[5].set(this.planePoints[4], this.planePoints[0], this.planePoints[1]);
    }

    public boolean pointInFrustum(Vector3 vector3) {
        for (int i = 0; i < this.planes.length; i++) {
            if (this.planes[i].testPoint(vector3) == Plane.PlaneSide.Back) {
                return false;
            }
        }
        return true;
    }

    public boolean pointInFrustum(float f, float f2, float f3) {
        for (int i = 0; i < this.planes.length; i++) {
            if (this.planes[i].testPoint(f, f2, f3) == Plane.PlaneSide.Back) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustum(Vector3 vector3, float f) {
        for (int i = 0; i < 6; i++) {
            if ((this.planes[i].normal.x * vector3.x) + (this.planes[i].normal.y * vector3.y) + (this.planes[i].normal.z * vector3.z) < (-f) - this.planes[i].d) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustum(float f, float f2, float f3, float f4) {
        for (int i = 0; i < 6; i++) {
            if ((this.planes[i].normal.x * f) + (this.planes[i].normal.y * f2) + (this.planes[i].normal.z * f3) < (-f4) - this.planes[i].d) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustumWithoutNearFar(Vector3 vector3, float f) {
        for (int i = 2; i < 6; i++) {
            if ((this.planes[i].normal.x * vector3.x) + (this.planes[i].normal.y * vector3.y) + (this.planes[i].normal.z * vector3.z) < (-f) - this.planes[i].d) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustumWithoutNearFar(float f, float f2, float f3, float f4) {
        for (int i = 2; i < 6; i++) {
            if ((this.planes[i].normal.x * f) + (this.planes[i].normal.y * f2) + (this.planes[i].normal.z * f3) < (-f4) - this.planes[i].d) {
                return false;
            }
        }
        return true;
    }

    public boolean boundsInFrustum(BoundingBox boundingBox) {
        int length = this.planes.length;
        for (int i = 0; i < length; i++) {
            if (this.planes[i].testPoint(boundingBox.getCorner000(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(boundingBox.getCorner001(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(boundingBox.getCorner010(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(boundingBox.getCorner011(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(boundingBox.getCorner100(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(boundingBox.getCorner101(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(boundingBox.getCorner110(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(boundingBox.getCorner111(tmpV)) == Plane.PlaneSide.Back) {
                return false;
            }
        }
        return true;
    }

    public boolean boundsInFrustum(Vector3 vector3, Vector3 vector32) {
        return boundsInFrustum(vector3.x, vector3.y, vector3.z, vector32.x / 2.0f, vector32.y / 2.0f, vector32.z / 2.0f);
    }

    public boolean boundsInFrustum(float f, float f2, float f3, float f4, float f5, float f6) {
        int length = this.planes.length;
        for (int i = 0; i < length; i++) {
            if (this.planes[i].testPoint(f + f4, f2 + f5, f3 + f6) == Plane.PlaneSide.Back && this.planes[i].testPoint(f + f4, f2 + f5, f3 - f6) == Plane.PlaneSide.Back && this.planes[i].testPoint(f + f4, f2 - f5, f3 + f6) == Plane.PlaneSide.Back && this.planes[i].testPoint(f + f4, f2 - f5, f3 - f6) == Plane.PlaneSide.Back && this.planes[i].testPoint(f - f4, f2 + f5, f3 + f6) == Plane.PlaneSide.Back && this.planes[i].testPoint(f - f4, f2 + f5, f3 - f6) == Plane.PlaneSide.Back && this.planes[i].testPoint(f - f4, f2 - f5, f3 + f6) == Plane.PlaneSide.Back && this.planes[i].testPoint(f - f4, f2 - f5, f3 - f6) == Plane.PlaneSide.Back) {
                return false;
            }
        }
        return true;
    }

    public boolean boundsInFrustum(OrientedBoundingBox orientedBoundingBox) {
        int length = this.planes.length;
        for (int i = 0; i < length; i++) {
            if (this.planes[i].testPoint(orientedBoundingBox.getCorner000(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(orientedBoundingBox.getCorner001(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(orientedBoundingBox.getCorner010(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(orientedBoundingBox.getCorner011(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(orientedBoundingBox.getCorner100(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(orientedBoundingBox.getCorner101(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(orientedBoundingBox.getCorner110(tmpV)) == Plane.PlaneSide.Back && this.planes[i].testPoint(orientedBoundingBox.getCorner111(tmpV)) == Plane.PlaneSide.Back) {
                return false;
            }
        }
        return true;
    }
}
