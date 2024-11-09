package com.badlogic.gdx.math;

import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.OrientedBoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import java.util.Arrays;
import java.util.List;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Intersector.class */
public final class Intersector {
    private static final Vector3 v0 = new Vector3();
    private static final Vector3 v1 = new Vector3();
    private static final Vector3 v2 = new Vector3();
    private static final FloatArray floatArray = new FloatArray();
    private static final FloatArray floatArray2 = new FloatArray();
    private static final Vector2 ip = new Vector2();
    private static final Vector2 ep1 = new Vector2();
    private static final Vector2 ep2 = new Vector2();
    private static final Vector2 s = new Vector2();
    private static final Vector2 e = new Vector2();
    static Vector2 v2a = new Vector2();
    static Vector2 v2b = new Vector2();
    static Vector2 v2c = new Vector2();
    static Vector2 v2d = new Vector2();
    private static final Plane p = new Plane(new Vector3(), 0.0f);
    private static final Vector3 i = new Vector3();
    private static final Vector3 dir = new Vector3();
    private static final Vector3 start = new Vector3();
    static Vector3 best = new Vector3();
    static Vector3 tmp = new Vector3();
    static Vector3 tmp1 = new Vector3();
    static Vector3 tmp2 = new Vector3();
    static Vector3 tmp3 = new Vector3();
    static Vector3 intersection = new Vector3();

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Intersector$MinimumTranslationVector.class */
    public static class MinimumTranslationVector {
        public Vector2 normal = new Vector2();
        public float depth = 0.0f;
    }

    private Intersector() {
    }

    public static boolean isPointInTriangle(Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34) {
        v0.set(vector32).sub(vector3);
        v1.set(vector33).sub(vector3);
        v2.set(vector34).sub(vector3);
        v1.crs(v2);
        v2.crs(v0);
        if (v1.dot(v2) < 0.0f) {
            return false;
        }
        v0.crs(v2.set(vector33).sub(vector3));
        return v1.dot(v0) >= 0.0f;
    }

    public static boolean isPointInTriangle(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        return isPointInTriangle(vector2.x, vector2.y, vector22.x, vector22.y, vector23.x, vector23.y, vector24.x, vector24.y);
    }

    public static boolean isPointInTriangle(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = f - f3;
        float f10 = f2 - f4;
        boolean z = ((f5 - f3) * f10) - ((f6 - f4) * f9) > 0.0f;
        if ((((f7 - f3) * f10) - ((f8 - f4) * f9) > 0.0f) == z) {
            return false;
        }
        return (((((f7 - f5) * (f2 - f6)) - ((f8 - f6) * (f - f5))) > 0.0f ? 1 : ((((f7 - f5) * (f2 - f6)) - ((f8 - f6) * (f - f5))) == 0.0f ? 0 : -1)) > 0) == z;
    }

    public static boolean intersectSegmentPlane(Vector3 vector3, Vector3 vector32, Plane plane, Vector3 vector33) {
        Vector3 sub = v0.set(vector32).sub(vector3);
        float dot = sub.dot(plane.getNormal());
        if (dot == 0.0f) {
            return false;
        }
        float f = (-(vector3.dot(plane.getNormal()) + plane.getD())) / dot;
        if (f < 0.0f || f > 1.0f) {
            return false;
        }
        vector33.set(vector3).add(sub.scl(f));
        return true;
    }

    public static int pointLineSide(Vector2 vector2, Vector2 vector22, Vector2 vector23) {
        return (int) Math.signum(((vector22.x - vector2.x) * (vector23.y - vector2.y)) - ((vector22.y - vector2.y) * (vector23.x - vector2.x)));
    }

    public static int pointLineSide(float f, float f2, float f3, float f4, float f5, float f6) {
        return (int) Math.signum(((f3 - f) * (f6 - f2)) - ((f4 - f2) * (f5 - f)));
    }

    public static boolean isPointInPolygon(Array<Vector2> array, Vector2 vector2) {
        Vector2 peek = array.peek();
        float f = vector2.x;
        float f2 = vector2.y;
        boolean z = false;
        for (int i2 = 0; i2 < array.size; i2++) {
            Vector2 vector22 = array.get(i2);
            if (((vector22.y < f2 && peek.y >= f2) || (peek.y < f2 && vector22.y >= f2)) && vector22.x + (((f2 - vector22.y) / (peek.y - vector22.y)) * (peek.x - vector22.x)) < f) {
                z = !z;
            }
            peek = vector22;
        }
        return z;
    }

    public static boolean isPointInPolygon(float[] fArr, int i2, int i3, float f, float f2) {
        boolean z = false;
        float f3 = fArr[i2];
        float f4 = fArr[i2 + 1];
        float f5 = f4;
        int i4 = i2 + 3;
        int i5 = i2 + i3;
        while (i4 < i5) {
            float f6 = fArr[i4];
            if ((f6 < f2 && f5 >= f2) || (f5 < f2 && f6 >= f2)) {
                float f7 = fArr[i4 - 1];
                if (f7 + (((f2 - f6) / (f5 - f6)) * (fArr[i4 - 3] - f7)) < f) {
                    z = !z;
                }
            }
            f5 = f6;
            i4 += 2;
        }
        if (((f4 < f2 && f5 >= f2) || (f5 < f2 && f4 >= f2)) && f3 + (((f2 - f4) / (f5 - f4)) * (fArr[i4 - 3] - f3)) < f) {
            z = !z;
        }
        return z;
    }

