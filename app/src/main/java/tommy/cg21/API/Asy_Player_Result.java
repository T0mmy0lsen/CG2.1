package tommy.cg21.API;

import android.widget.Toast;

import tommy.cg21.Activity.Players;
import tommy.cg21.Interface.Int_Player;
import tommy.cg21.Main;
import tommy.cg21.Objects.Obj_Player;

public class Asy_Player_Result implements Int_Player {

    Asy_Player getPlayer = new Asy_Player();
    Asy_Player_Stats_Result asy_player_stats_result = new Asy_Player_Stats_Result();

    public static String key = "a7c084f1-8ec6-4e3e-840b-085cbde654a1";
    String name;
    String reg;
    String bg = "teemo";

    public void callPlayer(String username, String region){
        name = username;
        reg = region;
        String summonname = username.replaceAll(" ", "%20");
        String url = "https://euw.api.pvp.net/api/lol/" + region + "/v1.4/summoner/by-name/" + summonname + "?api_key=" + key;
        getPlayer.delegate = this;
        getPlayer.execute(url);
    }

    public void processPlayer(Obj_Player result) {
        try {
            Main.playerDB.addUser(new Obj_Player(name,reg,result.getIcon(),result.getSummonerLevel(),result.getSummonerID(),bg));
            asy_player_stats_result.callPlayer_Stats(result.getSummonerID(),reg);
        } catch (NullPointerException e) {
            //Ignore
        }
    }
}
