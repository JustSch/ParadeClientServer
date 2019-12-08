package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHelper extends Thread{
	private Socket socket = null;
	public ServerHelper(Socket socket) {
		super("ServerHelper");
		this.socket = socket;
		

	}
	@Override
	public void run() {
		try {
			DataOutputStream dos = new DataOutputStream (
			        socket.getOutputStream() );
			DataInputStream  dis = new DataInputStream (
	                 socket.getInputStream() );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
	