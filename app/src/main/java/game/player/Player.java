package game.player;

/**
 * Created by Ann Gabrusionok on 24.10.2017.
 */

public class Player {
    private String name;
    private int record;

    public Player(){}
    public Player (String name){
        this.name = name;
        record = 0;
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
}
