package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.XmlReader;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/BaseTmxMapLoader.class */
public abstract class BaseTmxMapLoader<P extends Parameters> extends AsynchronousAssetLoader<TiledMap, P> {
    protected static final int FLAG_FLIP_HORIZONTALLY = Integer.MIN_VALUE;
    protected static final int FLAG_FLIP_VERTICALLY = 1073741824;
    protected static final int FLAG_FLIP_DIAGONALLY = 536870912;
    protected static final int MASK_CLEAR = -536870912;
    protected XmlReader xml;
    protected XmlReader.Element root;
    protected boolean convertObjectToTileSpace;
    protected boolean flipY;
    protected int mapTileWidth;
    protected int mapTileHeight;
    protected int mapWidthInPixels;
    protected int mapHeightInPixels;
    protected TiledMap map;
    protected IntMap<MapObject> idToObject;
    protected Array<Runnable> runOnEndOfLoadTiled;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/BaseTmxMapLoader$Parameters.class */
    public static class Parameters extends AssetLoaderParameters<TiledMap> {
        public boolean generateMipMaps = false;
        public Texture.TextureFilter textureMinFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureFilter textureMagFilter = Texture.TextureFilter.Nearest;
        public boolean convertObjectToTileSpace = false;
        public boolean flipY = true;
    }

    protected abstract Array<AssetDescriptor> getDependencyAssetDescriptors(FileHandle fileHandle, TextureLoader.TextureParameter textureParameter);

    protected abstract void addStaticTiles(FileHandle fileHandle, ImageResolver imageResolver, TiledMapTileSet tiledMapTileSet, XmlReader.Element element, Array<XmlReader.Element> array, String str, int i, int i2, int i3, int i4, int i5, String str2, int i6, int i7, String str3, int i8, int i9, FileHandle fileHandle2);

