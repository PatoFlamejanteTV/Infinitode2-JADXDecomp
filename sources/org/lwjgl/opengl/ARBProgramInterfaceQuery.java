package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBProgramInterfaceQuery.class */
public class ARBProgramInterfaceQuery {
    public static final int GL_UNIFORM = 37601;
    public static final int GL_UNIFORM_BLOCK = 37602;
    public static final int GL_PROGRAM_INPUT = 37603;
    public static final int GL_PROGRAM_OUTPUT = 37604;
    public static final int GL_BUFFER_VARIABLE = 37605;
    public static final int GL_SHADER_STORAGE_BLOCK = 37606;
    public static final int GL_VERTEX_SUBROUTINE = 37608;
    public static final int GL_TESS_CONTROL_SUBROUTINE = 37609;
    public static final int GL_TESS_EVALUATION_SUBROUTINE = 37610;
    public static final int GL_GEOMETRY_SUBROUTINE = 37611;
    public static final int GL_FRAGMENT_SUBROUTINE = 37612;
    public static final int GL_COMPUTE_SUBROUTINE = 37613;
    public static final int GL_VERTEX_SUBROUTINE_UNIFORM = 37614;
    public static final int GL_TESS_CONTROL_SUBROUTINE_UNIFORM = 37615;
    public static final int GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 37616;
    public static final int GL_GEOMETRY_SUBROUTINE_UNIFORM = 37617;
    public static final int GL_FRAGMENT_SUBROUTINE_UNIFORM = 37618;
    public static final int GL_COMPUTE_SUBROUTINE_UNIFORM = 37619;
    public static final int GL_TRANSFORM_FEEDBACK_VARYING = 37620;
    public static final int GL_ACTIVE_RESOURCES = 37621;
    public static final int GL_MAX_NAME_LENGTH = 37622;
    public static final int GL_MAX_NUM_ACTIVE_VARIABLES = 37623;
    public static final int GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 37624;
    public static final int GL_NAME_LENGTH = 37625;
    public static final int GL_TYPE = 37626;
    public static final int GL_ARRAY_SIZE = 37627;
    public static final int GL_OFFSET = 37628;
    public static final int GL_BLOCK_INDEX = 37629;
    public static final int GL_ARRAY_STRIDE = 37630;
    public static final int GL_MATRIX_STRIDE = 37631;
    public static final int GL_IS_ROW_MAJOR = 37632;
    public static final int GL_ATOMIC_COUNTER_BUFFER_INDEX = 37633;
    public static final int GL_BUFFER_BINDING = 37634;
    public static final int GL_BUFFER_DATA_SIZE = 37635;
    public static final int GL_NUM_ACTIVE_VARIABLES = 37636;
    public static final int GL_ACTIVE_VARIABLES = 37637;
    public static final int GL_REFERENCED_BY_VERTEX_SHADER = 37638;
    public static final int GL_REFERENCED_BY_TESS_CONTROL_SHADER = 37639;
    public static final int GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 37640;
    public static final int GL_REFERENCED_BY_GEOMETRY_SHADER = 37641;
    public static final int GL_REFERENCED_BY_FRAGMENT_SHADER = 37642;
    public static final int GL_REFERENCED_BY_COMPUTE_SHADER = 37643;
    public static final int GL_TOP_LEVEL_ARRAY_SIZE = 37644;
    public static final int GL_TOP_LEVEL_ARRAY_STRIDE = 37645;
    public static final int GL_LOCATION = 37646;
    public static final int GL_LOCATION_INDEX = 37647;
    public static final int GL_IS_PER_PATCH = 37607;

    static {
        GL.initialize();
    }

    protected ARBProgramInterfaceQuery() {
        throw new UnsupportedOperationException();
    }

    public static void nglGetProgramInterfaceiv(int i, int i2, int i3, long j) {
        GL43C.nglGetProgramInterfaceiv(i, i2, i3, j);
    }

    public static void glGetProgramInterfaceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL43C.glGetProgramInterfaceiv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetProgramInterfacei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL43C.glGetProgramInterfacei(i, i2, i3);
    }

    public static int nglGetProgramResourceIndex(int i, int i2, long j) {
        return GL43C.nglGetProgramResourceIndex(i, i2, j);
    }

    @NativeType("GLuint")
    public static int glGetProgramResourceIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL43C.glGetProgramResourceIndex(i, i2, byteBuffer);
    }

    @NativeType("GLuint")
    public static int glGetProgramResourceIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL43C.glGetProgramResourceIndex(i, i2, charSequence);
    }

    public static void nglGetProgramResourceName(int i, int i2, int i3, int i4, long j, long j2) {
        GL43C.nglGetProgramResourceName(i, i2, i3, i4, j, j2);
    }

    public static void glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetProgramResourceName(i, i2, i3, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        return GL43C.glGetProgramResourceName(i, i2, i3, i4);
    }

    @NativeType("void")
    public static String glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return glGetProgramResourceName(i, i2, i3, glGetProgramInterfacei(i, i2, 37622));
    }

    public static void nglGetProgramResourceiv(int i, int i2, int i3, int i4, long j, int i5, long j2, long j3) {
        GL43C.nglGetProgramResourceiv(i, i2, i3, i4, j, i5, j2, j3);
    }

    public static void glGetProgramResourceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLint *") IntBuffer intBuffer3) {
        GL43C.glGetProgramResourceiv(i, i2, i3, intBuffer, intBuffer2, intBuffer3);
    }

    public static int nglGetProgramResourceLocation(int i, int i2, long j) {
        return GL43C.nglGetProgramResourceLocation(i, i2, j);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL43C.glGetProgramResourceLocation(i, i2, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL43C.glGetProgramResourceLocation(i, i2, charSequence);
    }

    public static int nglGetProgramResourceLocationIndex(int i, int i2, long j) {
        return GL43C.nglGetProgramResourceLocationIndex(i, i2, j);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocationIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL43C.glGetProgramResourceLocationIndex(i, i2, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocationIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL43C.glGetProgramResourceLocationIndex(i, i2, charSequence);
    }

    public static void glGetProgramInterfaceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL43C.glGetProgramInterfaceiv(i, i2, i3, iArr);
    }

    public static void glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetProgramResourceName(i, i2, i3, iArr, byteBuffer);
    }

    public static void glGetProgramResourceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLint *") int[] iArr3) {
        GL43C.glGetProgramResourceiv(i, i2, i3, iArr, iArr2, iArr3);
    }
}
