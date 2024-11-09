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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/SearchModifier.class */
public final class SearchModifier extends Modifier {
    /* synthetic */ SearchModifier(byte b2) {
        this();
    }

    private SearchModifier() {
        super(ModifierType.SEARCH);
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean connectsToMiners() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/SearchModifier$SearchModifierFactory.class */
    public static class SearchModifierFactory extends Modifier.Factory<SearchModifier> {
        private TextureRegion c;

        public SearchModifierFactory() {
            super(ModifierType.SEARCH, MaterialColor.BLUE.P500, "icon-eye");
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            return a((int) (50.0f * ((float) StrictMath.pow(1.399999976158142d, i))));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("modifier_description_SEARCH", Float.valueOf(MathUtils.round((float) (gameValueProvider.getPercentValueAsMultiplier(GameValueType.MODIFIER_SEARCH_RANGE_VALUE) * 1000.0d)) * 0.1f));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-search");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public SearchModifier create() {
            return new SearchModifier((byte) 0);
        }
    }
}
