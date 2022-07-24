package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Blockchain blockchain = new Blockchain("target");

        BlockchainExplorer.printLastBlock(blockchain);
        BlockchainExplorer.printGenesisBlock(blockchain);
        System.out.println(blockchain.blockchainCheckValidity());

        User user = new User("RAPHA");
        //Block bloc = new Block(user.getFirstTransaction());
        //blockchain.addBlock(bloc);
        Wallet.printAmount("RAPHA");
        Wallet.printUserListTransactions("RAPHA");

        User user1 = new User("PHILP");
        Wallet.printAmount("PHILP");

        Transaction transaction = user.send(10.0,"RAPHA");
        if(transaction != null){
            Block block = new Block(transaction);
            blockchain.addBlock(block);
        }
        else System.out.println("Operation failed");

        Wallet.printAmount("RAPHA");

    }
}
