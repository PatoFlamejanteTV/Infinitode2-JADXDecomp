package com.google.common.base;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/JdkPattern.class */
final class JdkPattern extends CommonPattern implements Serializable {
    private final Pattern pattern;
    private static final long serialVersionUID = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkPattern(Pattern pattern) {
        this.pattern = (Pattern) Preconditions.checkNotNull(pattern);
    }

    @Override // com.google.common.base.CommonPattern
    public final CommonMatcher matcher(CharSequence charSequence) {
        return new JdkMatcher(this.pattern.matcher(charSequence));
    }

    @Override // com.google.common.base.CommonPattern
    public final String pattern() {
        return this.pattern.pattern();
    }

    @Override // com.google.common.base.CommonPattern
    public final int flags() {
        return this.pattern.flags();
    }

    @Override // com.google.common.base.CommonPattern
    public final String toString() {
        return this.pattern.toString();
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/JdkPattern$JdkMatcher.class */
    private static final class JdkMatcher extends CommonMatcher {
        final Matcher matcher;

        JdkMatcher(Matcher matcher) {
            this.matcher = (Matcher) Preconditions.checkNotNull(matcher);
        }

        @Override // com.google.common.base.CommonMatcher
        public final boolean matches() {
            return this.matcher.matches();
        }

        @Override // com.google.common.base.CommonMatcher
        public final boolean find() {
            return this.matcher.find();
        }

        @Override // com.google.common.base.CommonMatcher
        public final boolean find(int i) {
            return this.matcher.find(i);
        }

        @Override // com.google.common.base.CommonMatcher
        public final String replaceAll(String str) {
            return this.matcher.replaceAll(str);
        }

        @Override // com.google.common.base.CommonMatcher
        public final int end() {
            return this.matcher.end();
        }

        @Override // com.google.common.base.CommonMatcher
        public final int start() {
            return this.matcher.start();
        }
    }
}
