package myreality.development.magicwizard.activities;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.components.ComponentHandler;
import myreality.development.magicwizard.components.ComponentHandlerFactory;
import myreality.development.magicwizard.components.SimpleComponentHandlerFactory;
import android.os.Bundle;
import android.view.View;

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

	private SlidingMenu menu;
	
	private ComponentHandler handler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.main);

		ComponentHandlerFactory handlerFactory = new SimpleComponentHandlerFactory(this);
		handler = handlerFactory.create();
		this.bundle = bundle;
		
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.LEFT);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);		
		menu.setBehindWidthRes(R.dimen.menu_width);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT); 
		menu.setMenu(R.layout.menu);
	}
	
	public void onButtonClick(View view) {
		handler.handle(view.getId(), this);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
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
		load(bundle);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		this.bundle = outState;
		save(bundle);
	}
}