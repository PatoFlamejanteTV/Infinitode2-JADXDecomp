package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.Vector2;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Scaling.class */
public abstract class Scaling {
    protected static final Vector2 temp = new Vector2();
    public static final Scaling fit = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.1
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            float f5 = f4 / f3 > f2 / f ? f3 / f : f4 / f2;
            temp.x = f * f5;
            temp.y = f2 * f5;
            return temp;
        }
    };
    public static final Scaling contain = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.2
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            float f5 = f4 / f3 > f2 / f ? f3 / f : f4 / f2;
            float f6 = f5;
            if (f5 > 1.0f) {
                f6 = 1.0f;
            }
            temp.x = f * f6;
            temp.y = f2 * f6;
            return temp;
        }
    };
    public static final Scaling fill = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.3
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            float f5 = f4 / f3 < f2 / f ? f3 / f : f4 / f2;
            temp.x = f * f5;
            temp.y = f2 * f5;
            return temp;
        }
    };
    public static final Scaling fillX = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.4
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            float f5 = f3 / f;
            temp.x = f * f5;
            temp.y = f2 * f5;
            return temp;
        }
    };
    public static final Scaling fillY = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.5
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            float f5 = f4 / f2;
            temp.x = f * f5;
            temp.y = f2 * f5;
            return temp;
        }
    };
    public static final Scaling stretch = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.6
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            temp.x = f3;
            temp.y = f4;
            return temp;
        }
    };
    public static final Scaling stretchX = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.7
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            temp.x = f3;
            temp.y = f2;
            return temp;
        }
    };
    public static final Scaling stretchY = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.8
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            temp.x = f;
            temp.y = f4;
            return temp;
        }
    };
    public static final Scaling none = new Scaling() { // from class: com.badlogic.gdx.utils.Scaling.9
        @Override // com.badlogic.gdx.utils.Scaling
        public Vector2 apply(float f, float f2, float f3, float f4) {
            temp.x = f;
            temp.y = f2;
            return temp;
        }
    };

    public abstract Vector2 apply(float f, float f2, float f3, float f4);
}
