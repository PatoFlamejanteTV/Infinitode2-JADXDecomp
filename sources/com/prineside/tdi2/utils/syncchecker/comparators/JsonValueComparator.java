package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/JsonValueComparator.class */
public final class JsonValueComparator extends DeepClassComparator<JsonValue> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<JsonValue> forClass() {
        return JsonValue.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(JsonValue jsonValue, JsonValue jsonValue2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (jsonValue == null && jsonValue2 != null) {
            deepClassComparisonConfig.appendPrefix().append(": first object is null\n");
            return;
        }
        if (jsonValue != null && jsonValue2 == null) {
            deepClassComparisonConfig.appendPrefix().append(": second object is null\n");
        } else if (jsonValue != null && !jsonValue.toJson(JsonWriter.OutputType.minimal).equals(jsonValue2.toJson(JsonWriter.OutputType.minimal))) {
            StringBuilder append = deepClassComparisonConfig.appendPrefix().append(": json contents do not match\n");
            append.append(jsonValue.toJson(JsonWriter.OutputType.minimal)).append(SequenceUtils.EOL);
            append.append(jsonValue2.toJson(JsonWriter.OutputType.minimal)).append(SequenceUtils.EOL);
        }
    }
}
