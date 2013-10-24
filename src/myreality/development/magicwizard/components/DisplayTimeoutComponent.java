package myreality.development.magicwizard.components;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class DisplayTimeoutComponent implements Component {

	@Override
	public void handle(Activity activity, View sender) {
		Log.d("IT WORKS", "MUAHAHA");
	}

}
