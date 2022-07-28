package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Blockchain {
    private static LinkedList<Block> blocks = new LinkedList<>();
    private static int difficulty = 0;
    private static String path;
    // Constructor we deserialize the users and the blockchain JSON files
    // We check if there is already some chunks of code in the Blockchain File
    // otherwise we create new one, we create first transaction add it to the genesis Block and add as
    // our first block in the blockchain
    public Blockchain(String path) throws NoSuchAlgorithmException, IOException {
        this.path = path;
        Wallet.setListUsers(JsonFileManager.deserializationUsers(path));
        LinkedList<Block> listBlocks = JsonFileManager.deserialization(path);
        if (listBlocks.size() == 0){
            blocks.add(new Block(new Transaction(null,null,10000.0)));
            JsonFileManager.serialization(this.path,this);
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
    //After adding any block to the blockchain we serialize it and save it in the JSON file
    public void addBlock(Block block) throws IOException {
        if (block != null) {
            block.setPrevHash(this.getLastBlock().getHash());
            this.blocks.add(block);
            block = null;
            JsonFileManager.serialization(this.path, this);
        }
        else System.out.println("The block is NULL");
    }
    // This method allows us to obtain the list of the transactions by passing the name of the user
    // this method is called in Wallet Class
    // If the user does not exist the list will empty
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
    // This method check if the previous Hash of any block match with the previous block's hash
    public boolean blockchainCheckValidity() throws NoSuchAlgorithmException {
        int length = this.blocks.size();
        for (int i = length-1; i >= 1; i--) {
            if(!this.blocks.get(i).getPrevHash().equals(this.blocks.get(i-1).getHash())){
                return false;
            }
        }
        return true;
    }
    // this method return the amount of the first transaction or the amount existing in the blockchain
    public static double getAmount(){
        return blocks.get(0).getListTransaction().get(0).getAmount();
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Blockchain.difficulty = difficulty;
    }
}
