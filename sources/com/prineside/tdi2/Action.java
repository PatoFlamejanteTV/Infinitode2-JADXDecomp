package com.prineside.tdi2;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.KryoSerializable;
import com.prineside.tdi2.actions.BuildMinerAction;
import com.prineside.tdi2.actions.BuildModifierAction;
import com.prineside.tdi2.actions.BuildTowerAction;
import com.prineside.tdi2.actions.CallWaveAction;
import com.prineside.tdi2.actions.ChangeTowerAimStrategyAction;
import com.prineside.tdi2.actions.CoreUpgradeAction;
import com.prineside.tdi2.actions.CustomAction;
import com.prineside.tdi2.actions.CustomModifierButtonAction;
import com.prineside.tdi2.actions.CustomTowerButtonAction;
import com.prineside.tdi2.actions.EncounterBirdClickAction;
import com.prineside.tdi2.actions.EncounterBirdDeclineAction;
import com.prineside.tdi2.actions.GlobalUpgradeMinerAction;
import com.prineside.tdi2.actions.GlobalUpgradeTowerAction;
import com.prineside.tdi2.actions.ReRollBonusesAction;
import com.prineside.tdi2.actions.RewardingAdAction;
import com.prineside.tdi2.actions.ScriptAction;
import com.prineside.tdi2.actions.SelectGameplayBonusAction;
import com.prineside.tdi2.actions.SelectGlobalTowerAbilityAction;
import com.prineside.tdi2.actions.SelectTowerAbilityAction;
import com.prineside.tdi2.actions.SellMinerAction;
import com.prineside.tdi2.actions.SellModifierAction;
import com.prineside.tdi2.actions.SellTowerAction;
import com.prineside.tdi2.actions.ToggleTowerEnabledAction;
import com.prineside.tdi2.actions.UpgradeMinerAction;
import com.prineside.tdi2.actions.UpgradeTowerAction;
import com.prineside.tdi2.actions.UseAbilityAction;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Action.class */
public abstract class Action implements KryoSerializable {
    public abstract ActionType getType();

    public boolean affectsPlayerXp() {
        return false;
    }

    public void toJson(Json json) {
    }

    public static Action fromJson(JsonValue jsonValue) {
        ActionType valueOf = ActionType.valueOf(jsonValue.getString("t"));
        switch (valueOf) {
            case S:
                return new ScriptAction(jsonValue);
            case BT:
                return new BuildTowerAction(jsonValue);
            case CW:
                return new CallWaveAction(jsonValue);
            case RA:
                return new RewardingAdAction(jsonValue);
            case UT:
                return new UpgradeTowerAction(jsonValue);
            case GUT:
                return new GlobalUpgradeTowerAction(jsonValue);
            case ST:
                return new SellTowerAction(jsonValue);
            case BM:
                return new BuildMinerAction(jsonValue);
            case UM:
                return new UpgradeMinerAction(jsonValue);
            case GUM:
                return new GlobalUpgradeMinerAction(jsonValue);
            case SM:
                return new SellMinerAction(jsonValue);
            case BMO:
                return new BuildModifierAction(jsonValue);
            case STA:
                return new SelectTowerAbilityAction(jsonValue);
            case SGTA:
                return new SelectGlobalTowerAbilityAction(jsonValue);
            case CTAS:
                return new ChangeTowerAimStrategyAction(jsonValue);
            case UA:
                return new UseAbilityAction(jsonValue);
            case CTB:
                return new CustomTowerButtonAction(jsonValue);
            case SMO:
                return new SellModifierAction(jsonValue);
            case CU:
                return new CoreUpgradeAction(jsonValue);
            case CMB:
                return new CustomModifierButtonAction(jsonValue);
            case SGB:
                return new SelectGameplayBonusAction(jsonValue);
            case RRB:
                return new ReRollBonusesAction(jsonValue);
            case TTE:
                return new ToggleTowerEnabledAction(jsonValue);
            case EBC:
                return new EncounterBirdClickAction(jsonValue);
            case EBD:
                return new EncounterBirdDeclineAction(jsonValue);
            case C:
                return new CustomAction(jsonValue);
            default:
                throw new RuntimeException("Not implemented: " + valueOf.name());
        }
    }
}
