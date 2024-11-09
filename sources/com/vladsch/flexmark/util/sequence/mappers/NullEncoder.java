package com.vladsch.flexmark.util.sequence.mappers;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/NullEncoder.class */
public class NullEncoder {
    public static final CharMapper encodeNull = new EncodeNull();
    public static final CharMapper decodeNull = new DecodeNull();

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/NullEncoder$DecodeNull.class */
    private static class DecodeNull implements CharMapper {
        DecodeNull() {
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.CharMapper
        public char map(char c) {
            if (c == 65533) {
                return (char) 0;
            }
            return c;
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/NullEncoder$EncodeNull.class */
    private static class EncodeNull implements CharMapper {
        EncodeNull() {
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.CharMapper
        public char map(char c) {
            if (c == 0) {
                return (char) 65533;
            }
            return c;
        }
    }
}
