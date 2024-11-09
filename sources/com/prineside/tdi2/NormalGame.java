package com.prineside.tdi2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/NormalGame.class */
public class NormalGame extends Game {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1746a = TLog.forClass(NormalGame.class);

    public NormalGame(ActionResolver actionResolver, @Null Runnable runnable) {
        super(actionResolver, runnable);
    }

    @Override // com.prineside.tdi2.Game, com.badlogic.gdx.ApplicationListener
    public void create() {
        super.create();
        FileHandle local = Gdx.files.local("cache/crash-report.json");
        if (local.exists()) {
            try {
                String readString = local.readString("UTF-8");
                JsonValue parse = new JsonReader().parse(readString);
                if (parse != null) {
                    this.screenManager.goToCrashReportScreen(parse.getString("type", ""), parse.getString("thread", ""), parse.getString("message", ""), parse.getString("stacktrace", ""));
                    return;
                }
                throw new IllegalArgumentException("Invalid crash report file: " + readString);
            } catch (Exception e) {
                f1746a.e("failed to handle crash report file", e);
                this.screenManager.goToLoadingScreen(this.gameSyncLoader);
                return;
            }
        }
        FileHandle local2 = Gdx.files.local("cache/space.bin");
        if (!local2.exists()) {
            byte[] bArr = new byte[1024];
            Arrays.fill(bArr, (byte) -1);
            for (int i = 0; i < 32; i++) {
                local2.writeBytes(bArr, true);
            }
        }
        f1746a.i("set up sync loader, going to loading screen", new Object[0]);
        this.screenManager.goToLoadingScreen(this.gameSyncLoader);
    }
}
