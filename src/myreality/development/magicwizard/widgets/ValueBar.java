/**
 * Basic value bar that contains border rules for color fading. Each rule can be added
 * or removed by a public method. Default color is white (only one rule)
 * 
 * @since 27-08-2012
 * @author Miguel Gonzalez
 * @version 1.01
 */
package myreality.development.magicwizard.widgets;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import myreality.development.magicwizard.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;

public class ValueBar extends View {
	
	// Current value
	private int value, maximumCap;
	
	private BitmapDrawable drawableBar, drawableBackground;
	
	private Map<Integer, ValueRule> rules;
	
	private Paint paint;
	
	private Integer capLow, capHigh;
	
	private Vibrator vibrator;
	
	private int fontSize;

	private Typeface type;
	
	private Rect bounds;
	
	private Bitmap buffer;

	private Canvas canvas;
	
	// Constructors
	public ValueBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public ValueBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public ValueBar(Context context) {
		super(context);
		initView(context);
	}	
	
	public void setValue(int value) {
		if (getValue() != value) {
			if (capLow != null && capHigh != null) {
				if (value >= capLow && value <= capHigh) {
					this.value = value;
				}
			} else if (capLow != null) {
				if (value >= capLow) {
					this.value = value;
				}
			} else if (capHigh != null) {
				if (value <= capHigh) {
					this.value = value;
				}
			} else {
				this.value = value;
			}
			refreshBuffer();
			invalidate();

			vibrator.vibrate(5);
		}
	}
	
	
	
	/**
	 * Function to calculate the correct color (depends on the given color rules)
	 * 
	 * @return Correct color
	 */
	public int getValueColor() {
		if (rules.isEmpty()) {
			return Color.WHITE;
		} else if (rules.size() == 1) {
			for (Entry<Integer, ValueRule> set : rules.entrySet()) {
				return set.getValue().getColor();
			}
		} else {
			// Get the lower and the upper rules "around" the value
			ValueRule lower = null, upper = null;
			
			for (Entry<Integer, ValueRule> entry : rules.entrySet()) {
				if (entry.getKey() < value) {
					lower = entry.getValue();
				} else if (entry.getKey() >= value) {
					upper = entry.getValue();
					break;
				}
			}
			
			// Case 1: Value is the lowest
			if (lower == null) {
				return upper.getColor();
			} else
			
			// Case 2: Value is in the middle
			if (lower != null && upper != null) {
				// Calculate the percent color between the lowest and the highest
				double percent = (double)(value - lower.getValue()) / (double)(upper.getValue() - lower.getValue());
				int r1 = Color.red(lower.getColor()), 
				    g1 = Color.green(lower.getColor()), 
				    b1 = Color.blue(lower.getColor()), 
				    r2 = Color.red(upper.getColor()), 
				    g2 = Color.green(upper.getColor()), 
				    b2 = Color.blue(upper.getColor());	
				// Return the calculated color
				return blend(r2, g2, b2, r1, g1, b1, percent);
			} else
			
			// Case 3: Value is the highest
			if (upper == null) {
				return lower.getColor();
			}
		}
		return 0x000000;
	}
	
	
	
	public int getValue() {
		return value;
	}
	
	
	/** 
	 * Function to blend between 2 colors
	 * 
	 * @param clr1
	 * @param clr2
	 * @param ratio
	 * @return
	 */
	public int blend(float r1, float g1, float b1, float r2, float g2, float b2, double ratio) {
		float r = (float) ratio;
		float ir = (float) 1.0 - r;

		return Color.rgb((int)(r1 * r + r2 * ir), 
						 (int)(g1 * r + g2 * ir),
						 (int)(b1 * r + b2 * ir));             
	}
	
	public void plus() {
		if (capHigh != null) {
			if (value < capHigh) {
				setValue(getValue() + 1);
			}
		} else {
			setValue(getValue() + 1);
		}
	}
	
