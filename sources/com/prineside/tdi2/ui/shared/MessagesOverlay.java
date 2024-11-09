package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.MessageManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.UiUtils;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/MessagesOverlay.class */
public class MessagesOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: b, reason: collision with root package name */
    private boolean f3707b;
    private Group d;
    private Label e;
    private PaddedImageButton f;
    private String g;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3706a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 150, "MessagesOverlay main");
    private Table c = new Table();

    public static MessagesOverlay i() {
        return (MessagesOverlay) Game.i.uiManager.getComponent(MessagesOverlay.class);
    }

    public MessagesOverlay() {
        this.c.setOrigin(520.0f, 400.0f);
        this.c.setTransform(true);
        this.f3706a.getTable().add(this.c).width(1040.0f);
        Group group = new Group();
        group.setTransform(false);
        QuadActor quadActor = new QuadActor(new Color(791621631), new float[]{0.0f, 0.0f, 0.0f, 15.0f, 1040.0f, 0.0f, 1040.0f, 0.0f});
        quadActor.setSize(1040.0f, 15.0f);
        group.addActor(quadActor);
        this.c.add((Table) group).height(15.0f).padTop(160.0f).width(1040.0f).row();
        this.d = new Group();
        this.d.setTransform(false);
        this.c.add((Table) this.d).size(1040.0f, 800.0f).row();
        Group group2 = new Group();
        group2.setTransform(false);
        QuadActor quadActor2 = new QuadActor(new Color(791621631), new float[]{0.0f, 0.0f, 0.0f, 30.0f, 1040.0f, 30.0f, 1040.0f, 15.0f});
        quadActor2.setSize(1040.0f, 30.0f);
        group2.addActor(quadActor2);
        this.e = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.e.addAction(Actions.forever(Actions.sequence(Actions.color(Color.WHITE, 0.4f), Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.56f), 0.8f), Actions.delay(0.5f))));
        this.e.setTouchable(Touchable.disabled);
        this.e.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.e.setSize(1040.0f, 20.0f);
        this.e.setPosition(0.0f, -60.0f);
        this.e.setAlignment(1);
        group2.addActor(this.e);
        this.c.add((Table) group2).height(30.0f).padBottom(160.0f).width(1040.0f).row();
        this.f3706a.getTable().setVisible(false);
        Game.i.messageManager.addListener(new MessageManager.MessageManagerListener.Adapter() { // from class: com.prineside.tdi2.ui.shared.MessagesOverlay.1
            @Override // com.prineside.tdi2.managers.MessageManager.MessageManagerListener.Adapter, com.prineside.tdi2.managers.MessageManager.MessageManagerListener
            public void messagesUpdated() {
                if (MessagesOverlay.this.isVisible() && MessagesOverlay.this.g == null) {
                    MessagesOverlay.this.updateContents();
                }
            }

            @Override // com.prineside.tdi2.managers.MessageManager.MessageManagerListener.Adapter, com.prineside.tdi2.managers.MessageManager.MessageManagerListener
            public void serverRequestStarted() {
                if (MessagesOverlay.this.isVisible()) {
                    MessagesOverlay.this.a();
                }
            }

            @Override // com.prineside.tdi2.managers.MessageManager.MessageManagerListener.Adapter, com.prineside.tdi2.managers.MessageManager.MessageManagerListener
            public void serverRequestFinished() {
                if (MessagesOverlay.this.isVisible()) {
                    MessagesOverlay.this.b();
                }
            }
        });
    }

    private static String a(int i) {
        Locale locale = Game.i.localeManager.i18n.getLocale();
        DateFormat dateInstance = DateFormat.getDateInstance(2, locale);
        DateFormat timeInstance = DateFormat.getTimeInstance(2, locale);
        Date date = new Date(i * 1000);
        return dateInstance.format(date) + SequenceUtils.SPACE + timeInstance.format(date);
    }

    public boolean isVisible() {
        return this.f3707b;
    }

    public void show() {
        showAtMessage(null);
    }

    public void showAtMessage(@Null String str) {
        this.g = str;
        Game.i.messageManager.processLocalMessages();
        updateContents();
        this.e.setText(Game.i.localeManager.i18n.get("tap_outside_list_to_hide"));
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        DarkOverlay.i().addCallerOverlayLayer("MessagesOverlay", this.f3706a.zIndex - 1, () -> {
            hide();
            return true;
        });
        this.f3706a.getTable().setVisible(true);
        this.f3707b = true;
        this.c.clearActions();
        this.c.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.1f * f), Actions.scaleBy(1.0f, 0.0f, 0.3f * f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.3f * f, Interpolation.swingOut))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.f != null) {
            this.f.clearActions();
            this.f.setTransform(true);
            this.f.addAction(Actions.forever(Actions.rotateBy(360.0f, 1.0f)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f != null) {
            this.f.clearActions();
            this.f.setRotation(0.0f);
        }
    }

    private void c() {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(791621631)));
        image.setSize(1040.0f, 800.0f);
        this.d.addActor(image);
        Label label = new Label(Game.i.localeManager.i18n.get("mailbox").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        label.setSize(1000.0f, 40.0f);
        label.setPosition(40.0f, 740.0f);
        this.d.addActor(label);
        this.f = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-restart"), () -> {
            Game.i.messageManager.requestMessagesFromServer();
        }, MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P900);
        this.f.setSize(48.0f, 48.0f);
        this.f.setOrigin(24.0f, 24.0f);
        this.f.setPosition(952.0f, 732.0f);
        this.f.setIconSize(40.0f, 40.0f);
        this.f.setIconPosition(4.0f, 4.0f);
        if (Game.i.messageManager.isRequestingServer()) {
            a();
        }
        this.d.addActor(this.f);
        Table table = new Table();
        table.setTouchable(Touchable.enabled);
        ScrollPane scrollPane = new ScrollPane(table, Game.i.assetManager.getScrollPaneStyle(10.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(1040.0f, 720.0f);
        this.d.addActor(scrollPane);
        final MessageManager messageManager = Game.i.messageManager;
        Array<MessageManager.Message> messages = messageManager.getMessages();
        for (int i = 0; i < messages.size; i++) {
            final MessageManager.Message message = messages.get(i);
            final Table table2 = new Table();
            table2.setTouchable(Touchable.enabled);
            if (messageManager.isMessageRead(message.id)) {
                table2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f)));
            } else {
                table2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(1.0f, 1.0f, 1.0f, 0.07f)));
            }
            table2.addListener(new InputListener(this) { // from class: com.prineside.tdi2.ui.shared.MessagesOverlay.2
                @Override // com.prineside.tdi2.scene2d.InputListener
                public void enter(InputEvent inputEvent, float f, float f2, int i2, @Null Actor actor) {
                    if (messageManager.isMessageRead(message.id)) {
                        table2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.21f)));
                    } else {
                        table2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(1.0f, 1.0f, 1.0f, 0.1f)));
                    }
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i2, @Null Actor actor) {
                    if (messageManager.isMessageRead(message.id)) {
                        table2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f)));
                    } else {
                        table2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(1.0f, 1.0f, 1.0f, 0.07f)));
                    }
                }
            });
            table2.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.MessagesOverlay.3
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    MessagesOverlay.this.g = message.id;
                    MessagesOverlay.this.updateContents();
                }
            });
            table.add(table2).height(96.0f).padBottom(4.0f).growX().row();
            Group group = new Group();
            group.setTransform(false);
            table2.add((Table) group).size(64.0f).pad(16.0f).padLeft(40.0f);
            Drawable drawable = message.customIcon;
            Drawable drawable2 = drawable;
            if (drawable == null) {
                drawable2 = Game.i.assetManager.getDrawable("icon-letter");
            }
            Image image2 = new Image(drawable2);
            image2.setSize(64.0f, 64.0f);
            group.addActor(image2);
            if (messageManager.isMessageRead(message.id)) {
                image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            } else {
                image2.setColor(Color.WHITE);
                Image image3 = new Image(Game.i.assetManager.getDrawable("count-bubble"));
                image3.setSize(21.5f, 24.5f);
                image3.setPosition(51.0f, 38.0f);
                group.addActor(image3);
            }
            Table table3 = new Table();
            table2.add(table3).padLeft(8.0f).growX();
            LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(message.title, 30, 24, 640.0f);
            limitedWidthLabel.setAlignment(8);
            table3.add((Table) limitedWidthLabel).growX().row();
            Label label2 = new Label(a(message.date), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            label2.setAlignment(8);
            table3.add((Table) label2).growX().row();
            if (!message.notDeletable) {
                ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    Game.i.messageManager.deleteMessage(message);
                    updateContents();
                });
                complexButton.setBackground(new QuadDrawable(new QuadActor(new Color[]{Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE}, new float[]{0.0f, 0.0f, 8.0f, 96.0f, 100.0f, 96.0f, 100.0f, 0.0f})), 0.0f, 0.0f, 100.0f, 96.0f);
                complexButton.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.14f), MaterialColor.RED.P700, MaterialColor.RED.P500, Color.GRAY);
                complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-trash-bin"), 28.0f, 24.0f, 48.0f, 48.0f);
                table2.add((Table) complexButton).width(100.0f).height(96.0f);
            }
        }
        if (messages.size == 0) {
            Label label3 = new Label(Game.i.localeManager.i18n.get("no_new_messages"), Game.i.assetManager.getLabelStyle(30));
            label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) label3).padTop(16.0f).row();
        }
        table.add().width(1.0f).growY();
    }

    private void a(MessageManager.Message message) {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(791621631)));
        image.setSize(1040.0f, 800.0f);
        this.d.addActor(image);
        Table table = new Table();
        table.setSize(1040.0f, 800.0f);
        this.d.addActor(table);
        table.add((Table) new LabelButton(((Object) Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-triangle-left-hollow>")) + SequenceUtils.SPACE + Game.i.localeManager.i18n.get("back_to_mailbox"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE), () -> {
            this.g = null;
            updateContents();
        })).size(960.0f, 40.0f).padLeft(40.0f).padRight(40.0f).padTop(20.0f).padBottom(20.0f).row();
        Table table2 = new Table();
        table2.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f)));
        table.add(table2).growX().row();
        Label label = new Label(message.title, Game.i.assetManager.getLabelStyle(30));
        label.setWrap(true);
        table2.add((Table) label).width(960.0f).padTop(10.0f).row();
        Label label2 = new Label(a(message.date), Game.i.assetManager.getLabelStyle(21));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label2.setAlignment(8);
        table2.add((Table) label2).width(960.0f).padBottom(10.0f).row();
        Table table3 = new Table();
        table3.setTouchable(Touchable.enabled);
        ScrollPane scrollPane = new ScrollPane(table3, Game.i.assetManager.getScrollPaneStyle(10.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(1040.0f, 740.0f);
        table.add((Table) scrollPane).grow();
        Game.i.messageManager.markMessageRead(message);
        table3.add().width(1.0f).height(20.0f).row();
        table3.add((Table) message.contents).width(960.0f).row();
        if (message.items != null && message.items.size != 0) {
            Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-radial-top@flip-y"));
            image2.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            table3.add((Table) image2).height(9.0f).width(960.0f).padTop(15.0f).row();
            Table table4 = new Table();
            table4.background(Game.i.assetManager.getDrawable("gradient-radial-top").tint(MaterialColor.LIGHT_GREEN.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.14f)));
            table3.add(table4).width(960.0f).row();
            Label label3 = new Label(Game.i.localeManager.i18n.get("message_items_title"), Game.i.assetManager.getLabelStyle(24));
            label3.setColor(MaterialColor.LIGHT_GREEN.P500);
            label3.setAlignment(1);
            table4.add((Table) label3).width(960.0f).padTop(15.0f).row();
            Table table5 = new Table();
            table4.add(table5).width(960.0f).padTop(15.0f).row();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < message.items.size; i3++) {
                final ItemStack itemStack = message.items.get(i3);
                ItemCell itemCell = new ItemCell();
                itemCell.setColRow(i, i2);
                itemCell.setItemStack(itemStack);
                itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.MessagesOverlay.4
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        ItemDescriptionDialog.i().showWithCount(itemStack.getItem(), itemStack.getCount());
                        Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                    }
                });
                itemCell.setCompact();
                Cell size = table5.add((Table) itemCell).size(96.0f, 105.0f);
                i++;
                if (i == 10) {
                    i = 0;
                    i2++;
                    size.row();
                }
            }
            if (Game.i.messageManager.isMessageItemsReceived(message.id)) {
                Label label4 = new Label(Game.i.localeManager.i18n.get("message_items_received"), Game.i.assetManager.getLabelStyle(24));
                label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                label4.setAlignment(1);
                table4.add((Table) label4).padTop(15.0f).size(400.0f, 64.0f);
            } else {
                table4.add((Table) new RectButton(Game.i.localeManager.i18n.get("receive_message_items_button"), Game.i.assetManager.getLabelStyle(24), () -> {
                    Game.i.messageManager.receiveMessageItems(message);
                    updateContents();
                })).padTop(15.0f).size(400.0f, 64.0f);
            }
        }
        table3.add().width(1.0f).growY();
    }

    public void updateContents() {
        this.d.clear();
        MessageManager.Message message = null;
        if (this.g != null) {
            message = Game.i.messageManager.getMessage(this.g);
        }
        if (message == null) {
            c();
        } else {
            a(message);
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        DarkOverlay.i().removeCaller("MessagesOverlay");
        this.f3707b = false;
        this.c.clearActions();
        this.c.addAction(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.07f * f), Actions.scaleBy(0.0f, -this.c.getScaleY(), 0.3f * f, Interpolation.swingIn)), Actions.scaleBy(-this.c.getScaleX(), 0.0f, 0.3f * f, Interpolation.swingIn)), Actions.run(() -> {
            this.f3706a.getTable().setVisible(false);
        })));
    }
}
