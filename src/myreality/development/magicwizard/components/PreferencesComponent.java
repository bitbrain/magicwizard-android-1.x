package myreality.development.magicwizard.components;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Handles preferences
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public abstract class PreferencesComponent implements Component {
	
	private SharedPreferences preferences;

	@Override
	public void onCreate(Activity context) {
		preferences = context.getSharedPreferences(
        		context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
	}

	@Override
	public void onDestroy(Activity context) {
		
	}

	@Override
	public void onPause(Activity context) {
		
	}
	
	public SharedPreferences getPreferences() {
		return preferences;
	}

	
}
