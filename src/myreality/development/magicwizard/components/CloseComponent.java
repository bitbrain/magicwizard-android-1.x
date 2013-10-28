package myreality.development.magicwizard.components;

import myreality.development.magicwizard.components.core.AbstractComponent;
import myreality.development.magicwizard.components.core.Component;
import android.app.Activity;
import android.view.View;

/**
 * Handles the closing of the app
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 * 
 */
public class CloseComponent extends AbstractComponent implements Component {

	@Override
	public void handle(Activity context, View sender) {
		context.finish();
	}

}
