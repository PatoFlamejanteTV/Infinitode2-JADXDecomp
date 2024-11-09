package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/FrustumShapeBuilder.class */
public class FrustumShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, Camera camera) {
        build(meshPartBuilder, camera, tmpColor0.set(1.0f, 0.66f, 0.0f, 1.0f), tmpColor1.set(1.0f, 0.0f, 0.0f, 1.0f), tmpColor2.set(0.0f, 0.66f, 1.0f, 1.0f), tmpColor3.set(1.0f, 1.0f, 1.0f, 1.0f), tmpColor4.set(0.2f, 0.2f, 0.2f, 1.0f));
    }

    public static void build(MeshPartBuilder meshPartBuilder, Camera camera, Color color, Color color2, Color color3, Color color4, Color color5) {
        Vector3[] vector3Arr = camera.frustum.planePoints;
        build(meshPartBuilder, camera.frustum, color, color5);
        meshPartBuilder.line(vector3Arr[0], color2, camera.position, color2);
        meshPartBuilder.line(vector3Arr[1], color2, camera.position, color2);
        meshPartBuilder.line(vector3Arr[2], color2, camera.position, color2);
        meshPartBuilder.line(vector3Arr[3], color2, camera.position, color2);
        meshPartBuilder.line(camera.position, color4, centerPoint(vector3Arr[4], vector3Arr[5], vector3Arr[6]), color4);
        float len = tmpV0.set(vector3Arr[1]).sub(vector3Arr[0]).scl(0.5f).len();
        Vector3 centerPoint = centerPoint(vector3Arr[0], vector3Arr[1], vector3Arr[2]);
        tmpV0.set(camera.up).scl(len * 2.0f);
        centerPoint.add(tmpV0);
        meshPartBuilder.line(centerPoint, color3, vector3Arr[2], color3);
        meshPartBuilder.line(vector3Arr[2], color3, vector3Arr[3], color3);
        meshPartBuilder.line(vector3Arr[3], color3, centerPoint, color3);
    }

    public static void build(MeshPartBuilder meshPartBuilder, Frustum frustum, Color color, Color color2) {
        Vector3[] vector3Arr = frustum.planePoints;
        meshPartBuilder.line(vector3Arr[0], color, vector3Arr[1], color);
        meshPartBuilder.line(vector3Arr[1], color, vector3Arr[2], color);
        meshPartBuilder.line(vector3Arr[2], color, vector3Arr[3], color);
        meshPartBuilder.line(vector3Arr[3], color, vector3Arr[0], color);
        meshPartBuilder.line(vector3Arr[4], color, vector3Arr[5], color);
        meshPartBuilder.line(vector3Arr[5], color, vector3Arr[6], color);
        meshPartBuilder.line(vector3Arr[6], color, vector3Arr[7], color);
        meshPartBuilder.line(vector3Arr[7], color, vector3Arr[4], color);
        meshPartBuilder.line(vector3Arr[0], color, vector3Arr[4], color);
        meshPartBuilder.line(vector3Arr[1], color, vector3Arr[5], color);
        meshPartBuilder.line(vector3Arr[2], color, vector3Arr[6], color);
        meshPartBuilder.line(vector3Arr[3], color, vector3Arr[7], color);
        meshPartBuilder.line(middlePoint(vector3Arr[1], vector3Arr[0]), color2, middlePoint(vector3Arr[3], vector3Arr[2]), color2);
        meshPartBuilder.line(middlePoint(vector3Arr[2], vector3Arr[1]), color2, middlePoint(vector3Arr[3], vector3Arr[0]), color2);
        meshPartBuilder.line(middlePoint(vector3Arr[5], vector3Arr[4]), color2, middlePoint(vector3Arr[7], vector3Arr[6]), color2);
        meshPartBuilder.line(middlePoint(vector3Arr[6], vector3Arr[5]), color2, middlePoint(vector3Arr[7], vector3Arr[4]), color2);
    }

    private static Vector3 middlePoint(Vector3 vector3, Vector3 vector32) {
        tmpV0.set(vector32).sub(vector3).scl(0.5f);
        return tmpV1.set(vector3).add(tmpV0);
    }

    private static Vector3 centerPoint(Vector3 vector3, Vector3 vector32, Vector3 vector33) {
        tmpV0.set(vector32).sub(vector3).scl(0.5f);
        tmpV1.set(vector3).add(tmpV0);
        tmpV0.set(vector33).sub(vector32).scl(0.5f);
        return tmpV1.add(tmpV0);
    }
}
