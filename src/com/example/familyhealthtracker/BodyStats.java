package com.example.familyhealthtracker;

import java.io.FileOutputStream;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BodyStats extends Activity {
	
	public static final String EXTRA_MESSAGE1 = "USER_NAME";
	public static final String NEXT_LINE="\n";
	public Button submitButton,cancelButton,chartButton;
    public TextView time;
    public EditText editWeight,editHeight,editChest,editWaist,editHip;
	public String userName,timestamp;
	public String weight,height,chest,waist,hip;
	
	public void initialize()
	{
		submitButton= (Button)findViewById(R.id.buttonSubmit);
		cancelButton= (Button)findViewById(R.id.buttonCancel);
		chartButton= (Button)findViewById(R.id.buttonViewChart);
		editWeight= (EditText) findViewById(R.id.editTextWeight);
		editHeight= (EditText) findViewById(R.id.EditTextHeight);
		editChest= (EditText) findViewById(R.id.EditTextChest);
		editWaist= (EditText) findViewById(R.id.EditTextWaist);
		editHip= (EditText) findViewById(R.id.EditTextHip);
		time = (TextView) findViewById(R.id.timestamp);
		java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
		String date = dateFormat.format(new Date());
		time.setText(date);
		Intent intent = getIntent();
		userName = intent.getStringExtra(DetailUserName.EXTRA_MESSAGE1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_body_stats);
		initialize();
        cancelButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				cancelButtonAction();
			}
		});
        submitButton.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View view){
				weight=editWeight.getText().toString();
				height=editHip.getText().toString();
				chest=editChest.getText().toString();
				waist=editWaist.getText().toString();
				hip=editHip.getText().toString();
				timestamp=time.getText().toString();
				submitButtonAction();
				
				
			}
		});
        chartButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				Intent next= new Intent(BodyStats.this,ChartActivity.class);
		    	startActivity(next);
				
			}
		});
        
	}
	
	public void cancelButtonAction()
    {
    	finish();
    }
	
	public void submitButtonAction()
	{
		Intent next = new Intent(this, DetailUserName.class);
		next.putExtra(EXTRA_MESSAGE1,userName);
		String filename = userName;
		FileOutputStream outputStream;

		try {
		  outputStream = openFileOutput(filename, Context.MODE_APPEND);
		  outputStream.write(timestamp.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(weight.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(height.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(chest.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(waist.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(waist.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
	    startActivity(next);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.body_stats, menu);
		return true;
	}

}
