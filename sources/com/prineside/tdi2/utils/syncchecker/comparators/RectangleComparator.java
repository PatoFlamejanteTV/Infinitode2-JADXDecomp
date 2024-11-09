package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.math.Rectangle;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/RectangleComparator.class */
public final class RectangleComparator extends DeepClassComparator<Rectangle> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<Rectangle> forClass() {
        return Rectangle.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(Rectangle rectangle, Rectangle rectangle2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (rectangle.x != rectangle2.x) {
            deepClassComparisonConfig.appendPrefix().append(".x: ").append(rectangle.x).append(" != ").append(rectangle2.x).append(SequenceUtils.EOL);
        }
        if (rectangle.y != rectangle2.y) {
            deepClassComparisonConfig.appendPrefix().append(".y: ").append(rectangle.y).append(" != ").append(rectangle2.y).append(SequenceUtils.EOL);
        }
        if (rectangle.width != rectangle2.width) {
            deepClassComparisonConfig.appendPrefix().append(".width: ").append(rectangle.width).append(" != ").append(rectangle2.width).append(SequenceUtils.EOL);
        }
        if (rectangle.height != rectangle2.height) {
            deepClassComparisonConfig.appendPrefix().append(".height: ").append(rectangle.height).append(" != ").append(rectangle2.height).append(SequenceUtils.EOL);
        }
    }
}
