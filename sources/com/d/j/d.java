package com.d.j;

import com.d.m.l;
import java.util.logging.Level;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/* loaded from: infinitode-2.jar:com/d/j/d.class */
class d implements ErrorHandler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar) {
    }

    @Override // org.xml.sax.ErrorHandler
    public final void error(SAXParseException sAXParseException) {
        if (l.b()) {
            l.b(Level.WARNING, sAXParseException.getMessage());
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public final void fatalError(SAXParseException sAXParseException) {
        if (l.b()) {
            l.b(Level.WARNING, sAXParseException.getMessage());
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public final void warning(SAXParseException sAXParseException) {
        if (l.b()) {
            l.b(Level.WARNING, sAXParseException.getMessage());
        }
    }
}
