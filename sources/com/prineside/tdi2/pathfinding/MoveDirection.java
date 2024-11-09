package com.prineside.tdi2.pathfinding;

import com.google.common.base.Preconditions;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/MoveDirection.class */
public final class MoveDirection {
    public static final byte LEFT = 0;
    public static final byte RIGHT = 1;
    public static final byte TOP = 2;
    public static final byte BOTTOM = 3;
    public static final byte CENTER = 4;
    public static final int VALUES_COUNT = 5;
    public static final byte[] MOVE_SIDE_BY_DIRECTIONS = new byte[25];

    private MoveDirection() {
    }

    static {
        byte b2 = 0;
        while (true) {
            byte b3 = b2;
            if (b3 < 5) {
                byte b4 = 0;
                while (true) {
                    byte b5 = b4;
                    if (b5 < 5) {
                        if (b3 != b5) {
                            MOVE_SIDE_BY_DIRECTIONS[(b3 * 5) + b5] = MoveSide.valueOf(getName(b3) + JavaConstant.Dynamic.DEFAULT_NAME + getName(b5));
                        }
                        b4 = (byte) (b5 + 1);
                    }
                }
                b2 = (byte) (b3 + 1);
            } else {
                return;
            }
        }
    }

    public static String getName(byte b2) {
        switch (b2) {
            case 0:
                return "LEFT";
            case 1:
                return "RIGHT";
            case 2:
                return "TOP";
            case 3:
                return "BOTTOM";
            case 4:
                return "CENTER";
            default:
                return "UNKNOWN";
        }
    }

    public static byte valueOf(String str) {
        boolean z = -1;
        switch (str.hashCode()) {
            case 83253:
                if (str.equals("TOP")) {
                    z = 3;
                    break;
                }
                break;
            case 2332679:
                if (str.equals("LEFT")) {
                    z = true;
                    break;
                }
                break;
            case 77974012:
                if (str.equals("RIGHT")) {
                    z = 2;
                    break;
                }
                break;
            case 1965067819:
                if (str.equals("BOTTOM")) {
                    z = 4;
                    break;
                }
                break;
            case 1984282709:
                if (str.equals("CENTER")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return (byte) 4;
            case true:
                return (byte) 0;
            case true:
                return (byte) 1;
            case true:
                return (byte) 2;
            case true:
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    public static byte getMoveSideByDirections(byte b2, byte b3) {
        return MOVE_SIDE_BY_DIRECTIONS[(b2 * 5) + b3];
    }

    public static void assertValid(byte b2) {
        Preconditions.checkArgument(b2 >= 0 && b2 < 5, "direction must be a value in range 0..%s, %s given", 5, (int) b2);
    }
}
