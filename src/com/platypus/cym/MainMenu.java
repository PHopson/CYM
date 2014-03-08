package com.platypus.cym;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void startPhoneList(View view){
		Intent phoneIntent = new Intent(this,PhoneList.class);
		startActivity(phoneIntent);
	}
	
	public void startSettings(View view){
		Intent settingIntent = new Intent(this, SettingsActivity.class);
		startActivity(settingIntent);
	}

}
