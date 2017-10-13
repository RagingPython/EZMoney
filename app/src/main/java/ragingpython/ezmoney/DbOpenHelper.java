package ragingpython.ezmoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_WALLETS = "create table wallets(id integer primary key asc autoincrement, name text)";
    private static final String DB_CREATE_OPERATIONS = "create table operations(id integer primary key asc autoincrement, idFrom integer references wallets(id) on delete set null, idTo integer references wallets(id) on delete set null, amount float)";
    private static final String DB_CREATE_WALLETS_VIEW = "create view walletsView as select w.id id, w.name name, (case when ato.amount is null then 0.0 else ato.amount end - case when afrom.amount is null then 0.0 else  afrom.amount end) balance from wallets w left join (select idFrom, sum(amount) amount from operations where idFrom is not null group by idFrom) as afrom on w.id=afrom.idFrom left join (select idTo, sum(amount) amount from operations where idTo is not null group by idTo) as ato on w.id=ato.idTo";

    private static final String DB_NAME = "DataStorage";

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(DB_CREATE_WALLETS);
        db.execSQL(DB_CREATE_OPERATIONS);
        db.execSQL(DB_CREATE_WALLETS_VIEW);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
