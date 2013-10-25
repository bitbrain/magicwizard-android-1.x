package myreality.development.magicwizard.components;

import android.app.Activity;
import android.view.View;

/**
 * Handles a specific behavior
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public interface Component {
	
	
	/**
	 * Is called when added to an activity handler
	 * 
	 * @param context
	 */
	void onCreate(Activity context);
	
	/**
	 * Is called when the target activity gets destroyed
	 * 
	 * @param context
	 */
	void onDestroy(Activity context);
	
	/**
	 * Is called when the target activity gets paused
	 * 
	 * @param context
	 */
	void onPause(Activity context);
	
	/**
	 * Is called when resuming an activity
	 * 
	 * @param context
	 */
	void onResume(Activity context);

	/**
	 * Handles a specific context
	 * 
	 * @param context
	 */
	void handle(Activity context, View sender);
}
