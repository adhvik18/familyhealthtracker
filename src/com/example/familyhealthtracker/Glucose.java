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
import android.widget.Toast;

public class Glucose extends Activity {
	public static final String EXTRA_MESSAGE1 = "USER_NAME";
	public static final String NEXT_LINE="\n";
	public Button submitButton,cancelButton,chartButton;
	public TextView time;
	public EditText editGlucose,editHbA1c;
	public String userName,timestamp;
	public String glucose,HbA1c;
	
	public void initialize()
	{
		submitButton= (Button)findViewById(R.id.buttonSubmit);
		cancelButton= (Button)findViewById(R.id.buttonCancel);
		chartButton= (Button)findViewById(R.id.buttonViewChart);
		editGlucose= (EditText) findViewById(R.id.editTextGlucose);
		editHbA1c= (EditText) findViewById(R.id.editTextHbA1c);
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
		setContentView(R.layout.activity_glucose);
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
				glucose=editGlucose.getText().toString();
				HbA1c=editHbA1c.getText().toString();
				timestamp=time.getText().toString();
				submitButtonAction();
				
				
			}
		});
        
        chartButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				Intent next= new Intent(Glucose.this,ChartActivity.class);
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
		if(Integer.parseInt(glucose)>=8)
		{
			Toast.makeText(getBaseContext(), "Glucose level high", Toast.LENGTH_LONG).show();
		}
		if(Integer.parseInt(HbA1c)>=6 && Integer.parseInt(HbA1c)<=6.5)
		{
			Toast.makeText(getBaseContext(), "At a risk of diabetes", Toast.LENGTH_LONG).show();
		}
		else if(Integer.parseInt(HbA1c)>6.5)
		{
			Toast.makeText(getBaseContext(), "Check for Diabetes", Toast.LENGTH_LONG).show();
		}


		try {
		  outputStream = openFileOutput(filename, Context.MODE_APPEND);
		  outputStream.write(timestamp.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(glucose.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(HbA1c.getBytes());
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
		getMenuInflater().inflate(R.menu.glucose, menu);
		return true;
	}

}
