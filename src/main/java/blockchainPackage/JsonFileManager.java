package blockchainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        builder.registerTypeAdapter(Blockchain.class, new BlockchainAdapter());
        Gson gson = builder.create();
        if(filePath.charAt(filePath.length()-1) != '/') filePath += '/';
        return (writeJsonFile(filePath+"blockchain.json",gson.toJson(object)));

    }

    // Read the file from the passed path, deserialize it transforms it into an object and
    // return this last one
    public static Blockchain deserialization(String filePath) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Blockchain.class, new BlockchainAdapter());
        builder.serializeNulls();
        Gson gson = builder.create();
        Blockchain blockchain = null;
        blockchain = gson.fromJson(readJsonFile(filePath,"blockchain"), Blockchain.class);
        return  blockchain;
    }

    // Read the JSON file from our disk using the PathFile parameter
    private static String readJsonFile(String filePath, String type) {
        String json = "";
        if(filePath.charAt(filePath.length()-1) != '/') filePath += '/';
        File myData = new File(filePath+type+".json");
        // If the file does not exist already we're gonna create it
        if (!myData.exists()) {
            writeJsonFile(filePath+type+".json","");
            myData = new File(filePath+type+".json");
        }
        Scanner myReader = null;
        try {
            myReader = new Scanner(myData);
        } catch (FileNotFoundException e) {
            System.out.println("No such File or directory is found");
        }
        while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                json += data;
            }
        myReader.close();
        return json;
    }

    // Write into the file which is location in filePath
    private static String writeJsonFile(String filePath, String json) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(filePath);
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("No such directory is found");
        }

        return json;
    }

    // Serialize our object and write into a file using the filePath path, and finally
    // Return a string with the content of our file
    public static String serializationUser(String filePath) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(User.class, new UserAdapter());
        Gson gson = builder.create();
        if(filePath.charAt(filePath.length()-1) != '/') filePath += '/';
        return (writeJsonFile(filePath+"users.json",gson.toJson(Wallet.getListUsers())));

    }

    // Read the file from the passed path, deserialize it transforms it into an object and
    // return this last one
    public static ArrayList<User> deserializationUsers(String filePath) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserAdapter());
        builder.serializeNulls();
        Gson gson = builder.create();
        Type userType = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> users = null;
        users = gson.fromJson(readJsonFile(filePath,"users"), userType);
        return  users == null ? new ArrayList<>() : users;
    }
}
