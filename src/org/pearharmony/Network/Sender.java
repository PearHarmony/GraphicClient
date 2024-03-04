package org.pearharmony.Network;

// A Java program for a Client
import java.io.*;
import java.net.*;

public class Sender implements Runnable {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataOutputStream out = null;
	private String address;
	private int port;
	byte type;
	byte[] data;

	public Sender(String _address, int _port, byte[] _data) {
		address = _address;
		port = _port;
		data = _data;
	}

	public void run() {
		// establish a connection
		try {
			socket = new Socket(address, port);
			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException u) {
			System.out.println(u);
			return;
		} catch (IOException i) {
			System.out.println(i);
			return;
		}

		try {
			out.write(data);
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
