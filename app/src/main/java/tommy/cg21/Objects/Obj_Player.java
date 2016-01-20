package tommy.cg21.Objects;

public class Obj_Player {

    private String _summonname, _region, _bg;
    private int _summonerLevel, _summonerID, _icon;

    public Obj_Player(String summonname, String region, int icon, int summonerLevel, int summonerID, String bg) {
        _summonname = summonname;
        _region = region;
        _icon = icon;
        _summonerLevel = summonerLevel;
        _summonerID = summonerID;
        _bg = bg;
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

    public String getBG() {
        return _bg;
    }
}
