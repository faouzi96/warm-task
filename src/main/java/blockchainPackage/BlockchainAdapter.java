package blockchainPackage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class BlockchainAdapter extends TypeAdapter {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        Blockchain blockchain = (Blockchain) value;
        out.name("blockchain");
        out.beginArray();

        out.endArray();
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        try {
            Blockchain blockchain = new Blockchain("");
            in.beginObject();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