    public static boolean intersectPolygons(Polygon polygon, Polygon polygon2, Polygon polygon3) {
        if (polygon.getVertices().length == 0 || polygon2.getVertices().length == 0) {
            return false;
        }
        Vector2 vector2 = ip;
        Vector2 vector22 = ep1;
        Vector2 vector23 = ep2;
        Vector2 vector24 = s;
        Vector2 vector25 = e;
        FloatArray floatArray3 = floatArray;
        FloatArray floatArray4 = floatArray2;
        floatArray3.clear();
        floatArray4.clear();
        floatArray4.addAll(polygon.getTransformedVertices());
        float[] transformedVertices = polygon2.getTransformedVertices();
        int length = transformedVertices.length - 2;
        for (int i2 = 0; i2 <= length; i2 += 2) {
            vector22.set(transformedVertices[i2], transformedVertices[i2 + 1]);
            if (i2 < length) {
                vector23.set(transformedVertices[i2 + 2], transformedVertices[i2 + 3]);
            } else {
                vector23.set(transformedVertices[0], transformedVertices[1]);
            }
            if (floatArray4.size == 0) {
                return false;
            }
            vector24.set(floatArray4.get(floatArray4.size - 2), floatArray4.get(floatArray4.size - 1));
            for (int i3 = 0; i3 < floatArray4.size; i3 += 2) {
                vector25.set(floatArray4.get(i3), floatArray4.get(i3 + 1));
                boolean z = pointLineSide(vector23, vector22, vector24) > 0;
                if (pointLineSide(vector23, vector22, vector25) > 0) {
                    if (!z) {
                        intersectLines(vector24, vector25, vector22, vector23, vector2);
                        if (floatArray3.size < 2 || floatArray3.get(floatArray3.size - 2) != vector2.x || floatArray3.get(floatArray3.size - 1) != vector2.y) {
                            floatArray3.add(vector2.x);
                            floatArray3.add(vector2.y);
                        }
                    }
                    floatArray3.add(vector25.x);
                    floatArray3.add(vector25.y);
                } else if (z) {
                    intersectLines(vector24, vector25, vector22, vector23, vector2);
                    floatArray3.add(vector2.x);
                    floatArray3.add(vector2.y);
                }
                vector24.set(vector25.x, vector25.y);
            }
            floatArray4.clear();
            floatArray4.addAll(floatArray3);
            floatArray3.clear();
        }
        if (floatArray4.size >= 6) {
            if (polygon3 != null) {
                if (polygon3.getVertices().length == floatArray4.size) {
                    System.arraycopy(floatArray4.items, 0, polygon3.getVertices(), 0, floatArray4.size);
                    return true;
                }
                polygon3.setVertices(floatArray4.toArray());
                return true;
            }
            return true;
        }
        return false;
    }

    public static boolean intersectPolygons(FloatArray floatArray3, FloatArray floatArray4) {
        if (isPointInPolygon(floatArray3.items, 0, floatArray3.size, floatArray4.items[0], floatArray4.items[1]) || isPointInPolygon(floatArray4.items, 0, floatArray4.size, floatArray3.items[0], floatArray3.items[1])) {
            return true;
        }
        return intersectPolygonEdges(floatArray3, floatArray4);
    }

    public static boolean intersectPolygonEdges(FloatArray floatArray3, FloatArray floatArray4) {
        int i2 = floatArray3.size - 2;
        int i3 = floatArray4.size - 2;
        float[] fArr = floatArray3.items;
        float[] fArr2 = floatArray4.items;
        float f = fArr[i2];
        float f2 = fArr[i2 + 1];
        for (int i4 = 0; i4 <= i2; i4 += 2) {
            float f3 = fArr[i4];
            float f4 = fArr[i4 + 1];
            float f5 = fArr2[i3];
            float f6 = fArr2[i3 + 1];
            for (int i5 = 0; i5 <= i3; i5 += 2) {
                float f7 = fArr2[i5];
                float f8 = fArr2[i5 + 1];
                if (intersectSegments(f, f2, f3, f4, f5, f6, f7, f8, null)) {
                    return true;
                }
                f5 = f7;
                f6 = f8;
            }
            f = f3;
            f2 = f4;
        }
        return false;
    }

    public static float distanceLinePoint(float f, float f2, float f3, float f4, float f5, float f6) {
        return Math.abs(((f5 - f) * (f4 - f2)) - ((f6 - f2) * (f3 - f))) / Vector2.len(f3 - f, f4 - f2);
    }

    public static float distanceSegmentPoint(float f, float f2, float f3, float f4, float f5, float f6) {
        return nearestSegmentPoint(f, f2, f3, f4, f5, f6, v2a).dst(f5, f6);
    }

    public static float distanceSegmentPoint(Vector2 vector2, Vector2 vector22, Vector2 vector23) {
        return nearestSegmentPoint(vector2, vector22, vector23, v2a).dst(vector23);
    }

    public static Vector2 nearestSegmentPoint(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        float dst2 = vector2.dst2(vector22);
        if (dst2 == 0.0f) {
            return vector24.set(vector2);
        }
        float f = (((vector23.x - vector2.x) * (vector22.x - vector2.x)) + ((vector23.y - vector2.y) * (vector22.y - vector2.y))) / dst2;
        return f <= 0.0f ? vector24.set(vector2) : f >= 1.0f ? vector24.set(vector22) : vector24.set(vector2.x + (f * (vector22.x - vector2.x)), vector2.y + (f * (vector22.y - vector2.y)));
    }

    public static Vector2 nearestSegmentPoint(float f, float f2, float f3, float f4, float f5, float f6, Vector2 vector2) {
        float f7 = f3 - f;
        float f8 = f4 - f2;
        float f9 = (f7 * f7) + (f8 * f8);
        if (f9 == 0.0f) {
            return vector2.set(f, f2);
        }
        float f10 = (((f5 - f) * f7) + ((f6 - f2) * f8)) / f9;
        return f10 <= 0.0f ? vector2.set(f, f2) : f10 >= 1.0f ? vector2.set(f3, f4) : vector2.set(f + (f10 * f7), f2 + (f10 * f8));
    }

    public static boolean intersectSegmentCircle(Vector2 vector2, Vector2 vector22, Vector2 vector23, float f) {
        tmp.set(vector22.x - vector2.x, vector22.y - vector2.y, 0.0f);
        tmp1.set(vector23.x - vector2.x, vector23.y - vector2.y, 0.0f);
        float len = tmp.len();
        float dot = tmp1.dot(tmp.nor());
        if (dot <= 0.0f) {
            tmp2.set(vector2.x, vector2.y, 0.0f);
        } else if (dot < len) {
            tmp3.set(tmp.scl(dot));
            tmp2.set(tmp3.x + vector2.x, tmp3.y + vector2.y, 0.0f);
        } else {
            tmp2.set(vector22.x, vector22.y, 0.0f);
        }
        float f2 = vector23.x - tmp2.x;
        float f3 = vector23.y - tmp2.y;
        return (f2 * f2) + (f3 * f3) <= f;
    }

