package cz.cvut.bomberman.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class WriteFile is used to write text into file. Its is used in EditorGrid, so that it creates a new .txt file
 * to load our map from.
 */
public class WriteFile {
    private String path;
    private boolean append_to_file = false;

    public WriteFile(String file_path){
        path = file_path;
    }

    public WriteFile(String file_path, boolean append_value){
        path = file_path;
        append_to_file = append_value;
    }

    public void writeToFile(String textLine) throws IOException {

        FileWriter write = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s", textLine);
        print_line.close();


    }
}
