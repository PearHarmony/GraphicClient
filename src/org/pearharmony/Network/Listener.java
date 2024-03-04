package org.pearharmony.Network;

// A Java program for a Server
import java.net.*;
import java.io.*;
import org.pearharmony.Control.*;

public class Listener implements Runnable {
	// initialize socket and input stream
	private Socket socket;
	private ServerSocket server = null;
	private int port;
	public Handler handle;
	private Control control;
	Thread handler;

	public Listener(int _port, Control _control) {
		port = _port;
		control = _control;
	}

	// constructor with port
	public void run() {
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {

			// starts server and waits for a connection
			try {

				socket = server.accept();
				// handle connection
				handle = new Handler(socket, control);
				handler = new Thread(handle);
				handler.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
