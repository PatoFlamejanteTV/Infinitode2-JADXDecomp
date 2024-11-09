package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StreamUtils;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEffect.class */
public class ParticleEffect implements Disposable {
    private final Array<ParticleEmitter> emitters;
    private BoundingBox bounds;
    private boolean ownsTexture;
    protected float xSizeScale;
    protected float ySizeScale;
    protected float motionScale;

    public ParticleEffect() {
        this.xSizeScale = 1.0f;
        this.ySizeScale = 1.0f;
        this.motionScale = 1.0f;
        this.emitters = new Array<>(8);
    }

    public ParticleEffect(ParticleEffect particleEffect) {
        this.xSizeScale = 1.0f;
        this.ySizeScale = 1.0f;
        this.motionScale = 1.0f;
        this.emitters = new Array<>(true, particleEffect.emitters.size);
        int i = particleEffect.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.add(newEmitter(particleEffect.emitters.get(i2)));
        }
    }

    public void start() {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).start();
        }
    }

    public void reset() {
        reset(true, true);
    }

    public void reset(boolean z) {
        reset(z, true);
    }

    public void reset(boolean z, boolean z2) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).reset(z2);
        }
        if (z) {
            if (this.xSizeScale != 1.0f || this.ySizeScale != 1.0f || this.motionScale != 1.0f) {
                scaleEffect(1.0f / this.xSizeScale, 1.0f / this.ySizeScale, 1.0f / this.motionScale);
                this.motionScale = 1.0f;
                this.ySizeScale = 1.0f;
                this.xSizeScale = 1.0f;
            }
        }
    }

    public void update(float f) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).update(f);
        }
    }

    public void draw(Batch batch) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).draw(batch);
        }
    }

    public void draw(Batch batch, float f) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).draw(batch, f);
        }
    }

    public void allowCompletion() {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).allowCompletion();
        }
    }

    public boolean isComplete() {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (!this.emitters.get(i2).isComplete()) {
                return false;
            }
        }
        return true;
    }

    public void setDuration(int i) {
        int i2 = this.emitters.size;
        for (int i3 = 0; i3 < i2; i3++) {
            ParticleEmitter particleEmitter = this.emitters.get(i3);
            particleEmitter.setContinuous(false);
            particleEmitter.duration = i;
            particleEmitter.durationTimer = 0.0f;
        }
    }

    public void setPosition(float f, float f2) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).setPosition(f, f2);
        }
    }

    public void setFlip(boolean z, boolean z2) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).setFlip(z, z2);
        }
    }

    public void flipY() {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).flipY();
        }
    }

    public Array<ParticleEmitter> getEmitters() {
        return this.emitters;
    }

    public ParticleEmitter findEmitter(String str) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            ParticleEmitter particleEmitter = this.emitters.get(i2);
            if (particleEmitter.getName().equals(str)) {
                return particleEmitter;
            }
        }
        return null;
    }

    public void preAllocateParticles() {
        Array.ArrayIterator<ParticleEmitter> it = this.emitters.iterator();
        while (it.hasNext()) {
            it.next().preAllocateParticles();
        }
    }

    public void save(Writer writer) {
        int i = 0;
        int i2 = this.emitters.size;
        for (int i3 = 0; i3 < i2; i3++) {
            ParticleEmitter particleEmitter = this.emitters.get(i3);
            int i4 = i;
            i++;
            if (i4 > 0) {
                writer.write(SequenceUtils.EOL);
            }
            particleEmitter.save(writer);
        }
    }

    public void load(FileHandle fileHandle, FileHandle fileHandle2) {
        loadEmitters(fileHandle);
        loadEmitterImages(fileHandle2);
    }

    public void load(FileHandle fileHandle, TextureAtlas textureAtlas) {
        load(fileHandle, textureAtlas, null);
    }

    public void load(FileHandle fileHandle, TextureAtlas textureAtlas, String str) {
        loadEmitters(fileHandle);
        loadEmitterImages(textureAtlas, str);
    }

    public void loadEmitters(FileHandle fileHandle) {
        InputStream read = fileHandle.read();
        this.emitters.clear();
        BufferedReader bufferedReader = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(read), 512);
                do {
                    this.emitters.add(newEmitter(bufferedReader));
                } while (bufferedReader.readLine() != null);
                StreamUtils.closeQuietly(bufferedReader);
            } catch (IOException e) {
                throw new GdxRuntimeException("Error loading effect: " + fileHandle, e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(bufferedReader);
            throw th;
        }
    }

    public void loadEmitterImages(TextureAtlas textureAtlas) {
        loadEmitterImages(textureAtlas, null);
    }

    public void loadEmitterImages(TextureAtlas textureAtlas, String str) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            ParticleEmitter particleEmitter = this.emitters.get(i2);
            if (particleEmitter.getImagePaths().size != 0) {
                Array<Sprite> array = new Array<>();
                Array.ArrayIterator<String> it = particleEmitter.getImagePaths().iterator();
                while (it.hasNext()) {
                    String name = new File(it.next().replace('\\', '/')).getName();
                    String str2 = name;
                    int lastIndexOf = name.lastIndexOf(46);
                    if (lastIndexOf != -1) {
                        str2 = str2.substring(0, lastIndexOf);
                    }
                    if (str != null) {
                        str2 = str + str2;
                    }
                    Sprite createSprite = textureAtlas.createSprite(str2);
                    if (createSprite == null) {
                        throw new IllegalArgumentException("Atlas is missing region: " + str2);
                    }
                    array.add(createSprite);
                }
                particleEmitter.setSprites(array);
            }
        }
    }

    public void loadEmitterImages(FileHandle fileHandle) {
        this.ownsTexture = true;
        ObjectMap objectMap = new ObjectMap(this.emitters.size);
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            ParticleEmitter particleEmitter = this.emitters.get(i2);
            if (particleEmitter.getImagePaths().size != 0) {
                Array<Sprite> array = new Array<>();
                Array.ArrayIterator<String> it = particleEmitter.getImagePaths().iterator();
                while (it.hasNext()) {
                    String name = new File(it.next().replace('\\', '/')).getName();
                    Sprite sprite = (Sprite) objectMap.get(name);
                    Sprite sprite2 = sprite;
                    if (sprite == null) {
                        sprite2 = new Sprite(loadTexture(fileHandle.child(name)));
                        objectMap.put(name, sprite2);
                    }
                    array.add(sprite2);
                }
                particleEmitter.setSprites(array);
            }
        }
    }

    protected ParticleEmitter newEmitter(BufferedReader bufferedReader) {
        return new ParticleEmitter(bufferedReader);
    }

    protected ParticleEmitter newEmitter(ParticleEmitter particleEmitter) {
        return new ParticleEmitter(particleEmitter);
    }

    protected Texture loadTexture(FileHandle fileHandle) {
        return new Texture(fileHandle, false);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.ownsTexture) {
            int i = this.emitters.size;
            for (int i2 = 0; i2 < i; i2++) {
                Array.ArrayIterator<Sprite> it = this.emitters.get(i2).getSprites().iterator();
                while (it.hasNext()) {
                    it.next().getTexture().dispose();
                }
            }
        }
    }

    public BoundingBox getBoundingBox() {
        if (this.bounds == null) {
            this.bounds = new BoundingBox();
        }
        BoundingBox boundingBox = this.bounds;
        boundingBox.inf();
        Array.ArrayIterator<ParticleEmitter> it = this.emitters.iterator();
        while (it.hasNext()) {
            boundingBox.ext(it.next().getBoundingBox());
        }
        return boundingBox;
    }

    public void scaleEffect(float f) {
        scaleEffect(f, f, f);
    }

    public void scaleEffect(float f, float f2) {
        scaleEffect(f, f, f2);
    }

    public void scaleEffect(float f, float f2, float f3) {
        this.xSizeScale *= f;
        this.ySizeScale *= f2;
        this.motionScale *= f3;
        Array.ArrayIterator<ParticleEmitter> it = this.emitters.iterator();
        while (it.hasNext()) {
            ParticleEmitter next = it.next();
            next.scaleSize(f, f2);
            next.scaleMotion(f3);
        }
    }

    public void setEmittersCleanUpBlendFunction(boolean z) {
        int i = this.emitters.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.emitters.get(i2).setCleansUpBlendFunction(z);
        }
    }
}
