package com.android.davinchicodegame;

import com.example.davinchicodegame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class howtogame extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button returnBtn = (Button) findViewById(R.id.returnBtn);
		returnBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent resultIntent = new Intent();
				resultIntent.putExtra("name","mike");
				setResult(1, resultIntent);
				finish();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}