package tommy.cg21.Objects;

public class Player {

    private String _summonname, _region;
    private int _summonerLevel, _summonerID, _icon;

    public Player(String summonname, String region, int icon, int summonerLevel, int summonerID) {
        _summonname = summonname;
        _region = region;
        _icon = icon;
        _summonerLevel = summonerLevel;
        _summonerID = summonerID;
    }

    public String getSummonname() {
        return _summonname; }

    public String getRegion(){
        return _region;
    }

    public int getIcon() {
        return _icon;
    }

    public int getSummonerLevel(){
        return _summonerLevel;
    }

    public int getSummonerID(){
        return _summonerID;
    }
}
