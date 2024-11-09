package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/SellButton.class */
public class SellButton extends Group {
    private Color k = MaterialColor.RED.P800.cpy();
    private Color l = MaterialColor.RED.P900.cpy();
    private Color m = MaterialColor.RED.P700.cpy();
    private final Image n;
    private final Label o;
    private int p;
    private boolean q;
    private boolean r;

    public SellButton(final Runnable runnable) {
        setSize(192.0f, 80.0f);
        setTouchable(Touchable.enabled);
        setTransform(false);
        this.n = new Image(Game.i.assetManager.getDrawable("ui-sell-button"));
        this.n.setSize(192.0f, 80.0f);
        addActor(this.n);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-dollar"));
        image.setSize(40.0f, 40.0f);
        image.setPosition(35.0f, 20.0f);
        addActor(image);
        this.o = new Label("0", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.o.setSize(80.0f, 80.0f);
        this.o.setPosition(92.0f, 0.0f);
        this.o.setAlignment(16);
        addActor(this.o);
        if (HotKeyHintLabel.isEnabled()) {
            addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.SELL_BUILDING), 96.0f, -27.0f));
        }
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.SellButton.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                SellButton.this.r = true;
                SellButton.this.d();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                super.touchUp(inputEvent, f, f2, i, i2);
                SellButton.this.r = false;
                SellButton.this.d();
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.enter(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    SellButton.this.q = true;
                    SellButton.this.d();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.exit(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    SellButton.this.q = false;
                    SellButton.this.d();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (runnable != null) {
                    Dialog.i().showConfirmWithId(Game.i.localeManager.i18n.format("sell_for_coins_price_confirm", StringFormatter.commaSeparatedNumber(SellButton.this.p)), runnable, "sellButton");
                }
            }
        });
        d();
    }

    public void setColors(Color color, Color color2, Color color3) {
        this.k.set(color);
        this.l.set(color2);
        this.m.set(color3);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.r) {
            this.n.setColor(this.l);
        } else if (this.q) {
            this.n.setColor(this.m);
        } else {
            this.n.setColor(this.k);
        }
    }

    public void setPrice(int i) {
        this.p = i;
        this.o.setText(StringFormatter.commaSeparatedNumber(i));
    }
}
