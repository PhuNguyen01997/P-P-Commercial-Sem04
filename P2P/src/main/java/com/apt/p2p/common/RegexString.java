package com.apt.p2p.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexString {
    /**
     * get name from url (facebook or google) if login other local
     *
     * @param url
     * @return
     */
    public static String replaceUrl(String url) {
        String from = null;
        int indexCharacter = 0;
        int lengthUrl = url.length();
        String character = "/";
        Pattern pattern = Pattern.compile(character);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            indexCharacter = matcher.end();
            from = url.substring(indexCharacter , lengthUrl);

        }
        return from;

    }
}
