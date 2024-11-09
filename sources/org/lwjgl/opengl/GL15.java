package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL15.class */
public class GL15 extends GL14 {
    public static final int GL_FOG_COORD_SRC = 33872;
    public static final int GL_FOG_COORD = 33873;
    public static final int GL_CURRENT_FOG_COORD = 33875;
    public static final int GL_FOG_COORD_ARRAY_TYPE = 33876;
    public static final int GL_FOG_COORD_ARRAY_STRIDE = 33877;
    public static final int GL_FOG_COORD_ARRAY_POINTER = 33878;
    public static final int GL_FOG_COORD_ARRAY = 33879;
    public static final int GL_FOG_COORD_ARRAY_BUFFER_BINDING = 34973;
    public static final int GL_SRC0_RGB = 34176;
    public static final int GL_SRC1_RGB = 34177;
    public static final int GL_SRC2_RGB = 34178;
    public static final int GL_SRC0_ALPHA = 34184;
    public static final int GL_SRC1_ALPHA = 34185;
    public static final int GL_SRC2_ALPHA = 34186;
    public static final int GL_ARRAY_BUFFER = 34962;
    public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
    public static final int GL_ARRAY_BUFFER_BINDING = 34964;
    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
    public static final int GL_VERTEX_ARRAY_BUFFER_BINDING = 34966;
    public static final int GL_NORMAL_ARRAY_BUFFER_BINDING = 34967;
    public static final int GL_COLOR_ARRAY_BUFFER_BINDING = 34968;
    public static final int GL_INDEX_ARRAY_BUFFER_BINDING = 34969;
    public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 34970;
    public static final int GL_EDGE_FLAG_ARRAY_BUFFER_BINDING = 34971;
    public static final int GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING = 34972;
    public static final int GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING = 34973;
    public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING = 34974;
    public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;
    public static final int GL_STREAM_DRAW = 35040;
    public static final int GL_STREAM_READ = 35041;
    public static final int GL_STREAM_COPY = 35042;
    public static final int GL_STATIC_DRAW = 35044;
    public static final int GL_STATIC_READ = 35045;
    public static final int GL_STATIC_COPY = 35046;
    public static final int GL_DYNAMIC_DRAW = 35048;
    public static final int GL_DYNAMIC_READ = 35049;
    public static final int GL_DYNAMIC_COPY = 35050;
    public static final int GL_READ_ONLY = 35000;
    public static final int GL_WRITE_ONLY = 35001;
    public static final int GL_READ_WRITE = 35002;
    public static final int GL_BUFFER_SIZE = 34660;
    public static final int GL_BUFFER_USAGE = 34661;
    public static final int GL_BUFFER_ACCESS = 35003;
    public static final int GL_BUFFER_MAPPED = 35004;
    public static final int GL_BUFFER_MAP_POINTER = 35005;
    public static final int GL_SAMPLES_PASSED = 35092;
    public static final int GL_QUERY_COUNTER_BITS = 34916;
    public static final int GL_CURRENT_QUERY = 34917;
    public static final int GL_QUERY_RESULT = 34918;
    public static final int GL_QUERY_RESULT_AVAILABLE = 34919;

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL15() {
        throw new UnsupportedOperationException();
    }

