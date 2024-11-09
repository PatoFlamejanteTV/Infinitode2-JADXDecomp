package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/ShapeRenderer.class */
public class ShapeRenderer implements Disposable {
    private final ImmediateModeRenderer renderer;
    private boolean matrixDirty;
    private final Matrix4 projectionMatrix;
    private final Matrix4 transformMatrix;
    private final Matrix4 combinedMatrix;
    private final Vector2 tmp;
    private final Color color;
    private ShapeType shapeType;
    private boolean autoShapeType;
    private float defaultRectLineWidth;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/ShapeRenderer$ShapeType.class */
    public enum ShapeType {
        Point(0),
        Line(1),
        Filled(4);

        private final int glType;

        ShapeType(int i) {
            this.glType = i;
        }

        public final int getGlType() {
            return this.glType;
        }
    }

    public ShapeRenderer() {
        this(5000);
    }

    public ShapeRenderer(int i) {
        this(i, null);
    }

    public ShapeRenderer(int i, ShaderProgram shaderProgram) {
        this.matrixDirty = false;
        this.projectionMatrix = new Matrix4();
        this.transformMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.tmp = new Vector2();
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.defaultRectLineWidth = 0.75f;
        if (shaderProgram == null) {
            this.renderer = new ImmediateModeRenderer20(i, false, true, 0);
        } else {
            this.renderer = new ImmediateModeRenderer20(i, false, true, 0, shaderProgram);
        }
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.matrixDirty = true;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.color.set(f, f2, f3, f4);
    }

    public Color getColor() {
        return this.color;
    }

    public void updateMatrices() {
        this.matrixDirty = true;
    }

    public void setProjectionMatrix(Matrix4 matrix4) {
        this.projectionMatrix.set(matrix4);
        this.matrixDirty = true;
    }

    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public void setTransformMatrix(Matrix4 matrix4) {
        this.transformMatrix.set(matrix4);
        this.matrixDirty = true;
    }

    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    public void identity() {
        this.transformMatrix.idt();
        this.matrixDirty = true;
    }

    public void translate(float f, float f2, float f3) {
        this.transformMatrix.translate(f, f2, f3);
        this.matrixDirty = true;
    }

    public void rotate(float f, float f2, float f3, float f4) {
        this.transformMatrix.rotate(f, f2, f3, f4);
        this.matrixDirty = true;
    }

    public void scale(float f, float f2, float f3) {
        this.transformMatrix.scale(f, f2, f3);
        this.matrixDirty = true;
    }

    public void setAutoShapeType(boolean z) {
        this.autoShapeType = z;
    }

    public void begin() {
        if (!this.autoShapeType) {
            throw new IllegalStateException("autoShapeType must be true to use this method.");
        }
        begin(ShapeType.Line);
    }

    public void begin(ShapeType shapeType) {
        if (this.shapeType != null) {
            throw new IllegalStateException("Call end() before beginning a new shape batch.");
        }
        this.shapeType = shapeType;
        if (this.matrixDirty) {
            this.combinedMatrix.set(this.projectionMatrix);
            Matrix4.mul(this.combinedMatrix.val, this.transformMatrix.val);
            this.matrixDirty = false;
        }
        this.renderer.begin(this.combinedMatrix, this.shapeType.getGlType());
    }

    public void set(ShapeType shapeType) {
        if (this.shapeType == shapeType) {
            return;
        }
        if (this.shapeType == null) {
            throw new IllegalStateException("begin must be called first.");
        }
        if (!this.autoShapeType) {
            throw new IllegalStateException("autoShapeType must be enabled.");
        }
        end();
        begin(shapeType);
    }

    public void point(float f, float f2, float f3) {
        if (this.shapeType == ShapeType.Line) {
            float f4 = this.defaultRectLineWidth * 0.5f;
            line(f - f4, f2 - f4, f3, f + f4, f2 + f4, f3);
        } else if (this.shapeType == ShapeType.Filled) {
            float f5 = this.defaultRectLineWidth * 0.5f;
            box(f - f5, f2 - f5, f3 - f5, this.defaultRectLineWidth, this.defaultRectLineWidth, this.defaultRectLineWidth);
        } else {
            check(ShapeType.Point, null, 1);
            this.renderer.color(this.color);
            this.renderer.vertex(f, f2, f3);
        }
    }

