package org.pearharmony.Network;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.io.*;

public class Decoder {
    private static final byte[] PNG_HEADER = new byte[] {
        (byte) 0x89,
        'P', 'N', 'G',
        (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A};

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
    public boolean isPng(byte[] _data)
    {
       return Arrays.asList(_data).contains(PNG_HEADER);
    }
    public boolean isWav(byte[] _data)
    {
        return true;//TODO:Yourmom
    }
    

    public String text(byte[] _data) {
        return new String(_data, StandardCharsets.UTF_8);
    }

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
