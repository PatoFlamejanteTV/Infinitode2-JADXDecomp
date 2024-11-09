package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedUtils.class */
public interface BasedUtils {
    static {
        boolean z = AnonymousClass1.$assertionsDisabled;
    }

    static void generateSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder, BasedSequence basedSequence) {
        int i = -1;
        int i2 = -1;
        boolean z = false;
        StringBuilder sb = null;
        int length = basedSequence.length();
        for (int i3 = 0; i3 < length; i3++) {
            int indexOffset = basedSequence.getIndexOffset(i3);
            if (indexOffset >= 0) {
                if (i == -1) {
                    if (sb != null) {
                        if (!z) {
                            iBasedSegmentBuilder.appendAnchor(basedSequence.getStartOffset());
                            z = true;
                        }
                        iBasedSegmentBuilder.append(sb.toString());
                        sb = null;
                    }
                    i = indexOffset;
                } else if (indexOffset > i2 + 1) {
                    iBasedSegmentBuilder.append(i, i2 + 1);
                    i = indexOffset;
                }
                i2 = indexOffset;
            } else {
                if (i != -1) {
                    iBasedSegmentBuilder.append(i, i2 + 1);
                    i = -1;
                    i2 = -1;
                    z = true;
                }
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(basedSequence.charAt(i3));
            }
        }
        if (i != -1) {
            iBasedSegmentBuilder.append(i, i2 + 1);
            z = true;
        }
        if (sb != null) {
            if (!z) {
                iBasedSegmentBuilder.appendAnchor(basedSequence.getStartOffset());
                z = true;
            }
            iBasedSegmentBuilder.append(sb.toString());
            iBasedSegmentBuilder.appendAnchor(basedSequence.getEndOffset());
        }
        if (!z) {
            if (!AnonymousClass1.$assertionsDisabled && basedSequence.length() != 0) {
                throw new AssertionError();
            }
            iBasedSegmentBuilder.appendAnchor(basedSequence.getStartOffset());
        }
    }

    /* renamed from: com.vladsch.flexmark.util.sequence.BasedUtils$1, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedUtils$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !BasedUtils.class.desiredAssertionStatus();
        }
    }

    static BasedSequence asBased(CharSequence charSequence) {
        return BasedSequence.of(charSequence);
    }
}
