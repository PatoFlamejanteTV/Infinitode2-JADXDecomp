package com.google.common.base;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/MoreObjects.class */
public final class MoreObjects {
    public static <T> T firstNonNull(T t, T t2) {
        if (t != null) {
            return t;
        }
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(cls.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/MoreObjects$ToStringHelper.class */
    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitNullValues;
        private boolean omitEmptyValues;

        private ToStringHelper(String str) {
            this.holderHead = new ValueHolder();
            this.holderTail = this.holderHead;
            this.omitNullValues = false;
            this.omitEmptyValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        public final ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public final ToStringHelper add(String str, Object obj) {
            return addHolder(str, obj);
        }

        public final ToStringHelper add(String str, boolean z) {
            return addUnconditionalHolder(str, String.valueOf(z));
        }

        public final ToStringHelper add(String str, char c) {
            return addUnconditionalHolder(str, String.valueOf(c));
        }

        public final ToStringHelper add(String str, double d) {
            return addUnconditionalHolder(str, String.valueOf(d));
        }

        public final ToStringHelper add(String str, float f) {
            return addUnconditionalHolder(str, String.valueOf(f));
        }

        public final ToStringHelper add(String str, int i) {
            return addUnconditionalHolder(str, String.valueOf(i));
        }

        public final ToStringHelper add(String str, long j) {
            return addUnconditionalHolder(str, String.valueOf(j));
        }

        public final ToStringHelper addValue(Object obj) {
            return addHolder(obj);
        }

        public final ToStringHelper addValue(boolean z) {
            return addUnconditionalHolder(String.valueOf(z));
        }

        public final ToStringHelper addValue(char c) {
            return addUnconditionalHolder(String.valueOf(c));
        }

        public final ToStringHelper addValue(double d) {
            return addUnconditionalHolder(String.valueOf(d));
        }

        public final ToStringHelper addValue(float f) {
            return addUnconditionalHolder(String.valueOf(f));
        }

        public final ToStringHelper addValue(int i) {
            return addUnconditionalHolder(String.valueOf(i));
        }

        public final ToStringHelper addValue(long j) {
            return addUnconditionalHolder(String.valueOf(j));
        }

        private static boolean isEmpty(Object obj) {
            if (obj instanceof CharSequence) {
                return ((CharSequence) obj).length() == 0;
            }
            if (obj instanceof Collection) {
                return ((Collection) obj).isEmpty();
            }
            if (obj instanceof Map) {
                return ((Map) obj).isEmpty();
            }
            return obj instanceof Optional ? !((Optional) obj).isPresent() : obj.getClass().isArray() && Array.getLength(obj) == 0;
        }

        public final String toString() {
            boolean z = this.omitNullValues;
            boolean z2 = this.omitEmptyValues;
            String str = "";
            StringBuilder append = new StringBuilder(32).append(this.className).append('{');
            ValueHolder valueHolder = this.holderHead.next;
            while (true) {
                ValueHolder valueHolder2 = valueHolder;
                if (valueHolder2 != null) {
                    Object obj = valueHolder2.value;
                    if (!(valueHolder2 instanceof UnconditionalValueHolder)) {
                        if (obj == null) {
                            if (z) {
                            }
                        } else if (z2 && isEmpty(obj)) {
                        }
                        valueHolder = valueHolder2.next;
                    }
                    append.append(str);
                    str = ", ";
                    if (valueHolder2.name != null) {
                        append.append(valueHolder2.name).append('=');
                    }
                    if (obj != null && obj.getClass().isArray()) {
                        String deepToString = Arrays.deepToString(new Object[]{obj});
                        append.append((CharSequence) deepToString, 1, deepToString.length() - 1);
                    } else {
                        append.append(obj);
                    }
                    valueHolder = valueHolder2.next;
                } else {
                    return append.append('}').toString();
                }
            }
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        private ToStringHelper addHolder(Object obj) {
            addHolder().value = obj;
            return this;
        }

        private ToStringHelper addHolder(String str, Object obj) {
            ValueHolder addHolder = addHolder();
            addHolder.value = obj;
            addHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        private UnconditionalValueHolder addUnconditionalHolder() {
            UnconditionalValueHolder unconditionalValueHolder = new UnconditionalValueHolder();
            this.holderTail.next = unconditionalValueHolder;
            this.holderTail = unconditionalValueHolder;
            return unconditionalValueHolder;
        }

        private ToStringHelper addUnconditionalHolder(Object obj) {
            addUnconditionalHolder().value = obj;
            return this;
        }

        private ToStringHelper addUnconditionalHolder(String str, Object obj) {
            UnconditionalValueHolder addUnconditionalHolder = addUnconditionalHolder();
            addUnconditionalHolder.value = obj;
            addUnconditionalHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/google/common/base/MoreObjects$ToStringHelper$ValueHolder.class */
        public static class ValueHolder {
            String name;
            Object value;
            ValueHolder next;

            private ValueHolder() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/google/common/base/MoreObjects$ToStringHelper$UnconditionalValueHolder.class */
        public static final class UnconditionalValueHolder extends ValueHolder {
            private UnconditionalValueHolder() {
                super();
            }
        }
    }

    private MoreObjects() {
    }
}
