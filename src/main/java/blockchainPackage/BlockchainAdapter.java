package blockchainPackage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
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
    public Blockchain read(JsonReader in) throws IOException {
        LinkedList<Block> blocks = new LinkedList<>();
        Blockchain blockchain = new Blockchain(1);

        in.beginObject();
        while (in.hasNext()) {

            String name = in.nextName();

            switch (name) {
                case "length":
                    int length = in.nextInt();
                    blockchain.setLength(length);
                    continue;
                case "blocks":
                    blocks.addAll(BlockAdapter.read(in));
                    blockchain.setAllBlocks(blocks);
                    continue;
            }
        }
        in.endObject();
        return blockchain;
    }
}
