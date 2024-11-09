package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Skin.class */
public class Skin implements Disposable {
    TextureAtlas atlas;
    private static final Class[] defaultTagClasses = {BitmapFont.class, Color.class, TintedDrawable.class, NinePatchDrawable.class, SpriteDrawable.class, TextureRegionDrawable.class, TiledDrawable.class, Button.ButtonStyle.class, CheckBox.CheckBoxStyle.class, ImageButton.ImageButtonStyle.class, ImageTextButton.ImageTextButtonStyle.class, Label.LabelStyle.class, List.ListStyle.class, ProgressBar.ProgressBarStyle.class, ScrollPane.ScrollPaneStyle.class, SelectBox.SelectBoxStyle.class, Slider.SliderStyle.class, SplitPane.SplitPaneStyle.class, TextButton.TextButtonStyle.class, TextField.TextFieldStyle.class, TextTooltip.TextTooltipStyle.class, Touchpad.TouchpadStyle.class, Tree.TreeStyle.class, Window.WindowStyle.class};
    ObjectMap<Class, ObjectMap<String, Object>> resources = new ObjectMap<>();
    float scale = 1.0f;
    private final ObjectMap<String, Class> jsonClassTags = new ObjectMap<>(defaultTagClasses.length);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Skin$TintedDrawable.class */
    public static class TintedDrawable {
        public String name;
        public Color color;
    }

    public Skin() {
        for (Class cls : defaultTagClasses) {
            this.jsonClassTags.put(cls.getSimpleName(), cls);
        }
    }

    public Skin(FileHandle fileHandle) {
        for (Class cls : defaultTagClasses) {
            this.jsonClassTags.put(cls.getSimpleName(), cls);
        }
        FileHandle sibling = fileHandle.sibling(fileHandle.nameWithoutExtension() + ".atlas");
        if (sibling.exists()) {
            this.atlas = new TextureAtlas(sibling);
            addRegions(this.atlas);
        }
        load(fileHandle);
    }

    public Skin(FileHandle fileHandle, TextureAtlas textureAtlas) {
        for (Class cls : defaultTagClasses) {
            this.jsonClassTags.put(cls.getSimpleName(), cls);
        }
        this.atlas = textureAtlas;
        addRegions(textureAtlas);
        load(fileHandle);
    }

    public Skin(TextureAtlas textureAtlas) {
        for (Class cls : defaultTagClasses) {
            this.jsonClassTags.put(cls.getSimpleName(), cls);
        }
        this.atlas = textureAtlas;
        addRegions(textureAtlas);
    }

    public void load(FileHandle fileHandle) {
        try {
            getJsonLoader(fileHandle).fromJson(Skin.class, fileHandle);
        } catch (SerializationException e) {
            throw new SerializationException("Error reading file: " + fileHandle, e);
        }
    }

    public void addRegions(TextureAtlas textureAtlas) {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        int i = regions.size;
        for (int i2 = 0; i2 < i; i2++) {
            TextureAtlas.AtlasRegion atlasRegion = regions.get(i2);
            String str = atlasRegion.name;
            if (atlasRegion.index != -1) {
                str = str + JavaConstant.Dynamic.DEFAULT_NAME + atlasRegion.index;
            }
            add(str, atlasRegion, TextureRegion.class);
        }
    }

    public void add(String str, Object obj) {
        add(str, obj, obj.getClass());
    }

    public void add(String str, Object obj, Class cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (obj == null) {
            throw new IllegalArgumentException("resource cannot be null.");
        }
        ObjectMap<String, Object> objectMap = this.resources.get(cls);
        ObjectMap<String, Object> objectMap2 = objectMap;
        if (objectMap == null) {
            objectMap2 = new ObjectMap<>((cls == TextureRegion.class || cls == Drawable.class || cls == Sprite.class) ? 256 : 64);
            this.resources.put(cls, objectMap2);
        }
        objectMap2.put(str, obj);
    }

    public void remove(String str, Class cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.resources.get(cls).remove(str);
    }

    public <T> T get(Class<T> cls) {
        return (T) get("default", cls);
    }

