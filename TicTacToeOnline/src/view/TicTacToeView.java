package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.TicTacToeModelInterface;

public class TicTacToeView {
	private TicTacToeModelInterface model;
	private Board board;
	private MenBar menuBar;
	private LeftView left;
	private BorderPane root;
	private Scene scene;

	// Setzte der Main Stage
	public TicTacToeView(Stage stage, TicTacToeModelInterface model) {
		this.model = model;

		board = new Board();
		menuBar = new MenBar();
		left = new LeftView();

		this.root = new BorderPane();

		// TopPane hinzufügen der MenuBar
		root.setTop(menuBar);
		// Center, setzten des Spielbretts
		root.setCenter(board);
		// Links setzten der Spiele Buttons und statistik
		root.setLeft(left);

		this.scene = new Scene(root);
		// Formatierung hinzufügen
		scene.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

		stage.setTitle("Tic Tac Toe");
		stage.setScene(scene);
		stage.show();

	}

	// getter für BoardButtons
	public Button[][] getButtons() {
		return board.butt;
	}

	// getter für den Button aus dem Board
	public Button getButt(int row, int col) {
		return board.butt[row][col];
	}

	public void setButt(int row, int col, char setMark) {
		board.setButt(row, col, setMark);
	}

	// Getter der Menu Buttons
	public MenuItem getNewGame() {
		return menuBar.newGame;
	}

	// Getter für den NewRoundButton
	public Button getNewRound() {
		this.setWinLabel(" ");
		return left.newRound;
	}

	public void setNewBoard() {
		board.newBoard();
	}
	//Setzten des Labels wenn ein Sieger feststeht
	public void setWinLabel(String win) {
				left.setLabel(win);
	}
}
