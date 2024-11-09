package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.abilities.OverloadAbility;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.AbilityApply;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.items.GameValueLevelItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/TargetMenu.class */
public class TargetMenu {

    /* renamed from: b, reason: collision with root package name */
    private final SideMenu.SideMenuContainer f3422b;
    private boolean c;
    private Table d;
    private Image e;
    private Image f;
    private Label g;
    private int h = 0;
    private GameSystemProvider i;

    /* renamed from: a, reason: collision with root package name */
    private static final Color f3421a = new Color(623191551);
    private static final StringBuilder j = new StringBuilder();
    private static final IntFloatMap k = new IntFloatMap();

    public TargetMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.i = gameSystemProvider;
        this.f3422b = sideMenu.createContainer("TargetMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Label label = new Label(Game.i.localeManager.i18n.get("tile_name_TARGET").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label.setSize(460.0f, 26.0f);
        label.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.f3422b.addActor(label);
        this.e = new Image(Game.i.assetManager.getDrawable("icon-ability"));
        this.e.setColor(MaterialColor.RED.P500);
        this.e.setSize(48.0f, 48.0f);
        this.e.setPosition(450.0f, 986.0f + scaledViewportHeight);
        this.e.setTouchable(Touchable.enabled);
        this.e.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.TargetMenu.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("abilities_not_available_on_map"));
            }
        });
        this.f3422b.addActor(this.e);
        this.f = new Image(Game.i.assetManager.getDrawable("icon-research"));
        this.f.setColor(MaterialColor.RED.P500);
        this.f.setSize(48.0f, 48.0f);
        this.f.setPosition(514.0f, 986.0f + scaledViewportHeight);
        this.f.setTouchable(Touchable.enabled);
        this.f.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.TargetMenu.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("map_not_affected_by_researches"));
            }
        });
        this.f3422b.addActor(this.f);
        Label label2 = new Label(Game.i.localeManager.i18n.get("tile_description_TARGET"), Game.i.assetManager.getLabelStyle(24));
        label2.setSize(420.0f, 100.0f);
        label2.setPosition(40.0f, 884.0f + scaledViewportHeight);
        label2.setAlignment(10);
        label2.setWrap(true);
        this.f3422b.addActor(label2);
        Table table = new Table();
        table.setSize(600.0f, 52.0f);
        table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(f3421a));
        table.setPosition(0.0f, 886.0f + scaledViewportHeight);
        this.f3422b.addActor(table);
        table.add((Table) new Label(Game.i.localeManager.i18n.get("base_menu_gained_green_papers"), Game.i.assetManager.getLabelStyle(24))).expandX().fillX().height(52.0f).padLeft(40.0f);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-money"));
        image.setColor(MaterialColor.GREEN.P500);
        table.add((Table) image).size(32.0f).padTop(10.0f).padBottom(10.0f).padRight(8.0f);
        this.g = new Label("0", Game.i.assetManager.getLabelStyle(24));
        this.g.setColor(MaterialColor.GREEN.P500);
        table.add((Table) this.g).height(52.0f).padRight(40.0f);
        Label label3 = new Label(Game.i.localeManager.i18n.get("effect").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
        label3.setSize(100.0f, 16.0f);
        label3.setPosition(40.0f, 846.0f + scaledViewportHeight);
        label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.f3422b.addActor(label3);
        Label label4 = new Label(Game.i.localeManager.i18n.get("ignores_researches_short").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
        label4.setSize(100.0f, 16.0f);
        label4.setAlignment(16);
        label4.setPosition(340.0f, 846.0f + scaledViewportHeight);
        label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.f3422b.addActor(label4);
        Label label5 = new Label(Game.i.localeManager.i18n.get("value_units").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
        label5.setSize(100.0f, 16.0f);
        label5.setPosition(460.0f, 846.0f + scaledViewportHeight);
        label5.setAlignment(16);
        label5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.f3422b.addActor(label5);
        this.d = new Table();
        ScrollPane scrollPane = new ScrollPane(this.d);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(600.0f, 830.0f + scaledViewportHeight);
        this.f3422b.addActor(scrollPane);
        sideMenu.addListener(new SideMenu.SideMenuListener.SideMenuListenerAdapter() { // from class: com.prineside.tdi2.ui.components.TargetMenu.3
            @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
            public void offscreenChanged() {
                TargetMenu.this.b();
            }
        });
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.TARGET) {
                b();
                a(true);
            } else {
                a(false);
            }
        });
        gameSystemProvider.events.getListeners(AbilityApply.class).add(abilityApply -> {
            if (this.c && (abilityApply.getAbility() instanceof OverloadAbility)) {
                b();
            }
        });
        gameSystemProvider.events.getListeners(EnemyReachTarget.class).add(enemyReachTarget -> {
            if (this.c) {
                b();
            }
        });
        this.f3422b.hide();
    }

    private void a() {
        int calculatePrizeGreenPapers = this.i.gameState.calculatePrizeGreenPapers();
        if (this.h != calculatePrizeGreenPapers) {
            this.g.setText(StringFormatter.commaSeparatedNumber(calculatePrizeGreenPapers));
            this.h = calculatePrizeGreenPapers;
        }
    }

    private void a(boolean z) {
        if (this.c != z) {
            this.c = z;
            if (z) {
                this.f3422b.show();
            } else {
                this.f3422b.hide();
            }
        }
    }

    private void a(Drawable drawable, String str, String str2) {
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(600.0f, 52.0f);
        image.setColor(f3421a);
        group.addActor(image);
        Image image2 = new Image(drawable);
        image2.setPosition(40.0f, 6.0f);
        image2.setSize(40.0f, 40.0f);
        group.addActor(image2);
        LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(str, 24, 21, 420.0f);
        limitedWidthLabel.setSize(300.0f, 52.0f);
        limitedWidthLabel.setPosition(96.0f, 0.0f);
        group.addActor(limitedWidthLabel);
        Label label = new Label(str2, Game.i.assetManager.getLabelStyle(24));
        label.setPosition(460.0f, 0.0f);
        label.setSize(100.0f, 52.0f);
        label.setAlignment(16);
        group.addActor(label);
        this.d.add((Table) group).size(600.0f, 52.0f).padBottom(4.0f).row();
    }

    private void a(final GameValueType gameValueType, double d, boolean z, boolean z2) {
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(600.0f, 52.0f);
        image.setColor(f3421a);
        group.addActor(image);
        Image image2 = new Image(Game.i.gameValueManager.getStockValueConfig(gameValueType).createIconForBackground(new Color(623191551)));
        image2.setPosition(40.0f, 6.0f);
        image2.setSize(40.0f, 40.0f);
        group.addActor(image2);
        LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(Game.i.gameValueManager.getTitle(gameValueType), 24, 21, 420.0f);
        limitedWidthLabel.setSize(300.0f, 52.0f);
        limitedWidthLabel.setPosition(96.0f, 0.0f);
        limitedWidthLabel.setTouchable(Touchable.enabled);
        limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.TargetMenu.4
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Dialog.i().showAlert(Game.i.gameValueManager.getTitle(gameValueType));
            }
        });
        group.addActor(limitedWidthLabel);
        if (!z) {
            Image image3 = new Image(Game.i.assetManager.getDrawable("icon-check"));
            image3.setSize(32.0f, 32.0f);
            image3.setPosition(414.0f, 10.0f);
            group.addActor(image3);
        }
        j.setLength(0);
        GameValueManager.ValueUnits valueUnits = Game.i.gameValueManager.getStockValueConfig(gameValueType).units;
        if (valueUnits != GameValueManager.ValueUnits.BOOLEAN) {
            j.append(Game.i.gameValueManager.formatEffectValue(d, valueUnits));
            if (z2) {
                j.setCharAt(0, '=');
            }
            if (j.length == 1) {
                j.append('0');
            }
        } else if (d == 0.0d) {
            j.append(Game.i.localeManager.i18n.get("disabled").toLowerCase(Locale.ENGLISH));
        }
        Label label = new Label(j, Game.i.assetManager.getLabelStyle(24));
        label.setPosition(460.0f, 0.0f);
        label.setSize(100.0f, 52.0f);
        label.setAlignment(16);
        group.addActor(label);
        this.d.add((Table) group).size(600.0f, 52.0f).padBottom(4.0f).row();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Tile selectedTile = this.i._gameMapSelection.getSelectedTile();
        this.f3422b.setLabelOverTitleTilePos(selectedTile);
        this.d.clearChildren();
        if (selectedTile != null && selectedTile.type == TileType.TARGET) {
            TargetTile targetTile = (TargetTile) selectedTile;
            this.e.setVisible(targetTile.isDisableAbilities());
            this.f.setVisible(targetTile.isUseStockGameValues());
            Array<GameValueConfig> gameValues = targetTile.getGameValues();
            if (gameValues.size != 0) {
                for (int i = 0; i < gameValues.size; i++) {
                    GameValueConfig gameValueConfig = gameValues.get(i);
                    a(gameValueConfig.getType(), gameValueConfig.getValue(), gameValueConfig.isAllowBonuses(), gameValueConfig.isOverwrite());
                }
            } else {
                Label label = new Label(Game.i.localeManager.i18n.get("base_has_no_effects"), Game.i.assetManager.getLabelStyle(24));
                label.setAlignment(1);
                this.d.add((Table) label).size(600.0f, 52.0f).padBottom(4.0f).row();
            }
            OverloadAbility overloadAbility = null;
            int i2 = 0;
            while (true) {
                if (i2 >= this.i.ability.activeAbilities.size) {
                    break;
                }
                Ability ability = this.i.ability.activeAbilities.get(i2);
                if (!(ability instanceof OverloadAbility)) {
                    i2++;
                } else {
                    overloadAbility = (OverloadAbility) ability;
                    break;
                }
            }
            if (overloadAbility != null) {
                Table table = new Table();
                this.d.add(table).size(600.0f, 52.0f).padBottom(4.0f).row();
                Label label2 = new Label(Game.i.localeManager.i18n.get("ability_name_OVERLOAD"), Game.i.assetManager.getLabelStyle(24));
                label2.setColor(MaterialColor.DEEP_ORANGE.P500);
                table.add((Table) label2).padRight(16.0f);
                Image image = new Image(Game.i.assetManager.getDrawable("icon-overload"));
                image.setColor(MaterialColor.DEEP_ORANGE.P500);
                table.add((Table) image).size(32.0f).padRight(4.0f);
                Label label3 = new Label("x" + overloadAbility.getLevel(), Game.i.assetManager.getLabelStyle(24));
                label3.setColor(MaterialColor.DEEP_ORANGE.P500);
                table.add((Table) label3);
                a(Game.i.assetManager.getDrawable("icon-enemy-heart"), Game.i.localeManager.i18n.get("base_menu_overload_toughness"), StringFormatter.commaSeparatedNumber(MathUtils.round((overloadAbility.getDifficulty() * 100.0f) + 100.0f)).toString() + "%");
                a(Game.i.assetManager.getDrawable("icon-pickaxe"), Game.i.localeManager.i18n.get("mining_speed"), StringFormatter.commaSeparatedNumber(MathUtils.round((overloadAbility.getMiningSpeed() * 100.0f) + 100.0f)).toString() + "%");
            }
            if (this.i.gameState.basicLevelName != null) {
                k.clear();
                BasicLevel level = Game.i.basicLevelManager.getLevel(this.i.gameState.basicLevelName);
                for (int i3 = 0; i3 < level.quests.size; i3++) {
                    BasicLevelQuestConfig basicLevelQuestConfig = level.quests.items[i3];
                    if (basicLevelQuestConfig.wasEverCompleted()) {
                        for (int i4 = 0; i4 < basicLevelQuestConfig.prizes.size; i4++) {
                            ItemStack itemStack = basicLevelQuestConfig.prizes.items[i4];
                            if (itemStack.getItem().getType() == ItemType.GAME_VALUE_LEVEL) {
                                GameValueLevelItem gameValueLevelItem = (GameValueLevelItem) itemStack.getItem();
                                k.put(gameValueLevelItem.gameValueType.ordinal(), k.get(gameValueLevelItem.gameValueType.ordinal(), 0.0f) + ((float) gameValueLevelItem.delta));
                            }
                        }
                    }
                }
                for (int i5 = 0; i5 < level.waveQuests.size; i5++) {
                    BasicLevel.WaveQuest waveQuest = level.waveQuests.items[i5];
                    if (waveQuest.isCompleted()) {
                        for (int i6 = 0; i6 < waveQuest.prizes.size; i6++) {
                            ItemStack itemStack2 = waveQuest.prizes.items[i6];
                            if (itemStack2.getItem().getType() == ItemType.GAME_VALUE_LEVEL) {
                                GameValueLevelItem gameValueLevelItem2 = (GameValueLevelItem) itemStack2.getItem();
                                k.put(gameValueLevelItem2.gameValueType.ordinal(), k.get(gameValueLevelItem2.gameValueType.ordinal(), 0.0f) + ((float) gameValueLevelItem2.delta));
                            }
                        }
                    }
                }
                if (!k.isEmpty()) {
                    Label label4 = new Label(Game.i.localeManager.i18n.get("quests"), Game.i.assetManager.getLabelStyle(24));
                    label4.setAlignment(1);
                    label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    this.d.add((Table) label4).size(600.0f, 52.0f).padBottom(4.0f).row();
                    Iterator<IntFloatMap.Entry> it = k.iterator();
                    while (it.hasNext()) {
                        a(GameValueType.values[it.next().key], r0.value, true, false);
                    }
                }
            }
        }
        this.d.add().expandX().fillX().height(40.0f).row();
        this.d.add().expand().fill();
        a();
    }

    public void draw(float f) {
        if (this.c) {
            a();
        }
    }
}
