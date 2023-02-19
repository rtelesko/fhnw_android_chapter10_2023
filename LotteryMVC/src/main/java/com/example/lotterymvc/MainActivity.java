package com.example.lotterymvc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lotterymvc.view.PlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerView = new PlayerView(MainActivity.this, null);
        setContentView(playerView.getRootView());
        playerView.initView();
    }
}
