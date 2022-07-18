package blockchainPackage;

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

    public static void addUserWallet(User user){
        users.add(user);
    }
    public static void printUserListTransactions(String username){
        boolean b = false;
        for (User user:users) {
            if(user.getName().equals(username)){
                ArrayList<Transaction> transactions = user.getListTransaction();
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

    public static boolean checkUserExistence(String username){
        for (User user:users) {
            if(user.getName().equals(username)) return true;
        }
        return false;
    }
}
