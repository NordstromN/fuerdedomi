package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


public class LeftView extends VBox {
	//Label playerName = new Label("Set Your Name:");
//	TextField playerN = new TextField();
	//Button setName = new Button("Confirm Name");
	Button newRound = new Button("New Round");
	
	Label whoWon = new Label();
		
	public LeftView() {
		super();  //Der Super-Constructor wird zuerst aufgerufen!
	
		
		//setName.setId("button2");
	//	playerName.setId("label");
		whoWon.setId("label");
		newRound.setId("button2");
		
		this.setMinSize(200, 200);
		this.setPadding(new Insets(175, 25, 25, 25));
		this.setSpacing(10);
		this.setAlignment(Pos.BASELINE_LEFT);
		this.getChildren().addAll(newRound, whoWon);
	
		//Hover effekt wenn maus auf dem knopf ist
		DropShadow shadow = new DropShadow();
			newRound.addEventHandler(MouseEvent.MOUSE_ENTERED, 
				    new EventHandler<MouseEvent>() {
		         public void handle(MouseEvent e) {
		            newRound.setEffect(shadow);
		        }
		});
		//Entfernt den hover effekt wenn maus nicht auf dem knopf ist
			newRound.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		         public void handle(MouseEvent e) {
		        	newRound.setEffect(null);
		        }
		});
			}
	
	public void setLabel(String win) {
		whoWon.setText(win);
	}
	

}

