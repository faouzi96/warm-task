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

        // Create an empty Employee object
        Block block = null;
        LinkedList<Block> list = new LinkedList<>();
        try {
            block = new Block();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // Consume start of JSON object
        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            System.out.println(name);
            switch (name) {
                case "timestamp":
                    String next = in.nextString();
                    System.out.println(next);
                    block.setTimeStamp(next);
                    continue;
                case "hash":
                    next = in.nextString();
                    System.out.println(next);
                    block.setHash(next);
                    continue;
                case "prevHash":
                    next = in.nextString();
                    System.out.println(next);
                    block.setPrevHash(next);
                    continue;
                case "transactions":
                    next = in.nextString();
                    System.out.println(next);
                   // block.setTransactions(next);
                    continue;
            }
        }
        in.endObject();

        return null;
    }

    public void writeArrayTransactions(JsonWriter writer, ArrayList<Transaction> transactions) throws IOException {
        writer.beginArray();
        for (Transaction transaction : transactions) {
            TransactionAdapter.writeTransaction(writer, transaction);
        }
        writer.endArray();
    }

}
