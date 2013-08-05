package com.wilsonmc.tryactivities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DisplayMessageActivity extends Activity {

	public static final String SEARCH_STRING = null;

	TextView label1;
	
	boolean safety_on;
	
	String part1, part2, part3, sEngine, message = null;

	RadioGroup group1;

	RadioButton radio0, radio1, radio2;

	Button button1;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent intent = getIntent();
		message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

//	    setContentView(textView);
//	    
		group1 = (RadioGroup)

				findViewById(R.id.radioGroup1);

		radio0 = (RadioButton)

				findViewById(R.id.radio0);

		radio1 = (RadioButton)

				findViewById(R.id.radio1);
		
		radio2 = (RadioButton)

				findViewById(R.id.radio2);

		button1 = (Button) findViewById(R.id.button1);

		label1 = (TextView) findViewById(R.id.textView1);

		label1.setText("Please select Search options:");
	}
	
	public void safeToggler(View view){
		String safeString = null;
		safety_on = ((ToggleButton) view).isChecked();
		if (safety_on){
			safeString = "Safe Search ON";
		}
		else{
			safeString = "Safe Search OFF";
		}
		Toast.makeText(DisplayMessageActivity.this, safeString, Toast.LENGTH_LONG).show();
		
	}
	
	public void radioButtonClick(View view){
		boolean checked = ((RadioButton) view).isChecked();
		switch(view.getId()) {
        case R.id.radio0:
            if (checked)
            	sEngine = "Google";
                part1 = "http://www.google.com/search?q=";
            break;
        case R.id.radio1:
            if (checked)
            	sEngine = "Yahoo";
            	part1 = "http://search.yahoo.com/search?p=";
            break;
        case R.id.radio2:
            if (checked)
            	sEngine = "Bing";
            	part1 = "http://www.bing.com/search?q=";
            break;
    }
	}
	
	public void doSearch(View view){
		//Toast.makeText(DisplayMessageActivity.this, "Launching Browser...", Toast.LENGTH_SHORT).show();
		if (safety_on){
			part3 = "&safe=on";
		}
		else{
			part3 = "&safe=off";
		}
		
		part2 = message;
		Intent intent = new Intent(this, WebSearchActivity.class);
		String searchString = part1 + part2 + part3;
		intent.putExtra(SEARCH_STRING, searchString.toString());
		Toast.makeText(DisplayMessageActivity.this, "Searching" + sEngine + ".", Toast.LENGTH_SHORT).show();
		startActivity(intent);
		
		
	}

	/**	
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
