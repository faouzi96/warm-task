package blockchainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

public class BlockchainAdapter extends TypeAdapter {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        try {
            Blockchain blockchain = new Blockchain();
            in.beginObject();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
