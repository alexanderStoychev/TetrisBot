import java.awt.Color;

/**
An interface holding the settings for the PentriX game
@author Silvia Fallone
@version 0.38, 19 Oct 2017
*/
public interface PInfo {

	//Running setting
	int INITIAL_DELAY = 750;
	int MULT_DELAY = 15;
	String GAME_NAME = "PentriX";
	int WINDOW_HEIGHT = 800;
	int WINDOW_WIDTH = 700;
	Color BACKGROUND = new Color(34, 78, 103);
	String FILENAME = "leaderboard.txt";
	int LEADERS = 5;
	int LIMIT_LINES = 5;

	//Bot Settings
	//double[] BOT_WEIGHTS = new double[]{-2/7, -2/7, -2/7, -2/7};
	double[] BOT_WEIGHTS = new double[]{-1, -1, -0.5, -1};
	//Pentomino Names
	int NO_PENTO = -1;
	int PENTO_P1 = 0;
	int PENTO_P2 = 1;
	int PENTO_X = 2;
	int PENTO_F1 = 3;
	int PENTO_F2 = 4;
	int PENTO_V = 5;
	int PENTO_W = 6;
	int PENTO_Y1 = 7;
	int PENTO_Y2 = 8;
	int PENTO_I = 9;
	int PENTO_T = 10;
	int PENTO_Z1 = 11;
	int PENTO_Z2 = 12;
	int PENTO_U = 13;
	int PENTO_N1 = 14;
	int PENTO_N2 = 15;
	int PENTO_L1 = 16;
	int PENTO_L2 = 17;

	int[] PENTO_NAMES = {PENTO_P1, PENTO_P2, PENTO_X, PENTO_F1, PENTO_F2, PENTO_V, PENTO_W, PENTO_Y1, PENTO_Y2, PENTO_I, PENTO_T, PENTO_Z1, PENTO_Z2, PENTO_U, PENTO_N1, PENTO_N2, PENTO_L1, PENTO_L2, NO_PENTO};

	//Pentomino Colors
	Color COLOR_PENTO_P1 = Color.BLUE;
	Color COLOR_PENTO_P2 = Color.RED;
	Color COLOR_PENTO_X = Color.GREEN;
	Color COLOR_PENTO_F1 = Color.YELLOW;
	Color COLOR_PENTO_F2 = new Color(37, 42, 14);
	Color COLOR_PENTO_V = Color.MAGENTA;
	Color COLOR_PENTO_W = Color.CYAN;
	Color COLOR_PENTO_Y1 = Color.ORANGE;
	Color COLOR_PENTO_Y2 = Color.PINK;
	Color COLOR_PENTO_I = Color.WHITE;
	Color COLOR_PENTO_T = new Color(188, 66, 244);
	Color COLOR_PENTO_Z1 = new Color(66, 244, 238);
	Color COLOR_PENTO_Z2 = new Color(55, 165, 24);
	Color COLOR_PENTO_U = new Color(175, 31, 31);
	Color COLOR_PENTO_N1 = new Color(5, 146, 168);
	Color COLOR_PENTO_N2 = new Color(2, 255, 217);
	Color COLOR_PENTO_L1 = new Color(155, 9, 145);
	Color COLOR_PENTO_L2 = new Color(51, 51, 51);

	Color[] COLOR_PENTOES = {COLOR_PENTO_P1, COLOR_PENTO_P2, COLOR_PENTO_X, COLOR_PENTO_F1, COLOR_PENTO_F2, COLOR_PENTO_V, COLOR_PENTO_W, COLOR_PENTO_Y1, COLOR_PENTO_Y2, COLOR_PENTO_I, COLOR_PENTO_T, COLOR_PENTO_Z1, COLOR_PENTO_Z2, COLOR_PENTO_U, COLOR_PENTO_N1, COLOR_PENTO_N2, COLOR_PENTO_L1, COLOR_PENTO_L2};

	//Number settings
	int QUANTITY_PENTOES = COLOR_PENTOES.length;

	//Board Settings
	int ROWS = 15;
	int COLUMNS = 7;

	int MINO_WIDTH = 30;
	int MINO_HEIGHT = 30;

	int STARTING_X = COLUMNS%2 == 0 ? COLUMNS/2 - 1 : Math.round(COLUMNS/2);

	int BOARD_WIDTH = COLUMNS * MINO_WIDTH;
	int BOARD_HEIGHT = ROWS * MINO_HEIGHT;

}