    public static boolean intersectSegmentCircle(Vector2 vector2, Vector2 vector22, Circle circle, MinimumTranslationVector minimumTranslationVector) {
        v2a.set(vector22).sub(vector2);
        v2b.set(circle.x - vector2.x, circle.y - vector2.y);
        float len = v2a.len();
        float dot = v2b.dot(v2a.nor());
        if (dot <= 0.0f) {
            v2c.set(vector2);
        } else if (dot < len) {
            v2d.set(v2a.scl(dot));
            v2c.set(v2d).add(vector2);
        } else {
            v2c.set(vector22);
        }
        v2a.set(v2c.x - circle.x, v2c.y - circle.y);
        if (minimumTranslationVector != null) {
            if (v2a.equals(Vector2.Zero)) {
                v2d.set(vector22.y - vector2.y, vector2.x - vector22.x);
                minimumTranslationVector.normal.set(v2d).nor();
                minimumTranslationVector.depth = circle.radius;
            } else {
                minimumTranslationVector.normal.set(v2a).nor();
                minimumTranslationVector.depth = circle.radius - v2a.len();
            }
        }
        return v2a.len2() <= circle.radius * circle.radius;
    }

    public static boolean intersectFrustumBounds(Frustum frustum, BoundingBox boundingBox) {
        if (frustum.pointInFrustum(boundingBox.getCorner000(tmp)) || frustum.pointInFrustum(boundingBox.getCorner001(tmp)) || frustum.pointInFrustum(boundingBox.getCorner010(tmp)) || frustum.pointInFrustum(boundingBox.getCorner011(tmp)) || frustum.pointInFrustum(boundingBox.getCorner100(tmp)) || frustum.pointInFrustum(boundingBox.getCorner101(tmp)) || frustum.pointInFrustum(boundingBox.getCorner110(tmp)) || frustum.pointInFrustum(boundingBox.getCorner111(tmp))) {
            return true;
        }
        boolean z = false;
        for (Vector3 vector3 : frustum.planePoints) {
            z |= boundingBox.contains(vector3);
        }
        return z;
    }

    public static boolean intersectFrustumBounds(Frustum frustum, OrientedBoundingBox orientedBoundingBox) {
        boolean z = false;
        for (Vector3 vector3 : orientedBoundingBox.getVertices()) {
            z |= frustum.pointInFrustum(vector3);
        }
        if (z) {
            return true;
        }
        boolean z2 = false;
        for (Vector3 vector32 : frustum.planePoints) {
            z2 |= orientedBoundingBox.contains(vector32);
        }
        return z2;
    }

    public static float intersectRayRay(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        float f = vector23.x - vector2.x;
        float f2 = vector23.y - vector2.y;
        float f3 = (vector22.x * vector24.y) - (vector22.y * vector24.x);
        if (f3 != 0.0f) {
            return (f * (vector24.y / f3)) - (f2 * (vector24.x / f3));
        }
        return Float.POSITIVE_INFINITY;
    }

    public static boolean intersectRayPlane(Ray ray, Plane plane, Vector3 vector3) {
        float dot = ray.direction.dot(plane.getNormal());
        if (dot != 0.0f) {
            float f = (-(ray.origin.dot(plane.getNormal()) + plane.getD())) / dot;
            if (f < 0.0f) {
                return false;
            }
            if (vector3 != null) {
                vector3.set(ray.origin).add(v0.set(ray.direction).scl(f));
                return true;
            }
            return true;
        }
        if (plane.testPoint(ray.origin) == Plane.PlaneSide.OnPlane) {
            if (vector3 != null) {
                vector3.set(ray.origin);
                return true;
            }
            return true;
        }
        return false;
    }

    public static float intersectLinePlane(float f, float f2, float f3, float f4, float f5, float f6, Plane plane, Vector3 vector3) {
        Vector3 sub = tmp.set(f4, f5, f6).sub(f, f2, f3);
        Vector3 vector32 = tmp2.set(f, f2, f3);
        float dot = sub.dot(plane.getNormal());
        if (dot != 0.0f) {
            float f7 = (-(vector32.dot(plane.getNormal()) + plane.getD())) / dot;
            if (vector3 != null) {
                vector3.set(vector32).add(sub.scl(f7));
            }
            return f7;
        }
        if (plane.testPoint(vector32) == Plane.PlaneSide.OnPlane) {
            if (vector3 != null) {
                vector3.set(vector32);
                return 0.0f;
            }
            return 0.0f;
        }
        return -1.0f;
    }

    public static boolean intersectPlanes(Plane plane, Plane plane2, Plane plane3, Vector3 vector3) {
        tmp1.set(plane.normal).crs(plane2.normal);
        tmp2.set(plane2.normal).crs(plane3.normal);
        tmp3.set(plane3.normal).crs(plane.normal);
        float f = -plane.normal.dot(tmp2);
        if (Math.abs(f) < 1.0E-6f) {
            return false;
        }
        tmp1.scl(plane3.d);
        tmp2.scl(plane.d);
        tmp3.scl(plane2.d);
        vector3.set(tmp1.x + tmp2.x + tmp3.x, tmp1.y + tmp2.y + tmp3.y, tmp1.z + tmp2.z + tmp3.z);
        vector3.scl(1.0f / f);
        return true;
    }

    public static boolean intersectRayTriangle(Ray ray, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34) {
        Vector3 sub = v0.set(vector32).sub(vector3);
        Vector3 sub2 = v1.set(vector33).sub(vector3);
        Vector3 crs = v2.set(ray.direction).crs(sub2);
        float dot = sub.dot(crs);
        if (!MathUtils.isZero(dot)) {
            float f = 1.0f / dot;
            Vector3 sub3 = i.set(ray.origin).sub(vector3);
            float dot2 = sub3.dot(crs) * f;
            if (dot2 < 0.0f || dot2 > 1.0f) {
                return false;
            }
            Vector3 crs2 = sub3.crs(sub);
            float dot3 = ray.direction.dot(crs2) * f;
            if (dot3 < 0.0f || dot2 + dot3 > 1.0f) {
                return false;
            }
            float dot4 = sub2.dot(crs2) * f;
            if (dot4 < 0.0f) {
                return false;
            }
            if (vector34 == null) {
                return true;
            }
            if (dot4 <= 1.0E-6f) {
                vector34.set(ray.origin);
                return true;
            }
            ray.getEndPoint(vector34, dot4);
            return true;
        }
        p.set(vector3, vector32, vector33);
        if (p.testPoint(ray.origin) == Plane.PlaneSide.OnPlane && isPointInTriangle(ray.origin, vector3, vector32, vector33)) {
            if (vector34 != null) {
                vector34.set(ray.origin);
                return true;
            }
            return true;
        }
        return false;
    }

