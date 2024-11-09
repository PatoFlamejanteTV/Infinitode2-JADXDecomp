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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/DamageModifier.class */
public final class DamageModifier extends Modifier {
    /* synthetic */ DamageModifier(byte b2) {
        this();
    }

    private DamageModifier() {
        super(ModifierType.DAMAGE);
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean connectsToMiners() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/DamageModifier$DamageModifierFactory.class */
    public static class DamageModifierFactory extends Modifier.Factory<DamageModifier> {
        private TextureRegion c;

        public DamageModifierFactory() {
            super(ModifierType.DAMAGE, MaterialColor.RED.P500, "icon-damage");
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            return a((int) (100.0f * ((float) StrictMath.pow(1.5d, i))));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("modifier_description_DAMAGE", Float.valueOf(MathUtils.round((float) (gameValueProvider.getPercentValueAsMultiplier(GameValueType.MODIFIER_DAMAGE_VALUE) * 1000.0d)) * 0.1f));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-damage");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public DamageModifier create() {
            return new DamageModifier((byte) 0);
        }
    }
}
