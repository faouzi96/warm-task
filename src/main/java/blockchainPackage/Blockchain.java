package blockchainPackage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Blockchain {
    private static LinkedList<Block> blocks = new LinkedList<>();
    private static String path;
    public Blockchain(String path) throws NoSuchAlgorithmException, FileNotFoundException {
        this.path = path;
        Wallet.setListUsers(JsonFileManager.deserializationUsers(path));
        LinkedList<Block> listBlocks = JsonFileManager.deserialization(path);
        if (listBlocks.size() == 0){
            blocks.add(new Block(new Transaction(null,null,10000.0)));
        }
        else{
            blocks.addAll(listBlocks);
        }
    }

    public static String getPath() {
        return path;
    }
    public void setBlocks(LinkedList blocks){
        this.blocks = blocks;
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
    public void addBlock(Block block) throws IOException {
        block.setPrevHash(this.getLastBlock().getHash());
        this.blocks.add(block);
        JsonFileManager.serialization(this.path,this);
    }

    public static ArrayList<Transaction> getListTransactionsByUser(String username){
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (Block block:blocks) {
            for (Transaction transaction: block.getListTransaction()) {
               if ((transaction.getReceiver()!= null && transaction.getReceiver().equals(username)) ||(transaction.getSender() != null && transaction.getSender().equals(username))){
                   transactions.add(transaction);
               }
            }
        }
        return transactions;
    }

    public boolean blockchainCheckValidity() throws NoSuchAlgorithmException {
        int length = this.blocks.size();
        for (int i = length-1; i > 1; i--) {
            if(!this.blocks.get(i).getPrevHash().equals(this.blocks.get(i-1).getHash())){
                return false;
            }
        }
        return true;
    }
    public static double getAmount(){
        return blocks.get(0).getListTransaction().get(0).getAmount();
    }
}
