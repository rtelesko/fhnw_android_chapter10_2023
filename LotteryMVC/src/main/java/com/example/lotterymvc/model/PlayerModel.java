package com.example.lotterymvc.model;

import java.util.ArrayList;

public class PlayerModel {
    private final ArrayList<Player> playerList;

    public PlayerModel(){
        playerList = new ArrayList<Player>();

        // get users from database
        playerList.add(new Player("John"));
        playerList.add(new Player("David"));
        playerList.add(new Player("Jill"));
        playerList.add(new Player("Pat"));
        playerList.add(new Player("Donald"));
        playerList.add(new Player("Sarah"));
    }

    public ArrayList<Player> getPlayers() {
        return playerList;
    }
}