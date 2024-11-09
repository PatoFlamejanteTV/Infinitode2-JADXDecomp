package com.prineside.tdi2;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/DamageRecord.class */
public final class DamageRecord implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private Enemy f1691a;

    /* renamed from: b, reason: collision with root package name */
    private float f1692b;
    private float c;

    @Null
    private Tower f;

    @Null
    private Ability g;

    @Null
    private Projectile h;
    private boolean j;
    private boolean k;

    @Null
    private Unit l;

    @Null
    private Explosion m;
    private DamageType d = DamageType.BULLET;
    private int e = 0;
    private boolean i = true;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.f1691a);
        kryo.writeClassAndObject(output, this.f);
        output.writeFloat(this.f1692b);
        output.writeFloat(this.c);
        kryo.writeObject(output, this.d);
        kryo.writeClassAndObject(output, this.g);
        output.writeBoolean(this.i);
        output.writeBoolean(this.j);
        kryo.writeClassAndObject(output, this.h);
        output.writeInt(this.e);
        output.writeBoolean(this.k);
        kryo.writeClassAndObject(output, this.l);
        kryo.writeClassAndObject(output, this.m);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f1691a = (Enemy) kryo.readClassAndObject(input);
        this.f = (Tower) kryo.readClassAndObject(input);
        this.f1692b = input.readFloat();
        this.c = input.readFloat();
        this.d = (DamageType) kryo.readObject(input, DamageType.class);
        this.g = (Ability) kryo.readClassAndObject(input);
        this.i = input.readBoolean();
        this.j = input.readBoolean();
        this.h = (Projectile) kryo.readClassAndObject(input);
        this.e = input.readInt();
        this.k = input.readBoolean();
        this.l = (Unit) kryo.readClassAndObject(input);
        this.m = (Explosion) kryo.readClassAndObject(input);
    }

    public DamageRecord() {
        reset();
    }

    public final DamageRecord setup(Enemy enemy, float f, DamageType damageType) {
        setEnemy(enemy);
        setDamage(f);
        setDamageType(damageType);
        return this;
    }

    public final DamageRecord copyFor(Enemy enemy, DamageRecord damageRecord) {
        DamageRecord upVar = damageRecord.setup(enemy, this.f1692b, this.d);
        upVar.c = this.c;
        upVar.e = this.e;
        upVar.f = this.f;
        upVar.g = this.g;
        upVar.h = this.h;
        upVar.i = this.i;
        upVar.j = this.j;
        upVar.k = this.k;
        upVar.l = this.l;
        upVar.m = this.m;
        return upVar;
    }

    public final void reset() {
        this.f1691a = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.e = 0;
        this.i = true;
        this.j = false;
        this.k = false;
        this.l = null;
        this.m = null;
    }

    public final Enemy getEnemy() {
        return this.f1691a;
    }

    public final DamageRecord setEnemy(Enemy enemy) {
        Preconditions.checkNotNull(enemy);
        this.f1691a = enemy;
        return this;
    }

    @Null
    public final Unit getUnit() {
        return this.l;
    }

    public final DamageRecord setUnit(@Null Unit unit) {
        this.l = unit;
        return this;
    }

    @Null
    public final Explosion getExplosion() {
        return this.m;
    }

    public final DamageRecord setExplosion(@Null Explosion explosion) {
        this.m = explosion;
        return this;
    }

    @Null
    public final Tower getTower() {
        if (this.f == null || this.f.isRegistered()) {
            return this.f;
        }
        return null;
    }

    public final DamageRecord setTower(@Null Tower tower) {
        this.f = tower;
        return this;
    }

    public final float getDamage() {
        return this.f1692b;
    }

    public final DamageRecord setDamage(float f) {
        Preconditions.checkArgument(f > 0.0f && PMath.isFinite(f), "Invalid damage: %s", Float.valueOf(f));
        this.f1692b = f;
        return this;
    }

    public final float getFactDamage() {
        return this.c;
    }

    public final DamageRecord setFactDamage(float f) {
        Preconditions.checkArgument(f > 0.0f && PMath.isFinite(f), "Invalid factDamage: %s", Float.valueOf(f));
        this.c = f;
        return this;
    }

    public final DamageType getDamageType() {
        return this.d;
    }

    public final DamageRecord setDamageType(DamageType damageType) {
        Preconditions.checkNotNull(damageType);
        this.d = damageType;
        return this;
    }

    @Null
    public final Ability getAbility() {
        return this.g;
    }

    public final DamageRecord setAbility(@Null Ability ability) {
        this.g = ability;
        return this;
    }

    public final boolean isCleanForDps() {
        return this.i;
    }

    public final DamageRecord setCleanForDps(boolean z) {
        this.i = z;
        return this;
    }

    public final boolean isLethal() {
        return this.j;
    }

    public final DamageRecord setLethal(boolean z) {
        this.j = z;
        return this;
    }

    @Null
    public final Projectile getProjectile() {
        return this.h;
    }

    public final DamageRecord setProjectile(@Null Projectile projectile) {
        this.h = projectile;
        return this;
    }

    public final int getEfficiency() {
        return this.e;
    }

    public final DamageRecord setEfficiency(int i) {
        this.e = i;
        return this;
    }

    public final boolean isIgnoreTowerEfficiency() {
        return this.k;
    }

    public final DamageRecord setIgnoreTowerEfficiency(boolean z) {
        this.k = z;
        return this;
    }

    public final String toString() {
        return super.toString() + " (enemy: " + this.f1691a + ", damage: " + this.f1692b + ", factDamage: " + this.c + ", damageType: " + this.d + ", efficiency: " + this.e + ", tower: " + this.f + ", ability: " + this.g + ", projectile: " + this.h + ", cleanForDps: " + this.i + ", ignoreTowerEfficiency: " + this.k + ", unit: " + this.l + ", explosion: " + this.m + ")";
    }
}
