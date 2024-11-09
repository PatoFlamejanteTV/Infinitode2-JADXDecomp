package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/ShaderProgram.class */
public class ShaderProgram implements Disposable {
    public static final String POSITION_ATTRIBUTE = "a_position";
    public static final String NORMAL_ATTRIBUTE = "a_normal";
    public static final String COLOR_ATTRIBUTE = "a_color";
    public static final String TEXCOORD_ATTRIBUTE = "a_texCoord";
    public static final String TANGENT_ATTRIBUTE = "a_tangent";
    public static final String BINORMAL_ATTRIBUTE = "a_binormal";
    public static final String BONEWEIGHT_ATTRIBUTE = "a_boneWeight";
    private String log;
    private boolean isCompiled;
    private final ObjectIntMap<String> uniforms;
    private final ObjectIntMap<String> uniformTypes;
    private final ObjectIntMap<String> uniformSizes;
    private String[] uniformNames;
    private final ObjectIntMap<String> attributes;
    private final ObjectIntMap<String> attributeTypes;
    private final ObjectIntMap<String> attributeSizes;
    private String[] attributeNames;
    private int program;
    private int vertexShaderHandle;
    private int fragmentShaderHandle;
    private final FloatBuffer matrix;
    private final String vertexShaderSource;
    private final String fragmentShaderSource;
    private boolean invalidated;
    private int refCount;
    IntBuffer params;
    IntBuffer type;
    public static boolean pedantic = true;
    public static String prependVertexCode = "";
    public static String prependFragmentCode = "";
    private static final ObjectMap<Application, Array<ShaderProgram>> shaders = new ObjectMap<>();
    static final IntBuffer intbuf = BufferUtils.newIntBuffer(1);

    public ShaderProgram(String str, String str2) {
        this.log = "";
        this.uniforms = new ObjectIntMap<>();
        this.uniformTypes = new ObjectIntMap<>();
        this.uniformSizes = new ObjectIntMap<>();
        this.attributes = new ObjectIntMap<>();
        this.attributeTypes = new ObjectIntMap<>();
        this.attributeSizes = new ObjectIntMap<>();
        this.refCount = 0;
        this.params = BufferUtils.newIntBuffer(1);
        this.type = BufferUtils.newIntBuffer(1);
        if (str == null) {
            throw new IllegalArgumentException("vertex shader must not be null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("fragment shader must not be null");
        }
        if (prependVertexCode != null && prependVertexCode.length() > 0) {
            str = prependVertexCode + str;
        }
        if (prependFragmentCode != null && prependFragmentCode.length() > 0) {
            str2 = prependFragmentCode + str2;
        }
        this.vertexShaderSource = str;
        this.fragmentShaderSource = str2;
        this.matrix = BufferUtils.newFloatBuffer(16);
        compileShaders(str, str2);
        if (isCompiled()) {
            fetchAttributes();
            fetchUniforms();
            addManagedShader(Gdx.app, this);
        }
    }

    public ShaderProgram(FileHandle fileHandle, FileHandle fileHandle2) {
        this(fileHandle.readString(), fileHandle2.readString());
    }

    private void compileShaders(String str, String str2) {
        this.vertexShaderHandle = loadShader(35633, str);
        this.fragmentShaderHandle = loadShader(35632, str2);
        if (this.vertexShaderHandle == -1 || this.fragmentShaderHandle == -1) {
            this.isCompiled = false;
            return;
        }
        this.program = linkProgram(createProgram());
        if (this.program == -1) {
            this.isCompiled = false;
        } else {
            this.isCompiled = true;
        }
    }

    private int loadShader(int i, String str) {
        GL20 gl20 = Gdx.gl20;
        IntBuffer newIntBuffer = BufferUtils.newIntBuffer(1);
        int glCreateShader = gl20.glCreateShader(i);
        if (glCreateShader == 0) {
            return -1;
        }
        gl20.glShaderSource(glCreateShader, str);
        gl20.glCompileShader(glCreateShader);
        gl20.glGetShaderiv(glCreateShader, 35713, newIntBuffer);
        if (newIntBuffer.get(0) == 0) {
            String glGetShaderInfoLog = gl20.glGetShaderInfoLog(glCreateShader);
            this.log += (i == 35633 ? "Vertex shader\n" : "Fragment shader:\n");
            this.log += glGetShaderInfoLog;
            return -1;
        }
        return glCreateShader;
    }

    protected int createProgram() {
        int glCreateProgram = Gdx.gl20.glCreateProgram();
        if (glCreateProgram != 0) {
            return glCreateProgram;
        }
        return -1;
    }

    private int linkProgram(int i) {
        GL20 gl20 = Gdx.gl20;
        if (i == -1) {
            return -1;
        }
        gl20.glAttachShader(i, this.vertexShaderHandle);
        gl20.glAttachShader(i, this.fragmentShaderHandle);
        gl20.glLinkProgram(i);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4);
        allocateDirect.order(ByteOrder.nativeOrder());
        IntBuffer asIntBuffer = allocateDirect.asIntBuffer();
        gl20.glGetProgramiv(i, 35714, asIntBuffer);
        if (asIntBuffer.get(0) == 0) {
            this.log = Gdx.gl20.glGetProgramInfoLog(i);
            return -1;
        }
        return i;
    }

