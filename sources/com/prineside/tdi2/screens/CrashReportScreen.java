package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.managers.RenderingManager;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/CrashReportScreen.class */
public class CrashReportScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2745a = TLog.forClass(CrashReportScreen.class);
    private final BitmapFont c;
    private final Stage e;
    private final ScreenViewport f;
    private final Table g;
    private final Label.LabelStyle h;
    private final Label.LabelStyle i;
    private final SpriteBatch d = new SpriteBatch(8191, RenderingManager.createDefaultSpriteBatchShader());

    /* renamed from: b, reason: collision with root package name */
    private final BitmapFont f2746b = new BitmapFont(Gdx.files.internal("resourcepacks/default/futura.fnt"));

    public CrashReportScreen(String str, String str2, String str3, String str4) {
        this.f2746b.getData().setScale(this.f2746b.getData().scaleX * 0.5f);
        Texture texture = this.f2746b.getRegion().getTexture();
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
        texture.setFilter(textureFilter, textureFilter);
        this.c = new BitmapFont(Gdx.files.internal("resourcepacks/default/futura.fnt"));
        this.c.getData().setScale(this.c.getData().scaleX * 0.8f);
        Texture texture2 = this.c.getRegion().getTexture();
        Texture.TextureFilter textureFilter2 = Texture.TextureFilter.Linear;
        texture2.setFilter(textureFilter2, textureFilter2);
        this.f = new ScreenViewport();
        this.e = new Stage(this.f, this.d);
        String str5 = str + SequenceUtils.SPACE + str3 + SequenceUtils.SPACE + str4;
        String str6 = null;
        if (str5.contains("OutOfMemory")) {
            str6 = "Not enough memory (RAM) - try to disable some graphical effects / sell some tiles from your inventory";
        } else if (str5.contains("ENOSPC") || str5.contains("Test")) {
            str6 = "Not enough of free disk space - make sure your disk drive is not completely full and the game is allowed to create new files";
        }
        this.g = new Table();
        this.e.addActor(this.g);
        this.h = new Label.LabelStyle(this.f2746b, Color.WHITE);
        this.i = new Label.LabelStyle(this.c, Color.WHITE);
        a("Crash detected", MaterialColor.AMBER.P500, true, true);
        if (str6 != null) {
            a("Possible reason:", MaterialColor.CYAN.P500);
            a(str6, Color.WHITE, true, true);
        }
        a("Error:", MaterialColor.CYAN.P500);
        a(str + ": " + str3, Color.WHITE, true);
        a("Thread name:", MaterialColor.CYAN.P500);
        a(str2, Color.WHITE, true);
        a("Stacktrace:", MaterialColor.CYAN.P500);
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = str4.replaceAll("\t", "    ").split(SequenceUtils.EOL);
        int i = 0;
        while (true) {
            if (i >= split.length) {
                break;
            }
            stringBuilder.append(split[i]).append(SequenceUtils.EOL);
            if (i != 16) {
                i++;
            } else {
                stringBuilder.append("...");
                break;
            }
        }
        this.g.add((Table) new Label(stringBuilder.toString(), this.h)).minWidth(100.0f).padBottom(20.0f).top().left().row();
        this.g.add().height(40.0f).width(1.0f).row();
        a("Tap the screen or press any key to continue", MaterialColor.LIGHT_GREEN.P500, true, true).addAction(Actions.forever(Actions.sequence(Actions.color(MaterialColor.LIGHT_GREEN.P900, 0.5f), Actions.color(MaterialColor.LIGHT_GREEN.P500, 0.3f))));
    }

    private Label a(String str, Color color) {
        return a(str, color, false, false);
    }

    private Label a(String str, Color color, boolean z) {
        return a(str, color, true, false);
    }

    private Label a(String str, Color color, boolean z, boolean z2) {
        Label label = new Label(str, z2 ? this.i : this.h);
        label.setColor(color);
        label.setWrap(true);
        this.g.add((Table) label).growX().padBottom(z ? 20.0f : 0.0f).top().left().row();
        return label;
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void resize(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.f.setUnitsPerPixel(960.0f / i2);
            this.f.update(i, i2, true);
            this.g.setSize((960.0f * (i / i2)) - 160.0f, 880.0f);
            this.g.setPosition(80.0f, 40.0f);
        }
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(16640);
        this.e.act(f);
        this.e.draw();
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(-1)) {
            try {
                Gdx.files.local("cache/crash-report.json").delete();
            } catch (Exception unused) {
                f2745a.e("failed to delete crash report", new Object[0]);
            }
            Game.i.screenManager.goToLoadingScreen(Game.i.gameSyncLoader);
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.e.dispose();
        this.d.dispose();
        this.f2746b.dispose();
        this.c.dispose();
    }
}
