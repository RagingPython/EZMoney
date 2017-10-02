package ragingpython.ezmoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EZDbConnector {
    private SQLiteDatabase database;
    private EZSQLiteOpenHelper helper;

    public EZDbConnector(Context context) {
        helper = new EZSQLiteOpenHelper(context);
        database = helper.getWritableDatabase();
    }
}
