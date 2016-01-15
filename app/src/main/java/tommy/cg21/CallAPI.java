package tommy.cg21;

import tommy.cg21.Database.PlayerDB;
import tommy.cg21.Objects.Player;
import tommy.cg21.API.GetPlayer;

public class CallAPI {

    PlayerDB playerDB;
    GetPlayer getPlayer = new GetPlayer();
    public static String key = "a7c084f1-8ec6-4e3e-840b-085cbde654a1";

    public static void callChampions(int id){

    }

    public void callPlayer(String username, String region){
        String summonname = username.replaceAll(" ", "%20");
        String url = "https://euw.api.pvp.net/api/lol/" + region + "/v1.4/summoner/by-name/" + summonname + "?api_key=" + key;
        getPlayer.execute(url);
    }

    public void processPlayer(Player result) {
        playerDB.addUser(result);
    }

    public static void callGame(int id){

    }
}
