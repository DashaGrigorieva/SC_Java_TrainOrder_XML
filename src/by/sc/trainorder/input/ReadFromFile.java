package by.sc.trainorder.input;

import by.sc.trainorder.exception.DataCheckException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by User on 03.06.2016.
 */
public class ReadFromFile {

    private ArrayList<String> parsedFile = new ArrayList<String>();

    public void setParsedFile(ArrayList<String> parsedFile) { this.parsedFile = parsedFile; }
    public ArrayList<String> getParsedFile() { return parsedFile; }

    public ReadFromFile(String fileName) throws DataCheckException, IOException {
        File inFile = new File(fileName);
        if (!inFile.exists() || inFile.isDirectory()) throw new DataCheckException("Invalid input file name!");
        Path path = Paths.get(fileName);
        this.parsedFile = (ArrayList<String>) Files.readAllLines(path, StandardCharsets.UTF_8);
    }

}
