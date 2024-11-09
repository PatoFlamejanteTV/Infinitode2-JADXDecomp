package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.AbilitySlotButton;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.OverlayContinueButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/AbilitySelectionOverlay.class */
public final class AbilitySelectionOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3452a = TLog.forClass(AbilitySelectionOverlay.class);
    private Runnable c;
    private ObjectConsumer<SelectedAbilitiesConfiguration> d;
    private Label e;
    private Label f;
    private Label g;
    private Group h;
    private Group i;
    private Group j;
    private Table k;
    private ComplexButton l;
    private Label m;
    private Label n;
    private OverlayContinueButton o;
    private boolean q;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3453b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, HttpStatus.SC_MULTI_STATUS, "AbilitySelectionOverlay main");
    private final Array<AbilityListItem> p = new Array<>();
    private final AbilitySlotButton[] r = new AbilitySlotButton[6];
    private int s = -1;

    public static AbilitySelectionOverlay i() {
        return (AbilitySelectionOverlay) Game.i.uiManager.getComponent(AbilitySelectionOverlay.class);
    }

    public AbilitySelectionOverlay() {
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(600.0f, 380.0f);
        this.f3453b.getTable().add((Table) group).size(1200.0f, 760.0f);
        this.h = new Group();
        this.h.setTransform(true);
        this.h.setOrigin(600.0f, 380.0f);
        this.h.setSize(1200.0f, 760.0f);
        group.addActor(this.h);
        this.h.addActor(new QuadActor(new Color(791621631), new float[]{0.0f, 22.0f, 10.0f, 748.0f, 1200.0f, 760.0f, 1200.0f, 0.0f}));
        this.e = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.e.setPosition(60.0f, 666.0f);
        this.e.setSize(300.0f, 27.0f);
        this.h.addActor(this.e);
        this.l = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            if (Game.i.progressManager.getItemsCount(Item.D.ABILITY_TOKEN) <= 0) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_tokens"));
            } else {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("ability_selection_token_confirm"), () -> {
                    int intValue;
                    int abilities;
                    boolean z = false;
                    for (AbilityType abilityType : AbilityType.values) {
                        if (Game.i.gameValueManager.getSnapshot().getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType)) != 0 && (abilities = Game.i.progressManager.getAbilities(abilityType)) < (intValue = Game.i.gameValueManager.getEndlessSnapshot().getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType)))) {
                            z = true;
                            Game.i.progressManager.addAbilities(abilityType, intValue - abilities);
                        }
                    }
                    if (z) {
                        Game.i.analyticsManager.logCurrencySpent("used", "ability_token", 1);
                        Game.i.progressManager.removeItems(Item.D.ABILITY_TOKEN, 1);
                        update();
                        return;
                    }
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("all_abilities_are_already_purchased"));
                });
            }
        });
        this.l.setBackground(Game.i.assetManager.getDrawable("ui-ability-selection-token-button"), 0.0f, 0.0f, 158.0f, 79.0f);
        this.l.setSize(158.0f, 79.0f);
        this.l.setPosition(-7.0f, 550.0f);
        Image image = new Image(Game.i.assetManager.getDrawable("ability-token"));
        image.setPosition(42.0f, 12.0f);
        image.setSize(64.0f, 64.0f);
        image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        this.l.addActor(image);
        Image image2 = new Image(Game.i.assetManager.getDrawable("ability-token"));
        image2.setPosition(40.0f, 14.0f);
        image2.setSize(64.0f, 64.0f);
        this.l.addActor(image2);
        this.n = new Label("0", Game.i.assetManager.getLabelStyle(24));
        this.n.setPosition(105.0f, 20.0f);
        this.n.setSize(2.0f, 21.0f);
        this.n.setAlignment(1);
        this.n.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        this.l.addActor(this.n);
        this.m = new Label("0", Game.i.assetManager.getLabelStyle(24));
        this.m.setPosition(103.0f, 22.0f);
        this.m.setSize(2.0f, 21.0f);
        this.m.setAlignment(1);
        this.l.addActor(this.m);
        this.h.addActor(this.l);
        final Image image3 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
        image3.setSize(48.0f, 48.0f);
        image3.setPosition(168.0f, 565.0f);
        image3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        image3.setTouchable(Touchable.enabled);
        image3.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.AbilitySelectionOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                int i = 0;
                int[] iArr = new int[ResourceType.values.length];
                for (AbilityType abilityType : AbilityType.values) {
                    if (Game.i.gameValueManager.getSnapshot().getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType)) != 0) {
                        int intValue = Game.i.gameValueManager.getSnapshot().getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType));
                        for (int abilities = Game.i.progressManager.getAbilities(abilityType); abilities < intValue; abilities++) {
                            Ability.Factory<? extends Ability> factory = Game.i.abilityManager.getFactory(abilityType);
                            i += factory.getPriceInGreenPapers(abilities);
                            for (ResourceType resourceType : ResourceType.values) {
                                int ordinal = resourceType.ordinal();
                                iArr[ordinal] = iArr[ordinal] + factory.getPriceInResources(resourceType, abilities);
                            }
                        }
                    }
                }
                StringBuilder sb = new StringBuilder("[#81C784]<@icon-money>" + ((Object) StringFormatter.commaSeparatedNumber(i)) + "[]");
                for (ResourceType resourceType2 : ResourceType.values) {
                    if (iArr[resourceType2.ordinal()] > 0) {
                        String upperCase = Game.i.resourceManager.getColor(resourceType2).toString().toUpperCase(Locale.US);
                        String str = upperCase;
                        if (upperCase.length() > 6) {
                            str = str.substring(0, 6);
                        }
                        sb.append("  [#").append(str).append("]<@resource-").append(resourceType2.name().toLowerCase(Locale.ENGLISH)).append(">").append((CharSequence) StringFormatter.commaSeparatedNumber(iArr[resourceType2.ordinal()])).append("[]");
                    }
                }
                TooltipsOverlay.i().showText(TooltipsOverlay.TAG_GENERIC_TOOLTIP, image3, Game.i.assetManager.replaceRegionAliasesWithChars(Game.i.localeManager.i18n.format("ability_selection_token_hint", sb.toString())), AbilitySelectionOverlay.this.f3453b.mainUiLayer, AbilitySelectionOverlay.this.f3453b.zIndex + 1, 4);
            }
        });
        this.h.addActor(image3);
        for (int i = 0; i < 6; i++) {
            this.r[i] = new AbilitySlotButton(true, Game.i.gameValueManager.getSnapshot());
            this.r[i].setPosition(60.0f + ((i % 2) * 128.0f), ((172.0f + ((i / 2) * 128.0f)) + ((1 - (i % 2)) * 10.0f)) - 40.0f);
            this.h.addActor(this.r[i]);
            final int i2 = i;
            this.r[i].addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.AbilitySelectionOverlay.2
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    if (AbilitySelectionOverlay.this.s == i2) {
                        AbilitySelectionOverlay.this.selectSlot(-1);
                    } else {
                        AbilitySelectionOverlay.this.selectSlot(i2);
                    }
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            });
        }
        this.k = new Table();
        this.k.setWidth(780.0f);
        ScrollPane scrollPane = new ScrollPane(this.k);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(780.0f, 642.0f);
        scrollPane.setPosition(358.0f, 52.0f);
        this.h.addActor(scrollPane);
        Image image4 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image4.setColor(new Color(791621631));
        image4.setSize(780.0f, 64.0f);
        image4.setPosition(358.0f, 631.0f);
        image4.setTouchable(Touchable.disabled);
        this.h.addActor(image4);
        Image image5 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image5.setColor(new Color(791621631));
        image5.setSize(780.0f, 64.0f);
        image5.setPosition(358.0f, 51.0f);
        image5.setTouchable(Touchable.disabled);
        this.h.addActor(image5);
        this.i = new Group();
        this.i.setTransform(false);
        this.i.setPosition(155.0f, 50.0f);
        this.i.setSize(280.0f, 64.0f);
        this.i.setTouchable(Touchable.disabled);
        this.h.addActor(this.i);
        Image image6 = new Image(Game.i.assetManager.getDrawable("icon-arrow-pointer-top-left"));
        image6.setSize(64.0f, 64.0f);
        image6.setPosition(0.0f, 0.0f);
        this.i.addActor(image6);
        this.f = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.f.setPosition(80.0f, 0.0f);
        this.i.addActor(this.f);
        this.i.setVisible(false);
        this.j = new Group();
        this.j.setTransform(false);
        this.j.setPosition(240.0f, 50.0f);
        this.j.setSize(380.0f, 64.0f);
        this.h.addActor(this.j);
        Image image7 = new Image(Game.i.assetManager.getDrawable("icon-arrow-pointer-top-right"));
        image7.setSize(64.0f, 64.0f);
        image7.setPosition(316.0f, 0.0f);
        this.j.addActor(image7);
        this.g = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.g.setPosition(0.0f, 0.0f);
        this.g.setSize(296.0f, 20.0f);
        this.g.setAlignment(16);
        this.j.addActor(this.g);
        this.o = new OverlayContinueButton("", "icon-triangle-right", MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, () -> {
            if (this.d != null) {
                SelectedAbilitiesConfiguration b2 = b();
                a(b2);
                this.d.accept(b2);
            }
            setVisible(false);
        });
        this.o.setPosition(812.0f, -13.0f);
        this.h.addActor(this.o);
        this.f3453b.getTable().setVisible(false);
        this.q = false;
    }

    private SelectedAbilitiesConfiguration b() {
        SelectedAbilitiesConfiguration selectedAbilitiesConfiguration = new SelectedAbilitiesConfiguration();
        int i = 0;
        for (AbilitySlotButton abilitySlotButton : this.r) {
            if (abilitySlotButton.getAbility() != null) {
                selectedAbilitiesConfiguration.slots[i] = abilitySlotButton.getAbility();
                selectedAbilitiesConfiguration.counts[i] = Game.i.progressManager.getAbilities(abilitySlotButton.getAbility());
            }
            i++;
        }
        return selectedAbilitiesConfiguration;
    }

    private static void a(SelectedAbilitiesConfiguration selectedAbilitiesConfiguration) {
        ProgressPrefs.i().inventory.lastSelectedAbilities.clear();
        for (int i = 0; i < selectedAbilitiesConfiguration.slots.length; i++) {
            ProgressPrefs.i().inventory.lastSelectedAbilities.add(selectedAbilitiesConfiguration.slots[i]);
        }
        ProgressPrefs.i().requireSave();
    }

    public final void update() {
        int itemsCount = Game.i.progressManager.getItemsCount(Item.D.ABILITY_TOKEN);
        this.n.setText(new StringBuilder().append(itemsCount).toString());
        this.m.setText(new StringBuilder().append(itemsCount).toString());
        if (itemsCount <= 0) {
            ComplexButton complexButton = this.l;
            Color color = MaterialColor.GREY.P800;
            Color color2 = MaterialColor.GREY.P800;
            complexButton.setBackgroundColors(color, color, color2, color2);
        } else {
            this.l.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, MaterialColor.GREY.P800);
        }
        for (AbilitySlotButton abilitySlotButton : this.r) {
            if (abilitySlotButton.getAbility() != null) {
                abilitySlotButton.setCount(Game.i.progressManager.getAbilities(abilitySlotButton.getAbility()));
            }
        }
        for (int i = 0; i < this.p.size; i++) {
            this.p.get(i).update();
        }
    }

    public final void show(Runnable runnable, ObjectConsumer<SelectedAbilitiesConfiguration> objectConsumer) {
        this.c = runnable;
        this.d = objectConsumer;
        this.e.setText(Game.i.localeManager.i18n.get("select_abilities"));
        this.f.setText(Game.i.localeManager.i18n.get("tap_to_select_slot"));
        this.g.setText(Game.i.localeManager.i18n.get("choose_ability_for_slot"));
        this.o.label.setText(Game.i.localeManager.i18n.get("play"));
        setVisible(true);
        for (AbilitySlotButton abilitySlotButton : this.r) {
            abilitySlotButton.setAbility(null);
        }
        int i = 0;
        if (SettingsPrefs.i().settings.lastSelectedAbilities.size != 0) {
            Array.ArrayIterator<AbilityType> it = SettingsPrefs.i().settings.lastSelectedAbilities.iterator();
            while (it.hasNext()) {
                this.r[i].setAbility(it.next());
                i++;
                if (i == 6) {
                    break;
                }
            }
        } else {
            Array.ArrayIterator<AbilityType> it2 = ProgressPrefs.i().inventory.lastSelectedAbilities.iterator();
            while (it2.hasNext()) {
                this.r[i].setAbility(it2.next());
                i++;
                if (i == 6) {
                    break;
                }
            }
        }
        this.k.clear();
        this.k.add().expandX().height(64.0f).row();
        this.p.clear();
        for (AbilityType abilityType : AbilityType.values) {
            if (Game.i.gameValueManager.getSnapshot().getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType)) != 0) {
                AbilityListItem abilityListItem = new AbilityListItem(this, abilityType, (byte) 0);
                this.p.add(abilityListItem);
                this.k.add((Table) abilityListItem).size(780.0f, abilityListItem.getHeight()).padBottom(4.0f).row();
            }
        }
        this.k.add().expandX().height(64.0f).row();
        this.k.add().expand();
        selectSlot(-1);
        update();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        if (this.c != null) {
            a(b());
            this.c.run();
        }
        setVisible(false);
        this.c = null;
        this.d = null;
    }

    public final void setVisible(boolean z) {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        if (z) {
            DarkOverlay.i().addCaller("AbilitySelectionOverlay", UiManager.MainUiLayer.SCREEN, HttpStatus.SC_PARTIAL_CONTENT, () -> {
                hide();
                return true;
            });
            this.f3453b.getTable().setVisible(true);
            this.f3453b.getTable().setTouchable(Touchable.childrenOnly);
            this.h.clearActions();
            this.h.addAction(Actions.sequence(Actions.scaleTo(1.0f, 1.0f), Actions.moveTo(Game.i.settingsManager.getScaledViewportHeight() * 2.0f, 0.0f), Actions.moveTo(0.0f, 0.0f, 0.2f * f)));
        } else {
            DarkOverlay.i().removeCaller("AbilitySelectionOverlay");
            this.f3453b.getTable().setTouchable(Touchable.disabled);
            this.h.clearActions();
            this.h.addAction(Actions.sequence(Actions.moveTo(Game.i.settingsManager.getScaledViewportHeight() * 2.0f, 0.0f, 0.2f * f), Actions.run(() -> {
                this.f3453b.getTable().setVisible(false);
            })));
        }
        this.q = z;
    }

    public final void selectSlot(int i) {
        for (AbilitySlotButton abilitySlotButton : this.r) {
            abilitySlotButton.setSelected(false);
        }
        if (i != -1) {
            this.r[i].setSelected(true);
            this.i.setVisible(false);
            this.j.setVisible(true);
        } else {
            this.i.setVisible(true);
            this.j.setVisible(false);
        }
        this.s = i;
        update();
    }

    public final void setSelectedSlotAbilityType(AbilityType abilityType) {
        if (this.s == -1) {
            return;
        }
        for (AbilitySlotButton abilitySlotButton : this.r) {
            if (abilitySlotButton.getAbility() == abilityType) {
                abilitySlotButton.setAbility(null);
            }
        }
        this.r[this.s].setAbility(abilityType);
        selectSlot(-1);
    }

    public final boolean isVisible() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/AbilitySelectionOverlay$AbilityListItem.class */
    public class AbilityListItem extends Group {
        private AbilityType l;
        private Image m;
        private Image n;
        private Label o;
        private Label p;
        private ComplexButton q;
        private Label r;
        private Table s;
        private boolean t;

        /* synthetic */ AbilityListItem(AbilitySelectionOverlay abilitySelectionOverlay, AbilityType abilityType, byte b2) {
            this(abilityType);
        }

        private AbilityListItem(AbilityType abilityType) {
            this.l = abilityType;
            Ability.Factory<? extends Ability> factory = Game.i.abilityManager.getFactory(abilityType);
            Label label = new Label(factory.getDescription(Game.i.gameValueManager.getSnapshot()), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            label.setColor(1.0f, 1.0f, 1.0f, 0.78f);
            label.setPosition(96.0f, 8.0f);
            label.setWidth(400.0f);
            label.setWrap(true);
            label.setAlignment(10);
            label.layout();
            label.pack();
            label.setWidth(400.0f);
            if (label.getHeight() < 64.0f) {
                label.setHeight(64.0f);
            }
            float height = 64.0f + label.getHeight();
            setTransform(false);
            setSize(780.0f, height);
            this.m = new Image(Game.i.assetManager.getBlankWhiteTextureRegion());
            this.m.setSize(780.0f, height);
            addActor(this.m);
            setTouchable(Touchable.enabled);
            addListener(new ClickListener(AbilitySelectionOverlay.this, abilityType) { // from class: com.prineside.tdi2.ui.shared.AbilitySelectionOverlay.AbilityListItem.1

                /* renamed from: a, reason: collision with root package name */
                private /* synthetic */ AbilityType f3458a;

                {
                    this.f3458a = abilityType;
                }

                @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                    super.enter(inputEvent, f, f2, i, actor);
                    AbilityListItem.this.t = isOver();
                    AbilityListItem.this.d();
                }

                @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                    super.exit(inputEvent, f, f2, i, actor);
                    AbilityListItem.this.t = isOver();
                    AbilityListItem.this.d();
                }

                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    AbilitySelectionOverlay.this.setSelectedSlotAbilityType(this.f3458a);
                }
            });
            this.n = new Image(Game.i.assetManager.getBlankWhiteTextureRegion());
            this.n.setColor(MaterialColor.GREEN.P500);
            this.n.setSize(6.0f, height);
            addActor(this.n);
            Actor image = new Image(factory.getIconDrawable());
            image.setColor(factory.getColor());
            image.setSize(64.0f, 64.0f);
            image.setPosition(22.0f, height - 80.0f);
            addActor(image);
            Actor label2 = new Label(factory.getTitle(), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
            label2.setColor(factory.getColor());
            label2.setPosition(96.0f, height - 53.0f);
            addActor(label2);
            addActor(label);
            Table table = new Table();
            table.setSize(64.0f, 64.0f);
            table.setPosition(450.0f, height - 64.0f);
            addActor(table);
            this.o = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
            table.add((Table) this.o);
            this.p = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            this.p.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) this.p);
            Actor quadActor = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.14f), new float[]{0.0f, 0.0f, 16.0f, height, 156.0f, height, 140.0f, 0.0f});
            quadActor.setPosition(540.0f, 0.0f);
            addActor(quadActor);
            this.s = new Table();
            this.s.setSize(124.0f, height);
            this.s.setPosition(557.0f, 0.0f);
            addActor(this.s);
            this.q = new ComplexButton("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE), () -> {
                if (Game.i.progressManager.getAbilities(abilityType) >= Game.i.gameValueManager.getSnapshot().getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType))) {
                    return;
                }
                int abilities = Game.i.progressManager.getAbilities(abilityType);
                if (Game.i.progressManager.getGreenPapers() < factory.getPriceInGreenPapers(abilities)) {
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_green_papers"));
                    return;
                }
                for (ResourceType resourceType : ResourceType.values) {
                    if (Game.i.progressManager.getResources(resourceType) < factory.getPriceInResources(resourceType, abilities)) {
                        Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_resources"));
                        return;
                    }
                }
                Game.i.progressManager.addAbilities(abilityType, 1);
                Game.i.progressManager.removeGreenPapers(factory.getPriceInGreenPapers(abilities));
                for (ResourceType resourceType2 : ResourceType.values) {
                    Game.i.progressManager.removeResources(resourceType2, factory.getPriceInResources(resourceType2, abilities));
                }
                AbilitySelectionOverlay.this.update();
            });
            this.q.setSize(100.0f, height);
            this.q.setPosition(680.0f, 0.0f);
            this.q.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 16.0f, height, 100.0f, height, 100.0f, 0.0f})), 0.0f, 0.0f, 100.0f, height);
            this.q.setIconPositioned(Game.i.assetManager.getDrawable("icon-plus"), 30.0f, (height * 0.5f) - 24.0f, 48.0f, 48.0f);
            addActor(this.q);
            this.r = new Label("MAX", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            this.r.setPosition(680.0f, 0.0f);
            this.r.setSize(100.0f, height);
            this.r.setAlignment(1);
            addActor(this.r);
            d();
            update();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (this.t && AbilitySelectionOverlay.this.s != -1) {
                this.m.setColor(0.0f, 0.0f, 0.0f, 0.42f);
            } else {
                this.m.setColor(0.0f, 0.0f, 0.0f, 0.21f);
            }
        }

        public void update() {
            int intValue = Game.i.gameValueManager.getSnapshot().getIntValue(Game.i.abilityManager.getMaxPerGameGameValueType(this.l));
            int abilities = Game.i.progressManager.getAbilities(this.l);
            this.o.setText(String.valueOf(abilities));
            this.p.setText("  / " + intValue);
            this.s.clearChildren();
            if (abilities >= intValue) {
                this.r.setVisible(true);
                this.q.setVisible(false);
            } else {
                boolean z = true;
                Ability.Factory<? extends Ability> factory = Game.i.abilityManager.getFactory(this.l);
                if (factory.getPriceInGreenPapers(abilities) > Game.i.progressManager.getGreenPapers()) {
                    z = false;
                } else {
                    ResourceType[] resourceTypeArr = ResourceType.values;
                    int length = resourceTypeArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        ResourceType resourceType = resourceTypeArr[i];
                        if (Game.i.progressManager.getResources(resourceType) >= factory.getPriceInResources(resourceType, abilities)) {
                            i++;
                        } else {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    this.q.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P600, Color.WHITE);
                } else {
                    this.q.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P600, Color.WHITE);
                }
                if (factory.getPriceInGreenPapers(abilities) != 0) {
                    this.s.add((Table) new Image(Game.i.assetManager.getDrawable("icon-money"))).size(32.0f).padRight(6.0f).padBottom(4.0f);
                    this.s.add((Table) new Label(String.valueOf(factory.getPriceInGreenPapers(abilities)), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE))).padBottom(4.0f).row();
                }
                for (ResourceType resourceType2 : ResourceType.values) {
                    if (factory.getPriceInResources(resourceType2, abilities) != 0) {
                        Image image = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[resourceType2.ordinal()]));
                        image.setColor(Game.i.resourceManager.getColor(resourceType2));
                        this.s.add((Table) image).size(32.0f).padRight(6.0f).padBottom(4.0f);
                        this.s.add((Table) new Label(String.valueOf(factory.getPriceInResources(resourceType2, abilities)), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE))).padBottom(4.0f).row();
                    }
                }
                this.r.setVisible(false);
                this.q.setVisible(true);
            }
            this.n.setVisible(false);
            for (AbilitySlotButton abilitySlotButton : AbilitySelectionOverlay.this.r) {
                if (abilitySlotButton.getAbility() == this.l) {
                    this.n.setVisible(true);
                    return;
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/AbilitySelectionOverlay$SelectedAbilitiesConfiguration.class */
    public static class SelectedAbilitiesConfiguration implements KryoSerializable {
        public AbilityType[] slots;
        public int[] counts;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.slots);
            kryo.writeObject(output, this.counts);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.slots = (AbilityType[]) kryo.readObject(input, AbilityType[].class);
            this.counts = (int[]) kryo.readObject(input, int[].class);
        }

        public SelectedAbilitiesConfiguration() {
            this.slots = new AbilityType[6];
            this.counts = new int[6];
        }

        public SelectedAbilitiesConfiguration(SelectedAbilitiesConfiguration selectedAbilitiesConfiguration) {
            this.slots = new AbilityType[6];
            this.counts = new int[6];
            if (selectedAbilitiesConfiguration != null) {
                for (int i = 0; i < this.slots.length; i++) {
                    this.slots[i] = selectedAbilitiesConfiguration.slots[i];
                    this.counts[i] = selectedAbilitiesConfiguration.counts[i];
                }
            }
        }

        public int getSlot(AbilityType abilityType) {
            Preconditions.checkNotNull(abilityType);
            for (int i = 0; i < this.slots.length; i++) {
                if (this.slots[i] == abilityType) {
                    return i;
                }
            }
            return -1;
        }

        public void toJson(Json json) {
            for (int i = 0; i < 6; i++) {
                json.writeObjectStart();
                if (this.slots[i] != null) {
                    json.writeValue("type", this.slots[i].name());
                    json.writeValue("count", Integer.valueOf(this.counts[i]));
                }
                json.writeObjectEnd();
            }
        }

        public static SelectedAbilitiesConfiguration fromJson(JsonValue jsonValue) {
            SelectedAbilitiesConfiguration selectedAbilitiesConfiguration = new SelectedAbilitiesConfiguration();
            int i = 0;
            Iterator<JsonValue> iterator2 = jsonValue.iterator2();
            while (iterator2.hasNext()) {
                JsonValue next = iterator2.next();
                if (next.get("type") != null) {
                    try {
                        selectedAbilitiesConfiguration.slots[i] = AbilityType.valueOf(next.getString("type"));
                        selectedAbilitiesConfiguration.counts[i] = next.getInt("count");
                    } catch (Exception e) {
                        AbilitySelectionOverlay.f3452a.e("failed to load configuration slot " + i, e);
                    }
                }
                i++;
            }
            return selectedAbilitiesConfiguration;
        }
    }
}
