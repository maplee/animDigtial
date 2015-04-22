package com.jia.animnumber;

import android.app.Activity;
import android.os.Bundle;

import com.jia.animnumber.ui.AnimNumberView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anim_number);
		initView();
	}

	/**
	 * 
	 */
	private void initView() {
		AnimNumberView animTimeView = (AnimNumberView)findViewById(R.id.activity_anim_time_view);
		animTimeView.setCurrentValue(48.5,36.9);
	}

}
