package com.slzhibo.library.analytics.util;

public class JSONUtils {
    private static void addIndentBlank(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            try {
                sb.append('\t');
            } catch (Exception unused) {
                return;
            }
        }
    }

    public static String formatJson(String str) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    char c = 0;
                    boolean z = false;
                    int i2 = 0;
                    while (i < str.length()) {
                        char charAt = str.charAt(i);
                        if (charAt == '\"') {
                            if (c != '\\') {
                                z = !z;
                            }
                            sb.append(charAt);
                        } else if (charAt != ',') {
                            if (charAt != '{') {
                                if (charAt != '}') {
                                    switch (charAt) {
                                        case '[':
                                            break;
                                        case '\\':
                                            break;
                                        case ']':
                                            break;
                                        default:
                                            sb.append(charAt);
                                            break;
                                    }
                                }
                                if (!z) {
                                    sb.append('\n');
                                    i2--;
                                    addIndentBlank(sb, i2);
                                }
                                sb.append(charAt);
                            }
                            sb.append(charAt);
                            if (!z) {
                                sb.append('\n');
                                i2++;
                                addIndentBlank(sb, i2);
                            }
                        } else {
                            sb.append(charAt);
                            if (c != '\\' && !z) {
                                sb.append('\n');
                                addIndentBlank(sb, i2);
                            }
                        }
                        i++;
                        c = charAt;
                    }
                    return sb.toString();
                }
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
