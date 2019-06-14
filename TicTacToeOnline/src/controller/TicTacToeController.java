package controller;

import main.TTTClient;
import model.TicTacToeModelInterface;
import view.TicTacToeView;

public class TicTacToeController {
	private TicTacToeModelInterface model;
	private TicTacToeView view;
	private TTTClient start = new TTTClient();

	// Controller Konstruktor instanziiert das model interface und die view
	// Anschliessend werden die Buttons im Feld gesetzt
	public TicTacToeController(TicTacToeModelInterface model, TicTacToeView view) {
		this.model = model;
		this.view = view;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int r = row;
				int c = col;
				view.getButt(r, c).setOnAction(e -> setToken(r, c));
			}
		}
		view.getNewGame().setOnAction(e -> newGame());
		view.getNewRound().setOnAction(e -> newRound());

	}

	// Der Knopf startet eine neue Runde
	private void newRound() {
		view.setNewBoard();
		model.setBoard();
		view.setWinLabel("");

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int r = row;
				int c = col;
				view.getButt(r, c).setOnAction(e -> setToken(r, c));
			}
		}
	}

	// Setzt den char in das Board und check ob gewonnen
	private void setToken(int row, int col) {
		if (model.winCheck())
			return;

		if (!model.placeMark(row, col)) {
			view.setWinLabel("Wähle ein freies Feld");
		} else {
			if (model.winCheck()) {
				view.setButt(row, col, model.setMark());
				String win = view.getButt(row, col).getText();
				if (win.equals("x")) {
					win = "o";
				} else {
					win = "x";
				}
				view.setWinLabel("Der Gewinner ist " + win);
			} else {
					view.setButt(row, col, model.setMark());
				}
			}
		updateButt();
	}

	private void updateButt() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				view.setButt(row, col, model.getMark(row, col));
			}
		}
	}

	// Methode um neues Spiel zu starten
	private void newGame() {
		try {
			start.start(TTTClient.primarySt);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Message: " + e);
		}
	}

}
