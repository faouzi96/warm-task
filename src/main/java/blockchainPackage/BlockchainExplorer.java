package blockchainPackage;

public class BlockchainExplorer {

    public static void printBlockchainDetails(Blockchain blockchain) {
        System.out.println("________________________________________________");
        System.out.println("Blockchain length: "+blockchain.getLength());
        System.out.println("________________________________________________");
    }

    public static void printBlockDetails(String hash, Blockchain blockchain) {
        Block block = blockchain.getBlock(hash);
        if (block == null){
            System.out.println("________________________________________________");
            System.out.println("Block not found");
            System.out.println("________________________________________________");
        }
        else {
            System.out.println("\nBlock Details: \n");
            System.out.println("\t\t - Timestamp: " + block.getTimeStamp());
            System.out.println("\t\t - Hash: " + block.getHash());
            System.out.println("\t\t - Prev Hash: " + block.getPrevHash());
            System.out.println("\t\t - Nonce: " + block.getNonce());
            System.out.println("\t\t - Transactions List: \n");
            for (Transaction transaction : block.getListTransaction()) {
                System.out.println("\t\t\t - Transaction:____________________________________");
                System.out.println("\t\t\t\t - Timestamp: " + transaction.getTimestamp());
                System.out.println("\t\t\t\t - Sender: " + transaction.getSender());
                System.out.println("\t\t\t\t - Receiver: " + transaction.getReceiver());
                System.out.println("\t\t\t\t - Amount: " + transaction.getAmount());
                System.out.println("\t\t\t __________________________________________________");
            }
        }

    }

    public static void printBlockDetails(int height, Blockchain blockchain) {
        Block block = blockchain.getBlock(height);
        if (block == null){
            System.out.println("________________________________________________");
            System.out.println("Block not found");
            System.out.println("________________________________________________");
        }
        else {
            System.out.println("\n Block Details: \n");
            System.out.println("\t\t - Timestamp: " + block.getTimeStamp());
            System.out.println("\t\t - Hash: " + block.getHash());
            System.out.println("\t\t - Prev Hash: " + block.getPrevHash());
            System.out.println("\t\t - Nonce: " + block.getNonce());
            System.out.println("\t\t - Transactions List: \n");
            for (Transaction transaction : block.getListTransaction()) {
                System.out.println("\t\t\t - Transaction:____________________________________");
                System.out.println("\t\t\t\t - Timestamp: " + transaction.getTimestamp());
                System.out.println("\t\t\t\t - Sender: " + transaction.getSender());
                System.out.println("\t\t\t\t - Receiver: " + transaction.getReceiver());
                System.out.println("\t\t\t\t - Amount: " + transaction.getAmount());
                System.out.println("\t\t\t __________________________________________________");
            }
        }

    }

    public static void printLastBlock(Blockchain blockchain) {
        Block block = blockchain.getLastBlock();
        System.out.println("\n Block Details: \n");
        System.out.println("\t\t - Timestamp: " + block.getTimeStamp());
        System.out.println("\t\t - Hash: " + block.getHash());
        System.out.println("\t\t - Prev Hash: " + block.getPrevHash());
        System.out.println("\t\t - Transactions List: \n");
        for (Transaction transaction : block.getListTransaction()) {
            System.out.println("\t\t\t - Transaction:____________________________________");
            System.out.println("\t\t\t\t - Timestamp: " + transaction.getTimestamp());
            System.out.println("\t\t\t\t - Sender: " + transaction.getSender());
            System.out.println("\t\t\t\t - Receiver: " + transaction.getReceiver());
            System.out.println("\t\t\t\t - Amount: " + transaction.getAmount());
            System.out.println("\t\t\t __________________________________________________");
        }

    }

    public static void printGenesisBlock(Blockchain blockchain){
        Block block = blockchain.getGenesisBlock();
        System.out.println("\n Block Details: \n");
        System.out.println("\t\t - Timestamp: " + block.getTimeStamp());
        System.out.println("\t\t - Hash: " + block.getHash());
        System.out.println("\t\t - Prev Hash: " + block.getPrevHash());
        System.out.println("\t\t - Transactions List: \n");
        for (Transaction transaction : block.getListTransaction()) {
            System.out.println("\t\t\t - Transaction:____________________________________");
            System.out.println("\t\t\t\t - Timestamp: " + transaction.getTimestamp());
            System.out.println("\t\t\t\t - Sender: " + transaction.getSender());
            System.out.println("\t\t\t\t - Receiver: " + transaction.getReceiver());
            System.out.println("\t\t\t\t - Amount: " + transaction.getAmount());
            System.out.println("\t\t\t __________________________________________________");
        }
    }

}
