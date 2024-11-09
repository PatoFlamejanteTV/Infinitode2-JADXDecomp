package com.prineside.tdi2.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.buffs.BlizzardBuff;
import com.prineside.tdi2.buffs.FreezingBuff;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.tiles.RoadTile;
import com.prineside.tdi2.ui.actors.ImageWithParentColor;
import com.prineside.tdi2.units.IceFieldUnit;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/BlizzardAbility.class */
public class BlizzardAbility extends Ability implements Listener<EnemySpawn> {

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f1791b = {100, 125, 150, 175, User32.VK_PLAY, 300, 400, User32.WM_MDITILE, 750, 875, 1000};
    private static final int[][] c = {new int[]{5, 10, 25, 0, 0, 0, 0, 0, 0, 0, 300}, new int[]{0, 0, 5, 10, 30, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 10, 20, 50, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 10, 25, 45, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 10, 25, 75, 100}};
    public static final int ICE_FIELD_LIFETIME_MIN = 585;
    public static final int ICE_FIELD_LIFETIME_MAX = 615;
    public static final int ICE_FIELD_COUNT = 8;
    public static final int ICE_FIELD_MAX_TOUCHES = 20;
    private float d;
    private Array<ObjectPair<Enemy, BlizzardBuff>> e;
    private Array<IceFieldUnit> f;

    @NAGS
    private Group g;

    @NAGS
    private ParticleEffectPool.PooledEffect h;

    @NAGS
    private ParticleEffectPool.PooledEffect i;

