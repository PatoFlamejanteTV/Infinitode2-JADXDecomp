package com.prineside.tdi2.modifiers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import java.util.Comparator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/PowerModifier.class */
public final class PowerModifier extends Modifier {

    /* renamed from: a, reason: collision with root package name */
    private float f2599a;

    /* renamed from: b, reason: collision with root package name */
    private static final Comparator<Tower> f2600b = (tower, tower2) -> {
        return Float.compare(tower2.experience, tower.experience);
    };

    /* synthetic */ PowerModifier(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2599a);
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2599a = input.readFloat();
    }

    private PowerModifier() {
        super(ModifierType.POWER);
    }

    @Override // com.prineside.tdi2.Modifier
    public final boolean connectsToMiners() {
        return false;
    }

    @Override // com.prineside.tdi2.Modifier
    public final void update(float f) {
        super.update(f);
        this.f2599a += f;
        if (this.f2599a >= 1.0f) {
            this.f2599a -= 1.0f;
            Array<Tower> towerArray = this.S.TSH.getTowerArray();
            this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                if (tile instanceof PlatformTile) {
                    PlatformTile platformTile = (PlatformTile) tile;
                    if (platformTile.building instanceof Tower) {
                        towerArray.add((Tower) platformTile.building);
                        return true;
                    }
                    return true;
                }
                return true;
            });
            float f2 = towerArray.size;
            this.S.TSH.sort.sort(towerArray, f2600b);
            for (int i = 0; i < towerArray.size; i++) {
                Tower tower = towerArray.get(i);
                if (tower.isRegistered()) {
                    float min = StrictMath.min(f2, tower.currentLevelExperience);
                    if (min != 0.0f) {
                        f2 -= min;
                        this.S.experience.removeExperienceRaw(tower, min);
                        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                            for (int i2 = 0; i2 < min; i2++) {
                                this.S._particle.addOrbParticle(Game.i.modifierManager.F.POWER.d, 12.0f, tower.getTile().getX(), tower.getTile().getY(), getTile().getX(), getTile().getY());
                            }
                        }
                        if (f2 == 0.0f) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
            this.S.TSH.freeTowerArray(towerArray);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/PowerModifier$PowerModifierFactory.class */
    public static class PowerModifierFactory extends Modifier.Factory<PowerModifier> {
        private TextureRegion c;
        private TextureRegion d;

        public PowerModifierFactory() {
            super(ModifierType.POWER, MaterialColor.PINK.P500, "icon-power");
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            return a((int) (100.0f * ((float) StrictMath.pow(1.5d, i))));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("modifier_description_POWER", Float.valueOf(MathUtils.round((float) (gameValueProvider.getPercentValueAsMultiplier(GameValueType.MODIFIER_POWER_VALUE) * 1000.0d)) * 0.1f));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-power");
            this.d = Game.i.assetManager.getTextureRegion("xp-orb");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public PowerModifier create() {
            return new PowerModifier((byte) 0);
        }
    }
}
