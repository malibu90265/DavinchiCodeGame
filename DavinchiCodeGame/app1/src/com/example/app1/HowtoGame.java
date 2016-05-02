package com.example.app1;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

// 게임 방법 activity
public class HowtoGame extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.howtogame);
	    
		ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
		ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
		ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
		ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
		
		iv1.setAdjustViewBounds(true);
		iv2.setAdjustViewBounds(true);
		iv3.setAdjustViewBounds(true);
		iv4.setAdjustViewBounds(true);
		
		iv1.setImageDrawable(getResources().getDrawable(R.drawable.howtogame1));
		iv2.setImageDrawable(getResources().getDrawable(R.drawable.howtogame2));
		iv3.setImageDrawable(getResources().getDrawable(R.drawable.howtogame3));
		iv4.setImageDrawable(getResources().getDrawable(R.drawable.howtogame4));
		


	    // TODO Auto-generated method stub
	}

}