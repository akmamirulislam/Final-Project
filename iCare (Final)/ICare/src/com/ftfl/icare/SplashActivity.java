package com.ftfl.icare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ftfl.icare.database.UserProfileDataSource;

public class SplashActivity extends Activity {

	// UserProfile DataSource object
	UserProfileDataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		mDataSource = new UserProfileDataSource(this);

		// Thread that will sleep for 3 seconds
		Thread mSplash = new Thread() {
			public void run() {
				try {
					// Thread will sleep for 3 seconds
					sleep(3 * 1000);
					// After 3 seconds redirect to another intent

					if (mDataSource.isEmpty()) {
						Intent i = new Intent(SplashActivity.this,
								UserProfileCreateActivity.class);
						startActivity(i);
					} else {
						Intent i = new Intent(SplashActivity.this,
								MainActivity.class);
						startActivity(i);
					}

					// Remove activity
					finish();
				} catch (Exception e) {
				}
			}
		};
		// start thread
		mSplash.start();
	}
}
