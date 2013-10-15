package myreality.development.magicwizard.widgets;

import java.util.Random;

import myreality.development.magicwizard.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Single dice view
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 1.1
 * @since 1.1
 */
public class DiceView extends LinearLayout {
	
	private LayoutInflater inflater;
	
	private static final int DEFAULT_EYES = 6;
	
	private int eyeCount;
	
	private static AlertDialog selection;
	
	private Random random;
	
	private Typeface typeface;

	public DiceView(Context context, AttributeSet attrs) {
		super(context, attrs);		
		initView(context, getEyeCountFromAttributes(context, attrs));
	}

	public DiceView(Context context) {
		super(context);
		initView(context, DEFAULT_EYES);
	}
	
	
	private void initView(final Context context, int eyes) {
		random = new Random();
		setEyeCount(eyes);
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		addView(inflater.inflate(R.layout.dice_view, null));
		TextView txEyes = (TextView) findViewById(R.id.tx_dice_number);
		txEyes.setText(String.valueOf(getEyeCount()));
		typeface = Typeface.createFromAsset(context.getAssets(),"fonts/fritzbold.ttf"); 
		txEyes.setTypeface(typeface);
		
		txEyes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				roll(context);
			}			
		});
	}
	
	
	private void roll(Context context) {
		
		if (selection != null) {
			selection.cancel();		
		}
		
		Builder builder = new Builder(context);
		
		View title = inflater.inflate(R.layout.dices_result_title, null);
		TextView txCaption = (TextView) title.findViewById(R.id.tx_dice_caption);
		txCaption.setTypeface(typeface);
		
		builder.setView(inflater.inflate(R.layout.dice_result, null))
			   .setPositiveButton("OK", null)
			   .setCustomTitle(title);
		selection = builder.create();
		selection.show();
		
		// Wurfergebnis anzeigen
		TextView tx_result = (TextView) selection.findViewById(R.id.tx_dice_result);

		if (tx_result != null) {
			int eyeNumber = random.nextInt(getEyeCount()) + 1;
			tx_result.setText(String.valueOf(eyeNumber));
			tx_result.setTypeface(typeface);		
		} 
	}
	
	
	private int getEyeCountFromAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
			    R.styleable.DiceView);
			 
		final int N = a.getIndexCount();
		for (int i = 0; i < N; ++i)
		{
		    int attr = a.getIndex(i);
		    switch (attr) {
		        case R.styleable.DiceView_eyeCount:
		        	return a.getInteger(attr, DEFAULT_EYES);
		    }
		}
		return DEFAULT_EYES;
	}
	
	
	public int getEyeCount() {
		return eyeCount;
	}

	public void setEyeCount(int eyeCount) {
		this.eyeCount = eyeCount;
	}
}
