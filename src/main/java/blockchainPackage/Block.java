package blockchainPackage;

import com.fasterxml.jackson.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;

public class Block extends Exception{
    @JsonProperty
    private String prevHash;
    @JsonProperty
    private String hash;
    @JsonProperty
    private String timeStamp;
    @JsonProperty
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    @JsonCreator
    public Block(Transaction transaction) throws NoSuchAlgorithmException {
        this.prevHash = null;
        this.transactions.add(transaction);
        Hash256 hashObject = new Hash256(this.transactions.toString());
        this.hash = hashObject.getHash();
        this.timeStamp = (Instant.now()).toString();
    }

    @JsonGetter
    public String getHash(){
        return this.hash;
    }
    @JsonGetter
    public String getPrevHash(){
        return this.prevHash;
    }
    @JsonAnySetter
    public boolean addTransaction(Transaction transaction) throws NoSuchAlgorithmException {
        if(this.transactions.size() >= 10) return false;
        else{
            this.transactions.add(transaction);
            Hash256 hashObject = new Hash256(this.transactions.toString());
            this.hash = hashObject.getHash();
            return true;
        }
    }
    @JsonGetter
    public ArrayList<Transaction> getListTransaction(){
        return this.transactions;
    }

    @JsonSetter
    public void setPrevHash(String prevHash){
        this.prevHash = prevHash;
    }
}
