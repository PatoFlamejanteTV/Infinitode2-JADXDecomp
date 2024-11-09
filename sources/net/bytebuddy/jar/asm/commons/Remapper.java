package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.ConstantDynamic;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.signature.SignatureReader;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.jar.asm.signature.SignatureWriter;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/commons/Remapper.class */
public abstract class Remapper {
    public String mapDesc(String str) {
        return mapType(Type.getType(str)).getDescriptor();
    }

    private Type mapType(Type type) {
        switch (type.getSort()) {
            case 9:
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < type.getDimensions(); i++) {
                    sb.append('[');
                }
                sb.append(mapType(type.getElementType()).getDescriptor());
                return Type.getType(sb.toString());
            case 10:
                String map = map(type.getInternalName());
                return map != null ? Type.getObjectType(map) : type;
            case 11:
                return Type.getMethodType(mapMethodDesc(type.getDescriptor()));
            default:
                return type;
        }
    }

    public String mapType(String str) {
        if (str == null) {
            return null;
        }
        return mapType(Type.getObjectType(str)).getInternalName();
    }

    public String[] mapTypes(String[] strArr) {
        String[] strArr2 = null;
        for (int i = 0; i < strArr.length; i++) {
            String mapType = mapType(strArr[i]);
            if (mapType != null) {
                if (strArr2 == null) {
                    strArr2 = (String[]) strArr.clone();
                }
                strArr2[i] = mapType;
            }
        }
        return strArr2 != null ? strArr2 : strArr;
    }

    public String mapMethodDesc(String str) {
        if ("()V".equals(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder("(");
        for (Type type : Type.getArgumentTypes(str)) {
            sb.append(mapType(type).getDescriptor());
        }
        Type returnType = Type.getReturnType(str);
        if (returnType != Type.VOID_TYPE) {
            sb.append(')').append(mapType(returnType).getDescriptor());
        } else {
            sb.append(")V");
        }
        return sb.toString();
    }

    public Object mapValue(Object obj) {
        String mapMethodName;
        if (obj instanceof Type) {
            return mapType((Type) obj);
        }
        if (obj instanceof Handle) {
            Handle handle = (Handle) obj;
            boolean z = handle.getTag() <= 4;
            int tag = handle.getTag();
            String mapType = mapType(handle.getOwner());
            if (z) {
                mapMethodName = mapFieldName(handle.getOwner(), handle.getName(), handle.getDesc());
            } else {
                mapMethodName = mapMethodName(handle.getOwner(), handle.getName(), handle.getDesc());
            }
            return new Handle(tag, mapType, mapMethodName, z ? mapDesc(handle.getDesc()) : mapMethodDesc(handle.getDesc()), handle.isInterface());
        }
        if (obj instanceof ConstantDynamic) {
            ConstantDynamic constantDynamic = (ConstantDynamic) obj;
            int bootstrapMethodArgumentCount = constantDynamic.getBootstrapMethodArgumentCount();
            Object[] objArr = new Object[bootstrapMethodArgumentCount];
            for (int i = 0; i < bootstrapMethodArgumentCount; i++) {
                objArr[i] = mapValue(constantDynamic.getBootstrapMethodArgument(i));
            }
            String descriptor = constantDynamic.getDescriptor();
            return new ConstantDynamic(mapInvokeDynamicMethodName(constantDynamic.getName(), descriptor), mapDesc(descriptor), (Handle) mapValue(constantDynamic.getBootstrapMethod()), objArr);
        }
        return obj;
    }

    public String mapSignature(String str, boolean z) {
        if (str == null) {
            return null;
        }
        SignatureReader signatureReader = new SignatureReader(str);
        SignatureWriter signatureWriter = new SignatureWriter();
        SignatureVisitor createSignatureRemapper = createSignatureRemapper(signatureWriter);
        if (z) {
            signatureReader.acceptType(createSignatureRemapper);
        } else {
            signatureReader.accept(createSignatureRemapper);
        }
        return signatureWriter.toString();
    }

    @Deprecated
    protected SignatureVisitor createRemappingSignatureAdapter(SignatureVisitor signatureVisitor) {
        return createSignatureRemapper(signatureVisitor);
    }

    protected SignatureVisitor createSignatureRemapper(SignatureVisitor signatureVisitor) {
        return new SignatureRemapper(signatureVisitor, this);
    }

    public String mapAnnotationAttributeName(String str, String str2) {
        return str2;
    }

    public String mapInnerClassName(String str, String str2, String str3) {
        String mapType = mapType(str);
        if (mapType.equals(str)) {
            return str3;
        }
        int lastIndexOf = str.lastIndexOf(47);
        int lastIndexOf2 = mapType.lastIndexOf(47);
        if (lastIndexOf != -1 && lastIndexOf2 != -1 && str.substring(lastIndexOf).equals(mapType.substring(lastIndexOf2))) {
            return str3;
        }
        if (mapType.contains("$")) {
            int lastIndexOf3 = mapType.lastIndexOf(36) + 1;
            while (lastIndexOf3 < mapType.length() && Character.isDigit(mapType.charAt(lastIndexOf3))) {
                lastIndexOf3++;
            }
            return mapType.substring(lastIndexOf3);
        }
        return str3;
    }

    public String mapMethodName(String str, String str2, String str3) {
        return str2;
    }

    public String mapInvokeDynamicMethodName(String str, String str2) {
        return str;
    }

    public String mapRecordComponentName(String str, String str2, String str3) {
        return str2;
    }

    public String mapFieldName(String str, String str2, String str3) {
        return str2;
    }

    public String mapPackageName(String str) {
        return str;
    }

    public String mapModuleName(String str) {
        return str;
    }

    public String map(String str) {
        return str;
    }
}
