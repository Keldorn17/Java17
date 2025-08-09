package main.java.com.keldorn.dto;

import java.io.*;
import java.util.List;

public class Player implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private final static int version = 2;
    private  String name;
    private int topScore;
    private long bigScore;
    private final transient long accountId;
    private List<String> collectedWeapons;

    public Player(long accountId, String name, int topScore, List<String> collectedWeapons) {
        this.accountId = accountId;
        this.name = name;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + accountId + '\'' +
                "name='" + name + '\'' +
                ", topScore=" + topScore +
                ", bigScore=" + bigScore +
                ", collectedWeapons=" + collectedWeapons +
                '}';
    }

    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
//        stream.defaultReadObject();
//        bigScore = (bigScore == 0) ? 1_000_000_000L : bigScore;
        var serializedVer = stream.readInt();
        collectedWeapons = (List<String>) stream.readObject();
        name = stream.readUTF();
        topScore = stream.readInt();
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        System.out.println("--> Customized Writing");
        stream.writeInt(version);
        stream.writeObject(collectedWeapons);
        stream.writeUTF(name);
        stream.writeLong(topScore);
    }
}
