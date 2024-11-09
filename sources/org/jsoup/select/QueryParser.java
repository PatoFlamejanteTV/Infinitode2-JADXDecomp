package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;

/* loaded from: infinitode-2.jar:org/jsoup/select/QueryParser.class */
public class QueryParser {
    private static final char[] Combinators;
    private static final String[] AttributeEvals;
    private final TokenQueue tq;
    private final String query;
    private final List<Evaluator> evals = new ArrayList();
    private static final Pattern NTH_AB;
    private static final Pattern NTH_B;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !QueryParser.class.desiredAssertionStatus();
        Combinators = new char[]{',', '>', '+', '~', ' '};
        AttributeEvals = new String[]{"=", "!=", "^=", "$=", "*=", "~="};
        NTH_AB = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);
        NTH_B = Pattern.compile("([+-])?(\\d+)");
    }

    private QueryParser(String str) {
        Validate.notEmpty(str);
        String trim = str.trim();
        this.query = trim;
        this.tq = new TokenQueue(trim);
    }

    public static Evaluator parse(String str) {
        try {
            return new QueryParser(str).parse();
        } catch (IllegalArgumentException e) {
            throw new Selector.SelectorParseException(e.getMessage());
        }
    }

    Evaluator parse() {
        this.tq.consumeWhitespace();
        if (this.tq.matchesAny(Combinators)) {
            this.evals.add(new StructuralEvaluator.Root());
            combinator(this.tq.consume());
        } else {
            this.evals.add(consumeEvaluator());
        }
        while (!this.tq.isEmpty()) {
            boolean consumeWhitespace = this.tq.consumeWhitespace();
            if (this.tq.matchesAny(Combinators)) {
                combinator(this.tq.consume());
            } else if (consumeWhitespace) {
                combinator(' ');
            } else {
                this.evals.add(consumeEvaluator());
            }
        }
        if (this.evals.size() == 1) {
            return this.evals.get(0);
        }
        return new CombiningEvaluator.And(this.evals);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v17, types: [org.jsoup.select.CombiningEvaluator$Or] */
    /* JADX WARN: Type inference failed for: r0v18, types: [org.jsoup.select.CombiningEvaluator$Or] */
    /* JADX WARN: Type inference failed for: r0v35, types: [org.jsoup.select.StructuralEvaluator$ImmediateParentRun] */
    /* JADX WARN: Type inference failed for: r0v42, types: [org.jsoup.select.Evaluator] */
    /* JADX WARN: Type inference failed for: r0v52 */
    /* JADX WARN: Type inference failed for: r0v53 */
    private void combinator(char c) {
        Evaluator evaluator;
        CombiningEvaluator combiningEvaluator;
        CombiningEvaluator combiningEvaluator2;
        CombiningEvaluator combiningEvaluator3;
        this.tq.consumeWhitespace();
        Evaluator parse = parse(consumeSubQuery());
        boolean z = false;
        if (this.evals.size() == 1) {
            Evaluator evaluator2 = this.evals.get(0);
            evaluator = evaluator2;
            combiningEvaluator = evaluator2;
            if ((evaluator2 instanceof CombiningEvaluator.Or) && c != ',') {
                evaluator = ((CombiningEvaluator.Or) evaluator).rightMostEvaluator();
                if (!$assertionsDisabled && evaluator == null) {
                    throw new AssertionError();
                }
                z = true;
            }
        } else {
            CombiningEvaluator and = new CombiningEvaluator.And(this.evals);
            evaluator = and;
            combiningEvaluator = and;
        }
        this.evals.clear();
        switch (c) {
            case ' ':
                combiningEvaluator3 = new CombiningEvaluator.And(new StructuralEvaluator.Parent(evaluator), parse);
                break;
            case '+':
                combiningEvaluator3 = new CombiningEvaluator.And(new StructuralEvaluator.ImmediatePreviousSibling(evaluator), parse);
                break;
            case ',':
                if (evaluator instanceof CombiningEvaluator.Or) {
                    combiningEvaluator2 = (CombiningEvaluator.Or) evaluator;
                } else {
                    ?? or = new CombiningEvaluator.Or();
                    combiningEvaluator2 = or;
                    or.add(evaluator);
                }
                combiningEvaluator2.add(parse);
                combiningEvaluator3 = combiningEvaluator2;
                break;
            case '>':
                CombiningEvaluator immediateParentRun = evaluator instanceof StructuralEvaluator.ImmediateParentRun ? (StructuralEvaluator.ImmediateParentRun) evaluator : new StructuralEvaluator.ImmediateParentRun(evaluator);
                immediateParentRun.add(parse);
                combiningEvaluator3 = immediateParentRun;
                break;
            case '~':
                combiningEvaluator3 = new CombiningEvaluator.And(new StructuralEvaluator.PreviousSibling(evaluator), parse);
                break;
            default:
                throw new Selector.SelectorParseException("Unknown combinator '%s'", Character.valueOf(c));
        }
        if (z) {
            ((CombiningEvaluator.Or) combiningEvaluator).replaceRightMostEvaluator(combiningEvaluator3);
        } else {
            combiningEvaluator = combiningEvaluator3;
        }
        this.evals.add(combiningEvaluator);
    }

    private String consumeSubQuery() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        boolean z = false;
        while (!this.tq.isEmpty()) {
            if (this.tq.matchesAny(Combinators)) {
                if (z) {
                    break;
                }
                borrowBuilder.append(this.tq.consume());
            } else {
                z = true;
                if (this.tq.matches("(")) {
                    borrowBuilder.append("(").append(this.tq.chompBalanced('(', ')')).append(")");
                } else if (this.tq.matches("[")) {
                    borrowBuilder.append("[").append(this.tq.chompBalanced('[', ']')).append("]");
                } else {
                    borrowBuilder.append(this.tq.consume());
                }
            }
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    private Evaluator consumeEvaluator() {
        if (this.tq.matchChomp("#")) {
            return byId();
        }
        if (this.tq.matchChomp(".")) {
            return byClass();
        }
        if (this.tq.matchesWord() || this.tq.matches("*|")) {
            return byTag();
        }
        if (this.tq.matches("[")) {
            return byAttribute();
        }
        if (this.tq.matchChomp("*")) {
            return new Evaluator.AllElements();
        }
        if (this.tq.matchChomp(":")) {
            return parsePseudoSelector();
        }
        throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.tq.remainder());
    }

    private Evaluator parsePseudoSelector() {
        String consumeCssIdentifier = this.tq.consumeCssIdentifier();
        boolean z = -1;
        switch (consumeCssIdentifier.hashCode()) {
            case -2141736343:
                if (consumeCssIdentifier.equals("containsData")) {
                    z = 9;
                    break;
                }
                break;
            case -2136991809:
                if (consumeCssIdentifier.equals("first-child")) {
                    z = 19;
                    break;
                }
                break;
            case -1939921007:
                if (consumeCssIdentifier.equals("matchesWholeText")) {
                    z = 12;
                    break;
                }
                break;
            case -1754914063:
                if (consumeCssIdentifier.equals("nth-child")) {
                    z = 15;
                    break;
                }
                break;
            case -1629748624:
                if (consumeCssIdentifier.equals("nth-last-child")) {
                    z = 16;
                    break;
                }
                break;
            case -947996741:
                if (consumeCssIdentifier.equals("only-child")) {
                    z = 23;
                    break;
                }
                break;
            case -897532411:
                if (consumeCssIdentifier.equals("nth-of-type")) {
                    z = 17;
                    break;
                }
                break;
            case -872629820:
                if (consumeCssIdentifier.equals("nth-last-of-type")) {
                    z = 18;
                    break;
                }
                break;
            case -567445985:
                if (consumeCssIdentifier.equals("contains")) {
                    z = 5;
                    break;
                }
                break;
            case -55413797:
                if (consumeCssIdentifier.equals("containsWholeOwnText")) {
                    z = 8;
                    break;
                }
                break;
            case 3244:
                if (consumeCssIdentifier.equals("eq")) {
                    z = 2;
                    break;
                }
                break;
            case 3309:
                if (consumeCssIdentifier.equals("gt")) {
                    z = true;
                    break;
                }
                break;
            case 3370:
                if (consumeCssIdentifier.equals("is")) {
                    z = 4;
                    break;
                }
                break;
            case 3464:
                if (consumeCssIdentifier.equals("lt")) {
                    z = false;
                    break;
                }
                break;
            case 103066:
                if (consumeCssIdentifier.equals("has")) {
                    z = 3;
                    break;
                }
                break;
            case 109267:
                if (consumeCssIdentifier.equals("not")) {
                    z = 14;
                    break;
                }
                break;
            case 3506402:
                if (consumeCssIdentifier.equals("root")) {
                    z = 26;
                    break;
                }
                break;
            case 96634189:
                if (consumeCssIdentifier.equals("empty")) {
                    z = 25;
                    break;
                }
                break;
            case 208017639:
                if (consumeCssIdentifier.equals("containsOwn")) {
                    z = 6;
                    break;
                }
                break;
            case 614017170:
                if (consumeCssIdentifier.equals("matchText")) {
                    z = 27;
                    break;
                }
                break;
            case 835834661:
                if (consumeCssIdentifier.equals("last-child")) {
                    z = 20;
                    break;
                }
                break;
            case 840862003:
                if (consumeCssIdentifier.equals("matches")) {
                    z = 10;
                    break;
                }
                break;
            case 1255901423:
                if (consumeCssIdentifier.equals("matchesWholeOwnText")) {
                    z = 13;
                    break;
                }
                break;
            case 1292941139:
                if (consumeCssIdentifier.equals("first-of-type")) {
                    z = 21;
                    break;
                }
                break;
            case 1455900751:
                if (consumeCssIdentifier.equals("only-of-type")) {
                    z = 24;
                    break;
                }
                break;
            case 1870740819:
                if (consumeCssIdentifier.equals("matchesOwn")) {
                    z = 11;
                    break;
                }
                break;
            case 2014184485:
                if (consumeCssIdentifier.equals("containsWholeText")) {
                    z = 7;
                    break;
                }
                break;
            case 2025926969:
                if (consumeCssIdentifier.equals("last-of-type")) {
                    z = 22;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return new Evaluator.IndexLessThan(consumeIndex());
            case true:
                return new Evaluator.IndexGreaterThan(consumeIndex());
            case true:
                return new Evaluator.IndexEquals(consumeIndex());
            case true:
                return has();
            case true:
                return is();
            case true:
                return contains(false);
            case true:
                return contains(true);
            case true:
                return containsWholeText(false);
            case true:
                return containsWholeText(true);
            case true:
                return containsData();
            case true:
                return matches(false);
            case true:
                return matches(true);
            case true:
                return matchesWholeText(false);
            case true:
                return matchesWholeText(true);
            case true:
                return not();
            case true:
                return cssNthChild(false, false);
            case true:
                return cssNthChild(true, false);
            case true:
                return cssNthChild(false, true);
            case true:
                return cssNthChild(true, true);
            case true:
                return new Evaluator.IsFirstChild();
            case true:
                return new Evaluator.IsLastChild();
            case true:
                return new Evaluator.IsFirstOfType();
            case true:
                return new Evaluator.IsLastOfType();
            case true:
                return new Evaluator.IsOnlyChild();
            case true:
                return new Evaluator.IsOnlyOfType();
            case true:
                return new Evaluator.IsEmpty();
            case true:
                return new Evaluator.IsRoot();
            case true:
                return new Evaluator.MatchText();
            default:
                throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.tq.remainder());
        }
    }

    private Evaluator byId() {
        String consumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        return new Evaluator.Id(consumeCssIdentifier);
    }

    private Evaluator byClass() {
        String consumeCssIdentifier = this.tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        return new Evaluator.Class(consumeCssIdentifier.trim());
    }

    private Evaluator byTag() {
        Evaluator tag;
        String normalize = Normalizer.normalize(this.tq.consumeElementSelector());
        String str = normalize;
        Validate.notEmpty(normalize);
        if (str.startsWith("*|")) {
            tag = new CombiningEvaluator.Or(new Evaluator.Tag(str.substring(2)), new Evaluator.TagEndsWith(str.replace("*|", ":")));
        } else {
            if (str.contains("|")) {
                str = str.replace("|", ":");
            }
            tag = new Evaluator.Tag(str);
        }
        return tag;
    }

    private Evaluator byAttribute() {
        Evaluator attributeWithValueMatching;
        TokenQueue tokenQueue = new TokenQueue(this.tq.chompBalanced('[', ']'));
        String consumeToAny = tokenQueue.consumeToAny(AttributeEvals);
        Validate.notEmpty(consumeToAny);
        tokenQueue.consumeWhitespace();
        if (tokenQueue.isEmpty()) {
            attributeWithValueMatching = consumeToAny.startsWith("^") ? new Evaluator.AttributeStarting(consumeToAny.substring(1)) : consumeToAny.equals("*") ? new Evaluator.AttributeStarting("") : new Evaluator.Attribute(consumeToAny);
        } else if (tokenQueue.matchChomp("=")) {
            attributeWithValueMatching = new Evaluator.AttributeWithValue(consumeToAny, tokenQueue.remainder());
        } else if (tokenQueue.matchChomp("!=")) {
            attributeWithValueMatching = new Evaluator.AttributeWithValueNot(consumeToAny, tokenQueue.remainder());
        } else if (tokenQueue.matchChomp("^=")) {
            attributeWithValueMatching = new Evaluator.AttributeWithValueStarting(consumeToAny, tokenQueue.remainder());
        } else if (tokenQueue.matchChomp("$=")) {
            attributeWithValueMatching = new Evaluator.AttributeWithValueEnding(consumeToAny, tokenQueue.remainder());
        } else if (tokenQueue.matchChomp("*=")) {
            attributeWithValueMatching = new Evaluator.AttributeWithValueContaining(consumeToAny, tokenQueue.remainder());
        } else if (tokenQueue.matchChomp("~=")) {
            attributeWithValueMatching = new Evaluator.AttributeWithValueMatching(consumeToAny, Pattern.compile(tokenQueue.remainder()));
        } else {
            throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, tokenQueue.remainder());
        }
        return attributeWithValueMatching;
    }

    private Evaluator cssNthChild(boolean z, boolean z2) {
        int i;
        int parseInt;
        Evaluator isNthChild;
        String normalize = Normalizer.normalize(consumeParens());
        Matcher matcher = NTH_AB.matcher(normalize);
        Matcher matcher2 = NTH_B.matcher(normalize);
        if ("odd".equals(normalize)) {
            i = 2;
            parseInt = 1;
        } else if ("even".equals(normalize)) {
            i = 2;
            parseInt = 0;
        } else if (matcher.matches()) {
            i = matcher.group(3) != null ? Integer.parseInt(matcher.group(1).replaceFirst("^\\+", "")) : 1;
            parseInt = matcher.group(4) != null ? Integer.parseInt(matcher.group(4).replaceFirst("^\\+", "")) : 0;
        } else if (matcher2.matches()) {
            i = 0;
            parseInt = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
        } else {
            throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", normalize);
        }
        if (z2) {
            if (z) {
                isNthChild = new Evaluator.IsNthLastOfType(i, parseInt);
            } else {
                isNthChild = new Evaluator.IsNthOfType(i, parseInt);
            }
        } else if (z) {
            isNthChild = new Evaluator.IsNthLastChild(i, parseInt);
        } else {
            isNthChild = new Evaluator.IsNthChild(i, parseInt);
        }
        return isNthChild;
    }

    private String consumeParens() {
        return this.tq.chompBalanced('(', ')');
    }

    private int consumeIndex() {
        String trim = consumeParens().trim();
        Validate.isTrue(StringUtil.isNumeric(trim), "Index must be numeric");
        return Integer.parseInt(trim);
    }

    private Evaluator has() {
        String consumeParens = consumeParens();
        Validate.notEmpty(consumeParens, ":has(selector) sub-select must not be empty");
        return new StructuralEvaluator.Has(parse(consumeParens));
    }

    private Evaluator is() {
        String consumeParens = consumeParens();
        Validate.notEmpty(consumeParens, ":is(selector) sub-select must not be empty");
        return new StructuralEvaluator.Is(parse(consumeParens));
    }

    private Evaluator contains(boolean z) {
        String str = z ? ":containsOwn" : ":contains";
        String unescape = TokenQueue.unescape(consumeParens());
        Validate.notEmpty(unescape, str + "(text) query must not be empty");
        return z ? new Evaluator.ContainsOwnText(unescape) : new Evaluator.ContainsText(unescape);
    }

    private Evaluator containsWholeText(boolean z) {
        String str = z ? ":containsWholeOwnText" : ":containsWholeText";
        String unescape = TokenQueue.unescape(consumeParens());
        Validate.notEmpty(unescape, str + "(text) query must not be empty");
        return z ? new Evaluator.ContainsWholeOwnText(unescape) : new Evaluator.ContainsWholeText(unescape);
    }

    private Evaluator containsData() {
        String unescape = TokenQueue.unescape(consumeParens());
        Validate.notEmpty(unescape, ":containsData(text) query must not be empty");
        return new Evaluator.ContainsData(unescape);
    }

    private Evaluator matches(boolean z) {
        String str = z ? ":matchesOwn" : ":matches";
        String consumeParens = consumeParens();
        Validate.notEmpty(consumeParens, str + "(regex) query must not be empty");
        if (z) {
            return new Evaluator.MatchesOwn(Pattern.compile(consumeParens));
        }
        return new Evaluator.Matches(Pattern.compile(consumeParens));
    }

    private Evaluator matchesWholeText(boolean z) {
        String str = z ? ":matchesWholeOwnText" : ":matchesWholeText";
        String consumeParens = consumeParens();
        Validate.notEmpty(consumeParens, str + "(regex) query must not be empty");
        if (z) {
            return new Evaluator.MatchesWholeOwnText(Pattern.compile(consumeParens));
        }
        return new Evaluator.MatchesWholeText(Pattern.compile(consumeParens));
    }

    private Evaluator not() {
        String consumeParens = consumeParens();
        Validate.notEmpty(consumeParens, ":not(selector) subselect must not be empty");
        return new StructuralEvaluator.Not(parse(consumeParens));
    }

    public String toString() {
        return this.query;
    }
}
