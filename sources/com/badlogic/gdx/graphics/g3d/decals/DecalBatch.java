package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.SortedIntList;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/decals/DecalBatch.class */
public class DecalBatch implements Disposable {
    private static final int DEFAULT_SIZE = 1000;
    private float[] vertices;
    private Mesh mesh;
    private final SortedIntList<Array<Decal>> groupList;
    private GroupStrategy groupStrategy;
    private final Pool<Array<Decal>> groupPool;
    private final Array<Array<Decal>> usedGroups;

    public DecalBatch(GroupStrategy groupStrategy) {
        this(1000, groupStrategy);
    }

    public DecalBatch(int i, GroupStrategy groupStrategy) {
        this.groupList = new SortedIntList<>();
        this.groupPool = new Pool<Array<Decal>>(16) { // from class: com.badlogic.gdx.graphics.g3d.decals.DecalBatch.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.Pool
            public Array<Decal> newObject() {
                return new Array<>(false, 100);
            }
        };
        this.usedGroups = new Array<>(16);
        initialize(i);
        setGroupStrategy(groupStrategy);
    }

    public void setGroupStrategy(GroupStrategy groupStrategy) {
        this.groupStrategy = groupStrategy;
    }

    public void initialize(int i) {
        this.vertices = new float[i * 24];
        Mesh.VertexDataType vertexDataType = Mesh.VertexDataType.VertexArray;
        if (Gdx.gl30 != null) {
            vertexDataType = Mesh.VertexDataType.VertexBufferObjectWithVAO;
        }
        this.mesh = new Mesh(vertexDataType, false, i << 2, i * 6, new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
        short[] sArr = new short[i * 6];
        int i2 = 0;
        int i3 = 0;
        while (i3 < sArr.length) {
            sArr[i3] = (short) i2;
            sArr[i3 + 1] = (short) (i2 + 2);
            sArr[i3 + 2] = (short) (i2 + 1);
            sArr[i3 + 3] = (short) (i2 + 1);
            sArr[i3 + 4] = (short) (i2 + 2);
            sArr[i3 + 5] = (short) (i2 + 3);
            i3 += 6;
            i2 += 4;
        }
        this.mesh.setIndices(sArr);
    }

    public int getSize() {
        return this.vertices.length / 24;
    }

    public void add(Decal decal) {
        int decideGroup = this.groupStrategy.decideGroup(decal);
        Array<Decal> array = this.groupList.get(decideGroup);
        Array<Decal> array2 = array;
        if (array == null) {
            Array<Decal> obtain = this.groupPool.obtain();
            array2 = obtain;
            obtain.clear();
            this.usedGroups.add(array2);
            this.groupList.insert(decideGroup, array2);
        }
        array2.add(decal);
    }

    public void flush() {
        render();
        clear();
    }

    protected void render() {
        this.groupStrategy.beforeGroups();
        Iterator<SortedIntList.Node<Array<Decal>>> it = this.groupList.iterator();
        while (it.hasNext()) {
            SortedIntList.Node<Array<Decal>> next = it.next();
            this.groupStrategy.beforeGroup(next.index, next.value);
            render(this.groupStrategy.getGroupShader(next.index), next.value);
            this.groupStrategy.afterGroup(next.index);
        }
        this.groupStrategy.afterGroups();
    }

    private void render(ShaderProgram shaderProgram, Array<Decal> array) {
        DecalMaterial decalMaterial = null;
        int i = 0;
        Array.ArrayIterator<Decal> it = array.iterator();
        while (it.hasNext()) {
            Decal next = it.next();
            if (decalMaterial == null || !decalMaterial.equals(next.getMaterial())) {
                if (i > 0) {
                    flush(shaderProgram, i);
                    i = 0;
                }
                next.material.set();
                decalMaterial = next.material;
            }
            next.update();
            System.arraycopy(next.vertices, 0, this.vertices, i, next.vertices.length);
            int length = i + next.vertices.length;
            i = length;
            if (length == this.vertices.length) {
                flush(shaderProgram, i);
                i = 0;
            }
        }
        if (i > 0) {
            flush(shaderProgram, i);
        }
    }

    protected void flush(ShaderProgram shaderProgram, int i) {
        this.mesh.setVertices(this.vertices, 0, i);
        this.mesh.render(shaderProgram, 4, 0, i / 4);
    }

    protected void clear() {
        this.groupList.clear();
        this.groupPool.freeAll(this.usedGroups);
        this.usedGroups.clear();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        clear();
        this.vertices = null;
        this.mesh.dispose();
    }
}
