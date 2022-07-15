package blockchainPackage;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JsonFileManager {

    // Serialize our object and write into a file using the filePath path, and finally
    // Return a string with the content of our file
    public static String serialization(String filePath,Object object) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        return writeJsonFile(filePath,json);
    }
    // Read the file from the passed path, deserialize it transforms it into an object and
    // return this last one
    public static Object deserialization(String filePath, Class className) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(readJsonFile(filePath), className);
    }

    // Read the JSON file from our disk using the PathFile parameter
    private static String readJsonFile(String filePath) throws FileNotFoundException {
            String json = "";
            File myData = new File(filePath);
            Scanner myReader = new Scanner(myData);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                json.concat(data);
            }
            myReader.close();
            return json;
    }

    // Write into the file which is location in filePath
    private static String writeJsonFile(String filePath, String json) throws IOException {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(json);
            myWriter.close();
            System.out.println("JSON file Successfully wrote.");
        return json;
    }
}
*/