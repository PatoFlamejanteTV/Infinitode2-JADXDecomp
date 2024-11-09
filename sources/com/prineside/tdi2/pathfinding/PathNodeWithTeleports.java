package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathNodeWithTeleports.class */
public final class PathNodeWithTeleports implements PathNode {

    /* renamed from: a, reason: collision with root package name */
    private final short f2610a;

    /* renamed from: b, reason: collision with root package name */
    private final short f2611b;
    private final int[] c = new int[4];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathNodeWithTeleports$Serializer.class */
    public static final class Serializer extends com.esotericsoftware.kryo.Serializer<PathNodeWithTeleports> {
        @Override // com.esotericsoftware.kryo.Serializer
        public final void write(Kryo kryo, Output output, PathNodeWithTeleports pathNodeWithTeleports) {
            output.writeShort(pathNodeWithTeleports.f2610a);
            output.writeShort(pathNodeWithTeleports.f2611b);
            kryo.writeObject(output, pathNodeWithTeleports.c);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final PathNodeWithTeleports read2(Kryo kryo, Input input, Class<? extends PathNodeWithTeleports> cls) {
            return new PathNodeWithTeleports(input.readShort(), input.readShort(), (int[]) kryo.readObject(input, int[].class));
        }
    }

    public PathNodeWithTeleports(short s, short s2, int[] iArr) {
        this.f2610a = s;
        this.f2611b = s2;
        for (int i = 0; i < 4; i++) {
            this.c[i] = iArr[i];
        }
    }

    @Override // com.prineside.tdi2.pathfinding.PathNode
    public final short getX() {
        return this.f2610a;
    }

    @Override // com.prineside.tdi2.pathfinding.PathNode
    public final short getY() {
        return this.f2611b;
    }

    @Override // com.prineside.tdi2.pathfinding.PathNode
    @Null
    public final int[] getTeleports() {
        return this.c;
    }

    public final int hashCode() {
        return ((((((((((31 + this.f2610a) * 31) + this.f2611b) * 31) + this.c[0]) * 31) + this.c[1]) * 31) + this.c[2]) * 31) + this.c[3];
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PathNodeWithTeleports)) {
            return false;
        }
        PathNodeWithTeleports pathNodeWithTeleports = (PathNodeWithTeleports) obj;
        if (pathNodeWithTeleports.f2610a != this.f2610a || pathNodeWithTeleports.f2611b != this.f2611b) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (this.c[i] != pathNodeWithTeleports.c[i]) {
                return false;
            }
        }
        return true;
    }
}
