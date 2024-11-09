package com.prineside.tdi2.events;

import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/EventListenersDeepClassComparator.class */
public final class EventListenersDeepClassComparator extends DeepClassComparator<EventListeners> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<EventListeners> forClass() {
        return EventListeners.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(EventListeners eventListeners, EventListeners eventListeners2, DeepClassComparisonConfig deepClassComparisonConfig) {
        deepClassComparisonConfig.addPrefix(".entries");
        int i = 0;
        for (int i2 = 0; i2 < eventListeners.size(); i2++) {
            EventListeners.Entry entry = eventListeners.getEntriesBackingArray()[i2];
            if (entry != null && entry.isStateAffecting()) {
                i++;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < eventListeners2.size(); i4++) {
            EventListeners.Entry entry2 = eventListeners2.getEntriesBackingArray()[i4];
            if (entry2 != null && entry2.isStateAffecting()) {
                i3++;
            }
        }
        if (i != i3) {
            deepClassComparisonConfig.appendPrefix().append(": entry count differ (").append(i).append(", ").append(i3).append(")\no1: ").append(eventListeners.describe()).append("\no2: ").append(eventListeners2.describe());
        } else {
            int i5 = 0;
            int i6 = 0;
            EventListeners.Entry[] entriesBackingArray = eventListeners.getEntriesBackingArray();
            EventListeners.Entry[] entriesBackingArray2 = eventListeners2.getEntriesBackingArray();
            for (int i7 = 0; i7 < eventListeners.size(); i7++) {
                EventListeners.Entry entry3 = entriesBackingArray[i7];
                if (entry3 != null && entry3.isStateAffecting()) {
                    EventListeners.Entry entry4 = null;
                    while (true) {
                        if (i5 >= eventListeners2.size()) {
                            break;
                        }
                        EventListeners.Entry entry5 = entriesBackingArray2[i5];
                        i5++;
                        if (entry5 != null && entry5.isStateAffecting()) {
                            entry4 = entry5;
                            break;
                        }
                    }
                    deepClassComparisonConfig.addPrefix("[", SyncChecker.toString(i6), "]");
                    if (entry4 == null) {
                        deepClassComparisonConfig.appendPrefix().append(": state affecting listener not exists in the second group (").append(entry3).append(")\no1: ").append(eventListeners.describe()).append("\no2: ").append(eventListeners2.describe());
                    } else {
                        SyncChecker.compareObjects(entry3, entry4, deepClassComparisonConfig);
                    }
                    deepClassComparisonConfig.popPrefix(3);
                    i6++;
                }
            }
        }
        deepClassComparisonConfig.popPrefix(1);
    }
}
