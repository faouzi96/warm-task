package blockchainPackage;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;

public class User {

    private String name;

    private double amount;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public User(String name, double amount){
        this.name=name;
        this.transactions.add(new Transaction(null, this, amount));
        this.amount = amount;
        Wallet.addUserWallet(this);
    }

    public User(String name){
        this.name=name;
        this.amount = 0.0;
        Wallet.addUserWallet(this);
    }

    public String getName(){
        return this.name;
    }

    public double getAmount(){
        return this.amount;
    }

    private void receive(Transaction transaction) {
        this.amount = transaction.getAmount();
        this.transactions.add(transaction);
    }

    public Transaction send(double amount, User receiver){
        if(this.amount < amount) return null;
        else{
            Transaction transaction = new Transaction(this,receiver, amount);
            receiver.receive(transaction);
            this.amount -= amount;
            this.transactions.add(transaction);
            return transaction;
        }
    }

    public ArrayList<Transaction> getListTransaction(){
        return this.transactions;
    }
}
