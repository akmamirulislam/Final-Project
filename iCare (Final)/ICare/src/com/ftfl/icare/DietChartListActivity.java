package com.ftfl.icare;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ftfl.icare.adapter.DietChartCustomAdapter;
import com.ftfl.icare.database.DietChartDataSource;
import com.ftfl.icare.model.DietChart;

public class DietChartListActivity extends Activity {

	// Declaration of ListView and Button
	ListView mListViewDiet = null;

	Integer mSelectedID = null;

	// Profile Object
	DietChart mDiet = null;

	// DataSource Object
	DietChartDataSource mDataSource = null;

	// Profile Type ArrayList
	ArrayList<DietChart> mDietChartArrayList = null;

	// CustomAdapter
	DietChartCustomAdapter mCustomAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diet_chart_list);

		// Initialization of ListView and Button
		mListViewDiet = (ListView) findViewById(R.id.listViewDietChart);

		// Get Data From DataSource
		mDataSource = new DietChartDataSource(this);
		mDietChartArrayList = mDataSource.getDietList();

		// Set Adapter
		mCustomAdapter = new DietChartCustomAdapter(DietChartListActivity.this, mDietChartArrayList);
		mListViewDiet.setAdapter(mCustomAdapter);

		mListViewDiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								DietChartListActivity.this);
						mSelectedID = position;
						// Setting Dialog Title
						alertDialog.setTitle("iCare");
						// Setting Dialog Message
						alertDialog.setMessage("What do you want to do?");
						// Setting Icon to Dialog
						alertDialog.setIcon(R.drawable.ic_launcher);

						// Setting Negative "Delete" Button
						alertDialog.setNegativeButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// Write your code here to invoke NO
										// event
										Toast.makeText(getApplicationContext(),
												"Deleted", Toast.LENGTH_SHORT)
												.show();
										int sId = mDietChartArrayList.get(
												mSelectedID).getmDietId();
										mDataSource.deleteData(sId);
										dialog.cancel();
										startActivity(getIntent());

										// Finishing the Current Activity
										finish();
									}
								});

						// Setting Positive "Edit" Button
						alertDialog.setPositiveButton("Edit",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

										int sId = mDietChartArrayList.get(
												mSelectedID).getmDietId();

										// Creating Bundle object
										Bundle b = new Bundle();

										// put id into bundle
										b.putInt("id", sId);

										// Intent
										Intent updateIntent = new Intent(
												DietChartListActivity.this,
												DietChartUpdateActivity.class);

										// Storing bundle object into intent
										updateIntent.putExtras(b);

										// StartActivity
										startActivity(updateIntent);

									}
								});

						// Showing Alert Message
						alertDialog.show();
					}

				});

	}

	public void goDietHome(View view) {
		Intent mHomeIntent = new Intent(DietChartListActivity.this,
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
			Intent mIntent = new Intent(DietChartListActivity.this,
					DietChartCreateActivity.class);

			startActivity(mIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
