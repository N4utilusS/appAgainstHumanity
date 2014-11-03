package appAgainstHumanity.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
	private ServerSocket serverSocket;
	private String password = "bite";
	
	public Server(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
			this.serverSocket.setSoTimeout(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		while(true) {
			System.out.println("Awaiting a new connection...");
			try {
				Socket sock = serverSocket.accept();
				System.out.println("Accepting connection from " + sock.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(sock.getInputStream());
				String clientMsg = in.readUTF();
				System.out.println("Client sent: '" + clientMsg + "'");
				if(this.password.equals(clientMsg)) {
					System.out.println("Password accepted.");
					DataOutputStream out = new DataOutputStream(sock.getOutputStream());
					out.writeUTF("Password accepted. Welcome on " + sock.getLocalSocketAddress() + ".");
				}
				else {
					System.out.println("Password rejected.");
					DataOutputStream out = new DataOutputStream(sock.getOutputStream());
					out.writeUTF("Wrong password. Try again or get the frack out.");
				}
				
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
