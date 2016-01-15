package tommy.cg21;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import tommy.cg21.Activity.Select;
import tommy.cg21.Database.PlayerDB;
import tommy.cg21.Objects.Player;

public class Main extends AppCompatActivity {

    public static PlayerDB playerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerDB = new PlayerDB(getApplicationContext());

        //Start main activity, the select-screen.
        final Intent select = new Intent(this, Select.class);
        startActivity(select);
    }
}
