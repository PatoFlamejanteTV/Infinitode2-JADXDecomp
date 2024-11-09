package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.html.UriContentResolver;
import com.vladsch.flexmark.html.UriContentResolverFactory;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.dependency.LastDependent;
import com.vladsch.flexmark.util.misc.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/FileUriContentResolver.class */
public class FileUriContentResolver implements UriContentResolver {
    public FileUriContentResolver(LinkResolverBasicContext linkResolverBasicContext) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v22, types: [com.vladsch.flexmark.html.renderer.ResolvedContent] */
    @Override // com.vladsch.flexmark.html.UriContentResolver
    public ResolvedContent resolveContent(Node node, LinkResolverBasicContext linkResolverBasicContext, ResolvedContent resolvedContent) {
        ?? exists;
        ResolvedLink resolvedLink = resolvedContent.getResolvedLink();
        if (resolvedLink.getStatus() == LinkStatus.VALID) {
            String url = resolvedLink.getUrl();
            if (url.startsWith("file:/")) {
                File file = new File(url.startsWith("file://") ? url.substring(7) : File.separatorChar == '\\' ? url.substring(6) : url.substring(5));
                if (file.isFile() && (exists = file.exists()) != 0) {
                    try {
                        exists = resolvedContent.withContent(FileUtil.getFileContentBytesWithExceptions(file)).withStatus(LinkStatus.VALID);
                        return exists;
                    } catch (IOException e) {
                        exists.printStackTrace();
                    }
                }
            }
        }
        return resolvedContent;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/FileUriContentResolver$Factory.class */
    public static class Factory implements UriContentResolverFactory {
        @Override // com.vladsch.flexmark.html.UriContentResolverFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return Collections.singleton(LastDependent.class);
        }

        @Override // com.vladsch.flexmark.html.UriContentResolverFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.html.UriContentResolverFactory, com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.UriContentResolverFactory, java.util.function.Function
        public UriContentResolver apply(LinkResolverBasicContext linkResolverBasicContext) {
            return new FileUriContentResolver(linkResolverBasicContext);
        }
    }
}
