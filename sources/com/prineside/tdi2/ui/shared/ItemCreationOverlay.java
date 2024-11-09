package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.CheckBox;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ItemCreationOverlay.class */
public class ItemCreationOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3564a = TLog.forClass(ItemCreationOverlay.class);

    /* renamed from: b, reason: collision with root package name */
    private static final ItemType[] f3565b = new ItemType[0];
    public final SelectBox.SelectBoxStyle selectBoxStyle;
    public final TextField.TextFieldStyle textFieldStyle;
    public final Table form;
    public Item currentItem;
    public Item originalItem;
    public boolean inPlaceItemTypeChangeAllowed;
    private final RectButton f;
    private final Group g;
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 150, "ItemCreationOverlay main");
    private final ObjectMap<ItemType, RectButton> d = new ObjectMap<>();
    public ObjectConsumer<Item> changeListener = null;
    public int customIntA = -1;
    public int customIntB = -1;
    public Object customObject = null;
    private final CheckBox.CheckBoxStyle e = new CheckBox.CheckBoxStyle(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.RED.P500), Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.GREEN.P500), Game.i.assetManager.getFont(24), Color.WHITE);

    public static ItemCreationOverlay i() {
        return (ItemCreationOverlay) Game.i.uiManager.getComponent(ItemCreationOverlay.class);
    }

    public ItemCreationOverlay() {
        this.e.checkboxOff.setLeftWidth(24.0f);
        this.e.checkboxOff.setBottomHeight(24.0f);
        this.selectBoxStyle = Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getFont(24), true);
        this.textFieldStyle = Game.i.assetManager.getTextFieldStyle(24);
        Group group = new Group();
        group.setTransform(false);
        this.c.getTable().add((Table) group).size(1200.0f, 1000.0f);
        Actor image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(1200.0f, 1000.0f);
        image.setColor(new Color(858993663));
        group.addActor(image);
        Table table = new Table();
        ScrollPane scrollPane = new ScrollPane(table, Game.i.assetManager.getScrollPaneStyle(8.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(240.0f, 1000.0f);
        scrollPane.setScrollingDisabled(true, false);
        group.addActor(scrollPane);
        ItemType[] itemTypeArr = ItemType.values;
        int length = itemTypeArr.length;
        for (int i = 0; i < length; i++) {
            ItemType itemType = itemTypeArr[i];
            ItemType[] itemTypeArr2 = f3565b;
            int length2 = itemTypeArr2.length;
            int i2 = 0;
            while (true) {
                if (i2 < length2) {
                    i2 = itemType != itemTypeArr2[i2] ? i2 + 1 : i2;
                } else {
                    RectButton rectButton = new RectButton(itemType.name(), Game.i.assetManager.getLabelStyle(24), () -> {
                        this.currentItem = Game.i.itemManager.getFactory(itemType).createDefault();
                        if (!this.inPlaceItemTypeChangeAllowed) {
                            setInPlaceEditingItem(null, null, false);
                        }
                        updateForm();
                    });
                    Color color = Color.WHITE;
                    Color color2 = Color.WHITE;
                    rectButton.setIconLabelColors(color, color, color2, color2);
                    table.add((Table) rectButton).size(240.0f, 48.0f).padBottom(2.0f).row();
                    this.d.put(itemType, rectButton);
                    break;
                }
            }
        }
        table.add().width(1.0f).height(96.0f);
        this.form = new Table();
        this.form.setSize(940.0f, 906.0f);
        ScrollPane scrollPane2 = new ScrollPane(this.form, Game.i.assetManager.getScrollPaneStyle(16.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane2);
        scrollPane2.setSize(940.0f, 906.0f);
        scrollPane2.setPosition(250.0f, 84.0f);
        group.addActor(scrollPane2);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setPosition(250.0f, 10.0f);
        group.addActor(group2);
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("<", this.textFieldStyle);
        textFieldXPlatform.setSize(220.0f, 64.0f);
        textFieldXPlatform.setPosition(210.0f, 0.0f);
        group2.addActor(textFieldXPlatform);
        RectButton rectButton2 = new RectButton("From json", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.ItemCreationOverlay.1
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    Threads.i().runOnMainThread(() -> {
                        try {
                            JsonValue parse = new JsonReader().parse(str);
                            ItemCreationOverlay.this.currentItem = Item.fromJson(parse);
                            ItemCreationOverlay.this.updateForm();
                        } catch (Exception unused) {
                            Notifications.i().add("Failed to parse JSON", null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                        }
                    });
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, "From json", "", "Enter JSON of item");
        });
        rectButton2.setSize(230.0f, 64.0f);
        rectButton2.setPosition(-240.0f, 0.0f);
        group2.addActor(rectButton2);
        RectButton rectButton3 = new RectButton("Get json", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            Json json = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeObjectStart();
            this.currentItem.toJson(json);
            json.writeObjectEnd();
            f3564a.i(stringWriter.toString(), new Object[0]);
            textFieldXPlatform.setText(stringWriter.toString());
            textFieldXPlatform.getStage().setKeyboardFocus(textFieldXPlatform);
            textFieldXPlatform.selectAll();
        });
        rectButton3.setSize(200.0f, 64.0f);
        rectButton3.setPosition(0.0f, 0.0f);
        group2.addActor(rectButton3);
        this.g = new Group();
        this.g.setPosition(456.0f, 0.0f);
        this.g.setSize(64.0f, 64.0f);
        group2.addActor(this.g);
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("1", this.textFieldStyle);
        textFieldXPlatform2.setSize(200.0f, 64.0f);
        textFieldXPlatform2.setPosition(530.0f, 0.0f);
        group2.addActor(textFieldXPlatform2);
        RectButton rectButton4 = new RectButton("Give", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            int i3 = 1;
            try {
                i3 = Integer.parseInt(textFieldXPlatform2.getText());
            } catch (Exception unused) {
                Dialog.i().showAlert("Please enter correct items count");
            }
            if (this.currentItem != null) {
                Item cpy = this.currentItem.cpy();
                Game.i.progressManager.addItems(cpy, i3, "dev_mode");
                f3564a.i("item added: " + cpy.toString(), new Object[0]);
                Label label = new Label("Done!", Game.i.assetManager.getLabelStyle(24));
                label.setColor(MaterialColor.GREEN.P500);
                label.setPosition(740.0f, 0.0f);
                label.setSize(200.0f, 64.0f);
                label.setAlignment(1);
                label.setTouchable(Touchable.disabled);
                group2.addActor(label);
                label.addAction(Actions.sequence(Actions.moveBy(0.0f, 64.0f, 0.3f, Interpolation.exp5Out), Actions.fadeOut(0.2f), Actions.removeActor()));
            }
        });
        rectButton4.setSize(200.0f, 64.0f);
        rectButton4.setPosition(740.0f, 0.0f);
        group2.addActor(rectButton4);
        this.f = new RectButton("Apply", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            if (this.currentItem != null && this.originalItem != null) {
                if (this.originalItem.getType() == this.currentItem.getType()) {
                    try {
                        this.changeListener.accept(this.originalItem.from(this.currentItem));
                    } catch (Exception e) {
                        f3564a.e("failed to apply item changes", e);
                        this.changeListener.accept(this.currentItem);
                    }
                } else {
                    f3564a.i("type changed, new item", new Object[0]);
                    this.changeListener.accept(this.currentItem);
                }
                Label label = new Label("Done!", Game.i.assetManager.getLabelStyle(24));
                label.setColor(MaterialColor.GREEN.P500);
                label.setPosition(740.0f, 0.0f);
                label.setSize(200.0f, 64.0f);
                label.setAlignment(1);
                label.setTouchable(Touchable.disabled);
                group2.addActor(label);
                label.addAction(Actions.sequence(Actions.moveBy(0.0f, 64.0f, 0.3f, Interpolation.exp5Out), Actions.fadeOut(0.2f), Actions.removeActor()));
                return;
            }
            f3564a.e("current " + this.currentItem + " original " + this.originalItem, new Object[0]);
        });
        this.f.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.GRAY);
        this.f.setSize(200.0f, 64.0f);
        this.f.setPosition(960.0f, 0.0f);
        group2.addActor(this.f);
        this.currentItem = Item.D.GREEN_PAPER;
        updateForm();
        hide();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public boolean isPersistent() {
        return true;
    }

    public Label hintLabel(String str, boolean z) {
        Label label = new Label(str, Game.i.assetManager.getLabelStyle(21));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label.setWrap(true);
        this.form.add((Table) label).top().left().pad(0.0f, 0.0f, 10.0f, 0.0f).top().left().fillX().expandX();
        if (z) {
            this.form.row();
        }
        return label;
    }

    public Label labelEndRow(String str, boolean z) {
        Label label = new Label(str, Game.i.assetManager.getLabelStyle(24));
        this.form.add((Table) label).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).top().left();
        if (z) {
            this.form.row();
        }
        return label;
    }

    public Label label(String str) {
        return labelEndRow(str, true);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public LabelToggleButton toggle(String str, boolean z, ObjectConsumer<Boolean> objectConsumer) {
        return toggle(true, str, z, objectConsumer);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public LabelToggleButton toggle(boolean z, String str, boolean z2, ObjectConsumer<Boolean> objectConsumer) {
        LabelToggleButton labelToggleButton = new LabelToggleButton(str, z2, 24, 32.0f, objectConsumer);
        if (z) {
            this.form.add(labelToggleButton).height(48.0f).top().left().row();
        }
        return labelToggleButton;
    }

    public void selectBox(SelectBox selectBox) {
        this.form.add((Table) selectBox).size(400.0f, 48.0f).top().left().row();
    }

    public TextFieldXPlatform textFieldOfWidth(String str, float f, ObjectConsumer<String> objectConsumer) {
        return textFieldEndRow(str, f, objectConsumer, true);
    }

    public TextFieldXPlatform textFieldEndRow(String str, float f, final ObjectConsumer<String> objectConsumer, boolean z) {
        final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(str, this.textFieldStyle);
        textFieldXPlatform.setSize(f, 64.0f);
        this.form.add((Table) textFieldXPlatform).top().left().size(f, 48.0f);
        if (z) {
            this.form.row();
        }
        textFieldXPlatform.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.ItemCreationOverlay.2
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                objectConsumer.accept(textFieldXPlatform.getText());
            }
        });
        return textFieldXPlatform;
    }

    public TextFieldXPlatform textField(String str, ObjectConsumer<String> objectConsumer) {
        return textFieldOfWidth(str, 300.0f, objectConsumer);
    }

    public void updateItemIcon() {
        this.g.clear();
        this.g.addActor(this.currentItem.generateIcon(64.0f, true));
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x0b76  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0ba1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateForm() {
        /*
            Method dump skipped, instructions count: 4200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.shared.ItemCreationOverlay.updateForm():void");
    }

    public void setInPlaceEditingItem(Item item, ObjectConsumer<Item> objectConsumer, boolean z) {
        this.changeListener = objectConsumer;
        this.originalItem = item;
        this.inPlaceItemTypeChangeAllowed = z;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        DarkOverlay.i().removeCaller("ItemCreationOverlay");
        this.c.getTable().setVisible(false);
        setInPlaceEditingItem(null, null, false);
        Game.i.uiManager.stage.unfocusAll();
    }

    public void show() {
        if (!Config.isModdingMode() && !Game.i.progressManager.isDeveloperModeEnabled()) {
            Dialog.i().showAlert("Developer mode research required");
        } else {
            DarkOverlay.i().addCallerOverlayLayer("ItemCreationOverlay", this.c.zIndex - 1, () -> {
                hide();
                return true;
            });
            this.c.getTable().setVisible(true);
        }
    }

    public void showForItem(Item item) {
        this.currentItem = item.cpy();
        setInPlaceEditingItem(null, null, false);
        updateForm();
        show();
    }

    public void showForItemListenable(Item item, ObjectConsumer<Item> objectConsumer, boolean z) {
        this.currentItem = item.cpy();
        setInPlaceEditingItem(item, objectConsumer, z);
        updateForm();
        show();
    }
}
