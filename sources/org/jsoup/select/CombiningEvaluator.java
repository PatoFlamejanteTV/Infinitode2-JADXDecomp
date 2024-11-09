package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:org/jsoup/select/CombiningEvaluator.class */
public abstract class CombiningEvaluator extends Evaluator {
    final ArrayList<Evaluator> evaluators;
    final ArrayList<Evaluator> sortedEvaluators;
    int num;
    int cost;
    private static final Comparator<Evaluator> costComparator = (evaluator, evaluator2) -> {
        return evaluator.cost() - evaluator2.cost();
    };

    CombiningEvaluator() {
        this.num = 0;
        this.cost = 0;
        this.evaluators = new ArrayList<>();
        this.sortedEvaluators = new ArrayList<>();
    }

    CombiningEvaluator(Collection<Evaluator> collection) {
        this();
        this.evaluators.addAll(collection);
        updateEvaluators();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.select.Evaluator
    public void reset() {
        Iterator<Evaluator> it = this.evaluators.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
        super.reset();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.select.Evaluator
    public int cost() {
        return this.cost;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Evaluator rightMostEvaluator() {
        if (this.num > 0) {
            return this.evaluators.get(this.num - 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceRightMostEvaluator(Evaluator evaluator) {
        this.evaluators.set(this.num - 1, evaluator);
        updateEvaluators();
    }

    void updateEvaluators() {
        this.num = this.evaluators.size();
        this.cost = 0;
        Iterator<Evaluator> it = this.evaluators.iterator();
        while (it.hasNext()) {
            this.cost += it.next().cost();
        }
        this.sortedEvaluators.clear();
        this.sortedEvaluators.addAll(this.evaluators);
        Collections.sort(this.sortedEvaluators, costComparator);
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/CombiningEvaluator$And.class */
    public static final class And extends CombiningEvaluator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public And(Collection<Evaluator> collection) {
            super(collection);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public And(Evaluator... evaluatorArr) {
            this(Arrays.asList(evaluatorArr));
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            for (int i = 0; i < this.num; i++) {
                if (!this.sortedEvaluators.get(i).matches(element, element2)) {
                    return false;
                }
            }
            return true;
        }

        public final String toString() {
            return StringUtil.join(this.evaluators, "");
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/CombiningEvaluator$Or.class */
    public static final class Or extends CombiningEvaluator {
        Or(Collection<Evaluator> collection) {
            if (this.num > 1) {
                this.evaluators.add(new And(collection));
            } else {
                this.evaluators.addAll(collection);
            }
            updateEvaluators();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Or(Evaluator... evaluatorArr) {
            this(Arrays.asList(evaluatorArr));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Or() {
        }

        public final void add(Evaluator evaluator) {
            this.evaluators.add(evaluator);
            updateEvaluators();
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            for (int i = 0; i < this.num; i++) {
                if (this.sortedEvaluators.get(i).matches(element, element2)) {
                    return true;
                }
            }
            return false;
        }

        public final String toString() {
            return StringUtil.join(this.evaluators, ", ");
        }
    }
}
