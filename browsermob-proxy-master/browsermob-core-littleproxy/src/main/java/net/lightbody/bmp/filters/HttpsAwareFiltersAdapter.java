package net.lightbody.bmp.filters;

import com.google.common.net.HostAndPort;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import net.lightbody.bmp.util.BrowserMobHttpUtil;
import org.littleshoot.proxy.HttpFiltersAdapter;

/**
 * The HttpsAwareFiltersAdapter exposes the original host and the "real" host (after filter modifications) to filters for HTTPS
 * requets. HTTPS requests do not normally contain the host in the URI, and the Host header may be missing or spoofed.
 * <p/>
 * <b>Note:</b> The {@link #getHttpsRequestHostAndPort()} and {@link #getHttpsOriginalRequestHostAndPort()} methods can only be
 * called when the request is an HTTPS request. Otherwise they will throw an IllegalStateException.
 */
public class HttpsAwareFiltersAdapter extends HttpFiltersAdapter {
    public static final String IS_HTTPS_ATTRIBUTE_NAME = "isHttps";
    public static final String HOST_ATTRIBUTE_NAME = "host";
    public static final String ORIGINAL_HOST_ATTRIBUTE_NAME = "originalHost";

    public HttpsAwareFiltersAdapter(HttpRequest originalRequest, ChannelHandlerContext ctx) {
        super(originalRequest, ctx);
    }

    /**
     * Returns true if this is an HTTPS request.
     *
     * @return true if https, false if http
     */
    public boolean isHttps() {
        Attribute<Boolean> isHttpsAttr = ctx.attr(AttributeKey.<Boolean>valueOf(IS_HTTPS_ATTRIBUTE_NAME));

        Boolean isHttps = isHttpsAttr.get();
        if (isHttps == null) {
            return false;
        } else {
            return isHttps;
        }
    }

    /**
     * Returns the host and port of this HTTPS request, including any modifications by other filters.
     *
     * @return host and port of this HTTPS request
     * @throws IllegalStateException if this is not an HTTPS request
     */
    public String getHttpsRequestHostAndPort() throws IllegalStateException {
        if (!isHttps()) {
            throw new IllegalStateException("Request is not HTTPS. Cannot get host and port on non-HTTPS request using this method.");
        }

        Attribute<String> hostnameAttr = ctx.attr(AttributeKey.<String>valueOf(HOST_ATTRIBUTE_NAME));
        return hostnameAttr.get();
    }

    /**
     * Returns the original host and port of this HTTPS request, as sent by the client. Does not reflect any modifications
     * by other filters.
     *
     * @return host and port of this HTTPS request
     * @throws IllegalStateException if this is not an HTTPS request
     */
    public String getHttpsOriginalRequestHostAndPort() throws IllegalStateException {
        if (!isHttps()) {
            throw new IllegalStateException("Request is not HTTPS. Cannot get original host and port on non-HTTPS request using this method.");
        }

        Attribute<String> hostnameAttr = ctx.attr(AttributeKey.<String>valueOf(ORIGINAL_HOST_ATTRIBUTE_NAME));
        return hostnameAttr.get();
    }

    /**
     * Returns the full, absolute URL of the specified request for both HTTP and HTTPS URLs. The request may reflect
     * modifications from this or other filters. This filter instance must be currently handling the specified request;
     * otherwise the results are undefined.
     *
     * @param modifiedRequest a possibly-modified version of the request currently being processed
     * @return the full URL of the request, including scheme, host, port, path, and query parameters
     */
    public String getFullUrl(HttpRequest modifiedRequest) {
        // To get the full URL, we need to retrieve the Scheme, Host + Port, Path, and Query Params from the request.
        // Scheme: the scheme (HTTP/HTTPS) may or may not be part of the request, and so must be generated based on the
        //   type of connection.
        // Host and Port: for HTTP requests, the host and port can be read from the request itself using the URI and/or
        //   Host header. for HTTPS requests, the host and port are not available in the request. by using the
        //   getHttpsRequestHostAndPort() helper method in HttpsAwareFiltersAdapter, we can capture the host and port for
        //   HTTPS requests.
        // Path + Query Params + Fragment: these elements are contained in the HTTP request
        String url;
        if (isHttps()) {
            String hostAndPort = getHttpsRequestHostAndPort();
            String path = BrowserMobHttpUtil.getPathFromRequest(modifiedRequest);
            url = "https://" + hostAndPort + path;
        } else {
            String hostAndPort = BrowserMobHttpUtil.getHostAndPortFromRequest(modifiedRequest);
            String path = BrowserMobHttpUtil.getPathFromRequest(modifiedRequest);
            url = "http://" + hostAndPort + path;
        }
        return url;
    }

    /**
     * Returns the full, absolute URL of the original request from the client for both HTTP and HTTPS URLs. The URL
     * will not reflect modifications from this or other filters.
     *
     * @return the full URL of the original request, including scheme, host, port, path, and query parameters
     */
    public String getOriginalUrl() {
        return getFullUrl(originalRequest);
    }

    /**
     * Returns the host and port of the specified request for both HTTP and HTTPS requests.  The request may reflect
     * modifications from this or other filters. This filter instance must be currently handling the specified request;
     * otherwise the results are undefined.
     *
     * @param modifiedRequest a possibly-modified version of the request currently being processed
     * @return host and port of the specified request
     */
    public String getHostAndPort(HttpRequest modifiedRequest) {
        String serverHost;
        if (isHttps()) {
            HostAndPort hostAndPort = HostAndPort.fromString(getHttpsRequestHostAndPort());
            serverHost = hostAndPort.getHostText();
        } else {
            serverHost = BrowserMobHttpUtil.getHostFromRequest(modifiedRequest);
        }
        return serverHost;
    }
}
