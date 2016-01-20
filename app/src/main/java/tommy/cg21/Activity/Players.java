package tommy.cg21.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import tommy.cg21.API.Asy_Player_Stats;
import tommy.cg21.API.Asy_Player_Stats_Result;
import tommy.cg21.Database.Dat_Player;
import tommy.cg21.Main;
import tommy.cg21.Objects.Obj_Player;
import tommy.cg21.Objects.Obj_Player_Stats;
import tommy.cg21.R;

public class Players extends AppCompatActivity {

    public static List<Obj_Player> players = new ArrayList<Obj_Player>();
    static ArrayAdapter<Obj_Player> playerAdapter;
    ListView playerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_players);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.getBackground().setAlpha(120);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        playerListView = (ListView) findViewById(R.id.list_players);

        if (Main.playerDB.getUserCount() != 0) {
            players.addAll(Main.playerDB.getAllPlayers());
        }

        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Obj_Player thisPlayer;
                thisPlayer = Main.playerDB.getPlayer(position);
                int summonerID = thisPlayer.getSummonerID();
                startActivity(new Intent(Players.this, Current.class).putExtra("ID", summonerID));
            }
        });

        playerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Obj_Player thisPlayer;
                thisPlayer = Main.playerDB.getPlayer(position);
                int summonerID = thisPlayer.getSummonerID();
                Main.playerDB.deleteUser("" + summonerID);
                newList();
                return true;
            }
        });

        updateList();
    }

    public void newList() {
        players.clear();
        players.addAll(Main.playerDB.getAllPlayers());
        playerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        newList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(Players.this, Add_Player.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateList(){
        playerAdapter = new PlayerListAdapter();
        playerListView.setAdapter(playerAdapter);
    }

    private class PlayerListAdapter extends ArrayAdapter<Obj_Player> {
        public PlayerListAdapter() {
            super(Players.this, R.layout.player_list, players);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = getLayoutInflater().inflate(R.layout.player_list, parent, false);
            }

            Obj_Player currentPlayer = players.get(position);
            ImageView bg_list = (ImageView) view.findViewById(R.id.bg_list);
            
            try{
                bg_list.setImageURI(Uri.parse("android.resource://tommy.cg21/drawable/" + Main.championsDB.getChampion(Main.player_statsDB.getPlayer_stats(currentPlayer.getSummonerID()).getmostplayed()).toLowerCase() + "_splash_0"));
            } catch (NullPointerException e){
                bg_list.setImageURI(Uri.parse("android.resource://tommy.cg21/drawable/teemo_splash_0"));
            }

            TextView playerName = (TextView) view.findViewById(R.id.playerName);
            playerName.setText(currentPlayer.getSummonname());

            return view;
        }
    }
}
