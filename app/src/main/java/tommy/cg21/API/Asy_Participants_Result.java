package tommy.cg21.API;

import java.util.List;


import tommy.cg21.Activity.Current;
import tommy.cg21.Interface.Int_Participants;
import tommy.cg21.Objects.Obj_Participants;

public class Asy_Participants_Result implements Int_Participants {

    Asy_Participants getPlayerLive = new Asy_Participants();
    public static String key = "a7c084f1-8ec6-4e3e-840b-085cbde654a1";

    public void callPlayerLive(int id){
        String url = "https://euw.api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/EUW1/" + id + "?api_key=" + key;
        getPlayerLive.delegate = this;
        getPlayerLive.execute(url);
    }

    public void processPlayerLive(List<Obj_Participants> result) {
        Current.updateScreen(result);
    }
}
