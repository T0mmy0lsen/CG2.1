package tommy.cg21.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import tommy.cg21.API.Asy_Participants_Result;
import tommy.cg21.Objects.Obj_Participants;
import tommy.cg21.R;

public class Current extends AppCompatActivity {

    Asy_Participants_Result getPlayerLiveData = new Asy_Participants_Result();
    public static TextView idfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        Bundle getID = getIntent().getExtras();
        if( getID == null ){ return; }
        int id = getID.getInt("ID");
        idfield = (TextView) findViewById(R.id.idfield);
        idfield.setText("" + id);
        getPlayerLiveData.callPlayerLive(id);
    }

    public static void updateScreen(List<Obj_Participants> list){
        Obj_Participants participant = list.get(0);

        idfield.setText("Done " + participant.getteam());
    }
}
