package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/decals/CameraGroupStrategy.class */
public class CameraGroupStrategy implements GroupStrategy, Disposable {
    private static final int GROUP_OPAQUE = 0;
    private static final int GROUP_BLEND = 1;
    Pool<Array<Decal>> arrayPool;
    Array<Array<Decal>> usedArrays;
    ObjectMap<DecalMaterial, Array<Decal>> materialGroups;
    Camera camera;
    ShaderProgram shader;
    private final Comparator<Decal> cameraSorter;

    public CameraGroupStrategy(Camera camera) {
        this.arrayPool = new Pool<Array<Decal>>(16) { // from class: com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.Pool
            public Array<Decal> newObject() {
                return new Array<>();
            }
        };
        this.usedArrays = new Array<>();
        this.materialGroups = new ObjectMap<>();
        this.camera = camera;
        this.cameraSorter = new Comparator<Decal>() { // from class: com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy.2
            @Override // java.util.Comparator
            public int compare(Decal decal, Decal decal2) {
                return (int) Math.signum(CameraGroupStrategy.this.camera.position.dst(decal2.position) - CameraGroupStrategy.this.camera.position.dst(decal.position));
            }
        };
        createDefaultShader();
    }

    public CameraGroupStrategy(Camera camera, Comparator<Decal> comparator) {
        this.arrayPool = new Pool<Array<Decal>>(16) { // from class: com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.Pool
            public Array<Decal> newObject() {
                return new Array<>();
            }
        };
        this.usedArrays = new Array<>();
        this.materialGroups = new ObjectMap<>();
        this.camera = camera;
        this.cameraSorter = comparator;
        createDefaultShader();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return this.camera;
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public int decideGroup(Decal decal) {
        return decal.getMaterial().isOpaque() ? 0 : 1;
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int i, Array<Decal> array) {
        if (i == 1) {
            Gdx.gl.glEnable(3042);
            Gdx.gl.glDepthMask(false);
            array.sort(this.cameraSorter);
            return;
        }
        int i2 = array.size;
        for (int i3 = 0; i3 < i2; i3++) {
            Decal decal = array.get(i3);
            Array<Decal> array2 = this.materialGroups.get(decal.material);
            Array<Decal> array3 = array2;
            if (array2 == null) {
                Array<Decal> obtain = this.arrayPool.obtain();
                array3 = obtain;
                obtain.clear();
                this.usedArrays.add(array3);
                this.materialGroups.put(decal.material, array3);
            }
            array3.add(decal);
        }
        array.clear();
        ObjectMap.Values<Array<Decal>> it = this.materialGroups.values().iterator();
        while (it.hasNext()) {
            array.addAll(it.next());
        }
        this.materialGroups.clear();
        this.arrayPool.freeAll(this.usedArrays);
        this.usedArrays.clear();
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int i) {
        if (i == 1) {
            Gdx.gl.glDisable(3042);
            Gdx.gl.glDepthMask(true);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroups() {
        Gdx.gl.glEnable(2929);
        this.shader.bind();
        this.shader.setUniformMatrix("u_projectionViewMatrix", this.camera.combined);
        this.shader.setUniformi("u_texture", 0);
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroups() {
        Gdx.gl.glDisable(2929);
    }

    private void createDefaultShader() {
        this.shader = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projectionViewMatrix;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projectionViewMatrix * a_position;\n}\n", "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if (!this.shader.isCompiled()) {
            throw new IllegalArgumentException("couldn't compile shader: " + this.shader.getLog());
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public ShaderProgram getGroupShader(int i) {
        return this.shader;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.shader != null) {
            this.shader.dispose();
        }
    }
}
