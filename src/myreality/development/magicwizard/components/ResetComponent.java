package myreality.development.magicwizard.components;

import myreality.development.magicwizard.util.MagicToast;
import myreality.development.magicwizard.util.MagicToast.ToastType;
import myreality.development.magicwizard.util.Resetable;
import android.app.Activity;
import android.view.View;

/**
 * Handles the resetting behavior for context
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 *
 */
public class ResetComponent implements Component {

	@Override
	public void handle(Activity context, View sender) {
		if (context instanceof Resetable) {
			Resetable resetable = (Resetable)context;
			resetable.reset();

			MagicToast.show(context, "Successfully resetted", ToastType.SUCCESS);
		}
	}

	@Override
	public void onActivity(Activity context) {
		// TODO Auto-generated method stub
		
	}

}
