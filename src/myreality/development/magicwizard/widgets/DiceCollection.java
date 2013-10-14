package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.layouts.FlipLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

/**
 * Contains dices to roll
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.1
 * @version 1.1
 */
public class DiceCollection extends FlipLayout {
	
	private LayoutInflater inflater;

	public DiceCollection(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public DiceCollection(Context context) {
		super(context);
		initView(context);
	}
	
	
	private void initView(Context context) {
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		addView(inflater.inflate(R.layout.dice_collection, null));
	}

	
}
