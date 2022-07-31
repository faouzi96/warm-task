package blockchainPackage;

import java.time.Instant;
import java.util.ArrayList;

public class Block extends Exception{
    private String prevHash;
    private String hash;
    private String timeStamp;
    private int nonce;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Block(Transaction transaction) {
        this.nonce = 0;
        this.prevHash = null;
        this.transactions.add(transaction);
        this.timeStamp = (Instant.now()).toString();
    }
    //This constructor created just for the deserialization purpose
    public Block(){}
    public String getHash(){
        return this.hash;
    }
    public String getPrevHash(){
        return this.prevHash;
    }
    public boolean addTransaction(Transaction transaction) {
        if(this.transactions.size() >= 10) return false;
        else{
            this.transactions.add(transaction);
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
    public void setTransactions(ArrayList transactions){
        this.transactions = transactions;
    }
    public void setNonce(int nonce){
        this.nonce = nonce;
    }
    public int getNonce() {
        return nonce;
    }
}
