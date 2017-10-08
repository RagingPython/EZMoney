package ragingpython.ezmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

public class EZDbContainer {
    private SQLiteDatabase database;
    private EZSQLiteOpenHelper helper;
    private static EZDbContainer instance;

    public static synchronized EZDbContainer getInstance(Context context) {
        if (instance == null) {
            instance = new EZDbContainer(context);
        }
        return instance;
    }

    public EZDbContainer(Context context) {
        helper = new EZSQLiteOpenHelper(context);
        database = helper.getWritableDatabase();
    }

    public long createWallet(String name, float amount){
        ContentValues values = new ContentValues();
        values.put("name", name);
        long newid = database.insert("wallets", null,  values);
        if (amount != 0) {
            createOperation(-1, newid, amount);
        }
        return newid;
    }

    public void removeWallet(long id){
        database.delete("wallets","id = "+ String.valueOf(id),null);
        database.delete("operations","id_from is null and id_to is null",null);
    }

    public Cursor getWallets(){
        return database.query("walletsView",null,null,null,null,null,null);
    }

    public long createOperation(long id_from, long id_to, float amount){
        if ((id_from==-1)&(id_to==-1)) {return -1;}

        ContentValues values = new ContentValues();
        if (id_from!=-1) {values.put("idFrom", id_from);}
        if (id_to!=-1) {values.put("idTo", id_to);}
        values.put("amount", amount);
        return database.insert("operations", null,  values);
    }

    public void removeOperation(long id) {
        database.delete("operations", "id = " + Long.toString(id), null);
    }
    public Cursor getOperations(){
        return database.query("operations",null,null,null,null,null,null);
    }
}
