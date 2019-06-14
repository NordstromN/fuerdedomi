package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class TicTacToeModelClient implements TicTacToeModelInterface {

	private Socket socket;
	private PrintWriter ostream;
	private BufferedReader echoes;
	
	//Der Modelclient Socket um mit dem Server zu kommunizieren, localhost auf Port 50500
	public TicTacToeModelClient() {
		try {
			this.socket = new Socket("127.0.0.1", 50500);
			socket.setSoTimeout(15);
			this.ostream = new PrintWriter(this.socket.getOutputStream(), true);
			this.echoes = new BufferedReader(
					new InputStreamReader(this.socket.getInputStream()));

			this.ostream.println("Welcome Player");
		} catch (IOException e) {
			System.out.println("Unkown host");
		}
	}
	
	//Wenn setBoard methode ausgerufen, sendet String and Server
	public void setBoard() {
		this.ostream.println("setBoard");
	}

	//Sendet "WC" an Server welches die Methode winCheck aufruft
	//Wartet ein true/false als antwort ab um zu sehen ob jemand gewonnen hat
	public boolean winCheck() {
		boolean rval = false;
		this.ostream.println("WC");
		try {
			rval = this.echoes.readLine().equals("TRUE");
		} catch (IOException e) {
			return false;
		}	
		System.out.println("WC Answer: " + rval);
		return rval;
	}

	//ChangeToken methode sendet "CT" an server was die ChangeToken Methode aufgruft
	//Antwort ReturnValue ist der neue Token
	public char changeToken() {
		String rval;
		this.ostream.println("CT");
		try {
			rval = this.echoes.readLine();
		} catch (IOException e) {
			return 0;
		}	
		System.out.println("CT Answer: " + rval);
		return rval.toCharArray()[0];
	}

	//Sendet string SM und ruft so auf dem Server die SetMark methode auf
	public char setMark() {
		String rval;
		this.ostream.println("SM");
		try {
			rval = this.echoes.readLine();
		} catch (IOException e) {
			return 0;
		}	
		System.out.println("SM Answer: " + rval);
		return rval.toCharArray()[0];
	}

	//GetMark sendet dem Server GM und die beiden row col int
	//als antwort kommt ein Symbol x oder o welches gestzt ist
	public char getMark(int row, int col) {
		String rval;
		this.ostream.println("GM" + Integer.toString(row) + 
				Integer.toString(col));
		try {
			rval = this.echoes.readLine();
		} catch (IOException e) {
			return 0;
		}	
		System.out.println("GM Answer: " + rval);
		return rval.toCharArray()[0];
		
	}
	
	//placeMark führt die Methode aus welche den Char auf das Board setzt.
	public boolean placeMark(int row, int col) {
		boolean rval = false;
		String rb;
		this.ostream.println("PM" + Integer.toString(row) + 
				Integer.toString(col));
		try {
			rb = this.echoes.readLine(); 
			rval = rb.equals("TRUE");
		} catch (IOException e) {
			return false;
		}
		System.out.println("PM Answer: " + rb);
		return rval;
	}


}