    public static void glBindBuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL15C.glBindBuffer(i, i2);
    }

    public static void nglDeleteBuffers(int i, long j) {
        GL15C.nglDeleteBuffers(i, j);
    }

    public static void glDeleteBuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL15C.glDeleteBuffers(intBuffer);
    }

    public static void glDeleteBuffers(@NativeType("GLuint const *") int i) {
        GL15C.glDeleteBuffers(i);
    }

    public static void nglGenBuffers(int i, long j) {
        GL15C.nglGenBuffers(i, j);
    }

    public static void glGenBuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL15C.glGenBuffers(intBuffer);
    }

    @NativeType("void")
    public static int glGenBuffers() {
        return GL15C.glGenBuffers();
    }

    @NativeType("GLboolean")
    public static boolean glIsBuffer(@NativeType("GLuint") int i) {
        return GL15C.glIsBuffer(i);
    }

    public static void nglBufferData(int i, long j, long j2, int i2) {
        GL15C.nglBufferData(i, j, j2, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, j, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, byteBuffer, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, shortBuffer, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, intBuffer, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") LongBuffer longBuffer, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, longBuffer, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, floatBuffer, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, doubleBuffer, i2);
    }

    public static void nglBufferSubData(int i, long j, long j2, long j3) {
        GL15C.nglBufferSubData(i, j, j2, j3);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL15C.glBufferSubData(i, j, byteBuffer);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL15C.glBufferSubData(i, j, shortBuffer);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") IntBuffer intBuffer) {
        GL15C.glBufferSubData(i, j, intBuffer);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") LongBuffer longBuffer) {
        GL15C.glBufferSubData(i, j, longBuffer);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL15C.glBufferSubData(i, j, floatBuffer);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL15C.glBufferSubData(i, j, doubleBuffer);
    }

    public static void nglGetBufferSubData(int i, long j, long j2, long j3) {
        GL15C.nglGetBufferSubData(i, j, j2, j3);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") ByteBuffer byteBuffer) {
        GL15C.glGetBufferSubData(i, j, byteBuffer);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") ShortBuffer shortBuffer) {
        GL15C.glGetBufferSubData(i, j, shortBuffer);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") IntBuffer intBuffer) {
        GL15C.glGetBufferSubData(i, j, intBuffer);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") LongBuffer longBuffer) {
        GL15C.glGetBufferSubData(i, j, longBuffer);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") FloatBuffer floatBuffer) {
        GL15C.glGetBufferSubData(i, j, floatBuffer);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") DoubleBuffer doubleBuffer) {
        GL15C.glGetBufferSubData(i, j, doubleBuffer);
    }

    public static long nglMapBuffer(int i, int i2) {
        return GL15C.nglMapBuffer(i, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL15C.glMapBuffer(i, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, ByteBuffer byteBuffer) {
        return GL15C.glMapBuffer(i, i2, byteBuffer);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, long j, ByteBuffer byteBuffer) {
        return GL15C.glMapBuffer(i, i2, j, byteBuffer);
    }

    @NativeType("GLboolean")
    public static boolean glUnmapBuffer(@NativeType("GLenum") int i) {
        return GL15C.glUnmapBuffer(i);
    }

    public static void nglGetBufferParameteriv(int i, int i2, long j) {
        GL15C.nglGetBufferParameteriv(i, i2, j);
    }

    public static void glGetBufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL15C.glGetBufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetBufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL15C.glGetBufferParameteri(i, i2);
    }

    public static void nglGetBufferPointerv(int i, int i2, long j) {
        GL15C.nglGetBufferPointerv(i, i2, j);
    }

    public static void glGetBufferPointerv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        GL15C.glGetBufferPointerv(i, i2, pointerBuffer);
    }

    @NativeType("void")
    public static long glGetBufferPointer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL15C.glGetBufferPointer(i, i2);
    }

    public static void nglGenQueries(int i, long j) {
        GL15C.nglGenQueries(i, j);
    }

    public static void glGenQueries(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL15C.glGenQueries(intBuffer);
    }

    @NativeType("void")
    public static int glGenQueries() {
        return GL15C.glGenQueries();
    }

    public static void nglDeleteQueries(int i, long j) {
        GL15C.nglDeleteQueries(i, j);
    }

    public static void glDeleteQueries(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL15C.glDeleteQueries(intBuffer);
    }

    public static void glDeleteQueries(@NativeType("GLuint const *") int i) {
        GL15C.glDeleteQueries(i);
    }

    @NativeType("GLboolean")
    public static boolean glIsQuery(@NativeType("GLuint") int i) {
        return GL15C.glIsQuery(i);
    }

    public static void glBeginQuery(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL15C.glBeginQuery(i, i2);
    }

    public static void glEndQuery(@NativeType("GLenum") int i) {
        GL15C.glEndQuery(i);
    }

    public static void nglGetQueryiv(int i, int i2, long j) {
        GL15C.nglGetQueryiv(i, i2, j);
    }

    public static void glGetQueryiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL15C.glGetQueryiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetQueryi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL15C.glGetQueryi(i, i2);
    }

    public static void nglGetQueryObjectiv(int i, int i2, long j) {
        GL15C.nglGetQueryObjectiv(i, i2, j);
    }

    public static void glGetQueryObjectiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL15C.glGetQueryObjectiv(i, i2, intBuffer);
    }

    public static void glGetQueryObjectiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") long j) {
        GL15C.glGetQueryObjectiv(i, i2, j);
    }

    @NativeType("void")
    public static int glGetQueryObjecti(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL15C.glGetQueryObjecti(i, i2);
    }

    public static void nglGetQueryObjectuiv(int i, int i2, long j) {
        GL15C.nglGetQueryObjectuiv(i, i2, j);
    }

    public static void glGetQueryObjectuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL15C.glGetQueryObjectuiv(i, i2, intBuffer);
    }

    public static void glGetQueryObjectuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") long j) {
        GL15C.glGetQueryObjectuiv(i, i2, j);
    }

    @NativeType("void")
    public static int glGetQueryObjectui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL15C.glGetQueryObjectui(i, i2);
    }

    public static void glDeleteBuffers(@NativeType("GLuint const *") int[] iArr) {
        GL15C.glDeleteBuffers(iArr);
    }

    public static void glGenBuffers(@NativeType("GLuint *") int[] iArr) {
        GL15C.glGenBuffers(iArr);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") short[] sArr, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, sArr, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, iArr, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") long[] jArr, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, jArr, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") float[] fArr, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, fArr, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") double[] dArr, @NativeType("GLenum") int i2) {
        GL15C.glBufferData(i, dArr, i2);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") short[] sArr) {
        GL15C.glBufferSubData(i, j, sArr);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") int[] iArr) {
        GL15C.glBufferSubData(i, j, iArr);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") long[] jArr) {
        GL15C.glBufferSubData(i, j, jArr);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") float[] fArr) {
        GL15C.glBufferSubData(i, j, fArr);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") double[] dArr) {
        GL15C.glBufferSubData(i, j, dArr);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") short[] sArr) {
        GL15C.glGetBufferSubData(i, j, sArr);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") int[] iArr) {
        GL15C.glGetBufferSubData(i, j, iArr);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") long[] jArr) {
        GL15C.glGetBufferSubData(i, j, jArr);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") float[] fArr) {
        GL15C.glGetBufferSubData(i, j, fArr);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") double[] dArr) {
        GL15C.glGetBufferSubData(i, j, dArr);
    }

    public static void glGetBufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL15C.glGetBufferParameteriv(i, i2, iArr);
    }

    public static void glGenQueries(@NativeType("GLuint *") int[] iArr) {
        GL15C.glGenQueries(iArr);
    }

    public static void glDeleteQueries(@NativeType("GLuint const *") int[] iArr) {
        GL15C.glDeleteQueries(iArr);
    }

    public static void glGetQueryiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL15C.glGetQueryiv(i, i2, iArr);
    }

    public static void glGetQueryObjectiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL15C.glGetQueryObjectiv(i, i2, iArr);
    }

    public static void glGetQueryObjectuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        GL15C.glGetQueryObjectuiv(i, i2, iArr);
    }
}
