package com.badlogic.gdx.backends.headless.mock.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.NativeInputConfiguration;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/headless/mock/input/MockInput.class */
public class MockInput implements Input {
    private InputProcessor mockInputProcessor;

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

    @Override // com.badlogic.gdx.Input
    public int getMaxPointers() {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getX() {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getX(int i) {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaX() {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaX(int i) {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getY() {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getY(int i) {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaY() {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaY(int i) {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isTouched() {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public boolean justTouched() {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isTouched(int i) {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public float getPressure() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public float getPressure(int i) {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isButtonPressed(int i) {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isButtonJustPressed(int i) {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isKeyPressed(int i) {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isKeyJustPressed(int i) {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public void getTextInput(Input.TextInputListener textInputListener, String str, String str2, String str3) {
    }

    @Override // com.badlogic.gdx.Input
    public void getTextInput(Input.TextInputListener textInputListener, String str, String str2, String str3, Input.OnscreenKeyboardType onscreenKeyboardType) {
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
    public long getCurrentEventTime() {
        return 0L;
    }

    @Override // com.badlogic.gdx.Input
    public void setCatchKey(int i, boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public boolean isCatchKey(int i) {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public void setInputProcessor(InputProcessor inputProcessor) {
    }

    @Override // com.badlogic.gdx.Input
    public InputProcessor getInputProcessor() {
        if (this.mockInputProcessor == null) {
            this.mockInputProcessor = new InputAdapter();
        }
        return this.mockInputProcessor;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isPeripheralAvailable(Input.Peripheral peripheral) {
        return false;
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
    public void setCursorCatched(boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public boolean isCursorCatched() {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public void setCursorPosition(int i, int i2) {
    }
}
