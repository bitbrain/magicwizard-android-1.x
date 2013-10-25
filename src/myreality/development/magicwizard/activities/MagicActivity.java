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
		
	public MagicActivity() {
		reloadables = new HashSet<Reloadable>();
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
