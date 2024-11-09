package com.a.a.c.e;

import com.a.a.b.l;
import com.a.a.b.o;
import com.a.a.c.c.b.ai;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.Iterator;
import java.util.ServiceLoader;

/* loaded from: infinitode-2.jar:com/a/a/c/e/e.class */
public final class e extends ai<Path> {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f422a;

    static {
        boolean z = false;
        File[] listRoots = File.listRoots();
        int length = listRoots.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String path = listRoots[i].getPath();
            if (path.length() < 2 || !Character.isLetter(path.charAt(0)) || path.charAt(1) != ':') {
                i++;
            } else {
                z = true;
                break;
            }
        }
        f422a = z;
    }

    public e() {
        super((Class<?>) Path.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.nio.file.spi.FileSystemProvider] */
    /* JADX WARN: Type inference failed for: r0v35, types: [java.nio.file.Path] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.net.URI] */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Path a(l lVar, com.a.a.c.g gVar) {
        if (!lVar.a(o.VALUE_STRING)) {
            return (Path) gVar.a(Path.class, lVar);
        }
        String w = lVar.w();
        if (w.indexOf(58) < 0) {
            return Paths.get(w, new String[0]);
        }
        if (!f422a || w.length() < 2 || !Character.isLetter(w.charAt(0)) || w.charAt(1) != ':') {
            try {
                ?? uri = new URI(w);
                try {
                    uri = Paths.get(uri);
                    return uri;
                } catch (FileSystemNotFoundException e) {
                    try {
                        String scheme = uri.getScheme();
                        Iterator it = ServiceLoader.load(FileSystemProvider.class).iterator();
                        while (it.hasNext()) {
                            ?? r0 = (FileSystemProvider) it.next();
                            if (r0.getScheme().equalsIgnoreCase(scheme)) {
                                return r0.getPath(uri);
                            }
                        }
                        return (Path) gVar.a(a(), w, e);
                    } catch (Throwable th) {
                        uri.addSuppressed(e);
                        return (Path) gVar.a(a(), w, th);
                    }
                } catch (Throwable th2) {
                    return (Path) gVar.a(a(), w, th2);
                }
            } catch (URISyntaxException e2) {
                return (Path) gVar.a(a(), w, e2);
            }
        }
        return Paths.get(w, new String[0]);
    }
}
