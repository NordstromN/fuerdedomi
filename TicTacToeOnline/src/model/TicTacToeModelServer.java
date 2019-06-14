package model;

import model.TicTacToeModelInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicTacToeModelServer extends Thread {
	private Socket socket;
	private TicTacToeModelInterface model;

	
	//Der ModelServer empfängt die Kommunikation vom Model Client und 
	//Antowrtet diesem
	public TicTacToeModelServer(Socket socket, TicTacToeModelInterface model) {
		System.out.println("New Model Server");
		this.socket = socket;
		this.model = model;
	}

	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			model.setBoard();			
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			
			//Endlosschleife, der Server "hört" immer
			while (true) {
				String echoString = input.readLine();
				
				//Echo um im CMD zu sehen was "reinkommt"
				System.out.println("Received Command: " + echoString);
				if (echoString.equals("exit")) {
					break;
				} else if (echoString.equals("setBoard")) {
					model.setBoard();
				//PlaceMark Methode liest die Row, Col aus dem String
				//und sagt der methode wo die Marker zu setzten sind
				} else if (echoString.substring(0, 2).equals("PM")) {
					int row = Integer.parseInt(echoString.substring(2, 3));
					int col = Integer.parseInt(echoString.substring(3, 4));
					if (model.placeMark(row, col)) {
						output.println("TRUE");
					} else {
						output.println("FALSE");
					}
				//Sagt server die winCheck Methode auszuführen
				} else if (echoString.equals("WC")) {
					if (model.winCheck()) {
						output.println("TRUE");
					} else {
						output.println("FALSE");
					}
				} else if (echoString.equals("SM")) {
					char mark = model.setMark();
					output.println(mark);
				} else if (echoString.equals("CT")) {
					char mark = model.changeToken();
					output.println(mark);
				} else if (echoString.substring(0, 2).equals("GM")) {
					int row = Integer.parseInt(echoString.substring(2, 3));
					int col = Integer.parseInt(echoString.substring(3, 4));
					char mark = model.getMark(row, col);
					output.println(mark);
	

				try {
					//Server timeout test
					Thread.sleep(10);

				} catch (InterruptedException e) {
					System.out.println("Thread Interrupted");
				}
				// output.println(echoString);
			}
			}
		} catch (IOException e) {
			System.out.println("Opps: " + e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// Oh, well...
			}
		}
	}

}
