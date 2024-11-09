package org.jsoup.select;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.PseudoTextElement;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.ParseSettings;

/* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator.class */
public abstract class Evaluator {
    public abstract boolean matches(Element element, Element element2);

    public Predicate<Element> asPredicate(Element element) {
        return element2 -> {
            return matches(element, element2);
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reset() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int cost() {
        return 5;
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$Tag.class */
    public static final class Tag extends Evaluator {
        private final String tagName;

        public Tag(String str) {
            this.tagName = str;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.nameIs(this.tagName);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 1;
        }

        public final String toString() {
            return String.format("%s", this.tagName);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$TagEndsWith.class */
    public static final class TagEndsWith extends Evaluator {
        private final String tagName;

        public TagEndsWith(String str) {
            this.tagName = str;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.normalName().endsWith(this.tagName);
        }

        public final String toString() {
            return String.format("%s", this.tagName);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$Id.class */
    public static final class Id extends Evaluator {
        private final String id;

        public Id(String str) {
            this.id = str;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return this.id.equals(element2.id());
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 2;
        }

        public final String toString() {
            return String.format("#%s", this.id);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$Class.class */
    public static final class Class extends Evaluator {
        private final String className;

        public Class(String str) {
            this.className = str;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.hasClass(this.className);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 6;
        }

        public final String toString() {
            return String.format(".%s", this.className);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$Attribute.class */
    public static final class Attribute extends Evaluator {
        private final String key;

        public Attribute(String str) {
            this.key = str;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.hasAttr(this.key);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 2;
        }

        public final String toString() {
            return String.format("[%s]", this.key);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeStarting.class */
    public static final class AttributeStarting extends Evaluator {
        private final String keyPrefix;

        public AttributeStarting(String str) {
            Validate.notNull(str);
            this.keyPrefix = Normalizer.lowerCase(str);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            Iterator<org.jsoup.nodes.Attribute> it = element2.attributes().asList().iterator();
            while (it.hasNext()) {
                if (Normalizer.lowerCase(it.next().getKey()).startsWith(this.keyPrefix)) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 6;
        }

        public final String toString() {
            return String.format("[^%s]", this.keyPrefix);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeWithValue.class */
    public static final class AttributeWithValue extends AttributeKeyPair {
        public AttributeWithValue(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.hasAttr(this.key) && this.value.equalsIgnoreCase(element2.attr(this.key).trim());
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 3;
        }

        public final String toString() {
            return String.format("[%s=%s]", this.key, this.value);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeWithValueNot.class */
    public static final class AttributeWithValueNot extends AttributeKeyPair {
        public AttributeWithValueNot(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return !this.value.equalsIgnoreCase(element2.attr(this.key));
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 3;
        }

        public final String toString() {
            return String.format("[%s!=%s]", this.key, this.value);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeWithValueStarting.class */
    public static final class AttributeWithValueStarting extends AttributeKeyPair {
        public AttributeWithValueStarting(String str, String str2) {
            super(str, str2, false);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).startsWith(this.value);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 4;
        }

        public final String toString() {
            return String.format("[%s^=%s]", this.key, this.value);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeWithValueEnding.class */
    public static final class AttributeWithValueEnding extends AttributeKeyPair {
        public AttributeWithValueEnding(String str, String str2) {
            super(str, str2, false);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).endsWith(this.value);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 4;
        }

        public final String toString() {
            return String.format("[%s$=%s]", this.key, this.value);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeWithValueContaining.class */
    public static final class AttributeWithValueContaining extends AttributeKeyPair {
        public AttributeWithValueContaining(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).contains(this.value);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 6;
        }

        public final String toString() {
            return String.format("[%s*=%s]", this.key, this.value);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeWithValueMatching.class */
    public static final class AttributeWithValueMatching extends Evaluator {
        final String key;
        final Pattern pattern;

        public AttributeWithValueMatching(String str, Pattern pattern) {
            this.key = Normalizer.normalize(str);
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.hasAttr(this.key) && this.pattern.matcher(element2.attr(this.key)).find();
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 8;
        }

        public final String toString() {
            return String.format("[%s~=%s]", this.key, this.pattern.toString());
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AttributeKeyPair.class */
    public static abstract class AttributeKeyPair extends Evaluator {
        final String key;
        final String value;

        public AttributeKeyPair(String str, String str2) {
            this(str, str2, true);
        }

        public AttributeKeyPair(String str, String str2, boolean z) {
            Validate.notEmpty(str);
            Validate.notEmpty(str2);
            this.key = Normalizer.normalize(str);
            boolean z2 = (str2.startsWith("'") && str2.endsWith("'")) || (str2.startsWith("\"") && str2.endsWith("\""));
            boolean z3 = z2;
            str2 = z2 ? str2.substring(1, str2.length() - 1) : str2;
            this.value = z ? Normalizer.normalize(str2) : Normalizer.normalize(str2, z3);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$AllElements.class */
    public static final class AllElements extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return true;
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 10;
        }

        public final String toString() {
            return "*";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IndexLessThan.class */
    public static final class IndexLessThan extends IndexEvaluator {
        public IndexLessThan(int i) {
            super(i);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element != element2 && element2.elementSiblingIndex() < this.index;
        }

        public final String toString() {
            return String.format(":lt(%d)", Integer.valueOf(this.index));
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IndexGreaterThan.class */
    public static final class IndexGreaterThan extends IndexEvaluator {
        public IndexGreaterThan(int i) {
            super(i);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.elementSiblingIndex() > this.index;
        }

        public final String toString() {
            return String.format(":gt(%d)", Integer.valueOf(this.index));
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IndexEquals.class */
    public static final class IndexEquals extends IndexEvaluator {
        public IndexEquals(int i) {
            super(i);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.elementSiblingIndex() == this.index;
        }

        public final String toString() {
            return String.format(":eq(%d)", Integer.valueOf(this.index));
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsLastChild.class */
    public static final class IsLastChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            Element parent = element2.parent();
            return (parent == null || (parent instanceof Document) || element2 != parent.lastElementChild()) ? false : true;
        }

        public final String toString() {
            return ":last-child";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsFirstOfType.class */
    public static final class IsFirstOfType extends IsNthOfType {
        public IsFirstOfType() {
            super(0, 1);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public final String toString() {
            return ":first-of-type";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsLastOfType.class */
    public static final class IsLastOfType extends IsNthLastOfType {
        public IsLastOfType() {
            super(0, 1);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public final String toString() {
            return ":last-of-type";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$CssNthEvaluator.class */
    public static abstract class CssNthEvaluator extends Evaluator {

        /* renamed from: a, reason: collision with root package name */
        protected final int f4702a;

        /* renamed from: b, reason: collision with root package name */
        protected final int f4703b;

        protected abstract String getPseudoClass();

        protected abstract int calculatePosition(Element element, Element element2);

        public CssNthEvaluator(int i, int i2) {
            this.f4702a = i;
            this.f4703b = i2;
        }

        public CssNthEvaluator(int i) {
            this(0, i);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            Element parent = element2.parent();
            if (parent == null || (parent instanceof Document)) {
                return false;
            }
            int calculatePosition = calculatePosition(element, element2);
            return this.f4702a == 0 ? calculatePosition == this.f4703b : (calculatePosition - this.f4703b) * this.f4702a >= 0 && (calculatePosition - this.f4703b) % this.f4702a == 0;
        }

        public String toString() {
            if (this.f4702a == 0) {
                return String.format(":%s(%d)", getPseudoClass(), Integer.valueOf(this.f4703b));
            }
            if (this.f4703b == 0) {
                return String.format(":%s(%dn)", getPseudoClass(), Integer.valueOf(this.f4702a));
            }
            return String.format(":%s(%dn%+d)", getPseudoClass(), Integer.valueOf(this.f4702a), Integer.valueOf(this.f4703b));
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsNthChild.class */
    public static final class IsNthChild extends CssNthEvaluator {
        public IsNthChild(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected final int calculatePosition(Element element, Element element2) {
            return element2.elementSiblingIndex() + 1;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected final String getPseudoClass() {
            return "nth-child";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsNthLastChild.class */
    public static final class IsNthLastChild extends CssNthEvaluator {
        public IsNthLastChild(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected final int calculatePosition(Element element, Element element2) {
            if (element2.parent() == null) {
                return 0;
            }
            return element2.parent().childrenSize() - element2.elementSiblingIndex();
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected final String getPseudoClass() {
            return "nth-last-child";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsNthOfType.class */
    public static class IsNthOfType extends CssNthEvaluator {
        public IsNthOfType(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected int calculatePosition(Element element, Element element2) {
            Element parent = element2.parent();
            if (parent == null) {
                return 0;
            }
            int i = 0;
            int childNodeSize = parent.childNodeSize();
            for (int i2 = 0; i2 < childNodeSize; i2++) {
                Node childNode = parent.childNode(i2);
                if (childNode.normalName().equals(element2.normalName())) {
                    i++;
                }
                if (childNode == element2) {
                    break;
                }
            }
            return i;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-of-type";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsNthLastOfType.class */
    public static class IsNthLastOfType extends CssNthEvaluator {
        public IsNthLastOfType(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected int calculatePosition(Element element, Element element2) {
            if (element2.parent() == null) {
                return 0;
            }
            int i = 0;
            Element element3 = element2;
            while (true) {
                Element element4 = element3;
                if (element4 != null) {
                    if (element4.normalName().equals(element2.normalName())) {
                        i++;
                    }
                    element3 = element4.nextElementSibling();
                } else {
                    return i;
                }
            }
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-last-of-type";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsFirstChild.class */
    public static final class IsFirstChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            Element parent = element2.parent();
            return (parent == null || (parent instanceof Document) || element2 != parent.firstElementChild()) ? false : true;
        }

        public final String toString() {
            return ":first-child";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsRoot.class */
    public static final class IsRoot extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2 == (element instanceof Document ? element.firstElementChild() : element);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 1;
        }

        public final String toString() {
            return ":root";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsOnlyChild.class */
    public static final class IsOnlyChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            Element parent = element2.parent();
            return (parent == null || (parent instanceof Document) || !element2.siblingElements().isEmpty()) ? false : true;
        }

        public final String toString() {
            return ":only-child";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsOnlyOfType.class */
    public static final class IsOnlyOfType extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            Element parent = element2.parent();
            if (parent == null || (parent instanceof Document)) {
                return false;
            }
            int i = 0;
            Element firstElementChild = parent.firstElementChild();
            while (true) {
                Element element3 = firstElementChild;
                if (element3 != null) {
                    if (element3.normalName().equals(element2.normalName())) {
                        i++;
                    }
                    if (i > 1) {
                        break;
                    }
                    firstElementChild = element3.nextElementSibling();
                } else {
                    break;
                }
            }
            return i == 1;
        }

        public final String toString() {
            return ":only-of-type";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IsEmpty.class */
    public static final class IsEmpty extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            for (Node node : element2.childNodes()) {
                if (node instanceof TextNode) {
                    return ((TextNode) node).isBlank();
                }
                if (!(node instanceof Comment) && !(node instanceof XmlDeclaration) && !(node instanceof DocumentType)) {
                    return false;
                }
            }
            return true;
        }

        public final String toString() {
            return ":empty";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$IndexEvaluator.class */
    public static abstract class IndexEvaluator extends Evaluator {
        final int index;

        public IndexEvaluator(int i) {
            this.index = i;
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$ContainsText.class */
    public static final class ContainsText extends Evaluator {
        private final String searchText;

        public ContainsText(String str) {
            this.searchText = Normalizer.lowerCase(StringUtil.normaliseWhitespace(str));
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return Normalizer.lowerCase(element2.text()).contains(this.searchText);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 10;
        }

        public final String toString() {
            return String.format(":contains(%s)", this.searchText);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$ContainsWholeText.class */
    public static final class ContainsWholeText extends Evaluator {
        private final String searchText;

        public ContainsWholeText(String str) {
            this.searchText = str;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.wholeText().contains(this.searchText);
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 10;
        }

        public final String toString() {
            return String.format(":containsWholeText(%s)", this.searchText);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$ContainsWholeOwnText.class */
    public static final class ContainsWholeOwnText extends Evaluator {
        private final String searchText;

        public ContainsWholeOwnText(String str) {
            this.searchText = str;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return element2.wholeOwnText().contains(this.searchText);
        }

        public final String toString() {
            return String.format(":containsWholeOwnText(%s)", this.searchText);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$ContainsData.class */
    public static final class ContainsData extends Evaluator {
        private final String searchText;

        public ContainsData(String str) {
            this.searchText = Normalizer.lowerCase(str);
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return Normalizer.lowerCase(element2.data()).contains(this.searchText);
        }

        public final String toString() {
            return String.format(":containsData(%s)", this.searchText);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$ContainsOwnText.class */
    public static final class ContainsOwnText extends Evaluator {
        private final String searchText;

        public ContainsOwnText(String str) {
            this.searchText = Normalizer.lowerCase(StringUtil.normaliseWhitespace(str));
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return Normalizer.lowerCase(element2.ownText()).contains(this.searchText);
        }

        public final String toString() {
            return String.format(":containsOwn(%s)", this.searchText);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$Matches.class */
    public static final class Matches extends Evaluator {
        private final Pattern pattern;

        public Matches(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return this.pattern.matcher(element2.text()).find();
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 8;
        }

        public final String toString() {
            return String.format(":matches(%s)", this.pattern);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$MatchesOwn.class */
    public static final class MatchesOwn extends Evaluator {
        private final Pattern pattern;

        public MatchesOwn(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return this.pattern.matcher(element2.ownText()).find();
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 7;
        }

        public final String toString() {
            return String.format(":matchesOwn(%s)", this.pattern);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$MatchesWholeText.class */
    public static final class MatchesWholeText extends Evaluator {
        private final Pattern pattern;

        public MatchesWholeText(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return this.pattern.matcher(element2.wholeText()).find();
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 8;
        }

        public final String toString() {
            return String.format(":matchesWholeText(%s)", this.pattern);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$MatchesWholeOwnText.class */
    public static final class MatchesWholeOwnText extends Evaluator {
        private final Pattern pattern;

        public MatchesWholeOwnText(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            return this.pattern.matcher(element2.wholeOwnText()).find();
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return 7;
        }

        public final String toString() {
            return String.format(":matchesWholeOwnText(%s)", this.pattern);
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/select/Evaluator$MatchText.class */
    public static final class MatchText extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public final boolean matches(Element element, Element element2) {
            if (element2 instanceof PseudoTextElement) {
                return true;
            }
            for (TextNode textNode : element2.textNodes()) {
                PseudoTextElement pseudoTextElement = new PseudoTextElement(org.jsoup.parser.Tag.valueOf(element2.tagName(), element2.tag().namespace(), ParseSettings.preserveCase), element2.baseUri(), element2.attributes());
                textNode.replaceWith(pseudoTextElement);
                pseudoTextElement.appendChild(textNode);
            }
            return false;
        }

        @Override // org.jsoup.select.Evaluator
        protected final int cost() {
            return -1;
        }

        public final String toString() {
            return ":matchText";
        }
    }
}
