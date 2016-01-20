package tommy.cg21.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tommy.cg21.Objects.Obj_Player;

public class Dat_Player extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "player.db",
            TABLE_USERS = "player",
            COLUMN_SUMMONNAME = "_summonname",
            COLUMN_REGION = "_region",
            COLUMN_ICONURI = "_iconuri",
            COLUMN_SUMLEVEL = "_summonerLevel",
            COLUMN_SUMID = "_summonerID",
            COLUMN_BG = "_bg";

    public Dat_Player(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_SUMMONNAME + " TEXT, " +
                COLUMN_REGION + " TEXT, " +
                COLUMN_ICONURI + " TEXT, " +
                COLUMN_SUMLEVEL + " INTEGER, " +
                COLUMN_SUMID + " INTEGER, " +
                COLUMN_BG + " TEXT " +
                ");";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(Obj_Player player) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SUMMONNAME, player.getSummonname());
        contentValues.put(COLUMN_REGION, player.getRegion());
        contentValues.put(COLUMN_ICONURI, player.getIcon());
        contentValues.put(COLUMN_SUMLEVEL, player.getSummonerLevel());
        contentValues.put(COLUMN_SUMID, player.getSummonerID());
        contentValues.put(COLUMN_BG, player.getBG());

        db.insert(TABLE_USERS, null, contentValues);
        db.close();
    }

    public List<Obj_Player> getAllPlayers(){
        List<Obj_Player> players = new ArrayList<Obj_Player>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        if (c.moveToFirst()) {
            do {
                players.add(new Obj_Player(c.getString(0), c.getString(1), Integer.parseInt(c.getString(2))
                        , Integer.parseInt(c.getString(3)), Integer.parseInt(c.getString(4)), c.getString(5)));
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return players;
    }

    public int getUserCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        int count = c.getCount();
        db.close();
        c.close();

        return count;
    }

    public void deleteUser(String i) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_SUMID + "=" + i, null);
        db.close();
    }

    public Obj_Player getPlayer(int i){
        Obj_Player getPlayer;
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        c.moveToPosition(i);

        getPlayer = new Obj_Player(c.getString(0), c.getString(1), Integer.parseInt(c.getString(2))
                , Integer.parseInt(c.getString(3)), Integer.parseInt(c.getString(4)), c.getString(5));

        db.close();
        c.close();
        return getPlayer;
    }
}