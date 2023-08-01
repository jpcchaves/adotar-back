package com.jpcchaves.adotar.utils.base64;

import java.util.regex.Pattern;

public class Base64Utils {
    public static boolean isValidBase64(String base64String) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+/]*(=|==)?$");
        return pattern.matcher(base64String).matches();
    }

    public static String removeBase64Prefix(String base64String) {
        return base64String.replaceAll("^data:[a-z]+/[a-z]+;base64,", "");
    }

    public static boolean hasBase64Prefix(String base64String) {
        Pattern pattern = Pattern.compile("^data:image/[a-z]+;base64,");
        return pattern.matcher(base64String).find();
    }
}
