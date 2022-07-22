package blockchainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class JsonFileManager {

    // Serialize our object and write into a file using the filePath path, and finally
    // Return a string with the content of our file
    public static String serialization(String filePath,Blockchain object) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(Block.class, new BlockAdapter());
        Gson gson = builder.create();
        return (writeJsonFile(filePath+"target/blockchain.json",gson.toJson(object.getAllBlocks())));

    }

    // Read the file from the passed path, deserialize it transforms it into an object and
    // return this last one
    public static LinkedList<Block> deserialization(String filePath, Class className) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Block.class, new BlockAdapter());
        builder.serializeNulls();
        Gson gson = builder.create();
        Type blockType = new TypeToken<LinkedList<Block>>(){}.getType();
        LinkedList<Block> blockchain = gson.fromJson(readJsonFile(filePath), blockType);
        return  blockchain;
    }

    // Read the JSON file from our disk using the PathFile parameter
    private static String readJsonFile(String filePath) throws FileNotFoundException {
            String json = "";
            File myData = new File("target/blockchain.json");
            Scanner myReader = new Scanner(myData);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                json += data;
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
