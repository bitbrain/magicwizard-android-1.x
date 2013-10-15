package myreality.development.magicwizard.components;

import myreality.development.magicwizard.activities.Preferences;
import android.app.Activity;
import android.content.Intent;

/**
 * Component for displaying the preferences
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 *
 */
public class PreferencesComponent implements Component {

	@Override
	public void handle(Activity context) {
		Intent intent = new Intent(context, Preferences.class);
		context.startActivity(intent);
	}

	
}
