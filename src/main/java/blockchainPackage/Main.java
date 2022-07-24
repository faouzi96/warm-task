package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Blockchain blockchain = new Blockchain("target");
        BlockchainExplorer.printLastBlock(blockchain);
        BlockchainExplorer.printGenesisBlock(blockchain);
        System.out.println(blockchain.blockchainCheckValidity());

        User user = Wallet.getUser("ZAZA");
        Wallet.printAmount("ZAZA");
        Wallet.printUserListTransactions(user.getName());
        JsonFileManager.serialization("target",blockchain);
    }
}
