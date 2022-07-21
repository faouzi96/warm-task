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
        out.value(block.getPrevHash());
        out.name("transactions");
        writeArrayTransactions(out, block.getListTransaction());
        out.endObject();
    }

    @Override
    public Object read(JsonReader reader) throws IOException {
        Blockchain blockchain = new Blockchain();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            System.out.println(token);
/*
            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }

            if ("name".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                student.setName(reader.nextString());
            }

            if("rollNo".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                student.setRollNo(reader.nextInt());
            }

 */
        }
        reader.endObject();
        return student;
        return null;
    }

    public void writeArrayTransactions(JsonWriter writer, ArrayList<Transaction> transactions) throws IOException {
        writer.beginArray();
        for (Transaction transaction:transactions) {
            TransactionAdapter.writeTransaction(writer,transaction);
        }
        writer.endArray();
    }

}
