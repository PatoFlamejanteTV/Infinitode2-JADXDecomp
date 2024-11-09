package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.actions.AlphaAction;
import com.prineside.tdi2.scene2d.actions.DelayAction;
import com.prineside.tdi2.scene2d.actions.MoveToAction;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/QuestList.class */
public class QuestList implements Disposable {
    public static final float LIST_ITEM_HEIGHT = 44.0f;
    public static final float LIST_ITEM_HEIGHT_COMPACT = 40.0f;

    /* renamed from: b, reason: collision with root package name */
    private Image f3380b;
    private Image c;
    private Group d;
    private Label e;
    private boolean f;
    private static final StringBuilder h = new StringBuilder();

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3379a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "QuestList");
    private Array<QuestListItem> g = new Array<>();

    public QuestList() {
        Group group = new Group();
        group.setTransform(false);
        this.f3379a.getTable().add((Table) group).expand().top().left().padTop(175.0f).size(563.0f, 280.0f);
        this.f3380b = new Image(Game.i.assetManager.getDrawable("ui-quest-list-background"));
        this.f3380b.setSize(683.0f, 258.0f);
        this.f3380b.setPosition(-120.0f, 22.0f);
        this.f3380b.setTouchable(Touchable.disabled);
        group.addActor(this.f3380b);
        this.d = new Group();
        this.d.setTransform(false);
        this.d.setTouchable(Touchable.disabled);
        this.d.setSize(563.0f, 280.0f);
        this.d.setOrigin(0.0f, 140.0f);
        group.addActor(this.d);
        Image image = new Image(Game.i.assetManager.getDrawable("ui-quest-list-title-background"));
        image.setSize(488.0f, 64.0f);
        image.setPosition(-120.0f, 216.0f);
        image.setTouchable(Touchable.enabled);
        image.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.QuestList.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                QuestList.this.setVisible(!QuestList.this.f);
            }
        });
        group.addActor(image);
        this.c = new Image(Game.i.assetManager.getDrawable("icon-triangle-bottom"));
        this.c.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.c.setSize(32.0f, 32.0f);
        this.c.setPosition(48.0f, 232.0f);
        this.c.setTouchable(Touchable.disabled);
        group.addActor(this.c);
        Label label = new Label(Game.i.localeManager.i18n.get("quests").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label.setSize(368.0f, 64.0f);
        label.setAlignment(8);
        label.setPosition(106.0f, 216.0f);
        label.setTouchable(Touchable.disabled);
        group.addActor(label);
        this.e = new Label("1/3", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.e.setAlignment(16);
        this.e.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.e.setSize(320.0f, 64.0f);
        this.e.setPosition(0.0f, 216.0f);
        this.e.setTouchable(Touchable.disabled);
        group.addActor(this.e);
        if (HotKeyHintLabel.isEnabled()) {
            group.addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.TOGGLE_QUEST_LIST), 32.0f, 240.0f));
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.UI_QUEST_LIST_VISIBLE) == 1.0d) {
            this.f = false;
            setVisible(true);
        } else {
            this.f = true;
            setVisible(false);
        }
        Game.i.uiManager.runOnStageAct(() -> {
            try {
                b();
            } catch (Exception unused) {
            }
        });
    }

    public void finalFadeOut() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        this.f3379a.getTable().setTouchable(Touchable.disabled);
        this.f3379a.getTable().clearActions();
        this.f3379a.getTable().addAction(Actions.alpha(0.0f, f * 1.0f));
    }

    private void b() {
        SnapshotArray<Actor> children = this.d.getChildren();
        int i = children.size;
        for (int i2 = 0; i2 < i; i2++) {
            Actor actor = children.get(i2);
            for (int i3 = actor.getActions().size - 1; i3 >= 0; i3--) {
                Action action = actor.getActions().get(i3);
                if ((action instanceof AlphaAction) || (action instanceof MoveToAction) || (action instanceof DelayAction)) {
                    actor.removeAction(action);
                }
            }
        }
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        if (this.f) {
            float f2 = 162.0f;
            int i4 = children.size;
            for (int i5 = 0; i5 < i4; i5++) {
                QuestListItem questListItem = (QuestListItem) children.get(i5);
                if (!questListItem.n) {
                    questListItem.setVisible(true);
                    questListItem.clearActions();
                    questListItem.addAction(Actions.parallel(Actions.alpha(1.0f, 0.2f * f), Actions.moveTo(0.0f, f2, 0.2f * f)));
                    if (children.size <= 4) {
                        f2 -= 44.0f;
                    } else {
                        f2 -= 40.0f;
                    }
                }
            }
            this.f3380b.clearActions();
            this.f3380b.addAction(Actions.moveTo(-120.0f, 22.0f, 0.2f * f));
            this.d.clearActions();
            if (children.size <= 4) {
                this.d.clearActions();
                this.d.addAction(Actions.sequence(Actions.parallel(Actions.scaleTo(1.0f, 1.0f, 0.3f * f), Actions.moveTo(0.0f, 0.0f, 0.3f * f)), Actions.run(() -> {
                    this.d.setTransform(false);
                })));
            } else {
                this.d.setTransform(true);
                this.d.clearActions();
                this.d.addAction(Actions.parallel(Actions.scaleTo(0.87f, 0.87f, 0.3f * f), Actions.moveTo(5.0f, 10.0f, 0.3f * f)));
            }
            this.c.setDrawable(Game.i.assetManager.getDrawable("icon-triangle-bottom"));
        } else {
            int i6 = children.size;
            for (int i7 = 0; i7 < i6; i7++) {
                QuestListItem questListItem2 = (QuestListItem) children.get(i7);
                if (!questListItem2.n) {
                    questListItem2.setVisible(true);
                    questListItem2.clearActions();
                    questListItem2.addAction(Actions.parallel(Actions.alpha(0.0f, 0.2f * f), Actions.moveTo(0.0f, 232.0f, 0.2f * f), Actions.delay(0.2f * f, Actions.hide())));
                }
            }
            this.f3380b.clearActions();
            this.f3380b.addAction(Actions.moveTo(-683.0f, 22.0f, 0.2f * f));
            this.c.setDrawable(Game.i.assetManager.getDrawable("icon-triangle-right"));
        }
        d();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        if (this.g.size != 0) {
            this.f3379a.getTable().clearActions();
            this.f3379a.getTable().addAction(Actions.sequence(Actions.show(), Actions.alpha(1.0f, 0.3f * f)));
        } else {
            this.f3379a.getTable().clearActions();
            this.f3379a.getTable().addAction(Actions.sequence(Actions.alpha(0.0f, 0.3f * f), Actions.hide()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.g.size != 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.g.size; i2++) {
                if (this.g.get(i2).isCompleted()) {
                    i++;
                }
            }
            this.e.setText(i + "/" + this.g.size);
            return;
        }
        this.e.setText("");
    }

    public QuestListItem addQuestListItem() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        QuestListItem questListItem = new QuestListItem(this, (byte) 0);
        this.d.addActorAt(0, questListItem);
        questListItem.setPosition(0.0f, 162.0f);
        questListItem.setOrigin(8);
        questListItem.setTransform(true);
        questListItem.clearActions();
        questListItem.addAction(Actions.sequence(Actions.scaleTo(0.5f, 0.5f), Actions.scaleTo(1.25f, 1.25f, 0.2f * f), Actions.scaleTo(1.0f, 1.0f, 0.2f * f), Actions.run(() -> {
            questListItem.setTransform(false);
        })));
        this.g.add(questListItem);
        b();
        return questListItem;
    }

    public void removeQuestListItem(QuestListItem questListItem) {
        if (questListItem.n) {
            return;
        }
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        QuestListItem.a(questListItem, true);
        questListItem.clearActions();
        questListItem.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(-300.0f, 0.0f, 0.3f * f, Interpolation.circleIn), Actions.alpha(0.0f, 0.25f * f)), Actions.run(() -> {
            this.d.removeActor(questListItem);
            b();
        })));
        this.g.removeValue(questListItem, true);
    }

    public boolean isVisible() {
        return this.f;
    }

    public void setVisible(boolean z) {
        this.f = z;
        Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.UI_QUEST_LIST_VISIBLE, z ? 1.0d : 0.0d);
        b();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3379a);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/QuestList$QuestListItem.class */
    public class QuestListItem extends Group {
        private Label k;
        private Image l;
        private boolean m;
        private boolean n;
        private StringBuilder o;
        private StringBuilder p;

        /* synthetic */ QuestListItem(QuestList questList, byte b2) {
            this();
        }

        static /* synthetic */ boolean a(QuestListItem questListItem, boolean z) {
            questListItem.n = true;
            return true;
        }

        private QuestListItem() {
            this.o = new StringBuilder();
            this.p = new StringBuilder();
            setHeight(44.0f);
            this.l = new Image(Game.i.assetManager.getDrawable("checkbox"));
            this.l.setPosition(40.0f, 2.0f);
            this.l.setSize(44.0f, 44.0f);
            addActor(this.l);
            this.k = new Label("", Game.i.assetManager.getLabelStyle(30));
            this.k.setSize(100.0f, 44.0f);
            this.k.setPosition(106.0f, 0.0f);
            addActor(this.k);
        }

        public void setTitlePrefix(CharSequence charSequence) {
            this.o.setLength(0);
            this.o.append(charSequence);
            setText(this.p);
        }

        public void setText(CharSequence charSequence) {
            this.p.setLength(0);
            this.p.append(charSequence);
            QuestList.h.setLength(0);
            QuestList.h.append(this.o).append(charSequence);
            this.k.setText(QuestList.h);
        }

        public void setCompleted(boolean z) {
            float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
            if (z) {
                this.l.setDrawable(Game.i.assetManager.getDrawable("checkbox-checked"));
                if (!this.n) {
                    setTransform(true);
                    clearActions();
                    addAction(Actions.sequence(Actions.scaleTo(1.25f, 1.25f, 0.2f * f), Actions.scaleTo(1.0f, 1.0f, 0.2f * f), Actions.run(() -> {
                        setTransform(false);
                    })));
                }
            } else {
                this.l.setDrawable(Game.i.assetManager.getDrawable("checkbox"));
            }
            this.m = z;
            QuestList.this.d();
            QuestList.this.c();
        }

        public boolean isCompleted() {
            return this.m;
        }
    }
}
