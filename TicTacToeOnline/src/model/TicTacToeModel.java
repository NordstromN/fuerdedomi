package model;

public class TicTacToeModel implements TicTacToeModelInterface {

	private char[][] board;
	public char player1Token;

	// Konstruktor setzt neues Spiel auf
	public TicTacToeModel() {
		setBoard();
	}

	// Setzten einees neuen Brett/Spiel
	public void setBoard() {
		board = new char[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
	}

	// Kontrolle ob es ein Gewinner gibt mit den drei Methoden
	public boolean winCheck() {
		return (rowWinCheck() || colWinCheck() || diagWinCheck());
	}

	// Kontrolle ob eine Reihe alle drei Char gleich hat
	private boolean rowWinCheck() {
		for (int row = 0; row < 3; row++) {
			if (checkSameMark(board[row][0], board[row][1], board[row][2]) == true) {
				return true;
			}
		}
		return false;
	}

	// Kontrolle ob eine Kolumnte alle drei Char gleich hat
	private boolean colWinCheck() {
		for (int col = 0; col < 3; col++) {
			if (checkSameMark(board[0][col], board[1][col], board[2][col]) == true) {
				return true;
			}
		}
		return false;
	}

	// Kontrolle der beiden Diagonalen, obe diese gleich sind
	private boolean diagWinCheck() {
		return ((checkSameMark(board[0][0], board[1][1], board[2][2]) == true)
				|| (checkSameMark(board[0][2], board[1][1], board[2][0]) == true));
	}

	// Kontrolle ob alle drei Markierungen im Brett gleich sind
	private boolean checkSameMark(char c1, char c2, char c3) {
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}

	// Wechseln des Spielers
	public char changeToken() {
		if (player1Token == 'x') {
			return player1Token = 'o';
		} else {
			return player1Token = 'x';
		}
	}

	//Setztn und wechseln der Tokens für den nächsten Spieler
	public char setMark() {
		changeToken();
		return player1Token;
	}
	
	@Override
	public char getMark(int row, int col) {
		return board[row][col];
	}

	//PlayeMark kontrolliert ob das Feld besetzt ist oder nicht
	//wenn nicht, wird das Mark gesetzte
	public boolean placeMark(int row, int col) {
		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = player1Token;
					return true;
				}
			}
		}
		return false;
	}
	
}