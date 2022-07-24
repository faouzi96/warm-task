package blockchainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class Wallet {
    private static ArrayList<User> users = new ArrayList<>();
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
    public static User getUser(String username){
        for (User user:users) {
            if(user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }
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
    public static void addUserWallet(User user) throws IOException {
        if(!checkUserExistence(user.getName())){
            users.add(user);
            JsonFileManager.serializationUser(Blockchain.getPath());
        }
        else{
            System.out.println("User already exist");
        }
    }
    public static void updateUser(User user) throws IOException {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getName().equals(user.getName())){
                users.set(i,user);
            }
        }
        JsonFileManager.serializationUser(Blockchain.getPath());
    }
    public static boolean checkUserExistence(String username){
        for (User user:users) {
            if(user.getName().equals(username)) return true;
        }
        return false;
    }
    public static ArrayList getListUsers(){
        return users;
    }
    public static void setListUsers(ArrayList listUsers){
         users = listUsers;
    }

}
