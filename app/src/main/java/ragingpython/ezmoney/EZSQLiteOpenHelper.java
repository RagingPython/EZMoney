package ragingpython.ezmoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EZSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_WALLETS = "create table wallets(id integer primary key asc autoincrement, name text, currency text, type text, status text)";
    private static final String DB_CREATE_OPERATIONS = "create table operations(id integer primary key asc autoincrement, from_wallet integer, to_wallet integer, amount float, date_time text)";
    private static final String DB_CREATE_CATEGORIES = "create table categories(operation_id integer, category_id integer)";
    private static final String DB_CREATE_CATEGORY_TYPES = "create table wallets(id integer primary key asc autoincrement, name text)";

    private static final String DB_NAME = "DataStorage";

    public EZSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(DB_CREATE_WALLETS);
        db.execSQL(DB_CREATE_OPERATIONS);
        db.execSQL(DB_CREATE_CATEGORIES);
        db.execSQL(DB_CREATE_CATEGORY_TYPES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
