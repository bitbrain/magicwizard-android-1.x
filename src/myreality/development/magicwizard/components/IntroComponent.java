package myreality.development.magicwizard.components;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.util.InfoGenerator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Shows new features on first startup
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class IntroComponent extends PreferencesComponent {
	
	private InfoGenerator generator;
	
	public static final String INTRO_DONE = "INTRO_DONE";
	
	public IntroComponent(Typeface typeface) {
		this.generator = new InfoGenerator(typeface);
	}
	
	@Override
	public void onCreate(Activity context) {
		super.onCreate(context);
		
		if (!isIntroSeen()) {
			show(context);	
		}
	}

	@Override
	public void handle(Activity context, View sender) {
		
	}
	
	private void show(Activity context) {
		AlertDialog view = generator.generate(context);
		TextView txContent = (TextView) view
				.findViewById(R.id.tx_info_content);
		
		final CheckBox checkbox = new CheckBox(context);
		ViewGroup content = (ViewGroup) view.findViewById(R.id.layout_information);	
		content.addView(checkbox);
		checkbox.setText(R.string.opt_startup);
		
		view.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface arg0) {
				if (checkbox.isChecked()) {
					seen();
				}
			}
			
		});
		
		txContent.setText(R.string.app_tipp);
	}
	
	public boolean isIntroSeen() {
		if (getPreferences() != null) {
			return getPreferences().getBoolean(INTRO_DONE, false);
		} else {
			return false;
		}
	}
	
	private void seen() {
		Editor editor = getPreferences().edit();
		editor.putBoolean(INTRO_DONE, true);
		editor.commit();
	}

}
