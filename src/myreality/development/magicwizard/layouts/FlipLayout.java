package myreality.development.magicwizard.layouts;

import myreality.development.magicwizard.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class FlipLayout extends LinearLayout {
	
	private boolean isMirroring = false;
    @SuppressWarnings("unused")
	private Handler handler;


	public FlipLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	

	public FlipLayout(Context context) {
		super(context);
	}



	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
			    R.styleable.FlipLayout);
			 
		final int N = a.getIndexCount();
		for (int i = 0; i < N; ++i)
		{
		    int attr = a.getIndex(i);
		    switch (attr) {
		        case R.styleable.FlipLayout_flipped:
		        	isMirroring = a.getBoolean(attr, false);
		            break;
		    }
		}
		a.recycle();
		
		this.setWillNotDraw(false);
	}

	@Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        if (isMirroring) {
            canvas.rotate(180, getWidth() / 2, getHeight() / 2);
        }
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isMirroring)
            event.setLocation(getWidth() - event.getX(),
                    getHeight() - event.getY());
        return super.dispatchTouchEvent(event);
    }
    
    public boolean isFlipped() {
    	return isMirroring;
    }


	
}