    public static boolean intersectRaySphere(Ray ray, Vector3 vector3, float f, Vector3 vector32) {
        float dot = ray.direction.dot(vector3.x - ray.origin.x, vector3.y - ray.origin.y, vector3.z - ray.origin.z);
        if (dot < 0.0f) {
            return false;
        }
        if (vector3.dst2(ray.origin.x + (ray.direction.x * dot), ray.origin.y + (ray.direction.y * dot), ray.origin.z + (ray.direction.z * dot)) > f * f) {
            return false;
        }
        if (vector32 != null) {
            vector32.set(ray.direction).scl(dot - ((float) Math.sqrt(r0 - r0))).add(ray.origin);
            return true;
        }
        return true;
    }

    public static boolean intersectRayBounds(Ray ray, BoundingBox boundingBox, Vector3 vector3) {
        if (boundingBox.contains(ray.origin)) {
            if (vector3 != null) {
                vector3.set(ray.origin);
                return true;
            }
            return true;
        }
        float f = 0.0f;
        boolean z = false;
        if (ray.origin.x <= boundingBox.min.x && ray.direction.x > 0.0f) {
            float f2 = (boundingBox.min.x - ray.origin.x) / ray.direction.x;
            if (f2 >= 0.0f) {
                v2.set(ray.direction).scl(f2).add(ray.origin);
                if (v2.y >= boundingBox.min.y && v2.y <= boundingBox.max.y && v2.z >= boundingBox.min.z && v2.z <= boundingBox.max.z) {
                    z = true;
                    f = f2;
                }
            }
        }
        if (ray.origin.x >= boundingBox.max.x && ray.direction.x < 0.0f) {
            float f3 = (boundingBox.max.x - ray.origin.x) / ray.direction.x;
            if (f3 >= 0.0f) {
                v2.set(ray.direction).scl(f3).add(ray.origin);
                if (v2.y >= boundingBox.min.y && v2.y <= boundingBox.max.y && v2.z >= boundingBox.min.z && v2.z <= boundingBox.max.z && (!z || f3 < f)) {
                    z = true;
                    f = f3;
                }
            }
        }
        if (ray.origin.y <= boundingBox.min.y && ray.direction.y > 0.0f) {
            float f4 = (boundingBox.min.y - ray.origin.y) / ray.direction.y;
            if (f4 >= 0.0f) {
                v2.set(ray.direction).scl(f4).add(ray.origin);
                if (v2.x >= boundingBox.min.x && v2.x <= boundingBox.max.x && v2.z >= boundingBox.min.z && v2.z <= boundingBox.max.z && (!z || f4 < f)) {
                    z = true;
                    f = f4;
                }
            }
        }
        if (ray.origin.y >= boundingBox.max.y && ray.direction.y < 0.0f) {
            float f5 = (boundingBox.max.y - ray.origin.y) / ray.direction.y;
            if (f5 >= 0.0f) {
                v2.set(ray.direction).scl(f5).add(ray.origin);
                if (v2.x >= boundingBox.min.x && v2.x <= boundingBox.max.x && v2.z >= boundingBox.min.z && v2.z <= boundingBox.max.z && (!z || f5 < f)) {
                    z = true;
                    f = f5;
                }
            }
        }
        if (ray.origin.z <= boundingBox.min.z && ray.direction.z > 0.0f) {
            float f6 = (boundingBox.min.z - ray.origin.z) / ray.direction.z;
            if (f6 >= 0.0f) {
                v2.set(ray.direction).scl(f6).add(ray.origin);
                if (v2.x >= boundingBox.min.x && v2.x <= boundingBox.max.x && v2.y >= boundingBox.min.y && v2.y <= boundingBox.max.y && (!z || f6 < f)) {
                    z = true;
                    f = f6;
                }
            }
        }
        if (ray.origin.z >= boundingBox.max.z && ray.direction.z < 0.0f) {
            float f7 = (boundingBox.max.z - ray.origin.z) / ray.direction.z;
            if (f7 >= 0.0f) {
                v2.set(ray.direction).scl(f7).add(ray.origin);
                if (v2.x >= boundingBox.min.x && v2.x <= boundingBox.max.x && v2.y >= boundingBox.min.y && v2.y <= boundingBox.max.y && (!z || f7 < f)) {
                    z = true;
                    f = f7;
                }
            }
        }
        if (z && vector3 != null) {
            vector3.set(ray.direction).scl(f).add(ray.origin);
            if (vector3.x < boundingBox.min.x) {
                vector3.x = boundingBox.min.x;
            } else if (vector3.x > boundingBox.max.x) {
                vector3.x = boundingBox.max.x;
            }
            if (vector3.y < boundingBox.min.y) {
                vector3.y = boundingBox.min.y;
            } else if (vector3.y > boundingBox.max.y) {
                vector3.y = boundingBox.max.y;
            }
            if (vector3.z < boundingBox.min.z) {
                vector3.z = boundingBox.min.z;
            } else if (vector3.z > boundingBox.max.z) {
                vector3.z = boundingBox.max.z;
            }
        }
        return z;
    }

    public static boolean intersectRayBoundsFast(Ray ray, BoundingBox boundingBox) {
        return intersectRayBoundsFast(ray, boundingBox.getCenter(tmp1), boundingBox.getDimensions(tmp2));
    }

    public static boolean intersectRayBoundsFast(Ray ray, Vector3 vector3, Vector3 vector32) {
        float f = 1.0f / ray.direction.x;
        float f2 = 1.0f / ray.direction.y;
        float f3 = 1.0f / ray.direction.z;
        float f4 = ((vector3.x - (vector32.x * 0.5f)) - ray.origin.x) * f;
        float f5 = ((vector3.x + (vector32.x * 0.5f)) - ray.origin.x) * f;
        if (f4 > f5) {
            f4 = f5;
            f5 = f4;
        }
        float f6 = ((vector3.y - (vector32.y * 0.5f)) - ray.origin.y) * f2;
        float f7 = ((vector3.y + (vector32.y * 0.5f)) - ray.origin.y) * f2;
        if (f6 > f7) {
            f6 = f7;
            f7 = f6;
        }
        float f8 = ((vector3.z - (vector32.z * 0.5f)) - ray.origin.z) * f3;
        float f9 = ((vector3.z + (vector32.z * 0.5f)) - ray.origin.z) * f3;
        if (f8 > f9) {
            f8 = f9;
            f9 = f8;
        }
        float max = Math.max(Math.max(f4, f6), f8);
        float min = Math.min(Math.min(f5, f7), f9);
        return min >= 0.0f && min >= max;
    }

