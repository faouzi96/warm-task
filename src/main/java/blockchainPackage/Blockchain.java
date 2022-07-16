package blockchainPackage;

import com.fasterxml.jackson.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Blockchain {
    @JsonProperty
    private LinkedList<Block> blocks = new LinkedList<>();
    @JsonCreator
    public Blockchain() throws NoSuchAlgorithmException {
        blocks.add(new Block(new Transaction(null,null,10000.0)));
    }
    @JsonGetter

    public Block getGenesisBlock(){
        return this.blocks.getFirst();
    }
    @JsonGetter
    public LinkedList<Block> getAllBlocks(){
        return this.blocks;
    }
    @JsonGetter
    public Block getLastBlock(){
        return this.blocks.getLast();
    }
    @JsonGetter
    public int getLength(){
        return this.blocks.size();
    }

    @JsonAnySetter
    public void addBlock(Block block){
        block.setPrevHash(this.getLastBlock().getHash());
        this.blocks.add(block);
    }

}
