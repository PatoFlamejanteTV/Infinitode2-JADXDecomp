package net.bytebuddy.dynamic;

import net.bytebuddy.description.method.MethodDescription;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/VisibilityBridgeStrategy.class */
public interface VisibilityBridgeStrategy {
    boolean generateVisibilityBridge(MethodDescription methodDescription);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/VisibilityBridgeStrategy$Default.class */
    public enum Default implements VisibilityBridgeStrategy {
        ALWAYS { // from class: net.bytebuddy.dynamic.VisibilityBridgeStrategy.Default.1
            @Override // net.bytebuddy.dynamic.VisibilityBridgeStrategy
            public final boolean generateVisibilityBridge(MethodDescription methodDescription) {
                return true;
            }
        },
        ON_NON_GENERIC_METHOD { // from class: net.bytebuddy.dynamic.VisibilityBridgeStrategy.Default.2
            @Override // net.bytebuddy.dynamic.VisibilityBridgeStrategy
            public final boolean generateVisibilityBridge(MethodDescription methodDescription) {
                return !methodDescription.isGenerified();
            }
        },
        NEVER { // from class: net.bytebuddy.dynamic.VisibilityBridgeStrategy.Default.3
            @Override // net.bytebuddy.dynamic.VisibilityBridgeStrategy
            public final boolean generateVisibilityBridge(MethodDescription methodDescription) {
                return false;
            }
        };

        /* synthetic */ Default(byte b2) {
            this();
        }
    }
}
