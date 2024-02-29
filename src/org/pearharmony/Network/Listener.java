package org.pearharmony.Network;

// A Java program for a Server
import java.net.*;
import java.io.*;

public class Listener implements Runnable {
	// initialize socket and input stream
	private Socket socket;
	private ServerSocket server = null;
	private int port;
	public Handler handle;

	public Listener(int _port) {
		port = _port;
	}

	// constructor with port
	public void run() {
		// starts server and waits for a connection
		try {
			server = new ServerSocket(port);

			socket = server.accept();
			// handle connection
			handle = new Handler(socket);
			Thread handler = new Thread(handle);
			handler.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
