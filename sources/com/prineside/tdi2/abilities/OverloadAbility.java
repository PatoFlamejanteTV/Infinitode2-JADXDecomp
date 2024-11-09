package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.managers.RenderingManager;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/OverloadAbility.class */
public class OverloadAbility extends Ability {

    /* renamed from: b, reason: collision with root package name */
    private static final TLog f1806b = TLog.forClass(OverloadAbility.class);
    private static final int[] c = {150, User32.VK_PLAY, 350, 500, 750, 1200, Config.DISPLAY_WIDTH, 2200, 3400, 5000, CGL.kCGLBadAttribute};
    private static final int[][] d = {new int[]{15, 40, 100, 0, 0, 0, 0, 0, 0, 0, 200}, new int[]{0, 0, 20, 50, 120, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 30, 80, 150, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 20, 60, 130, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 40, 110, 200}};
    public static final BuffType[] AFFECTED_DEBUFFS = {BuffType.SLIPPING, BuffType.STUN, BuffType.SNOWBALL, BuffType.BLIZZARD, BuffType.THROW_BACK};
    private int e;
    private GameValueConfig f;
    private GameValueConfig g;

    @Null
    private OnEnemyReachTarget h;
    private boolean i;

    /* synthetic */ OverloadAbility(byte b2) {
        this();
    }

    static /* synthetic */ int a(OverloadAbility overloadAbility) {
        int i = overloadAbility.e;
        overloadAbility.e = i - 1;
        return i;
    }

