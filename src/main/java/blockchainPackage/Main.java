package blockchainPackage;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public void createUser(){

    }

    public static void printHelpAssist(){
        System.out.println("0 - Help --> See the commands");
        System.out.println("----------------------------Blockchain Explorer---------------------------------");
        System.out.println("1 - Last block");
        System.out.println("2 - Genesis block");
        System.out.println("3 - Blockchain length");
        System.out.println("4 - Block details (Height)");
        System.out.println("5 - Block details (Hash)");
        System.out.println("6 - Blockchain validity");
        System.out.println("7 - Mine the current block");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("------------------------------Users Operations-----------------------------------");
        System.out.println("8 - Create new user");
        System.out.println("9 - See list of users");
        System.out.println("10 - make transaction");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("------------------------------Wallet Explorer-----------------------------------");
        System.out.println("11 - Check the balance of any user");
        System.out.println("12 - See the list of transactions by user");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("------------------------------Extra-----------------------------------");
        System.out.println("99 - Exit the CLI");
        System.out.println("---------------------------------------------------------------------------------");
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Scanner scanner = new Scanner(System.in);
        String path = "";
        boolean pathExist = false;
        Block block = null;

        do {
            System.out.println("Enter the Blockchain Folder Path: ");
            path = scanner.nextLine();
            File file = new File(path);
            pathExist = file.exists();
            if (!pathExist) System.out.println("Directory not found");
        }while(!pathExist);
        printHelpAssist();
        Blockchain blockchain = new Blockchain(path);

        boolean lunched = true;
        while (lunched){
            Scanner sc = new Scanner(System.in);
            System.out.println("Type the command number: ");
            int order = scanner.nextInt();
            switch (order){
                case 0:
                    printHelpAssist();
                    continue;
                case 1:
                    BlockchainExplorer.printLastBlock(blockchain);
                    continue;
                case 2:
                    BlockchainExplorer.printGenesisBlock(blockchain);
                    continue;
                case 3:
                    BlockchainExplorer.printBlockchainDetails(blockchain);
                    continue;
                case 4:
                    System.out.println("Type the Height of the block:");
                    int height = scanner.nextInt();
                    BlockchainExplorer.printBlockDetails(height,blockchain);
                    continue;
                case 5:
                    System.out.println("Type the Hash of the block:");
                    String hash = sc.nextLine();
                    BlockchainExplorer.printBlockDetails(hash,blockchain);
                    continue;
                case 6:
                    if(blockchain.blockchainCheckValidity()) System.out.println("Blockchain is valid");
                    else System.out.println("Blockchain not valid");
                    continue;
                case 7:
                    if (block != null) {
                        System.out.println("Mine the block: ");
                        blockchain.addBlock(block);
                        System.out.println("The block is mined successfully");
                    }
                    else{
                        System.out.println("There is no block to mine");
                    }
                    continue;
                case 8:
                    System.out.println("Creation of new user: ");
                    System.out.println("Type the new user's name: ");
                    String name = sc.nextLine();
                    User newUser = new User(name);
                    System.out.println("User: "+ newUser.getName() + " created successfully");
                    continue;
                case 9:
                    System.out.println("List of users: ");
                    Wallet.printListUsers();
                    continue;
                case 10:
                    System.out.println("Creation of new Transaction: ");
                    System.out.println("Sender name: ");
                    String sender = sc.nextLine();
                    System.out.println("Receiver name: ");
                    String receiver = sc.nextLine();
                    System.out.println("Amount: ");
                    Scanner scanDouble = new Scanner(System.in);
                    double amount = (double)scanDouble.nextDouble();
                    while (amount <= 0){
                        System.out.println("The amount should be greater than 0");
                        amount = (double)scanDouble.nextDouble();
                    }
                    User user1 = Wallet.getUser(sender);
                    if (Wallet.checkUserExistence(sender) || Wallet.checkUserExistence(receiver)) {
                        Transaction transaction = user1.send(amount, receiver);
                        if (transaction != null) {
                            if (block == null) {
                                block = new Block(transaction);
                                System.out.println("Transaction added successfully to the block");
                            } else {
                                block.addTransaction(transaction);
                                if (block.getListTransaction().size() <= 10) {
                                    System.out.println("Transaction added successfully to the block");
                                }
                                if (block.getListTransaction().size() == 10) {
                                    blockchain.addBlock(block);
                                    block = null;
                                    System.out.println("The block is mined successfully < 10 Transaction in it >");
                                }
                            }
                        } else {
                            System.out.println("The balance of the user is not sufficient for this operation");
                            Wallet.printAmount(sender);
                        }
                    }
                    else System.out.println("The Sender or the Receiver does not exist");
                    // ADD to a block
                    continue;
                case 11:
                    System.out.println("Type the user's name: ");
                    Wallet.printAmount(sc.nextLine());
                    continue;
                case 12:
                    System.out.println("Type the user's name: ");
                    Wallet.printUserListTransactions(sc.nextLine());
                    continue;
                case 99:
                    lunched = false;
                    continue;
            }
        }
        // Creation or Deserialization of the blockchain structure
        // if there is no structure already created this instance will create the genesis block
        // automatically and assign 10000.0 unit as a first transaction
        // Accept a String as an argument ( path where can find or where we want store our blockchain
        /*----------------------------------------------------*/
            //Blockchain blockchain = new Blockchain("target");
        /*----------------------------------------------------*/

        /*---------------------------- Creation of the first user -----------------------------*/

            //User firstUser = new User("FIRSTONE");

            // just after creating the first user we have to get the first transaction which consist to
            // send all the balance to this first user
               //  Transaction transaction = firstUser.getFirstTransaction();

            // create a block and Add this transaction to it
            // Block has to contain at least one transaction this is why we pass the first transaction to its
            // constructor
              // Block block = new Block(transaction);

        /*--------------------------------------------------------------------------------------*/

        /*----------------------------Do transactions--------------------------------*/

           // We have to create other users who will have a blanace 0.0
              // User secondUser = new User("SECONDONE");
          // User method send - return the transaction
             // Transaction transaction = firstUser.send(HowMuch,secondUserName);
          // Add this transaction to a block which is not added to the blockchain
          // This method return a boolean which allows us to know if the block contains 10 or less than 10 transactions
            // block.addTransaction(transaction);


        /*------------------------------------------------------------------------------------*/

        /*---------------------------- Blockchain method add block -----------------------------*/

        /*--- block as an argument - return void ---- */
        // blockchain.addBlock(block);

        /*---------------------------------------------------------------------------------------*/

        /*----------------------------Blockchain explorer--------------------------------*/

           /*--- blockchain as an argument - display genesis block---- */
               // BlockchainExplorer.printGenesisBlock(blockchain);

           /*--- Height and blockchain as arguments - display a block---- */
               // BlockchainExplorer.printBlockDetails(height,blockchain);

           /*--- Hash and blockchain as arguments - display a block---- */
               // BlockchainExplorer.printBlockDetails(Hash,blockchain);

           /*--- blockchain as an argument - display the last block---- */
               // BlockchainExplorer.printLastBlock(blockchain);

           /*--- blockchain as an argument - display the length of the blockchain---- */
               ///// BlockchainExplorer.printBlockchainDetails(blockchain);

        /*------------------------------------------------------------------------------------*/

        /*--------------------------------- WALLET OPERATIONS --------------------------------*/
           // Get the user Object from his username
              // Wallet.getUser(userName);
           // display the balance for any user by passing his username
              // Wallet.printAmount(userName);
           // display the list of transaction for the username passed as an argument
              //Wallet.printUserListTransactions(userName);
        /*------------------------------------------------------------------------------------*/

       /*
          Transaction transaction = user.send(10.0,"RAPHA");
          if(transaction != null){
             Block block = new Block(transaction);
             blockchain.addBlock(block);
          }
          else System.out.println("Operation failed");
       */
    }
}
