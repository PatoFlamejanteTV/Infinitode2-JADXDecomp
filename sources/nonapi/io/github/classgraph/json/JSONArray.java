package nonapi.io.github.classgraph.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONArray.class */
public class JSONArray {
    List<Object> items;

    public JSONArray() {
        this.items = new ArrayList();
    }

    public JSONArray(List<Object> list) {
        this.items = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void toJSONString(Map<ReferenceEqualityKey<JSONReference>, CharSequence> map, boolean z, int i, int i2, StringBuilder sb) {
        boolean z2 = i2 > 0;
        int size = this.items.size();
        if (size == 0) {
            sb.append("[]");
            return;
        }
        sb.append('[');
        if (z2) {
            sb.append('\n');
        }
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = this.items.get(i3);
            if (z2) {
                JSONUtils.indent(i + 1, i2, sb);
            }
            JSONSerializer.jsonValToJSONString(obj, map, z, i + 1, i2, sb);
            if (i3 < size - 1) {
                sb.append(',');
            }
            if (z2) {
                sb.append('\n');
            }
        }
        if (z2) {
            JSONUtils.indent(i, i2, sb);
        }
        sb.append(']');
    }
}
