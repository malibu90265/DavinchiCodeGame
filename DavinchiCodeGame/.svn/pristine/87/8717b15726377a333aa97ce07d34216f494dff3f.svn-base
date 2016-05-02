package com.example.app1;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//button0 : 게임시작
		Button button0 = (Button)findViewById(R.id.btn00);
		button0.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				//Toast.makeText(getApplicationContext(), "게임 시작", Toast.LENGTH_LONG).show();

				Intent intent = new Intent(getApplicationContext(), GameStart.class);
				startActivity(intent);
			}
		});
		

		//button1 : 게임 방법		
		Button button1 = (Button)findViewById(R.id.btn01);
		button1.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				//Toast.makeText(getApplicationContext(), "게임 방법", Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(getApplicationContext(), HowtoGame.class);
				startActivity(intent);
				
			}
		});
		
		//button2 : 게임 설정
		Button button2 = (Button)findViewById(R.id.btn02);
		button2.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				//Toast.makeText(getApplicationContext(), "게임 설정", Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(getApplicationContext(), GameSetting.class);
				startActivity(intent);
				
			}
		});
		
		//button3 : 게임 종료
				Button button3 = (Button)findViewById(R.id.btn03);
				button3.setOnClickListener(new OnClickListener(){
					
					@Override
					public void onClick(View v){
						//Toast.makeText(getApplicationContext(), "게임 설정", Toast.LENGTH_LONG).show();
						finish();
			               android.os.Process.killProcess(android.os.Process.myPid());
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
