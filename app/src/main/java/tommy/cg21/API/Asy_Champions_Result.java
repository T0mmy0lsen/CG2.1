package tommy.cg21.API;

import java.util.ArrayList;
import java.util.List;

import tommy.cg21.Activity.Current;
import tommy.cg21.Interface.Int_Champions;
import tommy.cg21.Interface.Int_Participants;
import tommy.cg21.Main;
import tommy.cg21.Objects.Obj_Champions;
import tommy.cg21.Objects.Obj_Participants;

public class Asy_Champions_Result implements Int_Champions {

    List<Obj_Champions> objectChampionList = new ArrayList<Obj_Champions>();
    Asy_Champions getChamp = new Asy_Champions();
    public static String key = "a7c084f1-8ec6-4e3e-840b-085cbde654a1";

    public void callChampion(){
        String url = "https://global.api.pvp.net/api/lol/static-data/euw/v1.2/champion?api_key=" + key;
        getChamp.delegate = this;
        getChamp.execute(url);
    }

    public void processChampions(List<Obj_Champions> result) {
        objectChampionList = result;
        for (int i = 0; i < result.size(); i++){
            Main.championsDB.addChamp(result.get(i));
        }
    }
}