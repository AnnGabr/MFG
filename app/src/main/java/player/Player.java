package player;

import java.io.Serializable;

/**
 * Created by Ann on 20.10.2017.
 */

public class Player implements Serializable{
    public static Player instance = new Player();

    private String name;
    private int record;
    private int lastScore;
    private boolean hasNewRecord;

    public Player(){}
    public Player (String name){
        this.name = name;
        lastScore = 0;
        record = 0;
        hasNewRecord = false;
    }

    public Player(String name, int record){
        this.name = name;
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }

    public boolean hasNewRecord() {
        return hasNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        hasNewRecord = newRecord;
    }
}
