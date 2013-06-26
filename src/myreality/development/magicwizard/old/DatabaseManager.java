package myreality.development.magicwizard.old;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseManager extends SQLiteOpenHelper {

	// Konstanten
	private static final String DB_NAME = "cardlist.db";
	private static final int DB_VERSION = 1;
	private static final String DB_CREATE = 
			"CREATE TABLE cards (" +
			"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"name TEXT NOT NULL, " +
			"effects TEXT NOT NULL, " +
			"mana TEXT NOT NULL," +
			"image TEXT NOT NULL" +
			")";
	public DatabaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldver, int newver) {
		db.execSQL("DROP TABLE IF EXISTS cards");
		onCreate(db);
	}

}
