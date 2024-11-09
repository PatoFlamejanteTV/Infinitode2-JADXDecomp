package com.prineside.tdi2.modifiers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/MiningSpeedModifier.class */
public final class MiningSpeedModifier extends Modifier {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2598a = TLog.forClass(MiningSpeedModifier.class);

    /* synthetic */ MiningSpeedModifier(byte b2) {
        this();
    }

    private MiningSpeedModifier() {
        super(ModifierType.MINING_SPEED);
    }

    @Override // com.prineside.tdi2.Modifier
    public final void fillModifierMenu(Group group, ObjectMap<String, Object> objectMap) {
        if (objectMap.size == 0 || objectMap.get("modifier_menu_hint", null) == null) {
            int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
            group.clear();
            Table table = new Table();
            table.setPosition(40.0f, 80.0f);
            table.setSize(520.0f, scaledViewportHeight + 600);
            group.addActor(table);
            Label label = new Label(Game.i.localeManager.i18n.get("mining_speed_mods_tooltip"), Game.i.assetManager.getLabelStyle(24));
            label.setWrap(true);
            table.add((Table) label).width(520.0f).row();
            Table createEfficiencyTable = createEfficiencyTable(this.S, -1);
            table.add(createEfficiencyTable).width(520.0f).padTop(8.0f).row();
            table.add().width(1.0f).growY();
            f2598a.i("recreate custom menu", new Object[0]);
            objectMap.put("modifier_menu_hint", label);
            objectMap.put("modifier_menu_table", createEfficiencyTable);
        }
    }

    public static Table createEfficiencyTable(GameSystemProvider gameSystemProvider, int i) {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Game.i.assetManager.getLabelStyle(18));
        labelStyle.background = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        labelStyle.background.setLeftWidth(5.0f);
        labelStyle.background.setRightWidth(5.0f);
        labelStyle.background.setTopHeight(2.0f);
        labelStyle.background.setBottomHeight(2.0f);
        Table table = new Table();
        for (int i2 = 1; i2 <= 8; i2++) {
            Label label = new Label(String.valueOf(i2), labelStyle);
            if (i2 <= i) {
                label.setColor(MaterialColor.LIGHT_GREEN.P400);
            }
            label.setAlignment(1);
            table.add((Table) label).growX().padLeft(1.0f).padRight(1.0f);
        }
        table.row();
        float f = 0.0f;
        for (int i3 = 1; i3 <= 8; i3++) {
            float miningSpeedModifierEfficiencyPerCount = gameSystemProvider.miner.getMiningSpeedModifierEfficiencyPerCount(i3);
            float f2 = miningSpeedModifierEfficiencyPerCount - f;
            f = miningSpeedModifierEfficiencyPerCount;
            Label label2 = new Label(StringFormatter.compactNumber(f2 * 100.0f, false).append("%"), labelStyle);
            if (i3 <= i) {
                label2.setColor(MaterialColor.LIGHT_GREEN.P400);
            }
            label2.setAlignment(1);
            table.add((Table) label2).growX().padLeft(1.0f).padRight(1.0f).padTop(2.0f);
        }
        return table;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/MiningSpeedModifier$MiningSpeedModifierFactory.class */
    public static class MiningSpeedModifierFactory extends Modifier.Factory<MiningSpeedModifier> {
        private TextureRegion c;

        public MiningSpeedModifierFactory() {
            super(ModifierType.MINING_SPEED, MaterialColor.TEAL.P500, "icon-pickaxe");
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("modifier_description_MINING_SPEED", Float.valueOf(MathUtils.round((float) (gameValueProvider.getPercentValueAsMultiplier(GameValueType.MODIFIER_MINING_SPEED_VALUE) * 1000.0d)) * 0.1f));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            return a((int) (120.0f * ((float) StrictMath.pow(1.600000023841858d, i * 1.05d))));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-mining-speed");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public MiningSpeedModifier create() {
            return new MiningSpeedModifier((byte) 0);
        }
    }
}
