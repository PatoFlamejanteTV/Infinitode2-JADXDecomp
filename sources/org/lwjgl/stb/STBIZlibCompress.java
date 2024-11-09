package org.lwjgl.stb;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIZlibCompress.class */
public abstract class STBIZlibCompress extends Callback implements STBIZlibCompressI {
    public static STBIZlibCompress create(long j) {
        STBIZlibCompressI sTBIZlibCompressI = (STBIZlibCompressI) Callback.get(j);
        return sTBIZlibCompressI instanceof STBIZlibCompress ? (STBIZlibCompress) sTBIZlibCompressI : new Container(j, sTBIZlibCompressI);
    }

    public static STBIZlibCompress createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIZlibCompress create(STBIZlibCompressI sTBIZlibCompressI) {
        return sTBIZlibCompressI instanceof STBIZlibCompress ? (STBIZlibCompress) sTBIZlibCompressI : new Container(sTBIZlibCompressI.address(), sTBIZlibCompressI);
    }

    protected STBIZlibCompress() {
        super(CIF);
    }

    STBIZlibCompress(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIZlibCompress$Container.class */
    public static final class Container extends STBIZlibCompress {
        private final STBIZlibCompressI delegate;

        Container(long j, STBIZlibCompressI sTBIZlibCompressI) {
            super(j);
            this.delegate = sTBIZlibCompressI;
        }

        @Override // org.lwjgl.stb.STBIZlibCompressI
        public final long invoke(long j, int i, long j2, int i2) {
            return this.delegate.invoke(j, i, j2, i2);
        }
    }
}
