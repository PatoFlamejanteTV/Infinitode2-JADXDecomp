package com.prineside.tdi2.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResourceManager.class */
public class ResourceManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final Color[] f2444a;

    /* renamed from: b, reason: collision with root package name */
    private static final Color[] f2445b;
    private final StatisticsType[] c = new StatisticsType[ResourceType.values.length];
    private final String[] d = new String[ResourceType.values.length];
    public final ObjectMap<ResourceType, String> SHORT_RESOURCE_ALIASES = new ObjectMap<>();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResourceManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ResourceManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ResourceManager read() {
            return Game.i.resourceManager;
        }
    }

    static {
        Color[] colorArr = new Color[ResourceType.values.length];
        f2444a = colorArr;
        colorArr[ResourceType.SCALAR.ordinal()] = MaterialColor.GREEN.P500;
        f2444a[ResourceType.VECTOR.ordinal()] = MaterialColor.INDIGO.P400;
        f2444a[ResourceType.MATRIX.ordinal()] = MaterialColor.PURPLE.P400;
        f2444a[ResourceType.TENSOR.ordinal()] = MaterialColor.ORANGE.P500;
        f2444a[ResourceType.INFIAR.ordinal()] = MaterialColor.CYAN.P500;
        Color[] colorArr2 = new Color[ResourceType.values.length];
        f2445b = colorArr2;
        colorArr2[ResourceType.SCALAR.ordinal()] = MaterialColor.GREEN.P500;
        f2445b[ResourceType.VECTOR.ordinal()] = MaterialColor.INDIGO.P400;
        f2445b[ResourceType.MATRIX.ordinal()] = MaterialColor.PINK.P400.cpy().lerp(0.2f, 0.3f, 0.3f, 1.0f, 0.25f);
        f2445b[ResourceType.TENSOR.ordinal()] = MaterialColor.ORANGE.P500;
        f2445b[ResourceType.INFIAR.ordinal()] = MaterialColor.CYAN.P500;
    }

    public ResourceManager() {
        this.SHORT_RESOURCE_ALIASES.put(ResourceType.SCALAR, "S");
        this.SHORT_RESOURCE_ALIASES.put(ResourceType.VECTOR, "V");
        this.SHORT_RESOURCE_ALIASES.put(ResourceType.MATRIX, "M");
        this.SHORT_RESOURCE_ALIASES.put(ResourceType.TENSOR, "T");
        this.SHORT_RESOURCE_ALIASES.put(ResourceType.INFIAR, "I");
        for (ResourceType resourceType : ResourceType.values) {
            this.c[resourceType.ordinal()] = StatisticsType.valueOf("RG_" + this.SHORT_RESOURCE_ALIASES.get(resourceType));
            this.d[resourceType.ordinal()] = "resource_name_" + resourceType.name();
        }
    }

    public Color getColor(ResourceType resourceType) {
        if (Game.i.settingsManager.cvdFriendlyColors()) {
            return f2445b[resourceType.ordinal()];
        }
        return f2444a[resourceType.ordinal()];
    }

    public String getName(ResourceType resourceType) {
        return Game.i.localeManager.i18n.get(this.d[resourceType.ordinal()]);
    }

    public StatisticsType getGainedCountStatistic(ResourceType resourceType) {
        return this.c[resourceType.ordinal()];
    }
}
