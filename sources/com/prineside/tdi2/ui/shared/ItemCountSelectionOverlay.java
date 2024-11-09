package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ItemCountSelectionOverlay.class */
public final class ItemCountSelectionOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3560a = TLog.forClass(ItemCountSelectionOverlay.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3561b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 292, "ItemCountSelectionOverlay main");
    private Group c;
    private Table d;
    private ItemCell e;
    private Label f;
    private ComplexButton g;
    private Image h;
    private ComplexButton i;
    private ComplexButton j;
    private ComplexButton k;
    private ComplexButton l;
    private Group m;
    private int n;
    private int o;
    private int p;
    private ItemCountSelectionListener q;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ItemCountSelectionOverlay$ItemCountSelectionListener.class */
    public interface ItemCountSelectionListener {
        void countChanged(int i);

        void selectionConfirmed(int i);

        void selectionCanceled();
    }

    public static ItemCountSelectionOverlay i() {
        return (ItemCountSelectionOverlay) Game.i.uiManager.getComponent(ItemCountSelectionOverlay.class);
    }

    public ItemCountSelectionOverlay() {
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(400.0f, 142.0f);
        this.f3561b.getTable().add().expand().fill().row();
        this.f3561b.getTable().add((Table) group).size(800.0f, 284.0f).padBottom(128.0f);
        this.c = new Group();
        this.c.setTransform(false);
        this.c.setSize(800.0f, 284.0f);
        this.c.setOrigin(400.0f, 142.0f);
        group.addActor(this.c);
        QuadActor quadActor = new QuadActor(new Color(72), new float[]{9.0f, 0.0f, 0.0f, 284.0f, 800.0f, 275.0f, 793.0f, 7.0f});
        quadActor.setPosition(10.0f, -10.0f);
        this.c.addActor(quadActor);
        this.c.addActor(new QuadActor(new Color(791621631), new float[]{9.0f, 0.0f, 0.0f, 284.0f, 800.0f, 275.0f, 793.0f, 7.0f}));
        this.e = new ItemCell();
        this.e.setPosition(31.0f, 114.0f);
        this.c.addActor(this.e);
        this.f = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.f.setPosition(190.0f, 212.0f);
        this.f.setSize(100.0f, 25.0f);
        this.f.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.c.addActor(this.f);
        this.m = new Group();
        this.m.setTransform(false);
        this.m.setPosition(160.0f, 141.0f);
        this.m.setSize(620.0f, 72.0f);
        this.m.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.1
            private void a(float f) {
                float f2 = (f - 30.0f) / 560.0f;
                if (f2 <= 0.0f) {
                    ItemCountSelectionOverlay.this.setSelectedCount(ItemCountSelectionOverlay.this.n);
                    return;
                }
                if (f2 >= 1.0f) {
                    ItemCountSelectionOverlay.this.setSelectedCount(ItemCountSelectionOverlay.this.o);
                    return;
                }
                int round = MathUtils.round((f2 * (ItemCountSelectionOverlay.this.o - ItemCountSelectionOverlay.this.n)) + ItemCountSelectionOverlay.this.n);
                int i = round;
                if (round > 10000) {
                    i = MathUtils.round(i / 50.0f) * 50;
                } else if (i > 1000) {
                    i = MathUtils.round(i / 10.0f) * 10;
                }
                ItemCountSelectionOverlay.this.setSelectedCount(i);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                a(f);
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                a(f);
            }
        });
        this.c.addActor(this.m);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(560.0f, 12.0f);
        image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image.setPosition(30.0f, 30.0f);
        this.m.addActor(image);
        this.h = new Image(Game.i.assetManager.getDrawable("ui-item-count-selector-scroll-button"));
        this.h.setSize(41.0f, 25.0f);
        this.m.addActor(this.h);
        this.g = new ComplexButton("", Game.i.assetManager.getLabelStyle(36), () -> {
            Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.2
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    try {
                        ItemCountSelectionOverlay.this.setSelectedCount(Integer.valueOf(str).intValue());
                    } catch (Exception e) {
                        ItemCountSelectionOverlay.f3560a.e("invalid value", e);
                    }
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, this.n + " <=> " + this.o, new StringBuilder().append(this.p).toString(), "");
        });
        this.g.setLabel(0.0f, 0.0f, 156.0f, 40.0f, 16);
        this.g.setSize(156.0f, 40.0f);
        this.g.setPosition(594.0f, 208.0f);
        this.g.setLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P400, MaterialColor.LIGHT_BLUE.P600, Color.GRAY);
        this.c.addActor(this.g);
        ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(36), () -> {
            setSelectedCount(this.p - 1);
        });
        complexButton.setBackground(Game.i.assetManager.getDrawable("ui-item-count-selector-minus-button"), 0.0f, 0.0f, 48.0f, 53.0f);
        complexButton.setSize(48.0f, 53.0f);
        complexButton.setBackgroundColors(MaterialColor.BLUE_GREY.P700, MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P600, MaterialColor.GREY.P700);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-minus"), 9.0f, 13.0f, 32.0f, 32.0f);
        complexButton.setPosition(766.0f, 149.0f);
        this.c.addActor(complexButton);
        ComplexButton complexButton2 = new ComplexButton("", Game.i.assetManager.getLabelStyle(36), () -> {
            setSelectedCount(this.p + 1);
        });
        complexButton2.setBackground(Game.i.assetManager.getDrawable("ui-item-count-selector-plus-button"), 0.0f, 0.0f, 51.0f, 57.0f);
        complexButton2.setSize(51.0f, 57.0f);
        complexButton2.setBackgroundColors(MaterialColor.BLUE_GREY.P700, MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P600, MaterialColor.GREY.P700);
        complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-plus"), 10.0f, 17.0f, 32.0f, 32.0f);
        complexButton2.setPosition(768.0f, 202.0f);
        this.c.addActor(complexButton2);
        this.i = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            setSelectedCount(this.n);
        });
        this.i.setPosition(160.0f, 121.0f);
        this.i.setLabel(32.0f, 16.0f, 50.0f, 18.0f, 8);
        this.i.setSize(60.0f, 40.0f);
        this.i.setLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P400, MaterialColor.LIGHT_BLUE.P600, MaterialColor.GREY.P700);
        this.c.addActor(this.i);
        this.j = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            setSelectedCount(this.o);
        });
        this.j.setPosition(654.0f, 121.0f);
        this.j.setLabel(0.0f, 16.0f, 96.0f, 18.0f, 16);
        this.j.setSize(96.0f, 40.0f);
        this.j.setLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P400, MaterialColor.LIGHT_BLUE.P600, MaterialColor.GREY.P700);
        this.c.addActor(this.j);
        this.k = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            setSelectedCount(((this.o - this.n) / 2) + this.n);
        });
        this.k.setPosition(423.0f, 121.0f);
        this.k.setLabel(0.0f, 16.0f, 96.0f, 18.0f, 1);
        this.k.setSize(96.0f, 40.0f);
        this.k.setLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P400, MaterialColor.LIGHT_BLUE.P600, MaterialColor.GREY.P700);
        this.c.addActor(this.k);
        this.d = new Table();
        this.d.setSize(470.0f, 80.0f);
        this.d.setPosition(35.0f, 25.0f);
        this.c.addActor(this.d);
        this.l = new ComplexButton("", Game.i.assetManager.getLabelStyle(36), () -> {
            if (this.q != null) {
                this.q.selectionConfirmed(this.p);
            }
            hide();
        });
        this.l.setBackground(Game.i.assetManager.getDrawable("ui-item-count-selector-cancel-button"), 0.0f, 0.0f, 146.0f, 99.0f);
        this.l.setSize(146.0f, 99.0f);
        this.l.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.GREY.P700);
        this.l.setIconPositioned(Game.i.assetManager.getDrawable("icon-check"), 40.0f, 18.0f, 64.0f, 64.0f);
        this.l.setPosition(523.0f, -11.0f);
        this.c.addActor(this.l);
        ComplexButton complexButton3 = new ComplexButton("", Game.i.assetManager.getLabelStyle(36), () -> {
            if (this.q != null) {
                this.q.selectionCanceled();
            }
            hide();
        });
        complexButton3.setBackground(Game.i.assetManager.getDrawable("ui-item-count-selector-confirm-button"), 0.0f, 0.0f, 146.0f, 99.0f);
        complexButton3.setSize(138.0f, 99.0f);
        complexButton3.setBackgroundColors(MaterialColor.BLUE_GREY.P700, MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P600, MaterialColor.GREY.P700);
        complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 40.0f, 16.0f, 64.0f, 64.0f);
        complexButton3.setPosition(669.0f, -7.0f);
        this.c.addActor(complexButton3);
        this.f3561b.getTable().setVisible(false);
    }

    public final void setConfirmButtonEnabled(boolean z) {
        this.l.setEnabled(z);
    }

    public final int getMinCount() {
        return this.n;
    }

    public final int getMaxCount() {
        return this.o;
    }

    public final void setMinMaxCount(int i, int i2) {
        this.n = i;
        this.o = i2;
        this.i.setTextFromInt(i);
        this.j.setTextFromInt(i2);
        if (i2 - i > 2) {
            this.k.setTextFromInt(((i2 - i) / 2) + i);
        }
        if (this.p < this.n) {
            this.p = this.n;
        }
        if (this.p > this.o) {
            this.p = this.o;
        }
        this.g.setText("x" + ((Object) StringFormatter.commaSeparatedNumber(this.p)));
        b();
    }

    public final void setSelectedCount(int i) {
        int i2 = this.p;
        if (i < this.n) {
            i = this.n;
        }
        if (i > this.o) {
            i = this.o;
        }
        this.p = i;
        this.g.setText("x" + ((Object) StringFormatter.commaSeparatedNumber(i)));
        b();
        if (this.q != null && i2 != i) {
            this.q.countChanged(this.p);
        }
    }

    public final int getSelectedCount() {
        return this.p;
    }

    private void b() {
        if (this.n == this.o) {
            this.m.setVisible(false);
            this.i.setVisible(false);
            this.j.setVisible(false);
            this.k.setVisible(false);
            return;
        }
        this.h.setPosition(30.0f + (((this.p - this.n) / (this.o - this.n)) * 519.0f), 24.0f);
        this.m.setVisible(true);
        if (this.o - this.n > 2) {
            this.k.setVisible(true);
        } else {
            this.k.setVisible(false);
        }
        this.i.setVisible(true);
        this.j.setVisible(true);
    }

    public final void show(CharSequence charSequence, int i, int i2, Item item, ItemCountSelectionListener itemCountSelectionListener) {
        setConfirmButtonEnabled(true);
        this.e.setItem(item, 0);
        this.f.setText(charSequence);
        this.p = i;
        setMinMaxCount(i, i2);
        DarkOverlay.i().addCallerOverlayLayer("ItemCountSelectionOverlay", this.f3561b.zIndex - 1, () -> {
            hide();
            return true;
        });
        UiUtils.bouncyShowOverlay(null, this.f3561b.getTable(), this.c);
        b();
        this.q = itemCountSelectionListener;
    }

    public final Table getInfoContainer() {
        return this.d;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        this.q = null;
        this.d.clear();
        UiUtils.bouncyHideOverlay(null, this.f3561b.getTable(), this.c);
        DarkOverlay.i().removeCaller("ItemCountSelectionOverlay");
    }
}
