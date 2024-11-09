package com.vladsch.flexmark.util.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/TemplateUtil.class */
public class TemplateUtil {
    public static final Resolver NULL_RESOLVER = strArr -> {
        return null;
    };

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/TemplateUtil$Resolver.class */
    public interface Resolver {
        String resolve(String[] strArr);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/TemplateUtil$MappedResolver.class */
    public static class MappedResolver implements Resolver {
        protected final Map<String, String> resolved;

        public MappedResolver(Map<String, String> map) {
            this.resolved = map;
        }

        public MappedResolver() {
            this(new HashMap());
        }

        public MappedResolver set(String str, String str2) {
            this.resolved.put(str, str2);
            return this;
        }

        public Map<String, String> getMMap() {
            return this.resolved;
        }

        @Override // com.vladsch.flexmark.util.misc.TemplateUtil.Resolver
        public String resolve(String[] strArr) {
            if (strArr.length > 2) {
                return null;
            }
            return this.resolved.get(strArr[1]);
        }
    }

    public static String resolveRefs(CharSequence charSequence, Pattern pattern, Resolver resolver) {
        if (charSequence == null) {
            return "";
        }
        Matcher matcher = pattern.matcher(charSequence);
        if (matcher.find()) {
            StringBuffer stringBuffer = new StringBuffer();
            do {
                String[] strArr = new String[matcher.groupCount() + 1];
                for (int i = 0; i < strArr.length; i++) {
                    strArr[i] = matcher.group(i);
                }
                String resolve = resolver.resolve(strArr);
                matcher.appendReplacement(stringBuffer, resolve == null ? "" : resolve.replace("\\", "\\\\").replace("$", "\\$"));
            } while (matcher.find());
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }
        return charSequence.toString();
    }
}
