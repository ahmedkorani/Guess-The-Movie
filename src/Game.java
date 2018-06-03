import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private Scanner cin = new Scanner(System.in);
	private String movieTitle = randomMovie(); // Movie title to guess written from random movie function
	private char movieTitleAsterisks[] = new char[movieTitle.length()];// Movie title in (*)
	private String wrongLetters = ""; // keep all wrong guessed letters
	private boolean isEntered[] = new boolean[movieTitle.length()];// Check if letter is entered or not
	private boolean isCorrect = false;// Check if letter is correct or not
	private int randomNumber = 1; // Random movie number
	private int spaces = 0;// Counts the spaces in the movieTitle
	private int movieLength ;// movieTitle length without spaces
	private int score = 10;// Guesses left after losing points

	Game() {
		movieEncrypted();
		movieLength = movieTitle.length()-spaces;
	}

	// @return a random number using math.random() function
	private int randomMovieNumber() {
		randomNumber = (int) (Math.random() * 25);
		return randomNumber;
	}

	// @returns random movie title
	private String randomMovie() {
		File moviesFile = new File("Movies.txt");
		Scanner movieScanner = null;
		try {
			movieScanner = new Scanner(moviesFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		ArrayList<String> movies = new ArrayList<String>();
		while (movieScanner.hasNextLine()) {
			String line = movieScanner.nextLine();
			movies.add(line);

		}
		movieScanner.close();
		return movies.get(randomMovieNumber());
	}

	//@returns movieTitle in (*)
	private void movieEncrypted() {
		for (int i = 0; i < movieTitle.length(); i++) {
			if (movieTitle.charAt(i) == ' ') {
				spaces++;
			} else {
				movieTitleAsterisks[i] = '*';
			}
		}
		System.out.println(movieTitleAsterisks);
	}
	
	//play game function to let user guess the movie
	void playGame() {
		System.out.println();
		System.out.print("Enter a letter to guess :");
		char c = cin.next().charAt(0);
		c = Character.toLowerCase(c);
		isCorrect = false;
		for (int i = 0; i < movieTitle.length(); i++) {
			if (movieTitle.charAt(i) == c && !isEntered[i]) {
				movieTitleAsterisks[i] = c;
				isEntered[i] = true;
				isCorrect = true;
				break;
			}
		}
		if (!isCorrect && enteredLetter(c)) {
			System.out.println("Letter Already Entered, Please try again");
		} else if (!isCorrect && !enteredLetter(c)) {
			score--;
			wrongLetters += c;
		} else
			movieLength--;

		for (int j = 0; j < movieTitleAsterisks.length; j++) {
			System.out.print(movieTitleAsterisks[j]);
		}
		System.out.println("\nLives left : " + score);
	}

	// @returns a boolean value if the char was already entered before
	private boolean enteredLetter(char c) {
		for (int i = 0; i < wrongLetters.length(); i++) {
			if (c == wrongLetters.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	// check game state
	boolean gameOver() {
		if (score >= 1 && movieLength != 0)
			return false;
		return true;
	}

	void checkScore() {
		if (score >= 1)
			System.out.println("You won congrats");
		else
			System.out.println("You lost! good luck next time, Movie was : " + movieTitle);
	}

}
