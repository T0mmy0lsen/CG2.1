package tommy.cg21.API;

import tommy.cg21.Interface.DataPlayer;
import tommy.cg21.Main;
import tommy.cg21.Objects.Player;

public class GetPlayerData implements DataPlayer{

    GetPlayer getPlayer = new GetPlayer();
    public static String key = "a7c084f1-8ec6-4e3e-840b-085cbde654a1";

    public void callPlayer(String username, String region){
        String summonname = username.replaceAll(" ", "%20");
        String url = "https://euw.api.pvp.net/api/lol/" + region + "/v1.4/summoner/by-name/" + summonname + "?api_key=" + key;
        getPlayer.delegate = this;
        getPlayer.execute(url);
    }

    public void processPlayer(Player result) {
        Main.playerDB.addUser(result);
    }
}
