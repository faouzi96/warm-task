package blockchainPackage;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class Transaction {
    private User sender;
    private User receiver;
    private double amount;
    private String timestamp;

    public Transaction(User sender, User receiver, double amount) throws NoSuchAlgorithmException {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = (Instant.now()).toString();
    }
    public String getSender(){
        if(this.sender != null) return this.sender.getName();
        else return  null;
    }
    public String getReceiver(){
        if(this.receiver != null) return this.receiver.getName();
        else return null;
    }
    public double getAmount(){
        return this.amount;
    }
    public String getTimestamp(){
        return this.timestamp;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

}
