// @Veljko
package org.pearharmony.Network;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.*;

public class Decoder {
//Removes the first byte, which is the msg type indicator
    public byte[] cleanData(byte[] _data) {
        byte[] clean = new byte[_data.length - 1];
        for (int i = 0; i < clean.length; i++) {
            clean[i] = _data[i + 1];
        }
        return clean;
    }
//returns first byte, which is the msg type indicator
    public byte getType(byte[] _data) {
        return _data[0];
    }
//decodes an byte array to a UTF-8 String
    public String text(byte[] _data) {
        return new String(_data, StandardCharsets.UTF_8);
    }
//decodes byte array to a png and saves it to a path with the current unix time as name
    public Path picture(byte[] _data, String _path) {
        try {
            Path path = Paths.get(_path, System.currentTimeMillis() + ".png");
            Files.write(path, _data, StandardOpenOption.CREATE);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
//decodes byte array to a wav and saves it to a path with the current unix time as name
    public Path sound(byte[] _data, String _path) {
        Path path = Paths.get(_path, System.currentTimeMillis() + ".wav");
        try {
            Files.write(path, _data, StandardOpenOption.CREATE);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
