package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.R;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;

public class PoisonBar extends CounterBar {

	public PoisonBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public PoisonBar(Context context) {
		super(context);
		init(context, null);
	}
	
	public void init(Context context, AttributeSet attrs) {
		valueBar.setFontSize(98);
		valueBar.addRule(0, getResources().getColor(R.color.poison_low));
		valueBar.addRule(10, getResources().getColor(R.color.poison));
		valueBar.setMaximum(10);
		valueBar.enableLowCap(0);
		
		if (attrs != null) {
			this.loadFromAttributes(context, attrs);
		}
		
		reset(null);
	}
	
	@Override
	public void plus() {
		super.plus();
		if (valueBar.getValue() == 10) {
			vibrator.vibrate(400);
			Toast.makeText(getContext(), "Sie haben verloren!", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void reset(Activity activity) {
		super.reset(activity);
		setValue(0);
	}

}
