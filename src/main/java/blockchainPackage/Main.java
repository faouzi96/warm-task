package blockchainPackage;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public void createUser() throws IOException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        while (Wallet.checkUserExistence(username)){
            System.out.println("This user exist already");
            username = scanner.nextLine();
        }
        User user = new User(username);
        connected(username);
    }

    public static void printHelpAssist(){
        System.out.println("0 - Help --> See the commands");
        System.out.println("----------------------------Blockchain Explorer---------------------------------");
        System.out.println("1 - Last block --> Get the last block in the blockchain");
        System.out.println("2 - Genesis block --> Get the First (Genesis) block in the blockchain");
        System.out.println("3 - Block details --> Get a block details using the hash");
        System.out.println("4 - Block details --> Get a block details using the height");
        System.out.println("5 - Blockchain validity --> Check the validity of the structure");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("------------------------------Users Operations-----------------------------------");
        System.out.println("6 - Create new user");
        System.out.println("7 - See list of users");
        System.out.println("8 - make transaction");
        System.out.println("9 - Mine the current block");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("------------------------------Wallet Explorer-----------------------------------");
        System.out.println("10 - Check the balance of any user");
        System.out.println("11 - See the list of transactions by user");
        System.out.println("---------------------------------------------------------------------------------");
    }

    public exporeBlockchain(){
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Scanner scanner = new Scanner(System.in);
        String path = "";
        boolean pathExist = false;

        do {
            System.out.println("Enter the Blockchain Folder Path: ");
            path = scanner.nextLine();
            File file = new File(path);
            pathExist = file.exists();
            if (!pathExist) System.out.println("Directory not found");
        }while(!pathExist);

        Blockchain blockchain = new Blockchain(path);

        boolean lunched = true;
        while (lunched){
            int order = scanner.nextInt();
            switch (order){
                case 0:
                    printHelpAssist();
                case 1:
                    BlockchainExplorer.printLastBlock(blockchain);
                    continue;
                case 2:
                    continue;
                case 3:
                    continue;
                case 4:
                    continue;
                case 5:
                    continue;
                case 6:
                    continue;
                case 7:
                    continue;
                case 8:
                    continue;
                case 9:
                    lunched = false;
                    continue;
            }
        }
        // Creation or Deserialization of the blockchain structure
        // if there is no structure already created this instance will create the genesis block
        // automatically and assign 10000.0 unit as a first transaction
        // Accept a String as an argument ( path where can find or where we want store our blockchain
        /*----------------------------------------------------*/
            Blockchain blockchain = new Blockchain("target");
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
