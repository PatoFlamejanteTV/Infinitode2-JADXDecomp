package nonapi.io.github.classgraph.json;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONParser.class */
final class JSONParser extends Parser {
    private JSONParser(String str) {
        super(str);
    }

    private int getAndParseHexChar() {
        char cVar = getc();
        if (cVar >= '0' && cVar <= '9') {
            return cVar - '0';
        }
        if (cVar >= 'a' && cVar <= 'f') {
            return (cVar - 'a') + 10;
        }
        if (cVar >= 'A' && cVar <= 'F') {
            return (cVar - 'A') + 10;
        }
        throw new ParseException(this, "Invalid character in Unicode escape sequence: " + cVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.CharSequence parseString() {
        /*
            Method dump skipped, instructions count: 488
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: nonapi.io.github.classgraph.json.JSONParser.parseString():java.lang.CharSequence");
    }

    private Number parseNumber() {
        char peek;
        char peek2;
        char peek3;
        int position = getPosition();
        if (peekMatches("Infinity")) {
            advance(8);
            return Double.valueOf(Double.POSITIVE_INFINITY);
        }
        if (peekMatches("-Infinity")) {
            advance(9);
            return Double.valueOf(Double.NEGATIVE_INFINITY);
        }
        if (peekMatches("NaN")) {
            advance(3);
            return Double.valueOf(Double.NaN);
        }
        if (peek() == '-') {
            next();
        }
        int position2 = getPosition();
        while (hasMore() && (peek3 = peek()) >= '0' && peek3 <= '9') {
            next();
        }
        int position3 = getPosition();
        int i = position3 - position2;
        if (i == 0) {
            throw new ParseException(this, "Expected a number");
        }
        boolean z = peek() == '.';
        boolean z2 = z;
        if (z) {
            next();
            while (hasMore() && (peek2 = peek()) >= '0' && peek2 <= '9') {
                next();
            }
            if (getPosition() - (position3 + 1) == 0) {
                throw new ParseException(this, "Expected digits after decimal point");
            }
        }
        boolean z3 = peek() == 'e' || peek() == 'E';
        boolean z4 = z3;
        if (z3) {
            next();
            char peek4 = peek();
            if (peek4 == '-' || peek4 == '+') {
                next();
            }
            int position4 = getPosition();
            while (hasMore() && (peek = peek()) >= '0' && peek <= '9') {
                next();
            }
            if (getPosition() - position4 == 0) {
                throw new ParseException(this, "Expected an exponent");
            }
        }
        String substring = getSubstring(position, getPosition());
        if (z2 || z4) {
            return Double.valueOf(substring);
        }
        if (i < 10) {
            return Integer.valueOf(substring);
        }
        if (i == 10) {
            long parseLong = Long.parseLong(substring);
            if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                return Integer.valueOf((int) parseLong);
            }
            return Long.valueOf(parseLong);
        }
        return Long.valueOf(substring);
    }

    private JSONArray parseJSONArray() {
        expect('[');
        skipWhitespace();
        if (peek() == ']') {
            next();
            return new JSONArray(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        while (peek() != ']') {
            if (z) {
                z = false;
            } else {
                expect(',');
            }
            arrayList.add(parseJSON());
        }
        expect(']');
        return new JSONArray(arrayList);
    }

    private JSONObject parseJSONObject() {
        expect('{');
        skipWhitespace();
        if (peek() == '}') {
            next();
            return new JSONObject((List<Map.Entry<String, Object>>) Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(arrayList);
        boolean z = true;
        while (peek() != '}') {
            if (z) {
                z = false;
            } else {
                expect(',');
            }
            CharSequence parseString = parseString();
            if (parseString == null) {
                throw new ParseException(this, "Object keys must be strings");
            }
            if (peek() != ':') {
                return null;
            }
            expect(':');
            Object parseJSON = parseJSON();
            if (parseString.equals("__ID")) {
                if (parseJSON == null) {
                    throw new ParseException(this, "Got null value for \"__ID\" key");
                }
                jSONObject.objectId = (CharSequence) parseJSON;
            } else {
                arrayList.add(new AbstractMap.SimpleEntry(parseString.toString(), parseJSON));
            }
        }
        expect('}');
        return jSONObject;
    }

    private Object parseJSON() {
        skipWhitespace();
        char peek = peek();
        if (peek == '{') {
            JSONObject parseJSONObject = parseJSONObject();
            skipWhitespace();
            return parseJSONObject;
        }
        if (peek == '[') {
            JSONArray parseJSONArray = parseJSONArray();
            skipWhitespace();
            return parseJSONArray;
        }
        if (peek == '\"') {
            CharSequence parseString = parseString();
            skipWhitespace();
            if (parseString == null) {
                throw new ParseException(this, "Invalid string");
            }
            return parseString;
        }
        if (peekMatches("true")) {
            advance(4);
            skipWhitespace();
            return Boolean.TRUE;
        }
        if (peekMatches("false")) {
            advance(5);
            skipWhitespace();
            return Boolean.FALSE;
        }
        if (peekMatches("null")) {
            advance(4);
            skipWhitespace();
            return null;
        }
        Number parseNumber = parseNumber();
        skipWhitespace();
        return parseNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object parseJSON(String str) {
        return new JSONParser(str).parseJSON();
    }
}
