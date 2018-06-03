public class Main {

	public static void main(String[] args) {
		Game GuessTheMovie = new Game();
		while (!GuessTheMovie.gameOver()) {
			GuessTheMovie.playGame();
		}
		GuessTheMovie.checkScore();
	}
}
