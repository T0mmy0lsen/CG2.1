package tommy.cg21.API;

import tommy.cg21.Activity.Players;
import tommy.cg21.Interface.Int_Player_Stats;
import tommy.cg21.Main;
import tommy.cg21.Objects.Obj_Player_Stats;

public class Asy_Player_Stats_Result implements Int_Player_Stats {

    Asy_Player_Stats getPlayer_Stats = new Asy_Player_Stats();
    int id_player;

    public static String key = "a7c084f1-8ec6-4e3e-840b-085cbde654a1";

    public void callPlayer_Stats(int id, String region){
        id_player = id;
        String url = "https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.3/stats/by-summoner/" + id + "/ranked?season=SEASON2015&api_key=" + key;
        getPlayer_Stats.delegate = this;
        getPlayer_Stats.execute(url);
    }

    public void processPlayer_Stats(Obj_Player_Stats result) {
        int champ_id = result.getid();
        Main.player_statsDB.addUser(new Obj_Player_Stats(id_player,champ_id));
    }
}