    public final void line(float f, float f2, float f3, float f4, float f5, float f6) {
        line(f, f2, f3, f4, f5, f6, this.color, this.color);
    }

    public final void line(Vector3 vector3, Vector3 vector32) {
        line(vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, this.color, this.color);
    }

    public final void line(float f, float f2, float f3, float f4) {
        line(f, f2, 0.0f, f3, f4, 0.0f, this.color, this.color);
    }

    public final void line(Vector2 vector2, Vector2 vector22) {
        line(vector2.x, vector2.y, 0.0f, vector22.x, vector22.y, 0.0f, this.color, this.color);
    }

    public final void line(float f, float f2, float f3, float f4, Color color, Color color2) {
        line(f, f2, 0.0f, f3, f4, 0.0f, color, color2);
    }

    public void line(float f, float f2, float f3, float f4, float f5, float f6, Color color, Color color2) {
        if (this.shapeType == ShapeType.Filled) {
            rectLine(f, f2, f4, f5, this.defaultRectLineWidth, color, color2);
            return;
        }
        check(ShapeType.Line, null, 2);
        this.renderer.color(color.r, color.g, color.f888b, color.f889a);
        this.renderer.vertex(f, f2, f3);
        this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
        this.renderer.vertex(f4, f5, f6);
    }

