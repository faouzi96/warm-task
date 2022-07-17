package blockchainPackage;

import java.util.ArrayList;

public class BlockchainExplorer {
    Blockchain blockchain;
    ArrayList<User> users = new ArrayList<>();
    public BlockchainExplorer(Blockchain blockchain, ArrayList<User> users){
        this.blockchain = blockchain;
        this.users.addAll(users);
    }


}
