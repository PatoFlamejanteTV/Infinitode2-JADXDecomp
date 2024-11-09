package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.events.game.ProjectileDespawn;
import com.prineside.tdi2.events.game.ProjectileSpawn;
import com.prineside.tdi2.projectiles.AirProjectile;
import com.prineside.tdi2.projectiles.BasicProjectile;
import com.prineside.tdi2.projectiles.BuffProjectile;
import com.prineside.tdi2.projectiles.BulletWallProjectile;
import com.prineside.tdi2.projectiles.CannonProjectile;
import com.prineside.tdi2.projectiles.ChainLightningProjectile;
import com.prineside.tdi2.projectiles.LaserProjectile;
import com.prineside.tdi2.projectiles.MissileProjectile;
import com.prineside.tdi2.projectiles.MultishotProjectile;
import com.prineside.tdi2.projectiles.SplashProjectile;
import com.prineside.tdi2.projectiles.SplinterProjectile;
import com.prineside.tdi2.projectiles.VenomProjectile;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ProjectileSystem.class */
public final class ProjectileSystem extends GameSystem {
    public DelayedRemovalArray<Projectile> projectiles = new DelayedRemovalArray<>(false, 16, Projectile.class);
    public int nextProjectileId = 1;

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private final Projectile.Factory<?>[] f3032a = new Projectile.Factory[ProjectileType.values.length];

    @NAGS
    public final Factories F = new Factories();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ProjectileSystem$Factories.class */
    public static final class Factories {
        public final AirProjectile.AirProjectileFactory AIR = new AirProjectile.AirProjectileFactory();
        public final BasicProjectile.BasicProjectileFactory BASIC = new BasicProjectile.BasicProjectileFactory();
        public final CannonProjectile.CannonProjectileFactory CANNON = new CannonProjectile.CannonProjectileFactory();
        public final SplinterProjectile.SplinterProjectileFactory SPLINTER = new SplinterProjectile.SplinterProjectileFactory();
        public final ChainLightningProjectile.ChainLightningProjectileFactory CHAIN_LIGHTNING = new ChainLightningProjectile.ChainLightningProjectileFactory();
        public final MissileProjectile.MissileProjectileFactory MISSILE = new MissileProjectile.MissileProjectileFactory();
        public final MultishotProjectile.MultishotProjectileFactory MULTISHOT = new MultishotProjectile.MultishotProjectileFactory();
        public final SplashProjectile.SplashProjectileFactory SPLASH = new SplashProjectile.SplashProjectileFactory();
        public final VenomProjectile.VenomProjectileFactory VENOM = new VenomProjectile.VenomProjectileFactory();
        public final LaserProjectile.LaserProjectileFactory LASER = new LaserProjectile.LaserProjectileFactory();
        public final BulletWallProjectile.MultishotProjectileFactory BULLET_WALL = new BulletWallProjectile.MultishotProjectileFactory();
        public final BuffProjectile.BuffProjectileFactory BUFF = new BuffProjectile.BuffProjectileFactory();
    }

    public ProjectileSystem() {
        this.f3032a[ProjectileType.AIR.ordinal()] = this.F.AIR;
        this.f3032a[ProjectileType.BASIC.ordinal()] = this.F.BASIC;
        this.f3032a[ProjectileType.CANNON.ordinal()] = this.F.CANNON;
        this.f3032a[ProjectileType.SPLINTER.ordinal()] = this.F.SPLINTER;
        this.f3032a[ProjectileType.CHAIN_LIGHTNING.ordinal()] = this.F.CHAIN_LIGHTNING;
        this.f3032a[ProjectileType.MISSILE.ordinal()] = this.F.MISSILE;
        this.f3032a[ProjectileType.MULTISHOT.ordinal()] = this.F.MULTISHOT;
        this.f3032a[ProjectileType.SPLASH.ordinal()] = this.F.SPLASH;
        this.f3032a[ProjectileType.VENOM.ordinal()] = this.F.VENOM;
        this.f3032a[ProjectileType.LASER.ordinal()] = this.F.LASER;
        this.f3032a[ProjectileType.BULLET_WALL.ordinal()] = this.F.BULLET_WALL;
        this.f3032a[ProjectileType.BUFF.ordinal()] = this.F.BUFF;
        for (ProjectileType projectileType : ProjectileType.values) {
            if (this.f3032a[projectileType.ordinal()] == null) {
                throw new RuntimeException("Not all projectile factories were created");
            }
        }
        for (Projectile.Factory<?> factory : this.f3032a) {
            factory.setup(this);
        }
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.projectiles);
        output.writeInt(this.nextProjectileId);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.projectiles = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.nextProjectileId = input.readInt();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        if (!this.S.CFG.headless) {
            a();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PROJECTILE_DRAW, true, (batch, f, f2, f3) -> {
            draw(batch, f2, f3);
        }).setName("Projectile-draw"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Projectile.Factory<? extends Projectile> getFactory(ProjectileType projectileType) {
        return this.f3032a[projectileType.ordinal()];
    }

    public final void register(Projectile projectile) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (projectile.isRegistered()) {
            throw new RuntimeException("Already registered");
        }
        int i = this.nextProjectileId;
        this.nextProjectileId = i + 1;
        projectile.id = i;
        projectile.setRegistered(this.S);
        this.projectiles.add(projectile);
        this.S.events.getListeners(ProjectileSpawn.class).trigger(new ProjectileSpawn(projectile));
        if (Game.i.debugManager == null || !Game.i.debugManager.isEnabled()) {
            return;
        }
        Game.i.debugManager.registerValue("Projectiles count").append(this.projectiles.size);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        IntArray intArray = null;
        this.projectiles.begin();
        int i = this.projectiles.size;
        for (int i2 = 0; i2 < i; i2++) {
            Projectile projectile = this.projectiles.items[i2];
            projectile.update(f);
            if (projectile.isDone()) {
                if (intArray == null) {
                    intArray = new IntArray();
                }
                intArray.add(i2);
            }
        }
        this.projectiles.end();
        if (intArray != null) {
            this.projectiles.begin();
            int i3 = intArray.size;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = intArray.items[i4];
                Projectile projectile2 = this.projectiles.items[i5];
                projectile2.onDone();
                this.S.events.trigger(new ProjectileDespawn(projectile2));
                projectile2.setUnregistered();
                getFactory(projectile2.type).free(projectile2);
                this.projectiles.removeIndex(i5);
            }
            this.projectiles.end();
        }
        if (Game.i.debugManager == null || !Game.i.debugManager.isEnabled()) {
            return;
        }
        Game.i.debugManager.registerValue("Projectiles count").append(this.projectiles.size);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Projectile";
    }

    public final void draw(Batch batch, float f, float f2) {
        if (Game.i.settingsManager.isProjectilesDrawing()) {
            for (int i = this.projectiles.size - 1; i >= 0; i--) {
                this.projectiles.items[i].applyDrawInterpolation(f2);
                this.projectiles.items[i].draw(batch, f);
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.projectiles.clear();
        Game.i.debugManager.unregisterValue("Projectiles count");
        super.dispose();
    }
}
