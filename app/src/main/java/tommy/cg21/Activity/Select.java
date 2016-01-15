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
import tommy.cg21.API.GetPlayerData;
import tommy.cg21.Database.PlayerDB;
import tommy.cg21.Main;
import tommy.cg21.Objects.Player;
import tommy.cg21.R;


public class Select extends AppCompatActivity {

    GetPlayerData callAPI = new GetPlayerData();

    EditText username;
    EditText region;
    ArrayAdapter<Player> playerAdapter;
    ListView playerListView;
    List<Player> players = new ArrayList<Player>();

    private float lastX;
    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        playerListView = (ListView) findViewById(R.id.listView);
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        if (Main.playerDB.getUserCount() != 0){
            players.addAll(Main.playerDB.getAllPlayers());
            updateList();
        }
    }

    // Using the following method, we will handle all screen swaps.
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();

                // Handling left to right screen swap.
                if (lastX < currentX) {

                    // If there aren't any other children, just break.
                    if (flipper.getDisplayedChild() == 0)
                        break;

                    // Next screen comes in from left.
                    flipper.setInAnimation(this, R.anim.slide_in_from_left);
                    // Current screen goes out from right.
                    flipper.setOutAnimation(this, R.anim.slide_out_to_right);

                    // Display next screen.
                    flipper.showNext();
                }

                // Handling right to left screen swap.
                if (lastX > currentX) {

                    // If there is a child (to the left), kust break.
                    if (flipper.getDisplayedChild() == 1)
                        break;

                    // Next screen comes in from right.
                    flipper.setInAnimation(this, R.anim.slide_in_from_right);
                    // Current screen goes out from left.
                    flipper.setOutAnimation(this, R.anim.slide_out_to_left);

                    // Display previous screen.
                    flipper.showPrevious();
                }
                break;
        }
        return false;
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

            TextView playerName = (TextView) view.findViewById(R.id.playerName);
            playerName.setText(currentPlayer.getSummonname());

            return view;
        }
    }
}
