package nonapi.io.github.classgraph.json;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONReference.class */
class JSONReference {
    Object idObject;

    public JSONReference(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("idObject cannot be null");
        }
        this.idObject = obj;
    }
}
