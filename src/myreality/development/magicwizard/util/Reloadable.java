package myreality.development.magicwizard.util;

import android.app.Activity;
import android.os.Bundle;

/**
 * Reloadable entity
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 */
public interface Reloadable {

	void onReload(Activity from, boolean reset);
	
	void saveToBundle(Bundle bundle);
	
	void loadFromBundle(Bundle bundle);
	
	
}
