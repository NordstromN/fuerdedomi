package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import controller.TicTacToeController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TicTacToeModelClient;
import model.TicTacToeModelInterface;
import view.TicTacToeView;

public class TTTClient extends Application {

	TicTacToeModelInterface model;
	TicTacToeView view;
	TicTacToeController controller;

	// Final Int für das Spielfeld
	public static final int BOARD = 3;

	// Spieler zuordnung
	public final static int PLAYER_X = 0;
	public final static int PLAYER_O = 1;

	// Create a stage so start method will be available from another class
	public static Stage primarySt = new Stage();

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		// Now adding resp. creating and initializing the MVC components
		model = new TicTacToeModelClient();
		view = new TicTacToeView(primaryStage, model);
		controller = new TicTacToeController(model, view);
	}

	public static void main_old(String[] args) {

		//Client Socket, connect to server on localhost whith the port 50500	
		try (Socket socket = new Socket("127.0.0.1", 50500)) {
			
			// Setting a 5sek timeout window
			socket.setSoTimeout(5000);
			BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

			Scanner scan = new Scanner(System.in);
			String echoString;
			String response;

			do {
				System.out.println("Enter String to be Echoed: ");
				echoString = scan.nextLine();

				stringToEcho.println(echoString);
				if (!echoString.equals("exit")) {
					response = echoes.readLine();
					System.out.println(response);
				}

			} while (!echoString.equals("exit"));
		} catch (SocketTimeoutException e) {
			System.out.println("The Socket has timed out...");
		} catch (IOException e) {
			System.out.println("Client Error: " + e.getMessage());
		}
	}

}
