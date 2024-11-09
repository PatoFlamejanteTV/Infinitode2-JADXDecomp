package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/Subtitles.class */
public class Subtitles implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3417a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 104, "Subtitles main");
    private DelayedRemovalArray<ScheduledMessage> d = new DelayedRemovalArray<>(ScheduledMessage.class);

    /* renamed from: b, reason: collision with root package name */
    private Drawable f3418b = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.56f));
    private Table c = new Table();

    public Subtitles() {
        this.f3417a.getTable().setTouchable(Touchable.disabled);
        this.f3417a.getTable().add(this.c).fillX().expandX().expandY().bottom().right().padLeft(288.0f).padBottom(144.0f);
    }

    public void finalFadeOut() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        this.f3417a.getTable().setTouchable(Touchable.disabled);
        this.f3417a.getTable().clearActions();
        this.f3417a.getTable().addAction(Actions.alpha(0.0f, f * 1.0f));
    }

    public void schedule(CharSequence[] charSequenceArr, float f, float f2) {
        for (int i = 0; i < charSequenceArr.length; i++) {
            ScheduledMessage scheduledMessage = new ScheduledMessage(this, (byte) 0);
            scheduledMessage.f3419a = charSequenceArr[i];
            scheduledMessage.f3420b = f + (f2 * i);
            this.d.add(scheduledMessage);
        }
    }

    public void add(CharSequence charSequence) {
        Table table = new Table();
        table.setBackground(this.f3418b);
        this.c.add(table).fillX().expandX().padTop(4.0f).row();
        Label label = new Label(charSequence, Game.i.assetManager.getLabelStyle(30));
        label.setAlignment(8);
        label.setWrap(true);
        table.add((Table) label).pad(4.0f).padRight(120.0f).expand().fill();
        table.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(0.3f), Actions.delay(7.0f), Actions.fadeOut(2.0f), Actions.removeActor()));
    }

    public void draw(float f) {
        this.d.begin();
        for (int i = 0; i < this.d.size; i++) {
            ScheduledMessage scheduledMessage = this.d.items[i];
            scheduledMessage.f3420b -= f;
            if (scheduledMessage.f3420b <= 0.0f) {
                add(scheduledMessage.f3419a);
                this.d.removeIndex(i);
            }
        }
        this.d.end();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3417a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/Subtitles$ScheduledMessage.class */
    public class ScheduledMessage {

        /* renamed from: a, reason: collision with root package name */
        CharSequence f3419a;

        /* renamed from: b, reason: collision with root package name */
        float f3420b;

        private ScheduledMessage(Subtitles subtitles) {
        }

        /* synthetic */ ScheduledMessage(Subtitles subtitles, byte b2) {
            this(subtitles);
        }
    }
}
