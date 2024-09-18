import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthChecker {

    // Method to check the strength of the password
    public static String checkPasswordStrength(String password) {
        // Criteria flags
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Check the length of the password
        if (password.length() < 8) {
            return "Password is too short. It should be at least 8 characters.";
        }

        // Regular expression to find special characters
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*()\\-+_<>?]");
        Matcher specialCharMatcher = specialCharPattern.matcher(password);

        // Loop through each character in the password and set the flags
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (specialCharMatcher.find()) {
                hasSpecial = true;
            }
        }

        // Provide feedback based on the flags
        if (!hasUpper) {
            return "Password should contain at least one uppercase letter.";
        }
        if (!hasLower) {
            return "Password should contain at least one lowercase letter.";
        }
        if (!hasDigit) {
            return "Password should contain at least one digit.";
        }
        if (!hasSpecial) {
            return "Password should contain at least one special character (!@#$%^&*()_+-<>?).";
        }

        // If all conditions are satisfied, the password is strong
        return "Password is strong.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a password
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Check the password strength
        String result = checkPasswordStrength(password);

        // Display the result
        System.out.println(result);

        scanner.close();
    }
}

