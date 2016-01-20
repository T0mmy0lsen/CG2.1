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

import tommy.cg21.Objects.Obj_Champions;

public class Asy_Champions extends AsyncTask<String, String, List<Obj_Champions>> {

    List<Obj_Champions> championlist = new ArrayList<>();
    JSONObject jsonRootObject = new JSONObject();
    JSONObject jsonObject = new JSONObject();
    JSONObject tempObject = new JSONObject();
    StringBuilder jsonStringBuilder = new StringBuilder();
    String jsonLine;

    protected List<Obj_Champions> doInBackground(String...urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlC = (HttpURLConnection) url.openConnection();
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(urlC.getInputStream()));
            while ((jsonLine = jsonReader.readLine()) != null) {
                jsonStringBuilder.append(jsonLine);
            }
            jsonRootObject = new JSONObject(jsonStringBuilder.toString());

            jsonObject = jsonRootObject.optJSONObject("data");
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String name = keys.next();
                tempObject = jsonObject.optJSONObject(name);
                int id = tempObject.getInt("id");
                championlist.add(new Obj_Champions(id,name));
            }

        } catch (Exception e) {
            return championlist;
        }
        return championlist;
    }

    public Asy_Champions_Result delegate = null;

    @Override
    protected void onPostExecute(List<Obj_Champions> result) {
        delegate.processChampions(result);
    }
}