    public static boolean intersectRayOrientedBoundsFast(Ray ray, OrientedBoundingBox orientedBoundingBox) {
        return intersectRayOrientedBounds(ray, orientedBoundingBox, null);
    }

    public static boolean intersectRayOrientedBoundsFast(Ray ray, BoundingBox boundingBox, Matrix4 matrix4) {
        return intersectRayOrientedBounds(ray, boundingBox, matrix4, null);
    }

    public static boolean intersectRayOrientedBounds(Ray ray, OrientedBoundingBox orientedBoundingBox, Vector3 vector3) {
        return intersectRayOrientedBounds(ray, orientedBoundingBox.getBounds(), orientedBoundingBox.getTransform(), vector3);
    }

    public static boolean intersectRayOrientedBounds(Ray ray, BoundingBox boundingBox, Matrix4 matrix4, Vector3 vector3) {
        float f = 0.0f;
        float f2 = Float.MAX_VALUE;
        Vector3 sub = matrix4.getTranslation(tmp).sub(ray.origin);
        Vector3 vector32 = tmp1;
        tmp1.set(matrix4.val[0], matrix4.val[1], matrix4.val[2]);
        float dot = vector32.dot(sub);
        float dot2 = ray.direction.dot(vector32);
        if (Math.abs(dot2) > 1.0E-6f) {
            float f3 = (dot + boundingBox.min.x) / dot2;
            float f4 = (dot + boundingBox.max.x) / dot2;
            if (f3 > f4) {
                f3 = f4;
                f4 = f3;
            }
            if (f4 < Float.MAX_VALUE) {
                f2 = f4;
            }
            if (f3 > 0.0f) {
                f = f3;
            }
            if (f2 < f) {
                return false;
            }
        } else if ((-dot) + boundingBox.min.x > 0.0f || (-dot) + boundingBox.max.x < 0.0f) {
            return false;
        }
        Vector3 vector33 = tmp2;
        tmp2.set(matrix4.val[4], matrix4.val[5], matrix4.val[6]);
        float dot3 = vector33.dot(sub);
        float dot4 = ray.direction.dot(vector33);
        if (Math.abs(dot4) > 1.0E-6f) {
            float f5 = (dot3 + boundingBox.min.y) / dot4;
            float f6 = (dot3 + boundingBox.max.y) / dot4;
            if (f5 > f6) {
                f5 = f6;
                f6 = f5;
            }
            if (f6 < f2) {
                f2 = f6;
            }
            if (f5 > f) {
                f = f5;
            }
            if (f > f2) {
                return false;
            }
        } else if ((-dot3) + boundingBox.min.y > 0.0f || (-dot3) + boundingBox.max.y < 0.0f) {
            return false;
        }
        Vector3 vector34 = tmp3;
        tmp3.set(matrix4.val[8], matrix4.val[9], matrix4.val[10]);
        float dot5 = vector34.dot(sub);
        float dot6 = ray.direction.dot(vector34);
        if (Math.abs(dot6) > 1.0E-6f) {
            float f7 = (dot5 + boundingBox.min.z) / dot6;
            float f8 = (dot5 + boundingBox.max.z) / dot6;
            if (f7 > f8) {
                f7 = f8;
                f8 = f7;
            }
            if (f8 < f2) {
                f2 = f8;
            }
            if (f7 > f) {
                f = f7;
            }
            if (f > f2) {
                return false;
            }
        } else if ((-dot5) + boundingBox.min.z > 0.0f || (-dot5) + boundingBox.max.z < 0.0f) {
            return false;
        }
        if (vector3 != null) {
            ray.getEndPoint(vector3, f);
            return true;
        }
        return true;
    }

    public static boolean intersectRayTriangles(Ray ray, float[] fArr, Vector3 vector3) {
        float f = Float.MAX_VALUE;
        boolean z = false;
        if (fArr.length % 9 != 0) {
            throw new RuntimeException("triangles array size is not a multiple of 9");
        }
        for (int i2 = 0; i2 < fArr.length; i2 += 9) {
            if (intersectRayTriangle(ray, tmp1.set(fArr[i2], fArr[i2 + 1], fArr[i2 + 2]), tmp2.set(fArr[i2 + 3], fArr[i2 + 4], fArr[i2 + 5]), tmp3.set(fArr[i2 + 6], fArr[i2 + 7], fArr[i2 + 8]), tmp)) {
                float dst2 = ray.origin.dst2(tmp);
                if (dst2 < f) {
                    f = dst2;
                    best.set(tmp);
                    z = true;
                }
            }
        }
        if (!z) {
            return false;
        }
        if (vector3 != null) {
            vector3.set(best);
            return true;
        }
        return true;
    }

    public static boolean intersectRayTriangles(Ray ray, float[] fArr, short[] sArr, int i2, Vector3 vector3) {
        float f = Float.MAX_VALUE;
        boolean z = false;
        if (sArr.length % 3 != 0) {
            throw new RuntimeException("triangle list size is not a multiple of 3");
        }
        for (int i3 = 0; i3 < sArr.length; i3 += 3) {
            int i4 = sArr[i3] * i2;
            int i5 = sArr[i3 + 1] * i2;
            int i6 = sArr[i3 + 2] * i2;
            if (intersectRayTriangle(ray, tmp1.set(fArr[i4], fArr[i4 + 1], fArr[i4 + 2]), tmp2.set(fArr[i5], fArr[i5 + 1], fArr[i5 + 2]), tmp3.set(fArr[i6], fArr[i6 + 1], fArr[i6 + 2]), tmp)) {
                float dst2 = ray.origin.dst2(tmp);
                if (dst2 < f) {
                    f = dst2;
                    best.set(tmp);
                    z = true;
                }
            }
        }
        if (!z) {
            return false;
        }
        if (vector3 != null) {
            vector3.set(best);
            return true;
        }
        return true;
    }

