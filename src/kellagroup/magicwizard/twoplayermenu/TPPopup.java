package kellagroup.magicwizard.twoplayermenu;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.util.Reloadable;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import myreality.development.magicwizard.activities.Preferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Typeface;

public class TPPopup extends MagicActivity implements OnClickListener, OnMenuItemClickListener
{

	
		
		// Typeface TTF font (Fritz bold)
		private Typeface typeface;

		/* (non-Javadoc)
		 * @see android.app.Activity#onCreate(android.os.Bundle)
		 */

private PopupMenu popupMenu;

       private final static int Reset = 1;

       private final static int Info = 2;
       
       private final static int Rate = 3;
       
       private final static int Settings = 4;

       private final static int Exit = 5;

       @Override

       protected void onCreate(Bundle savedInstanceState) {

             super.onCreate(savedInstanceState);

             setContentView(R.layout.main_two_players);

             popupMenu = new PopupMenu(this, findViewById(R.id.tppopup));

             popupMenu.getMenu().add(Menu.NONE, Reset, Menu.NONE, "Reset");

             popupMenu.getMenu().add(Menu.NONE, Info, Menu.NONE, "Credits");

             popupMenu.getMenu().add(Menu.NONE, Rate, Menu.NONE, "Rate App");
             
             popupMenu.getMenu().add(Menu.NONE, Settings, Menu.NONE, "Settings");
             
             popupMenu.getMenu().add(Menu.NONE, Exit, Menu.NONE, "Exit");

             popupMenu.setOnMenuItemClickListener(this);

             findViewById(R.id.tppopup).setOnClickListener(this);

       }

       @Override

       public void onClick(View v) {

              popupMenu.show();

       }
       
       @Override

       public boolean onMenuItemClick(MenuItem item) {


              switch (item.getItemId()) {

              case Reset:

            	  	reset();

                     break;

              case Info:

                     showAppInfo();

                     break;

              case Rate:

                     rateApp();

                     break;
                 
              case Settings:
            	  
            	  	 showPreferences();
            	  
            	  	 break;
            
              case Exit:
            	  
            	  	 finish();
            	  
            	  	 break;

              }

              return false;

       }
       
   	private void rateApp() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=myreality.development.magicwizard"));
		startActivity(intent);
	}
	
	private void showAppInfo() {
		Builder builder = new Builder(this);
		AlertDialog alert = builder.setView(getLayoutInflater().inflate(R.layout.information, null))
			   .setCustomTitle(getLayoutInflater().inflate(R.layout.information_title, null))
			   .setIcon(android.R.drawable.ic_dialog_info)
			   .setPositiveButton(R.string.sf_ok, null)
			   .show();
		
		// Load the text views
		TextView txCaption = (TextView) alert.findViewById(R.id.tx_info_name);
		TextView txVersionName = (TextView) alert.findViewById(R.id.tx_info_version_name);
		TextView txVersionNumber = (TextView) alert.findViewById(R.id.tx_info_version_number);
		TextView txContent = (TextView) alert.findViewById(R.id.tx_info_content);
		
		// Change the default font
		txCaption.setTypeface(typeface);
		txVersionName.setTypeface(typeface);
		txVersionNumber.setTypeface(typeface);
		txContent.setTypeface(typeface);
	}
	
	private void showPreferences() {
		Intent intent = new Intent(this, Preferences.class);
		startActivity(intent);
	}

	private void reset() {
		for (Reloadable reloadable : reloadables) {
			reloadable.onReload(this, true);
		}
	}

}

