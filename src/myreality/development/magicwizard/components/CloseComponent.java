package myreality.development.magicwizard.components;

import android.app.Activity;

/**
 * Handles the closing of the app
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 *
 */
public class CloseComponent implements Component {

	@Override
	public void handle(Activity context) {
		context.finish();
	}

}
