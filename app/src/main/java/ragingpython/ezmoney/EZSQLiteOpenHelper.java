package ragingpython.ezmoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EZSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_WALLETS = "create table wallets(id integer primary key asc autoincrement, name text)";
    private static final String DB_CREATE_OPERATIONS = "create table operations(id integer primary key asc autoincrement, id_from integer references wallets(id) on delete set null, id_to integer references wallets(id) on delete set null, amount float)";


    private static final String DB_NAME = "DataStorage";

    public EZSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(DB_CREATE_WALLETS);
        db.execSQL(DB_CREATE_OPERATIONS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
