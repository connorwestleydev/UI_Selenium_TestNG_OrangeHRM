package utils;

import java.util.Random;

public class RandomVariables {

    /**
     * Generates a random string consisting of alphanumeric characters and
     * the following special characaters [space],!,#,@
     * @return
     */
    public static String getRandomString() {
        return new Random().ints(1, 128)
                .filter(i -> String.valueOf((char) i).matches("[a-zA-Z !#@]"))
                .limit(40)
                .mapToObj(i -> (char) i)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
