package myreality.development.magicwizard.components;

import android.app.Activity;
import android.view.View;

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
	public void onCreate(Activity context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy(Activity context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause(Activity context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(Activity context, View sender) {
		context.finish();
	}

}
