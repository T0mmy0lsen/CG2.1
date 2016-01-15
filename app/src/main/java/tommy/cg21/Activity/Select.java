package tommy.cg21.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.util.ArrayList;
import java.util.List;
import tommy.cg21.CallAPI;
import tommy.cg21.Database.PlayerDB;
import tommy.cg21.Objects.Player;
import tommy.cg21.R;


public class Select extends AppCompatActivity implements View.OnTouchListener {

    CallAPI callAPI;
    ViewFlipper flipper;
    EditText username;
    EditText region;
    PlayerDB playerDB;
    ArrayAdapter<Player> playerAdapter;
    ListView playerListView;
    List<Player> players = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        playerDB = new PlayerDB(getApplicationContext());
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        flipper.setOnTouchListener(this);

        if (playerDB.getUserCount() != 0){
            players.addAll(playerDB.getAllPlayers());
        }

        updateList();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        flipper.showNext();
        return true;
    }

    private void updateList(){
        playerAdapter = new PlayerListAdapter();
        playerListView.setAdapter(playerAdapter);
    }

    public void addPlayer(View v){
        username = (EditText) findViewById(R.id.username);
        region = (EditText) findViewById(R.id.region);
        callAPI.callPlayer(username.getText().toString(), region.getText().toString());
        updateList();
    }

    private class PlayerListAdapter extends ArrayAdapter<Player> {
        public PlayerListAdapter() {
            super(Select.this, R.layout.player_list, players);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = getLayoutInflater().inflate(R.layout.player_list, parent, false);
            }

            Player currentPlayer = players.get(position);

            TextView playerName = (TextView) findViewById(R.id.playerName);
            playerName.setText(currentPlayer.getSummonname());

            return view;
        }
    }
}
