package com.badlogic.gdx;

import com.badlogic.gdx.input.NativeInputConfiguration;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.ObjectIntMap;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Input.class */
public interface Input {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$Buttons.class */
    public static class Buttons {
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int MIDDLE = 2;
        public static final int BACK = 3;
        public static final int FORWARD = 4;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$InputStringValidator.class */
    public interface InputStringValidator {
        boolean validate(String str);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$KeyboardHeightObserver.class */
    public interface KeyboardHeightObserver {
        void onKeyboardHeightChanged(int i);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$OnscreenKeyboardType.class */
    public enum OnscreenKeyboardType {
        Default,
        NumberPad,
        PhonePad,
        Email,
        Password,
        URI
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$Orientation.class */
    public enum Orientation {
        Landscape,
        Portrait
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$Peripheral.class */
    public enum Peripheral {
        HardwareKeyboard,
        OnscreenKeyboard,
        MultitouchScreen,
        Accelerometer,
        Compass,
        Vibrator,
        HapticFeedback,
        Gyroscope,
        RotationVector,
        Pressure
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$TextInputListener.class */
    public interface TextInputListener {
        void input(String str);

        void canceled();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$VibrationType.class */
    public enum VibrationType {
        LIGHT,
        MEDIUM,
        HEAVY
    }

    float getAccelerometerX();

    float getAccelerometerY();

    float getAccelerometerZ();

    float getGyroscopeX();

    float getGyroscopeY();

    float getGyroscopeZ();

    int getMaxPointers();

    int getX();

    int getX(int i);

    int getDeltaX();

    int getDeltaX(int i);

    int getY();

    int getY(int i);

    int getDeltaY();

    int getDeltaY(int i);

    boolean isTouched();

    boolean justTouched();

    boolean isTouched(int i);

    float getPressure();

    float getPressure(int i);

    boolean isButtonPressed(int i);

    boolean isButtonJustPressed(int i);

    boolean isKeyPressed(int i);

    boolean isKeyJustPressed(int i);

    void getTextInput(TextInputListener textInputListener, String str, String str2, String str3);

    void getTextInput(TextInputListener textInputListener, String str, String str2, String str3, OnscreenKeyboardType onscreenKeyboardType);

    void setOnscreenKeyboardVisible(boolean z);

    void setOnscreenKeyboardVisible(boolean z, OnscreenKeyboardType onscreenKeyboardType);

    void openTextInputField(NativeInputConfiguration nativeInputConfiguration);

    void closeTextInputField(boolean z);

    void setKeyboardHeightObserver(KeyboardHeightObserver keyboardHeightObserver);

    void vibrate(int i);

    void vibrate(int i, boolean z);

    void vibrate(int i, int i2, boolean z);

    void vibrate(VibrationType vibrationType);

    float getAzimuth();

    float getPitch();

    float getRoll();

    void getRotationMatrix(float[] fArr);

    long getCurrentEventTime();

    void setCatchKey(int i, boolean z);

    boolean isCatchKey(int i);

    void setInputProcessor(InputProcessor inputProcessor);

    InputProcessor getInputProcessor();

    boolean isPeripheralAvailable(Peripheral peripheral);

    int getRotation();

    Orientation getNativeOrientation();

    void setCursorCatched(boolean z);

    boolean isCursorCatched();

