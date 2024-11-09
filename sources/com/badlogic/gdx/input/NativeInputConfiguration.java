package com.badlogic.gdx.input;

import com.badlogic.gdx.Input;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/input/NativeInputConfiguration.class */
public class NativeInputConfiguration {
    private TextInputWrapper textInputWrapper;
    private Integer maxLength;
    private Input.InputStringValidator validator;
    private Input.OnscreenKeyboardType type = Input.OnscreenKeyboardType.Default;
    private boolean preventCorrection = false;
    private boolean isMultiLine = false;
    private String placeholder = "";
    private boolean showPasswordButton = false;
    private String[] autoComplete = null;

    public Input.OnscreenKeyboardType getType() {
        return this.type;
    }

    public NativeInputConfiguration setType(Input.OnscreenKeyboardType onscreenKeyboardType) {
        this.type = onscreenKeyboardType;
        return this;
    }

    public boolean isPreventCorrection() {
        return this.preventCorrection;
    }

    public NativeInputConfiguration setPreventCorrection(boolean z) {
        this.preventCorrection = z;
        return this;
    }

    public TextInputWrapper getTextInputWrapper() {
        return this.textInputWrapper;
    }

    public NativeInputConfiguration setTextInputWrapper(TextInputWrapper textInputWrapper) {
        this.textInputWrapper = textInputWrapper;
        return this;
    }

    public boolean isMultiLine() {
        return this.isMultiLine;
    }

    public NativeInputConfiguration setMultiLine(boolean z) {
        this.isMultiLine = z;
        return this;
    }

    public Integer getMaxLength() {
        return this.maxLength;
    }

    public NativeInputConfiguration setMaxLength(Integer num) {
        this.maxLength = num;
        return this;
    }

    public Input.InputStringValidator getValidator() {
        return this.validator;
    }

    public NativeInputConfiguration setValidator(Input.InputStringValidator inputStringValidator) {
        this.validator = inputStringValidator;
        return this;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public NativeInputConfiguration setPlaceholder(String str) {
        this.placeholder = str;
        return this;
    }

    public boolean isShowPasswordButton() {
        return this.showPasswordButton;
    }

    public NativeInputConfiguration setShowPasswordButton(boolean z) {
        this.showPasswordButton = z;
        return this;
    }

    public String[] getAutoComplete() {
        return this.autoComplete;
    }

    public NativeInputConfiguration setAutoComplete(String[] strArr) {
        this.autoComplete = strArr;
        return this;
    }

    public void validate() {
        String str = null;
        if (this.type == null) {
            str = "OnscreenKeyboardType needs to be non null";
        }
        if (this.textInputWrapper == null) {
            str = "TextInputWrapper needs to be non null";
        }
        if (this.showPasswordButton && this.type != Input.OnscreenKeyboardType.Password) {
            str = "ShowPasswordButton only works with OnscreenKeyboardType.Password";
        }
        if (this.placeholder == null) {
            str = "Placeholder needs to be non null";
        }
        if (this.autoComplete != null && this.type != Input.OnscreenKeyboardType.Default) {
            str = "AutoComplete should only be used with OnscreenKeyboardType.Default";
        }
        if (this.autoComplete != null && this.isMultiLine) {
            str = "AutoComplete shouldn't be used with multiline";
        }
        if (str != null) {
            throw new IllegalArgumentException("NativeInputConfiguration validation failed: " + str);
        }
    }
}
