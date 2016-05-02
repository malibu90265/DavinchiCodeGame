package com.example.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameSetting extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gamesetting);
	    Button button2 = (Button)findViewById(R.id.btn2_back);
	    
	    button2.setOnClickListener(new OnClickListener(){
	    	
	    	@Override
	    	public void onClick(View v){
	    		finish();
	    	}
	    });
	    // TODO Auto-generated method stub
	}

}
