package com.prineside.tdi2;

import com.prineside.tdi2.enums.GameValueType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/GameValueProvider.class */
public interface GameValueProvider {
    double getValue(GameValueType gameValueType);

    float getFloatValue(GameValueType gameValueType);

    boolean getBooleanValue(GameValueType gameValueType);

    int getIntValue(GameValueType gameValueType);

    int getIntValueSum(GameValueType gameValueType, GameValueType gameValueType2);

    float getFloatValueSum(GameValueType gameValueType, GameValueType gameValueType2);

    double getPercentValueAsMultiplier(GameValueType gameValueType);

    double getPercentValueAsMultiplierSum(GameValueType gameValueType, GameValueType gameValueType2);

    double getPercentValueAsMultiplierSumAll(GameValueType[] gameValueTypeArr);
}
