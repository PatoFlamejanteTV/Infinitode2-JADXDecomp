package com.vladsch.flexmark.util.sequence.mappers;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/ChangeCase.class */
public class ChangeCase {
    public static final CharMapper toUpperCase = new ToUpperCase();
    public static final CharMapper toLowerCase = new ToLowerCase();

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/ChangeCase$ToLowerCase.class */
    private static class ToLowerCase implements CharMapper {
        ToLowerCase() {
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.CharMapper
        public char map(char c) {
            return Character.isUpperCase(c) ? Character.toLowerCase(c) : c;
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/ChangeCase$ToUpperCase.class */
    private static class ToUpperCase implements CharMapper {
        ToUpperCase() {
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.CharMapper
        public char map(char c) {
            return Character.isLowerCase(c) ? Character.toUpperCase(c) : c;
        }
    }
}
