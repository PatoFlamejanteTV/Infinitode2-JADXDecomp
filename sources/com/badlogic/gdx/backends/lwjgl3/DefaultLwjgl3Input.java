package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.AbstractInput;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputEventQueue;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.badlogic.gdx.input.NativeInputConfiguration;
import com.badlogic.gdx.net.HttpStatus;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/DefaultLwjgl3Input.class */
public class DefaultLwjgl3Input extends AbstractInput implements Lwjgl3Input {
    final Lwjgl3Window window;
    private InputProcessor inputProcessor;
    int mouseX;
    int mouseY;
    int mousePressed;
    int deltaX;
    int deltaY;
    boolean justTouched;
    char lastCharacter;
    final InputEventQueue eventQueue = new InputEventQueue();
    final boolean[] justPressedButtons = new boolean[5];
    private GLFWKeyCallback keyCallback = new GLFWKeyCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.DefaultLwjgl3Input.1
        @Override // org.lwjgl.glfw.GLFWKeyCallbackI
        public void invoke(long j, int i, int i2, int i3, int i4) {
            DefaultLwjgl3Input.this.keyCallback(j, i, i2, i3, i4);
        }
    };
    GLFWCharCallback charCallback = new GLFWCharCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.DefaultLwjgl3Input.2
        @Override // org.lwjgl.glfw.GLFWCharCallbackI
        public void invoke(long j, int i) {
            if ((i & 65280) == 63232) {
                return;
            }
            DefaultLwjgl3Input.this.lastCharacter = (char) i;
            DefaultLwjgl3Input.this.window.getGraphics().requestRendering();
            DefaultLwjgl3Input.this.eventQueue.keyTyped((char) i, System.nanoTime());
        }
    };
    private GLFWScrollCallback scrollCallback = new GLFWScrollCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.DefaultLwjgl3Input.3
        @Override // org.lwjgl.glfw.GLFWScrollCallbackI
        public void invoke(long j, double d, double d2) {
            DefaultLwjgl3Input.this.window.getGraphics().requestRendering();
            DefaultLwjgl3Input.this.eventQueue.scrolled(-((float) d), -((float) d2), System.nanoTime());
        }
    };
    private GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.DefaultLwjgl3Input.4
        private int logicalMouseY;
        private int logicalMouseX;

        @Override // org.lwjgl.glfw.GLFWCursorPosCallbackI
        public void invoke(long j, double d, double d2) {
            DefaultLwjgl3Input.this.deltaX = ((int) d) - this.logicalMouseX;
            DefaultLwjgl3Input.this.deltaY = ((int) d2) - this.logicalMouseY;
            DefaultLwjgl3Input defaultLwjgl3Input = DefaultLwjgl3Input.this;
            int i = (int) d;
            this.logicalMouseX = i;
            defaultLwjgl3Input.mouseX = i;
            DefaultLwjgl3Input defaultLwjgl3Input2 = DefaultLwjgl3Input.this;
            int i2 = (int) d2;
            this.logicalMouseY = i2;
            defaultLwjgl3Input2.mouseY = i2;
            if (DefaultLwjgl3Input.this.window.getConfig().hdpiMode == HdpiMode.Pixels) {
                float backBufferWidth = DefaultLwjgl3Input.this.window.getGraphics().getBackBufferWidth() / DefaultLwjgl3Input.this.window.getGraphics().getLogicalWidth();
                float backBufferHeight = DefaultLwjgl3Input.this.window.getGraphics().getBackBufferHeight() / DefaultLwjgl3Input.this.window.getGraphics().getLogicalHeight();
                DefaultLwjgl3Input.this.deltaX = (int) (DefaultLwjgl3Input.this.deltaX * backBufferWidth);
                DefaultLwjgl3Input.this.deltaY = (int) (DefaultLwjgl3Input.this.deltaY * backBufferHeight);
                DefaultLwjgl3Input.this.mouseX = (int) (DefaultLwjgl3Input.this.mouseX * backBufferWidth);
                DefaultLwjgl3Input.this.mouseY = (int) (DefaultLwjgl3Input.this.mouseY * backBufferHeight);
            }
            DefaultLwjgl3Input.this.window.getGraphics().requestRendering();
            long nanoTime = System.nanoTime();
            if (DefaultLwjgl3Input.this.mousePressed > 0) {
                DefaultLwjgl3Input.this.eventQueue.touchDragged(DefaultLwjgl3Input.this.mouseX, DefaultLwjgl3Input.this.mouseY, 0, nanoTime);
            } else {
                DefaultLwjgl3Input.this.eventQueue.mouseMoved(DefaultLwjgl3Input.this.mouseX, DefaultLwjgl3Input.this.mouseY, nanoTime);
            }
        }
    };
    private GLFWMouseButtonCallback mouseButtonCallback = new GLFWMouseButtonCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.DefaultLwjgl3Input.5
        @Override // org.lwjgl.glfw.GLFWMouseButtonCallbackI
        public void invoke(long j, int i, int i2, int i3) {
            int gdxButton = toGdxButton(i);
            if (i == -1 || gdxButton != -1) {
                long nanoTime = System.nanoTime();
                if (i2 == 1) {
                    DefaultLwjgl3Input.this.mousePressed++;
                    DefaultLwjgl3Input.this.justTouched = true;
                    DefaultLwjgl3Input.this.justPressedButtons[gdxButton] = true;
                    DefaultLwjgl3Input.this.window.getGraphics().requestRendering();
                    DefaultLwjgl3Input.this.eventQueue.touchDown(DefaultLwjgl3Input.this.mouseX, DefaultLwjgl3Input.this.mouseY, 0, gdxButton, nanoTime);
                    return;
                }
                DefaultLwjgl3Input.this.mousePressed = Math.max(0, DefaultLwjgl3Input.this.mousePressed - 1);
                DefaultLwjgl3Input.this.window.getGraphics().requestRendering();
                DefaultLwjgl3Input.this.eventQueue.touchUp(DefaultLwjgl3Input.this.mouseX, DefaultLwjgl3Input.this.mouseY, 0, gdxButton, nanoTime);
            }
        }

        private int toGdxButton(int i) {
            if (i == 0) {
                return 0;
            }
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 3;
            }
            return i == 4 ? 4 : -1;
        }
    };

    public DefaultLwjgl3Input(Lwjgl3Window lwjgl3Window) {
        this.window = lwjgl3Window;
        windowHandleChanged(lwjgl3Window.getWindowHandle());
    }

    void keyCallback(long j, int i, int i2, int i3, int i4) {
        switch (i3) {
            case 0:
                int gdxKeyCode = getGdxKeyCode(i);
                this.pressedKeyCount--;
                this.pressedKeys[gdxKeyCode] = false;
                this.window.getGraphics().requestRendering();
                this.eventQueue.keyUp(gdxKeyCode, System.nanoTime());
                return;
            case 1:
                int gdxKeyCode2 = getGdxKeyCode(i);
                this.eventQueue.keyDown(gdxKeyCode2, System.nanoTime());
                this.pressedKeyCount++;
                this.keyJustPressed = true;
                this.pressedKeys[gdxKeyCode2] = true;
                this.justPressedKeys[gdxKeyCode2] = true;
                this.window.getGraphics().requestRendering();
                this.lastCharacter = (char) 0;
                char characterForKeyCode = characterForKeyCode(gdxKeyCode2);
                if (characterForKeyCode != 0) {
                    this.charCallback.invoke(j, characterForKeyCode);
                    return;
                }
                return;
            case 2:
                if (this.lastCharacter != 0) {
                    this.window.getGraphics().requestRendering();
                    this.eventQueue.keyTyped(this.lastCharacter, System.nanoTime());
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3Input
    public void resetPollingStates() {
        this.justTouched = false;
        this.keyJustPressed = false;
        for (int i = 0; i < this.justPressedKeys.length; i++) {
            this.justPressedKeys[i] = false;
        }
        for (int i2 = 0; i2 < this.justPressedButtons.length; i2++) {
            this.justPressedButtons[i2] = false;
        }
        this.eventQueue.drain(null);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3Input
    public void windowHandleChanged(long j) {
        resetPollingStates();
        GLFW.glfwSetKeyCallback(this.window.getWindowHandle(), this.keyCallback);
        GLFW.glfwSetCharCallback(this.window.getWindowHandle(), this.charCallback);
        GLFW.glfwSetScrollCallback(this.window.getWindowHandle(), this.scrollCallback);
        GLFW.glfwSetCursorPosCallback(this.window.getWindowHandle(), this.cursorPosCallback);
        GLFW.glfwSetMouseButtonCallback(this.window.getWindowHandle(), this.mouseButtonCallback);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3Input
    public void update() {
        this.eventQueue.drain(this.inputProcessor);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3Input
    public void prepareNext() {
        if (this.justTouched) {
            this.justTouched = false;
            for (int i = 0; i < this.justPressedButtons.length; i++) {
                this.justPressedButtons[i] = false;
            }
        }
        if (this.keyJustPressed) {
            this.keyJustPressed = false;
            for (int i2 = 0; i2 < this.justPressedKeys.length; i2++) {
                this.justPressedKeys[i2] = false;
            }
        }
        this.deltaX = 0;
        this.deltaY = 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getMaxPointers() {
        return 1;
    }

    @Override // com.badlogic.gdx.Input
    public int getX() {
        return this.mouseX;
    }

    @Override // com.badlogic.gdx.Input
    public int getX(int i) {
        if (i == 0) {
            return this.mouseX;
        }
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaX() {
        return this.deltaX;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaX(int i) {
        if (i == 0) {
            return this.deltaX;
        }
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getY() {
        return this.mouseY;
    }

    @Override // com.badlogic.gdx.Input
    public int getY(int i) {
        if (i == 0) {
            return this.mouseY;
        }
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaY() {
        return this.deltaY;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaY(int i) {
        if (i == 0) {
            return this.deltaY;
        }
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isTouched() {
        return GLFW.glfwGetMouseButton(this.window.getWindowHandle(), 0) == 1 || GLFW.glfwGetMouseButton(this.window.getWindowHandle(), 1) == 1 || GLFW.glfwGetMouseButton(this.window.getWindowHandle(), 2) == 1 || GLFW.glfwGetMouseButton(this.window.getWindowHandle(), 3) == 1 || GLFW.glfwGetMouseButton(this.window.getWindowHandle(), 4) == 1;
    }

    @Override // com.badlogic.gdx.Input
    public boolean justTouched() {
        return this.justTouched;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isTouched(int i) {
        if (i == 0) {
            return isTouched();
        }
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public float getPressure() {
        return getPressure(0);
    }

    @Override // com.badlogic.gdx.Input
    public float getPressure(int i) {
        return isTouched(i) ? 1.0f : 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isButtonPressed(int i) {
        return GLFW.glfwGetMouseButton(this.window.getWindowHandle(), i) == 1;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isButtonJustPressed(int i) {
        if (i < 0 || i >= this.justPressedButtons.length) {
            return false;
        }
        return this.justPressedButtons[i];
    }

    @Override // com.badlogic.gdx.Input
    public void getTextInput(Input.TextInputListener textInputListener, String str, String str2, String str3) {
        getTextInput(textInputListener, str, str2, str3, Input.OnscreenKeyboardType.Default);
    }

    @Override // com.badlogic.gdx.Input
    public void getTextInput(Input.TextInputListener textInputListener, String str, String str2, String str3, Input.OnscreenKeyboardType onscreenKeyboardType) {
        textInputListener.canceled();
    }

    @Override // com.badlogic.gdx.Input
    public long getCurrentEventTime() {
        return this.eventQueue.getCurrentEventTime();
    }

    @Override // com.badlogic.gdx.Input
    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    @Override // com.badlogic.gdx.Input
    public InputProcessor getInputProcessor() {
        return this.inputProcessor;
    }

    @Override // com.badlogic.gdx.Input
    public void setCursorCatched(boolean z) {
        GLFW.glfwSetInputMode(this.window.getWindowHandle(), GLFW.GLFW_CURSOR, z ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
    }

    @Override // com.badlogic.gdx.Input
    public boolean isCursorCatched() {
        return GLFW.glfwGetInputMode(this.window.getWindowHandle(), GLFW.GLFW_CURSOR) == 212995;
    }

    @Override // com.badlogic.gdx.Input
    public void setCursorPosition(int i, int i2) {
        if (this.window.getConfig().hdpiMode == HdpiMode.Pixels) {
            i = (int) (i * (this.window.getGraphics().getLogicalWidth() / this.window.getGraphics().getBackBufferWidth()));
            i2 = (int) (i2 * (this.window.getGraphics().getLogicalHeight() / this.window.getGraphics().getBackBufferHeight()));
        }
        GLFW.glfwSetCursorPos(this.window.getWindowHandle(), i, i2);
        this.cursorPosCallback.invoke(this.window.getWindowHandle(), i, i2);
    }

    protected char characterForKeyCode(int i) {
        switch (i) {
            case 61:
                return '\t';
            case 66:
            case 160:
                return '\n';
            case 67:
                return '\b';
            case 112:
                return (char) 127;
            default:
                return (char) 0;
        }
    }

    public int getGdxKeyCode(int i) {
        switch (i) {
            case 32:
                return 62;
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 40:
            case 41:
            case 42:
            case 43:
            case 58:
            case 60:
            case 62:
            case 63:
            case 64:
            case 94:
            case 95:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 163:
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
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
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
            case 243:
            case User32.VK_OEM_ENLW /* 244 */:
            case User32.VK_OEM_BACKTAB /* 245 */:
            case User32.VK_ATTN /* 246 */:
            case User32.VK_CRSEL /* 247 */:
            case User32.VK_EXSEL /* 248 */:
            case User32.VK_EREOF /* 249 */:
            case User32.VK_PLAY /* 250 */:
            case User32.VK_ZOOM /* 251 */:
            case User32.VK_NONAME /* 252 */:
            case User32.VK_PA1 /* 253 */:
            case 254:
            case 255:
            case User32.WM_IME_ENDCOMPOSITION /* 270 */:
            case 271:
            case 272:
            case 273:
            case User32.WM_SYSCOMMAND /* 274 */:
            case User32.WM_TIMER /* 275 */:
            case User32.WM_HSCROLL /* 276 */:
            case User32.WM_VSCROLL /* 277 */:
            case User32.WM_INITMENU /* 278 */:
            case User32.WM_INITMENUPOPUP /* 279 */:
            case 285:
            case 286:
            case User32.WM_MENUSELECT /* 287 */:
            case User32.WM_MENUCHAR /* 288 */:
            case User32.WM_ENTERIDLE /* 289 */:
            case CGL.kCGLCPMPSwapsInFlight /* 315 */:
            case 316:
            case 317:
            case 318:
            case 319:
            case 337:
            case 338:
            case 339:
            default:
                return 0;
            case 39:
                return 75;
            case 44:
                return 55;
            case 45:
                return 69;
            case 46:
                return 56;
            case 47:
                return 76;
            case 48:
                return 7;
            case 49:
                return 8;
            case 50:
                return 9;
            case 51:
                return 10;
            case 52:
                return 11;
            case 53:
                return 12;
            case 54:
                return 13;
            case 55:
                return 14;
            case 56:
                return 15;
            case 57:
                return 16;
            case 59:
                return 74;
            case 61:
                return 70;
            case 65:
                return 29;
            case 66:
                return 30;
            case 67:
                return 31;
            case 68:
                return 32;
            case 69:
                return 33;
            case 70:
                return 34;
            case 71:
                return 35;
            case 72:
                return 36;
            case 73:
                return 37;
            case 74:
                return 38;
            case 75:
                return 39;
            case 76:
                return 40;
            case 77:
                return 41;
            case 78:
                return 42;
            case 79:
                return 43;
            case 80:
                return 44;
            case 81:
                return 45;
            case 82:
                return 46;
            case 83:
                return 47;
            case 84:
                return 48;
            case 85:
                return 49;
            case 86:
                return 50;
            case 87:
                return 51;
            case 88:
                return 52;
            case 89:
                return 53;
            case 90:
                return 54;
            case 91:
                return 71;
            case 92:
                return 73;
            case 93:
                return 72;
            case 96:
                return 68;
            case 161:
            case 162:
                return 0;
            case 256:
                return 111;
            case 257:
                return 66;
            case 258:
                return 61;
            case 259:
                return 67;
            case 260:
                return 124;
            case 261:
                return 112;
            case 262:
                return 22;
            case 263:
                return 21;
            case GLFW.GLFW_KEY_DOWN /* 264 */:
                return 20;
            case 265:
                return 19;
            case GLFW.GLFW_KEY_PAGE_UP /* 266 */:
                return 92;
            case GLFW.GLFW_KEY_PAGE_DOWN /* 267 */:
                return 93;
            case GLFW.GLFW_KEY_HOME /* 268 */:
                return 3;
            case 269:
                return 123;
            case GLFW.GLFW_KEY_CAPS_LOCK /* 280 */:
                return 115;
            case 281:
                return 116;
            case 282:
                return 143;
            case GLFW.GLFW_KEY_PRINT_SCREEN /* 283 */:
                return 120;
            case GLFW.GLFW_KEY_PAUSE /* 284 */:
                return 121;
            case 290:
                return 131;
            case 291:
                return 132;
            case 292:
                return 133;
            case 293:
                return 134;
            case 294:
                return 135;
            case 295:
                return 136;
            case 296:
                return 137;
            case 297:
                return 138;
            case GLFW.GLFW_KEY_F9 /* 298 */:
                return 139;
            case GLFW.GLFW_KEY_F10 /* 299 */:
                return 140;
            case 300:
                return 141;
            case 301:
                return 142;
            case 302:
                return 183;
            case 303:
                return 184;
            case 304:
                return 185;
            case 305:
                return 186;
            case 306:
                return 187;
            case 307:
                return 188;
            case 308:
                return 189;
            case 309:
                return 190;
            case 310:
                return 191;
            case 311:
                return 192;
            case 312:
                return 193;
            case 313:
                return 194;
            case 314:
                return 0;
            case GLFW.GLFW_KEY_KP_0 /* 320 */:
                return 144;
            case GLFW.GLFW_KEY_KP_1 /* 321 */:
                return 145;
            case GLFW.GLFW_KEY_KP_2 /* 322 */:
                return 146;
            case GLFW.GLFW_KEY_KP_3 /* 323 */:
                return 147;
            case GLFW.GLFW_KEY_KP_4 /* 324 */:
                return 148;
            case GLFW.GLFW_KEY_KP_5 /* 325 */:
                return 149;
            case GLFW.GLFW_KEY_KP_6 /* 326 */:
                return 150;
            case GLFW.GLFW_KEY_KP_7 /* 327 */:
                return 151;
            case GLFW.GLFW_KEY_KP_8 /* 328 */:
                return 152;
            case GLFW.GLFW_KEY_KP_9 /* 329 */:
                return 153;
            case GLFW.GLFW_KEY_KP_DECIMAL /* 330 */:
                return 158;
            case GLFW.GLFW_KEY_KP_DIVIDE /* 331 */:
                return 154;
            case GLFW.GLFW_KEY_KP_MULTIPLY /* 332 */:
                return 155;
            case GLFW.GLFW_KEY_KP_SUBTRACT /* 333 */:
                return 156;
            case GLFW.GLFW_KEY_KP_ADD /* 334 */:
                return 157;
            case GLFW.GLFW_KEY_KP_ENTER /* 335 */:
                return 160;
            case GLFW.GLFW_KEY_KP_EQUAL /* 336 */:
                return 161;
            case GLFW.GLFW_KEY_LEFT_SHIFT /* 340 */:
                return 59;
            case GLFW.GLFW_KEY_LEFT_CONTROL /* 341 */:
                return 129;
            case GLFW.GLFW_KEY_LEFT_ALT /* 342 */:
                return 57;
            case GLFW.GLFW_KEY_LEFT_SUPER /* 343 */:
                return 63;
            case GLFW.GLFW_KEY_RIGHT_SHIFT /* 344 */:
                return 60;
            case GLFW.GLFW_KEY_RIGHT_CONTROL /* 345 */:
                return 130;
            case GLFW.GLFW_KEY_RIGHT_ALT /* 346 */:
                return 58;
            case GLFW.GLFW_KEY_RIGHT_SUPER /* 347 */:
                return 63;
            case 348:
                return 82;
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.keyCallback.free();
        this.charCallback.free();
        this.scrollCallback.free();
        this.cursorPosCallback.free();
        this.mouseButtonCallback.free();
    }

    @Override // com.badlogic.gdx.Input
    public float getAccelerometerX() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public float getAccelerometerY() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public float getAccelerometerZ() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isPeripheralAvailable(Input.Peripheral peripheral) {
        return peripheral == Input.Peripheral.HardwareKeyboard;
    }

    @Override // com.badlogic.gdx.Input
    public int getRotation() {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public Input.Orientation getNativeOrientation() {
        return Input.Orientation.Landscape;
    }

    @Override // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z, Input.OnscreenKeyboardType onscreenKeyboardType) {
    }

    @Override // com.badlogic.gdx.Input
    public void openTextInputField(NativeInputConfiguration nativeInputConfiguration) {
    }

    @Override // com.badlogic.gdx.Input
    public void closeTextInputField(boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void setKeyboardHeightObserver(Input.KeyboardHeightObserver keyboardHeightObserver) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(int i) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(int i, boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(int i, int i2, boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(Input.VibrationType vibrationType) {
    }

    @Override // com.badlogic.gdx.Input
    public float getAzimuth() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public float getPitch() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public float getRoll() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public void getRotationMatrix(float[] fArr) {
    }

    @Override // com.badlogic.gdx.Input
    public float getGyroscopeX() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public float getGyroscopeY() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public float getGyroscopeZ() {
        return 0.0f;
    }
}
