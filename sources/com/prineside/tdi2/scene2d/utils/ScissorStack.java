package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/ScissorStack.class */
public class ScissorStack {

    /* renamed from: a, reason: collision with root package name */
    private static Array<Rectangle> f2731a = new Array<>();

    /* renamed from: b, reason: collision with root package name */
    private static Vector3 f2732b = new Vector3();
    private static Rectangle c = new Rectangle();

    public static boolean pushScissors(Rectangle rectangle) {
        a(rectangle);
        if (f2731a.size != 0) {
            Array<Rectangle> array = f2731a;
            Rectangle rectangle2 = array.get(array.size - 1);
            float max = Math.max(rectangle2.x, rectangle.x);
            float min = Math.min(rectangle2.x + rectangle2.width, rectangle.x + rectangle.width);
            if (min - max < 1.0f) {
                return false;
            }
            float max2 = Math.max(rectangle2.y, rectangle.y);
            float min2 = Math.min(rectangle2.y + rectangle2.height, rectangle.y + rectangle.height);
            if (min2 - max2 < 1.0f) {
                return false;
            }
            rectangle.x = max;
            rectangle.y = max2;
            rectangle.width = min - max;
            rectangle.height = Math.max(1.0f, min2 - max2);
        } else {
            if (rectangle.width < 1.0f || rectangle.height < 1.0f) {
                return false;
            }
            Gdx.gl.glEnable(3089);
        }
        f2731a.add(rectangle);
        HdpiUtils.glScissor((int) rectangle.x, (int) rectangle.y, (int) rectangle.width, (int) rectangle.height);
        return true;
    }

    public static Rectangle popScissors() {
        Rectangle pop = f2731a.pop();
        if (f2731a.size == 0) {
            Gdx.gl.glDisable(3089);
        } else {
            Rectangle peek = f2731a.peek();
            HdpiUtils.glScissor((int) peek.x, (int) peek.y, (int) peek.width, (int) peek.height);
        }
        return pop;
    }

    @Null
    public static Rectangle peekScissors() {
        if (f2731a.size == 0) {
            return null;
        }
        return f2731a.peek();
    }

    private static void a(Rectangle rectangle) {
        rectangle.x = Math.round(rectangle.x);
        rectangle.y = Math.round(rectangle.y);
        rectangle.width = Math.round(rectangle.width);
        rectangle.height = Math.round(rectangle.height);
        if (rectangle.width < 0.0f) {
            rectangle.width = -rectangle.width;
            rectangle.x -= rectangle.width;
        }
        if (rectangle.height < 0.0f) {
            rectangle.height = -rectangle.height;
            rectangle.y -= rectangle.height;
        }
    }

    public static void calculateScissors(Camera camera, Matrix4 matrix4, Rectangle rectangle, Rectangle rectangle2) {
        calculateScissors(camera, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), matrix4, rectangle, rectangle2);
    }

    public static void calculateScissors(Camera camera, float f, float f2, float f3, float f4, Matrix4 matrix4, Rectangle rectangle, Rectangle rectangle2) {
        f2732b.set(rectangle.x, rectangle.y, 0.0f);
        f2732b.mul(matrix4);
        camera.project(f2732b, f, f2, f3, f4);
        rectangle2.x = f2732b.x;
        rectangle2.y = f2732b.y;
        f2732b.set(rectangle.x + rectangle.width, rectangle.y + rectangle.height, 0.0f);
        f2732b.mul(matrix4);
        camera.project(f2732b, f, f2, f3, f4);
        rectangle2.width = f2732b.x - rectangle2.x;
        rectangle2.height = f2732b.y - rectangle2.y;
    }

    public static Rectangle getViewport() {
        if (f2731a.size == 0) {
            c.set(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            return c;
        }
        c.set(f2731a.peek());
        return c;
    }
}
