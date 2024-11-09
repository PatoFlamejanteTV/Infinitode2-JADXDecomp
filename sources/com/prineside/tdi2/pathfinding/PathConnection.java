package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathConnection.class */
public final class PathConnection extends DefaultConnection<HeavyPathNode> {
    public Array<HeavyPathNode> pathNodes;
    public boolean isTeleport;
    public float cost;
    public int fromIdx;
    public int toIdx;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathConnection$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<PathConnection> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, PathConnection pathConnection) {
            kryo.writeObject(output, pathConnection.pathNodes);
            output.writeBoolean(pathConnection.isTeleport);
            output.writeFloat(pathConnection.cost);
            output.writeInt(pathConnection.fromIdx);
            output.writeInt(pathConnection.toIdx);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public PathConnection read2(Kryo kryo, Input input, Class<? extends PathConnection> cls) {
            return new PathConnection((Array) kryo.readObject(input, Array.class), input.readInt(), input.readInt(), input.readBoolean(), input.readFloat());
        }
    }

    public PathConnection(Array<HeavyPathNode> array, int i, int i2, boolean z, float f) {
        super(null, null);
        this.pathNodes = array;
        this.fromIdx = i;
        this.toIdx = i2;
        this.isTeleport = z;
        this.cost = f;
    }

    @Override // com.badlogic.gdx.ai.pfa.DefaultConnection, com.badlogic.gdx.ai.pfa.Connection
    public final float getCost() {
        return this.cost;
    }

    @Override // com.badlogic.gdx.ai.pfa.DefaultConnection, com.badlogic.gdx.ai.pfa.Connection
    public final HeavyPathNode getFromNode() {
        return this.pathNodes.items[this.fromIdx];
    }

    @Override // com.badlogic.gdx.ai.pfa.DefaultConnection, com.badlogic.gdx.ai.pfa.Connection
    public final HeavyPathNode getToNode() {
        return this.pathNodes.items[this.toIdx];
    }
}