    public BaseTmxMapLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.xml = new XmlReader();
        this.flipY = true;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, P p) {
        this.root = this.xml.parse(fileHandle);
        TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
        if (p != null) {
            textureParameter.genMipMaps = p.generateMipMaps;
            textureParameter.minFilter = p.textureMinFilter;
            textureParameter.magFilter = p.textureMagFilter;
        }
        return getDependencyAssetDescriptors(fileHandle, textureParameter);
    }

    @Null
    public IntMap<MapObject> getIdToObject() {
        return this.idToObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TiledMap loadTiledMap(FileHandle fileHandle, P p, ImageResolver imageResolver) {
        this.map = new TiledMap();
        this.idToObject = new IntMap<>();
        this.runOnEndOfLoadTiled = new Array<>();
        if (p != null) {
            this.convertObjectToTileSpace = p.convertObjectToTileSpace;
            this.flipY = p.flipY;
        } else {
            this.convertObjectToTileSpace = false;
            this.flipY = true;
        }
        String attribute = this.root.getAttribute("orientation", null);
        int intAttribute = this.root.getIntAttribute("width", 0);
        int intAttribute2 = this.root.getIntAttribute("height", 0);
        int intAttribute3 = this.root.getIntAttribute("tilewidth", 0);
        int intAttribute4 = this.root.getIntAttribute("tileheight", 0);
        int intAttribute5 = this.root.getIntAttribute("hexsidelength", 0);
        String attribute2 = this.root.getAttribute("staggeraxis", null);
        String attribute3 = this.root.getAttribute("staggerindex", null);
        String attribute4 = this.root.getAttribute("backgroundcolor", null);
        MapProperties properties = this.map.getProperties();
        if (attribute != null) {
            properties.put("orientation", attribute);
        }
        properties.put("width", Integer.valueOf(intAttribute));
        properties.put("height", Integer.valueOf(intAttribute2));
        properties.put("tilewidth", Integer.valueOf(intAttribute3));
        properties.put("tileheight", Integer.valueOf(intAttribute4));
        properties.put("hexsidelength", Integer.valueOf(intAttribute5));
        if (attribute2 != null) {
            properties.put("staggeraxis", attribute2);
        }
        if (attribute3 != null) {
            properties.put("staggerindex", attribute3);
        }
        if (attribute4 != null) {
            properties.put("backgroundcolor", attribute4);
        }
        this.mapTileWidth = intAttribute3;
        this.mapTileHeight = intAttribute4;
        this.mapWidthInPixels = intAttribute * intAttribute3;
        this.mapHeightInPixels = intAttribute2 * intAttribute4;
        if (attribute != null && "staggered".equals(attribute) && intAttribute2 > 1) {
            this.mapWidthInPixels += intAttribute3 / 2;
            this.mapHeightInPixels = (this.mapHeightInPixels / 2) + (intAttribute4 / 2);
        }
        XmlReader.Element childByName = this.root.getChildByName("properties");
        if (childByName != null) {
            loadProperties(this.map.getProperties(), childByName);
        }
        Array.ArrayIterator<XmlReader.Element> it = this.root.getChildrenByName("tileset").iterator();
        while (it.hasNext()) {
            XmlReader.Element next = it.next();
            loadTileSet(next, fileHandle, imageResolver);
            this.root.removeChild(next);
        }
        int childCount = this.root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            loadLayer(this.map, this.map.getLayers(), this.root.getChild(i), fileHandle, imageResolver);
        }
        Array byType = this.map.getLayers().getByType(MapGroupLayer.class);
        while (byType.notEmpty()) {
            MapGroupLayer mapGroupLayer = (MapGroupLayer) byType.first();
            byType.removeIndex(0);
            Iterator<MapLayer> it2 = mapGroupLayer.getLayers().iterator();
            while (it2.hasNext()) {
                MapLayer next2 = it2.next();
                next2.setParallaxX(next2.getParallaxX() * mapGroupLayer.getParallaxX());
                next2.setParallaxY(next2.getParallaxY() * mapGroupLayer.getParallaxY());
                if (next2 instanceof MapGroupLayer) {
                    byType.add((MapGroupLayer) next2);
                }
            }
        }
        Array.ArrayIterator<Runnable> it3 = this.runOnEndOfLoadTiled.iterator();
        while (it3.hasNext()) {
            it3.next().run();
        }
        this.runOnEndOfLoadTiled = null;
        return this.map;
    }

    protected void loadLayer(TiledMap tiledMap, MapLayers mapLayers, XmlReader.Element element, FileHandle fileHandle, ImageResolver imageResolver) {
        String name = element.getName();
        if (name.equals("group")) {
            loadLayerGroup(tiledMap, mapLayers, element, fileHandle, imageResolver);
            return;
        }
        if (name.equals("layer")) {
            loadTileLayer(tiledMap, mapLayers, element);
        } else if (name.equals("objectgroup")) {
            loadObjectGroup(tiledMap, mapLayers, element);
        } else if (name.equals("imagelayer")) {
            loadImageLayer(tiledMap, mapLayers, element, fileHandle, imageResolver);
        }
    }

    protected void loadLayerGroup(TiledMap tiledMap, MapLayers mapLayers, XmlReader.Element element, FileHandle fileHandle, ImageResolver imageResolver) {
        if (element.getName().equals("group")) {
            MapGroupLayer mapGroupLayer = new MapGroupLayer();
            loadBasicLayerInfo(mapGroupLayer, element);
            XmlReader.Element childByName = element.getChildByName("properties");
            if (childByName != null) {
                loadProperties(mapGroupLayer.getProperties(), childByName);
            }
            int childCount = element.getChildCount();
            for (int i = 0; i < childCount; i++) {
                loadLayer(tiledMap, mapGroupLayer.getLayers(), element.getChild(i), fileHandle, imageResolver);
            }
            Iterator<MapLayer> it = mapGroupLayer.getLayers().iterator();
            while (it.hasNext()) {
                it.next().setParent(mapGroupLayer);
            }
            mapLayers.add(mapGroupLayer);
        }
    }

    protected void loadTileLayer(TiledMap tiledMap, MapLayers mapLayers, XmlReader.Element element) {
        if (element.getName().equals("layer")) {
            int intAttribute = element.getIntAttribute("width", 0);
            int intAttribute2 = element.getIntAttribute("height", 0);
            TiledMapTileLayer tiledMapTileLayer = new TiledMapTileLayer(intAttribute, intAttribute2, ((Integer) tiledMap.getProperties().get("tilewidth", Integer.class)).intValue(), ((Integer) tiledMap.getProperties().get("tileheight", Integer.class)).intValue());
            loadBasicLayerInfo(tiledMapTileLayer, element);
            int[] tileIds = getTileIds(element, intAttribute, intAttribute2);
            TiledMapTileSets tileSets = tiledMap.getTileSets();
            for (int i = 0; i < intAttribute2; i++) {
                for (int i2 = 0; i2 < intAttribute; i2++) {
                    int i3 = tileIds[(i * intAttribute) + i2];
                    boolean z = (i3 & Integer.MIN_VALUE) != 0;
                    boolean z2 = (i3 & 1073741824) != 0;
                    boolean z3 = (i3 & 536870912) != 0;
                    TiledMapTile tile = tileSets.getTile(i3 & SegmentTree.MAX_VALUE);
                    if (tile != null) {
                        TiledMapTileLayer.Cell createTileLayerCell = createTileLayerCell(z, z2, z3);
                        createTileLayerCell.setTile(tile);
                        tiledMapTileLayer.setCell(i2, this.flipY ? (intAttribute2 - 1) - i : i, createTileLayerCell);
                    }
                }
            }
            XmlReader.Element childByName = element.getChildByName("properties");
            if (childByName != null) {
                loadProperties(tiledMapTileLayer.getProperties(), childByName);
            }
            mapLayers.add(tiledMapTileLayer);
        }
    }

    protected void loadObjectGroup(TiledMap tiledMap, MapLayers mapLayers, XmlReader.Element element) {
        if (element.getName().equals("objectgroup")) {
            MapLayer mapLayer = new MapLayer();
            loadBasicLayerInfo(mapLayer, element);
            XmlReader.Element childByName = element.getChildByName("properties");
            if (childByName != null) {
                loadProperties(mapLayer.getProperties(), childByName);
            }
            Array.ArrayIterator<XmlReader.Element> it = element.getChildrenByName("object").iterator();
            while (it.hasNext()) {
                loadObject(tiledMap, mapLayer, it.next());
            }
            mapLayers.add(mapLayer);
        }
    }

    protected void loadImageLayer(TiledMap tiledMap, MapLayers mapLayers, XmlReader.Element element, FileHandle fileHandle, ImageResolver imageResolver) {
        float parseFloat;
        float parseFloat2;
        if (element.getName().equals("imagelayer")) {
            if (element.hasAttribute("offsetx")) {
                parseFloat = Float.parseFloat(element.getAttribute("offsetx", "0"));
            } else {
                parseFloat = Float.parseFloat(element.getAttribute("x", "0"));
            }
            if (element.hasAttribute("offsety")) {
                parseFloat2 = Float.parseFloat(element.getAttribute("offsety", "0"));
            } else {
                parseFloat2 = Float.parseFloat(element.getAttribute("y", "0"));
            }
            if (this.flipY) {
                parseFloat2 = this.mapHeightInPixels - parseFloat2;
            }
            TextureRegion textureRegion = null;
            XmlReader.Element childByName = element.getChildByName("image");
            if (childByName != null) {
                textureRegion = imageResolver.getImage(getRelativeFileHandle(fileHandle, childByName.getAttribute("source")).path());
                parseFloat2 -= textureRegion.getRegionHeight();
            }
            TiledMapImageLayer tiledMapImageLayer = new TiledMapImageLayer(textureRegion, parseFloat, parseFloat2);
            loadBasicLayerInfo(tiledMapImageLayer, element);
            XmlReader.Element childByName2 = element.getChildByName("properties");
            if (childByName2 != null) {
                loadProperties(tiledMapImageLayer.getProperties(), childByName2);
            }
            mapLayers.add(tiledMapImageLayer);
        }
    }

    protected void loadBasicLayerInfo(MapLayer mapLayer, XmlReader.Element element) {
        String attribute = element.getAttribute(Attribute.NAME_ATTR, null);
        float parseFloat = Float.parseFloat(element.getAttribute("opacity", "1.0"));
        boolean z = element.getIntAttribute("visible", 1) == 1;
        float floatAttribute = element.getFloatAttribute("offsetx", 0.0f);
        float floatAttribute2 = element.getFloatAttribute("offsety", 0.0f);
        float floatAttribute3 = element.getFloatAttribute("parallaxx", 1.0f);
        float floatAttribute4 = element.getFloatAttribute("parallaxy", 1.0f);
        mapLayer.setName(attribute);
        mapLayer.setOpacity(parseFloat);
        mapLayer.setVisible(z);
        mapLayer.setOffsetX(floatAttribute);
        mapLayer.setOffsetY(floatAttribute2);
        mapLayer.setParallaxX(floatAttribute3);
        mapLayer.setParallaxY(floatAttribute4);
    }

    protected void loadObject(TiledMap tiledMap, MapLayer mapLayer, XmlReader.Element element) {
        loadObject(tiledMap, mapLayer.getObjects(), element, this.mapHeightInPixels);
    }

    protected void loadObject(TiledMap tiledMap, TiledMapTile tiledMapTile, XmlReader.Element element) {
        loadObject(tiledMap, tiledMapTile.getObjects(), element, tiledMapTile.getTextureRegion().getRegionHeight());
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x038c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void loadObject(com.badlogic.gdx.maps.tiled.TiledMap r8, com.badlogic.gdx.maps.MapObjects r9, com.badlogic.gdx.utils.XmlReader.Element r10, float r11) {
        /*
            Method dump skipped, instructions count: 954
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.loadObject(com.badlogic.gdx.maps.tiled.TiledMap, com.badlogic.gdx.maps.MapObjects, com.badlogic.gdx.utils.XmlReader$Element, float):void");
    }

    protected void loadProperties(final MapProperties mapProperties, XmlReader.Element element) {
        if (element != null && element.getName().equals("properties")) {
            Array.ArrayIterator<XmlReader.Element> it = element.getChildrenByName("property").iterator();
            while (it.hasNext()) {
                XmlReader.Element next = it.next();
                final String attribute = next.getAttribute(Attribute.NAME_ATTR, null);
                String attribute2 = next.getAttribute("value", null);
                String attribute3 = next.getAttribute("type", null);
                if (attribute2 == null) {
                    attribute2 = next.getText();
                }
                if (attribute3 != null && attribute3.equals("object")) {
                    try {
                        final int parseInt = Integer.parseInt(attribute2);
                        this.runOnEndOfLoadTiled.add(new Runnable() { // from class: com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.1
                            @Override // java.lang.Runnable
                            public void run() {
                                mapProperties.put(attribute, BaseTmxMapLoader.this.idToObject.get(parseInt));
                            }
                        });
                    } catch (Exception e) {
                        throw new GdxRuntimeException("Error parsing property [\" + name + \"] of type \"object\" with value: [" + attribute2 + "]", e);
                    }
                } else {
                    mapProperties.put(attribute, castProperty(attribute, attribute2, attribute3));
                }
            }
        }
    }

    protected Object castProperty(String str, String str2, String str3) {
        if (str3 == null) {
            return str2;
        }
        if (str3.equals("int")) {
            return Integer.valueOf(str2);
        }
        if (str3.equals("float")) {
            return Float.valueOf(str2);
        }
        if (str3.equals("bool")) {
            return Boolean.valueOf(str2);
        }
        if (str3.equals("color")) {
            return Color.valueOf(str2.substring(3) + str2.substring(1, 3));
        }
        throw new GdxRuntimeException("Wrong type given for property " + str + ", given : " + str3 + ", supported : string, bool, int, float, color");
    }

    protected TiledMapTileLayer.Cell createTileLayerCell(boolean z, boolean z2, boolean z3) {
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        if (z3) {
            if (z && z2) {
                cell.setFlipHorizontally(true);
                cell.setRotation(3);
            } else if (z) {
                cell.setRotation(3);
            } else if (z2) {
                cell.setRotation(1);
            } else {
                cell.setFlipVertically(true);
                cell.setRotation(3);
            }
        } else {
            cell.setFlipHorizontally(z);
            cell.setFlipVertically(z2);
        }
        return cell;
    }

    public static int[] getTileIds(XmlReader.Element element, int i, int i2) {
        InputStream bufferedInputStream;
        int read;
        XmlReader.Element childByName = element.getChildByName("data");
        String attribute = childByName.getAttribute("encoding", null);
        if (attribute == null) {
            throw new GdxRuntimeException("Unsupported encoding (XML) for TMX Layer Data");
        }
        int[] iArr = new int[i * i2];
        if (attribute.equals("csv")) {
            String[] split = childByName.getText().split(",");
            for (int i3 = 0; i3 < split.length; i3++) {
                iArr[i3] = (int) Long.parseLong(split[i3].trim());
            }
        } else if (attribute.equals("base64")) {
            try {
                try {
                    String attribute2 = childByName.getAttribute("compression", null);
                    byte[] decode = Base64Coder.decode(childByName.getText());
                    if (attribute2 == null) {
                        bufferedInputStream = new ByteArrayInputStream(decode);
                    } else if (attribute2.equals("gzip")) {
                        bufferedInputStream = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(decode), decode.length));
                    } else if (attribute2.equals("zlib")) {
                        bufferedInputStream = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(decode)));
                    } else {
                        throw new GdxRuntimeException("Unrecognised compression (" + attribute2 + ") for TMX Layer Data");
                    }
                    byte[] bArr = new byte[4];
                    for (int i4 = 0; i4 < i2; i4++) {
                        for (int i5 = 0; i5 < i; i5++) {
                            int read2 = bufferedInputStream.read(bArr);
                            while (read2 < 4 && (read = bufferedInputStream.read(bArr, read2, 4 - read2)) != -1) {
                                read2 += read;
                            }
                            if (read2 != 4) {
                                throw new GdxRuntimeException("Error Reading TMX Layer Data: Premature end of tile data");
                            }
                            iArr[(i4 * i) + i5] = unsignedByteToInt(bArr[0]) | (unsignedByteToInt(bArr[1]) << 8) | (unsignedByteToInt(bArr[2]) << 16) | (unsignedByteToInt(bArr[3]) << 24);
                        }
                    }
                    StreamUtils.closeQuietly(bufferedInputStream);
                } catch (IOException e) {
                    throw new GdxRuntimeException("Error Reading TMX Layer Data - IOException: " + e.getMessage());
                }
            } catch (Throwable th) {
                StreamUtils.closeQuietly(null);
                throw th;
            }
        } else {
            throw new GdxRuntimeException("Unrecognised encoding (" + attribute + ") for TMX Layer Data");
        }
        return iArr;
    }

    protected static int unsignedByteToInt(byte b2) {
        return b2 & 255;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static FileHandle getRelativeFileHandle(FileHandle fileHandle, String str) {
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

    /* JADX WARN: Multi-variable type inference failed */
    protected void loadTileSet(XmlReader.Element element, FileHandle fileHandle, ImageResolver imageResolver) {
        if (element.getName().equals("tileset")) {
            int intAttribute = element.getIntAttribute("firstgid", 1);
            String str = "";
            int i = 0;
            int i2 = 0;
            FileHandle fileHandle2 = null;
            String attribute = element.getAttribute("source", null);
            if (attribute != null) {
                FileHandle relativeFileHandle = getRelativeFileHandle(fileHandle, attribute);
                try {
                    XmlReader.Element parse = this.xml.parse(relativeFileHandle);
                    element = parse;
                    XmlReader.Element childByName = parse.getChildByName("image");
                    if (childByName != null) {
                        str = childByName.getAttribute("source");
                        i = childByName.getIntAttribute("width", 0);
                        i2 = childByName.getIntAttribute("height", 0);
                        fileHandle2 = getRelativeFileHandle(relativeFileHandle, str);
                    }
                } catch (SerializationException unused) {
                    throw new GdxRuntimeException("Error parsing external tileset.");
                }
            } else {
                XmlReader.Element childByName2 = element.getChildByName("image");
                if (childByName2 != null) {
                    str = childByName2.getAttribute("source");
                    i = childByName2.getIntAttribute("width", 0);
                    i2 = childByName2.getIntAttribute("height", 0);
                    fileHandle2 = getRelativeFileHandle(fileHandle, str);
                }
            }
            String str2 = element.get(Attribute.NAME_ATTR, null);
            int intAttribute2 = element.getIntAttribute("tilewidth", 0);
            int intAttribute3 = element.getIntAttribute("tileheight", 0);
            int intAttribute4 = element.getIntAttribute("spacing", 0);
            int intAttribute5 = element.getIntAttribute("margin", 0);
            XmlReader.Element childByName3 = element.getChildByName("tileoffset");
            int i3 = 0;
            int i4 = 0;
            if (childByName3 != null) {
                i3 = childByName3.getIntAttribute("x", 0);
                i4 = childByName3.getIntAttribute("y", 0);
            }
            TiledMapTileSet tiledMapTileSet = new TiledMapTileSet();
            tiledMapTileSet.setName(str2);
            MapProperties properties = tiledMapTileSet.getProperties();
            XmlReader.Element childByName4 = element.getChildByName("properties");
            if (childByName4 != null) {
                loadProperties(properties, childByName4);
            }
            properties.put("firstgid", Integer.valueOf(intAttribute));
            Array<XmlReader.Element> childrenByName = element.getChildrenByName("tile");
            addStaticTiles(fileHandle, imageResolver, tiledMapTileSet, element, childrenByName, str2, intAttribute, intAttribute2, intAttribute3, intAttribute4, intAttribute5, attribute, i3, i4, str, i, i2, fileHandle2);
            Array array = new Array();
            Array.ArrayIterator<XmlReader.Element> it = childrenByName.iterator();
            while (it.hasNext()) {
                XmlReader.Element next = it.next();
                TiledMapTile tile = tiledMapTileSet.getTile(intAttribute + next.getIntAttribute(Attribute.ID_ATTR, 0));
                TiledMapTile tiledMapTile = tile;
                if (tile != null) {
                    AnimatedTiledMapTile createAnimatedTile = createAnimatedTile(tiledMapTileSet, tiledMapTile, next, intAttribute);
                    if (createAnimatedTile != null) {
                        array.add(createAnimatedTile);
                        tiledMapTile = createAnimatedTile;
                    }
                    addTileProperties(tiledMapTile, next);
                    addTileObjectGroup(tiledMapTile, next);
                }
            }
            Array.ArrayIterator it2 = array.iterator();
            while (it2.hasNext()) {
                AnimatedTiledMapTile animatedTiledMapTile = (AnimatedTiledMapTile) it2.next();
                tiledMapTileSet.putTile(animatedTiledMapTile.getId(), animatedTiledMapTile);
            }
            this.map.getTileSets().addTileSet(tiledMapTileSet);
        }
    }

    protected void addTileProperties(TiledMapTile tiledMapTile, XmlReader.Element element) {
        String attribute = element.getAttribute("terrain", null);
        if (attribute != null) {
            tiledMapTile.getProperties().put("terrain", attribute);
        }
        String attribute2 = element.getAttribute("probability", null);
        if (attribute2 != null) {
            tiledMapTile.getProperties().put("probability", attribute2);
        }
        String attribute3 = element.getAttribute("type", null);
        if (attribute3 != null) {
            tiledMapTile.getProperties().put("type", attribute3);
        }
        XmlReader.Element childByName = element.getChildByName("properties");
        if (childByName != null) {
            loadProperties(tiledMapTile.getProperties(), childByName);
        }
    }

    protected void addTileObjectGroup(TiledMapTile tiledMapTile, XmlReader.Element element) {
        XmlReader.Element childByName = element.getChildByName("objectgroup");
        if (childByName != null) {
            Array.ArrayIterator<XmlReader.Element> it = childByName.getChildrenByName("object").iterator();
            while (it.hasNext()) {
                loadObject(this.map, tiledMapTile, it.next());
            }
        }
    }

    protected AnimatedTiledMapTile createAnimatedTile(TiledMapTileSet tiledMapTileSet, TiledMapTile tiledMapTile, XmlReader.Element element, int i) {
        XmlReader.Element childByName = element.getChildByName("animation");
        if (childByName != null) {
            Array array = new Array();
            IntArray intArray = new IntArray();
            Array.ArrayIterator<XmlReader.Element> it = childByName.getChildrenByName("frame").iterator();
            while (it.hasNext()) {
                XmlReader.Element next = it.next();
                array.add((StaticTiledMapTile) tiledMapTileSet.getTile(i + next.getIntAttribute("tileid")));
                intArray.add(next.getIntAttribute("duration"));
            }
            AnimatedTiledMapTile animatedTiledMapTile = new AnimatedTiledMapTile(intArray, (Array<StaticTiledMapTile>) array);
            animatedTiledMapTile.setId(tiledMapTile.getId());
            return animatedTiledMapTile;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addStaticTiledMapTile(TiledMapTileSet tiledMapTileSet, TextureRegion textureRegion, int i, float f, float f2) {
        StaticTiledMapTile staticTiledMapTile = new StaticTiledMapTile(textureRegion);
        staticTiledMapTile.setId(i);
        staticTiledMapTile.setOffsetX(f);
        staticTiledMapTile.setOffsetY(this.flipY ? -f2 : f2);
        tiledMapTileSet.putTile(i, staticTiledMapTile);
    }
}
