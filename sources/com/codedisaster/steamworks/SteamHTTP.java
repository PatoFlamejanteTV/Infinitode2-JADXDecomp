package com.codedisaster.steamworks;

import com.badlogic.gdx.net.HttpStatus;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamHTTP.class */
public class SteamHTTP extends SteamInterface {
    private final boolean isServer;

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamHTTP$HTTPMethod.class */
    public enum HTTPMethod {
        Invalid,
        GET,
        HEAD,
        POST,
        PUT,
        DELETE,
        OPTIONS
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamHTTP$HTTPStatusCode.class */
    public enum HTTPStatusCode {
        Invalid(0),
        Continue(100),
        SwitchingProtocols(101),
        OK(200),
        Created(201),
        Accepted(HttpStatus.SC_ACCEPTED),
        NonAuthoritative(203),
        NoContent(HttpStatus.SC_NO_CONTENT),
        ResetContent(HttpStatus.SC_RESET_CONTENT),
        PartialContent(HttpStatus.SC_PARTIAL_CONTENT),
        MultipleChoices(300),
        MovedPermanently(301),
        Found(302),
        SeeOther(303),
        NotModified(304),
        UseProxy(305),
        TemporaryRedirect(307),
        BadRequest(400),
        Unauthorized(HttpStatus.SC_UNAUTHORIZED),
        PaymentRequired(HttpStatus.SC_PAYMENT_REQUIRED),
        Forbidden(HttpStatus.SC_FORBIDDEN),
        NotFound(HttpStatus.SC_NOT_FOUND),
        MethodNotAllowed(HttpStatus.SC_METHOD_NOT_ALLOWED),
        NotAcceptable(HttpStatus.SC_NOT_ACCEPTABLE),
        ProxyAuthRequired(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED),
        RequestTimeout(HttpStatus.SC_REQUEST_TIMEOUT),
        Conflict(HttpStatus.SC_CONFLICT),
        Gone(HttpStatus.SC_GONE),
        LengthRequired(HttpStatus.SC_LENGTH_REQUIRED),
        PreconditionFailed(HttpStatus.SC_PRECONDITION_FAILED),
        RequestEntityTooLarge(HttpStatus.SC_REQUEST_TOO_LONG),
        RequestURITooLong(HttpStatus.SC_REQUEST_URI_TOO_LONG),
        UnsupportedMediaType(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE),
        RequestedRangeNotSatisfiable(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE),
        ExpectationFailed(HttpStatus.SC_EXPECTATION_FAILED),
        Unknown4xx(418),
        TooManyRequests(429),
        InternalServerError(500),
        NotImplemented(501),
        BadGateway(502),
        ServiceUnavailable(503),
        GatewayTimeout(504),
        HTTPVersionNotSupported(505),
        Unknown5xx(599);

        private final int code;
        private static final HTTPStatusCode[] values = values();

        HTTPStatusCode(int i) {
            this.code = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static HTTPStatusCode byValue(int i) {
            int i2 = 0;
            int length = values.length - 1;
            while (i2 <= length) {
                int i3 = (i2 + length) / 2;
                HTTPStatusCode hTTPStatusCode = values[i3];
                if (i < hTTPStatusCode.code) {
                    length = i3 - 1;
                } else if (i > hTTPStatusCode.code) {
                    i2 = i3 + 1;
                } else {
                    return hTTPStatusCode;
                }
            }
            return Invalid;
        }
    }

    public SteamHTTP(SteamHTTPCallback steamHTTPCallback) {
        this(false, SteamHTTPNative.createCallback(new SteamHTTPCallbackAdapter(steamHTTPCallback)));
    }

    SteamHTTP(boolean z, long j) {
        super(j);
        this.isServer = z;
    }

    public SteamHTTPRequestHandle createHTTPRequest(HTTPMethod hTTPMethod, String str) {
        return new SteamHTTPRequestHandle(SteamHTTPNative.createHTTPRequest(this.isServer, hTTPMethod.ordinal(), str));
    }

    public boolean setHTTPRequestContextValue(SteamHTTPRequestHandle steamHTTPRequestHandle, long j) {
        return SteamHTTPNative.setHTTPRequestContextValue(this.isServer, steamHTTPRequestHandle.handle, j);
    }

    public boolean setHTTPRequestNetworkActivityTimeout(SteamHTTPRequestHandle steamHTTPRequestHandle, int i) {
        return SteamHTTPNative.setHTTPRequestNetworkActivityTimeout(this.isServer, steamHTTPRequestHandle.handle, i);
    }

    public boolean setHTTPRequestHeaderValue(SteamHTTPRequestHandle steamHTTPRequestHandle, String str, String str2) {
        return SteamHTTPNative.setHTTPRequestHeaderValue(this.isServer, steamHTTPRequestHandle.handle, str, str2);
    }

    public boolean setHTTPRequestGetOrPostParameter(SteamHTTPRequestHandle steamHTTPRequestHandle, String str, String str2) {
        return SteamHTTPNative.setHTTPRequestGetOrPostParameter(this.isServer, steamHTTPRequestHandle.handle, str, str2);
    }

    public SteamAPICall sendHTTPRequest(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return new SteamAPICall(SteamHTTPNative.sendHTTPRequest(this.isServer, this.callback, steamHTTPRequestHandle.handle));
    }

    public SteamAPICall sendHTTPRequestAndStreamResponse(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return new SteamAPICall(SteamHTTPNative.sendHTTPRequestAndStreamResponse(this.isServer, steamHTTPRequestHandle.handle));
    }

    public int getHTTPResponseHeaderSize(SteamHTTPRequestHandle steamHTTPRequestHandle, String str) {
        return SteamHTTPNative.getHTTPResponseHeaderSize(this.isServer, steamHTTPRequestHandle.handle, str);
    }

    public boolean getHTTPResponseHeaderValue(SteamHTTPRequestHandle steamHTTPRequestHandle, String str, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamHTTPNative.getHTTPResponseHeaderValue(this.isServer, steamHTTPRequestHandle.handle, str, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public int getHTTPResponseBodySize(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return SteamHTTPNative.getHTTPResponseBodySize(this.isServer, steamHTTPRequestHandle.handle);
    }

    public boolean getHTTPResponseBodyData(SteamHTTPRequestHandle steamHTTPRequestHandle, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamHTTPNative.getHTTPResponseBodyData(this.isServer, steamHTTPRequestHandle.handle, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public boolean getHTTPStreamingResponseBodyData(SteamHTTPRequestHandle steamHTTPRequestHandle, int i, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamHTTPNative.getHTTPStreamingResponseBodyData(this.isServer, steamHTTPRequestHandle.handle, i, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public boolean releaseHTTPRequest(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return SteamHTTPNative.releaseHTTPRequest(this.isServer, steamHTTPRequestHandle.handle);
    }
}