    public <T> T get(String str, Class<T> cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (cls == Drawable.class) {
            return (T) getDrawable(str);
        }
        if (cls == TextureRegion.class) {
            return (T) getRegion(str);
        }
        if (cls == NinePatch.class) {
            return (T) getPatch(str);
        }
        if (cls == Sprite.class) {
            return (T) getSprite(str);
        }
        ObjectMap<String, Object> objectMap = this.resources.get(cls);
        if (objectMap == null) {
            throw new GdxRuntimeException("No " + cls.getName() + " registered with name: " + str);
        }
        T t = (T) objectMap.get(str);
        if (t == null) {
            throw new GdxRuntimeException("No " + cls.getName() + " registered with name: " + str);
        }
        return t;
    }

    @Null
    public <T> T optional(String str, Class<T> cls) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        ObjectMap<String, Object> objectMap = this.resources.get(cls);
        if (objectMap == null) {
            return null;
        }
        return (T) objectMap.get(str);
    }

    public boolean has(String str, Class cls) {
        ObjectMap<String, Object> objectMap = this.resources.get(cls);
        if (objectMap == null) {
            return false;
        }
        return objectMap.containsKey(str);
    }

    @Null
    public <T> ObjectMap<String, T> getAll(Class<T> cls) {
        return (ObjectMap) this.resources.get(cls);
    }

    public Color getColor(String str) {
        return (Color) get(str, Color.class);
    }

    public BitmapFont getFont(String str) {
        return (BitmapFont) get(str, BitmapFont.class);
    }

    public TextureRegion getRegion(String str) {
        TextureRegion textureRegion = (TextureRegion) optional(str, TextureRegion.class);
        if (textureRegion != null) {
            return textureRegion;
        }
        Texture texture = (Texture) optional(str, Texture.class);
        if (texture == null) {
            throw new GdxRuntimeException("No TextureRegion or Texture registered with name: " + str);
        }
        TextureRegion textureRegion2 = new TextureRegion(texture);
        add(str, textureRegion2, TextureRegion.class);
        return textureRegion2;
    }

    @Null
    public Array<TextureRegion> getRegions(String str) {
        Array<TextureRegion> array = null;
        int i = 0 + 1;
        TextureRegion textureRegion = (TextureRegion) optional(str + JavaConstant.Dynamic.DEFAULT_NAME + 0, TextureRegion.class);
        TextureRegion textureRegion2 = textureRegion;
        if (textureRegion != null) {
            array = new Array<>();
            while (textureRegion2 != null) {
                array.add(textureRegion2);
                int i2 = i;
                i++;
                textureRegion2 = (TextureRegion) optional(str + JavaConstant.Dynamic.DEFAULT_NAME + i2, TextureRegion.class);
            }
        }
        return array;
    }

    public TiledDrawable getTiledDrawable(String str) {
        TiledDrawable tiledDrawable = (TiledDrawable) optional(str, TiledDrawable.class);
        if (tiledDrawable != null) {
            return tiledDrawable;
        }
        TiledDrawable tiledDrawable2 = new TiledDrawable(getRegion(str));
        tiledDrawable2.setName(str);
        if (this.scale != 1.0f) {
            scale(tiledDrawable2);
            tiledDrawable2.setScale(this.scale);
        }
        add(str, tiledDrawable2, TiledDrawable.class);
        return tiledDrawable2;
    }

    public NinePatch getPatch(String str) {
        int[] findValue;
        NinePatch ninePatch = (NinePatch) optional(str, NinePatch.class);
        NinePatch ninePatch2 = ninePatch;
        if (ninePatch != null) {
            return ninePatch2;
        }
        try {
            TextureRegion region = getRegion(str);
            if ((region instanceof TextureAtlas.AtlasRegion) && (findValue = ((TextureAtlas.AtlasRegion) region).findValue("split")) != null) {
                ninePatch2 = new NinePatch(region, findValue[0], findValue[1], findValue[2], findValue[3]);
                if (((TextureAtlas.AtlasRegion) region).findValue("pad") != null) {
                    ninePatch2.setPadding(r0[0], r0[1], r0[2], r0[3]);
                }
            }
            if (ninePatch2 == null) {
                ninePatch2 = new NinePatch(region);
            }
            if (this.scale != 1.0f) {
                ninePatch2.scale(this.scale, this.scale);
            }
            add(str, ninePatch2, NinePatch.class);
            return ninePatch2;
        } catch (GdxRuntimeException unused) {
            throw new GdxRuntimeException("No NinePatch, TextureRegion, or Texture registered with name: " + str);
        }
    }

    public Sprite getSprite(String str) {
        Sprite sprite = (Sprite) optional(str, Sprite.class);
        Sprite sprite2 = sprite;
        if (sprite != null) {
            return sprite2;
        }
        try {
            TextureRegion region = getRegion(str);
            if (region instanceof TextureAtlas.AtlasRegion) {
                TextureAtlas.AtlasRegion atlasRegion = (TextureAtlas.AtlasRegion) region;
                if (atlasRegion.rotate || atlasRegion.packedWidth != atlasRegion.originalWidth || atlasRegion.packedHeight != atlasRegion.originalHeight) {
                    sprite2 = new TextureAtlas.AtlasSprite(atlasRegion);
                }
            }
            if (sprite2 == null) {
                sprite2 = new Sprite(region);
            }
            if (this.scale != 1.0f) {
                Sprite sprite3 = sprite2;
                sprite3.setSize(sprite3.getWidth() * this.scale, sprite2.getHeight() * this.scale);
            }
            add(str, sprite2, Sprite.class);
            return sprite2;
        } catch (GdxRuntimeException unused) {
            throw new GdxRuntimeException("No NinePatch, TextureRegion, or Texture registered with name: " + str);
        }
    }

    public Drawable getDrawable(String str) {
        Drawable drawable = (Drawable) optional(str, Drawable.class);
        Drawable drawable2 = drawable;
        if (drawable != null) {
            return drawable2;
        }
        try {
            TextureRegion region = getRegion(str);
            if (region instanceof TextureAtlas.AtlasRegion) {
                TextureAtlas.AtlasRegion atlasRegion = (TextureAtlas.AtlasRegion) region;
                if (atlasRegion.findValue("split") != null) {
                    drawable2 = new NinePatchDrawable(getPatch(str));
                } else if (atlasRegion.rotate || atlasRegion.packedWidth != atlasRegion.originalWidth || atlasRegion.packedHeight != atlasRegion.originalHeight) {
                    drawable2 = new SpriteDrawable(getSprite(str));
                }
            }
            if (drawable2 == null) {
                drawable2 = new TextureRegionDrawable(region);
                if (this.scale != 1.0f) {
                    scale(drawable2);
                }
            }
        } catch (GdxRuntimeException unused) {
        }
        if (drawable2 == null) {
            NinePatch ninePatch = (NinePatch) optional(str, NinePatch.class);
            if (ninePatch != null) {
                drawable2 = new NinePatchDrawable(ninePatch);
            } else {
                Sprite sprite = (Sprite) optional(str, Sprite.class);
                if (sprite != null) {
                    drawable2 = new SpriteDrawable(sprite);
                } else {
                    throw new GdxRuntimeException("No Drawable, NinePatch, TextureRegion, Texture, or Sprite registered with name: " + str);
                }
            }
        }
        if (drawable2 instanceof BaseDrawable) {
            ((BaseDrawable) drawable2).setName(str);
        }
        add(str, drawable2, Drawable.class);
        return drawable2;
    }

    @Null
    public String find(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        ObjectMap<String, Object> objectMap = this.resources.get(obj.getClass());
        if (objectMap == null) {
            return null;
        }
        return objectMap.findKey(obj, true);
    }

    public Drawable newDrawable(String str) {
        return newDrawable(getDrawable(str));
    }

    public Drawable newDrawable(String str, float f, float f2, float f3, float f4) {
        return newDrawable(getDrawable(str), new Color(f, f2, f3, f4));
    }

    public Drawable newDrawable(String str, Color color) {
        return newDrawable(getDrawable(str), color);
    }

    public Drawable newDrawable(Drawable drawable) {
        if (drawable instanceof TiledDrawable) {
            return new TiledDrawable((TiledDrawable) drawable);
        }
        if (drawable instanceof TextureRegionDrawable) {
            return new TextureRegionDrawable((TextureRegionDrawable) drawable);
        }
        if (drawable instanceof NinePatchDrawable) {
            return new NinePatchDrawable((NinePatchDrawable) drawable);
        }
        if (drawable instanceof SpriteDrawable) {
            return new SpriteDrawable((SpriteDrawable) drawable);
        }
        throw new GdxRuntimeException("Unable to copy, unknown drawable type: " + drawable.getClass());
    }

    public Drawable newDrawable(Drawable drawable, float f, float f2, float f3, float f4) {
        return newDrawable(drawable, new Color(f, f2, f3, f4));
    }

    public Drawable newDrawable(Drawable drawable, Color color) {
        Drawable tint;
        if (drawable instanceof TextureRegionDrawable) {
            tint = ((TextureRegionDrawable) drawable).tint(color);
        } else if (drawable instanceof NinePatchDrawable) {
            tint = ((NinePatchDrawable) drawable).tint(color);
        } else if (drawable instanceof SpriteDrawable) {
            tint = ((SpriteDrawable) drawable).tint(color);
        } else {
            throw new GdxRuntimeException("Unable to copy, unknown drawable type: " + drawable.getClass());
        }
        if (tint instanceof BaseDrawable) {
            SpriteDrawable spriteDrawable = (BaseDrawable) tint;
            if (drawable instanceof BaseDrawable) {
                spriteDrawable.setName(((BaseDrawable) drawable).getName() + " (" + color + ")");
            } else {
                spriteDrawable.setName(" (" + color + ")");
            }
        }
        return tint;
    }

    public void scale(Drawable drawable) {
        drawable.setLeftWidth(drawable.getLeftWidth() * this.scale);
        drawable.setRightWidth(drawable.getRightWidth() * this.scale);
        drawable.setBottomHeight(drawable.getBottomHeight() * this.scale);
        drawable.setTopHeight(drawable.getTopHeight() * this.scale);
        drawable.setMinWidth(drawable.getMinWidth() * this.scale);
        drawable.setMinHeight(drawable.getMinHeight() * this.scale);
    }

    public void setScale(float f) {
        this.scale = f;
    }

    public void setEnabled(Actor actor, boolean z) {
        Method findMethod = findMethod(actor.getClass(), "getStyle");
        if (findMethod == null) {
            return;
        }
        try {
            Object invoke = findMethod.invoke(actor, new Object[0]);
            String find = find(invoke);
            if (find == null) {
                return;
            }
            Object obj = get(find.replace("-disabled", "") + (z ? "" : "-disabled"), invoke.getClass());
            Method findMethod2 = findMethod(actor.getClass(), "setStyle");
            if (findMethod2 == null) {
                return;
            }
            try {
                findMethod2.invoke(actor, obj);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
        }
    }

    @Null
    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.atlas != null) {
            this.atlas.dispose();
        }
        ObjectMap.Values<ObjectMap<String, Object>> it = this.resources.values().iterator();
        while (it.hasNext()) {
            ObjectMap.Values<Object> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                Object next = it2.next();
                if (next instanceof Disposable) {
                    ((Disposable) next).dispose();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected Json getJsonLoader(final FileHandle fileHandle) {
        Json json = new Json() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.1
            private static final String parentFieldName = "parent";

            @Override // com.badlogic.gdx.utils.Json
            public <T> T readValue(Class<T> cls, Class cls2, JsonValue jsonValue) {
                if (jsonValue != null && jsonValue.isString() && !ClassReflection.isAssignableFrom(CharSequence.class, cls)) {
                    return (T) Skin.this.get(jsonValue.asString(), cls);
                }
                return (T) super.readValue(cls, cls2, jsonValue);
            }

            @Override // com.badlogic.gdx.utils.Json
            protected boolean ignoreUnknownField(Class cls, String str) {
                return str.equals(parentFieldName);
            }

            @Override // com.badlogic.gdx.utils.Json
            public void readFields(Object obj, JsonValue jsonValue) {
                Class<? super Object> superclass;
                if (jsonValue.has(parentFieldName)) {
                    String str = (String) readValue(parentFieldName, String.class, jsonValue);
                    Class<?> cls = obj.getClass();
                    do {
                        try {
                            copyFields(Skin.this.get(str, cls), obj);
                        } catch (GdxRuntimeException unused) {
                            superclass = cls.getSuperclass();
                            cls = superclass;
                        }
                    } while (superclass != Object.class);
                    SerializationException serializationException = new SerializationException("Unable to find parent resource with name: " + str);
                    serializationException.addTrace(jsonValue.child.trace());
                    throw serializationException;
                }
                super.readFields(obj, jsonValue);
            }
        };
        json.setTypeName(null);
        json.setUsePrototypes(false);
        json.setSerializer(Skin.class, new Json.ReadOnlySerializer<Skin>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.2
            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.Serializer
            public Skin read(Json json2, JsonValue jsonValue, Class cls) {
                JsonValue jsonValue2 = jsonValue.child;
                while (true) {
                    JsonValue jsonValue3 = jsonValue2;
                    if (jsonValue3 != null) {
                        try {
                            Class cls2 = json2.getClass(jsonValue3.name());
                            Class cls3 = cls2;
                            if (cls2 == null) {
                                cls3 = ClassReflection.forName(jsonValue3.name());
                            }
                            readNamedObjects(json2, cls3, jsonValue3);
                            jsonValue2 = jsonValue3.next;
                        } catch (ReflectionException e) {
                            throw new SerializationException(e);
                        }
                    } else {
                        return this;
                    }
                }
            }

            private void readNamedObjects(Json json2, Class cls, JsonValue jsonValue) {
                Class cls2 = cls == TintedDrawable.class ? Drawable.class : cls;
                JsonValue jsonValue2 = jsonValue.child;
                while (true) {
                    JsonValue jsonValue3 = jsonValue2;
                    if (jsonValue3 != null) {
                        Object readValue = json2.readValue(cls, jsonValue3);
                        if (readValue != null) {
                            try {
                                Skin.this.add(jsonValue3.name, readValue, cls2);
                                if (cls2 != Drawable.class && ClassReflection.isAssignableFrom(Drawable.class, cls2)) {
                                    Skin.this.add(jsonValue3.name, readValue, Drawable.class);
                                }
                            } catch (Exception e) {
                                throw new SerializationException("Error reading " + ClassReflection.getSimpleName(cls) + ": " + jsonValue3.name, e);
                            }
                        }
                        jsonValue2 = jsonValue3.next;
                    } else {
                        return;
                    }
                }
            }
        });
        json.setSerializer(BitmapFont.class, new Json.ReadOnlySerializer<BitmapFont>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.3
            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.Serializer
            public BitmapFont read(Json json2, JsonValue jsonValue, Class cls) {
                BitmapFont bitmapFont;
                String str = (String) json2.readValue("file", String.class, jsonValue);
                float floatValue = ((Float) json2.readValue("scaledSize", (Class<Class>) Float.TYPE, (Class) Float.valueOf(-1.0f), jsonValue)).floatValue();
                Boolean bool = (Boolean) json2.readValue("flip", (Class<Class>) Boolean.class, (Class) Boolean.FALSE, jsonValue);
                Boolean bool2 = (Boolean) json2.readValue("markupEnabled", (Class<Class>) Boolean.class, (Class) Boolean.FALSE, jsonValue);
                Boolean bool3 = (Boolean) json2.readValue("useIntegerPositions", (Class<Class>) Boolean.class, (Class) Boolean.TRUE, jsonValue);
                FileHandle child = fileHandle.parent().child(str);
                FileHandle fileHandle2 = child;
                if (!child.exists()) {
                    fileHandle2 = Gdx.files.internal(str);
                }
                if (!fileHandle2.exists()) {
                    throw new SerializationException("Font file not found: " + fileHandle2);
                }
                String nameWithoutExtension = fileHandle2.nameWithoutExtension();
                try {
                    Array<TextureRegion> regions = this.getRegions(nameWithoutExtension);
                    if (regions != null) {
                        bitmapFont = new BitmapFont(new BitmapFont.BitmapFontData(fileHandle2, bool.booleanValue()), regions, true);
                    } else {
                        TextureRegion textureRegion = (TextureRegion) this.optional(nameWithoutExtension, TextureRegion.class);
                        if (textureRegion != null) {
                            bitmapFont = new BitmapFont(fileHandle2, textureRegion, bool.booleanValue());
                        } else {
                            FileHandle child2 = fileHandle2.parent().child(nameWithoutExtension + ".png");
                            if (child2.exists()) {
                                bitmapFont = new BitmapFont(fileHandle2, child2, bool.booleanValue());
                            } else {
                                bitmapFont = new BitmapFont(fileHandle2, bool.booleanValue());
                            }
                        }
                    }
                    bitmapFont.getData().markupEnabled = bool2.booleanValue();
                    bitmapFont.setUseIntegerPositions(bool3.booleanValue());
                    if (floatValue != -1.0f) {
                        bitmapFont.getData().setScale(floatValue / bitmapFont.getCapHeight());
                    }
                    return bitmapFont;
                } catch (RuntimeException e) {
                    throw new SerializationException("Error loading bitmap font: " + fileHandle2, e);
                }
            }
        });
        json.setSerializer(Color.class, new Json.ReadOnlySerializer<Color>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.4
            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.Serializer
            public Color read(Json json2, JsonValue jsonValue, Class cls) {
                if (jsonValue.isString()) {
                    return (Color) Skin.this.get(jsonValue.asString(), Color.class);
                }
                String str = (String) json2.readValue("hex", (Class<Class>) String.class, (Class) null, jsonValue);
                if (str != null) {
                    return Color.valueOf(str);
                }
                return new Color(((Float) json2.readValue("r", (Class<Class>) Float.TYPE, (Class) Float.valueOf(0.0f), jsonValue)).floatValue(), ((Float) json2.readValue("g", (Class<Class>) Float.TYPE, (Class) Float.valueOf(0.0f), jsonValue)).floatValue(), ((Float) json2.readValue(FlexmarkHtmlConverter.B_NODE, (Class<Class>) Float.TYPE, (Class) Float.valueOf(0.0f), jsonValue)).floatValue(), ((Float) json2.readValue(FlexmarkHtmlConverter.A_NODE, (Class<Class>) Float.TYPE, (Class) Float.valueOf(1.0f), jsonValue)).floatValue());
            }
        });
        json.setSerializer(TintedDrawable.class, new Json.ReadOnlySerializer() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Skin.5
            @Override // com.badlogic.gdx.utils.Json.ReadOnlySerializer, com.badlogic.gdx.utils.Json.Serializer
            public Object read(Json json2, JsonValue jsonValue, Class cls) {
                String str = (String) json2.readValue(Attribute.NAME_ATTR, String.class, jsonValue);
                Color color = (Color) json2.readValue("color", Color.class, jsonValue);
                if (color == null) {
                    throw new SerializationException("TintedDrawable missing color: " + jsonValue);
                }
                Drawable newDrawable = Skin.this.newDrawable(str, color);
                if (newDrawable instanceof BaseDrawable) {
                    ((BaseDrawable) newDrawable).setName(jsonValue.name + " (" + str + ", " + color + ")");
                }
                return newDrawable;
            }
        });
        ObjectMap.Entries<String, Class> it = this.jsonClassTags.iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            json.addClassTag((String) next.key, (Class) next.value);
        }
        return json;
    }

    public ObjectMap<String, Class> getJsonClassTags() {
        return this.jsonClassTags;
    }

    @Null
    private static Method findMethod(Class cls, String str) {
        for (Method method : ClassReflection.getMethods(cls)) {
            if (method.getName().equals(str)) {
                return method;
            }
        }
        return null;
    }
}
