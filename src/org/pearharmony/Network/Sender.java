package org.pearharmony;

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

	// constructor to put ip address and port
	/**
	 * @param address
	 * @param port
	 */
	public Sender(String _address, int _port, byte _type, byte[] _data) {
		address = _address;
		port = _port;
		data = _data;
		type = _type;
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
			byte[] pack = new byte[data.length + 1];
			pack[0] = type;
			for (int i = 0; i < data.length; i++) {
				pack[i + 1] = data[i];
			}
			out.write(pack);
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
