package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.Toast;

public class LifeBar extends CounterBar {

	public LifeBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		
	}

	public LifeBar(Context context) {
		super(context);
		init();
	}
	
	public void init(Activity activity) {
		valueBar.setFontSize(78);
		
		if (activity != null) {
			loadFromPreferences(activity, true);
		} else {
			setRuleDefault(20);
			setValue(20);
		}
		
		valueBar.clearRules();
		valueBar.addRule(valueBar.getMaximum() * 4, getResources().getColor(R.color.life_very_much));
		valueBar.addRule(valueBar.getMaximum() * 3, getResources().getColor(R.color.life_much));
		valueBar.addRule(valueBar.getMaximum(), getResources().getColor(R.color.life_full));
		valueBar.addRule(valueBar.getMaximum() / 2, getResources().getColor(R.color.life_medium));
		valueBar.addRule(valueBar.getMaximum() / 4, getResources().getColor(R.color.life_low));
		invalidate();
	}
	
	private void init() {
		init(null);
	}
	
	
	public void loadFromPreferences(Activity activity, boolean reset) {
		SharedPreferences settings =
				activity.getSharedPreferences(
						activity.getPackageName() + "_preferences", Context.MODE_PRIVATE);
		int defaultValue = Integer.valueOf(settings.getString("set_life_points", "20"));

		setRuleDefault(defaultValue);
		//if (reset) {		
			setValue(defaultValue);
		//}
	}	
	
	
	@Override
	public void minus() {
		super.minus();
	}

	@Override
	public void reset(Activity activity) {
		super.reset(activity);
		if (activity != null) {
			init(activity);
		}		
	}

	/* (non-Javadoc)
	 * @see myreality.development.magicwizard.widgets.CounterBar#loadFromBundle(android.os.Bundle)
	 */
	@Override
	public void loadFromBundle(Bundle bundle) {
		super.loadFromBundle(bundle);
		
		if (!bundle.containsKey(String.valueOf(getId()))) {
			setValue(valueBar.getMaximum());
		}
	}
	
	
	
}
