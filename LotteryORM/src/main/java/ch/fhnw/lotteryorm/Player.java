package ch.fhnw.lotteryorm;

import com.orm.SugarRecord;

public class Player extends SugarRecord {

    String name;
    String description;

    public Player() {
    }


    public Player(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
