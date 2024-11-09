package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XEvent.class */
public class XEvent extends Struct<XEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int XANY;
    public static final int XKEY;
    public static final int XBUTTON;
    public static final int XMOTION;
    public static final int XCROSSING;
    public static final int XFOCUS;
    public static final int XEXPOSE;
    public static final int XGRAPHICSEXPOSE;
    public static final int XNOEXPOSE;
    public static final int XVISIBILITY;
    public static final int XCREATEWINDOW;
    public static final int XDESTROYWINDOW;
    public static final int XUNMAP;
    public static final int XMAP;
    public static final int XMAPREQUEST;
    public static final int XREPARENT;
    public static final int XCONFIGURE;
    public static final int XGRAVITY;
    public static final int XRESIZEREQUEST;
    public static final int XCONFIGUREREQUEST;
    public static final int XCIRCULATE;
    public static final int XCIRCULATEREQUEST;
    public static final int XPROPERTY;
    public static final int XSELECTIONCLEAR;
    public static final int XSELECTIONREQUEST;
    public static final int XSELECTION;
    public static final int XCOLORMAP;
    public static final int XCLIENT;
    public static final int XMAPPING;
    public static final int XERROR;
    public static final int XKEYMAP;
    public static final int XGENERIC;
    public static final int XCOOKIE;

    static {
        Struct.Layout __union = __union(__member(4), __member(XAnyEvent.SIZEOF, XAnyEvent.ALIGNOF), __member(XKeyEvent.SIZEOF, XKeyEvent.ALIGNOF), __member(XButtonEvent.SIZEOF, XButtonEvent.ALIGNOF), __member(XMotionEvent.SIZEOF, XMotionEvent.ALIGNOF), __member(XCrossingEvent.SIZEOF, XCrossingEvent.ALIGNOF), __member(XFocusChangeEvent.SIZEOF, XFocusChangeEvent.ALIGNOF), __member(XExposeEvent.SIZEOF, XExposeEvent.ALIGNOF), __member(XGraphicsExposeEvent.SIZEOF, XGraphicsExposeEvent.ALIGNOF), __member(XNoExposeEvent.SIZEOF, XNoExposeEvent.ALIGNOF), __member(XVisibilityEvent.SIZEOF, XVisibilityEvent.ALIGNOF), __member(XCreateWindowEvent.SIZEOF, XCreateWindowEvent.ALIGNOF), __member(XDestroyWindowEvent.SIZEOF, XDestroyWindowEvent.ALIGNOF), __member(XUnmapEvent.SIZEOF, XUnmapEvent.ALIGNOF), __member(XMapEvent.SIZEOF, XMapEvent.ALIGNOF), __member(XMapRequestEvent.SIZEOF, XMapRequestEvent.ALIGNOF), __member(XReparentEvent.SIZEOF, XReparentEvent.ALIGNOF), __member(XConfigureEvent.SIZEOF, XConfigureEvent.ALIGNOF), __member(XGravityEvent.SIZEOF, XGravityEvent.ALIGNOF), __member(XResizeRequestEvent.SIZEOF, XResizeRequestEvent.ALIGNOF), __member(XConfigureRequestEvent.SIZEOF, XConfigureRequestEvent.ALIGNOF), __member(XCirculateEvent.SIZEOF, XCirculateEvent.ALIGNOF), __member(XCirculateRequestEvent.SIZEOF, XCirculateRequestEvent.ALIGNOF), __member(XPropertyEvent.SIZEOF, XPropertyEvent.ALIGNOF), __member(XSelectionClearEvent.SIZEOF, XSelectionClearEvent.ALIGNOF), __member(XSelectionRequestEvent.SIZEOF, XSelectionRequestEvent.ALIGNOF), __member(XSelectionEvent.SIZEOF, XSelectionEvent.ALIGNOF), __member(XColormapEvent.SIZEOF, XColormapEvent.ALIGNOF), __member(XClientMessageEvent.SIZEOF, XClientMessageEvent.ALIGNOF), __member(XMappingEvent.SIZEOF, XMappingEvent.ALIGNOF), __member(XErrorEvent.SIZEOF, XErrorEvent.ALIGNOF), __member(XKeymapEvent.SIZEOF, XKeymapEvent.ALIGNOF), __member(XGenericEvent.SIZEOF, XGenericEvent.ALIGNOF), __member(XGenericEventCookie.SIZEOF, XGenericEventCookie.ALIGNOF), __padding(24, CLONG_SIZE, true));
        SIZEOF = __union.getSize();
        ALIGNOF = __union.getAlignment();
        TYPE = __union.offsetof(0);
        XANY = __union.offsetof(1);
        XKEY = __union.offsetof(2);
        XBUTTON = __union.offsetof(3);
        XMOTION = __union.offsetof(4);
        XCROSSING = __union.offsetof(5);
        XFOCUS = __union.offsetof(6);
        XEXPOSE = __union.offsetof(7);
        XGRAPHICSEXPOSE = __union.offsetof(8);
        XNOEXPOSE = __union.offsetof(9);
        XVISIBILITY = __union.offsetof(10);
        XCREATEWINDOW = __union.offsetof(11);
        XDESTROYWINDOW = __union.offsetof(12);
        XUNMAP = __union.offsetof(13);
        XMAP = __union.offsetof(14);
        XMAPREQUEST = __union.offsetof(15);
        XREPARENT = __union.offsetof(16);
        XCONFIGURE = __union.offsetof(17);
        XGRAVITY = __union.offsetof(18);
        XRESIZEREQUEST = __union.offsetof(19);
        XCONFIGUREREQUEST = __union.offsetof(20);
        XCIRCULATE = __union.offsetof(21);
        XCIRCULATEREQUEST = __union.offsetof(22);
        XPROPERTY = __union.offsetof(23);
        XSELECTIONCLEAR = __union.offsetof(24);
        XSELECTIONREQUEST = __union.offsetof(25);
        XSELECTION = __union.offsetof(26);
        XCOLORMAP = __union.offsetof(27);
        XCLIENT = __union.offsetof(28);
        XMAPPING = __union.offsetof(29);
        XERROR = __union.offsetof(30);
        XKEYMAP = __union.offsetof(31);
        XGENERIC = __union.offsetof(32);
        XCOOKIE = __union.offsetof(33);
    }

    protected XEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XEvent create(long j, ByteBuffer byteBuffer) {
        return new XEvent(j, byteBuffer);
    }

    public XEvent(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int type() {
        return ntype(address());
    }

    public XAnyEvent xany() {
        return nxany(address());
    }

    public XKeyEvent xkey() {
        return nxkey(address());
    }

    public XButtonEvent xbutton() {
        return nxbutton(address());
    }

    public XMotionEvent xmotion() {
        return nxmotion(address());
    }

    public XCrossingEvent xcrossing() {
        return nxcrossing(address());
    }

    public XFocusChangeEvent xfocus() {
        return nxfocus(address());
    }

    public XExposeEvent xexpose() {
        return nxexpose(address());
    }

    public XGraphicsExposeEvent xgraphicsexpose() {
        return nxgraphicsexpose(address());
    }

    public XNoExposeEvent xnoexpose() {
        return nxnoexpose(address());
    }

    public XVisibilityEvent xvisibility() {
        return nxvisibility(address());
    }

    public XCreateWindowEvent xcreatewindow() {
        return nxcreatewindow(address());
    }

    public XDestroyWindowEvent xdestroywindow() {
        return nxdestroywindow(address());
    }

    public XUnmapEvent xunmap() {
        return nxunmap(address());
    }

    public XMapEvent xmap() {
        return nxmap(address());
    }

    public XMapRequestEvent xmaprequest() {
        return nxmaprequest(address());
    }

    public XReparentEvent xreparent() {
        return nxreparent(address());
    }

    public XConfigureEvent xconfigure() {
        return nxconfigure(address());
    }

    public XGravityEvent xgravity() {
        return nxgravity(address());
    }

    public XResizeRequestEvent xresizerequest() {
        return nxresizerequest(address());
    }

    public XConfigureRequestEvent xconfigurerequest() {
        return nxconfigurerequest(address());
    }

    public XCirculateEvent xcirculate() {
        return nxcirculate(address());
    }

    public XCirculateRequestEvent xcirculaterequest() {
        return nxcirculaterequest(address());
    }

    public XPropertyEvent xproperty() {
        return nxproperty(address());
    }

    public XSelectionClearEvent xselectionclear() {
        return nxselectionclear(address());
    }

    public XSelectionRequestEvent xselectionrequest() {
        return nxselectionrequest(address());
    }

    public XSelectionEvent xselection() {
        return nxselection(address());
    }

    public XColormapEvent xcolormap() {
        return nxcolormap(address());
    }

    public XClientMessageEvent xclient() {
        return nxclient(address());
    }

    public XMappingEvent xmapping() {
        return nxmapping(address());
    }

    public XErrorEvent xerror() {
        return nxerror(address());
    }

    public XKeymapEvent xkeymap() {
        return nxkeymap(address());
    }

    public XGenericEvent xgeneric() {
        return nxgeneric(address());
    }

    public XGenericEventCookie xcookie() {
        return nxcookie(address());
    }

    public static XEvent malloc() {
        return new XEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XEvent calloc() {
        return new XEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XEvent create(long j) {
        return new XEvent(j, null);
    }

    public static XEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XEvent(j, null);
    }

    public static Buffer malloc(int i) {
        return new Buffer(MemoryUtil.nmemAllocChecked(__checkMalloc(i, SIZEOF)), i);
    }

    public static Buffer calloc(int i) {
        return new Buffer(MemoryUtil.nmemCallocChecked(i, SIZEOF), i);
    }

    public static Buffer create(int i) {
        ByteBuffer __create = __create(i, SIZEOF);
        return new Buffer(MemoryUtil.memAddress(__create), __create, -1, 0, i, i);
    }

    public static Buffer create(long j, int i) {
        return new Buffer(j, i);
    }

    public static Buffer createSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return new Buffer(j, i);
    }

    @Deprecated
    public static XEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XEvent callocStack(MemoryStack memoryStack) {
        return calloc(memoryStack);
    }

    @Deprecated
    public static Buffer mallocStack(int i) {
        return malloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer callocStack(int i) {
        return calloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer mallocStack(int i, MemoryStack memoryStack) {
        return malloc(i, memoryStack);
    }

    @Deprecated
    public static Buffer callocStack(int i, MemoryStack memoryStack) {
        return calloc(i, memoryStack);
    }

    public static XEvent malloc(MemoryStack memoryStack) {
        return new XEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XEvent calloc(MemoryStack memoryStack) {
        return new XEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ntype(long j) {
        return UNSAFE.getInt((Object) null, j + TYPE);
    }

    public static XAnyEvent nxany(long j) {
        return XAnyEvent.create(j + XANY);
    }

    public static XKeyEvent nxkey(long j) {
        return XKeyEvent.create(j + XKEY);
    }

    public static XButtonEvent nxbutton(long j) {
        return XButtonEvent.create(j + XBUTTON);
    }

    public static XMotionEvent nxmotion(long j) {
        return XMotionEvent.create(j + XMOTION);
    }

    public static XCrossingEvent nxcrossing(long j) {
        return XCrossingEvent.create(j + XCROSSING);
    }

    public static XFocusChangeEvent nxfocus(long j) {
        return XFocusChangeEvent.create(j + XFOCUS);
    }

    public static XExposeEvent nxexpose(long j) {
        return XExposeEvent.create(j + XEXPOSE);
    }

    public static XGraphicsExposeEvent nxgraphicsexpose(long j) {
        return XGraphicsExposeEvent.create(j + XGRAPHICSEXPOSE);
    }

    public static XNoExposeEvent nxnoexpose(long j) {
        return XNoExposeEvent.create(j + XNOEXPOSE);
    }

    public static XVisibilityEvent nxvisibility(long j) {
        return XVisibilityEvent.create(j + XVISIBILITY);
    }

    public static XCreateWindowEvent nxcreatewindow(long j) {
        return XCreateWindowEvent.create(j + XCREATEWINDOW);
    }

    public static XDestroyWindowEvent nxdestroywindow(long j) {
        return XDestroyWindowEvent.create(j + XDESTROYWINDOW);
    }

    public static XUnmapEvent nxunmap(long j) {
        return XUnmapEvent.create(j + XUNMAP);
    }

    public static XMapEvent nxmap(long j) {
        return XMapEvent.create(j + XMAP);
    }

    public static XMapRequestEvent nxmaprequest(long j) {
        return XMapRequestEvent.create(j + XMAPREQUEST);
    }

    public static XReparentEvent nxreparent(long j) {
        return XReparentEvent.create(j + XREPARENT);
    }

    public static XConfigureEvent nxconfigure(long j) {
        return XConfigureEvent.create(j + XCONFIGURE);
    }

    public static XGravityEvent nxgravity(long j) {
        return XGravityEvent.create(j + XGRAVITY);
    }

    public static XResizeRequestEvent nxresizerequest(long j) {
        return XResizeRequestEvent.create(j + XRESIZEREQUEST);
    }

    public static XConfigureRequestEvent nxconfigurerequest(long j) {
        return XConfigureRequestEvent.create(j + XCONFIGUREREQUEST);
    }

    public static XCirculateEvent nxcirculate(long j) {
        return XCirculateEvent.create(j + XCIRCULATE);
    }

    public static XCirculateRequestEvent nxcirculaterequest(long j) {
        return XCirculateRequestEvent.create(j + XCIRCULATEREQUEST);
    }

    public static XPropertyEvent nxproperty(long j) {
        return XPropertyEvent.create(j + XPROPERTY);
    }

    public static XSelectionClearEvent nxselectionclear(long j) {
        return XSelectionClearEvent.create(j + XSELECTIONCLEAR);
    }

    public static XSelectionRequestEvent nxselectionrequest(long j) {
        return XSelectionRequestEvent.create(j + XSELECTIONREQUEST);
    }

    public static XSelectionEvent nxselection(long j) {
        return XSelectionEvent.create(j + XSELECTION);
    }

    public static XColormapEvent nxcolormap(long j) {
        return XColormapEvent.create(j + XCOLORMAP);
    }

    public static XClientMessageEvent nxclient(long j) {
        return XClientMessageEvent.create(j + XCLIENT);
    }

    public static XMappingEvent nxmapping(long j) {
        return XMappingEvent.create(j + XMAPPING);
    }

    public static XErrorEvent nxerror(long j) {
        return XErrorEvent.create(j + XERROR);
    }

    public static XKeymapEvent nxkeymap(long j) {
        return XKeymapEvent.create(j + XKEYMAP);
    }

    public static XGenericEvent nxgeneric(long j) {
        return XGenericEvent.create(j + XGENERIC);
    }

    public static XGenericEventCookie nxcookie(long j) {
        return XGenericEventCookie.create(j + XCOOKIE);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XEvent, Buffer> implements NativeResource {
        private static final XEvent ELEMENT_FACTORY = XEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XEvent.SIZEOF);
        }

        public Buffer(long j, int i) {
            super(j, null, -1, 0, i, i);
        }

        Buffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
            super(j, byteBuffer, i, i2, i3, i4);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.lwjgl.system.CustomBuffer
        public Buffer self() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.lwjgl.system.StructBuffer
        public XEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XEvent.ntype(address());
        }

        public XAnyEvent xany() {
            return XEvent.nxany(address());
        }

        public XKeyEvent xkey() {
            return XEvent.nxkey(address());
        }

        public XButtonEvent xbutton() {
            return XEvent.nxbutton(address());
        }

        public XMotionEvent xmotion() {
            return XEvent.nxmotion(address());
        }

        public XCrossingEvent xcrossing() {
            return XEvent.nxcrossing(address());
        }

        public XFocusChangeEvent xfocus() {
            return XEvent.nxfocus(address());
        }

        public XExposeEvent xexpose() {
            return XEvent.nxexpose(address());
        }

        public XGraphicsExposeEvent xgraphicsexpose() {
            return XEvent.nxgraphicsexpose(address());
        }

        public XNoExposeEvent xnoexpose() {
            return XEvent.nxnoexpose(address());
        }

        public XVisibilityEvent xvisibility() {
            return XEvent.nxvisibility(address());
        }

        public XCreateWindowEvent xcreatewindow() {
            return XEvent.nxcreatewindow(address());
        }

        public XDestroyWindowEvent xdestroywindow() {
            return XEvent.nxdestroywindow(address());
        }

        public XUnmapEvent xunmap() {
            return XEvent.nxunmap(address());
        }

        public XMapEvent xmap() {
            return XEvent.nxmap(address());
        }

        public XMapRequestEvent xmaprequest() {
            return XEvent.nxmaprequest(address());
        }

        public XReparentEvent xreparent() {
            return XEvent.nxreparent(address());
        }

        public XConfigureEvent xconfigure() {
            return XEvent.nxconfigure(address());
        }

        public XGravityEvent xgravity() {
            return XEvent.nxgravity(address());
        }

        public XResizeRequestEvent xresizerequest() {
            return XEvent.nxresizerequest(address());
        }

        public XConfigureRequestEvent xconfigurerequest() {
            return XEvent.nxconfigurerequest(address());
        }

        public XCirculateEvent xcirculate() {
            return XEvent.nxcirculate(address());
        }

        public XCirculateRequestEvent xcirculaterequest() {
            return XEvent.nxcirculaterequest(address());
        }

        public XPropertyEvent xproperty() {
            return XEvent.nxproperty(address());
        }

        public XSelectionClearEvent xselectionclear() {
            return XEvent.nxselectionclear(address());
        }

        public XSelectionRequestEvent xselectionrequest() {
            return XEvent.nxselectionrequest(address());
        }

        public XSelectionEvent xselection() {
            return XEvent.nxselection(address());
        }

        public XColormapEvent xcolormap() {
            return XEvent.nxcolormap(address());
        }

        public XClientMessageEvent xclient() {
            return XEvent.nxclient(address());
        }

        public XMappingEvent xmapping() {
            return XEvent.nxmapping(address());
        }

        public XErrorEvent xerror() {
            return XEvent.nxerror(address());
        }

        public XKeymapEvent xkeymap() {
            return XEvent.nxkeymap(address());
        }

        public XGenericEvent xgeneric() {
            return XEvent.nxgeneric(address());
        }

        public XGenericEventCookie xcookie() {
            return XEvent.nxcookie(address());
        }
    }
}
