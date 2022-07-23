package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Blockchain blockchain = new Blockchain("target");
        BlockchainExplorer.printBlockDetails(1,blockchain);
/*
        User firstUser = new User("JAJA",blockchain.getGenesisBlock().getListTransaction().get(0).getAmount());
        Block block1 = new Block(firstUser.getListTransaction().get(0));

        User secondUser = new User("KAKA");
        User thirdUser = new User("ZAZA");

        block1.addTransaction(firstUser.send(100.0,secondUser));
        block1.addTransaction(secondUser.send(10.0,firstUser));
        blockchain.addBlock(block1);

        Block block2 = new Block(firstUser.send(20.0, thirdUser));
        blockchain.addBlock(block2);


        //String s = JsonFileManager.serialization("",blockchain);
        LinkedList<Block> blockchainDeserialized = JsonFileManager.deserialization("");

        System.out.println(blockchainDeserialized.size());
        blockchain.setBlocks(blockchainDeserialized);
        BlockchainExplorer.printBlockchainDetails(blockchain);
        BlockchainExplorer.printBlockDetails(0,blockchain);

       // Wallet.printUserListTransactions("HAHA");

       // BlockchainExplorer.printBlockDetails("fc48c0ddb84d4301c1e823625bca72fbb927f2ddb7ebe743e99e3dfd96c0e621",blockchain);
        //JsonFileManager.serialization("..",blockchain);
 */
       // JsonFileManager.serialization("target",blockchain);
    }
}
