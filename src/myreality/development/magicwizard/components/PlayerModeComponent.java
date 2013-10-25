package myreality.development.magicwizard.components;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.util.MagicToast;
import myreality.development.magicwizard.util.MagicToast.ToastType;
import myreality.development.magicwizard.widgets.StateButton;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;

/**
 * Handles player mode
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class PlayerModeComponent extends PreferencesComponent {
	
	private static final String TWO_PLAYER_MODE = "TWO_PLAYER_MODE";

	@Override
	public void onCreate(Activity context) {
		super.onCreate(context);
		setTwoPlayerMode(context, isTwoPlayerMode());
	}

	@Override
	public void handle(Activity context, View sender) {
		
		if (sender instanceof StateButton) {
					
			StateButton button = (StateButton)sender;
			
			setTwoPlayerMode(context, button.isStateEnabled());
			saveToPreferences(context, button.isStateEnabled());
			
			if (button.isStateEnabled()) {
				MagicToast.show(context, "Two player mode enabled", ToastType.INFO);
			} else {
				MagicToast.show(context, "Single player mode enabled", ToastType.INFO);
			}
		}
	}

	private void setTwoPlayerMode(Activity context, boolean twoPlayerMode) {

		if (twoPlayerMode) {
			context.setContentView(R.layout.main_two_players);
		} else {
			context.setContentView(R.layout.main);
		}
		
	}
	
	public boolean isTwoPlayerMode() {
		if (getPreferences() != null) {
			return getPreferences().getBoolean(TWO_PLAYER_MODE, false);
		} else {
			return false;
		}
	}
	
	private void saveToPreferences(Activity context, boolean twoPlayerMode) {
		SharedPreferences settings = getPreferences();		
		Editor editor = settings.edit();
        editor.putBoolean(TWO_PLAYER_MODE, twoPlayerMode);
        editor.commit();
	}

}
