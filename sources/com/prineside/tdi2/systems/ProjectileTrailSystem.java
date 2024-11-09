package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.ProjectileTrail;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ProjectileTrailSystem.class */
public final class ProjectileTrailSystem extends GameSystem {
    private boolean c;
    private static final IntArray d = new IntArray();
    private float g;

    /* renamed from: a, reason: collision with root package name */
    private final DelayedRemovalArray<ProjectileTrail> f3033a = new DelayedRemovalArray<>(false, 16, ProjectileTrail.class);

    /* renamed from: b, reason: collision with root package name */
    private final DelayedRemovalArray<ProjectileTrail> f3034b = new DelayedRemovalArray<>(false, 16, ProjectileTrail.class);
    private final int e = Math.max(2, Runtime.getRuntime().availableProcessors());
    private final ExecutorService f = Executors.newFixedThreadPool(this.e, new ThreadFactory(this) { // from class: com.prineside.tdi2.systems.ProjectileTrailSystem.1

        /* renamed from: a, reason: collision with root package name */
        private int f3035a = 0;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder("ProjectileTrailSystem-");
            int i = this.f3035a;
            this.f3035a = i + 1;
            Thread thread = new Thread(runnable, sb.append(i).toString());
            thread.setDaemon(true);
            return thread;
        }
    });
    private final ArrayList<TrailsHandler> h = new ArrayList<>();

    public ProjectileTrailSystem() {
        for (int i = 0; i < this.e; i++) {
            this.h.add(new TrailsHandler(this, (byte) 0));
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PROJECTILE_TRAIL_UPDATE_DRAW, false, (batch, f, f2, f3) -> {
            updateDraw(f2);
        }).setName("ProjectileTrail-updateDraw"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PROJECTILE_TRAIL_DRAW_OPAQUE, false, (batch2, f4, f5, f6) -> {
            drawOpaque(batch2);
        }).setName("ProjectileTrail-drawOpaque"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PROJECTILE_TRAIL_DRAW, true, (batch3, f7, f8, f9) -> {
            draw(batch3);
        }).setName("ProjectileTrail-draw"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "ProjectileTrail";
    }

    public final void addTrail(ProjectileTrail projectileTrail) {
        if (!isEnabled()) {
            projectileTrail.free();
        } else {
            this.f3034b.add(projectileTrail);
        }
    }

    public final void addOpaqueTrail(ProjectileTrail projectileTrail) {
        if (!isEnabled()) {
            projectileTrail.free();
        } else {
            this.f3033a.add(projectileTrail);
        }
    }

    public final boolean isEnabled() {
        return this.c;
    }

    public final void updateDraw(float f) {
        if (!isEnabled()) {
            return;
        }
        this.g = f;
        d.clear();
        if (this.f3034b.size > Math.max(this.e * 10, 200) && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MULTITHREADING) != 0.0d) {
            a();
            for (int i = 0; i < this.e; i++) {
                TrailsHandler trailsHandler = this.h.get(i);
                if (trailsHandler.c.size != 0) {
                    d.addAll(trailsHandler.c);
                }
            }
            d.sort();
        } else {
            int i2 = this.f3034b.size;
            for (int i3 = 0; i3 < i2; i3++) {
                ProjectileTrail projectileTrail = this.f3034b.items[i3];
                projectileTrail.update(f);
                if (projectileTrail.isFinished()) {
                    d.add(i3);
                }
            }
        }
        if (d.size != 0) {
            for (int i4 = 0; i4 < d.size; i4++) {
                this.f3034b.items[d.items[i4]].free();
            }
            d.reverse();
            PMath.removeArrayIndicesDirect(this.f3034b, d);
            d.clear();
        }
        d.clear();
        int i5 = this.f3033a.size;
        for (int i6 = 0; i6 < i5; i6++) {
            ProjectileTrail projectileTrail2 = this.f3033a.items[i6];
            projectileTrail2.update(f);
            if (projectileTrail2.isFinished()) {
                d.add(i6);
            }
        }
        if (d.size != 0) {
            for (int i7 = 0; i7 < d.size; i7++) {
                this.f3033a.items[d.items[i7]].free();
            }
            d.reverse();
            PMath.removeArrayIndicesDirect(this.f3033a, d);
            d.clear();
        }
    }

    private void a() {
        double d2 = this.f3034b.size / this.e;
        double d3 = 0.0d;
        int i = 0;
        while (i < this.e) {
            int i2 = (int) d3;
            d3 += d2;
            int i3 = (i == this.e - 1 ? this.f3034b.size : (int) d3) - 1;
            TrailsHandler trailsHandler = this.h.get(i);
            trailsHandler.f3036a = i2;
            trailsHandler.f3037b = i3;
            i++;
        }
        try {
            this.f.invokeAll(this.h);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Failed to loop through the array", e);
        }
    }

    public final void drawOpaque(Batch batch) {
        if (isEnabled()) {
            int i = this.f3033a.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.f3033a.items[i2].draw(batch);
            }
        }
    }

    public final void draw(Batch batch) {
        this.c = Game.i.settingsManager.isProjectileTrailsDrawing() && Game.i.settingsManager.isProjectilesDrawing() && !this.S.gameState.canSkipMediaUpdate();
        if (isEnabled()) {
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            int i = this.f3034b.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.f3034b.items[i2].draw(batch);
            }
        }
        if (Game.i.debugManager.isEnabled()) {
            Game.i.debugManager.registerValue("Projectile trails").append(this.f3034b.size);
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        Game.i.debugManager.unregisterValue("Particles count");
        this.f3034b.clear();
        this.f3033a.clear();
        this.f.shutdown();
        super.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ProjectileTrailSystem$TrailsHandler.class */
    public final class TrailsHandler implements Callable<Object> {

        /* renamed from: a, reason: collision with root package name */
        private int f3036a;

        /* renamed from: b, reason: collision with root package name */
        private int f3037b;
        private final IntArray c;

        private TrailsHandler() {
            this.c = new IntArray();
        }

        /* synthetic */ TrailsHandler(ProjectileTrailSystem projectileTrailSystem, byte b2) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public final Object call() {
            this.c.clear();
            int i = this.f3037b;
            for (int i2 = this.f3036a; i2 <= i; i2++) {
                ProjectileTrail projectileTrail = ((ProjectileTrail[]) ProjectileTrailSystem.this.f3034b.items)[i2];
                projectileTrail.update(ProjectileTrailSystem.this.g);
                if (projectileTrail.isFinished()) {
                    this.c.add(i2);
                }
            }
            return null;
        }
    }
}
