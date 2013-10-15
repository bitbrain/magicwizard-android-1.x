package myreality.development.magicwizard.util;

import android.os.Bundle;

/**
 * Provides functionality to write and load from bundles
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 *
 */
public interface BundleIO {

	/**
	 * Saves to a bundle
	 * 
	 * @param bundle
	 */
	void save(Bundle bundle);
	
	/**
	 * Loads to a bundle
	 * 
	 * @param bundle
	 */
	void load(Bundle bundle);
}
