package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import com.vladsch.flexmark.util.html.Attribute;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TmxMapLoader.class */
public class TmxMapLoader extends BaseTmxMapLoader<Parameters> {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TmxMapLoader$Parameters.class */
    public static class Parameters extends BaseTmxMapLoader.Parameters {
    }

    public TmxMapLoader() {
        super(new InternalFileHandleResolver());
    }

    public TmxMapLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    public TiledMap load(String str) {
        return load(str, new Parameters());
    }

    public TiledMap load(String str, Parameters parameters) {
        FileHandle resolve = resolve(str);
        this.root = this.xml.parse(resolve);
        ObjectMap objectMap = new ObjectMap();
        Array.ArrayIterator<FileHandle> it = getDependencyFileHandles(resolve).iterator();
        while (it.hasNext()) {
            FileHandle next = it.next();
            Texture texture = new Texture(next, parameters.generateMipMaps);
            texture.setFilter(parameters.textureMinFilter, parameters.textureMagFilter);
            objectMap.put(next.path(), texture);
        }
        TiledMap loadTiledMap = loadTiledMap(resolve, parameters, new ImageResolver.DirectImageResolver(objectMap));
        loadTiledMap.setOwnedResources(objectMap.values().toArray());
        return loadTiledMap;
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, Parameters parameters) {
        this.map = loadTiledMap(fileHandle, parameters, new ImageResolver.AssetManagerImageResolver(assetManager));
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public TiledMap loadSync(AssetManager assetManager, String str, FileHandle fileHandle, Parameters parameters) {
        return this.map;
    }

    @Override // com.badlogic.gdx.maps.tiled.BaseTmxMapLoader
    protected Array<AssetDescriptor> getDependencyAssetDescriptors(FileHandle fileHandle, TextureLoader.TextureParameter textureParameter) {
        Array<AssetDescriptor> array = new Array<>();
        Array.ArrayIterator<FileHandle> it = getDependencyFileHandles(fileHandle).iterator();
        while (it.hasNext()) {
            array.add(new AssetDescriptor(it.next(), Texture.class, textureParameter));
        }
        return array;
    }

    protected Array<FileHandle> getDependencyFileHandles(FileHandle fileHandle) {
        Array<FileHandle> array = new Array<>();
        Array.ArrayIterator<XmlReader.Element> it = this.root.getChildrenByName("tileset").iterator();
        while (it.hasNext()) {
            XmlReader.Element next = it.next();
            String attribute = next.getAttribute("source", null);
            if (attribute != null) {
                FileHandle relativeFileHandle = getRelativeFileHandle(fileHandle, attribute);
                XmlReader.Element parse = this.xml.parse(relativeFileHandle);
                if (parse.getChildByName("image") != null) {
                    array.add(getRelativeFileHandle(relativeFileHandle, parse.getChildByName("image").getAttribute("source")));
                } else {
                    Array.ArrayIterator<XmlReader.Element> it2 = parse.getChildrenByName("tile").iterator();
                    while (it2.hasNext()) {
                        array.add(getRelativeFileHandle(relativeFileHandle, it2.next().getChildByName("image").getAttribute("source")));
                    }
                }
            } else if (next.getChildByName("image") != null) {
                array.add(getRelativeFileHandle(fileHandle, next.getChildByName("image").getAttribute("source")));
            } else {
                Array.ArrayIterator<XmlReader.Element> it3 = next.getChildrenByName("tile").iterator();
                while (it3.hasNext()) {
                    array.add(getRelativeFileHandle(fileHandle, it3.next().getChildByName("image").getAttribute("source")));
                }
            }
        }
        Array.ArrayIterator<XmlReader.Element> it4 = this.root.getChildrenByName("imagelayer").iterator();
        while (it4.hasNext()) {
            String attribute2 = it4.next().getChildByName("image").getAttribute("source", null);
            if (attribute2 != null) {
                array.add(getRelativeFileHandle(fileHandle, attribute2));
            }
        }
        return array;
    }

    @Override // com.badlogic.gdx.maps.tiled.BaseTmxMapLoader
    protected void addStaticTiles(FileHandle fileHandle, ImageResolver imageResolver, TiledMapTileSet tiledMapTileSet, XmlReader.Element element, Array<XmlReader.Element> array, String str, int i, int i2, int i3, int i4, int i5, String str2, int i6, int i7, String str3, int i8, int i9, FileHandle fileHandle2) {
        MapProperties properties = tiledMapTileSet.getProperties();
        if (fileHandle2 != null) {
            TextureRegion image = imageResolver.getImage(fileHandle2.path());
            properties.put("imagesource", str3);
            properties.put("imagewidth", Integer.valueOf(i8));
            properties.put("imageheight", Integer.valueOf(i9));
            properties.put("tilewidth", Integer.valueOf(i2));
            properties.put("tileheight", Integer.valueOf(i3));
            properties.put("margin", Integer.valueOf(i5));
            properties.put("spacing", Integer.valueOf(i4));
            int regionWidth = image.getRegionWidth() - i2;
            int regionHeight = image.getRegionHeight() - i3;
            int i10 = i;
            int i11 = i5;
            while (true) {
                int i12 = i11;
                if (i12 <= regionHeight) {
                    int i13 = i5;
                    while (true) {
                        int i14 = i13;
                        if (i14 <= regionWidth) {
                            TextureRegion textureRegion = new TextureRegion(image, i14, i12, i2, i3);
                            int i15 = i10;
                            i10++;
                            addStaticTiledMapTile(tiledMapTileSet, textureRegion, i15, i6, i7);
                            i13 = i14 + i2 + i4;
                        }
                    }
                    i11 = i12 + i3 + i4;
                } else {
                    return;
                }
            }
        } else {
            Array.ArrayIterator<XmlReader.Element> it = array.iterator();
            while (it.hasNext()) {
                XmlReader.Element next = it.next();
                XmlReader.Element childByName = next.getChildByName("image");
                if (childByName != null) {
                    String attribute = childByName.getAttribute("source");
                    if (str2 != null) {
                        fileHandle2 = getRelativeFileHandle(getRelativeFileHandle(fileHandle, str2), attribute);
                    } else {
                        fileHandle2 = getRelativeFileHandle(fileHandle, attribute);
                    }
                }
                addStaticTiledMapTile(tiledMapTileSet, imageResolver.getImage(fileHandle2.path()), i + next.getIntAttribute(Attribute.ID_ATTR), i6, i7);
            }
        }
    }
}
