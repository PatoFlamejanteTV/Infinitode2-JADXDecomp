package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/Path.class */
public final class Path {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2608a = TLog.forClass(Path.class);
    public static final Path EMPTY = new Path(new HeavyPathNode[0]);
    public static final SideFunction[][] SIDE_FUNCTIONS = new SideFunction[20][11];
    public static final SideFunction[][] SIDE_SIMPLE_FUNCTIONS;

    /* renamed from: b, reason: collision with root package name */
    private final PathNode[] f2609b;
    private final byte[] c;
    private final int d;

    /* synthetic */ Path(PathNode[] pathNodeArr, byte[] bArr, byte b2) {
        this(pathNodeArr, bArr);
    }

    static {
        for (int i = 0; i < 11; i++) {
            float f = (i - 5) * 0.0625f;
            SIDE_FUNCTIONS[0][i] = new BezierSideFunction(-0.5f, -f, f, -f, f, 0.5f, f);
            SIDE_FUNCTIONS[1][i] = new LinearSideFunction(-0.5f, -f, 0.5f, -f);
            SIDE_FUNCTIONS[2][i] = new BezierSideFunction(-0.5f, -f, -f, -f, -f, -0.5f, -f);
            SIDE_FUNCTIONS[3][i] = new BezierSideFunction(-0.5f, -f, 0.0f, -f, 0.0f, 0.0f, 0.0f);
            SIDE_FUNCTIONS[4][i] = new BezierSideFunction(-f, 0.5f, -f, f, -0.5f, f, -f);
            SIDE_FUNCTIONS[5][i] = new BezierSideFunction(-f, 0.5f, -f, -f, 0.5f, -f, f);
            SIDE_FUNCTIONS[6][i] = new LinearSideFunction(-f, 0.5f, -f, -0.5f);
            SIDE_FUNCTIONS[7][i] = new BezierSideFunction(-f, 0.5f, -f, 0.0f, 0.0f, 0.0f, 0.0f);
            SIDE_FUNCTIONS[8][i] = new LinearSideFunction(0.5f, f, -0.5f, f);
            SIDE_FUNCTIONS[9][i] = new BezierSideFunction(0.5f, f, f, f, f, 0.5f, -f);
            SIDE_FUNCTIONS[10][i] = new BezierSideFunction(0.5f, f, -f, f, -f, -0.5f, f);
            SIDE_FUNCTIONS[11][i] = new BezierSideFunction(0.5f, f, 0.0f, f, 0.0f, 0.0f, 0.0f);
            SIDE_FUNCTIONS[12][i] = new BezierSideFunction(f, -0.5f, f, f, -0.5f, f, f);
            SIDE_FUNCTIONS[13][i] = new LinearSideFunction(f, -0.5f, f, 0.5f);
            SIDE_FUNCTIONS[14][i] = new BezierSideFunction(f, -0.5f, f, -f, 0.5f, -f, -f);
            SIDE_FUNCTIONS[15][i] = new BezierSideFunction(f, -0.5f, f, 0.0f, 0.0f, 0.0f, 0.0f);
            SIDE_FUNCTIONS[16][i] = new BezierSideFunction(0.0f, 0.0f, 0.0f, f, -0.5f, f, 0.0f);
            SIDE_FUNCTIONS[17][i] = new BezierSideFunction(0.0f, 0.0f, f, 0.0f, f, 0.5f, 0.0f);
            SIDE_FUNCTIONS[18][i] = new BezierSideFunction(0.0f, 0.0f, 0.0f, -f, 0.5f, -f, 0.0f);
            SIDE_FUNCTIONS[19][i] = new BezierSideFunction(0.0f, 0.0f, -f, 0.0f, -f, -0.5f, 0.0f);
        }
        SIDE_SIMPLE_FUNCTIONS = new SideFunction[20][11];
        for (int i2 = 0; i2 < 11; i2++) {
            float f2 = (i2 - 5) * 0.0625f;
            SIDE_SIMPLE_FUNCTIONS[0][i2] = new SharpCornerSideFunction(-0.5f, -f2, f2, -f2, f2, 0.5f);
            SIDE_SIMPLE_FUNCTIONS[1][i2] = new LinearSideFunction(-0.5f, -f2, 0.5f, -f2);
            SIDE_SIMPLE_FUNCTIONS[2][i2] = new SharpCornerSideFunction(-0.5f, -f2, -f2, -f2, -f2, -0.5f);
            SIDE_SIMPLE_FUNCTIONS[3][i2] = new LinearSideFunction(-0.5f, -f2, 0.0f, 0.0f);
            SIDE_SIMPLE_FUNCTIONS[4][i2] = new SharpCornerSideFunction(-f2, 0.5f, -f2, f2, -0.5f, f2);
            SIDE_SIMPLE_FUNCTIONS[5][i2] = new SharpCornerSideFunction(-f2, 0.5f, -f2, -f2, 0.5f, -f2);
            SIDE_SIMPLE_FUNCTIONS[6][i2] = new LinearSideFunction(-f2, 0.5f, -f2, -0.5f);
            SIDE_SIMPLE_FUNCTIONS[7][i2] = new LinearSideFunction(-f2, 0.5f, 0.0f, 0.0f);
            SIDE_SIMPLE_FUNCTIONS[8][i2] = new LinearSideFunction(0.5f, f2, -0.5f, f2);
            SIDE_SIMPLE_FUNCTIONS[9][i2] = new SharpCornerSideFunction(0.5f, f2, f2, f2, f2, 0.5f);
            SIDE_SIMPLE_FUNCTIONS[10][i2] = new SharpCornerSideFunction(0.5f, f2, -f2, f2, -f2, -0.5f);
            SIDE_SIMPLE_FUNCTIONS[11][i2] = new LinearSideFunction(0.5f, f2, 0.0f, 0.0f);
            SIDE_SIMPLE_FUNCTIONS[12][i2] = new SharpCornerSideFunction(f2, -0.5f, f2, f2, -0.5f, f2);
            SIDE_SIMPLE_FUNCTIONS[13][i2] = new LinearSideFunction(f2, -0.5f, f2, 0.5f);
            SIDE_SIMPLE_FUNCTIONS[14][i2] = new SharpCornerSideFunction(f2, -0.5f, f2, -f2, 0.5f, -f2);
            SIDE_SIMPLE_FUNCTIONS[15][i2] = new LinearSideFunction(f2, -0.5f, 0.0f, 0.0f);
            SIDE_SIMPLE_FUNCTIONS[16][i2] = new LinearSideFunction(0.0f, 0.0f, -0.5f, f2);
            SIDE_SIMPLE_FUNCTIONS[17][i2] = new LinearSideFunction(0.0f, 0.0f, f2, 0.5f);
            SIDE_SIMPLE_FUNCTIONS[18][i2] = new LinearSideFunction(0.0f, 0.0f, 0.5f, -f2);
            SIDE_SIMPLE_FUNCTIONS[19][i2] = new LinearSideFunction(0.0f, 0.0f, -f2, -0.5f);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/Path$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<Path> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Path path) {
            kryo.writeObject(output, path.f2609b);
            kryo.writeObject(output, path.c);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Path read2(Kryo kryo, Input input, Class<? extends Path> cls) {
            return new Path((PathNode[]) kryo.readObject(input, PathNode[].class), (byte[]) kryo.readObject(input, byte[].class), (byte) 0);
        }
    }

    public final int hashCode() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Path)) {
            return false;
        }
        Path path = (Path) obj;
        if (path.f2609b.length != this.f2609b.length) {
            return false;
        }
        for (int i = 0; i < this.c.length; i++) {
            if (path.c[i] != this.c[i]) {
                return false;
            }
        }
        for (int i2 = 0; i2 < this.f2609b.length; i2++) {
            if (!this.f2609b[i2].equals(path.f2609b[i2])) {
                return false;
            }
        }
        return true;
    }

    public final PathNode[] getNodes() {
        return this.f2609b;
    }

    private Path(PathNode[] pathNodeArr, byte[] bArr) {
        this.f2609b = pathNodeArr;
        this.c = bArr;
        this.d = b();
    }

    public Path(DefaultGraphPath<HeavyPathNode> defaultGraphPath) {
        this.f2609b = new PathNode[defaultGraphPath.nodes.size];
        this.c = new byte[this.f2609b.length];
        for (int i = 0; i < defaultGraphPath.nodes.size; i++) {
            this.f2609b[i] = defaultGraphPath.nodes.get(i).toLightNode();
        }
        a();
        this.d = b();
    }

    public Path(Array<HeavyPathNode> array) {
        this.f2609b = new PathNode[array.size];
        this.c = new byte[this.f2609b.length];
        for (int i = 0; i < array.size; i++) {
            this.f2609b[i] = array.get(i).toLightNode();
        }
        a();
        this.d = b();
    }

    public Path(HeavyPathNode[] heavyPathNodeArr) {
        this.f2609b = new PathNode[heavyPathNodeArr.length];
        this.c = new byte[this.f2609b.length];
        for (int i = 0; i < heavyPathNodeArr.length; i++) {
            this.f2609b[i] = heavyPathNodeArr[i].toLightNode();
        }
        a();
        this.d = b();
    }

    public Path(HeavyPathNode[] heavyPathNodeArr, byte[] bArr) {
        if (heavyPathNodeArr.length != bArr.length) {
            throw new IllegalArgumentException("Number of nodes != number of move sides, " + heavyPathNodeArr.length + " != " + bArr.length);
        }
        this.f2609b = new PathNode[heavyPathNodeArr.length];
        byte[] bArr2 = new byte[this.f2609b.length];
        for (int i = 0; i < heavyPathNodeArr.length; i++) {
            this.f2609b[i] = heavyPathNodeArr[i].toLightNode();
        }
        this.c = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, this.c, 0, bArr2.length);
        this.d = b();
    }

    public final int getCount() {
        return this.f2609b.length;
    }

    public final byte getMoveSideByIdx(int i) {
        return this.c[i];
    }

    public final byte[] getMoveSides() {
        return this.c;
    }

    public final byte getMoveSideByPassedTiles(float f) {
        return getMoveSideByIdx((int) (f + 0.5f));
    }

    public final Path copyWithStartingMoveSide(byte b2) {
        MoveSide.assertValid(b2);
        PathNode[] pathNodeArr = new PathNode[this.f2609b.length];
        byte[] bArr = new byte[this.c.length];
        System.arraycopy(this.f2609b, 0, pathNodeArr, 0, this.f2609b.length);
        System.arraycopy(this.c, 0, bArr, 0, this.c.length);
        bArr[0] = b2;
        return new Path(pathNodeArr, bArr);
    }

    public final Path copyWithCustomMoveSides(int[] iArr) {
        Preconditions.checkNotNull(iArr, "sides can not be null");
        PathNode[] pathNodeArr = new PathNode[this.f2609b.length];
        byte[] bArr = new byte[this.c.length];
        System.arraycopy(this.f2609b, 0, pathNodeArr, 0, this.f2609b.length);
        System.arraycopy(this.c, 0, bArr, 0, this.c.length);
        if (iArr.length % 2 != 0) {
            throw new IllegalArgumentException("Array must contain pairs [idx,side,idx,side...]");
        }
        for (int i = 0; i < iArr.length; i += 2) {
            int i2 = iArr[i];
            byte b2 = (byte) iArr[i + 1];
            MoveSide.assertValid(b2);
            if (i2 < 0 || i2 >= this.f2609b.length) {
                throw new IllegalArgumentException("Invalid node idx: " + i2 + ", valid range for this path is 0.." + (this.f2609b.length - 1));
            }
            bArr[i2] = b2;
        }
        return new Path(pathNodeArr, bArr);
    }

    public final int getLengthInTiles() {
        return this.f2609b.length - 1;
    }

    private byte a(int i) {
        PathNode pathNode = this.f2609b[i];
        PathNode pathNode2 = null;
        PathNode pathNode3 = null;
        if (i > 0) {
            pathNode2 = this.f2609b[i - 1];
        }
        if (i < this.f2609b.length - 1) {
            pathNode3 = this.f2609b[i + 1];
        }
        return MoveSide.calculateMoveSides(pathNode, pathNode2, pathNode3);
    }

    private void a() {
        for (int i = 0; i < this.f2609b.length; i++) {
            this.c[i] = a(i);
        }
    }

    private int b() {
        int i = 1;
        for (byte b2 : this.c) {
            i = (i * 31) + b2;
        }
        for (PathNode pathNode : this.f2609b) {
            i = (i * 31) + pathNode.hashCode();
        }
        return i;
    }

    public final Vector2 getPosition(float f, int i, Vector2 vector2) {
        int length = this.f2609b.length - 1;
        float f2 = 0.99999f;
        if (f < this.f2609b.length - 0.5d) {
            float f3 = f + 0.5f;
            f = f3;
            length = (int) f3;
            f2 = f - length;
        }
        try {
            PathNode pathNode = this.f2609b[length];
            SIDE_FUNCTIONS[this.c[length]][i].position(f2, vector2);
            int x = (pathNode.getX() << 7) + 64;
            int y = (pathNode.getY() << 7) + 64;
            vector2.x += x;
            vector2.y += y;
            if (((int) vector2.x) > (x + 64) - 1) {
                vector2.x = (x + 64) - 1;
            }
            if (((int) vector2.y) > (y + 64) - 1) {
                vector2.y = (y + 64) - 1;
            }
            return vector2;
        } catch (Exception e) {
            throw new IllegalStateException("can't get current node, currentNodeIdx = " + length + ", passedTiles = " + f + ", nodes.size = " + this.f2609b.length, e);
        }
    }

    public final boolean passesThroughTileType(Map map, TileType tileType) {
        int lengthInTiles = getLengthInTiles();
        for (int i = 0; i <= lengthInTiles; i++) {
            PathNode pathNode = this.f2609b[i];
            Tile tile = map.getTile(pathNode.getX(), pathNode.getY());
            if (tile != null && tile.type == tileType) {
                return true;
            }
        }
        return false;
    }

    public final float getPositionSimpleSegmentsForGraphics(int i, Array<PathSegmentForRendering> array) {
        float f;
        float f2;
        array.clear();
        float f3 = 0.0f;
        int lengthInTiles = getLengthInTiles();
        for (int i2 = 0; i2 <= lengthInTiles; i2++) {
            byte moveSideByIdx = getMoveSideByIdx(i2);
            PathNode pathNode = this.f2609b[i2];
            if (MoveSide.isStraightLine(moveSideByIdx)) {
                PathSegmentForRendering pathSegmentForRendering = new PathSegmentForRendering();
                Vector2 vector2 = new Vector2();
                SIDE_SIMPLE_FUNCTIONS[moveSideByIdx][i].position(0.0f, vector2);
                pathSegmentForRendering.x1 = vector2.x + (pathNode.getX() << 7) + 64.0f;
                pathSegmentForRendering.y1 = vector2.y + (pathNode.getY() << 7) + 64.0f;
                SIDE_SIMPLE_FUNCTIONS[moveSideByIdx][i].position(1.0f, vector2);
                pathSegmentForRendering.x2 = vector2.x + (pathNode.getX() << 7) + 64.0f;
                pathSegmentForRendering.y2 = vector2.y + (pathNode.getY() << 7) + 64.0f;
                pathSegmentForRendering.length = PMath.getDistanceBetweenPoints(pathSegmentForRendering.x1, pathSegmentForRendering.y1, pathSegmentForRendering.x2, pathSegmentForRendering.y2);
                pathSegmentForRendering.distanceFromStart = f3;
                pathSegmentForRendering.direction = PathSegmentForRendering.getDirection(pathSegmentForRendering.x1, pathSegmentForRendering.y1, pathSegmentForRendering.x2, pathSegmentForRendering.y2);
                array.add(pathSegmentForRendering);
                f = f3;
                f2 = pathSegmentForRendering.length;
            } else {
                PathSegmentForRendering pathSegmentForRendering2 = new PathSegmentForRendering();
                Vector2 vector22 = new Vector2();
                SIDE_SIMPLE_FUNCTIONS[moveSideByIdx][i].position(0.0f, vector22);
                pathSegmentForRendering2.x1 = vector22.x + (pathNode.getX() << 7) + 64.0f;
                pathSegmentForRendering2.y1 = vector22.y + (pathNode.getY() << 7) + 64.0f;
                SIDE_SIMPLE_FUNCTIONS[moveSideByIdx][i].position(0.5f, vector22);
                pathSegmentForRendering2.x2 = vector22.x + (pathNode.getX() << 7) + 64.0f;
                pathSegmentForRendering2.y2 = vector22.y + (pathNode.getY() << 7) + 64.0f;
                pathSegmentForRendering2.length = PMath.getDistanceBetweenPoints(pathSegmentForRendering2.x1, pathSegmentForRendering2.y1, pathSegmentForRendering2.x2, pathSegmentForRendering2.y2);
                pathSegmentForRendering2.distanceFromStart = f3;
                pathSegmentForRendering2.direction = PathSegmentForRendering.getDirection(pathSegmentForRendering2.x1, pathSegmentForRendering2.y1, pathSegmentForRendering2.x2, pathSegmentForRendering2.y2);
                array.add(pathSegmentForRendering2);
                float f4 = f3 + pathSegmentForRendering2.length;
                PathSegmentForRendering pathSegmentForRendering3 = new PathSegmentForRendering();
                pathSegmentForRendering3.x1 = pathSegmentForRendering2.x2;
                pathSegmentForRendering3.y1 = pathSegmentForRendering2.y2;
                SIDE_SIMPLE_FUNCTIONS[moveSideByIdx][i].position(1.0f, vector22);
                pathSegmentForRendering3.x2 = vector22.x + (pathNode.getX() << 7) + 64.0f;
                pathSegmentForRendering3.y2 = vector22.y + (pathNode.getY() << 7) + 64.0f;
                pathSegmentForRendering3.length = PMath.getDistanceBetweenPoints(pathSegmentForRendering3.x1, pathSegmentForRendering3.y1, pathSegmentForRendering3.x2, pathSegmentForRendering3.y2);
                pathSegmentForRendering3.distanceFromStart = f4;
                pathSegmentForRendering3.direction = PathSegmentForRendering.getDirection(pathSegmentForRendering3.x1, pathSegmentForRendering3.y1, pathSegmentForRendering3.x2, pathSegmentForRendering3.y2);
                array.add(pathSegmentForRendering3);
                f = f4;
                f2 = pathSegmentForRendering3.length;
            }
            f3 = f + f2;
        }
        return f3;
    }

    public final float getSpeedMultiplier(float f, int i) {
        int length = this.f2609b.length - 1;
        if (f < this.f2609b.length - 1.0f) {
            length = (int) (f + 0.5f);
        }
        if (length < 0) {
            throw new IllegalStateException("currentNodeIdx = " + length + ", passedTiles: " + f);
        }
        return getSpeedMultiplierByNodeIdx(length, i);
    }

    public final float getSpeedMultiplierByNodeIdx(int i, int i2) {
        return SIDE_FUNCTIONS[this.c[i]][i2].speedMultiplier();
    }

    public final float getRotation(float f, int i) {
        int length = this.f2609b.length - 1;
        float f2 = 0.99999f;
        if (f < this.f2609b.length - 1) {
            float f3 = f + 0.5f;
            length = (int) f3;
            f2 = f3 - length;
        }
        return SIDE_FUNCTIONS[this.c[length]][i].rotation(f2);
    }

    public final PathNode getByIdx(int i) {
        return this.f2609b[i];
    }

    public final PathNode getByPassedTiles(float f) {
        return this.f2609b[(int) (f + 0.5f)];
    }

    public final int getNodeIdxByPassedTiles(float f) {
        return (int) (f + 0.5d);
    }

    public final boolean isPassedTilesOnPath(float f) {
        return f < ((float) getCount()) - 1.0f;
    }

    public final String describe() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("nodes: ").append(this.f2609b.length).append(SequenceUtils.EOL);
        for (int i = 0; i < this.f2609b.length; i++) {
            if (i != 0) {
                stringBuilder.append(" > ");
            }
            stringBuilder.append((int) this.f2609b[i].getX()).append(":").append((int) this.f2609b[i].getY()).append(" (").append(MoveSide.getName(this.c[i])).append(")");
        }
        return stringBuilder.toString();
    }

    public final void debugDump() {
        if (getCount() == 0) {
            f2608a.i("path his empty", new Object[0]);
        }
        for (int i = 0; i < getCount(); i++) {
            f2608a.i(i + ": " + this.f2609b[i].toString(), new Object[0]);
        }
    }

    public final float getPassedTilesDelta(float f, float f2, int i, float f3) {
        if (f3 <= 0.0f) {
            if (f3 < 0.0f) {
                return f3 * f;
            }
            return 0.0f;
        }
        int nodeIdxByPassedTiles = getNodeIdxByPassedTiles(f2);
        double speedMultiplierByNodeIdx = getSpeedMultiplierByNodeIdx(nodeIdxByPassedTiles, i);
        double d = 1.0d - ((f2 + 0.5d) % 1.0d);
        float f4 = (float) (f3 * f * speedMultiplierByNodeIdx);
        if (f4 > d && nodeIdxByPassedTiles + 1 < getCount()) {
            double speedMultiplierByNodeIdx2 = getSpeedMultiplierByNodeIdx(nodeIdxByPassedTiles + 1, i);
            if (speedMultiplierByNodeIdx != speedMultiplierByNodeIdx2) {
                return (float) (d + (f3 * (f - (d / (f3 * speedMultiplierByNodeIdx))) * speedMultiplierByNodeIdx2));
            }
        }
        return f4;
    }
}
