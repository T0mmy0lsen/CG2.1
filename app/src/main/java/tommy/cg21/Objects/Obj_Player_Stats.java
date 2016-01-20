package tommy.cg21.Objects;

public class Obj_Player_Stats {

    private int _id, _mostplayed;

    public Obj_Player_Stats(int id, int mostplayed) {
        _id = id;
        _mostplayed = mostplayed;
    }

    public int getid(){
        return _id;
    }

    public int getmostplayed(){
        return _mostplayed;
    }

}