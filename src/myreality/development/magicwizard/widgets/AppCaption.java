package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Shows the current app name and version in a box
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.1
 * @version 1.1
 */
public class AppCaption extends LinearLayout {
	
    private LayoutInflater inflater;

	public AppCaption(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout(context);
	}

	public AppCaption(Context context) {
		super(context);
		initLayout(context);
	}
	
	private void initLayout(Context context) {
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		addView(inflater.inflate(R.layout.app_caption, null));
		TextView txVersion = (TextView) findViewById(R.id.tx_version);
		Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/fritzbold.ttf"); 
		txVersion.setTypeface(type);
		txVersion.setText("v. " + txVersion.getText());
		
	}

	
}
