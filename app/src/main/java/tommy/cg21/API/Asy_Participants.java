package tommy.cg21.API;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tommy.cg21.Interface.Int_Participants;
import tommy.cg21.Objects.Obj_Participants;

public class Asy_Participants extends AsyncTask<String, String, List<Obj_Participants>> {

    List<Obj_Participants> list = new ArrayList<Obj_Participants>();
    JSONObject jsonRootObject = new JSONObject();
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    StringBuilder jsonStringBuilder = new StringBuilder();
    String jsonLine;


    protected List<Obj_Participants> doInBackground(String...urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlC = (HttpURLConnection) url.openConnection();
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(urlC.getInputStream()));
            while ((jsonLine = jsonReader.readLine()) != null) {
                jsonStringBuilder.append(jsonLine);
            }
            jsonRootObject = new JSONObject(jsonStringBuilder.toString());
            jsonArray = jsonRootObject.optJSONArray("participants");
            for (int i = 0; i < 10; i++){
                jsonObject = jsonArray.getJSONObject(i);
                int spell1 = jsonObject.optInt("spell1Id");
                int spell2 = jsonObject.optInt("spell2Id");
                int chId = jsonObject.optInt("championId");
                int piconId = jsonObject.optInt("profileIconId");
                int sId = jsonObject.optInt("summonerId");
                int team = jsonObject.optInt("teamId");
                String sName = jsonObject.optString("summonerName");
                list.add(new Obj_Participants(spell1,spell2,chId,piconId,sId,sName,team));
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public Int_Participants delegate = null;

    protected void onPostExecute(List<Obj_Participants> list) {
        delegate.processPlayerLive(list);
    }
}