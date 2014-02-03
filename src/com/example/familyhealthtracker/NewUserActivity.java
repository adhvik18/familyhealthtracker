package com.example.familyhealthtracker;


import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.util.Log;

public class NewUserActivity extends Activity {
	
public static final String EXTRA_MESSAGE1 = "USER_NAME";
public static final String EXTRA_MESSAGE2 = "AGE";
public static final String NEXT_LINE="\n";
public TextView textUserName,textUserInfo,textSex,textAge;
public EditText editUserName,editAge;
public RadioGroup rGroup;
public Button submitButton;
public String userName;
public String age;
public String gender;

	
	public void initialize()
	{
		rGroup = (RadioGroup) findViewById(R.id.rGroupGender);
		textUserName= (TextView) findViewById(R.id.textViewUserName);
	    textUserInfo= (TextView) findViewById(R.id.textViewUserInfo);
		textSex= (TextView) findViewById(R.id.textViewSex);
		textAge= (TextView) findViewById(R.id.textViewAge);
		editUserName= (EditText) findViewById(R.id.editTextUserName);
		editAge= (EditText) findViewById(R.id.editTextAge);
		//radioMale= (RadioButton) findViewById(R.id.radioButtonMale);
		//radioFemale= (RadioButton) findViewById(R.id.radioButtonFemale);
		submitButton= (Button)findViewById(R.id.buttonSubmit);
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		initialize();
		submitButton.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View view){
				userName=editUserName.getText().toString();
				age=editAge.getText().toString();
				int id =rGroup.getCheckedRadioButtonId();
				switch(id)
				{
				case R.id.radioButtonMale: gender="Male";break;
				case R.id.radioButtonFemale: gender = "female";break;
				
				default : gender = "N/A";
				}
				submitButtonAction();
				
				
			}
		});
		
	}
	
	public void submitButtonAction()
	{
		Intent next = new Intent(this, DetailUserName.class);
		next.putExtra(EXTRA_MESSAGE1,userName);
		//next.putExtra(EXTRA_MESSAGE2,age);
		/*SharedPreferences mypreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mypreferences.edit();
		editor.putString("userName", userName);
		editor.putString("userAge",age);
		editor.putString("userGender", gender);
		editor.commit();
		*/
		String filename = userName;
		//String string = "Hello world!";
		String filename1 = "userNames";
		FileOutputStream outputStream;
		FileOutputStream outputStream1;
		try {
		  outputStream = openFileOutput(filename, Context.MODE_APPEND);
		  outputStream1 = openFileOutput(filename1, Context.MODE_APPEND);
		  outputStream.write(userName.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream1.write(userName.getBytes());
		  outputStream1.write(NEXT_LINE.getBytes());
		  outputStream.write(age.getBytes());
		  outputStream.write(NEXT_LINE.getBytes());
		  outputStream.write(gender.getBytes());
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
		getMenuInflater().inflate(R.menu.new_user, menu);
		return true;
	}

}
