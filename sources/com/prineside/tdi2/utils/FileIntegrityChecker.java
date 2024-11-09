package com.prineside.tdi2.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ScriptManager;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FileIntegrityChecker.class */
public class FileIntegrityChecker {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3826a = TLog.forClass(FileIntegrityChecker.class);

    /* renamed from: b, reason: collision with root package name */
    private static Array<String> f3827b = null;

    public static Array<String> runTheTest() {
        if (f3827b == null) {
            Game.i.assertInMainThread();
            f3827b = new Array<>();
            try {
                ObjectSet objectSet = new ObjectSet();
                int i = 0;
                for (String str : AssetManager.localOrInternalFile("res/file-hashes.txt").readString("UTF-8").split(SequenceUtils.EOL)) {
                    String trim = str.trim();
                    i++;
                    if (trim.length() != 0) {
                        String[] split = trim.split("\\|");
                        if (split.length == 2) {
                            String str2 = split[0];
                            String str3 = split[1];
                            objectSet.add(str2);
                            FileHandle localOrInternalFile = AssetManager.localOrInternalFile(str2);
                            if (!localOrInternalFile.exists()) {
                                f3827b.add("not exists: " + str2);
                            } else if (localOrInternalFile.isDirectory()) {
                                f3827b.add("is directory: " + str2);
                            } else if (!a(localOrInternalFile).equals(str3)) {
                                f3827b.add("hash mismatch: " + str2);
                            }
                        } else {
                            f3826a.w("failed to read line %s in %s: %s, incorrect format", Integer.valueOf(i), "res/file-hashes.txt", trim);
                        }
                    }
                }
                FileHandle local = Gdx.files.local("scripts");
                if (local.exists() && local.isDirectory()) {
                    FileHandle[] list = Gdx.files.local("scripts/").list();
                    Array array = new Array();
                    for (FileHandle fileHandle : list) {
                        if (!fileHandle.name().equals(".definitions")) {
                            a(fileHandle, (Array<String>) array);
                        }
                    }
                    for (int i2 = 0; i2 < array.size; i2++) {
                        String str4 = (String) array.get(i2);
                        if (!objectSet.contains(str4)) {
                            f3827b.add("custom script: " + str4);
                        }
                    }
                } else {
                    f3826a.d("skipped scripts check - dir not exists", new Object[0]);
                }
            } catch (Throwable th) {
                f3826a.e("failed to run file integrity check", th);
                f3827b.add("failed to run the test: " + th.getMessage());
            }
        }
        return f3827b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void generateHashListFile() {
        Array array = new Array();
        String[] strArr = {"0.1", "0.2", "0.3", "0.4", "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.b1", "2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "2.8", "2.b1", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8", "3.b1", "4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "4.7", "4.8", "4.b1", "5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "5.7", "5.8", "5.b1", "5.b2", "6.1", "6.2", "6.3", "6.4", "6.5", "6.6", "dev", "rumble", "zecred"};
        for (int i = 0; i < 59; i++) {
            String str = strArr[i];
            array.add("levels/" + str + ".json");
            array.add("levels/maps/" + str + ".json");
        }
        array.add("res/luaj/class-list.txt");
        array.add("res/luaj/forced-class-aliases.txt");
        array.add("res/luaj/interfaces-priority.txt");
        array.add(ScriptManager.WHITELIST_FILE_PATH);
        array.add("res/achievements.json");
        array.add("res/basic-level-stages.json");
        array.add("res/core-tiles.json");
        array.add("res/daily-loot.json");
        array.add("res/game-values.json");
        array.add("res/kryo-registry.txt");
        array.add("res/researches.json");
        array.add("res/tower-enemy-attack-matrix.json");
        array.add("res/tower-enemy-damage-matrix.json");
        array.add("res/tower-stats.json");
        array.add("res/trophies.json");
        array.add("resourcepacks/default/textures/combined.atlas");
        array.add("resourcepacks/default/textures/combined.png");
        array.add("resourcepacks/default/debug.fnt");
        array.add("resourcepacks/default/font.fnt");
        array.add("resourcepacks/default/pack.json");
        array.add("resourcepacks/default/quads.json5");
        for (FileHandle fileHandle : Gdx.files.local("scripts/").list()) {
            if (!fileHandle.name().equals(".definitions")) {
                a(fileHandle, (Array<String>) array);
            }
        }
        Threads.sortGdxArray(array, (str2, str3) -> {
            return str2.compareTo(str3);
        });
        StringBuilder stringBuilder = new StringBuilder();
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            String str4 = (String) it.next();
            stringBuilder.append(str4).append("|").append(a(Gdx.files.local(str4))).append(SequenceUtils.EOL);
        }
        Gdx.files.local("res/file-hashes.txt").writeString(stringBuilder.toString(), false, "UTF-8");
        f3826a.i("Generated file hash list, checking integrity...", new Object[0]);
        Array<String> runTheTest = runTheTest();
        if (runTheTest.size == 0) {
            f3826a.i("file integrity test succeeded", new Object[0]);
            return;
        }
        f3826a.w("file integrity test failed:", new Object[0]);
        Array.ArrayIterator<String> it2 = runTheTest.iterator();
        while (it2.hasNext()) {
            f3826a.w("- " + it2.next(), new Object[0]);
        }
    }

    private static String a(FileHandle fileHandle) {
        return StringFormatter.bytesMd5Hash(fileHandle.readBytes());
    }

    private static void a(FileHandle fileHandle, Array<String> array) {
        if (fileHandle.isDirectory()) {
            for (FileHandle fileHandle2 : fileHandle.list()) {
                a(fileHandle2, array);
            }
            return;
        }
        array.add(fileHandle.path());
    }
}
