package com.vladsch.flexmark.util.sequence.mappers;

import com.vladsch.flexmark.util.misc.CharPredicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/SpaceMapper.class */
public class SpaceMapper {
    public static final CharMapper toNonBreakSpace = new ToNonBreakSpace();
    public static final CharMapper fromNonBreakSpace = new FromNonBreakSpace();

    public static boolean areEquivalent(char c, char c2) {
        if (c == c2) {
            return true;
        }
        if (c == ' ' && c2 == 160) {
            return true;
        }
        return c2 == ' ' && c == 160;
    }

    public static CharMapper toSpaces(CharPredicate charPredicate) {
        return new FromPredicate(charPredicate);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/SpaceMapper$FromNonBreakSpace.class */
    private static class FromNonBreakSpace implements CharMapper {
        FromNonBreakSpace() {
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.CharMapper
        public char map(char c) {
            if (c == 160) {
                return ' ';
            }
            return c;
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/SpaceMapper$FromPredicate.class */
    private static class FromPredicate implements CharMapper {
        final CharPredicate myPredicate;

        FromPredicate(CharPredicate charPredicate) {
            this.myPredicate = charPredicate;
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.CharMapper
        public char map(char c) {
            if (this.myPredicate.test(c)) {
                return ' ';
            }
            return c;
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/SpaceMapper$ToNonBreakSpace.class */
    private static class ToNonBreakSpace implements CharMapper {
        ToNonBreakSpace() {
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.CharMapper
        public char map(char c) {
            if (c == ' ') {
                return (char) 160;
            }
            return c;
        }
    }
}
