package blockchainPackage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        User user = (User) value;
        out.beginObject();

        out.name("name");
        out.value(user.getName());
        out.name("amount");
        out.value(user.getAmount());

        out.endObject();
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        String userName = null;
        double amount = 0;

        in.beginObject();

        while (in.hasNext()) {
            if (in.peek().equals(JsonToken.NULL)){
                in.nextNull();
            }
            String name = in.nextName();

            switch (name) {
                case "name":
                    userName = in.nextString();
                    continue;
                case "amount":
                    amount = in.nextDouble();
                    continue;
            }
        }

        User user = null;

        try {
            user = new User(userName,amount);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        in.endObject();
        return user;
    }
}
