package tommy.cg21.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import tommy.cg21.Objects.Obj_Player_Stats;

public class Dat_Player_Stats extends SQLiteOpenHelper {

    Obj_Player_Stats getPlayer_stats;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "player_stats.db",
            TABLE_USERS = "player_stats",
            COLUMN_SUMID = "_id",
            COLUMN_MOSTPLAYED = "_mostplayed";

    public Dat_Player_Stats(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_SUMID + " INTEGER, " +
                COLUMN_MOSTPLAYED + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(Obj_Player_Stats player_stats) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SUMID, player_stats.getid());
        contentValues.put(COLUMN_MOSTPLAYED, player_stats.getmostplayed());

        db.insert(TABLE_USERS, null, contentValues);

        db.close();
    }

    public Obj_Player_Stats getPlayer_stats(int i){
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE _id=" + i,null);
        c.moveToFirst();

        try{
            getPlayer_stats = new Obj_Player_Stats(c.getInt(0), c.getInt(1));
            db.close();
            c.close();
            return getPlayer_stats;
        } catch (CursorIndexOutOfBoundsException e){
            //
        }

        db.close();
        c.close();
        return getPlayer_stats;
    }

    public void deleteUser(int i) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_SUMID + "=" + i, null);
        db.close();
    }
}
