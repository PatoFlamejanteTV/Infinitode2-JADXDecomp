package com.prineside.tdi2.modifiers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.ModifierProcessor;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.modifiers.processors.BountyModifierProcessor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/BountyModifier.class */
public final class BountyModifier extends Modifier {
    public static final float ABILITY_BOOST_MULTIPLIER = 1.3f;
    public boolean boostedByAbility;
    public int coinsGained;

    @NAGS
    private ParticleEffectPool.PooledEffect c;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2593a = TLog.forClass(BountyModifier.class);

    /* renamed from: b, reason: collision with root package name */
    private static final StringBuilder f2594b = new StringBuilder();

    /* synthetic */ BountyModifier(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.boostedByAbility);
        output.writeVarInt(this.coinsGained, true);
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.boostedByAbility = input.readBoolean();
        this.coinsGained = input.readVarInt(true);
    }

    private BountyModifier() {
        super(ModifierType.BOUNTY);
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean connectsToMiners() {
        return false;
    }

    @Override // com.prineside.tdi2.Modifier
    public final void drawBatch(Batch batch, float f, MapRenderingSystem.DrawMode drawMode) {
        super.drawBatch(batch, f, drawMode);
        if (this.boostedByAbility) {
            if (this.c == null && this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                this.c = Game.i.assetManager.getParticleEffectPool("bounty-modifier-boost.prt").obtain();
                this.c.setPosition(getTile().center.x, getTile().center.y);
                this.S._particle.addParticle(this.c, false);
                return;
            }
            return;
        }
        if (this.c != null) {
            this.c.allowCompletion();
            this.c = null;
        }
    }

    @Override // com.prineside.tdi2.Building
    public final void removedFromMap() {
        super.removedFromMap();
        if (this.c != null) {
            this.c.allowCompletion();
            this.c = null;
        }
    }

    @Override // com.prineside.tdi2.Modifier
    public final void fillModifierMenu(Group group, ObjectMap<String, Object> objectMap) {
        int i = 1;
        if (this.boostedByAbility) {
            i = 32;
        }
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        if (objectMap.size == 0 || !objectMap.get("state", -1).equals(Integer.valueOf(i))) {
            f2593a.i("recreate custom menu", new Object[0]);
            group.clear();
            Group group2 = new Group();
            group2.setTransform(false);
            group2.setPosition(0.0f, 606.0f + scaledViewportHeight);
            group2.setSize(600.0f, 48.0f);
            group.addActor(group2);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setSize(600.0f, 48.0f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            group2.addActor(image);
            Label label = new Label(Game.i.localeManager.i18n.get("statistics_CG_PG"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            label.setSize(100.0f, 48.0f);
            label.setPosition(40.0f, 0.0f);
            group2.addActor(label);
            Label label2 = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
            label2.setSize(100.0f, 48.0f);
            label2.setPosition(460.0f, 0.0f);
            label2.setAlignment(16);
            group2.addActor(label2);
            Group group3 = new Group();
            group3.setTransform(false);
            group3.setPosition(0.0f, ((606.0f + scaledViewportHeight) - 48.0f) - 4.0f);
            group3.setSize(600.0f, 48.0f);
            group.addActor(group3);
            Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image2.setSize(600.0f, 48.0f);
            image2.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            group3.addActor(image2);
            Label label3 = new Label(Game.i.localeManager.i18n.get("efficiency"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            label3.setSize(100.0f, 48.0f);
            label3.setPosition(40.0f, 0.0f);
            group3.addActor(label3);
            Label label4 = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
            label4.setSize(100.0f, 48.0f);
            label4.setPosition(460.0f, 0.0f);
            label4.setAlignment(16);
            group3.addActor(label4);
            if (this.boostedByAbility) {
                Label label5 = new Label(Game.i.localeManager.i18n.format("bounty_boosted_by_ability", Integer.valueOf(MathUtils.round(130.0f))), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                label5.setSize(100.0f, 48.0f);
                label5.setColor(MaterialColor.LIGHT_GREEN.P500);
                label5.setPosition(40.0f, -48.0f);
                group3.addActor(label5);
            }
            objectMap.put("bonusCoinsLabel", label2);
            objectMap.put("efficiencyLabel", label4);
            objectMap.put("state", Integer.valueOf(i));
        }
        Label label6 = (Label) objectMap.get("bonusCoinsLabel");
        Label label7 = (Label) objectMap.get("efficiencyLabel");
        label6.setTextFromInt(this.coinsGained);
        int ceil = MathUtils.ceil(this.S.gameValue.getIntValue(GameValueType.MODIFIER_BOUNTY_VALUE) / ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_BOUNTY_PERCENT)));
        int money = this.S.gameState.getMoney();
        int floor = MathUtils.floor((money / ceil) * 100.0f);
        if (money >= ceil) {
            floor = 100;
        }
        f2594b.setLength(0);
        f2594b.append(money).append(" / ").append(ceil).append(" = ").append(floor).append("%");
        label7.setText(f2594b);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/BountyModifier$BountyModifierFactory.class */
    public static class BountyModifierFactory extends Modifier.Factory<BountyModifier> {
        private TextureRegion c;

        public BountyModifierFactory() {
            super(ModifierType.BOUNTY, MaterialColor.AMBER.P500, "icon-coin");
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public ModifierProcessor createProcessor() {
            return new BountyModifierProcessor();
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            String format = Game.i.localeManager.i18n.format("modifier_description_BOUNTY", Float.valueOf(MathUtils.round(((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.MODIFIER_BOUNTY_PERCENT)) * 1000.0f) * 0.1f), Integer.valueOf(gameValueProvider.getIntValue(GameValueType.MODIFIER_BOUNTY_VALUE)));
            if (!gameValueProvider.getBooleanValue(GameValueType.MODIFIER_BOUNTY_NEIGHBORING)) {
                format = format + "\n[#FFC107]" + Game.i.localeManager.i18n.get("modifier_cant_be_placed_near") + "[]";
            }
            if (!gameValueProvider.getBooleanValue(GameValueType.MODIFIER_BOUNTY_NO_HARM_TO_TOWERS)) {
                format = format + "\n[#FFC107]" + Game.i.localeManager.i18n.get("nearby_towers_dont_gain_coins") + "[]";
            }
            return format;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public boolean canBePlacedNear(ModifierType modifierType, GameValueProvider gameValueProvider) {
            if (gameValueProvider.getBooleanValue(GameValueType.MODIFIER_BOUNTY_NEIGHBORING)) {
                return super.canBePlacedNear(modifierType, gameValueProvider);
            }
            return modifierType != ModifierType.BOUNTY;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            float f = 1.0f;
            if (gameSystemProvider.gameState.averageDifficulty > 100) {
                f = 1.0f + ((gameSystemProvider.gameState.averageDifficulty - 100) * 0.01f * 0.5f);
            }
            return a((int) (180.0f * ((float) StrictMath.pow(1.600000023841858d, i * 1.15f)) * f));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-bounty");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public BountyModifier create() {
            return new BountyModifier((byte) 0);
        }
    }
}
