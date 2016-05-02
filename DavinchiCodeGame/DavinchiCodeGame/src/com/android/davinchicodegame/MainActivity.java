package com.android.davinchicodegame;

import com.example.davinchicodegame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	public static final int REQUEST_CODE_ANOTHER = 1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(getBaseContext(), howtogame.class);
				startActivityForResult(intent, REQUEST_CODE_ANOTHER);
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent Data){
		super.onActivityResult(requestCode, resultCode, Data);
		
		if(requestCode == REQUEST_CODE_ANOTHER){
			Toast toast = Toast.makeText(getBaseContext(), 
					"onActivityResult 메소드가 호출됨. 요청코드: " + requestCode
					+ ", 결과 코드 : " + resultCode,
					Toast.LENGTH_LONG);
			toast.show();
			
			if(resultCode == RESULT_OK){
				String name = Data.getExtras().getString("name");
				toast = Toast.makeText(getBaseContext(), "응답으로 전달된 name : " + name, Toast.LENGTH_LONG);
				toast.show();
			}
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
