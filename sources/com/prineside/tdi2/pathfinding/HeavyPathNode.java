package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 2)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/HeavyPathNode.class */
public final class HeavyPathNode implements KryoSerializable {
    public int index;
    public short x;
    public short y;
    public float cost;
    public Array<Connection<HeavyPathNode>> connections = new Array<>(PathConnection.class);
    public int[] teleportIndices = {-1, -1, -1, -1};

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.connections);
        output.writeVarInt(this.index, true);
        output.writeShort(this.x);
        output.writeShort(this.y);
        output.writeFloat(this.cost);
        kryo.writeObject(output, this.teleportIndices);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.connections = (Array) kryo.readObject(input, Array.class);
        this.index = input.readVarInt(true);
        this.x = input.readShort();
        this.y = input.readShort();
        this.cost = input.readFloat();
        this.teleportIndices = (int[]) kryo.readObject(input, int[].class);
    }

    public final int hashCode() {
        return (this.y * 14923) + this.x;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeavyPathNode)) {
            return false;
        }
        HeavyPathNode heavyPathNode = (HeavyPathNode) obj;
        if (heavyPathNode.index != this.index || heavyPathNode.x != this.x || heavyPathNode.y != this.y || heavyPathNode.cost != this.cost) {
            return false;
        }
        for (int i = 0; i < this.teleportIndices.length; i++) {
            if (heavyPathNode.teleportIndices[i] != this.teleportIndices[i]) {
                return false;
            }
        }
        return true;
    }

    public final void setup(int i, short s, short s2, float f) {
        this.index = i;
        this.x = s;
        this.y = s2;
        this.cost = f;
    }

    public final String toString() {
        return ((int) this.x) + ":" + ((int) this.y) + " (tp: " + this.teleportIndices[0] + ", " + this.teleportIndices[1] + ", " + this.teleportIndices[2] + ", " + this.teleportIndices[3] + ")";
    }

    public final PathNode toLightNode() {
        boolean z = false;
        int[] iArr = this.teleportIndices;
        int length = iArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (iArr[i] == -1) {
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            return new PathNodeWithTeleports(this.x, this.y, this.teleportIndices);
        }
        return new PathNodeWithoutTeleports(this.x, this.y);
    }
}
