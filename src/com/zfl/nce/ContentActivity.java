package com.zfl.nce;

import com.zfl.nce.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ContentActivity extends Activity {

	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.contentlayout);
	       Bundle bundle = this.getIntent().getExtras();
	       int position = bundle.getInt("position");
	       // String position = this.getIntent().getStringExtra("position");
	        TextView tv = (TextView) findViewById(R.id.contentView);
	        tv.setText(ReadXML.contentList.get(position));
	}

}
