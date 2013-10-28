package myreality.development.magicwizard.components;

import myreality.development.magicwizard.util.InfoGenerator;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;

/**
 * Handles showing information for the app
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class InfoComponent extends AbstractComponent implements Component {

	private InfoGenerator generator;

	public InfoComponent(Typeface typeface) {
		this.generator = new InfoGenerator(typeface);
	}

	@Override
	public void handle(Activity context, View sender) {
		generator.generate(context);
	}

}
