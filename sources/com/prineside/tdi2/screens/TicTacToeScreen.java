package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.LongArray;
import com.badlogic.gdx.utils.ShortArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.Widget;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.utils.BitVector;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    */
/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen.class */
public class TicTacToeScreen extends Screen {

    /* renamed from: a */
    private static final TLog f2843a = TLog.forClass(TicTacToeScreen.class);
    public static boolean LOG_AI_ACTIONS = true;
    public static boolean PRINT_AI_ACTIONS = true;

    /* renamed from: b */
    private static final Color f2844b = MaterialColor.LIGHT_GREEN.P500;
    private static final Color c = MaterialColor.LIGHT_BLUE.P500;
    private final Ai[] d = new Ai[20];
    private final Rules[] e = new Rules[20];
    private boolean f;
    private Ai g;
    private double h;
    private int i;
    private int j;
    private byte k;
    private final UiManager.UiLayer l;
    private final UiManager.UiLayer m;
    private Rules n;
    private byte o;
    private static GameReplay p;
    private final Array<GameReplay> q;
    private Board r;
    private Board s;
    private final Array<Label> t;
    private final Array<EndGameResult> u;
    private final int[] v;
    private final Table w;
    private final Label x;
    private final Label y;
    private final Label z;
    private final Table A;
    private final Image B;
    private final Label C;
    private final Label D;
    private Image[] E;
    private Image[] F;
    private static float[] G;
    private final Table H;
    private final Drawable I;
    private final Drawable J;
    private Ai K;
    private long L;
    private long M;
    private long N;
    private final Thread O;
    private Ai P;
    private ObjectConsumer<AiRunResult> Q;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Ai.class */
    public interface Ai {
        short makeMove(Board board);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$AiRunResult.class */
    public static class AiRunResult {

        /* renamed from: a */
        short f2849a;

        /* renamed from: b */
        String f2850b;
        long c;
    }

