package org.jsoup.helper;

/* loaded from: infinitode-2.jar:org/jsoup/helper/Validate.class */
public final class Validate {
    private Validate() {
    }

    public static void notNull(Object obj) {
        if (obj == null) {
            throw new ValidationException("Object must not be null");
        }
    }

    public static void notNullParam(Object obj, String str) {
        if (obj == null) {
            throw new ValidationException(String.format("The parameter '%s' must not be null.", str));
        }
    }

    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new ValidationException(str);
        }
    }

    public static Object ensureNotNull(Object obj) {
        if (obj == null) {
            throw new ValidationException("Object must not be null");
        }
        return obj;
    }

    public static Object ensureNotNull(Object obj, String str, Object... objArr) {
        if (obj == null) {
            throw new ValidationException(String.format(str, objArr));
        }
        return obj;
    }

    public static void isTrue(boolean z) {
        if (!z) {
            throw new ValidationException("Must be true");
        }
    }

    public static void isTrue(boolean z, String str) {
        if (!z) {
            throw new ValidationException(str);
        }
    }

    public static void isFalse(boolean z) {
        if (z) {
            throw new ValidationException("Must be false");
        }
    }

    public static void isFalse(boolean z, String str) {
        if (z) {
            throw new ValidationException(str);
        }
    }

    public static void noNullElements(Object[] objArr) {
        noNullElements(objArr, "Array must not contain any null objects");
    }

    public static void noNullElements(Object[] objArr, String str) {
        for (Object obj : objArr) {
            if (obj == null) {
                throw new ValidationException(str);
            }
        }
    }

    public static void notEmpty(String str) {
        if (str == null || str.length() == 0) {
            throw new ValidationException("String must not be empty");
        }
    }

    public static void notEmptyParam(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new ValidationException(String.format("The '%s' parameter must not be empty.", str2));
        }
    }

    public static void notEmpty(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new ValidationException(str2);
        }
    }

    public static void wtf(String str) {
        throw new IllegalStateException(str);
    }

    public static void fail(String str) {
        throw new ValidationException(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean assertFail(String str) {
        fail(str);
        return false;
    }

    public static void fail(String str, Object... objArr) {
        throw new ValidationException(String.format(str, objArr));
    }
}
