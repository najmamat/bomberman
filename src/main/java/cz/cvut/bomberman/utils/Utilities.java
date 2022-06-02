package cz.cvut.bomberman.utils;

import java.io.*;

/**
 * Utilities class has 2 methods, to read our .txt file and distinguish between data inside the file.
 */
public class Utilities {
    private String path;

    /**
     * Method loadFileAsString loads a whole text file as a String and String is later translated into data.
     * @param path
     * @return
     */
    public static String loadFileAsString(String path){
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line + "\n");
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static int parseInt(String number){
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
