package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.utils.BooleanSupplier;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DarkOverlay.class */
public final class DarkOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3509a = TLog.forClass(DarkOverlay.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3510b = new Color(218959279);
    private boolean d;
    private final DelayedRemovalArray<QueuedCaller> c = new DelayedRemovalArray<>(true, 1, QueuedCaller.class);
    private final ClickListener e = new ClickListener() { // from class: com.prineside.tdi2.ui.shared.DarkOverlay.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f, float f2) {
            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            if (DarkOverlay.this.c.size == 0) {
                DarkOverlay.f3509a.e("no callers", new Object[0]);
                return;
            }
            QueuedCaller queuedCaller = ((QueuedCaller[]) DarkOverlay.this.c.items)[DarkOverlay.this.c.size - 1];
            boolean z = true;
            if (queuedCaller.onClick != null) {
                z = queuedCaller.onClick.getAsBoolean();
            }
            if (z) {
                DarkOverlay.this.removeCaller(queuedCaller.name);
            }
        }
    };

    public static DarkOverlay i() {
        return (DarkOverlay) Game.i.uiManager.getComponent(DarkOverlay.class);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f) {
        if (this.d) {
            float f2 = Game.i.settingsManager.isUiAnimationsEnabled() ? 5.0f : 900100.0f;
            boolean z = false;
            for (int i = this.c.size - 1; i >= 0; i--) {
                QueuedCaller queuedCaller = this.c.items[i];
                if (queuedCaller.removing) {
                    queuedCaller.visible = false;
                } else if (z) {
                    queuedCaller.visible = false;
                } else {
                    queuedCaller.visible = true;
                    z = true;
                }
            }
            this.c.begin();
            for (int i2 = 0; i2 < this.c.size; i2++) {
                QueuedCaller queuedCaller2 = this.c.items[i2];
                if (!queuedCaller2.visible) {
                    if (queuedCaller2.alpha > 0.0f) {
                        queuedCaller2.alpha -= f * f2;
                        if (queuedCaller2.alpha <= 0.0f) {
                            queuedCaller2.alpha = 0.0f;
                            queuedCaller2.layer.getTable().setVisible(false);
                        }
                    }
                    if (queuedCaller2.alpha == 0.0f && queuedCaller2.removing) {
                        Game.i.uiManager.removeLayer(queuedCaller2.layer);
                        this.c.removeIndex(i2);
                    }
                } else {
                    if (queuedCaller2.alpha < 1.0f) {
                        queuedCaller2.alpha += f * f2;
                        if (queuedCaller2.alpha >= 1.0f) {
                            queuedCaller2.alpha = 1.0f;
                        }
                    }
                    queuedCaller2.layer.getTable().setVisible(true);
                }
                queuedCaller2.tint.getColor().f889a = ((queuedCaller2.alpha * 0.5f) + (Interpolation.pow2Out.apply(queuedCaller2.alpha) * 0.5f)) * f3510b.f889a;
            }
            this.c.end();
            if (this.c.size == 0) {
                this.d = false;
            }
        }
    }

    public final void removeCaller(String str) {
        for (int i = 0; i < this.c.size; i++) {
            if (this.c.items[i].name.equals(str)) {
                if (!this.c.items[i].removing) {
                    this.c.items[i].removing = true;
                    this.c.items[i].layer.getTable().setTouchable(Touchable.disabled);
                    f3509a.i("remove " + str, new Object[0]);
                    return;
                }
                return;
            }
        }
    }

    private void b() {
        this.c.sort((queuedCaller, queuedCaller2) -> {
            return Integer.compare((queuedCaller.layer.mainUiLayer.ordinal() * 100000) + queuedCaller.layer.zIndex, (queuedCaller2.layer.mainUiLayer.ordinal() * 100000) + queuedCaller2.layer.zIndex);
        });
    }

    public final void addCallerOverlayLayer(String str, int i, BooleanSupplier booleanSupplier) {
        addCaller(str, UiManager.MainUiLayer.OVERLAY, i, booleanSupplier);
    }

    public final void addCaller(String str, UiManager.MainUiLayer mainUiLayer, int i, BooleanSupplier booleanSupplier) {
        if (str == null) {
            throw new IllegalArgumentException("callerName is null");
        }
        for (int i2 = 0; i2 < this.c.size; i2++) {
            QueuedCaller queuedCaller = this.c.items[i2];
            if (queuedCaller.name.equals(str) && !queuedCaller.removing) {
                queuedCaller.onClick = booleanSupplier;
                return;
            }
        }
        QueuedCaller queuedCaller2 = new QueuedCaller((byte) 0);
        queuedCaller2.name = str;
        queuedCaller2.onClick = booleanSupplier;
        queuedCaller2.visible = true;
        queuedCaller2.alpha = 0.0f;
        queuedCaller2.layer = Game.i.uiManager.addLayerIgnoreSafeArea(mainUiLayer, i, str + " overlay", true);
        Image image = new Image(Game.i.assetManager.getBlankWhiteTextureRegion());
        image.setColor(f3510b.cpy().mul(1.0f, 1.0f, 1.0f, 0.0f));
        queuedCaller2.tint = image;
        queuedCaller2.layer.getTable().setTouchable(Touchable.enabled);
        queuedCaller2.layer.getTable().addListener(this.e);
        queuedCaller2.layer.getTable().add((Table) image).expand().fill();
        queuedCaller2.layer.getTable().setVisible(false);
        this.c.add(queuedCaller2);
        b();
        this.d = true;
        f3509a.i("add " + str, new Object[0]);
    }

    public final void printDebug() {
        for (int i = 0; i < this.c.size; i++) {
            QueuedCaller queuedCaller = this.c.items[i];
            f3509a.i("  " + queuedCaller.name + SequenceUtils.SPACE + queuedCaller.layer.mainUiLayer.name() + SequenceUtils.SPACE + queuedCaller.layer.zIndex + SequenceUtils.SPACE + queuedCaller.alpha + SequenceUtils.SPACE + queuedCaller.removing + SequenceUtils.SPACE + queuedCaller.visible, new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/DarkOverlay$QueuedCaller.class */
    public static class QueuedCaller {
        public String name;
        public BooleanSupplier onClick;
        public UiManager.UiLayer layer;
        public Image tint;
        public float alpha;
        public boolean visible;
        public boolean removing;

        private QueuedCaller() {
        }

        /* synthetic */ QueuedCaller(byte b2) {
            this();
        }
    }
}
