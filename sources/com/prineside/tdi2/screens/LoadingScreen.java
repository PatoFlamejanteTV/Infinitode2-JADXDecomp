package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.GameSyncLoader;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LoadingScreen.class */
public class LoadingScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2782a = TLog.forClass(LoadingScreen.class);

    /* renamed from: b, reason: collision with root package name */
    private final GameSyncLoader f2783b;
    private final Texture c;
    private final Texture d;
    private final Texture e;
    private final Texture f;
    private final ScreenViewport g;
    private final Stage h;
    private Image i;
    private Label j;
    private final Table k;
    private final Image l;
    private final Group m;
    private final Image n;
    private final Image o;
    private float p;
    private boolean q;
    private float r;
    private boolean s;

    static /* synthetic */ boolean a(LoadingScreen loadingScreen, boolean z) {
        loadingScreen.q = true;
        return true;
    }

    public LoadingScreen(final GameSyncLoader gameSyncLoader) {
        this.f2783b = gameSyncLoader;
        gameSyncLoader.addListener(new GameSyncLoader.SyncExecutionListener() { // from class: com.prineside.tdi2.screens.LoadingScreen.1
            @Override // com.prineside.tdi2.utils.GameSyncLoader.SyncExecutionListener
            public void startedTask(GameSyncLoader.Task task, GameSyncLoader.Task task2) {
                LoadingScreen.this.i.setWidth(384.0f * gameSyncLoader.getProgress());
                LoadingScreen.this.j.setText(task.title);
            }

            @Override // com.prineside.tdi2.utils.GameSyncLoader.SyncExecutionListener
            public void done() {
                LoadingScreen.a(LoadingScreen.this, true);
            }
        });
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        this.f = new Texture(pixmap);
        pixmap.dispose();
        this.d = new Texture(Gdx.files.internal("res/loading-brand.png"), Pixmap.Format.RGBA8888, false);
        Texture texture = this.d;
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
        texture.setFilter(textureFilter, textureFilter);
        this.e = new Texture(Gdx.files.internal("res/loading-brand-name.png"), Pixmap.Format.RGBA8888, false);
        Texture texture2 = this.e;
        Texture.TextureFilter textureFilter2 = Texture.TextureFilter.Linear;
        texture2.setFilter(textureFilter2, textureFilter2);
        Color color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        this.c = new Texture(Gdx.files.internal("res/loading-logo.png"), Pixmap.Format.RGBA8888, false);
        Texture texture3 = this.c;
        Texture.TextureFilter textureFilter3 = Texture.TextureFilter.Linear;
        texture3.setFilter(textureFilter3, textureFilter3);
        this.g = new ScreenViewport();
        this.h = new Stage(this.g, Game.i.renderingManager.batch);
        this.k = new Table();
        this.k.setFillParent(true);
        this.h.addActor(this.k);
        this.k.add((Table) new Image(this.c)).size(256.0f).padTop(220.0f).row();
        Group group = new Group();
        this.k.add((Table) group).size(384.0f, 16.0f).padTop(48.0f).row();
        this.j = new Label("", new Label.LabelStyle(Game.i.defaultSmallFuturaFont, MaterialColor.GREY.P600));
        this.j.setAlignment(8);
        this.k.add((Table) this.j).width(384.0f).padTop(8.0f).padBottom(120.0f).row();
        this.k.add((Table) new Image(this.d)).size(128.0f).padBottom(32.0f).row();
        Label label = new Label("Infinitode 2\nv.R.1.9.2 (b 208)\n" + ((Runtime.getRuntime().maxMemory() / 1024) / 1024) + "Mb / " + (Config.getMaxTextureSize() / 1024) + "k / " + (Game.i.actionResolver.isAppModified() ? "M" : "C") + (Config.isModdingMode() ? "(" + Config.getModId() + ")" : "") + " / " + Game.i.actionResolver.getShortDeviceInfo(), new Label.LabelStyle(Game.i.defaultSmallFuturaFont, new Color(0.28f, 0.28f, 0.28f, 1.0f)));
        label.setAlignment(1);
        this.k.add((Table) label).colspan(2).width(384.0f).row();
        Image image = new Image(this.f);
        image.setColor(Color.BLACK);
        image.setSize(384.0f, 8.0f);
        group.addActor(image);
        this.i = new Image(this.f);
        this.i.setColor(MaterialColor.CYAN.P400.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f));
        this.i.setSize(0.0f, 8.0f);
        group.addActor(this.i);
        this.l = new Image(this.f);
        this.l.setColor(color);
        this.l.setSize(this.g.getWorldWidth(), this.g.getWorldHeight());
        this.h.addActor(this.l);
        this.m = new Group();
        this.m.setSize(290.0f, 356.0f);
        this.m.setOrigin(1);
        this.h.addActor(this.m);
        this.n = new Image(this.d);
        this.n.setSize(290.0f, 290.0f);
        this.n.setOrigin(1);
        this.n.setPosition(0.0f, 66.0f);
        this.m.addActor(this.n);
        this.o = new Image(this.e);
        this.o.setSize(254.0f, 56.0f);
        this.o.setOrigin(1);
        this.o.setPosition(18.0f, 0.0f);
        this.m.addActor(this.o);
        new Image(this.d).setSize(290.0f, 290.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01e4  */
    @Override // com.prineside.tdi2.Screen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(float r8) {
        /*
            Method dump skipped, instructions count: 586
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.screens.LoadingScreen.draw(float):void");
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void resize(int i, int i2) {
        super.resize(i, i2);
        if (i > 0 && i2 > 0) {
            f2782a.i("resize " + i2 + SequenceUtils.SPACE + Gdx.graphics.getBackBufferHeight(), new Object[0]);
            int backBufferWidth = Gdx.graphics.getBackBufferWidth();
            int backBufferHeight = Gdx.graphics.getBackBufferHeight();
            this.g.setUnitsPerPixel(1080.0f / Gdx.graphics.getHeight());
            this.g.update(backBufferWidth, backBufferHeight, true);
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.c.dispose();
        this.d.dispose();
        this.e.dispose();
        this.h.dispose();
        this.f.dispose();
        f2782a.i("disposed", new Object[0]);
    }
}
