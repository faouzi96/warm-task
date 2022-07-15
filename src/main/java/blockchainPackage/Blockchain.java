package blockchainPackage;

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

    public void addBlock(Block block){
        block.setPrevHash(this.getLastBlock().getHash());
        this.blocks.add(block);
    }

}
