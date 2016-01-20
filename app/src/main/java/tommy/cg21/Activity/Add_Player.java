package tommy.cg21.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import tommy.cg21.API.Asy_Player_Result;
import tommy.cg21.Main;
import tommy.cg21.R;

public class Add_Player extends Activity implements AdapterView.OnItemSelectedListener{

    Asy_Player_Result asy_player_result = new Asy_Player_Result();
    static EditText username;
    String region;

    private Spinner spinner;
    private static final String[]paths = {"EU West", "EU East"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_region);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add_Player.this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.y = (int)(width*.1);

        getWindow().setLayout((int)(width*.8), (int)(height*.3));
        getWindow().setGravity(Gravity.TOP);
        getWindow().setAttributes(params);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:
                region = "euw";
                break;
            case 1:
                region = "eue";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Select Region!", Toast.LENGTH_LONG).show();
    }

    public void add_player(View v) throws InterruptedException {
        username = (EditText) findViewById(R.id.edit_player);
        if(!(playerExists(username.getText().toString()))){
            asy_player_result.callPlayer(username.getText().toString(), region);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Already Exists!", Toast.LENGTH_LONG).show();
            username.setText("");
        }
    }

    public static boolean playerExists(String thisPlayer){
        int playerCount = Players.players.size();
        for (int i = 0; i < playerCount; i++) {
            if (thisPlayer.compareToIgnoreCase(Players.players.get(i).getSummonname()) == 0) {
                return true;
            }
        }
        return false;
    }
}
