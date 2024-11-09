package org.lwjgl.opengl;

import com.badlogic.gdx.net.HttpStatus;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.abilities.BlizzardAbility;
import com.prineside.tdi2.configs.HeadlessConfig;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.ui.shared.MusicListOverlay;
import java.util.Set;
import java.util.function.IntFunction;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.openal.EXTDisconnect;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.ThreadLocalUtil;
import org.lwjgl.system.linux.FCNTL;
import org.lwjgl.system.macosx.CoreGraphics;
import org.lwjgl.system.macosx.ObjCRuntime;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLCapabilities.class */
public final class GLCapabilities {
    static final int ADDRESS_BUFFER_SIZE = 2228;
    public final long glEnable;
    public final long glDisable;
    public final long glAccum;
    public final long glAlphaFunc;
    public final long glAreTexturesResident;
    public final long glArrayElement;
    public final long glBegin;
    public final long glBindTexture;
    public final long glBitmap;
    public final long glBlendFunc;
    public final long glCallList;
    public final long glCallLists;
    public final long glClear;
    public final long glClearAccum;
    public final long glClearColor;
    public final long glClearDepth;
    public final long glClearIndex;
    public final long glClearStencil;
    public final long glClipPlane;
    public final long glColor3b;
    public final long glColor3s;
    public final long glColor3i;
    public final long glColor3f;
    public final long glColor3d;
    public final long glColor3ub;
    public final long glColor3us;
    public final long glColor3ui;
    public final long glColor3bv;
    public final long glColor3sv;
    public final long glColor3iv;
    public final long glColor3fv;
    public final long glColor3dv;
    public final long glColor3ubv;
    public final long glColor3usv;
    public final long glColor3uiv;
    public final long glColor4b;
    public final long glColor4s;
    public final long glColor4i;
    public final long glColor4f;
    public final long glColor4d;
    public final long glColor4ub;
    public final long glColor4us;
    public final long glColor4ui;
    public final long glColor4bv;
    public final long glColor4sv;
    public final long glColor4iv;
    public final long glColor4fv;
    public final long glColor4dv;
    public final long glColor4ubv;
    public final long glColor4usv;
    public final long glColor4uiv;
    public final long glColorMask;
    public final long glColorMaterial;
    public final long glColorPointer;
    public final long glCopyPixels;
    public final long glCullFace;
    public final long glDeleteLists;
    public final long glDepthFunc;
    public final long glDepthMask;
    public final long glDepthRange;
    public final long glDisableClientState;
    public final long glDrawArrays;
    public final long glDrawBuffer;
    public final long glDrawElements;
    public final long glDrawPixels;
    public final long glEdgeFlag;
    public final long glEdgeFlagv;
    public final long glEdgeFlagPointer;
    public final long glEnableClientState;
    public final long glEnd;
    public final long glEvalCoord1f;
    public final long glEvalCoord1fv;
    public final long glEvalCoord1d;
    public final long glEvalCoord1dv;
    public final long glEvalCoord2f;
    public final long glEvalCoord2fv;
    public final long glEvalCoord2d;
    public final long glEvalCoord2dv;
    public final long glEvalMesh1;
    public final long glEvalMesh2;
    public final long glEvalPoint1;
    public final long glEvalPoint2;
    public final long glFeedbackBuffer;
    public final long glFinish;
    public final long glFlush;
    public final long glFogi;
    public final long glFogiv;
    public final long glFogf;
    public final long glFogfv;
    public final long glFrontFace;
    public final long glGenLists;
    public final long glGenTextures;
    public final long glDeleteTextures;
    public final long glGetClipPlane;
    public final long glGetBooleanv;
    public final long glGetFloatv;
    public final long glGetIntegerv;
    public final long glGetDoublev;
    public final long glGetError;
    public final long glGetLightiv;
    public final long glGetLightfv;
    public final long glGetMapiv;
    public final long glGetMapfv;
    public final long glGetMapdv;
    public final long glGetMaterialiv;
    public final long glGetMaterialfv;
    public final long glGetPixelMapfv;
    public final long glGetPixelMapusv;
    public final long glGetPixelMapuiv;
    public final long glGetPointerv;
    public final long glGetPolygonStipple;
    public final long glGetString;
    public final long glGetTexEnviv;
    public final long glGetTexEnvfv;
    public final long glGetTexGeniv;
    public final long glGetTexGenfv;
    public final long glGetTexGendv;
    public final long glGetTexImage;
    public final long glGetTexLevelParameteriv;
    public final long glGetTexLevelParameterfv;
    public final long glGetTexParameteriv;
    public final long glGetTexParameterfv;
    public final long glHint;
    public final long glIndexi;
    public final long glIndexub;
    public final long glIndexs;
    public final long glIndexf;
    public final long glIndexd;
    public final long glIndexiv;
    public final long glIndexubv;
    public final long glIndexsv;
    public final long glIndexfv;
    public final long glIndexdv;
    public final long glIndexMask;
    public final long glIndexPointer;
    public final long glInitNames;
    public final long glInterleavedArrays;
    public final long glIsEnabled;
    public final long glIsList;
    public final long glIsTexture;
    public final long glLightModeli;
    public final long glLightModelf;
    public final long glLightModeliv;
    public final long glLightModelfv;
    public final long glLighti;
    public final long glLightf;
    public final long glLightiv;
    public final long glLightfv;
    public final long glLineStipple;
    public final long glLineWidth;
    public final long glListBase;
    public final long glLoadMatrixf;
    public final long glLoadMatrixd;
    public final long glLoadIdentity;
    public final long glLoadName;
    public final long glLogicOp;
    public final long glMap1f;
    public final long glMap1d;
    public final long glMap2f;
    public final long glMap2d;
    public final long glMapGrid1f;
    public final long glMapGrid1d;
    public final long glMapGrid2f;
    public final long glMapGrid2d;
    public final long glMateriali;
    public final long glMaterialf;
    public final long glMaterialiv;
    public final long glMaterialfv;
    public final long glMatrixMode;
    public final long glMultMatrixf;
    public final long glMultMatrixd;
    public final long glFrustum;
    public final long glNewList;
    public final long glEndList;
    public final long glNormal3f;
    public final long glNormal3b;
    public final long glNormal3s;
    public final long glNormal3i;
    public final long glNormal3d;
    public final long glNormal3fv;
    public final long glNormal3bv;
    public final long glNormal3sv;
    public final long glNormal3iv;
    public final long glNormal3dv;
    public final long glNormalPointer;
    public final long glOrtho;
    public final long glPassThrough;
    public final long glPixelMapfv;
    public final long glPixelMapusv;
    public final long glPixelMapuiv;
    public final long glPixelStorei;
    public final long glPixelStoref;
    public final long glPixelTransferi;
    public final long glPixelTransferf;
    public final long glPixelZoom;
    public final long glPointSize;
    public final long glPolygonMode;
    public final long glPolygonOffset;
    public final long glPolygonStipple;
    public final long glPushAttrib;
    public final long glPushClientAttrib;
    public final long glPopAttrib;
    public final long glPopClientAttrib;
    public final long glPopMatrix;
    public final long glPopName;
    public final long glPrioritizeTextures;
    public final long glPushMatrix;
    public final long glPushName;
    public final long glRasterPos2i;
    public final long glRasterPos2s;
    public final long glRasterPos2f;
    public final long glRasterPos2d;
    public final long glRasterPos2iv;
    public final long glRasterPos2sv;
    public final long glRasterPos2fv;
    public final long glRasterPos2dv;
    public final long glRasterPos3i;
    public final long glRasterPos3s;
    public final long glRasterPos3f;
    public final long glRasterPos3d;
    public final long glRasterPos3iv;
    public final long glRasterPos3sv;
    public final long glRasterPos3fv;
    public final long glRasterPos3dv;
    public final long glRasterPos4i;
    public final long glRasterPos4s;
    public final long glRasterPos4f;
    public final long glRasterPos4d;
    public final long glRasterPos4iv;
    public final long glRasterPos4sv;
    public final long glRasterPos4fv;
    public final long glRasterPos4dv;
    public final long glReadBuffer;
    public final long glReadPixels;
    public final long glRecti;
    public final long glRects;
    public final long glRectf;
    public final long glRectd;
    public final long glRectiv;
    public final long glRectsv;
    public final long glRectfv;
    public final long glRectdv;
    public final long glRenderMode;
    public final long glRotatef;
    public final long glRotated;
    public final long glScalef;
    public final long glScaled;
    public final long glScissor;
    public final long glSelectBuffer;
    public final long glShadeModel;
    public final long glStencilFunc;
    public final long glStencilMask;
    public final long glStencilOp;
    public final long glTexCoord1f;
    public final long glTexCoord1s;
    public final long glTexCoord1i;
    public final long glTexCoord1d;
    public final long glTexCoord1fv;
    public final long glTexCoord1sv;
    public final long glTexCoord1iv;
    public final long glTexCoord1dv;
    public final long glTexCoord2f;
    public final long glTexCoord2s;
    public final long glTexCoord2i;
    public final long glTexCoord2d;
    public final long glTexCoord2fv;
    public final long glTexCoord2sv;
    public final long glTexCoord2iv;
    public final long glTexCoord2dv;
    public final long glTexCoord3f;
    public final long glTexCoord3s;
    public final long glTexCoord3i;
    public final long glTexCoord3d;
    public final long glTexCoord3fv;
    public final long glTexCoord3sv;
    public final long glTexCoord3iv;
    public final long glTexCoord3dv;
    public final long glTexCoord4f;
    public final long glTexCoord4s;
    public final long glTexCoord4i;
    public final long glTexCoord4d;
    public final long glTexCoord4fv;
    public final long glTexCoord4sv;
    public final long glTexCoord4iv;
    public final long glTexCoord4dv;
    public final long glTexCoordPointer;
    public final long glTexEnvi;
    public final long glTexEnviv;
    public final long glTexEnvf;
    public final long glTexEnvfv;
    public final long glTexGeni;
    public final long glTexGeniv;
    public final long glTexGenf;
    public final long glTexGenfv;
    public final long glTexGend;
    public final long glTexGendv;
    public final long glTexImage1D;
    public final long glTexImage2D;
    public final long glCopyTexImage1D;
    public final long glCopyTexImage2D;
    public final long glCopyTexSubImage1D;
    public final long glCopyTexSubImage2D;
    public final long glTexParameteri;
    public final long glTexParameteriv;
    public final long glTexParameterf;
    public final long glTexParameterfv;
    public final long glTexSubImage1D;
    public final long glTexSubImage2D;
    public final long glTranslatef;
    public final long glTranslated;
    public final long glVertex2f;
    public final long glVertex2s;
    public final long glVertex2i;
    public final long glVertex2d;
    public final long glVertex2fv;
    public final long glVertex2sv;
    public final long glVertex2iv;
    public final long glVertex2dv;
    public final long glVertex3f;
    public final long glVertex3s;
    public final long glVertex3i;
    public final long glVertex3d;
    public final long glVertex3fv;
    public final long glVertex3sv;
    public final long glVertex3iv;
    public final long glVertex3dv;
    public final long glVertex4f;
    public final long glVertex4s;
    public final long glVertex4i;
    public final long glVertex4d;
    public final long glVertex4fv;
    public final long glVertex4sv;
    public final long glVertex4iv;
    public final long glVertex4dv;
    public final long glVertexPointer;
    public final long glViewport;
    public final long glTexImage3D;
    public final long glTexSubImage3D;
    public final long glCopyTexSubImage3D;
    public final long glDrawRangeElements;
    public final long glCompressedTexImage3D;
    public final long glCompressedTexImage2D;
    public final long glCompressedTexImage1D;
    public final long glCompressedTexSubImage3D;
    public final long glCompressedTexSubImage2D;
    public final long glCompressedTexSubImage1D;
    public final long glGetCompressedTexImage;
    public final long glSampleCoverage;
    public final long glActiveTexture;
    public final long glClientActiveTexture;
    public final long glMultiTexCoord1f;
    public final long glMultiTexCoord1s;
    public final long glMultiTexCoord1i;
    public final long glMultiTexCoord1d;
    public final long glMultiTexCoord1fv;
    public final long glMultiTexCoord1sv;
    public final long glMultiTexCoord1iv;
    public final long glMultiTexCoord1dv;
    public final long glMultiTexCoord2f;
    public final long glMultiTexCoord2s;
    public final long glMultiTexCoord2i;
    public final long glMultiTexCoord2d;
    public final long glMultiTexCoord2fv;
    public final long glMultiTexCoord2sv;
    public final long glMultiTexCoord2iv;
    public final long glMultiTexCoord2dv;
    public final long glMultiTexCoord3f;
    public final long glMultiTexCoord3s;
    public final long glMultiTexCoord3i;
    public final long glMultiTexCoord3d;
    public final long glMultiTexCoord3fv;
    public final long glMultiTexCoord3sv;
    public final long glMultiTexCoord3iv;
    public final long glMultiTexCoord3dv;
    public final long glMultiTexCoord4f;
    public final long glMultiTexCoord4s;
    public final long glMultiTexCoord4i;
    public final long glMultiTexCoord4d;
    public final long glMultiTexCoord4fv;
    public final long glMultiTexCoord4sv;
    public final long glMultiTexCoord4iv;
    public final long glMultiTexCoord4dv;
    public final long glLoadTransposeMatrixf;
    public final long glLoadTransposeMatrixd;
    public final long glMultTransposeMatrixf;
    public final long glMultTransposeMatrixd;
    public final long glBlendColor;
    public final long glBlendEquation;
    public final long glFogCoordf;
    public final long glFogCoordd;
    public final long glFogCoordfv;
    public final long glFogCoorddv;
    public final long glFogCoordPointer;
    public final long glMultiDrawArrays;
    public final long glMultiDrawElements;
    public final long glPointParameterf;
    public final long glPointParameteri;
    public final long glPointParameterfv;
    public final long glPointParameteriv;
    public final long glSecondaryColor3b;
    public final long glSecondaryColor3s;
    public final long glSecondaryColor3i;
    public final long glSecondaryColor3f;
    public final long glSecondaryColor3d;
    public final long glSecondaryColor3ub;
    public final long glSecondaryColor3us;
    public final long glSecondaryColor3ui;
    public final long glSecondaryColor3bv;
    public final long glSecondaryColor3sv;
    public final long glSecondaryColor3iv;
    public final long glSecondaryColor3fv;
    public final long glSecondaryColor3dv;
    public final long glSecondaryColor3ubv;
    public final long glSecondaryColor3usv;
    public final long glSecondaryColor3uiv;
    public final long glSecondaryColorPointer;
    public final long glBlendFuncSeparate;
    public final long glWindowPos2i;
    public final long glWindowPos2s;
    public final long glWindowPos2f;
    public final long glWindowPos2d;
    public final long glWindowPos2iv;
    public final long glWindowPos2sv;
    public final long glWindowPos2fv;
    public final long glWindowPos2dv;
    public final long glWindowPos3i;
    public final long glWindowPos3s;
    public final long glWindowPos3f;
    public final long glWindowPos3d;
    public final long glWindowPos3iv;
    public final long glWindowPos3sv;
    public final long glWindowPos3fv;
    public final long glWindowPos3dv;
    public final long glBindBuffer;
    public final long glDeleteBuffers;
    public final long glGenBuffers;
    public final long glIsBuffer;
    public final long glBufferData;
    public final long glBufferSubData;
    public final long glGetBufferSubData;
    public final long glMapBuffer;
    public final long glUnmapBuffer;
    public final long glGetBufferParameteriv;
    public final long glGetBufferPointerv;
    public final long glGenQueries;
    public final long glDeleteQueries;
    public final long glIsQuery;
    public final long glBeginQuery;
    public final long glEndQuery;
    public final long glGetQueryiv;
    public final long glGetQueryObjectiv;
    public final long glGetQueryObjectuiv;
    public final long glCreateProgram;
    public final long glDeleteProgram;
    public final long glIsProgram;
    public final long glCreateShader;
    public final long glDeleteShader;
    public final long glIsShader;
    public final long glAttachShader;
    public final long glDetachShader;
    public final long glShaderSource;
    public final long glCompileShader;
    public final long glLinkProgram;
    public final long glUseProgram;
    public final long glValidateProgram;
    public final long glUniform1f;
    public final long glUniform2f;
    public final long glUniform3f;
    public final long glUniform4f;
    public final long glUniform1i;
    public final long glUniform2i;
    public final long glUniform3i;
    public final long glUniform4i;
    public final long glUniform1fv;
    public final long glUniform2fv;
    public final long glUniform3fv;
    public final long glUniform4fv;
    public final long glUniform1iv;
    public final long glUniform2iv;
    public final long glUniform3iv;
    public final long glUniform4iv;
    public final long glUniformMatrix2fv;
    public final long glUniformMatrix3fv;
    public final long glUniformMatrix4fv;
    public final long glGetShaderiv;
    public final long glGetProgramiv;
    public final long glGetShaderInfoLog;
    public final long glGetProgramInfoLog;
    public final long glGetAttachedShaders;
    public final long glGetUniformLocation;
    public final long glGetActiveUniform;
    public final long glGetUniformfv;
    public final long glGetUniformiv;
    public final long glGetShaderSource;
    public final long glVertexAttrib1f;
    public final long glVertexAttrib1s;
    public final long glVertexAttrib1d;
    public final long glVertexAttrib2f;
    public final long glVertexAttrib2s;
    public final long glVertexAttrib2d;
    public final long glVertexAttrib3f;
    public final long glVertexAttrib3s;
    public final long glVertexAttrib3d;
    public final long glVertexAttrib4f;
    public final long glVertexAttrib4s;
    public final long glVertexAttrib4d;
    public final long glVertexAttrib4Nub;
    public final long glVertexAttrib1fv;
    public final long glVertexAttrib1sv;
    public final long glVertexAttrib1dv;
    public final long glVertexAttrib2fv;
    public final long glVertexAttrib2sv;
    public final long glVertexAttrib2dv;
    public final long glVertexAttrib3fv;
    public final long glVertexAttrib3sv;
    public final long glVertexAttrib3dv;
    public final long glVertexAttrib4fv;
    public final long glVertexAttrib4sv;
    public final long glVertexAttrib4dv;
    public final long glVertexAttrib4iv;
    public final long glVertexAttrib4bv;
    public final long glVertexAttrib4ubv;
    public final long glVertexAttrib4usv;
    public final long glVertexAttrib4uiv;
    public final long glVertexAttrib4Nbv;
    public final long glVertexAttrib4Nsv;
    public final long glVertexAttrib4Niv;
    public final long glVertexAttrib4Nubv;
    public final long glVertexAttrib4Nusv;
    public final long glVertexAttrib4Nuiv;
    public final long glVertexAttribPointer;
    public final long glEnableVertexAttribArray;
    public final long glDisableVertexAttribArray;
    public final long glBindAttribLocation;
    public final long glGetActiveAttrib;
    public final long glGetAttribLocation;
    public final long glGetVertexAttribiv;
    public final long glGetVertexAttribfv;
    public final long glGetVertexAttribdv;
    public final long glGetVertexAttribPointerv;
    public final long glDrawBuffers;
    public final long glBlendEquationSeparate;
    public final long glStencilOpSeparate;
    public final long glStencilFuncSeparate;
    public final long glStencilMaskSeparate;
    public final long glUniformMatrix2x3fv;
    public final long glUniformMatrix3x2fv;
    public final long glUniformMatrix2x4fv;
    public final long glUniformMatrix4x2fv;
    public final long glUniformMatrix3x4fv;
    public final long glUniformMatrix4x3fv;
    public final long glGetStringi;
    public final long glClearBufferiv;
    public final long glClearBufferuiv;
    public final long glClearBufferfv;
    public final long glClearBufferfi;
    public final long glVertexAttribI1i;
    public final long glVertexAttribI2i;
    public final long glVertexAttribI3i;
    public final long glVertexAttribI4i;
    public final long glVertexAttribI1ui;
    public final long glVertexAttribI2ui;
    public final long glVertexAttribI3ui;
    public final long glVertexAttribI4ui;
    public final long glVertexAttribI1iv;
    public final long glVertexAttribI2iv;
    public final long glVertexAttribI3iv;
    public final long glVertexAttribI4iv;
    public final long glVertexAttribI1uiv;
    public final long glVertexAttribI2uiv;
    public final long glVertexAttribI3uiv;
    public final long glVertexAttribI4uiv;
    public final long glVertexAttribI4bv;
    public final long glVertexAttribI4sv;
    public final long glVertexAttribI4ubv;
    public final long glVertexAttribI4usv;
    public final long glVertexAttribIPointer;
    public final long glGetVertexAttribIiv;
    public final long glGetVertexAttribIuiv;
    public final long glUniform1ui;
    public final long glUniform2ui;
    public final long glUniform3ui;
    public final long glUniform4ui;
    public final long glUniform1uiv;
    public final long glUniform2uiv;
    public final long glUniform3uiv;
    public final long glUniform4uiv;
    public final long glGetUniformuiv;
    public final long glBindFragDataLocation;
    public final long glGetFragDataLocation;
    public final long glBeginConditionalRender;
    public final long glEndConditionalRender;
    public final long glMapBufferRange;
    public final long glFlushMappedBufferRange;
    public final long glClampColor;
    public final long glIsRenderbuffer;
    public final long glBindRenderbuffer;
    public final long glDeleteRenderbuffers;
    public final long glGenRenderbuffers;
    public final long glRenderbufferStorage;
    public final long glRenderbufferStorageMultisample;
    public final long glGetRenderbufferParameteriv;
    public final long glIsFramebuffer;
    public final long glBindFramebuffer;
    public final long glDeleteFramebuffers;
    public final long glGenFramebuffers;
    public final long glCheckFramebufferStatus;
    public final long glFramebufferTexture1D;
    public final long glFramebufferTexture2D;
    public final long glFramebufferTexture3D;
    public final long glFramebufferTextureLayer;
    public final long glFramebufferRenderbuffer;
    public final long glGetFramebufferAttachmentParameteriv;
    public final long glBlitFramebuffer;
    public final long glGenerateMipmap;
    public final long glTexParameterIiv;
    public final long glTexParameterIuiv;
    public final long glGetTexParameterIiv;
    public final long glGetTexParameterIuiv;
    public final long glColorMaski;
    public final long glGetBooleani_v;
    public final long glGetIntegeri_v;
    public final long glEnablei;
    public final long glDisablei;
    public final long glIsEnabledi;
    public final long glBindBufferRange;
    public final long glBindBufferBase;
    public final long glBeginTransformFeedback;
    public final long glEndTransformFeedback;
    public final long glTransformFeedbackVaryings;
    public final long glGetTransformFeedbackVarying;
    public final long glBindVertexArray;
    public final long glDeleteVertexArrays;
    public final long glGenVertexArrays;
    public final long glIsVertexArray;
    public final long glDrawArraysInstanced;
    public final long glDrawElementsInstanced;
    public final long glCopyBufferSubData;
    public final long glPrimitiveRestartIndex;
    public final long glTexBuffer;
    public final long glGetUniformIndices;
    public final long glGetActiveUniformsiv;
    public final long glGetActiveUniformName;
    public final long glGetUniformBlockIndex;
    public final long glGetActiveUniformBlockiv;
    public final long glGetActiveUniformBlockName;
    public final long glUniformBlockBinding;
    public final long glGetBufferParameteri64v;
    public final long glDrawElementsBaseVertex;
    public final long glDrawRangeElementsBaseVertex;
    public final long glDrawElementsInstancedBaseVertex;
    public final long glMultiDrawElementsBaseVertex;
    public final long glProvokingVertex;
    public final long glTexImage2DMultisample;
    public final long glTexImage3DMultisample;
    public final long glGetMultisamplefv;
    public final long glSampleMaski;
    public final long glFramebufferTexture;
    public final long glFenceSync;
    public final long glIsSync;
    public final long glDeleteSync;
    public final long glClientWaitSync;
    public final long glWaitSync;
    public final long glGetInteger64v;
    public final long glGetInteger64i_v;
    public final long glGetSynciv;
    public final long glBindFragDataLocationIndexed;
    public final long glGetFragDataIndex;
    public final long glGenSamplers;
    public final long glDeleteSamplers;
    public final long glIsSampler;
    public final long glBindSampler;
    public final long glSamplerParameteri;
    public final long glSamplerParameterf;
    public final long glSamplerParameteriv;
    public final long glSamplerParameterfv;
    public final long glSamplerParameterIiv;
    public final long glSamplerParameterIuiv;
    public final long glGetSamplerParameteriv;
    public final long glGetSamplerParameterfv;
    public final long glGetSamplerParameterIiv;
    public final long glGetSamplerParameterIuiv;
    public final long glQueryCounter;
    public final long glGetQueryObjecti64v;
    public final long glGetQueryObjectui64v;
    public final long glVertexAttribDivisor;
    public final long glVertexP2ui;
    public final long glVertexP3ui;
    public final long glVertexP4ui;
    public final long glVertexP2uiv;
    public final long glVertexP3uiv;
    public final long glVertexP4uiv;
    public final long glTexCoordP1ui;
    public final long glTexCoordP2ui;
    public final long glTexCoordP3ui;
    public final long glTexCoordP4ui;
    public final long glTexCoordP1uiv;
    public final long glTexCoordP2uiv;
    public final long glTexCoordP3uiv;
    public final long glTexCoordP4uiv;
    public final long glMultiTexCoordP1ui;
    public final long glMultiTexCoordP2ui;
    public final long glMultiTexCoordP3ui;
    public final long glMultiTexCoordP4ui;
    public final long glMultiTexCoordP1uiv;
    public final long glMultiTexCoordP2uiv;
    public final long glMultiTexCoordP3uiv;
    public final long glMultiTexCoordP4uiv;
    public final long glNormalP3ui;
    public final long glNormalP3uiv;
    public final long glColorP3ui;
    public final long glColorP4ui;
    public final long glColorP3uiv;
    public final long glColorP4uiv;
    public final long glSecondaryColorP3ui;
    public final long glSecondaryColorP3uiv;
    public final long glVertexAttribP1ui;
    public final long glVertexAttribP2ui;
    public final long glVertexAttribP3ui;
    public final long glVertexAttribP4ui;
    public final long glVertexAttribP1uiv;
    public final long glVertexAttribP2uiv;
    public final long glVertexAttribP3uiv;
    public final long glVertexAttribP4uiv;
    public final long glBlendEquationi;
    public final long glBlendEquationSeparatei;
    public final long glBlendFunci;
    public final long glBlendFuncSeparatei;
    public final long glDrawArraysIndirect;
    public final long glDrawElementsIndirect;
    public final long glUniform1d;
    public final long glUniform2d;
    public final long glUniform3d;
    public final long glUniform4d;
    public final long glUniform1dv;
    public final long glUniform2dv;
    public final long glUniform3dv;
    public final long glUniform4dv;
    public final long glUniformMatrix2dv;
    public final long glUniformMatrix3dv;
    public final long glUniformMatrix4dv;
    public final long glUniformMatrix2x3dv;
    public final long glUniformMatrix2x4dv;
    public final long glUniformMatrix3x2dv;
    public final long glUniformMatrix3x4dv;
    public final long glUniformMatrix4x2dv;
    public final long glUniformMatrix4x3dv;
    public final long glGetUniformdv;
    public final long glMinSampleShading;
    public final long glGetSubroutineUniformLocation;
    public final long glGetSubroutineIndex;
    public final long glGetActiveSubroutineUniformiv;
    public final long glGetActiveSubroutineUniformName;
    public final long glGetActiveSubroutineName;
    public final long glUniformSubroutinesuiv;
    public final long glGetUniformSubroutineuiv;
    public final long glGetProgramStageiv;
    public final long glPatchParameteri;
    public final long glPatchParameterfv;
    public final long glBindTransformFeedback;
    public final long glDeleteTransformFeedbacks;
    public final long glGenTransformFeedbacks;
    public final long glIsTransformFeedback;
    public final long glPauseTransformFeedback;
    public final long glResumeTransformFeedback;
    public final long glDrawTransformFeedback;
    public final long glDrawTransformFeedbackStream;
    public final long glBeginQueryIndexed;
    public final long glEndQueryIndexed;
    public final long glGetQueryIndexediv;
    public final long glReleaseShaderCompiler;
    public final long glShaderBinary;
    public final long glGetShaderPrecisionFormat;
    public final long glDepthRangef;
    public final long glClearDepthf;
    public final long glGetProgramBinary;
    public final long glProgramBinary;
    public final long glProgramParameteri;
    public final long glUseProgramStages;
    public final long glActiveShaderProgram;
    public final long glCreateShaderProgramv;
    public final long glBindProgramPipeline;
    public final long glDeleteProgramPipelines;
    public final long glGenProgramPipelines;
    public final long glIsProgramPipeline;
    public final long glGetProgramPipelineiv;
    public final long glProgramUniform1i;
    public final long glProgramUniform2i;
    public final long glProgramUniform3i;
    public final long glProgramUniform4i;
    public final long glProgramUniform1ui;
    public final long glProgramUniform2ui;
    public final long glProgramUniform3ui;
    public final long glProgramUniform4ui;
    public final long glProgramUniform1f;
    public final long glProgramUniform2f;
    public final long glProgramUniform3f;
    public final long glProgramUniform4f;
    public final long glProgramUniform1d;
    public final long glProgramUniform2d;
    public final long glProgramUniform3d;
    public final long glProgramUniform4d;
    public final long glProgramUniform1iv;
    public final long glProgramUniform2iv;
    public final long glProgramUniform3iv;
    public final long glProgramUniform4iv;
    public final long glProgramUniform1uiv;
    public final long glProgramUniform2uiv;
    public final long glProgramUniform3uiv;
    public final long glProgramUniform4uiv;
    public final long glProgramUniform1fv;
    public final long glProgramUniform2fv;
    public final long glProgramUniform3fv;
    public final long glProgramUniform4fv;
    public final long glProgramUniform1dv;
    public final long glProgramUniform2dv;
    public final long glProgramUniform3dv;
    public final long glProgramUniform4dv;
    public final long glProgramUniformMatrix2fv;
    public final long glProgramUniformMatrix3fv;
    public final long glProgramUniformMatrix4fv;
    public final long glProgramUniformMatrix2dv;
    public final long glProgramUniformMatrix3dv;
    public final long glProgramUniformMatrix4dv;
    public final long glProgramUniformMatrix2x3fv;
    public final long glProgramUniformMatrix3x2fv;
    public final long glProgramUniformMatrix2x4fv;
    public final long glProgramUniformMatrix4x2fv;
    public final long glProgramUniformMatrix3x4fv;
    public final long glProgramUniformMatrix4x3fv;
    public final long glProgramUniformMatrix2x3dv;
    public final long glProgramUniformMatrix3x2dv;
    public final long glProgramUniformMatrix2x4dv;
    public final long glProgramUniformMatrix4x2dv;
    public final long glProgramUniformMatrix3x4dv;
    public final long glProgramUniformMatrix4x3dv;
    public final long glValidateProgramPipeline;
    public final long glGetProgramPipelineInfoLog;
    public final long glVertexAttribL1d;
    public final long glVertexAttribL2d;
    public final long glVertexAttribL3d;
    public final long glVertexAttribL4d;
    public final long glVertexAttribL1dv;
    public final long glVertexAttribL2dv;
    public final long glVertexAttribL3dv;
    public final long glVertexAttribL4dv;
    public final long glVertexAttribLPointer;
    public final long glGetVertexAttribLdv;
    public final long glViewportArrayv;
    public final long glViewportIndexedf;
    public final long glViewportIndexedfv;
    public final long glScissorArrayv;
    public final long glScissorIndexed;
    public final long glScissorIndexedv;
    public final long glDepthRangeArrayv;
    public final long glDepthRangeIndexed;
    public final long glGetFloati_v;
    public final long glGetDoublei_v;
    public final long glGetActiveAtomicCounterBufferiv;
    public final long glTexStorage1D;
    public final long glTexStorage2D;
    public final long glTexStorage3D;
    public final long glDrawTransformFeedbackInstanced;
    public final long glDrawTransformFeedbackStreamInstanced;
    public final long glDrawArraysInstancedBaseInstance;
    public final long glDrawElementsInstancedBaseInstance;
    public final long glDrawElementsInstancedBaseVertexBaseInstance;
    public final long glBindImageTexture;
    public final long glMemoryBarrier;
    public final long glGetInternalformativ;
    public final long glClearBufferData;
    public final long glClearBufferSubData;
    public final long glDispatchCompute;
    public final long glDispatchComputeIndirect;
    public final long glCopyImageSubData;
    public final long glDebugMessageControl;
    public final long glDebugMessageInsert;
    public final long glDebugMessageCallback;
    public final long glGetDebugMessageLog;
    public final long glPushDebugGroup;
    public final long glPopDebugGroup;
    public final long glObjectLabel;
    public final long glGetObjectLabel;
    public final long glObjectPtrLabel;
    public final long glGetObjectPtrLabel;
    public final long glFramebufferParameteri;
    public final long glGetFramebufferParameteriv;
    public final long glGetInternalformati64v;
    public final long glInvalidateTexSubImage;
    public final long glInvalidateTexImage;
    public final long glInvalidateBufferSubData;
    public final long glInvalidateBufferData;
    public final long glInvalidateFramebuffer;
    public final long glInvalidateSubFramebuffer;
    public final long glMultiDrawArraysIndirect;
    public final long glMultiDrawElementsIndirect;
    public final long glGetProgramInterfaceiv;
    public final long glGetProgramResourceIndex;
    public final long glGetProgramResourceName;
    public final long glGetProgramResourceiv;
    public final long glGetProgramResourceLocation;
    public final long glGetProgramResourceLocationIndex;
    public final long glShaderStorageBlockBinding;
    public final long glTexBufferRange;
    public final long glTexStorage2DMultisample;
    public final long glTexStorage3DMultisample;
    public final long glTextureView;
    public final long glBindVertexBuffer;
    public final long glVertexAttribFormat;
    public final long glVertexAttribIFormat;
    public final long glVertexAttribLFormat;
    public final long glVertexAttribBinding;
    public final long glVertexBindingDivisor;
    public final long glBufferStorage;
    public final long glClearTexSubImage;
    public final long glClearTexImage;
    public final long glBindBuffersBase;
    public final long glBindBuffersRange;
    public final long glBindTextures;
    public final long glBindSamplers;
    public final long glBindImageTextures;
    public final long glBindVertexBuffers;
    public final long glClipControl;
    public final long glCreateTransformFeedbacks;
    public final long glTransformFeedbackBufferBase;
    public final long glTransformFeedbackBufferRange;
    public final long glGetTransformFeedbackiv;
    public final long glGetTransformFeedbacki_v;
    public final long glGetTransformFeedbacki64_v;
    public final long glCreateBuffers;
    public final long glNamedBufferStorage;
    public final long glNamedBufferData;
    public final long glNamedBufferSubData;
    public final long glCopyNamedBufferSubData;
    public final long glClearNamedBufferData;
    public final long glClearNamedBufferSubData;
    public final long glMapNamedBuffer;
    public final long glMapNamedBufferRange;
    public final long glUnmapNamedBuffer;
    public final long glFlushMappedNamedBufferRange;
    public final long glGetNamedBufferParameteriv;
    public final long glGetNamedBufferParameteri64v;
    public final long glGetNamedBufferPointerv;
    public final long glGetNamedBufferSubData;
    public final long glCreateFramebuffers;
    public final long glNamedFramebufferRenderbuffer;
    public final long glNamedFramebufferParameteri;
    public final long glNamedFramebufferTexture;
    public final long glNamedFramebufferTextureLayer;
    public final long glNamedFramebufferDrawBuffer;
    public final long glNamedFramebufferDrawBuffers;
    public final long glNamedFramebufferReadBuffer;
    public final long glInvalidateNamedFramebufferData;
    public final long glInvalidateNamedFramebufferSubData;
    public final long glClearNamedFramebufferiv;
    public final long glClearNamedFramebufferuiv;
    public final long glClearNamedFramebufferfv;
    public final long glClearNamedFramebufferfi;
    public final long glBlitNamedFramebuffer;
    public final long glCheckNamedFramebufferStatus;
    public final long glGetNamedFramebufferParameteriv;
    public final long glGetNamedFramebufferAttachmentParameteriv;
    public final long glCreateRenderbuffers;
    public final long glNamedRenderbufferStorage;
    public final long glNamedRenderbufferStorageMultisample;
    public final long glGetNamedRenderbufferParameteriv;
    public final long glCreateTextures;
    public final long glTextureBuffer;
    public final long glTextureBufferRange;
    public final long glTextureStorage1D;
    public final long glTextureStorage2D;
    public final long glTextureStorage3D;
    public final long glTextureStorage2DMultisample;
    public final long glTextureStorage3DMultisample;
    public final long glTextureSubImage1D;
    public final long glTextureSubImage2D;
    public final long glTextureSubImage3D;
    public final long glCompressedTextureSubImage1D;
    public final long glCompressedTextureSubImage2D;
    public final long glCompressedTextureSubImage3D;
    public final long glCopyTextureSubImage1D;
    public final long glCopyTextureSubImage2D;
    public final long glCopyTextureSubImage3D;
    public final long glTextureParameterf;
    public final long glTextureParameterfv;
    public final long glTextureParameteri;
    public final long glTextureParameterIiv;
    public final long glTextureParameterIuiv;
    public final long glTextureParameteriv;
    public final long glGenerateTextureMipmap;
    public final long glBindTextureUnit;
    public final long glGetTextureImage;
    public final long glGetCompressedTextureImage;
    public final long glGetTextureLevelParameterfv;
    public final long glGetTextureLevelParameteriv;
    public final long glGetTextureParameterfv;
    public final long glGetTextureParameterIiv;
    public final long glGetTextureParameterIuiv;
    public final long glGetTextureParameteriv;
    public final long glCreateVertexArrays;
    public final long glDisableVertexArrayAttrib;
    public final long glEnableVertexArrayAttrib;
    public final long glVertexArrayElementBuffer;
    public final long glVertexArrayVertexBuffer;
    public final long glVertexArrayVertexBuffers;
    public final long glVertexArrayAttribFormat;
    public final long glVertexArrayAttribIFormat;
    public final long glVertexArrayAttribLFormat;
    public final long glVertexArrayAttribBinding;
    public final long glVertexArrayBindingDivisor;
    public final long glGetVertexArrayiv;
    public final long glGetVertexArrayIndexediv;
    public final long glGetVertexArrayIndexed64iv;
    public final long glCreateSamplers;
    public final long glCreateProgramPipelines;
    public final long glCreateQueries;
    public final long glGetQueryBufferObjectiv;
    public final long glGetQueryBufferObjectuiv;
    public final long glGetQueryBufferObjecti64v;
    public final long glGetQueryBufferObjectui64v;
    public final long glMemoryBarrierByRegion;
    public final long glGetTextureSubImage;
    public final long glGetCompressedTextureSubImage;
    public final long glTextureBarrier;
    public final long glGetGraphicsResetStatus;
    public final long glGetnMapdv;
    public final long glGetnMapfv;
    public final long glGetnMapiv;
    public final long glGetnPixelMapfv;
    public final long glGetnPixelMapuiv;
    public final long glGetnPixelMapusv;
    public final long glGetnPolygonStipple;
    public final long glGetnTexImage;
    public final long glReadnPixels;
    public final long glGetnColorTable;
    public final long glGetnConvolutionFilter;
    public final long glGetnSeparableFilter;
    public final long glGetnHistogram;
    public final long glGetnMinmax;
    public final long glGetnCompressedTexImage;
    public final long glGetnUniformfv;
    public final long glGetnUniformdv;
    public final long glGetnUniformiv;
    public final long glGetnUniformuiv;
    public final long glMultiDrawArraysIndirectCount;
    public final long glMultiDrawElementsIndirectCount;
    public final long glPolygonOffsetClamp;
    public final long glSpecializeShader;
    public final long glDebugMessageEnableAMD;
    public final long glDebugMessageInsertAMD;
    public final long glDebugMessageCallbackAMD;
    public final long glGetDebugMessageLogAMD;
    public final long glBlendFuncIndexedAMD;
    public final long glBlendFuncSeparateIndexedAMD;
    public final long glBlendEquationIndexedAMD;
    public final long glBlendEquationSeparateIndexedAMD;
    public final long glRenderbufferStorageMultisampleAdvancedAMD;
    public final long glNamedRenderbufferStorageMultisampleAdvancedAMD;
    public final long glUniform1i64NV;
    public final long glUniform2i64NV;
    public final long glUniform3i64NV;
    public final long glUniform4i64NV;
    public final long glUniform1i64vNV;
    public final long glUniform2i64vNV;
    public final long glUniform3i64vNV;
    public final long glUniform4i64vNV;
    public final long glUniform1ui64NV;
    public final long glUniform2ui64NV;
    public final long glUniform3ui64NV;
    public final long glUniform4ui64NV;
    public final long glUniform1ui64vNV;
    public final long glUniform2ui64vNV;
    public final long glUniform3ui64vNV;
    public final long glUniform4ui64vNV;
    public final long glGetUniformi64vNV;
    public final long glGetUniformui64vNV;
    public final long glProgramUniform1i64NV;
    public final long glProgramUniform2i64NV;
    public final long glProgramUniform3i64NV;
    public final long glProgramUniform4i64NV;
    public final long glProgramUniform1i64vNV;
    public final long glProgramUniform2i64vNV;
    public final long glProgramUniform3i64vNV;
    public final long glProgramUniform4i64vNV;
    public final long glProgramUniform1ui64NV;
    public final long glProgramUniform2ui64NV;
    public final long glProgramUniform3ui64NV;
    public final long glProgramUniform4ui64NV;
    public final long glProgramUniform1ui64vNV;
    public final long glProgramUniform2ui64vNV;
    public final long glProgramUniform3ui64vNV;
    public final long glProgramUniform4ui64vNV;
    public final long glVertexAttribParameteriAMD;
    public final long glQueryObjectParameteruiAMD;
    public final long glGetPerfMonitorGroupsAMD;
    public final long glGetPerfMonitorCountersAMD;
    public final long glGetPerfMonitorGroupStringAMD;
    public final long glGetPerfMonitorCounterStringAMD;
    public final long glGetPerfMonitorCounterInfoAMD;
    public final long glGenPerfMonitorsAMD;
    public final long glDeletePerfMonitorsAMD;
    public final long glSelectPerfMonitorCountersAMD;
    public final long glBeginPerfMonitorAMD;
    public final long glEndPerfMonitorAMD;
    public final long glGetPerfMonitorCounterDataAMD;
    public final long glSetMultisamplefvAMD;
    public final long glTexStorageSparseAMD;
    public final long glTextureStorageSparseAMD;
    public final long glStencilOpValueAMD;
    public final long glTessellationFactorAMD;
    public final long glTessellationModeAMD;
    public final long glGetTextureHandleARB;
    public final long glGetTextureSamplerHandleARB;
    public final long glMakeTextureHandleResidentARB;
    public final long glMakeTextureHandleNonResidentARB;
    public final long glGetImageHandleARB;
    public final long glMakeImageHandleResidentARB;
    public final long glMakeImageHandleNonResidentARB;
    public final long glUniformHandleui64ARB;
    public final long glUniformHandleui64vARB;
    public final long glProgramUniformHandleui64ARB;
    public final long glProgramUniformHandleui64vARB;
    public final long glIsTextureHandleResidentARB;
    public final long glIsImageHandleResidentARB;
    public final long glVertexAttribL1ui64ARB;
    public final long glVertexAttribL1ui64vARB;
    public final long glGetVertexAttribLui64vARB;
    public final long glNamedBufferStorageEXT;
    public final long glCreateSyncFromCLeventARB;
    public final long glClearNamedBufferDataEXT;
    public final long glClearNamedBufferSubDataEXT;
    public final long glClampColorARB;
    public final long glDispatchComputeGroupSizeARB;
    public final long glDebugMessageControlARB;
    public final long glDebugMessageInsertARB;
    public final long glDebugMessageCallbackARB;
    public final long glGetDebugMessageLogARB;
    public final long glDrawBuffersARB;
    public final long glBlendEquationiARB;
    public final long glBlendEquationSeparateiARB;
    public final long glBlendFunciARB;
    public final long glBlendFuncSeparateiARB;
    public final long glDrawArraysInstancedARB;
    public final long glDrawElementsInstancedARB;
    public final long glPrimitiveBoundingBoxARB;
    public final long glNamedFramebufferParameteriEXT;
    public final long glGetNamedFramebufferParameterivEXT;
    public final long glProgramParameteriARB;
    public final long glFramebufferTextureARB;
    public final long glFramebufferTextureLayerARB;
    public final long glFramebufferTextureFaceARB;
    public final long glSpecializeShaderARB;
    public final long glProgramUniform1dEXT;
    public final long glProgramUniform2dEXT;
    public final long glProgramUniform3dEXT;
    public final long glProgramUniform4dEXT;
    public final long glProgramUniform1dvEXT;
    public final long glProgramUniform2dvEXT;
    public final long glProgramUniform3dvEXT;
    public final long glProgramUniform4dvEXT;
    public final long glProgramUniformMatrix2dvEXT;
    public final long glProgramUniformMatrix3dvEXT;
    public final long glProgramUniformMatrix4dvEXT;
    public final long glProgramUniformMatrix2x3dvEXT;
    public final long glProgramUniformMatrix2x4dvEXT;
    public final long glProgramUniformMatrix3x2dvEXT;
    public final long glProgramUniformMatrix3x4dvEXT;
    public final long glProgramUniformMatrix4x2dvEXT;
    public final long glProgramUniformMatrix4x3dvEXT;
    public final long glUniform1i64ARB;
    public final long glUniform1i64vARB;
    public final long glProgramUniform1i64ARB;
    public final long glProgramUniform1i64vARB;
    public final long glUniform2i64ARB;
    public final long glUniform2i64vARB;
    public final long glProgramUniform2i64ARB;
    public final long glProgramUniform2i64vARB;
    public final long glUniform3i64ARB;
    public final long glUniform3i64vARB;
    public final long glProgramUniform3i64ARB;
    public final long glProgramUniform3i64vARB;
    public final long glUniform4i64ARB;
    public final long glUniform4i64vARB;
    public final long glProgramUniform4i64ARB;
    public final long glProgramUniform4i64vARB;
    public final long glUniform1ui64ARB;
    public final long glUniform1ui64vARB;
    public final long glProgramUniform1ui64ARB;
    public final long glProgramUniform1ui64vARB;
    public final long glUniform2ui64ARB;
    public final long glUniform2ui64vARB;
    public final long glProgramUniform2ui64ARB;
    public final long glProgramUniform2ui64vARB;
    public final long glUniform3ui64ARB;
    public final long glUniform3ui64vARB;
    public final long glProgramUniform3ui64ARB;
    public final long glProgramUniform3ui64vARB;
    public final long glUniform4ui64ARB;
    public final long glUniform4ui64vARB;
    public final long glProgramUniform4ui64ARB;
    public final long glProgramUniform4ui64vARB;
    public final long glGetUniformi64vARB;
    public final long glGetUniformui64vARB;
    public final long glGetnUniformi64vARB;
    public final long glGetnUniformui64vARB;
    public final long glColorTable;
    public final long glCopyColorTable;
    public final long glColorTableParameteriv;
    public final long glColorTableParameterfv;
    public final long glGetColorTable;
    public final long glGetColorTableParameteriv;
    public final long glGetColorTableParameterfv;
    public final long glColorSubTable;
    public final long glCopyColorSubTable;
    public final long glConvolutionFilter1D;
    public final long glConvolutionFilter2D;
    public final long glCopyConvolutionFilter1D;
    public final long glCopyConvolutionFilter2D;
    public final long glGetConvolutionFilter;
    public final long glSeparableFilter2D;
    public final long glGetSeparableFilter;
    public final long glConvolutionParameteri;
    public final long glConvolutionParameteriv;
    public final long glConvolutionParameterf;
    public final long glConvolutionParameterfv;
    public final long glGetConvolutionParameteriv;
    public final long glGetConvolutionParameterfv;
    public final long glHistogram;
    public final long glResetHistogram;
    public final long glGetHistogram;
    public final long glGetHistogramParameteriv;
    public final long glGetHistogramParameterfv;
    public final long glMinmax;
    public final long glResetMinmax;
    public final long glGetMinmax;
    public final long glGetMinmaxParameteriv;
    public final long glGetMinmaxParameterfv;
    public final long glMultiDrawArraysIndirectCountARB;
    public final long glMultiDrawElementsIndirectCountARB;
    public final long glVertexAttribDivisorARB;
    public final long glVertexArrayVertexAttribDivisorEXT;
    public final long glCurrentPaletteMatrixARB;
    public final long glMatrixIndexuivARB;
    public final long glMatrixIndexubvARB;
    public final long glMatrixIndexusvARB;
    public final long glMatrixIndexPointerARB;
    public final long glSampleCoverageARB;
    public final long glActiveTextureARB;
    public final long glClientActiveTextureARB;
    public final long glMultiTexCoord1fARB;
    public final long glMultiTexCoord1sARB;
    public final long glMultiTexCoord1iARB;
    public final long glMultiTexCoord1dARB;
    public final long glMultiTexCoord1fvARB;
    public final long glMultiTexCoord1svARB;
    public final long glMultiTexCoord1ivARB;
    public final long glMultiTexCoord1dvARB;
    public final long glMultiTexCoord2fARB;
    public final long glMultiTexCoord2sARB;
    public final long glMultiTexCoord2iARB;
    public final long glMultiTexCoord2dARB;
    public final long glMultiTexCoord2fvARB;
    public final long glMultiTexCoord2svARB;
    public final long glMultiTexCoord2ivARB;
    public final long glMultiTexCoord2dvARB;
    public final long glMultiTexCoord3fARB;
    public final long glMultiTexCoord3sARB;
    public final long glMultiTexCoord3iARB;
    public final long glMultiTexCoord3dARB;
    public final long glMultiTexCoord3fvARB;
    public final long glMultiTexCoord3svARB;
    public final long glMultiTexCoord3ivARB;
    public final long glMultiTexCoord3dvARB;
    public final long glMultiTexCoord4fARB;
    public final long glMultiTexCoord4sARB;
    public final long glMultiTexCoord4iARB;
    public final long glMultiTexCoord4dARB;
    public final long glMultiTexCoord4fvARB;
    public final long glMultiTexCoord4svARB;
    public final long glMultiTexCoord4ivARB;
    public final long glMultiTexCoord4dvARB;
    public final long glGenQueriesARB;
    public final long glDeleteQueriesARB;
    public final long glIsQueryARB;
    public final long glBeginQueryARB;
    public final long glEndQueryARB;
    public final long glGetQueryivARB;
    public final long glGetQueryObjectivARB;
    public final long glGetQueryObjectuivARB;
    public final long glMaxShaderCompilerThreadsARB;
    public final long glPointParameterfARB;
    public final long glPointParameterfvARB;
    public final long glGetGraphicsResetStatusARB;
    public final long glGetnMapdvARB;
    public final long glGetnMapfvARB;
    public final long glGetnMapivARB;
    public final long glGetnPixelMapfvARB;
    public final long glGetnPixelMapuivARB;
    public final long glGetnPixelMapusvARB;
    public final long glGetnPolygonStippleARB;
    public final long glGetnTexImageARB;
    public final long glReadnPixelsARB;
    public final long glGetnColorTableARB;
    public final long glGetnConvolutionFilterARB;
    public final long glGetnSeparableFilterARB;
    public final long glGetnHistogramARB;
    public final long glGetnMinmaxARB;
    public final long glGetnCompressedTexImageARB;
    public final long glGetnUniformfvARB;
    public final long glGetnUniformivARB;
    public final long glGetnUniformuivARB;
    public final long glGetnUniformdvARB;
    public final long glFramebufferSampleLocationsfvARB;
    public final long glNamedFramebufferSampleLocationsfvARB;
    public final long glEvaluateDepthValuesARB;
    public final long glMinSampleShadingARB;
    public final long glDeleteObjectARB;
    public final long glGetHandleARB;
    public final long glDetachObjectARB;
    public final long glCreateShaderObjectARB;
    public final long glShaderSourceARB;
    public final long glCompileShaderARB;
    public final long glCreateProgramObjectARB;
    public final long glAttachObjectARB;
    public final long glLinkProgramARB;
    public final long glUseProgramObjectARB;
    public final long glValidateProgramARB;
    public final long glUniform1fARB;
    public final long glUniform2fARB;
    public final long glUniform3fARB;
    public final long glUniform4fARB;
    public final long glUniform1iARB;
    public final long glUniform2iARB;
    public final long glUniform3iARB;
    public final long glUniform4iARB;
    public final long glUniform1fvARB;
    public final long glUniform2fvARB;
    public final long glUniform3fvARB;
    public final long glUniform4fvARB;
    public final long glUniform1ivARB;
    public final long glUniform2ivARB;
    public final long glUniform3ivARB;
    public final long glUniform4ivARB;
    public final long glUniformMatrix2fvARB;
    public final long glUniformMatrix3fvARB;
    public final long glUniformMatrix4fvARB;
    public final long glGetObjectParameterfvARB;
    public final long glGetObjectParameterivARB;
    public final long glGetInfoLogARB;
    public final long glGetAttachedObjectsARB;
    public final long glGetUniformLocationARB;
    public final long glGetActiveUniformARB;
    public final long glGetUniformfvARB;
    public final long glGetUniformivARB;
    public final long glGetShaderSourceARB;
    public final long glNamedStringARB;
    public final long glDeleteNamedStringARB;
    public final long glCompileShaderIncludeARB;
    public final long glIsNamedStringARB;
    public final long glGetNamedStringARB;
    public final long glGetNamedStringivARB;
    public final long glBufferPageCommitmentARB;
    public final long glNamedBufferPageCommitmentEXT;
    public final long glNamedBufferPageCommitmentARB;
    public final long glTexPageCommitmentARB;
    public final long glTexturePageCommitmentEXT;
    public final long glTexBufferARB;
    public final long glTextureBufferRangeEXT;
    public final long glCompressedTexImage3DARB;
    public final long glCompressedTexImage2DARB;
    public final long glCompressedTexImage1DARB;
    public final long glCompressedTexSubImage3DARB;
    public final long glCompressedTexSubImage2DARB;
    public final long glCompressedTexSubImage1DARB;
    public final long glGetCompressedTexImageARB;
    public final long glTextureStorage1DEXT;
    public final long glTextureStorage2DEXT;
    public final long glTextureStorage3DEXT;
    public final long glTextureStorage2DMultisampleEXT;
    public final long glTextureStorage3DMultisampleEXT;
    public final long glLoadTransposeMatrixfARB;
    public final long glLoadTransposeMatrixdARB;
    public final long glMultTransposeMatrixfARB;
    public final long glMultTransposeMatrixdARB;
    public final long glVertexArrayVertexAttribLOffsetEXT;
    public final long glVertexArrayBindVertexBufferEXT;
    public final long glVertexArrayVertexAttribFormatEXT;
    public final long glVertexArrayVertexAttribIFormatEXT;
    public final long glVertexArrayVertexAttribLFormatEXT;
    public final long glVertexArrayVertexAttribBindingEXT;
    public final long glVertexArrayVertexBindingDivisorEXT;
    public final long glWeightfvARB;
    public final long glWeightbvARB;
    public final long glWeightubvARB;
    public final long glWeightsvARB;
    public final long glWeightusvARB;
    public final long glWeightivARB;
    public final long glWeightuivARB;
    public final long glWeightdvARB;
    public final long glWeightPointerARB;
    public final long glVertexBlendARB;
    public final long glBindBufferARB;
    public final long glDeleteBuffersARB;
    public final long glGenBuffersARB;
    public final long glIsBufferARB;
    public final long glBufferDataARB;
    public final long glBufferSubDataARB;
    public final long glGetBufferSubDataARB;
    public final long glMapBufferARB;
    public final long glUnmapBufferARB;
    public final long glGetBufferParameterivARB;
    public final long glGetBufferPointervARB;
    public final long glVertexAttrib1sARB;
    public final long glVertexAttrib1fARB;
    public final long glVertexAttrib1dARB;
    public final long glVertexAttrib2sARB;
    public final long glVertexAttrib2fARB;
    public final long glVertexAttrib2dARB;
    public final long glVertexAttrib3sARB;
    public final long glVertexAttrib3fARB;
    public final long glVertexAttrib3dARB;
    public final long glVertexAttrib4sARB;
    public final long glVertexAttrib4fARB;
    public final long glVertexAttrib4dARB;
    public final long glVertexAttrib4NubARB;
    public final long glVertexAttrib1svARB;
    public final long glVertexAttrib1fvARB;
    public final long glVertexAttrib1dvARB;
    public final long glVertexAttrib2svARB;
    public final long glVertexAttrib2fvARB;
    public final long glVertexAttrib2dvARB;
    public final long glVertexAttrib3svARB;
    public final long glVertexAttrib3fvARB;
    public final long glVertexAttrib3dvARB;
    public final long glVertexAttrib4fvARB;
    public final long glVertexAttrib4bvARB;
    public final long glVertexAttrib4svARB;
    public final long glVertexAttrib4ivARB;
    public final long glVertexAttrib4ubvARB;
    public final long glVertexAttrib4usvARB;
    public final long glVertexAttrib4uivARB;
    public final long glVertexAttrib4dvARB;
    public final long glVertexAttrib4NbvARB;
    public final long glVertexAttrib4NsvARB;
    public final long glVertexAttrib4NivARB;
    public final long glVertexAttrib4NubvARB;
    public final long glVertexAttrib4NusvARB;
    public final long glVertexAttrib4NuivARB;
    public final long glVertexAttribPointerARB;
    public final long glEnableVertexAttribArrayARB;
    public final long glDisableVertexAttribArrayARB;
    public final long glProgramStringARB;
    public final long glBindProgramARB;
    public final long glDeleteProgramsARB;
    public final long glGenProgramsARB;
    public final long glProgramEnvParameter4dARB;
    public final long glProgramEnvParameter4dvARB;
    public final long glProgramEnvParameter4fARB;
    public final long glProgramEnvParameter4fvARB;
    public final long glProgramLocalParameter4dARB;
    public final long glProgramLocalParameter4dvARB;
    public final long glProgramLocalParameter4fARB;
    public final long glProgramLocalParameter4fvARB;
    public final long glGetProgramEnvParameterfvARB;
    public final long glGetProgramEnvParameterdvARB;
    public final long glGetProgramLocalParameterfvARB;
    public final long glGetProgramLocalParameterdvARB;
    public final long glGetProgramivARB;
    public final long glGetProgramStringARB;
    public final long glGetVertexAttribfvARB;
    public final long glGetVertexAttribdvARB;
    public final long glGetVertexAttribivARB;
    public final long glGetVertexAttribPointervARB;
    public final long glIsProgramARB;
    public final long glBindAttribLocationARB;
    public final long glGetActiveAttribARB;
    public final long glGetAttribLocationARB;
    public final long glWindowPos2iARB;
    public final long glWindowPos2sARB;
    public final long glWindowPos2fARB;
    public final long glWindowPos2dARB;
    public final long glWindowPos2ivARB;
    public final long glWindowPos2svARB;
    public final long glWindowPos2fvARB;
    public final long glWindowPos2dvARB;
    public final long glWindowPos3iARB;
    public final long glWindowPos3sARB;
    public final long glWindowPos3fARB;
    public final long glWindowPos3dARB;
    public final long glWindowPos3ivARB;
    public final long glWindowPos3svARB;
    public final long glWindowPos3fvARB;
    public final long glWindowPos3dvARB;
    public final long glUniformBufferEXT;
    public final long glGetUniformBufferSizeEXT;
    public final long glGetUniformOffsetEXT;
    public final long glBlendColorEXT;
    public final long glBlendEquationSeparateEXT;
    public final long glBlendFuncSeparateEXT;
    public final long glBlendEquationEXT;
    public final long glLockArraysEXT;
    public final long glUnlockArraysEXT;
    public final long glLabelObjectEXT;
    public final long glGetObjectLabelEXT;
    public final long glInsertEventMarkerEXT;
    public final long glPushGroupMarkerEXT;
    public final long glPopGroupMarkerEXT;
    public final long glDepthBoundsEXT;
    public final long glClientAttribDefaultEXT;
    public final long glPushClientAttribDefaultEXT;
    public final long glMatrixLoadfEXT;
    public final long glMatrixLoaddEXT;
    public final long glMatrixMultfEXT;
    public final long glMatrixMultdEXT;
    public final long glMatrixLoadIdentityEXT;
    public final long glMatrixRotatefEXT;
    public final long glMatrixRotatedEXT;
    public final long glMatrixScalefEXT;
    public final long glMatrixScaledEXT;
    public final long glMatrixTranslatefEXT;
    public final long glMatrixTranslatedEXT;
    public final long glMatrixOrthoEXT;
    public final long glMatrixFrustumEXT;
    public final long glMatrixPushEXT;
    public final long glMatrixPopEXT;
    public final long glTextureParameteriEXT;
    public final long glTextureParameterivEXT;
    public final long glTextureParameterfEXT;
    public final long glTextureParameterfvEXT;
    public final long glTextureImage1DEXT;
    public final long glTextureImage2DEXT;
    public final long glTextureSubImage1DEXT;
    public final long glTextureSubImage2DEXT;
    public final long glCopyTextureImage1DEXT;
    public final long glCopyTextureImage2DEXT;
    public final long glCopyTextureSubImage1DEXT;
    public final long glCopyTextureSubImage2DEXT;
    public final long glGetTextureImageEXT;
    public final long glGetTextureParameterfvEXT;
    public final long glGetTextureParameterivEXT;
    public final long glGetTextureLevelParameterfvEXT;
    public final long glGetTextureLevelParameterivEXT;
    public final long glTextureImage3DEXT;
    public final long glTextureSubImage3DEXT;
    public final long glCopyTextureSubImage3DEXT;
    public final long glBindMultiTextureEXT;
    public final long glMultiTexCoordPointerEXT;
    public final long glMultiTexEnvfEXT;
    public final long glMultiTexEnvfvEXT;
    public final long glMultiTexEnviEXT;
    public final long glMultiTexEnvivEXT;
    public final long glMultiTexGendEXT;
    public final long glMultiTexGendvEXT;
    public final long glMultiTexGenfEXT;
    public final long glMultiTexGenfvEXT;
    public final long glMultiTexGeniEXT;
    public final long glMultiTexGenivEXT;
    public final long glGetMultiTexEnvfvEXT;
    public final long glGetMultiTexEnvivEXT;
    public final long glGetMultiTexGendvEXT;
    public final long glGetMultiTexGenfvEXT;
    public final long glGetMultiTexGenivEXT;
    public final long glMultiTexParameteriEXT;
    public final long glMultiTexParameterivEXT;
    public final long glMultiTexParameterfEXT;
    public final long glMultiTexParameterfvEXT;
    public final long glMultiTexImage1DEXT;
    public final long glMultiTexImage2DEXT;
    public final long glMultiTexSubImage1DEXT;
    public final long glMultiTexSubImage2DEXT;
    public final long glCopyMultiTexImage1DEXT;
    public final long glCopyMultiTexImage2DEXT;
    public final long glCopyMultiTexSubImage1DEXT;
    public final long glCopyMultiTexSubImage2DEXT;
    public final long glGetMultiTexImageEXT;
    public final long glGetMultiTexParameterfvEXT;
    public final long glGetMultiTexParameterivEXT;
    public final long glGetMultiTexLevelParameterfvEXT;
    public final long glGetMultiTexLevelParameterivEXT;
    public final long glMultiTexImage3DEXT;
    public final long glMultiTexSubImage3DEXT;
    public final long glCopyMultiTexSubImage3DEXT;
    public final long glEnableClientStateIndexedEXT;
    public final long glDisableClientStateIndexedEXT;
    public final long glEnableClientStateiEXT;
    public final long glDisableClientStateiEXT;
    public final long glGetFloatIndexedvEXT;
    public final long glGetDoubleIndexedvEXT;
    public final long glGetPointerIndexedvEXT;
    public final long glGetFloati_vEXT;
    public final long glGetDoublei_vEXT;
    public final long glGetPointeri_vEXT;
    public final long glEnableIndexedEXT;
    public final long glDisableIndexedEXT;
    public final long glIsEnabledIndexedEXT;
    public final long glGetIntegerIndexedvEXT;
    public final long glGetBooleanIndexedvEXT;
    public final long glNamedProgramStringEXT;
    public final long glNamedProgramLocalParameter4dEXT;
    public final long glNamedProgramLocalParameter4dvEXT;
    public final long glNamedProgramLocalParameter4fEXT;
    public final long glNamedProgramLocalParameter4fvEXT;
    public final long glGetNamedProgramLocalParameterdvEXT;
    public final long glGetNamedProgramLocalParameterfvEXT;
    public final long glGetNamedProgramivEXT;
    public final long glGetNamedProgramStringEXT;
    public final long glCompressedTextureImage3DEXT;
    public final long glCompressedTextureImage2DEXT;
    public final long glCompressedTextureImage1DEXT;
    public final long glCompressedTextureSubImage3DEXT;
    public final long glCompressedTextureSubImage2DEXT;
    public final long glCompressedTextureSubImage1DEXT;
    public final long glGetCompressedTextureImageEXT;
    public final long glCompressedMultiTexImage3DEXT;
    public final long glCompressedMultiTexImage2DEXT;
    public final long glCompressedMultiTexImage1DEXT;
    public final long glCompressedMultiTexSubImage3DEXT;
    public final long glCompressedMultiTexSubImage2DEXT;
    public final long glCompressedMultiTexSubImage1DEXT;
    public final long glGetCompressedMultiTexImageEXT;
    public final long glMatrixLoadTransposefEXT;
    public final long glMatrixLoadTransposedEXT;
    public final long glMatrixMultTransposefEXT;
    public final long glMatrixMultTransposedEXT;
    public final long glNamedBufferDataEXT;
    public final long glNamedBufferSubDataEXT;
    public final long glMapNamedBufferEXT;
    public final long glUnmapNamedBufferEXT;
    public final long glGetNamedBufferParameterivEXT;
    public final long glGetNamedBufferSubDataEXT;
    public final long glProgramUniform1fEXT;
    public final long glProgramUniform2fEXT;
    public final long glProgramUniform3fEXT;
    public final long glProgramUniform4fEXT;
    public final long glProgramUniform1iEXT;
    public final long glProgramUniform2iEXT;
    public final long glProgramUniform3iEXT;
    public final long glProgramUniform4iEXT;
    public final long glProgramUniform1fvEXT;
    public final long glProgramUniform2fvEXT;
    public final long glProgramUniform3fvEXT;
    public final long glProgramUniform4fvEXT;
    public final long glProgramUniform1ivEXT;
    public final long glProgramUniform2ivEXT;
    public final long glProgramUniform3ivEXT;
    public final long glProgramUniform4ivEXT;
    public final long glProgramUniformMatrix2fvEXT;
    public final long glProgramUniformMatrix3fvEXT;
    public final long glProgramUniformMatrix4fvEXT;
    public final long glProgramUniformMatrix2x3fvEXT;
    public final long glProgramUniformMatrix3x2fvEXT;
    public final long glProgramUniformMatrix2x4fvEXT;
    public final long glProgramUniformMatrix4x2fvEXT;
    public final long glProgramUniformMatrix3x4fvEXT;
    public final long glProgramUniformMatrix4x3fvEXT;
    public final long glTextureBufferEXT;
    public final long glMultiTexBufferEXT;
    public final long glTextureParameterIivEXT;
    public final long glTextureParameterIuivEXT;
    public final long glGetTextureParameterIivEXT;
    public final long glGetTextureParameterIuivEXT;
    public final long glMultiTexParameterIivEXT;
    public final long glMultiTexParameterIuivEXT;
    public final long glGetMultiTexParameterIivEXT;
    public final long glGetMultiTexParameterIuivEXT;
    public final long glProgramUniform1uiEXT;
    public final long glProgramUniform2uiEXT;
    public final long glProgramUniform3uiEXT;
    public final long glProgramUniform4uiEXT;
    public final long glProgramUniform1uivEXT;
    public final long glProgramUniform2uivEXT;
    public final long glProgramUniform3uivEXT;
    public final long glProgramUniform4uivEXT;
    public final long glNamedProgramLocalParameters4fvEXT;
    public final long glNamedProgramLocalParameterI4iEXT;
    public final long glNamedProgramLocalParameterI4ivEXT;
    public final long glNamedProgramLocalParametersI4ivEXT;
    public final long glNamedProgramLocalParameterI4uiEXT;
    public final long glNamedProgramLocalParameterI4uivEXT;
    public final long glNamedProgramLocalParametersI4uivEXT;
    public final long glGetNamedProgramLocalParameterIivEXT;
    public final long glGetNamedProgramLocalParameterIuivEXT;
    public final long glNamedRenderbufferStorageEXT;
    public final long glGetNamedRenderbufferParameterivEXT;
    public final long glNamedRenderbufferStorageMultisampleEXT;
    public final long glNamedRenderbufferStorageMultisampleCoverageEXT;
    public final long glCheckNamedFramebufferStatusEXT;
    public final long glNamedFramebufferTexture1DEXT;
    public final long glNamedFramebufferTexture2DEXT;
    public final long glNamedFramebufferTexture3DEXT;
    public final long glNamedFramebufferRenderbufferEXT;
    public final long glGetNamedFramebufferAttachmentParameterivEXT;
    public final long glGenerateTextureMipmapEXT;
    public final long glGenerateMultiTexMipmapEXT;
    public final long glFramebufferDrawBufferEXT;
    public final long glFramebufferDrawBuffersEXT;
    public final long glFramebufferReadBufferEXT;
    public final long glGetFramebufferParameterivEXT;
    public final long glNamedCopyBufferSubDataEXT;
    public final long glNamedFramebufferTextureEXT;
    public final long glNamedFramebufferTextureLayerEXT;
    public final long glNamedFramebufferTextureFaceEXT;
    public final long glTextureRenderbufferEXT;
    public final long glMultiTexRenderbufferEXT;
    public final long glVertexArrayVertexOffsetEXT;
    public final long glVertexArrayColorOffsetEXT;
    public final long glVertexArrayEdgeFlagOffsetEXT;
    public final long glVertexArrayIndexOffsetEXT;
    public final long glVertexArrayNormalOffsetEXT;
    public final long glVertexArrayTexCoordOffsetEXT;
    public final long glVertexArrayMultiTexCoordOffsetEXT;
    public final long glVertexArrayFogCoordOffsetEXT;
    public final long glVertexArraySecondaryColorOffsetEXT;
    public final long glVertexArrayVertexAttribOffsetEXT;
    public final long glVertexArrayVertexAttribIOffsetEXT;
    public final long glEnableVertexArrayEXT;
    public final long glDisableVertexArrayEXT;
    public final long glEnableVertexArrayAttribEXT;
    public final long glDisableVertexArrayAttribEXT;
    public final long glGetVertexArrayIntegervEXT;
    public final long glGetVertexArrayPointervEXT;
    public final long glGetVertexArrayIntegeri_vEXT;
    public final long glGetVertexArrayPointeri_vEXT;
    public final long glMapNamedBufferRangeEXT;
    public final long glFlushMappedNamedBufferRangeEXT;
    public final long glColorMaskIndexedEXT;
    public final long glDrawArraysInstancedEXT;
    public final long glDrawElementsInstancedEXT;
    public final long glEGLImageTargetTexStorageEXT;
    public final long glEGLImageTargetTextureStorageEXT;
    public final long glBufferStorageExternalEXT;
    public final long glNamedBufferStorageExternalEXT;
    public final long glBlitFramebufferEXT;
    public final long glBlitFramebufferLayersEXT;
    public final long glBlitFramebufferLayerEXT;
    public final long glRenderbufferStorageMultisampleEXT;
    public final long glIsRenderbufferEXT;
    public final long glBindRenderbufferEXT;
    public final long glDeleteRenderbuffersEXT;
    public final long glGenRenderbuffersEXT;
    public final long glRenderbufferStorageEXT;
    public final long glGetRenderbufferParameterivEXT;
    public final long glIsFramebufferEXT;
    public final long glBindFramebufferEXT;
    public final long glDeleteFramebuffersEXT;
    public final long glGenFramebuffersEXT;
    public final long glCheckFramebufferStatusEXT;
    public final long glFramebufferTexture1DEXT;
    public final long glFramebufferTexture2DEXT;
    public final long glFramebufferTexture3DEXT;
    public final long glFramebufferRenderbufferEXT;
    public final long glGetFramebufferAttachmentParameterivEXT;
    public final long glGenerateMipmapEXT;
    public final long glProgramParameteriEXT;
    public final long glFramebufferTextureEXT;
    public final long glFramebufferTextureLayerEXT;
    public final long glFramebufferTextureFaceEXT;
    public final long glProgramEnvParameters4fvEXT;
    public final long glProgramLocalParameters4fvEXT;
    public final long glVertexAttribI1iEXT;
    public final long glVertexAttribI2iEXT;
    public final long glVertexAttribI3iEXT;
    public final long glVertexAttribI4iEXT;
    public final long glVertexAttribI1uiEXT;
    public final long glVertexAttribI2uiEXT;
    public final long glVertexAttribI3uiEXT;
    public final long glVertexAttribI4uiEXT;
    public final long glVertexAttribI1ivEXT;
    public final long glVertexAttribI2ivEXT;
    public final long glVertexAttribI3ivEXT;
    public final long glVertexAttribI4ivEXT;
    public final long glVertexAttribI1uivEXT;
    public final long glVertexAttribI2uivEXT;
    public final long glVertexAttribI3uivEXT;
    public final long glVertexAttribI4uivEXT;
    public final long glVertexAttribI4bvEXT;
    public final long glVertexAttribI4svEXT;
    public final long glVertexAttribI4ubvEXT;
    public final long glVertexAttribI4usvEXT;
    public final long glVertexAttribIPointerEXT;
    public final long glGetVertexAttribIivEXT;
    public final long glGetVertexAttribIuivEXT;
    public final long glGetUniformuivEXT;
    public final long glBindFragDataLocationEXT;
    public final long glGetFragDataLocationEXT;
    public final long glUniform1uiEXT;
    public final long glUniform2uiEXT;
    public final long glUniform3uiEXT;
    public final long glUniform4uiEXT;
    public final long glUniform1uivEXT;
    public final long glUniform2uivEXT;
    public final long glUniform3uivEXT;
    public final long glUniform4uivEXT;
    public final long glGetUnsignedBytevEXT;
    public final long glGetUnsignedBytei_vEXT;
    public final long glDeleteMemoryObjectsEXT;
    public final long glIsMemoryObjectEXT;
    public final long glCreateMemoryObjectsEXT;
    public final long glMemoryObjectParameterivEXT;
    public final long glGetMemoryObjectParameterivEXT;
    public final long glTexStorageMem2DEXT;
    public final long glTexStorageMem2DMultisampleEXT;
    public final long glTexStorageMem3DEXT;
    public final long glTexStorageMem3DMultisampleEXT;
    public final long glBufferStorageMemEXT;
    public final long glTextureStorageMem2DEXT;
    public final long glTextureStorageMem2DMultisampleEXT;
    public final long glTextureStorageMem3DEXT;
    public final long glTextureStorageMem3DMultisampleEXT;
    public final long glNamedBufferStorageMemEXT;
    public final long glTexStorageMem1DEXT;
    public final long glTextureStorageMem1DEXT;
    public final long glImportMemoryFdEXT;
    public final long glImportMemoryWin32HandleEXT;
    public final long glImportMemoryWin32NameEXT;
    public final long glPointParameterfEXT;
    public final long glPointParameterfvEXT;
    public final long glPolygonOffsetClampEXT;
    public final long glProvokingVertexEXT;
    public final long glRasterSamplesEXT;
    public final long glSecondaryColor3bEXT;
    public final long glSecondaryColor3sEXT;
    public final long glSecondaryColor3iEXT;
    public final long glSecondaryColor3fEXT;
    public final long glSecondaryColor3dEXT;
    public final long glSecondaryColor3ubEXT;
    public final long glSecondaryColor3usEXT;
    public final long glSecondaryColor3uiEXT;
    public final long glSecondaryColor3bvEXT;
    public final long glSecondaryColor3svEXT;
    public final long glSecondaryColor3ivEXT;
    public final long glSecondaryColor3fvEXT;
    public final long glSecondaryColor3dvEXT;
    public final long glSecondaryColor3ubvEXT;
    public final long glSecondaryColor3usvEXT;
    public final long glSecondaryColor3uivEXT;
    public final long glSecondaryColorPointerEXT;
    public final long glGenSemaphoresEXT;
    public final long glDeleteSemaphoresEXT;
    public final long glIsSemaphoreEXT;
    public final long glSemaphoreParameterui64vEXT;
    public final long glGetSemaphoreParameterui64vEXT;
    public final long glWaitSemaphoreEXT;
    public final long glSignalSemaphoreEXT;
    public final long glImportSemaphoreFdEXT;
    public final long glImportSemaphoreWin32HandleEXT;
    public final long glImportSemaphoreWin32NameEXT;
    public final long glUseShaderProgramEXT;
    public final long glActiveProgramEXT;
    public final long glCreateShaderProgramEXT;
    public final long glFramebufferFetchBarrierEXT;
    public final long glBindImageTextureEXT;
    public final long glMemoryBarrierEXT;
    public final long glStencilClearTagEXT;
    public final long glActiveStencilFaceEXT;
    public final long glTexBufferEXT;
    public final long glClearColorIiEXT;
    public final long glClearColorIuiEXT;
    public final long glTexParameterIivEXT;
    public final long glTexParameterIuivEXT;
    public final long glGetTexParameterIivEXT;
    public final long glGetTexParameterIuivEXT;
    public final long glTexStorage1DEXT;
    public final long glTexStorage2DEXT;
    public final long glTexStorage3DEXT;
    public final long glGetQueryObjecti64vEXT;
    public final long glGetQueryObjectui64vEXT;
    public final long glBindBufferRangeEXT;
    public final long glBindBufferOffsetEXT;
    public final long glBindBufferBaseEXT;
    public final long glBeginTransformFeedbackEXT;
    public final long glEndTransformFeedbackEXT;
    public final long glTransformFeedbackVaryingsEXT;
    public final long glGetTransformFeedbackVaryingEXT;
    public final long glVertexAttribL1dEXT;
    public final long glVertexAttribL2dEXT;
    public final long glVertexAttribL3dEXT;
    public final long glVertexAttribL4dEXT;
    public final long glVertexAttribL1dvEXT;
    public final long glVertexAttribL2dvEXT;
    public final long glVertexAttribL3dvEXT;
    public final long glVertexAttribL4dvEXT;
    public final long glVertexAttribLPointerEXT;
    public final long glGetVertexAttribLdvEXT;
    public final long glAcquireKeyedMutexWin32EXT;
    public final long glReleaseKeyedMutexWin32EXT;
    public final long glWindowRectanglesEXT;
    public final long glImportSyncEXT;
    public final long glFrameTerminatorGREMEDY;
    public final long glStringMarkerGREMEDY;
    public final long glApplyFramebufferAttachmentCMAAINTEL;
    public final long glSyncTextureINTEL;
    public final long glUnmapTexture2DINTEL;
    public final long glMapTexture2DINTEL;
    public final long glBeginPerfQueryINTEL;
    public final long glCreatePerfQueryINTEL;
    public final long glDeletePerfQueryINTEL;
    public final long glEndPerfQueryINTEL;
    public final long glGetFirstPerfQueryIdINTEL;
    public final long glGetNextPerfQueryIdINTEL;
    public final long glGetPerfCounterInfoINTEL;
    public final long glGetPerfQueryDataINTEL;
    public final long glGetPerfQueryIdByNameINTEL;
    public final long glGetPerfQueryInfoINTEL;
    public final long glBlendBarrierKHR;
    public final long glMaxShaderCompilerThreadsKHR;
    public final long glFramebufferParameteriMESA;
    public final long glGetFramebufferParameterivMESA;
    public final long glAlphaToCoverageDitherControlNV;
    public final long glMultiDrawArraysIndirectBindlessNV;
    public final long glMultiDrawElementsIndirectBindlessNV;
    public final long glMultiDrawArraysIndirectBindlessCountNV;
    public final long glMultiDrawElementsIndirectBindlessCountNV;
    public final long glGetTextureHandleNV;
    public final long glGetTextureSamplerHandleNV;
    public final long glMakeTextureHandleResidentNV;
    public final long glMakeTextureHandleNonResidentNV;
    public final long glGetImageHandleNV;
    public final long glMakeImageHandleResidentNV;
    public final long glMakeImageHandleNonResidentNV;
    public final long glUniformHandleui64NV;
    public final long glUniformHandleui64vNV;
    public final long glProgramUniformHandleui64NV;
    public final long glProgramUniformHandleui64vNV;
    public final long glIsTextureHandleResidentNV;
    public final long glIsImageHandleResidentNV;
    public final long glBlendParameteriNV;
    public final long glBlendBarrierNV;
    public final long glViewportPositionWScaleNV;
    public final long glCreateStatesNV;
    public final long glDeleteStatesNV;
    public final long glIsStateNV;
    public final long glStateCaptureNV;
    public final long glGetCommandHeaderNV;
    public final long glGetStageIndexNV;
    public final long glDrawCommandsNV;
    public final long glDrawCommandsAddressNV;
    public final long glDrawCommandsStatesNV;
    public final long glDrawCommandsStatesAddressNV;
    public final long glCreateCommandListsNV;
    public final long glDeleteCommandListsNV;
    public final long glIsCommandListNV;
    public final long glListDrawCommandsStatesClientNV;
    public final long glCommandListSegmentsNV;
    public final long glCompileCommandListNV;
    public final long glCallCommandListNV;
    public final long glBeginConditionalRenderNV;
    public final long glEndConditionalRenderNV;
    public final long glSubpixelPrecisionBiasNV;
    public final long glConservativeRasterParameterfNV;
    public final long glConservativeRasterParameteriNV;
    public final long glCopyImageSubDataNV;
    public final long glDepthRangedNV;
    public final long glClearDepthdNV;
    public final long glDepthBoundsdNV;
    public final long glDrawTextureNV;
    public final long glDrawVkImageNV;
    public final long glGetVkProcAddrNV;
    public final long glWaitVkSemaphoreNV;
    public final long glSignalVkSemaphoreNV;
    public final long glSignalVkFenceNV;
    public final long glGetMultisamplefvNV;
    public final long glSampleMaskIndexedNV;
    public final long glTexRenderbufferNV;
    public final long glDeleteFencesNV;
    public final long glGenFencesNV;
    public final long glIsFenceNV;
    public final long glTestFenceNV;
    public final long glGetFenceivNV;
    public final long glFinishFenceNV;
    public final long glSetFenceNV;
    public final long glFragmentCoverageColorNV;
    public final long glCoverageModulationTableNV;
    public final long glGetCoverageModulationTableNV;
    public final long glCoverageModulationNV;
    public final long glRenderbufferStorageMultisampleCoverageNV;
    public final long glRenderGpuMaskNV;
    public final long glMulticastBufferSubDataNV;
    public final long glMulticastCopyBufferSubDataNV;
    public final long glMulticastCopyImageSubDataNV;
    public final long glMulticastBlitFramebufferNV;
    public final long glMulticastFramebufferSampleLocationsfvNV;
    public final long glMulticastBarrierNV;
    public final long glMulticastWaitSyncNV;
    public final long glMulticastGetQueryObjectivNV;
    public final long glMulticastGetQueryObjectuivNV;
    public final long glMulticastGetQueryObjecti64vNV;
    public final long glMulticastGetQueryObjectui64vNV;
    public final long glVertex2hNV;
    public final long glVertex2hvNV;
    public final long glVertex3hNV;
    public final long glVertex3hvNV;
    public final long glVertex4hNV;
    public final long glVertex4hvNV;
    public final long glNormal3hNV;
    public final long glNormal3hvNV;
    public final long glColor3hNV;
    public final long glColor3hvNV;
    public final long glColor4hNV;
    public final long glColor4hvNV;
    public final long glTexCoord1hNV;
    public final long glTexCoord1hvNV;
    public final long glTexCoord2hNV;
    public final long glTexCoord2hvNV;
    public final long glTexCoord3hNV;
    public final long glTexCoord3hvNV;
    public final long glTexCoord4hNV;
    public final long glTexCoord4hvNV;
    public final long glMultiTexCoord1hNV;
    public final long glMultiTexCoord1hvNV;
    public final long glMultiTexCoord2hNV;
    public final long glMultiTexCoord2hvNV;
    public final long glMultiTexCoord3hNV;
    public final long glMultiTexCoord3hvNV;
    public final long glMultiTexCoord4hNV;
    public final long glMultiTexCoord4hvNV;
    public final long glFogCoordhNV;
    public final long glFogCoordhvNV;
    public final long glSecondaryColor3hNV;
    public final long glSecondaryColor3hvNV;
    public final long glVertexWeighthNV;
    public final long glVertexWeighthvNV;
    public final long glVertexAttrib1hNV;
    public final long glVertexAttrib1hvNV;
    public final long glVertexAttrib2hNV;
    public final long glVertexAttrib2hvNV;
    public final long glVertexAttrib3hNV;
    public final long glVertexAttrib3hvNV;
    public final long glVertexAttrib4hNV;
    public final long glVertexAttrib4hvNV;
    public final long glVertexAttribs1hvNV;
    public final long glVertexAttribs2hvNV;
    public final long glVertexAttribs3hvNV;
    public final long glVertexAttribs4hvNV;
    public final long glGetInternalformatSampleivNV;
    public final long glGetMemoryObjectDetachedResourcesuivNV;
    public final long glResetMemoryObjectParameterNV;
    public final long glTexAttachMemoryNV;
    public final long glBufferAttachMemoryNV;
    public final long glTextureAttachMemoryNV;
    public final long glNamedBufferAttachMemoryNV;
    public final long glBufferPageCommitmentMemNV;
    public final long glNamedBufferPageCommitmentMemNV;
    public final long glTexPageCommitmentMemNV;
    public final long glTexturePageCommitmentMemNV;
    public final long glDrawMeshTasksNV;
    public final long glDrawMeshTasksIndirectNV;
    public final long glMultiDrawMeshTasksIndirectNV;
    public final long glMultiDrawMeshTasksIndirectCountNV;
    public final long glPathCommandsNV;
    public final long glPathCoordsNV;
    public final long glPathSubCommandsNV;
    public final long glPathSubCoordsNV;
    public final long glPathStringNV;
    public final long glPathGlyphsNV;
    public final long glPathGlyphRangeNV;
    public final long glPathGlyphIndexArrayNV;
    public final long glPathMemoryGlyphIndexArrayNV;
    public final long glCopyPathNV;
    public final long glWeightPathsNV;
    public final long glInterpolatePathsNV;
    public final long glTransformPathNV;
    public final long glPathParameterivNV;
    public final long glPathParameteriNV;
    public final long glPathParameterfvNV;
    public final long glPathParameterfNV;
    public final long glPathDashArrayNV;
    public final long glGenPathsNV;
    public final long glDeletePathsNV;
    public final long glIsPathNV;
    public final long glPathStencilFuncNV;
    public final long glPathStencilDepthOffsetNV;
    public final long glStencilFillPathNV;
    public final long glStencilStrokePathNV;
    public final long glStencilFillPathInstancedNV;
    public final long glStencilStrokePathInstancedNV;
    public final long glPathCoverDepthFuncNV;
    public final long glPathColorGenNV;
    public final long glPathTexGenNV;
    public final long glPathFogGenNV;
    public final long glCoverFillPathNV;
    public final long glCoverStrokePathNV;
    public final long glCoverFillPathInstancedNV;
    public final long glCoverStrokePathInstancedNV;
    public final long glStencilThenCoverFillPathNV;
    public final long glStencilThenCoverStrokePathNV;
    public final long glStencilThenCoverFillPathInstancedNV;
    public final long glStencilThenCoverStrokePathInstancedNV;
    public final long glPathGlyphIndexRangeNV;
    public final long glProgramPathFragmentInputGenNV;
    public final long glGetPathParameterivNV;
    public final long glGetPathParameterfvNV;
    public final long glGetPathCommandsNV;
    public final long glGetPathCoordsNV;
    public final long glGetPathDashArrayNV;
    public final long glGetPathMetricsNV;
    public final long glGetPathMetricRangeNV;
    public final long glGetPathSpacingNV;
    public final long glGetPathColorGenivNV;
    public final long glGetPathColorGenfvNV;
    public final long glGetPathTexGenivNV;
    public final long glGetPathTexGenfvNV;
    public final long glIsPointInFillPathNV;
    public final long glIsPointInStrokePathNV;
    public final long glGetPathLengthNV;
    public final long glPointAlongPathNV;
    public final long glMatrixLoad3x2fNV;
    public final long glMatrixLoad3x3fNV;
    public final long glMatrixLoadTranspose3x3fNV;
    public final long glMatrixMult3x2fNV;
    public final long glMatrixMult3x3fNV;
    public final long glMatrixMultTranspose3x3fNV;
    public final long glGetProgramResourcefvNV;
    public final long glPixelDataRangeNV;
    public final long glFlushPixelDataRangeNV;
    public final long glPointParameteriNV;
    public final long glPointParameterivNV;
    public final long glPrimitiveRestartNV;
    public final long glPrimitiveRestartIndexNV;
    public final long glQueryResourceNV;
    public final long glGenQueryResourceTagNV;
    public final long glDeleteQueryResourceTagNV;
    public final long glQueryResourceTagNV;
    public final long glFramebufferSampleLocationsfvNV;
    public final long glNamedFramebufferSampleLocationsfvNV;
    public final long glResolveDepthValuesNV;
    public final long glScissorExclusiveArrayvNV;
    public final long glScissorExclusiveNV;
    public final long glMakeBufferResidentNV;
    public final long glMakeBufferNonResidentNV;
    public final long glIsBufferResidentNV;
    public final long glMakeNamedBufferResidentNV;
    public final long glMakeNamedBufferNonResidentNV;
    public final long glIsNamedBufferResidentNV;
    public final long glGetBufferParameterui64vNV;
    public final long glGetNamedBufferParameterui64vNV;
    public final long glGetIntegerui64vNV;
    public final long glUniformui64NV;
    public final long glUniformui64vNV;
    public final long glProgramUniformui64NV;
    public final long glProgramUniformui64vNV;
    public final long glBindShadingRateImageNV;
    public final long glShadingRateImagePaletteNV;
    public final long glGetShadingRateImagePaletteNV;
    public final long glShadingRateImageBarrierNV;
    public final long glShadingRateSampleOrderNV;
    public final long glShadingRateSampleOrderCustomNV;
    public final long glGetShadingRateSampleLocationivNV;
    public final long glTextureBarrierNV;
    public final long glTexImage2DMultisampleCoverageNV;
    public final long glTexImage3DMultisampleCoverageNV;
    public final long glTextureImage2DMultisampleNV;
    public final long glTextureImage3DMultisampleNV;
    public final long glTextureImage2DMultisampleCoverageNV;
    public final long glTextureImage3DMultisampleCoverageNV;
    public final long glCreateSemaphoresNV;
    public final long glSemaphoreParameterivNV;
    public final long glGetSemaphoreParameterivNV;
    public final long glBeginTransformFeedbackNV;
    public final long glEndTransformFeedbackNV;
    public final long glTransformFeedbackAttribsNV;
    public final long glBindBufferRangeNV;
    public final long glBindBufferOffsetNV;
    public final long glBindBufferBaseNV;
    public final long glTransformFeedbackVaryingsNV;
    public final long glActiveVaryingNV;
    public final long glGetVaryingLocationNV;
    public final long glGetActiveVaryingNV;
    public final long glGetTransformFeedbackVaryingNV;
    public final long glTransformFeedbackStreamAttribsNV;
    public final long glBindTransformFeedbackNV;
    public final long glDeleteTransformFeedbacksNV;
    public final long glGenTransformFeedbacksNV;
    public final long glIsTransformFeedbackNV;
    public final long glPauseTransformFeedbackNV;
    public final long glResumeTransformFeedbackNV;
    public final long glDrawTransformFeedbackNV;
    public final long glVertexArrayRangeNV;
    public final long glFlushVertexArrayRangeNV;
    public final long glVertexAttribL1i64NV;
    public final long glVertexAttribL2i64NV;
    public final long glVertexAttribL3i64NV;
    public final long glVertexAttribL4i64NV;
    public final long glVertexAttribL1i64vNV;
    public final long glVertexAttribL2i64vNV;
    public final long glVertexAttribL3i64vNV;
    public final long glVertexAttribL4i64vNV;
    public final long glVertexAttribL1ui64NV;
    public final long glVertexAttribL2ui64NV;
    public final long glVertexAttribL3ui64NV;
    public final long glVertexAttribL4ui64NV;
    public final long glVertexAttribL1ui64vNV;
    public final long glVertexAttribL2ui64vNV;
    public final long glVertexAttribL3ui64vNV;
    public final long glVertexAttribL4ui64vNV;
    public final long glGetVertexAttribLi64vNV;
    public final long glGetVertexAttribLui64vNV;
    public final long glVertexAttribLFormatNV;
    public final long glBufferAddressRangeNV;
    public final long glVertexFormatNV;
    public final long glNormalFormatNV;
    public final long glColorFormatNV;
    public final long glIndexFormatNV;
    public final long glTexCoordFormatNV;
    public final long glEdgeFlagFormatNV;
    public final long glSecondaryColorFormatNV;
    public final long glFogCoordFormatNV;
    public final long glVertexAttribFormatNV;
    public final long glVertexAttribIFormatNV;
    public final long glGetIntegerui64i_vNV;
    public final long glViewportSwizzleNV;
    public final long glBeginConditionalRenderNVX;
    public final long glEndConditionalRenderNVX;
    public final long glAsyncCopyImageSubDataNVX;
    public final long glAsyncCopyBufferSubDataNVX;
    public final long glUploadGpuMaskNVX;
    public final long glMulticastViewportArrayvNVX;
    public final long glMulticastScissorArrayvNVX;
    public final long glMulticastViewportPositionWScaleNVX;
    public final long glCreateProgressFenceNVX;
    public final long glSignalSemaphoreui64NVX;
    public final long glWaitSemaphoreui64NVX;
    public final long glClientWaitSemaphoreui64NVX;
    public final long glFramebufferTextureMultiviewOVR;
    public final long glNamedFramebufferTextureMultiviewOVR;
    public final boolean OpenGL11;
    public final boolean OpenGL12;
    public final boolean OpenGL13;
    public final boolean OpenGL14;
    public final boolean OpenGL15;
    public final boolean OpenGL20;
    public final boolean OpenGL21;
    public final boolean OpenGL30;
    public final boolean OpenGL31;
    public final boolean OpenGL32;
    public final boolean OpenGL33;
    public final boolean OpenGL40;
    public final boolean OpenGL41;
    public final boolean OpenGL42;
    public final boolean OpenGL43;
    public final boolean OpenGL44;
    public final boolean OpenGL45;
    public final boolean OpenGL46;
    public final boolean GL_3DFX_texture_compression_FXT1;
    public final boolean GL_AMD_blend_minmax_factor;
    public final boolean GL_AMD_conservative_depth;
    public final boolean GL_AMD_debug_output;
    public final boolean GL_AMD_depth_clamp_separate;
    public final boolean GL_AMD_draw_buffers_blend;
    public final boolean GL_AMD_framebuffer_multisample_advanced;
    public final boolean GL_AMD_gcn_shader;
    public final boolean GL_AMD_gpu_shader_half_float;
    public final boolean GL_AMD_gpu_shader_half_float_fetch;
    public final boolean GL_AMD_gpu_shader_int16;
    public final boolean GL_AMD_gpu_shader_int64;
    public final boolean GL_AMD_interleaved_elements;
    public final boolean GL_AMD_occlusion_query_event;
    public final boolean GL_AMD_performance_monitor;
    public final boolean GL_AMD_pinned_memory;
    public final boolean GL_AMD_query_buffer_object;
    public final boolean GL_AMD_sample_positions;
    public final boolean GL_AMD_seamless_cubemap_per_texture;
    public final boolean GL_AMD_shader_atomic_counter_ops;
    public final boolean GL_AMD_shader_ballot;
    public final boolean GL_AMD_shader_explicit_vertex_parameter;
    public final boolean GL_AMD_shader_image_load_store_lod;
    public final boolean GL_AMD_shader_stencil_export;
    public final boolean GL_AMD_shader_trinary_minmax;
    public final boolean GL_AMD_sparse_texture;
    public final boolean GL_AMD_stencil_operation_extended;
    public final boolean GL_AMD_texture_gather_bias_lod;
    public final boolean GL_AMD_texture_texture4;
    public final boolean GL_AMD_transform_feedback3_lines_triangles;
    public final boolean GL_AMD_transform_feedback4;
    public final boolean GL_AMD_vertex_shader_layer;
    public final boolean GL_AMD_vertex_shader_tessellator;
    public final boolean GL_AMD_vertex_shader_viewport_index;
    public final boolean GL_ARB_arrays_of_arrays;
    public final boolean GL_ARB_base_instance;
    public final boolean GL_ARB_bindless_texture;
    public final boolean GL_ARB_blend_func_extended;
    public final boolean GL_ARB_buffer_storage;
    public final boolean GL_ARB_cl_event;
    public final boolean GL_ARB_clear_buffer_object;
    public final boolean GL_ARB_clear_texture;
    public final boolean GL_ARB_clip_control;
    public final boolean GL_ARB_color_buffer_float;
    public final boolean GL_ARB_compatibility;
    public final boolean GL_ARB_compressed_texture_pixel_storage;
    public final boolean GL_ARB_compute_shader;
    public final boolean GL_ARB_compute_variable_group_size;
    public final boolean GL_ARB_conditional_render_inverted;
    public final boolean GL_ARB_conservative_depth;
    public final boolean GL_ARB_copy_buffer;
    public final boolean GL_ARB_copy_image;
    public final boolean GL_ARB_cull_distance;
    public final boolean GL_ARB_debug_output;
    public final boolean GL_ARB_depth_buffer_float;
    public final boolean GL_ARB_depth_clamp;
    public final boolean GL_ARB_depth_texture;
    public final boolean GL_ARB_derivative_control;
    public final boolean GL_ARB_direct_state_access;
    public final boolean GL_ARB_draw_buffers;
    public final boolean GL_ARB_draw_buffers_blend;
    public final boolean GL_ARB_draw_elements_base_vertex;
    public final boolean GL_ARB_draw_indirect;
    public final boolean GL_ARB_draw_instanced;
    public final boolean GL_ARB_enhanced_layouts;
    public final boolean GL_ARB_ES2_compatibility;
    public final boolean GL_ARB_ES3_1_compatibility;
    public final boolean GL_ARB_ES3_2_compatibility;
    public final boolean GL_ARB_ES3_compatibility;
    public final boolean GL_ARB_explicit_attrib_location;
    public final boolean GL_ARB_explicit_uniform_location;
    public final boolean GL_ARB_fragment_coord_conventions;
    public final boolean GL_ARB_fragment_layer_viewport;
    public final boolean GL_ARB_fragment_program;
    public final boolean GL_ARB_fragment_program_shadow;
    public final boolean GL_ARB_fragment_shader;
    public final boolean GL_ARB_fragment_shader_interlock;
    public final boolean GL_ARB_framebuffer_no_attachments;
    public final boolean GL_ARB_framebuffer_object;
    public final boolean GL_ARB_framebuffer_sRGB;
    public final boolean GL_ARB_geometry_shader4;
    public final boolean GL_ARB_get_program_binary;
    public final boolean GL_ARB_get_texture_sub_image;
    public final boolean GL_ARB_gl_spirv;
    public final boolean GL_ARB_gpu_shader5;
    public final boolean GL_ARB_gpu_shader_fp64;
    public final boolean GL_ARB_gpu_shader_int64;
    public final boolean GL_ARB_half_float_pixel;
    public final boolean GL_ARB_half_float_vertex;
    public final boolean GL_ARB_imaging;
    public final boolean GL_ARB_indirect_parameters;
    public final boolean GL_ARB_instanced_arrays;
    public final boolean GL_ARB_internalformat_query;
    public final boolean GL_ARB_internalformat_query2;
    public final boolean GL_ARB_invalidate_subdata;
    public final boolean GL_ARB_map_buffer_alignment;
    public final boolean GL_ARB_map_buffer_range;
    public final boolean GL_ARB_matrix_palette;
    public final boolean GL_ARB_multi_bind;
    public final boolean GL_ARB_multi_draw_indirect;
    public final boolean GL_ARB_multisample;
    public final boolean GL_ARB_multitexture;
    public final boolean GL_ARB_occlusion_query;
    public final boolean GL_ARB_occlusion_query2;
    public final boolean GL_ARB_parallel_shader_compile;
    public final boolean GL_ARB_pipeline_statistics_query;
    public final boolean GL_ARB_pixel_buffer_object;
    public final boolean GL_ARB_point_parameters;
    public final boolean GL_ARB_point_sprite;
    public final boolean GL_ARB_polygon_offset_clamp;
    public final boolean GL_ARB_post_depth_coverage;
    public final boolean GL_ARB_program_interface_query;
    public final boolean GL_ARB_provoking_vertex;
    public final boolean GL_ARB_query_buffer_object;
    public final boolean GL_ARB_robust_buffer_access_behavior;
    public final boolean GL_ARB_robustness;
    public final boolean GL_ARB_robustness_application_isolation;
    public final boolean GL_ARB_robustness_share_group_isolation;
    public final boolean GL_ARB_sample_locations;
    public final boolean GL_ARB_sample_shading;
    public final boolean GL_ARB_sampler_objects;
    public final boolean GL_ARB_seamless_cube_map;
    public final boolean GL_ARB_seamless_cubemap_per_texture;
    public final boolean GL_ARB_separate_shader_objects;
    public final boolean GL_ARB_shader_atomic_counter_ops;
    public final boolean GL_ARB_shader_atomic_counters;
    public final boolean GL_ARB_shader_ballot;
    public final boolean GL_ARB_shader_bit_encoding;
    public final boolean GL_ARB_shader_clock;
    public final boolean GL_ARB_shader_draw_parameters;
    public final boolean GL_ARB_shader_group_vote;
    public final boolean GL_ARB_shader_image_load_store;
    public final boolean GL_ARB_shader_image_size;
    public final boolean GL_ARB_shader_objects;
    public final boolean GL_ARB_shader_precision;
    public final boolean GL_ARB_shader_stencil_export;
    public final boolean GL_ARB_shader_storage_buffer_object;
    public final boolean GL_ARB_shader_subroutine;
    public final boolean GL_ARB_shader_texture_image_samples;
    public final boolean GL_ARB_shader_texture_lod;
    public final boolean GL_ARB_shader_viewport_layer_array;
    public final boolean GL_ARB_shading_language_100;
    public final boolean GL_ARB_shading_language_420pack;
    public final boolean GL_ARB_shading_language_include;
    public final boolean GL_ARB_shading_language_packing;
    public final boolean GL_ARB_shadow;
    public final boolean GL_ARB_shadow_ambient;
    public final boolean GL_ARB_sparse_buffer;
    public final boolean GL_ARB_sparse_texture;
    public final boolean GL_ARB_sparse_texture2;
    public final boolean GL_ARB_sparse_texture_clamp;
    public final boolean GL_ARB_spirv_extensions;
    public final boolean GL_ARB_stencil_texturing;
    public final boolean GL_ARB_sync;
    public final boolean GL_ARB_tessellation_shader;
    public final boolean GL_ARB_texture_barrier;
    public final boolean GL_ARB_texture_border_clamp;
    public final boolean GL_ARB_texture_buffer_object;
    public final boolean GL_ARB_texture_buffer_object_rgb32;
    public final boolean GL_ARB_texture_buffer_range;
    public final boolean GL_ARB_texture_compression;
    public final boolean GL_ARB_texture_compression_bptc;
    public final boolean GL_ARB_texture_compression_rgtc;
    public final boolean GL_ARB_texture_cube_map;
    public final boolean GL_ARB_texture_cube_map_array;
    public final boolean GL_ARB_texture_env_add;
    public final boolean GL_ARB_texture_env_combine;
    public final boolean GL_ARB_texture_env_crossbar;
    public final boolean GL_ARB_texture_env_dot3;
    public final boolean GL_ARB_texture_filter_anisotropic;
    public final boolean GL_ARB_texture_filter_minmax;
    public final boolean GL_ARB_texture_float;
    public final boolean GL_ARB_texture_gather;
    public final boolean GL_ARB_texture_mirror_clamp_to_edge;
    public final boolean GL_ARB_texture_mirrored_repeat;
    public final boolean GL_ARB_texture_multisample;
    public final boolean GL_ARB_texture_non_power_of_two;
    public final boolean GL_ARB_texture_query_levels;
    public final boolean GL_ARB_texture_query_lod;
    public final boolean GL_ARB_texture_rectangle;
    public final boolean GL_ARB_texture_rg;
    public final boolean GL_ARB_texture_rgb10_a2ui;
    public final boolean GL_ARB_texture_stencil8;
    public final boolean GL_ARB_texture_storage;
    public final boolean GL_ARB_texture_storage_multisample;
    public final boolean GL_ARB_texture_swizzle;
    public final boolean GL_ARB_texture_view;
    public final boolean GL_ARB_timer_query;
    public final boolean GL_ARB_transform_feedback2;
    public final boolean GL_ARB_transform_feedback3;
    public final boolean GL_ARB_transform_feedback_instanced;
    public final boolean GL_ARB_transform_feedback_overflow_query;
    public final boolean GL_ARB_transpose_matrix;
    public final boolean GL_ARB_uniform_buffer_object;
    public final boolean GL_ARB_vertex_array_bgra;
    public final boolean GL_ARB_vertex_array_object;
    public final boolean GL_ARB_vertex_attrib_64bit;
    public final boolean GL_ARB_vertex_attrib_binding;
    public final boolean GL_ARB_vertex_blend;
    public final boolean GL_ARB_vertex_buffer_object;
    public final boolean GL_ARB_vertex_program;
    public final boolean GL_ARB_vertex_shader;
    public final boolean GL_ARB_vertex_type_10f_11f_11f_rev;
    public final boolean GL_ARB_vertex_type_2_10_10_10_rev;
    public final boolean GL_ARB_viewport_array;
    public final boolean GL_ARB_window_pos;
    public final boolean GL_ATI_meminfo;
    public final boolean GL_ATI_shader_texture_lod;
    public final boolean GL_ATI_texture_compression_3dc;
    public final boolean GL_EXT_422_pixels;
    public final boolean GL_EXT_abgr;
    public final boolean GL_EXT_bgra;
    public final boolean GL_EXT_bindable_uniform;
    public final boolean GL_EXT_blend_color;
    public final boolean GL_EXT_blend_equation_separate;
    public final boolean GL_EXT_blend_func_separate;
    public final boolean GL_EXT_blend_minmax;
    public final boolean GL_EXT_blend_subtract;
    public final boolean GL_EXT_clip_volume_hint;
    public final boolean GL_EXT_compiled_vertex_array;
    public final boolean GL_EXT_debug_label;
    public final boolean GL_EXT_debug_marker;
    public final boolean GL_EXT_depth_bounds_test;
    public final boolean GL_EXT_direct_state_access;
    public final boolean GL_EXT_draw_buffers2;
    public final boolean GL_EXT_draw_instanced;
    public final boolean GL_EXT_EGL_image_storage;
    public final boolean GL_EXT_EGL_sync;
    public final boolean GL_EXT_external_buffer;
    public final boolean GL_EXT_framebuffer_blit;
    public final boolean GL_EXT_framebuffer_blit_layers;
    public final boolean GL_EXT_framebuffer_multisample;
    public final boolean GL_EXT_framebuffer_multisample_blit_scaled;
    public final boolean GL_EXT_framebuffer_object;
    public final boolean GL_EXT_framebuffer_sRGB;
    public final boolean GL_EXT_geometry_shader4;
    public final boolean GL_EXT_gpu_program_parameters;
    public final boolean GL_EXT_gpu_shader4;
    public final boolean GL_EXT_memory_object;
    public final boolean GL_EXT_memory_object_fd;
    public final boolean GL_EXT_memory_object_win32;
    public final boolean GL_EXT_multiview_tessellation_geometry_shader;
    public final boolean GL_EXT_multiview_texture_multisample;
    public final boolean GL_EXT_multiview_timer_query;
    public final boolean GL_EXT_packed_depth_stencil;
    public final boolean GL_EXT_packed_float;
    public final boolean GL_EXT_pixel_buffer_object;
    public final boolean GL_EXT_point_parameters;
    public final boolean GL_EXT_polygon_offset_clamp;
    public final boolean GL_EXT_post_depth_coverage;
    public final boolean GL_EXT_provoking_vertex;
    public final boolean GL_EXT_raster_multisample;
    public final boolean GL_EXT_secondary_color;
    public final boolean GL_EXT_semaphore;
    public final boolean GL_EXT_semaphore_fd;
    public final boolean GL_EXT_semaphore_win32;
    public final boolean GL_EXT_separate_shader_objects;
    public final boolean GL_EXT_shader_framebuffer_fetch;
    public final boolean GL_EXT_shader_framebuffer_fetch_non_coherent;
    public final boolean GL_EXT_shader_image_load_formatted;
    public final boolean GL_EXT_shader_image_load_store;
    public final boolean GL_EXT_shader_integer_mix;
    public final boolean GL_EXT_shader_samples_identical;
    public final boolean GL_EXT_shadow_funcs;
    public final boolean GL_EXT_shared_texture_palette;
    public final boolean GL_EXT_sparse_texture2;
    public final boolean GL_EXT_stencil_clear_tag;
    public final boolean GL_EXT_stencil_two_side;
    public final boolean GL_EXT_stencil_wrap;
    public final boolean GL_EXT_texture_array;
    public final boolean GL_EXT_texture_buffer_object;
    public final boolean GL_EXT_texture_compression_latc;
    public final boolean GL_EXT_texture_compression_rgtc;
    public final boolean GL_EXT_texture_compression_s3tc;
    public final boolean GL_EXT_texture_filter_anisotropic;
    public final boolean GL_EXT_texture_filter_minmax;
    public final boolean GL_EXT_texture_integer;
    public final boolean GL_EXT_texture_mirror_clamp;
    public final boolean GL_EXT_texture_shadow_lod;
    public final boolean GL_EXT_texture_shared_exponent;
    public final boolean GL_EXT_texture_snorm;
    public final boolean GL_EXT_texture_sRGB;
    public final boolean GL_EXT_texture_sRGB_decode;
    public final boolean GL_EXT_texture_sRGB_R8;
    public final boolean GL_EXT_texture_sRGB_RG8;
    public final boolean GL_EXT_texture_storage;
    public final boolean GL_EXT_texture_swizzle;
    public final boolean GL_EXT_timer_query;
    public final boolean GL_EXT_transform_feedback;
    public final boolean GL_EXT_vertex_array_bgra;
    public final boolean GL_EXT_vertex_attrib_64bit;
    public final boolean GL_EXT_win32_keyed_mutex;
    public final boolean GL_EXT_window_rectangles;
    public final boolean GL_EXT_x11_sync_object;
    public final boolean GL_GREMEDY_frame_terminator;
    public final boolean GL_GREMEDY_string_marker;
    public final boolean GL_INTEL_blackhole_render;
    public final boolean GL_INTEL_conservative_rasterization;
    public final boolean GL_INTEL_fragment_shader_ordering;
    public final boolean GL_INTEL_framebuffer_CMAA;
    public final boolean GL_INTEL_map_texture;
    public final boolean GL_INTEL_performance_query;
    public final boolean GL_INTEL_shader_integer_functions2;
    public final boolean GL_KHR_blend_equation_advanced;
    public final boolean GL_KHR_blend_equation_advanced_coherent;
    public final boolean GL_KHR_context_flush_control;
    public final boolean GL_KHR_debug;
    public final boolean GL_KHR_no_error;
    public final boolean GL_KHR_parallel_shader_compile;
    public final boolean GL_KHR_robust_buffer_access_behavior;
    public final boolean GL_KHR_robustness;
    public final boolean GL_KHR_shader_subgroup;
    public final boolean GL_KHR_texture_compression_astc_hdr;
    public final boolean GL_KHR_texture_compression_astc_ldr;
    public final boolean GL_KHR_texture_compression_astc_sliced_3d;
    public final boolean GL_MESA_framebuffer_flip_x;
    public final boolean GL_MESA_framebuffer_flip_y;
    public final boolean GL_MESA_framebuffer_swap_xy;
    public final boolean GL_MESA_tile_raster_order;
    public final boolean GL_NV_alpha_to_coverage_dither_control;
    public final boolean GL_NV_bindless_multi_draw_indirect;
    public final boolean GL_NV_bindless_multi_draw_indirect_count;
    public final boolean GL_NV_bindless_texture;
    public final boolean GL_NV_blend_equation_advanced;
    public final boolean GL_NV_blend_equation_advanced_coherent;
    public final boolean GL_NV_blend_minmax_factor;
    public final boolean GL_NV_blend_square;
    public final boolean GL_NV_clip_space_w_scaling;
    public final boolean GL_NV_command_list;
    public final boolean GL_NV_compute_shader_derivatives;
    public final boolean GL_NV_conditional_render;
    public final boolean GL_NV_conservative_raster;
    public final boolean GL_NV_conservative_raster_dilate;
    public final boolean GL_NV_conservative_raster_pre_snap;
    public final boolean GL_NV_conservative_raster_pre_snap_triangles;
    public final boolean GL_NV_conservative_raster_underestimation;
    public final boolean GL_NV_copy_depth_to_color;
    public final boolean GL_NV_copy_image;
    public final boolean GL_NV_deep_texture3D;
    public final boolean GL_NV_depth_buffer_float;
    public final boolean GL_NV_depth_clamp;
    public final boolean GL_NV_draw_texture;
    public final boolean GL_NV_draw_vulkan_image;
    public final boolean GL_NV_ES3_1_compatibility;
    public final boolean GL_NV_explicit_multisample;
    public final boolean GL_NV_fence;
    public final boolean GL_NV_fill_rectangle;
    public final boolean GL_NV_float_buffer;
    public final boolean GL_NV_fog_distance;
    public final boolean GL_NV_fragment_coverage_to_color;
    public final boolean GL_NV_fragment_program4;
    public final boolean GL_NV_fragment_program_option;
    public final boolean GL_NV_fragment_shader_barycentric;
    public final boolean GL_NV_fragment_shader_interlock;
    public final boolean GL_NV_framebuffer_mixed_samples;
    public final boolean GL_NV_framebuffer_multisample_coverage;
    public final boolean GL_NV_geometry_shader4;
    public final boolean GL_NV_geometry_shader_passthrough;
    public final boolean GL_NV_gpu_multicast;
    public final boolean GL_NV_gpu_shader5;
    public final boolean GL_NV_half_float;
    public final boolean GL_NV_internalformat_sample_query;
    public final boolean GL_NV_light_max_exponent;
    public final boolean GL_NV_memory_attachment;
    public final boolean GL_NV_memory_object_sparse;
    public final boolean GL_NV_mesh_shader;
    public final boolean GL_NV_multisample_coverage;
    public final boolean GL_NV_multisample_filter_hint;
    public final boolean GL_NV_packed_depth_stencil;
    public final boolean GL_NV_path_rendering;
    public final boolean GL_NV_path_rendering_shared_edge;
    public final boolean GL_NV_pixel_data_range;
    public final boolean GL_NV_point_sprite;
    public final boolean GL_NV_primitive_restart;
    public final boolean GL_NV_primitive_shading_rate;
    public final boolean GL_NV_query_resource;
    public final boolean GL_NV_query_resource_tag;
    public final boolean GL_NV_representative_fragment_test;
    public final boolean GL_NV_robustness_video_memory_purge;
    public final boolean GL_NV_sample_locations;
    public final boolean GL_NV_sample_mask_override_coverage;
    public final boolean GL_NV_scissor_exclusive;
    public final boolean GL_NV_shader_atomic_float;
    public final boolean GL_NV_shader_atomic_float64;
    public final boolean GL_NV_shader_atomic_fp16_vector;
    public final boolean GL_NV_shader_atomic_int64;
    public final boolean GL_NV_shader_buffer_load;
    public final boolean GL_NV_shader_buffer_store;
    public final boolean GL_NV_shader_subgroup_partitioned;
    public final boolean GL_NV_shader_texture_footprint;
    public final boolean GL_NV_shader_thread_group;
    public final boolean GL_NV_shader_thread_shuffle;
    public final boolean GL_NV_shading_rate_image;
    public final boolean GL_NV_stereo_view_rendering;
    public final boolean GL_NV_texgen_reflection;
    public final boolean GL_NV_texture_barrier;
    public final boolean GL_NV_texture_compression_vtc;
    public final boolean GL_NV_texture_multisample;
    public final boolean GL_NV_texture_rectangle_compressed;
    public final boolean GL_NV_texture_shader;
    public final boolean GL_NV_texture_shader2;
    public final boolean GL_NV_texture_shader3;
    public final boolean GL_NV_timeline_semaphore;
    public final boolean GL_NV_transform_feedback;
    public final boolean GL_NV_transform_feedback2;
    public final boolean GL_NV_uniform_buffer_std430_layout;
    public final boolean GL_NV_uniform_buffer_unified_memory;
    public final boolean GL_NV_vertex_array_range;
    public final boolean GL_NV_vertex_array_range2;
    public final boolean GL_NV_vertex_attrib_integer_64bit;
    public final boolean GL_NV_vertex_buffer_unified_memory;
    public final boolean GL_NV_viewport_array2;
    public final boolean GL_NV_viewport_swizzle;
    public final boolean GL_NVX_blend_equation_advanced_multi_draw_buffers;
    public final boolean GL_NVX_conditional_render;
    public final boolean GL_NVX_gpu_memory_info;
    public final boolean GL_NVX_gpu_multicast2;
    public final boolean GL_NVX_progress_fence;
    public final boolean GL_OVR_multiview;
    public final boolean GL_OVR_multiview2;
    public final boolean GL_S3_s3tc;
    public final boolean forwardCompatible;
    final PointerBuffer addresses;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GLCapabilities(FunctionProvider functionProvider, Set<String> set, boolean z, IntFunction<PointerBuffer> intFunction) {
        this.forwardCompatible = z;
        PointerBuffer apply = intFunction.apply(ADDRESS_BUFFER_SIZE);
        this.OpenGL11 = check_GL11(functionProvider, apply, set, z);
        this.OpenGL12 = check_GL12(functionProvider, apply, set);
        this.OpenGL13 = check_GL13(functionProvider, apply, set, z);
        this.OpenGL14 = check_GL14(functionProvider, apply, set, z);
        this.OpenGL15 = check_GL15(functionProvider, apply, set);
        this.OpenGL20 = check_GL20(functionProvider, apply, set);
        this.OpenGL21 = check_GL21(functionProvider, apply, set);
        this.OpenGL30 = check_GL30(functionProvider, apply, set);
        this.OpenGL31 = check_GL31(functionProvider, apply, set);
        this.OpenGL32 = check_GL32(functionProvider, apply, set);
        this.OpenGL33 = check_GL33(functionProvider, apply, set, z);
        this.OpenGL40 = check_GL40(functionProvider, apply, set);
        this.OpenGL41 = check_GL41(functionProvider, apply, set);
        this.OpenGL42 = check_GL42(functionProvider, apply, set);
        this.OpenGL43 = check_GL43(functionProvider, apply, set);
        this.OpenGL44 = check_GL44(functionProvider, apply, set);
        this.OpenGL45 = check_GL45(functionProvider, apply, set);
        this.OpenGL46 = check_GL46(functionProvider, apply, set);
        this.GL_3DFX_texture_compression_FXT1 = set.contains("GL_3DFX_texture_compression_FXT1");
        this.GL_AMD_blend_minmax_factor = set.contains("GL_AMD_blend_minmax_factor");
        this.GL_AMD_conservative_depth = set.contains("GL_AMD_conservative_depth");
        this.GL_AMD_debug_output = check_AMD_debug_output(functionProvider, apply, set);
        this.GL_AMD_depth_clamp_separate = set.contains("GL_AMD_depth_clamp_separate");
        this.GL_AMD_draw_buffers_blend = check_AMD_draw_buffers_blend(functionProvider, apply, set);
        this.GL_AMD_framebuffer_multisample_advanced = check_AMD_framebuffer_multisample_advanced(functionProvider, apply, set);
        this.GL_AMD_gcn_shader = set.contains("GL_AMD_gcn_shader");
        this.GL_AMD_gpu_shader_half_float = set.contains("GL_AMD_gpu_shader_half_float");
        this.GL_AMD_gpu_shader_half_float_fetch = set.contains("GL_AMD_gpu_shader_half_float_fetch");
        this.GL_AMD_gpu_shader_int16 = set.contains("GL_AMD_gpu_shader_int16");
        this.GL_AMD_gpu_shader_int64 = check_AMD_gpu_shader_int64(functionProvider, apply, set);
        this.GL_AMD_interleaved_elements = check_AMD_interleaved_elements(functionProvider, apply, set);
        this.GL_AMD_occlusion_query_event = check_AMD_occlusion_query_event(functionProvider, apply, set);
        this.GL_AMD_performance_monitor = check_AMD_performance_monitor(functionProvider, apply, set);
        this.GL_AMD_pinned_memory = set.contains("GL_AMD_pinned_memory");
        this.GL_AMD_query_buffer_object = set.contains("GL_AMD_query_buffer_object");
        this.GL_AMD_sample_positions = check_AMD_sample_positions(functionProvider, apply, set);
        this.GL_AMD_seamless_cubemap_per_texture = set.contains("GL_AMD_seamless_cubemap_per_texture");
        this.GL_AMD_shader_atomic_counter_ops = set.contains("GL_AMD_shader_atomic_counter_ops");
        this.GL_AMD_shader_ballot = set.contains("GL_AMD_shader_ballot");
        this.GL_AMD_shader_explicit_vertex_parameter = set.contains("GL_AMD_shader_explicit_vertex_parameter");
        this.GL_AMD_shader_image_load_store_lod = set.contains("GL_AMD_shader_image_load_store_lod");
        this.GL_AMD_shader_stencil_export = set.contains("GL_AMD_shader_stencil_export");
        this.GL_AMD_shader_trinary_minmax = set.contains("GL_AMD_shader_trinary_minmax");
        this.GL_AMD_sparse_texture = check_AMD_sparse_texture(functionProvider, apply, set);
        this.GL_AMD_stencil_operation_extended = check_AMD_stencil_operation_extended(functionProvider, apply, set);
        this.GL_AMD_texture_gather_bias_lod = set.contains("GL_AMD_texture_gather_bias_lod");
        this.GL_AMD_texture_texture4 = set.contains("GL_AMD_texture_texture4");
        this.GL_AMD_transform_feedback3_lines_triangles = set.contains("GL_AMD_transform_feedback3_lines_triangles");
        this.GL_AMD_transform_feedback4 = set.contains("GL_AMD_transform_feedback4");
        this.GL_AMD_vertex_shader_layer = set.contains("GL_AMD_vertex_shader_layer");
        this.GL_AMD_vertex_shader_tessellator = check_AMD_vertex_shader_tessellator(functionProvider, apply, set);
        this.GL_AMD_vertex_shader_viewport_index = set.contains("GL_AMD_vertex_shader_viewport_index");
        this.GL_ARB_arrays_of_arrays = set.contains("GL_ARB_arrays_of_arrays");
        this.GL_ARB_base_instance = check_ARB_base_instance(functionProvider, apply, set);
        this.GL_ARB_bindless_texture = check_ARB_bindless_texture(functionProvider, apply, set);
        this.GL_ARB_blend_func_extended = check_ARB_blend_func_extended(functionProvider, apply, set);
        this.GL_ARB_buffer_storage = check_ARB_buffer_storage(functionProvider, apply, set);
        this.GL_ARB_cl_event = check_ARB_cl_event(functionProvider, apply, set);
        this.GL_ARB_clear_buffer_object = check_ARB_clear_buffer_object(functionProvider, apply, set);
        this.GL_ARB_clear_texture = check_ARB_clear_texture(functionProvider, apply, set);
        this.GL_ARB_clip_control = check_ARB_clip_control(functionProvider, apply, set);
        this.GL_ARB_color_buffer_float = check_ARB_color_buffer_float(functionProvider, apply, set);
        this.GL_ARB_compatibility = set.contains("GL_ARB_compatibility");
        this.GL_ARB_compressed_texture_pixel_storage = set.contains("GL_ARB_compressed_texture_pixel_storage");
        this.GL_ARB_compute_shader = check_ARB_compute_shader(functionProvider, apply, set);
        this.GL_ARB_compute_variable_group_size = check_ARB_compute_variable_group_size(functionProvider, apply, set);
        this.GL_ARB_conditional_render_inverted = set.contains("GL_ARB_conditional_render_inverted");
        this.GL_ARB_conservative_depth = set.contains("GL_ARB_conservative_depth");
        this.GL_ARB_copy_buffer = check_ARB_copy_buffer(functionProvider, apply, set);
        this.GL_ARB_copy_image = check_ARB_copy_image(functionProvider, apply, set);
        this.GL_ARB_cull_distance = set.contains("GL_ARB_cull_distance");
        this.GL_ARB_debug_output = check_ARB_debug_output(functionProvider, apply, set);
        this.GL_ARB_depth_buffer_float = set.contains("GL_ARB_depth_buffer_float");
        this.GL_ARB_depth_clamp = set.contains("GL_ARB_depth_clamp");
        this.GL_ARB_depth_texture = set.contains("GL_ARB_depth_texture");
        this.GL_ARB_derivative_control = set.contains("GL_ARB_derivative_control");
        this.GL_ARB_direct_state_access = check_ARB_direct_state_access(functionProvider, apply, set);
        this.GL_ARB_draw_buffers = check_ARB_draw_buffers(functionProvider, apply, set);
        this.GL_ARB_draw_buffers_blend = check_ARB_draw_buffers_blend(functionProvider, apply, set);
        this.GL_ARB_draw_elements_base_vertex = check_ARB_draw_elements_base_vertex(functionProvider, apply, set);
        this.GL_ARB_draw_indirect = check_ARB_draw_indirect(functionProvider, apply, set);
        this.GL_ARB_draw_instanced = check_ARB_draw_instanced(functionProvider, apply, set);
        this.GL_ARB_enhanced_layouts = set.contains("GL_ARB_enhanced_layouts");
        this.GL_ARB_ES2_compatibility = check_ARB_ES2_compatibility(functionProvider, apply, set);
        this.GL_ARB_ES3_1_compatibility = check_ARB_ES3_1_compatibility(functionProvider, apply, set);
        this.GL_ARB_ES3_2_compatibility = check_ARB_ES3_2_compatibility(functionProvider, apply, set);
        this.GL_ARB_ES3_compatibility = set.contains("GL_ARB_ES3_compatibility");
        this.GL_ARB_explicit_attrib_location = set.contains("GL_ARB_explicit_attrib_location");
        this.GL_ARB_explicit_uniform_location = set.contains("GL_ARB_explicit_uniform_location");
        this.GL_ARB_fragment_coord_conventions = set.contains("GL_ARB_fragment_coord_conventions");
        this.GL_ARB_fragment_layer_viewport = set.contains("GL_ARB_fragment_layer_viewport");
        this.GL_ARB_fragment_program = set.contains("GL_ARB_fragment_program");
        this.GL_ARB_fragment_program_shadow = set.contains("GL_ARB_fragment_program_shadow");
        this.GL_ARB_fragment_shader = set.contains("GL_ARB_fragment_shader");
        this.GL_ARB_fragment_shader_interlock = set.contains("GL_ARB_fragment_shader_interlock");
        this.GL_ARB_framebuffer_no_attachments = check_ARB_framebuffer_no_attachments(functionProvider, apply, set);
        this.GL_ARB_framebuffer_object = check_ARB_framebuffer_object(functionProvider, apply, set);
        this.GL_ARB_framebuffer_sRGB = set.contains("GL_ARB_framebuffer_sRGB");
        this.GL_ARB_geometry_shader4 = check_ARB_geometry_shader4(functionProvider, apply, set);
        this.GL_ARB_get_program_binary = check_ARB_get_program_binary(functionProvider, apply, set);
        this.GL_ARB_get_texture_sub_image = check_ARB_get_texture_sub_image(functionProvider, apply, set);
        this.GL_ARB_gl_spirv = check_ARB_gl_spirv(functionProvider, apply, set);
        this.GL_ARB_gpu_shader5 = set.contains("GL_ARB_gpu_shader5");
        this.GL_ARB_gpu_shader_fp64 = check_ARB_gpu_shader_fp64(functionProvider, apply, set);
        this.GL_ARB_gpu_shader_int64 = check_ARB_gpu_shader_int64(functionProvider, apply, set);
        this.GL_ARB_half_float_pixel = set.contains("GL_ARB_half_float_pixel");
        this.GL_ARB_half_float_vertex = set.contains("GL_ARB_half_float_vertex");
        this.GL_ARB_imaging = check_ARB_imaging(functionProvider, apply, set, z);
        this.GL_ARB_indirect_parameters = check_ARB_indirect_parameters(functionProvider, apply, set);
        this.GL_ARB_instanced_arrays = check_ARB_instanced_arrays(functionProvider, apply, set);
        this.GL_ARB_internalformat_query = check_ARB_internalformat_query(functionProvider, apply, set);
        this.GL_ARB_internalformat_query2 = check_ARB_internalformat_query2(functionProvider, apply, set);
        this.GL_ARB_invalidate_subdata = check_ARB_invalidate_subdata(functionProvider, apply, set);
        this.GL_ARB_map_buffer_alignment = set.contains("GL_ARB_map_buffer_alignment");
        this.GL_ARB_map_buffer_range = check_ARB_map_buffer_range(functionProvider, apply, set);
        this.GL_ARB_matrix_palette = check_ARB_matrix_palette(functionProvider, apply, set);
        this.GL_ARB_multi_bind = check_ARB_multi_bind(functionProvider, apply, set);
        this.GL_ARB_multi_draw_indirect = check_ARB_multi_draw_indirect(functionProvider, apply, set);
        this.GL_ARB_multisample = check_ARB_multisample(functionProvider, apply, set);
        this.GL_ARB_multitexture = check_ARB_multitexture(functionProvider, apply, set);
        this.GL_ARB_occlusion_query = check_ARB_occlusion_query(functionProvider, apply, set);
        this.GL_ARB_occlusion_query2 = set.contains("GL_ARB_occlusion_query2");
        this.GL_ARB_parallel_shader_compile = check_ARB_parallel_shader_compile(functionProvider, apply, set);
        this.GL_ARB_pipeline_statistics_query = set.contains("GL_ARB_pipeline_statistics_query");
        this.GL_ARB_pixel_buffer_object = set.contains("GL_ARB_pixel_buffer_object");
        this.GL_ARB_point_parameters = check_ARB_point_parameters(functionProvider, apply, set);
        this.GL_ARB_point_sprite = set.contains("GL_ARB_point_sprite");
        this.GL_ARB_polygon_offset_clamp = check_ARB_polygon_offset_clamp(functionProvider, apply, set);
        this.GL_ARB_post_depth_coverage = set.contains("GL_ARB_post_depth_coverage");
        this.GL_ARB_program_interface_query = check_ARB_program_interface_query(functionProvider, apply, set);
        this.GL_ARB_provoking_vertex = check_ARB_provoking_vertex(functionProvider, apply, set);
        this.GL_ARB_query_buffer_object = set.contains("GL_ARB_query_buffer_object");
        this.GL_ARB_robust_buffer_access_behavior = set.contains("GL_ARB_robust_buffer_access_behavior");
        this.GL_ARB_robustness = check_ARB_robustness(functionProvider, apply, set);
        this.GL_ARB_robustness_application_isolation = set.contains("GL_ARB_robustness_application_isolation");
        this.GL_ARB_robustness_share_group_isolation = set.contains("GL_ARB_robustness_share_group_isolation");
        this.GL_ARB_sample_locations = check_ARB_sample_locations(functionProvider, apply, set);
        this.GL_ARB_sample_shading = check_ARB_sample_shading(functionProvider, apply, set);
        this.GL_ARB_sampler_objects = check_ARB_sampler_objects(functionProvider, apply, set);
        this.GL_ARB_seamless_cube_map = set.contains("GL_ARB_seamless_cube_map");
        this.GL_ARB_seamless_cubemap_per_texture = set.contains("GL_ARB_seamless_cubemap_per_texture");
        this.GL_ARB_separate_shader_objects = check_ARB_separate_shader_objects(functionProvider, apply, set);
        this.GL_ARB_shader_atomic_counter_ops = set.contains("GL_ARB_shader_atomic_counter_ops");
        this.GL_ARB_shader_atomic_counters = check_ARB_shader_atomic_counters(functionProvider, apply, set);
        this.GL_ARB_shader_ballot = set.contains("GL_ARB_shader_ballot");
        this.GL_ARB_shader_bit_encoding = set.contains("GL_ARB_shader_bit_encoding");
        this.GL_ARB_shader_clock = set.contains("GL_ARB_shader_clock");
        this.GL_ARB_shader_draw_parameters = set.contains("GL_ARB_shader_draw_parameters");
        this.GL_ARB_shader_group_vote = set.contains("GL_ARB_shader_group_vote");
        this.GL_ARB_shader_image_load_store = check_ARB_shader_image_load_store(functionProvider, apply, set);
        this.GL_ARB_shader_image_size = set.contains("GL_ARB_shader_image_size");
        this.GL_ARB_shader_objects = check_ARB_shader_objects(functionProvider, apply, set);
        this.GL_ARB_shader_precision = set.contains("GL_ARB_shader_precision");
        this.GL_ARB_shader_stencil_export = set.contains("GL_ARB_shader_stencil_export");
        this.GL_ARB_shader_storage_buffer_object = check_ARB_shader_storage_buffer_object(functionProvider, apply, set);
        this.GL_ARB_shader_subroutine = check_ARB_shader_subroutine(functionProvider, apply, set);
        this.GL_ARB_shader_texture_image_samples = set.contains("GL_ARB_shader_texture_image_samples");
        this.GL_ARB_shader_texture_lod = set.contains("GL_ARB_shader_texture_lod");
        this.GL_ARB_shader_viewport_layer_array = set.contains("GL_ARB_shader_viewport_layer_array");
        this.GL_ARB_shading_language_100 = set.contains("GL_ARB_shading_language_100");
        this.GL_ARB_shading_language_420pack = set.contains("GL_ARB_shading_language_420pack");
        this.GL_ARB_shading_language_include = check_ARB_shading_language_include(functionProvider, apply, set);
        this.GL_ARB_shading_language_packing = set.contains("GL_ARB_shading_language_packing");
        this.GL_ARB_shadow = set.contains("GL_ARB_shadow");
        this.GL_ARB_shadow_ambient = set.contains("GL_ARB_shadow_ambient");
        this.GL_ARB_sparse_buffer = check_ARB_sparse_buffer(functionProvider, apply, set);
        this.GL_ARB_sparse_texture = check_ARB_sparse_texture(functionProvider, apply, set);
        this.GL_ARB_sparse_texture2 = set.contains("GL_ARB_sparse_texture2");
        this.GL_ARB_sparse_texture_clamp = set.contains("GL_ARB_sparse_texture_clamp");
        this.GL_ARB_spirv_extensions = set.contains("GL_ARB_spirv_extensions");
        this.GL_ARB_stencil_texturing = set.contains("GL_ARB_stencil_texturing");
        this.GL_ARB_sync = check_ARB_sync(functionProvider, apply, set);
        this.GL_ARB_tessellation_shader = check_ARB_tessellation_shader(functionProvider, apply, set);
        this.GL_ARB_texture_barrier = check_ARB_texture_barrier(functionProvider, apply, set);
        this.GL_ARB_texture_border_clamp = set.contains("GL_ARB_texture_border_clamp");
        this.GL_ARB_texture_buffer_object = check_ARB_texture_buffer_object(functionProvider, apply, set);
        this.GL_ARB_texture_buffer_object_rgb32 = set.contains("GL_ARB_texture_buffer_object_rgb32");
        this.GL_ARB_texture_buffer_range = check_ARB_texture_buffer_range(functionProvider, apply, set);
        this.GL_ARB_texture_compression = check_ARB_texture_compression(functionProvider, apply, set);
        this.GL_ARB_texture_compression_bptc = set.contains("GL_ARB_texture_compression_bptc");
        this.GL_ARB_texture_compression_rgtc = set.contains("GL_ARB_texture_compression_rgtc");
        this.GL_ARB_texture_cube_map = set.contains("GL_ARB_texture_cube_map");
        this.GL_ARB_texture_cube_map_array = set.contains("GL_ARB_texture_cube_map_array");
        this.GL_ARB_texture_env_add = set.contains("GL_ARB_texture_env_add");
        this.GL_ARB_texture_env_combine = set.contains("GL_ARB_texture_env_combine");
        this.GL_ARB_texture_env_crossbar = set.contains("GL_ARB_texture_env_crossbar");
        this.GL_ARB_texture_env_dot3 = set.contains("GL_ARB_texture_env_dot3");
        this.GL_ARB_texture_filter_anisotropic = set.contains("GL_ARB_texture_filter_anisotropic");
        this.GL_ARB_texture_filter_minmax = set.contains("GL_ARB_texture_filter_minmax");
        this.GL_ARB_texture_float = set.contains("GL_ARB_texture_float");
        this.GL_ARB_texture_gather = set.contains("GL_ARB_texture_gather");
        this.GL_ARB_texture_mirror_clamp_to_edge = set.contains("GL_ARB_texture_mirror_clamp_to_edge");
        this.GL_ARB_texture_mirrored_repeat = set.contains("GL_ARB_texture_mirrored_repeat");
        this.GL_ARB_texture_multisample = check_ARB_texture_multisample(functionProvider, apply, set);
        this.GL_ARB_texture_non_power_of_two = set.contains("GL_ARB_texture_non_power_of_two");
        this.GL_ARB_texture_query_levels = set.contains("GL_ARB_texture_query_levels");
        this.GL_ARB_texture_query_lod = set.contains("GL_ARB_texture_query_lod");
        this.GL_ARB_texture_rectangle = set.contains("GL_ARB_texture_rectangle");
        this.GL_ARB_texture_rg = set.contains("GL_ARB_texture_rg");
        this.GL_ARB_texture_rgb10_a2ui = set.contains("GL_ARB_texture_rgb10_a2ui");
        this.GL_ARB_texture_stencil8 = set.contains("GL_ARB_texture_stencil8");
        this.GL_ARB_texture_storage = check_ARB_texture_storage(functionProvider, apply, set);
        this.GL_ARB_texture_storage_multisample = check_ARB_texture_storage_multisample(functionProvider, apply, set);
        this.GL_ARB_texture_swizzle = set.contains("GL_ARB_texture_swizzle");
        this.GL_ARB_texture_view = check_ARB_texture_view(functionProvider, apply, set);
        this.GL_ARB_timer_query = check_ARB_timer_query(functionProvider, apply, set);
        this.GL_ARB_transform_feedback2 = check_ARB_transform_feedback2(functionProvider, apply, set);
        this.GL_ARB_transform_feedback3 = check_ARB_transform_feedback3(functionProvider, apply, set);
        this.GL_ARB_transform_feedback_instanced = check_ARB_transform_feedback_instanced(functionProvider, apply, set);
        this.GL_ARB_transform_feedback_overflow_query = set.contains("GL_ARB_transform_feedback_overflow_query");
        this.GL_ARB_transpose_matrix = check_ARB_transpose_matrix(functionProvider, apply, set);
        this.GL_ARB_uniform_buffer_object = check_ARB_uniform_buffer_object(functionProvider, apply, set);
        this.GL_ARB_vertex_array_bgra = set.contains("GL_ARB_vertex_array_bgra");
        this.GL_ARB_vertex_array_object = check_ARB_vertex_array_object(functionProvider, apply, set);
        this.GL_ARB_vertex_attrib_64bit = check_ARB_vertex_attrib_64bit(functionProvider, apply, set);
        this.GL_ARB_vertex_attrib_binding = check_ARB_vertex_attrib_binding(functionProvider, apply, set);
        this.GL_ARB_vertex_blend = check_ARB_vertex_blend(functionProvider, apply, set);
        this.GL_ARB_vertex_buffer_object = check_ARB_vertex_buffer_object(functionProvider, apply, set);
        this.GL_ARB_vertex_program = check_ARB_vertex_program(functionProvider, apply, set);
        this.GL_ARB_vertex_shader = check_ARB_vertex_shader(functionProvider, apply, set);
        this.GL_ARB_vertex_type_10f_11f_11f_rev = set.contains("GL_ARB_vertex_type_10f_11f_11f_rev");
        this.GL_ARB_vertex_type_2_10_10_10_rev = check_ARB_vertex_type_2_10_10_10_rev(functionProvider, apply, set, z);
        this.GL_ARB_viewport_array = check_ARB_viewport_array(functionProvider, apply, set);
        this.GL_ARB_window_pos = check_ARB_window_pos(functionProvider, apply, set);
        this.GL_ATI_meminfo = set.contains("GL_ATI_meminfo");
        this.GL_ATI_shader_texture_lod = set.contains("GL_ATI_shader_texture_lod");
        this.GL_ATI_texture_compression_3dc = set.contains("GL_ATI_texture_compression_3dc");
        this.GL_EXT_422_pixels = set.contains("GL_EXT_422_pixels");
        this.GL_EXT_abgr = set.contains("GL_EXT_abgr");
        this.GL_EXT_bgra = set.contains("GL_EXT_bgra");
        this.GL_EXT_bindable_uniform = check_EXT_bindable_uniform(functionProvider, apply, set);
        this.GL_EXT_blend_color = check_EXT_blend_color(functionProvider, apply, set);
        this.GL_EXT_blend_equation_separate = check_EXT_blend_equation_separate(functionProvider, apply, set);
        this.GL_EXT_blend_func_separate = check_EXT_blend_func_separate(functionProvider, apply, set);
        this.GL_EXT_blend_minmax = check_EXT_blend_minmax(functionProvider, apply, set);
        this.GL_EXT_blend_subtract = set.contains("GL_EXT_blend_subtract");
        this.GL_EXT_clip_volume_hint = set.contains("GL_EXT_clip_volume_hint");
        this.GL_EXT_compiled_vertex_array = check_EXT_compiled_vertex_array(functionProvider, apply, set);
        this.GL_EXT_debug_label = check_EXT_debug_label(functionProvider, apply, set);
        this.GL_EXT_debug_marker = check_EXT_debug_marker(functionProvider, apply, set);
        this.GL_EXT_depth_bounds_test = check_EXT_depth_bounds_test(functionProvider, apply, set);
        this.GL_EXT_direct_state_access = check_EXT_direct_state_access(functionProvider, apply, set);
        this.GL_EXT_draw_buffers2 = check_EXT_draw_buffers2(functionProvider, apply, set);
        this.GL_EXT_draw_instanced = check_EXT_draw_instanced(functionProvider, apply, set);
        this.GL_EXT_EGL_image_storage = check_EXT_EGL_image_storage(functionProvider, apply, set);
        this.GL_EXT_EGL_sync = set.contains("GL_EXT_EGL_sync");
        this.GL_EXT_external_buffer = check_EXT_external_buffer(functionProvider, apply, set);
        this.GL_EXT_framebuffer_blit = check_EXT_framebuffer_blit(functionProvider, apply, set);
        this.GL_EXT_framebuffer_blit_layers = check_EXT_framebuffer_blit_layers(functionProvider, apply, set);
        this.GL_EXT_framebuffer_multisample = check_EXT_framebuffer_multisample(functionProvider, apply, set);
        this.GL_EXT_framebuffer_multisample_blit_scaled = set.contains("GL_EXT_framebuffer_multisample_blit_scaled");
        this.GL_EXT_framebuffer_object = check_EXT_framebuffer_object(functionProvider, apply, set);
        this.GL_EXT_framebuffer_sRGB = set.contains("GL_EXT_framebuffer_sRGB");
        this.GL_EXT_geometry_shader4 = check_EXT_geometry_shader4(functionProvider, apply, set);
        this.GL_EXT_gpu_program_parameters = check_EXT_gpu_program_parameters(functionProvider, apply, set);
        this.GL_EXT_gpu_shader4 = check_EXT_gpu_shader4(functionProvider, apply, set);
        this.GL_EXT_memory_object = check_EXT_memory_object(functionProvider, apply, set);
        this.GL_EXT_memory_object_fd = check_EXT_memory_object_fd(functionProvider, apply, set);
        this.GL_EXT_memory_object_win32 = check_EXT_memory_object_win32(functionProvider, apply, set);
        this.GL_EXT_multiview_tessellation_geometry_shader = set.contains("GL_EXT_multiview_tessellation_geometry_shader");
        this.GL_EXT_multiview_texture_multisample = set.contains("GL_EXT_multiview_texture_multisample");
        this.GL_EXT_multiview_timer_query = set.contains("GL_EXT_multiview_timer_query");
        this.GL_EXT_packed_depth_stencil = set.contains("GL_EXT_packed_depth_stencil");
        this.GL_EXT_packed_float = set.contains("GL_EXT_packed_float");
        this.GL_EXT_pixel_buffer_object = set.contains("GL_EXT_pixel_buffer_object");
        this.GL_EXT_point_parameters = check_EXT_point_parameters(functionProvider, apply, set);
        this.GL_EXT_polygon_offset_clamp = check_EXT_polygon_offset_clamp(functionProvider, apply, set);
        this.GL_EXT_post_depth_coverage = set.contains("GL_EXT_post_depth_coverage");
        this.GL_EXT_provoking_vertex = check_EXT_provoking_vertex(functionProvider, apply, set);
        this.GL_EXT_raster_multisample = check_EXT_raster_multisample(functionProvider, apply, set);
        this.GL_EXT_secondary_color = check_EXT_secondary_color(functionProvider, apply, set);
        this.GL_EXT_semaphore = check_EXT_semaphore(functionProvider, apply, set);
        this.GL_EXT_semaphore_fd = check_EXT_semaphore_fd(functionProvider, apply, set);
        this.GL_EXT_semaphore_win32 = check_EXT_semaphore_win32(functionProvider, apply, set);
        this.GL_EXT_separate_shader_objects = check_EXT_separate_shader_objects(functionProvider, apply, set);
        this.GL_EXT_shader_framebuffer_fetch = set.contains("GL_EXT_shader_framebuffer_fetch");
        this.GL_EXT_shader_framebuffer_fetch_non_coherent = check_EXT_shader_framebuffer_fetch_non_coherent(functionProvider, apply, set);
        this.GL_EXT_shader_image_load_formatted = set.contains("GL_EXT_shader_image_load_formatted");
        this.GL_EXT_shader_image_load_store = check_EXT_shader_image_load_store(functionProvider, apply, set);
        this.GL_EXT_shader_integer_mix = set.contains("GL_EXT_shader_integer_mix");
        this.GL_EXT_shader_samples_identical = set.contains("GL_EXT_shader_samples_identical");
        this.GL_EXT_shadow_funcs = set.contains("GL_EXT_shadow_funcs");
        this.GL_EXT_shared_texture_palette = set.contains("GL_EXT_shared_texture_palette");
        this.GL_EXT_sparse_texture2 = set.contains("GL_EXT_sparse_texture2");
        this.GL_EXT_stencil_clear_tag = check_EXT_stencil_clear_tag(functionProvider, apply, set);
        this.GL_EXT_stencil_two_side = check_EXT_stencil_two_side(functionProvider, apply, set);
        this.GL_EXT_stencil_wrap = set.contains("GL_EXT_stencil_wrap");
        this.GL_EXT_texture_array = check_EXT_texture_array(functionProvider, apply, set);
        this.GL_EXT_texture_buffer_object = check_EXT_texture_buffer_object(functionProvider, apply, set);
        this.GL_EXT_texture_compression_latc = set.contains("GL_EXT_texture_compression_latc");
        this.GL_EXT_texture_compression_rgtc = set.contains("GL_EXT_texture_compression_rgtc");
        this.GL_EXT_texture_compression_s3tc = set.contains("GL_EXT_texture_compression_s3tc");
        this.GL_EXT_texture_filter_anisotropic = set.contains("GL_EXT_texture_filter_anisotropic");
        this.GL_EXT_texture_filter_minmax = set.contains("GL_EXT_texture_filter_minmax");
        this.GL_EXT_texture_integer = check_EXT_texture_integer(functionProvider, apply, set);
        this.GL_EXT_texture_mirror_clamp = set.contains("GL_EXT_texture_mirror_clamp");
        this.GL_EXT_texture_shadow_lod = set.contains("GL_EXT_texture_shadow_lod");
        this.GL_EXT_texture_shared_exponent = set.contains("GL_EXT_texture_shared_exponent");
        this.GL_EXT_texture_snorm = set.contains("GL_EXT_texture_snorm");
        this.GL_EXT_texture_sRGB = set.contains("GL_EXT_texture_sRGB");
        this.GL_EXT_texture_sRGB_decode = set.contains("GL_EXT_texture_sRGB_decode");
        this.GL_EXT_texture_sRGB_R8 = set.contains("GL_EXT_texture_sRGB_R8");
        this.GL_EXT_texture_sRGB_RG8 = set.contains("GL_EXT_texture_sRGB_RG8");
        this.GL_EXT_texture_storage = check_EXT_texture_storage(functionProvider, apply, set);
        this.GL_EXT_texture_swizzle = set.contains("GL_EXT_texture_swizzle");
        this.GL_EXT_timer_query = check_EXT_timer_query(functionProvider, apply, set);
        this.GL_EXT_transform_feedback = check_EXT_transform_feedback(functionProvider, apply, set);
        this.GL_EXT_vertex_array_bgra = set.contains("GL_EXT_vertex_array_bgra");
        this.GL_EXT_vertex_attrib_64bit = check_EXT_vertex_attrib_64bit(functionProvider, apply, set);
        this.GL_EXT_win32_keyed_mutex = check_EXT_win32_keyed_mutex(functionProvider, apply, set);
        this.GL_EXT_window_rectangles = check_EXT_window_rectangles(functionProvider, apply, set);
        this.GL_EXT_x11_sync_object = check_EXT_x11_sync_object(functionProvider, apply, set);
        this.GL_GREMEDY_frame_terminator = check_GREMEDY_frame_terminator(functionProvider, apply, set);
        this.GL_GREMEDY_string_marker = check_GREMEDY_string_marker(functionProvider, apply, set);
        this.GL_INTEL_blackhole_render = set.contains("GL_INTEL_blackhole_render");
        this.GL_INTEL_conservative_rasterization = set.contains("GL_INTEL_conservative_rasterization");
        this.GL_INTEL_fragment_shader_ordering = set.contains("GL_INTEL_fragment_shader_ordering");
        this.GL_INTEL_framebuffer_CMAA = check_INTEL_framebuffer_CMAA(functionProvider, apply, set);
        this.GL_INTEL_map_texture = check_INTEL_map_texture(functionProvider, apply, set);
        this.GL_INTEL_performance_query = check_INTEL_performance_query(functionProvider, apply, set);
        this.GL_INTEL_shader_integer_functions2 = set.contains("GL_INTEL_shader_integer_functions2");
        this.GL_KHR_blend_equation_advanced = check_KHR_blend_equation_advanced(functionProvider, apply, set);
        this.GL_KHR_blend_equation_advanced_coherent = set.contains("GL_KHR_blend_equation_advanced_coherent");
        this.GL_KHR_context_flush_control = set.contains("GL_KHR_context_flush_control");
        this.GL_KHR_debug = check_KHR_debug(functionProvider, apply, set);
        this.GL_KHR_no_error = set.contains("GL_KHR_no_error");
        this.GL_KHR_parallel_shader_compile = check_KHR_parallel_shader_compile(functionProvider, apply, set);
        this.GL_KHR_robust_buffer_access_behavior = set.contains("GL_KHR_robust_buffer_access_behavior");
        this.GL_KHR_robustness = check_KHR_robustness(functionProvider, apply, set);
        this.GL_KHR_shader_subgroup = set.contains("GL_KHR_shader_subgroup");
        this.GL_KHR_texture_compression_astc_hdr = set.contains("GL_KHR_texture_compression_astc_hdr");
        this.GL_KHR_texture_compression_astc_ldr = set.contains("GL_KHR_texture_compression_astc_ldr");
        this.GL_KHR_texture_compression_astc_sliced_3d = set.contains("GL_KHR_texture_compression_astc_sliced_3d");
        this.GL_MESA_framebuffer_flip_x = set.contains("GL_MESA_framebuffer_flip_x");
        this.GL_MESA_framebuffer_flip_y = check_MESA_framebuffer_flip_y(functionProvider, apply, set);
        this.GL_MESA_framebuffer_swap_xy = set.contains("GL_MESA_framebuffer_swap_xy");
        this.GL_MESA_tile_raster_order = set.contains("GL_MESA_tile_raster_order");
        this.GL_NV_alpha_to_coverage_dither_control = check_NV_alpha_to_coverage_dither_control(functionProvider, apply, set);
        this.GL_NV_bindless_multi_draw_indirect = check_NV_bindless_multi_draw_indirect(functionProvider, apply, set);
        this.GL_NV_bindless_multi_draw_indirect_count = check_NV_bindless_multi_draw_indirect_count(functionProvider, apply, set);
        this.GL_NV_bindless_texture = check_NV_bindless_texture(functionProvider, apply, set);
        this.GL_NV_blend_equation_advanced = check_NV_blend_equation_advanced(functionProvider, apply, set);
        this.GL_NV_blend_equation_advanced_coherent = set.contains("GL_NV_blend_equation_advanced_coherent");
        this.GL_NV_blend_minmax_factor = set.contains("GL_NV_blend_minmax_factor");
        this.GL_NV_blend_square = set.contains("GL_NV_blend_square");
        this.GL_NV_clip_space_w_scaling = check_NV_clip_space_w_scaling(functionProvider, apply, set);
        this.GL_NV_command_list = check_NV_command_list(functionProvider, apply, set);
        this.GL_NV_compute_shader_derivatives = set.contains("GL_NV_compute_shader_derivatives");
        this.GL_NV_conditional_render = check_NV_conditional_render(functionProvider, apply, set);
        this.GL_NV_conservative_raster = check_NV_conservative_raster(functionProvider, apply, set);
        this.GL_NV_conservative_raster_dilate = check_NV_conservative_raster_dilate(functionProvider, apply, set);
        this.GL_NV_conservative_raster_pre_snap = set.contains("GL_NV_conservative_raster_pre_snap");
        this.GL_NV_conservative_raster_pre_snap_triangles = check_NV_conservative_raster_pre_snap_triangles(functionProvider, apply, set);
        this.GL_NV_conservative_raster_underestimation = set.contains("GL_NV_conservative_raster_underestimation");
        this.GL_NV_copy_depth_to_color = set.contains("GL_NV_copy_depth_to_color");
        this.GL_NV_copy_image = check_NV_copy_image(functionProvider, apply, set);
        this.GL_NV_deep_texture3D = set.contains("GL_NV_deep_texture3D");
        this.GL_NV_depth_buffer_float = check_NV_depth_buffer_float(functionProvider, apply, set);
        this.GL_NV_depth_clamp = set.contains("GL_NV_depth_clamp");
        this.GL_NV_draw_texture = check_NV_draw_texture(functionProvider, apply, set);
        this.GL_NV_draw_vulkan_image = check_NV_draw_vulkan_image(functionProvider, apply, set);
        this.GL_NV_ES3_1_compatibility = set.contains("GL_NV_ES3_1_compatibility");
        this.GL_NV_explicit_multisample = check_NV_explicit_multisample(functionProvider, apply, set);
        this.GL_NV_fence = check_NV_fence(functionProvider, apply, set);
        this.GL_NV_fill_rectangle = set.contains("GL_NV_fill_rectangle");
        this.GL_NV_float_buffer = set.contains("GL_NV_float_buffer");
        this.GL_NV_fog_distance = set.contains("GL_NV_fog_distance");
        this.GL_NV_fragment_coverage_to_color = check_NV_fragment_coverage_to_color(functionProvider, apply, set);
        this.GL_NV_fragment_program4 = set.contains("GL_NV_fragment_program4");
        this.GL_NV_fragment_program_option = set.contains("GL_NV_fragment_program_option");
        this.GL_NV_fragment_shader_barycentric = set.contains("GL_NV_fragment_shader_barycentric");
        this.GL_NV_fragment_shader_interlock = set.contains("GL_NV_fragment_shader_interlock");
        this.GL_NV_framebuffer_mixed_samples = check_NV_framebuffer_mixed_samples(functionProvider, apply, set);
        this.GL_NV_framebuffer_multisample_coverage = check_NV_framebuffer_multisample_coverage(functionProvider, apply, set);
        this.GL_NV_geometry_shader4 = set.contains("GL_NV_geometry_shader4");
        this.GL_NV_geometry_shader_passthrough = set.contains("GL_NV_geometry_shader_passthrough");
        this.GL_NV_gpu_multicast = check_NV_gpu_multicast(functionProvider, apply, set);
        this.GL_NV_gpu_shader5 = check_NV_gpu_shader5(functionProvider, apply, set);
        this.GL_NV_half_float = check_NV_half_float(functionProvider, apply, set);
        this.GL_NV_internalformat_sample_query = check_NV_internalformat_sample_query(functionProvider, apply, set);
        this.GL_NV_light_max_exponent = set.contains("GL_NV_light_max_exponent");
        this.GL_NV_memory_attachment = check_NV_memory_attachment(functionProvider, apply, set);
        this.GL_NV_memory_object_sparse = check_NV_memory_object_sparse(functionProvider, apply, set);
        this.GL_NV_mesh_shader = check_NV_mesh_shader(functionProvider, apply, set);
        this.GL_NV_multisample_coverage = set.contains("GL_NV_multisample_coverage");
        this.GL_NV_multisample_filter_hint = set.contains("GL_NV_multisample_filter_hint");
        this.GL_NV_packed_depth_stencil = set.contains("GL_NV_packed_depth_stencil");
        this.GL_NV_path_rendering = check_NV_path_rendering(functionProvider, apply, set);
        this.GL_NV_path_rendering_shared_edge = set.contains("GL_NV_path_rendering_shared_edge");
        this.GL_NV_pixel_data_range = check_NV_pixel_data_range(functionProvider, apply, set);
        this.GL_NV_point_sprite = check_NV_point_sprite(functionProvider, apply, set);
        this.GL_NV_primitive_restart = check_NV_primitive_restart(functionProvider, apply, set);
        this.GL_NV_primitive_shading_rate = set.contains("GL_NV_primitive_shading_rate");
        this.GL_NV_query_resource = check_NV_query_resource(functionProvider, apply, set);
        this.GL_NV_query_resource_tag = check_NV_query_resource_tag(functionProvider, apply, set);
        this.GL_NV_representative_fragment_test = set.contains("GL_NV_representative_fragment_test");
        this.GL_NV_robustness_video_memory_purge = set.contains("GL_NV_robustness_video_memory_purge");
        this.GL_NV_sample_locations = check_NV_sample_locations(functionProvider, apply, set);
        this.GL_NV_sample_mask_override_coverage = set.contains("GL_NV_sample_mask_override_coverage");
        this.GL_NV_scissor_exclusive = check_NV_scissor_exclusive(functionProvider, apply, set);
        this.GL_NV_shader_atomic_float = set.contains("GL_NV_shader_atomic_float");
        this.GL_NV_shader_atomic_float64 = set.contains("GL_NV_shader_atomic_float64");
        this.GL_NV_shader_atomic_fp16_vector = set.contains("GL_NV_shader_atomic_fp16_vector");
        this.GL_NV_shader_atomic_int64 = set.contains("GL_NV_shader_atomic_int64");
        this.GL_NV_shader_buffer_load = check_NV_shader_buffer_load(functionProvider, apply, set);
        this.GL_NV_shader_buffer_store = set.contains("GL_NV_shader_buffer_store");
        this.GL_NV_shader_subgroup_partitioned = set.contains("GL_NV_shader_subgroup_partitioned");
        this.GL_NV_shader_texture_footprint = set.contains("GL_NV_shader_texture_footprint");
        this.GL_NV_shader_thread_group = set.contains("GL_NV_shader_thread_group");
        this.GL_NV_shader_thread_shuffle = set.contains("GL_NV_shader_thread_shuffle");
        this.GL_NV_shading_rate_image = check_NV_shading_rate_image(functionProvider, apply, set);
        this.GL_NV_stereo_view_rendering = set.contains("GL_NV_stereo_view_rendering");
        this.GL_NV_texgen_reflection = set.contains("GL_NV_texgen_reflection");
        this.GL_NV_texture_barrier = check_NV_texture_barrier(functionProvider, apply, set);
        this.GL_NV_texture_compression_vtc = set.contains("GL_NV_texture_compression_vtc");
        this.GL_NV_texture_multisample = check_NV_texture_multisample(functionProvider, apply, set);
        this.GL_NV_texture_rectangle_compressed = set.contains("GL_NV_texture_rectangle_compressed");
        this.GL_NV_texture_shader = set.contains("GL_NV_texture_shader");
        this.GL_NV_texture_shader2 = set.contains("GL_NV_texture_shader2");
        this.GL_NV_texture_shader3 = set.contains("GL_NV_texture_shader3");
        this.GL_NV_timeline_semaphore = check_NV_timeline_semaphore(functionProvider, apply, set);
        this.GL_NV_transform_feedback = check_NV_transform_feedback(functionProvider, apply, set);
        this.GL_NV_transform_feedback2 = check_NV_transform_feedback2(functionProvider, apply, set);
        this.GL_NV_uniform_buffer_std430_layout = set.contains("GL_NV_uniform_buffer_std430_layout");
        this.GL_NV_uniform_buffer_unified_memory = set.contains("GL_NV_uniform_buffer_unified_memory");
        this.GL_NV_vertex_array_range = check_NV_vertex_array_range(functionProvider, apply, set);
        this.GL_NV_vertex_array_range2 = set.contains("GL_NV_vertex_array_range2");
        this.GL_NV_vertex_attrib_integer_64bit = check_NV_vertex_attrib_integer_64bit(functionProvider, apply, set);
        this.GL_NV_vertex_buffer_unified_memory = check_NV_vertex_buffer_unified_memory(functionProvider, apply, set);
        this.GL_NV_viewport_array2 = set.contains("GL_NV_viewport_array2");
        this.GL_NV_viewport_swizzle = check_NV_viewport_swizzle(functionProvider, apply, set);
        this.GL_NVX_blend_equation_advanced_multi_draw_buffers = set.contains("GL_NVX_blend_equation_advanced_multi_draw_buffers");
        this.GL_NVX_conditional_render = check_NVX_conditional_render(functionProvider, apply, set);
        this.GL_NVX_gpu_memory_info = set.contains("GL_NVX_gpu_memory_info");
        this.GL_NVX_gpu_multicast2 = check_NVX_gpu_multicast2(functionProvider, apply, set);
        this.GL_NVX_progress_fence = check_NVX_progress_fence(functionProvider, apply, set);
        this.GL_OVR_multiview = check_OVR_multiview(functionProvider, apply, set);
        this.GL_OVR_multiview2 = set.contains("GL_OVR_multiview2");
        this.GL_S3_s3tc = set.contains("GL_S3_s3tc");
        this.glEnable = apply.get(0);
        this.glDisable = apply.get(1);
        this.glAccum = apply.get(2);
        this.glAlphaFunc = apply.get(3);
        this.glAreTexturesResident = apply.get(4);
        this.glArrayElement = apply.get(5);
        this.glBegin = apply.get(6);
        this.glBindTexture = apply.get(7);
        this.glBitmap = apply.get(8);
        this.glBlendFunc = apply.get(9);
        this.glCallList = apply.get(10);
        this.glCallLists = apply.get(11);
        this.glClear = apply.get(12);
        this.glClearAccum = apply.get(13);
        this.glClearColor = apply.get(14);
        this.glClearDepth = apply.get(15);
        this.glClearIndex = apply.get(16);
        this.glClearStencil = apply.get(17);
        this.glClipPlane = apply.get(18);
        this.glColor3b = apply.get(19);
        this.glColor3s = apply.get(20);
        this.glColor3i = apply.get(21);
        this.glColor3f = apply.get(22);
        this.glColor3d = apply.get(23);
        this.glColor3ub = apply.get(24);
        this.glColor3us = apply.get(25);
        this.glColor3ui = apply.get(26);
        this.glColor3bv = apply.get(27);
        this.glColor3sv = apply.get(28);
        this.glColor3iv = apply.get(29);
        this.glColor3fv = apply.get(30);
        this.glColor3dv = apply.get(31);
        this.glColor3ubv = apply.get(32);
        this.glColor3usv = apply.get(33);
        this.glColor3uiv = apply.get(34);
        this.glColor4b = apply.get(35);
        this.glColor4s = apply.get(36);
        this.glColor4i = apply.get(37);
        this.glColor4f = apply.get(38);
        this.glColor4d = apply.get(39);
        this.glColor4ub = apply.get(40);
        this.glColor4us = apply.get(41);
        this.glColor4ui = apply.get(42);
        this.glColor4bv = apply.get(43);
        this.glColor4sv = apply.get(44);
        this.glColor4iv = apply.get(45);
        this.glColor4fv = apply.get(46);
        this.glColor4dv = apply.get(47);
        this.glColor4ubv = apply.get(48);
        this.glColor4usv = apply.get(49);
        this.glColor4uiv = apply.get(50);
        this.glColorMask = apply.get(51);
        this.glColorMaterial = apply.get(52);
        this.glColorPointer = apply.get(53);
        this.glCopyPixels = apply.get(54);
        this.glCullFace = apply.get(55);
        this.glDeleteLists = apply.get(56);
        this.glDepthFunc = apply.get(57);
        this.glDepthMask = apply.get(58);
        this.glDepthRange = apply.get(59);
        this.glDisableClientState = apply.get(60);
        this.glDrawArrays = apply.get(61);
        this.glDrawBuffer = apply.get(62);
        this.glDrawElements = apply.get(63);
        this.glDrawPixels = apply.get(64);
        this.glEdgeFlag = apply.get(65);
        this.glEdgeFlagv = apply.get(66);
        this.glEdgeFlagPointer = apply.get(67);
        this.glEnableClientState = apply.get(68);
        this.glEnd = apply.get(69);
        this.glEvalCoord1f = apply.get(70);
        this.glEvalCoord1fv = apply.get(71);
        this.glEvalCoord1d = apply.get(72);
        this.glEvalCoord1dv = apply.get(73);
        this.glEvalCoord2f = apply.get(74);
        this.glEvalCoord2fv = apply.get(75);
        this.glEvalCoord2d = apply.get(76);
        this.glEvalCoord2dv = apply.get(77);
        this.glEvalMesh1 = apply.get(78);
        this.glEvalMesh2 = apply.get(79);
        this.glEvalPoint1 = apply.get(80);
        this.glEvalPoint2 = apply.get(81);
        this.glFeedbackBuffer = apply.get(82);
        this.glFinish = apply.get(83);
        this.glFlush = apply.get(84);
        this.glFogi = apply.get(85);
        this.glFogiv = apply.get(86);
        this.glFogf = apply.get(87);
        this.glFogfv = apply.get(88);
        this.glFrontFace = apply.get(89);
        this.glGenLists = apply.get(90);
        this.glGenTextures = apply.get(91);
        this.glDeleteTextures = apply.get(92);
        this.glGetClipPlane = apply.get(93);
        this.glGetBooleanv = apply.get(94);
        this.glGetFloatv = apply.get(95);
        this.glGetIntegerv = apply.get(96);
        this.glGetDoublev = apply.get(97);
        this.glGetError = apply.get(98);
        this.glGetLightiv = apply.get(99);
        this.glGetLightfv = apply.get(100);
        this.glGetMapiv = apply.get(101);
        this.glGetMapfv = apply.get(102);
        this.glGetMapdv = apply.get(103);
        this.glGetMaterialiv = apply.get(104);
        this.glGetMaterialfv = apply.get(105);
        this.glGetPixelMapfv = apply.get(106);
        this.glGetPixelMapusv = apply.get(107);
        this.glGetPixelMapuiv = apply.get(108);
        this.glGetPointerv = apply.get(109);
        this.glGetPolygonStipple = apply.get(110);
        this.glGetString = apply.get(111);
        this.glGetTexEnviv = apply.get(112);
        this.glGetTexEnvfv = apply.get(113);
        this.glGetTexGeniv = apply.get(114);
        this.glGetTexGenfv = apply.get(115);
        this.glGetTexGendv = apply.get(116);
        this.glGetTexImage = apply.get(117);
        this.glGetTexLevelParameteriv = apply.get(118);
        this.glGetTexLevelParameterfv = apply.get(119);
        this.glGetTexParameteriv = apply.get(120);
        this.glGetTexParameterfv = apply.get(121);
        this.glHint = apply.get(122);
        this.glIndexi = apply.get(123);
        this.glIndexub = apply.get(124);
        this.glIndexs = apply.get(125);
        this.glIndexf = apply.get(126);
        this.glIndexd = apply.get(127);
        this.glIndexiv = apply.get(128);
        this.glIndexubv = apply.get(129);
        this.glIndexsv = apply.get(130);
        this.glIndexfv = apply.get(131);
        this.glIndexdv = apply.get(132);
        this.glIndexMask = apply.get(133);
        this.glIndexPointer = apply.get(134);
        this.glInitNames = apply.get(135);
        this.glInterleavedArrays = apply.get(136);
        this.glIsEnabled = apply.get(137);
        this.glIsList = apply.get(138);
        this.glIsTexture = apply.get(139);
        this.glLightModeli = apply.get(140);
        this.glLightModelf = apply.get(141);
        this.glLightModeliv = apply.get(142);
        this.glLightModelfv = apply.get(143);
        this.glLighti = apply.get(144);
        this.glLightf = apply.get(145);
        this.glLightiv = apply.get(146);
        this.glLightfv = apply.get(147);
        this.glLineStipple = apply.get(148);
        this.glLineWidth = apply.get(149);
        this.glListBase = apply.get(150);
        this.glLoadMatrixf = apply.get(151);
        this.glLoadMatrixd = apply.get(152);
        this.glLoadIdentity = apply.get(153);
        this.glLoadName = apply.get(154);
        this.glLogicOp = apply.get(155);
        this.glMap1f = apply.get(156);
        this.glMap1d = apply.get(157);
        this.glMap2f = apply.get(158);
        this.glMap2d = apply.get(159);
        this.glMapGrid1f = apply.get(160);
        this.glMapGrid1d = apply.get(161);
        this.glMapGrid2f = apply.get(162);
        this.glMapGrid2d = apply.get(163);
        this.glMateriali = apply.get(164);
        this.glMaterialf = apply.get(165);
        this.glMaterialiv = apply.get(166);
        this.glMaterialfv = apply.get(167);
        this.glMatrixMode = apply.get(168);
        this.glMultMatrixf = apply.get(169);
        this.glMultMatrixd = apply.get(170);
        this.glFrustum = apply.get(171);
        this.glNewList = apply.get(172);
        this.glEndList = apply.get(173);
        this.glNormal3f = apply.get(174);
        this.glNormal3b = apply.get(175);
        this.glNormal3s = apply.get(176);
        this.glNormal3i = apply.get(177);
        this.glNormal3d = apply.get(178);
        this.glNormal3fv = apply.get(179);
        this.glNormal3bv = apply.get(180);
        this.glNormal3sv = apply.get(181);
        this.glNormal3iv = apply.get(182);
        this.glNormal3dv = apply.get(183);
        this.glNormalPointer = apply.get(184);
        this.glOrtho = apply.get(185);
        this.glPassThrough = apply.get(186);
        this.glPixelMapfv = apply.get(187);
        this.glPixelMapusv = apply.get(188);
        this.glPixelMapuiv = apply.get(189);
        this.glPixelStorei = apply.get(190);
        this.glPixelStoref = apply.get(191);
        this.glPixelTransferi = apply.get(192);
        this.glPixelTransferf = apply.get(193);
        this.glPixelZoom = apply.get(194);
        this.glPointSize = apply.get(195);
        this.glPolygonMode = apply.get(196);
        this.glPolygonOffset = apply.get(197);
        this.glPolygonStipple = apply.get(198);
        this.glPushAttrib = apply.get(199);
        this.glPushClientAttrib = apply.get(200);
        this.glPopAttrib = apply.get(201);
        this.glPopClientAttrib = apply.get(HttpStatus.SC_ACCEPTED);
        this.glPopMatrix = apply.get(203);
        this.glPopName = apply.get(HttpStatus.SC_NO_CONTENT);
        this.glPrioritizeTextures = apply.get(HttpStatus.SC_RESET_CONTENT);
        this.glPushMatrix = apply.get(HttpStatus.SC_PARTIAL_CONTENT);
        this.glPushName = apply.get(HttpStatus.SC_MULTI_STATUS);
        this.glRasterPos2i = apply.get(208);
        this.glRasterPos2s = apply.get(209);
        this.glRasterPos2f = apply.get(210);
        this.glRasterPos2d = apply.get(211);
        this.glRasterPos2iv = apply.get(212);
        this.glRasterPos2sv = apply.get(213);
        this.glRasterPos2fv = apply.get(214);
        this.glRasterPos2dv = apply.get(215);
        this.glRasterPos3i = apply.get(216);
        this.glRasterPos3s = apply.get(217);
        this.glRasterPos3f = apply.get(218);
        this.glRasterPos3d = apply.get(User32.VK_OEM_4);
        this.glRasterPos3iv = apply.get(User32.VK_OEM_5);
        this.glRasterPos3sv = apply.get(221);
        this.glRasterPos3fv = apply.get(222);
        this.glRasterPos3dv = apply.get(User32.VK_OEM_8);
        this.glRasterPos4i = apply.get(CGL.kCGLCPDispatchTableSize);
        this.glRasterPos4s = apply.get(User32.VK_OEM_AX);
        this.glRasterPos4f = apply.get(226);
        this.glRasterPos4d = apply.get(User32.VK_ICO_HELP);
        this.glRasterPos4iv = apply.get(228);
        this.glRasterPos4sv = apply.get(User32.VK_PROCESSKEY);
        this.glRasterPos4fv = apply.get(230);
        this.glRasterPos4dv = apply.get(User32.VK_PACKET);
        this.glReadBuffer = apply.get(232);
        this.glReadPixels = apply.get(User32.VK_OEM_RESET);
        this.glRecti = apply.get(User32.VK_OEM_JUMP);
        this.glRects = apply.get(235);
        this.glRectf = apply.get(236);
        this.glRectd = apply.get(User32.VK_OEM_PA3);
        this.glRectiv = apply.get(User32.VK_OEM_WSCTRL);
        this.glRectsv = apply.get(User32.VK_OEM_CUSEL);
        this.glRectfv = apply.get(User32.VK_OEM_ATTN);
        this.glRectdv = apply.get(User32.VK_OEM_FINISH);
        this.glRenderMode = apply.get(User32.VK_OEM_COPY);
        this.glRotatef = apply.get(243);
        this.glRotated = apply.get(User32.VK_OEM_ENLW);
        this.glScalef = apply.get(User32.VK_OEM_BACKTAB);
        this.glScaled = apply.get(User32.VK_ATTN);
        this.glScissor = apply.get(User32.VK_CRSEL);
        this.glSelectBuffer = apply.get(User32.VK_EXSEL);
        this.glShadeModel = apply.get(User32.VK_EREOF);
        this.glStencilFunc = apply.get(User32.VK_PLAY);
        this.glStencilMask = apply.get(User32.VK_ZOOM);
        this.glStencilOp = apply.get(User32.VK_NONAME);
        this.glTexCoord1f = apply.get(User32.VK_PA1);
        this.glTexCoord1s = apply.get(254);
        this.glTexCoord1i = apply.get(255);
        this.glTexCoord1d = apply.get(256);
        this.glTexCoord1fv = apply.get(257);
        this.glTexCoord1sv = apply.get(258);
        this.glTexCoord1iv = apply.get(259);
        this.glTexCoord1dv = apply.get(260);
        this.glTexCoord2f = apply.get(261);
        this.glTexCoord2s = apply.get(262);
        this.glTexCoord2i = apply.get(263);
        this.glTexCoord2d = apply.get(GLFW.GLFW_KEY_DOWN);
        this.glTexCoord2fv = apply.get(265);
        this.glTexCoord2sv = apply.get(GLFW.GLFW_KEY_PAGE_UP);
        this.glTexCoord2iv = apply.get(GLFW.GLFW_KEY_PAGE_DOWN);
        this.glTexCoord2dv = apply.get(GLFW.GLFW_KEY_HOME);
        this.glTexCoord3f = apply.get(269);
        this.glTexCoord3s = apply.get(User32.WM_IME_ENDCOMPOSITION);
        this.glTexCoord3i = apply.get(271);
        this.glTexCoord3d = apply.get(272);
        this.glTexCoord3fv = apply.get(273);
        this.glTexCoord3sv = apply.get(User32.WM_SYSCOMMAND);
        this.glTexCoord3iv = apply.get(User32.WM_TIMER);
        this.glTexCoord3dv = apply.get(User32.WM_HSCROLL);
        this.glTexCoord4f = apply.get(User32.WM_VSCROLL);
        this.glTexCoord4s = apply.get(User32.WM_INITMENU);
        this.glTexCoord4i = apply.get(User32.WM_INITMENUPOPUP);
        this.glTexCoord4d = apply.get(GLFW.GLFW_KEY_CAPS_LOCK);
        this.glTexCoord4fv = apply.get(281);
        this.glTexCoord4sv = apply.get(282);
        this.glTexCoord4iv = apply.get(GLFW.GLFW_KEY_PRINT_SCREEN);
        this.glTexCoord4dv = apply.get(GLFW.GLFW_KEY_PAUSE);
        this.glTexCoordPointer = apply.get(285);
        this.glTexEnvi = apply.get(286);
        this.glTexEnviv = apply.get(User32.WM_MENUSELECT);
        this.glTexEnvf = apply.get(User32.WM_MENUCHAR);
        this.glTexEnvfv = apply.get(User32.WM_ENTERIDLE);
        this.glTexGeni = apply.get(290);
        this.glTexGeniv = apply.get(291);
        this.glTexGenf = apply.get(292);
        this.glTexGenfv = apply.get(293);
        this.glTexGend = apply.get(294);
        this.glTexGendv = apply.get(295);
        this.glTexImage1D = apply.get(296);
        this.glTexImage2D = apply.get(297);
        this.glCopyTexImage1D = apply.get(GLFW.GLFW_KEY_F9);
        this.glCopyTexImage2D = apply.get(GLFW.GLFW_KEY_F10);
        this.glCopyTexSubImage1D = apply.get(300);
        this.glCopyTexSubImage2D = apply.get(301);
        this.glTexParameteri = apply.get(302);
        this.glTexParameteriv = apply.get(303);
        this.glTexParameterf = apply.get(304);
        this.glTexParameterfv = apply.get(305);
        this.glTexSubImage1D = apply.get(306);
        this.glTexSubImage2D = apply.get(307);
        this.glTranslatef = apply.get(308);
        this.glTranslated = apply.get(309);
        this.glVertex2f = apply.get(310);
        this.glVertex2s = apply.get(311);
        this.glVertex2i = apply.get(312);
        this.glVertex2d = apply.get(313);
        this.glVertex2fv = apply.get(314);
        this.glVertex2sv = apply.get(CGL.kCGLCPMPSwapsInFlight);
        this.glVertex2iv = apply.get(316);
        this.glVertex2dv = apply.get(317);
        this.glVertex3f = apply.get(318);
        this.glVertex3s = apply.get(319);
        this.glVertex3i = apply.get(GLFW.GLFW_KEY_KP_0);
        this.glVertex3d = apply.get(GLFW.GLFW_KEY_KP_1);
        this.glVertex3fv = apply.get(GLFW.GLFW_KEY_KP_2);
        this.glVertex3sv = apply.get(GLFW.GLFW_KEY_KP_3);
        this.glVertex3iv = apply.get(GLFW.GLFW_KEY_KP_4);
        this.glVertex3dv = apply.get(GLFW.GLFW_KEY_KP_5);
        this.glVertex4f = apply.get(GLFW.GLFW_KEY_KP_6);
        this.glVertex4s = apply.get(GLFW.GLFW_KEY_KP_7);
        this.glVertex4i = apply.get(GLFW.GLFW_KEY_KP_8);
        this.glVertex4d = apply.get(GLFW.GLFW_KEY_KP_9);
        this.glVertex4fv = apply.get(GLFW.GLFW_KEY_KP_DECIMAL);
        this.glVertex4sv = apply.get(GLFW.GLFW_KEY_KP_DIVIDE);
        this.glVertex4iv = apply.get(GLFW.GLFW_KEY_KP_MULTIPLY);
        this.glVertex4dv = apply.get(GLFW.GLFW_KEY_KP_SUBTRACT);
        this.glVertexPointer = apply.get(GLFW.GLFW_KEY_KP_ADD);
        this.glViewport = apply.get(GLFW.GLFW_KEY_KP_ENTER);
        this.glTexImage3D = apply.get(GLFW.GLFW_KEY_KP_EQUAL);
        this.glTexSubImage3D = apply.get(337);
        this.glCopyTexSubImage3D = apply.get(338);
        this.glDrawRangeElements = apply.get(339);
        this.glCompressedTexImage3D = apply.get(GLFW.GLFW_KEY_LEFT_SHIFT);
        this.glCompressedTexImage2D = apply.get(GLFW.GLFW_KEY_LEFT_CONTROL);
        this.glCompressedTexImage1D = apply.get(GLFW.GLFW_KEY_LEFT_ALT);
        this.glCompressedTexSubImage3D = apply.get(GLFW.GLFW_KEY_LEFT_SUPER);
        this.glCompressedTexSubImage2D = apply.get(GLFW.GLFW_KEY_RIGHT_SHIFT);
        this.glCompressedTexSubImage1D = apply.get(GLFW.GLFW_KEY_RIGHT_CONTROL);
        this.glGetCompressedTexImage = apply.get(GLFW.GLFW_KEY_RIGHT_ALT);
        this.glSampleCoverage = apply.get(GLFW.GLFW_KEY_RIGHT_SUPER);
        this.glActiveTexture = apply.get(348);
        this.glClientActiveTexture = apply.get(349);
        this.glMultiTexCoord1f = apply.get(350);
        this.glMultiTexCoord1s = apply.get(351);
        this.glMultiTexCoord1i = apply.get(MapEditorItemInfoMenu.MENU_CONTENT_WIDTH);
        this.glMultiTexCoord1d = apply.get(353);
        this.glMultiTexCoord1fv = apply.get(354);
        this.glMultiTexCoord1sv = apply.get(355);
        this.glMultiTexCoord1iv = apply.get(356);
        this.glMultiTexCoord1dv = apply.get(357);
        this.glMultiTexCoord2f = apply.get(358);
        this.glMultiTexCoord2s = apply.get(359);
        this.glMultiTexCoord2i = apply.get(360);
        this.glMultiTexCoord2d = apply.get(361);
        this.glMultiTexCoord2fv = apply.get(362);
        this.glMultiTexCoord2sv = apply.get(363);
        this.glMultiTexCoord2iv = apply.get(364);
        this.glMultiTexCoord2dv = apply.get(365);
        this.glMultiTexCoord3f = apply.get(366);
        this.glMultiTexCoord3s = apply.get(367);
        this.glMultiTexCoord3i = apply.get(368);
        this.glMultiTexCoord3d = apply.get(369);
        this.glMultiTexCoord3fv = apply.get(370);
        this.glMultiTexCoord3sv = apply.get(371);
        this.glMultiTexCoord3iv = apply.get(372);
        this.glMultiTexCoord3dv = apply.get(373);
        this.glMultiTexCoord4f = apply.get(374);
        this.glMultiTexCoord4s = apply.get(375);
        this.glMultiTexCoord4i = apply.get(376);
        this.glMultiTexCoord4d = apply.get(377);
        this.glMultiTexCoord4fv = apply.get(378);
        this.glMultiTexCoord4sv = apply.get(379);
        this.glMultiTexCoord4iv = apply.get(380);
        this.glMultiTexCoord4dv = apply.get(381);
        this.glLoadTransposeMatrixf = apply.get(382);
        this.glLoadTransposeMatrixd = apply.get(383);
        this.glMultTransposeMatrixf = apply.get(384);
        this.glMultTransposeMatrixd = apply.get(385);
        this.glBlendColor = apply.get(386);
        this.glBlendEquation = apply.get(387);
        this.glFogCoordf = apply.get(388);
        this.glFogCoordd = apply.get(389);
        this.glFogCoordfv = apply.get(390);
        this.glFogCoorddv = apply.get(391);
        this.glFogCoordPointer = apply.get(User32.WS_EX_PALETTEWINDOW);
        this.glMultiDrawArrays = apply.get(393);
        this.glMultiDrawElements = apply.get(394);
        this.glPointParameterf = apply.get(395);
        this.glPointParameteri = apply.get(396);
        this.glPointParameterfv = apply.get(397);
        this.glPointParameteriv = apply.get(398);
        this.glSecondaryColor3b = apply.get(399);
        this.glSecondaryColor3s = apply.get(400);
        this.glSecondaryColor3i = apply.get(HttpStatus.SC_UNAUTHORIZED);
        this.glSecondaryColor3f = apply.get(HttpStatus.SC_PAYMENT_REQUIRED);
        this.glSecondaryColor3d = apply.get(HttpStatus.SC_FORBIDDEN);
        this.glSecondaryColor3ub = apply.get(HttpStatus.SC_NOT_FOUND);
        this.glSecondaryColor3us = apply.get(HttpStatus.SC_METHOD_NOT_ALLOWED);
        this.glSecondaryColor3ui = apply.get(HttpStatus.SC_NOT_ACCEPTABLE);
        this.glSecondaryColor3bv = apply.get(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED);
        this.glSecondaryColor3sv = apply.get(HttpStatus.SC_REQUEST_TIMEOUT);
        this.glSecondaryColor3iv = apply.get(HttpStatus.SC_CONFLICT);
        this.glSecondaryColor3fv = apply.get(HttpStatus.SC_GONE);
        this.glSecondaryColor3dv = apply.get(HttpStatus.SC_LENGTH_REQUIRED);
        this.glSecondaryColor3ubv = apply.get(HttpStatus.SC_PRECONDITION_FAILED);
        this.glSecondaryColor3usv = apply.get(HttpStatus.SC_REQUEST_TOO_LONG);
        this.glSecondaryColor3uiv = apply.get(HttpStatus.SC_REQUEST_URI_TOO_LONG);
        this.glSecondaryColorPointer = apply.get(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        this.glBlendFuncSeparate = apply.get(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
        this.glWindowPos2i = apply.get(HttpStatus.SC_EXPECTATION_FAILED);
        this.glWindowPos2s = apply.get(418);
        this.glWindowPos2f = apply.get(HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE);
        this.glWindowPos2d = apply.get(HttpStatus.SC_METHOD_FAILURE);
        this.glWindowPos2iv = apply.get(421);
        this.glWindowPos2sv = apply.get(HttpStatus.SC_UNPROCESSABLE_ENTITY);
        this.glWindowPos2fv = apply.get(HttpStatus.SC_LOCKED);
        this.glWindowPos2dv = apply.get(HttpStatus.SC_FAILED_DEPENDENCY);
        this.glWindowPos3i = apply.get(425);
        this.glWindowPos3s = apply.get(426);
        this.glWindowPos3f = apply.get(427);
        this.glWindowPos3d = apply.get(428);
        this.glWindowPos3iv = apply.get(429);
        this.glWindowPos3sv = apply.get(430);
        this.glWindowPos3fv = apply.get(431);
        this.glWindowPos3dv = apply.get(432);
        this.glBindBuffer = apply.get(433);
        this.glDeleteBuffers = apply.get(434);
        this.glGenBuffers = apply.get(435);
        this.glIsBuffer = apply.get(436);
        this.glBufferData = apply.get(437);
        this.glBufferSubData = apply.get(438);
        this.glGetBufferSubData = apply.get(439);
        this.glMapBuffer = apply.get(440);
        this.glUnmapBuffer = apply.get(441);
        this.glGetBufferParameteriv = apply.get(442);
        this.glGetBufferPointerv = apply.get(443);
        this.glGenQueries = apply.get(444);
        this.glDeleteQueries = apply.get(445);
        this.glIsQuery = apply.get(446);
        this.glBeginQuery = apply.get(447);
        this.glEndQuery = apply.get(FCNTL.S_IRWXU);
        this.glGetQueryiv = apply.get(449);
        this.glGetQueryObjectiv = apply.get(450);
        this.glGetQueryObjectuiv = apply.get(451);
        this.glCreateProgram = apply.get(452);
        this.glDeleteProgram = apply.get(453);
        this.glIsProgram = apply.get(454);
        this.glCreateShader = apply.get(455);
        this.glDeleteShader = apply.get(456);
        this.glIsShader = apply.get(457);
        this.glAttachShader = apply.get(458);
        this.glDetachShader = apply.get(459);
        this.glShaderSource = apply.get(MusicListOverlay.MusicItem.AMPLITUDES_PREVIEW_WIDTH);
        this.glCompileShader = apply.get(461);
        this.glLinkProgram = apply.get(462);
        this.glUseProgram = apply.get(463);
        this.glValidateProgram = apply.get(464);
        this.glUniform1f = apply.get(465);
        this.glUniform2f = apply.get(466);
        this.glUniform3f = apply.get(467);
        this.glUniform4f = apply.get(468);
        this.glUniform1i = apply.get(469);
        this.glUniform2i = apply.get(470);
        this.glUniform3i = apply.get(471);
        this.glUniform4i = apply.get(472);
        this.glUniform1fv = apply.get(473);
        this.glUniform2fv = apply.get(474);
        this.glUniform3fv = apply.get(475);
        this.glUniform4fv = apply.get(476);
        this.glUniform1iv = apply.get(477);
        this.glUniform2iv = apply.get(478);
        this.glUniform3iv = apply.get(479);
        this.glUniform4iv = apply.get(480);
        this.glUniformMatrix2fv = apply.get(User32.MN_GETHMENU);
        this.glUniformMatrix3fv = apply.get(482);
        this.glUniformMatrix4fv = apply.get(483);
        this.glGetShaderiv = apply.get(484);
        this.glGetProgramiv = apply.get(485);
        this.glGetShaderInfoLog = apply.get(486);
        this.glGetProgramInfoLog = apply.get(487);
        this.glGetAttachedShaders = apply.get(488);
        this.glGetUniformLocation = apply.get(489);
        this.glGetActiveUniform = apply.get(490);
        this.glGetUniformfv = apply.get(491);
        this.glGetUniformiv = apply.get(492);
        this.glGetShaderSource = apply.get(493);
        this.glVertexAttrib1f = apply.get(494);
        this.glVertexAttrib1s = apply.get(495);
        this.glVertexAttrib1d = apply.get(496);
        this.glVertexAttrib2f = apply.get(497);
        this.glVertexAttrib2s = apply.get(498);
        this.glVertexAttrib2d = apply.get(499);
        this.glVertexAttrib3f = apply.get(500);
        this.glVertexAttrib3s = apply.get(501);
        this.glVertexAttrib3d = apply.get(502);
        this.glVertexAttrib4f = apply.get(503);
        this.glVertexAttrib4s = apply.get(504);
        this.glVertexAttrib4d = apply.get(505);
        this.glVertexAttrib4Nub = apply.get(CGL.kCGLGOUseBuildCache);
        this.glVertexAttrib1fv = apply.get(HttpStatus.SC_INSUFFICIENT_STORAGE);
        this.glVertexAttrib1sv = apply.get(508);
        this.glVertexAttrib1dv = apply.get(509);
        this.glVertexAttrib2fv = apply.get(510);
        this.glVertexAttrib2sv = apply.get(511);
        this.glVertexAttrib2dv = apply.get(512);
        this.glVertexAttrib3fv = apply.get(513);
        this.glVertexAttrib3sv = apply.get(514);
        this.glVertexAttrib3dv = apply.get(515);
        this.glVertexAttrib4fv = apply.get(516);
        this.glVertexAttrib4sv = apply.get(517);
        this.glVertexAttrib4dv = apply.get(518);
        this.glVertexAttrib4iv = apply.get(519);
        this.glVertexAttrib4bv = apply.get(User32.WM_MBUTTONUP);
        this.glVertexAttrib4ubv = apply.get(User32.WM_MBUTTONDBLCLK);
        this.glVertexAttrib4usv = apply.get(User32.WM_MOUSEWHEEL);
        this.glVertexAttrib4uiv = apply.get(User32.WM_XBUTTONDOWN);
        this.glVertexAttrib4Nbv = apply.get(User32.WM_XBUTTONUP);
        this.glVertexAttrib4Nsv = apply.get(User32.WM_XBUTTONDBLCLK);
        this.glVertexAttrib4Niv = apply.get(User32.WM_MOUSEHWHEEL);
        this.glVertexAttrib4Nubv = apply.get(527);
        this.glVertexAttrib4Nusv = apply.get(User32.WM_PARENTNOTIFY);
        this.glVertexAttrib4Nuiv = apply.get(User32.WM_ENTERMENULOOP);
        this.glVertexAttribPointer = apply.get(User32.WM_EXITMENULOOP);
        this.glEnableVertexAttribArray = apply.get(User32.WM_NEXTMENU);
        this.glDisableVertexAttribArray = apply.get(User32.WM_SIZING);
        this.glBindAttribLocation = apply.get(User32.WM_CAPTURECHANGED);
        this.glGetActiveAttrib = apply.get(User32.WM_MOVING);
        this.glGetAttribLocation = apply.get(535);
        this.glGetVertexAttribiv = apply.get(User32.WM_POWERBROADCAST);
        this.glGetVertexAttribfv = apply.get(User32.WM_DEVICECHANGE);
        this.glGetVertexAttribdv = apply.get(538);
        this.glGetVertexAttribPointerv = apply.get(539);
        this.glDrawBuffers = apply.get(540);
        this.glBlendEquationSeparate = apply.get(541);
        this.glStencilOpSeparate = apply.get(542);
        this.glStencilFuncSeparate = apply.get(543);
        this.glStencilMaskSeparate = apply.get(User32.WM_MDICREATE);
        this.glUniformMatrix2x3fv = apply.get(User32.WM_MDIDESTROY);
        this.glUniformMatrix3x2fv = apply.get(User32.WM_MDIACTIVATE);
        this.glUniformMatrix2x4fv = apply.get(User32.WM_MDIRESTORE);
        this.glUniformMatrix4x2fv = apply.get(User32.WM_MDINEXT);
        this.glUniformMatrix3x4fv = apply.get(User32.WM_MDIMAXIMIZE);
        this.glUniformMatrix4x3fv = apply.get(User32.WM_MDITILE);
        this.glGetStringi = apply.get(User32.WM_MDICASCADE);
        this.glClearBufferiv = apply.get(User32.WM_MDIICONARRANGE);
        this.glClearBufferuiv = apply.get(User32.WM_MDIGETACTIVE);
        this.glClearBufferfv = apply.get(554);
        this.glClearBufferfi = apply.get(555);
        this.glVertexAttribI1i = apply.get(556);
        this.glVertexAttribI2i = apply.get(557);
        this.glVertexAttribI3i = apply.get(558);
        this.glVertexAttribI4i = apply.get(559);
        this.glVertexAttribI1ui = apply.get(User32.WM_MDISETMENU);
        this.glVertexAttribI2ui = apply.get(User32.WM_ENTERSIZEMOVE);
        this.glVertexAttribI3ui = apply.get(User32.WM_EXITSIZEMOVE);
        this.glVertexAttribI4ui = apply.get(User32.WM_DROPFILES);
        this.glVertexAttribI1iv = apply.get(User32.WM_MDIREFRESHMENU);
        this.glVertexAttribI2iv = apply.get(565);
        this.glVertexAttribI3iv = apply.get(566);
        this.glVertexAttribI4iv = apply.get(567);
        this.glVertexAttribI1uiv = apply.get(568);
        this.glVertexAttribI2uiv = apply.get(569);
        this.glVertexAttribI3uiv = apply.get(570);
        this.glVertexAttribI4uiv = apply.get(571);
        this.glVertexAttribI4bv = apply.get(572);
        this.glVertexAttribI4sv = apply.get(573);
        this.glVertexAttribI4ubv = apply.get(574);
        this.glVertexAttribI4usv = apply.get(575);
        this.glVertexAttribIPointer = apply.get(User32.WM_TOUCH);
        this.glGetVertexAttribIiv = apply.get(577);
        this.glGetVertexAttribIuiv = apply.get(578);
        this.glUniform1ui = apply.get(579);
        this.glUniform2ui = apply.get(580);
        this.glUniform3ui = apply.get(581);
        this.glUniform4ui = apply.get(582);
        this.glUniform1uiv = apply.get(583);
        this.glUniform2uiv = apply.get(584);
        this.glUniform3uiv = apply.get(BlizzardAbility.ICE_FIELD_LIFETIME_MIN);
        this.glUniform4uiv = apply.get(586);
        this.glGetUniformuiv = apply.get(587);
        this.glBindFragDataLocation = apply.get(588);
        this.glGetFragDataLocation = apply.get(589);
        this.glBeginConditionalRender = apply.get(590);
        this.glEndConditionalRender = apply.get(591);
        this.glMapBufferRange = apply.get(592);
        this.glFlushMappedBufferRange = apply.get(593);
        this.glClampColor = apply.get(594);
        this.glIsRenderbuffer = apply.get(595);
        this.glBindRenderbuffer = apply.get(596);
        this.glDeleteRenderbuffers = apply.get(597);
        this.glGenRenderbuffers = apply.get(598);
        this.glRenderbufferStorage = apply.get(599);
        this.glRenderbufferStorageMultisample = apply.get(600);
        this.glGetRenderbufferParameteriv = apply.get(601);
        this.glIsFramebuffer = apply.get(602);
        this.glBindFramebuffer = apply.get(603);
        this.glDeleteFramebuffers = apply.get(604);
        this.glGenFramebuffers = apply.get(605);
        this.glCheckFramebufferStatus = apply.get(606);
        this.glFramebufferTexture1D = apply.get(607);
        this.glFramebufferTexture2D = apply.get(608);
        this.glFramebufferTexture3D = apply.get(609);
        this.glFramebufferTextureLayer = apply.get(610);
        this.glFramebufferRenderbuffer = apply.get(611);
        this.glGetFramebufferAttachmentParameteriv = apply.get(612);
        this.glBlitFramebuffer = apply.get(613);
        this.glGenerateMipmap = apply.get(614);
        this.glTexParameterIiv = apply.get(BlizzardAbility.ICE_FIELD_LIFETIME_MAX);
        this.glTexParameterIuiv = apply.get(616);
        this.glGetTexParameterIiv = apply.get(617);
        this.glGetTexParameterIuiv = apply.get(618);
        this.glColorMaski = apply.get(619);
        this.glGetBooleani_v = apply.get(620);
        this.glGetIntegeri_v = apply.get(621);
        this.glEnablei = apply.get(622);
        this.glDisablei = apply.get(623);
        this.glIsEnabledi = apply.get(624);
        this.glBindBufferRange = apply.get(625);
        this.glBindBufferBase = apply.get(626);
        this.glBeginTransformFeedback = apply.get(627);
        this.glEndTransformFeedback = apply.get(628);
        this.glTransformFeedbackVaryings = apply.get(629);
        this.glGetTransformFeedbackVarying = apply.get(630);
        this.glBindVertexArray = apply.get(631);
        this.glDeleteVertexArrays = apply.get(632);
        this.glGenVertexArrays = apply.get(633);
        this.glIsVertexArray = apply.get(634);
        this.glDrawArraysInstanced = apply.get(635);
        this.glDrawElementsInstanced = apply.get(636);
        this.glCopyBufferSubData = apply.get(637);
        this.glPrimitiveRestartIndex = apply.get(638);
        this.glTexBuffer = apply.get(639);
        this.glGetUniformIndices = apply.get(640);
        this.glGetActiveUniformsiv = apply.get(User32.WM_IME_SETCONTEXT);
        this.glGetActiveUniformName = apply.get(User32.WM_IME_NOTIFY);
        this.glGetUniformBlockIndex = apply.get(User32.WM_IME_CONTROL);
        this.glGetActiveUniformBlockiv = apply.get(User32.WM_IME_COMPOSITIONFULL);
        this.glGetActiveUniformBlockName = apply.get(User32.WM_IME_SELECT);
        this.glUniformBlockBinding = apply.get(User32.WM_IME_CHAR);
        this.glGetBufferParameteri64v = apply.get(647);
        this.glDrawElementsBaseVertex = apply.get(User32.WM_IME_REQUEST);
        this.glDrawRangeElementsBaseVertex = apply.get(649);
        this.glDrawElementsInstancedBaseVertex = apply.get(650);
        this.glMultiDrawElementsBaseVertex = apply.get(651);
        this.glProvokingVertex = apply.get(652);
        this.glTexImage2DMultisample = apply.get(653);
        this.glTexImage3DMultisample = apply.get(654);
        this.glGetMultisamplefv = apply.get(655);
        this.glSampleMaski = apply.get(User32.WM_IME_KEYDOWN);
        this.glFramebufferTexture = apply.get(User32.WM_IME_KEYUP);
        this.glFenceSync = apply.get(658);
        this.glIsSync = apply.get(659);
        this.glDeleteSync = apply.get(660);
        this.glClientWaitSync = apply.get(661);
        this.glWaitSync = apply.get(662);
        this.glGetInteger64v = apply.get(663);
        this.glGetInteger64i_v = apply.get(664);
        this.glGetSynciv = apply.get(665);
        this.glBindFragDataLocationIndexed = apply.get(666);
        this.glGetFragDataIndex = apply.get(667);
        this.glGenSamplers = apply.get(668);
        this.glDeleteSamplers = apply.get(669);
        this.glIsSampler = apply.get(670);
        this.glBindSampler = apply.get(671);
        this.glSamplerParameteri = apply.get(User32.WM_NCMOUSEHOVER);
        this.glSamplerParameterf = apply.get(User32.WM_MOUSEHOVER);
        this.glSamplerParameteriv = apply.get(User32.WM_NCMOUSELEAVE);
        this.glSamplerParameterfv = apply.get(User32.WM_MOUSELEAVE);
        this.glSamplerParameterIiv = apply.get(676);
        this.glSamplerParameterIuiv = apply.get(677);
        this.glGetSamplerParameteriv = apply.get(678);
        this.glGetSamplerParameterfv = apply.get(679);
        this.glGetSamplerParameterIiv = apply.get(680);
        this.glGetSamplerParameterIuiv = apply.get(681);
        this.glQueryCounter = apply.get(682);
        this.glGetQueryObjecti64v = apply.get(683);
        this.glGetQueryObjectui64v = apply.get(684);
        this.glVertexAttribDivisor = apply.get(685);
        this.glVertexP2ui = apply.get(686);
        this.glVertexP3ui = apply.get(687);
        this.glVertexP4ui = apply.get(688);
        this.glVertexP2uiv = apply.get(User32.WM_WTSSESSION_CHANGE);
        this.glVertexP3uiv = apply.get(690);
        this.glVertexP4uiv = apply.get(691);
        this.glTexCoordP1ui = apply.get(692);
        this.glTexCoordP2ui = apply.get(693);
        this.glTexCoordP3ui = apply.get(694);
        this.glTexCoordP4ui = apply.get(695);
        this.glTexCoordP1uiv = apply.get(696);
        this.glTexCoordP2uiv = apply.get(697);
        this.glTexCoordP3uiv = apply.get(698);
        this.glTexCoordP4uiv = apply.get(699);
        this.glMultiTexCoordP1ui = apply.get(700);
        this.glMultiTexCoordP2ui = apply.get(701);
        this.glMultiTexCoordP3ui = apply.get(702);
        this.glMultiTexCoordP4ui = apply.get(703);
        this.glMultiTexCoordP1uiv = apply.get(User32.WM_TABLET_FIRST);
        this.glMultiTexCoordP2uiv = apply.get(705);
        this.glMultiTexCoordP3uiv = apply.get(706);
        this.glMultiTexCoordP4uiv = apply.get(707);
        this.glNormalP3ui = apply.get(708);
        this.glNormalP3uiv = apply.get(709);
        this.glColorP3ui = apply.get(710);
        this.glColorP4ui = apply.get(711);
        this.glColorP3uiv = apply.get(712);
        this.glColorP4uiv = apply.get(713);
        this.glSecondaryColorP3ui = apply.get(714);
        this.glSecondaryColorP3uiv = apply.get(715);
        this.glVertexAttribP1ui = apply.get(716);
        this.glVertexAttribP2ui = apply.get(717);
        this.glVertexAttribP3ui = apply.get(718);
        this.glVertexAttribP4ui = apply.get(719);
        this.glVertexAttribP1uiv = apply.get(720);
        this.glVertexAttribP2uiv = apply.get(721);
        this.glVertexAttribP3uiv = apply.get(722);
        this.glVertexAttribP4uiv = apply.get(723);
        this.glBlendEquationi = apply.get(724);
        this.glBlendEquationSeparatei = apply.get(725);
        this.glBlendFunci = apply.get(726);
        this.glBlendFuncSeparatei = apply.get(727);
        this.glDrawArraysIndirect = apply.get(728);
        this.glDrawElementsIndirect = apply.get(729);
        this.glUniform1d = apply.get(730);
        this.glUniform2d = apply.get(731);
        this.glUniform3d = apply.get(732);
        this.glUniform4d = apply.get(733);
        this.glUniform1dv = apply.get(734);
        this.glUniform2dv = apply.get(User32.WM_TABLET_LAST);
        this.glUniform3dv = apply.get(736);
        this.glUniform4dv = apply.get(737);
        this.glUniformMatrix2dv = apply.get(738);
        this.glUniformMatrix3dv = apply.get(739);
        this.glUniformMatrix4dv = apply.get(740);
        this.glUniformMatrix2x3dv = apply.get(741);
        this.glUniformMatrix2x4dv = apply.get(742);
        this.glUniformMatrix3x2dv = apply.get(743);
        this.glUniformMatrix3x4dv = apply.get(744);
        this.glUniformMatrix4x2dv = apply.get(745);
        this.glUniformMatrix4x3dv = apply.get(746);
        this.glGetUniformdv = apply.get(747);
        this.glMinSampleShading = apply.get(748);
        this.glGetSubroutineUniformLocation = apply.get(749);
        this.glGetSubroutineIndex = apply.get(750);
        this.glGetActiveSubroutineUniformiv = apply.get(751);
        this.glGetActiveSubroutineUniformName = apply.get(752);
        this.glGetActiveSubroutineName = apply.get(753);
        this.glUniformSubroutinesuiv = apply.get(754);
        this.glGetUniformSubroutineuiv = apply.get(755);
        this.glGetProgramStageiv = apply.get(756);
        this.glPatchParameteri = apply.get(757);
        this.glPatchParameterfv = apply.get(758);
        this.glBindTransformFeedback = apply.get(759);
        this.glDeleteTransformFeedbacks = apply.get(760);
        this.glGenTransformFeedbacks = apply.get(761);
        this.glIsTransformFeedback = apply.get(762);
        this.glPauseTransformFeedback = apply.get(763);
        this.glResumeTransformFeedback = apply.get(764);
        this.glDrawTransformFeedback = apply.get(765);
        this.glDrawTransformFeedbackStream = apply.get(766);
        this.glBeginQueryIndexed = apply.get(767);
        this.glEndQueryIndexed = apply.get(768);
        this.glGetQueryIndexediv = apply.get(769);
        this.glReleaseShaderCompiler = apply.get(770);
        this.glShaderBinary = apply.get(771);
        this.glGetShaderPrecisionFormat = apply.get(772);
        this.glDepthRangef = apply.get(773);
        this.glClearDepthf = apply.get(774);
        this.glGetProgramBinary = apply.get(775);
        this.glProgramBinary = apply.get(776);
        this.glProgramParameteri = apply.get(User32.WM_PAINTCLIPBOARD);
        this.glUseProgramStages = apply.get(User32.WM_VSCROLLCLIPBOARD);
        this.glActiveShaderProgram = apply.get(User32.WM_SIZECLIPBOARD);
        this.glCreateShaderProgramv = apply.get(User32.WM_ASKCBFORMATNAME);
        this.glBindProgramPipeline = apply.get(User32.WM_CHANGECBCHAIN);
        this.glDeleteProgramPipelines = apply.get(User32.WM_HSCROLLCLIPBOARD);
        this.glGenProgramPipelines = apply.get(User32.WM_QUERYNEWPALETTE);
        this.glIsProgramPipeline = apply.get(784);
        this.glGetProgramPipelineiv = apply.get(785);
        this.glProgramUniform1i = apply.get(786);
        this.glProgramUniform2i = apply.get(EXTDisconnect.ALC_CONNECTED);
        this.glProgramUniform3i = apply.get(788);
        this.glProgramUniform4i = apply.get(789);
        this.glProgramUniform1ui = apply.get(790);
        this.glProgramUniform2ui = apply.get(User32.WM_PRINT);
        this.glProgramUniform3ui = apply.get(User32.WM_PRINTCLIENT);
        this.glProgramUniform4ui = apply.get(User32.WM_APPCOMMAND);
        this.glProgramUniform1f = apply.get(User32.WM_THEMECHANGED);
        this.glProgramUniform2f = apply.get(795);
        this.glProgramUniform3f = apply.get(796);
        this.glProgramUniform4f = apply.get(User32.WM_CLIPBOARDUPDATE);
        this.glProgramUniform1d = apply.get(User32.WM_DWMCOMPOSITIONCHANGED);
        this.glProgramUniform2d = apply.get(User32.WM_DWMNCRENDERINGCHANGED);
        this.glProgramUniform3d = apply.get(User32.WM_DWMCOLORIZATIONCOLORCHANGED);
        this.glProgramUniform4d = apply.get(User32.WM_DWMWINDOWMAXIMIZEDCHANGE);
        this.glProgramUniform1iv = apply.get(802);
        this.glProgramUniform2iv = apply.get(User32.WM_DWMSENDICONICTHUMBNAIL);
        this.glProgramUniform3iv = apply.get(804);
        this.glProgramUniform4iv = apply.get(805);
        this.glProgramUniform1uiv = apply.get(User32.WM_DWMSENDICONICLIVEPREVIEWBITMAP);
        this.glProgramUniform2uiv = apply.get(807);
        this.glProgramUniform3uiv = apply.get(808);
        this.glProgramUniform4uiv = apply.get(809);
        this.glProgramUniform1fv = apply.get(810);
        this.glProgramUniform2fv = apply.get(811);
        this.glProgramUniform3fv = apply.get(812);
        this.glProgramUniform4fv = apply.get(813);
        this.glProgramUniform1dv = apply.get(814);
        this.glProgramUniform2dv = apply.get(815);
        this.glProgramUniform3dv = apply.get(816);
        this.glProgramUniform4dv = apply.get(817);
        this.glProgramUniformMatrix2fv = apply.get(818);
        this.glProgramUniformMatrix3fv = apply.get(819);
        this.glProgramUniformMatrix4fv = apply.get(820);
        this.glProgramUniformMatrix2dv = apply.get(821);
        this.glProgramUniformMatrix3dv = apply.get(822);
        this.glProgramUniformMatrix4dv = apply.get(823);
        this.glProgramUniformMatrix2x3fv = apply.get(824);
        this.glProgramUniformMatrix3x2fv = apply.get(825);
        this.glProgramUniformMatrix2x4fv = apply.get(826);
        this.glProgramUniformMatrix4x2fv = apply.get(827);
        this.glProgramUniformMatrix3x4fv = apply.get(828);
        this.glProgramUniformMatrix4x3fv = apply.get(829);
        this.glProgramUniformMatrix2x3dv = apply.get(830);
        this.glProgramUniformMatrix3x2dv = apply.get(User32.WM_GETTITLEBARINFOEX);
        this.glProgramUniformMatrix2x4dv = apply.get(832);
        this.glProgramUniformMatrix4x2dv = apply.get(833);
        this.glProgramUniformMatrix3x4dv = apply.get(834);
        this.glProgramUniformMatrix4x3dv = apply.get(835);
        this.glValidateProgramPipeline = apply.get(836);
        this.glGetProgramPipelineInfoLog = apply.get(837);
        this.glVertexAttribL1d = apply.get(838);
        this.glVertexAttribL2d = apply.get(839);
        this.glVertexAttribL3d = apply.get(840);
        this.glVertexAttribL4d = apply.get(841);
        this.glVertexAttribL1dv = apply.get(842);
        this.glVertexAttribL2dv = apply.get(843);
        this.glVertexAttribL3dv = apply.get(844);
        this.glVertexAttribL4dv = apply.get(845);
        this.glVertexAttribLPointer = apply.get(846);
        this.glGetVertexAttribLdv = apply.get(847);
        this.glViewportArrayv = apply.get(848);
        this.glViewportIndexedf = apply.get(849);
        this.glViewportIndexedfv = apply.get(850);
        this.glScissorArrayv = apply.get(851);
        this.glScissorIndexed = apply.get(852);
        this.glScissorIndexedv = apply.get(853);
        this.glDepthRangeArrayv = apply.get(854);
        this.glDepthRangeIndexed = apply.get(855);
        this.glGetFloati_v = apply.get(User32.WM_HANDHELDFIRST);
        this.glGetDoublei_v = apply.get(857);
        this.glGetActiveAtomicCounterBufferiv = apply.get(858);
        this.glTexStorage1D = apply.get(859);
        this.glTexStorage2D = apply.get(860);
        this.glTexStorage3D = apply.get(861);
        this.glDrawTransformFeedbackInstanced = apply.get(862);
        this.glDrawTransformFeedbackStreamInstanced = apply.get(User32.WM_HANDHELDLAST);
        this.glDrawArraysInstancedBaseInstance = apply.get(User32.WM_AFXFIRST);
        this.glDrawElementsInstancedBaseInstance = apply.get(865);
        this.glDrawElementsInstancedBaseVertexBaseInstance = apply.get(866);
        this.glBindImageTexture = apply.get(867);
        this.glMemoryBarrier = apply.get(868);
        this.glGetInternalformativ = apply.get(869);
        this.glClearBufferData = apply.get(870);
        this.glClearBufferSubData = apply.get(871);
        this.glDispatchCompute = apply.get(872);
        this.glDispatchComputeIndirect = apply.get(873);
        this.glCopyImageSubData = apply.get(874);
        this.glDebugMessageControl = apply.get(875);
        this.glDebugMessageInsert = apply.get(876);
        this.glDebugMessageCallback = apply.get(877);
        this.glGetDebugMessageLog = apply.get(878);
        this.glPushDebugGroup = apply.get(879);
        this.glPopDebugGroup = apply.get(880);
        this.glObjectLabel = apply.get(881);
        this.glGetObjectLabel = apply.get(882);
        this.glObjectPtrLabel = apply.get(883);
        this.glGetObjectPtrLabel = apply.get(884);
        this.glFramebufferParameteri = apply.get(885);
        this.glGetFramebufferParameteriv = apply.get(886);
        this.glGetInternalformati64v = apply.get(887);
        this.glInvalidateTexSubImage = apply.get(888);
        this.glInvalidateTexImage = apply.get(889);
        this.glInvalidateBufferSubData = apply.get(890);
        this.glInvalidateBufferData = apply.get(891);
        this.glInvalidateFramebuffer = apply.get(892);
        this.glInvalidateSubFramebuffer = apply.get(893);
        this.glMultiDrawArraysIndirect = apply.get(894);
        this.glMultiDrawElementsIndirect = apply.get(User32.WM_AFXLAST);
        this.glGetProgramInterfaceiv = apply.get(User32.WM_PENWINFIRST);
        this.glGetProgramResourceIndex = apply.get(897);
        this.glGetProgramResourceName = apply.get(898);
        this.glGetProgramResourceiv = apply.get(899);
        this.glGetProgramResourceLocation = apply.get(900);
        this.glGetProgramResourceLocationIndex = apply.get(901);
        this.glShaderStorageBlockBinding = apply.get(902);
        this.glTexBufferRange = apply.get(903);
        this.glTexStorage2DMultisample = apply.get(904);
        this.glTexStorage3DMultisample = apply.get(905);
        this.glTextureView = apply.get(906);
        this.glBindVertexBuffer = apply.get(907);
        this.glVertexAttribFormat = apply.get(908);
        this.glVertexAttribIFormat = apply.get(909);
        this.glVertexAttribLFormat = apply.get(910);
        this.glVertexAttribBinding = apply.get(User32.WM_PENWINLAST);
        this.glVertexBindingDivisor = apply.get(912);
        this.glBufferStorage = apply.get(913);
        this.glClearTexSubImage = apply.get(914);
        this.glClearTexImage = apply.get(915);
        this.glBindBuffersBase = apply.get(916);
        this.glBindBuffersRange = apply.get(917);
        this.glBindTextures = apply.get(918);
        this.glBindSamplers = apply.get(919);
        this.glBindImageTextures = apply.get(920);
        this.glBindVertexBuffers = apply.get(921);
        this.glClipControl = apply.get(922);
        this.glCreateTransformFeedbacks = apply.get(923);
        this.glTransformFeedbackBufferBase = apply.get(924);
        this.glTransformFeedbackBufferRange = apply.get(925);
        this.glGetTransformFeedbackiv = apply.get(926);
        this.glGetTransformFeedbacki_v = apply.get(927);
        this.glGetTransformFeedbacki64_v = apply.get(928);
        this.glCreateBuffers = apply.get(929);
        this.glNamedBufferStorage = apply.get(930);
        this.glNamedBufferData = apply.get(931);
        this.glNamedBufferSubData = apply.get(932);
        this.glCopyNamedBufferSubData = apply.get(933);
        this.glClearNamedBufferData = apply.get(934);
        this.glClearNamedBufferSubData = apply.get(935);
        this.glMapNamedBuffer = apply.get(936);
        this.glMapNamedBufferRange = apply.get(937);
        this.glUnmapNamedBuffer = apply.get(938);
        this.glFlushMappedNamedBufferRange = apply.get(939);
        this.glGetNamedBufferParameteriv = apply.get(940);
        this.glGetNamedBufferParameteri64v = apply.get(941);
        this.glGetNamedBufferPointerv = apply.get(942);
        this.glGetNamedBufferSubData = apply.get(943);
        this.glCreateFramebuffers = apply.get(944);
        this.glNamedFramebufferRenderbuffer = apply.get(945);
        this.glNamedFramebufferParameteri = apply.get(946);
        this.glNamedFramebufferTexture = apply.get(947);
        this.glNamedFramebufferTextureLayer = apply.get(948);
        this.glNamedFramebufferDrawBuffer = apply.get(949);
        this.glNamedFramebufferDrawBuffers = apply.get(950);
        this.glNamedFramebufferReadBuffer = apply.get(951);
        this.glInvalidateNamedFramebufferData = apply.get(952);
        this.glInvalidateNamedFramebufferSubData = apply.get(953);
        this.glClearNamedFramebufferiv = apply.get(954);
        this.glClearNamedFramebufferuiv = apply.get(955);
        this.glClearNamedFramebufferfv = apply.get(956);
        this.glClearNamedFramebufferfi = apply.get(957);
        this.glBlitNamedFramebuffer = apply.get(958);
        this.glCheckNamedFramebufferStatus = apply.get(959);
        this.glGetNamedFramebufferParameteriv = apply.get(960);
        this.glGetNamedFramebufferAttachmentParameteriv = apply.get(961);
        this.glCreateRenderbuffers = apply.get(962);
        this.glNamedRenderbufferStorage = apply.get(963);
        this.glNamedRenderbufferStorageMultisample = apply.get(964);
        this.glGetNamedRenderbufferParameteriv = apply.get(965);
        this.glCreateTextures = apply.get(966);
        this.glTextureBuffer = apply.get(967);
        this.glTextureBufferRange = apply.get(968);
        this.glTextureStorage1D = apply.get(969);
        this.glTextureStorage2D = apply.get(970);
        this.glTextureStorage3D = apply.get(971);
        this.glTextureStorage2DMultisample = apply.get(972);
        this.glTextureStorage3DMultisample = apply.get(973);
        this.glTextureSubImage1D = apply.get(974);
        this.glTextureSubImage2D = apply.get(975);
        this.glTextureSubImage3D = apply.get(976);
        this.glCompressedTextureSubImage1D = apply.get(977);
        this.glCompressedTextureSubImage2D = apply.get(978);
        this.glCompressedTextureSubImage3D = apply.get(979);
        this.glCopyTextureSubImage1D = apply.get(980);
        this.glCopyTextureSubImage2D = apply.get(981);
        this.glCopyTextureSubImage3D = apply.get(982);
        this.glTextureParameterf = apply.get(983);
        this.glTextureParameterfv = apply.get(984);
        this.glTextureParameteri = apply.get(985);
        this.glTextureParameterIiv = apply.get(986);
        this.glTextureParameterIuiv = apply.get(987);
        this.glTextureParameteriv = apply.get(988);
        this.glGenerateTextureMipmap = apply.get(989);
        this.glBindTextureUnit = apply.get(990);
        this.glGetTextureImage = apply.get(991);
        this.glGetCompressedTextureImage = apply.get(992);
        this.glGetTextureLevelParameterfv = apply.get(993);
        this.glGetTextureLevelParameteriv = apply.get(994);
        this.glGetTextureParameterfv = apply.get(995);
        this.glGetTextureParameterIiv = apply.get(996);
        this.glGetTextureParameterIuiv = apply.get(997);
        this.glGetTextureParameteriv = apply.get(998);
        this.glCreateVertexArrays = apply.get(Tower.MAX_LEVEL);
        this.glDisableVertexArrayAttrib = apply.get(1000);
        this.glEnableVertexArrayAttrib = apply.get(CoreGraphics.kCGErrorIllegalArgument);
        this.glVertexArrayElementBuffer = apply.get(CoreGraphics.kCGErrorInvalidConnection);
        this.glVertexArrayVertexBuffer = apply.get(CoreGraphics.kCGErrorInvalidContext);
        this.glVertexArrayVertexBuffers = apply.get(CoreGraphics.kCGErrorCannotComplete);
        this.glVertexArrayAttribFormat = apply.get(1005);
        this.glVertexArrayAttribIFormat = apply.get(CoreGraphics.kCGErrorNotImplemented);
        this.glVertexArrayAttribLFormat = apply.get(CoreGraphics.kCGErrorRangeCheck);
        this.glVertexArrayAttribBinding = apply.get(CoreGraphics.kCGErrorTypeCheck);
        this.glVertexArrayBindingDivisor = apply.get(1009);
        this.glGetVertexArrayiv = apply.get(CoreGraphics.kCGErrorInvalidOperation);
        this.glGetVertexArrayIndexediv = apply.get(CoreGraphics.kCGErrorNoneAvailable);
        this.glGetVertexArrayIndexed64iv = apply.get(1012);
        this.glCreateSamplers = apply.get(1013);
        this.glCreateProgramPipelines = apply.get(1014);
        this.glCreateQueries = apply.get(1015);
        this.glGetQueryBufferObjectiv = apply.get(1016);
        this.glGetQueryBufferObjectuiv = apply.get(1017);
        this.glGetQueryBufferObjecti64v = apply.get(1018);
        this.glGetQueryBufferObjectui64v = apply.get(1019);
        this.glMemoryBarrierByRegion = apply.get(1020);
        this.glGetTextureSubImage = apply.get(1021);
        this.glGetCompressedTextureSubImage = apply.get(1022);
        this.glTextureBarrier = apply.get(1023);
        this.glGetGraphicsResetStatus = apply.get(1024);
        this.glGetnMapdv = apply.get(1025);
        this.glGetnMapfv = apply.get(1026);
        this.glGetnMapiv = apply.get(1027);
        this.glGetnPixelMapfv = apply.get(1028);
        this.glGetnPixelMapuiv = apply.get(1029);
        this.glGetnPixelMapusv = apply.get(1030);
        this.glGetnPolygonStipple = apply.get(1031);
        this.glGetnTexImage = apply.get(1032);
        this.glReadnPixels = apply.get(1033);
        this.glGetnColorTable = apply.get(1034);
        this.glGetnConvolutionFilter = apply.get(1035);
        this.glGetnSeparableFilter = apply.get(1036);
        this.glGetnHistogram = apply.get(1037);
        this.glGetnMinmax = apply.get(FCNTL.F_SET_FILE_RW_HINT);
        this.glGetnCompressedTexImage = apply.get(1039);
        this.glGetnUniformfv = apply.get(STBTruetype.STBTT_MS_LANG_ITALIAN);
        this.glGetnUniformdv = apply.get(STBTruetype.STBTT_MS_LANG_JAPANESE);
        this.glGetnUniformiv = apply.get(STBTruetype.STBTT_MS_LANG_KOREAN);
        this.glGetnUniformuiv = apply.get(STBTruetype.STBTT_MS_LANG_DUTCH);
        this.glMultiDrawArraysIndirectCount = apply.get(1044);
        this.glMultiDrawElementsIndirectCount = apply.get(1045);
        this.glPolygonOffsetClamp = apply.get(1046);
        this.glSpecializeShader = apply.get(1047);
        this.glDebugMessageEnableAMD = apply.get(1048);
        this.glDebugMessageInsertAMD = apply.get(STBTruetype.STBTT_MS_LANG_RUSSIAN);
        this.glDebugMessageCallbackAMD = apply.get(1050);
        this.glGetDebugMessageLogAMD = apply.get(1051);
        this.glBlendFuncIndexedAMD = apply.get(1052);
        this.glBlendFuncSeparateIndexedAMD = apply.get(STBTruetype.STBTT_MS_LANG_SWEDISH);
        this.glBlendEquationIndexedAMD = apply.get(1054);
        this.glBlendEquationSeparateIndexedAMD = apply.get(1055);
        this.glRenderbufferStorageMultisampleAdvancedAMD = apply.get(1056);
        this.glNamedRenderbufferStorageMultisampleAdvancedAMD = apply.get(1057);
        this.glUniform1i64NV = apply.get(1058);
        this.glUniform2i64NV = apply.get(1059);
        this.glUniform3i64NV = apply.get(1060);
        this.glUniform4i64NV = apply.get(1061);
        this.glUniform1i64vNV = apply.get(1062);
        this.glUniform2i64vNV = apply.get(1063);
        this.glUniform3i64vNV = apply.get(1064);
        this.glUniform4i64vNV = apply.get(1065);
        this.glUniform1ui64NV = apply.get(1066);
        this.glUniform2ui64NV = apply.get(1067);
        this.glUniform3ui64NV = apply.get(1068);
        this.glUniform4ui64NV = apply.get(1069);
        this.glUniform1ui64vNV = apply.get(1070);
        this.glUniform2ui64vNV = apply.get(1071);
        this.glUniform3ui64vNV = apply.get(1072);
        this.glUniform4ui64vNV = apply.get(1073);
        this.glGetUniformi64vNV = apply.get(1074);
        this.glGetUniformui64vNV = apply.get(1075);
        this.glProgramUniform1i64NV = apply.get(1076);
        this.glProgramUniform2i64NV = apply.get(1077);
        this.glProgramUniform3i64NV = apply.get(1078);
        this.glProgramUniform4i64NV = apply.get(1079);
        this.glProgramUniform1i64vNV = apply.get(Config.VIEWPORT_HEIGHT);
        this.glProgramUniform2i64vNV = apply.get(1081);
        this.glProgramUniform3i64vNV = apply.get(1082);
        this.glProgramUniform4i64vNV = apply.get(1083);
        this.glProgramUniform1ui64NV = apply.get(1084);
        this.glProgramUniform2ui64NV = apply.get(1085);
        this.glProgramUniform3ui64NV = apply.get(1086);
        this.glProgramUniform4ui64NV = apply.get(1087);
        this.glProgramUniform1ui64vNV = apply.get(1088);
        this.glProgramUniform2ui64vNV = apply.get(1089);
        this.glProgramUniform3ui64vNV = apply.get(1090);
        this.glProgramUniform4ui64vNV = apply.get(1091);
        this.glVertexAttribParameteriAMD = apply.get(1092);
        this.glQueryObjectParameteruiAMD = apply.get(1093);
        this.glGetPerfMonitorGroupsAMD = apply.get(1094);
        this.glGetPerfMonitorCountersAMD = apply.get(1095);
        this.glGetPerfMonitorGroupStringAMD = apply.get(1096);
        this.glGetPerfMonitorCounterStringAMD = apply.get(1097);
        this.glGetPerfMonitorCounterInfoAMD = apply.get(1098);
        this.glGenPerfMonitorsAMD = apply.get(1099);
        this.glDeletePerfMonitorsAMD = apply.get(1100);
        this.glSelectPerfMonitorCountersAMD = apply.get(1101);
        this.glBeginPerfMonitorAMD = apply.get(1102);
        this.glEndPerfMonitorAMD = apply.get(1103);
        this.glGetPerfMonitorCounterDataAMD = apply.get(1104);
        this.glSetMultisamplefvAMD = apply.get(1105);
        this.glTexStorageSparseAMD = apply.get(1106);
        this.glTextureStorageSparseAMD = apply.get(1107);
        this.glStencilOpValueAMD = apply.get(1108);
        this.glTessellationFactorAMD = apply.get(1109);
        this.glTessellationModeAMD = apply.get(1110);
        this.glGetTextureHandleARB = apply.get(1111);
        this.glGetTextureSamplerHandleARB = apply.get(1112);
        this.glMakeTextureHandleResidentARB = apply.get(1113);
        this.glMakeTextureHandleNonResidentARB = apply.get(1114);
        this.glGetImageHandleARB = apply.get(1115);
        this.glMakeImageHandleResidentARB = apply.get(1116);
        this.glMakeImageHandleNonResidentARB = apply.get(1117);
        this.glUniformHandleui64ARB = apply.get(1118);
        this.glUniformHandleui64vARB = apply.get(1119);
        this.glProgramUniformHandleui64ARB = apply.get(1120);
        this.glProgramUniformHandleui64vARB = apply.get(1121);
        this.glIsTextureHandleResidentARB = apply.get(1122);
        this.glIsImageHandleResidentARB = apply.get(1123);
        this.glVertexAttribL1ui64ARB = apply.get(1124);
        this.glVertexAttribL1ui64vARB = apply.get(1125);
        this.glGetVertexAttribLui64vARB = apply.get(1126);
        this.glNamedBufferStorageEXT = apply.get(1127);
        this.glCreateSyncFromCLeventARB = apply.get(1128);
        this.glClearNamedBufferDataEXT = apply.get(1129);
        this.glClearNamedBufferSubDataEXT = apply.get(1130);
        this.glClampColorARB = apply.get(1131);
        this.glDispatchComputeGroupSizeARB = apply.get(1132);
        this.glDebugMessageControlARB = apply.get(1133);
        this.glDebugMessageInsertARB = apply.get(1134);
        this.glDebugMessageCallbackARB = apply.get(1135);
        this.glGetDebugMessageLogARB = apply.get(1136);
        this.glDrawBuffersARB = apply.get(1137);
        this.glBlendEquationiARB = apply.get(1138);
        this.glBlendEquationSeparateiARB = apply.get(1139);
        this.glBlendFunciARB = apply.get(1140);
        this.glBlendFuncSeparateiARB = apply.get(1141);
        this.glDrawArraysInstancedARB = apply.get(1142);
        this.glDrawElementsInstancedARB = apply.get(1143);
        this.glPrimitiveBoundingBoxARB = apply.get(1144);
        this.glNamedFramebufferParameteriEXT = apply.get(1145);
        this.glGetNamedFramebufferParameterivEXT = apply.get(1146);
        this.glProgramParameteriARB = apply.get(1147);
        this.glFramebufferTextureARB = apply.get(1148);
        this.glFramebufferTextureLayerARB = apply.get(1149);
        this.glFramebufferTextureFaceARB = apply.get(1150);
        this.glSpecializeShaderARB = apply.get(1151);
        this.glProgramUniform1dEXT = apply.get(1152);
        this.glProgramUniform2dEXT = apply.get(1153);
        this.glProgramUniform3dEXT = apply.get(1154);
        this.glProgramUniform4dEXT = apply.get(1155);
        this.glProgramUniform1dvEXT = apply.get(1156);
        this.glProgramUniform2dvEXT = apply.get(1157);
        this.glProgramUniform3dvEXT = apply.get(1158);
        this.glProgramUniform4dvEXT = apply.get(1159);
        this.glProgramUniformMatrix2dvEXT = apply.get(1160);
        this.glProgramUniformMatrix3dvEXT = apply.get(1161);
        this.glProgramUniformMatrix4dvEXT = apply.get(1162);
        this.glProgramUniformMatrix2x3dvEXT = apply.get(1163);
        this.glProgramUniformMatrix2x4dvEXT = apply.get(1164);
        this.glProgramUniformMatrix3x2dvEXT = apply.get(1165);
        this.glProgramUniformMatrix3x4dvEXT = apply.get(1166);
        this.glProgramUniformMatrix4x2dvEXT = apply.get(1167);
        this.glProgramUniformMatrix4x3dvEXT = apply.get(1168);
        this.glUniform1i64ARB = apply.get(1169);
        this.glUniform1i64vARB = apply.get(1170);
        this.glProgramUniform1i64ARB = apply.get(1171);
        this.glProgramUniform1i64vARB = apply.get(1172);
        this.glUniform2i64ARB = apply.get(1173);
        this.glUniform2i64vARB = apply.get(1174);
        this.glProgramUniform2i64ARB = apply.get(1175);
        this.glProgramUniform2i64vARB = apply.get(1176);
        this.glUniform3i64ARB = apply.get(1177);
        this.glUniform3i64vARB = apply.get(1178);
        this.glProgramUniform3i64ARB = apply.get(1179);
        this.glProgramUniform3i64vARB = apply.get(1180);
        this.glUniform4i64ARB = apply.get(1181);
        this.glUniform4i64vARB = apply.get(1182);
        this.glProgramUniform4i64ARB = apply.get(1183);
        this.glProgramUniform4i64vARB = apply.get(1184);
        this.glUniform1ui64ARB = apply.get(1185);
        this.glUniform1ui64vARB = apply.get(1186);
        this.glProgramUniform1ui64ARB = apply.get(1187);
        this.glProgramUniform1ui64vARB = apply.get(1188);
        this.glUniform2ui64ARB = apply.get(1189);
        this.glUniform2ui64vARB = apply.get(1190);
        this.glProgramUniform2ui64ARB = apply.get(1191);
        this.glProgramUniform2ui64vARB = apply.get(1192);
        this.glUniform3ui64ARB = apply.get(1193);
        this.glUniform3ui64vARB = apply.get(1194);
        this.glProgramUniform3ui64ARB = apply.get(1195);
        this.glProgramUniform3ui64vARB = apply.get(1196);
        this.glUniform4ui64ARB = apply.get(1197);
        this.glUniform4ui64vARB = apply.get(1198);
        this.glProgramUniform4ui64ARB = apply.get(1199);
        this.glProgramUniform4ui64vARB = apply.get(1200);
        this.glGetUniformi64vARB = apply.get(1201);
        this.glGetUniformui64vARB = apply.get(1202);
        this.glGetnUniformi64vARB = apply.get(1203);
        this.glGetnUniformui64vARB = apply.get(1204);
        this.glColorTable = apply.get(1205);
        this.glCopyColorTable = apply.get(1206);
        this.glColorTableParameteriv = apply.get(1207);
        this.glColorTableParameterfv = apply.get(1208);
        this.glGetColorTable = apply.get(1209);
        this.glGetColorTableParameteriv = apply.get(1210);
        this.glGetColorTableParameterfv = apply.get(1211);
        this.glColorSubTable = apply.get(1212);
        this.glCopyColorSubTable = apply.get(1213);
        this.glConvolutionFilter1D = apply.get(1214);
        this.glConvolutionFilter2D = apply.get(1215);
        this.glCopyConvolutionFilter1D = apply.get(1216);
        this.glCopyConvolutionFilter2D = apply.get(1217);
        this.glGetConvolutionFilter = apply.get(1218);
        this.glSeparableFilter2D = apply.get(1219);
        this.glGetSeparableFilter = apply.get(1220);
        this.glConvolutionParameteri = apply.get(1221);
        this.glConvolutionParameteriv = apply.get(1222);
        this.glConvolutionParameterf = apply.get(1223);
        this.glConvolutionParameterfv = apply.get(1224);
        this.glGetConvolutionParameteriv = apply.get(1225);
        this.glGetConvolutionParameterfv = apply.get(1226);
        this.glHistogram = apply.get(1227);
        this.glResetHistogram = apply.get(1228);
        this.glGetHistogram = apply.get(1229);
        this.glGetHistogramParameteriv = apply.get(1230);
        this.glGetHistogramParameterfv = apply.get(1231);
        this.glMinmax = apply.get(1232);
        this.glResetMinmax = apply.get(1233);
        this.glGetMinmax = apply.get(1234);
        this.glGetMinmaxParameteriv = apply.get(1235);
        this.glGetMinmaxParameterfv = apply.get(1236);
        this.glMultiDrawArraysIndirectCountARB = apply.get(1237);
        this.glMultiDrawElementsIndirectCountARB = apply.get(1238);
        this.glVertexAttribDivisorARB = apply.get(1239);
        this.glVertexArrayVertexAttribDivisorEXT = apply.get(1240);
        this.glCurrentPaletteMatrixARB = apply.get(1241);
        this.glMatrixIndexuivARB = apply.get(1242);
        this.glMatrixIndexubvARB = apply.get(1243);
        this.glMatrixIndexusvARB = apply.get(1244);
        this.glMatrixIndexPointerARB = apply.get(1245);
        this.glSampleCoverageARB = apply.get(1246);
        this.glActiveTextureARB = apply.get(1247);
        this.glClientActiveTextureARB = apply.get(1248);
        this.glMultiTexCoord1fARB = apply.get(1249);
        this.glMultiTexCoord1sARB = apply.get(1250);
        this.glMultiTexCoord1iARB = apply.get(1251);
        this.glMultiTexCoord1dARB = apply.get(1252);
        this.glMultiTexCoord1fvARB = apply.get(1253);
        this.glMultiTexCoord1svARB = apply.get(1254);
        this.glMultiTexCoord1ivARB = apply.get(1255);
        this.glMultiTexCoord1dvARB = apply.get(1256);
        this.glMultiTexCoord2fARB = apply.get(1257);
        this.glMultiTexCoord2sARB = apply.get(1258);
        this.glMultiTexCoord2iARB = apply.get(1259);
        this.glMultiTexCoord2dARB = apply.get(1260);
        this.glMultiTexCoord2fvARB = apply.get(1261);
        this.glMultiTexCoord2svARB = apply.get(1262);
        this.glMultiTexCoord2ivARB = apply.get(1263);
        this.glMultiTexCoord2dvARB = apply.get(1264);
        this.glMultiTexCoord3fARB = apply.get(1265);
        this.glMultiTexCoord3sARB = apply.get(1266);
        this.glMultiTexCoord3iARB = apply.get(1267);
        this.glMultiTexCoord3dARB = apply.get(1268);
        this.glMultiTexCoord3fvARB = apply.get(1269);
        this.glMultiTexCoord3svARB = apply.get(1270);
        this.glMultiTexCoord3ivARB = apply.get(1271);
        this.glMultiTexCoord3dvARB = apply.get(1272);
        this.glMultiTexCoord4fARB = apply.get(1273);
        this.glMultiTexCoord4sARB = apply.get(1274);
        this.glMultiTexCoord4iARB = apply.get(1275);
        this.glMultiTexCoord4dARB = apply.get(1276);
        this.glMultiTexCoord4fvARB = apply.get(1277);
        this.glMultiTexCoord4svARB = apply.get(1278);
        this.glMultiTexCoord4ivARB = apply.get(1279);
        this.glMultiTexCoord4dvARB = apply.get(1280);
        this.glGenQueriesARB = apply.get(1281);
        this.glDeleteQueriesARB = apply.get(1282);
        this.glIsQueryARB = apply.get(1283);
        this.glBeginQueryARB = apply.get(1284);
        this.glEndQueryARB = apply.get(1285);
        this.glGetQueryivARB = apply.get(1286);
        this.glGetQueryObjectivARB = apply.get(1287);
        this.glGetQueryObjectuivARB = apply.get(1288);
        this.glMaxShaderCompilerThreadsARB = apply.get(1289);
        this.glPointParameterfARB = apply.get(1290);
        this.glPointParameterfvARB = apply.get(1291);
        this.glGetGraphicsResetStatusARB = apply.get(1292);
        this.glGetnMapdvARB = apply.get(1293);
        this.glGetnMapfvARB = apply.get(1294);
        this.glGetnMapivARB = apply.get(1295);
        this.glGetnPixelMapfvARB = apply.get(1296);
        this.glGetnPixelMapuivARB = apply.get(1297);
        this.glGetnPixelMapusvARB = apply.get(1298);
        this.glGetnPolygonStippleARB = apply.get(1299);
        this.glGetnTexImageARB = apply.get(1300);
        this.glReadnPixelsARB = apply.get(1301);
        this.glGetnColorTableARB = apply.get(1302);
        this.glGetnConvolutionFilterARB = apply.get(1303);
        this.glGetnSeparableFilterARB = apply.get(1304);
        this.glGetnHistogramARB = apply.get(1305);
        this.glGetnMinmaxARB = apply.get(1306);
        this.glGetnCompressedTexImageARB = apply.get(1307);
        this.glGetnUniformfvARB = apply.get(1308);
        this.glGetnUniformivARB = apply.get(1309);
        this.glGetnUniformuivARB = apply.get(1310);
        this.glGetnUniformdvARB = apply.get(1311);
        this.glFramebufferSampleLocationsfvARB = apply.get(1312);
        this.glNamedFramebufferSampleLocationsfvARB = apply.get(1313);
        this.glEvaluateDepthValuesARB = apply.get(1314);
        this.glMinSampleShadingARB = apply.get(1315);
        this.glDeleteObjectARB = apply.get(1316);
        this.glGetHandleARB = apply.get(1317);
        this.glDetachObjectARB = apply.get(1318);
        this.glCreateShaderObjectARB = apply.get(1319);
        this.glShaderSourceARB = apply.get(1320);
        this.glCompileShaderARB = apply.get(1321);
        this.glCreateProgramObjectARB = apply.get(1322);
        this.glAttachObjectARB = apply.get(1323);
        this.glLinkProgramARB = apply.get(1324);
        this.glUseProgramObjectARB = apply.get(1325);
        this.glValidateProgramARB = apply.get(1326);
        this.glUniform1fARB = apply.get(1327);
        this.glUniform2fARB = apply.get(1328);
        this.glUniform3fARB = apply.get(1329);
        this.glUniform4fARB = apply.get(1330);
        this.glUniform1iARB = apply.get(1331);
        this.glUniform2iARB = apply.get(1332);
        this.glUniform3iARB = apply.get(Config.PLAYER_XP_MAX_PER_GAME);
        this.glUniform4iARB = apply.get(1334);
        this.glUniform1fvARB = apply.get(1335);
        this.glUniform2fvARB = apply.get(1336);
        this.glUniform3fvARB = apply.get(1337);
        this.glUniform4fvARB = apply.get(1338);
        this.glUniform1ivARB = apply.get(1339);
        this.glUniform2ivARB = apply.get(1340);
        this.glUniform3ivARB = apply.get(1341);
        this.glUniform4ivARB = apply.get(1342);
        this.glUniformMatrix2fvARB = apply.get(1343);
        this.glUniformMatrix3fvARB = apply.get(1344);
        this.glUniformMatrix4fvARB = apply.get(1345);
        this.glGetObjectParameterfvARB = apply.get(1346);
        this.glGetObjectParameterivARB = apply.get(1347);
        this.glGetInfoLogARB = apply.get(1348);
        this.glGetAttachedObjectsARB = apply.get(1349);
        this.glGetUniformLocationARB = apply.get(1350);
        this.glGetActiveUniformARB = apply.get(1351);
        this.glGetUniformfvARB = apply.get(1352);
        this.glGetUniformivARB = apply.get(1353);
        this.glGetShaderSourceARB = apply.get(1354);
        this.glNamedStringARB = apply.get(1355);
        this.glDeleteNamedStringARB = apply.get(1356);
        this.glCompileShaderIncludeARB = apply.get(1357);
        this.glIsNamedStringARB = apply.get(1358);
        this.glGetNamedStringARB = apply.get(1359);
        this.glGetNamedStringivARB = apply.get(1360);
        this.glBufferPageCommitmentARB = apply.get(1361);
        this.glNamedBufferPageCommitmentEXT = apply.get(1362);
        this.glNamedBufferPageCommitmentARB = apply.get(1363);
        this.glTexPageCommitmentARB = apply.get(1364);
        this.glTexturePageCommitmentEXT = apply.get(1365);
        this.glTexBufferARB = apply.get(1366);
        this.glTextureBufferRangeEXT = apply.get(1367);
        this.glCompressedTexImage3DARB = apply.get(1368);
        this.glCompressedTexImage2DARB = apply.get(1369);
        this.glCompressedTexImage1DARB = apply.get(1370);
        this.glCompressedTexSubImage3DARB = apply.get(1371);
        this.glCompressedTexSubImage2DARB = apply.get(1372);
        this.glCompressedTexSubImage1DARB = apply.get(1373);
        this.glGetCompressedTexImageARB = apply.get(1374);
        this.glTextureStorage1DEXT = apply.get(1375);
        this.glTextureStorage2DEXT = apply.get(1376);
        this.glTextureStorage3DEXT = apply.get(1377);
        this.glTextureStorage2DMultisampleEXT = apply.get(1378);
        this.glTextureStorage3DMultisampleEXT = apply.get(1379);
        this.glLoadTransposeMatrixfARB = apply.get(1380);
        this.glLoadTransposeMatrixdARB = apply.get(1381);
        this.glMultTransposeMatrixfARB = apply.get(1382);
        this.glMultTransposeMatrixdARB = apply.get(1383);
        this.glVertexArrayVertexAttribLOffsetEXT = apply.get(1384);
        this.glVertexArrayBindVertexBufferEXT = apply.get(1385);
        this.glVertexArrayVertexAttribFormatEXT = apply.get(1386);
        this.glVertexArrayVertexAttribIFormatEXT = apply.get(1387);
        this.glVertexArrayVertexAttribLFormatEXT = apply.get(1388);
        this.glVertexArrayVertexAttribBindingEXT = apply.get(1389);
        this.glVertexArrayVertexBindingDivisorEXT = apply.get(1390);
        this.glWeightfvARB = apply.get(1391);
        this.glWeightbvARB = apply.get(1392);
        this.glWeightubvARB = apply.get(1393);
        this.glWeightsvARB = apply.get(1394);
        this.glWeightusvARB = apply.get(1395);
        this.glWeightivARB = apply.get(1396);
        this.glWeightuivARB = apply.get(1397);
        this.glWeightdvARB = apply.get(1398);
        this.glWeightPointerARB = apply.get(1399);
        this.glVertexBlendARB = apply.get(1400);
        this.glBindBufferARB = apply.get(ObjCRuntime.OBJC_ASSOCIATION_RETAIN);
        this.glDeleteBuffersARB = apply.get(1402);
        this.glGenBuffersARB = apply.get(ObjCRuntime.OBJC_ASSOCIATION_COPY);
        this.glIsBufferARB = apply.get(1404);
        this.glBufferDataARB = apply.get(1405);
        this.glBufferSubDataARB = apply.get(1406);
        this.glGetBufferSubDataARB = apply.get(1407);
        this.glMapBufferARB = apply.get(1408);
        this.glUnmapBufferARB = apply.get(1409);
        this.glGetBufferParameterivARB = apply.get(1410);
        this.glGetBufferPointervARB = apply.get(1411);
        this.glVertexAttrib1sARB = apply.get(1412);
        this.glVertexAttrib1fARB = apply.get(1413);
        this.glVertexAttrib1dARB = apply.get(1414);
        this.glVertexAttrib2sARB = apply.get(1415);
        this.glVertexAttrib2fARB = apply.get(1416);
        this.glVertexAttrib2dARB = apply.get(1417);
        this.glVertexAttrib3sARB = apply.get(1418);
        this.glVertexAttrib3fARB = apply.get(1419);
        this.glVertexAttrib3dARB = apply.get(1420);
        this.glVertexAttrib4sARB = apply.get(1421);
        this.glVertexAttrib4fARB = apply.get(1422);
        this.glVertexAttrib4dARB = apply.get(1423);
        this.glVertexAttrib4NubARB = apply.get(1424);
        this.glVertexAttrib1svARB = apply.get(1425);
        this.glVertexAttrib1fvARB = apply.get(1426);
        this.glVertexAttrib1dvARB = apply.get(1427);
        this.glVertexAttrib2svARB = apply.get(1428);
        this.glVertexAttrib2fvARB = apply.get(1429);
        this.glVertexAttrib2dvARB = apply.get(1430);
        this.glVertexAttrib3svARB = apply.get(1431);
        this.glVertexAttrib3fvARB = apply.get(1432);
        this.glVertexAttrib3dvARB = apply.get(1433);
        this.glVertexAttrib4fvARB = apply.get(1434);
        this.glVertexAttrib4bvARB = apply.get(1435);
        this.glVertexAttrib4svARB = apply.get(1436);
        this.glVertexAttrib4ivARB = apply.get(1437);
        this.glVertexAttrib4ubvARB = apply.get(1438);
        this.glVertexAttrib4usvARB = apply.get(1439);
        this.glVertexAttrib4uivARB = apply.get(1440);
        this.glVertexAttrib4dvARB = apply.get(1441);
        this.glVertexAttrib4NbvARB = apply.get(1442);
        this.glVertexAttrib4NsvARB = apply.get(1443);
        this.glVertexAttrib4NivARB = apply.get(1444);
        this.glVertexAttrib4NubvARB = apply.get(1445);
        this.glVertexAttrib4NusvARB = apply.get(1446);
        this.glVertexAttrib4NuivARB = apply.get(1447);
        this.glVertexAttribPointerARB = apply.get(1448);
        this.glEnableVertexAttribArrayARB = apply.get(1449);
        this.glDisableVertexAttribArrayARB = apply.get(1450);
        this.glProgramStringARB = apply.get(1451);
        this.glBindProgramARB = apply.get(1452);
        this.glDeleteProgramsARB = apply.get(1453);
        this.glGenProgramsARB = apply.get(1454);
        this.glProgramEnvParameter4dARB = apply.get(1455);
        this.glProgramEnvParameter4dvARB = apply.get(1456);
        this.glProgramEnvParameter4fARB = apply.get(1457);
        this.glProgramEnvParameter4fvARB = apply.get(1458);
        this.glProgramLocalParameter4dARB = apply.get(1459);
        this.glProgramLocalParameter4dvARB = apply.get(1460);
        this.glProgramLocalParameter4fARB = apply.get(1461);
        this.glProgramLocalParameter4fvARB = apply.get(1462);
        this.glGetProgramEnvParameterfvARB = apply.get(1463);
        this.glGetProgramEnvParameterdvARB = apply.get(1464);
        this.glGetProgramLocalParameterfvARB = apply.get(1465);
        this.glGetProgramLocalParameterdvARB = apply.get(1466);
        this.glGetProgramivARB = apply.get(1467);
        this.glGetProgramStringARB = apply.get(1468);
        this.glGetVertexAttribfvARB = apply.get(1469);
        this.glGetVertexAttribdvARB = apply.get(1470);
        this.glGetVertexAttribivARB = apply.get(1471);
        this.glGetVertexAttribPointervARB = apply.get(1472);
        this.glIsProgramARB = apply.get(1473);
        this.glBindAttribLocationARB = apply.get(1474);
        this.glGetActiveAttribARB = apply.get(1475);
        this.glGetAttribLocationARB = apply.get(1476);
        this.glWindowPos2iARB = apply.get(1477);
        this.glWindowPos2sARB = apply.get(1478);
        this.glWindowPos2fARB = apply.get(1479);
        this.glWindowPos2dARB = apply.get(1480);
        this.glWindowPos2ivARB = apply.get(1481);
        this.glWindowPos2svARB = apply.get(1482);
        this.glWindowPos2fvARB = apply.get(1483);
        this.glWindowPos2dvARB = apply.get(1484);
        this.glWindowPos3iARB = apply.get(1485);
        this.glWindowPos3sARB = apply.get(1486);
        this.glWindowPos3fARB = apply.get(1487);
        this.glWindowPos3dARB = apply.get(1488);
        this.glWindowPos3ivARB = apply.get(1489);
        this.glWindowPos3svARB = apply.get(1490);
        this.glWindowPos3fvARB = apply.get(1491);
        this.glWindowPos3dvARB = apply.get(1492);
        this.glUniformBufferEXT = apply.get(1493);
        this.glGetUniformBufferSizeEXT = apply.get(1494);
        this.glGetUniformOffsetEXT = apply.get(1495);
        this.glBlendColorEXT = apply.get(1496);
        this.glBlendEquationSeparateEXT = apply.get(1497);
        this.glBlendFuncSeparateEXT = apply.get(1498);
        this.glBlendEquationEXT = apply.get(1499);
        this.glLockArraysEXT = apply.get(1500);
        this.glUnlockArraysEXT = apply.get(1501);
        this.glLabelObjectEXT = apply.get(1502);
        this.glGetObjectLabelEXT = apply.get(1503);
        this.glInsertEventMarkerEXT = apply.get(1504);
        this.glPushGroupMarkerEXT = apply.get(1505);
        this.glPopGroupMarkerEXT = apply.get(1506);
        this.glDepthBoundsEXT = apply.get(1507);
        this.glClientAttribDefaultEXT = apply.get(1508);
        this.glPushClientAttribDefaultEXT = apply.get(1509);
        this.glMatrixLoadfEXT = apply.get(1510);
        this.glMatrixLoaddEXT = apply.get(1511);
        this.glMatrixMultfEXT = apply.get(1512);
        this.glMatrixMultdEXT = apply.get(1513);
        this.glMatrixLoadIdentityEXT = apply.get(1514);
        this.glMatrixRotatefEXT = apply.get(1515);
        this.glMatrixRotatedEXT = apply.get(1516);
        this.glMatrixScalefEXT = apply.get(1517);
        this.glMatrixScaledEXT = apply.get(1518);
        this.glMatrixTranslatefEXT = apply.get(1519);
        this.glMatrixTranslatedEXT = apply.get(1520);
        this.glMatrixOrthoEXT = apply.get(1521);
        this.glMatrixFrustumEXT = apply.get(1522);
        this.glMatrixPushEXT = apply.get(1523);
        this.glMatrixPopEXT = apply.get(1524);
        this.glTextureParameteriEXT = apply.get(1525);
        this.glTextureParameterivEXT = apply.get(1526);
        this.glTextureParameterfEXT = apply.get(1527);
        this.glTextureParameterfvEXT = apply.get(1528);
        this.glTextureImage1DEXT = apply.get(1529);
        this.glTextureImage2DEXT = apply.get(1530);
        this.glTextureSubImage1DEXT = apply.get(1531);
        this.glTextureSubImage2DEXT = apply.get(1532);
        this.glCopyTextureImage1DEXT = apply.get(1533);
        this.glCopyTextureImage2DEXT = apply.get(1534);
        this.glCopyTextureSubImage1DEXT = apply.get(1535);
        this.glCopyTextureSubImage2DEXT = apply.get(1536);
        this.glGetTextureImageEXT = apply.get(GL11.GL_3D);
        this.glGetTextureParameterfvEXT = apply.get(GL11.GL_3D_COLOR);
        this.glGetTextureParameterivEXT = apply.get(GL11.GL_3D_COLOR_TEXTURE);
        this.glGetTextureLevelParameterfvEXT = apply.get(GL11.GL_4D_COLOR_TEXTURE);
        this.glGetTextureLevelParameterivEXT = apply.get(1541);
        this.glTextureImage3DEXT = apply.get(1542);
        this.glTextureSubImage3DEXT = apply.get(1543);
        this.glCopyTextureSubImage3DEXT = apply.get(1544);
        this.glBindMultiTextureEXT = apply.get(1545);
        this.glMultiTexCoordPointerEXT = apply.get(1546);
        this.glMultiTexEnvfEXT = apply.get(1547);
        this.glMultiTexEnvfvEXT = apply.get(1548);
        this.glMultiTexEnviEXT = apply.get(1549);
        this.glMultiTexEnvivEXT = apply.get(1550);
        this.glMultiTexGendEXT = apply.get(1551);
        this.glMultiTexGendvEXT = apply.get(1552);
        this.glMultiTexGenfEXT = apply.get(1553);
        this.glMultiTexGenfvEXT = apply.get(1554);
        this.glMultiTexGeniEXT = apply.get(1555);
        this.glMultiTexGenivEXT = apply.get(1556);
        this.glGetMultiTexEnvfvEXT = apply.get(1557);
        this.glGetMultiTexEnvivEXT = apply.get(1558);
        this.glGetMultiTexGendvEXT = apply.get(1559);
        this.glGetMultiTexGenfvEXT = apply.get(1560);
        this.glGetMultiTexGenivEXT = apply.get(1561);
        this.glMultiTexParameteriEXT = apply.get(1562);
        this.glMultiTexParameterivEXT = apply.get(1563);
        this.glMultiTexParameterfEXT = apply.get(1564);
        this.glMultiTexParameterfvEXT = apply.get(1565);
        this.glMultiTexImage1DEXT = apply.get(1566);
        this.glMultiTexImage2DEXT = apply.get(1567);
        this.glMultiTexSubImage1DEXT = apply.get(1568);
        this.glMultiTexSubImage2DEXT = apply.get(1569);
        this.glCopyMultiTexImage1DEXT = apply.get(1570);
        this.glCopyMultiTexImage2DEXT = apply.get(1571);
        this.glCopyMultiTexSubImage1DEXT = apply.get(1572);
        this.glCopyMultiTexSubImage2DEXT = apply.get(1573);
        this.glGetMultiTexImageEXT = apply.get(1574);
        this.glGetMultiTexParameterfvEXT = apply.get(1575);
        this.glGetMultiTexParameterivEXT = apply.get(1576);
        this.glGetMultiTexLevelParameterfvEXT = apply.get(1577);
        this.glGetMultiTexLevelParameterivEXT = apply.get(1578);
        this.glMultiTexImage3DEXT = apply.get(1579);
        this.glMultiTexSubImage3DEXT = apply.get(1580);
        this.glCopyMultiTexSubImage3DEXT = apply.get(1581);
        this.glEnableClientStateIndexedEXT = apply.get(1582);
        this.glDisableClientStateIndexedEXT = apply.get(1583);
        this.glEnableClientStateiEXT = apply.get(1584);
        this.glDisableClientStateiEXT = apply.get(1585);
        this.glGetFloatIndexedvEXT = apply.get(1586);
        this.glGetDoubleIndexedvEXT = apply.get(1587);
        this.glGetPointerIndexedvEXT = apply.get(1588);
        this.glGetFloati_vEXT = apply.get(1589);
        this.glGetDoublei_vEXT = apply.get(1590);
        this.glGetPointeri_vEXT = apply.get(1591);
        this.glEnableIndexedEXT = apply.get(1592);
        this.glDisableIndexedEXT = apply.get(1593);
        this.glIsEnabledIndexedEXT = apply.get(1594);
        this.glGetIntegerIndexedvEXT = apply.get(1595);
        this.glGetBooleanIndexedvEXT = apply.get(1596);
        this.glNamedProgramStringEXT = apply.get(1597);
        this.glNamedProgramLocalParameter4dEXT = apply.get(1598);
        this.glNamedProgramLocalParameter4dvEXT = apply.get(1599);
        this.glNamedProgramLocalParameter4fEXT = apply.get(Config.DISPLAY_WIDTH);
        this.glNamedProgramLocalParameter4fvEXT = apply.get(1601);
        this.glGetNamedProgramLocalParameterdvEXT = apply.get(1602);
        this.glGetNamedProgramLocalParameterfvEXT = apply.get(1603);
        this.glGetNamedProgramivEXT = apply.get(1604);
        this.glGetNamedProgramStringEXT = apply.get(1605);
        this.glCompressedTextureImage3DEXT = apply.get(1606);
        this.glCompressedTextureImage2DEXT = apply.get(1607);
        this.glCompressedTextureImage1DEXT = apply.get(1608);
        this.glCompressedTextureSubImage3DEXT = apply.get(1609);
        this.glCompressedTextureSubImage2DEXT = apply.get(1610);
        this.glCompressedTextureSubImage1DEXT = apply.get(1611);
        this.glGetCompressedTextureImageEXT = apply.get(1612);
        this.glCompressedMultiTexImage3DEXT = apply.get(1613);
        this.glCompressedMultiTexImage2DEXT = apply.get(1614);
        this.glCompressedMultiTexImage1DEXT = apply.get(1615);
        this.glCompressedMultiTexSubImage3DEXT = apply.get(1616);
        this.glCompressedMultiTexSubImage2DEXT = apply.get(1617);
        this.glCompressedMultiTexSubImage1DEXT = apply.get(1618);
        this.glGetCompressedMultiTexImageEXT = apply.get(1619);
        this.glMatrixLoadTransposefEXT = apply.get(1620);
        this.glMatrixLoadTransposedEXT = apply.get(1621);
        this.glMatrixMultTransposefEXT = apply.get(1622);
        this.glMatrixMultTransposedEXT = apply.get(1623);
        this.glNamedBufferDataEXT = apply.get(1624);
        this.glNamedBufferSubDataEXT = apply.get(1625);
        this.glMapNamedBufferEXT = apply.get(1626);
        this.glUnmapNamedBufferEXT = apply.get(1627);
        this.glGetNamedBufferParameterivEXT = apply.get(1628);
        this.glGetNamedBufferSubDataEXT = apply.get(1629);
        this.glProgramUniform1fEXT = apply.get(1630);
        this.glProgramUniform2fEXT = apply.get(1631);
        this.glProgramUniform3fEXT = apply.get(1632);
        this.glProgramUniform4fEXT = apply.get(1633);
        this.glProgramUniform1iEXT = apply.get(1634);
        this.glProgramUniform2iEXT = apply.get(1635);
        this.glProgramUniform3iEXT = apply.get(1636);
        this.glProgramUniform4iEXT = apply.get(1637);
        this.glProgramUniform1fvEXT = apply.get(1638);
        this.glProgramUniform2fvEXT = apply.get(1639);
        this.glProgramUniform3fvEXT = apply.get(1640);
        this.glProgramUniform4fvEXT = apply.get(1641);
        this.glProgramUniform1ivEXT = apply.get(1642);
        this.glProgramUniform2ivEXT = apply.get(1643);
        this.glProgramUniform3ivEXT = apply.get(1644);
        this.glProgramUniform4ivEXT = apply.get(1645);
        this.glProgramUniformMatrix2fvEXT = apply.get(1646);
        this.glProgramUniformMatrix3fvEXT = apply.get(1647);
        this.glProgramUniformMatrix4fvEXT = apply.get(1648);
        this.glProgramUniformMatrix2x3fvEXT = apply.get(1649);
        this.glProgramUniformMatrix3x2fvEXT = apply.get(1650);
        this.glProgramUniformMatrix2x4fvEXT = apply.get(1651);
        this.glProgramUniformMatrix4x2fvEXT = apply.get(1652);
        this.glProgramUniformMatrix3x4fvEXT = apply.get(1653);
        this.glProgramUniformMatrix4x3fvEXT = apply.get(1654);
        this.glTextureBufferEXT = apply.get(1655);
        this.glMultiTexBufferEXT = apply.get(1656);
        this.glTextureParameterIivEXT = apply.get(1657);
        this.glTextureParameterIuivEXT = apply.get(1658);
        this.glGetTextureParameterIivEXT = apply.get(1659);
        this.glGetTextureParameterIuivEXT = apply.get(1660);
        this.glMultiTexParameterIivEXT = apply.get(1661);
        this.glMultiTexParameterIuivEXT = apply.get(1662);
        this.glGetMultiTexParameterIivEXT = apply.get(1663);
        this.glGetMultiTexParameterIuivEXT = apply.get(1664);
        this.glProgramUniform1uiEXT = apply.get(1665);
        this.glProgramUniform2uiEXT = apply.get(1666);
        this.glProgramUniform3uiEXT = apply.get(1667);
        this.glProgramUniform4uiEXT = apply.get(1668);
        this.glProgramUniform1uivEXT = apply.get(1669);
        this.glProgramUniform2uivEXT = apply.get(1670);
        this.glProgramUniform3uivEXT = apply.get(1671);
        this.glProgramUniform4uivEXT = apply.get(1672);
        this.glNamedProgramLocalParameters4fvEXT = apply.get(1673);
        this.glNamedProgramLocalParameterI4iEXT = apply.get(1674);
        this.glNamedProgramLocalParameterI4ivEXT = apply.get(1675);
        this.glNamedProgramLocalParametersI4ivEXT = apply.get(1676);
        this.glNamedProgramLocalParameterI4uiEXT = apply.get(1677);
        this.glNamedProgramLocalParameterI4uivEXT = apply.get(1678);
        this.glNamedProgramLocalParametersI4uivEXT = apply.get(1679);
        this.glGetNamedProgramLocalParameterIivEXT = apply.get(1680);
        this.glGetNamedProgramLocalParameterIuivEXT = apply.get(1681);
        this.glNamedRenderbufferStorageEXT = apply.get(1682);
        this.glGetNamedRenderbufferParameterivEXT = apply.get(1683);
        this.glNamedRenderbufferStorageMultisampleEXT = apply.get(1684);
        this.glNamedRenderbufferStorageMultisampleCoverageEXT = apply.get(1685);
        this.glCheckNamedFramebufferStatusEXT = apply.get(1686);
        this.glNamedFramebufferTexture1DEXT = apply.get(1687);
        this.glNamedFramebufferTexture2DEXT = apply.get(1688);
        this.glNamedFramebufferTexture3DEXT = apply.get(1689);
        this.glNamedFramebufferRenderbufferEXT = apply.get(1690);
        this.glGetNamedFramebufferAttachmentParameterivEXT = apply.get(1691);
        this.glGenerateTextureMipmapEXT = apply.get(1692);
        this.glGenerateMultiTexMipmapEXT = apply.get(1693);
        this.glFramebufferDrawBufferEXT = apply.get(1694);
        this.glFramebufferDrawBuffersEXT = apply.get(1695);
        this.glFramebufferReadBufferEXT = apply.get(1696);
        this.glGetFramebufferParameterivEXT = apply.get(1697);
        this.glNamedCopyBufferSubDataEXT = apply.get(1698);
        this.glNamedFramebufferTextureEXT = apply.get(1699);
        this.glNamedFramebufferTextureLayerEXT = apply.get(1700);
        this.glNamedFramebufferTextureFaceEXT = apply.get(1701);
        this.glTextureRenderbufferEXT = apply.get(1702);
        this.glMultiTexRenderbufferEXT = apply.get(1703);
        this.glVertexArrayVertexOffsetEXT = apply.get(1704);
        this.glVertexArrayColorOffsetEXT = apply.get(1705);
        this.glVertexArrayEdgeFlagOffsetEXT = apply.get(1706);
        this.glVertexArrayIndexOffsetEXT = apply.get(1707);
        this.glVertexArrayNormalOffsetEXT = apply.get(1708);
        this.glVertexArrayTexCoordOffsetEXT = apply.get(1709);
        this.glVertexArrayMultiTexCoordOffsetEXT = apply.get(1710);
        this.glVertexArrayFogCoordOffsetEXT = apply.get(1711);
        this.glVertexArraySecondaryColorOffsetEXT = apply.get(1712);
        this.glVertexArrayVertexAttribOffsetEXT = apply.get(1713);
        this.glVertexArrayVertexAttribIOffsetEXT = apply.get(1714);
        this.glEnableVertexArrayEXT = apply.get(1715);
        this.glDisableVertexArrayEXT = apply.get(1716);
        this.glEnableVertexArrayAttribEXT = apply.get(1717);
        this.glDisableVertexArrayAttribEXT = apply.get(1718);
        this.glGetVertexArrayIntegervEXT = apply.get(1719);
        this.glGetVertexArrayPointervEXT = apply.get(1720);
        this.glGetVertexArrayIntegeri_vEXT = apply.get(1721);
        this.glGetVertexArrayPointeri_vEXT = apply.get(1722);
        this.glMapNamedBufferRangeEXT = apply.get(1723);
        this.glFlushMappedNamedBufferRangeEXT = apply.get(1724);
        this.glColorMaskIndexedEXT = apply.get(1725);
        this.glDrawArraysInstancedEXT = apply.get(1726);
        this.glDrawElementsInstancedEXT = apply.get(1727);
        this.glEGLImageTargetTexStorageEXT = apply.get(1728);
        this.glEGLImageTargetTextureStorageEXT = apply.get(1729);
        this.glBufferStorageExternalEXT = apply.get(1730);
        this.glNamedBufferStorageExternalEXT = apply.get(1731);
        this.glBlitFramebufferEXT = apply.get(1732);
        this.glBlitFramebufferLayersEXT = apply.get(1733);
        this.glBlitFramebufferLayerEXT = apply.get(1734);
        this.glRenderbufferStorageMultisampleEXT = apply.get(1735);
        this.glIsRenderbufferEXT = apply.get(1736);
        this.glBindRenderbufferEXT = apply.get(1737);
        this.glDeleteRenderbuffersEXT = apply.get(1738);
        this.glGenRenderbuffersEXT = apply.get(1739);
        this.glRenderbufferStorageEXT = apply.get(1740);
        this.glGetRenderbufferParameterivEXT = apply.get(1741);
        this.glIsFramebufferEXT = apply.get(1742);
        this.glBindFramebufferEXT = apply.get(1743);
        this.glDeleteFramebuffersEXT = apply.get(1744);
        this.glGenFramebuffersEXT = apply.get(1745);
        this.glCheckFramebufferStatusEXT = apply.get(1746);
        this.glFramebufferTexture1DEXT = apply.get(1747);
        this.glFramebufferTexture2DEXT = apply.get(1748);
        this.glFramebufferTexture3DEXT = apply.get(1749);
        this.glFramebufferRenderbufferEXT = apply.get(1750);
        this.glGetFramebufferAttachmentParameterivEXT = apply.get(1751);
        this.glGenerateMipmapEXT = apply.get(1752);
        this.glProgramParameteriEXT = apply.get(1753);
        this.glFramebufferTextureEXT = apply.get(1754);
        this.glFramebufferTextureLayerEXT = apply.get(1755);
        this.glFramebufferTextureFaceEXT = apply.get(1756);
        this.glProgramEnvParameters4fvEXT = apply.get(1757);
        this.glProgramLocalParameters4fvEXT = apply.get(1758);
        this.glVertexAttribI1iEXT = apply.get(1759);
        this.glVertexAttribI2iEXT = apply.get(1760);
        this.glVertexAttribI3iEXT = apply.get(1761);
        this.glVertexAttribI4iEXT = apply.get(1762);
        this.glVertexAttribI1uiEXT = apply.get(1763);
        this.glVertexAttribI2uiEXT = apply.get(1764);
        this.glVertexAttribI3uiEXT = apply.get(1765);
        this.glVertexAttribI4uiEXT = apply.get(1766);
        this.glVertexAttribI1ivEXT = apply.get(1767);
        this.glVertexAttribI2ivEXT = apply.get(1768);
        this.glVertexAttribI3ivEXT = apply.get(1769);
        this.glVertexAttribI4ivEXT = apply.get(1770);
        this.glVertexAttribI1uivEXT = apply.get(1771);
        this.glVertexAttribI2uivEXT = apply.get(1772);
        this.glVertexAttribI3uivEXT = apply.get(1773);
        this.glVertexAttribI4uivEXT = apply.get(1774);
        this.glVertexAttribI4bvEXT = apply.get(1775);
        this.glVertexAttribI4svEXT = apply.get(1776);
        this.glVertexAttribI4ubvEXT = apply.get(1777);
        this.glVertexAttribI4usvEXT = apply.get(1778);
        this.glVertexAttribIPointerEXT = apply.get(1779);
        this.glGetVertexAttribIivEXT = apply.get(1780);
        this.glGetVertexAttribIuivEXT = apply.get(1781);
        this.glGetUniformuivEXT = apply.get(1782);
        this.glBindFragDataLocationEXT = apply.get(1783);
        this.glGetFragDataLocationEXT = apply.get(1784);
        this.glUniform1uiEXT = apply.get(1785);
        this.glUniform2uiEXT = apply.get(1786);
        this.glUniform3uiEXT = apply.get(1787);
        this.glUniform4uiEXT = apply.get(1788);
        this.glUniform1uivEXT = apply.get(1789);
        this.glUniform2uivEXT = apply.get(1790);
        this.glUniform3uivEXT = apply.get(1791);
        this.glUniform4uivEXT = apply.get(GL11.GL_PASS_THROUGH_TOKEN);
        this.glGetUnsignedBytevEXT = apply.get(GL11.GL_POINT_TOKEN);
        this.glGetUnsignedBytei_vEXT = apply.get(GL11.GL_LINE_TOKEN);
        this.glDeleteMemoryObjectsEXT = apply.get(GL11.GL_POLYGON_TOKEN);
        this.glIsMemoryObjectEXT = apply.get(GL11.GL_BITMAP_TOKEN);
        this.glCreateMemoryObjectsEXT = apply.get(GL11.GL_DRAW_PIXEL_TOKEN);
        this.glMemoryObjectParameterivEXT = apply.get(GL11.GL_COPY_PIXEL_TOKEN);
        this.glGetMemoryObjectParameterivEXT = apply.get(GL11.GL_LINE_RESET_TOKEN);
        this.glTexStorageMem2DEXT = apply.get(HeadlessConfig.REPORT_INTERVAL);
        this.glTexStorageMem2DMultisampleEXT = apply.get(1801);
        this.glTexStorageMem3DEXT = apply.get(1802);
        this.glTexStorageMem3DMultisampleEXT = apply.get(1803);
        this.glBufferStorageMemEXT = apply.get(1804);
        this.glTextureStorageMem2DEXT = apply.get(1805);
        this.glTextureStorageMem2DMultisampleEXT = apply.get(1806);
        this.glTextureStorageMem3DEXT = apply.get(1807);
        this.glTextureStorageMem3DMultisampleEXT = apply.get(1808);
        this.glNamedBufferStorageMemEXT = apply.get(1809);
        this.glTexStorageMem1DEXT = apply.get(1810);
        this.glTextureStorageMem1DEXT = apply.get(1811);
        this.glImportMemoryFdEXT = apply.get(1812);
        this.glImportMemoryWin32HandleEXT = apply.get(1813);
        this.glImportMemoryWin32NameEXT = apply.get(1814);
        this.glPointParameterfEXT = apply.get(1815);
        this.glPointParameterfvEXT = apply.get(1816);
        this.glPolygonOffsetClampEXT = apply.get(1817);
        this.glProvokingVertexEXT = apply.get(1818);
        this.glRasterSamplesEXT = apply.get(1819);
        this.glSecondaryColor3bEXT = apply.get(1820);
        this.glSecondaryColor3sEXT = apply.get(1821);
        this.glSecondaryColor3iEXT = apply.get(1822);
        this.glSecondaryColor3fEXT = apply.get(1823);
        this.glSecondaryColor3dEXT = apply.get(1824);
        this.glSecondaryColor3ubEXT = apply.get(1825);
        this.glSecondaryColor3usEXT = apply.get(1826);
        this.glSecondaryColor3uiEXT = apply.get(1827);
        this.glSecondaryColor3bvEXT = apply.get(1828);
        this.glSecondaryColor3svEXT = apply.get(1829);
        this.glSecondaryColor3ivEXT = apply.get(1830);
        this.glSecondaryColor3fvEXT = apply.get(1831);
        this.glSecondaryColor3dvEXT = apply.get(1832);
        this.glSecondaryColor3ubvEXT = apply.get(1833);
        this.glSecondaryColor3usvEXT = apply.get(1834);
        this.glSecondaryColor3uivEXT = apply.get(1835);
        this.glSecondaryColorPointerEXT = apply.get(1836);
        this.glGenSemaphoresEXT = apply.get(1837);
        this.glDeleteSemaphoresEXT = apply.get(1838);
        this.glIsSemaphoreEXT = apply.get(1839);
        this.glSemaphoreParameterui64vEXT = apply.get(1840);
        this.glGetSemaphoreParameterui64vEXT = apply.get(1841);
        this.glWaitSemaphoreEXT = apply.get(1842);
        this.glSignalSemaphoreEXT = apply.get(1843);
        this.glImportSemaphoreFdEXT = apply.get(1844);
        this.glImportSemaphoreWin32HandleEXT = apply.get(1845);
        this.glImportSemaphoreWin32NameEXT = apply.get(1846);
        this.glUseShaderProgramEXT = apply.get(1847);
        this.glActiveProgramEXT = apply.get(1848);
        this.glCreateShaderProgramEXT = apply.get(1849);
        this.glFramebufferFetchBarrierEXT = apply.get(1850);
        this.glBindImageTextureEXT = apply.get(1851);
        this.glMemoryBarrierEXT = apply.get(1852);
        this.glStencilClearTagEXT = apply.get(1853);
        this.glActiveStencilFaceEXT = apply.get(1854);
        this.glTexBufferEXT = apply.get(1855);
        this.glClearColorIiEXT = apply.get(1856);
        this.glClearColorIuiEXT = apply.get(1857);
        this.glTexParameterIivEXT = apply.get(1858);
        this.glTexParameterIuivEXT = apply.get(1859);
        this.glGetTexParameterIivEXT = apply.get(1860);
        this.glGetTexParameterIuivEXT = apply.get(1861);
        this.glTexStorage1DEXT = apply.get(1862);
        this.glTexStorage2DEXT = apply.get(1863);
        this.glTexStorage3DEXT = apply.get(1864);
        this.glGetQueryObjecti64vEXT = apply.get(1865);
        this.glGetQueryObjectui64vEXT = apply.get(1866);
        this.glBindBufferRangeEXT = apply.get(1867);
        this.glBindBufferOffsetEXT = apply.get(1868);
        this.glBindBufferBaseEXT = apply.get(1869);
        this.glBeginTransformFeedbackEXT = apply.get(1870);
        this.glEndTransformFeedbackEXT = apply.get(1871);
        this.glTransformFeedbackVaryingsEXT = apply.get(1872);
        this.glGetTransformFeedbackVaryingEXT = apply.get(1873);
        this.glVertexAttribL1dEXT = apply.get(1874);
        this.glVertexAttribL2dEXT = apply.get(1875);
        this.glVertexAttribL3dEXT = apply.get(1876);
        this.glVertexAttribL4dEXT = apply.get(1877);
        this.glVertexAttribL1dvEXT = apply.get(1878);
        this.glVertexAttribL2dvEXT = apply.get(1879);
        this.glVertexAttribL3dvEXT = apply.get(1880);
        this.glVertexAttribL4dvEXT = apply.get(1881);
        this.glVertexAttribLPointerEXT = apply.get(1882);
        this.glGetVertexAttribLdvEXT = apply.get(1883);
        this.glAcquireKeyedMutexWin32EXT = apply.get(1884);
        this.glReleaseKeyedMutexWin32EXT = apply.get(1885);
        this.glWindowRectanglesEXT = apply.get(1886);
        this.glImportSyncEXT = apply.get(1887);
        this.glFrameTerminatorGREMEDY = apply.get(1888);
        this.glStringMarkerGREMEDY = apply.get(1889);
        this.glApplyFramebufferAttachmentCMAAINTEL = apply.get(1890);
        this.glSyncTextureINTEL = apply.get(1891);
        this.glUnmapTexture2DINTEL = apply.get(1892);
        this.glMapTexture2DINTEL = apply.get(1893);
        this.glBeginPerfQueryINTEL = apply.get(1894);
        this.glCreatePerfQueryINTEL = apply.get(1895);
        this.glDeletePerfQueryINTEL = apply.get(1896);
        this.glEndPerfQueryINTEL = apply.get(1897);
        this.glGetFirstPerfQueryIdINTEL = apply.get(1898);
        this.glGetNextPerfQueryIdINTEL = apply.get(1899);
        this.glGetPerfCounterInfoINTEL = apply.get(1900);
        this.glGetPerfQueryDataINTEL = apply.get(1901);
        this.glGetPerfQueryIdByNameINTEL = apply.get(1902);
        this.glGetPerfQueryInfoINTEL = apply.get(1903);
        this.glBlendBarrierKHR = apply.get(1904);
        this.glMaxShaderCompilerThreadsKHR = apply.get(1905);
        this.glFramebufferParameteriMESA = apply.get(1906);
        this.glGetFramebufferParameterivMESA = apply.get(1907);
        this.glAlphaToCoverageDitherControlNV = apply.get(1908);
        this.glMultiDrawArraysIndirectBindlessNV = apply.get(1909);
        this.glMultiDrawElementsIndirectBindlessNV = apply.get(1910);
        this.glMultiDrawArraysIndirectBindlessCountNV = apply.get(1911);
        this.glMultiDrawElementsIndirectBindlessCountNV = apply.get(1912);
        this.glGetTextureHandleNV = apply.get(1913);
        this.glGetTextureSamplerHandleNV = apply.get(1914);
        this.glMakeTextureHandleResidentNV = apply.get(1915);
        this.glMakeTextureHandleNonResidentNV = apply.get(1916);
        this.glGetImageHandleNV = apply.get(1917);
        this.glMakeImageHandleResidentNV = apply.get(1918);
        this.glMakeImageHandleNonResidentNV = apply.get(1919);
        this.glUniformHandleui64NV = apply.get(1920);
        this.glUniformHandleui64vNV = apply.get(1921);
        this.glProgramUniformHandleui64NV = apply.get(1922);
        this.glProgramUniformHandleui64vNV = apply.get(1923);
        this.glIsTextureHandleResidentNV = apply.get(1924);
        this.glIsImageHandleResidentNV = apply.get(1925);
        this.glBlendParameteriNV = apply.get(1926);
        this.glBlendBarrierNV = apply.get(1927);
        this.glViewportPositionWScaleNV = apply.get(1928);
        this.glCreateStatesNV = apply.get(1929);
        this.glDeleteStatesNV = apply.get(1930);
        this.glIsStateNV = apply.get(1931);
        this.glStateCaptureNV = apply.get(1932);
        this.glGetCommandHeaderNV = apply.get(1933);
        this.glGetStageIndexNV = apply.get(1934);
        this.glDrawCommandsNV = apply.get(1935);
        this.glDrawCommandsAddressNV = apply.get(1936);
        this.glDrawCommandsStatesNV = apply.get(1937);
        this.glDrawCommandsStatesAddressNV = apply.get(1938);
        this.glCreateCommandListsNV = apply.get(1939);
        this.glDeleteCommandListsNV = apply.get(1940);
        this.glIsCommandListNV = apply.get(1941);
        this.glListDrawCommandsStatesClientNV = apply.get(1942);
        this.glCommandListSegmentsNV = apply.get(1943);
        this.glCompileCommandListNV = apply.get(1944);
        this.glCallCommandListNV = apply.get(1945);
        this.glBeginConditionalRenderNV = apply.get(1946);
        this.glEndConditionalRenderNV = apply.get(1947);
        this.glSubpixelPrecisionBiasNV = apply.get(1948);
        this.glConservativeRasterParameterfNV = apply.get(1949);
        this.glConservativeRasterParameteriNV = apply.get(1950);
        this.glCopyImageSubDataNV = apply.get(1951);
        this.glDepthRangedNV = apply.get(1952);
        this.glClearDepthdNV = apply.get(1953);
        this.glDepthBoundsdNV = apply.get(1954);
        this.glDrawTextureNV = apply.get(1955);
        this.glDrawVkImageNV = apply.get(1956);
        this.glGetVkProcAddrNV = apply.get(1957);
        this.glWaitVkSemaphoreNV = apply.get(1958);
        this.glSignalVkSemaphoreNV = apply.get(1959);
        this.glSignalVkFenceNV = apply.get(1960);
        this.glGetMultisamplefvNV = apply.get(1961);
        this.glSampleMaskIndexedNV = apply.get(1962);
        this.glTexRenderbufferNV = apply.get(1963);
        this.glDeleteFencesNV = apply.get(1964);
        this.glGenFencesNV = apply.get(1965);
        this.glIsFenceNV = apply.get(1966);
        this.glTestFenceNV = apply.get(1967);
        this.glGetFenceivNV = apply.get(1968);
        this.glFinishFenceNV = apply.get(1969);
        this.glSetFenceNV = apply.get(1970);
        this.glFragmentCoverageColorNV = apply.get(1971);
        this.glCoverageModulationTableNV = apply.get(1972);
        this.glGetCoverageModulationTableNV = apply.get(1973);
        this.glCoverageModulationNV = apply.get(1974);
        this.glRenderbufferStorageMultisampleCoverageNV = apply.get(1975);
        this.glRenderGpuMaskNV = apply.get(1976);
        this.glMulticastBufferSubDataNV = apply.get(1977);
        this.glMulticastCopyBufferSubDataNV = apply.get(1978);
        this.glMulticastCopyImageSubDataNV = apply.get(1979);
        this.glMulticastBlitFramebufferNV = apply.get(1980);
        this.glMulticastFramebufferSampleLocationsfvNV = apply.get(1981);
        this.glMulticastBarrierNV = apply.get(1982);
        this.glMulticastWaitSyncNV = apply.get(1983);
        this.glMulticastGetQueryObjectivNV = apply.get(1984);
        this.glMulticastGetQueryObjectuivNV = apply.get(1985);
        this.glMulticastGetQueryObjecti64vNV = apply.get(1986);
        this.glMulticastGetQueryObjectui64vNV = apply.get(1987);
        this.glVertex2hNV = apply.get(1988);
        this.glVertex2hvNV = apply.get(1989);
        this.glVertex3hNV = apply.get(1990);
        this.glVertex3hvNV = apply.get(1991);
        this.glVertex4hNV = apply.get(1992);
        this.glVertex4hvNV = apply.get(1993);
        this.glNormal3hNV = apply.get(1994);
        this.glNormal3hvNV = apply.get(1995);
        this.glColor3hNV = apply.get(1996);
        this.glColor3hvNV = apply.get(1997);
        this.glColor4hNV = apply.get(1998);
        this.glColor4hvNV = apply.get(1999);
        this.glTexCoord1hNV = apply.get(2000);
        this.glTexCoord1hvNV = apply.get(2001);
        this.glTexCoord2hNV = apply.get(2002);
        this.glTexCoord2hvNV = apply.get(2003);
        this.glTexCoord3hNV = apply.get(2004);
        this.glTexCoord3hvNV = apply.get(2005);
        this.glTexCoord4hNV = apply.get(2006);
        this.glTexCoord4hvNV = apply.get(2007);
        this.glMultiTexCoord1hNV = apply.get(2008);
        this.glMultiTexCoord1hvNV = apply.get(2009);
        this.glMultiTexCoord2hNV = apply.get(2010);
        this.glMultiTexCoord2hvNV = apply.get(2011);
        this.glMultiTexCoord3hNV = apply.get(2012);
        this.glMultiTexCoord3hvNV = apply.get(2013);
        this.glMultiTexCoord4hNV = apply.get(2014);
        this.glMultiTexCoord4hvNV = apply.get(2015);
        this.glFogCoordhNV = apply.get(2016);
        this.glFogCoordhvNV = apply.get(2017);
        this.glSecondaryColor3hNV = apply.get(2018);
        this.glSecondaryColor3hvNV = apply.get(2019);
        this.glVertexWeighthNV = apply.get(2020);
        this.glVertexWeighthvNV = apply.get(2021);
        this.glVertexAttrib1hNV = apply.get(2022);
        this.glVertexAttrib1hvNV = apply.get(2023);
        this.glVertexAttrib2hNV = apply.get(2024);
        this.glVertexAttrib2hvNV = apply.get(2025);
        this.glVertexAttrib3hNV = apply.get(2026);
        this.glVertexAttrib3hvNV = apply.get(2027);
        this.glVertexAttrib4hNV = apply.get(2028);
        this.glVertexAttrib4hvNV = apply.get(2029);
        this.glVertexAttribs1hvNV = apply.get(2030);
        this.glVertexAttribs2hvNV = apply.get(2031);
        this.glVertexAttribs3hvNV = apply.get(2032);
        this.glVertexAttribs4hvNV = apply.get(2033);
        this.glGetInternalformatSampleivNV = apply.get(2034);
        this.glGetMemoryObjectDetachedResourcesuivNV = apply.get(2035);
        this.glResetMemoryObjectParameterNV = apply.get(2036);
        this.glTexAttachMemoryNV = apply.get(2037);
        this.glBufferAttachMemoryNV = apply.get(2038);
        this.glTextureAttachMemoryNV = apply.get(2039);
        this.glNamedBufferAttachMemoryNV = apply.get(2040);
        this.glBufferPageCommitmentMemNV = apply.get(2041);
        this.glNamedBufferPageCommitmentMemNV = apply.get(2042);
        this.glTexPageCommitmentMemNV = apply.get(2043);
        this.glTexturePageCommitmentMemNV = apply.get(2044);
        this.glDrawMeshTasksNV = apply.get(2045);
        this.glDrawMeshTasksIndirectNV = apply.get(2046);
        this.glMultiDrawMeshTasksIndirectNV = apply.get(2047);
        this.glMultiDrawMeshTasksIndirectCountNV = apply.get(2048);
        this.glPathCommandsNV = apply.get(GL11.GL_EXP2);
        this.glPathCoordsNV = apply.get(2050);
        this.glPathSubCommandsNV = apply.get(2051);
        this.glPathSubCoordsNV = apply.get(STBTruetype.STBTT_MS_LANG_CHINESE);
        this.glPathStringNV = apply.get(2053);
        this.glPathGlyphsNV = apply.get(2054);
        this.glPathGlyphRangeNV = apply.get(2055);
        this.glPathGlyphIndexArrayNV = apply.get(2056);
        this.glPathMemoryGlyphIndexArrayNV = apply.get(2057);
        this.glCopyPathNV = apply.get(2058);
        this.glWeightPathsNV = apply.get(2059);
        this.glInterpolatePathsNV = apply.get(2060);
        this.glTransformPathNV = apply.get(2061);
        this.glPathParameterivNV = apply.get(2062);
        this.glPathParameteriNV = apply.get(2063);
        this.glPathParameterfvNV = apply.get(2064);
        this.glPathParameterfNV = apply.get(2065);
        this.glPathDashArrayNV = apply.get(2066);
        this.glGenPathsNV = apply.get(2067);
        this.glDeletePathsNV = apply.get(2068);
        this.glIsPathNV = apply.get(2069);
        this.glPathStencilFuncNV = apply.get(2070);
        this.glPathStencilDepthOffsetNV = apply.get(2071);
        this.glStencilFillPathNV = apply.get(2072);
        this.glStencilStrokePathNV = apply.get(2073);
        this.glStencilFillPathInstancedNV = apply.get(2074);
        this.glStencilStrokePathInstancedNV = apply.get(2075);
        this.glPathCoverDepthFuncNV = apply.get(2076);
        this.glPathColorGenNV = apply.get(2077);
        this.glPathTexGenNV = apply.get(2078);
        this.glPathFogGenNV = apply.get(2079);
        this.glCoverFillPathNV = apply.get(2080);
        this.glCoverStrokePathNV = apply.get(2081);
        this.glCoverFillPathInstancedNV = apply.get(2082);
        this.glCoverStrokePathInstancedNV = apply.get(2083);
        this.glStencilThenCoverFillPathNV = apply.get(2084);
        this.glStencilThenCoverStrokePathNV = apply.get(2085);
        this.glStencilThenCoverFillPathInstancedNV = apply.get(2086);
        this.glStencilThenCoverStrokePathInstancedNV = apply.get(2087);
        this.glPathGlyphIndexRangeNV = apply.get(2088);
        this.glProgramPathFragmentInputGenNV = apply.get(2089);
        this.glGetPathParameterivNV = apply.get(2090);
        this.glGetPathParameterfvNV = apply.get(2091);
        this.glGetPathCommandsNV = apply.get(2092);
        this.glGetPathCoordsNV = apply.get(2093);
        this.glGetPathDashArrayNV = apply.get(2094);
        this.glGetPathMetricsNV = apply.get(2095);
        this.glGetPathMetricRangeNV = apply.get(2096);
        this.glGetPathSpacingNV = apply.get(2097);
        this.glGetPathColorGenivNV = apply.get(2098);
        this.glGetPathColorGenfvNV = apply.get(2099);
        this.glGetPathTexGenivNV = apply.get(2100);
        this.glGetPathTexGenfvNV = apply.get(2101);
        this.glIsPointInFillPathNV = apply.get(2102);
        this.glIsPointInStrokePathNV = apply.get(2103);
        this.glGetPathLengthNV = apply.get(2104);
        this.glPointAlongPathNV = apply.get(2105);
        this.glMatrixLoad3x2fNV = apply.get(2106);
        this.glMatrixLoad3x3fNV = apply.get(2107);
        this.glMatrixLoadTranspose3x3fNV = apply.get(2108);
        this.glMatrixMult3x2fNV = apply.get(2109);
        this.glMatrixMult3x3fNV = apply.get(2110);
        this.glMatrixMultTranspose3x3fNV = apply.get(2111);
        this.glGetProgramResourcefvNV = apply.get(2112);
        this.glPixelDataRangeNV = apply.get(2113);
        this.glFlushPixelDataRangeNV = apply.get(2114);
        this.glPointParameteriNV = apply.get(2115);
        this.glPointParameterivNV = apply.get(2116);
        this.glPrimitiveRestartNV = apply.get(2117);
        this.glPrimitiveRestartIndexNV = apply.get(2118);
        this.glQueryResourceNV = apply.get(2119);
        this.glGenQueryResourceTagNV = apply.get(2120);
        this.glDeleteQueryResourceTagNV = apply.get(2121);
        this.glQueryResourceTagNV = apply.get(2122);
        this.glFramebufferSampleLocationsfvNV = apply.get(2123);
        this.glNamedFramebufferSampleLocationsfvNV = apply.get(2124);
        this.glResolveDepthValuesNV = apply.get(2125);
        this.glScissorExclusiveArrayvNV = apply.get(2126);
        this.glScissorExclusiveNV = apply.get(2127);
        this.glMakeBufferResidentNV = apply.get(2128);
        this.glMakeBufferNonResidentNV = apply.get(2129);
        this.glIsBufferResidentNV = apply.get(2130);
        this.glMakeNamedBufferResidentNV = apply.get(2131);
        this.glMakeNamedBufferNonResidentNV = apply.get(2132);
        this.glIsNamedBufferResidentNV = apply.get(2133);
        this.glGetBufferParameterui64vNV = apply.get(2134);
        this.glGetNamedBufferParameterui64vNV = apply.get(2135);
        this.glGetIntegerui64vNV = apply.get(2136);
        this.glUniformui64NV = apply.get(2137);
        this.glUniformui64vNV = apply.get(2138);
        this.glProgramUniformui64NV = apply.get(2139);
        this.glProgramUniformui64vNV = apply.get(2140);
        this.glBindShadingRateImageNV = apply.get(2141);
        this.glShadingRateImagePaletteNV = apply.get(2142);
        this.glGetShadingRateImagePaletteNV = apply.get(2143);
        this.glShadingRateImageBarrierNV = apply.get(2144);
        this.glShadingRateSampleOrderNV = apply.get(2145);
        this.glShadingRateSampleOrderCustomNV = apply.get(2146);
        this.glGetShadingRateSampleLocationivNV = apply.get(2147);
        this.glTextureBarrierNV = apply.get(2148);
        this.glTexImage2DMultisampleCoverageNV = apply.get(2149);
        this.glTexImage3DMultisampleCoverageNV = apply.get(2150);
        this.glTextureImage2DMultisampleNV = apply.get(2151);
        this.glTextureImage3DMultisampleNV = apply.get(2152);
        this.glTextureImage2DMultisampleCoverageNV = apply.get(2153);
        this.glTextureImage3DMultisampleCoverageNV = apply.get(2154);
        this.glCreateSemaphoresNV = apply.get(2155);
        this.glSemaphoreParameterivNV = apply.get(2156);
        this.glGetSemaphoreParameterivNV = apply.get(2157);
        this.glBeginTransformFeedbackNV = apply.get(2158);
        this.glEndTransformFeedbackNV = apply.get(2159);
        this.glTransformFeedbackAttribsNV = apply.get(2160);
        this.glBindBufferRangeNV = apply.get(2161);
        this.glBindBufferOffsetNV = apply.get(2162);
        this.glBindBufferBaseNV = apply.get(2163);
        this.glTransformFeedbackVaryingsNV = apply.get(2164);
        this.glActiveVaryingNV = apply.get(2165);
        this.glGetVaryingLocationNV = apply.get(2166);
        this.glGetActiveVaryingNV = apply.get(2167);
        this.glGetTransformFeedbackVaryingNV = apply.get(2168);
        this.glTransformFeedbackStreamAttribsNV = apply.get(2169);
        this.glBindTransformFeedbackNV = apply.get(2170);
        this.glDeleteTransformFeedbacksNV = apply.get(2171);
        this.glGenTransformFeedbacksNV = apply.get(2172);
        this.glIsTransformFeedbackNV = apply.get(2173);
        this.glPauseTransformFeedbackNV = apply.get(2174);
        this.glResumeTransformFeedbackNV = apply.get(2175);
        this.glDrawTransformFeedbackNV = apply.get(2176);
        this.glVertexArrayRangeNV = apply.get(2177);
        this.glFlushVertexArrayRangeNV = apply.get(2178);
        this.glVertexAttribL1i64NV = apply.get(2179);
        this.glVertexAttribL2i64NV = apply.get(2180);
        this.glVertexAttribL3i64NV = apply.get(2181);
        this.glVertexAttribL4i64NV = apply.get(2182);
        this.glVertexAttribL1i64vNV = apply.get(2183);
        this.glVertexAttribL2i64vNV = apply.get(2184);
        this.glVertexAttribL3i64vNV = apply.get(2185);
        this.glVertexAttribL4i64vNV = apply.get(2186);
        this.glVertexAttribL1ui64NV = apply.get(2187);
        this.glVertexAttribL2ui64NV = apply.get(2188);
        this.glVertexAttribL3ui64NV = apply.get(2189);
        this.glVertexAttribL4ui64NV = apply.get(2190);
        this.glVertexAttribL1ui64vNV = apply.get(2191);
        this.glVertexAttribL2ui64vNV = apply.get(2192);
        this.glVertexAttribL3ui64vNV = apply.get(2193);
        this.glVertexAttribL4ui64vNV = apply.get(2194);
        this.glGetVertexAttribLi64vNV = apply.get(2195);
        this.glGetVertexAttribLui64vNV = apply.get(2196);
        this.glVertexAttribLFormatNV = apply.get(2197);
        this.glBufferAddressRangeNV = apply.get(2198);
        this.glVertexFormatNV = apply.get(2199);
        this.glNormalFormatNV = apply.get(2200);
        this.glColorFormatNV = apply.get(2201);
        this.glIndexFormatNV = apply.get(2202);
        this.glTexCoordFormatNV = apply.get(2203);
        this.glEdgeFlagFormatNV = apply.get(2204);
        this.glSecondaryColorFormatNV = apply.get(2205);
        this.glFogCoordFormatNV = apply.get(2206);
        this.glVertexAttribFormatNV = apply.get(2207);
        this.glVertexAttribIFormatNV = apply.get(2208);
        this.glGetIntegerui64i_vNV = apply.get(2209);
        this.glViewportSwizzleNV = apply.get(2210);
        this.glBeginConditionalRenderNVX = apply.get(2211);
        this.glEndConditionalRenderNVX = apply.get(2212);
        this.glAsyncCopyImageSubDataNVX = apply.get(2213);
        this.glAsyncCopyBufferSubDataNVX = apply.get(2214);
        this.glUploadGpuMaskNVX = apply.get(2215);
        this.glMulticastViewportArrayvNVX = apply.get(2216);
        this.glMulticastScissorArrayvNVX = apply.get(2217);
        this.glMulticastViewportPositionWScaleNVX = apply.get(2218);
        this.glCreateProgressFenceNVX = apply.get(2219);
        this.glSignalSemaphoreui64NVX = apply.get(2220);
        this.glWaitSemaphoreui64NVX = apply.get(2221);
        this.glClientWaitSemaphoreui64NVX = apply.get(2222);
        this.glFramebufferTextureMultiviewOVR = apply.get(2223);
        this.glNamedFramebufferTextureMultiviewOVR = apply.get(2224);
        this.addresses = ThreadLocalUtil.setupAddressBuffer(apply);
    }

    public final PointerBuffer getAddressBuffer() {
        return this.addresses;
    }

    public static void initialize() {
    }

    private static boolean check_GL11(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set, boolean z) {
        if (!set.contains("OpenGL11")) {
            return false;
        }
        int i = (!z || set.contains("GL_NV_vertex_buffer_unified_memory")) ? 0 : Integer.MIN_VALUE;
        return ((z || Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2, 3, 4, 5, 6, 8, 10, 11, 13, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 52, 53, 54, 56, 64, 65, 66, 67, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 85, 86, 87, 88, 90, 93, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 110, 112, 113, 114, 115, 116, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 138, 140, 141, 142, 143, 144, 145, 146, 147, 148, 150, 151, 152, 153, 154, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 192, 193, 194, 198, 199, 200, 201, HttpStatus.SC_ACCEPTED, 203, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_RESET_CONTENT, HttpStatus.SC_PARTIAL_CONTENT, HttpStatus.SC_MULTI_STATUS, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, User32.VK_OEM_4, User32.VK_OEM_5, 221, 222, User32.VK_OEM_8, CGL.kCGLCPDispatchTableSize, User32.VK_OEM_AX, 226, User32.VK_ICO_HELP, 228, User32.VK_PROCESSKEY, 230, User32.VK_PACKET, User32.VK_OEM_JUMP, 235, 236, User32.VK_OEM_PA3, User32.VK_OEM_WSCTRL, User32.VK_OEM_CUSEL, User32.VK_OEM_ATTN, User32.VK_OEM_FINISH, User32.VK_OEM_COPY, 243, User32.VK_OEM_ENLW, User32.VK_OEM_BACKTAB, User32.VK_ATTN, User32.VK_EXSEL, User32.VK_EREOF, User32.VK_PA1, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, GLFW.GLFW_KEY_DOWN, 265, GLFW.GLFW_KEY_PAGE_UP, GLFW.GLFW_KEY_PAGE_DOWN, GLFW.GLFW_KEY_HOME, 269, User32.WM_IME_ENDCOMPOSITION, 271, 272, 273, User32.WM_SYSCOMMAND, User32.WM_TIMER, User32.WM_HSCROLL, User32.WM_VSCROLL, User32.WM_INITMENU, User32.WM_INITMENUPOPUP, GLFW.GLFW_KEY_CAPS_LOCK, 281, 282, GLFW.GLFW_KEY_PRINT_SCREEN, GLFW.GLFW_KEY_PAUSE, 285, 286, User32.WM_MENUSELECT, User32.WM_MENUCHAR, User32.WM_ENTERIDLE, 290, 291, 292, 293, 294, 295, 308, 309, 310, 311, 312, 313, 314, CGL.kCGLCPMPSwapsInFlight, 316, 317, 318, 319, GLFW.GLFW_KEY_KP_0, GLFW.GLFW_KEY_KP_1, GLFW.GLFW_KEY_KP_2, GLFW.GLFW_KEY_KP_3, GLFW.GLFW_KEY_KP_4, GLFW.GLFW_KEY_KP_5, GLFW.GLFW_KEY_KP_6, GLFW.GLFW_KEY_KP_7, GLFW.GLFW_KEY_KP_8, GLFW.GLFW_KEY_KP_9, GLFW.GLFW_KEY_KP_DECIMAL, GLFW.GLFW_KEY_KP_DIVIDE, GLFW.GLFW_KEY_KP_MULTIPLY, GLFW.GLFW_KEY_KP_SUBTRACT, GLFW.GLFW_KEY_KP_ADD}, "glAccum", "glAlphaFunc", "glAreTexturesResident", "glArrayElement", "glBegin", "glBitmap", "glCallList", "glCallLists", "glClearAccum", "glClearIndex", "glClipPlane", "glColor3b", "glColor3s", "glColor3i", "glColor3f", "glColor3d", "glColor3ub", "glColor3us", "glColor3ui", "glColor3bv", "glColor3sv", "glColor3iv", "glColor3fv", "glColor3dv", "glColor3ubv", "glColor3usv", "glColor3uiv", "glColor4b", "glColor4s", "glColor4i", "glColor4f", "glColor4d", "glColor4ub", "glColor4us", "glColor4ui", "glColor4bv", "glColor4sv", "glColor4iv", "glColor4fv", "glColor4dv", "glColor4ubv", "glColor4usv", "glColor4uiv", "glColorMaterial", "glColorPointer", "glCopyPixels", "glDeleteLists", "glDrawPixels", "glEdgeFlag", "glEdgeFlagv", "glEdgeFlagPointer", "glEnd", "glEvalCoord1f", "glEvalCoord1fv", "glEvalCoord1d", "glEvalCoord1dv", "glEvalCoord2f", "glEvalCoord2fv", "glEvalCoord2d", "glEvalCoord2dv", "glEvalMesh1", "glEvalMesh2", "glEvalPoint1", "glEvalPoint2", "glFeedbackBuffer", "glFogi", "glFogiv", "glFogf", "glFogfv", "glGenLists", "glGetClipPlane", "glGetLightiv", "glGetLightfv", "glGetMapiv", "glGetMapfv", "glGetMapdv", "glGetMaterialiv", "glGetMaterialfv", "glGetPixelMapfv", "glGetPixelMapusv", "glGetPixelMapuiv", "glGetPolygonStipple", "glGetTexEnviv", "glGetTexEnvfv", "glGetTexGeniv", "glGetTexGenfv", "glGetTexGendv", "glIndexi", "glIndexub", "glIndexs", "glIndexf", "glIndexd", "glIndexiv", "glIndexubv", "glIndexsv", "glIndexfv", "glIndexdv", "glIndexMask", "glIndexPointer", "glInitNames", "glInterleavedArrays", "glIsList", "glLightModeli", "glLightModelf", "glLightModeliv", "glLightModelfv", "glLighti", "glLightf", "glLightiv", "glLightfv", "glLineStipple", "glListBase", "glLoadMatrixf", "glLoadMatrixd", "glLoadIdentity", "glLoadName", "glMap1f", "glMap1d", "glMap2f", "glMap2d", "glMapGrid1f", "glMapGrid1d", "glMapGrid2f", "glMapGrid2d", "glMateriali", "glMaterialf", "glMaterialiv", "glMaterialfv", "glMatrixMode", "glMultMatrixf", "glMultMatrixd", "glFrustum", "glNewList", "glEndList", "glNormal3f", "glNormal3b", "glNormal3s", "glNormal3i", "glNormal3d", "glNormal3fv", "glNormal3bv", "glNormal3sv", "glNormal3iv", "glNormal3dv", "glNormalPointer", "glOrtho", "glPassThrough", "glPixelMapfv", "glPixelMapusv", "glPixelMapuiv", "glPixelTransferi", "glPixelTransferf", "glPixelZoom", "glPolygonStipple", "glPushAttrib", "glPushClientAttrib", "glPopAttrib", "glPopClientAttrib", "glPopMatrix", "glPopName", "glPrioritizeTextures", "glPushMatrix", "glPushName", "glRasterPos2i", "glRasterPos2s", "glRasterPos2f", "glRasterPos2d", "glRasterPos2iv", "glRasterPos2sv", "glRasterPos2fv", "glRasterPos2dv", "glRasterPos3i", "glRasterPos3s", "glRasterPos3f", "glRasterPos3d", "glRasterPos3iv", "glRasterPos3sv", "glRasterPos3fv", "glRasterPos3dv", "glRasterPos4i", "glRasterPos4s", "glRasterPos4f", "glRasterPos4d", "glRasterPos4iv", "glRasterPos4sv", "glRasterPos4fv", "glRasterPos4dv", "glRecti", "glRects", "glRectf", "glRectd", "glRectiv", "glRectsv", "glRectfv", "glRectdv", "glRenderMode", "glRotatef", "glRotated", "glScalef", "glScaled", "glSelectBuffer", "glShadeModel", "glTexCoord1f", "glTexCoord1s", "glTexCoord1i", "glTexCoord1d", "glTexCoord1fv", "glTexCoord1sv", "glTexCoord1iv", "glTexCoord1dv", "glTexCoord2f", "glTexCoord2s", "glTexCoord2i", "glTexCoord2d", "glTexCoord2fv", "glTexCoord2sv", "glTexCoord2iv", "glTexCoord2dv", "glTexCoord3f", "glTexCoord3s", "glTexCoord3i", "glTexCoord3d", "glTexCoord3fv", "glTexCoord3sv", "glTexCoord3iv", "glTexCoord3dv", "glTexCoord4f", "glTexCoord4s", "glTexCoord4i", "glTexCoord4d", "glTexCoord4fv", "glTexCoord4sv", "glTexCoord4iv", "glTexCoord4dv", "glTexCoordPointer", "glTexEnvi", "glTexEnviv", "glTexEnvf", "glTexEnvfv", "glTexGeni", "glTexGeniv", "glTexGenf", "glTexGenfv", "glTexGend", "glTexGendv", "glTranslatef", "glTranslated", "glVertex2f", "glVertex2s", "glVertex2i", "glVertex2d", "glVertex2fv", "glVertex2sv", "glVertex2iv", "glVertex2dv", "glVertex3f", "glVertex3s", "glVertex3i", "glVertex3d", "glVertex3fv", "glVertex3sv", "glVertex3iv", "glVertex3dv", "glVertex4f", "glVertex4s", "glVertex4i", "glVertex4d", "glVertex4fv", "glVertex4sv", "glVertex4iv", "glVertex4dv", "glVertexPointer")) && Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{0, 1, 7, 9, 12, 14, 15, 17, 51, 55, 57, 58, 59, i + 60, 61, 62, 63, i + 68, 83, 84, 89, 91, 92, 94, 95, 96, 97, 98, 109, 111, 117, 118, 119, 120, 121, 122, 137, 139, 149, 155, 190, 191, 195, 196, 197, 232, User32.VK_OEM_RESET, User32.VK_CRSEL, User32.VK_PLAY, User32.VK_ZOOM, User32.VK_NONAME, 296, 297, GLFW.GLFW_KEY_F9, GLFW.GLFW_KEY_F10, 300, 301, 302, 303, 304, 305, 306, 307, GLFW.GLFW_KEY_KP_ENTER}, "glEnable", "glDisable", "glBindTexture", "glBlendFunc", "glClear", "glClearColor", "glClearDepth", "glClearStencil", "glColorMask", "glCullFace", "glDepthFunc", "glDepthMask", "glDepthRange", "glDisableClientState", "glDrawArrays", "glDrawBuffer", "glDrawElements", "glEnableClientState", "glFinish", "glFlush", "glFrontFace", "glGenTextures", "glDeleteTextures", "glGetBooleanv", "glGetFloatv", "glGetIntegerv", "glGetDoublev", "glGetError", "glGetPointerv", "glGetString", "glGetTexImage", "glGetTexLevelParameteriv", "glGetTexLevelParameterfv", "glGetTexParameteriv", "glGetTexParameterfv", "glHint", "glIsEnabled", "glIsTexture", "glLineWidth", "glLogicOp", "glPixelStorei", "glPixelStoref", "glPointSize", "glPolygonMode", "glPolygonOffset", "glReadBuffer", "glReadPixels", "glScissor", "glStencilFunc", "glStencilMask", "glStencilOp", "glTexImage1D", "glTexImage2D", "glCopyTexImage1D", "glCopyTexImage2D", "glCopyTexSubImage1D", "glCopyTexSubImage2D", "glTexParameteri", "glTexParameteriv", "glTexParameterf", "glTexParameterfv", "glTexSubImage1D", "glTexSubImage2D", "glViewport")) || Checks.reportMissing("GL", "OpenGL11");
    }

    private static boolean check_GL12(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL12")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{GLFW.GLFW_KEY_KP_EQUAL, 337, 338, 339}, "glTexImage3D", "glTexSubImage3D", "glCopyTexSubImage3D", "glDrawRangeElements") || Checks.reportMissing("GL", "OpenGL12");
        }
        return false;
    }

    private static boolean check_GL13(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set, boolean z) {
        if (set.contains("OpenGL13")) {
            return ((z || Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{349, 350, 351, MapEditorItemInfoMenu.MENU_CONTENT_WIDTH, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385}, "glClientActiveTexture", "glMultiTexCoord1f", "glMultiTexCoord1s", "glMultiTexCoord1i", "glMultiTexCoord1d", "glMultiTexCoord1fv", "glMultiTexCoord1sv", "glMultiTexCoord1iv", "glMultiTexCoord1dv", "glMultiTexCoord2f", "glMultiTexCoord2s", "glMultiTexCoord2i", "glMultiTexCoord2d", "glMultiTexCoord2fv", "glMultiTexCoord2sv", "glMultiTexCoord2iv", "glMultiTexCoord2dv", "glMultiTexCoord3f", "glMultiTexCoord3s", "glMultiTexCoord3i", "glMultiTexCoord3d", "glMultiTexCoord3fv", "glMultiTexCoord3sv", "glMultiTexCoord3iv", "glMultiTexCoord3dv", "glMultiTexCoord4f", "glMultiTexCoord4s", "glMultiTexCoord4i", "glMultiTexCoord4d", "glMultiTexCoord4fv", "glMultiTexCoord4sv", "glMultiTexCoord4iv", "glMultiTexCoord4dv", "glLoadTransposeMatrixf", "glLoadTransposeMatrixd", "glMultTransposeMatrixf", "glMultTransposeMatrixd")) && Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{GLFW.GLFW_KEY_LEFT_SHIFT, GLFW.GLFW_KEY_LEFT_CONTROL, GLFW.GLFW_KEY_LEFT_ALT, GLFW.GLFW_KEY_LEFT_SUPER, GLFW.GLFW_KEY_RIGHT_SHIFT, GLFW.GLFW_KEY_RIGHT_CONTROL, GLFW.GLFW_KEY_RIGHT_ALT, GLFW.GLFW_KEY_RIGHT_SUPER, 348}, "glCompressedTexImage3D", "glCompressedTexImage2D", "glCompressedTexImage1D", "glCompressedTexSubImage3D", "glCompressedTexSubImage2D", "glCompressedTexSubImage1D", "glGetCompressedTexImage", "glSampleCoverage", "glActiveTexture")) || Checks.reportMissing("GL", "OpenGL13");
        }
        return false;
    }

    private static boolean check_GL14(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set, boolean z) {
        if (set.contains("OpenGL14")) {
            return ((z || Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{388, 389, 390, 391, User32.WS_EX_PALETTEWINDOW, 399, 400, HttpStatus.SC_UNAUTHORIZED, HttpStatus.SC_PAYMENT_REQUIRED, HttpStatus.SC_FORBIDDEN, HttpStatus.SC_NOT_FOUND, HttpStatus.SC_METHOD_NOT_ALLOWED, HttpStatus.SC_NOT_ACCEPTABLE, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, HttpStatus.SC_REQUEST_TIMEOUT, HttpStatus.SC_CONFLICT, HttpStatus.SC_GONE, HttpStatus.SC_LENGTH_REQUIRED, HttpStatus.SC_PRECONDITION_FAILED, HttpStatus.SC_REQUEST_TOO_LONG, HttpStatus.SC_REQUEST_URI_TOO_LONG, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, HttpStatus.SC_EXPECTATION_FAILED, 418, HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, HttpStatus.SC_METHOD_FAILURE, 421, HttpStatus.SC_UNPROCESSABLE_ENTITY, HttpStatus.SC_LOCKED, HttpStatus.SC_FAILED_DEPENDENCY, 425, 426, 427, 428, 429, 430, 431, 432}, "glFogCoordf", "glFogCoordd", "glFogCoordfv", "glFogCoorddv", "glFogCoordPointer", "glSecondaryColor3b", "glSecondaryColor3s", "glSecondaryColor3i", "glSecondaryColor3f", "glSecondaryColor3d", "glSecondaryColor3ub", "glSecondaryColor3us", "glSecondaryColor3ui", "glSecondaryColor3bv", "glSecondaryColor3sv", "glSecondaryColor3iv", "glSecondaryColor3fv", "glSecondaryColor3dv", "glSecondaryColor3ubv", "glSecondaryColor3usv", "glSecondaryColor3uiv", "glSecondaryColorPointer", "glWindowPos2i", "glWindowPos2s", "glWindowPos2f", "glWindowPos2d", "glWindowPos2iv", "glWindowPos2sv", "glWindowPos2fv", "glWindowPos2dv", "glWindowPos3i", "glWindowPos3s", "glWindowPos3f", "glWindowPos3d", "glWindowPos3iv", "glWindowPos3sv", "glWindowPos3fv", "glWindowPos3dv")) && Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{386, 387, 393, 394, 395, 396, 397, 398, HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE}, "glBlendColor", "glBlendEquation", "glMultiDrawArrays", "glMultiDrawElements", "glPointParameterf", "glPointParameteri", "glPointParameterfv", "glPointParameteriv", "glBlendFuncSeparate")) || Checks.reportMissing("GL", "OpenGL14");
        }
        return false;
    }

    private static boolean check_GL15(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL15")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, FCNTL.S_IRWXU, 449, 450, 451}, "glBindBuffer", "glDeleteBuffers", "glGenBuffers", "glIsBuffer", "glBufferData", "glBufferSubData", "glGetBufferSubData", "glMapBuffer", "glUnmapBuffer", "glGetBufferParameteriv", "glGetBufferPointerv", "glGenQueries", "glDeleteQueries", "glIsQuery", "glBeginQuery", "glEndQuery", "glGetQueryiv", "glGetQueryObjectiv", "glGetQueryObjectuiv") || Checks.reportMissing("GL", "OpenGL15");
        }
        return false;
    }

    private static boolean check_GL20(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL20")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{452, 453, 454, 455, 456, 457, 458, 459, MusicListOverlay.MusicItem.AMPLITUDES_PREVIEW_WIDTH, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, User32.MN_GETHMENU, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, CGL.kCGLGOUseBuildCache, HttpStatus.SC_INSUFFICIENT_STORAGE, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, User32.WM_MBUTTONUP, User32.WM_MBUTTONDBLCLK, User32.WM_MOUSEWHEEL, User32.WM_XBUTTONDOWN, User32.WM_XBUTTONUP, User32.WM_XBUTTONDBLCLK, User32.WM_MOUSEHWHEEL, 527, User32.WM_PARENTNOTIFY, User32.WM_ENTERMENULOOP, User32.WM_EXITMENULOOP, User32.WM_NEXTMENU, User32.WM_SIZING, User32.WM_CAPTURECHANGED, User32.WM_MOVING, 535, User32.WM_POWERBROADCAST, User32.WM_DEVICECHANGE, 538, 539, 540, 541, 542, 543, User32.WM_MDICREATE}, "glCreateProgram", "glDeleteProgram", "glIsProgram", "glCreateShader", "glDeleteShader", "glIsShader", "glAttachShader", "glDetachShader", "glShaderSource", "glCompileShader", "glLinkProgram", "glUseProgram", "glValidateProgram", "glUniform1f", "glUniform2f", "glUniform3f", "glUniform4f", "glUniform1i", "glUniform2i", "glUniform3i", "glUniform4i", "glUniform1fv", "glUniform2fv", "glUniform3fv", "glUniform4fv", "glUniform1iv", "glUniform2iv", "glUniform3iv", "glUniform4iv", "glUniformMatrix2fv", "glUniformMatrix3fv", "glUniformMatrix4fv", "glGetShaderiv", "glGetProgramiv", "glGetShaderInfoLog", "glGetProgramInfoLog", "glGetAttachedShaders", "glGetUniformLocation", "glGetActiveUniform", "glGetUniformfv", "glGetUniformiv", "glGetShaderSource", "glVertexAttrib1f", "glVertexAttrib1s", "glVertexAttrib1d", "glVertexAttrib2f", "glVertexAttrib2s", "glVertexAttrib2d", "glVertexAttrib3f", "glVertexAttrib3s", "glVertexAttrib3d", "glVertexAttrib4f", "glVertexAttrib4s", "glVertexAttrib4d", "glVertexAttrib4Nub", "glVertexAttrib1fv", "glVertexAttrib1sv", "glVertexAttrib1dv", "glVertexAttrib2fv", "glVertexAttrib2sv", "glVertexAttrib2dv", "glVertexAttrib3fv", "glVertexAttrib3sv", "glVertexAttrib3dv", "glVertexAttrib4fv", "glVertexAttrib4sv", "glVertexAttrib4dv", "glVertexAttrib4iv", "glVertexAttrib4bv", "glVertexAttrib4ubv", "glVertexAttrib4usv", "glVertexAttrib4uiv", "glVertexAttrib4Nbv", "glVertexAttrib4Nsv", "glVertexAttrib4Niv", "glVertexAttrib4Nubv", "glVertexAttrib4Nusv", "glVertexAttrib4Nuiv", "glVertexAttribPointer", "glEnableVertexAttribArray", "glDisableVertexAttribArray", "glBindAttribLocation", "glGetActiveAttrib", "glGetAttribLocation", "glGetVertexAttribiv", "glGetVertexAttribfv", "glGetVertexAttribdv", "glGetVertexAttribPointerv", "glDrawBuffers", "glBlendEquationSeparate", "glStencilOpSeparate", "glStencilFuncSeparate", "glStencilMaskSeparate") || Checks.reportMissing("GL", "OpenGL20");
        }
        return false;
    }

    private static boolean check_GL21(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL21")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{User32.WM_MDIDESTROY, User32.WM_MDIACTIVATE, User32.WM_MDIRESTORE, User32.WM_MDINEXT, User32.WM_MDIMAXIMIZE, User32.WM_MDITILE}, "glUniformMatrix2x3fv", "glUniformMatrix3x2fv", "glUniformMatrix2x4fv", "glUniformMatrix4x2fv", "glUniformMatrix3x4fv", "glUniformMatrix4x3fv") || Checks.reportMissing("GL", "OpenGL21");
        }
        return false;
    }

    private static boolean check_GL30(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL30")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{User32.WM_MDICASCADE, User32.WM_MDIICONARRANGE, User32.WM_MDIGETACTIVE, 554, 555, 556, 557, 558, 559, User32.WM_MDISETMENU, User32.WM_ENTERSIZEMOVE, User32.WM_EXITSIZEMOVE, User32.WM_DROPFILES, User32.WM_MDIREFRESHMENU, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, User32.WM_TOUCH, 577, 578, 579, 580, 581, 582, 583, 584, BlizzardAbility.ICE_FIELD_LIFETIME_MIN, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, BlizzardAbility.ICE_FIELD_LIFETIME_MAX, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634}, "glGetStringi", "glClearBufferiv", "glClearBufferuiv", "glClearBufferfv", "glClearBufferfi", "glVertexAttribI1i", "glVertexAttribI2i", "glVertexAttribI3i", "glVertexAttribI4i", "glVertexAttribI1ui", "glVertexAttribI2ui", "glVertexAttribI3ui", "glVertexAttribI4ui", "glVertexAttribI1iv", "glVertexAttribI2iv", "glVertexAttribI3iv", "glVertexAttribI4iv", "glVertexAttribI1uiv", "glVertexAttribI2uiv", "glVertexAttribI3uiv", "glVertexAttribI4uiv", "glVertexAttribI4bv", "glVertexAttribI4sv", "glVertexAttribI4ubv", "glVertexAttribI4usv", "glVertexAttribIPointer", "glGetVertexAttribIiv", "glGetVertexAttribIuiv", "glUniform1ui", "glUniform2ui", "glUniform3ui", "glUniform4ui", "glUniform1uiv", "glUniform2uiv", "glUniform3uiv", "glUniform4uiv", "glGetUniformuiv", "glBindFragDataLocation", "glGetFragDataLocation", "glBeginConditionalRender", "glEndConditionalRender", "glMapBufferRange", "glFlushMappedBufferRange", "glClampColor", "glIsRenderbuffer", "glBindRenderbuffer", "glDeleteRenderbuffers", "glGenRenderbuffers", "glRenderbufferStorage", "glRenderbufferStorageMultisample", "glGetRenderbufferParameteriv", "glIsFramebuffer", "glBindFramebuffer", "glDeleteFramebuffers", "glGenFramebuffers", "glCheckFramebufferStatus", "glFramebufferTexture1D", "glFramebufferTexture2D", "glFramebufferTexture3D", "glFramebufferTextureLayer", "glFramebufferRenderbuffer", "glGetFramebufferAttachmentParameteriv", "glBlitFramebuffer", "glGenerateMipmap", "glTexParameterIiv", "glTexParameterIuiv", "glGetTexParameterIiv", "glGetTexParameterIuiv", "glColorMaski", "glGetBooleani_v", "glGetIntegeri_v", "glEnablei", "glDisablei", "glIsEnabledi", "glBindBufferRange", "glBindBufferBase", "glBeginTransformFeedback", "glEndTransformFeedback", "glTransformFeedbackVaryings", "glGetTransformFeedbackVarying", "glBindVertexArray", "glDeleteVertexArrays", "glGenVertexArrays", "glIsVertexArray") || Checks.reportMissing("GL", "OpenGL30");
        }
        return false;
    }

    private static boolean check_GL31(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL31")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{635, 636, 637, 638, 639, 640, User32.WM_IME_SETCONTEXT, User32.WM_IME_NOTIFY, User32.WM_IME_CONTROL, User32.WM_IME_COMPOSITIONFULL, User32.WM_IME_SELECT, User32.WM_IME_CHAR}, "glDrawArraysInstanced", "glDrawElementsInstanced", "glCopyBufferSubData", "glPrimitiveRestartIndex", "glTexBuffer", "glGetUniformIndices", "glGetActiveUniformsiv", "glGetActiveUniformName", "glGetUniformBlockIndex", "glGetActiveUniformBlockiv", "glGetActiveUniformBlockName", "glUniformBlockBinding") || Checks.reportMissing("GL", "OpenGL31");
        }
        return false;
    }

    private static boolean check_GL32(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL32")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{647, User32.WM_IME_REQUEST, 649, 650, 651, 652, 653, 654, 655, User32.WM_IME_KEYDOWN, User32.WM_IME_KEYUP, 658, 659, 660, 661, 662, 663, 664, 665}, "glGetBufferParameteri64v", "glDrawElementsBaseVertex", "glDrawRangeElementsBaseVertex", "glDrawElementsInstancedBaseVertex", "glMultiDrawElementsBaseVertex", "glProvokingVertex", "glTexImage2DMultisample", "glTexImage3DMultisample", "glGetMultisamplefv", "glSampleMaski", "glFramebufferTexture", "glFenceSync", "glIsSync", "glDeleteSync", "glClientWaitSync", "glWaitSync", "glGetInteger64v", "glGetInteger64i_v", "glGetSynciv") || Checks.reportMissing("GL", "OpenGL32");
        }
        return false;
    }

    private static boolean check_GL33(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set, boolean z) {
        if (set.contains("OpenGL33")) {
            return ((z || Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{686, 687, 688, User32.WM_WTSSESSION_CHANGE, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, User32.WM_TABLET_FIRST, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715}, "glVertexP2ui", "glVertexP3ui", "glVertexP4ui", "glVertexP2uiv", "glVertexP3uiv", "glVertexP4uiv", "glTexCoordP1ui", "glTexCoordP2ui", "glTexCoordP3ui", "glTexCoordP4ui", "glTexCoordP1uiv", "glTexCoordP2uiv", "glTexCoordP3uiv", "glTexCoordP4uiv", "glMultiTexCoordP1ui", "glMultiTexCoordP2ui", "glMultiTexCoordP3ui", "glMultiTexCoordP4ui", "glMultiTexCoordP1uiv", "glMultiTexCoordP2uiv", "glMultiTexCoordP3uiv", "glMultiTexCoordP4uiv", "glNormalP3ui", "glNormalP3uiv", "glColorP3ui", "glColorP4ui", "glColorP3uiv", "glColorP4uiv", "glSecondaryColorP3ui", "glSecondaryColorP3uiv")) && Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{666, 667, 668, 669, 670, 671, User32.WM_NCMOUSEHOVER, User32.WM_MOUSEHOVER, User32.WM_NCMOUSELEAVE, User32.WM_MOUSELEAVE, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 716, 717, 718, 719, 720, 721, 722, 723}, "glBindFragDataLocationIndexed", "glGetFragDataIndex", "glGenSamplers", "glDeleteSamplers", "glIsSampler", "glBindSampler", "glSamplerParameteri", "glSamplerParameterf", "glSamplerParameteriv", "glSamplerParameterfv", "glSamplerParameterIiv", "glSamplerParameterIuiv", "glGetSamplerParameteriv", "glGetSamplerParameterfv", "glGetSamplerParameterIiv", "glGetSamplerParameterIuiv", "glQueryCounter", "glGetQueryObjecti64v", "glGetQueryObjectui64v", "glVertexAttribDivisor", "glVertexAttribP1ui", "glVertexAttribP2ui", "glVertexAttribP3ui", "glVertexAttribP4ui", "glVertexAttribP1uiv", "glVertexAttribP2uiv", "glVertexAttribP3uiv", "glVertexAttribP4uiv")) || Checks.reportMissing("GL", "OpenGL33");
        }
        return false;
    }

    private static boolean check_GL40(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL40")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, User32.WM_TABLET_LAST, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769}, "glBlendEquationi", "glBlendEquationSeparatei", "glBlendFunci", "glBlendFuncSeparatei", "glDrawArraysIndirect", "glDrawElementsIndirect", "glUniform1d", "glUniform2d", "glUniform3d", "glUniform4d", "glUniform1dv", "glUniform2dv", "glUniform3dv", "glUniform4dv", "glUniformMatrix2dv", "glUniformMatrix3dv", "glUniformMatrix4dv", "glUniformMatrix2x3dv", "glUniformMatrix2x4dv", "glUniformMatrix3x2dv", "glUniformMatrix3x4dv", "glUniformMatrix4x2dv", "glUniformMatrix4x3dv", "glGetUniformdv", "glMinSampleShading", "glGetSubroutineUniformLocation", "glGetSubroutineIndex", "glGetActiveSubroutineUniformiv", "glGetActiveSubroutineUniformName", "glGetActiveSubroutineName", "glUniformSubroutinesuiv", "glGetUniformSubroutineuiv", "glGetProgramStageiv", "glPatchParameteri", "glPatchParameterfv", "glBindTransformFeedback", "glDeleteTransformFeedbacks", "glGenTransformFeedbacks", "glIsTransformFeedback", "glPauseTransformFeedback", "glResumeTransformFeedback", "glDrawTransformFeedback", "glDrawTransformFeedbackStream", "glBeginQueryIndexed", "glEndQueryIndexed", "glGetQueryIndexediv") || Checks.reportMissing("GL", "OpenGL40");
        }
        return false;
    }

    private static boolean check_GL41(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL41")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{770, 771, 772, 773, 774, 775, 776, User32.WM_PAINTCLIPBOARD, User32.WM_VSCROLLCLIPBOARD, User32.WM_SIZECLIPBOARD, User32.WM_ASKCBFORMATNAME, User32.WM_CHANGECBCHAIN, User32.WM_HSCROLLCLIPBOARD, User32.WM_QUERYNEWPALETTE, 784, 785, 786, EXTDisconnect.ALC_CONNECTED, 788, 789, 790, User32.WM_PRINT, User32.WM_PRINTCLIENT, User32.WM_APPCOMMAND, User32.WM_THEMECHANGED, 795, 796, User32.WM_CLIPBOARDUPDATE, User32.WM_DWMCOMPOSITIONCHANGED, User32.WM_DWMNCRENDERINGCHANGED, User32.WM_DWMCOLORIZATIONCOLORCHANGED, User32.WM_DWMWINDOWMAXIMIZEDCHANGE, 802, User32.WM_DWMSENDICONICTHUMBNAIL, 804, 805, User32.WM_DWMSENDICONICLIVEPREVIEWBITMAP, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, User32.WM_GETTITLEBARINFOEX, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, User32.WM_HANDHELDFIRST, 857}, "glReleaseShaderCompiler", "glShaderBinary", "glGetShaderPrecisionFormat", "glDepthRangef", "glClearDepthf", "glGetProgramBinary", "glProgramBinary", "glProgramParameteri", "glUseProgramStages", "glActiveShaderProgram", "glCreateShaderProgramv", "glBindProgramPipeline", "glDeleteProgramPipelines", "glGenProgramPipelines", "glIsProgramPipeline", "glGetProgramPipelineiv", "glProgramUniform1i", "glProgramUniform2i", "glProgramUniform3i", "glProgramUniform4i", "glProgramUniform1ui", "glProgramUniform2ui", "glProgramUniform3ui", "glProgramUniform4ui", "glProgramUniform1f", "glProgramUniform2f", "glProgramUniform3f", "glProgramUniform4f", "glProgramUniform1d", "glProgramUniform2d", "glProgramUniform3d", "glProgramUniform4d", "glProgramUniform1iv", "glProgramUniform2iv", "glProgramUniform3iv", "glProgramUniform4iv", "glProgramUniform1uiv", "glProgramUniform2uiv", "glProgramUniform3uiv", "glProgramUniform4uiv", "glProgramUniform1fv", "glProgramUniform2fv", "glProgramUniform3fv", "glProgramUniform4fv", "glProgramUniform1dv", "glProgramUniform2dv", "glProgramUniform3dv", "glProgramUniform4dv", "glProgramUniformMatrix2fv", "glProgramUniformMatrix3fv", "glProgramUniformMatrix4fv", "glProgramUniformMatrix2dv", "glProgramUniformMatrix3dv", "glProgramUniformMatrix4dv", "glProgramUniformMatrix2x3fv", "glProgramUniformMatrix3x2fv", "glProgramUniformMatrix2x4fv", "glProgramUniformMatrix4x2fv", "glProgramUniformMatrix3x4fv", "glProgramUniformMatrix4x3fv", "glProgramUniformMatrix2x3dv", "glProgramUniformMatrix3x2dv", "glProgramUniformMatrix2x4dv", "glProgramUniformMatrix4x2dv", "glProgramUniformMatrix3x4dv", "glProgramUniformMatrix4x3dv", "glValidateProgramPipeline", "glGetProgramPipelineInfoLog", "glVertexAttribL1d", "glVertexAttribL2d", "glVertexAttribL3d", "glVertexAttribL4d", "glVertexAttribL1dv", "glVertexAttribL2dv", "glVertexAttribL3dv", "glVertexAttribL4dv", "glVertexAttribLPointer", "glGetVertexAttribLdv", "glViewportArrayv", "glViewportIndexedf", "glViewportIndexedfv", "glScissorArrayv", "glScissorIndexed", "glScissorIndexedv", "glDepthRangeArrayv", "glDepthRangeIndexed", "glGetFloati_v", "glGetDoublei_v") || Checks.reportMissing("GL", "OpenGL41");
        }
        return false;
    }

    private static boolean check_GL42(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL42")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{858, 859, 860, 861, 862, User32.WM_HANDHELDLAST, User32.WM_AFXFIRST, 865, 866, 867, 868, 869}, "glGetActiveAtomicCounterBufferiv", "glTexStorage1D", "glTexStorage2D", "glTexStorage3D", "glDrawTransformFeedbackInstanced", "glDrawTransformFeedbackStreamInstanced", "glDrawArraysInstancedBaseInstance", "glDrawElementsInstancedBaseInstance", "glDrawElementsInstancedBaseVertexBaseInstance", "glBindImageTexture", "glMemoryBarrier", "glGetInternalformativ") || Checks.reportMissing("GL", "OpenGL42");
        }
        return false;
    }

    private static boolean check_GL43(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL43")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, User32.WM_AFXLAST, User32.WM_PENWINFIRST, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, User32.WM_PENWINLAST, 912}, "glClearBufferData", "glClearBufferSubData", "glDispatchCompute", "glDispatchComputeIndirect", "glCopyImageSubData", "glDebugMessageControl", "glDebugMessageInsert", "glDebugMessageCallback", "glGetDebugMessageLog", "glPushDebugGroup", "glPopDebugGroup", "glObjectLabel", "glGetObjectLabel", "glObjectPtrLabel", "glGetObjectPtrLabel", "glFramebufferParameteri", "glGetFramebufferParameteriv", "glGetInternalformati64v", "glInvalidateTexSubImage", "glInvalidateTexImage", "glInvalidateBufferSubData", "glInvalidateBufferData", "glInvalidateFramebuffer", "glInvalidateSubFramebuffer", "glMultiDrawArraysIndirect", "glMultiDrawElementsIndirect", "glGetProgramInterfaceiv", "glGetProgramResourceIndex", "glGetProgramResourceName", "glGetProgramResourceiv", "glGetProgramResourceLocation", "glGetProgramResourceLocationIndex", "glShaderStorageBlockBinding", "glTexBufferRange", "glTexStorage2DMultisample", "glTexStorage3DMultisample", "glTextureView", "glBindVertexBuffer", "glVertexAttribFormat", "glVertexAttribIFormat", "glVertexAttribLFormat", "glVertexAttribBinding", "glVertexBindingDivisor") || Checks.reportMissing("GL", "OpenGL43");
        }
        return false;
    }

    private static boolean check_GL44(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL44")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{913, 914, 915, 916, 917, 918, 919, 920, 921}, "glBufferStorage", "glClearTexSubImage", "glClearTexImage", "glBindBuffersBase", "glBindBuffersRange", "glBindTextures", "glBindSamplers", "glBindImageTextures", "glBindVertexBuffers") || Checks.reportMissing("GL", "OpenGL44");
        }
        return false;
    }

    private static boolean check_GL45(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("OpenGL45")) {
            return false;
        }
        functionProvider.getFunctionAddress("glGetMapdv");
        functionProvider.getFunctionAddress("glGetMapfv");
        functionProvider.getFunctionAddress("glGetMapiv");
        functionProvider.getFunctionAddress("glGetPixelMapfv");
        functionProvider.getFunctionAddress("glGetPixelMapuiv");
        functionProvider.getFunctionAddress("glGetPixelMapusv");
        functionProvider.getFunctionAddress("glGetPolygonStipple");
        if (set.contains("GL_ARB_imaging")) {
            functionProvider.getFunctionAddress("glGetColorTable");
        }
        if (set.contains("GL_ARB_imaging")) {
            functionProvider.getFunctionAddress("glGetConvolutionFilter");
        }
        if (set.contains("GL_ARB_imaging")) {
            functionProvider.getFunctionAddress("glGetSeparableFilter");
        }
        if (set.contains("GL_ARB_imaging")) {
            functionProvider.getFunctionAddress("glGetHistogram");
        }
        if (set.contains("GL_ARB_imaging")) {
            functionProvider.getFunctionAddress("glGetMinmax");
        }
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, Tower.MAX_LEVEL, 1000, CoreGraphics.kCGErrorIllegalArgument, CoreGraphics.kCGErrorInvalidConnection, CoreGraphics.kCGErrorInvalidContext, CoreGraphics.kCGErrorCannotComplete, 1005, CoreGraphics.kCGErrorNotImplemented, CoreGraphics.kCGErrorRangeCheck, CoreGraphics.kCGErrorTypeCheck, 1009, CoreGraphics.kCGErrorInvalidOperation, CoreGraphics.kCGErrorNoneAvailable, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1033, STBTruetype.STBTT_MS_LANG_ITALIAN, STBTruetype.STBTT_MS_LANG_KOREAN, STBTruetype.STBTT_MS_LANG_DUTCH}, "glClipControl", "glCreateTransformFeedbacks", "glTransformFeedbackBufferBase", "glTransformFeedbackBufferRange", "glGetTransformFeedbackiv", "glGetTransformFeedbacki_v", "glGetTransformFeedbacki64_v", "glCreateBuffers", "glNamedBufferStorage", "glNamedBufferData", "glNamedBufferSubData", "glCopyNamedBufferSubData", "glClearNamedBufferData", "glClearNamedBufferSubData", "glMapNamedBuffer", "glMapNamedBufferRange", "glUnmapNamedBuffer", "glFlushMappedNamedBufferRange", "glGetNamedBufferParameteriv", "glGetNamedBufferParameteri64v", "glGetNamedBufferPointerv", "glGetNamedBufferSubData", "glCreateFramebuffers", "glNamedFramebufferRenderbuffer", "glNamedFramebufferParameteri", "glNamedFramebufferTexture", "glNamedFramebufferTextureLayer", "glNamedFramebufferDrawBuffer", "glNamedFramebufferDrawBuffers", "glNamedFramebufferReadBuffer", "glInvalidateNamedFramebufferData", "glInvalidateNamedFramebufferSubData", "glClearNamedFramebufferiv", "glClearNamedFramebufferuiv", "glClearNamedFramebufferfv", "glClearNamedFramebufferfi", "glBlitNamedFramebuffer", "glCheckNamedFramebufferStatus", "glGetNamedFramebufferParameteriv", "glGetNamedFramebufferAttachmentParameteriv", "glCreateRenderbuffers", "glNamedRenderbufferStorage", "glNamedRenderbufferStorageMultisample", "glGetNamedRenderbufferParameteriv", "glCreateTextures", "glTextureBuffer", "glTextureBufferRange", "glTextureStorage1D", "glTextureStorage2D", "glTextureStorage3D", "glTextureStorage2DMultisample", "glTextureStorage3DMultisample", "glTextureSubImage1D", "glTextureSubImage2D", "glTextureSubImage3D", "glCompressedTextureSubImage1D", "glCompressedTextureSubImage2D", "glCompressedTextureSubImage3D", "glCopyTextureSubImage1D", "glCopyTextureSubImage2D", "glCopyTextureSubImage3D", "glTextureParameterf", "glTextureParameterfv", "glTextureParameteri", "glTextureParameterIiv", "glTextureParameterIuiv", "glTextureParameteriv", "glGenerateTextureMipmap", "glBindTextureUnit", "glGetTextureImage", "glGetCompressedTextureImage", "glGetTextureLevelParameterfv", "glGetTextureLevelParameteriv", "glGetTextureParameterfv", "glGetTextureParameterIiv", "glGetTextureParameterIuiv", "glGetTextureParameteriv", "glCreateVertexArrays", "glDisableVertexArrayAttrib", "glEnableVertexArrayAttrib", "glVertexArrayElementBuffer", "glVertexArrayVertexBuffer", "glVertexArrayVertexBuffers", "glVertexArrayAttribFormat", "glVertexArrayAttribIFormat", "glVertexArrayAttribLFormat", "glVertexArrayAttribBinding", "glVertexArrayBindingDivisor", "glGetVertexArrayiv", "glGetVertexArrayIndexediv", "glGetVertexArrayIndexed64iv", "glCreateSamplers", "glCreateProgramPipelines", "glCreateQueries", "glGetQueryBufferObjectiv", "glGetQueryBufferObjectuiv", "glGetQueryBufferObjecti64v", "glGetQueryBufferObjectui64v", "glMemoryBarrierByRegion", "glGetTextureSubImage", "glGetCompressedTextureSubImage", "glTextureBarrier", "glGetGraphicsResetStatus", "glReadnPixels", "glGetnUniformfv", "glGetnUniformiv", "glGetnUniformuiv") || Checks.reportMissing("GL", "OpenGL45");
    }

    private static boolean check_GL46(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenGL46")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1044, 1045, 1046, 1047}, "glMultiDrawArraysIndirectCount", "glMultiDrawElementsIndirectCount", "glPolygonOffsetClamp", "glSpecializeShader") || Checks.reportMissing("GL", "OpenGL46");
        }
        return false;
    }

    private static boolean check_AMD_debug_output(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_debug_output")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1048, STBTruetype.STBTT_MS_LANG_RUSSIAN, 1050, 1051}, "glDebugMessageEnableAMD", "glDebugMessageInsertAMD", "glDebugMessageCallbackAMD", "glGetDebugMessageLogAMD") || Checks.reportMissing("GL", "GL_AMD_debug_output");
        }
        return false;
    }

    private static boolean check_AMD_draw_buffers_blend(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_draw_buffers_blend")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1052, STBTruetype.STBTT_MS_LANG_SWEDISH, 1054, 1055}, "glBlendFuncIndexedAMD", "glBlendFuncSeparateIndexedAMD", "glBlendEquationIndexedAMD", "glBlendEquationSeparateIndexedAMD") || Checks.reportMissing("GL", "GL_AMD_draw_buffers_blend");
        }
        return false;
    }

    private static boolean check_AMD_framebuffer_multisample_advanced(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_framebuffer_multisample_advanced")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1056, 1057}, "glRenderbufferStorageMultisampleAdvancedAMD", "glNamedRenderbufferStorageMultisampleAdvancedAMD") || Checks.reportMissing("GL", "GL_AMD_framebuffer_multisample_advanced");
        }
        return false;
    }

    private static boolean check_AMD_gpu_shader_int64(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_AMD_gpu_shader_int64")) {
            return false;
        }
        int i = set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, i + 1076, i + 1077, i + 1078, i + 1079, i + Config.VIEWPORT_HEIGHT, i + 1081, i + 1082, i + 1083, i + 1084, i + 1085, i + 1086, i + 1087, i + 1088, i + 1089, i + 1090, i + 1091}, "glUniform1i64NV", "glUniform2i64NV", "glUniform3i64NV", "glUniform4i64NV", "glUniform1i64vNV", "glUniform2i64vNV", "glUniform3i64vNV", "glUniform4i64vNV", "glUniform1ui64NV", "glUniform2ui64NV", "glUniform3ui64NV", "glUniform4ui64NV", "glUniform1ui64vNV", "glUniform2ui64vNV", "glUniform3ui64vNV", "glUniform4ui64vNV", "glGetUniformi64vNV", "glGetUniformui64vNV", "glProgramUniform1i64NV", "glProgramUniform2i64NV", "glProgramUniform3i64NV", "glProgramUniform4i64NV", "glProgramUniform1i64vNV", "glProgramUniform2i64vNV", "glProgramUniform3i64vNV", "glProgramUniform4i64vNV", "glProgramUniform1ui64NV", "glProgramUniform2ui64NV", "glProgramUniform3ui64NV", "glProgramUniform4ui64NV", "glProgramUniform1ui64vNV", "glProgramUniform2ui64vNV", "glProgramUniform3ui64vNV", "glProgramUniform4ui64vNV") || Checks.reportMissing("GL", "GL_AMD_gpu_shader_int64");
    }

    private static boolean check_AMD_interleaved_elements(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_interleaved_elements")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1092}, "glVertexAttribParameteriAMD") || Checks.reportMissing("GL", "GL_AMD_interleaved_elements");
        }
        return false;
    }

    private static boolean check_AMD_occlusion_query_event(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_occlusion_query_event")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1093}, "glQueryObjectParameteruiAMD") || Checks.reportMissing("GL", "GL_AMD_occlusion_query_event");
        }
        return false;
    }

    private static boolean check_AMD_performance_monitor(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_performance_monitor")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103, 1104}, "glGetPerfMonitorGroupsAMD", "glGetPerfMonitorCountersAMD", "glGetPerfMonitorGroupStringAMD", "glGetPerfMonitorCounterStringAMD", "glGetPerfMonitorCounterInfoAMD", "glGenPerfMonitorsAMD", "glDeletePerfMonitorsAMD", "glSelectPerfMonitorCountersAMD", "glBeginPerfMonitorAMD", "glEndPerfMonitorAMD", "glGetPerfMonitorCounterDataAMD") || Checks.reportMissing("GL", "GL_AMD_performance_monitor");
        }
        return false;
    }

    private static boolean check_AMD_sample_positions(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_sample_positions")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1105}, "glSetMultisamplefvAMD") || Checks.reportMissing("GL", "GL_AMD_sample_positions");
        }
        return false;
    }

    private static boolean check_AMD_sparse_texture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_sparse_texture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1106, 1107}, "glTexStorageSparseAMD", "glTextureStorageSparseAMD") || Checks.reportMissing("GL", "GL_AMD_sparse_texture");
        }
        return false;
    }

    private static boolean check_AMD_stencil_operation_extended(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_stencil_operation_extended")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1108}, "glStencilOpValueAMD") || Checks.reportMissing("GL", "GL_AMD_stencil_operation_extended");
        }
        return false;
    }

    private static boolean check_AMD_vertex_shader_tessellator(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_AMD_vertex_shader_tessellator")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1109, 1110}, "glTessellationFactorAMD", "glTessellationModeAMD") || Checks.reportMissing("GL", "GL_AMD_vertex_shader_tessellator");
        }
        return false;
    }

    private static boolean check_ARB_base_instance(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_base_instance")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{User32.WM_AFXFIRST, 865, 866}, "glDrawArraysInstancedBaseInstance", "glDrawElementsInstancedBaseInstance", "glDrawElementsInstancedBaseVertexBaseInstance") || Checks.reportMissing("GL", "GL_ARB_base_instance");
        }
        return false;
    }

    private static boolean check_ARB_bindless_texture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_bindless_texture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1119, 1120, 1121, 1122, 1123, 1124, 1125, 1126}, "glGetTextureHandleARB", "glGetTextureSamplerHandleARB", "glMakeTextureHandleResidentARB", "glMakeTextureHandleNonResidentARB", "glGetImageHandleARB", "glMakeImageHandleResidentARB", "glMakeImageHandleNonResidentARB", "glUniformHandleui64ARB", "glUniformHandleui64vARB", "glProgramUniformHandleui64ARB", "glProgramUniformHandleui64vARB", "glIsTextureHandleResidentARB", "glIsImageHandleResidentARB", "glVertexAttribL1ui64ARB", "glVertexAttribL1ui64vARB", "glGetVertexAttribLui64vARB") || Checks.reportMissing("GL", "GL_ARB_bindless_texture");
        }
        return false;
    }

    private static boolean check_ARB_blend_func_extended(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_blend_func_extended")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{666, 667}, "glBindFragDataLocationIndexed", "glGetFragDataIndex") || Checks.reportMissing("GL", "GL_ARB_blend_func_extended");
        }
        return false;
    }

    private static boolean check_ARB_buffer_storage(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_buffer_storage")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{913, (set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE) + 1127}, "glBufferStorage", "glNamedBufferStorageEXT") || Checks.reportMissing("GL", "GL_ARB_buffer_storage");
        }
        return false;
    }

    private static boolean check_ARB_cl_event(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_cl_event")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1128}, "glCreateSyncFromCLeventARB") || Checks.reportMissing("GL", "GL_ARB_cl_event");
        }
        return false;
    }

    private static boolean check_ARB_clear_buffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_clear_buffer_object")) {
            return false;
        }
        int i = set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{870, 871, i + 1129, i + 1130}, "glClearBufferData", "glClearBufferSubData", "glClearNamedBufferDataEXT", "glClearNamedBufferSubDataEXT") || Checks.reportMissing("GL", "GL_ARB_clear_buffer_object");
    }

    private static boolean check_ARB_clear_texture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_clear_texture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{914, 915}, "glClearTexSubImage", "glClearTexImage") || Checks.reportMissing("GL", "GL_ARB_clear_texture");
        }
        return false;
    }

    private static boolean check_ARB_clip_control(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_clip_control")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{922}, "glClipControl") || Checks.reportMissing("GL", "GL_ARB_clip_control");
        }
        return false;
    }

    private static boolean check_ARB_color_buffer_float(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_color_buffer_float")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1131}, "glClampColorARB") || Checks.reportMissing("GL", "GL_ARB_color_buffer_float");
        }
        return false;
    }

    private static boolean check_ARB_compute_shader(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_compute_shader")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{872, 873}, "glDispatchCompute", "glDispatchComputeIndirect") || Checks.reportMissing("GL", "GL_ARB_compute_shader");
        }
        return false;
    }

    private static boolean check_ARB_compute_variable_group_size(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_compute_variable_group_size")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1132}, "glDispatchComputeGroupSizeARB") || Checks.reportMissing("GL", "GL_ARB_compute_variable_group_size");
        }
        return false;
    }

    private static boolean check_ARB_copy_buffer(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_copy_buffer")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{637}, "glCopyBufferSubData") || Checks.reportMissing("GL", "GL_ARB_copy_buffer");
        }
        return false;
    }

    private static boolean check_ARB_copy_image(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_copy_image")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{874}, "glCopyImageSubData") || Checks.reportMissing("GL", "GL_ARB_copy_image");
        }
        return false;
    }

    private static boolean check_ARB_debug_output(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_debug_output")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1133, 1134, 1135, 1136}, "glDebugMessageControlARB", "glDebugMessageInsertARB", "glDebugMessageCallbackARB", "glGetDebugMessageLogARB") || Checks.reportMissing("GL", "GL_ARB_debug_output");
        }
        return false;
    }

    private static boolean check_ARB_direct_state_access(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_direct_state_access")) {
            return false;
        }
        int i = ARB_transform_feedback2(set) ? 0 : Integer.MIN_VALUE;
        int i2 = ARB_uniform_buffer_object(set) ? 0 : Integer.MIN_VALUE;
        int i3 = ARB_buffer_storage(set) ? 0 : Integer.MIN_VALUE;
        int i4 = ARB_copy_buffer(set) ? 0 : Integer.MIN_VALUE;
        int i5 = ARB_clear_texture(set) ? 0 : Integer.MIN_VALUE;
        int i6 = ARB_map_buffer_range(set) ? 0 : Integer.MIN_VALUE;
        int i7 = ARB_framebuffer_object(set) ? 0 : Integer.MIN_VALUE;
        int i8 = ARB_framebuffer_no_attachments(set) ? 0 : Integer.MIN_VALUE;
        int i9 = ARB_invalidate_subdata(set) ? 0 : Integer.MIN_VALUE;
        int i10 = ARB_texture_buffer_object(set) ? 0 : Integer.MIN_VALUE;
        int i11 = ARB_texture_buffer_range(set) ? 0 : Integer.MIN_VALUE;
        int i12 = ARB_texture_storage(set) ? 0 : Integer.MIN_VALUE;
        int i13 = ARB_texture_storage_multisample(set) ? 0 : Integer.MIN_VALUE;
        int i14 = ARB_vertex_array_object(set) ? 0 : Integer.MIN_VALUE;
        int i15 = ARB_vertex_attrib_binding(set) ? 0 : Integer.MIN_VALUE;
        int i16 = ARB_multi_bind(set) ? 0 : Integer.MIN_VALUE;
        int i17 = ARB_sampler_objects(set) ? 0 : Integer.MIN_VALUE;
        int i18 = ARB_separate_shader_objects(set) ? 0 : Integer.MIN_VALUE;
        int i19 = ARB_query_buffer_object(set) ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{i + 923, i2 + 924, i2 + 925, i + 926, i + 927, i + 928, 929, i3 + 930, 931, 932, i4 + 933, i5 + 934, i5 + 935, 936, i6 + 937, 938, i6 + 939, 940, 941, 942, 943, i7 + 944, i7 + 945, i8 + 946, i7 + 947, i7 + 948, i7 + 949, i7 + 950, i7 + 951, i9 + 952, i9 + 953, i7 + 954, i7 + 955, i7 + 956, i7 + 957, i7 + 958, i7 + 959, i8 + 960, i7 + 961, i7 + 962, i7 + 963, i7 + 964, i7 + 965, 966, i10 + 967, i11 + 968, i12 + 969, i12 + 970, i12 + 971, i13 + 972, i13 + 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, i7 + 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, i14 + Tower.MAX_LEVEL, i14 + 1000, i14 + CoreGraphics.kCGErrorIllegalArgument, i14 + CoreGraphics.kCGErrorInvalidConnection, i15 + CoreGraphics.kCGErrorInvalidContext, i16 + CoreGraphics.kCGErrorCannotComplete, i15 + 1005, i15 + CoreGraphics.kCGErrorNotImplemented, i15 + CoreGraphics.kCGErrorRangeCheck, i15 + CoreGraphics.kCGErrorTypeCheck, i15 + 1009, i14 + CoreGraphics.kCGErrorInvalidOperation, i14 + CoreGraphics.kCGErrorNoneAvailable, i14 + 1012, i17 + 1013, i18 + 1014, 1015, i19 + 1018, i19 + 1016, i19 + 1019, i19 + 1017}, "glCreateTransformFeedbacks", "glTransformFeedbackBufferBase", "glTransformFeedbackBufferRange", "glGetTransformFeedbackiv", "glGetTransformFeedbacki_v", "glGetTransformFeedbacki64_v", "glCreateBuffers", "glNamedBufferStorage", "glNamedBufferData", "glNamedBufferSubData", "glCopyNamedBufferSubData", "glClearNamedBufferData", "glClearNamedBufferSubData", "glMapNamedBuffer", "glMapNamedBufferRange", "glUnmapNamedBuffer", "glFlushMappedNamedBufferRange", "glGetNamedBufferParameteriv", "glGetNamedBufferParameteri64v", "glGetNamedBufferPointerv", "glGetNamedBufferSubData", "glCreateFramebuffers", "glNamedFramebufferRenderbuffer", "glNamedFramebufferParameteri", "glNamedFramebufferTexture", "glNamedFramebufferTextureLayer", "glNamedFramebufferDrawBuffer", "glNamedFramebufferDrawBuffers", "glNamedFramebufferReadBuffer", "glInvalidateNamedFramebufferData", "glInvalidateNamedFramebufferSubData", "glClearNamedFramebufferiv", "glClearNamedFramebufferuiv", "glClearNamedFramebufferfv", "glClearNamedFramebufferfi", "glBlitNamedFramebuffer", "glCheckNamedFramebufferStatus", "glGetNamedFramebufferParameteriv", "glGetNamedFramebufferAttachmentParameteriv", "glCreateRenderbuffers", "glNamedRenderbufferStorage", "glNamedRenderbufferStorageMultisample", "glGetNamedRenderbufferParameteriv", "glCreateTextures", "glTextureBuffer", "glTextureBufferRange", "glTextureStorage1D", "glTextureStorage2D", "glTextureStorage3D", "glTextureStorage2DMultisample", "glTextureStorage3DMultisample", "glTextureSubImage1D", "glTextureSubImage2D", "glTextureSubImage3D", "glCompressedTextureSubImage1D", "glCompressedTextureSubImage2D", "glCompressedTextureSubImage3D", "glCopyTextureSubImage1D", "glCopyTextureSubImage2D", "glCopyTextureSubImage3D", "glTextureParameterf", "glTextureParameterfv", "glTextureParameteri", "glTextureParameterIiv", "glTextureParameterIuiv", "glTextureParameteriv", "glGenerateTextureMipmap", "glBindTextureUnit", "glGetTextureImage", "glGetCompressedTextureImage", "glGetTextureLevelParameterfv", "glGetTextureLevelParameteriv", "glGetTextureParameterfv", "glGetTextureParameterIiv", "glGetTextureParameterIuiv", "glGetTextureParameteriv", "glCreateVertexArrays", "glDisableVertexArrayAttrib", "glEnableVertexArrayAttrib", "glVertexArrayElementBuffer", "glVertexArrayVertexBuffer", "glVertexArrayVertexBuffers", "glVertexArrayAttribFormat", "glVertexArrayAttribIFormat", "glVertexArrayAttribLFormat", "glVertexArrayAttribBinding", "glVertexArrayBindingDivisor", "glGetVertexArrayiv", "glGetVertexArrayIndexediv", "glGetVertexArrayIndexed64iv", "glCreateSamplers", "glCreateProgramPipelines", "glCreateQueries", "glGetQueryBufferObjecti64v", "glGetQueryBufferObjectiv", "glGetQueryBufferObjectui64v", "glGetQueryBufferObjectuiv") || Checks.reportMissing("GL", "GL_ARB_direct_state_access");
    }

    private static boolean check_ARB_draw_buffers(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_draw_buffers")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1137}, "glDrawBuffersARB") || Checks.reportMissing("GL", "GL_ARB_draw_buffers");
        }
        return false;
    }

    private static boolean check_ARB_draw_buffers_blend(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_draw_buffers_blend")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1138, 1139, 1140, 1141}, "glBlendEquationiARB", "glBlendEquationSeparateiARB", "glBlendFunciARB", "glBlendFuncSeparateiARB") || Checks.reportMissing("GL", "GL_ARB_draw_buffers_blend");
        }
        return false;
    }

    private static boolean check_ARB_draw_elements_base_vertex(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_draw_elements_base_vertex")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{User32.WM_IME_REQUEST, 649, 650, 651}, "glDrawElementsBaseVertex", "glDrawRangeElementsBaseVertex", "glDrawElementsInstancedBaseVertex", "glMultiDrawElementsBaseVertex") || Checks.reportMissing("GL", "GL_ARB_draw_elements_base_vertex");
        }
        return false;
    }

    private static boolean check_ARB_draw_indirect(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_draw_indirect")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{728, 729}, "glDrawArraysIndirect", "glDrawElementsIndirect") || Checks.reportMissing("GL", "GL_ARB_draw_indirect");
        }
        return false;
    }

    private static boolean check_ARB_draw_instanced(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_draw_instanced")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1142, 1143}, "glDrawArraysInstancedARB", "glDrawElementsInstancedARB") || Checks.reportMissing("GL", "GL_ARB_draw_instanced");
        }
        return false;
    }

    private static boolean check_ARB_ES2_compatibility(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_ES2_compatibility")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{770, 771, 772, 773, 774}, "glReleaseShaderCompiler", "glShaderBinary", "glGetShaderPrecisionFormat", "glDepthRangef", "glClearDepthf") || Checks.reportMissing("GL", "GL_ARB_ES2_compatibility");
        }
        return false;
    }

    private static boolean check_ARB_ES3_1_compatibility(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_ES3_1_compatibility")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1020}, "glMemoryBarrierByRegion") || Checks.reportMissing("GL", "GL_ARB_ES3_1_compatibility");
        }
        return false;
    }

    private static boolean check_ARB_ES3_2_compatibility(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_ES3_2_compatibility")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1144}, "glPrimitiveBoundingBoxARB") || Checks.reportMissing("GL", "GL_ARB_ES3_2_compatibility");
        }
        return false;
    }

    private static boolean check_ARB_framebuffer_no_attachments(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_framebuffer_no_attachments")) {
            return false;
        }
        int i = set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{885, 886, i + 1145, i + 1146}, "glFramebufferParameteri", "glGetFramebufferParameteriv", "glNamedFramebufferParameteriEXT", "glGetNamedFramebufferParameterivEXT") || Checks.reportMissing("GL", "GL_ARB_framebuffer_no_attachments");
    }

    private static boolean check_ARB_framebuffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_framebuffer_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614}, "glIsRenderbuffer", "glBindRenderbuffer", "glDeleteRenderbuffers", "glGenRenderbuffers", "glRenderbufferStorage", "glRenderbufferStorageMultisample", "glGetRenderbufferParameteriv", "glIsFramebuffer", "glBindFramebuffer", "glDeleteFramebuffers", "glGenFramebuffers", "glCheckFramebufferStatus", "glFramebufferTexture1D", "glFramebufferTexture2D", "glFramebufferTexture3D", "glFramebufferTextureLayer", "glFramebufferRenderbuffer", "glGetFramebufferAttachmentParameteriv", "glBlitFramebuffer", "glGenerateMipmap") || Checks.reportMissing("GL", "GL_ARB_framebuffer_object");
        }
        return false;
    }

    private static boolean check_ARB_geometry_shader4(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_geometry_shader4")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1147, 1148, 1149, 1150}, "glProgramParameteriARB", "glFramebufferTextureARB", "glFramebufferTextureLayerARB", "glFramebufferTextureFaceARB") || Checks.reportMissing("GL", "GL_ARB_geometry_shader4");
        }
        return false;
    }

    private static boolean check_ARB_get_program_binary(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_get_program_binary")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{775, 776, User32.WM_PAINTCLIPBOARD}, "glGetProgramBinary", "glProgramBinary", "glProgramParameteri") || Checks.reportMissing("GL", "GL_ARB_get_program_binary");
        }
        return false;
    }

    private static boolean check_ARB_get_texture_sub_image(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_get_texture_sub_image")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1021, 1022}, "glGetTextureSubImage", "glGetCompressedTextureSubImage") || Checks.reportMissing("GL", "GL_ARB_get_texture_sub_image");
        }
        return false;
    }

    private static boolean check_ARB_gl_spirv(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_gl_spirv")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1151}, "glSpecializeShaderARB") || Checks.reportMissing("GL", "GL_ARB_gl_spirv");
        }
        return false;
    }

    private static boolean check_ARB_gpu_shader_fp64(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_gpu_shader_fp64")) {
            return false;
        }
        set.contains("GL_EXT_direct_state_access");
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{730, 731, 732, 733, 734, User32.WM_TABLET_LAST, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747}, "glUniform1d", "glUniform2d", "glUniform3d", "glUniform4d", "glUniform1dv", "glUniform2dv", "glUniform3dv", "glUniform4dv", "glUniformMatrix2dv", "glUniformMatrix3dv", "glUniformMatrix4dv", "glUniformMatrix2x3dv", "glUniformMatrix2x4dv", "glUniformMatrix3x2dv", "glUniformMatrix3x4dv", "glUniformMatrix4x2dv", "glUniformMatrix4x3dv", "glGetUniformdv") || Checks.reportMissing("GL", "GL_ARB_gpu_shader_fp64");
    }

    private static boolean check_ARB_gpu_shader_int64(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_gpu_shader_int64")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1169, 1170, 1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178, 1179, 1180, 1181, 1182, 1183, 1184, 1185, 1186, 1187, 1188, 1189, 1190, 1191, 1192, 1193, 1194, 1195, 1196, 1197, 1198, 1199, 1200, 1201, 1202, 1203, 1204}, "glUniform1i64ARB", "glUniform1i64vARB", "glProgramUniform1i64ARB", "glProgramUniform1i64vARB", "glUniform2i64ARB", "glUniform2i64vARB", "glProgramUniform2i64ARB", "glProgramUniform2i64vARB", "glUniform3i64ARB", "glUniform3i64vARB", "glProgramUniform3i64ARB", "glProgramUniform3i64vARB", "glUniform4i64ARB", "glUniform4i64vARB", "glProgramUniform4i64ARB", "glProgramUniform4i64vARB", "glUniform1ui64ARB", "glUniform1ui64vARB", "glProgramUniform1ui64ARB", "glProgramUniform1ui64vARB", "glUniform2ui64ARB", "glUniform2ui64vARB", "glProgramUniform2ui64ARB", "glProgramUniform2ui64vARB", "glUniform3ui64ARB", "glUniform3ui64vARB", "glProgramUniform3ui64ARB", "glProgramUniform3ui64vARB", "glUniform4ui64ARB", "glUniform4ui64vARB", "glProgramUniform4ui64ARB", "glProgramUniform4ui64vARB", "glGetUniformi64vARB", "glGetUniformui64vARB", "glGetnUniformi64vARB", "glGetnUniformui64vARB") || Checks.reportMissing("GL", "GL_ARB_gpu_shader_int64");
        }
        return false;
    }

    private static boolean check_ARB_imaging(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set, boolean z) {
        if (set.contains("GL_ARB_imaging")) {
            return ((z || Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1205, 1206, 1207, 1208, 1209, 1210, 1211, 1212, 1213, 1214, 1215, 1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236}, "glColorTable", "glCopyColorTable", "glColorTableParameteriv", "glColorTableParameterfv", "glGetColorTable", "glGetColorTableParameteriv", "glGetColorTableParameterfv", "glColorSubTable", "glCopyColorSubTable", "glConvolutionFilter1D", "glConvolutionFilter2D", "glCopyConvolutionFilter1D", "glCopyConvolutionFilter2D", "glGetConvolutionFilter", "glSeparableFilter2D", "glGetSeparableFilter", "glConvolutionParameteri", "glConvolutionParameteriv", "glConvolutionParameterf", "glConvolutionParameterfv", "glGetConvolutionParameteriv", "glGetConvolutionParameterfv", "glHistogram", "glResetHistogram", "glGetHistogram", "glGetHistogramParameteriv", "glGetHistogramParameterfv", "glMinmax", "glResetMinmax", "glGetMinmax", "glGetMinmaxParameteriv", "glGetMinmaxParameterfv")) && Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{386, 387}, "glBlendColor", "glBlendEquation")) || Checks.reportMissing("GL", "GL_ARB_imaging");
        }
        return false;
    }

    private static boolean check_ARB_indirect_parameters(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_indirect_parameters")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1237, 1238}, "glMultiDrawArraysIndirectCountARB", "glMultiDrawElementsIndirectCountARB") || Checks.reportMissing("GL", "GL_ARB_indirect_parameters");
        }
        return false;
    }

    private static boolean check_ARB_instanced_arrays(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_instanced_arrays")) {
            return false;
        }
        set.contains("GL_EXT_direct_state_access");
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1239}, "glVertexAttribDivisorARB") || Checks.reportMissing("GL", "GL_ARB_instanced_arrays");
    }

    private static boolean check_ARB_internalformat_query(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_internalformat_query")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{869}, "glGetInternalformativ") || Checks.reportMissing("GL", "GL_ARB_internalformat_query");
        }
        return false;
    }

    private static boolean check_ARB_internalformat_query2(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_internalformat_query2")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{887}, "glGetInternalformati64v") || Checks.reportMissing("GL", "GL_ARB_internalformat_query2");
        }
        return false;
    }

    private static boolean check_ARB_invalidate_subdata(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_invalidate_subdata")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{888, 889, 890, 891, 892, 893}, "glInvalidateTexSubImage", "glInvalidateTexImage", "glInvalidateBufferSubData", "glInvalidateBufferData", "glInvalidateFramebuffer", "glInvalidateSubFramebuffer") || Checks.reportMissing("GL", "GL_ARB_invalidate_subdata");
        }
        return false;
    }

    private static boolean check_ARB_map_buffer_range(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_map_buffer_range")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{592, 593}, "glMapBufferRange", "glFlushMappedBufferRange") || Checks.reportMissing("GL", "GL_ARB_map_buffer_range");
        }
        return false;
    }

    private static boolean check_ARB_matrix_palette(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_matrix_palette")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1241, 1242, 1243, 1244, 1245}, "glCurrentPaletteMatrixARB", "glMatrixIndexuivARB", "glMatrixIndexubvARB", "glMatrixIndexusvARB", "glMatrixIndexPointerARB") || Checks.reportMissing("GL", "GL_ARB_matrix_palette");
        }
        return false;
    }

    private static boolean check_ARB_multi_bind(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_multi_bind")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{916, 917, 918, 919, 920, 921}, "glBindBuffersBase", "glBindBuffersRange", "glBindTextures", "glBindSamplers", "glBindImageTextures", "glBindVertexBuffers") || Checks.reportMissing("GL", "GL_ARB_multi_bind");
        }
        return false;
    }

    private static boolean check_ARB_multi_draw_indirect(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_multi_draw_indirect")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{894, User32.WM_AFXLAST}, "glMultiDrawArraysIndirect", "glMultiDrawElementsIndirect") || Checks.reportMissing("GL", "GL_ARB_multi_draw_indirect");
        }
        return false;
    }

    private static boolean check_ARB_multisample(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_multisample")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1246}, "glSampleCoverageARB") || Checks.reportMissing("GL", "GL_ARB_multisample");
        }
        return false;
    }

    private static boolean check_ARB_multitexture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_multitexture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280}, "glActiveTextureARB", "glClientActiveTextureARB", "glMultiTexCoord1fARB", "glMultiTexCoord1sARB", "glMultiTexCoord1iARB", "glMultiTexCoord1dARB", "glMultiTexCoord1fvARB", "glMultiTexCoord1svARB", "glMultiTexCoord1ivARB", "glMultiTexCoord1dvARB", "glMultiTexCoord2fARB", "glMultiTexCoord2sARB", "glMultiTexCoord2iARB", "glMultiTexCoord2dARB", "glMultiTexCoord2fvARB", "glMultiTexCoord2svARB", "glMultiTexCoord2ivARB", "glMultiTexCoord2dvARB", "glMultiTexCoord3fARB", "glMultiTexCoord3sARB", "glMultiTexCoord3iARB", "glMultiTexCoord3dARB", "glMultiTexCoord3fvARB", "glMultiTexCoord3svARB", "glMultiTexCoord3ivARB", "glMultiTexCoord3dvARB", "glMultiTexCoord4fARB", "glMultiTexCoord4sARB", "glMultiTexCoord4iARB", "glMultiTexCoord4dARB", "glMultiTexCoord4fvARB", "glMultiTexCoord4svARB", "glMultiTexCoord4ivARB", "glMultiTexCoord4dvARB") || Checks.reportMissing("GL", "GL_ARB_multitexture");
        }
        return false;
    }

    private static boolean check_ARB_occlusion_query(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_occlusion_query")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288}, "glGenQueriesARB", "glDeleteQueriesARB", "glIsQueryARB", "glBeginQueryARB", "glEndQueryARB", "glGetQueryivARB", "glGetQueryObjectivARB", "glGetQueryObjectuivARB") || Checks.reportMissing("GL", "GL_ARB_occlusion_query");
        }
        return false;
    }

    private static boolean check_ARB_parallel_shader_compile(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_parallel_shader_compile")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1289}, "glMaxShaderCompilerThreadsARB") || Checks.reportMissing("GL", "GL_ARB_parallel_shader_compile");
        }
        return false;
    }

    private static boolean check_ARB_point_parameters(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_point_parameters")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1290, 1291}, "glPointParameterfARB", "glPointParameterfvARB") || Checks.reportMissing("GL", "GL_ARB_point_parameters");
        }
        return false;
    }

    private static boolean check_ARB_polygon_offset_clamp(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_polygon_offset_clamp")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1046}, "glPolygonOffsetClamp") || Checks.reportMissing("GL", "GL_ARB_polygon_offset_clamp");
        }
        return false;
    }

    private static boolean check_ARB_program_interface_query(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_program_interface_query")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{User32.WM_PENWINFIRST, 897, 898, 899, 900, 901}, "glGetProgramInterfaceiv", "glGetProgramResourceIndex", "glGetProgramResourceName", "glGetProgramResourceiv", "glGetProgramResourceLocation", "glGetProgramResourceLocationIndex") || Checks.reportMissing("GL", "GL_ARB_program_interface_query");
        }
        return false;
    }

    private static boolean check_ARB_provoking_vertex(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_provoking_vertex")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{652}, "glProvokingVertex") || Checks.reportMissing("GL", "GL_ARB_provoking_vertex");
        }
        return false;
    }

    private static boolean check_ARB_robustness(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_robustness")) {
            return false;
        }
        int i = functionProvider.getFunctionAddress("glGetMapdv") != 0 ? 0 : Integer.MIN_VALUE;
        int i2 = functionProvider.getFunctionAddress("glGetMapfv") != 0 ? 0 : Integer.MIN_VALUE;
        int i3 = functionProvider.getFunctionAddress("glGetMapiv") != 0 ? 0 : Integer.MIN_VALUE;
        int i4 = functionProvider.getFunctionAddress("glGetPixelMapfv") != 0 ? 0 : Integer.MIN_VALUE;
        int i5 = functionProvider.getFunctionAddress("glGetPixelMapuiv") != 0 ? 0 : Integer.MIN_VALUE;
        int i6 = functionProvider.getFunctionAddress("glGetPixelMapusv") != 0 ? 0 : Integer.MIN_VALUE;
        int i7 = functionProvider.getFunctionAddress("glGetPolygonStipple") != 0 ? 0 : Integer.MIN_VALUE;
        int i8 = (!set.contains("GL_ARB_imaging") || functionProvider.getFunctionAddress("glGetColorTable") == 0) ? Integer.MIN_VALUE : 0;
        int i9 = (!set.contains("GL_ARB_imaging") || functionProvider.getFunctionAddress("glGetConvolutionFilter") == 0) ? Integer.MIN_VALUE : 0;
        int i10 = (!set.contains("GL_ARB_imaging") || functionProvider.getFunctionAddress("glGetSeparableFilter") == 0) ? Integer.MIN_VALUE : 0;
        int i11 = (!set.contains("GL_ARB_imaging") || functionProvider.getFunctionAddress("glGetHistogram") == 0) ? Integer.MIN_VALUE : 0;
        int i12 = (!set.contains("GL_ARB_imaging") || functionProvider.getFunctionAddress("glGetMinmax") == 0) ? Integer.MIN_VALUE : 0;
        int i13 = set.contains("OpenGL13") ? 0 : Integer.MIN_VALUE;
        int i14 = set.contains("OpenGL20") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1292, i + 1293, i2 + 1294, i3 + 1295, i4 + 1296, i5 + 1297, i6 + 1298, i7 + 1299, 1300, 1301, i8 + 1302, i9 + 1303, i10 + 1304, i11 + 1305, i12 + 1306, i13 + 1307, i14 + 1308, i14 + 1309, (set.contains("OpenGL30") ? 0 : Integer.MIN_VALUE) + 1310, (set.contains("OpenGL40") ? 0 : Integer.MIN_VALUE) + 1311}, "glGetGraphicsResetStatusARB", "glGetnMapdvARB", "glGetnMapfvARB", "glGetnMapivARB", "glGetnPixelMapfvARB", "glGetnPixelMapuivARB", "glGetnPixelMapusvARB", "glGetnPolygonStippleARB", "glGetnTexImageARB", "glReadnPixelsARB", "glGetnColorTableARB", "glGetnConvolutionFilterARB", "glGetnSeparableFilterARB", "glGetnHistogramARB", "glGetnMinmaxARB", "glGetnCompressedTexImageARB", "glGetnUniformfvARB", "glGetnUniformivARB", "glGetnUniformuivARB", "glGetnUniformdvARB") || Checks.reportMissing("GL", "GL_ARB_robustness");
    }

    private static boolean check_ARB_sample_locations(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_sample_locations")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1312, 1313, 1314}, "glFramebufferSampleLocationsfvARB", "glNamedFramebufferSampleLocationsfvARB", "glEvaluateDepthValuesARB") || Checks.reportMissing("GL", "GL_ARB_sample_locations");
        }
        return false;
    }

    private static boolean check_ARB_sample_shading(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_sample_shading")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1315}, "glMinSampleShadingARB") || Checks.reportMissing("GL", "GL_ARB_sample_shading");
        }
        return false;
    }

    private static boolean check_ARB_sampler_objects(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_sampler_objects")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{668, 669, 670, 671, User32.WM_NCMOUSEHOVER, User32.WM_MOUSEHOVER, User32.WM_NCMOUSELEAVE, User32.WM_MOUSELEAVE, 676, 677, 678, 679, 680, 681}, "glGenSamplers", "glDeleteSamplers", "glIsSampler", "glBindSampler", "glSamplerParameteri", "glSamplerParameterf", "glSamplerParameteriv", "glSamplerParameterfv", "glSamplerParameterIiv", "glSamplerParameterIuiv", "glGetSamplerParameteriv", "glGetSamplerParameterfv", "glGetSamplerParameterIiv", "glGetSamplerParameterIuiv") || Checks.reportMissing("GL", "GL_ARB_sampler_objects");
        }
        return false;
    }

    private static boolean check_ARB_separate_shader_objects(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_separate_shader_objects")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{User32.WM_VSCROLLCLIPBOARD, User32.WM_SIZECLIPBOARD, User32.WM_ASKCBFORMATNAME, User32.WM_CHANGECBCHAIN, User32.WM_HSCROLLCLIPBOARD, User32.WM_QUERYNEWPALETTE, 784, User32.WM_PAINTCLIPBOARD, 785, 786, EXTDisconnect.ALC_CONNECTED, 788, 789, 790, User32.WM_PRINT, User32.WM_PRINTCLIENT, User32.WM_APPCOMMAND, User32.WM_THEMECHANGED, 795, 796, User32.WM_CLIPBOARDUPDATE, User32.WM_DWMCOMPOSITIONCHANGED, User32.WM_DWMNCRENDERINGCHANGED, User32.WM_DWMCOLORIZATIONCOLORCHANGED, User32.WM_DWMWINDOWMAXIMIZEDCHANGE, 802, User32.WM_DWMSENDICONICTHUMBNAIL, 804, 805, User32.WM_DWMSENDICONICLIVEPREVIEWBITMAP, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, User32.WM_GETTITLEBARINFOEX, 832, 833, 834, 835, 836, 837}, "glUseProgramStages", "glActiveShaderProgram", "glCreateShaderProgramv", "glBindProgramPipeline", "glDeleteProgramPipelines", "glGenProgramPipelines", "glIsProgramPipeline", "glProgramParameteri", "glGetProgramPipelineiv", "glProgramUniform1i", "glProgramUniform2i", "glProgramUniform3i", "glProgramUniform4i", "glProgramUniform1ui", "glProgramUniform2ui", "glProgramUniform3ui", "glProgramUniform4ui", "glProgramUniform1f", "glProgramUniform2f", "glProgramUniform3f", "glProgramUniform4f", "glProgramUniform1d", "glProgramUniform2d", "glProgramUniform3d", "glProgramUniform4d", "glProgramUniform1iv", "glProgramUniform2iv", "glProgramUniform3iv", "glProgramUniform4iv", "glProgramUniform1uiv", "glProgramUniform2uiv", "glProgramUniform3uiv", "glProgramUniform4uiv", "glProgramUniform1fv", "glProgramUniform2fv", "glProgramUniform3fv", "glProgramUniform4fv", "glProgramUniform1dv", "glProgramUniform2dv", "glProgramUniform3dv", "glProgramUniform4dv", "glProgramUniformMatrix2fv", "glProgramUniformMatrix3fv", "glProgramUniformMatrix4fv", "glProgramUniformMatrix2dv", "glProgramUniformMatrix3dv", "glProgramUniformMatrix4dv", "glProgramUniformMatrix2x3fv", "glProgramUniformMatrix3x2fv", "glProgramUniformMatrix2x4fv", "glProgramUniformMatrix4x2fv", "glProgramUniformMatrix3x4fv", "glProgramUniformMatrix4x3fv", "glProgramUniformMatrix2x3dv", "glProgramUniformMatrix3x2dv", "glProgramUniformMatrix2x4dv", "glProgramUniformMatrix4x2dv", "glProgramUniformMatrix3x4dv", "glProgramUniformMatrix4x3dv", "glValidateProgramPipeline", "glGetProgramPipelineInfoLog") || Checks.reportMissing("GL", "GL_ARB_separate_shader_objects");
        }
        return false;
    }

    private static boolean check_ARB_shader_atomic_counters(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_shader_atomic_counters")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{858}, "glGetActiveAtomicCounterBufferiv") || Checks.reportMissing("GL", "GL_ARB_shader_atomic_counters");
        }
        return false;
    }

    private static boolean check_ARB_shader_image_load_store(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_shader_image_load_store")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{867, 868}, "glBindImageTexture", "glMemoryBarrier") || Checks.reportMissing("GL", "GL_ARB_shader_image_load_store");
        }
        return false;
    }

    private static boolean check_ARB_shader_objects(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_shader_objects")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, Config.PLAYER_XP_MAX_PER_GAME, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354}, "glDeleteObjectARB", "glGetHandleARB", "glDetachObjectARB", "glCreateShaderObjectARB", "glShaderSourceARB", "glCompileShaderARB", "glCreateProgramObjectARB", "glAttachObjectARB", "glLinkProgramARB", "glUseProgramObjectARB", "glValidateProgramARB", "glUniform1fARB", "glUniform2fARB", "glUniform3fARB", "glUniform4fARB", "glUniform1iARB", "glUniform2iARB", "glUniform3iARB", "glUniform4iARB", "glUniform1fvARB", "glUniform2fvARB", "glUniform3fvARB", "glUniform4fvARB", "glUniform1ivARB", "glUniform2ivARB", "glUniform3ivARB", "glUniform4ivARB", "glUniformMatrix2fvARB", "glUniformMatrix3fvARB", "glUniformMatrix4fvARB", "glGetObjectParameterfvARB", "glGetObjectParameterivARB", "glGetInfoLogARB", "glGetAttachedObjectsARB", "glGetUniformLocationARB", "glGetActiveUniformARB", "glGetUniformfvARB", "glGetUniformivARB", "glGetShaderSourceARB") || Checks.reportMissing("GL", "GL_ARB_shader_objects");
        }
        return false;
    }

    private static boolean check_ARB_shader_storage_buffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_shader_storage_buffer_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{902}, "glShaderStorageBlockBinding") || Checks.reportMissing("GL", "GL_ARB_shader_storage_buffer_object");
        }
        return false;
    }

    private static boolean check_ARB_shader_subroutine(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_shader_subroutine")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{749, 750, 751, 752, 753, 754, 755, 756}, "glGetSubroutineUniformLocation", "glGetSubroutineIndex", "glGetActiveSubroutineUniformiv", "glGetActiveSubroutineUniformName", "glGetActiveSubroutineName", "glUniformSubroutinesuiv", "glGetUniformSubroutineuiv", "glGetProgramStageiv") || Checks.reportMissing("GL", "GL_ARB_shader_subroutine");
        }
        return false;
    }

    private static boolean check_ARB_shading_language_include(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_shading_language_include")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1355, 1356, 1357, 1358, 1359, 1360}, "glNamedStringARB", "glDeleteNamedStringARB", "glCompileShaderIncludeARB", "glIsNamedStringARB", "glGetNamedStringARB", "glGetNamedStringivARB") || Checks.reportMissing("GL", "GL_ARB_shading_language_include");
        }
        return false;
    }

    private static boolean check_ARB_sparse_buffer(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_sparse_buffer")) {
            return false;
        }
        set.contains("GL_EXT_direct_state_access");
        set.contains("GL_ARB_direct_state_access");
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1361}, "glBufferPageCommitmentARB") || Checks.reportMissing("GL", "GL_ARB_sparse_buffer");
    }

    private static boolean check_ARB_sparse_texture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_sparse_texture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1364, (set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE) + 1365}, "glTexPageCommitmentARB", "glTexturePageCommitmentEXT") || Checks.reportMissing("GL", "GL_ARB_sparse_texture");
        }
        return false;
    }

    private static boolean check_ARB_sync(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_sync")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{658, 659, 660, 661, 662, 663, 665}, "glFenceSync", "glIsSync", "glDeleteSync", "glClientWaitSync", "glWaitSync", "glGetInteger64v", "glGetSynciv") || Checks.reportMissing("GL", "GL_ARB_sync");
        }
        return false;
    }

    private static boolean check_ARB_tessellation_shader(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_tessellation_shader")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{757, 758}, "glPatchParameteri", "glPatchParameterfv") || Checks.reportMissing("GL", "GL_ARB_tessellation_shader");
        }
        return false;
    }

    private static boolean check_ARB_texture_barrier(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_texture_barrier")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1023}, "glTextureBarrier") || Checks.reportMissing("GL", "GL_ARB_texture_barrier");
        }
        return false;
    }

    private static boolean check_ARB_texture_buffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_texture_buffer_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1366}, "glTexBufferARB") || Checks.reportMissing("GL", "GL_ARB_texture_buffer_object");
        }
        return false;
    }

    private static boolean check_ARB_texture_buffer_range(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_texture_buffer_range")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{903, (set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE) + 1367}, "glTexBufferRange", "glTextureBufferRangeEXT") || Checks.reportMissing("GL", "GL_ARB_texture_buffer_range");
        }
        return false;
    }

    private static boolean check_ARB_texture_compression(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_texture_compression")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1368, 1369, 1370, 1371, 1372, 1373, 1374}, "glCompressedTexImage3DARB", "glCompressedTexImage2DARB", "glCompressedTexImage1DARB", "glCompressedTexSubImage3DARB", "glCompressedTexSubImage2DARB", "glCompressedTexSubImage1DARB", "glGetCompressedTexImageARB") || Checks.reportMissing("GL", "GL_ARB_texture_compression");
        }
        return false;
    }

    private static boolean check_ARB_texture_multisample(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_texture_multisample")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{653, 654, 655, User32.WM_IME_KEYDOWN}, "glTexImage2DMultisample", "glTexImage3DMultisample", "glGetMultisamplefv", "glSampleMaski") || Checks.reportMissing("GL", "GL_ARB_texture_multisample");
        }
        return false;
    }

    private static boolean check_ARB_texture_storage(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_texture_storage")) {
            return false;
        }
        int i = set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{859, 860, 861, i + 1375, i + 1376, i + 1377}, "glTexStorage1D", "glTexStorage2D", "glTexStorage3D", "glTextureStorage1DEXT", "glTextureStorage2DEXT", "glTextureStorage3DEXT") || Checks.reportMissing("GL", "GL_ARB_texture_storage");
    }

    private static boolean check_ARB_texture_storage_multisample(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_texture_storage_multisample")) {
            return false;
        }
        int i = set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{904, 905, i + 1378, i + 1379}, "glTexStorage2DMultisample", "glTexStorage3DMultisample", "glTextureStorage2DMultisampleEXT", "glTextureStorage3DMultisampleEXT") || Checks.reportMissing("GL", "GL_ARB_texture_storage_multisample");
    }

    private static boolean check_ARB_texture_view(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_texture_view")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{906}, "glTextureView") || Checks.reportMissing("GL", "GL_ARB_texture_view");
        }
        return false;
    }

    private static boolean check_ARB_timer_query(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_timer_query")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{682, 683, 684}, "glQueryCounter", "glGetQueryObjecti64v", "glGetQueryObjectui64v") || Checks.reportMissing("GL", "GL_ARB_timer_query");
        }
        return false;
    }

    private static boolean check_ARB_transform_feedback2(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_transform_feedback2")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{759, 760, 761, 762, 763, 764, 765}, "glBindTransformFeedback", "glDeleteTransformFeedbacks", "glGenTransformFeedbacks", "glIsTransformFeedback", "glPauseTransformFeedback", "glResumeTransformFeedback", "glDrawTransformFeedback") || Checks.reportMissing("GL", "GL_ARB_transform_feedback2");
        }
        return false;
    }

    private static boolean check_ARB_transform_feedback3(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_transform_feedback3")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{766, 767, 768, 769}, "glDrawTransformFeedbackStream", "glBeginQueryIndexed", "glEndQueryIndexed", "glGetQueryIndexediv") || Checks.reportMissing("GL", "GL_ARB_transform_feedback3");
        }
        return false;
    }

    private static boolean check_ARB_transform_feedback_instanced(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_transform_feedback_instanced")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{862, User32.WM_HANDHELDLAST}, "glDrawTransformFeedbackInstanced", "glDrawTransformFeedbackStreamInstanced") || Checks.reportMissing("GL", "GL_ARB_transform_feedback_instanced");
        }
        return false;
    }

    private static boolean check_ARB_transpose_matrix(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_transpose_matrix")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1380, 1381, 1382, 1383}, "glLoadTransposeMatrixfARB", "glLoadTransposeMatrixdARB", "glMultTransposeMatrixfARB", "glMultTransposeMatrixdARB") || Checks.reportMissing("GL", "GL_ARB_transpose_matrix");
        }
        return false;
    }

    private static boolean check_ARB_uniform_buffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_uniform_buffer_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{640, User32.WM_IME_SETCONTEXT, User32.WM_IME_NOTIFY, User32.WM_IME_CONTROL, User32.WM_IME_COMPOSITIONFULL, User32.WM_IME_SELECT, 625, 626, 621, User32.WM_IME_CHAR}, "glGetUniformIndices", "glGetActiveUniformsiv", "glGetActiveUniformName", "glGetUniformBlockIndex", "glGetActiveUniformBlockiv", "glGetActiveUniformBlockName", "glBindBufferRange", "glBindBufferBase", "glGetIntegeri_v", "glUniformBlockBinding") || Checks.reportMissing("GL", "GL_ARB_uniform_buffer_object");
        }
        return false;
    }

    private static boolean check_ARB_vertex_array_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_vertex_array_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{631, 632, 633, 634}, "glBindVertexArray", "glDeleteVertexArrays", "glGenVertexArrays", "glIsVertexArray") || Checks.reportMissing("GL", "GL_ARB_vertex_array_object");
        }
        return false;
    }

    private static boolean check_ARB_vertex_attrib_64bit(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_vertex_attrib_64bit")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{838, 839, 840, 841, 842, 843, 844, 845, 846, 847, (set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE) + 1384}, "glVertexAttribL1d", "glVertexAttribL2d", "glVertexAttribL3d", "glVertexAttribL4d", "glVertexAttribL1dv", "glVertexAttribL2dv", "glVertexAttribL3dv", "glVertexAttribL4dv", "glVertexAttribLPointer", "glGetVertexAttribLdv", "glVertexArrayVertexAttribLOffsetEXT") || Checks.reportMissing("GL", "GL_ARB_vertex_attrib_64bit");
        }
        return false;
    }

    private static boolean check_ARB_vertex_attrib_binding(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_ARB_vertex_attrib_binding")) {
            return false;
        }
        int i = set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{907, 908, 909, 910, User32.WM_PENWINLAST, 912, i + 1385, i + 1386, i + 1387, i + 1388, i + 1389, i + 1390}, "glBindVertexBuffer", "glVertexAttribFormat", "glVertexAttribIFormat", "glVertexAttribLFormat", "glVertexAttribBinding", "glVertexBindingDivisor", "glVertexArrayBindVertexBufferEXT", "glVertexArrayVertexAttribFormatEXT", "glVertexArrayVertexAttribIFormatEXT", "glVertexArrayVertexAttribLFormatEXT", "glVertexArrayVertexAttribBindingEXT", "glVertexArrayVertexBindingDivisorEXT") || Checks.reportMissing("GL", "GL_ARB_vertex_attrib_binding");
    }

    private static boolean check_ARB_vertex_blend(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_vertex_blend")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1391, 1392, 1393, 1394, 1395, 1396, 1397, 1398, 1399, 1400}, "glWeightfvARB", "glWeightbvARB", "glWeightubvARB", "glWeightsvARB", "glWeightusvARB", "glWeightivARB", "glWeightuivARB", "glWeightdvARB", "glWeightPointerARB", "glVertexBlendARB") || Checks.reportMissing("GL", "GL_ARB_vertex_blend");
        }
        return false;
    }

    private static boolean check_ARB_vertex_buffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_vertex_buffer_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{ObjCRuntime.OBJC_ASSOCIATION_RETAIN, 1402, ObjCRuntime.OBJC_ASSOCIATION_COPY, 1404, 1405, 1406, 1407, 1408, 1409, 1410, 1411}, "glBindBufferARB", "glDeleteBuffersARB", "glGenBuffersARB", "glIsBufferARB", "glBufferDataARB", "glBufferSubDataARB", "glGetBufferSubDataARB", "glMapBufferARB", "glUnmapBufferARB", "glGetBufferParameterivARB", "glGetBufferPointervARB") || Checks.reportMissing("GL", "GL_ARB_vertex_buffer_object");
        }
        return false;
    }

    private static boolean check_ARB_vertex_program(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_vertex_program")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1412, 1413, 1414, 1415, 1416, 1417, 1418, 1419, 1420, 1421, 1422, 1423, 1424, 1425, 1426, 1427, 1428, 1429, 1430, 1431, 1432, 1433, 1434, 1435, 1436, 1437, 1438, 1439, 1440, 1441, 1442, 1443, 1444, 1445, 1446, 1447, 1448, 1449, 1450, 1451, 1452, 1453, 1454, 1455, 1456, 1457, 1458, 1459, 1460, 1461, 1462, 1463, 1464, 1465, 1466, 1467, 1468, 1469, 1470, 1471, 1472, 1473}, "glVertexAttrib1sARB", "glVertexAttrib1fARB", "glVertexAttrib1dARB", "glVertexAttrib2sARB", "glVertexAttrib2fARB", "glVertexAttrib2dARB", "glVertexAttrib3sARB", "glVertexAttrib3fARB", "glVertexAttrib3dARB", "glVertexAttrib4sARB", "glVertexAttrib4fARB", "glVertexAttrib4dARB", "glVertexAttrib4NubARB", "glVertexAttrib1svARB", "glVertexAttrib1fvARB", "glVertexAttrib1dvARB", "glVertexAttrib2svARB", "glVertexAttrib2fvARB", "glVertexAttrib2dvARB", "glVertexAttrib3svARB", "glVertexAttrib3fvARB", "glVertexAttrib3dvARB", "glVertexAttrib4fvARB", "glVertexAttrib4bvARB", "glVertexAttrib4svARB", "glVertexAttrib4ivARB", "glVertexAttrib4ubvARB", "glVertexAttrib4usvARB", "glVertexAttrib4uivARB", "glVertexAttrib4dvARB", "glVertexAttrib4NbvARB", "glVertexAttrib4NsvARB", "glVertexAttrib4NivARB", "glVertexAttrib4NubvARB", "glVertexAttrib4NusvARB", "glVertexAttrib4NuivARB", "glVertexAttribPointerARB", "glEnableVertexAttribArrayARB", "glDisableVertexAttribArrayARB", "glProgramStringARB", "glBindProgramARB", "glDeleteProgramsARB", "glGenProgramsARB", "glProgramEnvParameter4dARB", "glProgramEnvParameter4dvARB", "glProgramEnvParameter4fARB", "glProgramEnvParameter4fvARB", "glProgramLocalParameter4dARB", "glProgramLocalParameter4dvARB", "glProgramLocalParameter4fARB", "glProgramLocalParameter4fvARB", "glGetProgramEnvParameterfvARB", "glGetProgramEnvParameterdvARB", "glGetProgramLocalParameterfvARB", "glGetProgramLocalParameterdvARB", "glGetProgramivARB", "glGetProgramStringARB", "glGetVertexAttribfvARB", "glGetVertexAttribdvARB", "glGetVertexAttribivARB", "glGetVertexAttribPointervARB", "glIsProgramARB") || Checks.reportMissing("GL", "GL_ARB_vertex_program");
        }
        return false;
    }

    private static boolean check_ARB_vertex_shader(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_vertex_shader")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1413, 1412, 1414, 1416, 1415, 1417, 1419, 1418, 1420, 1422, 1421, 1423, 1424, 1426, 1425, 1427, 1429, 1428, 1430, 1432, 1431, 1433, 1434, 1436, 1441, 1437, 1435, 1438, 1439, 1440, 1442, 1443, 1444, 1445, 1446, 1447, 1448, 1449, 1450, 1474, 1475, 1476, 1471, 1469, 1470, 1472}, "glVertexAttrib1fARB", "glVertexAttrib1sARB", "glVertexAttrib1dARB", "glVertexAttrib2fARB", "glVertexAttrib2sARB", "glVertexAttrib2dARB", "glVertexAttrib3fARB", "glVertexAttrib3sARB", "glVertexAttrib3dARB", "glVertexAttrib4fARB", "glVertexAttrib4sARB", "glVertexAttrib4dARB", "glVertexAttrib4NubARB", "glVertexAttrib1fvARB", "glVertexAttrib1svARB", "glVertexAttrib1dvARB", "glVertexAttrib2fvARB", "glVertexAttrib2svARB", "glVertexAttrib2dvARB", "glVertexAttrib3fvARB", "glVertexAttrib3svARB", "glVertexAttrib3dvARB", "glVertexAttrib4fvARB", "glVertexAttrib4svARB", "glVertexAttrib4dvARB", "glVertexAttrib4ivARB", "glVertexAttrib4bvARB", "glVertexAttrib4ubvARB", "glVertexAttrib4usvARB", "glVertexAttrib4uivARB", "glVertexAttrib4NbvARB", "glVertexAttrib4NsvARB", "glVertexAttrib4NivARB", "glVertexAttrib4NubvARB", "glVertexAttrib4NusvARB", "glVertexAttrib4NuivARB", "glVertexAttribPointerARB", "glEnableVertexAttribArrayARB", "glDisableVertexAttribArrayARB", "glBindAttribLocationARB", "glGetActiveAttribARB", "glGetAttribLocationARB", "glGetVertexAttribivARB", "glGetVertexAttribfvARB", "glGetVertexAttribdvARB", "glGetVertexAttribPointervARB") || Checks.reportMissing("GL", "GL_ARB_vertex_shader");
        }
        return false;
    }

    private static boolean check_ARB_vertex_type_2_10_10_10_rev(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set, boolean z) {
        if (set.contains("GL_ARB_vertex_type_2_10_10_10_rev")) {
            return ((z || Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{686, 687, 688, User32.WM_WTSSESSION_CHANGE, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, User32.WM_TABLET_FIRST, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715}, "glVertexP2ui", "glVertexP3ui", "glVertexP4ui", "glVertexP2uiv", "glVertexP3uiv", "glVertexP4uiv", "glTexCoordP1ui", "glTexCoordP2ui", "glTexCoordP3ui", "glTexCoordP4ui", "glTexCoordP1uiv", "glTexCoordP2uiv", "glTexCoordP3uiv", "glTexCoordP4uiv", "glMultiTexCoordP1ui", "glMultiTexCoordP2ui", "glMultiTexCoordP3ui", "glMultiTexCoordP4ui", "glMultiTexCoordP1uiv", "glMultiTexCoordP2uiv", "glMultiTexCoordP3uiv", "glMultiTexCoordP4uiv", "glNormalP3ui", "glNormalP3uiv", "glColorP3ui", "glColorP4ui", "glColorP3uiv", "glColorP4uiv", "glSecondaryColorP3ui", "glSecondaryColorP3uiv")) && Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{716, 717, 718, 719, 720, 721, 722, 723}, "glVertexAttribP1ui", "glVertexAttribP2ui", "glVertexAttribP3ui", "glVertexAttribP4ui", "glVertexAttribP1uiv", "glVertexAttribP2uiv", "glVertexAttribP3uiv", "glVertexAttribP4uiv")) || Checks.reportMissing("GL", "GL_ARB_vertex_type_2_10_10_10_rev");
        }
        return false;
    }

    private static boolean check_ARB_viewport_array(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_viewport_array")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{848, 849, 850, 851, 852, 853, 854, 855, User32.WM_HANDHELDFIRST, 857}, "glViewportArrayv", "glViewportIndexedf", "glViewportIndexedfv", "glScissorArrayv", "glScissorIndexed", "glScissorIndexedv", "glDepthRangeArrayv", "glDepthRangeIndexed", "glGetFloati_v", "glGetDoublei_v") || Checks.reportMissing("GL", "GL_ARB_viewport_array");
        }
        return false;
    }

    private static boolean check_ARB_window_pos(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_ARB_window_pos")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1477, 1478, 1479, 1480, 1481, 1482, 1483, 1484, 1485, 1486, 1487, 1488, 1489, 1490, 1491, 1492}, "glWindowPos2iARB", "glWindowPos2sARB", "glWindowPos2fARB", "glWindowPos2dARB", "glWindowPos2ivARB", "glWindowPos2svARB", "glWindowPos2fvARB", "glWindowPos2dvARB", "glWindowPos3iARB", "glWindowPos3sARB", "glWindowPos3fARB", "glWindowPos3dARB", "glWindowPos3ivARB", "glWindowPos3svARB", "glWindowPos3fvARB", "glWindowPos3dvARB") || Checks.reportMissing("GL", "GL_ARB_window_pos");
        }
        return false;
    }

    private static boolean check_EXT_bindable_uniform(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_bindable_uniform")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1493, 1494, 1495}, "glUniformBufferEXT", "glGetUniformBufferSizeEXT", "glGetUniformOffsetEXT") || Checks.reportMissing("GL", "GL_EXT_bindable_uniform");
        }
        return false;
    }

    private static boolean check_EXT_blend_color(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_blend_color")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1496}, "glBlendColorEXT") || Checks.reportMissing("GL", "GL_EXT_blend_color");
        }
        return false;
    }

    private static boolean check_EXT_blend_equation_separate(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_blend_equation_separate")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1497}, "glBlendEquationSeparateEXT") || Checks.reportMissing("GL", "GL_EXT_blend_equation_separate");
        }
        return false;
    }

    private static boolean check_EXT_blend_func_separate(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_blend_func_separate")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1498}, "glBlendFuncSeparateEXT") || Checks.reportMissing("GL", "GL_EXT_blend_func_separate");
        }
        return false;
    }

    private static boolean check_EXT_blend_minmax(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_blend_minmax")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1499}, "glBlendEquationEXT") || Checks.reportMissing("GL", "GL_EXT_blend_minmax");
        }
        return false;
    }

    private static boolean check_EXT_compiled_vertex_array(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_compiled_vertex_array")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1500, 1501}, "glLockArraysEXT", "glUnlockArraysEXT") || Checks.reportMissing("GL", "GL_EXT_compiled_vertex_array");
        }
        return false;
    }

    private static boolean check_EXT_debug_label(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_debug_label")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1502, 1503}, "glLabelObjectEXT", "glGetObjectLabelEXT") || Checks.reportMissing("GL", "GL_EXT_debug_label");
        }
        return false;
    }

    private static boolean check_EXT_debug_marker(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_debug_marker")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1504, 1505, 1506}, "glInsertEventMarkerEXT", "glPushGroupMarkerEXT", "glPopGroupMarkerEXT") || Checks.reportMissing("GL", "GL_EXT_debug_marker");
        }
        return false;
    }

    private static boolean check_EXT_depth_bounds_test(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_depth_bounds_test")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1507}, "glDepthBoundsEXT") || Checks.reportMissing("GL", "GL_EXT_depth_bounds_test");
        }
        return false;
    }

    private static boolean check_EXT_direct_state_access(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_EXT_direct_state_access")) {
            return false;
        }
        int i = set.contains("OpenGL12") ? 0 : Integer.MIN_VALUE;
        int i2 = set.contains("OpenGL13") ? 0 : Integer.MIN_VALUE;
        int i3 = set.contains("OpenGL30") ? 0 : Integer.MIN_VALUE;
        int i4 = set.contains("GL_ARB_vertex_program") ? 0 : Integer.MIN_VALUE;
        int i5 = set.contains("OpenGL15") ? 0 : Integer.MIN_VALUE;
        int i6 = set.contains("OpenGL20") ? 0 : Integer.MIN_VALUE;
        int i7 = set.contains("OpenGL21") ? 0 : Integer.MIN_VALUE;
        int i8 = set.contains("GL_EXT_texture_buffer_object") ? 0 : Integer.MIN_VALUE;
        int i9 = set.contains("GL_EXT_texture_integer") ? 0 : Integer.MIN_VALUE;
        int i10 = set.contains("GL_EXT_gpu_shader4") ? 0 : Integer.MIN_VALUE;
        int i11 = set.contains("GL_EXT_gpu_program_parameters") ? 0 : Integer.MIN_VALUE;
        int i12 = set.contains("GL_NV_gpu_program4") ? 0 : Integer.MIN_VALUE;
        int i13 = set.contains("GL_NV_framebuffer_multisample_coverage") ? 0 : Integer.MIN_VALUE;
        int i14 = (set.contains("GL_EXT_geometry_shader4") || set.contains("GL_NV_gpu_program4")) ? 0 : Integer.MIN_VALUE;
        int i15 = set.contains("GL_NV_explicit_multisample") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1508, 1509, 1510, 1511, 1512, 1513, 1514, 1515, 1516, 1517, 1518, 1519, 1520, 1521, 1522, 1523, 1524, 1525, 1526, 1527, 1528, 1529, 1530, 1531, 1532, 1533, 1534, 1535, 1536, GL11.GL_3D, GL11.GL_3D_COLOR, GL11.GL_3D_COLOR_TEXTURE, GL11.GL_4D_COLOR_TEXTURE, 1541, i + 1542, i + 1543, i + 1544, i2 + 1545, i2 + 1546, i2 + 1547, i2 + 1548, i2 + 1549, i2 + 1550, i2 + 1551, i2 + 1552, i2 + 1553, i2 + 1554, i2 + 1555, i2 + 1556, i2 + 1557, i2 + 1558, i2 + 1559, i2 + 1560, i2 + 1561, i2 + 1562, i2 + 1563, i2 + 1564, i2 + 1565, i2 + 1566, i2 + 1567, i2 + 1568, i2 + 1569, i2 + 1570, i2 + 1571, i2 + 1572, i2 + 1573, i2 + 1574, i2 + 1575, i2 + 1576, i2 + 1577, i2 + 1578, i2 + 1579, i2 + 1580, i2 + 1581, i2 + 1582, i2 + 1583, i2 + 1586, i2 + 1587, i2 + 1588, i2 + 1592, i2 + 1593, i2 + 1594, i2 + 1595, i2 + 1596, i4 + 1597, i4 + 1598, i4 + 1599, i4 + Config.DISPLAY_WIDTH, i4 + 1601, i4 + 1602, i4 + 1603, i4 + 1604, i4 + 1605, i2 + 1606, i2 + 1607, i2 + 1608, i2 + 1609, i2 + 1610, i2 + 1611, i2 + 1612, i2 + 1613, i2 + 1614, i2 + 1615, i2 + 1616, i2 + 1617, i2 + 1618, i2 + 1619, i2 + 1620, i2 + 1621, i2 + 1622, i2 + 1623, i5 + 1624, i5 + 1625, i5 + 1626, i5 + 1627, i5 + 1628, i5 + 1629, i6 + 1630, i6 + 1631, i6 + 1632, i6 + 1633, i6 + 1634, i6 + 1635, i6 + 1636, i6 + 1637, i6 + 1638, i6 + 1639, i6 + 1640, i6 + 1641, i6 + 1642, i6 + 1643, i6 + 1644, i6 + 1645, i6 + 1646, i6 + 1647, i6 + 1648, i7 + 1649, i7 + 1650, i7 + 1651, i7 + 1652, i7 + 1653, i7 + 1654, i8 + 1655, i8 + 1656, i9 + 1657, i9 + 1658, i9 + 1659, i9 + 1660, i9 + 1661, i9 + 1662, i9 + 1663, i9 + 1664, i10 + 1665, i10 + 1666, i10 + 1667, i10 + 1668, i10 + 1669, i10 + 1670, i10 + 1671, i10 + 1672, i11 + 1673, i12 + 1674, i12 + 1675, i12 + 1676, i12 + 1677, i12 + 1678, i12 + 1679, i12 + 1680, i12 + 1681, i3 + 1682, i3 + 1683, i3 + 1684, i13 + 1685, i3 + 1686, i3 + 1687, i3 + 1688, i3 + 1689, i3 + 1690, i3 + 1691, i3 + 1692, i3 + 1693, i3 + 1694, i3 + 1695, i3 + 1696, i3 + 1697, i3 + 1698, i14 + 1699, i14 + 1700, i14 + 1701, i15 + 1702, i15 + 1703, i3 + 1704, i3 + 1705, i3 + 1706, i3 + 1707, i3 + 1708, i3 + 1709, i3 + 1710, i3 + 1711, i3 + 1712, i3 + 1713, i3 + 1714, i3 + 1715, i3 + 1716, i3 + 1717, i3 + 1718, i3 + 1719, i3 + 1720, i3 + 1721, i3 + 1722, i3 + 1723, i3 + 1724}, "glClientAttribDefaultEXT", "glPushClientAttribDefaultEXT", "glMatrixLoadfEXT", "glMatrixLoaddEXT", "glMatrixMultfEXT", "glMatrixMultdEXT", "glMatrixLoadIdentityEXT", "glMatrixRotatefEXT", "glMatrixRotatedEXT", "glMatrixScalefEXT", "glMatrixScaledEXT", "glMatrixTranslatefEXT", "glMatrixTranslatedEXT", "glMatrixOrthoEXT", "glMatrixFrustumEXT", "glMatrixPushEXT", "glMatrixPopEXT", "glTextureParameteriEXT", "glTextureParameterivEXT", "glTextureParameterfEXT", "glTextureParameterfvEXT", "glTextureImage1DEXT", "glTextureImage2DEXT", "glTextureSubImage1DEXT", "glTextureSubImage2DEXT", "glCopyTextureImage1DEXT", "glCopyTextureImage2DEXT", "glCopyTextureSubImage1DEXT", "glCopyTextureSubImage2DEXT", "glGetTextureImageEXT", "glGetTextureParameterfvEXT", "glGetTextureParameterivEXT", "glGetTextureLevelParameterfvEXT", "glGetTextureLevelParameterivEXT", "glTextureImage3DEXT", "glTextureSubImage3DEXT", "glCopyTextureSubImage3DEXT", "glBindMultiTextureEXT", "glMultiTexCoordPointerEXT", "glMultiTexEnvfEXT", "glMultiTexEnvfvEXT", "glMultiTexEnviEXT", "glMultiTexEnvivEXT", "glMultiTexGendEXT", "glMultiTexGendvEXT", "glMultiTexGenfEXT", "glMultiTexGenfvEXT", "glMultiTexGeniEXT", "glMultiTexGenivEXT", "glGetMultiTexEnvfvEXT", "glGetMultiTexEnvivEXT", "glGetMultiTexGendvEXT", "glGetMultiTexGenfvEXT", "glGetMultiTexGenivEXT", "glMultiTexParameteriEXT", "glMultiTexParameterivEXT", "glMultiTexParameterfEXT", "glMultiTexParameterfvEXT", "glMultiTexImage1DEXT", "glMultiTexImage2DEXT", "glMultiTexSubImage1DEXT", "glMultiTexSubImage2DEXT", "glCopyMultiTexImage1DEXT", "glCopyMultiTexImage2DEXT", "glCopyMultiTexSubImage1DEXT", "glCopyMultiTexSubImage2DEXT", "glGetMultiTexImageEXT", "glGetMultiTexParameterfvEXT", "glGetMultiTexParameterivEXT", "glGetMultiTexLevelParameterfvEXT", "glGetMultiTexLevelParameterivEXT", "glMultiTexImage3DEXT", "glMultiTexSubImage3DEXT", "glCopyMultiTexSubImage3DEXT", "glEnableClientStateIndexedEXT", "glDisableClientStateIndexedEXT", "glGetFloatIndexedvEXT", "glGetDoubleIndexedvEXT", "glGetPointerIndexedvEXT", "glEnableIndexedEXT", "glDisableIndexedEXT", "glIsEnabledIndexedEXT", "glGetIntegerIndexedvEXT", "glGetBooleanIndexedvEXT", "glNamedProgramStringEXT", "glNamedProgramLocalParameter4dEXT", "glNamedProgramLocalParameter4dvEXT", "glNamedProgramLocalParameter4fEXT", "glNamedProgramLocalParameter4fvEXT", "glGetNamedProgramLocalParameterdvEXT", "glGetNamedProgramLocalParameterfvEXT", "glGetNamedProgramivEXT", "glGetNamedProgramStringEXT", "glCompressedTextureImage3DEXT", "glCompressedTextureImage2DEXT", "glCompressedTextureImage1DEXT", "glCompressedTextureSubImage3DEXT", "glCompressedTextureSubImage2DEXT", "glCompressedTextureSubImage1DEXT", "glGetCompressedTextureImageEXT", "glCompressedMultiTexImage3DEXT", "glCompressedMultiTexImage2DEXT", "glCompressedMultiTexImage1DEXT", "glCompressedMultiTexSubImage3DEXT", "glCompressedMultiTexSubImage2DEXT", "glCompressedMultiTexSubImage1DEXT", "glGetCompressedMultiTexImageEXT", "glMatrixLoadTransposefEXT", "glMatrixLoadTransposedEXT", "glMatrixMultTransposefEXT", "glMatrixMultTransposedEXT", "glNamedBufferDataEXT", "glNamedBufferSubDataEXT", "glMapNamedBufferEXT", "glUnmapNamedBufferEXT", "glGetNamedBufferParameterivEXT", "glGetNamedBufferSubDataEXT", "glProgramUniform1fEXT", "glProgramUniform2fEXT", "glProgramUniform3fEXT", "glProgramUniform4fEXT", "glProgramUniform1iEXT", "glProgramUniform2iEXT", "glProgramUniform3iEXT", "glProgramUniform4iEXT", "glProgramUniform1fvEXT", "glProgramUniform2fvEXT", "glProgramUniform3fvEXT", "glProgramUniform4fvEXT", "glProgramUniform1ivEXT", "glProgramUniform2ivEXT", "glProgramUniform3ivEXT", "glProgramUniform4ivEXT", "glProgramUniformMatrix2fvEXT", "glProgramUniformMatrix3fvEXT", "glProgramUniformMatrix4fvEXT", "glProgramUniformMatrix2x3fvEXT", "glProgramUniformMatrix3x2fvEXT", "glProgramUniformMatrix2x4fvEXT", "glProgramUniformMatrix4x2fvEXT", "glProgramUniformMatrix3x4fvEXT", "glProgramUniformMatrix4x3fvEXT", "glTextureBufferEXT", "glMultiTexBufferEXT", "glTextureParameterIivEXT", "glTextureParameterIuivEXT", "glGetTextureParameterIivEXT", "glGetTextureParameterIuivEXT", "glMultiTexParameterIivEXT", "glMultiTexParameterIuivEXT", "glGetMultiTexParameterIivEXT", "glGetMultiTexParameterIuivEXT", "glProgramUniform1uiEXT", "glProgramUniform2uiEXT", "glProgramUniform3uiEXT", "glProgramUniform4uiEXT", "glProgramUniform1uivEXT", "glProgramUniform2uivEXT", "glProgramUniform3uivEXT", "glProgramUniform4uivEXT", "glNamedProgramLocalParameters4fvEXT", "glNamedProgramLocalParameterI4iEXT", "glNamedProgramLocalParameterI4ivEXT", "glNamedProgramLocalParametersI4ivEXT", "glNamedProgramLocalParameterI4uiEXT", "glNamedProgramLocalParameterI4uivEXT", "glNamedProgramLocalParametersI4uivEXT", "glGetNamedProgramLocalParameterIivEXT", "glGetNamedProgramLocalParameterIuivEXT", "glNamedRenderbufferStorageEXT", "glGetNamedRenderbufferParameterivEXT", "glNamedRenderbufferStorageMultisampleEXT", "glNamedRenderbufferStorageMultisampleCoverageEXT", "glCheckNamedFramebufferStatusEXT", "glNamedFramebufferTexture1DEXT", "glNamedFramebufferTexture2DEXT", "glNamedFramebufferTexture3DEXT", "glNamedFramebufferRenderbufferEXT", "glGetNamedFramebufferAttachmentParameterivEXT", "glGenerateTextureMipmapEXT", "glGenerateMultiTexMipmapEXT", "glFramebufferDrawBufferEXT", "glFramebufferDrawBuffersEXT", "glFramebufferReadBufferEXT", "glGetFramebufferParameterivEXT", "glNamedCopyBufferSubDataEXT", "glNamedFramebufferTextureEXT", "glNamedFramebufferTextureLayerEXT", "glNamedFramebufferTextureFaceEXT", "glTextureRenderbufferEXT", "glMultiTexRenderbufferEXT", "glVertexArrayVertexOffsetEXT", "glVertexArrayColorOffsetEXT", "glVertexArrayEdgeFlagOffsetEXT", "glVertexArrayIndexOffsetEXT", "glVertexArrayNormalOffsetEXT", "glVertexArrayTexCoordOffsetEXT", "glVertexArrayMultiTexCoordOffsetEXT", "glVertexArrayFogCoordOffsetEXT", "glVertexArraySecondaryColorOffsetEXT", "glVertexArrayVertexAttribOffsetEXT", "glVertexArrayVertexAttribIOffsetEXT", "glEnableVertexArrayEXT", "glDisableVertexArrayEXT", "glEnableVertexArrayAttribEXT", "glDisableVertexArrayAttribEXT", "glGetVertexArrayIntegervEXT", "glGetVertexArrayPointervEXT", "glGetVertexArrayIntegeri_vEXT", "glGetVertexArrayPointeri_vEXT", "glMapNamedBufferRangeEXT", "glFlushMappedNamedBufferRangeEXT") || Checks.reportMissing("GL", "GL_EXT_direct_state_access");
    }

    private static boolean check_EXT_draw_buffers2(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_draw_buffers2")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1725, 1596, 1595, 1592, 1593, 1594}, "glColorMaskIndexedEXT", "glGetBooleanIndexedvEXT", "glGetIntegerIndexedvEXT", "glEnableIndexedEXT", "glDisableIndexedEXT", "glIsEnabledIndexedEXT") || Checks.reportMissing("GL", "GL_EXT_draw_buffers2");
        }
        return false;
    }

    private static boolean check_EXT_draw_instanced(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_draw_instanced")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1726, 1727}, "glDrawArraysInstancedEXT", "glDrawElementsInstancedEXT") || Checks.reportMissing("GL", "GL_EXT_draw_instanced");
        }
        return false;
    }

    private static boolean check_EXT_EGL_image_storage(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_EGL_image_storage")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1728, (hasDSA(set) ? 0 : Integer.MIN_VALUE) + 1729}, "glEGLImageTargetTexStorageEXT", "glEGLImageTargetTextureStorageEXT") || Checks.reportMissing("GL", "GL_EXT_EGL_image_storage");
        }
        return false;
    }

    private static boolean check_EXT_external_buffer(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_external_buffer")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1730, (hasDSA(set) ? 0 : Integer.MIN_VALUE) + 1731}, "glBufferStorageExternalEXT", "glNamedBufferStorageExternalEXT") || Checks.reportMissing("GL", "GL_EXT_external_buffer");
        }
        return false;
    }

    private static boolean check_EXT_framebuffer_blit(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_framebuffer_blit")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1732}, "glBlitFramebufferEXT") || Checks.reportMissing("GL", "GL_EXT_framebuffer_blit");
        }
        return false;
    }

    private static boolean check_EXT_framebuffer_blit_layers(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_framebuffer_blit_layers")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1733, 1734}, "glBlitFramebufferLayersEXT", "glBlitFramebufferLayerEXT") || Checks.reportMissing("GL", "GL_EXT_framebuffer_blit_layers");
        }
        return false;
    }

    private static boolean check_EXT_framebuffer_multisample(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_framebuffer_multisample")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1735}, "glRenderbufferStorageMultisampleEXT") || Checks.reportMissing("GL", "GL_EXT_framebuffer_multisample");
        }
        return false;
    }

    private static boolean check_EXT_framebuffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_framebuffer_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1736, 1737, 1738, 1739, 1740, 1741, 1742, 1743, 1744, 1745, 1746, 1747, 1748, 1749, 1750, 1751, 1752}, "glIsRenderbufferEXT", "glBindRenderbufferEXT", "glDeleteRenderbuffersEXT", "glGenRenderbuffersEXT", "glRenderbufferStorageEXT", "glGetRenderbufferParameterivEXT", "glIsFramebufferEXT", "glBindFramebufferEXT", "glDeleteFramebuffersEXT", "glGenFramebuffersEXT", "glCheckFramebufferStatusEXT", "glFramebufferTexture1DEXT", "glFramebufferTexture2DEXT", "glFramebufferTexture3DEXT", "glFramebufferRenderbufferEXT", "glGetFramebufferAttachmentParameterivEXT", "glGenerateMipmapEXT") || Checks.reportMissing("GL", "GL_EXT_framebuffer_object");
        }
        return false;
    }

    private static boolean check_EXT_geometry_shader4(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_geometry_shader4")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1753, 1754, 1755, 1756}, "glProgramParameteriEXT", "glFramebufferTextureEXT", "glFramebufferTextureLayerEXT", "glFramebufferTextureFaceEXT") || Checks.reportMissing("GL", "GL_EXT_geometry_shader4");
        }
        return false;
    }

    private static boolean check_EXT_gpu_program_parameters(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_gpu_program_parameters")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1757, 1758}, "glProgramEnvParameters4fvEXT", "glProgramLocalParameters4fvEXT") || Checks.reportMissing("GL", "GL_EXT_gpu_program_parameters");
        }
        return false;
    }

    private static boolean check_EXT_gpu_shader4(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_gpu_shader4")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1759, 1760, 1761, 1762, 1763, 1764, 1765, 1766, 1767, 1768, 1769, 1770, 1771, 1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 1786, 1787, 1788, 1789, 1790, 1791, GL11.GL_PASS_THROUGH_TOKEN}, "glVertexAttribI1iEXT", "glVertexAttribI2iEXT", "glVertexAttribI3iEXT", "glVertexAttribI4iEXT", "glVertexAttribI1uiEXT", "glVertexAttribI2uiEXT", "glVertexAttribI3uiEXT", "glVertexAttribI4uiEXT", "glVertexAttribI1ivEXT", "glVertexAttribI2ivEXT", "glVertexAttribI3ivEXT", "glVertexAttribI4ivEXT", "glVertexAttribI1uivEXT", "glVertexAttribI2uivEXT", "glVertexAttribI3uivEXT", "glVertexAttribI4uivEXT", "glVertexAttribI4bvEXT", "glVertexAttribI4svEXT", "glVertexAttribI4ubvEXT", "glVertexAttribI4usvEXT", "glVertexAttribIPointerEXT", "glGetVertexAttribIivEXT", "glGetVertexAttribIuivEXT", "glGetUniformuivEXT", "glBindFragDataLocationEXT", "glGetFragDataLocationEXT", "glUniform1uiEXT", "glUniform2uiEXT", "glUniform3uiEXT", "glUniform4uiEXT", "glUniform1uivEXT", "glUniform2uivEXT", "glUniform3uivEXT", "glUniform4uivEXT") || Checks.reportMissing("GL", "GL_EXT_gpu_shader4");
        }
        return false;
    }

    private static boolean check_EXT_memory_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_EXT_memory_object")) {
            return false;
        }
        int i = hasDSA(set) ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{GL11.GL_POINT_TOKEN, GL11.GL_LINE_TOKEN, GL11.GL_POLYGON_TOKEN, GL11.GL_BITMAP_TOKEN, GL11.GL_DRAW_PIXEL_TOKEN, GL11.GL_COPY_PIXEL_TOKEN, GL11.GL_LINE_RESET_TOKEN, HeadlessConfig.REPORT_INTERVAL, 1801, 1802, 1803, 1804, i + 1805, i + 1806, i + 1807, i + 1808, i + 1809, 1810, i + 1811}, "glGetUnsignedBytevEXT", "glGetUnsignedBytei_vEXT", "glDeleteMemoryObjectsEXT", "glIsMemoryObjectEXT", "glCreateMemoryObjectsEXT", "glMemoryObjectParameterivEXT", "glGetMemoryObjectParameterivEXT", "glTexStorageMem2DEXT", "glTexStorageMem2DMultisampleEXT", "glTexStorageMem3DEXT", "glTexStorageMem3DMultisampleEXT", "glBufferStorageMemEXT", "glTextureStorageMem2DEXT", "glTextureStorageMem2DMultisampleEXT", "glTextureStorageMem3DEXT", "glTextureStorageMem3DMultisampleEXT", "glNamedBufferStorageMemEXT", "glTexStorageMem1DEXT", "glTextureStorageMem1DEXT") || Checks.reportMissing("GL", "GL_EXT_memory_object");
    }

    private static boolean check_EXT_memory_object_fd(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_memory_object_fd")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1812}, "glImportMemoryFdEXT") || Checks.reportMissing("GL", "GL_EXT_memory_object_fd");
        }
        return false;
    }

    private static boolean check_EXT_memory_object_win32(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_memory_object_win32")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1813, 1814}, "glImportMemoryWin32HandleEXT", "glImportMemoryWin32NameEXT") || Checks.reportMissing("GL", "GL_EXT_memory_object_win32");
        }
        return false;
    }

    private static boolean check_EXT_point_parameters(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_point_parameters")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1815, 1816}, "glPointParameterfEXT", "glPointParameterfvEXT") || Checks.reportMissing("GL", "GL_EXT_point_parameters");
        }
        return false;
    }

    private static boolean check_EXT_polygon_offset_clamp(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_polygon_offset_clamp")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1817}, "glPolygonOffsetClampEXT") || Checks.reportMissing("GL", "GL_EXT_polygon_offset_clamp");
        }
        return false;
    }

    private static boolean check_EXT_provoking_vertex(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_provoking_vertex")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1818}, "glProvokingVertexEXT") || Checks.reportMissing("GL", "GL_EXT_provoking_vertex");
        }
        return false;
    }

    private static boolean check_EXT_raster_multisample(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_raster_multisample")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1819}, "glRasterSamplesEXT") || Checks.reportMissing("GL", "GL_EXT_raster_multisample");
        }
        return false;
    }

    private static boolean check_EXT_secondary_color(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_secondary_color")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1820, 1821, 1822, 1823, 1824, 1825, 1826, 1827, 1828, 1829, 1830, 1831, 1832, 1833, 1834, 1835, 1836}, "glSecondaryColor3bEXT", "glSecondaryColor3sEXT", "glSecondaryColor3iEXT", "glSecondaryColor3fEXT", "glSecondaryColor3dEXT", "glSecondaryColor3ubEXT", "glSecondaryColor3usEXT", "glSecondaryColor3uiEXT", "glSecondaryColor3bvEXT", "glSecondaryColor3svEXT", "glSecondaryColor3ivEXT", "glSecondaryColor3fvEXT", "glSecondaryColor3dvEXT", "glSecondaryColor3ubvEXT", "glSecondaryColor3usvEXT", "glSecondaryColor3uivEXT", "glSecondaryColorPointerEXT") || Checks.reportMissing("GL", "GL_EXT_secondary_color");
        }
        return false;
    }

    private static boolean check_EXT_semaphore(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_semaphore")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{GL11.GL_POINT_TOKEN, GL11.GL_LINE_TOKEN, 1837, 1838, 1839, 1840, 1841, 1842, 1843}, "glGetUnsignedBytevEXT", "glGetUnsignedBytei_vEXT", "glGenSemaphoresEXT", "glDeleteSemaphoresEXT", "glIsSemaphoreEXT", "glSemaphoreParameterui64vEXT", "glGetSemaphoreParameterui64vEXT", "glWaitSemaphoreEXT", "glSignalSemaphoreEXT") || Checks.reportMissing("GL", "GL_EXT_semaphore");
        }
        return false;
    }

    private static boolean check_EXT_semaphore_fd(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_semaphore_fd")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1844}, "glImportSemaphoreFdEXT") || Checks.reportMissing("GL", "GL_EXT_semaphore_fd");
        }
        return false;
    }

    private static boolean check_EXT_semaphore_win32(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_semaphore_win32")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1845, 1846}, "glImportSemaphoreWin32HandleEXT", "glImportSemaphoreWin32NameEXT") || Checks.reportMissing("GL", "GL_EXT_semaphore_win32");
        }
        return false;
    }

    private static boolean check_EXT_separate_shader_objects(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_separate_shader_objects")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1847, 1848, 1849}, "glUseShaderProgramEXT", "glActiveProgramEXT", "glCreateShaderProgramEXT") || Checks.reportMissing("GL", "GL_EXT_separate_shader_objects");
        }
        return false;
    }

    private static boolean check_EXT_shader_framebuffer_fetch_non_coherent(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_shader_framebuffer_fetch_non_coherent")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1850}, "glFramebufferFetchBarrierEXT") || Checks.reportMissing("GL", "GL_EXT_shader_framebuffer_fetch_non_coherent");
        }
        return false;
    }

    private static boolean check_EXT_shader_image_load_store(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_shader_image_load_store")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1851, 1852}, "glBindImageTextureEXT", "glMemoryBarrierEXT") || Checks.reportMissing("GL", "GL_EXT_shader_image_load_store");
        }
        return false;
    }

    private static boolean check_EXT_stencil_clear_tag(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_stencil_clear_tag")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1853}, "glStencilClearTagEXT") || Checks.reportMissing("GL", "GL_EXT_stencil_clear_tag");
        }
        return false;
    }

    private static boolean check_EXT_stencil_two_side(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_stencil_two_side")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1854}, "glActiveStencilFaceEXT") || Checks.reportMissing("GL", "GL_EXT_stencil_two_side");
        }
        return false;
    }

    private static boolean check_EXT_texture_array(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_texture_array")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1755}, "glFramebufferTextureLayerEXT") || Checks.reportMissing("GL", "GL_EXT_texture_array");
        }
        return false;
    }

    private static boolean check_EXT_texture_buffer_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_texture_buffer_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1855}, "glTexBufferEXT") || Checks.reportMissing("GL", "GL_EXT_texture_buffer_object");
        }
        return false;
    }

    private static boolean check_EXT_texture_integer(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_texture_integer")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1856, 1857, 1858, 1859, 1860, 1861}, "glClearColorIiEXT", "glClearColorIuiEXT", "glTexParameterIivEXT", "glTexParameterIuivEXT", "glGetTexParameterIivEXT", "glGetTexParameterIuivEXT") || Checks.reportMissing("GL", "GL_EXT_texture_integer");
        }
        return false;
    }

    private static boolean check_EXT_texture_storage(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_EXT_texture_storage")) {
            return false;
        }
        int i = hasDSA(set) ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1862, 1863, 1864, i + 1375, i + 1376, i + 1377}, "glTexStorage1DEXT", "glTexStorage2DEXT", "glTexStorage3DEXT", "glTextureStorage1DEXT", "glTextureStorage2DEXT", "glTextureStorage3DEXT") || Checks.reportMissing("GL", "GL_EXT_texture_storage");
    }

    private static boolean check_EXT_timer_query(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_timer_query")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1865, 1866}, "glGetQueryObjecti64vEXT", "glGetQueryObjectui64vEXT") || Checks.reportMissing("GL", "GL_EXT_timer_query");
        }
        return false;
    }

    private static boolean check_EXT_transform_feedback(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_transform_feedback")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1867, 1868, 1869, 1870, 1871, 1872, 1873, 1595, 1596}, "glBindBufferRangeEXT", "glBindBufferOffsetEXT", "glBindBufferBaseEXT", "glBeginTransformFeedbackEXT", "glEndTransformFeedbackEXT", "glTransformFeedbackVaryingsEXT", "glGetTransformFeedbackVaryingEXT", "glGetIntegerIndexedvEXT", "glGetBooleanIndexedvEXT") || Checks.reportMissing("GL", "GL_EXT_transform_feedback");
        }
        return false;
    }

    private static boolean check_EXT_vertex_attrib_64bit(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_vertex_attrib_64bit")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1874, 1875, 1876, 1877, 1878, 1879, 1880, 1881, 1882, 1883, (set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE) + 1384}, "glVertexAttribL1dEXT", "glVertexAttribL2dEXT", "glVertexAttribL3dEXT", "glVertexAttribL4dEXT", "glVertexAttribL1dvEXT", "glVertexAttribL2dvEXT", "glVertexAttribL3dvEXT", "glVertexAttribL4dvEXT", "glVertexAttribLPointerEXT", "glGetVertexAttribLdvEXT", "glVertexArrayVertexAttribLOffsetEXT") || Checks.reportMissing("GL", "GL_EXT_vertex_attrib_64bit");
        }
        return false;
    }

    private static boolean check_EXT_win32_keyed_mutex(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_win32_keyed_mutex")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1884, 1885}, "glAcquireKeyedMutexWin32EXT", "glReleaseKeyedMutexWin32EXT") || Checks.reportMissing("GL", "GL_EXT_win32_keyed_mutex");
        }
        return false;
    }

    private static boolean check_EXT_window_rectangles(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_window_rectangles")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1886}, "glWindowRectanglesEXT") || Checks.reportMissing("GL", "GL_EXT_window_rectangles");
        }
        return false;
    }

    private static boolean check_EXT_x11_sync_object(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_EXT_x11_sync_object")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1887}, "glImportSyncEXT") || Checks.reportMissing("GL", "GL_EXT_x11_sync_object");
        }
        return false;
    }

    private static boolean check_GREMEDY_frame_terminator(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_GREMEDY_frame_terminator")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1888}, "glFrameTerminatorGREMEDY") || Checks.reportMissing("GL", "GL_GREMEDY_frame_terminator");
        }
        return false;
    }

    private static boolean check_GREMEDY_string_marker(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_GREMEDY_string_marker")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1889}, "glStringMarkerGREMEDY") || Checks.reportMissing("GL", "GL_GREMEDY_string_marker");
        }
        return false;
    }

    private static boolean check_INTEL_framebuffer_CMAA(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_INTEL_framebuffer_CMAA")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1890}, "glApplyFramebufferAttachmentCMAAINTEL") || Checks.reportMissing("GL", "GL_INTEL_framebuffer_CMAA");
        }
        return false;
    }

    private static boolean check_INTEL_map_texture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_INTEL_map_texture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1891, 1892, 1893}, "glSyncTextureINTEL", "glUnmapTexture2DINTEL", "glMapTexture2DINTEL") || Checks.reportMissing("GL", "GL_INTEL_map_texture");
        }
        return false;
    }

    private static boolean check_INTEL_performance_query(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_INTEL_performance_query")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1894, 1895, 1896, 1897, 1898, 1899, 1900, 1901, 1902, 1903}, "glBeginPerfQueryINTEL", "glCreatePerfQueryINTEL", "glDeletePerfQueryINTEL", "glEndPerfQueryINTEL", "glGetFirstPerfQueryIdINTEL", "glGetNextPerfQueryIdINTEL", "glGetPerfCounterInfoINTEL", "glGetPerfQueryDataINTEL", "glGetPerfQueryIdByNameINTEL", "glGetPerfQueryInfoINTEL") || Checks.reportMissing("GL", "GL_INTEL_performance_query");
        }
        return false;
    }

    private static boolean check_KHR_blend_equation_advanced(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_KHR_blend_equation_advanced")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1904}, "glBlendBarrierKHR") || Checks.reportMissing("GL", "GL_KHR_blend_equation_advanced");
        }
        return false;
    }

    private static boolean check_KHR_debug(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_KHR_debug")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{875, 876, 877, 878, 879, 880, 881, 882, 883, 884}, "glDebugMessageControl", "glDebugMessageInsert", "glDebugMessageCallback", "glGetDebugMessageLog", "glPushDebugGroup", "glPopDebugGroup", "glObjectLabel", "glGetObjectLabel", "glObjectPtrLabel", "glGetObjectPtrLabel") || Checks.reportMissing("GL", "GL_KHR_debug");
        }
        return false;
    }

    private static boolean check_KHR_parallel_shader_compile(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_KHR_parallel_shader_compile")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1905}, "glMaxShaderCompilerThreadsKHR") || Checks.reportMissing("GL", "GL_KHR_parallel_shader_compile");
        }
        return false;
    }

    private static boolean check_KHR_robustness(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_KHR_robustness")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1024, 1033, STBTruetype.STBTT_MS_LANG_ITALIAN, STBTruetype.STBTT_MS_LANG_KOREAN, STBTruetype.STBTT_MS_LANG_DUTCH}, "glGetGraphicsResetStatus", "glReadnPixels", "glGetnUniformfv", "glGetnUniformiv", "glGetnUniformuiv") || Checks.reportMissing("GL", "GL_KHR_robustness");
        }
        return false;
    }

    private static boolean check_MESA_framebuffer_flip_y(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_MESA_framebuffer_flip_y")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1906, 1907}, "glFramebufferParameteriMESA", "glGetFramebufferParameterivMESA") || Checks.reportMissing("GL", "GL_MESA_framebuffer_flip_y");
        }
        return false;
    }

    private static boolean check_NV_alpha_to_coverage_dither_control(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_alpha_to_coverage_dither_control")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1908}, "glAlphaToCoverageDitherControlNV") || Checks.reportMissing("GL", "GL_NV_alpha_to_coverage_dither_control");
        }
        return false;
    }

    private static boolean check_NV_bindless_multi_draw_indirect(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_bindless_multi_draw_indirect")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1909, 1910}, "glMultiDrawArraysIndirectBindlessNV", "glMultiDrawElementsIndirectBindlessNV") || Checks.reportMissing("GL", "GL_NV_bindless_multi_draw_indirect");
        }
        return false;
    }

    private static boolean check_NV_bindless_multi_draw_indirect_count(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_bindless_multi_draw_indirect_count")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1911, 1912}, "glMultiDrawArraysIndirectBindlessCountNV", "glMultiDrawElementsIndirectBindlessCountNV") || Checks.reportMissing("GL", "GL_NV_bindless_multi_draw_indirect_count");
        }
        return false;
    }

    private static boolean check_NV_bindless_texture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_bindless_texture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1913, 1914, 1915, 1916, 1917, 1918, 1919, 1920, 1921, 1922, 1923, 1924, 1925}, "glGetTextureHandleNV", "glGetTextureSamplerHandleNV", "glMakeTextureHandleResidentNV", "glMakeTextureHandleNonResidentNV", "glGetImageHandleNV", "glMakeImageHandleResidentNV", "glMakeImageHandleNonResidentNV", "glUniformHandleui64NV", "glUniformHandleui64vNV", "glProgramUniformHandleui64NV", "glProgramUniformHandleui64vNV", "glIsTextureHandleResidentNV", "glIsImageHandleResidentNV") || Checks.reportMissing("GL", "GL_NV_bindless_texture");
        }
        return false;
    }

    private static boolean check_NV_blend_equation_advanced(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_blend_equation_advanced")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1926, 1927}, "glBlendParameteriNV", "glBlendBarrierNV") || Checks.reportMissing("GL", "GL_NV_blend_equation_advanced");
        }
        return false;
    }

    private static boolean check_NV_clip_space_w_scaling(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_clip_space_w_scaling")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1928}, "glViewportPositionWScaleNV") || Checks.reportMissing("GL", "GL_NV_clip_space_w_scaling");
        }
        return false;
    }

    private static boolean check_NV_command_list(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_command_list")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1929, 1930, 1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945}, "glCreateStatesNV", "glDeleteStatesNV", "glIsStateNV", "glStateCaptureNV", "glGetCommandHeaderNV", "glGetStageIndexNV", "glDrawCommandsNV", "glDrawCommandsAddressNV", "glDrawCommandsStatesNV", "glDrawCommandsStatesAddressNV", "glCreateCommandListsNV", "glDeleteCommandListsNV", "glIsCommandListNV", "glListDrawCommandsStatesClientNV", "glCommandListSegmentsNV", "glCompileCommandListNV", "glCallCommandListNV") || Checks.reportMissing("GL", "GL_NV_command_list");
        }
        return false;
    }

    private static boolean check_NV_conditional_render(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_conditional_render")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1946, 1947}, "glBeginConditionalRenderNV", "glEndConditionalRenderNV") || Checks.reportMissing("GL", "GL_NV_conditional_render");
        }
        return false;
    }

    private static boolean check_NV_conservative_raster(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_conservative_raster")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1948}, "glSubpixelPrecisionBiasNV") || Checks.reportMissing("GL", "GL_NV_conservative_raster");
        }
        return false;
    }

    private static boolean check_NV_conservative_raster_dilate(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_conservative_raster_dilate")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1949}, "glConservativeRasterParameterfNV") || Checks.reportMissing("GL", "GL_NV_conservative_raster_dilate");
        }
        return false;
    }

    private static boolean check_NV_conservative_raster_pre_snap_triangles(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_conservative_raster_pre_snap_triangles")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1950}, "glConservativeRasterParameteriNV") || Checks.reportMissing("GL", "GL_NV_conservative_raster_pre_snap_triangles");
        }
        return false;
    }

    private static boolean check_NV_copy_image(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_copy_image")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1951}, "glCopyImageSubDataNV") || Checks.reportMissing("GL", "GL_NV_copy_image");
        }
        return false;
    }

    private static boolean check_NV_depth_buffer_float(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_depth_buffer_float")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1952, 1953, 1954}, "glDepthRangedNV", "glClearDepthdNV", "glDepthBoundsdNV") || Checks.reportMissing("GL", "GL_NV_depth_buffer_float");
        }
        return false;
    }

    private static boolean check_NV_draw_texture(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_draw_texture")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1955}, "glDrawTextureNV") || Checks.reportMissing("GL", "GL_NV_draw_texture");
        }
        return false;
    }

    private static boolean check_NV_draw_vulkan_image(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_draw_vulkan_image")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1956, 1957, 1958, 1959, 1960}, "glDrawVkImageNV", "glGetVkProcAddrNV", "glWaitVkSemaphoreNV", "glSignalVkSemaphoreNV", "glSignalVkFenceNV") || Checks.reportMissing("GL", "GL_NV_draw_vulkan_image");
        }
        return false;
    }

    private static boolean check_NV_explicit_multisample(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_explicit_multisample")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1961, 1962, 1963}, "glGetMultisamplefvNV", "glSampleMaskIndexedNV", "glTexRenderbufferNV") || Checks.reportMissing("GL", "GL_NV_explicit_multisample");
        }
        return false;
    }

    private static boolean check_NV_fence(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_fence")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1964, 1965, 1966, 1967, 1968, 1969, 1970}, "glDeleteFencesNV", "glGenFencesNV", "glIsFenceNV", "glTestFenceNV", "glGetFenceivNV", "glFinishFenceNV", "glSetFenceNV") || Checks.reportMissing("GL", "GL_NV_fence");
        }
        return false;
    }

    private static boolean check_NV_fragment_coverage_to_color(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_fragment_coverage_to_color")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1971}, "glFragmentCoverageColorNV") || Checks.reportMissing("GL", "GL_NV_fragment_coverage_to_color");
        }
        return false;
    }

    private static boolean check_NV_framebuffer_mixed_samples(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_framebuffer_mixed_samples")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1819, 1972, 1973, 1974}, "glRasterSamplesEXT", "glCoverageModulationTableNV", "glGetCoverageModulationTableNV", "glCoverageModulationNV") || Checks.reportMissing("GL", "GL_NV_framebuffer_mixed_samples");
        }
        return false;
    }

    private static boolean check_NV_framebuffer_multisample_coverage(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_framebuffer_multisample_coverage")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1975}, "glRenderbufferStorageMultisampleCoverageNV") || Checks.reportMissing("GL", "GL_NV_framebuffer_multisample_coverage");
        }
        return false;
    }

    private static boolean check_NV_gpu_multicast(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_gpu_multicast")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987}, "glRenderGpuMaskNV", "glMulticastBufferSubDataNV", "glMulticastCopyBufferSubDataNV", "glMulticastCopyImageSubDataNV", "glMulticastBlitFramebufferNV", "glMulticastFramebufferSampleLocationsfvNV", "glMulticastBarrierNV", "glMulticastWaitSyncNV", "glMulticastGetQueryObjectivNV", "glMulticastGetQueryObjectuivNV", "glMulticastGetQueryObjecti64vNV", "glMulticastGetQueryObjectui64vNV") || Checks.reportMissing("GL", "GL_NV_gpu_multicast");
        }
        return false;
    }

    private static boolean check_NV_gpu_shader5(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_NV_gpu_shader5")) {
            return false;
        }
        int i = set.contains("GL_EXT_direct_state_access") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, i + 1076, i + 1077, i + 1078, i + 1079, i + Config.VIEWPORT_HEIGHT, i + 1081, i + 1082, i + 1083, i + 1084, i + 1085, i + 1086, i + 1087, i + 1088, i + 1089, i + 1090, i + 1091}, "glUniform1i64NV", "glUniform2i64NV", "glUniform3i64NV", "glUniform4i64NV", "glUniform1i64vNV", "glUniform2i64vNV", "glUniform3i64vNV", "glUniform4i64vNV", "glUniform1ui64NV", "glUniform2ui64NV", "glUniform3ui64NV", "glUniform4ui64NV", "glUniform1ui64vNV", "glUniform2ui64vNV", "glUniform3ui64vNV", "glUniform4ui64vNV", "glGetUniformi64vNV", "glGetUniformui64vNV", "glProgramUniform1i64NV", "glProgramUniform2i64NV", "glProgramUniform3i64NV", "glProgramUniform4i64NV", "glProgramUniform1i64vNV", "glProgramUniform2i64vNV", "glProgramUniform3i64vNV", "glProgramUniform4i64vNV", "glProgramUniform1ui64NV", "glProgramUniform2ui64NV", "glProgramUniform3ui64NV", "glProgramUniform4ui64NV", "glProgramUniform1ui64vNV", "glProgramUniform2ui64vNV", "glProgramUniform3ui64vNV", "glProgramUniform4ui64vNV") || Checks.reportMissing("GL", "GL_NV_gpu_shader5");
    }

    private static boolean check_NV_half_float(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_NV_half_float")) {
            return false;
        }
        int i = set.contains("GL_EXT_fog_coord") ? 0 : Integer.MIN_VALUE;
        int i2 = set.contains("GL_EXT_secondary_color") ? 0 : Integer.MIN_VALUE;
        int i3 = set.contains("GL_EXT_vertex_weighting") ? 0 : Integer.MIN_VALUE;
        int i4 = set.contains("GL_NV_vertex_program") ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, i + 2016, i + 2017, i2 + 2018, i2 + 2019, i3 + 2020, i3 + 2021, i4 + 2022, i4 + 2023, i4 + 2024, i4 + 2025, i4 + 2026, i4 + 2027, i4 + 2028, i4 + 2029, i4 + 2030, i4 + 2031, i4 + 2032, i4 + 2033}, "glVertex2hNV", "glVertex2hvNV", "glVertex3hNV", "glVertex3hvNV", "glVertex4hNV", "glVertex4hvNV", "glNormal3hNV", "glNormal3hvNV", "glColor3hNV", "glColor3hvNV", "glColor4hNV", "glColor4hvNV", "glTexCoord1hNV", "glTexCoord1hvNV", "glTexCoord2hNV", "glTexCoord2hvNV", "glTexCoord3hNV", "glTexCoord3hvNV", "glTexCoord4hNV", "glTexCoord4hvNV", "glMultiTexCoord1hNV", "glMultiTexCoord1hvNV", "glMultiTexCoord2hNV", "glMultiTexCoord2hvNV", "glMultiTexCoord3hNV", "glMultiTexCoord3hvNV", "glMultiTexCoord4hNV", "glMultiTexCoord4hvNV", "glFogCoordhNV", "glFogCoordhvNV", "glSecondaryColor3hNV", "glSecondaryColor3hvNV", "glVertexWeighthNV", "glVertexWeighthvNV", "glVertexAttrib1hNV", "glVertexAttrib1hvNV", "glVertexAttrib2hNV", "glVertexAttrib2hvNV", "glVertexAttrib3hNV", "glVertexAttrib3hvNV", "glVertexAttrib4hNV", "glVertexAttrib4hvNV", "glVertexAttribs1hvNV", "glVertexAttribs2hvNV", "glVertexAttribs3hvNV", "glVertexAttribs4hvNV") || Checks.reportMissing("GL", "GL_NV_half_float");
    }

    private static boolean check_NV_internalformat_sample_query(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_internalformat_sample_query")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2034}, "glGetInternalformatSampleivNV") || Checks.reportMissing("GL", "GL_NV_internalformat_sample_query");
        }
        return false;
    }

    private static boolean check_NV_memory_attachment(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (!set.contains("GL_NV_memory_attachment")) {
            return false;
        }
        int i = hasDSA(set) ? 0 : Integer.MIN_VALUE;
        return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2035, 2036, 2037, 2038, i + 2039, i + 2040}, "glGetMemoryObjectDetachedResourcesuivNV", "glResetMemoryObjectParameterNV", "glTexAttachMemoryNV", "glBufferAttachMemoryNV", "glTextureAttachMemoryNV", "glNamedBufferAttachMemoryNV") || Checks.reportMissing("GL", "GL_NV_memory_attachment");
    }

    private static boolean check_NV_memory_object_sparse(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_memory_object_sparse")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2041, 2042, 2043, 2044}, "glBufferPageCommitmentMemNV", "glNamedBufferPageCommitmentMemNV", "glTexPageCommitmentMemNV", "glTexturePageCommitmentMemNV") || Checks.reportMissing("GL", "GL_NV_memory_object_sparse");
        }
        return false;
    }

    private static boolean check_NV_mesh_shader(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_mesh_shader")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2045, 2046, 2047, 2048}, "glDrawMeshTasksNV", "glDrawMeshTasksIndirectNV", "glMultiDrawMeshTasksIndirectNV", "glMultiDrawMeshTasksIndirectCountNV") || Checks.reportMissing("GL", "GL_NV_mesh_shader");
        }
        return false;
    }

    private static boolean check_NV_path_rendering(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_path_rendering")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{GL11.GL_EXP2, 2050, 2051, STBTruetype.STBTT_MS_LANG_CHINESE, 2053, 2054, 2055, 2058, 2060, 2061, 2062, 2063, 2064, 2065, 2066, 2067, 2068, 2069, 2070, 2071, 2072, 2073, 2074, 2075, 2076, 2080, 2081, 2082, 2083, 2090, 2091, 2092, 2093, 2094, 2095, 2096, 2097, 2102, 2103, 2104, 2105}, "glPathCommandsNV", "glPathCoordsNV", "glPathSubCommandsNV", "glPathSubCoordsNV", "glPathStringNV", "glPathGlyphsNV", "glPathGlyphRangeNV", "glCopyPathNV", "glInterpolatePathsNV", "glTransformPathNV", "glPathParameterivNV", "glPathParameteriNV", "glPathParameterfvNV", "glPathParameterfNV", "glPathDashArrayNV", "glGenPathsNV", "glDeletePathsNV", "glIsPathNV", "glPathStencilFuncNV", "glPathStencilDepthOffsetNV", "glStencilFillPathNV", "glStencilStrokePathNV", "glStencilFillPathInstancedNV", "glStencilStrokePathInstancedNV", "glPathCoverDepthFuncNV", "glCoverFillPathNV", "glCoverStrokePathNV", "glCoverFillPathInstancedNV", "glCoverStrokePathInstancedNV", "glGetPathParameterivNV", "glGetPathParameterfvNV", "glGetPathCommandsNV", "glGetPathCoordsNV", "glGetPathDashArrayNV", "glGetPathMetricsNV", "glGetPathMetricRangeNV", "glGetPathSpacingNV", "glIsPointInFillPathNV", "glIsPointInStrokePathNV", "glGetPathLengthNV", "glPointAlongPathNV") || Checks.reportMissing("GL", "GL_NV_path_rendering");
        }
        return false;
    }

    private static boolean check_NV_pixel_data_range(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_pixel_data_range")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2113, 2114}, "glPixelDataRangeNV", "glFlushPixelDataRangeNV") || Checks.reportMissing("GL", "GL_NV_pixel_data_range");
        }
        return false;
    }

    private static boolean check_NV_point_sprite(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_point_sprite")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2115, 2116}, "glPointParameteriNV", "glPointParameterivNV") || Checks.reportMissing("GL", "GL_NV_point_sprite");
        }
        return false;
    }

    private static boolean check_NV_primitive_restart(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_primitive_restart")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2117, 2118}, "glPrimitiveRestartNV", "glPrimitiveRestartIndexNV") || Checks.reportMissing("GL", "GL_NV_primitive_restart");
        }
        return false;
    }

    private static boolean check_NV_query_resource(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_query_resource")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2119}, "glQueryResourceNV") || Checks.reportMissing("GL", "GL_NV_query_resource");
        }
        return false;
    }

    private static boolean check_NV_query_resource_tag(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_query_resource_tag")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2120, 2121, 2122}, "glGenQueryResourceTagNV", "glDeleteQueryResourceTagNV", "glQueryResourceTagNV") || Checks.reportMissing("GL", "GL_NV_query_resource_tag");
        }
        return false;
    }

    private static boolean check_NV_sample_locations(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_sample_locations")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2123, 2124, 2125}, "glFramebufferSampleLocationsfvNV", "glNamedFramebufferSampleLocationsfvNV", "glResolveDepthValuesNV") || Checks.reportMissing("GL", "GL_NV_sample_locations");
        }
        return false;
    }

    private static boolean check_NV_scissor_exclusive(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_scissor_exclusive")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2126, 2127}, "glScissorExclusiveArrayvNV", "glScissorExclusiveNV") || Checks.reportMissing("GL", "GL_NV_scissor_exclusive");
        }
        return false;
    }

    private static boolean check_NV_shader_buffer_load(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_shader_buffer_load")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2128, 2129, 2130, 2131, 2132, 2133, 2134, 2135, 2136, 2137, 2138, 1075, 2139, 2140}, "glMakeBufferResidentNV", "glMakeBufferNonResidentNV", "glIsBufferResidentNV", "glMakeNamedBufferResidentNV", "glMakeNamedBufferNonResidentNV", "glIsNamedBufferResidentNV", "glGetBufferParameterui64vNV", "glGetNamedBufferParameterui64vNV", "glGetIntegerui64vNV", "glUniformui64NV", "glUniformui64vNV", "glGetUniformui64vNV", "glProgramUniformui64NV", "glProgramUniformui64vNV") || Checks.reportMissing("GL", "GL_NV_shader_buffer_load");
        }
        return false;
    }

    private static boolean check_NV_shading_rate_image(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_shading_rate_image")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2141, 2142, 2143, 2144, 2145, 2146, 2147}, "glBindShadingRateImageNV", "glShadingRateImagePaletteNV", "glGetShadingRateImagePaletteNV", "glShadingRateImageBarrierNV", "glShadingRateSampleOrderNV", "glShadingRateSampleOrderCustomNV", "glGetShadingRateSampleLocationivNV") || Checks.reportMissing("GL", "GL_NV_shading_rate_image");
        }
        return false;
    }

    private static boolean check_NV_texture_barrier(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_texture_barrier")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2148}, "glTextureBarrierNV") || Checks.reportMissing("GL", "GL_NV_texture_barrier");
        }
        return false;
    }

    private static boolean check_NV_texture_multisample(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_texture_multisample")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2149, 2150, 2151, 2152, 2153, 2154}, "glTexImage2DMultisampleCoverageNV", "glTexImage3DMultisampleCoverageNV", "glTextureImage2DMultisampleNV", "glTextureImage3DMultisampleNV", "glTextureImage2DMultisampleCoverageNV", "glTextureImage3DMultisampleCoverageNV") || Checks.reportMissing("GL", "GL_NV_texture_multisample");
        }
        return false;
    }

    private static boolean check_NV_timeline_semaphore(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_timeline_semaphore")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2155, 2156, 2157}, "glCreateSemaphoresNV", "glSemaphoreParameterivNV", "glGetSemaphoreParameterivNV") || Checks.reportMissing("GL", "GL_NV_timeline_semaphore");
        }
        return false;
    }

    private static boolean check_NV_transform_feedback(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_transform_feedback")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2158, 2159, 2160, 2161, 2162, 2163, 2164, 2165, 2166, 2167, 2168, 2169}, "glBeginTransformFeedbackNV", "glEndTransformFeedbackNV", "glTransformFeedbackAttribsNV", "glBindBufferRangeNV", "glBindBufferOffsetNV", "glBindBufferBaseNV", "glTransformFeedbackVaryingsNV", "glActiveVaryingNV", "glGetVaryingLocationNV", "glGetActiveVaryingNV", "glGetTransformFeedbackVaryingNV", "glTransformFeedbackStreamAttribsNV") || Checks.reportMissing("GL", "GL_NV_transform_feedback");
        }
        return false;
    }

    private static boolean check_NV_transform_feedback2(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_transform_feedback2")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2170, 2171, 2172, 2173, 2174, 2175, 2176}, "glBindTransformFeedbackNV", "glDeleteTransformFeedbacksNV", "glGenTransformFeedbacksNV", "glIsTransformFeedbackNV", "glPauseTransformFeedbackNV", "glResumeTransformFeedbackNV", "glDrawTransformFeedbackNV") || Checks.reportMissing("GL", "GL_NV_transform_feedback2");
        }
        return false;
    }

    private static boolean check_NV_vertex_array_range(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_vertex_array_range")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2177, 2178}, "glVertexArrayRangeNV", "glFlushVertexArrayRangeNV") || Checks.reportMissing("GL", "GL_NV_vertex_array_range");
        }
        return false;
    }

    private static boolean check_NV_vertex_attrib_integer_64bit(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_vertex_attrib_integer_64bit")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2179, 2180, 2181, 2182, 2183, 2184, 2185, 2186, 2187, 2188, 2189, 2190, 2191, 2192, 2193, 2194, 2195, 2196, (set.contains("GL_NV_vertex_buffer_unified_memory") ? 0 : Integer.MIN_VALUE) + 2197}, "glVertexAttribL1i64NV", "glVertexAttribL2i64NV", "glVertexAttribL3i64NV", "glVertexAttribL4i64NV", "glVertexAttribL1i64vNV", "glVertexAttribL2i64vNV", "glVertexAttribL3i64vNV", "glVertexAttribL4i64vNV", "glVertexAttribL1ui64NV", "glVertexAttribL2ui64NV", "glVertexAttribL3ui64NV", "glVertexAttribL4ui64NV", "glVertexAttribL1ui64vNV", "glVertexAttribL2ui64vNV", "glVertexAttribL3ui64vNV", "glVertexAttribL4ui64vNV", "glGetVertexAttribLi64vNV", "glGetVertexAttribLui64vNV", "glVertexAttribLFormatNV") || Checks.reportMissing("GL", "GL_NV_vertex_attrib_integer_64bit");
        }
        return false;
    }

    private static boolean check_NV_vertex_buffer_unified_memory(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_vertex_buffer_unified_memory")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2198, 2199, 2200, 2201, 2202, 2203, 2204, 2205, 2206, 2207, 2208, 2209}, "glBufferAddressRangeNV", "glVertexFormatNV", "glNormalFormatNV", "glColorFormatNV", "glIndexFormatNV", "glTexCoordFormatNV", "glEdgeFlagFormatNV", "glSecondaryColorFormatNV", "glFogCoordFormatNV", "glVertexAttribFormatNV", "glVertexAttribIFormatNV", "glGetIntegerui64i_vNV") || Checks.reportMissing("GL", "GL_NV_vertex_buffer_unified_memory");
        }
        return false;
    }

    private static boolean check_NV_viewport_swizzle(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NV_viewport_swizzle")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2210}, "glViewportSwizzleNV") || Checks.reportMissing("GL", "GL_NV_viewport_swizzle");
        }
        return false;
    }

    private static boolean check_NVX_conditional_render(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NVX_conditional_render")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2211, 2212}, "glBeginConditionalRenderNVX", "glEndConditionalRenderNVX") || Checks.reportMissing("GL", "GL_NVX_conditional_render");
        }
        return false;
    }

    private static boolean check_NVX_gpu_multicast2(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NVX_gpu_multicast2")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2213, 2214, 2215, 2216, 2217, 2218}, "glAsyncCopyImageSubDataNVX", "glAsyncCopyBufferSubDataNVX", "glUploadGpuMaskNVX", "glMulticastViewportArrayvNVX", "glMulticastScissorArrayvNVX", "glMulticastViewportPositionWScaleNVX") || Checks.reportMissing("GL", "GL_NVX_gpu_multicast2");
        }
        return false;
    }

    private static boolean check_NVX_progress_fence(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_NVX_progress_fence")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2219, 2220, 2221, 2222}, "glCreateProgressFenceNVX", "glSignalSemaphoreui64NVX", "glWaitSemaphoreui64NVX", "glClientWaitSemaphoreui64NVX") || Checks.reportMissing("GL", "GL_NVX_progress_fence");
        }
        return false;
    }

    private static boolean check_OVR_multiview(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("GL_OVR_multiview")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{2223, (hasDSA(set) ? 0 : Integer.MIN_VALUE) + 2224}, "glFramebufferTextureMultiviewOVR", "glNamedFramebufferTextureMultiviewOVR") || Checks.reportMissing("GL", "GL_OVR_multiview");
        }
        return false;
    }

    private static boolean hasDSA(Set<String> set) {
        return set.contains("GL45") || set.contains("GL_ARB_direct_state_access") || set.contains("GL_EXT_direct_state_access");
    }

    private static boolean ARB_framebuffer_object(Set<String> set) {
        return set.contains("OpenGL30") || set.contains("GL_ARB_framebuffer_object");
    }

    private static boolean ARB_map_buffer_range(Set<String> set) {
        return set.contains("OpenGL30") || set.contains("GL_ARB_map_buffer_range");
    }

    private static boolean ARB_vertex_array_object(Set<String> set) {
        return set.contains("OpenGL30") || set.contains("GL_ARB_vertex_array_object");
    }

    private static boolean ARB_copy_buffer(Set<String> set) {
        return set.contains("OpenGL31") || set.contains("GL_ARB_copy_buffer");
    }

    private static boolean ARB_texture_buffer_object(Set<String> set) {
        return set.contains("OpenGL31") || set.contains("GL_ARB_texture_buffer_object");
    }

    private static boolean ARB_uniform_buffer_object(Set<String> set) {
        return set.contains("OpenGL31") || set.contains("GL_ARB_uniform_buffer_object");
    }

    private static boolean ARB_instanced_arrays(Set<String> set) {
        return set.contains("OpenGL33") || set.contains("GL_ARB_instanced_arrays");
    }

    private static boolean ARB_sampler_objects(Set<String> set) {
        return set.contains("OpenGL33") || set.contains("GL_ARB_sampler_objects");
    }

    private static boolean ARB_transform_feedback2(Set<String> set) {
        return set.contains("OpenGL40") || set.contains("GL_ARB_transform_feedback2");
    }

    private static boolean ARB_vertex_attrib_64bit(Set<String> set) {
        return set.contains("OpenGL41") || set.contains("GL_ARB_vertex_attrib_64bit");
    }

    private static boolean ARB_separate_shader_objects(Set<String> set) {
        return set.contains("OpenGL41") || set.contains("GL_ARB_separate_shader_objects");
    }

    private static boolean ARB_texture_storage(Set<String> set) {
        return set.contains("OpenGL42") || set.contains("GL_ARB_texture_storage");
    }

    private static boolean ARB_texture_storage_multisample(Set<String> set) {
        return set.contains("OpenGL43") || set.contains("GL_ARB_texture_storage_multisample");
    }

    private static boolean ARB_vertex_attrib_binding(Set<String> set) {
        return set.contains("OpenGL43") || set.contains("GL_ARB_vertex_attrib_binding");
    }

    private static boolean ARB_invalidate_subdata(Set<String> set) {
        return set.contains("OpenGL43") || set.contains("GL_ARB_invalidate_subdata");
    }

    private static boolean ARB_texture_buffer_range(Set<String> set) {
        return set.contains("OpenGL43") || set.contains("GL_ARB_texture_buffer_range");
    }

    private static boolean ARB_clear_buffer_object(Set<String> set) {
        return set.contains("OpenGL43") || set.contains("GL_ARB_clear_buffer_object");
    }

    private static boolean ARB_framebuffer_no_attachments(Set<String> set) {
        return set.contains("OpenGL43") || set.contains("GL_ARB_framebuffer_no_attachments");
    }

    private static boolean ARB_buffer_storage(Set<String> set) {
        return set.contains("OpenGL44") || set.contains("GL_ARB_buffer_storage");
    }

    private static boolean ARB_clear_texture(Set<String> set) {
        return set.contains("OpenGL44") || set.contains("GL_ARB_clear_texture");
    }

    private static boolean ARB_multi_bind(Set<String> set) {
        return set.contains("OpenGL44") || set.contains("GL_ARB_multi_bind");
    }

    private static boolean ARB_query_buffer_object(Set<String> set) {
        return set.contains("OpenGL44") || set.contains("GL_ARB_query_buffer_object");
    }
}
