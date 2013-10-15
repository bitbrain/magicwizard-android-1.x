package myreality.development.magicwizard.activities;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.components.CloseComponent;
import myreality.development.magicwizard.components.ComponentHandler;
import myreality.development.magicwizard.components.InfoComponent;
import myreality.development.magicwizard.components.PreferencesComponent;
import myreality.development.magicwizard.components.RateComponent;
import myreality.development.magicwizard.components.ResetComponent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

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

	private ComponentHandler handler;

	private SlidingMenu menu;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.main);

		// Load the target font from assets
		handler = new ComponentHandler();
		typeface = Typeface.createFromAsset(getAssets(), "fonts/fritzbold.ttf");
		this.bundle = bundle;

		// Inject behavior
		//handler.add(R.id.btn_reset, new ResetComponent());
		//handler.add(R.id.btn_rate, new RateComponent());
		//handler.add(R.id.btn_close, new CloseComponent());
		//handler.add(R.id.btn_info, new InfoComponent(typeface));
		//handler.add(R.id.btn_preferences, new PreferencesComponent());

		reloadPreferences();
		
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.LEFT);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		// TEST Layout! 
		menu.setMenu(R.layout.menu);
		
		ListView menuList = (ListView) menu.findViewById(R.id.listview);
		

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

	private void reloadPreferences() {
		//clear();
		if (isSinglePlayerMode()) {
			//setContentView(R.layout.main);
		} else {
			//setContentView(R.layout.main_two_players);
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
}