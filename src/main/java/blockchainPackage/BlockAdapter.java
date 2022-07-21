package blockchainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

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
        out.value(block.getHash());
        out.name("transactions");
        //out.value(JsonFileManager.serializationTransaction(block.getListTransaction()));
        //ArrayList<String> hashTransactions = new ArrayList<>();
        //for (Transaction transaction: block.getListTransaction()) {
          //  hashTransactions.add(transaction.getHash());
        //}
        writeArray(out, block.getListTransaction());

        //GsonBuilder builder = new GsonBuilder();
        // builder.registerTypeAdapter(Blockchain.class, new BlockchainAdapter());
        //builder.registerTypeAdapter(Transaction.class, new TransactionAdapter());
        //Gson gson = builder.create();
        //out.value(hashTransactions.toString());
        out.endObject();
    }

    @Override
    public Object read(JsonReader reader) throws IOException {

        return null;
    }

    public void writeArray(JsonWriter writer, ArrayList<Transaction> transactions) throws IOException {
        writer.beginArray();
        for (Transaction transaction:transactions) {
            TransactionAdapter.writeTransaction(writer,transaction);
        }
        writer.endArray();
    }

}
