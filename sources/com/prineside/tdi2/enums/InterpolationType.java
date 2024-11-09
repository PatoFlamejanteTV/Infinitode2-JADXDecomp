package com.prineside.tdi2.enums;

import com.badlogic.gdx.math.Interpolation;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/InterpolationType.class */
public enum InterpolationType {
    linear,
    smooth,
    smooth2,
    smoother,
    fade,
    pow2,
    slowFast,
    fastSlow,
    pow2InInverse,
    pow2OutInverse,
    pow3,
    pow3In,
    pow3Out,
    pow3InInverse,
    pow3OutInverse,
    pow4,
    pow4In,
    pow4Out,
    pow5,
    pow5In,
    pow5Out,
    sine,
    sineIn,
    sineOut,
    exp10,
    exp10In,
    exp10Out,
    exp5,
    exp5In,
    exp5Out,
    circle,
    circleIn,
    circleOut,
    elastic,
    elasticIn,
    elasticOut,
    swing,
    swingIn,
    swingOut,
    bounce,
    bounceIn,
    bounceOut;

    public static final InterpolationType[] values;
    public static final Interpolation[] objects;

    static {
        InterpolationType[] values2 = values();
        values = values2;
        Interpolation[] interpolationArr = new Interpolation[values2.length];
        objects = interpolationArr;
        interpolationArr[linear.ordinal()] = Interpolation.linear;
        objects[smooth.ordinal()] = Interpolation.smooth;
        objects[smooth2.ordinal()] = Interpolation.smooth2;
        objects[smoother.ordinal()] = Interpolation.smoother;
        objects[fade.ordinal()] = Interpolation.fade;
        objects[pow2.ordinal()] = Interpolation.pow2;
        objects[slowFast.ordinal()] = Interpolation.slowFast;
        objects[fastSlow.ordinal()] = Interpolation.fastSlow;
        objects[pow2InInverse.ordinal()] = Interpolation.pow2InInverse;
        objects[pow2OutInverse.ordinal()] = Interpolation.pow2OutInverse;
        objects[pow3.ordinal()] = Interpolation.pow3;
        objects[pow3In.ordinal()] = Interpolation.pow3In;
        objects[pow3Out.ordinal()] = Interpolation.pow3Out;
        objects[pow3InInverse.ordinal()] = Interpolation.pow3InInverse;
        objects[pow3OutInverse.ordinal()] = Interpolation.pow3OutInverse;
        objects[pow4.ordinal()] = Interpolation.pow4;
        objects[pow4In.ordinal()] = Interpolation.pow4In;
        objects[pow4Out.ordinal()] = Interpolation.pow4Out;
        objects[pow5.ordinal()] = Interpolation.pow5;
        objects[pow5In.ordinal()] = Interpolation.pow5In;
        objects[pow5Out.ordinal()] = Interpolation.pow5Out;
        objects[sine.ordinal()] = Interpolation.sine;
        objects[sineIn.ordinal()] = Interpolation.sineIn;
        objects[sineOut.ordinal()] = Interpolation.sineOut;
        objects[exp10.ordinal()] = Interpolation.exp10;
        objects[exp10In.ordinal()] = Interpolation.exp10In;
        objects[exp10Out.ordinal()] = Interpolation.exp10Out;
        objects[exp5.ordinal()] = Interpolation.exp5;
        objects[exp5In.ordinal()] = Interpolation.exp5In;
        objects[exp5Out.ordinal()] = Interpolation.exp5Out;
        objects[circle.ordinal()] = Interpolation.circle;
        objects[circleIn.ordinal()] = Interpolation.circleIn;
        objects[circleOut.ordinal()] = Interpolation.circleOut;
        objects[elastic.ordinal()] = Interpolation.elastic;
        objects[elasticIn.ordinal()] = Interpolation.elasticIn;
        objects[elasticOut.ordinal()] = Interpolation.elasticOut;
        objects[swing.ordinal()] = Interpolation.swing;
        objects[swingIn.ordinal()] = Interpolation.swingIn;
        objects[swingOut.ordinal()] = Interpolation.swingOut;
        objects[bounce.ordinal()] = Interpolation.bounce;
        objects[bounceIn.ordinal()] = Interpolation.bounceIn;
        objects[bounceOut.ordinal()] = Interpolation.bounceOut;
    }

    public static Interpolation getObject(InterpolationType interpolationType) {
        return objects[interpolationType.ordinal()];
    }

    public static InterpolationType getType(Interpolation interpolation) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == interpolation) {
                return values[i];
            }
        }
        return null;
    }
}