    void setCursorPosition(int i, int i2);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Input$Keys.class */
    public static class Keys {
        public static final int ANY_KEY = -1;
        public static final int NUM_0 = 7;
        public static final int NUM_1 = 8;
        public static final int NUM_2 = 9;
        public static final int NUM_3 = 10;
        public static final int NUM_4 = 11;
        public static final int NUM_5 = 12;
        public static final int NUM_6 = 13;
        public static final int NUM_7 = 14;
        public static final int NUM_8 = 15;
        public static final int NUM_9 = 16;
        public static final int A = 29;
        public static final int ALT_LEFT = 57;
        public static final int ALT_RIGHT = 58;
        public static final int APOSTROPHE = 75;
        public static final int AT = 77;
        public static final int B = 30;
        public static final int BACK = 4;
        public static final int BACKSLASH = 73;
        public static final int C = 31;
        public static final int CALL = 5;
        public static final int CAMERA = 27;
        public static final int CAPS_LOCK = 115;
        public static final int CLEAR = 28;
        public static final int COMMA = 55;
        public static final int D = 32;
        public static final int DEL = 67;
        public static final int BACKSPACE = 67;
        public static final int FORWARD_DEL = 112;
        public static final int DPAD_CENTER = 23;
        public static final int DPAD_DOWN = 20;
        public static final int DPAD_LEFT = 21;
        public static final int DPAD_RIGHT = 22;
        public static final int DPAD_UP = 19;
        public static final int CENTER = 23;
        public static final int DOWN = 20;
        public static final int LEFT = 21;
        public static final int RIGHT = 22;
        public static final int UP = 19;
        public static final int E = 33;
        public static final int ENDCALL = 6;
        public static final int ENTER = 66;
        public static final int ENVELOPE = 65;
        public static final int EQUALS = 70;
        public static final int EXPLORER = 64;
        public static final int F = 34;
        public static final int FOCUS = 80;
        public static final int G = 35;
        public static final int GRAVE = 68;
        public static final int H = 36;
        public static final int HEADSETHOOK = 79;
        public static final int HOME = 3;
        public static final int I = 37;
        public static final int J = 38;
        public static final int K = 39;
        public static final int L = 40;
        public static final int LEFT_BRACKET = 71;
        public static final int M = 41;
        public static final int MEDIA_FAST_FORWARD = 90;
        public static final int MEDIA_NEXT = 87;
        public static final int MEDIA_PLAY_PAUSE = 85;
        public static final int MEDIA_PREVIOUS = 88;
        public static final int MEDIA_REWIND = 89;
        public static final int MEDIA_STOP = 86;
        public static final int MENU = 82;
        public static final int MINUS = 69;
        public static final int MUTE = 91;
        public static final int N = 42;
        public static final int NOTIFICATION = 83;
        public static final int NUM = 78;
        public static final int O = 43;
        public static final int P = 44;
        public static final int PAUSE = 121;
        public static final int PERIOD = 56;
        public static final int PLUS = 81;
        public static final int POUND = 18;
        public static final int POWER = 26;
        public static final int PRINT_SCREEN = 120;
        public static final int Q = 45;
        public static final int R = 46;
        public static final int RIGHT_BRACKET = 72;
        public static final int S = 47;
        public static final int SCROLL_LOCK = 116;
        public static final int SEARCH = 84;
        public static final int SEMICOLON = 74;
        public static final int SHIFT_LEFT = 59;
        public static final int SHIFT_RIGHT = 60;
        public static final int SLASH = 76;
        public static final int SOFT_LEFT = 1;
        public static final int SOFT_RIGHT = 2;
        public static final int SPACE = 62;
        public static final int STAR = 17;
        public static final int SYM = 63;
        public static final int T = 48;
        public static final int TAB = 61;
        public static final int U = 49;
        public static final int UNKNOWN = 0;
        public static final int V = 50;
        public static final int VOLUME_DOWN = 25;
        public static final int VOLUME_UP = 24;
        public static final int W = 51;
        public static final int X = 52;
        public static final int Y = 53;
        public static final int Z = 54;
        public static final int META_ALT_LEFT_ON = 16;
        public static final int META_ALT_ON = 2;
        public static final int META_ALT_RIGHT_ON = 32;
        public static final int META_SHIFT_LEFT_ON = 64;
        public static final int META_SHIFT_ON = 1;
        public static final int META_SHIFT_RIGHT_ON = 128;
        public static final int META_SYM_ON = 4;
        public static final int CONTROL_LEFT = 129;
        public static final int CONTROL_RIGHT = 130;
        public static final int ESCAPE = 111;
        public static final int END = 123;
        public static final int INSERT = 124;
        public static final int PAGE_UP = 92;
        public static final int PAGE_DOWN = 93;
        public static final int PICTSYMBOLS = 94;
        public static final int SWITCH_CHARSET = 95;
        public static final int BUTTON_CIRCLE = 255;
        public static final int BUTTON_A = 96;
        public static final int BUTTON_B = 97;
        public static final int BUTTON_C = 98;
        public static final int BUTTON_X = 99;
        public static final int BUTTON_Y = 100;
        public static final int BUTTON_Z = 101;
        public static final int BUTTON_L1 = 102;
        public static final int BUTTON_R1 = 103;
        public static final int BUTTON_L2 = 104;
        public static final int BUTTON_R2 = 105;
        public static final int BUTTON_THUMBL = 106;
        public static final int BUTTON_THUMBR = 107;
        public static final int BUTTON_START = 108;
        public static final int BUTTON_SELECT = 109;
        public static final int BUTTON_MODE = 110;
        public static final int NUMPAD_0 = 144;
        public static final int NUMPAD_1 = 145;
        public static final int NUMPAD_2 = 146;
        public static final int NUMPAD_3 = 147;
        public static final int NUMPAD_4 = 148;
        public static final int NUMPAD_5 = 149;
        public static final int NUMPAD_6 = 150;
        public static final int NUMPAD_7 = 151;
        public static final int NUMPAD_8 = 152;
        public static final int NUMPAD_9 = 153;
        public static final int NUMPAD_DIVIDE = 154;
        public static final int NUMPAD_MULTIPLY = 155;
        public static final int NUMPAD_SUBTRACT = 156;
        public static final int NUMPAD_ADD = 157;
        public static final int NUMPAD_DOT = 158;
        public static final int NUMPAD_COMMA = 159;
        public static final int NUMPAD_ENTER = 160;
        public static final int NUMPAD_EQUALS = 161;
        public static final int NUMPAD_LEFT_PAREN = 162;
        public static final int NUMPAD_RIGHT_PAREN = 163;
        public static final int NUM_LOCK = 143;
        public static final int COLON = 243;
        public static final int F1 = 131;
        public static final int F2 = 132;
        public static final int F3 = 133;
        public static final int F4 = 134;
        public static final int F5 = 135;
        public static final int F6 = 136;
        public static final int F7 = 137;
        public static final int F8 = 138;
        public static final int F9 = 139;
        public static final int F10 = 140;
        public static final int F11 = 141;
        public static final int F12 = 142;
        public static final int F13 = 183;
        public static final int F14 = 184;
        public static final int F15 = 185;
        public static final int F16 = 186;
        public static final int F17 = 187;
        public static final int F18 = 188;
        public static final int F19 = 189;
        public static final int F20 = 190;
        public static final int F21 = 191;
        public static final int F22 = 192;
        public static final int F23 = 193;
        public static final int F24 = 194;
        public static final int MAX_KEYCODE = 255;
        private static ObjectIntMap<String> keyNames;

