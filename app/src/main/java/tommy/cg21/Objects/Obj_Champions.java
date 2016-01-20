package tommy.cg21.Objects;

public class Obj_Champions {
    private String _name;
    private int _id;

    public Obj_Champions(int id, String name) {
        _id = id;
        _name = name;
    }

    public int getid(){
        return _id;
    }

    public String getname(){
        return _name;
    }

}
