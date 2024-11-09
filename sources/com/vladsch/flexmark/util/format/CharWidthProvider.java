package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.misc.CharPredicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/CharWidthProvider.class */
public interface CharWidthProvider {
    public static final CharWidthProvider NULL = new CharWidthProvider() { // from class: com.vladsch.flexmark.util.format.CharWidthProvider.1
        @Override // com.vladsch.flexmark.util.format.CharWidthProvider
        public final int getSpaceWidth() {
            return 1;
        }

        @Override // com.vladsch.flexmark.util.format.CharWidthProvider
        public final int getCharWidth(char c) {
            return 1;
        }
    };

    int getSpaceWidth();

    int getCharWidth(char c);

    default int getStringWidth(CharSequence charSequence) {
        return getStringWidth(charSequence, CharPredicate.NONE);
    }

    default int getStringWidth(CharSequence charSequence, CharPredicate charPredicate) {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i2);
            if (!charPredicate.test(charAt)) {
                i += getCharWidth(charAt);
            }
        }
        return i;
    }
}
