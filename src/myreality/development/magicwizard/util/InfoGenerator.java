package myreality.development.magicwizard.util;

import myreality.development.magicwizard.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Generates info boxes
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class InfoGenerator {
	
	private Typeface typeface;

	public InfoGenerator(Typeface typeface) {
		this.typeface = typeface;
	}
	
	/**
	 * Generates a new info popup and returns the dialog
	 * 
	 * @param context activity context
	 * @return new dialog
	 */
	public AlertDialog generate(Activity context) {
		Builder builder = new Builder(context);
		AlertDialog alert = builder
				.setView(
						context.getLayoutInflater().inflate(
								R.layout.information, null))
				.setCustomTitle(
						context.getLayoutInflater().inflate(
								R.layout.information_title, null))
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton(R.string.sf_ok, null).show();

		// Load the text views
		TextView txCaption = (TextView) alert.findViewById(R.id.tx_info_name);
		TextView txVersionName = (TextView) alert
				.findViewById(R.id.tx_info_version_name);
		TextView txVersionNumber = (TextView) alert
				.findViewById(R.id.tx_info_version_number);
		TextView txContent = (TextView) alert
				.findViewById(R.id.tx_info_content);

		// Change the default font
		txCaption.setTypeface(typeface);
		txVersionName.setTypeface(typeface);
		txVersionNumber.setTypeface(typeface);
		txContent.setTypeface(typeface);
		
		return alert;
	}
}