    static /* synthetic */ boolean a(OverloadAbility overloadAbility, boolean z) {
        overloadAbility.i = true;
        return true;
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.e, true);
        kryo.writeObject(output, this.f);
        kryo.writeObject(output, this.g);
        kryo.writeObjectOrNull(output, this.h, OnEnemyReachTarget.class);
        output.writeBoolean(this.i);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readVarInt(true);
        this.f = (GameValueConfig) kryo.readObject(input, GameValueConfig.class);
        this.g = (GameValueConfig) kryo.readObject(input, GameValueConfig.class);
        this.h = (OnEnemyReachTarget) kryo.readObjectOrNull(input, OnEnemyReachTarget.class);
        this.i = input.readBoolean();
    }

    private OverloadAbility() {
        super(AbilityType.OVERLOAD);
        this.e = 1;
        this.f = new GameValueConfig(GameValueType.ENEMIES_VULNERABILITY, 0.0d, false, true);
        this.g = new GameValueConfig(GameValueType.MINERS_SPEED, 0.0d, false, true);
    }

    @Override // com.prineside.tdi2.Ability
    public void onDone() {
        f1806b.i("onDone", new Object[0]);
        this.S.gameValue.removeCustomGameValue(this.f);
        this.S.gameValue.removeCustomGameValue(this.g);
        if (this.h != null) {
            this.S.events.getListeners(EnemyReachTarget.class).remove(this.h);
            this.h = null;
        }
        this.S.gameValue.recalculate();
    }

    public int getLevel() {
        return this.e;
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
        RenderingManager.prepareBatch(batch, false);
        TargetTile targetTileOrThrow = this.S.map.getMap().getTargetTileOrThrow();
        float x = (targetTileOrThrow.getX() << 7) + 32.0f;
        float y = (targetTileOrThrow.getY() << 7) + 38.4f;
        float f2 = -2.0f;
        while (true) {
            float f3 = f2;
            if (f3 <= 2.0f) {
                float f4 = -2.0f;
                while (true) {
                    float f5 = f4;
                    if (f5 <= 2.0f) {
                        if (f3 != 0.0f || f5 != 0.0f) {
                            batch.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                            batch.draw(Game.i.assetManager.getTextureRegion("icon-overload"), x + 6.4f + f3, y + 6.4f + f5, 51.2f, 51.2f);
                            ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(24);
                            font.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                            font.draw(batch, new StringBuilder().append(this.e).toString(), x + 24.0f + f3, y + 18.0f + f5, 51.2f, 1, false);
                            font.setColor(Color.WHITE);
                        }
                        f4 = f5 + 1.0f;
                    }
                }
                f2 = f3 + 1.0f;
            } else {
                batch.setColor(MaterialColor.DEEP_ORANGE.P500);
                batch.draw(Game.i.assetManager.getTextureRegion("icon-overload"), x + 6.4f, y + 6.4f, 51.2f, 51.2f);
                ResourcePack.ResourcePackBitmapFont font2 = Game.i.assetManager.getFont(24);
                font2.setColor(MaterialColor.DEEP_ORANGE.P500);
                font2.draw(batch, new StringBuilder().append(this.e).toString(), x + 24.0f, y + 18.0f, 51.2f, 1, false);
                font2.setColor(Color.WHITE);
                return;
            }
        }
    }

    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d2) {
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        for (int i = 0; i < this.S.ability.activeAbilities.size; i++) {
            Ability ability = this.S.ability.activeAbilities.items[i];
            if (ability.getType() == AbilityType.OVERLOAD) {
                OverloadAbility overloadAbility = (OverloadAbility) ability;
                overloadAbility.e++;
                overloadAbility.c();
                this.i = true;
                return true;
            }
        }
        this.S.gameValue.addCustomGameValue(this.f);
        this.S.gameValue.addCustomGameValue(this.g);
        this.h = new OnEnemyReachTarget(this, (byte) 0);
        this.S.events.getListeners(EnemyReachTarget.class).addStateAffecting(this.h);
        c();
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void startEffects() {
        a(1.5f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        float difficulty = getDifficulty() * f;
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy.EnemyReference enemyReference = this.S.map.spawnedEnemies.items[i];
            if (enemyReference.enemy != null) {
                Enemy enemy = enemyReference.enemy;
                for (BuffType buffType : AFFECTED_DEBUFFS) {
                    DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(buffType);
                    if (buffsByTypeOrNull != null) {
                        for (int i2 = 0; i2 < buffsByTypeOrNull.size; i2++) {
                            ((Buff) buffsByTypeOrNull.get(i2)).duration -= difficulty;
                        }
                    }
                }
            }
        }
    }

    public float getDifficulty() {
        return ((float) StrictMath.pow(((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_OVERLOAD_DIFFICULTY)) + 1.0f, this.e)) - 1.0f;
    }

    public float getMiningSpeed() {
        float pow = ((float) StrictMath.pow(((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_OVERLOAD_MINING_SPEED)) + 1.0f, this.e)) - 1.0f;
        if (DifficultyMode.isEndless(this.S.gameState.difficultyMode)) {
            pow *= (((this.S.gameState.modeDifficultyMultiplier / 100.0f) - 1.0f) * this.S.gameValue.getFloatValue(GameValueType.ABILITY_OVERLOAD_BONUS_PER_DIFFICULTY)) + 1.0f;
        }
        return pow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        float difficulty = getDifficulty();
        this.g.setValue(getMiningSpeed() + 1.0f);
        this.g.setFinalGlobalMultiplier(true);
        this.f.setValue(1.0f / (difficulty + 1.0f));
        this.f.setFinalGlobalMultiplier(true);
        this.S.gameValue.recalculate();
        f1806b.d("miningSpeedGV %s", Double.valueOf(this.g.getValue()));
        f1806b.d("enemiesVulnerabilityGV %s", Double.valueOf(this.f.getValue()));
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.i;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/OverloadAbility$OverloadAbilityFactory.class */
    public static class OverloadAbilityFactory extends Ability.Factory<OverloadAbility> {
        public OverloadAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public OverloadAbility create() {
            return new OverloadAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.DEEP_ORANGE.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_OVERLOAD", StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.ABILITY_OVERLOAD_MINING_SPEED), 2, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.ABILITY_OVERLOAD_DIFFICULTY), 2, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.ABILITY_OVERLOAD_BONUS_PER_DIFFICULTY), 2, true).toString());
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.DEEP_ORANGE.P800;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return OverloadAbility.c[StrictMath.min(i, OverloadAbility.c.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return OverloadAbility.d[resourceType.ordinal()][StrictMath.min(i, OverloadAbility.d[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-overload");
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/OverloadAbility$OnEnemyReachTarget.class */
    public static final class OnEnemyReachTarget extends SerializableListener<OverloadAbility> implements Listener<EnemyReachTarget> {
        /* synthetic */ OnEnemyReachTarget(OverloadAbility overloadAbility, byte b2) {
            this(overloadAbility);
        }

        private OnEnemyReachTarget() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnEnemyReachTarget(OverloadAbility overloadAbility) {
            this.f1759a = overloadAbility;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyReachTarget enemyReachTarget) {
            if (enemyReachTarget.getEnemy().getCurrentTile() instanceof TargetTile) {
                OverloadAbility.a((OverloadAbility) this.f1759a);
                if (((OverloadAbility) this.f1759a).e != 0) {
                    ((OverloadAbility) this.f1759a).c();
                } else {
                    OverloadAbility.a((OverloadAbility) this.f1759a, true);
                }
            }
        }
    }
}
