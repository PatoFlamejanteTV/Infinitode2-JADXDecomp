package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.towers.FreezingTower;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/LoopAbility.class */
public class LoopAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1801b;
    private static final int[][] c;
    private int d;
    private int e;
    private float f;
    private float g;

    /* synthetic */ LoopAbility(byte b2) {
        this();
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [int[], int[][]] */
    static {
        TLog.forClass(LoopAbility.class);
        f1801b = new int[]{200, User32.VK_PLAY, GLFW.GLFW_KEY_LEFT_SHIFT, 500, 700, 900, 1200, 1500, 1900, 2400, EventListeners.PRIORITY_HIGHEST};
        c = new int[]{new int[]{10, 20, 45, 80, 120, 180, User32.VK_OEM_ATTN, 300, 360, HttpStatus.SC_METHOD_FAILURE, 500}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.d);
        output.writeInt(this.e);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readInt();
        this.e = input.readInt();
        this.f = input.readFloat();
        this.g = input.readFloat();
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        this.f = (float) (d * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_LOOP_DAMAGE_MULTIPLIER));
        this.g = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_LOOP_RESOURCE_AMOUNT);
        this.d = (int) (i * 0.0078125f);
        this.e = (int) (i2 * 0.0078125f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        if (this.f <= 0.0f) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_zero_damage"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        int[] iArr = {this.d, this.e, this.d - 1, this.e, this.d + 1, this.e, this.d, this.e + 1, this.d, this.e - 1};
        Array array = new Array();
        for (int i = 0; i < 10; i += 2) {
            Tile tile = this.S.map.getMap().getTile(iArr[i], iArr[i + 1]);
            if (!(tile instanceof PlatformTile)) {
                if (tile instanceof SourceTile) {
                    SourceTile sourceTile = (SourceTile) tile;
                    if (sourceTile.miner != null) {
                        array.add(sourceTile);
                    }
                }
            } else {
                PlatformTile platformTile = (PlatformTile) tile;
                if ((platformTile.building instanceof Tower) && !(((Tower) platformTile.building) instanceof FreezingTower)) {
                    array.add(platformTile);
                }
            }
        }
        if (array.size == 0) {
            if (this.S._gameUi != null) {
                Notifications.i().add(Game.i.localeManager.i18n.get("ability_cant_start_affects_nothing"), Game.i.assetManager.getDrawable("icon-ability"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                return false;
            }
            return false;
        }
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            Tile tile2 = (Tile) it.next();
            if (!(tile2 instanceof PlatformTile)) {
                if (tile2 instanceof SourceTile) {
                    Miner miner = ((SourceTile) tile2).miner;
                    int i2 = 0;
                    for (int i3 = 0; i3 < miner.minedResources.length; i3++) {
                        i2 += miner.minedResources[i3];
                    }
                    miner.loopAbilityResourceBuffer = (int) (i2 * this.g);
                    miner.affectedByLoopAbility = this;
                }
            } else {
                Tower tower = (Tower) ((PlatformTile) tile2).building;
                tower.loopAbilityDamageBuffer = Math.max(tower.loopAbilityDamageBuffer, this.f);
                tower.affectedByLoopAbility = this;
            }
        }
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            Array.ArrayIterator it2 = array.iterator();
            while (it2.hasNext()) {
                Tile tile3 = (Tile) it2.next();
                ParticleEffectPool.PooledEffect obtain = Game.i.assetManager.getParticleEffectPool("building-loop.p").obtain();
                this.S._particle.addParticle(obtain, false);
                obtain.setPosition(tile3.center.x, tile3.center.y);
            }
            return true;
        }
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
    }

    private LoopAbility() {
        super(AbilityType.LOOP);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/LoopAbility$RepeatAbilityFactory.class */
    public static class RepeatAbilityFactory extends Ability.Factory<LoopAbility> {
        public RepeatAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public LoopAbility create() {
            return new LoopAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return true;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.GREEN.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_LOOP", StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.ABILITY_LOOP_DAMAGE_MULTIPLIER), 1, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.ABILITY_LOOP_RESOURCE_AMOUNT), 1, true).toString());
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.GREEN.P700;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getTitle() {
            return Game.i.localeManager.i18n.get("ability_name_LOOP");
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return LoopAbility.f1801b[StrictMath.min(i, LoopAbility.f1801b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return LoopAbility.c[resourceType.ordinal()][StrictMath.min(i, LoopAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-loop");
        }
    }
}
