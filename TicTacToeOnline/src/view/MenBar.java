package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.TicTacToeModelInterface;

public class MenBar extends MenuBar {
	
	private TicTacToeModelInterface model;
	protected Menu game;
	protected MenuItem newGame, endGame;
	
	//create the constructor for the menuBar and the below items 
	
	public MenBar() {
		
	game = new Menu("Game");
	
	//Adding the Sub-Items to the Menu 
	newGame = new MenuItem("New Game");
	endGame = new MenuItem("End Application");
	//closebox method
	endGame.setOnAction((ActionEvent e) -> closeBox());
	//new game method
	newGame.setOnAction((ActionEvent e) -> model.setBoard());
	//add the items
	game.getItems().addAll(newGame, endGame);
	this.getMenus().add(game);
	}
	
	//Closing method including a display
	public void closeBox() {
		Platform.exit();
	}
	
}