    /*  JADX ERROR: Failed to decode insn: 0x0007: MOVE_MULTI, method: com.prineside.tdi2.screens.TicTacToeScreen.a(com.prineside.tdi2.screens.TicTacToeScreen, long):long
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
        	at jadx.core.ProcessClass.process(ProcessClass.java:70)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
        */
    static /* synthetic */ long a(com.prineside.tdi2.screens.TicTacToeScreen r6, long r7) {
        /*
            r0 = r6
            r1 = r0
            long r1 = r1.N
            r2 = r7
            long r1 = r1 + r2
            // decode failed: arraycopy: source index -1 out of bounds for object array[6]
            r0.N = r1
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.screens.TicTacToeScreen.a(com.prineside.tdi2.screens.TicTacToeScreen, long):long");
    }

    static {
    }

    public TicTacToeScreen() {
        for (int i = 0; i < 20; i++) {
            float f = i / 19.0f;
            if (f <= 0.3f) {
                float f2 = f / 0.3f;
                this.d[i] = new AiMixin(new Level3Ai((byte) 2, (byte) 0), 1.0f - f2, new Level4Ai((byte) 2), f2);
            } else if (f <= 0.6f) {
                float f3 = (f - 0.3f) / 0.3f;
                this.d[i] = new AiMixin(new Level4Ai((byte) 2), 1.0f - f3, new Level5Ai((byte) 2), f3);
            } else {
                this.d[i] = new Level5Ai((byte) 2);
            }
            this.d[i] = new Level5Ai((byte) 2);
            switch (i) {
                case 0:
                case 1:
                case 2:
                    this.e[i] = new Rules((short) 3, (short) 3);
                    break;
                case 3:
                case 4:
                    this.e[i] = new Rules((short) 5, (short) 4);
                    break;
                case 5:
                case 6:
                case 7:
                    this.e[i] = new Rules((short) 5, (short) 4);
                    break;
                case 8:
                case 9:
                case 10:
                    this.e[i] = new Rules((short) 6, (short) 4);
                    break;
                case 11:
                case 12:
                    this.e[i] = new Rules((short) 7, (short) 4);
                    break;
                case 13:
                case 14:
                case 15:
                    this.e[i] = new Rules((short) 8, (short) 5);
                    break;
                case 16:
                case 17:
                case 18:
                case 19:
                    this.e[i] = new Rules((short) 9, (short) 5);
                    break;
            }
            this.e[i] = new Rules((short) 6, (short) 4);
        }
        this.f = false;
        this.g = null;
        this.h = 0.0d;
        this.i = 0;
        this.j = 0;
        this.l = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 99, "TicTacToeScreen main");
        this.m = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "TicTacToeScreen main");
        this.q = new Array<>(true, 1, GameReplay.class);
        this.t = new Array<>(true, 8, Label.class);
        this.u = new Array<>(true, 8, EndGameResult.class);
        this.v = new int[3];
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        BackButton.i().setVisible(true).setText(null).setClickHandler(this::h);
        Level1Ai.f2859a.setSeed(9001L);
        this.O = new Thread(() -> {
            while (true) {
                if (this.P != null) {
                    long realTickCount = Game.getRealTickCount();
                    this.s.copyFieldFrom(this.r);
                    short makeMove = this.P.makeMove(this.s);
                    long realTickCount2 = Game.getRealTickCount() - realTickCount;
                    this.P = null;
                    AiRunResult aiRunResult = new AiRunResult();
                    aiRunResult.c = realTickCount2;
                    aiRunResult.f2849a = makeMove;
                    if (makeMove < 0 || makeMove >= this.r.cellCount) {
                        aiRunResult.f2850b = "incorrect cell index " + ((int) makeMove);
                    } else if (this.r.getCell(makeMove) != 0) {
                        aiRunResult.f2850b = "cell " + ((int) makeMove) + " already occupied by " + (this.r.getCell(makeMove) == 1 ? "cross" : "circle");
                    }
                    this.Q.accept(aiRunResult);
                }
                try {
                    Thread.sleep(2L);
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }, "TicTacToe AI");
        this.O.setDaemon(true);
        this.O.start();
        Table table = new Table();
        Table table2 = new Table();
        table.add(table2).width(600.0f).padLeft(20.0f).padTop(20.0f).row();
        table.add().growY().row();
        table2.add((Table) new Widget() { // from class: com.prineside.tdi2.screens.TicTacToeScreen.1
            AnonymousClass1() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
            public void draw(Batch batch, float f4) {
                super.validate();
                float x = getX();
                float y = getY();
                ResourcePack.AtlasTextureRegion atlasTextureRegion = AssetManager.TextureRegions.i().blank;
                for (int i2 = 0; i2 < TicTacToeScreen.this.u.size; i2++) {
                    EndGameResult endGameResult = ((EndGameResult[]) TicTacToeScreen.this.u.items)[i2];
                    if (endGameResult.f2853a == 1) {
                        batch.setColor(TicTacToeScreen.f2844b);
                    } else if (endGameResult.f2853a == 2) {
                        batch.setColor(TicTacToeScreen.c);
                    } else {
                        batch.setColor(0.28f, 0.28f, 0.28f, 1.0f);
                    }
                    batch.draw(atlasTextureRegion, x + (6.0f * i2), y, 5.0f, ((float) (endGameResult.f2854b / 20.0d)) * 150.0f);
                }
                batch.setColor(Color.WHITE);
            }
        }).size(600.0f, 150.0f).row();
        Table table3 = new Table();
        table2.add(table3).width(600.0f).padTop(20.0f);
        this.x = new Label("0", Game.i.assetManager.getLabelStyle(30));
        this.x.setAlignment(1);
        this.x.setColor(f2844b);
        table3.add((Table) this.x).width(200.0f);
        this.z = new Label("0", Game.i.assetManager.getLabelStyle(30));
        this.z.setAlignment(1);
        this.z.setColor(new Color(0.56f, 0.56f, 0.56f, 1.0f));
        table3.add((Table) this.z).width(200.0f);
        this.y = new Label("0", Game.i.assetManager.getLabelStyle(30));
        this.y.setAlignment(1);
        this.y.setColor(c);
        table3.add((Table) this.y).width(200.0f);
        table3.row();
        Label label = new Label("Player", Game.i.assetManager.getLabelStyle(21));
        label.setColor(new Color(1.0f, 1.0f, 1.0f, 0.28f));
        table3.add((Table) label);
        Label label2 = new Label("Draws", Game.i.assetManager.getLabelStyle(21));
        label2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.28f));
        table3.add((Table) label2);
        Label label3 = new Label("Ensor", Game.i.assetManager.getLabelStyle(21));
        label3.setColor(new Color(1.0f, 1.0f, 1.0f, 0.28f));
        table3.add((Table) label3);
        this.A = new Table();
        table.add(this.A).width(600.0f);
        this.l.getTable().add(table).padLeft(20.0f).padBottom(180.0f).width(600.0f).growY();
        this.l.getTable().add().grow();
        this.I = Game.i.assetManager.getDrawable("icon-minus");
        this.J = Game.i.assetManager.getDrawable("icon-plus");
        this.C = new Label("", Game.i.assetManager.getDebugLabelStyle());
        this.C.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.C.setAlignment(1);
        this.m.getTable().add((Table) this.C).padBottom(15.0f).row();
        this.D = new Label("", Game.i.assetManager.getDebugLabelStyle());
        this.m.getTable().add((Table) this.D).padBottom(15.0f).row();
        this.B = new Image(Game.i.assetManager.getDrawable("icon-sand-clock"));
        this.B.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
        Group group = new Group();
        group.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
        this.B.setSize(32.0f, 32.0f);
        this.B.setOrigin(16.0f, 16.0f);
        group.setOrigin(16.0f, 16.0f);
        group.addActor(this.B);
        this.m.getTable().add((Table) group).padBottom(15.0f).size(32.0f).row();
        this.w = new Table();
        this.m.getTable().add(this.w).row();
        this.H = new Table();
        this.m.getTable().add(this.H).padTop(15.0f).row();
        this.H.add((Table) new RectButton("New game", Game.i.assetManager.getDebugLabelStyle(), () -> {
            if (e()) {
                c(0);
            }
        })).width(128.0f).height(48.0f);
        this.H.setVisible(false);
        c(0);
        g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.prineside.tdi2.screens.TicTacToeScreen$1 */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$1.class */
    public class AnonymousClass1 extends Widget {
        AnonymousClass1() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
        public void draw(Batch batch, float f4) {
            super.validate();
            float x = getX();
            float y = getY();
            ResourcePack.AtlasTextureRegion atlasTextureRegion = AssetManager.TextureRegions.i().blank;
            for (int i2 = 0; i2 < TicTacToeScreen.this.u.size; i2++) {
                EndGameResult endGameResult = ((EndGameResult[]) TicTacToeScreen.this.u.items)[i2];
                if (endGameResult.f2853a == 1) {
                    batch.setColor(TicTacToeScreen.f2844b);
                } else if (endGameResult.f2853a == 2) {
                    batch.setColor(TicTacToeScreen.c);
                } else {
                    batch.setColor(0.28f, 0.28f, 0.28f, 1.0f);
                }
                batch.draw(atlasTextureRegion, x + (6.0f * i2), y, 5.0f, ((float) (endGameResult.f2854b / 20.0d)) * 150.0f);
            }
            batch.setColor(Color.WHITE);
        }
    }

    public static void b(int i) {
        if (i < 0 || i >= G.length) {
            return;
        }
        G[i] = 1.0f;
    }

    private boolean e() {
        return this.h < 20.0d;
    }

    private void f() {
        if (this.n.fieldSize <= 8) {
            this.r = new FixedSizeSmallBoard(this.n);
            this.s = new FixedSizeSmallBoard(this.n);
        } else {
            this.r = new DynamicSizeBoard(this.n);
            this.s = new DynamicSizeBoard(this.n);
        }
        this.E = new Image[this.n.fieldSize * this.n.fieldSize];
        this.F = new Image[this.n.fieldSize * this.n.fieldSize];
        G = new float[this.n.fieldSize * this.n.fieldSize];
        short s = 0;
        while (true) {
            short s2 = s;
            if (s2 >= this.E.length) {
                break;
            }
            this.E[s2] = new Image();
            this.E[s2].setTouchable(Touchable.enabled);
            this.E[s2].addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.TicTacToeScreen.2

                /* renamed from: a */
                private /* synthetic */ short f2845a;

                AnonymousClass2(short s22) {
                    r5 = s22;
                }

                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    if (TicTacToeScreen.this.j == 2 && TicTacToeScreen.this.r.getCell(r5) == 0) {
                        TicTacToeScreen.this.r.setCell(r5, (byte) 1);
                        TicTacToeScreen.p.f2857a.add(new IntPair(r5, 1));
                        TicTacToeScreen.f2843a.i("player made manual move in " + (((float) (Game.getRealTickCount() - TicTacToeScreen.this.L)) * 0.001f) + " ms", new Object[0]);
                        TicTacToeScreen.a(TicTacToeScreen.this, Game.getRealTickCount() - TicTacToeScreen.this.L);
                        TicTacToeScreen.this.c(1);
                    }
                }
            });
            this.F[s22] = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            this.F[s22].setColor(MaterialColor.LIGHT_BLUE.P900);
            s = (short) (s22 + 1);
        }
        TextureRegionDrawable drawable = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME);
        this.w.clear();
        for (int i = 0; i < this.n.fieldSize; i++) {
            for (int i2 = 0; i2 < this.n.fieldSize; i2++) {
                int a2 = a(i2, i);
                Group group = new Group();
                group.setTransform(false);
                this.F[a2].setSize(64.0f, 64.0f);
                this.F[a2].getColor().f889a = 0.0f;
                group.addActor(this.F[a2]);
                Label label = new Label(new StringBuilder().append(a2).toString(), Game.i.assetManager.getDebugLabelStyle());
                label.setColor(1.0f, 1.0f, 1.0f, 0.14f);
                label.setSize(64.0f, 64.0f);
                label.setAlignment(1);
                group.addActor(label);
                this.E[a2].setSize(48.0f, 48.0f);
                this.E[a2].setOrigin(24.0f, 24.0f);
                this.E[a2].setPosition(8.0f, 8.0f);
                group.addActor(this.E[a2]);
                this.w.add((Table) group).size(64.0f);
                if (i2 != this.n.fieldSize - 1) {
                    Image image = new Image(drawable);
                    image.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    this.w.add((Table) image).width(2.0f).height(64.0f);
                }
            }
            this.w.row();
            if (i != this.n.fieldSize - 1) {
                for (int i3 = 0; i3 < this.n.fieldSize; i3++) {
                    Image image2 = new Image(drawable);
                    image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    this.w.add((Table) image2).size(64.0f, 2.0f);
                    if (i3 != this.n.fieldSize - 1) {
                        Image image3 = new Image(drawable);
                        image3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                        this.w.add((Table) image3).width(2.0f).height(2.0f);
                    }
                }
                this.w.row();
            }
        }
    }

    /* renamed from: com.prineside.tdi2.screens.TicTacToeScreen$2 */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$2.class */
    public class AnonymousClass2 extends ClickListener {

        /* renamed from: a */
        private /* synthetic */ short f2845a;

        AnonymousClass2(short s22) {
            r5 = s22;
        }

        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            if (TicTacToeScreen.this.j == 2 && TicTacToeScreen.this.r.getCell(r5) == 0) {
                TicTacToeScreen.this.r.setCell(r5, (byte) 1);
                TicTacToeScreen.p.f2857a.add(new IntPair(r5, 1));
                TicTacToeScreen.f2843a.i("player made manual move in " + (((float) (Game.getRealTickCount() - TicTacToeScreen.this.L)) * 0.001f) + " ms", new Object[0]);
                TicTacToeScreen.a(TicTacToeScreen.this, Game.getRealTickCount() - TicTacToeScreen.this.L);
                TicTacToeScreen.this.c(1);
            }
        }
    }

    public void c(int i) {
        Game.i.assertInMainThread();
        this.j = i;
        switch (this.j) {
            case 0:
                this.i++;
                this.N = 0L;
                this.M = 0L;
                this.n = this.e[(int) this.h];
                p = new GameReplay((byte) 0);
                this.o = this.o == 1 ? (byte) 2 : (byte) 1;
                f();
                this.D.setText("Game starts");
                this.H.setVisible(false);
                for (Image image : this.E) {
                    image.clearActions();
                    image.addAction(Actions.scaleTo(1.0f, 1.0f));
                }
                this.K = this.d[(int) this.h];
                p.a("using ai: " + this.K);
                g();
                a("Round " + this.i + ": " + (this.o == 2 ? "Ensor" : "Player") + " moves first");
                return;
            case 1:
                this.D.setText("Ensor's move");
                k();
                return;
            case 2:
                this.D.setText("Player's move");
                k();
                this.L = Game.getRealTickCount();
                return;
            case 3:
                this.D.setText("Ensor's AI running");
                return;
            case 4:
                this.D.setText("Player's AI running");
                return;
            case 5:
                this.D.setText("Game over, " + (this.k == 0 ? "tie" : this.k == 1 ? "player wins" : "Ensor wins"));
                f2843a.i("player sum time: " + this.N, new Object[0]);
                f2843a.i("ensor sum time: " + this.M, new Object[0]);
                double d = 0.0d;
                if (this.k == 2) {
                    a("Round " + this.i + ": [#FF9800]Ensor[] wins");
                    d = -0.4d;
                } else if (this.k == 1) {
                    a("Round " + this.i + ": [#8BC34A]Player[] wins");
                    d = 1.0d;
                    this.q.add(p);
                    if (this.q.size > 5) {
                        this.q.removeIndex(0);
                    }
                } else if (this.M > this.N) {
                    double d2 = this.M / this.N;
                    if (d2 > 5.0d) {
                        a("Round " + this.i + ": [#8BC34A]Player[] wins by draw, he was x" + ((Object) StringFormatter.compactNumberWithPrecision(d2, 1)) + " faster");
                        d = 0.04d;
                    } else {
                        a("Round " + this.i + ": draw but Player was x" + ((Object) StringFormatter.compactNumberWithPrecision(d2, 1)) + " faster");
                    }
                } else {
                    double d3 = this.N / this.M;
                    if (d3 > 5.0d) {
                        a("Round " + this.i + ": [#FF9800]Ensor[] wins by draw, he was x" + ((Object) StringFormatter.compactNumberWithPrecision(d3, 1)) + " faster");
                        d = -0.01d;
                    } else {
                        a("Round " + this.i + ": draw but Ensor was x" + ((Object) StringFormatter.compactNumberWithPrecision(d3, 1)) + " faster");
                    }
                }
                this.h = MathUtils.clamp(this.h + d, 0.0d, 20.0d);
                a(this.k, this.N, this.M, d, this.h);
                g();
                this.H.setVisible(true);
                return;
            default:
                return;
        }
    }

    private void g() {
        this.C.setText("Round: " + this.i + ", score: " + MathUtils.round(((float) this.h) * 5.0f) + " / " + MathUtils.round(100.0f) + "\nPlace " + ((int) this.n.winCondition) + " marks in a row to win the game");
    }

    private void a(byte b2, long j, long j2, double d, double d2) {
        EndGameResult endGameResult = new EndGameResult((byte) 0);
        endGameResult.f2853a = b2;
        endGameResult.f2854b = d2;
        this.u.add(endGameResult);
        if (this.u.size == 101) {
            this.u.removeIndex(0);
        }
        int[] iArr = this.v;
        iArr[b2] = iArr[b2] + 1;
        this.x.setTextFromInt(this.v[1]);
        this.z.setTextFromInt(this.v[0]);
        this.y.setTextFromInt(this.v[2]);
    }

    private void a(String str) {
        Threads.i().runOnMainThread(() -> {
            Label label = new Label(str, Game.i.assetManager.getDebugLabelStyle());
            label.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            this.t.add(label);
            if (this.t.size == 20) {
                this.t.removeIndex(0);
            }
            this.A.clear();
            this.A.add().growY().row();
            for (int i = 0; i < this.t.size; i++) {
                float clamp = MathUtils.clamp((((i + 1) + (20 - this.t.size)) / 20.0f) + 0.05f, 0.0f, 1.0f);
                this.t.get(i).clearActions();
                this.t.get(i).addAction(Actions.color(new Color(1.0f, 1.0f, 1.0f, clamp * 0.56f), 0.3f));
                this.A.add((Table) this.t.get(i)).growX().row();
            }
        });
    }

    private int a(int i, int i2) {
        return (i2 * this.n.fieldSize) + i;
    }

    private void h() {
        Game.i.screenManager.goToSettingsScreen();
    }

    private void a(Ai ai, ObjectConsumer<AiRunResult> objectConsumer) {
        this.P = ai;
        this.Q = objectConsumer;
    }

    private void i() {
        c(3);
        a(this.K, aiRunResult -> {
            if (aiRunResult.f2850b != null) {
                f2843a.e("Error: " + aiRunResult.f2850b, new Object[0]);
            } else if (this.r.getCell(aiRunResult.f2849a) == 0) {
                this.r.setCell(aiRunResult.f2849a, (byte) 2);
                p.f2857a.add(new IntPair(aiRunResult.f2849a, 2));
            } else {
                f2843a.e("can't make move for Ensor - cell is already occupied", new Object[0]);
            }
            this.M += aiRunResult.c;
            Threads.i().runOnMainThread(() -> {
                c(2);
            });
        });
    }

    private void j() {
        c(4);
        a(this.g, aiRunResult -> {
            if (aiRunResult.f2850b != null) {
                f2843a.e(aiRunResult.f2850b, new Object[0]);
            } else if (this.r.getCell(aiRunResult.f2849a) == 0) {
                this.r.setCell(aiRunResult.f2849a, (byte) 1);
                p.f2857a.add(new IntPair(aiRunResult.f2849a, 1));
            } else {
                f2843a.e("can't make move for Player - cell is already occupied", new Object[0]);
            }
            this.N += aiRunResult.c;
            Threads.i().runOnMainThread(() -> {
                c(1);
            });
        });
    }

    private void k() {
        boolean hasEmptyCells = this.r.hasEmptyCells();
        ShortArray fittingWinnerMask = this.r.getFittingWinnerMask();
        if (fittingWinnerMask != null) {
            this.k = this.r.getCell(fittingWinnerMask.first());
            for (int i = 0; i < fittingWinnerMask.size; i++) {
                short s = fittingWinnerMask.items[i];
                this.E[s].clearActions();
                this.E[s].addAction(Actions.sequence(Actions.scaleTo(1.3f, 1.3f, 0.2f), Actions.scaleTo(1.0f, 1.0f, 1.0f)));
            }
            c(5);
            return;
        }
        if (!hasEmptyCells) {
            this.k = (byte) 0;
            c(5);
        }
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            h();
        }
        short s = 0;
        while (true) {
            short s2 = s;
            if (s2 >= this.r.cellCount) {
                break;
            }
            byte cell = this.r.getCell(s2);
            if (cell == 1) {
                this.E[s2].setDrawable(this.J);
                this.E[s2].setColor(f2844b);
            } else if (cell == 2) {
                this.E[s2].setDrawable(this.I);
                this.E[s2].setColor(c);
            } else {
                this.E[s2].setDrawable(null);
            }
            s = (short) (s2 + 1);
        }
        for (int i = 0; i < this.r.cellCount; i++) {
            if (this.j == 2 || this.j == 4) {
                this.F[i].setColor(MaterialColor.LIGHT_GREEN.P900);
            } else {
                this.F[i].setColor(MaterialColor.LIGHT_BLUE.P900);
            }
            this.F[i].getColor().f889a = G[i];
            float[] fArr = G;
            int i2 = i;
            fArr[i2] = fArr[i2] * 0.9f;
        }
        this.B.setVisible(false);
        this.B.clearActions();
        this.B.addAction(Actions.alpha(0.0f, 0.1f));
        switch (this.j) {
            case 0:
                if (this.o == 2) {
                    c(1);
                    return;
                } else {
                    c(2);
                    return;
                }
            case 1:
                i();
                return;
            case 2:
                if (this.g != null) {
                    j();
                    return;
                }
                return;
            case 3:
            case 4:
                this.B.setVisible(true);
                this.B.clearActions();
                this.B.addAction(Actions.alpha(1.0f, 0.1f));
                return;
            case 5:
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.l);
        Game.i.uiManager.removeLayer(this.m);
        if (this.O != null) {
            this.O.interrupt();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Board.class */
    public static abstract class Board {
        public final Rules rules;
        public final short cellCount;

        public abstract Board cpy();

        public abstract boolean hasSameField(Board board);

        public abstract int getTotalTickCount();

        public abstract void copyFieldFrom(Board board);

        public abstract byte getCell(short s);

        public abstract void setCell(short s, byte b2);

        public abstract byte getWinner();

        public abstract ShortArray getFittingWinnerMask();

        public abstract ShortArray getFittingWinnerMaskAsync(long[] jArr);

        public abstract byte getWinnerAsync(long[] jArr);

        public abstract int getFittingWinnerMasksCountAsync(long[] jArr, byte b2);

        public Board(Board board) {
            this.rules = board.rules;
            this.cellCount = (short) (board.rules.fieldSize * board.rules.fieldSize);
        }

        public Board(Rules rules) {
            this.rules = rules;
            this.cellCount = (short) (rules.fieldSize * rules.fieldSize);
        }

        public final boolean hasEmptyCells() {
            return getTotalTickCount() != this.cellCount;
        }

        public final boolean isEmpty() {
            return getTotalTickCount() == 0;
        }

        public final short getCellIdx(short s, short s2) {
            return this.rules.getCellIdx(s, s2);
        }

        public final byte getCellByXY(short s, short s2) {
            return getCell(getCellIdx(s, s2));
        }

        public final byte getOpponent(byte b2) {
            return b2 == 1 ? (byte) 2 : (byte) 1;
        }

        public final String getPlayerName(byte b2) {
            switch (b2) {
                case 1:
                    return "Player";
                case 2:
                    return "Ensor";
                default:
                    return "None";
            }
        }

        public final Rules getRules() {
            return this.rules;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 < this.rules.fieldSize) {
                    short s3 = 0;
                    while (true) {
                        short s4 = s3;
                        if (s4 < this.rules.fieldSize) {
                            switch (getCellByXY(s4, s2)) {
                                case 0:
                                    sb.append('.');
                                    break;
                                case 1:
                                    sb.append('+');
                                    break;
                                case 2:
                                    sb.append('-');
                                    break;
                            }
                            s3 = (short) (s4 + 1);
                        }
                    }
                    sb.append(SequenceUtils.EOL);
                    s = (short) (s2 + 1);
                } else {
                    return sb.toString();
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$FixedSizeSmallBoard.class */
    public static final class FixedSizeSmallBoard extends Board {

        /* renamed from: a */
        private long f2855a;

        /* renamed from: b */
        private long f2856b;

        private static void a(Rules rules) {
            if (rules.fieldSize > 8) {
                throw new IllegalArgumentException("Field size can not exceed 8");
            }
        }

        public FixedSizeSmallBoard(Board board) {
            super(board);
            a(board.rules);
            copyFieldFrom(board);
        }

        public FixedSizeSmallBoard(Rules rules) {
            super(rules);
            a(rules);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final Board cpy() {
            return new FixedSizeSmallBoard(this);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final boolean hasSameField(Board board) {
            FixedSizeSmallBoard fixedSizeSmallBoard = (FixedSizeSmallBoard) board;
            return this.f2855a == fixedSizeSmallBoard.f2855a && this.f2856b == fixedSizeSmallBoard.f2856b;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final int getTotalTickCount() {
            return Long.bitCount(this.f2855a) + Long.bitCount(this.f2856b);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final void copyFieldFrom(Board board) {
            FixedSizeSmallBoard fixedSizeSmallBoard = (FixedSizeSmallBoard) board;
            this.f2855a = fixedSizeSmallBoard.f2855a;
            this.f2856b = fixedSizeSmallBoard.f2856b;
        }

        private static boolean a(long j, int i) {
            return (j & (1 << i)) != 0;
        }

        private static long b(long j, int i) {
            return j | (1 << i);
        }

        private static long c(long j, int i) {
            return j & ((1 << i) ^ (-1));
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final byte getCell(short s) {
            if (a(this.f2855a, s)) {
                return (byte) 1;
            }
            if (a(this.f2856b, s)) {
                return (byte) 2;
            }
            return (byte) 0;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final void setCell(short s, byte b2) {
            switch (b2) {
                case 0:
                    this.f2855a = c(this.f2855a, s);
                    this.f2856b = c(this.f2856b, s);
                    return;
                case 1:
                    this.f2855a = b(this.f2855a, s);
                    this.f2856b = c(this.f2856b, s);
                    return;
                case 2:
                    this.f2855a = c(this.f2855a, s);
                    this.f2856b = b(this.f2856b, s);
                    return;
                default:
                    return;
            }
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final byte getWinner() {
            ShortArray fittingWinnerMask = getFittingWinnerMask();
            if (fittingWinnerMask == null) {
                return (byte) 0;
            }
            return getCell(fittingWinnerMask.items[0]);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final ShortArray getFittingWinnerMask() {
            return getFittingWinnerMaskAsync(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final ShortArray getFittingWinnerMaskAsync(long[] jArr) {
            if (getTotalTickCount() + 2 < (this.rules.winCondition << 1)) {
                return null;
            }
            int i = 0;
            while (i < this.rules.c.size) {
                long j = this.rules.c.items[i];
                if ((j & this.f2855a) != j && (j & this.f2856b) != j) {
                    i++;
                }
                return ((ShortArray[]) this.rules.f2873a.items)[i];
            }
            return null;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final byte getWinnerAsync(long[] jArr) {
            if (getTotalTickCount() + 2 < (this.rules.winCondition << 1)) {
                return (byte) 0;
            }
            for (int i = 0; i < this.rules.c.size; i++) {
                long j = this.rules.c.items[i];
                if ((j & this.f2855a) == j) {
                    return (byte) 1;
                }
                if ((j & this.f2856b) == j) {
                    return (byte) 2;
                }
            }
            return (byte) 0;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final int getFittingWinnerMasksCountAsync(long[] jArr, byte b2) {
            long j = b2 == 1 ? this.f2855a : this.f2856b;
            int i = 0;
            for (int i2 = 0; i2 < this.rules.c.size; i2++) {
                long j2 = this.rules.c.items[i2];
                if ((j2 & j) == j2) {
                    i++;
                }
            }
            return i;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$DynamicSizeBoard.class */
    public static final class DynamicSizeBoard extends Board {

        /* renamed from: a */
        private final BitVector f2851a;

        /* renamed from: b */
        private final BitVector f2852b;
        private static long[] c;

        public DynamicSizeBoard(Board board) {
            super(board);
            this.f2851a = new BitVector(this.cellCount);
            this.f2852b = new BitVector(this.cellCount);
            copyFieldFrom(board);
        }

        public DynamicSizeBoard(Rules rules) {
            super(rules);
            this.f2851a = new BitVector(this.cellCount);
            this.f2852b = new BitVector(this.cellCount);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final Board cpy() {
            return new DynamicSizeBoard(this);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final boolean hasSameField(Board board) {
            DynamicSizeBoard dynamicSizeBoard = (DynamicSizeBoard) board;
            return this.f2852b.exactlyTheSame(dynamicSizeBoard.f2852b) && this.f2851a.exactlyTheSame(dynamicSizeBoard.f2851a);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final int getTotalTickCount() {
            return this.f2851a.cardinality() + this.f2852b.cardinality();
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final void copyFieldFrom(Board board) {
            DynamicSizeBoard dynamicSizeBoard = (DynamicSizeBoard) board;
            for (int i = 0; i < this.cellCount; i++) {
                this.f2851a.unsafeSetValue(i, dynamicSizeBoard.f2851a.unsafeGet(i));
                this.f2852b.unsafeSetValue(i, dynamicSizeBoard.f2852b.unsafeGet(i));
            }
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final byte getCell(short s) {
            if (this.f2851a.unsafeGet(s)) {
                return (byte) 1;
            }
            if (this.f2852b.unsafeGet(s)) {
                return (byte) 2;
            }
            return (byte) 0;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final void setCell(short s, byte b2) {
            switch (b2) {
                case 0:
                    this.f2851a.unsafeSetValue(s, false);
                    this.f2852b.unsafeSetValue(s, false);
                    return;
                case 1:
                    this.f2851a.unsafeSetValue(s, true);
                    this.f2852b.unsafeSetValue(s, false);
                    return;
                case 2:
                    this.f2851a.unsafeSetValue(s, false);
                    this.f2852b.unsafeSetValue(s, true);
                    return;
                default:
                    return;
            }
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final byte getWinner() {
            ShortArray fittingWinnerMask = getFittingWinnerMask();
            if (fittingWinnerMask == null) {
                return (byte) 0;
            }
            return getCell(fittingWinnerMask.items[0]);
        }

        private static void a(long[] jArr, long[] jArr2) {
            int length = jArr.length;
            for (int i = 0; i < length; i++) {
                int i2 = i;
                jArr[i2] = jArr[i2] & jArr2[i];
            }
        }

        public static boolean exactlyTheSame(long[] jArr, long[] jArr2) {
            int length = jArr.length;
            for (int i = 0; i < length; i++) {
                if (jArr[i] != jArr2[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final ShortArray getFittingWinnerMask() {
            if (c == null || c.length != this.f2851a.words.length) {
                c = new long[this.f2851a.words.length];
            }
            return getFittingWinnerMaskAsync(c);
        }

        public final long[] createThreadSafeBits() {
            return new long[this.f2851a.words.length];
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final ShortArray getFittingWinnerMaskAsync(long[] jArr) {
            if (getTotalTickCount() + 2 < (this.rules.winCondition << 1)) {
                return null;
            }
            for (int i = 0; i < this.rules.f2874b.size; i++) {
                BitVector bitVector = ((BitVector[]) this.rules.f2874b.items)[i];
                System.arraycopy(this.f2851a.words, 0, jArr, 0, jArr.length);
                a(jArr, bitVector.words);
                if (!exactlyTheSame(jArr, bitVector.words)) {
                    System.arraycopy(this.f2852b.words, 0, jArr, 0, jArr.length);
                    a(jArr, bitVector.words);
                    if (exactlyTheSame(jArr, bitVector.words)) {
                        return ((ShortArray[]) this.rules.f2873a.items)[i];
                    }
                } else {
                    return ((ShortArray[]) this.rules.f2873a.items)[i];
                }
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final byte getWinnerAsync(long[] jArr) {
            if (getTotalTickCount() + 2 < (this.rules.winCondition << 1)) {
                return (byte) 0;
            }
            for (int i = 0; i < this.rules.f2874b.size; i++) {
                BitVector bitVector = ((BitVector[]) this.rules.f2874b.items)[i];
                System.arraycopy(this.f2851a.words, 0, jArr, 0, jArr.length);
                a(jArr, bitVector.words);
                if (exactlyTheSame(jArr, bitVector.words)) {
                    return (byte) 1;
                }
                System.arraycopy(this.f2852b.words, 0, jArr, 0, jArr.length);
                a(jArr, bitVector.words);
                if (exactlyTheSame(jArr, bitVector.words)) {
                    return (byte) 2;
                }
            }
            return (byte) 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Board
        public final int getFittingWinnerMasksCountAsync(long[] jArr, byte b2) {
            BitVector bitVector = b2 == 1 ? this.f2851a : this.f2852b;
            int i = 0;
            for (int i2 = 0; i2 < this.rules.f2874b.size; i2++) {
                BitVector bitVector2 = ((BitVector[]) this.rules.f2874b.items)[i2];
                System.arraycopy(bitVector.words, 0, jArr, 0, jArr.length);
                a(jArr, bitVector2.words);
                if (exactlyTheSame(jArr, bitVector2.words)) {
                    i++;
                }
            }
            return i;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Rules.class */
    public static final class Rules {
        public final short fieldSize;
        public final short winCondition;

        /* renamed from: a */
        private final Array<ShortArray> f2873a = new Array<>(true, 8, ShortArray.class);

        /* renamed from: b */
        private final Array<BitVector> f2874b = new Array<>(true, 8, BitVector.class);
        private final LongArray c = new LongArray(8);

        public Rules(short s, short s2) {
            this.fieldSize = s;
            this.winCondition = s2;
            a();
        }

        public final short getCellIdx(short s, short s2) {
            return (short) ((s2 * this.fieldSize) + s);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void a() {
            this.f2873a.clear();
            this.f2874b.clear();
            this.c.clear();
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 >= this.fieldSize) {
                    break;
                }
                short s3 = 0;
                while (true) {
                    short s4 = s3;
                    if (s4 <= this.fieldSize - this.winCondition) {
                        ShortArray shortArray = new ShortArray();
                        short s5 = s4;
                        while (true) {
                            short s6 = s5;
                            if (s6 < s4 + this.winCondition) {
                                shortArray.add(getCellIdx(s6, s2));
                                s5 = (short) (s6 + 1);
                            }
                        }
                        this.f2873a.add(shortArray);
                        s3 = (short) (s4 + 1);
                    }
                }
                s = (short) (s2 + 1);
            }
            short s7 = 0;
            while (true) {
                short s8 = s7;
                if (s8 >= this.fieldSize) {
                    break;
                }
                short s9 = 0;
                while (true) {
                    short s10 = s9;
                    if (s10 <= this.fieldSize - this.winCondition) {
                        ShortArray shortArray2 = new ShortArray();
                        short s11 = s10;
                        while (true) {
                            short s12 = s11;
                            if (s12 < s10 + this.winCondition) {
                                shortArray2.add(getCellIdx(s8, s12));
                                s11 = (short) (s12 + 1);
                            }
                        }
                        this.f2873a.add(shortArray2);
                        s9 = (short) (s10 + 1);
                    }
                }
                s7 = (short) (s8 + 1);
            }
            short[] sArr = {new short[]{1, 1}, new short[]{1, -1}};
            for (int i = 0; i < 2; i++) {
                short[] sArr2 = sArr[i];
                short s13 = 0;
                while (true) {
                    short s14 = s13;
                    if (s14 < this.fieldSize) {
                        short s15 = 0;
                        while (true) {
                            short s16 = s15;
                            if (s16 < this.fieldSize) {
                                ShortArray shortArray3 = new ShortArray();
                                short s17 = s16;
                                short s18 = s14;
                                do {
                                    shortArray3.add(getCellIdx(s17, s18));
                                    if (shortArray3.size == this.winCondition) {
                                        break;
                                    }
                                    s17 = (short) (s17 + sArr2[0]);
                                    s18 = (short) (s18 + sArr2[1]);
                                    if (s17 < 0 || s18 < 0 || s17 >= this.fieldSize) {
                                        break;
                                    }
                                } while (s18 < this.fieldSize);
                                if (shortArray3.size == this.winCondition) {
                                    this.f2873a.add(shortArray3);
                                }
                                s15 = (short) (s16 + 1);
                            }
                        }
                        s13 = (short) (s14 + 1);
                    }
                }
            }
            Array.ArrayIterator<ShortArray> it = this.f2873a.iterator();
            while (it.hasNext()) {
                ShortArray next = it.next();
                BitVector bitVector = new BitVector(this.fieldSize * this.fieldSize);
                for (int i2 = 0; i2 < next.size; i2++) {
                    bitVector.unsafeSet(next.items[i2]);
                }
                this.f2874b.add(bitVector);
                if (this.fieldSize <= 8) {
                    long j = 0;
                    for (int i3 = 0; i3 < next.size; i3++) {
                        j |= 1 << next.items[i3];
                    }
                    this.c.add(j);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$AiMixin.class */
    public static final class AiMixin implements Ai {

        /* renamed from: a */
        private Ai f2847a;

        /* renamed from: b */
        private Ai f2848b;
        private float c;
        private float d;

        public AiMixin(Ai ai, float f, Ai ai2, float f2) {
            this.f2847a = ai;
            this.f2848b = ai2;
            this.c = f;
            this.d = f2;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Ai
        public final short makeMove(Board board) {
            if (this.c == 0.0f) {
                TicTacToeScreen.p.a("running " + this.f2848b.getClass().getSimpleName());
                return this.f2848b.makeMove(board);
            }
            if (this.d == 0.0f) {
                TicTacToeScreen.p.a("running " + this.f2847a.getClass().getSimpleName());
                return this.f2847a.makeMove(board);
            }
            if (Level1Ai.f2859a.nextFloat() * (this.c + this.d) < this.c) {
                TicTacToeScreen.p.a("running " + this.f2847a.getClass().getSimpleName());
                return this.f2847a.makeMove(board);
            }
            TicTacToeScreen.p.a("running " + this.f2848b.getClass().getSimpleName());
            return this.f2848b.makeMove(board);
        }

        public final String toString() {
            return "Mixin " + this.f2847a.getClass().getSimpleName() + SequenceUtils.SPACE + this.c + " / " + this.f2848b.getClass().getSimpleName() + SequenceUtils.SPACE + this.d;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Level1Ai.class */
    public static final class Level1Ai implements Ai {

        /* renamed from: a */
        private static final RandomXS128 f2859a = new RandomXS128();

        /* renamed from: b */
        private final ShortArray f2860b = new ShortArray();
        private final byte c;

        static /* synthetic */ boolean a(Level1Ai level1Ai, Board board) {
            return a(board);
        }

        public Level1Ai(byte b2) {
            this.c = b2;
        }

        private static boolean a(Board board) {
            return board.getTotalTickCount() == board.cellCount - 1;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Ai
        public final short makeMove(Board board) {
            byte opponent;
            if (!board.isEmpty()) {
                int i = 0;
                int i2 = 0;
                short s = 0;
                while (true) {
                    short s2 = s;
                    if (s2 >= board.cellCount) {
                        break;
                    }
                    byte cell = board.getCell(s2);
                    if (cell != 0) {
                        if (cell == this.c) {
                            i++;
                        } else {
                            i2++;
                        }
                    }
                    s = (short) (s2 + 1);
                }
                if (i > i2) {
                    opponent = this.c;
                } else {
                    opponent = board.getOpponent(this.c);
                }
                this.f2860b.clear();
                short s3 = 0;
                while (true) {
                    short s4 = s3;
                    if (s4 >= board.rules.fieldSize) {
                        break;
                    }
                    short s5 = 0;
                    while (true) {
                        short s6 = s5;
                        if (s6 < board.rules.fieldSize) {
                            if (board.getCellByXY(s4, s6) == 0) {
                                boolean z = false;
                                short s7 = -1;
                                while (true) {
                                    short s8 = s7;
                                    if (s8 > 1) {
                                        break;
                                    }
                                    short s9 = -1;
                                    while (true) {
                                        short s10 = s9;
                                        if (s10 <= 1) {
                                            if (s8 != 0 || s10 != 0) {
                                                short s11 = (short) (s4 + s8);
                                                short s12 = (short) (s6 + s10);
                                                if (s11 >= 0 && s11 < board.rules.fieldSize && s12 >= 0 && s12 < board.rules.fieldSize && board.getCellByXY(s11, s12) == opponent) {
                                                    z = true;
                                                    break;
                                                }
                                            }
                                            s9 = (short) (s10 + 1);
                                        }
                                    }
                                    s7 = (short) (s8 + 1);
                                }
                                if (z) {
                                    this.f2860b.add(board.getCellIdx(s4, s6));
                                }
                            }
                            s5 = (short) (s6 + 1);
                        }
                    }
                    s3 = (short) (s4 + 1);
                }
                if (this.f2860b.size == 0) {
                    TicTacToeScreen.p.a("L1 - making random first move (fallback)");
                    return (short) f2859a.nextInt(board.cellCount);
                }
                TicTacToeScreen.p.a("L1 - making random move near " + (opponent == this.c ? "us" : "opponent"));
                return this.f2860b.get(f2859a.nextInt(this.f2860b.size));
            }
            TicTacToeScreen.p.a("L1 - making random first move");
            return (short) f2859a.nextInt(board.cellCount);
        }

        public final String toString() {
            return getClass().getSimpleName();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Level2Ai.class */
    public static final class Level2Ai implements Ai {

        /* renamed from: a */
        private final byte f2861a;

        /* renamed from: b */
        private final ShortArray f2862b = new ShortArray();
        private final Level1Ai c = new Level1Ai((byte) 2);

        public Level2Ai(byte b2) {
            this.f2861a = b2;
        }

        public final short tryWin(Board board) {
            this.f2862b.clear();
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 >= board.cellCount) {
                    break;
                }
                if (board.getCell(s2) == 0) {
                    board.setCell(s2, this.f2861a);
                    byte winner = board.getWinner();
                    board.setCell(s2, (byte) 0);
                    if (winner == this.f2861a) {
                        this.f2862b.add(s2);
                        break;
                    }
                }
                s = (short) (s2 + 1);
            }
            if (this.f2862b.size != 0) {
                return this.f2862b.items[Level1Ai.f2859a.nextInt(this.f2862b.size)];
            }
            return (short) -1;
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Ai
        public final short makeMove(Board board) {
            short tryWin = tryWin(board);
            if (tryWin == -1) {
                return this.c.makeMove(board);
            }
            TicTacToeScreen.p.a("L2 - trying to win");
            return tryWin;
        }

        public final String toString() {
            return getClass().getSimpleName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Level3Ai.class */
    public static final class Level3Ai implements Ai {

        /* renamed from: a */
        private final byte f2863a;

        /* renamed from: b */
        private final Level1Ai f2864b;
        private final Level2Ai c;

        /* synthetic */ Level3Ai(byte b2, byte b3) {
            this(b2);
        }

        private Level3Ai(byte b2) {
            this.f2863a = b2;
            this.f2864b = new Level1Ai(b2);
            this.c = new Level2Ai(b2);
        }

        public short a(Board board) {
            byte opponent = board.getOpponent(this.f2863a);
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 < board.cellCount) {
                    if (board.getCell(s2) == 0) {
                        board.setCell(s2, opponent);
                        byte winner = board.getWinner();
                        board.setCell(s2, (byte) 0);
                        if (winner == opponent) {
                            return s2;
                        }
                    }
                    s = (short) (s2 + 1);
                } else {
                    return (short) -1;
                }
            }
        }

        public short b(Board board) {
            if (board.isEmpty() || Level1Ai.a(this.f2864b, board)) {
                return this.f2864b.makeMove(board);
            }
            short tryWin = this.c.tryWin(board);
            if (tryWin != -1) {
                return tryWin;
            }
            return a(board);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Ai
        public final short makeMove(Board board) {
            short b2 = b(board);
            if (b2 == -1) {
                return this.f2864b.makeMove(board);
            }
            TicTacToeScreen.p.a("L3 - trying to win or counter");
            return b2;
        }

        public final String toString() {
            return getClass().getSimpleName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Level4Ai.class */
    public static final class Level4Ai implements Ai {

        /* renamed from: a */
        private final byte f2865a;

        /* renamed from: b */
        private final ShortArray f2866b = new ShortArray();
        private final Level2Ai c;
        private final Level3Ai d;

        public Level4Ai(byte b2) {
            this.f2865a = b2;
            this.c = new Level2Ai(b2);
            this.d = new Level3Ai(b2, (byte) 0);
        }

        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Ai
        public final short makeMove(Board board) {
            short b2 = this.d.b(board);
            if (b2 == -1) {
                this.f2866b.clear();
                short s = 0;
                while (true) {
                    short s2 = s;
                    if (s2 >= board.cellCount) {
                        break;
                    }
                    if (board.getCell(s2) == 0) {
                        this.f2866b.add(s2);
                    }
                    s = (short) (s2 + 1);
                }
                this.f2866b.shuffle();
                int i = 0;
                int i2 = 0;
                short s3 = 0;
                while (true) {
                    short s4 = s3;
                    if (s4 >= board.cellCount) {
                        break;
                    }
                    byte cell = board.getCell(s4);
                    if (cell == this.f2865a) {
                        i++;
                    } else if (cell != 0) {
                        i2++;
                    }
                    s3 = (short) (s4 + 1);
                }
                if (i > i2) {
                    for (int i3 = 0; i3 < this.f2866b.size; i3++) {
                        short s5 = this.f2866b.items[i3];
                        board.setCell(s5, this.f2865a);
                        short tryWin = this.c.tryWin(board);
                        board.setCell(s5, (byte) 0);
                        if (tryWin != -1) {
                            TicTacToeScreen.p.a("L4 - winning in 2 turns");
                            return tryWin;
                        }
                    }
                } else {
                    byte opponent = board.getOpponent(this.f2865a);
                    short s6 = 0;
                    while (true) {
                        short s7 = s6;
                        if (s7 >= board.cellCount) {
                            break;
                        }
                        if (board.getCell(s7) == 0) {
                            board.setCell(s7, opponent);
                            short a2 = this.d.a(board);
                            board.setCell(s7, (byte) 0);
                            if (a2 != -1) {
                                TicTacToeScreen.p.a("L4 - countering in 2 turns");
                                return a2;
                            }
                        }
                        s6 = (short) (s7 + 1);
                    }
                }
                return this.d.makeMove(board);
            }
            TicTacToeScreen.p.a("L4 - trying to win or counter");
            return b2;
        }

        public final String toString() {
            return getClass().getSimpleName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Level5Ai.class */
    public static final class Level5Ai implements Ai {

        /* renamed from: a */
        private final byte f2867a;

        /* renamed from: b */
        private int f2868b;
        private final Level3Ai c;
        private final ThreadPoolExecutor d;
        private static final byte[] e = new byte[16];

        public Level5Ai(byte b2) {
            new ShortArray();
            this.f2867a = b2;
            this.c = new Level3Ai(b2, (byte) 0);
            this.d = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
        }

        private void a(Board board, byte b2, int i, SimResult simResult) {
            ShortArray shortArray = new ShortArray();
            a(board, shortArray);
            for (int i2 = 0; i2 < shortArray.size; i2++) {
                short s = shortArray.items[i2];
                board.setCell(s, b2);
                byte winnerAsync = board.getWinnerAsync(simResult.f2870b);
                if (winnerAsync != 0) {
                    double pow = (Math.pow(1.0d, i == 0 ? 1.0d : 0.75d * (i - 1)) / shortArray.size) * Math.pow(10.0d, board.getFittingWinnerMasksCountAsync(simResult.f2870b, winnerAsync) - 1);
                    double[] dArr = simResult.c;
                    int i3 = winnerAsync - 1;
                    dArr[i3] = dArr[i3] + pow;
                } else if (!board.hasEmptyCells()) {
                    simResult.d++;
                } else if (i != this.f2868b) {
                    a(board, board.getOpponent(b2), i + 1, simResult);
                } else {
                    simResult.e++;
                }
                board.setCell(s, (byte) 0);
            }
        }

        static {
            int i = 0;
            for (int i2 = -1; i2 <= 1; i2++) {
                for (int i3 = -1; i3 <= 1; i3++) {
                    if (i3 != 0 || i2 != 0) {
                        int i4 = i;
                        int i5 = i + 1;
                        e[i4] = (byte) i2;
                        i = i5 + 1;
                        e[i5] = (byte) i3;
                    }
                }
            }
        }

        private static void a(Board board, ShortArray shortArray) {
            short s = board.rules.fieldSize;
            short s2 = 0;
            while (true) {
                short s3 = s2;
                if (s3 < s) {
                    short s4 = 0;
                    while (true) {
                        short s5 = s4;
                        if (s5 < s) {
                            if (board.getCellByXY(s5, s3) == 0) {
                                boolean z = false;
                                int i = 0;
                                while (true) {
                                    if (i >= e.length) {
                                        break;
                                    }
                                    short s6 = (short) (e[i] + s5);
                                    short s7 = (short) (e[i + 1] + s3);
                                    if (s7 < 0 || s7 >= s || s6 < 0 || s6 >= s || board.getCellByXY(s6, s7) == 0) {
                                        i += 2;
                                    } else {
                                        z = true;
                                        break;
                                    }
                                }
                                if (z) {
                                    shortArray.add(board.getCellIdx(s5, s3));
                                }
                            }
                            s4 = (short) (s5 + 1);
                        }
                    }
                    s2 = (short) (s3 + 1);
                } else {
                    return;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v63, types: [java.util.concurrent.Future] */
        /* JADX WARN: Type inference failed for: r0v64, types: [java.lang.Exception] */
        /* JADX WARN: Type inference failed for: r0v66, types: [java.lang.Object] */
        @Override // com.prineside.tdi2.screens.TicTacToeScreen.Ai
        public final short makeMove(Board board) {
            short b2 = this.c.b(board);
            if (b2 == -1) {
                int i = 0;
                short s = 0;
                while (true) {
                    short s2 = s;
                    if (s2 >= board.cellCount) {
                        break;
                    }
                    if (board.getCell(s2) == this.f2867a) {
                        i++;
                    }
                    s = (short) (s2 + 1);
                }
                int totalTickCount = board.getTotalTickCount() - i;
                if (totalTickCount == 1 && i == 0) {
                    byte b3 = this.f2867a == 2 ? (byte) 1 : (byte) 2;
                    short s3 = 0;
                    while (true) {
                        short s4 = s3;
                        if (s4 >= board.rules.fieldSize) {
                            break;
                        }
                        short s5 = 0;
                        while (true) {
                            short s6 = s5;
                            if (s6 < board.rules.fieldSize) {
                                if (board.getCell(board.getCellIdx(s4, s6)) != b3) {
                                    s5 = (short) (s6 + 1);
                                } else {
                                    return board.getCellIdx((short) (s4 < board.rules.fieldSize / 2 ? s4 + 1 : s4 - 1), (short) (s6 < board.rules.fieldSize / 2 ? s6 + 1 : s6 - 1));
                                }
                            }
                        }
                        s3 = (short) (s4 + 1);
                    }
                }
                if (totalTickCount == i) {
                    this.f2868b = 3;
                } else {
                    this.f2868b = 4;
                }
                if (board.rules.fieldSize <= 5) {
                    TicTacToeScreen.p.a("L5 - small field, increase depth");
                    this.f2868b += 2;
                }
                if (board.getTotalTickCount() + 5 < (board.rules.winCondition << 1)) {
                    TicTacToeScreen.p.a("L5 - no possible winner yet, increase depth");
                    this.f2868b += 2;
                }
                TicTacToeScreen.p.a("L5 - setting maxDepth to " + this.f2868b);
                ShortArray shortArray = new ShortArray();
                a(board, shortArray);
                shortArray.shuffle();
                Array array = new Array(true, shortArray.size, SimResult.class);
                byte opponent = board.getOpponent(this.f2867a);
                LinkedList<??> linkedList = new LinkedList();
                AtomicInteger atomicInteger = new AtomicInteger();
                for (int i2 = 0; i2 < shortArray.size; i2++) {
                    SimResult simResult = new SimResult((byte) 0);
                    simResult.f2870b = board instanceof DynamicSizeBoard ? ((DynamicSizeBoard) board).createThreadSafeBits() : null;
                    simResult.f2869a = shortArray.items[i2];
                    array.add(simResult);
                    Board cpy = board.cpy();
                    cpy.setCell(simResult.f2869a, this.f2867a);
                    linkedList.add(this.d.submit(() -> {
                        a(cpy, opponent, 0, simResult);
                        atomicInteger.addAndGet(1);
                        TicTacToeScreen.b(simResult.f2869a);
                    }));
                }
                for (?? r0 : linkedList) {
                    try {
                        r0 = r0.get();
                    } catch (Exception e2) {
                        r0.printStackTrace();
                    }
                }
                double d = -1.7976931348623157E308d;
                Array array2 = new Array(true, 1, SimResult.class);
                Array.ArrayIterator it = array.iterator();
                while (it.hasNext()) {
                    SimResult simResult2 = (SimResult) it.next();
                    simResult2.a(this.f2867a);
                    simResult2.a(opponent);
                    double a2 = (simResult2.a(this.f2867a) * 0.015d) - (simResult2.a(opponent) * 0.1d);
                    if (d < a2) {
                        d = a2;
                        array2.clear();
                        array2.add(simResult2);
                    } else if (d == a2) {
                        array2.add(simResult2);
                    }
                }
                return ((SimResult) array2.random()).f2869a;
            }
            TicTacToeScreen.p.a("L5 - tryWinOrCounter");
            return b2;
        }

        public final String toString() {
            return getClass().getSimpleName();
        }

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Level5Ai$SimResult.class */
        public static final class SimResult {

            /* renamed from: a */
            short f2869a;

            /* renamed from: b */
            long[] f2870b;
            final double[] c;
            int d;
            int e;

            private SimResult() {
                this.c = new double[2];
            }

            /* synthetic */ SimResult(byte b2) {
                this();
            }

            public double a(byte b2) {
                return this.c[b2 - 1];
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$Node.class */
    private static class Node implements Comparable<Node> {

        /* renamed from: a */
        private byte f2871a;

        /* renamed from: b */
        private Board f2872b;
        private short c;
        private Array<Node> d;

        @Override // java.lang.Comparable
        public int compareTo(Node node) {
            return Double.compare(0.0d, 0.0d);
        }

        public String toString() {
            return "Node (player: " + (this.f2871a == 2 ? "Ensor" : "Player") + ", move: " + ((int) this.c) + ", children: " + this.d.size + ", numVisits: 0, uct: 0.0, victories: 0, draws: 0, loses: 0, winner: 0, field: \n" + this.f2872b + ")";
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$TicTacToeSimulator.class */
    private static final class TicTacToeSimulator {
        private TicTacToeSimulator() {
        }

        /* synthetic */ TicTacToeSimulator(byte b2) {
            this();
        }

        static {
            new ShortArray();
            new ShortArray();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$MCTSBestMoveFinder.class */
    private static class MCTSBestMoveFinder {
        static {
            new ShortArray();
            new Array(true, 1, Node.class);
        }

        MCTSBestMoveFinder() {
            new TicTacToeSimulator((byte) 0);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$MiniMaxCombined.class */
    private static final class MiniMaxCombined {
        static {
            new ShortArray();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$EndGameResult.class */
    public static final class EndGameResult {

        /* renamed from: a */
        byte f2853a;

        /* renamed from: b */
        double f2854b;

        private EndGameResult() {
        }

        /* synthetic */ EndGameResult(byte b2) {
            this();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/TicTacToeScreen$GameReplay.class */
    public static final class GameReplay {

        /* renamed from: a */
        Array<IntPair> f2857a;

        /* renamed from: b */
        private IntMap<Array<String>> f2858b;

        private GameReplay() {
            this.f2857a = new Array<>(true, 1, IntPair.class);
        }

        /* synthetic */ GameReplay(byte b2) {
            this();
        }

        public synchronized void a(String str) {
            if (TicTacToeScreen.LOG_AI_ACTIONS) {
                if (this.f2858b == null) {
                    this.f2858b = new IntMap<>();
                }
                Array<String> array = this.f2858b.get(this.f2857a.size);
                Array<String> array2 = array;
                if (array == null) {
                    array2 = new Array<>(true, 1, String.class);
                    this.f2858b.put(this.f2857a.size, array2);
                }
                array2.add(str);
            }
            if (TicTacToeScreen.PRINT_AI_ACTIONS) {
                TicTacToeScreen.f2843a.i("ai: " + str, new Object[0]);
            }
        }
    }
}
