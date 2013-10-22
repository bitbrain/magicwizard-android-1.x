package myreality.development.magicwizard.components;

import myreality.development.magicwizard.util.MagicToast;
import myreality.development.magicwizard.util.MagicToast.ToastType;
import myreality.development.magicwizard.util.Resetable;
import android.app.Activity;

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
	public void handle(Activity context) {
		if (context instanceof Resetable) {
			Resetable resetable = (Resetable)context;
			resetable.reset();
			
			MagicToast.show(context, "Resetted game", ToastType.SUCCESS);
		}
	}

}
