package com.a.a.b.d;

import com.a.a.b.l;

/* loaded from: infinitode-2.jar:com/a/a/b/d/e.class */
public enum e implements com.a.a.b.c {
    ALLOW_JAVA_COMMENTS(false, l.a.ALLOW_COMMENTS),
    ALLOW_YAML_COMMENTS(false, l.a.ALLOW_YAML_COMMENTS),
    ALLOW_SINGLE_QUOTES(false, l.a.ALLOW_SINGLE_QUOTES),
    ALLOW_UNQUOTED_FIELD_NAMES(false, l.a.ALLOW_UNQUOTED_FIELD_NAMES),
    ALLOW_UNESCAPED_CONTROL_CHARS(false, l.a.ALLOW_UNQUOTED_CONTROL_CHARS),
    ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false, l.a.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER),
    ALLOW_LEADING_ZEROS_FOR_NUMBERS(false, l.a.ALLOW_NUMERIC_LEADING_ZEROS),
    ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS(false, l.a.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS),
    ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS(false, l.a.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS),
    ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS(false, l.a.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS),
    ALLOW_NON_NUMERIC_NUMBERS(false, l.a.ALLOW_NON_NUMERIC_NUMBERS),
    ALLOW_MISSING_VALUES(false, l.a.ALLOW_MISSING_VALUES),
    ALLOW_TRAILING_COMMA(false, l.a.ALLOW_TRAILING_COMMA);

    private final boolean n = false;
    private final int o = 1 << ordinal();
    private final l.a p;

    e(boolean z, l.a aVar) {
        this.p = aVar;
    }

    @Override // com.a.a.b.c, com.a.a.b.g.h
    public final boolean a() {
        return this.n;
    }

    @Override // com.a.a.b.c, com.a.a.b.g.h
    public final int b() {
        return this.o;
    }

    @Override // com.a.a.b.g.h
    public final boolean a(int i) {
        return (i & this.o) != 0;
    }

    public final l.a c() {
        return this.p;
    }
}
