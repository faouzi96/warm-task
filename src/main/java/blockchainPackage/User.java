package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class User {
    private String name;
    private double amount;
    private Transaction firstTransaction;

    public User(String name) throws IOException, NoSuchAlgorithmException {
        if(Wallet.getListUsers().size() == 0){
            this.firstTransaction = new Transaction(null, this, Blockchain.getAmount());
            this.amount = Blockchain.getAmount();
        }
        else this.amount = 0.0;
        this.name=name;
        Wallet.addUserWallet(this);
    }

    public User(String name, double amount) throws IOException, NoSuchAlgorithmException {
        this.name=name;
        this.amount = amount;
        Wallet.addUserWallet(this);
    }

    public String getName(){
        return this.name;
    }

    public double getAmount(){
        return this.amount;
    }

    private void receive(Transaction transaction) throws IOException {
        this.amount += transaction.getAmount();
        Wallet.updateUser(this);
    }

    public Transaction send(double amount, User receiver) throws NoSuchAlgorithmException, IOException {
        if(this.amount < amount) return null;
        else{
            Transaction transaction = new Transaction(this,receiver, amount);
            receiver.receive(transaction);
            this.amount -= amount;
            Wallet.updateUser(this);
            return transaction;
        }
    }

    public Transaction getFirstTransaction(){
        return this.firstTransaction;
    }
    public void setFirstTransaction(Transaction transactions){
        this.firstTransaction = transactions;
    }

    public void setName(String name) {
        this.name = name;
    }
}
