package tommy.cg21.Objects;

public class Obj_Participants {

    private int _spell1Id, _spell2Id, _championId, _profileIconId, _summonerId, _team;
    private String _summonerName;

    public Obj_Participants(int spell1Id, int spell2Id, int championId, int profileIconId, int summonerId, String summonerName, int team){
        _spell1Id = spell1Id;
        _spell2Id = spell2Id;
        _championId = championId;
        _profileIconId = profileIconId;
        _summonerId = summonerId;
        _summonerName = summonerName;
        _team = team;
    }

    public int getSpell1Id() { return _spell1Id;}
    public int getSpell2Id() { return _spell2Id;}
    public int getChampionId() { return _championId;}
    public int getProfileIconId() { return _profileIconId;}
    public int getSummonerId() { return _summonerId;}
    public int getteam() { return _team;}
    public String getSummonerName() { return _summonerName;}

}
