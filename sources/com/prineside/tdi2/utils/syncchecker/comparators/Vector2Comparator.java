package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/Vector2Comparator.class */
public final class Vector2Comparator extends DeepClassComparator<Vector2> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<Vector2> forClass() {
        return Vector2.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(Vector2 vector2, Vector2 vector22, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (vector2.x != vector22.x) {
            deepClassComparisonConfig.appendPrefix().append(".x: ").append(vector2.x).append(" != ").append(vector22.x).append(SequenceUtils.EOL);
        }
        if (vector2.y != vector22.y) {
            deepClassComparisonConfig.appendPrefix().append(".y: ").append(vector2.y).append(" != ").append(vector22.y).append(SequenceUtils.EOL);
        }
    }
}
