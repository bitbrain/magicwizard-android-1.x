package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.activities.MagicActivity;
import myreality.development.magicwizard.layouts.FlipLayout;
import myreality.development.magicwizard.util.Reloadable;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class CounterBar extends FlipLayout implements Reloadable {

	protected ValueBar valueBar;
	
	protected Vibrator vibrator;
	
    private LayoutInflater inflater;

	public CounterBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout(context);
		loadFromAttributes(context, attrs);
	}

	public CounterBar(Context context) {
		super(context);
		initLayout(context);
	}
	
	
	protected void setRuleDefault(int value) {
		valueBar.clearRules();
		valueBar.setMaximum(value);
	}
	
	public void plus() {
		valueBar.plus();
		vibrator.vibrate(5);
	}
	
	public void minus() {
		valueBar.minus();
		vibrator.vibrate(5);
	}
	
	public void setValue(int value) {
		valueBar.setValue(value);
	}
	
	public int getValue() {
		return valueBar.getValue();
	}
	
	private void initLayout(Context context) {
		
		if (context instanceof MagicActivity) {
			((MagicActivity) context).addReloadable(this);			
		}
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		addView(inflater.inflate(R.layout.counterbar, null));
		vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
		valueBar = (ValueBar) findViewById(R.id.vb_value);
		// Listener
		ImageButton btnLeft = (ImageButton) findViewById(R.id.btn_count_left);
		btnLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				minus();
			}			
		});
		ImageButton btnRight = (ImageButton) findViewById(R.id.btn_count_right);
		btnRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				plus();
			}			
		});
	}
	
	public void reset(Activity activity) {
		
	}

	@Override
	public void onReload(Activity from, boolean reset) {
		if (reset) {
			reset(from);		
		}
	}

	@Override
	public void saveToBundle(Bundle bundle) {
		bundle.putInt(String.valueOf(getId()), getValue());
	}

	@Override
	public void loadFromBundle(Bundle bundle) {
		setValue(bundle.getInt(String.valueOf(getId())));
	}
	
	protected void loadFromAttributes(Context context, AttributeSet attrs) {
		
		TypedArray a = context.obtainStyledAttributes(attrs,
			    R.styleable.CounterBar);
		
		ImageButton btnLeft = (ImageButton) this.findViewById(R.id.btn_count_left);
		ImageButton btnRight = (ImageButton) this.findViewById(R.id.btn_count_right);	

		final int N = a.getIndexCount();

		for (int i = 0; i < N; ++i)
		{
		    int attr = a.getIndex(i);
		    
		    switch (attr) {
		        case R.styleable.CounterBar_buttonSize:
		        	// Set a new button size
		        	float buttonSize = a.getDimension(attr, 28.0f);		        		        	
		        	ViewGroup.LayoutParams leftParams = btnLeft.getLayoutParams();
		        	ViewGroup.LayoutParams rightParams = btnRight.getLayoutParams();
		        	
		        	leftParams.width = (int) buttonSize;
		        	rightParams.width = (int) buttonSize;
		        	break;
		        case R.styleable.CounterBar_fontSize:
		        	int fontSize = a.getInteger(attr, 28);	
		        	valueBar.setFontSize(fontSize);
		        	break;
		        case R.styleable.CounterBar_minusIcon:
		        	int minusId = a.getResourceId(attr, R.drawable.ico_minus);
		        	btnLeft.setImageResource(minusId);
		        	break;
		        case R.styleable.CounterBar_plusIcon:
		        	int plusId = a.getResourceId(attr, R.drawable.ico_plus);
		        	btnRight.setImageResource(plusId);
		        	break;
		        	
		    }
		}
		
		a.recycle();
	}
}
