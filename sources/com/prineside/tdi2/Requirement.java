package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.enums.RequirementType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Requirement.class */
public class Requirement {
    public RequirementType type;
    public ResearchType researchType;
    public int researchLevel;
    public String levelName;
    public int levelStars;
    public String stageName;
    public int stageStars;
    public StatisticsType statisticsType;
    public double statisticsValue;
    public String openedLevelName;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1747a = TLog.forClass(Requirement.class);

    /* renamed from: b, reason: collision with root package name */
    private static final String f1748b = StringFormatter.toRGB(MaterialColor.ORANGE.P500).toString();
    private static final String c = StringFormatter.toRGB(Color.WHITE).toString();
    private static final StringBuilder d = new StringBuilder();

    public Requirement() {
        setType(RequirementType.ALL_TIME_STATISTIC);
    }

    public void setType(RequirementType requirementType) {
        if (this.type == requirementType) {
            return;
        }
        this.type = requirementType;
        switch (requirementType) {
            case ALL_TIME_STATISTIC:
                if (this.statisticsType == null) {
                    this.statisticsType = StatisticsType.WIP;
                    return;
                }
                return;
            case OPENED_LEVEL:
                if (this.openedLevelName == null) {
                    this.openedLevelName = "0.1";
                    return;
                }
                return;
            case STAGE_STARS:
                if (this.stageName == null) {
                    this.stageName = "1";
                    return;
                }
                return;
            case STARS:
                if (this.levelName == null) {
                    this.levelName = "0.1";
                    return;
                }
                return;
            case RESEARCH:
                if (this.researchType == null) {
                    this.researchType = ResearchType.ROOT;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void toJson(Json json) {
        json.writeValue("type", this.type.name());
        switch (this.type) {
            case ALL_TIME_STATISTIC:
                json.writeValue(Attribute.NAME_ATTR, this.statisticsType.name());
                json.writeValue("value", Double.valueOf(this.statisticsValue));
                return;
            case OPENED_LEVEL:
                json.writeValue("level", this.openedLevelName);
                return;
            case STAGE_STARS:
                json.writeValue("stage", this.stageName);
                json.writeValue("amount", Integer.valueOf(this.stageStars));
                return;
            case STARS:
                json.writeValue("level", this.levelName);
                json.writeValue("amount", Integer.valueOf(this.levelStars));
                return;
            case RESEARCH:
                json.writeValue(Attribute.NAME_ATTR, this.researchType.name());
                json.writeValue("level", Integer.valueOf(this.researchLevel));
                return;
            default:
                return;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " (" + ((Object) getTitle(false)) + ": " + ((Object) getFormattedValue()) + ")";
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x00bf. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0321 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00e4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0254 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0250 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02cf A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.prineside.tdi2.Requirement fromJson(com.a.a.b.l r6) {
        /*
            Method dump skipped, instructions count: 847
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.Requirement.fromJson(com.a.a.b.l):com.prineside.tdi2.Requirement");
    }

    public static Requirement fromJson(JsonValue jsonValue) {
        Requirement requirement = new Requirement();
        requirement.type = RequirementType.valueOf(jsonValue.getString("type"));
        switch (requirement.type) {
            case ALL_TIME_STATISTIC:
                requirement.statisticsType = StatisticsType.valueOf(jsonValue.getString(Attribute.NAME_ATTR));
                requirement.statisticsValue = jsonValue.getDouble("value");
                break;
            case OPENED_LEVEL:
                requirement.openedLevelName = jsonValue.getString("level");
                break;
            case STAGE_STARS:
                requirement.stageName = jsonValue.getString("stage");
                requirement.stageStars = jsonValue.getInt("amount");
                break;
            case STARS:
                requirement.levelName = jsonValue.getString("level");
                requirement.levelStars = jsonValue.getInt("amount");
                break;
            case RESEARCH:
                requirement.researchType = ResearchType.valueOf(jsonValue.getString(Attribute.NAME_ATTR));
                requirement.researchLevel = jsonValue.getInt("level");
                break;
        }
        return requirement;
    }

    public boolean isSatisfied() {
        switch (this.type) {
            case ALL_TIME_STATISTIC:
                return Game.i.statisticsManager.getAllTime(this.statisticsType) >= this.statisticsValue;
            case OPENED_LEVEL:
                BasicLevel level = Game.i.basicLevelManager.getLevel(this.openedLevelName);
                if (level == null) {
                    return false;
                }
                return Game.i.basicLevelManager.isOpened(level);
            case STAGE_STARS:
                return Game.i.basicLevelManager.getGainedStarsOnStage(Game.i.basicLevelManager.getStage(this.stageName)) >= this.stageStars;
            case STARS:
                BasicLevel level2 = Game.i.basicLevelManager.getLevel(this.levelName);
                return level2 != null && Game.i.basicLevelManager.getGainedStarsOnLevel(level2) >= this.levelStars;
            case RESEARCH:
                return Game.i.researchManager.getResearchInstance(this.researchType).getInstalledLevel() >= this.researchLevel;
            default:
                return false;
        }
    }

    public String getIconTextureName() {
        switch (this.type) {
            case ALL_TIME_STATISTIC:
                return "icon-statistics";
            case OPENED_LEVEL:
                return "icon-joystick";
            case STAGE_STARS:
                return "icon-star-stack";
            case STARS:
                return "icon-star";
            case RESEARCH:
                return "icon-research";
            default:
                return AssetManager.BLANK_REGION_NAME;
        }
    }

    public StringBuilder getTitle(boolean z) {
        d.setLength(0);
        switch (this.type) {
            case ALL_TIME_STATISTIC:
                d.append(Game.i.statisticsManager.getStatisticsTitle(this.statisticsType));
                break;
            case OPENED_LEVEL:
                BasicLevel level = Game.i.basicLevelManager.getLevel(this.openedLevelName);
                if (level != null) {
                    if (z) {
                        d.append(Game.i.localeManager.i18n.format("requirement_title_OPENED_LEVEL_colored", StringFormatter.toRGB(Game.i.basicLevelManager.getStage(level.stageName).color), level.name));
                        break;
                    } else {
                        d.append(Game.i.localeManager.i18n.format("requirement_title_OPENED_LEVEL", level.name));
                        break;
                    }
                }
                break;
            case STAGE_STARS:
                BasicLevelStage stage = Game.i.basicLevelManager.getStage(this.stageName);
                if (z) {
                    d.append(Game.i.localeManager.i18n.format("requirement_title_STAGE_STARS_colored", StringFormatter.toRGB(stage.color), stage.name));
                    break;
                } else {
                    d.append(Game.i.localeManager.i18n.format("requirement_title_STAGE_STARS", stage.name));
                    break;
                }
            case STARS:
                BasicLevel level2 = Game.i.basicLevelManager.getLevel(this.levelName);
                if (level2 != null) {
                    if (z) {
                        d.append(Game.i.localeManager.i18n.format("requirement_title_STARS_colored", StringFormatter.toRGB(Game.i.basicLevelManager.getStage(level2.stageName).color), level2.name));
                        break;
                    } else {
                        d.append(Game.i.localeManager.i18n.format("requirement_title_STARS", level2.name));
                        break;
                    }
                }
                break;
            case RESEARCH:
                Research researchInstance = Game.i.researchManager.getResearchInstance(this.researchType);
                TowerType relatedToTowerType = researchInstance.isUnlocksTower() ? researchInstance.getRelatedToTowerType() : null;
                d.append(researchInstance.getTitle());
                if (relatedToTowerType != null) {
                    d.append(" (").append(Game.i.towerManager.getFactory(relatedToTowerType).getTitle()).append(')');
                    break;
                }
                break;
        }
        return d;
    }

    public StringBuilder getFormattedValue() {
        d.setLength(0);
        switch (this.type) {
            case ALL_TIME_STATISTIC:
                double allTime = Game.i.statisticsManager.getAllTime(this.statisticsType);
                double d2 = allTime;
                if (allTime >= this.statisticsValue) {
                    d2 = this.statisticsValue;
                    d.append("[#").append(c).append("]");
                } else {
                    d.append("[#").append(f1748b).append("]");
                }
                if (this.statisticsType == StatisticsType.PT || this.statisticsType == StatisticsType.PRT) {
                    d.append(StringFormatter.digestTime((int) d2)).append("[] / ").append(StringFormatter.digestTime((int) this.statisticsValue));
                    break;
                } else {
                    d.append(StringFormatter.compactNumber(d2, false)).append("[] / ").append(StringFormatter.compactNumber(this.statisticsValue, false));
                    break;
                }
            case STAGE_STARS:
                int gainedStarsOnStage = Game.i.basicLevelManager.getGainedStarsOnStage(Game.i.basicLevelManager.getStage(this.stageName));
                int i = gainedStarsOnStage;
                if (gainedStarsOnStage >= this.stageStars) {
                    i = this.stageStars;
                    d.append("[#").append(c).append("]");
                } else {
                    d.append("[#").append(f1748b).append("]");
                }
                d.append(i).append("[] / ").append(this.stageStars);
                break;
            case STARS:
                BasicLevel level = Game.i.basicLevelManager.getLevel(this.levelName);
                if (level != null) {
                    int gainedStarsOnLevel = Game.i.basicLevelManager.getGainedStarsOnLevel(level);
                    int i2 = gainedStarsOnLevel;
                    if (gainedStarsOnLevel >= this.levelStars) {
                        i2 = this.levelStars;
                        d.append("[#").append(c).append("]");
                    } else {
                        d.append("[#").append(f1748b).append("]");
                    }
                    d.append(i2).append("[] / ").append(this.levelStars);
                    break;
                }
                break;
        }
        return d;
    }
}
