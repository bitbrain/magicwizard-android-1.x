package myreality.development.magicwizard.old;
/*package myreality.development.magicwizard.old;

import myreality.development.magicwizard.R;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SimpleCursorAdapter;

public class CardlistActivity extends ListActivity {

	private SQLiteDatabase mDatabase;
	private DatabaseManager mHelper;
	private ProgressDialog progDialog;
	private final Handler handler = new Handler() {
		   public void handleMessage(Message msg) {
			   progDialog.dismiss();
		      }
	};
	
	private Thread checkUpdate = new Thread() {  
		   public void run() {
			   
		      handler.sendEmptyMessage(0);      
		      }
	};
	
	private static final String CARD_SELECT_RAW =
			"SELECT * FROM cards";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cardlist);
		mHelper = new DatabaseManager(this);
		mDatabase = mHelper.getReadableDatabase();
		downloadData();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mDatabase.close();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		mDatabase = mHelper.getReadableDatabase();
		
		displayData();
	}
	
	private void displayData()
	{
		
		Cursor cursor = mDatabase.rawQuery(CARD_SELECT_RAW, null);
		startManagingCursor(cursor);
		
		SimpleCursorAdapter adapter =
				new SimpleCursorAdapter(this,
						android.R.layout.simple_list_item_1,
						cursor,
						new String[] {"name"}, 
						new int[] {android.R.id.text1
						}
				);
		
		setListAdapter(adapter);
	}
	
	private void downloadData()
	{
		progDialog = ProgressDialog.show(this, "Bitte Warten", "Suche neue Karten...", true, false);
		checkUpdate.start();
	}
} */
