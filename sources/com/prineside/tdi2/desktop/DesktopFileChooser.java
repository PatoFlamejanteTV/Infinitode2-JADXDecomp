package com.prineside.tdi2.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.FileChooser;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.nfd.NFDFilterItem;
import org.lwjgl.util.nfd.NativeFileDialog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/DesktopFileChooser.class */
public class DesktopFileChooser implements FileChooser {
    @Override // com.prineside.tdi2.utils.FileChooser
    public void choose(FileChooser.Configuration configuration, FileChooser.Callback callback) {
        int NFD_OpenDialog;
        Preconditions.checkNotNull(configuration, "configuration can not be null");
        Preconditions.checkNotNull(callback, "callback can not be null");
        PointerBuffer memAllocPointer = MemoryUtil.memAllocPointer(1);
        try {
            if (configuration.intent == FileChooser.FileChooseIntent.SAVE) {
                NFD_OpenDialog = NativeFileDialog.NFD_SaveDialog(memAllocPointer, (NFDFilterItem.Buffer) null, configuration.directory == null ? Gdx.files.external("/").file().getPath() : configuration.directory.file().getPath(), (CharSequence) null);
            } else {
                NFD_OpenDialog = NativeFileDialog.NFD_OpenDialog(memAllocPointer, (NFDFilterItem.Buffer) null, configuration.directory == null ? Gdx.files.external("/").file().getPath() : configuration.directory.file().getPath());
            }
            switch (NFD_OpenDialog) {
                case 0:
                    callback.onError(new Exception(NativeFileDialog.NFD_GetError()));
                    break;
                case 1:
                    callback.onFileChoose(new FileHandle(memAllocPointer.getStringUTF8(0)));
                    NativeFileDialog.nNFD_FreePath(memAllocPointer.get(0));
                    break;
                case 2:
                    callback.onCancel();
                    break;
            }
        } catch (Exception e) {
            callback.onError(e);
        } finally {
            MemoryUtil.memFree(memAllocPointer);
        }
    }

    @Override // com.prineside.tdi2.utils.FileChooser
    public boolean intentSupported(FileChooser.FileChooseIntent fileChooseIntent) {
        return true;
    }
}
