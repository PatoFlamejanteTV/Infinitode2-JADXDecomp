package com.vladsch.flexmark.ext.gfm.issues.internal;

import com.vladsch.flexmark.ext.gfm.issues.GfmIssuesExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/internal/GfmIssuesOptions.class */
public class GfmIssuesOptions implements MutableDataSetter {
    public final String gitHubIssuesUrlRoot;
    public final String gitHubIssueUrlPrefix;
    public final String gitHubIssueUrlSuffix;
    public final String gitHubIssueTextPrefix;
    public final String gitHubIssueTextSuffix;

    public GfmIssuesOptions(DataHolder dataHolder) {
        this.gitHubIssuesUrlRoot = GfmIssuesExtension.GIT_HUB_ISSUES_URL_ROOT.get(dataHolder);
        this.gitHubIssueUrlPrefix = GfmIssuesExtension.GIT_HUB_ISSUE_URL_PREFIX.get(dataHolder);
        this.gitHubIssueUrlSuffix = GfmIssuesExtension.GIT_HUB_ISSUE_URL_SUFFIX.get(dataHolder);
        this.gitHubIssueTextPrefix = GfmIssuesExtension.GIT_HUB_ISSUE_HTML_PREFIX.get(dataHolder);
        this.gitHubIssueTextSuffix = GfmIssuesExtension.GIT_HUB_ISSUE_HTML_SUFFIX.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmIssuesExtension.GIT_HUB_ISSUES_URL_ROOT, (DataKey<String>) this.gitHubIssuesUrlRoot);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmIssuesExtension.GIT_HUB_ISSUE_URL_PREFIX, (DataKey<String>) this.gitHubIssueUrlPrefix);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmIssuesExtension.GIT_HUB_ISSUE_URL_SUFFIX, (DataKey<String>) this.gitHubIssueUrlSuffix);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmIssuesExtension.GIT_HUB_ISSUE_HTML_PREFIX, (DataKey<String>) this.gitHubIssueTextPrefix);
        mutableDataHolder.set((DataKey<DataKey<String>>) GfmIssuesExtension.GIT_HUB_ISSUE_HTML_SUFFIX, (DataKey<String>) this.gitHubIssueTextSuffix);
        return mutableDataHolder;
    }
}
