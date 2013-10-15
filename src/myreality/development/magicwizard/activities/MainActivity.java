package myreality.development.magicwizard.activities;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.components.ComponentHandler;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
public class MainActivity extends MagicActivity implements OnMenuItemClickListener {

	// Target bundle to save data with
	private Bundle bundle;

	// Typeface TTF font (Fritz bold)
	private Typeface typeface;

	private ComponentHandler handler;
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		// Load the target font from assets
		handler = new ComponentHandler();
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
		handler.handle(view.getId(), this);
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

	private void reloadPreferences() {
		clear();
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
		reset();
		load(bundle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myreality.development.magicwizard.activities.MagicActivity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		save(bundle);
	}

	@Override
	protected void onRestoreInstanceState(Bundle bundle) {
		super.onRestoreInstanceState(bundle);
		this.bundle = bundle;
		reloadPreferences();
		load(bundle);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		this.bundle = outState;
		save(bundle);
	}
	
	public void showMenuPopup(View view) {
		PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
	      popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
	    
	      popupMenu.setOnMenuItemClickListener((OnMenuItemClickListener) this);
	    
	      popupMenu.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.opt_reset:
				reset();
				break;
			case R.id.opt_preferences:
				showPreferences();
				break;
			case R.id.opt_information:
				showAppInfo();
				break;
			case R.id.opt_rate:
				rateApp();
				break;
			case R.id.opt_close:
				finish();
				break;
		}

		return super.onOptionsItemSelected(item);
	}
}