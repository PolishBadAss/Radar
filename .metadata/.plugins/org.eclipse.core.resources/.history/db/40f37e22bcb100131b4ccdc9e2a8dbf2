package com.example.javacvapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CvActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cv);
		try {
			VideoRape.createBWFrames();
			VideoRape.setRange();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cv, menu);
		return true;
	}

}
