package blockchainPackage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class BlockchainAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        Blockchain blockchain = (Blockchain) value;
        out.beginObject();

        out.name("length");
        out.value(blockchain.getLength());
        out.name("blocks");
        BlockAdapter.writeArrayBlocks(out, blockchain.getAllBlocks());

        out.endObject();
    }

    @Override
    public Object read(JsonReader in) throws IOException {

        Blockchain blockchain = null;
        try {
            blockchain = new Blockchain();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        in.beginObject();
        while (in.hasNext()) {
            if (in.peek().equals(JsonToken.NULL)){
                in.nextNull();
            }
            String name = in.nextName();

            switch (name) {

                case "length":
                    int length = in.nextInt();
                    blockchain.setLength(length);
                    continue;
                case "blocks":
                    blockchain.setAllBlocks((LinkedList) BlockAdapter.read(in));
                    continue;
            }
        }
        in.endObject();
        return blockchain;
    }
}
