package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.UBJsonReader;
import com.badlogic.gdx.utils.UBJsonWriter;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.script.ScriptEnvironment;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Stack;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.ui.VerticalGroup;
import com.prineside.tdi2.scene2d.ui.WidgetGroup;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.FileUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.LogLevel;
import com.prineside.tdi2.utils.logging.LogWriter;
import com.prineside.tdi2.utils.logging.Logger;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.regex.Matcher;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DeveloperConsole.class */
public class DeveloperConsole implements Disposable, UiManager.UiComponent {
    private final UiManager.UiLayer c;
    private final UiManager.UiLayer d;
    private final UiManager.UiLayer e;
    private boolean f;
    private final VerticalGroup g;
    private final Group h;
    private final TextField i;
    private final ScrollPane j;
    private final Label.LabelStyle l;
    private final Label.LabelStyle m;
    private final Label.LabelStyle n;
    private final ComplexButton o;
    private final ComplexButton p;
    public final Table buttonsNStuff;
    private Table q;
    private Label r;
    private ScriptEnvironment.Suggestion s;
    private BufferedReader z;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3512a = TLog.forClass(DeveloperConsole.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color[] f3513b = {MaterialColor.BLUE.P300, MaterialColor.BLUE_GREY.P300, MaterialColor.GREEN.P300, MaterialColor.PURPLE.P300, MaterialColor.YELLOW.P300, MaterialColor.LIGHT_BLUE.P300, MaterialColor.GREY.P300, MaterialColor.CYAN.P300, MaterialColor.ORANGE.P300, MaterialColor.INDIGO.P300, MaterialColor.RED.P300, MaterialColor.TEAL.P300, MaterialColor.AMBER.P300, MaterialColor.PINK.P300, MaterialColor.LIME.P300, MaterialColor.LIGHT_GREEN.P300, MaterialColor.DEEP_PURPLE.P300, MaterialColor.DEEP_ORANGE.P300, MaterialColor.BROWN.P300};
    private static final Calendar x = new GregorianCalendar();
    private static final StringBuilder y = new StringBuilder();
    private final Array<LogLine> k = new Array<>(LogLine.class);
    private final CustomSettingsUI[] t = new CustomSettingsUI[SettingsManager.CustomValueType.values.length];
    private final Listener<ScreenResize> u = screenResize -> {
        h();
    };
    private int v = 0;
    private final Array<String> w = new Array<>(new String[]{""});

    static /* synthetic */ int a(DeveloperConsole developerConsole, int i) {
        developerConsole.v = 0;
        return 0;
    }

    @Null
    public static DeveloperConsole i() {
        if ((Config.isModdingMode() || Game.i.progressManager.isDeveloperModeEnabled()) && Game.i.progressManager.isDeveloperModeEnabled()) {
            return (DeveloperConsole) Game.i.uiManager.getComponent(DeveloperConsole.class);
        }
        return null;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public boolean isPersistent() {
        return true;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void preRender(float f) {
        if (this.f && this.z != null) {
            try {
                String readLine = this.z.readLine();
                String str = null;
                Array array = new Array();
                long j = 0;
                byte b2 = 1;
                StringBuilder stringBuilder = new StringBuilder();
                while (readLine != null) {
                    Matcher matcher = LogWriter.LOG_FILE_ENTRY_REGEX.matcher("");
                    try {
                        matcher.reset(readLine);
                        if (matcher.find()) {
                            if (str != null) {
                                array.add(new Logger.LogEntry(b2, j, str, stringBuilder.toString()));
                                stringBuilder.setLength(0);
                            }
                            j = LogWriter.LOG_DATE_FORMAT.parse(matcher.group(1)).getTime();
                            b2 = LogLevel.shortNameToLevel(matcher.group(2).charAt(0));
                            str = matcher.group(3);
                            stringBuilder.append(readLine.substring(matcher.group().length()));
                        } else {
                            stringBuilder.append('\n').append(readLine);
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to parse log line: " + readLine);
                        e.printStackTrace(System.out);
                    }
                    readLine = this.z.readLine();
                }
                if (str != null) {
                    array.add(new Logger.LogEntry(b2, j, str, stringBuilder.toString()));
                    stringBuilder.setLength(0);
                }
                for (int i = 0; i < array.size; i++) {
                    a((Logger.LogEntry) array.get(i));
                }
            } catch (Exception e2) {
                System.err.println("Failed to read log file");
                e2.printStackTrace();
                this.z = null;
            }
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void postRender(float f) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DeveloperConsole$CustomSettingsUI.class */
    public static class CustomSettingsUI {

        /* renamed from: a, reason: collision with root package name */
        TextFieldXPlatform f3520a;

        /* renamed from: b, reason: collision with root package name */
        LabelToggleButton f3521b;

        private CustomSettingsUI() {
        }

        /* synthetic */ CustomSettingsUI(byte b2) {
            this();
        }
    }

    public DeveloperConsole() {
        f3512a.i("creating DeveloperConsole", new Object[0]);
        this.d = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.OVERLAY, 9000, "DeveloperConsole background", true);
        this.d.ignoreVisibleFrame = true;
        this.e = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 9001, "DeveloperConsole main");
        this.e.followVisibleFrame = true;
        this.e.ignoreVisibleFrame = true;
        Game.i.uiManager.rebuildLayers();
        this.c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 9002, "DeveloperConsole toggle button");
        this.l = new Label.LabelStyle(Game.i.assetManager.getDebugFont(false), Color.WHITE);
        this.m = new Label.LabelStyle(Game.i.assetManager.getDebugFont(false), Color.WHITE);
        this.n = new Label.LabelStyle(Game.i.assetManager.getDebugFont(true), Color.WHITE);
        this.d.getTable().setBackground(new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(User32.VK_OEM_RESET)));
        WidgetGroup widgetGroup = new WidgetGroup();
        widgetGroup.setTransform(false);
        this.e.getTable().add((Table) widgetGroup).expand().fill().pad(8.0f);
        Table table = new Table();
        table.setFillParent(true);
        widgetGroup.addActor(table);
        Table table2 = new Table();
        table2.setTouchable(Touchable.childrenOnly);
        table2.setFillParent(true);
        widgetGroup.addActor(table2);
        this.buttonsNStuff = new Table();
        table2.add(this.buttonsNStuff).expand().top().left().padLeft(-8.0f).padTop(0.0f);
        this.h = new Group();
        this.h.setTransform(false);
        this.h.setSize(560.0f, 600.0f);
        this.h.setPosition(96.0f, -438.0f);
        this.h.setVisible(false);
        this.buttonsNStuff.addActor(this.h);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(560.0f, 600.0f);
        image.setColor(0.1f, 0.1f, 0.1f, 1.0f);
        this.h.addActor(image);
        Table table3 = new Table();
        TextField.TextFieldStyle textFieldStyle = Game.i.assetManager.getTextFieldStyle(21);
        SettingsManager.CustomValueType[] customValueTypeArr = new SettingsManager.CustomValueType[SettingsManager.CustomValueType.values.length];
        System.arraycopy(SettingsManager.CustomValueType.values, 0, customValueTypeArr, 0, SettingsManager.CustomValueType.values.length);
        Arrays.sort(customValueTypeArr, (customValueType, customValueType2) -> {
            return customValueType.name().compareTo(customValueType2.name());
        });
        for (final SettingsManager.CustomValueType customValueType3 : customValueTypeArr) {
            final CustomSettingsUI customSettingsUI = new CustomSettingsUI((byte) 0);
            this.t[customValueType3.ordinal()] = customSettingsUI;
            LabelToggleButton labelToggleButton = new LabelToggleButton(customValueType3.name(), Game.i.settingsManager.getCustomValue(customValueType3) != 0.0d, 21, 24.0f, bool -> {
                if (bool.booleanValue()) {
                    Game.i.settingsManager.setCustomValue(customValueType3, 1.0d);
                } else {
                    Game.i.settingsManager.setCustomValue(customValueType3, 0.0d);
                }
                customSettingsUI.f3520a.setText(new StringBuilder().append(Game.i.settingsManager.getCustomValue(customValueType3)).toString());
            });
            customSettingsUI.f3521b = labelToggleButton;
            table3.add(labelToggleButton).size(460.0f, 24.0f).padBottom(2.0f);
            customSettingsUI.f3520a = new TextFieldXPlatform(new StringBuilder().append(Game.i.settingsManager.getCustomValue(customValueType3)).toString(), textFieldStyle);
            customSettingsUI.f3520a.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.DeveloperConsole.1
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        double parseDouble = Double.parseDouble(customSettingsUI.f3520a.getText());
                        Game.i.settingsManager.setCustomValue(customValueType3, parseDouble);
                        customSettingsUI.f3521b.setEnabled(parseDouble != 0.0d);
                    } catch (Exception unused) {
                    }
                }
            });
            table3.add((Table) customSettingsUI.f3520a).size(80.0f, 24.0f).padBottom(2.0f).row();
        }
        ScrollPane scrollPane = new ScrollPane(table3);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(540.0f, 600.0f);
        scrollPane.setPosition(10.0f, 0.0f);
        this.h.addActor(scrollPane);
        ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            setCustomSettingsVisible(!isCustomSettingsVisible());
        });
        complexButton.setBackground(Game.i.assetManager.getQuad("ui.console.buttonBg"), 0.0f, 0.0f, 80.0f, 52.0f);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-tools"), 24.0f, 10.0f, 32.0f, 32.0f);
        complexButton.setBackgroundColors(MaterialColor.DEEP_ORANGE.P800, MaterialColor.DEEP_ORANGE.P900, MaterialColor.DEEP_ORANGE.P700, Color.BLACK);
        this.buttonsNStuff.add((Table) complexButton).size(80.0f, 52.0f).top().left().padBottom(4.0f).row();
        ComplexButton complexButton2 = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            ItemCreationOverlay.i().show();
            setVisible(false);
        });
        complexButton2.setBackground(Game.i.assetManager.getQuad("ui.console.buttonBg"), 0.0f, 0.0f, 80.0f, 52.0f);
        complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-backpack"), 24.0f, 10.0f, 32.0f, 32.0f);
        complexButton2.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P900, MaterialColor.LIGHT_GREEN.P700, Color.BLACK);
        this.buttonsNStuff.add((Table) complexButton2).size(80.0f, 52.0f).top().left().padBottom(4.0f).row();
        ComplexButton complexButton3 = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            StateDebugger.i().show();
            setVisible(false);
        });
        complexButton3.setBackground(Game.i.assetManager.getQuad("ui.console.buttonBg"), 0.0f, 0.0f, 80.0f, 52.0f);
        complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-magnifying-glass"), 24.0f, 10.0f, 32.0f, 32.0f);
        complexButton3.setBackgroundColors(MaterialColor.LIME.P800, MaterialColor.LIME.P900, MaterialColor.LIME.P700, Color.BLACK);
        this.buttonsNStuff.add((Table) complexButton3).size(80.0f, 52.0f).top().left().padBottom(4.0f).row();
        this.g = new VerticalGroup();
        this.g.grow();
        this.g.padTop(220.0f);
        this.j = new ScrollPane(this.g, Game.i.assetManager.getScrollPaneStyle(16.0f));
        this.j.setOverscroll(false, false);
        UiUtils.enableMouseMoveScrollFocus(this.j);
        Table table4 = new Table();
        this.i = new TextFieldXPlatform("", new TextField.TextFieldStyle(Game.i.assetManager.getLargeDebugFont(false), Color.WHITE, new Quad(Game.i.assetManager.getQuad("ui.textField.console.cursor"), true), new Quad(Game.i.assetManager.getQuad("ui.textField.console.selection"), true), new Quad(Game.i.assetManager.getQuad("ui.textField.console.background"), true)));
        this.i.setFocusTraversal(false);
        table4.add((Table) this.i).expandX().fillX().height(64.0f);
        this.r = new Label("", new Label.LabelStyle(Game.i.assetManager.getLargeDebugFont(false), Color.WHITE));
        this.r.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.r.setHeight(64.0f);
        this.r.setWidth(400.0f);
        this.r.setTouchable(Touchable.disabled);
        this.r.setX(15.0f);
        this.r.setY(0.0f);
        table4.addActor(this.r);
        this.q = new Table();
        this.q.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.BLUE_GREY.P900));
        this.q.setSize(600.0f, 200.0f);
        table4.addActorAt(0, this.q);
        table.add((Table) this.j).expand().fill().row();
        table.add(table4).expandX().fillX().height(64.0f).padTop(8.0f).row();
        this.p = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), this::e);
        this.p.setSize(64.0f, 64.0f);
        this.p.setPosition(-192.0f, 0.0f);
        this.p.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-bottom-hollow"), 8.0f, 8.0f, 48.0f, 48.0f);
        this.p.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 64.0f, 64.0f);
        this.p.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.21f), new Color(0.0f, 0.0f, 0.0f, 0.0f));
        this.p.setIconLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P600, new Color(1.0f, 1.0f, 1.0f, 0.28f));
        table4.add((Table) this.p).size(64.0f);
        this.o = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), this::d);
        this.o.setSize(64.0f, 64.0f);
        this.o.setPosition(-128.0f, 0.0f);
        this.o.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-top-hollow"), 8.0f, 8.0f, 48.0f, 48.0f);
        this.o.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 64.0f, 64.0f);
        this.o.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.21f), new Color(0.0f, 0.0f, 0.0f, 0.0f));
        this.o.setIconLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P600, new Color(1.0f, 1.0f, 1.0f, 0.28f));
        table4.add((Table) this.o).size(64.0f);
        ComplexButton complexButton4 = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            a("log(utils.print(" + this.i.getText().trim() + "))", false);
        });
        complexButton4.setSize(64.0f, 64.0f);
        complexButton4.setPosition(-256.0f, 0.0f);
        complexButton4.setIconPositioned(Game.i.assetManager.getDrawable("icon-eye"), 8.0f, 8.0f, 48.0f, 48.0f);
        complexButton4.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 64.0f, 64.0f);
        complexButton4.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.21f), new Color(0.0f, 0.0f, 0.0f, 0.0f));
        complexButton4.setIconLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P600, new Color(1.0f, 1.0f, 1.0f, 0.28f));
        table4.add((Table) complexButton4).size(64.0f);
        ComplexButton complexButton5 = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.DEVELOPER_DOCUMENTATION_URL);
        });
        complexButton5.setIconPositioned(Game.i.assetManager.getDrawable("icon-book-closed"), 8.0f, 8.0f, 48.0f, 48.0f);
        complexButton5.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 64.0f, 64.0f);
        complexButton5.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.21f), new Color(0.0f, 0.0f, 0.0f, 0.0f));
        complexButton5.setIconLabelColors(MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIGHT_GREEN.P300, MaterialColor.LIGHT_GREEN.P600, new Color(1.0f, 1.0f, 1.0f, 0.28f));
        table4.add((Table) complexButton5).size(64.0f);
        RectButton rectButton = new RectButton("Submit", Game.i.assetManager.getLabelStyle(30), this::g);
        rectButton.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P900, MaterialColor.LIGHT_GREEN.P700, Color.GRAY);
        table4.add((Table) rectButton).size(192.0f, 64.0f);
        this.i.clearListeners();
        this.i.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.shared.DeveloperConsole.2
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent, int i) {
                if (i == 19) {
                    DeveloperConsole.this.d();
                    inputEvent.cancel();
                    inputEvent.halt();
                    return true;
                }
                if (i == 20) {
                    DeveloperConsole.this.e();
                    inputEvent.cancel();
                    inputEvent.halt();
                    return true;
                }
                if (DeveloperConsole.this.i.getSelection().length() == 0 && DeveloperConsole.this.i.getCursorPosition() == DeveloperConsole.this.i.getText().length() && (i == 61 || i == 22)) {
                    if (DeveloperConsole.this.a()) {
                        inputEvent.cancel();
                        inputEvent.halt();
                        return true;
                    }
                } else if (i == 66 || i == 160) {
                    DeveloperConsole.this.g();
                    inputEvent.cancel();
                    inputEvent.halt();
                    return true;
                }
                return super.keyDown(inputEvent, i);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean keyTyped(InputEvent inputEvent, char c) {
                if (c == '\t' || c == '\r' || c == '\n') {
                    inputEvent.halt();
                    inputEvent.cancel();
                    return true;
                }
                DeveloperConsole.a(DeveloperConsole.this, 0);
                DeveloperConsole.this.w.set(0, DeveloperConsole.this.i.getText());
                return false;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean keyUp(InputEvent inputEvent, int i) {
                return false;
            }
        });
        this.i.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.DeveloperConsole.3
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                DeveloperConsole.this.c();
            }
        });
        this.i.addListener(this.i.getDefaultInputListener());
        Game.i.cursorGraphics.setActorCustomMouseCursor(this.i, Cursor.SystemCursor.Ibeam);
        Game.EVENTS.getListeners(ScreenResize.class).add(this.u);
        ComplexButton complexButton6 = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            setVisible(!this.f);
        });
        complexButton6.setBackground(Game.i.assetManager.getQuad("ui.console.buttonBg"), 0.0f, 0.0f, 80.0f, 52.0f);
        complexButton6.setIconPositioned(Game.i.assetManager.getDrawable("icon-terminal"), 24.0f, 10.0f, 32.0f, 32.0f);
        complexButton6.setBackgroundColors(MaterialColor.BLUE_GREY.P700, MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P600, Color.BLACK);
        this.c.getTable().add((Table) complexButton6).expand().top().left().size(80.0f, 52.0f).padTop(175.0f);
        try {
            FileHandle local = Gdx.files.local("cache/dev-console-history.ubjson");
            if (local.exists()) {
                Iterator<JsonValue> iterator2 = new UBJsonReader().parse(local).iterator2();
                while (iterator2.hasNext()) {
                    this.w.add(iterator2.next().asString());
                }
            }
        } catch (Exception unused) {
        }
        this.f = false;
        this.e.getTable().setVisible(false);
        this.d.getTable().setVisible(false);
        h();
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        String b2 = b();
        if (b2 != null) {
            this.i.setText(b2);
            this.i.setCursorPosition(b2.length());
            c();
            return true;
        }
        return false;
    }

    @Null
    private String b() {
        if (this.s == null || this.s.variants.size == 0) {
            return null;
        }
        if (this.s.variants.size == 1) {
            return this.i.getText().substring(0, this.s.start) + this.s.variants.first().first;
        }
        int i = 100;
        for (int i2 = 0; i2 < this.s.variants.size; i2++) {
            String str = this.s.variants.get(i2).first;
            if (str.length() < i) {
                i = str.length();
            }
        }
        String str2 = null;
        loop1: for (int i3 = 1; i3 < i; i3++) {
            String substring = this.s.variants.get(0).first.substring(0, i3);
            for (int i4 = 1; i4 < this.s.variants.size; i4++) {
                if (!this.s.variants.get(i4).first.substring(0, i3).equals(substring)) {
                    break loop1;
                }
            }
            str2 = substring;
        }
        if (str2 != null) {
            return this.i.getText().substring(0, this.s.start) + str2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ScriptEnvironment scriptEnvironment;
        if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
            scriptEnvironment = ((GameScreen) Game.i.screenManager.getCurrentScreen()).S.script.scriptEnvironment;
        } else {
            scriptEnvironment = Game.i.scriptManager.global;
        }
        String text = this.i.getText();
        if (text.length() != 0) {
            ScriptEnvironment.Suggestion autocompletion = scriptEnvironment.getAutocompletion(text, this.i.getCursorPosition() - 1);
            this.s = autocompletion;
            if (autocompletion == null || autocompletion.variants.size == 0) {
                this.r.setVisible(false);
                this.q.setVisible(false);
                this.s = null;
                return;
            }
            this.q.setVisible(true);
            this.q.clear();
            Table table = new Table();
            ScrollPane scrollPane = new ScrollPane(table, Game.i.assetManager.getScrollPaneStyle(0.0f));
            scrollPane.setOverscroll(false, false);
            scrollPane.setScrollingDisabled(true, false);
            this.q.add((Table) scrollPane).pad(5.0f).grow();
            Array.ArrayIterator<ObjectPair<String, String>> it = autocompletion.variants.iterator();
            while (it.hasNext()) {
                ObjectPair<String, String> next = it.next();
                table.add((Table) new Label(next.first, Game.i.assetManager.getDebugLabelStyle())).growX();
                Label label = new Label(":" + next.second, Game.i.assetManager.getDebugLabelStyle());
                label.setColor(MaterialColor.LIGHT_BLUE.P500);
                table.add((Table) label).fillX().padLeft(8.0f).row();
            }
            table.row();
            table.add().width(1.0f).growY();
            table.layout();
            table.pack();
            table.layout();
            table.pack();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < autocompletion.start; i++) {
                stringBuilder.append("x");
            }
            this.q.setX((StringFormatter.calculateWidth(stringBuilder, Game.i.assetManager.getDebugFont(false)) + 14.0f) - 5.0f);
            this.q.setY(this.i.getHeight() + 5.0f);
            this.q.setWidth(Math.max(300.0f, table.getWidth() + 10.0f));
            this.q.setHeight(Math.max(10.0f, table.getHeight() + 10.0f));
            String b2 = b();
            if (b2 != null) {
                stringBuilder.setLength(0);
                for (int i2 = 0; i2 < b2.length(); i2++) {
                    if (i2 < this.i.getCursorPosition()) {
                        stringBuilder.append(' ');
                    } else {
                        stringBuilder.append(b2.charAt(i2));
                    }
                }
                this.r.setVisible(true);
                this.r.setText(stringBuilder.toString());
                return;
            }
            return;
        }
        this.r.setVisible(false);
        this.q.setVisible(false);
        this.s = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.w.size == 0) {
            return;
        }
        this.v++;
        if (this.v >= this.w.size) {
            this.v = this.w.size - 1;
        }
        this.i.setText(this.w.get(this.v));
        this.i.setCursorPosition(9999);
        c();
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.w.size == 0) {
            return;
        }
        this.v--;
        if (this.v < 0) {
            this.v = 0;
        }
        this.i.setText(this.w.get(this.v));
        this.i.setCursorPosition(9999);
        c();
        f();
    }

    private void f() {
        if (this.w.size != 0) {
            this.o.setEnabled(this.v != this.w.size - 1);
            this.p.setEnabled(this.v > 0);
        } else {
            this.o.setEnabled(false);
            this.p.setEnabled(false);
        }
    }

    private void a(String str, boolean z) {
        if (str.length() == 0) {
            return;
        }
        f3512a.i(">>> " + str, new Object[0]);
        if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
            ((GameScreen) Game.i.screenManager.getCurrentScreen()).S.script.runScriptAction(str);
        } else {
            ScriptEnvironment.LuaExecutionResult executeLua = Game.i.scriptManager.global.executeLua(str, "console");
            if (executeLua.caughtError == null && executeLua.returnValue != null && !executeLua.returnValue.isnil()) {
                f3512a.i("<<< " + executeLua.returnValue, new Object[0]);
            }
        }
        if (z) {
            if (this.w.size >= 2 && this.w.get(1).equals(str)) {
                this.w.set(0, "");
            } else {
                this.w.set(0, str);
                this.w.insert(0, "");
            }
            if (this.w.size > 20) {
                this.w.pop();
            }
            this.v = 0;
            n();
            f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a(this.i.getText().trim(), true);
        this.i.setText("");
        c();
    }

    private void h() {
        k();
    }

    private static int j() {
        int round = MathUtils.round((float) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_CONSOLE_LINE_COUNT));
        int i = round;
        if (round <= 1) {
            i = 100;
        }
        return i;
    }

    private void k() {
        this.j.fling(0.3f, 0.0f, -1000000.0f);
    }

    private LogLine a(@Null Date date, @Null String str, byte b2, String str2, final String str3) {
        LogLine logLine;
        if (this.k.size >= j()) {
            logLine = this.k.removeIndex(0);
            this.k.add(logLine);
            this.g.removeActor(logLine);
            this.g.invalidate();
        } else {
            logLine = new LogLine(this);
            this.k.add(logLine);
        }
        if (date != null) {
            x.setTime(date);
            int i = x.get(11);
            int i2 = x.get(12);
            int i3 = x.get(13);
            y.setLength(0);
            if (i < 10) {
                y.append('0');
            }
            y.append(i).append(':');
            if (i2 < 10) {
                y.append('0');
            }
            y.append(i2).append(':');
            if (i3 < 10) {
                y.append('0');
            }
            y.append(i3);
            logLine.n.setText(y);
            logLine.n.setVisible(true);
        } else {
            logLine.n.setVisible(false);
        }
        switch (b2) {
            case 0:
                logLine.p.setColor(MaterialColor.BLUE_GREY.P900);
                logLine.q.setText("D");
                logLine.l.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                break;
            case 1:
                logLine.p.setColor(MaterialColor.LIGHT_BLUE.P800);
                logLine.q.setText("I");
                logLine.l.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                break;
            case 2:
                logLine.p.setColor(MaterialColor.YELLOW.P900);
                logLine.q.setText("W");
                logLine.l.setColor(MaterialColor.YELLOW.P500);
                break;
            case 3:
                logLine.p.setColor(MaterialColor.RED.P800);
                logLine.q.setText("E");
                logLine.l.setColor(MaterialColor.RED.P400);
                break;
            default:
                if (str2.startsWith(">>> ")) {
                    str2 = str2.substring(4);
                    logLine.p.setColor(MaterialColor.GREEN.P800);
                    logLine.q.setText(">");
                    logLine.l.setColor(MaterialColor.GREEN.P300);
                    break;
                } else if (str2.startsWith("<<< ")) {
                    str2 = str2.substring(4);
                    logLine.p.setColor(MaterialColor.PURPLE.P800);
                    logLine.q.setText("<");
                    logLine.l.setColor(MaterialColor.PURPLE.P300);
                    break;
                }
                break;
        }
        if (str != null) {
            if (str.length() > 20) {
                str = str.substring(0, 20) + "...";
            }
            logLine.k.setColor(a(str));
            logLine.k.setText(str);
            logLine.k.setVisible(true);
            logLine.o.setVisible(true);
        } else {
            logLine.k.setVisible(false);
            logLine.o.setVisible(false);
        }
        if (!str2.contains("\\[#")) {
            str2 = str2.replaceAll("\\[]", "[[]");
        }
        if (str2.contains(SequenceUtils.EOL)) {
            logLine.l.setText(str2.split(SequenceUtils.EOL)[0]);
        } else {
            logLine.l.setText(str2);
        }
        logLine.n.clearListeners();
        if (str3 != null) {
            final LogLine logLine2 = logLine;
            logLine.n.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.DeveloperConsole.4
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                public void enter(InputEvent inputEvent, float f, float f2, int i4, @Null Actor actor) {
                    logLine2.n.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                    super.enter(inputEvent, f, f2, i4, actor);
                }

                @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i4, @Null Actor actor) {
                    logLine2.n.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    super.exit(inputEvent, f, f2, i4, actor);
                }

                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    String str4 = str3;
                    if (str3.startsWith(">>> ")) {
                        str4 = str4.substring(4);
                    } else if (str3.startsWith("<<< ")) {
                        str4 = str4.substring(4);
                    }
                    Gdx.app.getClipboard().setContents(str4);
                    Notifications.i().add("Copied to the clipboard!", null, null, null);
                }
            });
        }
        this.g.addActor(logLine);
        return logLine;
    }

    private void a(Logger.LogEntry logEntry) {
        boolean z = this.j.getScrollPercentY() > 0.99f;
        String[] split = logEntry.message.split(SequenceUtils.EOL);
        byte b2 = logEntry.logLevel;
        if (logEntry.message.startsWith(">>> ") || logEntry.message.startsWith("<<< ")) {
            b2 = -1;
        }
        boolean z2 = true;
        for (String str : split) {
            if (z2) {
                a(new Date(logEntry.timestampMs), logEntry.tag, b2, str, logEntry.message);
            } else {
                a(null, null, b2, str, logEntry.message);
            }
            z2 = false;
        }
        try {
            this.j.layout();
            if (z) {
                k();
            }
        } catch (Exception unused) {
            f3512a.e("failed to layout log scroll pane", new Object[0]);
            this.g.clearChildren();
            this.k.clear();
        }
    }

    public void toggleVisible() {
        setVisible(!this.f);
    }

    public void setCustomSettingsVisible(boolean z) {
        this.h.setVisible(z);
        if (z) {
            for (SettingsManager.CustomValueType customValueType : SettingsManager.CustomValueType.values) {
                CustomSettingsUI customSettingsUI = this.t[customValueType.ordinal()];
                double customValue = Game.i.settingsManager.getCustomValue(customValueType);
                customSettingsUI.f3520a.setText(new StringBuilder().append(customValue).toString());
                customSettingsUI.f3521b.setEnabled(customValue != 0.0d);
            }
        }
    }

    private void l() {
        Logger.forceLogFileFlushAndRun(() -> {
            File file = Game.i.actionResolver.getLogFile().file();
            Array<String> tail = FileUtils.tail(file, j());
            tail.reverse();
            long j = 0;
            String str = null;
            byte b2 = 1;
            Array array = new Array();
            StringBuilder stringBuilder = new StringBuilder();
            Matcher matcher = LogWriter.LOG_FILE_ENTRY_REGEX.matcher("");
            Array.ArrayIterator<String> it = tail.iterator();
            while (it.hasNext()) {
                String next = it.next();
                try {
                    matcher.reset(next);
                    if (matcher.find()) {
                        if (str != null) {
                            array.add(new Logger.LogEntry(b2, j, str, stringBuilder.toString()));
                            stringBuilder.setLength(0);
                        }
                        j = LogWriter.LOG_DATE_FORMAT.parse(matcher.group(1)).getTime();
                        b2 = LogLevel.shortNameToLevel(matcher.group(2).charAt(0));
                        str = matcher.group(3);
                        stringBuilder.append(next.substring(matcher.group().length()));
                    } else {
                        stringBuilder.append('\n').append(next);
                    }
                } catch (Exception e) {
                    System.out.println("Failed to parse log line: " + next);
                    e.printStackTrace(System.out);
                }
            }
            if (str != null) {
                array.add(new Logger.LogEntry(b2, j, str, stringBuilder.toString()));
                stringBuilder.setLength(0);
            }
            synchronized (this) {
                if (this.z != null) {
                    try {
                        this.z.close();
                    } catch (IOException unused) {
                    }
                }
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    bufferedReader.skip(Long.MAX_VALUE);
                    this.z = bufferedReader;
                } catch (Exception unused2) {
                }
            }
            Threads.i().runOnMainThread(() -> {
                if (this.f) {
                    Array.ArrayIterator it2 = array.iterator();
                    while (it2.hasNext()) {
                        a((Logger.LogEntry) it2.next());
                    }
                    k();
                }
            });
        });
        try {
            this.j.getStage().setScrollFocus(this.j);
            this.i.getStage().setKeyboardFocus(this.i);
        } catch (Exception unused) {
        }
        k();
        c();
    }

    private void m() {
        this.g.clear();
        this.k.clear();
        try {
            this.i.getStage().unfocusAll();
        } catch (Exception unused) {
        }
        try {
            this.z.close();
            this.z = null;
        } catch (Exception unused2) {
        }
        this.i.setText("");
        c();
        Game.i.uiManager.stage.setKeyboardFocus(null);
        setCustomSettingsVisible(false);
    }

    public boolean isCustomSettingsVisible() {
        return this.h.isVisible();
    }

    public void setVisible(boolean z) {
        if (this.f != z) {
            this.f = z;
            this.d.getTable().setVisible(z);
            this.e.getTable().setVisible(z);
            if (z) {
                l();
            } else {
                m();
            }
        }
    }

    private static Color a(String str) {
        long j = 0;
        for (int i = 0; i < str.length(); i++) {
            j += str.charAt(i);
        }
        return f3513b[(int) (j % f3513b.length)];
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.IOException] */
    private void n() {
        ?? r0 = this.w.size;
        if (r0 > 1) {
            try {
                OutputStream write = Gdx.files.local("cache/dev-console-history.ubjson").write(false, 512);
                UBJsonWriter uBJsonWriter = new UBJsonWriter(write);
                uBJsonWriter.array();
                for (int i = 1; i < this.w.size; i++) {
                    uBJsonWriter.value(this.w.get(i));
                }
                uBJsonWriter.pop();
                r0 = write;
                r0.close();
            } catch (IOException e) {
                r0.printStackTrace();
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        f3512a.i("disposing", new Object[0]);
        Game.EVENTS.getListeners(ScreenResize.class).remove(this.u);
        Game.i.uiManager.removeLayer(this.e);
        Game.i.uiManager.removeLayer(this.d);
        Game.i.uiManager.removeLayer(this.c);
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DeveloperConsole$LogLine.class */
    public class LogLine extends Table {
        Label k;
        Label l;
        Label n;
        Stack o;
        Image p;
        Label q;

        LogLine(DeveloperConsole developerConsole) {
            this.n = new Label("", developerConsole.l);
            this.n.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            this.n.setTouchable(Touchable.enabled);
            this.n.setAlignment(10);
            add((LogLine) this.n).top().left().padLeft(0.0f).width(120.0f).padTop(2.0f).padBottom(2.0f);
            this.k = new Label("", developerConsole.m);
            this.k.setTouchable(Touchable.disabled);
            this.k.setAlignment(10);
            add((LogLine) this.k).top().left().width(220.0f).padTop(2.0f).padBottom(2.0f);
            this.p = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            this.p.setSize(24.0f, 24.0f);
            this.q = new Label("", Game.i.assetManager.getDebugLabelStyle());
            this.q.setSize(24.0f, 24.0f);
            this.q.setAlignment(1);
            this.o = new Stack();
            this.o.addActor(this.p);
            this.o.addActor(this.q);
            add((LogLine) this.o).top().left().size(24.0f).padTop(2.0f).padBottom(2.0f).padRight(8.0f);
            this.l = new Label("", developerConsole.n);
            this.l.setTouchable(Touchable.disabled);
            this.l.setAlignment(10);
            this.l.setWrap(true);
            add((LogLine) this.l).expandX().fill().padTop(2.0f).padBottom(2.0f);
        }
    }
}
