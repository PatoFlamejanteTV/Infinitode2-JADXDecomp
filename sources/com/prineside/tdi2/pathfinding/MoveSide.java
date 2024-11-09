package com.prineside.tdi2.pathfinding;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.BitVector;
import com.prineside.tdi2.utils.IntPair;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/MoveSide.class */
public final class MoveSide {
    public static final byte LEFT_TOP = 0;
    public static final byte LEFT_RIGHT = 1;
    public static final byte LEFT_BOTTOM = 2;
    public static final byte LEFT_CENTER = 3;
    public static final byte TOP_LEFT = 4;
    public static final byte TOP_RIGHT = 5;
    public static final byte TOP_BOTTOM = 6;
    public static final byte TOP_CENTER = 7;
    public static final byte RIGHT_LEFT = 8;
    public static final byte RIGHT_TOP = 9;
    public static final byte RIGHT_BOTTOM = 10;
    public static final byte RIGHT_CENTER = 11;
    public static final byte BOTTOM_LEFT = 12;
    public static final byte BOTTOM_TOP = 13;
    public static final byte BOTTOM_RIGHT = 14;
    public static final byte BOTTOM_CENTER = 15;
    public static final byte CENTER_LEFT = 16;
    public static final byte CENTER_TOP = 17;
    public static final byte CENTER_RIGHT = 18;
    public static final byte CENTER_BOTTOM = 19;
    public static final int VALUES_COUNT = 20;
    public static final byte[] REVERSE = {4, 8, 12, 16, 0, 9, 13, 17, 1, 5, 14, 18, 2, 6, 10, 19, 3, 7, 11, 15};

    /* renamed from: a, reason: collision with root package name */
    private static final BitVector f2606a = new BitVector(400);

    /* renamed from: b, reason: collision with root package name */
    private static final IntPair[] f2607b = new IntPair[20];

    private MoveSide() {
    }

    static {
        byte b2 = 0;
        while (true) {
            byte b3 = b2;
            if (b3 < 20) {
                byte b4 = 0;
                while (true) {
                    byte b5 = b4;
                    if (b5 >= 20) {
                        break;
                    }
                    f2606a.unsafeSetValue((b3 * 20) + b5, (getName(b3).endsWith("TOP") && getName(b5).startsWith("BOTTOM")) || (getName(b3).endsWith("BOTTOM") && getName(b5).startsWith("TOP")) || ((getName(b3).endsWith("LEFT") && getName(b5).startsWith("RIGHT")) || (getName(b3).endsWith("RIGHT") && getName(b5).startsWith("LEFT"))));
                    b4 = (byte) (b5 + 1);
                }
                if (getName(b3).endsWith("TOP")) {
                    f2607b[b3] = new IntPair(0, 1);
                } else if (getName(b3).endsWith("RIGHT")) {
                    f2607b[b3] = new IntPair(1, 0);
                } else if (getName(b3).endsWith("LEFT")) {
                    f2607b[b3] = new IntPair(-1, 0);
                } else if (getName(b3).endsWith("BOTTOM")) {
                    f2607b[b3] = new IntPair(0, -1);
                }
                b2 = (byte) (b3 + 1);
            } else {
                return;
            }
        }
    }

    public static boolean areConnected(byte b2, byte b3) {
        return f2606a.unsafeGet((b2 * 20) + b3);
    }

    public static IntPair getNextNodeShift(byte b2) {
        return f2607b[b2];
    }

    public static boolean isStraightLine(byte b2) {
        return b2 == 1 || b2 == 8 || b2 == 6 || b2 == 13;
    }

