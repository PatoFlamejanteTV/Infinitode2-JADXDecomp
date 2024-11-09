package nonapi.io.github.classgraph.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONObject.class */
public class JSONObject {
    List<Map.Entry<String, Object>> items;
    CharSequence objectId;

    public JSONObject(int i) {
        this.items = new ArrayList(i);
    }

    public JSONObject(List<Map.Entry<String, Object>> list) {
        this.items = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void toJSONString(Map<ReferenceEqualityKey<JSONReference>, CharSequence> map, boolean z, int i, int i2, StringBuilder sb) {
        int i3;
        boolean z2 = i2 > 0;
        int size = this.items.size();
        if (z) {
            i3 = size;
        } else {
            i3 = 0;
            Iterator<Map.Entry<String, Object>> it = this.items.iterator();
            while (it.hasNext()) {
                if (it.next().getValue() != null) {
                    i3++;
                }
            }
        }
        if (this.objectId == null && i3 == 0) {
            sb.append("{}");
            return;
        }
        sb.append(z2 ? "{\n" : "{");
        if (this.objectId != null) {
            if (z2) {
                JSONUtils.indent(i + 1, i2, sb);
            }
            sb.append('\"');
            sb.append("__ID");
            sb.append(z2 ? "\": " : "\":");
            JSONSerializer.jsonValToJSONString(this.objectId, map, z, i + 1, i2, sb);
            if (i3 > 0) {
                sb.append(',');
            }
            if (z2) {
                sb.append('\n');
            }
        }
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            Map.Entry<String, Object> entry = this.items.get(i5);
            Object value = entry.getValue();
            if (value != null || z) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Cannot serialize JSON object with null key");
                }
                if (z2) {
                    JSONUtils.indent(i + 1, i2, sb);
                }
                sb.append('\"');
                JSONUtils.escapeJSONString(key, sb);
                sb.append(z2 ? "\": " : "\":");
                JSONSerializer.jsonValToJSONString(value, map, z, i + 1, i2, sb);
                i4++;
                if (i4 < i3) {
                    sb.append(',');
                }
                if (z2) {
                    sb.append('\n');
                }
            }
        }
        if (z2) {
            JSONUtils.indent(i, i2, sb);
        }
        sb.append('}');
    }
}