	public void minus() {
		if (capLow != null) {
			if (value > capLow) {
				setValue(getValue() - 1);
			}
		} else {
			setValue(getValue() - 1);
		}
		
	}
	

	/**
	 * General initialization for the view
	 * 
	 * @param context Context of the view
	 */
	private void initView(Context context) {		
		
		bounds = new Rect();
		fontSize = 82;
		rules = new TreeMap<Integer, ValueRule>();
		drawableBackground = (BitmapDrawable) getResources().getDrawable(R.drawable.bar_background);
		drawableBar = (BitmapDrawable) getResources().getDrawable(R.drawable.bar_value);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
		paint.setAntiAlias(true);
		setMaximum(10);
		setValue(0);
		capLow = null; capHigh = null;
		vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
		setOnTouchListener(new ValueBarListener(context));
		type = Typeface.createFromAsset(context.getAssets(),"fonts/fritzbold.ttf");
	}
	
	
	private void initBuffer(int width, int height) {
		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		buffer = Bitmap.createBitmap(width, height, conf);
		canvas = new Canvas(buffer);
		refreshBuffer();
	}
		

	/* (non-Javadoc)
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		initBuffer(getMeasuredWidth(), getMeasuredHeight());
	}

	public void enableLowCap(int value) {
		capLow = value;
	}
	
	public void enableHighCap(int value) {
		capHigh = value;
	}
	
	
	public void disableLowCap() {
		capLow = null;
	}
	
	public void disableHighCap() {
		capHigh = null;
	}
	
	
	
	public void setMaximum(int max) {
		maximumCap = max;
		refreshBuffer();
	}
	
	public int getMaximum() {
		return maximumCap;
	}
	
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(buffer, 0, 0, paint);
	}
	
	

	public void addRule(int value, int color) {
		rules.put(value, new ValueRule(value, color));
		refreshBuffer();
	}
	
	public void removeRule(int value) {
		rules.remove(value);
		refreshBuffer();
	}
	
	class ValueRule {
		
		private int color;
		private int value;
		
		public ValueRule(int value, int color) {
			this.color = color;
			this.value = value;
		}
		
		public int getColor() {
			return color;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	
	
	public void clearRules() {
		rules.clear();
	}
	
	
	private void refreshBuffer() {
		
		if (canvas != null) {
		
			canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
			
			// Drawing the background
			drawableBackground.setColorFilter(getValueColor(), Mode.MULTIPLY);
			
			// Drawing the value
			drawableBar.setColorFilter(getValueColor(), Mode.MULTIPLY);
			int newWidth = (int) ((getWidth() - getPaddingRight()) * (float)((float)value / (float)maximumCap));
			if (newWidth > getWidth()) {
				newWidth = getWidth();
			}
			
			int left = getPaddingLeft();
			int top = getPaddingTop();
			
			drawableBackground.setBounds(newWidth, getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
			drawableBackground.draw(canvas);
			drawableBar.setBounds(left, top, newWidth, getHeight() - getPaddingBottom());
			drawableBar.draw(canvas);
			
			// Printing the value as a number
			paint.setTextSize(fontSize);
			
		    paint.getTextBounds("0", 0, 1, bounds);		
			paint.setTypeface(type);
			paint.setColor(getValueColor());
			paint.setShadowLayer(1, 0, 2, 0x44000000);
			canvas.drawText(String.valueOf(value), getWidth() / 2 - paint.measureText(String.valueOf(value)) / 2, getHeight() / 2 + bounds.height() / 2, paint);
		}
	}

	/* (non-Javadoc)
	 * @see android.view.View#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		refreshBuffer();
	}

	/* (non-Javadoc)
	 * @see android.view.View#onRestoreInstanceState(android.os.Parcelable)
	 */
	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		super.onRestoreInstanceState(state);
		refreshBuffer();
	}

	/* (non-Javadoc)
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		refreshBuffer();
	}
	
	
	
	

}