    public static boolean intersectRayTriangles(Ray ray, List<Vector3> list, Vector3 vector3) {
        float f = Float.MAX_VALUE;
        boolean z = false;
        if (list.size() % 3 != 0) {
            throw new RuntimeException("triangle list size is not a multiple of 3");
        }
        for (int i2 = 0; i2 < list.size(); i2 += 3) {
            if (intersectRayTriangle(ray, list.get(i2), list.get(i2 + 1), list.get(i2 + 2), tmp)) {
                float dst2 = ray.origin.dst2(tmp);
                if (dst2 < f) {
                    f = dst2;
                    best.set(tmp);
                    z = true;
                }
            }
        }
        if (!z) {
            return false;
        }
        if (vector3 != null) {
            vector3.set(best);
            return true;
        }
        return true;
    }

    public static boolean intersectBoundsPlaneFast(BoundingBox boundingBox, Plane plane) {
        return intersectBoundsPlaneFast(boundingBox.getCenter(tmp1), boundingBox.getDimensions(tmp2).scl(0.5f), plane.normal, plane.d);
    }

    public static boolean intersectBoundsPlaneFast(Vector3 vector3, Vector3 vector32, Vector3 vector33, float f) {
        return Math.abs(vector33.dot(vector3) - f) <= ((vector32.x * Math.abs(vector33.x)) + (vector32.y * Math.abs(vector33.y))) + (vector32.z * Math.abs(vector33.z));
    }

    public static boolean intersectLines(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24, Vector2 vector25) {
        return intersectLines(vector2.x, vector2.y, vector22.x, vector22.y, vector23.x, vector23.y, vector24.x, vector24.y, vector25);
    }

    public static boolean intersectLines(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Vector2 vector2) {
        float f9 = ((f8 - f6) * (f3 - f)) - ((f7 - f5) * (f4 - f2));
        if (f9 == 0.0f) {
            return false;
        }
        if (vector2 != null) {
            float f10 = (((f7 - f5) * (f2 - f6)) - ((f8 - f6) * (f - f5))) / f9;
            vector2.set(f + ((f3 - f) * f10), f2 + ((f4 - f2) * f10));
            return true;
        }
        return true;
    }

    public static boolean intersectLinePolygon(Vector2 vector2, Vector2 vector22, Polygon polygon) {
        float[] transformedVertices = polygon.getTransformedVertices();
        float f = vector2.x;
        float f2 = vector2.y;
        float f3 = vector22.x;
        float f4 = vector22.y;
        int length = transformedVertices.length;
        float f5 = transformedVertices[length - 2];
        float f6 = transformedVertices[length - 1];
        for (int i2 = 0; i2 < length; i2 += 2) {
            float f7 = transformedVertices[i2];
            float f8 = transformedVertices[i2 + 1];
            float f9 = ((f8 - f6) * (f3 - f)) - ((f7 - f5) * (f4 - f2));
            if (f9 != 0.0f) {
                float f10 = (((f7 - f5) * (f2 - f6)) - ((f8 - f6) * (f - f5))) / f9;
                if (f10 >= 0.0f && f10 <= 1.0f) {
                    return true;
                }
            }
            f5 = f7;
            f6 = f8;
        }
        return false;
    }

    public static boolean intersectRectangles(Rectangle rectangle, Rectangle rectangle2, Rectangle rectangle3) {
        if (rectangle.overlaps(rectangle2)) {
            rectangle3.x = Math.max(rectangle.x, rectangle2.x);
            rectangle3.width = Math.min(rectangle.x + rectangle.width, rectangle2.x + rectangle2.width) - rectangle3.x;
            rectangle3.y = Math.max(rectangle.y, rectangle2.y);
            rectangle3.height = Math.min(rectangle.y + rectangle.height, rectangle2.y + rectangle2.height) - rectangle3.y;
            return true;
        }
        return false;
    }

    public static boolean intersectSegmentRectangle(float f, float f2, float f3, float f4, Rectangle rectangle) {
        float f5 = rectangle.x + rectangle.width;
        float f6 = rectangle.y + rectangle.height;
        if (intersectSegments(f, f2, f3, f4, rectangle.x, rectangle.y, rectangle.x, f6, null) || intersectSegments(f, f2, f3, f4, rectangle.x, rectangle.y, f5, rectangle.y, null) || intersectSegments(f, f2, f3, f4, f5, rectangle.y, f5, f6, null) || intersectSegments(f, f2, f3, f4, rectangle.x, f6, f5, f6, null)) {
            return true;
        }
        return rectangle.contains(f, f2);
    }

    public static boolean intersectSegmentRectangle(Vector2 vector2, Vector2 vector22, Rectangle rectangle) {
        return intersectSegmentRectangle(vector2.x, vector2.y, vector22.x, vector22.y, rectangle);
    }

    public static boolean intersectSegmentPolygon(Vector2 vector2, Vector2 vector22, Polygon polygon) {
        float[] transformedVertices = polygon.getTransformedVertices();
        float f = vector2.x;
        float f2 = vector2.y;
        float f3 = vector22.x;
        float f4 = vector22.y;
        int length = transformedVertices.length;
        float f5 = transformedVertices[length - 2];
        float f6 = transformedVertices[length - 1];
        for (int i2 = 0; i2 < length; i2 += 2) {
            float f7 = transformedVertices[i2];
            float f8 = transformedVertices[i2 + 1];
            float f9 = ((f8 - f6) * (f3 - f)) - ((f7 - f5) * (f4 - f2));
            if (f9 != 0.0f) {
                float f10 = f2 - f6;
                float f11 = f - f5;
                float f12 = (((f7 - f5) * f10) - ((f8 - f6) * f11)) / f9;
                if (f12 >= 0.0f && f12 <= 1.0f) {
                    float f13 = (((f3 - f) * f10) - ((f4 - f2) * f11)) / f9;
                    if (f13 >= 0.0f && f13 <= 1.0f) {
                        return true;
                    }
                }
            }
            f5 = f7;
            f6 = f8;
        }
        return false;
    }

    public static boolean intersectSegments(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24, Vector2 vector25) {
        return intersectSegments(vector2.x, vector2.y, vector22.x, vector22.y, vector23.x, vector23.y, vector24.x, vector24.y, vector25);
    }

