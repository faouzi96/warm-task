package blockchainPackage;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
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
        System.out.println("90 - Change Blockchain mining difficulty");
        System.out.println("99 - Exit the CLI");
        System.out.println("---------------------------------------------------------------------------------");
    }
    public static void createNewUser() throws IOException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creation of new user: ");
        System.out.println("Type the new user's name: ");
        String name = scanner.nextLine();
        User newUser = new User(name);
        System.out.println("User: "+ newUser.getName() + " created successfully");
    }
    public static void mineBlock(Blockchain blockchain, Block block) throws IOException, NoSuchAlgorithmException {
        if (block != null) {
            System.out.println("Mine the block: ");
            blockchain.addBlock(block);
            System.out.println("The block is mined successfully");
        }
        else{
            System.out.println("There is no block to mine");
        }
    }
    public static Block createNewTransaction(Blockchain blockchain, Block blockRef) throws NoSuchAlgorithmException, IOException {
        Block block = blockRef;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creation of new Transaction: ");
        System.out.println("Sender name: ");
        String sender = scanner.nextLine();
        System.out.println("Receiver name: ");
        String receiver = scanner.nextLine();
        System.out.println("Amount: ");
        Scanner scanDouble = new Scanner(System.in);
        double amount = Double.parseDouble(scanDouble.nextLine());
        while (amount <= 0){
            System.out.println("The amount should be greater than 0");
            amount = Double.parseDouble(scanDouble.nextLine());
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
        return block;
    }
    public static String readFolder(){
        Scanner scanner = new Scanner(System.in);
        String path="";
        boolean pathExist = false;
        do {
            System.out.println("Enter the Blockchain Folder Path: ");
            path = scanner.nextLine();
            File file = new File(path);
            pathExist = file.exists();
            if (!pathExist) System.out.println("Directory not found");
        }while(!pathExist);
        return path;
    }
    public static void changeDifficulty(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Change the difficulty: ");
        System.out.println("Blockchain Difficulty right now: " + Blockchain.getDifficulty());
        System.out.println("Enter the difficulty: (If you do not want to change it. Enter the same number as the previous)");
        int difficulty = (Integer) scanner.nextInt();
        while ((difficulty > 4 || difficulty < 0)){
            System.out.println("The difficulty should be in the interval [0,4]");
            System.out.println("Enter the difficulty: (If you do not want to change it. Enter the same number as the previous)");
            difficulty = (Integer) scanner.nextInt();
        }
        Blockchain.setDifficulty(difficulty);
        System.out.println("Blockchain difficulty has changed successfully");
    }
    public static void getBlockByHeight(Blockchain blockchain){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the Height of the block:");
        int height = scanner.nextInt();
        BlockchainExplorer.printBlockDetails(height,blockchain);
    }
    public static void getBlockByHash(Blockchain blockchain){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the Hash of the block:");
        String hash = scanner.nextLine();
        BlockchainExplorer.printBlockDetails(hash,blockchain);
    }
    public static void validityChecker(Blockchain blockchain) throws NoSuchAlgorithmException {
        if(blockchain.blockchainCheckValidity()) System.out.println("Blockchain is valid");
        else System.out.println("Blockchain not valid");
    }
    public static void userAmount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the user's name: ");
        Wallet.printAmount(scanner.nextLine());
    }
    public static void userTransactions(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the user's name: ");
        Wallet.printUserListTransactions(scanner.nextLine());
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Scanner scanner = new Scanner(System.in);
        Block block = null;
        boolean lunched = true;

        String path = readFolder();
        changeDifficulty();
        Blockchain blockchain = new Blockchain(path);
        printHelpAssist();

        while (lunched){
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
                    getBlockByHeight(blockchain);
                    continue;
                case 5:
                    getBlockByHash(blockchain);
                    continue;
                case 6:
                    validityChecker(blockchain);
                    continue;
                case 7:
                    mineBlock(blockchain, block);
                    block = null;
                    continue;
                case 8:
                    createNewUser();
                    continue;
                case 9:
                    System.out.println("List of users: ");
                    Wallet.printListUsers();
                    continue;
                case 10:
                    block = createNewTransaction(blockchain, block);
                    continue;
                case 11:
                    userAmount();
                    continue;
                case 12:
                    userTransactions();
                    continue;
                case 90:
                    changeDifficulty();
                    continue;
                case 99:
                    if(block != null) mineBlock(blockchain,block);
                    lunched = false;
                    continue;
            }
        }
    }
}
