package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.TicTacToeModelInterface;

public class Board extends GridPane {
	private TicTacToeModelInterface model;

	Button[][] butt = new Button[3][3];

	// Der Konstruktor erstellt ein neues Spielfeld
	public Board() {
		newBoard();
	}

	// Methode zur grafischen Erstellung des Board
	public void newBoard() {

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				butt[row][col] = new Button("-");
				butt[row][col].setPrefSize(200, 200);
				this.add(butt[row][col], row, col);
			}
		}

		// Hover effekt wenn maus auf dem knopf ist
		DropShadow shadow = new DropShadow();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int r = row;
				int c = col;
				getButt(r, c).addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						getButt(r, c).setEffect(shadow);
					}
				});
				// Entfernt den hover effekt wenn maus nicht auf dem knopf
				getButt(r, c).addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						getButt(r, c).setEffect(null);
					}
				});
			}
		}
	}

	// Getter für die Button
	public Button getButt(int row, int col) {
		return butt[row][col];
	}

	// Setter für die Button auf dem Board
	public void setButt(int row, int col, char setMark) {
		butt[row][col].setText(String.valueOf(setMark));
	}

}
