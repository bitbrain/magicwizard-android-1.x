package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.layouts.FlipLayout;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;

public class ValueBarListener implements OnTouchListener {

	private VelocityTracker vTracker = null;
	private float oldX = 0;
	
	public ValueBarListener(Context context) {
	}

	public boolean onTouch(final View v, MotionEvent event) {
		
		ValueBar bar = (ValueBar) v;

		switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:

				if (vTracker == null)
					vTracker = VelocityTracker.obtain();
				else
					vTracker.clear();

				vTracker.addMovement(event);

				oldX = event.getRawX();
				if (bar.getValue() <= bar.getMaximum()) {
					setBarValue(bar, oldX);
				}
				break;

			case MotionEvent.ACTION_MOVE:

				vTracker.addMovement(event);
				vTracker.computeCurrentVelocity(1);
	
				// Add a move action on YOUR_ELEMENT
				// event.getRawX(); and event.getRawY(); coordinates are where you
				// touched
	
				if (bar.getValue() > bar.getMaximum()) {
					float velocity = vTracker.getXVelocity();
	
					if (velocity > 0) {
						for (float i = 0; i < velocity; ++i) {
							bar.plus();
						}
					} else {
						for (float i = velocity; i < 0; ++i) {
							bar.minus();
						}
					}
				} else {
					setBarValue(bar, event.getRawX());
				}
				break;

			case MotionEvent.ACTION_CANCEL:

				vTracker.recycle();
				return false;

			case MotionEvent.ACTION_UP:

				vTracker.addMovement(event);
				vTracker.computeCurrentVelocity(1000);

				// Add a touch up action!

				break;
			default:
				break;
		}
		
		return true;
	}		
	
	
	private void setBarValue(ValueBar bar, float xPos) {
		
		float totalWidth = bar.getMeasuredWidth();
		
		// TODO: Fix landscape position bug here
		float posX = xPos - bar.getLeft();
		
		float percentage = posX / totalWidth;
		
		int newValue = (int) (bar.getMaximum() * percentage);
		
		// Correct the position of a flipped element
		if (bar.getParent().getParent() instanceof FlipLayout) {
			FlipLayout layout = (FlipLayout) bar.getParent().getParent();
			
			if (layout.isFlipped()) {
				newValue = (int) (bar.getMaximum() * (1.0f - percentage));
			}
		}
		
		if (newValue >= 0) {
			bar.setValue(newValue);
			bar.invalidate();
		}
	}

}
