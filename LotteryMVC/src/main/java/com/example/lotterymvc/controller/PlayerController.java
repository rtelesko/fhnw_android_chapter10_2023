package com.example.lotterymvc.controller;

import com.example.lotterymvc.model.PlayerModel;
import com.example.lotterymvc.view.PlayerView;

public class PlayerController {
    private final PlayerModel playerModel;

    private final PlayerView playerView;

    public PlayerController(PlayerView userView) {
        this.playerModel = new PlayerModel();
        this.playerView = userView;
    }

    public void getPlayers() {
        playerView.displayPlayers(playerModel.getPlayers());
    }
}