package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import model.TicTacToeModelServer;
import model.TicTacToeModel;

public class TTTServer {

	public static void main(String[] args) {
		TicTacToeModel model = new TicTacToeModel();
		
		try {
			// Server socket dem Port zugeordnet
			ServerSocket serverSocket = new ServerSocket(50500);
			
			while (true) {
				new TicTacToeModelServer(serverSocket.accept(), model).start();
			}

		} catch (IOException e) {
			System.out.println("Server Exception: " + e.getMessage());
		}
	}
}
