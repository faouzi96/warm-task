package blockchainPackage;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class SimpleObj {
    private ArrayList<Block> blocks = new ArrayList<>();
    int x;
    public SimpleObj() throws NoSuchAlgorithmException {
       Transaction transaction = new Transaction(null,null,10000.0);
       blocks.add(new Block(transaction));
    }
    public String getName(){
        return "HEYa";
    }
    public ArrayList<Block> getBlocks(){
        return blocks;
    }

}
