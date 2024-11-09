package com.prineside.tdi2.systems.randomEncounter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/EncounterBird.class */
public class EncounterBird implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3078a = TLog.forClass(EncounterBird.class);
    public static float HIT_BOX_SCALE = 1.75f;

    @NAGS
    public ParticleEffectPool.PooledEffect underEffect;

    @NAGS
    public ParticleEffectPool.PooledEffect midEffect;

    @NAGS
    public ParticleEffectPool.PooledEffect overEffect;
    public GameSystemProvider S;

    @NAGS
    public float existsTime;
    public int existsFrames;
    public int framesLifeTime;
    public boolean requiresConfirmation;

    @NAGS
    public float timeSinceRandomTargetSwitch;

    @NAGS
    public long lastHitTimestamp;
    public RandomEncounterSystem.BirdAction birdAcceptAction;
    public int maxHp = 1;
    public int hp = 1;

    @NAGS
    public Vector2 position = new Vector2();

    @NAGS
    public Vector2 velocity = new Vector2();

    @NAGS
    public float lifeTime = 20.0f;

    @NAGS
    public Vector2 targetPoint = new Vector2();

    @NAGS
    public Color baseColor = MaterialColor.YELLOW.P400.cpy();

    @NAGS
    public Color overlayColor = MaterialColor.YELLOW.P600.cpy();

    @NAGS
    public float maxVelocity = 384.0f;

    @NAGS
    public float acceleration = 192.0f;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.S);
        output.writeFloat(this.existsTime);
        output.writeVarInt(this.existsFrames, true);
        output.writeVarInt(this.framesLifeTime, true);
        output.writeVarInt(this.maxHp, true);
        output.writeVarInt(this.hp, true);
        output.writeBoolean(this.requiresConfirmation);
        kryo.writeObject(output, this.position);
        kryo.writeObject(output, this.velocity);
        output.writeFloat(this.lifeTime);
        kryo.writeObject(output, this.targetPoint);
        kryo.writeObject(output, this.baseColor);
        kryo.writeObject(output, this.overlayColor);
        output.writeFloat(this.maxVelocity);
        output.writeFloat(this.acceleration);
        output.writeFloat(this.timeSinceRandomTargetSwitch);
        kryo.writeClassAndObject(output, this.birdAcceptAction);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.S = (GameSystemProvider) kryo.readObject(input, GameSystemProvider.class);
        this.existsTime = input.readFloat();
        this.existsFrames = input.readVarInt(true);
        this.framesLifeTime = input.readVarInt(true);
        this.maxHp = input.readVarInt(true);
        this.hp = input.readVarInt(true);
        this.requiresConfirmation = input.readBoolean();
        this.position = (Vector2) kryo.readObject(input, Vector2.class);
        this.velocity = (Vector2) kryo.readObject(input, Vector2.class);
        this.lifeTime = input.readFloat();
        this.targetPoint = (Vector2) kryo.readObject(input, Vector2.class);
        this.baseColor = (Color) kryo.readObject(input, Color.class);
        this.overlayColor = (Color) kryo.readObject(input, Color.class);
        this.maxVelocity = input.readFloat();
        this.acceleration = input.readFloat();
        this.timeSinceRandomTargetSwitch = input.readFloat();
        this.birdAcceptAction = (RandomEncounterSystem.BirdAction) kryo.readClassAndObject(input);
    }

    private EncounterBird() {
    }

    public EncounterBird(GameSystemProvider gameSystemProvider, RandomEncounterSystem.BirdAction birdAction) {
        Preconditions.checkNotNull(gameSystemProvider, "S can not be null");
        Preconditions.checkNotNull(birdAction, "birdAcceptAction can not be null");
        this.S = gameSystemProvider;
        this.birdAcceptAction = birdAction;
        selectNewTargetPoint();
        this.position.set(this.targetPoint);
        this.velocity.set(FastRandom.getFairFloat() - 0.5f, FastRandom.getFairFloat() - 0.5f).scl(this.maxVelocity);
    }

    public void setLifeTime(float f) {
        int ceil = MathUtils.ceil(f * this.S.gameValue.getTickRate());
        this.lifeTime = f;
        this.framesLifeTime = ceil;
    }

    public boolean isVisible() {
        return this.existsTime < this.lifeTime;
    }

    public boolean isActive() {
        return this.existsFrames <= this.framesLifeTime;
    }

    private void a() {
        if (this.underEffect == null) {
            float floatValue = this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_SCALE);
            this.underEffect = Game.i.assetManager.getParticleEffectPool("encounter-bird-under.p").obtain();
            this.underEffect.scaleEffect(floatValue);
            for (int i = 0; i < this.underEffect.getEmitters().size; i++) {
                this.underEffect.getEmitters().get(i).getTint().setColors(new float[]{this.baseColor.r, this.baseColor.g, this.baseColor.f888b});
            }
            this.midEffect = Game.i.assetManager.getParticleEffectPool("encounter-bird-middle.p").obtain();
            this.midEffect.scaleEffect(floatValue);
            for (int i2 = 0; i2 < this.midEffect.getEmitters().size; i2++) {
                this.midEffect.getEmitters().get(i2).getTint().setColors(new float[]{this.overlayColor.r, this.overlayColor.g, this.overlayColor.f888b});
            }
            this.overEffect = Game.i.assetManager.getParticleEffectPool("encounter-bird-over.p").obtain();
            this.overEffect.scaleEffect(floatValue);
            for (int i3 = 0; i3 < this.overEffect.getEmitters().size; i3++) {
                this.overEffect.getEmitters().get(i3).getTint().setColors(new float[]{this.overlayColor.r, this.overlayColor.g, this.overlayColor.f888b});
            }
        }
    }

    public void updateOnDraw(float f) {
        a();
        this.existsTime += f;
        this.underEffect.update(f);
        this.midEffect.update(f);
        this.overEffect.update(f);
        if (this.targetPoint.dst2(this.position) < 4096.0f) {
            selectNewTargetPoint();
        }
        Vector2 nor = new Vector2(this.targetPoint).sub(this.position).nor();
        nor.scl(this.acceleration * f);
        this.velocity.add(nor);
        if (this.velocity.len() > this.maxVelocity) {
            this.velocity.scl(this.maxVelocity / this.velocity.len());
        }
        this.position.add(this.velocity.x * f, this.velocity.y * f);
        this.timeSinceRandomTargetSwitch += f;
        if (this.timeSinceRandomTargetSwitch > this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_DIRECTION_CHANGE_DELAY)) {
            selectNewTargetPoint();
        }
    }

    public void tick() {
        this.existsFrames++;
    }

    public void selectNewTargetPoint() {
        this.targetPoint.x = FastRandom.getFairFloat() * (this.S.map.getMap().getWidth() << 7);
        this.targetPoint.y = FastRandom.getFairFloat() * (this.S.map.getMap().getHeight() << 7);
        this.timeSinceRandomTargetSwitch = 0.0f;
    }

    public void draw(Batch batch) {
        float f;
        if (isVisible()) {
            float floatValue = this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_SCALE);
            float f2 = this.position.x;
            float f3 = this.position.y;
            float f4 = 128.0f * floatValue;
            if (this.existsTime < 0.5f) {
                f = this.existsTime / 0.5f;
                floatValue *= MathUtils.clamp(f * 2.0f, 0.0f, 1.0f);
            } else if (this.existsTime > this.lifeTime - 0.5f) {
                f = 1.0f - ((this.existsTime - (this.lifeTime - 0.5f)) / 0.5f);
            } else if (this.existsTime > this.lifeTime - 2.5f) {
                f = 0.25f + (((float) ((Math.sin(((((this.existsTime - this.lifeTime) - 2.5f) * 3.1415927f) * 4.0f) + 1.5707964f) + 1.0d) / 2.0d)) * 0.75f);
            } else {
                f = 1.0f;
            }
            if (this.S.gameState.getGameSpeed() < 0.95f) {
                f *= 0.5f;
            }
            a();
            float f5 = (this.existsTime % 0.4f) / 0.4f;
            float apply = f5 < 0.25f ? Interpolation.fastSlow.apply(f5 / 0.25f) : Interpolation.smooth.apply(1.0f - ((f5 - 0.25f) / 0.75f));
            float f6 = 1.0f + ((1.0f - apply) * 0.05f);
            float f7 = 1.0f - ((1.0f - apply) * 0.05f);
            float f8 = f3 + (apply * 7.0f);
            batch.flush();
            batch.setColor(1.0f, 1.0f, 1.0f, f);
            this.underEffect.setPosition(f2, f8);
            this.underEffect.draw(batch);
            long realTickCount = Game.getRealTickCount() - this.lastHitTimestamp;
            float f9 = 0.0f;
            if (realTickCount < 200000) {
                f9 = 1.0f - (((float) realTickCount) / 200000.0f);
            }
            batch.setColor(1.0f, 1.0f, 1.0f, f);
            float f10 = (apply * 40.0f) - 15.0f;
            batch.draw(AssetManager.TextureRegions.i().encounterBirdWingLeft, (f2 - (29.0f * floatValue)) - (f4 * 0.5f), (f8 + (18.0f * floatValue)) - (f4 * 0.5f), 74.0f * floatValue, 31.0f * floatValue, 89.0f * floatValue, 84.0f * floatValue, 1.0f, 1.0f, f10);
            batch.draw(AssetManager.TextureRegions.i().encounterBirdWingRight, (f2 + (68.0f * floatValue)) - (f4 * 0.5f), (f8 + (18.0f * floatValue)) - (f4 * 0.5f), 16.0f * floatValue, 31.0f * floatValue, 89.0f * floatValue, 84.0f * floatValue, 1.0f, 1.0f, -f10);
            batch.flush();
            batch.setBlendFunction(770, 771);
            batch.setColor(this.baseColor);
            batch.setColor(this.baseColor.r, this.baseColor.g, this.baseColor.f888b, f);
            batch.draw(Game.i.assetManager.getTextureRegion("quest-tile-base"), f2 - ((f4 * 0.5f) * f7), f8 - ((f4 * 0.5f) * f6), f4 * f7, f4 * f6);
            if (f9 != 0.0f) {
                batch.flush();
                batch.setBlendFunction(770, 1);
                batch.setColor(this.baseColor.r, this.baseColor.g, this.baseColor.f888b, f * f9);
                batch.draw(Game.i.assetManager.getTextureRegion("quest-tile-base"), f2 - ((f4 * 0.5f) * f7), f8 - ((f4 * 0.5f) * f6), f4 * f7, f4 * f6);
                batch.flush();
                batch.setBlendFunction(770, 771);
            }
            batch.flush();
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            batch.setColor(1.0f, 1.0f, 1.0f, f);
            this.midEffect.setPosition(f2, f8);
            this.midEffect.draw(batch);
            batch.flush();
            batch.setBlendFunction(770, 771);
            batch.setColor(this.overlayColor);
            batch.setColor(this.overlayColor.r, this.overlayColor.g, this.overlayColor.f888b, f);
            batch.draw(Game.i.assetManager.getTextureRegion("quest-tile-overlay"), f2 - ((f4 * 0.5f) * f7), f8 - ((f4 * 0.5f) * f6), f4 * f7, f4 * f6);
            batch.flush();
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            batch.setColor(1.0f, 1.0f, 1.0f, f);
            this.overEffect.setPosition(f2, f8);
            this.overEffect.draw(batch);
            batch.flush();
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            batch.setBlendFunction(770, 771);
            if (this.maxHp > 1) {
                ResourcePack.AtlasTextureRegion blankWhiteTextureRegion = Game.i.assetManager.getBlankWhiteTextureRegion();
                batch.setColor(Enemy.HEALTH_BAR_BACKGROUND_COLOR);
                batch.draw(blankWhiteTextureRegion, (f2 - 32.0f) - 2.0f, f8 + 56.0f, 68.0f, 10.0f);
                batch.setColor(this.overlayColor);
                batch.draw(blankWhiteTextureRegion, f2 - 32.0f, f8 + 56.0f + 2.0f, 64.0f * (this.hp / this.maxHp), 6.0f);
                batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            }
            if (this.S.gameState.getGameSpeed() < 0.95f) {
                Game.i.assetManager.getQuad("randomEncounter.bubbleSlowMotion").draw(batch, f2 - 24.0f, ((f8 - 24.0f) + f4) - 16.0f, 48.0f, 48.0f);
            }
            if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_UNITS_BBOX) != 0.0d) {
                float f11 = 128.0f * floatValue * HIT_BOX_SCALE;
                float f12 = 128.0f * floatValue * HIT_BOX_SCALE;
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.position.x - (f11 * 0.5f), this.position.y - (f12 * 0.5f), this.position.x - (f11 * 0.5f), this.position.y + (f12 * 0.5f), 1.0f, MaterialColor.YELLOW.P500.toFloatBits(), MaterialColor.YELLOW.P500.toFloatBits());
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.position.x - (f11 * 0.5f), this.position.y - (f12 * 0.5f), this.position.x + (f11 * 0.5f), this.position.y - (f12 * 0.5f), 1.0f, MaterialColor.YELLOW.P500.toFloatBits(), MaterialColor.YELLOW.P500.toFloatBits());
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.position.x - (f11 * 0.5f), this.position.y + (f12 * 0.5f), this.position.x + (f11 * 0.5f), this.position.y + (f12 * 0.5f), 1.0f, MaterialColor.YELLOW.P500.toFloatBits(), MaterialColor.YELLOW.P500.toFloatBits());
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.position.x + (f11 * 0.5f), this.position.y + (f12 * 0.5f), this.position.x + (f11 * 0.5f), this.position.y - (f12 * 0.5f), 1.0f, MaterialColor.YELLOW.P500.toFloatBits(), MaterialColor.YELLOW.P500.toFloatBits());
            }
        }
    }

    public boolean isMouseHit(float f, float f2) {
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_SCALE);
        float f3 = this.position.x;
        float f4 = this.position.y;
        float f5 = 128.0f * floatValue * HIT_BOX_SCALE;
        float f6 = 128.0f * floatValue * HIT_BOX_SCALE;
        return f >= f3 - (f5 * 0.5f) && f <= f3 + (f5 * 0.5f) && f2 >= f4 - (f6 * 0.5f) && f2 <= f4 + (f6 * 0.5f);
    }

    public void onClickAction() {
        f3078a.i("click encounter", new Object[0]);
        this.lastHitTimestamp = Game.getRealTickCount();
        this.hp--;
        if (this.hp < 0) {
            this.hp = 0;
        }
        if (this.hp == 0) {
            this.existsFrames = this.framesLifeTime + 1;
            this.birdAcceptAction.performAction(this.S, this);
        }
    }

    public void onDeclineAction() {
        f3078a.i("onDeclineAction", new Object[0]);
        this.existsFrames = this.framesLifeTime + 1;
    }

    public void onFinish() {
        f3078a.i("on finish encounter", new Object[0]);
        if (this.S._particle != null && this.lastHitTimestamp != 0) {
            this.S._particle.addShatterParticle(Game.i.assetManager.getTextureRegion("quest-tile-base"), this.position.x, this.position.y, this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_SCALE) * 128.0f, 0.0f, 1.0f, this.baseColor, null, true);
            ParticleEffectPool.PooledEffect obtain = Game.i.assetManager.getParticleEffectPool("encounter-bird-pop.p").obtain();
            obtain.setPosition(this.position.x, this.position.y);
            obtain.scaleEffect(this.S.gameValue.getFloatValue(GameValueType.ENCOUNTER_BIRD_SCALE));
            float[] colors = obtain.getEmitters().first().getTint().getColors();
            colors[0] = this.overlayColor.r;
            colors[1] = this.overlayColor.g;
            colors[2] = this.overlayColor.f888b;
            obtain.getEmitters().first().getTint().setColors(colors);
            this.S._particle.addParticle(obtain, false);
        }
    }
}
