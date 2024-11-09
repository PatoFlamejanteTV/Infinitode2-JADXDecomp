package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.events.game.EnemyDespawn;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.shapes.ChainLightning;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.MovingAverageLong;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.PooledCustomEffect;
import com.prineside.tdi2.utils.StringFormatter;
import java.util.Arrays;
import java.util.Comparator;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ParticleSystem.class */
public final class ParticleSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f3007a;

    /* renamed from: b, reason: collision with root package name */
    private final int f3008b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private static final Comparator<DamageParticle> A;
    private int B;
    private static final IntIntMap C;
    private int J;
    private int K;
    private int M;
    private static final Vector2 O;
    private static final StringBuilder P;
    private static final float[] Q;
    private static final Color R;
    private static final Vector2 T;
    private static final Vector2 U;
    private static final Vector2 V;
    private static final DelayedRemovalArray<DamageParticle> W;
    private static float[] X;
    private final float[] j = new float[LimitedParticleType.values.length];
    private final DelayedRemovalArray<ParticleEffect> k = new DelayedRemovalArray<>(false, 16, ParticleEffect.class);
    private final DelayedRemovalArray<ParticleEffect> l = new DelayedRemovalArray<>(false, 16, ParticleEffect.class);
    private final DelayedRemovalArray<XpOrbParticle> m = new DelayedRemovalArray<>(XpOrbParticle.class);
    private final DelayedRemovalArray<ShatterPolygon> n = new DelayedRemovalArray<>(ShatterPolygon.class);
    private final DelayedRemovalArray<ShatterPolygon> o = new DelayedRemovalArray<>(ShatterPolygon.class);
    private final DelayedRemovalArray<CoinParticle> p = new DelayedRemovalArray<>(CoinParticle.class);
    private final DelayedRemovalArray<DamageParticle> q = new DelayedRemovalArray<>(DamageParticle.class);
    private final DelayedRemovalArray<ChainLightning> r = new DelayedRemovalArray<>(false, 1, ChainLightning.class);
    private final DelayedRemovalArray<DamageParticle> s = new DelayedRemovalArray<>(DamageParticle.class);
    private final DelayedRemovalArray<DamageParticle> t = new DelayedRemovalArray<>(DamageParticle.class);
    private final DelayedRemovalArray<DamageParticle> u = new DelayedRemovalArray<>(DamageParticle.class);
    private final DelayedRemovalArray<DamageParticle> v = new DelayedRemovalArray<>(DamageParticle.class);
    private final DelayedRemovalArray<FlashParticle> w = new DelayedRemovalArray<>(FlashParticle.class);
    private final Pool<XpOrbParticle> x = new Pool<XpOrbParticle>(this, 1, 512) { // from class: com.prineside.tdi2.systems.ParticleSystem.1
        {
            super(1, 512);
        }

        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ XpOrbParticle newObject() {
            return a();
        }

        private static XpOrbParticle a() {
            return new XpOrbParticle((byte) 0);
        }
    };
    private final Pool<ShatterPolygon> y = new Pool<ShatterPolygon>(this, 1, 512) { // from class: com.prineside.tdi2.systems.ParticleSystem.2
        {
            super(1, 512);
        }

        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ ShatterPolygon newObject() {
            return a();
        }

        private static ShatterPolygon a() {
            return new ShatterPolygon((byte) 0);
        }
    };
    private final Pool<CoinParticle> z = new Pool<CoinParticle>(this, 1, 512) { // from class: com.prineside.tdi2.systems.ParticleSystem.3
        {
            super(1, 512);
        }

        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ CoinParticle newObject() {
            return a();
        }

        private static CoinParticle a() {
            return new CoinParticle((byte) 0);
        }
    };
    private final MovingAverageLong D = new MovingAverageLong(32);
    private final IntMap<Pool<DamageParticle>> E = new IntMap<>();
    private final Pool<FlashParticle> F = new Pool<FlashParticle>(this, 1, 512) { // from class: com.prineside.tdi2.systems.ParticleSystem.4
        {
            super(1, 512);
        }

        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ FlashParticle newObject() {
            return a();
        }

        private static FlashParticle a() {
            return new FlashParticle((byte) 0);
        }
    };
    private final IntMap<BitmapFontCache> G = new IntMap<>();
    private final IntIntMap L = new IntIntMap();
    private final IntFloatMap N = new IntFloatMap();
    private TextureRegion I = Game.i.assetManager.getTextureRegion("coin-small");
    private BitmapFontCache H = new BitmapFontCache(Game.i.assetManager.getFont(21));

    static {
        float[] fArr = new float[LimitedParticleType.values.length];
        f3007a = fArr;
        Arrays.fill(fArr, 4.0f * Config.UPDATE_DELTA_TIME);
        f3007a[LimitedParticleType.EXPLOSION_FIREBALL.ordinal()] = 1.0f * Config.UPDATE_DELTA_TIME;
        f3007a[LimitedParticleType.EXPLOSION_MISSILE.ordinal()] = 8.0f * Config.UPDATE_DELTA_TIME;
        f3007a[LimitedParticleType.EXPLOSION_CANNON.ordinal()] = 8.0f * Config.UPDATE_DELTA_TIME;
        f3007a[LimitedParticleType.ENEMY_HIT.ordinal()] = 5.0f * Config.UPDATE_DELTA_TIME;
        f3007a[LimitedParticleType.ENEMY_DEAD.ordinal()] = 4.0f * Config.UPDATE_DELTA_TIME;
        f3007a[LimitedParticleType.RESOURCE_MINED.ordinal()] = 5.0f * Config.UPDATE_DELTA_TIME;
        (damageParticle, damageParticle2) -> {
            return Long.compare(damageParticle.e, damageParticle2.e);
        };
        A = (damageParticle3, damageParticle4) -> {
            return Float.compare(damageParticle4.k, damageParticle3.k);
        };
        C = new IntIntMap();
        O = new Vector2();
        P = new StringBuilder();
        Q = new float[20];
        R = Color.WHITE.cpy();
        T = new Vector2();
        U = new Vector2();
        V = new Vector2();
        W = new DelayedRemovalArray<>(true, 1, DamageParticle.class);
        X = new float[20];
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    public ParticleSystem() {
        this.H.setUseIntegerPositions(false);
        float customValue = (float) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PARTICLE_COUNT);
        this.f3008b = (int) (200.0f * customValue);
        this.c = (int) (100.0f * customValue);
        this.d = (int) (600.0f * customValue);
        this.e = (int) (200.0f * customValue);
        this.f = (int) (200.0f * customValue);
        this.g = (int) (100.0f * customValue);
        this.h = (int) (200.0f * customValue);
        this.i = (int) (100.0f * customValue);
        System.arraycopy(f3007a, 0, this.j, 0, this.j.length);
        float f = ((1.0f / customValue) + 2.0f) / 3.0f;
        for (int i = 0; i < this.j.length; i++) {
            float[] fArr = this.j;
            int i2 = i;
            fArr[i2] = fArr[i2] * f;
        }
    }

    public final void addFlashParticle(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        addFlashParticleColored(textureRegion, f, f2, f3, f4, f5, f6, f7, Color.WHITE);
    }

    public final void addFlashParticleColored(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, Color color) {
        if (this.S.gameState.canSkipMediaUpdate() || !Game.i.settingsManager.isParticlesDrawing() || this.w.size > this.f3008b) {
            return;
        }
        if (this.w.size > this.c && FastRandom.getFloat() < 0.5f) {
            return;
        }
        FlashParticle obtain = this.F.obtain();
        obtain.time = 0.0f;
        obtain.texture = textureRegion.getTexture();
        obtain.color.set(color);
        DrawUtils.bakeVertices(obtain.vertices, textureRegion, f - f3, f2 - f4, f3, f4, f5, f6, 1.0f, 1.0f, f7);
        this.w.add(obtain);
    }

    public final void addRegularShatterParticle(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5) {
        addShatterParticle(textureRegion, f, f2, f3, f4, f5, Color.WHITE, Interpolation.pow2InInverse, true);
    }

    public final void addShatterParticle(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, Color color, @Null Interpolation interpolation, boolean z) {
        if (this.S.gameState.canSkipMediaUpdate() || !Game.i.settingsManager.isParticlesDrawing()) {
            return;
        }
        DelayedRemovalArray<ShatterPolygon> delayedRemovalArray = z ? this.o : this.n;
        DelayedRemovalArray<ShatterPolygon> delayedRemovalArray2 = delayedRemovalArray;
        if (delayedRemovalArray.size > this.d) {
            return;
        }
        if (delayedRemovalArray2.size > this.e && FastRandom.getFloat() > 0.5f) {
            return;
        }
        if (interpolation == null) {
            interpolation = Interpolation.linear;
        }
        float f6 = f - (f3 * 0.5f);
        float f7 = f2 - (f3 * 0.5f);
        float f8 = f3 * f5;
        float f9 = 0.25f + (FastRandom.getFloat() * 0.5f);
        float f10 = 0.25f + (FastRandom.getFloat() * 0.5f);
        float f11 = 0.25f + (FastRandom.getFloat() * 0.5f);
        float f12 = 0.25f + (FastRandom.getFloat() * 0.5f);
        float f13 = 0.25f + (FastRandom.getFloat() * 0.5f);
        float f14 = 0.25f + (FastRandom.getFloat() * 0.5f);
        float u = textureRegion.getU();
        float u2 = textureRegion.getU2();
        float v2 = textureRegion.getV2();
        float v = textureRegion.getV();
        float f15 = u + ((u2 - u) * f13);
        float f16 = v2 + ((v - v2) * f14);
        float f17 = v2 + ((v - v2) * f9);
        float f18 = u + ((u2 - u) * f10);
        float f19 = v2 + ((v - v2) * f11);
        float f20 = u + ((u2 - u) * f12);
        ShatterPolygon obtain = this.y.obtain();
        obtain.h = textureRegion.getTexture();
        obtain.i.set(color);
        obtain.k = interpolation;
        obtain.d[0] = 0.0f;
        obtain.d[1] = f8 * f9;
        obtain.d[2] = 0.0f;
        obtain.d[3] = f8;
        obtain.d[4] = f8 * f10;
        obtain.d[5] = f8;
        obtain.d[6] = f8 * f13;
        obtain.d[7] = f8 * f14;
        obtain.f3014a.setVertices(obtain.d);
        obtain.f3015b[0] = u;
        obtain.f3015b[1] = f17;
        obtain.f3015b[2] = u;
        obtain.f3015b[3] = v;
        obtain.f3015b[4] = f18;
        obtain.f3015b[5] = v;
        obtain.f3015b[6] = f15;
        obtain.f3015b[7] = f16;
        obtain.f3014a.setPosition(f6, f7);
        obtain.f3014a.setOrigin(f8 * 0.5f, f8 * 0.5f);
        obtain.f3014a.setRotation(f4);
        obtain.e = 0.6f + (FastRandom.getFloat() * 0.5f);
        obtain.f = (FastRandom.getFloat() * 0.5f) - 1.0f;
        ShatterPolygon.c(obtain, 0.0f);
        obtain.c.set(0.0f, 128.0f + (FastRandom.getFloat() * 256.0f)).rotateDeg((FastRandom.getFloat() * 60.0f) + 15.0f + f4);
        delayedRemovalArray2.add(obtain);
        ShatterPolygon obtain2 = this.y.obtain();
        obtain2.h = textureRegion.getTexture();
        obtain2.i.set(color);
        obtain2.k = interpolation;
        obtain2.d[0] = f8 * f13;
        obtain2.d[1] = f8 * f14;
        obtain2.d[2] = f8 * f10;
        obtain2.d[3] = f8;
        obtain2.d[4] = f8;
        obtain2.d[5] = f8;
        obtain2.d[6] = f8;
        obtain2.d[7] = f8 * f11;
        obtain2.f3014a.setVertices(obtain2.d);
        obtain2.f3015b[0] = f15;
        obtain2.f3015b[1] = f16;
        obtain2.f3015b[2] = f18;
        obtain2.f3015b[3] = v;
        obtain2.f3015b[4] = u2;
        obtain2.f3015b[5] = v;
        obtain2.f3015b[6] = u2;
        obtain2.f3015b[7] = f19;
        obtain2.f3014a.setPosition(f6, f7);
        obtain2.f3014a.setOrigin(f8 * 0.5f, f8 * 0.5f);
        obtain2.f3014a.setRotation(f4);
        obtain2.e = 0.6f + (FastRandom.getFloat() * 0.5f);
        ShatterPolygon.c(obtain2, 0.0f);
        obtain2.c.set(0.0f, 128.0f + (FastRandom.getFloat() * 256.0f)).rotateDeg((FastRandom.getFloat() * 60.0f) + 15.0f + 270.0f + f4);
        delayedRemovalArray2.add(obtain2);
        ShatterPolygon obtain3 = this.y.obtain();
        obtain3.h = textureRegion.getTexture();
        obtain3.i.set(color);
        obtain3.k = interpolation;
        obtain3.d[0] = f8;
        obtain3.d[1] = f8 * f11;
        obtain3.d[2] = f8;
        obtain3.d[3] = 0.0f;
        obtain3.d[4] = f8 * f12;
        obtain3.d[5] = 0.0f;
        obtain3.d[6] = f8 * f13;
        obtain3.d[7] = f8 * f14;
        obtain3.f3014a.setVertices(obtain3.d);
        obtain3.f3015b[0] = u2;
        obtain3.f3015b[1] = f19;
        obtain3.f3015b[2] = u2;
        obtain3.f3015b[3] = v2;
        obtain3.f3015b[4] = f20;
        obtain3.f3015b[5] = v2;
        obtain3.f3015b[6] = f15;
        obtain3.f3015b[7] = f16;
        obtain3.f3014a.setPosition(f6, f7);
        obtain3.f3014a.setOrigin(f8 * 0.5f, f8 * 0.5f);
        obtain3.f3014a.setRotation(f4);
        obtain3.e = 0.6f + (FastRandom.getFloat() * 0.5f);
        ShatterPolygon.c(obtain3, 0.0f);
        obtain3.c.set(0.0f, 128.0f + (FastRandom.getFloat() * 256.0f)).rotateDeg((FastRandom.getFloat() * 60.0f) + 15.0f + 180.0f + f4);
        delayedRemovalArray2.add(obtain3);
        ShatterPolygon obtain4 = this.y.obtain();
        obtain4.h = textureRegion.getTexture();
        obtain4.i.set(color);
        obtain4.k = interpolation;
        obtain4.d[0] = f8 * f13;
        obtain4.d[1] = f8 * f14;
        obtain4.d[2] = f8 * f12;
        obtain4.d[3] = 0.0f;
        obtain4.d[4] = 0.0f;
        obtain4.d[5] = 0.0f;
        obtain4.d[6] = 0.0f;
        obtain4.d[7] = f8 * f9;
        obtain4.f3014a.setVertices(obtain4.d);
        obtain4.f3015b[0] = f15;
        obtain4.f3015b[1] = f16;
        obtain4.f3015b[2] = f20;
        obtain4.f3015b[3] = v2;
        obtain4.f3015b[4] = u;
        obtain4.f3015b[5] = v2;
        obtain4.f3015b[6] = u;
        obtain4.f3015b[7] = f17;
        obtain4.f3014a.setPosition(f6, f7);
        obtain4.f3014a.setOrigin(f8 * 0.5f, f8 * 0.5f);
        obtain4.f3014a.setRotation(f4);
        obtain4.e = 0.6f + (FastRandom.getFloat() * 0.5f);
        ShatterPolygon.c(obtain4, 0.0f);
        obtain4.c.set(0.0f, 128.0f + (FastRandom.getFloat() * 256.0f)).rotateDeg((FastRandom.getFloat() * 60.0f) + 15.0f + 90.0f + f4);
        delayedRemovalArray2.add(obtain4);
    }

    public final void addXpOrbParticle(float f, int i, int i2, int i3, int i4) {
        if (this.m.size > this.h) {
            return;
        }
        int i5 = (int) (f / 5.0f);
        int i6 = i5;
        if (i5 <= 0) {
            i6 = 1;
        }
        float f2 = 12.0f;
        int i7 = 0;
        while (true) {
            if (i6 <= 4) {
                break;
            }
            i6 /= 2;
            f2 *= 1.25f;
            i7++;
            if (i7 == 7) {
                if (i6 > 4) {
                    i6 = 4;
                }
            }
        }
        for (int i8 = 0; i8 < i6; i8++) {
            addOrbParticle(Game.i.modifierManager.F.BALANCE.orbTexture, f2, i, i2, i3, i4);
        }
    }

    public final void addOrbParticle(TextureRegion textureRegion, float f, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.S.state.canSkipMediaUpdate() || !Game.i.settingsManager.isParticlesDrawing() || this.m.size > this.h) {
            return;
        }
        if ((this.m.size <= this.i || FastRandom.getFloat() <= 0.5f) && (i6 = this.L.get((i5 = ((((((i + 31) * 31) + i2) * 31) + i3) * 31) + i4), 0)) < 4) {
            XpOrbParticle obtain = this.x.obtain();
            V.set(1.0f, 0.0f).rotateDeg(FastRandom.getFloat() * 360.0f).scl(FastRandom.getFloat() * 64.0f * 0.8f).add((i << 7) + 64, (i2 << 7) + 64);
            obtain.f = V.x;
            obtain.g = V.y;
            V.set(1.0f, 0.0f).rotateDeg(FastRandom.getFloat() * 360.0f).scl(FastRandom.getFloat() * 64.0f * 3.0f);
            obtain.d = V.x;
            obtain.e = V.y;
            V.set(1.0f, 0.0f).rotateDeg(FastRandom.getFloat() * 360.0f).scl(FastRandom.getFloat() * 64.0f * 0.4f).add((i3 << 7) + 64, (i4 << 7) + 64);
            obtain.h = V.x;
            obtain.i = V.y;
            obtain.f3017b = obtain.f;
            obtain.c = obtain.g;
            obtain.j = textureRegion;
            obtain.f3016a = f;
            this.m.add(obtain);
            this.L.put(i5, i6 + 1);
        }
    }

    private BitmapFontCache a(int i, float f, float f2) {
        BitmapFontCache bitmapFontCache;
        if (i < 32) {
            if (!this.G.containsKey(i)) {
                bitmapFontCache = new BitmapFontCache(Game.i.assetManager.getFont(21));
                P.setLength(0);
                P.append(i);
                bitmapFontCache.addText(P, 0.0f, 0.0f);
                this.G.put(i, bitmapFontCache);
                bitmapFontCache.setUseIntegerPositions(false);
            } else {
                bitmapFontCache = this.G.get(i);
            }
        } else {
            BitmapFontCache bitmapFontCache2 = this.H;
            bitmapFontCache = bitmapFontCache2;
            bitmapFontCache2.clear();
            bitmapFontCache.addText(StringFormatter.compactNumber(i, false), 0.0f, 0.0f);
        }
        BitmapFontCache bitmapFontCache3 = bitmapFontCache;
        bitmapFontCache3.translate((-bitmapFontCache3.getX()) + f, (-bitmapFontCache.getY()) + f2);
        return bitmapFontCache;
    }

    public final void addCoinParticle(float f, float f2, int i) {
        if (this.S.state.canSkipMediaUpdate() || !Game.i.settingsManager.isParticlesDrawing()) {
            return;
        }
        CoinParticle obtain = this.z.obtain();
        obtain.f3010a.set(f, f2);
        obtain.f3011b = 64.0f + (FastRandom.getFloat() * 32.0f);
        obtain.c = 0.0f;
        obtain.d = i;
        this.p.add(obtain);
    }

    public final void addChainLightning(ChainLightning chainLightning) {
        if (this.S.state.canSkipMediaUpdate() || !Game.i.settingsManager.isProjectilesDrawing()) {
            return;
        }
        if (this.r.size > this.f) {
            chainLightning.free();
        } else if (this.r.size > this.g && FastRandom.getFloat() > 0.5f) {
            chainLightning.free();
        } else {
            this.r.add(chainLightning);
        }
    }

    public final void addEnemyHitParticle(Tower tower, Enemy enemy, float f, Projectile projectile) {
        float angleBetweenPoints = PMath.getAngleBetweenPoints(tower.getTile().center, enemy.getPosition()) - 90.0f;
        U.set(1.0f, 0.0f).rotateDeg(angleBetweenPoints).scl(enemy.getSize() * 0.75f);
        T.set(enemy.getPosition()).add(U);
        int a2 = a(T.x, T.y, LimitedParticleType.ENEMY_HIT);
        if (a2 == 0) {
            return;
        }
        ParticleEffectPool.PooledEffect hitParticle = enemy.getHitParticle();
        hitParticle.setPosition(T.x, T.y);
        ParticleEmitter first = hitParticle.getEmitters().first();
        first.getAngle().setHigh(angleBetweenPoints - 60.0f, angleBetweenPoints + 60.0f);
        ParticleEmitter.GradientColorValue tint = first.getTint();
        float[] colors = tint.getColors();
        Color color = enemy.getColor();
        colors[0] = color.r;
        colors[1] = color.g;
        colors[2] = color.f888b;
        tint.setColors(colors);
        ParticleEmitter.ScaledNumericValue emission = first.getEmission();
        int i = (int) ((f / enemy.maxHealth) * 50.0f);
        int i2 = i;
        if (i < 3) {
            i2 = 3;
        }
        emission.setHigh(i2);
        a(hitParticle, LimitedParticleType.ENEMY_HIT, a2);
    }

    private Pool<DamageParticle> a(final int i) {
        Pool<DamageParticle> pool = this.E.get(i, null);
        Pool<DamageParticle> pool2 = pool;
        if (pool == null) {
            pool2 = new Pool<DamageParticle>(this) { // from class: com.prineside.tdi2.systems.ParticleSystem.5
                /* JADX INFO: Access modifiers changed from: private */
                @Override // com.badlogic.gdx.utils.Pool
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public DamageParticle newObject() {
                    return new DamageParticle(i);
                }
            };
            this.E.put(i, pool2);
        }
        return pool2;
    }

    private static int a(float f, float f2) {
        return ((((int) (f2 * 0.0078125f)) * 48) << 1) + ((int) (f * 0.0078125f));
    }

    private void b(float f, float f2) {
        int i;
        float min = Math.min(f, f2);
        C.clear();
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DAMAGE_PARTICLES_MORE) == 0.0d) {
            i = 1;
        } else {
            i = 5;
        }
        this.B = i;
        this.q.begin();
        for (int i2 = 0; i2 < this.q.size; i2++) {
            DamageParticle damageParticle = this.q.items[i2];
            damageParticle.k += min;
            if (damageParticle.k < damageParticle.i) {
                int a2 = a(damageParticle.f3012a, damageParticle.f3013b);
                IntIntMap intIntMap = C;
                intIntMap.put(a2, intIntMap.get(a2, 0) + 1);
                if (min > 0.0f) {
                    damageParticle.d -= min * 170.0f;
                    damageParticle.c *= 0.995f;
                    damageParticle.f3012a += damageParticle.c * min;
                    damageParticle.f3013b += damageParticle.d * min;
                }
            } else {
                this.q.removeIndex(i2);
                a(damageParticle.h).free(damageParticle);
            }
        }
        this.q.end();
        W.clear();
        this.s.begin();
        for (int i3 = 0; i3 < this.s.size; i3++) {
            DamageParticle damageParticle2 = this.s.items[i3];
            damageParticle2.l += f2;
            if (damageParticle2.l >= 0.5f) {
                W.add(damageParticle2);
                this.s.removeIndex(i3);
            }
        }
        this.s.end();
        a(W, this.q);
        a(this.t, this.q);
        a(this.u, this.q);
        a(this.v, this.q);
    }

    private void a(DelayedRemovalArray<DamageParticle> delayedRemovalArray, DelayedRemovalArray<DamageParticle> delayedRemovalArray2) {
        if (delayedRemovalArray.size == 0) {
            return;
        }
        byte b2 = delayedRemovalArray.first().j;
        int i = -1;
        float f = 0.0f;
        for (int i2 = 0; i2 < delayedRemovalArray2.size; i2++) {
            DamageParticle damageParticle = delayedRemovalArray2.items[i2];
            if (damageParticle.j == b2) {
                f = Math.max(f, (float) damageParticle.e);
            }
        }
        for (int i3 = 0; i3 < delayedRemovalArray.size; i3++) {
            DamageParticle damageParticle2 = delayedRemovalArray.items[i3];
            if (((float) damageParticle2.e) > f) {
                i = i3;
                f = (float) damageParticle2.e;
            }
        }
        if (i != -1) {
            delayedRemovalArray2.add(delayedRemovalArray.removeIndex(i));
        }
        delayedRemovalArray.shuffle();
        for (int i4 = 0; i4 < delayedRemovalArray.size; i4++) {
            DamageParticle damageParticle3 = delayedRemovalArray.items[i4];
            int a2 = a(damageParticle3.f3012a, damageParticle3.f3013b);
            int i5 = C.get(a2, 0);
            if (i5 >= this.B) {
                a(damageParticle3.h).free(damageParticle3);
            } else {
                delayedRemovalArray2.add(damageParticle3);
                C.put(a2, i5 + 1);
            }
        }
        delayedRemovalArray.clear();
    }

    private float a(long j) {
        float average = ((float) j) / ((float) this.D.getAverage());
        if (average > 30.0f) {
            return 1.4f;
        }
        if (average > 10.0f) {
            return 1.3f;
        }
        if (average > 5.0f) {
            return 1.2f;
        }
        if (average > 2.0f) {
            return 1.1f;
        }
        return 1.0f;
    }

    public final void addDamageParticle(float f, float f2, long j, int i, int i2) {
        if (j > 0 && !this.S.state.canSkipMediaUpdate() && Game.i.settingsManager.isParticlesDrawing() && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DAMAGE_PARTICLES_ENABLED) != 0.0d) {
            if (i2 != 0) {
                for (int i3 = 0; i3 < this.s.size; i3++) {
                    DamageParticle damageParticle = this.s.items[i3];
                    if (damageParticle.f == i2) {
                        damageParticle.e += j;
                        damageParticle.g = 1.0f;
                        damageParticle.f3012a = (f + (FastRandom.getFloat() * 8.0f)) - 4.0f;
                        damageParticle.f3013b = ((f2 + 16.0f) + (FastRandom.getFloat() * 8.0f)) - 4.0f;
                        return;
                    }
                }
            }
            this.D.push(j);
            DamageParticle obtain = a(i).obtain();
            obtain.f3012a = (f + (FastRandom.getFloat() * 8.0f)) - 4.0f;
            obtain.f3013b = ((f2 + 16.0f) + (FastRandom.getFloat() * 8.0f)) - 4.0f;
            obtain.g = a(j);
            obtain.e = j;
            obtain.f = i2;
            if (DamageType.Efficiency.isOverTime(i)) {
                this.s.add(obtain);
                return;
            }
            if (DamageType.Efficiency.isEspeciallyEffective(i)) {
                this.t.add(obtain);
            } else if (DamageType.Efficiency.isCritical(i)) {
                this.u.add(obtain);
            } else {
                this.v.add(obtain);
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PARTICLE_UPDATE_DRAW, false, (batch, f, f2, f3) -> {
            updateDraw(f, f2);
        }).setName("Particle-updateDraw"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PARTICLE_DRAW, true, (batch2, f4, f5, f6) -> {
            draw(batch2, f4, f5);
        }).setName("Particle-draw"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PARTICLE_DRAW_DAMAGE, false, (batch3, f7, f8, f9) -> {
            drawDamageParticles(batch3);
        }).setName("Particle-drawDamage"));
        this.S.events.getListeners(EnemyDespawn.class).add(enemyDespawn -> {
            ObjectMap<String, ParticleEffectPool.PooledEffect> attachedParticles = enemyDespawn.getEnemy().getAttachedParticles();
            if (attachedParticles != null && attachedParticles.size != 0) {
                ObjectMap.Entries<String, ParticleEffectPool.PooledEffect> it = attachedParticles.iterator();
                while (it.hasNext()) {
                    ((ParticleEffectPool.PooledEffect) it.next().value).allowCompletion();
                }
            }
        }).setDescription("Allows completion of the attached particles to an enemies");
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Particle";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void freeParticle(ParticleEffect particleEffect) {
        if (particleEffect instanceof ParticleEffectPool.PooledEffect) {
            ((ParticleEffectPool.PooledEffect) particleEffect).free();
        } else if (particleEffect instanceof PooledCustomEffect) {
            ((PooledCustomEffect) particleEffect).free();
        }
    }

    private int a(float f, float f2, LimitedParticleType limitedParticleType) {
        int ordinal = ((((31 + ((int) (f * 0.015625f))) * 31) + ((int) (f2 * 0.015625f))) * 31) + limitedParticleType.ordinal();
        if (this.N.get(ordinal, 0.0f) <= 0.0f) {
            return ordinal;
        }
        return 0;
    }

    private void a(ParticleEffect particleEffect, LimitedParticleType limitedParticleType, int i) {
        addParticle(particleEffect, true);
        this.N.put(i, this.j[limitedParticleType.ordinal()]);
        this.J++;
    }

    public final void addLimitedParticle(ParticleEffect particleEffect, LimitedParticleType limitedParticleType, float f, float f2) {
        int a2 = a(f, f2, limitedParticleType);
        if (a2 == 0) {
            freeParticle(particleEffect);
            this.K++;
        } else {
            a(particleEffect, limitedParticleType, a2);
        }
    }

    public final boolean willParticleBeSkipped() {
        return this.S.state.canSkipMediaUpdate();
    }

    public final boolean addParticle(ParticleEffect particleEffect, boolean z) {
        if (willParticleBeSkipped() && z) {
            freeParticle(particleEffect);
            return false;
        }
        this.k.add(particleEffect);
        return true;
    }

    public final boolean addOpaqueParticle(ParticleEffect particleEffect) {
        if (willParticleBeSkipped()) {
            freeParticle(particleEffect);
            return false;
        }
        this.l.add(particleEffect);
        return true;
    }

    private void c(float f, float f2) {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MULTITHREADING) != 0.0d && this.k.size > 300) {
            this.k.begin();
            int i = this.k.size;
            for (int i2 = 0; i2 < i; i2++) {
                ParticleEffect particleEffect = this.k.items[i2];
                if (particleEffect.isComplete()) {
                    this.k.removeIndex(i2);
                    particleEffect.reset();
                    freeParticle(particleEffect);
                }
            }
            this.k.end();
            Threads.i().concurrentLoop(this.k, (i3, particleEffect2) -> {
                if (particleEffect2.getEmitters().size != 0 && particleEffect2.getEmitters().first().getName().charAt(0) == '#') {
                    particleEffect2.update(f);
                } else {
                    particleEffect2.update(f2);
                }
            });
            return;
        }
        this.k.begin();
        int i4 = this.k.size;
        for (int i5 = 0; i5 < i4; i5++) {
            ParticleEffect particleEffect3 = this.k.items[i5];
            if (particleEffect3.isComplete()) {
                this.k.removeIndex(i5);
                particleEffect3.reset();
                freeParticle(particleEffect3);
            } else if (particleEffect3.getEmitters().size != 0 && particleEffect3.getEmitters().first().getName().charAt(0) == '#') {
                particleEffect3.update(f);
            } else {
                particleEffect3.update(f2);
            }
        }
        this.k.end();
    }

    private void d(float f, float f2) {
        for (int i = this.l.size - 1; i >= 0; i--) {
            ParticleEffect particleEffect = this.l.items[i];
            if (particleEffect.isComplete()) {
                this.l.removeIndex(i);
                particleEffect.reset();
                freeParticle(particleEffect);
            } else if (particleEffect.getEmitters().size != 0 && particleEffect.getEmitters().first().getName().charAt(0) == '#') {
                particleEffect.update(f);
            } else {
                particleEffect.update(f2);
            }
        }
    }

    private void a(float f) {
        if (f > 0.1f) {
            f = 0.1f;
        }
        this.m.begin();
        for (int i = 0; i < this.m.size; i++) {
            XpOrbParticle xpOrbParticle = this.m.items[i];
            O.set(xpOrbParticle.h, xpOrbParticle.i).sub(xpOrbParticle.f, xpOrbParticle.g).nor().scl(384.0f);
            xpOrbParticle.d += (xpOrbParticle.d * (-f)) + (O.x * f);
            xpOrbParticle.e += (xpOrbParticle.e * (-f)) + (O.y * f);
            xpOrbParticle.f += xpOrbParticle.d * f;
            xpOrbParticle.g += xpOrbParticle.e * f;
            if (PMath.getSquareDistanceBetweenPoints(xpOrbParticle.f, xpOrbParticle.g, xpOrbParticle.h, xpOrbParticle.i) < 16.0f) {
                this.m.removeIndex(i);
                this.x.free(xpOrbParticle);
            }
        }
        this.m.end();
    }

    private void e(float f, float f2) {
        float min = Math.min(f, f2);
        this.p.begin();
        for (int i = 0; i < this.p.size; i++) {
            CoinParticle coinParticle = this.p.items[i];
            float apply = Interpolation.pow2Out.apply(1.0f - (coinParticle.c / 1.5f)) * coinParticle.f3011b;
            coinParticle.f3010a.y += apply * min;
            coinParticle.c += min;
            if (coinParticle.c >= 1.5f) {
                this.p.removeIndex(i);
                this.z.free(coinParticle);
            }
        }
        this.p.end();
    }

    private void b(float f) {
        for (int i = 0; i < this.r.size; i++) {
            ChainLightning chainLightning = this.r.items[i];
            chainLightning.update(f);
            if (chainLightning.isFinished()) {
                this.r.removeIndex(i);
                chainLightning.free();
            }
        }
    }

    private void c(float f) {
        this.w.begin();
        for (int i = 0; i < this.w.size; i++) {
            FlashParticle flashParticle = this.w.items[i];
            flashParticle.time += f;
            if (flashParticle.time >= 0.17f) {
                this.w.removeIndex(i);
                this.F.free(flashParticle);
            }
        }
        this.w.end();
    }

    private void a(Batch batch, float f) {
        if (this.n.size != 0) {
            batch.end();
            batch.begin();
            batch.setBlendFunction(770, 771);
            a(batch, this.n, f);
            batch.end();
            batch.setBlendFunction(770, 1);
            batch.begin();
        }
    }

    private void a(Batch batch) {
        batch.setColor(Color.WHITE);
        int i = this.k.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.k.items[i2].draw(batch);
        }
    }

    private void b(Batch batch) {
        batch.setColor(Color.WHITE);
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
            batch.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        }
        for (int i = 0; i < this.m.size; i++) {
            XpOrbParticle xpOrbParticle = this.m.items[i];
            float min = StrictMath.min(PMath.getSquareDistanceBetweenPoints(xpOrbParticle.f, xpOrbParticle.g, xpOrbParticle.h, xpOrbParticle.i) * 0.00390625f, PMath.getSquareDistanceBetweenPoints(xpOrbParticle.f, xpOrbParticle.g, xpOrbParticle.f3017b, xpOrbParticle.c) * 0.00390625f);
            float f = min;
            if (min > 1.0f) {
                f = 1.0f;
            }
            float f2 = xpOrbParticle.f3016a * f;
            batch.draw(xpOrbParticle.j, xpOrbParticle.f - (f2 * 0.5f), xpOrbParticle.g - (f2 * 0.5f), f2, f2);
        }
    }

    private void c(Batch batch) {
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        if (Game.i.settingsManager.isFlyingCoinsEnabled()) {
            this.p.begin();
            for (int i = 0; i < this.p.size; i++) {
                CoinParticle coinParticle = this.p.items[i];
                float f = 1.0f;
                if (1.5f - coinParticle.c < 0.3f) {
                    f = (1.5f - coinParticle.c) / 0.3f;
                }
                batch.setColor(1.0f, 1.0f, 1.0f, f);
                batch.draw(this.I, coinParticle.f3010a.x - 34.0f, coinParticle.f3010a.y - 12.0f, 24.0f, 24.0f);
                a(coinParticle.d, coinParticle.f3010a.x, coinParticle.f3010a.y + 7.0f).draw(batch, f);
            }
            this.p.end();
            batch.setColor(Color.WHITE);
        }
    }

    private void d(Batch batch) {
        batch.setColor(Color.WHITE);
        this.w.begin();
        for (int i = 0; i < this.w.size; i++) {
            FlashParticle flashParticle = this.w.items[i];
            float f = (0.17f - flashParticle.time) / 0.17f;
            R.set(flashParticle.color);
            R.f889a = f;
            float floatBits = R.toFloatBits();
            for (int i2 = 0; i2 < 4; i2++) {
                flashParticle.vertices[(i2 * 5) + 2] = floatBits;
            }
            batch.draw(flashParticle.texture, flashParticle.vertices, 0, 20);
        }
        this.w.end();
        batch.setColor(Color.WHITE);
    }

    public final void drawDamageParticles(Batch batch) {
        float f;
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DAMAGE_PARTICLES_ENABLED) != 0.0d && this.q.size != 0) {
            this.q.sort(A);
            this.q.begin();
            Texture texture = Game.i.assetManager.getBlankWhiteTextureRegion().getTexture();
            for (int i = 0; i < this.q.size; i++) {
                DamageParticle damageParticle = this.q.items[i];
                if (damageParticle.k < 0.07f) {
                    f = 1.85f;
                } else if (damageParticle.k < 0.19f) {
                    f = 1.0f + (0.85f * (1.0f - ((damageParticle.k - 0.07f) / 0.12f)));
                } else {
                    f = 1.0f;
                }
                float f2 = f * damageParticle.g;
                float f3 = damageParticle.k / damageParticle.i;
                float f4 = 1.0f;
                float f5 = 1.0f;
                if (f3 >= 0.07f) {
                    if (f3 < 0.3f) {
                        f5 = 0.82f + (0.18f * (1.0f - ((f3 - 0.07f) / 0.23f)));
                    } else if (f3 < 0.7f) {
                        f5 = 0.82f;
                    } else {
                        f4 = 1.0f - ((f3 - 0.7f) / 0.3f);
                    }
                }
                damageParticle.a();
                float[] vertices = damageParticle.getFontCache().getVertices();
                int vertexCount = damageParticle.getFontCache().getVertexCount(0);
                if (X.length < vertexCount) {
                    X = new float[vertexCount];
                }
                System.arraycopy(vertices, 0, X, 0, vertexCount);
                float f6 = f5;
                float floatBits = R.set(damageParticle.getFontCache().getColor()).mul(f6, f6, f5, f4).toFloatBits();
                for (int i2 = 2; i2 < vertexCount; i2 += 5) {
                    X[i2] = floatBits;
                }
                float f7 = 0.0f;
                float f8 = 0.0f;
                for (int i3 = 0; i3 < vertexCount; i3 += 20) {
                    float f9 = X[i3] * f2;
                    float f10 = X[i3 + 1] * f2;
                    float f11 = X[i3 + 10] * f2;
                    float f12 = X[i3 + 11] * f2;
                    float max = Math.max(f7, f11);
                    f7 = max;
                    f8 = Math.max(max, f12);
                    float[] fArr = X;
                    fArr[i3 + 5] = f9;
                    fArr[i3] = f9;
                    X[i3 + 16] = f10;
                    X[i3 + 1] = f10;
                    X[i3 + 15] = f11;
                    X[i3 + 10] = f11;
                    X[i3 + 11] = f12;
                    X[i3 + 6] = f12;
                }
                for (int i4 = 0; i4 < vertexCount; i4 += 5) {
                    float[] fArr2 = X;
                    int i5 = i4;
                    fArr2[i5] = fArr2[i5] + (damageParticle.f3012a - (f7 * 0.5f));
                    float[] fArr3 = X;
                    int i6 = i4 + 1;
                    fArr3[i6] = fArr3[i6] + damageParticle.f3013b + (f8 * 0.5f);
                }
                batch.draw(texture, X, 0, vertexCount);
            }
            this.q.end();
            batch.setColor(Color.WHITE);
        }
    }

    private void e(Batch batch) {
        for (int i = 0; i < this.r.size; i++) {
            this.r.items[i].draw(batch);
        }
    }

    private void b(Batch batch, float f) {
        if (this.o.size != 0 || this.l.size != 0) {
            batch.end();
            batch.begin();
            batch.setBlendFunction(770, 771);
            a(batch, this.o, f);
            batch.setColor(Color.WHITE);
            int i = this.l.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.l.items[i2].draw(batch);
            }
            batch.setColor(Color.WHITE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateDraw(float f, float f2) {
        ObjectMap<String, ParticleEffectPool.PooledEffect> attachedParticles;
        if (this.S.gameState != null && this.S.gameState.isGameOver()) {
            f2 = f;
        }
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy.EnemyReference enemyReference = this.S.map.spawnedEnemies.items[i];
            if (enemyReference != null && enemyReference.enemy != null && (attachedParticles = enemyReference.enemy.getAttachedParticles()) != null) {
                ObjectMap.Entries<String, ParticleEffectPool.PooledEffect> it = attachedParticles.iterator();
                while (it.hasNext()) {
                    ((ParticleEffectPool.PooledEffect) it.next().value).setPosition(enemyReference.enemy.getPosition().x, enemyReference.enemy.getPosition().y);
                }
            }
        }
        c(f * 0.5f, f2 * 0.5f);
        d(f * 0.5f, f2 * 0.5f);
        a(f2);
        e(f, f2);
        b(f, f2);
        c(f2);
        b(f2);
        IntFloatMap.Keys keys = this.N.keys();
        while (keys.hasNext) {
            int next = keys.next();
            float f3 = this.N.get(next, 0.0f);
            if (f3 > 0.0f) {
                this.N.put(next, f3 - f2);
            }
        }
        if (this.S.gameState != null && this.S.gameState.updateNumber != this.M) {
            this.M = this.S.gameState.updateNumber;
            this.L.clear();
        }
        if (Game.i.debugManager.isEnabled()) {
            Game.i.debugManager.registerValue("Particles count").append(this.k.size);
        }
        if (Game.i.debugManager.isEnabled()) {
            Game.i.debugManager.registerValue("Limited particles added/skipped").append(this.J).append("/").append(this.K);
        }
    }

    public final void draw(Batch batch, float f, float f2) {
        a(batch, f2);
        a(batch);
        e(batch);
        b(batch);
        c(batch);
        d(batch);
        b(batch, f2);
    }

    private void a(Batch batch, DelayedRemovalArray<ShatterPolygon> delayedRemovalArray, float f) {
        if (delayedRemovalArray.size != 0) {
            delayedRemovalArray.begin();
            for (int i = 0; i < delayedRemovalArray.size; i++) {
                ShatterPolygon shatterPolygon = delayedRemovalArray.items[i];
                shatterPolygon.draw(batch, Q, f);
                if (shatterPolygon.g > shatterPolygon.e) {
                    delayedRemovalArray.removeIndex(i);
                    this.y.free(shatterPolygon);
                }
            }
            delayedRemovalArray.end();
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        Game.i.debugManager.unregisterValue("Particles count");
        Game.i.debugManager.unregisterValue("Limited particles added/skipped");
        Game.i.debugManager.unregisterValue("Particles remove queue");
        this.k.clear();
        this.l.clear();
        this.m.clear();
        this.o.clear();
        this.n.clear();
        this.p.clear();
        this.x.clear();
        this.z.clear();
        this.G.clear();
        this.H = null;
        this.I = null;
        super.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ParticleSystem$XpOrbParticle.class */
    public static class XpOrbParticle {
        private TextureRegion j;

        /* renamed from: a, reason: collision with root package name */
        float f3016a;

        /* renamed from: b, reason: collision with root package name */
        float f3017b;
        float c;
        float d;
        float e;
        float f;
        float g;
        float h;
        float i;

        private XpOrbParticle() {
        }

        /* synthetic */ XpOrbParticle(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ParticleSystem$CoinParticle.class */
    public static class CoinParticle {

        /* renamed from: a, reason: collision with root package name */
        Vector2 f3010a;

        /* renamed from: b, reason: collision with root package name */
        float f3011b;
        float c;
        int d;

        private CoinParticle() {
            this.f3010a = new Vector2();
        }

        /* synthetic */ CoinParticle(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ParticleSystem$DamageParticle.class */
    public static final class DamageParticle implements Pool.Poolable {

        /* renamed from: a, reason: collision with root package name */
        float f3012a;

        /* renamed from: b, reason: collision with root package name */
        float f3013b;
        float c;
        float d;
        long e;
        int f;
        float g = 1.0f;
        final int h;
        final float i;
        final byte j;
        float k;
        float l;
        private BitmapFontCache m;
        private boolean n;

        public DamageParticle(int i) {
            reset();
            this.h = i;
            if (DamageType.Efficiency.isEspeciallyEffective(i)) {
                this.i = 1.5f;
                this.j = (byte) 0;
                return;
            }
            if (DamageType.Efficiency.isCritical(i)) {
                this.i = 1.1624999f;
                this.j = (byte) 1;
            } else if (DamageType.Efficiency.isOverTime(i)) {
                this.i = 1.05f;
                this.j = (byte) 2;
            } else if (DamageType.Efficiency.isEffective(i)) {
                this.i = 0.90000004f;
                this.j = (byte) 3;
            } else {
                this.i = 0.75f;
                this.j = (byte) 4;
            }
        }

        public final boolean isPrepared() {
            return this.n;
        }

        public final BitmapFontCache getFontCache() {
            if (this.m == null) {
                if (DamageType.Efficiency.isOverTime(this.h)) {
                    this.m = new BitmapFontCache(Game.i.assetManager.getDamageNumbersOverTimeFont());
                } else if (DamageType.Efficiency.isEspeciallyEffective(this.h)) {
                    this.m = new BitmapFontCache(Game.i.assetManager.getDamageNumbersEspeciallyEffectiveFont());
                } else {
                    this.m = new BitmapFontCache(Game.i.assetManager.getDamageNumbersFont());
                }
                if (DamageType.Efficiency.isEspeciallyEffective(this.h)) {
                    this.m.setColor(MaterialColor.PINK.P300);
                } else if (DamageType.Efficiency.isCritical(this.h)) {
                    this.m.setColor(MaterialColor.ORANGE.P200);
                } else if (DamageType.Efficiency.isEffective(this.h)) {
                    this.m.setColor(MaterialColor.LIGHT_GREEN.P300);
                } else if (DamageType.Efficiency.isWeak(this.h)) {
                    this.m.setColor(MaterialColor.GREY.P400);
                } else if (DamageType.Efficiency.isOverTime(this.h)) {
                    this.m.setColor(MaterialColor.BLUE.P200);
                } else {
                    this.m.setColor(Color.WHITE);
                }
                this.m.setUseIntegerPositions(true);
            }
            return this.m;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (!this.n) {
                BitmapFontCache fontCache = getFontCache();
                fontCache.clear();
                StringBuilder commaSeparatedNumber = StringFormatter.commaSeparatedNumber(this.e);
                if (DamageType.Efficiency.isCritical(this.h)) {
                    commaSeparatedNumber.append('!');
                }
                fontCache.addText(commaSeparatedNumber, 0.0f, 0.0f);
                float f = 0.0f;
                for (int i = 0; i < fontCache.getLayouts().size; i++) {
                    f = Math.max(f, fontCache.getLayouts().get(i).width);
                }
                this.n = true;
            }
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public final void reset() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.n = false;
            this.e = 0L;
            this.f = 0;
            this.g = 1.0f;
            this.c = 48.0f + (FastRandom.getFloat() * 24.0f);
            if (FastRandom.getFloat() < 0.5f) {
                this.c = -this.c;
            }
            this.d = (0.9f + (FastRandom.getFloat() * 0.1f)) * 48.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ParticleSystem$FlashParticle.class */
    public static class FlashParticle {
        public Texture texture;
        public float[] vertices;
        public float time;
        public Color color;

        private FlashParticle() {
            this.vertices = new float[20];
            this.color = Color.WHITE.cpy();
        }

        /* synthetic */ FlashParticle(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ParticleSystem$ShatterPolygon.class */
    public static class ShatterPolygon implements Pool.Poolable {

        /* renamed from: a, reason: collision with root package name */
        private Polygon f3014a;

        /* renamed from: b, reason: collision with root package name */
        private float[] f3015b;
        private Vector2 c;
        private float[] d;
        private float e;
        private float f;
        private float g;
        private Texture h;
        private Color i;
        private float j;
        private Interpolation k;

        private ShatterPolygon() {
            this.f3014a = new Polygon();
            this.f3015b = new float[8];
            this.c = new Vector2();
            this.d = new float[8];
            this.i = Color.WHITE.cpy();
        }

        /* synthetic */ ShatterPolygon(byte b2) {
            this();
        }

        static /* synthetic */ float c(ShatterPolygon shatterPolygon, float f) {
            shatterPolygon.g = 0.0f;
            return 0.0f;
        }

        public void draw(Batch batch, float[] fArr, float f) {
            float apply = this.k.apply(this.g / this.e);
            float f2 = apply - this.j;
            this.j = apply;
            float f3 = 1.0f - (this.g / this.e);
            ParticleSystem.R.set(this.i);
            ParticleSystem.R.f889a *= Interpolation.pow5Out.apply(f3);
            float floatBits = ParticleSystem.R.toFloatBits();
            this.f3014a.translate(this.c.x * f2, this.c.y * f2);
            this.f3014a.rotate(this.f * 270.0f * (1.5f - f3) * f2);
            float[] transformedVertices = this.f3014a.getTransformedVertices();
            for (int i = 0; i < 4; i++) {
                fArr[i * 5] = transformedVertices[i << 1];
                fArr[(i * 5) + 1] = transformedVertices[(i << 1) + 1];
                fArr[(i * 5) + 2] = floatBits;
                fArr[(i * 5) + 3] = this.f3015b[i << 1];
                fArr[(i * 5) + 4] = this.f3015b[(i << 1) + 1];
            }
            this.g += f;
            batch.draw(this.h, fArr, 0, 20);
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            this.j = 0.0f;
            this.h = null;
        }
    }
}
