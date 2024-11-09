package com.prineside.tdi2;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/EnemyGroup.class */
public class EnemyGroup implements KryoSerializable {

    /* renamed from: b, reason: collision with root package name */
    private EnemyType f1699b;
    public float speed;
    public float health;
    public float delay;
    public float interval;
    public float bounty;
    public float killExp;
    public int killScore;
    public int count;

    /* renamed from: a, reason: collision with root package name */
    protected int f1700a;

    /* synthetic */ EnemyGroup(byte b2) {
        this();
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f1699b);
        output.writeFloat(this.speed);
        output.writeFloat(this.health);
        output.writeFloat(this.delay);
        output.writeFloat(this.interval);
        output.writeFloat(this.bounty);
        output.writeFloat(this.killExp);
        output.writeInt(this.killScore);
        output.writeVarInt(this.count, true);
        output.writeVarInt(this.f1700a, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f1699b = (EnemyType) kryo.readObject(input, EnemyType.class);
        this.speed = input.readFloat();
        this.health = input.readFloat();
        this.delay = input.readFloat();
        this.interval = input.readFloat();
        this.bounty = input.readFloat();
        this.killExp = input.readFloat();
        this.killScore = input.readInt();
        this.count = input.readVarInt(true);
        this.f1700a = input.readVarInt(true);
    }

    private EnemyGroup() {
        this.killExp = 1.0f;
        this.killScore = 1;
        this.f1700a = 0;
    }

    public EnemyGroup(EnemyType enemyType, float f, float f2, int i, float f3, float f4, float f5, float f6, int i2) {
        this.killExp = 1.0f;
        this.killScore = 1;
        this.f1700a = 0;
        setEnemyType(enemyType);
        this.speed = f;
        this.health = f2;
        this.count = i;
        this.delay = f3;
        this.interval = f4;
        this.bounty = f5;
        this.killExp = f6;
        this.killScore = i2;
    }

    public EnemyType getEnemyType() {
        return this.f1699b;
    }

    public void setEnemyType(EnemyType enemyType) {
        Preconditions.checkNotNull(enemyType, "type can not be null");
        this.f1699b = enemyType;
    }

    public int getSpawnedCount() {
        return this.f1700a;
    }

    public EnemyGroup cpy() {
        EnemyGroup enemyGroup = new EnemyGroup(this.f1699b, this.speed, this.health, this.count, this.delay, this.interval, this.bounty, this.killExp, this.killScore);
        enemyGroup.f1700a = this.f1700a;
        return enemyGroup;
    }

    public SpawnEnemyGroup createSpawnPortion(int i) {
        SpawnEnemyGroup spawnEnemyGroup = new SpawnEnemyGroup(this, this.f1699b, this.speed, this.health, i, this.delay, this.interval, this.bounty, this.killExp, this.killScore);
        spawnEnemyGroup.f1700a = 0;
        return spawnEnemyGroup;
    }

    public void toJson(Json json) {
        json.writeValue("enemyType", this.f1699b.name());
        if (this.delay > 0.0f) {
            json.writeValue("delay", Float.valueOf(this.delay));
        }
        json.writeValue("interval", Float.valueOf(this.interval));
        json.writeValue("count", Integer.valueOf(this.count));
        json.writeValue("health", Float.valueOf(this.health));
        json.writeValue("speed", Float.valueOf(this.speed));
        json.writeValue("bounty", Float.valueOf(this.bounty));
        json.writeValue("exp", Float.valueOf(this.killExp));
        json.writeValue("score", Integer.valueOf(this.killScore));
    }

    public static EnemyGroup fromJson(JsonValue jsonValue) {
        EnemyGroup enemyGroup = new EnemyGroup();
        enemyGroup.f1699b = EnemyType.valueOf(jsonValue.getString("enemyType"));
        enemyGroup.delay = jsonValue.getFloat("delay", 0.0f);
        enemyGroup.interval = jsonValue.getFloat("interval");
        enemyGroup.count = jsonValue.getInt("count");
        enemyGroup.health = jsonValue.getFloat("health");
        enemyGroup.speed = jsonValue.getFloat("speed");
        enemyGroup.bounty = jsonValue.getFloat("bounty");
        enemyGroup.killExp = jsonValue.getFloat("exp");
        enemyGroup.killScore = jsonValue.getInt("score");
        return enemyGroup;
    }

    public int getSpawnCountByTime(float f) {
        float f2 = f - this.delay;
        if (f2 < 0.0f) {
            return 0;
        }
        if (this.interval > 0.0f) {
            int floor = 1 + MathUtils.floor(f2 / this.interval);
            int i = floor;
            if (floor > this.count) {
                i = this.count;
            }
            return i;
        }
        return this.count;
    }

    public String toString() {
        return "EnemyGroup { type: " + this.f1699b.name() + ", speed: " + this.speed + ", health: " + this.health + ", delay: " + this.delay + ", interval: " + this.interval + ", bounty: " + this.bounty + ", killExp: " + this.killExp + ", killScore: " + this.killScore + ", count: " + this.count + ", spawnedCount: " + this.f1700a + " }";
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/EnemyGroup$SpawnEnemyGroup.class */
    public static class SpawnEnemyGroup extends EnemyGroup {
        public EnemyGroup waveGroup;

        @Override // com.prineside.tdi2.EnemyGroup, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            super.write(kryo, output);
            kryo.writeObjectOrNull(output, this.waveGroup, EnemyGroup.class);
        }

        @Override // com.prineside.tdi2.EnemyGroup, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            super.read(kryo, input);
            this.waveGroup = (EnemyGroup) kryo.readObjectOrNull(input, EnemyGroup.class);
        }

        public SpawnEnemyGroup() {
            super((byte) 0);
        }

        public SpawnEnemyGroup(EnemyGroup enemyGroup, EnemyType enemyType, float f, float f2, int i, float f3, float f4, float f5, float f6, int i2) {
            super(enemyType, f, f2, i, f3, f4, f5, f6, i2);
            this.waveGroup = enemyGroup;
        }

        public void addSpawnedCount(int i) {
            this.f1700a += i;
            this.waveGroup.f1700a += i;
        }
    }
}
