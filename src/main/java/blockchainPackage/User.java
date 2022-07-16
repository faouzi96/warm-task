package blockchainPackage;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;

public class User {
    @JsonProperty
    private String name;
    @JsonProperty
    private Wallet wallet;
    @JsonProperty
    private String hash;

    @JsonCreator
    public User(String name, double amount){
        this.name=name;
        this.wallet = new Wallet(amount);
    }
    @JsonCreator
    public User(String name){
        this.name=name;
        this.wallet = new Wallet(0.0);
    }
    @JsonGetter
    public String getName(){
        return this.name;
    }
    @JsonGetter
    public double getAmount(){
        return this.wallet.getAmount();
    }
    @JsonSetter
    private void receive(Transaction transaction) {
        this.wallet.addAmount(transaction.getAmount());
        this.wallet.addTransaction(transaction);
    }
    @JsonAnyGetter
    public Transaction send(double amount, User receiver){
        if(this.wallet.amount < amount) return null;
        else{
            Transaction transaction = new Transaction(this,receiver, amount);
            receiver.receive(transaction);
            this.wallet.subAmount(amount);
            this.wallet.addTransaction(transaction);
            return transaction;
        }
    }
    @JsonGetter
    public ArrayList<Transaction> getListTransaction(){
        return this.wallet.getListTransactions();
    }
}
