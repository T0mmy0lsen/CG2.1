package tommy.cg21.API;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tommy.cg21.Interface.Int_Player;
import tommy.cg21.Interface.Int_Player_Stats;
import tommy.cg21.Objects.Obj_Player;
import tommy.cg21.Objects.Obj_Player_Stats;

public class Asy_Player_Stats extends AsyncTask<String, String, List<Obj_Player_Stats>> {

    Obj_Player_Stats player_stats;
    Obj_Player_Stats player_stats_temp;
    List<Obj_Player_Stats> list = new ArrayList<Obj_Player_Stats>();
    JSONObject jsonRootObject = new JSONObject();
    JSONObject jsonObject = new JSONObject();
    JSONObject tempObject = new JSONObject();
    JSONObject tempObject2 = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    StringBuilder jsonStringBuilder = new StringBuilder();
    String jsonLine;
    int id, mostplayed, number_played = 0, pre_number_played, position_most_played, position_id, champ_id;

    protected List<Obj_Player_Stats> doInBackground(String...urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlC = (HttpURLConnection) url.openConnection();
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(urlC.getInputStream()));
            while ((jsonLine = jsonReader.readLine()) != null) {
                jsonStringBuilder.append(jsonLine);
            }

            jsonRootObject = new JSONObject(jsonStringBuilder.toString());

            jsonArray = jsonRootObject.optJSONArray("champions");
            for (int i = 0; i < jsonArray.length(); i++) {
                tempObject = jsonArray.optJSONObject(i);
                id = tempObject.getInt("id");
                tempObject2 = tempObject.getJSONObject("stats");
                mostplayed = tempObject2.getInt("totalSessionsPlayed");
                list.add(new Obj_Player_Stats(id,mostplayed));
            }

        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public Int_Player_Stats delegate = null;

    protected void onPostExecute(List<Obj_Player_Stats> result) {
        for (int i = 0; i < result.size(); i++){
            if (!(result.get(i).getid() == 0)) {
                number_played = result.get(i).getmostplayed();
                if (number_played > pre_number_played){
                    pre_number_played = number_played;
                    position_most_played = i;
                }
            }
        }

        delegate.processPlayer_Stats(result.get(position_most_played));
    }
}
