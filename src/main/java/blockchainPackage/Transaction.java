package blockchainPackage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Transaction {

    @JsonProperty
    private User sender;
    @JsonProperty
    private User receiver;
    @JsonProperty
    private double amount;
    @JsonProperty
    private String timestamp;

    @JsonCreator
    public Transaction(User sender, User receiver, double amount){
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = (Instant.now()).toString();
    }

    @JsonGetter
    public String getSender(){
        if(this.sender != null) return this.sender.getName();
        else return  null;
    }
    @JsonGetter
    public String getReceiver(){
        if(this.receiver != null) return this.receiver.getName();
        else return null;
    }
    @JsonGetter
    public double getAmount(){
        return this.amount;
    }
    @JsonGetter
    public String getTimestamp(){
        return this.timestamp;
    }

}
