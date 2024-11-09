package com.vladsch.flexmark.ext.gfm.users.internal;

import com.vladsch.flexmark.ext.gfm.users.GfmUsersExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/internal/GfmUsersOptions.class */
public class GfmUsersOptions implements MutableDataSetter {
    public final String gitHubIssuesUrlRoot;
    public final String gitHubIssueUrlPrefix;
    public final String gitHubIssueUrlSuffix;
    public final String gitHubUserTextPrefix;
    public final String gitHubUserTextSuffix;

    public GfmUsersOptions(DataHolder dataHolder) {
        this.gitHubIssuesUrlRoot = GfmUsersExtension.GIT_HUB_USERS_URL_ROOT.get(dataHolder);
        this.gitHubIssueUrlPrefix = GfmUsersExtension.GIT_HUB_USER_URL_PREFIX.get(dataHolder);
        this.gitHubIssueUrlSuffix = GfmUsersExtension.GIT_HUB_USER_URL_SUFFIX.get(dataHolder);
        this.gitHubUserTextPrefix = GfmUsersExtension.GIT_HUB_USER_HTML_PREFIX.get(dataHolder);
        this.gitHubUserTextSuffix = GfmUsersExtension.GIT_HUB_USER_HTML_SUFFIX.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmUsersExtension.GIT_HUB_USERS_URL_ROOT, (DataKey<String>) this.gitHubIssuesUrlRoot);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmUsersExtension.GIT_HUB_USER_URL_PREFIX, (DataKey<String>) this.gitHubIssueUrlPrefix);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmUsersExtension.GIT_HUB_USER_URL_SUFFIX, (DataKey<String>) this.gitHubIssueUrlSuffix);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmUsersExtension.GIT_HUB_USER_HTML_PREFIX, (DataKey<String>) this.gitHubUserTextPrefix);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmUsersExtension.GIT_HUB_USER_HTML_SUFFIX, (DataKey<String>) this.gitHubUserTextSuffix);
        return mutableDataHolder;
    }
}
