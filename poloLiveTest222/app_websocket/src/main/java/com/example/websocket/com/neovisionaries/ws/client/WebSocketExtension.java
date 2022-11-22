package com.example.websocket.com.neovisionaries.ws.client;


import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class WebSocketExtension {
    public static final String PERMESSAGE_DEFLATE = "permessage-deflate";
    private final String mName;
    private final Map<String, String> mParameters;

    public void validate() throws WebSocketException {
    }

    public WebSocketExtension(String str) {
        if (Token.isValid(str)) {
            this.mName = str;
            this.mParameters = new LinkedHashMap();
            return;
        }
        throw new IllegalArgumentException("'name' is not a valid token.");
    }

    public WebSocketExtension(WebSocketExtension webSocketExtension) {
        if (webSocketExtension != null) {
            this.mName = webSocketExtension.getName();
            this.mParameters = new LinkedHashMap(webSocketExtension.getParameters());
            return;
        }
        throw new IllegalArgumentException("'source' is null.");
    }

    public String getName() {
        return this.mName;
    }

    public Map<String, String> getParameters() {
        return this.mParameters;
    }

    public boolean containsParameter(String str) {
        return this.mParameters.containsKey(str);
    }

    public String getParameter(String str) {
        return this.mParameters.get(str);
    }

    public WebSocketExtension setParameter(String str, String str2) {
        if (!Token.isValid(str)) {
            throw new IllegalArgumentException("'key' is not a valid token.");
        } else if (str2 == null || Token.isValid(str2)) {
            this.mParameters.put(str, str2);
            return this;
        } else {
            throw new IllegalArgumentException("'value' is not a valid token.");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.mName);
        for (Map.Entry<String, String> entry : this.mParameters.entrySet()) {
            sb.append("; ");
            sb.append(entry.getKey());
            String value = entry.getValue();
            if (!(value == null || value.length() == 0)) {
                sb.append("=");
                sb.append(value);
            }
        }
        return sb.toString();
    }

    public static WebSocketExtension parse(String str) {
        String extractValue;
        if (str == null) {
            return null;
        }
        String[] split = str.trim().split("\\s*;\\s*");
        if (split.length == 0) {
            return null;
        }
        String str2 = split[0];
        if (!Token.isValid(str2)) {
            return null;
        }
        WebSocketExtension createInstance = createInstance(str2);
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].split("\\s*=\\s*", 2);
            if (!(split2.length == 0 || split2[0].length() == 0)) {
                String str3 = split2[0];
                if (Token.isValid(str3) && ((extractValue = extractValue(split2)) == null || Token.isValid(extractValue))) {
                    createInstance.setParameter(str3, extractValue);
                }
            }
        }
        return createInstance;
    }

    private static String extractValue(String[] strArr) {
        if (strArr.length != 2) {
            return null;
        }
        return Token.unquote(strArr[1]);
    }

    private static WebSocketExtension createInstance(String str) {
        if (PERMESSAGE_DEFLATE.equals(str)) {
            return new PerMessageDeflateExtension(str);
        }
        return new WebSocketExtension(str);
    }
}
