package blockchainPackage;

import java.io.IOException;
import java.util.ArrayList;

public class Wallet {
    private static ArrayList<User> users = new ArrayList<>();
    //PRINT the amount or the balance of the user which is passed as an argument to this method
    public static void printAmount(String username){
        boolean b = false;
        for (User user:users) {
             if(user.getName().equals(username)) {
                 System.out.println("________________________________________________");
                 System.out.println("User: " + username);
                 System.out.println("Amount: "+ user.getAmount());
                 System.out.println("________________________________________________");
                 b = true;
             }
        }
        if(!b){
            System.out.println("________________________________________________");
            System.out.println("User: " + username);
            System.out.println("User not found");
            System.out.println("________________________________________________");
        }
    }
    // This method return the User object based on his Name
    public static User getUser(String username){
        for (User user:users) {
            if(user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    // This method display or print the list of transactions of the user which his name is passed as parameter to this method
    public static void printUserListTransactions(String username){
        boolean b = false;
        for (User user:users) {
            if(user.getName().equals(username)){
                ArrayList<Transaction> transactions = Blockchain.getListTransactionsByUser(username);
                System.out.println("_____________________________________________________________");
                System.out.println("Transactions of the user: " + username);
                for (Transaction transaction: transactions) {
                    System.out.println("\t - Transaction:____________________________________");
                    System.out.println("\t\t - Timestamp: " + transaction.getTimestamp());
                    System.out.println("\t\t - Sender: " + transaction.getSender());
                    System.out.println("\t\t - Receiver: " + transaction.getReceiver());
                    System.out.println("\t\t - Amount: " + transaction.getAmount());
                    System.out.println("\t\t __________________________________________________");
                }
                System.out.println("_____________________________________________________________");
                b=true;
            }
        }
        if(!b){
            System.out.println("________________________________________________");
            System.out.println("User: " + username);
            System.out.println("User not found");
            System.out.println("________________________________________________");
        }
    }
    // After creating any user we call this method to add it at the list of users available in the wallet
    public static void addUserWallet(User user) throws IOException {
        if(!checkUserExistence(user.getName())){
            users.add(user);
            JsonFileManager.serializationUser(Blockchain.getPath());
        }
        else{
            System.out.println("User already exist");
        }
    }
    // This method is called we do any new operation (transaction) to update the user balance information
    public static void updateUser(User user) throws IOException {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getName().equals(user.getName())){
                users.set(i,user);
            }
        }
        JsonFileManager.serializationUser(Blockchain.getPath());
    }
    //This method check if the user already exist in the list of users
    public static boolean checkUserExistence(String username){
        for (User user:users) {
            if(user.getName().equals(username)) return true;
        }
        return false;
    }
    // Return all the list of users
    public static ArrayList<User> getListUsers(){
        return users;
    }
    // This method is just used during the deserialization process of the users file json
    public static void setListUsers(ArrayList<User> listUsers){
         users = listUsers;
    }

}
