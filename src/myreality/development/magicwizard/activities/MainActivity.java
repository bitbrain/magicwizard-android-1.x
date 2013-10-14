package myreality.development.magicwizard.activities;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.util.Reloadable;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

/**
 * Main activity of the application
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 1.1
 * @since 1.0
 */
public class MainActivity extends MagicActivity {

	// Target bundle to save data with
	private Bundle bundle;

	// Typeface TTF font (Fritz bold)
	private Typeface typeface;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		// Load the target font from assets
		typeface = Typeface.createFromAsset(getAssets(), "fonts/fritzbold.ttf");
		this.bundle = bundle;
		reloadPreferences();

	}

	/**
	 * Is called when a button of the main menu has been pressed
	 * 
	 * @param view
	 *            Target button
	 */
	public void onButtonClick(View view) {
		switch (view.getId()) {
		case R.id.btn_reset:
			reset();
			break;
		case R.id.btn_preferences:
			showPreferences();
			break;
		case R.id.btn_info:
			showAppInfo();
			break;
		case R.id.btn_rate:
			rateApp();
			break;
		case R.id.btn_close:
			finish();
			break;
		}
	}

	private void rateApp() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri
				.parse("market://details?id=myreality.development.magicwizard"));
		startActivity(intent);
	}

	private void showAppInfo() {
		Builder builder = new Builder(this);
		AlertDialog alert = builder
				.setView(
						getLayoutInflater().inflate(R.layout.information, null))
				.setCustomTitle(
						getLayoutInflater().inflate(R.layout.information_title,
								null))
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
	}

	private void showPreferences() {
		Intent intent = new Intent(this, Preferences.class);
		startActivity(intent);
	}

	private void reset() {
		for (Reloadable reloadable : reloadables) {
			reloadable.onReload(this, true);
		}
	}

	private void reloadPreferences() {
		reloadables.clear();
		if (isSinglePlayerMode()) {
			setContentView(R.layout.main);
		} else {
			setContentView(R.layout.main_two_players);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		reloadPreferences();
		for (Reloadable reloadable : reloadables) {
			reloadable.onReload(this, true);
		}
		loadFromBundle(bundle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myreality.development.magicwizard.activities.MagicActivity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		saveToBundle(bundle);
	}

	@Override
	protected void onRestoreInstanceState(Bundle bundle) {
		super.onRestoreInstanceState(bundle);
		this.bundle = bundle;
		reloadPreferences();
		loadFromBundle(bundle);
	}

	private void loadFromBundle(Bundle bundle) {
		if (bundle != null) {
			for (Reloadable reloadable : reloadables) {
				reloadable.loadFromBundle(bundle);
			}
		}
	}

	private void saveToBundle(Bundle bundle) {
		if (bundle != null) {
			for (Reloadable reloadable : reloadables) {
				reloadable.saveToBundle(bundle);
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		this.bundle = outState;
		saveToBundle(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.life_counter_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Builder builder = null;

		switch (item.getItemId()) {
		case R.id.opt_reset:
			reset();
			break;
		case R.id.opt_preferences:
			Intent intent = new Intent(this, Preferences.class);
			startActivity(intent);
			break;
		case R.id.opt_information:
			builder = new Builder(this);
			builder.setView(
					getLayoutInflater().inflate(R.layout.information, null))
					.setTitle(R.string.opt_info)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton(R.string.sf_ok, null).show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}