package org.jsoup.select;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.NodeIterator;

/* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator.class */
abstract class StructuralEvaluator extends Evaluator {
    final Evaluator evaluator;
    final ThreadLocal<IdentityHashMap<Element, IdentityHashMap<Element, Boolean>>> threadMemo = ThreadLocal.withInitial(IdentityHashMap::new);

    public StructuralEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    boolean memoMatches(Element element, Element element2) {
        IdentityHashMap<Element, IdentityHashMap<Element, Boolean>> identityHashMap = this.threadMemo.get();
        IdentityHashMap<Element, Boolean> identityHashMap2 = identityHashMap.get(element);
        IdentityHashMap<Element, Boolean> identityHashMap3 = identityHashMap2;
        if (identityHashMap2 == null) {
            identityHashMap3 = new IdentityHashMap<>();
            identityHashMap.put(element, identityHashMap3);
        }
        Boolean bool = identityHashMap3.get(element2);
        Boolean bool2 = bool;
        if (bool == null) {
            bool2 = Boolean.valueOf(this.evaluator.matches(element, element2));
            identityHashMap3.put(element2, bool2);
        }
        return bool2.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.select.Evaluator
    public void reset() {
        this.threadMemo.get().clear();
        super.reset();
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$Root.class */
    static class Root extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            return element == element2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 1;
        }

        public String toString() {
            return "";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$Has.class */
    static class Has extends StructuralEvaluator {
        static final ThreadLocal<NodeIterator<Element>> ThreadElementIter = ThreadLocal.withInitial(() -> {
            return new NodeIterator(new Element("html"), Element.class);
        });

        public Has(Evaluator evaluator) {
            super(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            NodeIterator<Element> nodeIterator = ThreadElementIter.get();
            nodeIterator.restart(element2);
            while (nodeIterator.hasNext()) {
                Element next = nodeIterator.next();
                if (next != element2 && this.evaluator.matches(element2, next)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 10 * this.evaluator.cost();
        }

        public String toString() {
            return String.format(":has(%s)", this.evaluator);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$Is.class */
    static class Is extends StructuralEvaluator {
        public Is(Evaluator evaluator) {
            super(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            return this.evaluator.matches(element, element2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 2 + this.evaluator.cost();
        }

        public String toString() {
            return String.format(":is(%s)", this.evaluator);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$Not.class */
    static class Not extends StructuralEvaluator {
        public Not(Evaluator evaluator) {
            super(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            return !memoMatches(element, element2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 2 + this.evaluator.cost();
        }

        public String toString() {
            return String.format(":not(%s)", this.evaluator);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$Parent.class */
    static class Parent extends StructuralEvaluator {
        public Parent(Evaluator evaluator) {
            super(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            Element parent = element2.parent();
            while (true) {
                Element element3 = parent;
                if (element3 != null) {
                    if (memoMatches(element, element3)) {
                        return true;
                    }
                    if (element3 != element) {
                        parent = element3.parent();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 2 * this.evaluator.cost();
        }

        public String toString() {
            return String.format("%s ", this.evaluator);
        }
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$ImmediateParent.class */
    static class ImmediateParent extends StructuralEvaluator {
        public ImmediateParent(Evaluator evaluator) {
            super(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            Element parent;
            return (element == element2 || (parent = element2.parent()) == null || !memoMatches(element, parent)) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 1 + this.evaluator.cost();
        }

        public String toString() {
            return String.format("%s > ", this.evaluator);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$ImmediateParentRun.class */
    static class ImmediateParentRun extends Evaluator {
        final ArrayList<Evaluator> evaluators = new ArrayList<>();
        int cost = 2;

        public ImmediateParentRun(Evaluator evaluator) {
            this.evaluators.add(evaluator);
            this.cost += evaluator.cost();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void add(Evaluator evaluator) {
            this.evaluators.add(evaluator);
            this.cost += evaluator.cost();
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            if (element2 == element) {
                return false;
            }
            for (int size = this.evaluators.size() - 1; size >= 0; size--) {
                if (element2 == null || !this.evaluators.get(size).matches(element, element2)) {
                    return false;
                }
                element2 = element2.parent();
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return this.cost;
        }

        public String toString() {
            return StringUtil.join(this.evaluators, " > ");
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$PreviousSibling.class */
    static class PreviousSibling extends StructuralEvaluator {
        public PreviousSibling(Evaluator evaluator) {
            super(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            Element firstElementSibling = element2.firstElementSibling();
            while (true) {
                Element element3 = firstElementSibling;
                if (element3 != null && element3 != element2) {
                    if (memoMatches(element, element3)) {
                        return true;
                    }
                    firstElementSibling = element3.nextElementSibling();
                } else {
                    return false;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 3 * this.evaluator.cost();
        }

        public String toString() {
            return String.format("%s ~ ", this.evaluator);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/StructuralEvaluator$ImmediatePreviousSibling.class */
    static class ImmediatePreviousSibling extends StructuralEvaluator {
        public ImmediatePreviousSibling(Evaluator evaluator) {
            super(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            Element previousElementSibling;
            return (element == element2 || (previousElementSibling = element2.previousElementSibling()) == null || !memoMatches(element, previousElementSibling)) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jsoup.select.Evaluator
        public int cost() {
            return 2 + this.evaluator.cost();
        }

        public String toString() {
            return String.format("%s + ", this.evaluator);
        }
    }
}