    public static boolean intersectSegments(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Vector2 vector2) {
        float f9 = ((f8 - f6) * (f3 - f)) - ((f7 - f5) * (f4 - f2));
        if (f9 == 0.0f) {
            return false;
        }
        float f10 = f2 - f6;
        float f11 = f - f5;
        float f12 = (((f7 - f5) * f10) - ((f8 - f6) * f11)) / f9;
        if (f12 < 0.0f || f12 > 1.0f) {
            return false;
        }
        float f13 = (((f3 - f) * f10) - ((f4 - f2) * f11)) / f9;
        if (f13 < 0.0f || f13 > 1.0f) {
            return false;
        }
        if (vector2 != null) {
            vector2.set(f + ((f3 - f) * f12), f2 + ((f4 - f2) * f12));
            return true;
        }
        return true;
    }

    static float det(float f, float f2, float f3, float f4) {
        return (f * f4) - (f2 * f3);
    }

    static double detd(double d, double d2, double d3, double d4) {
        return (d * d4) - (d2 * d3);
    }

    public static boolean overlaps(Circle circle, Circle circle2) {
        return circle.overlaps(circle2);
    }

    public static boolean overlaps(Rectangle rectangle, Rectangle rectangle2) {
        return rectangle.overlaps(rectangle2);
    }

    public static boolean overlaps(Circle circle, Rectangle rectangle) {
        float f = circle.x;
        float f2 = circle.y;
        if (circle.x < rectangle.x) {
            f = rectangle.x;
        } else if (circle.x > rectangle.x + rectangle.width) {
            f = rectangle.x + rectangle.width;
        }
        if (circle.y < rectangle.y) {
            f2 = rectangle.y;
        } else if (circle.y > rectangle.y + rectangle.height) {
            f2 = rectangle.y + rectangle.height;
        }
        float f3 = f - circle.x;
        float f4 = f3 * f3;
        float f5 = f2 - circle.y;
        return f4 + (f5 * f5) < circle.radius * circle.radius;
    }

    public static boolean overlapConvexPolygons(Polygon polygon, Polygon polygon2) {
        return overlapConvexPolygons(polygon, polygon2, (MinimumTranslationVector) null);
    }

    public static boolean overlapConvexPolygons(Polygon polygon, Polygon polygon2, MinimumTranslationVector minimumTranslationVector) {
        return overlapConvexPolygons(polygon.getTransformedVertices(), polygon2.getTransformedVertices(), minimumTranslationVector);
    }

    public static boolean overlapConvexPolygons(float[] fArr, float[] fArr2, MinimumTranslationVector minimumTranslationVector) {
        return overlapConvexPolygons(fArr, 0, fArr.length, fArr2, 0, fArr2.length, minimumTranslationVector);
    }

    public static boolean overlapConvexPolygons(float[] fArr, int i2, int i3, float[] fArr2, int i4, int i5, MinimumTranslationVector minimumTranslationVector) {
        if (minimumTranslationVector != null) {
            minimumTranslationVector.depth = Float.MAX_VALUE;
            minimumTranslationVector.normal.setZero();
        }
        boolean overlapsOnAxisOfShape = overlapsOnAxisOfShape(fArr2, i4, i5, fArr, i2, i3, minimumTranslationVector, true);
        boolean z = overlapsOnAxisOfShape;
        if (overlapsOnAxisOfShape) {
            z = overlapsOnAxisOfShape(fArr, i2, i3, fArr2, i4, i5, minimumTranslationVector, false);
        }
        if (!z) {
            if (minimumTranslationVector != null) {
                minimumTranslationVector.depth = 0.0f;
                minimumTranslationVector.normal.setZero();
                return false;
            }
            return false;
        }
        return true;
    }

    private static boolean overlapsOnAxisOfShape(float[] fArr, int i2, int i3, float[] fArr2, int i4, int i5, MinimumTranslationVector minimumTranslationVector, boolean z) {
        float f;
        float f2;
        int i6 = i2 + i3;
        int i7 = i4 + i5;
        for (int i8 = i2; i8 < i6; i8 += 2) {
            float f3 = fArr[i8];
            float f4 = fArr[i8 + 1];
            float f5 = fArr[(i8 + 2) % i3];
            float f6 = f4 - fArr[(i8 + 3) % i3];
            float f7 = -(f3 - f5);
            float len = Vector2.len(f6, f7);
            float f8 = f6 / len;
            float f9 = f7 / len;
            float f10 = Float.MAX_VALUE;
            float f11 = -3.4028235E38f;
            for (int i9 = i2; i9 < i6; i9 += 2) {
                float f12 = (fArr[i9] * f8) + (fArr[i9 + 1] * f9);
                f10 = Math.min(f10, f12);
                f11 = Math.max(f11, f12);
            }
            float f13 = Float.MAX_VALUE;
            float f14 = -3.4028235E38f;
            for (int i10 = i4; i10 < i7; i10 += 2) {
                float f15 = (fArr2[i10] * f8) + (fArr2[i10 + 1] * f9);
                f13 = Math.min(f13, f15);
                f14 = Math.max(f14, f15);
            }
            if (f11 < f13 || f14 < f10) {
                return false;
            }
            if (minimumTranslationVector != null) {
                float min = Math.min(f11, f14) - Math.max(f10, f13);
                boolean z2 = f10 < f13 && f11 > f14;
                boolean z3 = f13 < f10 && f14 > f11;
                float f16 = 0.0f;
                float f17 = 0.0f;
                if (z2 || z3) {
                    f16 = Math.abs(f10 - f13);
                    f17 = Math.abs(f11 - f14);
                    min += Math.min(f16, f17);
                }
                if (minimumTranslationVector.depth > min) {
                    minimumTranslationVector.depth = min;
                    if (z) {
                        boolean z4 = f10 < f13;
                        boolean z5 = z4;
                        f = z4 ? f8 : -f8;
                        f2 = z5 ? f9 : -f9;
                    } else {
                        boolean z6 = f10 > f13;
                        boolean z7 = z6;
                        f = z6 ? f8 : -f8;
                        f2 = z7 ? f9 : -f9;
                    }
                    if (z2 || z3) {
                        boolean z8 = f16 > f17;
                        boolean z9 = z8;
                        f = z8 ? f : -f;
                        f2 = z9 ? f2 : -f2;
                    }
                    minimumTranslationVector.normal.set(f, f2);
                }
            }
        }
        return true;
    }

