package appAgainstHumanity;

import model.Client;
import model.Server;

public class Main {

	public static void main(String[] args) {
		new Client();
		Server s = new Server(6066);
		s.start();
	}

}
