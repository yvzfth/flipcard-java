package application;

import java.util.regex.Pattern;

public class SignupValidation {

    private static String regexPatternEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static String regexPatternUsername = "^[a-zA-Z0-9._-]{3,10}$";
    private static String regexPatternPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{6,12}$";

    /**
     * This regular expression is provided by the OWASP validation regex
     * repository
     * to check the email validation:
     * ^[a-zA-Z0-9_+&*-] + (?:\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\.) +
     * [a-zA-Z]{2, 7}$
     * This regex also supports the most validations in the standard email
     * structure.
     */
    public static boolean emailCheckSignup(String emailAddress) {
        return Pattern.compile(regexPatternEmail)
                .matcher(emailAddress)
                .matches();
    }

    /**
     * ^[a-zA-Z0-9._-]{3,}$
     * 10 >= Length >=3
     * Valid characters: a-z, A-Z, 0-9, points, dashes and underscores.
     */
    public static boolean usernameCheckSignup(String username) {
        return Pattern.compile(regexPatternUsername)
                .matcher(username)
                .matches();
    }

    /*
     * ^ # start-of-string
     * (?=.*[0-9]) # a digit must occur at least once
     * (?=.*[a-z]) # a lower case letter must occur at least once
     * (?=.*[A-Z]) # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=.]) # a special character must occur at least once
     * (?=\S+$) # no whitespace allowed in the entire string
     * .{6,12} # anything, at least 6 to 12 places though
     * $ # end-of-string
     */
    public static boolean passwordCheckSignup(String password) {
        return Pattern.compile(regexPatternPassword)
                .matcher(password)
                .matches();
    }

    /**
     * • You might be missing the username or domain part
     * • Email must have '@' character"
     */
    public static String getErrorMessageEmail() {
        return "• You might be missing the username or domain part\n" +
                "• Email must have '@' character";

    }

    /**
     * • Valid characters: a-z, A-Z, 0-9, points, dashes and underscores.
     * • Username must have 3 to 10 characters
     */
    public static String getErrorMessageUsername() {
        return "• Valid characters: a-z, A-Z, 0-9, points, dashes and underscores.\n" +
                "• Username must have 3 to 10 characters";

    }

    /**
     * • A digit must occur at least once,
     * • A lower case letter must occur at least once
     * • An upper case letter must occur at least once
     * • A special character must occur at least once (@#$%^&+=.)
     * • No whitespace allowed in the entire string
     * • Anything, at least 6 to 12 places though
     */
    public static String getErrorMessagePassword() {

        return "• A digit must occur at least once," +
                "\n• A lower case letter must occur at least once" +
                "\n• An upper case letter must occur at least once" +
                "\n• A special character must occur at least once (@#$%^&+=.)" +
                "\n• No whitespace allowed in the entire string" +
                "\n• Anything, at least 6 to 12 places though";

    }

}
