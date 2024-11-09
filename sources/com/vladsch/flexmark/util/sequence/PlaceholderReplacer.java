package com.vladsch.flexmark.util.sequence;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/PlaceholderReplacer.class */
public class PlaceholderReplacer {
    public static <T> void replaceAll(Collection<T> collection, Function<String, String> function, char c, char c2, Function<T, String> function2, BiConsumer<T, String> biConsumer) {
        if (collection.isEmpty()) {
            return;
        }
        StringBuilder sb = null;
        for (T t : collection) {
            String apply = function2.apply(t);
            int length = apply.length();
            int i = 0;
            StringBuilder sb2 = null;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (sb == null) {
                    int indexOf = apply.indexOf(c, i);
                    if (indexOf == -1) {
                        if (i > 0) {
                            if (sb2 != null) {
                                sb2.append(apply.substring(i));
                            } else {
                                biConsumer.accept(t, apply.substring(i));
                            }
                        }
                    } else {
                        sb = new StringBuilder();
                        if (i < indexOf) {
                            if (sb2 == null) {
                                sb2 = new StringBuilder();
                            }
                            sb2.append(apply.substring(i, indexOf));
                        }
                        int i2 = indexOf + 1;
                        i = i2;
                        if (i2 >= length && sb2 == null) {
                            biConsumer.accept(t, "");
                        }
                    }
                } else {
                    int indexOf2 = apply.indexOf(c2, i);
                    if (indexOf2 == -1) {
                        sb.append(apply.substring(i));
                        if (sb2 == null) {
                            biConsumer.accept(t, "");
                        }
                        i = length;
                    } else {
                        sb.append(apply.substring(i, indexOf2));
                        i = indexOf2 + 1;
                        String sb3 = sb.toString();
                        String apply2 = function.apply(sb3);
                        sb = null;
                        if (apply2 == null) {
                            apply2 = c + sb3 + c2;
                        }
                        if (sb2 == null) {
                            sb2 = new StringBuilder();
                        }
                        sb2.append(apply2);
                    }
                }
            }
            if (sb2 != null) {
                biConsumer.accept(t, sb2.toString());
            }
        }
    }
}
