package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/BasicLevelStage.class */
public class BasicLevelStage {
    public String name;
    public Color color;
    public String titleAlias;
    public boolean regular = true;
    public final Array<Requirement> showRequirements = new Array<>();
    public final Array<BasicLevel> levels = new Array<>(true, 1, BasicLevel.class);

    public BasicLevelStage(String str, Color color, String str2) {
        this.name = str;
        this.color = color;
        this.titleAlias = str2;
    }

    public void toJson(Json json) {
        json.writeValue(Attribute.NAME_ATTR, this.name);
        json.writeValue(Attribute.TITLE_ATTR, this.titleAlias);
        json.writeValue("regular", Boolean.valueOf(this.regular));
        json.writeValue("color", "#" + this.color.toString().substring(0, 6));
        json.writeArrayStart("showRequirements");
        for (int i = 0; i < this.showRequirements.size; i++) {
            Requirement requirement = this.showRequirements.get(i);
            json.writeObjectStart();
            requirement.toJson(json);
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
    }

    public void sortLevels() {
        for (int i = 0; i < this.levels.size; i++) {
            for (int i2 = i + 1; i2 < this.levels.size; i2++) {
                if (this.levels.items[i].stagePosition > this.levels.items[i2].stagePosition) {
                    this.levels.swap(i, i2);
                }
            }
        }
    }

    public String getTitle() {
        if (Game.i.localeManager.i18n.has(this.titleAlias)) {
            return Game.i.localeManager.i18n.get(this.titleAlias);
        }
        return this.titleAlias;
    }

    public static BasicLevelStage fromJson(JsonValue jsonValue) {
        String string = jsonValue.getString(Attribute.NAME_ATTR);
        String string2 = jsonValue.getString(Attribute.TITLE_ATTR);
        boolean z = jsonValue.getBoolean("regular", false);
        Color color = new Color();
        Color.rgb888ToColor(color, Integer.parseInt(jsonValue.getString("color").substring(1), 16));
        color.f889a = 1.0f;
        BasicLevelStage basicLevelStage = new BasicLevelStage(string, color, string2);
        basicLevelStage.regular = z;
        Iterator<JsonValue> iterator2 = jsonValue.get("showRequirements").iterator2();
        while (iterator2.hasNext()) {
            basicLevelStage.showRequirements.add(Requirement.fromJson(iterator2.next()));
        }
        return basicLevelStage;
    }
}
