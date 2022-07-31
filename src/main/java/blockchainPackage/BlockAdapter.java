package blockchainPackage;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class BlockAdapter {

    private static void writeBlocks(JsonWriter out, Object value) throws IOException {
        Block block = (Block) value;
        out.beginObject();

        out.name("timestamp");
        out.value(block.getTimeStamp());
        out.name("nonce");
        out.value(block.getNonce());
        out.name("hash");
        out.value(block.getHash());
        out.name("prevHash");
        out.value(block.getPrevHash());
        out.name("transactions");
        TransactionAdapter.writeArrayTransactions(out, block.getListTransaction());

        out.endObject();
    }

    public static LinkedList<Block> read(JsonReader in) throws IOException {

        //List of Transactions contained in a block
        LinkedList<Block> blocks = new LinkedList<>();

        in.beginArray();
        while(!in.peek().equals(JsonToken.END_ARRAY)) {
            Block block = new Block();
            in.beginObject();

            while (in.hasNext()) {

                String name = in.nextName();

                switch (name) {

                    case "timestamp":
                        String next = in.nextString();
                        block.setTimeStamp(next);
                        continue;
                    case "nonce":
                        int nonce = in.nextInt();
                        block.setNonce(nonce);
                        continue;
                    case "hash":
                        next = in.nextString();
                        block.setHash(next);
                        continue;
                    case "prevHash":
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            block.setPrevHash(null);
                        } else {
                            block.setPrevHash(in.nextString());
                        }
                        continue;
                    case "transactions":
                        try {
                            block.setTransactions(TransactionAdapter.read(in));
                            blocks.add(block);
                        } catch (NoSuchAlgorithmException e) {
                            throw new RuntimeException(e);
                        }
                        continue;
                }
            }
            in.endObject();
        }
        in.endArray();
        return blocks;
    }

    public static void writeArrayBlocks(JsonWriter writer, LinkedList<Block> blocks) throws IOException {
        writer.beginArray();
        for (Block block : blocks) {
            BlockAdapter.writeBlocks(writer, block);
        }
        writer.endArray();
    }

}
