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

import com.ftfl.icare.adapter.VaccinationCustomAdapter;
import com.ftfl.icare.database.VaccinationDataSource;
import com.ftfl.icare.model.Vaccination;

public class VaccinationListActivity extends Activity {

	// Declaration of ListView and Button
	ListView mListViewVaccination = null;

	Integer mSelectedID = null;

	// Profile Object
	Vaccination mVaccine = null;

	// DataSource Object
	VaccinationDataSource mDataSource = null;

	// Profile Type ArrayList
	ArrayList<Vaccination> mVaccinationArrayList = null;

	// CustomAdapter
	VaccinationCustomAdapter mCustomAdapter = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vaccine_list);

		// Initialization of ListView and Button
		mListViewVaccination = (ListView) findViewById(R.id.listViewVaccine);

		// Get Data From DataSource
		mDataSource = new VaccinationDataSource(this);
		mVaccinationArrayList = mDataSource.getVaccinationList();

		// Set Adapter
		mCustomAdapter = new VaccinationCustomAdapter(
				VaccinationListActivity.this, mVaccinationArrayList);
		mListViewVaccination.setAdapter(mCustomAdapter);

		mListViewVaccination
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								VaccinationListActivity.this);
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
										int sId = mVaccinationArrayList.get(
												mSelectedID).getmId();
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

										int sId = mVaccinationArrayList.get(
												mSelectedID).getmId();

										// Creating Bundle object
										Bundle b = new Bundle();

										// put id into bundle
										b.putInt("id", sId);

										// Intent
										Intent updateIntent = new Intent(
												VaccinationListActivity.this,
												VaccinatioUpdateActivity.class);

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

	public void goVaccineHome(View view) {
		Intent mHomeIntent = new Intent(VaccinationListActivity.this,
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
			Intent mIntent = new Intent(VaccinationListActivity.this,
					VaccinationCreateActivity.class);

			startActivity(mIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
