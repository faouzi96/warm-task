package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class User {
    private String name;
    private double amount;
    private Transaction firstTransaction;

    public User(String name) throws IOException, NoSuchAlgorithmException {
        // IF there is no user created already we assign all the balance to the first created
        if(Wallet.getListUsers().size() == 0){
            this.firstTransaction = new Transaction(null, this, Blockchain.getAmount());
            this.amount = Blockchain.getAmount();
        }
        else this.amount = 0.0;
        this.name=name;
        //Add the user to the list of users in the Wallet
        Wallet.addUserWallet(this);
    }
    // Constructor which is used just and uniquely in the deserialization process
    public User(String name, double amount) throws IOException, NoSuchAlgorithmException {
        this.name=name;
        this.amount = amount;
        Wallet.addUserWallet(this);
    }
    // Get the name of the user
    public String getName(){
        return this.name;
    }
    // Get the amount or the balance of the user
    public double getAmount(){
        return this.amount;
    }
    // Method which allows to the user to receive transactions
    private void receive(Transaction transaction) throws IOException {
        this.amount += transaction.getAmount();
        Wallet.updateUser(this);
    }
    // Method which enables for user to send or make transactions
    public Transaction send(double amount, String receiverName) throws NoSuchAlgorithmException, IOException {
        //Get the User object from the Name of the user
        User receiver = Wallet.getUser(receiverName);
        //If the amount is not enough or lesser than the cost of the operation
        if(this.amount < amount) {
            System.out.println("The user does not have enough money to do this transaction");
            return null;
        }
        else{
            Transaction transaction = new Transaction(this,receiver, amount);
            receiver.receive(transaction);
            this.amount -= amount;
            //Update the user's balance in the Wallet
            Wallet.updateUser(this);
            return transaction;
        }
    }
    // Get the first transaction which is created directly after the creation of our first user and
    // used to make the fisrt transaction and add it to our first block
    public Transaction getFirstTransaction(){
        return this.firstTransaction;
    }
    //// these are optional methods they don't serve for nothing in this application
    public void setFirstTransaction(Transaction transactions){
        this.firstTransaction = transactions;
    }
    public void setName(String name) {
        this.name = name;
    }
}
