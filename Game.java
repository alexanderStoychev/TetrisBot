/**
Class to run the game
@author Antonio Rodríguez
@author Julián Marrades
@version 0.13, 20 Oct 2017
*/
public class Game implements PInfo {

	private static PCanvas canvas;
	private static Window frame;
	private static int RPS;
	private static int CLOCKS;

	public static void main(String[] args) {
		startGame();
	}

	/**
	Build the pieces required and start the game loop
	*/
	private static void startGame() {
		createWindow(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_NAME);
		iterateMainLoop();
	}

	/**
	Initialize the Window and the Canvas
	*/
	private static void createWindow(int width, int height, String title) {
		canvas = new PCanvas(width, height);
		frame = new Window(title, canvas);
	}

	/**
	Draw the next frame of the game and increase RPS by 1
	*/
	private static void refresh() {
		RPS++;
		canvas.draw();
	}

	/**
	Increase CLOCKS by 1
	*/
	private static void clock() {
		CLOCKS++;
	}

	private static void resetRPS() {
		RPS = 0;
		CLOCKS = 0;
	}

	/**
	Game loop
	*/
	private static void iterateMainLoop() {
		final int NS_PER_SECOND = 1000000000;
		final int RPS_OBJECTIVE = 60;
		final int NS_PER_FRAME = NS_PER_SECOND / RPS_OBJECTIVE;

		long referenceRefreshTime = System.nanoTime();
		//long referenceTimeCounter = System.nanoTime();

		double delta = 0;
		double timeWithoutProcessing;

		while (true) {
			long initialTime = System.nanoTime();
			timeWithoutProcessing = initialTime - referenceRefreshTime;
			referenceRefreshTime = initialTime;
			delta += timeWithoutProcessing / NS_PER_FRAME;

			while (delta >= 1) {
				delta--;
				refresh();
			}

			clock();

			/*
			if (System.nanoTime() - referenceTimeCounter >= NS_PER_SECOND) {
				System.out.println("RPS = " + RPS + ", CLOCKS = " + CLOCKS);
				referenceTimeCounter = System.nanoTime();
				resetRPS();
			}
			*/
		}
	}

}
