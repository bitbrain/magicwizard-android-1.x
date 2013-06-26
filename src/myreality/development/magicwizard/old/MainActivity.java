/*package myreality.development.magicwizard.old;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.activities.MagicActivity;
import myreality.development.magicwizard.activities.Preferences;
import myreality.development.magicwizard.widgets.IconContextMenu;
import myreality.development.magicwizard.widgets.LifeBar;
import myreality.development.magicwizard.widgets.PoisonBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends MagicActivity {
    *//** Called when the activity is first created. *//*
	
	private final int CONTEXT_MENU_ID = 1;
	private IconContextMenu iconContextMenu = null;
	
	*//**
     * list item long click handler
     * used to show the context menu
     *//*
    private OnClickListener menuOnClickListener = new OnClickListener() {

		public void onClick(View v) {
			showDialog(CONTEXT_MENU_ID);
		}
	};
	
	private final int MENU_ITEM_1_ACTION = 1;
	private final int MENU_ITEM_2_ACTION = 2;
	
	private AlertDialog selection;
	
    public MainActivity() {
		super();
    }
	
	*//**
	 * create context menu
	 *//*
	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == CONTEXT_MENU_ID) {
			Dialog dialog = iconContextMenu.createMenu(null);
			return dialog;
		}
		return super.onCreateDialog(id);
	}
	
	private void initSampleList() {
    	ImageButton menuButton = (ImageButton)findViewById(R.id.btn_menu);    	
    	menuButton.setOnClickListener(menuOnClickListener);
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	public void onButtonClick(View view) {	
		Builder builder = null;
		switch (view.getId()) {
			case R.id.btn_reset:
				builder = new Builder(this);
				builder.setPositiveButton("OK", 
				          new DialogInterface.OnClickListener() {
						      public void onClick(DialogInterface dialog, int which) {
						          reset();
						      } 
					      })
					   .setNegativeButton("Abbrechen", null)
				       .setTitle("Achtung");
				builder.setMessage("Saemtlicher Fortschritt wird zurueckgesetzt");
				builder.create().show();
				break;
			case R.id.btn_dice:
				showDiceSelection();
				break;
			case R.id.btn_quit:
				builder = new Builder(this);
				builder.setPositiveButton("OK", 
				          new DialogInterface.OnClickListener() {
						      public void onClick(DialogInterface dialog, int which) {
						          finish();
						      } 
					      })
					   .setNegativeButton("Abbrechen", null)
				       .setTitle("Achtung");
				builder.setMessage("Sie verlassen die Anwendung.");
				builder.create().show();
				break;
			case R.id.btn_dice_6:
				showDiceResult((int) (Math.random() * 6) + 1);
				break;
			case R.id.btn_dice_10:
				showDiceResult((int) (Math.random() * 10) + 1);
				break;
			case R.id.btn_dice_20:
				showDiceResult((int) (Math.random() * 20) + 1);
				break;
			case R.id.btn_dice_30:
				showDiceResult((int) (Math.random() * 30) + 1);
				break;
			case R.id.btn_sign_in:
				Intent signIntent = new Intent(this, SignupActivity.class);
				timer.pause();
				startActivity(signIntent);				
				break;
		}		
	}
	
	public void showDiceSelection() {
		
		Builder builder = new Builder(this);
		builder.setView(getLayoutInflater().inflate(R.layout.dices, null))
		         .setCustomTitle(getLayoutInflater().inflate(R.layout.dices_title, null));
		selection = builder.create();
		selection.show();
	}
	
	public void showDiceResult(int result) {
		Builder builder = new Builder(this);
		if (selection != null) {
			selection.cancel();		
		}
		builder.setView(getLayoutInflater().inflate(R.layout.dice_result, null))
			   .setPositiveButton("OK", null)
			   .setCustomTitle(getLayoutInflater().inflate(R.layout.dices_result_title, null));
		selection = builder.create();
		selection.show();
		
		// Wurfergebnis anzeigen
		TextView tx_result = (TextView) selection.findViewById(R.id.tx_dice_result);
		
		if (tx_result != null) {
			tx_result.setText(String.valueOf(result));
		} 
	}

	public void reset() {
		
		boolean twoPlayerMode = checkTwoPlayerMode();
		
		if (!twoPlayerMode) {
			LifeBar lifeBar = (LifeBar)findViewById(R.id.cb_life);
			PoisonBar poisonBar = (PoisonBar)findViewById(R.id.cb_poison);
			lifeBar.reset(this);
			poisonBar.reset();
		} else {
			LifeBar lifeBar = (LifeBar)findViewById(R.id.cb_life_p1);
			PoisonBar poisonBar = (PoisonBar)findViewById(R.id.cb_poison_p1);
			lifeBar.reset(this);
			poisonBar.reset();
			lifeBar = (LifeBar)findViewById(R.id.cb_life_p2);
			poisonBar = (PoisonBar)findViewById(R.id.cb_poison_p2);
			lifeBar.reset(this);
			poisonBar.reset();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences settings =
				getSharedPreferences(
						getPackageName() + "_preferences", MODE_PRIVATE);
		
		String lastActivity = settings.getString("lastActivity", "");
		
		if (lastActivity.equals("Preferences")) {
			initSettings(false);
		}
		
	}
	
	private boolean checkTwoPlayerMode() {
		SharedPreferences settings =
				getSharedPreferences(
						getPackageName() + "_preferences", MODE_PRIVATE);		
		
		if (settings.getBoolean("disable_screen_lock", true)) {			
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		} else {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
		
		return settings.getBoolean("enable_two_player_mode", false);
	}
	
	
	private void initSettings() {
		initSettings(false);
	}
	
	private void initSettings(boolean firstUse) {
		if (checkTwoPlayerMode()) {
			setContentView(R.layout.main_two_players);
			LifeBar lifeBar1 = (LifeBar)findViewById(R.id.cb_life_p1);
			LifeBar lifeBar2 = (LifeBar)findViewById(R.id.cb_life_p2);
			lifeBar1.loadFromPreferences(this, firstUse);
			lifeBar2.loadFromPreferences(this, firstUse);
		} else {
			setContentView(R.layout.main);
			LifeBar lifeBar = (LifeBar)findViewById(R.id.cb_life);
			lifeBar.loadFromPreferences(this, firstUse);
		}
		Resources res = getResources();
        ImageButton contextButton = (ImageButton)findViewById(R.id.btn_menu);
        
        initSampleList();
        iconContextMenu = new IconContextMenu(this, CONTEXT_MENU_ID);
        iconContextMenu.addItem(res, getResources().getString(R.string.opt_preferences), R.drawable.ico_preferences, MENU_ITEM_1_ACTION);
        iconContextMenu.addItem(res, getResources().getString(R.string.opt_info), R.drawable.ico_info, MENU_ITEM_2_ACTION);
        final Context context = this;
        
       //set onclick listener for context menu
        iconContextMenu.setOnClickListener(new IconContextMenu.IconContextMenuOnClickListener() {

			public void onClick(int menuId) {
				switch(menuId) {
				case MENU_ITEM_1_ACTION:
					Intent intent = new Intent(context, Preferences.class);
					startActivity(intent);
					break;
				case MENU_ITEM_2_ACTION:
					Builder builder = new Builder(context);
					builder.setView(getLayoutInflater().inflate(R.layout.information, null))
						   .setCustomTitle(getLayoutInflater().inflate(R.layout.information_title, null))
						   .setIcon(android.R.drawable.ic_dialog_info)
						   .setPositiveButton(R.string.sf_ok, null)
						   .show();
					break;
				}
			}
		});
        
        registerForContextMenu(contextButton);
	}
		
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initSettings(true);
    }
	
	
	
	

}*/