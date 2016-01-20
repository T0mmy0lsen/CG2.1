package tommy.cg21.API;

import android.os.AsyncTask;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import tommy.cg21.Interface.Int_Player;
import tommy.cg21.Objects.Obj_Player;

public class Asy_Player extends AsyncTask<String, String, Obj_Player> {

    Obj_Player player;
    JSONObject jsonRootObject = new JSONObject();
    JSONObject jsonObject = new JSONObject();
    StringBuilder jsonStringBuilder = new StringBuilder();
    String name, jsonLine;
    int summonerLevel, summonerID, icon;

    protected Obj_Player doInBackground(String...urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlC = (HttpURLConnection) url.openConnection();
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(urlC.getInputStream()));
            while ((jsonLine = jsonReader.readLine()) != null) {
                jsonStringBuilder.append(jsonLine);
            }

            jsonRootObject = new JSONObject(jsonStringBuilder.toString());

            Iterator<String> keys = jsonRootObject.keys();
            if( keys.hasNext() ){
                name = keys.next();
            }

            String root = jsonRootObject.optString(name);
            jsonObject = new JSONObject(root);
            summonerID = jsonObject.optInt("id");
            icon = jsonObject.optInt("profileIconId");
            summonerLevel = jsonObject.optInt("summonerLevel");

            player = new Obj_Player(name, "euw", icon, summonerLevel, summonerID, "teemo");

        } catch (Exception e) {
            return player;
        }
        return player;
    }

    public Int_Player delegate = null;

    protected void onPostExecute(Obj_Player player) {
        delegate.processPlayer(player);
    }
}
