package myreality.development.magicwizard.old;
/*package myreality.development.magicwizard.old;

import java.text.SimpleDateFormat;
import java.util.Date;

import myreality.development.magicwizard.Preferences;
import myreality.development.magicwizard.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LifeCounterActivity extends Activity {

	
	 private final static int DEFAULT_POINTS = 20;
	 private boolean LOST;
	 private Handler handler = null;
	 private long mStartTime = System.currentTimeMillis(), mCurrentTime = System.currentTimeMillis();
	 private int mPoisonCounter = 0, mLifePoints = DEFAULT_POINTS;
	 
	 private AlertDialog selection;
	 
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.lifecounter_activity);	
	    handler = new Handler();
	    resetAll();
	    selection = null;
	    updateTime();    
	    
	 }
	 
	 private Runnable updateTimeTask = new Runnable() {
		 public void run() {
		 updateTime();
		 handler.postDelayed(this, 1000);
		 }
	 };
	 
	 
	 @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if ( handler != null ) {
			handler.removeCallbacks(updateTimeTask);
			handler = null; 
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		handler.removeCallbacks(updateTimeTask);
	}

	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			
			getMenuInflater().inflate(R.menu.life_counter_menu, menu);		
			return super.onCreateOptionsMenu(menu);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			
			Builder builder = null;
			
			switch (item.getItemId()) {
				
				case R.id.opt_reset:
					builder = new Builder(this);
					
					// Beim Klick auf "OK" soll ein Reset stattfinden
					OnClickListener restart_listener = new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							resetAll();
						}	
					   };
				builder.setTitle(R.string.opt_warning)
					   .setIcon(android.R.drawable.ic_dialog_alert)
					   .setMessage(R.string.tx_message_reset)
					   .setPositiveButton(R.string.sf_ok, restart_listener)
					   .setNegativeButton(R.string.sf_abort, null)
					   .show();
				break;
				case R.id.opt_preferences:
					Intent intent = new Intent(this, Preferences.class);
					startActivity(intent);
					break;
				case R.id.opt_information:
					builder = new Builder(this);
					builder.setView(getLayoutInflater().inflate(R.layout.information, null))
						   .setTitle(R.string.opt_info)
						   .setIcon(android.R.drawable.ic_dialog_info)
						   .setPositiveButton(R.string.sf_ok, null)
						   .show();
					break;
			}
			
			return super.onOptionsItemSelected(item);
		}
		
		
		public void resetAll() {
			
			TextView tx_points = (TextView) findViewById(R.id.tx_lifecounter_points);
			TextView tx_poison_points = (TextView) findViewById(R.id.tx_lifecounter_poisoncounter_value);
			tx_points.setText(String.valueOf(DEFAULT_POINTS));
			tx_poison_points.setText(String.valueOf(0));
			mPoisonCounter = 0;
			mLifePoints = 20;
			checkColor();
			LOST = false;
			checkPoisonPoints();
			mStartTime = System.currentTimeMillis();
		    mCurrentTime = System.currentTimeMillis();	
			handler.removeCallbacks(updateTimeTask);
		    handler.postDelayed(updateTimeTask, 1000); 
		    updateTime();
		}
		
		@Override
		protected void onResume() {
			Resources res = getResources();
			
			handler.removeCallbacks(updateTimeTask);
			handler.postDelayed(updateTimeTask, 1000);
			
			super.onResume();
			
			// Name des Spielers laden
		    SharedPreferences settings =
					getSharedPreferences(
							getPackageName() + "_preferences",
							MODE_PRIVATE);
						
			TextView tx_caption = (TextView) findViewById(R.id.tx_lifecounter_caption);
			String new_caption = res.getString(R.string.tx_lifecounter_caption);
			String player = settings.getString("player1", "Spieler1");
			tx_caption.setText(new_caption + " - " + player);
			
			if (settings.getBoolean("disable_screen_lock", true)) {			
				getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			} else {
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			}
		}


		private void checkColor() {
			TextView tx_points = (TextView) findViewById(R.id.tx_lifecounter_points);
			
			int points = Integer.parseInt(tx_points.getText().toString());
		
			tx_points.setTextColor(getPointColor(0, 50, points));
			
		}
		
		public void onPoisonButtonClick(View view) {
			TextView tx_poison_points = (TextView) findViewById(R.id.tx_lifecounter_poisoncounter_value);
			int poison_points = Integer.parseInt(tx_poison_points.getText().toString());
			
			switch (view.getId()) {
				case R.id.sf_lifecounter_poisoncounter_add:
					poison_points++;
					break;					
				case R.id.sf_lifecounter_poisoncounter_reduce:
					poison_points--;
					break;
			}
			
			tx_poison_points.setText(String.valueOf(poison_points));
			
			if (poison_points > 9) {
				loose();
			}
			
			if (LOST && poison_points < 10) {
				LOST = false;
			}
			
			checkPoisonPoints();
			
			mPoisonCounter = poison_points;
		}
		
		
		
		public void onLifeButtonClick(View view) {		
			Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(10);
			TextView tx_points = (TextView) findViewById(R.id.tx_lifecounter_points);	
			int life_points = Integer.parseInt(tx_points.getText().toString());	
			
			switch (view.getId()) {
					
				case R.id.sf_lifecounter_add_life:
					life_points++;
					break;
				case R.id.sf_lifecounter_add_more_life:
					life_points += 5;
					break;
				case R.id.sf_lifecounter_reduce_life:
					life_points --;
					break;
				case R.id.sf_lifecounter_reduce_more_life:
					life_points -= 5;
					break;
			}
			
			tx_points.setText(String.valueOf(life_points));
			checkColor();
			
			if (life_points < 1) {
				loose();
			}
			
			if (LOST && life_points > 0) {
				LOST = false;
			}
			
			getPointColor(0, 40, life_points);
			
			mLifePoints = life_points;
			
			//makeLifeToast();
						
		}
		
		public void makeLifeToast() {
			
			String message = "";
			
			switch (mLifePoints) {
				case 5:
					message = "Vorsicht! Gleich bist du tot!";
					break;
				case 40:
					message = "Deine Lebenspunkte sind hoch.";
					break;
				case 100:
					message = "Du kannst fast nicht mehr verlieren!";
					break;
				default:
					return;
			}
			
			Toast.makeText(this, message, Toast.LENGTH_LONG).show();
		}
		
		public void loose() {
			
			if (!LOST) {
				// Spieler hat verloren
				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	
				long milliseconds = 1000;
				v.vibrate(milliseconds);
				
				Toast.makeText(this, "Du hast verloren!", Toast.LENGTH_SHORT).show();
				
				LOST = true;
			}
		}
		
		
		public void checkPoisonPoints() {
			TextView tx_poison_points = (TextView) findViewById(R.id.tx_lifecounter_poisoncounter_value);
			Button sf_reduce = (Button) findViewById(R.id.sf_lifecounter_poisoncounter_reduce);
			int points = Integer.parseInt(tx_poison_points.getText().toString());	
			
			if (points < 1) {
				tx_poison_points.setText("0");
				sf_reduce.setEnabled(false);
				mPoisonCounter = 0;
			} else {
				sf_reduce.setEnabled(true);
			}
		}
		
		private void updateTime() {			
			final TextView time = (TextView) findViewById(R.id.tx_lifecounter_time);
			mCurrentTime = System.currentTimeMillis();			
			final Date totalTime = new Date(mCurrentTime - mStartTime);
			final SimpleDateFormat formatterTime = new SimpleDateFormat( "H:mm:ss" );
			time.setText(getResources().getString(R.string.tx_time) + ": " + formatterTime.format(totalTime) );
		}
		
		
		
		*//**
		 * Funktion zum Waehlen einer passenden Farbe, abhaengig vom Fortschritt
		 * 
		 * @author 	Miguel Gonzalez
		 * @version 1.0
		 * @since	05-12-2011
		 *//*
		private int getPointColor(int min, int max, int current) {
			
			if (min >= max) {
				return 0;
			}
			
			int rel_current = 0;
			int maximum = 0;
			// Momentanen %-Wert berechnen
			if (min < 0) {
				rel_current = min * (-1) + current; 
				maximum = min + max;
			} else {
				rel_current = current - min;
				maximum = max - min;
			}
			
			double percent = (double) (rel_current) / (double) maximum;
			
			int red = 0, green = 0, blue = 0;
			
			if (percent < 0) {
				red = 255;
				green = 0;
				blue = 0;
			} else if (percent < 0.25 && percent >= 0.0) { // Ebene 1
				red = 255;
				green = getPercentColor(percent);
				blue = 0;
				
			} else if (percent >= 0.25 && percent < 0.50) { // Ebene 2
				percent -= 0.25;
				green = 255;
				red = 255 - getPercentColor(percent);
				blue = 0;
			} else if (percent >= 0.50 && percent < 0.75) { // Ebene 3
				percent -= 0.50;
				red = 0;
				green = 255;
				blue = getPercentColor(percent);
				
			} else if (percent >= 0.75 && percent < 1.01){ // Ebene 4
				percent -= 0.75;
				red = 0;
				blue = 255;
				green = 255 - getPercentColor(percent);
			} else {
				red = 0;
				blue = 255;
				green = 0;
			}			
			
			return Color.rgb(red, green, blue);
		}
		
		private int getPercentColor(double percent) {
			return (int) (1020 * percent);
			
		}
		
		@Override
		public void onConfigurationChanged(Configuration newConfig) {
			
			// Name des Spielers laden
		    SharedPreferences settings =
					getSharedPreferences(
							getPackageName() + "_preferences",
							MODE_PRIVATE);
		    
			// TODO Auto-generated method stub
			super.onConfigurationChanged(newConfig);
			setContentView(R.layout.lifecounter_activity);
			TextView tx_points = (TextView) findViewById(R.id.tx_lifecounter_points);
			TextView tx_poison_points = (TextView) findViewById(R.id.tx_lifecounter_poisoncounter_value);
			tx_points.setText(String.valueOf(mLifePoints));
			tx_poison_points.setText(String.valueOf(mPoisonCounter));
					
			// Ueberpruefen
			checkColor();
			checkPoisonPoints();	
						
			TextView tx_caption = (TextView) findViewById(R.id.tx_lifecounter_caption);
			String new_caption = getResources().getString(R.string.tx_lifecounter_caption);
			String player = settings.getString("player1", "Spieler1");
			tx_caption.setText(new_caption + " - " + player);
			
			// Zeit anzeigen
			updateTime();
		}
		
		
		public void onButtonClick(View view) {
			
			int result = 10;
			switch (view.getId()) {
				case R.id.sf_lifecounter_dice:
					Builder builder = new Builder(this);
					builder.setView(getLayoutInflater().inflate(R.layout.dices, null))
					         .setTitle("Waehle einen Wuerfel:");
					selection = builder.create();
					selection.show();
					break;
				case R.id.sf_dice_twenty_sides:	
					result = (int) (Math.random() * 20) + 1;
					showResult(result);
					break;
				case R.id.sf_dice_thirty_sides:	
					result = (int) (Math.random() * 30) + 1;
					showResult(result);
					break;
				case R.id.sf_dice_ten_sides:	
					result = (int) (Math.random() * 10) + 1;
					showResult(result);
					break;
				case R.id.sf_dice_six_sides:	
					result = (int) (Math.random() * 6) + 1;
					showResult(result);
					break;
			}
		}
		
		public void showResult(int result) {
			
			Builder builder = new Builder(this);
			if (selection != null) {
				selection.cancel();		
			}
			builder.setView(getLayoutInflater().inflate(R.layout.dice_result, null))
			         .setPositiveButton("OK", null);
			selection = builder.create();
			selection.show();
			
			// Wurfergebnis anzeigen
			TextView tx_result = (TextView) selection.findViewById(R.id.tx_dice_result);
			
			if (tx_result != null) {
				tx_result.setText(String.valueOf(result));
			} 
			
			
		}
}
*/