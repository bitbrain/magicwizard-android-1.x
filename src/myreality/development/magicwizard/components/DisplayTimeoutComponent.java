package myreality.development.magicwizard.components;

import myreality.development.magicwizard.util.MagicToast;
import myreality.development.magicwizard.util.MagicToast.ToastType;
import myreality.development.magicwizard.widgets.StateButton;
import android.app.Activity;
import android.view.View;

/**
 * Handles display timeout
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class DisplayTimeoutComponent implements Component {

	@Override
	public void handle(Activity activity, View sender) {
		
		if (sender instanceof StateButton) {
			StateButton button = (StateButton)sender;
			
			if (button.isStateEnabled()) {
				MagicToast.show(activity, "Display timeout disabled", ToastType.FAIL);
			} else {
				MagicToast.show(activity, "Display timeout enabled", ToastType.SUCCESS);
			}
		}
	}

	@Override
	public void onActivity(Activity context) {
		// TODO Auto-generated method stub
		
	}

}
