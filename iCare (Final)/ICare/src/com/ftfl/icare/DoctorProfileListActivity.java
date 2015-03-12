package com.ftfl.icare;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ftfl.icare.adapter.DoctorProfileCustomAdapter;
import com.ftfl.icare.database.DoctorProfileDataSource;
import com.ftfl.icare.model.DoctorProfile;

public class DoctorProfileListActivity extends Activity {

	// Declaration of ListView and Button
	ListView mListView = null;

	Integer mSelectedID = null;

	// Profile Object
	DoctorProfile mProfile = null;

	// DataSource Object
	DoctorProfileDataSource mDataSource = null;

	// Profile Type ArrayList
	ArrayList<DoctorProfile> mDoctorProfileArrayList = null;

	// CustomAdapter
	DoctorProfileCustomAdapter mCustomAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_list);

		// Initialization of ListView and Button
		mListView = (ListView) findViewById(R.id.listViewDoctorProfile);

		// Get Data From DataSource
		mDataSource = new DoctorProfileDataSource(this);
		mDoctorProfileArrayList = mDataSource.getProfileList();

		// Set Adapter
		mCustomAdapter = new DoctorProfileCustomAdapter(
				DoctorProfileListActivity.this, mDoctorProfileArrayList);
		mListView.setAdapter(mCustomAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mSelectedID = mDoctorProfileArrayList.get(position).getmId();

				// Creating Bundle object
				Bundle b = new Bundle();

				// put id into bundle
				b.putInt("id", mSelectedID);
				Intent i = new Intent(getBaseContext(),
						DoctorProfileDetailActivity.class);
				// Storing bundle object into intent
				i.putExtras(b);
				startActivity(i);
			}
		});

	}

	public void goDoctorHome(View view) {
		Intent mHomeIntent = new Intent(DoctorProfileListActivity.this,
				MainActivity.class);
		startActivity(mHomeIntent);
	}

	// Main menu Operations
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.addmore, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.addMore) {
			Intent mIntent = new Intent(DoctorProfileListActivity.this,
					DoctorProfileCreateActivity.class);

			startActivity(mIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
