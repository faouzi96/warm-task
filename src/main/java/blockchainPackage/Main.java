package blockchainPackage;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;



public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        SimpleObj obj = new SimpleObj();

        ArrayList<User> users = new ArrayList<>();
        Blockchain blockchain = new Blockchain();

        BlockchainExplorer.printGenesisBlock(blockchain);

        User firstUser = new User("JAJA",blockchain.getGenesisBlock().getListTransaction().get(0).getAmount());
        Block block1 = new Block(firstUser.getListTransaction().get(0));

        User secondUser = new User("DADA");
        User thirdUser = new User("HAHA");

        block1.addTransaction(firstUser.send(100.0,secondUser));
        block1.addTransaction(secondUser.send(10.0,firstUser));
        blockchain.addBlock(block1);

        Block block2 = new Block(firstUser.send(20.0, thirdUser));
        blockchain.addBlock(block2);
        BlockchainExplorer.printBlockchainDetails(blockchain);
        BlockchainExplorer.printBlockDetails(1,blockchain);

        Wallet.printUserListTransactions("DADA");
        /*
        System.out.println("Block hash: "+blockchain.getLastBlock().getHash());
        System.out.println("Previous Block hash: "+blockchain.getLastBlock().getPrevHash());

        ArrayList<Transaction> userTransaction = firstUser.getListTransaction();

        for (int i = 0; i < userTransaction.size(); i++) {
            System.out.println(userTransaction.get(i).getSender());
            System.out.println(userTransaction.get(i).getReceiver());
            System.out.println(userTransaction.get(i).getTimestamp());
            System.out.println(userTransaction.get(i).getAmount());
        }

    */
    }
}
