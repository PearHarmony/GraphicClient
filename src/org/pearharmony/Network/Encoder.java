package org.pearharmony;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encoder {
    public byte[] textEncode(String _string) {
        return (_string + "[eof]").getBytes();
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
}
