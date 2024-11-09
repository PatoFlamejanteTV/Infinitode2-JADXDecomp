package com.vladsch.flexmark.util.options;

import java.text.MessageFormat;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/MessageProvider.class */
public interface MessageProvider {
    public static final MessageProvider DEFAULT = (str, str2, objArr) -> {
        return (objArr.length <= 0 || str2.indexOf(123) < 0) ? str2 : MessageFormat.format(str2, objArr);
    };

    String message(String str, String str2, Object... objArr);
}
