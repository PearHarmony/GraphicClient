package org.pearharmony;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.*;

public class Decoder {

    public String textDecode(byte[] _data) {
        return new String(_data, StandardCharsets.UTF_8);
    }

    public void pictureDecode(byte[] _data, String _path) {
        Path path = Paths.get(_path, System.currentTimeMillis() + ".png");
        try {
            Files.write(path, _data, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
