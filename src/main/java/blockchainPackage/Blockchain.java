package blockchainPackage;

import com.fasterxml.jackson.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Blockchain {

    private LinkedList<Block> blocks = new LinkedList<>();

    public Blockchain() throws NoSuchAlgorithmException {
        blocks.add(new Block(new Transaction(null,null,10000.0)));
    }
    public Block getGenesisBlock(){
        return this.blocks.getFirst();
    }
    public LinkedList<Block> getAllBlocks(){
        return this.blocks;
    }
    public Block getLastBlock(){
        return this.blocks.getLast();
    }
    public int getLength(){
        return this.blocks.size();
    }
    public Block getBlock(String hash){
        for (Block block:this.blocks) {
            if(block.getHash().equals(hash)){
                return block;
            }
        }
        return null;
    }
    public Block getBlock(int height){
        for (int i = 0; i < this.blocks.size(); i++) {
            if (i == height) return this.blocks.get(i);
        }
        return null;
    }
    public void addBlock(Block block){
        block.setPrevHash(this.getLastBlock().getHash());
        this.blocks.add(block);
    }

}
