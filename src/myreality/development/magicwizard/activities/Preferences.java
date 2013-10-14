package myreality.development.magicwizard.activities;

import myreality.development.magicwizard.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences settings =
				getSharedPreferences(
						getPackageName() + "_preferences", MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString("lastActivity", getClass().getSimpleName());
		editor.commit();
	}

	
	
	
	
	
}
