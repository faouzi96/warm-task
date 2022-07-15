package blockchainPackage;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JsonFileManager {

    // Serialize our object and write into a file using the filePath path, and finally
    // Return a string with the content of our file
    public static void serialization(String filePath,Blockchain object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath+"target/blockchain.json"),object);
    }
    // Read the file from the passed path, deserialize it transforms it into an object and
    // return this last one
    public static Object deserialization(String filePath, Class className) throws FileNotFoundException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(readJsonFile(filePath+"target/blockchain.json"), className);
    }

    // Read the JSON file from our disk using the PathFile parameter
    private static String readJsonFile(String filePath) throws FileNotFoundException {
            String json = "";
            File myData = new File(filePath);
            Scanner myReader = new Scanner(myData);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                json += data;
            }
            myReader.close();
            System.out.println(json);
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
