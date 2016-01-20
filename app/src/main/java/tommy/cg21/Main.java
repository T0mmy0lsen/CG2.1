package tommy.cg21;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tommy.cg21.API.Asy_Champions_Result;
import tommy.cg21.Activity.Players;
import tommy.cg21.Database.Dat_Champions;
import tommy.cg21.Database.Dat_Player;
import tommy.cg21.Database.Dat_Player_Stats;

public class Main extends AppCompatActivity {

    public static Dat_Player playerDB;
    public static Dat_Champions championsDB;
    public static Dat_Player_Stats player_statsDB;

    Asy_Champions_Result asy_champions_result = new Asy_Champions_Result();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerDB = new Dat_Player(getApplicationContext());
        championsDB = new Dat_Champions(getApplicationContext());
        player_statsDB = new Dat_Player_Stats(getApplicationContext());

        //Download Champions
        if (championsDB.getChampionsCount() < 50) {
            asy_champions_result.callChampion();
        }

        //Start main activity, the select-screen.
        final Intent select = new Intent(this, Players.class);
        startActivity(select);
    }
}
