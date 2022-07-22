package blockchainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

public class BlockAdapter extends TypeAdapter {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        Block block = (Block) value;
        out.beginObject();

        out.name("timestamp");
        out.value(block.getTimeStamp());
        out.name("hash");
        out.value(block.getHash());
        out.name("prevHash");
        out.value(block.getPrevHash());
        out.name("transactions");
        writeArrayTransactions(out, block.getListTransaction());
        out.endObject();
    }

    @Override
    public Object read(JsonReader in) throws IOException {

        Block block = null;
        try {
            block = new Block();
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

                case "timestamp":
                    String next = in.nextString();
                    block.setTimeStamp(next);
                    continue;
                case "hash":
                    next = in.nextString();
                    block.setHash(next);
                    continue;
                case "prevHash":
                    if (in.peek() == JsonToken.NULL){
                        in.nextNull();
                        block.setPrevHash(null);
                    }
                    else{
                        block.setPrevHash(in.nextString());
                   }
                    continue;
                case "transactions":
                    try {
                        block.setTransactions(TransactionAdapter.read(in));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    continue;
            }
        }
       in.endObject();
        return block;
    }

    public void writeArrayTransactions(JsonWriter writer, ArrayList<Transaction> transactions) throws IOException {
        writer.beginArray();
        for (Transaction transaction : transactions) {
            TransactionAdapter.writeTransaction(writer, transaction);
        }
        writer.endArray();
    }

}
