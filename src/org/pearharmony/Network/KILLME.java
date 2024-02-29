
package org.pearharmony;

//TODO: KILL MYSELF!!!!
public class KILLME {
	public static void main(String args[]) {
		new KILLME().start();
	}

	public void start() {
		Decoder de = new Decoder();
		Encoder en = new Encoder();
		de.pictureDecode(en.pictureEncode("D:", "test.png"), "D:");
	}
}