package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLFrameBuffer.class */
public abstract class GLFrameBuffer<T extends GLTexture> implements Disposable {
    protected static final int GL_DEPTH24_STENCIL8_OES = 35056;
    protected static int defaultFramebufferHandle;
    protected int framebufferHandle;
    protected int depthbufferHandle;
    protected int stencilbufferHandle;
    protected int depthStencilPackedBufferHandle;
    protected boolean hasDepthStencilPackedBuffer;
    protected boolean isMRT;
    protected GLFrameBufferBuilder<? extends GLFrameBuffer<T>> bufferBuilder;
    private IntBuffer defaultDrawBuffers;
    protected static final Map<Application, Array<GLFrameBuffer>> buffers = new HashMap();
    protected static boolean defaultFramebufferHandleInitialized = false;
    static final IntBuffer singleInt = BufferUtils.newIntBuffer(1);
    protected Array<T> textureAttachments = new Array<>();
    protected final IntArray colorBufferHandles = new IntArray();

    protected abstract T createTexture(FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec);

    protected abstract void disposeColorTexture(T t);

    protected abstract void attachFrameBufferColorTexture(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public GLFrameBuffer() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFrameBuffer(GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder) {
        this.bufferBuilder = gLFrameBufferBuilder;
        build();
    }

    public T getColorBufferTexture() {
        return this.textureAttachments.first();
    }

    public Array<T> getTextureAttachments() {
        return this.textureAttachments;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void build() {
        GL20 gl20 = Gdx.gl20;
        checkValidBuilder();
        if (!defaultFramebufferHandleInitialized) {
            defaultFramebufferHandleInitialized = true;
            if (Gdx.app.getType() == Application.ApplicationType.iOS) {
                IntBuffer asIntBuffer = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asIntBuffer();
                gl20.glGetIntegerv(36006, asIntBuffer);
                defaultFramebufferHandle = asIntBuffer.get(0);
            } else {
                defaultFramebufferHandle = 0;
            }
        }
        this.framebufferHandle = gl20.glGenFramebuffer();
        gl20.glBindFramebuffer(36160, this.framebufferHandle);
        int i = this.bufferBuilder.width;
        int i2 = this.bufferBuilder.height;
        if (this.bufferBuilder.hasDepthRenderBuffer) {
            this.depthbufferHandle = gl20.glGenRenderbuffer();
            gl20.glBindRenderbuffer(36161, this.depthbufferHandle);
            if (this.bufferBuilder.samples > 0) {
                Gdx.gl31.glRenderbufferStorageMultisample(36161, this.bufferBuilder.samples, this.bufferBuilder.depthRenderBufferSpec.internalFormat, i, i2);
            } else {
                gl20.glRenderbufferStorage(36161, this.bufferBuilder.depthRenderBufferSpec.internalFormat, i, i2);
            }
        }
        if (this.bufferBuilder.hasStencilRenderBuffer) {
            this.stencilbufferHandle = gl20.glGenRenderbuffer();
            gl20.glBindRenderbuffer(36161, this.stencilbufferHandle);
            if (this.bufferBuilder.samples > 0) {
                Gdx.gl31.glRenderbufferStorageMultisample(36161, this.bufferBuilder.samples, this.bufferBuilder.stencilRenderBufferSpec.internalFormat, i, i2);
            } else {
                gl20.glRenderbufferStorage(36161, this.bufferBuilder.stencilRenderBufferSpec.internalFormat, i, i2);
            }
        }
        if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
            this.depthStencilPackedBufferHandle = gl20.glGenRenderbuffer();
            gl20.glBindRenderbuffer(36161, this.depthStencilPackedBufferHandle);
            if (this.bufferBuilder.samples > 0) {
                Gdx.gl31.glRenderbufferStorageMultisample(36161, this.bufferBuilder.samples, this.bufferBuilder.packedStencilDepthRenderBufferSpec.internalFormat, i, i2);
            } else {
                gl20.glRenderbufferStorage(36161, this.bufferBuilder.packedStencilDepthRenderBufferSpec.internalFormat, i, i2);
            }
            this.hasDepthStencilPackedBuffer = true;
        }
        this.isMRT = this.bufferBuilder.textureAttachmentSpecs.size > 1;
        int i3 = 0;
        if (this.isMRT) {
            Array.ArrayIterator<FrameBufferTextureAttachmentSpec> it = this.bufferBuilder.textureAttachmentSpecs.iterator();
            while (it.hasNext()) {
                FrameBufferTextureAttachmentSpec next = it.next();
                T createTexture = createTexture(next);
                this.textureAttachments.add(createTexture);
                if (next.isColorTexture()) {
                    gl20.glFramebufferTexture2D(36160, i3 + 36064, 3553, createTexture.getTextureObjectHandle(), 0);
                    i3++;
                } else if (next.isDepth) {
                    gl20.glFramebufferTexture2D(36160, 36096, 3553, createTexture.getTextureObjectHandle(), 0);
                } else if (next.isStencil) {
                    gl20.glFramebufferTexture2D(36160, 36128, 3553, createTexture.getTextureObjectHandle(), 0);
                }
            }
        } else if (this.bufferBuilder.textureAttachmentSpecs.size > 0) {
            T createTexture2 = createTexture(this.bufferBuilder.textureAttachmentSpecs.first());
            this.textureAttachments.add(createTexture2);
            gl20.glBindTexture(createTexture2.glTarget, createTexture2.getTextureObjectHandle());
        }
        Array.ArrayIterator<FrameBufferRenderBufferAttachmentSpec> it2 = this.bufferBuilder.colorRenderBufferSpecs.iterator();
        while (it2.hasNext()) {
            FrameBufferRenderBufferAttachmentSpec next2 = it2.next();
            int glGenRenderbuffer = gl20.glGenRenderbuffer();
            gl20.glBindRenderbuffer(36161, glGenRenderbuffer);
            if (this.bufferBuilder.samples > 0) {
                Gdx.gl31.glRenderbufferStorageMultisample(36161, this.bufferBuilder.samples, next2.internalFormat, i, i2);
            } else {
                gl20.glRenderbufferStorage(36161, next2.internalFormat, i, i2);
            }
            Gdx.gl.glFramebufferRenderbuffer(36160, i3 + 36064, 36161, glGenRenderbuffer);
            this.colorBufferHandles.add(glGenRenderbuffer);
            i3++;
        }
        if (this.isMRT || this.bufferBuilder.samples > 0) {
            this.defaultDrawBuffers = BufferUtils.newIntBuffer(i3);
            for (int i4 = 0; i4 < i3; i4++) {
                this.defaultDrawBuffers.put(i4 + 36064);
            }
            this.defaultDrawBuffers.position(0);
            Gdx.gl30.glDrawBuffers(i3, this.defaultDrawBuffers);
        } else if (this.bufferBuilder.textureAttachmentSpecs.size > 0) {
            attachFrameBufferColorTexture(this.textureAttachments.first());
        }
        if (this.bufferBuilder.hasDepthRenderBuffer) {
            gl20.glFramebufferRenderbuffer(36160, 36096, 36161, this.depthbufferHandle);
        }
        if (this.bufferBuilder.hasStencilRenderBuffer) {
            gl20.glFramebufferRenderbuffer(36160, 36128, 36161, this.stencilbufferHandle);
        }
        if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
            gl20.glFramebufferRenderbuffer(36160, 33306, 36161, this.depthStencilPackedBufferHandle);
        }
        gl20.glBindRenderbuffer(36161, 0);
        Array.ArrayIterator<T> it3 = this.textureAttachments.iterator();
        while (it3.hasNext()) {
            gl20.glBindTexture(it3.next().glTarget, 0);
        }
        int glCheckFramebufferStatus = gl20.glCheckFramebufferStatus(36160);
        int i5 = glCheckFramebufferStatus;
        if (glCheckFramebufferStatus == 36061 && this.bufferBuilder.hasDepthRenderBuffer && this.bufferBuilder.hasStencilRenderBuffer && (Gdx.graphics.supportsExtension("GL_OES_packed_depth_stencil") || Gdx.graphics.supportsExtension("GL_EXT_packed_depth_stencil"))) {
            if (this.bufferBuilder.hasDepthRenderBuffer) {
                gl20.glDeleteRenderbuffer(this.depthbufferHandle);
                this.depthbufferHandle = 0;
            }
            if (this.bufferBuilder.hasStencilRenderBuffer) {
                gl20.glDeleteRenderbuffer(this.stencilbufferHandle);
                this.stencilbufferHandle = 0;
            }
            if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
                gl20.glDeleteRenderbuffer(this.depthStencilPackedBufferHandle);
                this.depthStencilPackedBufferHandle = 0;
            }
            this.depthStencilPackedBufferHandle = gl20.glGenRenderbuffer();
            this.hasDepthStencilPackedBuffer = true;
            gl20.glBindRenderbuffer(36161, this.depthStencilPackedBufferHandle);
            if (this.bufferBuilder.samples > 0) {
                Gdx.gl31.glRenderbufferStorageMultisample(36161, this.bufferBuilder.samples, 35056, i, i2);
            } else {
                gl20.glRenderbufferStorage(36161, 35056, i, i2);
            }
            gl20.glBindRenderbuffer(36161, 0);
            gl20.glFramebufferRenderbuffer(36160, 36096, 36161, this.depthStencilPackedBufferHandle);
            gl20.glFramebufferRenderbuffer(36160, 36128, 36161, this.depthStencilPackedBufferHandle);
            i5 = gl20.glCheckFramebufferStatus(36160);
        }
        gl20.glBindFramebuffer(36160, defaultFramebufferHandle);
        if (i5 != 36053) {
            Array.ArrayIterator<T> it4 = this.textureAttachments.iterator();
            while (it4.hasNext()) {
                disposeColorTexture(it4.next());
            }
            if (this.hasDepthStencilPackedBuffer) {
                gl20.glDeleteBuffer(this.depthStencilPackedBufferHandle);
            } else {
                if (this.bufferBuilder.hasDepthRenderBuffer) {
                    gl20.glDeleteRenderbuffer(this.depthbufferHandle);
                }
                if (this.bufferBuilder.hasStencilRenderBuffer) {
                    gl20.glDeleteRenderbuffer(this.stencilbufferHandle);
                }
            }
            gl20.glDeleteFramebuffer(this.framebufferHandle);
            if (i5 == 36054) {
                throw new IllegalStateException("Frame buffer couldn't be constructed: incomplete attachment");
            }
            if (i5 == 36057) {
                throw new IllegalStateException("Frame buffer couldn't be constructed: incomplete dimensions");
            }
            if (i5 == 36055) {
                throw new IllegalStateException("Frame buffer couldn't be constructed: missing attachment");
            }
            if (i5 == 36061) {
                throw new IllegalStateException("Frame buffer couldn't be constructed: unsupported combination of formats");
            }
            if (i5 == 36182) {
                throw new IllegalStateException("Frame buffer couldn't be constructed: multisample mismatch");
            }
            throw new IllegalStateException("Frame buffer couldn't be constructed: unknown error " + i5);
        }
        addManagedFrameBuffer(Gdx.app, this);
    }

    private void checkValidBuilder() {
        if (this.bufferBuilder.samples > 0 && !Gdx.graphics.isGL31Available()) {
            throw new GdxRuntimeException("Framebuffer multisample requires GLES 3.1+");
        }
        if (this.bufferBuilder.samples > 0 && this.bufferBuilder.textureAttachmentSpecs.size > 0) {
            throw new GdxRuntimeException("Framebuffer multisample with texture attachments not yet supported");
        }
        if (!Gdx.graphics.isGL30Available()) {
            boolean z = Gdx.graphics.supportsExtension("GL_OES_packed_depth_stencil") || Gdx.graphics.supportsExtension("GL_EXT_packed_depth_stencil");
            if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer && !z) {
                throw new GdxRuntimeException("Packed Stencil/Render render buffers are not available on GLES 2.0");
            }
            if (this.bufferBuilder.textureAttachmentSpecs.size > 1) {
                throw new GdxRuntimeException("Multiple render targets not available on GLES 2.0");
            }
            Array.ArrayIterator<FrameBufferTextureAttachmentSpec> it = this.bufferBuilder.textureAttachmentSpecs.iterator();
            while (it.hasNext()) {
                FrameBufferTextureAttachmentSpec next = it.next();
                if (next.isDepth) {
                    throw new GdxRuntimeException("Depth texture FrameBuffer Attachment not available on GLES 2.0");
                }
                if (next.isStencil) {
                    throw new GdxRuntimeException("Stencil texture FrameBuffer Attachment not available on GLES 2.0");
                }
                if (next.isFloat && !Gdx.graphics.supportsExtension("OES_texture_float")) {
                    throw new GdxRuntimeException("Float texture FrameBuffer Attachment not available on GLES 2.0");
                }
            }
        }
        if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
            if (this.bufferBuilder.hasDepthRenderBuffer || this.bufferBuilder.hasStencilRenderBuffer) {
                throw new GdxRuntimeException("Frame buffer couldn't be constructed: packed stencil depth buffer cannot be specified together with separated depth or stencil buffer");
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        GL20 gl20 = Gdx.gl20;
        Array.ArrayIterator<T> it = this.textureAttachments.iterator();
        while (it.hasNext()) {
            disposeColorTexture(it.next());
        }
        gl20.glDeleteRenderbuffer(this.depthStencilPackedBufferHandle);
        gl20.glDeleteRenderbuffer(this.depthbufferHandle);
        gl20.glDeleteRenderbuffer(this.stencilbufferHandle);
        gl20.glDeleteFramebuffer(this.framebufferHandle);
        if (buffers.get(Gdx.app) != null) {
            buffers.get(Gdx.app).removeValue(this, true);
        }
    }

    public void bind() {
        Gdx.gl20.glBindFramebuffer(36160, this.framebufferHandle);
    }

    public static void unbind() {
        Gdx.gl20.glBindFramebuffer(36160, defaultFramebufferHandle);
    }

    public void begin() {
        bind();
        setFrameBufferViewport();
    }

    protected void setFrameBufferViewport() {
        Gdx.gl20.glViewport(0, 0, this.bufferBuilder.width, this.bufferBuilder.height);
    }

    public void end() {
        end(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    }

    public void end(int i, int i2, int i3, int i4) {
        unbind();
        Gdx.gl20.glViewport(i, i2, i3, i4);
    }

    public void transfer(GLFrameBuffer<T> gLFrameBuffer) {
        int i = 0;
        Array.ArrayIterator<FrameBufferTextureAttachmentSpec> it = gLFrameBuffer.bufferBuilder.textureAttachmentSpecs.iterator();
        while (it.hasNext()) {
            FrameBufferTextureAttachmentSpec next = it.next();
            if (next.isDepth && (this.bufferBuilder.hasDepthRenderBuffer || this.bufferBuilder.hasPackedStencilDepthRenderBuffer)) {
                i |= 256;
            } else if (next.isStencil && (this.bufferBuilder.hasStencilRenderBuffer || this.bufferBuilder.hasPackedStencilDepthRenderBuffer)) {
                i |= 1024;
            } else if (this.colorBufferHandles.size > 0) {
                i |= 16384;
            }
        }
        transfer(gLFrameBuffer, i);
    }

    public void transfer(GLFrameBuffer<T> gLFrameBuffer, int i) {
        if (gLFrameBuffer.getWidth() != getWidth() || gLFrameBuffer.getHeight() != getHeight()) {
            throw new IllegalArgumentException("source and destination frame buffers must have same size.");
        }
        Gdx.gl.glBindFramebuffer(36008, this.framebufferHandle);
        Gdx.gl.glBindFramebuffer(36009, gLFrameBuffer.framebufferHandle);
        int i2 = 0;
        int i3 = 0;
        Array.ArrayIterator<FrameBufferTextureAttachmentSpec> it = gLFrameBuffer.bufferBuilder.textureAttachmentSpecs.iterator();
        while (it.hasNext()) {
            if (it.next().isColorTexture()) {
                Gdx.gl30.glReadBuffer(i2 + 36064);
                singleInt.clear();
                singleInt.put(i3 + 36064);
                singleInt.flip();
                Gdx.gl30.glDrawBuffers(1, singleInt);
                Gdx.gl30.glBlitFramebuffer(0, 0, getWidth(), getHeight(), 0, 0, gLFrameBuffer.getWidth(), gLFrameBuffer.getHeight(), i, 9728);
                i = 16384;
                i2++;
            }
            i3++;
        }
        if (i != 16384) {
            Gdx.gl30.glBlitFramebuffer(0, 0, getWidth(), getHeight(), 0, 0, gLFrameBuffer.getWidth(), gLFrameBuffer.getHeight(), i, 9728);
        }
        if (gLFrameBuffer.defaultDrawBuffers != null) {
            Gdx.gl30.glDrawBuffers(gLFrameBuffer.defaultDrawBuffers.limit(), gLFrameBuffer.defaultDrawBuffers);
        }
        Gdx.gl.glBindFramebuffer(36008, 0);
        Gdx.gl.glBindFramebuffer(36009, 0);
    }

    public int getFramebufferHandle() {
        return this.framebufferHandle;
    }

    public int getDepthBufferHandle() {
        return this.depthbufferHandle;
    }

    public int getColorBufferHandle(int i) {
        return this.colorBufferHandles.get(i);
    }

    public int getStencilBufferHandle() {
        return this.stencilbufferHandle;
    }

    protected int getDepthStencilPackedBuffer() {
        return this.depthStencilPackedBufferHandle;
    }

    public int getHeight() {
        return this.bufferBuilder.height;
    }

    public int getWidth() {
        return this.bufferBuilder.width;
    }

    private static void addManagedFrameBuffer(Application application, GLFrameBuffer gLFrameBuffer) {
        Array<GLFrameBuffer> array = buffers.get(application);
        Array<GLFrameBuffer> array2 = array;
        if (array == null) {
            array2 = new Array<>();
        }
        array2.add(gLFrameBuffer);
        buffers.put(application, array2);
    }

    public static void invalidateAllFrameBuffers(Application application) {
        Array<GLFrameBuffer> array;
        if (Gdx.gl20 == null || (array = buffers.get(application)) == null) {
            return;
        }
        for (int i = 0; i < array.size; i++) {
            array.get(i).build();
        }
    }

    public static void clearAllFrameBuffers(Application application) {
        buffers.remove(application);
    }

    public static StringBuilder getManagedStatus(StringBuilder sb) {
        sb.append("Managed buffers/app: { ");
        Iterator<Application> it = buffers.keySet().iterator();
        while (it.hasNext()) {
            sb.append(buffers.get(it.next()).size);
            sb.append(SequenceUtils.SPACE);
        }
        sb.append("}");
        return sb;
    }

    public static String getManagedStatus() {
        return getManagedStatus(new StringBuilder()).toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLFrameBuffer$FrameBufferTextureAttachmentSpec.class */
    public static class FrameBufferTextureAttachmentSpec {
        int internalFormat;
        int format;
        int type;
        boolean isFloat;
        boolean isGpuOnly;
        boolean isDepth;
        boolean isStencil;

        public FrameBufferTextureAttachmentSpec(int i, int i2, int i3) {
            this.internalFormat = i;
            this.format = i2;
            this.type = i3;
        }

        public boolean isColorTexture() {
            return (this.isDepth || this.isStencil) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLFrameBuffer$FrameBufferRenderBufferAttachmentSpec.class */
    public static class FrameBufferRenderBufferAttachmentSpec {
        int internalFormat;

        public FrameBufferRenderBufferAttachmentSpec(int i) {
            this.internalFormat = i;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLFrameBuffer$GLFrameBufferBuilder.class */
    public static abstract class GLFrameBufferBuilder<U extends GLFrameBuffer<? extends GLTexture>> {
        protected int width;
        protected int height;
        protected int samples;
        protected Array<FrameBufferTextureAttachmentSpec> textureAttachmentSpecs;
        protected Array<FrameBufferRenderBufferAttachmentSpec> colorRenderBufferSpecs;
        protected FrameBufferRenderBufferAttachmentSpec stencilRenderBufferSpec;
        protected FrameBufferRenderBufferAttachmentSpec depthRenderBufferSpec;
        protected FrameBufferRenderBufferAttachmentSpec packedStencilDepthRenderBufferSpec;
        protected boolean hasStencilRenderBuffer;
        protected boolean hasDepthRenderBuffer;
        protected boolean hasPackedStencilDepthRenderBuffer;

        public abstract U build();

        public GLFrameBufferBuilder(int i, int i2) {
            this(i, i2, 0);
        }

        public GLFrameBufferBuilder(int i, int i2, int i3) {
            this.textureAttachmentSpecs = new Array<>();
            this.colorRenderBufferSpecs = new Array<>();
            this.width = i;
            this.height = i2;
            this.samples = i3;
        }

        public GLFrameBufferBuilder<U> addColorTextureAttachment(int i, int i2, int i3) {
            this.textureAttachmentSpecs.add(new FrameBufferTextureAttachmentSpec(i, i2, i3));
            return this;
        }

        public GLFrameBufferBuilder<U> addBasicColorTextureAttachment(Pixmap.Format format) {
            int glFormat = Pixmap.Format.toGlFormat(format);
            return addColorTextureAttachment(glFormat, glFormat, Pixmap.Format.toGlType(format));
        }

        public GLFrameBufferBuilder<U> addFloatAttachment(int i, int i2, int i3, boolean z) {
            FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec = new FrameBufferTextureAttachmentSpec(i, i2, i3);
            frameBufferTextureAttachmentSpec.isFloat = true;
            frameBufferTextureAttachmentSpec.isGpuOnly = z;
            this.textureAttachmentSpecs.add(frameBufferTextureAttachmentSpec);
            return this;
        }

        public GLFrameBufferBuilder<U> addDepthTextureAttachment(int i, int i2) {
            FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec = new FrameBufferTextureAttachmentSpec(i, 6402, i2);
            frameBufferTextureAttachmentSpec.isDepth = true;
            this.textureAttachmentSpecs.add(frameBufferTextureAttachmentSpec);
            return this;
        }

        public GLFrameBufferBuilder<U> addStencilTextureAttachment(int i, int i2) {
            FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec = new FrameBufferTextureAttachmentSpec(i, 36128, i2);
            frameBufferTextureAttachmentSpec.isStencil = true;
            this.textureAttachmentSpecs.add(frameBufferTextureAttachmentSpec);
            return this;
        }

        public GLFrameBufferBuilder<U> addDepthRenderBuffer(int i) {
            this.depthRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(i);
            this.hasDepthRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder<U> addColorRenderBuffer(int i) {
            this.colorRenderBufferSpecs.add(new FrameBufferRenderBufferAttachmentSpec(i));
            return this;
        }

        public GLFrameBufferBuilder<U> addStencilRenderBuffer(int i) {
            this.stencilRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(i);
            this.hasStencilRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder<U> addStencilDepthPackedRenderBuffer(int i) {
            this.packedStencilDepthRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(i);
            this.hasPackedStencilDepthRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder<U> addBasicDepthRenderBuffer() {
            return addDepthRenderBuffer(33189);
        }

        public GLFrameBufferBuilder<U> addBasicStencilRenderBuffer() {
            return addStencilRenderBuffer(36168);
        }

        public GLFrameBufferBuilder<U> addBasicStencilDepthPackedRenderBuffer() {
            return addStencilDepthPackedRenderBuffer(35056);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLFrameBuffer$FrameBufferBuilder.class */
    public static class FrameBufferBuilder extends GLFrameBufferBuilder<FrameBuffer> {
        public FrameBufferBuilder(int i, int i2) {
            super(i, i2);
        }

        public FrameBufferBuilder(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer.GLFrameBufferBuilder
        public FrameBuffer build() {
            return new FrameBuffer(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLFrameBuffer$FloatFrameBufferBuilder.class */
    public static class FloatFrameBufferBuilder extends GLFrameBufferBuilder<FloatFrameBuffer> {
        public FloatFrameBufferBuilder(int i, int i2) {
            super(i, i2);
        }

        public FloatFrameBufferBuilder(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer.GLFrameBufferBuilder
        public FloatFrameBuffer build() {
            return new FloatFrameBuffer(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLFrameBuffer$FrameBufferCubemapBuilder.class */
    public static class FrameBufferCubemapBuilder extends GLFrameBufferBuilder<FrameBufferCubemap> {
        public FrameBufferCubemapBuilder(int i, int i2) {
            super(i, i2);
        }

        public FrameBufferCubemapBuilder(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer.GLFrameBufferBuilder
        public FrameBufferCubemap build() {
            return new FrameBufferCubemap(this);
        }
    }
}
