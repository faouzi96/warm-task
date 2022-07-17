package blockchainPackage;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;

public class User {

    private String name;

    private Wallet wallet;

    private String hash;


    public User(String name, double amount){
        this.name=name;
        this.wallet = new Wallet(amount, this);
    }

    public User(String name){
        this.name=name;
        this.wallet = new Wallet(0.0);
    }

    public String getName(){
        return this.name;
    }

    public double getAmount(){
        return this.wallet.getAmount();
    }

    private void receive(Transaction transaction) {
        this.wallet.addAmount(transaction.getAmount());
        this.wallet.addTransaction(transaction);
    }

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

    public ArrayList<Transaction> getListTransaction(){
        return this.wallet.getListTransactions();
    }
}