    public String getLog() {
        if (this.isCompiled) {
            this.log = Gdx.gl20.glGetProgramInfoLog(this.program);
            return this.log;
        }
        return this.log;
    }

    public boolean isCompiled() {
        return this.isCompiled;
    }

    private int fetchAttributeLocation(String str) {
        GL20 gl20 = Gdx.gl20;
        int i = this.attributes.get(str, -2);
        int i2 = i;
        if (i == -2) {
            i2 = gl20.glGetAttribLocation(this.program, str);
            this.attributes.put(str, i2);
        }
        return i2;
    }

    private int fetchUniformLocation(String str) {
        return fetchUniformLocation(str, pedantic);
    }

    public int fetchUniformLocation(String str, boolean z) {
        int i = this.uniforms.get(str, -2);
        int i2 = i;
        if (i == -2) {
            int glGetUniformLocation = Gdx.gl20.glGetUniformLocation(this.program, str);
            i2 = glGetUniformLocation;
            if (glGetUniformLocation == -1 && z) {
                if (this.isCompiled) {
                    throw new IllegalArgumentException("No uniform with name '" + str + "' in shader");
                }
                throw new IllegalStateException("An attempted fetch uniform from uncompiled shader \n" + getLog());
            }
            this.uniforms.put(str, i2);
        }
        return i2;
    }

