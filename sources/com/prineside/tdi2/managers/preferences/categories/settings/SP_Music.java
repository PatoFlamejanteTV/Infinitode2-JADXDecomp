package com.prineside.tdi2.managers.preferences.categories.settings;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.managers.music.CachedMusicManager;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Music.class */
public class SP_Music implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2547a = TLog.forClass(SP_Music.class);
    public IntArray thumbsUpMusicHashes = new IntArray();
    public Array<MusicManager.MusicSource> menuThemeSources = new Array<>(false, 1, MusicManager.MusicSource.class);
    public Array<CachedMusicManager.CacheStatus> cacheStatuses;

    public SP_Music() {
        this.menuThemeSources.add(MusicManager.MusicSource.DEFAULT);
        this.cacheStatuses = new Array<>(false, 1, CachedMusicManager.CacheStatus.class);
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public void load(PrefMap prefMap) {
        this.thumbsUpMusicHashes.clear();
        try {
            String str = prefMap.get("thumbsUpMusicHashes", null);
            if (str != null) {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    this.thumbsUpMusicHashes.add(iterator2.next().asInt());
                }
            }
        } catch (Exception e) {
            f2547a.e("failed to load thumbsUpMusicHashes", e);
        }
        this.menuThemeSources.clear();
        String str2 = prefMap.get("menuThemeSources", null);
        if (str2 != null) {
            try {
                Iterator<JsonValue> iterator22 = new JsonReader().parse(str2).iterator2();
                while (iterator22.hasNext()) {
                    this.menuThemeSources.add(MusicManager.MusicSource.fromJson(iterator22.next()));
                }
            } catch (Exception e2) {
                f2547a.e("failed to load menuThemeSources from preferences", e2);
            }
        }
        if (this.menuThemeSources.size == 0) {
            this.menuThemeSources.add(MusicManager.MusicSource.DEFAULT);
        }
        this.cacheStatuses.clear();
        String str3 = prefMap.get("cachedMusicStatus", null);
        if (str3 != null) {
            Iterator<JsonValue> iterator23 = new JsonReader().parse(str3).iterator2();
            while (iterator23.hasNext()) {
                try {
                    this.cacheStatuses.add(CachedMusicManager.CacheStatus.fromJson(iterator23.next()));
                } catch (Exception unused) {
                }
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public synchronized void save(PrefMap prefMap) {
        if (this.thumbsUpMusicHashes.size != 0) {
            Json json = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeArrayStart();
            for (int i = 0; i < this.thumbsUpMusicHashes.size; i++) {
                json.writeValue(Integer.valueOf(this.thumbsUpMusicHashes.items[i]));
            }
            json.writeArrayEnd();
            prefMap.set("thumbsUpMusicHashes", stringWriter.toString());
        }
        Json json2 = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter2 = new StringWriter();
        json2.setWriter(stringWriter2);
        json2.writeArrayStart();
        for (int i2 = 0; i2 < this.menuThemeSources.size; i2++) {
            MusicManager.MusicSource musicSource = this.menuThemeSources.items[i2];
            json2.writeObjectStart();
            musicSource.toJson(json2);
            json2.writeObjectEnd();
        }
        json2.writeArrayEnd();
        prefMap.set("menuThemeSources", stringWriter2.toString());
        if (this.cacheStatuses.size != 0) {
            Json json3 = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter3 = new StringWriter();
            json3.setWriter(stringWriter3);
            json3.writeArrayStart();
            for (int i3 = 0; i3 < this.cacheStatuses.size; i3++) {
                json3.writeObjectStart();
                this.cacheStatuses.items[i3].toJson(json3);
                json3.writeObjectEnd();
            }
            json3.writeArrayEnd();
            prefMap.set("cachedMusicStatus", stringWriter3.toString());
        }
    }
}
