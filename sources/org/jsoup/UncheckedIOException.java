package org.jsoup;

import java.io.IOException;

/* loaded from: infinitode-2.jar:org/jsoup/UncheckedIOException.class */
public class UncheckedIOException extends java.io.UncheckedIOException {
    public UncheckedIOException(IOException iOException) {
        super(iOException);
    }

    public UncheckedIOException(String str) {
        super(new IOException(str));
    }

    public IOException ioException() {
        return getCause();
    }
}
