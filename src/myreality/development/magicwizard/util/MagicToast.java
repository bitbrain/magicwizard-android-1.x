package myreality.development.magicwizard.util;

import myreality.development.magicwizard.R;
import android.content.Context;
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
			public int getBackgroundResource() {
				return R.drawable.darkbutton;
			}

			@Override
			public int getColorResource() {
				return R.color.caption;
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
			public int getBackgroundResource() {
				return R.drawable.darkbutton;
			}

			@Override
			public int getColorResource() {
				return R.color.caption;
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
			public int getBackgroundResource() {
				return R.drawable.darkbutton;
			}

			@Override
			public int getColorResource() {
				return R.color.caption;
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
			public int getBackgroundResource() {
				return R.drawable.darkbutton;
			}

			@Override
			public int getColorResource() {
				return R.color.caption;
			}
		};
		
		public abstract int getIconResource();		
		public abstract int getBackgroundResource();
		public abstract int getColorResource();
		
		
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
		view.setTextColor(context.getResources().getColor(type.getColorResource()));
		return view;
		
	}
	
	private static ViewGroup generateLayout(Context context, ToastType type) {
		LinearLayout layout = new LinearLayout(context);
		layout.setBackgroundResource(type.getBackgroundResource());
		return layout;
	}
}
