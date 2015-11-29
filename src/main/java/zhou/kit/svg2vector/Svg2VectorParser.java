package zhou.kit.svg2vector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhou on 15-11-29.
 */
public class Svg2VectorParser {

    private static final String template = "<vector xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "        android:width=\"24dp\"\n" +
            "        android:height=\"24dp\"\n" +
            "        android:viewportWidth=\"24.0\"\n" +
            "        android:viewportHeight=\"24.0\">\n" +
            "    <path\n" +
            "        android:fillColor=\"#FF000000\"\n" +
            "        android:pathData=\"%s\"/>\n" +
            "</vector>";

    private static final String t = "<path d=\"(.+?)\"/>";
    private static final Pattern PATTERN = Pattern.compile(t);

    public static String parse(String svg) {
        Matcher matcher = PATTERN.matcher(svg);
        if (matcher.find()) {
            return String.format(template, matcher.group(1));
        }
        return null;
    }

}
