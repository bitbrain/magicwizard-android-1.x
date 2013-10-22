package myreality.development.magicwizard.util;

import myreality.development.magicwizard.R;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is a wrapper class for Androids <code>Toast</code> implementation in
 * order to display custom toast messages.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class MagicToast {

	public enum ToastType {
		
		/**
		 * Identifier for a success toast
		 */
		SUCCESS {
			
			@Override
			public int getIconResource() {
				return R.drawable.dice;
			}

			@Override
			public int getFontColorResource() {
				return R.color.toast_text_success;
			}

			@Override
			public int getBorderColorResource() {
				return R.color.toast_border_success;
			}

			@Override
			public int getBackgroundColorResource() {
				return R.color.toast_background_success;
			}

		},
	
		/**
		 * Identifier for an informal toast
		 */
		INFO {

			@Override
			public int getIconResource() {
				return R.drawable.dice;
			}

			@Override
			public int getFontColorResource() {
				return R.color.toast_text_info;
			}

			@Override
			public int getBorderColorResource() {
				return R.color.toast_border_info;
			}

			@Override
			public int getBackgroundColorResource() {
				return R.color.toast_background_info;
			}

		},
	
		/**
		 * Identifier for a warning toast
		 */
		WARN {

			@Override
			public int getIconResource() {
				return R.drawable.dice;
			}

			@Override
			public int getFontColorResource() {
				return R.color.toast_text_warn;
			}

			@Override
			public int getBorderColorResource() {
				return R.color.toast_border_warn;
			}

			@Override
			public int getBackgroundColorResource() {
				return R.color.toast_background_warn;
			}
		},
	
		/**
		 * Identifier for a failing toast
		 */
		FAIL {

			@Override
			public int getIconResource() {
				return R.drawable.dice;
			}

			@Override
			public int getFontColorResource() {
				return R.color.toast_text_fail;
			}

			@Override
			public int getBorderColorResource() {
				return R.color.toast_border_fail;
			}

			@Override
			public int getBackgroundColorResource() {
				return R.color.toast_background_fail;
			}
		};
		
		public abstract int getIconResource();		
		public abstract int getFontColorResource();
		public abstract int getBorderColorResource();
		public abstract int getBackgroundColorResource();
		
	}
	
	// Private constructor, this is only an utility class
	private MagicToast() { }
	
	/**
	 * Shows a toast of the given {@see ToastType}
	 * 
	 * @param context current context to show the toast on
	 * @param text text to display
	 * @param type type of this toast
	 */
	public static void show(Context context, String text, ToastType type) {
		Toast toast = new Toast(context);	
		toast.setView(generateToastView(context, text, type));		
		toast.show();
	}
	
	
	
	private static View generateToastView(Context context, String text, ToastType type) {
		
		// Load components
		ViewGroup layout = generateLayout(context, type); 
		ImageView imageView = generateImageView(context, type);
		TextView textView = generateTextView(context, type);
		textView.setTextSize(25);
		textView.setText(text);			
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(30, 0, 0, 0);

		// Initialize layout
		layout.addView(imageView);
		layout.addView(textView);
		
		return layout;
	}
	
	private static ImageView generateImageView(Context context, ToastType type) {
		ImageView view = new ImageView(context);
		view.setImageResource(type.getIconResource());
		return view;
	}
	
	private static TextView generateTextView(Context context, ToastType type) {
		TextView view = new TextView(context);
		view.setTextColor(context.getResources().getColor(type.getFontColorResource()));
		return view;
		
	}
	
	@SuppressWarnings("deprecation")
	private static ViewGroup generateLayout(Context context, ToastType type) {
		LinearLayout layout = new LinearLayout(context);
		
		// Set background
		layout.setBackgroundDrawable(generateDrawable(context, type));
		
		layout.setPadding(30, 30, 30, 30);
		return layout;
	}
	
	private static Drawable generateDrawable(Context context, ToastType type) {	
		int colorBackground = context.getResources().getColor(type.getBackgroundColorResource());
		int colorBorder = context.getResources().getColor(type.getBorderColorResource());
		
		ShapeDrawable drawable = new ShapeDrawable(new RectShape());
		Drawable[] layers = new Drawable[2];
		Paint paint = drawable.getPaint();	
		paint.setColor(colorBorder);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
		ShapeDrawable drawable2 = new ShapeDrawable(new RectShape());
		paint = drawable2.getPaint();
		paint.setColor(colorBackground);
		paint.setStyle(Style.FILL);
		
		layers[0] = drawable2;
		layers[1] = drawable;
		
		LayerDrawable composite = new LayerDrawable(layers);
		
		return composite;
	}
}
