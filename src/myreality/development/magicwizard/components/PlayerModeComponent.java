package myreality.development.magicwizard.components;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.activities.MainActivity;
import android.app.Activity;
import android.view.View;

/**
 * Handles player mode
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class PlayerModeComponent extends PreferencesComponent {

	@Override
	public void onCreate(Activity context) {
		super.onCreate(context);

	}

	@Override
	public void handle(Activity context, View sender) {

	}

	private void setTwoPlayerMode(Activity context, boolean twoPlayerMode) {
		if (context instanceof MainActivity) {
			MainActivity activity = (MainActivity) context;

			if (twoPlayerMode) {
				activity.getMenu().setContent(R.layout.main_two_players);
			} else {
				activity.getMenu().setContent(R.layout.main);
			}
		}
	}

}