    /* synthetic */ BlizzardAbility(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.f);
    }

    @Override // com.prineside.tdi2.Ability, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readFloat();
        this.e = (Array) kryo.readObject(input, Array.class);
        this.f = (Array) kryo.readObject(input, Array.class);
    }

    private BlizzardAbility() {
        super(AbilityType.BLIZZARD);
        this.e = new Array<>(true, 1, ObjectPair.class);
        this.f = new Array<>(true, 1, IceFieldUnit.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v54, types: [S, java.lang.Long] */
    @Override // com.prineside.tdi2.Ability
    public void configure(int i, int i2, double d) {
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.ABILITY_BLIZZARD_DURATION);
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ABILITY_BLIZZARD_BONUS_DAMAGE);
        this.d = floatValue;
        this.e.clear();
        this.S.map.spawnedEnemies.begin();
        for (int i3 = 0; i3 < this.S.map.spawnedEnemies.size; i3++) {
            Enemy enemy = this.S.map.spawnedEnemies.items[i3].enemy;
            if (enemy != null) {
                BlizzardBuff blizzardBuff = new BlizzardBuff();
                blizzardBuff.setup(floatValue, floatValue * 10.0f, percentValueAsMultiplier);
                this.e.add(new ObjectPair<>(enemy, blizzardBuff));
            }
        }
        this.S.map.spawnedEnemies.end();
        Map map = this.S.map.getMap();
        IntSet intSet = new IntSet();
        for (int i4 = 0; i4 < this.S.map.spawnedUnits.size; i4++) {
            Unit unit = this.S.map.spawnedUnits.get(i4);
            if (unit instanceof IceFieldUnit) {
                intSet.add((Map.posToCell(unit.position.y) * 48) + Map.posToCell(unit.position.x));
            }
        }
        long j = 0;
        Array array = new Array(true, 1, ObjectPair.class);
        Array tilesByType = map.getTilesByType(RoadTile.class);
        for (int i5 = 0; i5 < tilesByType.size; i5++) {
            RoadTile roadTile = (RoadTile) tilesByType.get(i5);
            if (!intSet.contains((roadTile.getY() * 48) + roadTile.getX())) {
                long distanceBetweenPoints = PMath.getDistanceBetweenPoints(roadTile.center, map.getTargetTileOrThrow().center);
                array.add(new ObjectPair(roadTile, Long.valueOf(distanceBetweenPoints)));
                j = Math.max(j, distanceBetweenPoints);
            }
        }
        if (array.size != 0) {
            long j2 = 0;
            for (int i6 = 0; i6 < array.size; i6++) {
                ObjectPair objectPair = ((ObjectPair[]) array.items)[i6];
                objectPair.second = Long.valueOf((j - ((Long) objectPair.second).longValue()) + 128);
                j2 += ((Long) objectPair.second).longValue();
            }
            for (int i7 = 0; i7 < 8; i7++) {
                long randomFloat = this.S.gameState.randomFloat() * ((float) j2);
                ObjectPair objectPair2 = null;
                for (int i8 = 0; i8 < array.size; i8++) {
                    objectPair2 = ((ObjectPair[]) array.items)[i8];
                    long longValue = randomFloat - ((Long) objectPair2.second).longValue();
                    randomFloat = longValue;
                    if (longValue <= 0) {
                        break;
                    }
                }
                if (objectPair2 == null) {
                    objectPair2 = (ObjectPair) array.first();
                }
                array.removeValue(objectPair2, true);
                j2 -= ((Long) objectPair2.second).longValue();
                IceFieldUnit create = Game.i.unitManager.F.ICE_FIELD.create();
                create.setup(((RoadTile) objectPair2.first).center.x, ((RoadTile) objectPair2.first).center.y, 585.0f + (this.S.gameState.randomFloat() * 30.0f), 20);
                this.f.add(create);
                if (array.size == 0) {
                    return;
                }
            }
        }
    }

    @Override // com.prineside.tdi2.Ability
    public boolean start() {
        for (int i = 0; i < this.e.size; i++) {
            ObjectPair<Enemy, BlizzardBuff> objectPair = this.e.items[i];
            this.S.buff.P_BLIZZARD.addBuff(objectPair.first, objectPair.second);
        }
        for (int i2 = 0; i2 < this.f.size; i2++) {
            IceFieldUnit iceFieldUnit = this.f.items[i2];
            this.S.unit.register(iceFieldUnit);
            this.S.map.spawnUnit(iceFieldUnit);
        }
        this.S.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Applies Freezing debuff to the enemies that spawn during this ability is still active");
        if (this.S._gameUi != null) {
            this.g = new Group(this) { // from class: com.prineside.tdi2.abilities.BlizzardAbility.1
                @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
                public void draw(Batch batch, float f) {
                    batch.end();
                    batch.begin();
                    batch.setBlendFunction(770, 1);
                    super.draw(batch, f);
                    batch.end();
                    batch.begin();
                    batch.setBlendFunction(770, 771);
                }
            };
            this.g.setTransform(false);
            this.g.setTouchable(Touchable.disabled);
            this.g.setSize(Game.i.uiManager.viewport.getWorldWidth() + (Game.i.uiManager.getScreenSafeMargin() << 1), Game.i.uiManager.viewport.getWorldHeight());
            this.g.setPosition(-Game.i.uiManager.getScreenSafeMargin(), 0.0f);
            this.S._gameUi.mainUi.customElementsContainer.addActor(this.g);
            ImageWithParentColor imageWithParentColor = new ImageWithParentColor(Game.i.assetManager.getDrawable("blizzard-screen-border@flip-x"));
            imageWithParentColor.setSize(195.0f, 453.0f);
            imageWithParentColor.setColor(MaterialColor.LIGHT_BLUE.P100);
            this.g.addActor(imageWithParentColor);
            ImageWithParentColor imageWithParentColor2 = new ImageWithParentColor(Game.i.assetManager.getDrawable("blizzard-screen-border"));
            imageWithParentColor2.setSize(195.0f, 453.0f);
            imageWithParentColor2.setColor(MaterialColor.LIGHT_BLUE.P100);
            imageWithParentColor2.setPosition(Game.i.uiManager.viewport.getWorldWidth() - 195.0f, 0.0f);
            this.g.addActor(imageWithParentColor2);
            this.g.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(0.1f)));
        }
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && this.S._gameUi != null) {
            this.h = Game.i.abilityManager.F.BLIZZARD.f1792a.obtain();
            this.i = Game.i.abilityManager.F.BLIZZARD.f1792a.obtain();
            this.S._gameUi.mainUi.particlesCanvas.addParticle(this.h, -Game.i.uiManager.getScreenSafeMargin(), 0.0f);
            this.S._gameUi.mainUi.particlesCanvas.addParticle(this.i, Game.i.uiManager.viewport.getWorldWidth() + Game.i.uiManager.getScreenSafeMargin(), 0.0f);
            return true;
        }
        return true;
    }

    @Override // com.prineside.tdi2.Ability
    public void update(float f) {
        this.d -= f;
    }

    @Override // com.prineside.tdi2.Ability
    public boolean isDone() {
        return this.d <= 0.0f;
    }

    @Override // com.prineside.tdi2.Ability
    public void onDone() {
        if (this.g != null) {
            this.g.clearActions();
            this.g.addAction(Actions.sequence(Actions.fadeOut(0.3f), Actions.removeActor()));
        }
        if (this.h != null) {
            this.h.allowCompletion();
            this.h = null;
            this.i.allowCompletion();
            this.i = null;
        }
        this.S.events.getListeners(EnemySpawn.class).remove(this);
    }

    @Override // com.prineside.tdi2.Ability
    public void draw(Batch batch, float f) {
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(EnemySpawn enemySpawn) {
        FreezingBuff freezingBuff = new FreezingBuff();
        freezingBuff.setup(null, 10.0f, 100.0f, this.d, this.d * 10.0f, 0.0f, 0.0f);
        this.S.buff.P_FREEZING.addBuff(enemySpawn.getEnemy(), freezingBuff);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/abilities/BlizzardAbility$BlizzardAbilityFactory.class */
    public static class BlizzardAbilityFactory extends Ability.Factory<BlizzardAbility> {

        /* renamed from: a, reason: collision with root package name */
        private ParticleEffectPool f1792a;

        public BlizzardAbilityFactory(AbilityType abilityType) {
            super(abilityType);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Ability.Factory
        public BlizzardAbility create() {
            return new BlizzardAbility((byte) 0);
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public void setupAssets() {
            super.setupAssets();
            this.f1792a = Game.i.assetManager.getParticleEffectPool("blizzard.prt");
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public boolean requiresMapPointing() {
            return false;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getColor() {
            return MaterialColor.CYAN.P500;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("ability_description_BLIZZARD", Float.valueOf(gameValueProvider.getFloatValue(GameValueType.ABILITY_BLIZZARD_DURATION)), 8, 10, Integer.valueOf(MathUtils.round(65.0f)), 20) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("ability_frozen_enemies_damage_bonus", Integer.valueOf((int) StrictMath.round(gameValueProvider.getPercentValueAsMultiplier(GameValueType.ABILITY_BLIZZARD_BONUS_DAMAGE) * 100.0d)));
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public Color getDarkerColor() {
            return MaterialColor.CYAN.P700;
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInGreenPapers(int i) {
            return BlizzardAbility.f1791b[StrictMath.min(i, BlizzardAbility.f1791b.length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public int getPriceInResources(ResourceType resourceType, int i) {
            return BlizzardAbility.c[resourceType.ordinal()][StrictMath.min(i, BlizzardAbility.c[0].length - 1)];
        }

        @Override // com.prineside.tdi2.Ability.Factory
        public TextureRegionDrawable getIconDrawable() {
            return Game.i.assetManager.getDrawable("icon-blizzard");
        }
    }
}
