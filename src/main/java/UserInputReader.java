import java.util.Scanner;

public class UserInputReader {
    /**
     * Gets the string input from the terminal passed by the user.
     *
     * @param attribute the magazine attribute to be presented in the error message to the user
     * @return the string input from the terminal passed by the user
     */
    public String getStringFromUserInput(String attribute) {
        Scanner reader = new Scanner(System.in);
        boolean quit = false;
        String input = null;

        while (!quit) {
            input = reader.nextLine();
            if (!isValidString(input)) {
                System.out.println("Please enter a valid " + attribute + ":");
            } else {
                quit = true;
            }
        }
        return input;
    }


    /**
     * Validates a string passed as parameter to ensure it is not null or empty.
     *
     * @param string the string to be validated
     * @return true if the string passed as parameter is valid, otherwise false is returned.
     */
    private boolean isValidString(String string) {
        boolean valid = false;
        if (!(string == null) && !(string.trim().isEmpty())) {
            valid = true;
        }
        return valid;
    }
}
