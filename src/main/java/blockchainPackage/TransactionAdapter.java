package blockchainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TransactionAdapter extends TypeAdapter {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        Transaction transaction = (Transaction) value;
        out.beginObject();

        out.name("timestamp");
        out.value(transaction.getTimestamp());
        out.name("sender");
        out.value(transaction.getSender());
        out.name("receiver");
        out.value(transaction.getReceiver());
        out.name("amount");
        out.value(transaction.getAmount());
        out.endObject();
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        return null;
    }

}