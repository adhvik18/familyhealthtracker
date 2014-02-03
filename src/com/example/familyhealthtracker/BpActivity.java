package com.example.familyhealthtracker;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import android.widget.Toast;

public class BpActivity extends Activity {
	
	public static final String EXTRA_MESSAGE1 = "USER_NAME";
	public static final String NEXT_LINE="\n";
	public Button submitButton,cancelButton,chartButton;
	public TextView time;
	public EditText editSP,editDP;
	public String userName,timestamp;
	public String systolicPressure,diastolicPressure;
	
	public void initialize()
	{
		submitButton= (Button)findViewById(R.id.buttonSubmit);
		cancelButton= (Button)findViewById(R.id.buttonCancel);
		chartButton= (Button)findViewById(R.id.buttonViewChart);
		editSP= (EditText) findViewById(R.id.editTextSystolic);
		editDP= (EditText) findViewById(R.id.editTextDiastolic);
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
		setContentView(R.layout.activity_bp);
		initialize();
		
        cancelButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				cancelButtonAction();
			}
		});
        
        submitButton.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View arg0) {
    			// TODO Auto-generated method stub
    			systolicPressure=editSP.getText().toString();
				diastolicPressure=editDP.getText().toString();
				timestamp=time.getText().toString();
    		    submitButtonAction();
    			
    		}
    	});
        
        chartButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				Intent next= new Intent(BpActivity.this,ChartActivity.class);
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
		
		if( (Integer.parseInt(systolicPressure) >= 120 && Integer.parseInt(systolicPressure) <= 139) || 
				(Integer.parseInt(diastolicPressure) >= 80 && Integer.parseInt(diastolicPressure) <= 89))
		{
			Toast.makeText(getBaseContext(), "Pre hyper tension", Toast.LENGTH_LONG).show();
		}
		
		if( (Integer.parseInt(systolicPressure) >= 140 && Integer.parseInt(systolicPressure) <= 159) || 
				(Integer.parseInt(diastolicPressure) >= 90 && Integer.parseInt(diastolicPressure) <= 99))
		{
			Toast.makeText(getBaseContext(), "High Blood Pressure Stage 1 hyper tension", Toast.LENGTH_LONG).show();
		}
		
		if( (Integer.parseInt(systolicPressure) >= 160) || 
				(Integer.parseInt(diastolicPressure) >= 100) )
		{
			Toast.makeText(getBaseContext(), "High Blood Pressure Stage 2 hyper tension", Toast.LENGTH_LONG).show();
		}
		
		
		
			try {
		  outputStream = openFileOutput(filename, Context.MODE_APPEND);
		  outputStream.write(timestamp.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(systolicPressure.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(diastolicPressure.getBytes());
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
		getMenuInflater().inflate(R.menu.bp, menu);
		return true;
	}

}
