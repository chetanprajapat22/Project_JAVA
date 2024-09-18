import java.util.Scanner;

public class TemperatureConverter {

    // Convert Celsius to Fahrenheit
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // Convert Fahrenheit to Celsius
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter temperature value
        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();

        // Prompt user to enter unit of measurement (C for Celsius, F for Fahrenheit)
        System.out.print("Is the temperature in (C)elsius or (F)ahrenheit? ");
        char unit = scanner.next().toUpperCase().charAt(0); // Convert input to uppercase for easy comparison

        // Perform the conversion based on the input unit
        if (unit == 'C') {
            double convertedTemp = celsiusToFahrenheit(temperature);
            System.out.printf("%.2f°C is equal to %.2f°F.\n", temperature, convertedTemp);
        } else if (unit == 'F') {
            double convertedTemp = fahrenheitToCelsius(temperature);
            System.out.printf("%.2f°F is equal to %.2f°C.\n", temperature, convertedTemp);
        } else {
            System.out.println("Invalid input. Please enter 'C' for Celsius or 'F' for Fahrenheit.");
        }

        scanner.close();
    }
}

