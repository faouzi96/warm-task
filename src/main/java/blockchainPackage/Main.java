package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

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
