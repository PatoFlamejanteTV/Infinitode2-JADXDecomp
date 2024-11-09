package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Wave.class */
public final class Wave implements KryoSerializable {
    public int waveNumber;
    public int difficulty;
    public int totalEnemiesCount;
    public boolean started;
    public boolean enemiesCanBeSplitBetweenSpawns = true;
    public boolean enemiesCanHaveRandomSideShifts = true;
    public DelayedRemovalArray<EnemyGroup> enemyGroups = new DelayedRemovalArray<>(true, 1, EnemyGroup.class);
    public String waveMessage = null;
    public float enemiesSumHealth = 0.0f;
    public float enemiesSumBounty = 0.0f;
    public float enemiesTookDamage = 0.0f;
    public WaveProcessor waveProcessor = null;
    public int killedEnemiesCount = 0;
    public int passedEnemiesCount = 0;
    public int killedEnemiesBountySum = 0;
    public boolean completed = false;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        output.writeVarInt(this.waveNumber, true);
        output.writeVarInt(this.difficulty, true);
        output.writeVarInt(this.totalEnemiesCount, true);
        output.writeBoolean(this.enemiesCanBeSplitBetweenSpawns);
        output.writeBoolean(this.enemiesCanHaveRandomSideShifts);
        kryo.writeObject(output, this.enemyGroups);
        kryo.writeObjectOrNull(output, this.waveMessage, String.class);
        output.writeFloat(this.enemiesSumHealth);
        output.writeFloat(this.enemiesSumBounty);
        output.writeFloat(this.enemiesTookDamage);
        kryo.writeClassAndObject(output, this.waveProcessor);
        output.writeBoolean(this.started);
        output.writeVarInt(this.killedEnemiesCount, true);
        output.writeVarInt(this.passedEnemiesCount, true);
        output.writeVarInt(this.killedEnemiesBountySum, true);
        output.writeBoolean(this.completed);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.waveNumber = input.readVarInt(true);
        this.difficulty = input.readVarInt(true);
        this.totalEnemiesCount = input.readVarInt(true);
        this.enemiesCanBeSplitBetweenSpawns = input.readBoolean();
        this.enemiesCanHaveRandomSideShifts = input.readBoolean();
        this.enemyGroups = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.waveMessage = (String) kryo.readObjectOrNull(input, String.class);
        this.enemiesSumHealth = input.readFloat();
        this.enemiesSumBounty = input.readFloat();
        this.enemiesTookDamage = input.readFloat();
        this.waveProcessor = (WaveProcessor) kryo.readClassAndObject(input);
        this.started = input.readBoolean();
        this.killedEnemiesCount = input.readVarInt(true);
        this.passedEnemiesCount = input.readVarInt(true);
        this.killedEnemiesBountySum = input.readVarInt(true);
        this.completed = input.readBoolean();
    }

    public static float calculateDefaultBossWaveCoinsSum(int i) {
        return ((float) ((12.0d + StrictMath.pow(i, 0.65d)) * (2.0f + (i * 0.05f)))) * 3.0f;
    }

    public static float calculateDefaultBossWaveScoreSum(int i) {
        return ((float) ((12.0d + StrictMath.pow(i, 0.65d)) * 10.0d)) * 3.0f;
    }

    public static float calculateDefaultBossWaveExpSum(int i) {
        return ((float) ((12.0d + StrictMath.pow(i, 0.65d)) * (1.0f + (i * 0.01f)))) * 3.0f;
    }

    private Wave() {
    }

    public Wave(int i, int i2, Array<EnemyGroup> array) {
        this.waveNumber = i;
        this.difficulty = i2;
        this.enemyGroups.addAll(array);
        Array.ArrayIterator<EnemyGroup> it = array.iterator();
        while (it.hasNext()) {
            EnemyGroup next = it.next();
            this.totalEnemiesCount += next.count;
            this.enemiesSumBounty += next.count * next.bounty;
            this.enemiesSumHealth += next.count * next.health;
        }
    }

    public final boolean isFullySpawned() {
        for (int i = 0; i < this.enemyGroups.size; i++) {
            if (this.enemyGroups.items[i].f1700a < this.enemyGroups.items[i].count) {
                return false;
            }
        }
        return true;
    }

    public final int getSpawnedEnemyCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.enemyGroups.size; i2++) {
            i += this.enemyGroups.items[i2].f1700a;
        }
        return i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wave {\n");
        sb.append("  waveNumber: ").append(this.waveNumber).append(SequenceUtils.EOL);
        sb.append("  averageDifficulty: ").append(this.difficulty).append(SequenceUtils.EOL);
        sb.append("  enemiesCount: ").append(this.totalEnemiesCount).append(SequenceUtils.EOL);
        sb.append("  enemyGroups: {\n");
        Array.ArrayIterator<EnemyGroup> it = this.enemyGroups.iterator();
        while (it.hasNext()) {
            sb.append("    ").append(it.next().toString()).append(SequenceUtils.EOL);
        }
        sb.append("  }\n");
        sb.append("}");
        return sb.toString();
    }
}
