package myreality.development.magicwizard.components;

import android.app.Activity;

/**
 * Handles a specific behavior
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public interface Component {

	/**
	 * Handles a specific context
	 * 
	 * @param context
	 */
	void handle(Activity context);
}
