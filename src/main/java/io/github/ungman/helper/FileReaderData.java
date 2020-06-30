package io.github.ungman.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReaderData {

    public static List<String> readFullFile(String filePath) {
        List<String> dataFromFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                dataFromFile.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFromFile;
    }
}
