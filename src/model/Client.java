package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static final String IP = "192.168.1.8";
	private static final int PORT = 6066;

	public Client() {
		connect(IP, PORT);
	}

	private void connect(String ip, int port){
		
		System.out.println("Connection.");
		try (Socket client = new Socket(ip, port);
				OutputStream outToServer = client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
				InputStream inFromServer = client.getInputStream();
				DataInputStream in = new DataInputStream(inFromServer);) {
			
			System.out.println("Password verification.");
			String password = "bite";
			out.writeUTF(password);
			
			String passwordAnswer = in.readUTF();
			System.out.println("Server sent: " + passwordAnswer);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
