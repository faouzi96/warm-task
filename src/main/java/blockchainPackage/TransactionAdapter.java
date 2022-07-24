package blockchainPackage;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class TransactionAdapter {

    public static void writeTransaction(JsonWriter out, Object value) throws IOException {
        Transaction transaction = (Transaction) value;
        out.beginObject();

        out.name("timestamp");
        out.value(transaction.getTimestamp());
        out.name("sender");
        out.value(transaction.getSender());
        out.name("receiver");
        out.value(transaction.getReceiver());
        out.name("amount");
        out.value(transaction.getAmount());

        out.endObject();
    }

    public static ArrayList read(JsonReader in) throws IOException, NoSuchAlgorithmException {
        //Declaration of an empty object as a template
        Transaction transaction = null;
        //List of Transactions contained in a block
        ArrayList<Transaction> transactions = new ArrayList<>();
        // Json Array starter
        in.beginArray();
        // While it's not the end of the Json array
        while(!in.peek().equals(JsonToken.END_ARRAY)){
            // Declaration and init of Transaction's properties
            User sender = null;
            User receiver = null;
            double amount = 0.0;
            String timestamp = "";
            // Json Object starter (Transaction)
            in.beginObject();
            // while there is a next element within the object
            while (in.hasNext()) {
                // Extract the name of the filed
                String name = in.nextName();
                // Check which field name match with the extracted name
                switch (name) {
                    case "timestamp":
                        timestamp = in.nextString();
                        continue;
                    case "sender":
                        // check if the value in the json file is NULL or not
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            sender = null;
                        } else {
                            sender = Wallet.getUser(in.nextString());
                        }
                        continue;
                    case "receiver":
                        // check if the value in the json file is NULL or not
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            receiver = null;
                        } else {
                            receiver = Wallet.getUser(in.nextString());
                        }
                        continue;
                    case "amount":
                        amount = in.nextDouble();
                        continue;
                }

            }
            // The end of the json object (Transaction)
            in.endObject();
            //Creation of Transaction instance using those different properties already initialized
            transaction = new Transaction(sender,receiver,amount);
            transaction.setTimestamp(timestamp);
            // Add the Transaction object to the List of the transaction in this block
            transactions.add(transaction);
        }
        //The end of the Json array
        in.endArray();
        // Return the list of the transactions in a specific block
        return transactions;
    }

    public static void writeArrayTransactions(JsonWriter writer, ArrayList<Transaction> transactions) throws IOException {
        writer.beginArray();
        for (Transaction transaction : transactions) {
            TransactionAdapter.writeTransaction(writer, transaction);
        }
        writer.endArray();
    }
}