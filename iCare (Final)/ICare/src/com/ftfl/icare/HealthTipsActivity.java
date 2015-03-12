package com.ftfl.icare;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.ftfl.icare.adapter.HealthTipsCustomAdapter;

public class HealthTipsActivity extends Activity {

	// Variable Declaration
	ListView mLvHealthTips = null;

	// Care Center Custom Adapter
	HealthTipsCustomAdapter mCareCenterAdapter;

	// care center informations
	ArrayList<String> namesList = new ArrayList<String>();
	ArrayList<String> tipsList = new ArrayList<String>();

	String[] mName = { "1. Give Your Diet a Berry Boost",
			"2. Get Dirty -- and Stress Less", "3. Floss Daily",
			"4. Get Outside to Exercise", "5. Be Good to Your Eyes",
			"6. Vacation Time!", "7. Alcohol: Go Lite", "8. Sleep Well" };

	String[] mTips = {
			"If you do one thing this summer to improve your diet, have a cup of mixed fresh berries -- blackberries, blueberries, or strawberries -- every day. They'll help you load up on antioxidants, which may help prevent damage to tissues and reduce the risks of age-related illnesses. Blueberries and blackberries are especially antioxidant-rich",
			"To improve your stress level, plant a small garden, cultivate a flower box, or if space is really limited, plant a few flower pots -- indoors or out. Just putting your hands in soil is grounding. And when life feels like you're moving so fast your feet are barely touching the stuff, being mentally grounded can help relieve physical and mental stress. ",
			"You know you need to, now it's time to start: floss every single day. Do it at the beach (in a secluded spot), while reading on your patio, or when watching TV -- and the task will breeze by. Flossing reduces oral bacteria, which improves overall body health, and if oral bacteria is low, your body has more resources to fight bacteria elsewhere. Floss daily and you're doing better than at least 85% of people.",
			"Pick one outdoor activity -- going on a hike, taking a nature walk, playing games such as tag with your kids, cycling, roller blading, or swimming -- to shed that cooped-up feeling of gym workouts.  And remember, the family that plays together not only gets fit together -- it's also a great way to create bonding time.",
			"To protect your vision at work and at play, wear protective eyewear. When outdoors, wear sunglasses that block at least 99% of ultraviolet A and B rays. Sunglasses can help prevent cataracts, as well as wrinkles around the eyes. And when playing sports or doing tasks such as mowing the lawn, wear protective eyewear. Ask your eye doctor about the best type; some are sport-specific",
			"Improve your heart health: take advantage of summer's slower schedule by using your vacation time to unwind.  Vacations have multiple benefits: They can help lower your blood pressure, heart rate, and stress hormones such as cortisol, which contributes to a widening waist and an increased risk of heart disease.",
			"Summer's a great time to skip drinks with hard alcohol and choose a light, chilled alcoholic beverage (unless you are pregnant or should not drink for health or other reasons). A sangria (table wine diluted with juice), a cold beer, or a wine spritzer are all refreshing but light. In moderation -- defined as one to two drinks daily -- alcohol can protect against heart disease.",
			"Resist the urge to stay up later during long summer days. Instead pay attention to good sleep hygiene by keeping the same bedtime and wake-up schedule and not drinking alcohol within three hours of bedtime.  It's also a good idea to avoid naps during the day unless you take them every day at the same time, for the same amount of time." };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_tips);

		// Initialize ListView
		mLvHealthTips = (ListView) findViewById(R.id.lvHealthTips);

		// Initialize Data Source
		namesList.addAll(Arrays.asList(mName));
		tipsList.addAll(Arrays.asList(mTips));
		// Passing Through Custom Adapter
		mCareCenterAdapter = new HealthTipsCustomAdapter(this, namesList,
				tipsList);

		mLvHealthTips.setAdapter(mCareCenterAdapter);

	}
}