    public void curve(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i) {
        check(ShapeType.Line, null, (i << 1) + 2);
        float floatBits = this.color.toFloatBits();
        float f9 = 1.0f / i;
        float f10 = f9 * f9;
        float f11 = f10 * f9;
        float f12 = 3.0f * f9;
        float f13 = 3.0f * f10;
        float f14 = 6.0f * f10;
        float f15 = 6.0f * f11;
        float f16 = (f - (f3 * 2.0f)) + f5;
        float f17 = (f2 - (f4 * 2.0f)) + f6;
        float f18 = (((f3 - f5) * 3.0f) - f) + f7;
        float f19 = (((f4 - f6) * 3.0f) - f2) + f8;
        float f20 = f;
        float f21 = f2;
        float f22 = ((f3 - f) * f12) + (f16 * f13) + (f18 * f11);
        float f23 = ((f4 - f2) * f12) + (f17 * f13) + (f19 * f11);
        float f24 = (f16 * f14) + (f18 * f15);
        float f25 = (f17 * f14) + (f19 * f15);
        float f26 = f18 * f15;
        float f27 = f19 * f15;
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f20, f21, 0.0f);
                f20 += f22;
                f21 += f23;
                f22 += f24;
                f23 += f25;
                f24 += f26;
                f25 += f27;
                this.renderer.color(floatBits);
                this.renderer.vertex(f20, f21, 0.0f);
            } else {
                this.renderer.color(floatBits);
                this.renderer.vertex(f20, f21, 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f7, f8, 0.0f);
                return;
            }
        }
    }

    public void triangle(float f, float f2, float f3, float f4, float f5, float f6) {
        check(ShapeType.Line, ShapeType.Filled, 6);
        float floatBits = this.color.toFloatBits();
        if (this.shapeType == ShapeType.Line) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f3, f4, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f3, f4, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f3, f4, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f5, f6, 0.0f);
    }

    public void triangle(float f, float f2, float f3, float f4, float f5, float f6, Color color, Color color2, Color color3) {
        check(ShapeType.Line, ShapeType.Filled, 6);
        if (this.shapeType == ShapeType.Line) {
            this.renderer.color(color.r, color.g, color.f888b, color.f889a);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
            this.renderer.vertex(f3, f4, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
            this.renderer.vertex(f3, f4, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(color.r, color.g, color.f888b, color.f889a);
            this.renderer.vertex(f, f2, 0.0f);
            return;
        }
        this.renderer.color(color.r, color.g, color.f888b, color.f889a);
        this.renderer.vertex(f, f2, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
        this.renderer.vertex(f3, f4, 0.0f);
        this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
        this.renderer.vertex(f5, f6, 0.0f);
    }

    public void rect(float f, float f2, float f3, float f4) {
        check(ShapeType.Line, ShapeType.Filled, 8);
        float floatBits = this.color.toFloatBits();
        if (this.shapeType == ShapeType.Line) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f3, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f3, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f3, f2 + f4, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f3, f2 + f4, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f4, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f4, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f3, f2, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f3, f2 + f4, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f3, f2 + f4, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f4, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, 0.0f);
    }

    public void rect(float f, float f2, float f3, float f4, Color color, Color color2, Color color3, Color color4) {
        check(ShapeType.Line, ShapeType.Filled, 8);
        if (this.shapeType == ShapeType.Line) {
            this.renderer.color(color.r, color.g, color.f888b, color.f889a);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
            this.renderer.vertex(f + f3, f2, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
            this.renderer.vertex(f + f3, f2, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
            this.renderer.vertex(f + f3, f2 + f4, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
            this.renderer.vertex(f + f3, f2 + f4, 0.0f);
            this.renderer.color(color4.r, color4.g, color4.f888b, color4.f889a);
            this.renderer.vertex(f, f2 + f4, 0.0f);
            this.renderer.color(color4.r, color4.g, color4.f888b, color4.f889a);
            this.renderer.vertex(f, f2 + f4, 0.0f);
            this.renderer.color(color.r, color.g, color.f888b, color.f889a);
            this.renderer.vertex(f, f2, 0.0f);
            return;
        }
        this.renderer.color(color.r, color.g, color.f888b, color.f889a);
        this.renderer.vertex(f, f2, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
        this.renderer.vertex(f + f3, f2, 0.0f);
        this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
        this.renderer.vertex(f + f3, f2 + f4, 0.0f);
        this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
        this.renderer.vertex(f + f3, f2 + f4, 0.0f);
        this.renderer.color(color4.r, color4.g, color4.f888b, color4.f889a);
        this.renderer.vertex(f, f2 + f4, 0.0f);
        this.renderer.color(color.r, color.g, color.f888b, color.f889a);
        this.renderer.vertex(f, f2, 0.0f);
    }

    public void rect(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        rect(f, f2, f3, f4, f5, f6, f7, f8, f9, this.color, this.color, this.color, this.color);
    }

    public void rect(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Color color, Color color2, Color color3, Color color4) {
        check(ShapeType.Line, ShapeType.Filled, 8);
        float cosDeg = MathUtils.cosDeg(f9);
        float sinDeg = MathUtils.sinDeg(f9);
        float f10 = -f3;
        float f11 = -f4;
        float f12 = f5 - f3;
        float f13 = f6 - f4;
        if (f7 != 1.0f || f8 != 1.0f) {
            f10 *= f7;
            f11 *= f8;
            f12 *= f7;
            f13 *= f8;
        }
        float f14 = f + f3;
        float f15 = f2 + f4;
        float f16 = ((cosDeg * f10) - (sinDeg * f11)) + f14;
        float f17 = (sinDeg * f10) + (cosDeg * f11) + f15;
        float f18 = ((cosDeg * f12) - (sinDeg * f11)) + f14;
        float f19 = (sinDeg * f12) + (cosDeg * f11) + f15;
        float f20 = ((cosDeg * f12) - (sinDeg * f13)) + f14;
        float f21 = (sinDeg * f12) + (cosDeg * f13) + f15;
        float f22 = f16 + (f20 - f18);
        float f23 = f21 - (f19 - f17);
        if (this.shapeType == ShapeType.Line) {
            this.renderer.color(color.r, color.g, color.f888b, color.f889a);
            this.renderer.vertex(f16, f17, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
            this.renderer.vertex(f18, f19, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
            this.renderer.vertex(f18, f19, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
            this.renderer.vertex(f20, f21, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
            this.renderer.vertex(f20, f21, 0.0f);
            this.renderer.color(color4.r, color4.g, color4.f888b, color4.f889a);
            this.renderer.vertex(f22, f23, 0.0f);
            this.renderer.color(color4.r, color4.g, color4.f888b, color4.f889a);
            this.renderer.vertex(f22, f23, 0.0f);
            this.renderer.color(color.r, color.g, color.f888b, color.f889a);
            this.renderer.vertex(f16, f17, 0.0f);
            return;
        }
        this.renderer.color(color.r, color.g, color.f888b, color.f889a);
        this.renderer.vertex(f16, f17, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.f888b, color2.f889a);
        this.renderer.vertex(f18, f19, 0.0f);
        this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
        this.renderer.vertex(f20, f21, 0.0f);
        this.renderer.color(color3.r, color3.g, color3.f888b, color3.f889a);
        this.renderer.vertex(f20, f21, 0.0f);
        this.renderer.color(color4.r, color4.g, color4.f888b, color4.f889a);
        this.renderer.vertex(f22, f23, 0.0f);
        this.renderer.color(color.r, color.g, color.f888b, color.f889a);
        this.renderer.vertex(f16, f17, 0.0f);
    }

    public void rectLine(float f, float f2, float f3, float f4, float f5) {
        check(ShapeType.Line, ShapeType.Filled, 8);
        float floatBits = this.color.toFloatBits();
        Vector2 nor = this.tmp.set(f4 - f2, f - f3).nor();
        float f6 = f5 * 0.5f;
        float f7 = nor.x * f6;
        float f8 = nor.y * f6;
        if (this.shapeType == ShapeType.Line) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f7, f2 + f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f - f7, f2 - f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f3 - f7, f4 - f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f7, f2 + f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f3 - f7, f4 - f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f - f7, f2 - f8, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f7, f2 + f8, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f - f7, f2 - f8, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f3 - f7, f4 - f8, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f - f7, f2 - f8, 0.0f);
    }

    public void rectLine(float f, float f2, float f3, float f4, float f5, Color color, Color color2) {
        check(ShapeType.Line, ShapeType.Filled, 8);
        float floatBits = color.toFloatBits();
        float floatBits2 = color2.toFloatBits();
        Vector2 nor = this.tmp.set(f4 - f2, f - f3).nor();
        float f6 = f5 * 0.5f;
        float f7 = nor.x * f6;
        float f8 = nor.y * f6;
        if (this.shapeType == ShapeType.Line) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f7, f2 + f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f - f7, f2 - f8, 0.0f);
            this.renderer.color(floatBits2);
            this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
            this.renderer.color(floatBits2);
            this.renderer.vertex(f3 - f7, f4 - f8, 0.0f);
            this.renderer.color(floatBits2);
            this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f7, f2 + f8, 0.0f);
            this.renderer.color(floatBits2);
            this.renderer.vertex(f3 - f7, f4 - f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f - f7, f2 - f8, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f7, f2 + f8, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f - f7, f2 - f8, 0.0f);
        this.renderer.color(floatBits2);
        this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
        this.renderer.color(floatBits2);
        this.renderer.vertex(f3 - f7, f4 - f8, 0.0f);
        this.renderer.color(floatBits2);
        this.renderer.vertex(f3 + f7, f4 + f8, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f - f7, f2 - f8, 0.0f);
    }

    public void rectLine(Vector2 vector2, Vector2 vector22, float f) {
        rectLine(vector2.x, vector2.y, vector22.x, vector22.y, f);
    }

    public void box(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = -f6;
        float floatBits = this.color.toFloatBits();
        if (this.shapeType == ShapeType.Line) {
            check(ShapeType.Line, ShapeType.Filled, 24);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f5, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f5, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2 + f5, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2 + f5, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f5, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f5, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f5, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2 + f5, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3 + f7);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2 + f5, f3 + f7);
            return;
        }
        check(ShapeType.Line, ShapeType.Filled, 36);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2 + f5, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3 + f7);
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2, f3);
        this.renderer.color(floatBits);
        this.renderer.vertex(f, f2, f3);
    }

    public void x(float f, float f2, float f3) {
        line(f - f3, f2 - f3, f + f3, f2 + f3);
        line(f - f3, f2 + f3, f + f3, f2 - f3);
    }

    public void x(Vector2 vector2, float f) {
        x(vector2.x, vector2.y, f);
    }

    public void arc(float f, float f2, float f3, float f4, float f5) {
        arc(f, f2, f3, f4, f5, Math.max(1, (int) (6.0f * ((float) Math.cbrt(f3)) * (f5 / 360.0f))));
    }

    public void arc(float f, float f2, float f3, float f4, float f5, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        float floatBits = this.color.toFloatBits();
        float f6 = (6.2831855f * (f5 / 360.0f)) / i;
        float cos = MathUtils.cos(f6);
        float sin = MathUtils.sin(f6);
        float cos2 = f3 * MathUtils.cos(f4 * 0.017453292f);
        float sin2 = f3 * MathUtils.sin(f4 * 0.017453292f);
        if (this.shapeType == ShapeType.Line) {
            check(ShapeType.Line, ShapeType.Filled, (i << 1) + 2);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + cos2, f2 + sin2, 0.0f);
            for (int i2 = 0; i2 < i; i2++) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f + cos2, f2 + sin2, 0.0f);
                float f7 = cos2;
                cos2 = (cos * cos2) - (sin * sin2);
                sin2 = (sin * f7) + (cos * sin2);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + cos2, f2 + sin2, 0.0f);
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f + cos2, f2 + sin2, 0.0f);
        } else {
            check(ShapeType.Line, ShapeType.Filled, (i * 3) + 3);
            for (int i3 = 0; i3 < i; i3++) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f, f2, 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + cos2, f2 + sin2, 0.0f);
                float f8 = cos2;
                cos2 = (cos * cos2) - (sin * sin2);
                sin2 = (sin * f8) + (cos * sin2);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + cos2, f2 + sin2, 0.0f);
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + cos2, f2 + sin2, 0.0f);
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f + 0.0f, f2 + 0.0f, 0.0f);
    }

    public void circle(float f, float f2, float f3) {
        circle(f, f2, f3, Math.max(1, (int) (6.0f * ((float) Math.cbrt(f3)))));
    }

    public void circle(float f, float f2, float f3, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        float floatBits = this.color.toFloatBits();
        float f4 = 6.2831855f / i;
        float cos = MathUtils.cos(f4);
        float sin = MathUtils.sin(f4);
        float f5 = f3;
        float f6 = 0.0f;
        if (this.shapeType == ShapeType.Line) {
            check(ShapeType.Line, ShapeType.Filled, (i << 1) + 2);
            for (int i2 = 0; i2 < i; i2++) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f5, f2 + f6, 0.0f);
                float f7 = f5;
                f5 = (cos * f5) - (sin * f6);
                f6 = (sin * f7) + (cos * f6);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f5, f2 + f6, 0.0f);
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f5, f2 + f6, 0.0f);
        } else {
            check(ShapeType.Line, ShapeType.Filled, (i * 3) + 3);
            int i3 = i - 1;
            for (int i4 = 0; i4 < i3; i4++) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f, f2, 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f5, f2 + f6, 0.0f);
                float f8 = f5;
                f5 = (cos * f5) - (sin * f6);
                f6 = (sin * f8) + (cos * f6);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f5, f2 + f6, 0.0f);
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f5, f2 + f6, 0.0f);
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f3, f2 + 0.0f, 0.0f);
    }

    public void ellipse(float f, float f2, float f3, float f4) {
        ellipse(f, f2, f3, f4, Math.max(1, (int) (12.0f * ((float) Math.cbrt(Math.max(f3 * 0.5f, f4 * 0.5f))))));
    }

    public void ellipse(float f, float f2, float f3, float f4, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        check(ShapeType.Line, ShapeType.Filled, i * 3);
        float floatBits = this.color.toFloatBits();
        float f5 = 6.2831855f / i;
        float f6 = f + (f3 / 2.0f);
        float f7 = f2 + (f4 / 2.0f);
        if (this.shapeType == ShapeType.Line) {
            for (int i2 = 0; i2 < i; i2++) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f6 + (f3 * 0.5f * MathUtils.cos(i2 * f5)), f7 + (f4 * 0.5f * MathUtils.sin(i2 * f5)), 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f6 + (f3 * 0.5f * MathUtils.cos((i2 + 1) * f5)), f7 + (f4 * 0.5f * MathUtils.sin((i2 + 1) * f5)), 0.0f);
            }
            return;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f6 + (f3 * 0.5f * MathUtils.cos(i3 * f5)), f7 + (f4 * 0.5f * MathUtils.sin(i3 * f5)), 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f6 + (f3 * 0.5f * MathUtils.cos((i3 + 1) * f5)), f7 + (f4 * 0.5f * MathUtils.sin((i3 + 1) * f5)), 0.0f);
        }
    }

    public void ellipse(float f, float f2, float f3, float f4, float f5) {
        ellipse(f, f2, f3, f4, f5, Math.max(1, (int) (12.0f * ((float) Math.cbrt(Math.max(f3 * 0.5f, f4 * 0.5f))))));
    }

    public void ellipse(float f, float f2, float f3, float f4, float f5, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        check(ShapeType.Line, ShapeType.Filled, i * 3);
        float floatBits = this.color.toFloatBits();
        float f6 = 6.2831855f / i;
        float f7 = (3.1415927f * f5) / 180.0f;
        float sin = MathUtils.sin(f7);
        float cos = MathUtils.cos(f7);
        float f8 = f + (f3 / 2.0f);
        float f9 = f2 + (f4 / 2.0f);
        float f10 = f3 * 0.5f;
        float f11 = 0.0f;
        if (this.shapeType == ShapeType.Line) {
            for (int i2 = 0; i2 < i; i2++) {
                this.renderer.color(floatBits);
                this.renderer.vertex((f8 + (cos * f10)) - (sin * f11), f9 + (sin * f10) + (cos * f11), 0.0f);
                f10 = f3 * 0.5f * MathUtils.cos((i2 + 1) * f6);
                f11 = f4 * 0.5f * MathUtils.sin((i2 + 1) * f6);
                this.renderer.color(floatBits);
                this.renderer.vertex((f8 + (cos * f10)) - (sin * f11), f9 + (sin * f10) + (cos * f11), 0.0f);
            }
            return;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.renderer.color(floatBits);
            this.renderer.vertex((f8 + (cos * f10)) - (sin * f11), f9 + (sin * f10) + (cos * f11), 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f8, f9, 0.0f);
            f10 = f3 * 0.5f * MathUtils.cos((i3 + 1) * f6);
            f11 = f4 * 0.5f * MathUtils.sin((i3 + 1) * f6);
            this.renderer.color(floatBits);
            this.renderer.vertex((f8 + (cos * f10)) - (sin * f11), f9 + (sin * f10) + (cos * f11), 0.0f);
        }
    }

    public void cone(float f, float f2, float f3, float f4, float f5) {
        cone(f, f2, f3, f4, f5, Math.max(1, (int) (4.0f * ((float) Math.sqrt(f4)))));
    }

    public void cone(float f, float f2, float f3, float f4, float f5, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        check(ShapeType.Line, ShapeType.Filled, (i << 2) + 2);
        float floatBits = this.color.toFloatBits();
        float f6 = 6.2831855f / i;
        float cos = MathUtils.cos(f6);
        float sin = MathUtils.sin(f6);
        float f7 = f4;
        float f8 = 0.0f;
        if (this.shapeType == ShapeType.Line) {
            for (int i2 = 0; i2 < i; i2++) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f7, f2 + f8, f3);
                this.renderer.color(floatBits);
                this.renderer.vertex(f, f2, f3 + f5);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f7, f2 + f8, f3);
                float f9 = f7;
                f7 = (cos * f7) - (sin * f8);
                f8 = (sin * f9) + (cos * f8);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f7, f2 + f8, f3);
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f7, f2 + f8, f3);
        } else {
            int i3 = i - 1;
            for (int i4 = 0; i4 < i3; i4++) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f, f2, f3);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f7, f2 + f8, f3);
                float f10 = f7;
                float f11 = f8;
                f7 = (cos * f7) - (sin * f8);
                f8 = (sin * f10) + (cos * f8);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f7, f2 + f8, f3);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f10, f2 + f11, f3);
                this.renderer.color(floatBits);
                this.renderer.vertex(f + f7, f2 + f8, f3);
                this.renderer.color(floatBits);
                this.renderer.vertex(f, f2, f3 + f5);
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f7, f2 + f8, f3);
        }
        float f12 = f7;
        float f13 = f8;
        this.renderer.color(floatBits);
        this.renderer.vertex(f + f4, f2 + 0.0f, f3);
        if (this.shapeType != ShapeType.Line) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f12, f2 + f13, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f + f4, f2 + 0.0f, f3);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, f3 + f5);
        }
    }

    public void polygon(float[] fArr, int i, int i2) {
        float f;
        float f2;
        if (i2 < 6) {
            throw new IllegalArgumentException("Polygons must contain at least 3 points.");
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Polygons must have an even number of vertices.");
        }
        check(ShapeType.Line, null, i2);
        float floatBits = this.color.toFloatBits();
        float f3 = fArr[0];
        float f4 = fArr[1];
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4 += 2) {
            float f5 = fArr[i4];
            float f6 = fArr[i4 + 1];
            if (i4 + 2 >= i2) {
                f = f3;
                f2 = f4;
            } else {
                f = fArr[i4 + 2];
                f2 = fArr[i4 + 3];
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
        }
    }

    public void polygon(float[] fArr) {
        polygon(fArr, 0, fArr.length);
    }

    public void polyline(float[] fArr, int i, int i2) {
        if (i2 < 4) {
            throw new IllegalArgumentException("Polylines must contain at least 2 points.");
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Polylines must have an even number of vertices.");
        }
        check(ShapeType.Line, null, i2);
        float floatBits = this.color.toFloatBits();
        int i3 = (i + i2) - 2;
        for (int i4 = i; i4 < i3; i4 += 2) {
            float f = fArr[i4];
            float f2 = fArr[i4 + 1];
            float f3 = fArr[i4 + 2];
            float f4 = fArr[i4 + 3];
            this.renderer.color(floatBits);
            this.renderer.vertex(f, f2, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f3, f4, 0.0f);
        }
    }

    public void polyline(float[] fArr) {
        polyline(fArr, 0, fArr.length);
    }

    protected final void check(ShapeType shapeType, ShapeType shapeType2, int i) {
        if (this.shapeType == null) {
            throw new IllegalStateException("begin must be called first.");
        }
        if (this.shapeType != shapeType && this.shapeType != shapeType2) {
            if (!this.autoShapeType) {
                if (shapeType2 == null) {
                    throw new IllegalStateException("Must call begin(ShapeType." + shapeType + ").");
                }
                throw new IllegalStateException("Must call begin(ShapeType." + shapeType + ") or begin(ShapeType." + shapeType2 + ").");
            }
            end();
            begin(shapeType);
            return;
        }
        if (this.matrixDirty) {
            ShapeType shapeType3 = this.shapeType;
            end();
            begin(shapeType3);
        } else if (this.renderer.getMaxVertices() - this.renderer.getNumVertices() < i) {
            ShapeType shapeType4 = this.shapeType;
            end();
            begin(shapeType4);
        }
    }

    public void end() {
        this.renderer.end();
        this.shapeType = null;
    }

    public void flush() {
        ShapeType shapeType = this.shapeType;
        if (shapeType == null) {
            return;
        }
        end();
        begin(shapeType);
    }

    public ShapeType getCurrentType() {
        return this.shapeType;
    }

    public ImmediateModeRenderer getRenderer() {
        return this.renderer;
    }

    public boolean isDrawing() {
        return this.shapeType != null;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.renderer.dispose();
    }
}
