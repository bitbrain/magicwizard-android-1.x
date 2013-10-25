package myreality.development.magicwizard.components;

import myreality.development.magicwizard.util.MagicToast;
import myreality.development.magicwizard.util.MagicToast.ToastType;
import myreality.development.magicwizard.widgets.StateButton;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;

/**
 * Handles display timeout
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class DisplayTimeoutComponent extends PreferencesComponent {
	
	private static final String DISPLAY_TIMEOUT = "DISPLAY_TIMEOUT";

	@Override
	public void onCreate(Activity context) {
		super.onCreate(context);
		loadFromPreferences(context);
	}	

	@Override
	public void handle(Activity activity, View sender) {
		
		if (sender instanceof StateButton) {
			
			StateButton button = (StateButton)sender;
			
			saveToPreferences(activity, button.isStateEnabled());
			
			if (button.isStateEnabled()) {
				MagicToast.show(activity, "Display timeout enabled", ToastType.SUCCESS);
			} else {
				MagicToast.show(activity, "Display timeout disabled", ToastType.FAIL);
			}
		}
	}
	
	private void loadFromPreferences(Activity context) {
		SharedPreferences settings = getPreferences();
	}
	
	private void saveToPreferences(Activity context, Boolean state) {
		SharedPreferences settings = getPreferences();		
		Editor editor = settings.edit();
        editor.putBoolean(DISPLAY_TIMEOUT, state);
        editor.commit();
	}

}
