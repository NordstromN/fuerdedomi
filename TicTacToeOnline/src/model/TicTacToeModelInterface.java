package model;

public interface TicTacToeModelInterface {

	// Setzten einees neuen Brett/Spiel
	void setBoard();

	// Kontrolle ob es ein Gewinner gibt mit den drei Methoden
	boolean winCheck();

	// Wechseln des Spielers
	char changeToken();

	char setMark();
	
	char getMark(int row, int col);

	boolean placeMark(int row, int col);
	}