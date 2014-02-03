package com.example.familyhealthtracker;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailUserName extends Activity {
	
	public static final String EXTRA_MESSAGE1="USER_NAME";
	public TextView personName,personAge,personGender;
	public String userName;
	public Button bpButton,glucoseButton,heartButton,bodyButton,remButton;
	
	public void initialize()
	{
		personName= (TextView) findViewById(R.id.textViewPersonName);
		personAge= (TextView) findViewById(R.id.textViewPersonAge);
		personGender = (TextView) findViewById(R.id.textViewPersonGender);
		bpButton = (Button) findViewById(R.id.buttonBP);
		glucoseButton = (Button) findViewById(R.id.buttonGlucose);
		heartButton = (Button) findViewById(R.id.buttonHeartRate);
		bodyButton = (Button) findViewById(R.id.buttonBodyStats);
		remButton = (Button) findViewById(R.id.buttonReminder);
		Intent intent = getIntent();
		userName = intent.getStringExtra(NewUserActivity.EXTRA_MESSAGE1);
		userName = intent.getStringExtra(ExistingUserActivity.EXTRA_MESSAGE1);
		//message2 = intent.getStringExtra(NewUserActivity.EXTRA_MESSAGE2);
		//personName.setText(message1);
		//personAge.setText(message2);
		/*SharedPreferences mypreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
		personName.setText(mypreferences.getString("userName", null));
		personAge.setText(mypreferences.getString("userAge",null));
		personGender.setText(mypreferences.getString("userGender", null));
		*/
		
		try {
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
		            openFileInput(userName)));
		    String inputString;
		    StringBuffer stringBuffer = new StringBuffer();  
		    //while ((inputString = inputReader.readLine()) != null) {
		    //    stringBuffer.append(inputString + "\n");
		    //}
		    stringBuffer.append(inputReader.readLine());
		    personName.setText(stringBuffer.toString());
		    inputString = inputReader.readLine();
		    personAge.setText(inputString);
		    inputString = inputReader.readLine();
		    personGender.setText(inputString);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_user_name);
		initialize();
	    bpButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		bpButtonAction();
			
		}
	});
	glucoseButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		glucoseButtonAction();
		}
	});
	heartButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		heartButtonAction();
		}
	});
	bodyButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		bodyButtonAction();
		}
	});
remButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		remButtonAction();
		}
	});
	}
	
	public void bpButtonAction() {
		
		Intent next= new Intent(DetailUserName.this,BpActivity.class);
		next.putExtra(EXTRA_MESSAGE1,userName);
    	startActivity(next);
	}
	
	public void glucoseButtonAction() {
		Intent next = new Intent(DetailUserName.this,Glucose.class);
		next.putExtra(EXTRA_MESSAGE1,userName);
		startActivity(next);
	}
	
	public void heartButtonAction() {
		Intent next = new Intent(DetailUserName.this,HeartRate.class);
		next.putExtra(EXTRA_MESSAGE1,userName);
		startActivity(next);
	}
	
	public void bodyButtonAction() {
		Intent next = new Intent(DetailUserName.this,BodyStats.class);
		next.putExtra(EXTRA_MESSAGE1,userName);
		startActivity(next);
	}
	
	public void remButtonAction() {
		Intent next = new Intent(DetailUserName.this,ReminderActivity.class);
		next.putExtra(EXTRA_MESSAGE1,userName);
		startActivity(next);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it  present.
		getMenuInflater().inflate(R.menu.detail_user_name, menu);
		return true;
	}

}
