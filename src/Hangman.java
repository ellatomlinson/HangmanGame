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
                 
                 Press 0 to begin, or any key to exit game""");
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
     * @return char guessed by user
     */
    private static char getPlay(ArrayList<Character> guesses){
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

        return newGuess;
    }

    /**
     * Checks if users guess appears in the word
     * @param newGuess Character guess
     * @param lives Number of lives user has remaining
     * @param word Word user is attempting to guess
     * @return updated lives count
     */
    private static int checkGuess(char newGuess, int lives, String word){
        int guessAppearance = 0;

        // Check how many times user guess appears in the word
        for (int i = 0; i < word.length(); i++){
                if (word.charAt(i) == newGuess){
                    guessAppearance ++;
                }
        }

        // If guess never appears, subtract a life
        if (guessAppearance == 0){
            lives -= 1;
        }

        return lives;

    }

    private static void lossScreen(String word){
        System.out.println("""
                ▀▄    ▄ ████▄   ▄       █    ████▄    ▄▄▄▄▄   ▄███▄     ▄\s
                  █  █  █   █    █      █    █   █   █     ▀▄ █▀   ▀   █ \s
                   ▀█   █   █ █   █     █    █   █ ▄  ▀▀▀▀▄   ██▄▄    █  \s
                   █    ▀████ █   █     ███▄ ▀████  ▀▄▄▄▄▀    █▄   ▄▀ █  \s
                 ▄▀           █▄ ▄█         ▀                 ▀███▀      \s
                               ▀▀▀                                    ▀  \s
                                                                        \s
                
                """);
        System.out.println("The word was: " + word);
    }

    /**
     * Checks if user has won
     * @param board StringBuilder containing the completed board at current game stage
     * @return boolean value of whether user has won or not
     */
    private static boolean checkWin(StringBuilder board){
        boolean win = true;

        for (int i = 0; i < board.length(); i++){
            char currentChar = board.charAt(i);
            String currentLetter = Character.toString(currentChar);

            if (currentLetter.equals("_")){
                win = false;
            }

        }

        if (win){
            System.out.println("""
                    ____    ____  ______    __    __     ____    __    ____  __  .__   __.  __ \s
                    \\   \\  /   / /  __  \\  |  |  |  |    \\   \\  /  \\  /   / |  | |  \\ |  | |  |\s
                     \\   \\/   / |  |  |  | |  |  |  |     \\   \\/    \\/   /  |  | |   \\|  | |  |\s
                      \\_    _/  |  |  |  | |  |  |  |      \\            /   |  | |  . `  | |  |\s
                        |  |    |  `--'  | |  `--'  |       \\    /\\    /    |  | |  |\\   | |__|\s
                        |__|     \\______/   \\______/         \\__/  \\__/     |__| |__| \\__| (__)\s
                                                                                               \s""");
        }

        return win;
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean lose = false;
        boolean win = false;

        // Print title screen
        titleScreen();
        // Get input from user
        Scanner user = new Scanner(System.in);
        String userSelection = user.nextLine();

        // If input is 0, start game
        while (userSelection.equals("0")){
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
            char newGuess = getPlay(guesses);
            // Update life counter according to guess correctness
            lives = checkGuess(newGuess, lives, word);

            while ((!lose) && (!win)){
                // Draw hangman
                drawHangman(lives);

                // Draw board
                board = drawBoard(guesses, word);
                System.out.println(board);

                // User makes play
                newGuess = getPlay(guesses);
                // Update life counter according to guess correctness
                lives = checkGuess(newGuess, lives, word);

                // If lives are equal to 0, play lose screen
                if (lives == 0){
                    lossScreen(word);
                    lose = true;
                }

                // Check for win
                board = drawBoard(guesses, word);
                win = checkWin(board);
            }

            // Get new input
            System.out.println("Press 0 to play again, or any key to exit");
            userSelection = user.nextLine();
        }

        user.close();
    }

}
