package com.prineside.tdi2.managers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.luaj.Globals;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.script.ClassTreeLuaTable;
import com.prineside.tdi2.managers.script.GlobalCLuaTable;
import com.prineside.tdi2.managers.script.MathEnvironment;
import com.prineside.tdi2.managers.script.ScriptEnvironment;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ScriptManager.class */
public class ScriptManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2448a = TLog.forClass(ScriptManager.class);
    public static final String WHITELIST_FILE_PATH = "res/luaj/whitelist.txt";
    public static final String CLASS_ALIASES_FILE_PATH = "res/luaj/lua-class-aliases.txt";
    public ScriptEnvironment global;
    public MathEnvironment math;

    /* renamed from: b, reason: collision with root package name */
    private Whitelist f2449b;
    private volatile LuaTable c;
    private GlobalCLuaTable d;

    static {
        new StringBuilder();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ScriptManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ScriptManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ScriptManager read() {
            return Game.i.scriptManager;
        }
    }

    public ScriptManager() {
        Threads.i().runAsync(this::a);
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        this.global = new ScriptEnvironment();
        this.global.initialize();
        this.global.loadScriptsInDir("scripts/global");
        f2448a.i("global environment init", new Object[0]);
        this.math = new MathEnvironment();
    }

    public static Array<String> getPackagesToScanFromConfig() {
        Scanner scanner = new Scanner(new File("res/luaj/packages-to-scan.txt"));
        Array<String> array = new Array<>(true, 1, String.class);
        while (scanner.hasNextLine()) {
            String trim = scanner.nextLine().trim();
            if (trim.length() != 0) {
                array.add(trim);
            }
        }
        scanner.close();
        return array;
    }

    public void restoreClassTreePath(String str, ClassTreeLuaTable classTreeLuaTable) {
        try {
            String[] split = str.split("\\.");
            LuaTable luaPackageDefinitions = getLuaPackageDefinitions();
            for (String str2 : split) {
                luaPackageDefinitions = (LuaTable) luaPackageDefinitions.get(str2);
            }
            classTreeLuaTable.loadFrom(luaPackageDefinitions);
        } catch (Exception e) {
            f2448a.e("failed to restore package table " + str, e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x007d, code lost:            throw new java.lang.IllegalArgumentException("Invalid definition at line " + r10 + " in res/luaj/forced-class-aliases.txt");     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void a() {
        /*
            Method dump skipped, instructions count: 965
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.ScriptManager.a():void");
    }

    public LuaTable getLuaPackageDefinitions() {
        a();
        return this.c;
    }

    public GlobalCLuaTable getGlobalCTable() {
        a();
        return this.d;
    }

    public void fillGlobalsWithClassDefinitions(Globals globals) {
        LuaTable luaPackageDefinitions = getLuaPackageDefinitions();
        for (LuaValue luaValue : luaPackageDefinitions.keys()) {
            globals.set(luaValue, new ClassTreeLuaTable(luaPackageDefinitions.get(luaValue)));
        }
        globals.set(LuaString.valueOf("C"), getGlobalCTable());
    }

    public Whitelist getWhitelist() {
        if (this.f2449b == null) {
            f2448a.i("loading LuaJ whitelist from file...", new Object[0]);
            long realTickCount = Game.getRealTickCount();
            try {
                this.f2449b = Whitelist.fromFile(AssetManager.localOrInternalFile(WHITELIST_FILE_PATH));
                f2448a.i("whitelist loaded in " + (((float) (Game.getRealTickCount() - realTickCount)) / 1000.0f) + "ms", new Object[0]);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to read from LuaJ whitelist", e);
            }
        }
        return this.f2449b;
    }
}
