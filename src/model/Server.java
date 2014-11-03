package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server extends Thread {
	private ServerSocket serverSocket;
	private String password = "bite";
	   
   public Server(int port)
   {
      try {
		serverSocket = new ServerSocket(port);
	      serverSocket.setSoTimeout(10000);
	} catch (IOException e) {
		e.printStackTrace();
	}
   }

   public void run()
   {
      while(true)
      {
         try {
            System.out.println("Waiting for connection...");
            Socket sock = serverSocket.accept();
            DataInputStream in = new DataInputStream(sock.getInputStream());
            String clientMsg = in.readUTF();
            System.out.println("Client sent '" + clientMsg + "'");
            if(this.password.equals(clientMsg)) {
            	System.out.println("Password accepted.");
            	DataOutputStream out = new DataOutputStream(sock.getOutputStream());
	            out.writeUTF("You are now connected to " + sock.getLocalSocketAddress());
            }
            else {
            	System.out.println("Password accepted.");
            	DataOutputStream out = new DataOutputStream(sock.getOutputStream());
	            out.writeUTF("Wrong password. Get the frack away.");
            }
            sock.close();
         } catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         } catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }

}
