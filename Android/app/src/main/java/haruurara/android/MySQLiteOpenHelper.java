package haruurara.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Takayuki on 2017/10/22.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Users";
    private static final int DB_VERSION = 1;
    private static final String _ID = "_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_IMAGE = "user_image";

    public MySQLiteOpenHelper(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_NAME + " TEXT NOT NULL,"
                + USER_IMAGE + " BLOB NOT NULL"
                + ");" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE" + DB_NAME + ";");
        onCreate(db);
    }
}
