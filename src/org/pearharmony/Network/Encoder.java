// @Veljko
package org.pearharmony.Network;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encoder {
    //encodes a string to a byte array but sets the first byte to a type id
    public byte[] text(String _string) {
        byte[] data = _string.getBytes(StandardCharsets.UTF_8);
        byte[] pack = new byte[data.length + 1];
        pack[0] = (byte) 0x00;
        for (int i = 0; i < data.length; i++) {
            pack[i + 1] = data[i];
        }
        return pack;
    } 

    //encodes a picture from a path to a byte array but sets the first byte to a type id
    public byte[] picture(String _path, String _filename) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(_path, _filename));
            byte[] pack = new byte[data.length + 1];
            pack[0] = (byte) 0x01;
            for (int i = 0; i < data.length; i++) {
                pack[i + 1] = data[i];
            }
            return pack;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //encodes a picture from a path to a byte array but sets the first byte to a type id
    public byte[] picture(Path _path) {
        try {
            byte[] data = Files.readAllBytes(_path);
            byte[] pack = new byte[data.length + 1];
            pack[0] = (byte) 0x01;
            for (int i = 0; i < data.length; i++) {
                pack[i + 1] = data[i];
            }
            return pack;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //encodes a wav file from a path to a byte array but sets the first byte to a type id
    public byte[] sound(String _path, String _filename) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(_path, _filename));
            byte[] pack = new byte[data.length + 1];
            pack[0] = (byte) 0x02;
            for (int i = 0; i < data.length; i++) {
                pack[i + 1] = data[i];
            }
            return pack;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //encodes a wav file from a path to a byte array but sets the first byte to a type id
    public byte[] sound(Path _path) {
        try {
            byte[] data = Files.readAllBytes(_path);
            byte[] pack = new byte[data.length + 1];
            pack[0] = (byte) 0x02;
            for (int i = 0; i < data.length; i++) {
                pack[i + 1] = data[i];
            }
            return pack;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
