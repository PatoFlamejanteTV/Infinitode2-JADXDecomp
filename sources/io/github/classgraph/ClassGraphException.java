package io.github.classgraph;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraphException.class */
public class ClassGraphException extends IllegalArgumentException {
    static final long serialVersionUID = 1;

    ClassGraphException(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassGraphException(String str, Throwable th) {
        super(str, th);
    }
}
