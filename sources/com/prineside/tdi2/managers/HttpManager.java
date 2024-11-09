package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.managers.GateManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;
import java.util.HashMap;

@REGS(serializer = GateManager.Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/HttpManager.class */
public final class HttpManager extends Manager.ManagerAdapter {

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/HttpManager$RequestListener.class */
    public interface RequestListener {
        void onFinish(boolean z, @Null Net.HttpResponse httpResponse, boolean z2, @Null Throwable th);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/HttpManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<HttpManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public HttpManager read() {
            return Game.i.httpManager;
        }
    }

    public final PreparedRequest prepareRequest(String str, String str2) {
        return new PreparedRequest(this, str, (byte) 0).url(str2);
    }

    public final PreparedRequest post(String str) {
        return prepareRequest(Net.HttpMethods.POST, str);
    }

    public final PreparedRequest get(String str) {
        return prepareRequest(Net.HttpMethods.GET, str);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/HttpManager$PreparedRequest.class */
    public final class PreparedRequest {

        /* renamed from: a, reason: collision with root package name */
        private final Net.HttpRequest f2346a;

        /* renamed from: b, reason: collision with root package name */
        @Null
        private RequestListener f2347b;

        @Null
        private HashMap<String, String> c;

        /* synthetic */ PreparedRequest(HttpManager httpManager, String str, byte b2) {
            this(httpManager, str);
        }

        private PreparedRequest(HttpManager httpManager, String str) {
            Preconditions.checkNotNull(str);
            this.f2346a = new Net.HttpRequest(str);
        }

        public final Net.HttpRequest getHttp() {
            return this.f2346a;
        }

        public final PreparedRequest url(String str) {
            Preconditions.checkNotNull(str);
            this.f2346a.setUrl(str);
            return this;
        }

        public final PreparedRequest timeOut(int i) {
            this.f2346a.setTimeOut(i);
            return this;
        }

        public final PreparedRequest param(String str, String str2) {
            Preconditions.checkNotNull(str, "key can not be null (%s, %s)", str, str2);
            Preconditions.checkNotNull(str2, "value can not be null (%s, %s)", str, str2);
            if (this.c == null) {
                this.c = new HashMap<>();
            }
            this.c.put(str, str2);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final PreparedRequest params(ObjectMap<String, String> objectMap) {
            Preconditions.checkNotNull(objectMap);
            ObjectMap.Entries<String, String> it = objectMap.iterator();
            while (it.hasNext()) {
                ObjectMap.Entry next = it.next();
                param((String) next.key, (String) next.value);
            }
            return this;
        }

        public final PreparedRequest listener(RequestListener requestListener) {
            this.f2347b = requestListener;
            return this;
        }

        public final PreparedRequest send() {
            Net.HttpResponseListener httpResponseListener = null;
            if (this.f2347b != null) {
                httpResponseListener = new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.HttpManager.PreparedRequest.1
                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        PreparedRequest.this.f2347b.onFinish(true, httpResponse, false, null);
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void failed(Throwable th) {
                        PreparedRequest.this.f2347b.onFinish(false, null, false, th);
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void cancelled() {
                        PreparedRequest.this.f2347b.onFinish(false, null, true, null);
                    }
                };
            }
            if (this.c != null) {
                this.f2346a.setContent(HttpParametersUtils.convertHttpParameters(this.c));
            }
            Gdx.f881net.sendHttpRequest(this.f2346a, httpResponseListener);
            return this;
        }
    }
}