    public static void splitTriangle(float[] fArr, Plane plane, SplitTriangle splitTriangle) {
        int length = fArr.length / 3;
        boolean z = plane.testPoint(fArr[0], fArr[1], fArr[2]) == Plane.PlaneSide.Back;
        boolean z2 = plane.testPoint(fArr[length + 0], fArr[length + 1], fArr[length + 2]) == Plane.PlaneSide.Back;
        boolean z3 = plane.testPoint(fArr[0 + (length << 1)], fArr[1 + (length << 1)], fArr[2 + (length << 1)]) == Plane.PlaneSide.Back;
        splitTriangle.reset();
        if (z == z2 && z2 == z3) {
            splitTriangle.total = 1;
            if (z) {
                splitTriangle.numBack = 1;
                System.arraycopy(fArr, 0, splitTriangle.back, 0, fArr.length);
                return;
            } else {
                splitTriangle.numFront = 1;
                System.arraycopy(fArr, 0, splitTriangle.front, 0, fArr.length);
                return;
            }
        }
        splitTriangle.total = 3;
        splitTriangle.numFront = (z ? 0 : 1) + (z2 ? 0 : 1) + (z3 ? 0 : 1);
        splitTriangle.numBack = splitTriangle.total - splitTriangle.numFront;
        splitTriangle.setSide(!z);
        if (z != z2) {
            splitEdge(fArr, 0, length, length, plane, splitTriangle.edgeSplit, 0);
            splitTriangle.add(fArr, 0, length);
            splitTriangle.add(splitTriangle.edgeSplit, 0, length);
            splitTriangle.setSide(!splitTriangle.getSide());
            splitTriangle.add(splitTriangle.edgeSplit, 0, length);
        } else {
            splitTriangle.add(fArr, 0, length);
        }
        int i2 = length + length;
        if (z2 != z3) {
            splitEdge(fArr, length, i2, length, plane, splitTriangle.edgeSplit, 0);
            splitTriangle.add(fArr, length, length);
            splitTriangle.add(splitTriangle.edgeSplit, 0, length);
            splitTriangle.setSide(!splitTriangle.getSide());
            splitTriangle.add(splitTriangle.edgeSplit, 0, length);
        } else {
            splitTriangle.add(fArr, length, length);
        }
        if (z3 != z) {
            splitEdge(fArr, i2, 0, length, plane, splitTriangle.edgeSplit, 0);
            splitTriangle.add(fArr, i2, length);
            splitTriangle.add(splitTriangle.edgeSplit, 0, length);
            splitTriangle.setSide(!splitTriangle.getSide());
            splitTriangle.add(splitTriangle.edgeSplit, 0, length);
        } else {
            splitTriangle.add(fArr, i2, length);
        }
        if (splitTriangle.numFront == 2) {
            System.arraycopy(splitTriangle.front, length << 1, splitTriangle.front, length * 3, length << 1);
            System.arraycopy(splitTriangle.front, 0, splitTriangle.front, length * 5, length);
        } else {
            System.arraycopy(splitTriangle.back, length << 1, splitTriangle.back, length * 3, length << 1);
            System.arraycopy(splitTriangle.back, 0, splitTriangle.back, length * 5, length);
        }
    }

    private static void splitEdge(float[] fArr, int i2, int i3, int i4, Plane plane, float[] fArr2, int i5) {
        float intersectLinePlane = intersectLinePlane(fArr[i2], fArr[i2 + 1], fArr[i2 + 2], fArr[i3], fArr[i3 + 1], fArr[i3 + 2], plane, intersection);
        fArr2[i5] = intersection.x;
        fArr2[i5 + 1] = intersection.y;
        fArr2[i5 + 2] = intersection.z;
        for (int i6 = 3; i6 < i4; i6++) {
            float f = fArr[i2 + i6];
            fArr2[i5 + i6] = f + (intersectLinePlane * (fArr[i3 + i6] - f));
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Intersector$SplitTriangle.class */
    public static class SplitTriangle {
        public float[] front;
        public float[] back;
        float[] edgeSplit;
        public int numFront;
        public int numBack;
        public int total;
        boolean frontCurrent = false;
        int frontOffset = 0;
        int backOffset = 0;

        public SplitTriangle(int i) {
            this.front = new float[(i * 3) << 1];
            this.back = new float[(i * 3) << 1];
            this.edgeSplit = new float[i];
        }

        public String toString() {
            return "SplitTriangle [front=" + Arrays.toString(this.front) + ", back=" + Arrays.toString(this.back) + ", numFront=" + this.numFront + ", numBack=" + this.numBack + ", total=" + this.total + "]";
        }

        void setSide(boolean z) {
            this.frontCurrent = z;
        }

        boolean getSide() {
            return this.frontCurrent;
        }

        void add(float[] fArr, int i, int i2) {
            if (this.frontCurrent) {
                System.arraycopy(fArr, i, this.front, this.frontOffset, i2);
                this.frontOffset += i2;
            } else {
                System.arraycopy(fArr, i, this.back, this.backOffset, i2);
                this.backOffset += i2;
            }
        }

        void reset() {
            this.frontCurrent = false;
            this.frontOffset = 0;
            this.backOffset = 0;
            this.numFront = 0;
            this.numBack = 0;
            this.total = 0;
        }
    }

    public static boolean hasOverlap(Vector3[] vector3Arr, Vector3[] vector3Arr2, Vector3[] vector3Arr3) {
        for (Vector3 vector3 : vector3Arr) {
            float f = Float.MAX_VALUE;
            float f2 = -3.4028235E38f;
            for (Vector3 vector32 : vector3Arr2) {
                float dot = vector32.dot(vector3);
                f = Math.min(f, dot);
                f2 = Math.max(f2, dot);
            }
            float f3 = Float.MAX_VALUE;
            float f4 = -3.4028235E38f;
            for (Vector3 vector33 : vector3Arr3) {
                float dot2 = vector33.dot(vector3);
                f3 = Math.min(f3, dot2);
                f4 = Math.max(f4, dot2);
            }
            if (f2 < f3 || f4 < f) {
                return false;
            }
        }
        return true;
    }
}
