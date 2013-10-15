package myreality.development.magicwizard.components;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Handles the rating of this app
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class RateComponent implements Component {

	@Override
	public void handle(Activity context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri
				.parse("market://details?id=myreality.development.magicwizard"));
		context.startActivity(intent);
	}

}
