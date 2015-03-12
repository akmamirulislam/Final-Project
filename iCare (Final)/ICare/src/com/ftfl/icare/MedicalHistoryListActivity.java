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

import com.ftfl.icare.adapter.MedicalHistoryCustomAdapter;
import com.ftfl.icare.database.MedicalHistoryDataSource;
import com.ftfl.icare.model.MedicalHistory;

public class MedicalHistoryListActivity extends Activity {

	// Declaration of ListView and Button
	ListView mListViewMedical = null;

	Integer mSelectedID = null;

	// Profile Object
	MedicalHistory mMedical = null;

	// DataSource Object
	MedicalHistoryDataSource mDataSource = null;

	// Profile Type ArrayList
	ArrayList<MedicalHistory> mMedicalArrayList = null;

	// CustomAdapter
	MedicalHistoryCustomAdapter mCustomAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_history_list);

		// Initialization of ListView and Button
		mListViewMedical = (ListView) findViewById(R.id.listViewMedical);

		// Get Data From DataSource
		mDataSource = new MedicalHistoryDataSource(this);
		mMedicalArrayList = mDataSource.getMedicalHistoryList();

		// Set Adapter
		mCustomAdapter = new MedicalHistoryCustomAdapter(
				MedicalHistoryListActivity.this, mMedicalArrayList);
		mListViewMedical.setAdapter(mCustomAdapter);

		mListViewMedical
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						mSelectedID = mMedicalArrayList.get(position).getmId();

						// // Creating Bundle object
						Bundle b = new Bundle();

						// put id into bundle
						b.putInt("id", mSelectedID);
						Intent i = new Intent(MedicalHistoryListActivity.this,
								MedicalHistoryViewActivity.class);
						// Storing bundle object into intent
						i.putExtras(b);
						startActivity(i);

						// Finishing the Current Activity
						finish();
					}
				});

	}

	public void goMedicalHome(View view) {
		Intent mHomeIntent = new Intent(MedicalHistoryListActivity.this,
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
			Intent mIntent = new Intent(MedicalHistoryListActivity.this,
					MedicalHistoryCreateActivity.class);

			startActivity(mIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
