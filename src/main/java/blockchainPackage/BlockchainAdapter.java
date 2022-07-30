package blockchainPackage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class BlockchainAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        Blockchain blockchain = (Blockchain) value;
        out.beginObject();

        out.name("length");
        out.value(blockchain.getLength());
        out.name("difficulty");
        out.value(Blockchain.getDifficulty());
        out.name("blocks");
        BlockAdapter.writeArrayTransactions(out, block.getListTransaction());

        out.endObject();
    }

    @Override
    public Object read(JsonReader in) throws IOException {

        Block block = new Block();

        in.beginObject();
        while (in.hasNext()) {
            if (in.peek().equals(JsonToken.NULL)){
                in.nextNull();
            }
            String name = in.nextName();

            switch (name) {

                case "length":
                    String next = in.nextString();
                    block.setTimeStamp(next);
                    continue;
                case "difficulty":
                    int nonce = in.nextInt();
                    block.setNonce(nonce);
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
}
