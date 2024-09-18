import java.util.Random;
import java.util.Scanner;

public class RandomPasswordGenerator {

    // Characters for password generation
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for password length
        System.out.print("Enter the desired length of the password: ");
        int length = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Ask the user for password content preferences
        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecial = scanner.nextLine().equalsIgnoreCase("yes");

        // Check if at least one type of character is selected
        if (!includeLowercase && !includeUppercase && !includeNumbers && !includeSpecial) {
            System.out.println("You must select at least one character type.");
            return;
        }

        // Generate the password
        String password = generatePassword(length, includeLowercase, includeUppercase, includeNumbers, includeSpecial);

        // Display the generated password
        System.out.println("Generated password: " + password);

        scanner.close();
    }

    // Method to generate a random password based on user preferences
    public static String generatePassword(int length, boolean includeLowercase, boolean includeUppercase,
                                          boolean includeNumbers, boolean includeSpecial) {
        StringBuilder allowedCharacters = new StringBuilder();
        Random random = new Random();

        // Build the pool of allowed characters based on the user's choices
        if (includeLowercase) {
            allowedCharacters.append(LOWERCASE);
        }
        if (includeUppercase) {
            allowedCharacters.append(UPPERCASE);
        }
        if (includeNumbers) {
            allowedCharacters.append(DIGITS);
        }
        if (includeSpecial) {
            allowedCharacters.append(SPECIAL_CHARACTERS);
        }

        // Generate the password
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            password.append(allowedCharacters.charAt(randomIndex));
        }

        return password.toString();
    }
}
