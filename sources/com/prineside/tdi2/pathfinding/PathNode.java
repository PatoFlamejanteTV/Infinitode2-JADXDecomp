package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true, arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathNode.class */
public interface PathNode {
    short getX();

    short getY();

    @Null
    int[] getTeleports();
}