    public void setUniformi(String str, int i) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1i(fetchUniformLocation(str), i);
    }

    public void setUniformi(int i, int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1i(i, i2);
    }

    public void setUniformi(String str, int i, int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2i(fetchUniformLocation(str), i, i2);
    }

    public void setUniformi(int i, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2i(i, i2, i3);
    }

    public void setUniformi(String str, int i, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3i(fetchUniformLocation(str), i, i2, i3);
    }

    public void setUniformi(int i, int i2, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3i(i, i2, i3, i4);
    }

    public void setUniformi(String str, int i, int i2, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4i(fetchUniformLocation(str), i, i2, i3, i4);
    }

    public void setUniformi(int i, int i2, int i3, int i4, int i5) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4i(i, i2, i3, i4, i5);
    }

    public void setUniformf(String str, float f) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1f(fetchUniformLocation(str), f);
    }

    public void setUniformf(int i, float f) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1f(i, f);
    }

    public void setUniformf(String str, float f, float f2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2f(fetchUniformLocation(str), f, f2);
    }

    public void setUniformf(int i, float f, float f2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2f(i, f, f2);
    }

    public void setUniformf(String str, float f, float f2, float f3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3f(fetchUniformLocation(str), f, f2, f3);
    }

    public void setUniformf(int i, float f, float f2, float f3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3f(i, f, f2, f3);
    }

    public void setUniformf(String str, float f, float f2, float f3, float f4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4f(fetchUniformLocation(str), f, f2, f3, f4);
    }

    public void setUniformf(int i, float f, float f2, float f3, float f4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4f(i, f, f2, f3, f4);
    }

    public void setUniform1fv(String str, float[] fArr, int i, int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1fv(fetchUniformLocation(str), i2, fArr, i);
    }

    public void setUniform1fv(int i, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1fv(i, i3, fArr, i2);
    }

    public void setUniform2fv(String str, float[] fArr, int i, int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2fv(fetchUniformLocation(str), i2 / 2, fArr, i);
    }

    public void setUniform2fv(int i, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2fv(i, i3 / 2, fArr, i2);
    }

    public void setUniform3fv(String str, float[] fArr, int i, int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3fv(fetchUniformLocation(str), i2 / 3, fArr, i);
    }

    public void setUniform3fv(int i, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3fv(i, i3 / 3, fArr, i2);
    }

    public void setUniform4fv(String str, float[] fArr, int i, int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4fv(fetchUniformLocation(str), i2 / 4, fArr, i);
    }

    public void setUniform4fv(int i, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4fv(i, i3 / 4, fArr, i2);
    }

    public void setUniformMatrix(String str, Matrix4 matrix4) {
        setUniformMatrix(str, matrix4, false);
    }

    public void setUniformMatrix(String str, Matrix4 matrix4, boolean z) {
        setUniformMatrix(fetchUniformLocation(str), matrix4, z);
    }

    public void setUniformMatrix(int i, Matrix4 matrix4) {
        setUniformMatrix(i, matrix4, false);
    }

    public void setUniformMatrix(int i, Matrix4 matrix4, boolean z) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniformMatrix4fv(i, 1, z, matrix4.val, 0);
    }

    public void setUniformMatrix(String str, Matrix3 matrix3) {
        setUniformMatrix(str, matrix3, false);
    }

    public void setUniformMatrix(String str, Matrix3 matrix3, boolean z) {
        setUniformMatrix(fetchUniformLocation(str), matrix3, z);
    }

    public void setUniformMatrix(int i, Matrix3 matrix3) {
        setUniformMatrix(i, matrix3, false);
    }

    public void setUniformMatrix(int i, Matrix3 matrix3, boolean z) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniformMatrix3fv(i, 1, z, matrix3.val, 0);
    }

    public void setUniformMatrix3fv(String str, FloatBuffer floatBuffer, int i, boolean z) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        floatBuffer.position(0);
        gl20.glUniformMatrix3fv(fetchUniformLocation(str), i, z, floatBuffer);
    }

    public void setUniformMatrix4fv(String str, FloatBuffer floatBuffer, int i, boolean z) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        floatBuffer.position(0);
        gl20.glUniformMatrix4fv(fetchUniformLocation(str), i, z, floatBuffer);
    }

    public void setUniformMatrix4fv(int i, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniformMatrix4fv(i, i3 / 16, false, fArr, i2);
    }

    public void setUniformMatrix4fv(String str, float[] fArr, int i, int i2) {
        setUniformMatrix4fv(fetchUniformLocation(str), fArr, i, i2);
    }

    public void setUniformf(String str, Vector2 vector2) {
        setUniformf(str, vector2.x, vector2.y);
    }

    public void setUniformf(int i, Vector2 vector2) {
        setUniformf(i, vector2.x, vector2.y);
    }

    public void setUniformf(String str, Vector3 vector3) {
        setUniformf(str, vector3.x, vector3.y, vector3.z);
    }

    public void setUniformf(int i, Vector3 vector3) {
        setUniformf(i, vector3.x, vector3.y, vector3.z);
    }

    public void setUniformf(String str, Vector4 vector4) {
        setUniformf(str, vector4.x, vector4.y, vector4.z, vector4.w);
    }

    public void setUniformf(int i, Vector4 vector4) {
        setUniformf(i, vector4.x, vector4.y, vector4.z, vector4.w);
    }

    public void setUniformf(String str, Color color) {
        setUniformf(str, color.r, color.g, color.f888b, color.f889a);
    }

    public void setUniformf(int i, Color color) {
        setUniformf(i, color.r, color.g, color.f888b, color.f889a);
    }

    public void setVertexAttribute(String str, int i, int i2, boolean z, int i3, Buffer buffer) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int fetchAttributeLocation = fetchAttributeLocation(str);
        if (fetchAttributeLocation == -1) {
            return;
        }
        gl20.glVertexAttribPointer(fetchAttributeLocation, i, i2, z, i3, buffer);
    }

    public void setVertexAttribute(int i, int i2, int i3, boolean z, int i4, Buffer buffer) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glVertexAttribPointer(i, i2, i3, z, i4, buffer);
    }

    public void setVertexAttribute(String str, int i, int i2, boolean z, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int fetchAttributeLocation = fetchAttributeLocation(str);
        if (fetchAttributeLocation == -1) {
            return;
        }
        gl20.glVertexAttribPointer(fetchAttributeLocation, i, i2, z, i3, i4);
    }

    public void setVertexAttribute(int i, int i2, int i3, boolean z, int i4, int i5) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glVertexAttribPointer(i, i2, i3, z, i4, i5);
    }

    @Deprecated
    public void begin() {
        bind();
    }

    public void bind() {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUseProgram(this.program);
    }

    @Deprecated
    public void end() {
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        GL20 gl20 = Gdx.gl20;
        gl20.glUseProgram(0);
        gl20.glDeleteShader(this.vertexShaderHandle);
        gl20.glDeleteShader(this.fragmentShaderHandle);
        gl20.glDeleteProgram(this.program);
        if (shaders.get(Gdx.app) != null) {
            shaders.get(Gdx.app).removeValue(this, true);
        }
    }

    public void disableVertexAttribute(String str) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int fetchAttributeLocation = fetchAttributeLocation(str);
        if (fetchAttributeLocation == -1) {
            return;
        }
        gl20.glDisableVertexAttribArray(fetchAttributeLocation);
    }

    public void disableVertexAttribute(int i) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glDisableVertexAttribArray(i);
    }

    public void enableVertexAttribute(String str) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int fetchAttributeLocation = fetchAttributeLocation(str);
        if (fetchAttributeLocation == -1) {
            return;
        }
        gl20.glEnableVertexAttribArray(fetchAttributeLocation);
    }

    public void enableVertexAttribute(int i) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glEnableVertexAttribArray(i);
    }

    private void checkManaged() {
        if (this.invalidated) {
            compileShaders(this.vertexShaderSource, this.fragmentShaderSource);
            this.invalidated = false;
        }
    }

    private void addManagedShader(Application application, ShaderProgram shaderProgram) {
        Array<ShaderProgram> array = shaders.get(application);
        Array<ShaderProgram> array2 = array;
        if (array == null) {
            array2 = new Array<>();
        }
        array2.add(shaderProgram);
        shaders.put(application, array2);
    }

    public static void invalidateAllShaderPrograms(Application application) {
        Array<ShaderProgram> array;
        if (Gdx.gl20 == null || (array = shaders.get(application)) == null) {
            return;
        }
        for (int i = 0; i < array.size; i++) {
            array.get(i).invalidated = true;
            array.get(i).checkManaged();
        }
    }

    public static void clearAllShaderPrograms(Application application) {
        shaders.remove(application);
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Managed shaders/app: { ");
        ObjectMap.Keys<Application> it = shaders.keys().iterator();
        while (it.hasNext()) {
            sb.append(shaders.get(it.next()).size);
            sb.append(SequenceUtils.SPACE);
        }
        sb.append("}");
        return sb.toString();
    }

    public static int getNumManagedShaderPrograms() {
        return shaders.get(Gdx.app).size;
    }

    public void setAttributef(String str, float f, float f2, float f3, float f4) {
        Gdx.gl20.glVertexAttrib4f(fetchAttributeLocation(str), f, f2, f3, f4);
    }

    private void fetchUniforms() {
        this.params.clear();
        Gdx.gl20.glGetProgramiv(this.program, 35718, this.params);
        int i = this.params.get(0);
        this.uniformNames = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.params.clear();
            this.params.put(0, 1);
            this.type.clear();
            String glGetActiveUniform = Gdx.gl20.glGetActiveUniform(this.program, i2, this.params, this.type);
            this.uniforms.put(glGetActiveUniform, Gdx.gl20.glGetUniformLocation(this.program, glGetActiveUniform));
            this.uniformTypes.put(glGetActiveUniform, this.type.get(0));
            this.uniformSizes.put(glGetActiveUniform, this.params.get(0));
            this.uniformNames[i2] = glGetActiveUniform;
        }
    }

    private void fetchAttributes() {
        this.params.clear();
        Gdx.gl20.glGetProgramiv(this.program, 35721, this.params);
        int i = this.params.get(0);
        this.attributeNames = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.params.clear();
            this.params.put(0, 1);
            this.type.clear();
            String glGetActiveAttrib = Gdx.gl20.glGetActiveAttrib(this.program, i2, this.params, this.type);
            this.attributes.put(glGetActiveAttrib, Gdx.gl20.glGetAttribLocation(this.program, glGetActiveAttrib));
            this.attributeTypes.put(glGetActiveAttrib, this.type.get(0));
            this.attributeSizes.put(glGetActiveAttrib, this.params.get(0));
            this.attributeNames[i2] = glGetActiveAttrib;
        }
    }

    public boolean hasAttribute(String str) {
        return this.attributes.containsKey(str);
    }

    public int getAttributeType(String str) {
        return this.attributeTypes.get(str, 0);
    }

    public int getAttributeLocation(String str) {
        return this.attributes.get(str, -1);
    }

    public int getAttributeSize(String str) {
        return this.attributeSizes.get(str, 0);
    }

    public boolean hasUniform(String str) {
        return this.uniforms.containsKey(str);
    }

    public int getUniformType(String str) {
        return this.uniformTypes.get(str, 0);
    }

    public int getUniformLocation(String str) {
        return this.uniforms.get(str, -1);
    }

    public int getUniformSize(String str) {
        return this.uniformSizes.get(str, 0);
    }

    public String[] getAttributes() {
        return this.attributeNames;
    }

    public String[] getUniforms() {
        return this.uniformNames;
    }

    public String getVertexShaderSource() {
        return this.vertexShaderSource;
    }

    public String getFragmentShaderSource() {
        return this.fragmentShaderSource;
    }

    public int getHandle() {
        return this.program;
    }
}
