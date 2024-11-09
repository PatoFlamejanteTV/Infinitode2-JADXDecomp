package nonapi.io.github.classgraph.types;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/types/ParseException.class */
public class ParseException extends Exception {
    static final long serialVersionUID = 1;

    public ParseException(Parser parser, String str) {
        super(parser == null ? str : str + " (" + parser.getPositionInfo() + ")");
    }
}
