import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    /**
     * Prints hangman according to how many lives remain
     * @param lives integer representing number of lives player has
     */
    private static void drawHangman(int lives){
        String hangman = "";

        // Create hangman according to number of lives
        switch (lives){
            case 1 -> hangman = """
                     _______
                    |/      |
                    |      (_)
                    |
                    |
                    |
                    |
                 ___|___""";
            case 2 -> hangman = """
                     _______
                    |/      |
                    |      (_)
                    |      \\|
                    |
                    |
                    |
                 ___|___""";
            case 3 -> hangman = """
                     _______
                    |/      |
                    |      (_)
                    |      \\|/
                    |
                    |
                    |
                 ___|___""";
            case 4 -> hangman = """
                     _______
                    |/      |
                    |      (_)
                    |      \\|/
                    |       |
                    |
                    |
                 ___|___""";
            case 5 -> hangman = """
                     _______
                    |/      |
                    |      (_)
                    |      \\|/
                    |       |
                    |      /
                    |
                 ___|___""";
            case 6 -> hangman = """
                     _______
                    |/      |
                    |      (_)
                    |      \\|/
                    |       |
                    |      / \\
                    |
                 ___|___""";
        }

        // Print hangman to terminal
        System.out.println(hangman);
    }

    /**
     * Generates random word of length greater than 3
     * @return String word
     */
    private static String getWord() throws FileNotFoundException {
        // Create scanner for words list txt file
        Scanner fileReader = new Scanner(new File("words_alpha.txt"));
        // Create arraylist for words
        List<String> wordList = new ArrayList<>();

        // Input words into arrayList
        while (fileReader.hasNext()){
            wordList.add(fileReader.nextLine());
        }

        // Retrieve random word from wordList
        Random r = new Random();
        String word = wordList.get(r.nextInt(wordList.size()));

        // If word length is greater than 3, return word, if not, generate new word
        while (word.length() < 3){
            r = new Random();
            word = wordList.get(r.nextInt(wordList.size()));
        }

        return word;
    }

    /**
     * Creates the hangman board, with certain letters hidden/revealed depending on users guesses
     * @param guesses ArrayList of chars, containing user's guesses
     * @param word String, the word the user is attempting to guess
     * @return StringBuilder containing the completed board at current game stage
     */
    private static StringBuilder drawBoard(ArrayList<Character> guesses, String word){
        StringBuilder board = new StringBuilder("");

        // Loop through each char in word
        for (int i = 0; i < word.length(); i++){
            char wordLetter = word.charAt(i);

            // If user has guessed char, reveal letter in board
            if (guesses.contains(wordLetter)){
                board.append(wordLetter).append(" ");
            }
            // If user has not guessed char, keep letter hidden as an underscore
            else
                board.append("_").append(" ");

        }

        // Return final board
        return board;
    }

    /**
     * Gets new character guess from user
     * @param guesses Arraylist of chars user has guessed during game
     * @return updated list of guesses
     */
    private static ArrayList<Character> getPlay(ArrayList<Character> guesses){
        // Print previous guesses
        System.out.println("Previous guesses: " + guesses);

        // Get next guess input
        System.out.println("Enter new guess: ");
        Scanner play = new Scanner(System.in);

        char newGuess = play.nextLine().charAt(0);

        // Ensure guess has not already been guessed
        while (guesses.contains(newGuess)){
            System.out.println("Enter new guess: ");
            newGuess = play.nextLine().charAt(0);
        }

        // Update guess list
        guesses.add(newGuess);

        return guesses;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Print title screen
        titleScreen();
        // Get input from user
        Scanner user = new Scanner(System.in);
        String userSelection = user.nextLine();

        while (!Objects.equals(userSelection, "X")){
            // If input is 0, start game
            if (userSelection.equals("0")){
                // Set lives to full
                int lives = 6;
                // Create empty guess list
                ArrayList<Character> guesses;
                guesses = new ArrayList<>();
                // Draw hangman
                drawHangman(lives);
                // Select word
                String word = getWord();
                // Draw board
                StringBuilder board = drawBoard(guesses, word);
                System.out.println(board);

                // User makes play
                getPlay(guesses);

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
