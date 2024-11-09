package com.vladsch.flexmark.util.sequence.mappers;

import java.util.Objects;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/CharMapper.class */
public interface CharMapper {
    public static final CharMapper IDENTITY = c -> {
        return c;
    };

    char map(char c);

    default CharMapper compose(CharMapper charMapper) {
        Objects.requireNonNull(charMapper);
        return charMapper == IDENTITY ? this : c -> {
            return map(charMapper.map(c));
        };
    }

    default CharMapper andThen(CharMapper charMapper) {
        Objects.requireNonNull(charMapper);
        return charMapper == IDENTITY ? this : c -> {
            return charMapper.map(map(c));
        };
    }

    static CharMapper identity() {
        return IDENTITY;
    }
}
