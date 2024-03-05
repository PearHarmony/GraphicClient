package org.pearharmony.Network;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.*;

public class Decoder {
    public byte[] cleanData(byte[] _data) {
        byte[] clean = new byte[_data.length - 1];
        for (int i = 0; i < clean.length; i++) {
            clean[i] = _data[i + 1];
        }
        return clean;
    }

    public byte getType(byte[] _data) {
        return _data[0];
    }

    public String text(byte[] _data) {
        return new String(_data, StandardCharsets.UTF_8);
    }

    public Path picture(byte[] _data, String _path) {
        Path path = Paths.get(_path, System.currentTimeMillis() + ".png");
        try {
            Files.write(path, _data, StandardOpenOption.CREATE);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Path sound(byte[] _data, String _path) {
        Path path = Paths.get(_path, System.currentTimeMillis() + ".mp3");
        try {
            Files.write(path, _data, StandardOpenOption.CREATE);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
