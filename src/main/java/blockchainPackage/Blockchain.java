package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Blockchain {
    private static LinkedList<Block> blocks = new LinkedList<>();
    private static int difficulty = 0;
    private int length;
    private static String path;
    // Constructor we deserialize the users and the blockchain JSON files
    // We check if there is already some chunks of code in the Blockchain File
    // otherwise we create new one, we create first transaction add it to the genesis Block and add as
    // our first block in the blockchain
    public Blockchain() throws NoSuchAlgorithmException, IOException {
            this.length = 0;
            this.addBlock(new Block(new Transaction(null,null,10000.0)));
            JsonFileManager.serialization(Blockchain.path,this);
    }

    public static String getPath() {
        return Blockchain.path;
    }
    public static void setPath(String path){
        Blockchain.path = path;
    }
    public Block getGenesisBlock(){
        return Blockchain.blocks.getFirst();
    }
    public LinkedList<Block> getAllBlocks(){
        return Blockchain.blocks;
    }
    public void setAllBlocks(LinkedList blocks){
        Blockchain.blocks = blocks;
    }
    public Block getLastBlock(){
        return Blockchain.blocks.getLast();
    }
    public int getLength(){
        return this.length;
    }
    public void setLength(int length){
        this.length = length;
    }
    public Block getBlock(String hash){
        for (Block block:Blockchain.blocks) {
            if(block.getHash().equals(hash)){
                return block;
            }
        }
        return null;
    }
    public Block getBlock(int height){
        for (int i = 0; i < Blockchain.blocks.size(); i++) {
            if (i == height) return Blockchain.blocks.get(i);
        }
        return null;
    }
    //After adding any block to the blockchain we serialize it and save it in the JSON file
    public void addBlock(Block block) throws IOException, NoSuchAlgorithmException {
        if (block != null) {
            if (this.getAllBlocks().size() != 0) block.setPrevHash(this.getLastBlock().getHash());
            Blockchain.blocks.add(hashBlockWithDifficulty(block));
            this.length++;
            JsonFileManager.serialization(Blockchain.path, this);
        }
        else System.out.println("The block is NULL");
    }
    // This method allows us to obtain the list of the transactions by passing the name of the user
    // this method is called in Wallet Class
    // If the user does not exist the list will empty
    public static ArrayList<Transaction> getListTransactionsByUser(String username){
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (Block block:Blockchain.blocks) {
            for (Transaction transaction: block.getListTransaction()) {
               if ((transaction.getReceiver()!= null && transaction.getReceiver().equals(username)) ||(transaction.getSender() != null && transaction.getSender().equals(username))){
                   transactions.add(transaction);
               }
            }
        }
        return transactions;
    }
    // This method check if the previous Hash of any block match with the previous block's hash and the if the
    // the block Hash is valid ( create new hash using the nonce of the block)
    public boolean blockchainCheckValidity() throws NoSuchAlgorithmException {
        int length = Blockchain.blocks.size();
        boolean hashValidity = true;
        boolean chainValidity = true;
        boolean lengthValidity = true;

        if (length != this.length) lengthValidity = false;

        for (int i = length-1; i >= 1; i--) {
            Block block = Blockchain.blocks.get(i);
            Block prevBlock = Blockchain.blocks.get(i-1);

            if(!block.getPrevHash().equals(prevBlock.getHash())){
                chainValidity = false;
            }

            Hash256 hashObject = new Hash256(block.getListTransaction().toString() + block.getNonce());
            if(!block.getHash().equals(hashObject.getHash())){
                hashValidity = false;
            }
        }

        return hashValidity && chainValidity && lengthValidity;

    }
    // this method return the amount of the first transaction or the amount existing in the blockchain
    public static double getAmount(){
        return blocks.get(0).getListTransaction().get(0).getAmount();
    }
    public static int getDifficulty() {
        return Blockchain.difficulty;
    }
    public static void setDifficulty(int difficulty) {
        if(difficulty >= 0 && difficulty <= 4) Blockchain.difficulty = difficulty;
    }
    private Block hashBlockWithDifficulty(Block block) throws NoSuchAlgorithmException {

        Hash256 hashObject = new Hash256(block.getListTransaction().toString());
        int nonce = 0;

        while (!hashObject.getHash().substring(0,Blockchain.getDifficulty()).equals("0000".substring(0,Blockchain.getDifficulty()))){
            nonce++;
            hashObject = new Hash256(block.getListTransaction().toString() + nonce);
        }

        block.setHash(hashObject.getHash());
        block.setNonce(nonce);
        return block;
    }
    public boolean isNull(){
        return Blockchain.blocks.isEmpty();
    }
}
