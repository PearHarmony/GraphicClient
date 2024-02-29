package org.pearharmony.Network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encoder {
    public byte[] text(String _string) {
        byte[] data = (_string).getBytes();
        byte[] pack = new byte[data.length + 1];
        pack[0] = (byte) 0x00;
        for (int i = 0; i < data.length; i++) {
            pack[i + 1] = data[i];
        }
        return pack;
    }

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
}
