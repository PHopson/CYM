package com.platypus.cym;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run(){
				final Intent splashIntent = new Intent(SplashScreen.this,MainMenu.class);
				startActivity(splashIntent);
				finish();
			}
		}, 2000);
	}
	

}
