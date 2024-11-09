package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathNodeWithoutTeleports.class */
public final class PathNodeWithoutTeleports implements PathNode {

    /* renamed from: a, reason: collision with root package name */
    private final short f2612a;

    /* renamed from: b, reason: collision with root package name */
    private final short f2613b;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathNodeWithoutTeleports$Serializer.class */
    public static final class Serializer extends com.esotericsoftware.kryo.Serializer<PathNodeWithoutTeleports> {
        @Override // com.esotericsoftware.kryo.Serializer
        public final void write(Kryo kryo, Output output, PathNodeWithoutTeleports pathNodeWithoutTeleports) {
            output.writeShort(pathNodeWithoutTeleports.f2612a);
            output.writeShort(pathNodeWithoutTeleports.f2613b);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final PathNodeWithoutTeleports read2(Kryo kryo, Input input, Class<? extends PathNodeWithoutTeleports> cls) {
            return new PathNodeWithoutTeleports(input.readShort(), input.readShort());
        }
    }

    public PathNodeWithoutTeleports(short s, short s2) {
        this.f2612a = s;
        this.f2613b = s2;
    }

    @Override // com.prineside.tdi2.pathfinding.PathNode
    public final short getX() {
        return this.f2612a;
    }

    @Override // com.prineside.tdi2.pathfinding.PathNode
    public final short getY() {
        return this.f2613b;
    }

    @Override // com.prineside.tdi2.pathfinding.PathNode
    @Null
    public final int[] getTeleports() {
        return null;
    }

    public final int hashCode() {
        return (this.f2612a * 15331) + this.f2613b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PathNodeWithoutTeleports)) {
            return false;
        }
        PathNodeWithoutTeleports pathNodeWithoutTeleports = (PathNodeWithoutTeleports) obj;
        return pathNodeWithoutTeleports.f2612a == this.f2612a && pathNodeWithoutTeleports.f2613b == this.f2613b;
    }
}
