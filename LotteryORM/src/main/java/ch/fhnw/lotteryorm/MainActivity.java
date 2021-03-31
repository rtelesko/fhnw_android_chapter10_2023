package ch.fhnw.lotteryorm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.orm.SugarContext;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btnSave;
    TextInputEditText tvName;
    TextInputEditText tvDescr;
    TextView tvMsg;

    /*
        Example to persist Data in SQL using Sugar ORM
        https://satyan.github.io/sugar/

        Check the AndroidManifest.xml - there are some changes you need to make for example setting the database name
        Then you need a Model Class (see Player.java) which extends from SugarRecord with two Constructors
        After you instantiate an Player Objekt (see below) you can use save/update/delete on the Object
        for all the CRUD Operations

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvDescr = findViewById(R.id.tvDescr);
        tvMsg = findViewById(R.id.tvMsg);

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            Log.d("TAG", "onClick: save data...");
            Player player = new Player(Objects.requireNonNull(tvName.getText()).toString(), Objects.requireNonNull(tvDescr.getText()).toString());
            player.save();
            tvMsg.setText("Player saved!");

            // To List all the players you can use Player.listAll():
            List<Player> list = Player.listAll(Player.class);
            list.stream().forEach(val -> Log.d("TAG", val.name));
            // To Update just change the name and update:
            //player.name = "Another name";
            //player.update();

            // Count the Players with Player.count()
            Log.d("TAG", "onCreate: Player count: " + Player.count(Player.class));

        });

    }
}