        public static String toString(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("keycode cannot be negative, keycode: " + i);
            }
            if (i > 255) {
                throw new IllegalArgumentException("keycode cannot be greater than 255, keycode: " + i);
            }
            switch (i) {
                case 0:
                    return "Unknown";
                case 1:
                    return "Soft Left";
                case 2:
                    return "Soft Right";
                case 3:
                    return "Home";
                case 4:
                    return "Back";
                case 5:
                    return "Call";
                case 6:
                    return "End Call";
                case 7:
                    return "0";
                case 8:
                    return "1";
                case 9:
                    return "2";
                case 10:
                    return "3";
                case 11:
                    return "4";
                case 12:
                    return "5";
                case 13:
                    return "6";
                case 14:
                    return "7";
                case 15:
                    return "8";
                case 16:
                    return "9";
                case 17:
                    return "*";
                case 18:
                    return "#";
                case 19:
                    return "Up";
                case 20:
                    return "Down";
                case 21:
                    return "Left";
                case 22:
                    return "Right";
                case 23:
                    return "Center";
                case 24:
                    return "Volume Up";
                case 25:
                    return "Volume Down";
                case 26:
                    return "Power";
                case 27:
                    return "Camera";
                case 28:
                    return "Clear";
                case 29:
                    return "A";
                case 30:
                    return "B";
                case 31:
                    return "C";
                case 32:
                    return "D";
                case 33:
                    return "E";
                case 34:
                    return "F";
                case 35:
                    return "G";
                case 36:
                    return "H";
                case 37:
                    return "I";
                case 38:
                    return "J";
                case 39:
                    return "K";
                case 40:
                    return "L";
                case 41:
                    return "M";
                case 42:
                    return "N";
                case 43:
                    return "O";
                case 44:
                    return "P";
                case 45:
                    return "Q";
                case 46:
                    return "R";
                case 47:
                    return "S";
                case 48:
                    return "T";
                case 49:
                    return "U";
                case 50:
                    return "V";
                case 51:
                    return "W";
                case 52:
                    return "X";
                case 53:
                    return "Y";
                case 54:
                    return "Z";
                case 55:
                    return ",";
                case 56:
                    return ".";
                case 57:
                    return "L-Alt";
                case 58:
                    return "R-Alt";
                case 59:
                    return "L-Shift";
                case 60:
                    return "R-Shift";
                case 61:
                    return "Tab";
                case 62:
                    return "Space";
                case 63:
                    return "SYM";
                case 64:
                    return "Explorer";
                case 65:
                    return "Envelope";
                case 66:
                    return "Enter";
                case 67:
                    return "Delete";
                case 68:
                    return "`";
                case 69:
                    return "-";
                case 70:
                    return "=";
                case 71:
                    return "[";
                case 72:
                    return "]";
                case 73:
                    return "\\";
                case 74:
                    return ";";
                case 75:
                    return "'";
                case 76:
                    return "/";
                case 77:
                    return "@";
                case 78:
                    return "Num";
                case 79:
                    return "Headset Hook";
                case 80:
                    return "Focus";
                case 81:
                    return "Plus";
                case 82:
                    return "Menu";
                case 83:
                    return "Notification";
                case 84:
                    return "Search";
                case 85:
                    return "Play/Pause";
                case 86:
                    return "Stop Media";
                case 87:
                    return "Next Media";
                case 88:
                    return "Prev Media";
                case 89:
                    return "Rewind";
                case 90:
                    return "Fast Forward";
                case 91:
                    return "Mute";
                case 92:
                    return "Page Up";
                case 93:
                    return "Page Down";
                case 94:
                    return "PICTSYMBOLS";
                case 95:
                    return "SWITCH_CHARSET";
                case 96:
                    return "A Button";
                case 97:
                    return "B Button";
                case 98:
                    return "C Button";
                case 99:
                    return "X Button";
                case 100:
                    return "Y Button";
                case 101:
                    return "Z Button";
                case 102:
                    return "L1 Button";
                case 103:
                    return "R1 Button";
                case 104:
                    return "L2 Button";
                case 105:
                    return "R2 Button";
                case 106:
                    return "Left Thumb";
                case 107:
                    return "Right Thumb";
                case 108:
                    return "Start";
                case 109:
                    return "Select";
                case 110:
                    return "Button Mode";
                case 111:
                    return "Escape";
                case 112:
                    return "Forward Delete";
                case 113:
                case 114:
                case 117:
                case 118:
                case 119:
                case 122:
                case 125:
                case 126:
                case 127:
                case 128:
                case 164:
                case 165:
                case 166:
                case 167:
                case 168:
                case 169:
                case 170:
                case 171:
                case 172:
                case 173:
                case 174:
                case 175:
                case 176:
                case 177:
                case 178:
                case 179:
                case 180:
                case 181:
                case 182:
                case 195:
                case 196:
                case 197:
                case 198:
                case 199:
                case 200:
                case 201:
                case HttpStatus.SC_ACCEPTED /* 202 */:
                case 203:
                case HttpStatus.SC_NO_CONTENT /* 204 */:
                case HttpStatus.SC_RESET_CONTENT /* 205 */:
                case HttpStatus.SC_PARTIAL_CONTENT /* 206 */:
                case HttpStatus.SC_MULTI_STATUS /* 207 */:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215:
                case 216:
                case 217:
                case 218:
                case User32.VK_OEM_4 /* 219 */:
                case User32.VK_OEM_5 /* 220 */:
                case 221:
                case 222:
                case User32.VK_OEM_8 /* 223 */:
                case CGL.kCGLCPDispatchTableSize /* 224 */:
                case User32.VK_OEM_AX /* 225 */:
                case 226:
                case User32.VK_ICO_HELP /* 227 */:
                case 228:
                case User32.VK_PROCESSKEY /* 229 */:
                case 230:
                case User32.VK_PACKET /* 231 */:
                case 232:
                case User32.VK_OEM_RESET /* 233 */:
                case User32.VK_OEM_JUMP /* 234 */:
                case 235:
                case 236:
                case User32.VK_OEM_PA3 /* 237 */:
                case User32.VK_OEM_WSCTRL /* 238 */:
                case User32.VK_OEM_CUSEL /* 239 */:
                case User32.VK_OEM_ATTN /* 240 */:
                case User32.VK_OEM_FINISH /* 241 */:
                case User32.VK_OEM_COPY /* 242 */:
                default:
                    return null;
                case 115:
                    return "Caps Lock";
                case 116:
                    return "Scroll Lock";
                case 120:
                    return "Print";
                case 121:
                    return "Pause";
                case 123:
                    return "End";
                case 124:
                    return "Insert";
                case 129:
                    return "L-Ctrl";
                case 130:
                    return "R-Ctrl";
                case 131:
                    return "F1";
                case 132:
                    return "F2";
                case 133:
                    return "F3";
                case 134:
                    return "F4";
                case 135:
                    return "F5";
                case 136:
                    return "F6";
                case 137:
                    return "F7";
                case 138:
                    return "F8";
                case 139:
                    return "F9";
                case 140:
                    return "F10";
                case 141:
                    return "F11";
                case 142:
                    return "F12";
                case 143:
                    return "Num Lock";
                case 144:
                    return "Numpad 0";
                case 145:
                    return "Numpad 1";
                case 146:
                    return "Numpad 2";
                case 147:
                    return "Numpad 3";
                case 148:
                    return "Numpad 4";
                case 149:
                    return "Numpad 5";
                case 150:
                    return "Numpad 6";
                case 151:
                    return "Numpad 7";
                case 152:
                    return "Numpad 8";
                case 153:
                    return "Numpad 9";
                case 154:
                    return "Num /";
                case 155:
                    return "Num *";
                case 156:
                    return "Num -";
                case 157:
                    return "Num +";
                case 158:
                    return "Num .";
                case 159:
                    return "Num ,";
                case 160:
                    return "Num Enter";
                case 161:
                    return "Num =";
                case 162:
                    return "Num (";
                case 163:
                    return "Num )";
                case 183:
                    return "F13";
                case 184:
                    return "F14";
                case 185:
                    return "F15";
                case 186:
                    return "F16";
                case 187:
                    return "F17";
                case 188:
                    return "F18";
                case 189:
                    return "F19";
                case 190:
                    return "F20";
                case 191:
                    return "F21";
                case 192:
                    return "F22";
                case 193:
                    return "F23";
                case 194:
                    return "F24";
                case 243:
                    return ":";
            }
        }

        public static int valueOf(String str) {
            if (keyNames == null) {
                initializeKeyNames();
            }
            return keyNames.get(str, -1);
        }

        private static void initializeKeyNames() {
            keyNames = new ObjectIntMap<>();
            for (int i = 0; i < 256; i++) {
                String keys = toString(i);
                if (keys != null) {
                    keyNames.put(keys, i);
                }
            }
        }
    }
}
