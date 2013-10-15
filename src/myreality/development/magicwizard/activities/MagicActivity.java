package myreality.development.magicwizard.activities;

import java.util.HashSet;

import myreality.development.magicwizard.util.BundleIO;
import myreality.development.magicwizard.util.Clearable;
import myreality.development.magicwizard.util.Reloadable;
import myreality.development.magicwizard.util.Resetable;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Basic activity that provides helpful functions
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 1.1
 * @since 1.0
 */
public class MagicActivity extends Activity implements Resetable, Clearable, BundleIO {
		
	private HashSet<Reloadable> reloadables;
	private SharedPreferences settings;
	
	
	
	
	public MagicActivity() {
		reloadables = new HashSet<Reloadable>();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
	}





	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();		
		Editor editor = settings.edit();
		editor.putString("lastActivity", getClass().getSimpleName());
		editor.commit();
		clearDisplayTimeout();
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		clearDisplayTimeout();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		checkDisplayTimeout();
	}

	/**
	 * @return True, when single player mode is active
	 */
	protected boolean isSinglePlayerMode() {
		return !settings.getBoolean("enable_two_player_mode", false);
	}
	
	
	protected void clearDisplayTimeout() {
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	protected void enableDisplayTimeout() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	protected void checkDisplayTimeout() {
			if (settings.getBoolean("disable_screen_lock", false)) {			
				enableDisplayTimeout();
			} else {
				clearDisplayTimeout();
			}
	}
	
	
	protected String getLastActivity() {
		return settings.getString("lastActivity", "");
	}

	
	public void addReloadable(Reloadable reloadable) {
		reloadables.add(reloadable);
	}

	@Override
	public void reset() {
		for (Reloadable reloadable : reloadables) {
			reloadable.onReload(this, true);
		}
	}

	@Override
	public void clear() {
		reloadables.clear();
	}

	@Override
	public void save(Bundle bundle) {
		
		if (bundle != null) {
		
			for (Reloadable reloadable : reloadables) {
				reloadable.saveToBundle(bundle);
			}
		
		}
	}

	@Override
	public void load(Bundle bundle) {
		
		if (bundle != null) {
		
			for (Reloadable reloadable : reloadables) {
				reloadable.loadFromBundle(bundle);
			}
			
		}
	}
	
	
}
