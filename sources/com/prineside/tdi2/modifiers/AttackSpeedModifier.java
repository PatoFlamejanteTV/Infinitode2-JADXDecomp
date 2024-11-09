package com.prineside.tdi2.modifiers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/AttackSpeedModifier.class */
public final class AttackSpeedModifier extends Modifier {
    /* synthetic */ AttackSpeedModifier(byte b2) {
        this();
    }

    private AttackSpeedModifier() {
        super(ModifierType.ATTACK_SPEED);
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean connectsToMiners() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/AttackSpeedModifier$AttackSpeedModifierFactory.class */
    public static class AttackSpeedModifierFactory extends Modifier.Factory<AttackSpeedModifier> {
        private TextureRegion c;

        public AttackSpeedModifierFactory() {
            super(ModifierType.ATTACK_SPEED, MaterialColor.AMBER.P500, "icon-attack-speed");
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("modifier_description_ATTACK_SPEED", Float.valueOf(MathUtils.round((float) (gameValueProvider.getPercentValueAsMultiplier(GameValueType.MODIFIER_ATTACK_SPEED_VALUE) * 1000.0d)) * 0.1f));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            return a((int) (100.0f * ((float) StrictMath.pow(1.5d, i))));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-attack-speed");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public AttackSpeedModifier create() {
            return new AttackSpeedModifier((byte) 0);
        }
    }
}
