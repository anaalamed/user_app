package utils;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;

public class Files {

    // write to file
    public  static void writeToFile(String filename, String  content) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  static  void writeJsonToFile(String filename, HashMap<String, String> content) {
        Gson gson = new Gson();
        writeToFile(filename, gson.toJson(content));
    }

    // read from file
    public static HashMap<String, String> readFromFile(String filename) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filename)) {

            // Convert JSON File to HashMap
            HashMap<String, String> mapJson = gson.fromJson(reader, HashMap.class);
            return mapJson;

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException ex: "+ e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
