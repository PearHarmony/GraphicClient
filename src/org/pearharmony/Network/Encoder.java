package org.pearharmony.Network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encoder {
    public byte[] textEncode(String _string) {
        return (_string).getBytes();
    }

    public byte[] pictureEncode(String _path, String _filename) {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get(_path, _filename));
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public byte[] pictureEncode(Path _path) {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(_path);
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
