package com.prineside.tdi2.ui.shared.luaWhitelistEditor.events;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/events/EntryStateChange.class */
public final class EntryStateChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final TreeEntry f3779a;

    public EntryStateChange(TreeEntry treeEntry) {
        Preconditions.checkNotNull(treeEntry);
        this.f3779a = treeEntry;
    }

    public final TreeEntry getTreeEntry() {
        return this.f3779a;
    }
}
