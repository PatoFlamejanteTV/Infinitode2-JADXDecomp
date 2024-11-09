package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Value.class */
public abstract class Value {
    public static final Fixed zero = new Fixed(0.0f);
    public static Value minWidth = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMinWidth();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getWidth();
        }
    };
    public static Value minHeight = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.2
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMinHeight();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getHeight();
        }
    };
    public static Value prefWidth = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.3
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getPrefWidth();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getWidth();
        }
    };
    public static Value prefHeight = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.4
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getPrefHeight();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getHeight();
        }
    };
    public static Value maxWidth = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.5
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMaxWidth();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getWidth();
        }
    };
    public static Value maxHeight = new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.6
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            if (actor instanceof Layout) {
                return ((Layout) actor).getMaxHeight();
            }
            if (actor == 0) {
                return 0.0f;
            }
            return actor.getHeight();
        }
    };

    public abstract float get(@Null Actor actor);

    public float get() {
        return get(null);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Value$Fixed.class */
    public static class Fixed extends Value {

        /* renamed from: a, reason: collision with root package name */
        private static Fixed[] f2699a = new Fixed[111];

        /* renamed from: b, reason: collision with root package name */
        private final float f2700b;

        public Fixed(float f) {
            this.f2700b = f;
        }

        @Override // com.prineside.tdi2.scene2d.ui.Value
        public float get(@Null Actor actor) {
            return this.f2700b;
        }

        public String toString() {
            return Float.toString(this.f2700b);
        }

        public static Fixed valueOf(float f) {
            if (f == 0.0f) {
                return zero;
            }
            if (f >= -10.0f && f <= 100.0f && f == ((int) f)) {
                Fixed fixed = f2699a[((int) f) + 10];
                Fixed fixed2 = fixed;
                if (fixed == null) {
                    Fixed fixed3 = new Fixed(f);
                    fixed2 = fixed3;
                    f2699a[((int) f) + 10] = fixed3;
                }
                return fixed2;
            }
            return new Fixed(f);
        }
    }

    public static Value percentWidth(final float f) {
        return new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.7
            @Override // com.prineside.tdi2.scene2d.ui.Value
            public float get(@Null Actor actor) {
                return actor.getWidth() * f;
            }
        };
    }

    public static Value percentHeight(final float f) {
        return new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.8
            @Override // com.prineside.tdi2.scene2d.ui.Value
            public float get(@Null Actor actor) {
                return actor.getHeight() * f;
            }
        };
    }

    public static Value percentWidth(final float f, final Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        return new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.9
            @Override // com.prineside.tdi2.scene2d.ui.Value
            public float get(@Null Actor actor2) {
                return Actor.this.getWidth() * f;
            }
        };
    }

    public static Value percentHeight(final float f, final Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        return new Value() { // from class: com.prineside.tdi2.scene2d.ui.Value.10
            @Override // com.prineside.tdi2.scene2d.ui.Value
            public float get(@Null Actor actor2) {
                return Actor.this.getHeight() * f;
            }
        };
    }
}