    public static String getName(byte b2) {
        switch (b2) {
            case 0:
                return "LEFT_TOP";
            case 1:
                return "LEFT_RIGHT";
            case 2:
                return "LEFT_BOTTOM";
            case 3:
                return "LEFT_CENTER";
            case 4:
                return "TOP_LEFT";
            case 5:
                return "TOP_RIGHT";
            case 6:
                return "TOP_BOTTOM";
            case 7:
                return "TOP_CENTER";
            case 8:
                return "RIGHT_LEFT";
            case 9:
                return "RIGHT_TOP";
            case 10:
                return "RIGHT_BOTTOM";
            case 11:
                return "RIGHT_CENTER";
            case 12:
                return "BOTTOM_LEFT";
            case 13:
                return "BOTTOM_TOP";
            case 14:
                return "BOTTOM_RIGHT";
            case 15:
                return "BOTTOM_CENTER";
            case 16:
                return "CENTER_LEFT";
            case 17:
                return "CENTER_TOP";
            case 18:
                return "CENTER_RIGHT";
            case 19:
                return "CENTER_BOTTOM";
            default:
                return "UNKNOWN";
        }
    }

    public static byte valueOf(String str) {
        boolean z = -1;
        switch (str.hashCode()) {
            case -1943089714:
                if (str.equals("RIGHT_BOTTOM")) {
                    z = 10;
                    break;
                }
                break;
            case -1923874824:
                if (str.equals("RIGHT_CENTER")) {
                    z = 11;
                    break;
                }
                break;
            case -1792626435:
                if (str.equals("LEFT_TOP")) {
                    z = false;
                    break;
                }
                break;
            case -1196165855:
                if (str.equals("BOTTOM_TOP")) {
                    z = 13;
                    break;
                }
                break;
            case -1025888925:
                if (str.equals("LEFT_BOTTOM")) {
                    z = 2;
                    break;
                }
                break;
            case -1006674035:
                if (str.equals("LEFT_CENTER")) {
                    z = 3;
                    break;
                }
                break;
            case -873241494:
                if (str.equals("RIGHT_LEFT")) {
                    z = 8;
                    break;
                }
                break;
            case -769025646:
                if (str.equals("CENTER_RIGHT")) {
                    z = 18;
                    break;
                }
                break;
            case -475662734:
                if (str.equals("TOP_RIGHT")) {
                    z = 5;
                    break;
                }
                break;
            case -440631887:
                if (str.equals("CENTER_LEFT")) {
                    z = 16;
                    break;
                }
                break;
            case -434150460:
                if (str.equals("LEFT_RIGHT")) {
                    z = true;
                    break;
                }
                break;
            case -154073903:
                if (str.equals("TOP_LEFT")) {
                    z = 4;
                    break;
                }
                break;
            case -129238807:
                if (str.equals("BOTTOM_CENTER")) {
                    z = 15;
                    break;
                }
                break;
            case 1218764914:
                if (str.equals("RIGHT_TOP")) {
                    z = 9;
                    break;
                }
                break;
            case 1477882197:
                if (str.equals("CENTER_BOTTOM")) {
                    z = 19;
                    break;
                }
                break;
            case 1533816552:
                if (str.equals("BOTTOM_RIGHT")) {
                    z = 14;
                    break;
                }
                break;
            case 1573315995:
                if (str.equals("BOTTOM_LEFT")) {
                    z = 12;
                    break;
                }
                break;
            case 1648362059:
                if (str.equals("CENTER_TOP")) {
                    z = 17;
                    break;
                }
                break;
            case 1982197877:
                if (str.equals("TOP_BOTTOM")) {
                    z = 6;
                    break;
                }
                break;
            case 2001412767:
                if (str.equals("TOP_CENTER")) {
                    z = 7;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return (byte) 0;
            case true:
                return (byte) 1;
            case true:
                return (byte) 2;
            case true:
                return (byte) 3;
            case true:
                return (byte) 4;
            case true:
                return (byte) 5;
            case true:
                return (byte) 6;
            case true:
                return (byte) 7;
            case true:
                return (byte) 8;
            case true:
                return (byte) 9;
            case true:
                return (byte) 10;
            case true:
                return (byte) 11;
            case true:
                return (byte) 12;
            case true:
                return (byte) 13;
            case true:
                return (byte) 14;
            case true:
                return (byte) 15;
            case true:
                return (byte) 16;
            case true:
                return (byte) 17;
            case true:
                return (byte) 18;
            case true:
                return (byte) 19;
            default:
                return (byte) 0;
        }
    }

    public static byte calculateMoveSides(PathNode pathNode, PathNode pathNode2, PathNode pathNode3) {
        int[] teleports;
        int[] teleports2;
        int[] teleports3;
        int[] teleports4;
        byte b2 = -1;
        byte b3 = -1;
        if (pathNode2 != null) {
            if (pathNode2.getY() == pathNode.getY() && pathNode2.getX() + 1 == pathNode.getX()) {
                b2 = 0;
            } else if (pathNode2.getY() == pathNode.getY() && pathNode2.getX() - 1 == pathNode.getX()) {
                b2 = 1;
            } else if (pathNode2.getX() == pathNode.getX() && pathNode2.getY() + 1 == pathNode.getY()) {
                b2 = 3;
            } else if (pathNode2.getX() == pathNode.getX() && pathNode2.getY() - 1 == pathNode.getY()) {
                b2 = 2;
            }
        } else {
            b2 = 4;
        }
        if (pathNode3 != null) {
            if (pathNode3.getY() == pathNode.getY() && pathNode3.getX() + 1 == pathNode.getX()) {
                b3 = 0;
            } else if (pathNode3.getY() == pathNode.getY() && pathNode3.getX() - 1 == pathNode.getX()) {
                b3 = 1;
            } else if (pathNode3.getX() == pathNode.getX() && pathNode3.getY() + 1 == pathNode.getY()) {
                b3 = 3;
            } else if (pathNode3.getX() == pathNode.getX() && pathNode3.getY() - 1 == pathNode.getY()) {
                b3 = 2;
            }
        } else {
            b3 = 4;
        }
        if (b2 == -1 && (teleports3 = pathNode2.getTeleports()) != null) {
            byte b4 = 0;
            while (true) {
                byte b5 = b4;
                if (b5 >= 4) {
                    break;
                }
                int i = teleports3[b5];
                if (i != -1 && (teleports4 = pathNode.getTeleports()) != null) {
                    byte b6 = 0;
                    while (true) {
                        byte b7 = b6;
                        if (b7 >= 4) {
                            break;
                        }
                        if (i != teleports4[b7]) {
                            b6 = (byte) (b7 + 1);
                        } else {
                            b2 = b7;
                            break;
                        }
                    }
                }
                b4 = (byte) (b5 + 1);
            }
        }
        if (b3 == -1 && (teleports = pathNode3.getTeleports()) != null) {
            byte b8 = 0;
            while (true) {
                byte b9 = b8;
                if (b9 >= 4) {
                    break;
                }
                int i2 = teleports[b9];
                if (i2 != -1 && (teleports2 = pathNode.getTeleports()) != null) {
                    byte b10 = 0;
                    while (true) {
                        byte b11 = b10;
                        if (b11 >= 4) {
                            break;
                        }
                        if (i2 != teleports2[b11]) {
                            b10 = (byte) (b11 + 1);
                        } else {
                            b3 = b11;
                            break;
                        }
                    }
                }
                b8 = (byte) (b9 + 1);
            }
        }
        if (b2 == -1 || b3 == -1) {
            throw new IllegalStateException("Can't find move direction - " + (b2 == -1 ? "no from, " : "") + SequenceUtils.SPACE + (b3 == -1 ? "no to, " : "") + " (prev: " + pathNode2 + ") (curr: " + pathNode + ") (next: " + pathNode3 + ")");
        }
        if (b2 == b3) {
            b2 = 4;
        }
        return MoveDirection.getMoveSideByDirections(b2, b3);
    }

    public static void assertValid(byte b2) {
        Preconditions.checkArgument(b2 >= 0 && b2 < 20, "move side must be a value in range 0..%s, %s given", 20, (int) b2);
    }
}
