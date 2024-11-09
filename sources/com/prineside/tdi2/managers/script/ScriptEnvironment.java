package com.prineside.tdi2.managers.script;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.Globals;
import com.prineside.luaj.LoadState;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.compiler.LuaC;
import com.prineside.luaj.lib.Bit32Lib;
import com.prineside.luaj.lib.DebugLib;
import com.prineside.luaj.lib.PackageLib;
import com.prineside.luaj.lib.StringLib;
import com.prineside.luaj.lib.TableLib;
import com.prineside.luaj.lib.jse.JavaClass;
import com.prineside.luaj.lib.jse.JavaInstance;
import com.prineside.luaj.lib.jse.JseBaseLib;
import com.prineside.luaj.lib.jse.JseMathLib;
import com.prineside.luaj.lib.jse.JseOsLib;
import com.prineside.luaj.lib.jse.LuajavaLib;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.script.autocompletion.LuaScriptParser;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.lang.reflect.Field;
import java.util.Map;
import net.bytebuddy.utility.JavaConstant;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/ScriptEnvironment.class */
public class ScriptEnvironment implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2556a = TLog.forClass(ScriptEnvironment.class);

    /* renamed from: b, reason: collision with root package name */
    private Globals f2557b;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/ScriptEnvironment$LuaExecutionResult.class */
    public static class LuaExecutionResult {
        public LuaValue returnValue;
        public Throwable caughtError;
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f2557b);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f2557b = (Globals) kryo.readObject(input, Globals.class);
    }

    private void a() {
        if (this.f2557b == null) {
            throw new IllegalStateException("Script environment not initialized yet");
        }
    }

    public void initialize() {
        if (this.f2557b == null) {
            this.f2557b = new Globals();
            this.f2557b.load(new JseBaseLib());
            this.f2557b.load(new PackageLib());
            this.f2557b.load(new JseOsLib());
            this.f2557b.load(new Bit32Lib());
            this.f2557b.load(new TableLib());
            this.f2557b.load(new StringLib());
            this.f2557b.load(new JseMathLib());
            this.f2557b.load(new LuajavaLib());
            this.f2557b.load(new DebugLib());
            LoadState.install(this.f2557b);
            LuaC luaC = new LuaC();
            this.f2557b.compiler = luaC;
            this.f2557b.loader = luaC;
            Game.i.scriptManager.fillGlobalsWithClassDefinitions(this.f2557b);
            loadScriptsInDir("scripts");
        }
    }

    @Null
    private Object a(LuaValue luaValue, Array<String> array) {
        if (array.size == 0) {
            return luaValue;
        }
        LuaValue luaValue2 = luaValue.get(array.peek());
        if (luaValue2 != null && !luaValue2.isnil()) {
            Array<String> array2 = new Array<>(array);
            array2.pop();
            return a(luaValue2, array2);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Null
    public Suggestion getAutocompletion(String str, int i) {
        try {
            String[] aCStringAt = new LuaScriptParser(str).getACStringAt(i);
            if (aCStringAt != null && aCStringAt.length != 0) {
                Array<String> array = new Array<>(aCStringAt);
                array.pop();
                array.reverse();
                Object a2 = a(this.f2557b, array);
                if (a2 == null) {
                    return null;
                }
                Suggestion suggestion = new Suggestion();
                suggestion.start = 0;
                String str2 = aCStringAt[aCStringAt.length - 1];
                for (int length = i - str2.length(); length >= 0; length--) {
                    char charAt = str.charAt(length);
                    if (charAt == ' ' || charAt == ':' || charAt == '.' || charAt == '(' || charAt == '=') {
                        suggestion.start = length + 1;
                        break;
                    }
                }
                if (a2 instanceof LuaTable) {
                    LuaValue luaValue = LuaValue.NIL;
                    LuaTable luaTable = (LuaTable) a2;
                    while (true) {
                        Varargs next = luaTable.next(luaValue);
                        LuaValue arg1 = next.arg1();
                        luaValue = arg1;
                        if (arg1.isnil()) {
                            break;
                        }
                        LuaValue arg = next.arg(2);
                        if (luaValue.isstring()) {
                            String str3 = luaValue.tojstring();
                            if (str2.length() == 0 || str3.startsWith(str2)) {
                                suggestion.variants.add(new ObjectPair<>(str3, arg.typename()));
                            }
                        }
                    }
                } else if (a2 instanceof JavaClass) {
                    JavaClass javaClass = (JavaClass) a2;
                    ObjectMap.Entries<LuaString, Field> it = javaClass.getClassFields().iterator();
                    while (it.hasNext()) {
                        ObjectMap.Entry next2 = it.next();
                        if (((LuaString) next2.key).tojstring().startsWith(str2)) {
                            suggestion.variants.add(new ObjectPair<>(((LuaString) next2.key).tojstring(), ((Field) next2.value).getType().getSimpleName()));
                        }
                    }
                    for (Map.Entry<LuaString, LuaValue> entry : javaClass.getClassMethods().entrySet()) {
                        if (entry.getKey().tojstring().startsWith(str2)) {
                            suggestion.variants.add(new ObjectPair<>(entry.getKey().tojstring(), "method"));
                        }
                    }
                } else {
                    JavaClass javaClass2 = ((JavaInstance) a2).getJavaClass();
                    ObjectMap.Entries<LuaString, Field> it2 = javaClass2.getInstanceFields().iterator();
                    while (it2.hasNext()) {
                        ObjectMap.Entry next3 = it2.next();
                        if (((LuaString) next3.key).tojstring().startsWith(str2)) {
                            suggestion.variants.add(new ObjectPair<>(((LuaString) next3.key).tojstring(), ((Field) next3.value).getType().getSimpleName()));
                        }
                    }
                    for (Map.Entry<LuaString, LuaValue> entry2 : javaClass2.getInstanceMethods().entrySet()) {
                        if (entry2.getKey().tojstring().startsWith(str2)) {
                            suggestion.variants.add(new ObjectPair<>(entry2.getKey().tojstring(), "method"));
                        }
                    }
                }
                suggestion.variants.sort((objectPair, objectPair2) -> {
                    return ((String) objectPair.first).compareTo((String) objectPair2.first);
                });
                return suggestion;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void loadScriptsInDir(String str) {
        Array array = new Array(true, 1, FileHandle.class);
        if (Gdx.files.local(str).exists()) {
            for (FileHandle fileHandle : Gdx.files.local(str).list()) {
                if (!fileHandle.isDirectory()) {
                    array.add(fileHandle);
                }
            }
        }
        for (FileHandle fileHandle2 : Gdx.files.internal(str).list()) {
            if (!fileHandle2.isDirectory()) {
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= array.size) {
                        break;
                    }
                    if (!((FileHandle[]) array.items)[i].path().equals(fileHandle2.path())) {
                        i++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    array.add(fileHandle2);
                }
            }
        }
        for (int i2 = 0; i2 < array.size; i2++) {
            for (int i3 = i2 + 1; i3 < array.size; i3++) {
                if (((FileHandle[]) array.items)[i2].name().compareTo(((FileHandle[]) array.items)[i3].name()) > 0) {
                    array.swap(i2, i3);
                }
            }
        }
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            FileHandle fileHandle3 = (FileHandle) it.next();
            if (!fileHandle3.name().startsWith(JavaConstant.Dynamic.DEFAULT_NAME)) {
                try {
                    loadScript(fileHandle3);
                } catch (Exception e) {
                    f2556a.e("Failed to load script: " + fileHandle3.name(), e);
                }
            }
        }
    }

    public Globals getGlobals() {
        a();
        return this.f2557b;
    }

    public LuaExecutionResult loadScript(FileHandle fileHandle) {
        return executeLua(fileHandle.readString("UTF-8"), fileHandle.type() == Files.FileType.Classpath ? "[CP] " + fileHandle.path() : fileHandle.path());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.prineside.tdi2.managers.script.ScriptEnvironment$LuaExecutionResult] */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.prineside.tdi2.managers.script.ScriptEnvironment$LuaExecutionResult] */
    public LuaExecutionResult executeLua(String str, String str2) {
        a();
        LuaError luaExecutionResult = new LuaExecutionResult();
        try {
            luaExecutionResult = luaExecutionResult;
            luaExecutionResult.returnValue = this.f2557b.load(str, str2).call();
        } catch (LuaError e) {
            handleLuaError(luaExecutionResult);
            luaExecutionResult.caughtError = e;
        } catch (Exception e2) {
            f2556a.e("executeLua() failed for string:\n" + str, e2);
            luaExecutionResult.caughtError = e2;
        }
        return luaExecutionResult;
    }

    public static void handleLuaError(LuaError luaError) {
        String fileline = luaError.getFileline();
        if (fileline != null) {
            if (fileline.startsWith("ScriptTile[")) {
                f2556a.e("error in script tile: %s", fileline);
                try {
                    if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
                        Game.i.screenManager.getCurrentScreen();
                    }
                } catch (Exception unused) {
                }
            } else if (fileline.startsWith("@")) {
                f2556a.e("error in file: %s", fileline);
            }
        }
        if (Game.i.settingsManager != null && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_PRINT_FULL_LUA_STACKTRACES) == 0.0d) {
            f2556a.e("Lua error at %s: %s", luaError.getFileline(), luaError.getMessage());
        } else {
            f2556a.e("Lua error at %s", luaError.getFileline(), luaError);
        }
    }

    public void dispose() {
        this.f2557b = null;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/ScriptEnvironment$Suggestion.class */
    public static final class Suggestion {
        public int start;
        public Array<ObjectPair<String, String>> variants = new Array<>();

        public final String toString() {
            return "@" + this.start + ":" + this.variants.toString("|");
        }
    }
}
