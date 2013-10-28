package myreality.development.magicwizard.components;

import myreality.development.magicwizard.components.core.PreferencesComponent;
import myreality.development.magicwizard.util.MagicToast;
import myreality.development.magicwizard.util.MagicToast.ToastType;
import myreality.development.magicwizard.widgets.StateButton;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.WindowManager;

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
		setDisplayTimeout(context, isEnabled());
	}
	
	
	
	@Override
	public void onDestroy(Activity context) {
		super.onDestroy(context);
		setDisplayTimeout(context, false);
	}



	@Override
	public void onPause(Activity context) {
		super.onPause(context);
		setDisplayTimeout(context, false);
	}



	@Override
	public void onResume(Activity context) {
		super.onResume(context);
		setDisplayTimeout(context, isEnabled());
	}



	@Override
	public void handle(Activity activity, View sender) {
		
		if (sender instanceof StateButton) {
			
			StateButton button = (StateButton)sender;
			
			setDisplayTimeout(activity, button.isStateEnabled());
			saveToPreferences(activity, button.isStateEnabled());
			
			if (button.isStateEnabled()) {
				MagicToast.show(activity, "Display timeout enabled", ToastType.SUCCESS);
			} else {
				MagicToast.show(activity, "Display timeout disabled", ToastType.FAIL);
			}
		}
	}
	
	public boolean isEnabled() {
		if (getPreferences() != null) {
			return getPreferences().getBoolean(DISPLAY_TIMEOUT, false);
		} else {
			return false;
		}
	}
	
	private void saveToPreferences(Activity context, Boolean state) {
		SharedPreferences settings = getPreferences();		
		Editor editor = settings.edit();
        editor.putBoolean(DISPLAY_TIMEOUT, state);
        editor.commit();
	}
	
	private void setDisplayTimeout(Activity context, boolean state) {
		if (state) {
			context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		} else {
			context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
	}

}
