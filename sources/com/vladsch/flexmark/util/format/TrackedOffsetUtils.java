package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.LineInfo;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.tree.BasedOffsetTracker;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TrackedOffsetUtils.class */
public class TrackedOffsetUtils {
    public static void resolveTrackedOffsets(BasedSequence basedSequence, LineAppendable lineAppendable, List<TrackedOffset> list, int i, boolean z) {
        if (!list.isEmpty()) {
            TrackedOffsetList create = TrackedOffsetList.create(basedSequence, list);
            int size = create.size();
            int i2 = 0;
            ISequenceBuilder<?, ?> builder = lineAppendable.getBuilder();
            BasedSequence baseSequence = builder instanceof SequenceBuilder ? ((SequenceBuilder) builder).getBaseSequence() : basedSequence.getBaseSequence();
            for (LineInfo lineInfo : lineAppendable.getLinesInfo(i, 0, lineAppendable.getLineCount())) {
                BasedSequence line = lineInfo.getLine();
                TrackedOffsetList trackedOffsets = create.getTrackedOffsets(line.getStartOffset(), line.getEndOffset());
                if (!trackedOffsets.isEmpty()) {
                    for (TrackedOffset trackedOffset : trackedOffsets) {
                        BasedOffsetTracker create2 = BasedOffsetTracker.create(line);
                        if (!trackedOffset.isResolved()) {
                            int offset = trackedOffset.getOffset();
                            boolean isCharAt = baseSequence.isCharAt(offset, CharPredicate.WHITESPACE);
                            if (!isCharAt || baseSequence.isCharAt(offset - 1, CharPredicate.WHITESPACE)) {
                                if (!isCharAt && baseSequence.isCharAt(offset + 1, CharPredicate.WHITESPACE)) {
                                    trackedOffset.setIndex(create2.getOffsetInfo(offset, false).startIndex + i2);
                                } else {
                                    trackedOffset.setIndex(create2.getOffsetInfo(offset, true).endIndex + i2);
                                }
                            } else {
                                trackedOffset.setIndex(create2.getOffsetInfo(offset - 1, false).endIndex + i2);
                            }
                            if (z) {
                                System.out.println(String.format("Resolved %d to %d, start: %d, in line[%d]: '%s'", Integer.valueOf(offset), Integer.valueOf(trackedOffset.getIndex()), Integer.valueOf(i2), Integer.valueOf(lineInfo.index), line.getBuilder().append((CharSequence) line).toStringWithRanges(true)));
                            }
                            size--;
                        }
                    }
                }
                i2 += line.length();
                if (size <= 0) {
                    return;
                }
            }
        }
    }
}
