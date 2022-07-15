package blockchainPackage;

import java.util.ArrayList;

public class Wallet {
    double amount;
    ArrayList<Transaction> transactions = new ArrayList<>();
    public Wallet(double amount){
        this.amount = amount;
    }
    public double getAmount(){
        return this.amount;
    }
    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }
    public void addAmount(double amount){
        this.amount += amount;
    }
    public void subAmount(double amount){
        this.amount -= amount;
    }
    public ArrayList<Transaction> getListTransactions(){
        return this.transactions;
    }
}
