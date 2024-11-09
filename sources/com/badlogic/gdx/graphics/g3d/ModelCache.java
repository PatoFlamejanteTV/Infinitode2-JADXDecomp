package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderableSorter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FlushablePool;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/ModelCache.class */
public class ModelCache implements RenderableProvider, Disposable {
    private Array<Renderable> renderables;
    private FlushablePool<Renderable> renderablesPool;
    private FlushablePool<MeshPart> meshPartPool;
    private Array<Renderable> items;
    private Array<Renderable> tmp;
    private MeshBuilder meshBuilder;
    private boolean building;
    private RenderableSorter sorter;
    private MeshPool meshPool;
    private Camera camera;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/ModelCache$MeshPool.class */
    public interface MeshPool extends Disposable {
        Mesh obtain(VertexAttributes vertexAttributes, int i, int i2);

        void flush();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/ModelCache$SimpleMeshPool.class */
    public static class SimpleMeshPool implements MeshPool {
        private Array<Mesh> freeMeshes = new Array<>();
        private Array<Mesh> usedMeshes = new Array<>();

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public void flush() {
            this.freeMeshes.addAll(this.usedMeshes);
            this.usedMeshes.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public Mesh obtain(VertexAttributes vertexAttributes, int i, int i2) {
            int i3 = this.freeMeshes.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Mesh mesh = this.freeMeshes.get(i4);
                if (mesh.getVertexAttributes().equals(vertexAttributes) && mesh.getMaxVertices() >= i && mesh.getMaxIndices() >= i2) {
                    this.freeMeshes.removeIndex(i4);
                    this.usedMeshes.add(mesh);
                    return mesh;
                }
            }
            Mesh mesh2 = new Mesh(false, 65536, Math.max(65536, 1 << (32 - Integer.numberOfLeadingZeros(i2 - 1))), vertexAttributes);
            this.usedMeshes.add(mesh2);
            return mesh2;
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            Array.ArrayIterator<Mesh> it = this.usedMeshes.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
            this.usedMeshes.clear();
            Array.ArrayIterator<Mesh> it2 = this.freeMeshes.iterator();
            while (it2.hasNext()) {
                it2.next().dispose();
            }
            this.freeMeshes.clear();
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/ModelCache$TightMeshPool.class */
    public static class TightMeshPool implements MeshPool {
        private Array<Mesh> freeMeshes = new Array<>();
        private Array<Mesh> usedMeshes = new Array<>();

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public void flush() {
            this.freeMeshes.addAll(this.usedMeshes);
            this.usedMeshes.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public Mesh obtain(VertexAttributes vertexAttributes, int i, int i2) {
            int i3 = this.freeMeshes.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Mesh mesh = this.freeMeshes.get(i4);
                if (mesh.getVertexAttributes().equals(vertexAttributes) && mesh.getMaxVertices() == i && mesh.getMaxIndices() == i2) {
                    this.freeMeshes.removeIndex(i4);
                    this.usedMeshes.add(mesh);
                    return mesh;
                }
            }
            Mesh mesh2 = new Mesh(true, i, i2, vertexAttributes);
            this.usedMeshes.add(mesh2);
            return mesh2;
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            Array.ArrayIterator<Mesh> it = this.usedMeshes.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
            this.usedMeshes.clear();
            Array.ArrayIterator<Mesh> it2 = this.freeMeshes.iterator();
            while (it2.hasNext()) {
                it2.next().dispose();
            }
            this.freeMeshes.clear();
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/ModelCache$Sorter.class */
    public static class Sorter implements RenderableSorter, Comparator<Renderable> {
        @Override // com.badlogic.gdx.graphics.g3d.utils.RenderableSorter
        public void sort(Camera camera, Array<Renderable> array) {
            array.sort(this);
        }

        @Override // java.util.Comparator
        public int compare(Renderable renderable, Renderable renderable2) {
            int compareTo = renderable.meshPart.mesh.getVertexAttributes().compareTo(renderable2.meshPart.mesh.getVertexAttributes());
            if (compareTo == 0) {
                int compareTo2 = renderable.material.compareTo((Attributes) renderable2.material);
                if (compareTo2 == 0) {
                    return renderable.meshPart.primitiveType - renderable2.meshPart.primitiveType;
                }
                return compareTo2;
            }
            return compareTo;
        }
    }

    public ModelCache() {
        this(new Sorter(), new SimpleMeshPool());
    }

    public ModelCache(RenderableSorter renderableSorter, MeshPool meshPool) {
        this.renderables = new Array<>();
        this.renderablesPool = new FlushablePool<Renderable>() { // from class: com.badlogic.gdx.graphics.g3d.ModelCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.badlogic.gdx.utils.Pool
            public Renderable newObject() {
                return new Renderable();
            }
        };
        this.meshPartPool = new FlushablePool<MeshPart>() { // from class: com.badlogic.gdx.graphics.g3d.ModelCache.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.badlogic.gdx.utils.Pool
            public MeshPart newObject() {
                return new MeshPart();
            }
        };
        this.items = new Array<>();
        this.tmp = new Array<>();
        this.sorter = renderableSorter;
        this.meshPool = meshPool;
        this.meshBuilder = new MeshBuilder();
    }

    public void begin() {
        begin(null);
    }

    public void begin(Camera camera) {
        if (this.building) {
            throw new GdxRuntimeException("Call end() after calling begin()");
        }
        this.building = true;
        this.camera = camera;
        this.renderablesPool.flush();
        this.renderables.clear();
        this.items.clear();
        this.meshPartPool.flush();
        this.meshPool.flush();
    }

    private Renderable obtainRenderable(Material material, int i) {
        Renderable obtain = this.renderablesPool.obtain();
        obtain.bones = null;
        obtain.environment = null;
        obtain.material = material;
        obtain.meshPart.mesh = null;
        obtain.meshPart.offset = 0;
        obtain.meshPart.size = 0;
        obtain.meshPart.primitiveType = i;
        obtain.meshPart.center.set(0.0f, 0.0f, 0.0f);
        obtain.meshPart.halfExtents.set(0.0f, 0.0f, 0.0f);
        obtain.meshPart.radius = -1.0f;
        obtain.shader = null;
        obtain.userData = null;
        obtain.worldTransform.idt();
        return obtain;
    }

    public void end() {
        if (!this.building) {
            throw new GdxRuntimeException("Call begin() prior to calling end()");
        }
        this.building = false;
        if (this.items.size == 0) {
            return;
        }
        this.sorter.sort(this.camera, this.items);
        Renderable renderable = this.items.get(0);
        VertexAttributes vertexAttributes = renderable.meshPart.mesh.getVertexAttributes();
        Material material = renderable.material;
        int i = renderable.meshPart.primitiveType;
        int i2 = this.renderables.size;
        this.meshBuilder.begin(vertexAttributes);
        MeshPart part = this.meshBuilder.part("", i, this.meshPartPool.obtain());
        this.renderables.add(obtainRenderable(material, i));
        int i3 = this.items.size;
        for (int i4 = 0; i4 < i3; i4++) {
            Renderable renderable2 = this.items.get(i4);
            VertexAttributes vertexAttributes2 = renderable2.meshPart.mesh.getVertexAttributes();
            Material material2 = renderable2.material;
            int i5 = renderable2.meshPart.primitiveType;
            boolean z = vertexAttributes2.equals(vertexAttributes) && (this.meshBuilder.getNumVertices() + (renderable2.meshPart.mesh.getNumIndices() > 0 ? renderable2.meshPart.mesh.getNumVertices() : renderable2.meshPart.size) <= 65536);
            boolean z2 = z;
            if (!(z && i5 == i && material2.same(material, true))) {
                if (!z2) {
                    Mesh end = this.meshBuilder.end(this.meshPool.obtain(vertexAttributes, this.meshBuilder.getNumVertices(), this.meshBuilder.getNumIndices()));
                    while (i2 < this.renderables.size) {
                        int i6 = i2;
                        i2++;
                        this.renderables.get(i6).meshPart.mesh = end;
                    }
                    vertexAttributes = vertexAttributes2;
                    this.meshBuilder.begin(vertexAttributes2);
                }
                MeshPart part2 = this.meshBuilder.part("", i5, this.meshPartPool.obtain());
                Renderable renderable3 = this.renderables.get(this.renderables.size - 1);
                renderable3.meshPart.offset = part.offset;
                renderable3.meshPart.size = part.size;
                part = part2;
                material = material2;
                i = i5;
                this.renderables.add(obtainRenderable(material2, i5));
            }
            this.meshBuilder.setVertexTransform(renderable2.worldTransform);
            this.meshBuilder.addMesh(renderable2.meshPart.mesh, renderable2.meshPart.offset, renderable2.meshPart.size);
        }
        Mesh end2 = this.meshBuilder.end(this.meshPool.obtain(vertexAttributes, this.meshBuilder.getNumVertices(), this.meshBuilder.getNumIndices()));
        while (i2 < this.renderables.size) {
            int i7 = i2;
            i2++;
            this.renderables.get(i7).meshPart.mesh = end2;
        }
        Renderable renderable4 = this.renderables.get(this.renderables.size - 1);
        renderable4.meshPart.offset = part.offset;
        renderable4.meshPart.size = part.size;
    }

    public void add(Renderable renderable) {
        if (!this.building) {
            throw new GdxRuntimeException("Can only add items to the ModelCache in between .begin() and .end()");
        }
        if (renderable.bones == null) {
            this.items.add(renderable);
        } else {
            this.renderables.add(renderable);
        }
    }

    public void add(RenderableProvider renderableProvider) {
        renderableProvider.getRenderables(this.tmp, this.renderablesPool);
        int i = this.tmp.size;
        for (int i2 = 0; i2 < i; i2++) {
            add(this.tmp.get(i2));
        }
        this.tmp.clear();
    }

    public <T extends RenderableProvider> void add(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array<Renderable> array, Pool<Renderable> pool) {
        if (this.building) {
            throw new GdxRuntimeException("Cannot render a ModelCache in between .begin() and .end()");
        }
        Array.ArrayIterator<Renderable> it = this.renderables.iterator();
        while (it.hasNext()) {
            Renderable next = it.next();
            next.shader = null;
            next.environment = null;
        }
        array.addAll(this.renderables);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.building) {
            throw new GdxRuntimeException("Cannot dispose a ModelCache in between .begin() and .end()");
        }
        this.meshPool.dispose();
    }
}
