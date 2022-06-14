import java.util.Objects;
import java.util.Scanner;

public class Hangman {

    /**
     * Prints out starting game screen and menu
     */
    public static void titleScreen(){
        System.out.println("""
                 .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .-----------------.
                | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
                | |  ____  ____  | || |      __      | || | ____  _____  | || |    ______    | || | ____    ____ | || |      __      | || | ____  _____  | |
                | | |_   ||   _| | || |     /  \\     | || ||_   \\|_   _| | || |  .' ___  |   | || ||_   \\  /   _|| || |     /  \\     | || ||_   \\|_   _| | |
                | |   | |__| |   | || |    / /\\ \\    | || |  |   \\ | |   | || | / .'   \\_|   | || |  |   \\/   |  | || |    / /\\ \\    | || |  |   \\ | |   | |
                | |   |  __  |   | || |   / ____ \\   | || |  | |\\ \\| |   | || | | |    ____  | || |  | |\\  /| |  | || |   / ____ \\   | || |  | |\\ \\| |   | |
                | |  _| |  | |_  | || | _/ /    \\ \\_ | || | _| |_\\   |_  | || | \\ `.___]  _| | || | _| |_\\/_| |_ | || | _/ /    \\ \\_ | || | _| |_\\   |_  | |
                | | |____||____| | || ||____|  |____|| || ||_____|\\____| | || |  `._____.'   | || ||_____||_____|| || ||____|  |____|| || ||_____|\\____| | |
                | |              | || |              | || |              | || |              | || |              | || |              | || |              | |
                | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
                 '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\s
                 
                 Press 0 to begin, or X to exit game""");
    }

    public static void main(String[] args){
        // Print title screen
        titleScreen();
        // Get input from user
        Scanner user = new Scanner(System.in);
        String userSelection = user.nextLine();

        while (!Objects.equals(userSelection, "X")){
            // If input is 0, start game
            if (userSelection.equals("0")){
                System.out.println("Start game");
            }
            // If input is invalid (not 0 or X), print message requesting proper input
            else{
                System.out.println("Please enter either 0 or X");
            }

            // Get input from user
            userSelection = user.nextLine();
        }

        user.close();
    }

}
