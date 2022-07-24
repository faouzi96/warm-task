package blockchainPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Blockchain blockchain = new Blockchain("C:\\Users\\fboussad\\Downloads");
        BlockchainExplorer.printLastBlock(blockchain);
        BlockchainExplorer.printGenesisBlock(blockchain);
        System.out.println(blockchain.blockchainCheckValidity());

        User user = Wallet.getUser("ZAZA");
        Wallet.printAmount("ZAZA");
        Wallet.printUserListTransactions(user.getName());
        JsonFileManager.serialization("C:\\Users\\fboussad\\Downloads",blockchain);
    }
}
