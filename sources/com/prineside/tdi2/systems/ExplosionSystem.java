package com.prineside.tdi2.systems;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.explosions.AirFallExplosion;
import com.prineside.tdi2.explosions.CannonExplosion;
import com.prineside.tdi2.explosions.FireballExplosion;
import com.prineside.tdi2.explosions.GenericExplosion;
import com.prineside.tdi2.explosions.MissileExplosion;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ExplosionSystem.class */
public final class ExplosionSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private Array<Explosion> f2940a = new Array<>(false, 16, Explosion.class);

    @NAGS
    public final Factories F = new Factories();

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private final Explosion.Factory<?>[] f2941b = new Explosion.Factory[ExplosionType.values.length];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ExplosionSystem$Factories.class */
    public static final class Factories {
        public final CannonExplosion.CannonExplosionFactory CANNON = new CannonExplosion.CannonExplosionFactory();
        public final MissileExplosion.MissileExplosionFactory MISSILE = new MissileExplosion.MissileExplosionFactory();
        public final FireballExplosion.FireballExplosionFactory FIREBALL = new FireballExplosion.FireballExplosionFactory();
        public final AirFallExplosion.AirFallExplosionFactory AIR_FALL = new AirFallExplosion.AirFallExplosionFactory();
        public final GenericExplosion.GenericExplosionFactory GENERIC = new GenericExplosion.GenericExplosionFactory();
    }

    public ExplosionSystem() {
        this.f2941b[ExplosionType.CANNON.ordinal()] = this.F.CANNON;
        this.f2941b[ExplosionType.MISSILE.ordinal()] = this.F.MISSILE;
        this.f2941b[ExplosionType.FIREBALL.ordinal()] = this.F.FIREBALL;
        this.f2941b[ExplosionType.AIR_FALL.ordinal()] = this.F.AIR_FALL;
        this.f2941b[ExplosionType.GENERIC.ordinal()] = this.F.GENERIC;
        for (ExplosionType explosionType : ExplosionType.values) {
            if (this.f2941b[explosionType.ordinal()] == null) {
                throw new RuntimeException("Not all explosion factories were created");
            }
        }
        for (Explosion.Factory<?> factory : this.f2941b) {
            factory.setup(this);
        }
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2940a);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2940a = (Array) kryo.readObject(input, Array.class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Explosion.Factory<? extends Explosion> getFactory(ExplosionType explosionType) {
        return this.f2941b[explosionType.ordinal()];
    }

    public final void register(Explosion explosion) {
        this.S.gameState.checkGameplayUpdateAllowed();
        explosion.setRegistered(this.S);
        this.f2940a.add(explosion);
        if (Game.i.debugManager == null || !Game.i.debugManager.isEnabled()) {
            return;
        }
        Game.i.debugManager.registerValue("Explosions count").append(this.f2940a.size);
    }

    private void a(int i) {
        this.f2940a.items[i].setUnregistered();
        this.f2940a.removeIndex(i);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        for (int i = this.f2940a.size - 1; i >= 0; i--) {
            this.f2940a.items[i].update(f);
        }
        for (int i2 = this.f2940a.size - 1; i2 >= 0; i2--) {
            if (this.f2940a.items[i2].isDone()) {
                a(i2);
                if (Game.i.debugManager != null && Game.i.debugManager.isEnabled()) {
                    Game.i.debugManager.registerValue("Explosions count").append(this.f2940a.size);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Explosion";
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        for (int i = 0; i < this.f2940a.size; i++) {
            this.f2940a.items[i].setUnregistered();
        }
        this.f2940a.clear();
        Game.i.debugManager.unregisterValue("Explosions count");
        super.dispose();
    }
}
