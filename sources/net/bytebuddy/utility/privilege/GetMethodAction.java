package net.bytebuddy.utility.privilege;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;
import java.util.Arrays;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/utility/privilege/GetMethodAction.class */
public class GetMethodAction implements PrivilegedAction<Method> {
    private final String type;
    private final String name;
    private final Class<?>[] parameter;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.type.equals(((GetMethodAction) obj).type) && this.name.equals(((GetMethodAction) obj).name) && Arrays.equals(this.parameter, ((GetMethodAction) obj).parameter);
    }

    public int hashCode() {
        return (((((getClass().hashCode() * 31) + this.type.hashCode()) * 31) + this.name.hashCode()) * 31) + Arrays.hashCode(this.parameter);
    }

    public GetMethodAction(String str, String str2, Class<?>... clsArr) {
        this.type = str;
        this.name = str2;
        this.parameter = clsArr;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.security.PrivilegedAction
    @MaybeNull
    @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
    public Method run() {
        try {
            return Class.forName(this.type).getMethod(this.name, this.parameter);
        } catch (Exception unused) {
            return null;
        }
    }
}
