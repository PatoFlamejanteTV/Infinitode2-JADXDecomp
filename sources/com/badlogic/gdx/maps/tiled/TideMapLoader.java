package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TideMapLoader.class */
public class TideMapLoader extends SynchronousAssetLoader<TiledMap, Parameters> {
    private XmlReader xml;
    private XmlReader.Element root;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TideMapLoader$Parameters.class */
    public static class Parameters extends AssetLoaderParameters<TiledMap> {
    }

    public TideMapLoader() {
        super(new InternalFileHandleResolver());
        this.xml = new XmlReader();
    }

    public TideMapLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.xml = new XmlReader();
    }

    public TiledMap load(String str) {
        try {
            FileHandle resolve = resolve(str);
            this.root = this.xml.parse(resolve);
            ObjectMap objectMap = new ObjectMap();
            Array.ArrayIterator<FileHandle> it = loadTileSheets(this.root, resolve).iterator();
            while (it.hasNext()) {
                FileHandle next = it.next();
                objectMap.put(next.path(), new Texture(next));
            }
            TiledMap loadMap = loadMap(this.root, resolve, new ImageResolver.DirectImageResolver(objectMap));
            loadMap.setOwnedResources(objectMap.values().toArray());
            return loadMap;
        } catch (IOException e) {
            throw new GdxRuntimeException("Couldn't load tilemap '" + str + "'", e);
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public TiledMap load(AssetManager assetManager, String str, FileHandle fileHandle, Parameters parameters) {
        try {
            return loadMap(this.root, fileHandle, new ImageResolver.AssetManagerImageResolver(assetManager));
        } catch (Exception e) {
            throw new GdxRuntimeException("Couldn't load tilemap '" + str + "'", e);
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, Parameters parameters) {
        Array<AssetDescriptor> array = new Array<>();
        try {
            this.root = this.xml.parse(fileHandle);
            Array.ArrayIterator<FileHandle> it = loadTileSheets(this.root, fileHandle).iterator();
            while (it.hasNext()) {
                array.add(new AssetDescriptor(it.next().path(), Texture.class));
            }
            return array;
        } catch (IOException e) {
            throw new GdxRuntimeException("Couldn't load tilemap '" + str + "'", e);
        }
    }

    private TiledMap loadMap(XmlReader.Element element, FileHandle fileHandle, ImageResolver imageResolver) {
        TiledMap tiledMap = new TiledMap();
        XmlReader.Element childByName = element.getChildByName("Properties");
        if (childByName != null) {
            loadProperties(tiledMap.getProperties(), childByName);
        }
        Array.ArrayIterator<XmlReader.Element> it = element.getChildByName("TileSheets").getChildrenByName("TileSheet").iterator();
        while (it.hasNext()) {
            loadTileSheet(tiledMap, it.next(), fileHandle, imageResolver);
        }
        Array.ArrayIterator<XmlReader.Element> it2 = element.getChildByName("Layers").getChildrenByName("Layer").iterator();
        while (it2.hasNext()) {
            loadLayer(tiledMap, it2.next());
        }
        return tiledMap;
    }

    private Array<FileHandle> loadTileSheets(XmlReader.Element element, FileHandle fileHandle) {
        Array<FileHandle> array = new Array<>();
        Array.ArrayIterator<XmlReader.Element> it = element.getChildByName("TileSheets").getChildrenByName("TileSheet").iterator();
        while (it.hasNext()) {
            array.add(getRelativeFileHandle(fileHandle, it.next().getChildByName("ImageSource").getText()));
        }
        return array;
    }

    private void loadTileSheet(TiledMap tiledMap, XmlReader.Element element, FileHandle fileHandle, ImageResolver imageResolver) {
        if (element.getName().equals("TileSheet")) {
            String attribute = element.getAttribute("Id");
            element.getChildByName("Description").getText();
            String text = element.getChildByName("ImageSource").getText();
            XmlReader.Element childByName = element.getChildByName("Alignment");
            String attribute2 = childByName.getAttribute("SheetSize");
            String attribute3 = childByName.getAttribute("TileSize");
            String attribute4 = childByName.getAttribute("Margin");
            childByName.getAttribute("Spacing");
            String[] split = attribute2.split(" x ");
            Integer.parseInt(split[0]);
            Integer.parseInt(split[1]);
            String[] split2 = attribute3.split(" x ");
            int parseInt = Integer.parseInt(split2[0]);
            int parseInt2 = Integer.parseInt(split2[1]);
            String[] split3 = attribute4.split(" x ");
            int parseInt3 = Integer.parseInt(split3[0]);
            int parseInt4 = Integer.parseInt(split3[1]);
            String[] split4 = attribute4.split(" x ");
            int parseInt5 = Integer.parseInt(split4[0]);
            int parseInt6 = Integer.parseInt(split4[1]);
            TextureRegion image = imageResolver.getImage(getRelativeFileHandle(fileHandle, text).path());
            TiledMapTileSets tileSets = tiledMap.getTileSets();
            int i = 1;
            Iterator<TiledMapTileSet> it = tileSets.iterator();
            while (it.hasNext()) {
                i += it.next().size();
            }
            TiledMapTileSet tiledMapTileSet = new TiledMapTileSet();
            tiledMapTileSet.setName(attribute);
            tiledMapTileSet.getProperties().put("firstgid", Integer.valueOf(i));
            int i2 = i;
            int regionWidth = image.getRegionWidth() - parseInt;
            int regionHeight = image.getRegionHeight() - parseInt2;
            int i3 = parseInt4;
            while (true) {
                int i4 = i3;
                if (i4 > regionHeight) {
                    break;
                }
                int i5 = parseInt3;
                while (true) {
                    int i6 = i5;
                    if (i6 <= regionWidth) {
                        StaticTiledMapTile staticTiledMapTile = new StaticTiledMapTile(new TextureRegion(image, i6, i4, parseInt, parseInt2));
                        staticTiledMapTile.setId(i2);
                        int i7 = i2;
                        i2++;
                        tiledMapTileSet.putTile(i7, staticTiledMapTile);
                        i5 = i6 + parseInt + parseInt5;
                    }
                }
                i3 = i4 + parseInt2 + parseInt6;
            }
            XmlReader.Element childByName2 = element.getChildByName("Properties");
            if (childByName2 != null) {
                loadProperties(tiledMapTileSet.getProperties(), childByName2);
            }
            tileSets.addTileSet(tiledMapTileSet);
        }
    }

    private void loadLayer(TiledMap tiledMap, XmlReader.Element element) {
        if (element.getName().equals("Layer")) {
            String attribute = element.getAttribute("Id");
            String attribute2 = element.getAttribute("Visible");
            XmlReader.Element childByName = element.getChildByName("Dimensions");
            String attribute3 = childByName.getAttribute("LayerSize");
            String attribute4 = childByName.getAttribute("TileSize");
            String[] split = attribute3.split(" x ");
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            String[] split2 = attribute4.split(" x ");
            TiledMapTileLayer tiledMapTileLayer = new TiledMapTileLayer(parseInt, parseInt2, Integer.parseInt(split2[0]), Integer.parseInt(split2[1]));
            tiledMapTileLayer.setName(attribute);
            tiledMapTileLayer.setVisible(attribute2.equalsIgnoreCase("True"));
            Array<XmlReader.Element> childrenByName = element.getChildByName("TileArray").getChildrenByName("Row");
            TiledMapTileSets tileSets = tiledMap.getTileSets();
            TiledMapTileSet tiledMapTileSet = null;
            int i = 0;
            int i2 = childrenByName.size;
            for (int i3 = 0; i3 < i2; i3++) {
                XmlReader.Element element2 = childrenByName.get(i3);
                int i4 = (i2 - 1) - i3;
                int i5 = 0;
                int childCount = element2.getChildCount();
                for (int i6 = 0; i6 < childCount; i6++) {
                    XmlReader.Element child = element2.getChild(i6);
                    String name = child.getName();
                    if (name.equals("TileSheet")) {
                        TiledMapTileSet tileSet = tileSets.getTileSet(child.getAttribute("Ref"));
                        tiledMapTileSet = tileSet;
                        i = ((Integer) tileSet.getProperties().get("firstgid", Integer.class)).intValue();
                    } else if (name.equals("Null")) {
                        i5 += child.getIntAttribute("Count");
                    } else if (!name.equals("Static")) {
                        if (name.equals("Animated")) {
                            int i7 = child.getInt("Interval");
                            XmlReader.Element childByName2 = child.getChildByName("Frames");
                            Array array = new Array();
                            int childCount2 = childByName2.getChildCount();
                            for (int i8 = 0; i8 < childCount2; i8++) {
                                XmlReader.Element child2 = childByName2.getChild(i8);
                                String name2 = child2.getName();
                                if (name2.equals("TileSheet")) {
                                    TiledMapTileSet tileSet2 = tileSets.getTileSet(child2.getAttribute("Ref"));
                                    tiledMapTileSet = tileSet2;
                                    i = ((Integer) tileSet2.getProperties().get("firstgid", Integer.class)).intValue();
                                } else if (name2.equals("Static")) {
                                    array.add((StaticTiledMapTile) tiledMapTileSet.getTile(i + child2.getIntAttribute("Index")));
                                }
                            }
                            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                            cell.setTile(new AnimatedTiledMapTile(i7 / 1000.0f, (Array<StaticTiledMapTile>) array));
                            int i9 = i5;
                            i5++;
                            tiledMapTileLayer.setCell(i9, i4, cell);
                        }
                    } else {
                        TiledMapTileLayer.Cell cell2 = new TiledMapTileLayer.Cell();
                        cell2.setTile(tiledMapTileSet.getTile(i + child.getIntAttribute("Index")));
                        int i10 = i5;
                        i5++;
                        tiledMapTileLayer.setCell(i10, i4, cell2);
                    }
                }
            }
            XmlReader.Element childByName3 = element.getChildByName("Properties");
            if (childByName3 != null) {
                loadProperties(tiledMapTileLayer.getProperties(), childByName3);
            }
            tiledMap.getLayers().add(tiledMapTileLayer);
        }
    }

    private void loadProperties(MapProperties mapProperties, XmlReader.Element element) {
        if (element.getName().equals("Properties")) {
            Array.ArrayIterator<XmlReader.Element> it = element.getChildrenByName("Property").iterator();
            while (it.hasNext()) {
                XmlReader.Element next = it.next();
                String attribute = next.getAttribute("Key", null);
                String attribute2 = next.getAttribute("Type", null);
                String text = next.getText();
                if (attribute2.equals("Int32")) {
                    mapProperties.put(attribute, Integer.valueOf(Integer.parseInt(text)));
                } else if (!attribute2.equals("String") && attribute2.equals("Boolean")) {
                    mapProperties.put(attribute, Boolean.valueOf(text.equalsIgnoreCase("true")));
                } else {
                    mapProperties.put(attribute, text);
                }
            }
        }
    }

    private static FileHandle getRelativeFileHandle(FileHandle fileHandle, String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\\/");
        FileHandle parent = fileHandle.parent();
        while (true) {
            FileHandle fileHandle2 = parent;
            if (stringTokenizer.hasMoreElements()) {
                String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("..")) {
                    parent = fileHandle2.parent();
                } else {
                    parent = fileHandle2.child(nextToken);
                }
            } else {
                return fileHandle2;
            }
        }
    }
}
