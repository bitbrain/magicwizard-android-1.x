package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * Button which has two drawables for each state
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class StateButton extends ImageButton implements OnClickListener {	
	
	private boolean enabled;
	
	private Integer disabledID, enabledID;
	
	public StateButton(Context context) {
		super(context);
		setOnClickListener(this);
	}

	public StateButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		loadFromAttributes(context, attrs);
		setStateEnabled(isStateEnabled());
		setOnClickListener(this);
	}
	
	
	
	public StateButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		loadFromAttributes(context, attrs);
		setStateEnabled(isStateEnabled());
		setOnClickListener(this);
	}

	public boolean isStateEnabled() {
		return enabled;
	}
	
	public void setStateEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if (enabled && enabledID != null) {
			this.setImageResource(enabledID);
		} else if (!enabled && disabledID != null) {
			this.setImageResource(disabledID);
		}
	}
	
	
	
	
	protected void loadFromAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
			    R.styleable.StateButton);
		
		final int N = a.getIndexCount();
		
		for (int i = 0; i < N; ++i) {
			
		    int attr = a.getIndex(i);
		    
		    
		    switch (attr) {
			    case R.styleable.StateButton_stateEnabled:
			    	enabled = a.getBoolean(attr, false);
			    	break;
			    case R.styleable.StateButton_iconDisabled:
			    	disabledID = a.getResourceId(attr, 0);
			    	break;
			    case R.styleable.StateButton_iconEnabled:
			    	enabledID = a.getResourceId(attr, 0);
			    	break;
		    }
		}
		
		a.recycle();
	}

	@Override
	public void onClick(View view) {
		if (view.equals(this)) {
			setStateEnabled(!isStateEnabled());
		}
	}

}
