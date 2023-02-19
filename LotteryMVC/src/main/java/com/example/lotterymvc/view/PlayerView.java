package com.example.lotterymvc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lotterymvc.R;
import com.example.lotterymvc.controller.PlayerController;
import com.example.lotterymvc.model.Player;

import java.util.ArrayList;

public class PlayerView {
    View playerView;
    PlayerController playerController;
    Button getPlayersButton;
    ListView playersListView;

    public PlayerView(Context context, ViewGroup container) {
        playerView = LayoutInflater.from(context).inflate(R.layout.activity_main, container, false);
        playerController = new PlayerController(this);
    }

    public void initView() {
        getPlayersButton = playerView.findViewById(R.id.button);
        getPlayersButton.setOnClickListener(l -> {
            playerController.getPlayers();
        });
        playersListView = playerView.findViewById(R.id.player_list_view);
    }

    public void displayPlayers(ArrayList<Player> players) {
        PlayersAdapter adapter = new PlayersAdapter(playerView.getContext(), players);

        playersListView.setAdapter(adapter);
    }

    public View getRootView() {
        return playerView;
    }
}

class PlayersAdapter extends ArrayAdapter<Player> {
    public PlayersAdapter(Context context, ArrayList<Player> players) {
        super(context, 0, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player player = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }
        TextView tvName = convertView.findViewById(R.id.player_name);
        tvName.setText(player.getName());
        return convertView;
    }
}
