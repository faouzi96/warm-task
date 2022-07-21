package blockchainPackage;


import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;

public class Block extends Exception{

    private String prevHash;

    private String hash;

    private String timeStamp;

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();


    public Block(Transaction transaction) throws NoSuchAlgorithmException {
        this.prevHash = null;
        this.transactions.add(transaction);
        Hash256 hashObject = new Hash256(this.transactions.toString());
        this.hash = hashObject.getHash();
        this.timeStamp = (Instant.now()).toString();
    }
    public Block() throws NoSuchAlgorithmException {}
    public String getHash(){
        return this.hash;
    }

    public String getPrevHash(){
        return this.prevHash;
    }

    public boolean addTransaction(Transaction transaction) throws NoSuchAlgorithmException {
        if(this.transactions.size() >= 10) return false;
        else{
            this.transactions.add(transaction);
            Hash256 hashObject = new Hash256(this.transactions.toString());
            this.hash = hashObject.getHash();
            return true;
        }
    }
    public ArrayList<Transaction> getListTransaction(){
        return this.transactions;
    }
    public String getTimeStamp(){
        return this.timeStamp;
    }
    public void setHash(String hash){
        this.hash = hash;
    }
    public void setPrevHash(String prevHash){
        this.prevHash = prevHash;
    }
    public void setTimeStamp(String timestamp){
        this.timeStamp = timestamp;
    }
}
