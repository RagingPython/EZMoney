package ragingpython.ezmoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

}
