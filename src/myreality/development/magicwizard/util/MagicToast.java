package myreality.development.magicwizard.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
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
		SUCCESS,
	
		/**
		 * Identifier for an informal toast
		 */
		INFO,
	
		/**
		 * Identifier for a warning toast
		 */
		WARN,
	
		/**
		 * Identifier for a failing toast
		 */
		FAIL
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
		
		LinearLayout layout = new LinearLayout(context);
		layout.setBackgroundResource(android.R.color.white);
		
		TextView textView = new TextView(context);
		textView.setTextColor(Color.RED);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		textView.setText(text);		
		layout.addView(textView);
		
		return layout;
	}
}
